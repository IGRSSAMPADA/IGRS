package com.wipro.igrs.regcompletion.bd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.regcompletion.constants.RegCompConstant;
import com.wipro.igrs.regcompletion.dto.RegCompletDTO;

/**
 * ===========================================================================
 * File : DocumentSearchForm.java Description : Represents the SQL Class
 * 
 * Author : Hari Krishna G.V Created Date : Jan 5, 2008
 * 
 * ===========================================================================
 */
public class CommonDropDownBD {
	IGRSCommon common;
	private  Logger logger = 
		(Logger) Logger.getLogger(CommonDropDownBD.class);

	/**
	 * @param _fdate
	 * @return
	 */
	public String getDate(String _fdate) {
		logger.debug("in bd for get date" + _fdate);

		StringTokenizer stoken = new StringTokenizer(_fdate, "/");
		String dd = stoken.nextToken();
		String mm = stoken.nextToken();
		String yy = stoken.nextToken();
		if (dd.length() == 2) {
			_fdate = mm + "-" + getMonthName(dd) + "-" + yy;
		}

		logger.debug(_fdate);
		return _fdate;

	}

	/**
	 * @param _month
	 * @return
	 */
	public String getMonthName(String _month) {
		logger.debug("in bd for get mon");
		HashMap hm = new HashMap();
		hm.put("01", "JAN");
		hm.put("02", "FEB");
		hm.put("03", "MAR");
		hm.put("04", "APR");
		hm.put("05", "MAY");
		hm.put("06", "JUN");
		hm.put("07", "JUL");
		hm.put("08", "AUG");
		hm.put("09", "SEP");
		hm.put("10", "OCT");
		hm.put("11", "NOV");
		hm.put("12", "DEC");
		return (String) hm.get(_month);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrict() throws Exception {
		ArrayList distList = new ArrayList();

		try {
			common = new IGRSCommon();
			ArrayList tmpList = common.getDistrict();
			// String seqNum=common.getSequenceNumber("Dummy","REG");
			// logger.debug("===seqNum=="+seqNum);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setDistId((String) tmpsubList.get(0) + "~"
							+ (String) tmpsubList.get(1));
					dto.setDistName((String) tmpsubList.get(1));
					distList.add(dto);
				}

			}
			
		} catch (Exception e) {
		
			logger.error(e);

		}
		return distList;
	}

	/**
	 * @param _distId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getTehisil(String _distId) throws Exception {
		ArrayList tehsilList = new ArrayList();

		try {
			common = new IGRSCommon();

			String[] districtId = _distId.split("~");
			_distId = districtId[0];
			ArrayList tmpList = common.getTehsil(_distId);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setTehsilId((String) tmpsubList.get(0) + "~"
							+ (String) tmpsubList.get(1));
					dto.setTehsilName((String) tmpsubList.get(1));
					tehsilList.add(dto);
				}

			}
			
		} catch (Exception e) {
			
			logger.error(e);
		}
		return tehsilList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAreaType() throws Exception {
		ArrayList areaTypeList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getAreaType();
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setAreaTypeId((String) tmpsubList.get(0) + "~"
							+ (String) tmpsubList.get(1));
					dto.setAreaTypeName((String) tmpsubList.get(1));
					areaTypeList.add(dto);
				}
			}
			
		} catch (Exception e) {
			logger.error(e);

		}
		return areaTypeList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyTypeList() throws Exception {
		ArrayList propertyTypeList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getPropertyTypeList();
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setPropertyTypeId((String) tmpsubList.get(0) + "~"
							+ (String) tmpsubList.get(1));
					dto.setPropertyTypeName((String) tmpsubList.get(1));
					propertyTypeList.add(dto);
				}
			}
			
		} catch (Exception e) {
			logger.error(e);

		}
		return propertyTypeList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyTypeDisp(String _propertyTypeID)
			throws Exception {
		ArrayList propertyTypeList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			String[] propertyUsageId = _propertyTypeID.split("~");
			ArrayList tmpList = common.getPropertyTypeDisp(propertyUsageId[0]);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setPropertyTypeId((String) tmpsubList.get(0) + "~"
							+ tmpsubList.get(1));
					dto.setPropertyTypeName((String) tmpsubList.get(1));
					propertyTypeList.add(dto);
				}
			}
			
		} catch (Exception e) {
			logger.error(e);

		}
		return propertyTypeList;
	}

	// madan sub caluse
	/**
	 * @param districtId
	 * @param tehsilId
	 * @param wardId
	 * @param mohallaId
	 * @param propertyId
	 * @return ArrayList
	 */
	/*
	 * public ArrayList<PropertyValuationDTO> getSubClause(
	 * PropertyValuationDTO useDTO) {
	 * 
	 * ArrayList<PropertyValuationDTO> listDTO = new ArrayList<PropertyValuationDTO>();
	 * String[] districtId = useDTO.getDistrictID().split("~"); String[]
	 * tehsilId = useDTO.getTehsilID().split("~"); String[] wardId =
	 * useDTO.getWardId().split("~"); String[] mohallaId =
	 * useDTO.getMahallaId().split("~"); String[] propertyId =
	 * useDTO.getPropertyTypeID().split("~"); String[] usageType =
	 * useDTO.getUsePlotId().split("~"); String[] param = null; try {
	 * 
	 * PropertiesFileReader pr = PropertiesFileReader.
	 * getInstance("com.wipro.igrs.propertyvaluation"); String buildingID =
	 * pr.getValue(CommonConstant.BUILDING_ID); String SQL = "";
	 * logger.debug("Property Type:-"+propertyId);
	 * 
	 * if(propertyId != null && propertyId.length == 2) { String propertyTypeId =
	 * propertyId[0]; if(buildingID.equals(propertyTypeId)) { param = new
	 * String[8]; String[] l2Id = useDTO.getCeilingTypeId().split("~"); String[]
	 * floorId = useDTO.getTypeFloorID().split("~");
	 * 
	 * if(floorId != null && floorId.length ==3) { param[0] = floorId[0]; } if
	 * (districtId != null && districtId.length == 2) { param[1] =
	 * districtId[0]; } if (tehsilId != null && tehsilId.length == 2) { param[2] =
	 * tehsilId[0]; } if (wardId != null && wardId.length == 2) { param[3] =
	 * wardId[0]; } if (mohallaId != null && mohallaId.length == 2) { param[4] =
	 * mohallaId[0]; } if (propertyId != null && propertyId.length == 2) {
	 * param[5] = propertyId[0];
	 *  } if(usageType != null && usageType.length ==3) { param[6] =
	 * usageType[0]; } if(l2Id != null && l2Id.length ==3) { param[7] = l2Id[0]; }
	 * 
	 * SQL = CommonSQL.SUB_CLAUSE_BUILDING_QUERY; }else { param = new String[6];
	 * if (districtId != null && districtId.length == 2) { param[0] =
	 * districtId[0]; } if (tehsilId != null && tehsilId.length == 2) { param[1] =
	 * tehsilId[0]; } if (wardId != null && wardId.length == 2) { param[2] =
	 * wardId[0]; } if (mohallaId != null && mohallaId.length == 2) { param[3] =
	 * mohallaId[0]; } if (propertyId != null && propertyId.length == 2) {
	 * param[4] = propertyId[0];
	 *  } if(usageType != null && usageType.length ==3) { param[5] =
	 * usageType[0]; } SQL = CommonSQL.SUB_CLAUSE_DETAILS_QUERY; }
	 *  }
	 * 
	 * 
	 * 
	 * 
	 * ArrayList retList = new IGRSCommon().getSubClause(param, SQL); if
	 * (retList != null) { for (int i = 0; i < retList.size(); i++) { ArrayList
	 * lst = (ArrayList) retList.get(i); PropertyValuationDTO dto = new
	 * PropertyValuationDTO(); dto.setSubClauseId((String) lst.get(0) + "~" +
	 * (String) lst.get(1)); dto.setSubClause((String) lst.get(1));
	 * dto.setAgriUnitFlag((String) lst.get(2)); // dto.setHdnSubClause((String)
	 * lst.get(0) // +"~"+(String) lst.get(1)); logger.debug(lst.get(0) + ":" +
	 * lst.get(1)); listDTO.add(dto); } } } catch (Exception x) {
	 * logger.debug(x); } return listDTO; }
	 */

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertySubclause(RegCompletDTO _refdto)
			throws Exception {
		ArrayList subclauseList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {

			String nonAgriQRY = "SELECT SUB_CLAUSE_ID,SUB_CLAUSE_NAME FROM IGRS_SUB_CLAUSE_MASTER "
					+ " WHERE SUB_CLAUSE_ID IN (SELECT SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_AREA_MAPPING "
					+ " WHERE DISTRICT_ID=? AND TEHSIL_ID = ? AND WARD_PATWARI_ID  = ? and MOHALLA_VILLAGE_ID = ? "
					+ " and PROPERTY_TYPE_ID = ? AND PROPERTY_TYPE_L1_ID=?) order BY SUB_CLAUSE_NAME";

			String agriQRY = "SELECT DISTINCT S.SUB_CLAUSE_ID,M.SUB_CLAUSE_NAME,M.UNIT_FLAG"
					+ " FROM IGRS_SUB_CLAUSE_AREA_MAPPING S,IGRS_SUB_CLAUSE_MASTER M"
					+ " WHERE S.SUB_CLAUSE_ID IN (SELECT SUB_CLAUSE_ID FROM "
					+ " IGRS_SUB_CLAUSE_AREA_MAPPING WHERE SUB_CLAUSE_ID "
					+ " IN (SELECT SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_FLOOR_MAPPING "
					+ " WHERE FLOOR_ID=?)) AND DISTRICT_ID=? AND TEHSIL_ID=? "
					+ " AND WARD_PATWARI_ID=? AND MOHALLA_VILLAGE_ID=? AND "
					+ " PROPERTY_TYPE_ID=? AND PROPERTY_TYPE_L1_ID=? AND "
					+ " PROPERTY_TYPE_L2_ID=? AND S.SUB_CLAUSE_ID=M.SUB_CLAUSE_ID";
          
			String array[] = null;
			String[] floorId = null;
			String[] distId = null;
			String[] tehsilId = null;
			String[] wardId = null;
			String[] mohallaId = null;
			String[] propId = null;
			String[] propUsedId = null;
			ArrayList tmpList = new ArrayList();

			String floorrefid = _refdto.getFloorNo();
			logger.debug("common bd  in bd floorrefid---<>" + floorrefid);
			logger.debug("common bd  in bd _refdto.getMohallaId()---<>"
					+ _refdto.getMohallaId());
			logger.debug("common bd  in bd CommercialType---<>"
					+ _refdto.getCommercialType());
			if (floorrefid != null && !floorrefid.equals("Select")) {
				array = new String[8];
				floorId = _refdto.getFloorNo().split("~");
				distId = _refdto.getDistId().split("~");
				tehsilId = _refdto.getTehsilId().split("~");
				wardId = _refdto.getWardId().split("~");
				mohallaId = _refdto.getMohallaId().split("~");
				propId = _refdto.getPropertyTypeId().split("~");
				propUsedId = _refdto.getPropertyUsageTypeId().split("~");

				array[0] = floorId[0];
				array[1] = distId[0];
				array[2] = tehsilId[0];
				array[3] = wardId[0];
				array[4] = mohallaId[0];
				array[5] = propId[0];
				array[6] = propUsedId[0];
				if (_refdto.getCeilingTypeId() != null
						&& !_refdto.getCeilingTypeId().equals("Select")) {
					logger.debug("in ceiling type ");
					String[] ceilingId = _refdto.getCeilingTypeId().split("~");
					array[7] = ceilingId[0];
				} else {
					logger.debug("in commertial type ");
					String[] commertialId = _refdto.getCommercialType().split(
							"~");
					array[7] = commertialId[0];

				}
				 logger.debug(agriQRY);
				tmpList = common.getPropertySubclause(array, agriQRY);
			}
			if (_refdto.getFloorNo() == null
					|| _refdto.getFloorNo().equals("Select")
					&& !_refdto.getPropertyTypeId().equals("PLT_002")) {
				array = new String[6];
				distId = _refdto.getDistId().split("~");
				tehsilId = _refdto.getTehsilId().split("~");
				wardId = _refdto.getWardId().split("~");
				mohallaId = _refdto.getMohallaId().split("~");
				propId = _refdto.getPropertyTypeId().split("~");
				propUsedId = _refdto.getPropertyUsageTypeId().split("~");
				array[0] = distId[0];
				array[1] = tehsilId[0];
				array[2] = wardId[0];
				array[3] = mohallaId[0];
				array[4] = propId[0];
				array[5] = propUsedId[0];
				if (!array[5].equals("Select")) {
					logger.debug(nonAgriQRY);
					tmpList = common.getSubClause(array, nonAgriQRY);
				}
			}
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					logger.debug("sub caluses-<>"+(String) tmpsubList.get(0) + "~"
							+ tmpsubList.get(1));
					dto.setSubclauseId((String) tmpsubList.get(0) + "~"
							+ tmpsubList.get(1));
					dto.setSubclauseName((String) tmpsubList.get(1));
					subclauseList.add(dto);
				}
			}
			
		} catch (Exception e) {
			logger.error(e);
		}
		return subclauseList;
	}

	/**
	 * @param _areaTypeId
	 * @return
	 * @throws Exception
	 */
	// String[] _areaTypeId
	public ArrayList getWardOrPatwari(RegCompletDTO _refdto,String language) throws Exception {
		ArrayList wardList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			String[] _ward = new String[3];

			String[] tehsilId = _refdto.getTehsilId().split("~");
			String[] areaId = _refdto.getAreaTypeId().split("~");

			_ward[0] = tehsilId[0];
			_ward[1] = areaId[0];
			_ward[2] = _refdto.getWardpatwarId();

			ArrayList tmpList = common.getWardOrPatwari(_ward,language);// _areaTypeId
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setWardId((String) tmpsubList.get(0) + "~"
							+ tmpsubList.get(1));// for ward number
					dto.setWardName((String) tmpsubList.get(1));// for ward name
					wardList.add(dto);
				}
			}

		} catch (Exception e) {
			logger.error(e);

		}
		return wardList;
	}

	/**
	 * @param _wardRpatwariId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getMohallaOrVillage(RegCompletDTO _refdto,String language)
			throws Exception {
		ArrayList mohallaList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			logger.debug("mohalla id--<><>" + _refdto.getMohallaId());
			logger.debug("ward id--<><>" + _refdto.getWardId());
			String[] _mohalla = new String[2];
			String[] mohallasplit = _refdto.getMohallaId().split("~");
			_mohalla[0] =  _refdto.getMohallaId();//mohallasplit[0];
			if (_refdto.getWardId() != null) {
				String[] wardsplit = _refdto.getWardId().split("~");
				_mohalla[1] =  wardsplit[0];
			}
			// if(_refdto.getPatwariId()!=null){

			// }
			ArrayList tmpList = common.getMohallaOrVillage(_mohalla,language);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setMohallaId((String) tmpsubList.get(0) + "~"
							+ tmpsubList.get(1));// for ward number
					dto.setMohallaName((String) tmpsubList.get(1));// for ward
																	// name
					mohallaList.add(dto);
				}
			}
			
		} catch (Exception e) {
			logger.error(e);
		}
		return mohallaList;
	}

	/**
	 * @param _wardRpatwariId
	 * @return
	 * @throws Exception
	 */

	public ArrayList getProprtyUsageType(String _wardRpatwariId)
			throws Exception {
		ArrayList villageList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getBuildingType(_wardRpatwariId);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setVillageId((String) tmpsubList.get(0) + "~"
							+ tmpsubList.get(1));// for ward number
					dto.setVillageName((String) tmpsubList.get(1));// for ward
																	// name
					villageList.add(dto);
				}
			}
			
		} catch (Exception e) {
			logger.error(e);
		}
		return villageList;
	}

	/**
	 * @param _wardRpatwariId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getMunicipalGovern() throws Exception {
		ArrayList municipaList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getMunicipalGovern();
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setGovmunciplId((String) tmpsubList.get(0) + "~"
							+ tmpsubList.get(1));// for ward number
					dto.setGovmunciplName((String) tmpsubList.get(1));// for
																		// ward
																		// name
					municipaList.add(dto);
				}
			}
			
		} catch (Exception e) {
			logger.error(e);
		}
		return municipaList;
	}

	/**
	 * @param l1ID
	 * @return
	 */
	public ArrayList getFloorType(String _propUsedID) {
		ArrayList list = new ArrayList();

		try {
			String[] propUsedId = _propUsedID.split("~");
			ArrayList retList = new IGRSCommon().getFloor(propUsedId[0]);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					RegCompletDTO dto = new RegCompletDTO();
					dto.setFloorNo((String) lst.get(0));
					dto.setFloorName((String) lst.get(1));
					dto.setFloorDesc((String) lst.get(2));
					dto.setTypeOfloor((String) lst.get(0) + "~"
							+ (String) lst.get(1) + "~" + (String) lst.get(2));
					list.add(dto);
				}
			}
		} catch (Exception x) {
			logger.error(x);
			
		}
		return list;
	}

	/**
	 * @param propertyTypeL1Id
	 * @return ArrayList
	 */
	public ArrayList getL2Type(String _propertyTypeL1Id) {
		ArrayList list = new ArrayList();

		try {

			String[] propertyTypeL1Id = _propertyTypeL1Id.split("~");
			ArrayList retList = new IGRSCommon()
					.getBuildingType(propertyTypeL1Id[0]);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					RegCompletDTO dto = new RegCompletDTO();
					dto.setCeilingTypeId((String) lst.get(0));
					dto.setCeilingType((String) lst.get(2));
					dto.setCeilinghidType((String) lst.get(0) + "~"
							+ (String) lst.get(1) + "~" + (String) lst.get(2));
					list.add(dto);
					logger.debug("setCeilinghidType--<>"+dto.getCeilinghidType());
				}
			}
		} catch (Exception x) {
			logger.error(x);
		}
		return list;
	}

	/**
	 * @param propertyTypeL1Id
	 * @return ArrayList
	 */
	public ArrayList getLocsOfSplitPart() {
		ArrayList list = new ArrayList();
		try {

			RegCompletDTO dto0 = new RegCompletDTO();
			;
			dto0.setSplitPartName(RegCompConstant.SPLITPART_0);
			RegCompletDTO dto1 = new RegCompletDTO();
			;
			dto1.setSplitPartName(RegCompConstant.SPLITPART_1);
			RegCompletDTO dto2 = new RegCompletDTO();
			;
			dto2.setSplitPartName(RegCompConstant.SPLITPART_2);
			RegCompletDTO dto3 = new RegCompletDTO();
			;
			dto3.setSplitPartName(RegCompConstant.SPLITPART_3);
			RegCompletDTO dto4 = new RegCompletDTO();
			;
			dto4.setSplitPartName(RegCompConstant.SPLITPART_4);
			RegCompletDTO dto5 = new RegCompletDTO();
			;
			dto5.setSplitPartName(RegCompConstant.SPLITPART_5);
			RegCompletDTO dto6 = new RegCompletDTO();
			;
			dto6.setSplitPartName(RegCompConstant.SPLITPART_6);
			RegCompletDTO dto7 = new RegCompletDTO();
			;
			dto7.setSplitPartName(RegCompConstant.SPLITPART_7);

			list.add(dto0);
			list.add(dto1);
			list.add(dto2);
			list.add(dto3);
			list.add(dto4);
			list.add(dto5);
			list.add(dto6);
			list.add(dto7);

		} catch (Exception x) {
			logger.error(x);
		}
		return list;
	}

	/**
	 * @param _areaTypeId
	 * @return
	 * @throws Exception
	 * 
	 * public ArrayList getPatwarihalka(String _areaTypeId) throws Exception{
	 * ArrayList patwariList = new ArrayList(); IGRSCommon common=new
	 * IGRSCommon(); try { ArrayList
	 * tmpList=common.getPatwarihalka(_areaTypeId); for(int i=0;i<tmpList.size();i++){
	 * ArrayList tmpsubList=(ArrayList)tmpList.get(i); if(tmpsubList!=null){
	 * RegCompletDTO dto=new RegCompletDTO();
	 * dto.setPatwariId((String)tmpsubList.get(0));//for ward number
	 * dto.setPatwariName((String)tmpsubList.get(1));//for ward name
	 * patwariList.add(dto); } }
	 * LoggerMsg.info("getPartyDetails-->"+patwariList); }catch (Exception e) {
	 * LoggerMsg.error(e); } return patwariList; }
	 */

}
