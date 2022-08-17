package com.wipro.igrs.deedDraft.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.deedDraft.dao.DeedDraftViewEditDAO;
import com.wipro.igrs.deedDraft.dto.DeedDraftAppDTO;
import com.wipro.igrs.deedDraft.dto.DeedDraftDTO;

public class DeedDraftViewEditBD {
	private Logger logger = Logger.getLogger(DeedDraftViewEditBD.class);
	DeedDraftViewEditDAO viewDAO = new DeedDraftViewEditDAO();

	/**
	 * @param userId
	 * @param lang
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDraftDeedDetailsForDashboard(String userId, String lang) throws Exception {
		ArrayList finalList = new ArrayList();
		ArrayList list = viewDAO.getDraftDeedDetailsForDashboard(userId);
		for (int i = 0; i < list.size(); i++) {
			ArrayList subList = (ArrayList) list.get(i);
			DeedDraftDTO dto = new DeedDraftDTO();
			dto.setDeedId((String) subList.get(0));
			dto.setDeedName((String) subList.get(1));
			String status = (String) subList.get(2);
			String createdBy = (String) subList.get(3);
			String createdDate = (String) subList.get(4);
			String type = (String) subList.get(5);
			if (status.equalsIgnoreCase("A") || status.equalsIgnoreCase("I")) {
				if (lang.equalsIgnoreCase("hi"))
					dto.setDeedStatus("Not Consumed");
				else
					dto.setDeedStatus("Not Consumed");
			} else {
				if (lang.equalsIgnoreCase("hi"))
					dto.setDeedStatus("Consumed");
				else
					dto.setDeedStatus("Consumed");
			}
			if (createdBy == null) {
				if (lang.equalsIgnoreCase("hi"))
					dto.setDeedType("Yes");
				else
					dto.setDeedType("Yes");
			} else {
				if (lang.equalsIgnoreCase("hi"))
					dto.setDeedType("No");
				else
					dto.setDeedType("No");
			}
			if ("R".equals(type)) {
				if (lang.equalsIgnoreCase("hi"))
					dto.setDeedAppType("पंजीयन प्रक्रिया");
				else
					dto.setDeedAppType("Registration");
			} else if ("E".equals(type)) {
				if (lang.equalsIgnoreCase("hi"))
					dto.setDeedAppType("ई - स्टाम्प");
				else
					dto.setDeedAppType("E-stamp");
			} else if ("A".equals(type)) {
				if (lang.equalsIgnoreCase("hi"))
					dto.setDeedAppType("न्यायनिर्णयन");
				else
					dto.setDeedAppType("Adjudication");
			} else {
				dto.setDeedAppType("--");
			}
			dto.setDeedDate(createdDate);
			finalList.add(dto);
		}
		logger.debug("size of list in bd-->" + finalList.size());
		return finalList;
	}

	public ArrayList getDraftDeedDeleteDetailsForDashboard(String userId, String lang) throws Exception {
		ArrayList finalList = new ArrayList();
		ArrayList list = viewDAO.getDraftDeedDeleteDetailsForDashboard(userId);
		for (int i = 0; i < list.size(); i++) {
			ArrayList subList = (ArrayList) list.get(i);
			DeedDraftDTO dto = new DeedDraftDTO();
			dto.setDeedId((String) subList.get(0));
			dto.setDeedName((String) subList.get(1));
			String createdDate = (String) subList.get(2);
			String initiationNumber = (String) subList.get(3);
			//String createdDate = (String)subList.get(4);
			dto.setDeedDate(createdDate);
			dto.setInitiationNumber(initiationNumber);
			finalList.add(dto);
		}
		logger.debug("size of list in bd-->" + finalList.size());
		return finalList;
	}

	public boolean updateDeedDraftStatus(String deedDraftId) {
		return viewDAO.updateDeedDraftStatus(deedDraftId);
	}

	public DeedDraftDTO getDeedContent(String deedDraftId, String pageId, DeedDraftDTO dto) throws Exception {
		ArrayList subList = viewDAO.getDeedContent(deedDraftId, pageId);
		//	for(int i= 0 ; i < subList.size() ;i++)
		//	{
		//ArrayList subList = (ArrayList)list.get(i);
		dto.setDeedName((String) subList.get(0));
		dto.setDeedContent((String) subList.get(1));
		String status = (String) subList.get(2);;
		String createdBy = (String) subList.get(3);
		if (status.equalsIgnoreCase("D") || createdBy == null)
			dto.setEditCheck("N");
		else
			dto.setEditCheck("Y");
		//}
		return dto;
	}

	public String getMaxPageNumber(String deedDraftId) throws Exception {
		return viewDAO.getMaxPageNumber(deedDraftId);
	}

	public String getAdjudicationFlag(String applicationId) throws Exception {
		return viewDAO.getAdjudicationFlag(applicationId);
	}

	public boolean saveDeedDraftDetails(DeedDraftDTO ddto, String userID, String appType, String applicationId) throws Exception {
		return viewDAO.saveDeedDraftDetails(ddto, userID, appType, applicationId);
	}

	public boolean checkDeedRecordOnNext(String deedDraftId, String pageId) throws Exception {
		return viewDAO.checkDeedRecordOnNext(deedDraftId, pageId);
	}

	public boolean changeStatus(String deedDraftId) throws Exception {
		return viewDAO.changeStatus(deedDraftId);
	}

	public boolean saveDeedDraftPath(DeedDraftDTO ddto, String userID) throws Exception {
		return viewDAO.saveDeedDraftPath(ddto);
	}

	public boolean deleteDeed(String[] deedId) {
		return viewDAO.deleteDeed(deedId);
	}

	//saurav
	public String getRegTxnId(String deedAppId) throws Exception {
		return viewDAO.getRegTxnId(deedAppId);
	}

	public String getAppType(String deedAppId) throws Exception {
		return viewDAO.getAppType(deedAppId);
	}

	public String getMasterCheck(String deedAppId) throws Exception {
		return viewDAO.getMasterCheck(deedAppId);
	}

	public String getDeedStatus(String deedAppId) throws Exception {
		return viewDAO.getDeedStatus(deedAppId);
	}

	public ArrayList checkValidRegID(String appID) throws Exception {
		return viewDAO.checkValidRegID(appID);
	}

	public ArrayList getPropdetails(String appId) throws Exception {
		return viewDAO.getPropdetails(appId);
	}

	public ArrayList getDutyDetails(String appId) throws Exception {
		return viewDAO.getDutyDetails(appId);
	}

	public ArrayList getConsenterDetails(String regTxnId, String userId) throws Exception {
		return viewDAO.getConsenterDetails(regTxnId, userId);
	}

	public ArrayList getAppDetails(String appId) throws Exception {
		return viewDAO.getAppDetails(appId);
	}

	public String getPropRelated(String deedId, String instId) throws Exception {
		return viewDAO.getPropRelated(deedId, instId);
	}

	public String getPropOptionalCheck(String deedId, String instId) throws Exception {
		return viewDAO.getPropOptionalCheck(deedId, instId);
	}

	public String getPropValCheck(String deedId, String instId) throws Exception {
		return viewDAO.getPropValCheck(deedId, instId);
	}

	public ArrayList getExemption(String appId, String type) throws Exception {
		return viewDAO.getExemption(appId, type);
	}

	public ArrayList getValuationDetails(String params, String language) throws Exception {
		return viewDAO.getValuationDetails(params, language);
	}

	public String getPropType(String id) throws Exception {
		return viewDAO.getPropType(id);
	}

	public ArrayList getKhasraDetails(String propertyId) throws Exception {
		return viewDAO.getKhasraDetails(propertyId);
	}

	public ArrayList getPropDutyDetails(String dutyTxnId, String regTxnId, String propertId) throws Exception {
		return viewDAO.getPropDutyDetails(dutyTxnId, regTxnId, propertId);
	}

	public ArrayList getUserEnterableValue(String dutyTxnId, String propDutyId, String language) throws Exception {
		return viewDAO.getUserEnterableValue(dutyTxnId, propDutyId, language);
	}

	public ArrayList getbuildingView(String valId, String language) throws Exception {
		ArrayList build = viewDAO.getbuildingView(valId, language);
		return build;
	}
	//added for construction slab changes by saurav
	public String getConstructionSlabDesc(String id, String language) throws Exception {
		return viewDAO.getConstructionSlabDesc(id, language);
	}
	public ArrayList getconstSlabDetail(String id, String language) throws Exception{
		return viewDAO.getconstSlabDetail(id,language);
	}
	//construction slab changes end
	public ArrayList getClauseListAgriConst(String language, String agriTxnId) throws Exception {
		return viewDAO.getClauseListAgriConst(language, agriTxnId);
	}

	public ArrayList getBuildingDetailsAgri(String agriTxnId, String language) throws Exception {
		return viewDAO.getBuildingDetailsAgri(agriTxnId, language);
	}

	public ArrayList getFloorList(String buildingTxnId, String language) throws Exception {
		return viewDAO.getFloorList(buildingTxnId, language);
	}

	public ArrayList getAgriDetails(String valId, String language) throws Exception {
		ArrayList retList = new ArrayList();
		ArrayList subClauseList = new ArrayList();
		DeedDraftAppDTO ddto = null;
		subClauseList = viewDAO.getAgriDetails(valId, language);
		for (int i = 0; i < subClauseList.size(); i++) {
			ArrayList subList = (ArrayList) subClauseList.get(i);
			ddto = new DeedDraftAppDTO();
			ddto.setDistrictName((String) subList.get(0));
			ddto.setTehsilName((String) subList.get(1));
			ddto.setAreaTypeName((String) subList.get(2));
			ddto.setSubAreaTypeName((String) subList.get(3));
			ddto.setWardName((String) subList.get(4));
			ddto.setColonyName((String) subList.get(5));
			ddto.setCommonAgriSingleBuyer((String) subList.get(6));
			ddto.setCommonAgriSameFamily(subList.get(7) == null ? "-" : subList.get(7).toString());
			ddto.setCommonAgriBuyerCount(subList.get(8) == null ? "-" : subList.get(8).toString());
			ddto.setCommonAgriTreePresent(subList.get(9) == null ? "-" : subList.get(9).toString());
			ddto.setCommonAgriDiscloseShare(subList.get(10) == null ? "-" : subList.get(10).toString());
			ddto.setAreaId((String) subList.get(11));
			retList.add(ddto);
		}
		return subClauseList;
	}

	public ArrayList getAgriPropDetails(String valId, String language) throws Exception {
		ArrayList retList = new ArrayList();
		DeedDraftAppDTO ddto = null;
		ArrayList agriPropDetailsList = new ArrayList();
		try {
			agriPropDetailsList = viewDAO.getagriPropDetailsList(valId, language);
			for (int i = 0; i < agriPropDetailsList.size(); i++) {
				ArrayList subList = (ArrayList) agriPropDetailsList.get(i);
				ddto = new DeedDraftAppDTO();
				ddto.setCommonAgriTxnid(subList.get(0) == null ? "-" : subList.get(0).toString());
				ddto.setCommonAgriSubTypeId(subList.get(1) == null ? "-" : subList.get(1).toString());
				ddto.setCommonAgriPropSubTypeName(subList.get(2) == null ? "-" : subList.get(2).toString());
				ddto.setCommonTotalUndivArea(subList.get(3) == null ? "-" : subList.get(3).toString());
				ddto.setCommonTotalUnirriOneCrop(subList.get(4) == null ? "-" : subList.get(4).toString());
				ddto.setCommonTotalUnirriTwoCrop(subList.get(5) == null ? "-" : subList.get(5).toString());
				ddto.setCommonTotalIrrigatedArea(subList.get(6) == null ? "-" : subList.get(6).toString());
				ddto.setCommonAgriConstruction(subList.get(7) == null ? "-" : subList.get(7).toString());
				ddto.setCommonTotalDivArea(subList.get(8) == null ? "-" : subList.get(8).toString());
				ddto.setCommonTotalResiArea(subList.get(9) == null ? "-" : subList.get(9).toString());
				ddto.setCommonTotalCommArea(subList.get(10) == null ? "-" : subList.get(10).toString());
				ddto.setCommonTotalIndArea(subList.get(11) == null ? "-" : subList.get(11).toString());
				ddto.setCommonTotalEduArea(subList.get(12) == null ? "-" : subList.get(12).toString());
				ddto.setCommonTotalHealthArea(subList.get(13) == null ? "-" : subList.get(13).toString());
				ddto.setCommonTotalOtherArea(subList.get(14) == null ? "-" : subList.get(14).toString());
				ddto.setCommonAgriEducationTcp(subList.get(15) == null ? "-" : subList.get(15).toString());
				ddto.setCommonAgriHealthTcp(subList.get(16) == null ? "-" : subList.get(16).toString());
				retList.add(ddto);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return agriPropDetailsList;
	}

	public ArrayList getAgriTreeDetails(String valId, String language) throws Exception {
		ArrayList agriTreeDetailsList = new ArrayList();
		ArrayList retList = new ArrayList();
		DeedDraftAppDTO ddto = null;
		try {
			agriTreeDetailsList = viewDAO.getAgriTreeDetailsList(valId, language);
			for (int i = 0; i < agriTreeDetailsList.size(); i++) {
				ArrayList subList = (ArrayList) agriTreeDetailsList.get(i);
				ddto = new DeedDraftAppDTO();
				ddto.setCommonAgriTreeCount((String) subList.get(1));
				if (language.equalsIgnoreCase("en")) {
					ddto.setCommonAgriTreeName((String) subList.get(0));
				} else {
					ddto.setCommonAgriTreeName((String) subList.get(0));
				}
				retList.add(ddto);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return agriTreeDetailsList;
	}

	public ArrayList getClauseList(String language, String valId) throws Exception {
		return viewDAO.getClauseList(language, valId);
	}
}
