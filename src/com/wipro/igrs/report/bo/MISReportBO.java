/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: ReportBD.java
 *
 * Description	   		: This class interacts with DbService for data 
 * 							persistance and data fetch
 *
 * Version Number  		: 0.0 
 *
 * Created Date	   		: 30 04 2008 
 *
 * Modification History	: Created
 * Added - Slot Stastics Future Max Date Validation  - RAhul  25-OCT-16  
 */

package com.wipro.igrs.report.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wipro.igrs.DeliveryOfDocuments.bo.DeliveryOfDocumentsBO;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.propertyvaluation.util.PropertiesFileReader;
import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.report.dto.EstampJudicialDTO;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.report.dto.MarketTrendReportDTO;
import com.wipro.igrs.report.form.MISReportForm;
import com.wipro.igrs.report.sql.CommonSQL;

public class MISReportBO {
	/**
	 * ========================================================================
	 * === Method : getDistrictDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Madan Mohan Created Date : Apr 30, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	private static final Logger logger = Logger.getLogger(MISReportBO.class);

	private IGRSCommon common;

	public String getOfficeType(String officeId) {

		String officeType = null;

		IGRSCommon common;
		try {
			common = new IGRSCommon();

			// officeType = common.getOfficeType(officeId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return officeType;
	}

	public ArrayList getDistrictDetails() {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			ArrayList resultList = common.getDistrict("MP");
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();
					reportDTO.setDistrictId((String) list.get(0));
					reportDTO.setDistrictName((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getDistrictId() + ":" + reportDTO.getDistrictName());
				}
			}
		} catch (Exception err) {

			logger.debug("In getDistrictDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getTehsilTypeDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Madan Mohan Created Date : May 12, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getTehsilTypeDetails(MarketTrendReportDTO dto) {
		String districtId = dto.getDistrictId();
		logger.debug("districtId:-" + districtId);
		ReportDAO dao = new ReportDAO();

		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getTehsilTypeDetails BD start");
			IGRSCommon common = new IGRSCommon();

			ArrayList resultList = common.getThesil(districtId);

			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					String offTypeId = (String) list.get(0);
					MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();
					reportDTO.setTehsilId((String) list.get(0));
					reportDTO.setTehsilName((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getTehsilId() + ":" + reportDTO.getTehsilName());

				}
			}
			logger.debug("In getTehsilTypeDetails BD TryBlock end ");
		} catch (Exception err) {

			logger.debug("In getTehsilTypeDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getAreaTypeDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Madan Mohan Created Date : May 12, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getAreaTypeDetails(String language) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			ArrayList resultList = common.getAreaType();
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();
					reportDTO.setAreaTypeId((String) list.get(0));
					if (language.equalsIgnoreCase("en")) {
						reportDTO.setAreaTypeName((String) list.get(1));
					} else {
						reportDTO.setAreaTypeName((String) list.get(2));
					}
					returnList.add(reportDTO);
					logger.debug(reportDTO.getAreaTypeId() + ":" + reportDTO.getAreaTypeName());
				}
			}
		} catch (Exception err) {

			logger.debug("In getAreaTypeDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getWardDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Madan Mohan Created Date : May 12, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getWardDetails(MarketTrendReportDTO dto) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			ArrayList resultList = common.getWard(dto.getTehsilId(), dto.getAreaTypeId(), dto.getAreaType());
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					ArrayList list = (ArrayList) resultList.get(i);
					MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();
					reportDTO.setWardPatwariId((String) list.get(0));
					reportDTO.setWardPatwariName((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getWardPatwariId() + ":" + reportDTO.getWardPatwariName());
				}
			}
			logger.debug("In getWardDetails BD TryBlock end ");
		} catch (Exception err) {

			logger.debug("In getWardDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getPatwariDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Madan Mohan Created Date : May 12, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getMohallaDetails(MarketTrendReportDTO dto) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getPatwariDetails BD start");
			IGRSCommon common = new IGRSCommon();
			ArrayList resultList = common.getMahalla(dto.getWardPatwariId());
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					ArrayList list = (ArrayList) resultList.get(i);
					MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();
					reportDTO.setMohallaId((String) list.get(0));
					reportDTO.setMohallaName((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getWardPatwariId() + ":" + reportDTO.getWardPatwariName());
				}
			}
		} catch (Exception err) {

			logger.debug("In getPatwariDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getOfficeNameDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Madan Mohan Created Date : Apr 30, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getOfficeNameDetails(MarketTrendReportDTO dto) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getOfficeNameDetails BD start");
			String[] param = new String[2];
			param[0] = dto.getUserType();
			param[1] = "A";
			String SQL = CommonSQL.OFFICE_NAME_DETAILS_I;

			ArrayList resultList = dao.getOfficeNameDetails(param, SQL);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					ArrayList list = (ArrayList) resultList.get(i);
					MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();
					reportDTO.setOfficeId((String) list.get(0));
					reportDTO.setOfficeName((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getOfficeName() + ":" + reportDTO.getOfficeId());
				}
			}
		} catch (Exception err) {

			logger.debug("In getOfficeNameDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * @param dto
	 * @return
	 */
	public String[] returnYear(MarketTrendReportDTO dto) {
		String search = dto.getSearchType();
		String[] str = new String[2];

		if ("M".equals(search)) {
			ReportDAO dao = new ReportDAO();
			ArrayList list = dao.getCurrentYear();
			if (list != null) {
				if (list.size() == 1) {
					ArrayList ret = (ArrayList) list.get(0);
					str[0] = (String) ret.get(0);
					str[1] = (String) ret.get(1);
				}
			}
		}
		if ("P".equals(search)) {
			String fromDate = dto.getFromDate();
			String toDate = dto.getToDate();

			str[0] = fromDate.substring(fromDate.length() - 4, fromDate.length());
			str[1] = toDate.substring(toDate.length() - 4, toDate.length());

		}
		return str;
	}

	public ArrayList getYearForJudicalStamp(int iYear) {

		String str = "";
		ArrayList listRet = new ArrayList();

		ReportDAO dao = new ReportDAO();
		ArrayList list = dao.getCurrentYear();
		if (list != null) {
			if (list.size() == 1) {
				ArrayList ret = (ArrayList) list.get(0);
				str = (String) ret.get(0);

			}
		}
		for (int i = 0; i < iYear; i++) {
			int Year = Integer.parseInt(str) - i;
			MISReportDTO dto = new MISReportDTO();
			int YearPlus = Year + 1;
			dto.setYearValue("" + Year);
			dto.setYear("" + Year + "-" + YearPlus);
			listRet.add(dto);
		}
		return listRet;
	}

	/**
	 * @param dto
	 * @return
	 */
	public ArrayList getUsageReport(MISReportDTO dto) {
		ArrayList list = new ArrayList();

		ReportDAO dao = new ReportDAO();

		String searchType = dto.getSearchType();
		String SQL = "";
		String[] param = null;
		/*
		 * if ("M".equals(searchType)) { SQL = CommonSQL.USAGE_REPORT_MONTH;
		 * param = new String[1]; param[0] = dto.getMonth(); }
		 */

		SQL = CommonSQL.USAGE_REPORT_PERIOD;
		param = new String[2];
		param[0] = dto.getFromDate();
		param[1] = dto.getToDate();

		logger.debug("SQL:-" + SQL);
		ArrayList retList = dao.getUsageReport(param, SQL);

		if (retList != null) {
			for (int i = 0; i < retList.size(); i++) {
				ArrayList listRet = (ArrayList) retList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				logger.debug("Fun Name:-" + (String) listRet.get(0));
				misDTO.setFunctionality((String) listRet.get(0));
				misDTO.setNoTimeAccessed((String) listRet.get(1));
				// misDTO.setAccessTime((String) listRet.get(3));
				misDTO.setAccessdate((String) listRet.get(2));
				String funname = (String) listRet.get(0);

				String funcid = "";// dao.getfuncid(funname);

				int time = (Integer.parseInt(listRet.get(1).toString()));

				if ("FUN_038".equalsIgnoreCase(funcid)) {

					int min = 2;

					int sec = 3;

					misDTO.setAccessTime(time * min + "min" + "" + time * sec + "sec");

				}

				list.add(misDTO);
			}
		}

		return list;
	}

	/**
	 * @param dto
	 * @return
	 */
	public ArrayList getSearchRequestReport(MISReportDTO dto) {
		ArrayList list = new ArrayList();

		ReportDAO dao = new ReportDAO();

		String searchType = dto.getSearchType();
		String userType = dto.getUserType();
		String SQL = CommonSQL.SEARCH_REQUEST_QUERY_I;

		String[] param = null;
		if ("M".equals(searchType)) {

			// param = new String[1];
			// param[0] = dto.getMonth();
			if ("SRO".equals(userType) || "DRO".equals(userType)) {
				SQL = SQL + CommonSQL.SEARCH_REQUEST_OFFICE + CommonSQL.SEARCH_REQUEST_MONTH + CommonSQL.SEARCH_REQUEST_QUERY_II;

				param = new String[3];
				param[0] = dto.getOfficeId();
				param[1] = userType;
				param[2] = dto.getMonth();

			}
			if ("A".equals(userType)) {
				SQL = SQL + CommonSQL.SEARCH_REQUEST_DISTRICT + CommonSQL.SEARCH_REQUEST_MONTH + CommonSQL.SEARCH_REQUEST_QUERY_II;

				param = new String[2];
				param[0] = dto.getDistrictID();
				param[1] = dto.getMonth();
			}
		}
		if ("P".equals(searchType)) {

			if ("SRO".equals(userType) || "DRO".equals(userType)) {
				SQL = SQL + CommonSQL.SEARCH_REQUEST_OFFICE + CommonSQL.SEARCH_REQUEST_PERIOD + CommonSQL.SEARCH_REQUEST_QUERY_II;

				param = new String[4];

				param[0] = dto.getOfficeId();
				param[1] = userType;
				param[2] = dto.getFromDate();
				param[3] = dto.getToDate();
			}
			if ("A".equals(userType)) {
				SQL = SQL + CommonSQL.SEARCH_REQUEST_DISTRICT + CommonSQL.SEARCH_REQUEST_PERIOD + CommonSQL.SEARCH_REQUEST_QUERY_II;
				param = new String[3];
				param[0] = dto.getDistrictID();
				param[1] = dto.getFromDate();
				param[2] = dto.getToDate();
			}

		}
		logger.debug("Search Request SQL:-" + SQL);
		ArrayList retList = dao.getUsageReport(param, SQL);
		try {
			common = new IGRSCommon();
		} catch (Exception x) {
			logger.debug(x);
		}

		if (retList != null) {
			for (int i = 0; i < retList.size(); i++) {
				ArrayList listRet = (ArrayList) retList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				misDTO.setUserDate((String) listRet.get(0));
				misDTO.setUserID((String) listRet.get(1));
				misDTO.setUserName((String) listRet.get(2));
				misDTO.setSearchDocumentDetails((String) listRet.get(3));
				misDTO.setSearchPropertyOwnerName((String) listRet.get(4));
				misDTO.setSearchCount((String) listRet.get(5));
				String total = listRet.get(6) == null ? "0.0" : (String) listRet.get(6);

				misDTO.setSearchTotal(common.getCurrencyFormat(Double.parseDouble(total)));
				list.add(misDTO);
			}
		}

		return list;
	}

	/*
	 * Method : getOfficeNameDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Madan Created Date : June 03 2008
	 * 
	 * @param reportForm description
	 * ============================================
	 * ===============================
	 */
	public ArrayList getOfficeNameDetailsMIS(MISReportDTO dto, String lang) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getOfficeNameDetails BD start");
			String[] param = new String[2];
			param[0] = dto.getUserType();
			param[1] = "A";
			String SQL = CommonSQL.OFFICE_NAME_DETAILS_I;

			ArrayList resultList = dao.getOfficeNameDetails(param, SQL);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					ArrayList list = (ArrayList) resultList.get(i);
					MISReportDTO reportDTO = new MISReportDTO();
					reportDTO.setOfficeId((String) list.get(0));
					if ("en".equalsIgnoreCase(lang))
						reportDTO.setOfficeName((String) list.get(1));
					else
						reportDTO.setOfficeName((String) list.get(2));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getOfficeName() + ":" + reportDTO.getOfficeId());
				}
			}
		} catch (Exception err) {

			logger.debug("In getOfficeNameDetails Exception " + err);
		}
		return returnList;
	}

	public ArrayList getDistrictDetailsMIS(String language, String offcID) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			// IGRSCommon common = new IGRSCommon();
			ArrayList resultList = dao.getDistrict("1", offcID);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					MISReportDTO reportDTO = new MISReportDTO();
					reportDTO.setDistrictID((String) list.get(0));
					if ("en".equalsIgnoreCase(language))
						reportDTO.setDistrictName((String) list.get(1));
					else
						reportDTO.setDistrictName((String) list.get(2));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getDistrictID() + ":" + reportDTO.getDistrictName());
				}
			}
		} catch (Exception err) {

			logger.debug("In getDistrictDetails Exception " + err);
		}
		return returnList;
	}

	public ArrayList getMonthlyRevenueList(String year, String language) {
		ArrayList reportList = new ArrayList();
		ArrayList list = new ArrayList();
		ReportDAO dao = new ReportDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		list = dao.districtEstampCode();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList rowList = (ArrayList) list.get(i);
				map.put((String) rowList.get(0), (String) rowList.get(1));
			}
		}
		ArrayList distList = dao.districtList();
		for (int i = 0; i < distList.size(); i++) {
			ArrayList txnList = new ArrayList();
			EstampJudicialDTO retDTO = new EstampJudicialDTO();
			ArrayList distrowList = (ArrayList) distList.get(i);
			String distId = (String) distrowList.get(0);
			if (distId.length() == 1) {
				distId = "0" + distId;
			}
			String distName = "";
			if ("en".equalsIgnoreCase(language)) {
				distName = (String) distrowList.get(1);
			} else {
				distName = (String) distrowList.get(2);
			}
			retDTO.setDistrictId(distId);
			retDTO.setDistrictName(distName);
			Set mapSet2 = (Set) map.entrySet();
			Iterator mapIterator2 = mapSet2.iterator();
			while (mapIterator2.hasNext()) {
				Map.Entry mapEntry1 = (Map.Entry) mapIterator2.next();
				String key = (String) mapEntry1.getKey();
				String value = (String) mapEntry1.getValue();
				if (value.equalsIgnoreCase(distId)) {
					txnList.add(key);
				}
			}
			if (txnList.size() == 0) {
				retDTO.setJanCollection("--");
				retDTO.setFebCollection("--");
				retDTO.setMarCollection("--");
				retDTO.setAprCollection("--");
				retDTO.setJunCollection("--");
				retDTO.setJulCollection("--");
				retDTO.setAugCollection("--");
				retDTO.setSepCollection("--");
				retDTO.setOctCollection("--");
				retDTO.setNovCollection("--");
				retDTO.setDecCollection("--");
				retDTO.setMayCollection("--");
				reportList.add(retDTO);
			} else {
				ArrayList amountlist = dao.amountMonth(txnList, year);
				if (amountlist == null || amountlist.size() == 0) {
					retDTO.setJanCollection("--");
					retDTO.setFebCollection("--");
					retDTO.setMarCollection("--");
					retDTO.setAprCollection("--");
					retDTO.setJunCollection("--");
					retDTO.setJulCollection("--");
					retDTO.setAugCollection("--");
					retDTO.setSepCollection("--");
					retDTO.setOctCollection("--");
					retDTO.setNovCollection("--");
					retDTO.setDecCollection("--");
					retDTO.setMayCollection("--");
					reportList.add(retDTO);
				} else if (amountlist != null && amountlist.size() > 0) {
					boolean jan = false;
					boolean feb = false;
					boolean mar = false;
					boolean apr = false;
					boolean may = false;
					boolean jun = false;
					boolean jul = false;
					boolean aug = false;
					boolean sep = false;
					boolean oct = false;
					boolean nov = false;
					boolean dec = false;
					for (int k = 0; k < amountlist.size(); k++) {
						ArrayList amountrowList = (ArrayList) amountlist.get(k);
						if (((String) amountrowList.get(1)).equalsIgnoreCase("jan")) {
							retDTO.setJanCollection((String) amountrowList.get(0));
							jan = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("feb")) {
							retDTO.setFebCollection((String) amountrowList.get(0));
							feb = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("mar")) {
							retDTO.setMarCollection((String) amountrowList.get(0));
							mar = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("apr")) {
							retDTO.setAprCollection((String) amountrowList.get(0));
							apr = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("may")) {
							retDTO.setMayCollection((String) amountrowList.get(0));
							may = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("jun")) {
							retDTO.setJunCollection((String) amountrowList.get(0));
							jun = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("jul")) {
							retDTO.setJulCollection((String) amountrowList.get(0));
							jul = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("aug")) {
							retDTO.setAugCollection((String) amountrowList.get(0));
							aug = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("sep")) {
							retDTO.setSepCollection((String) amountrowList.get(0));
							sep = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("oct")) {
							retDTO.setOctCollection((String) amountrowList.get(0));
							oct = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("nov")) {
							retDTO.setNovCollection((String) amountrowList.get(0));
							nov = true;
						} else if (((String) amountrowList.get(1)).equalsIgnoreCase("dec")) {
							retDTO.setDecCollection((String) amountrowList.get(0));
							dec = true;
						} else {

						}
						if (jan == false) {
							retDTO.setJanCollection("--");
						}
						if (feb == false) {
							retDTO.setFebCollection("--");
						}
						if (mar == false) {
							retDTO.setMarCollection("--");
						}
						if (apr == false) {
							retDTO.setAprCollection("--");
						}
						if (may == false) {
							retDTO.setMayCollection("--");
						}
						if (jun == false) {
							retDTO.setJunCollection("--");
						}
						if (jul == false) {
							retDTO.setJulCollection("--");
						}
						if (aug == false) {
							retDTO.setAugCollection("--");
						}
						if (sep == false) {
							retDTO.setSepCollection("--");
						}
						if (oct == false) {
							retDTO.setOctCollection("--");
						}
						if (nov == false) {
							retDTO.setNovCollection("--");
						}
						if (dec == false) {
							retDTO.setDecCollection("--");
						}
					}
					reportList.add(retDTO);

				}
			}

		}

		/*
		 * String[] param = new String[1]; param[0] = year; ReportDAO dao = new
		 * ReportDAO(); ArrayList listRet = dao.getMonthlyJudicial(param); try {
		 * common = new IGRSCommon(); } catch (Exception x) { logger.debug(x); }
		 * if (listRet != null) { for (int i = 0; i < listRet.size(); i++) {
		 * ArrayList retList = (ArrayList) listRet.get(i); MISReportDTO dto =
		 * new MISReportDTO(); dto.setDistrictID((String) retList.get(0));
		 * dto.setDistrictName((String) retList.get(1)); String april =
		 * retList.get(2) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(2)));
		 * 
		 * dto.setRevenueApril(april);
		 * 
		 * String may = retList.get(3) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(3)));
		 * 
		 * dto.setRevenueMay(may);
		 * 
		 * String june = retList.get(4) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(4)));
		 * 
		 * dto.setRevenueJune(june);
		 * 
		 * String july = retList.get(5) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(5)));
		 * 
		 * dto.setRevenueJuly(july);
		 * 
		 * String aug = retList.get(6) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(6)));
		 * 
		 * dto.setRevenueAug(aug);
		 * 
		 * String sept = retList.get(7) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(7)));
		 * 
		 * dto.setRevenueSept(sept);
		 * 
		 * String oct = retList.get(8) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(8)));
		 * 
		 * dto.setRevenueOct(oct);
		 * 
		 * String nov = retList.get(9) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(9)));
		 * 
		 * dto.setRevenueNov(nov);
		 * 
		 * String dec = retList.get(10) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(10)));
		 * 
		 * dto.setRevenueDec(dec);
		 * 
		 * String jan = retList.get(11) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(11)));
		 * 
		 * dto.setRevenueJan(jan);
		 * 
		 * String feb = retList.get(12) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(12)));
		 * 
		 * dto.setRevenueFeb(feb);
		 * 
		 * String mar = retList.get(13) == null ? "" : common
		 * .getCurrencyFormat(Double.parseDouble((String) retList .get(13)));
		 * 
		 * dto.setRevenueMar(mar); list.add(dto); } }
		 */
		return reportList;
	}

	public ArrayList getRevenueSROURReport(MISReportDTO dto) {
		ArrayList list = new ArrayList();
		PropertiesFileReader pr;
		ReportDAO dao = new ReportDAO();

		String urbanID = "";
		String ruralID = "";
		String month = "";
		String userType = "SRO";
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			urbanID = pr.getValue("URBAN_ID");
			ruralID = pr.getValue("RURAL_ID");
			month = dto.getMonth() != null ? dto.getMonth() : "";
		} catch (Exception x) {
			logger.debug(x);
		}

		logger.debug("UR ID:-" + urbanID + ":" + ruralID + ":" + month);

		String searchType = dto.getSearchType();
		String SQL = "";
		String[] param = null;

		if ("M".equals(searchType)) {
			SQL = CommonSQL.REVENUE_RECEIPT_SRO_MONTH_UR;
			param = new String[21];
			param[0] = urbanID;
			param[1] = month;
			param[2] = ruralID;
			param[3] = userType;
			param[4] = month;
			param[5] = urbanID;
			param[6] = userType;
			param[7] = month;
			param[8] = ruralID;
			param[9] = month;
			param[10] = urbanID;
			param[11] = month;
			param[12] = ruralID;
			param[13] = userType;
			param[14] = month;
			param[15] = urbanID;
			param[16] = userType;
			param[17] = month;
			param[18] = urbanID;
			param[19] = userType;
			param[20] = month;
		}
		if ("P".equals(searchType)) {
			SQL = CommonSQL.REVENUE_RECEIPT_SRO_PERIOD_UR;
			param = new String[29];
			String fromDate = dto.getFromDate();
			String toDate = dto.getToDate();

			param[0] = urbanID;
			param[1] = fromDate;
			param[2] = toDate;
			param[3] = ruralID;
			param[4] = userType;
			param[5] = fromDate;
			param[6] = toDate;
			param[7] = urbanID;
			param[8] = userType;
			param[9] = fromDate;
			param[10] = toDate;
			param[11] = ruralID;
			param[12] = fromDate;
			param[13] = toDate;
			param[14] = urbanID;
			param[15] = fromDate;
			param[16] = toDate;
			param[17] = ruralID;
			param[18] = userType;
			param[19] = fromDate;
			param[20] = toDate;
			param[21] = urbanID;
			param[22] = userType;
			param[23] = fromDate;
			param[24] = toDate;
			param[25] = urbanID;
			param[26] = userType;
			param[27] = fromDate;
			param[28] = toDate;
		}
		logger.debug("Search Type:-" + searchType);
		for (int i = 0; i < param.length; i++) {
			logger.debug("param[" + i + "]=" + param[i]);
		}
		ArrayList retList = dao.getUsageReport(param, SQL);

		if (retList != null) {
			for (int i = 0; i < retList.size(); i++) {
				ArrayList listRet = (ArrayList) retList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				misDTO.setOfficeId((String) listRet.get(0));
				misDTO.setOfficeName((String) listRet.get(1));

				double ruralRevenueCur = listRet.get(2) == null ? 0.0 : Double.parseDouble((String) listRet.get(2));

				double urbanRevenueCur = listRet.get(3) == null ? 0.0 : Double.parseDouble((String) listRet.get(3));

				misDTO.setRevenueRuralCur(common.getCurrencyFormat(ruralRevenueCur));
				misDTO.setRevenueUrbanCur(common.getCurrencyFormat(urbanRevenueCur));

				double totalRevenueCur = ruralRevenueCur + urbanRevenueCur;

				misDTO.setRevenueCurTotal("" + totalRevenueCur);

				misDTO.setNoOfDocRuralCur((String) listRet.get(4));
				misDTO.setNoOfDocUrbanCur((String) listRet.get(5));

				int noOfDocRuralCur = listRet.get(4) == null ? 0 : Integer.parseInt((String) listRet.get(4));

				int noOfDocUrbanCur = listRet.get(5) == null ? 0 : Integer.parseInt((String) listRet.get(5));

				int noOfDocTotalCur = noOfDocRuralCur + noOfDocUrbanCur;

				misDTO.setNoOfDocCurTotal("" + noOfDocTotalCur);

				misDTO.setRevenueRuralPrev((String) listRet.get(6));
				misDTO.setRevenueUrbanPrev((String) listRet.get(7));

				double ruralRevenuePrev = listRet.get(6) == null ? 0.0 : Double.parseDouble((String) listRet.get(6));

				double urbanRevenuePrev = listRet.get(7) == null ? 0.0 : Double.parseDouble((String) listRet.get(7));

				misDTO.setRevenueRuralPrev(common.getCurrencyFormat(ruralRevenuePrev));
				misDTO.setRevenueUrbanPrev(common.getCurrencyFormat(urbanRevenuePrev));

				double totalRevenuePrev = ruralRevenuePrev + urbanRevenuePrev;

				misDTO.setRevenuePrevTotal(common.getCurrencyFormat(totalRevenuePrev));

				misDTO.setNoOfDocRuralPrev((String) listRet.get(8));
				misDTO.setNoOfDocUrbanPrev((String) listRet.get(9));

				int noOfDocRuralPrev = listRet.get(8) == null ? 0 : Integer.parseInt((String) listRet.get(8));

				int noOfDocUrbanPrev = listRet.get(9) == null ? 0 : Integer.parseInt((String) listRet.get(9));

				int noOfDocTotalPrev = noOfDocRuralPrev + noOfDocUrbanPrev;

				misDTO.setNoOfDocPrevTotal("" + noOfDocTotalPrev);

				double ruralPer = ruralRevenuePrev == 0.0 ? 0.00 : ((ruralRevenueCur - ruralRevenuePrev) / ruralRevenuePrev) * 100;

				misDTO.setRevenueRuralPer(common.getCurrencyFormat(ruralPer) + " %");

				double urbanPer = urbanRevenuePrev == 0.00 ? 0.00 : ((urbanRevenueCur - urbanRevenuePrev) / urbanRevenuePrev) * 100;
				misDTO.setRevenueUrbanPer(common.getCurrencyFormat(urbanPer) + " %");

				double totalRevenuePer = totalRevenuePrev == 0.00 ? 0.00 : ((totalRevenueCur - totalRevenuePrev) / totalRevenuePrev) * 100;
				misDTO.setRevenuePerTotal(common.getCurrencyFormat(totalRevenuePer) + " %");

				list.add(misDTO);
			}
		}

		return list;
	}

	public HashMap getCessMunicipalBlock(MISReportDTO dto) {
		HashMap list = new HashMap();

		ArrayList part1List = new ArrayList();

		PropertiesFileReader pr;
		ReportDAO dao = new ReportDAO();

		String month = dto.getMonth();
		String userType = dto.getUserType();
		try {

			common = new IGRSCommon();
		} catch (Exception x) {
			logger.debug(x);
		}
		String searchType = dto.getSearchType();

		logger.debug("User Type:-" + userType + ":" + month + ":" + searchType + ":" + dto.getDistrictID());

		String SQL = "";
		String[] param = null;
		SQL = CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_I;
		if ("M".equals(searchType)) {
			SQL = SQL + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_MONTH;
			if ("SRO".equals(userType) || "DRO".equals(userType)) {
				SQL = SQL + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_MONTH_OFFICE + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_II;
				param = new String[3];
				param[0] = dto.getMonth();
				param[1] = userType;
				param[2] = dto.getOfficeId();

			}
			if ("A".equals(userType)) {
				if ("".equals(dto.getDistrictID())) {
					SQL = SQL + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_II;
					param = new String[1];
					param[0] = dto.getMonth();
				} else {
					SQL = SQL + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_MONTH_DISTRICT + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_II;
					param = new String[2];
					param[0] = dto.getMonth();
					param[1] = dto.getDistrictID();
				}
			}
			logger.debug("SQL M:-" + SQL);
		}
		if ("P".equals(searchType)) {

			SQL = SQL + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_PERIOD;
			if ("SRO".equals(userType) || "DRO".equals(userType)) {
				SQL = SQL + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_MONTH_OFFICE + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_II;
				param = new String[4];
				param[0] = dto.getFromDate();
				param[1] = dto.getToDate();
				param[2] = userType;
				param[3] = dto.getOfficeId();
			}
			if ("A".equals(userType)) {
				if ("".equals(dto.getDistrictID())) {
					SQL = SQL + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_II;
					param = new String[2];
					param[0] = dto.getFromDate();
					param[1] = dto.getToDate();

				} else {
					SQL = SQL + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_MONTH_DISTRICT + CommonSQL.CESS_BLOCK_MUNICIPAL_DUTY_II;
					param = new String[3];
					param[0] = dto.getFromDate();
					param[1] = dto.getToDate();
					param[2] = dto.getDistrictID();
				}
			}
			logger.debug("SQL :-" + SQL);
		}

		ArrayList retList = dao.getCessMunicipalDuty(param, SQL);
		double totalOrg = 0.0;
		if (retList != null) {
			for (int i = 0; i < retList.size(); i++) {
				ArrayList listRet = (ArrayList) retList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				misDTO.setNameOfOrg((String) listRet.get(0));
				misDTO.setNoOfDocOrg((String) listRet.get(1));
				double revenueOrg = listRet.get(2) == null ? 0.0 : Double.parseDouble((String) listRet.get(2));

				totalOrg = totalOrg + revenueOrg;
				misDTO.setRevenueReceiptOrg(common.getCurrencyFormat(revenueOrg));

				part1List.add(misDTO);
			}

			logger.debug("Total Revenue:-" + totalOrg);
		}

		list.put("dtoList", part1List);
		list.put("dtoTotal", common.getCurrencyFormat(totalOrg));

		return list;
	}

	public int getYearCheck() {
		ReportDAO dao = new ReportDAO();
		return Integer.parseInt(dao.getYearCheck());
	}

	/*
	 * public static void main(String [] arg) { MISReportBO bd = new
	 * MISReportBO(); logger.debug(CommonSQL.REVENUE_RECEIPT_SRO_PERIOD_UR);
	 * MarketTrendReportDTO dto = new MarketTrendReportDTO();
	 * dto.setSearchType("P"); dto.setMonth("June"); dto.setUserType("A");
	 * dto.setOfficeId("SRO-01"); dto.setFromDate("03/04/2008");
	 * dto.setToDate("05/04/2008"); dto.setDistrictId("DD");
	 * dto.setTehsilId("TT"); dto.setWardPatwariId("WW");
	 * dto.setMohallaId("MM"); ArrayList list = bd.getMarketTrendReport(dto); }
	 */

	// added BY SHREERAJ
	public ArrayList getSearchReqReport(String froDate, String toDate, String lang) throws Exception {
		ArrayList reportFinalList = new ArrayList();
		ArrayList ret = new ArrayList();
		ReportDAO dao = new ReportDAO();
		int sum = 0;

		ArrayList reportList = dao.getSearchReqReport(froDate, toDate);
		try {
			// ArrayList report=new ArrayList();
			ArrayList report = (ArrayList) reportList.get(0);
			ArrayList report1 = (ArrayList) reportList.get(1);
			for (int i = 0; i < report.size(); i++) {
				ArrayList reportsub = (ArrayList) report.get(i);
				ArrayList ownList1 = new ArrayList();
				String fullName = "";
				MISReportDTO misDTO = new MISReportDTO();
				misDTO.setDate((String) reportsub.get(0));
				misDTO.setUserID((String) reportsub.get(2));
				if (lang.equalsIgnoreCase("en")) {
					misDTO.setDeedName((String) reportsub.get(3));
					misDTO.setInstName((String) reportsub.get(4));
				} else {
					misDTO.setDeedName((String) reportsub.get(6));
					misDTO.setInstName((String) reportsub.get(7));
				}
				misDTO.setCount((String) reportsub.get(5));
				ArrayList reportsub1 = (ArrayList) report1.get(i);
				int nodoc = Integer.parseInt((String) reportsub.get(5));
				sum += nodoc;

				for (int j = 0; j < reportsub1.size(); j++) {
					MISReportDTO misDTO1 = new MISReportDTO();

					ArrayList ownerList = (ArrayList) reportsub1.get(j);
					misDTO1.setOwnName((String) ownerList.get(0));
					ownList1.add(misDTO1);
				}
				// misDTO.setOwnerNameList((ArrayList)ownList1);
				String name[] = new String[ownList1.size()];
				for (int k = 0; k < ownList1.size(); k++) {

					MISReportDTO mrt = (MISReportDTO) ownList1.get(k);
					// System.out.println();
					name[k] = mrt.getOwnName();
					// fullName=(k+1)+". "+name[k]+"\n";
				}
				for (int l = 0; l < name.length; l++) {
					String s = name[l];

					fullName = fullName.concat("\n" + (l + 1) + ". " + s + "\n\n");

				}

				misDTO.setOwnName(fullName);
				reportFinalList.add(misDTO);
				// reportFinalList.add(ownList1);
			}
			ret.add(String.valueOf(sum));
			ret.add(reportFinalList);
		} catch (Exception e) {
			logger.error(e);

		}
		return ret;

	}

	public ArrayList getRegCopyReport(String froDate, String toDate) throws Exception {
		ArrayList reportFinalList = new ArrayList();
		ArrayList ret = new ArrayList();
		ReportDAO dao = new ReportDAO();
		int sum = 0;

		ArrayList reportList = dao.getRegCopyReport(froDate, toDate);
		try {
			// ArrayList report=new ArrayList();
			ArrayList report = (ArrayList) reportList.get(0);
			ArrayList report1 = (ArrayList) reportList.get(1);
			for (int i = 0; i < report.size(); i++) {
				ArrayList reportsub = (ArrayList) report.get(i);
				ArrayList ownList1 = new ArrayList();
				String fullName = "";
				MISReportDTO misDTO = new MISReportDTO();
				misDTO.setDate((String) reportsub.get(0));
				misDTO.setUserID((String) reportsub.get(1));
				misDTO.setRegId((String) reportsub.get(2));
				misDTO.setDeedName((String) reportsub.get(3));
				misDTO.setInstName((String) reportsub.get(4));
				misDTO.setCount((String) reportsub.get(5));
				ArrayList reportsub1 = (ArrayList) report1.get(i);
				int nodoc = Integer.parseInt((String) reportsub.get(5));
				sum += nodoc;

				for (int j = 0; j < reportsub1.size(); j++) {
					MISReportDTO misDTO1 = new MISReportDTO();

					ArrayList ownerList = (ArrayList) reportsub1.get(j);
					misDTO1.setOwnName((String) ownerList.get(0));
					ownList1.add(misDTO1);
				}
				// misDTO.setOwnerNameList((ArrayList)ownList1);
				String name[] = new String[ownList1.size()];
				for (int k = 0; k < ownList1.size(); k++) {

					MISReportDTO mrt = (MISReportDTO) ownList1.get(k);
					// System.out.println();
					name[k] = mrt.getOwnName();
					// fullName=(k+1)+". "+name[k]+"\n";
				}
				for (int l = 0; l < name.length; l++) {
					String s = name[l];

					fullName = fullName.concat((l + 1) + ". " + s + "\n\n");

				}

				misDTO.setOwnName(fullName);
				reportFinalList.add(misDTO);
				// reportFinalList.add(ownList1);
			}
			ret.add(String.valueOf(sum));
			ret.add(reportFinalList);
		} catch (Exception e) {
			logger.error(e);

		}
		return ret;

	}

	public ArrayList getSroList(String tehsilID, String language) throws Exception {
		ReportDAO dao = new ReportDAO();

		ArrayList listnew = new ArrayList();
		ArrayList list = dao.getSroList(tehsilID, language);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				logger.debug("in getSroList bd for loop start:-" + list.get(i));
				ArrayList reslist = (ArrayList) list.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				misDTO.setOfficeId((String) reslist.get(0));
				misDTO.setOfficeName((String) reslist.get(1));
				listnew.add(misDTO);

			}
		}
		return listnew;
	}

	public ArrayList getRevRcptSROReport(String month, String year) throws Exception {

		ReportDAO dao = new ReportDAO();
		MISReportDTO misDTO1 = new MISReportDTO();
		ArrayList listnew = new ArrayList();
		ArrayList list = dao.getRevRcptSROReport(month, year);
		ArrayList subList1 = (ArrayList) list.get(0);
		ArrayList subList2 = (ArrayList) list.get(1);
		ArrayList subList3 = (ArrayList) list.get(2);
		ArrayList subList4 = (ArrayList) list.get(3);
		try {
			for (int i = 0; i < subList1.size(); i++) {
				MISReportDTO misDTO = new MISReportDTO();

				if (subList1 != null && subList1.size() > 0) {

					ArrayList undList = (ArrayList) subList1.get(i);
					String areaType = (String) undList.get(2);
					String sroName = (String) undList.get(0);
					if ((i + 1) < subList1.size()) {
						ArrayList undList1 = (ArrayList) subList1.get(i + 1);
						String areaTypeplus = (String) undList1.get(2);
						String areaType1 = (String) undList1.get(2);

						String sroName1 = (String) undList1.get(0);

						if (sroName.equals(sroName1) && !areaType.equals(areaType1)) {
							String totIncMonth = String.valueOf(BigDecimal.valueOf(Double.parseDouble((String) undList.get(1)) + Double.parseDouble((String) undList1.get(1))).toPlainString());
							String totNoDocMonth = String.valueOf(BigDecimal.valueOf(Double.parseDouble((String) undList.get(3)) + Double.parseDouble((String) undList1.get(3))).toPlainString());

							misDTO.setSroName(sroName);
							misDTO.setIncMonthTotal(totIncMonth);
							misDTO.setNoDocMonthTotal(totNoDocMonth);
							/*
							 * misDTO.setIncMonthUrban((String)undList1.get(1));
							 * misDTO
							 * .setNoDocMonthUrban((String)undList1.get(3));
							 */
							if (areaType.equals("1")) {
								misDTO.setIncMonthRural((String) undList.get(1));
								misDTO.setNoDocMonthRural((String) undList.get(3));
							}
							if (areaType.equals("2")) {
								misDTO.setIncMonthUrban((String) undList.get(1));
								misDTO.setNoDocMonthUrban((String) undList.get(3));

							}
							if (areaTypeplus.equals("2")) {
								misDTO.setIncMonthUrban((String) undList1.get(1));
								misDTO.setNoDocMonthUrban((String) undList1.get(3));
							}
							if (areaTypeplus.equals("1")) {
								misDTO.setIncMonthRural((String) undList1.get(1));
								misDTO.setNoDocMonthRural((String) undList1.get(3));
							}
							i = i + 1;
						} else if (!sroName.equals(sroName1) && !areaType.equals(areaType1)) {

							misDTO.setSroName(sroName);

							if (areaType.equals("1")) {
								misDTO.setIncMonthRural((String) undList.get(1));
								misDTO.setNoDocMonthRural((String) undList.get(3));
								misDTO.setIncMonthUrban("--");
								misDTO.setNoDocMonthUrban("--");
								misDTO.setIncMonthTotal((String) undList.get(1));
								misDTO.setNoDocMonthTotal((String) undList.get(3));

							}
							if (areaType.equals("2")) {
								misDTO.setIncMonthRural("--");
								misDTO.setNoDocMonthRural("--");
								misDTO.setIncMonthUrban((String) undList1.get(1));
								misDTO.setNoDocMonthUrban((String) undList1.get(3));
								misDTO.setIncMonthTotal((String) undList1.get(1));
								misDTO.setNoDocMonthTotal((String) undList1.get(3));
							}

						} else if (!sroName.equals(sroName1) && areaType.equals(areaType1)) {
							if (i == 0) {
								misDTO.setSroName(sroName);
								misDTO.setIncMonthTotal((String) undList.get(1));
								misDTO.setNoDocMonthTotal((String) undList.get(3));
								/*
								 * misDTO.setIncMonthUrban((String)undList1.get(1
								 * ));
								 * misDTO.setNoDocMonthUrban((String)undList1
								 * .get(3));
								 */
								if (areaType.equals("1")) {
									misDTO.setIncMonthRural((String) undList.get(1));
									misDTO.setNoDocMonthRural((String) undList.get(3));
									misDTO.setIncMonthUrban("--");
									misDTO.setNoDocMonthUrban("--");

								}
								if (areaType.equals("2")) {
									misDTO.setIncMonthUrban((String) undList.get(1));
									misDTO.setNoDocMonthUrban((String) undList.get(3));
									misDTO.setIncMonthRural("--");
									misDTO.setNoDocMonthRural("--");
								}
							} else {
								misDTO.setSroName(sroName1);
								misDTO.setIncMonthTotal((String) undList1.get(1));
								misDTO.setNoDocMonthTotal((String) undList1.get(3));
								/*
								 * misDTO.setIncMonthUrban((String)undList1.get(1
								 * ));
								 * misDTO.setNoDocMonthUrban((String)undList1
								 * .get(3));
								 */
								if (areaType.equals("1")) {
									misDTO.setIncMonthRural((String) undList1.get(1));
									misDTO.setNoDocMonthRural((String) undList1.get(3));
								}
								if (areaType.equals("2")) {
									misDTO.setIncMonthUrban((String) undList1.get(1));
									misDTO.setNoDocMonthUrban((String) undList1.get(3));
								}
							}
						} else {
							misDTO = null;
							continue;
						}
					} else {
						misDTO.setSroName(sroName);
						misDTO.setIncMonthTotal((String) undList.get(1));
						misDTO.setNoDocMonthTotal((String) undList.get(3));
						if (areaType.equals("1")) {
							misDTO.setIncMonthRural((String) undList.get(1));
							misDTO.setNoDocMonthRural((String) undList.get(3));
							misDTO.setIncMonthUrban("--");
							misDTO.setNoDocMonthUrban("--");
						}
						if (areaType.equals("2")) {
							misDTO.setIncMonthUrban((String) undList.get(1));
							misDTO.setNoDocMonthUrban((String) undList.get(3));
							misDTO.setIncMonthRural("--");
							misDTO.setNoDocMonthRural("--");
						}

					}
					/*
					 * else{ misDTO.setSroName(sroName);
					 * misDTO.setIncMonthTotal((String)undList.get(1));
					 * misDTO.setNoDocMonthTotal((String)undList.get(3)); }
					 */
					/*
					 * if(areaType.equals("1")){
					 * misDTO.setIncMonthRural((String)undList.get(1));
					 * misDTO.setNoDocMonthRural((String)undList.get(3)); }
					 * if(areaType.equals("2")){
					 * misDTO.setIncMonthUrban((String)undList.get(1));
					 * misDTO.setNoDocMonthUrban((String)undList.get(3)); }
					 */
					// misDTO1.setMisReportDTO(misDTO);
				} else {
					misDTO.setIncMonthTotal("0");
					misDTO.setNoDocMonthTotal("0");
					misDTO.setIncMonthRural("0");
					misDTO.setNoDocMonthRural("0");
					misDTO.setIncMonthUrban("0");
					misDTO.setNoDocMonthUrban("0");
				}
				if (subList2 != null && subList2.size() > 0 && i < subList2.size()) {

					ArrayList undList = (ArrayList) subList2.get(i);
					String areaType = (String) undList.get(2);
					String sroName = (String) undList.get(0);
					if ((i + 1) < subList2.size()) {

						ArrayList undList1 = (ArrayList) subList2.get(i + 1);
						String areaTypeplus = (String) undList1.get(2);
						String areaType1 = (String) undList1.get(2);

						String sroName1 = (String) undList1.get(0);

						if (sroName.equals(sroName1) && !areaType.equals(areaType1)) {
							String totIncMonth = String.valueOf(BigDecimal.valueOf(Double.parseDouble((String) undList.get(1)) + Double.parseDouble((String) undList1.get(1))).toPlainString());
							String totNoDocMonth = String.valueOf(BigDecimal.valueOf(Double.parseDouble((String) undList.get(3)) + Double.parseDouble((String) undList1.get(3))).toPlainString());

							misDTO.setSroName(sroName);
							misDTO.setIncPrvMonthTotal(totIncMonth);
							misDTO.setNoDocPrvMonthTotal(totNoDocMonth);
							/*
							 * misDTO.setIncPrvMonthUrban((String)undList1.get(1)
							 * );
							 * misDTO.setNoDocPrvMonthUrban((String)undList1.get
							 * (3));
							 */
							if (areaType.equals("1")) {
								misDTO.setIncPrvMonthRural((String) undList.get(1));
								misDTO.setNoDocPrvMonthRural((String) undList.get(3));
							}
							if (areaType.equals("2")) {
								misDTO.setIncPrvMonthUrban((String) undList.get(1));
								misDTO.setNoDocPrvMonthUrban((String) undList.get(3));
							}
							if (areaTypeplus.equals("2")) {
								misDTO.setIncPrvMonthUrban((String) undList1.get(1));
								misDTO.setIncPrvMonthUrban((String) undList1.get(3));
							}
						}
						if (!sroName.equals(sroName1) && areaType.equals(areaType1)) {
							misDTO.setSroName(sroName1);
							misDTO.setIncPrvMonthTotal((String) undList1.get(1));
							misDTO.setNoDocPrvMonthTotal((String) undList1.get(3));
							/*
							 * misDTO.setIncPrvMonthUrban((String)undList1.get(1)
							 * );
							 * misDTO.setNoDocPrvMonthUrban((String)undList1.get
							 * (3));
							 */
							if (areaType.equals("1")) {
								misDTO.setIncPrvMonthRural((String) undList.get(1));
								misDTO.setNoDocPrvMonthRural((String) undList.get(3));
								misDTO.setIncPrvMonthUrban("0");
								misDTO.setNoDocPrvMonthUrban("0");
							}
							if (areaType.equals("2")) {
								misDTO.setIncPrvMonthUrban((String) undList1.get(1));
								misDTO.setNoDocPrvMonthUrban((String) undList1.get(3));
								misDTO.setIncPrvMonthRural("0");
								misDTO.setNoDocPrvMonthRural("0");
							}
						}
					} else {
						misDTO.setSroName(sroName);
						misDTO.setIncPrvMonthTotal((String) undList.get(1));
						misDTO.setNoDocPrvMonthTotal((String) undList.get(3));
						if (areaType.equals("1")) {
							misDTO.setIncPrvMonthRural((String) undList.get(1));
							misDTO.setNoDocPrvMonthRural((String) undList.get(3));
							misDTO.setIncPrvMonthUrban("0");
							misDTO.setNoDocPrvMonthUrban("0");
						}
						if (areaType.equals("2")) {
							misDTO.setIncPrvMonthUrban((String) undList.get(1));
							misDTO.setNoDocPrvMonthUrban((String) undList.get(3));
							misDTO.setIncPrvMonthRural("0");
							misDTO.setNoDocPrvMonthRural("0");
						}
					}
					/*
					 * else{ misDTO.setSroName(sroName);
					 * misDTO.setIncPrvMonthTotal((String)undList.get(1));
					 * misDTO.setNoDocPrvMonthTotal((String)undList.get(3)); }
					 */
					/*
					 * if(areaType.equals("1")){
					 * misDTO.setIncPrvMonthRural((String)undList.get(1));
					 * misDTO.setNoDocPrvMonthRural((String)undList.get(3)); }
					 * if(areaType.equals("2")){
					 * misDTO.setIncPrvMonthUrban((String)undList.get(1));
					 * misDTO.setNoDocPrvMonthUrban((String)undList.get(3)); }
					 */
					// misDTO1.setMisReportDTO(misDTO);
				} else {
					misDTO.setIncPrvMonthTotal("0");
					misDTO.setNoDocPrvMonthTotal("0");
					misDTO.setIncPrvMonthRural("0");
					misDTO.setNoDocPrvMonthRural("0");
					misDTO.setIncPrvMonthUrban("0");
					misDTO.setNoDocPrvMonthUrban("0");
				}
				if (subList3 != null && subList3.size() > 0 && i < subList3.size()) {

					ArrayList undList = (ArrayList) subList3.get(i);
					String areaType = (String) undList.get(2);
					String sroName = (String) undList.get(0);
					if ((i + 1) < subList3.size()) {
						ArrayList undList1 = (ArrayList) subList3.get(i + 1);

						String areaType1 = (String) undList1.get(2);
						String areaTypeplus = (String) undList1.get(2);
						String sroName1 = (String) undList1.get(0);

						if (sroName.equals(sroName1) && !areaType.equals(areaType1)) {
							String totIncMonth = String.valueOf(BigDecimal.valueOf(Double.parseDouble((String) undList.get(1)) + Double.parseDouble((String) undList1.get(1))).toPlainString());
							String totNoDocMonth = String.valueOf(BigDecimal.valueOf(Double.parseDouble((String) undList.get(3)) + Double.parseDouble((String) undList1.get(3))).toPlainString());

							misDTO.setSroName(sroName);
							misDTO.setIncAprlTotal(totIncMonth);
							misDTO.setNoDocAprilTotal(totNoDocMonth);
							/*
							 * misDTO.setIncAprlUrban((String)undList1.get(1));
							 * misDTO
							 * .setNoDocAprilUrban((String)undList1.get(3));
							 */
							if (areaType.equals("1")) {
								misDTO.setIncAprlRural((String) undList.get(1));
								misDTO.setNoDocAprilRural((String) undList.get(3));
							}
							if (areaType.equals("2")) {
								misDTO.setIncAprlUrban((String) undList.get(1));
								misDTO.setNoDocAprilUrban((String) undList.get(3));
							}
							if (areaTypeplus.equals("2")) {
								misDTO.setIncAprlUrban((String) undList1.get(1));
								misDTO.setNoDocAprilUrban((String) undList1.get(3));
							}
						}
						if (!sroName.equals(sroName1) && areaType.equals(areaType1)) {
							misDTO.setSroName(sroName);
							misDTO.setIncAprlTotal((String) undList1.get(1));
							misDTO.setNoDocAprilTotal((String) undList1.get(3));
							/*
							 * misDTO.setIncAprlUrban((String)undList1.get(1));
							 * misDTO
							 * .setNoDocAprilUrban((String)undList1.get(3));
							 */
							if (areaType.equals("1")) {
								misDTO.setIncAprlRural((String) undList.get(1));
								misDTO.setNoDocAprilRural((String) undList.get(3));
							}
							if (areaType.equals("2")) {
								misDTO.setIncAprlUrban((String) undList1.get(1));
								misDTO.setNoDocAprilUrban((String) undList1.get(3));
							}
						}
					} else {
						misDTO.setSroName(sroName);
						misDTO.setIncAprlTotal((String) undList.get(1));
						misDTO.setNoDocAprilTotal((String) undList.get(3));
						if (areaType.equals("1")) {
							misDTO.setIncAprlRural((String) undList.get(1));
							misDTO.setNoDocAprilRural((String) undList.get(3));
							misDTO.setIncAprlUrban("0");
							misDTO.setNoDocAprilUrban("0");
						}
						if (areaType.equals("2")) {
							misDTO.setIncAprlUrban((String) undList.get(1));
							misDTO.setNoDocAprilUrban((String) undList.get(3));
							misDTO.setIncAprlRural("0");
							misDTO.setNoDocAprilRural("0");
						}
					}
					/*
					 * else{ misDTO.setSroName(sroName);
					 * misDTO.setIncAprlTotal((String)undList.get(1));
					 * misDTO.setNoDocAprilTotal((String)undList.get(3)); }
					 */

					// misDTO1.setMisReportDTO(misDTO);
				} else {
					misDTO.setIncAprlTotal("0");
					misDTO.setNoDocAprilTotal("0");
					misDTO.setNoDocAprilRural("0");
					misDTO.setIncAprlRural("0");
					misDTO.setIncAprlUrban("0");
					misDTO.setNoDocAprilUrban("0");
				}
				if (subList4 != null && subList4.size() > 0 && i < subList4.size()) {

					ArrayList undList = (ArrayList) subList4.get(i);
					String areaType = (String) undList.get(2);
					String sroName = (String) undList.get(0);
					if ((i + 1) < subList4.size()) {
						ArrayList undList1 = (ArrayList) subList4.get(i + 1);

						String areaType1 = (String) undList1.get(2);
						String areaTypeplus = (String) undList1.get(2);
						String sroName1 = (String) undList1.get(0);

						if (sroName.equals(sroName1) && !areaType.equals(areaType1)) {
							String totIncMonth = String.valueOf(BigDecimal.valueOf(Double.parseDouble((String) undList.get(1)) + Double.parseDouble((String) undList1.get(1))).toPlainString());
							String totNoDocMonth = String.valueOf(BigDecimal.valueOf(Double.parseDouble((String) undList.get(3)) + Double.parseDouble((String) undList1.get(3))).toPlainString());

							misDTO.setSroName(sroName);
							misDTO.setIncPrvAprlTotal(totIncMonth);
							misDTO.setNoDocPrvAprilTotal(totNoDocMonth);
							/*
							 * misDTO.setIncAprlUrban((String)undList1.get(1));
							 * misDTO
							 * .setNoDocAprilUrban((String)undList1.get(3));
							 */
							if (areaType.equals("1")) {
								misDTO.setIncPrvAprlRural((String) undList.get(1));
								misDTO.setNoDocPrvAprilRural((String) undList.get(3));
							}
							if (areaType.equals("2")) {
								misDTO.setIncPrvAprlUrban((String) undList.get(1));
								misDTO.setNoDocPrvAprilUrban((String) undList.get(3));
							}
							if (areaTypeplus.equals("2")) {
								misDTO.setIncPrvAprlUrban((String) undList1.get(1));
								misDTO.setNoDocPrvAprilUrban((String) undList1.get(3));
							}
						}
						if (!sroName.equals(sroName1) && areaType.equals(areaType1)) {
							misDTO.setSroName(sroName);
							misDTO.setIncPrvAprlTotal((String) undList1.get(1));
							misDTO.setNoDocPrvAprilTotal((String) undList1.get(3));
							/*
							 * misDTO.setIncAprlUrban((String)undList1.get(1));
							 * misDTO
							 * .setNoDocAprilUrban((String)undList1.get(3));
							 */
							if (areaType.equals("1")) {
								misDTO.setIncPrvAprlRural((String) undList.get(1));
								misDTO.setNoDocPrvAprilRural((String) undList.get(3));
							}
							if (areaType.equals("2")) {
								misDTO.setIncPrvAprlUrban((String) undList1.get(1));
								misDTO.setNoDocPrvAprilUrban((String) undList1.get(3));
							}
						}
					} else {
						misDTO.setSroName(sroName);
						misDTO.setIncPrvAprlTotal((String) undList.get(1));
						misDTO.setNoDocPrvAprilTotal((String) undList.get(3));
						if (areaType.equals("1")) {
							misDTO.setIncPrvAprlRural((String) undList.get(1));
							misDTO.setNoDocPrvAprilRural((String) undList.get(3));
							misDTO.setIncPrvAprlUrban("0");
							misDTO.setNoDocPrvAprilUrban("0");
						}
						if (areaType.equals("2")) {
							misDTO.setIncPrvAprlUrban((String) undList.get(1));
							misDTO.setNoDocPrvAprilUrban((String) undList.get(3));
							misDTO.setIncPrvAprlRural("0");
							misDTO.setNoDocPrvAprilRural("0");
						}
					}

					/*
					 * else{ misDTO.setSroName(sroName);
					 * misDTO.setIncAprlTotal((String)undList.get(1));
					 * misDTO.setNoDocAprilTotal((String)undList.get(3)); }
					 */

					// misDTO1.setMisReportDTO(misDTO);
				} else {
					misDTO.setIncPrvAprlTotal("0");
					misDTO.setNoDocPrvAprilTotal("0");
					misDTO.setIncPrvAprlRural("0");
					misDTO.setNoDocPrvAprilRural("0");
					misDTO.setIncPrvAprlUrban("0");
					misDTO.setNoDocPrvAprilUrban("0");
				}
				String compIncMnthRural = "", compIncMnthUrban = "", compIncMnthTotal = "";
				String compIncAprlRural = "", compIncAprlUrban = "", compIncAprlTotal = "";
				String compNoDocRural = "", compNoDocUrban = "", compNoDocTotal = "";

				//
				if (misDTO.getIncPrvMonthRural() != null && !misDTO.getIncPrvMonthRural().equals("0")) {
					compIncMnthRural = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvMonthRural()) - Double.parseDouble(misDTO.getIncMonthRural())) / Double.parseDouble(misDTO.getIncPrvMonthRural())) * 100)));
				} else {
					compIncMnthRural = "--";
				}

				if (misDTO.getIncPrvMonthUrban() != null && !misDTO.getIncPrvMonthUrban().equals("0")) {
					compIncMnthUrban = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvMonthUrban()) - Double.parseDouble(misDTO.getIncMonthUrban())) / Double.parseDouble(misDTO.getIncPrvMonthUrban())) * 100)));
				} else {
					compIncMnthUrban = "--";
				}
				if (misDTO.getIncPrvMonthTotal() != null && !misDTO.getIncPrvMonthTotal().equals("0")) {
					compIncMnthTotal = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvMonthTotal()) - Double.parseDouble(misDTO.getIncMonthTotal())) / Double.parseDouble(misDTO.getIncPrvMonthTotal())) * 100)));
				} else {
					compIncMnthTotal = "--";
				}

				//		

				if (misDTO.getIncPrvAprlRural() != null && !misDTO.getIncPrvAprlRural().equals("0")) {
					compIncAprlRural = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvAprlRural()) - Double.parseDouble(misDTO.getIncAprlRural())) / Double.parseDouble(misDTO.getIncPrvAprlRural())) * 100)));
				} else {
					compIncAprlRural = "--";
				}

				if (misDTO.getIncPrvAprlUrban() != null && !misDTO.getIncPrvAprlUrban().equals("0")) {
					compIncAprlUrban = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvAprlUrban()) - Double.parseDouble(misDTO.getIncAprlUrban())) / Double.parseDouble(misDTO.getIncPrvAprlUrban())) * 100)));
				} else {
					compIncAprlUrban = "--";
				}
				if (misDTO.getIncPrvAprlTotal() != null && !misDTO.getIncPrvAprlTotal().equals("0")) {
					compIncAprlTotal = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvAprlTotal()) - Double.parseDouble(misDTO.getIncAprlTotal())) / Double.parseDouble(misDTO.getIncPrvAprlTotal())) * 100)));
				} else {
					compIncAprlTotal = "--";
				}

				//

				if (misDTO.getNoDocPrvMonthRural() != null && !misDTO.getNoDocPrvMonthRural().equals("0")) {
					compNoDocRural = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getNoDocPrvMonthRural()) - Double.parseDouble(misDTO.getNoDocMonthRural())) / Double.parseDouble(misDTO.getNoDocPrvMonthRural())) * 100)));
				} else {
					compNoDocRural = "--";
				}

				if (misDTO.getNoDocPrvMonthUrban() != null && !misDTO.getNoDocPrvMonthUrban().equals("0")) {
					compNoDocUrban = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getNoDocPrvMonthUrban()) - Double.parseDouble(misDTO.getNoDocMonthUrban())) / Double.parseDouble(misDTO.getNoDocPrvMonthUrban())) * 100)));
				} else {
					compNoDocUrban = "--";
				}
				if (misDTO.getNoDocPrvMonthTotal() != null && !misDTO.getNoDocPrvMonthTotal().equals("0")) {
					compNoDocTotal = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getNoDocPrvMonthTotal()) - Double.parseDouble(misDTO.getNoDocMonthTotal())) / Double.parseDouble(misDTO.getNoDocPrvMonthTotal())) * 100)));
				} else {
					compNoDocTotal = "--";
				}

				misDTO.setIncCompMonthRural(compIncMnthRural.concat("%"));
				misDTO.setIncCompMonthUrban(compIncMnthUrban.concat("%"));
				misDTO.setIncCompMonthTotal(compIncMnthTotal.concat("%"));
				misDTO.setIncCompAprlRural(compIncAprlRural.concat("%"));
				misDTO.setIncCompAprlUrban(compIncAprlUrban.concat("%"));
				misDTO.setIncCompAprlTotal(compIncAprlTotal.concat("%"));
				misDTO.setNoDocCompRural(compNoDocRural.concat("%"));
				misDTO.setNoDocCompUrban(compNoDocUrban.concat("%"));
				misDTO.setNoDocCompTotal(compNoDocTotal.concat("%"));

				if (!(subList4 != null && subList4.size() > 0)) {
					misDTO.setIncPrvAprlTotal("--");
					misDTO.setNoDocPrvAprilTotal("--");
					misDTO.setIncPrvAprlRural("--");
					misDTO.setNoDocPrvAprilRural("--");
					misDTO.setIncPrvAprlUrban("--");
					misDTO.setNoDocPrvAprilUrban("--");
				}
				if (!(subList3 != null && subList3.size() > 0)) {
					// misDTO.setSroName("--");
					misDTO.setIncAprlTotal("--");
					misDTO.setNoDocAprilTotal("--");
					misDTO.setNoDocAprilRural("--");
					misDTO.setIncAprlRural("--");
					misDTO.setIncAprlUrban("--");
					misDTO.setNoDocAprilUrban("--");
				}
				if (!(subList2 != null && subList2.size() > 0)) {
					// misDTO.setSroName("--");
					misDTO.setIncPrvMonthTotal("--");
					misDTO.setNoDocPrvMonthTotal("--");
					misDTO.setIncPrvMonthRural("--");
					misDTO.setNoDocPrvMonthRural("--");
					misDTO.setIncPrvMonthUrban("--");
					misDTO.setNoDocPrvMonthUrban("--");

				}
				if (!(subList1 != null && subList1.size() > 0)) {
					// misDTO.setSroName("--");
					misDTO.setIncMonthTotal("--");
					misDTO.setNoDocMonthTotal("--");
					misDTO.setIncMonthRural("--");
					misDTO.setNoDocMonthRural("--");
					misDTO.setIncMonthUrban("--");
					misDTO.setNoDocMonthUrban("--");
				}
				if (subList1.size() == 1 || i == subList1.size() - 1) {
					listnew.add(misDTO);
				} else {
					/*
					 * if(i==subList1.size()-1){ break; }
					 */
					listnew.add(misDTO);
				}
			}
		} catch (Exception e) {

		}
		return listnew;
	}

	public ArrayList getSROStampRegReport(String month, String year) throws Exception {
		ArrayList ret = new ArrayList();
		ReportDAO dao = new ReportDAO();

		ArrayList reportList = dao.getSROStampRegReport(month, year);
		ArrayList subList1 = (ArrayList) reportList.get(0);
		ArrayList subList2 = (ArrayList) reportList.get(1);
		ArrayList subList3 = (ArrayList) reportList.get(2);
		ArrayList subList4 = (ArrayList) reportList.get(3);
		try {
			for (int i = 0; i < subList1.size(); i++) {
				MISReportDTO misDTO = new MISReportDTO();
				if (subList1.size() > 0) {
					ArrayList undList = (ArrayList) subList1.get(i);
					misDTO.setSroName((String) undList.get(0));
					misDTO.setIncMonthRural((String) undList.get(1));
					misDTO.setIncMonthUrban((String) undList.get(2));
					misDTO.setNoDocMonthTotal((String) undList.get(3));
					misDTO.setIncMonthTotal(String.valueOf((BigDecimal.valueOf(Double.parseDouble((String) undList.get(1)) + Double.parseDouble((String) undList.get(2))).toPlainString())));
				} else {
					misDTO.setIncMonthRural("0");
					misDTO.setIncMonthUrban("0");
					misDTO.setNoDocMonthTotal("0");
					misDTO.setIncMonthTotal("0");
				}

				if (subList2.size() > 0) {
					ArrayList undList = (ArrayList) subList2.get(i);
					misDTO.setSroName((String) undList.get(0));
					misDTO.setIncPrvMonthRural((String) undList.get(1));
					misDTO.setIncPrvMonthUrban((String) undList.get(2));
					misDTO.setNoDocPrvMonthTotal((String) undList.get(3));
					misDTO.setIncPrvMonthTotal(String.valueOf((BigDecimal.valueOf(Double.parseDouble((String) undList.get(1)) + Double.parseDouble((String) undList.get(2))).toPlainString())));
				} else {
					misDTO.setIncPrvMonthRural("0");
					misDTO.setIncPrvMonthUrban("0");
					misDTO.setNoDocPrvMonthTotal("0");
					misDTO.setIncPrvMonthTotal("0");
				}
				if (subList3.size() > 0) {
					ArrayList undList = (ArrayList) subList3.get(i);
					misDTO.setSroName((String) undList.get(0));
					misDTO.setIncAprlRural((String) undList.get(1));
					misDTO.setIncAprlUrban((String) undList.get(2));
					misDTO.setNoDocAprilTotal((String) undList.get(3));
					misDTO.setIncAprlTotal(String.valueOf((BigDecimal.valueOf(Double.parseDouble((String) undList.get(1)) + Double.parseDouble((String) undList.get(2))).toPlainString())));
				} else {
					misDTO.setIncAprlRural("0");
					misDTO.setIncAprlUrban("0");
					misDTO.setNoDocAprilTotal("0");
					misDTO.setIncAprlTotal("0");
				}
				if (subList4.size() > 0) {
					ArrayList undList = (ArrayList) subList4.get(i);
					misDTO.setSroName((String) undList.get(0));
					misDTO.setIncPrvAprlRural((String) undList.get(1));
					misDTO.setIncPrvAprlUrban((String) undList.get(2));
					misDTO.setNoDocPrvAprilTotal((String) undList.get(3));
					misDTO.setIncPrvAprlTotal(String.valueOf((BigDecimal.valueOf(Double.parseDouble((String) undList.get(1)) + Double.parseDouble((String) undList.get(2))).toPlainString())));
				} else {
					misDTO.setIncPrvAprlRural("0");
					misDTO.setIncPrvAprlUrban("0");
					misDTO.setNoDocPrvAprilTotal("0");
					misDTO.setIncPrvAprlTotal("0");
				}
				String compIncMnthRural = "", compIncMnthUrban = "", compIncMnthTotal = "";
				String compIncAprlRural = "", compIncAprlUrban = "", compIncAprlTotal = "";
				String compNoDocTotal = "";
				//
				if (!misDTO.getIncPrvMonthRural().equals("0")) {
					compIncMnthRural = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvMonthRural()) - Double.parseDouble(misDTO.getIncMonthRural())) / Double.parseDouble(misDTO.getIncPrvMonthRural())) * 100)));
					if (compIncMnthRural.contains(".")) {
						String str[] = compIncMnthRural.split(".");
						String s = str[0].concat(str[1].substring(0, 2));
					}

				} else {
					compIncMnthRural = "--";
				}

				if (!misDTO.getIncPrvMonthUrban().equals("0")) {
					compIncMnthUrban = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvMonthUrban()) - Double.parseDouble(misDTO.getIncMonthUrban())) / Double.parseDouble(misDTO.getIncPrvMonthUrban())) * 100)));
					if (compIncMnthUrban.contains(".")) {
						String str[] = compIncMnthRural.split(".");
						String s = str[0].concat(str[1].substring(0, 2));
					}
				} else {
					compIncMnthUrban = "--";
				}
				if (!misDTO.getIncPrvMonthTotal().equals("0")) {
					compIncMnthTotal = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvMonthTotal()) - Double.parseDouble(misDTO.getIncMonthTotal())) / Double.parseDouble(misDTO.getIncPrvMonthTotal())) * 100)));
				} else {
					compIncMnthTotal = "--";
				}

				//		

				if (!misDTO.getIncPrvAprlRural().equals("0")) {
					compIncAprlRural = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvAprlRural()) - Double.parseDouble(misDTO.getIncAprlRural())) / Double.parseDouble(misDTO.getIncPrvAprlRural())) * 100)));

				} else {
					compIncAprlRural = "--";
				}

				if (!misDTO.getIncPrvAprlUrban().equals("0")) {
					compIncAprlUrban = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvAprlUrban()) - Double.parseDouble(misDTO.getIncAprlUrban())) / Double.parseDouble(misDTO.getIncPrvAprlUrban())) * 100)));
					if (compIncAprlUrban.contains(".")) {
						String str[] = compIncAprlUrban.split(".");
						String s = str[0].concat(str[1].substring(0, 2));
					}
				} else {
					compIncAprlUrban = "--";
				}
				if (!misDTO.getIncPrvAprlTotal().equals("0")) {
					compIncAprlTotal = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getIncPrvAprlTotal()) - Double.parseDouble(misDTO.getIncAprlTotal())) / Double.parseDouble(misDTO.getIncPrvAprlTotal())) * 100)));
					if (compIncAprlTotal.contains(".")) {
						String str[] = compIncAprlTotal.split(".");
						String s = str[0].concat(str[1].substring(0, 2));
					}
				} else {
					compIncAprlTotal = "--";
				}

				//
				if (!misDTO.getNoDocPrvMonthTotal().equals("0")) {
					compNoDocTotal = String.valueOf(Math.abs(Math.round(((Double.parseDouble(misDTO.getNoDocPrvMonthTotal()) - Double.parseDouble(misDTO.getNoDocMonthTotal())) / Double.parseDouble(misDTO.getNoDocPrvMonthTotal())) * 100)));
				} else {
					compNoDocTotal = "--";
				}

				misDTO.setIncCompMonthRural(compIncMnthRural.concat("%"));
				misDTO.setIncCompMonthUrban(compIncMnthUrban.concat("%"));
				misDTO.setIncCompMonthTotal(compIncMnthTotal.concat("%"));
				misDTO.setIncCompAprlRural(compIncAprlRural.concat("%"));
				misDTO.setIncCompAprlUrban(compIncAprlUrban.concat("%"));
				misDTO.setIncCompAprlTotal(compIncAprlTotal.concat("%"));
				misDTO.setNoDocCompTotal(compNoDocTotal.concat("%"));

				if (!(subList4.size() > 0 && subList4 != null)) {
					misDTO.setIncPrvAprlTotal("--");
					misDTO.setNoDocPrvAprilTotal("--");
					misDTO.setIncPrvAprlRural("--");
					misDTO.setIncPrvAprlUrban("--");
				}
				if (!(subList3.size() > 0 && subList3 != null)) {
					misDTO.setIncAprlTotal("--");
					misDTO.setNoDocAprilTotal("--");
					misDTO.setIncAprlRural("--");
					misDTO.setIncAprlUrban("--");
				}
				if (!(subList2.size() > 0 && subList2 != null)) {
					misDTO.setIncPrvMonthTotal("--");
					misDTO.setNoDocPrvMonthTotal("--");
					misDTO.setIncPrvMonthRural("--");
					misDTO.setIncPrvMonthUrban("--");

				}
				if (!(subList1.size() > 0 && subList1 != null)) {
					misDTO.setIncMonthTotal("--");
					misDTO.setNoDocMonthTotal("--");
					misDTO.setIncMonthRural("--");
					misDTO.setIncMonthUrban("--");
				}
				ret.add(misDTO);
			}
		} catch (Exception e) {

		}
		return ret;
	}

	public ArrayList getRegistrationReport(MISReportForm eForm, String lang) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getRegistrationReport(eForm);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {
				ArrayList deedList = new ArrayList();
				ArrayList subList = (ArrayList) reportList.get(i);
				HashMap<Integer, String> record = new HashMap<Integer, String>();
				MISReportDTO misDTO = new MISReportDTO();
				if ("en".equalsIgnoreCase(lang)) {
					misDTO.setDistrictName(subList.get(0).toString());
					misDTO.setTehsilName(subList.get(13).toString());
					record.put(1,"District Name~"+subList.get(0).toString());
					record.put(2,"Tehsil Name~"+subList.get(13).toString());
				} else {
					misDTO.setDistrictName(subList.get(11).toString());
					misDTO.setTehsilName(subList.get(14).toString());
					record.put(1,"District Name~"+subList.get(11).toString());
					record.put(2,"Tehsil Name~"+subList.get(14).toString());
				}
				misDTO.setDate(subList.get(1).toString());
				record.put(3,"Date~"+subList.get(1).toString());
				misDTO.setCount(subList.get(2).toString());
				record.put(4,"Deed Name~"+subList.get(2).toString());
				String deed = subList.get(3).toString();
				String deedSplit[] = deed.split("~");
				String deedInfo1 = deedSplit[1];
				String str1 = deedInfo1.substring(deedInfo1.indexOf("[") + 1, deedInfo1.indexOf("]"));
				record.put(4,"Deed Name~"+(str1));
				for (int j = 0; j < deedSplit.length; j++) {
					MISReportDTO misDTO1 = new MISReportDTO();
					String deedInfo = deedSplit[j];
					String str = deedInfo.substring(deedInfo.indexOf("[") + 1, deedInfo.indexOf("]"));
					String splitStr[] = str.split(",");
					misDTO1.setDeedCount(splitStr[0]);
					if ("en".equalsIgnoreCase(lang))
						misDTO1.setDeedName(splitStr[1]);
					else
						misDTO1.setDeedName(splitStr[3]);
					misDTO1.setDeedID(splitStr[2]);
					deedList.add(misDTO1);

				}
				misDTO.setDeedList(deedList);
				// misDTO.setStampDuty(subList.get(4).toString());
				/*
				 * misDTO.setStampDuty(BigDecimal.valueOf(
				 * Float.parseFloat(subList.get(4).toString()))
				 * .toPlainString());
				 * 
				 * misDTO.setGramDuty(BigDecimal.valueOf(
				 * Float.parseFloat(subList.get(5).toString()))
				 * .toPlainString()); misDTO.setNagarDuty(BigDecimal.valueOf(
				 * Float.parseFloat(subList.get(6).toString()))
				 * .toPlainString()); misDTO.setUpkarDuty(BigDecimal.valueOf(
				 * Float.parseFloat(subList.get(7).toString()))
				 * .toPlainString()); misDTO.setTotalDuty(BigDecimal.valueOf(
				 * Float.parseFloat(subList.get(8).toString()))
				 * .toPlainString()); misDTO.setRegFee(BigDecimal.valueOf(
				 * Float.parseFloat(subList.get(9).toString()))
				 * .toPlainString());
				 * misDTO.setDistrictID(subList.get(10).toString());
				 * misDTO.setRegDutySum(BigDecimal.valueOf(
				 * Float.parseFloat(subList.get(12).toString()))
				 * .toPlainString());
				 */
				misDTO.setStampDuty(subList.get(4).toString());
				record.put(5,"Stamp Duty~"+subList.get(4).toString());
				misDTO.setGramDuty(subList.get(5).toString());
				record.put(6,"Gram Duty~"+subList.get(5).toString());
				misDTO.setNagarDuty(subList.get(6).toString());
				record.put(7,"Nagar Duty~"+subList.get(6).toString());
				misDTO.setUpkarDuty(subList.get(7).toString());
				record.put(8,"Upkar Duty~"+subList.get(7).toString());
				misDTO.setTotalDuty(subList.get(8).toString());
				record.put(9,"Total Duty~"+subList.get(8).toString());
				misDTO.setRegFee(subList.get(9).toString());
				record.put(10,"Reg Fee~"+subList.get(9).toString());
				misDTO.setDistrictID(subList.get(10).toString());
				misDTO.setRegDutySum(subList.get(12).toString());
				misDTO.setTehsilID(subList.get(15).toString());
				misDTO.setRecordSet(record);
				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}
	// added by rustam

	public ArrayList getRegistrationSummaryReport(MISReportForm eForm, String lang) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getRegistrationSummaryReport(eForm);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {
				ArrayList deedList = new ArrayList();
				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				if ("en".equalsIgnoreCase(lang)) {
					misDTO.setDistrictName(subList.get(0).toString());
					misDTO.setTehsilName(subList.get(13).toString());
				} else {
					misDTO.setDistrictName(subList.get(11).toString());
					misDTO.setTehsilName(subList.get(14).toString());
				}
				misDTO.setDate(subList.get(1).toString());
				misDTO.setCount(subList.get(2).toString());
				String deed = subList.get(3).toString();
				String deedSplit[] = deed.split("~");
				for (int j = 0; j < deedSplit.length; j++) {
					MISReportDTO misDTO1 = new MISReportDTO();
					String deedInfo = deedSplit[j];
					String str = deedInfo.substring(deedInfo.indexOf("[") + 1, deedInfo.indexOf("]"));
					String splitStr[] = str.split(",");
					misDTO1.setDeedCount(splitStr[0]);
					if ("en".equalsIgnoreCase(lang))
						misDTO1.setDeedName(splitStr[1]);
					else
						misDTO1.setDeedName(splitStr[3]);
					misDTO1.setDeedID(splitStr[2]);
					deedList.add(misDTO1);

				}
				misDTO.setDeedList(deedList);
				misDTO.setStampDuty(subList.get(4).toString());
				misDTO.setGramDuty(subList.get(5).toString());
				misDTO.setNagarDuty(subList.get(6).toString());
				misDTO.setUpkarDuty(subList.get(7).toString());
				misDTO.setTotalDuty(subList.get(8).toString());
				misDTO.setRegFee(subList.get(9).toString());
				misDTO.setDistrictID(subList.get(10).toString());
				misDTO.setRegDutySum(subList.get(12).toString());
				misDTO.setTehsilID(subList.get(15).toString());
				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public ArrayList getDeedTypeList(String language) throws Exception {
		ArrayList tehsilList = new ArrayList();
		ReportDAO dao = new ReportDAO();
		try {
			ArrayList tmpList = dao.getDeedTypeList();
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					MISReportDTO dto = new MISReportDTO();
					dto.setDeedID((String) tmpsubList.get(0));
					if (language.equalsIgnoreCase("en"))
						dto.setDeedName((String) tmpsubList.get(1));
					else
						dto.setDeedName((String) tmpsubList.get(2));
					tehsilList.add(dto);
				}

			}
			logger.info("getPartyDetails-->" + tehsilList);
		} catch (Exception e) {
			logger.error(e);

		}
		return tehsilList;
	}

	public ArrayList getSlotRegistrationReport(MISReportForm eForm, String lang) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getSlotRegistrationReport(eForm);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {

				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				if ("en".equalsIgnoreCase(lang))
					misDTO.setDistrictName(subList.get(0).toString());
				else
					misDTO.setDistrictName(subList.get(7).toString());
				misDTO.setSlotAvailable(subList.get(1).toString());
				misDTO.setSlotBooked(subList.get(2).toString());
				misDTO.setRegComp(subList.get(3).toString());
				misDTO.setDocDelivered(subList.get(4).toString());
				misDTO.setSlotPending(subList.get(5).toString());
				misDTO.setDistrictID(subList.get(6).toString());
				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public ArrayList getSlotRegistrationReportTotal(MISReportForm eForm) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.getSlotRegistrationReportTotal(eForm);
	}

	public ArrayList getTimeRegistrationReport(MISReportForm eForm, String language) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getTimeRegistrationReport(eForm);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {
				ArrayList deedList = new ArrayList();
				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				misDTO.setRegId(subList.get(0).toString());
				if (language.equalsIgnoreCase("en"))
					misDTO.setDeedName(subList.get(1).toString());
				else
					misDTO.setDeedName(subList.get(18).toString());
				misDTO.setConsiderationAmount(BigDecimal.valueOf(Float.parseFloat(subList.get(2).toString())).toPlainString());
				misDTO.setMarketValue(BigDecimal.valueOf(Float.parseFloat(subList.get(3).toString())).toPlainString());
				misDTO.setStampDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(4).toString())).toPlainString());
				misDTO.setGramDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(5).toString())).toPlainString());
				misDTO.setNagarDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(6).toString())).toPlainString());
				misDTO.setUpkarDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(7).toString())).toPlainString());
				misDTO.setTotalDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(8).toString())).toPlainString());
				misDTO.setRegFee(BigDecimal.valueOf(Float.parseFloat(subList.get(9).toString())).toPlainString());
				misDTO.setDocInitSP(subList.get(10).toString());
				misDTO.setMakerStart(subList.get(11).toString());
				misDTO.setMakerEnd(subList.get(12).toString());
				misDTO.setCheckerStart(subList.get(13).toString());
				misDTO.setCheckerEnd(subList.get(14).toString());
				misDTO.setMakerPrintOut(subList.get(15).toString());
				misDTO.setSign(subList.get(16).toString());
				misDTO.setDocDelivered(subList.get(17).toString());
				misDTO.setMakerID(subList.get(19).toString());
				misDTO.setCheckerID(subList.get(20).toString());
				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public ArrayList getSPEstampReport(MISReportForm eForm, String language) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getSPEstampReport(eForm);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {
				ArrayList deedList = new ArrayList();
				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				if (language.equalsIgnoreCase("en"))
					misDTO.setDistrictName(subList.get(0).toString());
				else
					misDTO.setDistrictName(subList.get(1).toString());
				misDTO.setSpNumber(subList.get(2).toString());
				misDTO.setSpTotCrdLmt(subList.get(3).toString());
				if (!eForm.getRadioSearch().equalsIgnoreCase("Y")) {
					misDTO.setAmtEstampReg(subList.get(4).toString());
					misDTO.setAmtEstampUnreg(subList.get(5).toString());

					misDTO.setSpCommEarn(subList.get(6).toString());
					misDTO.setSpCrdBal(subList.get(7).toString());
					misDTO.setSpCrdPrvBal(subList.get(8).toString());
					misDTO.setCarryFwdAmt(subList.get(9).toString());
				} else {
					misDTO.setSpCommEarn(subList.get(4).toString());
					misDTO.setSpCrdBal(subList.get(5).toString());
					misDTO.setSpCrdPrvBal(subList.get(6).toString());
					misDTO.setCarryFwdAmt(subList.get(7).toString());
					misDTO.setDebFrmOthSer(subList.get(8).toString());
				}

				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public ArrayList getRegistrationReportDrill(MISReportForm eForm, String lang, String disID, String deedID, String tehsilID, String DID) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getRegistrationReportDrill(eForm, disID, deedID, tehsilID, DID);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {

				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				if ("en".equalsIgnoreCase(lang)) {
					misDTO.setDistrictName(subList.get(0).toString());
					misDTO.setDeedName(subList.get(3).toString());
				} else {
					misDTO.setDistrictName(subList.get(1).toString());
					misDTO.setDeedName(subList.get(4).toString());
				}
				misDTO.setCount(subList.get(2).toString());
				misDTO.setStampDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(5).toString())).toPlainString());
				misDTO.setGramDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(6).toString())).toPlainString());
				misDTO.setNagarDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(7).toString())).toPlainString());
				misDTO.setUpkarDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(8).toString())).toPlainString());
				misDTO.setTotalDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(9).toString())).toPlainString());
				misDTO.setRegFee(BigDecimal.valueOf(Float.parseFloat(subList.get(10).toString())).toPlainString());
				misDTO.setDate(subList.get(11).toString());
				misDTO.setRegDutySum(BigDecimal.valueOf(Float.parseFloat(subList.get(12).toString())).toPlainString());

				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public ArrayList getSlotStatReportDrill(MISReportForm eForm, String lang, String disID, String report) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getSlotStatReportDrill(eForm, disID, report);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {

				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				if ("en".equalsIgnoreCase(lang)) {
					misDTO.setDistrictName(subList.get(0).toString());
					misDTO.setDeedName(subList.get(3).toString());
				} else {
					misDTO.setDistrictName(subList.get(1).toString());
					misDTO.setDeedName(subList.get(4).toString());
				}
				misDTO.setCount(subList.get(2).toString());
				misDTO.setStampDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(5).toString())).toPlainString());
				misDTO.setGramDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(6).toString())).toPlainString());
				misDTO.setNagarDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(7).toString())).toPlainString());
				misDTO.setUpkarDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(8).toString())).toPlainString());
				misDTO.setTotalDuty(BigDecimal.valueOf(Float.parseFloat(subList.get(9).toString())).toPlainString());
				misDTO.setRegFee(BigDecimal.valueOf(Float.parseFloat(subList.get(10).toString())).toPlainString());
				misDTO.setDate(subList.get(11).toString());
				misDTO.setRegDutySum(BigDecimal.valueOf(Float.parseFloat(subList.get(12).toString())).toPlainString());
				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public String getRegTxnID(String eRegNo) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.getRegTxnID(eRegNo);
	}

	public String getDistrictName(String distID, String language) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.getDistrictName(distID, language);
	}

	public String getDeedName(String deedID, String language) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.getDeedName(deedID, language);
	}

	public String getOfficeName(String offcID) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.getOfficeName(offcID);
	}

	public ArrayList getOfficeNameDetailsMISDist(MISReportDTO dto, String lang) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getOfficeNameDetails BD start");
			String[] param = new String[3];
			param[0] = dto.getUserType();
			param[1] = "A";
			param[2] = dto.getDistrictID();
			String SQL = CommonSQL.OFFICE_NAME_DETAILS_DISTWISE;

			ArrayList resultList = dao.getOfficeNameDetails(param, SQL);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					ArrayList list = (ArrayList) resultList.get(i);
					MISReportDTO reportDTO = new MISReportDTO();
					reportDTO.setOfficeId((String) list.get(0));
					if ("en".equalsIgnoreCase(lang))
						reportDTO.setOfficeName((String) list.get(1));
					else
						reportDTO.setOfficeName((String) list.get(2));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getOfficeName() + ":" + reportDTO.getOfficeId());
				}
			}
		} catch (Exception err) {

			logger.debug("In getOfficeNameDetails Exception " + err);
		}
		return returnList;
	}

	public ArrayList getOfficeNameDetailsMISDistSr(String officeId, String lang) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getOfficeNameDetails BD start");
			String[] param = new String[1];
			param[0] = officeId;
			String SQL = CommonSQL.OFFICE_NAME_DETAILS_DISTWISE_SR;

			ArrayList resultList = dao.getOfficeNameDetails(param, SQL);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					ArrayList list = (ArrayList) resultList.get(i);
					MISReportDTO reportDTO = new MISReportDTO();
					reportDTO.setOfficeId((String) list.get(0));
					if ("en".equalsIgnoreCase(lang))
						reportDTO.setOfficeName((String) list.get(1));
					else
						reportDTO.setOfficeName((String) list.get(2));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getOfficeName() + ":" + reportDTO.getOfficeId());
				}
			}
		} catch (Exception err) {

			logger.debug("In getOfficeNameDetails Exception " + err);
		}
		return returnList;
	}

	public boolean validateReport(String fromDate, String toDate) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validateReport(fromDate, toDate);
	}
	public boolean validateReportSplicence(String fromDate, String toDate) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validateReportSplicence(fromDate, toDate);
	}
	public boolean validateReportRegistration(String fromDate, String toDate) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validateReportRegistration(fromDate, toDate);
	}
	// sp summary report
	public boolean validateSpSumaryReport(String fromDate, String toDate) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validateSpSumaryReport(fromDate, toDate);
	}

	public boolean validateGenericReport(String fromDate, String toDate, String attributeValue) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validateGenericReport(fromDate, toDate, attributeValue);
	}

	public boolean validateReportForSlot(String fromDate, String toDate) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validateReportForSlot(fromDate, toDate);
	}
	public boolean validateReportEstamp(String fromDate, String toDate) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validateReportEstamp(fromDate, toDate);
	}
	// Added by Thilak 12/05/2017
	public boolean validateDocDeliveryReport(String fromDate, String toDate) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validateDocDeliveryReport(fromDate, toDate);
	}

	// Added - Slot Stastics Future Max Date Validation - RAhul 25-OCT-16 start
	public boolean validSlotDate(String toDate) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validSlotDate(toDate);
	}
	public String slotDuration() throws Exception {

		ReportDAO dao = new ReportDAO();
		return dao.slotDuration();
	}

	// Added - Slot Stastics Future Max Date Validation - RAhul 25-OCT-16 end
	public ArrayList getInspectionReport(MISReportForm eForm, String language) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getInspectionReport(eForm);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {
				ArrayList propList = new ArrayList();
				ArrayList propList1 = new ArrayList();
				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				misDTO.setRegId(subList.get(0).toString());
				misDTO.setRegComp(subList.get(1).toString());
				String propertyID = subList.get(2).toString();
				// String deed=subList.get(3).toString();f
				String propertySplit[] = propertyID.split("~");
				for (int j = 0; j < propertySplit.length; j++) {
					MISReportDTO misDTO1 = new MISReportDTO();
					String propertyInfo = propertySplit[j];
					// String str=deedInfo.substring(deedInfo.indexOf("[")+1,
					// deedInfo.indexOf("]"));
					// String splitStr[]=str.split(",");
					misDTO1.setPropertyID(propertyInfo);
					propList.add(misDTO1);

				}
				misDTO.setDeedList(propList);

				String uploadID = subList.get(3).toString();
				String uploadSplit[] = uploadID.split("~");
				for (int j = 0; j < uploadSplit.length; j++) {
					MISReportDTO misDTO1 = new MISReportDTO();
					String uploadInfo = uploadSplit[j];
					// String str=deedInfo.substring(deedInfo.indexOf("[")+1,
					// deedInfo.indexOf("]"));
					// String splitStr[]=str.split(",");
					if ("en".equalsIgnoreCase(language))
						misDTO1.setUploadID("Upload " + (j + 1));
					else
						misDTO1.setUploadID("अपलोड " + (j + 1));
					misDTO1.setUploadFile(uploadInfo);
					propList1.add(misDTO1);

				}
				misDTO.setDeedTypeList(propList1);
				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public ArrayList getEstampReport(MISReportForm eForm, String lang) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getEstampReport(eForm);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {
				ArrayList deedList = new ArrayList();
				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				if ("en".equalsIgnoreCase(lang)) {
					misDTO.setDistrictName(subList.get(0).toString());
					misDTO.setDeedName(subList.get(1).toString());
				} else {
					misDTO.setDistrictName(subList.get(2).toString());
					misDTO.setDeedName(subList.get(3).toString());
				}
				misDTO.setCount(subList.get(4).toString());
				misDTO.setTotalDuty(subList.get(5).toString());
				misDTO.setConsiderationAmount(subList.get(6).toString());
				misDTO.setDistrictID(subList.get(7).toString());
				misDTO.setDeedID(subList.get(8).toString());
				System.out.println("Deed Name" + misDTO.getDeedName());
				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public ArrayList getEstampReportDrill(MISReportForm eForm, String lang, String disID, String deedID, String uID) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getEstampReportDrill(eForm, disID, deedID, uID);
		try {
			// ArrayList report=new ArrayList();
			for (int i = 0; i < reportList.size(); i++) {

				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				if ("en".equalsIgnoreCase(lang)) {
					// misDTO.setDistrictName(subList.get(0).toString());
					misDTO.setDeed1(subList.get(1).toString());
				} else {
					// misDTO.setDistrictName(subList.get(1).toString());
					misDTO.setDeed1(subList.get(2).toString());
				}
				misDTO.setSpLicSumm(subList.get(0).toString());
				misDTO.setCount1(subList.get(3).toString());
				misDTO.setAmount1(subList.get(4).toString());
				misDTO.setComm1(subList.get(5).toString());
				misDTO.setUserID(subList.get(8).toString());
				misDTO.setDeedID(subList.get(7).toString());

				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public ArrayList getEstampReportDrill1(MISReportForm eForm, String lang, String disID, String deedID, String uID) throws Exception {
		ReportDAO dao = new ReportDAO();
		int sum = 0;
		ArrayList list = new ArrayList();

		ArrayList reportList = dao.getEstampReportDrill1(eForm, disID, deedID, uID);
		try {

			for (int i = 0; i < reportList.size(); i++) {

				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();

				misDTO.setRegId(subList.get(0).toString());

				misDTO.setAmount1(subList.get(1).toString());
				misDTO.setComm1(subList.get(2).toString());
				misDTO.setUserID(subList.get(3).toString());
				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return list;

	}

	public ArrayList gettehsil(String districtID, String language) {
		ReportDAO dao = new ReportDAO();
		ArrayList reportList = dao.gettehsil(districtID, language);
		ArrayList list = new ArrayList();
		try {

			for (int i = 0; i < reportList.size(); i++) {

				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();

				misDTO.setTehsilreg(subList.get(0).toString());

				misDTO.setTehsilIdreg(subList.get(1).toString());

				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}

		return list;

	}
	public ArrayList getoffices(String tehsilId, String language) {
		ReportDAO dao = new ReportDAO();
		ArrayList reportList = dao.getoffice(tehsilId, language);
		ArrayList list = new ArrayList();
		try {

			for (int i = 0; i < reportList.size(); i++) {

				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();

				misDTO.setOfficeReg(subList.get(0).toString());

				misDTO.setOfficeRegId(subList.get(1).toString());

				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}

		return list;

	}

	public ArrayList getSRS(String officesId, String language, String officeId, String userId) {
		ReportDAO dao = new ReportDAO();
		ArrayList reportList = dao.getSRS(officesId, language, officeId, userId);
		ArrayList list = new ArrayList();
		try {

			for (int i = 0; i < reportList.size(); i++) {

				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();

				misDTO.setSrNamereg(subList.get(0).toString());

				misDTO.setSrUserIdreg(subList.get(1).toString());

				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}

		return list;

	}

	public boolean validateReportInsp(String durFrom, String durTo) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.validateReportInsp(durFrom, durTo);
	}

	public ArrayList getOffice(String tehsilId, String language, String officeId) {
		ReportDAO dao = new ReportDAO();
		ArrayList reportList = dao.getOffice(tehsilId, language, officeId);
		ArrayList list = new ArrayList();
		try {

			for (int i = 0; i < reportList.size(); i++) {

				ArrayList subList = (ArrayList) reportList.get(i);
				MISReportDTO misDTO = new MISReportDTO();

				misDTO.setOfficeReg(subList.get(0).toString());

				misDTO.setOfficeRegId(subList.get(1).toString());

				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}

		return list;

	}

	public String getOfficeTypeID(String offcID) throws Exception {
		ReportDAO dao = new ReportDAO();
		return dao.getOfficeType(offcID);
	}

	// added by Thilak

	public ArrayList getYears() {
		ReportDAO dao = new ReportDAO();
		ArrayList yearsList = dao.getYears();
		ArrayList list = new ArrayList();
		try {

			for (int i = 0; i < yearsList.size(); i++) {

				ArrayList subList = (ArrayList) yearsList.get(i);
				MISReportDTO misDTO = new MISReportDTO();

				misDTO.setYear1(subList.get(0).toString());
				misDTO.setYear2(subList.get(0).toString());
				// misDTO.setOfficeRegId(subList.get(1).toString());

				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}

		return list;
	}

	public ArrayList getFinYears() {
		ReportDAO dao = new ReportDAO();
		ArrayList yearsList = dao.getFinYears();
		ArrayList list = new ArrayList();
		try {

			for (int i = 0; i < yearsList.size(); i++) {

				ArrayList subList = (ArrayList) yearsList.get(i);
				MISReportDTO misDTO = new MISReportDTO();

				misDTO.setFinYear1(subList.get(0).toString());
				misDTO.setFinYear2(subList.get(0).toString());
				// misDTO.setOfficeRegId(subList.get(1).toString());

				list.add(misDTO);
			}
		} catch (Exception e) {
			logger.error(e);

		}

		return list;
	}
}
