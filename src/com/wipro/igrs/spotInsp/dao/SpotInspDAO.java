package com.wipro.igrs.spotInsp.dao;
/**
 * ===========================================================================
 * File           :   SpotInspDAO.java
 * Description    :   Represents the DAO Class

 * Author         :   Pavani param
 * Created Date   :   Apr 08, 2008.

 * ===========================================================================
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.reginit.sql.RegCommonSQL;
import com.wipro.igrs.spotInsp.bd.SpotInspBD;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;
import com.wipro.igrs.spotInsp.form.SpotInspForm;
import com.wipro.igrs.spotInsp.sql.CommonSQL;
import com.wipro.igrs.util.CommonUtil;

public class SpotInspDAO {
	public boolean boo = true;

	ArrayList judList;
	ArrayList nonJudList;
	ArrayList list = null;
	boolean insert;
	String SQL;
	// DBUtility dbUtil ;
	IGRSCommon igrsCommon = null;
	private final Logger logger = Logger.getLogger(SpotInspDAO.class);
	/**
	 * Getting Country list
	 * 
	 * @return list
	 */

	public ArrayList getSubArea(String language, String areaId, String tehsilId, String urbanFlag) {
		ArrayList subAreaList = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if (language.equalsIgnoreCase("en")) {
				if ("Y".equalsIgnoreCase(urbanFlag)) {
					dbUtil.createPreparedStatement(CommonSQL.SUB_AREA_NAME_EN_UR);
					subAreaList = dbUtil.executeQuery(new String[]{areaId, tehsilId});

				} else {
					dbUtil.createPreparedStatement(CommonSQL.SUB_AREA_NAME_EN);
					subAreaList = dbUtil.executeQuery(new String[]{areaId});
				}
			} else {
				if ("Y".equalsIgnoreCase(urbanFlag)) {
					dbUtil.createPreparedStatement(CommonSQL.SUB_AREA_NAME_HI_UR);
					subAreaList = dbUtil.executeQuery(new String[]{areaId, tehsilId});
				} else {
					dbUtil.createPreparedStatement(CommonSQL.SUB_AREA_NAME_HI);
					subAreaList = dbUtil.executeQuery(new String[]{areaId});

				}
			}

			return subAreaList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {

				logger.debug(e.getMessage(), e);
			}
		}
	}

	public ArrayList getColonyName(String language, String wardPatwariId) {
		ArrayList l2NameList = null;
		DBUtility dbUtil = null;
		System.out.println("Ward Id " + wardPatwariId);
		try {
			dbUtil = new DBUtility();
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(CommonSQL.COLONY_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(CommonSQL.COLONY_NAME_HI);
			}
			l2NameList = dbUtil.executeQuery(new String[]{wardPatwariId.split("~")[1]});
			return l2NameList;

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList getCountry() {

		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getCountry();
			logger.info("SpotInspDAO----getCountry " + list);
		} catch (Exception e) {
			logger.error("Exception in getCountry():" + e);
		}
		return list;
	}

	public String getCurrentDistrict(String officeId) {
		String value = "";
		DBUtility dbUtil = null;
		try {

			String param[] = new String[1];
			param[0] = officeId;
			String SQL = " SELECT DISTINCT DISTRICT_ID FROM IGRS_office_MASTER	WHERE   OFFICE_ID='" + officeId + "'";
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			value = dbUtil.executeQry(SQL);
		} catch (Exception e) {
			logger.error("Exception in getCountry():" + e);
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

	/**
	 * Getting State list.
	 * 
	 * @param countryId
	 * @return list
	 */

	public ArrayList getWardPatwari(String language, String subAreaId, String tehsilId) {
		ArrayList wardPatwariList = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_HI);
			}
			wardPatwariList = dbUtil.executeQuery(new String[]{subAreaId, tehsilId});
			return wardPatwariList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {

				logger.debug(e.getMessage(), e);
			}
		}
	}

	public ArrayList getState(String countryId) {

		try {

			igrsCommon = new IGRSCommon();
			if (countryId == null) {
				countryId = "INDIA";
			}
			list = igrsCommon.getState(countryId);
			logger.info("SpotInspDAO----getState  " + list);
		} catch (Exception e) {
			logger.error("Exception in getState():" + e);
		}
		return list;
	}

	/**
	 * Getting District list.
	 * 
	 * @return list
	 */

	public ArrayList getDistrict(String stateId) {
		try {
			if (stateId == null) {
				stateId = "1";
			}
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getDistrict(stateId);
			logger.info("SpotInspDAO----getState  " + list);
		} catch (Exception e) {
			logger.error("Exception in getState():" + e);
		}
		return list;
	}

	public ArrayList getDistrictZone(String zoneId, String language) {

		String a = null;
		ArrayList data = null;
		DBUtility dbUtil = null;
		try {
			String SQL = "";
			String param[] = new String[1];
			param[0] = zoneId;
			dbUtil = new DBUtility();
			if (language.equalsIgnoreCase("en"))
				SQL = CommonSQL.SPOT_GET_DISTRICT_ZONE_LIST;
			else
				SQL = CommonSQL.SPOT_GET_DISTRICT_ZONE_LIST_HINDI;
			logger.debug("before getting Zone details ");
			dbUtil.createPreparedStatement(SQL);
			logger.debug("SQL:" + SQL);
			data = dbUtil.executeQuery(param);

			logger.debug("value of  Zone_Id) :- " + data.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception in getDistrictZoneDetails() :- " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return data;

	}

	/**
	 * Getting District list.
	 * 
	 * @return list
	 */

	public ArrayList getCriteriaDocument(SpotInspForm spForm) throws Exception {

		ArrayList list = new ArrayList();
		String param[] = new String[5];

		if (spForm.getViewType().equals("State")) {
			SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT;
			param[0] = "1";
			param[1] = spForm.getRangeId();
			param[2] = spForm.getRangeId();
			if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
				param[3] = spForm.getDurFrom();
				param[4] = spForm.getDurTo();

			}
		} else if (spForm.getViewType().equals("Zone")) {
			SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENT;
			param[0] = spForm.getZoneId();
			param[1] = spForm.getRangeId();
			param[2] = spForm.getRangeId();
			if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
				param[3] = spForm.getDurFrom();
				param[4] = spForm.getDurTo();

			}
		} else {
			SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENT;
			param[0] = spForm.getDistrictId();
			param[1] = spForm.getRangeId();
			param[2] = spForm.getRangeId();
			if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
				param[3] = spForm.getDurFrom();
				param[4] = spForm.getDurTo();

			}
		}
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public ArrayList getHyperLinkData(SpotInspForm spForm) {
		ArrayList data = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String sql = "";
			String param[] = new String[1];
			String language = spForm.getLanguage();
			if ("Zone".equalsIgnoreCase(spForm.getViewType())) {
				logger.debug("Entered Zone List Filter");
				if (language.equalsIgnoreCase("en"))
					sql = CommonSQL.IGRS_SPOT_FINAL_QUERY_ZONE;
				else
					sql = CommonSQL.IGRS_SPOT_FINAL_QUERY_ZONE_HINDI;
				param[0] = spForm.getZoneId();

			} else if ("District".equalsIgnoreCase(spForm.getViewType())) {
				logger.debug("Entered District List Filter");
				if (language.equalsIgnoreCase("en"))
					sql = CommonSQL.IGRS_SPOT_FINAL_QUERY_DISTRICT;
				else
					sql = CommonSQL.IGRS_SPOT_FINAL_QUERY_DISTRICT_HINDI;
				param[0] = spForm.getDistrictId();
			}
			dbUtil.createPreparedStatement(sql);
			data = dbUtil.executeQuery(param);

		} catch (Exception e) {

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return data;

	}

	@SuppressWarnings("unchecked")
	public ArrayList getTempData(SpotInspForm spForm) throws Exception {

		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			try {
				emptyTemp();
			} catch (Exception e) {
				logger.debug("Error : " + e);
			}

			if (spForm.getQuestion().equalsIgnoreCase("Y")) {
				logger.debug("Entered Refined Criteria Yes");
				if (spForm.getViewType().equals("State")) { // For State_Yes
					if (spForm.getPropertyL2Id() == null || spForm.getPropertyL2Id().equalsIgnoreCase("")) {

						SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT_YES;

						StringBuilder squery = new StringBuilder(SQL);
						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						} // End of for loopp

						squery.append(CommonSQL.REST_OF_QUERY_FINAL);

						int sizeOfQuery = 12 + values.length;

						String param[] = new String[12];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getTehsilId();
						param[3] = spForm.getAreaTypeId();
						param[4] = spForm.getWardId();
						param[5] = spForm.getMohallaId();
						param[6] = spForm.getZoneId();
						param[7] = spForm.getDistrictId();
						param[8] = spForm.getRangeId();
						param[9] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[10] = spForm.getDurFrom();
							param[11] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);

						dbUtil = new DBUtility();
						SQL = squery.toString();

						dbUtil.createPreparedStatement(SQL);
						logger.debug("*********" + SQL);
						list = dbUtil.executeQuery(paramfinal);
						logger.debug("size of Arraylist" + list.size());

					}// End Of if for state

					else {

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT_FLOOR_YES;
						} else
							SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT_L2_YES;

						StringBuilder squery = new StringBuilder(SQL);
						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						} // End of for loopp

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							squery.append(CommonSQL.REST_OF_QUERY_FLOOR_FINAL);
						} else {
							squery.append(CommonSQL.REST_OF_QUERY_FINAL);
						}

						int sizeOfQuery = 13 + values.length;

						String param[] = new String[13];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getPropertyL2Id();
						param[3] = spForm.getTehsilId();
						param[4] = spForm.getAreaTypeId();
						param[5] = spForm.getWardId();
						param[6] = spForm.getMohallaId();
						param[7] = spForm.getZoneId();
						param[8] = spForm.getDistrictId();
						param[9] = spForm.getRangeId();
						param[10] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[11] = spForm.getDurFrom();
							param[12] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);

						SQL = squery.toString();

						dbUtil.createPreparedStatement(SQL);
						logger.debug("*********" + SQL);
						list = dbUtil.executeQuery(paramfinal);
						logger.debug("size of Arraylist" + list.size());

					}

				} else if (spForm.getViewType().equals("Zone")) {

					if (spForm.getPropertyL2Id() != null & !spForm.getPropertyL2Id().equalsIgnoreCase("")) {
						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT_FLOOR_YES;
						} else
							SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENT_L2_YES;

						StringBuilder squery = new StringBuilder(SQL);

						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						}

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							squery.append(CommonSQL.REST_OF_QUERY_FLOOR_FINAL);
						} else
							squery.append(CommonSQL.REST_OF_QUERY_FINAL);

						int sizeOfQuery = 13 + values.length;

						String param[] = new String[13];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getPropertyL2Id();
						param[3] = spForm.getTehsilId();
						param[4] = spForm.getAreaTypeId();
						param[5] = spForm.getWardId();
						param[6] = spForm.getMohallaId();
						param[7] = spForm.getZoneId();
						param[8] = spForm.getDistrictId();
						param[9] = spForm.getRangeId();
						param[10] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[11] = spForm.getDurFrom();
							param[12] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);

						SQL = squery.toString();
						dbUtil.createPreparedStatement(SQL);
						list = dbUtil.executeQuery(paramfinal);

					} else {

						SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENT_YES;

						StringBuilder squery = new StringBuilder(SQL);

						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						}

						squery.append(CommonSQL.REST_OF_QUERY_FINAL);

						int sizeOfQuery = 12 + values.length;

						String param[] = new String[12];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getTehsilId();
						param[3] = spForm.getAreaTypeId();
						param[4] = spForm.getWardId();
						param[5] = spForm.getMohallaId();
						param[6] = spForm.getZoneId();
						param[7] = spForm.getDistrictId();
						param[8] = spForm.getRangeId();
						param[9] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[10] = spForm.getDurFrom();
							param[11] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);

						SQL = squery.toString();
						dbUtil.createPreparedStatement(SQL);
						list = dbUtil.executeQuery(paramfinal);

					}
				}

				else {
					if (spForm.getPropertyL2Id() != null & !spForm.getPropertyL2Id().equalsIgnoreCase(""))// L2
					{

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT_FLOOR_YES;
						} else
							SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENT_L2_YES;

						StringBuilder squery = new StringBuilder(SQL);

						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						}

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							squery.append(CommonSQL.REST_OF_QUERY_FLOOR_FINAL);
						} else
							squery.append(CommonSQL.REST_OF_QUERY_FINAL);

						int sizeOfQuery = 13 + values.length;

						String param[] = new String[13];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getPropertyL2Id();
						param[3] = spForm.getTehsilId();
						param[4] = spForm.getAreaTypeId();
						param[5] = spForm.getWardId();
						param[6] = spForm.getMohallaId();
						param[7] = spForm.getZoneId();
						param[8] = spForm.getDistrictId();
						param[9] = spForm.getRangeId();
						param[10] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[11] = spForm.getDurFrom();
							param[12] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);
						SQL = squery.toString();
						logger.debug("^^^^^^^^^^^^QUERY" + SQL);
						dbUtil.createPreparedStatement(SQL);
						list = dbUtil.executeQuery(paramfinal);

					} else // Optional Level
					{
						SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENT_YES;

						StringBuilder squery = new StringBuilder(SQL);

						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						}
						squery.append(CommonSQL.REST_OF_QUERY_FINAL);

						int sizeOfQuery = 12 + values.length;

						String param[] = new String[12];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getTehsilId();
						param[3] = spForm.getAreaTypeId();
						param[4] = spForm.getWardId();
						param[5] = spForm.getMohallaId();
						param[6] = spForm.getZoneId();
						param[7] = spForm.getDistrictId();
						param[8] = spForm.getRangeId();
						param[9] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[10] = spForm.getDurFrom();
							param[11] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);
						SQL = squery.toString();
						dbUtil = new DBUtility();
						logger.debug("^^^^^^^^^^^^QUERY" + SQL);
						dbUtil.createPreparedStatement(SQL);
						list = dbUtil.executeQuery(paramfinal);

					}

				}
			} else {
				if (spForm.getViewType().equals("State")) { // For State_NO
					if (spForm.getPropertyL2Id() == null || spForm.getPropertyL2Id().equalsIgnoreCase("")) {

						SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT_NO;

						StringBuilder squery = new StringBuilder(SQL);
						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						} // End of for loopp

						squery.append(CommonSQL.REST_OF_QUERY_FINAL);

						int sizeOfQuery = 6 + values.length;

						String param[] = new String[6];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getRangeId();
						param[3] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[4] = spForm.getDurFrom();
							param[5] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);

						SQL = squery.toString();

						dbUtil.createPreparedStatement(SQL);
						logger.debug("*********" + SQL);
						list = dbUtil.executeQuery(paramfinal);
						logger.debug("size of Arraylist" + list.size());
						System.out.println();

					}// End Of if for state

					else {

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {

							SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT_L2_FLOOR_NO;
						} else {
							SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT_L2_NO;
						}

						StringBuilder squery = new StringBuilder(SQL);
						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						} // End of for loopp

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							squery.append(CommonSQL.REST_OF_QUERY_FLOOR_FINAL);
						} else {
							squery.append(CommonSQL.REST_OF_QUERY_FINAL);
						}

						int sizeOfQuery = 7 + values.length;

						String param[] = new String[7];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getPropertyL2Id();
						param[3] = spForm.getRangeId();
						param[4] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[5] = spForm.getDurFrom();
							param[6] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);

						SQL = squery.toString();

						dbUtil.createPreparedStatement(SQL);
						logger.debug("*********" + SQL);
						list = dbUtil.executeQuery(paramfinal);
						logger.debug("size of Arraylist" + list.size());

					}

				} else if (spForm.getViewType().equals("Zone")) {

					if (spForm.getPropertyL2Id() != null & !spForm.getPropertyL2Id().equalsIgnoreCase("")) {
						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {

							SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENT_L2_FLOOR_NO;
						} else {
							SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENT_L2_NO;
						}

						StringBuilder squery = new StringBuilder(SQL);

						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						}

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							squery.append(CommonSQL.REST_OF_QUERY_FLOOR_FINAL);
						} else {
							squery.append(CommonSQL.REST_OF_QUERY_FINAL);
						}
						int sizeOfQuery = 8 + values.length;
						String param[] = new String[8];
						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getPropertyL2Id();

						param[3] = spForm.getZoneId();
						param[4] = spForm.getRangeId();
						param[5] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[6] = spForm.getDurFrom();
							param[7] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);

						SQL = squery.toString();
						dbUtil.createPreparedStatement(SQL);
						list = dbUtil.executeQuery(paramfinal);

					} else {

						SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENT_NO;

						StringBuilder squery = new StringBuilder(SQL);

						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						}

						squery.append(CommonSQL.REST_OF_QUERY_FINAL);

						int sizeOfQuery = 7 + values.length;
						String param[] = new String[7];
						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();

						param[2] = spForm.getZoneId();
						param[3] = spForm.getRangeId();
						param[4] = spForm.getRangeId();
						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[5] = spForm.getDurFrom();
							param[6] = spForm.getDurTo();

						}
						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);

						SQL = squery.toString();
						dbUtil.createPreparedStatement(SQL);
						list = dbUtil.executeQuery(paramfinal);

					}
				}

				else {
					if (spForm.getPropertyL2Id() != null & !spForm.getPropertyL2Id().equalsIgnoreCase(""))// L2
					{

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENT_L2_FLOOR_NO;
						} else
							SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENT_L2_NO;

						StringBuilder squery = new StringBuilder(SQL);

						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						}

						if ((spForm.getPropertyId() != null && spForm.getPropertyL2Id() != null) && (spForm.getPropertyId().equalsIgnoreCase("4") || spForm.getPropertyId().equalsIgnoreCase("5") || spForm.getPropertyId().equalsIgnoreCase("10"))) {
							squery.append(CommonSQL.REST_OF_QUERY_FLOOR_FINAL);
						} else

							squery.append(CommonSQL.REST_OF_QUERY_FINAL);
						int sizeOfQuery = 9 + values.length;

						String param[] = new String[9];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getPropertyL2Id();
						param[3] = spForm.getZoneId();
						param[4] = spForm.getDistrictId();
						param[5] = spForm.getRangeId();
						param[6] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[7] = spForm.getDurFrom();
							param[8] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);
						SQL = squery.toString();
						logger.debug("^^^^^^^^^^^^QUERY" + SQL);
						dbUtil.createPreparedStatement(SQL);
						list = dbUtil.executeQuery(paramfinal);

					} else // Optional Level
					{
						SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENT_NO;

						StringBuilder squery = new StringBuilder(SQL);

						String[] values = spForm.getSelectedClauses();

						for (int i = 0; i < values.length; i++) {
							if (i != values.length - 1)
								squery.append("?,");
							else
								squery.append("?");

						}
						squery.append(CommonSQL.REST_OF_QUERY_FINAL);

						int sizeOfQuery = 8 + values.length;

						String param[] = new String[8];

						param[0] = spForm.getDeedId();
						param[1] = spForm.getPropertyId();
						param[2] = spForm.getZoneId();
						param[3] = spForm.getDistrictId();
						param[4] = spForm.getRangeId();
						param[5] = spForm.getRangeId();

						if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
							param[6] = spForm.getDurFrom();
							param[7] = spForm.getDurTo();

						}

						String paramfinal[] = new String[sizeOfQuery];
						paramfinal = (String[]) ArrayUtils.addAll(param, values);
						SQL = squery.toString();
						dbUtil = new DBUtility();
						logger.debug("^^^^^^^^^^^^QUERY" + SQL);
						dbUtil.createPreparedStatement(SQL);
						list = dbUtil.executeQuery(paramfinal);

					}

				}

			}

			if (list.isEmpty() || list.size() == 0 || list.size() < 0) {

				return null;

			}

			if (spForm.getQuestion().equalsIgnoreCase("Y")) {
				String userData[] = new String[5];
				userData[0] = getPropertyId(spForm.getPropertyId());
				userData[1] = spForm.getTehsilId();
				userData[2] = spForm.getAreaTypeId();
				userData[3] = spForm.getMohallaId();
				userData[4] = spForm.getWardId();
				if (list != null && list.size() > 0) {
					String parameterTemp = "";
					String parameterFinal = "";
					ArrayList temp = null;
					boolean check = false;
					for (int i = 0; i < list.size(); i++) {
						temp = (ArrayList) list.get(i);
						parameterTemp = temp.toString();
						if (parameterTemp != null) {
							parameterFinal = parameterTemp.substring(1, parameterTemp.length() - 1);
						} else {
							parameterFinal = "";
						}
						String add[] = parameterFinal.split(",");
						String finals[] = (String[]) ArrayUtils.addAll(add, userData);
						check = addTempDetails(finals);
						if (!check) {
							boo = false;
							break;
						}

					}

				}

			} else if (spForm.getQuestion().equalsIgnoreCase("N")) {
				String userData[] = new String[1];
				userData[0] = getPropertyId(spForm.getPropertyId());

				if (list != null && list.size() != 0) {
					String parameterTemp = "";
					String parameterFinal = "";
					ArrayList temp = null;
					boolean check = false;
					for (int i = 0; i < list.size(); i++) {
						temp = (ArrayList) list.get(i);
						parameterTemp = temp.toString();
						if (parameterTemp != null) {
							parameterFinal = parameterTemp.substring(1, parameterTemp.length() - 1);
						} else {
							parameterFinal = "";
						}

						String add[] = parameterFinal.split(",");
						String finals[] = (String[]) ArrayUtils.addAll(add, userData);
						check = addTempDetailsNo(finals);
						if (!check) {
							boo = false;
							break;
						}

					}

				}

			}
			if (spForm.getQuestion().equalsIgnoreCase("Y")) {
				if (boo) {
					HashMap category = new HashMap();
					String districts[] = getDistricts();

					for (int i = 0; i < districts.length; i++) {
						String district[] = districts[i].split("~");
						String number = district[0];
						ArrayList list = getDistrictInfoListsYes(number, spForm.getLanguage());
						category.put(districts[i], list);

					}
					spForm.setFinalValues(category); // setting hasmap values to
					// form
					System.out.println("Hashmap Size is : " + category.size());

				}

				dbUtil.createStatement();
				ArrayList finallist = null;
				if (spForm.getLanguage().equalsIgnoreCase("en"))
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY);
				else
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY_HINDI);
				// emptyTemp();

				return finallist;
			} else if (spForm.getQuestion().equalsIgnoreCase("N")) {
				if (boo) {
					HashMap category = new HashMap();
					String districts[] = getDistricts();

					for (int i = 0; i < districts.length; i++) {
						String district[] = districts[i].split("~");
						String number = district[0];
						ArrayList list = getDistrictInfoListsNo(number, spForm.getLanguage());
						category.put(districts[i], list);

					}
					spForm.setFinalValues(category); // setting hasmap values to
					// form
					System.out.println("Hashmap Size is : " + category.size());

				}

				dbUtil.createStatement();
				ArrayList finallist = null;
				if (spForm.getLanguage().equalsIgnoreCase("en"))
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY);
				else
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY_HINDI);

				// emptyTemp();
				return finallist;
			}

		} catch (Exception e) {

		} finally {

		}
		return null;
	}
	// Edited By Mohit Soni

	private void emptyTemp() {
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();

			dbUtil.executeUpdate(CommonSQL.IGRS_SPOT_TEMP_EMPTY);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String getPropertyId(String id) {
		String param[] = new String[1];
		String propertyId = "";
		param[0] = String.valueOf(id);
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_TEMP_GET_PROPERTY);

			propertyId = dbUtil.executeQry(param);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return propertyId;

	}

	public ArrayList getDistrictInfoListsYes(String id, String languge) {
		logger.debug("Enetered getDistrictInfoListsYes");
		String sql = "";
		if (languge.equalsIgnoreCase("en"))
			sql = CommonSQL.IGRS_GET_ALL_INFO;
		else
			sql = CommonSQL.IGRS_GET_ALL_INFO_HINDI;
		String param[] = new String[1];
		param[0] = id;

		ArrayList finalData = new ArrayList();
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			ArrayList data1 = dbUtil.executeQuery(param);

			for (int i = 0; i < data1.size(); i++) {
				ArrayList data = (ArrayList) data1.get(i);
				SpotInspDTO dto = new SpotInspDTO();
				if (data.get(1) != null && !data.get(1).toString().equalsIgnoreCase(""))
					dto.setDistrict((String) data.get(1));

				else {
					if (languge.equalsIgnoreCase("en"))
						dto.setDistrict("ALL");
					else
						dto.setDistrict("सभी");
				}

				dto.setDistrictId((String) data.get(0));

				dto.setZoneId((String) data.get(2));

				if (data.get(3) != null && !data.get(3).toString().equalsIgnoreCase(""))
					dto.setZoneName((String) data.get(3));
				else {
					if (languge.equalsIgnoreCase("en"))
						dto.setZoneName("ALL");
					else
						dto.setZoneName("सभी");
				}

				dto.setPropertyId((String) data.get(4));
				dto.setPropertyname((String) data.get(5));

				dto.setTehsilId((String) data.get(6));

				if (data.get(7) != null && !data.get(7).toString().equalsIgnoreCase(""))
					dto.setTehsilName((String) data.get(7));
				else {
					if (languge.equalsIgnoreCase("en"))
						dto.setTehsilName("ALL");
					else
						dto.setTehsilName("सभी");
				}

				dto.setWardPatwariId((String) data.get(8));

				if (data.get(9) != null && !data.get(9).toString().equalsIgnoreCase(""))
					dto.setVardPatwariName((String) data.get(9));
				else {
					if (languge.equalsIgnoreCase("en"))
						dto.setVardPatwariName("ALL");
					else
						dto.setVardPatwariName("सभी");
				}

				dto.setAreaTypeId((String) data.get(10));

				if (data.get(11) != null && !data.get(11).toString().equalsIgnoreCase(""))
					dto.setAreaTypeName((String) data.get(11));
				else {
					if (languge.equalsIgnoreCase("en"))
						dto.setAreaTypeName("ALL");
					else
						dto.setAreaTypeName("सभी");
				}

				dto.setMohallaId((String) data.get(12));

				if (data.get(13) != null && !data.get(13).toString().equalsIgnoreCase(""))
					dto.setMohallaName((String) data.get(13));
				else {
					if (languge.equalsIgnoreCase("en"))
						dto.setMohallaName("ALL");
					else
						dto.setMohallaName("सभी");
				}

				dto.setSubAreaId((String) data.get(14));

				if (data.get(15) != null && !data.get(15).toString().equalsIgnoreCase(""))
					dto.setSubAreaName((String) data.get(15));
				else {
					if (languge.equalsIgnoreCase("en"))
						dto.setSubAreaName("ALL");
					else
						dto.setSubAreaName("सभी");
				}

				dto.setRegIdcompletion((String) data.get(16));
				dto.setRegistrationOfficeId(data.get(17).toString().trim());//
				dto.setRegId(data.get(18).toString().trim());//
				dto.setSrName(data.get(19).toString().trim());//
				dto.setDrName(data.get(20).toString().trim());//
				dto.setMarketValue((String) data.get(21));
				dto.setStampDuty((String) data.get(22));
				dto.setRegFee((String) data.get(23));
				dto.setDigName((String) data.get(24));
				dto.setDocCompletionSR((String)data.get(25));
				finalData.add(dto);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return finalData;

	}

	public ArrayList getDistrictInfoListsNo(String id, String language) {
		logger.debug("Enetered getDistrictInfoLists");
		String sql = "";
		if (language.equalsIgnoreCase("en"))
			sql = CommonSQL.IGRS_GET_ALL_INFO_NO;
		else
			sql = CommonSQL.IGRS_GET_ALL_INFO_NO_HINDI;
		String param[] = new String[1];
		param[0] = id;
		ArrayList finalData = new ArrayList();
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			ArrayList data1 = dbUtil.executeQuery(param);
			logger.debug("Size of List is :" + data1.size());
			for (int i = 0; i < data1.size(); i++) {
				ArrayList data = (ArrayList) data1.get(i);
				SpotInspDTO dto = new SpotInspDTO();
				dto.setDistrictId((String) data.get(0));
				dto.setDistrict((String) data.get(1));
				dto.setZoneId((String) data.get(2));
				dto.setZoneName((String) data.get(3));
				dto.setPropertyId((String) data.get(4));
				dto.setPropertyname((String) data.get(5));
				dto.setRegIdcompletion((String) data.get(6));
				dto.setRegistrationOfficeId(data.get(7).toString().trim());
				dto.setRegId(data.get(8).toString().trim());
				dto.setSrName(data.get(9).toString().trim());
				dto.setDrName(data.get(10).toString().trim());
				dto.setMarketValue((String) data.get(11));
				dto.setStampDuty((String) data.get(12));
				dto.setRegFee((String) data.get(13));
				dto.setDigName((String) data.get(14));
				dto.setDocCompletionSR((String)data.get(15));
				finalData.add(dto);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return finalData;

	}

	public String[] getDistricts() {
		boolean check = false;

		String query = CommonSQL.IGRS_GET_TEMP_DISTRICTS;
		String a = "";
		String[] valuefinal = null;
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createStatement();
			ArrayList values = dbUtil.executeQuery(query);

			for (int i = 0; i < values.size(); i++) {
				ArrayList v = (ArrayList) values.get(i);

				if (i != values.size() - 1) {
					a = a + v.get(0) + "~" + v.get(1) + ",";
				} else {
					a = a + v.get(0) + "~" + v.get(1);
				}

			}
			valuefinal = a.split(",");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		logger.debug(valuefinal.toString());
		return valuefinal;
	}

	public boolean addTempDetails(String param[]) {
		DBUtility dbUtil = null;
		boolean check = false;

		String query = CommonSQL.IGRS_SPOT_ADD_TEMP;
		try {

			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(query);
			check = dbUtil.executeUpdate(param);

			if (check) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				dbUtil.rollback();
			} catch (Exception ex) {

			}
			e.printStackTrace();

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return check;
	}

	public boolean addTempDetailsNo(String param[]) {
		boolean check = false;

		String query = CommonSQL.IGRS_SPOT_ADD_TEMP_NO;
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(query);
			check = dbUtil.executeUpdate(param);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return check;
	}

	public ArrayList getCriteriaDocument1(SpotInspForm spForm) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		if (spForm.getQuestion().equalsIgnoreCase("N")) {

			if (spForm.getViewType().equals("State")) {
				if (spForm.getPropertyL2Id() == null || spForm.getPropertyL2Id().equalsIgnoreCase("")) {

					SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT1;

					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 7 + values.length;

					String param[] = new String[7];

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();

					param[2] = "1";
					param[3] = spForm.getRangeId();
					param[4] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[5] = spForm.getDurFrom();
						param[6] = spForm.getDurTo();

					}

					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);

					dbUtil = new DBUtility();
					SQL = squery.toString();

					dbUtil.createPreparedStatement(SQL);
					logger.debug("*********" + SQL);
					list = dbUtil.executeQuery(paramfinal);
					logger.debug("size of Arraylist" + list.size());
				} else {

					SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENTL2;

					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 8 + values.length;

					String param[] = new String[8];
					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();
					param[2] = spForm.getPropertyL2Id();
					param[3] = "1";
					param[4] = spForm.getRangeId();
					param[5] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[6] = spForm.getDurFrom();
						param[7] = spForm.getDurTo();

					}

					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);
					dbUtil = new DBUtility();
					SQL = squery.toString();
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);
				}

			} else if (spForm.getViewType().equals("Zone")) {

				if (spForm.getPropertyL2Id() != null & !spForm.getPropertyL2Id().equalsIgnoreCase("")) {

					String param[] = new String[9];
					SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENTL2;

					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 9 + values.length;

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();
					param[2] = spForm.getPropertyL2Id();
					param[3] = spForm.getZoneId();
					param[4] = "1";
					param[5] = spForm.getRangeId();
					param[6] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[7] = spForm.getDurFrom();
						param[8] = spForm.getDurTo();

					}
					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);

					dbUtil = new DBUtility();
					SQL = squery.toString();
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);
				} else {
					String param[] = new String[8];
					SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENT1;

					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 8 + values.length;

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();

					param[2] = spForm.getZoneId();
					param[3] = "1";
					param[4] = spForm.getRangeId();
					param[5] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[6] = spForm.getDurFrom();
						param[7] = spForm.getDurTo();

					}
					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);

					dbUtil = new DBUtility();
					SQL = squery.toString();
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);

				}
			} else {
				if (spForm.getPropertyL2Id() != null & !spForm.getPropertyL2Id().equalsIgnoreCase(""))// L2
				{
					SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENTL2;
					String param[] = new String[10];
					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 10 + values.length;

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();
					param[2] = spForm.getPropertyL2Id();
					param[3] = spForm.getDistrictId();
					param[4] = spForm.getZoneId();
					param[5] = "1";
					param[6] = spForm.getRangeId();
					param[7] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[8] = spForm.getDurFrom();
						param[9] = spForm.getDurTo();

					}
					dbUtil = new DBUtility();
					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);
					SQL = squery.toString();
					logger.debug("^^^^^^^^^^^^QUERY" + SQL);
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);

				} else // Optional Level
				{
					SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENT1;
					String param[] = new String[9];
					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}
					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 9 + values.length;

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();
					param[2] = spForm.getDistrictId();
					param[3] = spForm.getZoneId();
					param[4] = "1";
					param[5] = spForm.getRangeId();
					param[6] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[7] = spForm.getDurFrom();
						param[8] = spForm.getDurTo();

					}

					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);
					SQL = squery.toString();
					dbUtil = new DBUtility();
					logger.debug("^^^^^^^^^^^^QUERY" + SQL);
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);
				}
			}
		}

		else if (spForm.getQuestion().equalsIgnoreCase("Y")) {

			if (spForm.getViewType().equals("State")) {
				if (spForm.getPropertyL2Id() == null || spForm.getPropertyL2Id().equalsIgnoreCase("")) {

					SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENT1;

					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 7 + values.length;

					String param[] = new String[7];

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();

					param[2] = "1";
					param[3] = spForm.getRangeId();
					param[4] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[5] = spForm.getDurFrom();
						param[6] = spForm.getDurTo();

					}

					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);

					dbUtil = new DBUtility();
					SQL = squery.toString();

					dbUtil.createPreparedStatement(SQL);
					logger.debug("*********" + SQL);
					list = dbUtil.executeQuery(paramfinal);
					logger.debug("size of Arraylist" + list.size());
				} else {

					SQL = CommonSQL.SPOT_CRITERIA_STATE_DOCUMENTL2;

					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 8 + values.length;

					String param[] = new String[8];
					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();
					param[2] = spForm.getPropertyL2Id();
					param[3] = "1";
					param[4] = spForm.getRangeId();
					param[5] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[6] = spForm.getDurFrom();
						param[7] = spForm.getDurTo();

					}

					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);
					dbUtil = new DBUtility();
					SQL = squery.toString();
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);
				}

			} else if (spForm.getViewType().equals("Zone")) {

				if (spForm.getPropertyL2Id() != null & !spForm.getPropertyL2Id().equalsIgnoreCase("")) {

					String param[] = new String[9];
					SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENTL2;

					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 9 + values.length;

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();
					param[2] = spForm.getPropertyL2Id();
					param[3] = spForm.getZoneId();
					param[4] = "1";
					param[5] = spForm.getRangeId();
					param[6] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[7] = spForm.getDurFrom();
						param[8] = spForm.getDurTo();

					}
					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);

					dbUtil = new DBUtility();
					SQL = squery.toString();
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);
				} else {
					String param[] = new String[8];
					SQL = CommonSQL.SPOT_CRITERIA_ZONE_DOCUMENT1;

					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 8 + values.length;

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();

					param[2] = spForm.getZoneId();
					param[3] = "1";
					param[4] = spForm.getRangeId();
					param[5] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[6] = spForm.getDurFrom();
						param[7] = spForm.getDurTo();

					}
					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);

					dbUtil = new DBUtility();
					SQL = squery.toString();
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);

				}
			} else {
				if (spForm.getPropertyL2Id() != null & !spForm.getPropertyL2Id().equalsIgnoreCase(""))// L2
				{
					SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENTL2;
					String param[] = new String[10];
					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}

					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 10 + values.length;

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();
					param[2] = spForm.getPropertyL2Id();
					param[3] = spForm.getDistrictId();
					param[4] = spForm.getZoneId();
					param[5] = "1";
					param[6] = spForm.getRangeId();
					param[7] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[8] = spForm.getDurFrom();
						param[9] = spForm.getDurTo();

					}
					dbUtil = new DBUtility();
					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);
					SQL = squery.toString();
					logger.debug("^^^^^^^^^^^^QUERY" + SQL);
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);

				} else // Optional Level
				{
					SQL = CommonSQL.SPOT_CRITERIA_DISTRICT_DOCUMENT1;
					String param[] = new String[9];
					StringBuilder squery = new StringBuilder(SQL);

					String[] values = spForm.getSelectedClauses();

					for (int i = 0; i < values.length; i++) {
						if (i != values.length - 1)
							squery.append("?,");
						else
							squery.append("?");

					}
					squery.append(CommonSQL.REST_OF_QUERY);

					int sizeOfQuery = 9 + values.length;

					param[0] = spForm.getDeedId();
					param[1] = spForm.getPropertyId();
					param[2] = spForm.getDistrictId();
					param[3] = spForm.getZoneId();
					param[4] = "1";
					param[5] = spForm.getRangeId();
					param[6] = spForm.getRangeId();
					if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
						param[7] = spForm.getDurFrom();
						param[8] = spForm.getDurTo();

					}

					String paramfinal[] = new String[sizeOfQuery];
					paramfinal = (String[]) ArrayUtils.addAll(param, values);
					SQL = squery.toString();
					dbUtil = new DBUtility();
					logger.debug("^^^^^^^^^^^^QUERY" + SQL);
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(paramfinal);
				}

			}
		}
		try {

			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public ArrayList getDocumentReInspection(SpotInspForm spForm) throws Exception {
		ArrayList list = new ArrayList();
		String param[] = new String[3];

		if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
			param[1] = spForm.getDurFrom();
			param[2] = spForm.getDurTo();

		}

		if (spForm.getViewType().equals("State")) {
			SQL = CommonSQL.SPOT_reinspection_CRITERIA_DOCUMENT;
			param[0] = "C";
		} else if (spForm.getViewType().equals("Zone")) {
			SQL = CommonSQL.SPOT_reinspection_zone_CRITERIA_DOCUMENT;
			param[0] = spForm.getZoneId();
		} else {
			SQL = CommonSQL.SPOT_reinspection_district_CRITERIA_DOCUMENT;
			param[0] = spForm.getDistrictId();
		}
		DBUtility dbUtil = null;
		try

		{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * Getting District list.
	 * 
	 * @return list
	 */

	public ArrayList getAssignCriteriaDocument(SpotInspForm spForm) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		if (spForm.getPropertyL2Id() != null && !spForm.getPropertyL2Id().equalsIgnoreCase("")) {
			String param[] = new String[8];
			SQL = CommonSQL.SPOT_ASSIGNED_CRITERIA_DOCUMENT_PROPERTY_L2;

			try {
				dbUtil = new DBUtility();
				param[0] = spForm.getDeedId();
				param[1] = spForm.getPropertyId();
				param[2] = spForm.getPropertyL2Id();
				param[3] = spForm.getDistrictId();
				param[4] = spForm.getRangeId();
				param[5] = spForm.getRangeId();

				if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
					param[6] = spForm.getDurFrom();
					param[7] = spForm.getDurTo();

				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(param);
				logger.info("dist id" + spForm.getDistrictId());
				logger.info("Wipro in SpotInspDAO - getAssignList() AFTER EXCUTIN QUERY list values" + list);
			} catch (Exception e) {
				logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
			}
		} else {
			String param[] = new String[7];
			SQL = CommonSQL.SPOT_ASSIGNED_CRITERIA_DOCUMENT_PROPERTY_L1;
			try {
				dbUtil = new DBUtility();
				param[0] = spForm.getDeedId();
				param[1] = spForm.getPropertyId();
				param[2] = spForm.getDistrictId();
				param[3] = spForm.getRangeId();
				param[4] = spForm.getRangeId();

				if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
					param[5] = spForm.getDurFrom();
					param[6] = spForm.getDurTo();

				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(param);
				logger.info("dist id" + spForm.getDistrictId());
				logger.info("Wipro in SpotInspDAO - getAssignList() AFTER EXCUTIN QUERY list values" + list);
			} catch (Exception e) {
				logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
			}

		}
		dbUtil.closeConnection();
		return list;
	}

	public ArrayList getAssignCriteriaDocumentTest(SpotInspForm spForm) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		if (spForm.getPropertyL2Id() != null && !spForm.getPropertyL2Id().equalsIgnoreCase("")) {
			String param[] = new String[8];
			SQL = CommonSQL.SPOT_ASSIGNED_CRITERIA_DOCUMENT_PROPERTY_L2;
			try {
				dbUtil = new DBUtility();
				param[0] = spForm.getDeedId();
				param[1] = spForm.getPropertyId();
				param[2] = spForm.getPropertyL2Id();
				param[3] = spForm.getDistrictId();
				param[4] = spForm.getRangeId();
				param[5] = spForm.getRangeId();

				if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
					param[6] = spForm.getDurFrom();
					param[7] = spForm.getDurTo();

				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(param);
				logger.info("dist id" + spForm.getDistrictId());
				logger.info("Wipro in SpotInspDAO - getAssignList() AFTER EXCUTIN QUERY list values" + list);
			} catch (Exception e) {
				logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
			}
		} else {
			String param[] = new String[7];
			SQL = CommonSQL.SPOT_ASSIGNED_CRITERIA_DOCUMENT_PROPERTY_L1;
			try {
				dbUtil = new DBUtility();
				param[0] = spForm.getDeedId();
				param[1] = spForm.getPropertyId();
				param[2] = spForm.getDistrictId();
				param[3] = spForm.getRangeId();
				param[4] = spForm.getRangeId();

				if (spForm.getDurFrom() != null && spForm.getDurTo() != null) {
					param[5] = spForm.getDurFrom();
					param[6] = spForm.getDurTo();

				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(param);
				logger.info("dist id" + spForm.getDistrictId());
				logger.info("Wipro in SpotInspDAO - getAssignList() AFTER EXCUTIN QUERY list values" + list);
			} catch (Exception e) {
				logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
			}

		}
		dbUtil.closeConnection();
		return list;
	}

	/**
	 * Getting District list.
	 * 
	 * @param id
	 * @return list
	 */
	public String getGuidelineRate(String[] param, String id) throws Exception {

		String params[] = getPropGuideLineDetails(param);
		String rate = "";
		String[] finalParam;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String query = "";
			if (params[6] == null || params[6].equalsIgnoreCase("") || params[6].equalsIgnoreCase("null") || id.equalsIgnoreCase("2")) {
				finalParam = new String[6];
				finalParam[0] = params[0];
				finalParam[1] = params[1];
				finalParam[2] = params[2];
				finalParam[3] = params[3];
				finalParam[4] = params[4];
				finalParam[5] = params[5];
				query = RegCommonSQL.GET_GUIDELINE_RATE_WITHOUT_L2;
			} else {
				finalParam = params;
				query = RegCommonSQL.GET_GUIDELINE_RATE_WITH_L2;
			}
			dbUtil.createPreparedStatement(query);

			rate = dbUtil.executeQry(finalParam);

		} catch (Exception exception) {
			logger.debug("Exception in getGuidelineRate" + exception);
		} finally {
			dbUtil.closeConnection();
		}
		logger.debug("s");
		return rate;

	}

	private String[] getPropGuideLineDetails(String[] param) {
		String params[] = null;
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_GUIDELINE_VALUE);
			ArrayList data = dbUtil.executeQuery(param);

			if (data != null && data.size() > 0) {
				ArrayList dataNow = (ArrayList) data.get(0);
				String l2_type = (String) dataNow.get(6);

				params = new String[7];
				params[0] = (String) dataNow.get(0);
				params[1] = (String) dataNow.get(1);
				params[2] = (String) dataNow.get(2);
				params[3] = (String) dataNow.get(3);
				params[4] = (String) dataNow.get(4);
				params[5] = (String) dataNow.get(5);
				params[6] = (String) dataNow.get(6);

			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return params;
	}

	public ArrayList getZone(String stateId, String language) {
		try {
			if (stateId == null) {
				stateId = "1";
			}

			list = getZones(stateId, language);
			logger.info("SpotInspDAO----getState  " + list);
		} catch (Exception e) {
			logger.error("Exception in getState():" + e);
		}
		return list;
	}

	public synchronized ArrayList getZones(String stateId, String language) throws Exception {
		String arry[] = new String[1];
		logger.debug("State ID:-" + stateId);

		ArrayList list = new ArrayList();
		if (stateId != null) {
			arry[0] = stateId;
		}
		DBUtility dbUtil = null;
		try {
			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();
			if (language.equalsIgnoreCase("en"))
				dbUtil.createPreparedStatement(CommonSQL.ZONE_QUERY);
			else
				dbUtil.createPreparedStatement(CommonSQL.ZONE_QUERY_HINDI);

			list = dbUtil.executeQuery(arry);
			logger.debug("After initialize DBUtility");
			if (logger.isDebugEnabled()) {
				logger.debug("getDistrict(String) - end");
			}
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public ArrayList getTehsil(String districtId, String language) {
		try {

			list = getTehsils(districtId, language);
			logger.info("SpotInspDAO----getTehsil  " + list);
		} catch (Exception e) {
			logger.error("Exception in getTehsil():" + e);
		}
		return list;
	}

	public synchronized ArrayList getMahalla(String patwariId, String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getMahalla(String) - start");
		}
		String SQL = "";
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.MOHALLA_QUERY;
		else
			SQL = CommonSQL.MOHALLA_QUERY_HINDI;

		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		if (patwariId != null) {
			arry[0] = patwariId;
		}
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - " + "getMahalla after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - " + "getMahalla() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	public synchronized ArrayList getTehsils(String _distId, String language) throws Exception {
		String SQL = "";
		if (language.equalsIgnoreCase("en"))
			SQL = "SELECT TEHSIL_ID,TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_STATUS='A' AND DISTRICT_ID=?";
		else
			SQL = "SELECT TEHSIL_ID,H_TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_STATUS='A' AND DISTRICT_ID=?";

		String arry[] = new String[1];
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if (_distId != null) {
				arry[0] = _distId;
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);

			} else
				return null;
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public ArrayList getAreaType(String language) {
		DBUtility dbUtil = null;
		try {
			String sql = "";
			if (language.equalsIgnoreCase("en"))
				sql = CommonSQL.SPOT_AREA_TYPE_LIST;
			else
				sql = CommonSQL.SPOT_AREA_TYPE_LIST_HINDI;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);

			logger.info("SpotInspDAO----getAreaType  " + list);
		} catch (Exception e) {
			logger.error("Exception in getAreaType():" + e);
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
	 * getting SRO list values.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSroList() throws Exception {
		ArrayList list = new ArrayList();
		SQL = CommonSQL.SPOT_SRO_LIST;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public ArrayList getDistSrList(String districtId) throws Exception {
		ArrayList list = new ArrayList();
		SQL = CommonSQL.SPOT_SR_LIST + "'" + districtId + "'";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public ArrayList getDistDrList(String districtId) throws Exception {
		ArrayList list = new ArrayList();
		SQL = CommonSQL.SPOT_SR_LIST + "'" + districtId + "'";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public ArrayList getSrList(String userId) throws Exception {
		DBUtility dbUtil = null;
		ArrayList list = new ArrayList();
		SQL = CommonSQL.SPOT_SR_FOR_DR_LIST + "'" + userId + "')";;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public ArrayList getActivityList(String language) throws Exception {
		ArrayList list = new ArrayList();
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_ACTIVITY_LIST;
		else
			SQL = CommonSQL.SPOT_ACTIVITY_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public boolean updateCriteriaActivity(String grpid) throws Exception {
		boolean grpSubmit = false;
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		logger.debug("Criteria id is " + grpid);
		dbUtil.setAutoCommit(false);
		logger.debug("Criteria id is " + grpid);
		String param1[] = new String[1];
		logger.debug("Criteria id is 2" + grpid);
		String detailsInsert = new String();
		String count = new String();
		logger.debug("Criteria id is 3" + grpid);
		param1[0] = grpid;
		logger.debug("inside  is 1 " + grpid.equals("1"));
		logger.debug("Criteria id is " + grpid);

		try {
			logger.debug("Before UPDATE criteria  details ");

			if (grpid.equals("1"))
				detailsInsert = " UPDATE IGRS_SPOT_CRITERIA_DEED_MASTER SET STATUS= 'D' WHERE STATUS='A' ";
			else if (grpid.equals("2")) {
				detailsInsert = " UPDATE IGRS_SPOT_CRITERIA_SUBCLAUSE SET STATUS= 'D' WHERE STATUS='A' ";

			} else if (grpid.equals("3")) {
				detailsInsert = " UPDATE IGRS_SPOT_CRITERIA_PROPERTY SET STATUS= 'D' WHERE STATUS='A' ";
			} else if (grpid.equals("4")) {
				detailsInsert = " UPDATE IGRS_SPOT_CRITERIA_AREA SET STATUS= 'D' WHERE STATUS='A' ";
			} else if (grpid.equals("5")) {
				detailsInsert = " UPDATE IGRS_SPOT_CRITERIA_RANGE SET STATUS= 'D' WHERE STATUS='A' ";
			}
			dbUtil.createStatement();
			grpSubmit = dbUtil.executeUpdate(detailsInsert);
			logger.debug("After UPDATE GROUP USER  details " + grpSubmit);

			logger.debug("Spot Criteria Details updated " + detailsInsert);
		} catch (Exception x) {
			logger.error("Exception in IGRS_SPOT_CRITERIA() :- " + x);
			dbUtil.rollback();
		} finally {
			try {
				if (!grpSubmit) {
					dbUtil.rollback();
				} else {
					dbUtil.commit();
				}
			} catch (Exception ex) {
				logger.error("Exception in IGRS_SPOT_CRITERIA() :-" + ex);
			} finally {
				dbUtil.closeConnection();
			}
		}

		return grpSubmit;
	}

	public ArrayList getcurrentdeedInstrumentList(String language) throws Exception {
		ArrayList list = new ArrayList();
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_CURRENT_DEED_INSTRUMENT_LIST;
		else
			SQL = CommonSQL.SPOT_CURRENT_DEED_INSTRUMENT_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public ArrayList getCurrentRangeList() throws Exception {
		ArrayList list = new ArrayList();
		SQL = CommonSQL.SPOT_CURRENT_RANGE_LIST;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public ArrayList getRangeList() throws Exception {
		ArrayList list = new ArrayList();
		SQL = CommonSQL.SPOT_RANGE_LIST;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public ArrayList getdeedInstrumentList(String language) throws Exception {
		ArrayList list = new ArrayList();

		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_DEED_INSTRUMENT_LIST;
		else
			SQL = CommonSQL.SPOT_DEED_INSTRUMENT_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public boolean insertCriteriaActivity(String aId, String grpid) throws Exception {
		boolean grpSubmit = false;
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		try {
			String sql = null;

			if (grpid.equals("1")) {
				sql = CommonSQL.INSERT_DEED_CRITERIA_MAPPING;
			} else if (grpid.equals("2")) {
				sql = CommonSQL.INSERT_SUBCLAUSE_CRITERIA_MAPPING;
			} else if (grpid.equals("3")) {
				sql = CommonSQL.INSERT_PROPERTY_CRITERIA_MAPPING;
			} else if (grpid.equals("4")) {
				sql = CommonSQL.INSERT_AREA_CRITERIA_MAPPING;
			} else if (grpid.equals("5")) {
				sql = CommonSQL.INSERT_RANGE_CRITERIA_MAPPING;
			}

			dbUtil.createPreparedStatement(sql);
			String param[] = new String[1];
			param[0] = aId;
			grpSubmit = dbUtil.executeUpdate(param);

			if (grpSubmit) {
				dbUtil.commit();
			}

			logger.debug("Role-Group Details Inserted ");
		} catch (Exception x) {
			logger.error("Exception in insertRGDetails() :- " + x);
			dbUtil.rollback();
		} finally {
			try {

				dbUtil.closeConnection();

			} catch (Exception ex) {
				logger.error("Exception in insertRGDetails() :-" + ex);
			}
		}
		return grpSubmit;
	}

	public ArrayList getSubClauseList(String language) throws Exception {
		ArrayList list = new ArrayList();
		if (language.equalsIgnoreCase("en"))

			SQL = CommonSQL.SPOT_SUB_CLAUSE_LIST;
		else
			SQL = CommonSQL.SPOT_SUB_CLAUSE_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public ArrayList getCurrentSubClauseList(String language) throws Exception {
		ArrayList list = new ArrayList();
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_CURRENT_SUB_CLAUSE_LIST;
		else
			SQL = CommonSQL.SPOT_CURRENT_SUB_CLAUSE_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public ArrayList getCurrentSubClauseList1(String language) throws Exception {
		DBUtility dbUtil = null;
		ArrayList list = new ArrayList();
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_CURRENT_SUB_CLAUSE_LIST;
		else
			SQL = CommonSQL.SPOT_CURRENT_SUB_CLAUSE_LIST_HINDI;

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public synchronized ArrayList getWard(String tehsilId, String areaType, String wardType, String language) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getWard(String, String, String) - start");
		}
		DBUtility dbUtil = null;
		String SQL = "";
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.WARD_QUERY;
		else
			SQL = CommonSQL.WARD_QUERY_HINDI;
		String arry[] = new String[3];
		ArrayList list = new ArrayList();
		if (tehsilId != null) {
			arry[0] = tehsilId;
		}

		arry[1] = areaType;
		arry[2] = wardType;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - " + "getWard() after preparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - " + "getWard() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	public ArrayList getPropertyL2(String propertyId, String language) throws Exception {
		ArrayList list = new ArrayList();
		String param[] = new String[1];
		param[0] = propertyId;
		DBUtility dbUtil = null;
		if (language.equalsIgnoreCase("en")) {
			SQL = CommonSQL.SELECT_PROPERTY_TYPE_L2_DETAILS;
		} else
			SQL = CommonSQL.SELECT_PROPERTY_TYPE_L2_DETAILS_HINDI;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
			logger.info("Wipro in SpotInspDAO - getProptertyL2List() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getProptertyL2List()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public ArrayList getCurrentpropertyList(String language) throws Exception {
		ArrayList list = new ArrayList();

		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_CURRENT_PROPERTY_LIST;
		else
			SQL = CommonSQL.SPOT_CURRENT_PROPERTY_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public ArrayList getpropertyPlotList(String language) throws Exception {
		ArrayList list = new ArrayList();

		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_CURRENT_PROPERTY_LIST_PLOT;
		else
			SQL = CommonSQL.SPOT_CURRENT_PROPERTY_LIST_PLOT_HI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}
	public ArrayList getpropertyBuildList(String language) throws Exception {
		ArrayList list = new ArrayList();

		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_CURRENT_PROPERTY_LIST_BUILD;
		else
			SQL = CommonSQL.SPOT_CURRENT_PROPERTY_LIST_BUILD_HI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	public ArrayList getpropertyAgriList(String language) throws Exception {
		ArrayList list = new ArrayList();

		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_CURRENT_PROPERTY_LIST_AGRI;
		else
			SQL = CommonSQL.SPOT_CURRENT_PROPERTY_LIST_AGRI_HI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}
	public ArrayList getCurrentAreaList(String language) throws Exception {
		DBUtility dbUtil = null;
		ArrayList list = new ArrayList();

		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_CURRENT_AREA_LIST;
		else
			SQL = CommonSQL.SPOT_CURRENT_AREA_LIST_HINDI;

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public ArrayList getAreaList(String language) throws Exception {
		ArrayList list = new ArrayList();
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_AREA_LIST;
		else
			SQL = CommonSQL.SPOT_AREA_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getSroList()  " + e);
		}

		return list;
	}

	/**
	 * getting Spot Inspection View Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSpotInspViewDet(String refId) throws Exception {
		SQL = CommonSQL.SPOT_VIEW_DET + "'" + refId + "'";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		} catch (Exception e) {
			logger.error("exception in calling at spot Inspection DAO Class at getSpotInspViewDet ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection Schedule Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSpotInspSechDet(String applNo) throws Exception {
		SQL = CommonSQL.SPOT_SCH_DET + "'" + applNo + "'";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		} catch (Exception e) {
			logger.error("Eexception in calling at DAO Class at getSpotInspSechDet()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection Schedule Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSpotInspCompDet(String refId) throws Exception {
		SQL = CommonSQL.SPOT_COMP_DET + "'" + refId + "'";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSpotInspCompDet() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at DAO Class at getSpotInspCompDet()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection DR Request Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSpotDrReqDet(String refId) throws Exception {
		SQL = CommonSQL.SPOT_DR_REQ_DET + "'" + refId + "'";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSpotDrReqDet() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at DAO Class at getSpotDrReqDet()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection DR Request View Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSpotDrViewDet(String visitRefId) throws Exception {
		logger.info("Wipro in SpotInspDAO - getSpotDrReqDet() before EXCUTIN QUERYvisitRefId= " + visitRefId);
		SQL = CommonSQL.SPOT_DR_VIEW_DET + "'" + visitRefId + "'";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in SpotInspDAO - getSpotDrViewDet() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at DAO Class at getSpotDrViewDet()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * Updating Spot Inspection Schedule Update Details.
	 * 
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @param param
	 * @return
	 * @throws Exception
	 */
	// --START--APPLICANT DETAILS INSERT
	public boolean SpotInspSechUpd(String _refApplNo, String _refInsPlanDate, String roleId, String funId, String userId, String _reamrks) throws Exception {
		DBUtility dbUtil = null;
		boolean blnSpotUpd = false;
		try {
			igrsCommon = new IGRSCommon();
			String[] arry = new String[3];
			if (_refApplNo != null && _refInsPlanDate != null) {
				arry[0] = _refInsPlanDate;
				arry[1] = _reamrks;
				arry[2] = _refApplNo;

			}
			String SQL = CommonSQL.SPOT_SCH_UPDATE;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			blnSpotUpd = dbUtil.executeUpdate(arry);
			if (blnSpotUpd) {
				dbUtil.commit();
				// igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS","UPDATE","T",funId,userId,roleId);
			}

		} catch (Exception x) {
			logger.error("Exception in SpotInspSechUpd() :- " + x);
			x.printStackTrace();
			dbUtil.rollback();
			// igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS","UPDATE","F",funId,userId,roleId);
		} finally {
			try {
				if (!blnSpotUpd) {
					dbUtil.rollback();
					dbUtil.closeConnection();
					// igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS","INSERT","F",funId,userId,roleId);
				}

			} catch (Exception ex) {
				logger.error("Exception in visitDetIns() :-" + ex);
				ex.printStackTrace();
			}
		}
		return blnSpotUpd;
	}
	/**
	 * Updating Spot Inspection Completion Update Details.
	 * 
	 * @param userId
	 * @param funId
	 * @param roleId
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 * 
	 */
	public boolean SpotInspCompUpd(SpotInspForm _refForm, String roleId, String funId, String userId, HttpServletRequest request) throws Exception {
		boolean blnSpotUpd = false;
		String caseId = "";
		DBUtility dbUtil = null;
		try {
			logger.info("inspectionStatus found correct o incorrect" + _refForm.getInspStatus());

			igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			String[] arry = new String[7];
			String array1[] = new String[17];
			String array2[] = new String[7];
			String prefix = "spotprop1[";
			String suffix = "].";
			String newArea = "newArea";
			String newConstructedArea = "newConstructedArea";
			String newTypeOfConstruction = "newTypeOfConstruction";
			String newPropertyUse = "newPropertyUse";
			String newMarketValue = "newMarketValue";
			String newGuideLineValue = "newguidelineValue";
			String s[] = null;
			StringBuilder paramBldr = new StringBuilder();
			String value = "";
			for (int i = 0; i < _refForm.getObjSIDto().getPropList().size(); i++) {
				logger.info("criteriaListSize/srListSize3 --  " + _refForm.getObjSIDto().getPropList().size());
				SpotInspDTO dto = (SpotInspDTO) _refForm.getObjSIDto().getPropList().get(i);

				// SpotInspDTO
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newArea);

				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					dto.setNewArea(value);
				}
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newConstructedArea);
				s = request.getParameterValues(paramBldr.toString());
				value = s[i];
				if (value != null && ("".equals(value)) == false) {
					dto.setNewConstructedArea(value);
				}
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newTypeOfConstruction);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					dto.setNewTypeOfConstruction(value);
				}
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newPropertyUse);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					dto.setNewPropertyUse(value);
				}

				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newMarketValue);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					dto.setNewMarketValue(value);
				}

				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newGuideLineValue);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					dto.setNewguidelineValue(value);
				}

				array1[0] = dto.getRegId();
				array1[1] = dto.getPropId();
				array1[2] = dto.getFloorId();
				array1[3] = dto.getNewArea();
				array1[4] = dto.getNewConstructedArea();
				array1[5] = dto.getNewPropertyUse();
				array1[6] = dto.getNewTypeOfConstruction();
				array1[7] = userId;
				array1[8] = dto.getOldArea();;
				array1[9] = dto.getOldConstructedArea();;
				array1[10] = dto.getOldPropertyUse();;
				array1[11] = dto.getOldTypeOfConstruction();
				array1[12] = dto.getNewguidelineValue();
				array1[13] = dto.getOldguidelineValue();
				array1[14] = dto.getOldMarketValue();
				array1[15] = dto.getNewMarketValue();
				array1[16] = _refForm.getCompApplNo();
				String SQLINS = CommonSQL.SPOT_COMP_INSERT;

				logger.info("_refForm.getStatus()  " + _refForm.getStatus());

				if (_refForm.getStatus().equalsIgnoreCase("R")) {

					SQLINS = CommonSQL.SPOT_COMP_INSERT_NO_FLOOR;

					array2[0] = dto.getNewArea();
					array2[1] = dto.getNewConstructedArea();
					array2[2] = dto.getNewPropertyUse();
					array2[3] = dto.getNewTypeOfConstruction();
					array2[4] = userId;
					array2[5] = dto.getRegId();
					array2[6] = dto.getPropId();
					if (!dto.getFloorId().equals(""))
						SQLINS = CommonSQL.SPOT_COMP_INSERT1 + "'" + dto.getFloorId() + "'";

					dbUtil.createPreparedStatement(SQLINS);
					blnSpotUpd = dbUtil.executeUpdate(array2);

				} else {
					dbUtil.createPreparedStatement(SQLINS);
					blnSpotUpd = dbUtil.executeUpdate(array1);
				}

			}

			if (blnSpotUpd) {

				String SQLs = CommonSQL.SPOT_INSPECTION_INSERT;
				SpotInspDTO temp = _refForm.getObjSIDto();
				String param[] = new String[7];
				param[6] = _refForm.getCompApplNo();
				param[0] = temp.getRegFee();
				param[1] = temp.getNewRegfee();
				param[2] = temp.getStampDuty();

				param[3] = temp.getNewStampDuty();
				param[4] = temp.getRemark();
				param[5] = temp.getNewRemark();
				dbUtil.createPreparedStatement(SQLs);

				blnSpotUpd = dbUtil.executeUpdate(param);

			}

			String ref = null;

			if (_refForm.getStatus().equalsIgnoreCase("R"))
				ref = "DD02" + _refForm.getDistrictId();
			else {
				ref = "DD01" + _refForm.getDistrictId();
			}

			if (_refForm.getInspStatus().equals("Y")) {
				IGRSCommon common = new IGRSCommon();
				arry[4] = common.getSequenceNumber("DUMMY", ref);
				_refForm.setCaseId(arry[4]);
				caseId = arry[4];
			}

			arry[0] = SpotInspBD.getOracleDate(_refForm.getInsActDate());
			// arry[1] = _refForm.getChPropDet();
			arry[1] = _refForm.getChangeVal();

			arry[2] = userId;
			// arry[4] = _refForm.getNameofParty();
			arry[5] = _refForm.getPhotCopyPath();
			arry[6] = _refForm.getCompApplNo();
			arry[3] = _refForm.getInspStatus();

			System.out.println(_refForm.getCompApplNo());
			System.out.println(_refForm.getRegId());
			String SQL = CommonSQL.SPOT_COMP_UPDATE;
			if (_refForm.getStatus().equalsIgnoreCase("R"))
				SQL = CommonSQL.SPOT_COMP_UPDATE1;
			dbUtil.createPreparedStatement(SQL);
			blnSpotUpd = dbUtil.executeUpdate(arry);

			if (blnSpotUpd) {
				SpotInspDTO temp = _refForm.getObjSIDto();
				SQL = CommonSQL.IGRS_INSERT_INTO_CASE_MONITORING;
				String params[] = new String[7];
				params[0] = getRegCompletionNumberDAO(_refForm.getCompApplNo());
				params[1] = caseId;
				params[2] = "I";
				params[3] = userId;
				params[4] = temp.getNewRegfee();
				params[5] = temp.getNewStampDuty();
				params[6] = "S";
				dbUtil.createPreparedStatement(SQL);
				blnSpotUpd = dbUtil.executeUpdate(params);
			}

			if (blnSpotUpd) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "T", funId, userId, roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in SpotInspCompUpd() :-((0)) " + x);
			x.printStackTrace();
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
		} finally {
			try {
				if (!blnSpotUpd) {
					dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in SpotInspCompUpd() :-" + ex);
			}
		}
		return blnSpotUpd;
	}

	public boolean SpotInspCompUpdCheckL(SpotInspForm _refForm, String roleId, String funId, String userId, HttpServletRequest request) throws Exception {

		DBUtility dbUtil = null;
		boolean blnSpotUpd = false;
		String caseId = "";
		_refForm.setSaveInspStatus("N");
		try {
			logger.info("inspectionStatus found correct o incorrect" + _refForm.getInspStatus());

			igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);// changed by akansha

			for (int i = 0; i < _refForm.getObjSIDto().getPropList().size(); i++) {
				logger.info("criteriaListSize/srListSize3 --  " + _refForm.getObjSIDto().getPropList().size());
				SpotInspDTO dto = (SpotInspDTO) _refForm.getObjSIDto().getPropList().get(i);
				String param[] = new String[4];

				param[0] = _refForm.getCompApplNo();
				param[1] = dto.getPropertyId();
				param[2] = dto.getPostInspectionComment();
				param[3] = userId;
				String SQL = CommonSQL.IGRS_SPOT_INSERT_INPECTION_DETAILS;
				dbUtil.createPreparedStatement(SQL);
				blnSpotUpd = dbUtil.executeUpdate(param);

			}

			if (blnSpotUpd) {

				blnSpotUpd = false;// changed by akansha
				String SQLs = CommonSQL.SPOT_INSPECTION_INSERT;
				SpotInspDTO temp = _refForm.getObjSIDto();
				String param[] = new String[15];
				param[6] = _refForm.getCompApplNo();
				param[0] = temp.getRegFee();
				param[1] = temp.getNewRegfee();
				param[2] = temp.getStampDuty();

				param[3] = temp.getNewStampDuty();
				param[4] = temp.getRemark();
				param[5] = temp.getNewRemark();
				param[7] = temp.getJandpadDuty();
				param[8] = temp.getNewJandpadDuty();
				param[9] = temp.getMunicipalDuty();
				param[10] = temp.getNewMunicipalDuty();
				param[11] = temp.getUpkarDuty();
				param[12] = temp.getNewUpkarDuty();
				param[13] = temp.getTotalDuty();
				param[14] = temp.getNewTotalDuty();

				dbUtil.createPreparedStatement(SQLs);

				blnSpotUpd = dbUtil.executeUpdate(param);

			}

			String ref = null;
			blnSpotUpd = false;// changed by akansha
			if (_refForm.getStatus().equalsIgnoreCase("R"))
				ref = "DD02" + _refForm.getDistrictId();
			else {
				ref = "DD01" + _refForm.getDistrictId();
			}

			String arry[] = new String[8];
			/* if(_refForm.getInspStatus().equals("Y")) */// commented by
			// akansha
			if (_refForm.getInspStatus().equals("N")) {
				IGRSCommon common = new IGRSCommon();
				arry[4] = common.getSequenceNumber("DUMMY", ref);
				_refForm.setCaseId(arry[4]);
				caseId = arry[4];
			}

			arry[0] = SpotInspBD.getOracleDate(_refForm.getInsActDate());
			// arry[1] = _refForm.getChPropDet();
			arry[1] = _refForm.getChangeVal();

			arry[2] = userId;
			// arry[4] = _refForm.getNameofParty();
			arry[5] = _refForm.getPhotCopyPath();
			arry[6] = _refForm.getPhotoCopyName();
			arry[7] = _refForm.getCompApplNo();
			arry[3] = _refForm.getInspStatus();

			System.out.println(_refForm.getCompApplNo());
			System.out.println(_refForm.getRegId());
			String SQL = CommonSQL.SPOT_COMP_UPDATE;
			if (_refForm.getStatus().equalsIgnoreCase("R"))
				SQL = CommonSQL.SPOT_COMP_UPDATE1;
			dbUtil.createPreparedStatement(SQL);
			blnSpotUpd = dbUtil.executeUpdate(arry);

			/* if(blnSpotUpd && _refForm.getInspStatus().equals("Y")) */// commented
			// by
			// akansha
			if (blnSpotUpd && _refForm.getInspStatus().equals("N")) {
				SpotInspDTO temp = _refForm.getObjSIDto();
				SQL = CommonSQL.IGRS_INSERT_INTO_CASE_MONITORING;
				String params[] = new String[7];
				params[0] = getRegCompletionNumberDAO(_refForm.getCompApplNo());
				params[1] = caseId;
				params[2] = "I";
				params[3] = userId;
				params[4] = temp.getNewRegfee();
				params[5] = temp.getNewStampDuty();
				params[6] = "S";
				dbUtil.createPreparedStatement(SQL);
				blnSpotUpd = dbUtil.executeUpdate(params);
			}

			if (blnSpotUpd) {
				_refForm.setSaveInspStatus("Y");

				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "T", funId, userId, roleId);
			}

		} catch (Exception e) {
			_refForm.setSaveInspStatus("N");
			dbUtil.rollback();// changed by akansha
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();
		}

		// boo=boo;
		return blnSpotUpd;

	}

	public boolean SpotInspCompUpdCheck(SpotInspForm _refForm, String roleId, String funId, String userId, HttpServletRequest request) throws Exception {
		boolean blnSpotUpd = false;
		String caseId = "";
		DBUtility dbUtil = null;
		try {
			logger.info("inspectionStatus found correct o incorrect" + _refForm.getInspStatus());

			igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			String[] arry = new String[7];
			String array1[] = new String[8];
			String array2[] = new String[7];
			String prefix = "spotprop1[";
			String suffix = "].";

			String prefix1 = "spotprop2[";
			String suffix1 = "].";

			String newArea = "newArea";

			String newConstructedArea = "newConstructedArea";
			String newTypeOfConstruction = "newTypeOfConstruction";
			String newPropertyUse = "newPropertyUse";
			String newMarketValue = "newMarketValue";
			String newGuideLineValue = "newguidelineValue";

			StringBuilder paramBldr = new StringBuilder();
			String value = "";
			String s[] = null;
			StringBuilder paramBldrs = new StringBuilder();
			String values = "";
			for (int i = 0; i < _refForm.getObjSIDto().getPropList().size(); i++) {
				logger.info("criteriaListSize/srListSize3 --  " + _refForm.getObjSIDto().getPropList().size());
				SpotInspDTO dto = (SpotInspDTO) _refForm.getObjSIDto().getPropList().get(i);

				ArrayList<SpotInspDTO> list = dto.getFloorList();
				if (dto.getPropertyId().equalsIgnoreCase("2")) {
					for (int j = 0; j < list.size(); j++) {
						SpotInspDTO dto1 = list.get(j);
						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(j);
						paramBldrs.append(suffix1);
						paramBldrs.append(newArea);

						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setNewArea(values);
						}

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(j);
						paramBldrs.append(suffix1);
						paramBldrs.append(newConstructedArea);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setNewConstructedArea(values);
						}

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(j);
						paramBldrs.append(suffix1);
						paramBldrs.append(newTypeOfConstruction);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setNewTypeOfConstruction(values);
						}

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(j);
						paramBldrs.append(suffix1);
						paramBldrs.append(newPropertyUse);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setNewPropertyUse(values);
						}

						String SQLINS = CommonSQL.IGRS_SPOT_INSERT_PROPERTY_DETAILS;
						dbUtil.createPreparedStatement(SQLINS);
						String parameterFloor[] = new String[12];
						parameterFloor[0] = dto.getRegId();
						parameterFloor[1] = dto.getPropId();
						parameterFloor[2] = dto1.getNewArea();
						parameterFloor[3] = dto1.getOldArea();
						parameterFloor[4] = dto1.getNewConstructedArea();
						parameterFloor[5] = dto1.getOldConstructedArea();
						parameterFloor[6] = dto1.getNewTypeOfConstruction();
						parameterFloor[7] = dto1.getOldTypeOfConstruction();
						parameterFloor[8] = dto1.getNewPropertyUse();
						parameterFloor[9] = dto1.getOldPropertyUse();
						parameterFloor[10] = userId;
						parameterFloor[11] = dto.getPropertyId();
						blnSpotUpd = dbUtil.executeUpdate(parameterFloor);
					}
				}

				else {
					if (list != null && list.size() > 0) {
						SpotInspDTO dto1 = list.get(0);
						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(0);
						paramBldrs.append(suffix1);
						paramBldrs.append(newTypeOfConstruction);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setNewTypeOfConstruction(values);
						}

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(0);
						paramBldrs.append(suffix1);
						paramBldrs.append(newPropertyUse);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setNewPropertyUse(values);
						}

						String SQLINS = CommonSQL.IGRS_SPOT_INSERT_PROPERTY_DETAILS;
						dbUtil.createPreparedStatement(SQLINS);
						String parameterFloor[] = new String[12];
						parameterFloor[0] = dto.getRegId();
						parameterFloor[1] = dto.getPropId();
						parameterFloor[2] = dto1.getNewArea();
						if (dto1.getNewArea().equalsIgnoreCase("N/A")) {
							parameterFloor[2] = "0";
						}
						parameterFloor[3] = dto1.getOldArea();
						if (dto1.getOldArea().equalsIgnoreCase("N/A")) {
							parameterFloor[3] = "0";
						}

						parameterFloor[4] = dto1.getNewConstructedArea();
						if (dto1.getNewConstructedArea().equalsIgnoreCase("N/A")) {
							parameterFloor[4] = "0";
						}
						parameterFloor[5] = dto1.getOldConstructedArea();
						if (dto1.getOldConstructedArea().equalsIgnoreCase("N/A")) {
							parameterFloor[5] = "0";
						}
						parameterFloor[6] = dto1.getNewTypeOfConstruction();
						parameterFloor[7] = dto1.getOldTypeOfConstruction();
						parameterFloor[8] = dto1.getOldPropertyUse();
						parameterFloor[9] = dto1.getNewPropertyUse();
						parameterFloor[10] = userId;
						parameterFloor[11] = dto.getPropertyId();
						blnSpotUpd = dbUtil.executeUpdate(parameterFloor);

					}

				}

				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newMarketValue);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					dto.setNewMarketValue(value);
				}

				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newGuideLineValue);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					dto.setNewguidelineValue(value);
				}

				array1[0] = dto.getRegId();
				array1[1] = dto.getPropId();
				array1[2] = userId;
				array1[3] = dto.getNewguidelineValue();
				array1[4] = dto.getOldguidelineValue();
				array1[5] = dto.getOldMarketValue();
				array1[6] = dto.getNewMarketValue();
				array1[7] = _refForm.getCompApplNo();
				String SQLINS = CommonSQL.SPOT_COMP_INSERT_WO;

				logger.info("_refForm.getStatus()  " + _refForm.getStatus());

				if (_refForm.getStatus().equalsIgnoreCase("R")) {

					SQLINS = CommonSQL.SPOT_COMP_INSERT_NO_FLOOR;

					array2[0] = dto.getNewArea();
					array2[1] = dto.getNewConstructedArea();
					array2[2] = dto.getNewPropertyUse();
					array2[3] = dto.getNewTypeOfConstruction();
					array2[4] = userId;
					array2[5] = dto.getRegId();
					array2[6] = dto.getPropId();
					if (!dto.getFloorId().equals(""))
						SQLINS = CommonSQL.SPOT_COMP_INSERT1 + "'" + dto.getFloorId() + "'";

					dbUtil.createPreparedStatement(SQLINS);
					blnSpotUpd = dbUtil.executeUpdate(array2);

				} else {
					dbUtil.createPreparedStatement(SQLINS);
					blnSpotUpd = dbUtil.executeUpdate(array1);
				}

			}

			if (blnSpotUpd) {

				String SQLs = CommonSQL.SPOT_INSPECTION_INSERT;
				SpotInspDTO temp = _refForm.getObjSIDto();
				String param[] = new String[7];
				param[6] = _refForm.getCompApplNo();
				param[0] = temp.getRegFee();
				param[1] = temp.getNewRegfee();
				param[2] = temp.getStampDuty();

				param[3] = temp.getNewStampDuty();
				param[4] = temp.getRemark();
				param[5] = temp.getNewRemark();
				dbUtil.createPreparedStatement(SQLs);

				blnSpotUpd = dbUtil.executeUpdate(param);

			}

			String ref = null;

			if (_refForm.getStatus().equalsIgnoreCase("R"))
				ref = "DD02" + _refForm.getDistrictId();
			else {
				ref = "DD01" + _refForm.getDistrictId();
			}

			if (_refForm.getInspStatus().equals("Y")) {
				IGRSCommon common = new IGRSCommon();
				arry[4] = common.getSequenceNumber("DUMMY", ref);
				_refForm.setCaseId(arry[4]);
				caseId = arry[4];
			}

			arry[0] = SpotInspBD.getOracleDate(_refForm.getInsActDate());
			// arry[1] = _refForm.getChPropDet();
			arry[1] = _refForm.getChangeVal();

			arry[2] = userId;
			// arry[4] = _refForm.getNameofParty();
			arry[5] = _refForm.getPhotCopyPath();
			arry[6] = _refForm.getCompApplNo();
			arry[3] = _refForm.getInspStatus();

			System.out.println(_refForm.getCompApplNo());
			System.out.println(_refForm.getRegId());
			String SQL = CommonSQL.SPOT_COMP_UPDATE;
			if (_refForm.getStatus().equalsIgnoreCase("R"))
				SQL = CommonSQL.SPOT_COMP_UPDATE1;
			dbUtil.createPreparedStatement(SQL);
			blnSpotUpd = dbUtil.executeUpdate(arry);

			if (blnSpotUpd && _refForm.getInspStatus().equals("Y")) {
				SpotInspDTO temp = _refForm.getObjSIDto();
				SQL = CommonSQL.IGRS_INSERT_INTO_CASE_MONITORING;
				String params[] = new String[7];
				params[0] = getRegCompletionNumberDAO(_refForm.getCompApplNo());
				params[1] = caseId;
				params[2] = "I";
				params[3] = userId;
				params[4] = temp.getNewRegfee();
				params[5] = temp.getNewStampDuty();
				params[6] = "S";
				dbUtil.createPreparedStatement(SQL);
				blnSpotUpd = dbUtil.executeUpdate(params);
			}

			if (blnSpotUpd) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "T", funId, userId, roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in SpotInspCompUpd() :-((0)) " + x);
			x.printStackTrace();
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
		} finally {
			try {
				if (!blnSpotUpd) {
					dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in SpotInspCompUpd() :-" + ex);
			}
		}
		return blnSpotUpd;
	}

	public boolean SpotReInspCompUpdL(SpotInspForm _refForm, String roleId, String funId, String userId, HttpServletRequest request) throws Exception {
		boolean blnSpotUpd = false;
		String caseId = "";
		DBUtility dbUtil = null;
		try {
			logger.info("inspectionStatus found correct o incorrect" + _refForm.getInspStatus());

			igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(true);

			for (int i = 0; i < _refForm.getObjSIDto().getPropList().size(); i++) {
				logger.info("criteriaListSize/srListSize3 --  " + _refForm.getObjSIDto().getPropList().size());
				SpotInspDTO dto = (SpotInspDTO) _refForm.getObjSIDto().getPropList().get(i);

				String param1[] = new String[3];

				param1[2] = dto.getPropertyId();
				param1[0] = dto.getReInspectionComments();
				param1[1] = userId;
				String SQL = CommonSQL.IGRS_SPOT_RE_UPDATE;
				dbUtil.createPreparedStatement(SQL);
				blnSpotUpd = dbUtil.executeUpdate(param1);

			}

			if (blnSpotUpd) {

				String SQLs = CommonSQL.SPOT_RE_INSPECTION_INSERT;
				SpotInspDTO temp = _refForm.getObjSIDto();
				String param[] = new String[15];
				param[6] = _refForm.getCompApplNo();
				param[0] = temp.getNewRegfee();
				param[1] = temp.getCrossRegFee();
				param[2] = temp.getNewStampDuty();

				param[3] = temp.getCrossRegFee();
				param[4] = temp.getNewRemark();
				param[5] = temp.getCrossRemark();
				param[7] = temp.getNewJandpadDuty();
				param[8] = temp.getCrossJanpadDuty();
				param[9] = temp.getNewMunicipalDuty();
				param[10] = temp.getCrossMuncipalDuty();
				param[11] = temp.getNewUpkarDuty();
				param[12] = temp.getCrossUpkarDuty();
				param[13] = temp.getNewTotalDuty();
				param[14] = temp.getCrossTotalDuty();

				dbUtil.createPreparedStatement(SQLs);

				blnSpotUpd = dbUtil.executeUpdate(param);

			}

			String ref = null;
			String arry[] = new String[7];
			if (_refForm.getStatus().equalsIgnoreCase("R"))
				ref = "DD02" + _refForm.getDistrictId();
			else {
				ref = "DD01" + _refForm.getDistrictId();
			}

			if (_refForm.getInspStatus().equals("Y")) {
				IGRSCommon common = new IGRSCommon();

				arry[4] = common.getSequenceNumber("DUMMY", ref);
				_refForm.setCaseId(arry[4]);
				caseId = arry[4];
			}

			arry[0] = SpotInspBD.getOracleDate(_refForm.getInsActDate());
			// arry[1] = _refForm.getChPropDet();
			arry[1] = _refForm.getChangeVal();

			arry[2] = userId;
			// arry[4] = _refForm.getNameofParty();
			arry[5] = _refForm.getPhotCopyPath();
			arry[6] = _refForm.getCompApplNo();
			arry[3] = _refForm.getInspStatus();

			System.out.println(_refForm.getCompApplNo());
			System.out.println(_refForm.getRegId());

			SQL = CommonSQL.SPOT_COMP_UPDATE1;
			dbUtil.createPreparedStatement(SQL);
			blnSpotUpd = dbUtil.executeUpdate(arry);

			if (blnSpotUpd && _refForm.getInspStatus().equals("Y")) {
				SpotInspDTO temp = _refForm.getObjSIDto();
				SQL = CommonSQL.IGRS_INSERT_INTO_CASE_MONITORING;
				String params[] = new String[7];
				params[0] = getRegCompletionNumberDAO(_refForm.getCompApplNo());
				params[1] = caseId;
				params[2] = "I";
				params[3] = userId;
				params[4] = temp.getCrossRegFee();
				params[5] = temp.getCrossStampDuty();
				params[6] = "R";
				dbUtil.createPreparedStatement(SQL);
				blnSpotUpd = dbUtil.executeUpdate(params);
			}

			if (blnSpotUpd) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "T", funId, userId, roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in SpotInspCompUpd() :-((0)) " + x);
			x.printStackTrace();
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
		} finally {
			try {
				if (!blnSpotUpd) {
					dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in SpotInspCompUpd() :-" + ex);
			}
		}
		return blnSpotUpd;
	}

	public boolean SpotReInspCompUpd(SpotInspForm _refForm, String roleId, String funId, String userId, HttpServletRequest request) throws Exception {
		boolean blnSpotUpd = false;
		String caseId = "";
		DBUtility dbUtil = null;
		try {
			logger.info("inspectionStatus found correct o incorrect" + _refForm.getInspStatus());

			igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			String[] arry = new String[7];
			String array1[] = new String[6];
			String array2[] = new String[7];
			String prefix = "spotprop1[";
			String suffix = "].";

			String prefix1 = "spotprop2[";
			String suffix1 = "].";

			String newArea = "crossArea";
			String newConstructedArea = "crossConstructedArea";
			String newTypeOfConstruction = "crossTypeOfConstruction";
			String newPropertyUse = "crossPropertyUse";
			String newMarketValue = "crossMarketValue";
			String newGuideLineValue = "crossGuideLineValue";

			StringBuilder paramBldr = new StringBuilder();
			String value = "";
			String s[] = null;
			StringBuilder paramBldrs = new StringBuilder();
			String values = "";
			for (int i = 0; i < _refForm.getObjSIDto().getPropList().size(); i++) {
				logger.info("criteriaListSize/srListSize3 --  " + _refForm.getObjSIDto().getPropList().size());
				SpotInspDTO dto = (SpotInspDTO) _refForm.getObjSIDto().getPropList().get(i);

				ArrayList<SpotInspDTO> list = dto.getFloorList();
				if (dto.getPropertyId().equalsIgnoreCase("2")) {
					for (int j = 0; j < list.size(); j++) {
						SpotInspDTO dto1 = list.get(j);
						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(j);
						paramBldrs.append(suffix1);
						paramBldrs.append(newArea);

						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setCrossArea(values);
						}

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(j);
						paramBldrs.append(suffix1);
						paramBldrs.append(newConstructedArea);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setCrossConstructedArea(values);
						}

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(j);
						paramBldrs.append(suffix1);
						paramBldrs.append(newTypeOfConstruction);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setCrossTypeOfConstruction(values);
						}

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(j);
						paramBldrs.append(suffix1);
						paramBldrs.append(newPropertyUse);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setCrossPropertyUse(values);
						}

						String SQLINS = CommonSQL.IGRS_SPOT_UPDATE_PROPERTY_DETAILS;
						dbUtil.createPreparedStatement(SQLINS);
						String parameterFloor[] = new String[7];
						parameterFloor[0] = dto.getRegId();

						parameterFloor[1] = dto1.getCrossArea();
						parameterFloor[2] = dto1.getCrossConstructedArea();
						parameterFloor[3] = dto1.getCrossTypeOfConstruction();
						parameterFloor[4] = dto1.getCrossPropertyUse();
						parameterFloor[5] = userId;
						parameterFloor[6] = dto.getPropId();
						blnSpotUpd = dbUtil.executeUpdate(parameterFloor);
					}
				}

				else {
					if (list != null && list.size() > 0) {
						SpotInspDTO dto1 = list.get(0);

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(0);
						paramBldrs.append(suffix1);
						paramBldrs.append(newArea);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setCrossArea(values);
						}

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(0);
						paramBldrs.append(suffix1);
						paramBldrs.append(newTypeOfConstruction);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setCrossTypeOfConstruction(values);
						}

						paramBldrs.delete(0, paramBldrs.length());
						paramBldrs.append(prefix1);
						paramBldrs.append(0);
						paramBldrs.append(suffix1);
						paramBldrs.append(newPropertyUse);
						s = request.getParameterValues(paramBldrs.toString());
						values = s[i];
						if (values != null && ("".equals(values)) == false) {
							dto1.setCrossPropertyUse(values);
						}

						String SQLINS = CommonSQL.IGRS_SPOT_UPDATE_PROPERTY_DETAILS;
						dbUtil.createPreparedStatement(SQLINS);
						String parameterFloor[] = new String[7];
						parameterFloor[0] = dto.getRegId();

						parameterFloor[1] = dto1.getCrossArea();
						parameterFloor[2] = dto1.getCrossConstructedArea();
						parameterFloor[3] = dto1.getCrossTypeOfConstruction();
						parameterFloor[4] = dto1.getCrossPropertyUse();
						if (dto1.getCrossArea().equalsIgnoreCase("N/A") || dto1.getCrossArea().equalsIgnoreCase("")) {
							parameterFloor[1] = "0";
						}

						parameterFloor[2] = dto1.getCrossConstructedArea();
						if (dto1.getCrossConstructedArea() != null) {
							if (dto1.getCrossConstructedArea().equalsIgnoreCase("N/A") || dto1.getCrossConstructedArea().equalsIgnoreCase("")) {
								parameterFloor[2] = "0";
							}
						} else {
							parameterFloor[2] = "0";
						}
						parameterFloor[3] = dto1.getCrossTypeOfConstruction();
						parameterFloor[4] = dto1.getCrossPropertyUse();
						parameterFloor[5] = userId;
						parameterFloor[6] = dto.getPropertyId();
						blnSpotUpd = dbUtil.executeUpdate(parameterFloor);

					}

				}

				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newMarketValue);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					dto.setCrossMarketValue(value);
				}

				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(i);
				paramBldr.append(suffix);
				paramBldr.append(newGuideLineValue);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					dto.setCrossGuideLineValue(value);
				}

				array1[0] = dto.getRegId();
				array1[1] = dto.getPropId();
				array1[2] = userId;
				array1[3] = dto.getCrossMarketValue();
				array1[4] = dto.getCrossGuideLineValue();
				array1[5] = _refForm.getCompApplNo();
				String SQLINS = CommonSQL.SPOT_COMP_UPDATE_WO;

				logger.info("_refForm.getStatus()  " + _refForm.getStatus());

				dbUtil.createPreparedStatement(SQLINS);
				blnSpotUpd = dbUtil.executeUpdate(array1);

			}

			if (blnSpotUpd) {

				String SQLs = CommonSQL.SPOT_RE_INSPECTION_INSERT;
				SpotInspDTO temp = _refForm.getObjSIDto();
				String param[] = new String[7];
				param[6] = _refForm.getCompApplNo();
				param[0] = temp.getNewRegfee();
				param[1] = temp.getCrossRegFee();
				param[2] = temp.getNewStampDuty();

				param[3] = temp.getCrossRegFee();
				param[4] = temp.getNewRemark();
				param[5] = temp.getCrossRemark();
				dbUtil.createPreparedStatement(SQLs);

				blnSpotUpd = dbUtil.executeUpdate(param);

			}

			String ref = null;

			if (_refForm.getStatus().equalsIgnoreCase("R"))
				ref = "DD02" + _refForm.getDistrictId();
			else {
				ref = "DD01" + _refForm.getDistrictId();
			}

			if (_refForm.getInspStatus().equals("Y")) {
				IGRSCommon common = new IGRSCommon();
				arry[4] = common.getSequenceNumber("DUMMY", ref);
				_refForm.setCaseId(arry[4]);
				caseId = arry[4];
			}

			arry[0] = SpotInspBD.getOracleDate(_refForm.getInsActDate());
			// arry[1] = _refForm.getChPropDet();
			arry[1] = _refForm.getChangeVal();

			arry[2] = userId;
			// arry[4] = _refForm.getNameofParty();
			arry[5] = _refForm.getPhotCopyPath();
			arry[6] = _refForm.getCompApplNo();
			arry[3] = _refForm.getInspStatus();

			System.out.println(_refForm.getCompApplNo());
			System.out.println(_refForm.getRegId());

			SQL = CommonSQL.SPOT_COMP_UPDATE1;
			dbUtil.createPreparedStatement(SQL);
			blnSpotUpd = dbUtil.executeUpdate(arry);

			if (blnSpotUpd && _refForm.getInspStatus().equals("Y")) {
				SpotInspDTO temp = _refForm.getObjSIDto();
				SQL = CommonSQL.IGRS_INSERT_INTO_CASE_MONITORING;
				String params[] = new String[7];
				params[0] = getRegCompletionNumberDAO(_refForm.getCompApplNo());
				params[1] = caseId;
				params[2] = "I";
				params[3] = userId;
				params[4] = temp.getCrossRegFee();
				params[5] = temp.getCrossStampDuty();
				params[6] = "R";
				dbUtil.createPreparedStatement(SQL);
				blnSpotUpd = dbUtil.executeUpdate(params);
			}

			if (blnSpotUpd) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "T", funId, userId, roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in SpotInspCompUpd() :-((0)) " + x);
			x.printStackTrace();
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
		} finally {
			try {
				if (!blnSpotUpd) {
					dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in SpotInspCompUpd() :-" + ex);
			}
		}
		return blnSpotUpd;
	}
	/*
	 * public boolean SpotRe1InspCompUpd(SpotInspForm _refForm, String roleId,
	 * String funId, String userId, HttpServletRequest request) throws Exception
	 * { boolean blnSpotUpd = false; String caseId=""; try {
	 * 
	 * 
	 * logger.info("inspectionStatus found correct o incorrect"+_refForm.
	 * getInspStatus());
	 * 
	 * 
	 * igrsCommon = new IGRSCommon(); dbUtil = new DBUtility();
	 * dbUtil.setAutoCommit(false); String[] arry = new String[7]; String
	 * array1[]=new String [8]; String array2[]=new String [7]; String prefix =
	 * "spotprop1["; String suffix = "].";
	 * 
	 * String prefix1 = "spotprop2["; String suffix1 = "]."; String newArea =
	 * "crossArea"; String newConstructedArea = "crossConstructedArea"; String
	 * newTypeOfConstruction = "crossTypeOfConstruction"; String newPropertyUse
	 * = "crossPropertyUse"; String newMarketValue = "crossMarketValue"; String
	 * newGuideLineValue = "crossGuideLineValue";
	 * 
	 * StringBuilder paramBldr = new StringBuilder(); String value = ""; String
	 * s[] = null; StringBuilder paramBldrs = new StringBuilder(); String values
	 * = ""; for(int i=0;i<_refForm.getObjSIDto().getPropList().size();i++) {
	 * logger.info("--!--"+_refForm.getObjSIDto().getPropList().size());
	 * SpotInspDTO dto = (SpotInspDTO)
	 * _refForm.getObjSIDto().getPropList().get(i);
	 * 
	 * // SpotInspDTO SpotInspDTO dto1 = list.get(j); paramBldrs.delete(0,
	 * paramBldrs.length()); paramBldrs.append(prefix1); paramBldrs.append(j);
	 * paramBldrs.append(suffix1); paramBldrs.append(newArea);
	 * 
	 * s = request.getParameterValues(paramBldrs.toString()); values = s[i]; if
	 * (values != null && ("".equals(values)) == false) {
	 * dto1.setNewArea(values); }
	 * 
	 * paramBldrs.delete(0, paramBldrs.length()); paramBldrs.append(prefix1);
	 * paramBldrs.append(j); paramBldrs.append(suffix1);
	 * paramBldrs.append(newConstructedArea); s =
	 * request.getParameterValues(paramBldrs.toString()); values = s[i]; if
	 * (values != null && ("".equals(values)) == false) {
	 * dto1.setNewConstructedArea(values); }
	 * 
	 * 
	 * paramBldrs.delete(0, paramBldrs.length()); paramBldrs.append(prefix1);
	 * paramBldrs.append(j); paramBldrs.append(suffix1);
	 * paramBldrs.append(newTypeOfConstruction); s =
	 * request.getParameterValues(paramBldrs.toString()); values = s[i]; if
	 * (values != null && ("".equals(values)) == false) {
	 * dto1.setNewTypeOfConstruction(values); }
	 * 
	 * 
	 * paramBldrs.delete(0, paramBldrs.length()); paramBldrs.append(prefix1);
	 * paramBldrs.append(j); paramBldrs.append(suffix1);
	 * paramBldrs.append(newPropertyUse); s =
	 * request.getParameterValues(paramBldrs.toString()); values = s[i]; if
	 * (values != null && ("".equals(values)) == false) {
	 * dto1.setNewPropertyUse(values); }
	 * 
	 * 
	 * String SQLINS="";
	 * 
	 * logger.info("_refForm.getStatus()  "+_refForm.getStatus());
	 * 
	 * if(_refForm.getStatus().equalsIgnoreCase("R")) {
	 * 
	 * SQLINS=CommonSQL.SPOT_COMP_INSERT_NO_FLOOR;
	 * 
	 * 
	 * array2[0]=dto.getCrossArea(); array2[1]=dto.getCrossConstructedArea();
	 * array2[2]=dto.getCrossPropertyUse();
	 * array2[3]=dto.getCrossTypeOfConstruction(); array2[4]=userId;
	 * array2[5]=dto.getCrossMarketValue();
	 * array2[6]=dto.getCrossGuideLineValue();
	 * array2[7]=_refForm.getCompApplNo(); array2[8]=dto.getPropId();
	 * if(!dto.getFloorId().equals(""))
	 * SQLINS=CommonSQL.SPOT_COMP_INSERT1+"'"+dto.getFloorId()+"'";
	 * 
	 * 
	 * dbUtil.createPreparedStatement(SQLINS); blnSpotUpd =
	 * dbUtil.executeUpdate(array2);
	 * 
	 * } else { dbUtil.createPreparedStatement(SQLINS); blnSpotUpd =
	 * dbUtil.executeUpdate(array1); }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * if(blnSpotUpd) {
	 * 
	 * 
	 * String SQLs = CommonSQL.SPOT_RE_INSPECTION_INSERT; SpotInspDTO temp =
	 * _refForm.getObjSIDto(); String param[] = new String[7]; param[6] =
	 * _refForm.getCompApplNo(); param[0] =temp.getNewRegfee(); param[1] =
	 * temp.getCrossRegFee(); param[2] = temp.getNewStampDuty();
	 * 
	 * param[3] = temp.getCrossStampDuty(); param[4] = temp.getNewRemark();
	 * param[5] = temp.getCrossRemark(); dbUtil.createPreparedStatement(SQLs);
	 * 
	 * blnSpotUpd = dbUtil.executeUpdate(param);
	 * 
	 * }
	 * 
	 * 
	 * String ref=null;
	 * 
	 * if(_refForm.getStatus().equalsIgnoreCase("R"))
	 * ref="DD02"+_refForm.getDistrictId(); else {
	 * ref="DD01"+_refForm.getDistrictId(); }
	 * 
	 * if(_refForm.getInspStatus().equals("Y")) { IGRSCommon common=new
	 * IGRSCommon(); arry[4]=common.getSequenceNumber("DUMMY",ref);
	 * _refForm.setCaseId(arry[4]); caseId = arry[4]; }
	 * 
	 * arry[0] = SpotInspBD.getOracleDate(_refForm.getInsActDate()); //arry[1] =
	 * _refForm.getChPropDet(); arry[1] = _refForm.getChangeVal();
	 * 
	 * arry[2] = userId; // arry[4] = _refForm.getNameofParty(); arry[5] =
	 * _refForm.getPhotCopyPath(); arry[6] = _refForm.getCompApplNo(); arry[3]
	 * =_refForm.getInspStatus();
	 * 
	 * 
	 * 
	 * String SQL="";
	 * 
	 * SQL=CommonSQL.SPOT_COMP_UPDATE1; dbUtil.createPreparedStatement(SQL);
	 * blnSpotUpd = dbUtil.executeUpdate(arry);
	 * 
	 * if(blnSpotUpd) { SpotInspDTO temp = _refForm.getObjSIDto(); SQL =
	 * CommonSQL.IGRS_INSERT_INTO_CASE_MONITORING; String params[] = new
	 * String[7]; params[0] =
	 * getRegCompletionNumberDAO(_refForm.getCompApplNo()); params[1] = caseId;
	 * params[2] = "I"; params[3] = userId; params[4] =temp.getCrossRegFee() ;
	 * params[5] = temp.getCrossStampDuty(); params[6] ="R";
	 * dbUtil.createPreparedStatement(SQL); blnSpotUpd =
	 * dbUtil.executeUpdate(params); }
	 * 
	 * if (blnSpotUpd) { dbUtil.commit();
	 * igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS"
	 * ,"UPDATE","T",funId,userId,roleId); } } catch (Exception x) {
	 * logger.error("Exception in SpotInspCompUpd() :-((0)) " + x);
	 * x.printStackTrace(); dbUtil.rollback();
	 * igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS"
	 * ,"UPDATE","F",funId,userId,roleId); } finally { try { if(!blnSpotUpd) {
	 * dbUtil.rollback();
	 * igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS","UPDATE"
	 * ,"F",funId,userId,roleId); } dbUtil.closeConnection(); } catch (Exception
	 * ex) { logger.error("Exception in SpotInspCompUpd() :-" + ex); } } return
	 * blnSpotUpd; }
	 */

	/**
	 * getting Spot Inspection View Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSpotViewResFinal(SpotInspForm _refForm) throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[4];
		String userId = _refForm.getLoggedInUserId();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();

			SQL = CommonSQL.IGRS_SPOT_GET_VIEW_DETAILS;

			if (_refForm.getStatus().equals("R"))
				SQL = CommonSQL.IGRS_RE_SPOT_GET_VIEW_DETAILS;

			String arry1[] = new String[8];
			if (_refForm.getDurFrom() != null && _refForm.getDurTo() != null) {
				// arry1[0] = SpotInspBD.getOracleDate(_refForm.getDurFrom());
				// arry1[1] = SpotInspBD.getOracleDate(_refForm.getDurTo());
				// arry1[0] = _refForm.getDurFrom();
				// arry1[1] = _refForm.getDurTo();
				arry1[0] = userId;
				arry1[1] = _refForm.getLoggedInOffice();
				arry1[2] = _refForm.getDurFrom();
				arry1[3] = _refForm.getDurTo();
				arry1[4] = userId;
				arry1[5] = _refForm.getLoggedInOffice();
				arry1[6] = _refForm.getDurFrom();
				arry1[7] = _refForm.getDurTo();
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry1);
			}
		} catch (Exception e) {
			System.out.println("exception in calling atSpotInspDAO  -- DAO Class at getSpotViewRes ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public ArrayList getSpotViewRes(SpotInspForm _refForm) throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[4];
		String userId = _refForm.getLoggedInUserId();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();

			SQL = CommonSQL.SPOT_SRVIEW_DATE_QUERY;

			if (_refForm.getStatus().equals("R"))
				SQL = CommonSQL.SPOT_SRVIEW_REINSECT_DATE_QUERY;

			String arry1[] = new String[3];
			if (_refForm.getDurFrom() != null && _refForm.getDurTo() != null) {
				// arry1[0] = SpotInspBD.getOracleDate(_refForm.getDurFrom());
				// arry1[1] = SpotInspBD.getOracleDate(_refForm.getDurTo());
				arry1[0] = _refForm.getDurFrom();
				arry1[1] = _refForm.getDurTo();
				arry1[2] = userId;

				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry1);
			}
		} catch (Exception e) {
			System.out.println("exception in calling atSpotInspDAO  -- DAO Class at getSpotViewRes ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection Schedule Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotSechRes(SpotInspForm _refForm) throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[1];

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();

			// SQL = CommonSQL.SPOT_VIEW_DATE_QUERY; //ramesh commented on
			// 02Jan13
			SQL = CommonSQL.SPOT_VIEW_INSPECTION_DATE_QUERY;

			if (_refForm.getStatus().equals("R"))
				SQL = CommonSQL.SPOT_VIEW_REINSPECTION_DATE_QUERY;

			String arry1[] = new String[2];
			if (_refForm.getDurFrom() != null && _refForm.getDurTo() != null) {
				// arry1[0] = SpotInspBD.getOracleDate(_refForm.getDurFrom());
				// arry1[1] = SpotInspBD.getOracleDate(_refForm.getDurTo());
				arry1[0] = _refForm.getDurFrom();
				arry1[1] = _refForm.getDurTo();

				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry1);
				System.out.println("Wipro in SpotInspDAO - getSpotSechRes() Search Type is Date  " + SQL);
			}
		} catch (Exception e) {
			System.out.println("exception in calling at SpotInspDAO  -- DAO Class at getSpotSechRes ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection Schedule Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotAssignmentRequest(SpotInspForm _refForm) throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[1];

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if (_refForm.getViewType().equalsIgnoreCase("State")) {

				// SQL = CommonSQL.SPOT_VIEW_APPID_QUERY; //Ramesh Commented on
				// 02Jan 13
				SQL = CommonSQL.SPOT_VIEW_SCHUDLE_TXNID_QUERY;
				if (_refForm.getReferenceID() != null) {
					arry[0] = _refForm.getReferenceID();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);
				System.out.println("Wipro in SpotInspDAO  - getSpotSechRes() AFTER EXCUTIN QUERY raj" + list);
			}
			if (_refForm.getViewType().equalsIgnoreCase("Zone")) {

				// SQL = CommonSQL.SPOT_VIEW_STATUS_QUERY; //Ramesh commented on
				// 02Jan 13
				SQL = CommonSQL.SPOT_SCHUDLE_STATUS_QUERY;
				if (_refForm.getStatus() != null) {
					arry[0] = _refForm.getStatus();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);
				System.out.println("Wipro in SpotInspDAO - getSpotSechRes() AFTER EXCUTIN QUERY" + list);
			}

			if (_refForm.getViewType().equalsIgnoreCase("District")) {

				// SQL = CommonSQL.SPOT_VIEW_DATE_QUERY; //ramesh commented on
				// 02Jan13
				SQL = CommonSQL.SPOT_VIEW_SCHUDLE_DATE_QUERY;
				String arry1[] = new String[2];
				if (_refForm.getDurFrom() != null && _refForm.getDurTo() != null) {
					// arry1[0] =
					// SpotInspBD.getOracleDate(_refForm.getDurFrom());
					// arry1[1] = SpotInspBD.getOracleDate(_refForm.getDurTo());
					arry1[0] = _refForm.getDurFrom();
					arry1[1] = _refForm.getDurTo();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry1);
				System.out.println("Wipro in SpotInspDAO - getSpotSechRes() Search Type is Date  " + SQL);
			}
		} catch (Exception e) {
			System.out.println("exception in calling at SpotInspDAO  -- DAO Class at getSpotSechRes ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection Schedule Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotSechRequest(SpotInspForm _refForm) throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[1];

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if (_refForm.getViewType().equalsIgnoreCase("RefId")) {

				SQL = CommonSQL.SPOT_VIEW_APPID_QUERY;
				if (_refForm.getReferenceID() != null) {
					arry[0] = _refForm.getReferenceID();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);
				System.out.println("Wipro in SpotInspDAO  - getSpotSechRes() AFTER EXCUTIN QUERY raj" + list);
			}
			if (_refForm.getViewType().equalsIgnoreCase("Status")) {

				SQL = CommonSQL.SPOT_VIEW_STATUS_QUERY;
				if (_refForm.getStatus() != null) {
					arry[0] = _refForm.getStatus();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);
				System.out.println("Wipro in SpotInspDAO - getSpotSechRes() AFTER EXCUTIN QUERY" + list);
			}

			if (_refForm.getViewType().equalsIgnoreCase("Date")) {

				SQL = CommonSQL.SPOT_VIEW_DATE_QUERY; // ramesh commented on
				// 02Jan13

				String arry1[] = new String[2];
				if (_refForm.getDurFrom() != null && _refForm.getDurTo() != null) {
					// arry1[0] =
					// SpotInspBD.getOracleDate(_refForm.getDurFrom());
					// arry1[1] = SpotInspBD.getOracleDate(_refForm.getDurTo());
					arry1[0] = _refForm.getDurFrom();
					arry1[1] = _refForm.getDurTo();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry1);
				System.out.println("Wipro in SpotInspDAO - getSpotSechRes() Search Type is Date  " + SQL);
			}
		} catch (Exception e) {
			System.out.println("exception in calling at SpotInspDAO  -- DAO Class at getSpotSechRes ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection Schedule Results.
	 * 
	 * @param language
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotCompRes(SpotInspForm _refForm, String language) throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[4];
		String ary[] = new String[1];
		String userId = _refForm.getLoggedInUserId();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();

			// SQL = CommonSQL.SPOT_VIEW_DATE_QUERY; //Ramesh Completed on 02
			// Jan13
			if (language.equalsIgnoreCase("en"))
				SQL = CommonSQL.SPOT_COMPLETION_DATE_QUERY;
			else
				SQL = CommonSQL.SPOT_COMPLETION_DATE_QUERY_HINDI;

			if (_refForm.getStatus().equals("R")) {
				if (language.equalsIgnoreCase("en"))
					SQL = CommonSQL.SPOT_RECOMPLETION_DATE_QUERY;
				else
					SQL = CommonSQL.SPOT_RECOMPLETION_DATE_QUERY_HI;
			}
			String arry1[] = null;

			if (_refForm.getStatus().equals("R")) {
				arry1 = new String[4];
				if (_refForm.getDurFrom() != null && _refForm.getDurTo() != null) {
					logger.info("_refEDto.getEstampValFrom()=" + _refForm.getDurFrom());
					logger.info("_refEDto.getEstampValTo()=" + _refForm.getDurTo());
					arry1[0] = _refForm.getDurFrom();
					arry1[1] = _refForm.getDurTo();
					arry1[2] = userId;
					arry1[3] = _refForm.getLoggedInOffice();
				}
			} else {
				arry1 = new String[5];
				if (_refForm.getDurFrom() != null && _refForm.getDurTo() != null) {
					logger.info("_refEDto.getEstampValFrom()=" + _refForm.getDurFrom());
					logger.info("_refEDto.getEstampValTo()=" + _refForm.getDurTo());
					arry1[0] = _refForm.getDurFrom();
					arry1[1] = _refForm.getDurTo();
					arry1[2] = userId;
					arry1[3] = _refForm.getDistrictId();
					arry1[4] = _refForm.getLoggedInOffice();
				}
			}

			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(arry1);

		} catch (Exception e) {
			System.out.println("exception in calling at SpotInspDAO  -- DAO Class at getSpotCompRes ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection DR Request Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getDrReqRes(SpotInspForm _refForm) throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[3];

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();

			System.out.println("Wipro in SpotInspDAO - getDrReqRes() Search Type is Date pav  ");

			// SQL = CommonSQL.SPOT_VIEW_DATE_QUERY; //Commented by Ramesh on
			// 02Jan13
			SQL = CommonSQL.SPOT_DRAPPROVE_VIEW_DATE_QUERY;
			String arry1[] = new String[3];
			if (_refForm.getDurFrom() != null && _refForm.getDurTo() != null) {

				// arry1[0] = SpotInspBD.getOracleDate(_refForm.getDurFrom());
				// arry1[1] = SpotInspBD.getOracleDate(_refForm.getDurTo());
				arry[0] = _refForm.getDurFrom();
				arry[1] = _refForm.getDurTo();
				arry[2] = _refForm.getDistrictId();

				dbUtil.createPreparedStatement(SQL);

				list = dbUtil.executeQuery(arry);
				System.out.println("Wipro in SpotInspDAO - getDrReqRes() Search Type is Date  " + SQL);
			}
		} catch (Exception e) {
			System.out.println("exception in calling at SpotInspDAO  -- DAO Class at getDrReqRes ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * getting Spot Inspection DR Request View Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getDrViewRes(SpotInspForm _refForm) throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		DBUtility dbUtil = null;
		logger.info("Wipro in SpotInspDAO - getDrViewRes() _refForm.getViewType()===" + _refForm.getViewType());
		try {
			dbUtil = new DBUtility();
			if (_refForm.getViewType().equalsIgnoreCase("RefId")) {
				// SQL = CommonSQL.SPOT_VIEW_APPID_QUERY; //Ramesh commented on
				// 02Jan 13
				SQL = CommonSQL.SPOT_DR_REQVIEW_TXN_QUERY;
				if (_refForm.getReferenceID() != null) {
					arry[0] = _refForm.getReferenceID();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);
			}
			if (_refForm.getViewType().equalsIgnoreCase("Status")) {

				// SQL = CommonSQL.SPOT_VIEW_STATUS_QUERY;//Ramesh Commented on
				// 02 Jan 13
				SQL = CommonSQL.SPOT_DR_REQVIEW_DATE_QUERY;
				if (_refForm.getStatus() != null) {
					arry[0] = _refForm.getStatus();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);
				logger.info("Wipro in SpotInspDAO - getDrViewRes() AFTER EXCUTIN QUERY" + list);
			}
			if (_refForm.getViewType().equalsIgnoreCase("Date")) {
				// SQL = CommonSQL.SPOT_VIEW_DATE_QUERY; //Ramesh Commented on
				// 02 Jan 13
				SQL = CommonSQL.SPOT_DR_REQVIEW_DATE_QUERY;
				if (_refForm.getSpotId() != null) {

					if (_refForm.getFoundStatus().equals("NA")) {
						SQL = CommonSQL.SPOT_ADMIN_QVIEW_DATE_QUERY + "and INSP.district_id ='" + _refForm.getDistrictId() + "'" + "and INSP.SPOT_INSP_STATUS ='" + _refForm.getStatus() + "'";
					} else {
						SQL = CommonSQL.SPOT_ADMIN_QVIEW_DATE_QUERY + "and INSP.district_id ='" + _refForm.getDistrictId() + "'" + "and INSP.found_correct ='" + _refForm.getFoundStatus() + "'" + "and INSP.SPOT_INSP_STATUS ='" + _refForm.getStatus() + "'";
					}
				}

				String arry1[] = new String[2];
				if (_refForm.getDurFrom() != null && _refForm.getDurTo() != null) {
					// arry1[0] =
					// SpotInspBD.getOracleDate(_refForm.getDurFrom());
					// arry1[1] = SpotInspBD.getOracleDate(_refForm.getDurTo());
					arry1[0] = _refForm.getDurFrom();
					arry1[1] = _refForm.getDurTo();
				}
				dbUtil.createPreparedStatement(SQL);
				logger.info("dbUtil.createPreparedStatement(SQL); " + SQL);
				list = dbUtil.executeQuery(arry1);
			}
		} catch (Exception e) {
			System.out.println("exception in calling at SpotInspDAO  -- DAO Class at getDrViewRes ()  " + e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * Updating DR Request Details.
	 * 
	 * @param _refParam
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean updateDrReqDet(String[] _refParam, String roleId, String funId, String userId) throws Exception {
		boolean blnDrReqUpd = false;
		DBUtility dbUtil = null;
		try {
			igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			String param[] = new String[2];
			param[0] = _refParam[0];
			param[1] = _refParam[4];
			String SQL = CommonSQL.DR_REQ_UPDATE;
			String SQL_D = CommonSQL.DR_REQ_UPDATE_DEACT;
			if (param[0].equalsIgnoreCase("A")) {
				dbUtil.createPreparedStatement(SQL);
				blnDrReqUpd = dbUtil.executeUpdate(_refParam);
			} else if (param[0].equalsIgnoreCase("D")) {
				dbUtil.createPreparedStatement(SQL_D);
				blnDrReqUpd = dbUtil.executeUpdate(param);
			}
			logger.info("Wipro in SpotInspDAO - updateDrReqDet() after creating preparedstSQL===" + SQL);
			logger.info("Wipro in SpotInspDAO - updateDrReqDet() SQL_D===" + SQL);
			if (blnDrReqUpd) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "T", funId, userId, roleId);
			}

		} catch (Exception x) {
			logger.info("SpotInspDAO--Exception in updateDrReqDet() :- " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
		} finally {
			try {
				if (!blnDrReqUpd) {
					dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "UPDATE", "F", funId, userId, roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.info("SpotInspDAO--Exception in updateDrReqDet() :-" + ex);
			}
		}
		return blnDrReqUpd;
	}

	/**
	 * getting Property Transaction Id List.
	 * 
	 * @param regTxnId
	 * @return
	 */
	public ArrayList getPropIdList(String regTxnId) throws Exception {
		logger.info(" SpotInspDAO--   getPropIdList()");
		ArrayList list = null;

		String arr[] = new String[2];

		String sql = CommonSQL.SELECT_SPOT_PROP_DETAILS;
		DBUtility dbUtil = null;
		try {
			arr[0] = regTxnId;
			arr[1] = regTxnId;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			list = dbUtil.executeQuery(arr);

			logger.info("SpotInspDAO ---- getPropIdList " + list);
		} catch (Exception e) {
			logger.info("SpotInspDAO ---- Exception in getPropIdList():" + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	/**
	 * getting Property Transaction Id List.
	 * 
	 * @param regTxnId
	 * @return
	 */
	public ArrayList getNewPropIdList(String regTxnId) throws Exception {
		logger.info(" SpotInspDAO--   getPropIdList()");
		ArrayList list = null;

		String arr[] = new String[1];
		DBUtility dbUtil = null;
		String sql = CommonSQL.SPOT_VIEW_DET + "'" + regTxnId + "'";
		try {
			arr[0] = regTxnId;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);

			logger.info("SpotInspDAO ---- getPropIdList " + list);
		} catch (Exception e) {
			logger.info("SpotInspDAO ---- Exception in getPropIdList():" + e);
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	public boolean insSpSpotAssignDet(String[] _refParam, String roleId, String funId, String userId) throws Exception {
		boolean blnInsSpDet = false;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String SQL = CommonSQL.SP_SPOT_ASSIGN_INSERT;
			String SQL1 = CommonSQL.SPOT_FLAG_UPDATE;

			String[] arr = new String[1];
			arr[0] = _refParam[1];
			logger.info("Registration Number Is" + _refParam[1]);
			dbUtil.createPreparedStatement(SQL);
			blnInsSpDet = dbUtil.executeUpdate(_refParam);
			logger.info("Wipro in SpotInspDAO - insSpReqDet() after creating preparedst" + SQL);
			if (blnInsSpDet) {
				dbUtil.createPreparedStatement(SQL1);
				blnInsSpDet = dbUtil.executeUpdate(arr);
				logger.info("Wipro in SpotInspDAO - UPDATESPOTFLAG() after creating preparedst" + SQL1);
				dbUtil.commit();
				// igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS","INSERT","T",funId,userId,roleId);
			}
		} catch (Exception x) {
			x.printStackTrace();
			dbUtil.rollback();
			// igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS","INSERT","F",funId,userId,roleId);
		} finally {
			try {
				dbUtil.closeConnection();
				/*
				 * if (!blnInsSpDet) { dbUtil.rollback();
				 * igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS"
				 * ,"INSERT","F",funId,userId,roleId); }
				 */
			} catch (Exception ex) {
				logger.info("SpotInspDAO--Exception in insSpReqDet() :-" + ex);
			}
		}
		return blnInsSpDet;
	}

	/**
	 * Inserting spot Inspection request Details.
	 * 
	 * @param _refParam
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */

	public boolean insSpReqDet(String[] _refParam, String roleId, String funId, String userId) throws Exception {
		boolean blnInsSpDet = false;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String SQL = CommonSQL.SP_DET_INSERT;
			dbUtil.createPreparedStatement(SQL);
			blnInsSpDet = dbUtil.executeUpdate(_refParam);
			logger.info("Wipro in SpotInspDAO - insSpReqDet() after creating preparedst" + SQL);
			if (blnInsSpDet) {
				dbUtil.commit();
				// igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS","INSERT","T",funId,userId,roleId);
			}
		} catch (Exception x) {
			x.printStackTrace();
			dbUtil.rollback();
			// igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS","INSERT","F",funId,userId,roleId);
		} finally {
			try {
				if (!blnInsSpDet) {
					dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS", "INSERT", "F", funId, userId, roleId);
				}
			} catch (Exception ex) {
				logger.info("SpotInspDAO--Exception in insSpReqDet() :-" + ex);
			}
		}
		return blnInsSpDet;
	}

	/*
	 * public boolean updateSpReqDet(String[] _refParam, String roleId, String
	 * funId, String userId) throws Exception { boolean blnInsSpDet = false; try
	 * { dbUtil = new DBUtility(); String SQL = CommonSQL.SP_DET_UPDATE;
	 * dbUtil.createPreparedStatement(SQL); blnInsSpDet =
	 * dbUtil.executeUpdate(_refParam);
	 * logger.info("Wipro in SpotInspDAO - insSpReqDet() after creating preparedst"
	 * + SQL); if (blnInsSpDet) { dbUtil.commit();
	 * //igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS"
	 * ,"INSERT","T",funId,userId,roleId); //commented by ramesh on 06 dec 12 }
	 * } catch (Exception x) { x.printStackTrace(); dbUtil.rollback();
	 * //igrsCommon
	 * .saveLogDet("IGRS_SPOT_INSPECTION_DETAILS","INSERT","F",funId,
	 * userId,roleId); //commented by ramesh on 06 dec 12 } finally { try { if
	 * (!blnInsSpDet) { dbUtil.rollback();
	 * igrsCommon.saveLogDet("IGRS_SPOT_INSPECTION_DETAILS"
	 * ,"INSERT","F",funId,userId,roleId); } } catch (Exception ex) {
	 * logger.info("SpotInspDAO--Exception in insSpReqDet() :-" + ex); } }
	 * return blnInsSpDet; }
	 */
	/**
	 * 
	 * @param _spForm
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean updateSpReqDet(SpotInspForm _spForm, String roleId, String funId, String userId) throws Exception {
		String sqlQuery = "";
		int rowCount;
		Connection con = null;
		boolean flag = false;
		try {
			DBUtility dbUtil = null;
			dbUtil = new DBUtility();
			PreparedStatement prepStmt;
			if (con == null || con.isClosed()) {
				con = dbUtil.returnConnection();
			}
			con.setAutoCommit(false);
			// Start insert into IGRS_CERTIFIED_COPY_TXN
			sqlQuery = CommonSQL.SP_DET_UPDATE;
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setString(1, _spForm.getPropId());
			// prepStmt.setString(2, _spForm.getUserId());
			prepStmt.setString(2, _spForm.getUserId());
			prepStmt.setString(3, _spForm.getRemarks());
			prepStmt.setString(4, _spForm.getSiReason());
			prepStmt.setString(5, _spForm.getReqType());
			prepStmt.setString(6, CommonUtil.getConvertedDate(_spForm.getReqiDate()));
			prepStmt.setString(7, _spForm.getRegTxnId());
			rowCount = prepStmt.executeUpdate();
			logger.debug("RowCount " + rowCount);
			prepStmt.close();

			sqlQuery = CommonSQL.PROP_FLAG_UPDATE;
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setString(1, _spForm.getPropId());
			rowCount = prepStmt.executeUpdate();
			logger.debug("RowCount " + rowCount);
			prepStmt.close();

			con.commit();
			flag = true;
			return flag;
		} catch (Exception e) {
			logger.info("Exception in insertlockProperty() :- " + e);
			e.printStackTrace();
			flag = false;
			con.rollback();
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				logger.info("Exception in insertlockProperty() :-" + ex);
			}
		}
		return flag;
	}
	/**
	 * 
	 * @param propId
	 * @return
	 */

	public ArrayList getPropStatus(String[] propId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			logger.debug("before getting lock details ");
			dbUtil.createPreparedStatement(CommonSQL.SELECT_SPOT_PROP_STATUS);
			logger.debug("SQL:" + CommonSQL.SELECT_SPOT_PROP_STATUS);
			list = dbUtil.executeQuery(propId);
			logger.debug("LIST=" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception in getCopyIssuance() :- " + e);
		} finally {
			try {

				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in closing connection :-" + ex);
			}
		}

		return list;
	}

	public String getAssignPercantage() {
		String a = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String SQL = "SELECT  ATTRIBUTE_VALUE  FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID = 'ATT_159' ";
			logger.debug("before getting lock details ");
			dbUtil.createStatement();
			logger.debug("SQL:" + SQL);
			a = dbUtil.executeQry(SQL);
			logger.debug("value of  percentage) :- " + a);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception in getCopyIssuance() :- " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return a;
	}

	public String getType(String office) {
		String a = null;
		DBUtility dbUtil = null;
		try {
			String param[] = new String[1];
			param[0] = office;
			dbUtil = new DBUtility();
			String SQL = CommonSQL.SPOT_CHECK_ROLE;
			logger.debug("before getting office details ");
			dbUtil.createPreparedStatement(SQL);
			logger.debug("SQL:" + SQL);
			ArrayList data = dbUtil.executeQuery(param);
			ArrayList data1 = (ArrayList) data.get(0);
			a = (String) data1.get(0);
			logger.debug("USer is that) :- " + a);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception in getCopyIssuance() :- " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return a;
	}

	public String getDIGZoneDAO(String office) {

		String a = null;
		DBUtility dbUtil = null;
		try {
			String param[] = new String[1];
			param[0] = office;
			dbUtil = new DBUtility();
			String SQL = CommonSQL.SPOT_GET_ZONE_DIG;
			logger.debug("before getting Zone details ");
			dbUtil.createPreparedStatement(SQL);
			logger.debug("SQL:" + SQL);
			ArrayList data = dbUtil.executeQuery(param);
			ArrayList data1 = (ArrayList) data.get(0);
			a = (String) data1.get(0);
			logger.debug("value of  Zone_Id) :- " + a);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception in getZoneDetails() :- " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return a;
	}

	public ArrayList getDistDIGListDAO(String zoneId, String language) {

		ArrayList list = new ArrayList();
		String param[] = new String[1];
		param[0] = zoneId;
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.SPOT_GET_DISTRICT_ZONE_LIST;
		else
			SQL = CommonSQL.SPOT_GET_DISTRICT_ZONE_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
			logger.info("Wipro in SpotInspDAO - getDIGList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getDIGList()  " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public ArrayList getDistListDAO(String userId) {
		ArrayList list = new ArrayList();
		String param[] = new String[1];
		param[0] = userId;
		SQL = CommonSQL.SPOT_GET_DISTRICT_ZONES;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
			logger.info("Wipro in SpotInspDAO - getDROList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getDROList()  " + e);
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

	public boolean insertAssignSpotDataDAO(HashMap<String, ArrayList<SpotInspDTO>> srMap, String roleId, String funId, String userId) {
		boolean check = true;
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();

			dbUtil.setAutoCommit(false);

			HashMap<String, ArrayList<SpotInspDTO>> data = srMap;
			IGRSCommon common = new IGRSCommon();
			Iterator<String> dataKey = data.keySet().iterator();
			String param[] = null;
			ArrayList<String> regNumbers = null;
			String spotId1 = "";
			while (dataKey.hasNext()) {
				if (!check) {
					break;
				}
				System.out.println();

				String key = dataKey.next();
				String spotId = common.getSequenceNumber("IGRS_SPOT_INS_TXN_ID_SEQ", "SpotTxn");
				ArrayList<SpotInspDTO> eachData = data.get(key);
				regNumbers = new ArrayList<String>();
				for (int i = 0; i < eachData.size(); i++) {
					SpotInspDTO dto = eachData.get(i);
					dto.setSpotId(spotId);
					param = new String[15];
					param[0] = dto.getSpotId();
					param[1] = dto.getRegIdcompletion();
					param[2] = dto.getZoneId();
					param[3] = dto.getDistrictId();
					param[4] = dto.getRegistrationOfficeId();
					param[5] = dto.getDrName();
					param[6] = dto.getAssginPersonUserId();
					param[7] = common.getSpotMaxDay();
					param[8] = userId;
					param[9] = dto.getMarketValue();
					param[10] = dto.getRegFee();
					param[11] = dto.getStampDuty();
					param[12] = dto.getTehsilId();
					param[13] = dto.getSrName();
					param[14] = dto.getDigName();

					initiateEmail(dto.getRegIdcompletion(), userId, dto.getAssginPersonUserId(), dto.getSpotId(), param[7].toString());
					regNumbers.add(dto.getRegIdcompletion());
					dbUtil = new DBUtility();
					dbUtil.createPreparedStatement(CommonSQL.SP_SPOT_ASSIGN_INSERT_DATA);
					check = dbUtil.executeUpdate(param);

					if (check) {
						String paramtrans[] = new String[9];
						paramtrans[0] = dto.getSpotId();
						paramtrans[1] = dto.getDistrictId();
						paramtrans[2] = dto.getZoneId();
						paramtrans[3] = dto.getDrName();
						paramtrans[4] = dto.getAssignPersonName();
						paramtrans[5] = userId;
						paramtrans[6] = common.getSpotMaxDay();
						paramtrans[7] = dto.getAssginPersonUserId();
						paramtrans[8] = dto.getDigName();

						dbUtil.createPreparedStatement(CommonSQL.SP_SPOT_ASSIGN_INSERT_TRANSACTION_DATA);
						check = dbUtil.executeUpdate(paramtrans);

					}

					if (check) {
						String params[] = new String[1];
						params[0] = dto.getRegIdcompletion();
						dbUtil.createPreparedStatement(CommonSQL.SPOT_FLAG_UPDATE);
						dbUtil.executeUpdate(params);
					}
					if (!check) {
						break;
					}

				}

			}

			if (check) {

				dbUtil.commit();
				return true;
			} else {
				dbUtil.rollback();
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		check = check;
		return check;

	}

	public HashMap<String, ArrayList<SpotInspDTO>> getSrLists(Iterator<String> ids) {

		HashMap<String, ArrayList<SpotInspDTO>> temp = new HashMap<String, ArrayList<SpotInspDTO>>();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String param[] = new String[2];
			dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_GET_SR_LIST);
			logger.debug("@@aakash@@" + CommonSQL.IGRS_SPOT_GET_SR_LIST);
			while (ids.hasNext()) {
				String id = ids.next();
				param[0] = id;
				param[1] = id;
				ArrayList list = dbUtil.executeQuery(param);
				ArrayList<SpotInspDTO> t = new ArrayList<SpotInspDTO>();
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						ArrayList y = (ArrayList) list.get(i);
						SpotInspDTO a = new SpotInspDTO();
						a.setSrId((String) y.get(0));
						a.setSrName((String) y.get(1));
						t.add(a);
					}

				}

				temp.put(id, t);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return temp;
	}
	//added by saurav
/*	public HashMap<String, ArrayList<SpotInspDTO>> getDocUserList(ArrayList<SpotInspDTO> edata){
		ArrayList<String> res = new ArrayList<String>();
		Iterator<SpotInspDTO> itr = edata.iterator();
		TreeSet<String> userIds = new TreeSet<String>();
		DBUtility dbUtil = null;
		while(itr.hasNext()){
			SpotInspDTO id = itr.next();
			userIds.add(id.getDocCompletionSR());
		}
		try {
			dbUtil = new DBUtility();
			String param[] = new String[1];
			dbUtil.createPreparedStatement("select update_by from igrs_Reg_txn_detls where registration_number = ?");
			logger.debug("@@aakash@@" + CommonSQL.IGRS_SPOT_GET_SR_LIST);
			Iterator<String> itr1 = userIds.iterator();
			while (itr1.hasNext()) {
				String id = itr1.next();
				Iterator<SpotInspDTO> itr2 = edata.iterator();
				while(itr2.hasNext()){
					SpotInspDTO id1 = itr2.next();
					param[0] = id1.getRegIdcompletion();
					String list = dbUtil.executeQry(param);
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}*/
	public String getRegCompletionNumberDAO(String reginitno) {

		String param[] = new String[1];
		param[0] = reginitno;
		String completionNumber = "";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.SPOT_GET_REG_COMPLETION);
			completionNumber = dbUtil.executeQry(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return completionNumber;
	}

	public ArrayList getRegInfoDAO(String regTxnId) {

		String param[] = new String[1];
		param[0] = regTxnId;
		ArrayList ret = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_GET_STAMP_DETAILS);

			ret = dbUtil.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ret;
	}

	public ArrayList getReRegInfoDAO(String regTxnId) {

		String param[] = new String[1];
		param[0] = regTxnId;
		ArrayList ret = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.SELECT_RE_SPOT_GET_REG_INFO);

			ret = dbUtil.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ret;
	}

	public ArrayList<SpotInspDTO> getDashBoardDetails(SpotInspForm spForm, String userId) {

		ArrayList list = new ArrayList();
		int n = 0;
		//String param[] = {};
		ArrayList<String> param = new ArrayList<String>();
		/*param[0] = spForm.getDurFrom();
		param[1] = spForm.getDurTo();
		param[2] = userId;
		param[3] = spForm.getLoggedInOffice();*/
		param.add(spForm.getDurFrom());
		param.add(spForm.getDurTo());
		if (spForm.getStatus().equalsIgnoreCase("R")) {
			SQL = CommonSQL.IGRS_RE_SPOT_GET_DASHBOARD_VALUES;
		} else {
			SQL = CommonSQL.IGRS_SPOT_GET_DASHBOARD_VALUES;
		}
		StringBuilder squery = new StringBuilder(SQL);
		if(spForm.getLoggedInOfficeType().equalsIgnoreCase("DRO")){
			squery.append(CommonSQL.IGRS_SPOT_DASHBOARD_VALUES_DRO);
			param.add(userId);
			param.add(spForm.getLoggedInOffice());
		}else if(spForm.getLoggedInOfficeType().equalsIgnoreCase("SRO")){
			squery.append(CommonSQL.IGRS_SPOT_DASHBOARD_VALUES_SRO);
			param.add(userId);
			param.add(spForm.getLoggedInOffice());
		}else if(spForm.getLoggedInOfficeType().equalsIgnoreCase("DIGO")){
			squery.append(CommonSQL.IGRS_SPOT_DASHBOARD_VALUES_IGO);
			param.add(spForm.getZoneId());
		}else{
			
		}
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(squery.toString());
			//list = dbUtil.executeQuery(param);
			list = dbUtil.executeQuery(param);
			logger.info("Wipro in SpotInspDAO - getDROList() AFTER EXCUTIN QUERY list values" + list);
		} catch (Exception e) {
			logger.error("exception in calling at SpotInspDAO Class at getDROList()  " + e);
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

	public ArrayList getDrillDownDashboardValues(String id) {

		String param[] = new String[1];
		param[0] = id;
		ArrayList temp = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_GET_DRILL_DASHBOARD_VALUES);
			temp = dbUtil.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return temp;
	}

	public ArrayList getReDrillDownDashboardValues(String id) {

		String param[] = new String[1];
		param[0] = id;
		ArrayList temp = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_RE_SPOT_GET_DRILL_DASHBOARD_VALUES);
			temp = dbUtil.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return temp;
	}

	public boolean updateStatus(SpotInspForm spForm) {

		String param[] = new String[1];
		param[0] = spForm.getCompApplNo();
		ArrayList temp = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if (spForm.getStatus().equalsIgnoreCase("R")) {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_RE_SPOT_UPDATE_FLAG_SELECT);
			} else {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_UPDATE_FLAG_SELECT);
			}
			String spotId = dbUtil.executeQry(param);
			param[0] = spotId;
			if (spForm.getStatus().equalsIgnoreCase("R")) {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_RE_SPOT_GET_FLAG);
			} else {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_GET_FLAG);
			}
			ArrayList m = dbUtil.executeQuery(param);

			if (m.size() == 0 || m == null) {
				if (spForm.getStatus().equalsIgnoreCase("R")) {
					dbUtil.createPreparedStatement(CommonSQL.IGRS_RE_SPOT_UPDATE_FLAGS);
				} else {
					dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_UPDATE_FLAGS);
				}
				dbUtil.executeUpdate(param);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	public ArrayList getReInspectionDocument(SpotInspForm spForm) {

		String sql = null;
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();

			if (spForm.getViewType().equalsIgnoreCase("State")) {
				String param[] = new String[2];
				param[0] = spForm.getDurFrom();
				param[1] = spForm.getDurTo();

				sql = CommonSQL.IGRS_RE_GET_FULL_DATA_STATE;
				dbUtil.createPreparedStatement(sql);
				list = dbUtil.executeQuery(param);

			} else if (spForm.getViewType().equalsIgnoreCase("Zone")) {
				String param[] = new String[3];
				param[0] = spForm.getDurFrom();
				param[1] = spForm.getDurTo();
				param[2] = spForm.getZoneId();
				sql = CommonSQL.IGRS_RE_GET_FULL_DATA_ZONE;
				dbUtil.createPreparedStatement(sql);
				list = dbUtil.executeQuery(param);

			}

			else if (spForm.getViewType().equalsIgnoreCase("District")) {
				String param[] = new String[3];
				param[0] = spForm.getDurFrom();
				param[1] = spForm.getDurTo();
				param[2] = spForm.getDistrictId();
				sql = CommonSQL.IGRS_RE_GET_FULL_DATA_DISTRICT;
				dbUtil.createPreparedStatement(sql);
				list = dbUtil.executeQuery(param);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public ArrayList getOfficeList(String districtId, String regId, String officeID) {
		String param[] = new String[1];
		param[0] = districtId;
		String arry[] = null;
		ArrayList data = new ArrayList();
		DBUtility dbUtil = null;
		try {

			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();

			String sql = CommonSQL.GET_OFFICE_TYPE_ID;
			dbUtil.createPreparedStatement(sql);
			String officeTypeID = dbUtil.executeQry(new String[]{officeID});
			String distQuery = "";
			// 4 is DIG and 2 is DR
			if (officeTypeID == null)
				officeTypeID = "";
			if ("2".equalsIgnoreCase(officeTypeID)) {
				distQuery = CommonSQL.GET_DIST_NAME_DR;
				arry = new String[1];
				arry[0] = officeID;
			} else if ("4".equalsIgnoreCase(officeTypeID)) {// FOR SR
				distQuery = CommonSQL.GET_DIST_NAME_DIG;
				arry = new String[2];
				arry[0] = officeID;
				arry[1] = officeID;
			} else {
				distQuery = CommonSQL.GET_DIST_NAME_IG;
				arry = new String[1];
				arry[0] = "5";
			}
			dbUtil.createPreparedStatement(distQuery);

			// }
			ArrayList ret = dbUtil.executeQuery(arry);

			if (ret != null && ret.size() > 0) {

				for (int i = 0; i < ret.size(); i++) {

					ArrayList a = (ArrayList) ret.get(i);
					SpotInspDTO dto = new SpotInspDTO();
					String id = (String) (a.get(0));
					dto.setOffice_id(id + "~" + regId);
					dto.setOffice_name((String) (a.get(1)));
					data.add(dto);
				}

			}
			logger.debug("After initialize DBUtility");
			if (logger.isDebugEnabled()) {
				logger.debug("getDistrict(String) - end");
			}
			/*
			 * 
			 * dbUtil = new DBUtility();
			 * dbUtil.createPreparedStatement(CommonSQL.IGRS_GET_OFFICE_NAMES);
			 * ArrayList ret = dbUtil.executeQuery(param);
			 * 
			 * if(ret!=null && ret.size()>0) {
			 * 
			 * for(int i=0;i<ret.size();i++) {
			 * 
			 * ArrayList a = (ArrayList) ret.get(i); SpotInspDTO dto = new
			 * SpotInspDTO(); String id = (String)(a.get(0));
			 * dto.setOffice_id(id +"~"+regId);
			 * dto.setOffice_name((String)(a.get(1))); data.add(dto); }
			 * 
			 * 
			 * }
			 */} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	public HashMap<String, ArrayList<SpotInspDTO>> getreassignData(ArrayList list2, String userId) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList data = new ArrayList();
		boolean check = true;
		String date = "";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);

			dbUtil.createStatement();
			date = dbUtil.executeQry("select trunc(sysdate) from dual");

			TreeSet<String> users = new TreeSet<String>();
			HashMap<String, ArrayList<SpotInspDTO>> datas = new HashMap<String, ArrayList<SpotInspDTO>>();
			for (int i = 0; i < list2.size(); i++) {
				SpotInspDTO temp = (SpotInspDTO) list2.get(i);
				String ar[] = temp.getSrRoleId().split("~");
				users.add(ar[0]);

			}

			Iterator<String> it = users.iterator();

			while (it.hasNext()) {

				String id = it.next();
				ArrayList<SpotInspDTO> a = new ArrayList<SpotInspDTO>();
				for (int j = 0; j < list2.size(); j++) {
					SpotInspDTO temp = (SpotInspDTO) list2.get(j);
					String tempArray[] = temp.getSrRoleId().split("~");
					String regid = tempArray[0];
					if (regid.equalsIgnoreCase(id)) {
						a.add(temp);

					}

				}

				datas.put(id, a);

			}
			Iterator<String> dataKey = datas.keySet().iterator();

			while (dataKey.hasNext()) {
				if (!check) {
					break;
				}

				String key = dataKey.next();
				String spotId = common.getSequenceNumber("IGRS_SPOT_INS_TXN_ID_SEQ", "ReSpotTxn");

				ArrayList<SpotInspDTO> eachData = datas.get(key);

				for (int n = 0; n < eachData.size(); n++) {
					SpotInspDTO temp = eachData.get(n);
					String param[] = new String[5];
					String param1[] = new String[1];
					param[0] = userId;
					temp.setSpotId(spotId);
					temp.setOrderDate(date);
					String[] ap = temp.getSrRoleId().split("~");

					param[1] = ap[0];
					param1[0] = ap[0];
					temp.setAssginPersonUserId(ap[0]);
					param[2] = common.getSpotMaxDay();
					param[3] = spotId;
					param[4] = temp.getRegIdcompletion();
					logger.debug("Check");
					dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_GET_USER);
					String name = dbUtil.executeQry(param1);
					temp.setReassignPersonName(name);

					param1[0] = temp.getAssignPersonName();
					String srIoName = dbUtil.executeQry(param1);

					temp.setAssignPersonName(srIoName);

					initiateEmail(temp.getRegIdcompletion(), userId, ap[0], spotId, param[2]);
					dbUtil = new DBUtility();
					dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_RE_ISNP_INSERT);
					check = dbUtil.executeUpdate(param);

					if (check && (n == eachData.size() - 1)) {
						String param2[] = new String[8];
						param2[0] = spotId;
						param2[1] = common.getSpotMaxDay();

						param2[2] = ap[0];
						param2[3] = userId;
						param2[4] = temp.getDistrictId();
						param2[5] = temp.getZoneId();
						param2[6] = temp.getDigName();
						param2[7] = temp.getDrName();
						dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_INSERT_RE_TRANSACTION);
						check = dbUtil.executeUpdate(param2);

					}

					if (check) {
						String params[] = new String[1];
						params[0] = temp.getRegIdcompletion();
						dbUtil.createPreparedStatement(CommonSQL.SPOT_RE_FLAG_UPDATE);
						dbUtil.executeUpdate(params);
					}

					if (!check) {
						break;
					}

				}

			}

			if (check) {

				dbUtil.commit();
				return datas;
			}

			else {
				dbUtil.rollback();
				logger.debug(" Not Done");
				return null;
			}

		} catch (Exception e) {
			dbUtil.rollback();
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();
		}

		return null;
	}

	public ArrayList getSRORoleList(String office_id) {
		ArrayList data = null;
		String param[] = new String[1];
		String temp[] = office_id.split("~");
		param[0] = temp[0];
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_SPOT_GET_RE_INPECTION_OFFICERS);
			data = dbUtil.executeQuery(param);

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
		return data;
	}

	public String getPercentageValue() {
		String percentage = "";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			percentage = dbUtil.executeQry(CommonSQL.IGRS_SPOT_GET_PERCENTAGE);
			if (percentage == null) {
				percentage = "";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return percentage;
	}

	public ArrayList<SpotInspDTO> getData(String regId) {
		ArrayList<SpotInspDTO> finalList = new ArrayList<SpotInspDTO>();
		DBUtility dbUtil = null;
		try {
			String param[] = new String[1];
			param[0] = regId;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_SELECT_PROPERTY_ID);
			ArrayList data = dbUtil.executeQuery(param);
			if (data != null & data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					SpotInspDTO d = new SpotInspDTO();
					ArrayList datas = (ArrayList) data.get(i);
					String prop_type = (String) datas.get(1);
					String prop_id = (String) datas.get(0);
					String paramProp[] = new String[1];
					paramProp[0] = prop_id;
					if (prop_type.equalsIgnoreCase("2")) {

						dbUtil.createPreparedStatement(CommonSQL.GET_FLOOR_DATA);
						ArrayList list = dbUtil.executeQuery(paramProp);
						ArrayList<SpotInspDTO> floorList = new ArrayList<SpotInspDTO>();

						for (int j = 0; j < list.size(); j++) {
							SpotInspDTO dto = new SpotInspDTO();
							ArrayList values = (ArrayList) list.get(j);
							dto.setNewArea((String) values.get(3));
							dto.setOldArea((String) values.get(3));
							dto.setNewConstructedArea((String) values.get(2));
							dto.setOldConstructedArea((String) values.get(2));
							dto.setNewTypeOfConstruction((String) values.get(1));
							dto.setOldTypeOfConstruction((String) values.get(1));
							dto.setNewPropertyUse((String) values.get(0));
							dto.setOldPropertyUse((String) values.get(0));
							floorList.add(dto);
						}

						dbUtil.createPreparedStatement(CommonSQL.SELECT_MARKET_VALUE);
						ArrayList ds = dbUtil.executeQuery(paramProp);
						ArrayList e = (ArrayList) ds.get(0);
						String market_Value = (String) e.get(0);
						String reg_id = (String) e.get(1);
						d.setOldMarketValue(market_Value);
						d.setNewMarketValue(market_Value);
						d.setNewguidelineValue(getGuidelineRate(paramProp, "2"));
						d.setOldguidelineValue(getGuidelineRate(paramProp, "2"));
						d.setFloorList(floorList);
						d.setPropId(prop_id);
						d.setPropertyId(prop_type);
						d.setPropertyname(getPropertyName(prop_type));
						d.setRegId(reg_id);
						finalList.add(d);

					} else {
						dbUtil.createPreparedStatement(CommonSQL.SELECT_WITHOUT_FLOOR_DETAILS);
						ArrayList po = dbUtil.executeQuery(paramProp);
						ArrayList<SpotInspDTO> floorList = new ArrayList<SpotInspDTO>();

						if (po != null && po.size() > 0) {
							ArrayList temp = (ArrayList) po.get(0);
							SpotInspDTO a = new SpotInspDTO();
							a.setNewArea((String) temp.get(4));
							a.setNewConstructedArea("N/A");
							a.setOldConstructedArea("N/A");
							a.setOldArea((String) temp.get(4));
							String data1 = (String) temp.get(0);
							String data2 = (String) temp.get(1);
							if (data1 != null) {
								a.setOldTypeOfConstruction((String) temp.get(0));
								a.setNewTypeOfConstruction((String) temp.get(0));
							} else {
								a.setOldTypeOfConstruction("N/A");
								a.setNewTypeOfConstruction("N/A");
							}

							if (data2 != null) {
								a.setOldPropertyUse((String) temp.get(1));
								a.setNewPropertyUse((String) temp.get(1));
							} else {
								a.setOldPropertyUse("N/A");
								a.setNewPropertyUse("N/A");
							}
							floorList.add(a);

							d.setOldTypeOfConstruction((String) temp.get(1));
							d.setNewTypeOfConstruction((String) temp.get(1));
							d.setOldPropertyUse((String) temp.get(0));
							d.setNewPropertyUse((String) temp.get(0));
							d.setOldMarketValue((String) temp.get(2));
							d.setNewMarketValue((String) temp.get(2));
							d.setOldguidelineValue(getGuidelineRate(paramProp, "1"));
							d.setNewguidelineValue(getGuidelineRate(paramProp, "1"));
							d.setPropId(prop_id);
							d.setPropertyId(prop_type);
							d.setPropertyname(getPropertyName(prop_type));
							d.setRegId((String) temp.get(3));
						}
						d.setFloorList(floorList);
						finalList.add(d);

					}

				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.debug(e.toString());
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Integer> unsortList = new ArrayList<Integer>();
		for (int y = 0; y < finalList.size(); y++) {
			SpotInspDTO dto = finalList.get(y);
			Integer i = dto.getFloorList().size();
			unsortList.add(i);

		}
		Collections.sort(unsortList);
		Collections.reverse(unsortList);
		System.out.println(unsortList.toString());
		ArrayList<SpotInspDTO> finalSortedList = new ArrayList<SpotInspDTO>();
		for (int i = 0; i < unsortList.size(); i++) {
			int size = unsortList.get(i);
			for (int j = 0; j < finalList.size(); j++) {
				SpotInspDTO dto = finalList.get(j);
				int floorSize = dto.getFloorList().size();
				if (floorSize == size && dto.getSortCheck().equalsIgnoreCase("Y")) {
					finalSortedList.add(dto);
					dto.setSortCheck("N");

				}

			}

		}

		return finalSortedList;
	}

	public ArrayList<SpotInspDTO> getDataRe(String regId) {
		DBUtility dbUtil = null;
		ArrayList<SpotInspDTO> finalList = new ArrayList<SpotInspDTO>();
		try {
			String param[] = new String[1];
			param[0] = regId;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_SELECT_PROPERTY_ID);
			ArrayList data = dbUtil.executeQuery(param);
			if (data != null & data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					SpotInspDTO d = new SpotInspDTO();
					ArrayList datas = (ArrayList) data.get(i);
					String prop_type = (String) datas.get(1);
					String prop_id = (String) datas.get(0);
					String paramProp[] = new String[1];
					paramProp[0] = prop_id;
					if (prop_type.equalsIgnoreCase("2")) {

						dbUtil.createPreparedStatement(CommonSQL.GET_FLOOR_RE_DATA);
						ArrayList list = dbUtil.executeQuery(paramProp);
						ArrayList<SpotInspDTO> floorList = new ArrayList<SpotInspDTO>();

						for (int j = 0; j < list.size(); j++) {
							SpotInspDTO dto = new SpotInspDTO();
							ArrayList values = (ArrayList) list.get(j);
							dto.setCrossArea((String) values.get(7));
							dto.setNewArea((String) values.get(7));
							dto.setOldArea((String) values.get(6));

							dto.setCrossConstructedArea((String) values.get(5));
							dto.setNewConstructedArea((String) values.get(5));
							dto.setOldConstructedArea((String) values.get(4));

							dto.setCrossTypeOfConstruction((String) values.get(3));
							dto.setNewTypeOfConstruction((String) values.get(3));
							dto.setOldTypeOfConstruction((String) values.get(1));

							dto.setCrossPropertyUse((String) values.get(2));
							dto.setNewPropertyUse((String) values.get(2));
							dto.setOldPropertyUse((String) values.get(0));
							floorList.add(dto);
						}

						dbUtil.createPreparedStatement(CommonSQL.SELECT_RE_MARKET_VALUE);
						ArrayList ds = dbUtil.executeQuery(paramProp);
						ArrayList e = (ArrayList) ds.get(0);
						String market_Value = (String) e.get(0);
						String reg_id = (String) e.get(1);

						d.setOldMarketValue((String) e.get(0));
						d.setNewMarketValue((String) e.get(1));
						d.setCrossMarketValue((String) e.get(1));
						d.setNewguidelineValue((String) e.get(3));
						d.setOldguidelineValue((String) e.get(2));
						d.setCrossGuideLineValue((String) e.get(3));
						d.setFloorList(floorList);
						d.setPropId(prop_id);
						d.setPropertyId(prop_type);
						d.setPropertyname(getPropertyName(prop_type));
						d.setRegId((String) e.get(4));
						finalList.add(d);

					} else {
						dbUtil.createPreparedStatement(CommonSQL.SELECT_RE_WITHOUT_FLOOR_DETAILS);
						ArrayList po = dbUtil.executeQuery(paramProp);
						ArrayList<SpotInspDTO> floorList = new ArrayList<SpotInspDTO>();

						if (po != null && po.size() > 0) {
							ArrayList temp = (ArrayList) po.get(0);
							SpotInspDTO a = new SpotInspDTO();
							a.setOldTypeOfConstruction((String) temp.get(0));
							a.setNewTypeOfConstruction((String) temp.get(2));
							a.setCrossTypeOfConstruction((String) temp.get(2));

							a.setOldPropertyUse((String) temp.get(1));
							a.setNewPropertyUse((String) temp.get(3));
							a.setCrossPropertyUse((String) temp.get(3));
							a.setOldArea((String) temp.get(5));
							a.setNewArea((String) temp.get(6));
							a.setCrossArea((String) temp.get(6));
							floorList.add(a);

							dbUtil.createPreparedStatement(CommonSQL.SPOT_GET_RE_CALCULATION);
							ArrayList ds1 = dbUtil.executeQuery(paramProp);
							ArrayList es = (ArrayList) ds1.get(0);

							d.setOldMarketValue((String) es.get(0));
							d.setNewMarketValue((String) es.get(1));
							d.setCrossMarketValue((String) es.get(1));

							d.setOldguidelineValue((String) es.get(2));
							d.setNewguidelineValue((String) es.get(3));
							d.setCrossGuideLineValue((String) es.get(3));
							d.setPropId(prop_id);
							d.setPropertyId(prop_type);
							d.setPropertyname(getPropertyName(prop_type));

							d.setRegId((String) temp.get(4));
						}
						d.setFloorList(floorList);
						finalList.add(d);

					}

				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			logger.debug(e.toString());
		}
		List<Integer> unsortList = new ArrayList<Integer>();
		for (int y = 0; y < finalList.size(); y++) {
			SpotInspDTO dto = finalList.get(y);
			Integer i = dto.getFloorList().size();
			unsortList.add(i);

		}
		Collections.sort(unsortList);
		Collections.reverse(unsortList);
		System.out.println(unsortList.toString());
		ArrayList<SpotInspDTO> finalSortedList = new ArrayList<SpotInspDTO>();
		for (int i = 0; i < unsortList.size(); i++) {
			int size = unsortList.get(i);
			for (int j = 0; j < finalList.size(); j++) {
				SpotInspDTO dto = finalList.get(j);
				int floorSize = dto.getFloorList().size();
				if (floorSize == size && dto.getSortCheck().equalsIgnoreCase("Y")) {
					finalSortedList.add(dto);
					dto.setSortCheck("N");

				}

			}

		}
		return finalSortedList;
	}

	public String getPropertyName(String id) {
		if (id.equalsIgnoreCase("1")) {
			return "PLOT";
		} else if (id.equalsIgnoreCase("2")) {
			return "Building";
		} else {
			return "Agri Land";
		}
	}

	public static void main(String[] args) {

		SpotInspDAO y = new SpotInspDAO();
		y.getDataRe("180713000635");

	}

	public String getSelectPropertyName(String propId, String langauge) {
		String[] propIds = propId.split("~");
		DBUtility dbUtil = null;
		if (propIds.length == 1) {
			try {
				dbUtil = new DBUtility();
				if (langauge.equalsIgnoreCase("en"))
					dbUtil.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_SINGLE);
				else
					dbUtil.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_SINGLE_HI);
				String name = dbUtil.executeQry(new String[]{propId});
				dbUtil.closeConnection();
				return name;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				dbUtil = new DBUtility();
				if (langauge.equalsIgnoreCase("en"))
					dbUtil.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_THREE);
				else
					dbUtil.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_THREE_HI);
				String name = dbUtil.executeQry(new String[]{propIds[1], propIds[0], propIds[2]});
				dbUtil.closeConnection();
				return name;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getPropIdSearch(String l1) {
		DBUtility dbUtil = null;
		String id[] = l1.split("~");
		if (id.length == 1) {
			try {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(CommonSQL.GET_PROPERTY_TYPE);
				String name = dbUtil.executeQry(new String[]{l1});
				dbUtil.closeConnection();
				return name;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			return id[0];
		}
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList getTempDataPlot(SpotInspForm spForm) {

		SQL = CommonSQL.SEARCH_PLOT;
		ArrayList list = new ArrayList();
		ArrayList finalList = new ArrayList();
		StringBuilder squery = new StringBuilder(SQL);
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("A1", spForm.getDeedId());
		paramsMap.put("A2", spForm.getRangeId());
		paramsMap.put("A3", spForm.getRangeId());
		paramsMap.put("A4", spForm.getDurFrom()+" 00:00:00");
		paramsMap.put("A5", spForm.getDurTo()+" 23:59:59");
		//added by saurav for office check
		paramsMap.put("A6", spForm.getLoggedInOffice());
		String[] values = spForm.getSelectedClauses();

		for (int i = 0; i < values.length; i++) {
			if (!values[0].equalsIgnoreCase("-1")) {
				if (values.length == 1) {
					squery.append(" and sub.SUB_CLAUSE_ID IN(?)");
					paramsMap.put("B" + (i + 1), values[i]);
				} else {

					if (i == 0)

					{
						squery.append(" and sub.SUB_CLAUSE_ID IN(?,");
						paramsMap.put("B" + (i + 1), values[i]);
					} else if (i != values.length - 1) {
						squery.append("?,");
						paramsMap.put("B" + (i + 1), values[i]);
					} else {
						squery.append("?)");
						paramsMap.put("B" + (i + 1), values[i]);
					}

				}
			}
		}
		int l1 = Integer.parseInt(spForm.getPropertyId());// l1 id
		switch (l1) {
			case 1 :
				squery.append(" and ((plot.RESIDENTIAL_AREA is not null) and (plot.RESIDENTIAL_AREA!=0))");
				break;
			case 2 :
				squery.append(" and ((plot.COMMERCIAL_AREA is not null) and (plot.COMMERCIAL_AREA!=0))");
				break;
			case 3 :
				squery.append(" and ((plot.INDUSTRIAL_AREA is not null) and (plot.INDUSTRIAL_AREA!=0))");
				break;
			case 4 :
				squery.append(" and ((plot.HEALTH_AREA is not null) and (plot.HEALTH_AREA!=0))");
				break;
			case 5 :
				squery.append(" and ((plot.EDUCATION_AREA is not null) and (plot.EDUCATION_AREA!=0))");
				break;
			case 6 :
				squery.append(" and ((plot.OTHER_AREA is not null) and (plot.OTHER_AREA!=0))");
				break;
			case 33 :
				squery.append(" and ((plot.RESICUMM_AREA is not null) and (plot.RESICUMM_AREA!=0))");
				break;
		}
		if (spForm.getQuestion().equalsIgnoreCase("N")) {
			if (spForm.getViewType().equals("State")) {

			} else if (spForm.getViewType().equals("Zone")) {
				squery.append("  AND reg.REGISTRATION_ZONE_ID =?");
				paramsMap.put("C1", spForm.getZoneId());//
			} else {
				squery.append("  AND reg.REGISTRATION_ZONE_ID =? and reg.REGISTRATION_DIST_ID=?");
				paramsMap.put("C1", spForm.getZoneId());
				paramsMap.put("C2", spForm.getDistrictId());
			}

		} else {
			if (!spForm.getZoneId().equalsIgnoreCase("-1")) {
				squery.append("  AND reg.REGISTRATION_ZONE_ID =?");
				paramsMap.put("C1", spForm.getZoneId());
				if (!spForm.getDistrictId().equalsIgnoreCase("-1")) {
					squery.append("  AND reg.REGISTRATION_DIST_ID =?");
					paramsMap.put("C2", spForm.getDistrictId());
					if (!spForm.getTehsilId().equalsIgnoreCase("-1")) {
						squery.append("  AND reginit.tehsil_id        =?");
						paramsMap.put("C3", spForm.getTehsilId());
						if (!spForm.getAreaTypeId().equalsIgnoreCase("-1")) {
							squery.append(" AND reginit.AREA_TYPE_ID     = ?");
							paramsMap.put("C4", spForm.getAreaTypeId());
							if (!spForm.getSubAreaId().equalsIgnoreCase("-1")) {
								squery.append("  AND reginit.GOV_BODY_ID        = ?");
								paramsMap.put("C5", spForm.getSubAreaId());
								if (!spForm.getWardIds().equalsIgnoreCase("-1")) {
									squery.append("  AND reginit.WARD_ID =?");
									paramsMap.put("C6", spForm.getWardIds().split("~")[0]);
									if (!spForm.getColonyId().equalsIgnoreCase("-1")) {
										squery.append("  AND reginit.MOHALLA_ID =?");
										paramsMap.put("C7", spForm.getColonyId().split("~")[0]);
									}
								}
							}
						}

					}

				}
			}

		}
		Collection<String> values1 = paramsMap.values();
		ArrayList<String> tmp = new ArrayList(values1);
		String[] params = tmp.toArray(new String[]{});
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			try {
				emptyTemp();
			} catch (Exception e) {
				logger.debug("Error : " + e);
			}

			dbUtil.createPreparedStatement(squery.toString());
			list = dbUtil.executeQuery(params);
			if (list.isEmpty() || list.size() == 0 || list.size() < 0) {

				return null;

			}

			if (spForm.getQuestion().equalsIgnoreCase("Y")) {
				String userData[] = new String[6];
				userData[0] = getPropertyId(spForm.getPropertyId());
				userData[1] = spForm.getTehsilId();
				userData[2] = spForm.getAreaTypeId();
				userData[3] = spForm.getColonyId().split("~")[0];
				userData[4] = spForm.getWardIds().split("~")[0];
				userData[5] = spForm.getSubAreaId();

				if (list != null && list.size() > 0) {
					String parameterTemp = "";
					String parameterFinal = "";
					ArrayList temp = null;
					boolean check = false;
					for (int i = 0; i < list.size(); i++) {
						temp = (ArrayList) list.get(i);
						parameterTemp = temp.toString();
						if (parameterTemp != null) {
							parameterFinal = parameterTemp.substring(1, parameterTemp.length() - 1);
						} else {
							parameterFinal = "";
						}
						String add[] = parameterFinal.split(",");
						String finals[] = (String[]) ArrayUtils.addAll(add, userData);
						check = addTempDetails(finals);
						if (!check) {
							boo = false;
							break;
						}

					}

				}

			} else if (spForm.getQuestion().equalsIgnoreCase("N")) {
				String userData[] = new String[1];
				userData[0] = getPropertyId(spForm.getPropertyId());

				if (list != null && list.size() != 0) {
					String parameterTemp = "";
					String parameterFinal = "";
					ArrayList temp = null;
					boolean check = false;
					{
						check = false;
					}
					for (int i = 0; i < list.size(); i++) {
						temp = (ArrayList) list.get(i);
						parameterTemp = temp.toString();
						if (parameterTemp != null) {
							parameterFinal = parameterTemp.substring(1, parameterTemp.length() - 1);
						} else {
							parameterFinal = "";
						}

						String add[] = parameterFinal.split(",");
						String finals[] = (String[]) ArrayUtils.addAll(add, userData);
						check = addTempDetailsNo(finals);
						if (!check) {
							boo = false;
							boo = false;
							break;
						}

					}

				}

			}
			if (spForm.getQuestion().equalsIgnoreCase("Y")) {
				if (boo) {
					HashMap category = new HashMap();
					String districts[] = getDistricts();

					for (int i = 0; i < districts.length; i++) {
						String district[] = districts[i].split("~");
						String number = district[0];
						list = getDistrictInfoListsYes(number, spForm.getLanguage());
						category.put(districts[i], list);

					}
					spForm.setFinalValues(category); // setting hasmap values to
					// form
					System.out.println("Hashmap Size is : " + category.size());

				}

				dbUtil.createStatement();
				ArrayList finallist = null;
				if (spForm.getLanguage().equalsIgnoreCase("en"))
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY);
				else
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY_HINDI);
				// emptyTemp();

				return finallist;
			} else if (spForm.getQuestion().equalsIgnoreCase("N")) {
				if (boo) {
					HashMap category = new HashMap();
					String districts[] = getDistricts();

					for (int i = 0; i < districts.length; i++) {
						String district[] = districts[i].split("~");
						String number = district[0];
						list = getDistrictInfoListsNo(number, spForm.getLanguage()); //CHANGES MADE IN THE METHOD BY SAURAV
						category.put(districts[i], list);

					}
					spForm.setFinalValues(category); // setting hasmap values to
					// form
					System.out.println("Hashmap Size is : " + category.size());

				}

				dbUtil.createStatement();
				ArrayList finallist = null;
				if (spForm.getLanguage().equalsIgnoreCase("en"))
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY);
				else
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY_HINDI);

				// emptyTemp();
				return finallist;
			}
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
		return null;
	}

	public ArrayList getTempDataAgri(SpotInspForm spForm) {

		SQL = CommonSQL.SEARCH_AGRI;
		ArrayList list = new ArrayList();
		ArrayList finalList = new ArrayList();
		StringBuilder squery = new StringBuilder(SQL);
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("A1", spForm.getDeedId());
		paramsMap.put("A2", spForm.getRangeId());
		paramsMap.put("A3", spForm.getRangeId());
		paramsMap.put("A4", spForm.getDurFrom()+" 00:00:00");
		paramsMap.put("A5", spForm.getDurTo()+" 23:59:59");
		//added by saurav for office check
		paramsMap.put("A6", spForm.getLoggedInOffice());
		String[] values = spForm.getSelectedClauses();

		for (int i = 0; i < values.length; i++) {
			if (!values[0].equalsIgnoreCase("-1")) {
				if (i == 0)

				{
					squery.append(" and sub.SUB_CLAUSE_ID IN(?,");
					paramsMap.put("B" + (i + 1), values[i]);
				} else if (i != values.length - 1) {
					squery.append("?,");
					paramsMap.put("B" + (i + 1), values[i]);
				} else {
					squery.append("?)");
					paramsMap.put("B" + (i + 1), values[i]);
				}
			}
		}

		String propId[] = spForm.getPropertyId().split("~");

		int l1 = 0;
		if (propId.length == 1) {
			l1 = Integer.parseInt(spForm.getPropertyId());// l1 id
		} else {
			l1 = Integer.parseInt(propId[2]);
		}
		switch (l1) {
			case 1 :
				squery.append(" and ((agri.RESIDENTIAL_AREA is not null) and (agri.RESIDENTIAL_AREA!=0))");
				if (propId.length > 1) {
					squery.append("and agri.AGRI_SUB_TYPE_ID='" + propId[1] + "' ");
				}
				break;
			case 2 :
				squery.append(" and ((agri.COMMERCIAL_AREA is not null) and (agri.COMMERCIAL_AREA!=0))");
				if (propId.length > 1) {
					squery.append("and agri.AGRI_SUB_TYPE_ID='" + propId[1] + "' ");
				}
				break;
			case 3 :
				squery.append(" and ((agri.INDUSTRIAL_AREA is not null) and (agri.INDUSTRIAL_AREA!=0))");
				if (propId.length > 1) {
					squery.append("and agri.AGRI_SUB_TYPE_ID='" + propId[1] + "' ");
				}

				break;
			case 4 :
				squery.append(" and ((agri.HEALTH_AREA is not null) and (agri.HEALTH_AREA!=0))");
				if (propId.length > 1) {
					squery.append("and agri.AGRI_SUB_TYPE_ID='" + propId[1] + "' ");
				}

				break;
			case 5 :
				squery.append(" and ((agri.EDUCATION_AREA is not null) and (agri.EDUCATION_AREA!=0))");
				if (propId.length > 1) {
					squery.append("and agri.AGRI_SUB_TYPE_ID='" + propId[1] + "' ");
				}
				break;
			case 6 :
				squery.append(" and ((agri.OTHER_AREA is not null) and (agri.OTHER_AREA!=0))");
				if (propId.length > 1) {
					squery.append("and agri.AGRI_SUB_TYPE_ID='" + propId[1] + "' ");
				}

				break;
			case 20 :
				squery.append(" and ((agri.IRRIGATED_AREA is not null) and (agri.IRRIGATED_AREA!=0))");
				if (propId.length > 1) {
					squery.append("and agri.AGRI_SUB_TYPE_ID='" + propId[1] + "' ");
				}

				break;
			case 23 :
				squery.append(" and ((agri.UNIRRIGATED_1_CROP_AREA is not null) and (agri.UNIRRIGATED_1_CROP_AREA!=0))");
				if (propId.length > 1) {
					squery.append("and agri.AGRI_SUB_TYPE_ID='" + propId[1] + "' ");
				}

				break;
			case 24 :
				squery.append(" and ((agri.UNIRRIGATED_2_CROP_AREA is not null) and (agri.UNIRRIGATED_2_CROP_AREA!=0))");
				if (propId.length > 1) {
					squery.append("and agri.AGRI_SUB_TYPE_ID='" + propId[1] + "' ");
				}

				break;
		}
		if (spForm.getQuestion().equalsIgnoreCase("N")) {
			if (spForm.getViewType().equals("State")) {

			} else if (spForm.getViewType().equals("Zone")) {
				squery.append("  AND reg.REGISTRATION_ZONE_ID =?");
				paramsMap.put("C1", spForm.getZoneId());//
			} else {
				squery.append("  AND reg.REGISTRATION_ZONE_ID =? and reg.REGISTRATION_DIST_ID=?");
				paramsMap.put("C1", spForm.getZoneId());
				paramsMap.put("C2", spForm.getDistrictId());
			}

		} else {
			if (!spForm.getZoneId().equalsIgnoreCase("-1")) {
				squery.append("  AND reg.REGISTRATION_ZONE_ID =?");
				paramsMap.put("C1", spForm.getZoneId());
				if (!spForm.getDistrictId().equalsIgnoreCase("-1")) {
					squery.append("  AND reg.REGISTRATION_DIST_ID =?");
					paramsMap.put("C2", spForm.getDistrictId());
					if (!spForm.getTehsilId().equalsIgnoreCase("-1")) {
						squery.append("  AND reginit.tehsil_id        =?");
						paramsMap.put("C3", spForm.getTehsilId());
						if (!spForm.getAreaTypeId().equalsIgnoreCase("-1")) {
							squery.append(" AND reginit.AREA_TYPE_ID     = ?");
							paramsMap.put("C4", spForm.getAreaTypeId());
							if (!spForm.getSubAreaId().equalsIgnoreCase("-1")) {
								squery.append("  AND reginit.GOV_BODY_ID        = ?");
								paramsMap.put("C5", spForm.getSubAreaId());
								if (!spForm.getWardIds().equalsIgnoreCase("-1")) {
									squery.append("  AND reginit.WARD_ID =?");
									paramsMap.put("C6", spForm.getWardIds().split("~")[0]);
									if (!spForm.getColonyId().equalsIgnoreCase("-1")) {
										squery.append("  AND reginit.MOHALLA_ID =?");
										paramsMap.put("C7", spForm.getColonyId().split("~")[0]);
									}
								}
							}
						}

					}

				}
			}

		}
		Collection<String> values1 = paramsMap.values();
		ArrayList<String> tmp = new ArrayList(values1);
		String[] params = tmp.toArray(new String[]{});
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			try {
				emptyTemp();
			} catch (Exception e) {
				logger.debug("Error : " + e);
			}

			dbUtil.createPreparedStatement(squery.toString());
			list = dbUtil.executeQuery(params);
			if (list.isEmpty() || list.size() == 0 || list.size() < 0) {

				return null;

			}

			if (spForm.getQuestion().equalsIgnoreCase("Y")) {
				String userData[] = new String[6];
				userData[0] = getPropertyId(spForm.getPropertyId());
				userData[1] = spForm.getTehsilId();
				userData[2] = spForm.getAreaTypeId();
				userData[3] = spForm.getColonyId().split("~")[0];
				userData[4] = spForm.getWardIds().split("~")[0];
				userData[5] = spForm.getSubAreaId();
				if (list != null && list.size() > 0) {
					String parameterTemp = "";
					String parameterFinal = "";
					ArrayList temp = null;
					boolean check = false;
					for (int i = 0; i < list.size(); i++) {
						temp = (ArrayList) list.get(i);
						parameterTemp = temp.toString();
						if (parameterTemp != null) {
							parameterFinal = parameterTemp.substring(1, parameterTemp.length() - 1);
						} else {
							parameterFinal = "";
						}
						String add[] = parameterFinal.split(",");
						String finals[] = (String[]) ArrayUtils.addAll(add, userData);
						check = addTempDetails(finals);
						if (!check) {
							boo = false;
							break;
						}

					}

				}

			} else if (spForm.getQuestion().equalsIgnoreCase("N")) {
				String userData[] = new String[1];
				userData[0] = getPropertyId(spForm.getPropertyId().split("~")[0]);

				if (list != null && list.size() != 0) {
					String parameterTemp = "";
					String parameterFinal = "";
					ArrayList temp = null;
					boolean check = false;
					{
						check = false;
					}
					for (int i = 0; i < list.size(); i++) {
						temp = (ArrayList) list.get(i);
						parameterTemp = temp.toString();
						if (parameterTemp != null) {
							parameterFinal = parameterTemp.substring(1, parameterTemp.length() - 1);
						} else {
							parameterFinal = "";
						}

						String add[] = parameterFinal.split(",");
						String finals[] = (String[]) ArrayUtils.addAll(add, userData);
						check = addTempDetailsNo(finals);
						if (!check) {
							boo = false;
							boo = false;
							break;
						}

					}

				}

			}
			if (spForm.getQuestion().equalsIgnoreCase("Y")) {
				if (boo) {
					HashMap category = new HashMap();
					String districts[] = getDistricts();

					for (int i = 0; i < districts.length; i++) {
						String district[] = districts[i].split("~");
						String number = district[0];
						list = getDistrictInfoListsYes(number, spForm.getLanguage());
						category.put(districts[i], list);

					}
					spForm.setFinalValues(category); // setting hasmap values to
					// form
					System.out.println("Hashmap Size is : " + category.size());

				}

				dbUtil.createStatement();
				ArrayList finallist = null;
				if (spForm.getLanguage().equalsIgnoreCase("en"))
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY);
				else
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY_HINDI);
				// emptyTemp();

				return finallist;
			} else if (spForm.getQuestion().equalsIgnoreCase("N")) {
				if (boo) {
					HashMap category = new HashMap();
					String districts[] = getDistricts();

					for (int i = 0; i < districts.length; i++) {
						String district[] = districts[i].split("~");
						String number = district[0];
						list = getDistrictInfoListsNo(number, spForm.getLanguage());
						category.put(districts[i], list);

					}
					spForm.setFinalValues(category); // setting hasmap values to
					// form
					System.out.println("Hashmap Size is : " + category.size());

				}

				dbUtil.createStatement();
				ArrayList finallist = null;
				if (spForm.getLanguage().equalsIgnoreCase("en"))
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY);
				else
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY_HINDI);

				// emptyTemp();
				return finallist;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList getTempDataBuilding(SpotInspForm spForm) {

		SQL = CommonSQL.SEARCH_BUILD;
		ArrayList list = new ArrayList();
		ArrayList finalList = new ArrayList();
		StringBuilder squery = new StringBuilder(SQL);

		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("A1", spForm.getDeedId());
		paramsMap.put("A2", spForm.getRangeId());
		paramsMap.put("A3", spForm.getRangeId());
		paramsMap.put("A4", spForm.getDurFrom()+" 00:00:00");
		paramsMap.put("A5", spForm.getDurTo()+" 23:59:59");
		//ADDED BY SAURAV
		paramsMap.put("A6", spForm.getLoggedInOffice());
		String[] values = spForm.getSelectedClauses();

		String propId[] = spForm.getPropertyId().split("~");

		int l1 = 0;
		if (propId.length == 1) {
			l1 = Integer.parseInt(spForm.getPropertyId());// l1 id
		} else {
			l1 = Integer.parseInt(propId[2]);
		}
		String l2 = spForm.getPropertyL2Id();

		int l2Id = 0;
		if (l2 != null && !l2.equalsIgnoreCase("")) {
			l2Id = Integer.parseInt(l2);
		}
		switch (l1) {
			case 1 :
				squery.append(CommonSQL.SEARCH_BUILD1 + " and ((build.FLOOR_AREA is not null) and (build.FLOOR_AREA!=0))");
				squery.append("and build.BUILDING_SUB_TYPE_ID='" + propId[1] + "' ");
				break;
			case 7 :
				if (propId.length > 1) {
					squery.append(CommonSQL.SEARCH_BUILD1 + " and ((build.FLOOR_AREA is not null) and (build.FLOOR_AREA!=0))");
					squery.append("and build.BUILDING_SUB_TYPE_ID='" + propId[1] + "' ");
				} else {

					squery.append(",IGRS_FLOOR_TXN_DETLS txn,IGRS_FLOOR_AREA_DETLS area" + CommonSQL.SEARCH_BUILD1);
					squery.append(" and ((build.RESIDENTIAL_AREA is not null) and (build.RESIDENTIAL_AREA!=0))");
					switch (l2Id) {
						case 32 :
							squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 33 :
							squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 34 :
							squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 35 :
							squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 36 :
							squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 37 :
							squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 38 :
							squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 1 :
							squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 2 :
							squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 3 :
							squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 4 :
							squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 5 :
							squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 6 :
							squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 7 :
							squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 10 :
							squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 11 :
							squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 12 :
							squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 13 :
							squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 14 :
							squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 15 :
							squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 16 :
							squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 17 :
							squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 18 :
							squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 19 :
							squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 20 :
							squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 21 :
							squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 22 :
							squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 23 :
							squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 24 :
							squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

						case 25 :
							squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
							break;

					}

				}
				break;
			case 8 :

				squery.append(",IGRS_FLOOR_TXN_DETLS txn,IGRS_FLOOR_AREA_DETLS area" + CommonSQL.SEARCH_BUILD1);
				squery.append(" and ((build.RESIDENTIAL_AREA is not null) and (build.RESIDENTIAL_AREA!=0))");
				switch (l2Id) {
					case 32 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 33 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 34 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 35 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 36 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 37 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 38 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 1 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 2 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 3 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 4 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 5 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 6 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 7 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 10 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 11 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 12 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 13 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 14 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 15 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 16 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 17 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 18 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 19 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 20 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 21 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 22 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 23 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 24 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 25 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

				}
				break;
			case 10 :

				squery.append(",IGRS_FLOOR_TXN_DETLS txn,IGRS_FLOOR_AREA_DETLS area" + CommonSQL.SEARCH_BUILD1);
				squery.append(" and ((build.RESIDENTIAL_AREA is not null) and (build.RESIDENTIAL_AREA!=0))");
				switch (l2Id) {
					case 32 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 33 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 34 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 35 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 36 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 37 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 38 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 1 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 2 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 3 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 4 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 5 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 6 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 7 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 10 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 11 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 12 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 13 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 14 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 15 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 16 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 17 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 18 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 19 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 20 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 21 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 22 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 23 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 24 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 25 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

				}

				break;
			case 11 :

				squery.append(",IGRS_FLOOR_TXN_DETLS txn,IGRS_FLOOR_AREA_DETLS area" + CommonSQL.SEARCH_BUILD1);
				squery.append(" and ((build.RESIDENTIAL_AREA is not null) and (build.RESIDENTIAL_AREA!=0))");
				switch (l2Id) {
					case 32 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 33 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 34 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 35 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 36 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 37 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 38 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 1 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 2 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 3 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 4 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 5 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 6 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 7 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 10 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 11 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 12 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 13 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 14 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 15 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 16 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 17 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 18 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 19 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 20 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 21 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 22 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 23 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 24 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 25 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

				}

				break;
			case 12 :

				squery.append(",IGRS_FLOOR_TXN_DETLS txn,IGRS_FLOOR_AREA_DETLS area" + CommonSQL.SEARCH_BUILD1);
				squery.append(" and ((build.RESIDENTIAL_AREA is not null) and (build.RESIDENTIAL_AREA!=0))");
				switch (l2Id) {
					case 32 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 33 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 34 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 35 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 36 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 37 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 38 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 1 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 2 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 3 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 4 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 5 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 6 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 7 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 10 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 11 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 12 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 13 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 14 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 15 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 16 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 17 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 18 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 19 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 20 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 21 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 22 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 23 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 24 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 25 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

				}
				break;
			case 13 :

				squery.append(",IGRS_FLOOR_TXN_DETLS txn,IGRS_FLOOR_AREA_DETLS area" + CommonSQL.SEARCH_BUILD1);
				squery.append(" and ((build.RESIDENTIAL_AREA is not null) and (build.RESIDENTIAL_AREA!=0))");
				switch (l2Id) {
					case 32 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 33 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 34 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 35 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 36 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 37 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 38 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 1 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 2 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 3 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 4 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 5 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 6 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 7 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 10 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 11 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 12 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 13 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 14 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 15 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 16 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 17 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 18 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 19 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 20 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 21 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 22 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 23 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 24 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 25 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

				}
				break;
			case 32 :

				squery.append(",IGRS_FLOOR_TXN_DETLS txn,IGRS_FLOOR_AREA_DETLS area" + CommonSQL.SEARCH_BUILD1);
				squery.append(" and ((build.RESIDENTIAL_AREA is not null) and (build.RESIDENTIAL_AREA!=0))");
				switch (l2Id) {
					case 32 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 33 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 34 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 35 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 36 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 37 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 38 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 1 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 2 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 3 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 4 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 5 :
						squery.append(" and ((area.SHOP_AREA is not null) and (area.SHOP_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 6 :
						squery.append(" and ((area.OFFICE_AREA is not null) and (area.OFFICE_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 7 :
						squery.append(" and ((area.GODOWN_AREA is not null) and (area.GODOWN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 10 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 11 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 12 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 13 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 14 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 15 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 16 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 17 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 18 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 19 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 20 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 21 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 22 :
						squery.append(" and ((area.RCC_AREA is not null) and (area.RCC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 23 :
						squery.append(" and ((area.RBC_AREA is not null) and (area.RBC_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 24 :
						squery.append(" and ((area.TIN_AREA is not null) and (area.TIN_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

					case 25 :
						squery.append(" and ((area.KACHA_AREA is not null) and (area.KACHA_AREA!=0)) and txn.BUILDING_TXN_ID=build.BUILDING_TXN_ID and txn.FLOOR_TXN_ID=area.FLOOR_TXN_ID");
						break;

				}

				break;
			case 14 :
				squery.append(CommonSQL.SEARCH_BUILD1 + " and ((build.TERRACE_AREA is not null) and (build.TERRACE_AREA!=0)) and build.TERRACE_USAGE=14 ");

				break;
			case 15 :
				squery.append(CommonSQL.SEARCH_BUILD1 + " and ((build.TERRACE_AREA is not null) and (build.TERRACE_AREA!=0)) and build.TERRACE_USAGE=15");

				break;
			case 16 :
				squery.append(CommonSQL.SEARCH_BUILD1 + " and ((build.TERRACE_AREA is not null) and (build.TERRACE_AREA!=0)) and build.TERRACE_USAGE=16");

				break;
			case 17 :
				squery.append(CommonSQL.SEARCH_BUILD1 + " and ((build.TERRACE_AREA is not null) and (build.TERRACE_AREA!=0)) and build.TERRACE_USAGE=17");

				break;
			case 18 :
				squery.append(CommonSQL.SEARCH_BUILD1 + " and ((build.FLOOR_AREA is not null) and (build.FLOOR_AREA!=0)) and build.MULTISTOREY_SUB_TYPE=18");

				break;
			case 19 :
				squery.append(CommonSQL.SEARCH_BUILD1 + " and ((build.FLOOR_AREA is not null) and (build.FLOOR_AREA!=0)) and build.MULTISTOREY_SUB_TYPE=19");

				break;

		}
		for (int i = 0; i < values.length; i++) {
			if (!values[0].equalsIgnoreCase("-1")) {
				if (i == 0)

				{
					
					squery.append(" and sub.SUB_CLAUSE_ID IN(?,");
					paramsMap.put("B" + (i + 1), values[i]);
					
				} else if (i != values.length - 1) {
					squery.append("?,");
					paramsMap.put("B" + (i + 1), values[i]);
				} else {
					squery.append("?)");
					paramsMap.put("B" + (i + 1), values[i]);
				}
			}
		}

		if (spForm.getQuestion().equalsIgnoreCase("N")) {
			if (spForm.getViewType().equals("State")) {

			} else if (spForm.getViewType().equals("Zone")) {
				squery.append("  AND reg.REGISTRATION_ZONE_ID =?");
				paramsMap.put("C1", spForm.getZoneId());//
			} else {
				squery.append("  AND reg.REGISTRATION_ZONE_ID =? and reg.REGISTRATION_DIST_ID=?");
				paramsMap.put("C1", spForm.getZoneId());
				paramsMap.put("C2", spForm.getDistrictId());
			}

		} else {
			if (!spForm.getZoneId().equalsIgnoreCase("-1")) {
				squery.append("  AND reg.REGISTRATION_ZONE_ID =?");
				paramsMap.put("C1", spForm.getZoneId());
				if (!spForm.getDistrictId().equalsIgnoreCase("-1")) {
					squery.append("  AND reg.REGISTRATION_DIST_ID =?");
					paramsMap.put("C2", spForm.getDistrictId());
					if (!spForm.getTehsilId().equalsIgnoreCase("-1")) {
						squery.append("  AND reginit.tehsil_id        =?");
						paramsMap.put("C3", spForm.getTehsilId());
						if (!spForm.getAreaTypeId().equalsIgnoreCase("-1")) {
							squery.append(" AND reginit.AREA_TYPE_ID     = ?");
							paramsMap.put("C4", spForm.getAreaTypeId());
							if (!spForm.getSubAreaId().equalsIgnoreCase("-1")) {
								squery.append("  AND reginit.GOV_BODY_ID        = ?");
								paramsMap.put("C5", spForm.getSubAreaId());
								if (!spForm.getWardIds().equalsIgnoreCase("-1")) {
									squery.append("  AND reginit.WARD_ID =?");
									paramsMap.put("C6", spForm.getWardIds().split("~")[0]);
									if (!spForm.getColonyId().equalsIgnoreCase("-1")) {
										squery.append("  AND reginit.MOHALLA_ID =?");
										paramsMap.put("C7", spForm.getColonyId().split("~")[0]);
									}
								}
							}
						}

					}

				}
			}

		}
		Collection<String> values1 = paramsMap.values();
		ArrayList<String> tmp = new ArrayList(values1);
		String[] params = tmp.toArray(new String[]{});
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			try {
				emptyTemp();
			} catch (Exception e) {
				logger.debug("Error : " + e);
			}

			dbUtil.createPreparedStatement(squery.toString());
			list = dbUtil.executeQuery(params);
			if (list.isEmpty() || list.size() == 0 || list.size() < 0) {

				return null;

			}

			if (spForm.getQuestion().equalsIgnoreCase("Y")) {
				String userData[] = new String[6];
				userData[0] = getPropertyId(spForm.getPropertyId());
				userData[1] = spForm.getTehsilId();
				userData[2] = spForm.getAreaTypeId();
				userData[3] = spForm.getColonyId().split("~")[0];
				userData[4] = spForm.getWardIds().split("~")[0];
				userData[5] = spForm.getSubAreaId();
				if (list != null && list.size() > 0) {
					String parameterTemp = "";
					String parameterFinal = "";
					ArrayList temp = null;
					boolean check = false;
					for (int i = 0; i < list.size(); i++) {
						temp = (ArrayList) list.get(i);
						parameterTemp = temp.toString();
						if (parameterTemp != null) {
							parameterFinal = parameterTemp.substring(1, parameterTemp.length() - 1);
						} else {
							parameterFinal = "";
						}
						String add[] = parameterFinal.split(",");
						String finals[] = (String[]) ArrayUtils.addAll(add, userData);
						check = addTempDetails(finals);
						if (!check) {
							boo = false;
							break;
						}

					}

				}

			} else if (spForm.getQuestion().equalsIgnoreCase("N")) {
				String userData[] = new String[1];
				userData[0] = getPropertyId(spForm.getPropertyId().split("~")[0]);

				if (list != null && list.size() != 0) {
					String parameterTemp = "";
					String parameterFinal = "";
					ArrayList temp = null;
					boolean check = false;
					{
						check = false;
					}
					for (int i = 0; i < list.size(); i++) {
						temp = (ArrayList) list.get(i);
						parameterTemp = temp.toString();
						if (parameterTemp != null) {
							parameterFinal = parameterTemp.substring(1, parameterTemp.length() - 1);
						} else {
							parameterFinal = "";
						}

						String add[] = parameterFinal.split(",");
						String finals[] = (String[]) ArrayUtils.addAll(add, userData);
						check = addTempDetailsNo(finals);
						if (!check) {
							boo = false;
							boo = false;
							break;
						}

					}

				}

			}
			if (spForm.getQuestion().equalsIgnoreCase("Y")) {
				if (boo) {
					HashMap category = new HashMap();
					String districts[] = getDistricts();

					for (int i = 0; i < districts.length; i++) {
						String district[] = districts[i].split("~");
						String number = district[0];
						list = getDistrictInfoListsYes(number, spForm.getLanguage());
						category.put(districts[i], list);

					}
					spForm.setFinalValues(category); // setting hasmap values to
					// form
					System.out.println("Hashmap Size is : " + category.size());

				}

				dbUtil.createStatement();
				ArrayList finallist = null;
				if (spForm.getLanguage().equalsIgnoreCase("en"))
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY);
				else
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY_HINDI);
				// emptyTemp();

				return finallist;
			} else if (spForm.getQuestion().equalsIgnoreCase("N")) {
				if (boo) {
					HashMap category = new HashMap();
					String districts[] = getDistricts();

					for (int i = 0; i < districts.length; i++) {
						String district[] = districts[i].split("~");
						String number = district[0];
						list = getDistrictInfoListsNo(number, spForm.getLanguage());
						category.put(districts[i], list);

					}
					spForm.setFinalValues(category); // setting hasmap values to
					// form
					System.out.println("Hashmap Size is : " + category.size());

				}

				dbUtil.createStatement();
				ArrayList finallist = null;
				if (spForm.getLanguage().equalsIgnoreCase("en"))
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY);
				else
					finallist = dbUtil.executeQuery(CommonSQL.IGRS_SPOT_FINAL_QUERY_HINDI);

				// emptyTemp();
				return finallist;
			}
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
		return null;

	}

	public boolean initiateEmail(String regNu, String userId, String AssignedID, String spotID, String dates) {
		boolean boo = false;
		DBUtility dbUtils = null;
		try {

			String emailUser = getEmail(userId);
			String emailAssigned = getEmail(AssignedID);
			String body = "You have been assigned documents for Spot Inspectionts.Document with  E-Registration Number  ";
			String reg = regNu;
			String header = "Dear Sir/Madam";
			String footer = "Thanks & Regards";
			String finalContent = body + reg + " have been allocated for Spot inspection with Spot Id " + spotID + ". Inspection of these documents must be completed within " + dates + " days";
			String arr[] = {emailAssigned, emailUser, "SPOT ORDER DETAILS", finalContent, emailUser, "N", userId, header, footer};
			dbUtils = new DBUtility();
			dbUtils.createPreparedStatement(CommonSQL.INSERT_EMAIL_DATA);
			boo = dbUtils.executeUpdate(arr);

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtils.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return boo;

	}

	private String getEmail(String userId) {
		String arr[] = {userId};
		String email = "";
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_EMAIL_ID);
			email = dbUtil.executeQry(arr);

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return email;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage(), e);
			}
		}
		return email;
	}

	public ArrayList getDataL(String regTxnId) {
		String arr[] = {regTxnId};
		ArrayList li = null;
		DBUtility dbUtil = null;
		try {

			String SQL = CommonSQL.GET_PROPERTY_IDS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			li = dbUtil.executeQuery(arr);

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return li;
	}

	public ArrayList getDataLR(String regTxnId) {
		String arr[] = {regTxnId};
		ArrayList li = null;
		DBUtility dbUtil = null;
		try {

			String SQL = CommonSQL.GET_PROPERTY_RE_IDS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			li = dbUtil.executeQuery(arr);

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return li;
	}

	public boolean checkCaseMonitoring(String reg_id) {
		try {
			String arr[] = {reg_id};
			DBUtility dbuti = new DBUtility();
			String SQL = CommonSQL.IGRS_CHECK_CASE_MONITORING;
			dbuti.createPreparedStatement(SQL);
			ArrayList lo = dbuti.executeQuery(arr);

			if (lo.size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public void getBifercatedDuties(String regId, SpotInspForm spForm) {

		try {
			String sql = CommonSQL.IGRS_GET_STAMP_DETAILS;
			String arr[] = {regId};
			DBUtility dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			ArrayList list = dbUtil.executeQuery(arr);
			if (list != null && list.size() != 0) {
				ArrayList li = (ArrayList) list.get(0);
				spForm.getObjSIDto().setJandpadDuty((String) li.get(0));
				spForm.getObjSIDto().setGovStampDuty((String) li.get(1));
				spForm.getObjSIDto().setMunicipalDuty((String) li.get(2));
				spForm.getObjSIDto().setUpkarDuty((String) li.get(3));
				spForm.getObjSIDto().setTotalDuty((String) li.get(4));
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}

	}

	public String getRegTxnID(String eRegNo) throws Exception {

		String[] regDtl = {eRegNo};
		String regTxnID = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.GET_REG_TXN_ID);
			regTxnID = dbUtility.executeQry(regDtl);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		return regTxnID;
	}

	public String getPostInspectionComments(String spotTxnId) throws Exception {
		String[] remarks = {spotTxnId};
		String postInspComment = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.POST_INSPECTION_COMMENTS);
			postInspComment = dbUtility.executeQry(remarks);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		return postInspComment;
	}

	public ArrayList getOtherSpotInsDtls(String regNo) {
		String param[] = new String[1];
		param[0] = regNo;
		ArrayList ret = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.SELECT_OTHER_INFO);

			ret = dbUtil.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ret;

	}

	public String getInspectionStatus(String id) {
		String value = "";
		DBUtility dbUtil = null;
		try {
			String param[] = new String[1];
			param[0] = id;
			String SQL = " select status_spot from IGRS_SPOT_TRANSACTION_TABLE where spot_txn_id='" + id + "'";
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			value = dbUtil.executeQry(SQL);
		} catch (Exception e) {
			logger.error("Exception in getInspectionStatus():" + e);
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

	public boolean validateDate(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_DURATION;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);

			logger.debug("sql query for registration Report====" + durationSQL);
			String dayDiff = dbUtil.executeQry(new String[]{toDate, fromDate});
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff) & Integer.parseInt(dayDiff)>=0)
				boo = true;
			else
				boo = false;

		} catch (Exception e) {
			logger.debug("Exception");
			// TODO: handle exception
		} finally {
			try {

				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boo;
	}
	
	
	
	public String duration() {

		String duration = "";
		String durationSQL = CommonSQL.GET_DURATION;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			duration = dbUtil.executeQry(durationSQL);
		} catch (Exception e) {
			logger.debug("Exception");
			// TODO: handle exception
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return duration;
	}

}
