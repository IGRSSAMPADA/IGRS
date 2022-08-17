package com.wipro.igrs.propertyvaluation.bo;

/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  PropertyValuationBO.java 
 * Author      :  Madan Mohan 
 * Description :   
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.propertyvaluation.constant.CommonConstant;
import com.wipro.igrs.propertyvaluation.dao.PropertyValuationDAO;
import com.wipro.igrs.propertyvaluation.dto.AreaDTO;
import com.wipro.igrs.propertyvaluation.dto.DistrictDTO;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.FloorCalcTypeDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;
import com.wipro.igrs.propertyvaluation.dto.MahallaDTO;
import com.wipro.igrs.propertyvaluation.dto.MunicipalDTO;
import com.wipro.igrs.propertyvaluation.dto.PropertyDTO;
import com.wipro.igrs.propertyvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.propertyvaluation.dto.TehsilDTO;
import com.wipro.igrs.propertyvaluation.dto.UsePlotDTO;
import com.wipro.igrs.propertyvaluation.dto.WardDTO;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.util.PropertiesFileReader;
import java.util.Date;
import java.text.SimpleDateFormat;


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
				DistrictDTO dto=new DistrictDTO();
				System.out.println("Dist:-" + lst.get(0) + ":" + lst.get(1));
				dto.setDistrictID(Integer.parseInt((String)(lst.get(0))));
				
				if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
				dto.setDistrict((String) lst.get(1));
				}else{
					dto.setDistrict((String) lst.get(2));
				}
			
				list.add(dto);
			}

		}
		return list;
	}

	public String getCurrentYear() throws Exception
	{
		IGRSCommon common = new IGRSCommon();
		return common.getcurrYear();
	}

	
	/**
	 * @param districtId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getTehsil(int districtId,String languageLocale) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList ret = common.getThesil(districtId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				TehsilDTO dto = new TehsilDTO();
				System.out.println("Tehsil:-" + lst.get(0) + ":" + lst.get(1));
				dto.setTehsilID(Integer.parseInt((String) lst.get(0)));
				if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
				dto.setTehsil((String) lst.get(1));
				}else{
					dto.setTehsil((String) lst.get(2));
				}
				list.add(dto);
				System.out.println("added tehsil to list");
			}

		}
		return list;
	}
	
	/**
	 * @param districtId
	 * @return ArrayList
	 * @throws Exception
	 */
	public String getGuidelineValue(PropertyValuationDTO useDTO) throws Exception {
		IGRSCommon common = new IGRSCommon();
		
		
		int districtId = useDTO.getDistrictID();
		int tehsilId = useDTO.getTehsilID();
		int wardId = useDTO.getWardId();
		int mohallaId = useDTO.getMahallaId();
		int propertyId = useDTO.getPropertyTypeID();
	
		String[] propertyTypeL1 = useDTO.getUsePlotId().split("~");

		String propertyTypeL2Id = null;
		 
		String[] propertyTypeL2=null ;
	
		
		String propertyTypeL1Id = "";
		System.out.println("Ceiling type id ");
		System.out.println("Ceiling type id " + useDTO.getCeilingTypeId());
		String guidelineValue =null;
		if (useDTO.getCeilingTypeId()!= null ) {
		 propertyTypeL2 = useDTO.getCeilingTypeId().split("~");
		}
		
		System.out.println("Ceiling type id " +useDTO.getCeilingTypeId()!= null);
	
		if (propertyTypeL1 != null && propertyTypeL1.length == 3) {
			propertyTypeL1Id = propertyTypeL1[0];
		}
		
		System.out.println("Ceiling type id " +useDTO.getCeilingTypeId()!= null);
		
		if (propertyTypeL2 != null && propertyTypeL2.length == 3) {
			propertyTypeL2Id = propertyTypeL2[0];
		}
		
		
		try {
			ArrayList list = new ArrayList();
			
			
			System.out.println("Checking market value");
			
 	                ArrayList	ret = new IGRSCommon().getGuidelineValue( districtId,
							tehsilId, wardId, mohallaId, propertyId, propertyTypeL1Id,
							propertyTypeL2Id);
 	           	System.out.println("i am here in getGuidelineValue ");
			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					try{
					ArrayList lst = (ArrayList) ret.get(i);
					System.out.println((String) lst.get(0));
					guidelineValue=((String) lst.get(0));
					}
					catch(Exception e)
					{
						System.out.println(e);
					}		}
			}
			}catch(Exception e)
			{
				System.out.println(e);
			}
			System.out.println("guidelineValue is"+guidelineValue);
					return guidelineValue;
		}
/**
	 * @param tehsilId
	 * @return ArrayList
	 * @throws Exception
	 */
	public String getGuidelineDuration(int districtId) throws Exception {
		IGRSCommon common = new IGRSCommon();
		
		ArrayList ret= new ArrayList();
		try{
			System.out.println("hojyoiyou"+ret);
		 ret = common.getGuidelineDuration(districtId);
			System.out.println(ret);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("I m here "+ret.size());
		ArrayList list = new ArrayList();
		String durationTo = null;
		Date dTO;
		
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				try{
				ArrayList lst = (ArrayList) ret.get(i);
				System.out.println((String) lst.get(0));
				String todaysDate=((String) lst.get(0));
				//System.out.println(todaysDate);
				SimpleDateFormat formatter	  = new SimpleDateFormat("yyyy-mm-dd");
				dTO = formatter.parse(todaysDate);
				SimpleDateFormat formatter2	  = new SimpleDateFormat("dd/mm/yyyy");
				System.out.println("Date after SDF"+dTO);
				durationTo = formatter2.format(dTO);
				System.out.println("Date after SDF"+durationTo);
			
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				System.out.println(durationTo);
			}

		}
		return durationTo;
	}

	/**
	 * @param tehsilId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWard(int tehsilId, int areaType, int wardType,String languageLocale)
			throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList ret = common.getWard(tehsilId, areaType, wardType);
	//	System.out.println("Tehsil:-" + ret.get(0) + ":" + ret.get(1));
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				WardDTO dto = new WardDTO();
				System.out.println("Tehsil:-" + lst.get(0) + ":" + lst.get(1));
				dto.setWardId(Integer.parseInt((String) lst.get(0)));
				if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
				dto.setWard((String) lst.get(1));
				}else{
					dto.setWard((String) lst.get(2));	
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
	public ArrayList getMahalla(int patwariId,String languageLocale) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList ret = common.getMahalla(patwariId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				MahallaDTO dto = new MahallaDTO();
				System.out.println("Mahalla:-" + lst.get(0) + ":" + lst.get(1));
				dto.setMahallaId(Integer.parseInt((String) lst.get(0)));
				if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
				dto.setMahalla((String) lst.get(1));
				}else{
					dto.setMahalla((String) lst.get(2));
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
					PropertyDTO dto = new PropertyDTO();
					dto.setPropertyTypeID(Integer.parseInt((String) lst.get(0)));
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					dto.setPropertyType((String) lst.get(1));
					}else{
						dto.setPropertyType((String) lst.get(2));	
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
			System.out.println(x);
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
					MunicipalDTO dto = new MunicipalDTO();
					dto.setMunicipalBodyID(Integer.parseInt((String) lst.get(0)));
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					dto.setMunicipalBody((String) lst.get(1));
					}else{
						dto.setMunicipalBody((String) lst.get(2));
					}
					
					list.add(dto);
				}
			}

		} catch (Exception x) {
			System.out.println(x);
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
					AreaDTO dto=new AreaDTO();
					dto.setAreaId(Integer.parseInt((String) lst.get(0)));
					
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					dto.setAreaType((String) lst.get(1));
					}else{
						dto.setAreaType((String) lst.get(2));
					}
					
					
					list.add(dto);
				}

			}

		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	/**
	 * @param propertyId
	 * @return ArrayList
	 */
	public ArrayList getUsePlot(int propertyId,String languageLocale) {
		ArrayList list = new ArrayList();
		String usePlot[][] = { { "R", "Residential" }, { "C", "Commercial" } };

		try {
			ArrayList retList = new IGRSCommon().getUsedPlot(propertyId);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setUsePlotId((String) lst.get(0));
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					dto.setUsePlot((String) lst.get(1));
					dto.setHdnUsePlot((String) lst.get(0) + "~"
							+ (String) lst.get(1) + "~" + (String) lst.get(2));
					}else{
						dto.setUsePlot((String) lst.get(3));
						dto.setHdnUsePlot((String) lst.get(0) + "~"
								+ (String) lst.get(3) + "~" + (String) lst.get(2));
					}
			     //   dto.setHdnUsePlot((String) lst.get(0) + "~"
				//			+ (String) lst.get(1) + "~" + (String) lst.get(2));
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	/**
	 * @param propertyTypeL1Id
	 * @return ArrayList
	 */
	public ArrayList getL2Type(String propertyTypeL1Id,String languageLocale) {
		ArrayList list = new ArrayList();

		try {
			ArrayList retList = new IGRSCommon()
					.getBuildingType(propertyTypeL1Id);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setCeilingTypeId((String) lst.get(0));
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					dto.setCeilingType((String) lst.get(2));
					dto.setHdnCeilingName((String) lst.get(0) + "~"
							+ (String) lst.get(1) + "~" + (String) lst.get(2));
					}else{
						dto.setCeilingType((String) lst.get(3));
						dto.setHdnCeilingName((String) lst.get(0) + "~"
								+ (String) lst.get(1) + "~" + (String) lst.get(3));
					}
					//dto.setHdnCeilingName((String) lst.get(0) + "~"
					//		+ (String) lst.get(1) + "~" + (String) lst.get(2));
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getFloorType(String l1ID,String languageLocale) {
		ArrayList list = new ArrayList();

		try {
			ArrayList retList = new IGRSCommon().getFloor(l1ID);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setTypeFloorID((String) lst.get(0));
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					dto.setTypeFloorName((String) lst.get(1));
					dto.setTypeFloorDesc((String) lst.get(2));
					dto.setHdnTypeFloor((String) lst.get(0) + "~"
							+ (String) lst.get(1) + "~" + (String) lst.get(2));
					}else{
						dto.setTypeFloorName((String) lst.get(3));	
						dto.setTypeFloorDesc((String) lst.get(3));
						dto.setHdnTypeFloor((String) lst.get(0) + "~"
								+ (String) lst.get(3) + "~" + (String) lst.get(3));
					}
					
					//dto.setHdnTypeFloor((String) lst.get(0) + "~"
					//		+ (String) lst.get(1) + "~" + (String) lst.get(2));
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	public ArrayList getLandType() {
		
		ArrayList list = new ArrayList();
		
			try {			
				ArrayList retList = new IGRSCommon().getLandType();
				for (int i = 0; i <  retList.size(); i++) {

					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setLandMeterId((String) list.get(0));
					dto.setLandMeterType((String) list.get(1));
					list.add(dto);
				}
	} catch (Exception e) {
				
				e.printStackTrace();
			}
		
		return list;
	}
	
	public ArrayList getLandType(String getUsePlotId,String languageLocale) {
		ArrayList list = new ArrayList();
		
		try {			
			ArrayList retList = new IGRSCommon().getLandType();
			
		String[] propertyTypeL1 = getUsePlotId.split("~");
		System.out.println("Entering into landtype list "+propertyTypeL1[0]);
		if (propertyTypeL1[0].equals("8"))	{	
			System.out.println("in if prop 8 : "+propertyTypeL1[0]);
			PropertyValuationDTO dto = new PropertyValuationDTO();
			for (int j = 0; j < retList.size(); j++) {
				ArrayList lst = (ArrayList) retList.get(j);
				if(((String) lst.get(1)).equals("SQM")){
					dto.setLandMeterId((String) lst.get(0));
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					dto.setLandMeterType((String) lst.get(1));
					}else{
						dto.setLandMeterType((String) lst.get(2));	
					}
					list.add(dto);
		
				}
			}	
		}
			else {
				for (int j = 0; j < retList.size(); j++) {
				PropertyValuationDTO dto = new PropertyValuationDTO();
				ArrayList lst = (ArrayList) retList.get(j);
				if((!((String) lst.get(1)).equals("SQM"))){
				dto.setLandMeterId((String) lst.get(0));
				if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
				dto.setLandMeterType((String) lst.get(1));
				}else{
					dto.setLandMeterType((String) lst.get(2));
				}
				list.add(dto);
				}
				}
			}
		}
			catch(Exception x) {
				System.out.println(x);
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
			System.out.println(x);
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
		int districtId = useDTO.getDistrictID();
		int tehsilId = useDTO.getTehsilID();
		int wardId = useDTO.getWardId();
		int mohallaId = useDTO.getMahallaId();
		int propertyId = useDTO.getPropertyTypeID();
		String[] usePlot = useDTO.getUsePlotId().split("~");
		System.out.println("use plo1 1:" + usePlot[0]);
		String[] usageType = useDTO.getUsePlotId().split("~");
		String[] param = null;
		String[] param1 = null;
		try {

			
			String buildingID = getPropertyID(CommonConstant.BUILDING_ID);
			System.out.println("buildingID Type:-" + buildingID);
			String SQL = "";
			System.out.println("Property Type:-" + propertyId);
			System.out.println("IF -"+buildingID.equals(propertyId));
				if (buildingID.equals(Integer.toString((propertyId)))) {
					param1 = new String[7];
				
					String[] l2Id = useDTO.getCeilingTypeId().split("~");
					String[] floorId = useDTO.getTypeFloorID().split("~");
					//String[] plotId = useDTO.getUsePlotId().split("~");
					String plotID=usePlot[0];
					System.out.println("i am true :"+buildingID.equals(propertyId));
					
						param1[0] = Integer.toString(districtId);
					
						param1[1] = Integer.toString(tehsilId);
					
						param1[2] = Integer.toString(wardId);
					
						param1[3] = Integer.toString(mohallaId);
				
						param1[4] = Integer.toString(propertyId);

						param1[5] = plotID;
						
						param1[6]=	l2Id[0];				
					/*if (usageType != null && usageType.length == 3) {
						param[5] = usageType[0];
					}
					/*if (l2Id != null && l2Id.length == 3) {
						param[6] = l2Id[0];
					}
					if (floorId != null && floorId.length == 3) {
						param[7] = floorId[0];
					}*/
					
					SQL = CommonSQL.SUB_CLAUSE_BUILDING_QUERY;
				} else {
					//param = new String[4];
					param = new String[6];
											param[0] =  Integer.toString(districtId);
											param[1] = Integer.toString(tehsilId);
											
											param[2] = Integer.toString(wardId);
										
											param[3] = Integer.toString(mohallaId);
									
											param[4] = Integer.toString(propertyId);

					
					
				if (usageType != null && usageType.length == 3) {
						param[5] = usageType[0];
					}
				param1=new String[7];	
				param1[0] =  Integer.toString(districtId);
				param1[1] = Integer.toString(tehsilId);
				
				param1[2] = Integer.toString(wardId);
			
				param1[3] = Integer.toString(mohallaId);
		
				param1[4] = Integer.toString(propertyId);



				if (usageType != null && usageType.length == 3) {
					param1[5] = usageType[0];
				}
				
				if(usePlot[0].equalsIgnoreCase("8"))
					{
						String ciel[]=useDTO.getCeilingTypeId().split("~");
						param1[6]=ciel[0];
					SQL = CommonSQL.SUB_CLAUSE_QUERY_500;
					}
					else
					{	
					SQL = CommonSQL.SUB_CLAUSE_DETAILS_QUERY;
					}
				}
				ArrayList retList =null;
				if(usePlot[0].equalsIgnoreCase("8")||usePlot[0].equalsIgnoreCase("4")||usePlot[0].equalsIgnoreCase("5")||usePlot[0].equalsIgnoreCase("10"))
				{
					 retList = new IGRSCommon().getSubClause(param1, SQL);
				}
				else{
					retList = new IGRSCommon().getSubClause(param, SQL);
				}
				if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					PropertyValuationDTO dto = new PropertyValuationDTO();
					dto.setSubClauseId((String) lst.get(0) + "~"
							+ (String) lst.get(1));
					dto.setSubClause((String) lst.get(1));
					dto.setAgriUnitFlag((String) lst.get(2));
					 dto.setHdnSubClause((String) lst.get(0)
					 +"~"+(String) lst.get(1));
					System.out.println(" SUB CLAUSE VALUE IS  "+ lst.get(0) + ":" + lst.get(1));
					listDTO.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return listDTO;
	}
	// Added by VINAY And SATBIR for Property Valuation of Agricultural and Plot Land.
	public String getMarketValueTotal(PropertyValuationDTO useDTO)
	{

		String marketValue = new String();
		double basevalue=0.0;
		String[] propertyTypeL1 = useDTO.getUsePlotId().split("~");
		String propertyTypeL1Id = null;
		String propertyTypeL2Id = null;
		 
		String[] propertyTypeL2=null ;
		if (useDTO.getCeilingTypeId()!= null ) {
			 propertyTypeL2 = useDTO.getCeilingTypeId().split("~");
			}
			
		
			System.out.println("Ceiling type id " +useDTO.getCeilingTypeId()!= null);
		
			if (propertyTypeL1 != null && propertyTypeL1.length == 3) {
				propertyTypeL1Id = propertyTypeL1[0];
			}
			
			System.out.println("Ceiling type id " +useDTO.getCeilingTypeId()!= null);
			
			if (propertyTypeL2 != null && propertyTypeL2.length == 3) {
				propertyTypeL2Id = propertyTypeL2[0];
			}
		int districtId = useDTO.getDistrictID();
		int tehsilId = useDTO.getTehsilID();
		int wardId = useDTO.getWardId();
		int mohallaId = useDTO.getMahallaId();
		int propertyId = useDTO.getPropertyTypeID();
		
		double returnValue=0.0;
		String unitFlag="H";   // flag for Hectare
		if(useDTO.getLandMeterId()!=null && useDTO.getLandMeterId().equalsIgnoreCase("1"))
		{
			unitFlag="S";  // flag For SQM
		}
		String sqmeterType = null;
		double totalSqlMeter = useDTO.getTotalSqMeter();
		
		String noTrees = useDTO.getHdnNoOfTree();

		System.out.println("No of Trees:-" + noTrees);

		

		String subclause = useDTO.getHdnExtSubClause();
		String subclauseId[]=subclause.split(",");
		if(propertyId==3)
		{
			if(propertyId==3){
			try {
				basevalue= new PropertyValuationDAO().getBaseValueAgri(String.valueOf(districtId), String.valueOf(tehsilId), String.valueOf(wardId), String.valueOf(mohallaId), String.valueOf(propertyId), propertyTypeL1Id, propertyTypeL2Id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		String noTreeId[]=noTrees.split(",");	
		basevalue=basevalue*totalSqlMeter;
		returnValue=basevalue;
		String tid="";
		for(int i=0;i<subclauseId.length;i++)
		{
			String sid=subclauseId[i];
			
			String treeFlag="NT";
			for(int j=0;j<noTreeId.length;j++)
			{
				String jid=noTreeId[j];
				if(jid.indexOf(sid+"-")!=-1)
				{
					tid=noTreeId[j];
					treeFlag="T";
				}
			}
			if(treeFlag.equalsIgnoreCase("T"))
			{
				String treeparam[]=tid.split("-");
				try {
					//updated on 06-10-2012 
					marketValue = new IGRSCommon().getPropertyValuationAgriPlot( basevalue,unitFlag,districtId,
							tehsilId, wardId, mohallaId, treeparam[0], totalSqlMeter,
							treeFlag, treeparam[1]);
					System.out.println(marketValue);
					returnValue=returnValue+Double.parseDouble(marketValue);

				} catch (Exception x) {
					System.out.println(x);
				}
			}
			else
			{
				try {
					//updated on 06-10-2012 
					marketValue = new IGRSCommon().getPropertyValuationAgriPlot( basevalue,unitFlag,districtId,
							tehsilId, wardId, mohallaId, sid, totalSqlMeter,
							treeFlag,"0");
					System.out.println(marketValue);
					returnValue=returnValue+Double.parseDouble(marketValue);
					
				} catch (Exception x) {
					System.out.println(x);
				}
			}
		
		}
		}
		else if(propertyId==1)  // for plot
		{
			 if(propertyId==1)
			{
				unitFlag="S";        // unit for area in plot is always SQM    
				try {
					basevalue= new PropertyValuationDAO().getBaseValuePlot(String.valueOf(districtId), String.valueOf(tehsilId), String.valueOf(wardId), String.valueOf(mohallaId), String.valueOf(propertyId), propertyTypeL1Id, propertyTypeL2Id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			 basevalue=basevalue*totalSqlMeter;
			 returnValue=basevalue;
			 for(int i=0;i<subclauseId.length;i++)
				{
					String sid=subclauseId[i];
					String treeFlag="NT";            // in Plot Trees are not Present hence flag= NT(NO TREE).
					try {
							//updated on 06-10-2012 
							marketValue = new IGRSCommon().getPropertyValuationAgriPlot( basevalue,unitFlag,districtId,
									tehsilId, wardId, mohallaId, sid, totalSqlMeter,
									treeFlag,"0");    //"0" indicates no Tree Present
							System.out.println(marketValue);
							returnValue=returnValue+Double.parseDouble(marketValue);

						} catch (Exception x) {
							System.out.println(x);
						}
					}
				
				}
		
		return String.valueOf(returnValue);
	
	}
	public String[] getMarketValue(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];
		int districtId = useDTO.getDistrictID();
		int tehsilId = useDTO.getTehsilID();
		int wardId = useDTO.getWardId();
		int mohallaId = useDTO.getMahallaId();
		int propertyId = useDTO.getPropertyTypeID();
	
		String[] propertyTypeL1 = useDTO.getUsePlotId().split("~");

		String propertyTypeL2Id = null;
		 
		String[] propertyTypeL2=null ;
	
		String sqmeterType = null;
		double totalSqlMeter = useDTO.getTotalSqMeter();
		double baseValue = 0.0;
		
		if(useDTO.getConsiderAmt()!=null && !useDTO.getConsiderAmt().equalsIgnoreCase("")){
			baseValue=Double.parseDouble(useDTO.getConsiderAmt());
		}
		
		String noTrees = useDTO.getHdnNoOfTree();

		System.out.println("No of Trees:-" + noTrees);


		String subclause = useDTO.getHdnExtSubClause();
		String propertyTypeL1Id = "";
		System.out.println("Ceiling type id ");
		System.out.println("Ceiling type id " + useDTO.getCeilingTypeId());

		if (useDTO.getCeilingTypeId()!= null ) {
		 propertyTypeL2 = useDTO.getCeilingTypeId().split("~");
		}
		
		System.out.println("Ceiling type id " +useDTO.getCeilingTypeId()!= null);
	
		if (propertyTypeL1 != null && propertyTypeL1.length == 3) {
			propertyTypeL1Id = propertyTypeL1[0];
		}
		
		System.out.println("Ceiling type id " +useDTO.getCeilingTypeId()!= null);
		
		if (propertyTypeL2 != null && propertyTypeL2.length == 3) {
			propertyTypeL2Id = propertyTypeL2[0];
		}
		
		
		try {
			//updated on 06-10-2012 
			System.out.println("@@@@45454545@@@@");
			System.out.println("Checking market value");
			marketValue = new IGRSCommon().getPropertyValuation( districtId,
					tehsilId, wardId, mohallaId, propertyId, propertyTypeL1Id,
					propertyTypeL2Id, subclause, baseValue, sqmeterType,
					totalSqlMeter, noTrees);
			System.out.println(marketValue);
			

		} catch (Exception x) {
			System.out.println(x);
		}
		 if(marketValue !=null) {
			 if(marketValue[0] == null || "0".equals(marketValue[0])) {
				 marketValue[0] = Double.toString(baseValue);
				 System.out.println("marketValue:-"+marketValue[0]);
				 System.out.println("exception :-"+marketValue[1]);
				 System.out.println("exception :-"+marketValue[2]);
				 
			 }
		 }
		System.out.println("marketValue:-"+marketValue+" baseValue:-"+baseValue);
		return marketValue;
	}

	public String[] getFloorWiseMarketValue(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];
		int districtId = useDTO.getDistrictID();
		int tehsilId = useDTO.getTehsilID();
		int wardId = useDTO.getWardId();
		int mohallaId = useDTO.getMahallaId();
		int propertyId = useDTO.getPropertyTypeID();
		int floorCalcId=useDTO.getFloorCalcTypeID();
		String[] propertyTypeL1 = useDTO.getUsePlotId().split("~");
		String[] propertyTypeL2 = useDTO.getCeilingTypeId().split("~");
		System.out.println("useDTO.getFloorID() BO:-" + useDTO.getTypeFloorID());
		String[] floorType = useDTO.getTypeFloorID().split("~");
		System.out.println("useDTO.getFloorID() BO:-" + useDTO.getTypeFloorID());

		String propertyTypeL2Id = null;
		String floorTypeId = null;
		String sqmeterType = null;
		double totalSqlMeter = useDTO.getTotalSqMeter();
		double consturctedArea = useDTO.getConstructedArea();

		String noTrees =useDTO.getHdnNoOfTree();

		System.out.println("No of Trees:-" + noTrees);

		
		String subclause = useDTO.getHdnExtSubClause();
		System.out.println("useDTO.getHdnExtSubClause() BO:-" + useDTO.getHdnExtSubClause());
		String propertyTypeL1Id = "";

	
		
		if (propertyTypeL1 != null && propertyTypeL1.length == 3) {
			propertyTypeL1Id = propertyTypeL1[0];
		}
		if (propertyTypeL2 != null && propertyTypeL2.length == 3) {
			propertyTypeL2Id = propertyTypeL2[0];
		}
		if (floorType != null && floorType.length == 3) {
			floorTypeId = floorType[0];
		}

		double baseValue = 0.0;
		
		if(useDTO.getConsiderAmt()!=null && !useDTO.getConsiderAmt().equalsIgnoreCase("")){
			baseValue = Double.parseDouble(useDTO.getConsiderAmt());
		}

		try {
			marketValue = new IGRSCommon().getFloorwisePropertyValuation(
					 districtId, tehsilId, wardId, mohallaId, propertyId,
					propertyTypeL1Id, propertyTypeL2Id, floorTypeId,subclause,
					baseValue, floorCalcId, totalSqlMeter, consturctedArea,
					noTrees);

		} catch (Exception x) {
			System.out.println(x);
		}
		if(marketValue !=null) {
			 if(marketValue[0] == null || "0".equals(marketValue[0])) {
				 marketValue[0] = Double.toString(baseValue);
			 }
		 }
		if(subclause.indexOf("200")!=-1||subclause.indexOf("201")!=-1||subclause.indexOf("202")!=-1||subclause.indexOf("203")!=-1)
		{
			if(totalSqlMeter<=35.0)
			{
			marketValue[0] = Double.toString(Double.parseDouble(marketValue[0])/2);
		
			}
		}
		if(subclause.indexOf("181")!=-1||subclause.indexOf("182")!=-1||subclause.indexOf("183")!=-1)
		{
			marketValue[0] = Double.toString(Double.parseDouble(marketValue[0])*0.85);
		}
		
		return marketValue;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getDeed(String Property) {
		ArrayList list = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			
			ArrayList retList = common.getDeedType(Property);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					DutyCalculationDTO dto = new DutyCalculationDTO();
					dto.setDeedId(Integer.parseInt(lst.get(0).toString())); 
					dto.setDeedType(lst.get(1).toString());
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	/**
	 * @param deedType
	 * @return ArrayList
	 */
	public ArrayList getInstrument(int deedType,String languageLocale) {
		ArrayList list = new ArrayList();
		try {

			int[] deedId = new int[1];
			deedId[0] = deedType;
			//deedId[1] = CommonConstant.INSTRUMENT_STATUS;
			ArrayList retList = new IGRSCommon().getInstruments(deedId);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					InstrumentsDTO dto = new InstrumentsDTO();
					dto.setInstId(Integer.parseInt(lst.get(0).toString()));
					dto.setInstType(lst.get(1).toString());
					//dto.setLabelDisplay(lst.get(2).toString());
					//dto.setLabelAmountFlag(lst.get(3).toString());
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					dto.setHdnAmount(lst.get(0).toString()
							+"~"+lst.get(1).toString()+"~"+lst.get(2).toString()+"~"+lst.get(3).toString());
					}else{
						dto.setHdnAmount(lst.get(0).toString()
								+"~"+lst.get(1).toString()+"~"+lst.get(4).toString()+"~"+lst.get(3).toString());
					}
					

					System.out.println(dto.getHdnAmount());
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		
		return list;
	}
	/**
	 * @param deedType
	 * @return ArrayList
	 */
	public ArrayList getFloorCalcType(PropertyValuationDTO useDTO,String languageLocale) {
		ArrayList list = new ArrayList();
		try {

			String propertyTypeL1Id[] = useDTO.getUsePlotId().split("~");
			//deedId[1] = CommonConstant.INSTRUMENT_STATUS;
			ArrayList retList = new IGRSCommon().getFloorCalcType(propertyTypeL1Id[0]);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					FloorCalcTypeDTO dto = new FloorCalcTypeDTO();
					dto.setFloorCalcId(Integer.parseInt(lst.get(0).toString()));
					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setFloorCalcType(lst.get(1).toString());
					//dto.setLabelDisplay(lst.get(2).toString());
					//dto.setLabelAmountFlag(lst.get(3).toString());
					dto.setHdnfloorCalcType(lst.get(0).toString()
							+"~"+lst.get(1).toString());
					}else{
						dto.setFloorCalcType(lst.get(2).toString());
						//dto.setLabelDisplay(lst.get(2).toString());
						//dto.setLabelAmountFlag(lst.get(3).toString());
						dto.setHdnfloorCalcType(lst.get(0).toString()
								+"~"+lst.get(2).toString());
					}

					System.out.println(dto.getHdnfloorCalcType());
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		
		return list;
	}

	/**
	 * 
	 * @param deedType
	 * @param instId
	 * @return ArrayList
	 */
	public ArrayList getExemptions(int deedType, int instId,String languageLocale) {
		ArrayList list = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			 DBUtility	 dbUtil = new DBUtility();
			 ArrayList retList=new ArrayList();
			 String exemp=null ;
			 String exempLevel = common.getExemptionsLevel(deedType);
			 // commented by Lavi on 04th October 2013.
			 
			 /*String exempLevel = "SELECT exemption_level FROM IGRS_DEED_TYPE_MASTER "
				+"WHERE DEED_TYPE_ID="+deedType+"";*/
			  
			 logger.debug("Inside exempLevel " + deedType);
			  
				try {
					// commented by Lavi on 04th October 2013.
					
					/*dbUtil = new DBUtility();
					logger.debug("Inside exempLevel " + exempLevel);
					dbUtil.createStatement();
					exemp = dbUtil.executeQry(exempLevel);*/
					//logger.debug("Inside exempLevel AND exemp IS " + exemp);
					
					// end of commented code by Lavi on 04th October 2013.
					exemp = exempLevel;
					logger.debug("Inside exempLevel AND exemp IS " + exemp);
					
				} catch (Exception x) {
					logger.debug(x);
				} 
			 
				
				if(exemp.equalsIgnoreCase("D"))
			 	{
					System.out.println("INSIDE IF");
			 		int[] deedId = new int[1];
					deedId[0] = deedType;
					 retList = common.getInstLevelExemptions(deedId);
					if (retList != null) {
						for (int i = 0; i < retList.size(); i++) {
							ArrayList lst = (ArrayList) retList.get(i);
							ExemptionDTO dto = new ExemptionDTO();
							dto.setExemId(lst.get(0).toString());
							dto.setExemType(lst.get(1).toString());
							dto.setExemDeedId(lst.get(0).toString() + "~"
									+ lst.get(1).toString());
							System.out.println("###" + lst.get(1).toString());
							list.add(dto);
						}
					
			 	}
			 	}else
			 	{
			 		System.out.println("INSIDE ELSEIF");
			 		int[] deedId1 = new int[2];
					deedId1[0] = deedType;
					deedId1[1] = instId;	
					 retList = common.getExemptions(deedId1);
					if (retList != null) {
						for (int i = 0; i < retList.size(); i++) {
							ArrayList lst = (ArrayList) retList.get(i);
							ExemptionDTO dto = new ExemptionDTO();
							dto.setExemId(lst.get(0).toString());
							dto.setExemType(lst.get(1).toString());
							dto.setExemDeedId(lst.get(0).toString() + "~"
									+ lst.get(1).toString());
							System.out.println("###" + lst.get(1).toString());
							list.add(dto);
						}
			 	}
			 
			 	
			 	}
		} catch (Exception x) {
			System.out.println(x);
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
	public double getDutyCalculation(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO,
			PropertyValuationDTO propertyDTO) {
		double calculatedDuty = 0.0;
		try {
			IGRSCommon common = new IGRSCommon();
		
			System.out.println("@@@@1545@@@@" + instDTO.getInstId() + ":"
					+ dutyDTO.getDeedId());
		
				int instId =instDTO.getInstId();

			int deedId = dutyDTO.getDeedId();
			double dutyPaid=dutyDTO.getDutyAlreadyPaid();
			double annualRent=0.0;
			String exemption = exeDTO.getHdnExAmount();
			System.out.println("Exemption Type:-" + exemption);
			double marketValue = propertyDTO.getMarketValue();
			double baseValue = 0.0;
			
			if(propertyDTO.getConsiderAmt()!=null && !propertyDTO.getConsiderAmt().equalsIgnoreCase("")){
				baseValue = Double.parseDouble(propertyDTO.getConsiderAmt());
			}
			
			double shareVaue=dutyDTO.getShareValue();
			System.out.println("marketValue:-" + marketValue);
			System.out.println("baseValue:-" + baseValue);
			System.out.println("Duty Already Paid : "+dutyPaid);
			System.out.println("Share Value : "+shareVaue);
			dutyDTO.setBaseValue(Double.toString(marketValue) );
		
			if (instDTO.getLabelAmountFlag().equals(CommonConstant.REGISTRATION_NO_MARKET_VALUE))
			{
				marketValue=baseValue;
			}
				
				
				calculatedDuty = common.getStampDuty(deedId, instId, exemption,
						marketValue,annualRent, dutyPaid);
			
			
				
		//For Conveyance deed --Share value instrument	
		if(instDTO.getLabelAmountFlag().equals(CommonConstant.SHARE_VALUE)&& exeDTO.getHdnExAmount().equals("")){
			baseValue=(shareVaue+baseValue)*(0.007);
			if (baseValue>calculatedDuty){
				calculatedDuty=baseValue;
				System.out.println("Share Condition Duty :-" + calculatedDuty);
			}
		}

			System.out.println("Duty :-" + calculatedDuty);
		} catch (Exception x) {
			System.out.println(x);
		}

		return calculatedDuty;
	}

	public double[] getMunicipalDutyCalculation(DutyCalculationDTO dutyDTO,InstrumentsDTO instDTO, ExemptionDTO exeDTO,
			PropertyValuationDTO propertyDTO)
	{	
		double calculatedDuty[]=new double[3];
		try {
	
		String[] propertyTypeL1 = propertyDTO.getUsePlotId().split("~");
		int areaTypeId= propertyDTO.getAreaId();
		String propertyTypeL1Id = "";
	
		int munBodyId=propertyDTO.getMunicipalBodyID();
		
		if (propertyTypeL1 != null && propertyTypeL1.length == 3) {
			propertyTypeL1Id = propertyTypeL1[0];
		}
		
		
		
		System.out.println("munBody ID :-"+munBodyId);
		
		IGRSCommon common = new IGRSCommon();
		
			System.out.println("@@@@1545@@@@" + instDTO.getInstId() + ":"
					+ dutyDTO.getDeedId());
			//if (instDTO.getHdnAmount()!=null){
		//	String[] inst = instDTO.getHdnAmount().split("~");
				int instId =instDTO.getInstId();
	
		
	
		int deedId =dutyDTO.getDeedId();
		String marketVal="";
		double stampDuty=dutyDTO.getStampDuty();
		System.out.println("@@@@@@@STAMP DUTY @@@@@@@"+stampDuty);
	//	if (inst != null && inst.length == 2) {
		//	System.out.println("Instrument type:-" + instId + ":" + inst[1]);
			//instId = inst[0];
		//}


		String exemption = exeDTO.getHdnExAmount();
		System.out.println("Exemption Type:-" + exemption);
		double marketValue = propertyDTO.getMarketValue();
		double baseValue = propertyDTO.getBiggestValue();
		System.out.println("marketValue:-" + marketValue);
		System.out.println("baseValue:-" + baseValue);
		
		System.out.println(" CALLING MUNICIPAL BODY FUNCTIO0N FOR CALCULATING THE DATA");

		
			calculatedDuty = common.getMuncipalCalculatedDuty(deedId,instId,munBodyId,propertyTypeL1Id,
					marketValue,stampDuty);

		
		System.out.println("Duty :-" + calculatedDuty);
	} catch (Exception x) {
		System.out.println(x);
	}
	return calculatedDuty;
	}
	
	/**
	 * 
	 * @param dutyDTO
	 * @param instDTO
	 * @param exeDTO
	 * @return String[]
	 */
	
	
	public double getRegistrationFee(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO) {
		double regFee=0.0 ;
		try {
			IGRSCommon common = new IGRSCommon();
			System.out.println("@@@@@@@@" + instDTO.getHdnAmount() + ":"
					+ dutyDTO.getDeedId());
			int instId = instDTO.getInstId();
			int deedId = dutyDTO.getDeedId();
			String exemption = exeDTO.getHdnExAmount();
			System.out.println("Exemption Type:-" + exemption);
			//double baseValue = dutyDTO.getBaseValue();
			
			
			double baseValue=0.0;
			
			if(dutyDTO.getBaseValue()!=null && !dutyDTO.getBaseValue().equalsIgnoreCase("")){
				baseValue=Double.parseDouble(dutyDTO.getBaseValue());
				
			}
			
			
			
			
			System.out.println("baseValue:-" + baseValue);
			double duty =dutyDTO.getStampDuty();
			System.out.println("STAM DUTY:-" + duty);
				regFee = common.getRegistrstionFee(deedId, instId, null,duty,
						baseValue);
				
				System.out.println("Reg Calculated : "+regFee);
				
				if(instDTO.getLabelAmountFlag()!=null){
		     		System.out.println("Reg flag"+instDTO.getLabelAmountFlag());
		     	if(instDTO.getLabelAmountFlag().equals(CommonConstant.REG_ALREADY_PAID))	{
					double regPaid=dutyDTO.getRegPaid();
					System.out.println("Reg Already Paid : "+regPaid);
					regFee=regFee-regPaid;
					System.out.println("Reg Calculated after  Paid : "+regFee);
					
					}
		     	}
			
			System.out.println("Registration Fee :-" + regFee);
		} catch (Exception x) {
			System.out.println(x);
		}
		 
		return regFee;
	}
	/*
	public double getRegistrationFee(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO,
			PropertyValuationDTO propertyDTO) {
	double regFee =0.0;
		try {
			IGRSCommon common = new IGRSCommon();
			System.out.println("@@@@1545@@@@" + instDTO.getInstId() + ":"
					+ dutyDTO.getDeedId());
			//if (instDTO.getHdnAmount()!=null){
		//	String[] inst = instDTO.getHdnAmount().split("~");
				int instId =instDTO.getInstId();
	
		
	
		int deedId =dutyDTO.getDeedId();
		
		

			String exemption = exeDTO.getHdnExAmount();
			System.out.println("Exemption Type:-" + exemption);
			double marketValue = propertyDTO.getMarketValue();
			double baseValue = propertyDTO.getConsiderAmt();
			System.out.println("marketValue:-" + marketValue);
			System.out.println("baseValue:-" + baseValue);
			

			if (marketValue > baseValue || marketValue == baseValue) {
				regFee = common.getRegistrstionFee(deedId, instId, null,
						marketValue);

			} else if (baseValue > marketValue) {
				regFee = common.getRegistrstionFee(deedId, instId, null,
						baseValue);
			}

			System.out.println("Registration Fee :-" + regFee);
		} catch (Exception x) {
			System.out.println(x);
		}
		 
		return regFee;
	}
*/
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

	

		Iterator I = map.entrySet().iterator();
		System.out.println("I.hasNext():-" + I.hasNext() + ":" + subclause);

		while (I.hasNext()) {

			ArrayList subclauseListmap = new ArrayList();

			Map.Entry me = (Map.Entry) I.next();
			System.out.println("getKey :-" + me.getKey());
			PropertyValuationDTO hDTO = (PropertyValuationDTO) me.getValue();
			System.out.println("FloorID:-" + hDTO.getFloorID());
			System.out.println("Floor Subclause:-" + hDTO.getHdnSubClauseName());

			String key = "";
			if (hDTO != null) {
				key = hDTO.getTypeFloorDesc() + " - "+ hDTO.getUsageBuilding() + " - "+ hDTO.getCeilingType() ;
			}

			String[] subclauseary = hDTO.getHdnSubClauseName().split("~,");
			
			if(subclauseary!=null)
	     	{
				subclauseary[subclauseary.length-1]=subclauseary[subclauseary.length-1].replace("~","");
	     	}
			
			for (int i = 0; i < subclauseary.length; i++) {
				PropertyValuationDTO dto = new PropertyValuationDTO();
				System.out.println("subclause:-" + subclauseary[i]);
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

		int district = useDTO.getDistrictID();
	
		int tehsil = useDTO.getTehsilID();
	
		int ward = useDTO.getWardId() ;
		int mohalla = useDTO.getMahallaId();
	
		int property = useDTO.getPropertyTypeID();
	
		int muncipal = useDTO.getMunicipalBodyID();
	
	int area = useDTO.getAreaId();
		
		String[] usePlot = useDTO.getUsePlotId() == null ? null : useDTO
				.getUsePlotId().split("~");

		if (usePlot != null && usePlot.length == 3) {
			useDTO.setUsePlot(usePlot[1]);
			System.out.println(usePlot[1]);
		}
		IGRSCommon common = new IGRSCommon();
		// if (useDTO.getTotalSqMeter() != null) {
		useDTO.setTotalSqMeter(Double.parseDouble(common
				.getCurrencyFormat(useDTO.getTotalSqMeter())));
		// }
		// if(useDTO.getConsiderAmt() != null) {
		//useDTO.setConsiderAmt(Double.parseDouble(common
		//		.getCurrencyFormat(useDTO.getConsiderAmt())));
		// }
		useDTO.setMktValue(common.getCurrencyFormat(useDTO.getMarketValue()));
		/*
		 * String[] subclause = useDTO.getHdnSubClauseName().split(",");
		 * if(subclause != null && subclause.length ==2) {
		 * useDTO.setSubClause(subclause[1]); }
		 */

		return useDTO;
	}

	public String[] getMunicipalDuty(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];
		//String districtId =useDTO.getDistrictID();;
		int tehsil = useDTO.getTehsilID();
		
		int ward = useDTO.getWardId() ;
		int mohalla = useDTO.getMahallaId();
	
		int property = useDTO.getPropertyTypeID();
	
		int muncipal = useDTO.getMunicipalBodyID();
	

		int district =useDTO.getDistrictID();




		double baseValue = useDTO.getMarketValue();
		System.out.println("baseValue :-" + baseValue);
		try {
			marketValue = new IGRSCommon().getMunicipalDuty( district, tehsil,
					ward, mohalla, baseValue);

		} catch (Exception x) {
			System.out.println(x);
		}
		return marketValue;
	}

	// Imran's code
	public String[] getPresentMarketValue(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];

		int district = useDTO.getDistrictID();
		int tehsil = useDTO.getTehsilID();
		int ward = useDTO.getWardId();
		int mohalla = useDTO.getMahallaId();
		int property = useDTO.getPropertyTypeID();
		String[] propertyTypeL1Id = useDTO.getUsePlotId().split("~");

		String propertyTypeL2Id = null;
		String sqmeterType = null;
		double totalSqlMeter = useDTO.getTotalSqMeter();
		String noTrees = useDTO.getHdnNoOfTree();
		System.out.println("No of Trees:-" + noTrees);
		String subclause = getSubstringString(useDTO.getHdnExtSubClause());

		double baseValue = 0.0;
		
		if(useDTO.getConsiderAmt()!=null && !useDTO.getConsiderAmt().equalsIgnoreCase("")){
			baseValue = Double.parseDouble(useDTO.getConsiderAmt());
		}

		try {
			marketValue = new IGRSCommon().getPropertyValuation( district,
					tehsil, ward, mohalla, property,
					propertyTypeL1Id[0], propertyTypeL2Id, subclause,
					baseValue, sqmeterType, totalSqlMeter, noTrees);
			System.out.println("marketValue:-"+marketValue[0]
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
			System.out.println(x);
		}
		return marketValue;
	}

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

	public String[] getBuildingMarketValue(PropertyValuationDTO useDTO) {
		String[] marketValue = new String[3];

		int district = useDTO.getDistrictID();
		int tehsil = useDTO.getTehsilID();
		int ward = useDTO.getWardId();
		int mohalla = useDTO.getMahallaId();
		int property = useDTO.getPropertyTypeID();
		String propertyTypeL1Id[] = useDTO.getUsePlotId().split("~");
		String floorId = "'2,3'";// useDTO.getFloorID();
		String propertyTypeL2Id = useDTO.getHdnPropertyType();
		String sqmeterType = null;
		String floorSubClauseId = "'5,9;4,6'";// useDTO.getHdnExtSubClause();
		double totalSqlMeter = useDTO.getTotalSqMeter();

		String noTrees = "1";// useDTO.getHdnNoOfTree();

		System.out.println("No of Trees:-" + noTrees);

		String subclause = "'5,9;4,6'";// getSubstringString(useDTO.getHdnExtSubClause());

		double baseValue = 0.0;
		
		if(useDTO.getConsiderAmt()!=null && !useDTO.getConsiderAmt().equalsIgnoreCase("")){
			baseValue = Double.parseDouble(useDTO.getConsiderAmt());
		}
		
		try {
			marketValue = new IGRSCommon().getFloorPropertyValuation(
					 district, tehsil, ward, mohalla, property,
					propertyTypeL1Id[0], propertyTypeL2Id, subclause, floorId,floorSubClauseId ,baseValue, totalSqlMeter, noTrees);

		} catch (Exception x) {
			System.out.println(x);
		}
		return marketValue;
	}

	public void captureUserDetails(PropertyValuationDTO useDTO) {
		System.out.println("i am in captureUserDetails");
	int districtId = useDTO.getDistrictID();
		int tehsilId = useDTO.getTehsilID();
		int wardId = useDTO.getWardId();
		int mohallaId = useDTO.getMahallaId();
		int propertyId = useDTO.getPropertyTypeID();
		String propertyTypeL1Id[] = useDTO.getUsePlotId().split("~");
		int areaId=useDTO.getAreaId();
		int govMunBodyId=useDTO.getMunicipalBodyID();
		String propertyTypeL2Id =null;
		
		String[] ceilingType = useDTO.getCeilingTypeId() == null ? null
				: useDTO.getCeilingTypeId().split("~");
		if (ceilingType != null && ceilingType.length == 3) {
						propertyTypeL2Id = ceilingType[0];
		}
		String sqmeterType = useDTO.getLandMeterId();
		double totalSqlMeter = useDTO.getTotalSqMeter();
		String noTrees = useDTO.getHdnNoOfTree();
		System.out.println("No of Trees:-" + noTrees);
		int district =useDTO.getDistrictID();
		//String subclause = useDTO.getHdnExtSubClause();
		String fname=useDTO.getFirstName();
		String mname=useDTO.getMiddleName();
		String lname=useDTO.getLastName();
		String gender=useDTO.getChkSex();
		String UserId=useDTO.getUserId();
		String setSystemMVDisplay=useDTO.getSystemMVDisplay();
		System.out.println(" UserId:-" + UserId);
		String dateofbirth=null;
		if(useDTO.getDobDay()!=null)
		{
			System.out.println("inside first == dateofbirth "+dateofbirth);
			dateofbirth=useDTO.getDobDay()+"-"+useDTO.getDobMonth()+"-"+useDTO.getDobYear();;
		}
		
		Double marketValue =useDTO.getMarketValue();
		System.out.println("dateofbirth "+dateofbirth);
		double baseValue = 0.0;
		
		if(useDTO.getConsiderAmt()!=null && !useDTO.getConsiderAmt().equalsIgnoreCase("")){
			baseValue = Double.parseDouble(useDTO.getConsiderAmt());
		}

		try {
			//updated on 06-10-2012 
			System.out.println("@@@@45454545@@@@");
			System.out.println("Checking market value");
	String valuationId=	new IGRSCommon().captureUserDetails(fname,mname,lname,gender,dateofbirth,baseValue, district,
					tehsilId,areaId, wardId, mohallaId,govMunBodyId, propertyId, propertyTypeL1Id[0],
					propertyTypeL2Id, marketValue, sqmeterType,
					totalSqlMeter, noTrees,UserId,setSystemMVDisplay);
		    useDTO.setValuationId(valuationId);
			System.out.println(marketValue);
			System.out.println(" valuation id"+valuationId);
			
			

		} catch (Exception x) {
			System.out.println(x);
		}
		 
	
		
	}
	
	public void subClauseDetails(PropertyValuationDTO useDTO) {

			
			try {
				//updated on 06-10-2012 
				System.out.println("NSUB CLAUSE DETAILS");
				String noTrees;
				String subclause =null;
				String ncomp =null;
				String valuationId=useDTO.getValuationId();
				logger.debug("Valuation Id" + valuationId);
				System.out.println("No of Trees:-" +useDTO.getHdnNoOfTree());
				//if(useDTO.getHdnNoOfTree()!=null){
				noTrees = useDTO.getHdnNoOfTree();
				System.out.println("No of Trees:-" + noTrees);
				//}
				System.out.println("No of Trees:-");
				if (useDTO.getHdnNoOfTree()==null){
					noTrees="";
				}				
			
					subclause = useDTO.getHdnExtSubClause();
					logger.debug("Subclause id:-" + subclause);
			
				logger.debug("After Subclause");
				if (!noTrees.equalsIgnoreCase("")){
					logger.debug("INSIDE  NO OF TREE");
				String[] subClauseArray = subclause.split(",");
				String[] notreeArray = noTrees.split(",");
				int treeArraySize=(notreeArray.length)*2;
				logger.debug("treeArraySize:-" + treeArraySize);
				String[] treeArray=new String[treeArraySize];

				for(int i=0;i<notreeArray.length;i++)
				{
					 String tmpArr[] = notreeArray[i].split("-");	
					 treeArray[2*i] = tmpArr[0];
					 treeArray[(2*i)+1] = tmpArr[1];
				}
				String floorCalculation = Integer.toString(useDTO.getFloorCalculationID());
				logger.debug("No of Trees:-" + treeArray);
				logger.debug("Valuation Id" + valuationId);
				logger.debug("Subclause id:-" + subclause);
				logger.debug("Floor id:-" + floorCalculation);
				for(int i=0;i<subClauseArray.length;i++)
				{
					noTrees=null;
					for(int j=0;j<treeArray.length;j=j+2)
					{		logger.debug("treeArray length:-"+treeArray.length );
						logger.debug("subClauseArray[i] :"+i+" :"+subClauseArray[i]);
						logger.debug("INSIDE  TREE ARRAY SIZE");
						logger.debug("treeArray[j] :"+j+" :"+treeArray[j]);
						logger.debug("condition" + treeArray[j].equalsIgnoreCase(subClauseArray[i]));
					if (treeArray[j].equalsIgnoreCase(subClauseArray[i])){
						subclause=subClauseArray[i];
						noTrees=treeArray[j+1];
						logger.debug("treeArray[j+1]" + noTrees);
					logger.debug("No of Trees:-" + noTrees);
					logger.debug("Valuation Id" + valuationId);
					logger.debug("Subclause id:-" + subclause);
					logger.debug("Floor id:-" + floorCalculation);
					}else
						{	
							subclause=subClauseArray[i];
						}
					}
					new IGRSCommon().subClauseDetails(valuationId,subclause,noTrees,floorCalculation);
				}
				}else if ((!subclause.equalsIgnoreCase(""))&&noTrees.equalsIgnoreCase("")){
					logger.debug("INSIDE  SUBCLAUSE ");
					logger.debug("Subclause id:-" + subclause);
					String[] subClauseArray = subclause.split(",");
					String floorCalculation = Integer.toString(useDTO.getFloorCalculationID());
			     	logger.debug("Valuation Id" + valuationId);
					logger.debug("Subclause id:-" + subclause);
					for(int i=0;i<subClauseArray.length;i++)
					{
						
							subclause=subClauseArray[i];
							noTrees=null;
							
						logger.debug("No of Trees:-" + noTrees);
						logger.debug("Valuation Id" + valuationId);
						logger.debug("Subclause id:-" + subclause);
						logger.debug("Floor id:-" + floorCalculation);
						new IGRSCommon().subClauseDetails(valuationId,subclause,noTrees,floorCalculation);
					}
				}		
				}
			 catch (Exception x) {
				logger.debug(x);
			 }
			 
		
			
		}
	
	public void captureExchangeValuation(PropertyValuationDTO useDTO) {

			Double marketValue =useDTO.getMarketValue();

			try {
				//updated on 06-10-2012 
				logger.debug("@@@@45454545@@@@");
				logger.debug("Checking market value");
		String valuationId=	new IGRSCommon().captureExchangeValuation( marketValue);
			    useDTO.setValuationId(valuationId);
				logger.debug(marketValue);
				logger.debug(" valuation id"+valuationId);
				
				

			} catch (Exception x) {
				logger.debug(x);
			}
			 
		
			
		}
	
	

	
	public void captureStampDetails(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO,PropertyValuationDTO useDTO) {
		
		    String valuationId = useDTO.getValuationId();
			double stampDuty = dutyDTO.getStampDuty();
			double gramDuty = useDTO.getPanchayatDuty();
			double nagarDuty = useDTO.getNagarPalikaDuty();
			double upkar = useDTO.getUpkarDuty();
			double regFee = useDTO.getRegistrationFee();
			double marketValue =useDTO.getMarketValue();
			double total =useDTO.getTotal();
			int deedId=dutyDTO.getDeedId();
			int instId=instDTO.getInstId();
			String exempId=exeDTO.getHdnExAmount();
			logger.debug("exeDTO.getHdnExAmount()  IS"+exeDTO.getHdnExAmount());
			double regPaid=dutyDTO.getRegPaid();
			double dutyPaid=dutyDTO.getDutyAlreadyPaid();
			double shareValue=dutyDTO.getShareValue();
			String userId=useDTO.getUserId();
			try {
				//updated on 06-10-2012 
				logger.debug("@@@@45454545@@@@");
				logger.debug("Checking market value");
		String dutyId=	new IGRSCommon().captureStampDetails(valuationId,stampDuty,gramDuty,nagarDuty,upkar,regFee,marketValue,total,deedId,instId,
				exempId,regPaid,dutyPaid,shareValue,userId);
			    useDTO.setStampId(dutyId);
				logger.debug(marketValue);
				logger.debug(" valuation id"+dutyId);

			} catch (Exception x) {
				logger.debug(x);
			}
			 
		
			
		}
	
	public void updateUserDetails(String id,String finalValuationId,String partyLabel) {

		
		try {
			//updated on 06-10-2012 
			logger.debug("@@@@New Id is@@@@"+id);
			logger.debug("final ID IS"+finalValuationId);
			logger.debug("Party ID IS"+partyLabel);
			new IGRSCommon().updateUserDetails(id,finalValuationId,partyLabel);
			

		} catch (Exception x) {
			logger.debug(x);
		}

	}
	
	public void captureFloorDetails(HashMap hmap,PropertyValuationDTO propdto) {
		
		Iterator I = hmap.entrySet().iterator();
		try {
		while (I.hasNext()) {
			Map.Entry me = (Map.Entry) I.next();
			logger.debug(" map size"+hmap.size());
			PropertyValuationDTO useDTO = (PropertyValuationDTO) me
					.getValue();
		
			String propertyTypeL1Id[] = useDTO.getUsePlotId().split("~");
			logger.debug(" map size again 8888833"+hmap.size());
			String propertyTypeL2Id[] =useDTO.getCeilingTypeId().split("~");
			logger.debug(" map size again 33"+hmap.size());
			double totalSqlMeter = useDTO.getTotalSqMeter();
			double consSqlMeter = useDTO.getConstructedArea();
			logger.debug(" map size again44"+hmap.size());
			String noTrees = useDTO.getHdnNoOfTree();
			//String subclause = useDTO.getHdnExtSubClause();
			logger.debug(" map size2222 again44"+hmap.size());
			double marketValue =useDTO.getMarketValue();
			logger.debug(" marketValue "+marketValue);
			double baseValue = 0.0;
			
			if(useDTO.getConsiderAmt()!=null && !useDTO.getConsiderAmt().equalsIgnoreCase("")){
				baseValue = Double.parseDouble(useDTO.getConsiderAmt());
			}
			
			
			logger.debug(" baseValue "+baseValue);
			String floorId[] = useDTO.getTypeFloorID().split("~");
			String valuationId=	 propdto.getValuationId();
			logger.debug("floorId "+floorId);
			int floorCalcId=propdto.getFloorCalcTypeID();
			useDTO.setValuationId(valuationId);
			String userId=propdto.getUserId();
			String setSystemMVDisplay=useDTO.getSystemMVDisplay();
			logger.debug(" valuationId"+valuationId);
			logger.debug(" map size77772222 again44"+hmap.size());
		    String floorCalculation =	new IGRSCommon().captureFloorDetails(valuationId,floorCalcId,floorId[0],baseValue,propertyTypeL1Id[0],
						propertyTypeL2Id[0], marketValue,totalSqlMeter, noTrees,consSqlMeter,userId,setSystemMVDisplay);
				useDTO.setFloorCalculationID(Integer.parseInt(floorCalculation));
				if(useDTO.getHdnExtSubClause()!=null){
				subClauseDetails(useDTO);
				}
				logger.debug("floor calculation id "+floorCalculation);
			   	logger.debug(marketValue);
				logger.debug(" valuation id"+valuationId);
							}
		
		} catch (Exception x) {
			logger.debug(x);

		}
	
}
public String convertDOB(String date) throws Exception {
		
		//String sysdate=commonBd.getCurrDateTime();
		
		//String transDate1=paymentBD.gettranDate(eForm);
        SimpleDateFormat date1 = new SimpleDateFormat ("dd/MMM/yyyy");
       // SimpleDateFormat date2 = new SimpleDateFormat ("dd/MMM/yyyy");
        SimpleDateFormat date2 = new SimpleDateFormat ("dd/MM/yyyy");
        Date d1 = date1.parse(date);
        String formatDate = date2.format(d1);

		System.out.println("formatted date=----->"+formatDate);
		
		
		
		return formatDate;
					
		
	}
}