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
 * FILE NAME: GuidelineApprovalBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    6th MAY 2008 
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE APPROVAL ACTION.
 */

package com.wipro.igrs.guideline.bd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wipro.igrs.ACL.dto.OfficeDTO;
import com.wipro.igrs.guideline.dao.GuidelineApprovalDAO;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.login.dto.MasterListDTO;

/**
 * @author NIHAR M.
 * 
 */
public class GuidelineApprovalBD {

	GuidelineApprovalDAO guideDAO = new GuidelineApprovalDAO();

	/**
	 * @param mohDetails
	 * @param gDTO
	 * @param dFrom
	 * @param dTo
	 * @return boolean
	 * @throws Exception
	 */

	/**
	 * @return
	 */
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
			//logger.debug("Activity ID"+actId);
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
					//logger.debug("Activity list---"+(String)list1.get(0));
					if(((String)list1.get(0)).equalsIgnoreCase(actId))
					{
						officeList.addAll(ofcdto.getOfficeList());
					}
				}
			}
			//logger.debug("size of officeList"+officeList.size());
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
	 * @param distid
	 * @return ArrayList that holds the TehsilList
	 */
	/*public ArrayList getTehsilList(String distid) {
		return guideDAO.getTehsilList(distid);
	}*/

	/**
	 * @return ArrayList that holds the AreaTypes
	 */
	public ArrayList getAreasTypeList() {
		return guideDAO.getAreasTypeList();
	}
	
	
//////////////////////////BELOW CODE COMMENTED BY SIMRAN/////////////////////////////////////////////////////
	/**
	 * @param tehsilId
	 * @return ArrayList that holds the WardList
	 */
	/*public ArrayList getWardList(String tehsilId) {

		//String tehsilID[] = new String[1];
		//tehsilID[0] = tehsilId;
		return guideDAO.getWardList(tehsilId);
	}*/

	/**
	 * @param tehsilId
	 * @return PatwariList
	 */
	/*public ArrayList getPatwariList(String tehsilId) {

		//String tehsilID[] = new String[1];
		//tehsilID[0] = tehsilId;
		return guideDAO.getPatwariList(tehsilId);
	}*/

	/**
	 * @param patwariId
	 * @return ArrayList that holds the VillageList
	 */
	/*public ArrayList getVillage(String patwariId, String fDate, String tDate) {

		String patID[] = new String[3];
		patID[0] = patwariId;
		patID[1] = fDate;
		patID[2] = tDate;
		return guideDAO.getVillage(patID);
	}*/

	/**
	 * @param mohDetails
	 * @param gDTO
	 * @param dFrom
	 * @param dTo
	 * @return boolean
	 * @throws Exception
	 */
	/*public boolean updateTempMohallaDetails(ArrayList mohDetails,
			GuidelineDTO gDTO, String dFrom, String dTo,String roleId, String funId, String userId) throws Exception { // create

		GuidelineApprovalDAO dao = new GuidelineApprovalDAO();
		boolean mohallaInserted = false;
		mohallaInserted = dao.updateTempMohallaDetails(mohDetails, gDTO, dFrom,
				dTo,roleId,funId,userId);
		return mohallaInserted;
	}*/

	/**
	 * This function will return one arraylist of mohalla by the taking the
	 * parameters as wardid, duration from date and duration to date. This is
	 * used for the approval process.
	 * 
	 * @param wardId
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList that holds the MOHALLA List
	 */
	/*public ArrayList getMohList(String wardId) {

		//String wardID[] = new String[1];
		//wardID[0] = wardId;
		//wardID[1] = fDate;
		//wardID[2] = tDate;

		return guideDAO.getMohList(wardId);
	}*/

	/**
	 * @return ArrayList
	 */
	/*public ArrayList checkTempTableData() {
		return guideDAO.checkTempTableData();
	}*/

	/**
	 * This function is used for to fetch the individual mohalla list details by
	 * taking the parameters as mohalla id, base period from and base period to.
	 * This is used for the Approval Process.
	 * 
	 * @param mohID
	 * @param baseFrom
	 * @param baseTo
	 * @return ArrayList that holds the Individual Mohalla Details
	 */

	/*public ArrayList getIndividualMohallaDetails(String mohID, String fYear) {

		String[] param = new String[2];
		param[0] = mohID;
		param[1] = fYear;
	//	param[2] = baseTo;

		return guideDAO.getIndividualMohallaDetails(param);
	}
	*/
	
	/*public ArrayList getStartEndDate(String fyear)
	{
		
		//String fY[] = new String[1];
		//fY[0] = fyear;
		return guideDAO.getStartEndDate(fyear);
	}*/
	
	/////////////////////////////////////////END COMMENTED CODE////////////////////////////////////////////////////
	
	/**
	 * @return ARrayList that holds FinancialYear LIst
	 */
	public ArrayList getFinancialYearList()
	{
		return guideDAO.getFinancialYearList();
	}
	
	
	/**
	 * 
	 * @param finan
	 * @param district
	 * @return ArrayList that Holds available versions of Guideline for a particular District and 
	 * Financial Year 
	 */
	public ArrayList showStatusChecker( String finan,String  district, String lang)
	{
		return guideDAO.showStatusChecker(finan, district, lang);
	}
	
	/**
	 * 
	 * @param distId
	 * @param guideID
	 * @return ArrayList that holds list of Tehsils for particular guideline for which Checker has not 
	 * yet approved the guideline Data
	 */
	public ArrayList getPendingTehsilListChecker(String distId, long guideID, String lang)
	{
		return guideDAO.getPendingTehsilListChecker(distId,guideID,lang);
	}
	
	/**
	 * 
	 * @param distId
	 * @param guideID
	 * @return ArrayList that holds list of Tehsils for particular guideline for which Checker has  
	 * already approved the guideline Data
	 */
	public ArrayList getCompleteTehsilListChecker(String distId, long guideID, String lang)
	{
		return guideDAO.getCompleteTehsilListChecker(distId,guideID,lang);
	}
	
	/**
	 * 
	 * @param tehID
	 * @param guideID
	 * @return ArrayList that holds list of wards for particular guideline for which Checker has not
	 * yet approved the guideline Data
	 */
	public ArrayList getPendingWardListChecker(int tehID, long guideID, String lang)
	{
		return guideDAO.getPendingWardListChecker(tehID, guideID,lang);
	}
	
	/**
	 * 
	 * @param tehID
	 * @param guideID
	 * @return ArrayList that holds list of wards for particular guideline for which Checker has  
	 * already approved the guideline Data
	 */
	public ArrayList getCompleteWardListChecker(int tehID, long guideID, String lang)
	{
		return guideDAO.getCompleteWardListChecker(tehID, guideID,lang);
	}
	
	/**
	 * 
	 * @param tehID
	 * @param guideID
	 * @return ArrayList that holds list of Patwari Halkas for particular guideline for which Checker has not
	 * yet approved the guideline Data
	 */
	public ArrayList getPendingPatwariListChecker(int tehID, long guideID, String lang)
	{
		return guideDAO.getPendingPatwariListChecker(tehID, guideID,lang);
	}
	
	/**
	 * 
	 * @param tehID
	 * @param guideID
	 * @return ArrayList that holds list of Patwari Halkas for particular guideline for which Checker has  
	 * already approved the guideline Data
	 */
	public ArrayList getCompletePatwariListChecker(int tehID, long guideID, String lang)
	{
		return guideDAO.getCompletePatwariListChecker(tehID, guideID,lang);
	}
	
	/**
	 * 
	 * @param wardId
	 * @param guideId
	 * @return  ArrayList that holds list of mohallas for particular guideline for which Checker has not
	 * yet approved the guideline Data
	 */
	public ArrayList getPendingMohallaListChecker(int wardId, long guideId, String lang)
	{
		return guideDAO.getPendingMohallaListChecker( wardId,  guideId,lang);
	}
	
	/**
	 * 
	 * @param wardId
	 * @param guideId
	 * @return ArrayList that holds list of mohallas for particular guideline for which Checker has  
	 * already approved the guideline Data
	 */
	public ArrayList getCompleteMohallaListChecker(int wardId, long guideId, String lang)
	{
		return guideDAO.getCompleteMohallaListChecker(wardId, guideId, lang);
	}
	
	/**
	 * 
	 * @param guideID
	 * @param mohID
	 * @return ArrayList that holds Guideline Data for a particular mohalla which is pending for approval
	 */
	public ArrayList guideLineViewChecker(long guideID, String mohID, String lang)
	{
		return guideDAO.guideLineViewChecker(guideID, mohID, lang);
	}
	
	
	/*public ArrayList getIndividualMohallaDetails2()
	{
		return guideDAO.getIndividualMohallaDetails2();
	}*/
	
	/*public boolean updateTempMohallaDetailsNew(ArrayList mohDetails,
			GuidelineDTO gDTO, String dFrom, String dTo,String roleId, String funId, String userId) throws Exception { // create

		GuidelineApprovalDAO dao = new GuidelineApprovalDAO();
		boolean mohallaInserted = false;
		mohallaInserted = dao.updateTempMohallaDetails(mohDetails, gDTO, dFrom,
				dTo,roleId,funId,userId);
		return mohallaInserted;
	}
	public boolean insertFinalMohallaMasterDetailsNew(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, 
			String dTo,String roleId, String funId, String userId){
		GuidelineApprovalDAO dao = new GuidelineApprovalDAO();
		boolean mohallaInserted = false;
		mohallaInserted = dao.insertFinalMohallaMasterDetailsNew(mohallaDetails, gDTO, dFrom,
				dTo,roleId,funId,userId);
		return mohallaInserted;
		
	}*/

	
	/**
	 * @param guideID
	 * @param mohID
	 * @return ArrayList that holds guideline data for a particular mohalla that has been approved
	 */
	public ArrayList guideLineViewCheckerComplete( long guideID, String mohID, String lang)
	{
		return guideDAO.guideLineViewCheckerComplete(guideID, mohID, lang);
	}
	
	/*public ArrayList mappedSubClauses(GuidelineDTO gDTO)
	{
		return guideDAO.mappedSubclauses(gDTO);
	}*/
	
	/*public ArrayList unMappedSubClauses(GuidelineDTO gDTO)
	{
		return guideDAO.unMappedSubclauses(gDTO);
	}*/
	
	/**
	 * @param gDTO
	 * @return ArrayList that holds list of subclauses 
	 */
	public ArrayList subclausesNew(GuidelineDTO gDTO)
	{
		return guideDAO.subclausesNew(gDTO);
	}
	
	/**
	 * 
	 * @param subId
	 * @return ArrayList that holds subclause name and subclause desc for subclause ids passed as parameter
	 */
	public ArrayList subClause(String subId[])
	{
		return guideDAO.subClause(subId);
	}
	
	/**
	 * 
	 * @param subKeys
	 * @return HashMap that holds list of subClauses that have not been modified or changed
	 */
	public HashMap subClauseNotClicked(ArrayList subKeys)
	{
		return guideDAO.subClauseNotClicked(subKeys);
	
	}
	
	/**
	 * This function is used to copy subClauses in case of derived guideline
	 * @param guideId
	 * @return boolean
	 */
	public boolean subClauseDeriveToApprove(String guideId)
	{
		return guideDAO.subClauseDeriveToApprove(guideId);
	}
	
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that holds subClauses for derived Guideline
	 */
	public ArrayList subclauses(GuidelineDTO gDTO)
	{
		return guideDAO.subclauses(gDTO);
	}
	
	/**
	 * 
	 * @param subKeys
	 * @param gDTO
	 * @return HashMap that holds list of subClauses that are derived and has not been modified or changed
	 */
	public HashMap subClauseNotClickedDerived(ArrayList subKeys, GuidelineDTO gDTO)
	{
		return guideDAO.subClauseNotClickedDerived(subKeys, gDTO);
	
   }
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that holds list of subclauses to show on read only page for checker(In case of derived Guideline)
	 */
	public ArrayList subClauseReadOnlyCompleteDerived (GuidelineDTO gDTO)
	{
		return guideDAO.subClauseReadOnlyCompleteDerived(gDTO);
	}
	
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that holds list of subclauses to show on read only page for checker
	 */
	public ArrayList subClauseReadOnlyComplete(GuidelineDTO gDTO)
	{
		return guideDAO.subClauseReadOnlyComplete(gDTO);
	}

	/**
	 * 
	 * @param officeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrict(String officeId, String lang ) throws Exception
	{
		logger.debug("**************new district method**********");
		return guideDAO.getDistrict(officeId,lang);
	}
}
