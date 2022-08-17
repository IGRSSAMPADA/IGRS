package com.wipro.igrs.newPropvaluation.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.wipro.igrs.newPropvaluation.action.CalculateBuilding;
import com.wipro.igrs.newPropvaluation.action.CalculatePlot;
import com.wipro.igrs.newPropvaluation.action.CalculationAgriLand;
import com.wipro.igrs.newPropvaluation.constant.PropertyValuationConstant;
import com.wipro.igrs.newPropvaluation.dao.PropertyValuationDAO;
import com.wipro.igrs.newPropvaluation.dto.ClrKhasraDetailsDTO;
import com.wipro.igrs.newPropvaluation.dto.KhasraClrDTO;
import com.wipro.igrs.newPropvaluation.dto.OwnerDetlsDTO;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;

import com.wipro.igrs.newPropvaluation.dto.TreeDTO;
import com.wipro.igrs.newPropvaluation.form.PropertyValuationForm;
import com.wipro.igrs.newPropvaluation.action.CalculationAgriLandDevAgree;

public class PropertyValuationBO {
	private static Logger logger = org.apache.log4j.Logger.getLogger(PropertyValuationBO.class);
	private PropertyValuationDAO propDAO;
	CalculationAgriLand cal;
	CalculationAgriLandDevAgree calDevAgree;
	public PropertyValuationBO() throws Exception {
		propDAO = new PropertyValuationDAO();
		cal = new CalculationAgriLand();
		calDevAgree = new CalculationAgriLandDevAgree();
	}
	public ArrayList<PropertyValuationDTO> getTehsil(String language, String districtId) throws Exception {

		ArrayList tehsilList = null;
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			tehsilList = propDAO.getTehsil(language, districtId);
			if (tehsilList != null && tehsilList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < tehsilList.size(); i++) {
					ArrayList subList = (ArrayList) tehsilList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setTehsilId((String) subList.get(0));
					propDTO.setTehsilName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getArea(String language) throws Exception {

		ArrayList areaList = null;
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			areaList = propDAO.getArea(language);
			if (areaList != null && areaList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < areaList.size(); i++) {
					ArrayList subList = (ArrayList) areaList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setAreaId((String) subList.get(0));
					propDTO.setAreaName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getArea2(String language, String instId) throws Exception// CREATED BY ROOPAM - 14 APRIL 2015
	{

		ArrayList areaList = null;
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			areaList = propDAO.getArea2(language, instId);
			if (areaList != null && areaList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < areaList.size(); i++) {
					ArrayList subList = (ArrayList) areaList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setAreaId((String) subList.get(0));
					propDTO.setAreaName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getSubArea(String language, String areaId, String tehsilId, String urbanFlag) {
		ArrayList subAreaList = null;
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			subAreaList = propDAO.getSubArea(language, areaId, tehsilId, urbanFlag);
			if (subAreaList != null && subAreaList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < subAreaList.size(); i++) {
					ArrayList subList = (ArrayList) subAreaList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setSubAreaId((String) subList.get(0));
					propDTO.setSubAreaName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}
	public ArrayList<PropertyValuationDTO> getWardPatwari(String language, String subAreaId, String tehsilId) {
		ArrayList wardPatwariList = null;
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			wardPatwariList = propDAO.getWardPatwari(language, subAreaId, tehsilId);
			if (wardPatwariList != null && wardPatwariList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < wardPatwariList.size(); i++) {
					ArrayList subList = (ArrayList) wardPatwariList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setWardPatwariId((String) subList.get(0) + "~" + (String) subList.get(2));
					propDTO.setWardPatwariName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public String getDistrictName(String language, String districtId) {
		return propDAO.getDistrictName(language, districtId);
	}

	public String getDistrictClrFlag(String language, String districtId) {
		return propDAO.getDistrictClrFlag(language, districtId);
	}

	public String getTehsilClrFlag(String language, String tehsilID) {
		return propDAO.getTehsilClrFlag(language, tehsilID);
	}
	
	public String getTehsilClrUpdatedFlag(String language, String tehsilID) {
		return propDAO.getTehsilClrUpdatedFlag(language, tehsilID);
	}
	
	public ArrayList<PropertyValuationDTO> getPropertyType(String language, String instId) {
		ArrayList propertyList = null;
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			propertyList = propDAO.getPropertyType(language, instId);
			if (propertyList != null && propertyList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < propertyList.size(); i++) {
					ArrayList subList = (ArrayList) propertyList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setPropertyId((String) subList.get(0));
					propDTO.setPropertyName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getBuildingType(String language) {
		ArrayList buildingTypeList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			buildingTypeList = propDAO.getBuildingType(language);
			if (buildingTypeList != null && buildingTypeList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < buildingTypeList.size(); i++) {
					ArrayList subList = (ArrayList) buildingTypeList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setBuildingTypeId((String) subList.get(0));
					propDTO.setBuildingTypeName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}
	public ArrayList<PropertyValuationDTO> getL1Name(String language, PropertyValuationDTO propDTO1) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL1Name(language, propDTO1);
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setOpenTerraceUsageId((String) subList.get(0));
					propDTO.setOpenTerraceUsageName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getMultiStoreyType(String language, PropertyValuationDTO propDTO1) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL1Name(language, propDTO1);
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setMultiStoreyTypeId((String) subList.get(0));
					propDTO.setMultiStoreyTypeName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getMultiCommPropertyName(String language, String type) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getMultiCommPropName(language, type);
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setMultiCommPropId((String) subList.get(0));
					propDTO.setMultiCommPropName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getIndepenBuildPropType(String language, PropertyValuationDTO propDTO1) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL1Name(language, propDTO1);
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setIndenBuildPropId((String) subList.get(0));
					propDTO.setIndenBuildPropName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getResiL2Name(String language) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL2Name(language, "7");
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setResiL2Id((String) subList.get(0));
					propDTO.setResiL2Name((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getCommL2Name(String language) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL2Name(language, "8");
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setCommL2Id((String) subList.get(0));
					propDTO.setCommL2Name((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getIndL2Name(String language) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL2Name(language, "10");
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setIndL2Id((String) subList.get(0));
					propDTO.setIndL2Name((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getSchoolL2Name(String language) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL2Name(language, "12");
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setSchoolL2Id((String) subList.get(0));
					propDTO.setSchoolL2Name((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getHealthL2Name(String language) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL2Name(language, "11");
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setHealthL2Id((String) subList.get(0));
					propDTO.setHealthL2Name((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getOtherL2Name(String language) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL2Name(language, "13");
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setOtherL2Id((String) subList.get(0));
					propDTO.setOtherL2Name((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getResiCommL2Name(String language) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getL2Name(language, "32");
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setResiCommId((String) subList.get(0));
					propDTO.setResiCommName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getFloorName(String language, String string) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			if (string.equalsIgnoreCase("RESI")) {
				l1NameList = propDAO.getFloorName(language, "18");
			} else {
				l1NameList = propDAO.getFloorName(language, "19");
			}

			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setFloorId((String) subList.get(0));
					propDTO.setFloorName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getColonyName(String language, String wardPatwariId) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getColonyName(language, wardPatwariId);
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setColonyId((String) subList.get(0) + "~" + (String) subList.get(2) + "~" + (String) subList.get(3));
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
	public ArrayList<PropertyValuationDTO> getColonyName2(String language, String wardPatwariId, String instId) {
		ArrayList l1NameList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			l1NameList = propDAO.getColonyName2(language, wardPatwariId, instId);
			if (l1NameList != null && l1NameList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < l1NameList.size(); i++) {
					ArrayList subList = (ArrayList) l1NameList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setColonyId((String) subList.get(0) + "~" + (String) subList.get(2) + "~" + (String) subList.get(3));
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
	public String submitOpenTerraceDetails(PropertyValuationDTO propDTO) {

		String val_id = "";
		String buildId = "";
		if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
			val_id = "";
		} else {
			val_id = propDAO.getValuationId();
			propDTO.setValuationId(val_id);
		}
		buildId = propDAO.getBuildingId();
		propDTO.setBuildingTxnId(buildId);
		boolean flag = propDAO.submitOpenTerraceDetails(propDTO);
		if (flag == true) {
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				return buildId;
			} else {
				return val_id;
			}
		} else {
			return "";
		}

	}
	public String submitIndependentFloorDetails(PropertyValuationDTO propDTO) {

		String val_id = "";
		String buildId = "";
		if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
			val_id = "";
		} else {
			val_id = propDAO.getValuationId();
			propDTO.setValuationId(val_id);
		}
		buildId = propDAO.getBuildingId();
		propDTO.setBuildingTxnId(buildId);
		boolean flag = propDAO.submitIndependentFloorDetails(propDTO);
		if (flag == true) {
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				return buildId;
			} else {
				return val_id;
			}
		} else {
			return "";
		}
	}
	public String submitMultiStoreyDetails(PropertyValuationDTO propDTO, String userId) {
		String val_id = "";
		String buildId = "";
		String isConstruct = propDTO.getIsConstruction();
		if ("construct".equalsIgnoreCase(isConstruct)) {
			val_id = "";
		} else {
			val_id = propDAO.getValuationId();
			propDTO.setValuationId(val_id);
		}
		buildId = propDAO.getBuildingId();
		propDTO.setBuildingTxnId(buildId);
		boolean flag = propDAO.submitMultiStoreyDetails(propDTO, userId);
		if (flag == true) {
			if ("construct".equalsIgnoreCase(isConstruct)) {
				return buildId;
			} else {
				return val_id;
			}
		} else {
			return "";
		}
	}
	public String submitIndependentBuildingDetails(PropertyValuationDTO propDTO) {
		String val_id = "";
		String buildId = "";
		if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
			val_id = "";
		} else {
			val_id = propDAO.getValuationId();
			propDTO.setValuationId(val_id);
		}
		buildId = propDAO.getBuildingId();
		propDTO.setBuildingTxnId(buildId);
		boolean flag = propDAO.submitIndependentBuildingDetails(propDTO);
		if (flag == true) {
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				return buildId;
			} else {
				return val_id;
			}
		} else {
			return "";
		}
	}

	// --added by satbir kumar for agriLand--

	public ArrayList<PropertyValuationDTO> getAgriLandType(String language, String deedId, String instId) {
		ArrayList agriLandTypeList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			agriLandTypeList = propDAO.getAgriLandType(language, deedId, instId);
			if (agriLandTypeList != null && agriLandTypeList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < agriLandTypeList.size(); i++) {
					ArrayList subList = (ArrayList) agriLandTypeList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setAgriLandTypeId((String) subList.get(0));
					propDTO.setAgriLandTypeName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getAgriTreeType(String language) {

		ArrayList agriTreeTypeList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			agriTreeTypeList = propDAO.getAgriTreeType(language);
			if (agriTreeTypeList != null && agriTreeTypeList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < agriTreeTypeList.size(); i++) {
					ArrayList subList = (ArrayList) agriTreeTypeList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setAgriTreeTypeId((String) subList.get(0));
					propDTO.setAgriTreeTypeName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getAgriSubTreeGrt45Type(String language, String treeCategoryId) {
		ArrayList agriSubTreeTypeGrt45List = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			agriSubTreeTypeGrt45List = propDAO.getAgriSubTreeGrt45Type(language, treeCategoryId);
			if (agriSubTreeTypeGrt45List != null && agriSubTreeTypeGrt45List.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < agriSubTreeTypeGrt45List.size(); i++) {
					ArrayList subList = (ArrayList) agriSubTreeTypeGrt45List.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setAgriSubTreeTypeId((String) subList.get(0));
					propDTO.setAgriSubTreeTypeName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}
	public ArrayList<PropertyValuationDTO> getAgriSubTreeles45Type(String language, String treeCategoryId) {
		ArrayList agriSubTreeTypeles45List = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			agriSubTreeTypeles45List = propDAO.getAgriSubTreeles45Type(language, treeCategoryId);
			if (agriSubTreeTypeles45List != null && agriSubTreeTypeles45List.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < agriSubTreeTypeles45List.size(); i++) {
					ArrayList subList = (ArrayList) agriSubTreeTypeles45List.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setAgriSubTreeTypeId((String) subList.get(0));
					propDTO.setAgriSubTreeTypeName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public boolean insertAgriBuyerTreeDetails(TreeDTO dto, PropertyValuationForm propForm, String language, String userId) {

		boolean Insert = false;
		try {
			Insert = propDAO.insertBuyerTreeDetails(dto, propForm, language, userId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Insert;
	}

	public String getPlotId() throws Exception {
		String plotId = "";

		plotId = propDAO.getPlotId();

		return plotId;

	}

	public String insertPlot(PropertyValuationDTO propDTO, String edu, String health) {
		String valId = "";
		try {
			valId = propDAO.insertPlotDetails(propDTO, edu, health);
		} catch (Exception e) {
			logger.error(e);

		}
		return valId;

	}

	public void insertSubclauseValues(PropertyValuationDTO propDTO) {
		try {
			propDAO.insertSubclauseValues(propDTO, propDTO.getValuationId(), propDTO.getUserId());
		} catch (Exception e) {
			logger.error(e);

		}

	}

	public String[] getPlotMarketValue(PropertyValuationDTO propDTO, String eduTcp, String healthTcp) throws Exception {
		CalculatePlot calPlot = new CalculatePlot();
		String marketValue = "";
		String finalValue = "";
		String[] market = new String[2];

		marketValue = calPlot.calcWithoutSubclauses(propDTO, eduTcp, healthTcp);

		if (propDTO.getPlotSubId() != "") {
			String subIds = propDTO.getPlotSubId();
			String sId[] = subIds.split("~");

			finalValue = calPlot.calcWithSubclauses(marketValue, sId, propDTO.getPlotOnlyResi());

		} else {
			finalValue = marketValue;
		}

		market[0] = marketValue;
		market[1] = finalValue;
		return market;
	}

	public ArrayList getSubclauseData(PropertyValuationDTO propDTO, String locale) {
		ArrayList plotSubclauseList = new ArrayList();
		ArrayList retList = new ArrayList();
		try {
			plotSubclauseList = propDAO.getSubclauseData(propDTO);
			for (int i = 0; i < plotSubclauseList.size(); i++) {
				ArrayList subList = (ArrayList) plotSubclauseList.get(i);
				PropertyValuationDTO propDTO1 = new PropertyValuationDTO();
				propDTO1.setPlotSubClauseId((String) subList.get(0));
				if (locale.equalsIgnoreCase("en")) {
					propDTO1.setPlotSubClauseName((String) subList.get(1));
				} else {
					propDTO1.setPlotSubClauseName((String) subList.get(3));
				}
				retList.add(propDTO1);
			}
		} catch (Exception e) {
			logger.error(e);

		}
		return retList;

	}

	public String insertMainTxnId(String language, String userId) {
		String InsertMainTxnId = "";
		try {
			InsertMainTxnId = propDAO.insertMainTxnId(language, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return InsertMainTxnId;
	}

	public String censusId(String colonyId) {
		String censusId = "";
		try {
			censusId = propDAO.censusId(colonyId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return censusId;
	}
	
	public String lgdCode(String colonyId) {
		String lgdCode = "";
		try {
			lgdCode = propDAO.lgdCode(colonyId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lgdCode;
	}

	public ArrayList<PropertyValuationDTO> getSubclauseDataBuilding(PropertyValuationDTO propDTO, String language, String agriFlag) {
		ArrayList plotSubclauseList = new ArrayList();
		ArrayList retList = new ArrayList();
		try {
			plotSubclauseList = propDAO.getSubclauseDataBuilding(propDTO, agriFlag);
			for (int i = 0; i < plotSubclauseList.size(); i++) {
				ArrayList subList = (ArrayList) plotSubclauseList.get(i);
				PropertyValuationDTO propDTO1 = new PropertyValuationDTO();
				if ((PropertyValuationConstant.IND_BUILD_COMM_OLD_50).equals(subList.get(0)) || (PropertyValuationConstant.IND_BUILD_COMM_OLD_25).equals(subList.get(0))) {
					if ("Y".equalsIgnoreCase(propDTO.getCommFlag())) {
						propDTO1.setPlotSubClauseId((String) subList.get(0));
						if (language.equalsIgnoreCase("en")) {
							propDTO1.setPlotSubClauseName((String) subList.get(1));
						} else {
							propDTO1.setPlotSubClauseName((String) subList.get(3));
						}
						retList.add(propDTO1);
					}
				} else {
					propDTO1.setPlotSubClauseId((String) subList.get(0));
					if (language.equalsIgnoreCase("en")) {
						propDTO1.setPlotSubClauseName((String) subList.get(1));
					} else {
						propDTO1.setPlotSubClauseName((String) subList.get(3));
					}
					retList.add(propDTO1);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return retList;

	}

	public String[] getundivertedValue(PropertyValuationDTO undivpropDTO, PropertyValuationDTO propDTO) {
		String unDivValue[] = new String[2];

		unDivValue = cal.calcUndiverted(undivpropDTO, propDTO);

		return unDivValue;
	}
	public String[] getGuidelineValuesBuilding(PropertyValuationDTO propDTO) {
		CalculateBuilding build;
		try {
			build = new CalculateBuilding();
			return build.calcWithoutSubclauses(propDTO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	public String getValueWithInternalSubClause(String baseValue, PropertyValuationDTO propDTO) {
		CalculateBuilding build;
		try {
			build = new CalculateBuilding();
			return build.calcInternalSubclauses(baseValue, propDTO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public String updateFinalMarketValue(TreeDTO dto, PropertyValuationForm propForm, String language, String userId) {

		String FinalMarketValue = "";
		try {
			FinalMarketValue = propDAO.updateFinalMarketValue(dto, propForm, language, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return FinalMarketValue;
	}
	// below created by Roopam
	public String[] getBuildingValueWithSubClause(String baseValue, String ids[], PropertyValuationDTO propDTO) {
		CalculateBuilding build;
		try {
			build = new CalculateBuilding();
			return build.calcWithSubclauses(baseValue, ids, propDTO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	public String[] getTotalTreeValue(TreeDTO dto, PropertyValuationDTO propDTO) {
		String totalTreeValue[] = new String[2];

		totalTreeValue = cal.calcTotalTreeValue(dto, propDTO);

		return totalTreeValue;
	}
	public boolean updateGuidelineMV(TreeDTO dto, PropertyValuationForm propForm, String language, String userId) {
		boolean update = false;
		try {
			update = propDAO.updateGuidelineMV(dto, propForm, language, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return update;
	}
	public ArrayList<PropertyValuationDTO> getAgriSubClauseList(String language, PropertyValuationDTO propDTO) {

		ArrayList agriSubClauseList = new ArrayList();
		ArrayList retList = new ArrayList();
		try {
			agriSubClauseList = propDAO.getAgriSubClauseList(propDTO);
			for (int i = 0; i < agriSubClauseList.size(); i++) {
				ArrayList subList = (ArrayList) agriSubClauseList.get(i);
				PropertyValuationDTO propDTO1 = new PropertyValuationDTO();
				propDTO1.setAgriSubClauseId((String) subList.get(0));
				if (language.equalsIgnoreCase("en")) {
					propDTO1.setAgriSubClauseName((String) subList.get(1));
				} else {
					propDTO1.setAgriSubClauseName((String) subList.get(3));
				}
				retList.add(propDTO1);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return retList;
	}
	public String[] getdivertedValue(PropertyValuationDTO divpropDTO, PropertyValuationDTO propDTO) {

		String DivValue[] = new String[2];

		DivValue = cal.calcDiverted(divpropDTO, propDTO);

		return DivValue;
	}

	public double getFloorValue(PropertyValuationDTO[] dto) {
		CalculateBuilding build;
		try {
			build = new CalculateBuilding();
			return build.calculateFloorValue(dto);
		} catch (Exception e) {
			logger.error(e);
			return 0.0;
		}
	}

	public String getGuideLineId(PropertyValuationDTO propDTO) {
		String guidelineId = "";
		try {
			guidelineId = propDAO.getGuideLineId(propDTO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		return guidelineId;
	}
	public void insertFinalPlot(PropertyValuationDTO propDTO) {
		propDAO.insertFinalPlot(propDTO);

	}

	// ADDED BY SIMRAN

	public String getValueWithSubClause(String baseValue, PropertyValuationDTO propDTO) {
		CalculateBuilding build;
		try {
			build = new CalculateBuilding();
			Double finalVal = build.calcWithSubclauses(baseValue, propDTO);
			return String.valueOf(finalVal);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public String getBulidingName(String buildingid, String language) {
		return propDAO.getBuildingName(buildingid, language);
	}

	public String[] getbothValue(PropertyValuationDTO bothpropDTO, PropertyValuationDTO propDTO) {
		String bothvalue[] = new String[3];

		bothvalue = cal.calculateBoth(bothpropDTO, propDTO);

		return bothvalue;
	}

	public boolean multiStroreyCommercialType(PropertyValuationDTO propDTO) {
		String rate = propDAO.getL1Rate(propDTO, "19", "2");
		if (rate == null || rate.equalsIgnoreCase("") || Double.parseDouble(rate) == 0) {
			return false;

		} else {
			return true;
		}

	}
	public ArrayList<PropertyValuationDTO> getAgriTotalPropertyList(String agriMainValTxnId) {

		ArrayList agriTotalPropertyList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			agriTotalPropertyList = propDAO.agriTotalPropertyList(agriMainValTxnId);
			if (agriTotalPropertyList != null && agriTotalPropertyList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < agriTotalPropertyList.size(); i++) {
					ArrayList subList = (ArrayList) agriTotalPropertyList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					double value = Double.parseDouble((String) subList.get(0));
					value = Math.ceil(value);
					propDTO.setAgriIndivPropertyValue(BigDecimal.valueOf(value).toPlainString());
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList<PropertyValuationDTO> getAgriRefrncIdFinalMVList(String agriMainValTxnId) {
		ArrayList agriRefrncIdFinalMVList = new ArrayList();
		ArrayList retList = new ArrayList();
		try {
			agriRefrncIdFinalMVList = propDAO.getAgriRefrncIdFinalMVList(agriMainValTxnId);
			for (int i = 0; i < agriRefrncIdFinalMVList.size(); i++) {
				ArrayList subList = (ArrayList) agriRefrncIdFinalMVList.get(i);
				PropertyValuationDTO propDTO1 = new PropertyValuationDTO();

				propDTO1.setAgriRefrncId((String) subList.get(0));
				propDTO1.setAgriIndivFinalMV((String) subList.get(1));

				retList.add(propDTO1);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return retList;
	}
	public String getMuncipalFlag(String subAreaId) {
		if (subAreaId.equalsIgnoreCase("5")) {
			return "RF";
		} else {
			String municipalId = propDAO.getMunicipalId(subAreaId);
			if ("1".equalsIgnoreCase(municipalId) || "2".equalsIgnoreCase(municipalId) || "5".equalsIgnoreCase(municipalId) || "3".equalsIgnoreCase(municipalId)) {
				return "NF";
			} else if ("4".equalsIgnoreCase(municipalId)) {
				return "RF";
			} else {
				return "N";
			}
		}
	}

	// create by Rustam
	public ArrayList<PropertyValuationDTO> getConstructionSlabList(String language) {
		ArrayList slabList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			slabList = propDAO.getConstructionSlabList(language);
			if (slabList != null && slabList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < slabList.size(); i++) {
					ArrayList subList = (ArrayList) slabList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setOlderId((Integer.parseInt(subList.get(0).toString())));
					propDTO.setDurationYear((String) subList.get(1));
					propDTO.setOlderOperand((Integer.parseInt(subList.get(2).toString())));
					propDTO.setOperatorname((String) subList.get(3));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList<PropertyValuationDTO> getConstructionSlabListBySlabMapId(int slabMapId) {
		ArrayList slabList = new ArrayList();
		ArrayList<PropertyValuationDTO> returnList = null;
		try {
			slabList = propDAO.getConstructionSlabListBySlabMapId(slabMapId);
			if (slabList != null && slabList.size() > 0) {
				returnList = new ArrayList<PropertyValuationDTO>();
				for (int i = 0; i < slabList.size(); i++) {
					ArrayList subList = (ArrayList) slabList.get(i);
					PropertyValuationDTO propDTO = new PropertyValuationDTO();
					propDTO.setOlderId((Integer.parseInt(subList.get(0).toString())));
					propDTO.setDurationYear((String) subList.get(1));
					propDTO.setOlderOperand((Integer.parseInt(subList.get(2).toString())));
					propDTO.setOperatorname((String) subList.get(3));
					returnList.add(propDTO);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList getRinPustikaNo(String regTxnId) {
		return propDAO.getRinPustikaNo(regTxnId);
	}
	
	public ArrayList getKhasraDbLink(String lgdCode) {
		return propDAO.getKhasraDbLink(lgdCode);
	}
	public ArrayList getValTxnId(String regTxnId) {
		return propDAO.getValTxnId(regTxnId);
	}
	public String getColonyId(String valTxnId) {
		return propDAO.getColonyId(valTxnId);
	}

	public ArrayList getKhasraListClr(String valTxnId) {
		return propDAO.getKhasraListClr(valTxnId);
	}
	// added by ankit saurav for developer agreement
	public String getdivertedValueDevAgree(PropertyValuationDTO divpropDTO, PropertyValuationDTO propDTO) {

		String DivValue[] = new String[2];

		DivValue = calDevAgree.calcDivertedDevAgree(divpropDTO, propDTO);

		return DivValue[1];
	}
	// added by ankit saurav for developer agreement
	public String getundivertedValueDevAgree(PropertyValuationDTO undivpropDTO, PropertyValuationDTO propDTO) {
		String unDivValue[] = new String[2];

		unDivValue = calDevAgree.calcUndivertedDevAgree(undivpropDTO, propDTO);

		return unDivValue[1];
	}
	// added by ankit saurav for developer agreement
	public String[] getbothValueDevAgree(PropertyValuationDTO bothpropDTO, PropertyValuationDTO propDTO) {
		String bothvalue[] = new String[3];

		bothvalue = calDevAgree.calculateBothDevAgree(bothpropDTO, propDTO);

		return bothvalue;
	}

	// added for developer agreement
	public String getPlotMarketValueDevAgree(PropertyValuationDTO propDTO, String eduTcp, String healthTcp) throws Exception {
		CalculatePlot calPlot = new CalculatePlot();
		String marketValue = "";
		String finalValue = "";
		String[] market = new String[2];

		marketValue = calPlot.calcWithoutSubclausesDevAgree(propDTO, eduTcp, healthTcp);

		if (propDTO.getPlotSubId() != "") {
			String subIds = propDTO.getPlotSubId();
			String sId[] = subIds.split("~");

			finalValue = calPlot.calcWithSubclauses(marketValue, sId, propDTO.getPlotOnlyResi());

		} else {
			finalValue = marketValue;
		}

		market[0] = marketValue;
		market[1] = finalValue;
		return market[1];
	}
public String getInstrumentClrFlag(String instId) {
	return propDAO.getInstrumentClrFlag(instId);
}

	public ClrKhasraDetailsDTO getKhasraDetailsFromDbLink(String khasraId, String lgdCode) throws Exception {

		ArrayList khasraList = null;
		ClrKhasraDetailsDTO returnList = null;
		try {
			khasraList = propDAO.getKhasraDetailsFromDbLink(khasraId, lgdCode);
			if (khasraList != null && khasraList.size() > 0) {
				returnList = new ClrKhasraDetailsDTO();
				for (int i = 0; i < khasraList.size(); i++) {
					ArrayList subList = (ArrayList) khasraList.get(i);
					ClrKhasraDetailsDTO dto = new ClrKhasraDetailsDTO();
				dto.setDistCodeClr(subList.get(0)==null ?"" : subList.get(0).toString());
				dto.setTehCodeClr(subList.get(1)==null ?"" :subList.get(1).toString());
				dto.setRiCircleClr(subList.get(2) ==null ?"" :subList.get(2).toString());
				dto.setHalkaNoClr(subList.get(3) ==null ?"" :subList.get(3).toString() );
				dto.setBhucodeClr(subList.get(4)==null ?"" : subList.get(4).toString());
				//dto.setLandOwnershipIdClr(subList.get(5)==null ?"" : subList.get(5).toString());
				//dto.setLandOwnernameClr(subList.get(6)==null ?"" : subList.get(6).toString());
				//dto.setFatherNameClr(subList.get(7)==null ?"" :subList.get(7).toString());
				dto.setBasraNumberClr(subList.get(5)==null ?"" :subList.get(5).toString());
				dto.setKhasraIdClr(subList.get(6)==null ?"" :subList.get(6).toString());
				dto.setKhasraNumberCLR(subList.get(7)==null ?"" : subList.get(7).toString());
				dto.setKhasraAreaClr(subList.get(8)==null ?"" :subList.get(8).toString());
				//dto.setOwnerTypeClr(subList.get(12)==null ?"" :subList.get(12).toString());
				dto.setLandTypeClr(subList.get(9)==null ?"" :subList.get(9).toString());
				//dto.setIsIrrigatedClr(subList.get(14)==null ?"" :subList.get(14).toString());
				//dto.setOwnerShareClr(subList.get(15)==null ?"" :subList.get(15).toString());
				//dto.setIsDivertedClr(subList.get(16)==null ?"" :subList.get(16).toString());
				dto.setActionStatusClr(subList.get(10)==null ?"" :subList.get(10).toString());
				dto.setLgdCodeClr(subList.get(11)==null ?"" :subList.get(11).toString());
				dto.setPlotTypeClr(subList.get(12)==null ?"" :subList.get(12).toString());
				dto.setLandUseClr(subList.get(13)==null ?"" :subList.get(13).toString());
				dto.setNoyiyatClr(subList.get(14)==null ?"" :subList.get(14).toString());
				dto.setCropClr(subList.get(15)==null ?"" :subList.get(15).toString());
				dto.setUlPinClr(subList.get(16)==null ?"" :subList.get(16).toString());
				dto.setAlPinClr(subList.get(17)==null ?"" :subList.get(17).toString());
					returnList=dto;

					if(dto.getLandUseClr()!=null && !dto.getLandUseClr().isEmpty()){
						
						String landUseTypeDesc = "";
						
						String landUseType = dto.getLandUseClr();
						
						 landUseTypeDesc = propDAO.getLandDesc(landUseType);
						dto.setLandTypeDesc(landUseTypeDesc);
						
						
					}
					else{
						dto.setLandTypeDesc("");
					}
					

					if(dto.getPlotTypeClr()!=null && !dto.getPlotTypeClr().isEmpty()){
						
						String getPlotTypeClrDesc = "";
						
						String plotType = dto.getPlotTypeClr().toString();
						
						getPlotTypeClrDesc = propDAO.getPlotDesc(plotType);
						dto.setPlotTypeDesc(getPlotTypeClrDesc);
						
						
					}
					else{
						dto.setPlotTypeDesc("");
					}
					
	             if(dto.getCropClr()!=null && !dto.getCropClr().isEmpty()){
						
						String cropDesc = "";
						
						String plotType = dto.getCropClr().toString();
						
						cropDesc = propDAO.getCropDesc(plotType);
						dto.setCropTypeDesc(cropDesc);
						
						
					}
	             else{
	            	 dto.setCropTypeDesc("");
	             }
					
				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}



	public ArrayList<OwnerDetlsDTO> getOwnerDetails(String khasraId, String lgdCode) throws Exception {

		ArrayList ownerList = null;
		ArrayList<OwnerDetlsDTO> returnList = null;
		try {
			ownerList = propDAO.OwnerDetlsDTO(khasraId, lgdCode);
			if (ownerList != null && ownerList.size() > 0) {
				returnList = new ArrayList<OwnerDetlsDTO>();
				for (int i = 0; i < ownerList.size(); i++) {
					ArrayList subList = (ArrayList) ownerList.get(i);
					OwnerDetlsDTO dto = new OwnerDetlsDTO();
				dto.setOwnerId(subList.get(0)==null ?"-" : subList.get(0).toString());
				dto.setOwnername(subList.get(1)==null ?"-" :subList.get(1).toString());
				dto.setFatherName(subList.get(2) ==null ?"-" :subList.get(2).toString());
				dto.setOwnerType(subList.get(3) ==null ?"-" :subList.get(3).toString() );
				dto.setOwnerShare(subList.get(4)==null ?"-" : subList.get(4).toString());
		
					returnList.add(dto);

				}

			}

			return returnList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
}

