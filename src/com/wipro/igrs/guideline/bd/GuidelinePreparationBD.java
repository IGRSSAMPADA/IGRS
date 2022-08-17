
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
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
 */ 

/* 
 * FILE NAME: GuidelinePreparationBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    6th MAY 2008 
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BUSINESS DELEGATE  FOR GUIDELINE PREPARATION ACTION.
 */

package com.wipro.igrs.guideline.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wipro.igrs.ACL.dto.OfficeDTO;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.guideline.dao.GuidelinePreparationDAO;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.regCompChecker.sql.RegCompCheckerSQL;
/**
 * @author NIHAR M.
 *
 */
public class GuidelinePreparationBD {
	GuidelinePreparationDAO guideDAO = new GuidelinePreparationDAO();
	GuidelineDTO formDTO = new GuidelineDTO();
	private Logger logger = (Logger) Logger
	.getLogger(GuidelinePreparationBD.class);
	
	
	
	
	/**
	 * 
	 * @param officeIdList
	 * @return ArrayList that holds the DistrictList for logged in user
	 */
	public ArrayList getDistrictList(HashMap officeDetails, String actId) {
		
		String roleID = "";
		ArrayList officeList = new ArrayList();
		ArrayList activityList = new ArrayList();
		//ArrayList finalList  = new ArrayList();
		
		
		
		Set mapSet1 = (Set)officeDetails.entrySet();
		Iterator mapIterator1 = mapSet1.iterator();
		logger.debug("IN DISTRICT DETAILS METHOD------>");
		logger.debug("Activity ID"+actId);
		while(mapIterator1.hasNext())
		{
			Map.Entry mapEntry1 = (Map.Entry)mapIterator1.next();
			OfficeDTO ofcdto= (OfficeDTO)mapEntry1.getValue();
			roleID = (String)mapEntry1.getKey();
			activityList = (ArrayList)ofcdto.getActivityList();
			activityList.trimToSize();
			for(int i = 0;i<activityList.size();i++)
			{
				ArrayList list1 = (ArrayList)activityList.get(i);
				logger.debug("Activity list---"+(String)list1.get(0));
				if(((String)list1.get(0)).equalsIgnoreCase(actId))
				{
					officeList.addAll(ofcdto.getOfficeList());
				}
			}
		}
		logger.debug("size of officeList"+officeList.size());
		officeList.trimToSize();
		
		
		ArrayList districtList = guideDAO.getDistrictIdList(officeList);
			
		
		/*ArrayList list =  guideDAO.getDistrictList();
		ArrayList disttList = new ArrayList();
		Iterator itr = list.iterator();
		Iterator itr2 = officeIdList.iterator();
		
		ArrayList disttArr = new ArrayList();
		
			for(int i = 0;i<officeIdList.size();i++)
			{
				disttArr = (ArrayList)officeIdList.get(0);
			}
			
			
			//for(int k = 0;k<officeDetails.size();k++)
			//{
				
				//if(officeDetails.get(k)!= null)
				//{
					//logger.debug("list not null");
					//MasterListDTO distt = (MasterListDTO)officeDetails.get(k);
				
				//disttArr.add((String)distt.getDistID());
				
				//}
			//}
	
		logger.debug("DISTRICT ARR");
		
		while(itr.hasNext())
		{
			GuidelineDTO gDTO = (GuidelineDTO)itr.next();
			for(int i = 0;i<disttArr.size();i++)
			{
				if(disttArr.get(i).equals(gDTO.getDistrictID()))
				{
					disttList.add(gDTO);
				}
			}	
		}
		logger.debug("size of final disttList"+disttList.size());*/
		return districtList;
		
	}
	
	/**
	 * 
	 * @return ArrayList that holds all districts
	 */
	public ArrayList getDistrictList2() {
		return guideDAO.getDistrictList();
	}
	
	/**
	 * 
	 * @return ArrayList that holds FinancialYear List
	 */
	public ArrayList getFinancialYearList(){
		return guideDAO.getFinancialYearList();
	}

	
	/*
	 * 
	 * BELOW CODE COMMENTED BY SIMRAN AS THESE METHODS ARE NO LONGER USED IN GUIDELINE MODULE
	 */
	
	/*public ArrayList getVersionList(String finan, String distt){
		return guideDAO.getVersionList(finan, distt);
	}
	
	
	
	public ArrayList  getTehsilList(String distid){
		logger.debug("BD:-  getTehsilList()");
		//String distID[] = new String[1];
		//distID[0] = distid;
		return guideDAO.getTehsilList(distid);
	}

	
	public ArrayList getWardList(String tehsilId){

		//String tehsilID[] = new String[1];
		//tehsilID[0] = tehsilId;
		return guideDAO.getWardList(tehsilId);
	}

	
	public ArrayList getPatwariList(String tehsilId){

		//String tehsilID[] = new String[1];
		//tehsilID[0] = tehsilId;
		return guideDAO.getPatwariList(tehsilId);
	}

	public ArrayList getMohViewList(String warray){

		///String wardID[] = new String[2];
		//wardID[0] = warray;
		//wardID[1] = fyear;

		return guideDAO.getMohViewList(warray);

	}
	public ArrayList getMohList2(String wardId){

		//String wardID[] = new String[1];
		//wardID[0] = wardId;
		return guideDAO.getMohList2(wardId);
	}

	public ArrayList getMohList(String wardId){

		String wardID[] = new String[1];
		wardID[0] = wardId;
		//wardID[1] = fDate;
		//wardID[2] = tDate;

		return guideDAO.getMohList(wardId);
	}
	public ArrayList getVillage(String patwariId){

		//String patID[] = new String[3];
		//patID[0] = patwariId;
		//patID[1] = fDate;
		//patID[2] = tDate;
		return guideDAO.getVillage(patwariId);
	}
	
	public ArrayList getIndividualMohallaDetails(String mohID, String baseFrom, String baseTo){

		String[] param = new String[3];
		param[0] = mohID;
		param[1] = baseFrom;
		param[2] = baseTo;

		return guideDAO.getIndividualMohallaDetails(param);
	}
	public ArrayList getIndividualMohallaDetailsReadOnly(String mohID, String baseFrom, String baseTo){

		String[] param = new String[3];
		param[0] = mohID;
		param[1] = baseFrom;
		param[2] = baseTo;

		return guideDAO.getIndividualMohallaDetailsReadOnly(param);
		}
		
		public boolean insertMohallaDetails(ArrayList mohalla) throws Exception{//approve  use it

		logger.debug("BD:-  insertMohallaDetails");

		GuidelinePreparationDAO dao = new GuidelinePreparationDAO();
		boolean mohallaInserted = false;
		mohallaInserted = dao.insertMohallaDetails(mohalla);
		return mohallaInserted;
	}
	public boolean insertTempMohallaDetails(ArrayList mohalla) throws Exception{//approve

		logger.debug("BD:-  insertMohallaDetails");

		GuidelinePreparationDAO dao = new GuidelinePreparationDAO();
		boolean mohallaInserted = false;
		mohallaInserted = dao.insertTempMohallaDetails(mohalla);
		return mohallaInserted;
	}
	public boolean insertIndividualMohallaDetails(ArrayList mohDetails, GuidelineDTO gDTO, String dFrom, String dTo, String roleId, String funId, String userId) throws Exception{  //create

		logger.debug("BD:-  insertIndividualMohallaDetails");
		GuidelinePreparationDAO dao = new GuidelinePreparationDAO();
		boolean mohallaInserted = false;
		mohallaInserted = dao.insertIndividualMohallaDetails(mohDetails, gDTO, dFrom, dTo,roleId,funId,userId);
		return mohallaInserted;
	}


	
	public boolean updateMohallaDetails(ArrayList mohalla) throws Exception{

		logger.debug("BD:-  updateMohallaDetails");

		GuidelinePreparationDAO dao = new GuidelinePreparationDAO();
		boolean mohallaUpdated = false;
		mohallaUpdated = dao.updateMohallaDetails(mohalla);
		return mohallaUpdated;
	}


	
	public String getMohallaName(String mohID)
	{
		return guideDAO.getMohallaName(mohID);
	}


	
	public ArrayList checkTempTableData()
	{
		return guideDAO.checkTempTableData();
	}
*/


	/**
	 * @return ArrayList that holds the AreaTypes
	 * @throws Exception 
	 */
	public ArrayList getAreasTypeList(GuidelineDTO ggdto) throws Exception{
		return guideDAO.getAreasTypeList(ggdto);
	}


	/**
	 * This function is used to fetch all the available property types for mohalla/village
	 * @return ArrayList 
	 */
	public ArrayList getIndividualMohallaDetails2(GuidelineDTO ggdto){
		return guideDAO.getIndividualMohallaDetails2(ggdto);
	}

	
	/*
	 * Added By Simran
	 */
	/**
	 * This function is used to get starting and ending date for current financial Year
	 * @return ArrayList
	 * @param fyear
	 */
	public ArrayList getStartEndDate(String fyear)
	{
		
		return guideDAO.getStartEndDate(fyear);
	}
	
	/**
	 * 
	 * @param finanDistt
	 * @param distId
	 * @return long guidelineId 
	 */
	
	public long getGuidelineID( String finanDistt, String distId)
	{
		return guideDAO.getGuidelineID(finanDistt, distId );
	}
	
	/**
	 * This function is used to insert all the combinations of tehsils/wards/mohallas in status table to keep
	 * track of data entry by maker
	 * @param guidelineID
	 * @param distId
	 * @return boolean
	 */
	
	public boolean insertAllCombinations(String guidelineID, String distId)
	{
		return guideDAO.insertAllCombinations(guidelineID, distId);
	}
	
	/**
	 * 
	 * @param distId
	 * @param guidelineID
	 * @return ArrayList that holds list of tehsils for which maker has not entered data in that guideline
	 */
	
	public ArrayList getTehsilListMaker(String distId, long guidelineID, GuidelineDTO ggdto )
	{
		
		return guideDAO.getTehsilListMaker(distId, guidelineID,ggdto);
		
	}
	/**
	 * 
	 * @param tehID
	 * @param guidelineID
	 * @return  ArrayList that holds list of wards for which maker has not entered data in that guideline
	 */

	public ArrayList getWardListMaker(String tehID, long guidelineID, GuidelineDTO ggdto)
	{
		return guideDAO.getWardListMaker(tehID, guidelineID, ggdto);
	}
	
	/**
	 * 
	 * @param tehID
	 * @param guidelineID
	 * @return  ArrayList that holds list of Patwari halkas for which maker has not entered data in that guideline
	 */
	
	public ArrayList getPatwariListMaker(String tehID, long guidelineID,GuidelineDTO ggdto)
	{
		return guideDAO.getPatwariListMaker(tehID, guidelineID, ggdto);
	}
	
	/**
	 * 
	 * @param wardID
	 * @param guidelineID
	 * @return  ArrayList that holds list of mohallas/villages for which maker has not entered data in that guideline
	 */
	public ArrayList getMohListMaker(String wardID, long guidelineID,GuidelineDTO ggdto)
	{
		return guideDAO.getMohListMaker(wardID, guidelineID, ggdto);
	}
	
	/**
	 * This function is used to insert data in guideline temp tables
	 * @param mohDetails
	 * @param gDTO
	 * @param dFrom
	 * @param dTo
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	 
	public boolean insertPendingGuidelineValues(ArrayList mohDetails, GuidelineDTO gDTO, String dFrom, String dTo, String roleId, String funId, String userId,String flag)throws Exception
	{
		return guideDAO.insertPendingGuidelineValues(mohDetails, gDTO, dFrom, dTo, roleId, funId, userId, flag);
	}
	
	/**
	 * 
	 * @param finan
	 * @param disttID
	 * @return ArrayList that holds all the available versions of guideline for that district
	 * along with status 
	 */
	public ArrayList showStatusMaker(String finan, String disttID, String lang)
	{
		return guideDAO.showStatusMaker(finan, disttID, lang);
	}
	
	/**
	 * This function is used to copy data from final tables when user derives 
	 * guideline from Previous Year's guideline
	 * @param gDTO
	 * @param userID
	 * @return boolean 
	 */
	public boolean insertParentData(GuidelineDTO gDTO, String userID)
	{
		return guideDAO.insertParentData(gDTO, userID);
	}
	
	/**
	 * This function is used to copy data from final tables when user derives 
	 * guideline from Draft guideline
	 * @param gDTO
	 * @param userID
	 * @return
	 */
	public boolean copyDraftData(GuidelineDTO gDTO, String userID)
	{
		return guideDAO.copyDraftData(gDTO, userID);
	}
	
	/**
	 * 
	 * @param finan
	 * @param disttID
	 * @param derivedFrom
	 * @param formDTO2 
	 * @return ArrayList that holds list of all versions of guideline derived from previous year or draft
	 * depending on derivedFrom Parameter passed
	 */
	public ArrayList showVersionFinalMaker(String finan, String disttID, int derivedFrom, String lang, GuidelineDTO formDTO2)
	{
		return guideDAO.showVersionFinalMaker(finan, disttID, derivedFrom,lang,formDTO2);
	}
	
	/**
	 * 
	 * @param formDTO
	 * @return ArrayList that holds guideline data in case guideline is derived
	 */
	
	public ArrayList getIndividualMohallaDetailsNew(GuidelineDTO formDTO)
	{
		return guideDAO.getIndividualMohallaDetailsNew(formDTO);
	}
	
	/**
	 * This function is used to update guideline values in case user
	 * has modified any data in derived guideline
	 * @param mohallaDetails
	 * @param gDTO
	 * @param dFrom
	 * @param dTo
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @param subIds
	 * @return boolean
	 */
	
	public boolean updatePendingGuidelineValues(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, 
			String dTo,String roleId, String funId, String userId)
	{
		return guideDAO.updatePendingGuidelineValues(mohallaDetails, gDTO, dFrom, dTo, roleId, funId, userId);
	}
	
	
	/*public boolean draftData(String distId)
	{
		return guideDAO.draftData(distId);
	}*/
	
	/**
	 * @param gDTO
	 * @return ArrayList that holds list of subclauses for selected mohalla 
	 * and property types in case of derived guideline 
	 */
	public ArrayList subclauses(GuidelineDTO gDTO)
	{
		return guideDAO.subclauses(gDTO);
	}
	
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that is newly added this year and has not been derived from previous years guideline
	 * @throws SQLException 
	 */
	public ArrayList subclausesNotDerived(GuidelineDTO gDTO) throws SQLException
	{
		return guideDAO.subclausesNotDerived(gDTO);
	}
	
	/**
	 * 
	 * @param subKeys
	 * @param gDTO
	 * @return HashMap that holds subclauses which are mapped but not clicked by user
	 */
	public HashMap subClauseNotClicked(ArrayList subKeys, GuidelineDTO gDTO)
	{
		return guideDAO.subClauseNotClicked(subKeys, gDTO);
	
   }
	
	/**
	 * 
	 * @param subKeys
	 * @param gDTO
	 * @return HashMap
	 */
	public HashMap subClauseNotClickedNewMohalla(ArrayList subKeys, GuidelineDTO gDTO)
	{
		return guideDAO.subClauseNotClickedNewMohalla(subKeys, gDTO);
	}
	
	/**
	 * 
	 * @param subId
	 * @return ArrayList that holds name of subclause whose Id is passed in this method
	 */
	public ArrayList subClause(String subId[])
	{
		return guideDAO.subClause(subId);
	}
	
	/*public ArrayList subclausesNew(GuidelineDTO gDTO)
	{
		return guideDAO.subclausesNew(gDTO);
	}*/
	
	/**
	 * This method is used to insert sub clauses in case of derived guideline
	 *  @param subDetails
	 *  @param gDTO
	 * @return boolean
	 */
	public boolean subClauseInsertionNewMohalla(ArrayList subDetails, GuidelineDTO gDTO)
	{
		return guideDAO.subClauseInsertionNewMohalla(subDetails, gDTO);
	}
	
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that holds available Draft versions of guideline
	 */
	public ArrayList availableDraftVersions(GuidelineDTO gDTO)
	{
	
		return guideDAO.availableDraftVersions(gDTO);
	}
	
	/**
	 * 
	 * @param officeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrict(String officeId, GuidelineDTO ggdto) throws Exception
	{
		logger.debug("**************new district method**********");
		return guideDAO.getDistrict(officeId, ggdto);
	}
	
	public ArrayList getSubArea(String language, String areaId, String tehsilId, String urbanFlag,String guidelineID) {
		ArrayList  subAreaList=null;
		ArrayList <GuidelineDTO>returnList=null;
		try{
			subAreaList=guideDAO.getSubArea(language,areaId,tehsilId,urbanFlag,guidelineID);
			if(subAreaList!=null&& subAreaList.size()>0)
			{
				returnList=new ArrayList<GuidelineDTO>();
				for(int i=0;i<subAreaList.size();i++)
				{
					ArrayList subList=(ArrayList) subAreaList.get(i);
					GuidelineDTO gDTO= new GuidelineDTO();
					gDTO.setSubAreaId((String) subList.get(0));
					gDTO.setSubAreaName((String) subList.get(1));
					returnList.add(gDTO);
					
				}
				
			}
			
			return returnList;
		}catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public ArrayList<GuidelineDTO> getWardPatwari(String language,
			String subAreaId,String tehsilId,String guidelineID) {
			ArrayList  wardPatwariList=null;
			ArrayList <GuidelineDTO>returnList=null;
		try{
			wardPatwariList=guideDAO.getWardPatwari(language,subAreaId,tehsilId,guidelineID);
			if(wardPatwariList!=null&& wardPatwariList.size()>0)
			{
				returnList=new ArrayList<GuidelineDTO>();
				for(int i=0;i<wardPatwariList.size();i++)
				{
					ArrayList subList=(ArrayList) wardPatwariList.get(i);
					GuidelineDTO propDTO= new GuidelineDTO();
					propDTO.setWardID((String) subList.get(0)+"~"+(String) subList.get(2));
					propDTO.setWard((String) subList.get(1));
					returnList.add(propDTO);
					
				}
				
			}
			
			return returnList;
		}catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public ArrayList<GuidelineDTO> getColonyName(String language,
			String wardPatwariId,String guidelineID) {
		ArrayList  l1NameList=new ArrayList();
		ArrayList <GuidelineDTO>returnList=null;
		try{
			l1NameList=guideDAO.getColonyName(language,wardPatwariId,guidelineID);
			if(l1NameList!=null&& l1NameList.size()>0)
			{
				returnList=new ArrayList<GuidelineDTO>();
				for(int i=0;i<l1NameList.size();i++)
				{
					ArrayList subList=(ArrayList) l1NameList.get(i);
					GuidelineDTO propDTO= new GuidelineDTO();
					propDTO.setMohallaID((String) subList.get(0)+"~"+(String) subList.get(2)+"~"+(String) subList.get(3));
					propDTO.setMohalla((String) subList.get(1));
					returnList.add(propDTO);
					
				}
				
			}
			
			return returnList;
		}catch (Exception e) {
			logger.error(e);
			return null;
		}

	
	}
	public ArrayList deriveGuidelineDetails(ArrayList guidelineValues)
	{
		return guideDAO.deriveGuidelineDetails(guidelineValues);
	}
	
	public ArrayList getTehsilListMakerCreation(String distId, long guideID, GuidelineDTO ggdto)
	{
		return guideDAO.getTehsilListMakerCreation(distId, guideID, ggdto);
	}
	
	public ArrayList getSubAreaFirst(String language, String areaId, String tehsilId, String urbanFlag) {
		ArrayList  subAreaList=null;
		ArrayList <GuidelineDTO>returnList=null;
		try{ 
			subAreaList=guideDAO.getSubAreaFirst(language,areaId,tehsilId,urbanFlag);
			if(subAreaList!=null&& subAreaList.size()>0)
			{
				returnList=new ArrayList<GuidelineDTO>();
				for(int i=0;i<subAreaList.size();i++)
				{
					ArrayList subList=(ArrayList) subAreaList.get(i);
					GuidelineDTO gDTO= new GuidelineDTO();
					gDTO.setSubAreaId((String) subList.get(0));
					gDTO.setSubAreaName((String) subList.get(1));
					returnList.add(gDTO);
					
				}
				
			}
			
			return returnList;
		}catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public ArrayList<GuidelineDTO> getWardPatwariFirst(String language,
			String subAreaId,String tehsilId) {
			ArrayList  wardPatwariList=null;
			ArrayList <GuidelineDTO>returnList=null;
		try{
			wardPatwariList=guideDAO.getWardPatwariFirst(language,subAreaId,tehsilId);
			if(wardPatwariList!=null&& wardPatwariList.size()>0)
			{
				returnList=new ArrayList<GuidelineDTO>();
				for(int i=0;i<wardPatwariList.size();i++)
				{
					ArrayList subList=(ArrayList) wardPatwariList.get(i);
					GuidelineDTO propDTO= new GuidelineDTO();
					propDTO.setWardID((String) subList.get(0)+"~"+(String) subList.get(2));
					propDTO.setWard((String) subList.get(1));
					returnList.add(propDTO);
					
				}
				
			}
			
			return returnList;
		}catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public ArrayList<GuidelineDTO> getColonyNameFirst(String language,
			String wardPatwariId) {
		ArrayList  l1NameList=new ArrayList();
		ArrayList <GuidelineDTO>returnList=null;
		try{
			l1NameList=guideDAO.getColonyNameFirst(language,wardPatwariId);
			if(l1NameList!=null&& l1NameList.size()>0)
			{
				returnList=new ArrayList<GuidelineDTO>();
				for(int i=0;i<l1NameList.size();i++)
				{
					ArrayList subList=(ArrayList) l1NameList.get(i);
					GuidelineDTO propDTO= new GuidelineDTO();
					propDTO.setMohallaID((String) subList.get(0)+"~"+(String) subList.get(2)+"~"+(String) subList.get(3));
					propDTO.setMohalla((String) subList.get(1));
					returnList.add(propDTO);
					
				}
				
			}
			
			return returnList;
		}catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList getTehsilListViewall(String distId, long guidelineID, GuidelineDTO ggdto) {
		return guideDAO.getTehsilListViewall(distId, guidelineID,ggdto);
	}

	public ArrayList getSubAreaListViewall(GuidelineDTO ggdto, String guideID) {
		return guideDAO.getSubAreaListViewall(ggdto,guideID);
	}

	public ArrayList getWardListViewall(GuidelineDTO ggdto, String guideID) {
		return guideDAO.getWardListViewall(ggdto,guideID);
	}

	public ArrayList getMohallaListViewall(GuidelineDTO ggdto, String guideID) {
		return guideDAO.getMohallaListViewall(ggdto,guideID);
	}

	public ArrayList getguidelineratesList(GuidelineDTO ggdto, String guideID) {
		return guideDAO.getguidelineratesList(ggdto,guideID);
	}

	public GuidelineDTO getFinancialDuration(GuidelineDTO ggdto,
			String guideID) {
		return guideDAO.getFinancialDuration(ggdto,guideID);
	}

	public String getMohallaAppliclause(GuidelineDTO formDTO2) {
		
		String appliclause=null;
		appliclause= guideDAO.getMohallaAppliclause(formDTO2);
		// TODO Auto-generated method stub
		return appliclause;
	}

	public  boolean getUpdatePrintStatus(GuidelineDTO formDTO2) throws Exception {
		return guideDAO.getUpdatePrintStatus(formDTO2);
	}

	public String getSRPublishStatus(GuidelineDTO formDTO2) {
		String srstatus=null;
		srstatus= guideDAO.getSRPublishStatus(formDTO2);
		// TODO Auto-generated method stub
		return srstatus;
	}

	public String getPrintStatus(GuidelineDTO formDTO2) {
		String printstatus=null;
		printstatus= guideDAO.getPrintStatus(formDTO2);
		// TODO Auto-generated method stub
		return printstatus;
	}

	public boolean getUpdateSubmitStatus(GuidelineDTO formDTO2) throws Exception {
		return guideDAO.getUpdateSubmitStatus(formDTO2);
	}

	public boolean getUpdateSubmitSRAll(GuidelineDTO formDTO2) throws Exception {
		return guideDAO.getUpdateSubmitSRAll(formDTO2);
		
	}

	public String getUserType(GuidelineDTO formDTO2) throws Exception {
		String UserType=null;
		UserType= guideDAO.getUserType(formDTO2);
		// TODO Auto-generated method stub
		return UserType;
	}

	public String getGuidelineparentList(GuidelineDTO formDTO2, String finanDistt, String fromDate, String toDate) throws Exception {
		return guideDAO.getGuidelineparentList(formDTO2,finanDistt,fromDate,toDate);
	}

	public boolean deleteDeacCombinations(String guidelineID, String distId) {
		return guideDAO.deleteDeacCombinations(guidelineID, distId);
	}

	public boolean insertNewCombinations(String guidelineID, String distId) {
		return guideDAO.insertNewCombinations(guidelineID, distId);
	}

	public ArrayList getNewMohallaName(String language, GuidelineDTO formDTO2) {
		return guideDAO.getMohallaListViewall(language,formDTO2);
	}

	public boolean insertTemplateData(String distId, GuidelineDTO formDTO2) {
		return guideDAO.insertTemplateData(distId,formDTO2);
	}

	public boolean getinsertDataForPrint(String guideId, GuidelineDTO formDTO2) {
		return guideDAO.getinsertDataForPrint(guideId,formDTO2);
	}

	public boolean checkForAlreadySubmitted(String guideLineID, GuidelineDTO gdto) throws Exception {
		return guideDAO.checkForAlreadySubmitted(guideLineID,gdto);
	}

	public ArrayList getconstructRateList(String appliclause) {
		return guideDAO.getconstructRateList(appliclause);
	}

	
}
