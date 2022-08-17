
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
 * FILE NAME: GuidelinePreparationForm.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    12th APRIL 2008 
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE FORM BEAN FOR GUIDELINE PREPARATION 
 */

package com.wipro.igrs.guideline.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.wipro.igrs.guideline.dto.GuidelineDTO;
import org.apache.struts.action.ActionForm;

/**
 * @author NIHAR M.
 *
 */
public class GuidelinePreparationForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private GuidelineDTO guideDTO = new GuidelineDTO();
	private ArrayList districtList = new ArrayList();
	private ArrayList tehsilList = new ArrayList();
	private ArrayList completeTehsilList = new ArrayList();
	private ArrayList wardList = new ArrayList();
	private ArrayList patwariList = new ArrayList();
	private ArrayList mohallaList = new ArrayList();
	private ArrayList completeWardList = new ArrayList();
	private ArrayList completePatwariList = new ArrayList();
	private ArrayList completeMohallaList = new ArrayList();
	private ArrayList completeVillageList = new ArrayList();
	private ArrayList combination  = new ArrayList();
	private ArrayList mappedSubList = new ArrayList();
	private ArrayList unMappedSubList = new ArrayList();
	private ArrayList SubClauseList = new ArrayList();
	private ArrayList propDetailsList = new ArrayList();
	private ArrayList subClauseSelectedList = new ArrayList();
	private ArrayList availableDraftList = new ArrayList();
	private HashMap guidelineDataMap = new HashMap();
	private LinkedHashMap guidelineDataMapPraroop1 = new LinkedHashMap();
	private LinkedHashMap guidelineDataMapPraroop2 = new LinkedHashMap();
	private LinkedHashMap guidelineDataMapPraroop3 = new LinkedHashMap();
	private ArrayList propTypePraroop1 = new ArrayList();
	private ArrayList propTypePraroop2 = new ArrayList();
	private ArrayList propTypePraroop3 = new ArrayList();
	private ArrayList newMohallas = new ArrayList();
	private String errorMsg;
	private String subAreaId;

	public ArrayList getAvailableDraftList() {
		return availableDraftList;
	}

	public void setAvailableDraftList(ArrayList availableDraftList) {
		this.availableDraftList = availableDraftList;
	}

	private String[] propDetailsList1 = new String[50];
	private String[] subClauseSelectedList1 = new String[50];
	
	
	

	public ArrayList getCombination() {
		return combination;
	}

	public void setCombination(ArrayList combination) {
		this.combination = combination;
	}

	public ArrayList getCompleteVillageList() {
		return completeVillageList;
	}

	public void setCompleteVillageList(ArrayList completeVillageList) {
		this.completeVillageList = completeVillageList;
	}

	public ArrayList getCompleteMohallaList() {
		return completeMohallaList;
	}

	public void setCompleteMohallaList(ArrayList completeMohallaList) {
		this.completeMohallaList = completeMohallaList;
	}

	public ArrayList getCompleteWardList() {
		return completeWardList;
	}

	public void setCompleteWardList(ArrayList completeWardList) {
		this.completeWardList = completeWardList;
	}

	public ArrayList getCompletePatwariList() {
		return completePatwariList;
	}

	public void setCompletePatwariList(ArrayList completePatwariList) {
		this.completePatwariList = completePatwariList;
	}

	public ArrayList getCompleteTehsilList() {
		return completeTehsilList;
	}

	public void setCompleteTehsilList(ArrayList completeTehsilList) {
		this.completeTehsilList = completeTehsilList;
	}

	private ArrayList areaTypeList = new ArrayList();
	private ArrayList labelWardPatwari = new ArrayList();
	private ArrayList villageList = new ArrayList();
	private ArrayList financialYearList = new ArrayList();
	
	
	//----######added by satbir kumar-----
	
	
	
	private ArrayList guidelineIdList = new ArrayList();
	
	
	//----------end of addition--------
	
	
	
	private String guideLineID;

	public String getGuideLineID() {
		return guideLineID;
	}

	public void setGuideLineID(String guideLineID) {
		this.guideLineID = guideLineID;
	}

	private HashMap guidelineStatusList = new HashMap();
	
	private ArrayList versionList = new ArrayList();

	public ArrayList getVersionList() {
		return versionList;
	}

	public void setVersionList(ArrayList versionList) {
		this.versionList = versionList;
	}

	private String totalNumberOfResults;
	
	//financial Year list for guideline creation
	public ArrayList getFinancialYearList() {
		return financialYearList;
	}

	public HashMap getGuidelineStatusList() {
		return guidelineStatusList;
	}

	public void setGuidelineStatusList(HashMap guidelineStatusList) {
		this.guidelineStatusList = guidelineStatusList;
	}

	public void setFinancialYearList(ArrayList financialYearList) {
		this.financialYearList = financialYearList;
	}
	
	//end

	private String start;

	private ArrayList mohallaAllDetails;
	private ArrayList indPropDetails;
	private ArrayList indPropertySubTypeDetails;

	private String[] radioGuideLineValue;

	/**
	 * @return radioGuideLineValue
	 */
	public String[] getRadioGuideLineValue() {
		return radioGuideLineValue;
	}

	/**
	 * @param radioGuideLineValue
	 */
	public void setDisableRdsUserId(String[] radioGuideLineValue) {
		this.radioGuideLineValue = radioGuideLineValue;
	}


	/**
	 * @return ArrayList
	 */
	public ArrayList getIndPropertySubTypeDetails() {
		return indPropertySubTypeDetails;
	}

	/**
	 * @param indPropertySubTypeDetails
	 */
	public void setIndPropertySubTypeDetails(ArrayList indPropertySubTypeDetails) {
		this.indPropertySubTypeDetails = indPropertySubTypeDetails;
	}

	/**
	 * @return districtList
	 */
	public ArrayList getDistrictList() {
		return districtList;
	}

	/**
	 * @param districtList
	 */
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	/**
	 * @return tehsilList
	 */
	public ArrayList getTehsilList() {
		return tehsilList;
	}

	/**
	 * @param tehsilList
	 */
	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}

	/**
	 * @return guideDTO
	 */
	public GuidelineDTO getGuideDTO() {
		return guideDTO;
	}
	/**
	 * @param guideDTO
	 */
	public void setGuideDTO(GuidelineDTO guideDTO) {
		this.guideDTO = guideDTO;
	}

	/**
	 * @return wardList
	 */
	public ArrayList getWardList() {
		return wardList;
	}

	/**
	 * @param wardList
	 */
	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}

	/**
	 * @return mohallaList
	 */
	public ArrayList getMohallaList() {
		return mohallaList;
	}

	/**
	 * @param mohallaList
	 */
	public void setMohallaList(ArrayList mohallaList) {
		this.mohallaList = mohallaList;
	}

	/**
	 * @return totalNumberOfResults
	 */
	public String getTotalNumberOfResults() {
		return totalNumberOfResults;
	}

	/**
	 * @param totalNumberOfResults
	 */
	public void setTotalNumberOfResults(String totalNumberOfResults) {
		this.totalNumberOfResults = totalNumberOfResults;
	}

	/**
	 * @return start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return areaTypeList
	 */
	public ArrayList getAreaTypeList() {
		return areaTypeList;
	}

	/**
	 * @param areaTypeList
	 */
	public void setAreaTypeList(ArrayList areaTypeList) {
		this.areaTypeList = areaTypeList;
	}

	/**
	 * @return labelWardPatwari
	 */
	public ArrayList getLabelWardPatwari() {
		return labelWardPatwari;
	}

	/**
	 * @param labelWardPatwari
	 */
	public void setLabelWardPatwari(ArrayList labelWardPatwari) {
		this.labelWardPatwari = labelWardPatwari;
	}

	/**
	 * @return patwariList
	 */
	public ArrayList getPatwariList() {
		return patwariList;
	}

	/**
	 * @param patwariList
	 */
	public void setPatwariList(ArrayList patwariList) {
		this.patwariList = patwariList;
	}

	/**
	 * @return villageList
	 */
	public ArrayList getVillageList() {
		return villageList;
	}

	/**
	 * @param villageList
	 */
	public void setVillageList(ArrayList villageList) {
		this.villageList = villageList;
	}

	/**
	 * @return serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return mohallaAllDetails
	 */
	public ArrayList getMohallaAllDetails() {
		return mohallaAllDetails;
	}

	/**
	 * @param mohallaAllDetails
	 */
	public void setMohallaAllDetails(ArrayList mohallaAllDetails) {
		this.mohallaAllDetails = mohallaAllDetails;
	}

	/**
	 * @return indPropDetails
	 */
	public ArrayList getIndPropDetails() {
		return indPropDetails;
	}

	/**
	 * @param indPropDetails
	 */
	public void setIndPropDetails(ArrayList indPropDetails) {
		this.indPropDetails = indPropDetails;
	}

	/**
	 * @param index
	 * @return GuidelineDTO
	 */
	public GuidelineDTO getGuidelineDTO(int index)	{
		int size = this.getMohallaAllDetails().size();
		GuidelineDTO guidelineDTO = new GuidelineDTO();
		if(index < size){
			return (GuidelineDTO)this.getMohallaAllDetails().get(index);
		}
		return guidelineDTO;
	}

	/**
	 * @param index
	 * @param guidelineDTO
	 */
	public void setGuidelineDTO(int index, GuidelineDTO guidelineDTO) {
		int size = this.getMohallaAllDetails().size();
		if(index < size){
			this.getMohallaAllDetails().set(index,guidelineDTO);
		}
	}

	/**
	 * 
	 * @return mappedSubList
	 */
	public ArrayList getMappedSubList() {
		return mappedSubList;
	}

	/**
	 * 
	 * @param mappedSubList
	 */
	public void setMappedSubList(ArrayList mappedSubList) {
		this.mappedSubList = mappedSubList;
	}

	/**
	 * 
	 * @return unMappedSubList
	 */
	public ArrayList getUnMappedSubList() {
		return unMappedSubList;
	}

	/**
	 * 
	 * @param unMappedSubList
	 */
	public void setUnMappedSubList(ArrayList unMappedSubList) {
		this.unMappedSubList = unMappedSubList;
	}

	/**
	 * 
	 * @return SubClauseList
	 */
	public ArrayList getSubClauseList() {
		return SubClauseList;
	}

	/**
	 * 
	 * @param subClauseList
	 */
	public void setSubClauseList(ArrayList subClauseList) {
		SubClauseList = subClauseList;
	}
	
	
	
	/*
	 * 
	 * SUBCLAUSE
	 */
	/*private String subId1;
	private String subId2;
	private String subId3;
	private String subId4;
	private String subId5;
	private String subId6;
	private String subId7;
	private String subId8;
	private String subId9;
	private String subId10;
	private String subId11;
	private String subId12;
	private String subId13;
	private String subId14;
	private String subId15;
	private String subId16;
	private String subId17;




	public String getSubId1() {
		return subId1;
	}

	public void setSubId1(String subId1) {
		this.subId1 = subId1;
	}

	public String getSubId2() {
		return subId2;
	}

	public void setSubId2(String subId2) {
		this.subId2 = subId2;
	}

	public String getSubId3() {
		return subId3;
	}

	public void setSubId3(String subId3) {
		this.subId3 = subId3;
	}

	public String getSubId4() {
		return subId4;
	}

	public void setSubId4(String subId4) {
		this.subId4 = subId4;
	}

	public String getSubId5() {
		return subId5;
	}

	public void setSubId5(String subId5) {
		this.subId5 = subId5;
	}

	public String getSubId6() {
		return subId6;
	}

	public void setSubId6(String subId6) {
		this.subId6 = subId6;
	}

	public String getSubId7() {
		return subId7;
	}

	public void setSubId7(String subId7) {
		this.subId7 = subId7;
	}

	public String getSubId9() {
		return subId9;
	}

	public void setSubId9(String subId9) {
		this.subId9 = subId9;
	}

	public String getSubId10() {
		return subId10;
	}

	public void setSubId10(String subId10) {
		this.subId10 = subId10;
	}

	public String getSubId11() {
		return subId11;
	}

	public void setSubId11(String subId11) {
		this.subId11 = subId11;
	}

	public String getSubId12() {
		return subId12;
	}

	public void setSubId12(String subId12) {
		this.subId12 = subId12;
	}

	public String getSubId13() {
		return subId13;
	}

	public void setSubId13(String subId13) {
		this.subId13 = subId13;
	}

	public String getSubId14() {
		return subId14;
	}

	public void setSubId14(String subId14) {
		this.subId14 = subId14;
	}

	public String getSubId15() {
		return subId15;
	}

	public void setSubId15(String subId15) {
		this.subId15 = subId15;
	}

	public String getSubId16() {
		return subId16;
	}

	public void setSubId16(String subId16) {
		this.subId16 = subId16;
	}

	public String getSubId17() {
		return subId17;
	}

	public void setSubId17(String subId17) {
		this.subId17 = subId17;
	}

	public String getSubId8() {
		return subId8;
	}

	public void setSubId8(String subId8) {
		this.subId8 = subId8;
	}*/

	/**
	 * @return propDetailsList
	 */
	public ArrayList getPropDetailsList() {
		return propDetailsList;
	}

	/**
	 * 
	 * @param propDetailsList
	 */
	public void setPropDetailsList(ArrayList propDetailsList) {
		this.propDetailsList = propDetailsList;
	}

	/**
	 * 
	 * @return subClauseSelectedList
	 */
	public ArrayList getSubClauseSelectedList() {
		return subClauseSelectedList;
	}

	/**
	 * 
	 * @param subClauseSelectedList
	 */
	public void setSubClauseSelectedList(ArrayList subClauseSelectedList) {
		this.subClauseSelectedList = subClauseSelectedList;
	}

	private String index;




	/**
	 * 
	 * @return index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * 
	 * @param index
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	public String[] getPropDetailsList1() {
		return propDetailsList1;
	}

	public void setPropDetailsList1(String[] propDetailsList1) {
		this.propDetailsList1 = propDetailsList1;
	}

	public String[] getSubClauseSelectedList1() {
		return subClauseSelectedList1;
	}

	public void setSubClauseSelectedList1(String[] subClauseSelectedList1) {
		this.subClauseSelectedList1 = subClauseSelectedList1;
	}

	public HashMap getGuidelineDataMap() {
		return guidelineDataMap;
	}

	public void setGuidelineDataMap(HashMap guidelineDataMap) {
		this.guidelineDataMap = guidelineDataMap;
	}

	

	public LinkedHashMap getGuidelineDataMapPraroop1() {
		return guidelineDataMapPraroop1;
	}

	public void setGuidelineDataMapPraroop1(LinkedHashMap guidelineDataMapPraroop1) {
		this.guidelineDataMapPraroop1 = guidelineDataMapPraroop1;
	}

	

	public LinkedHashMap getGuidelineDataMapPraroop2() {
		return guidelineDataMapPraroop2;
	}

	public void setGuidelineDataMapPraroop2(LinkedHashMap guidelineDataMapPraroop2) {
		this.guidelineDataMapPraroop2 = guidelineDataMapPraroop2;
	}

	public LinkedHashMap getGuidelineDataMapPraroop3() {
		return guidelineDataMapPraroop3;
	}

	public void setGuidelineDataMapPraroop3(LinkedHashMap guidelineDataMapPraroop3) {
		this.guidelineDataMapPraroop3 = guidelineDataMapPraroop3;
	}

	public ArrayList getPropTypePraroop1() {
		return propTypePraroop1;
	}

	public void setPropTypePraroop1(ArrayList propTypePraroop1) {
		this.propTypePraroop1 = propTypePraroop1;
	}

	public ArrayList getPropTypePraroop2() {
		return propTypePraroop2;
	}

	public void setPropTypePraroop2(ArrayList propTypePraroop2) {
		this.propTypePraroop2 = propTypePraroop2;
	}

	public ArrayList getPropTypePraroop3() {
		return propTypePraroop3;
	}

	public void setPropTypePraroop3(ArrayList propTypePraroop3) {
		this.propTypePraroop3 = propTypePraroop3;
	}

	//ADDED BY SIMRAN
	
	private ArrayList subAreaList = new ArrayList();

	public ArrayList getSubAreaList() {
		return subAreaList;
	}

	public void setSubAreaList(ArrayList subAreaList) {
		this.subAreaList = subAreaList;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setGuidelineIdList(ArrayList guidelineIdList) {
		this.guidelineIdList = guidelineIdList;
	}

	public ArrayList getGuidelineIdList() {
		return guidelineIdList;
	}

	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}

	public String getSubAreaId() {
		return subAreaId;
	}

	public void setNewMohallas(ArrayList newMohallas) {
		this.newMohallas = newMohallas;
	}

	public ArrayList getNewMohallas() {
		return newMohallas;
	}

	

}
