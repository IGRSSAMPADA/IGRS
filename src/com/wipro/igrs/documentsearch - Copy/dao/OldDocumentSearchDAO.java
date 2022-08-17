package com.wipro.igrs.documentsearch.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.documentsearch.form.OldDocumentSearchForm;
import com.wipro.igrs.documentsearch.sql.CommonSQL;
import com.wipro.igrs.documentsearch.sql.OldDocumentSearchSQL;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */
public class OldDocumentSearchDAO {
	DBUtility dbUtility = null;
	Logger logger = (Logger) Logger.getLogger(OldDocumentSearchDAO.class);
	String srNameQueryAppend = " AND B.NAME_OF_SR = ?";

	/**
	 * @return
	 * @throws Exception
	 */
	public List<String> getDistrictList() throws Exception {
		List<String> districList = null;
		dbUtility = new DBUtility();
		List<List<String>> temp = null;
		districList = new ArrayList<String>();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			temp = dbUtility.executeQuery(OldDocumentSearchSQL.OLD_SEARCH_SRO_NAME_QUERY);
			for (List<String> list : temp) {
				districList.add(list.get(0).toString());
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return districList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<String> getSroNameList() throws Exception {
		List<String> sroNameList = null;
		dbUtility = new DBUtility();
		List<List<String>> temp = null;
		sroNameList = new ArrayList<String>();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			temp = dbUtility.executeQuery(OldDocumentSearchSQL.OLD_SEARCH_SRO_NAME_QUERY);
			for (List<String> list : temp) {
				sroNameList.add(list.get(0).toString());
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return sroNameList;
	}

	/**
	 * @param form
	 * @param userId
	 * @param functionId
	 * @return
	 * @throws Exception
	 */
	public String storeOldSearchATxnDetails(OldDocumentSearchForm form, String userId, String functionId,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		String docTxnId = "";
		boolean flag = false;
		String detailsInsert = null;
		IGRSCommon common = null;
		try {
			dbUtility = new DBUtility();
			detailsInsert = OldDocumentSearchSQL.OLD_DOC_SEARCH_A_DETAILS_INSERT;
			String[] detArray = new String[13];
			common = new IGRSCommon();
			docTxnId = common.getSequenceNumber("DUMMY", "OLDCOPY");
			if (docTxnId != null) {
				detArray[0] = docTxnId;
				detArray[1] = userId;
				detArray[2] = userId;
				detArray[3] = functionId;
				detArray[4] = deedRegistrationParamMap.get("district");
				detArray[5] = deedRegistrationParamMap.get("sro_name");
				detArray[6] = deedRegistrationParamMap.get("name_of_sr");
				detArray[7] = deedRegistrationParamMap.get("book_number");
				detArray[8] = deedRegistrationParamMap.get("volume_number");
				detArray[9] = deedRegistrationParamMap.get("document_number");
				detArray[10] = deedRegistrationParamMap.get("date_of_registration");
				detArray[11] = form.getIsOld();
				detArray[12] = deedRegistrationParamMap.get("registration_number");
				dbUtility.createPreparedStatement(detailsInsert);
				flag = dbUtility.executeUpdate(detArray);
			}
			if (!flag) {
				dbUtility.rollback();
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return docTxnId;
	}

	/**
	 * @param form
	 * @param deedRegistrationParamMap
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getSaleBuyerSellerDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.BUYER_SELLER_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");
				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getSalePropertyDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {

		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.SALE_PROPERTY_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getSaleKhasraDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.SALE_KHASRA_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getSaleFloorDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.SALE_FLOOR_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getSaleStampDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.SALE_STAMP_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getMasterPartyDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.MASTER_PARTY_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getMasterPropertyDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.MASTER_PROPERTY_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getMasterKhasraDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.MASTER_KHASRA_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getMasterFloorDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.MASTER_FLOOR_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");
				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getMasterStampDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.MASTER_STAMP_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getExchangePartyDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.EXCHANGE_PARTY_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getExchangePropertyDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.EXCHANGE_PROPERTY_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getExchangeKhasraDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.EXCHANGE_KHASRA_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getExchangeStampDetails(OldDocumentSearchForm form,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			dbUtility = new DBUtility();
			query = new StringBuilder(OldDocumentSearchSQL.EXCHANGE_STAMP_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append("B.".concat(entry.getKey().trim()) + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");

				}
			}
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param paymentflag
	 * @param form
	 * @param userId
	 * @param functionId
	 * @param refID
	 * @return
	 * @throws Exception
	 */
	public String storePaymentTxnDetails(String paymentflag, OldDocumentSearchForm form, String userId,
			String functionId, String refID) throws Exception {

		String TxnId = "";
		boolean flag1 = false;
		try {
			dbUtility = new DBUtility();
			String docSrchPaymentTxnInsertQuery = CommonSQL.DOC_SEARCH_PAYTXN_INSERT;
			String[] docSrchPaymentTxnInsertQueryParams = new String[6];
			IGRSCommon common = new IGRSCommon();
			TxnId = common.getTransactionNumber("DUMMY");
			docSrchPaymentTxnInsertQueryParams[0] = TxnId;
			docSrchPaymentTxnInsertQueryParams[1] = refID;
			docSrchPaymentTxnInsertQueryParams[2] = paymentflag;
			docSrchPaymentTxnInsertQueryParams[3] = functionId;
			docSrchPaymentTxnInsertQueryParams[4] = userId;
			docSrchPaymentTxnInsertQueryParams[5] = form.getTotalFee() + "";
			dbUtility.createPreparedStatement(docSrchPaymentTxnInsertQuery);
			flag1 = dbUtility.executeUpdate(docSrchPaymentTxnInsertQueryParams);
			if (!flag1) {
				dbUtility.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return TxnId;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getSaleDetailsA(OldDocumentSearchForm form, Map<String, String> params) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			query = new StringBuilder(OldDocumentSearchSQL.SALE_DEED_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append(entry.getKey() + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");
				}
			}
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */

	public List<List<String>> getExchangeDetailsA(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			query = new StringBuilder(OldDocumentSearchSQL.EXCHANGE_DEED_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append(entry.getKey() + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");
				}
			}
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */

	public List<List<String>> getMasterDetailsA(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			query = new StringBuilder(OldDocumentSearchSQL.MASTER_DEED_DETAILS);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				query.append(entry.getKey() + "= ?");
				list.add(entry.getValue().trim());
				if (entrySetIterator.hasNext()) {
					query.append(" AND ");
				}
			}
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getOldSrchAResult(List<String> params) throws Exception {
		List<List<String>> records = null;
		String query = null;

		try {
			dbUtility = new DBUtility();
			query = OldDocumentSearchSQL.GET_OLD_SEARCH_RECORD_LIST_A;
			dbUtility.createPreparedStatement(query);
			records = dbUtility.executeQuery(params);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getOldSrchBResult(List<String> params) throws Exception {
		List<List<String>> records = null;
		String query = null;

		try {
			dbUtility = new DBUtility();
			query = OldDocumentSearchSQL.GET_OLD_SEARCH_RECORD_LIST_B;
			dbUtility.createPreparedStatement(query);
			records = dbUtility.executeQuery(params);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getOldSearchATxnDetails(List<String> params) throws Exception {
		List<List<String>> records = null;
		String query = null;
		try {
			dbUtility = new DBUtility();
			query = OldDocumentSearchSQL.OLD_DOC_SEARCH_A_DETAILS_SELECT;
			dbUtility.createPreparedStatement(query);
			records = dbUtility.executeQuery(params);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;
	}

	/**
	 * @param dateInString
	 * @return
	 */
	public String dateConverter(String dateInString) {
		String formattedDate = "";
		dateInString = dateInString.substring(0, dateInString.indexOf(' '));
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			Date date = formatter.parse(dateInString);
			formattedDate = formatter.format(date);
		} catch (ParseException e) {
			logger.error(e);
		}
		return formattedDate;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getSaleDetailsB(OldDocumentSearchForm form, Map<String, String> params) throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		try {
			query = new StringBuilder(OldDocumentSearchSQL.SALE_DEED_DETAILS_B);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			String[] dateParamList = new String[2];
			String[] dateString = new String[2];
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				if (entry.getKey() == "DISTRICT") {
					query.append(" A.DISTRICT = ? ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "SRO_NAME") {
					query.append(" A.SRO_NAME = ? ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "VOLUME_NUMBER") {
					query.append("A.VOLUME_NUMBER= ? ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "DATE_OF_REGISTRATION") {
					query.append("A.DATE_OF_REGISTRATION= ? ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "DOCUMENT_NUMBER") {
					query.append("A.DOCUMENT_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "BOOK_NUMBER") {
					query.append("A.BOOK_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "FROM_DATE") {
					dateString[0] = "AND A.DATE_OF_REGISTRATION BETWEEN to_date(?,'DD/MM/YYYY')";
					dateParamList[0] = entry.getValue().trim();
				}
				if (entry.getKey() == "TO_DATE") {
					dateString[1] = " AND to_date(?,'DD/MM/YYYY')";
					dateParamList[1] = entry.getValue().trim();
				}
				if (entry.getKey() == "ORGANIGATION") {
					query.append("B.ORGANIGATION= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "PARTY_NAME") {
					query.append("B.NAME= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "PROPERTY_WARD_NUMBER") {
					query.append("D.PROPERTY_WARD_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "KHASRA_NUMBER") {
					query.append("C.KHASRA_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entrySetIterator.hasNext() && !(entry.getKey() == "TO_DATE" || entry.getKey() == "FROM_DATE")) {
					query.append(" AND ");
				}
			}
			if (!(dateString[0] == null && dateString[1] == null)) {
				query.append(dateString[0] + dateString[1]);
				list.add(dateParamList[0]);
				list.add(dateParamList[1]);
			}
			query.append(" ORDER BY A.DATE_OF_REGISTRATION ");
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;

	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getExchangeDetailsB(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		String[] dateParamList = new String[2];
		String[] dateString = new String[2];
		try {
			query = new StringBuilder(OldDocumentSearchSQL.EXCHANGE_DEED_DETAILS_B);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				if (entry.getKey() == "DISTRICT") {
					query.append(" A.DISTRICT = ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "SRO_NAME") {
					query.append(" A.SRO_NAME = ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "VOLUME_NUMBER") {
					query.append("A.VOLUME_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "DATE_OF_REGISTRATION") {
					query.append("A.DATE_OF_REGISTRATION= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "DOCUMENT_NUMBER") {
					query.append("A.DOCUMENT_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "BOOK_NUMBER") {
					query.append("A.BOOK_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "FROM_DATE") {
					dateString[0] = "AND A.DATE_OF_REGISTRATION BETWEEN to_date(?,'DD/MM/YYYY')";
					dateParamList[0] = entry.getValue().trim();
				}
				if (entry.getKey() == "TO_DATE") {
					dateString[1] = " AND to_date(?,'DD/MM/YYYY')";
					dateParamList[1] = entry.getValue().trim();
				}
				if (entry.getKey() == "ORGANIGATION") {
					query.append("B.PARTY_ORGANIGATION_NAME= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "PARTY_NAME") {
					query.append("B.PARTY_NAME= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "PROPERTY_WARD_NUMBER") {
					query.append("D.PROPERTY_WARD_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "KHASRA_NUMBER") {
					query.append("C.KHASRA_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entrySetIterator.hasNext() && !(entry.getKey() == "TO_DATE" || entry.getKey() == "FROM_DATE")) {
					query.append(" AND ");
				}
			}
			if (!(dateString[0] == null && dateString[1] == null)) {
				query.append(dateString[0] + dateString[1]);
				list.add(dateParamList[0]);
				list.add(dateParamList[1]);
			}
			query.append(" ORDER BY A.DATE_OF_REGISTRATION ");
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;

	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getMasterDetailsB(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> records = null;
		StringBuilder query = null;
		List<String> list = null;
		String[] dateParamList = new String[2];
		String[] dateString = new String[2];
		try {
			query = new StringBuilder(OldDocumentSearchSQL.MASTER_DEED_DETAILS_B);
			query.append(" WHERE ");
			list = new ArrayList<String>();
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				if (entry.getKey() == "DISTRICT") {
					query.append(" A.DISTRICT = ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "SRO_NAME") {
					query.append(" A.SRO_NAME = ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "VOLUME_NUMBER") {
					query.append("A.VOLUME_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "DATE_OF_REGISTRATION") {
					query.append("A.DATE_OF_REGISTRATION= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "DOCUMENT_NUMBER") {
					query.append("A.DOCUMENT_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "BOOK_NUMBER") {
					query.append("A.BOOK_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "FROM_DATE") {
					dateString[0] = "AND A.DATE_OF_REGISTRATION BETWEEN  to_date(?,'DD/MM/YYYY')";
					dateParamList[0] = entry.getValue().trim();
				}
				if (entry.getKey() == "TO_DATE") {
					dateString[1] = " AND to_date(?,'DD/MM/YYYY')";
					dateParamList[1] = entry.getValue().trim();
				}
				if (entry.getKey() == "ORGANIGATION") {
					query.append("B.ORGANIGATION= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "PARTY_NAME") {
					query.append("B.NAME= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "PROPERTY_WARD_NUMBER") {
					query.append("D.PROPERTY_WARD_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entry.getKey() == "KHASRA_NUMBER") {
					query.append("C.KHASRA_NUMBER= ?  ");
					list.add(entry.getValue().trim());
				}
				if (entrySetIterator.hasNext() && !(entry.getKey() == "TO_DATE" || entry.getKey() == "FROM_DATE")) {
					query.append(" AND ");
				}
			}
			if (!(dateString[0] == null && dateString[1] == null)) {
				query.append(dateString[0] + dateString[1]);
				list.add(dateParamList[0]);
				list.add(dateParamList[1]);
			}
			query.append(" ORDER BY A.DATE_OF_REGISTRATION ");
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(list);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return records;

	}

	/**
	 * @param form
	 * @param paramMap
	 * @param transactionId
	 * @return
	 * @throws Exception
	 */
	public boolean updateOldSearchATxnDetails(OldDocumentSearchForm form, Map<String, String> paramMap,
			String transactionId) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder query = null;
		List<List<String>> records = null;
		try {
			query = new StringBuilder(OldDocumentSearchSQL.OLD_DOC_OFFICE_SEARCH_DETAILS_UPDATE);
			Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				if (entry.getKey() == "DOC_PATH") {
					query.append(" DOC_PATH = ?  ");
					params.add(entry.getValue());
				}
				if (entry.getKey() == "DOC_NAME") {
					query.append(" DOC_NAME = ?  ");
					params.add(entry.getValue());
				}
				if (entry.getKey() == "OFFICIAL_SEARCH_PURPOSE") {
					query.append(" OFFICIAL_SEARCH_PURPOSE = ?  ");
					params.add(entry.getValue());
				}
				if (entrySetIterator.hasNext()) {
					query.append(" , ");
				}
			}
			if (!(transactionId == "" || transactionId == null)) {
				{
					if (!(transactionId.equalsIgnoreCase(""))) {
						query.append(" WHERE DOC_SEARCH_TXN_ID=? ");
						params.add(transactionId);
					}
				}
			}
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(query.toString());
			records = dbUtility.executeQuery(params);
			if (records.isEmpty()) {
				dbUtility.rollback();
				return false;
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return true;
	}
}
