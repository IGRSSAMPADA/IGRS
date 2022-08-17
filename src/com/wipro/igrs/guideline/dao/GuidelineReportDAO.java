package com.wipro.igrs.guideline.dao;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.itextpdf.text.log.SysoLogger;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DBUtilityTest;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;
import com.wipro.igrs.guideline.sql.CommonSQL;


public class GuidelineReportDAO {

	
	DBUtility dbUtility = null;
	GuideLineViewDAO gDAO = new GuideLineViewDAO();
	private Logger logger = Logger.getLogger(GuidelineReportDAO.class);
	
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList getFinancialYear() throws Exception
	{
		ArrayList financialYearList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = CommonSQL.FINANCIAL_VIEW;
			financialYearList = dbUtility.executeQuery(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug(e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
			}
		}
		return financialYearList;
	}
	
	/**
	 * 
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDistrict() throws Exception
	{
		ArrayList districtList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = CommonSQL.DISTRICT;
			districtList = dbUtility.executeQuery(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug(e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
			}
		}
		return districtList;
	}
	
	/**
	 * 
	 * @param financialYear
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getAvialableGuidelines(String financialYear, String disttID) throws Exception
	{
		ArrayList availableGuidelinesList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			String query = CommonSQL.GET_AVIALBALE_DRAFT_GUIDELINE;
			dbUtility.createPreparedStatement(query);
			String arr[] = {
					disttID,
					financialYear
			};
			availableGuidelinesList = dbUtility.executeQuery(arr);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug(e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
			}
		}
		return availableGuidelinesList;
	}
	
	
	/**
	 * 
	 * @param disttID
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getFinalGuidelineDetls(String disttID) throws Exception
	{
		ArrayList finalGuideline = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			String query = CommonSQL.GET_FINAL_GUIDELINE_DETLS;
			String arr [] = {disttID};
			
			dbUtility.createPreparedStatement(query);
			finalGuideline  = dbUtility.executeQuery(arr);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug(e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
			}
		}
		return finalGuideline;
	}
	
	/**
	 * @author sh836413
	 * @param lang
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getArea(String language) {
		ArrayList areaList=null;
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			if(language.equalsIgnoreCase("en"))
			{	
				areaList=dbUtility.executeQuery(CommonSQL.AREA_NAME_EN);
			}
			else
			{
				areaList=dbUtility.executeQuery(CommonSQL.AREA_NAME_HI);
			}
			
			return areaList;
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	/**
	 * @author sh836413
	 * @param lang,areaId,tehsilId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getSubArea(String language, String areaId, String tehsilId) {
		ArrayList subAreaList=null;
		
	try {
		dbUtility = new DBUtility();
		if(language.equalsIgnoreCase("en"))
		{	
			if("2".equalsIgnoreCase(areaId))//for urban
			{	
				dbUtility.createPreparedStatement(CommonSQL.SUB_AREA_NAME_EN_UR);
			subAreaList=dbUtility.executeQuery(new String[]{areaId,tehsilId});

			}
			else
			{
				dbUtility.createPreparedStatement(CommonSQL.SUB_AREA_NAME_EN);	
				subAreaList=dbUtility.executeQuery(new String[]{areaId});
			}
		}
		else
		{
			if("2".equalsIgnoreCase(areaId))//for rural
			{	
				dbUtility.createPreparedStatement(CommonSQL.SUB_AREA_NAME_HI_UR);
			subAreaList=dbUtility.executeQuery(new String[]{areaId,tehsilId});
			}
			else
			{
				dbUtility.createPreparedStatement(CommonSQL.SUB_AREA_NAME_HI);	
				subAreaList=dbUtility.executeQuery(new String[]{areaId});

			}
		}
		
		return subAreaList;
		
	} catch (Exception e) {
		logger.error(e);
		return null;
	}

	}
	/**
	 * @author sh836413 
	 * @param lang,subAreaId,tehsilId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWardPatwari(String language, String subAreaId,
			String tehsilId) {

			ArrayList wardPatwariList=null;
		
		try {
			dbUtility = new DBUtility();
			if(language.equalsIgnoreCase("en"))
			{	
				dbUtility.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_EN);
			}
			else
			{
				dbUtility.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_HI);	
			}
			wardPatwariList=dbUtility.executeQuery(new String[]{subAreaId,tehsilId});
			return wardPatwariList;
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * @author sh836413 
	 * @param lang,wardPatwariId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getColonyName(String language, String wardPatwariId) {
		ArrayList l2NameList=null;
		
		try {
			dbUtility = new DBUtility();
			if(language.equalsIgnoreCase("en"))
			{	
				dbUtility.createPreparedStatement(CommonSQL.COLONY_NAME_EN);
			}
			else
			{
				dbUtility.createPreparedStatement(CommonSQL.COLONY_NAME_HI);	
			}
			l2NameList=dbUtility.executeQuery(new String[]{wardPatwariId});
			return l2NameList;
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}	
	}
	/**
	 * 
	 * @param <LinkedHashMap>
	 * @param glDTO
	 * @return HashMap
	 * @throws Exception
	 */
	public LinkedHashMap getGuidelineDataPraroop1 (GuidelineDTO glDTO, GuidelinePreparationForm eForm, String lang)throws Exception  {
		LinkedHashMap  guidelineData = new LinkedHashMap();
		HashMap tehsilMap = new HashMap();
		HashMap wardMap = new HashMap();
		HashMap mohallaMap = new HashMap();
		glDTO.setPraroopType("1");
		try {
				String disttName = glDTO.getDistrict();
				ArrayList tehsilList = gDAO.getTehsilList(glDTO.getDistrictID(),lang);
				tehsilMap = new HashMap();
				for(int i = 0;i<tehsilList.size();i++)
				{
					/*
					wardMap = new HashMap();
					ArrayList mohallaGuidelineData  = new ArrayList();
					GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
					String tehsilId = tDTO.getTehsilID();
					String tehsilName = tDTO.getTehsil();
					ArrayList wardPatwariList = gDAO.getWardPatwari(tehsilId,lang);
					for(int j = 0;j<wardPatwariList.size();j++)
					{
						mohallaMap = new HashMap();
						GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
						String wardId = wDTO.getWardID();
						String wardName = wDTO.getWard();
						ArrayList mohallaList = gDAO.getMohViewList(wardId,lang);
						for(int k = 0; k<mohallaList.size();k++)
						{
							
							GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
							String mohallaId = mDTO.getMohallaID();
							String mohallaName = mDTO.getMohalla();
							glDTO.setTehsilID(tehsilId);
							glDTO.setWardID(wardId);
							glDTO.setMohallaID(mohallaId);
						
							mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
							logger.debug("size of arrayList: "+mohallaGuidelineData.size());
							logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName);
							guidelineData.put(disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName, mohallaGuidelineData);
							
						}
					}
					
					
			*/
					wardMap = new HashMap();
					ArrayList mohallaGuidelineData  = new ArrayList();
					GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
					String tehsilId = tDTO.getTehsilID();
					String tehsilName = tDTO.getTehsil();
					ArrayList areaList=getArea(lang);
					for(int a=0;a<areaList.size();a++){
						ArrayList al =(ArrayList)areaList.get(a);
						//GuidelineDTO alDTO =(GuidelineDTO)areaList.get(a);
						String areaId = al.get(0).toString();
						String areaName = al.get(1).toString();
						ArrayList subAreaList = getSubArea(lang,areaId,tehsilId);
						for(int b=0;b<subAreaList.size();b++){
							ArrayList sal =(ArrayList)subAreaList.get(b);
							String subAreaId = sal.get(0).toString();
							String subAreaName = sal.get(1).toString();
							ArrayList wardPatwariList = getWardPatwari(lang,subAreaId,tehsilId);
							for(int j = 0;j<wardPatwariList.size();j++)
							{
								mohallaMap = new HashMap();
								//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
								//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
								ArrayList wpl =(ArrayList)wardPatwariList.get(j);
								String wardId = wpl.get(0).toString();
								String wardName = wpl.get(1).toString();
								String wardMappingID = wpl.get(2).toString();
								ArrayList mohallaList = getColonyName(lang,wardMappingID);
								for(int k = 0; k<mohallaList.size();k++)
								{
									
									//GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
									ArrayList col =(ArrayList)mohallaList.get(k);
									String colonyID = col.get(0).toString();
									String colonyName = col.get(1).toString();
									String colonyMappingID = col.get(2).toString();
									glDTO.setTehsilID(tehsilId);
									glDTO.setAreaTypeID(areaId);
									glDTO.setSubAreaID(subAreaId);
									glDTO.setWardMappingID(wardMappingID);
									glDTO.setWardID(wardId);
									glDTO.setMohallaID(colonyID);
									glDTO.setColonyMappingID(colonyMappingID);
									
									mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
									logger.debug("size of arrayList: "+mohallaGuidelineData.size());
									logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName);
									guidelineData.put(disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName, mohallaGuidelineData);
									
								}
							}
						}
					}
					
						
				
				}
				
				logger.debug("size of HashMap"+guidelineData.size());

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
			}
		}
		
		return guidelineData;
	}

	/**
	 * 
	 * @param glDTO
	 * @return HashMap
	 * @throws Exception
	 */
	public LinkedHashMap getGuidelineDataPraroop2 (GuidelineDTO glDTO,GuidelinePreparationForm eForm,String lang)throws Exception  {
		LinkedHashMap  guidelineData = new LinkedHashMap();
		HashMap tehsilMap = new HashMap();
		HashMap wardMap = new HashMap();
		HashMap mohallaMap = new HashMap();
		glDTO.setPraroopType("2");
		try {
				String disttName = glDTO.getDistrict();
				ArrayList tehsilList = gDAO.getTehsilList(glDTO.getDistrictID(),lang);
				tehsilMap = new HashMap();
				for(int i = 0;i<tehsilList.size();i++)
				{
					/*
					wardMap = new HashMap();
					ArrayList mohallaGuidelineData  = new ArrayList();
					GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
					String tehsilId = tDTO.getTehsilID();
					String tehsilName = tDTO.getTehsil();
					ArrayList wardPatwariList = gDAO.getWardPatwari(tehsilId,lang);
					for(int j = 0;j<wardPatwariList.size();j++)
					{
						mohallaMap = new HashMap();
						GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
						String wardId = wDTO.getWardID();
						String wardName = wDTO.getWard();
						ArrayList mohallaList = gDAO.getMohViewList(wardId,lang);
						for(int k = 0; k<mohallaList.size();k++)
						{
							
							GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
							String mohallaId = mDTO.getMohallaID();
							String mohallaName = mDTO.getMohalla();
							glDTO.setTehsilID(tehsilId);
							glDTO.setWardID(wardId);
							glDTO.setMohallaID(mohallaId);
						
							mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
							logger.debug("size of arrayList: "+mohallaGuidelineData.size());
							logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName);
							guidelineData.put(disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName, mohallaGuidelineData);
							
						}
					}
				
					
			*/

					wardMap = new HashMap();
					ArrayList mohallaGuidelineData  = new ArrayList();
					GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
					String tehsilId = tDTO.getTehsilID();
					String tehsilName = tDTO.getTehsil();
					
					ArrayList areaList=getArea(lang);
					for(int a=0;a<areaList.size();a++){
						ArrayList al =(ArrayList)areaList.get(a);
						//GuidelineDTO alDTO =(GuidelineDTO)areaList.get(a);
						String areaId = al.get(0).toString();
						String areaName = al.get(1).toString();
						ArrayList subAreaList = getSubArea(lang,areaId,tehsilId);
						for(int b=0;b<subAreaList.size();b++){
							ArrayList sal =(ArrayList)subAreaList.get(b);
							String subAreaId = sal.get(0).toString();
							String subAreaName = sal.get(1).toString();
							ArrayList wardPatwariList = getWardPatwari(lang,subAreaId,tehsilId);
							for(int j = 0;j<wardPatwariList.size();j++)
							{
								mohallaMap = new HashMap();
								//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
								//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
								ArrayList wpl =(ArrayList)wardPatwariList.get(j);
								String wardId = wpl.get(0).toString();
								String wardName = wpl.get(1).toString();
								String wardMappingID = wpl.get(2).toString();
								ArrayList mohallaList = getColonyName(lang,wardMappingID);
								for(int k = 0; k<mohallaList.size();k++)
								{
									
									//GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
									ArrayList col =(ArrayList)mohallaList.get(k);
									String colonyID = col.get(0).toString();
									String colonyName = col.get(1).toString();
									String colonyMappingID = col.get(2).toString();
									glDTO.setTehsilID(tehsilId);
									glDTO.setAreaTypeID(areaId);
									glDTO.setSubAreaID(subAreaId);
									glDTO.setWardMappingID(wardMappingID);
									glDTO.setWardID(wardId);
									glDTO.setMohallaID(colonyID);
									glDTO.setColonyMappingID(colonyMappingID);
									
									mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
									logger.debug("size of arrayList: "+mohallaGuidelineData.size());
									logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName);
									guidelineData.put(disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName, mohallaGuidelineData);
									
								}
							}
						}
					}
					
					
			
				}
				
				logger.debug("size of HashMap"+guidelineData.size());

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
			}
		}
		
		return guidelineData;
	}

	/**
	 * 
	 * @param glDTO
	 * @return HashMap
	 * @throws Exception
	 */
	public LinkedHashMap getGuidelineDataPraroop3 (GuidelineDTO glDTO,GuidelinePreparationForm eForm, String lang)throws Exception  {
		LinkedHashMap  guidelineData = new LinkedHashMap();
		HashMap tehsilMap = new HashMap();
		HashMap wardMap = new HashMap();
		HashMap mohallaMap = new HashMap();
		glDTO.setPraroopType("3");
		try {
				String disttName = glDTO.getDistrict();
				ArrayList tehsilList = gDAO.getTehsilList(glDTO.getDistrictID(),lang);
				tehsilMap = new HashMap();
				for(int i = 0;i<tehsilList.size();i++)
				{/*
					wardMap = new HashMap();
					ArrayList mohallaGuidelineData  = new ArrayList();
					GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
					String tehsilId = tDTO.getTehsilID();
					String tehsilName = tDTO.getTehsil();
					ArrayList wardPatwariList = gDAO.getWardPatwari(tehsilId,lang);
					for(int j = 0;j<wardPatwariList.size();j++)
					{
						mohallaMap = new HashMap();
						GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
						String wardId = wDTO.getWardID();
						String wardName = wDTO.getWard();
						ArrayList mohallaList = gDAO.getMohViewList(wardId,lang);
						for(int k = 0; k<mohallaList.size();k++)
						{
							
							GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
							String mohallaId = mDTO.getMohallaID();
							String mohallaName = mDTO.getMohalla();
							glDTO.setTehsilID(tehsilId);
							glDTO.setWardID(wardId);
							glDTO.setMohallaID(mohallaId);
						
							mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
							logger.debug("size of arrayList: "+mohallaGuidelineData.size());
							logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName);
							guidelineData.put(disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName, mohallaGuidelineData);
							
						}
					}
					
					
			*/

					wardMap = new HashMap();
					ArrayList mohallaGuidelineData  = new ArrayList();
					GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
					String tehsilId = tDTO.getTehsilID();
					String tehsilName = tDTO.getTehsil();
					
					ArrayList areaList=getArea(lang);
					for(int a=0;a<areaList.size();a++){
						ArrayList al =(ArrayList)areaList.get(a);
						//GuidelineDTO alDTO =(GuidelineDTO)areaList.get(a);
						String areaId = al.get(0).toString();
						String areaName = al.get(1).toString();
						ArrayList subAreaList = getSubArea(lang,areaId,tehsilId);
						for(int b=0;b<subAreaList.size();b++){
							ArrayList sal =(ArrayList)subAreaList.get(b);
							String subAreaId = sal.get(0).toString();
							String subAreaName = sal.get(1).toString();
							ArrayList wardPatwariList = getWardPatwari(lang,subAreaId,tehsilId);
							for(int j = 0;j<wardPatwariList.size();j++)
							{
								mohallaMap = new HashMap();
								//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
								//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
								ArrayList wpl =(ArrayList)wardPatwariList.get(j);
								String wardId = wpl.get(0).toString();
								String wardName = wpl.get(1).toString();
								String wardMappingID = wpl.get(2).toString();
								ArrayList mohallaList = getColonyName(lang,wardMappingID);
								for(int k = 0; k<mohallaList.size();k++)
								{
									
									//GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
									ArrayList col =(ArrayList)mohallaList.get(k);
									String colonyID = col.get(0).toString();
									String colonyName = col.get(1).toString();
									String colonyMappingID = col.get(2).toString();
									glDTO.setTehsilID(tehsilId);
									glDTO.setAreaTypeID(areaId);
									glDTO.setSubAreaID(subAreaId);
									glDTO.setWardMappingID(wardMappingID);
									glDTO.setWardID(wardId);
									glDTO.setMohallaID(colonyID);
									glDTO.setColonyMappingID(colonyMappingID);
									
									mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
									logger.debug("size of arrayList: "+mohallaGuidelineData.size());
									logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName);
									guidelineData.put(disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName, mohallaGuidelineData);
									
								}
							}
						}
					}
					
					
				
				}
				
				logger.debug("size of HashMap"+guidelineData.size());

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
			}
		}
		
		return guidelineData;
	}


	/**
	 * 
	 * @param gDTO
	 * @return ArrayList
	 */
	public ArrayList getIndividualMohallaViewPraroop(GuidelineDTO gDTO)
	{
		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String ward = null;
		String sql = "";
		try {
			
			dbUtility = new DBUtility();
	System.out.println("hi");
			dbUtility.createPreparedStatement(CommonSQL.GUIDELINE_REPORT_TEMP_PRAROOP);
			System.out.println("hi");
			String patID = gDTO.getPatwariID();
			String warID = gDTO.getWardID();
			
			if(patID != null && patID.length() > 0)
			{
				 ward = gDTO.getPatwariID();
			}
			else
				 ward = gDTO.getWardID();
			
			
		         String arr[] = {gDTO.getRadioValue(),gDTO.getAreaTypeID(),
		        		 gDTO.getTehsilID(),
		        		 ward,gDTO.getColonyMappingID(),
		        		 gDTO.getMohallaID(),
		        		 gDTO.getPraroopType()};  
	          
	         list2 =  dbUtility.executeQuery(arr);
	           
	         if(list2.size() == 0)
	         {
	        	 mohallaDetails = new GuidelineDTO();
	        	 if(gDTO.getPraroopType() == "1")
	        	 {
	    		
	        		 for(int i = 0 ;i < 3; i++)
	        		 {
	        			 mohallaDetails.setGuideLineValue("0");
	        			 mohallaDetailsList.add(mohallaDetails);
	        		 }
	        	 }
	        	 else if(gDTO.getPraroopType() == "2")
	        	 {
	        		 for(int i = 0 ;i < 9; i++)
	        		 {
	        			 mohallaDetails.setGuideLineValue("0");
	        			 mohallaDetailsList.add(mohallaDetails);
	        		 }
	        	 }
	        	 else
	        	 {
	        		 for(int i = 0 ;i < 4; i++)
	        		 {
	        			 mohallaDetails.setGuideLineValue("0");
	        			 mohallaDetailsList.add(mohallaDetails);
	        		 }
	        	 }
	         }
	         else
	         {
	        	for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohallaDetails = new GuidelineDTO();
				
				mohallaDetails.setPropertyID((String)list1.get(0));
				mohallaDetails.setPropertyTypeName((String)list1.get(1));
				mohallaDetails.setL1_ID((String)list1.get(2));
				mohallaDetails.setL1Name((String)list1.get(3));
				mohallaDetails.setL2_ID((String)list1.get(4));
				mohallaDetails.setL2Name((String)list1.get(5));
				if(list1.get(6).equals(""))
				{
					mohallaDetails.setGuideLineValue("0");
				}
				else
				{
					mohallaDetails.setGuideLineValue((String)list1.get(6));
				}
				mohallaDetails.setMohallaID((String)list1.get(7));
				mohallaDetails.setMohalla((String)list1.get(8));
				mohallaDetails.setUomName((String)list1.get(9));
				mohallaDetails.setUomID((String)list1.get(10));
				
				String guidelineID = gDTO.getFinalGuideline();
				logger.debug("********"+guidelineID);
				String guideFinalValue = "";
				if(list1.get(4) == "" || list1.get(4) == null)
				{
					sql = CommonSQL.GET_PRVALENT_GUIDELINE_DETLS_NULL;
					String arr1[] = {guidelineID,
			        		 gDTO.getTehsilID(),
			        		 ward,
			        		 gDTO.getMohallaID(),gDTO.getColonyMappingID(),
			        		mohallaDetails.getPropertyID(),
			        		mohallaDetails.getL1_ID()
			        		
			        		};
					dbUtility.createPreparedStatement(sql);
					guideFinalValue = dbUtility.executeQry(arr1);
				}
				else
				{
					sql = CommonSQL.GET_PRVALENT_GUIDELINE_DETLS;
					String arr1[] = {guidelineID,
			        		 gDTO.getTehsilID(),
			        		 ward,
			        		 gDTO.getMohallaID(),gDTO.getColonyMappingID(),
			        		mohallaDetails.getPropertyID(),
			        		mohallaDetails.getL1_ID(),
			        		mohallaDetails.getL2_ID()
			        		};
					dbUtility.createPreparedStatement(sql);
					guideFinalValue = dbUtility.executeQry(arr1);
				}
				
				if(guideFinalValue == "")
				{
					mohallaDetails.setGuidelineFinalValue("0");
				}
				else
				{
					mohallaDetails.setGuidelineFinalValue(guideFinalValue);
				}
				mohallaDetailsList.add(mohallaDetails);
			}
	    }
	    
			
			logger.debug("size of mohallaDetailsList"+mohallaDetailsList.size());
		} catch (Exception x) {
			logger.debug("error---->  "+x);

		}finally {
			try {
				
				dbUtility.closeConnection();
			} catch (Exception ex) {

				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return mohallaDetailsList;
	}
	
	
}
