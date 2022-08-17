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
 * File Name	   		: ReportBO.java
 *
 * Description	   		: This class interacts with DbService for data 
 * 							persistance and data fetch
 *
 * Version Number  		: 0.0 
 *
 * Created Date	   		: 09 06 2008 
 *
 * Modification History	: Created
 */

package com.wipro.igrs.report.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.report.dto.ReportDTO;
import com.wipro.igrs.spotInsp.dao.SpotInspDAO;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;
import com.wipro.igrs.suppleDoc.dao.SuppleDocDAO;

public class ReportBO {

	/**
	 * ========================================================================
	 * === Method : getDistrictDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Sreelatha M Created Date : Apr 30, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	private IGRSCommon common;
	private Logger logger = (Logger) Logger.getLogger(ReportBO.class);

	public ArrayList getDistrictDetails() {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getDistrictDetails BD start");
			ArrayList resultList = dao.getDistrictDetails();
			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportDTO reportDTO = new ReportDTO();
					reportDTO.setDistrictId((String) list.get(0));
					reportDTO.setDistrictName((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug("in bd for loop end,DIST.Id:-"
							+ reportDTO.getDistrictId());
					logger.debug("in bd for loop end,DIST.Location:-"
							+ reportDTO.getDistrictName());
				}
			}
			logger.debug("In getDistrictDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getDistrictDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getEffectFactorDetails() Description : Retrieving values
	 * from database Arguments : ReportForm reportForm return type : List Author
	 * : Sreelatha M Created Date : May 17,2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getEffectFactorDetails() {
		ReportDAO dao = new ReportDAO();
		// ArrayList resultList=new ArrayList();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getEffectFactorDetails BD start");
			ArrayList resultList = dao.getEffectFactorDetails();
			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportDTO reportDTO = new ReportDTO();
					reportDTO.setFactorName((String) list.get(0));
					String valIncDec = (String) list.get(1) + " "
							+ (String) list.get(2);
					reportDTO.setFacValIncDec(valIncDec);
					returnList.add(reportDTO);
					logger.debug("in bd for loop end,FactorName:-"
							+ reportDTO.getFactorName());
					logger.debug("in bd for loop end FactorInc  "
							+ reportDTO.getFacValIncDec());
				}
			}
			logger.debug("In getEffectFactorDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getEffectFactorDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getRegisterDocumentDetails() Description : Retrieving values
	 * from database Arguments : ReportForm reportForm return type : List Author
	 * : Sreelatha M Created Date : May 26,2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getRegisterDocumentDetails() {
		ReportDAO dao = new ReportDAO();
		// ArrayList resultList=new ArrayList();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getRegisterDocumentDetails BD start");
			ArrayList resultList = dao.getRegisterDocumentDetails();
			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportDTO reportDTO = new ReportDTO();
					reportDTO.setRegUserId((String) list.get(0));

					returnList.add(reportDTO);
					logger.debug("in bd for loop end,regUserId:-"
							+ reportDTO.getRegUserId());

				}
			}
			logger.debug("In getRegisterDocumentDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getRegisterDocumentDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getFiscalYearDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Sreelatha M Created Date : May 27,2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getFiscalYearDetails() {
		ReportDAO dao = new ReportDAO();
		// ArrayList resultList=new ArrayList();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getFiscalYearDetails BD start");
			ArrayList resultList = dao.getFiscalYearDetails();
			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportDTO reportDTO = new ReportDTO();
					String fisYrLabel = (String) list.get(0)
							+ (String) list.get(1);
					logger.debug(" fisYrLabel " + fisYrLabel);
					reportDTO.setFiscalId((String) list.get(0));
					reportDTO.setFiscalYear((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug("in bd for loop end,reportDTO.setFiscalYear:-"
							+ reportDTO.getFiscalYear());
					logger.debug("in bd for loop end reportDTO.setFiscalId  "
							+ reportDTO.getFiscalId());
				}
			}
			logger.debug("In getFiscalYearDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getFiscalYearDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getDroNameDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Sreelatha M Created Date : May 27,2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getDroNameDetails() {
		ReportDAO dao = new ReportDAO();
		// ArrayList resultList=new ArrayList();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getDroNameDetails BD start");
			ArrayList resultList = dao.getDroNameDetails();
			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportDTO reportDTO = new ReportDTO();
					reportDTO.setDroName((String) list.get(0));
					reportDTO.setDroId((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug("in bd for loop end,reportDTO.GetDroName:-"
							+ reportDTO.getDroName());
					logger.debug("in bd for loop end reportDTO.setDroId  "
							+ reportDTO.getDroId());
				}
			}
			logger.debug("In getDroNameDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getDroNameDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getSroNameDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Sreelatha M Created Date : Jun 07,2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getSroNameDetails() {
		ReportDAO dao = new ReportDAO();
		// ArrayList resultList=new ArrayList();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getSroNameDetails BD start");
			ArrayList resultList = dao.getSroNameDetails();
			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportDTO reportDTO = new ReportDTO();
					reportDTO.setSroName((String) list.get(0));
					reportDTO.setOfficeId((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug("in bd for loop end,reportDTO.getSroName:-"
							+ reportDTO.getSroName());
					logger.debug("in bd for loop end reportDTO.getSroId  "
							+ reportDTO.getOfficeId());
				}
			}
			logger.debug("In getSroNameDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getSroNameDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getSROwiseStampRegFeeDetails() Description : Retrieving
	 * values from database Arguments : ReportForm reportForm return type : List
	 * Author : Sreelatha M Created Date : May 06, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getSROwiseStampRegFeeDetails(ReportDTO _reportDTO) {
		ReportDAO dao = new ReportDAO();
		ReportDTO reportDTO = new ReportDTO();
		ArrayList returnList = new ArrayList();
		try {
			common = new IGRSCommon();
		} catch (Exception x) {
			logger.debug(" in Exception" + x);
		}
		try {
			logger.debug("in getSROwiseStampRegFeeDetails");
			ArrayList resultList = dao.getSROwiseStampRegFeeDetails(_reportDTO);
			logger.debug("resultlist size" + resultList.size());
			logger.debug("resultlist size" + resultList.size());
			int j = 1;
			double curMonStampDty = 0, curMonRegFee = 0, curMonTot = 0, preMonStampDty = 0, preMonRegFee = 0, preMonTot = 0, compStampDty = 0.0, compRegFee = 0.0, compTot = 0.0, curNoDocStampDty = 0, curNoDocRegFee = 0, preNoDocStampDty = 0, preNoDocRegFee = 0, curNoDocTot = 0, preNoDocTot = 0, compNoDocStampDty = 0.0, compNoDocRegFee = 0.0, compNoDocTot = 0.0;
			if (resultList != null && resultList.size() > 0) {
				logger.debug("in bd if resultList block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					// reportDTO = new ReportDTO();
					String sroName = (String) list.get(0);
					if ((list.get(1)).toString().trim().length() != 0) {
						curMonStampDty = Double.parseDouble((String) list
								.get(1));
					}
					if (list.get(2) != null) {
						if ((list.get(2)).toString().trim().length() != 0) {
							curMonRegFee = Double.parseDouble((String) list
									.get(2));
							logger.debug("CurMonRegFee===" + curMonRegFee);
							System.out
									.println("CurMonRegFee===" + curMonRegFee);
						}
					}
					if ((list.get(3)).toString().trim().length() != 0) {
						curNoDocStampDty = Double.parseDouble((String) list
								.get(3));
						logger.debug("curNoDocStampDty===" + curNoDocStampDty);
						logger.debug("curNoDocStampDty===" + curNoDocStampDty);
					}
					if ((list.get(4)).toString().trim().length() != 0) {
						curNoDocRegFee = Double.parseDouble((String) list
								.get(4));
						logger.debug("curNoDocRegFee===" + curNoDocRegFee);
						System.out
								.println("curNoDocRegFee===" + curNoDocRegFee);
					}
					if ((list.get(5)).toString().trim().length() != 0) {
						preMonStampDty = Double.parseDouble((String) list
								.get(5));
						logger.debug("preMonStampDty===" + preMonStampDty);
						System.out
								.println("preMonStampDty===" + preMonStampDty);
					}
					if ((list.get(6)).toString().trim().length() != 0) {
						preMonRegFee = Double.parseDouble((String) list.get(6));
						logger.debug("preMonRegFee===" + preMonRegFee);
						logger.debug("preMonRegFee===" + preMonRegFee);
					}
					if ((list.get(7)).toString().trim().length() != 0) {
						preNoDocStampDty = Double.parseDouble((String) list
								.get(7));
						logger.debug("preNoDocStampDty===" + preNoDocStampDty);
						logger.debug("preNoDocStampDty===" + preNoDocStampDty);
					}
					if ((list.get(8)).toString().trim().length() != 0) {
						preNoDocRegFee = Double.parseDouble((String) list
								.get(8));
						logger.debug("preNoDocRegFee===" + preNoDocRegFee);
						System.out
								.println("preNoDocRegFee===" + preNoDocRegFee);
					}

					curMonTot = curMonStampDty + curMonRegFee;
					preMonTot = preMonStampDty + preMonRegFee;
					curNoDocTot = curNoDocStampDty + curNoDocRegFee;
					preNoDocTot = preNoDocStampDty + preNoDocRegFee;

					if (preMonStampDty != 0) {
						compStampDty = ((curMonStampDty - preMonStampDty) / (preMonStampDty)) * 100;
					} else {
						compStampDty = curMonStampDty * 100;
					}
					if (preMonRegFee != 0) {
						compRegFee = ((curMonRegFee - preMonRegFee) / (preMonRegFee)) * 100;
					} else {
						compRegFee = curMonRegFee * 100;
					}
					if (preNoDocStampDty != 0) {
						compNoDocStampDty = ((curNoDocStampDty - preNoDocStampDty) / (preNoDocStampDty)) * 100;
					} else {
						compNoDocStampDty = curNoDocStampDty * 100;
					}
					if (preNoDocRegFee != 0) {
						compNoDocRegFee = ((curNoDocRegFee - preNoDocRegFee) / (preNoDocRegFee)) * 100;
					} else {
						compNoDocRegFee = curNoDocRegFee * 100;
					}

					compTot = compStampDty + compRegFee;
					compNoDocTot = compNoDocStampDty + compNoDocRegFee;
					reportDTO.setSno(j);
					reportDTO.setSroName(sroName);
					reportDTO.setCurMonStampDty(common
							.getCurrencyFormat(curMonStampDty));
					reportDTO.setCurMonRegFee(common
							.getCurrencyFormat(curMonRegFee));
					reportDTO.setCurMonTot(common.getCurrencyFormat(curMonTot));
					reportDTO.setPreMonStampDty(common
							.getCurrencyFormat(preMonStampDty));
					reportDTO.setPreMonRegFee(common
							.getCurrencyFormat(preMonRegFee));
					reportDTO.setPreMonTot(common.getCurrencyFormat(preMonTot));
					reportDTO.setCompStampDty(common
							.getCurrencyFormat(compStampDty));
					reportDTO.setCompRegFee(common
							.getCurrencyFormat(compRegFee));
					reportDTO.setCompTot(common.getCurrencyFormat(compTot));
					reportDTO.setCurNoDocStampDty(common
							.getCurrencyFormat(curNoDocStampDty));
					reportDTO.setCurNoDocRegFee(common
							.getCurrencyFormat(curNoDocRegFee));
					reportDTO.setCurNoDocTot(common
							.getCurrencyFormat(curNoDocTot));
					reportDTO.setPreNoDocStampDty(common
							.getCurrencyFormat(preNoDocStampDty));
					reportDTO.setPreNoDocRegFee(common
							.getCurrencyFormat(preNoDocRegFee));
					reportDTO.setPreNoDocTot(common
							.getCurrencyFormat(preNoDocTot));
					reportDTO.setCompNoDocStampDty(common
							.getCurrencyFormat(compNoDocStampDty));
					reportDTO.setCompNoDocRegFee(common
							.getCurrencyFormat(compNoDocRegFee));
					reportDTO.setCompNoDocTot(common
							.getCurrencyFormat(compNoDocTot));

					logger.debug(" sro name===" + reportDTO.getSroName());
					logger.debug("curMonStampDty==="
							+ reportDTO.getCurMonStampDty());
					logger.debug("CurMonRegFee==="
							+ reportDTO.getCurMonRegFee());
					logger.debug("curMonTot===" + reportDTO.getCurMonTot());
					j++;
				}
				logger.debug("in bd if resultList block end ");
				returnList.add(reportDTO);
			}
			logger.debug("In getSROwiseStampRegFeeDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getSROwiseStampRegFeeDetails Exception " + err);
			logger.debug("In getSROwiseStampRegFeeDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getRevenueProjectDetails() Description : Retrieving values
	 * from database Arguments : ReportForm reportForm return type : List Author
	 * : Sreelatha M Created Date : May 13, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getRevenueProjectDetails(ReportDTO _reportDTO)
			throws Exception {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		String facIncDec = _reportDTO.getFacValIncDec();
		logger.debug("in BD FactorIncDec " + facIncDec);
		logger.debug("facIncDec=========" + facIncDec);
		double facVal = 0;
		String effFacVal = "";
		String[] result = facIncDec == null ? null : facIncDec.split("\\s");
		logger.debug("FFF");
		try {
			common = new IGRSCommon();
		} catch (Exception x) {
			logger.debug(" in Exception" + x);
		}
		if (result != null) {
			for (int x = 0; x < result.length; x++)
				effFacVal = result[0];
		}
		logger.debug("effFacVal" + effFacVal);
		logger.debug("Float.parseFloat(result[0])==="
				+ Integer.parseInt(result[0]));
		facVal = ((Double.parseDouble(result[0])) / 100);
		logger.debug("val=======" + facVal);
		facIncDec = result[1];
		logger.debug("in BD facIncDec===" + facIncDec);
		try {
			logger.debug("in getRevenueProjectDetails BD");
			ArrayList resultList = dao.getRevenueProjectDetails(_reportDTO);
			ArrayList valNameList = (ArrayList) resultList.get(0);

			// ArrayList noOfDocPrevious = (ArrayList)resultList.get(1);
			logger.debug("size of ResultList========" + resultList.size());
			int j = 1;
			if (valNameList != null && valNameList.size() > 0) {
				logger.debug("in bd if block start");
				String preDocType = "";
				double preNoDoc = 0, preRevRec = 0, preMarketVal = 0, compNoDoc = 0, compRevRec = 0, compVal = 0;

				for (int i = 0; i < valNameList.size(); i++) {
					logger.debug("in bd for loop start:-" + valNameList.get(i));
					ReportDTO reportDTO = new ReportDTO();
					ArrayList list = (ArrayList) valNameList.get(i);
					reportDTO.setSno(j);
					String curDocType = (String) list.get(0);
					double curNoDoc = (Double.parseDouble((String) list.get(1)));
					double curRevRec = (Double
							.parseDouble((String) list.get(2)));
					double curMarketVal = (Double.parseDouble((String) list
							.get(3)));
					preDocType = (String) list.get(4);
					preNoDoc = (Double.parseDouble((String) list.get(5)));
					preRevRec = (Double.parseDouble((String) list.get(6)));
					preMarketVal = (Double.parseDouble((String) list.get(7)));

					reportDTO.setDocType(curDocType);
					reportDTO.setNoOfDoc((String) list.get(1));
					reportDTO
							.setRevReceipt(common.getCurrencyFormat(curRevRec));
					reportDTO.setValuation(common
							.getCurrencyFormat(curMarketVal));
					logger.debug("no of doc====" + reportDTO.getNoOfDoc() + ""
							+ "  and  doctype=========="
							+ reportDTO.getDocType());
					if (preDocType.equals(curDocType)) {
						if (preNoDoc != 0) {
							compNoDoc = ((curNoDoc - preNoDoc) / (preNoDoc)) * 100;
						} else {
							compNoDoc = curNoDoc * 100;
						}
						if (preRevRec != 0) {
							compRevRec = ((curRevRec - preRevRec) / (preRevRec)) * 100;
						} else {
							compRevRec = curRevRec * 100;
						}
						if (preMarketVal != 0) {
							compVal = ((curMarketVal - preMarketVal) / (preMarketVal)) * 100;
						} else {
							compVal = curMarketVal * 100;
						}
						reportDTO.setCompNoOfDoc(common
								.getCurrencyFormat(compNoDoc));
						reportDTO.setComprevReceipt(common
								.getCurrencyFormat(curRevRec));
						logger.debug(common.getCurrencyFormat(curRevRec));
						reportDTO.setCompValuation(common
								.getCurrencyFormat(compVal));
					} else {
						compNoDoc = curNoDoc * 100;
						compRevRec = curRevRec * 100;
						compVal = curMarketVal * 100;
						reportDTO.setCompNoOfDoc(common
								.getCurrencyFormat(compNoDoc));
						logger.debug(common.getCurrencyFormat(compRevRec));
						reportDTO.setComprevReceipt(common
								.getCurrencyFormat(compRevRec));
						reportDTO.setCompValuation(common
								.getCurrencyFormat(compVal));
					}
					if (facIncDec.equalsIgnoreCase("I")) {
						reportDTO.setEffFactorVal(effFacVal);
						double effFacDoc = curNoDoc + facVal;
						reportDTO.setEffFacDoc(common
								.getCurrencyFormat(effFacDoc));
						logger.debug("in BD effFacDoc " + effFacDoc);
						double effFacRevRec = curRevRec + facVal;
						reportDTO.setEffFacReceipt(common
								.getCurrencyFormat(effFacRevRec));
						logger.debug("in BD effFacRevRec " + effFacRevRec);
						double effFacValuation = curMarketVal + facVal;
						reportDTO.setEffFacValuation(common
								.getCurrencyFormat(effFacValuation));
						logger
								.debug("in BD effFacValuation "
										+ effFacValuation);
					} else {
						String effFactor = "";
						effFactor = "-" + effFacVal;
						reportDTO.setEffFactorVal(effFactor);
						double effFacDoc = curNoDoc - facVal;
						reportDTO.setEffFacDoc(common
								.getCurrencyFormat(effFacDoc));
						logger.debug("in BD else effFacDoc "
								+ common.getCurrencyFormat(effFacDoc));
						double effFacRevRec = curRevRec - facVal;
						reportDTO.setEffFacReceipt(common
								.getCurrencyFormat(effFacRevRec));
						logger.debug("in BD else effFacRevRec "
								+ common.getCurrencyFormat(effFacRevRec));
						double effFacValuation = curMarketVal - facVal;
						reportDTO.setEffFacValuation(common
								.getCurrencyFormat(effFacValuation));
						logger.debug("in BD else effFacValuation "
								+ common.getCurrencyFormat(effFacValuation));
					}

					j++;
					returnList.add(reportDTO);
				}
				logger.debug("in bd if block end ");
			}
			logger.debug("In getRevenueProjectDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getRevenueProjectDetails BD Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getSubwiseStampRegFeeDetails() Description : Retrieving
	 * values from database Arguments : ReportForm reportForm return type : List
	 * Author : Sreelatha M Created Date : May 19, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getSubwiseStampRegFeeDetails(ReportDTO _reportDTO) {
		ReportDAO dao = new ReportDAO();
		ReportDTO reportDTO = null;
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("in getSubwiseStampRegFeeDetails");
			ArrayList resultList = dao.getSubwiseStampRegFeeDetails(_reportDTO);
			logger.debug("resultList size==" + resultList.size());
			logger.debug("resultList==" + resultList);
			int j = 1;
			float exemRegFee = 0f, exemStampDtyTot = 0f, exemRegFeeTot = 0f, exemTotTot = 0f, aprExemRegFeeTot = 0f, aprExemTotTot = 0f, aprExemStampDtyTot = 0;
			int exemNoDocTot = 0, aprExemNoDocTot = 0;
			if (resultList != null && resultList.size() > 0) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd list1 for loop start:-"
							+ resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					reportDTO = new ReportDTO();
					reportDTO.setSno(j);
					reportDTO.setExemName((String) list.get(0));
					int exemNoDoc = Integer.parseInt((String) list.get(1));
					float exemStampDty = Float.parseFloat((String) list.get(2));
					if (list.get(3) != null) {
						if (list.get(3).toString().trim().length() != 0) {
							exemRegFee = Float.parseFloat((String) list.get(3));
						}
					}
					float exemTot = exemNoDoc + exemStampDty + exemRegFee;

					exemNoDocTot = exemNoDocTot + exemNoDoc;
					exemRegFeeTot = exemRegFeeTot + exemRegFee;
					exemStampDtyTot = exemStampDtyTot + exemStampDty;
					exemTotTot = exemTotTot + exemTot;
					logger.debug("exemNoDocTot=====" + exemNoDocTot);
					logger.debug("exemRegFeeTot=====" + exemRegFeeTot);
					System.out
							.println("exemStampDtyTot=====" + exemStampDtyTot);
					logger.debug("exemTotTot=====" + exemTotTot);

					reportDTO.setExemNoDoc(exemNoDoc);
					reportDTO.setExemStampDty(exemStampDty);
					reportDTO.setExemRegFee(exemRegFee);
					reportDTO.setExemTot(exemTot);
					j++;
					returnList.add(reportDTO);
				}
			}
			if (reportDTO != null) {
				reportDTO.setExemNoDocTot(exemNoDocTot);
				logger.debug("reportDTO.getExemNoDocTot"
						+ reportDTO.getExemNoDocTot());
				reportDTO.setExemStampDtyTot(exemStampDtyTot);
				logger.debug("reportDTO.getExemStampDtyTot()"
						+ reportDTO.getExemStampDtyTot());
				reportDTO.setExemRegFeeTot(exemRegFeeTot);
				logger.debug("reportDTO.getExemRegFeeTot()"
						+ reportDTO.getExemRegFeeTot());
				reportDTO.setExemTotTot(exemTotTot);
				logger.debug("reportDTO.getExemTotTot()"
						+ reportDTO.getExemTotTot());

				returnList.add(reportDTO);
			}
			logger.debug("In getSubwiseStampRegFeeDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getSubwiseStampRegFeeDetails BD Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getCompRevReceiptsDetails() Description : Retrieving values
	 * from database Arguments : ReportForm reportForm return type : List Author
	 * : Sreelatha M Created Date : May 19, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getCompRevReceiptsDetails(ReportDTO _reportDTO) {
		ReportDAO dao = new ReportDAO();
		ReportDTO reportDTO = new ReportDTO();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("in getCompRevReceiptsDetails");
			ArrayList resultList = dao.getCompRevReceiptsDetails(_reportDTO);
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();
			ArrayList list3 = new ArrayList();
			ArrayList list4 = new ArrayList();

			if (resultList != null) {
				logger.debug("resultList size==" + resultList.size());
				logger.debug("resultList==" + resultList);
				list1 = (ArrayList) resultList.get(0);
				logger.debug("list1" + list1);
				list2 = (ArrayList) resultList.get(1);
				logger.debug("list2" + list2);
				list3 = (ArrayList) resultList.get(2);
				logger.debug("list3" + list3);
				list4 = (ArrayList) resultList.get(3);
				logger.debug("list4" + list4);
			}
			String progMonth1 = "", progMonth = "";
			float progTarg = 0f, progAchiev = 0f, preProgAchiev = 0f, preProgTarg = 0f, progTargTot = 0f, preProgTargTot = 0f, percentIncDecInTarget = 0f, percentIncDecInAchiev = 0f;
			int sno = 1;
			if (list1 != null && list1.size() > 0) {
				logger.debug("in bd if list1 block start");
				for (int i = 0; i < list1.size(); i++) {
					logger.debug("in bd list1 for loop start:-" + list1.get(i));
					reportDTO = new ReportDTO();
					ArrayList list = (ArrayList) list1.get(i);

					progMonth1 = (String) list.get(0);
					progTarg = Float.parseFloat((String) list.get(1));
					progTargTot = progTargTot + progTarg;

					reportDTO.setSno(sno);
					if (progMonth1.equalsIgnoreCase("Apr")) {
						reportDTO.setProgMonth("APRIL");
					} else if (progMonth1.equalsIgnoreCase("May")) {
						reportDTO.setProgMonth("MAY");
					} else if (progMonth1.equalsIgnoreCase("Jun")) {
						reportDTO.setProgMonth("JUNE");
					} else if (progMonth1.equalsIgnoreCase("Jul")) {
						reportDTO.setProgMonth("JULY");
					} else if (progMonth1.equalsIgnoreCase("Aug")) {
						reportDTO.setProgMonth("AUGUST");
					} else if (progMonth1.equalsIgnoreCase("Sep")) {
						reportDTO.setProgMonth("SEPTEMBER");
					} else if (progMonth1.equalsIgnoreCase("Oct")) {
						reportDTO.setProgMonth("OCTOBER");
					} else if (progMonth1.equalsIgnoreCase("Nov")) {
						reportDTO.setProgMonth("NOVEMBER");
					} else if (progMonth1.equalsIgnoreCase("Dec")) {
						reportDTO.setProgMonth("DECEMBER");
					} else if (progMonth1.equalsIgnoreCase("Jan")) {
						reportDTO.setProgMonth("JANUARY");
					} else if (progMonth1.equalsIgnoreCase("Feb")) {
						reportDTO.setProgMonth("FEBRUARY");
					} else if (progMonth1.equalsIgnoreCase("Mar")) {
						reportDTO.setProgMonth("MARCH");
					}
					reportDTO.setProgTarg(progTarg);
					sno++;
					logger.debug("reportDTO.getProgMonth()========"
							+ reportDTO.getProgMonth());
					logger.debug("reportDTO.getProgTarg()========"
							+ reportDTO.getProgTarg());
					logger.debug("progTargTot========" + progTargTot);

					logger.debug("in bd list3 start:-" + list3.get(i));
					ArrayList lst = (ArrayList) list3.get(i);
					progMonth = (String) lst.get(0);
					progAchiev = Float.parseFloat((String) lst.get(1));
					reportDTO.setProgAchiev(progAchiev);
					logger.debug("progMonth1====" + progMonth);
					logger.debug("progAchiev=====" + reportDTO.getProgAchiev());

					logger.debug("in bd list4 start:-" + list4.get(i));
					lst = (ArrayList) list4.get(i);
					progMonth = (String) lst.get(0);
					preProgAchiev = Float.parseFloat((String) lst.get(1));
					logger.debug("progMonth2=====" + progMonth);
					logger.debug("preProgAchiev=====" + preProgAchiev);

					if (preProgAchiev != 0) {
						percentIncDecInAchiev = ((progAchiev - preProgAchiev) / preProgAchiev) * 100;
					} else {
						percentIncDecInAchiev = progAchiev * 100;
					}
					reportDTO.setPercentIncDecInAchiev(percentIncDecInAchiev);
					logger.debug("percentIncDecInAchiev====="
							+ reportDTO.getPercentIncDecInAchiev());

					returnList.add(reportDTO);
				}
			}

			if (list2 != null && list2.size() > 0) {
				logger.debug("in bd if list2 block start");
				for (int i = 0; i < list2.size(); i++) {
					logger.debug("in bd list2 for loop start:-" + list2.get(i));
					ArrayList list = (ArrayList) list2.get(i);

					progMonth1 = (String) list.get(0);
					preProgTarg = Float.parseFloat((String) list.get(1));

					preProgTargTot = preProgTargTot + preProgTarg;
					logger.debug("preProgTargTot========" + preProgTargTot);
				}
				if (preProgTargTot != 0) {
					percentIncDecInTarget = ((progTargTot - preProgTargTot) / (preProgTargTot)) * 100;
				} else {
					percentIncDecInTarget = progTargTot * 100;
				}
				reportDTO.setProgTargTot(progTargTot);
				logger.debug("reportDTO.getProgTargTot()========"
						+ reportDTO.getProgTargTot());
				reportDTO.setPercentIncDecInTarget(percentIncDecInTarget);
				logger.debug("percentIncDecInTarget========"
						+ reportDTO.getPercentIncDecInTarget());
				returnList.add(reportDTO);
			}
			logger.debug("In getCompRevReceiptsDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getCompRevReceiptsDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ========================================================================
	 * === Method : getRegDocDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Sreelatha M Created Date : May 19, 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ====================================================
	 *            =======================
	 */
	public ArrayList getRegDocDetails(ReportDTO _reportDTO) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("in getRegDocDetails");
			ArrayList resultList = dao.getRegDocDetails(_reportDTO);
			int j = 1;
			if (resultList != null && resultList.size() > 0) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportDTO reportDTO = new ReportDTO();
					reportDTO.setSno(j);
					reportDTO.setRegUserId((String) list.get(0));
					reportDTO.setRegDocDetails((String) list.get(1));
					reportDTO.setRegPropOwnerName((String) list.get(2));
					reportDTO.setRegCount(Integer
							.parseInt((String) list.get(3)));
					reportDTO.setRegTotal(Float
							.parseFloat((String) list.get(4)));
					j++;
					returnList.add(reportDTO);
					logger.debug("reportDTO.getRegUserId=="
							+ reportDTO.getRegUserId());
					logger.debug("reportDTO.getRegDocDetails=="
							+ reportDTO.getRegDocDetails());
					logger.debug("reportDTO.getRegPropOwnerName=="
							+ reportDTO.getRegPropOwnerName());
					logger.debug("reportDTO.getRegCount=="
							+ reportDTO.getRegCount());
					logger.debug("reportDTO.getRegTotal=="
							+ reportDTO.getRegTotal());
				}
				logger.debug("in bd if block end ");
			}
			logger.debug("In getRegDocDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getRegDocDetails Exception " + err);
		}
		return returnList;
	}

	// added bu Rats
	public int getYearCheck() {
		ReportDAO dao = new ReportDAO();
		return Integer.parseInt(dao.getYearCheck());
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
			ReportDTO dto = new ReportDTO();
			int YearPlus = Year + 1;
			dto.setYearValue("" + Year);
			dto.setYear("" + Year + "-" + YearPlus);
			listRet.add(dto);
		}
		return listRet;
	}

	// Added by Rachita for Create Target

	public ArrayList getYearForCreateTarget(int iYear) {

		String str = "";
		ArrayList listRet = new ArrayList();

		ReportDAO dao = new ReportDAO();
		ArrayList list = dao.getCurrentYearCreateTarget();
		if (list != null) {
			if (list.size() == 1) {
				ArrayList ret = (ArrayList) list.get(0);
				str = (String) ret.get(0);

			}
		}
		for (int i = 0; i < iYear; i++) {
			int Year = Integer.parseInt(str) + i;
			ReportDTO dto = new ReportDTO();
			int YearPlus = Year + 1;
			dto.setYearValue("" + Year);
			dto.setYear("" + Year + "-" + YearPlus);
			listRet.add(dto);
		}
		return listRet;
	}

	public String getOfficeType(String officeId) {

		String officeType = null;
		ReportDAO dao = new ReportDAO();
		officeType = dao.getOfficeType(officeId);

		return officeType;

	}

	public ArrayList getAllTehsil(String distId) {

		ReportDAO dao = new ReportDAO();
		return dao.getAllTehsil(distId);

	}

	public String getDistrictId(String officeId) {
		// TODO Auto-generated method stub
		ReportDAO dao = new ReportDAO();

		return dao.getDistrictId(officeId);
	}

	public String getType(String officeType) {
		// TODO Auto-generated method stub
		ReportDAO dao = new ReportDAO();
		return dao.getType(officeType);
	}

	public ArrayList getDistListBO(String officeId) {
		ReportDAO dao = new ReportDAO();
		return dao.getDistListDAO(officeId);

	}

	public String getDIGZoneBo(String officeId) {
		ReportDAO dao = new ReportDAO();
		return dao.getDIGZoneDAO(officeId);
	}

	public ArrayList getCurrentRangeList() {
		ReportDAO dao = new ReportDAO();
		return dao.getCurrentRangeList();
	}

	public ArrayList getCurrentSubClauseList1(String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getCurrentSubClauseList1(language);
	}

	public ArrayList getcurrentdeedInstrumentList(String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getcurrentdeedInstrumentList(language);
	}

	public ArrayList getCurrentpropertyList(String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getCurrentpropertyList(language);
	}

	public String getSelectPropertyName(String propId, String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getSelectPropertyName(propId, language);
	}

	public ArrayList getTehsil(String districtId, String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getTehsil(districtId, language);

	}

	public ArrayList getAreaType(String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getAreaType(language);
	}

	public ArrayList getZone(String stateId, String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getZone(stateId, language);
	}

	public ArrayList getDistDIGListBO(String zoneId,String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getDistDIGListDAO(zoneId,language);
	}

	public ArrayList<MISReportDTO> getSubArea(String language, String areaId,
			String tehsilId, String urbanFlag) {

		ArrayList subAreaList = null;
		ReportDAO refDAO = new ReportDAO();
		ArrayList<MISReportDTO> returnList = null;
		try {
			subAreaList = refDAO.getSubArea(language, areaId, tehsilId,
					urbanFlag);
			if (subAreaList != null && subAreaList.size() > 0) {
				returnList = new ArrayList<MISReportDTO>();
				for (int i = 0; i < subAreaList.size(); i++) {
					ArrayList subList = (ArrayList) subAreaList.get(i);
					MISReportDTO propDTO = new MISReportDTO();
					propDTO.setSubAreaId((String) subList.get(0));
					propDTO.setSubAreaName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static ArrayList getPropertyL2(String propertyId, String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getPropertyL2(propertyId, language);
	}

	public String getMuncipalFlag(String subAreaId) {
		SuppleDocDAO dao = new SuppleDocDAO();
		if (subAreaId.equalsIgnoreCase("5")) {
			return "RF";
		} else {
			String municipalId = dao.getMunicipalId(subAreaId);
			if ("1".equalsIgnoreCase(municipalId)
					|| "2".equalsIgnoreCase(municipalId)) {
				return "NF";
			} else if ("4".equalsIgnoreCase(municipalId)) {
				return "RF";
			} else {
				return "N";
			}
		}
	}

	public ArrayList<MISReportDTO> getWardPatwari(String language,
			String subAreaId, String tehsilId) {
		ReportDAO dao = new ReportDAO();
		ArrayList wardPatwariList = null;
		ArrayList<MISReportDTO> returnList = null;
		try {
			wardPatwariList = dao.getWardPatwari(language, subAreaId, tehsilId);
			if (wardPatwariList != null && wardPatwariList.size() > 0) {
				returnList = new ArrayList<MISReportDTO>();
				for (int i = 0; i < wardPatwariList.size(); i++) {
					ArrayList subList = (ArrayList) wardPatwariList.get(i);
					MISReportDTO propDTO = new MISReportDTO();
					propDTO
							.setWardIds(((String) subList.get(0) + "~" + (String) subList
									.get(2)));
					propDTO.setWardPatwariName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return null;
		}
	}

	public ArrayList<MISReportDTO> getColonyName(String language,
			String wardPatwariId) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<MISReportDTO> returnList = null;
		ReportDAO da = new ReportDAO();
		try {
			l1NameList = da.getColonyName(language, wardPatwariId);
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<MISReportDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					MISReportDTO propDTO = new MISReportDTO();
					propDTO.setColonyId((String) subList.get(0) + "~"
							+ (String) subList.get(2) + "~"
							+ (String) subList.get(3));
					propDTO.setColonyName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	public ArrayList getDistrictZone(String zoneId, String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getDistrictZone(zoneId, language);
	}

	public ArrayList getSroListInsp(String tehsilId, String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getSroListInsp(tehsilId, language);
	}

	public ArrayList getSrListInsp(String officeId) {
		ReportDAO dao = new ReportDAO();
		return dao.getSrListInsp(officeId);
	}

	public ArrayList getAllDeedList(String language) {

		ReportDAO dao = new ReportDAO();
		return dao.getAllDeedList(language);
	}

	public ArrayList getInstrument(String deedID, String language) {

		ReportDAO dao = new ReportDAO();
		return dao.getInstrument(deedID, language);

	}

	public ArrayList getAllpropertyList(String language) {
		ReportDAO dao = new ReportDAO();
		return dao.getAllpropertyList(language);
	}

}
/*
 * public ArrayList getSubArea(String language, String areaId, String tehsilId,
 * String string) { // TODO Auto-generated method stub return null; }
 */
