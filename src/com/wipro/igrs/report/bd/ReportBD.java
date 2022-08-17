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
 */

package com.wipro.igrs.report.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.report.bo.ReportBO;
import com.wipro.igrs.report.dto.MISReportDTO;

import com.wipro.igrs.report.dto.ReportDTO;
import com.wipro.igrs.report.form.MISReportForm;
import com.wipro.igrs.spotInsp.bo.SpotInspBO;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;
import com.wipro.igrs.spotInsp.dto.SpotInspNewDTO;

public class ReportBD {

	private IGRSCommon common;
	private Logger logger = (Logger) Logger.getLogger(ReportBD.class);
	ReportBO reportBO = new ReportBO();
	ArrayList list;

	/**
	 * ========================================================================
	 * === Method : getDistrictDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Sreelatha M Created Date : Apr 30, 2008
	 * ==================================
	 * =========================================
	 */
	public ArrayList getDistrictDetails() throws Exception {
		return reportBO.getDistrictDetails();
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
		return reportBO.getEffectFactorDetails();
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
		return reportBO.getRegisterDocumentDetails();
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
		return reportBO.getFiscalYearDetails();
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
		return reportBO.getDroNameDetails();
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
		return reportBO.getSroNameDetails();
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
		return reportBO.getSROwiseStampRegFeeDetails(_reportDTO);
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
	public ArrayList getRevenueProjectDetails(ReportDTO _reportDTO) {
		ReportBO bo = new ReportBO();
		ArrayList list = new ArrayList();
		try {

			list = bo.getRevenueProjectDetails(_reportDTO);

		} catch (Exception x) {
			logger.debug("Hello Sreelatha :-" + x);
		}
		return list;
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
		return reportBO.getSubwiseStampRegFeeDetails(_reportDTO);
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
		return reportBO.getCompRevReceiptsDetails(_reportDTO);
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
		return reportBO.getRegDocDetails(_reportDTO);

	}

	public String getOfficeType(String officeId) {

		String officeType = null;
		officeType = reportBO.getOfficeType(officeId);

		return officeType;
	}

	public ArrayList allTehsilList(String distId) {

		return reportBO.getAllTehsil(distId);

	}

	public String getDistrictId(String officeId) {
		// TODO Auto-generated method stub
		return reportBO.getDistrictId(officeId);
	}

	public String getType(String officeType) {
		// TODO Auto-generated method stub

		return reportBO.getType(officeType);
	}

	public ArrayList getDistList(String officeId) {

		ReportBO reportBO = new ReportBO();
		ArrayList ret = new ArrayList();

		ret = reportBO.getDistListBO(officeId);

		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setDistrictID((String) lst.get(0));
				dto.setDistrictName((String) lst.get(1));
				dto.setZoneId((String) lst.get(2));
				list.add(dto);
			}
			logger.info("SpotInspBD----getDistrcit  " + list);
		}
		return list;

	}

	public String getDIGZone(String officeId) {
		ReportBO reportBO = new ReportBO();
		return reportBO.getDIGZoneBo(officeId);

	}

	public ArrayList getCurrentRangeList() {
		ReportBO reportBO = new ReportBO();
		ArrayList ret = reportBO.getCurrentRangeList();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setSelectedName((String) lst.get(0));
				dto.setSelectedId((String) lst.get(1));
				list.add(dto);
			}
			// logger.info("SpotInspBD----getState  "+list);
		}
		return list;

	}

	public ArrayList getCurrentSubClauseList1(String language) {
		ReportBO reportBO = new ReportBO();
		ArrayList ret = reportBO.getCurrentSubClauseList1(language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setSelectedName((String) lst.get(0));
				dto.setSelectedId((String) lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  " + list);
		}
		return list;

	}

	public ArrayList getcurrentdeedInstrumentList(String language) {

		ReportBO reportBO = new ReportBO();
		ArrayList ret = reportBO.getcurrentdeedInstrumentList(language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setSelectedName((String) lst.get(0));
				dto.setSelectedId((String) lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  " + list);
		}
		return list;

	}

	public ArrayList getCurrentpropertyList(String language) {

		ReportBO reportBO = new ReportBO();
		ArrayList ret = reportBO.getCurrentpropertyList(language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				String id = (String) lst.get(0);
				dto.setSelectedId(id);
				String name = reportBO.getSelectPropertyName(id, language);
				dto.setSelectedName(name);
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  " + list);
		}
		return list;

	}

	public ArrayList getTehsil(String districtId, String language) {

		ReportBO reportBO = new ReportBO();

		ArrayList ret = reportBO.getTehsil(districtId, language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setTehsilName((String) lst.get(1));
				dto.setTehsilId((String) lst.get(0));
				list.add(dto);
			}
		}
		// logger.info("in SpotInspBD  getTehsil()............... "+list);
		return list;

	}

	public ArrayList getAreTypea(String language) {

		ReportBO reportBO = new ReportBO();

		ArrayList ret = reportBO.getAreaType(language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setAreaTypeName((String) lst.get(1));
				dto.setAreaTypeId((String) lst.get(0));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getTehsil()............... " + list);
		return list;

	}

	public ArrayList getZone(String stateId, String language) {

		ReportBO reportBO = new ReportBO();

		ArrayList ret = reportBO.getZone(stateId, language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setZoneId((String) lst.get(0));
				dto.setZoneName((String) lst.get(1));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getDistrict()............... " + list);
		return list;

	}

	public ArrayList getDistDIGList(String zoneId,String language) {

		ReportBO reportBO = new ReportBO();

		ArrayList ret = reportBO.getDistDIGListBO(zoneId,language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setDistrictName((String) lst.get(0));
				dto.setDistrictId((String) lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  " + list);
		}
		return list;

	}

	public ArrayList getDistDIGListForZone(String zoneId,String language) {

		ReportBO reportBO = new ReportBO();

		ArrayList ret = reportBO.getDistDIGListBO(zoneId,language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setDistrictName((String) lst.get(0));
				dto.setDistrictID((String) lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  " + list);
		}
		return list;

	}

	public ArrayList getPropertyL2(String propertyId, String language) {

		ReportBO objBo = new ReportBO();
		ArrayList ret = ReportBO.getPropertyL2(propertyId, language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setPropertySubID((String) lst.get(0));
				dto.setPropertySubName((String) lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  " + list);
		}
		return list;

	}

	public ArrayList getDistrictZone(String zoneId, String language) {
		ReportBO objBo = new ReportBO();

		ArrayList ret = objBo.getDistrictZone(zoneId, language);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setDistrictID((String) lst.get(1));
				dto.setDistrictName((String) lst.get(0));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getDistrict()............... " + list);
		return list;
	}

	public ArrayList getSroListInsp(String tehsilId, String language) {

		ReportBO objBo = new ReportBO();
		ArrayList ret = objBo.getSroListInsp(tehsilId, language);
		ArrayList list1 = new ArrayList();
		if (ret != null) {
			
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);
				MISReportDTO dto1 = new MISReportDTO();
				dto1.setOfficeId((String) list.get(0));
				dto1.setOfficeSROName((String) list.get(1));
				list1.add(dto1);
			}
		}

		return list1;
	}

	public ArrayList getSrList(String officeId) {

		ReportBO objBo = new ReportBO();
		ArrayList ret = objBo.getSrListInsp(officeId);
		ArrayList list1=new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setSrId((String) list.get(0));
				dto.setSrName((String) list.get(1));
				list1.add(dto);
			}
		}

		return list1;

	}

	public ArrayList getAllDeedList(String language) {

		ReportBO objBo = new ReportBO();
		ArrayList ret = objBo.getAllDeedList(language);
		ArrayList list1=new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setDeedID((String) list.get(0));
				dto.setDeedName((String) list.get(1));
				list1.add(dto);
			}
		}

		return list1;

	}

	public ArrayList getInstrument(String deedID, String language) {

		ReportBO objBo = new ReportBO();
		ArrayList ret = objBo.getInstrument(deedID,language);
		ArrayList list1=new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setInstID((String) list.get(0));
				dto.setInstName((String) list.get(1));
				list1.add(dto);
			}
		}

		return list1;

	}

	public ArrayList getAllpropertyList(String language) {

		ReportBO objBo = new ReportBO();
		ArrayList ret = objBo.getAllpropertyList(language);
		ArrayList list1=new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);
				MISReportDTO dto = new MISReportDTO();
				dto.setPropertyID((String) list.get(0));
				dto.setPropertyName((String) list.get(1));
				list1.add(dto);
			}
		}

		return list1;

	}

}