package com.wipro.igrs.spotInsp.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.spotInsp.bd.SpotInspBD;
import com.wipro.igrs.spotInsp.dao.SpotInspDAO;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;
import com.wipro.igrs.spotInsp.form.SpotInspForm;
import com.wipro.igrs.suppleDoc.dao.SuppleDocDAO;

public class SpotInspBO


{
	private final Logger logger = Logger.getLogger(SpotInspDAO.class);

	/**
	 * getting country values
	 * @return
	 */
	public ArrayList getCountry() throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getCountry();
	}

	public String getCurrentDistrict(String office) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getCurrentDistrict(office);
	}
	public ArrayList<SpotInspDTO> getColonyName(String language,
	String wardPatwariId) {
		ArrayList  l1NameList=new ArrayList();
		ArrayList <SpotInspDTO>returnList=null;
		SpotInspDAO da = new SpotInspDAO();
		try{
			l1NameList=da.getColonyName(language,wardPatwariId);
			if(l1NameList!=null&& l1NameList.size()>0)
			{
				returnList=new ArrayList<SpotInspDTO>();
				for(int i=0;i<l1NameList.size();i++)
				{
					ArrayList subList=(ArrayList) l1NameList.get(i);
					SpotInspDTO propDTO= new SpotInspDTO();
					propDTO.setColonyId((String) subList.get(0)+"~"+(String) subList.get(2)+"~"+(String) subList.get(3));
					propDTO.setColonyName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		}catch (Exception e) {
			logger.error(e);
			return null;
		}


	}

	public ArrayList<SpotInspDTO> getWardPatwari(String language,
	String subAreaId,String tehsilId) {
		SpotInspDAO dao = new SpotInspDAO();
		ArrayList  wardPatwariList=null;
		ArrayList <SpotInspDTO>returnList=null;
		try{
			wardPatwariList=dao.getWardPatwari(language,subAreaId,tehsilId);
			if(wardPatwariList!=null&& wardPatwariList.size()>0)
			{
				returnList=new ArrayList<SpotInspDTO>();
				for(int i=0;i<wardPatwariList.size();i++)
				{
					ArrayList subList=(ArrayList) wardPatwariList.get(i);
					SpotInspDTO propDTO= new SpotInspDTO();
					propDTO.setWardIds(((String) subList.get(0)+"~"+(String) subList.get(2)));
					propDTO.setWardPatwariName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		}catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return null;
		}
	}

	/**
	 * getting SRO list values.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getDistSrList(String districtId) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDistSrList(districtId);
	}

	public ArrayList getDistDrList(String districtId) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDistDrList(districtId);
	}

	public ArrayList<SpotInspDTO> getSubArea(String language,
	String areaId,String tehsilId,String urbanFlag) {
		ArrayList  subAreaList=null;
		SpotInspDAO refDAO = new SpotInspDAO();
		ArrayList <SpotInspDTO>returnList=null;
		try{
			subAreaList=refDAO.getSubArea(language,areaId,tehsilId,urbanFlag);
			if(subAreaList!=null&& subAreaList.size()>0)
			{
				returnList=new ArrayList<SpotInspDTO>();
				for(int i=0;i<subAreaList.size();i++)
				{
					ArrayList subList=(ArrayList) subAreaList.get(i);
					SpotInspDTO propDTO= new SpotInspDTO();
					propDTO.setSubAreaId((String) subList.get(0));
					propDTO.setSubAreaName((String) subList.get(1));
					returnList.add(propDTO);

				}

			}

			return returnList;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String getMuncipalFlag(String subAreaId)
	{
		SuppleDocDAO dao = new SuppleDocDAO();
		if(subAreaId.equalsIgnoreCase("5"))
		{
			return "RF";
		}
		else
		{
			String municipalId=dao.getMunicipalId(subAreaId);
			if("1".equalsIgnoreCase(municipalId)||"2".equalsIgnoreCase(municipalId))
			{
				return "NF";
			}
			else if("4".equalsIgnoreCase(municipalId))
			{
				return "RF";
			}
			else
			{
				return "N";
			}
		}
	}



	public ArrayList getSrList(String userId) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSrList(userId);
	}

	public ArrayList getSroList() throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSroList();
	}

	public ArrayList getdeedInstrumentList(String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getdeedInstrumentList(language);
	}

	public ArrayList getRangeList() throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getRangeList();
	}




	public ArrayList getCurrentRangeList() throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getCurrentRangeList();
	}


	public ArrayList getcurrentdeedInstrumentList(String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getcurrentdeedInstrumentList(language);
	}

	public boolean updateCriteriaActivity(String grpid) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.updateCriteriaActivity(grpid);
	}

	public boolean insertCriteriaActivity(String aId, String grpid) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.insertCriteriaActivity(aId,grpid);
	}


	public ArrayList getSubClauseList(String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSubClauseList(language);
	}
	public ArrayList getCurrentSubClauseList(String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getCurrentSubClauseList(language);
	}

	public ArrayList getCurrentSubClauseList1(String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getCurrentSubClauseList1(language);
	}

	public ArrayList getPropertyL2(String propertyId, String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getPropertyL2(propertyId,language);
	}

	public ArrayList getAreaList(String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getAreaList(language);
	}

	public ArrayList getCurrentAreaList(String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getCurrentAreaList(language);
	}

	public ArrayList getpropertyList(String language,String propId) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		if("1".equalsIgnoreCase(propId))
		{
			return dao.getpropertyPlotList(language);
		}
		else if("2".equalsIgnoreCase(propId))
		{
			return dao.getpropertyBuildList(language);

		}
		else
		{
			return dao.getpropertyAgriList(language);

		}
	}

	public ArrayList getCurrentpropertyList(String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getCurrentpropertyList(language);
	}


	public ArrayList getActivityList(String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getActivityList(language);
	}


	/**
	 * Getting District list.
	 * @return list
	 */

	public ArrayList getCriteriaDocument(SpotInspForm spForm)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();




		return dao.getTempData(spForm);

	}
	public ArrayList getCriteriaDocumentPlot(SpotInspForm spForm)throws Exception
	{
		{
			SpotInspDAO dao = new  SpotInspDAO();
			return dao.getTempDataPlot(spForm);
		}
	}
	public ArrayList getCriteriaDocumentAgri(SpotInspForm spForm)throws Exception
	{
		{
			SpotInspDAO dao = new  SpotInspDAO();
			return dao.getTempDataAgri(spForm);
		}



	}
	public ArrayList getCriteriaDocumentBuild(SpotInspForm spForm)throws Exception
	{
		{
			SpotInspDAO dao = new  SpotInspDAO();
			return dao.getTempDataBuilding(spForm);
		}



	}
	public ArrayList getCriteriaDocumentHyperLink(SpotInspForm spForm)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getHyperLinkData(spForm);
	}


	public ArrayList getDocumentReInspection(SpotInspForm spForm)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDocumentReInspection(spForm);
	}

	public ArrayList getAssignCriteriaDocument(SpotInspForm spForm)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getAssignCriteriaDocument(spForm);
	}

	public ArrayList getAssignCriteriaDocumentTest(SpotInspForm spForm)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getAssignCriteriaDocumentTest(spForm);
	}



	public ArrayList getDistrict(String stateId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDistrict(stateId);
	}

	public ArrayList getDistrictZone(String zoneId, String language)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDistrictZone(zoneId,language);
	}

	/**
	 * Getting District list.
	 * @param language
	 * 
	 * @return list
	 */

	public ArrayList getZone(String stateId, String language)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getZone(stateId,language);
	}


	public String getGuidelineRate(String[] param) throws Exception {
		SpotInspBD commonBd = new SpotInspBD();
		return commonBd.getGuidelineRate(param,"1");
	}

	public ArrayList getTehsil(String districtId, String language)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getTehsil(districtId,language);
	}


	public ArrayList getAreaType(String language)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getAreaType(language);
	}


	/**
	 * getting Spot Inspection View Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */


	public ArrayList getSpotViewRes(SpotInspForm form) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotViewRes(form);
	}
	public ArrayList getSpotViewResFinal(SpotInspForm form) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotViewResFinal(form);
	}

	/**
	 * getting Spot Inspection Schedule Results.
	 * @param language
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSpotCompRes(SpotInspForm form, String language) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();

		ArrayList li = dao.getSpotCompRes(form, language);
		ArrayList finalList = new ArrayList();
		for(int i=0;i<li.size();i++)
		{

			ArrayList l = (ArrayList) li.get(i);
			String reg_id = (String) l.get(0);
			String reg_txn_id = dao.getRegCompletionNumberDAO(reg_id);
			boolean check = dao.checkCaseMonitoring(reg_txn_id);
			if(!check)
			{
				finalList.add(l);
			}

		}

		return finalList;
	}

	/**
	 * getting Spot Inspection DR Request Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getDrReqRes(SpotInspForm form) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDrReqRes(form);
	}

	/**
	 * getting Spot Inspection DR Request View Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getDrViewRes(SpotInspForm form) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDrViewRes(form);
	}

	/**
	 * 
	 * getting Spot Inspection View Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotInspViewDet(String applNo)  throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotInspViewDet(applNo);
	}


	/**
	 * Updating  Spot Inspection Schedule  Update  Details.
	 * @param userId
	 * @param funId
	 * @param roleId
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public boolean SpotInspSechUpd(String applNo, String insPlanDate, String roleId, String funId, String userId,String remarks) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.SpotInspSechUpd(applNo, insPlanDate,roleId,funId,userId,remarks);
	}

	/**
	 * Updating  Spot Inspection Completion  Update  Details.
	 * @param userId
	 * @param funId
	 * @param roleId
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 *
	 */

	public boolean SpotInspCompUpd(SpotInspForm form, String roleId, String funId, String userId, HttpServletRequest request)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.SpotInspCompUpdCheckL(form,roleId,funId,userId, request);
	}

	public boolean SpotReInspCompUpd(SpotInspForm form, String roleId, String funId, String userId, HttpServletRequest request)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.SpotReInspCompUpdL(form,roleId,funId,userId, request);
	}
	/**
	 * getting Spot Inspection Schedule Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotInspSechDet(String applNo) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotInspSechDet(applNo);
	}

	/**
	 * getting Spot Inspection Schedule Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotInspCompDet(String applNo) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotInspCompDet(applNo);
	}

	/**
	 * getting Spot Inspection DR Request Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotDrReqDet(String applNo) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotDrReqDet(applNo);
	}

	/**
	 * getting Spot Inspection DR Request View Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getSpotDrViewDet(String applNo) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotDrViewDet(applNo);
	}

	/**
	 * Updating DR Request Details.
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @param _refParam
	 * @return
	 * @throws Exception
	 */

	public boolean updateDrReqDet(String[] param, String roleId, String funId, String userId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.updateDrReqDet(param,roleId,funId,userId);
	}


	/**
	 * getting Property Transaction Id List.
	 * @param regTxnId
	 * @return
	 */

	public ArrayList getPropIdList(String regTxnId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getPropIdList(regTxnId);
	}

	public ArrayList getPropIdListM(String regTxnId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getData(regTxnId);
	}

	public ArrayList getPropIdListL(String regTxnId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDataL(regTxnId);
	}

	public ArrayList getPropIdListLR(String regTxnId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDataLR(regTxnId);
	}

	public ArrayList getPropIdListRe(String regTxnId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getDataRe(regTxnId);
	}

	public ArrayList getNewPropIdList(String regTxnId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getNewPropIdList(regTxnId);
	}

	/**
	 * Inserting spot Inspection request Details.
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @param _refParam
	 * @return
	 * @throws Exception
	 */

	public boolean insSpReqDet(String[] param, String roleId, String funId, String userId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.insSpReqDet(param,roleId,funId,userId);
	}

	public boolean insSpSpotAssignDet(String[] param, String roleId, String funId, String userId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.insSpSpotAssignDet(param,roleId,funId,userId);
	}

	/**
	 * 
	 * @param param
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean updateSpReqDet(SpotInspForm _spForm, String roleId, String funId, String userId)throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.updateSpReqDet(_spForm,roleId,funId,userId);
	}
	/**
	 * getting spot Inpection search result
	 * @param form
	 * @return list
	 */
	public ArrayList getSpotSechRes(SpotInspForm form) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotSechRes(form);
	}



	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSpotAssignmentRequest(SpotInspForm form) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotAssignmentRequest(form);
	}

	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSpotSechRequest(SpotInspForm form) throws Exception
	{
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getSpotSechRequest(form);
	}

	public String getDIGZoneBo(String office) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getDIGZoneDAO(office);
	}

	public ArrayList getDistDIGListBO(String zoneId, String language) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getDistDIGListDAO(zoneId, language);
	}

	public ArrayList getDistListBO(String userId) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getDistListDAO(userId);
	}

	public boolean insertAssignSpotDataBO(HashMap<String, ArrayList<SpotInspDTO>> srMap,
	String roleId, String funId, String userId) {
		SpotInspDAO dao = new SpotInspDAO();

		return dao.insertAssignSpotDataDAO(srMap,roleId,funId,userId);
	}

	public HashMap<String, ArrayList<SpotInspDTO>> getSrLists(Iterator<String> ids) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getSrLists(ids);
	}
	//added by saurav
	/*public HashMap<String, ArrayList<SpotInspDTO>> getDocUserList(ArrayList<SpotInspDTO> edata){
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getDocUserList(edata);
	}*/
	//end saurav
	public String getRegCompletionNumberBO(String reginitno) {

		SpotInspDAO dao = new SpotInspDAO();
		return dao.getRegCompletionNumberDAO(reginitno);
	}

	public ArrayList getRegInfo(String regTxnId) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getRegInfoDAO(regTxnId);
	}

	public ArrayList getReRegInfo(String regTxnId) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getReRegInfoDAO(regTxnId);
	}


	public ArrayList<SpotInspDTO> getDashBoardDetails(SpotInspForm spForm,String userId) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getDashBoardDetails(spForm,userId);
	}

	public ArrayList getDrillDownDashboardValues(String id) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getDrillDownDashboardValues(id);
	}

	public ArrayList getReDrillDownDashboardValues(String id) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getReDrillDownDashboardValues(id);
	}

	public boolean updateStatus(SpotInspForm spForm) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.updateStatus(spForm);
	}

	public ArrayList getReInspectionDocument(SpotInspForm spForm) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getReInspectionDocument(spForm);
	}

	public String getSelectPropertyName(String propId,String langauge)
	{
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getSelectPropertyName(propId,langauge);
	}

	public String getPropIdSearch(String l1) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getPropIdSearch(l1);
	}

	public String getPostInspectionComments(String spotTxnId) throws Exception{
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getPostInspectionComments(spotTxnId);
	}

	public ArrayList getOtherSpotInsDtls(String regNo) {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.getOtherSpotInsDtls(regNo);
	}

	public String getInspectionStatus(String id) {
		SpotInspDAO dao = new  SpotInspDAO();
		return dao.getInspectionStatus(id);
	}

	public boolean validateDate(String fromDate, String toDate)
	throws Exception {
		SpotInspDAO dao = new SpotInspDAO();
		return dao.validateDate(fromDate, toDate);
	}
	
	


	public String duration()throws Exception{

		SpotInspDAO dao = new SpotInspDAO();
		return dao.duration();
	}


}
