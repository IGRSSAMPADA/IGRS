package com.wipro.igrs.spotInsp.bd;
///**
// * ===========================================================================
// * File           :   SpotInspBD.java
// * Description    :   Represents the Business Delegate Class
//
// * Author         :   pavani Param
// * Created Date   :   Apr 08, 2008.
//
// * ===========================================================================
// */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.spotInsp.bo.SpotInspBO;
import com.wipro.igrs.spotInsp.dao.SpotInspDAO;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;
import com.wipro.igrs.spotInsp.dto.SpotInspNewDTO;
import com.wipro.igrs.spotInsp.form.SpotInspForm;
///**
// *
// * @author pavpapa
// *
// */
public class SpotInspBD
{
	private final Logger logger = Logger.getLogger(SpotInspBD.class);
	boolean blnInsval;
	boolean blnInsRem;
	ArrayList list = null;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCountry() throws Exception
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getCountry();
		list  =  new ArrayList();

		if (ret !=  null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto = new SpotInspDTO();
				dto.setValue((String)lst.get(0));
				dto.setName((String)lst.get(1));
				list.add(dto);

			}
			logger.info(" getCountry List SpotInspBD "+ list);
		}
		return list;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCurrentDistrict(String OfficeId) throws Exception
	{    String ret ="";

	SpotInspBO objBo = new SpotInspBO();
	ret  =  objBo.getCurrentDistrict(OfficeId);
	logger.info(" getCountry List SpotInspBD "+ ret);

	return ret;
	}


	/**
	 * 
	 * @param language
	 * @return
	 * @throws Exception
	 */
	public ArrayList getActivityList(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getActivityList(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setActivityId((String)lst.get(0));
				dto.setActivityName((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getdeedInstrumentList(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getdeedInstrumentList(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getRangeList() throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getRangeList();
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getWardList(String tehsilId,String areaType,String wardType,String language) throws Exception {
		SpotInspDAO d = new SpotInspDAO();
		ArrayList ret = d.getWard(tehsilId, areaType, wardType,language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(1));
				dto.setSelectedId((String)lst.get(0));
				list.add(dto);
			}
			logger.info("SpotInspBD----getWardList  "+list);
		}
		return list;
	}


	public ArrayList getMohallaList(String wardId, String language) throws Exception {
		SpotInspDAO d = new SpotInspDAO();
		ArrayList ret = d.getMahalla(wardId,language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(1));
				dto.setSelectedId((String)lst.get(0));
				list.add(dto);
			}
			logger.info("SpotInspBD----getMohallaId  "+list);
		}
		return list;
	}

	public ArrayList getCurrentRangeList() throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getCurrentRangeList();
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}



	public ArrayList getcurrentdeedInstrumentList(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getcurrentdeedInstrumentList(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}


	public boolean insertCriteriaActivity(String grpid, ArrayList menuArray)
	throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		boolean flag1 = false;
		try {

			String id = new String();
			String flag = new String();
			flag1=objBo.updateCriteriaActivity(grpid);
			logger.debug("updated previous CRITERIA ACTIVITY----"+ flag1);
			flag1 = false;
			if (menuArray != null && menuArray.size() > 0) {
				for (int g = 0; g < menuArray.size(); g++) {
					SpotInspNewDTO gdto = (SpotInspNewDTO) menuArray.get(g);

			id = (String) gdto.getSelectedId();
			flag = (String) gdto.getSelectFlag();
			if (flag.equalsIgnoreCase("Y")) {
				flag1 = objBo.insertCriteriaActivity(id, grpid);
			} else if (flag.equalsIgnoreCase("N")) {
				//System.out.println("not inserted");
				logger.debug("not inserted");
				continue;

					}

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag1;
	}


	public ArrayList getpropertyList(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList plotRet  =  objBo.getpropertyList(language,"1");
		ArrayList buildRet  =  objBo.getpropertyList(language,"2");
		ArrayList agriRet  =  objBo.getpropertyList(language,"3");
		list  =  new ArrayList();

		if (plotRet != null) {
			for (int i  =  0; i < plotRet.size(); i++) {
				ArrayList lst  =  (ArrayList)plotRet.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		if (buildRet != null) {
			for (int i  =  0; i < buildRet.size(); i++) {
				ArrayList lst  =  (ArrayList)buildRet.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			SpotInspNewDTO dto8  =  new SpotInspNewDTO();
			if(language.equalsIgnoreCase("en"))
				dto8.setSelectedName("Building-Independent Floor");
			else
				dto8.setSelectedName("बिल्डिंग-स्वतंत्र मंजिल");
			dto8.setSelectedId("2~2~1");
			logger.info("SpotInspBD----getState  "+list);
			list.add(dto8);
		}
		if (agriRet != null) {
			for (int i  =  0; i < agriRet.size(); i++) {
				ArrayList lst  =  (ArrayList)agriRet.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			for(int i=6;i<=7;i++)
			{
				if (plotRet != null) {
					for (int j  =  0; j < plotRet.size(); j++) {
						ArrayList lst  =  (ArrayList)plotRet.get(j);
						SpotInspNewDTO dto  =  new SpotInspNewDTO();
						if(i==6)
						{
							if(!"7".equalsIgnoreCase((String)lst.get(1)))
							{
								if(language.equalsIgnoreCase("en"))
									dto.setSelectedName("AGRICULTURAL LAND-Diverted-"+((String)lst.get(0)).split("-")[1]);
								else
									dto.setSelectedName("कृषि  भूमि-बँट-"+((String)lst.get(0)).split("-")[1]);
							}
						}
						else
						{
							if(!"7".equalsIgnoreCase((String)lst.get(1)))
							{
								if(language.equalsIgnoreCase("en"))
									dto.setSelectedName("AGRICULTURAL LAND-Undiverted and diverted both"+((String)lst.get(0)).split("-")[1]);
								else
									dto.setSelectedName("कृषि भूमि -एक - रूप और दोनों बँट"+((String)lst.get(0)).split("-")[1]);
							}
						}
						dto.setSelectedId("3~"+i+"~"+(String)lst.get(1));
						list.add(dto);
					}
				}

				if(i==7)
				{
					SpotInspNewDTO Ag1 = new SpotInspNewDTO();
					SpotInspNewDTO Ag2 = new SpotInspNewDTO();
					SpotInspNewDTO Ag3 = new SpotInspNewDTO();

					if(language.equalsIgnoreCase("en"))
						Ag1.setSelectedName("AGRICULTURAL LAND-Undiverted and diverted both-Irrigated");
					else
						Ag1.setSelectedName("कृषि भूमि -एक - रूप और दोनों बँट-सिंचित");
					if(language.equalsIgnoreCase("en"))
						Ag2.setSelectedName("AGRICULTURAL LAND-Undiverted and diverted both-Unirrigated 1 Crop");
					else
						Ag1.setSelectedName("कृषि भूमि -एक - रूप और दोनों बँट-असिंचित 1 फसल");
					if(language.equalsIgnoreCase("en"))
						Ag3.setSelectedName("AGRICULTURAL LAND-Undiverted and diverted both-Unirrigated 2 crop");
					else
						Ag1.setSelectedName("कृषि भूमि -एक - रूप और दोनों बँट-असिंचित 2 फसल");

					Ag1.setSelectedId("3~7~20");
					Ag2.setSelectedId("3~7~23");
					Ag3.setSelectedId("3~7~24");

					list.add(Ag1);
					list.add(Ag2);
					list.add(Ag3);

				}
			}

			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getAreaList(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getAreaList(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getCurrentpropertyList(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getCurrentpropertyList(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				String id=(String)lst.get(0);
				dto.setSelectedId(id);
				String name=objBo.getSelectPropertyName(id,language);
				dto.setSelectedName(name);
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getCurrentAreaList(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getCurrentAreaList(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getCurrentSubClauseList(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getCurrentSubClauseList(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}


	public ArrayList getCurrentSubClauseList1(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getCurrentSubClauseList1(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}
	public ArrayList getPropertyL2(String propertyId, String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getPropertyL2(propertyId,language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(1));
				dto.setSelectedId((String)lst.get(0));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getSubClauseList(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getSubClauseList(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspNewDTO dto  =  new SpotInspNewDTO();
				dto.setSelectedName((String)lst.get(0));
				dto.setSelectedId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getDistSrList(String districtId) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getDistSrList(districtId);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setUserName((String)lst.get(0));
				dto.setUserId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getDistDrList(String districtId) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getDistDrList(districtId);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setUserName((String)lst.get(0));
				dto.setUserId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getSrList(String userId) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getSrList(userId);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setUserName((String)lst.get(0));
				dto.setUserId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public ArrayList getSroList() throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret  =  objBo.getSroList();
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setValue((String)lst.get(0));
				dto.setName((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;
	}

	public String getGuidelineRate(String[] param,String d) throws Exception {
		SpotInspDAO commonDao = new SpotInspDAO();
		return commonDao.getGuidelineRate(param,d);
	}


	/**
	 * 
	 * @param stateId
	 * @param language
	 * @return
	 * @throws Exception
	 */ //Added by Mohit Soni
	public ArrayList getZone(String stateId, String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getZone(stateId,language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setZoneId((String)lst.get(0));
				dto.setZoneName((String)lst.get(1));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}
	//Added by Mohit Soni
	public ArrayList getTehsil(String districtId, String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getTehsil(districtId,language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setTehsilName((String)lst.get(1));
				dto.setTehsilId((String)lst.get(0));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getTehsil()............... "+list);
		return list;
	}


	//Added by Mohit Soni
	public ArrayList getAreTypea(String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getAreaType(language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setAreaTypeName((String)lst.get(1));
				dto.setAreaTypeId((String)lst.get(0));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getTehsil()............... "+list);
		return list;
	}


	/**
	 * 
	 * @param stateId
	 * @return
	 * @throws Exception
	 */

	//Added by Mohit Soni
	public ArrayList getDistrict(String stateId) throws Exception {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getDistrict(stateId);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictId((String)lst.get(0));
				dto.setDistrictName((String)lst.get(1));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}
	/**
	 * 
	 * @param language
	 * @param stateId
	 * @return
	 * @throws Exception
	 */



	public ArrayList getDistrictZone(String zoneId, String language) throws Exception {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getDistrictZone(zoneId,language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictId((String)lst.get(1));
				dto.setDistrictName((String)lst.get(0));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}


	public ArrayList getAssignCriteriaDocument(SpotInspForm spForm ) throws Exception {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getAssignCriteriaDocument(spForm);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictName((String)lst.get(0));
				dto.setDistrictId((String)lst.get(1));
				dto.setZoneName((String)lst.get(2));
				dto.setZoneId((String)lst.get(3));
				dto.setRegId((String)lst.get(4));
				dto.setTehsilId((String)lst.get(5));
				dto.setTehsilName((String)lst.get(6));
				dto.setMarketValue((String)lst.get(7));
				dto.setRegFee((String)lst.get(8));
				dto.setStampDuty((String)lst.get(9));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}


	public ArrayList getAssignCriteriaDocumentTest(SpotInspForm spForm ) throws Exception {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getAssignCriteriaDocumentTest(spForm);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictName((String)lst.get(0));
				dto.setDistrictId((String)lst.get(1));
				dto.setZoneName((String)lst.get(2));
				dto.setZoneId((String)lst.get(3));
				dto.setRegId((String)lst.get(4));
				dto.setTehsilId((String)lst.get(5));
				dto.setTehsilName((String)lst.get(6));
				dto.setMarketValue((String)lst.get(7));
				dto.setRegFee((String)lst.get(8));
				dto.setStampDuty((String)lst.get(9));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}

	/**
	 * 
	 * @param stateId
	 * @return
	 * @throws Exception

	 *
	 *
	 */

	public String getPropIdSearch(String l1)
	{
		SpotInspBO objBo = new SpotInspBO();
		return objBo.getPropIdSearch(l1);
	}
	public ArrayList getCriteriaDocumentPlot(SpotInspForm spForm) throws Exception {
		ArrayList newList=new ArrayList();
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getCriteriaDocumentPlot(spForm);


		ArrayList  list  =  new ArrayList();

		if(ret==null)
		{
			{
				return null;
			}

		}


		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictName((String)lst.get(0));
				dto.setDistrictId((String)lst.get(1));
				dto.setZoneName((String)lst.get(2));
				dto.setZoneId((String)lst.get(3));
				dto.setCount((String)lst.get(4));
				list.add(dto);
			}
		}
		spForm.setMainCountList(list);
		spForm.setPercentage(getAssignPercantage());
		System.out.println(spForm.getPercentage());
		SpotInspDTO  a= 	(SpotInspDTO) (spForm.getMainCountList().get(0));
		System.out.println("MAIN "+a.getCount());



		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}


	public ArrayList getCriteriaDocumentAgri(SpotInspForm spForm) throws Exception {
		ArrayList newList=new ArrayList();
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getCriteriaDocumentAgri(spForm);


		ArrayList  list  =  new ArrayList();

		if(ret==null)
		{
			{
				return null;
			}

		}


		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictName((String)lst.get(0));
				dto.setDistrictId((String)lst.get(1));
				dto.setZoneName((String)lst.get(2));
				dto.setZoneId((String)lst.get(3));
				dto.setCount((String)lst.get(4));
				list.add(dto);
			}
		}
		spForm.setMainCountList(list);
		spForm.setPercentage(getAssignPercantage());
		System.out.println(spForm.getPercentage());
		SpotInspDTO  a= 	(SpotInspDTO) (spForm.getMainCountList().get(0));
		System.out.println("MAIN "+a.getCount());



		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}
	public ArrayList getCriteriaDocumentBuild(SpotInspForm spForm) throws Exception {
		ArrayList newList=new ArrayList();
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getCriteriaDocumentBuild(spForm);


		ArrayList  list  =  new ArrayList();

		if(ret==null)
		{
			{
				return null;
			}

		}


		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictName((String)lst.get(0));
				dto.setDistrictId((String)lst.get(1));
				dto.setZoneName((String)lst.get(2));
				dto.setZoneId((String)lst.get(3));
				dto.setCount((String)lst.get(4));

				list.add(dto);
			}
		}
		spForm.setMainCountList(list);
		spForm.setPercentage(getAssignPercantage());
		System.out.println(spForm.getPercentage());
		SpotInspDTO  a= 	(SpotInspDTO) (spForm.getMainCountList().get(0));
		System.out.println("MAIN "+a.getCount());



		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}

	public ArrayList getCriteriaDocument(SpotInspForm spForm) throws Exception {
		ArrayList newList=new ArrayList();
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getCriteriaDocument(spForm);


		ArrayList  list  =  new ArrayList();

		if(ret==null)
		{
			return null;

		}


		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictName((String)lst.get(0));
				dto.setDistrictId((String)lst.get(1));
				dto.setZoneName((String)lst.get(2));
				dto.setZoneId((String)lst.get(3));
				dto.setCount((String)lst.get(4));
				list.add(dto);
			}
		}
		spForm.setMainCountList(list);
		spForm.setPercentage(getAssignPercantage());
		System.out.println(spForm.getPercentage());
		SpotInspDTO  a= 	(SpotInspDTO) (spForm.getMainCountList().get(0));
		System.out.println("MAIN "+a.getCount());



		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}

	public ArrayList getReInspectionDocument(SpotInspForm spForm) throws Exception {
		ArrayList newList=new ArrayList();
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getReInspectionDocument(spForm);


		ArrayList  list  =  new ArrayList();

		if(ret==null)
		{
			return null;

		}


		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setZoneName((String)lst.get(0));

				dto.setDistrictId((String)lst.get(1));
				dto.setDrName((String)lst.get(2));
				dto.setSrName((String)lst.get(3));
				dto.setAssignPersonName((String)lst.get(4));
				dto.setRegIdcompletion((String)lst.get(5));
				dto.setDistrictName((String)lst.get(6));
				dto.setMarketValue((String)lst.get(7));
				dto.setRegFee((String)lst.get(8));
				dto.setStampDuty((String)lst.get(9));
				dto.setDateinsp((String)lst.get(10));
				dto.setDatetodo((String)lst.get(11));
				dto.setDigName((String)lst.get(12));
				dto.setZoneId((String)lst.get(13));
				list.add(dto);
			}
		}




		logger.info("in SpotInspBD  getREInspectionDocument()............... "+list);
		return list;
	}



	public ArrayList applyPercentage(ArrayList list,int percentage)
	{
		if (list != null) {
			ArrayList temp = new ArrayList();
			for(int i=0;i<list.size();i++)
			{
				SpotInspDTO dto  =  (SpotInspDTO)list.get(i);
				double c=Double.parseDouble(dto.getCount());
				c=(percentage*c)/100;
				dto.setCount(Integer.toString((int) Math.round(c)));
				temp.add(dto);
			}
			return temp;
		}
		else
			return null;
	}

	public ArrayList clonedData(ArrayList list) throws CloneNotSupportedException
	{
		ArrayList temp = new ArrayList();
		for(int i=0;i<list.size();i++){


			SpotInspDTO dto=(SpotInspDTO)list.get(i);
			SpotInspDTO dto1=dto.clone();
			temp.add(dto1);

		}
		return temp;
	}
	public ArrayList getCriteriaDocumentHyperLink(SpotInspForm spForm) throws Exception {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getCriteriaDocumentHyperLink(spForm);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictName((String)lst.get(0));
				dto.setDistrictId((String)lst.get(1));
				dto.setZoneName((String)lst.get(2));
				dto.setZoneId((String)lst.get(3));
				dto.setCount((String)lst.get(4));

				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}


	public ArrayList getDocumentReInspection(SpotInspForm spForm) throws Exception {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  = objBo.getDocumentReInspection(spForm);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictName((String)lst.get(2));
				dto.setDistrictId((String)lst.get(3));
				dto.setZoneName((String)lst.get(0));
				dto.setZoneId((String)lst.get(1));
				dto.setCount((String)lst.get(4));
				list.add(dto);
			}
		}
		logger.info("in SpotInspBD  getDistrict()............... "+list);
		return list;
	}



	/**
	 * 
	 * @param DateFormat
	 * @return
	 */

	public static String getOracleDate(String DateFormat) {
		String finalDate  =  "";
		if(DateFormat!= null || !DateFormat.equalsIgnoreCase("") )
		{
			StringTokenizer st = new StringTokenizer(DateFormat,"/");
			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();
			String inputDate  =  day + "-" + month + "-" + year;
			SimpleDateFormat formatter  =  new SimpleDateFormat("dd-MM-yyyy");

			Date newDate ;
			try {
				newDate  =  formatter.parse(inputDate);
				SimpleDateFormat format  =  new SimpleDateFormat("dd/MMM/yyyy");
				finalDate  =  format.format(newDate);

			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return  finalDate;
	}

	/**
	 *
	 * @param form
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotViewResFinal(SpotInspForm form) throws Exception
	{

		ArrayList list = new ArrayList();
		ArrayList returnList = null;
		SpotInspBO objBo = new SpotInspBO();
		try{
			list = objBo.getSpotViewResFinal(form);

			if(list!= null)
			{
				returnList = new ArrayList();
				for(int i = 0;i<list.size();i++)
				{
					SpotInspDTO dto = new SpotInspDTO();
					ArrayList rowList = (ArrayList) list.get(i);
					dto.setZoneName((String)rowList.get(1));
					dto.setDigName((String)rowList.get(2));
					dto.setDrName((String)rowList.get(3));
					dto.setAssignPersonName((String)rowList.get(4));
					String a = (String)rowList.get(6);
					String b = (String)rowList.get(7);
					if(a == null)
					{
						a="0";
					}

					if(b==null)
					{
						b="0";
					}

					int tot = Integer.parseInt(a);
					int ins = Integer.parseInt(b);
					String pen = String.valueOf((tot-ins));
					dto.setTotalDocument(a);
					dto.setNoOfInspected(b);
					dto.setNoOfPending(pen);
					returnList.add(dto);
				}
			}



		}
		catch (Exception e) {
			e.printStackTrace();
		}


		return returnList;
	}

	public SpotInspForm getSpotViewRes(SpotInspForm form) throws Exception
	{

		ArrayList visitVRList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList retList1 = null;
		SpotInspBO objBo = new SpotInspBO();
		try{
			list = objBo.getSpotViewRes(form);
			if(list!= null)
			{
				for(int i = 0;i<list.size();i++)
				{
					form =new SpotInspForm();
					retList1 = (ArrayList)list.get(i);
					form.setApplicationNumber(retList1.get(0).toString());
					form.setApplicationDate(retList1.get(1).toString());
					//form.setSelectionDate(retList1.get(2).toString());

					if(retList1.get(3).toString().equalsIgnoreCase("C"))
						form.setStatus("Completed");
					if(retList1.get(3).toString().equalsIgnoreCase("Y"))
						form.setStatus("Pending");
					if(retList1.get(3).toString().equalsIgnoreCase("R"))
						form.setStatus("Re-Inspection Completed");
					visitVRList.add(form);
					logger.info("Wipro in Spot Inspection BD  getSpotViewRes  retList1= " + visitVRList);
				}
				form.setSpotViewList(visitVRList);
			}
		}
		catch(Exception e)
		{
			logger.info("this is Exception in getSpotViewRes in BD" + e);
		}
		return form;
	}

	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public SpotInspForm getSpotSechRes(SpotInspForm form) throws Exception  //Very important
	{

		ArrayList spotSechList = new ArrayList();
		ArrayList list = new ArrayList();
		SpotInspBO objBo = new SpotInspBO();
		ArrayList lst =null;
		logger.info("Wipro in SpotInspection BD  - getSpotSechRes() before EXCUTIN QUERY");
		try{
			list = objBo.getSpotSechRes(form);
			logger.info("Wipro in SpotInspection BD  getSpotSechRes Resit ---------------------->list.size() = " + list.size());
			if(list!= null)
			{
				for(int i = 0;i<list.size();i++)
				{
					form =new SpotInspForm();


					lst  =  (ArrayList)list.get(i);
					SpotInspDTO dto  =  new SpotInspDTO();
					dto.setDistrictId((String)lst.get(3));
					dto.setDistrictName((String)lst.get(2));
					dto.setZoneName((String)lst.get(0));
					dto.setZoneId((String)lst.get(1));
					dto.setCount((String)lst.get(5));
					dto.setSpotId((String)lst.get(3)+"~"+(String)lst.get(4)+"~"+lst.get(6).toString());


					if(lst.get(4).toString().equalsIgnoreCase("Y"))
						dto.setStatus("Inspection Pending");
					if(lst.get(4).toString().equalsIgnoreCase("C"))
						dto.setStatus("Inspection Completed");

					if(lst.get(lst.size()-2)!=null){
						if(lst.get(lst.size()-2).toString().equalsIgnoreCase("Y"))
							dto.setFoundStatus("Found Correct");
						if(lst.get(lst.size()-2).toString().equalsIgnoreCase("N"))
							dto.setFoundStatus("Found Incorrect");

					}

					if(lst.get(4).toString().equalsIgnoreCase("R"))
					{

						if(lst.get(lst.size()-1)!=null){
							if(lst.get(lst.size()-1).toString().equalsIgnoreCase("C"))
								dto.setStatus("Re Inspection Completed");
							if(lst.get(lst.size()-1).toString().equalsIgnoreCase("A"))
								dto.setStatus("Re Inspection Pending");
							dto.setFoundStatus("Found Correct During Inspection");

						}


					}




					spotSechList.add(dto);

				}
				form.setSpotViewList(spotSechList);
			}


		} catch (Exception e) {
			logger.info("Wipro in SpotInspection BD  - Exception in getSpotSechRes() in BD" + e);
		}
		return form;
	}

	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public SpotInspForm getSpotSechRequest(SpotInspForm form) throws Exception
	{

		ArrayList spotSechList = new ArrayList();
		ArrayList list = new ArrayList();
		SpotInspBO objBo = new SpotInspBO();
		ArrayList retList1 = null;
		logger.info("Wipro in SpotInspection BD  - getSpotSechRes() before EXCUTIN QUERY");
		try{
			list = objBo.getSpotSechRequest(form);
			logger.info("Wipro in SpotInspection BD  getSpotSechRes Resit ---------------------->list.size() = " + list.size());
			if(list!= null)
			{
				for(int i = 0;i<list.size();i++)
				{
					form =new SpotInspForm();
					retList1 = (ArrayList)list.get(i);
					logger.info("Wipro in Spot Inspection BD  getSpotSechRes  retList1= " + retList1);
					form.setSchApplNo(retList1.get(0).toString());
					String tt=(String)retList1.get(1);
					if(tt==null)
					{
						form.setApplicationDate("");
						tt="";
					}
					form.setApplicationDate(tt);
					tt=(String)retList1.get(2);
					if(tt==null)
					{
						form.setSelectionDate("");
						tt="";
					}
					form.setSelectionDate(tt);
					if(retList1.get(3).toString().equalsIgnoreCase("S"))
						form.setStatus("Scheduled");
					if(retList1.get(3).toString().equalsIgnoreCase("R"))
						form.setStatus("Recommended");
					if(retList1.get(3).toString().equalsIgnoreCase("C"))
						form.setStatus("Completed");
					if(retList1.get(3).toString().equalsIgnoreCase("P"))
						form.setStatus("Pending for Spot Inspection");
					if(retList1.get(3).toString().equalsIgnoreCase("D"))
						form.setStatus("Spot Inspection Rejected by DR");
					logger.info("Wipro in SpotInspection  BD  getSpotSechRes  form= "+ form);
					spotSechList.add(form);

				}
				form.setSpotViewList(spotSechList);
			}


		} catch (Exception e) {
			logger.info("Wipro in SpotInspection BD  - Exception in getSpotSechRes() in BD" + e);
		}
		return form;
	}

	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public SpotInspForm getSpotAssignmentRequest(SpotInspForm form) throws Exception
	{

		ArrayList spotSechList = new ArrayList();
		ArrayList list = new ArrayList();
		SpotInspBO objBo = new SpotInspBO();
		ArrayList retList1 = null;
		logger.info("Wipro in SpotInspection BD  - getSpotAssignmentRequest() before EXCUTIN QUERY");
		try{
			list = objBo.getSpotSechRequest(form);
			logger.info("Wipro in SpotInspection BD  getSpotAssignmentRequest Resit ---------------------->list.size() = " + list.size());
			if(list!= null)
			{
				for(int i = 0;i<list.size();i++)
				{
					form =new SpotInspForm();
					retList1 = (ArrayList)list.get(i);
					logger.info("Wipro in Spot Inspection BD  getSpotAssignmentRequest  retList1= " + retList1);
					form.setSchApplNo(retList1.get(0).toString());
					String tt=(String)retList1.get(1);
					if(tt==null)
					{
						form.setApplicationDate("");
						tt="";
					}
					form.setApplicationDate(tt);
					tt=(String)retList1.get(2);
					if(tt==null)
					{
						form.setSelectionDate("");
						tt="";
					}
					form.setSelectionDate(tt);
					if(retList1.get(3).toString().equalsIgnoreCase("S"))
						form.setStatus("Scheduled");
					if(retList1.get(3).toString().equalsIgnoreCase("R"))
						form.setStatus("Recommended");
					if(retList1.get(3).toString().equalsIgnoreCase("C"))
						form.setStatus("Completed");
					if(retList1.get(3).toString().equalsIgnoreCase("P"))
						form.setStatus("Pending for Spot Inspection");
					if(retList1.get(3).toString().equalsIgnoreCase("D"))
						form.setStatus("Spot Inspection Rejected by DR");
					logger.info("Wipro in SpotInspection  BD  getSpotSechRes  form= "+ form);
					spotSechList.add(form);

				}
				form.setSpotViewList(spotSechList);
			}


		} catch (Exception e) {
			logger.info("Wipro in SpotInspection BD  - Exception in getSpotSechRes() in BD" + e);
		}
		return form;
	}



	/**
	 * 
	 * @param form
	 * @param language
	 * @return
	 * @throws Exception
	 */
	public SpotInspForm getSpotCompRes(SpotInspForm form, String language) throws Exception
	{

		SpotInspForm l = form;
		ArrayList visitVRList = new ArrayList();
		ArrayList list = new ArrayList();
		SpotInspBO objBo = new SpotInspBO();
		ArrayList retList1 = null;
		logger.info("Wipro in SpotInspection BD  - getSpotSechRes() before EXCUTIN QUERY");
		try{
			list = objBo.getSpotCompRes(form,language);
			logger.info("Wipro in SpotInspection BD  getSpotSechRes Resit ---------------------->list.size() = " + list.size());

			TreeSet<String> spotIds = new TreeSet<String>();
			logger.debug("s");
			if(list!= null)
			{
				for(int i = 0;i<list.size();i++)
				{
					form =new SpotInspForm();
					retList1 = (ArrayList)list.get(i);

					logger.info("Wipro in Spot Inspection BD  getSpotSechRes  retList1= " + retList1);
					form.setCompApplNo(retList1.get(0).toString());
					form.setApplicationDate(retList1.get(1).toString());
					form.setSelectionDate(retList1.get(2).toString());
					if(retList1.get(3).toString().equalsIgnoreCase("R"))
						form.setStatus("ReInspection Pending");
					if(retList1.get(3).toString().equalsIgnoreCase("Y") )
						if(language.equalsIgnoreCase("en"))
							form.setStatus("Pending");
						else
							form.setStatus("अपूर्ण");
					if(retList1.get(3).toString().equalsIgnoreCase("C"))
						form.setStatus("Completed");
					form.setSpotref(retList1.get(0).toString()+"~"+retList1.get(3).toString());
					form.setDistrictName(retList1.get(4).toString());
					form.setZoneName(retList1.get(5).toString());
					form.setTehsilName("abc");
					form.setMarketValue(retList1.get(6).toString());
					form.setRegFee(retList1.get(7).toString());
					form.setStampDuty(retList1.get(8).toString());
					form.setSrName((String)retList1.get(9));
					form.setDrName((String)retList1.get(10));
					form.setSpotId((String)retList1.get(11));
					visitVRList.add(form);
					//					spotIds.add(form.getSpotId()+"~"+form.getApplicationDate()+"~"+form.getStatus());
					spotIds.add(form.getSpotId()+"~"+form.getApplicationDate());

				}

				HashMap<String, ArrayList<SpotInspForm>> temp = new HashMap<String, ArrayList<SpotInspForm>>();
				Iterator<String> key = spotIds.iterator();

				while(key.hasNext())
				{
					String tempKey = key.next();
					String param[] = tempKey.split("~");
					String spotId1 = param[0];
					ArrayList<SpotInspForm> n = new ArrayList<SpotInspForm>();
					for(int i=0;i<visitVRList.size();i++)
					{
						SpotInspForm a = (SpotInspForm) visitVRList.get(i);
						if(spotId1.equalsIgnoreCase(a.getSpotId()))
						{
							n.add(a);
						}

					}

					temp.put(tempKey, n);


				}

				l.setViewList(temp);


				l.setSpotViewList(visitVRList);
			}

		}
		catch(Exception e)
		{
			logger.info("this is Exception in  in BD" + e);
		}

		System.out.println("dd");
		l.setActionName("");
		return l;
	}

	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */

	public String getRegCompletionNumber(String reginitno)
	{
		SpotInspBO objBo = new SpotInspBO();
		return 	objBo.getRegCompletionNumberBO(reginitno);




	}

	public SpotInspForm getDrReqRes(SpotInspForm form) throws Exception
	{

		ArrayList visitVRList = new ArrayList();
		SpotInspBO objBo = new SpotInspBO();
		ArrayList list = new ArrayList();
		ArrayList lst = null;
		logger.info("Wipro in SpotInspection BD  - getDrReqRes() before EXCUTIN QUERY");
		try{
			list = objBo.getDrReqRes(form);
			logger.info("Wipro inSpotInspection  BD  getDrReqRes---------------------->list. = " + list);
			logger.info("Wipro in SpotInspection BD  getDrReqRes Resit ---------------------->list.size() = " + list.size());
			if(list!= null)
			{
				for(int i = 0;i<list.size();i++)
				{
					form =new SpotInspForm();
					lst = (ArrayList)list.get(i);

					form.setDistrictName((String)lst.get(5));
					form.setZoneName((String)lst.get(4));
					form.setTehsilName((String)lst.get(6));
					form.setMarketValue((String)lst.get(7));
					form.setRegFee((String)lst.get(8));
					form.setStampDuty((String)lst.get(9));
					form.setSrName((String)lst.get(10));
					form.setDrName((String)lst.get(11));

					logger.info("Wipro in Spot Inspection BD  getDrReqRes  retList1= " + lst);
					form.setDrReqApplNo(lst.get(0).toString());
					String tt=(String)lst.get(1);
					if(tt==null)
					{
						form.setApplicationDate("");
						tt="";
					}
					form.setApplicationDate(tt);
					tt=(String)lst.get(2);
					if(tt==null)
					{
						form.setSelectionDate("");
						tt="";
					}
					form.setSelectionDate(tt);
					form.setStatus(lst.get(3).toString());
					if(lst.get(3).toString().equalsIgnoreCase("S"))
						form.setStatus("Scheduled");
					if(lst.get(3).toString().equalsIgnoreCase("C"))
						form.setStatus("Inspection Completed and Found Correct");
					if(lst.get(3).toString().equalsIgnoreCase("P"))
						form.setStatus("Pending");
					if(lst.get(3).toString().equalsIgnoreCase("Y"))
						form.setStatus("Pending for Approval");
					visitVRList.add(form);

				}
				form.setSpotViewList(visitVRList);
			}

		} catch (Exception e) {
			logger.info("this is Exception in getDrReqRes in BD" + e);
		}
		return form;
	}

	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public SpotInspForm getDrViewRes(SpotInspForm form) throws Exception
	{
		ArrayList visitVRList = new ArrayList();
		SpotInspBO objBo = new SpotInspBO();
		ArrayList list = new ArrayList();
		ArrayList retList1 = null;
		logger.info("Wipro in SpotInspection BD  - getDrReqRes() before EXCUTIN QUERY");
		try{
			list = objBo.getDrViewRes(form);

			logger.info("Wipro in SpotInspection BD  getDrViewRes Resit ---------------------->list.size() = " + list.size());
			if(list!= null)
			{
				for(int i = 0;i<list.size();i++)
				{
					form =new SpotInspForm();
					retList1 = (ArrayList)list.get(i);
					form.setDrViewApplNo((String)retList1.get(0));
					form.setApplicationDate((String)retList1.get(1));
					form.setSelectionDate((String)retList1.get(2));
					if(((String)retList1.get(3)).toString().equalsIgnoreCase("C"))
						form.setStatus("Completed");
					if(((String)retList1.get(3)).toString().equalsIgnoreCase("Y"))
						form.setStatus("Pending");
					if(((String)retList1.get(3)).toString().equalsIgnoreCase("R"))
						form.setStatus("ReInspection Completed/Pending");
					form.setZoneName((String)retList1.get(4));
					form.setDistrictName((String)retList1.get(5));
					//form.setDistrictId((String)retList1.get(9));
					form.setSrName((String)retList1.get(6));
					if(((String)retList1.get(7)).toString().equalsIgnoreCase("Y"))
						form.setFoundStatus("Found  correct");
					if(((String)retList1.get(7)).toString().equalsIgnoreCase("N"))
						form.setFoundStatus("Found  Incorrect");
					if(((String)retList1.get(7)).toString().equalsIgnoreCase("NA"))
						form.setFoundStatus("NA");
					form.setCaseId((String)retList1.get(8));
					visitVRList.add(form);
				}
				form.setSpotViewList(visitVRList);
				logger.info("After ");
			}
		}
		catch(Exception e)
		{
			logger.info("this is Exception in getDrViewRes in BD" + e);
		}
		return form;
	}

	/**
	 * getting the spot Inspection View details.
	 * @param form
	 * @return
	 * @throws Exception
	 */


	public  SpotInspForm getSpotInspViewDet(SpotInspForm form) throws Exception
	{
		logger.info("  Enter in to the Wipro in Spot Inspection BD - getSpotInspViewDet() ");
		list = new ArrayList();
		ArrayList	propList = new ArrayList();
		ArrayList retRemList  = new ArrayList();
		ArrayList retList1 = new ArrayList();
		SpotInspBO objBo = new SpotInspBO();
		String applNo = form.getApplicationNumber();
		logger.info("Wipro in Spot Inspection BD - getSpotInspViewDet() visitRefId="+applNo);
		try
		{
			list = objBo.getSpotInspViewDet(applNo);
			for (int i = 0; i < list.size(); i++)
			{
				retList1  =  (ArrayList)list.get(i);
				logger.info("Wipro in Spot Inspection BD - getSpotInspViewDet() before EXCUTIN QUERY"+retList1);
				//--START--Applicant Details.


				form.setDistrict((String)retList1.get(12));
				form.setSroName((String)retList1.get(13));
				form.setSrName((String)retList1.get(6));
				form.setApplicationNumber((String)retList1.get(0));
				form.setDateOfRequest((String)retList1.get(7));
				form.setInsPlanDate((String)retList1.get(8));
				form.setInsActDate((String)retList1.get(9));
				form.setChangeVal((String)retList1.get(10));
				form.setRemarks((String)retList1.get(11));
				form.setReChangeVal((String)retList1.get(24));
				form.setReRemarks((String)retList1.get(25));
				form.setReInsPlanDate((String)retList1.get(26));
				form.setReInsActDate((String)retList1.get(27));
				form.setStatus((String)retList1.get(28));

				logger.info("form.getStatus() "+form.getStatus());



				logger.info("(String) list.get(0)  "+(String) retList1.get(0));
				SpotInspDTO dto = new SpotInspDTO();
				dto.setPropId((String) retList1.get(1));
				dto.setOldArea((String) retList1.get(15));
				dto.setOldConstructedArea((String) retList1.get(16));
				dto.setOldPropertyUse((String) retList1.get(17));
				dto.setOldTypeOfConstruction((String) retList1.get(18));
				dto.setReArea((String) retList1.get(20));
				dto.setReConstructedArea((String) retList1.get(21));
				dto.setRePropertyUse((String) retList1.get(22));
				dto.setReTypeOfConstruction((String) retList1.get(23));
				dto.setNewArea((String) retList1.get(2));
				dto.setNewConstructedArea((String) retList1.get(3));
				dto.setNewPropertyUse((String) retList1.get(4));
				dto.setNewTypeOfConstruction((String) retList1.get(5));

				logger.info("dto.getPropId()  "+dto.getPropId());
				propList.add(dto);
				logger.info("propList  "+propList.size());

			}
			form.setSpotCompViewList(propList);
			logger.info("setSpotCompViewList  "+ form.getSpotCompViewList().size());

		}
		catch(Exception e)
		{
			logger.info("this is Exception in Spot Inspection BD - getSpotInspViewDet()" + e);
		}
		return form;
	}

	/**
	 * getting the spot Inspection Schedule update details.
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public  boolean SpotInspSechUpd(String _refApplNo,String _refInsPlanDate, String roleId, String funId, String userId,String remarks) throws Exception
	{
		boolean blnSechUpd = false;
		SpotInspBO objBo = new SpotInspBO();
		_refInsPlanDate=getOracleDate(_refInsPlanDate);

		try
		{
			blnSechUpd = objBo.SpotInspSechUpd(_refApplNo,_refInsPlanDate,roleId,funId,userId,remarks);
			logger.info("Wipro in Spot Inspection BD - getSpotInspViewDet() AFTER EXCUTIN QUERY");
		}
		catch(Exception e)
		{
			logger.info("this is Exception in Spot Inspection BD - getSpotInspViewDet()" + e);
		}

		return blnSechUpd;
	}

	/**
	 * getting the spot Inspection Completion update details.
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public  boolean SpotInspCompUpd(SpotInspForm _refForm, String roleId, String funId, String userId, HttpServletRequest request) throws Exception
	{
		boolean blnSechUpd = false;
		SpotInspBO objBo = new SpotInspBO();
		logger.info("  Enter in to the Wipro in Spot Inspection BD - getSpotInspViewDet() ");
		logger.info("Wipro in Spot Inspection BD - SpotInspCompUpd() before EXCUTIN QUERY");
		try
		{
			if(_refForm.getStatus().equalsIgnoreCase("R"))
			{
				blnSechUpd = objBo.SpotReInspCompUpd(_refForm,roleId,funId,userId,request);
			}
			else
			{
				blnSechUpd = objBo.SpotInspCompUpd(_refForm,roleId,funId,userId,request);
			}
			logger.info("Wipro in Spot Inspection BD - SpotInspCompUpd() AFTER EXCUTIN QUERY");
		}
		catch(Exception e)
		{
			logger.info("this is Exception in Spot Inspection BD - SpotInspCompUpd()" + e);
		}

		return blnSechUpd;
	}


	/**
	 * getting the spot Inspection Schdule details.
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public  SpotInspForm getSpotInspSechDet(SpotInspForm form) throws Exception
	{

		logger.info("  Enter in to the Wipro in Spot Inspection BD - getSpotInspSechDet() ");
		list = new ArrayList();
		ArrayList retRemList  = new ArrayList();
		ArrayList retList1 = new ArrayList();
		String applNo = form.getSchApplNo();
		SpotInspBO objBo = new SpotInspBO();
		logger.info("Wipro in Spot Inspection BD - getSpotInspSechDet() visitRefId="+applNo);

		try
		{
			list = objBo.getSpotInspSechDet(applNo);
			retList1  =  (ArrayList)list.get(0);
			logger.info("Wipro in Spot Inspection BD - getSpotInspSechDet() before EXCUTIN QUERY"+retList1);
			//--START--Applicant Details.
			form.setSchApplNo((String)retList1.get(0));
			form.setApplicationDate((String)retList1.get(1));
			form.setSelectionDate((String)retList1.get(2));
			if(retList1.get(3).toString().equalsIgnoreCase("S"))
				form.setStatus("Scheduled");
			if(retList1.get(3).toString().equalsIgnoreCase("R"))
				form.setStatus("Recommended");
			if(retList1.get(3).toString().equalsIgnoreCase("C"))
				form.setStatus("Completed");
			if(retList1.get(3).toString().equalsIgnoreCase("p"))
				form.setStatus("Pending");
			if(retList1.get(3).toString().equalsIgnoreCase("A"))
				form.setStatus("Approved");


		} catch (Exception e) {
			logger.info("this is Exception in Spot Inspection BD - getSpotInspSechDet()" + e);
		}

		return form;
	}

	/**
	 * getting the spot Inspection Schdule details.
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public  SpotInspForm getSpotInspCompDet(SpotInspForm form) throws Exception
	{

		logger.info("  Enter in to the Wipro in Spot Inspection BD - getSpotInspCompDet() ");
		list = new ArrayList();
		ArrayList retRemList  = new ArrayList();
		ArrayList retList1 = new ArrayList();
		String applNo = form.getSchApplNo();
		SpotInspBO objBo = new SpotInspBO();
		logger.info("Wipro in Spot Inspection BD - getSpotInspCompDet() visitRefId="+applNo);
		logger.info("Wipro in Spot Inspection BD - getSpotInspCompDet() before EXCUTIN QUERY");
		try
		{
			list = objBo.getSpotInspCompDet(applNo);
			retList1  =  (ArrayList)list.get(0);
			logger.info("Wipro in Spot Inspection BD - getSpotInspCompDet() before EXCUTIN QUERY"+retList1);
			//--START--Applicant Details.
			form.setCompApplNo((String)retList1.get(0));
			form.setApplicationDate((String)retList1.get(1));
			form.setSelectionDate((String)retList1.get(2));
			form.setInsPlanDate((String) retList1.get(3));
			if(retList1.get(5).toString().equalsIgnoreCase("A"))
				form.setStatus("Approved");
			form.setOffSro((String)retList1.get(6));
			form.setDistrictId((String)retList1.get(7));
			logger.info("Wipro in Spot Inspection BD - getSpotInspCompDet() AFTER EXCUTIN QUERY"+list);
		}
		catch(Exception e)
		{
			logger.info("this is Exception in Spot Inspection BD - getSpotInspCompDet()" + e);
		}
		return form;
	}
	/**
	 * getting the spot Inspection DR Request  details.
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public  SpotInspForm getSpotDrReqDet(SpotInspForm form) throws Exception
	{
		logger.info("  Enter in to the Wipro in Spot Inspection BD - getSpotDrReqDet() ");
		list = new ArrayList();
		SpotInspBO objBo = new SpotInspBO();
		ArrayList retRemList  = new ArrayList();
		ArrayList retList1 = new ArrayList();
		String applNo = form.getSchApplNo();
		logger.info("Wipro in Spot Inspection BD - getSpotDrReqDet() visitRefId="+applNo);
		logger.info("Wipro in Spot Inspection BD - getSpotDrReqDet() before EXCUTIN QUERY");

		try
		{
			list = objBo.getSpotDrReqDet(applNo);
			retList1  =  (ArrayList)list.get(0);
			logger.info("Wipro in Spot Inspection BD - getSpotDrReqDet() before EXCUTIN QUERY"+retList1);
			//--START--Applicant Details.
			form.setDrReqApplNo((String)retList1.get(0));
			form.setApplicationDate((String)retList1.get(1));
			form.setStatus((String) retList1.get(2));
			if(((String)retList1.get(2)).equalsIgnoreCase("C"))
				form.setStatus("Completed");
			form.setSroName((String) retList1.get(3));
			form.setSrName((String) retList1.get(4));
			form.setRemarks((String) retList1.get(5));
			//--END--Request Details.
			logger.info("Wipro in Spot Inspection BD - getSpotDrReqDet() AFTER EXCUTIN QUERY"+list);
		}
		catch(Exception e)
		{
			logger.info("this is Exception in Spot Inspection BD - getSpotDrReqDet()" + e);
		}
		return form;
	}

	/**
	 * getting the spot Inspection DR Request View  details.
	 * @param form
	 * @return
	 * @throws Exception
	 */


	public  SpotInspForm getSpotDrViewDet(SpotInspForm form) throws Exception
	{

		logger.info("  Enter in to the Wipro in Spot Inspection BD - getSpotDrViewDet() ");
		list = new ArrayList();
		SpotInspBO objBo = new SpotInspBO();
		ArrayList retRemList  = new ArrayList();
		ArrayList retList1 = new ArrayList();
		String applNo = form.getSchApplNo();
		logger.info("Wipro in Spot Inspection BD - getSpotDrViewDet() visitRefId="+applNo);
		logger.info("Wipro in Spot Inspection BD - getSpotDrViewDet() before EXCUTIN QUERY");
		try
		{
			list = objBo.getSpotDrViewDet(applNo);
			retList1  =  (ArrayList)list.get(0);
			logger.info("Wipro in Spot Inspection BD - getSpotDrViewDet() before EXCUTIN QUERY"+retList1);
			//--..START--Applicant Details.
			form.setDrViewApplNo((String)retList1.get(0));
			form.setApplicationDate((String)retList1.get(1));
			form.setStatus((String) retList1.get(2));
			if(((String)retList1.get(2)).equalsIgnoreCase("C"))
				form.setStatus("Completed");
			if(((String)retList1.get(2)).equalsIgnoreCase("Y"))
				form.setStatus("Pending for approval");
			form.setSroName((String) retList1.get(3));
			form.setSrName((String) retList1.get(4));
			if(((String)retList1.get(5)).equalsIgnoreCase("1"))
				form.setReqType("Spot Inspection Request");
			else
				form.setReqType("Spot Inspection Request");
			form.setSrReason((String) retList1.get(6));
			form.setRemarks((String) retList1.get(7));
			//--END--Request Details.
		} catch (Exception e) {
			e.printStackTrace();
		}

		return form;
	}
	/**
	 * getting the spot Inspection Schedule update details.
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @param form
	 * @return
	 * @throws Exception
	 */

	public boolean updateDrReqDet(SpotInspForm _refForm, String roleId, String funId, String userId) throws Exception {
		boolean blnDrReqUpd = false;

		SpotInspBO objBo = new SpotInspBO();
		String param[]=new String[5];
		logger.info("  Enter in to the Wipro in Spot Inspection BD - updateDrReqDet() ");

		param[0]=_refForm.getInspStatus();
		param[1]=_refForm.getAssSroName();
		param[2]=_refForm.getDrReason();
		param[3]=_refForm.getDrRemarks();
		param[4]=_refForm.getDrReqApplNo();
		logger.info(_refForm.getDrReqApplNo());
		try {
			blnDrReqUpd = objBo.updateDrReqDet(param,roleId,funId,userId);
		} catch (Exception e) {
			logger.info("this is Exception in Spot Inspection BD - updateDrReqDet()"+ e);
		}

		return blnDrReqUpd;
	}

	/**
	 * 
	 * @param regTxnId
	 * @return
	 * @throws Exception
	 */

	public SpotInspDTO getRegInfo(String regTxnId)
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList y = objBo.getRegInfo(regTxnId);
		SpotInspDTO a= new SpotInspDTO();
		if(y!=null && y.size()>0)
		{
			ArrayList u = (ArrayList) y.get(0);
			a.setRegFee((String)u.get(0));
			a.setJandpadDuty((String)u.get(1));
			a.setStampDuty((String)u.get(2));
			a.setMunicipalDuty((String)u.get(3));
			a.setUpkarDuty((String)u.get(4));
			a.setTotalDuty((String)u.get(5));
		}

		return a;

	}
	public SpotInspDTO getReRegInfo(String regTxnId)
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList y = objBo.getReRegInfo(regTxnId);
		SpotInspDTO a= new SpotInspDTO();
		if(y!=null && y.size()>0)
		{
			ArrayList u = (ArrayList) y.get(0);

			a.setRegFee((String)u.get(0));
			a.setStampDuty((String)u.get(1));
			a.setRemark((String)u.get(2));
			a.setCrossRegFee((String)u.get(3));
			a.setCrossStampDuty((String)u.get(4));
			//			a.setCrossRemark((String)u.get(5));
			a.setCrossRemark("");
			a.setJandpadDuty((String)u.get(6));
			a.setCrossJanpadDuty((String)u.get(7));
			a.setMunicipalDuty((String)u.get(8));
			a.setCrossMuncipalDuty((String)u.get(9));
			a.setUpkarDuty((String)u.get(10));
			a.setCrossUpkarDuty((String)u.get(11));
			a.setTotalDuty((String)u.get(12));
			a.setCrossTotalDuty((String)u.get(13));
		}

		return a;

	}


	public ArrayList getPropIdList(String regTxnId) throws Exception
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret = objBo.getPropIdList(regTxnId);

		ArrayList list1 = new ArrayList();


		if (ret != null)
		{
			for (int i = 0; i < ret.size(); i++) {
				logger.info("In for loop--->  "+ret.get(i));
				ArrayList list = (ArrayList) ret.get(i);
				logger.info("(String) list.get(0)  "+(String) list.get(0));
				SpotInspDTO dto = new SpotInspDTO();
				dto.setPropId((String) list.get(0));
				dto.setOldArea((String) list.get(1));
				String temp = (String) list.get(2);

				dto.setOldConstructedArea((String) list.get(2));
				dto.setNewConstructedArea((String) list.get(2));

				dto.setOldPropertyUse((String) list.get(3));
				dto.setOldTypeOfConstruction((String) list.get(4));
				dto.setNewArea((String) list.get(1));

				dto.setNewPropertyUse((String) list.get(3));
				dto.setNewTypeOfConstruction((String) list.get(4));
				dto.setFloorId((String) list.get(5));
				dto.setRegId((String) list.get(6));

				dto.setNewMarketValue((String) list.get(7));
				dto.setOldMarketValue((String) list.get(7));
				dto.setOldguidelineValue((String) list.get(7));
				dto.setNewguidelineValue((String) list.get(7));
				logger.info("dto.getPropId()  "+dto.getPropId());

				if(((String) list.get(3)).equals("IRRIGATED AGRICULTURAL LAND"))
				{
					dto.setAreaType("HECTARE");
				}
				else if(((String) list.get(3)).equals("UN-IRRIGATED AGRICULTURAL LAND")){
					dto.setAreaType("HECTARE");
				}else{
					dto.setAreaType("SQM");
				}

				list1.add(dto);
			}
			logger.info(" getPropIdList List SpotInspBD " + list1);
		}
		logger.info(" getPropIdList List SpotInspBD " + list1.size());
		return list1;
	}

	public ArrayList getPropIdListM(String regTxnId) throws Exception
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret = objBo.getPropIdListM(regTxnId);
		if(ret!=null)

			return ret;
		else
			return null;
	}


	public ArrayList getPropIdListL(String regTxnId) throws Exception
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret = objBo.getPropIdListL(regTxnId);
		ArrayList retList=new ArrayList();
		if(ret!=null)
		{
			for(int i=0;i<ret.size();i++)
			{
				ArrayList rowList=(ArrayList) ret.get(i);
				SpotInspDTO dto = new SpotInspDTO();
				dto.setPropertyId((String)rowList.get(0));
				retList.add(dto);
			}



		}
		return retList;

	}


	public ArrayList getPropIdListLR(String regTxnId) throws Exception
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret = objBo.getPropIdListLR(regTxnId);
		ArrayList retList=new ArrayList();
		if(ret!=null)
		{
			for(int i=0;i<ret.size();i++)
			{
				ArrayList rowList=(ArrayList) ret.get(i);
				SpotInspDTO dto = new SpotInspDTO();
				dto.setPropertyId((String)rowList.get(0));
				dto.setPostInspectionComment((String)rowList.get(1)!=null && !((String)rowList.get(1)).isEmpty()?(String)rowList.get(1):"-");
				retList.add(dto);
			}



		}
		return retList;

	}


	public ArrayList getPropIdListRe(String regTxnId) throws Exception
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret = objBo.getPropIdListRe(regTxnId);
		if(ret!=null)

			return ret;
		else
			return null;
	}


	public ArrayList getNewPropIdList(String regTxnId) throws Exception
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList ret = objBo.getNewPropIdList(regTxnId);
		ArrayList list1 = new ArrayList();

		if (ret != null)
		{
			for (int i = 0; i < ret.size(); i++) {
				logger.info("In for loop--->  "+ret.get(i));
				ArrayList list = (ArrayList) ret.get(i);
				logger.info("(String) list.get(0)  "+(String) list.get(0));
				SpotInspDTO dto = new SpotInspDTO();
				dto.setPropId((String) list.get(1));

				dto.setOldArea((String) list.get(15)); //Area 1

				dto.setOldConstructedArea((String) list.get(16)); //Constructed Area 2

				dto.setOldPropertyUse((String) list.get(17));  //Property USe 3

				dto.setOldTypeOfConstruction((String) list.get(18)); // Type Of Construction 4

				dto.setNewArea((String) list.get(2));  //Area 1

				dto.setCrossArea((String) list.get(2));  //Area 1

				dto.setNewConstructedArea((String) list.get(3));  //Constructed Area 2

				dto.setCrossConstructedArea((String) list.get(3));   //Constructed Area 2

				dto.setNewPropertyUse((String) list.get(4));   //Property Use 3

				dto.setCrossPropertyUse((String) list.get(4)); //Property Use 3

				dto.setNewTypeOfConstruction((String) list.get(5));  // Type Of Construction 4

				dto.setCrossTypeOfConstruction((String) list.get(5)); // Type Of Construction 4

				dto.setFloorId((String) list.get(19));

				dto.setRegId((String) list.get(0));


				dto.setOldMarketValue((String) list.get(30));

				dto.setNewMarketValue((String) list.get(31));
				dto.setCrossMarketValue((String) list.get(31));



				dto.setOldguidelineValue((String) list.get(32));
				dto.setNewguidelineValue((String) list.get(33));
				dto.setCrossGuideLineValue((String) list.get(33));

				if(((String) list.get(17)).equals("IRRIGATED AGRICULTURAL LAND"))
				{
					dto.setAreaType("HECTARE");
				}
				else if(((String) list.get(17)).equals("UN-IRRIGATED AGRICULTURAL LAND")){
					dto.setAreaType("HECTARE");
				}else{
					dto.setAreaType("SQM");
				}

				logger.info("dto.getPropId()  "+dto.getPropId());
				list1.add(dto);

			}
			logger.info(" getPropIdList List SpotInspBD " + list1);
		}
		logger.info(" getPropIdList List SpotInspBD " + list1.size());
		return list1;
	}

	/*public boolean insSpReqDet(SpotInspForm _spForm, String roleId, String funId, String userId) throws Exception
	{
       boolean blnInsVal = false;
       IGRSCommon common = new IGRSCommon();
		String param[]=new String[9];
		 SpotInspBO objBo = new SpotInspBO();
		logger.info("  Enter in to the Wipro in Spot Inspection BD - insSpReqDet() ");

		String spotId = null;
		spotId = common.getSequenceNumber("IGRS_SPOT_INS_TXN_ID_SEQ", "SpotTxn");
		_spForm.setSpotInspTxnId(spotId);
		param[0]=_spForm.getRegTxnId();
		param[1]=_spForm.getPropId();
		param[2]=_spForm.getUserId();
		param[3]=_spForm.getUserId();
		param[4]=_spForm.getRemarks();
		param[5]=_spForm.getSpotInspTxnId();
		param[6]=_spForm.getSiReason();
		param[7]=_spForm.getReqType();
		param[8]=CommonUtil.getConvertedDate(_spForm.getReqiDate());

		try {
			blnInsVal  = objBo.insSpReqDet(param,roleId,funId,userId);
		} catch (Exception e) {
			logger.info("this is Exception in Spot Inspection BD - insSpReqDet()"+ e);
		}
		return blnInsVal;
	}*/

	public boolean insSpSpotAssignDet(SpotInspForm _spForm, String roleId, String funId, String userId) throws Exception
	{
		boolean blnInsVal = false;
		IGRSCommon common = new IGRSCommon();
		String param[]=new String[13];
		SpotInspBO objBo = new SpotInspBO();
		logger.info("  Enter in to the Wipro in Spot Inspection BD - insSpReqDet() ");
		ArrayList assignCriteriaList  =  new ArrayList();
		ArrayList assignNewList  =  new ArrayList();
		int criteriaListSize=_spForm.getAssignedCriteriaList().size();
		int count=0;
		for(int i=0;i<criteriaListSize;i++)
		{

			SpotInspDTO assignSrDTO =new SpotInspDTO();
			SpotInspDTO dto  =  (SpotInspDTO) _spForm.getAssignedCriteriaList().get(i);


			String spotId = null;
			spotId = common.getSequenceNumber("IGRS_SPOT_INS_TXN_ID_SEQ", "SpotTxn");
			_spForm.setSpotInspTxnId(spotId);
			param[0]=spotId;
			param[1]=dto.getRegId();
			param[2]=dto.getZoneId();
			param[3]=dto.getDistrictId();
			param[4]=dto.getUserId();
			param[5]=dto.getDruserId();
			param[6]=dto.getUserId();
			param[7]=common.getSpotMaxDay();;
			param[8]=userId;
			param[9]=dto.getMarketValue();
			param[10]=dto.getRegFee();
			param[11]=dto.getStampDuty();
			param[12]=dto.getTehsilId();
			dto.setSpotId(spotId);



			assignNewList.add(dto);
			count++;


			try {
				blnInsVal  = objBo.insSpSpotAssignDet(param,roleId,funId,userId);
			} catch (Exception e) {
				logger.info("this is Exception in Spot Inspection BD - insSpReqDet()"+ e);
			}
		}
		_spForm.setAssignedCriteriaList(assignNewList);
		return blnInsVal;
	}

	public boolean updateSpReqDet(SpotInspForm _spForm, String roleId, String funId, String userId) throws Exception
	{
		boolean blnInsVal = false;
		IGRSCommon common = new IGRSCommon();
		String param[]=new String[8];
		SpotInspBO objBo = new SpotInspBO();
		logger.info("  Enter in to the Wipro in Spot Inspection BD - insSpReqDet() ");

		/*String spotId = null;
			spotId = common.getSequenceNumber("IGRS_SPOT_INS_TXN_ID_SEQ", "SpotTxn");
			_spForm.setSpotInspTxnId(spotId);
			param[0]=_spForm.getPropId();
			param[1]=_spForm.getUserId();
			param[2]=_spForm.getUserId();
			param[3]=_spForm.getRemarks();
			//param[4]=_spForm.getSpotInspTxnId();
			param[4]=_spForm.getSiReason();
			param[5]=_spForm.getReqType();
			param[6]=CommonUtil.getConvertedDate(_spForm.getReqiDate());
			param[7]=_spForm.getRegTxnId();
		 */
		try {
			blnInsVal  = objBo.updateSpReqDet(_spForm,roleId,funId,userId);
		} catch (Exception e) {
			logger.info("this is Exception in Spot Inspection BD - insSpReqDet()"+ e);
		}
		return blnInsVal;
	}

	/**
	 * 
	 * @param propId
	 * @return
	 * @throws Exception
	 */

	public String getPropStatus(String propId)  throws Exception
	{
		{
			SpotInspDAO dao = new  SpotInspDAO();
			ArrayList list = new ArrayList();
			ArrayList retList1 = new ArrayList();
			String propStatus = "";
			try {

				//list = dao.getLockStatus(regId);

				String[] par1 = new String[1];
				par1[0] = propId;
				//ArrayList ret2 = dao.getLockStatus(par1);
				list = dao.getPropStatus(par1);
				retList1 = (ArrayList) list.get(0);
				propStatus = ((String) retList1.get(0));
				logger.info("PropertyLockingBD in lockstatus(): lockstatus="+ propStatus);
			} catch (Exception e)
			{
				logger.debug("PropertyLockingBD -- Exception in PropertyLockingBD - getFee():"+ e);
			}
			return propStatus;
		}
	}

	public String getAssignPercantage()  throws Exception
	{
		{
			SpotInspDAO dao = new  SpotInspDAO();

			String propStatus = "";
			try {

				//list = dao.getLockStatus(regId);



				//ArrayList ret2 = dao.getLockStatus(par1);

				propStatus = dao.getAssignPercantage();
				logger.info("PropertyLockingBD in lockstatus(): lockstatus="+ propStatus);
			} catch (Exception e)
			{
				logger.debug("PropertyLockingBD -- Exception in PropertyLockingBD - getFee():"+ e);
			}
			return propStatus;
		}
	}

	public String getType(String office) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getType(office);

	}

	public String getDIGZone(String office) {

		SpotInspBO objBo = new SpotInspBO();
		return objBo.getDIGZoneBo(office);
	}

	public ArrayList getDistDIGList(String zoneId, String language) {
		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  =  objBo.getDistDIGListBO(zoneId, language);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictName((String)lst.get(0));
				dto.setDistrictId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getState  "+list);
		}
		return list;

	}

	public ArrayList getDistList(String officeId) {

		SpotInspBO objBo = new SpotInspBO();

		ArrayList ret  =  objBo.getDistListBO(officeId);
		list  =  new ArrayList();

		if (ret != null) {
			for (int i  =  0; i < ret.size(); i++) {
				ArrayList lst  =  (ArrayList)ret.get(i);
				SpotInspDTO dto  =  new SpotInspDTO();
				dto.setDistrictId((String)lst.get(0));
				dto.setZoneId((String)lst.get(1));
				list.add(dto);
			}
			logger.info("SpotInspBD----getDistrcit  "+list);
		}
		return list;


	}

	public boolean insertAssignSpotData(HashMap<String, ArrayList<SpotInspDTO>> srMap, String roleId, String funId, String userId) {

		SpotInspBO objBo = new SpotInspBO();


		return objBo.insertAssignSpotDataBO(srMap,roleId,funId,userId);
	}

	public HashMap<String, ArrayList<SpotInspDTO>> getSrLists(Iterator<String> ids) {

		SpotInspBO objBo = new SpotInspBO();
		return objBo.getSrLists(ids);
	}
	
	//added by saurav
	public HashMap<String, ArrayList<SpotInspDTO>> getDocUserList(ArrayList<SpotInspDTO> edata){
		SpotInspBO objBo = new SpotInspBO();
		TreeSet<String> userIds = new TreeSet<String>();
		Iterator<SpotInspDTO> itr = edata.iterator();
		while(itr.hasNext()){
			SpotInspDTO id = itr.next();
			userIds.add(id.getDocCompletionSR());
		}
		HashMap<String, ArrayList<SpotInspDTO>> res = new HashMap<String, ArrayList<SpotInspDTO>>();
		
		Iterator<String> ids = userIds.iterator();
		while(ids.hasNext()){
			String id1 = ids.next();
			Iterator<SpotInspDTO> it2 = edata.iterator();
			ArrayList<SpotInspDTO> spList = new ArrayList<SpotInspDTO>();
			while(it2.hasNext()){
				SpotInspDTO spdto = it2.next();
				if(id1.equalsIgnoreCase(spdto.getDocCompletionSR())){
					spList.add(spdto);
				}
			}
			res.put(id1, spList);
		}
		return res;
	}
	//end saurav
	public ArrayList<SpotInspDTO> getDashBoardDetails(SpotInspForm spForm,String userId) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList m = null;
		ArrayList temp  = objBo.getDashBoardDetails(spForm,userId);

		if(temp!=null & temp.size()>0)
		{

			m = new ArrayList();

			for(int i=0;i<temp.size();i++)
			{

				ArrayList demo = (ArrayList) temp.get(i);

				SpotInspDTO a = new SpotInspDTO();

				a.setSpotId((String)demo.get(0));
				a.setDrName((String)demo.get(1));
				a.setAssignPersonName((String)demo.get(2));
				a.setDateinsp((String)demo.get(3));
				a.setDatetodo((String)demo.get(4));
				String status = (String)demo.get(5);
				if(status.equalsIgnoreCase("Y"))
				{
					status = "Inspection Pending";
				}
				else
				{
					status = "Inspection Completed";
				}
				a.setStatus(status);
				a.setDigName((String)demo.get(6));
				a.setZoneName((String)demo.get(7));
				m.add(a);

			}



		}
		return m;
	}

	public ArrayList getDrillDownDashboardValues(String id) throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList temp = objBo.getDrillDownDashboardValues(id);
		ArrayList m = null;

		if(temp!=null & temp.size()>0)
		{
			m = new ArrayList();

			for(int i=0;i<temp.size();i++)
			{

				ArrayList demo = (ArrayList) temp.get(i);

				SpotInspDTO a = new SpotInspDTO();
				a.setSpotId(id);
				a.setRegIdcompletion((String)demo.get(0));
				a.setZoneName((String)demo.get(1));
				a.setDistrictName((String)demo.get(2));
				a.setSrName((String)demo.get(4));
				a.setAssignPersonName((String)demo.get(3));
				a.setDateinsp((String)demo.get(5));
				a.setDatetodo((String)demo.get(6));
				String found = (String)demo.get(7);
				if(found!=null)
				{
					//changed by akansha for spot insp ; 5/7/19
					if(found.equalsIgnoreCase("Y"))
					{
						found="Found Correct";

					}
					else
					{
						found="Found Incorrect";
					}
				}
				else
				{
					found = "N/A";
				}
				a.setFoundStatus(found);
				String complaintId = (String)demo.get(8);
				if(complaintId==null)
				{
					complaintId = "N/A";
				}

				a.setComplaintId(complaintId);



				m.add(a);

			}

		}

		return m;
	}

	public ArrayList getReDrillDownDashboardValues(String id) {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList temp = objBo.getReDrillDownDashboardValues(id);
		ArrayList m = null;

		if(temp!=null & temp.size()>0)
		{
			m = new ArrayList();

			for(int i=0;i<temp.size();i++)
			{

				ArrayList demo = (ArrayList) temp.get(i);

				SpotInspDTO a = new SpotInspDTO();

				a.setRegIdcompletion((String)demo.get(0));
				a.setZoneName((String)demo.get(1));
				a.setDistrictName((String)demo.get(2));
				a.setSrName((String)demo.get(4));
				a.setAssignPersonName((String)demo.get(3));
				a.setDateinsp((String)demo.get(5));
				a.setDatetodo((String)demo.get(6));
				String found = (String)demo.get(7);
				if(found!=null)
				{
					if(found.equalsIgnoreCase("N"))
					{
						found="Found Correct";

					}
					else
					{
						found="Found Incorrect";
					}
				}
				else
				{
					found = "N/A";
				}
				a.setFoundStatus(found);
				String complaintId = (String)demo.get(8);

				if(complaintId==null)
				{
					complaintId = "N/A";
				}

				a.setComplaintId(complaintId);
				a.setAreaTypeName((String)demo.get(9));
				String status = (String)demo.get(10);
				if(status.equalsIgnoreCase("Y"))
				{
					a.setStatus("Inspection Pending");
					a.setFoundStatus("N/A");
				}
				else
					a.setStatus("Inspection Completed")	;
				m.add(a);

			}

		}

		return m;
	}

	public boolean updateStatus(SpotInspForm spForm) {

		SpotInspBO objBo = new SpotInspBO();

		return 	objBo.updateStatus(spForm);


	}

	public ArrayList getReFilterList(ArrayList list2, String[] a,String office) {
		SpotInspDAO dao = new SpotInspDAO();
		ArrayList filter = new ArrayList();
		logger.debug("length" + a.length);
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<list2.size();j++)
			{
				SpotInspDTO ab = (SpotInspDTO) list2.get(j);
				String reg = ab.getRegIdcompletion();
				if(reg==null)
				{
					reg="";
				}
				logger.debug("reg num"+ab.getRegIdcompletion());
				if(ab.getRegIdcompletion().equalsIgnoreCase(a[i]))
				{
					SpotInspDTO abc = new SpotInspDTO();
					logger.debug("Match");
					String districtId = ab.getDistrictId();
					logger.debug("Distt"+districtId);
					ArrayList officeList = dao.getOfficeList(districtId,ab.getRegIdcompletion(),office);
					abc.setZoneName(ab.getZoneName());

					abc.setDistrictId(ab.getDistrictId());
					abc.setDrName(ab.getDrName());
					abc.setSrName(ab.getSrName());
					abc.setAssignPersonName(ab.getAssignPersonName());
					abc.setRegIdcompletion(ab.getRegIdcompletion());
					abc.setDistrictName(ab.getDistrictName());
					abc.setMarketValue(ab.getMarketValue());
					abc.setRegFee(ab.getRegFee());
					abc.setStampDuty(ab.getStampDuty());
					abc.setDateinsp(ab.getDateinsp());
					abc.setDatetodo(ab.getDatetodo());
					abc.setOfficeList(officeList);
					abc.setZoneName(ab.getZoneName());
					abc.setDigName(ab.getDigName());
					abc.setZoneId(ab.getZoneId());
					//abc.setOffice_id("OFC85~MP010852013A3010074");
					filter.add(abc);
					break;

				}


			}





		}
		return filter;

	}

	public HashMap<String, ArrayList<SpotInspDTO>> reassignData(ArrayList list2, String userId) {

		SpotInspDAO dao = new SpotInspDAO();
		HashMap<String, ArrayList<SpotInspDTO>> data = null;
		try {
			data = dao.getreassignData(list2,userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data ;


	}

	public ArrayList getSRORoleList(String office_id,String reg) {
		SpotInspDAO dao = new SpotInspDAO();
		ArrayList data = null;
		ArrayList srData = null;
		try {
			data = dao.getSRORoleList(office_id);

			if(data!=null & data.size()>0)
			{
				srData = new ArrayList();
				for(int i=0;i<data.size();i++)
				{
					ArrayList a = (ArrayList) data.get(i);
					SpotInspDTO dto = new SpotInspDTO();
					dto.setSrRoleName((String)a.get(1));
					dto.setSrRoleId((String)a.get(0)+"~"+reg);

					srData.add(dto);

				}




			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return srData ;
	}

	public void getBifercatedDuties(String regId, SpotInspForm spForm) {
		SpotInspDAO dao = new SpotInspDAO();
		dao.getBifercatedDuties(regId,spForm);


	}
	public String getRegTxnID(String eRegNo) throws Exception{
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getRegTxnID(eRegNo);
	}

	//Added by Akansha - STQC

	public SpotInspDTO getObservationInfo(String regTxnId)
	{
		SpotInspBO objBo = new SpotInspBO();
		ArrayList y = objBo.getReRegInfo(regTxnId);
		SpotInspDTO a= new SpotInspDTO();
		if(y!=null && y.size()>0)
		{
			ArrayList u = (ArrayList) y.get(0);
			a.setRegFee((String)u.get(0)!=null && !((String)u.get(0)).isEmpty()?(String)u.get(0):"-");
			a.setStampDuty((String)u.get(1)!=null && !((String)u.get(1)).isEmpty()?(String)u.get(1):"-");
			a.setRemark((String)u.get(2)!=null && !((String)u.get(2)).isEmpty()?(String)u.get(2):"-");
			a.setNewRegfee((String)u.get(3)!=null && !((String)u.get(3)).isEmpty()?(String)u.get(3):"-");
			a.setNewStampDuty((String)u.get(4)!=null && !((String)u.get(4)).isEmpty()?(String)u.get(4):"-");
			a.setNewRemark((String)u.get(5)!=null && !((String)u.get(5)).isEmpty()?(String)u.get(5):"-");
			a.setJandpadDuty((String)u.get(6)!=null && !((String)u.get(6)).isEmpty()?(String)u.get(6):"-");
			a.setNewJandpadDuty((String)u.get(7)!=null && !((String)u.get(7)).isEmpty()?(String)u.get(7):"-");
			a.setMunicipalDuty((String)u.get(8)!=null && !((String)u.get(8)).isEmpty()?(String)u.get(8):"-");
			a.setNewMunicipalDuty((String)u.get(9)!=null && !((String)u.get(9)).isEmpty()?(String)u.get(9):"-");
			a.setUpkarDuty((String)u.get(10)!=null && !((String)u.get(10)).isEmpty()?(String)u.get(10):"-");
			a.setNewUpkarDuty((String)u.get(11)!=null && !((String)u.get(11)).isEmpty()?(String)u.get(11):"-");
			a.setTotalDuty((String)u.get(12)!=null && !((String)u.get(12)).isEmpty()?(String)u.get(12):"-");
			a.setNewTotalDuty((String)u.get(13)!=null && !((String)u.get(13)).isEmpty()?(String)u.get(13):"-");
		}

		return a;

	}

	public SpotInspDTO getOtherSpotInsDtls(String regNo) {
		SpotInspBO objBo = new SpotInspBO();
		ArrayList y = objBo.getOtherSpotInsDtls(regNo);
		SpotInspDTO a= new SpotInspDTO();
		if(y!=null && y.size()>0)
		{
			ArrayList u = (ArrayList) y.get(0);
			a.setDateinsp((String)u.get(0)!=null && !((String)u.get(0)).isEmpty()?(String)u.get(0):"-");
			a.setFoundStatus((String)u.get(1));
			a.setChangeInVal((String)u.get(2)!=null && !((String)u.get(2)).isEmpty()?(String)u.get(2):"-");
			a.setUploadPath((String)u.get(3)!=null && !((String)u.get(3)).isEmpty()?(String)u.get(3):"-");
			a.setPhotoFilename((String)u.get(4)!=null && !((String)u.get(4)).isEmpty()?(String)u.get(4):"-");
		}

		return a;
	}

	public String getInspectionStatus(String id) {
		String ret ="";

		SpotInspBO objBo = new SpotInspBO();
		ret  =  objBo.getInspectionStatus(id);
		logger.info(" getInspectionStatus SpotInspBD "+ ret);

		return ret;
	}

	public boolean validateDate(String fromDate, String toDate)
	throws Exception {
		SpotInspBO objBo = new SpotInspBO();
		return objBo.validateDate(fromDate, toDate);
	}
	

	public String duration()throws Exception{

		SpotInspBO objBo = new SpotInspBO();
		return objBo.duration();
	}
}
