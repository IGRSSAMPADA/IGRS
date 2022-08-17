package com.wipro.igrs.common.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.dao.CommonDAO;
import com.wipro.igrs.common.dto.CommonDTO;
import com.wipro.igrs.common.form.CommonForm;



public class CommonBO {
	private static Logger logger = org.apache.log4j.Logger.getLogger(CommonBO.class);

	private final CommonDAO commonDAO;

	public CommonBO() throws Exception
	{
		commonDAO= new CommonDAO();

	}
	public CommonDTO getplotPropertyDetails(String language, String id) {

		CommonDTO cmDTO=new CommonDTO();
		ArrayList  propertyDetails=null;

		ArrayList <CommonDTO>returnList=null;
		try{
			propertyDetails= commonDAO.getplotPropertyDetails(language,id);
			if(propertyDetails!=null&& propertyDetails.size()>0)
			{
				returnList=new ArrayList<CommonDTO>();
				for(int i=0;i<propertyDetails.size();i++)
				{
					ArrayList subList=(ArrayList) propertyDetails.get(i);
					CommonDTO commonDTO= new CommonDTO();
					commonDTO.setDistrict((String) subList.get(0));
					commonDTO.setTehsil((String) subList.get(1));
					commonDTO.setArea((String) subList.get(2));
					commonDTO.setSubArea((String) subList.get(3));
					commonDTO.setWardPatwari((String) subList.get(4));
					commonDTO.setColony((String) subList.get(5));
					commonDTO.setCommonPlotTotalArea((String) subList.get(6));

					//commonDTO.setCommonPlotResidentialArea((String) subList.get(7));

					commonDTO.setCommonPlotResidentialArea(subList.get(7)==null ?"-":subList.get(7).toString());

					//commonDTO.setCommonPlotCommercialArea((String) subList.get(8));

					commonDTO.setCommonPlotCommercialArea(subList.get(8)==null ?"-":subList.get(8).toString());

					//commonDTO.setCommonPlotIndustrialArea((String) subList.get(9));

					commonDTO.setCommonPlotIndustrialArea(subList.get(9)==null ?"-":subList.get(9).toString());

					//commonDTO.setCommonPlotEducationalArea((String) subList.get(10));

					commonDTO.setCommonPlotEducationalArea(subList.get(10)==null ?"-":subList.get(10).toString());

					//commonDTO.setCommonPlotHealthArea((String) subList.get(11));

					commonDTO.setCommonPlotHealthArea(subList.get(11)==null ?"-":subList.get(11).toString());

					//commonDTO.setCommonPlotOtherArea((String) subList.get(12));

					commonDTO.setCommonPlotOtherArea(subList.get(12)==null ?"-":subList.get(12).toString());

					commonDTO.setCommonEducTncp((String) subList.get(13));
					commonDTO.setCommonHealthTncp((String) subList.get(14));
					commonDTO.setAreaId((String) subList.get(15));
					commonDTO.setCommonResiFlag((String) subList.get(16));
					commonDTO.setCommonResiArea((String) subList.get(17));
					cmDTO.setCmDTO(commonDTO);

				}

			}


		}catch (Exception e) {
			logger.error(e);
			return cmDTO;
		}
		return cmDTO;



	}

	public ArrayList<CommonDTO> getallSubClauseList(String language, String id, CommonDTO commonDTO1) {
		ArrayList SubClauseList=new ArrayList();
		ArrayList retList=new ArrayList();

		try{
			SubClauseList=commonDAO.getallSubClauseList(language,id,commonDTO1);
			for(int i=0;i<SubClauseList.size();i++){
				ArrayList subList=(ArrayList)SubClauseList.get(i);
				CommonDTO commonDTO= new CommonDTO();
				commonDTO.setSubClauseId((String) subList.get(1));
				if(language.equalsIgnoreCase("en")){
					commonDTO.setSubClauseName((String) subList.get(0));
				}else{
					commonDTO.setSubClauseName((String) subList.get(0));
				}
				retList.add(commonDTO);
			}
		}catch (Exception e) {
			logger.error(e);
		}
		return retList;
	}
	public ArrayList<CommonDTO> getallSubClauseListBuildAgri(String language, String id, CommonDTO commonDTO1) {
		ArrayList SubClauseList=new ArrayList();
		ArrayList retList=new ArrayList();

		try{
			SubClauseList=commonDAO.getallSubClauseListBuildAgri(language,id,commonDTO1);
			for(int i=0;i<SubClauseList.size();i++){
				ArrayList subList=(ArrayList)SubClauseList.get(i);
				CommonDTO commonDTO= new CommonDTO();
				commonDTO.setSubClauseId((String) subList.get(1));
				if(language.equalsIgnoreCase("en")){
					commonDTO.setSubClauseName((String) subList.get(0));
				}else{
					commonDTO.setSubClauseName((String) subList.get(0));
				}
				retList.add(commonDTO);
			}
		}catch (Exception e) {
			logger.error(e);
		}
		return retList;
	}

	public ArrayList getagriAreaDetailsList(String language, String id,
	CommonForm commonForm) {
		ArrayList SubClauseList=new ArrayList();
		CommonDTO cmDTO=new CommonDTO();
		ArrayList retList=new ArrayList();
		try{
			SubClauseList=commonDAO.getagriAreaDetailsList(language,id,commonForm);
			for(int i=0;i<SubClauseList.size();i++){
				ArrayList subList=(ArrayList)SubClauseList.get(i);

				CommonDTO commonDTO1= new CommonDTO();
				commonDTO1.setDistrict((String) subList.get(0));
				commonDTO1.setTehsil((String) subList.get(1));
				commonDTO1.setArea((String) subList.get(2));
				commonDTO1.setSubArea((String) subList.get(3));
				commonDTO1.setWardPatwari((String) subList.get(4));
				commonDTO1.setColony((String) subList.get(5));
				commonDTO1.setCommonAgriSingleBuyer((String) subList.get(6));
				commonDTO1.setCommonAgriSameFamily(subList.get(7)==null ?"-":subList.get(7).toString());
				commonDTO1.setCommonAgriBuyerCount(subList.get(8)==null ?"-":subList.get(8).toString());
				commonDTO1.setCommonAgriTreePresent(subList.get(9)==null ?"-":subList.get(9).toString());
				commonDTO1.setCommonAgriDiscloseShare(subList.get(10)==null ?"-":subList.get(10).toString());
				commonDTO1.setAreaId((String) subList.get(11));
				retList.add(commonDTO1);
			}
		}catch (Exception e) {
			logger.error(e);
		}
		return retList;
	}
	public ArrayList getagriPropDetailsList(String language, String id,
	CommonDTO commonDTO) {
		ArrayList agriPropDetailsList=new ArrayList();
		//CommonDTO cmDTO=new CommonDTO();
		ArrayList retList=new ArrayList();
		try{
			agriPropDetailsList=commonDAO.getagriPropDetailsList(language,id,commonDTO);
			for(int i=0;i<agriPropDetailsList.size();i++){
				ArrayList subList=(ArrayList)agriPropDetailsList.get(i);

				CommonDTO commonDTO1= new CommonDTO();
				commonDTO1.setCommonAgriTxnid(subList.get(0)==null ?"-":subList.get(0).toString());
				commonDTO1.setCommonAgriSubTypeId(subList.get(1)==null ?"-":subList.get(1).toString());
				commonDTO1.setCommonAgriPropSubTypeName(subList.get(2)==null ?"-":subList.get(2).toString());
				commonDTO1.setCommonTotalUndivArea(subList.get(3)==null ?"-":subList.get(3).toString());
				commonDTO1.setCommonTotalUnirriOneCrop(subList.get(4)==null ?"-":subList.get(4).toString());
				commonDTO1.setCommonTotalUnirriTwoCrop(subList.get(5)==null ?"-":subList.get(5).toString());
				commonDTO1.setCommonTotalIrrigatedArea(subList.get(6)==null ?"-":subList.get(6).toString());
				commonDTO1.setCommonAgriConstruction(subList.get(7)==null ?"-":subList.get(7).toString());
				commonDTO1.setCommonTotalDivArea(subList.get(8)==null ?"-":subList.get(8).toString());
				commonDTO1.setCommonTotalResiArea(subList.get(9)==null ?"-":subList.get(9).toString());
				commonDTO1.setCommonTotalCommArea(subList.get(10)==null ?"-":subList.get(10).toString());
				commonDTO1.setCommonTotalIndArea(subList.get(11)==null ?"-":subList.get(11).toString());
				commonDTO1.setCommonTotalEduArea(subList.get(12)==null ?"-":subList.get(12).toString());
				commonDTO1.setCommonTotalHealthArea(subList.get(13)==null ?"-":subList.get(13).toString());
				commonDTO1.setCommonTotalOtherArea(subList.get(14)==null ?"-":subList.get(14).toString());
				commonDTO1.setCommonAgriEducationTcp(subList.get(15)==null ?"-":subList.get(15).toString());
				commonDTO1.setCommonAgriHealthTcp(subList.get(16)==null ?"-":subList.get(16).toString());
				//changed by akansha for construction khasra
				commonDTO1.setIsConstructionKhasraNo(subList.get(17)==null ?"-":subList.get(17).toString());
				retList.add(commonDTO1);

			}
		}catch (Exception e) {
			logger.error(e);
		}
		return retList;
	}


	public CommonDTO getbuildingView(String valId,String language)
	{
		CommonDTO cmDTO=new CommonDTO();
		ArrayList buildList;
		cmDTO.setOnlyResi("");

		if(valId.startsWith("a"))
		{
			buildList=commonDAO.getBuildingDetailsAgri(valId.substring(1), language);

		}
		else
		{
			buildList=commonDAO.getBuildingDetails(valId, language);
		}




		if(buildList!=null)
		{

			ArrayList subList=(ArrayList) buildList.get(0);
			cmDTO.setDistrict((String) subList.get(0));
			cmDTO.setTehsil((String) subList.get(1));
			cmDTO.setArea((String) subList.get(2));
			cmDTO.setSubArea((String) subList.get(3));
			cmDTO.setWardPatwari((String) subList.get(4));
			cmDTO.setColony((String) subList.get(5));
			String buildingId=subList.get(7).toString().trim();
			cmDTO.setBuildingTypeId(buildingId);
			String buildingTxId=subList.get(6).toString().trim();
			String onlyResi = subList.get(37)==null?"":subList.get(37).toString();
			cmDTO.setOnlyResi(onlyResi);

			if("4".equalsIgnoreCase(buildingId))
			{
				cmDTO.setOpenTerraceArea(subList.get(15).toString());
				cmDTO.setOpenTerraceUsage(subList.get(14).toString());
			}
			else if("2".equalsIgnoreCase(buildingId))
			{
				cmDTO.setTotalArea(subList.get(8).toString());
				cmDTO.setFloorName(subList.get(9).toString());
				cmDTO.setOlder("N");
				String older20=subList.get(10)==null?"":subList.get(10).toString();
				String older50=subList.get(11)==null?"":subList.get(11).toString();
				if("Y".equalsIgnoreCase(older50))
				{
					cmDTO.setOlder("50");
				}
				if("Y".equalsIgnoreCase(older20))
				{
					cmDTO.setOlder("20");
				}
				String lift=subList.get(12)==null?"":subList.get(12).toString();;
				if("Y".equalsIgnoreCase(lift))
				{
					cmDTO.setIsLiftMall("Y");
				}
				else
				{
					cmDTO.setIsLiftMall("N");
				}
				String openTerrace=subList.get(13)==null?"":subList.get(13).toString();
				if("Y".equalsIgnoreCase(openTerrace))
				{
					cmDTO.setOpenTerraceFlag("Y");
					cmDTO.setOpenTerraceArea(subList.get(15).toString());
					cmDTO.setOpenTerraceUsage(subList.get(14).toString());

				}
				else
				{
					cmDTO.setOpenTerraceFlag("N");
				}

				String constructionSlab = subList.get(36)==null?"":subList.get(36).toString();
				if(constructionSlab.equalsIgnoreCase("0")||constructionSlab.isEmpty()||constructionSlab.equalsIgnoreCase("")){
					cmDTO.setOlderSubClause("N");
				}
				else{

					constructionSlab = commonDAO.getBuildingOlderRebate(constructionSlab, language);
					cmDTO.setOlderSubClause(constructionSlab);
				}

			}
			else if("3".equalsIgnoreCase(buildingId))
			{
				String multiStoreyId=subList.get(34).toString();
				if("18".equalsIgnoreCase(multiStoreyId))
				{
					cmDTO.setMultiStoreyTypeId("18");
					cmDTO.setMultiStoreyUsageName(subList.get(16).toString());
					cmDTO.setTotalArea(subList.get(8).toString());
					cmDTO.setCommonArea(subList.get(19).toString());
					cmDTO.setBuiltUpArea(subList.get(18).toString());
					cmDTO.setFloorName(subList.get(9).toString());
					cmDTO.setOlder("N");
					String older20=subList.get(10)==null?"":subList.get(10).toString();
					String older50=subList.get(11)==null?"":subList.get(11).toString();
					if("Y".equalsIgnoreCase(older50))
					{
						cmDTO.setOlder("50");
					}
					if("Y".equalsIgnoreCase(older20))
					{
						cmDTO.setOlder("20");
					}

					String constructionSlab = subList.get(36)==null?"":subList.get(36).toString();
					if(constructionSlab.equalsIgnoreCase("0")||constructionSlab.isEmpty()||constructionSlab.equalsIgnoreCase("")){
						cmDTO.setOlderSubClause("N");
					}
					else{

						constructionSlab = commonDAO.getBuildingOlderRebate(constructionSlab, language);
						cmDTO.setOlderSubClause(constructionSlab);
					}
					String lift=subList.get(12)==null?"":subList.get(12).toString();;
					if("Y".equalsIgnoreCase(lift))
					{
						cmDTO.setIsLiftMall("Y");
					}
					else
					{
						cmDTO.setIsLiftMall("N");
					}
					String openTerrace=subList.get(13)==null?"":subList.get(13).toString();
					if("Y".equalsIgnoreCase(openTerrace))
					{
						cmDTO.setOpenTerraceFlag("Y");
						cmDTO.setOpenTerraceArea(subList.get(15).toString());
						cmDTO.setOpenTerraceUsage(subList.get(14).toString());

					}
					else
					{
						cmDTO.setOpenTerraceFlag("N");
					}

				}
				else
				{
					cmDTO.setMultiStoreyTypeId("19");
					cmDTO.setMultiStoreyUsageName(subList.get(16).toString());
					cmDTO.setTotalArea(subList.get(8).toString());
					cmDTO.setCommonArea(subList.get(19).toString());
					cmDTO.setBuiltUpArea(subList.get(18).toString());
					cmDTO.setFloorName(subList.get(9).toString());
					cmDTO.setMultiStoreyUsageName(subList.get(20).toString());
					cmDTO.setOlder("N");
					String older20=subList.get(10)==null?"":subList.get(10).toString();
					String older50=subList.get(11)==null?"":subList.get(11).toString();
					if("Y".equalsIgnoreCase(older50))
					{
						cmDTO.setOlder("50");
					}
					if("Y".equalsIgnoreCase(older20))
					{
						cmDTO.setOlder("20");
					}
					String nearRoad=subList.get(22)==null?"":subList.get(22).toString();
					if("Y".equalsIgnoreCase(nearRoad))
					{
						cmDTO.setNearRoad("Y");
					}
					else
					{
						cmDTO.setNearRoad("N");
					}
					String mall=subList.get(21)==null?"":subList.get(21).toString();;
					if("Y".equalsIgnoreCase(mall))
					{
						cmDTO.setIsLiftMall("Y");
					}
					else
					{
						cmDTO.setIsLiftMall("N");
					}
					String openTerrace=subList.get(13)==null?"":subList.get(13).toString();
					if("Y".equalsIgnoreCase(openTerrace))
					{
						cmDTO.setOpenTerraceFlag("Y");
						cmDTO.setOpenTerraceArea(subList.get(15).toString());
						cmDTO.setOpenTerraceUsage(subList.get(14).toString());

					}
					else
					{
						cmDTO.setOpenTerraceFlag("N");
					}
				}
			}
			else
			{
				cmDTO.setTotalArea(subList.get(17).toString());


				if(subList.get(23)==null|| subList.get(23).toString().equalsIgnoreCase(""))
				{
					cmDTO.setResiArea("");
				}
				else
				{
					cmDTO.setResiArea(subList.get(23).toString());
				}
				if(subList.get(24)==null|| subList.get(24).toString().equalsIgnoreCase(""))
				{
					cmDTO.setCommArea("");
				}
				else
				{
					cmDTO.setCommArea(subList.get(24).toString());
				}
				if(subList.get(25)==null|| subList.get(25).toString().equalsIgnoreCase(""))
				{
					cmDTO.setIndArea("");
				}
				else
				{
					cmDTO.setIndArea(subList.get(25).toString());
				}
				if(subList.get(26)==null|| subList.get(26).toString().equalsIgnoreCase(""))
				{
					cmDTO.setSchoolArea("");
				}
				else
				{
					cmDTO.setSchoolArea(subList.get(26).toString());
				}
				if(subList.get(27)==null|| subList.get(27).toString().equalsIgnoreCase(""))
				{
					cmDTO.setHealthArea("");
				}
				else
				{
					cmDTO.setHealthArea(subList.get(27).toString());
				}
				if(subList.get(28)==null|| subList.get(28).toString().equalsIgnoreCase(""))
				{
					cmDTO.setOtherArea("");
				}
				else
				{
					cmDTO.setOtherArea(subList.get(28).toString());
				}
				if(subList.get(35)==null|| subList.get(35).toString().equalsIgnoreCase(""))
				{
					cmDTO.setResiCommArea("");
				}
				else
				{
					cmDTO.setResiCommArea(subList.get(35).toString());
				}

				if(!cmDTO.getResiArea().equalsIgnoreCase("") && cmDTO.getCommArea().equalsIgnoreCase("") && cmDTO.getIndArea().equalsIgnoreCase("")
				&&  cmDTO.getSchoolArea().equalsIgnoreCase("") && cmDTO.getHealthArea().equalsIgnoreCase("") && cmDTO.getOtherArea().equalsIgnoreCase("")

				&& cmDTO.getResiCommArea().equalsIgnoreCase("")
				){
					//cmDTO.setOnlyResiBuilding("R");
					String constructionSlab = subList.get(36)==null?"":subList.get(36).toString();
					if(constructionSlab.equalsIgnoreCase("0")||constructionSlab.isEmpty()||constructionSlab.equalsIgnoreCase("")){
						cmDTO.setOlderSubClause("N");
					}
					else{

						constructionSlab = commonDAO.getBuildingOlderRebate(constructionSlab, language);
						cmDTO.setOlderSubClause(constructionSlab);
					}
				}
				String housingBoard=subList.get(33)==null?"":subList.get(33).toString();
				if("Y".equalsIgnoreCase(housingBoard))
				{
					cmDTO.setIsHosingBoard("Y");
				}
				else
				{
					cmDTO.setIsHosingBoard("N");
				}
				String isAkvn=subList.get(32)==null?"":subList.get(32).toString();
				if("Y".equalsIgnoreCase(isAkvn))
				{
					cmDTO.setIsAkvn("Y");
				}
				else
				{
					cmDTO.setIsAkvn("N");

				}
				String superConstruction=subList.get(31)==null?"":subList.get(31).toString();
				if("Y".equalsIgnoreCase(superConstruction))
				{
					cmDTO.setIsSuperConstruction("Y");
				}
				else
				{
					cmDTO.setIsSuperConstruction("N");

				}
				String eduTcp=subList.get(29)==null?"":subList.get(29).toString();;
				String healthTCP=subList.get(30)==null?"":subList.get(30).toString();;
				if("Y".equalsIgnoreCase(healthTCP))
				{
					cmDTO.setHealthTCP("Y");
				}
				else
				{
					cmDTO.setHealthTCP("N");

				}
				if("Y".equalsIgnoreCase(eduTcp))
				{
					cmDTO.setEduTCP("Y");

				}
				else
				{
					cmDTO.setEduTCP("N");
				}

				ArrayList floorAreaList=commonDAO.getFloorAreaList(buildingTxId,language);

				if(floorAreaList!=null)
				{
					cmDTO.setFloorAreaList(new ArrayList());
					for(int i=0;i<floorAreaList.size();i++)
					{
						ArrayList floorSubList=(ArrayList) floorAreaList.get(i);

						CommonDTO dto= new CommonDTO();

						dto.setFloorPropId(floorSubList.get(0).toString());
						dto.setFloorName(floorSubList.get(1).toString());
						if(floorSubList.get(2)==null ||floorSubList.get(2).toString().equalsIgnoreCase(""))
						{
							dto.setRccArea("0");
						}
						else
						{
							dto.setRccArea((String)floorSubList.get(2));
						}
						if(floorSubList.get(5)==null ||floorSubList.get(5).toString().equalsIgnoreCase(""))
						{
							dto.setKacchaArea("0");
						}
						else
						{
							dto.setKacchaArea((String)floorSubList.get(5));
						}
						if(floorSubList.get(4)==null ||floorSubList.get(4).toString().equalsIgnoreCase(""))
						{
							dto.setTinArea("0");
						}
						else
						{
							dto.setTinArea((String)floorSubList.get(4));
						}
						if(floorSubList.get(3)==null ||floorSubList.get(3).toString().equalsIgnoreCase(""))
						{
							dto.setRbcArea("0");
						}
						else
						{
							dto.setRbcArea((String)floorSubList.get(3));
						}
						if(floorSubList.get(6)==null ||floorSubList.get(6).toString().equalsIgnoreCase(""))
						{
							dto.setShopArea("0");
						}
						else
						{
							dto.setShopArea((String)floorSubList.get(6));
						}
						if(floorSubList.get(7)==null ||floorSubList.get(7).toString().equalsIgnoreCase(""))
						{
							dto.setOfficeArea("0");
						}
						else
						{
							dto.setOfficeArea((String)floorSubList.get(7));
						}
						if(floorSubList.get(8)==null ||floorSubList.get(8).toString().equalsIgnoreCase(""))
						{
							dto.setGodownArea("0");
						}
						else
						{
							dto.setGodownArea((String)floorSubList.get(8));
						}
						cmDTO.getFloorAreaList().add(dto);
					}
				}

			}


		}
		return cmDTO;

	}


	public String getpropTypeId(String language, String id) {
		String Id =null;

		Id=commonDAO.getpropTypeId(language,id);

		return Id;
	}
	public ArrayList getAgriTreeDetailsList(String language,
	String id, CommonDTO commonDTO) {
		ArrayList agriTreeDetailsList=new ArrayList();
		ArrayList retList=new ArrayList();

		try{
			agriTreeDetailsList=commonDAO.getAgriTreeDetailsList(language,id,commonDTO);
			for(int i=0;i<agriTreeDetailsList.size();i++){
				ArrayList subList=(ArrayList)agriTreeDetailsList.get(i);
				CommonDTO commonDTO1= new CommonDTO();
				commonDTO1.setCommonAgriTreeCount((String) subList.get(1));
				if(language.equalsIgnoreCase("en")){
					commonDTO1.setCommonAgriTreeName((String) subList.get(0));
				}else{
					commonDTO1.setCommonAgriTreeName((String) subList.get(0));
				}
				retList.add(commonDTO1);
			}
		}catch (Exception e) {
			logger.error(e);
		}
		return retList;
	}
	// ADDDE BY RAKESH
	public String getClrFlag(String valID) {
		//String Id =null;

		return commonDAO.getClrFlag(valID);


	}


	public ArrayList getAgriPropDetailsListClr(String valID) {
		//String Id =null;

		return commonDAO.getAgriPropDetailsListClr(valID);


	}
}