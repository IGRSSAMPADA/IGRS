package com.wipro.igrs.newPropvaluationefiling.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newPropvaluationefiling.action.CalculationAgriLand;
import com.wipro.igrs.newPropvaluationefiling.constant.PropertyValuationConstant;
import com.wipro.igrs.newPropvaluationefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.newPropvaluationefiling.dto.TreeDTO;
import com.wipro.igrs.newPropvaluationefiling.form.PropertyValuationForm;
import com.wipro.igrs.newPropvaluationefiling.sql.PropertyValuationSQL;

public class PropertyValuationDAO {

	DBUtility dbUtil = null;
	CalculationAgriLand cal;
	private static Logger logger = org.apache.log4j.Logger.getLogger(PropertyValuationDAO.class);
	public PropertyValuationDAO() throws Exception {
		// dbUtil = new DBUtility();

	}
	public ArrayList getTehsil(String language, String districtId) {

		ArrayList tehsilList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.TEHSIL_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.TEHSIL_NAME_HI);
			}
			tehsilList = dbUtil.executeQuery(new String[]{districtId});

		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return tehsilList;

	}
	public ArrayList getArea(String language) {
		ArrayList areaList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			if (language.equalsIgnoreCase("en")) {
				areaList = dbUtil.executeQuery(PropertyValuationSQL.AREA_NAME_EN);
			} else {
				areaList = dbUtil.executeQuery(PropertyValuationSQL.AREA_NAME_HI);
			}

			return areaList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getArea2(String language, String instId) {
		ArrayList areaList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			if (instId.equals("2107"))// partition of agri land
			{
				if (language.equalsIgnoreCase("en")) {
					areaList = dbUtil.executeQuery(PropertyValuationSQL.AREA_NAME_EN_PARTN);
				} else {
					areaList = dbUtil.executeQuery(PropertyValuationSQL.AREA_NAME_HI_PARTN);
				}
			} else {
				if (language.equalsIgnoreCase("en")) {
					areaList = dbUtil.executeQuery(PropertyValuationSQL.AREA_NAME_EN);
				} else {
					areaList = dbUtil.executeQuery(PropertyValuationSQL.AREA_NAME_HI);
				}
			}
			return areaList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getSubArea(String language, String areaId, String tehsilId, String urbanFlag) {
		ArrayList subAreaList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				if ("Y".equalsIgnoreCase(urbanFlag)) {
					dbUtil.createPreparedStatement(PropertyValuationSQL.SUB_AREA_NAME_EN_UR);
					subAreaList = dbUtil.executeQuery(new String[]{areaId, tehsilId});

				} else {
					dbUtil.createPreparedStatement(PropertyValuationSQL.SUB_AREA_NAME_EN);
					subAreaList = dbUtil.executeQuery(new String[]{areaId});
				}
			} else {
				if ("Y".equalsIgnoreCase(urbanFlag)) {
					dbUtil.createPreparedStatement(PropertyValuationSQL.SUB_AREA_NAME_HI_UR);
					subAreaList = dbUtil.executeQuery(new String[]{areaId, tehsilId});
				} else {
					dbUtil.createPreparedStatement(PropertyValuationSQL.SUB_AREA_NAME_HI);
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
				logger.error(e);

			}

		}

	}
	public ArrayList getWardPatwari(String language, String subAreaId, String tehsilId) {
		ArrayList wardPatwariList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.WARD_PATWARI_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.WARD_PATWARI_NAME_HI);
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
				logger.error(e);

			}

		}
	}
	public String getDistrictName(String language, String districtId) {
		String districtName = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.DISTRICT_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.DISTRICT_NAME_HI);
			}
			districtName = dbUtil.executeQry(new String[]{districtId});
			return districtName;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public String getDistrictClrFlag(String language, String districtId) {
		String clrFlag = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.DISTRICT_CLR_FLAG);

			clrFlag = dbUtil.executeQry(new String[]{districtId});
			return clrFlag;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public String getTehsilClrFlag(String language, String tehsilID) {
		String clrFlag = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.TEHSIL_CLR_FLAG);

			clrFlag = dbUtil.executeQry(new String[]{tehsilID});
			return clrFlag;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public String censusId(String colonyId) {
		String clrFlag = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.CENSUS_CLR_CODE);

			clrFlag = dbUtil.executeQry(new String[]{colonyId});
			return clrFlag;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public ArrayList getPropertyType(String language, String instId) {
		ArrayList propertyList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			if (language.equalsIgnoreCase("en")) {
				if ("2107".equalsIgnoreCase(instId))// partition of agri land
				{
					propertyList = dbUtil.executeQuery(PropertyValuationSQL.PROPERTY_TYPE_NAME_EN_AGRI);

				} else {
					propertyList = dbUtil.executeQuery(PropertyValuationSQL.PROPERTY_TYPE_NAME_EN);
				}
			} else {
				if ("2107".equalsIgnoreCase(instId)) {
					propertyList = dbUtil.executeQuery(PropertyValuationSQL.PROPERTY_TYPE_NAME_HI_AGRI);

				} else {
					propertyList = dbUtil.executeQuery(PropertyValuationSQL.PROPERTY_TYPE_NAME_HI);
				}
			}

			return propertyList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getBuildingType(String language) {
		ArrayList buildingTypeList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			if (language.equalsIgnoreCase("en")) {
				buildingTypeList = dbUtil.executeQuery(PropertyValuationSQL.BUILDING_TYPE_NAME_EN);
			} else {
				buildingTypeList = dbUtil.executeQuery(PropertyValuationSQL.BUILDING_TYPE_NAME_HI);
			}

			return buildingTypeList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getL1Name(String language, PropertyValuationDTO propDTO1) {
		ArrayList l1NameList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.L1_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.L1_NAME_HI);
			}
			l1NameList = dbUtil.executeQuery(new String[]{propDTO1.getPropertyId(), propDTO1.getBuildingTypeId()});
			return l1NameList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getMultiCommPropName(String language, String type) {
		ArrayList propTypeList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			if (language.equalsIgnoreCase("en")) {
				if (type.equalsIgnoreCase("comm")) {
					propTypeList = dbUtil.executeQuery(PropertyValuationSQL.MULTI_COMM_PROP_EN_COMM);
				} else {
					propTypeList = dbUtil.executeQuery(PropertyValuationSQL.MULTI_COMM_PROP_EN_OTHER);
				}
			} else {
				if (type.equalsIgnoreCase("comm")) {
					propTypeList = dbUtil.executeQuery(PropertyValuationSQL.MULTI_COMM_PROP_HI_COMM);
				} else {
					propTypeList = dbUtil.executeQuery(PropertyValuationSQL.MULTI_COMM_PROP_HI_OTHER);
				}
			}

			return propTypeList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getL2Name(String language, String l1Id) {
		ArrayList l2NameList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.L2_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.L2_NAME_HI);
			}
			l2NameList = dbUtil.executeQuery(new String[]{l1Id});
			return l2NameList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getFloorName(String language, String string) {
		ArrayList l2NameList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.FLOOR_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.FLOOR_NAME_HI);
			}
			l2NameList = dbUtil.executeQuery(new String[]{string});
			return l2NameList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getColonyName(String language, String wardPatwariId) {
		ArrayList l2NameList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.COLONY_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.COLONY_NAME_HI);
			}
			l2NameList = dbUtil.executeQuery(new String[]{wardPatwariId.split("~")[1]});
			return l2NameList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public ArrayList getColonyName2(String language, String wardPatwariId, String instId) {
		ArrayList l2NameList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			if (instId.equals("2107"))// partition of agri land
			{
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(PropertyValuationSQL.COLONY_NAME_EN_PARTN);
				} else {
					dbUtil.createPreparedStatement(PropertyValuationSQL.COLONY_NAME_HI_PARTN);
				}
			} else {
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(PropertyValuationSQL.COLONY_NAME_EN);
				} else {
					dbUtil.createPreparedStatement(PropertyValuationSQL.COLONY_NAME_HI);
				}
			}
			l2NameList = dbUtil.executeQuery(new String[]{wardPatwariId.split("~")[1]});
			return l2NameList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public String getValuationId() {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			String val_id = dbUtil.executeQry(PropertyValuationSQL.GET_VALUATION_ID);
			return val_id;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public String getBuildingId() {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			String val_id = dbUtil.executeQry(PropertyValuationSQL.GET_BUILDING_ID);
			return val_id;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public String getFloorTxnId() {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			String val_id = dbUtil.executeQry(PropertyValuationSQL.GET_FLOOR_TXN_ID);
			return val_id;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public boolean submitValuationMain(PropertyValuationDTO propDTO) {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String[] arr = null;
			dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_VALUATION_DETAILS);
			// Modified By Gulrej on 29th Aug, 2017 -- Start
			if (propDTO.getOpenTerraceArea() != null && propDTO.getOpenTerraceArea().trim() != "") {
				arr = new String[]{propDTO.getValuationId(), propDTO.getDistrictId(), propDTO.getTehsilId(), propDTO.getAreaId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getPropertyId(), propDTO.getGuideLineId(), propDTO.getGuideLineValue(), propDTO.getFinalMarketValue(), propDTO.getUserId(), propDTO.getMunicipalFlag(), propDTO.getOpenTerraceArea()};
			} else if (propDTO.getFloorArea() != null && propDTO.getFloorArea().trim() != "") {
				arr = new String[]{propDTO.getValuationId(), propDTO.getDistrictId(), propDTO.getTehsilId(), propDTO.getAreaId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getPropertyId(), propDTO.getGuideLineId(), propDTO.getGuideLineValue(), propDTO.getFinalMarketValue(), propDTO.getUserId(), propDTO.getMunicipalFlag(), propDTO.getFloorArea()};
			} else if (propDTO.getPlotTotArea() != null && propDTO.getPlotTotArea().trim() != "") {
				arr = new String[]{propDTO.getValuationId(), propDTO.getDistrictId(), propDTO.getTehsilId(), propDTO.getAreaId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getPropertyId(), propDTO.getGuideLineId(), propDTO.getGuideLineValue(), propDTO.getFinalMarketValue(), propDTO.getUserId(), propDTO.getMunicipalFlag(), propDTO.getPlotTotArea()};
			}
			// Modified By Gulrej on 29th Aug, 2017 -- End
			return dbUtil.executeUpdate(arr);
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public boolean submitOpenTerraceDetails(PropertyValuationDTO propDTO) {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			boolean flag1 = false;
			String valuationId;
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				flag1 = true;
				valuationId = "";
			} else {
				flag1 = submitValuationMain(propDTO);
				valuationId = propDTO.getValuationId();
			}
			boolean flag2 = false;
			if (flag1) {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_OPEN_TERRACE_DETAILS);
				String[] arr = new String[]{propDTO.getBuildingTxnId(), valuationId, propDTO.getBuildingTypeId(), propDTO.getOpenTerraceArea(), propDTO.getOpenTerraceUsageId(), propDTO.getUserId()};
				flag2 = dbUtil.executeUpdate(arr);
			}
			if (flag1 && flag2) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public boolean submitIndependentFloorDetails(PropertyValuationDTO propDTO) {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			String older20 = "N";
			String older50 = "N";
			boolean flag1 = false;
			String valuationId;

			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				flag1 = true;
				valuationId = "";
			} else {
				flag1 = submitValuationMain(propDTO);
				valuationId = propDTO.getValuationId();
			}
			boolean flag2 = false;
			if (flag1) {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_INDEPENDENT_FLOOR);
				if ("20".equalsIgnoreCase(propDTO.getOlder())) {
					older20 = "Y";

				} else if ("50".equalsIgnoreCase(propDTO.getOlder())) {
					older50 = "Y";

				}

				String[] arr = new String[]{propDTO.getBuildingTxnId(), valuationId, propDTO.getBuildingTypeId(), propDTO.getFloorArea(), propDTO.getFloorId(), older20, older50, propDTO.getIsLift(), "YES".equalsIgnoreCase(propDTO.getIsOpenTerraceFlag()) ? "Y" : "N", propDTO.getOpenTerraceArea(), propDTO.getOpenTerraceUsageId(), propDTO.getUserId()};
				flag2 = dbUtil.executeUpdate(arr);
			}
			if (flag1 && flag2) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public boolean submitMultiStoreyDetails(PropertyValuationDTO propDTO, String userId) {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			String older20 = "N";
			String older50 = "N";
			boolean flag1 = false;
			String valuationId;

			String isConstruct = propDTO.getIsConstruction();
			if ("construct".equalsIgnoreCase(isConstruct)) {
				flag1 = true;
				valuationId = "";
			} else {
				flag1 = submitValuationMain(propDTO);
				valuationId = propDTO.getValuationId();
			}
			boolean flag2 = false;
			if (flag1) {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_MULTISTOREY_DETAILS);
				if ("20".equalsIgnoreCase(propDTO.getOlder())) {
					older20 = "Y";
				} else if ("50".equalsIgnoreCase(propDTO.getOlder())) {
					older50 = "Y";
				}

				String[] arr = new String[]{propDTO.getBuildingTxnId(), valuationId, propDTO.getBuildingTypeId(), propDTO.getFloorArea(), propDTO.getBuiltUpArea(), propDTO.getCommonArea(), propDTO.getFloorId(), older20, older50, propDTO.getIsLift(), "YES".equalsIgnoreCase(propDTO.getIsOpenTerraceFlag()) ? "Y" : "N", propDTO.getOpenTerraceArea(), propDTO.getOpenTerraceUsageId(), propDTO.getMultiCommPropId(), propDTO.getIsLift(), propDTO.getNearRoad(), propDTO.getUserId(), propDTO.getMultiStoreyTypeId()};
				flag2 = dbUtil.executeUpdate(arr);
			}
			if (flag1 && flag2) {
				if (propDTO.getMultiStoreyTypeId().equalsIgnoreCase("18")) // insert
																			// subclause
																			// only
																			// in
																			// case
																			// of
																			// residential
					return insertSubclauseValues(propDTO, valuationId, userId);
				else
					return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			try {
				dbUtil = new DBUtility();
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public boolean submitIndependentBuildingDetails(PropertyValuationDTO propDTO) {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// transaction management by Roopam :- 12 feb 2014
			boolean boo = false;
			boolean flag1 = false;
			String valuationId;

			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				flag1 = true;
				valuationId = "";
			} else {
				flag1 = submitValuationMain(propDTO);
				valuationId = propDTO.getValuationId();
			}
			boolean flag2 = true;

			if (flag1) {
				String buildingID = propDTO.getBuildingTxnId();
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_BUILDING_DETAILS);
				String[] arr = new String[]{buildingID, valuationId, propDTO.getBuildingTypeId(), propDTO.getPlotTotArea(), propDTO.getPlotResiArea(), propDTO.getPlotCommArea(), propDTO.getPlotIndusArea(), propDTO.getPlotSchoolArea(), propDTO.getPlotHealthArea(), propDTO.getPlotOtherArea(), propDTO.getIsTncpSchool(), propDTO.getIsTncpHealth(), propDTO.getPlotCost(), propDTO.getConstructionCost(), propDTO.getUserId(), propDTO.getIsAkvnFlag(), propDTO.getIsSuperConstructionFlag(), propDTO.getHousingCheckFlag(), propDTO.getResiCommArea()};
				boo = dbUtil.executeUpdate(arr);
				if (boo) {
					for (int i = 0; i < propDTO.getFloorMarketValueList().size(); i++) {
						PropertyValuationDTO floorDTO = propDTO.getFloorMarketValueList().get(i);

						String floorNo = floorDTO.getFloorNo();
						String floortxnid = getFloorTxnId();
						dbUtil = new DBUtility();
						dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_FLOOR_TXN_DETAILS);
						String[] arr1 = new String[]{floortxnid, buildingID, floorDTO.getFloorId(), String.valueOf(floorDTO.getFloorValue()), propDTO.getUserId()};
						boo = dbUtil.executeUpdate(arr1);
						if (boo) {
							if (propDTO.getResiFloorAreaList() != null && propDTO.getResiFloorAreaList().size() > 0) {
								PropertyValuationDTO resiDTO = propDTO.getResiFloorAreaList().get(i);
								dbUtil = new DBUtility();
								dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_FLOOR_AREA_DETAILS);
								String[] arr2 = new String[]{floortxnid, "7", resiDTO.getRccArea(), resiDTO.getRbcArea(), resiDTO.getTinArea(), resiDTO.getKacchaArea(), resiDTO.getShopArea(), resiDTO.getOfficeArea(), resiDTO.getGodownArea(), propDTO.getUserId()};
								boo = dbUtil.executeUpdate(arr2);
								if (!boo) {
									dbUtil.rollback();
									break;
								}
							}
							if (propDTO.getCommFloorAreaList() != null && propDTO.getCommFloorAreaList().size() > 0) {
								PropertyValuationDTO commDTO = propDTO.getCommFloorAreaList().get(i);
								dbUtil = new DBUtility();
								dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_FLOOR_AREA_DETAILS);
								String[] arr2 = new String[]{floortxnid, "8", commDTO.getRccArea(), commDTO.getRbcArea(), commDTO.getTinArea(), commDTO.getKacchaArea(), commDTO.getShopArea(), commDTO.getOfficeArea(), commDTO.getGodownArea(), propDTO.getUserId()};
								boo = dbUtil.executeUpdate(arr2);
								if (!boo) {
									dbUtil.rollback();
									break;
								}
							}
							if (propDTO.getIndFloorAreaList() != null && propDTO.getIndFloorAreaList().size() > 0) {
								PropertyValuationDTO indDTO = propDTO.getIndFloorAreaList().get(i);
								dbUtil = new DBUtility();
								dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_FLOOR_AREA_DETAILS);
								String[] arr2 = new String[]{floortxnid, "10", indDTO.getRccArea(), indDTO.getRbcArea(), indDTO.getTinArea(), indDTO.getKacchaArea(), indDTO.getShopArea(), indDTO.getOfficeArea(), indDTO.getGodownArea(), propDTO.getUserId()};
								boo = dbUtil.executeUpdate(arr2);
								if (!boo) {
									dbUtil.rollback();
									break;
								}
							}
							if (propDTO.getSchoolFloorAreaList() != null && propDTO.getSchoolFloorAreaList().size() > 0) {
								PropertyValuationDTO schoolDTO = propDTO.getSchoolFloorAreaList().get(i);
								dbUtil = new DBUtility();
								dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_FLOOR_AREA_DETAILS);
								String[] arr2 = new String[]{floortxnid, "12", schoolDTO.getRccArea(), schoolDTO.getRbcArea(), schoolDTO.getTinArea(), schoolDTO.getKacchaArea(), schoolDTO.getShopArea(), schoolDTO.getOfficeArea(), schoolDTO.getGodownArea(), propDTO.getUserId()};
								boo = dbUtil.executeUpdate(arr2);
								if (!boo) {
									dbUtil.rollback();
									break;
								}
							}
							if (propDTO.getHealthFloorAreaList() != null && propDTO.getHealthFloorAreaList().size() > 0) {
								PropertyValuationDTO healthDTO = propDTO.getHealthFloorAreaList().get(i);
								dbUtil = new DBUtility();
								dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_FLOOR_AREA_DETAILS);
								String[] arr2 = new String[]{floortxnid, "11", healthDTO.getRccArea(), healthDTO.getRbcArea(), healthDTO.getTinArea(), healthDTO.getKacchaArea(), healthDTO.getShopArea(), healthDTO.getOfficeArea(), healthDTO.getGodownArea(), propDTO.getUserId()};
								boo = dbUtil.executeUpdate(arr2);
								if (!boo) {
									dbUtil.rollback();
									break;
								}
							}
							if (propDTO.getOtherFloorAreaList() != null && propDTO.getOtherFloorAreaList().size() > 0) {
								PropertyValuationDTO otherDTO = propDTO.getOtherFloorAreaList().get(i);
								dbUtil = new DBUtility();
								dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_FLOOR_AREA_DETAILS);
								String[] arr2 = new String[]{floortxnid, "13", otherDTO.getRccArea(), otherDTO.getRbcArea(), otherDTO.getTinArea(), otherDTO.getKacchaArea(), otherDTO.getShopArea(), otherDTO.getOfficeArea(), otherDTO.getGodownArea(), propDTO.getUserId()};
								boo = dbUtil.executeUpdate(arr2);
								if (!boo) {
									dbUtil.rollback();
									break;
								}
							}
							if (propDTO.getResiCommFloorAreaList() != null && propDTO.getResiCommFloorAreaList().size() > 0) {
								PropertyValuationDTO resiCommDTO = propDTO.getResiCommFloorAreaList().get(i);
								dbUtil = new DBUtility();
								dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_FLOOR_AREA_DETAILS);
								String[] arr2 = new String[]{floortxnid, "32", resiCommDTO.getRccArea(), resiCommDTO.getRbcArea(), resiCommDTO.getTinArea(), resiCommDTO.getKacchaArea(), resiCommDTO.getShopArea(), resiCommDTO.getOfficeArea(), resiCommDTO.getGodownArea(), propDTO.getUserId()};
								boo = dbUtil.executeUpdate(arr2);
								if (!boo) {
									dbUtil.rollback();
									break;
								}
							}
						} else {
							dbUtil.rollback();
						}

					}

					if (boo) {

						boo = insertSubclauseValues(propDTO, valuationId, propDTO.getUserId());

					} else {
						dbUtil = new DBUtility();
						dbUtil.rollback();
					}

				} else {
					dbUtil = new DBUtility();
					dbUtil.rollback();
				}
				if (!boo) {
					flag2 = false;
				}
			} else {
				dbUtil = new DBUtility();
				dbUtil.rollback();
			}

			if (flag1 && flag2) {
				dbUtil = new DBUtility();
				dbUtil.commit();
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			try {
				dbUtil = new DBUtility();
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getGuideLineValueOpenTerrace(PropertyValuationDTO propDTO) {
		ArrayList guidelineValues = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.OPEN_TERRACE_GUIDELINE_VALUE);
			guidelineValues = dbUtil.executeQuery(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], propDTO.getPropertyId(), propDTO.getOpenTerraceUsageId()});
			return guidelineValues;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}
	public ArrayList getGuideLineValueIndependentFloor(PropertyValuationDTO propDTO) {
		ArrayList guidelineValues = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.INDEPENDENT_FLOOR_GUIDELINE_VALUE);
			guidelineValues = dbUtil.executeQuery(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], propDTO.getPropertyId(), "7", "1"});
			return guidelineValues;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getGuideLineValueMultiResiFloor(PropertyValuationDTO propDTO) {
		ArrayList guidelineValues = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.MULTI_RESI_GUIDELINE_VALUE);
			guidelineValues = dbUtil.executeQuery(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], "2", "18"});
			if (guidelineValues == null) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.INDEPENDENT_FLOOR_GUIDELINE_VALUE);
				guidelineValues = dbUtil.executeQuery(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], propDTO.getPropertyId(), "7", "1"});
			} else {
				ArrayList subList = (ArrayList) guidelineValues.get(0);
				String rate = (String) subList.get(1);
				if (rate.equalsIgnoreCase("") || Double.parseDouble(rate) == 0) {
					dbUtil.createPreparedStatement(PropertyValuationSQL.INDEPENDENT_FLOOR_GUIDELINE_VALUE);
					guidelineValues = dbUtil.executeQuery(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], propDTO.getPropertyId(), "7", "1"});

				}
			}
			logger.debug("getGuideLineValueMultiResiFloor DAO guidelineValue" + guidelineValues);
			return guidelineValues;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public String getFloorRebate(String floorId, String l1Id) {
		String rebate = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.FLOOR_REBATE);
			rebate = dbUtil.executeQry(new String[]{floorId, l1Id});
			return rebate;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	// --added by satbir kumar for agriLand--

	public ArrayList getAgriLandType(String language, String deedId, String instId) {
		ArrayList agriLandTypeList = null;

		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			if (language.equalsIgnoreCase("en")) {
				if ("1092".equalsIgnoreCase(deedId)) {
					agriLandTypeList = dbUtil.executeQuery(PropertyValuationSQL.AGRILAND_TYPE_NAME_EN_LEASE);
				} else if ("2107".equalsIgnoreCase(instId)) {
					agriLandTypeList = dbUtil.executeQuery(PropertyValuationSQL.AGRILAND_TYPE_NAME_EN_AGI);

				} else {
					agriLandTypeList = dbUtil.executeQuery(PropertyValuationSQL.AGRILAND_TYPE_NAME_EN);
				}
			} else {
				if ("1092".equalsIgnoreCase(deedId)) {
					agriLandTypeList = dbUtil.executeQuery(PropertyValuationSQL.AGRILAND_TYPE_NAME_HI_LEASE);
				} else if ("2107".equalsIgnoreCase(instId)) {
					agriLandTypeList = dbUtil.executeQuery(PropertyValuationSQL.AGRILAND_TYPE_NAME_HI_AGI);

				} else {
					agriLandTypeList = dbUtil.executeQuery(PropertyValuationSQL.AGRILAND_TYPE_NAME_HI);
				}
			}

			return agriLandTypeList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public ArrayList getAgriTreeType(String language) {
		ArrayList agriTreeTypeList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			if (language.equalsIgnoreCase("en")) {
				agriTreeTypeList = dbUtil.executeQuery(PropertyValuationSQL.AGRILAND_TREE_NAME_EN);
			} else {
				agriTreeTypeList = dbUtil.executeQuery(PropertyValuationSQL.AGRILAND_TREE_NAME_HI);
			}

			return agriTreeTypeList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}
	public ArrayList getAgriSubTreeGrt45Type(String language, String treeCategoryId) {
		ArrayList agriSubTreeTypeGrt45List = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.AGRILAND_SUB_TREE_GRT45_NAME_EN);

			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.AGRILAND_SUB_TREE_GRT45_NAME_HI);

			}
			agriSubTreeTypeGrt45List = dbUtil.executeQuery(new String[]{treeCategoryId});
			return agriSubTreeTypeGrt45List;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}

	public String getPlotId() throws Exception {
		String plotId = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = PropertyValuationSQL.SELECT_PLOT_ID;
		// String sqlDate=PropertyValuationSQL.PLOT_SYS_DATE;

		dbUtil.createStatement();

		String seq = dbUtil.executeQry(sql);
		// String currDate=dbUtil.executeQry(sqlDate);
		// plotId="PLOT".concat(currDate).concat(seq);
		System.out.println(plotId);

		return seq;

	}

	public ArrayList getAgriSubTreeles45Type(String language, String treeCategoryId) {

		ArrayList agriSubTreeTypeles45List = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			dbUtil.createStatement();
			if (language.equalsIgnoreCase("en")) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.AGRILAND_SUB_TREE_LES45_NAME_EN);

			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.AGRILAND_SUB_TREE_LES45_NAME_HI);

			}
			agriSubTreeTypeles45List = dbUtil.executeQuery(new String[]{treeCategoryId});
			return agriSubTreeTypeles45List;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public boolean insertBuyerTreeDetails(TreeDTO dto, PropertyValuationForm propForm, String language, String userId) throws Exception {

		boolean Insert = false;
		String sql = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sql = PropertyValuationSQL.GET_VALUATION_ID;
			dbUtil.createStatement();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String valTxnId = dbUtil.executeQry(sql);

		propForm.getPropDTO().setAgriValTxnId(valTxnId);

		String arrAgriTxnDetls[] = new String[19];

		arrAgriTxnDetls[0] = valTxnId;//propForm.getPropDTO().getAgriMainValTxnId
										// ();
		arrAgriTxnDetls[1] = propForm.getPropDTO().getDistrictId();
		arrAgriTxnDetls[2] = propForm.getPropDTO().getTehsilId();
		arrAgriTxnDetls[3] = propForm.getPropDTO().getAreaId();
		arrAgriTxnDetls[4] = propForm.getPropDTO().getSubAreaId();
		arrAgriTxnDetls[5] = propForm.getPropDTO().getAgriWardPatwariId();
		arrAgriTxnDetls[6] = propForm.getPropDTO().getAgriColonyid();
		arrAgriTxnDetls[7] = propForm.getPropDTO().getPropertyId();
		arrAgriTxnDetls[8] = propForm.getPropDTO().getAgriLandTypeId();
		arrAgriTxnDetls[9] = propForm.getPropDTO().getGuideLineId();
		if (propForm.getPropDTO().getAreMoreBuyers().equalsIgnoreCase("morebuyers"))

		{
			arrAgriTxnDetls[10] = "N";
		} else {
			arrAgriTxnDetls[10] = "Y";
		}

		if (propForm.getPropDTO().getAreMoreBuyers().equalsIgnoreCase("morebuyers") && propForm.getPropDTO().getFamily().equalsIgnoreCase("samefamily")) {

			arrAgriTxnDetls[11] = "Y";
		}

		else {

			arrAgriTxnDetls[11] = "N";
		}

		if (propForm.getPropDTO().getNoOfBuyers().equalsIgnoreCase("") || propForm.getPropDTO().getNoOfBuyers().equalsIgnoreCase(null)) {

			arrAgriTxnDetls[12] = "0";
		} else {
			arrAgriTxnDetls[12] = propForm.getPropDTO().getNoOfBuyers();

		}
		if (propForm.getPropDTO().getAgriTotalTreeValue() == null || propForm.getPropDTO().getAgriTotalTreeValue().equalsIgnoreCase("0.0") || propForm.getPropDTO().getAgriTotalTreeValue().equalsIgnoreCase("")) {
			arrAgriTxnDetls[13] = "N";
		}

		else {
			arrAgriTxnDetls[13] = "Y";
		}

		arrAgriTxnDetls[14] = propForm.getPropDTO().getAgriMainValTxnId();
		arrAgriTxnDetls[15] = userId;
		if (propForm.getPropDTO().getAgriDiscloseShare().equalsIgnoreCase("openshare"))

		{
			arrAgriTxnDetls[16] = "Y";
		} else {
			arrAgriTxnDetls[16] = "N";
		}
		arrAgriTxnDetls[17] = propForm.getPropDTO().getMunicipalFlag();

		// Modified By Gulrej on 29th Aug, 2017-- Start
		if (propForm.getPropDTO().getAgriTotalUnDivertedArea() != null && propForm.getPropDTO().getAgriTotalUnDivertedArea().trim() != "") {
			arrAgriTxnDetls[18] = propForm.getPropDTO().getAgriTotalUnDivertedArea();
		} else if (propForm.getPropDTO().getAgriTotalDivertedArea() != null && propForm.getPropDTO().getAgriTotalDivertedArea().trim() != "") {
			arrAgriTxnDetls[18] = propForm.getPropDTO().getAgriTotalDivertedArea();
		}
		// Modified By Gulrej on 29th Aug, 2017-- End
		try {
			dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_AGRI_TXN_DETLS);

			boolean updated = dbUtil.executeUpdate(arrAgriTxnDetls);

			if (updated == true) {
				logger.debug("updated first table");
			} else {
				logger.debug("cannot updated first table");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int clrLoop=1;
		for (int i = 0; i < propForm.getAgriAddBuyerList().size(); i++) {
			PropertyValuationDTO buyerDTO = propForm.getAgriAddBuyerList().get(i);

			if (buyerDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
				sql = PropertyValuationSQL.GET_AGRI_TXN_ID;
				try {
					dbUtil.createStatement();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String agriTxnId = dbUtil.executeQry(sql);
				String arrUndivBuyerDetls[] = new String[13];
				arrUndivBuyerDetls[0] = agriTxnId;
				arrUndivBuyerDetls[1] = valTxnId;
				arrUndivBuyerDetls[2] = buyerDTO.getAgriLandTypeId();
				arrUndivBuyerDetls[3] = buyerDTO.getAgriTotalUnDivertedArea();
				arrUndivBuyerDetls[4] = buyerDTO.getAgriUndivSingleCropArea();
				arrUndivBuyerDetls[5] = buyerDTO.getAgriUndivDoubleCropArea();
				arrUndivBuyerDetls[6] = buyerDTO.getAgriUndivIrrigatedArea();
				if ("construct".equalsIgnoreCase(buyerDTO.getIsConstruction()))

				{

					arrUndivBuyerDetls[7] = "Y";

				}

				else {
					arrUndivBuyerDetls[7] = "N";
				}
				arrUndivBuyerDetls[8] = buyerDTO.getAgriUndivertedValue();
				arrUndivBuyerDetls[9] = userId;
				if (buyerDTO.getAgriBuildingConstructArea() == null || buyerDTO.getAgriBuildingConstructArea().equalsIgnoreCase("")) {
					buyerDTO.setAgriBuildingConstructArea("0.0");
				}
				arrUndivBuyerDetls[10] = String.valueOf(Double.parseDouble(buyerDTO.getAgriBuildingConstructArea()) / 10000);
				arrUndivBuyerDetls[11] = buyerDTO.getAgriBuildingConstructCost();
				arrUndivBuyerDetls[12] = buyerDTO.getIsConstructionKhasraNo();
				try {
					dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_AGRI_UNDIV_BUYER_DETLS);

					boolean updated = dbUtil.executeUpdate(arrUndivBuyerDetls);

					// Added by Rakesh: Start
					if (updated == true
							&& propForm.getPropDTO().getClrFlag() != null && clrLoop==1)  {
						if (propForm.getPropDTO().getPropertyId()
								.equalsIgnoreCase("3")
								&& propForm.getPropDTO().getClrFlag()
										.equalsIgnoreCase("Y")) {
							for (int j = 0; j < propForm.getSampadaClrKhasraList().size(); j++) {
								dbUtil
								.createPreparedStatement(PropertyValuationSQL.INSERT_UNDIV_AGRI_KHASRA_CLR_DETLS);

						String khasraClrArray[] = new String[28];						
						sql = PropertyValuationSQL.GET_KHASRA_TXN_ID;
						dbUtil.createStatement();
						logger.debug("Khasra No DAO"+ propForm.getClrDto().getKhasraNoClr());
						String khasraTxnID = dbUtil.executeQry(sql);
						khasraClrArray[0] = khasraTxnID;
						khasraClrArray[1] = valTxnId;
						//khasraClrArray[2] = propForm.getClrDto().getKhasraNoClr();
						//khasraClrArray[2] = propForm.getSmapadaClrDTO()getKhasraNoClr();
						khasraClrArray[2] = propForm.getSampadaClrKhasraList().get(j)
								.getKhasraNoClr();						
						khasraClrArray[3] = propForm.getSampadaClrKhasraList().get(j)
								.getClrKhasraId();
						khasraClrArray[4] = propForm.getSampadaClrKhasraList().get(j)
								.getKhasraTotalArea();
						khasraClrArray[5] = propForm.getSampadaClrKhasraList().get(j)
								.getLandIrriOrNot();
						khasraClrArray[6] = propForm.getSampadaClrKhasraList().get(j)
								.getIrrigatedArea();
						khasraClrArray[7] = propForm.getSampadaClrKhasraList().get(j)
								.getLandUnIrriOrNot();

						//khasraClrArray[8] = propForm.getClrDto().getNonIrrigatedArea();
						khasraClrArray[8] = propForm.getSampadaClrKhasraList().get(j)
								.getSingleCropArea();
						khasraClrArray[9] = propForm.getSampadaClrKhasraList().get(j)
								.getLandUnirriDouble();
						khasraClrArray[10] = propForm.getSampadaClrKhasraList().get(j)
								.getDoubleCropArea();
						khasraClrArray[11] = propForm.getSampadaClrKhasraList().get(j)
								.getPadtiFlag();
						khasraClrArray[12] = propForm.getSampadaClrKhasraList().get(j)
								.getPadtiArea();
						khasraClrArray[13] = propForm.getSampadaClrKhasraList().get(j)
								.getOwnership();
						khasraClrArray[14] = propForm.getSampadaClrKhasraList().get(j)
								.getNohiyat();
						khasraClrArray[15] = propForm.getSampadaClrKhasraList().get(j)
								.getCurrentStatus();
						khasraClrArray[16] = propForm.getSampadaClrKhasraList().get(j)
								.getKehfiyat();

						khasraClrArray[17] = propForm.getSampadaClrKhasraList().get(j)
								.getClrFlag();

						//khasraClrArray[18] = propForm.getSampadaArea();
						khasraClrArray[18] = String.valueOf(propForm.getSampadaClrKhasraList().get(j).getTotalSaleableArea());//Ajit 

						khasraClrArray[19] = propForm.getSampadaClrKhasraList().get(j)
								.getMortgage();
						khasraClrArray[20] = userId;
						//Sampada Data
						khasraClrArray[21] =propForm.getSampadaClrKhasraList().get(j).getCensusCode();
						khasraClrArray[22] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaSingleCrop());
						khasraClrArray[23] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaDoubleCrop());
						khasraClrArray[24] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaIrrigated());						
						khasraClrArray[25] =propForm.getPropDTO().getRinPustikaNoClr();
						//Below change by Ajit for CLR for Undiverted Agriland Bug 10 start 
						String totalSaleableArea=String.valueOf(propForm.getSampadaClrKhasraList().get(j).getTotalSaleableArea());
						if(totalSaleableArea.length()>5 && totalSaleableArea.contains(".")){
							 totalSaleableArea=String.valueOf(propForm.getSampadaClrKhasraList().get(j).getTotalSaleableArea()).substring(0, 5);
							 logger.debug("CLR Khasra khasra Data insertion totalSaleableArea"+ totalSaleableArea);
						}
						khasraClrArray[26] = totalSaleableArea;//String.valueOf(propForm.getSampadaClrKhasraList().get(j).getTotalSaleableArea());
						//change by Ajit for CLR for Undiverted Agriland Bug 10 start
						khasraClrArray[27] ="Y";
						boolean flag=dbUtil.executeUpdate(khasraClrArray);
					logger.debug("CLR Khasra khasra Data insertion flag"+ flag);
					
					if(flag== true){
						dbUtil.commit();
					}else{
						dbUtil.rollback();
					}
							}
							
							
							propForm.setSampadaArea("");

							//boolean updatedNew = dbUtil	.executeUpdate(khasraClrArray);
						}

					}
					// Added by Rakesh: End

					if (updated == true) {
						logger.debug("updated undiv buyer details");

						if ("construct".equalsIgnoreCase(buyerDTO.getIsConstruction())) {
							String arrUndivBuyerConstructDetls[] = new String[3];

							arrUndivBuyerConstructDetls[0] = agriTxnId;
							arrUndivBuyerConstructDetls[1] = valTxnId;
							arrUndivBuyerConstructDetls[2] = buyerDTO.getAgriBuildingConstructTxnId();

							try {
								dbUtil.createPreparedStatement(PropertyValuationSQL.UPDATE_AGRI_CONSTRUCTION_DETLS);

								boolean updated1 = dbUtil.executeUpdate(arrUndivBuyerConstructDetls);

								if (updated1 == true) {
									logger.debug("updated undiv buyer construction details");
									String arrupdatepropsubclause[] = new String[1];

									arrupdatepropsubclause[0] = agriTxnId;

									try {
										dbUtil.createPreparedStatement(PropertyValuationSQL.UPDATE_PROP_SUB_CLAUSE_DETLS);

										boolean updated2 = dbUtil.executeUpdate(arrupdatepropsubclause);
										if (updated2 == true) {
											logger.debug("updated prop subclause table");
										} else {
											logger.debug("cannot updated prop subclause table");
										}
									}

									catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

								} else {
									logger.debug("cannot update undiv buyer construction details");
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							logger.debug("cannot uupdate undiv buyer details");
						}

					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (buyerDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
				sql = PropertyValuationSQL.GET_AGRI_TXN_ID;
				try {
					dbUtil.createStatement();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String agriTxnId = dbUtil.executeQry(sql);
				String arrdivBuyerDetls[] = new String[18];
				arrdivBuyerDetls[0] = agriTxnId;
				arrdivBuyerDetls[1] = valTxnId;
				arrdivBuyerDetls[2] = buyerDTO.getAgriLandTypeId();
				if ("construct".equalsIgnoreCase(buyerDTO.getIsConstruction()))

				{
					arrdivBuyerDetls[3] = "Y";

				}

				else {
					arrdivBuyerDetls[3] = "N";
				}
				arrdivBuyerDetls[4] = buyerDTO.getAgriTotalDivertedArea();
				arrdivBuyerDetls[5] = buyerDTO.getAgriTotalDivertedResidentialArea();
				arrdivBuyerDetls[6] = buyerDTO.getAgriTotalDivertedCommercialArea();
				arrdivBuyerDetls[7] = buyerDTO.getAgriTotalDivertedIndustrialArea();
				arrdivBuyerDetls[8] = buyerDTO.getAgriTotalDivertedEducationalArea();
				arrdivBuyerDetls[9] = buyerDTO.getAgriTotalDivertedHealthArea();
				arrdivBuyerDetls[10] = buyerDTO.getAgriTotalDivertedOthersArea();
				if ("YESEDUC".equalsIgnoreCase(buyerDTO.getIsAgriTncpEducational())) {
					arrdivBuyerDetls[11] = "Y";
				} else {
					arrdivBuyerDetls[11] = "N";
				}
				if ("YESHEALTH".equalsIgnoreCase(buyerDTO.getIsAgriTncpHealth())) {
					arrdivBuyerDetls[12] = "Y";
				} else {
					arrdivBuyerDetls[12] = "N";
				}
				arrdivBuyerDetls[13] = buyerDTO.getAgriDivertedValue();
				arrdivBuyerDetls[14] = userId;
				if (buyerDTO.getAgriBuildingConstructArea() == null || buyerDTO.getAgriBuildingConstructArea().equalsIgnoreCase("")) {
					buyerDTO.setAgriBuildingConstructArea("0.0");
				}
				arrdivBuyerDetls[15] = String.valueOf(Double.parseDouble(buyerDTO.getAgriBuildingConstructArea()) / 10000);
				arrdivBuyerDetls[16] = buyerDTO.getAgriBuildingConstructCost();
				arrdivBuyerDetls[17] = "";//buyerDTO.getIsConstructionKhasraNo();
				try {
					dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_AGRI_DIV_BUYER_DETLS);

					boolean updated = dbUtil.executeUpdate(arrdivBuyerDetls);

					// Added by Rakesh: Start
					if (updated == true
							&& propForm.getPropDTO().getClrFlag() != null && clrLoop==1)  {
						if (propForm.getPropDTO().getPropertyId()
								.equalsIgnoreCase("3")
								&& propForm.getPropDTO().getClrFlag()
										.equalsIgnoreCase("Y")) {
							
							
														
							for (int j = 0; j < propForm.getSampadaClrKhasraList().size(); j++) {
								dbUtil
								.createPreparedStatement(PropertyValuationSQL.INSERT_DIV_AGRI_KHASRA_CLR_DETLS);

						//String khasraClrArray[] = new String[26];		
								String khasraClrArray[] = new String[32];
						sql = PropertyValuationSQL.GET_KHASRA_TXN_ID;
						dbUtil.createStatement();
						logger.debug("Khasra No DAO"+ propForm.getClrDto().getKhasraNoClr());
						String khasraTxnID = dbUtil.executeQry(sql);
						khasraClrArray[0] = khasraTxnID;
						khasraClrArray[1] = valTxnId;
						//khasraClrArray[2] = propForm.getClrDto().getKhasraNoClr();
						//khasraClrArray[2] = propForm.getSmapadaClrDTO()getKhasraNoClr();
						khasraClrArray[2] = propForm.getSampadaClrKhasraList().get(j)
								.getKhasraNoClr();						
						khasraClrArray[3] = propForm.getSampadaClrKhasraList().get(j)
								.getClrKhasraId();
						khasraClrArray[4] = propForm.getSampadaClrKhasraList().get(j)
								.getKhasraTotalArea();
						khasraClrArray[5] = propForm.getSampadaClrKhasraList().get(j)
								.getLandIrriOrNot();
						khasraClrArray[6] = propForm.getSampadaClrKhasraList().get(j)
								.getIrrigatedArea();
						khasraClrArray[7] = propForm.getSampadaClrKhasraList().get(j)
								.getLandUnIrriOrNot();

						//khasraClrArray[8] = propForm.getClrDto().getNonIrrigatedArea();
						khasraClrArray[8] = propForm.getSampadaClrKhasraList().get(j)
								.getSingleCropArea();
						khasraClrArray[9] = propForm.getSampadaClrKhasraList().get(j)
								.getLandUnirriDouble();
						khasraClrArray[10] = propForm.getSampadaClrKhasraList().get(j)
								.getDoubleCropArea();
						khasraClrArray[11] = propForm.getSampadaClrKhasraList().get(j)
								.getPadtiFlag();
						khasraClrArray[12] = propForm.getSampadaClrKhasraList().get(j)
								.getPadtiArea();
						khasraClrArray[13] = propForm.getSampadaClrKhasraList().get(j)
								.getOwnership();
						khasraClrArray[14] = propForm.getSampadaClrKhasraList().get(j)
								.getNohiyat();
						khasraClrArray[15] = propForm.getSampadaClrKhasraList().get(j)
								.getCurrentStatus();
						khasraClrArray[16] = propForm.getSampadaClrKhasraList().get(j)
								.getKehfiyat();

						khasraClrArray[17] = propForm.getSampadaClrKhasraList().get(j)
								.getClrFlag();

						//khasraClrArray[18] = propForm.getSampadaArea();
						
						//khasraClrArray[18] = propForm.getSampadaArea();
						//khasraClrArray[18] = String.valueOf(propForm.getSampadaClrKhasraList().get(j).getTotalSaleableArea());
						khasraClrArray[18] = "0";

						khasraClrArray[19] = propForm.getSampadaClrKhasraList().get(j)
								.getMortgage();
						khasraClrArray[20] = userId;
						//Sampada Data
						khasraClrArray[21] =propForm.getSampadaClrKhasraList().get(j).getCensusCode();
						/*khasraClrArray[22] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaSingleCrop());
						khasraClrArray[23] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaDoubleCrop());
						khasraClrArray[24] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaIrrigated());
						khasraClrArray[25] =propForm.getPropDTO().getRinPustikaNoClr();*/
						khasraClrArray[22] =propForm.getPropDTO().getRinPustikaNoClr();
						khasraClrArray[23] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedArea());
						khasraClrArray[24] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedResidentialArea());
						khasraClrArray[25] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedCommercialArea());
						
						
						khasraClrArray[26] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedIndustrialArea());
						khasraClrArray[27] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedEducationalArea());
						khasraClrArray[28] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedHealthArea());
						khasraClrArray[29] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedOthersArea());
						//Below change by Ajit for CLR for Undiverted Agriland Bug 10 start 
						String totalSaleableArea=String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedArea());
						if(totalSaleableArea.length()>5 && totalSaleableArea.contains(".")){
							 totalSaleableArea=String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedArea()).substring(0, 5);
							 logger.debug("CLR Khasra khasra Data insertion totalSaleableArea diverted land--"+ totalSaleableArea);
						}
						khasraClrArray[30] = totalSaleableArea;//String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedArea());
						//change by Ajit for CLR for Undiverted Agriland Bug 10 start
						
						
						khasraClrArray[31] ="Y";
						
						boolean flag=dbUtil.executeUpdate(khasraClrArray);
						
						if(flag== true){
							dbUtil.commit();
						}else{
							dbUtil.rollback();
						}
					logger.debug("CLR Khasra Data insertion flag"+ flag);
							}
							
							propForm.setSampadaArea("");

							
						}

					}
					// Added by Rakesh: End

					if (updated == true) {
						if ("construct".equalsIgnoreCase(buyerDTO.getIsConstruction())) {
							logger.debug("updated div buyer details");
							String arrdivBuyerConstructDetls[] = new String[3];

							arrdivBuyerConstructDetls[0] = agriTxnId;
							arrdivBuyerConstructDetls[1] = valTxnId;
							arrdivBuyerConstructDetls[2] = buyerDTO.getAgriBuildingConstructTxnId();

							try {
								dbUtil.createPreparedStatement(PropertyValuationSQL.UPDATE_AGRI_CONSTRUCTION_DETLS);

								boolean updated1 = dbUtil.executeUpdate(arrdivBuyerConstructDetls);

								if (updated1 == true) {
									logger.debug("updated div buyer construction details");

									String arrupdatepropsubclause[] = new String[1];

									arrupdatepropsubclause[0] = agriTxnId;

									try {
										dbUtil.createPreparedStatement(PropertyValuationSQL.UPDATE_PROP_SUB_CLAUSE_DETLS);

										boolean updated2 = dbUtil.executeUpdate(arrupdatepropsubclause);
										if (updated2 == true) {
											logger.debug("updated prop subclause table");
										} else {
											logger.debug("cannot updated prop subclause table");
										}
									}

									catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} else {
									logger.debug("cannot update div buyer construction details");
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} else {
							logger.debug("cannot update div buyer details");
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			// -----for agriLand both----

			else {

				sql = PropertyValuationSQL.GET_AGRI_TXN_ID;
				try {
					dbUtil.createStatement();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String agriTxnId = dbUtil.executeQry(sql);
				String arrBothBuyerDetls[] = new String[23];
				arrBothBuyerDetls[0] = agriTxnId;
				arrBothBuyerDetls[1] = valTxnId;
				arrBothBuyerDetls[2] = buyerDTO.getAgriLandTypeId();
				arrBothBuyerDetls[3] = buyerDTO.getAgriTotalUnDivertedArea();
				arrBothBuyerDetls[4] = buyerDTO.getAgriUndivSingleCropArea();
				arrBothBuyerDetls[5] = buyerDTO.getAgriUndivDoubleCropArea();
				arrBothBuyerDetls[6] = buyerDTO.getAgriUndivIrrigatedArea();
				if (buyerDTO.getIsConstruction().equalsIgnoreCase("construct"))

				{
					arrBothBuyerDetls[7] = "Y"; // ----both diverted and
												// undiverted values from
												// calculation with areas left

				}

				else {
					arrBothBuyerDetls[7] = "N";
				}
				arrBothBuyerDetls[8] = buyerDTO.getAgriTotalDivertedArea();
				arrBothBuyerDetls[9] = buyerDTO.getAgriTotalDivertedResidentialArea();
				arrBothBuyerDetls[10] = buyerDTO.getAgriTotalDivertedCommercialArea();
				arrBothBuyerDetls[11] = buyerDTO.getAgriTotalDivertedIndustrialArea();
				arrBothBuyerDetls[12] = buyerDTO.getAgriTotalDivertedEducationalArea();
				arrBothBuyerDetls[13] = buyerDTO.getAgriTotalDivertedHealthArea();
				arrBothBuyerDetls[14] = buyerDTO.getAgriTotalDivertedOthersArea();
				if ("YESEDUC".equalsIgnoreCase(buyerDTO.getIsAgriTncpEducational())) {
					arrBothBuyerDetls[15] = "Y";
				} else {
					arrBothBuyerDetls[15] = "N";
				}
				if ("YESHEALTH".equalsIgnoreCase(buyerDTO.getIsAgriTncpHealth())) {
					arrBothBuyerDetls[16] = "Y";
				} else {
					arrBothBuyerDetls[16] = "N";
				}
				arrBothBuyerDetls[17] = buyerDTO.getAgriDivertedValue();
				arrBothBuyerDetls[18] = buyerDTO.getAgriUndivertedValue();
				arrBothBuyerDetls[19] = userId;
				if (buyerDTO.getAgriBuildingConstructArea() == null || buyerDTO.getAgriBuildingConstructArea().equalsIgnoreCase("")) {
					buyerDTO.setAgriBuildingConstructArea("0.0");
				}
				arrBothBuyerDetls[20] = String.valueOf(Double.parseDouble(buyerDTO.getAgriBuildingConstructArea()) / 10000);
				arrBothBuyerDetls[21] = buyerDTO.getAgriBuildingConstructCost();
				arrBothBuyerDetls[22] = buyerDTO
				.getIsConstructionKhasraNo();

				try {
					dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_AGRI_BOTH_BUYER_DETLS);

					boolean updated = dbUtil.executeUpdate(arrBothBuyerDetls);

					// Added by Rakesh: Start
					if (updated == true
							&& propForm.getPropDTO().getClrFlag() != null && clrLoop==1)  {
						if (propForm.getPropDTO().getPropertyId()
								.equalsIgnoreCase("3")
								&& propForm.getPropDTO().getClrFlag()
										.equalsIgnoreCase("Y")) {
							for (int j = 0; j < propForm.getSampadaClrKhasraList().size(); j++) {
								dbUtil
								.createPreparedStatement(PropertyValuationSQL.INSERT_BOTH_AGRI_KHASRA_CLR_DETLS);

						//String khasraClrArray[] = new String[26];		
								String khasraClrArray[] = new String[35];
						sql = PropertyValuationSQL.GET_KHASRA_TXN_ID;
						dbUtil.createStatement();
						logger.debug("Khasra No DAO"+ propForm.getClrDto().getKhasraNoClr());
						String khasraTxnID = dbUtil.executeQry(sql);
						khasraClrArray[0] = khasraTxnID;
						khasraClrArray[1] = valTxnId;
						//khasraClrArray[2] = propForm.getClrDto().getKhasraNoClr();
						//khasraClrArray[2] = propForm.getSmapadaClrDTO()getKhasraNoClr();
						khasraClrArray[2] = propForm.getSampadaClrKhasraList().get(j)
								.getKhasraNoClr();						
						khasraClrArray[3] = propForm.getSampadaClrKhasraList().get(j)
								.getClrKhasraId();
						khasraClrArray[4] = propForm.getSampadaClrKhasraList().get(j)
								.getKhasraTotalArea();
						khasraClrArray[5] = propForm.getSampadaClrKhasraList().get(j)
								.getLandIrriOrNot();
						khasraClrArray[6] = propForm.getSampadaClrKhasraList().get(j)
								.getIrrigatedArea();
						khasraClrArray[7] = propForm.getSampadaClrKhasraList().get(j)
								.getLandUnIrriOrNot();

						//khasraClrArray[8] = propForm.getClrDto().getNonIrrigatedArea();
						khasraClrArray[8] = propForm.getSampadaClrKhasraList().get(j)
								.getSingleCropArea();
						khasraClrArray[9] = propForm.getSampadaClrKhasraList().get(j)
								.getLandUnirriDouble();
						khasraClrArray[10] = propForm.getSampadaClrKhasraList().get(j)
								.getDoubleCropArea();
						khasraClrArray[11] = propForm.getSampadaClrKhasraList().get(j)
								.getPadtiFlag();
						khasraClrArray[12] = propForm.getSampadaClrKhasraList().get(j)
								.getPadtiArea();
						khasraClrArray[13] = propForm.getSampadaClrKhasraList().get(j)
								.getOwnership();
						khasraClrArray[14] = propForm.getSampadaClrKhasraList().get(j)
								.getNohiyat();
						khasraClrArray[15] = propForm.getSampadaClrKhasraList().get(j)
								.getCurrentStatus();
						khasraClrArray[16] = propForm.getSampadaClrKhasraList().get(j)
								.getKehfiyat();

						khasraClrArray[17] = propForm.getSampadaClrKhasraList().get(j)
								.getClrFlag();

						//khasraClrArray[18] = propForm.getSampadaArea();
						
						//khasraClrArray[18] = propForm.getSampadaArea();
						khasraClrArray[18] = String.valueOf(propForm.getSampadaClrKhasraList().get(j).getTotalSaleableArea());

						khasraClrArray[19] = propForm.getSampadaClrKhasraList().get(j)
								.getMortgage();
						khasraClrArray[20] = userId;
						//Sampada Data
						khasraClrArray[21] =propForm.getSampadaClrKhasraList().get(j).getCensusCode();
						/*khasraClrArray[22] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaSingleCrop());
						khasraClrArray[23] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaDoubleCrop());
						khasraClrArray[24] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaIrrigated());
						khasraClrArray[25] =propForm.getPropDTO().getRinPustikaNoClr();*/
						khasraClrArray[22] =propForm.getPropDTO().getRinPustikaNoClr();
						khasraClrArray[23] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedArea());
						khasraClrArray[24] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedResidentialArea());
						khasraClrArray[25] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedCommercialArea());					
						
						khasraClrArray[26] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedIndustrialArea());
						khasraClrArray[27] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedEducationalArea());
						khasraClrArray[28] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedHealthArea());
						khasraClrArray[29] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedOthersArea());
						khasraClrArray[30] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaSingleCrop());
						khasraClrArray[31] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaDoubleCrop());
						khasraClrArray[32] =String.valueOf(propForm.getSampadaClrKhasraList().get(j).getSampadaIrrigated());
						//Below change by Ajit for CLR for diverted Undiverted both Agriland Bug 10 start 
						String totalSaleableArea= String.valueOf(propForm.getSampadaClrKhasraList().get(j).getTotalSaleableArea() + propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedArea());
						if(totalSaleableArea.length()>5 && totalSaleableArea.contains(".")) {
							 totalSaleableArea= String.valueOf(propForm.getSampadaClrKhasraList().get(j).getTotalSaleableArea() + propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedArea()).substring(0, 5);
							 logger.debug("CLR Khasra khasra Data insertion totalSaleableArea for both---"+ totalSaleableArea);
						}
						khasraClrArray[33] = totalSaleableArea;//String.valueOf(propForm.getSampadaClrKhasraList().get(j).getTotalSaleableArea() + propForm.getSampadaClrKhasraList().get(j).getSampadaTotalDivertedArea());
						//change by Ajit for CLR for Undiverted Agriland Bug 10 End
						
						khasraClrArray[34] ="Y";
						
						
						
						boolean flag=dbUtil.executeUpdate(khasraClrArray);
						if(flag== true){
							dbUtil.commit();
						}else{
							dbUtil.rollback();
						}
						
					logger.debug("CLR Khasra Data insertion flag for both"+ flag);
					}
							propForm.setSampadaArea("");

							
						}

					}
					// Added by Rakesh: End

					if (updated == true) {
						if ("construct".equalsIgnoreCase(buyerDTO.getIsConstruction())) {
							logger.debug("updated both buyer details");
							String arrBothBuyerConstructDetls[] = new String[3];

							arrBothBuyerConstructDetls[0] = agriTxnId;
							arrBothBuyerConstructDetls[1] = valTxnId;
							arrBothBuyerConstructDetls[2] = buyerDTO.getAgriBuildingConstructTxnId();

							try {
								dbUtil.createPreparedStatement(PropertyValuationSQL.UPDATE_AGRI_CONSTRUCTION_DETLS);

								boolean updated1 = dbUtil.executeUpdate(arrBothBuyerConstructDetls);

								if (updated1 == true) {
									logger.debug("updated both buyer construction details");

									String arrupdatepropsubclause[] = new String[1];

									arrupdatepropsubclause[0] = agriTxnId;

									try {
										dbUtil.createPreparedStatement(PropertyValuationSQL.UPDATE_PROP_SUB_CLAUSE_DETLS);

										boolean updated2 = dbUtil.executeUpdate(arrupdatepropsubclause);
										if (updated2 == true) {
											logger.debug("updated prop subclause table");
										} else {
											logger.debug("cannot updated prop subclause table");
										}
									}

									catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} else {
									logger.debug("cannot update both buyer construction details");
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} else {
							logger.debug("cannot updated both buyer details");
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
		// ---insertion of subclauses---

		if (propForm.getPropDTO().getAgriSelectedSubclauseId() != null && !propForm.getPropDTO().getAgriSelectedSubclauseId().equalsIgnoreCase("")) {
			insertSubclauseValues(propForm.getPropDTO(), valTxnId, userId);
		}

		String arrTreeDetls[] = new String[5];
		if (dto.getSagunTree() != null && !dto.getSagunTree().equalsIgnoreCase("") && !dto.getSagunTree().equalsIgnoreCase("0")) {
			try {
				dbUtil = new DBUtility();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sql = PropertyValuationSQL.GET_AGRI_TREE_MAP_ID;
			try {
				dbUtil.createStatement();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String agriTreeMapTxnId = dbUtil.executeQry(sql);
			arrTreeDetls[0] = agriTreeMapTxnId;
			arrTreeDetls[1] = valTxnId;
			arrTreeDetls[2] = "1";
			arrTreeDetls[3] = dto.getSagunTree();
			arrTreeDetls[4] = userId;
			try {
				dbUtil.createStatement();

				dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_AGRI_TREE_DETLS);

				Insert = dbUtil.executeUpdate(arrTreeDetls);

			} catch (Exception e) {
				logger.error(e);
				// return null;
			}
		}
		if (dto.getSaalTree() != null && !dto.getSaalTree().equalsIgnoreCase("") && !dto.getSaalTree().equalsIgnoreCase("0")) {
			try {
				dbUtil = new DBUtility();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sql = PropertyValuationSQL.GET_AGRI_TREE_MAP_ID;
			try {
				dbUtil.createStatement();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String agriTreeMapTxnId = dbUtil.executeQry(sql);
			arrTreeDetls[0] = agriTreeMapTxnId;
			arrTreeDetls[1] = valTxnId;
			arrTreeDetls[2] = "2";
			arrTreeDetls[3] = dto.getSaalTree();
			arrTreeDetls[4] = userId;
			try {
				dbUtil.createStatement();

				dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_AGRI_TREE_DETLS);

				Insert = dbUtil.executeUpdate(arrTreeDetls);

			} catch (Exception e) {
				logger.error(e);
				// return null;
			}
		}
		if (dto.getFruitTree() != null && !dto.getFruitTree().equalsIgnoreCase("") && !dto.getFruitTree().equalsIgnoreCase("0")) {
			try {
				dbUtil = new DBUtility();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sql = PropertyValuationSQL.GET_AGRI_TREE_MAP_ID;
			try {
				dbUtil.createStatement();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String agriTreeMapTxnId = dbUtil.executeQry(sql);
			arrTreeDetls[0] = agriTreeMapTxnId;
			arrTreeDetls[1] = valTxnId;
			arrTreeDetls[2] = "3";
			arrTreeDetls[3] = dto.getFruitTree();
			arrTreeDetls[4] = userId;
			try {
				dbUtil.createStatement();

				dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_AGRI_TREE_DETLS);

				Insert = dbUtil.executeUpdate(arrTreeDetls);

			} catch (Exception e) {
				logger.error(e);
				// return null;
			}
		}
		if (dto.getLess45Tree() != null && !dto.getLess45Tree().equalsIgnoreCase("") && !dto.getLess45Tree().equalsIgnoreCase("0")) {
			try {
				dbUtil = new DBUtility();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sql = PropertyValuationSQL.GET_AGRI_TREE_MAP_ID;
			try {
				dbUtil.createStatement();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String agriTreeMapTxnId = dbUtil.executeQry(sql);
			arrTreeDetls[0] = agriTreeMapTxnId;
			arrTreeDetls[1] = valTxnId;
			arrTreeDetls[2] = "4";
			arrTreeDetls[3] = dto.getLess45Tree();
			arrTreeDetls[4] = userId;
			try {
				dbUtil.createStatement();

				dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_AGRI_TREE_DETLS);

				Insert = dbUtil.executeUpdate(arrTreeDetls);

			} catch (Exception e) {
				logger.error(e);
				// return null;
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.error(e);

				}

			}
		}

		return Insert;

	}

	public String insertPlotDetails(PropertyValuationDTO propDTO, String edu, String hlt) {
		String valId = "";
		boolean flag = false;
		boolean flagVal = false;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] param = new String[12];

		param[0] = propDTO.getPlotTotArea();
		param[1] = propDTO.getPlotResiArea();
		param[2] = propDTO.getPlotCommArea();
		param[3] = propDTO.getPlotIndusArea();
		param[4] = propDTO.getPlotSchoolArea();
		param[5] = propDTO.getPlotHealthArea();
		param[6] = edu;
		param[7] = hlt;
		param[8] = propDTO.getPlotOtherArea();
		param[9] = propDTO.getUserId();
		param[10] = propDTO.getPlotResicumComm();
		if ("Yes".equalsIgnoreCase(propDTO.getRadioResiComm())) {
			param[11] = "Y";
		} else {
			param[11] = "N";
		}

		String[] param1 = new String[10];

		param1[0] = propDTO.getDistrictId();
		param1[1] = propDTO.getTehsilId();
		param1[2] = propDTO.getAreaId();
		param1[3] = propDTO.getSubAreaId();
		param1[4] = propDTO.getWardPatwariId().split("~")[0];
		param1[5] = propDTO.getColonyId().split("~")[0];
		param1[6] = propDTO.getPropertyId();
		param1[7] = propDTO.getUserId();
		param1[8] = propDTO.getMunicipalFlag();
		param1[9] = propDTO.getPlotTotArea(); // Added By Gulrej on 29th Aug,
												// 2017

		String sql = PropertyValuationSQL.INSERT_PROP_VAL_PLOT_DETAILS;

		String sql1 = PropertyValuationSQL.INSERT_PLOT_DETAILS;

		try {

			dbUtil.createPreparedStatement(sql);
			flag = dbUtil.executeUpdate(param1);
			if (flag == true) {
				dbUtil.createPreparedStatement(sql1);
				flagVal = dbUtil.executeUpdate(param);
				if (flagVal == true) {
					dbUtil.createStatement();
					valId = dbUtil.executeQry(PropertyValuationSQL.GET_VALUATION_ID_CURR);

				}
			}
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return valId;

	}

	public ArrayList getGuidelineId(PropertyValuationDTO propDTO) {
		ArrayList list = new ArrayList();

		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] param1 = new String[8];
		param1[1] = propDTO.getTehsilId();
		param1[2] = propDTO.getAreaId();
		param1[4] = propDTO.getWardPatwariId().split("~")[0];
		param1[5] = propDTO.getColonyId().split("~")[0];
		param1[3] = propDTO.getSubAreaId();

		param1[6] = propDTO.getPropertyId();
		param1[7] = propDTO.getUserId();
		return list;
	}
	public ArrayList getPlotGuidelineValues(PropertyValuationDTO propDTO) {

		ArrayList guidelineValues = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.SELECT_PLOT_GUIDELINE_VALUE);
			guidelineValues = dbUtil.executeQuery(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], propDTO.getPropertyId(), String.valueOf(PropertyValuationConstant.PLOT_RESIDENTIAL_L1), String.valueOf(PropertyValuationConstant.PLOT_COMMERCIAL_L1), String.valueOf(PropertyValuationConstant.PLOT_INDUSTRIAL_L1), String.valueOf(PropertyValuationConstant.PLOT_HEALTH_L1), String.valueOf(PropertyValuationConstant.PLOT_EDUCATIONAL_L1), String.valueOf(PropertyValuationConstant.PLOT_OTHER_L1), String.valueOf(PropertyValuationConstant.PLOT_RESI_CUM_COMM_L1)});
			return guidelineValues;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public ArrayList getSubclauseData(PropertyValuationDTO propDTO) {
		ArrayList plotSubclause = new ArrayList();
		String sql = "";

		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if ("1".equalsIgnoreCase(propDTO.getPropertyId())) {
				sql = PropertyValuationSQL.SELECT_SUBCLAUSE_MASTER_PLOT;
				dbUtil.createPreparedStatement(sql);

				plotSubclause = dbUtil.executeQuery(new String[]{propDTO.getPropertyId()});
			} else if ("3".equalsIgnoreCase(propDTO.getPropertyId())) {
				sql = PropertyValuationSQL.SELECT_SUBCLAUSE_MASTER_AGRI_BUILD;
				dbUtil.createPreparedStatement(sql);

				plotSubclause = dbUtil.executeQuery(new String[]{propDTO.getPropertyId(), propDTO.getAgriLandTypeId()});
			}
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return plotSubclause;

	}

	public ArrayList calcWithSubclauses(String propertyId) {
		ArrayList retList = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sqlBuild = PropertyValuationSQL.IND_BUILDING_CALCULATE_WITH_SUBCLAUSS;
		String sqlPlot = PropertyValuationSQL.IND_PLOT_CALCULATE_WITH_SUBCLAUSS;
		try {
			dbUtil.createStatement();
			if (propertyId.equalsIgnoreCase("2")) {
				retList = dbUtil.executeQuery(sqlBuild);
			} else {
				retList = dbUtil.executeQuery(sqlPlot);
			}
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return retList;

	}

	public String insertMainTxnId(String language, String userId) throws Exception {
		String AgriMainTxnId = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = PropertyValuationSQL.GET_VALUATION_ID;
		try {
			dbUtil.createStatement();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		AgriMainTxnId = dbUtil.executeQry(sql);
		String sql1 = PropertyValuationSQL.INSERT_AGRI_MAIN_TXN_ID;
		try {
			String[] arrInsertMainId = new String[2];
			arrInsertMainId[0] = AgriMainTxnId;
			arrInsertMainId[1] = userId;
			dbUtil.createPreparedStatement(sql1);
			boolean insertMainTxnId = dbUtil.executeUpdate(arrInsertMainId);

			if (insertMainTxnId == true) {
				logger.debug("main Txn Id inserted");
			} else {
				logger.debug("main Txn Id insertion fail");
			}
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

		return AgriMainTxnId;
	}

	public ArrayList getSubclauseDataBuilding(PropertyValuationDTO propDTO, String agriFlag) {
		ArrayList plotSubclause = new ArrayList();
		String sql = "";

		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (agriFlag.equalsIgnoreCase("YES")) {

				if (propDTO.getResiFlag().equalsIgnoreCase("Y") && propDTO.getCommFlag().equalsIgnoreCase("N") && propDTO.getIndFlag().equalsIgnoreCase("N") && propDTO.getHealthFlag().equalsIgnoreCase("N") && propDTO.getSchoolFlag().equalsIgnoreCase("N") && propDTO.getOtherFlag().equalsIgnoreCase("N") && propDTO.getResiCommFlag().equalsIgnoreCase("N")) {
					sql = PropertyValuationSQL.SELECT_SUBCLAUSE_MASTER_AGRI_BUILD_AGRI_resi;

				} else {
					sql = PropertyValuationSQL.SELECT_SUBCLAUSE_MASTER_AGRI_BUILD_AGRI;
				}
				String[] param1 = new String[2];
				param1[0] = propDTO.getPropertyId();
				param1[1] = propDTO.getBuildingTypeId();
				dbUtil.createPreparedStatement(sql);
				plotSubclause = dbUtil.executeQuery(param1);
			} else {
				if ("3".equalsIgnoreCase(propDTO.getBuildingTypeId())) {
					String[] param1 = new String[3];
					param1[0] = propDTO.getPropertyId();
					param1[1] = propDTO.getBuildingTypeId();
					param1[2] = propDTO.getMultiStoreyTypeId();

					sql = PropertyValuationSQL.SELECT_SUBCLAUSE_MASTER_AGRI_BUILD_Multi;
					dbUtil.createPreparedStatement(sql);
					plotSubclause = dbUtil.executeQuery(param1);
				} else {
					String[] param1 = new String[2];
					param1[0] = propDTO.getPropertyId();
					param1[1] = propDTO.getBuildingTypeId();

					// added by akansha const cost changes
					if (propDTO.getResiFlag().equalsIgnoreCase("Y") && propDTO.getCommFlag().equalsIgnoreCase("N") && propDTO.getIndFlag().equalsIgnoreCase("N") && propDTO.getHealthFlag().equalsIgnoreCase("N") && propDTO.getSchoolFlag().equalsIgnoreCase("N") && propDTO.getOtherFlag().equalsIgnoreCase("N") && propDTO.getResiCommFlag().equalsIgnoreCase("N")) {
						sql = PropertyValuationSQL.SELECT_SUBCLAUSE_MASTER_AGRI_BUILD_RESI;

					} else {

						sql = PropertyValuationSQL.SELECT_SUBCLAUSE_MASTER_AGRI_BUILD;
					}
					dbUtil.createPreparedStatement(sql);
					plotSubclause = dbUtil.executeQuery(param1);
				}
			}

		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return plotSubclause;
	}

	public ArrayList getAllGuideLineValuesOfUndiverted(PropertyValuationDTO propDTO) {

		ArrayList guideLineValuesList = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = PropertyValuationSQL.GET_GUIDELINE_ALL_UNDIVERTED_VALUES;

		String[] param1 = new String[12];

		param1[0] = propDTO.getDistrictId();
		param1[1] = propDTO.getAreaId();
		param1[2] = propDTO.getTehsilId();
		param1[3] = propDTO.getSubAreaId();
		param1[4] = propDTO.getAgriWardPatwariId();
		param1[5] = propDTO.getAgriColonyid();
		// param1[6]=propDTO.getAgriSubAreaWardMapId();
		param1[6] = propDTO.getAgriColonyWardMapId();
		param1[7] = propDTO.getPropertyId();
		param1[8] = "20"; // ----Irrigated l1 ID---
		param1[9] = "21"; // ----Un-irrigated l1 ID--
		param1[10] = "23"; // ----One Crop l1 ID---
		param1[11] = "24"; // ----Double Crop l1 ID---

		try {
			dbUtil.createPreparedStatement(sql);
			guideLineValuesList = dbUtil.executeQuery(param1);
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return guideLineValuesList;
	}

	public String getL2Rate(PropertyValuationDTO propDTO, String l1Id, String l2Id) {
		String rate = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.L2_GUIDELINE_VALUE);
			rate = dbUtil.executeQry(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], propDTO.getPropertyId(), l1Id, l2Id});
			return rate;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}
	public String getL1Rate(PropertyValuationDTO propDTO, String l1Id, String propertyId) {
		String rate = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.L1_GUIDELINE_VALUE);
			rate = dbUtil.executeQry(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], propertyId, l1Id});
			return rate;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}

	public ArrayList getAllGuideLineTreeRates() {
		ArrayList treeValuesList = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = PropertyValuationSQL.GET_GUIDELINE_ALL_TREE_RATES;

		String[] param1 = new String[4];

		param1[0] = "1"; // ----TREE_ID OF SAGUN TREE----
		param1[1] = "2"; // ----TREE_ID OF SAL TREE----
		param1[2] = "3"; // ----TREE_ID OF FRUIT TREE----
		param1[3] = "4"; // ----TREE_ID OF LES45 TREE----

		try {
			dbUtil.createPreparedStatement(sql);
			treeValuesList = dbUtil.executeQuery(param1);
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return treeValuesList;

	}
	public String updateFinalMarketValue(TreeDTO dto, PropertyValuationForm propForm, String language, String userId) {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String mainValId = propForm.getPropDTO().getAgriMainValTxnId();
		boolean update = false;
		String FinalMarketValue = "";
		try {
			String sql1 = PropertyValuationSQL.GET_SUM_FIRST_MARKET_VALUE;

			String[] param = new String[1];
			param[0] = mainValId;
			dbUtil.createPreparedStatement(sql1);
			FinalMarketValue = dbUtil.executeQry(param);
		} catch (Exception e) {
			logger.error(e);

		}
		try {
			String sql1 = PropertyValuationSQL.UPDATE_FINAL_MARKET_VALUE;

			String[] param = new String[3];
			param[0] = FinalMarketValue;
			param[1] = propForm.getPropDTO().getPropertyId();
			param[2] = mainValId;

			dbUtil.createPreparedStatement(sql1);

			update = dbUtil.executeUpdate(param);

			if (update == true) {
				logger.debug("updated FINAL_MV");
			} else {
				logger.debug("cannot update FINAL_MV");
			}
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return FinalMarketValue;

	}
	public boolean updateGuidelineMV(TreeDTO dto, PropertyValuationForm propForm, String language, String userId) {
		boolean update = false;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String valId = propForm.getPropDTO().getAgriValTxnId();

		String mainValId = propForm.getPropDTO().getAgriMainValTxnId();

		String Value = "";
		String subIds = "";
		String UndivValue = "";

		String divValue = "";
		double bothArea = 0.0;
		String totalArea = "";
		String bothSumtotalArea = "";
		String constructedCost = "";
		String divtotalArea = "";

		try {
			String sql = PropertyValuationSQL.GET_SUM_UNDIV_AGRI_VALUE_TXN;

			String[] param = new String[1];
			param[0] = valId;
			dbUtil.createPreparedStatement(sql);

			UndivValue = dbUtil.executeQry(param);

		} catch (Exception e) {
			logger.error(e);

		}

		try {
			String sql1 = PropertyValuationSQL.GET_SUM_DIV_AGRI_VALUE_TXN;

			String[] param = new String[1];
			param[0] = valId;
			dbUtil.createPreparedStatement(sql1);

			divValue = dbUtil.executeQry(param);

		} catch (Exception e) {
			logger.error(e);

		}
		if (UndivValue == null || UndivValue.equalsIgnoreCase("")) {
			UndivValue = "0.0";
		}

		if (divValue == null || divValue.equalsIgnoreCase("")) {
			divValue = "0.0";
		}

		if (propForm.getPropDTO().getAgriTotalTreeValue() == null || propForm.getPropDTO().getAgriTotalTreeValue().equalsIgnoreCase("")) {
			UndivValue = "0.0";
		}

		try {
			cal = new CalculationAgriLand();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (propForm.getPropDTO().getAgriLandTypeId().equalsIgnoreCase("5")) {

			try {
				String sql1 = PropertyValuationSQL.GET_SUM_UNDIV_AGRI_TOTAL_AREA_VALUE;

				String[] param = new String[1];
				param[0] = valId;
				dbUtil.createPreparedStatement(sql1);

				totalArea = dbUtil.executeQry(param);

			} catch (Exception e) {
				logger.error(e);

			}
		}

		if (propForm.getPropDTO().getAgriLandTypeId().equalsIgnoreCase("6")) {
			try {
				String sql1 = PropertyValuationSQL.GET_SUM_DIV_AGRI_TOTAL_AREA_VALUE;

				String[] param = new String[1];
				param[0] = valId;
				dbUtil.createPreparedStatement(sql1);

				totalArea = dbUtil.executeQry(param);

			} catch (Exception e) {
				logger.error(e);

			}
		}
		if (propForm.getPropDTO().getAgriLandTypeId().equalsIgnoreCase("7")) {
			try {
				String sql1 = PropertyValuationSQL.GET_SUM_BOTH_AGRI_TOTAL_AREA_VALUE;

				String[] param = new String[1];
				param[0] = valId;
				dbUtil.createPreparedStatement(sql1);

				bothSumtotalArea = dbUtil.executeQry(param);

				bothArea = Double.parseDouble(bothSumtotalArea);

				totalArea = String.valueOf(bothArea);

			} catch (Exception e) {
				logger.error(e);

			}

		}

		Value = String.valueOf(Double.parseDouble(UndivValue) + Double.parseDouble(divValue));

		if (Value == null || Value.equalsIgnoreCase("")) {
			Value = "0.0";
		}

		try {
			String sql6 = PropertyValuationSQL.UPDATE_AGRI_INDIV_GUIDELINE_MV;

			String[] param3 = new String[2];
			param3[0] = Value;
			param3[1] = valId;
			dbUtil.createPreparedStatement(sql6);

			boolean updated = dbUtil.executeUpdate(param3);

			if (updated == true) {

				logger.debug("GUIDELINE_MV updated");
			} else {

				logger.debug("cannot update GUIDELINE_MV");
			}

		} catch (Exception e) {
			logger.error(e);

		}
		if (propForm.getPropDTO().getAgriSelectedSubclauseId() != "") {
			subIds = propForm.propDTO.getAgriSelectedSubclauseId();
			String sId[] = subIds.split("~");
			String[] subClauseId = new String[sId.length];

			Value = cal.calcAgriWithSubclauses(UndivValue, divValue, propForm.getPropDTO().getAgriIrrigatedRate(), sId, propForm.getPropDTO(), totalArea, propForm);
		}

		try {
			String sql1 = PropertyValuationSQL.GET_SUM_CONSTRUCTED_VALUE;

			String[] param = new String[1];
			param[0] = valId;
			dbUtil.createPreparedStatement(sql1);

			constructedCost = dbUtil.executeQry(param);

		} catch (Exception e) {
			logger.error(e);

		}
		boolean boo = false;
		if (propForm.getPropDTO().getAgriSelectedSubclauseId() != "") {
			subIds = propForm.propDTO.getAgriSelectedSubclauseId();
			String sId[] = subIds.split("~");
			String[] subClauseId = new String[sId.length];
			for (int i = 0; i < sId.length; i++) {
				if ("11".equalsIgnoreCase(sId[i])) {
					boo = true;
				}
			}
		}

		double FinalMarketValue = 0;
		if (boo) {
			FinalMarketValue = Double.parseDouble(Value) + Double.parseDouble(propForm.getPropDTO().getAgriTotalTreeValue());
			for (int i = 0; i < propForm.getAgriAddBuyerList().size(); i++) {
				PropertyValuationDTO buyerDTO = propForm.getAgriAddBuyerList().get(i);
				double constructionCost = Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
				if (constructionCost > 0) {
					FinalMarketValue = constructionCost + FinalMarketValue;
				} else {
					if (buyerDTO.getAgriBuildingPlotValue() != null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase("")) {
						double plotValue = Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());

						FinalMarketValue = FinalMarketValue + plotValue;
					}
				}
			}

		} else {
			FinalMarketValue = Double.parseDouble(Value) + Double.parseDouble(propForm.getPropDTO().getAgriTotalTreeValue()) + Double.parseDouble(constructedCost);
		}
		String MarketValue = String.valueOf(FinalMarketValue);;

		try {
			String sql1 = PropertyValuationSQL.UPDATE_FIRST_MARKET_VALUE;

			String[] param = new String[2];
			param[0] = MarketValue;
			param[1] = valId;
			dbUtil.createPreparedStatement(sql1);
			update = dbUtil.executeUpdate(param);

			if (update == true) {
				logger.debug("updated individual FINAL_MV");
			} else {
				logger.debug("cannot update individual FINAL_MV");
			}
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

		return update;
	}
	public ArrayList getAgriSubClauseList(PropertyValuationDTO propDTO) {

		ArrayList agriSubClause = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = PropertyValuationSQL.SELECT_SUBCLAUSE_MASTER_AGRI_BUILD;

		String[] param1 = new String[2];
		param1[0] = propDTO.getPropertyId();
		param1[1] = propDTO.getAgriLandTypeId();

		try {
			dbUtil.createPreparedStatement(sql);
			agriSubClause = dbUtil.executeQuery(param1);
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return agriSubClause;

	}

	public ArrayList calcAgriWithSubclauses() {
		ArrayList retList = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = PropertyValuationSQL.AGRI_CALCULATE_WITH_SUBCLAUSS;
		try {
			dbUtil.createStatement();
			retList = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return retList;

	}

	public String getGuideLineId(PropertyValuationDTO propDTO) {
		String guideLineId = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = PropertyValuationSQL.SELECT_GUIDELINE_ID;
		String[] param1 = new String[7];
		param1[0] = propDTO.getDistrictId();
		param1[1] = propDTO.getAreaId();
		param1[2] = propDTO.getTehsilId();
		param1[3] = propDTO.getSubAreaId();
		param1[4] = propDTO.getWardPatwariId().split("~")[0];
		param1[5] = propDTO.getColonyId().split("~")[0];
		param1[6] = propDTO.getColonyId().split("~")[1];
		try {
			dbUtil.createPreparedStatement(sql);
			guideLineId = dbUtil.executeQry(param1);

		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return guideLineId;
	}
	public void insertFinalPlot(PropertyValuationDTO propDTO) {
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] param1 = new String[4];
		param1[0] = propDTO.getGuideLineId();
		param1[1] = propDTO.getGuideLineValue();
		param1[2] = propDTO.getPlotMarketValue();
		param1[3] = propDTO.getValuationId();
		String sql = PropertyValuationSQL.UPDATE_PROP_VAL_PLOT_DETAILS;

		try {
			dbUtil.createPreparedStatement(sql);
			dbUtil.executeUpdate(param1);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	// Below created by Roopam for building.
	public ArrayList getBuildingGuidelineValues(PropertyValuationDTO propDTO) {

		ArrayList guidelineValues = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// PROPERTY TYPE ID HARD CODDED AS PLOT.
			dbUtil.createPreparedStatement(PropertyValuationSQL.SELECT_BUILDING_GUIDELINE_VALUE);
			guidelineValues = dbUtil.executeQuery(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], PropertyValuationConstant.PLOT_ID, String.valueOf(PropertyValuationConstant.PLOT_RESIDENTIAL_L1), String.valueOf(PropertyValuationConstant.PLOT_COMMERCIAL_L1), String.valueOf(PropertyValuationConstant.PLOT_INDUSTRIAL_L1), String.valueOf(PropertyValuationConstant.PLOT_HEALTH_L1), String.valueOf(PropertyValuationConstant.PLOT_EDUCATIONAL_L1), String.valueOf(PropertyValuationConstant.PLOT_OTHER_L1)});
			return guidelineValues;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	// added by SIMRAN

	public ArrayList calcWithSubclausesBuilding() {
		ArrayList retList = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = PropertyValuationSQL.B_CALCULATE_WITH_SUBCLAUSS;
		try {
			dbUtil.createStatement();
			retList = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			logger.error(e);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return retList;

	}

	// ADDED BY SIMRAN

	public ArrayList getGuideLineValueMultistoryComm(PropertyValuationDTO propDTO, String l1, String l2) {
		ArrayList guidelineValues = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.MULTI_COMM_GUIDELINE_VALUE);
			guidelineValues = dbUtil.executeQuery(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], propDTO.getPropertyId(), l1, l2});
			return guidelineValues;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public ArrayList getGuideLineValueMultistory(PropertyValuationDTO propDTO, String l, String l1) {
		ArrayList guidelineValues = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.MULTI_COMMMERCIAL_GUIDELINE_VALUE_L1);
			guidelineValues = dbUtil.executeQuery(new String[]{propDTO.getDistrictId(), propDTO.getAreaId(), propDTO.getTehsilId(), propDTO.getSubAreaId(), propDTO.getWardPatwariId().split("~")[0], propDTO.getColonyId().split("~")[0], propDTO.getColonyId().split("~")[1], l, l1});
			return guidelineValues;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public boolean insertSubclauseValues(PropertyValuationDTO propDTO, String valuationId, String userId) {
		boolean flag = true;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String subId = "";
			if (propDTO.getPropertyId().equalsIgnoreCase("1")) {
				subId = propDTO.getPlotSubId() == null ? "" : propDTO.getPlotSubId();
			} else if (propDTO.getPropertyId().equalsIgnoreCase("3")) {
				subId = propDTO.getAgriSelectedSubclauseId() == null ? "" : propDTO.getAgriSelectedSubclauseId();
			} else {
				subId = propDTO.getBuildingSubId() == null ? "" : propDTO.getBuildingSubId();
			}
			if (subId == "" && propDTO.getOlderId() == 0) {
				return true;
			} else {
				if (subId == "" && propDTO.getOlderId() != 0) {
					subId = "0";
					String slabMapID = String.valueOf(propDTO.getOlderId());
					dbUtil = new DBUtility();

					dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_INTO_SUBCLAUSE_PROP_MAP_WITH_SLAB_ID);
					String[] arr = new String[]{valuationId, subId, userId, slabMapID};
					flag = dbUtil.executeUpdate(arr);

					logger.debug("DAO SUBCLAUSE FLAG:" + flag);
					return flag;

				} else {
					String slabMapID = String.valueOf(propDTO.getOlderId());
					String subIds[] = subId.split(PropertyValuationConstant.SPLIT_CONSTANT);
					logger.debug("subclause total-------" + subIds.length);

				for (int i = 0; i < subIds.length; i++) {
					logger.debug("subclause total-------" + subIds[i]);
					if (subIds[i] == null || subIds[i].equalsIgnoreCase("")) {

						} else { // condition added by Roopam for avoiding blank
									// sub
							// clause id record
							dbUtil = new DBUtility();
							//dbUtil.createPreparedStatement(PropertyValuationSQL
							// .INSERT_INTO_SUBCLAUSE_PROP_MAP);
							dbUtil.createPreparedStatement(PropertyValuationSQL.INSERT_INTO_SUBCLAUSE_PROP_MAP_WITH_SLAB_ID);
							String[] arr = new String[]{valuationId, subIds[i], userId, slabMapID};
							flag = dbUtil.executeUpdate(arr);
							if (flag == false) { // by roopam for transaction
													// mgmt.
								break;
							}
						}
					}
				}
				logger.debug("DAO SUBCLAUSE FLAG:" + flag);
				return flag;
			}

		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	public String getBuildingName(String buildingid, String language) {
		String name = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if ("en".equalsIgnoreCase(language)) {
				dbUtil.createPreparedStatement(PropertyValuationSQL.GET_BUILDING_NAME_EN);
			} else {
				dbUtil.createPreparedStatement(PropertyValuationSQL.GET_BUILDING_NAME_HI);
			}
			name = dbUtil.executeQry(new String[]{buildingid});
			return name;

		} catch (Exception e) {
			logger.error(e);
			return null;

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}
	public ArrayList agriTotalPropertyList(String agriMainValTxnId) {

		ArrayList agriTotalPropertyList = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] param1 = new String[1];
		param1[0] = agriMainValTxnId;
		String sql = PropertyValuationSQL.GET_AGRI_INDIV_PROPERTY_VALUE;

		try {
			dbUtil.createPreparedStatement(sql);
			agriTotalPropertyList = dbUtil.executeQuery(param1);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return agriTotalPropertyList;
	}
	public ArrayList getAgriRefrncIdFinalMVList(String agriMainValTxnId) {
		ArrayList agriRefrncIdFinalMVList = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] param1 = new String[1];
		param1[0] = agriMainValTxnId;
		String sql = PropertyValuationSQL.GET_AGRI_REFRNC_ID_FINAL_MV;

		try {
			dbUtil.createPreparedStatement(sql);
			agriRefrncIdFinalMVList = dbUtil.executeQuery(param1);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return agriRefrncIdFinalMVList;
	}
	public String getMunicipalId(String subAreaId) {
		String name = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.GET_MUNICIPAL_ID);

			name = dbUtil.executeQry(new String[]{subAreaId});
			return name;

		} catch (Exception e) {
			logger.error(e);
			return null;

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}
	/**
	 * Method : getRinPustikaNo Description : Getting all RinPustika No wrt
	 * regTxnId
	 * 
	 * @param query
	 *            : String
	 * @throws : Exception Returns : ArrayList
	 */
	public ArrayList getRinPustikaNo(String regTxnId) {
		ArrayList rinPustikaList = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.GET_RIN_PUSTIKA_CLR);

			rinPustikaList = dbUtil.executeQuery((new String[]{regTxnId}));
			return rinPustikaList;

		} catch (Exception e) {
			logger.error(e);
			return null;

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}
	/**
	 * Auther : Rakesh Soni Method : getRinPustikaNo Description : Getting all
	 * RinPustika No wrt regTxnId
	 * 
	 * @param query
	 *            : String
	 * @throws : Exception Returns : ArrayList
	 */
	public ArrayList getValTxnId(String regTxnId) {
		ArrayList valTxnList = new ArrayList();
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.GET_VAL_TXN_ID_CLR);

			valTxnList = dbUtil.executeQuery((new String[]{regTxnId}));
			return valTxnList;

		} catch (Exception e) {
			logger.error(e);
			return null;

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}

	// created by Rakesh fro clr getColonyId
	public String getColonyId(String valTxnId) {
		String name = "";
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			dbUtil.createPreparedStatement(PropertyValuationSQL.GET_COLONY_ID_CLR);

			name = dbUtil.executeQry(new String[]{valTxnId});
			return name;

		} catch (Exception e) {
			logger.error(e);
			return null;

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}
	// Created By rakesh
	public ArrayList getKhasraListClr(String valTxnId) {

		ArrayList khasraList = new ArrayList();
		ArrayList singleArr = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String[] param = {valTxnId};
			dbUtil.createPreparedStatement(PropertyValuationSQL.GET_ALL_KHASRA_LIST_WRT_VALTXNID);
			khasraList = dbUtil.executeQuery(param);
			for (int i = 0; i < khasraList.size(); i++) {

				ArrayList temp = (ArrayList) khasraList.get(i);
				singleArr.add(temp.get(0));
			}

			dbUtil.closeConnection();
		} catch (Exception exception) {
			logger.info("Exception in getKhasraListClr" + exception);
		}
		return singleArr;
	}

	// create by Rustam

	public ArrayList getConstructionSlabList(String language) {
		ArrayList slabList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();

			if ("en".equalsIgnoreCase(language)) {
				return slabList = dbUtil.executeQuery(PropertyValuationSQL.CONST_SLAB_LIST_eng);
			} else {
				return slabList = dbUtil.executeQuery(PropertyValuationSQL.CONST_SLAB_LIST_hin);

			}
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public ArrayList getConstructionSlabListBySlabMapId(int slabMapId) {
		ArrayList slabList = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			int[] param = {slabMapId};
			dbUtil.createPreparedStatement(PropertyValuationSQL.CONST_SLAB_LIST_BY_SLAB_MAP_ID);
			return slabList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
}