package com.wipro.igrs.regCompChecker.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import net.lingala.zip4j.core.ZipFile;
import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.newgen.burning.Images;
import com.newgen.burning.Party;
import com.wipro.igrs.RegCompMaker.constants.RegCompConstant;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;
import com.wipro.igrs.RegCompMaker.sql.RegCommonMkrSQL;
import com.wipro.igrs.Seals.dao.SealsDAO;
import com.wipro.igrs.Seals.sql.SealsSQL;
import com.wipro.igrs.aadhar.common.util.AadharUtil;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.device.applet.EtokenChange;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dao.RegCommonDAO;
import com.wipro.igrs.newreginit.dto.AadharDetailDTO;
import com.wipro.igrs.newreginit.dto.AadharRespDTO;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;
import com.wipro.igrs.regCompChecker.form.RegCompCheckerForm;
import com.wipro.igrs.regCompChecker.sql.RegCompCheckerSQL;
import com.wipro.igrs.reginit.sql.RegCommonSQL;

public class RegCompCheckerDAO {
	ArrayList mainList = null;
	String sql = null;
	String sqlOne = null;
	private Logger logger = Logger.getLogger(RegCompCheckerDAO.class);
	private ArrayList subList;

	/*
	 * 
	 * Method to check whether data is available for entered registration ID and 
	 * transaction for that Id is Complete or not
	 * Returns Boolean
	 */
	public Boolean checkData(String regNumber) throws Exception {
		Boolean dataFound = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			mainList = new ArrayList();
			
			sql = "SELECT REG_TXN_ID,REGISTRATION_TXN_STATUS FROM IGRS_REG_TXN_DETLS " +
					"WHERE  REG_TXN_ID='" + regNumber + "'";
			mainList = dbUtility.executeQuery(sql);*/
			String SQL = RegCompCheckerSQL.GET_REG_TXN_ID;
			dbUtility.createPreparedStatement(SQL);
			mainList = dbUtility.executeQuery(new String[] { regNumber });
			if (mainList != null) {
				for (int i = 0; i < mainList.size(); i++) {
					System.out.println(i);
					ArrayList list = (ArrayList) mainList.get(i);
					if (list != null) {
						//	logger.debug(list.get(1).toString());
						if (list.get(1).toString().equalsIgnoreCase("13")) {
							dataFound = true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
			}
		}
		logger.debug("Transaction Complete in DAO" + dataFound);
		return dataFound;
	}

	// METHOD TO GET STAMP DUTY, UPKAR DUTY, NAGAR PALIKA DUTY , REG FEE AND TOTAL FEE 
	public ArrayList getDutyDetls(String appId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_REG_INIT_DUTY_DETS+"'"+appId+"'";
			list = dbUtility.executeQuery(query);*/
			String SQL = RegCompCheckerSQL.GET_REG_INIT_DUTY_DETS;
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[] { appId });
		} catch (Exception exception) {
			logger.debug(exception.getStackTrace());
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param regInitId
	 * @return
	 */
	public ArrayList getChangedDutyAtMaker(String regInitId) {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_BAL_DUTY_AT_MAKER+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String SQL = RegCompCheckerSQL.GET_BAL_DUTY_AT_MAKER;
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[] { regInitId });
			//logger.debug("size of list-----> "+list.size());
		} catch (Exception exception) {
			logger.debug("Exception in getChangedDutyAtMaker" + exception);
			logger.debug(exception.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * getDeedInstId for getting deed and instrument ids from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author Simran
	 */
	public ArrayList getDeedInstId(String appId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_REG_INIT_DEED_INST_ID+"'"+appId+"'";
			list = dbUtility.executeQuery(query);*/
			String SQL = RegCompCheckerSQL.GET_REG_INIT_DEED_INST_ID;
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[] { appId });
		} catch (Exception exception) {
			logger.debug("Exception in getDeedInstId" + exception);
			logger.debug(exception.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * for getting valuation id corresponding to registration app id and
	 * property Id from db.
	 * 
	 * @param
	 * @return String
	 * @author Simran
	 */
	public String getPropValuationId(String appId, String propId) throws Exception {
		String valuationId = "";
		DBUtility dbUtility = null;
		try {
			String param[] = { appId, propId };
			dbUtility = new DBUtility();
			sql = RegCommonSQL.GET_REG_INIT_PROP_VALUATION_ID;
			dbUtility.createPreparedStatement(sql);
			valuationId = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getPropValuationId-RegCommonDAO" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return valuationId;
	}

	/**
	 * getPropDetlsForDashboard for getting PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author Simran
	 */
	public ArrayList getPropDetlsForDashboard(String appId) throws Exception {
		ArrayList propDetls = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCommonSQL.GET_PROP_DETLS_DASHBOARD+"'"+appId+"'";
			propDetls = dbUtility.executeQuery(query);*/
			String SQL = RegCommonSQL.GET_PROP_DETLS_DASHBOARD;
			dbUtility.createPreparedStatement(SQL);
			propDetls = dbUtility.executeQuery(new String[] { appId });
		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDashboard" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		return propDetls;
	}

	/**
	 * getOtherPropDetsForViewConfirm for getting other property details from
	 * db.
	 * 
	 * @param String, String
	 * @return ArrayList
	 * @author Simran
	 */
	public ArrayList getOtherPropDetsForViewConfirm(String appId, String propId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			String param[] = { appId.trim(), propId.trim() };
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_REG_INIT_OTHER_PROP_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
		} catch (Exception exception) {
			logger.debug("Exception in getOtherPropDetsForViewConfirm" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	/**
	 * getPartyDetsForViewConfirm for getting APPLICANT PARTY details from db.
	 * 
	 * @param String, String
	 * @return ArrayList
	 * @author Simran
	 */
	public ArrayList getPartyDetsForViewConfirm(String appId, String partyId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			String param[] = { appId.trim(), partyId.trim() };
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_PARTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
		} catch (Exception exception) {
			logger.debug("Exception in getApplicantPartyDets" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	/**
	 * getOwnerDetls for getting owner details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author Simran
	 */
	public ArrayList getOwnerDetls(String ownerId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCommonSQL.GET_OWNER_DETAILS+"'"+ownerId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCommonSQL.GET_OWNER_DETAILS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { ownerId });
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	/**
	 * for getting photo id proof name from db.
	 * 
	 * @param
	 * @return String
	 * @author Simran
	 */
	public String getPhotoIdProofName(String proofId) throws Exception {
		String proofname = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql=RegCommonSQL.GET_PHOTO_PROOF_NAME+"'"+proofId+"'";
			proofname = dbUtility.executeQry(sql);*/
			String query = RegCommonSQL.GET_PHOTO_PROOF_NAME;
			dbUtility.createPreparedStatement(query);
			proofname = dbUtility.executeQry(new String[] { proofId });
		} catch (Exception exception) {
			System.out.println("Exception in getPhotoIdProofName-RegCommonDAO" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return proofname;
	}

	/**
	 * for Modifying Details of Individual entered during registration
	 * Initiation Process.
	 * 
	 * @param
	 * @return boolean
	 * @author Simran
	 */
	public boolean savePartyDetails(String[] partyDetails) throws Exception {
		boolean saveParty = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCompCheckerSQL.SAVE_PARTY_DETAILS;
			dbUtility.createPreparedStatement(sql);
			saveParty = dbUtility.executeUpdate(partyDetails);
		} catch (Exception e) {
			logger.debug("Exception in save Party Details in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return saveParty;
	}

	/**
	 * @param regInitId
	 * @return BOOLEAN
	 * @throws Exception AUTHOR SIMRAN
	 */
	public String getAdjDetails(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		String adjNumber = null;
		boolean isAdj = true;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.IS_ADJUDICATED+"'"+regInitId+"'";
			adjNumber = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.IS_ADJUDICATED;
			dbUtility.createPreparedStatement(query);
			adjNumber = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		return adjNumber;
	}

	/**
	 * @param regInitId
	 * @return BOOLEAN
	 * @throws Exception AUTHOR SIMRAN
	 */
	public boolean modifyMarketValue(String[] propDetails) throws Exception {
		boolean modifyMV = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCompCheckerSQL.UPDATE_MV;
			dbUtility.createPreparedStatement(sql);
			modifyMV = dbUtility.executeUpdate(propDetails);
		} catch (Exception e) {
			logger.debug("Exception in saveMarketValue in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return modifyMV;
	}

	/**
	 * @param regInitId
	 * @return BOOLEAN
	 * @throws Exception AUTHOR SIMRAN
	 */
	public boolean modifyDuties(String[] duties, String regInitId) throws Exception {
		boolean modifyDuties = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query = RegCompCheckerSQL.CHECK_ALREADY_INSERTED_DUTIES+"'"+regInitId+"'";
			String count = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.CHECK_ALREADY_INSERTED_DUTIES;
			dbUtility.createPreparedStatement(query);
			String count = dbUtility.executeQry(new String[] { regInitId });
			if (count.equals("0")) {
				sql = RegCompCheckerSQL.INSERT_CHECKER_DUTIES;
				dbUtility.createPreparedStatement(sql);
				modifyDuties = dbUtility.executeUpdate(duties);
			} else {
				/*String query1 = RegCompCheckerSQL.DELETE_ALREDY_INSERTED_DUTIES+"'"+regInitId+"'";
				boolean flag = dbUtility.executeUpdate(query1);*/
				String query1 = RegCompCheckerSQL.DELETE_ALREDY_INSERTED_DUTIES;
				dbUtility.createPreparedStatement(query1);
				boolean flag = dbUtility.executeUpdate(new String[] { regInitId });
				if (flag) {
					sql = RegCompCheckerSQL.INSERT_CHECKER_DUTIES;
					dbUtility.createPreparedStatement(sql);
					modifyDuties = dbUtility.executeUpdate(duties);
				}
			}
		} catch (Exception e) {
			logger.debug("Exception in modifyDuties in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return modifyDuties;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public boolean isLinking(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.IS_LINKING+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.IS_LINKING;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
			if (list.size() > 0) {
				flag = true;
			}
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		logger.debug("Size of Adj List in DAO" + list.size());
		return flag;
	}

	public boolean isLinking2(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.IS_LINKING2+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.IS_LINKING2;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
			if (list.size() > 0) {
				flag = true;
			}
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of Adj List in DAO" + list.size());
		return flag;
	}

	/**
	 * @param regInitId
	 * @return ARRAYLIST
	 * @throws Exception
	 */
	public ArrayList getLinkingDuties(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.LINKING_STAMP_REG_FEE+"'"+regInitId+"'";*/
			String query = RegCompCheckerSQL.LINKING_STAMP_REG_FEE;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
			//list = dbUtility.executeQuery(query);
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of Adj List in DAO" + list.size());
		return list;
	}

	public ArrayList getLinkingDutiesAlreadyPaid(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.LINK_ALREADY_PAID+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.LINK_ALREADY_PAID;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
			dbUtility.closeConnection();
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of Adj List in DAO" + list.size());
		return list;
	}

	public boolean getLinkDetails(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query="SELECT REG_FEE FROM IGRS_REG_CHK_PROP_PYMNT_LNKNG WHERE REG_TXN_ID="+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_REG_FEE;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in View Witness" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		logger.debug("Size of witness List in DAO" + list.size());
		if (list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @return
	 */
	public boolean saveLinkingPropertyPaymentDetails(String regInit, String userId, String date) {
		boolean saveDetails = false;
		DBUtility dbUtility = null;
		try {
			String arr[] = { userId, date, regInit };
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.SAVE_PROP_PAY_LINK_DETAILS;
			dbUtility.createPreparedStatement(sql);
			saveDetails = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.debug("Exception in saveLinkingPropertyPaymentDetails in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return saveDetails;
	}

	/**
	 * @param _fdate
	 * @return String
	 */
	public String getDate(String _fdate) {
		System.out.println("_fdate" + _fdate);
		StringTokenizer stoken = new StringTokenizer(_fdate, "/");
		String dd = stoken.nextToken();
		String mm = stoken.nextToken();
		String yy = stoken.nextToken();
		if (dd.length() == 2) {
			_fdate = dd + "-" + getMonthName(mm) + "-" + yy;
		}
		return _fdate;
	}

	/**
	 * @param _month
	 * @return
	 */
	public String getMonthName(String _month) {
		HashMap hm = new HashMap();
		hm.put("01", "JAN");
		hm.put("02", "FEB");
		hm.put("03", "MAR");
		hm.put("04", "APR");
		hm.put("05", "MAY");
		hm.put("06", "JUN");
		hm.put("07", "JUL");
		hm.put("08", "AUG");
		hm.put("09", "SEP");
		hm.put("10", "OCT");
		hm.put("11", "NOV");
		hm.put("12", "DEC");
		return (String) hm.get(_month);
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList holdDetails() throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = RegCompCheckerSQL.HOLD_DATA;
			list = dbUtility.executeQuery(query);
		} catch (Exception exception) {
			logger.debug("Exception in holdDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection RegCompMkrDAO : saveHoldData " + e.getStackTrace());
			}
		}
		logger.debug("Size of holdDetails in DAO" + list.size());
		return list;
	}

	/**
	 * @param regNum
	 * @param fwdPage
	 * @return boolean
	 */
	public boolean isHoldData(String regNum, String fwdPage) {
		boolean flag = false;
		String holdFlag = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String arr[] = { fwdPage, regNum };
			String query = RegCompCheckerSQL.IS_HOLD_DATA;
			dbUtility.createPreparedStatement(query);
			holdFlag = dbUtility.executeQry(arr);
			if (holdFlag.equalsIgnoreCase("Y")) {
				flag = true;
			}
		} catch (Exception exception) {
			logger.debug("Exception in isHold" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection RegCompMkrDAO : isHold " + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * @param regNum
	 * @param hldFlag
	 * @param fwdPage
	 * @param date
	 * @param createdBy
	 * @return
	 * @throws Exception
	 */
	public boolean saveHoldDataChecker(RegCompCheckerDTO rDTO, String hldFlag, String fwdPage, String date, String createdBy, boolean flag) throws Exception {
		// TODO Auto-generated method stub
		boolean tmpFlag = false;
		String str = "";
		String regNum = rDTO.getRegInitNumber();
		String holdId = rDTO.getHoldId();
		String holdRemarks = rDTO.getRemarks();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			//dbUtility.createStatement();
			if (flag) {
				sql = RegCompCheckerSQL.UPDATE_HOLD_DATA;//+"'"+regNum+"'";
				dbUtility.createPreparedStatement(sql);
				String temp[] = new String[1];
				temp[0] = regNum;
				/*temp[1] = regNum;
				temp[2] = getDate(date);
				temp[3] = holdRemarks;
				temp[4] = getDate(date);
				temp[5] = createdBy;
				temp[6] = regNum;*/
				tmpFlag = dbUtility.executeUpdate(temp);
			}
			sql = RegCompCheckerSQL.INSERT_HOLD_TABLE_DATA_CHECKER;
			dbUtility.createPreparedStatement(sql);
			String temp[] = new String[6];
			temp[0] = rDTO.getHoldPageId();
			temp[1] = regNum;
			temp[2] = getDate(date);
			temp[3] = holdRemarks;
			temp[4] = getDate(date);
			temp[5] = createdBy;
			tmpFlag = dbUtility.executeUpdate(temp);
			if (tmpFlag) {
				sql = RegCompCheckerSQL.UPDATE_REG_STATUS1;
				dbUtility.createPreparedStatement(sql);
				//dbUtility.createStatement();
				//sql = "UPDATE IGRS_REG_TXN_DETLS SET REGISTRATION_TXN_STATUS = '14' WHERE REG_TXN_ID ="+regNum;
				//logger.debug("<-----"+sql);
				//dbUtility.executeUpdate(sql);
				dbUtility.executeUpdate(new String[] { regNum });
				UpdateTImeEtoken(regNum, "14");
				EtokenChange eChange = new EtokenChange(regNum, "14", null);
				Thread t = new Thread(eChange);
				t.start();
			}
		} catch (Exception e) {
			System.out.println("error in regCompMkrDAO : saveHoldData" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection RegCompMkrDAO : saveHoldData " + e.getStackTrace());
			}
		}
		return tmpFlag;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWitnessDetails(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.VIEW_WITNESS+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String regArr[] = { regInitId };
			String query = RegCompCheckerSQL.VIEW_WITNESS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(regArr);
		} catch (Exception exception) {
			logger.debug("Exception in View Witness" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		logger.debug("Size of witness List in DAO" + list.size());
		return list;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getLinkHstryDetails(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.VIEW_LINKING_HISTORY+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.VIEW_LINKING_HISTORY;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in View Linking History Details" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		logger.debug("Size of linkHistory List in DAO" + list.size());
		return list;
	}

	/**
	 * After linking screen
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPartiesPresentDetails(String regInitId, String language) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList list2 = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list3 = new ArrayList();
		ArrayList partyPresentDetails = new ArrayList();
		// for POA owner list - Rahul
		ArrayList poAOwnerListForOrg = new ArrayList();
		RegCommonDAO commonDAO = new RegCommonDAO();
		RegCommonBO commonBO = new RegCommonBO();
		RegCommonBD commonBd = new RegCommonBD();
		RegCommonDTO mapDto = new RegCommonDTO();
		RegCommonForm form = new RegCommonForm();
		int i1 = 0;
		String OwnerRolePoA = "";
		RegCompCheckerDTO checkerDTO = new RegCompCheckerDTO();
		String language1[] = regInitId.split(":");
		ArrayList poAOwnerList = new ArrayList();
		if (language1.length > 1) {
			regInitId = language1[0];
			language = language1[1];
		} else {
			regInitId = language1[0];
		}
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String sql = RegCompCheckerSQL.VIEW_FROM_REG_INIT+"'"+regInitId+"'";
			list = dbUtility.executeQuery(sql);
			String query=RegCompCheckerSQL.VIEW_PRESENT_PARTIES+"'"+regInitId+"'";
			list2 = dbUtility.executeQuery(query);*/
			String sql = RegCompCheckerSQL.VIEW_FROM_REG_INIT;
			dbUtility.createPreparedStatement(sql);
			// added new one for Govt Rahul
			list = dbUtility.executeQuery(new String[] { regInitId });
			String query = RegCompCheckerSQL.VIEW_PRESENT_PARTIES;
			dbUtility.createPreparedStatement(query);
			list2 = dbUtility.executeQuery(new String[] { regInitId });
			// to check common flow  for will Deed - Rahul
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String arrCommon[] = { regInitId };
			sqlOne = RegCommonMkrSQL.GET_COMMON_FLOW_FLAG;
			System.out.println(sqlOne);
			dbUtility.createPreparedStatement(sqlOne);
			String CommonDeedFlag = "";
			CommonDeedFlag = dbUtility.executeQry(arrCommon);
			System.out.println("CommonDeedFlag" + CommonDeedFlag);
			String partyId = "";
			for (int i = 0; i < list.size(); i++) {
				list1 = (ArrayList) list.get(i);
				RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
				if (list1.get(0) != null) {
					partyId = list1.get(0).toString();
					//added by Ankit for aadhar integration - start
					AadharDetailDTO aadharDts = new RegCompCheckerBD().getAadharDetailsByPartyTxnId(partyId);
					if (aadharDts.getACKID_CHECKER() != null && aadharDts != null && !aadharDts.getACKID_CHECKER().isEmpty()) {
						rDTO.setIsAadharValidated("true");
					} else {
						rDTO.setIsAadharValidated("false");
					}
				}
				if (list1.get(16) != null) {
					rDTO.setSelectedPhotoId(list1.get(16).toString());
				} else {
					rDTO.setSelectedPhotoId("");
				}
				//added by Ankit for aadhar integration - end
				// for PoA , Owner display - Rahul 
				int roleId = Integer.parseInt((String) list1.get(5));
				String[] claimantArr = commonBO.getClaimantFlag(Integer.toString(roleId));
				mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				// int ExecutantClaimantfromDB = Integer.parseInt((String)list1.get(15));
				int ExecutantClaimantfromDB = 0;
				if (list1.get(14) != null && !list1.get(14).toString().equalsIgnoreCase("")) {
					ExecutantClaimantfromDB = Integer.parseInt((String) list1.get(14));
				}
				// For Will Deed - Authenticated - Rahul
				if (list1.get(8) != null && list1.get(8).toString().equalsIgnoreCase("2")) {
					if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						//int ExecutantClaimantfromDB = Integer.parseInt((String)list1.get(15));
						System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
						form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
						String RoleName = form.getExecutantClaimantName();
						System.out.println("Role Name " + RoleName);
						// to check hindi and english role 
						String HindiEnglishRole[] = RoleName.split(",");
						String hindesc = HindiEnglishRole[0];
						String EngDesc = HindiEnglishRole[1];
						String PoaName = null;
						if (EngDesc.contains("Authenticated")) {
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(Authenticated PoA Holder) ";
							} else {
								PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
							}
						} else {
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(PoA Holder) ";
							} else {
								PoaName = " द्वारा (मुख्‍त्‍यार आम)";
							}
						}
						/* String OwnerDetail =null;
						 if(i1<poAOwnerList.size())
						 {
						 ArrayList m = (ArrayList) poAOwnerList.get(i1);
							mapDto.setOwnerPartyDesc(m.get(0).toString());
						 
						 OwnerDetail =mapDto.getOwnerPartyDesc();
						 System.out.println("Owner result from DB is "+OwnerDetail);
						   
						   String parts[] = OwnerDetail.split(",");
						   String Owner = parts[0];
						   String PoADesc =parts[1];
						 
							OwnerRolePoA=Owner+PoaName+PoADesc;
							System.out.println("owner Role to display "+ OwnerRolePoA);
						 }*/
						String poAOwnerListNew = commonBd.getOwnerDetails(regInitId, partyId, language);
						String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
						OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
						rDTO.setFnameTrns(OwnerRolePoA);
						rDTO.setLnameTrns("");
					} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						String RoleName = null;
						rDTO.setRoleName((String) list1.get(3));
						// rDTO.setPartyRole(getPartyRole((String) subList.get(3),language));
						rDTO.setPartyRoleFullNamePOA(commonBO.getRoleNameForPOA((String) list1.get(5)));
						RoleName = rDTO.getPartyRoleFullNamePOA();
						System.out.println("Role Name " + RoleName);
						// to check hindi and english role 
						String HindiEnglishRole[] = RoleName.split(",");
						String hindesc = HindiEnglishRole[0];
						String EngDesc = HindiEnglishRole[1];
						String PoaName = null;
						if (EngDesc.contains("Authenticated")) {
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(Authenticated PoA Holder) ";
							} else {
								PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
							}
						} else {
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(PoA Holder) ";
							} else {
								PoaName = " द्वारा (मुख्‍त्‍यार आम)";
							}
						}
						System.out.println("POA NAMe is " + PoaName);
						System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
						// System.out.println("PoAOwnerlistdisplay"+poAOwnerList.get(i));
						/*	 String OwnerDetail =null;
						  if(i1<poAOwnerList.size())
						  {
							ArrayList m = (ArrayList) poAOwnerList.get(i1);
							mapDto.setOwnerPartyDesc(m.get(0).toString());
						 
							OwnerDetail =mapDto.getOwnerPartyDesc();
							System.out.println("Owner result from DB is "+OwnerDetail);
						   
						   String parts[] = OwnerDetail.split(",");
						   String Owner = parts[0];
						   String PoADesc =parts[1];
						 
							 OwnerRolePoA=Owner+PoaName+PoADesc;
							System.out.println("owner Role to display "+ OwnerRolePoA);
						  }*/
						String poAOwnerListNew = commonBd.getOwnerDetails(regInitId, partyId, language);
						String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
						OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
						rDTO.setFnameTrns(OwnerRolePoA);
						rDTO.setLnameTrns("");
					} else {//individual
						//minor check to be implemented.-- Guardian - Rahul
						String fName = (String) list1.get(1);
						int age = list1.get(10) != null ? Integer.parseInt((String) list1.get(10)) : 0;
						String relations = commonBO.getRelationshipName((list1.get(13).toString()), language);
						String Mname = "";
						if ((list1.get(15) != null) && (!list1.get(15).toString().equalsIgnoreCase("")) && (!list1.get(15).toString().equalsIgnoreCase("null"))) {
							Mname = (String) list1.get(15);
						}
						if (Mname == null || "null".equalsIgnoreCase(Mname)) {
							rDTO.setFnameTrns(fName);
							rDTO.setLnameTrns((String) list1.get(2));
						} else {
							rDTO.setFnameTrns(fName + " " + Mname);
							rDTO.setLnameTrns((String) list1.get(2));
						}
						if (age < RegCompCheckerConstant.MINOR_AGE_LIMIT) {
							String MinorName = rDTO.getFnameTrns();
							//rDTO.setFnameTrns("(Minor) "+MinorName);
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								rDTO.setFnameTrns("(Minor) " + MinorName);
								rDTO.setLnameTrns((String) list1.get(2) + " " + relations + " " + (String) list1.get(11) + " By " + (String) list1.get(12));
							} else {
								rDTO.setFnameTrns("(अवयस्‍क) " + MinorName);
								rDTO.setLnameTrns((String) list1.get(2) + " " + relations + " " + (String) list1.get(11) + " द्वारा  " + (String) list1.get(12));
							}
						}
						/*else{
						
						rDTO.setFnameTrns(fName);
						rDTO.setLnameTrns((String)list1.get(2));
						}*/
					}
				}
				// end of POA - owner desc - rahul
				else {
					// first name , middle  name
					//logger.debug((String)list1.get(1));
					String fName = (String) list1.get(1);
					fName = fName == null ? "" : fName;
					if ("".equals(fName)) {
						if (list1.get(8) != null && list1.get(8).toString().equalsIgnoreCase("3")) {
							if (list1.get(9) != null && !list1.get(9).toString().equalsIgnoreCase("")) {
								rDTO.setFnameTrns((String) list1.get(9));
							} else {
								rDTO.setFnameTrns("--");
							}
							rDTO.setLnameTrns("");
						} else {
							if (list1.get(8) != null && list1.get(8).toString().equalsIgnoreCase("1")) {
								if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6) {
									poAOwnerListForOrg = commonBd.getOwnerPoaDescDisplayForOrg(language, regInitId);
									mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
									// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
									System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
									form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
									String RoleName = form.getExecutantClaimantName();
									System.out.println("Role Name " + RoleName);
									String PoaName = null;
									// to check hindi and english role 
									String HindiEnglishRole[] = RoleName.split(",");
									String hindesc = HindiEnglishRole[0];
									String EngDesc = HindiEnglishRole[1];
									if (EngDesc.contains("Authenticated")) {
										if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											PoaName = " By(Authenticated PoA Holder) ";
										} else {
											PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
										}
									} else {
										if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											PoaName = " By(PoA Holder) ";
										} else {
											PoaName = " द्वारा (मुख्‍त्‍यार आम)";
										}
									}
									/*	 String OwnerDetail =null;
										 
										 if(i1<poAOwnerListForOrg.size())
										 {
										 ArrayList n = (ArrayList) poAOwnerListForOrg.get(i1);
											mapDto.setOwnerPartyDescForOrg(n.get(0).toString());
										 
											OwnerDetail =mapDto.getOwnerPartyDescForOrg();
										 System.out.println("Owner result from DB is "+OwnerDetail);
										   
										   String parts[] = OwnerDetail.split(",");
										   String Owner = parts[0];
										   String PoADesc =parts[1];
										 
										  OwnerRolePoA=Owner+PoaName+PoADesc;
											System.out.println("owner Role to display "+ OwnerRolePoA);
										 }*/
									String poAOwnerListNew = commonBd.getOwnerDetails(regInitId, partyId, language);
									String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
									OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
									rDTO.setFnameTrns(OwnerRolePoA);
									rDTO.setLnameTrns("");
								} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
									mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
									poAOwnerListForOrg = commonBd.getOwnerPoaDescDisplayForOrg(language, regInitId);
									String RoleName = null;
									rDTO.setRoleName((String) list1.get(3));
									// rDTO.setPartyRole(getPartyRole((String) subList.get(3),language));
									rDTO.setPartyRoleFullNamePOA(commonBO.getRoleNameForPOA((String) list1.get(5)));
									RoleName = rDTO.getPartyRoleFullNamePOA();
									System.out.println("Role Name " + RoleName);
									// to check hindi and english role 
									String HindiEnglishRole[] = RoleName.split(",");
									String hindesc = HindiEnglishRole[0];
									String EngDesc = HindiEnglishRole[1];
									String PoaName = null;
									if (EngDesc.contains("Authenticated")) {
										if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											PoaName = " By(Authenticated PoA Holder) ";
										} else {
											PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
										}
									} else {
										if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											PoaName = " By(PoA Holder) ";
										} else {
											PoaName = " द्वारा (मुख्‍त्‍यार आम)";
										}
									}
									System.out.println("POA NAMe is " + PoaName);
									System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
									//  System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
									/*		 String OwnerDetail =null;
										  if(i1<poAOwnerListForOrg.size())
										  {
											ArrayList m = (ArrayList) poAOwnerListForOrg.get(i1);
											mapDto.setOwnerPartyDescForOrg(m.get(0).toString());
										 
											OwnerDetail =mapDto.getOwnerPartyDescForOrg();
											System.out.println("Owner result from DB is "+OwnerDetail);
										   
										   String parts[] = OwnerDetail.split(",");
										   String Owner = parts[0];
										   String PoADesc =parts[1];
										 
										  OwnerRolePoA=Owner+PoaName+PoADesc;
											System.out.println("owner Role to display "+ OwnerRolePoA);
										  }*/
									String poAOwnerListNew = commonBd.getOwnerDetails(regInitId, partyId, language);
									String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
									OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
									rDTO.setFnameTrns(OwnerRolePoA);
									rDTO.setLnameTrns("");
								} else {
									rDTO.setFnameTrns((String) list1.get(4));
									rDTO.setLnameTrns("");
								}
							}
						}
					}
					/*else
					{//individual
					 //minor check to be implemented.-- Guardian - Rahul
						
						int age=list1.get(10)!=null?Integer.parseInt((String)list1.get(10)):0;
						String Mname=(String)list1.get(12);
						if(Mname ==null || "null".equalsIgnoreCase(Mname)){
							rDTO.setFnameTrns(fName);
							rDTO.setLnameTrns((String)list1.get(2));
						}
						else {
							rDTO.setFnameTrns(fName+" "+(String)list1.get(12));
							rDTO.setLnameTrns((String)list1.get(2));
						}
						if(age<RegCompCheckerConstant.MINOR_AGE_LIMIT){
							
							String MinorName =rDTO.getFnameTrns();
							//rDTO.setFnameTrns("(Minor) "+MinorName);
							if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
						  {
								rDTO.setFnameTrns("(Minor) "+MinorName);
							   rDTO.setLnameTrns((String)list1.get(2)+"  "+(String)list1.get(14)+" "+(String)list1.get(11)+" By "+(String)list1.get(13));
						  }
							else 
							{
								rDTO.setFnameTrns("(अवयस्‍क) "+MinorName);
								rDTO.setLnameTrns((String)list1.get(2)+"  "+(String)list1.get(16)+" "+(String)list1.get(11)+" द्वारा "+(String)list1.get(13));
							}
							
							
						}
						else{
						
						rDTO.setFnameTrns(fName);
						rDTO.setLnameTrns((String)list1.get(2));
						}
						
						}*/
				} // minor check finish - Rahul
				rDTO.setPartyTxnId((String) list1.get(0));
				//rDTO.setFnameTrns((String)list1.get(1));
				//rDTO.setLnameTrns((String)list1.get(2));
				//rDTO.setGovtOfcCheck((String)list1.get(7));
				if (list1.get(8) != null && list1.get(8).toString().equalsIgnoreCase("3")) {
					rDTO.setGovtOfcCheck("Y");
				} else {
					rDTO.setGovtOfcCheck("N");
				}
				String partyRoleId = (String) list1.get(5);
				if (partyRoleId.equals("0"))
					rDTO.setRoleName((String) list1.get(6));
				else
					rDTO.setRoleName((String) list1.get(3));
				rDTO.setIsGovtOfficial("");
				rDTO.setGovtOfficial("");
				rDTO.setReprsnName("");
				for (int j = 0; j < list2.size(); j++) {
					list3 = (ArrayList) list2.get(j);
					if (((String) list3.get(0)).equals(rDTO.getPartyTxnId())) {
						rDTO.setStatus("Y");
						break;
					} else {
						rDTO.setStatus("N");
					}
				}
				rDTO.setPartyThumbChk("N");
				rDTO.setPartySignChk("N");
				sql = RegCompCheckerSQL.GET_ALL_PATH_PARTY;
				System.out.println(sql);
				dbUtility.createPreparedStatement(sql);
				ArrayList mainPhotoList = new ArrayList();
				String aar1[] = new String[1];
				aar1[0] = rDTO.getPartyTxnId();
				mainPhotoList = dbUtility.executeQuery(aar1);
				if (mainPhotoList != null && mainPhotoList.size() > 0) {
					ArrayList subList1 = (ArrayList) mainPhotoList.get(0);
					if (subList1.get(2) != null && !subList1.get(2).toString().equalsIgnoreCase("")) {
						rDTO.setPartySignChk("Y");
						rDTO.setSignatureName(subList1.get(2).toString());
						rDTO.setSignaturePath(subList1.get(3).toString());
					}
					if (subList1.get(4) != null && !subList1.get(4).toString().equalsIgnoreCase("")) {
						rDTO.setPartyThumbChk("Y");
						rDTO.setFingerPrintName(subList1.get(4).toString());
						rDTO.setFingerPrintPath(subList1.get(5).toString());
					}
					if (subList1.get(6) != null && !subList1.get(6).toString().equalsIgnoreCase("")) {
						//rDTO.setGovtOffLttrName(subList1.get(6).toString());
						//rDTO.setGovtOffLttrPath(subList1.get(7).toString());
					}
				}
				partyPresentDetails.add(rDTO);
			}
			dbUtility.closeConnection();
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.debug("Exception in getPartiesPresentDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of witness List in DAO" + list.size());
		return partyPresentDetails;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public String getExecutionDate(String regInitId) throws Exception {
		String execDate = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.EXECUTION_DATE+"'"+regInitId+"'";
			execDate = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.EXECUTION_DATE;
			dbUtility.createPreparedStatement(query);
			execDate = dbUtility.executeQry(new String[] { regInitId });
			dbUtility.closeConnection();
		} catch (Exception exception) {
			logger.debug("Exception in View Witness" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		return execDate;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getComplianceDetails(String[] regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = RegCompCheckerSQL.VIEW_COMPLIANCE_LIST;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(regInitId);
		} catch (Exception exception) {
			logger.debug("Exception in View Compliance" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of Compliance List in DAO" + list.size());
		return list;
	}

	public ArrayList getPartyDet(String regNumber) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql = RegCompCheckerSQL.GET_PARTY_DETAILS+"'"+regNumber+"'";
			dbUtility.createPreparedStatement(sql);*/
			sql = RegCompCheckerSQL.GET_PARTY_DETAILS_new;
			dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			//list = dbUtility.executeQuery(sql);
			//System.out.println(list.size());
			list = dbUtility.executeQuery(new String[] { regNumber });
			logger.error("");
		} catch (Exception e) {
			logger.error("" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public ArrayList getWitnessDet(String regNumber) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql = RegCompCheckerSQL.GET_WITNESS_DETAILS_FOR_CHECKER_COMPLIANCE+"'"+regNumber+"'";*/
			//dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			//list = dbUtility.executeQuery(sql);
			//System.out.println(list.size());
			sql = RegCompCheckerSQL.GET_WITNESS_DETAILS_FOR_CHECKER_COMPLIANCE;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(new String[] { regNumber });
		} catch (Exception e) {
			logger.error("" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection" + e.getStackTrace());
			}
		}
		return list;
	}

	public String getWitnessPhotoDet(String partyOtherId) throws Exception {
		String path = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql = RegCompCheckerSQL.GET_PHOTO_DETAILS+"'"+partyOtherId+"'";
			//dbUtility.createPreparedStatement(sql);
			System.out.println(sql);
			path = dbUtility.executeQry(sql);*/
			sql = RegCompCheckerSQL.GET_PHOTO_DETAILS;
			dbUtility.createPreparedStatement(sql);
			path = dbUtility.executeQry(new String[] { partyOtherId });
		} catch (Exception e) {
			logger.error("" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection" + e.getStackTrace());
			}
		}
		return path;
	}

	/**
	 * @param regInit
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getLinkdRegNumber(String regInit) throws Exception {
		ArrayList linkedRegNumber = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql = RegCommonMkrSQL.GET_LINKED_REG_NUMBER + "'" + regInit + "'";
			linkedRegNumber = dbUtility.executeQuery(sql);*/
			sql = RegCommonMkrSQL.GET_LINKED_REG_NUMBER;
			dbUtility.createPreparedStatement(sql);
			linkedRegNumber = dbUtility.executeQuery(new String[] { regInit });
		} catch (Exception e) {
			logger.error("" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection" + e.getStackTrace());
			}
		}
		return linkedRegNumber;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCaveatDet(String regNumber, ArrayList regList) throws Exception {
		// TODO Auto-generated method stub
		mainList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList sublist = new ArrayList();
		ArrayList subregList = new ArrayList();
		ArrayList propList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if (regList.size() != 0) {
				for (int k = 0; k < regList.size(); k++) {
					subregList = (ArrayList) regList.get(k);
					String num = (String) subregList.get(0);
					String propId = (String) subregList.get(1);
					if (propId.length() == 15)
						propId = "0" + propId;
					String arr[] = { num, propId };
					sql = RegCompCheckerSQL.GET_CAVEAT_DETAILS;
					dbUtility.createPreparedStatement(sql);
					list = dbUtility.executeQuery(arr);
					if (list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							sublist = (ArrayList) list.get(i);
							RegCompCheckerDTO dto = new RegCompCheckerDTO();
							dto.setTransactionId((String) sublist.get(0));
							String caveatid = (String) sublist.get(1);
							dbUtility.createPreparedStatement(RegCompCheckerSQL.GET_CAVEAT_NAME);
							/*String SQL = RegCompCheckerSQL.GET_CAVEAT_NAME + "'"
									+ caveatid + "'";*/
							String caveatname = dbUtility.executeQry(new String[] { caveatid });
							dto.setCaveatName(caveatname);
							dto.setStayOrdrNum((String) sublist.get(2));
							dto.setStayOrdrDet((String) sublist.get(3));
							dto.setCaveatDet((String) sublist.get(4));
							dto.setRegNumLocked((String) sublist.get(5));
							dto.setCaveatStatus((String) sublist.get(6));
							dto.setStayOrdrStrtDate((String) sublist.get(7));
							dto.setStayOrdrUptoDate((String) sublist.get(8));
							dto.setPropTxnLock((String) sublist.get(9));
							dto.setPropertyId((String) sublist.get(10));
							//creating error this codeto be uncommented later
							/*if (((String) sublist.get(9))
									.equalsIgnoreCase("yes")) {
								propList = getPropIdLockedByCaveat((String) sublist
										.get(0));
								dto.setPropIDLocked(propList);
							}*/
							mainList.add(dto);
						}
						logger.debug("<------size of arraylist with caveat details" + mainList.size());
					}
				}
			}
		} catch (Exception e) {
			logger.error("error in getCaveatDet" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getCaveatDet" + e.getStackTrace());
			}
		}
		return mainList;
	}

	public ArrayList getCaveatDetChecklist(String regNumber, ArrayList regList) throws Exception {
		// TODO Auto-generated method stub
		mainList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList sublist = new ArrayList();
		ArrayList subregList = new ArrayList();
		ArrayList propList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if (regList.size() != 0) {
				for (int k = 0; k < regList.size(); k++) {
					subregList = (ArrayList) regList.get(k);
					String num = (String) subregList.get(0);
					String propId = (String) subregList.get(1);
					if (propId.length() == 15)
						propId = "0" + propId;
					String arr[] = { num, propId };
					sql = RegCompCheckerSQL.GET_CAVEAT_DETAILS_CHECKLIST;
					dbUtility.createPreparedStatement(sql);
					list = dbUtility.executeQuery(arr);
					if (list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							sublist = (ArrayList) list.get(i);
							RegCompCheckerDTO dto = new RegCompCheckerDTO();
							dto.setTransactionId((String) sublist.get(0));
							String caveatid = (String) sublist.get(1);
							dbUtility.createPreparedStatement(RegCompCheckerSQL.GET_CAVEAT_NAME);
							/*String SQL = RegCompCheckerSQL.GET_CAVEAT_NAME + "'"
									+ caveatid + "'";*/
							String caveatname = dbUtility.executeQry(new String[] { caveatid });
							dto.setCaveatName(caveatname);
							dto.setStayOrdrNum((String) sublist.get(2));
							dto.setStayOrdrDet((String) sublist.get(3));
							dto.setCaveatDet((String) sublist.get(4));
							dto.setRegNumLocked((String) sublist.get(5));
							dto.setCaveatStatus((String) sublist.get(6));
							dto.setStayOrdrStrtDate((String) sublist.get(7));
							dto.setStayOrdrUptoDate((String) sublist.get(8));
							dto.setPropTxnLock((String) sublist.get(9));
							dto.setPropertyId((String) sublist.get(10));
							//creating error this codeto be uncommented later
							/*if (((String) sublist.get(9))
									.equalsIgnoreCase("yes")) {
								propList = getPropIdLockedByCaveat((String) sublist
										.get(0));
								dto.setPropIDLocked(propList);
							}*/
							mainList.add(dto);
						}
						logger.debug("<------size of arraylist with caveat details" + mainList.size());
					}
				}
			}
		} catch (Exception e) {
			logger.error("error in getCaveatDet" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getCaveatDet" + e.getStackTrace());
			}
		}
		return mainList;
	}

	public ArrayList getBnkCaveatDet(String regNumber, ArrayList regList) throws Exception {
		// TODO Auto-generated method stub
		mainList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList sublist = new ArrayList();
		ArrayList subregList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if (regList.size() != 0) {
				for (int k = 0; k < regList.size(); k++) {
					subregList = (ArrayList) regList.get(k);
					String num = (String) subregList.get(0);
					String propId = (String) subregList.get(1);
					String arr[] = { num, propId };
					if (propId.length() == 15)
						propId = "0" + propId;
					sql = RegCompCheckerSQL.GET_BANK_CAVEAT_DETAILS;
					dbUtility.createPreparedStatement(sql);
					list = dbUtility.executeQuery(arr);
					if (list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							sublist = (ArrayList) list.get(i);
							RegCompCheckerDTO dto = new RegCompCheckerDTO();
							dto.setCvtBnkTxnId((String) sublist.get(0));
							dto.setOttId((String) sublist.get(1));
							dto.setLoanAcntNum((String) sublist.get(2));
							dto.setBnkName((String) sublist.get(3));
							dto.setRegId((String) sublist.get(4));
							dto.setPropertyId((String) sublist.get(5));
							mainList.add(dto);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("error in getBnkCaveatDet" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection" + e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * @param caveatID
	 * @return String
	 * @throws Exception
	 */
	public String getCaveatName(String caveatID) throws Exception {
		String caveatName = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_CAVEAT_NAME+"'"+caveatID+"'";
			caveatName = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_CAVEAT_NAME;
			dbUtility.createPreparedStatement(query);
			caveatName = dbUtility.executeQry(new String[] { caveatID });
		} catch (Exception exception) {
			logger.debug("Exception in getCaveatName" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		return caveatName;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCheckListDetails(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_CHECKLIST_DETAILS+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_CHECKLIST_DETAILS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getCheckListDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of checkList in DAO" + list.size());
		return list;
	}

	/**
	 * @param checkTxnId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCheckListDeathUploadDetails(String checkTxnId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DEATH_UPLOAD_DETAILS+"'"+checkTxnId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_DEATH_UPLOAD_DETAILS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { checkTxnId });
		} catch (Exception exception) {
			logger.debug("Exception in getCheckListDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of checkList in DAO" + list.size());
		return list;
	}

	/**
	 * @param checkTxnId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCheckListPOADetails(String checkTxnId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_POA_DETAILS+"'"+checkTxnId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_POA_DETAILS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { checkTxnId });
		} catch (Exception exception) {
			logger.debug("Exception in getCheckListPOADetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of checkList in DAO" + list.size());
		return list;
	}

	/**
	 * @param regNum
	 * @param partyDetails
	 * @return
	 * @throws Exception
	 */
	public boolean modifyPartiesPresentDetails(String regNum, ArrayList partyDetails, Map govtOffLetterMap, String userID, String cdate) throws Exception {
		// TODO Auto-generated method stub
		boolean tmpFlag = false;
		String str = "";
		String date = getDate(cdate);
		boolean dataFlag = true;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			for (int i = 0; i < partyDetails.size(); i++) {
				Set mapSet = (Set) govtOffLetterMap.entrySet();
				Iterator mapIterator = mapSet.iterator();
				boolean flag1 = false;
				RegCompCheckerDTO rtDTO = (RegCompCheckerDTO) partyDetails.get(i);
				//		logger.debug("PARTY TXN ID"+rtDTO.getPartyTxnId());
				//		logger.debug("STATUS"+rtDTO.getStatus());
				String partyId1[] = { rtDTO.getPartyTxnId() };
				String query = RegCompCheckerSQL.GET_PATY_OTHER_ID;
				dbUtility.createPreparedStatement(query);
				String seqNum = dbUtility.executeQry(partyId1);
				logger.debug("partyId form party detls----->" + seqNum);
				if (seqNum == null || seqNum == "") {
					dataFlag = false;
					query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
					dbUtility.createStatement();
					seqNum = dbUtility.executeQry(query);
				}
				//		logger.debug("<------SEQ NUMBER IN DAO"+seqNum);
				while (mapIterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) mapIterator.next();
					RegCompCheckerDTO rDTO = (RegCompCheckerDTO) mapEntry.getValue();
					String partyId = (String) mapEntry.getKey();
					if (partyId.equalsIgnoreCase(rtDTO.getPartyTxnId())) {
						logger.debug("PARTY ID MATCH IN MAP AND LIST ");
						flag1 = true;
						if (dataFlag) {
							String arr[] = { "Y", // is govt official
							rtDTO.getGovtOfficial(), rtDTO.getReprsnName(), RegCompCheckerConstant.FILE_NAME_GOVT_LETTER, rDTO.getGovtOffLetterPath(), userID, date, rtDTO.getSignatureName(), rtDTO.getSignaturePath(), rtDTO.getFingerPrintName(), rtDTO.getFingerPrintPath(),
							//"signature.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE+partyId+"/signature.GIF",
							//"FingerPrint.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB+partyId+"/FingerPrint.GIF",
							rtDTO.getThumbRemarks(),
							//"Photo.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO+partyId,
							seqNum };
							query = RegCompCheckerSQL.UPDATE_PARTY_OTHER_DETLS_new;
							dbUtility.createPreparedStatement(query);
							boolean flag = dbUtility.executeUpdate(arr);
						} else {
							String arr[] = { seqNum, "Y", // is govt official
							rtDTO.getGovtOfficial(), rtDTO.getReprsnName(), RegCompCheckerConstant.FILE_NAME_GOVT_LETTER, rDTO.getGovtOffLetterPath(), userID, date, rtDTO.getSignatureName(), rtDTO.getSignaturePath(), rtDTO.getFingerPrintName(), rtDTO.getFingerPrintPath(),
							//"signature.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE+partyId+"/signature.GIF",
							//"FingerPrint.GIF",
							///RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB+partyId+"/FingerPrint.GIF",
							rtDTO.getThumbRemarks(),
							//"Photo.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO+partyId,
							};
							query = RegCompCheckerSQL.INSERT_PARTY_OTHER_DETLS_new;
							dbUtility.createPreparedStatement(query);
							boolean flag = dbUtility.executeUpdate(arr);
						}
						if (rtDTO.getStatus().equals("Y")) {
							sql = RegCompCheckerSQL.UPDATE_PARTY_PRESENT_1;
							dbUtility.createPreparedStatement(sql);
							String[] partArr = {//rDTO.getIsGovtOfficial(),
							//rDTO.getGovtOfficial(),
							//rDTO.getReprsnName(),
							seqNum, userID, date, regNum, rtDTO.getPartyTxnId() };
							tmpFlag = dbUtility.executeUpdate(partArr);
						}
					}
				}
				if (!flag1) {
					logger.debug("status---->" + rtDTO.getStatus());
					if (dataFlag) {
						if (rtDTO.getStatus().equals("Y")) {
							String arr[] = { userID, rtDTO.getSignatureName(), rtDTO.getSignaturePath(), rtDTO.getFingerPrintName(), rtDTO.getFingerPrintPath(),
							//"signature.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE+rtDTO.getPartyTxnId()+"/signature.GIF",
							//"FingerPrint.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB+rtDTO.getPartyTxnId()+"/FingerPrint.GIF",
							//"Photo.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO+rtDTO.getPartyTxnId(),
							rtDTO.getThumbRemarks(), seqNum };
							query = RegCompCheckerSQL.UPDATE_PARTY_OTHER_DETLS_NEW;
							dbUtility.createPreparedStatement(query);
							boolean flag = dbUtility.executeUpdate(arr);
						}
					} else {
						if (rtDTO.getStatus().equals("Y")) {
							String arr[] = { seqNum, userID, rtDTO.getSignatureName(), rtDTO.getSignaturePath(), rtDTO.getFingerPrintName(), rtDTO.getFingerPrintPath(),
							//"signature.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE+rtDTO.getPartyTxnId()+"/signature.GIF",
							//"FingerPrint.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB+rtDTO.getPartyTxnId()+"/FingerPrint.GIF",
							rtDTO.getThumbRemarks(),
							//"Photo.GIF",
							//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO+rtDTO.getPartyTxnId(),
							};
							query = RegCompCheckerSQL.INSERT_PARTY_OTHER_DETLS_NEW;
							dbUtility.createPreparedStatement(query);
							boolean flag = dbUtility.executeUpdate(arr);
						}
						sql = RegCompCheckerSQL.UPDATE_PARTY_PRESENT_1;
						dbUtility.createPreparedStatement(sql);
						String[] partArr = { seqNum, userID, date, regNum, rtDTO.getPartyTxnId() };
						tmpFlag = dbUtility.executeUpdate(partArr);
					}
				}
			}
			/*else
			{
				for(int i= 0;i<partyDetails.size();i++)
				{
					RegCompCheckerDTO rDTO = (RegCompCheckerDTO)partyDetails.get(i);
					logger.debug("PARTY TXN ID"+rDTO.getPartyTxnId());
					logger.debug("STATUS"+rDTO.getStatus());
					if(rDTO.getStatus().equals("N"))
					{
						//sql = RegCompCheckerSQL.UPDATE_PARTY_DETAILS;  // commented by SIMRAN--11 june
						//dbUtility.createPreparedStatement(sql);
						//String[] partArr= {regNum,
						//		rDTO.getPartyTxnId()};
						//tmpFlag = dbUtility.executeUpdate(partArr);
					}
					else
					{
						String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
						dbUtility.createStatement();
						String seqNum = dbUtility.executeQry(query);
						logger.debug("<------SEQ NUMBER IN DAO"+seqNum);
						
							String arr[] = {seqNum,
									userID,
									"signature.GIF",
									RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE+rDTO.getPartyTxnId(),
									"FingerPrint.GIF",
									RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB+rDTO.getPartyTxnId(),
									"Photo.GIF",
									RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO+rDTO.getPartyTxnId(),
							};
							query = RegCompCheckerSQL.INSERT_PARTY_OTHER_DETLS_2;
							dbUtility.createPreparedStatement(query);
							boolean flag = dbUtility.executeUpdate(arr);
						
							sql = RegCompCheckerSQL.UPDATE_PARTY_OTHER_DETLS;
							dbUtility.createPreparedStatement(sql);
							String[] partArr = {
									seqNum,
									userID,
									regNum,
									rDTO.getPartyTxnId()
											
							};
							tmpFlag = dbUtility.executeUpdate(partArr);
					}
					
				}
			}*/
			/*for(int i= 0;i<partyDetails.size();i++)
			{
				RegCompCheckerDTO rDTO = (RegCompCheckerDTO)partyDetails.get(i);
				logger.debug("PARTY TXN ID"+rDTO.getPartyTxnId());
				logger.debug("STATUS"+rDTO.getStatus());
				if(rDTO.getStatus().equals("N"))
				{
					//sql = RegCompCheckerSQL.UPDATE_PARTY_DETAILS;  // commented by SIMRAN--11 june
					//dbUtility.createPreparedStatement(sql);
					//String[] partArr= {regNum,
					//		rDTO.getPartyTxnId()};
					//tmpFlag = dbUtility.executeUpdate(partArr);
				}
				else
				{
					sql = RegCompCheckerSQL.UPDATE_PARTY_PRESENT;
					dbUtility.createPreparedStatement(sql);
					String[] partArr = {//rDTO.getIsGovtOfficial(),
							//rDTO.getGovtOfficial(),
							//rDTO.getReprsnName(),
							userID,
							date,
							regNum,
							rDTO.getPartyTxnId()		
					};
					tmpFlag = dbUtility.executeUpdate(partArr);
				}
				
			}*/
		} catch (Exception e) {
			System.out.println("error in regCompMkrDAO : saveHoldData" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection RegCompMkrDAO : saveHoldData " + e.getStackTrace());
			}
		}
		return tmpFlag;
	}

	/**
	 * @param checkListDetails
	 * @return
	 * @throws Exception
	 */
	/*public boolean modifyCheckListDetials(String[] checkListDetails) throws Exception {
	// TODO Auto-generated method stub
		boolean tmpFlag = false;
		String str = "";
		try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		sql = RegCompCheckerSQL.UPDATE_CHECKLIST;
		dbUtility.createPreparedStatement(sql);
		tmpFlag = dbUtility.executeUpdate(checkListDetails);
		
		} catch (Exception e) {
		System.out.println("error in regCompMkrDAO : saveHoldData"
				+ e.getStackTrace());
		} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			System.out
					.println("error in closing connection RegCompMkrDAO : saveHoldData "
							+ e.getStackTrace());
		}
		}
		return tmpFlag;
	}*/
	/**
	 * @param regInitId
	 * @param userId
	 * @param cdate
	 * @return
	 * @throws Exception
	 */
	public boolean checkListData(String regInitId, String userId, String cdate) throws Exception {
		String checkList = "";
		boolean flag = false;
		String cDate = getDate(cdate);
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql =RegCommonMkrSQL.CHECK_CHECKLIST+"'"+regInitId+"'";
			
			logger.debug(sql);
			checkList = dbUtility.executeQry(sql);*/
			sql = RegCommonMkrSQL.CHECK_CHECKLIST;
			dbUtility.createPreparedStatement(sql);
			checkList = dbUtility.executeQry(new String[] { regInitId });
			if (!checkList.equals("")) {
				String arr[] = new String[3];
				arr[0] = userId;
				arr[1] = cDate;
				arr[2] = checkList;
				dbUtility.createPreparedStatement(RegCommonMkrSQL.DEL_CHECKLIST_POA);
				flag = dbUtility.executeUpdate(arr);
				//if(flag)
				//{
				dbUtility.createPreparedStatement(RegCommonMkrSQL.DEL_CHECKLIST_DTH);
				flag = dbUtility.executeUpdate(arr);
				//if(flag)
				//{
				arr[2] = regInitId;
				dbUtility.createPreparedStatement(RegCommonMkrSQL.DEL_CHECKLIST_MAIN);
				flag = dbUtility.executeUpdate(arr);
				//}
				//}
			} else {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in checkListData " + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection checkListData" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * @param dto2
	 * @param cdate
	 * @param userId
	 * @return String
	 * @throws Exception
	 */
	public String saveChecklist(RegCompCheckerDTO rDTO, String cdate, String userId) throws Exception {
		boolean flg = false;
		String dateOutInd = null;
		String orderDate = null;
		String aplDate = null;
		String comments = null;
		String authNo = null;
		String dateOutIndFlg;
		String orderFlg;
		String aplFlg;
		String deathFlg;
		String poaFlg;
		String poaMpFlg;
		String number = "";
		String checkListArr[] = new String[13];
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String SQL = RegCommonMkrSQL.GET_CHECKLIST_TXN_NUM;
			dbUtility.createStatement();
			number = dbUtility.executeQry(SQL);
			dbUtility.closeConnection();
			if (rDTO.getExecOutIndiaFlag().equals("Y")) {
				dateOutInd = rDTO.getDeedOutIndDate();
				dateOutIndFlg = "Y";
				//		logger.debug("<----"+dateOutInd);
			} else {
				dateOutInd = "";
				dateOutIndFlg = "";
			}
			if (rDTO.getCourtOrderFlag().equals("Y")) {
				orderDate = rDTO.getOrderDate();
				orderFlg = "Y";
				//		logger.debug("<---"+orderDate);
			} else {
				orderDate = "";
				orderFlg = "";
			}
			if (rDTO.getCourtAplFlag().equals("Y")) {
				orderDate = rDTO.getOrderDate();
				aplDate = rDTO.getLastAplDate();
				aplFlg = "Y";
			} else {
				aplDate = "";
				aplFlg = "";
			}
			if (rDTO.getDocAftrDeathFlg() != null && rDTO.getDocAftrDeathFlg().equals("Y")) {
				comments = rDTO.getComments();
				deathFlg = "Y";
			} else {
				comments = "";
				deathFlg = "";
			}
			if (rDTO.getPoaFlag() != null && rDTO.getPoaFlag().equals("Y")) {
				//if(rDTO.getPoaMpFlag().equals("Y"))
				//{
				//poaMpFlg = "Y";	
				//}
				//else
				//{
				//poaMpFlg = "";
				//}
				poaFlg = "Y";
				//authNo = rDTO.getPoaAuthNo();
			} else {
				poaFlg = "";
				poaMpFlg = "";
				authNo = "";
			}
			checkListArr[0] = number;
			checkListArr[1] = rDTO.getRegInitNumber();
			checkListArr[2] = rDTO.getExecDate();
			checkListArr[3] = dateOutIndFlg;
			checkListArr[4] = orderFlg;
			checkListArr[5] = orderDate;
			checkListArr[6] = aplFlg;
			checkListArr[7] = deathFlg;
			checkListArr[8] = poaFlg;
			checkListArr[9] = aplDate;
			checkListArr[10] = userId;
			checkListArr[11] = cdate;
			checkListArr[12] = dateOutInd;
			dbUtility = new DBUtility();
			sql = RegCommonMkrSQL.INSERT_INTO_CHECKLIST;
			dbUtility.createPreparedStatement(sql);
			flg = dbUtility.executeUpdate(checkListArr);
			if (!flg) {
				number = "";
			}
		} catch (Exception e) {
			logger.error("error in saveCheckList" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection saveChecklist" + e.getStackTrace());
			}
		}
		logger.debug("checklist number in DAO" + number);
		return number;
	}

	/**
	 * @param dto2
	 * @param cdate
	 * @param userId
	 * @param num
	 * @param deathCertList
	 * @return
	 */
	public boolean saveDthCertDetails(RegCompCheckerDTO dto2, String cdate, String userId, String num, Map deathCertList) {
		boolean insertData = false;
		String cDate = getDate(cdate);
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			Set mapSet = (Set) deathCertList.entrySet();
			Iterator mapIterator = mapSet.iterator();
			while (mapIterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) mapIterator.next();
				ArrayList valueList = (ArrayList) mapEntry.getValue();
				RegCompCheckerDTO rDTO = (RegCompCheckerDTO) valueList.get(0);
				String partyId = (String) mapEntry.getKey();
				if (rDTO.getComments().equals("-"))
					rDTO.setComments("");
				String arr[] = { num, RegCompConstant.FILE_NAME_DTH_CERTIFICATE, rDTO.getFilePath(), RegCompConstant.FILE_NAME_RELTN_PROOF, rDTO.getFilePathRltn(), rDTO.getComments(), userId, cDate, partyId };
				dbUtility.createPreparedStatement(RegCommonMkrSQL.INSERT_DEATH_CERT_DETAILS);
				insertData = dbUtility.executeUpdate(arr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in saveDathCertDetails of ragcompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection saveDathCertDetails of ragcompMkrDAO" + e.getStackTrace());
			}
		}
		return insertData;
	}

	/**
	 * @param dto2
	 * @param cdate
	 * @param userId
	 * @param num
	 * @param poaList
	 * @return
	 */
	public boolean savePOADetails(RegCompCheckerDTO dto2, String cdate, String userId, String num, Map poaList) {
		boolean insertData = false;
		String cDate = getDate(cdate);
		String poaMpFlg = "N";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			Set mapSet = (Set) poaList.entrySet();
			Iterator mapIterator = mapSet.iterator();
			while (mapIterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) mapIterator.next();
				ArrayList valueList = (ArrayList) mapEntry.getValue();
				RegCompCheckerDTO rDTO = (RegCompCheckerDTO) valueList.get(0);
				//String partyId =  (String)mapEntry.getKey();
				//if(rDTO.getPoaFlg().equalsIgnoreCase(RegCompConstant.POA_FROM_MP))
				//{
				poaMpFlg = rDTO.getPoaMpFlg();
				//}
				String arr[] = { num, poaMpFlg, rDTO.getPoaAuthNo(), RegCompConstant.FILE_NAME_POA_DOC, rDTO.getFilePathPOA(), userId, cDate };
				dbUtility.createPreparedStatement(RegCommonMkrSQL.INSERT_POA_DETAILS);
				insertData = dbUtility.executeUpdate(arr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in savePOADetails of ragcompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection savePOADetails of ragcompMkrDAO" + e.getStackTrace());
			}
		}
		return insertData;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public String getPaidDutiesduringMaker(String regInitId) throws Exception {
		String amt = null;
		String[] pArr = { regInitId, "P" };
		logger.debug("arr" + pArr.length);
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = RegCompCheckerSQL.PAID_AMT_MAKER;
			dbUtility.createPreparedStatement(query);
			amt = dbUtility.executeQry(pArr);
		} catch (Exception exception) {
			logger.debug("Exception in getPaidAmtMaker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		return amt;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public Double getDutiesRegInit(String regInitId) throws Exception {
		Double dutyPaid = 0.0;
		ArrayList paymentRecords = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.DUTIES_AT_REG_INIT+"'"+regInitId+"'";
			paymentRecords = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.DUTIES_AT_REG_INIT;
			dbUtility.createPreparedStatement(query);
			paymentRecords = dbUtility.executeQuery(new String[] { regInitId });
			if (paymentRecords != null) {
				for (int i = 0; i < paymentRecords.size(); i++) {
					ArrayList list1 = (ArrayList) paymentRecords.get(i);
					if (list1.get(0) != null && !list1.get(0).toString().equalsIgnoreCase("")) {
						dutyPaid = dutyPaid + Double.parseDouble((String) list1.get(0));
					}
				}
			}
			dbUtility.closeConnection();
		} catch (Exception exception) {
			logger.debug("Exception in getDutiesRegInit" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		return dutyPaid;
	}

	public Double getRegFeeRegInit(String regInitId) throws Exception {
		Double regFeePaid = 0.0;
		ArrayList paymentRecords = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.DUTIES_AT_REG_INIT+"'"+regInitId+"'";
			paymentRecords = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.REG_AT_REG_INIT;
			dbUtility.createPreparedStatement(query);
			paymentRecords = dbUtility.executeQuery(new String[] { regInitId });
			if (paymentRecords != null) {
				for (int i = 0; i < paymentRecords.size(); i++) {
					ArrayList list1 = (ArrayList) paymentRecords.get(i);
					if (list1.get(0) != null && !list1.get(0).toString().equalsIgnoreCase("")) {
						regFeePaid = regFeePaid + Double.parseDouble((String) list1.get(0));
					}
				}
			}
			dbUtility.closeConnection();
		} catch (Exception exception) {
			logger.debug("Exception in getDutiesRegInit" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		return regFeePaid;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getLinkingDutiesChecker(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.DUTIES_AT_CHECKER_END+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.DUTIES_AT_CHECKER_END;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
			dbUtility.closeConnection();
		} catch (Exception exception) {
			logger.debug("Exception in getLinkingDutiesChecker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of duty List checker in DAO" + list.size());
		return list;
	}

	/**
	 * @param adj
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLinkingDutiesAdj(String adj) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.DUTIES_AT_CHCEKER_ADJ+"'"+adj+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.DUTIES_AT_CHCEKER_ADJ;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { adj });
		} catch (Exception exception) {
			logger.debug("Exception in getLinkingDutiesChecker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		logger.debug("Size of duty List checker in DAO" + list.size());
		return list;
	}

	/**
	 * @param regNumber
	 * @param valueOf
	 * @param userId
	 * @param cdate
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public boolean insertPaymentDetails(String regNumber, String valueOf, String userId, String cdate, String tableName, String sourceMod, String pmtType, RegCompCheckerDTO rDTO) throws Exception {
		boolean boo = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			//if(tableName.equalsIgnoreCase("IGRS_REGCOMP_CHKR_TXN_DETLS"))
			//{
			//sql = "INSERT INTO "+tableName+"(TRANSACTION_ID,ESTAMP_AMOUNT,PAYMENT_FLAG,CREATED_BY,CREATED_DATE) VALUES(?,?,?,?,?)";
			//}
			//else
			//{
			//}
			dbUtility.createStatement();
			String query = RegCompCheckerSQL.GET_PAYMENT_ID;
			String txnId = dbUtility.executeQry(query);
			//	logger.debug("Transaction Id------>"+txnId);
			rDTO.setPmtTxnId(txnId);
			sql = RegCompCheckerSQL.INSERT_INTO_PAYMENT_TABLE;
			dbUtility.createPreparedStatement(sql);
			String[] detls = new String[8];
			detls[0] = txnId;
			detls[1] = regNumber;
			detls[2] = valueOf;
			detls[3] = "I";//payment flag initiated
			detls[4] = sourceMod;
			detls[5] = pmtType;
			detls[6] = userId;
			detls[7] = getDate(cdate);
			boo = dbUtility.executeUpdate(detls);
			if (boo) {
				dbUtility.commit();
			} else
				dbUtility.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("insertPaymentDetails in dao" + e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * @param pkey
	 * @param userId
	 * @param cdate
	 * @param table
	 * @return
	 * @throws Exception
	 */
	public boolean updtStatus(String pkey, String userId, String cdate, String modFlag, String pymntType) throws Exception {
		boolean flg1 = false;
		boolean flg2 = false;
		boolean flg3 = false;
		String date = getDate(cdate);
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = RegCompCheckerSQL.UPDATE_PAYMENT_STATUS;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { userId, date, pkey, modFlag, pymntType };
			flg2 = dbUtility.executeUpdate(arr);
			if (flg2) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
		} catch (Exception e) {
			logger.error("error in updtStatus" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return false;
	}

	/**
	 * @param regInitId
	 * @return String
	 * @throws Exception
	 */
	public String getOfficeCodeChecker(String userId) throws Exception {
		String officeCode = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_OFFICE_ID+"'"+userId+"'";
			officeCode = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_OFFICE_ID;
			dbUtility.createPreparedStatement(query);
			officeCode = dbUtility.executeQry(new String[] { userId });
		} catch (Exception exception) {
			logger.debug("Exception in getOfficeCodeChecker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return officeCode;
	}

	public String getUsrId(String regNo) {
		String districtId = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DISTRICT_ID+"'"+officeId+"'";
			districtId = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_USER_COMP;
			dbUtility.createPreparedStatement(query);
			districtId = dbUtility.executeQry(new String[] { regNo });
		} catch (Exception exception) {
			logger.debug("Exception in getDistrictCodeChecker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return districtId;
	}

	public String getOfcId(String regNo) {
		String districtId = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DISTRICT_ID+"'"+officeId+"'";
			districtId = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_OFFICE_COMP;
			dbUtility.createPreparedStatement(query);
			districtId = dbUtility.executeQry(new String[] { regNo });
		} catch (Exception exception) {
			logger.debug("Exception in getDistrictCodeChecker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return districtId;
	}

	public String getPrintFlag(String regNo) {
		String districtId = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DISTRICT_ID+"'"+officeId+"'";
			districtId = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_PRINT_FLAG;
			dbUtility.createPreparedStatement(query);
			districtId = dbUtility.executeQry(new String[] { regNo });
		} catch (Exception exception) {
			logger.debug("Exception in getDistrictCodeChecker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return districtId;
	}

	/**
	 * @param officeId
	 * @return String
	 * @throws Exception
	 */
	public String getDistrictCodeChecker(String officeId) throws Exception {
		String districtId = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DISTRICT_ID+"'"+officeId+"'";
			districtId = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_DISTRICT_ID;
			dbUtility.createPreparedStatement(query);
			districtId = dbUtility.executeQry(new String[] { officeId });
		} catch (Exception exception) {
			logger.debug("Exception in getDistrictCodeChecker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return districtId;
	}

	/**
	 * @return String
	 * @throws Exception
	 */
	public ArrayList getBookDetails() throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = RegCompCheckerSQL.GET_BOOK_TYPE;
			list = dbUtility.executeQuery(query);
		} catch (Exception exception) {
			logger.debug("Exception in getBookDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @return String
	 * @throws Exception
	 */
	public String getSeqNum(boolean flag) throws Exception {
		String seqNum = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if (flag) {
				dbUtility.createStatement();
				String sql = RegCompCheckerSQL.DROP_SEQ_NUM;
				dbUtility.executeUpdate(sql);
				dbUtility.createStatement();
				String sql1 = RegCompCheckerSQL.CREATE_SEQ_NUM;
				dbUtility.executeUpdate(sql1);
			}
			dbUtility.createStatement();
			String query = RegCompCheckerSQL.GET_SEQ_NUM;
			seqNum = dbUtility.executeQry(query);
		} catch (Exception exception) {
			logger.debug("Exception in getSeqNum" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return seqNum;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getFinalLinkingDuties(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.FINAL_STAMP_REG_FEE+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.FINAL_STAMP_REG_FEE;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		logger.debug("Size of List in DAO" + list.size());
		return list;
	}

	/**
	 * method to check any hold on application number entered by user
	 * 
	 * @param regNumber
	 * @return String
	 * @throws Exception
	 */
	public String checkHold(String regNumber) throws Exception {
		String fwdPage = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql = "SELECT FORWARD_PAGE FROM IGRS_REG_CMPLTN_CHECKER_HOLD "
					+ "WHERE HLD_FLAG='Y' AND REG_TXN_ID='" + regNumber + "'";
			fwdPage = dbUtility.executeQry(sql);
			sql = 
				System.out.println(fwdPage);*/
			sql = RegCompCheckerSQL.GET_FORWARD_PAGE;
			dbUtility.createPreparedStatement(sql);
			fwdPage = dbUtility.executeQry(new String[] { regNumber });
		} catch (Exception e) {
			logger.debug("Exception in CheckHold method : regCompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug("Exception in dbUtility close : RegCompMkrDAO" + e.getStackTrace());
			}
		}
		return fwdPage;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getofficialList() throws Exception {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		mainList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			list = dbUtility.executeQuery(RegCommonMkrSQL.GET_GOVT_OFFICIAL_lIST);
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				RegCompCheckerDTO rdto = new RegCompCheckerDTO();
				rdto.setGovtOffId((String) subList.get(0));
				rdto.setGovtOffName((String) subList.get(1));
				mainList.add(rdto);
			}
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return mainList;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 */
	public ArrayList getHoldDetails(String regInitId) {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql = "SELECT REGISTRATION_TXN_STATUS FROM IGRS_REG_TXN_DETLS WHERE REG_TXN_ID ='"+regInitId+"'";
			String str1 = dbUtility.executeQry(sql);*/
			sql = RegCompCheckerSQL.GET_REGISTARTION_STATUS;
			dbUtility.createPreparedStatement(sql);
			String str1 = dbUtility.executeQry(new String[] { regInitId });
			if (str1.equals("14")) // hold at checker = 14
			{
				String query = RegCompCheckerSQL.CHECK_HOLD;
				dbUtility.createPreparedStatement(query);
				list = dbUtility.executeQuery(new String[] { regInitId });
			}
			dbUtility.closeConnection();
		} catch (Exception exception) {
			logger.debug("Exception in getHoldDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug("Exception in dbUtility close : RegCompMkrDAO" + e.getStackTrace());
			}
		}
		logger.debug("Size of getHoldDetails List in DAO" + list.size());
		return list;
	}

	/**
	 * @param reginitId
	 * @param userId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public boolean updateFinalStatus(String reginitId, String userId, String date, String status) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			//dbUtility.setAutoCommit(false);
			/*dbUtility.createStatement();
			
			sql="UPDATE IGRS_REG_TXN_DETLS SET REGISTRATION_TXN_STATUS ='17'," +  //////17- registration Complete
				"UPDATE_BY ='"+userId+"',UPDATE_DATE =to_date('"+date+"','dd/mm/yy') " +
					"WHERE REG_TXN_ID ='"+reginitId+"'";
			
			flag = dbUtility.executeUpdate(sql);*/
			sql = RegCompCheckerSQL.UPDATE_REGISTRATION_STATUS;
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeUpdate(new String[] { status, userId, reginitId });
			if ("14".equalsIgnoreCase(status) || "17".equalsIgnoreCase(status)) {
				EtokenChange eChange = new EtokenChange(reginitId, status, null);
				Thread t = new Thread(eChange);
				t.start();
			}
			UpdateTImeEtoken(reginitId, status);
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return flag;
	}

	/**
	 * This method inserts completion number in Registration_txn_detls table and
	 * inserts all DOD Detls, Estamp Detls and prop Lock Detls
	 * 
	 * @param regInitId
	 * @param regCompNumber
	 * @return
	 * @throws Exception
	 */
	public boolean insertFinalDetails(String regInitId, String regCompNumber, String userId, String cDate, String officeId, String titleCertFlg, String districtCode, String SroName, String bookId, String disttId) throws Exception {
		boolean flag = false;
		boolean flag2 = false;
		String txnId;
		String fee_free_date;
		String last_del_date;
		String last_due_date;
		String flg;
		String tDate = getDate(cDate);
		CallableStatement clstmt = null;
		String estmTxnId = "";
		DBUtility dbUtility = null;
		try {
			//dbUtility.setAutoCommit(false);
			dbUtility = new DBUtility();
			String drName = "";
			String drOffc = "";
			String digName = "";
			String sqlDr = RegCompCheckerSQL.GET_DR_DIG_NAME;
			dbUtility.createPreparedStatement(sqlDr);
			String offcArr[] = { officeId };
			ArrayList drList = dbUtility.executeQuery(offcArr);
			for (int i = 0; i < drList.size(); i++) {
				ArrayList subList = (ArrayList) drList.get(i);
				drName = (String) subList.get(2);
				drOffc = (String) subList.get(1);
			}
			String sqlDIG = RegCompCheckerSQL.GET_DR_DIG_NAME;
			dbUtility.createPreparedStatement(sqlDIG);
			String offcArrDig[] = { drOffc };
			ArrayList digList = dbUtility.executeQuery(offcArrDig);
			for (int i = 0; i < digList.size(); i++) {
				ArrayList subList = (ArrayList) digList.get(i);
				digName = (String) subList.get(2);
			}
			logger.debug("drName --------------->" + drName);
			logger.debug("digName -------------->" + digName);
			String sql = RegCompCheckerSQL.UPDATE_FINAL_DETLS;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { regCompNumber, titleCertFlg, userId, SroName, userId, bookId, officeId, disttId, disttId, drName, digName, regInitId };
			flag = dbUtility.executeUpdate(arr);
			if (flag) {
				ArrayList flagList = new ArrayList();
				String arrDOD[] = { regCompNumber };
				dbUtility.createPreparedStatement(RegCompCheckerSQL.MODE_DETL);
				flagList = dbUtility.executeQuery(arrDOD);
				flagList.trimToSize();
				if (flagList.size() == 0) {
					flg = "N";
				} else {
					flg = "Y";
				}
				clstmt = dbUtility.createCallableStatement(RegCompCheckerSQL.DOD_DATES_PROCEDURE);
				clstmt.setString(1, regCompNumber);
				clstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				clstmt.registerOutParameter(3, OracleTypes.VARCHAR);
				clstmt.registerOutParameter(4, OracleTypes.VARCHAR);
				clstmt.registerOutParameter(5, OracleTypes.VARCHAR);
				boolean j = clstmt.execute();
				logger.debug("JJJJJJJJJ" + j);
				fee_free_date = clstmt.getString(2);
				last_del_date = clstmt.getString(3);
				last_due_date = clstmt.getString(4);
				// SimpleDateFormat date1 = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss.S");
				//  SimpleDateFormat date2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss");
				//	 Date dateTmp = date1.parse(fee_free_date);
				// Date dateTmp1 = date1.parse(last_del_date);
				// Date dateTmp2 = date1.parse(last_due_date);
				//	 logger.info("Text : " + fee_free_date);
				//	 logger.info("Date : " + dateTmp);
				String final_fee_free = fee_free_date;//date2.format(fee_free_date);
				String final_last_del = last_del_date; //date2.format(last_del_date);
				String final_last_due = last_due_date;//date2.format(last_due_date);
				//	 logger.debug("final date"+final_fee_free);
				String sql2 = RegCompCheckerSQL.GET_DOD_SEQ;
				dbUtility.createStatement();
				String txn_nubr = dbUtility.executeQry(sql2);
				String[] dod_arry = new String[9];
				dod_arry[0] = txn_nubr;
				dod_arry[1] = regCompNumber;
				// dod_arry[2]= tDate;
				dod_arry[2] = final_fee_free;
				dod_arry[3] = final_last_del;
				dod_arry[4] = final_last_due;
				dod_arry[5] = userId;
				dod_arry[6] = userId;
				dod_arry[7] = officeId;
				dod_arry[8] = flg;
				String sql1 = RegCompCheckerSQL.INSERT_DOD_DETL;
				dbUtility.createPreparedStatement(sql1);
				flag = dbUtility.executeUpdate(dod_arry);
				// if(flag)
				//  {
				String sql3 = RegCompCheckerSQL.UPDATE_ESTAMP_STATUS;
				dbUtility.createPreparedStatement(sql3);
				String arrEstamp[] = { userId, tDate, regInitId };
				flag2 = dbUtility.executeUpdate(arrEstamp);
				//if(flag)
				//{
				//String SQL1 = "SELECT COUNT(PROPERTY_LOCK_ID) FROM IGRS_PROP_LOCK_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
				String SQL1 = RegCompCheckerSQL.GET_COUNT_PROPERTY_ID;
				logger.debug(SQL1);
				dbUtility.createStatement();
				String number1 = dbUtility.executeQry(SQL1);
				if (number1.equalsIgnoreCase("0")) {
					logger.debug("in if clause");
					//  String drpqry = "DROP SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ";                         
					String drpqry = RegCompCheckerSQL.DROP_SEQ_PROP_LCK;
					dbUtility.createStatement();
					dbUtility.executeUpdate(drpqry);
					//  String crtqry = "CREATE SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
					String crtqry = RegCompCheckerSQL.CREATE_SEQ_PROP_LCK;
					dbUtility.createStatement();
					dbUtility.executeUpdate(crtqry);
				}
				//String SQL2 = "select IGRS_PROP_LCK_TXN_ID_SEQ.nextval from dual";
				/*sql = RegCompCheckerSQL.GET_PROP_ID_LIST;
				dbUtility.createPreparedStatement(sql);
				String arrProp [] = {regInitId};
				mainList = new ArrayList();
				mainList = dbUtility.executeQuery(arrProp);
				if (mainList.size() != 0) {
					for (int i = 0; i < mainList.size(); i++) {
						System.out.println(i);
						ArrayList list = (ArrayList) mainList.get(i);
						String str = (String) list.get(0);*/
				sql = RegCompCheckerSQL.GET_PROP_ID_LIST;
				dbUtility.createPreparedStatement(sql);
				String arrPropLock[] = { regInitId };
				mainList = new ArrayList();
				mainList = dbUtility.executeQuery(arrPropLock);
				if (mainList.size() != 0) {
					for (int i = 0; i < mainList.size(); i++) {
						ArrayList subList = (ArrayList) mainList.get(i);
						String propId = (String) subList.get(0);
						logger.debug("propId----->" + propId);
						String SQL2 = RegCompCheckerSQL.GET_SEQ_PROP_LCK_ID;
						dbUtility.createStatement();
						String number2 = dbUtility.executeQry(SQL2);
						logger.debug("prop lock seq ----->" + number2);
						if (number2.length() == 1) {
							number2 = "00000" + number2;
						} else if (number2.length() == 2) {
							number2 = "0000" + number2;
						} else if (number2.length() == 3) {
							number2 = "000" + number2;
						} else if (number2.length() == 4) {
							number2 = "00" + number2;
						} else if (number2.length() == 5) {
							number2 = "0" + number2;
						}
						Date date = new Date();
						Format yearformat = new SimpleDateFormat("yy");
						Format monthformat = new SimpleDateFormat("MM");
						Format dateformat = new SimpleDateFormat("dd");
						String dfmt = dateformat.format(date);
						String yfmt = yearformat.format(date);
						String mfmt = monthformat.format(date);
						estmTxnId = "73" + dfmt + mfmt + yfmt + number2;
						logger.debug("prop lock txn Id ----->" + estmTxnId);
						String arrLock[] = { estmTxnId, regCompNumber, tDate, propId, "1", officeId, userId, tDate, };
						String sql4 = RegCompCheckerSQL.INSERT_PROPERTY_LOCKING_DETLS;//+"'"+regInitId+"'";
						dbUtility.createPreparedStatement(sql4);
						flag = dbUtility.executeUpdate(arrLock);
					}
				}
				//if(flag)                                     // email alert on completion of registration
				//{
				ArrayList listParty = new ArrayList();
				ArrayList subList = new ArrayList();
				String status = "";
				String emailContent = "E-Registration is completed successfully." + " Your e-Registration number is :" + regCompNumber;
				dbUtility = new DBUtility();
				String sqlparties = RegCompCheckerSQL.GET_PARTIES_EMAIL_ID;
				dbUtility.createPreparedStatement(sqlparties);
				String arrParty[] = { regInitId };
				listParty = dbUtility.executeQuery(arrParty);
				for (int i = 0; i < listParty.size(); i++) {
					subList = (ArrayList) listParty.get(i);
					String emailId = (String) subList.get(1);
					if (emailId != "" && emailId != null) {
						dbUtility.createCallableStatement(RegCompCheckerSQL.CALL_IGRS_EMAIL_PROC);
						status = dbUtility.insertEmailData(emailId, RegCompCheckerConstant.REGISTRATION_COMPLETION_SUB, emailContent);
					}
				}
				logger.debug("<-----------status obtained-------------->" + status);
				//below code for email alert in case of multiple properties
				sql = RegCompCheckerSQL.GET_PROP_ID_LIST;
				dbUtility.createPreparedStatement(sql);
				String arrProp[] = { regInitId };
				mainList = new ArrayList();
				mainList = dbUtility.executeQuery(arrProp);
				if (mainList.size() != 0) {
					for (int i = 0; i < mainList.size(); i++) {
						System.out.println(i);
						ArrayList list = (ArrayList) mainList.get(i);
						String distt = (String) list.get(1);
						if (distt != districtCode) {
							///*************pending*************//
						}
					}
				}
				//}
				//}
				// }
			}
			if (flag) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return true;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public String checkStatus(String regInitId) throws Exception {
		String status = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
			status = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.CHECK_STATUS;
			dbUtility.createPreparedStatement(query);
			status = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getSeqNum" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return status;
	}

	public String checkEtokenFlag(String officeId) throws Exception {
		String status = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
			status = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.CHECK_ETOKEN_FLAG;
			dbUtility.createPreparedStatement(query);
			status = dbUtility.executeQry(new String[] { officeId });
		} catch (Exception exception) {
			logger.debug("Exception in checkEtokenFlag" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return status;
	}

	public String checkEtokenUserId(String regId, String userId) throws Exception {
		String mappingId = "";
		String counterUserId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
			status = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_COUNTER_MAPPING_ID;
			dbUtility.createPreparedStatement(query);
			mappingId = dbUtility.executeQry(new String[] { regId });
			sql = RegCompCheckerSQL.GET_COUNTER_USER_ID;
			dbUtility.createPreparedStatement(sql);
			counterUserId = dbUtility.executeQry(new String[] { mappingId });
		} catch (Exception exception) {
			logger.debug("Exception in checkEtokenFlag" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return counterUserId;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public String getCompletionNumber(String regInitId) throws Exception {
		String compNumber = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_CMPLETION_NUMBER+"'"+regInitId+"'";
			compNumber = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_CMPLETION_NUMBER;
			dbUtility.createPreparedStatement(query);
			compNumber = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getSeqNum" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus" + e.getStackTrace());
			}
		}
		return compNumber;
	}

	/**
	 * @param Comp
	 * @return
	 * @throws Exception
	 */
	public String getInitiationNumber(String Comp) throws Exception {
		String init = null;
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query="SELECT Reg_init_id from IGRS_REG_COMPLETION_DETAILS where Reg_comp_number ="+"'"+Comp+"'";
			init = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_REG_INIT_ID;
			dbUtility.createPreparedStatement(query);
			init = dbUtility.executeQry(new String[] { Comp });
		} catch (Exception exception) {
			logger.debug("Exception in View Witness" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return init;
	}

	/**
	 * @param regInitId
	 * @param witnessTxnId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCompleteWitnessDetails(String regInitId, String witnessTxnId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = RegCompCheckerSQL.GET_WITNESS_COMPLETE_DETAILS;
			dbUtility.createPreparedStatement(query);
			String arr[] = { regInitId, witnessTxnId };
			list = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getCompleteDetailsWitness" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection " + e.getStackTrace());
			}
		}
		logger.debug("Size of Witness List in DAO" + list.size());
		return list;
	}

	/**
	 * @param regInitId
	 * @return ArrayList that holds Stamp duty and Reg Fee Linked during
	 *         Registration Completion-MAKER
	 */
	public ArrayList getLinkDutiesAtMaker(String regInitId) {
		ArrayList dutiesAtMaker = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_LNKNG_DUTIES_AT_MAKER+"'"+regInitId+"'";
			dutiesAtMaker = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_LNKNG_DUTIES_AT_MAKER;
			dbUtility.createPreparedStatement(query);
			dutiesAtMaker = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getLinkDutiesAtMaker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getLinkDutiesAtMaker" + e.getStackTrace());
			}
		}
		return dutiesAtMaker;
	}

	/**
	 * @param regInitId
	 * @return
	 */
	public ArrayList getLinkDutiesAtChecker(String regInitId) {
		ArrayList dutiesAtMaker = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_LNKNG_DUTIES_AT_CHECKER+"'"+regInitId+"'";
			dutiesAtMaker = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_LNKNG_DUTIES_AT_CHECKER;
			dbUtility.createPreparedStatement(query);
			dutiesAtMaker = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getLinkDutiesAtChecker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getLinkDutiesAtChecker" + e.getStackTrace());
			}
		}
		return dutiesAtMaker;
	}

	public boolean modifyMVdetails(String[] mvDetails) throws Exception {
		boolean modify = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCompCheckerSQL.UPDATE_MV_CHANGE_DETAILS;
			dbUtility.createPreparedStatement(sql);
			modify = dbUtility.executeUpdate(mvDetails);
		} catch (Exception e) {
			logger.debug("Exception in modifyMVdetails in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return modify;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getTransPartyDets(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*String query= RegCommonMkrSQL.GET_PARTY_NAME+"'"+regInitId+"'";
			dbUtility.createStatement();
			list = dbUtility.executeQuery(query);*/
			String query = RegCommonMkrSQL.GET_PARTY_NAME;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyDets" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	/**
	 * @param regInitId
	 * @param partyName
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getTransPartyIds(String regInitId, String partyName) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			String arr[] = { regInitId, partyName, regInitId, partyName };
			dbUtility = new DBUtility();
			String query = RegCommonMkrSQL.GET_PARTY_TXN_ID;
			logger.debug("<------------" + query);
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyIds" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param regInit
	 * @return
	 * @throws Exception
	 */
	public String checkPOA(String poaAuthNo) throws Exception {
		String regInit = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql =RegCommonMkrSQL.CHECK_POA+"'"+poaAuthNo+"'";
			logger.debug(sql);
			regInit = dbUtility.executeQry(sql);
			*/
			sql = RegCommonMkrSQL.CHECK_POA;
			dbUtility.createPreparedStatement(sql);
			regInit = dbUtility.executeQry(new String[] { poaAuthNo });
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getDeedId of checkPOA" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection checkPOA of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return regInit;
	}

	/**
	 * @param witnessTxnId
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateWitnessStatus(String witnessTxnId, String date, String userId) throws Exception {
		boolean updateFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_WITNESS_STATUS;
			logger.debug("<-----" + sql);
			dbUtility.createPreparedStatement(sql);
			String arr[] = { date, userId, witnessTxnId };
			updateFlag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			System.out.println("error in regCompCheckerDAO : updateWitnessStatus" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection regCompCheckerDAO : updateWitnessStatus " + e.getStackTrace());
			}
		}
		return updateFlag;
	}

	/**
	 * @param rDTO
	 * @param date
	 * @param createdBy
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveWitnessDetails(RegCompCheckerDTO rDTO, String date, String createdBy) throws Exception {
		// TODO Auto-generated method stub
		boolean tmpFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			//	sql = "SELECT IGRS_REG_PROP_PMNT_LNKG_SEQ.NEXTVAL FROM DUAL";
			sql = RegCompCheckerSQL.GET_PYMT_PROP_SEQ;
			String num = dbUtility.executeQry(sql);
			logger.debug("<-----" + sql);
			//logger.debug("Witness Txn Number"+num);
			rDTO.setWitnessTxnNummber(num);
			sql = RegCompCheckerSQL.INSERT_WITNESS_DETAILS;
			logger.debug("<-----" + sql);
			dbUtility.createPreparedStatement(sql);
			String temp[] = new String[16];
			temp[0] = num;
			temp[1] = rDTO.getRegInitNumber();
			temp[2] = rDTO.getWitFirstName();
			temp[3] = rDTO.getWitMiddleName();
			temp[4] = rDTO.getWitLastName();
			//logger.debug("<---------"+rDTO.getWitGender());
			if (rDTO.getWitGender() != null) {
				if (rDTO.getWitGender().equalsIgnoreCase("Male")) {
					temp[5] = "M";
				} else if (rDTO.getWitGender().equalsIgnoreCase("Female")) {
					temp[5] = "F";
				} else {
					temp[5] = "O";
				}
			}
			//temp[6] = rDTO.getWitAge();
			temp[6] = rDTO.getWitAge();
			temp[7] = rDTO.getWitFatherName();
			temp[8] = rDTO.getWitMotherName();
			temp[9] = rDTO.getWitSpouseName();
			temp[10] = rDTO.getWitNationality();
			temp[11] = rDTO.getWitAddress();
			temp[12] = rDTO.getWitPhoneNumber();
			temp[13] = createdBy;
			temp[14] = date;
			temp[15] = rDTO.getRelationshipWit();
			tmpFlag = dbUtility.executeUpdate(temp);
			if (tmpFlag) {
				String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
				dbUtility.createStatement();
				String seqNum = dbUtility.executeQry(query);
				String arr[] = { seqNum, createdBy, "", "" };
				query = RegCommonMkrSQL.INSERT_PARTY_OTHER_DETLS_2;
				dbUtility.createPreparedStatement(query);
				boolean flag = dbUtility.executeUpdate(arr);
				if (flag) {
					query = RegCommonMkrSQL.UPDATE_WITNESS_OTHER_DETLS;
					dbUtility.createPreparedStatement(query);
					String[] partArr = { seqNum, createdBy, rDTO.getRegInitNumber(), num };
					tmpFlag = dbUtility.executeUpdate(partArr);
				}
			}
		} catch (Exception e) {
			System.out.println("error in regCompCheckerDAO : saveWitnessDetails" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection regCompCheckerDAO : saveWitnessDetails " + e.getStackTrace());
			}
		}
		return tmpFlag;
	}

	/**
	 * @param rdto
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateCaseDetails(RegCompCheckerDTO rdto, String date, String userId) throws Exception {
		boolean flag = false;
		String complaintNo = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String dd = date.substring(0, 2);
			String mm = date.substring(3, 5);
			String yy = date.substring(6, 10);
			String no = "IC" + dd + mm + yy;
			String query = RegCompCheckerSQL.GET_CASE_SEQ;
			String seqNo = dbUtility.executeQry(query);
			complaintNo = no + seqNo;
			//	logger.debug("Complaint No----->"+complaintNo);
			rdto.setComplaintNo(complaintNo);
			sql = RegCompCheckerSQL.INSERT_CASE_DETAILS;
			logger.debug("<-----" + sql);
			dbUtility.createPreparedStatement(sql);
			String arr[] = { rdto.getRegInitNumber(), complaintNo, rdto.getNetStampDuty(), userId, date };
			flag = dbUtility.executeUpdate(arr);
			if (flag) {
				sql = RegCompCheckerSQL.UPDATE_REG_STATUS;
				dbUtility.createPreparedStatement(sql);
				String arrUpdate[] = { "15", date, userId, rdto.getRegInitNumber() };
				flag = dbUtility.executeUpdate(arrUpdate);
			}
		} catch (Exception e) {
			System.out.println("error in regCompCheckerDAO : updateCaseDetails" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection regCompCheckerDAO : updateCaseDetails " + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * @param regInitId
	 * @return
	 */
	public String getImpundDetails(String regInitId) {
		String status = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			//	dbUtility.createStatement();
			sql = RegCompCheckerSQL.GET_REGISTARTION_STATUS;
			dbUtility.createPreparedStatement(sql);
			//"SELECT REGISTRATION_TXN_STATUS FROM IGRS_REG_TXN_DETLS WHERE REG_TXN_ID ='"+regInitId+"'";
			String str1 = dbUtility.executeQry(new String[] { regInitId });
			if (str1.equals("15")) // IMPOUNDED
			{
				sql = RegCompCheckerSQL.GET_IMPOUND_DETAILS;//+"'"+regInitId+"'";
				dbUtility.createPreparedStatement(sql);
				status = dbUtility.executeQry(new String[] { regInitId });
				status = "15~" + status;
			}
			if (str1.equals("16") || str1.equals("18")) //TIME BARRED
			{
				sql = RegCompCheckerSQL.GET_TIME_BARRED_DETAILS;//+"'"+regInitId+"'";
				dbUtility.createPreparedStatement(sql);
				status = dbUtility.executeQry(new String[] { regInitId });
				status = str1 + "~" + status;
			}
		} catch (Exception exception) {
			logger.debug("Exception in getImpundDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public boolean updateRegSR(String regId, String usrName, String usrOffc) throws Exception {
		boolean modify = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCompCheckerSQL.UPDATE_SR_NAME;
			dbUtility.createPreparedStatement(sql);
			String[] arr = new String[3];
			arr[0] = usrName;
			arr[1] = usrOffc;
			arr[2] = regId;
			modify = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.debug("Exception in modifyImpoundDetails in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		return modify;
	}

	/**
	 * @param pmtReceiptArr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean modifyImpoundDetails(String pmtReceiptArr[]) throws Exception {
		boolean modify = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCompCheckerSQL.UPDATE_IMPOUND_DETAILS;
			dbUtility.createPreparedStatement(sql);
			modify = dbUtility.executeUpdate(pmtReceiptArr);
		} catch (Exception e) {
			logger.debug("Exception in modifyImpoundDetails in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		return modify;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPropLockDetails(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList propLockDetails = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_PROP_LOCK_DETAILS+ "'" + regInitId + "'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_PROP_LOCK_DETAILS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
			for (int i = 0; i < list.size(); i++) {
				ArrayList list2 = (ArrayList) list.get(i);
				RegCompCheckerDTO rdto = new RegCompCheckerDTO();
				rdto.setPropertyId((String) list2.get(0));
				if (((String) list2.get(1)).equals("")) {
					rdto.setPoaRegId("-");
				} else {
					rdto.setPoaRegId((String) list2.get(1));
				}
				if (((String) list2.get(2)).equals("")) {
					rdto.setPurposeOfLocking("-");
				} else {
					rdto.setPurposeOfLocking((String) list2.get(2));
				}
				//rdto.setPoaRegId((String)list2.get(1));
				//rdto.setPurposeOfLocking((String)list2.get(2));
				propLockDetails.add(rdto);
			}
		} catch (Exception exception) {
			logger.debug("Exception in getPropLockDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		return propLockDetails;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCaseOpenDetails(String regInitId, ArrayList regList) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList caseDetails = new ArrayList();
		ArrayList subregList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			//dbUtility.createStatement();
			if (regList.size() != 0) {
				for (int k = 0; k < regList.size(); k++) {
					subregList = (ArrayList) regList.get(k);
					String num = (String) subregList.get(0);
					String propId = (String) subregList.get(1);
					String arr[] = { num, propId };
					/*String query=RegCompCheckerSQL.GET_CASE_DETAILS+"'"+num+"'";
					list = dbUtility.executeQuery(query);*/
					String query = RegCompCheckerSQL.GET_CASE_DETAILS;
					dbUtility.createPreparedStatement(query);
					list = dbUtility.executeQuery(new String[] { num });
					for (int i = 0; i < list.size(); i++) {
						ArrayList list2 = (ArrayList) list.get(i);
						RegCompCheckerDTO rdto = new RegCompCheckerDTO();
						rdto.setCaseId((String) list2.get(0));
						if (((String) list2.get(1)).equals("")) {
							rdto.setCaseName("-");
						} else {
							rdto.setCaseName((String) list2.get(1));
						}
						if (((String) list2.get(2)).equals("")) {
							rdto.setComments("-");
						} else {
							rdto.setComments((String) list2.get(2));
						}
						//rdto.setPoaRegId((String)list2.get(1));
						//rdto.setPurposeOfLocking((String)list2.get(2));
						caseDetails.add(rdto);
					}
				}
			}
		} catch (Exception exception) {
			logger.debug("Exception in getCaseOpenDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		return caseDetails;
	}

	/**
	 * @param appId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPaymentFlag(String appId, String sourceMod, String pmt_type) throws Exception {
		ArrayList paymentList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String param[] = { appId, appId, sourceMod, pmt_type };
			sql = RegCompCheckerSQL.GET_PAYMENT_FLAG;
			dbUtility.createPreparedStatement(sql);
			paymentList = dbUtility.executeQuery(param);
			logger.debug("payment flag from database----->" + paymentList);
		} catch (Exception exception) {
			System.out.println("Exception in getPaymentFlag-RegCommonDAO" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return paymentList;
	}

	/**
	 * @param regInitId
	 * @param sourceMod
	 * @param pmtType
	 * @return ArrayList
	 */
	public ArrayList getPaidAmtChecker(String regInitId, String sourceMod, String pmtType) {
		ArrayList paidAmtAtChecker = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_PAID_AMT;
			dbUtility.createPreparedStatement(query);
			String arr[] = { sourceMod, pmtType, regInitId };
			paidAmtAtChecker = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getPaidAmtChecker" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getPaidAmtChecker" + e.getStackTrace());
			}
		}
		return paidAmtAtChecker;
	}

	/**
	 * @param rdto
	 * @param date
	 * @param userId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	/*public boolean updateTimeBarrdCaseDetails(RegCompCheckerDTO rdto, String date, String userId,String status) throws Exception{
		boolean flag = false;
		String complaintNo = "";
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String dd  = date.substring(0, 2);
			String mm = date.substring(3, 5);
			String yy = date.substring(6,10);
			String no = "PP"+dd+mm+yy;
			String query = RegCompCheckerSQL.GET_TIME_BARRED_CASE_SEQ;
			String seqNo = dbUtility.executeQry(query);
			complaintNo = no+seqNo;
			
			logger.debug("Complaint No----->"+complaintNo);
			rdto.setComplaintNo(complaintNo);
			sql = RegCompCheckerSQL.INSERT_TIME_BARRED_CASE_DETAILS;
			logger.debug("<-----"+sql);
			dbUtility.createPreparedStatement(sql);
			String arr[] = {rdto.getRegInitNumber(),
					complaintNo,
					"1000",  // not confirmed
					userId,
					date
				};
			flag = dbUtility.executeUpdate(arr);
			
			if(flag)
			{
				sql = RegCompCheckerSQL.UPDATE_REG_STATUS;
				dbUtility.createPreparedStatement(sql);
				String arrUpdate[] = {status,
						date,
						userId,
						rdto.getRegInitNumber()
					};
				flag = dbUtility.executeUpdate(arrUpdate);
			}
		}
		catch (Exception e) {
			System.out.println("error in regCompCheckerDAO : updateTimeBarrdCaseDetails"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out
						.println("error in closing connection regCompCheckerDAO : updateTimeBarrdCaseDetails "
								+ e.getStackTrace());
			}
		}
		
		return flag;
	}*/
	public String updateTimeBarrdCaseDetails(String regNumber, String date, String userId, String status) throws Exception {
		boolean flag = false;
		String complaintNo = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String dd = date.substring(0, 2);
			String mm = date.substring(3, 5);
			String yy = date.substring(6, 10);
			String no = "PP" + dd + mm + yy;
			String query = RegCompCheckerSQL.GET_TIME_BARRED_CASE_SEQ;
			String seqNo = dbUtility.executeQry(query);
			complaintNo = no + seqNo;
			dbUtility.createStatement();
			query = RegCompCheckerSQL.REG_FEE_INITIATION + regNumber + "'";
			String amt = dbUtility.executeQry(query);
			logger.debug("Complaint No----->" + complaintNo);
			//rdto.setComplaintNo(complaintNo);
			sql = RegCompCheckerSQL.INSERT_TIME_BARRED_CASE_DETAILS;
			logger.debug("<-----" + sql);
			dbUtility.createPreparedStatement(sql);
			String arr[] = { regNumber, complaintNo, amt, // not confirmed
			userId, date };
			flag = dbUtility.executeUpdate(arr);
			if (flag) {
				sql = RegCompCheckerSQL.UPDATE_REG_STATUS;
				dbUtility.createPreparedStatement(sql);
				String arrUpdate[] = { status, date, userId, regNumber };
				flag = dbUtility.executeUpdate(arrUpdate);
			}
		} catch (Exception e) {
			System.out.println("error in regCompCheckerDAO : updateTimeBarrdCaseDetails" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection regCompCheckerDAO : updateTimeBarrdCaseDetails " + e.getStackTrace());
			}
		}
		return complaintNo;
	}

	/**
	 * @param pnaltyReceiptArr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean modifyTimeBarrdCaseDetails(String pnaltyReceiptArr[]) throws Exception {
		boolean modify = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCompCheckerSQL.UPDATE_TIME_BARRED_DETAILS;
			dbUtility.createPreparedStatement(sql);
			modify = dbUtility.executeUpdate(pnaltyReceiptArr);
		} catch (Exception e) {
			logger.debug("Exception in modifyTimeBarrdCaseDetails in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		return modify;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPropertyRelatedComplianeList(String regInitId) throws Exception {
		ArrayList propertyRelatedComplaince = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_PROPERTY_RELATED_COMPLIANCE+"'"+regInitId+"'";
			propertyRelatedComplaince = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_PROPERTY_RELATED_COMPLIANCE;
			dbUtility.createPreparedStatement(query);
			propertyRelatedComplaince = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getPropertyRelatedComplianeList" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getPropertyRelatedComplianeList" + e.getStackTrace());
			}
		}
		return propertyRelatedComplaince;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPartyRelatedComplianeList(String regInitId) throws Exception {
		ArrayList propertyRelatedComplaince = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			//String query=RegCompCheckerSQL.GET_PARTY_RELATED_COMPLAINCE;
			String query = RegCompCheckerSQL.GET_PARTY_RELATED_COMPLAINCE_updated;
			dbUtility.createPreparedStatement(query);
			String arr[] = { regInitId };
			propertyRelatedComplaince = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getPartyRelatedComplianeList" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getPartyRelatedComplianeList" + e.getStackTrace());
			}
		}
		return propertyRelatedComplaince;
	}

	/**
	 * @param propertyRelatedCompliance
	 * @param regInitId
	 * @param userId
	 * @param date
	 * @return boolean
	 */
	public boolean modifyPropertyRelatedCompliance(HashMap propertyRelatedCompliance, String regInitId, String userId, String date) {
		boolean flag = false;
		String query = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if (propertyRelatedCompliance.size() > 0) {
				Set mapSet = (Set) propertyRelatedCompliance.entrySet();
				Iterator mapIterator = mapSet.iterator();
				while (mapIterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) mapIterator.next();
					String propertyId = (String) mapEntry.getKey();
					ArrayList valueList = (ArrayList) mapEntry.getValue();
					RegCompCheckerDTO rrdto = (RegCompCheckerDTO) valueList.get(0);
					if (rrdto.getIsUpload().equals("Y")) {
						query = RegCompCheckerSQL.INSERT_UPLOAD_PROPERTY_HISTORY;
						dbUtility.createPreparedStatement(query);
						String arrTemp[] = { "C", userId, date, regInitId, propertyId };
						flag = dbUtility.executeUpdate(arrTemp);
						if (flag) {
							query = RegCompCheckerSQL.MODIFY_PROPERTY_RELATED_COMPLIANCE;
							dbUtility.createPreparedStatement(query);
							String arr[] = { rrdto.getPropImage1FilePath(), rrdto.getPropImage2FilePath(), rrdto.getPropImage3FilePath(), rrdto.getPropMapFilePath(), rrdto.getPropRinFilePath(), rrdto.getPropKhasraFilePath(), userId, date, regInitId, propertyId, };
							flag = dbUtility.executeUpdate(arr);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.debug("Exception in modifyPropertyRelatedCompliance in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * @param partyRelatedCompliance
	 * @param regInitId
	 * @param userId
	 * @param date
	 * @return boolean
	 */
	public boolean modifyPartyRelatedCompliance(HashMap partyRelatedCompliance, String regInitId, String userId, String date) {
		boolean flag = false;
		String query = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if (partyRelatedCompliance.size() > 0) {
				Set mapSet = (Set) partyRelatedCompliance.entrySet();
				Iterator mapIterator = mapSet.iterator();
				while (mapIterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) mapIterator.next();
					String partyId = (String) mapEntry.getKey();
					ArrayList valueList = (ArrayList) mapEntry.getValue();
					RegCompCheckerDTO rrdto = (RegCompCheckerDTO) valueList.get(0);
					if (rrdto.getIsUpload().equals("Y")) {
						query = RegCompCheckerSQL.INSERT_UPLOAD_PARTY_HISTORY;
						dbUtility.createPreparedStatement(query);
						String arrTemp[] = { "C", userId, date, regInitId, partyId };
						flag = dbUtility.executeUpdate(arrTemp);
						if (flag) {
							query = RegCompCheckerSQL.MODIFY_PARTY_RELATED_COMPLIANCE;
							dbUtility.createPreparedStatement(query);
							String arr[] = { rrdto.getCollectorCertDocPath(), rrdto.getPhotoProofDocPath(), rrdto.getNotAffdExecDocPath(), rrdto.getExecPhotoPath(), rrdto.getNotAffdAttrPath(), rrdto.getAttrPhotoPath(), rrdto.getClaimntPhotoPath(), rrdto.getPanForm60Path(), userId, date, regInitId, partyId, };
							flag = dbUtility.executeUpdate(arr);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.debug("Exception in modifyPropertyRelatedCompliance in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * @param witnessUploadMap
	 * @param regInitId
	 * @param userId
	 * @param cdate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addWitnessPhotographDetails(Map witnessUploadMap, String regInitId, String userId, String cdate) throws Exception {
		String date = getDate(cdate);
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			if (witnessUploadMap.size() > 0) {
				Set mapSet = (Set) witnessUploadMap.entrySet();
				Iterator mapIterator = mapSet.iterator();
				while (mapIterator.hasNext()) {
					String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
					dbUtility.createStatement();
					String seqNum = dbUtility.executeQry(query);
					logger.debug("<------SEQ NUMBER IN DAO" + seqNum);
					Map.Entry mapEntry = (Map.Entry) mapIterator.next();
					ArrayList valueList = (ArrayList) mapEntry.getValue();
					RegCompCheckerDTO rDTO = (RegCompCheckerDTO) valueList.get(0);
					String wittId = (String) mapEntry.getKey();
					query = RegCompCheckerSQL.INSERT_PARTY_OTHER_WITNESS_DETAILS;
					dbUtility.createPreparedStatement(query);
					String arr[] = { seqNum, rDTO.getWitnessDocPath(), userId, date };
					flag = dbUtility.executeUpdate(arr);
					if (flag) {
						String sql = RegCompCheckerSQL.UPDATE_WITNESS_TXN_DETAILS;
						dbUtility.createPreparedStatement(sql);
						String arrTemp[] = { userId, date, seqNum, regInitId, wittId };
						flag = dbUtility.executeUpdate(arrTemp);
					}
				}
			}
		} catch (Exception e) {
			logger.debug("Exception in addWitnessPhotographDetails in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * for inserting estamp txn id mapped with reg txn id into database.
	 * 
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertEstampMappingDetls(String[] param) {
		boolean boo = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.INSERT_REG_ESTAMP_MAP_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo)
				dbUtility.commit();
			else
				dbUtility.rollback();
		} catch (Exception e) {
			logger.error("RegCompCheckerDAODAO in insertEstampMappingDetls start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getEstampPurposeDetails(String regInitId) {
		String purpose = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_ESTAMP_PURPOSE_DFTLS+"'"+regInitId+"'";
			purpose = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_ESTAMP_PURPOSE_DFTLS;
			dbUtility.createPreparedStatement(query);
			purpose = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getEstampPurposeDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getEstampPurposeDetails" + e.getStackTrace());
			}
		}
		return purpose;
	}

	public String getEstampFlag(String regInitId) {
		String purpose = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_ESTAMP_PURPOSE_DFTLS+"'"+regInitId+"'";
			purpose = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.ESTAMP_FLAG;
			dbUtility.createPreparedStatement(query);
			purpose = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getEstampPurposeDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getEstampPurposeDetails" + e.getStackTrace());
			}
		}
		return purpose;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getEstampStatus(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_ESTAMP_STATUS+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_ESTAMP_STATUS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getEstampStatus" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getEstampStatus" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getEstampRefundStatus(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_ESTAMP_DEACTIVATION_DETLS+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_ESTAMP_DEACTIVATION_DETLS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getEstampRefundStatus" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getEstampRefundStatus" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param regInitId
	 * @param status
	 * @return boolean
	 * @throws Exception
	 */
	public boolean UpdateRegistrationCompletionStatus(String regInitId, String status, String date, String userId) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_REGISTRATION_STATUS;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { status, userId, regInitId };
			flag = dbUtility.executeUpdate(arr);
			if ("14".equalsIgnoreCase(status) || "17".equalsIgnoreCase(status)) {
				EtokenChange eChange = new EtokenChange(regInitId, status, null);
				Thread t = new Thread(eChange);
				t.start();
			}
			UpdateTImeEtoken(regInitId, status);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getCancelledDetails(String regInitId) {
		String status = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql =  RegCompCheckerSQL.GET_REGISTRATION_STATUS+"'"+regInitId+"'";
			status = dbUtility.executeQry(sql);*/
			sql = RegCompCheckerSQL.GET_REGISTRATION_STATUS;
			dbUtility.createPreparedStatement(sql);
			status = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getCancelledDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	/**
	 * @param dutiesArr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertFinalDuties(String dutiesArr[]) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = RegCompCheckerSQL.UPDATE_REG_CHK_DUTY_DETLS;
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeUpdate(dutiesArr);
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return flag;
	}

	/**
	 * @param regInitId
	 * @param userId
	 * @param date
	 * @return boolean
	 */
	/*public boolean deactivateEstampDetails(String regInitId, String userId, String date)
	{
		ArrayList list = new ArrayList();
		boolean flag = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_ESTAMPS+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);
			
			for(int i = 0 ; i < list.size(); i++)
			{
				ArrayList list2 = (ArrayList)list.get(i);
				String EstampId = (String)list2.get(0);
				if(EstampId != "")
				{
					String sql = RegCompCheckerSQL.UPDATE_ESTAMP_STATUS;
					dbUtility.createPreparedStatement(sql);
					String arr[] = {
							userId,
							date,
							EstampId
					};
					flag = dbUtility.executeUpdate(arr);
				}
			}
			
		} catch (Exception exception) {
			logger.debug("Exception in deactivateEstampDetails" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection deactivateEstampDetails"+e.getStackTrace());
			}
			
		}
		return flag;
	}*/
	public String getTimeBarrdStatus(String regInitId) {
		String penalityId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_TIME_BARRD_DETLS+"'"+regInitId+"'";
			penalityId = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_TIME_BARRD_DETLS;
			dbUtility.createPreparedStatement(query);
			penalityId = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getTimeBarrdStatus" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getTimeBarrdStatus" + e.getStackTrace());
			}
		}
		return penalityId;
	}

	/**
	 * @param regDtlsArr
	 * @return
	 * @throws Exception
	 */
	public boolean modifyLastDueDate(String regDtlsArr[]) throws Exception {
		boolean modify = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCompCheckerSQL.UPDATE_LAST_DUE_DATE;
			dbUtility.createPreparedStatement(sql);
			modify = dbUtility.executeUpdate(regDtlsArr);
		} catch (Exception e) {
			logger.debug("Exception in modifyLastDueDate in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		return modify;
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getLastDueDate(String regInitId) {
		String lastDueDate = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_LAST_DUE_DATE+"'"+regInitId+"'";
			lastDueDate = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_LAST_DUE_DATE;
			dbUtility.createPreparedStatement(query);
			lastDueDate = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getLastDueDate" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getLastDueDate" + e.getStackTrace());
			}
		}
		return lastDueDate;
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getDeedId(String regInitId) {
		String deedId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DEED_ID+"'"+regInitId+"'";
			deedId = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_DEED_ID;
			dbUtility.createPreparedStatement(query);
			deedId = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getDeedId" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getDeedId" + e.getStackTrace());
			}
		}
		return deedId;
	}

	public String getbookId(String regInitId) {
		String deedId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DEED_ID+"'"+regInitId+"'";
			deedId = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_BOOK_ID;
			dbUtility.createPreparedStatement(query);
			deedId = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getDeedId" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getDeedId" + e.getStackTrace());
			}
		}
		return deedId;
	}

	public String getbookName(String bookId) {
		String deedId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DEED_ID+"'"+regInitId+"'";
			deedId = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_BOOK_NAME;
			dbUtility.createPreparedStatement(query);
			deedId = dbUtility.executeQry(new String[] { bookId });
		} catch (Exception exception) {
			logger.debug("Exception in getDeedId" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getDeedId" + e.getStackTrace());
			}
		}
		return deedId;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getRejectionListDetls() throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = RegCompCheckerSQL.GET_REJECTION_LIST_DETLS;
			list = dbUtility.executeQuery(query);
		} catch (Exception exception) {
			logger.debug("Exception in getRejectionListDetls" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getRejectionListDetls" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param sealID
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getSealSubTypeIds(String sealID) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_SEAl_SUBTYPE_ID+"'"+sealID+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_SEAl_SUBTYPE_ID;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { sealID });
		} catch (Exception exception) {
			logger.debug("Exception in getSealSubTypeIds" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getSealSubTypeIds" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param regInitId
	 * @param sealId
	 * @param sealSubId
	 * @return boolean
	 */
	public boolean insertSealsData(String regInitId, String sealId, String sealSubId) {
		String count = "";
		String sql = "";
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.CHECK_SEALS_DATA+"'"+regInitId+"'";
			count = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.CHECK_SEALS_DATA;
			dbUtility.createPreparedStatement(query);
			count = dbUtility.executeQry(new String[] { regInitId });
			if (count.equals("0")) {
				String arr[] = { regInitId, sealId, sealSubId };
				if (sealId.equals(RegCompCheckerConstant.PRESENTATION_SEAl)) {
					sql = RegCompCheckerSQL.INSERT_PRSENTATION_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.STAMPDUTY_SEAl)) {
					sql = RegCompCheckerSQL.INSERT_STAMP_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.WITNESS_SEAl)) {
					sql = RegCompCheckerSQL.INSERT_WITNESS_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.EXECUTION_SEAl)) {
					sql = RegCompCheckerSQL.INSERT_EXECUTION_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.REGISTRATIONFEE_SEAl)) {
					sql = RegCompCheckerSQL.INSERT_REGISTRATION_FEE_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.REGISTRATION_SEAl)) {
					sql = RegCompCheckerSQL.INSERT_REGISTRATION_SEAL_DATA;
				}
				dbUtility.createPreparedStatement(sql);
				flag = dbUtility.executeUpdate(arr);
			} else {
				String arr[] = { sealId, sealSubId, regInitId };
				if (sealId.equals(RegCompCheckerConstant.PRESENTATION_SEAl)) {
					sql = RegCompCheckerSQL.UPDATE_PRSENTATION_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.STAMPDUTY_SEAl)) {
					sql = RegCompCheckerSQL.UPDATE_STAMP_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.WITNESS_SEAl)) {
					sql = RegCompCheckerSQL.UPDATE_WITNESS_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.EXECUTION_SEAl)) {
					sql = RegCompCheckerSQL.UPDATE_EXECUTION_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.REGISTRATIONFEE_SEAl)) {
					sql = RegCompCheckerSQL.UPDATE_REGISTRATION_FEE_SEAL_DATA;
				} else if (sealId.equals(RegCompCheckerConstant.REGISTRATION_SEAl)) {
					sql = RegCompCheckerSQL.UPDATE_REGISTRATION_SEAL_DATA;
				}
				dbUtility.createPreparedStatement(sql);
				flag = dbUtility.executeUpdate(arr);
			}
		} catch (Exception exception) {
			logger.debug("Exception in insertSealsData" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection insertSealsData" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * @param officeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLoggedInDistrictDetls(String officeId, String lang) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = "";
			if (lang.equalsIgnoreCase("hi")) {
				query = RegCompCheckerSQL.GET_OFFICE_DISTRICT_DETLS_HI;
			} else {
				query = RegCompCheckerSQL.GET_OFFICE_DISTRICT_DETLS;
			}
			dbUtility.createPreparedStatement(query);
			String arr[] = { officeId };
			list = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getLoggedInDistrictDetls" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAdjudicatedDutyDetls(String appId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_ADJUDICATED_DUTY_DETS+"'"+appId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.GET_ADJUDICATED_DUTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { appId });
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyIdList(String regNumber) throws Exception {
		ArrayList propIdList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.GET_PROP_ID_LIST;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { regNumber };
			mainList = new ArrayList();
			mainList = dbUtility.executeQuery(arr);
			if (mainList.size() != 0) {
				System.out.println(mainList.size());
				for (int i = 0; i < mainList.size(); i++) {
					System.out.println(i);
					ArrayList list = (ArrayList) mainList.get(i);
					String str = (String) list.get(0);
					System.out.println(str);
					RegCompCheckerDTO regdto = new RegCompCheckerDTO();
					regdto.setPropId(str);
					System.out.println(regdto.getPropId());
					propIdList.add(regdto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
			logger.error("error in getProperty method of RegCompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in getProperty method close dbUtility of RegCompMkrDAO :" + e.getStackTrace());
			}
		}
		logger.debug(propIdList.size());
		return propIdList;
	}

	/**
	 * @param regInitId
	 * @param emailSub
	 * @param emailContent
	 * @return
	 * @throws Exception
	 */
	public String emailAlert(String regInitId, String emailSub, String emailContent, String disttCode) throws Exception {
		String status = new String();
		ArrayList list = new ArrayList();
		ArrayList subList = new ArrayList();
		ArrayList list2 = new ArrayList();
		ArrayList subList2 = new ArrayList();
		String userId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = RegCompCheckerSQL.GET_DRO;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { disttCode };
			list = dbUtility.executeQuery(arr);
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				String droId = (String) subList.get(0);
				String droName = (String) subList.get(1);
				String sql2 = RegCompCheckerSQL.GET_DR_OFFICERS_USER_ID;
				dbUtility.createPreparedStatement(sql2);
				String arrDR[] = { droId };
				list2 = dbUtility.executeQuery(arrDR);
				for (int j = 0; j < list2.size(); j++) {
					subList2 = (ArrayList) list2.get(j);
					userId = (String) subList2.get(0);
					if (userId != "" && userId != null) {
						dbUtility.createCallableStatement(RegCompCheckerSQL.CALL_IGRS_EMAIL_PROC_DR);
						status = dbUtility.insertEmailData(userId, emailSub, emailContent);
					}
				}
				//if(emailId != "" && emailId != null)
				//{
				//dbUtility.createCallableStatement(RegCompCheckerSQL.CALL_IGRS_EMAIL_PROC_DR);
				//	status = dbUtility.insertEmailData(emailId,emailSub, emailContent);
				//}
			}
			logger.debug("<-----------status obtained-------------->" + status);
			//list2 = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return status;
	}

	/**
	 * @param eForm
	 * @param userId
	 * @param date
	 * @param regNum
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateBiometricDetails(RegCompCheckerForm eForm, String userId, String date, String regNum) throws Exception {
		ArrayList partyList = eForm.getPartiesDetailList();
		ArrayList witnessList = eForm.getWitnessDetailsList();
		ArrayList consenterList = eForm.getConsenterDetailsList();
		boolean tmpFlag = false;
		Iterator itr = partyList.iterator();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			while (itr.hasNext()) {
				RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr.next();
				String partyId = rrdto.getPartyTxnId();
				logger.debug("^^^^party id" + partyId);
				String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
				dbUtility.createStatement();
				String seqNum = dbUtility.executeQry(query);
				//		logger.debug("<------SEQ NUMBER IN DAO"+seqNum);
				String arr[] = { seqNum, userId, rrdto.getSignatureName(), rrdto.getSignaturePath(), rrdto.getThumbName(), rrdto.getThumbPath(), rrdto.getPhotoName(), rrdto.getPhotoPath(), "" //rrdto.getThumbRemarks()
				//"signature.GIF",
				//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE+partyId+"/"+"signature.GIF",
				//"FingerPrint.GIF",
				//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB+partyId+"/"+"FingerPrint.GIF",
				////"Photo.GIF",
				//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO+partyId+"/"+"Photo.GIF",
				};
				query = RegCompCheckerSQL.INSERT_PARTY_OTHER_DETLS_2;
				dbUtility.createPreparedStatement(query);
				boolean flag = dbUtility.executeUpdate(arr);
				sql = RegCompCheckerSQL.UPDATE_PARTY_OTHER_DETLS;
				dbUtility.createPreparedStatement(sql);
				String[] partArr = { seqNum, userId, regNum, partyId };
				tmpFlag = dbUtility.executeUpdate(partArr);
			}
			Iterator itr2 = witnessList.iterator();
			while (itr2.hasNext()) {
				RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr2.next();
				String partyId = rrdto.getWitnessTxnNummber();
				//	logger.debug("^^^^witness id"+partyId);
				String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
				dbUtility.createStatement();
				String seqNum = dbUtility.executeQry(query);
				//logger.debug("<------SEQ NUMBER IN DAO"+seqNum);
				String arr[] = { seqNum, userId, rrdto.getSignatureName(), rrdto.getSignaturePath(), rrdto.getThumbName(), rrdto.getThumbPath(), rrdto.getPhotoName(), rrdto.getPhotoPath(), ""//rrdto.getThumbRemarks()
				//"signature.GIF",
				//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE+partyId+"/"+"signature.GIF",
				//"FingerPrint.GIF",
				//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB+partyId+"/"+"FingerPrint.GIF",
				//"Photo.GIF",
				//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO+partyId+"/"+"Photo.GIF",
				};
				query = RegCompCheckerSQL.INSERT_PARTY_OTHER_DETLS_2;
				dbUtility.createPreparedStatement(query);
				boolean flag = dbUtility.executeUpdate(arr);
				sql = RegCompCheckerSQL.UPDATE_WITNESS_OTHER_DETLS;
				dbUtility.createPreparedStatement(sql);
				String[] partArr = { seqNum, userId, regNum, partyId };
				tmpFlag = dbUtility.executeUpdate(partArr);
			}
			Iterator itr3 = consenterList.iterator();
			while (itr3.hasNext()) {
				RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr3.next();
				String partyId = rrdto.getConsentorTxnNumber();
				//	logger.debug("^^^^witness id"+partyId);
				String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
				dbUtility.createStatement();
				String seqNum = dbUtility.executeQry(query);
				//logger.debug("<------SEQ NUMBER IN DAO"+seqNum);
				String arr[] = { seqNum, userId, rrdto.getSignatureName(), rrdto.getSignaturePath(), rrdto.getThumbName(), rrdto.getThumbPath(), rrdto.getPhotoName(), rrdto.getPhotoPath(), ""//rrdto.getThumbRemarks()
				//"signature.GIF",
				//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE+partyId+"/"+"signature.GIF",
				//"FingerPrint.GIF",
				//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB+partyId+"/"+"FingerPrint.GIF",
				//"Photo.GIF",
				//RegCompCheckerConstant.FILE_UPLOAD_PATH+regNum+RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO+partyId+"/"+"Photo.GIF",
				};
				query = RegCompCheckerSQL.INSERT_PARTY_OTHER_DETLS_2;
				dbUtility.createPreparedStatement(query);
				boolean flag = dbUtility.executeUpdate(arr);
				sql = RegCompCheckerSQL.UPDATE_CONSENTER_OTHER_DETLS;
				dbUtility.createPreparedStatement(sql);
				String[] partArr = { seqNum, userId, regNum, partyId };
				tmpFlag = dbUtility.executeUpdate(partArr);
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return true;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getConsenterDetails(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.VIEW_CONSENTER+"'"+regInitId+"'";
			list = dbUtility.executeQuery(query);*/
			String query = RegCompCheckerSQL.VIEW_CONSENTER;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getConsenterDetails" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		logger.debug("Size of Consenter List in DAO" + list.size());
		return list;
	}

	/**
	 * @param witnessTxnId
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateConsenterStatus(String witnessTxnId, String date, String userId) throws Exception {
		boolean updateFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_CONSENTER_STATUS;
			logger.debug("<-----" + sql);
			dbUtility.createPreparedStatement(sql);
			String arr[] = { date, userId, witnessTxnId };
			updateFlag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			System.out.println("error in regCompCheckerDAO : updateConsenterStatus" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection regCompCheckerDAO : updateConsenterStatus " + e.getStackTrace());
			}
		}
		return updateFlag;
	}

	/**
	 * @param regInitId
	 * @param witnessTxnId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCompleteConsenterDetails(String regInitId, String witnessTxnId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = RegCompCheckerSQL.GET_CONSENTER_COMPLETE_DETAILS;
			dbUtility.createPreparedStatement(query);
			String arr[] = { regInitId, witnessTxnId };
			list = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getCompleteConsenterDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection " + e.getStackTrace());
			}
		}
		logger.debug("Size of Consenter List in DAO" + list.size());
		return list;
	}

	/**
	 * @param rDTO
	 * @param date
	 * @param createdBy
	 * @return
	 * @throws Exception
	 */
	public boolean saveConsenterDetails(RegCompCheckerDTO rDTO, String date, String createdBy) throws Exception {
		boolean tmpFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCompCheckerSQL.GET_PYMT_PROP_SEQ;
			//sql = "SELECT IGRS_REG_PROP_PMNT_LNKG_SEQ.NEXTVAL FROM DUAL";
			String num = dbUtility.executeQry(sql);
			//	logger.debug("<-----"+sql);
			//	logger.debug("Witness Txn Number"+num);
			rDTO.setWitnessTxnNummber(num);
			sql = RegCompCheckerSQL.INSERT_CONSENTER_DETAILS;
			//	logger.debug("<-----"+sql);
			dbUtility.createPreparedStatement(sql);
			String temp[] = new String[16];
			temp[0] = num;
			temp[1] = rDTO.getRegInitNumber();
			temp[2] = rDTO.getWitFirstName();
			temp[3] = rDTO.getWitMiddleName();
			temp[4] = rDTO.getWitLastName();
			//	logger.debug("<---------"+rDTO.getWitGender());
			if (rDTO.getWitGender() != null) {
				if (rDTO.getWitGender().equalsIgnoreCase("Male")) {
					temp[5] = "M";
				} else if (rDTO.getWitGender().equalsIgnoreCase("Female")) {
					temp[5] = "F";
				} else {
					temp[5] = "";
				}
			}
			//temp[6] = rDTO.getWitAge();
			temp[6] = rDTO.getWitAge();
			temp[7] = rDTO.getWitFatherName();
			temp[8] = rDTO.getWitMotherName();
			temp[9] = rDTO.getWitSpouseName();
			temp[10] = rDTO.getWitNationality();
			temp[11] = rDTO.getWitAddress();
			temp[12] = rDTO.getWitPhoneNumber();
			temp[13] = createdBy;
			temp[14] = date;
			temp[15] = rDTO.getRelationshipWit();
			tmpFlag = dbUtility.executeUpdate(temp);
		} catch (Exception e) {
			System.out.println("error in regCompCheckerDAO : saveConsenterDetails" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out.println("error in closing connection regCompCheckerDAO : saveConsenterDetails " + e.getStackTrace());
			}
		}
		return tmpFlag;
	}

	/**
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public ArrayList getConsenterDet(String regNumber) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			sql = RegCompCheckerSQL.GET_CONSENTER_DETAILS+"'"+regNumber+"'";
			//dbUtility.createPreparedStatement(sql);
			System.out.println(sql);
			list = dbUtility.executeQuery(sql);
			System.out.println(list.size());*/
			sql = RegCompCheckerSQL.GET_CONSENTER_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(new String[] { regNumber });
		} catch (Exception e) {
			logger.error("" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * @param rdto
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertSrSignDetails(RegCompCheckerDTO rdto, String userId) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String arr[] = { rdto.getFileNameSrSign(), rdto.getParentPathSrSign() + "/" + rdto.getFileNameSrSign(), userId, rdto.getRegInitNumber() };
			sql = RegCompCheckerSQL.INSERT_SR_SIGN_DETAILS;
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("error in insertSrSignDetails" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection insertSrSignDetails" + e.getStackTrace());
			}
		}
		return flag;
	}

	/*public String getDeedIdCompletion(String regInitId)
	{
		String deedId = "";
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DEED_ID+"'"+regInitId+"'";
			deedId = dbUtility.executeQry(query);
			String query=RegCompCheckerSQL.GET_DEED_ID_COMPLETION;
			dbUtility.createPreparedStatement(query);
			deedId = dbUtility.executeQry(new String[]{regInitId});
			
		} catch (Exception exception) {
			logger.debug("Exception in getDeedId" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getDeedId"+e.getStackTrace());
			}
			
		}
		return deedId;
	}
	
	public String getInstIdCompletion(String regInitId)
	{
		String deedId = "";
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DEED_ID+"'"+regInitId+"'";
			deedId = dbUtility.executeQry(query);
			String query=RegCompCheckerSQL.GET_INST_ID_COMPLETION;
			dbUtility.createPreparedStatement(query);
			deedId = dbUtility.executeQry(new String[]{regInitId});
			
		} catch (Exception exception) {
			logger.debug("Exception in getDeedId" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getDeedId"+e.getStackTrace());
			}
			
		}
		return deedId;
	}*/
	public ArrayList getRelationshipList(String gender, String language) {
		ArrayList mainList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				sql = RegCommonSQL.GET_RELATIONSHIP_LIST;
			} else {
				sql = RegCommonSQL.GET_RELATIONSHIP_LIST_HI;
			}
			dbUtility.createPreparedStatement(sql);
			String param[] = { gender };
			ArrayList list = dbUtility.executeQuery(param);
			// mainList = new ArrayList();
			ArrayList subList = null;
			RegCompCheckerDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new RegCompCheckerDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO:getRelationshipList in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * @param linkHistoryDetails
	 * @return
	 * @throws Exception
	 */
	public String getPropertyType(String propId) {
		String deedId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { propId };
			sql = RegCommonMkrSQL.PROP_ID;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			deedId = dbUtility.executeQry(regArr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return deedId;
	}

	public boolean upadtePinDetails(ArrayList linkHistoryDetails) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			Iterator itr = linkHistoryDetails.iterator();
			while (itr.hasNext()) {
				dbUtility = new DBUtility();
				RegCompCheckerDTO rdto = (RegCompCheckerDTO) itr.next();
				String transactionType = rdto.getTransactionType();
				transactionType = transactionType == null ? "" : transactionType;
				if (transactionType.equalsIgnoreCase("F")) {
					sql = RegCompCheckerSQL.UPDATE_FINAL_PIN_STATUS;
					dbUtility.createPreparedStatement(sql);
					String arr[] = { rdto.getOldPropertyId() };
					flag = dbUtility.executeUpdate(arr);
					dbUtility.closeConnection();
				} else {
					double area = getOldArea(rdto.getOldPropertyId());
					double totalArea = 0;
					String id3 = getPropertyType(rdto.getOldPropertyId());
					if ("1".equalsIgnoreCase(id3)) {
						totalArea = totalArea + getPlotArea(rdto.getOldPropertyId());
					} else if ("3".equalsIgnoreCase(id3)) {
						totalArea = totalArea + getAgriArea(rdto.getOldPropertyId());
					}
					if ("2".equalsIgnoreCase(id3)) {
						dbUtility = new DBUtility();
						String query = RegCompCheckerSQL.GET_PROPRTY_DETIALS_FOR_PIN_OLD;
						dbUtility.createPreparedStatement(query);
						String arr[] = { rdto.getOldPropertyId() };
						ArrayList list = dbUtility.executeQuery(arr);
						dbUtility.closeConnection();
						for (int i = 0; i < list.size(); i++) {
							ArrayList subList = (ArrayList) list.get(i);
							String disttId = (String) subList.get(0);
							String tehId = (String) subList.get(1);
							String propTypeId = (String) subList.get(2);
							String propId = (String) subList.get(3);
							/*if("2".equalsIgnoreCase(propId))
							{
								continue;
							}*/
							if (disttId.length() == 1) {
								disttId = "0" + disttId;
							}
							if (tehId.length() == 1)
								tehId = "00" + tehId;
							else if (tehId.length() == 2)
								tehId = "0" + tehId;
							else
								tehId = tehId;
							int randomNumber = (int) (Math.random() * 9000) + 1000;
							logger.debug("^^^^^^^^^^^^" + randomNumber);
							String random = String.valueOf(randomNumber);
							String pinNo = disttId + tehId + propTypeId + random;
							logger.debug("^^^^^^^PIN NUMBER" + pinNo);
							dbUtility = new DBUtility();
							String sql = RegCompCheckerSQL.UPADTE_PIN_DETAILS;
							dbUtility.createPreparedStatement(sql);
							String pinArr[] = { pinNo, "A", propId };
							flag = dbUtility.executeUpdate(pinArr);
							dbUtility.closeConnection();
						}
					} else {
						if (area < totalArea) {
							dbUtility = new DBUtility();
							String query = RegCompCheckerSQL.GET_PROPRTY_DETIALS_FOR_PIN_OLD;
							dbUtility.createPreparedStatement(query);
							String arr[] = { rdto.getOldPropertyId() };
							ArrayList list = dbUtility.executeQuery(arr);
							dbUtility.closeConnection();
							for (int i = 0; i < list.size(); i++) {
								ArrayList subList = (ArrayList) list.get(i);
								String disttId = (String) subList.get(0);
								String tehId = (String) subList.get(1);
								String propTypeId = (String) subList.get(2);
								String propId = (String) subList.get(3);
								/*if("2".equalsIgnoreCase(propId))
								{
									continue;
								}*/
								if (disttId.length() == 1) {
									disttId = "0" + disttId;
								}
								if (tehId.length() == 1)
									tehId = "00" + tehId;
								else if (tehId.length() == 2)
									tehId = "0" + tehId;
								else
									tehId = tehId;
								int randomNumber = (int) (Math.random() * 9000) + 1000;
								logger.debug("^^^^^^^^^^^^" + randomNumber);
								String random = String.valueOf(randomNumber);
								String pinNo = disttId + tehId + propTypeId + random;
								logger.debug("^^^^^^^PIN NUMBER" + pinNo);
								dbUtility = new DBUtility();
								String sql = RegCompCheckerSQL.UPADTE_PIN_DETAILS;
								dbUtility.createPreparedStatement(sql);
								String pinArr[] = { pinNo, "A", propId };
								flag = dbUtility.executeUpdate(pinArr);
								dbUtility.closeConnection();
							}
						} else {
							dbUtility = new DBUtility();
							sql = RegCompCheckerSQL.UPDATE_FINAL_PIN_STATUS;
							dbUtility.createPreparedStatement(sql);
							String arr[] = { rdto.getOldPropertyId() };
							flag = dbUtility.executeUpdate(arr);
							dbUtility.closeConnection();
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("error in upadtePinDetails" + e.getStackTrace());
		} finally {
			try {
				//	dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection upadtePinDetails" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getInstId(String regInitId) {
		String deedId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.GET_DEED_ID+"'"+regInitId+"'";
			deedId = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.GET_INST_ID;
			dbUtility.createPreparedStatement(query);
			deedId = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getInstId" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getInstId" + e.getStackTrace());
			}
		}
		return deedId;
	}

	/**
	 * @param regInitNumber
	 * @return boolean
	 * @throws Exception
	 */
	public boolean pinGeneration(String regInitNumber, String deedId, String userId) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList subList = new ArrayList();
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_PROPRTY_DETIALS_FOR_PIN;
			dbUtility.createPreparedStatement(query);
			String arr[] = { regInitNumber };
			list = dbUtility.executeQuery(arr);
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				String disttId = (String) subList.get(0);
				String tehId = (String) subList.get(1);
				String propTypeId = (String) subList.get(2);
				String propId = (String) subList.get(3);
				if ("2".equalsIgnoreCase(propId)) {
					continue;
				}
				if (disttId.length() == 1) {
					disttId = "0" + disttId;
				}
				if (tehId.length() == 1)
					tehId = "00" + tehId;
				else if (tehId.length() == 2)
					tehId = "0" + tehId;
				else
					tehId = tehId;
				int randomNumber = (int) (Math.random() * 9000) + 1000;
				logger.debug("^^^^^^^^^^^^" + randomNumber);
				String random = String.valueOf(randomNumber);
				String pinNo = disttId + tehId + propTypeId + random;
				logger.debug("^^^^^^^PIN NUMBER" + pinNo);
				String sql = RegCompCheckerSQL.UPADTE_PIN_DETAILS;
				dbUtility.createPreparedStatement(sql);
				String pinArr[] = { pinNo, "A", propId };
				flag = dbUtility.executeUpdate(pinArr);
				if (flag) {
					//TODO - get claimants and sms
					if (deedId.equals(RegCompCheckerConstant.CERTIFICATION_OF_SALE) || deedId.equals(RegCompCheckerConstant.CONVEYANCE_DEED) || deedId.equals(RegCompCheckerConstant.GIFT_DEED) || deedId.equals(RegCompCheckerConstant.LEASE_DEED) || deedId.equals(RegCompCheckerConstant.RELEASE_DEED)) {/*
																																				String queryMob = RegCompCheckerSQL.GET_MOBILE_NUMBERS;
																																				dbUtility.createPreparedStatement(queryMob);
																																				String mobArr[] ={regInitNumber,
																																						propId};
																																				ArrayList mobList = dbUtility.executeQuery(mobArr);
																																				for(int k= 0 ; k < mobList.size(); k++)
																																				{
																																					ArrayList subListMob = (ArrayList)mobList.get(k);
																																					String mobNumber = (String)subListMob.get(0);
																																					logger.debug("****** mob number ********"+mobNumber);
																																					String content = "Your pin number for property Id "+propId+" is: "+pinNo;
																																					logger.debug("****** sms content ********"+content);
																																					String sqlSms = RegCompCheckerSQL.INSERT_SMS_DETAILS;
																																					dbUtility.createPreparedStatement(sqlSms);
																																					String smsArr[] = {mobNumber,
																																							userId,
																																							content};
																																					flag = dbUtility.executeUpdate(smsArr);
																																					logger.debug("sms flag*******************"+flag);
																																				}
																																			*/} else if (deedId.equals(RegCompCheckerConstant.EXCHANGE_DEED)) {
						/*String queryMob = RegCompCheckerSQL.GET_MOBILE_NUMBER_FOR_EXCHANGE_DEED;
						dbUtility.createPreparedStatement(queryMob);
						String mobArr[] ={propId,
								regInitNumber,
								regInitNumber};
						ArrayList mobList = dbUtility.executeQuery(mobArr);
						for(int k= 0 ; k < mobList.size(); k++)
						{
							ArrayList subListMob = (ArrayList)mobList.get(k);
							String mobNumber = (String)subListMob.get(0);
							logger.debug("****** mob number ********"+mobNumber);
							String content = "Your pin number for property Id "+propId+" is: "+pinNo;
							logger.debug("****** sms content ********"+content);
							String sqlSms = RegCompCheckerSQL.INSERT_SMS_DETAILS;
							dbUtility.createPreparedStatement(sqlSms);
							String smsArr[] = {mobNumber,
									userId,
									content};
							flag = dbUtility.executeUpdate(smsArr);
							logger.debug("sms flag*******************"+flag);
						}*/
						flag = true;
					} else if (deedId.equals(RegCompCheckerConstant.PARTITION_DEED_PROPERTY)) {
						/*String queryMob = RegCompCheckerSQL.GET_MOBILE_NUMBER_FOR_EXCHANGE_DEED;
						dbUtility.createPreparedStatement(queryMob);
						String mobArr[] ={
								propId,
								regInitNumber,
								regInitNumber};
						ArrayList mobList = dbUtility.executeQuery(mobArr);
						for(int k= 0 ; k < mobList.size(); k++)
						{
							ArrayList subListMob = (ArrayList)mobList.get(k);
							String mobNumber = (String)subListMob.get(0);
							logger.debug("****** mob number ********"+mobNumber);
							String content = "Your pin number for property Id "+propId+" is: "+pinNo;
							logger.debug("****** sms content ********"+content);
							String sqlSms = RegCompCheckerSQL.INSERT_SMS_DETAILS;
							dbUtility.createPreparedStatement(sqlSms);
							String smsArr[] = {mobNumber,
									userId,
									content};
							flag = dbUtility.executeUpdate(smsArr);
							logger.debug("sms flag*******************"+flag);
						
						}*/
						flag = true;
					}
				}
			}
		} catch (Exception exception) {
			logger.debug("Exception in pinGeneration" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection pinGeneration" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * @param regInitNumber
	 * @return
	 * @throws Exception
	 */
	public ArrayList pinDetails(String regInitNumber) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_PIN_DETAILS_FOR_PRINT;
			dbUtility.createPreparedStatement(query);
			String arr[] = { regInitNumber };
			list = dbUtility.executeQuery(arr);
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				RegCompCheckerDTO rdto = new RegCompCheckerDTO();
				rdto.setDistName((String) subList.get(0));
				rdto.setTehsilName((String) subList.get(1));
				rdto.setPropertyTypeName((String) subList.get(2));
				rdto.setPropertyId((String) subList.get(3));
				rdto.setPinNumber((String) subList.get(4));
				mainList.add(rdto);
			}
		} catch (Exception exception) {
			logger.debug("Exception in pinDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection pinDetails" + e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public boolean seqChk(String currDate) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_SEQ_DETAILS;
			dbUtility.createPreparedStatement(query);
			String year = currDate.substring(8);
			logger.debug("YEAR" + year);
			String arg =year;
			//String arg = "%-"+14+"%";
			String arr[] = { arg, arg };
			logger.debug("qry" + query);
			String val = dbUtility.executeQry(arr);
			if (val.equals("0")) {
				flag = true;
			}
		} catch (Exception exception) {
			logger.debug("Exception in pinDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection pinDetails" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean addHoldRemark(RegCompCheckerDTO formDTO) {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.UPDATE_HOLD_STATUS_REMARKS;
			dbUtility.createPreparedStatement(query);
			String arr[] = { formDTO.getHoldDocumentName(), formDTO.getHoldDocumentPath(), formDTO.getUnholdRemarks(), formDTO.getRegInitNumber() };
			logger.debug("qry" + query);
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception exception) {
			logger.debug("Exception in pinDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection pinDetails" + e.getStackTrace());
			}
		}
		return flag;
	}

	public String getHoldRemarks(String regNum, String holdId) {
		String remark = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_HOLD_REMARKS;
			dbUtility.createPreparedStatement(query);
			String arr[] = { regNum, holdId };
			logger.debug("qry" + query);
			remark = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in pinDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection pinDetails" + e.getStackTrace());
			}
		}
		return remark;
	}

	public boolean UpdateRegistrationCompletionStatusRefusal(String regInitId, String status, String date, String userId, String remarks) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_REGISTRATION_STATUS_REFUSAL;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { status, userId, remarks, regInitId };
			flag = dbUtility.executeUpdate(arr);
			UpdateTImeEtoken(regInitId, status);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean checkImpound(String regNo) {
		String remark = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.CHECK_IMPOUND;
			dbUtility.createPreparedStatement(query);
			String arr[] = { regNo };
			logger.debug("qry" + query);
			remark = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in pinDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection pinDetails" + e.getStackTrace());
			}
		}
		if (remark.equalsIgnoreCase(regNo)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPropertyCaveats(String propId) {
		boolean flag = false;
		ArrayList list = new ArrayList();
		logger.debug(propId.length());
		if (propId.length() == 15)
			propId = "0" + propId;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.CHECK_PROPERTY_STATUS;
			dbUtility.createPreparedStatement(query);
			String arr[] = { propId };
			logger.debug("qry" + query);
			//status = dbUtility.executeQry(arr);
			list = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in pinDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection pinDetails" + e.getStackTrace());
			}
		}
		for (int i = 0; i < list.size(); i++) {
			ArrayList subList = (ArrayList) list.get(i);
			String cavStatus = (String) subList.get(0);
			if ("LOGGED".equalsIgnoreCase(cavStatus)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public ArrayList checkBookedSlot(String regNo) {
		logger.debug("<--------- reg no " + regNo);
		ArrayList list = null;
		String arr[] = new String[2];
		DBUtility dbUtility = null;
		try {
			if (regNo.length() == 11) {
				arr[0] = regNo;
				regNo = "0" + regNo;
				arr[1] = regNo;
			} else {
				if (regNo.startsWith("0")) {
					logger.debug("strats with 0");
					arr[0] = regNo;
					regNo = regNo.substring(1);
					arr[1] = regNo;
				} else {
					arr[0] = regNo;
					arr[1] = regNo;
				}
			}
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.CHECK_SLOT_BOOKING;
			dbUtility.createPreparedStatement(query);
			logger.debug("qry" + query);
			list = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in pinDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection pinDetails" + e.getStackTrace());
			}
		}
		return list;
	}

	public boolean checkRegNumber(String regNumber) {
		String dregNumber = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.CHECK_REG_NUMBER;
			dbUtility.createPreparedStatement(query);
			String arr[] = { regNumber };
			logger.debug("qry" + query);
			dregNumber = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in pinDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection pinDetails" + e.getStackTrace());
			}
		}
		if (regNumber.equalsIgnoreCase(dregNumber)) {
			return true;
		} else {
			return false;
		}
	}

	public String getSystemDate() {
		String sysDate = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = SealsSQL.GET_SYS_DATE;
			dbUtility.createStatement();
			sysDate = dbUtility.executeQry(sql);
		} catch (Exception exception) {
			logger.debug("Exception in getSystemDate" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return sysDate;
	}

	public String getSrDetails(String userId) throws Exception {
		String srName = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL = SealsSQL.GET_SR_DETAILS;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = { userId };
			srName = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getSrDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return srName;
	}

	public String getofficeName(String officeId, String lang) throws Exception {
		String offcName = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL = "";
			if (lang.equalsIgnoreCase("en"))
				SQL = SealsSQL.GET_OFFICE_NAME;
			else
				SQL = SealsSQL.GET_OFFICE_NAME_HI;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = { officeId };
			offcName = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getofficeName" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return offcName;
	}

	public ArrayList getRegStampDuty(String regNumber) throws Exception {
		ArrayList dutyDetls = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL = RegCompCheckerSQL.GET_REG_STAMP_DETLS;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = { regNumber };
			dutyDetls = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getofficeName" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return dutyDetls;
	}

	public String getDeedDocPath(String regNumber) throws Exception {
		String deedDocPath = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL = RegCompCheckerSQL.GET_DEED_DOC_PATH;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = { regNumber };
			deedDocPath = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getofficeName" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return deedDocPath;
	}

	public ArrayList getEstampForRegistration(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_ESTAMP_DETLS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getEstampForRegistration" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getEstampForRegistration" + e.getStackTrace());
			}
		}
		return list;
	}

	public String getDutyTxnId(String regInitId) throws Exception {
		String dutyTxnId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_DUTY_TXN_ID;
			dbUtility.createPreparedStatement(query);
			dutyTxnId = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getEstampForRegistration" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getEstampForRegistration" + e.getStackTrace());
			}
		}
		return dutyTxnId;
	}

	public ArrayList getPropertyImages(String regInitId) throws Exception {
		ArrayList propImages = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL = SealsSQL.GET_PROP_IMAGES;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = { regInitId };
			propImages = dbUtility.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getSrDetails" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return propImages;
	}

	public boolean updateFinalDocGenChk(String regNumber) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL = RegCompCheckerSQL.UPADTE_FINAL_DOC_GEN_CHK;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = { regNumber };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception exception) {
			logger.debug("Exception in updateFinalDocGenChk" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error(" exception in  updateFinalDocGenChk" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updatePrintFlag(String regNumber) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL = RegCompCheckerSQL.UPADTE_PRINT_FLAG;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = { regNumber };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception exception) {
			logger.debug("Exception in updateFinalDocGenChk" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error(" exception in  updateFinalDocGenChk" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updatePrintFlag(String regNumber, String status) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL = RegCompCheckerSQL.UPADTE_FLAG_PRINTED;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = { status, regNumber };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception exception) {
			logger.debug("Exception in updateFinalDocGenChk" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error(" exception in  updateFinalDocGenChk" + e.getStackTrace());
			}
		}
		return flag;
	}

	public String checkFinalDocGenFlag(String regInitId) throws Exception {
		String status = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
			status = dbUtility.executeQry(query);*/
			String query = RegCompCheckerSQL.CHECK_FINAL_DOC_GEN_FLAG;
			dbUtility.createPreparedStatement(query);
			status = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in checkFinalDocGenFlag" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection checkFinalDocGenFlag" + e.getStackTrace());
			}
		}
		return status;
	}

	public ArrayList getDeedDocDetails(String regNum) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regNum };
			sql = RegCommonMkrSQL.GET_DEED_DOC_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(regArr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	public ArrayList getEstampCodeDetails(String regNum) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regNum };
			sql = RegCompCheckerSQL.GET_ESTAMP_CODE_DETLS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(regArr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	public String checkExecutionSealApllied(String regInitNumber) {
		String flag = "N";
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regInitNumber };
			sql = RegCompCheckerSQL.GET_EXECUTION_SEAL_DETLS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(regArr);
			if (list != null && list.size() > 0) {
				flag = "Y";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	public String checkRegSealApllied(String regInitNumber) {
		String flag = "N";
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regInitNumber };
			sql = RegCompCheckerSQL.GET_REG_FEE_SEAL_DETLS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(regArr);
			if (list != null && list.size() > 0) {
				flag = "Y";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	public ArrayList getPropertyImagesCertificate(String regInitId) {
		ArrayList retList = null;
		ArrayList propertyList = executeQry(RegCompCheckerSQL.ANEXXURE_PROPERTY_DETAILS_NEW, new String[] { regInitId });
		if (propertyList != null && propertyList.size() > 0) {
			retList = new ArrayList();
			for (int i = 0; i < propertyList.size(); i++) {
				ArrayList subList = (ArrayList) propertyList.get(i);
				String propertyId = (String) subList.get(0);
				String path1 = (String) subList.get(1);
				String path2 = (String) subList.get(2);
				String mpCostFlag = (String) subList.get(3);
				if (path1 != null && !path1.equalsIgnoreCase("")) {
					retList.add(path1);
				}
				if (path2 != null && !path2.equalsIgnoreCase("")) {
					if ("Y".equalsIgnoreCase(mpCostFlag)) {
						PropertiesFileReader proper;
						String FILE_UPLOAD_PATH = "";
						try {
							proper = PropertiesFileReader.getInstance("resources.igrs");
							FILE_UPLOAD_PATH = proper.getValue("igrs_upload_path") + File.separator + "03" + File.separator + propertyId + File.separator;
							ZipFile zip = new ZipFile(path2);
							zip.extractAll(FILE_UPLOAD_PATH);
							File folder = new File(FILE_UPLOAD_PATH);
							File[] listOfFiles = folder.listFiles();
							for (int z = 0; z < listOfFiles.length; z++) {
								String tempPath = listOfFiles[z].getPath();
								retList.add(tempPath);
							}
						} catch (Exception e1) {
							logger.debug(e1.getMessage(), e1);
						}
					} else {
						retList.add(path2);
					}
				}
			}
		}
		return retList;
	}

	public ArrayList<Images> getAnnexureImages(String regInitId, String language, String userId) {
		ArrayList<Images> list = new ArrayList<Images>();
		// for patries
		try {
			ArrayList propertyList = executeQry(RegCompCheckerSQL.ANEXXURE_PROPERTY_DETAILS, new String[] { regInitId });
			if (propertyList != null && propertyList.size() > 0) {
				for (int i = 0; i < propertyList.size(); i++) {
					ArrayList subList = (ArrayList) propertyList.get(i);
					String propertyId = (String) subList.get(0);
					String path1 = (String) subList.get(1);
					ArrayList excuteList = getPartyDetails(regInitId, propertyId, language);
					String name = (String) excuteList.get(0);
					String sign = (String) excuteList.get(1);
					String address = getPropertyAddress(propertyId, language);
					String date = getDateAnnexure(regInitId);
					String disclaimer = "";
					if ("en".equalsIgnoreCase(language)) {
						disclaimer = "I declare that I will be responsible for any issue regarding details and title of property.";
					} else {
						disclaimer = "मैं घोषणा करता हूँ, कि संपत्ति पहचान के मामले में  किसी भी  प्रकार की त्रुटि  के लिए जिम्मेदार  रहूँगा|";
					}
					ArrayList kasraList = khasraTable(propertyId);
					String place = getPlace(userId, language);
					if (path1 != null && !path1.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("property");
						img.setType("jpg");
						img.setExecutantNameAddress(name);
						img.setPropertyId(propertyId);
						img.setSignatureLocation(sign);
						img.setPropertyAddress(address);
						img.setDate(date);
						img.setTableEntries(kasraList);
						img.setPlace(place);
						img.setLocation(path1);
						img.setDisclaimer(disclaimer);
						list.add(img);
					}
					String path2 = (String) subList.get(2);
					if (path2 != null && !path2.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("property");
						img.setType("jpg");
						img.setLocation(path2);
						img.setExecutantNameAddress(name);
						img.setPropertyId(propertyId);
						img.setSignatureLocation(sign);
						img.setPropertyAddress(address);
						img.setDate(date);
						img.setTableEntries(kasraList);
						img.setPlace(place);
						img.setDisclaimer(disclaimer);
						list.add(img);
					}
					String path3 = (String) subList.get(3);
					if (path3 != null && !path3.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("property");
						img.setType("jpg");
						img.setLocation(path3);
						img.setExecutantNameAddress(name);
						img.setPropertyId(propertyId);
						img.setSignatureLocation(sign);
						img.setPropertyAddress(address);
						img.setDate(date);
						img.setTableEntries(kasraList);
						img.setPlace(place);
						img.setDisclaimer(disclaimer);
						list.add(img);
					}
					String path4 = (String) subList.get(4);
					String mpCostFlag = (String) subList.get(7);
					if ("Y".equalsIgnoreCase(mpCostFlag)) {
						PropertiesFileReader proper;
						String FILE_UPLOAD_PATH = "";
						try {
							proper = PropertiesFileReader.getInstance("resources.igrs");
							FILE_UPLOAD_PATH = proper.getValue("igrs_upload_path") + File.separator + "03" + File.separator + propertyId + File.separator;
						} catch (Exception e1) {
							logger.debug(e1.getMessage(), e1);
						}
						ZipFile zip = new ZipFile(path4);
						zip.extractAll(FILE_UPLOAD_PATH);
						File folder = new File(FILE_UPLOAD_PATH);
						File[] listOfFiles = folder.listFiles();
						for (int z = 0; z < listOfFiles.length; z++) {
							Images img = new Images();
							String tempPath = listOfFiles[z].getPath();
							img.setImageType("property");
							img.setType("jpg");
							img.setLocation(tempPath);
							img.setExecutantNameAddress(name);
							img.setPropertyId(propertyId);
							img.setSignatureLocation(sign);
							img.setPropertyAddress(address);
							img.setDate(date);
							img.setTableEntries(kasraList);
							img.setPlace(place);
							img.setDisclaimer(disclaimer);
							list.add(img);
						}
					} else {
						if (path4 != null && !path4.equalsIgnoreCase("")) {
							Images img = new Images();
							img.setImageType("property");
							img.setType("jpg");
							img.setLocation(path4);
							img.setExecutantNameAddress(name);
							img.setPropertyId(propertyId);
							img.setSignatureLocation(sign);
							img.setPropertyAddress(address);
							img.setDate(date);
							img.setTableEntries(kasraList);
							img.setPlace(place);
							img.setDisclaimer(disclaimer);
							list.add(img);
						}
					}
					String path5 = (String) subList.get(5);
					if (path5 != null && !path5.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("property");
						img.setType("jpg");
						img.setLocation(path5);
						img.setExecutantNameAddress(name);
						img.setPropertyId(propertyId);
						img.setSignatureLocation(sign);
						img.setPropertyAddress(address);
						img.setDate(date);
						img.setTableEntries(kasraList);
						img.setPlace(place);
						img.setDisclaimer(disclaimer);
						list.add(img);
					}
					String path6 = (String) subList.get(6);
					if (path6 != null && !path6.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("property");
						img.setType("jpg");
						img.setLocation(path6);
						img.setExecutantNameAddress(name);
						img.setPropertyId(propertyId);
						img.setSignatureLocation(sign);
						img.setPropertyAddress(address);
						img.setDate(date);
						img.setTableEntries(kasraList);
						img.setPlace(place);
						img.setDisclaimer(disclaimer);
						list.add(img);
					}
					ArrayList propertyOtherList = executeQry(RegCompCheckerSQL.ANEXXURE_PROPERTY_DETAILS_OTHER, new String[] { propertyId });
					if (propertyOtherList != null && propertyOtherList.size() > 0) {
						for (int j = 0; j < propertyOtherList.size(); j++) {
							ArrayList subArrayList = (ArrayList) propertyOtherList.get(j);
							String path = (String) subArrayList.get(0);
							if (path != null && !path.equalsIgnoreCase("")) {
								Images img = new Images();
								img.setImageType("property");
								img.setType("jpg");
								img.setLocation(path);
								img.setExecutantNameAddress(name);
								img.setPropertyId(propertyId);
								img.setSignatureLocation(sign);
								img.setPropertyAddress(address);
								img.setDate(date);
								img.setTableEntries(kasraList);
								img.setPlace(place);
								img.setDisclaimer(disclaimer);
								list.add(img);
							}
						}
					}
				}
			}
			ArrayList partyList = executeQry(RegCompCheckerSQL.ANEXXURE_PARTY_DETAILS, new String[] { regInitId });
			if (partyList != null && partyList.size() > 0) {
				for (int i = 0; i < partyList.size(); i++) {
					ArrayList subList = (ArrayList) partyList.get(i);
					String partyId = (String) subList.get(0);
					String path1 = (String) subList.get(1);
					if (path1 != null && !path1.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path1);
						list.add(img);
					}
					String path2 = (String) subList.get(2);
					if (path2 != null && !path2.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path2);
						list.add(img);
					}
					String path3 = (String) subList.get(3);
					if (path3 != null && !path3.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path3);
						list.add(img);
					}
					String path4 = (String) subList.get(4);
					if (path4 != null && !path4.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path4);
						list.add(img);
					}
					String path5 = (String) subList.get(5);
					if (path5 != null && !path5.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path5);
						list.add(img);
					}
					String path6 = (String) subList.get(6);
					if (path6 != null && !path6.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path6);
						list.add(img);
					}
					String path7 = (String) subList.get(7);
					if (path7 != null && !path7.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path7);
						list.add(img);
					}
					String path8 = (String) subList.get(8);
					if (path8 != null && !path8.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path8);
						list.add(img);
					}
					ArrayList partyOtherList = executeQry(RegCompCheckerSQL.ANEXXURE_PARTY_DETAILS_OTHER, new String[] { partyId });
					if (partyOtherList != null && partyOtherList.size() > 0) {
						for (int j = 0; j < partyOtherList.size(); j++) {
							ArrayList subArrayList = (ArrayList) partyOtherList.get(j);
							String path = (String) subArrayList.get(0);
							if (path != null && !path.equalsIgnoreCase("")) {
								Images img = new Images();
								img.setImageType("ids");
								img.setType("jpg");
								img.setLocation(path);
								list.add(img);
							}
						}
					}
				}
			}
			ArrayList OtherList = executeQry(RegCompCheckerSQL.ANEXXURE_PARTY_OTHER_UPLOAD, new String[] { regInitId });
			if (OtherList != null && OtherList.size() > 0) {
				for (int j = 0; j < OtherList.size(); j++) {
					ArrayList subArrayList = (ArrayList) OtherList.get(j);
					String path = (String) subArrayList.get(0);
					if (path != null && !path.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path);
						list.add(img);
					}
				}
			}
			ArrayList constrList = executeQry(RegCompCheckerSQL.ANEXXURE_CONSENTER_OTHER_UPLOAD, new String[] { regInitId });
			if (constrList != null && constrList.size() > 0) {
				for (int j = 0; j < constrList.size(); j++) {
					ArrayList subArrayList = (ArrayList) constrList.get(j);
					String path = (String) subArrayList.get(0);
					if (path != null && !path.equalsIgnoreCase("")) {
						Images img = new Images();
						img.setImageType("ids");
						img.setType("jpg");
						img.setLocation(path);
						list.add(img);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getStackTrace());
			logger.debug(e.getMessage(), e);
			logger.debug("error in annexure list");
		}
		return list;
	}

	private ArrayList executeQry(String query, String[] arr) {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = query;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(arr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	public String getSubjectNameDao(String userId) {
		String subjectName = "";
		String arr[] = { userId };
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(RegCompCheckerSQL.GET_SUBJECT_NAME);
			subjectName = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getSubjectNameDao" + exception);
		} finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x.getMessage(), x);
				logger.debug(x.getStackTrace());
			}
		}
		return subjectName;
	}

	public ArrayList<String> getPartyDetails(String regInitId, String propId, String langauge) {
		ArrayList<String> returnList = new ArrayList<String>();
		String query = "";
		if ("en".equalsIgnoreCase(langauge)) {
			query = RegCompCheckerSQL.GET_EXECUTANT_DETAILS;
		} else {
			query = RegCompCheckerSQL.GET_EXECUTANT_DETAILS_HI;
		}
		ArrayList list = executeQry(query, new String[] { regInitId, propId });
		if (list != null && list.size() > 0) {
			ArrayList subList = (ArrayList) list.get(0);
			String applaeteId = (String) subList.get(0);
			if ("2".equalsIgnoreCase(applaeteId)) {
				String name = "";
				if (subList.get(2) != null && !subList.get(2).toString().equalsIgnoreCase("")) {
					name = (String) subList.get(1) + " " + (String) subList.get(2) + " " + (String) subList.get(3) + ", " + (String) subList.get(7) + ", " + (String) subList.get(10) + ", " + (String) subList.get(11) + ", " + (String) subList.get(9);
				} else {
					name = (String) subList.get(1) + " " + (String) subList.get(3) + ", " + (String) subList.get(7) + ", " + (String) subList.get(10) + ", " + (String) subList.get(11) + ", " + (String) subList.get(9);
				}
				String sign = "";
				if (subList.get(6) != null && !subList.get(6).toString().equalsIgnoreCase("")) {
					sign = subList.get(6).toString();
				} else {
					sign = "NA";
				}
				returnList.add(name);
				returnList.add(sign);
			} else if ("1".equalsIgnoreCase(applaeteId)) {
				String name = "";
				name = (String) subList.get(4) + ", " + (String) subList.get(12) + ", " + (String) subList.get(10) + ", " + (String) subList.get(11) + ", " + (String) subList.get(9);
				String sign = "";
				if (subList.get(6) != null && !subList.get(6).toString().equalsIgnoreCase("")) {
					sign = subList.get(6).toString();
				} else {
					sign = "NA";
				}
				returnList.add(name);
				returnList.add(sign);
			} else if ("3".equalsIgnoreCase(applaeteId)) {
				String name = "";
				if (subList.get(5) != null && !subList.get(5).toString().equalsIgnoreCase("")) {
					name = (String) subList.get(5) + ", " + (String) subList.get(8) + ", " + (String) subList.get(10) + ", " + (String) subList.get(11) + ", " + (String) subList.get(9);
				} else {
					name = "----" + ", " + (String) subList.get(8) + ", " + (String) subList.get(10) + ", " + (String) subList.get(11) + ", " + (String) subList.get(9);
				}
				String sign = "";
				if (subList.get(6) != null && !subList.get(6).toString().equalsIgnoreCase("")) {
					sign = subList.get(6).toString();
				} else {
					sign = "NA";
				}
				returnList.add(name);
				returnList.add(sign);
			}
		}
		return returnList;
	}

	public String getPropertyAddress(String propId, String langauge) {
		String status = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
			status = dbUtility.executeQry(query);*/
			String query = "";
			if ("en".equalsIgnoreCase(langauge)) {
				query = RegCompCheckerSQL.GET_PROP_DETAILS;
			} else {
				query = RegCompCheckerSQL.GET_PROP_DETAILS_HI;
			}
			dbUtility.createPreparedStatement(query);
			status = dbUtility.executeQry(new String[] { propId });
		} catch (Exception exception) {
			logger.debug("Exception in checkFinalDocGenFlag" + exception);
			logger.debug(exception.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection checkFinalDocGenFlag" + e.getStackTrace());
			}
		}
		return status;
	}

	String getPlace(String userId, String langauge) {
		String status = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
			status = dbUtility.executeQry(query);*/
			String query = "";
			if ("en".equalsIgnoreCase(langauge)) {
				query = RegCompCheckerSQL.GET_PLACE;
			} else {
				query = RegCompCheckerSQL.GET_PLACE_HI;
			}
			dbUtility.createPreparedStatement(query);
			status = dbUtility.executeQry(new String[] { userId });
		} catch (Exception exception) {
			logger.debug("Exception in checkFinalDocGenFlag" + exception);
			logger.debug(exception.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection checkFinalDocGenFlag" + e.getStackTrace());
			}
		}
		return status;
	}

	String getDateAnnexure(String regInitId) {
		String status = null;
		try {
			SealsDAO dao = new SealsDAO();
			return dao.getSealDAte(regInitId, "12");
			/*dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
			status = dbUtility.executeQry(query);
			String query="";
			
				query=SealsSQL.GET_SYS_DATE;//RegCompCheckerSQL.GET_SYSDATE;
			
				
			
			dbUtility.createStatement();
			status = dbUtility.executeQry(query);*/
		} catch (Exception exception) {
			logger.debug("Exception in checkFinalDocGenFlag" + exception);
			logger.debug(exception.getStackTrace());
		} finally {
			try {
				//dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection checkFinalDocGenFlag" + e.getStackTrace());
			}
		}
		return status;
	}

	ArrayList khasraTable(String propId) {
		ArrayList<String> returnList = new ArrayList<String>();
		ArrayList list = executeQry(RegCompCheckerSQL.KHASRA_DETAILS, new String[] { propId });
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String details = "";
				ArrayList subList = (ArrayList) list.get(i);
				details = details + (String) subList.get(0) + "#" + (String) subList.get(1) + "#" + (String) subList.get(2) + "#" + (String) subList.get(3) + "#";
				String east = (String) subList.get(4);
				String west = (String) subList.get(5);
				String north = (String) subList.get(6);
				String south = (String) subList.get(7);
				if (east != null && east.length() > 48) {
					east = east.substring(0, 48);
					east = east + "..";
				}
				if (west != null && west.length() > 48) {
					west = west.substring(0, 48);
					west = west + "..";
				}
				if (north != null && north.length() > 48) {
					north = north.substring(0, 48);
					north = north + "..";
				}
				if (south != null && south.length() > 48) {
					south = south.substring(0, 48);
					south = south + "..";
				}
				details = details + east + "#" + west + "#" + north + "#" + south;
				returnList.add(details);
			}
		} else {
			String details = "----#----#----#----#----#----#----#----";
			returnList.add(details);
		}
		return returnList;
	}

	public boolean updatePhotoDetails(String partyId, String photoName, String photoPath) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_PHOTO_PATH_PARTY;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updatePhotoDetailsWitness(String partyId, String photoName, String photoPath) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_PHOTO_PATH_WITNESS;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updatePhotoDetailsConsenter(String partyId, String photoName, String photoPath) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_PHOTO_PATH_CONSENTER;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updateSignDetails(String partyId, String photoName, String photoPath) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_SIGN_PATH_PARTY;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updateSignDetailsWitness(String partyId, String photoName, String photoPath) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_SIGN_PATH_WITNESS;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updateSignDetailsConsenter(String partyId, String photoName, String photoPath) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_SIGN_PATH_CONSENTER;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updateThumbDetails(String partyId, String photoName, String photoPath, String remarks) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_THUMB_PATH_PARTY;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, remarks, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updateThumbDetailsWitness(String partyId, String photoName, String photoPath, String remarks) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_THUMB_PATH_WITNESS;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, remarks, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updateThumbDetailsConsenter(String partyId, String photoName, String photoPath, String remarks) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_THUMB_PATH_CONSENTER;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, remarks, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean updateGovDetails(String partyId, String photoName, String photoPath) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_GOV_PATH_PARTY;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { photoName, photoPath, partyId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public String getConsenterFlag(String regInitId) {
		String subjectName = "";
		String arr[] = { regInitId };
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(RegCompCheckerSQL.GET_CONSNTER_FLAG);
			subjectName = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getSubjectNameDao" + exception);
		} finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x.getMessage(), x);
			}
		}
		return subjectName;
	}

	public String getConsnterAmount(String regInitId) {
		String subjectName = "";
		String arr[] = { regInitId };
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(RegCompCheckerSQL.GET_CONSNTER_AMOUNT);
			subjectName = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getSubjectNameDao" + exception);
		} finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x.getMessage(), x);
			}
		}
		return subjectName;
	}

	public boolean upadteConsenterDuties(RegCompCheckerForm eForm) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_CONSTENTER_DUTIES;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { eForm.getConsStamp(), eForm.getConsJanpad(), eForm.getConsMunicipal(), eForm.getConsUpkar(), eForm.getConsTotal(), eForm.getConsRegFee(), eForm.getConsnterAmount(), eForm.getRegDTO().getRegInitNumber() };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public String getLangauge(String txnId) {
		String[] tid = new String[1];
		tid[0] = txnId;
		String dty = "";
		try {
			DBUtility transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(RegCompCheckerSQL.GET_LANGUAGE);
			dty = transmgtUtil.executeQry(tid);
			transmgtUtil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
		}
		return dty;
	}

	public boolean UpdateTImeEtoken(String regInitId, String status) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.UPDATE_ETOKEN_CHECKER_CHECK;
			dbUtility.createPreparedStatement(sql);
			String status1 = "";
			if ("14".equalsIgnoreCase(status)) {
				status1 = "H";
			}
			String arr[] = { status1, status, regInitId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean insertTimeMakerStart(RegCompCheckerForm eForm, String userId, String sroid) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			ArrayList list;
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.GET_ETOKEN_CHECKER_CHECK;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { eForm.getRegDTO().getRegInitNumber() };
			list = dbUtility.executeQuery(arr);
			if (list != null && list.size() > 0) {
				flag = true;
			} else {
				sql = RegCompCheckerSQL.INSERT_ETOKEN_CHECKER_CHECK;
				dbUtility.createPreparedStatement(sql);
				String arr1[] = { eForm.getRegDTO().getRegInitNumber(), userId, sroid };
				flag = dbUtility.executeUpdate(arr1);
			}
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection UpdateRegistrationCompletionStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean sendMail(String regInitNumber, String content, String userId) {
		boolean flag = true;
		DBUtility dbUtility = null;
		try {
			ArrayList list;
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.GET_EMAIL_ID;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { regInitNumber };
			list = dbUtility.executeQuery(arr);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList subList = (ArrayList) list.get(i);
					String emailId = (String) subList.get(0);
					insertEmailData(userId, emailId, content, regInitNumber);
				}
			}
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean sendSMS(String regInitNumber, String content, String userId) {
		boolean flag = true;
		DBUtility dbUtility = null;
		try {
			ArrayList list;
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.GET_SMS_NUMBER;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { regInitNumber };
			list = dbUtility.executeQuery(arr);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList subList = (ArrayList) list.get(i);
					String number = (String) subList.get(0);
					insertSMSData(userId, number, content);
				}
			}
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public String insertSMSData(String userId, String mobileno, String content) throws Exception {
		String status = new String();
		String[] param = { mobileno, userId, content };
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(RegCompCheckerSQL.INSERT_SMS_CONTENT);
			dbUtility.executeUpdate(param);
			//dbUtility.executeQuery(param);
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return status;
	}

	public String insertEmailData(String userId, String emailId, String content, String regInitID) throws Exception {
		String status = new String();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			//dbUtility.createCallableStatement(RegCompCheckerSQL.EMAIL_DATA);
			dbUtility.createPreparedStatement(RegCompCheckerSQL.EMAIL_DATA);
			String param1[] = new String[] { emailId, "Regarding Regisration apllication Number" + regInitID, content };
			dbUtility.executeUpdate(param1);
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return status;
	}

	public String getDateTIME() {
		String status = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*dbUtility.createStatement();
			String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
			status = dbUtility.executeQry(query);*/
			String query = "";
			query = RegCompCheckerSQL.GET_SYSDATETIME;
			dbUtility.createStatement();
			status = dbUtility.executeQry(query);
		} catch (Exception exception) {
			logger.debug("Exception in checkFinalDocGenFlag" + exception);
			logger.debug(exception.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection checkFinalDocGenFlag" + e.getStackTrace());
			}
		}
		return status;
	}

	public String getPinFlag(String instId) {
		String status = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String arr[] = { instId };
			sql = RegCompCheckerSQL.GET_PIN_FLAG;
			dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			status = dbUtility.executeQry(arr);
			//System.out.println(list.size());
		} catch (Exception e) {
			logger.error("GET_PIN_FLAG:" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection" + e.getStackTrace());
			}
		}
		return status;
	}

	public String getreginitNumber(String compNumber) throws Exception {
		// TODO Auto-generated method stub
		String RegInitNumber = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String arr[] = { compNumber };
			sql = RegCommonMkrSQL.GET_REG_INIT_NUMBER;
			dbUtility.createPreparedStatement(sql);
			RegInitNumber = dbUtility.executeQry(arr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getreginitNumber of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getreginitNumber of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return RegInitNumber;
	}

	public String getInstd(String regInit) throws Exception {
		String deedId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regInit };
			sql = RegCommonMkrSQL.GET_INST_ID;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			deedId = dbUtility.executeQry(regArr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return deedId;
	}

	public boolean checkPinRequired(String regNumber) {
		boolean flag = false;
		String deedId = "";
		String instId = "";
		try {
			deedId = getDeedId(regNumber);
			instId = getInstd(regNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pinFlag = getPinFlag(instId);
		if (pinFlag != null && pinFlag.equalsIgnoreCase("Y")) {
			flag = true;
		}
		/*if((deedId.equals(RegCompCheckerConstant.CERTIFICATION_OF_SALE)&& instId.equals(RegCompCheckerConstant.SALE_DEED_INST_1))||
				(deedId.equals(RegCompCheckerConstant.CONVEYANCE_DEED) && (instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_1) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_2) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_3) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_4) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_5))) ||
				(deedId.equals(RegCompCheckerConstant.EXCHANGE_DEED) && instId.equals(RegCompCheckerConstant.EXCAHNGE_DEED_INST_1)) ||
				(deedId.equals(RegCompCheckerConstant.GIFT_DEED) && instId.equals(RegCompCheckerConstant.GIFT_DEED_INST_1)) ||
				(deedId.equals(RegCompCheckerConstant.LEASE_DEED) && (instId.equals(RegCompCheckerConstant.LEASE_DEED_INST_1))) ||
				(deedId.equals(RegCompCheckerConstant.RELEASE_DEED) && instId.equals(RegCompCheckerConstant.RELAESE_DEED_INST_1)) ||
				(deedId.equals(RegCompCheckerConstant.PARTITION_DEED) && (instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_1) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_2) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_3))))
		{
			flag=true;
		}*/
		else {
			flag = false;
		}
		return flag;
	}

	public double getPlotArea(String valTxnId) {
		String deedId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { valTxnId };
			sql = RegCompCheckerSQL.GET_AREA_PLOT;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			deedId = dbUtility.executeQry(regArr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return Double.parseDouble(deedId);
	}

	public double getAgriArea(String valTxnId) {
		String deedId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { valTxnId };
			sql = RegCompCheckerSQL.GET_AREA_AGRI;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			deedId = dbUtility.executeQry(regArr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return (Double.parseDouble(deedId) * 10000);
	}

	public Double getOldArea(String propId) {
		ArrayList list = new ArrayList();
		String SQL;
		logger.info("Wipro in RegCompMkrDAO - getComplList() before EXCUTIN QUERY");
		SQL = RegCompCheckerSQL.GET_PROP_IDS;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[] { propId });
			logger.info("Wipro in RegCompMkrDAO - getComplList() AFTER EXCUTIN QUERY" + SQL);
			logger.info("Wipro in RegCompMkrDAO - getComplList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("Wipro in RegCompMkrDAO -Exception in calling at DAO Class at getComplList ()  " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double totalArea = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList subList = (ArrayList) list.get(i);
				String id = (String) subList.get(1);
				String propid1 = (String) subList.get(0);
				if ("1".equalsIgnoreCase(id)) {
					totalArea = totalArea + getPlotArea(propid1);
				} else {
					totalArea = totalArea + getAgriArea(propid1);
				}
			}
		}
		return totalArea;
	}

	public boolean updateRegCompNumber(String regInitId, String regCompNumber) {
		boolean saveParty = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCompCheckerSQL.UPDATE_COMP_NUMBER;
			dbUtility.createPreparedStatement(sql);
			String arr[] = new String[] { regCompNumber, regInitId };
			saveParty = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.debug("Exception in save Party Details in RegCompCheckerDAO" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return saveParty;
	}

	public ArrayList getPartyDetailsRegCertificate(String regInitNo, String langauge) {
		ArrayList returnList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.GET_COMMON_FLAG;
			dbUtility.createPreparedStatement(sql);
			String arr[] = new String[] { regInitNo };
			String commonFlag = dbUtility.executeQry(arr);
			if ("en".equalsIgnoreCase(langauge)) {
				if ("Y".equalsIgnoreCase(langauge)) {
					sql = RegCompCheckerSQL.GET_PARTY_DETAILS_CER_COMMOMN;
				} else {
					sql = RegCompCheckerSQL.GET_PARTY_DETAILS_CER_OTHER;
				}
			} else {
				if ("Y".equalsIgnoreCase(langauge)) {
					sql = RegCompCheckerSQL.GET_PARTY_DETAILS_CER_COMMOMN_HI;
				} else {
					sql = RegCompCheckerSQL.GET_PARTY_DETAILS_CER_OTHER_HI;
				}
			}
			dbUtility.createPreparedStatement(sql);
			System.out.println(sql);
			ArrayList list = dbUtility.executeQuery(arr);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList subList = (ArrayList) list.get(i);
					Party party = new Party();
					String appId = (String) subList.get(0);
					if ("1".equalsIgnoreCase(appId)) {
						String name = (String) subList.get(5);
						String fanme = (String) subList.get(6);
						String addr = (String) subList.get(7);
						String role = (String) subList.get(10);
						party.setType("Organization");
						party.setName(name);
						party.setFather_representedBy(fanme);
						party.setAddress(addr);
						party.setHeading(role);
					} else if ("2".equalsIgnoreCase(appId)) {
						String name = "";
						if (subList.get(2) != null && !subList.get(2).toString().equalsIgnoreCase("")) {
							name = (String) subList.get(1) + " " + (String) subList.get(2) + " " + (String) subList.get(3);
						} else {
							name = (String) subList.get(1) + " " + (String) subList.get(3);
						}
						String fanme = (String) subList.get(4);
						String addr = (String) subList.get(7);
						String role = (String) subList.get(10);
						party.setType("Person");
						party.setName(name);
						party.setFather_representedBy(fanme);
						party.setAddress(addr);
						party.setHeading(role);
					} else if ("3".equalsIgnoreCase(appId)) {
						String name = "--";
						if (subList.get(8) != null && !subList.get(8).toString().equalsIgnoreCase("")) {
							name = (String) subList.get(8);;
						}
						String fanme = "--";
						String addr = (String) subList.get(9);
						String role = (String) subList.get(10);
						party.setType("Person");
						party.setName(name);
						party.setFather_representedBy(fanme);
						party.setAddress(addr);
						party.setHeading(role);
					}
					returnList.add(party);
					if (subList.get(12) != null && !subList.get(12).toString().equalsIgnoreCase("")) {
						String ownerId = subList.get(12).toString();
						String arr1[] = new String[] { ownerId };
						if ("en".equalsIgnoreCase(langauge)) {
							sql = RegCompCheckerSQL.GET_PARTY_DETAILS_CER_OWNER;
						} else {
							sql = RegCompCheckerSQL.GET_PARTY_DETAILS_CER_OWNER;
						}
						dbUtility.createPreparedStatement(sql);
						ArrayList list1 = dbUtility.executeQuery(arr1);
						if (list1 != null && list1.size() > 0) {
							for (int j = 0; j < list1.size(); j++) {
								ArrayList subList1 = (ArrayList) list1.get(j);
								Party party1 = new Party();
								String appId1 = (String) subList1.get(0);
								if ("1".equalsIgnoreCase(appId1)) {
									String name = (String) subList1.get(5);
									String fanme = (String) subList1.get(6);
									String addr = (String) subList1.get(7);
									String role = (String) subList.get(10);
									party1.setType("Organization");
									party1.setName(name);
									party1.setFather_representedBy(fanme);
									party1.setAddress(addr);
									party1.setHeading(role);
								} else if ("2".equalsIgnoreCase(appId1)) {
									String name = (String) subList1.get(1);
									/*if( subList1.get(2)!=null&&! subList1.get(2).toString().equalsIgnoreCase(""))
									{
										name=(String) subList1.get(1)+" "+(String) subList1.get(2)+" "+(String) subList1.get(3);
									}
									else
									{
										name=(String) subList1.get(1)+" "+(String) subList1.get(3);
									}*/
									String fanme = (String) subList1.get(4);
									String addr = (String) subList1.get(7);
									String role = (String) subList.get(10);
									party1.setType("Owner");
									party1.setName(name);
									party1.setFather_representedBy("--");
									party1.setAddress(addr);
									party1.setHeading(role);
								} else if ("3".equalsIgnoreCase(appId1)) {
									String name = "--";
									if (subList1.get(8) != null && !subList1.get(8).toString().equalsIgnoreCase("")) {
										name = (String) subList1.get(8);;
									}
									String fanme = "--";
									String addr = (String) subList1.get(9);
									String role = (String) subList.get(10);
									party1.setType("Owner");
									party1.setName(name);
									party1.setFather_representedBy(fanme);
									party1.setAddress(addr);
									party1.setHeading(role);
								}
								returnList.add(party1);
							}
						}
					}
				}
			}
			if ("en".equalsIgnoreCase(langauge)) {
				sql = RegCompCheckerSQL.GET_CONSENTER_DETAILS_REG;
			} else {
				sql = RegCompCheckerSQL.GET_CONSENTER_DETAILS_REG_HI;
			}
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(new String[] { regInitNo });
			if (list != null && list.size() > 0) {
				for (int k = 0; k < list.size(); k++) {
					Party party = new Party();
					ArrayList subList = (ArrayList) list.get(k);
					String name = (String) subList.get(0);
					String fanme = (String) subList.get(1);
					String addr = (String) subList.get(2);
					String role = "";
					if ("en".equalsIgnoreCase(langauge)) {
						role = "Consenter";
					} else {
						role = "सहमतिकर्ता";
					}
					party.setType("Person");
					party.setName(name);
					party.setFather_representedBy(fanme);
					party.setAddress(addr);
					party.setHeading(role);
					returnList.add(party);
				}
			}
		} catch (Exception e) {
			logger.debug("Exception in save Party Details in RegCompCheckerDAO" + e);
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return returnList;
	}

	public String getMarketValue(String regInitId) {
		String marketValue = "--";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regInitId };
			sql = RegCompCheckerSQL.GET_MARKET_VALUE;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			marketValue = dbUtility.executeQry(regArr);
			if (marketValue == null || "".equalsIgnoreCase(marketValue)) {
				marketValue = "--";
			} else {
				if (marketValue.contains(".")) {
					if ((marketValue.substring(marketValue.indexOf("."))).length() > 2) {
						marketValue = marketValue.substring(0, (marketValue.indexOf(".") + 3));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return marketValue;
	}

	public boolean updatePropertyUpload(String propid, String regNo, String columnNamePath, String docPath) {
		boolean boo = false;
		String sql = "";
		if (RegCompCheckerConstant.MAP_COLUMN.equalsIgnoreCase(columnNamePath)) {
			sql = "Update IGRS_REG_PROPRTY_DTLS set " + columnNamePath + "='" + docPath + "' , MPCST_AVAIL_FLAG='N' where REG_TXN_ID=" + regNo + " and PROPERTY_ID=" + propid;
		} else {
			sql = "Update IGRS_REG_PROPRTY_DTLS set " + columnNamePath + "='" + docPath + "' where REG_TXN_ID=" + regNo + " and PROPERTY_ID=" + propid;
		}
		DBUtility dbUtility = null;
		try {
			logger.debug(sql);
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			boo = dbUtility.executeUpdate(sql);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			try {
				dbUtility.rollback();
			} catch (Exception e1) {
				logger.debug(e1.getMessage(), e1);
			}
		} finally {
			try {
				dbUtility.commit();
				dbUtility.closeConnection();
			} catch (Exception z) {
				logger.debug(z.getMessage(), z);
			}
		}
		return boo;
	}

	public boolean updatePartyUpload(String partyId, String regNo, String columnNamePath, String docPath) {
		boolean boo = false;
		String sql = "Update IGRS_REG_TXN_PARTY_DETLS set " + columnNamePath + "='" + docPath + "' where REG_TXN_ID=" + regNo + " and PARTY_TXN_ID=" + partyId;
		DBUtility dbUtility = null;
		try {
			logger.debug(sql);
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			boo = dbUtility.executeUpdate(sql);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			try {
				dbUtility.rollback();
			} catch (Exception e1) {
				logger.debug(e1.getMessage(), e1);
			}
		} finally {
			try {
				dbUtility.commit();
				dbUtility.closeConnection();
			} catch (Exception z) {
				logger.debug(z.getMessage(), z);
			}
		}
		return boo;
	}

	public boolean updateSuccesPrint(String regCompNumber, String status) {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(RegCompCheckerSQL.UPDATE_STATUS_PRINT);
			String arr[] = new String[2];
			if ("1".equalsIgnoreCase(status)) {
				arr[0] = "Y";
			} else {
				arr[0] = "N";
			}
			arr[1] = regCompNumber;
			flag = dbUtility.executeUpdate(arr);
			if ("1".equalsIgnoreCase(status)) {
				dbUtility.createPreparedStatement(RegCompCheckerSQL.PRINTED_STATUS_UPDATE1);
				String arr1[] = new String[1];
				arr1[0] = regCompNumber;
				flag = dbUtility.executeUpdate(arr1);
			}
			dbUtility.commit();
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	public String getExecDate(String reginitNo) {
		String date = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_EXEC_DATE;
			dbUtility.createPreparedStatement(query);
			date = dbUtility.executeQry(new String[] { reginitNo });
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return date;
	}

	public String getPenalityId(String reginitNo) {
		String peanlity = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.PENALITY_ID;
			dbUtility.createPreparedStatement(query);
			peanlity = dbUtility.executeQry(new String[] { reginitNo });
			dbUtility.closeConnection();
			if (peanlity == null || peanlity.equalsIgnoreCase("")) {
				peanlity = "";
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return peanlity;
	}

	public String getPenalityIdOnly(String reginitNo) {
		String peanlity = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.PENALITY_ID_ONLY;
			dbUtility.createPreparedStatement(query);
			peanlity = dbUtility.executeQry(new String[] { reginitNo });
			dbUtility.closeConnection();
			if (peanlity == null || peanlity.equalsIgnoreCase("")) {
				peanlity = "";
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return peanlity;
	}

	public boolean upadteTimeBarredDetials(RegCompleteMakerDTO dto) {
		boolean boo = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.UPADTE_TIME_BARRED;
			dbUtility.createPreparedStatement(query);
			boo = dbUtility.executeUpdate(new String[] { dto.getTimePhotoPathUploaded(), dto.getExecRemarks(), dto.getRegNumber() });
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return boo;
	}

	public String getDutyUpdateFlag(String regInitId) {
		String peanlity = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_DUTY_FLAG;
			dbUtility.createPreparedStatement(query);
			peanlity = dbUtility.executeQry(new String[] { regInitId });
			dbUtility.closeConnection();
			if (peanlity == null || peanlity.equalsIgnoreCase("")) {
				peanlity = "";
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return peanlity;
	}

	public boolean checkImagesPresentation(String regInitId) {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_PARTY_PATH;
			dbUtility.createPreparedStatement(query);
			ArrayList list = dbUtility.executeQuery(new String[] { regInitId });
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList rowList = (ArrayList) list.get(i);
					String path1 = (String) rowList.get(0);
					String path2 = (String) rowList.get(1);
					String path3 = (String) rowList.get(2);
					if (path1 != null && !path1.equalsIgnoreCase("") && path2 != null && !path2.equalsIgnoreCase("") && path3 != null && !path3.equalsIgnoreCase("")) {
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	public boolean checkImagesPresentationSelected(String[] partyId) {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_PARTY_PATH_SELECTED;
			int j = 0;
			ArrayList<String> partyList = new ArrayList<String>();
			for (int i = 0; i < partyId.length; i++) {
				String index = partyId[i];
				String party = "";
				if (index != null) {
					party = index.split(",")[0];
				}
				if (party != null && !party.equalsIgnoreCase("")) {
					partyList.add(party);
					if (j == 0) {
						query = query + "?";
						j = j + 1;
					} else {
						query = query + ",?";
					}
				}
			}
			query = query + RegCompCheckerSQL.GET_PARTY_PATH_SELECTED_REMAINING;
			//String[] partyArray=(String[]) partyList.toArray();
			String[] partyArray = partyList.toArray(new String[] {});
			dbUtility.createPreparedStatement(query);
			ArrayList list = dbUtility.executeQuery(partyArray);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList rowList = (ArrayList) list.get(i);
					String path1 = (String) rowList.get(0);
					String path2 = (String) rowList.get(1);
					String path3 = (String) rowList.get(2);
					if (path1 != null && !path1.equalsIgnoreCase("") && path2 != null && !path2.equalsIgnoreCase("") && path3 != null && !path3.equalsIgnoreCase("")) {
						flag = true;
					} else {
						flag = false;
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	public String getPresentationSingleParty(String regInitId) {
		String partyId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_PARTY_PATH_SINGLE;
			dbUtility.createPreparedStatement(query);
			ArrayList list = dbUtility.executeQuery(new String[] { regInitId });
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList rowList = (ArrayList) list.get(i);
					String path1 = (String) rowList.get(0);
					String path2 = (String) rowList.get(1);
					String path3 = (String) rowList.get(2);
					partyId = (String) rowList.get(3);
					if (path1 != null && !path1.equalsIgnoreCase("") && path2 != null && !path2.equalsIgnoreCase("") && path3 != null && !path3.equalsIgnoreCase("")) {
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return partyId;
	}

	public String getWitnessSeals(String regInitId) {
		String partyId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_WITNESS_SEAL;
			dbUtility.createPreparedStatement(query);
			ArrayList list = dbUtility.executeQuery(new String[] { regInitId });
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList rowList = (ArrayList) list.get(i);
					String path1 = (String) rowList.get(0);
					partyId = partyId + "~" + path1;
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return partyId;
	}

	public String getThumbSealsAll(String regInitId) {
		String partyId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_PARTY_SEAL;
			dbUtility.createPreparedStatement(query);
			ArrayList list = dbUtility.executeQuery(new String[] { regInitId });
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList rowList = (ArrayList) list.get(i);
					String path1 = (String) rowList.get(0);
					partyId = partyId + "~" + path1;
				}
			}
			query = RegCompCheckerSQL.GET_WITNESS_SEAL;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList rowList = (ArrayList) list.get(i);
					String path1 = (String) rowList.get(0);
					partyId = partyId + "~" + path1;
				}
			}
			query = RegCompCheckerSQL.GET_CONSENTER_SEAL;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { regInitId });
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList rowList = (ArrayList) list.get(i);
					String path1 = (String) rowList.get(0);
					partyId = partyId + "~" + path1;
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return partyId;
	}

	public ArrayList checkLinking(String regInitNumber) {
		ArrayList list = new ArrayList();
		ArrayList returnList = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regInitNumber };
			sql = RegCompCheckerSQL.GET_LINKING_DETLS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(regArr);
			if (list != null && list.size() > 0) {
				returnList = new ArrayList();
				returnList = (ArrayList) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return returnList;
	}

	public String getRegistartionDate(String regInitId) {
		String peanlity = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_REG_DATE;
			dbUtility.createPreparedStatement(query);
			peanlity = dbUtility.executeQry(new String[] { regInitId.trim() });
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return peanlity;
	}

	public String getRegistartionDateInit(String regInitId) {
		String peanlity = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.GET_REG_DATE_INIT;
			dbUtility.createPreparedStatement(query);
			peanlity = dbUtility.executeQry(new String[] { regInitId.trim() });
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return peanlity;
	}

	public String getCommonFlowFlag(String regInitId) {
		DBUtility dbUtility = null;
		String CommonDeedFlag = "";
		//dbUtility.createStatement();
		try {
			dbUtility = new DBUtility();
			String arrCommon[] = { regInitId };
			String sqlOne = RegCommonMkrSQL.GET_COMMON_FLOW_FLAG;
			System.out.println(sqlOne);
			dbUtility.createPreparedStatement(sqlOne);
			CommonDeedFlag = dbUtility.executeQry(arrCommon);
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		System.out.println("CommonDeedFlag" + CommonDeedFlag);
		return CommonDeedFlag;
	}

	//  Set Value for consideration amount - Registration Certficate Print Preview - 19 SEp
	public String getConsiderAmount(String regInitId) {
		String considerAmount = "--";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regInitId };
			sql = RegCompCheckerSQL.GET_CONSIDER_AMOUNT;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			considerAmount = dbUtility.executeQry(regArr);
			if (considerAmount == null || "".equalsIgnoreCase(considerAmount)) {
				considerAmount = "NA";
			} else {
				if (considerAmount.contains(".")) {
					if ((considerAmount.substring(considerAmount.indexOf("."))).length() > 2) {
						considerAmount = considerAmount.substring(0, (considerAmount.indexOf(".") + 3));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in get considerAmt of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getconsiderAmt of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return considerAmount;
	}

	//done by akansha for mutation
	public String getclrFlag(String regInitId) throws Exception {
		String clrFlag = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.get_clr_flag;
			dbUtility.createPreparedStatement(query);
			clrFlag = dbUtility.executeQry(new String[] { regInitId });
		} catch (Exception exception) {
			logger.debug("Exception in getSeqNum" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("in case fail to get clr flag for mutation" + e.getStackTrace());
			}
		}
		return clrFlag;
	}

	public boolean updateMutationStatus(String regInitId, String mutationStatus) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCompCheckerSQL.update_Mutation_Status;
			dbUtility.createPreparedStatement(sql);
			String arr[] = { mutationStatus, regInitId };
			flag = dbUtility.executeUpdate(arr);
		} catch (Exception e) {
			logger.error("RegCompCheckerDAO in updateMutationStatus start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updateMutationStatus" + e.getStackTrace());
			}
		}
		return flag;
	}

	// Added by ankit for aadhar integration - start 
	public AadharDetailDTO getAadharDetailsByPartyTxnId(String partyTxnId) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList aadharDtsList = new ArrayList();
		AadharDetailDTO aadharDts = new AadharDetailDTO();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.AADHAR_DTS_BY_PARTY_TXN_ID;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { partyTxnId });
			if (list != null) {
				aadharDtsList = (ArrayList) list.get(0);
				aadharDts.setADHAR_TXN_ID(aadharDtsList.get(0) != null ? aadharDtsList.get(0).toString() : "");
				aadharDts.setPARTY_TXN_ID(aadharDtsList.get(1) != null ? aadharDtsList.get(1).toString() : "");
				aadharDts.setADHAR_NO(aadharDtsList.get(2) != null ? aadharDtsList.get(2).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(2).toString()) : "");
				aadharDts.setADHAR_NAME(aadharDtsList.get(3) != null ? aadharDtsList.get(3).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(3).toString()) : "");
				aadharDts.setTRANSACTION_ID1(aadharDtsList.get(4) != null ? aadharDtsList.get(4).toString() : "");
				aadharDts.setTRANSACTION_ID2(aadharDtsList.get(5) != null ? aadharDtsList.get(5).toString() : "");
				aadharDts.setREG_TXN_ID(aadharDtsList.get(6) != null ? aadharDtsList.get(6).toString() : "");
				aadharDts.setCREATED_BY(aadharDtsList.get(7) != null ? aadharDtsList.get(7).toString() : "");
				aadharDts.setCREATED_DATE(aadharDtsList.get(8) != null ? aadharDtsList.get(8).toString() : "");
				aadharDts.setCONSENTER_TXN_NUM(aadharDtsList.get(9) != null ? aadharDtsList.get(9).toString() : "");
				aadharDts.setACKID_INIT(aadharDtsList.get(10) != null ? aadharDtsList.get(10).toString() : "");
				aadharDts.setACKID_CHECKER(aadharDtsList.get(11) != null ? aadharDtsList.get(11).toString() : "");
				aadharDts.setDOB(aadharDtsList.get(12) != null ? aadharDtsList.get(12).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(12).toString()) : "");
				aadharDts.setGENDER(aadharDtsList.get(13) != null ? aadharDtsList.get(13).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(13).toString()) : "");
				aadharDts.setMOBILE(aadharDtsList.get(14) != null ? aadharDtsList.get(14).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(14).toString()) : "");
				aadharDts.setEMAIL_ID(aadharDtsList.get(15) != null ? aadharDtsList.get(15).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(15).toString()) : "");
				aadharDts.setGUARDIAN_NAME(aadharDtsList.get(16) != null ? aadharDtsList.get(16).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(16).toString()) : "");
				aadharDts.setRELATION_TYPE(aadharDtsList.get(17) != null ? aadharDtsList.get(17).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(17).toString()) : "");
				aadharDts.setCARE_OF(aadharDtsList.get(18) != null ? aadharDtsList.get(18).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(18).toString()) : "");
				aadharDts.setBUILDING(aadharDtsList.get(19) != null ? aadharDtsList.get(19).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(19).toString()) : "");
				aadharDts.setSTREET(aadharDtsList.get(20) != null ? aadharDtsList.get(20).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(20).toString()) : "");
				aadharDts.setLANDMARK(aadharDtsList.get(21) != null ? aadharDtsList.get(21).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(21).toString()) : "");
				aadharDts.setLOCALITY(aadharDtsList.get(22) != null ? aadharDtsList.get(22).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(22).toString()) : "");
				aadharDts.setCITY(aadharDtsList.get(23) != null ? aadharDtsList.get(23).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(23).toString()) : "");
				aadharDts.setSUB_DISTRICT(aadharDtsList.get(24) != null ? aadharDtsList.get(24).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(24).toString()) : "");
				aadharDts.setDISTRICT(aadharDtsList.get(25) != null ? aadharDtsList.get(25).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(25).toString()) : "");
				aadharDts.setSTATE(aadharDtsList.get(26) != null ? aadharDtsList.get(26).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(26).toString()) : "");
				aadharDts.setPIN_CODE(aadharDtsList.get(27) != null ? aadharDtsList.get(27).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(27).toString()) : "");
				aadharDts.setPO(aadharDtsList.get(28) != null ? aadharDtsList.get(28).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(28).toString()) : "");
				aadharDts.setRESIDENT_PHOTO_PATH(aadharDtsList.get(29) != null ? aadharDtsList.get(29).toString() : "");
				/*	if(aadharDtsList.get(12)!=null){
						ObjectMapper mapper = new ObjectMapper();
						AadharResidentDetails adharResDtls = mapper.readValue(aadharDtsList.get(12).toString().isEmpty()?"":AadharUtil.decryptWithAES256(aadharDtsList.get(12).toString()),AadharResidentDetails.class);
						aadharDts.setAdharResidntDtls(adharResDtls);
					}*/
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.debug("Exception in getAadharDetailsByPartyTxnId" + exception.getMessage());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		return aadharDts;
	}

	public AadharDetailDTO getAadharDetailsByConstTxnNum(String constTxnNum) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList aadharDtsList = new ArrayList();
		AadharDetailDTO aadharDts = new AadharDetailDTO();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.AADHAR_DTS_BY_CONSENTER_TXN_NUM;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(new String[] { constTxnNum });
			if (list != null) {
				aadharDtsList = (ArrayList) list.get(0);
				aadharDts.setADHAR_TXN_ID(aadharDtsList.get(0) != null ? aadharDtsList.get(0).toString() : "");
				aadharDts.setPARTY_TXN_ID(aadharDtsList.get(1) != null ? aadharDtsList.get(1).toString() : "");
				aadharDts.setADHAR_NO(aadharDtsList.get(2) != null ? aadharDtsList.get(2).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(2).toString()) : "");
				aadharDts.setADHAR_NAME(aadharDtsList.get(3) != null ? aadharDtsList.get(3).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(3).toString()) : "");
				aadharDts.setTRANSACTION_ID1(aadharDtsList.get(4) != null ? aadharDtsList.get(4).toString() : "");
				aadharDts.setTRANSACTION_ID2(aadharDtsList.get(5) != null ? aadharDtsList.get(5).toString() : "");
				aadharDts.setREG_TXN_ID(aadharDtsList.get(6) != null ? aadharDtsList.get(6).toString() : "");
				aadharDts.setCREATED_BY(aadharDtsList.get(7) != null ? aadharDtsList.get(7).toString() : "");
				aadharDts.setCREATED_DATE(aadharDtsList.get(8) != null ? aadharDtsList.get(8).toString() : "");
				aadharDts.setCONSENTER_TXN_NUM(aadharDtsList.get(9) != null ? aadharDtsList.get(9).toString() : "");
				aadharDts.setACKID_INIT(aadharDtsList.get(10) != null ? aadharDtsList.get(10).toString() : "");
				aadharDts.setACKID_CHECKER(aadharDtsList.get(11) != null ? aadharDtsList.get(11).toString() : "");
				aadharDts.setDOB(aadharDtsList.get(12) != null ? aadharDtsList.get(12).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(12).toString()) : "");
				aadharDts.setGENDER(aadharDtsList.get(13) != null ? aadharDtsList.get(13).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(13).toString()) : "");
				aadharDts.setMOBILE(aadharDtsList.get(14) != null ? aadharDtsList.get(14).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(14).toString()) : "");
				aadharDts.setEMAIL_ID(aadharDtsList.get(15) != null ? aadharDtsList.get(15).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(15).toString()) : "");
				aadharDts.setGUARDIAN_NAME(aadharDtsList.get(16) != null ? aadharDtsList.get(16).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(16).toString()) : "");
				aadharDts.setRELATION_TYPE(aadharDtsList.get(17) != null ? aadharDtsList.get(17).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(17).toString()) : "");
				aadharDts.setCARE_OF(aadharDtsList.get(18) != null ? aadharDtsList.get(18).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(18).toString()) : "");
				aadharDts.setBUILDING(aadharDtsList.get(19) != null ? aadharDtsList.get(19).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(19).toString()) : "");
				aadharDts.setSTREET(aadharDtsList.get(20) != null ? aadharDtsList.get(20).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(20).toString()) : "");
				aadharDts.setLANDMARK(aadharDtsList.get(21) != null ? aadharDtsList.get(21).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(21).toString()) : "");
				aadharDts.setLOCALITY(aadharDtsList.get(22) != null ? aadharDtsList.get(22).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(22).toString()) : "");
				aadharDts.setCITY(aadharDtsList.get(23) != null ? aadharDtsList.get(23).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(23).toString()) : "");
				aadharDts.setSUB_DISTRICT(aadharDtsList.get(24) != null ? aadharDtsList.get(24).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(24).toString()) : "");
				aadharDts.setDISTRICT(aadharDtsList.get(25) != null ? aadharDtsList.get(25).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(25).toString()) : "");
				aadharDts.setSTATE(aadharDtsList.get(26) != null ? aadharDtsList.get(26).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(26).toString()) : "");
				aadharDts.setPIN_CODE(aadharDtsList.get(27) != null ? aadharDtsList.get(27).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(27).toString()) : "");
				aadharDts.setPO(aadharDtsList.get(28) != null ? aadharDtsList.get(28).toString().isEmpty() ? "" : AadharUtil.decryptWithAES256(aadharDtsList.get(28).toString()) : "");
				aadharDts.setRESIDENT_PHOTO_PATH(aadharDtsList.get(29) != null ? aadharDtsList.get(29).toString() : "");
				/*if(aadharDtsList.get(12)!=null){
					ObjectMapper mapper = new ObjectMapper();
					AadharResidentDetails adharResDtls = mapper.readValue(aadharDtsList.get(12).toString().isEmpty()?"":AadharUtil.decryptWithAES256(aadharDtsList.get(12).toString()),AadharResidentDetails.class);
					aadharDts.setAdharResidntDtls(adharResDtls);
				}*/
			}
		} catch (Exception exception) {
			logger.debug("Exception in getAadharDetailsByPartyTxnId" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		return aadharDts;
	}

	public String getImagePathByPartyTxnId(String partyTxnId) throws Exception {
		String path = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCompCheckerSQL.THUMB_IMP_PATH;
			dbUtility.createPreparedStatement(query);
			path = dbUtility.executeQry(new String[] { partyTxnId });
		} catch (Exception exception) {
			logger.debug("Exception in getImagePathByPartyTxnId" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
		return path;
	}

	public boolean updateAadharDetails(AadharRespDTO adharRespDTO, String partyTxnId, String consenterTxnId, String imagePath) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			/*ObjectMapper mapper = new ObjectMapper();
			String aadharResdtDtls = mapper.writeValueAsString(adharRespDTO.getResidentDetails());*/
			String[] aadharDtsArry = { adharRespDTO.getTransactionId() != null ? adharRespDTO.getTransactionId().toString() : "", adharRespDTO.getTransactionCode() != null ? adharRespDTO.getTransactionCode().toString() : "", adharRespDTO.getResidentDetails().getDob() != null ? adharRespDTO.getResidentDetails().getDob().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getDob().toString()) : "", adharRespDTO.getResidentDetails().getGender() != null ? adharRespDTO.getResidentDetails().getGender().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getGender().toString()) : "", adharRespDTO.getResidentDetails().getMobile() != null ? adharRespDTO.getResidentDetails().getMobile().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getMobile().toString()) : "", adharRespDTO.getResidentDetails().getEmailId() != null ? adharRespDTO.getResidentDetails().getEmailId().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getEmailId().toString()) : "", adharRespDTO.getResidentDetails().getGuardianName() != null ? adharRespDTO.getResidentDetails().getGuardianName().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getGuardianName().toString()) : "", adharRespDTO.getResidentDetails().getGuardianRelationType() != null ? adharRespDTO.getResidentDetails().getGuardianRelationType().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getGuardianRelationType().toString()) : "", adharRespDTO.getResidentDetails().getCareof() != null ? adharRespDTO.getResidentDetails().getCareof().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getCareof().toString()) : "", adharRespDTO.getResidentDetails().getBuilding() != null ? adharRespDTO.getResidentDetails().getBuilding().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getBuilding().toString()) : "", adharRespDTO.getResidentDetails().getStreet() != null ? adharRespDTO.getResidentDetails().getStreet().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getStreet().toString()) : "", adharRespDTO.getResidentDetails().getLandmark() != null ? adharRespDTO.getResidentDetails().getLandmark().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getLandmark().toString()) : "", adharRespDTO.getResidentDetails().getLocality() != null ? adharRespDTO.getResidentDetails().getLocality().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getLocality().toString()) : "", adharRespDTO.getResidentDetails().getVtc() != null ? adharRespDTO.getResidentDetails().getVtc().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getVtc().toString()) : "", adharRespDTO.getResidentDetails().getSubdistrict() != null ? adharRespDTO.getResidentDetails().getSubdistrict().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getSubdistrict().toString()) : "", adharRespDTO.getResidentDetails().getDistrict() != null ? AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getDistrict().toString()) : "", adharRespDTO.getResidentDetails().getState() != null ? adharRespDTO.getResidentDetails().getState().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getState().toString()) : "", adharRespDTO.getResidentDetails().getPincode() != null ? AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getPincode().toString()) : "", adharRespDTO.getResidentDetails().getPO() != null ? adharRespDTO.getResidentDetails().getPO().toString().isEmpty() ? "" : AadharUtil.encryptWithAES256(adharRespDTO.getResidentDetails().getPO().toString()) : "", imagePath != null ? imagePath : "", partyTxnId == null ? consenterTxnId : partyTxnId };
			if (partyTxnId != null) {
				sql = RegCompCheckerSQL.UPDATE_PARTY_AADHAR_DETAILS_CHK;
			}
			if (consenterTxnId != null) {
				sql = RegCompCheckerSQL.UPDATE_CONSENTER_AADHAR_DETAILS_CHK;
			}
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeUpdate(aadharDtsArry);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in updateAadharDetails : " + e.getStackTrace());
		} finally {
			try {
				if (flag) {
					dbUtility.commit();
				} else {
					dbUtility.rollback();
				}
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updateAadharDetails " + e.getStackTrace());
			}
		}
		return flag;
	}

	// Added by ankit for aadhar integration - end 
	//done by akansha for refusal seal
	/*public String  getRefusalRemarks(String regInitId) throws Exception{
		String getRefusalRemarks = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			
			String query=RegCompCheckerSQL.getRefusalRemarks;
			dbUtility.createPreparedStatement(query);
			getRefusalRemarks = dbUtility.executeQry(new String[]{regInitId});
		} catch (Exception exception) {
			logger.debug("Exception in getSeqNum" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("in case fail to get clr flag for mutation"+e.getStackTrace());
			}
			
		}
		
		return getRefusalRemarks;
	}*/
	//added by ankit for plant and machinery 
	public String getMachineryAmount(String regInitId) {
		String machineryAmount = "--";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regInitId };
			sql = RegCompCheckerSQL.GET_MACHINERY_AMOUNT;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			machineryAmount = dbUtility.executeQry(regArr);
			if (machineryAmount == null || "".equalsIgnoreCase(machineryAmount)) {
				machineryAmount = "NA";
			} else {
				machineryAmount = machineryAmount;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in get considerAmt of regCompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getMachineryAmt of regCompMkrDAO" + e.getStackTrace());
			}
		}
		return machineryAmount;
	}

	//added by ankit for INC000000147473
	public String getMarketValueExchangeDeed(String regInitId) {
		String marketValue = "--";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regInitId };
			sql = RegCompCheckerSQL.GET_MARKET_VALUE_EXCHANGE_DEED;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			marketValue = dbUtility.executeQry(regArr);
			if (marketValue == null || "".equalsIgnoreCase(marketValue)) {
				marketValue = "--";
			} else {
				if (marketValue.contains(".")) {
					if ((marketValue.substring(marketValue.indexOf("."))).length() > 2) {
						marketValue = marketValue.substring(0, (marketValue.indexOf(".") + 3));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getMarketValueExchangeDeed of regCompCkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getMarketValueExchangeDeed of regCompCkrDAO" + e.getStackTrace());
			}
		}
		return marketValue;
	}
	//Added by rustam
	public ArrayList getCyberTehsilFormDetails(String regNum) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = { regNum };
			sql = RegCommonMkrSQL.GET_CYBER_TEHSIL_FORM_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(regArr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO" + e.getStackTrace());
			}
		}
		return list;
	}
}
