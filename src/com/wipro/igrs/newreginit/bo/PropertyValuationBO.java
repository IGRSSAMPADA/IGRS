package com.wipro.igrs.newreginit.bo;

/* Copyright (c) 2006-2008 WIPRO. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.propertyvaluation.constant.CommonConstant;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.PropertyValuationDTO;
import com.wipro.igrs.newreginit.util.PropertiesFileReader;

/**
 * @author Madan Mohan
 * 
 */
public class PropertyValuationBO {

	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger
			.getLogger(PropertyValuationBO.class);

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDistrict(String languageLocale) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList ret = common.getDistrict(CommonConstant.STATE_ID);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				PropertyValuationDTO dto = new PropertyValuationDTO();
				logger.debug("Dist:-" + lst.get(0) + ":" + lst.get(1));
				dto.setDistrictID((String) lst.get(0));
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setDistrict((String) lst.get(1));
				dto.setHdnDistrict((String) lst.get(0) + "~"
						+ (String) lst.get(1));
				}else{
					dto.setDistrict((String) lst.get(2));
					dto.setHdnDistrict((String) lst.get(0) + "~"
							+ (String) lst.get(2));
				}
				list.add(dto);
			}

		}
		return list;
	}

	/**
	 * @param districtId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getTehsil(String districtId, String languageLocale) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList ret = common.getThesil(districtId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				PropertyValuationDTO dto = new PropertyValuationDTO();
				logger.debug("Tehsil:-" + lst.get(0) + ":" + lst.get(1));
				dto.setTehsilID((String) lst.get(0));
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setTehsil((String) lst.get(1));
				dto.setHdnTehsil((String) lst.get(0) + "~"
						+ (String) lst.get(1));
				}else{
					dto.setTehsil((String) lst.get(2));
					dto.setHdnTehsil((String) lst.get(0) + "~"
							+ (String) lst.get(2));
				}
				list.add(dto);
			}

		}
		return list;
	}

	/**
	 * @param tehsilId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWard(String tehsilId, String areaType, String wardType, String languageLocale)
			throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList ret = common.getWard(tehsilId, areaType, wardType);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				PropertyValuationDTO dto = new PropertyValuationDTO();
				logger.debug("Tehsil:-" + lst.get(0) + ":" + lst.get(1));
				dto.setWardId((String) lst.get(0));
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setWard((String) lst.get(1));
				dto.setHdnWard((String) lst.get(0) + "~" + (String) lst.get(1));
				}else{
					dto.setWard((String) lst.get(2));
					dto.setHdnWard((String) lst.get(0) + "~" + (String) lst.get(2));
				}
				list.add(dto);
			}

		}
		return list;
	}

	/**
	 * @param tehsilId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getMahalla(String patwariId, String languageLocale) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList ret = common.getMahalla(patwariId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				PropertyValuationDTO dto = new PropertyValuationDTO();
				logger.debug("Mahalla:-" + lst.get(0) + ":" + lst.get(1));
				dto.setMahallaId((String) lst.get(0));
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setMahalla((String) lst.get(1));
				dto.setHdnMahalla((String) lst.get(0) + "~"
						+ (String) lst.get(1));
				}else{
					dto.setMahalla((String) lst.get(2));
					dto.setHdnMahalla((String) lst.get(0) + "~"
							+ (String) lst.get(2));
				}
				list.add(dto);
			}

		}
		return list;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getPropertyType(String languageLocale) {
		ArrayList list = new ArrayList();

		/*
		 * String propertyType[][] = { { "P", "Plot" }, { "B", "Building" }, {
		 * "A", "Agricultural Land" } };
		 */

		try {
			ArrayList retList = new IGRSCommon().getPropertyType();
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setPropertyTypeID((String) lst.get(0));
					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setPropertyType((String) lst.get(1));
					dto.setHdnPropertyType((String) lst.get(0) + "~"
							+ (String) lst.get(1));
					}else{
						dto.setPropertyType((String) lst.get(2));
						dto.setHdnPropertyType((String) lst.get(0) + "~"
								+ (String) lst.get(2));
					}
					list.add(dto);
				}
			}
			/*
			 * for( int i=0; i<propertyType.length; i++ ){
			 * 
			 * PropertyValuationDTO dto = new PropertyValuationDTO();
			 * dto.setPropertyTypeID(propertyType[i][0]);
			 * dto.setPropertyType(propertyType[i][1]);
			 * dto.setHdnPropertyType(dto.getPropertyTypeID()+"~"+dto
			 * .getPropertyType()); list.add(dto); }
			 */
		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getMunicipalBody(String languageLocale) {
		ArrayList list = new ArrayList();
		try {
			ArrayList retList = new IGRSCommon().getMunicipalBody();
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setMunicipalBodyID((String) lst.get(0));
					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setMunicipalBody((String) lst.get(1));
					dto.setHdnMunicipalBody((String) lst.get(0) + "~"
							+ (String) lst.get(1));
					}else{
						dto.setMunicipalBody((String) lst.get(2));
						dto.setHdnMunicipalBody((String) lst.get(0) + "~"
								+ (String) lst.get(2));
					}
					list.add(dto);
				}
			}

		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getAreaType(String languageLocale) {
		ArrayList list = new ArrayList();
		try {
			ArrayList retList = new IGRSCommon().getAreaType();
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setAreaId((String) lst.get(0));
					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setAreaType((String) lst.get(1));
					dto.setHdnAreaType((String) lst.get(0) + "~"
							+ (String) lst.get(1));
					}else{
						dto.setAreaType((String) lst.get(2));
						dto.setHdnAreaType((String) lst.get(0) + "~"
								+ (String) lst.get(2));
					}
					logger.debug(lst.get(0) + ":" + lst.get(1));
					list.add(dto);
				}

			}

		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	/**
	 * @param propertyId
	 * @return ArrayList
	 */
	public ArrayList getUsePlot(String propertyId) {
		ArrayList list = new ArrayList();
		String usePlot[][] = { { "R", "Residential" }, { "C", "Commercial" } };

		try {
			ArrayList retList = new IGRSCommon().getUsedPlot(propertyId);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setUsePlotId((String) lst.get(0));
					dto.setUsePlot((String) lst.get(1));
					dto.setHdnUsePlot((String) lst.get(0) + "~"
							+ (String) lst.get(1) + "~" + (String) lst.get(2));
					list.add(dto);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	/**
	 * @param propertyTypeL1Id
	 * @return ArrayList
	 */
	public ArrayList getL2Type(String propertyTypeL1Id) {
		ArrayList list = new ArrayList();

		try {
			ArrayList retList = new IGRSCommon()
					.getBuildingType(propertyTypeL1Id);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setCeilingTypeId((String) lst.get(0));
					dto.setCeilingType((String) lst.get(2));
					dto.setHdnCeilingName((String) lst.get(0) + "~"
							+ (String) lst.get(1) + "~" + (String) lst.get(2));
					list.add(dto);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getFloorType(String l1ID) {
		ArrayList list = new ArrayList();

		try {
			ArrayList retList = new IGRSCommon().getFloor(l1ID);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setTypeFloorID((String) lst.get(0));
					dto.setTypeFloorName((String) lst.get(1));
					dto.setTypeFloorDesc((String) lst.get(2));
					dto.setHdnTypeFloor((String) lst.get(0) + "~"
							+ (String) lst.get(1) + "~" + (String) lst.get(2));
					list.add(dto);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	public ArrayList getLandType() {
		String[][] landType = { { "SQM", "Sq.Meter" },
				{ "HECTRE", "Sq.Hectare" } };
		ArrayList list = new ArrayList();
		for (int i = 0; i < landType.length; i++) {

			PropertyValuationDTO dto = new PropertyValuationDTO();
			dto.setLandMeterId(landType[i][0]);
			dto.setLandMeterType(landType[i][1]);

			list.add(dto);
		}
		return list;
	}

	public String getPropertyID(String key) {
		String propertyID = "";
		try {

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.propertyvaluation");
			propertyID = pr.getValue(key);
		}catch(Exception x) {
			logger.debug(x);
		}
		
			
		return propertyID;
	}
	/**
	 * @param districtId
	 * @param tehsilId
	 * @param wardId
	 * @param mohallaId
	 * @param propertyId
	 * @return ArrayList
	 */
	public ArrayList getSubClause(PropertyValuationDTO useDTO) {

		ArrayList listDTO = new ArrayList();
		String[] districtId = useDTO.getDistrictID().split("~");
		String[] tehsilId = useDTO.getTehsilID().split("~");
		String[] wardId = useDTO.getWardId().split("~");
		String[] mohallaId = useDTO.getMahallaId().split("~");
		String[] propertyId = useDTO.getPropertyTypeID().split("~");
		String[] usageType = useDTO.getUsePlotId().split("~");
		String[] param = null;
		try {

			
			String buildingID = getPropertyID(CommonConstant.BUILDING_ID);
			String SQL = "";
			logger.debug("Property Type:-" + propertyId);

			if (propertyId != null && propertyId.length == 2) {
				String propertyTypeId = propertyId[0];
				if (buildingID.equals(propertyTypeId)) {
					param = new String[8];
					String[] l2Id = useDTO.getCeilingTypeId().split("~");
					String[] floorId = useDTO.getTypeFloorID().split("~");

					if (districtId != null && districtId.length == 2) {
						param[0] = districtId[0];
					}
					if (tehsilId != null && tehsilId.length == 2) {
						param[1] = tehsilId[0];
					}
					if (wardId != null && wardId.length == 2) {
						param[2] = wardId[0];
					}
					if (mohallaId != null && mohallaId.length == 2) {
						param[3] = mohallaId[0];
					}
					if (propertyId != null && propertyId.length == 2) {
						param[4] = propertyId[0];

					}
					if (usageType != null && usageType.length == 3) {
						param[5] = usageType[0];
					}
					if (l2Id != null && l2Id.length == 3) {
						param[6] = l2Id[0];
					}
					if (floorId != null && floorId.length == 3) {
						param[7] = floorId[0];
					}

					SQL = CommonSQL.SUB_CLAUSE_BUILDING_QUERY;
				} else {
					param = new String[6];
					if (districtId != null && districtId.length == 2) {
						param[0] = districtId[0];
					}
					if (tehsilId != null && tehsilId.length == 2) {
						param[1] = tehsilId[0];
					}
					if (wardId != null && wardId.length == 2) {
						param[2] = wardId[0];
					}
					if (mohallaId != null && mohallaId.length == 2) {
						param[3] = mohallaId[0];
					}
					if (propertyId != null && propertyId.length == 2) {
						param[4] = propertyId[0];

					}
					if (usageType != null && usageType.length == 3) {
						param[5] = usageType[0];
					}
					SQL = CommonSQL.SUB_CLAUSE_DETAILS_QUERY;
				}

			}

			ArrayList retList = new IGRSCommon().getSubClause(param, SQL);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setSubClauseId((String) lst.get(0) + "~"
							+ (String) lst.get(1));
					dto.setSubClause((String) lst.get(1));
					dto.setAgriUnitFlag((String) lst.get(2));
					// dto.setHdnSubClause((String) lst.get(0)
					// +"~"+(String) lst.get(1));
					logger.debug(lst.get(0) + ":" + lst.get(1));
					listDTO.add(dto);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return listDTO;
	}

	/*public String[] getMarketValue(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];
		String[] districtId = useDTO.getDistrictID().split("~");
		String[] tehsilId = useDTO.getTehsilID().split("~");
		String[] wardId = useDTO.getWardId().split("~");
		String[] mohallaId = useDTO.getMahallaId().split("~");
		String[] propertyId = useDTO.getPropertyTypeID().split("~");
		String[] propertyTypeL1 = useDTO.getUsePlotId().split("~");

		String propertyTypeL2Id = null;
		String sqmeterType = null;
		double totalSqlMeter = useDTO.getTotalSqMeter();
		String noTrees = useDTO.getHdnNoOfTree();

		logger.debug("No of Trees:-" + noTrees);

		String district = "";
		String tehsil = "";
		String ward = "";
		String mohalla = "";
		String property = "";
		String subclause = useDTO.getHdnExtSubClause();
		String propertyTypeL1Id = "";

		if (districtId != null && districtId.length == 2) {
			district = districtId[0];
		}
		if (tehsilId != null && tehsilId.length == 2) {
			tehsil = tehsilId[0];
		}
		if (wardId != null && wardId.length == 2) {
			ward = wardId[0];
		}
		if (mohallaId != null && mohallaId.length == 2) {
			mohalla = mohallaId[0];
		}
		if (propertyId != null && propertyId.length == 2) {
			property = propertyId[0];
		}
		if (propertyTypeL1 != null && propertyTypeL1.length == 3) {
			propertyTypeL1Id = propertyTypeL1[0];
		}
		double baseValue = useDTO.getConsiderAmt();

		try {
			marketValue = new IGRSCommon().getPropertyValuation(district,
					tehsil, ward, mohalla, property, propertyTypeL1Id,
					propertyTypeL2Id, subclause, baseValue, sqmeterType,
					totalSqlMeter, noTrees);
			

		} catch (Exception x) {
			logger.debug(x);
		}
		 if(marketValue !=null) {
			 if(marketValue[0] == null || "0".equals(marketValue[0])) {
				 marketValue[0] = Double.toString(baseValue);
			 }
		 }
		logger.debug("marketValue:-"+marketValue+" baseValue:-"+baseValue);
		return marketValue;
	}*/

	/*public String[] getFloorWiseMarketValue(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];
		String[] districtId = useDTO.getDistrictID().split("~");
		String[] tehsilId = useDTO.getTehsilID().split("~");
		String[] wardId = useDTO.getWardId().split("~");
		String[] mohallaId = useDTO.getMahallaId().split("~");
		String[] propertyId = useDTO.getPropertyTypeID().split("~");
		String[] propertyTypeL1 = useDTO.getUsePlotId().split("~");
		String[] propertyTypeL2 = useDTO.getCeilingTypeId().split("~");
		logger.debug("useDTO.getFloorID() BO:-" + useDTO.getTypeFloorID());
		String[] floorType = useDTO.getTypeFloorID().split("~");

		String propertyTypeL2Id = null;
		String floorTypeId = null;
		String sqmeterType = null;
		double totalSqlMeter = useDTO.getTotalSqMeter();
		double consturctedArea = useDTO.getConstructedArea();

		String noTrees = useDTO.getHdnNoOfTree();

		logger.debug("No of Trees:-" + noTrees);

		String district = "";
		String tehsil = "";
		String ward = "";
		String mohalla = "";
		String property = "";
		String subclause = useDTO.getHdnExtSubClause();
		String propertyTypeL1Id = "";

		if (districtId != null && districtId.length == 2) {
			district = districtId[0];
		}
		if (tehsilId != null && tehsilId.length == 2) {
			tehsil = tehsilId[0];
		}
		if (wardId != null && wardId.length == 2) {
			ward = wardId[0];
		}
		if (mohallaId != null && mohallaId.length == 2) {
			mohalla = mohallaId[0];
		}
		if (propertyId != null && propertyId.length == 2) {
			property = propertyId[0];
		}
		if (propertyTypeL1 != null && propertyTypeL1.length == 3) {
			propertyTypeL1Id = propertyTypeL1[0];
		}
		if (propertyTypeL2 != null && propertyTypeL2.length == 3) {
			propertyTypeL2Id = propertyTypeL2[0];
		}
		if (floorType != null && floorType.length == 3) {
			floorTypeId = floorType[0];
		}

		double baseValue = useDTO.getConsiderAmt();

		try {
			marketValue = new IGRSCommon().getFloorwisePropertyValuation(
					district, tehsil, ward, mohalla, property,
					propertyTypeL1Id, propertyTypeL2Id, subclause, floorTypeId,
					baseValue, sqmeterType, totalSqlMeter, consturctedArea,
					noTrees);
			logger.debug("marketValue(getFloorWiseMarketValue):-"
					+marketValue[0]+":"
					+marketValue[1]+":"
					+marketValue[2]);
		} catch (Exception x) {
			logger.debug(x);
		}
		if(marketValue !=null) {
			 if(marketValue[0] == null || "0".equals(marketValue[0])) {
				 marketValue[0] = Double.toString(baseValue);
			 }
		 }
		return marketValue;
	}*/

	/**
	 * @return ArrayList
	 */
	public ArrayList getDeed() {
		ArrayList list = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			ArrayList retList = common.getDeedType(null);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					DutyCalculationDTO dto = new DutyCalculationDTO();
					/*dto.setDeedId(lst.get(0).toString() + "~"
							+ lst.get(1).toString());*/
					//above commented by roopam
					dto.setDeedType(lst.get(1).toString());

					list.add(dto);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	/**
	 * @param deedType
	 * @return ArrayList
	 */
	public ArrayList getInstrument(String deedType) {
		ArrayList list = new ArrayList();
		try {

			String[] deedId = new String[2];
			deedId[0] = deedType;
			deedId[1] = CommonConstant.INSTRUMENT_STATUS;
			ArrayList retList = new IGRSCommon().getInstruments(deedId);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					InstrumentsDTO dto = new InstrumentsDTO();
					/*dto.setInstId(lst.get(0).toString());*/
					//above commented by roopam
					dto.setInstType(lst.get(1).toString());
					dto.setHdnAmount(lst.get(0).toString() + "~"
							+ lst.get(1).toString());

					logger.debug(dto.getHdnAmount());
					list.add(dto);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	/**
	 * 
	 * @param deedType
	 * @param instId
	 * @return ArrayList
	 */
	public ArrayList getExemptions(String deedType, String instId) {
		ArrayList list = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			String[] deedId = new String[3];
			deedId[0] = deedType;
			deedId[1] = instId;
			deedId[2] = CommonConstant.EXEMPTION_STATUS;

			ArrayList retList = common.getExemptions(deedId);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					ExemptionDTO dto = new ExemptionDTO();
					dto.setExemId(lst.get(0).toString());
					dto.setExemType(lst.get(1).toString());
					dto.setExemDeedId(lst.get(0).toString() + "~"
							+ lst.get(1).toString());
					logger.debug("###" + lst.get(1).toString());
					list.add(dto);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	/**
	 * 
	 * @param dutyDTO
	 * @param instDTO
	 * @param exeDTO
	 * @return
	 */
	/*public String getDutyCalculation(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO,
			PropertyValuationDTO propertyDTO) {
		String calculatedDuty = "";
		try {
			IGRSCommon common = new IGRSCommon();
			logger.debug("@@@@@@@@" + instDTO.getHdnAmount() + ":"
					+ dutyDTO.getDeedId());
			String[] inst = instDTO.getHdnAmount().split("~");
			String[] deed = dutyDTO.getDeedId().split("~");
			String deedId = "";
			String instId = "";
			if (inst != null && inst.length == 2) {
				logger.debug("Instrument type:-" + instId + ":" + inst[1]);
				instId = inst[0];
			}
			if (deed != null && deed.length == 2) {
				logger.debug("Deed Type:-" + deedId + ":" + deed[1]);
				deedId = deed[0];
			}

			String exemption = exeDTO.getHdnExAmount();
			logger.debug("Exemption Type:-" + exemption);
			double marketValue = propertyDTO.getMarketValue();
			double baseValue = propertyDTO.getConsiderAmt();
			logger.debug("marketValue:-" + marketValue);
			logger.debug("baseValue:-" + baseValue);

			if (marketValue > baseValue || marketValue == baseValue) {
				calculatedDuty = common.getStampDuty(deedId, instId, exemption,
						marketValue);

			} else if (baseValue > marketValue) {
				calculatedDuty = common.getStampDuty(deedId, instId, exemption,
						baseValue);

			}

			logger.debug("Duty :-" + calculatedDuty);
		} catch (Exception x) {
			logger.debug(x);
		}

		return calculatedDuty;
	}*/

	/**
	 * 
	 * @param dutyDTO
	 * @param instDTO
	 * @param exeDTO
	 * @return String[]
	 */
	/*public String[] getRegistrationFee(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO,
			PropertyValuationDTO propertyDTO) {
		String[] regFee = new String[3];
		try {
			IGRSCommon common = new IGRSCommon();
			logger.debug("@@@@@@@@" + instDTO.getHdnAmount() + ":"
					+ dutyDTO.getDeedId());
			String[] inst = instDTO.getHdnAmount().split("~");
			String[] deed = dutyDTO.getDeedId().split("~");
			String deedId = "";
			String instId = "";
			if (inst != null && inst.length == 2) {
				instId = inst[0];
				logger.debug("Instrument type:-" + instId + ":" + inst[1]);
			}
			if (deed != null && deed.length == 2) {
				deedId = deed[0];
				logger.debug("Deed Type:-" + deedId + ":" + deed[1]);
			}

			String exemption = exeDTO.getHdnExAmount();
			logger.debug("Exemption Type:-" + exemption);
			double marketValue = propertyDTO.getMarketValue();
			double baseValue = propertyDTO.getConsiderAmt();
			logger.debug("marketValue:-" + marketValue);
			logger.debug("baseValue:-" + baseValue);

			if (marketValue > baseValue || marketValue == baseValue) {
				regFee = common.getRegistrstionFee(deedId, instId, null,
						marketValue);

			} else if (baseValue > marketValue) {
				regFee = common.getRegistrstionFee(deedId, instId, null,
						baseValue);
			}

			logger.debug("Registration Fee :-" + regFee);
		} catch (Exception x) {
			logger.debug(x);
		}
		 
		return regFee;
	}*/

	/**
	 * @param String[]
	 * @return ArrayList
	 */
	public ArrayList getExemptionList(String[] exemptions) {
		ArrayList exemp = new ArrayList();
		if (exemptions != null && exemptions.length > 0) {
			for (int i = 0; i < exemptions.length; i++) {
				ExemptionDTO dto = new ExemptionDTO();
				dto.setExemType(exemptions[i]);
				exemp.add(dto);

			}
		}
		return exemp;
	}

	/**
	 * @param String[]
	 * @return ArrayList
	 */
	public ArrayList getSubClauseList(String[] subclause) {
		ArrayList subclauselist = new ArrayList();
		if (subclause != null && subclause.length > 0) {
			for (int i = 0; i < subclause.length; i++) {
				PropertyValuationDTO dto = new PropertyValuationDTO();
				dto.setSubClause(subclause[i]);
				subclauselist.add(dto);

			}
		}
		return subclauselist;
	}

	public HashMap getSubClauseMap(HashMap map, String[] subclause,
			String floorDesc) {
		HashMap collectionMap = new HashMap();

		ArrayList subclauselist = new ArrayList();

		if (subclause != null && subclause.length > 0) {
			for (int i = 0; i < subclause.length; i++) {
				PropertyValuationDTO dto = new PropertyValuationDTO();
				dto.setSubClause(subclause[i]);
				subclauselist.add(dto);

			}
		}
		String[] floorKey = floorDesc.split("~");
		String mapkey = "";
		if (floorKey != null && floorKey.length == 3) {
			mapkey = floorKey[2] + "(" + floorKey[1] + ")";
		}
		collectionMap.put(mapkey, subclauselist);

		Iterator I = map.entrySet().iterator();
		logger.debug("I.hasNext():-" + I.hasNext() + ":" + subclause);

		while (I.hasNext()) {

			ArrayList subclauseListmap = new ArrayList();

			Map.Entry me = (Map.Entry) I.next();
			logger.debug("getKey :-" + me.getKey());
			PropertyValuationDTO hDTO = (PropertyValuationDTO) me.getValue();
			logger.debug("FloorID:-" + hDTO.getFloorID());
			logger.debug("Floor Subclause:-" + hDTO.getHdnSubClauseName());

			String key = "";
			if (hDTO != null) {
				key = hDTO.getTypeFloorDesc() + "(" + hDTO.getFloorID() + ")";
			}

			String[] subclauseary = hDTO.getHdnSubClauseName().split(",");
			for (int i = 0; i < subclauseary.length; i++) {
				PropertyValuationDTO dto = new PropertyValuationDTO();
				logger.debug("subclause:-" + subclauseary[i]);
				dto.setSubClause(subclauseary[i]);
				subclauseListmap.add(dto);
			}
			collectionMap.put(key, subclauseListmap);
		}

		return collectionMap;
	}

	/**
	 * @param useDTO
	 * @return PropertyValuationDTO
	 */
	public PropertyValuationDTO getPropertyValuationDTO(
			PropertyValuationDTO useDTO) throws Exception {

		String[] district = useDTO.getDistrictID() == null ? null : useDTO
				.getDistrictID().split("~");
		if (district != null && district.length == 2) {
			useDTO.setDistrict(district[1]);
			useDTO.setDistrictID(district[0]);
		}
		String[] tehsil = useDTO.getTehsilID() == null ? null : useDTO
				.getTehsilID().split("~");
		if (tehsil != null && tehsil.length == 2) {
			useDTO.setTehsil(tehsil[1]);
			useDTO.setTehsilID(tehsil[0]);
		}
		String[] ward = useDTO.getWardId() == null ? null : useDTO.getWardId()
				.split("~");
		if (ward != null && ward.length == 2) {
			useDTO.setWard(ward[1]);
			useDTO.setWardId(ward[0]);
		}

		
		String[] mohalla = useDTO.getMahallaId() == null ? null : useDTO
				.getMahallaId().split("~");
		if (mohalla != null && mohalla.length == 2) {
			useDTO.setMahalla(mohalla[1]);
			useDTO.setMahallaId(mohalla[0]);
		}
		String[] property = useDTO.getPropertyTypeID() == null ? null : useDTO
				.getPropertyTypeID().split("~");
		if (property != null && property.length == 2) {
			useDTO.setPropertyType(property[1]);
			useDTO.setPropertyTypeID(property[0]);
		}
		
		String[] muncipal = useDTO.getMunicipalBodyID() == null ? null : useDTO
				.getMunicipalBodyID().split("~");
		if (muncipal != null && muncipal.length == 2) {
			useDTO.setMunicipalBody(muncipal[1]);
			useDTO.setMunicipalBodyID(muncipal[0]);
		}
		String[] area = useDTO.getAreaId() == null ? null : useDTO.getAreaId()
				.split("~");
		if (area != null && area.length == 2) {
			useDTO.setAreaType(area[1]);
			useDTO.setAreaId(area[0]);
		}
		String[] usePlot = useDTO.getUsePlotId() == null ? null : useDTO
				.getUsePlotId().split("~");

		if (usePlot != null && usePlot.length == 3) {
			useDTO.setUsePlot(usePlot[1]);
			useDTO.setUsePlotId(usePlot[0]);
			logger.debug(usePlot[1]);
		}
		
		
		IGRSCommon common = new IGRSCommon();
		// if (useDTO.getTotalSqMeter() != null) {
		useDTO.setTotalSqMeter(Double.parseDouble(common
				.getCurrencyFormat(useDTO.getTotalSqMeter())));
		// }
		// if(useDTO.getConsiderAmt() != null) {
		useDTO.setConsiderAmt(Double.parseDouble(common
				.getCurrencyFormat(useDTO.getConsiderAmt())));
		// }
		useDTO.setMktValue(common.getCurrencyFormat(useDTO.getMarketValue()));
		/*
		 * String[] subclause = useDTO.getHdnSubClauseName().split(",");
		 * if(subclause != null && subclause.length ==2) {
		 * useDTO.setSubClause(subclause[1]); }
		 */

		return useDTO;
	}

	/*public String[] getMunicipalDuty(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];
		String[] districtId = useDTO.getDistrictID() == null ? null : useDTO
				.getDistrictID().split("~");
		String[] tehsilId = useDTO.getTehsilID() == null ? null : useDTO
				.getTehsilID().split("~");
		String[] wardId = useDTO.getWardId() == null ? null : useDTO
				.getWardId().split("~");
		String[] mohallaId = useDTO.getMahallaId() == null ? null : useDTO
				.getMahallaId().split("~");

		String district = "";
		String tehsil = "";
		String ward = "";
		String mohalla = "";

		if (districtId != null && districtId.length == 2) {
			district = districtId[0];
		}
		if (tehsilId != null && tehsilId.length == 2) {
			tehsil = tehsilId[0];
		}
		if (wardId != null && wardId.length == 2) {
			ward = wardId[0];
		}
		if (mohallaId != null && mohallaId.length == 2) {
			mohalla = mohallaId[0];
		}

		double baseValue = useDTO.getMarketValue();
		logger.debug("baseValue :-" + baseValue);
		try {
			marketValue = new IGRSCommon().getMunicipalDuty(district, tehsil,
					ward, mohalla, baseValue);

		} catch (Exception x) {
			logger.debug(x);
		}
		return marketValue;
	}*/

	// Imran's code
	/*public String[] getPresentMarketValue(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];

		String district[] = useDTO.getDistrictID().split("~");
		String tehsil[] = useDTO.getTehsilID().split("~");
		String ward[] = useDTO.getWardId().split("~");
		String mohalla[] = useDTO.getMahallaId().split("~");
		String property[] = useDTO.getPropertyTypeID().split("~");
		String propertyTypeL1Id[] = useDTO.getUsePlotId().split("~");

		String propertyTypeL2Id = null;
		String sqmeterType = null;
		double totalSqlMeter = useDTO.getTotalSqMeter();
		String noTrees = useDTO.getHdnNoOfTree();
		logger.debug("No of Trees:-" + noTrees);
		String subclause = getSubstringString(useDTO.getHdnExtSubClause());

		double baseValue = useDTO.getConsiderAmt();

		try {
			marketValue = new IGRSCommon().getPropertyValuation(district[0],
					tehsil[0], ward[0], mohalla[0], property[0],
					propertyTypeL1Id[0], propertyTypeL2Id, subclause,
					baseValue, sqmeterType, totalSqlMeter, noTrees);
			logger.debug("marketValue:-"+marketValue[0]
			                            +":"+marketValue[1]
			                            +":"+marketValue[2]);
			
			if(marketValue!=null){
				if(marketValue[0] !=null){
					if(Integer.parseInt(marketValue[0]) == 0) {
						marketValue[0] = String.valueOf(baseValue);
					}
				}
			}

		} catch (Exception x) {
			logger.debug(x);
		}
		return marketValue;
	}*/

	// Imran's code
	private String getSubstringString(String _subClause) {
		String retString = "";
		StringTokenizer rsTokenOne = new StringTokenizer(_subClause, ",");
		while (rsTokenOne.hasMoreTokens()) {
			String subone = rsTokenOne.nextToken();
			String[] rsTokenTwo = subone.split("~");
			if (retString.equals("")) {
				retString = rsTokenTwo[0];
			} else {
				retString = retString + "," + rsTokenTwo[0];
			}
		}
		return retString;
	}

	/*public String[] getBuildingMarketValue(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];

		String district[] = useDTO.getDistrictID().split("~");
		String tehsil[] = useDTO.getTehsilID().split("~");
		String ward[] = useDTO.getWardId().split("~");
		String mohalla[] = useDTO.getMahallaId().split("~");
		String property[] = useDTO.getPropertyTypeID().split("~");
		String propertyTypeL1Id[] = useDTO.getUsePlotId().split("~");
		String floorId = "'2,3'";// useDTO.getFloorID();
		String propertyTypeL2Id = useDTO.getHdnPropertyType();
		String sqmeterType = null;
		String floorSubClauseId = "'5,9;4,6'";// useDTO.getHdnExtSubClause();
		double totalSqlMeter = useDTO.getTotalSqMeter();

		String noTrees = "1";// useDTO.getHdnNoOfTree();

		logger.debug("No of Trees:-" + noTrees);

		String subclause = "'5,9;4,6'";// getSubstringString(useDTO.getHdnExtSubClause());

		double baseValue = useDTO.getConsiderAmt();
		try {
			marketValue = new IGRSCommon().getFloorPropertyValuation(
					district[0], tehsil[0], ward[0], mohalla[0], property[0],
					propertyTypeL1Id[0], propertyTypeL2Id, subclause, floorId,
					property[0], baseValue, totalSqlMeter, noTrees);

		} catch (Exception x) {
			logger.debug(x);
		}
		return marketValue;
	}*/

}
