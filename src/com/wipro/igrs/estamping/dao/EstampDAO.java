package com.wipro.igrs.estamping.dao;

/**
 * ===========================================================================
 * File           :   EstampDAO.java
 * Description    :   Represents the DAO Class

 * Author         :   Pavani param
 * Created Date   :   Dec 07, 2007

 * ===========================================================================
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.deedDraft.bd.DeedDraftBD;
import com.wipro.igrs.estamping.bd.EstampingBD;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.estamping.dto.EstampDetailsDTO;
import com.wipro.igrs.estamping.form.DutyCalculationForm;
import com.wipro.igrs.estamping.form.EstampFormBean;
import com.wipro.igrs.estamping.sql.CommonSQL;
import com.wipro.igrs.regcompletion.sql.RegCommonSQL;

public class EstampDAO {
	//DBUtility dbUtil;
	EstampDetailsDTO objEstamp;
	//IGRSCommon  igrsCommon; 
	ArrayList judList;
	ArrayList nonJudList;
	ArrayList list = null;
	String str1;
	boolean insert;
	String SQL = null;
	PreparedStatement pst = null;
	Connection con = null;
	private Logger logger = (Logger) Logger.getLogger(EstampDAO.class);

	public EstampDAO() {
		try {
			//dbUtil  =  new DBUtility();
			//igrsCommon  = new IGRSCommon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getting Country list
	 * 
	 * @return list
	 */
	public ArrayList getCountry() {
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			list = igrsCommon.getCountry();
		} catch (Exception e) {
			logger.error("EstampDAO -- Exception in getCountry():" + e);
		}
		return list;
	}

	/**
	 * Getting id list for dashboard page
	 * 
	 * @return list
	 */
	public ArrayList getSROFirst(String id) {
		DBUtility dbUtil = null;
		try {
			String temp[] = new String[1];
			temp[0] = id;
			/* String SQL = "SELECT ESTAMP_CODE FROM IGRS_ESTAMP_TXN_DETAILS WHERE TRANSACTION_ID ='"+id+"'";
			 //TODO
			*/dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.SELECT_ESTAMP_CODE);
			logger.info("in Estmap DAO before executing values  getSROFirst()");
			list = dbUtil.executeQuery(temp);
		} catch (Exception e) {
			logger.error("EstampDAO -- Exception in getSROFirst():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		return list;
	}

	/**
	 * Getting Purpose list
	 * 
	 * @return list
	 */
	public ArrayList getPurpose() {
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			list = igrsCommon.getPurpose();
		} catch (Exception e) {
			logger.error("EstampDAO -- Exception in getPurpose():" + e);
		}
		return list;
	}

	/**
	 * Getting State list.
	 * 
	 * @param countryId
	 * @return list
	 */
	public ArrayList getState(String countryId) {
		try {
			if (countryId == null) {
				countryId = "INDIA";
			}
			IGRSCommon igrsCommon = new IGRSCommon();
			list = igrsCommon.getState(countryId);
		} catch (Exception e) {
			logger.error("EstampDAO -- Exception in getState():" + e);
		}
		return list;
	}

	/**
	 * Getting District list.
	 * 
	 * @return list
	 */
	public ArrayList getDistrict(String stateId, String language) {
		try {
			if (stateId == null) {
				stateId = "MP";
			}
			IGRSCommon igrsCommon = new IGRSCommon();
			list = igrsCommon.getDistrict(stateId);
			logger.info("EstampDAO----getDistrict()" + list);
		} catch (Exception e) {
			logger.info("EstampDAO -- Exception in getDistrict():" + e);
		}
		return list;
	}

	/**
	 * Getting Fee.
	 * 
	 * @return list
	 */
	public String getFee(String strPurpose) {
		try {
			/* if (strPurpose == null) {
			 
			 }*/
			IGRSCommon igrsCommon = new IGRSCommon();
			str1 = igrsCommon.getFee(strPurpose);
			logger.info("EstampDAO----getFee" + str1);
		} catch (Exception e) {
			logger.info("EstampDAO -- Exception in getFee():" + e);
		}
		return str1;
	}

	/**
	 * Getting Photo List
	 * 
	 * @return list
	 */
	public ArrayList getphotoIdList() {
		/*String SQL  =  "SELECT PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_TYPE_NAME FROM IGRS_PHOTOID_PROOF_TYPE_MASTER";
		//TODO
		*/DBUtility dbUtil = null;
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.SELECT_PHOTO_PROOF);
			logger.info("EstampDAAO in getphotoIdList(): list.size()" + list.size());
		} catch (Exception e) {
			logger.error("Exception in getphotoIdList():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("EstampDAAO in getphotoIdList():" + list);
		return list;
	}

	/**
	 * Getting Judicial list
	 * 
	 * @return list
	 * @throws Exception
	 */
	public ArrayList getJudicialList() throws Exception {
		logger.info("in Estmap DAO   getJudicialList()");
		ArrayList list = null;
		String SQL = CommonSQL.ESTAMPING_JUD_LIST;
		DBUtility dbUtil = new DBUtility();
		try {
			dbUtil.createStatement();
			logger.info("in Estmap DAO before executing values  getJudicialList()");
			list = dbUtil.executeQuery(SQL);
		} catch (Exception e) {
			throw new Exception();
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * Getting deed details
	 * 
	 * @return list
	 */
	public ArrayList getOptionalDeedType() {
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			list = igrsCommon.getOptionalDeedType();
			logger.info("Wipro in EstampDAo - getOptionalDeedType() after execute query  " + list);
		} catch (Exception e) {
			logger.error("Exception in getOptionalDeedType():" + e);
		}
		return list;
	}

	/**
	 * Getting Instrument list
	 * 
	 * @param deedTypeID
	 * @return
	 */
	public ArrayList getInstrumentDet(String deedTypeID) throws Exception {
		logger.info("in Estmap DAO  getInstrumentDet");
		String SQL = CommonSQL.ESTAMPING_INSTR_DET + "'" + deedTypeID + "'";
		DBUtility dbUtil = new DBUtility();
		dbUtil.createStatement();
		logger.info("in Estmap DAO before executing values getInstrumentDet");
		//logger.debug("SQL-->  "+SQL);	 	 
		ArrayList l1 = null;
		try {
			l1 = dbUtil.executeQuery(SQL);
		} catch (Exception e) {
			throw new Exception();
		} finally {
			dbUtil.closeConnection();
		} //logger.debug("list in dao-->  "+l1.size()+"            "+l1);
		return l1;
	}

	/**
	 * getting Exemption list
	 * 
	 * @param deedTypeID
	 * @param insID
	 * @return
	 */
	public ArrayList getExempList(String insID, String deedTypeID) throws Exception {
		logger.info("in Estmap DAO  getExemList");
		String SQL = CommonSQL.ESTAMPING_EXEM_DET;
		String arr[] = new String[2];
		arr[0] = insID;
		arr[1] = deedTypeID;
		DBUtility dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(SQL);
		logger.info("in Estmap DAO before executing values getExemList");
		//logger.debug("SQL-->  "+SQL);	 	 
		ArrayList l1 = null;
		try {
			l1 = dbUtil.executeQuery(arr);
			//logger.debug("list in dao-->  "+l1.size()+"            "+l1);
		} catch (Exception e) {
			throw new Exception();
		} finally {
			dbUtil.closeConnection();
		}
		return l1;
	}

	/**
	 * Inserting Applicant Individual Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	// --START--APPLICANT DETAILS INSERT
	public boolean applIndDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		boolean applIndInsert = false;
		DBUtility dbUtil = null;
		try {
			logger.info("Wipro in EstampDAo- before inserting Estamping data ");
			logger.info("Wipro in EstampDAo - applIndDetInsert() before creating preparedst");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.ESTAMPING_APP_IND_INSERT);
			logger.info("Wipro in EstampDAo - applIndDetInsert() after creating preparedst");
			applIndInsert = dbUtil.executeUpdate(param);
			logger.info("before inserting admin data ");
			if (applIndInsert) {
				dbUtil.commit();
				IGRSCommon igrsCommon = new IGRSCommon();
				igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_PARTY_DETAILS", "INSERT", "T", funId, userId, roleId);
			}
			logger.info("after inserting ");
		} catch (Exception x) {
			logger.error("Exception in applIndDetInsert() :- " + x);
			dbUtil.rollback();
		} finally {
			try {
				if (!applIndInsert) {
					dbUtil.rollback();
					IGRSCommon igrsCommon = new IGRSCommon();
					igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_PARTY_DETAILS", "INSERT", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in applIndDetInsert() :-" + ex);
			}
		}
		return applIndInsert;
	}

	/**
	 * Inserting Applicant Organization Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean applOrgDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		boolean applOrgInsert = false;
		DBUtility dbUtil = null;
		try {
			logger.info("Wipro in EstampDAo- before inserting Estamping data ");
			logger.info("Wipro in EstampDAo - applOrgDetInsert() before creating preparedst");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.ESTAMPING_APP_ORG_INSERT);
			logger.info("Wipro in EstampDAo - applOrgDetInsert() after creating preparedst");
			applOrgInsert = dbUtil.executeUpdate(param);
			logger.info("before inserting admin data ");
			if (applOrgInsert) {
				dbUtil.commit();
				IGRSCommon igrsCommon = new IGRSCommon();
				igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_PARTY_DETAILS", "INSERT", "T", funId, userId, roleId);
			}
			logger.info("after inserting ");
		} catch (Exception x) {
			logger.error("Exception in applOrgDetInsert() :- " + x);
			dbUtil.rollback();
			IGRSCommon igrsCommon = new IGRSCommon();
			igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_PARTY_DETAILS", "INSERT", "F", funId, userId, roleId);
		} finally {
			try {
				if (!applOrgInsert) {
					dbUtil.rollback();
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in applOrgDetInsert() :-" + ex);
			}
		}
		return applOrgInsert;
	}

	// --END--APPLICANT DETAILS INSERT
	// --START--TRANSACTING PARTY DETAILS INSERT
	/**
	 * Inserting Txn party Individual Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean txnIndDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		boolean txn1IndInsert = false;
		DBUtility dbUtil = null;
		try {
			logger.info("Wipro in EstampDAo- before inserting Estamping data ");
			logger.info("Wipro in EstampDAo - txnIndDetInsert() before creating preparedst");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.ESTAMPING_Txn_IND_INSERT);
			logger.info("Wipro in EstampDAo - txnIndDetInsert() after creating preparedst");
			txn1IndInsert = dbUtil.executeUpdate(param);
			logger.info("before inserting admin data ");
			if (txn1IndInsert) {
				dbUtil.commit();
				IGRSCommon igrsCommon = new IGRSCommon();
				igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_PARTY_DETAILS", "INSERT", "T", funId, userId, roleId);
			}
			logger.info("after inserting ");
		} catch (Exception x) {
			logger.error("Exception in txnIndDetInsert() :- " + x);
			dbUtil.rollback();
		} finally {
			try {
				if (!txn1IndInsert) {
					dbUtil.rollback();
					IGRSCommon igrsCommon = new IGRSCommon();
					igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_PARTY_DETAILS", "INSERT", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in txnIndDetInsert() :-" + ex);
			}
		}
		return txn1IndInsert;
	}

	/**
	 * Inserting Txn party Organization Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean txnOrgDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		boolean txnOrgInsert = false;
		DBUtility dbUtil = null;
		try {
			logger.info("Wipro in EstampDAo- before inserting Estamping data ");
			logger.info("Wipro in EstampDAo - txnOrgDetInsert() before creating preparedst");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.ESTAMPING_TXN_ORG_INSERT);
			logger.info("Wipro in EstampDAo - txnOrgDetInsert() after creating preparedst");
			txnOrgInsert = dbUtil.executeUpdate(param);
			logger.info("before inserting admin data ");
			if (txnOrgInsert) {
				dbUtil.commit();
				IGRSCommon igrsCommon = new IGRSCommon();
				igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_PARTY_DETAILS", "INSERT", "T", funId, userId, roleId);
			}
			logger.info("after inserting ");
		} catch (Exception x) {
			logger.error("Exception in txnOrgDetInsert() :- " + x);
			dbUtil.rollback();
			IGRSCommon igrsCommon = new IGRSCommon();
			igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_PARTY_DETAILS", "INSERT", "F", funId, userId, roleId);
		} finally {
			try {
				if (!txnOrgInsert) {
					dbUtil.rollback();
					IGRSCommon igrsCommon = new IGRSCommon();
					igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_PARTY_DETAILS", "INSERT", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in txnOrgDetInsert() :-" + ex);
			}
		}
		return txnOrgInsert;
	}

	// --END--TRANSACTING PARTY DETAILS INSERT
	/**
	 * Inserting Deed Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean deedDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		boolean deedDetInsert = false;
		DBUtility dbUtil = new DBUtility();
		try {
			logger.info("Wipro in EstampDAo- before inserting Estamping data ");
			logger.info("Wipro in EstampDAo - deedDetInsert() before creating preparedst");
			if (param[6] != null && !param[6].equalsIgnoreCase("")) {
				String tempExem[] = param[6].split(",");
				for (int j = 0; j < tempExem.length; j++) {
					param[6] = tempExem[j];
					deedDetInsert = insertData(CommonSQL.ESTAMPING_DEED_DET_INSERT, param, dbUtil);
				}
			} else {
				param[6] = "";
				deedDetInsert = insertData(RegCommonSQL.INSERT_DEED_EXEM_DETAILS, param, dbUtil);
			}
			//dbUtil.createPreparedStatement(CommonSQL.ESTAMPING_DEED_DET_INSERT);
			logger.info("Wipro in EstampDAo - deedDetInsert() after creating preparedst");
			//deedDetInsert  =  dbUtil.executeUpdate(param);
			//logger.debug("Wipro in EstampDAo deedDetInsert  =------>"+deedDetInsert);
			logger.info("before inserting admin data ");
			if (deedDetInsert) {
				dbUtil.commit();
				IGRSCommon igrsCommon = new IGRSCommon();
				igrsCommon.saveLogDet("IGRS_ESTAMP_DEED_DETAILS", "INSERT", "T", funId, userId, roleId);
			}
			logger.info("after inserting ");
		} catch (Exception x) {
			logger.error("Exception in deedDetInsert() :- " + x);
			dbUtil.rollback();
			IGRSCommon igrsCommon = new IGRSCommon();
			igrsCommon.saveLogDet("IGRS_ESTAMP_DEED_DETAILS", "INSERT", "F", funId, userId, roleId);
		} finally {
			try {
				if (!deedDetInsert) {
					dbUtil.rollback();
					IGRSCommon igrsCommon = new IGRSCommon();
					igrsCommon.saveLogDet("IGRS_ESTAMP_DEED_DETAILS", "INSERT", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in deedDetInsert() :-" + ex);
			}
		}
		//dbUtil.closeConnection();
		return deedDetInsert;
	}

	/**
	 * Inserting Stamp Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean stampdutyDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		boolean stampDutyDetInsert = false;
		DBUtility dbUtil = null;
		try {
			logger.info("Wipro in EstampDAo- before inserting Estamping data ");
			dbUtil = new DBUtility();
			logger.info("Wipro in EstampDAo - stampdutyDetInsert() before creating preparedst");
			dbUtil.createPreparedStatement(CommonSQL.ESTAMPING_STAMP_DUTY_INSERT);
			logger.info("Wipro in EstampDAo - stampdutyDetInsert() after creating preparedst");
			stampDutyDetInsert = dbUtil.executeUpdate(param);
			logger.info("before inserting admin data ");
			if (stampDutyDetInsert) {
				dbUtil.commit();
				IGRSCommon igrsCommon = new IGRSCommon();
				igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_DETAILS", "INSERT", "T", funId, userId, roleId);
			}
			logger.info("after inserting ");
		} catch (Exception x) {
			logger.error("Exception in stampdutyDetInsert() :- " + x);
			dbUtil.rollback();
		} finally {
			try {
				if (!stampDutyDetInsert) {
					dbUtil.rollback();
					IGRSCommon igrsCommon = new IGRSCommon();
					igrsCommon.saveLogDet("IGRS_ESTAMP_TXN_DETAILS", "INSERT", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in stampdutyDetInsert() :-" + ex);
			}
		}
		dbUtil.closeConnection();
		return stampDutyDetInsert;
	}

	/**
	 * Inserting upload Document Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean upLoadDocIns(String empTxnId, String docId, String fileName, File file) throws Exception {
		boolean blnUpLoadIns = false;
		int j;
		DBUtility dbUtil = null;
		try {
			logger.info("Wipro in EstampDAo- before inserting Estamping data ");
			logger.info("Wipro in EstampDAo - upLoadDocIns() before creating preparedst");
			logger.info("Wipro in EstampDAo - upLoadDocIns() before creating preparedst");
			dbUtil = new DBUtility();
			String SQL = CommonSQL.ESTAMP_DOC_INSERT;
			FileInputStream fis = new FileInputStream(file);
			pst = dbUtil.returnPreparedStatement(SQL);
			pst.setString(1, docId);
			pst.setString(2, empTxnId);
			pst.setString(3, fileName);
			pst.setBinaryStream(4, fis, (int) fis.available());
			j = pst.executeUpdate();
			logger.info("Wipro in EstampDAo - upLoadDocIns() after creating preparedst");
			if (j > 0) {
				dbUtil.commit();
				blnUpLoadIns = true;
			} else
				dbUtil.rollback();
		} catch (Exception x) {
			logger.error("Exception in upLoadDocIns() :- " + x);
			dbUtil.rollback();
		} finally {
			dbUtil.closeConnection();
		}
		return blnUpLoadIns;
	}

	/**
	 * Inserting E-Stmp De-activation Party Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean deactEstampPartyDetIns(String param[], String roleId, String funId, String userId) throws Exception {
		boolean blnDeactIns = false;
		DBUtility dbUtil = new DBUtility();
		try {
			logger.info("Wipro in EstampDAo- deactEstampPartyDetIns() before inserting Estamping data ");
			logger.info("Wipro in EstampDAo - deactEstampPartyDetIns() before creating preparedst");
			String SQL = CommonSQL.ESTAMPING_DEACT_PARTY_INSERT;
			dbUtil.createPreparedStatement(CommonSQL.ESTAMPING_DEACT_PARTY_INSERT);
			logger.info("Wipro in EstampDAo - deactEstampPartyDetIns() after creating preparedst");
			blnDeactIns = dbUtil.executeUpdate(param);
			logger.info("Wipro in EstampDAo - deactEstampPartyDetIns() SQL====" + SQL);
			blnDeactIns = dbUtil.executeUpdate(param);
			logger.info("before inserting admin data ");
			if (blnDeactIns) {
				dbUtil.commit();
				IGRSCommon igrsCommon = new IGRSCommon();
				igrsCommon.saveLogDet("IGRS_ESTAMP_DEACT_PARTY_DTLS", "INSERT", "T", funId, userId, roleId);
			}
			logger.info("deactEstampPartyDetIns() after inserting ");
		} catch (Exception x) {
			logger.error("EstampDAo--Exception in deactEstampPartyDetIns() :- " + x);
			dbUtil.rollback();
		} finally {
			try {
				if (!blnDeactIns) {
					dbUtil.rollback();
					IGRSCommon igrsCommon = new IGRSCommon();
					igrsCommon.saveLogDet("IGRS_ESTAMP_DEACT_PARTY_DTLS", "INSERT", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("EstampDAo-- Exception in deactEstampPartyDetIns() :-" + ex);
			}
		}
		//dbUtil.closeConnection();
		return blnDeactIns;
	}

	/**
	 * Inserting E-Stmp De-activation A Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean deactEstampDetIns(String param[], String roleId, String funId, String userId) throws Exception {
		boolean blnDeactIns = false;
		DBUtility dbUtil = new DBUtility();
		try {
			logger.info("Wipro in EstampDAo-deactEstampDetIns() before inserting Estamping data ");
			logger.info("Wipro in EstampDAo - deactEstampDetIns() before creating preparedst");
			dbUtil.createPreparedStatement(CommonSQL.ESTAMPING_DEACT_INSERT);
			logger.info("Wipro in EstampDAo - deactEstampDetIns() after creating preparedst");
			blnDeactIns = dbUtil.executeUpdate(param);
			logger.info("before inserting admin data ");
			if (blnDeactIns) {
				dbUtil.commit();
			}
			logger.info("deactEstampDetIns() after inserting ");
		} catch (Exception x) {
			logger.error("EstampDAo-- Exception in deactEstampDetIns() :- " + x);
			dbUtil.rollback();
		} finally {
			try {
				if (!blnDeactIns) {
					dbUtil.rollback();
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("EstampDAo-- Exception in deactEstampDetIns() :-" + ex);
			}
		}
		//dbUtil.closeConnection();
		return blnDeactIns;
	}

	/**
	 * getting Estamp Txn ID
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getEstampTxnId() {
		String SQL = CommonSQL.ESTAMPING_TXN_ID;
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("EstampDAO in getEstampTxnId(): list.size()" + list.size());
		} catch (Exception e) {
			logger.error("EstampDAO -- Exception in getEstampTxnId():" + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * getting Estamp Non Judicial Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList printEstampTxnDet(String estampTxnId) throws Exception {
		ArrayList estampList = new ArrayList();
		logger.info("Wipro in EstampDAo - printEstampTxnDet() before EXCUTIN QUERY");
		SQL = CommonSQL.ESTAMPING_PRINT_DET;
		DBUtility dbUtil = new DBUtility();
		try {
			String arry[] = new String[1];
			if (estampTxnId != null) {
				arry[0] = estampTxnId;
			}
			dbUtil.createPreparedStatement(SQL);
			logger.info("Wipro in EstampDAo - printEstampTxnDet() AFTER creating prepared statement");
			list = dbUtil.executeQuery(arry);
			logger.info("Wipro in EstampDAo - printEstampTxnDet() AFTER EXCUTIN QUERY" + SQL);
			logger.info("Wipro in EstampDAo - printEstampTxnDet() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at DAO Class at printEstampTxnDet ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Estamp Judicial Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList printJudEstampTxnDet(String estampTxnId) throws Exception {
		ArrayList estampList = new ArrayList();
		logger.info("Wipro in EstampDAo - printJudEstampTxnDet() before EXCUTIN QUERY");
		SQL = CommonSQL.ESTAMPING_JUD_PRINT_DETA;
		logger.debug("Wipro in EstampDAo - printJudEstampTxnDet() AFTER creating prepared statement@@@@@@" + SQL);
		DBUtility dbUtil = new DBUtility();
		try {
			String arry[] = new String[1];
			if (estampTxnId != null) {
				arry[0] = estampTxnId;
			}
			dbUtil.createPreparedStatement(SQL);
			logger.info("Wipro in EstampDAo - printJudEstampTxnDet() AFTER creating prepared statement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in EstampDAo - printJudEstampTxnDet() AFTER EXCUTIN QUERY" + SQL);
			logger.info("Wipro in EstampDAo - printJudEstampTxnDet() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at DAO Class at printJudEstampTxnDet ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Registation Applicant Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getInitAppDet(String applRegId) throws Exception {
		ArrayList estampList = new ArrayList();
		DBUtility dbUtil = null;
		try {
			logger.info("Wipro in EstampDAo - getInitAppDet() before EXCUTIN QUERY");
			logger.debug("Wipro in EstampDAo - getInitAppDet() AFTER creating prepared statement@@@@@@" + SQL);
			dbUtil = new DBUtility();
			String arry[] = new String[1];
			if (applRegId != null) {
				SQL = CommonSQL.ESTAMPING_REG_INIT_QUERY + "'" + applRegId + "%'";
				logger.info("Wipro in EstampDAo - getInitAppDet() AFTER creating prepared statement");
				dbUtil.createStatement();
				list = dbUtil.executeQuery(SQL);
				logger.debug("Wipro in EstampDAo - getInitAppDet() AFTER EXCUTIN QUERY" + SQL);
				logger.info("Wipro in EstampDAo - getInitAppDet() AFTER EXCUTIN QUERY list values" + list);
			}
		} catch (Exception e) {
			logger.debug("exception in calling at DAO Class at getInitAppDet ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Party Type list Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPartyTypeList() {
		String dependence[] = new String[1];
		dependence[0] = "0";
		/* String SQL = "SELECT PARTY_TYPE_ID,PARTY_TYPE_NAME FROM IGRS_PARTY_TYPE_MASTER WHERE DEPENDENCE_ID='0'";
		 //TODO 
		*/ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.SELECT_PARTY_NAME);
			list = dbUtil.executeQuery(dependence);
			logger.info("EstampDAAO in getPartyTypeList(): list.size()" + list.size());
		} catch (Exception e) {
			logger.error("Exception in getPartyTypeList():" + e);
			logger.info("Exception in getPartyTypeList():" + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("EstampDAAO in getPartyTypeList():" + list);
		return list;
	}

	/**
	 * getting Party Type ID -party Type Master
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPartyTypeId(String party, String partyType) {
		logger.info("enter EstampDAAO in getPartyTypeId");
		/*String SQL = "SELECT PARTY_TYPE_ID FROM IGRS_PARTY_TYPE_MASTER WHERE PARTY_TYPE_NAME=? AND DEPENDENCE_ID=?";
		//TODO
		*/ArrayList list = new ArrayList();
		String arry[] = new String[2];
		arry[0] = party;
		arry[1] = partyType;
		DBUtility dbUtil = null;
		logger.info("enter before prepared statement EstampDAAO in getPartyTypeId");
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.SELECT_PARTY_TYPE_ID);
			list = dbUtil.executeQuery(arry);
			logger.info("EstampDAAO in getPartyTypeId():" + list);
		} catch (Exception e) {
			logger.error("Exception in getPartyTypeId():" + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * getting getting Deactive Txn Id
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDeactTxnId() {
		logger.info("enter EstampDAAO in getDeactTxnId");
		String SQL = CommonSQL.ESTAMPING_DEACT_TXNID_QUERY;
		ArrayList list = new ArrayList();
		logger.info(" before prepared statement EstampDAAO in getDeactTxnId SQL===" + SQL);
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			dbUtil.closeConnection();
			logger.info("EstampDAO ----- in getDeactTxnId():" + list);
		} catch (Exception e) {
			logger.error("EstampDAO ----- Exception in getDeactTxnId():" + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 *getting Registation Initiated Application Details
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getRegDeactDet(String _regIntId) throws Exception {
		ArrayList estampList = new ArrayList();
		logger.info("Wipro in EstampDAo - printEstampTxnDet() before EXCUTIN QUERY");
		SQL = CommonSQL.ESTAMPING_REG_DET_QUERY + "'" + _regIntId + "'";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in EstampDAo - getRegDeactDet() AFTER EXCUTIN QUERY" + SQL);
			logger.info("Wipro in EstampDAo - getRegDeactDet() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("Wipro in EstampDAo -Exception in calling at DAO Class at getRegDeactDet ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * printing Deactivation Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList PrintDeactDet(String _dactTxnId) throws Exception {
		ArrayList estampList = new ArrayList();
		logger.info("Wipro in EstampDAo - PrintDeactDet() before EXCUTIN QUERY");
		SQL = CommonSQL.ESTAMPING_PRINT_DEACT_QUERY + "'" + _dactTxnId + "'";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in EstampDAo - PrintDeactDet() AFTER EXCUTIN QUERY" + SQL);
			logger.info("Wipro in EstampDAo - PrintDeactDet() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("Wipro in EstampDAo -Exception in calling at DAO Class at PrintDeactDet ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/* *//**
	 * calculating Judicial Stamp Duty
	 * 
	 * @param _refstampId
	 * @param _refpetVal
	 * @param _refUnits
	 * @throws Exception
	 * @return
	 */
	/*
	     public String getJudStampDuty(String _refstampId, String _refpetVal,String _refUnits)
	     {
	    	 logger.info("in Estmap DAO  getStampDutyValue");
			String  stampDuty="";
			logger.info("in Estmap DAO before executing values getJudStampDuty ");
		 	try
		 	{
		 	stampDuty=dbUtil.getJudStampDuty(_refstampId, _refpetVal, _refUnits);
		 	}
		 	catch(Exception e)
		 	{
		 		logger.error("Exceptin in getStampDutyValue "+e);
		 		logger.info("Exceptin in getStampDutyValue "+e);
		  	}
		 	logger.info("in Estmap DAO after executing values getJudStampDuty    ");
			return stampDuty;
		
	     }*/
	/**
	 * calculating Non Judicial Stamp Duty
	 * 
	 * @param _refDeedTypeId
	 * @param _refInstrId
	 * @param _refExemId
	 * @param _secAmt
	 * @throws Exception
	 * @return
	 */
	public String getNonJudStampDuty(String _refDeedTypeId, String _refInstrId, String _refExemId, String _secAmt) {
		String stampDuty = "";
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			stampDuty = igrsCommon.getNonJudStampDuty(_refDeedTypeId, _refInstrId, _refExemId, _secAmt);
		} catch (Exception e) {
			logger.error("Exceptin in getNonJudStampDuty " + e);
		}
		return stampDuty;
	}

	/**
	 * for calculating othersFeeDuty
	 * 
	 * @param _refFunId
	 * @throws Exception
	 * @return othersFeeDuty
	 */
	public ArrayList getOthersDuty(String _retFunId, String _serId, String _userType) throws Exception {
		logger.info("Wipro in EstampDAo - getOthersDuty() before EXCUTIN QUERY");
		ArrayList othersFeeDuty = null;
		logger.info("in Estmap DAO before executing values getOthersDuty() ");
		try {
			//othersFeeDuty=igrsCommon.getOthersFeeDuty(_refFunId);
			IGRSCommon igrsCommon = new IGRSCommon();
			othersFeeDuty = igrsCommon.getOthersFeeDuty(_retFunId, _serId, _userType);
		} catch (Exception e) {
			logger.error("Exceptin in getOthersDuty() " + e);
			logger.info("Exceptin in getOthersDuty() " + e);
		}
		logger.info("in Estmap DAO after executing values getOthersDuty()");
		return othersFeeDuty;
	}

	/**
	 * getting SRO list values.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSroList(String distId) throws Exception {
		ArrayList list = new ArrayList();
		SQL = CommonSQL.MSTAMP_SRO_LIST;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(new String[] { "3", distId });
		} catch (Exception e) {
			logger.info("exception in calling at Estmap DAO  Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public ArrayList getTrNameList() {
		ArrayList list = new ArrayList();
		logger.info("Wipro in Estmap DAO - getTrNameList() before EXCUTIN QUERY");
		SQL = CommonSQL.MSTAMP_TRNAME_LIST;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.info("Wipro in Estmap DAO  - getTrNameList() BEFORE EXCUTIN QUERY" + SQL);
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in Estmap DAO  - getTrNameList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.info("exception in calling at Estmap DAO  Class at getTrNameList()  " + e);
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

	public boolean savePstampDet(String[] pstampDet) throws Exception {
		boolean boo = false;
		SQL = CommonSQL.MSTAMP_DET_INSERT;
		DBUtility dbUtil = null;
		try {
			logger.info("Estmap DAO - savePstampDet()SQL==" + SQL);
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			boo = dbUtil.executeUpdate(pstampDet);
			logger.info("Estmap DAO - savePstampDet()SQL==" + SQL);
			if (boo)
				dbUtil.commit();
		} catch (Exception e) {
			logger.info("Exception in calling at Estmap DAO  Class at savePstampDet()  " + e);
			dbUtil.rollback();
		} finally {
			dbUtil.closeConnection();
		}
		return boo;
	}

	public boolean savePstCodeDet(String[] param) throws Exception {
		boolean boo = false;
		SQL = CommonSQL.MSTAMP_CODE_INSERT;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			boo = dbUtil.executeUpdate(param);
			logger.info("Estmap DAO - savePstCodeDet()SQL==" + SQL);
			if (boo)
				dbUtil.commit();
		} catch (Exception e) {
			logger.info("exception in calling at Estmap DAO  Class at savePstCodeDet()  " + e);
			dbUtil.rollback();
		} finally {
			dbUtil.closeConnection();
		}
		return boo;
	}

	public ArrayList getMStampViewDet(String stampCodeFm, String stampCodeTo) {
		ArrayList list = new ArrayList();
		logger.info("Wipro in Estmap DAO - getMStampViewDet() before EXCUTIN QUERY");
		SQL = CommonSQL.MSTAMP_VIEWDET_SELECT;
		String arr[] = new String[2];
		DBUtility dbUtil = null;
		try {
			arr[0] = stampCodeFm;
			arr[1] = stampCodeTo;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.info("Wipro in Estmap DAO  - getMStampViewDet() BEFORE EXCUTIN QUERY" + SQL);
			list = dbUtil.executeQuery(arr);
			logger.info("Wipro in Estmap DAO  - getMStampViewDet() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.info("Exception in calling at Estmap DAO  Class at getMStampViewDet()  " + e);
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

	public boolean upLoadDocIns(ArrayList columnName, ArrayList columnValue, String tableName, String docFieldName, String txnFieldName, String docTxnId, String fileName, String filePath) {
		boolean boo = false;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			boo = dbUtil.saveDRODocuments(columnName, columnValue, tableName, docFieldName, txnFieldName, docTxnId, fileName, filePath);
			//boo = dbUtil.executeUpdate(param);
			if (boo)
				dbUtil.commit();
		} catch (Exception e) {
			logger.info("exception in calling at Estmap DAO  Class at upLoadDocIns()  " + e);
			try {
				dbUtil.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.info("exception in calling at Estmap DAO  Class at upLoadDocIns()  " + e1);
			}
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return boo;
	}

	/**
	 * getting E-Stamp Searach details
	 * 
	 * @param estampCode
	 * @return
	 */
	public ArrayList getEstampSearchDet(String estampCode) {
		ArrayList estampList = new ArrayList();
		logger.info("Wipro in EstampDAo - getEstampSearchDet() before EXCUTIN QUERY");
		SQL = CommonSQL.ESTAMPING_SEARCH_DET;
		DBUtility dbUtil = null;
		try {
			String arry[] = new String[1];
			if (estampCode != null) {
				arry[0] = estampCode;
			}
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.info("Wipro in EstampDAo - getEstampSearchDet() AFTER creating prepared statement");
			list = dbUtil.executeQuery(arry);
			logger.info("Wipro in EstampDAo - getEstampSearchDet() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at DAO Class at getEstampSearchDet ()  " + e);
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
	 * getting the deed details,Instrument details.
	 * 
	 * @param deedId
	 * @return
	 * @throws Exception
	 */
	public EstampDetailsDTO getDeedDetails(String deedId) throws Exception {
		EstampDetailsDTO dto = new EstampDetailsDTO();
		DBUtility dbUtil = new DBUtility();
		try {
			String[] deedType = new String[2];
			logger.debug("deedId:-" + deedId);
			String[] deedAry = deedId != "" ? deedId.split("~") : null;
			deedType[0] = deedAry != null ? deedAry[0] : "";
			deedType[1] = "A";
			logger.debug("deedType[0]:-" + deedType[0]);
			IGRSCommon igrsCommon = new IGRSCommon();
			//	igrsCommon = new IGRSCommon();
			ArrayList instList = igrsCommon.getInstruments(deedType);
			ArrayList list = new ArrayList();
			if (instList != null) {
				for (int i = 0; i < instList.size(); i++) {
					ArrayList listInst = (ArrayList) instList.get(i);
					EstampDetailsDTO dtoInst = new EstampDetailsDTO();
					dtoInst.setInstId(listInst.get(0).toString());
					dtoInst.setInstName(listInst.get(1).toString());
					logger.debug("listInst.get(1).toString():" + listInst.get(1).toString());
					list.add(dtoInst);
				}
			}
			dto.setInstrList(list);
			dto.setFormList(getFormFields(deedType[0], dbUtil));
		} catch (Exception e) {
			e.getStackTrace();
			logger.error("RegCompDAO in dao getDeedDetails:" + e.getStackTrace());
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("EstampDAO in dao getDeedDetails close:" + e.getStackTrace());
			}
		}
		return dto;
	}

	/**
	 * returns the Form Fields for selected deed
	 * 
	 * @param deed
	 * @return ArrayList
	 */
	public ArrayList getFormFields(String deed, DBUtility dbUtility) {
		ArrayList map = new ArrayList();
		String sql = null;
		try {
			// deed = "DEED_001";
			sql = RegCommonSQL.SELECT_FORM_FIELDS + "'" + deed + "'";
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				EstampDetailsDTO dto = new EstampDetailsDTO();
				dto.setInputAlt(subList.get(0).toString());
				dto.setInputTypeId(subList.get(1).toString());
				dto.setInputLabel(subList.get(2).toString());
				dto.setInputType(subList.get(3).toString());
				if (dto.getInputAlt().equalsIgnoreCase("number"))
					dto.setInputScript("return MyNumeric()");
				if (dto.getInputAlt().equalsIgnoreCase("text"))
					dto.setInputScript("return Alphabetic()");
				map.add(dto);
			}
		} catch (Exception e) {
			logger.error("EstampDAO in dao getFormFields:" + e.getStackTrace());
		} finally {
			try {
				if (!dbUtility.isClosed()) {
					dbUtility.closeConnection();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

	//-- Deed Type List 
	/**
	 * for getting All Deed Details
	 * 
	 * @return ArrayList
	 */
	public ArrayList getDeedTypeList() throws Exception {
		ArrayList mainList = new ArrayList();
		DBUtility dbUtil = new DBUtility();
		String sql = null;
		try {
			sql = RegCommonSQL.SELECT_DEED_TYPE_OPTIONAL;
			dbUtil.createStatement();
			mainList = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			logger.error("EstampDAO in dao getDeedTypeList:" + e.getStackTrace());
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("EstampDAO in dao getDeedTypeList close:" + e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * inserting deed attribute details
	 * 
	 * @param objEstampDet
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @param strNonJudEstTxnId
	 * @return
	 * @throws Exception
	 */
	public boolean deedAttrDet(EstampDetailsDTO objEstampDet, String roleId, String funId, String userId, String strNonJudEstTxnId) throws Exception {
		boolean boo = false;
		String param[] = new String[3];
		IGRSCommon igrsCommon = new IGRSCommon();
		DBUtility dbUtil = null;
		try {
			logger.info("Wipro in EstampDAo- deedAttrDet ");
			HashMap map = objEstampDet.getMapForm();
			Iterator it = map.entrySet().iterator();
			param[0] = strNonJudEstTxnId;
			while (it.hasNext()) {
				Map.Entry me = (Map.Entry) it.next();
				param[1] = me.getKey().toString();
				logger.debug("EstampDAo :- form Value:-" + me.getKey());
				param[2] = me.getValue().toString();
				logger.debug("EstampDAo :- form Value:-" + me.getValue());
				dbUtil = new DBUtility();
				boo = insertData(CommonSQL.INSERT_ESTAMP_DEED_ATTR_DETAILS, param, dbUtil);
			}
			if (boo)
				dbUtil.commit();
		} catch (Exception x) {
			logger.error("Exception in deedAttrDet() :- " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_ESTAMP_DEED_ATTR_MAP", "INSERT", "F", funId, userId, roleId);
		} finally {
			dbUtil.closeConnection();
		}
		if (boo)
			igrsCommon.saveLogDet("IGRS_ESTAMP_DEED_ATTR_MAP", "INSERT", "T", funId, userId, roleId);
		return boo;
	}

	/**
	 * to insert data into database
	 * 
	 * @param query
	 * @param param
	 * @return status(boolean)
	 */
	public boolean insertData(String query, String param[], DBUtility dbUtility) {
		boolean boo = false;
		try {
			dbUtility.createPreparedStatement(query);
			boo = dbUtility.executeUpdate(param);
		} catch (Exception e) {
			try {
				//dbUtility.rollback();
			} catch (Exception e1) {
				logger.error("RegCompDAO in dao insertData1:" + e.getStackTrace());
				// e.printStackTrace();
			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			logger.error("RegCompDAO in dao insertData:" + e.getStackTrace());
			// e.printStackTrace();
		}
		return boo;
	}

	/**
	 * getting deed details for estamp search
	 * 
	 * @param estampCode
	 * @return
	 */
	public ArrayList getdeedSearchDet(String estampCode) throws Exception {
		ArrayList mainList = new ArrayList();
		DBUtility dbUtil = new DBUtility();
		String sql = null;
		try {
			sql = CommonSQL.SELECT_DEED_DET + "'" + estampCode + "'";
			dbUtil.createStatement();
			mainList = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			logger.error("EstampDAO in dao getdeedSearchDet:" + e.getStackTrace());
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("EstampDAO in dao getdeedSearchDet close:" + e.getStackTrace());
			}
		}
		return mainList;
	}

	public EstampDetailsDTO getdeedAttDet(String estampCode, String deedId) throws Exception {
		ArrayList map = new ArrayList();
		EstampDetailsDTO objDto = new EstampDetailsDTO();
		String sql = null;
		DBUtility dbUtil = new DBUtility();
		sql = CommonSQL.SELECT_SEARCH_DEED_INSTR;
		try {
			String arr[] = new String[2];
			arr[0] = deedId;
			arr[1] = estampCode;
			dbUtil.createPreparedStatement(sql);
			ArrayList list = dbUtil.executeQuery(arr);
			logger.debug("EstampDAO in dao getdeedAttDet:--------->" + list);
			if (list != null) {
				ArrayList subList = null;
				for (int i = 0; i < list.size(); i++) {
					subList = (ArrayList) list.get(i);
					EstampDetailsDTO dto = new EstampDetailsDTO();
					dto.setInstId((String) subList.get(0));
					dto.setInstName((String) subList.get(1));
					map.add(dto);
				}
			}
			objDto.setInstrList(map);
			map = new ArrayList();
			sql = CommonSQL.SELECT_SEARCH_DEED_EXEM;
			dbUtil.createPreparedStatement(sql);
			ArrayList list1 = dbUtil.executeQuery(arr);
			logger.debug("EstampDAO in dao getdeedAttDet getExemption:--------->" + list1);
			if (list1 != null) {
				ArrayList subList = null;
				for (int i = 0; i < list1.size(); i++) {
					subList = (ArrayList) list1.get(i);
					EstampDetailsDTO dto = new EstampDetailsDTO();
					dto.setExemptionId((String) subList.get(0));
					dto.setExemptionName((String) subList.get(1));
					map.add(dto);
				}
			}
			objDto.setExempList(map);
		} catch (Exception e) {
			logger.error("EstampDAO in dao getdeedAttDet:" + e.getStackTrace());
		} finally {
			dbUtil.closeConnection();
		}
		return objDto;
	}

	/**
	 * getPendingEstampApps for getting pending applications of estamps from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author LAVI
	 */
	public ArrayList getPendingEstampApps(String userId, String language) {
		ArrayList pendingAppList = new ArrayList();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { userId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.GET_PENDING_APPLICATIONS_DETLS;
			} else {
				SQL = CommonSQL.GET_PENDING_APPLICATIONS_DETLS_HI;
			}
			dbUtil.createPreparedStatement(SQL);
			try {
				pendingAppList = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				pendingAppList.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return pendingAppList;
	}

	public ArrayList getDetailsOfDuties(String TxnId, String language) {
		ArrayList dutyDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.GET_DETAILS_FOR_DUTIES;
				SQL = "select a.estamp_txn_id,d.deed_type_name, i.instrument_name, du.STAMP_DUTY, du.MUNICIPAL_DUTY, du.BLOCK_DUTY, du.UPKAR_DUTY, du.TOTAL, to_char(a.created_date,'dd/mm/yyyy'), a.estamp_purpose, p.deed_duration,i.INSTRUMENT_DESCRIPTION,du.TOTAL_AFTER_EXEMP,du.REG_FEE_AFTER_EXEMP from igrs_estamp_txn_detls a, igrs_deed_type_master d, igrs_deed_instrument_master i, igrs_duty_txn_detls du, IGRS_ESTAMP_TXN_PARTY_DETLS p where a.deed_id=d.deed_type_id and a.instrument_id=i.instrument_id and a.duty_txn_id=du.duty_txn_id and p.estamp_txn_id= a.estamp_txn_id and p.deed_duration is not null and a.estamp_txn_id=?";
			} else {
				SQL = CommonSQL.GET_DETAILS_FOR_DUTIES_HI;
				SQL = "select a.estamp_txn_id,d.h_deed_type_name, i.h_instrument_name, du.STAMP_DUTY, du.MUNICIPAL_DUTY, du.BLOCK_DUTY, du.UPKAR_DUTY, du.TOTAL, to_char(a.created_date,'dd/mm/yyyy'), a.estamp_purpose, p.deed_duration,i.h_INSTRUMENT_DESCRIPTION,du.TOTAL_AFTER_EXEMP,du.REG_FEE_AFTER_EXEMP from igrs_estamp_txn_detls a, igrs_deed_type_master d, igrs_deed_instrument_master i, igrs_duty_txn_detls du, IGRS_ESTAMP_TXN_PARTY_DETLS p where a.deed_id=d.deed_type_id and a.instrument_id=i.instrument_id and a.duty_txn_id=du.duty_txn_id and p.estamp_txn_id= a.estamp_txn_id and p.deed_duration is not null and a.estamp_txn_id=?";
			}
			dbUtil.createPreparedStatement(SQL);
			try {
				dutyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				dutyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return dutyDetails;
	}

	public ArrayList getRegExemptions(String TxnId, String language) {
		ArrayList dutyDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId, "R" };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.GET_EXEMPTIONS;
			} else {
				SQL = CommonSQL.GET_EXEMPTIONS_HI;
			}
			dbUtil.createPreparedStatement(SQL);
			try {
				dutyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				dutyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return dutyDetails;
	}

	public ArrayList getStampExemeptions(String TxnId, String language) {
		ArrayList dutyDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId, "S" };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.GET_EXEMPTIONS;
			} else {
				SQL = CommonSQL.GET_EXEMPTIONS_HI;
			}
			dbUtil.createPreparedStatement(SQL);
			try {
				dutyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				dutyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return dutyDetails;
	}

	public ArrayList getUserEneterableList(String TxnId, String language) {
		ArrayList dutyDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.USER_FIELDS;
			} else {
				SQL = CommonSQL.USER_FIELDS_HI;
			}
			dbUtil.createPreparedStatement(SQL);
			try {
				dutyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				dutyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return dutyDetails;
	}

	public ArrayList getParaDetls(String txnId, String language) {
		ArrayList alist = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String sql;
			String param[] = new String[1];
			param[0] = txnId;
			if ("en".equalsIgnoreCase(language)) {
				sql = "SELECT B.FIELD_NAME, A.FIELD_VALUE FROM igrs_estamp_txn_detls ab, IGRS_PROP_DUTY_FIELD_DETLS A, IGRS_INST_FIELD_MAPPING B WHERE ab.estamp_txn_id=? and A.INST_FIELD_MAP_ID = B.INST_FIELD_MAP_ID AND a.duty_txn_id  =ab.duty_txn_id";
			} else {
				sql = "SELECT B.FIELD_NAME, A.FIELD_VALUE FROM igrs_estamp_txn_detls ab, IGRS_PROP_DUTY_FIELD_DETLS A, IGRS_INST_FIELD_MAPPING B WHERE ab.estamp_txn_id=? and A.INST_FIELD_MAP_ID = B.INST_FIELD_MAP_ID AND a.duty_txn_id  =ab.duty_txn_id";
			}
			dbUtil.createPreparedStatement(sql);
			alist = dbUtil.executeQuery(param);
			dbUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alist;
	}

	public ArrayList getDetailsOfApplicant(String TxnId, String language) {
		ArrayList partyDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.GET_DETAILS_FOR_PARTY;
			} else {
				SQL = CommonSQL.GET_DETAILS_FOR_PARTY_HI;
			}
			logger.debug("***************" + SQL);
			dbUtil.createPreparedStatement(SQL);
			try {
				partyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				partyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return partyDetails;
	}

	public ArrayList getDetailsOfDocument(String TxnId) {
		ArrayList documentDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_DETAILS_FOR_DOCUMENT;
			dbUtil.createPreparedStatement(SQL);
			try {
				documentDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				documentDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return documentDetails;
	}

	//Added By Lavi for E-Stamp Search
	public ArrayList checkEcode(String ecode) {
		ArrayList ecodeId = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ECODE_ID;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeId = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getEcodeId() after dbUtil.executeQuery(SQL)");
				ecodeId.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeId;
	}

	public ArrayList viewEcodeDetails(String ecode) {
		ArrayList ecodeId = new ArrayList();
		String estampid = ecode.substring(3, 4);
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if (estampid.equalsIgnoreCase("2")) {
				SQL = CommonSQL.GET_RU_ECODE_DETAILS_JUD;
				dbUtil.createPreparedStatement(SQL);
			} else if (estampid.equalsIgnoreCase("1")) {
				SQL = CommonSQL.GET_RU_ECODE_DETAILS_NONJUD;
				dbUtil.createPreparedStatement(SQL);
			}
			try {
				ecodeId = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - viewEcodeDetails() after dbUtil.executeQuery(SQL)");
				ecodeId.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeId;
	}

	public ArrayList viewEcodeType(String ecodeTypeID) {
		ArrayList ecodeType = new ArrayList();
		String[] param = { ecodeTypeID };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_RU_ECODE_DETAILS_TYPE;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeType = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - viewEcodeDetails() after dbUtil.executeQuery(SQL)");
				ecodeType.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeType;
	}

	public ArrayList viewEcodeDetailsDRS(String ecode) {
		ArrayList ecodeDetails = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_DRS_ECODE_DETAILS;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - viewEcodeDetailsDRS() after dbUtil.executeQuery(SQL)");
				ecodeDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDetails;
	}

	public ArrayList getDetailsOfApplicantDRS(String TxnId, String language) {
		ArrayList partyDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if (language.equalsIgnoreCase("en")) {
				SQL = CommonSQL.GET_DRS_ORG_ECODE_DETAILS_FOR_PARTY;
			} else {
				SQL = CommonSQL.GET_DRS_ORG_ECODE_DETAILS_FOR_PARTY_HI;
			}
			logger.debug("***************" + SQL);
			dbUtil.createPreparedStatement(SQL);
			try {
				partyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getDetailsOfApplicantDRS() after dbUtil.executeQuery(SQL)");
				partyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return partyDetails;
	}

	public ArrayList deactEcodeDetails(String ecode) {
		ArrayList ecodeDeactDetails = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ESTAMP_DETAILS_FOR_DEACTIVATION;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDeactDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - deactEcodeDetails() after dbUtil.executeQuery(SQL)");
				ecodeDeactDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDeactDetails;
	}

	public ArrayList deactEcodeDetailsZero(String ecode) {
		ArrayList ecodeDeactDetails = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ESTAMP_DEACT_DETLS_ZERO;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDeactDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - deactEcodeDetails() after dbUtil.executeQuery(SQL)");
				ecodeDeactDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDeactDetails;
	}

	public ArrayList deactDocTypeJud(String ecode) {
		ArrayList ecodeDocTypeJud = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ESTAMP_DETAILS_FOR_DEACT_JUD;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDocTypeJud = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - deactDocTypeJud() after dbUtil.executeQuery(SQL)");
				ecodeDocTypeJud.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDocTypeJud;
	}

	public ArrayList estampAmount(String ecode) {
		ArrayList ecodeDocTypeJud = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ESTAMP_AMOUNT;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDocTypeJud = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - deactDocTypeJud() after dbUtil.executeQuery(SQL)");
				ecodeDocTypeJud.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDocTypeJud;
	}

	public ArrayList deactEcodeDetailsJudZero(String ecode) {
		ArrayList ecodeDeactDetails = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ESTAMP_DEACT_DETLS_JUD_ZERO;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDeactDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - deactEcodeDetails() after dbUtil.executeQuery(SQL)");
				ecodeDeactDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDeactDetails;
	}

	public ArrayList deactEcodeDate() {
		ArrayList ecodeDeactDate = new ArrayList();
		//String[] param={ecode};
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_DEACTIVATION_DATE;
			dbUtil.createStatement();
			try {
				ecodeDeactDate = dbUtil.executeQuery(SQL);
				//ecodeDeactDate=dbUtil.executeQuery(SQL);
				logger.debug("Wipro in EstampDAO - deactEcodeDate() after dbUtil.executeQuery(SQL)");
				ecodeDeactDate.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDeactDate;
	}

	public ArrayList deactEcodeAppDetails(String ecode) {
		ArrayList ecodeDeactAppDetails = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_APP_DETAILS_FOR_DEACTIVATION;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDeactAppDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - deactEcodeAppDetails() after dbUtil.executeQuery(SQL)");
				ecodeDeactAppDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDeactAppDetails;
	}

	public ArrayList deactEcodePartyDetails(String ecode) {
		ArrayList ecodeDeactPartyDetails = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_PARTY_DETAILS_FOR_DEACTIVATION;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDeactPartyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - deactEcodePartyDetails() after dbUtil.executeQuery(SQL)");
				ecodeDeactPartyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDeactPartyDetails;
	}

	public ArrayList deactDRName(String userId) {
		ArrayList ecodeDeactPartyDetails = new ArrayList();
		String[] param = { userId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_DEACTIVATION_DR_NAME;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDeactPartyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - deactDRName() after dbUtil.executeQuery(SQL)");
				ecodeDeactPartyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDeactPartyDetails;
	}

	// for the creation of deactivation request number and insertion of all the details of deactivation in the table.
	public boolean insertDeactDetails(DutyCalculationForm eform, DashBoardDTO dto, String userId) {
		boolean transactionflag = false;
		//String estmTxnId = null;
		String deactCode = null;
		String deactEcodetable[] = new String[11];
		Date date = new Date();
		Format yearformat = new SimpleDateFormat("yyyy");
		Format monthformat = new SimpleDateFormat("MM");
		// Format dateformat  = new SimpleDateFormat("dd");
		// String dfmt = dateformat.format(date);
		String yfmt = yearformat.format(date);
		String mfmt = monthformat.format(date);
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(true);
			//for  deactivate table and deactivate_request_id
			/* String SQL = "SELECT TO_CHAR(SYSDATE, 'DD/MM/YYYY') FROM DUAL";

			*///logger.debug(SQL);
			dbUtil.createStatement();
			logger.debug("******* Statement created");
			String number1 = dbUtil.executeQry(CommonSQL.SELECT_SYSDATE);
			logger.debug("*******" + number1);
			logger.info("*******" + number1);
			System.out.println("*******" + number1);
			String subDate = number1.substring(0, 2);
			logger.debug("******* Substring obtained" + subDate);
			if (subDate.equalsIgnoreCase("01")) {
				/*String sql= "SELECT COUNT(DEACT_REQUEST_ID) FROM IGRS_ESTAMP_DEACTIVATION_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";

				*/dbUtil.createStatement();
				String countDeactId = dbUtil.executeQry(CommonSQL.SELECT_DEACT_REQSTID);
				if (countDeactId.equalsIgnoreCase("0")) {
					logger.debug("in if clause");
					/*String drop_query = "DROP SEQUENCE IGRS_ESTAMP_DEACT_REQST_SEQ";

					*/dbUtil.createStatement();
					dbUtil.executeUpdate(CommonSQL.DROP_SEQUNCE);
					/*String create_query = "CREATE SEQUENCE IGRS_ESTAMP_DEACT_REQST_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 9999999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";

					*/dbUtil.createStatement();
					dbUtil.executeUpdate(CommonSQL.CREATE_SEQUNCE);
				}
			}
			//System.out.println("Not in If condition");  
			/*String SQL1 = "select IGRS_ESTAMP_DEACT_REQST_SEQ.nextval from dual";

			*/dbUtil.createStatement();
			String number2 = dbUtil.executeQry(CommonSQL.SELECT_SQUENCE);
			if (number2.length() == 1) {
				number2 = "0000" + number2;
			} else if (number2.length() == 2) {
				number2 = "000" + number2;
			} else if (number2.length() == 3) {
				number2 = "00" + number2;
			} else if (number2.length() == 4) {
				number2 = "0" + number2;
			}
			logger.debug("---------------------------->" + dto.getEstampId());
			String estampCode = dto.getEstampId().toString();
			String appdis = dto.getEstampId().toString().substring(4, 6);
			String eid = estampCode.substring(2, 4);
			deactCode = "RR" + eid + appdis + mfmt + yfmt + number2;
			//eform.setEcode(deactCode);
			/*String SQL3 = "select IGRS_ESTAMP_DEACTIVATE_ID_SEQ.nextval from dual";

			*/dbUtil.createStatement();
			String number3 = dbUtil.executeQry(CommonSQL.SELECT_SEQUNCE2);
			deactEcodetable[0] = number3;
			deactEcodetable[1] = estampCode;
			deactEcodetable[10] = estampCode;
			deactEcodetable[9] = (String) dto.getDrName();
			deactEcodetable[2] = deactCode;
			deactEcodetable[3] = dto.getPersName().toString();
			deactEcodetable[4] = dto.getPersFatherName().toString();
			deactEcodetable[5] = dto.getPersMotherName().toString();
			deactEcodetable[6] = dto.getPersAddress().toString();
			deactEcodetable[7] = dto.getPersPhnNo().toString();
			deactEcodetable[8] = dto.getPersMobNo().toString();
			deactEcodetable[9] = userId;
			deactEcodetable[10] = dto.getEstampId().toString();
			dbUtil.createPreparedStatement(CommonSQL.ESTAMP_DEACT_DTL_TABLE_INSERT);
			dbUtil.executeUpdate(deactEcodetable);
			transactionflag = true;
			dbUtil.commit();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			// dbUtil.rollback();
			throw ne;
		} catch (SQLException se) {
			se.printStackTrace();
			//dbUtil.rollback();
			logger.error("SQL Exception at estamp  in DAO " + se);
			//throw se;
		} catch (Exception e) {
			e.printStackTrace();
			//dbUtil.rollback();
			logger.error(" Exception at estamp in DAO " + e);
			//throw e;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Exception at estamp in DAO  " + e);
			}
		}
		//eform.setMainTxnId(estmTxnId);
		return transactionflag;
	}

	public ArrayList deactRequestId(DashBoardDTO dto) {
		ArrayList deactRequestId = new ArrayList();
		String[] param = { dto.getEstampId().toString() };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ESTAMP_DEACT_REQUEST_ID;
			dbUtil.createPreparedStatement(SQL);
			try {
				deactRequestId = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - deactEcodeDetails() after dbUtil.executeQuery(SQL)");
				deactRequestId.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return deactRequestId;
	}

	public ArrayList checkEcodeDeact(String ecode) {
		ArrayList ecodeId = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ECODE_ID_DEACT;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeId = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getEcodeIdDeact() after dbUtil.executeQuery(SQL)");
				ecodeId.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeId;
	}

	public ArrayList checkEcodeExp(String ecode) {
		ArrayList ecodeIdExp = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ECODE_ID_EXP;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeIdExp = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getEcodeIdDeact() after dbUtil.executeQuery(SQL)");
				ecodeIdExp.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeIdExp;
	}

	public ArrayList checkEcodeDeactv(String ecode) {
		ArrayList ecodeIdDeactv = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ECODE_ID_DEACTVATD;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeIdDeactv = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - checkEcodeDeatv() after dbUtil.executeQuery(SQL)");
				ecodeIdDeactv.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeIdDeactv;
	}

	public ArrayList checkEcodeConsumed(String ecode) {
		ArrayList ecodeIdDeactv = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ECODE_ID_CONSUMD;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeIdDeactv = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - checkEcodeDeatv() after dbUtil.executeQuery(SQL)");
				ecodeIdDeactv.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeIdDeactv;
	}

	//ADDED BY LAVI FOR JUDICIAL ESTAMPS
	public ArrayList getPendingApps(String userId, String lang) {
		ArrayList pendingAppList = new ArrayList();
		String[] param = { userId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(lang)) {
				SQL = CommonSQL.GET_PENDING_APPLICATIONS_DETLS_JUD;
			} else {
				SQL = CommonSQL.GET_PENDING_APPLICATIONS_DETLS_JUD_H;
			}
			dbUtil.createPreparedStatement(SQL);
			try {
				pendingAppList = dbUtil.executeQuery(param);
				logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
				pendingAppList.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return pendingAppList;
	}

	/******************************************************************
	 * Method Name : insertDocDetls() Arguments : Form Return : Boolean Throws :
	 * NullPointer or SQLException or Exception
	 *******************************************************************/
	public boolean insertDocDetls(EstampFormBean eform) throws NullPointerException, SQLException, Exception {
		boolean transactionflag = false;
		String docTbl[] = new String[6];
		String uid = eform.getObjEstampDet().getUid().toString();
		String docname = eform.getObjEstampDet().getDoc().toString();
		String docPath = eform.getObjEstampDet().getDocPath();
		//docname = eform.getDocname();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.setAutoCommit(false);
			//for  txn table and estamp_txn_id
			//for document table
			/*String SQL4 = "select IGRS_ESTAMP_DOC_ID_SEQ.nextval from dual";

			*/transmgtUtil.createStatement();
			String number4 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUNCE3);
			docTbl[0] = number4;
			docTbl[1] = (String) eform.getObjEstampDet().getMainTxnId();
			docTbl[2] = docname;
			docTbl[3] = "A";
			docTbl[4] = uid;
			docTbl[5] = docPath;
			if (!docname.equalsIgnoreCase("")) {
				transmgtUtil.createPreparedStatement(CommonSQL.DOCUMENT_TABLE_INSERT);
				transmgtUtil.executeUpdate(docTbl);
			}
			transactionflag = true;
			transmgtUtil.commit();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			transmgtUtil.rollback();
			throw ne;
		} catch (SQLException se) {
			se.printStackTrace();
			transmgtUtil.rollback();
			logger.error("SQL Exception at estamp  in DAO " + se);
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			transmgtUtil.rollback();
			logger.error(" Exception at estamp in DAO " + e);
			throw e;
		} finally {
			try {
				transmgtUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Exception at estamp in DAO  " + e);
			}
		}
		return transactionflag;
	}

	public synchronized ArrayList getDocType(String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getCountry() - start");
		}
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.debug("Wipro in IGRSCommon - " + "getDocType() after create statement");
			if ("en".equalsIgnoreCase(language)) {
				list = dbUtil.executeQuery(CommonSQL.DOCUMENT_TYPE_QUERY);
			} else {
				list = dbUtil.executeQuery(CommonSQL.DOCUMENT_TYPE_QUERY_H);
			}
			logger.debug("Wipro in IGRSCommon - " + "getDocType() after execute query");
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public String getDate() {
		String date = null;
		//String[] param={ecode};
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_DEACTIVATION_DATE;
			dbUtil.createStatement();
			try {
				date = dbUtil.executeQry(SQL);
				//ecodeDeactDate=dbUtil.executeQuery(SQL);
				logger.debug("Wipro in EstampDAO - getDate() after dbUtil.executeQry(SQL)");
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return date;
	}

	public ArrayList getPayDtls(String txnId) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		DBUtility transmgtUtil = null;
		ArrayList list = null;
		try {
			list = new ArrayList();
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_PAY_DTLS);
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			logger.debug(e);
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return list;
	}

	public String getDuty(String txnId) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		String dty = "";
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_DUTY_DTLS);
			dty = transmgtUtil.executeQry(tid);
		} catch (Exception e) {
			logger.debug(e);
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return dty;
	}

	// added by gulrej
	// This method updates IGRS_ESTAMP_PAYMENT_DETLS on basis of ESTAMP_TXN_ID
	public boolean updatePay1(EstampFormBean eform, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		boolean transactionflag = false;
		//String estmTxnId = null;
		String paytable[] = new String[3];
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.setAutoCommit(false);
			/*String SQL1 = "select IGRS_ESTAMP_PAYMENT_ID_SEQ.nextval from dual";

			*/transmgtUtil.createStatement();
			//String number1 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUNCE4);
			//eform.getObjEstampDet().setUniqKey(number1);
			//paytable[0] =number1;
			paytable[2] = eform.getObjEstampDet().getMainTxnId().toString();
			paytable[0] = Double.toString(eform.getObjEstampDet().getTotal());
			paytable[1] = "I";
			//paytable[4] =eform.getObjEstampDet().getUid().toString();
			transmgtUtil.createPreparedStatement(CommonSQL.PAY_TABLE_UPDATE1);
			transmgtUtil.executeUpdate(paytable);
			transactionflag = true;
			transmgtUtil.commit();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			transmgtUtil.rollback();
			throw ne;
		} catch (SQLException se) {
			se.printStackTrace();
			transmgtUtil.rollback();
			logger.error("SQL Exception at updatePay1  in DAO " + se);
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			transmgtUtil.rollback();
			logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
			throw e;
		} finally {
			try {
				transmgtUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			}
		}
		//eform.setMainTxnId(estmTxnId);
		return transactionflag;
	}

	public boolean insertPay(EstampFormBean eform, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		boolean transactionflag = false;
		//String estmTxnId = null;
		String paytable[] = new String[5];
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.setAutoCommit(false);
			/*String SQL1 = "select IGRS_ESTAMP_PAYMENT_ID_SEQ.nextval from dual";

			*/transmgtUtil.createStatement();
			String number1 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUNCE4);
			eform.getObjEstampDet().setUniqKey(number1);
			paytable[0] = number1;
			paytable[1] = eform.getObjEstampDet().getMainTxnId().toString();
			paytable[2] = Double.toString(eform.getObjEstampDet().getTotal());
			paytable[3] = "I";
			paytable[4] = eform.getObjEstampDet().getUid().toString();
			transmgtUtil.createPreparedStatement(CommonSQL.PAY_TABLE_INSERT);
			transmgtUtil.executeUpdate(paytable);
			transactionflag = true;
			transmgtUtil.commit();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			transmgtUtil.rollback();
			throw ne;
		} catch (SQLException se) {
			se.printStackTrace();
			transmgtUtil.rollback();
			logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se);
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			transmgtUtil.rollback();
			logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
			throw e;
		} finally {
			try {
				transmgtUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			}
		}
		//eform.setMainTxnId(estmTxnId);
		return transactionflag;
	}

	public boolean updatePay(EstampFormBean eform, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		boolean transactionflag = false;
		//String estmTxnId = null;
		String paytable[] = new String[1];
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.setAutoCommit(false);
			paytable[0] = eform.getObjEstampDet().getMainTxnId().toString();
			transmgtUtil.createPreparedStatement(CommonSQL.Delete_from_estamp_pay);
			transmgtUtil.executeUpdate(paytable);
			transactionflag = true;
			transmgtUtil.commit();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			transmgtUtil.rollback();
			throw ne;
		} catch (SQLException se) {
			se.printStackTrace();
			transmgtUtil.rollback();
			logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se);
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			transmgtUtil.rollback();
			logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
			throw e;
		} finally {
			try {
				transmgtUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			}
		}
		//eform.setMainTxnId(estmTxnId);
		return transactionflag;
	}

	//ADDED BY LAVI FOR UPDATION OF COMPLETION STATUS IN ESTAMP TABLE
	public boolean updateStatus(EstampFormBean form) throws Exception {
		String tid[] = new String[1];
		tid[0] = form.getObjEstampDet().getMainTxnId().toString();
		logger.info("inside dao of update status judicial");
		logger.info("--------->" + form.getObjEstampDet().getMainTxnId());
		boolean flag = false;
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.UPDATE_ESTAMP_STATUS_JUD);
			flag = transmgtUtil.executeUpdate(tid);
		} catch (Exception e) {
			logger.debug(e);
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return flag;
	}

	public ArrayList getruName(String txnId, String language) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_RU_NAME_DTLS);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_RU_NAME_DTLS_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public String getspName(String txnId) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		String dty = "";
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_NAME_DTLS);
			dty = transmgtUtil.executeQry(tid);
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			transmgtUtil.closeConnection();
		}
		return dty;
	}

	public ArrayList getspDtls(String txnId, String lang) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if (lang.equalsIgnoreCase("en")) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_DTLS);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_DTLS_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getspbnkDtls(String txnId, String language) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_BNK_DTLS);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_BNK_DTLS_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			logger.debug(e);
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getAppDtls(String txnId, String language) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_APP_DTLS);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_APP_DTLS_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
			list.trimToSize();
		}
		return list;
	}

	public ArrayList getPartyDtls(String txnId, String language) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
		if ("en".equalsIgnoreCase(language)) {
			transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DTLS);
		} else {
			transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DTLS_HI);
		}
		list = transmgtUtil.executeQuery(tid);
		transmgtUtil.closeConnection();
		list.trimToSize();
		return list;
	}

	public boolean inserteCode(EstampFormBean eform, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		boolean transactionflag = false;
		//String estmTxnId = null;
		String ecode = null;
		String ecodetable[] = new String[16];
		String paytbl[] = new String[2];
		String updtValidityDate[] = new String[2];
		Date date = new Date();
		Format yearformat = new SimpleDateFormat("yyyy");
		Format monthformat = new SimpleDateFormat("MM");
		Format dateformat = new SimpleDateFormat("dd");
		String dfmt = dateformat.format(date);
		String yfmt = yearformat.format(date);
		String mfmt = monthformat.format(date);
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.setAutoCommit(false);
			//for  txn table and estamp_txn_id
			String SQL1 = "SELECT COUNT(ESTAMP_CODE) FROM IGRS_ESTAMP_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
			logger.debug(SQL1);
			transmgtUtil.createStatement();
			String number1 = transmgtUtil.executeQry(CommonSQL.SELECT_ESTAMP_COUNT1);
			if (number1.equalsIgnoreCase("0")) {
				logger.debug("in if clause");
				/*String drpqry = "DROP SEQUENCE IGRS_ESTAMP_CODE_SEQ";

				*/
				transmgtUtil.createStatement();
				transmgtUtil.executeUpdate(CommonSQL.DROP_ESTAMP_SQUNCE);
				/*String crtqry = "CREATE SEQUENCE IGRS_ESTAMP_CODE_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";

				*/transmgtUtil.createStatement();
				transmgtUtil.executeUpdate(CommonSQL.SELECT_ESTAMP_COUNT2);
			}
			String SQL2 = "select IGRS_ESTAMP_CODE_SEQ.nextval from dual";
			transmgtUtil.createStatement();
			String number2 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUNCE5);
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
			String appdis = eform.getObjEstampDet().getAppDistrict().toString();
			if (appdis.length() == 1) {
				appdis = "0" + appdis;
			}
			ecode = "0102" + appdis + dfmt + mfmt + yfmt + number2;
			eform.getObjEstampDet().setEcode(ecode);
			/* String SQL3 = "select IGRS_ESTAMP_TRANSACTION_ID_SEQ.nextval from dual";

			*/transmgtUtil.createStatement();
			String number3 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUNCE6);
			ecodetable[0] = number3;
			ecodetable[1] = eform.getObjEstampDet().getMainTxnId().toString();
			ecodetable[2] = ecode;
			ecodetable[3] = eform.getObjEstampDet().getUid().toString();
			ecodetable[4] = "022";
			ecodetable[5] = eform.getObjEstampDet().getAmount().toString();
			ecodetable[6] = "2";
			ecodetable[7] = "";
			ecodetable[8] = null;// validity date according to the configurable param-----yet to be done
			ecodetable[9] = "A";
			ecodetable[10] = eform.getObjEstampDet().getEstampPurps().toString();
			ecodetable[11] = "E"; ///source mod code----master is yet to be made
			ecodetable[12] = eform.getObjEstampDet().getIssuedPlace();
			ecodetable[13] = eform.getObjEstampDet().getOfficeName();
			ecodetable[14] = eform.getObjEstampDet().getUserName();
			ecodetable[15] = eform.getObjEstampDet().getRole();
			paytbl[0] = eform.getObjEstampDet().getUid().toString();
			paytbl[1] = eform.getObjEstampDet().getMainTxnId().toString();
			updtValidityDate[0] = eform.getObjEstampDet().getMainTxnId().toString();
			updtValidityDate[1] = eform.getObjEstampDet().getMainTxnId().toString();
			transmgtUtil.createPreparedStatement(CommonSQL.ESTAMP_DTL_TABLE_INSERT);
			transmgtUtil.executeUpdate(ecodetable);
			// Added by Lavi for the updation of validity date in the table
			transmgtUtil.createPreparedStatement(CommonSQL.UPDT_ECODE_VALID_DATE);
			transmgtUtil.executeUpdate(updtValidityDate);
			// end of updation
			transmgtUtil.createPreparedStatement(CommonSQL.PAY_TABLE_UPDATE);
			transmgtUtil.executeUpdate(paytbl);
			transmgtUtil.createPreparedStatement(CommonSQL.PAY_TABLE_UPDATE);
			transmgtUtil.executeUpdate(paytbl);
			transactionflag = true;
			transmgtUtil.commit();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			transmgtUtil.rollback();
			throw ne;
		} catch (SQLException se) {
			se.printStackTrace();
			transmgtUtil.rollback();
			logger.error("SQL Exception at estamp  in DAO " + se);
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			transmgtUtil.rollback();
			logger.error(" Exception at estamp in DAO " + e);
			throw e;
		} finally {
			try {
				transmgtUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Exception at estamp in DAO  " + e);
			}
		}
		//eform.setMainTxnId(estmTxnId);
		return transactionflag;
	}

	public ArrayList getEcodeDtls(String txnId) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_ECODE_DTLS);
			list = transmgtUtil.executeQuery(tid);
			list.trimToSize();
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e);
		} finally {
			transmgtUtil.closeConnection();
		}
		return list;
	}

	public boolean updateTxn(EstampFormBean eform, String lang) throws NullPointerException, SQLException, Exception {
		boolean transactionflag = false;
		String txntable[] = new String[3];
		String judtable[] = new String[10];
		String docTbl[] = new String[4];
		String appdtl[] = new String[26];
		String partydtl[] = new String[26];
		String estmTxnId = eform.getObjEstampDet().getMainTxnId().toString();
		String aptype = eform.getObjEstampDet().getAppType().toString();
		String apfname = eform.getObjEstampDet().getAppFirsName().toString();
		String apmdname = eform.getObjEstampDet().getAppMiddleName().toString();
		String aplname = eform.getObjEstampDet().getAppLastName().toString();
		String apgender = eform.getObjEstampDet().getAppGender().toString();
		String appdy = eform.getObjEstampDet().getAppDay().toString();
		String apmnth = eform.getObjEstampDet().getAppMonth().toString();
		String apyr = eform.getObjEstampDet().getAppYear().toString();
		String apfthrname = eform.getObjEstampDet().getAppFatherName().toString();
		String apmthrname = eform.getObjEstampDet().getAppMotherName().toString();
		String apadrs = eform.getObjEstampDet().getAppAddress().toString();
		String apcntry = eform.getObjEstampDet().getAppCountry();
		String apstate = eform.getObjEstampDet().getAppState();
		String apdstrct = eform.getObjEstampDet().getAppDistrict();
		String appostlcd = eform.getObjEstampDet().getAppPostalCode().toString();
		String apmob = eform.getObjEstampDet().getAppMobno().toString();
		String apphn = eform.getObjEstampDet().getAppPhno().toString();
		String apemail = eform.getObjEstampDet().getAppEmailID().toString();
		String apphotoId = eform.getObjEstampDet().getAppPhotoId().toString();
		String apphotoidno = eform.getObjEstampDet().getAppPhotoIdno().toString();
		String appersns = eform.getObjEstampDet().getAppPersons().toString();
		String aporgname = eform.getObjEstampDet().getAppOrgName();
		String apauthfname = eform.getObjEstampDet().getAppAuthFirstName();
		String apauthmdname = eform.getObjEstampDet().getAppAuthMiddleName();
		String apauthlname = eform.getObjEstampDet().getAppAuthLastName();
		String apbnknme = eform.getObjEstampDet().getAppBankName();
		String apbnmadrs = eform.getObjEstampDet().getAppBankAddress();
		//String deedDuratn = eform.getDeedDuration();
		String prtytype = eform.getObjEstampDet().getPartyType().toString();
		String prtyfname = eform.getObjEstampDet().getPartyFirsName().toString();
		String prtymdname = eform.getObjEstampDet().getPartyMiddleName().toString();
		String prtylname = eform.getObjEstampDet().getPartyLastName().toString();
		String prtygender = eform.getObjEstampDet().getPartyGender().toString();
		String prtydy = eform.getObjEstampDet().getPartyDay().toString();
		String prtymnth = eform.getObjEstampDet().getPartyMonth().toString();
		String prtyyr = eform.getObjEstampDet().getPartyYear().toString();
		String prtyfthrname = eform.getObjEstampDet().getPartyFatherName().toString();
		String prtymthrname = eform.getObjEstampDet().getPartyMotherName().toString();
		String prtyadrs = eform.getObjEstampDet().getPartyAddress().toString();
		String prtycntry = eform.getObjEstampDet().getPartyCountry();
		String prtystate = eform.getObjEstampDet().getPartyState();
		String prtydstrct = eform.getObjEstampDet().getPartyDistrict();
		String prtypostlcd = eform.getObjEstampDet().getPartyPostalCode().toString();
		String prtymob = eform.getObjEstampDet().getPartyMobno().toString();
		String prtyphn = eform.getObjEstampDet().getPartyPhno().toString();
		String prtyemail = eform.getObjEstampDet().getPartyEmailID().toString();
		String prtyphotoId = eform.getObjEstampDet().getPartyPhotoId().toString();
		String prtyphotoidno = eform.getObjEstampDet().getPartyPhotoIdno().toString();
		String prtypersns = eform.getObjEstampDet().getPartyPersons().toString();
		String prtyorgname = eform.getObjEstampDet().getPartyOrgName();
		String prtyauthfname = eform.getObjEstampDet().getPartyAuthFirstName();
		String prtyauthmdname = eform.getObjEstampDet().getPartyAuthMiddleName();
		String prtyauthlname = eform.getObjEstampDet().getPartyAuthLastName();
		String prtybnknme = eform.getObjEstampDet().getPartyBankName();
		String prtybnmadrs = eform.getObjEstampDet().getPartyBankAddress();
		String estmpurpose = eform.getObjEstampDet().getEstampPurps().toString();
		String uid = eform.getObjEstampDet().getUid().toString();
		String docname = eform.getObjEstampDet().getDoc().toString();
		String docPath = eform.getObjEstampDet().getDocPath();
		//String docname = eform.getDocname();
		//**********ADDED BY SIMRAN*******///
		if (apgender.equalsIgnoreCase("M")) {
			if (lang.equalsIgnoreCase("en"))
				eform.getObjEstampDet().setAppGenderDisp("Male");
			else
				eform.getObjEstampDet().setAppGenderDisp("पुस्र्ष");
		} else {
			if (lang.equalsIgnoreCase("en"))
				eform.getObjEstampDet().setAppGenderDisp("Female");
			else
				eform.getObjEstampDet().setAppGenderDisp("महिला");
		}
		if (prtygender.equalsIgnoreCase("M")) {
			if (lang.equalsIgnoreCase("en"))
				eform.getObjEstampDet().setPartyGenderDisp("Male");
			else
				eform.getObjEstampDet().setPartyGenderDisp("पुस्र्ष");
		} else {
			if (lang.equalsIgnoreCase("en"))
				eform.getObjEstampDet().setPartyGenderDisp("Female");
			else
				eform.getObjEstampDet().setPartyGenderDisp("महिला");
		}
		//***********END**********************
		Date date = new Date();
		Format yearformat = new SimpleDateFormat("yy");
		Format monthformat = new SimpleDateFormat("MM");
		Format dateformat = new SimpleDateFormat("dd");
		String dfmt = dateformat.format(date);
		String yfmt = yearformat.format(date);
		String mfmt = monthformat.format(date);
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.setAutoCommit(false);
			//for  txn table and estamp_txn_id
			txntable[0] = estmpurpose;
			txntable[1] = uid;
			txntable[2] = estmTxnId;
			String txnTableArray = eform.getObjEstampDet().getCourt();
			String array[] = txnTableArray.split("::");
			judtable[0] = eform.getObjEstampDet().getCourtDocType().toString();
			judtable[1] = array[0];
			judtable[2] = eform.getObjEstampDet().getPartyCountry().toString();
			judtable[3] = eform.getObjEstampDet().getPartyState().toString();
			judtable[4] = eform.getObjEstampDet().getDistt().toString();
			judtable[5] = eform.getObjEstampDet().getAmount().toString();
			judtable[6] = eform.getObjEstampDet().getEstampPurps().toString();
			judtable[7] = uid;
			judtable[8] = array[1];
			judtable[9] = estmTxnId;
			//for duty table
			//for document table
			docTbl[0] = docname;
			docTbl[1] = uid;
			docTbl[2] = docPath;
			docTbl[3] = estmTxnId;
			//for txn_party table-applicant
			appdtl[0] = aptype;
			if (aptype.equalsIgnoreCase("1")) {
				appdtl[1] = apauthfname;
				appdtl[2] = apauthmdname;
				appdtl[3] = apauthlname;
				appdtl[4] = "";
				appdtl[5] = "";
			} else {
				appdtl[1] = apfname;
				appdtl[2] = apmdname;
				appdtl[3] = aplname;
				appdtl[5] = appdy + "-" + apmnth + "-" + apyr;
				//SIMRAN
				//if(lang.equalsIgnoreCase("hi"))
				//{
				String mnthDisp = "";
				if (apmnth.equalsIgnoreCase("JAN"))
					mnthDisp = "01";
				else if (apmnth.equalsIgnoreCase("FEB"))
					mnthDisp = "02";
				else if (apmnth.equalsIgnoreCase("MAR"))
					mnthDisp = "03";
				else if (apmnth.equalsIgnoreCase("APR"))
					mnthDisp = "04";
				else if (apmnth.equalsIgnoreCase("MAY"))
					mnthDisp = "05";
				else if (apmnth.equalsIgnoreCase("JUN"))
					mnthDisp = "06";
				else if (apmnth.equalsIgnoreCase("JUL"))
					mnthDisp = "07";
				else if (apmnth.equalsIgnoreCase("AUG"))
					mnthDisp = "08";
				else if (apmnth.equalsIgnoreCase("SEP"))
					mnthDisp = "09";
				else if (apmnth.equalsIgnoreCase("OCT"))
					mnthDisp = "10";
				else if (apmnth.equalsIgnoreCase("NOV"))
					mnthDisp = "11";
				else if (apmnth.equalsIgnoreCase("DEC"))
					mnthDisp = "12";
				eform.getObjEstampDet().setAppAge(appdy + "/" + mnthDisp + "/" + apyr);
				//}
				//else
				//{
				//  eform.setAppAge(appdtl[8]);
				//}
				appdtl[4] = apgender;
				// eform.getObjEstampDet().setAppAge(appdtl[5]);
			}
			appdtl[6] = eform.getObjEstampDet().getPartyCountry();;
			appdtl[7] = eform.getObjEstampDet().getPartyState();
			appdtl[8] = apdstrct;
			appdtl[9] = apadrs;
			appdtl[10] = appostlcd;
			appdtl[11] = apphn;
			appdtl[12] = apmob;
			appdtl[13] = apemail;
			appdtl[14] = apphotoId;
			appdtl[15] = apphotoidno;
			appdtl[16] = apbnknme;
			appdtl[17] = apbnmadrs;
			appdtl[18] = aporgname;
			appdtl[19] = apfthrname;
			appdtl[20] = apmthrname;
			appdtl[21] = appersns;
			appdtl[22] = uid;
			appdtl[24] = "Y";
			appdtl[25] = estmTxnId;
			appdtl[23] = "";
			//for txn_party table-party
			partydtl[0] = prtytype;
			if (prtytype.equalsIgnoreCase("1")) {
				partydtl[1] = prtyauthfname;
				partydtl[2] = prtyauthmdname;
				partydtl[3] = prtyauthlname;
				partydtl[4] = "";
				partydtl[5] = "";
			} else {
				partydtl[1] = prtyfname;
				partydtl[2] = prtymdname;
				partydtl[3] = prtylname;
				partydtl[4] = prtygender;
				partydtl[5] = prtydy + "-" + prtymnth + "-" + prtyyr;
				String mnthDisp = "";
				if (prtymnth.equalsIgnoreCase("JAN"))
					mnthDisp = "01";
				else if (prtymnth.equalsIgnoreCase("FEB"))
					mnthDisp = "02";
				else if (prtymnth.equalsIgnoreCase("MAR"))
					mnthDisp = "03";
				else if (prtymnth.equalsIgnoreCase("APR"))
					mnthDisp = "04";
				else if (prtymnth.equalsIgnoreCase("MAY"))
					mnthDisp = "05";
				else if (prtymnth.equalsIgnoreCase("JUN"))
					mnthDisp = "06";
				else if (prtymnth.equalsIgnoreCase("JUL"))
					mnthDisp = "07";
				else if (prtymnth.equalsIgnoreCase("AUG"))
					mnthDisp = "08";
				else if (prtymnth.equalsIgnoreCase("SEP"))
					mnthDisp = "09";
				else if (prtymnth.equalsIgnoreCase("OCT"))
					mnthDisp = "10";
				else if (prtymnth.equalsIgnoreCase("NOV"))
					mnthDisp = "11";
				else if (prtymnth.equalsIgnoreCase("DEC"))
					mnthDisp = "12";
				eform.getObjEstampDet().setPartyAge(prtydy + "/" + mnthDisp + "/" + prtyyr);
				//eform.getObjEstampDet().setPartyAge( partydtl[5]);
			}
			partydtl[6] = prtycntry;
			partydtl[7] = prtystate;
			partydtl[8] = prtydstrct;
			partydtl[9] = prtyadrs;
			partydtl[10] = prtypostlcd;
			partydtl[11] = prtyphn;
			partydtl[12] = prtymob;
			partydtl[13] = prtyemail;
			partydtl[14] = prtyphotoId;
			partydtl[15] = prtyphotoidno;
			partydtl[16] = prtybnknme;
			partydtl[17] = prtybnmadrs;
			partydtl[18] = prtyorgname;
			partydtl[19] = prtyfthrname;
			partydtl[20] = prtymthrname;
			partydtl[21] = prtypersns;
			partydtl[22] = uid;
			partydtl[23] = "";
			partydtl[24] = "N";
			partydtl[25] = estmTxnId;
			transmgtUtil.createPreparedStatement(CommonSQL.TXN_TABLE_UPDATE);
			transmgtUtil.executeUpdate(txntable);
			transmgtUtil.createPreparedStatement(CommonSQL.JUD_TABLE_UPDATE);
			transmgtUtil.executeUpdate(judtable);
			transmgtUtil.createPreparedStatement(CommonSQL.PARTY_TABLE_UPDATE);
			transmgtUtil.executeUpdate(appdtl);
			if (!prtytype.equalsIgnoreCase("")) {
				transmgtUtil.createPreparedStatement(CommonSQL.PARTY_TABLE_UPDATE);
				transmgtUtil.executeUpdate(partydtl);
			}
			if (!docname.equalsIgnoreCase("")) {
				transmgtUtil.createPreparedStatement(CommonSQL.DOCUMENT_TABLE_UPDATE);
				transmgtUtil.executeUpdate(docTbl);
			}
			transactionflag = true;
			transmgtUtil.commit();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			transmgtUtil.rollback();
			throw ne;
		} catch (SQLException se) {
			se.printStackTrace();
			transmgtUtil.rollback();
			logger.error("SQL Exception at estamp  in DAO " + se);
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			transmgtUtil.rollback();
			logger.error(" Exception at estamp in DAO " + e);
			throw e;
		} finally {
			try {
				transmgtUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Exception at estamp in DAO  " + e);
			}
		}
		// eform.getObjEstampDet().setMainTxnId(estmTxnId);
		return transactionflag;
	}

	public ArrayList getjudDetails(String TxnId, String language) {
		ArrayList dutyDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.GET_JUDICIAL_DETAILS;
			} else {
				SQL = CommonSQL.GET_JUDICIAL_DETAILS_HI;
			}
			dbUtil.createPreparedStatement(SQL);
			try {
				dutyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				dutyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return dutyDetails;
	}

	public ArrayList getDetailsOfApplicantJud(String TxnId, String language) {
		ArrayList partyDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.GET_DETAILS_FOR_PARTY;
			} else {
				SQL = CommonSQL.GET_DETAILS_FOR_PARTY_HI;
			}
			logger.debug("***************" + SQL);
			dbUtil.createPreparedStatement(SQL);
			try {
				partyDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				partyDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return partyDetails;
	}

	public ArrayList getDetailsOfDocumentJud(String TxnId) {
		ArrayList documentDetails = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list1 = new ArrayList();
		String[] param = { TxnId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_DETAILS_FOR_DOCUMENT;
			dbUtil.createPreparedStatement(SQL);
			try {
				documentDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				documentDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return documentDetails;
	}

	public ArrayList getdocTypeValue(String documentName, String language) {
		ArrayList documentDetails = new ArrayList();
		String[] param = { documentName };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.DOCUMENT_TYPE_JUD_QUERY;
			} else {
				SQL = CommonSQL.DOCUMENT_TYPE_JUD_QUERY_H;
			}
			dbUtil.createPreparedStatement(SQL);
			try {
				documentDetails = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				documentDetails.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return documentDetails;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getCountryJud(String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getCountry() - start");
		}
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.debug("Wipro in IGRSCommon - " + "getCountry() after create statement");
			if ("en".equalsIgnoreCase(language)) {
				list = dbUtil.executeQuery(CommonSQL.COUNTRY_QUERY);
			} else {
				list = dbUtil.executeQuery(CommonSQL.COUNTRY_QUERY_H);
			}
			logger.debug("Wipro in IGRSCommon - " + "getCountry() after execute query");
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public synchronized ArrayList getStateJud(String countryId, String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getState(String) - start");
		}
		ArrayList list = new ArrayList();
		if ("en".equalsIgnoreCase(language)) {
			SQL = CommonSQL.STATE_QUERY;
		} else {
			SQL = CommonSQL.STATE_QUERY_H;
		}
		String arry[] = new String[1];
		if (countryId != null) {
			arry[0] = countryId;
		}
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - " + "getState() after excuteQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public ArrayList getecodeConsumption(String ecode) {
		ArrayList ecodeId = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_CONSUMPTION_DETAILS;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeId = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - viewEcodeDetailsDRS() after dbUtil.executeQuery(SQL)");
				ecodeId.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeId;
	}

	public String gettype(String userId) {
		DBUtility dbUtil = null;
		String type = "";
		try {
			String arry[] = new String[1];
			arry[0] = userId;
			String SQL = CommonSQL.USER_TYPE_QUERY;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - gettype() " + "after creating preparedstatement");
			type = dbUtil.executeQry(arry);
			logger.debug("Wipro in IGRSCommon - " + "gettype() after excuteQuery");
			logger.debug("found type" + type);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return type;
	}

	public ArrayList getdetails(String userId, EstampDetailsDTO objEstampDto, String language) {
		DBUtility dbUtil = null;
		ArrayList details = new ArrayList();
		try {
			String param[] = new String[1];
			String sql = "";
			param[0] = userId;
			if ("en".equalsIgnoreCase(language)) {
				sql = CommonSQL.USER_DETAILS;
			} else {
				sql = CommonSQL.USER_DETAILS_HI;
			}
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			details = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return details;
	}

	public ArrayList getrudetails(String userId, EstampDetailsDTO objEstampDto, String txnId, String language) {
		DBUtility dbUtil = null;
		ArrayList details = new ArrayList();
		try {
			String param[] = new String[2];
			param[0] = txnId;
			param[1] = userId;
			String sql = "";
			if ("en".equalsIgnoreCase(language)) {
				sql = CommonSQL.USER_RU_DETAILS;
			} else {
				sql = CommonSQL.USER_RU_DETAILS_HI;
			}
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			details = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return details;
	}

	public ArrayList getiudetails(EstampDetailsDTO objEstampDto, String offcId, String language) {
		DBUtility dbUtil = null;
		ArrayList details = new ArrayList();
		try {
			String param[] = new String[1];
			String sql = "";
			param[0] = offcId;
			if ("en".equalsIgnoreCase(language)) {
				sql = CommonSQL.USER_IU_DETAILS;
			} else {
				sql = CommonSQL.USER_IU_DETAILS_HI;
			}
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			details = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return details;
	}

	/******************************************************************
	 * Method Name : insertTxn() Arguments : Form Return : Boolean Throws :
	 * NullPointer or SQLException or Exception
	 *******************************************************************/
	public boolean insertTxn(EstampFormBean eform, String lang) throws NullPointerException, SQLException, Exception {
		boolean transactionflag = false;
		String estmTxnId = null;
		String maintable[] = new String[7];
		String txntable[] = new String[12];
		//String docTbl[] =   new String[6];
		String appdtl[] = new String[27];
		String partydtl[] = new String[27];
		String aptype = eform.getObjEstampDet().getAppType().toString();
		String apfname = eform.getObjEstampDet().getAppFirsName().toString();
		String apmdname = eform.getObjEstampDet().getAppMiddleName().toString();
		String aplname = eform.getObjEstampDet().getAppLastName().toString();
		String apgender = eform.getObjEstampDet().getAppGender().toString();
		String appdy = eform.getObjEstampDet().getAppDay().toString();
		String apmnth = eform.getObjEstampDet().getAppMonth().toString();
		String apyr = eform.getObjEstampDet().getAppYear().toString();
		String apfthrname = eform.getObjEstampDet().getAppFatherName().toString();
		String apmthrname = eform.getObjEstampDet().getAppMotherName().toString();
		String apadrs = eform.getObjEstampDet().getAppAddress().toString();
		String apcntry = eform.getObjEstampDet().getAppCountry();
		String apstate = eform.getObjEstampDet().getAppState();
		String apdstrct = eform.getObjEstampDet().getAppDistrict();
		String appostlcd = eform.getObjEstampDet().getAppPostalCode().toString();
		String apmob = eform.getObjEstampDet().getAppMobno().toString();
		String apphn = eform.getObjEstampDet().getAppPhno().toString();
		String apemail = eform.getObjEstampDet().getAppEmailID().toString();
		String apphotoId = eform.getObjEstampDet().getAppPhotoId().toString();
		String apphotoidno = eform.getObjEstampDet().getAppPhotoIdno().toString();
		String appersns = eform.getObjEstampDet().getAppPersons().toString();
		String aporgname = eform.getObjEstampDet().getAppOrgName();
		String apauthfname = eform.getObjEstampDet().getAppAuthFirstName();
		String apauthmdname = eform.getObjEstampDet().getAppAuthMiddleName();
		String apauthlname = eform.getObjEstampDet().getAppAuthLastName();
		String apbnknme = eform.getObjEstampDet().getAppBankName();
		String apbnmadrs = eform.getObjEstampDet().getAppBankAddress();
		String prtytype = eform.getObjEstampDet().getPartyType().toString();
		String prtyfname = eform.getObjEstampDet().getPartyFirsName().toString();
		String prtymdname = eform.getObjEstampDet().getPartyMiddleName().toString();
		String prtylname = eform.getObjEstampDet().getPartyLastName().toString();
		String prtygender = eform.getObjEstampDet().getPartyGender().toString();
		String prtydy = eform.getObjEstampDet().getPartyDay().toString();
		String prtymnth = eform.getObjEstampDet().getPartyMonth().toString();
		String prtyyr = eform.getObjEstampDet().getPartyYear().toString();
		String prtyfthrname = eform.getObjEstampDet().getPartyFatherName().toString();
		String prtymthrname = eform.getObjEstampDet().getPartyMotherName().toString();
		String prtyadrs = eform.getObjEstampDet().getPartyAddress().toString();
		String prtycntry = eform.getObjEstampDet().getPartyCountry();
		String prtystate = eform.getObjEstampDet().getPartyState();
		String prtydstrct = eform.getObjEstampDet().getPartyDistrict();
		String prtypostlcd = eform.getObjEstampDet().getPartyPostalCode().toString();
		String prtymob = eform.getObjEstampDet().getPartyMobno().toString();
		String prtyphn = eform.getObjEstampDet().getPartyPhno().toString();
		String prtyemail = eform.getObjEstampDet().getPartyEmailID().toString();
		String prtyphotoId = eform.getObjEstampDet().getPartyPhotoId().toString();
		String prtyphotoidno = eform.getObjEstampDet().getPartyPhotoIdno().toString();
		String prtypersns = eform.getObjEstampDet().getPartyPersons().toString();
		String prtyorgname = eform.getObjEstampDet().getPartyOrgName();
		String prtyauthfname = eform.getObjEstampDet().getPartyAuthFirstName();
		String prtyauthmdname = eform.getObjEstampDet().getPartyAuthMiddleName();
		String prtyauthlname = eform.getObjEstampDet().getPartyAuthLastName();
		String prtybnknme = eform.getObjEstampDet().getPartyBankName();
		String prtybnmadrs = eform.getObjEstampDet().getPartyBankAddress();
		//**********ADDED BY SIMRAN*******///
		if (apgender.equalsIgnoreCase("M")) {
			if (lang.equalsIgnoreCase("en"))
				eform.getObjEstampDet().setAppGenderDisp("Male");
			else
				eform.getObjEstampDet().setAppGenderDisp("पुस्र्ष");
		} else {
			if (lang.equalsIgnoreCase("en"))
				eform.getObjEstampDet().setAppGenderDisp("Female");
			else
				eform.getObjEstampDet().setAppGenderDisp("महिला");
		}
		if (prtygender.equalsIgnoreCase("M")) {
			if (lang.equalsIgnoreCase("en"))
				eform.getObjEstampDet().setPartyGenderDisp("Male");
			else
				eform.getObjEstampDet().setPartyGenderDisp("पुस्र्ष");
		} else {
			if (lang.equalsIgnoreCase("en"))
				eform.getObjEstampDet().setPartyGenderDisp("Female");
			else
				eform.getObjEstampDet().setPartyGenderDisp("महिला");
		}
		//***********END**********************
		//String estmpurpose = eform.getObjEstampDet().getEstampPurpose();
		String uid = eform.getObjEstampDet().getUid().toString();
		//String docname=eform.getObjEstampDet().getDoc().toString();
		//String docPath = eform.getObjEstampDet().getDocPath();
		//docname = eform.getDocname();
		Date date = new Date();
		Format yearformat = new SimpleDateFormat("yy");
		Format monthformat = new SimpleDateFormat("MM");
		Format dateformat = new SimpleDateFormat("dd");
		String dfmt = dateformat.format(date);
		String yfmt = yearformat.format(date);
		String mfmt = monthformat.format(date);
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.setAutoCommit(false);
			//for  txn table and estamp_txn_id
			/*String SQL1 = "SELECT COUNT(ESTAMP_TXN_ID) FROM IGRS_ESTAMP_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";

			*///logger.debug(SQL1);
			transmgtUtil.createStatement();
			String number1 = transmgtUtil.executeQry(CommonSQL.SELECT_ESTAMP_COUNT3);
			if (number1.equalsIgnoreCase("0")) {
				logger.debug("in if clause");
				/*String drpqry = "DROP SEQUENCE IGRS_ESTAMP_TXN_ID_SEQ";

				*/transmgtUtil.createStatement();
				transmgtUtil.executeUpdate(CommonSQL.DROP_SEQUNCE2);
				//transmgtUtil.executeQry(drpqry);
				/*String crtqry = "CREATE SEQUENCE IGRS_ESTAMP_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";

				*/transmgtUtil.createStatement();
				transmgtUtil.executeUpdate(CommonSQL.SELECT_ESTAMP_COUNT4);
			}
			/*String SQL2 = "select IGRS_ESTAMP_TXN_ID_SEQ.nextval from dual";

			*/transmgtUtil.createStatement();
			String number2 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUNCE7);
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
			estmTxnId = dfmt + mfmt + yfmt + number2;
			/* String SQL7 = "select IGRS_ESTAMP_JUDICIAL_ID_SEQ.nextval from dual";

			*/transmgtUtil.createStatement();
			String number7 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUNCE8);
			maintable[0] = estmTxnId;
			maintable[1] = "2";
			maintable[2] = "";
			maintable[3] = "";
			maintable[4] = "";
			maintable[5] = (String) uid;
			maintable[6] = eform.getObjEstampDet().getEstampPurps().toString();
			String txnTableArray = eform.getObjEstampDet().getCourt();
			String array[] = txnTableArray.split("::");
			txntable[0] = number7;
			txntable[1] = (String) estmTxnId;
			txntable[2] = eform.getObjEstampDet().getCourtDocType().toString();
			txntable[3] = array[0];
			txntable[4] = array[1];
			txntable[5] = eform.getObjEstampDet().getPartyCountry();
			txntable[6] = eform.getObjEstampDet().getPartyState();
			txntable[7] = eform.getObjEstampDet().getDistt().toString();
			txntable[8] = eform.getObjEstampDet().getAmount().toString();
			txntable[9] = eform.getObjEstampDet().getEstampPurps().toString();
			txntable[10] = uid;
			txntable[11] = "2";
			/* //for document table
			 String SQL4 = "select IGRS_ESTAMP_DOC_ID_SEQ.nextval from dual";
			 transmgtUtil.createStatement();
			 String number4 = transmgtUtil.executeQry(SQL4);
			 docTbl[0]=number4;
			 docTbl[1]=estmTxnId;
			 docTbl[2]=docname;
			 docTbl[3]="A";
			 docTbl[4]=uid;
			 docTbl[5]=docPath;*/
			//for txn_party table-applicant
			/*String SQL5 = "select IGRS_ESTAMP_PARTY_ID_SEQ.nextval from dual";

			*/transmgtUtil.createStatement();
			String number5 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUNCE9);
			appdtl[0] = number5;
			appdtl[1] = estmTxnId;
			appdtl[2] = aptype;
			appdtl[3] = "Y";
			if (aptype.equalsIgnoreCase("1")) {
				appdtl[4] = apauthfname;
				appdtl[5] = apauthmdname;
				appdtl[6] = apauthlname;
				appdtl[7] = "";
				appdtl[8] = "";
			} else {
				appdtl[4] = apfname;
				appdtl[5] = apmdname;
				appdtl[6] = aplname;
				appdtl[8] = appdy + "-" + apmnth + "-" + apyr;
				String mnthDisp = "";
				if (apmnth.equalsIgnoreCase("JAN"))
					mnthDisp = "01";
				else if (apmnth.equalsIgnoreCase("FEB"))
					mnthDisp = "02";
				else if (apmnth.equalsIgnoreCase("MAR"))
					mnthDisp = "03";
				else if (apmnth.equalsIgnoreCase("APR"))
					mnthDisp = "04";
				else if (apmnth.equalsIgnoreCase("MAY"))
					mnthDisp = "05";
				else if (apmnth.equalsIgnoreCase("JUN"))
					mnthDisp = "06";
				else if (apmnth.equalsIgnoreCase("JUL"))
					mnthDisp = "07";
				else if (apmnth.equalsIgnoreCase("AUG"))
					mnthDisp = "08";
				else if (apmnth.equalsIgnoreCase("SEP"))
					mnthDisp = "09";
				else if (apmnth.equalsIgnoreCase("OCT"))
					mnthDisp = "10";
				else if (apmnth.equalsIgnoreCase("NOV"))
					mnthDisp = "11";
				else if (apmnth.equalsIgnoreCase("DEC"))
					mnthDisp = "12";
				eform.getObjEstampDet().setAppAge(appdy + "/" + mnthDisp + "/" + apyr);
				appdtl[7] = apgender;
				// eform.getObjEstampDet().setAppAge(appdtl[8]);
			}
			appdtl[9] = eform.getObjEstampDet().getPartyCountry();
			appdtl[10] = eform.getObjEstampDet().getPartyState();
			appdtl[11] = apdstrct;
			appdtl[12] = apadrs;
			appdtl[13] = appostlcd;
			appdtl[14] = apphn;
			appdtl[15] = apmob;
			appdtl[16] = apemail;
			appdtl[17] = apphotoId;
			appdtl[18] = apphotoidno;
			appdtl[19] = apbnknme;
			appdtl[20] = apbnmadrs;
			appdtl[21] = aporgname;
			appdtl[22] = apfthrname;
			appdtl[23] = apmthrname;
			appdtl[24] = appersns;
			appdtl[25] = uid;
			appdtl[26] = "";
			//for txn_party table-party
			//String SQL6 = "select IGRS_ESTAMP_PARTY_ID_SEQ.nextval from dual";
			transmgtUtil.createStatement();
			String number6 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUNCE10);
			partydtl[0] = number6;
			partydtl[1] = estmTxnId;
			partydtl[2] = prtytype;
			partydtl[3] = "N";
			if (prtytype.equalsIgnoreCase("1")) {
				partydtl[4] = prtyauthfname;
				partydtl[5] = prtyauthmdname;
				partydtl[6] = prtyauthlname;
				partydtl[7] = "";
				partydtl[8] = "";
			} else {
				partydtl[4] = prtyfname;
				partydtl[5] = prtymdname;
				partydtl[6] = prtylname;
				partydtl[7] = prtygender;
				partydtl[8] = prtydy + "-" + prtymnth + "-" + prtyyr;
				String mnthDisp = "";
				if (prtymnth.equalsIgnoreCase("JAN"))
					mnthDisp = "01";
				else if (prtymnth.equalsIgnoreCase("FEB"))
					mnthDisp = "02";
				else if (prtymnth.equalsIgnoreCase("MAR"))
					mnthDisp = "03";
				else if (prtymnth.equalsIgnoreCase("APR"))
					mnthDisp = "04";
				else if (prtymnth.equalsIgnoreCase("MAY"))
					mnthDisp = "05";
				else if (prtymnth.equalsIgnoreCase("JUN"))
					mnthDisp = "06";
				else if (prtymnth.equalsIgnoreCase("JUL"))
					mnthDisp = "07";
				else if (prtymnth.equalsIgnoreCase("AUG"))
					mnthDisp = "08";
				else if (prtymnth.equalsIgnoreCase("SEP"))
					mnthDisp = "09";
				else if (prtymnth.equalsIgnoreCase("OCT"))
					mnthDisp = "10";
				else if (prtymnth.equalsIgnoreCase("NOV"))
					mnthDisp = "11";
				else if (prtymnth.equalsIgnoreCase("DEC"))
					mnthDisp = "12";
				eform.getObjEstampDet().setPartyAge(prtydy + "/" + mnthDisp + "/" + prtyyr);
				//eform.getObjEstampDet().setPartyAge( partydtl[8]);
			}
			partydtl[9] = prtycntry;
			partydtl[10] = prtystate;
			partydtl[11] = prtydstrct;
			partydtl[12] = prtyadrs;
			partydtl[13] = prtypostlcd;
			partydtl[14] = prtyphn;
			partydtl[15] = prtymob;
			partydtl[16] = prtyemail;
			partydtl[17] = prtyphotoId;
			partydtl[18] = prtyphotoidno;
			partydtl[19] = prtybnknme;
			partydtl[20] = prtybnmadrs;
			partydtl[21] = prtyorgname;
			partydtl[22] = prtyfthrname;
			partydtl[23] = prtymthrname;
			partydtl[24] = prtypersns;
			partydtl[25] = uid;
			partydtl[26] = "";
			transmgtUtil.createPreparedStatement(CommonSQL.TXN_TABLE_INSERT_JUD);
			transmgtUtil.executeUpdate(maintable);
			transmgtUtil.createPreparedStatement(CommonSQL.JUD_TABLE_INSERT);
			transmgtUtil.executeUpdate(txntable);
			transmgtUtil.createPreparedStatement(CommonSQL.PARTY_TABLE_INSERT);
			transmgtUtil.executeUpdate(appdtl);
			if (!prtytype.equalsIgnoreCase("")) {
				transmgtUtil.createPreparedStatement(CommonSQL.PARTY_TABLE_INSERT);
				transmgtUtil.executeUpdate(partydtl);
			}
			/* if (!docname.equalsIgnoreCase("")){
			 transmgtUtil.createPreparedStatement(CommonSQL.DOCUMENT_TABLE_INSERT);
			 transmgtUtil.executeUpdate(docTbl); 
			 }*/
			transactionflag = true;
			transmgtUtil.commit();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			transmgtUtil.rollback();
			throw ne;
		} catch (SQLException se) {
			se.printStackTrace();
			transmgtUtil.rollback();
			logger.error("SQL Exception at estamp  in DAO " + se);
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			transmgtUtil.rollback();
			logger.error(" Exception at estamp in DAO " + e);
			throw e;
		} finally {
			try {
				transmgtUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Exception at estamp in DAO  " + e);
			}
		}
		eform.getObjEstampDet().setMainTxnId(estmTxnId);
		return transactionflag;
	}

	public ArrayList getiuDtls(String userId, String officeId, String language) throws Exception {
		String[] tid = new String[3];
		tid[0] = officeId;
		tid[1] = userId;
		tid[2] = officeId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_IU_DTLS);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_IU_DTLS_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public String getecodetype(String ecode) {
		DBUtility dbUtil = null;
		String type = "";
		try {
			String arry[] = new String[1];
			arry[0] = ecode;
			String SQL = CommonSQL.GET_ECODE_TYPE;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			type = dbUtil.executeQry(arry);
			logger.debug("Wipro in IGRSCommon - " + "getState() after excuteQuery");
			logger.debug("found type" + type);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return type;
	}

	public ArrayList deactCREcodeDetailsZero(String ecode, String regID) throws Exception {
		String[] tid = new String[4];
		tid[0] = regID;
		tid[1] = regID;
		tid[2] = regID;
		tid[3] = ecode;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_DEAC_DETAILS);
			list = transmgtUtil.executeQuery(tid);
			list.trimToSize();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return list;
	}

	public String getregID(String ecode) {
		DBUtility dbUtil = null;
		String id = "";
		try {
			String arry[] = new String[1];
			arry[0] = ecode;
			String SQL = CommonSQL.GET_REG_TXN_ID;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			id = dbUtil.executeQry(arry);
			logger.debug("Wipro in IGRSCommon - " + "getState() after excuteQuery");
			logger.debug("found type" + id);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}

	public ArrayList deactCREcodeAppdetails(String ecode, String regID) throws Exception {
		String[] tid = new String[1];
		tid[0] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY1_DETAILS);
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList deactCREcodepartydetails(String ecode, String regID) throws Exception {
		String[] tid = new String[2];
		tid[0] = regID;
		tid[1] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY2_DETAILS);
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList viewCREcodeDetails(String ecode, String regID) throws Exception {
		String[] tid = new String[4];
		tid[0] = regID;
		tid[1] = regID;
		tid[2] = regID;
		tid[3] = ecode;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_RU_CRECODE_DETAILS);
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList viewCREcodeDetailsDRS(String ecode, String regID) throws Exception {
		String[] tid = new String[4];
		tid[0] = regID;
		tid[1] = regID;
		tid[2] = regID;
		tid[3] = ecode;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS);
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getCRDetailsOfApplicantDRS(String ecode, String regID) throws Exception {
		String[] tid = new String[4];
		tid[0] = regID;
		tid[1] = regID;
		tid[2] = regID;
		tid[3] = ecode;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS);
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getCRtype(String regID) throws Exception {
		String[] tid = new String[1];
		tid[0] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_TYPE_ECODE_DETAILS);
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getCRYDetailsOfApplicantDRS(String regID, String language) throws Exception {
		String[] tid = new String[2];
		tid[0] = regID;
		tid[1] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS_FOR_PARTY);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS_FOR_PARTY_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getCRNDetailsOfApplicantDRS(String regID, String language) throws Exception {
		String[] tid = new String[3];
		tid[0] = regID;
		tid[1] = regID;
		tid[2] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_ORG_DRS_ECODE_DETAILS_FOR_PARTY2);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_ORG_DRS_ECODE_DETAILS_FOR_PARTY2_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getCRNorgDetailsOfApplicantDRS(String regID, String language) throws Exception {
		String[] tid = new String[3];
		tid[0] = regID;
		tid[1] = regID;
		tid[2] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_ORG_DRS_ECODE_DETAILS_FOR_PARTY2);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_ORG_DRS_ECODE_DETAILS_FOR_PARTY2_HI);
			}
			list = transmgtUtil.executeQuery(tid);
			list.trimToSize();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return list;
	}

	public ArrayList getCRNindvDetailsOfApplicantDRS(String regID, String language) throws Exception {
		String[] tid = new String[3];
		tid[0] = regID;
		tid[1] = regID;
		tid[2] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS_FOR_PARTY2);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS_FOR_PARTY2_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getindvDetailsOfApplicantDRS(String ecode, String language) throws Exception {
		String[] tid = new String[1];
		tid[0] = ecode;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_ECODE_DETAILS_FOR_PARTY);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_ECODE_DETAILS_FOR_PARTY_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getindvDetailsOfApplicantDRSAdoption(String ecode, String language) throws Exception {
		String[] tid = new String[1];
		tid[0] = ecode;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_ECODE_DETAILS_FOR_PARTY_A);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_ECODE_DETAILS_FOR_PARTY_HI_A);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getorgDetailsOfApplicantDRS(String ecode, String language) throws Exception {
		String[] tid = new String[1];
		tid[0] = ecode;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_ORG_ECODE_DETAILS_FOR_PARTY);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_ORG_ECODE_DETAILS_FOR_PARTY_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getNDetailsOfApplicantDRS(String ecode, String language) throws Exception {
		String[] tid = new String[1];
		tid[0] = ecode;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_N_ORG_ECODE_DETAILS_FOR_PARTY);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_N_ORG_ECODE_DETAILS_FOR_PARTY_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getCRYindvDetailsOfApplicantDRS(String regID, String language) throws Exception {
		String[] tid = new String[2];
		tid[0] = regID;
		tid[1] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS_FOR_PARTY1);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS_FOR_PARTY1_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	public ArrayList getindvDetailsOfApplicantDRSNew(String ecode, String language) throws Exception {
		String[] tid = new String[1];
		tid[0] = ecode;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_ECODE_DETAILS_FOR_APPLICANT);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_DRS_ECODE_DETAILS_FOR_APPLICANT_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

	//*****************ADDED BY SIMRAN********//
	public String getRemarksForConsumption(String ecode) throws Exception {
		String remarks = "";
		String[] tid = new String[1];
		tid[0] = ecode;
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_REMARKS_FOR_CONSUMPTION);
			remarks = transmgtUtil.executeQry(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return remarks;
	}

	/**
	 * @param ecode
	 * @return
	 */
	public String getDeactivateCount(String ecode) {
		String count = "";
		DBUtility dbUtil = null;
		try {
			String arry[] = new String[1];
			arry[0] = ecode;
			String SQL = CommonSQL.GET_ESTAMP_DEACTICVATE_DETLS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			count = dbUtil.executeQry(arry);
			logger.debug("Wipro in IGRSCommon - " + "getState() after excuteQuery");
			logger.debug("found type" + count);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	//*************************************************
	public String getCertChkDetails(String ecode) {
		String chk = "";
		DBUtility dbUtil = null;
		try {
			String arry[] = new String[1];
			arry[0] = ecode;
			String SQL = CommonSQL.GET_ESTAMP_CERTIFICATE_CHK_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			chk = dbUtil.executeQry(arry);
			logger.debug("Wipro in IGRSCommon - " + "getState() after excuteQuery");
			logger.debug("found type" + chk);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return chk;
	}

	public String getEstampDocRegDetails(String ecode) {
		String chk = "";
		DBUtility dbUtil = null;
		try {
			String arry[] = new String[1];
			arry[0] = ecode;
			String SQL = CommonSQL.GET_ESTAMP_DOC_DETAILS_REG;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			chk = dbUtil.executeQry(arry);
			logger.debug("Wipro in IGRSCommon - " + "getState() after excuteQuery");
			logger.debug("found type" + chk);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return chk;
	}

	public ArrayList getEstampDocDetails(String ecode) {
		ArrayList estampDocDetails = new ArrayList();
		DBUtility dbUtil = null;
		try {
			String arry[] = new String[1];
			arry[0] = ecode;
			String SQL = CommonSQL.GET_ESTAMP_DOC_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - getState() " + "after creating preparedstatement");
			estampDocDetails = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - " + "getState() after excuteQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return estampDocDetails;
	}

	public boolean updateCertificateGenerationChk(String eStampTxnId) throws Exception {
		String tid[] = new String[1];
		tid[0] = eStampTxnId;
		logger.info("inside dao of updateCertificateGenerationChk judicial");
		boolean flag = false;
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.UPADTE_CERTIFICATE_CHK_DETLS);
			flag = transmgtUtil.executeUpdate(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return flag;
	}

	public String deedDraftId(String deedId) throws Exception {
		String tid[] = new String[1];
		tid[0] = deedId;
		logger.info("inside dao of updateCertificateGenerationChk judicial");
		String flag = "";
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_DEED_ID_STATUS);
			flag = transmgtUtil.executeQry(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return flag;
	}

	public String deedDraftIdValidate(String deedId, String userID, String type) throws Exception {
		String tid[] = new String[3];
		tid[0] = deedId;
		tid[1] = userID;
		tid[2] = type;
		logger.info("inside dao of deedDraftIdValidate judicial");
		String flag = "";
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_DEED_ID_STATUS_VALIDATE);
			flag = transmgtUtil.executeQry(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return flag;
	}

	public String deedDraftIdIncompValidate(String deedId, String userId) throws Exception {
		String tid[] = new String[3];
		tid[0] = deedId;
		tid[1] = userId;
		tid[2] = "E";
		logger.info("inside dao of deedDraftIdIncompValidate Non judicial");
		String flag = "";
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_INCOMP_DEED_CHECK);
			flag = transmgtUtil.executeQry(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return flag;
	}

	public boolean getDeedAppDetls(String deedId, String userId, String typeApp) throws Exception {
		String tid[] = new String[3];
		tid[0] = deedId;
		tid[1] = userId;
		tid[2] = typeApp;
		String type = "";
		boolean flag = false;
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_DEED_APP_TYPE);
			type = transmgtUtil.executeQry(tid);
			if ("R".equals(type) || "A".equals(type)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return flag;
	}

	public boolean updateDeedConsume(DutyCalculationForm form, String language, HttpServletResponse response) throws Exception {
		boolean finalFlag = true;
		DBUtility transmgtUtil = null;
		try {
			EstampingBD bd = new EstampingBD();
			String status = bd.deedDraftId(form.getDeedDraftId());
			if (status == null || status.equalsIgnoreCase("")) {
				form.setDeedDraftErrorFlag("Y");
				form.setDeedDraftStatus("D");
				if ("en".equalsIgnoreCase(language)) {
					form.setDeedDraftError("Invalid Id");
					finalFlag = false;
				} else {
					form.setDeedDraftError("अवैध आईडी|");
					finalFlag = false;
				}
			} else if (status.equalsIgnoreCase("D")) {
				form.setDeedDraftErrorFlag("Y");
				form.setDeedDraftStatus("D");
				if ("en".equalsIgnoreCase(language)) {
					form.setDeedDraftError("Already Consumed");
				} else {
					form.setDeedDraftError("आईडी को पहले से ही इस्तेमाल किया जा चुका है।");
				}
			} else {
				DeedDraftBD bd1 = new DeedDraftBD();
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String path = pr.getValue("pdf.location");
				//byte[] propDetlsBytes = bd.propDetlsPdf(regForm.getHidnRegTxnId()); //added by ankit for prop val pdf 
				byte[] propDetlsBytes = null; //added by ankit for prop val pdf 
				byte[] byteArr = bd1.generateDeedPDF(path, form.getDeedDraftId(), response, 1, propDetlsBytes); //change by ankit for prop val pdf
				PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
				String downloadPath = proper.getValue("igrs_upload_path");
				downloadPath = downloadPath + File.separator + "IGRS";
				String filePath = downloadPath + File.separator + "05" + File.separator + form.getDeedDraftId() + File.separator;
				String fileName = "deed_document.PDF";
				form.setDocname(fileName);
				form.setDoc(fileName);
				form.setDocPath(filePath);
				form.setDocPathComplete(filePath);
				File folder = new File(filePath);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				try {
					File newFile = new File(filePath, fileName);
					if (!newFile.exists()) {
						logger.info("NEW FILE NAME:-" + newFile);
						FileOutputStream fos = new FileOutputStream(newFile);
						fos.write(byteArr);
						fos.close();
					} else {
						//String str = fileName.substring(0, fileName.indexOf("."));
						//fileName = str + "_01" + ".jpg";
						//newFile = new File(filePath, fileName);
						FileOutputStream fos = new FileOutputStream(newFile);
						fos.write(byteArr);
						fos.close();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				String tid[] = new String[1];
				tid[0] = form.getDeedDraftId();
				logger.info("inside dao of updateCertificateGenerationChk judicial");
				boolean flag = false;
				transmgtUtil = new DBUtility();
				transmgtUtil.createPreparedStatement(CommonSQL.UPDATE_DEED_ID_STATUS);
				flag = transmgtUtil.executeUpdate(tid);
				if (!flag) {
					finalFlag = false;
				}
				return flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transmgtUtil.closeConnection();
		}
		return finalFlag;
	}

	public String getAmount(String ecode) {
		String ecodeDocTypeJud = "";
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_ESTAMP_AMOUNT;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeDocTypeJud = dbUtil.executeQry(param);
				logger.debug("Wipro in EstampDAO - deactDocTypeJud() after dbUtil.executeQuery(SQL)");
				ecodeDocTypeJud.trim();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeDocTypeJud;
	}

	public String getCurrDateTime() throws Exception {
		String currDateTime = new String();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			currDateTime = dbUtil.executeQry(CommonSQL.CURRENT_DATE_TIME);
		} catch (Exception exception) {
			logger.debug("Exception in getCurrDateTime" + exception);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x);
			}
		}
		return currDateTime;
	}

	public String getSubjectNameDao(String userId) {
		String subjectName = "";
		String arr[] = { userId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_SUBJECT_NAME);
			subjectName = dbUtil.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getSubjectNameDao" + exception);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x.getMessage(), x);
			}
		}
		return subjectName;
	}

	public synchronized ArrayList getAppellate(String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getCommon(String) - start");
		}
		String SQL = "";
		if ("en".equalsIgnoreCase(language)) {
			SQL = CommonSQL.APPELLATE_QUERY;
		} else {
			SQL = CommonSQL.APPELLATE_QUERY_H;
		}
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.debug("Wipro in IGRSCommon - " + "getappellate() after create statement");
			list = dbUtil.executeQuery(SQL);
			logger.debug("Wipro in IGRSCommon - " + "getappellate() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public String getUserType(String userId) {
		String subjectName = "";
		String arr[] = { userId };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_USER_TYPE);
			subjectName = dbUtil.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getSubjectNameDao" + exception);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x.getMessage(), x);
			}
		}
		return subjectName;
	}

	public boolean checkEstamp(String regTxnID, String mod_flag) {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = CommonSQL.GET_ESTAMP_CHECK_ESTAMP_MOD;
			dbUtility.createPreparedStatement(query);
			String arr[] = new String[2];
			arr[0] = mod_flag;
			arr[1] = regTxnID;
			String noOfEstamp = dbUtility.executeQry(arr);
			if (Integer.parseInt(noOfEstamp) == 0)
				flag = true;
			else
				flag = false;
		} catch (Exception exception) {
			logger.debug("Exception in checkEstamp" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection checkEstamp" + e.getStackTrace());
			}
		}
		return flag;
	}

	public String getEcode(String txnID, String modFlag) {
		String eCode = "";
		String arr[] = { modFlag, txnID };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_ECODE);
			eCode = dbUtil.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getEcode" + exception);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x.getMessage(), x);
			}
		}
		return eCode;
	}

	public ArrayList getFatherName(String txnID) {
		//String name[]={};
		String arr[] = { txnID, txnID };
		DBUtility dbUtil = null;
		String name[] = new String[2];
		ArrayList<String> list = new ArrayList<String>();
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.FATHER_NAME_LIST);
			list = dbUtil.executeQuery(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getFatherName" + exception);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x.getMessage(), x);
			}
		}
		return list;
	}

	public String deedDraftIdValidateInEstampTxn(String deedId) throws Exception {
		String tid[] = new String[1];
		tid[0] = deedId;
		logger.info("inside dao of deedDraftIdValidateInEstampTxn judicial");
		String flag = "";
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(CommonSQL.GET_DEED_ID_STATUS_VALIDATE_ESTAMP);
			flag = transmgtUtil.executeQry(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		return flag;
	}

	public String checkUser(String estampCodeId, String userId) {
		String arr[] = { estampCodeId, userId };
		String chkUser = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.CHK_USER);
			chkUser = dbUtil.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in check User" + exception);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x.getMessage(), x);
			}
		}
		return chkUser;
	}

	public ArrayList getDistrictCourt(String language) throws Exception {
		DBUtility dbUtil = null;
		ArrayList list = new ArrayList();
		//String SQL = CommonSQL.STATE_QUERY;
		if ("en".equalsIgnoreCase(language)) {
			SQL = CommonSQL.GET_DISTRICT_COURT_QUERY;
		} else {
			SQL = CommonSQL.GET_DISTRICT_COURT_QUERY_H;
		}
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.debug("Wipro in IGRSCommon - " + "getHomeState() after excuteQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public ArrayList getCourtList(String courtType, String DistrictCourtId, String language) throws Exception {
		String arry[] = new String[2];
		ArrayList list = new ArrayList();
		if (courtType != null) {
			arry[0] = DistrictCourtId;
			arry[1] = courtType;
		}
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				SQL = CommonSQL.COURT_LIST;
			} else {
				SQL = CommonSQL.COURT_LIST_H;
			}
			dbUtil.createPreparedStatement(SQL);
			try {
				list = dbUtil.executeQuery(arry);
			} catch (Exception e) {
				logger.debug(e);
			}
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public ArrayList getjudCourtDetails(String txnId) throws Exception {
		String[] tid = new String[1];
		tid[0] = txnId;
		//tid[1]=txnId;
		ArrayList courtdetails = null;
		DBUtility transmgtUtil = new DBUtility();
		try {
			transmgtUtil.createPreparedStatement(CommonSQL.JUD_COURT_DETAILS);
			courtdetails = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			throw new Exception();
		} finally {
			transmgtUtil.closeConnection();
		}
		return courtdetails;
	}

	public ArrayList viewEcodeDetailsNJ_DRS(String ecode) {
		ArrayList ecodeId = new ArrayList();
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_DRS_ECODE_DETAILS_NJ;
			dbUtil.createPreparedStatement(SQL);
			try {
				ecodeId = dbUtil.executeQuery(param);
				logger.debug("Wipro in EstampDAO - viewEcodeDetailsDRS() after dbUtil.executeQuery(SQL)");
				ecodeId.trimToSize();
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ecodeId;
	}

	public String getGovtStamp(String txnID) {
		String eCode = "";
		String arr[] = { txnID };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.STAMP_DUTY_CHECK);
			eCode = dbUtil.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getEcode" + exception);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception x) {
				logger.debug(x.getMessage(), x);
			}
		}
		return eCode;
	}

	public ArrayList getRefundRequestDtls(String ecode) {
		ArrayList refundReqDtls = new ArrayList();
		//String estampid = ecode.substring(3, 4);
		String[] param = { ecode };
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			SQL = CommonSQL.GET_REFUND_REQUEST;
			dbUtil.createPreparedStatement(SQL);
			refundReqDtls = dbUtil.executeQuery(param);
			logger.debug("EstampDAO - getRefundRequestDtls() after dbUtil.executeQuery(SQL)");
			refundReqDtls.trimToSize();
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return refundReqDtls;
	}
	
	//added by saurav
	public ArrayList getCRYindvDetailsOfGOVApplicantDRS(String regID, String language) throws Exception {
		String[] tid = new String[2];
		tid[0] = regID;
		tid[1] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS_FOR_PARTY_GOV);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CR_DRS_ECODE_DETAILS_FOR_PARTY1_GOV_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}
	public ArrayList getCRNindvDetailsOfGOVApplicantDRS(String regID, String language) throws Exception {
		String[] tid = new String[3];
		tid[0] = regID;
		tid[1] = regID;
		tid[2] = regID;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = null;
		try {
			transmgtUtil = new DBUtility();
			if ("en".equalsIgnoreCase(language)) {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CRN_DRS_ECODE_DETAILS_FOR_PARTY_GOV);
			} else {
				transmgtUtil.createPreparedStatement(CommonSQL.GET_CRN_DRS_ECODE_DETAILS_FOR_PARTY1_GOV_HI);
			}
			list = transmgtUtil.executeQuery(tid);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			transmgtUtil.closeConnection();
		}
		list.trimToSize();
		return list;
	}

}
