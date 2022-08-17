/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *V
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: ReportDAO.java
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
 *  Added Search Script for with out Deed and With Office,SR -Rahul
 */

package com.wipro.igrs.report.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.report.bo.MISReportBO;
import com.wipro.igrs.report.dto.MarketTrendReportDTO;
import com.wipro.igrs.report.dto.ReportDTO;
import com.wipro.igrs.report.dto.ReportingDTO;
import com.wipro.igrs.report.form.MISReportForm;
import com.wipro.igrs.report.sql.CommonSQL;

public class ReportDAO {
	DBUtility dbutility;
	/** Creates a new instance of RevenueMgmtDAO */
	private static final Logger logger = Logger.getLogger(ReportDAO.class);

	public ReportDAO() {
		/*
		 * try{ logger.debug("In RevenueMgmtDAO construtor"); dbutility = new
		 * DBUtility(); } catch(Exception objE) { objE.getStackTrace();
		 * logger.error("Exception when creating " + "DBUtilitiy obj creating "
		 * + objE); }
		 */
	}

	/**
	 * ...method getDistrictDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description Description : Retrieving values from
	 *            IGRS_DISTRICT_MASTER table Created Date : Mar 18,2008 Author :
	 *            Sreelatha M
	 */
	public ArrayList getDistrictDetails() {
		// ...implementation goes here...
		logger.debug("in getDistrictDetails dao block start");
		ArrayList ret = new ArrayList();
		try {
			String sql = CommonSQL.DISTRICT_DETAILS;
			logger.debug("sql query====" + sql);
			dbutility = new DBUtility();
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getDistrictDetails DAO: "
					+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {

				logger.error("this is Fianlly Try catch in "
						+ "getDistrictDetails DAO " + err);
			}
		}
		logger.debug("in getDistrictDetails dao block end");
		return ret;
	}

	/**
	 * ...method getOfficeTypeDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description
	 */
	public ArrayList getTehsilTypeDetails(ReportDTO dto) {
		// ...implementation goes here...
		String districtName = dto.getDistrictName();
		logger.debug("districtName:-" + districtName);
		String districtId = dto.getDistrictId();
		logger.debug("districtId:-" + districtId);
		logger.debug("in getTehsilTypeDetails dao block start");
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.TEHSIL_TYPE_DETAILS;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql
					+ ("DISTRICT_ID = '" + districtId + "' "));
		} catch (Exception err) {

			logger.error("this is Exception in " + "getTehsilTypeDetails DAO: "
					+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {

				logger.error("this is Fianlly Try catch in "
						+ "getTehsilTypeDetails DAO " + err);
			}
		}
		logger.debug("in getTehsilTypeDetails dao block end");
		return ret;
	}

	/**
	 * ...method getOfficeTypeDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description
	 */
	/*
	 * public ArrayList getOfficeTypeDetails(ReportForm reportForm) { //
	 * ...implementation goes here...
	 * logger.debug("in getOfficeTypeDetails dao block start"); ArrayList ret =
	 * new ArrayList(); try { String sql = CommonSQL.OFFICE_TYPE_DETAILS;
	 * logger.debug("sql query===="+sql); dbutility.createStatement(); ret =
	 * dbutility.executeQuery(sql); } catch(Exception err) {
	 * 
	 * logger.error("this is Exception in " + "getOfficeTypeDetails DAO: " +
	 * err); } finally { try { dbutility.closeConnection(); } catch(Exception
	 * err) {
	 * 
	 * logger.error("this is Fianlly Try catch in " +
	 * "getOfficeTypeDetails DAO " + err); } }
	 * logger.debug("in getOfficeTypeDetails dao block end"); return ret; }
	 * 
	 * /** ...method getOfficeNameDetails documentation comment...
	 * 
	 * @param reportForm description
	 */
	public ArrayList getOfficeNameDetails(String[] param, String SQL) {

		ArrayList ret = new ArrayList();

		// String[] param = new String[2];
		try {
			dbutility = new DBUtility();
			/*
			 * String sql = CommonSQL.OFFICE_NAME_DETAILS_I;
			 * logger.debug("SQL Query :-"+sql); param[0] = dto.getUserType();
			 * param[1] = "A";
			 */
			dbutility.createPreparedStatement(SQL);
			ret = dbutility.executeQuery(param);
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {
				logger.error(err);
			}
		}
		logger.debug("in getOfficeNameDetails dao block end");
		return ret;
	}

	/**
	 * @param param
	 * @param searchType
	 * @return
	 */
	public ArrayList getMarketTrendReport(String[] param, String SQL) {
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();

			logger.debug("SQL:-" + SQL);
			dbutility.createPreparedStatement(SQL);

			ret = dbutility.executeQuery(param);
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {

				logger.error(err);
			}
		}
		return ret;
	}

	/**
	 * @return
	 */
	public ArrayList getCurrentYear() {
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();

			dbutility.createStatement();

			ret = dbutility.executeQuery(CommonSQL.CURRENT_YEAR);
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {

				logger.error(err);
			}
		}
		return ret;
	}

	/**
	 * @param param
	 * @param SQL
	 * @return ArrayList
	 */
	public synchronized ArrayList getUsageReport(String[] param, String SQL) {
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();

			logger.debug("Usage SQL:-" + SQL);
			dbutility.createPreparedStatement(SQL);

			ret = dbutility.executeQuery(param);
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {

				logger.error(err);
			}
		}
		return ret;
	}

	public synchronized ArrayList getMonthlyJudicial(String[] yearParam) {
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();

			// logger.debug("Usage SQL:-"+SQL);
			String SQL = CommonSQL.MONTHLY_REVENUE_JUDICAL_STAMPS;
			logger.debug("SQL:-" + SQL);
			dbutility.createPreparedStatement(SQL);

			ret = dbutility.executeQuery(yearParam);
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {

				logger.error(err);
			}
		}
		return ret;
	}

	public synchronized ArrayList getCessMunicipalDuty(String[] param,
			String SQL) {
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();

			logger.debug("SQL:-" + SQL);
			dbutility.createPreparedStatement(SQL);

			ret = dbutility.executeQuery(param);
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {

				logger.error(err);
			}
		}
		return ret;
	}

	/**
	 * ...method getEffectFactorDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description Description : Retrieving values from
	 *            igrs_revenue_project_factor table Created Date : May 17,2008
	 *            Author : Sreelatha M
	 */
	public synchronized ArrayList getEffectFactorDetails() {
		// ...implementation goes here...
		logger.debug("in getEffectFactorDetails dao block start");
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.EFFECT_FACTOR_DETAILS;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);
		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in "
					+ "getEffectFactorDetails DAO: " + err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getEffectFactorDetails DAO " + err);
			}
		}
		logger.debug("in getEffectFactorDetails dao block end");
		return ret;
	}

	/**
	 * ...method getRegisterDocumentDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description Description : Retrieving values from
	 *            IGRS_DOC_SEARCH_DETAILS table Created Date : May 17,2008
	 *            Author : Sreelatha M
	 */
	public ArrayList getRegisterDocumentDetails() {
		// ...implementation goes here...
		logger.debug("in getRegisterDocumentDetails dao block start");
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.REG_DOC_DETAILS;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);
		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in "
					+ "getRegisterDocumentDetails DAO: " + err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getRegisterDocumentDetails DAO " + err);
			}
		}
		logger.debug("in getRegisterDocumentDetails dao block end");
		return ret;
	}

	/**
	 * ...method getFiscalYearDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description Description : Retrieving values from
	 *            IGRS_FISCAL_YEAR table Created Date : May 17,2008 Author :
	 *            Sreelatha M
	 */
	public ArrayList getFiscalYearDetails() {
		// ...implementation goes here...
		logger.debug("in getFiscalYearDetails dao block start");
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.FISCAL_YEAR_DETAILS;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);
		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in " + "getFiscalYearDetails DAO: "
					+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getFiscalYearDetails DAO " + err);
			}
		}
		logger.debug("in getFiscalYearDetails dao block end");
		return ret;
	}

	/**
	 * ...method getDroNameDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description Description : Retrieving values from
	 *            IGRS_OFFICE_MASTER table Created Date : May 17,2008 Author :
	 *            Sreelatha M
	 */
	public ArrayList getDroNameDetails() {
		// ...implementation goes here...
		logger.debug("in getDroNameDetails dao block start");
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.DRO_DETAILS;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);
		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in " + "getDroNameDetails DAO: "
					+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getDroNameDetails DAO " + err);
			}
		}
		logger.debug("in getDroNameDetails dao block end");
		return ret;
	}

	/**
	 * ...method getSroNameDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description Description : Retrieving values from
	 *            IGRS_OFFICE_MASTER table Created Date : Jun 07,2008 Author :
	 *            Sreelatha M
	 */
	public ArrayList getSroNameDetails() {
		// ...implementation goes here...
		logger.debug("in getSroNameDetails dao block start");
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.SRO_DETAILS;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);
		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in " + "getSroNameDetails DAO: "
					+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getSroNameDetails DAO " + err);
			}
		}
		logger.debug("in getSroNameDetails dao block end");
		return ret;
	}

	/**
	 * ...method getSROwiseStampRegFeeDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description
	 */
	public ArrayList getSROwiseStampRegFeeDetails(ReportDTO _reportDTO) {
		// ...implementation goes here...
		logger.debug("in getSROwiseStampRegFeeDetails dao block start");
		String month = _reportDTO.getMonth();
		logger.debug("month=======-" + month);
		logger.debug("month=======-" + month);
		String fromDate = _reportDTO.getFromDate();
		String toDate = _reportDTO.getToDate();
		logger.debug("fromDate====" + fromDate + "  todate====" + toDate);
		String officeId = _reportDTO.getOfficeId();
		logger.debug("officeId====" + officeId);
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			if (month != null && !(month.equalsIgnoreCase("Select"))) {
				logger.debug("month=======-" + month);
				logger.debug("in getSROwiseStampRegFeeDetails month blk DAO");
				String sql = CommonSQL.SRO_WISE_MON_OFFICE_ID_DETAILS;
				logger.debug("sql SRO_WISE_MON_OFFICE_ID_DETAILS query===="
						+ sql);
				System.out
						.println("sql SRO_WISE_MON_OFFICE_ID_DETAILS query===="
								+ sql);
				dbutility.createPreparedStatement(sql);
				String sd[] = new String[7];
				sd[0] = month;
				sd[1] = month;
				sd[2] = month;
				sd[3] = month;
				sd[4] = month;
				sd[5] = month;
				sd[6] = month;
				// dbutility.createStatement();
				ret = dbutility.executeQuery(sd);

			} else if (fromDate != null && toDate != null) {
				System.out
						.println("in getSROwiseStampRegFeeDetails period blk DAO");
				String sql = CommonSQL.SRO_WISE_PERIOD_OFFICE_ID_DETAILS;
				System.out
						.println("sql SRO_WISE_MON_OFFICE_ID_DETAILS query===="
								+ sql);
				dbutility.createPreparedStatement(sql);
				String sd[] = new String[14];
				sd[0] = fromDate;
				sd[1] = toDate;
				sd[2] = fromDate;
				sd[3] = toDate;
				sd[4] = fromDate;
				sd[5] = toDate;
				sd[6] = fromDate;
				sd[7] = toDate;
				sd[8] = fromDate;
				sd[9] = toDate;
				sd[10] = fromDate;
				sd[11] = toDate;
				sd[12] = fromDate;
				sd[13] = toDate;
				ret = dbutility.executeQuery(sd);
			}
		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in "
					+ "getSROwiseStampRegFeeDetails DAO: " + err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getSROwiseStampRegFeeDetails DAO " + err);
			}
		}
		logger.debug("in getSROwiseStampRegFeeDetails dao block end");
		return ret;
	}

	/**
	 * ...method getRevenueProjectDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description
	 */
	public ArrayList getRevenueProjectDetails(ReportDTO _reportDTO) {
		// ...implementation goes here...
		logger.debug("in getRevenueProjectDetails dao block start");
		String districtName = _reportDTO.getDistrictName();
		logger.debug("districtName:-" + districtName);
		String districtId = _reportDTO.getDistrictId();
		logger.debug("districtId:-" + districtId);
		String fromDate = _reportDTO.getFromDate();
		String toDate = _reportDTO.getToDate();
		logger.debug("FromDate===" + fromDate + " and ToDate===" + toDate);
		String month = _reportDTO.getMonth();
		logger.debug("month=======-" + month);
		String userType = _reportDTO.getUserType();
		logger.debug("userType====" + userType);

		ArrayList finalList = new ArrayList();
		ArrayList ret1 = new ArrayList();
		try {
			dbutility = new DBUtility();
			if (month != null && !(month.equalsIgnoreCase("Select"))) {
				logger.debug("in month blk DAO");
				String officeId = null;
				if (userType.equalsIgnoreCase("sro")) {
					officeId = _reportDTO.getOfficeId();
				} else if (userType.equalsIgnoreCase("dro")) {
					officeId = _reportDTO.getDroId();
				}
				logger.debug("officeId=====" + officeId);
				if (officeId != null && !(officeId.equalsIgnoreCase("Select"))) {
					String sql = CommonSQL.REV_PROJECT_MONTH_OFFICEID_DETAILS;
					logger
							.debug("sql REV_PROJECT_MONTH_OFFICEID_DETAILS query===="
									+ sql);
					dbutility.createPreparedStatement(sql);
					String sd[] = new String[10];
					sd[0] = month;
					sd[1] = officeId;
					sd[2] = month;
					sd[3] = officeId;
					sd[4] = month;
					sd[5] = officeId;
					sd[6] = month;
					sd[7] = month;
					sd[8] = officeId;
					sd[9] = month;
					ret1 = dbutility.executeQuery(sd);
					finalList.add(ret1);
				} else if (districtId != null
						&& !(districtId.equalsIgnoreCase("ALL"))) {
					String sql = CommonSQL.REV_PROJECT_MONTH_DISTRICTID_DETAILS;
					logger
							.debug("sql REV_PROJECT_MONTH_DISTRICTID_ALL_DETAILS query===="
									+ sql);
					dbutility.createPreparedStatement(sql);
					String sd[] = new String[12];
					sd[0] = districtId;
					sd[1] = month;
					sd[2] = districtId;
					sd[3] = month;
					sd[4] = districtId;
					sd[5] = month;
					sd[6] = districtId;
					sd[7] = month;
					sd[8] = districtId;
					sd[9] = month;
					sd[10] = districtId;
					sd[11] = month;
					ret1 = dbutility.executeQuery(sd);
					finalList.add(ret1);
				} else if (districtId != null
						&& (districtId.equalsIgnoreCase("ALL"))) {
					String sql = CommonSQL.REV_PROJECT_MONTH_DISTRICTID_ALL_DETAILS;
					logger
							.debug("sql REV_PROJECT_MONTH_DISTRICTID_ALL_DETAILS query===="
									+ sql);
					dbutility.createPreparedStatement(sql);
					String sd[] = new String[6];
					sd[0] = month;
					sd[1] = month;
					sd[2] = month;
					sd[3] = month;
					sd[4] = month;
					sd[5] = month;
					ret1 = dbutility.executeQuery(sd);
					finalList.add(ret1);
				}

			} else if (fromDate != null && toDate != null) {
				logger.debug("in FromDate and ToDate blk DAO");
				String officeId = null;
				if (userType.equalsIgnoreCase("sro")) {
					officeId = _reportDTO.getOfficeId();
				} else if (userType.equalsIgnoreCase("dro")) {
					officeId = _reportDTO.getDroId();
				}
				logger.debug("officeId=====" + officeId);
				if (officeId != null && !(officeId.equalsIgnoreCase("Select"))) {
					String sql = CommonSQL.REV_PROJECT_PERIOD_OFFICEID_DETAILS;
					logger
							.debug("sql REV_PROJECT_PERIOD_OFFICEID_DETAILS query===="
									+ sql);
					dbutility.createPreparedStatement(sql);
					String sd[] = new String[16];
					sd[0] = fromDate;
					sd[1] = toDate;
					sd[2] = officeId;
					sd[3] = fromDate;
					sd[4] = toDate;
					sd[5] = officeId;
					sd[6] = fromDate;
					sd[7] = toDate;
					sd[8] = officeId;
					sd[9] = fromDate;
					sd[10] = toDate;
					sd[11] = fromDate;
					sd[12] = toDate;
					sd[13] = officeId;
					sd[14] = fromDate;
					sd[15] = toDate;
					ret1 = dbutility.executeQuery(sd);
					finalList.add(ret1);
				} else if (districtId != null
						&& !(districtId.equalsIgnoreCase("ALL"))) {
					String sql = CommonSQL.REV_PROJECT_PERIOD_DISTRICTID_DETAILS;
					logger
							.debug("sql REV_PROJECT_PERIOD_DISTRICTID_DETAILS query===="
									+ sql);
					dbutility.createPreparedStatement(sql);
					String sd[] = new String[18];
					sd[0] = districtId;
					sd[1] = fromDate;
					sd[2] = toDate;
					sd[3] = districtId;
					sd[4] = fromDate;
					sd[5] = toDate;
					sd[6] = districtId;
					sd[7] = fromDate;
					sd[8] = toDate;
					sd[9] = districtId;
					sd[10] = fromDate;
					sd[11] = toDate;
					sd[12] = districtId;
					sd[13] = fromDate;
					sd[14] = toDate;
					sd[15] = districtId;
					sd[16] = fromDate;
					sd[17] = toDate;
					ret1 = dbutility.executeQuery(sd);
					finalList.add(ret1);
				} else if (districtId != null
						&& (districtId.equalsIgnoreCase("ALL"))) {
					String sql = CommonSQL.REV_PROJECT_PERIOD_DISTRICTID_ALL_DETAILS;
					logger
							.debug("sql REV_PROJECT_PERIOD_DISTRICTID_ALL_DETAILS query===="
									+ sql);
					dbutility.createPreparedStatement(sql);
					String sd[] = new String[12];
					sd[0] = fromDate;
					sd[1] = toDate;
					sd[2] = fromDate;
					sd[3] = toDate;
					sd[4] = fromDate;
					sd[5] = toDate;
					sd[6] = fromDate;
					sd[7] = toDate;
					sd[8] = fromDate;
					sd[9] = toDate;
					sd[10] = fromDate;
					sd[11] = toDate;
					ret1 = dbutility.executeQuery(sd);
					finalList.add(ret1);
				}
			}

		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in "
					+ "getRevenueProjectDetails DAO: " + err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getRevenueProjectDetails DAO " + err);
			}
		}
		logger.debug("in getRevenueProjectDetails dao block end");
		return finalList;
	}

	/**
	 * ...method getSubwiseStampRegFeeDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description
	 */
	public ArrayList getSubwiseStampRegFeeDetails(ReportDTO _reportDTO) {
		// ...implementation goes here...
		logger.debug("in getSubwiseStampRegFeeDetails dao block start");
		String month = _reportDTO.getMonth();
		logger.debug("month=======-" + month);
		String fromDate = _reportDTO.getFromDate();
		String toDate = _reportDTO.getToDate();
		logger.debug("FromDate===" + fromDate + " and ToDate===" + toDate);
		String officeId = _reportDTO.getOfficeId();
		logger.debug("officeId====" + officeId);
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			if (month != null && !(month.equalsIgnoreCase("Select"))) {
				logger.debug("in getSubwiseStampRegFeeDetails month blk DAO");
				String sql = CommonSQL.SUB_WISE_MON_STAMP_REG_FEE_RPT;
				System.out
						.println("sql SUB_WISE__MON_STAMP_REG_FEE_RPT query===="
								+ sql);
				dbutility.createPreparedStatement(sql);
				String[] sd = new String[2];
				sd[0] = month;
				sd[1] = month;
				ret = dbutility.executeQuery(sd);
			} else {
				logger.debug("in all else  blk DAO");
				String sql = CommonSQL.SUB_WISE_PERIOD_STAMP_REG_FEE_RPT;
				logger.debug("sql SUB_WISE_STAMP_REG_FEE_RPT query====" + sql);
				dbutility.createPreparedStatement(sql);
				String[] sd = new String[4];
				sd[0] = fromDate;
				sd[1] = toDate;
				sd[2] = fromDate;
				sd[3] = toDate;
				ret = dbutility.executeQuery(sd);
			}
		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in "
					+ "getSubwiseStampRegFeeDetails DAO: " + err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getSubwiseStampRegFeeDetails DAO " + err);
			}
		}
		logger.debug("in getSubwiseStampRegFeeDetails dao block end");
		return ret;
	}

	/**
	 * ...method getCompRevReceiptsDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description
	 */
	public ArrayList getCompRevReceiptsDetails(ReportDTO _reportDTO) {
		// ...implementation goes here...
		logger.debug("in getCompRevReceiptsDetails dao block start");
		String districtName = _reportDTO.getDistrictName();
		logger.debug("districtName:-" + districtName);
		String districtId = _reportDTO.getDistrictId();
		logger.debug("districtId:-" + districtId);
		String fiscalId = _reportDTO.getFiscalId();
		logger.debug("fiscalId:-" + fiscalId);
		String droId = _reportDTO.getDroId();
		logger.debug("droId:-" + droId);
		ArrayList finalList = new ArrayList();
		ArrayList ret1 = new ArrayList();
		ArrayList ret2 = new ArrayList();
		ArrayList ret3 = new ArrayList();
		ArrayList ret4 = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql1 = CommonSQL.COMP_REV_RECEIPTS_PROG_TARG;
			logger.debug("sql COMP_REV_RECEIPTS_PROG_TARG query====" + sql1);
			dbutility.createStatement();
			ret1 = dbutility
					.executeQuery(sql1
							+ " "
							+ " AND IGRS_EXP_MONTLY_TARGET.fiscal_year_id='"
							+ fiscalId
							+ "' "
							+ " AND IGRS_EXP_MONTLY_TARGET.district_id='"
							+ districtId
							+ "' "
							+ " AND IGRS_EXP_MONTLY_TARGET.OFFICE_DRO_ID ='"
							+ droId
							+ "' )"
							+ " JOIN IGRS_OFFICE_MASTER "
							+ " ON (IGRS_EXP_MONTLY_TARGET.OFFICE_DRO_ID=IGRS_OFFICE_MASTER.OFFICE_ID "
							+ " AND IGRS_OFFICE_MASTER.OFFICE_TYPE_ID='DRO')"
							+ " RIGHT OUTER JOIN igrs_fiscal_month_master"
							+ " ON(igrs_fiscal_month_master.fiscal_month_id=igrs_exp_montly_target.fiscal_month_id)"
							+ " GROUP BY igrs_fiscal_month_master.fiscal_month_name,"
							+ " igrs_fiscal_month_master.rowid ORDER BY igrs_fiscal_month_master.rowid ");

			finalList.add(ret1);

			logger.debug("sql COMP_REV_RECEIPTS_PROG_TARG_PREVIOUS query===="
					+ sql1);
			dbutility.createStatement();
			ret2 = dbutility
					.executeQuery(sql1
							+ " "
							+ " AND IGRS_EXP_MONTLY_TARGET.fiscal_year_id="
							+ " (SELECT FISCAL_YEAR_ID "
							+ " FROM IGRS_FISCAL_YEAR"
							+ " WHERE SUBSTR(FISCAL_YEAR,4,2)= "
							+ " (SELECT SUBSTR(FISCAL_YEAR,1,2) "
							+ " FROM IGRS_FISCAL_YEAR WHERE FISCAL_YEAR_ID='"
							+ fiscalId
							+ "'))"
							+ " AND IGRS_EXP_MONTLY_TARGET.district_id='"
							+ districtId
							+ "'"
							+ " AND IGRS_EXP_MONTLY_TARGET.OFFICE_DRO_ID ='"
							+ droId
							+ "' )"
							+ " JOIN IGRS_OFFICE_MASTER"
							+ " ON (IGRS_EXP_MONTLY_TARGET.OFFICE_DRO_ID=IGRS_OFFICE_MASTER.OFFICE_ID"
							+ " AND IGRS_OFFICE_MASTER.OFFICE_TYPE_ID='DRO')"
							+ " RIGHT OUTER JOIN igrs_fiscal_month_master"
							+ " ON(igrs_fiscal_month_master.fiscal_month_id=igrs_exp_montly_target.fiscal_month_id)"
							+ " GROUP BY"
							+ " igrs_fiscal_month_master.fiscal_month_name,"
							+ " igrs_fiscal_month_master.rowid" + " ORDER BY  "
							+ " igrs_fiscal_month_master.rowid ");

			finalList.add(ret2);

			String sql3 = CommonSQL.COMP_REV_RECEIPTS_PROG_ACHIEV;
			logger.debug("sql COMP_REV_RECEIPTS_PROG_ACHIEV query====" + sql3);
			dbutility.createStatement();
			ret3 = dbutility
					.executeQuery(sql3
							+ ""
							+ " AND Igrs_exp_bud_target_dtls.FISCAL_YEAR_ID='"
							+ fiscalId
							+ "'"
							+ " AND IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID='"
							+ droId
							+ "'"
							+ " AND IGRS_PAYMENT_MODE_DETAILS.DISTRICT_ID='"
							+ districtId
							+ "'"
							+ " AND (TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'MM') "
							+ " IN (04,05,06,07,08,09,10,11,12) AND "
							+ " TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'yy')= "
							+ " ( select distinct substr(igrs_fiscal_year.FISCAL_YEAR,1,2) "
							+ " from igrs_fiscal_year,"
							+ " Igrs_exp_bud_target_dtls iebtd"
							+ " where igrs_fiscal_year.fiscal_year_id=iebtd.fiscal_year_id"
							+ " AND  iebtd.district_id=Igrs_exp_bud_target_dtls.district_id"
							+ " and iebtd.fiscal_year_id='"
							+ fiscalId
							+ "'))"
							+ " )"
							+ " JOIN"
							+ " IGRS_OFFICE_MASTER"
							+ " ON(IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID=IGRS_OFFICE_MASTER.OFFICE_ID"
							+ " AND IGRS_OFFICE_MASTER.OFFICE_TYPE_ID='DRO')"
							+ " RIGHT OUTER JOIN ( SELECT fiscal_month_NAME "
							+ " FROM igrs_fiscal_month_master"
							+ " WHERE fiscal_month_id IN (1,2,3,4,5,6,7,8,9)) ff "
							+ " ON(ff.fiscal_month_NAME=TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'Mon')) "
							+ " GROUP BY ff.fiscal_month_name, ff.rowid "
							+ " UNION"
							+ " SELECT "
							+ " ff.fiscal_month_NAME MONTHS,"
							+ " nvl(SUM(IGRS_PAYMENT_MODE_DETAILS.GROSS_AMOUNT),0) ProgAcievement,"
							+ " ff.ROWID "
							+ " FROM IGRS_PAYMENT_MODE_DETAILS "
							+ " join "
							+ " Igrs_exp_bud_target_dtls"
							+ " on("
							+ " IGRS_PAYMENT_MODE_DETAILS.DETAILED_HEAD_ID =Igrs_exp_bud_target_dtls.DETAILED_HEAD_ID"
							+ " AND IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID =Igrs_exp_bud_target_dtls.OFFICE_DRO_ID "
							+ " AND IGRS_PAYMENT_MODE_DETAILS.district_id =Igrs_exp_bud_target_dtls.district_id"
							+ " AND Igrs_exp_bud_target_dtls.FISCAL_YEAR_ID='"
							+ fiscalId
							+ "'"
							+ " AND IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID='"
							+ droId
							+ "'"
							+ " AND IGRS_PAYMENT_MODE_DETAILS.DISTRICT_ID='"
							+ districtId
							+ "'"
							+ " AND (TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'MM') IN (01,02,03)"
							+ " AND"
							+ " TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'yy')="
							+ " ( select distinct substr(igrs_fiscal_year.FISCAL_YEAR,4,2)"
							+ " from igrs_fiscal_year,"
							+ " Igrs_exp_bud_target_dtls iebtd"
							+ " where igrs_fiscal_year.fiscal_year_id=iebtd.fiscal_year_id"
							+ " AND  iebtd.district_id=Igrs_exp_bud_target_dtls.district_id"
							+ " and iebtd.fiscal_year_id='"
							+ fiscalId
							+ "')))"
							+ " JOIN"
							+ " IGRS_OFFICE_MASTER"
							+ " ON(IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID=IGRS_OFFICE_MASTER.OFFICE_ID"
							+ " AND IGRS_OFFICE_MASTER.OFFICE_TYPE_ID='DRO')"
							+ " RIGHT OUTER JOIN ( SELECT fiscal_month_NAME"
							+ " FROM igrs_fiscal_month_master "
							+ "  WHERE fiscal_month_id IN (10,11,12))ff"
							+ " ON(ff.fiscal_month_NAME=TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'Mon'))"
							+ " GROUP BY ff.fiscal_month_name, ff.rowid ORDER BY  3");
			finalList.add(ret3);

			String sql4 = CommonSQL.COMP_REV_RECEIPTS_PROG_ACHIEV;
			logger.debug("sql COMP_REV_RECEIPTS_PROG_ACHIEV query====" + sql4);
			dbutility.createStatement();
			ret4 = dbutility
					.executeQuery(sql4
							+ ""
							+ " AND Igrs_exp_bud_target_dtls.FISCAL_YEAR_ID='"
							+ fiscalId
							+ "' "
							+ " AND IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID='"
							+ droId
							+ "' "
							+ " AND IGRS_PAYMENT_MODE_DETAILS.DISTRICT_ID='"
							+ districtId
							+ "' "
							+ " AND (TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'MM') IN (04,05,06,07,08,09,10,11,12) "
							+ " AND TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'yy')= "
							+ " ( SELECT DISTINCT substr(igrs_fiscal_year.FISCAL_YEAR,1,2) "
							+ " FROM igrs_fiscal_year,"
							+ " Igrs_exp_bud_target_dtls iebtd "
							+ " WHERE igrs_fiscal_year.fiscal_year_id=iebtd.fiscal_year_id "
							+ " AND  iebtd.district_id=Igrs_exp_bud_target_dtls.district_id "
							+ " and iebtd.fiscal_year_id=(SELECT FISCAL_YEAR_ID  "
							+ " FROM IGRS_FISCAL_YEAR "
							+ " WHERE SUBSTR(FISCAL_YEAR,4,2)= "
							+ " (SELECT SUBSTR(FISCAL_YEAR,1,2) "
							+ " FROM IGRS_FISCAL_YEAR "
							+ " WHERE FISCAL_YEAR_ID='"
							+ fiscalId
							+ "')))))"
							+ " JOIN "
							+ " IGRS_OFFICE_MASTER "
							+ " ON(IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID=IGRS_OFFICE_MASTER.OFFICE_ID "
							+ " AND IGRS_OFFICE_MASTER.OFFICE_TYPE_ID='DRO')"
							+ " RIGHT OUTER JOIN ( SELECT fiscal_month_NAME "
							+ " FROM igrs_fiscal_month_master "
							+ " WHERE fiscal_month_id IN (1,2,3,4,5,6,7,8,9))ff   "
							+ " ON(ff.fiscal_month_NAME=TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'Mon'))"
							+ " GROUP BY ff.fiscal_month_name,"
							+ " ff.rowid"
							+ " UNION"
							+ " SELECT ff.fiscal_month_NAME MONTHS, "
							+ " nvl(SUM(IGRS_PAYMENT_MODE_DETAILS.GROSS_AMOUNT),0) ProgAcievement,"
							+ " ff.ROWID FROM IGRS_PAYMENT_MODE_DETAILS "
							+ " JOIN Igrs_exp_bud_target_dtls ON("
							+ " IGRS_PAYMENT_MODE_DETAILS.DETAILED_HEAD_ID =Igrs_exp_bud_target_dtls.DETAILED_HEAD_ID"
							+ " AND IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID =Igrs_exp_bud_target_dtls.OFFICE_DRO_ID "
							+ " AND IGRS_PAYMENT_MODE_DETAILS.district_id =Igrs_exp_bud_target_dtls.district_id"
							+ " AND Igrs_exp_bud_target_dtls.FISCAL_YEAR_ID='"
							+ fiscalId
							+ "'"
							+ " AND IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID='"
							+ droId
							+ "'"
							+ " AND IGRS_PAYMENT_MODE_DETAILS.DISTRICT_ID='"
							+ districtId
							+ "'"
							+ " AND (TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'MM') IN (01,02,03)"
							+ " AND TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'yy')= "
							+ " ( SELECT DISTINCT substr(igrs_fiscal_year.FISCAL_YEAR,4,2)"
							+ " FROM igrs_fiscal_year,"
							+ " Igrs_exp_bud_target_dtls iebtd"
							+ " WHERE igrs_fiscal_year.fiscal_year_id=iebtd.fiscal_year_id"
							+ " AND  iebtd.district_id=Igrs_exp_bud_target_dtls.district_id"
							+ " and iebtd.fiscal_year_id=(SELECT FISCAL_YEAR_ID "
							+ " FROM IGRS_FISCAL_YEAR"
							+ " WHERE SUBSTR(FISCAL_YEAR,4,2)= (SELECT SUBSTR(FISCAL_YEAR,1,2) "
							+ " FROM IGRS_FISCAL_YEAR WHERE FISCAL_YEAR_ID='"
							+ fiscalId
							+ "')))))"
							+ " JOIN"
							+ " IGRS_OFFICE_MASTER ON(IGRS_PAYMENT_MODE_DETAILS.OFFICE_ID=IGRS_OFFICE_MASTER.OFFICE_ID"
							+ " AND IGRS_OFFICE_MASTER.OFFICE_TYPE_ID='DRO')"
							+ " RIGHT OUTER JOIN ( SELECT fiscal_month_NAME "
							+ " FROM igrs_fiscal_month_master "
							+ " WHERE fiscal_month_id IN (10,11,12))ff    "
							+ " ON(ff.fiscal_month_NAME=TO_CHAR(IGRS_PAYMENT_MODE_DETAILS.CREATED_DATE,'Mon')    )"
							+ " GROUP BY  ff.fiscal_month_name,"
							+ " ff.rowid ORDER BY  3");
			finalList.add(ret4);

		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in "
					+ "getCompRevReceiptsDetails DAO: " + err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getCompRevReceiptsDetails DAO " + err);
			}
		}
		logger.debug("in getCompRevReceiptsDetails dao block end");
		return finalList;
	}

	/**
	 * ...method getRegDocDetails documentation comment...
	 * 
	 * @param reportForm
	 *            description
	 */
	public ArrayList getRegDocDetails(ReportDTO _reportDTO) {
		// ...implementation goes here...
		logger.debug("in getRegDocDetails dao block start");
		String radioSearch = _reportDTO.getRadioSearch();
		logger.debug("radioSearch====" + radioSearch);
		String fromDate = _reportDTO.getFromDate();
		String toDate = _reportDTO.getToDate();
		logger.debug("FromDate===" + fromDate + " and ToDate===" + toDate);
		String month = _reportDTO.getMonth();
		logger.debug("month===" + month);
		String districtId = _reportDTO.getDistrictId();
		logger.debug("districtId====" + districtId);
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			if (radioSearch.equalsIgnoreCase("period")) {
				if (fromDate != null && toDate != null) {
					String sql = CommonSQL.REG_DOC_COPY_REQUEST_PERIOD_RPT;
					logger
							.debug("sql REG_DOC_COPY_REQUEST_PERIOD_RPT query===="
									+ sql);
					dbutility.createPreparedStatement(sql);
					String sd[] = new String[3];
					sd[0] = districtId;
					sd[1] = fromDate;
					sd[2] = toDate;
					ret = dbutility.executeQuery(sd);
				}
			} else {
				if (month != null && !(month.equalsIgnoreCase("Select"))) {
					String sql = CommonSQL.REG_DOC_COPY_REQUEST_MONTH_RPT;
					logger.debug("sql REG_DOC_COPY_REQUEST_MONTH_RPT query===="
							+ sql);
					dbutility.createPreparedStatement(sql);
					String sd[] = new String[2];
					sd[0] = districtId;
					sd[1] = month;
					ret = dbutility.executeQuery(sd);
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in " + "getRegDocDetails DAO: "
					+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {
				err.getStackTrace();
				logger.error("this is Fianlly Try catch in "
						+ "getRegDocDetails DAO " + err);
			}
		}
		logger.debug("in getRegDocDetails dao block end");
		return ret;
	}

	/**
	 * ...method getBookTypeList documentation comment...
	 * 
	 * @param reportForm
	 *            description Description : Retrieving Book type id Created Date
	 *            : Mar 18,2008 Author : Pavani P
	 */
	public ArrayList getBookTypeList() {
		// ...implementation goes here...
		logger.debug("in getBookTypeList dao block start");
		ArrayList ret = new ArrayList();

		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.BOOK_TYPE_ID;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getBookTypeList DAO: "
					+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {

				logger.error("this is Fianlly Try catch in "
						+ "getBookTypeList DAO " + err);
			}
		}
		logger.debug("in getBookTypeList dao block end");

		return ret;
	}

	// Added by vinay
	public ArrayList districtEstampCode() {
		ArrayList list = new ArrayList();
		try {
			dbutility = new DBUtility();
			String query = CommonSQL.GET_ESTAMP_CODE;
			dbutility.createStatement();
			list = dbutility.executeQuery(query);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	public ArrayList amountMonth(ArrayList getList, String year) {
		ArrayList list = new ArrayList();

		try {

			String s = "";
			dbutility = new DBUtility();
			String query = CommonSQL.GET_AMOUNT;
			for (int i = 0; i < getList.size(); i++) {
				if (i == 0) {
					query = query + "?";
				} else {
					query = query + ",?";
				}
			}
			getList.add("01/apr/" + year);
			getList.add("31/mar/" + (Integer.parseInt(year) + 1));
			for (int i = 0; i < getList.size(); i++) {
				if (i == 0) {
					s = s + getList.get(i);
				} else {
					s = s + "," + getList.get(i);
				}
			}
			String param[] = s.split(",");
			query = query + ") " + CommonSQL.GET_AMOUNT_PENDING;
			dbutility.createPreparedStatement(query);
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	public ArrayList districtList() {
		ArrayList list = new ArrayList();
		try {
			dbutility = new DBUtility();
			String query = CommonSQL.GET_DISTRICT_LIST;
			dbutility.createStatement();
			list = dbutility.executeQuery(query);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	public String getYearCheck() {
		String value = "";
		try {
			dbutility = new DBUtility();
			String query = CommonSQL.YEAR_CHECK;
			dbutility.createStatement();
			value = dbutility.executeQry(query);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}

	public ArrayList getDeedTypeList() {
		logger.debug("in getDeedTypeList dao block start");
		ArrayList ret = new ArrayList();
		try {

			dbutility = new DBUtility();
			String sql = CommonSQL.DEED_TYPE;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);

		} catch (Exception err) {

			logger.error("this is Exception in " + "getDeedTypeList DAO: "
					+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {

				logger.error("this is Fianlly Try catch in "
						+ "getDeedTypeList DAO " + err);
			}
		}
		logger.debug("in getDeedTypeList dao block end");
		return ret;
	}

	public ArrayList getdeedReport1(String deedID) {
		logger.debug("in getdeedReport dao block start");
		ArrayList ret = new ArrayList();
		try {
			String param[] = { deedID, deedID, deedID };
			dbutility = new DBUtility();
			String sql = CommonSQL.DEED_NAME_NOOFDOC;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			ret = dbutility.executeQuery(param);

		} catch (Exception err) {

			logger.error("this is Exception in " + "getDeedTypeList DAO: "
					+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {

				logger.error("this is Fianlly Try catch in "
						+ "getDeedTypeList DAO " + err);
			}
		}
		logger.debug("in getDeedTypeList dao block end");
		return ret;
	}

	public ArrayList getdeedReport2(String deedID) {
		logger.debug("in getdeedReport2 dao block start");
		ArrayList ret = new ArrayList();
		try {
			String param[] = { deedID };
			dbutility = new DBUtility();
			String sql = CommonSQL.DEED_VALUATION;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			ret = dbutility.executeQuery(param);

		} catch (Exception err) {

			logger
					.error("this is Exception in " + "getdeedReport2 DAO: "
							+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {

				logger.error("this is Fianlly Try catch in "
						+ "getdeedReport2 DAO " + err);
			}
		}
		logger.debug("in getdeedReport2 dao block end");
		return ret;
	}

	public ArrayList getdeedReport3(String deedID) {
		logger.debug("in getdeedReport3 dao block start");
		ArrayList ret = new ArrayList();
		ArrayList ret1 = new ArrayList();
		ArrayList mainret = new ArrayList();
		double r1 = 0, v1 = 0, r2 = 0, v2 = 0;
		try {
			String param[] = { deedID };
			dbutility = new DBUtility();
			String sql = CommonSQL.DEED_VALUATION;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			ret = dbutility.executeQuery(param);
			String sql1 = CommonSQL.DEED_VALUATION_PREV;
			logger.debug("sql query====" + sql1);
			dbutility.createPreparedStatement(sql1);
			ret1 = dbutility.executeQuery(param);
			for (int i = 0; i < ret.size(); i++) {
				System.out.println(ret.get(i) + "     " + ret1.get(i));

				ArrayList tmpsubList = (ArrayList) ret.get(i);
				ArrayList tmpsubList1 = (ArrayList) ret1.get(i);
				if (tmpsubList != null) {
					System.out.println(tmpsubList.get(0) + "     "
							+ tmpsubList.get(1));
					String rs1 = (String) tmpsubList.get(0);
					String vs1 = (String) tmpsubList.get(1);

					if (rs1 == null) {
						r1 = 0;
					} else {
						r1 = Double.parseDouble(rs1);
					}
					if (vs1 == null) {
						v1 = 0;
					} else {
						v1 = Double.parseDouble(vs1);
					}

					String rs2 = (String) tmpsubList1.get(0);
					String vs2 = (String) tmpsubList1.get(1);
					if (rs2 == null) {
						r2 = 0;
					} else {
						r2 = Double.parseDouble(rs2);
					}
					if (vs2 == null) {
						v2 = 0;
					} else {
						v2 = Double.parseDouble(vs2);
					}

					/*
					 * if(s2==null){ b=0; }else{ b=Integer.parseInt(s2); }
					 */

					double revnue = Math.abs(r1 - r2);
					double valun = Math.abs(v1 - v2);
					String rev = String.valueOf(BigDecimal.valueOf(revnue)
							.toPlainString());
					String val = String.valueOf(BigDecimal.valueOf(valun)
							.toPlainString());
					mainret.add(rev);
					mainret.add(val);
				}
			}
		} catch (Exception err) {

			logger
					.error("this is Exception in " + "getdeedReport3 DAO: "
							+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {

				logger.error("this is Fianlly Try catch in "
						+ "getdeedReport3 DAO " + err);
			}
		}
		logger.debug("in getdeedReport3 dao block end");
		return ret1;
	}

	/*
	 * public ArrayList getdurReport(String fromYear,String toYear){
	 * logger.debug("in getdurReport dao block start"); ArrayList retsub = new
	 * ArrayList(); ArrayList retsub1=new ArrayList(); ArrayList mainret=new
	 * ArrayList(); int dp=0,dc=0; double rd=0.0,rc=0.0,vc=0.0,vd=0.0; int
	 * yearplus=Integer.parseInt(toYear); yearplus=yearplus+1; String
	 * toyearplus1=String.valueOf(yearplus); String
	 * prvfromYear=String.valueOf(Integer.parseInt(fromYear)-1); String
	 * prvtoYear=String.valueOf(Integer.parseInt(toYear)-1); try{ String
	 * param[]={fromYear,toyearplus1}; String param1[]={prvfromYear,prvtoYear};
	 * dbutility = new DBUtility(); String sql = CommonSQL.MTR_DUR_DUTY_REPORT;
	 * logger.debug("sql query====" + sql);
	 * dbutility.createPreparedStatement(sql); retsub =
	 * dbutility.executeQuery(param); String sql1 =
	 * CommonSQL.MTR_DUR_DUTY_REPORT_PRV; logger.debug("sql query====" + sql1);
	 * dbutility.createPreparedStatement(sql1); retsub1 =
	 * dbutility.executeQuery(param1); for(int i=0;i<retsub.size();i++){
	 * ArrayList ret=(ArrayList)retsub.get(i); ArrayList
	 * ret1=(ArrayList)retsub1.get(i); String doccurr=(String)ret.get(1); String
	 * revdur=(String)ret.get(2); String valdur=(String)ret.get(3); String
	 * docprv=(String)ret1.get(0);
	 * 
	 * String revcurr=(String)ret1.get(1);
	 * 
	 * String valcurr=(String)ret1.get(2);
	 * 
	 * if(doccurr==null){ dc=0; } else{ dc=Integer.parseInt(doccurr); }
	 * if(docprv==null){ dp=0; } else{ dp=Integer.parseInt(docprv); }
	 * if(revcurr==null){ rc=0.0; }else{ rc=Double.parseDouble(revcurr); }
	 * if(revdur==null){ rd=0.0; }else{ rd=Double.parseDouble(revdur); }
	 * if(valdur==null){ vd=0.0; }else{ vd=Double.parseDouble(valdur); }
	 * if(valcurr==null){ vc=0.0; }else{ vc=Double.parseDouble(valcurr); }
	 * 
	 * int docComp=Math.abs(dp-dc); double revComp=Math.abs(rd-rc); double
	 * valComp=Math.abs(vd-vc); int totaldocComp=0; double totalRevComp=0.0;
	 * double totalValComp=0.0; totaldocComp=totaldocComp+docComp;
	 * totalRevComp=totalRevComp+revComp; totalValComp=totalValComp+valComp;
	 * String docS=String.valueOf(BigDecimal.valueOf(docComp).toPlainString());
	 * String revC=String.valueOf(BigDecimal.valueOf(revComp).toPlainString());
	 * String valC=String.valueOf(BigDecimal.valueOf(valComp).toPlainString());
	 * ret.add(docS); ret.add(revC); ret.add(valC); mainret.add(ret); }
	 * 
	 * 
	 * }catch (Exception err) {
	 * 
	 * logger.error("this is Exception in " + "getdurReport DAO: " + err); }
	 * finally { try { dbutility.closeConnection(); } catch (Exception err) {
	 * 
	 * logger.error("this is Fianlly Try catch in " + "getdurReport DAO " +
	 * err); } } logger.debug("in getdeedReport1 dao block end"); return
	 * mainret; }
	 */

	public ArrayList getAreaReport(MarketTrendReportDTO mtrDto) {
		logger.debug("in getAreaReport dao block start");
		ArrayList ret = new ArrayList();
		ArrayList ret1 = new ArrayList();
		ArrayList mainRet = new ArrayList();
		ArrayList mainRet1 = new ArrayList();
		ArrayList mainRet2 = new ArrayList();
		Double r1 = 0.0, v1 = 0.0, r2 = 0.0, v2 = 0.0, n1 = 0.0, n2 = 0.0, nodoccomp = 0.0, revnue = 0.0, valun = 0.0;
		String nodoc = "";
		String rev = "";
		String val = "";
		try {

			dbutility = new DBUtility();
			String sql = CommonSQL.MTR_AREA_DUTY_REPORT;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			String sd[] = new String[6];
			sd[0] = mtrDto.getDistId();
			sd[1] = mtrDto.getTehisilId();
			sd[2] = mtrDto.getAreaTypeId();
			sd[3] = mtrDto.getSubAreaId();
			sd[4] = mtrDto.getWardId().split("~")[0];
			sd[5] = mtrDto.getMohallaId().split("~")[0];

			ret = dbutility.executeQuery(sd);
			String sql1 = CommonSQL.MTR_AREA_DUTY_REPORT_PRV;
			logger.debug("sql query====" + sql1);
			dbutility.createPreparedStatement(sql1);
			ret1 = dbutility.executeQuery(sd);

			for (int i = 0; i < ret.size(); i++) {

				if (ret1.size() > 0) {
					ArrayList tmpsubList = (ArrayList) ret1.get(i);

					String nd1 = (String) tmpsubList.get(0);
					String rs1 = (String) tmpsubList.get(1);
					String vs1 = (String) tmpsubList.get(2);

					if (nd1 == null) {
						n1 = 0.0;
					} else {
						n1 = Double.parseDouble(nd1);
					}
					if (rs1 == null) {
						r1 = 0.0;
					} else {
						r1 = Double.parseDouble(rs1);
					}
					if (vs1 == null) {
						v1 = 0.0;
					} else {
						v1 = Double.parseDouble(vs1);
					}
				} else {
					n1 = 0.0;
					r1 = 0.0;
					v1 = 0.0;
				}
				if (ret.size() > 0) {
					ArrayList tmpsubList1 = (ArrayList) ret.get(i);
					String nd2 = (String) tmpsubList1.get(1);
					String rs2 = (String) tmpsubList1.get(2);
					String vs2 = (String) tmpsubList1.get(3);

					if (nd2 == null) {
						n2 = 0.0;
					} else {
						n2 = Double.parseDouble(nd2);
					}
					if (rs2 == null) {
						r2 = 0.0;
					} else {
						r2 = Double.parseDouble(rs2);
					}
					if (vs2 == null) {
						v2 = 0.0;
					} else {
						v2 = Double.parseDouble(vs2);
					}
				} else {
					n2 = 0.0;
					r2 = 0.0;
					v2 = 0.0;
				}
				if (n1 == 0) {
					nodoc = String.valueOf("-");
				} else {
					nodoccomp = Math.abs((((n1 - n2) / n1) * 100));
					nodoc = String.valueOf(nodoccomp).concat("%");
				}
				if (r1 == 0) {
					rev = String.valueOf("-");
				} else {
					revnue = Math.abs((((r1 - r2) / r1) * 100));
					rev = String.valueOf(revnue).concat("%");
				}
				if (v1 == 0) {
					val = String.valueOf("-");
				} else {
					valun = Math.abs((((v1 - v2) / v1) * 100));
					val = String.valueOf(valun).concat("%");
				}

				ArrayList ret2 = new ArrayList();

				ret2.add(nodoc);
				ret2.add(rev);
				ret2.add(val);

				mainRet.add(ret);
				mainRet1.add(ret2);
				/*
				 * 
				 * mainRet1.add(ret2); mainRet.add(mainRet1);
				 */
			}
			mainRet2.add(mainRet);
			mainRet2.add(mainRet1);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getAreaReport DAO: " + err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {

				logger.error("this is Fianlly Try catch in "
						+ "getAreaReport DAO " + err);
			}
		}
		logger.debug("in getAreaReport dao block end");
		return mainRet2;
	}

	public ArrayList getTotalReport(MarketTrendReportDTO mtrDto) {
		logger.debug("in getTotalReport dao block start");
		ArrayList ret = new ArrayList();
		ArrayList ret1 = new ArrayList();

		try {
			String sd[] = new String[6];
			sd[0] = mtrDto.getDistId();
			sd[1] = mtrDto.getTehisilId();
			sd[2] = mtrDto.getAreaTypeId();
			sd[3] = mtrDto.getSubAreaId();
			sd[4] = mtrDto.getWardId().split("~")[0];
			sd[5] = mtrDto.getMohallaId().split("~")[0];
			dbutility = new DBUtility();
			String sql = CommonSQL.MTR_AREA_TOTAL_DUTY_REPORT;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			ret = dbutility.executeQuery(sd);
			String sql1 = CommonSQL.MTR_AREA_TOTAL_DUTY_REPORT_PRV;
			logger.debug("sql query====" + sql1);
			dbutility.createPreparedStatement(sql1);
			ret1 = dbutility.executeQuery(sd);
			ret.add(ret1);

		} catch (Exception err) {

			logger
					.error("this is Exception in " + "getTotalReport DAO: "
							+ err);
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception err) {

				logger.error("this is Fianlly Try catch in "
						+ "getTotalReport DAO " + err);
			}
		}
		logger.debug("in getTotalReport dao block end");
		return ret;
	}

	/*
	 * public ArrayList getTotalReportDur(String fromYear,String toYear){
	 * logger.debug("in getTotalReportDur dao block start"); ArrayList retsub=
	 * new ArrayList(); ArrayList retsub1=new ArrayList(); int dp=0,dc=0; double
	 * rd=0.0,rc=0.0,vc=0.0,vd=0.0; ArrayList mainret=new ArrayList(); int
	 * yearplus=Integer.parseInt(toYear); yearplus=yearplus+1; String
	 * toyearplus1=String.valueOf(yearplus); try{ String
	 * param[]={fromYear,toyearplus1}; String param1[]={toYear}; dbutility = new
	 * DBUtility(); String sql = CommonSQL.MTR_DUR_TOTAL_DUTY_REPORT;
	 * logger.debug("sql query====" + sql);
	 * dbutility.createPreparedStatement(sql);
	 * retsub=dbutility.executeQuery(param); String sql1 =
	 * CommonSQL.MTR_DUR_TOTAL_DUTY_REPORT_PRV; logger.debug("sql query====" +
	 * sql1); dbutility.createPreparedStatement(sql1); retsub1 =
	 * dbutility.executeQuery(param1); ArrayList ret=(ArrayList)retsub.get(0);
	 * ArrayList ret1=(ArrayList)retsub1.get(0); String
	 * doccurr=(String)ret.get(1); String revdur=(String)ret.get(2); String
	 * valdur=(String)ret.get(3); String docprv=(String)ret1.get(0);
	 * 
	 * String revcurr=(String)ret1.get(1);
	 * 
	 * String valcurr=(String)ret1.get(2);
	 * 
	 * if(doccurr==null){ dc=0; } else{ dc=Integer.parseInt(doccurr); }
	 * if(docprv==null){ dp=0; } else{ dp=Integer.parseInt(docprv); }
	 * if(revcurr==null){ rc=0.0; }else{ rc=Double.parseDouble(revcurr); }
	 * if(revdur==null){ rd=0.0; }else{ rd=Double.parseDouble(revdur); }
	 * if(valdur==null){ vd=0.0; }else{ vd=Double.parseDouble(valdur); }
	 * if(valcurr==null){ vc=0.0; }else{ vc=Double.parseDouble(valcurr); } int
	 * docComp=Math.abs(dp-dc); double revComp=Math.abs(rd-rc); double
	 * valComp=Math.abs(vd-vc); String
	 * docS=String.valueOf(BigDecimal.valueOf(docComp).toPlainString()); String
	 * revC=String.valueOf(BigDecimal.valueOf(revComp).toPlainString()); String
	 * valC=String.valueOf(BigDecimal.valueOf(valComp).toPlainString());
	 * ret.add(docS); ret.add(revC); ret.add(valC); mainret.add(ret);
	 * 
	 * 
	 * }catch (Exception err) {
	 * 
	 * logger.error("this is Exception in " + "getTotalReportDur DAO: " + err);
	 * } finally { try { dbutility.closeConnection(); } catch (Exception err) {
	 * 
	 * logger.error("this is Fianlly Try catch in " + "getTotalReportDur DAO " +
	 * err); } } logger.debug("in getTotalReportDur dao block end"); return
	 * mainret; }
	 */
	public String getConfigYear() {
		logger.debug("in getConfigYear dao block start");
		String year = "";
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.MTR_DUR_CONFIG_PARAM;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			year = dbutility.executeQry(sql);
			System.out.println(year);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getConfigYear DAO: " + err);
		}
		return year;
	}

	// added by Shreeraj
	public ArrayList getSearchReqReport(String froDate, String toDate) {
		logger.debug("in getSearchReqReport dao block start");
		ArrayList ret = new ArrayList();
		ArrayList ret1 = new ArrayList();
		ArrayList retList = new ArrayList();
		String sql1 = "";
		String toDatep = toDate.split("/")[0];
		String toDatePlus = String.valueOf(Integer.parseInt(toDatep) + 1);
		toDate = toDate.replaceFirst(toDatep, toDatePlus);
		try {
			String param[] = { froDate, toDate };
			dbutility = new DBUtility();
			String sql = CommonSQL.MIS_SEARCH_REQUEST;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			ret = dbutility.executeQuery(param);
			sql1 = CommonSQL.MIS_SEARCH_REQUEST_REGNO;
			logger.debug("sql query====" + sql1);
			for (int i = 0; i < ret.size(); i++) {
				ArrayList retloop = new ArrayList();
				ArrayList retsub = (ArrayList) ret.get(i);
				String param1[] = { (String) retsub.get(1),
						(String) retsub.get(1) };
				dbutility.createPreparedStatement(sql1);
				retloop = dbutility.executeQuery(param1);
				ret1.add(retloop);

			}
			retList.add(ret);
			retList.add(ret1);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getSearchReqReport DAO: "
					+ err);
		}
		return retList;
	}

	public ArrayList getRegCopyReport(String froDate, String toDate) {
		logger.debug("in getSearchReqReport dao block start");
		ArrayList ret = new ArrayList();
		ArrayList ret1 = new ArrayList();
		ArrayList retList = new ArrayList();
		String sql1 = "";
		String toDatep = toDate.split("/")[0];
		String toDatePlus = String.valueOf(Integer.parseInt(toDatep) + 1);
		toDate = toDate.replaceFirst(toDatep, toDatePlus);
		try {
			String param[] = { froDate, toDate };
			dbutility = new DBUtility();
			String sql = CommonSQL.MIS_REG_DOC_COPY_REQUEST;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			ret = dbutility.executeQuery(param);
			sql1 = CommonSQL.MIS_REG_DOC_COPY_REQUEST_REGNO;
			logger.debug("sql query====" + sql1);
			for (int i = 0; i < ret.size(); i++) {
				ArrayList retloop = new ArrayList();
				ArrayList retsub = (ArrayList) ret.get(i);
				String param1[] = { (String) retsub.get(2) };
				dbutility.createPreparedStatement(sql1);
				retloop = dbutility.executeQuery(param1);
				ret1.add(retloop);

			}
			retList.add(ret);
			retList.add(ret1);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getSearchReqReport DAO: "
					+ err);
		}
		return retList;
	}

	public ArrayList getSroList(String tehsilId, String language) {
		logger.debug("in getSroList dao block start");
		ArrayList list = new ArrayList();
		try {

			dbutility = new DBUtility();
			String sql = CommonSQL.REV_RCPT_REPORT_SRO;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			list = dbutility.executeQuery(sql);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getSroList DAO: " + err);
		}
		return list;
	}

	public ArrayList getRevRcptSROReport(String month, String year) {
		logger.debug("in getRevRcptSROReport dao block start");
		String mplus = "";
		int m = 0;
		ArrayList list = new ArrayList();
		ArrayList listPrv = new ArrayList();
		ArrayList listApr = new ArrayList();
		ArrayList listAprPrv = new ArrayList();
		ArrayList mainList = new ArrayList();
		try {
			String param[] = { month, year, month, year };
			String yr = String.valueOf(Integer.parseInt(year) - 1);
			String param1[] = { month, yr, month, yr };
			String param2[] = new String[4];
			String param3[] = new String[4];
			if (!month.equalsIgnoreCase("12")) {
				m = Integer.parseInt(month);
				mplus = String.valueOf(m + 1);
			} else {
				mplus = "12";
			}
			param3[0] = month;
			param3[2] = month;

			if (m > 4) {
				param2[0] = mplus;
				param2[1] = year;
				param2[2] = mplus;
				param2[3] = year;
				param3[1] = year;
				param3[3] = year;
			} else {
				param2[0] = mplus;
				param2[1] = yr;
				param2[2] = mplus;
				param2[3] = yr;
				param3[3] = yr;
				param3[1] = yr;
			}

			dbutility = new DBUtility();
			String sql = CommonSQL.REV_RCPT_REPORT_SRO_REPORT;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
			listPrv = dbutility.executeQuery(param1);
			String sql1 = CommonSQL.REV_RCPT_REPORT_SRO_REPORT_APRIL;
			logger.debug("sql query====" + sql1);
			dbutility.createPreparedStatement(sql1);
			listApr = dbutility.executeQuery(param2);
			listAprPrv = dbutility.executeQuery(param3);
			mainList.add(list);
			mainList.add(listPrv);
			mainList.add(listApr);
			mainList.add(listAprPrv);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getRevRcptSROReport DAO: "
					+ err);
		}
		return mainList;
	}

	public ArrayList getSROStampRegReport(String month, String year) {
		logger.debug("in getSROStampRegReport dao block start");
		ArrayList list = new ArrayList();
		ArrayList listPrv = new ArrayList();
		ArrayList listApr = new ArrayList();
		ArrayList listAprPrv = new ArrayList();
		ArrayList mainList = new ArrayList();
		try {
			String param[] = { month, year };
			String yr = String.valueOf(Integer.parseInt(year) - 1);
			String param1[] = { month, yr };
			String param2[] = new String[2];
			String param3[] = new String[2];
			int m = Integer.parseInt(month);
			String mplus = String.valueOf(m + 1);
			param3[0] = month;

			if (m > 4) {
				param2[0] = mplus;
				param2[1] = year;
				param3[1] = year;

			} else {
				param2[0] = mplus;
				param2[1] = yr;
				param3[1] = yr;
			}

			dbutility = new DBUtility();
			String sql = CommonSQL.REV_RCPT_STAMPREGFEE_REPORT;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
			listPrv = dbutility.executeQuery(param1);
			String sql1 = CommonSQL.REV_RCPT_STAMPREGFEE_REPORT_APRIL;
			logger.debug("sql query====" + sql1);
			dbutility.createPreparedStatement(sql1);
			listApr = dbutility.executeQuery(param2);
			listAprPrv = dbutility.executeQuery(param3);
			mainList.add(list);
			mainList.add(listPrv);
			mainList.add(listApr);
			mainList.add(listAprPrv);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getSROStampRegReport DAO: "
					+ err);
		}
		return mainList;
	}

	public ArrayList getSubArea(String language, String areaId,
			String tehsilId, String urbanFlag) {
		ArrayList subAreaList = null;
		try {
			dbutility = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				if ("Y".equalsIgnoreCase(urbanFlag)) {
					dbutility
							.createPreparedStatement(CommonSQL.SUB_AREA_NAME_EN_UR);
					subAreaList = dbutility.executeQuery(new String[] { areaId,
							tehsilId });

				} else {
					dbutility
							.createPreparedStatement(CommonSQL.SUB_AREA_NAME_EN);
					subAreaList = dbutility
							.executeQuery(new String[] { areaId });
				}
			} else {
				if ("Y".equalsIgnoreCase(urbanFlag)) {
					dbutility
							.createPreparedStatement(CommonSQL.SUB_AREA_NAME_HI_UR);
					subAreaList = dbutility.executeQuery(new String[] { areaId,
							tehsilId });
				} else {
					dbutility
							.createPreparedStatement(CommonSQL.SUB_AREA_NAME_HI);
					subAreaList = dbutility
							.executeQuery(new String[] { areaId });

				}
			}

			return subAreaList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}

	public ArrayList getWardPatwari(String language, String subAreaId,
			String tehsilId) {
		ArrayList wardPatwariList = null;
		try {
			dbutility = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				dbutility
						.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_EN);
			} else {
				dbutility
						.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_HI);
			}
			wardPatwariList = dbutility.executeQuery(new String[] { subAreaId,
					tehsilId });
			return wardPatwariList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public ArrayList getColonyName(String language, String wardPatwariId) {
		ArrayList l2NameList = null;
		try {
			dbutility = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				dbutility.createPreparedStatement(CommonSQL.COLONY_NAME_EN);
			} else {
				dbutility.createPreparedStatement(CommonSQL.COLONY_NAME_HI);
			}
			l2NameList = dbutility.executeQuery(new String[] { wardPatwariId
					.split("~")[1] });
			return l2NameList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public ArrayList getRevenueReceiptReport(ReportingDTO dto) {
		ArrayList list = new ArrayList();
		String param[] = new String[32];
		try {
			dbutility = new DBUtility();
			String toYear = dto.getYear();
			String fromYear = String
					.valueOf(Integer.parseInt(dto.getYear()) - 1);
			// String toYear=String.valueOf(Integer.parseInt(dto.getYear())+1);
			String distID = dto.getDistrictID();
			String month = dto.getMonthTarget();

			param[0] = distID;
			param[1] = fromYear;
			param[2] = toYear;
			param[3] = month;

			param[4] = distID;
			param[5] = fromYear;
			param[6] = toYear;
			param[7] = month;

			param[8] = distID;
			param[9] = fromYear;
			param[10] = toYear;
			param[11] = month;

			param[12] = distID;
			param[13] = fromYear;
			param[14] = toYear;
			param[15] = month;

			param[16] = distID;
			param[17] = fromYear;
			param[18] = toYear;
			param[19] = month;

			param[20] = distID;
			param[21] = fromYear;
			param[22] = toYear;
			param[23] = month;

			param[24] = distID;
			param[25] = fromYear;
			param[26] = toYear;
			param[27] = month;

			param[28] = distID;
			param[29] = fromYear;
			param[30] = toYear;
			param[31] = month;

			String sql = CommonSQL.REC_RCPT_AND_REG_DOC_REPORT;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList getTotalRevenueReceiptReport(ReportingDTO dto) {
		ArrayList list = new ArrayList();
		String param[] = new String[32];
		try {
			dbutility = new DBUtility();
			String toYear = dto.getYear();
			String fromYear = String
					.valueOf(Integer.parseInt(dto.getYear()) - 1);
			String distID = dto.getDistrictID();
			String month = dto.getMonthTarget();

			param[0] = distID;
			param[1] = fromYear;
			param[2] = toYear;
			param[3] = month;

			param[4] = distID;
			param[5] = fromYear;
			param[6] = toYear;
			param[7] = month;

			param[8] = distID;
			param[9] = fromYear;
			param[10] = toYear;
			param[11] = month;

			param[12] = distID;
			param[13] = fromYear;
			param[14] = toYear;
			param[15] = month;

			param[16] = distID;
			param[17] = fromYear;
			param[18] = toYear;
			param[19] = month;

			param[20] = distID;
			param[21] = fromYear;
			param[22] = toYear;
			param[23] = month;

			param[24] = distID;
			param[25] = fromYear;
			param[26] = toYear;
			param[27] = month;

			param[28] = distID;
			param[29] = fromYear;
			param[30] = toYear;
			param[31] = month;

			String sql = CommonSQL.REC_RCPT_AND_REG_DOC_REPORT_TOTAL;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// Added by Rachita for Create Target
	public ArrayList getCurrentYearCreateTarget() {
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();

			dbutility.createStatement();

			ret = dbutility.executeQuery(CommonSQL.CURRENT_YEAR_CREATE_TARGET);
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {

				logger.error(err);
			}
		}
		return ret;
	}

	public ArrayList getSubjectWiseReport(ReportingDTO dto) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			String fromYear = dto.getYear();
			String toYear = "";

			String month = dto.getMonthTarget();
			String monthplus = String.valueOf(Integer.parseInt(month) + 1);
			if (monthplus.equals("13")) {
				monthplus = "12";
			}
			/*
			 * param[0]=distID; param[1]=fromYear; param[2]=toYear;
			 */
			int m = Integer.parseInt(month);
			if (m < 4)
				toYear = String.valueOf(Integer.parseInt(dto.getYear()) + 1);
			else
				toYear = fromYear;

			String sql = CommonSQL.SUB_WISE_STAMP_REGFEE_REPORT;
			String param[] = { month + "/" + toYear, "04/" + fromYear,
					monthplus + "/" + toYear };

			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getTotalSubjectWiseReport(ReportingDTO dto) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			String fromYear = dto.getYear();
			String toYear = "";

			String month = dto.getMonthTarget();
			String monthplus = String.valueOf(Integer.parseInt(month) + 1);
			if (monthplus.equals("13")) {
				monthplus = "12";
			}
			/*
			 * param[0]=distID; param[1]=fromYear; param[2]=toYear;
			 */
			int m = Integer.parseInt(month);
			if (m < 4)
				toYear = String.valueOf(Integer.parseInt(dto.getYear()) + 1);
			else
				toYear = fromYear;

			String sql = CommonSQL.TOT_SUB_WISE_STAMP_REGFEE_REPORT;
			String param[] = { month + "/" + toYear, "04/" + fromYear,
					monthplus + "/" + toYear };

			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getCompInfoReport(ReportingDTO dto) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			String fromYear = dto.getYear();
			String toYear = "";
			String distID = dto.getDistrictID();
			String month = dto.getMonthTarget();
			String monthplus = String.valueOf(Integer.parseInt(month) + 1);
			String prvYear = String
					.valueOf(Integer.parseInt(dto.getYear()) - 1);
			String nextYear = String
					.valueOf(Integer.parseInt(dto.getYear()) + 1);
			if (monthplus.equals("13")) {
				monthplus = "12";
			}
			String monthlyTarget = "";
			/*
			 * param[0]=distID; param[1]=fromYear; param[2]=toYear;
			 */
			int m = Integer.parseInt(month);
			if (m < 4)
				toYear = String.valueOf(Integer.parseInt(dto.getYear()) + 1);
			else
				toYear = fromYear;
			if (month.equals("01"))
				monthlyTarget = "JANUARY_C";
			if (month.equals("02"))
				monthlyTarget = "FEBRUARY_C";
			if (month.equals("03"))
				monthlyTarget = "MARCH_C";
			if (month.equals("04"))
				monthlyTarget = "APRIL_C";
			if (month.equals("05"))
				monthlyTarget = "MAY_C";
			if (month.equals("06"))
				monthlyTarget = "JUNE_C";
			if (month.equals("07"))
				monthlyTarget = "JULY_C";
			if (month.equals("08"))
				monthlyTarget = "AUGUST_C";
			if (month.equals("09"))
				monthlyTarget = "SEPTEMBER_C";
			if (month.equals("10"))
				monthlyTarget = "OCTOBER_C";
			if (month.equals("11"))
				monthlyTarget = "NOVEMBER_C";
			if (month.equals("12"))
				monthlyTarget = "DECEMBER_C";
			String sql = CommonSQL.COMP_INFO_REV_RCPT_REG_DOC_REPORT_1
					+ monthlyTarget
					+ CommonSQL.COMP_INFO_REV_RCPT_REG_DOC_REPORT;
			// String sql = CommonSQL.COMP_INFO_REV_RCPT_REG_DOC_REPORT;
			String param[] = { distID, month + "/" + toYear, distID,
					month + "/" + toYear, distID, month + "/" + toYear, distID,
					month + "/" + toYear, distID, month + "/" + toYear, distID,
					month + "/" + toYear, distID, month + "/" + toYear, distID,
					month + "/" + toYear, fromYear + "-" + nextYear, distID,
					distID, month + "/" + prvYear, distID,
					month + "/" + prvYear, distID, month + "/" + prvYear,
					distID, month + "/" + prvYear, distID,
					month + "/" + prvYear, distID, month + "/" + prvYear,
					distID, month + "/" + prvYear, distID,
					month + "/" + prvYear, fromYear + "-" + nextYear, distID,
					distID, "04/" + fromYear, monthplus + "/" + toYear, distID,
					"04/" + fromYear, monthplus + "/" + toYear, distID,
					"04/" + fromYear, monthplus + "/" + toYear, distID,
					"04/" + fromYear, monthplus + "/" + toYear, distID,
					"04/" + fromYear, monthplus + "/" + toYear, distID,
					"04/" + fromYear, monthplus + "/" + toYear, distID,
					"04/" + fromYear, monthplus + "/" + toYear, distID,
					"04/" + fromYear, monthplus + "/" + toYear,
					fromYear + "-" + nextYear, distID, distID, "04/" + prvYear,
					monthplus + "/" + prvYear, distID, "04/" + prvYear,
					monthplus + "/" + prvYear, distID, "04/" + prvYear,
					monthplus + "/" + prvYear, distID, "04/" + prvYear,
					monthplus + "/" + prvYear, distID, "04/" + prvYear,
					monthplus + "/" + prvYear, distID, "04/" + prvYear,
					monthplus + "/" + prvYear, distID, "04/" + prvYear,
					monthplus + "/" + prvYear, distID, "04/" + prvYear,
					monthplus + "/" + prvYear, fromYear + "-" + nextYear,
					distID };

			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getNoDocCompInfoReport(ReportingDTO dto) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			String fromYear = dto.getYear();
			String toYear = "";
			String distID = dto.getDistrictID();
			String month = dto.getMonthTarget();
			String monthplus = String.valueOf(Integer.parseInt(month) + 1);
			String prvYear = String
					.valueOf(Integer.parseInt(dto.getYear()) - 1);
			String nextYear = String
					.valueOf(Integer.parseInt(dto.getYear()) + 1);
			if (monthplus.equals("13")) {
				monthplus = "12";
			}
			/*
			 * param[0]=distID; param[1]=fromYear; param[2]=toYear;
			 */
			int m = Integer.parseInt(month);
			if (m < 4)
				toYear = String.valueOf(Integer.parseInt(dto.getYear()) + 1);
			else
				toYear = fromYear;

			String sql = CommonSQL.COMP_INFO_NODOC_REV_RCPT_REG_DOC_REPORT;
			String param[] = { distID, month + "/" + toYear, distID,
					month + "/" + toYear, distID, month + "/" + prvYear,
					distID, month + "/" + prvYear, distID, "04/" + fromYear,
					monthplus + "/" + toYear, distID, "04/" + fromYear,
					monthplus + "/" + toYear, distID, "04/" + prvYear,
					monthplus + "/" + prvYear, distID, "04/" + prvYear,
					monthplus + "/" + prvYear };

			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getdurReport(String fromYear, String toYear) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			if (fromYear.equalsIgnoreCase(toYear)) {
				toYear = String.valueOf(Integer.parseInt(toYear) + 1);
			}
			String prvFromYear = String.valueOf(Integer.parseInt(fromYear) - 1);
			String prvToYear = String.valueOf(Integer.parseInt(toYear) - 1);

			String sql = CommonSQL.MARKET_TREND_DUR;
			String param[] = { fromYear, toYear, prvFromYear, prvToYear };

			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getTotalReportDur(String fromYear, String toYear) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			if (fromYear.equalsIgnoreCase(toYear)) {
				toYear = String.valueOf(Integer.parseInt(toYear) + 1);
			}
			String prvFromYear = String.valueOf(Integer.parseInt(fromYear) - 1);
			String prvToYear = String.valueOf(Integer.parseInt(toYear) - 1);

			String sql = CommonSQL.MARKET_TREND_DUR_TOTAL;
			String param[] = { fromYear, toYear, prvFromYear, prvToYear };

			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
//modified by shubham

	public ArrayList getRegistrationReport(MISReportForm eForm) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			String frmDt = eForm.getFromDate()+" 00:00:00"; //Added By Praveen : " 00:00:00"
			String toDt = eForm.getToDate() +" 23:59:59"; //Added By Praveen : " 23:59:59"
			String distID = eForm.getReportDTO().getDistrictID();
			String deedID = eForm.getReportDTO().getDeedID();
			String tehsilID=eForm.getReportDTO().getTehsilIdreg();
			String officeId=eForm.getReportDTO().getOfficeRegId();
			String srId=eForm.getReportDTO().getSrUserIdreg();
			
			
			
			String param[] = null;
			// String sql = CommonSQL.REGISTRATION_REPORT;
			StringBuilder str = new StringBuilder(CommonSQL.REGISTRATION_REPORT);
			// String param[] = {fromYear, toYear,prvFromYear,prvToYear};

			logger.debug("sql query====" + str);
			//comb 1
			if (!distID.equalsIgnoreCase("") && !deedID.equalsIgnoreCase("") &&!tehsilID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("") && !srId.equalsIgnoreCase(""))  {
				str.setLength(0);
				//commented by akansha
				//str.append(CommonSQL.ALL);
				//str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_10);
				//str.append(CommonSQL.REGISTRATION_REPORT_1);
				//str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_2);
				//str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_OFFICE);
				//str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_DUTY);
				//str.append(CommonSQL.REGISTRATION_REPORT_2);
			   //str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID);
				//str.append(CommonSQL.REGISTRATION_REPORT_END);
				//str.append(CommonSQL.REGISTRATION_REPORT_DIST_ID);
				//param = new String[] { frmDt, toDt,officeId, frmDt, toDt, deedID,officeId,srId, tehsilID,frmDt,
				//		toDt, deedID,officeId,srId, deedID,  distID };
				
				str.append(CommonSQL.ALL_Selected);  // change in query for MView by ankit
				param = new String[] { frmDt, toDt,deedID,officeId,srId, tehsilID,frmDt,
								toDt, deedID,officeId,srId,  distID };
				
				logger.info("comb 1 ");
			}
			//comb 2
			else if (!distID.equalsIgnoreCase("") && !deedID.equalsIgnoreCase("") &&!tehsilID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase(""))  {
				str.setLength(0);
				/*str.append(CommonSQL.tehsil_date_district_deed_office);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_11);
				str.append(CommonSQL.REGISTRATION_REPORT_1);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_2);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_OFFICE);
				str.append(CommonSQL.REGISTRATION_REPORT_2);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID);
				str.append(CommonSQL.REGISTRATION_REPORT_END);
				str.append(CommonSQL.REGISTRATION_REPORT_DIST_ID);
				param = new String[] { frmDt, toDt,officeId, frmDt, toDt, deedID,officeId,tehsilID,frmDt,
						toDt, deedID, officeId,deedID,  distID };*/
				
				str.append(CommonSQL.Criteria2_Registration_Report); // change in query for MView by ankit
				param = new String[] { frmDt, toDt,deedID,officeId,tehsilID, frmDt, toDt, deedID,officeId, distID };
				logger.info("comb 2 ");
			}
			//comb 3
			else if (!distID.equalsIgnoreCase("") && !deedID.equalsIgnoreCase("") &&!tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase(""))  {
				str.setLength(0);
			/*	str.append(CommonSQL.tehsil_date_district_deed);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_12);
				str.append(CommonSQL.REGISTRATION_REPORT_1);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_2);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_TEHSIL);
				str.append(CommonSQL.REGISTRATION_REPORT_2);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID);
				str.append(CommonSQL.REGISTRATION_REPORT_END);
				str.append(CommonSQL.REGISTRATION_REPORT_DIST_ID);
				param = new String[] { frmDt, toDt, frmDt, toDt, deedID,tehsilID,frmDt,
						toDt, deedID, tehsilID,deedID,  distID};*/
				
				str.append(CommonSQL.Criteria3_registration_report); // change in query for MView by ankit
				param = new String[] { frmDt, toDt,  deedID,tehsilID,frmDt,
						toDt, deedID, tehsilID,  distID};
				logger.info("comb 3 ");
			}
			//comb 4
			else if (!distID.equalsIgnoreCase("") && !deedID.equalsIgnoreCase("") && tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase(""))  {
			str.setLength(0);
		//	str.append(CommonSQL.DEED_DISTRICT);
			
			str.append(CommonSQL.DEED_DISTRICT_UPDATED); // change in query for MView by ankit
			/*	param = new String[] { frmDt, toDt, frmDt, toDt, deedID,frmDt,
						toDt, deedID, deedID,  distID };*/
				
				param = new String[] { frmDt, toDt, deedID,frmDt,
						toDt, deedID,  distID };
				
				logger.info("comb 4 ");
			}
			//comb 5
			else if (!distID.equalsIgnoreCase("") && deedID.equalsIgnoreCase("") && tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase("")) {
				str.setLength(0);
				//str.append(CommonSQL.District_Without_Deed);
				str.append(CommonSQL.District_Without_Deed_updated);   //change query by ankit for MViews
				
				/*param = new String[] { frmDt, toDt, frmDt, toDt, frmDt, toDt,
						distID };*/
				
				param = new String[] { frmDt, toDt, frmDt, toDt, 
						distID };
				
				logger.info("comb 5 ");
			} 
			// comb 6
			else if (!deedID.equalsIgnoreCase("") && distID.equalsIgnoreCase("") && tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase("")) {
				str.setLength(0);
			//	str.append(CommonSQL.deedWithdate);
			/*	param = new String[] { frmDt, toDt, frmDt, toDt, deedID, frmDt,
						toDt, deedID, deedID };*/
				
				str.append(CommonSQL.deedWithdate_update); //change query by ankit for MViews
				
				param = new String[] { frmDt, toDt, deedID, frmDt,
						toDt, deedID};
				
				logger.info("comb 6 ");
			}/*--start--*///with null deed
			
			//comb 7
			else if (deedID.equalsIgnoreCase("") && !distID.equalsIgnoreCase("") && !tehsilID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("") && !srId.equalsIgnoreCase("")) {
			/*	str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_15);
				str.append(CommonSQL.REGISTRATION_REPORT_12);
			    str.append(CommonSQL.REGISTRATION_REPORT_2);
				str.append(CommonSQL.REGISTRATION_REPORT_END);
				str.append(CommonSQL.REGISTRATION_REPORT_DIST_ID);
				param = new String[] { frmDt, toDt, officeId,srId, frmDt, toDt, officeId,srId, tehsilID,srId,officeId, frmDt,
						toDt, distID }*/;
						str.setLength(0);	
						str.append(CommonSQL.combination7_registrationReport); //change query by ankit for MViews
						param = new String[] { frmDt, toDt, officeId,srId,  tehsilID,srId,officeId, frmDt,
								toDt, distID };
						logger.info("comb 7 ");
			}
			// comb 8
			else if (deedID.equalsIgnoreCase("") && !distID.equalsIgnoreCase("") && !tehsilID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase("")) {
				str.setLength(0);
				/*str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_WITHOUT_SR);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_16);
				str.append(CommonSQL.REGISTRATION_REPORT_13);
				str.append(CommonSQL.REGISTRATION_REPORT_2);
				str.append(CommonSQL.REGISTRATION_REPORT_END);
				str.append(CommonSQL.REGISTRATION_REPORT_DIST_ID);*/
				/*param = new String[] {frmDt, toDt,officeId, frmDt, toDt, officeId, tehsilID,officeId, frmDt,
						toDt, distID };*/
				str.append(CommonSQL.combination8_registrationReport);   //change query by ankit for MViews
				param = new String[] {frmDt, toDt,officeId,tehsilID,frmDt,
						toDt, officeId,tehsilID,distID };
				
				logger.info("comb 8 ");
			}
			// comb 9
			else if (deedID.equalsIgnoreCase("") && !distID.equalsIgnoreCase("") && !tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase("")) {
				str.setLength(0);
			/*	str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_WITHOUT_SR1);
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_17);
				str.append(CommonSQL.REGISTRATION_REPORT_14);
				str.append(CommonSQL.REGISTRATION_REPORT_2);
				str.append(CommonSQL.REGISTRATION_REPORT_END);
				str.append(CommonSQL.REGISTRATION_REPORT_DIST_ID);
				param = new String[] { frmDt, toDt, frmDt, toDt, tehsilID,  tehsilID,frmDt,
						toDt, distID };*/
				str.append(CommonSQL.combination9_registrationReport);  //change query by ankit for MViews
				param = new String[] { frmDt, toDt,tehsilID,frmDt,
						toDt,tehsilID, distID };
				
				logger.info("comb 9 ");
			}
			
			else if (!tehsilID.equalsIgnoreCase(""))
			{
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_1);  //no change for Mview
				param = new String[] { frmDt, toDt, frmDt, toDt, deedID, frmDt,
						toDt, deedID, deedID ,tehsilID};
			}
				else {
					logger.info("deed is selected : " + str.toString());
					str.setLength(0);
			//	str.append(CommonSQL.ONLY_DATE);
				
				str.append(CommonSQL.ONLY_DATE_UPDATED); //change for Mview
			//	param = new String[] { frmDt, toDt, frmDt, toDt, frmDt, toDt };
				param = new String[] { frmDt, toDt, frmDt, toDt };
				
				
			}
			str.append(CommonSQL.REGISTRATION_REPORT_END_ORDER);      //no change for Mview
			logger.info("Final build query : " + str.toString());
			dbutility.createPreparedStatement(str.toString());
			list = dbutility.executeQuery(param);

			// to take the sum
			StringBuilder sumQuery = new StringBuilder();
			//sumQuery.append(CommonSQL.SUM_REGISTRATION_REPORT);
			sumQuery.append(CommonSQL.SUM_REGISTRATION_REPORT_UPDATED); //no change for Mview
			
			sumQuery.append(str.toString());
			sumQuery.append(CommonSQL.SUM_REGISTRATION_REPORT_END);   //no change for Mview 
			dbutility.createPreparedStatement(sumQuery.toString());
			ArrayList totList = dbutility.executeQuery(param);
			ArrayList sumList = (ArrayList) totList.get(0);
			eForm.getReportDTO().setTotDistrict(sumList.get(0).toString());
			eForm.getReportDTO().setTotRegDate(sumList.get(2).toString());
			// eForm.getReportDTO().setTotRegNo(sumList.get(2).toString());
			eForm.getReportDTO().setTotDeed(sumList.get(3).toString());
			eForm.getReportDTO().setTotStamp(sumList.get(4).toString());
			eForm.getReportDTO().setTotGram(sumList.get(5).toString());
			eForm.getReportDTO().setTotNagar(sumList.get(6).toString());
			eForm.getReportDTO().setTotUpkar(sumList.get(7).toString());
			eForm.getReportDTO().setTotDuty(sumList.get(8).toString());
			eForm.getReportDTO().setTotRegFee(sumList.get(9).toString());
			eForm.getReportDTO().setTotRegDutySum(sumList.get(12).toString());
			eForm.getReportDTO().setTotTehsilName(sumList.get(13).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	//added by rustam
	
	public ArrayList getRegistrationSummaryReport(MISReportForm eForm) {
		ArrayList list = new ArrayList();
	
		try {
			dbutility = new DBUtility();
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			
			String frmDt = date+" 00:00:00";
			String toDt =  date+" 23:59:59";
			String distID = eForm.getReportDTO().getDistrictID();
			String deedID = eForm.getReportDTO().getDeedID();
			String tehsilID=eForm.getReportDTO().getTehsilIdreg();
			String officeId=eForm.getReportDTO().getOfficeRegId();
			String srId=eForm.getReportDTO().getSrUserIdreg();
			String param[] = null;
			StringBuilder str = new StringBuilder(CommonSQL.REGISTRATION_REPORT);
			logger.debug("sql query====" + str);
			//comb 1
			if (!distID.equalsIgnoreCase("") && !deedID.equalsIgnoreCase("") &&!tehsilID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("") && !srId.equalsIgnoreCase(""))  {
				str.setLength(0);
				str.append(CommonSQL.ALL_Selected_G);  
				param = new String[] { frmDt, toDt,deedID,officeId,srId, tehsilID,frmDt,
								toDt, deedID,officeId,srId,  distID };
				logger.info("comb 1 ");
			}
			//comb 2
			else if (!distID.equalsIgnoreCase("") && !deedID.equalsIgnoreCase("") &&!tehsilID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase(""))  {
				str.setLength(0);
				str.append(CommonSQL.Criteria2_Registration_Report_G);
				param = new String[] { frmDt, toDt,deedID,officeId,tehsilID, frmDt, toDt, deedID,officeId, distID };
				logger.info("comb 2 ");
			}
			//comb 3
			else if (!distID.equalsIgnoreCase("") && !deedID.equalsIgnoreCase("") &&!tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase(""))  {
				str.setLength(0);
				str.append(CommonSQL.Criteria3_registration_report_G); 
				param = new String[] { frmDt, toDt,  deedID,tehsilID,frmDt,
						toDt, deedID, tehsilID,  distID};
				logger.info("comb 3 ");
			}
			//comb 4
			else if (!distID.equalsIgnoreCase("") && !deedID.equalsIgnoreCase("") && tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase(""))  {
			str.setLength(0);
			str.append(CommonSQL.DEED_DISTRICT_UPDATED_G); 
				param = new String[] { frmDt, toDt, deedID,frmDt,
						toDt, deedID,  distID };
				
				logger.info("comb 4 ");
			}
			//comb 5
			else if (!distID.equalsIgnoreCase("") && deedID.equalsIgnoreCase("") && tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase("")) {
				str.setLength(0);
				str.append(CommonSQL.District_Without_Deed_updated_G); 
				param = new String[] { frmDt, toDt, frmDt, toDt, 
						distID };
				logger.info("comb 5 ");
			} 
			// comb 6
			else if (!deedID.equalsIgnoreCase("") && distID.equalsIgnoreCase("") && tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase("")) {
				str.setLength(0);
				str.append(CommonSQL.deedWithdate_update_G); 
				param = new String[] { frmDt, toDt, deedID, frmDt,
						toDt, deedID};
				logger.info("comb 6 ");
			}/*--start--*///with null deed
			
			//comb 7
			else if (deedID.equalsIgnoreCase("") && !distID.equalsIgnoreCase("") && !tehsilID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("") && !srId.equalsIgnoreCase("")) {
						str.setLength(0);	
						str.append(CommonSQL.combination7_registrationReport_G); 
						param = new String[] { frmDt, toDt, officeId,srId,  tehsilID,srId,officeId, frmDt,
								toDt, distID };
						logger.info("comb 7 ");
			}
			// comb 8
			else if (deedID.equalsIgnoreCase("") && !distID.equalsIgnoreCase("") && !tehsilID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase("")) {
				str.setLength(0);
				str.append(CommonSQL.combination8_registrationReport_G); 
				param = new String[] {frmDt, toDt,officeId,tehsilID,frmDt,
						toDt, officeId,tehsilID,distID };
				logger.info("comb 8 ");
			}
			// comb 9
			else if (deedID.equalsIgnoreCase("") && !distID.equalsIgnoreCase("") && !tehsilID.equalsIgnoreCase("") && officeId.equalsIgnoreCase("") && srId.equalsIgnoreCase("")) {
				str.setLength(0);
				str.append(CommonSQL.combination9_registrationReport_G);
				param = new String[] { frmDt, toDt,tehsilID,frmDt,
						toDt,tehsilID, distID };
				
				logger.info("comb 9 ");
			}
			
			else if (!tehsilID.equalsIgnoreCase(""))
			{
				str.append(CommonSQL.REGISTRATION_REPORT_DEED_ID_1);  
				param = new String[] { frmDt, toDt, frmDt, toDt, deedID, frmDt,
						toDt, deedID, deedID ,tehsilID};
			}
				else {
					logger.info("deed is selected : " + str.toString());
					str.setLength(0);
				
				str.append(CommonSQL.ONLY_DATE_UPDATED_G); 
				param = new String[] { frmDt, toDt, frmDt, toDt, frmDt, toDt };
				
			}
			str.append(CommonSQL.REGISTRATION_REPORT_END_ORDER); 
			logger.info("Final build query : " + str.toString());
			dbutility.createPreparedStatement(str.toString());
			list = dbutility.executeQuery(param);

			// to take the sum
			StringBuilder sumQuery = new StringBuilder();
			
			sumQuery.append(CommonSQL.SUM_REGISTRATION_REPORT_UPDATED);
			
			sumQuery.append(str.toString());
			sumQuery.append(CommonSQL.SUM_REGISTRATION_REPORT_END);  
			dbutility.createPreparedStatement(sumQuery.toString());
			ArrayList totList = dbutility.executeQuery(param);
			ArrayList sumList = (ArrayList) totList.get(0);
			eForm.getReportDTO().setTotDistrict(sumList.get(0).toString());
			eForm.getReportDTO().setTotRegDate(sumList.get(2).toString());
			// eForm.getReportDTO().setTotRegNo(sumList.get(2).toString());
			eForm.getReportDTO().setTotDeed(sumList.get(3).toString());
			eForm.getReportDTO().setTotStamp(sumList.get(4).toString());
			eForm.getReportDTO().setTotGram(sumList.get(5).toString());
			eForm.getReportDTO().setTotNagar(sumList.get(6).toString());
			eForm.getReportDTO().setTotUpkar(sumList.get(7).toString());
			eForm.getReportDTO().setTotDuty(sumList.get(8).toString());
			eForm.getReportDTO().setTotRegFee(sumList.get(9).toString());
			eForm.getReportDTO().setTotRegDutySum(sumList.get(12).toString());
			eForm.getReportDTO().setTotTehsilName(sumList.get(13).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList getSlotRegistrationReport(MISReportForm eForm) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			String frmDt = eForm.getFromDate()+" 00:00:00"; //Added By Praveen
			String toDt = eForm.getToDate()+" 23:59:59";//Added By Praveen
			
			String updatedFromDt = eForm.getFromDate();
			String updatedToDt = eForm.getToDate();
			String distID = eForm.getReportDTO().getDistrictID();
			String offcID = eForm.getReportDTO().getOfficeId();
			//dbutility.createPreparedStatement(CommonSQL.SLOT_STATISTICS_REPORT);
			dbutility.createPreparedStatement(CommonSQL.SLOT_STATISTICS_REPORT_mview);//changed by akansha for mview
			list = dbutility.executeQuery(new String[] { frmDt, toDt, offcID,
					frmDt, toDt, offcID,frmDt, toDt, offcID,offcID,updatedToDt, updatedFromDt,offcID,frmDt,toDt, 
					distID });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getSlotRegistrationReportTotal(MISReportForm eForm) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
	/*		String frmDt = eForm.getFromDate();
			String toDt = eForm.getToDate();*/
			
			String frmDt = eForm.getFromDate() + "00:00:00";//Added By Praveen
			String toDt = eForm.getToDate()+ "23:59:59";//Added By Praveen
			String updatedFromDt = eForm.getFromDate();
			String updatedToDt = eForm.getToDate();
			String distID = eForm.getReportDTO().getDistrictID();
			String offcID = eForm.getReportDTO().getOfficeId();
			String param[] = null;
			// String sql = CommonSQL.REGISTRATION_REPORT;

			StringBuilder str = new StringBuilder(
					CommonSQL.SLOT_STATISTICS_REPORT_SUM);
			//str.append(CommonSQL.SLOT_STATISTICS_REPORT);
			str.append(CommonSQL.SLOT_STATISTICS_REPORT_mview);
			str.append(CommonSQL.SLOT_STATISTICS_REPORT_SUM_END);
			// String param[] = {fromYear, toYear,prvFromYear,prvToYear};

			logger.debug("sql query====" + str);

			logger.info("Final build query : " + str.toString());
			dbutility.createPreparedStatement(str.toString());
			list = dbutility.executeQuery(new String[] { frmDt, toDt, offcID,
					frmDt, toDt, offcID,frmDt, toDt, offcID,offcID,updatedToDt, updatedFromDt,offcID,frmDt,toDt, 
					distID });
			str.append(CommonSQL.REGISTRATION_REPORT_END);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	//Added by Ashutosh
	public ArrayList getTimeRegistrationReport(MISReportForm eForm) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			String frmDt = eForm.getFromDate();
			String toDt = eForm.getToDate();
			String distID = eForm.getReportDTO().getDistrictID();
			String srId= eForm.getUser();
			
			if ((!srId.equalsIgnoreCase("")) && (!srId.equalsIgnoreCase(null)))  {

			dbutility
					.createPreparedStatement(CommonSQL.TIME_REGISTRATION_REPORT_SR);
			list = dbutility.executeQuery(new String[] { frmDt, toDt, distID,
					frmDt, toDt, srId });
			}
			else{
				
				dbutility
				.createPreparedStatement(CommonSQL.TIME_REGISTRATION_REPORT);
		list = dbutility.executeQuery(new String[] { frmDt, toDt, distID,
				frmDt, toDt});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getSPEstampReport(MISReportForm eForm) {
		ArrayList list = new ArrayList();
		String param[] = null;
		StringBuilder str = null;
		try {
			dbutility = new DBUtility();
			String frmDt = eForm.getFromDate();
			String toDt = eForm.getToDate();
			String distID = eForm.getReportDTO().getDistrictID();
			if (eForm.getRadioSearch().equalsIgnoreCase("Y")) {
				//str = new StringBuilder(
				//		CommonSQL.SP_ESTAMP_REPORT_OTHER_SERVICES);
				frmDt = frmDt+" 00:00:00";
				toDt  = toDt+" 23:59:59";
				str = new StringBuilder(CommonSQL.SP_ESTAMP_REPORT_PARTI).append(CommonSQL.SP_ESTAMP_REPORT_OTHER_SERVICES_PARTII);
				if (distID != null && !distID.trim().equalsIgnoreCase("")) {
					str = null;
					str = new StringBuilder(CommonSQL.SP_ESTAMP_REPORT_PARTI).append(CommonSQL.SP_ESTAMP_REPORT_PARTI_DIST).append(CommonSQL.SP_ESTAMP_REPORT_OTHER_SERVICES_PARTII).append(CommonSQL.SP_ESTAMP_REPORT_PARTII_DIST);
					param = new String[] { toDt ,  frmDt ,  distID, frmDt , toDt , frmDt , toDt , frmDt , toDt , toDt , toDt ,  frmDt , toDt ,  frmDt , toDt , frmDt , frmDt, distID };
				} else {
					param = new String[] { toDt ,  frmDt ,  frmDt , toDt , frmDt , toDt , frmDt , toDt  , toDt , toDt ,  frmDt , toDt ,  frmDt , toDt , frmDt , frmDt};
				}
				str.append(CommonSQL.SP_OTHER_SER_REPORT_END);

			} else {
				frmDt = frmDt+" 00:00:00";
				toDt  = toDt+" 23:59:59";
				str = new StringBuilder(CommonSQL.SP_ESTAMP_REPORT_PARTI).append(CommonSQL.SP_ESTAMP_REPORT_PARTII);
//				str =new StringBuilder(CommonSQL.SP_ESTAMP_REPORT);
				if (distID != null && !distID.trim().equalsIgnoreCase("")) {
					str = null;
					str = new StringBuilder(CommonSQL.SP_ESTAMP_REPORT_PARTI).append(CommonSQL.SP_ESTAMP_REPORT_PARTI_DIST).append(CommonSQL.SP_ESTAMP_REPORT_PARTII).append(CommonSQL.SP_ESTAMP_REPORT_PARTII_DIST);
					param = new String[] { toDt , frmDt , distID , frmDt , toDt ,  frmDt , toDt , toDt , toDt ,  frmDt , toDt ,  frmDt , toDt , frmDt , frmDt , frmDt , toDt , frmDt , toDt , distID };
				} else {
					param = new String[] { toDt , frmDt , frmDt , toDt ,  frmDt , toDt , toDt , toDt ,  frmDt , toDt ,  frmDt , toDt , frmDt , frmDt , frmDt , toDt , frmDt , toDt };
				}
			str.append(CommonSQL.SP_ESTAMP_REPORT__END);

			}

			dbutility.createPreparedStatement(str.toString());
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getRegistrationReportDrill(MISReportForm eForm,
			String distID, String deed, String tehsilID,String DID) {
		ArrayList list = new ArrayList();
		String deedID = "";
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			
			String frmDt = eForm.getFromDate()+" 00:00:00"; //Added By Ankit : " 00:00:00"
			String toDt = eForm.getToDate() +" 23:59:59"; //Added By Ankit : " 23:59:59"
			
			String srId=eForm.getReportDTO().getSrUserIdreg();
			//if(!srId.equalsIgnoreCase("")){
				//srId="IGRS000428";
			//}
			String officeId=eForm.getReportDTO().getOfficeRegId();
			if (deed == null)
				deedID = eForm.getReportDTO().getDeedID();
			else
				deedID = deed;
			String param[] = null;
			// String sql = CommonSQL.REGISTRATION_REPORT;
			StringBuilder str = new StringBuilder(
					CommonSQL.REGISTRATION_REPORT_REGNO_WISE);
			// String param[] = {fromYear, toYear,prvFromYear,prvToYear};
			if (deedID == null)
				deedID = "";
			logger.debug("sql query====" + str);
			// Added Search Script for with out Deed and With Office,SR - Rahul Start 
			 
			 if (deedID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("") && !srId.equalsIgnoreCase(""))
			    {
				 str.append(CommonSQL.REGISTRATION_REPORT_REGNO_WISE_WITH_OFFICE_SR);
				 param = new String[] { frmDt, toDt, tehsilID,officeId,srId};
			   }
			 else  if (deedID.equalsIgnoreCase("") && !officeId.equalsIgnoreCase("")){
				 str.append(CommonSQL.REGISTRATION_REPORT_REGNO_WISE_WITH_OFFICE);
				 param = new String[] { frmDt, toDt, tehsilID,officeId};
			   }
			//Added Search Script for with out Deed and With Office ,SR - Rahul End 
			 else if (deedID.equalsIgnoreCase("")) {
				param = new String[] { frmDt, toDt, tehsilID };
			    } 
			else if(!srId.equalsIgnoreCase("")|| !officeId.equalsIgnoreCase("")){
				str.append(CommonSQL.REGISTRATION_REPORT_REGNO_WISE_DEED_ID_2);
				param = new String[] { frmDt, toDt, tehsilID,deedID};
				
			if(!officeId.equalsIgnoreCase("")){
				str.append(CommonSQL.REGISTRATION_REPORT_REGNO_WISE_OFFICE_2);
				param = new String[] { frmDt, toDt, tehsilID,deedID,officeId};
			}
			if(!srId.equalsIgnoreCase("")){
			if(!srId.equalsIgnoreCase("")  && DID==null){
				str.append(CommonSQL.REGISTRATION_REPORT_REGNO_WISE_SRID_2);
				param = new String[] { frmDt, toDt, tehsilID, deedID,officeId,srId};
			}
			else{
				str.append(CommonSQL.REGISTRATION_REPORT_REGNO_WISE_SRID_2);
				param = new String[] { frmDt, toDt, tehsilID, deedID,officeId,srId};
			}
			}
			}
			else {
				str.append(CommonSQL.REGISTRATION_REPORT_REGNO_WISE_DEED_ID_2);
				param = new String[] { frmDt, toDt, tehsilID, deedID };
			}
			str.append(CommonSQL.REGISTRATION_REPORT_REGNO_END);
			logger.info("Final build query : " + str.toString());
			dbutility.createPreparedStatement(str.toString());
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getSlotStatReportDrill(MISReportForm eForm, String distID,
			String report) {
		ArrayList list = new ArrayList();
		String deedID = "";
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			String frmDt = eForm.getFromDate() + "00:00:00";//Added By Praveen
			String toDt = eForm.getToDate()+ "23:59:59";//Added By Praveen

			String param[] = null;
			// String sql = CommonSQL.REGISTRATION_REPORT;
			/*StringBuilder str = new StringBuilder(
					CommonSQL.SLOT_STATS_REPORT_REGNO_WISE);*/
			
			StringBuilder str = new StringBuilder(
					CommonSQL.SLOT_STATS_REPORT_REGNO_WISE_MVIEW);// changed by akansha for mview
			// String param[] = {fromYear, toYear,prvFromYear,prvToYear};
			str.append(CommonSQL.SLOT_STATS_REPORT_REGNO_END_mview);//changed by akansha for mview
			logger.debug("sql query====" + str);
			
			if (report.equalsIgnoreCase("slotDelv")) {
				//str.append(CommonSQL.SLOT_STATS_REPORT_REGNO_delv);
				str = new StringBuilder();
				str.append(CommonSQL.SLOT_STATS_REPORT_REGNO_delv_mview);//changed by akansha for mview
			}
			param = new String[] { frmDt, toDt, distID };
		//	str.append(CommonSQL.SLOT_STATS_REPORT_REGNO_END);
			
			//str.append(CommonSQL.SLOT_STATS_REPORT_REGNO_END_mview);//changed by akansha for mview
			logger.info("Final build query : " + str.toString());
			dbutility.createPreparedStatement(str.toString());
			list = dbutility.executeQuery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public String getRegTxnID(String eRegNo) throws Exception {

		String[] regDtl = { eRegNo };
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

	public String getDistrictName(String distID,String language) throws Exception {

		String[] regDtl = { distID };
		String DistID = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if ("en".equalsIgnoreCase(language))
			dbUtility.createPreparedStatement(CommonSQL.GET_DIST_NAME);
			else
				dbUtility.createPreparedStatement(CommonSQL.GET_DIST_NAME_HI);
			
			DistID = dbUtility.executeQry(regDtl);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		return DistID;
	}

	public String getDeedName(String deedID,String language) throws Exception {

		String[] regDtl = { deedID };
		String regTxnID = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if ("en".equalsIgnoreCase(language))
			dbUtility.createPreparedStatement(CommonSQL.GET_DEED_NAME);
			else
				dbUtility.createPreparedStatement(CommonSQL.GET_DEED_NAME_HI);	
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

	public String getOfficeName(String offcID) throws Exception {

		String[] regDtl = { offcID };
		String officeName = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.GET_OFFICE_NAME);
			officeName = dbUtility.executeQry(regDtl);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		return officeName;
	}

	public synchronized ArrayList getDistrict(String stateId, String officeID)
			throws Exception {
		String arry[] = new String[1];
		logger.debug("State ID:-" + stateId);
		logger.debug("Office ID:-" + officeID);
		ArrayList list = new ArrayList();

		DBUtility dbUtil = null;

		try {
			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();

			String sql = CommonSQL.GET_OFFICE_TYPE_ID;
			dbUtil.createPreparedStatement(sql);
			String officeTypeID = dbUtil.executeQry(new String[] { officeID });
			String distQuery = "";
			// 4 is DIG and 2 is DR
			if (officeTypeID == null)
				officeTypeID = "";
			if ("4".equalsIgnoreCase(officeTypeID)) {
				distQuery = CommonSQL.GET_DIST_NAME_DIG;
				arry[0] = officeID;
			} 
			else if ("2".equalsIgnoreCase(officeTypeID)) {
				distQuery = CommonSQL.GET_DIST_NAME_DR;
				arry[0] = officeID;
			}
			 else if ("3".equalsIgnoreCase(officeTypeID)) {// FOR SR
				distQuery = CommonSQL.GET_DIST_NAME_SR;
				arry[0] = officeID;
			} else {
				distQuery = CommonSQL.DISTRICT_QUERY_HINDI;
				arry[0] = stateId;
			}
			dbUtil.createPreparedStatement(distQuery);

			// }
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

	public boolean validateReport(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate, fromDate });
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
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

	public boolean validateReportSplicence(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION_SPlicence;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate, fromDate });
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
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
	public boolean validateReportRegistration(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION_REGISTRATION;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			
			logger.debug("sql query for registration Report===="    + durationSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate, fromDate });
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
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
	
	
	public boolean validateGenericReport(String fromDate, String toDate, String attributeValue) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_GENERIC;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String param[] = new String[1];
			param[0] = attributeValue;
			//String day = dbUtil.executeUpdate(arr)(durationSQL);
			dbUtil.createPreparedStatement(durationSQL);
			String day=dbUtil.executeQry(param);

			dbUtil.createPreparedStatement(dateDiffSQL);
			
			logger.debug("sql query for registration Report===="    + durationSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate, fromDate });
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
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
	
	public String validateGenericReportDays(String fromDate, String toDate, String attributeValue) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_GENERIC;
		DBUtility dbUtil = null;
		String day ="";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String param[] = new String[1];
			param[0] = attributeValue;
			//String day = dbUtil.executeUpdate(arr)(durationSQL);
			dbUtil.createPreparedStatement(durationSQL);
			 day=dbUtil.executeQry(param);
			logger.debug("sql query for registration Report===="    + durationSQL);
				
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
		return day;
	}
	public boolean validateSpSumaryReport(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_SP_SummaryReport;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			
			logger.debug("sql query for registration Report===="    + durationSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate, fromDate });
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
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
	public boolean validateReportForSlot(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_SLOTREPORT_DURATION;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			
			logger.debug("sql query for registration Report===="    + durationSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate, fromDate });
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
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
	
	public boolean validateReportEstamp(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION_ESTAMP;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			
			logger.debug("sql query for registration Report===="    + durationSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate, fromDate });
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
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
	
	public String validateReportRegistrationDay(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION_REGISTRATION;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;
    String day = "";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			 day = dbUtil.executeQry(durationSQL);

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
		return day;
	}
	
	public String validateSpSumaryReportDay(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_SP_SummaryReport;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;
    String day = "";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			 day = dbUtil.executeQry(durationSQL);

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
		return day;
	}
	
	public String validateReportSpLicenseDay(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION_SPlicence;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;
    String day = "";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			 day = dbUtil.executeQry(durationSQL);

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
		return day;
	}
	public String validateReportEstampDays(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION_ESTAMP;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;
String day = "";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			 day = dbUtil.executeQry(durationSQL);

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
		return day;
	}
	public String validateReportDay(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;
		String day ="";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			 day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate, fromDate });
			
			
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
		return day;
	}

	// Added by Thilak 12/05/2017
	public boolean validateDocDeliveryReport(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_DOC_DELIVERY_REPORT_DURATION;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate, fromDate });
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
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
	
	//Added - Slot Stastics Future Max Date Validation  - RAhul  25-OCT-16   Start
	public boolean validSlotDate(String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_SLOTREPORT_DURATION;
		String dateDiffSQL = CommonSQL.REPORT_SLOTDATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { toDate});
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
				boo = true;
			else
				boo = false;
			
		} catch (Exception e) {
			logger.debug("Exception");
			// TODO: handle exception
		} finally {
			try {
				
				dbutility.closeConnection();
				if (!dbutility.isClosed())
					dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boo;
	}
	
	public String slotDuration()
	{
		
		String  slotDuration = "";
		String durationSQL = CommonSQL.GET_SLOTREPORT_DURATION;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			slotDuration = dbUtil.executeQry(durationSQL);
		} catch (Exception e) {
			logger.debug("Exception");
			// TODO: handle exception
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 	slotDuration;
	}
//Added - Slot Stastics Future Max Date Validation  - RAhul  25-OCT-16  End

	public String getPropIdSearch(String l1) {
		  DBUtility dbUtil=null;
		String id[]=l1.split("~");
		if(id.length==1)
		{try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_PROPERTY_TYPE);
			String name= dbUtil.executeQry(new String[]{l1});
			dbUtil.closeConnection();
			return name;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		else 
		{
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
	
	public ArrayList getInspectionReport(MISReportForm eForm) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		ReportDAO dao = new ReportDAO();
		String answer = eForm.getQuestion();
//changed by akansha
		String startTime = " 00:00:00";
		String endTime =  " 23:59:59";
		
		if(answer.equalsIgnoreCase("Y")){
		try {
			String propType=dao.getPropIdSearch(eForm.getPropertySubID());
			dbutility = new DBUtility();
		
			String frmDt = eForm.getDurFrom()+startTime;
			
			String toDt = eForm.getDurTo()+endTime;
			String zoneID=eForm.getZoneId();
			String distID = eForm.getDistrictID();
			String tehsilID=eForm.getTehsilId();
			String deedID=eForm.getDeedID();
            String instID=eForm.getInstID();
            String areaID=eForm.getAreaTypeId();
            String subAreaID=eForm.getSubAreaId();
            String wardId=null;
            String colonyId=null;
            String propertyId = eForm.getPropertyID();
            String propSubId=eForm.getPropertySubID();
            String rangeID=null;
            if(eForm.getRangeId().equalsIgnoreCase(""))
            	rangeID="";
             else
            	rangeID=eForm.getRangeId(); 
           // rangeID=Integer.parseInt(eForm.getRangeId());
            //String range=eForm.getRangeId();
            System.out.println(rangeID);
            String param[]=null;
            
            StringBuilder str1=new StringBuilder(CommonSQL.INSPECTION_REPORT_START); // no change
            if(propertyId.toString()!=null && !propertyId.equalsIgnoreCase("-1") && !propertyId.equalsIgnoreCase("")&&propType.toString()!=null && !propType.equalsIgnoreCase("-1") && !propType.equalsIgnoreCase("")){
            
            if(propertyId.equalsIgnoreCase("1")){
            //str1.append(CommonSQL.INSPECTION_MAIN_plot);
            str1.append(CommonSQL.INSPECTION_MAIN_plot_mview);
            
            
            
            }
            else if(propertyId.equalsIgnoreCase("2")){
                //str1.append(CommonSQL.INSPECTION_MAIN_build);
                str1.append(CommonSQL.INSPECTION_MAIN_build_mview);
                
                }
            
            else{
            	//str1.append(CommonSQL.INSPECTION_MAIN_agri);
            	str1.append(CommonSQL.INSPECTION_MAIN_agri_mview);
            }
            }
            else{
            	
            	//  str1.append(CommonSQL.INSPECTION_MAIN1);	//query 3/4 of mview
            	  
            	  str1.append(CommonSQL.INSPECTION_MAIN1_mview);
            	  
            }
            
            if(rangeID.equalsIgnoreCase("3729")){

            	str1.append(CommonSQL.INSPECTION_REPORT_RANGE1);
                }
                else if(rangeID.equalsIgnoreCase("4147")){
                	str1.append(CommonSQL.INSPECTION_REPORT_RANGE2);
                }
            
           
            param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt };
            
            
           
            
            if(areaID!=null && !areaID.equalsIgnoreCase("-1") && !areaID.toString().equalsIgnoreCase("")){
          
            //	str1.append(CommonSQL.INSPECTION_REPORT_WITH_AREA); //query 4 for mview
            	
            	
            	str1.append(CommonSQL.INSPECTION_REPORT_WITH_AREA_mview);
            	
            	
            	 param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, areaID };
            	 
            	if(subAreaID!=null && !subAreaID.equalsIgnoreCase("-1") && !subAreaID.equalsIgnoreCase("")  ) {
            		
            		//str1.append(CommonSQL.INSPECTION_REPORT_WITH_GOV_BODY_ID);// query 7
            		str1.append(CommonSQL.INSPECTION_REPORT_WITH_GOV_BODY_ID_mview);
            		
            		param=new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, areaID,subAreaID};
            		
            		
            		if(eForm.getWardIds()!=null && !eForm.getWardIds().equalsIgnoreCase("-1")
            				&& !eForm.getWardIds().equalsIgnoreCase("")){
            			
            			String[] array = eForm.getWardIds().split("\\~");
            			wardId=array[0];
            			
            			//str1.append(CommonSQL.INSPECTION_REPORT_AREA_WITH_WARD); //query 8
            			str1.append(CommonSQL.INSPECTION_REPORT_AREA_WITH_WARD_mview);
            			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, areaID,subAreaID,wardId };
            			
            		
            			if(eForm.getColonyId()!=null && !eForm.getColonyId().equalsIgnoreCase("-1")
            					&& !eForm.getColonyId().equalsIgnoreCase("")) {
            				
            				
            				String array1[]=eForm.getColonyId().split("\\~");
            				colonyId=array1[0];
            				
            				//str1.append(CommonSQL.INSPECTION_REPORT_AREA_WITH_COLONY);// query 8
            				
            				str1.append(CommonSQL.INSPECTION_REPORT_AREA_WITH_COLONY_mview);
            				
            				param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, areaID,subAreaID,wardId,colonyId};
            				
            				
            				
            			}
            			
            			
            		}
            		
            		
            	}
            	 
            	
            	
            }
            
            if(propertyId.toString()!=null && !propertyId.equalsIgnoreCase("-1") && !propertyId.equalsIgnoreCase("")&&
            		(areaID==null || areaID.equalsIgnoreCase("-1") || areaID.toString().equalsIgnoreCase(""))		 ) {
            	
            //	str1.append(CommonSQL.INSPECTION_REPORT_WITH_PROPERTY_ID);// query 5
            	
            	str1.append(CommonSQL.INSPECTION_REPORT_WITH_PROPERTY_ID_mview);
            	
            	//param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId };
            	
            	if(propType.toString()!=null && !propType.equalsIgnoreCase("-1") && !propType.equalsIgnoreCase("")){
            		 int l1=Integer.parseInt(eForm.getPropertySubID());// l1 id
            		if("1".equalsIgnoreCase(propertyId))
            		{	
            			
            		//	str1.append(CommonSQL.INSPECTION_REPORT_FOR_PLOT); commented for mview
            			
            			switch(l1)
           			 {
       			 	/*case 1: 	str1.append( " and ((plot.RESIDENTIAL_AREA is not null) and (plot.RESIDENTIAL_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      				 			break;
      			 	case 2: 	str1.append( " and ((plot.COMMERCIAL_AREA is not null) and (plot.COMMERCIAL_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;
      			 	case 3: 	str1.append( " and ((plot.INDUSTRIAL_AREA is not null) and (plot.INDUSTRIAL_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId};
      	 						break;
      			 	case 4: 	str1.append( " and ((plot.HEALTH_AREA is not null) and (plot.HEALTH_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;
      			 	case 5: 	str1.append( " and ((plot.EDUCATION_AREA is not null) and (plot.EDUCATION_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;
      			 	case 6: 	str1.append( " and ((plot.OTHER_AREA is not null) and (plot.OTHER_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;
      			 	case 33: 	str1.append( " and ((plot.RESICUMM_AREA is not null) and (plot.RESICUMM_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;*/
            			
            			
           			case 1: 	str1.append( " and ((a.RESIDENTIAL_AREA is not null) and (a.RESIDENTIAL_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      				 			break;
      			 	case 2: 	str1.append( " and ((a.COMMERCIAL_AREA is not null) and (a.COMMERCIAL_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;
      			 	case 3: 	str1.append( " and ((a.INDUSTRIAL_AREA is not null) and (a.INDUSTRIAL_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId};
      	 						break;
      			 	case 4: 	str1.append( " and ((a.HEALTH_AREA is not null) and (a.HEALTH_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;
      			 	case 5: 	str1.append( " and ((a.EDUCATION_AREA is not null) and (a.EDUCATION_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;
      			 	case 6: 	str1.append( " and ((a.OTHER_AREA is not null) and (a.OTHER_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;
      			 	case 33: 	str1.append( " and ((a.RESICUMM_AREA is not null) and (a.RESICUMM_AREA!=0))");
      			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
      	 						break;
      			 }
            			
            		//	param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId };	
            			
            			//param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, propertyId};
            		
            		}
            		else if("3".equalsIgnoreCase(propertyId))
            		{
            			//str1.append(CommonSQL.INSPECTION_REPORT_FOR_agri); commented for mview
            			
            			//str1.append(CommonSQL.INSPECTION_REPORT_FOR_agri1);
            			str1.append(CommonSQL.INSPECTION_REPORT_FOR_agri1_mview);
            			//param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId,propSubId };
            			
            			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId,propSubId};	
            		}
            		else if("2".equalsIgnoreCase(propertyId))
            		{
            			//str1.append(CommonSQL.INSPECTION_REPORT_FOR_building); //commented for mview
            			
            		//	str1.append(CommonSQL.INSPECTION_REPORT_FOR_building1); 
            			str1.append(CommonSQL.INSPECTION_REPORT_FOR_building1_mview); 
            			
            			//param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId,propSubId };
            			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId,propSubId};	
            		}
        				
            		
            		
            		
            		//param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId,propSubId };
            		
            		
            	}
            	
            	else{
            		
            		param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId };	// query 5
            		
            		
            	}
            	
            	
            }
            	
            if(propertyId.toString()!=null && !propertyId.equalsIgnoreCase("-1") && !propertyId.equalsIgnoreCase("")&&
            		areaID!=null && !areaID.equalsIgnoreCase("-1") && !areaID.toString().equalsIgnoreCase(""))
            		{
            	
            	//str1.append(CommonSQL.INSPECTION_REPORT_WITH_PROPERTY_ID);  // mview
            	str1.append(CommonSQL.INSPECTION_REPORT_WITH_PROPERTY_ID_mview);
            	
            		param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID,propertyId };
            		
            	
            	if(propType.toString()!=null && !propType.equalsIgnoreCase("-1") && !propType.equalsIgnoreCase("")){

           		 int l1=Integer.parseInt(eForm.getPropertySubID());// l1 id
           		if("1".equalsIgnoreCase(propertyId))
           		{	
           			
           	//		str1.append(CommonSQL.INSPECTION_REPORT_FOR_PLOT); // only natural join //query 11 remove
           			
           			switch(l1)
          			 {
          			 	/*case 1: 	str1.append( " and ((plot.RESIDENTIAL_AREA is not null) and (plot.RESIDENTIAL_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          				 			break;
          				case 2: 	str1.append( " and ((plot.COMMERCIAL_AREA is not null) and (plot.COMMERCIAL_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break; 	
          	 						
          	 			case 3: 	str1.append( " and ((plot.INDUSTRIAL_AREA is not null) and (plot.INDUSTRIAL_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          				 case 4: 	str1.append( " and ((plot.HEALTH_AREA is not null) and (plot.HEALTH_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          			 	case 5: 	str1.append( " and ((plot.EDUCATION_AREA is not null) and (plot.EDUCATION_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          			 	case 6: 	str1.append( " and ((plot.OTHER_AREA is not null) and (plot.OTHER_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          			 	case 33: 	str1.append( " and ((plot.RESICUMM_AREA is not null) and (plot.RESICUMM_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;			
          				 			*/
           			
           			
           			
          				 			//mview changes
          			 	case 1: 	str1.append( " and ((a.RESIDENTIAL_AREA is not null) and (a.RESIDENTIAL_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          				 			break;
          			 	case 2: 	str1.append( " and ((a.COMMERCIAL_AREA is not null) and (a.COMMERCIAL_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          			 	case 3: 	str1.append( " and ((a.INDUSTRIAL_AREA is not null) and (a.INDUSTRIAL_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          			 	case 4: 	str1.append( " and ((a.HEALTH_AREA is not null) and (a.HEALTH_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          			 	case 5: 	str1.append( " and ((a.EDUCATION_AREA is not null) and (a.EDUCATION_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          			 	case 6: 	str1.append( " and ((a.OTHER_AREA is not null) and (a.OTHER_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          			 	case 33: 	str1.append( " and ((a.RESICUMM_AREA is not null) and (a.RESICUMM_AREA!=0))");
          			 	param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
          	 						break;
          			 }
           			
           		//	param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId };	
           			
           			//param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID, propertyId};
           		
           		}
           		else if("3".equalsIgnoreCase(propertyId))
           		{
           			//str1.append(CommonSQL.INSPECTION_REPORT_FOR_agri); //query 8 to remove
           			
           			//str1.append(CommonSQL.INSPECTION_REPORT_FOR_agri1);//query 8 
           			str1.append(CommonSQL.INSPECTION_REPORT_FOR_agri1_mview);
           			//param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId,propSubId };
           			

           			
           			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID,propertyId,propSubId};	
           		}
           		else if("2".equalsIgnoreCase(propertyId))
           		{
           		//	str1.append(CommonSQL.INSPECTION_REPORT_FOR_building); // query 10 removing
           			
           			//str1.append(CommonSQL.INSPECTION_REPORT_FOR_building1);
           			str1.append(CommonSQL.INSPECTION_REPORT_FOR_building1_mview);
           			//param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId,propSubId };
           			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID,propertyId,propSubId};	
           		}
       				
           		
           		
           		
           		//param = new String[] {zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,propertyId,propSubId };
           		
           	if(!propertyId.equalsIgnoreCase("1")){	
           		if(subAreaID!=null && !subAreaID.equalsIgnoreCase("-1") && !subAreaID.equalsIgnoreCase("")  ) {
           			
           			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID,subAreaID,propertyId,propSubId};	
           			
           		}
            	
           		
           		if(eForm.getWardIds()!=null && !eForm.getWardIds().equalsIgnoreCase("-1")
        				&& !eForm.getWardIds().equalsIgnoreCase("")){
           			String[] array = eForm.getWardIds().split("\\~");
        			wardId=array[0];
        			
           			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID,subAreaID,wardId,propertyId,propSubId};
           		}
           		
           		if(eForm.getColonyId()!=null && !eForm.getColonyId().equalsIgnoreCase("-1")
    					&& !eForm.getColonyId().equalsIgnoreCase("")) {
    				
    				
    				String array1[]=eForm.getColonyId().split("\\~");
    				colonyId=array1[0];
    				
    			
    				
    				param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, areaID,subAreaID,wardId,colonyId,propertyId,propSubId};
    				
    				
    				
    			}
           	}
           	else{
           		
           		
           		if(subAreaID!=null && !subAreaID.equalsIgnoreCase("-1") && !subAreaID.equalsIgnoreCase("")  ) {
           			
           			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID,subAreaID,propertyId};	
           			
           		}
            	
           		
           		if(eForm.getWardIds()!=null && !eForm.getWardIds().equalsIgnoreCase("-1")
        				&& !eForm.getWardIds().equalsIgnoreCase("")){
           			String[] array = eForm.getWardIds().split("\\~");
        			wardId=array[0];
        			
           			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID,subAreaID,wardId,propertyId};
           		}
           		
           		if(eForm.getColonyId()!=null && !eForm.getColonyId().equalsIgnoreCase("-1")
    					&& !eForm.getColonyId().equalsIgnoreCase("")) {
    				
    				
    				String array1[]=eForm.getColonyId().split("\\~");
    				colonyId=array1[0];
    				
    			
    				
    				param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, areaID,subAreaID,wardId,colonyId,propertyId};
    				
    				
    				
    			}
           	
           	}
            	}
            	else{
            		
            		if(subAreaID!=null && !subAreaID.equalsIgnoreCase("-1") && !subAreaID.equalsIgnoreCase("")  ) {
               			
               			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID,subAreaID,propertyId};	
               			
               		}
                	
               		
               		if(eForm.getWardIds()!=null && !eForm.getWardIds().equalsIgnoreCase("-1")
            				&& !eForm.getWardIds().equalsIgnoreCase("")){
               			String[] array = eForm.getWardIds().split("\\~");
            			wardId=array[0];
            			
               			param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt,areaID,subAreaID,wardId,propertyId};
               		}
               		
               		if(eForm.getColonyId()!=null && !eForm.getColonyId().equalsIgnoreCase("-1")
        					&& !eForm.getColonyId().equalsIgnoreCase("")) {
        				
        				
        				String array1[]=eForm.getColonyId().split("\\~");
        				colonyId=array1[0];
        				
        			
        				
        				param=new String[]{zoneID, distID,tehsilID,deedID,instID, frmDt, toDt, areaID,subAreaID,wardId,colonyId,propertyId};
        				
        				
        				
        			}
            		
            	}
            	
            		}            	
    
            	
            	
            
          //  str1.append(CommonSQL.INSPECTION_MAIN2);
            str1.append(CommonSQL.INSPECTION_MAIN2_mview);
            
            str1.append(CommonSQL.INSPECTION_END);
            
            
            dbutility.createPreparedStatement(str1.toString());
			 list = dbutility.executeQuery(param);
            
			 
			
            
            
           
          } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}else {
			
			try {
				dbutility = new DBUtility();
				
				String frmDt = eForm.getDurFrom()+startTime;
				
				String toDt = eForm.getDurTo()+endTime;
			
				//String frmDt = eForm.getDurFrom();
			//	String toDt = eForm.getDurTo();
				String zoneID=eForm.getZoneId();
				String distID = eForm.getDistrictID();
				String tehsilID=eForm.getTehsilId();
				String deedID=eForm.getDeedID();
	            String instID=eForm.getInstID();
	         
	            String sro= eForm.getOfficeId();
	            String sr=eForm.getSrId();
	           
	          if(sr!=null &&  !sr.equalsIgnoreCase("-1")){
	        	  
	        	  // dbutility.createPreparedStatement(CommonSQL.INSPECTION_REPORT_SRO); // query 2 for mviews
	        	   
	        	   dbutility.createPreparedStatement(CommonSQL.INSPECTION_REPORT_SRO_mview);  
			    list = dbutility.executeQuery(new String[] { zoneID,distID,tehsilID,deedID,instID,sro,sr, frmDt, toDt });
			
	          }
	          else {
	        	  //  dbutility.createPreparedStatement(CommonSQL.INSPECTION_REPORT_SRO_ONLY); // query 1 for mview
	        	    
	        	    dbutility.createPreparedStatement(CommonSQL.INSPECTION_REPORT_SRO_ONLY_mview);  
					list = dbutility.executeQuery(new String[] { zoneID,distID,tehsilID,deedID,instID,sro, frmDt, toDt });
		
	 	  
	          }
	           
				
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					dbutility.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		}
		
			
			
		return list;
	}

	
	
	public ArrayList valuatePropSubtupe(ArrayList propList,String propertyId){
	
		ArrayList <String>list1=new ArrayList<String>(); // added by Siddhartha
		
		list1.add("540201600011187");
		list1.add("540201600011193");
		list1.add("540201600011197");
		String propid=null;
		String valTxnId=null;
		String totalArea=null;
		String residentialArea=null;
		String commercialArea=null;
		String industrialArea=null;
		String educationArea=null;
		String healthArea=null;
		String otherArea=null;
		String resiCommerce=null;
		int add=0;
		
		ArrayList areaDetls=new ArrayList();
		int flag=0;
	
		try {
			dbutility = new DBUtility();
			
			for ( int i=0;i<list1.size(); i++){
				
				propid=list1.get(i);
				dbutility.createPreparedStatement("select val_txn_id from igrs_reg_proprty_dtls where PROPERTY_ID = ? ");
				valTxnId=dbutility.executeQry(propid);
				
				
				if(propertyId.equals(1)){
				dbutility.createPreparedStatement("select TOTAL_AREA,RESIDENTIAL_AREA,COMMERCIAL_AREA,INDUSTRIAL_AREA,EDUCATION_AREA,"
                                  +" HEALTH_AREA, other_area from igrs_prop_plot_detls where val_txn_id=? ");
				
				areaDetls=dbutility.executeQuery(valTxnId);
				
				totalArea=(String) areaDetls.get(0);
				residentialArea=(String)areaDetls.get(1);
				commercialArea=(String)areaDetls.get(2);
				industrialArea=(String)areaDetls.get(3);
				educationArea=(String)areaDetls.get(4);
				healthArea=(String)areaDetls.get(5);
				otherArea=(String)areaDetls.get(6);
				add=Integer.parseInt(residentialArea)+Integer.parseInt(commercialArea);
				resiCommerce=Integer.toString(add);
			
				//String.valueOf(intVarable)
				
				if(totalArea.equals(residentialArea))
					flag=1;
				else if(totalArea.equals(commercialArea))
					flag=2;
				else if(totalArea.equals(industrialArea))
					flag=3;
				else if(totalArea.equals(educationArea))
					flag=4;
				else if(totalArea.equals(healthArea))
					flag=5;
				else if(totalArea.equals(otherArea))
					flag=6;
				else if (totalArea.equals(resiCommerce))
					flag=7;}
				
				else if (propertyId.equals(2)){
					

					dbutility.createPreparedStatement("select TOTAL_AREA,RESIDENTIAL_AREA,COMMERCIAL_AREA,INDUSTRIAL_AREA,EDUCATION_AREA,"
	                                  +" HEALTH_AREA, other_area from igrs_prop_plot_detls where val_txn_id=? ");
					
					areaDetls=dbutility.executeQuery(valTxnId);
					
					totalArea=(String) areaDetls.get(0);
					residentialArea=(String)areaDetls.get(1);
					commercialArea=(String)areaDetls.get(2);
					industrialArea=(String)areaDetls.get(3);
					educationArea=(String)areaDetls.get(4);
					healthArea=(String)areaDetls.get(5);
					otherArea=(String)areaDetls.get(6);
					add=Integer.parseInt(residentialArea)+Integer.parseInt(commercialArea);
					resiCommerce=Integer.toString(add);
				
					//String.valueOf(intVarable)
					
					if(totalArea.equals(residentialArea))
						flag=1;
					else if(totalArea.equals(commercialArea))
						flag=2;
					else if(totalArea.equals(industrialArea))
						flag=3;
					else if(totalArea.equals(educationArea))
						flag=4;
					else if(totalArea.equals(healthArea))
						flag=5;
					else if(totalArea.equals(otherArea))
						flag=6;
					else if (totalArea.equals(resiCommerce))
						flag=7;
					
				}
				
				else{
					

					dbutility.createPreparedStatement("select TOTAL_AREA,RESIDENTIAL_AREA,COMMERCIAL_AREA,INDUSTRIAL_AREA,EDUCATION_AREA,"
	                                  +" HEALTH_AREA, other_area from igrs_prop_plot_detls where val_txn_id=? ");
					
					areaDetls=dbutility.executeQuery(valTxnId);
					
					totalArea=(String) areaDetls.get(0);
					residentialArea=(String)areaDetls.get(1);
					commercialArea=(String)areaDetls.get(2);
					industrialArea=(String)areaDetls.get(3);
					educationArea=(String)areaDetls.get(4);
					healthArea=(String)areaDetls.get(5);
					otherArea=(String)areaDetls.get(6);
					add=Integer.parseInt(residentialArea)+Integer.parseInt(commercialArea);
					resiCommerce=Integer.toString(add);
				
					//String.valueOf(intVarable)
					
					if(totalArea.equals(residentialArea))
						flag=1;
					else if(totalArea.equals(commercialArea))
						flag=2;
					else if(totalArea.equals(industrialArea))
						flag=3;
					else if(totalArea.equals(educationArea))
						flag=4;
					else if(totalArea.equals(healthArea))
						flag=5;
					else if(totalArea.equals(otherArea))
						flag=6;
					else if (totalArea.equals(resiCommerce))
						flag=7;
				}
				
			}
			
		}catch (Exception e){
			
			e.printStackTrace();
		}finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return propList;
		
	}
	
	
	
	
	
	
	
	public ArrayList getEstampReport(MISReportForm eForm) {
		ArrayList list = new ArrayList();
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			//String frmDt = eForm.getFromDate();
		//	String toDt = eForm.getToDate();
			
			String frmDt = eForm.getFromDate()+" 00:00:00"; //Added By akansha : " 00:00:00"
			String toDt = eForm.getToDate() +" 23:59:59"; //Added By akansha : " 23:59:59"
			String distID = eForm.getReportDTO().getDistrictID();
			String deedID = eForm.getReportDTO().getDeedID();
			String param[] = null;
			// String sql = CommonSQL.REGISTRATION_REPORT;
			//StringBuilder str = new StringBuilder(CommonSQL.ESTAMP_REPORT);
			//StringBuilder str = new StringBuilder(CommonSQL.ESTAMP_REPORT_UPDATED);// by akansha
			StringBuilder str = new StringBuilder(CommonSQL.ESTAMP_REPORT_UPDATED_mview);// by rustam
			
			// String param[] = {fromYear, toYear,prvFromYear,prvToYear};
			param = new String[] { frmDt, toDt, distID };
			logger.debug("sql query for Estamp Report====" + str);
			if (!deedID.equalsIgnoreCase("")) {
				//str.append(CommonSQL.ESTAMP_REPORT_DEED);
				str.append(CommonSQL.ESTAMP_REPORT_DEED_mview);//by rustam

				param = new String[] { frmDt, toDt, distID, deedID };
			}
			//str.append(CommonSQL.ESTAMP_REPORT_END);
			str.append(CommonSQL.ESTAMP_REPORT_END_mview);//by rustam
			logger.info("Final build query : " + str.toString());
			dbutility.createPreparedStatement(str.toString());
			list = dbutility.executeQuery(param);

			// to take the sum
			StringBuilder sumQuery = new StringBuilder();
			//sumQuery.append(CommonSQL.ESTAMP_REPORT_SUM);
			sumQuery.append(CommonSQL.ESTAMP_REPORT_SUM_UPDATED);
			
			sumQuery.append(str.toString());
			sumQuery.append(CommonSQL.ESTAMP_REPORT_SUM_END);
			dbutility.createPreparedStatement(sumQuery.toString());
			ArrayList totList = dbutility.executeQuery(param);
			ArrayList sumList = (ArrayList) totList.get(0);
			eForm.getReportDTO().setTotDistrict(sumList.get(1).toString());
			// eForm.getReportDTO().setTotRegDate(sumList.get(2).toString());
			// eForm.getReportDTO().setTotRegNo(sumList.get(2).toString());
			eForm.getReportDTO().setTotDeed(sumList.get(2).toString());
			eForm.getReportDTO().setTotStamp(sumList.get(3).toString());
			eForm.getReportDTO().setTotGram(sumList.get(4).toString());
			/*
			 * eForm.getReportDTO().setTotNagar(sumList.get(6).toString());
			 * eForm.getReportDTO().setTotUpkar(sumList.get(7).toString());
			 * eForm.getReportDTO().setTotDuty(sumList.get(8).toString());
			 * eForm.getReportDTO().setTotRegFee(sumList.get(9).toString());
			 * eForm
			 * .getReportDTO().setTotRegDutySum(sumList.get(12).toString());
			 * eForm
			 * .getReportDTO().setTotTehsilName(sumList.get(13).toString());
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getEstampReportDrill(MISReportForm eForm, String distID,
			String deed, String uID) {
		ArrayList list = new ArrayList();
		String deedID = "";
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
		//	String frmDt = eForm.getFromDate();
			//String toDt = eForm.getToDate();
			
			String frmDt = eForm.getFromDate()+" 00:00:00"; //Added By akansha : " 00:00:00"
			String toDt = eForm.getToDate() +" 23:59:59"; //Added By akansha : " 23:59:59"
			if (deed == null)
				deedID = eForm.getReportDTO().getDrillDeedID();
			else
				deedID = deed;
			String param[] = null;
			//StringBuilder str = new StringBuilder(CommonSQL.ESTAMP_REPORT_I);
			
			//StringBuilder str = new StringBuilder(CommonSQL.ESTAMP_REPORT_1_updated);
			StringBuilder str = new StringBuilder(CommonSQL.ESTAMP_REPORT_1_updated_mview);// by rustam for mview
			// String param[] = {fromYear, toYear,prvFromYear,prvToYear};
			param = new String[] { frmDt, toDt, distID, deedID };
			logger.debug("sql query for Estamp Report1====" + str);

			/*str.append(CommonSQL.ESTAMP_REPORT_I_END);*/
			str.append(CommonSQL.ESTAMP_REPORT_I_END_mview);//by rustam for mview
			logger.info("Final build query : " + str.toString());
			dbutility.createPreparedStatement(str.toString());
			list = dbutility.executeQuery(param);

			// to take the sum
			StringBuilder sumQuery = new StringBuilder();
			sumQuery.append(CommonSQL.ESTAMP_REPORT_SUM_I);
			sumQuery.append(str.toString());
			sumQuery.append(CommonSQL.ESTAMP_REPORT_SUM_END_I);
			dbutility.createPreparedStatement(sumQuery.toString());
			ArrayList totList = dbutility.executeQuery(param);
			ArrayList sumList = (ArrayList) totList.get(0);
			eForm.getReportDTO().setTotspLicSumm(sumList.get(0).toString());
			eForm.getReportDTO().setTotdeed1(sumList.get(1).toString());
			eForm.getReportDTO().setTotcount1(sumList.get(2).toString());
			eForm.getReportDTO().setTotamount1(sumList.get(3).toString());
			eForm.getReportDTO().setTotcomm1(sumList.get(4).toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getEstampReportDrill1(MISReportForm eForm, String distID,
			String deed, String uID) {
		ArrayList list = new ArrayList();
		String deedID = "";
		// String param[]=new String[32];
		try {
			dbutility = new DBUtility();
			//String frmDt = eForm.getFromDate();
		//	String toDt = eForm.getToDate();
			String frmDt = eForm.getFromDate()+" 00:00:00"; //Added By akansha : " 00:00:00"
			String toDt = eForm.getToDate() +" 23:59:59"; //Added By akansha : " 23:59:59"
			if (deed == null)
				deedID = eForm.getReportDTO().getDeedID();
			else
				deedID = deed;
			String param[] = null;
			//StringBuilder str = new StringBuilder(CommonSQL.ESTAMP_REPORT_II);
			//StringBuilder str = new StringBuilder(CommonSQL.ESTAMP_REPORT_II_updated);
			StringBuilder str = new StringBuilder(CommonSQL.ESTAMP_REPORT_II_updated_mview);// by rustam for mview
			// String param[] = {fromYear, toYear,prvFromYear,prvToYear};
			param = new String[] { frmDt, toDt, distID, deedID, uID };
			logger.debug("sql query for Estamp Report2====" + str);

			// str.append(CommonSQL.ESTAMP_REPORT_I_END);
			logger.info("Final build query : " + str.toString());
			dbutility.createPreparedStatement(str.toString());
			list = dbutility.executeQuery(param);

			// to take the sum
			StringBuilder sumQuery = new StringBuilder();
			sumQuery.append(CommonSQL.ESTAMP_REPORT_SUM_II);
			sumQuery.append(str.toString());
			sumQuery.append(CommonSQL.ESTAMP_REPORT_SUM_END_II);
			dbutility.createPreparedStatement(sumQuery.toString());
			ArrayList totList = dbutility.executeQuery(param);
			ArrayList sumList = (ArrayList) totList.get(0);
			eForm.getReportDTO().setTotestamp(sumList.get(0).toString());
			eForm.getReportDTO().setTotamount2(sumList.get(1).toString());
			eForm.getReportDTO().setTotcomm2(sumList.get(2).toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public String getOfficeType(String officeId) {

		String getOfficeType = null;
		String SQL = null;

		try {
			String param[] = new String[1];
			param[0] = officeId;

			dbutility = new DBUtility();
			dbutility.createPreparedStatement(CommonSQL.OFFICE_TYPE);

			getOfficeType = dbutility.executeQry(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return getOfficeType;
	}

	public ArrayList getAllTehsil(String distId) {

		ArrayList list = new ArrayList();

		String param[] = new String[1];
		param[0] = distId;

		try {
			dbutility = new DBUtility();
			dbutility.createPreparedStatement(CommonSQL.TEHSIL_LIST);
			list = dbutility.executeQuery(param);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;

	}

	public String getDistrictId(String officeId) {
		// TODO Auto-generated method stub

		String getDistId = null;
		String SQL = null;

		try {
			String param[] = new String[1];
			param[0] = officeId;

			dbutility = new DBUtility();
			dbutility.createPreparedStatement(CommonSQL.DIST_ID);

			getDistId = dbutility.executeQry(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return getDistId;
	}

	public String getType(String officeType) {
		// TODO Auto-generated method stub

		String officer = null;
		String SQL = null;

		try {
			String param[] = new String[1];
			param[0] = officeType;

			dbutility = new DBUtility();
			dbutility.createPreparedStatement(CommonSQL.OFFICER);

			officer = dbutility.executeQry(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return officer;
	}

	public ArrayList getDistListDAO(String officeId) {

		ArrayList list = new ArrayList();
		String param[] = new String[1];
		param[0] = officeId;
		String SQL = CommonSQL.INSP_GET_DISTRICT_ZONES;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
			// logger.info("Wipro in SpotInspDAO - getDROList() AFTER EXCUTIN QUERY list values"
			// +list);
		} catch (Exception e) {
			// logger.error("exception in calling at SpotInspDAO Class at getDROList()  "
			// +e);
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

	public String getDIGZoneDAO(String officeId) {
		String a = null;
		DBUtility dbUtil = null;
		try {
			String param[] = new String[1];
			param[0] = officeId;
			dbUtil = new DBUtility();
			String SQL = CommonSQL.INSP_GET_ZONE_DIG;
			// logger.debug("before getting Zone details ");
			dbUtil.createPreparedStatement(SQL);
			// logger.debug("SQL:"+SQL);
			ArrayList data = dbUtil.executeQuery(param);
			ArrayList data1 = (ArrayList) data.get(0);
			a = (String) data1.get(0);
			// logger.debug("value of  Zone_Id) :- " + a);
		} catch (Exception e) {
			e.printStackTrace();
			// logger.debug("Exception in getZoneDetails() :- " + e);
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

	public ArrayList getCurrentRangeList() {

		ArrayList list = new ArrayList();
		String SQL = CommonSQL.INSP_CURRENT_RANGE_LIST;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger
					.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values"
							+ list);
		} catch (Exception e) {
			logger
					.error("exception in calling at SpotInspDAO Class at getSroList()  "
							+ e);
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

	public ArrayList getCurrentSubClauseList1(String language) {

		DBUtility dbUtil = null;
		ArrayList list = new ArrayList();
		String SQL = null;
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.INSP_CURRENT_SUB_CLAUSE_LIST;
		else
			SQL = CommonSQL.INSP_CURRENT_SUB_CLAUSE_LIST_HINDI;

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			// logger.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values"
			// +list);
		} catch (Exception e) {
			logger
					.error("exception in calling at SpotInspDAO Class at getSroList()  "
							+ e);
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

	public ArrayList getcurrentdeedInstrumentList(String language) {

		ArrayList list = new ArrayList();
		String SQL = null;
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.INSP_CURRENT_DEED_INSTRUMENT_LIST;
		else
			SQL = CommonSQL.INSP_CURRENT_DEED_INSTRUMENT_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger
					.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values"
							+ list);
		} catch (Exception e) {
			logger
					.error("exception in calling at SpotInspDAO Class at getSroList()  "
							+ e);
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

	public ArrayList getCurrentpropertyList(String language) {

		ArrayList list = new ArrayList();

		String SQL = null;
		if (language.equalsIgnoreCase("en"))
			SQL = CommonSQL.INSP_CURRENT_PROPERTY_LIST;
		else
			SQL = CommonSQL.INSP_CURRENT_PROPERTY_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger
					.info("Wipro in SpotInspDAO - getSroList() AFTER EXCUTIN QUERY list values"
							+ list);
		} catch (Exception e) {
			logger
					.error("exception in calling at SpotInspDAO Class at getSroList()  "
							+ e);
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

	public String getSelectPropertyName(String propId, String language) {

		String[] propIds = propId.split("~");
		DBUtility dbUtil = null;
		if (propIds.length == 1) {
			try {
				dbUtil = new DBUtility();
				if (language.equalsIgnoreCase("en"))
					dbUtil
							.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_SINGLE);
				else
					dbUtil
							.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_SINGLE_HI);
				String name = dbUtil.executeQry(new String[] { propId });
				dbUtil.closeConnection();
				return name;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				dbUtil = new DBUtility();
				if (language.equalsIgnoreCase("en"))
					dbUtil
							.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_THREE);
				else
					dbUtil
							.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_THREE_HI);
				String name = dbUtil.executeQry(new String[] { propIds[1],
						propIds[0], propIds[2] });
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

	public ArrayList getTehsil(String districtId, String language) {
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
			if (districtId != null) {
				arry[0] = districtId;
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);

			} else
				return null;
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
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

	public ArrayList getAreaType(String language) {

		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			String sql = "";
			if (language.equalsIgnoreCase("en"))
				sql = CommonSQL.INSP_AREA_TYPE_LIST;
			else
				sql = CommonSQL.INSP_AREA_TYPE_LIST_HINDI;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);

			// logger.info("SpotInspDAO----getAreaType  "+list);
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

	public ArrayList getZone(String stateId, String language) {

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
				dbUtil.createPreparedStatement(CommonSQL.INSP_ZONE_QUERY);
			else
				dbUtil.createPreparedStatement(CommonSQL.INSP_ZONE_QUERY_HINDI);

			list = dbUtil.executeQuery(arry);
			logger.debug("After initialize DBUtility");
			if (logger.isDebugEnabled()) {
				logger.debug("getDistrict(String) - end");
			}
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
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

	public ArrayList getDistDIGListDAO(String zoneId,String language) {

		ArrayList list = new ArrayList();
		String param[] = new String[1];
		param[0] = zoneId;
		String SQL = null;
		if (language.equalsIgnoreCase("en"))
		SQL = CommonSQL.INSP_GET_DISTRICT_ZONE_LIST;
		else 
			SQL=CommonSQL.INSP_GET_DISTRICT_ZONE_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
			logger
					.info("Wipro in SpotInspDAO - getDIGList() AFTER EXCUTIN QUERY list values"
							+ list);
		} catch (Exception e) {
			logger
					.error("exception in calling at SpotInspDAO Class at getDIGList()  "
							+ e);
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

	public ArrayList getPropertyL2(String propertyId, String language) {

		String SQL = null;
		ArrayList list = new ArrayList();
		String param[] = new String[1];
		param[0] = propertyId;
		DBUtility dbUtil = null;
		if(propertyId.equalsIgnoreCase("1")){
			
			if (language.equalsIgnoreCase("en")) {
				SQL = CommonSQL.INSP_PROPERTYID1_SUB_TYPE__DETAILS;
			} else
				SQL = CommonSQL.INSP_PROPERTYID1_SUB__DETAILS_HINDI;
			
		}
		else
		{
		if (language.equalsIgnoreCase("en")) {
			SQL = CommonSQL.INSP_PROPERTY_SUB_TYPE__DETAILS;
		} else
			SQL = CommonSQL.INSP_PROPERTY_SUB__DETAILS_HINDI;
		}
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
			logger
					.info("Wipro in ReportDAO  for Inspection Report - getProptertyL2List() AFTER EXCUTIN QUERY list values"
							+ list);
		} catch (Exception e) {
			logger
					.error("exception in calling at ReportDAO for Inspection Report Class at getProptertyL2List()  "
							+ e);
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
				SQL = CommonSQL.INSP_GET_DISTRICT_ZONE_LIST;
			else
				SQL = CommonSQL.INSP_GET_DISTRICT_ZONE_LIST_HINDI;
			logger.debug("before getting Zone details ");
			dbUtil.createPreparedStatement(SQL);
			logger.debug("SQL:" + SQL);
			data = dbUtil.executeQuery(param);

			// logger.debug("value of  Zone_Id) :- " + data.size());
		} catch (Exception e) {
			e.printStackTrace();
			// logger.debug("Exception in getDistrictZoneDetails() :- " + e);
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

	public ArrayList getSroListInsp(String tehsilId, String language) {

		ArrayList data = null;
		DBUtility dbUtil = null;

		try {
			String SQL = "";
			String param[] = new String[1];
			param[0] = tehsilId;
			dbUtil = new DBUtility();
			if (language.equalsIgnoreCase("en"))
				SQL = CommonSQL.INSP_GET_SRO_LIST;
			else
				SQL = CommonSQL.INSP_GET_SRO_LIST_HINDI;
			logger.debug("before getting Zone details ");
			dbUtil.createPreparedStatement(SQL);
			logger.debug("SQL:" + SQL);
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

		// logger.debug("value of  Zone_Id) :- " + data.size());

		return data;

	}

	public ArrayList getSrListInsp(String officeId) {

		ArrayList data = null;
		DBUtility dbUtil = null;

		try {
			String SQL = "";
			String param[] = new String[2];
			param[0] = officeId;
			param[1] = officeId;
			dbUtil = new DBUtility();

			SQL = CommonSQL.INSP_GET_SR_LIST;

			// logger.debug("before getting Zone details ");
			dbUtil.createPreparedStatement(SQL);
			logger.debug("SQL:" + SQL);
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

		// logger.debug("value of  Zone_Id) :- " + data.size());

		return data;

	}

	public ArrayList getAllDeedList(String language) {

		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			String sql = "";
			if (language.equalsIgnoreCase("en"))
				sql = CommonSQL.INSP_DEED_LIST;
			else
				sql = CommonSQL.INSP_DEED_LIST_HINDI;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);

			// logger.info("SpotInspDAO----getAreaType  "+list);
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

	public ArrayList getInstrument(String deedID, String language) {

		ArrayList data = null;
		DBUtility dbUtil = null;

		try {
			String SQL = "";
			String param[] = new String[1];
			param[0] = deedID;
			dbUtil = new DBUtility();
			if (language.equalsIgnoreCase("en"))
				SQL = CommonSQL.INSP_GET_INSTRU_LIST;
			else
				SQL = CommonSQL.INSP_GET_INSTRU_LIST_HINDI;
			logger.debug("before getting Zone details ");
			dbUtil.createPreparedStatement(SQL);
			logger.debug("SQL:" + SQL);
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

		// logger.debug("value of  Zone_Id) :- " + data.size());

		return data;

	}

	public ArrayList getAllpropertyList(String language) {

		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			String sql = "";
			if (language.equalsIgnoreCase("en"))
				sql = CommonSQL.INSP_PROP_LIST;
			else
				sql = CommonSQL.INSP_PROP_LIST_HINDI;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);

			// logger.info("SpotInspDAO----getAreaType  "+list);
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

	public boolean validateReportInsp(String durFrom, String durTo) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION_INSP;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { durTo,durFrom  });
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
				boo = true;
			else
				boo = false;
			
		} catch (Exception e) {
			logger.debug("Exception");
			// TODO: handle exception
		} finally {
			try {
				
				dbUtil.closeConnection();
				if (!dbUtil.isClosed())
					dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boo;
	}
	
	public String validateReportInspDay(String durFrom, String durTo) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION_INSP;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;
		String day ="";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			 day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			String dayDiff = dbUtil
					.executeQry(new String[] { durTo,durFrom  });
		
			
		} catch (Exception e) {
			logger.debug("Exception");
			// TODO: handle exception
		} finally {
			try {
				
				dbUtil.closeConnection();
				if (!dbUtil.isClosed())
					dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return day;
	}
	public ArrayList gettehsil(String districtID,String language) {
		String thsl = districtID;
		String SQL = null;
		String lang=language;
		ArrayList tehsillst=new ArrayList();

		try {
			String param[] = new String[1];
			param[0] = thsl;
if(lang.equalsIgnoreCase("en")){
			dbutility = new DBUtility();
			dbutility.createPreparedStatement(CommonSQL.Tehsil);
}
else
{
	dbutility = new DBUtility();
	dbutility.createPreparedStatement(CommonSQL.Tehsil_HINDI);
	}

			tehsillst = dbutility.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return tehsillst;
	}
	
	public ArrayList getoffice(String tehsilId,String language) {
		String thsl = tehsilId;
		String SQL = null;
		String lang=language;
		ArrayList officeList=new ArrayList();

		try {
			String param[] = new String[1];
			param[0] = thsl;
if(lang.equalsIgnoreCase("en")){
			dbutility = new DBUtility();
			dbutility.createPreparedStatement(CommonSQL.Office);
}
else {
	dbutility = new DBUtility();
	dbutility.createPreparedStatement(CommonSQL.Office_HINDI);
}
			officeList = dbutility.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return officeList;
	}

	public ArrayList getSRS(String officesId,String language,String officeId,String userId) {
		String offc = officesId;
		String SQL = null;
		String lang=language;
		ArrayList SRList=new ArrayList();

		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.GET_OFFICE_TYPE_ID;
			dbutility.createPreparedStatement(sql);
			String officeTypeID = dbutility.executeQry(new String[] { officeId });

			String param[] = new String[1];
		
			if (officeTypeID.equalsIgnoreCase("3"))
			{
				param[0] = userId;
				dbutility.createPreparedStatement(CommonSQL.SRS_Hindi);
			}else{
				param[0] = offc;
				dbutility.createPreparedStatement(CommonSQL.SRS);
				
			}
		
			SRList = dbutility.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return SRList;
	}
	public ArrayList getSroList() {
		logger.debug("in getSroList dao block start");
		ArrayList list = new ArrayList();
		try {

			dbutility = new DBUtility();
			String sql = CommonSQL.REV_RCPT_REPORT_SRO;
			logger.debug("sql query====" + sql);
			dbutility.createStatement();
			list = dbutility.executeQuery(sql);
		} catch (Exception err) {

			logger.error("this is Exception in " + "getSroList DAO: " + err);
		}
		return list;
	}
	
	public ArrayList getOffice(String tehsilId,String language,String officeID) {
		String thsl = tehsilId;
		String SQL = null;
		String lang=language;
		ArrayList officeList=new ArrayList();

		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.GET_OFFICE_TYPE_ID;
			dbutility.createPreparedStatement(sql);
			String officeTypeID = dbutility.executeQry(new String[] { officeID });
			//String distQuery = "";
			String param[] = new String[1];
			
			// 4 is DIG and 2 is DR and 3 is SR
			if (officeTypeID.equalsIgnoreCase("3"))
			{
				String param1[] = new String[2];
				param1[0] = officeID;
				param1[1] = thsl;
				if(lang.equalsIgnoreCase("en")){
						dbutility.createPreparedStatement(CommonSQL.Office_sr);
						}
						else {
						dbutility.createPreparedStatement(CommonSQL.Office_sr_HINDI);
						}
				officeList = dbutility.executeQuery(param1);
			}else{
				param[0] = thsl;
			if(lang.equalsIgnoreCase("en")){
		//	dbutility = new DBUtility();
			dbutility.createPreparedStatement(CommonSQL.Office);
			}
			else {
	//dbutility = new DBUtility();
			dbutility.createPreparedStatement(CommonSQL.Office_HINDI);
			}
			officeList = dbutility.executeQuery(param);
		}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return officeList;
	}
	
	public ArrayList getYears()
	{
		ArrayList yearList = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.GET_YERAS;
			logger.debug("Query "+sql);
			dbutility.createStatement();
			yearList = dbutility.executeQuery(sql);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return yearList;
		
	}
	
	public ArrayList getFinYears()
	{
		ArrayList yearList = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.GET_FIN_YERAS;
			logger.debug("Query "+sql);
			dbutility.createStatement();
			yearList = dbutility.executeQuery(sql);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return yearList;
		
	}
}
