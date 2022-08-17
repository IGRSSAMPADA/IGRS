/**
 * ServiceProviderAccountDAO.java
 * Date			Author				Description
 * 21-10-2016	Aakash Agarwal		Validate user id based on license number
 */

package com.wipro.igrs.serviceProvider.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;
import com.wipro.igrs.db.DBUtility;

import com.wipro.igrs.serviceProvider.dto.ServiceProviderAccountDTO;
import com.wipro.igrs.serviceProvider.form.ServiceProviderAccountForm;
import com.wipro.igrs.serviceProvider.sql.ServiceProviderSQL; /*import com.wipro.igrs.sp.sql.CommonSQL;*/
import com.wipro.igrs.util.CommonUtil;

/**
 * @author root
 * 
 */

public class ServiceProviderAccountDAO {

	private static Logger logger = (Logger) Logger.getLogger(ServiceProviderAccountDAO.class);
	/**
	 * Constructor name : ServiceProviderAccountDAO
	 * 
	 */
	// DBUtility dbUtil = null;
	ArrayList list = null;

	private CallableStatement clstmt;

	/**
	 * Constructor name : ServiceProviderAccountDAO
	 * 
	 * @throws Exception
	 */
	public ServiceProviderAccountDAO() throws Exception {
		// dbUtil = new DBUtility();
	}

	/**
	 * Method name :getUserType
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getUserType(String userid) throws Exception {
		DBUtility dbUtility = new DBUtility();
		try {
			String arrdistid[] = {userid};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTSPUSERTYPE);
			list = dbUtility.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * Method name :getServiceProviderInfo
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getServiceProviderInfo(String userid) throws Exception {
		DBUtility dbUtility = new DBUtility();
		try {
			String arrdistid[] = {userid, userid};
			// dbUtil.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERACCOUNTINFO);
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTSERVICEPROVIDERACCOUNTINFONEW);

			list = dbUtility.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	// added by shruti
	public ArrayList getSpAcntDetails(String userid, String sessionLicenseNo) throws Exception {
		DBUtility dbUtility = new DBUtility();

		try {
			String arrdistid[] = {userid, sessionLicenseNo, sessionLicenseNo};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTSERVICEPROVIDERACCOUNTINFONEW1);
			ArrayList listDetails = new ArrayList();
			listDetails = dbUtility.executeQuery(arrdistid);
			list = dbUtility.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	// end
	// ////CHECK THIS FOR ERROR
	// added by Lavi for getting values of avail credit limit for estamp
	public ArrayList getSpAcntDetailsEstamp(String userid, String sessionLicenseNo) throws Exception {
		DBUtility dbUtility = new DBUtility();
		try {
			String arrdistid[] = {userid, sessionLicenseNo, sessionLicenseNo};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTSERVICEPROVIDERACCOUNTINFOFORESTAMP);
			ArrayList listDetails = new ArrayList();
			listDetails = dbUtility.executeQuery(arrdistid);
			list = listDetails;// dbUtility.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}
	// end of addition by Lavi for getting values of avail credit limit for
	// estamp

	// added by shruti
	public ArrayList getSpDetails(String userid) throws Exception {
		DBUtility dbUtility = new DBUtility();
		try {
			String arrdistid[] = {userid, userid};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTSPDETAILS);
			list = dbUtility.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}
	// end

	// added by shruti
	public ArrayList getSpBankDetails(String userid) throws Exception {
		DBUtility dbUtility = new DBUtility();
		try {
			String arrdistid[] = {userid, userid};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTSPBANKDETAILS);
			list = dbUtility.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * Method name :getServiceProviderBankInfo
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getServiceProviderBankInfo(String userid) throws Exception {
		DBUtility dbUtility = new DBUtility();
		try {
			String arrdistid[] = {userid, userid};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTSERVICEPROVIDERBANKACCOUNTINFO);
			list = dbUtility.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * Method name :getServiceProviderStmt
	 * 
	 * @param userid
	 * @param accountDTO
	 * @return
	 * @throws Exception
	 * 
	 */
	// Updated by Aakash
	// Start Validate user id based on license number
	public ArrayList getServiceProviderStmt(String sessionLicenseNo, ServiceProviderAccountDTO accountDTO, String language, HttpSession session) throws Exception {
		// End Validate user id based on license number
		DBUtility dbUtility = new DBUtility();
		List<String> listdistid = new ArrayList<String>();
		String query = null;
		// Start Validate user id based on license number
		List<List<String>> userList = getUserId(sessionLicenseNo);
		if (userList.size() == 1 && userList.get(0).get(0).equals(session.getAttribute("UserId"))) {
			// End Validate user id based on license number
			try {
				// String lno=accountDTO.getLicencenumber();
				// String arrdistid[] = { sessionLicenseNo, sessionLicenseNo,
				// CommonUtil.getConvertedDate(accountDTO.getFromdate()),
				// CommonUtil.getConvertedDate(accountDTO.getTodate()) };
				// commented by shruti
				/*
				 * dbUtil.createPreparedStatement(CommonSQL.
				 * SELECTSERVICEPROVIDERACCOUNTSTMT );
				 */
				// modified by shruti-5Feb 2013
				/*
				 * dbUtil.createPreparedStatement(CommonSQL.
				 * SELECTSERVICEPROVIDERACCOUNTSTMTNEW);
				 */
				/*
				 * dbUtil.createPreparedStatement(CommonSQL.
				 * SELECTSERVICEPROVIDERACCOUNTSTMTNEW1);
				 */
				listdistid.add(sessionLicenseNo);
				listdistid.add(sessionLicenseNo);
				listdistid.add(sessionLicenseNo);
				if (accountDTO.getFromdate() != null && accountDTO.getTodate() != null && !(accountDTO.getFromdate().trim().isEmpty()) && !(accountDTO.getTodate().trim().isEmpty())) {
					listdistid.add(CommonUtil.getConvertedDate(accountDTO.getFromdate()));
					listdistid.add(CommonUtil.getConvertedDate(accountDTO.getTodate()));
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_OTHERSERVICES_VIEW + "         AND TO_DATE (icltl.TRANSACTION_DATE) BETWEEN ? AND ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_OTHERSERVICES_VIEW_H + "         AND TO_DATE (icltl.TRANSACTION_DATE) BETWEEN ? AND ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				} else if (accountDTO.getCrn() != null && !(accountDTO.getCrn().trim().isEmpty())) {
					listdistid.add(accountDTO.getCrn());
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_OTHERSERVICES_VIEW + "         AND VIEW1.CRN_EPRN = ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_OTHERSERVICES_VIEW_H + "         AND VIEW1.CRN_EPRN = ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				} else if (accountDTO.getTxnid() != null && !(accountDTO.getTxnid().trim().isEmpty())) {
					listdistid.add(accountDTO.getTxnid());
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_OTHERSERVICES_VIEW + "         AND icltl.payment_txn_id= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_OTHERSERVICES_VIEW_H + "         AND icltl.payment_txn_id= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				} else if (accountDTO.getPaymentamount() != null && !(accountDTO.getPaymentamount().trim().isEmpty())) {
					listdistid.add(accountDTO.getPaymentamount());
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_OTHERSERVICES_VIEW + "         AND icltl.paid_amount= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_OTHERSERVICES_VIEW_H + "         AND icltl.paid_amount= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				}

				list = dbUtility.executeQuery(listdistid);
				logger.info("STATEMENT LIST:----" + list);
				accountDTO.setTodate(null);
				accountDTO.setFromdate(null);
				accountDTO.setCrn(null);
				accountDTO.setTxnid(null);
				accountDTO.setPaymentamount(null);
			} catch (Exception exception) {
				logger.info("Exception in getServiceProviderStmt" + exception);
			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
				}
			}
			return list;
		}// Start Validate user id based on license number
		else {
			throw new Exception("/jsp/SessionExpired.jsp");
		}
		// End Validate user id based on license number
	}

	// Updated by Aakash
	// Start Validate user id based on license number
	public ArrayList getServiceProviderStmtEstamp(String sessionLicenseNo, ServiceProviderAccountDTO accountDTO, String language, HttpSession session) throws Exception {
		// Start Validate user id based on license number
		DBUtility dbUtility = new DBUtility();
		List<String> listdistid = new ArrayList<String>();
		String query = null;
		// Start Validate user id based on license number
		List<List<String>> userList = getUserId(sessionLicenseNo);
		if (userList.size() == 1 && userList.get(0).get(0).equals(session.getAttribute("UserId"))) {
			// End Validate user id based on license number
			try {
				listdistid.add(sessionLicenseNo);
				listdistid.add(sessionLicenseNo);
				listdistid.add(sessionLicenseNo);
				if (accountDTO.getFromdate() != null && accountDTO.getTodate() != null && !(accountDTO.getFromdate().trim().isEmpty()) && !(accountDTO.getTodate().trim().isEmpty())) {
					// listdistid.add(CommonUtil.getConvertedDate(accountDTO.getFromdate()));
					// listdistid.add(CommonUtil.getConvertedDate(accountDTO.getTodate()));
					// CommonUtil.getConvertedDate(accountDTO.getFromdate());
					// CommonUtil.getConvertedDate(accountDTO.getTodate());

					String finalFrmDate = null;
					String finalToDate = null;
					Date frmDate = new Date();
					Date toDate = new Date();
					try {
						frmDate = new SimpleDateFormat("dd/MM/yyyy").parse(accountDTO.getFromdate());
						toDate = new SimpleDateFormat("dd/MM/yyyy").parse(accountDTO.getTodate());
					} catch (ParseException e1) {
						logger.info("Exception in parsing date" + e1);
						// e1.printStackTrace();
					}
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");

					finalFrmDate = formatter.format(frmDate);
					finalToDate = formatter.format(toDate);
					String finalFrmDate1[] = finalFrmDate.split(" ");
					String finalFrmDate2 = finalFrmDate1[0];
					String finalFrmDate3 = finalFrmDate2 + " 00:00:00";

					String finalToDate1[] = finalToDate.split(" ");
					String finalToDate2 = finalToDate1[0];
					String finalToDate3 = finalToDate2 + " 23:59:59";

					listdistid.add(finalFrmDate3);
					listdistid.add(finalToDate3);
					if ("en".equalsIgnoreCase(language)) {

						query = ServiceProviderSQL.CREDIT_LIMIT_ESTAMP_VIEW
						// +
						// "    AND TO_DATE (ICLTL.TRANSACTION_DATE) BETWEEN ? AND ? "
						+ "    AND ICLTL.TRANSACTION_DATE BETWEEN TO_DATE(?,'DD/MON/YYYY HH24:MI:SS') AND TO_DATE(?,'DD/MON/YYYY HH24:MI:SS') "
						// + "        ORDER BY (icltl.UPDATE_DATE)";
						+ "        ORDER BY icltl.UPDATE_DATE";
						dbUtility.createPreparedStatement(query.trim());
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_ESTAMP_VIEW_H
						// +
						// "     AND TO_DATE (ICLTL.TRANSACTION_DATE) BETWEEN ? AND ? "
						+ "    AND ICLTL.TRANSACTION_DATE BETWEEN TO_DATE(?,'DD/MON/YYYY HH24:MI:SS') AND TO_DATE(?,'DD/MON/YYYY HH24:MI:SS') "
						// + "        ORDER BY (icltl.UPDATE_DATE)";
						+ "        ORDER BY icltl.UPDATE_DATE";
						dbUtility.createPreparedStatement(query);
					}
				} else if (accountDTO.getCrn() != null && !(accountDTO.getCrn().trim().isEmpty())) {
					listdistid.add(accountDTO.getCrn());
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_ESTAMP_VIEW + "         AND VIEW1.CRN_EPRN = ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_ESTAMP_VIEW_H + "         AND VIEW1.CRN_EPRN = ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				} else if (accountDTO.getTxnid() != null && !(accountDTO.getTxnid().trim().isEmpty())) {
					listdistid.add(accountDTO.getTxnid());
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_ESTAMP_VIEW + "         AND payment_txn_id= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_ESTAMP_VIEW_H + "         AND payment_txn_id= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				} else if (accountDTO.getPaymentamount() != null && !(accountDTO.getPaymentamount().trim().isEmpty())) {
					listdistid.add(accountDTO.getPaymentamount());
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_ESTAMP_VIEW + "         AND paid_amount= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query.trim());
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_ESTAMP_VIEW_H + "         AND paid_amount= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				}

				list = dbUtility.executeQuery(listdistid);
				logger.info("STATEMENT LIST:----" + list);
				// accountDTO.setTodate(null);
				// accountDTO.setFromdate(null);
				accountDTO.setCrn(null);
				accountDTO.setTxnid(null);
				accountDTO.setPaymentamount(null);
			} catch (Exception exception) {
				logger.info("Exception in getServiceProviderStmtEstamp" + exception);
			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
				}
			}
			return list;
		}// Start Validate user id based on license number
		else {
			throw new Exception("/jsp/SessionExpired.jsp");
		}
		// End Validate user id based on license number
	}

	public String getOpeningBal(String sessionLicenseNo) throws Exception {
		DBUtility dbUtility = new DBUtility();
		String openingBal = "";
		try {
			dbUtility = new DBUtility();
			// dbUtility.createStatement();
			String[] licnoDetails = {sessionLicenseNo, sessionLicenseNo};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SPOPENINGBALANCE);
			openingBal = dbUtility.executeQry(licnoDetails);
			if (openingBal == "") {
				openingBal = "0";
			}
			logger.info("OPENING BALANCE----" + openingBal);
		} catch (Exception exception) {
			logger.info("EXCEPTION IN OPENING BALANCE MEHTOD" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return openingBal;
	}

	public String getOpeningBalEstamp(String sessionLicenseNo) throws Exception {
		DBUtility dbUtility = new DBUtility();
		String openingBal = "";
		try {
			// dbUtil=new DBUtility();
			dbUtility.createStatement();
			String[] licnoDetails = {sessionLicenseNo, sessionLicenseNo};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SPOPENINGBALANCEESTAMP);
			openingBal = dbUtility.executeQry(licnoDetails);
			if (openingBal == "") {
				openingBal = "0";
			}
			logger.info("OPENING BALANCE----" + openingBal);
		} catch (Exception exception) {
			logger.info("EXCEPTION IN OPENING BALANCE ESTAMP MEHTOD" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return openingBal;
	}

	/**
	 * Method name :getServiceProviderStmtmonth
	 * 
	 * @param userid
	 * @param accountDTO
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getServiceProviderStmtmonth(String userid, ServiceProviderAccountDTO accountDTO) throws Exception {
		DBUtility dbUtility = new DBUtility();
		try {

			String arrdistid[] = {userid, accountDTO.getPaymonth(), accountDTO.getPayyear()};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTSERVICEPROVIDERACCTSTMT);
			list = dbUtility.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	// added by shruti for credited amount txn record for SP--------
	public boolean spAcntBalUpdt(ServiceProviderAccountDTO accountDTO, String userid) throws Exception {

		boolean flag = false;
		DBUtility dbUtility = new DBUtility();
		try {

			dbUtility = new DBUtility();
			String spdtls[] = {accountDTO.getSrNo(), getSPLicenseNumberPayment(userid),
			// accountDTO.getLicencenumber(),
			// accountDTO.getPaymentdate(),
			// accountDTO.getPaymenttypename(),
			// accountDTO.getTransactionno(),
			// accountDTO.getPayamount(),
			// accountDTO.getTxnpurpose(),
			// accountDTO.getAccountbalance()
			userid, "FUN_300"};
			// logger.info("@@@@@@"+amount);
			dbUtility.createPreparedStatement(ServiceProviderSQL.CREDIT_SP_ACNT);
			flag = dbUtility.executeUpdate(spdtls);

		} catch (Exception exception) {
			logger.info("Exception in getNewSPSerialNumber" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return flag;

	}

	// added by Lavi for avail of credit limit for estamp

	public boolean spAcntBalUpdtEstamp(ServiceProviderAccountDTO accountDTO, String userid) throws Exception {

		boolean flag = false;
		ServiceProviderAccountDAO dao = new ServiceProviderAccountDAO();
		DBUtility dbUtility = new DBUtility();
		try {

			dbUtility = new DBUtility();
			String spdtls[] = {accountDTO.getSrNo(),
			// accountDTO.getLicencenumber(),
			getSPLicenseNumberPayment(userid),// neeraj
			// accountDTO.getPaymentdate(),
			// accountDTO.getPaymenttypename(),
			// accountDTO.getTransactionno(),
			// accountDTO.getPayamount(),
			// accountDTO.getTxnpurpose(),
			// accountDTO.getAccountbalance()
			userid, "FUN_200"};
			// logger.info("@@@@@@"+amount);
			dbUtility.createPreparedStatement(ServiceProviderSQL.CREDIT_SP_ACNT_ESTAMP);
			flag = dbUtility.executeUpdate(spdtls);

		} catch (Exception exception) {
			logger.info("Exception in getNewSPSerialNumber" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return flag;

	}

	// added by shruti for serial number generation of credit and debit
	// transactions
	public String getCrdtLimitSerialNumber() throws Exception {
		String crdtlmtxnid = "";
		DBUtility dbUtility = new DBUtility();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			crdtlmtxnid = dbUtility.executeQry(ServiceProviderSQL.SELECT_CRDT_LIMIT_TXN_SEQ);
		} catch (Exception exception) {
			logger.info("Exception in getCrdtLimitSerialNumber" + exception);
		}
		// finally cannot be added----part of transaction-12 may 2014 -shruti
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return crdtlmtxnid;
	}

	// added by lavi for serial number generation of credit and debt
	// transactions for estamp

	public String getCrdtLimitSerialNumberEstamp() throws Exception {
		String crdtlmtxnid = "";
		DBUtility dbUtility = new DBUtility();
		try {
			dbUtility.createStatement();
			crdtlmtxnid = dbUtility.executeQry(ServiceProviderSQL.CRDT_LIMIT_TXN_SEQ_ESTAMP);
		} catch (Exception exception) {
			logger.info("Exception in getCrdtLimitSerialNumber" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		// finally cannot be added----part of transaction-12 may 2014 -shruti
		return crdtlmtxnid;
	}
	// end of addition by lavi for serial number generation of credit and debt
	// transactions for estamp

	// added by shruti for debited amt txn record for SP

	public boolean spDebitAcntBalUpdt(ServiceProviderAccountDTO accountDTO, String userid) throws Exception {

		boolean flag = false;
		DBUtility dbUtility = new DBUtility();

		try {

			// dbUtil = new DBUtility();
			dbUtility.setAutoCommit(false);
			String spdtls[] = {getCrdtLimitSerialNumber(), accountDTO.getLicencenumber(), accountDTO.getPaymenttypename(), accountDTO.getTransactionno(), accountDTO.getPaymentamount(),

			accountDTO.getTxnpurpose(), accountDTO.getAccountbalance(), accountDTO.getSpcomm()};
			dbUtility.createPreparedStatement(ServiceProviderSQL.DEBIT_SP_ACNT);
			flag = dbUtility.executeUpdate(spdtls);
			if (flag) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
		} catch (Exception exception) {
			logger.info("Exception in credit limit usage" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return flag;

	}

	public ArrayList spCreditBalance(String lno) throws Exception {
		DBUtility dbUtility = new DBUtility();
		try {
			String[] uid = {lno, lno};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTCREDITBALANCE);
			list = dbUtility.executeQuery(uid);
		} catch (Exception exception) {
			logger.info("Exception in getspCreditBalance-DAO" + exception);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getLicenseNumber(String userid) throws Exception {
		DBUtility dbUtility = new DBUtility();

		try {
			String uid[] = {userid};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTLICENSENUMBER);
			list = dbUtility.executeQuery(uid);

			// commented by Lavi for new getting LicenseDetails from new Tables

			/*
			 * if(list.size()==0) {
			 * dbUtil.createPreparedStatement(CommonSQL.SELECTLICENSENUMBER1);
			 * list = dbUtil.executeQuery(uid); }
			 */

		} catch (Exception exception) {
			logger.info("Exception in get License Number" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	public String getSPLicenseNumberPayment(String userid) throws Exception {
		DBUtility dbUtility = new DBUtility();
		String licNo = null;
		try {

			String uid[] = {userid};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECTLICENSENUMBER);
			licNo = dbUtility.executeQry(uid);

		} catch (Exception exception) {
			logger.info("Exception in get License Number" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return licNo;
	}

	public String getSpCommFactor() throws Exception {
		String spcommfctr = "";
		DBUtility dbUtility = new DBUtility();
		try {
			dbUtility.createStatement();
			spcommfctr = dbUtility.executeQry(ServiceProviderSQL.SELECTSPCOMMFACTOR);
		} catch (Exception exception) {
			logger.info("Exception in get Sp Comm Factor");
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return spcommfctr;
	}

	public String getPaymentFlag(String sr_no) throws Exception {

		String paymentFlag = null;
		DBUtility dbUtility = new DBUtility();

		try {
			// dbUtil = new DBUtility();
			dbUtility.createStatement();
			paymentFlag = dbUtility.executeQry(ServiceProviderSQL.SPPAYMENTFLAGSTATUS + "'" + sr_no + "'");
		} catch (Exception exception) {
			logger.info("Exception in getPaymentFlag-ServiceProviderAccountDAO" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return paymentFlag;

	}
	public boolean getUpdatedPaymentFlag(String sr_no) throws Exception {

		boolean flag = false;
		// String paymentFlag=null;
		DBUtility dbUtility = new DBUtility();

		try {
			// dbUtil = new DBUtility();
			dbUtility.createStatement();

			String[] srno = {sr_no};
			dbUtility.createPreparedStatement(ServiceProviderSQL.SETPAYMENTSTATUS);
			flag = dbUtility.executeUpdate(srno);
			dbUtility.commit();

		} catch (Exception exception) {
			logger.info("Exception in getPaymentFlag-ServiceProviderAccountDAO" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return flag;

	}

	public String getCreditAmt(String licenseno) throws Exception {
		String crdtamt = "";
		DBUtility dbUtility = new DBUtility();
		try {
			// dbUtil=new DBUtility();
			// dbUtility.createStatement();
			String[] lno = {licenseno};
			dbUtility.createPreparedStatement(ServiceProviderSQL.GETCREDITAMOUNT);
			crdtamt = dbUtility.executeQry(lno);
			logger.info("C amount----" + crdtamt);
		} catch (Exception exception) {
			logger.info("Exception in getCreditAmt" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return crdtamt;
	}
	public String getDebitAmt(String licenseno) throws Exception {
		String debitamt = "";
		DBUtility dbUtility = new DBUtility();
		try {
			// dbUtil=new DBUtility();
			// dbUtility.createStatement();
			String[] lno = {licenseno};
			dbUtility.createPreparedStatement(ServiceProviderSQL.GETDEBITAMOUNT);
			debitamt = dbUtility.executeQry(lno);
			logger.info("D amount----" + debitamt);
		} catch (Exception exception) {
			logger.info("Exception in getDebitAmt" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return debitamt;
	}
	public String getPaidAmt(String sr_no) throws Exception {
		String paidAmt = "";
		DBUtility dbUtility = new DBUtility();
		try {
			// dbUtil=new DBUtility();
			// dbUtil.createStatement();
			String[] srno = {sr_no};
			dbUtility.createPreparedStatement(ServiceProviderSQL.GETPAIDAMT);
			paidAmt = dbUtility.executeQry(srno);
			logger.info("PAID amount----" + paidAmt);
		} catch (Exception exception) {
			logger.info("Exception in getDebitAmt" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return paidAmt;
	}
	public boolean getUpdatedSPAmtFlag(String spAmt, String sr_no) throws Exception {

		boolean flag = false;
		// String paymentFlag=null;
		DBUtility dbUtility = new DBUtility();

		try {
			// dbUtil = new DBUtility();
			// dbUtil.createStatement();

			String[] srno = {spAmt, sr_no};
			dbUtility.createPreparedStatement(ServiceProviderSQL.UPDATESPAMT);
			flag = dbUtility.executeUpdate(srno);

		} catch (Exception exception) {
			logger.info("Exception in getPaymentFlag-ServiceProviderAccountDAO" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return flag;

	}

	public String getSPComm(String _funId, String _amt) throws Exception {
		String spComm = "";
		logger.info("Inside getOthersFeeDuty()");
		DBUtility dbUtility = new DBUtility();
		try {

			// dbUtil = new DBUtility();
			clstmt = dbUtility.returnCallableStatement(ServiceProviderSQL.SPCOMMISION_CALC);
			clstmt.setString(1, _funId);
			clstmt.setString(2, _amt);
			clstmt.registerOutParameter(3, OracleTypes.DOUBLE);

			if (!clstmt.execute()) {
				double temp = clstmt.getDouble(3);
				logger.info("value fetched in callable statement-----" + temp);
				spComm = String.valueOf(temp);
				logger.info("value as fetched from callable statement and conversion into string-----" + spComm);
				double spCommamt = Double.parseDouble(spComm);
				logger.info("double converted value:-------" + spCommamt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {

			dbUtility.closeConnection();
		}
		logger.info("spComm:-" + spComm);
		return spComm;
	}

	// added by Lavi for integration with new payment module.

	public ArrayList getOfficeId(ServiceProviderAccountForm providerAccountForm) throws Exception {
		String userid = providerAccountForm.getAccountDTO().getUserId();
		String uid[] = {userid};
		DBUtility dbUtility = new DBUtility();
		try {
			// dbUtil=new DBUtility();
			dbUtility.createPreparedStatement(ServiceProviderSQL.SELECT_OFC_DISTT_DR);
			list = dbUtility.executeQuery(uid);
		} catch (Exception exception) {
			logger.info("Exception in getOfficeId" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	public String getLicenseId(String userid) throws Exception {
		DBUtility dbUtility = new DBUtility();
		String licenseId = "";
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection conn = dbUtility.getDBConnection();
			String sql = "select distinct LICENSE_NUMBER FROM IGRS_SP_DETLS WHERE CREATED_BY='" + userid + "' and STATUS='A' and APPLCTN_STATUS=8";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				licenseId = rs.getString(1);
			}
		} catch (Exception exception) {
			logger.info("Exception in get License Number" + exception);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
			}
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
			}
		}

		return licenseId;
	}

	// Start Validate user id based on license number
	public List<List<String>> getUserId(String licenseNumber) throws Exception {
		List<List<String>> userList = null;
		DBUtility dbUtility = new DBUtility();
		List<String> licenseList = new ArrayList<String>();
		try {
			licenseList.add(licenseNumber);
			dbUtility.createPreparedStatement(ServiceProviderSQL.GET_USER_ID);
			userList = dbUtility.executeQuery(licenseList);
		} catch (Exception e) {
			logger.info("Exception in Getting UserId based on license number" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Judicial Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return userList;
	}
	// End Validate user id based on license number

	// START | changes to fetch credit limit param value by santosh
	public String getNonJudLedgerParam() throws Exception {
		String nonJudLedgerParam = "";
		DBUtility dbUtility = new DBUtility();
		try {
			dbUtility.createStatement();
			nonJudLedgerParam = dbUtility.executeQry(ServiceProviderSQL.NONJUDESTAMPLEDGERPARAM);
		} catch (Exception exception) {
			logger.info("Exception in get Sp Comm Factor");
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return nonJudLedgerParam;
	}

	public String getOtherServJudLedgerParam() throws Exception {
		String otherServicesLedgerParam = "";
		DBUtility dbUtility = new DBUtility();
		try {
			dbUtility.createStatement();
			otherServicesLedgerParam = dbUtility.executeQry(ServiceProviderSQL.OTHERSERVICESLEDGERPARAM);
		} catch (Exception exception) {
			logger.info("Exception in get Sp Comm Factor");
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return otherServicesLedgerParam;
	}
	// END | changes to fetch credit limit param value by santosh
	// added by saurav on 10/02/22 for fetching account balance of mutation.
	public ArrayList getSpAcntDetailsMutation(String userid, String sessionLicenseNo) throws Exception {
		DBUtility dbUtility = new DBUtility();
		try {
			String arrdistid[] = {userid, sessionLicenseNo, sessionLicenseNo};
			dbUtility.createPreparedStatement(ServiceProviderSQL.FETCH_ACC_BAL_MUTAION);
			ArrayList listDetails = new ArrayList();
			listDetails = dbUtility.executeQuery(arrdistid);
			list = listDetails;// dbUtility.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}
	public boolean spAcntBalUpdtMutation(ServiceProviderAccountDTO accountDTO, String userid) throws Exception {

		boolean flag = false;
		ServiceProviderAccountDAO dao = new ServiceProviderAccountDAO();
		DBUtility dbUtility = new DBUtility();
		try {

			dbUtility = new DBUtility();
			String spdtls[] = {accountDTO.getSrNo(), getSPLicenseNumberPayment(userid), userid, "FUN_600"};

			dbUtility.createPreparedStatement(ServiceProviderSQL.CREDIT_SP_ACNT_MUTATION);
			flag = dbUtility.executeUpdate(spdtls);

		} catch (Exception exception) {
			logger.info("Exception in spAcntBalUpdtMutation" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		return flag;

	}
	public String getCrdtLimitSerialNumberMutation() throws Exception {
		String crdtlmtxnid = "";
		DBUtility dbUtility = new DBUtility();
		try {
			dbUtility.createStatement();
			crdtlmtxnid = dbUtility.executeQry(ServiceProviderSQL.CRDT_LIMIT_TXN_SEQ_MUTATION);
		} catch (Exception exception) {
			logger.info("Exception in getCrdtLimitSerialNumber" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
			}
		}
		// finally cannot be added----part of transaction-12 may 2014 -shruti
		return crdtlmtxnid;
	}

	public String getMutationServJudLedgerParam() throws Exception {
		String otherServicesLedgerParam = "";
		DBUtility dbUtility = new DBUtility();
		try {
			dbUtility.createStatement();
			otherServicesLedgerParam = dbUtility.executeQry(ServiceProviderSQL.GET_MUTATION_SERVICE_DURTATION);
		} catch (Exception exception) {
			logger.info("Exception in getMutationServJudLedgerParam");
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Service Provider Account DAO getMutationServJudLedgerParam " + e.getStackTrace());
			}
		}
		return otherServicesLedgerParam;
	}
	public ArrayList getServiceProviderMutationStatement(String sessionLicenseNo, ServiceProviderAccountDTO accountDTO, String language, HttpSession session) throws Exception {
		// End Validate user id based on license number
		DBUtility dbUtility = new DBUtility();
		List<String> listdistid = new ArrayList<String>();
		String query = null;
		// Start Validate user id based on license number
		List<List<String>> userList = getUserId(sessionLicenseNo);
		if (userList.size() == 1 && userList.get(0).get(0).equals(session.getAttribute("UserId"))) {
			// End Validate user id based on license number
			try {
				listdistid.add(sessionLicenseNo);
				listdistid.add(sessionLicenseNo);
				listdistid.add(sessionLicenseNo);
				if (accountDTO.getFromdate() != null && accountDTO.getTodate() != null && !(accountDTO.getFromdate().trim().isEmpty()) && !(accountDTO.getTodate().trim().isEmpty())) {
					listdistid.add(CommonUtil.getConvertedDate(accountDTO.getFromdate()));
					listdistid.add(CommonUtil.getConvertedDate(accountDTO.getTodate()));
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_MUTATION_VIEW + "         AND TO_DATE (icltl.TRANSACTION_DATE) BETWEEN ? AND ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_MUTATION_VIEW_H + "         AND TO_DATE (icltl.TRANSACTION_DATE) BETWEEN ? AND ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				} else if (accountDTO.getCrn() != null && !(accountDTO.getCrn().trim().isEmpty())) {
					listdistid.add(accountDTO.getCrn());
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_MUTATION_VIEW + "         AND VIEW1.CRN_EPRN = ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_MUTATION_VIEW_H + "         AND VIEW1.CRN_EPRN = ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				} else if (accountDTO.getTxnid() != null && !(accountDTO.getTxnid().trim().isEmpty())) {
					listdistid.add(accountDTO.getTxnid());
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_MUTATION_VIEW + "         AND icltl.payment_txn_id= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_MUTATION_VIEW_H + "         AND icltl.payment_txn_id= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				} else if (accountDTO.getPaymentamount() != null && !(accountDTO.getPaymentamount().trim().isEmpty())) {
					listdistid.add(accountDTO.getPaymentamount());
					if ("en".equalsIgnoreCase(language)) {
						query = ServiceProviderSQL.CREDIT_LIMIT_MUTATION_VIEW + "         AND icltl.paid_amount= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					} else {
						query = ServiceProviderSQL.CREDIT_LIMIT_MUTATION_VIEW_H + "         AND icltl.paid_amount= ? " + "        ORDER BY (icltl.UPDATE_DATE)";
						dbUtility.createPreparedStatement(query);
					}
				}

				list = dbUtility.executeQuery(listdistid);
				logger.info("STATEMENT LIST:----" + list);
				accountDTO.setTodate(null);
				accountDTO.setFromdate(null);
				accountDTO.setCrn(null);
				accountDTO.setTxnid(null);
				accountDTO.setPaymentamount(null);
			} catch (Exception exception) {
				logger.info("Exception in getServiceProviderMutationStatement" + exception);
			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
				}
			}
			return list;
		}// Start Validate user id based on license number
		else {
			throw new Exception("/jsp/SessionExpired.jsp");
		}
		// End Validate user id based on license number
	}
}
