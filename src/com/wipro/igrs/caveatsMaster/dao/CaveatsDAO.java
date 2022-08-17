package com.wipro.igrs.caveatsMaster.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.caveatsMaster.dto.CaveatsDTO;
import com.wipro.igrs.caveatsMaster.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;

public class CaveatsDAO {
	public CaveatsDAO() {
	}

	private Logger logger = (Logger) Logger.getLogger(CaveatsDAO.class);

	// $01 Method for Inserting Caveat Details in CAVEAT_TXN
	public boolean logCaveatsDAO(String[] param1, String param2[])
			throws SQLException, IOException, Exception {
		logger.debug("IN DAO logCaveats");
		DBUtility dbUtil = null;
		boolean log = false;
		try {
			dbUtil = new DBUtility();
			String qry1 = CommonSQL.CAVEATS_CHARGES_INSERT;
			dbUtil.createPreparedStatement(qry1);
			if (dbUtil.executeUpdate(param1)) {
				String qry2 = CommonSQL.CAVEATS_CHARGES_INSERT_PROPERTYID;
				dbUtil.createPreparedStatement(qry2);
				if (dbUtil.executeUpdate(param2)) {
					log = true;
					dbUtil.commit();
				} else
					dbUtil.rollback();
			}
		} catch (IOException ie) {
			logger.error(ie.getMessage(), ie);
		} catch (SQLException se) {
			logger.error(se.getMessage(), se);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return log;
	}

	// $01 End
	// $02 Method for Retrieving Caveat Type from CAVEAT_MASTER
	public ArrayList createCaveatsList() throws Exception {
		logger.debug("IN DAO createCaveatsList");
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;

		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CommonSQL.IGRS_CAVEATS_CAVEAT_MASTER;
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaveatsDTO type = new CaveatsDTO();
						type.setCaveatType((String) typeTemp.get(0));
						type.setCaveatId((String) typeTemp.get(1));
						type.setCaveatSorderStatus((String) typeTemp.get(0)
								+ "~" + (String) typeTemp.get(2) + "^"
								+ (String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (IOException ie) {
			logger.error(ie.getMessage(),ie);
		} catch (SQLException se) {
			logger.error(se.getMessage(),se);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// ////mona

	public boolean insertCaveatsMasterDAO(String[] param) throws Exception {
		logger.debug("IN DAO insertCaveatsMasterDAO");
		DBUtility dbUtil = null;
		boolean result = false;
		try {
			String query = CommonSQL.IGRS_CAVEATS_CAVEAT_MASTER_INSERT_ALL_FIELDS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(query);
			result = dbUtil.executeUpdate(param);
			return result;
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return result;
	}

	public ArrayList retrieveAllCaveats() throws Exception {
		logger.debug("IN DAO retrieveAllCaveats");
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;

		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CommonSQL.IGRS_CAVEATS_CAVEAT_MASTER_RETRIEVE_ALL;
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaveatsDTO type = new CaveatsDTO();

						type.setCaveatId((String) typeTemp.get(0));
						type.setCaveatType((String) typeTemp.get(1));
						type.setCaveatDetails((String) typeTemp.get(2));
						type.setCaveatStatus((String) typeTemp.get(3));
						type.setCaveatCreatedBy((String) typeTemp.get(4));

						type.setCaveatCreatedDate(typeTemp.get(5).toString());
						type.setCaveatUpdatedBy((String) typeTemp.get(6));
						type.setCaveatUpdatedDate(typeTemp.get(7).toString());
						type.setCaveatStayOrderStatus((String) typeTemp.get(8));

						ar1.add(type);
					}
				}
			}
		} catch (IOException ie) {

			logger.error(ie);
		} catch (SQLException se) {

			logger.error(se);
		} catch (Exception e) {

			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	public boolean updateCaveatsMasterDAO(String[] param) throws Exception {
		logger.debug("IN DAO insertCaveatsMasterDAO");
		DBUtility dbUtil = null;
		boolean result = false;
		try {
			String query = CommonSQL.IGRS_CAVEATS_CAVEAT_MASTER_UPDATE;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(query);
			result = dbUtil.executeUpdate(param);
			return result;
		} catch (IOException ie) {
			logger.error(ie);

		} catch (SQLException se) {
			logger.error(se);

		} catch (Exception e) {
			logger.error(e);

		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return result;
	}

	public boolean deleteCaveatsMasterDAO(String id) throws Exception {
		logger.debug("IN DAO deleteCaveatsMasterDAO");
		DBUtility dbUtil = null;
		boolean result = false;
		String[] param = new String[1];
		param[0] = id;
		try {
			String query = CommonSQL.IGRS_CAVEATS_CAVEAT_MASTER_DELETE;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(query);
			result = dbUtil.executeUpdate(param);
			return result;
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return result;
	}

	public boolean isCaveatsExist(String name) throws Exception {// if it
																	// returns
		// true its an
		// error
		logger.debug("IN DAO isExist");
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;

		boolean result = false;
		String[] param = new String[1];
		param[0] = name;

		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CommonSQL.IGRS_CAVEATS_CAVEAT_MASTER_GET_NAME;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);

			if (!typeList.isEmpty()) {
				result = true;
			}

		} catch (IOException ie) {

			logger.error(ie);

		} catch (SQLException se) {

			logger.error(se);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e);

		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}

		return result;
	}

	public CaveatsDTO retrieveCavietsByName(String name) throws Exception {
		logger.debug("IN DAO retrieveCavietsByName");
		CaveatsDTO type = new CaveatsDTO();
		DBUtility dbUtil = null;

		boolean result = false;
		String[] param = new String[1];
		param[0] = name;

		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CommonSQL.IGRS_CAVEATS_CAVEAT_MASTER_GET_NAME;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);

			if (typeList != null) {

				typeTemp = new ArrayList();
				typeTemp = (ArrayList) typeList.get(0);
				if (typeTemp.size() > 0) {
					// CaveatsDTO type = new CaveatsDTO();

					type.setCaveatId((String) typeTemp.get(0));
					type.setCaveatType((String) typeTemp.get(1));
					type.setCaveatDetails((String) typeTemp.get(2));
					type.setCaveatStatus((String) typeTemp.get(3));
					type.setCaveatCreatedBy((String) typeTemp.get(4));

					// type.setCaveatCreatedDate(typeTemp.get(5));
					type.setCaveatUpdatedBy((String) typeTemp.get(6));
					// type.setCaveatUpdatedDate((Date)typeTemp.get(7));
					type.setCaveatStayOrderStatus((String) typeTemp.get(8));

				}
			}

		} catch (IOException ie) {

			logger.error(ie);

		} catch (SQLException se) {

			logger.error(se);

		} catch (Exception e) {

			logger.error(e);

		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}

		return type;
	}

	// ////////////////////
	// $02 End
	// $03.START Method for Retrieving Country from MASTER
	public ArrayList countryList() throws Exception {
		logger.debug("IN DAO countryList");
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CommonSQL.IGRS_COUNTRY_MASTER; // Query for Country
			// list from
			// IGRS_COUNTRY_MASTER
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaveatsDTO type = new CaveatsDTO();
						type.setCountryId((String) typeTemp.get(0));
						type.setCountryName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $03.END Method for Retriving Country from Master
	// $04.START Method for Retrieving State from MASTER
	public ArrayList stateList(String countryIdVar) throws Exception {
		logger.debug("IN DAO stateList");
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;

		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();
			dbUtil = new DBUtility();
			String str = CommonSQL.IGRS_STATE_MASTER; // Query for Country
														// list
			// from IGRS_COUNTRY_MASTER
			String param[] = new String[1];
			param[0] = "" + countryIdVar;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaveatsDTO type = new CaveatsDTO();
						type.setStateId((String) typeTemp.get(0));
						type.setStateName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $04.END Method for Retriving Country from Master
	// $05. Method for Searching Caveat Using Reference Id
	public ArrayList findCaveatDAO(String refId) throws Exception {
		logger.debug("IN DAO findCaveat");
		ArrayList list = null;
		DBUtility dbUtil = new DBUtility();
		try {
			String qury = new String(CommonSQL.IGRS_CAVEATS_SEARCH_REFID);
			String param[] = new String[1];
			param[0] = "" + refId;

			dbUtil.createPreparedStatement(qury);
			list = dbUtil.executeQuery(param);
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return list;
	}

	// $05 End
	// $06
	public ArrayList releaseSearchByAllDAO(String searchFields[])
			throws IOException, SQLException, Exception {
		logger.debug("IN DAO releaseSearchByAllDAO");
		ArrayList list = new ArrayList();
		CaveatsDTO cDTO = new CaveatsDTO();
		list = null;
		ArrayList list2 = new ArrayList();
		String regNo = searchFields[1];
		String frDate = searchFields[2];
		String toDate = searchFields[3];
		String errMessages = null;
		try {
			DBUtility dbUtil = new DBUtility();
			if (!(regNo.equalsIgnoreCase("") || regNo.equalsIgnoreCase(null))) {
				String param[] = new String[1];
				String query = CommonSQL.IGRS_CAVEATS_RELEASE_BY_REGNO;
				param[0] = "" + regNo;
				dbUtil.createPreparedStatement(query);
				list = dbUtil.executeQuery(param);
				errMessages = "Caveat has not found for given REGISTRATION NUMBER";
			} else if (!(frDate.equalsIgnoreCase("")
					|| frDate.equalsIgnoreCase(null)
					|| toDate.equalsIgnoreCase("") || toDate
					.equalsIgnoreCase(null))) {
				String param[] = new String[2];
				String query = CommonSQL.IGRS_CAVEATS_RELEASE_BY_DURATION;
				param[0] = "" + frDate;
				param[1] = "" + toDate;
				dbUtil.createPreparedStatement(query);
				list = dbUtil.executeQuery(param);
				errMessages = "Caveat has not found for given DURATION";
			}
			if (list.isEmpty()) {
				cDTO.setErrorMsg(errMessages);
				list2.add(cDTO);
			} else {
				try {
					ArrayList typeTemp = new ArrayList();
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							typeTemp = new ArrayList();
							typeTemp = (ArrayList) list.get(i);
							if (typeTemp.size() > 0) {
								CaveatsDTO type = new CaveatsDTO();
								type.setRegistrationNumber((String) typeTemp
										.get(0));
								type.setFlag((String) typeTemp.get(1));
								type.setReferenceID((String) typeTemp.get(2));
								type.setLoggedDate((String) typeTemp.get(3));
								type.setSerialNo(i + 1);
								list2.add(type);
							}
						}
					}
				} catch (Exception e) {
					logger.error(e);
				} finally {
					logger.debug("using FINALLY Connection is closed");
					dbUtil.closeConnection();
				}
			}
			return list2;
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("Finally Called");
		}
		return null;
	}

	// $11 Method for search to be Released Caveat by Reg No, Duration
	public ArrayList relsCavtBankByAllDAO(String searchFields[]) {
		logger.debug("IN DAO relsCavtBankByAllDAO");
		ArrayList list = new ArrayList();
		CaveatsDTO cDTO = new CaveatsDTO();
		list = null;
		ArrayList list2 = new ArrayList();
		String regNo = searchFields[1];
		String frDate = searchFields[2];
		String toDate = searchFields[3];
		String errMessages = null;
		try {
			DBUtility dbUtil = new DBUtility();
			if (!(regNo.equalsIgnoreCase("") || regNo.equalsIgnoreCase(null))) {
				String param[] = new String[1];
				String query = CommonSQL.IGRS_CAVEATS_BANK_RELEASE_REGNO;
				param[0] = "" + regNo;
				dbUtil.createPreparedStatement(query);
				list = dbUtil.executeQuery(param);
				errMessages = "Caveat has not found for given REGISTRATION NUMBER";
			} else if (!(frDate.equalsIgnoreCase("")
					|| frDate.equalsIgnoreCase(null)
					|| toDate.equalsIgnoreCase("") || toDate
					.equalsIgnoreCase(null))) {
				String param[] = new String[2];
				String query = CommonSQL.IGRS_CAVEATS_BANK_RELEASE_DURATION;
				param[0] = "" + frDate;
				param[1] = "" + toDate;
				dbUtil.createPreparedStatement(query);
				list = dbUtil.executeQuery(param);
				errMessages = "Caveat has not found for given DURATION";
			}
			if (list.isEmpty()) {
				cDTO.setErrorMsg(errMessages);
				list2.add(cDTO);
			} else {
				try {
					ArrayList typeTemp = new ArrayList();
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							typeTemp = new ArrayList();
							typeTemp = (ArrayList) list.get(i);
							if (typeTemp.size() > 0) {
								CaveatsDTO type = new CaveatsDTO();
								type.setRegistrationNumber((String) typeTemp
										.get(0));
								type.setFlag((String) typeTemp.get(1));
								type.setReferenceID((String) typeTemp.get(2));
								type.setLoggedDate((String) typeTemp.get(3));
								type.setSerialNo(i + 1);
								list2.add(type);
							}
						}
					}
				} catch (NullPointerException e) {
					logger.error(e);
				} catch (Exception e) {
					logger.error(e);
				} finally {
					logger.debug("using Finally Connection is closed");
				}
			}
			return (list2);
		} catch (Exception e) {
			logger.error(e);
			return (list2);
		}
	}

	// $11 End
	public ArrayList releaseByRegnoDAO(String RegNo) throws IOException,
			SQLException, Exception {
		logger.debug("IN DAO releaseByRegnoDAO");
		ArrayList list = null;
		DBUtility dbUtil = new DBUtility();
		try {
			String param[] = new String[1];
			String query = CommonSQL.IGRS_CAVEATS_RELEASE_BY_REGNO;
			param[0] = "" + RegNo;
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(param);
			return (list);
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return list;
	}

	// $13 Method for searching Registration Id
	public ArrayList searchRegIdDao(String RegNo) throws IOException,
			SQLException, Exception {
		logger.debug("IN DAO searchRegIdDao");
		ArrayList list = null;
		DBUtility dbUtil = new DBUtility();
		try {
			String param[] = new String[1];
			String query = CommonSQL.IGRS_CAVEATS_REGID_SEARCH_IN_REG_TABLE;
			param[0] = "" + RegNo;
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(param);
			return (list);
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return list;
	}

	// $13 End
	public boolean modifyFlagDAO(String param[]) throws Exception {
		logger.debug("IN DAO modifyFlagDAO");
		DBUtility dbUtil = new DBUtility();
		try {
			String query = CommonSQL.IGRS_CAVEATS_UPDATE_RELEASE;
			dbUtil.createPreparedStatement(query);
			return (dbUtil.executeUpdate(param));
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return false;
	}

	// $12 Method for updating status for Released Caveat
	public boolean relBankUpdationDAO(String param[]) throws Exception {
		logger.debug("IN DAO relBankUpdationDAO");
		DBUtility dbUtil = new DBUtility();
		try {
			String query = CommonSQL.IGRS_CAVEATS_BANK_RELEASE_UPDATE;
			dbUtil.createPreparedStatement(query);
			return dbUtil.executeUpdate(param);
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return false;
	}

	// $12 End
	public ArrayList searchByRefidDAO(String refid) throws Exception {
		logger.debug("IN DAO searchByRefidDAO");
		DBUtility dbUtil = new DBUtility();
		try {
			String param[] = new String[1];
			String query = CommonSQL.IGRS_CAVEATS_SEARCH_REFID_VIEW;
			param[0] = "" + refid;
			dbUtil.createPreparedStatement(query);
			ArrayList list = dbUtil.executeQuery(param);
			return list;
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return null;
	}

	public ArrayList searchByAllDAO(String searchFields[]) {
		logger.debug("IN DAO searchByAllDAO");
		ArrayList list = new ArrayList();
		CaveatsDTO cDTO = new CaveatsDTO();
		list = null;
		ArrayList list2 = new ArrayList();
		String refId = searchFields[0];
		String regNo = searchFields[1];
		String status = searchFields[2];
		String frDate = searchFields[3];
		String toDate = searchFields[4];
		String errMessages = null;
		try {
			DBUtility dbUtil = new DBUtility();
			if ((!(refId.equalsIgnoreCase(""))) && (!(refId == null))) {
				String param[] = new String[1];
				String query = CommonSQL.IGRS_CAVEATS_SEARCH_TXNID_VIEW;
				param[0] = "" + refId;
				dbUtil.createPreparedStatement(query);
				list = dbUtil.executeQuery(param);
				errMessages = "Caveat has not been found for given Reference ID";
			} else if (!regNo.equalsIgnoreCase("")) {
				String param[] = new String[1];
				String query = CommonSQL.IGRS_CAVEATS_SEARCH_REGNO_VIEW;
				param[0] = "" + regNo;
				dbUtil.createPreparedStatement(query);
				list = dbUtil.executeQuery(param);
				errMessages = "Caveat has not been found for given Registration ID";
			} else if (!(status.equalsIgnoreCase("[Select]") || status
					.equalsIgnoreCase(null))) {
				String param[] = new String[1];
				String query = CommonSQL.IGRS_CAVEATS_SEARCH_STATUS_VIEW;
				param[0] = "" + status;
				dbUtil.createPreparedStatement(query);
				list = dbUtil.executeQuery(param);
				errMessages = "Caveat has not been found for given Status";
			} else if (!(frDate.equalsIgnoreCase("")
					|| frDate.equalsIgnoreCase(null)
					|| toDate.equalsIgnoreCase("") || toDate
					.equalsIgnoreCase(null))) {
				String param[] = new String[2];
				String query = CommonSQL.IGRS_CAVEATS_SEARCH_DURATION_VIEW;
				param[0] = "" + frDate;
				param[1] = "" + toDate;
				dbUtil.createPreparedStatement(query);
				list = dbUtil.executeQuery(param);
				errMessages = "Caveat has not been found for given Duration";
			}
			if (list.isEmpty()) {
				cDTO.setErrorMsg(errMessages);
				list2.add(cDTO);
			} else {
				try {
					ArrayList typeTemp = new ArrayList();
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							typeTemp = new ArrayList();
							typeTemp = (ArrayList) list.get(i);
							if (typeTemp.size() > 0) {
								CaveatsDTO type = new CaveatsDTO();
								type.setRegistrationNumber((String) typeTemp
										.get(0));
								type.setFlag((String) typeTemp.get(1));
								type.setReferenceID((String) typeTemp.get(2));
								type.setLoggedDate((String) typeTemp.get(3));
								type.setSerialNo(i + 1);
								list2.add(type);
							}
						}
					}
				} catch (NullPointerException e) {
					logger.error(e);
				} catch (Exception e) {
					logger.error(e);
				} finally {
					dbUtil.closeConnection();
					logger.debug("Connection is closed using finally");
				}
			}
			return (list2);
		} catch (Exception e) {
			logger.error(e);
			return (list2);
		}
	}

	public ArrayList searchForPinDAO(String regno, String pin)
			throws IOException, SQLException, Exception {
		logger.debug("IN DAO searchForPinDAO");
		ArrayList list = null;
		DBUtility dbUtil = new DBUtility();
		CaveatsDTO cDTO = new CaveatsDTO();
		try {
			String param[] = new String[2];
			String query = CommonSQL.IGRS_CAVEATS_CHECK_PIN;
			param[0] = "" + regno;
			param[1] = "" + pin;

			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(param);
			ArrayList resultList = new ArrayList();
			resultList = null;
			if (!list.isEmpty()) {
				String param2[] = new String[1];
				String query2 = CommonSQL.IGRS_CAVEATS_OTT_ACTIVE;
				param2[0] = "" + regno;
				dbUtil.createPreparedStatement(query2);
				ArrayList list2 = dbUtil.executeQuery(param2);
				if (list2.isEmpty()) {
					resultList = list;
				}
			} else {
				cDTO.setErrorMsg("not Found");
			}
			return resultList;
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return null;
	}

	public CaveatsDTO insertOttDAO(String regno, String pin, String ott)
			throws IOException, SQLException, Exception {
		logger.debug("IN DAO insertOttDAO");
		CaveatsDTO cDTO = new CaveatsDTO();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String query = CommonSQL.IGRS_CAVEATS_OTT_EXDAYS;
			dbUtil.createStatement();
			String days = dbUtil.executeQry(query);
			int exdays = Integer.parseInt(days);
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DATE, exdays);
			Date today = new Date();
			String DATE_FORMAT = "dd/MM/yyyy";
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					DATE_FORMAT);

			String param[] = new String[6];
			param[0] = regno;
			param[1] = pin;
			param[2] = ott;
			param[3] = sdf.format(c1.getTime());
			param[4] = "Active";
			param[5] = "" + sdf.format(today);
			query = CommonSQL.IGRS_CAVEATS_OTT_INSERT;
			dbUtil.createPreparedStatement(query);
			boolean insertion = dbUtil.executeUpdate(param);
			if (insertion) {
				cDTO.setRegistrationNumber(param[0]);
				cDTO.setPinNumber(param[1]);
				cDTO.setOttNumber(param[2]);
				cDTO.setOttExpiryDate(param[3]);
				cDTO.setOttDate(sdf.format(today));
				return (cDTO);
			} else {
				return null;
			}
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return null;
	}

	public ArrayList searchForOttDAO(String regno, String ott)
			throws SQLException, IOException, Exception {
		logger.debug("IN DAO searchByAll");
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			String param[] = new String[2];
			String query = CommonSQL.IGRS_CAVEATS_OTT_SEARCH_BYBANK;
			param[0] = "" + regno;
			param[1] = "" + ott;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(param);
			return list;
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return list;
	}

	public boolean insertCaveatBankDAO(String[] param) throws Exception {
		logger.debug("IN DAO searchByAll");
		DBUtility dbUtil = null;
		boolean result = false;
		try {
			String query = CommonSQL.IGRS_CAVEATS_INSERT_BYBANK;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(query);
			result = dbUtil.executeUpdate(param);
			return result;
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return result;
	}

	public boolean updateOttStatusDAO(String regno, String ottno)
			throws IOException, SQLException, Exception {
		logger.debug("IN DAO updateOttStatusDAO");
		DBUtility dbUtil = null;
		try {
			String param[] = new String[2];
			param[0] = regno;
			param[1] = ottno;
			String query = CommonSQL.IGRS_CAVEATS_OTT_UPDATE;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(query);
			return (dbUtil.executeUpdate(param));
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return false;
	}

	public ArrayList releaseCvtBankByRefIdDAO(String refId) throws Exception {
		logger.debug("IN DAO releaseCvtBankByRefIdDAO");
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			String qury = new String(CommonSQL.IGRS_CAVEATS_BANK_RELEASE_REFID);
			String param[] = new String[1];
			param[0] = "" + refId;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(qury);
			list = dbUtil.executeQuery(param);
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return list;
	}

	// $03 Method for Retrieving Districts from DISTRICT_MASTER
	public ArrayList districtStack(String _stateIdVar) throws Exception {
		logger.debug("IN DAO districtStack");
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;

		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CommonSQL.IGRS_DISTRICT_MASTER; // Query for
															// district
			// list from
			// IGRS_DISTRICT_MASTER
			String param[] = new String[1];
			param[0] = "" + _stateIdVar;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaveatsDTO type = new CaveatsDTO();
						type.setDistrictId((String) typeTemp.get(0));
						type.setDistrictName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}
	// $03 End
}
