package com.wipro.igrs.newPropvaluation.action;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.newPropvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.newPropvaluation.constant.PropertyValuationConstant;
import com.wipro.igrs.newPropvaluation.dao.PropertyValuationDAO;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;

public class CalculateBuilding {

	private static Logger logger = org.apache.log4j.Logger.getLogger(PropertyValuationBO.class);
	private PropertyValuationDAO propDAO;
	public CalculateBuilding() throws Exception
	{
		propDAO= new PropertyValuationDAO();
	}
	
	public String[] calcOpenTerrace(PropertyValuationDTO propDTO){
		double value=0;
		String guidelineId="";
		String returnString[]=new String[2];
		returnString[0]="0";
		returnString[1]="0";
		ArrayList guideLineList=propDAO.getGuideLineValueOpenTerrace(propDTO);
		if(guideLineList!=null && guideLineList.size()>0)
		{
			ArrayList subList= (ArrayList) guideLineList.get(0);
			
			guidelineId=String.valueOf(subList.get(0));
			value=Double.parseDouble(propDTO.getOpenTerraceArea())*Double.parseDouble(String.valueOf(subList.get(1)));
			returnString[0]=guidelineId;
			returnString[1]=BigDecimal.valueOf(value).toPlainString();
		}
		return returnString;
	}
	
	public String[] calcIndependentFloor(PropertyValuationDTO propDTO){
		double value=0;
		String guidelineId="";
		String returnString[]=new String[2];
		returnString[0]="0";
		returnString[1]="0";
		ArrayList guideLineList=propDAO.getGuideLineValueIndependentFloor(propDTO);
		if(guideLineList!=null && guideLineList.size()>0)
		{
			ArrayList subList= (ArrayList) guideLineList.get(0);
			guidelineId=String.valueOf(subList.get(0));
			value=Double.parseDouble(propDTO.getFloorArea())*Double.parseDouble(String.valueOf(subList.get(1)));
			returnString[0]=guidelineId;
			returnString[1]=BigDecimal.valueOf(value).toPlainString();
		}
		return returnString;
	}
	
	public String[] calcWithoutSubclauses(PropertyValuationDTO propDTO){
		if(propDTO.getBuildingTypeId().equalsIgnoreCase("4"))
			{
			return calcOpenTerrace(propDTO);
			}
		else if(propDTO.getBuildingTypeId().equalsIgnoreCase("2"))
		{
			return calcIndependentFloor(propDTO); 
		}
		else if(propDTO.getBuildingTypeId().equalsIgnoreCase("3"))
		{
			return calcMultiStroreyFloor(propDTO); 
		}
		else if(propDTO.getBuildingTypeId().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_ID))  //this condition added by Roopam for independent building. 
		{
			return calcIndependentBuilding(propDTO); 
		}
		return null;
		}
	
	public String[] calcIndependentBuilding(PropertyValuationDTO propDTO){
		
		String[] returnValues=new String[2];
		returnValues[0]="0";
		returnValues[1]="0";
		String value="";
		
		try{
		String eduTcp=propDTO.getIsTncpSchool();
		String healthTcp=propDTO.getIsTncpHealth();
		logger.debug("eduTCP:"+propDTO.getIsTncpSchool());
		logger.debug("healthTCP:"+propDTO.getIsTncpHealth());
		String marketValue="";
		//ArrayList list=new ArrayList();
		//list=propDAO.getGuidelineId(propDTO);
		double resiArea=0.0;
		double resiCommArea=0.0;
		double commArea=0.0;
		double	indArea=0.0;
		double healthArea=0.0;
		double schoolArea=0.0;
		double otherArea=0.0;
		double baseValue=0.0;
		double resiValue=0.0;
		double commValue=0.0;
		double indValue=0.0;
		double schoolValue=0.0;
		double healthValue=0.0;
		double otherValue=0.0;
		double marketVal=0.0;
		if(propDTO.getPlotResiArea()!=null && !propDTO.getPlotResiArea().equalsIgnoreCase(""))
		{	
		resiArea=Double.valueOf(propDTO.getPlotResiArea());
		if(resiArea>0)
		{	
		propDTO.setBuildOnlyResi("Y");
		}
		}
		if(propDTO.getPlotCommArea()!=null && !propDTO.getPlotCommArea().equalsIgnoreCase(""))
		{
		commArea=Double.valueOf(propDTO.getPlotCommArea());
		if(commArea>0)
		{	
		propDTO.setBuildOnlyResi("N");
		}
		}
		if(propDTO.getPlotIndusArea()!=null && !propDTO.getPlotIndusArea().equalsIgnoreCase(""))
		{
		
			indArea=Double.valueOf(propDTO.getPlotIndusArea());
			if(indArea>0)
			{	
			propDTO.setBuildOnlyResi("N");
			}
		}
		if(propDTO.getPlotHealthArea()!=null && !propDTO.getPlotHealthArea().equalsIgnoreCase(""))
		{
		healthArea=Double.valueOf(propDTO.getPlotHealthArea());
		if(healthArea>0)
		{	
		propDTO.setBuildOnlyResi("N");
		}
		}
		if(propDTO.getPlotSchoolArea()!=null && !propDTO.getPlotSchoolArea().equalsIgnoreCase(""))
		{
		schoolArea=Double.valueOf(propDTO.getPlotSchoolArea());
		if(schoolArea>0)
		{	
		propDTO.setBuildOnlyResi("N");
		}
		}
		if(propDTO.getPlotOtherArea()!=null && !propDTO.getPlotOtherArea().equalsIgnoreCase(""))
		{
		otherArea=Double.valueOf(propDTO.getPlotOtherArea());
		if(otherArea>0)
		{	
		propDTO.setBuildOnlyResi("N");
		}
		}
		if(propDTO.getResiCommArea()!=null && !propDTO.getResiCommArea().equalsIgnoreCase(""))
		{
		resiCommArea=Double.valueOf(propDTO.getResiCommArea());
		if(resiCommArea>0)
		{	
		propDTO.setBuildOnlyResi("N");
		}
		}
		if(resiCommArea>0)
		{
			PropertyValuationDAO propDAO=new PropertyValuationDAO();
			ArrayList guideLineValues=propDAO.getBuildingGuidelineValues(propDTO);
			
			//get guidline id
			if(guideLineValues!=null && guideLineValues.size()>0){
				
				ArrayList rowList=(ArrayList)guideLineValues.get(0);
				if(rowList!=null){
				returnValues[0]=(String)rowList.get(1);
				}else{
					returnValues[0]="";
				}
			}	
			String resiCommRate=propDAO.getL1Rate(propDTO,"33","1");
			double resiCommRates=0;
			if(resiCommRate!=null&&!resiCommRate.equalsIgnoreCase(""))
			{
				resiCommRates=Double.parseDouble(resiCommRate);
				baseValue=baseValue+(resiCommArea*resiCommRates);
				value=BigDecimal.valueOf(baseValue).toPlainString();
			}
		}
		else
		{	
			double resiGroundArea=0;
			double commGroundArea=0;
			double indGroundArea=0;
			double schoolGroundArea=0;
			double healthGroundArea=0;
			double otherGroundArea=0;
			for(int i=0;i<propDTO.getResiFloorAreaList().size();i++)
			{
				PropertyValuationDTO resiDTO=propDTO.getResiFloorAreaList().get(i);
				if("3".equalsIgnoreCase(resiDTO.getFloorId()))
				{
					if(resiDTO.getRccArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRccArea()))
					{
						resiGroundArea=resiGroundArea+Double.parseDouble(resiDTO.getRccArea());
					}
					if(resiDTO.getRbcArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRbcArea()))
					{
						resiGroundArea=resiGroundArea+Double.parseDouble(resiDTO.getRbcArea());
					}
					if(resiDTO.getKacchaArea()!=null&&!"".equalsIgnoreCase(resiDTO.getKacchaArea()))
					{
						resiGroundArea=resiGroundArea+Double.parseDouble(resiDTO.getKacchaArea());
					}
					if(resiDTO.getTinArea()!=null&&!"".equalsIgnoreCase(resiDTO.getTinArea()))
					{
						resiGroundArea=resiGroundArea+Double.parseDouble(resiDTO.getTinArea());
					}
				}
			}
			for(int i=0;i<propDTO.getIndFloorAreaList().size();i++)
			{
				PropertyValuationDTO resiDTO=propDTO.getIndFloorAreaList().get(i);
				if("3".equalsIgnoreCase(resiDTO.getFloorId()))
				{
					if(resiDTO.getRccArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRccArea()))
					{
						indGroundArea=indGroundArea+Double.parseDouble(resiDTO.getRccArea());
					}
					if(resiDTO.getRbcArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRbcArea()))
					{
						indGroundArea=indGroundArea+Double.parseDouble(resiDTO.getRbcArea());
					}
					if(resiDTO.getKacchaArea()!=null&&!"".equalsIgnoreCase(resiDTO.getKacchaArea()))
					{
						indGroundArea=indGroundArea+Double.parseDouble(resiDTO.getKacchaArea());
					}
					if(resiDTO.getTinArea()!=null&&!"".equalsIgnoreCase(resiDTO.getTinArea()))
					{
						indGroundArea=indGroundArea+Double.parseDouble(resiDTO.getTinArea());
					}
				}
			}
			for(int i=0;i<propDTO.getSchoolFloorAreaList().size();i++)
			{
				PropertyValuationDTO resiDTO=propDTO.getSchoolFloorAreaList().get(i);
				if("3".equalsIgnoreCase(resiDTO.getFloorId()))
				{
					if(resiDTO.getRccArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRccArea()))
					{
						schoolGroundArea=schoolGroundArea+Double.parseDouble(resiDTO.getRccArea());
					}
					if(resiDTO.getRbcArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRbcArea()))
					{
						schoolGroundArea=schoolGroundArea+Double.parseDouble(resiDTO.getRbcArea());
					}
					if(resiDTO.getKacchaArea()!=null&&!"".equalsIgnoreCase(resiDTO.getKacchaArea()))
					{
						schoolGroundArea=schoolGroundArea+Double.parseDouble(resiDTO.getKacchaArea());
					}
					if(resiDTO.getTinArea()!=null&&!"".equalsIgnoreCase(resiDTO.getTinArea()))
					{
						schoolGroundArea=schoolGroundArea+Double.parseDouble(resiDTO.getTinArea());
					}
				}
			}
			for(int i=0;i<propDTO.getHealthFloorAreaList().size();i++)
			{
				PropertyValuationDTO resiDTO=propDTO.getHealthFloorAreaList().get(i);
				if("3".equalsIgnoreCase(resiDTO.getFloorId()))
				{
					if(resiDTO.getRccArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRccArea()))
					{
						healthGroundArea=healthGroundArea+Double.parseDouble(resiDTO.getRccArea());
					}
					if(resiDTO.getRbcArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRbcArea()))
					{
						healthGroundArea=healthGroundArea+Double.parseDouble(resiDTO.getRbcArea());
					}
					if(resiDTO.getKacchaArea()!=null&&!"".equalsIgnoreCase(resiDTO.getKacchaArea()))
					{
						healthGroundArea=healthGroundArea+Double.parseDouble(resiDTO.getKacchaArea());
					}
					if(resiDTO.getTinArea()!=null&&!"".equalsIgnoreCase(resiDTO.getTinArea()))
					{
						healthGroundArea=healthGroundArea+Double.parseDouble(resiDTO.getTinArea());
					}
				}
			}
			for(int i=0;i<propDTO.getOtherFloorAreaList().size();i++)
			{
				PropertyValuationDTO resiDTO=propDTO.getOtherFloorAreaList().get(i);
				if("3".equalsIgnoreCase(resiDTO.getFloorId()))
				{
					if(resiDTO.getRccArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRccArea()))
					{
						otherGroundArea=otherGroundArea+Double.parseDouble(resiDTO.getRccArea());
					}
					if(resiDTO.getRbcArea()!=null&&!"".equalsIgnoreCase(resiDTO.getRbcArea()))
					{
						otherGroundArea=otherGroundArea+Double.parseDouble(resiDTO.getRbcArea());
					}
					if(resiDTO.getKacchaArea()!=null&&!"".equalsIgnoreCase(resiDTO.getKacchaArea()))
					{
						otherGroundArea=otherGroundArea+Double.parseDouble(resiDTO.getKacchaArea());
					}
					if(resiDTO.getTinArea()!=null&&!"".equalsIgnoreCase(resiDTO.getTinArea()))
					{
						otherGroundArea=otherGroundArea+Double.parseDouble(resiDTO.getTinArea());
					}
				}
			}
			for(int i=0;i<propDTO.getCommFloorAreaList().size();i++)
			{
				PropertyValuationDTO resiDTO=propDTO.getCommFloorAreaList().get(i);
				if("3".equalsIgnoreCase(resiDTO.getFloorId()))
				{
					if(resiDTO.getShopArea()!=null&&!"".equalsIgnoreCase(resiDTO.getShopArea()))
					{
						commGroundArea=commGroundArea+Double.parseDouble(resiDTO.getShopArea());
					}
					if(resiDTO.getOfficeArea()!=null&&!"".equalsIgnoreCase(resiDTO.getOfficeArea()))
					{
						commGroundArea=commGroundArea+Double.parseDouble(resiDTO.getOfficeArea());
					}
					if(resiDTO.getGodownArea()!=null&&!"".equalsIgnoreCase(resiDTO.getGodownArea()))
					{
						commGroundArea=commGroundArea+Double.parseDouble(resiDTO.getGodownArea());
					}
					
				}
			}	
		PropertyValuationDAO propDAO=new PropertyValuationDAO();
		ArrayList guideLineValues=propDAO.getBuildingGuidelineValues(propDTO);
		
		//get guidline id
		if(guideLineValues!=null && guideLineValues.size()>0){
			
			ArrayList rowList=(ArrayList)guideLineValues.get(0);
			if(rowList!=null){
			returnValues[0]=(String)rowList.get(1);
			}else{
				returnValues[0]="";
			}
		if("TRUE".equalsIgnoreCase(propDTO.getOnlyIndustrial())||"TRUE".equalsIgnoreCase(propDTO.getAllAreaZero()))
		{
			if("Y".equalsIgnoreCase(propDTO.getIsSuperConstructionFlag())||"Y".equalsIgnoreCase(propDTO.getHousingCheckFlag()))
			{
			commGroundArea=0;
			indGroundArea=0;
			resiGroundArea=0;
			healthGroundArea=0;
			schoolGroundArea=0;
			otherGroundArea=0;
			
			}
		}
		for(int i=0;i<guideLineValues.size();i++)
		{
			ArrayList subList= (ArrayList) guideLineValues.get(i);
			if(subList!=null)
			{
				if((i+1)==PropertyValuationConstant.PLOT_RESIDENTIAL_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						resiValue=Double.valueOf(subList.get(0).toString());
						baseValue=baseValue+(resiGroundArea*Double.valueOf(subList.get(0).toString()));
						value=BigDecimal.valueOf(baseValue).toPlainString();
				
					}
				}
				if((i+1)==PropertyValuationConstant.PLOT_COMMERCIAL_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						commValue=Double.valueOf(subList.get(0).toString());
						baseValue=baseValue+(commGroundArea*Double.valueOf(subList.get(0).toString()));
						value=BigDecimal.valueOf(baseValue).toPlainString();
				
					}
				}
				if((i+1)==PropertyValuationConstant.PLOT_INDUSTRIAL_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						indValue=Double.valueOf(subList.get(0).toString());
						baseValue=baseValue+(indGroundArea*Double.valueOf(subList.get(0).toString()));
						value=BigDecimal.valueOf(baseValue).toPlainString();
				
					}
				}
				if((i+1)==PropertyValuationConstant.PLOT_HEALTH_L1)
				{
					if("Y".equalsIgnoreCase(healthTcp))
					{	
						if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
						{
							healthValue=Double.valueOf(subList.get(0).toString());
						baseValue=baseValue+(healthGroundArea*Double.valueOf(subList.get(0).toString()));//changed as suggested by Vinay
						value=BigDecimal.valueOf(baseValue).toPlainString();
				
						}
					}
					else
					{
						healthValue=resiValue;
						baseValue=baseValue+(healthGroundArea*resiValue);//changed as suggested by Vinay
						value=BigDecimal.valueOf(baseValue).toPlainString();
					}
				}
				if((i+1)==PropertyValuationConstant.PLOT_EDUCATIONAL_L1)
				{
					if("Y".equalsIgnoreCase(eduTcp))
					{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
						{
						schoolValue=Double.valueOf(subList.get(0).toString());
						baseValue=baseValue+(schoolGroundArea*Double.valueOf(subList.get(0).toString()));
						value=BigDecimal.valueOf(baseValue).toPlainString();
				
						}
					}
					else
					{
						schoolValue=resiValue;
						baseValue=baseValue+(schoolGroundArea*resiValue);
						value=BigDecimal.valueOf(baseValue).toPlainString();
					}
				}
				if((i+1)==PropertyValuationConstant.PLOT_OTHER_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						otherValue=Double.valueOf(subList.get(0).toString());
						baseValue=baseValue+(otherGroundArea*Double.valueOf(subList.get(0).toString()));
						value=BigDecimal.valueOf(baseValue).toPlainString();
				
					}
				}
			}		
		}
	if((resiGroundArea+commGroundArea+indGroundArea+schoolGroundArea+healthGroundArea+otherGroundArea)==Double.parseDouble(propDTO.getPlotTotArea()))
	{
		// construction on whole plot
	}
	else
	{
	double remaingArea=(Double.parseDouble(propDTO.getPlotTotArea())-(resiGroundArea+commGroundArea+indGroundArea+schoolGroundArea+healthGroundArea+otherGroundArea));
	commArea=commArea-commGroundArea;
	resiArea=resiArea-resiGroundArea;
	indArea=indArea-indGroundArea;
	healthArea=healthArea-healthGroundArea;
	schoolArea=schoolArea-schoolGroundArea;
	otherArea=otherArea-otherGroundArea;
	double [][]arr=new double[6][2];
	arr[0][0]=resiValue;
	arr[0][1]=resiArea;
	arr[1][0]=commValue;
	arr[1][1]=commArea;
	arr[2][0]=indValue;
	arr[2][1]=indArea;
	arr[3][0]=schoolValue;
	arr[3][1]=schoolArea;
	arr[4][0]=healthValue;
	arr[4][1]=healthArea;
	arr[5][0]=otherValue;
	arr[5][1]=otherArea;
	java.util.Arrays.sort(arr, new java.util.Comparator<double[]>() {
        public int compare(double[] a, double[] b) {
            return Double.compare(a[0], b[0]);
        }
    });

	for(int i=arr.length-1;i>=0;i--)
	{
		if(arr[i][1]>0&&arr[i][1]>=remaingArea&&remaingArea>0)
		{
			baseValue=baseValue+remaingArea*arr[i][0];
			remaingArea=0;
			value=BigDecimal.valueOf(baseValue).toPlainString();
		}
		else if(arr[i][1]>0&&arr[i][1]<remaingArea&&remaingArea>0)
		{
			baseValue=baseValue+arr[i][1]*arr[i][0];
			remaingArea=remaingArea-arr[i][1];
			value=BigDecimal.valueOf(baseValue).toPlainString();
		}
	}
	}
		
	}
		
		}
		marketVal=baseValue;
		value=BigDecimal.valueOf(marketVal).toPlainString();
		try{
		
		}catch (Exception e) {
			logger.error(e);
			
		}
		marketValue=BigDecimal.valueOf(marketVal).toPlainString();
		
		returnValues[1]=marketValue;
		}catch(Exception e){
			logger.debug("exception in CalculateBuilding - calcIndedfdfpendentBuilding");
		}
		
		return returnValues;
		}
	
	
	public String[] calcMultiStroreyFloor(PropertyValuationDTO propDTO){
		if("18".equalsIgnoreCase(propDTO.getMultiStoreyTypeId()))
		{	
		double value=0;
		String guidelineId="";
		String returnString[]=new String[2];
		returnString[0]="0";
		returnString[1]="0";
		ArrayList guideLineList=propDAO.getGuideLineValueMultiResiFloor(propDTO);//rcc rate 
		if(guideLineList!=null && guideLineList.size()>0)
		{
			ArrayList subList= (ArrayList) guideLineList.get(0);
			guidelineId=String.valueOf(subList.get(0));
			value=Double.parseDouble(propDTO.getFloorArea())*Double.parseDouble(String.valueOf(subList.get(1)));
			returnString[0]=guidelineId;
			returnString[1]=BigDecimal.valueOf(value).toPlainString();
		}
		logger.debug("calcMultiStroreyFloor calculate Building "+returnString[1]);
		return returnString;
		}
		else if("19".equalsIgnoreCase(propDTO.getMultiStoreyTypeId()))
		{
			double value=0;
			String guidelineId="";
			String returnString[]=new String[2];
			returnString[0]="0";
			returnString[1]="0";
			ArrayList guideLineList = new ArrayList();
			//ADDED BY SIMRAN
			int subPropertyTypeInComm = Integer.parseInt(propDTO.getMultiCommPropId());
			boolean flag=false;
			try {
				flag=new PropertyValuationBO().multiStroreyCommercialType(propDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(flag==true)
			{
				guideLineList=propDAO.getGuideLineValueMultistory(propDTO,"2","19");
			}
			else
			{	
			if((subPropertyTypeInComm == PropertyValuationConstant.MULTI_COMM_SHOP) || (subPropertyTypeInComm == PropertyValuationConstant.MULTI_COMM_OFFICE) ||(subPropertyTypeInComm == PropertyValuationConstant.MULTI_COMM_GODOWN))
			{
				guideLineList=propDAO.getGuideLineValueMultistoryComm(propDTO,String.valueOf(PropertyValuationConstant.MULTI_COMM_BUILDING),propDTO.getMultiCommPropId());
			}
			else
			{
				guideLineList=propDAO.getGuideLineValueMultistory(propDTO,"2","19");
			}
			//ArrayList guideLineList=propDAO.getGuideLineValueIndependentFloor(propDTO);//rcc rate //SIMRAN same as L2
			}
			if(guideLineList!=null && guideLineList.size()>0)
			{
				ArrayList subList= (ArrayList) guideLineList.get(0);
				guidelineId=String.valueOf(subList.get(0));
				value=Double.parseDouble(propDTO.getFloorArea())*Double.parseDouble(String.valueOf(subList.get(1)));
				returnString[0]=guidelineId;
				returnString[1]=BigDecimal.valueOf(value).toPlainString();
			}
			return returnString;
		}
		return null;	
		}
	/*public String[] calcMultiStroreyCommercialFloor(PropertyValuationDTO propDTO){
		double value=0;
		String guidelineId="";
		String returnString[]=new String[2];
		ArrayList guideLineList=propDAO.getGuideLineValueIndependentFloor(propDTO);//rcc rate 
		if(guideLineList!=null && guideLineList.size()>0)
		{
			ArrayList subList= (ArrayList) guideLineList.get(0);
			guidelineId=String.valueOf(subList.get(0));
			value=Double.parseDouble(propDTO.getFloorArea())*Double.parseDouble(String.valueOf(subList.get(1)));
			returnString[0]=guidelineId;
			returnString[1]=String.valueOf(value);;
		}
		return returnString;
	}*/
	public String calcInternalSubclauses(String baseValue,PropertyValuationDTO propDTO){
		if(propDTO.getBuildingTypeId().equalsIgnoreCase("3"))
		{	
		String newBaseValue="";
		boolean nearRoadFlag=true;
		double returnValue=Double.parseDouble(baseValue);
		String floorId="";
		String liftPresent= propDTO.getIsLift();
		String mutiStoreyId=propDTO.getMultiStoreyTypeId();
		   //change by ankit for prop val : Mall
			if("Y".equalsIgnoreCase(liftPresent) && "18".equalsIgnoreCase(mutiStoreyId))
			{
				floorId="3";   // for ground floor
			}
			else
			{
				floorId=propDTO.getFloorId();
			}
		
		
		if("18".equalsIgnoreCase(mutiStoreyId))//residential
		{
			double rebate=0.0;
			String guideRate=propDAO.getL1Rate(propDTO,"18","2");
			//Added by Shreeraj for lift rebate for multistorey residential
			if("Y".equalsIgnoreCase(liftPresent))
			{
				
					if(guideRate==null ||guideRate.equalsIgnoreCase("")||Double.parseDouble(guideRate)==0)
					{
						rebate=Double.parseDouble(propDAO.getFloorRebate(propDTO.getFloorId(),"18","L"));
					}else{
						rebate=Double.parseDouble(propDAO.getFloorRebate(floorId,"18"));
					}
			}
			else{
				if(guideRate==null ||guideRate.equalsIgnoreCase("")||Double.parseDouble(guideRate)==0)
				{
					rebate=Double.parseDouble(propDAO.getFloorRebate(propDTO.getFloorId(),"18","F"));
				}else{
					rebate=Double.parseDouble(propDAO.getFloorRebate(floorId,"18"));
				}
				}
				double multiplyingFactor=(100-rebate)/100;
				String isOlder=propDTO.getIsOlder();
				String older=propDTO.getOlder();




				/*if("YES".equalsIgnoreCase(isOlder))
			{
				 guideRate=propDAO.getL1Rate(propDTO,"18","2");
				if(guideRate==null ||guideRate.equalsIgnoreCase("")||Double.parseDouble(guideRate)==0)
				{
					guideRate=propDAO.getL2Rate(propDTO,"7","1");//rcc rate calculation
				}
				double guidelineRates=Double.parseDouble(guideRate);// from guideline whether residential or rcc rates
				double residentialPlotRate=Double.parseDouble(propDAO.getL1Rate(propDTO,"1","1"));
				double area=Double.parseDouble(propDTO.getFloorArea());
				if("20".equalsIgnoreCase(older))
				{
					double olderRebate=10;
					double olderRebateMF=(olderRebate)/100;
					returnValue=((multiplyingFactor*(area*guidelineRates))-(area*(guidelineRates-residentialPlotRate)*olderRebateMF));
				}
				else if("50".equalsIgnoreCase(older))
				{
					double olderRebate=20;
					double olderRebateMF=(olderRebate)/100;
					returnValue=((multiplyingFactor*(area*guidelineRates))-(area*(guidelineRates-residentialPlotRate)*olderRebateMF));
				}
				else
				{
					returnValue=multiplyingFactor*Double.parseDouble(baseValue);
				}
			}*/
				/*Added by  Rustam*/
				double slabOperand	=	0.0;
				String slabOperator="";
				for(PropertyValuationDTO dto : propDTO.getConstructionSlabList())
				{
					if(dto.getOlderId()==propDTO.getOlderId()){
						slabOperand = dto.getOlderOperand();
						slabOperator = dto.getOperatorname();
						System.out.println("slabOperand : "+ slabOperand+" slabOperator : "+slabOperator);
					}
				}

				if(propDTO.getOlderId()>0){

					guideRate=propDAO.getL1Rate(propDTO,"18","2");
					if(guideRate==null ||guideRate.equalsIgnoreCase("")||Double.parseDouble(guideRate)==0)
					{
						guideRate=propDAO.getL2Rate(propDTO,"7","1");
					}

					/*guideRate=propDAO.getL2Rate(propDTO,"7","1");//rcc rate calculation : changes reverted as asked by department : akansha

					if(guideRate==null ||guideRate.equalsIgnoreCase("")||Double.parseDouble(guideRate)==0){
						guideRate=propDAO.getL1Rate(propDTO,"18","2");

					}*/

					double guidelineRates=Double.parseDouble(guideRate);// from guideline whether residential or rcc rates
					double residentialPlotRate=Double.parseDouble(propDAO.getL1Rate(propDTO,"1","1"));
					double area=Double.parseDouble(propDTO.getFloorArea());

					double olderRebate=slabOperand;
					double olderRebateMF=(olderRebate)/100;
					returnValue=((multiplyingFactor*(area*guidelineRates))-(area*(guidelineRates-residentialPlotRate)*olderRebateMF));
				}





				else
				{
					returnValue=multiplyingFactor*Double.parseDouble(baseValue);
				}

			}
			//TODO: -SIMRAN
			//shree rebate calc done below for multi commercial when mall is not selected
			if(propDTO.getMultiStoreyTypeId().equalsIgnoreCase("19")) // commercial
			{	double rebate=0.0;
				
				//added by Shreeraj
				if("Y".equalsIgnoreCase(liftPresent))//liftPresent is mall in case of multi commercial(by Shreeraj)
				 rebate=Double.parseDouble(propDAO.getFloorRebate(propDTO.getFloorId(),"19","M"));
				else
				 rebate=Double.parseDouble(propDAO.getFloorRebate(floorId,"19"));
				
				double multiplyingFactor=(100-rebate)/100;
				if("YES".equalsIgnoreCase(propDTO.getIsOlder()))
				{	
					double area=Double.parseDouble(propDTO.getFloorArea());
					double guidelineRates=Double.parseDouble(baseValue)/area; 
					double commercialPlotRate=0.0;//SIMRAN
					
					String commPlotRate = "";
					
					commPlotRate = propDAO.getL1Rate(propDTO, "2", "1");
					commercialPlotRate = Double.parseDouble(commPlotRate);
					
					
					if("20".equalsIgnoreCase(propDTO.getOlder()))
					{
						double olderRebate=10;
						double olderRebateMF=(olderRebate)/100;
						returnValue=((multiplyingFactor*(area*guidelineRates))-(area*(guidelineRates-commercialPlotRate)*olderRebateMF));
			
					}
					else if("50".equalsIgnoreCase(propDTO.getOlder()))
					{
						double olderRebate=20;
						double olderRebateMF=(olderRebate)/100;
						returnValue=((multiplyingFactor*(area*guidelineRates))-(area*(guidelineRates-commercialPlotRate)*olderRebateMF));
			
					}
					else
					{
						returnValue=multiplyingFactor*Double.parseDouble(baseValue);
					}
					// to be check wheter less tahn resi value or not
					/*double resiBaseValue=0.0;  parameter
					double returnResiValue=0.0;  
					String resifloorId="";
					
					if(propDTO.getIsLift().equalsIgnoreCase("Y")|| propDTO.getNearRoad().equalsIgnoreCase("Y"))
					{
						floorId="3";   // for ground floor
					}
					else 
					{
						floorId=propDTO.getFloorId();
						
					}*/
					
					
					
			}	
				else
				{
					
						returnValue=multiplyingFactor*Double.parseDouble(baseValue);
					
				}
				
				logger.debug("return val res^^^^^^^^^^^^"+returnValue);
				//TO CHECK WHETHER COMMERCIAL RATE IS LESS THAN RESIDENTIAL RATE -- SIMRAN
				if("Y".equalsIgnoreCase(propDTO.getNearRoad())&& "19".equalsIgnoreCase(propDTO.getMultiStoreyTypeId())&& nearRoadFlag==true&&!"Y".equalsIgnoreCase(liftPresent))
				{
					double nearroadRebate=15; //15%  SIMRAN
					returnValue=returnValue-((nearroadRebate*returnValue)/100);
				}
				if(!((floorId.equals(PropertyValuationConstant.MEZZANINE_FLOOR_ID)) || (floorId.equals(PropertyValuationConstant.LOWER_GROUND_FLOOR_ID)) || (floorId.equals(PropertyValuationConstant.UPPER_GROUND_FLOOR_ID))))
				{
					String floorIdRes = floorId; 
					if((floorId.equals(PropertyValuationConstant.THIRD_FLOOR_ID)) || (floorId.equals(PropertyValuationConstant.FOURTH_FLOOR_AND_ABOVE_ID)))
					{
						floorIdRes = PropertyValuationConstant.THIRD_FLOOR_AND_ABOVE_ID;
					}
					double area=Double.parseDouble(propDTO.getFloorArea());
					double rebateForResidential=Double.parseDouble(propDAO.getFloorRebate(floorIdRes,"18"));
					double multiplyingFactorResidential=(100-rebateForResidential)/100;
					
					double guidelineRatesResidential=0.0; 
					String guideRateResidential = "";
					guideRateResidential=propDAO.getL1Rate(propDTO,"18","2");  // to get residential Multistorey rate
					guideRateResidential = guideRateResidential == null?"":guideRateResidential;
					
					if(guideRateResidential.equals(""))
					{
						logger.debug("if^^^^^^^^^^^^^^^^^^^^^^");
						guideRateResidential=propDAO.getL2Rate(propDTO,"7","1");  // RCC RATE in case multistorey rate is not defined
					}
					guidelineRatesResidential = Double.parseDouble(guideRateResidential);
					Double baseValueResidential = guidelineRatesResidential;
					Double returnValueResidential = guidelineRatesResidential;
					double areaRes=Double.parseDouble(propDTO.getFloorArea());
					if("YES".equalsIgnoreCase(propDTO.getIsOlder()))
					{	
						
						double residentialPlotRates=0.0;//SIMRAN
						
						String residentialPlotRate = "";
						
						int subPropertyTypeInComm = Integer.parseInt(propDTO.getMultiCommPropId());
						
						residentialPlotRate = propDAO.getL1Rate(propDTO, "1", "1");
						residentialPlotRates = Double.parseDouble(residentialPlotRate);
						
						
						if("20".equalsIgnoreCase(propDTO.getOlder()))
						{
							double olderRebate=10;
							double olderRebateMF=(olderRebate)/100;
							returnValueResidential=((multiplyingFactorResidential*(areaRes*guidelineRatesResidential))-(areaRes*(guidelineRatesResidential-residentialPlotRates)*olderRebateMF));
				
						}
						else if("50".equalsIgnoreCase(propDTO.getOlder()))
						{
							double olderRebate=20;
							double olderRebateMF=(olderRebate)/100;
							returnValueResidential=((multiplyingFactorResidential*(areaRes*guidelineRatesResidential))-(areaRes*(guidelineRatesResidential-residentialPlotRates)*olderRebateMF));
				
						}
						else
						{
							returnValueResidential=multiplyingFactorResidential*baseValueResidential*areaRes;
						}
						// to be check wheter less tahn resi value or not
						/*double resiBaseValue=0.0;  parameter
						double returnResiValue=0.0;  
						String resifloorId="";
						
						if(propDTO.getIsLift().equalsIgnoreCase("Y")|| propDTO.getNearRoad().equalsIgnoreCase("Y"))
						{
							floorId="3";   // for ground floor
						}
						else 
						{
							floorId=propDTO.getFloorId();
							
						}*/
						
						
						
				}	
					else
					{
						
						returnValueResidential=multiplyingFactorResidential*baseValueResidential*areaRes;
						
					}
					logger.debug("return val res^^^^^^^^^^^^"+returnValueResidential);
					if(returnValueResidential>returnValue)
					{	
						returnValue = returnValueResidential;
					}	
				}
				
			
		}	
			
		
		if("YES".equalsIgnoreCase(propDTO.getIsOpenTerraceFlag()))
		{
		returnValue=returnValue+Double.parseDouble(calcOpenTerrace(propDTO)[1]);
		}
		
		logger.debug("return value with internal subclausescalculate bilding:"+returnValue);
		return String.valueOf(returnValue);
		}
		
		else if (propDTO.getBuildingTypeId().equalsIgnoreCase("2"))
		{
			String newBaseValue="";
			double returnValue=Double.parseDouble(baseValue);
			String floorId="";
			if("Y".equalsIgnoreCase(propDTO.getIsLift()))
			{
				floorId="3";   // for ground floor
			}
			else
			{
				floorId=propDTO.getFloorId();
			}

			double rebate=Double.parseDouble(propDAO.getFloorRebate(floorId,"31"));
			double multiplyingFactor=(100-rebate)/100;
			double guidelineRates=Double.parseDouble(propDAO.getL2Rate(propDTO,"7","1"));
			double rseidentialPlotRate=Double.parseDouble(propDAO.getL1Rate(propDTO,"1","1"));
			/*	if("YES".equalsIgnoreCase(propDTO.getIsOlder()))
		{
			 // from guideline  rcc rates

			double area=Double.parseDouble(propDTO.getFloorArea());
			if("20".equalsIgnoreCase(propDTO.getOlder()))
			{
				double olderRebate=10;
				double olderRebateMF=(olderRebate)/100;
				returnValue=((multiplyingFactor*(area*guidelineRates))-(area*(guidelineRates-rseidentialPlotRate)*olderRebateMF));

			}
			else if("50".equalsIgnoreCase(propDTO.getOlder()))
			{
				double olderRebate=20;
				double olderRebateMF=(olderRebate)/100;
				returnValue=((multiplyingFactor*(area*guidelineRates))-(area*(guidelineRates-rseidentialPlotRate)*olderRebateMF));

			}
			else
			{
				returnValue=multiplyingFactor*Double.parseDouble(baseValue);

			}

		}*/

			double slabOperand	=	0.0;
			String slabOperator="";
			for(PropertyValuationDTO dto : propDTO.getConstructionSlabList())
			{
				if(dto.getOlderId()==propDTO.getOlderId()){
					slabOperand = dto.getOlderOperand();
					slabOperator = dto.getOperatorname();
					System.out.println("slabOperand : "+ slabOperand+" slabOperator : "+slabOperator);
				}
			}

			if(propDTO.getOlderId()>0){  // Construction cost slab

				double area=Double.parseDouble(propDTO.getFloorArea());

				double olderRebate=slabOperand;
				double olderRebateMF=(olderRebate)/100;
				returnValue=((multiplyingFactor*(area*guidelineRates))-(area*(guidelineRates-rseidentialPlotRate)*olderRebateMF));


			}

			else
			{
				returnValue=multiplyingFactor*Double.parseDouble(baseValue);
			}
			double multResiRates=0.0;
			double multiResiReturnValue=0.0;
			String multiRates=propDAO.getL1Rate(propDTO,"18","2");


			rebate=Double.parseDouble(propDAO.getFloorRebate(floorId,"18"));
			multiplyingFactor=(100-rebate)/100;

			//added by akansha: changes reverted:

			//String multiRates=propDAO.getL2Rate(propDTO,"7","1");//rcc rate calculation

			/*if(multiRates==null ||multiRates.equalsIgnoreCase("")||Double.parseDouble(multiRates)==0){
				multiRates=propDAO.getL1Rate(propDTO,"18","2");
			}*/

			if(multiRates==null||multiRates.equalsIgnoreCase("")||Double.parseDouble(multiRates)==0)
			{
				//multResiRates=guidelineRates;
				multResiRates=0;
			}
			else
			{
				multResiRates=Double.parseDouble(multiRates);
			}
			String isOlder=propDTO.getIsOlder();
			String older=propDTO.getOlder();
			/*if("YES".equalsIgnoreCase(isOlder))
		{
			double area=Double.parseDouble(propDTO.getFloorArea());
			if("20".equalsIgnoreCase(older))
			{
				double olderRebate=10;
				double olderRebateMF=(olderRebate)/100;
				multiResiReturnValue=((multiplyingFactor*(area*multResiRates))-(area*(multResiRates-rseidentialPlotRate)*olderRebateMF));
			}
			else if("50".equalsIgnoreCase(older))
			{
				double olderRebate=20;
				double olderRebateMF=(olderRebate)/100;
				multiResiReturnValue=((multiplyingFactor*(area*multResiRates))-(area*(multResiRates-rseidentialPlotRate)*olderRebateMF));
			}
			else
			{
				multiResiReturnValue=multiplyingFactor*multResiRates*Double.parseDouble(propDTO.getFloorArea());
			}
		}*/

			if(propDTO.getOlderId()>0){  // Construction cost slab

				double area=Double.parseDouble(propDTO.getFloorArea());

				double olderRebate=slabOperand;
				double olderRebateMF=(olderRebate)/100;
				multiResiReturnValue=((multiplyingFactor*(area*multResiRates))-(area*(multResiRates-rseidentialPlotRate)*olderRebateMF));


			}

			else
			{
				multiResiReturnValue=multiplyingFactor*multResiRates*Double.parseDouble(propDTO.getFloorArea());
			}
			if(multiResiReturnValue>=returnValue)
			{
				returnValue=multiResiReturnValue;
			}
			if("YES".equalsIgnoreCase(propDTO.getIsOpenTerraceFlag()))
			{
				returnValue=returnValue+Double.parseDouble(calcOpenTerrace(propDTO)[1]);
			}
			return String.valueOf(returnValue);

		}
		
		return baseValue;
		}
	
	public String[] calcWithSubclauses(String baseValues,String ids[], PropertyValuationDTO propDTO) throws Exception{
		//calculation logic with subclauses
		String marketValue[]=new String[3];
		String value="";          // this variable will be used while debugging the code. will show current value after recent calculation in string.
		if(propDTO.getBuildingTypeId().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_ID)){
			double summationValue=0.0;
			double finalValue=Double.parseDouble(baseValues);
			double constCost=propDTO.getConstCost();
			double finalConstCost=propDTO.getConstCost();
			double natHighway=0.0;
			double operandHighway=0.0;
			boolean colonyFlag=false;
			double operandLeglColny=10000; // set as 110 because operator is -%, so less than comparison is required. By Roopam
			double distHighway=0.0;
			double operandDouble=0.0;
			double constSlabOperand	=	0.0;
			String constSlabOperator="";
			String ewsFlag=propDTO.getBuildOnlyResi();
			PropertyValuationDAO propDAO=new PropertyValuationDAO();
			ArrayList guideLineValues=propDAO.calcWithSubclauses(propDTO.getPropertyId());
			PropertyValuationBO propBO = new PropertyValuationBO();
			//ArrayList constSlab =  propDAO.getConstructionSlabListBySlabMapId(propDTO.getSlabMapId());

			double baseValue=Double.parseDouble(baseValues);
			double totArea=0;                 // used in gandi basti sub clause
			if(propDTO.getPlotTotArea()!=null && !propDTO.getPlotTotArea().equalsIgnoreCase("")){
				totArea=Double.parseDouble(propDTO.getPlotTotArea());
			}


			for(PropertyValuationDTO dto : propDTO.getConstructionSlabList())
			{
				if(dto.getOlderId()==propDTO.getOlderId()){
					constSlabOperand = dto.getOlderOperand();
					constSlabOperator = dto.getOperatorname();
					System.out.println("constSlabOperand : "+ constSlabOperand+" constSlabOperator : "+constSlabOperator);
				}
			}


			for(int j=1;j<ids.length;j++){
				for(int i=0;i<guideLineValues.size();i++){
					ArrayList subList=(ArrayList)guideLineValues.get(i);
					if(ids[j].trim().equalsIgnoreCase(subList.get(0).toString())){
						String operator=subList.get(1).toString();
						String operand=subList.get(2).toString();

						if(ids[j].trim().equalsIgnoreCase("1")||ids[j].trim().equalsIgnoreCase("2")||ids[j].trim().equalsIgnoreCase("3")) // highway sub clauses
						{
							if(Double.parseDouble(operand) > operandHighway)
							{
								operandHighway=Double.parseDouble(operand);
							}
						}else if((ids[j].trim().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_SBCL_ID_20)
						||ids[j].trim().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_SBCL_ID_50)) && !propDTO.getIsOnlyResidential().equalsIgnoreCase("R")){  // older than 20 or 50 sub clauses

							if(operator.equalsIgnoreCase("+%")){
								finalConstCost=constCost+(constCost*(Double.valueOf(operand)/100));
								value=BigDecimal.valueOf(finalConstCost).toPlainString();

							}
							if(operator.equalsIgnoreCase("-%")){
								finalConstCost=constCost-(constCost*(Double.valueOf(operand)/100));
								value=BigDecimal.valueOf(finalConstCost).toPlainString();
								ArrayList list =  propDAO.getConstructionSlabListBySlabMapId(propDTO.getOlderId());
							}

						}




						else if((ids[j].trim().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_SBCL_ID_GANDI_BASTI)) ||
						ids[j].trim().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_SBCL_ID_LGL_COL)){            // gandi basti and legal colony sub clauses

							if(ids[j].trim().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_SBCL_ID_GANDI_BASTI))
							{
								if(totArea<=35)
								{
									if(Double.parseDouble(operand) < operandLeglColny)
									{
										operandLeglColny=Double.parseDouble(operand);
										colonyFlag=true;

									}

								}
							}
							else
							{
								if("Y".equalsIgnoreCase(ewsFlag))
								{
									if(Double.parseDouble(operand) < operandLeglColny)
									{
										operandLeglColny=Double.parseDouble(operand);
										colonyFlag=true;

									}
								}
							}
						}
						/*else {  //THIS CONDITION FOR CORNER PLOT SUB CLAUSE
						if(operator.equalsIgnoreCase("+%")){
							finalValue=baseValue+(baseValue*(Double.valueOf(operand)/100));
							value=BigDecimal.valueOf(finalValue).toPlainString();

						}
						if(operator.equalsIgnoreCase("-%")){
							finalValue=baseValue-(baseValue*(Double.valueOf(operand)/100));
							value=BigDecimal.valueOf(finalValue).toPlainString();

						}
					}*/
						else if(ids[j].trim().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_CORNER_PLOT)) {  //THIS CONDITION FOR CORNER PLOT SUB CLAUSE
							if(operator.equalsIgnoreCase("+%")){
								finalValue=baseValue+(baseValue*(Double.valueOf(operand)/100));
								value=BigDecimal.valueOf(finalValue).toPlainString();

							}
							if(operator.equalsIgnoreCase("-%")){
								finalValue=baseValue-(baseValue*(Double.valueOf(operand)/100));
								value=BigDecimal.valueOf(finalValue).toPlainString();

							}
						}
						//added by saurav for adding new sub clause under 
						else if (ids[j].trim().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_COMM_OLD_50)){
							  //this condition is for adding new sub clause under independent commercial building
							if(operator.equalsIgnoreCase("-%")){
								finalConstCost=constCost-(constCost*(Double.valueOf(operand)/100));
								value=BigDecimal.valueOf(finalConstCost).toPlainString();

							}
						
						}
						else if (ids[j].trim().equalsIgnoreCase(PropertyValuationConstant.IND_BUILD_COMM_OLD_25)){
							  //this condition is for adding new sub clause under independent commercial building
							if(operator.equalsIgnoreCase("-%")){
								finalConstCost=constCost-(constCost*(Double.valueOf(operand)/100));
								value=BigDecimal.valueOf(finalConstCost).toPlainString();

							}
						
						}
						//}

					}
				}

			}

			/*Added by  Rustam*/
			if(propDTO.getOlderId()>0){  // Construction cost slab

				if("+%".equalsIgnoreCase(constSlabOperator)){

					finalConstCost=constCost+(constCost*(Double.valueOf(constSlabOperand)/100));
					value=BigDecimal.valueOf(finalConstCost).toPlainString();

				}
				if("-%".equalsIgnoreCase(constSlabOperator)){

					finalConstCost=constCost-(constCost*(Double.valueOf(constSlabOperand)/100));
					value=BigDecimal.valueOf(finalConstCost).toPlainString();

				}

			}


			if(colonyFlag==false)
			{
				operandLeglColny=0.0;
			}
			finalValue=finalValue+(baseValue*operandHighway/100);  //APPLYING HIGHWAY OPERAND
			value=BigDecimal.valueOf(finalValue).toPlainString();  //VALUE AFTER APPLYING HIGHWAY OPERAND
			finalValue=finalValue-(baseValue*operandLeglColny/100);  //APPLYING LEGAL COLONY/GANDI BASTI OPERAND

			finalConstCost=finalConstCost-(constCost*operandLeglColny/100);
			value=BigDecimal.valueOf(finalValue).toPlainString();    //VALUE AFTER APPLYING LEGAL COLONY/GANDI BASTI OPERAND
			summationValue=finalValue+finalConstCost;
			value=BigDecimal.valueOf(summationValue).toPlainString();

			marketValue[0]=BigDecimal.valueOf(summationValue).toPlainString();
			marketValue[1]=BigDecimal.valueOf(finalValue).toPlainString();
			marketValue[2]=BigDecimal.valueOf(finalConstCost).toPlainString();
		}
		return marketValue;
		
}
	public double calculateFloorValue(PropertyValuationDTO[] dto)
	{
		double floorRebate=0.0;
		double floorValue=0.0;
		String floorId="";
		String propertyL1="";
		PropertyValuationDTO floorDTO=dto[0];
		PropertyValuationDTO residto=dto[1];
		PropertyValuationDTO commdto=dto[2];
		PropertyValuationDTO inddto=dto[3];
		PropertyValuationDTO schooldto=dto[4];
		PropertyValuationDTO healthdto=dto[5];
		PropertyValuationDTO otherdto=dto[6];
		PropertyValuationDTO propdto=dto[7];
		PropertyValuationDTO resiCommDto=dto[8];
		floorId=floorDTO.getFloorId();
		if(commdto!=null)
		{
			propertyL1="8";  //commercial
		}
		else
		{
			propertyL1="7";  //residential
		}
		//floorRebate=Double.parseDouble(propDAO.getFloorRebate(floorId,propertyL1));
		//double multiplyingFactor=(100-floorRebate)/100;
		double residentialRate=Double.parseDouble(propDAO.getL1Rate(propdto,"1","1"));//residential plot rate
		double rccRate= Double.parseDouble(propDAO.getL2Rate(propdto,"7","1"));
		double rbcRate= Double.parseDouble(propDAO.getL2Rate(propdto,"7","2"));
		double tinRate=Double.parseDouble(propDAO.getL2Rate(propdto,"7","3"));
		double kacchaRate=Double.parseDouble(propDAO.getL2Rate(propdto,"7","4"));
		if(residto!=null)
		{
			double rccArea=0.0;
			double rbcArea=0.0;
			double tinArea=0.0;
			double kacchaArea=0.0;
			if("7".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			else if("6".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			String rebate=propDAO.getFloorRebate(floorId,"7");
			if(rebate!=null && !rebate.equalsIgnoreCase(""))
			{	
			floorRebate=Double.parseDouble(rebate);
			}
			else
			{
			floorRebate=0.0;
			}
			double multiplyingFactor=(100-floorRebate)/100;
			if(residto.getRccArea()!=null && !residto.getRccArea().equalsIgnoreCase("")){
			rccArea=Double.parseDouble(residto.getRccArea());
			}
			if(residto.getRbcArea()!=null && !residto.getRbcArea().equalsIgnoreCase("")){
			rbcArea=Double.parseDouble(residto.getRbcArea());
			}
			if(residto.getTinArea()!=null && !residto.getTinArea().equalsIgnoreCase("")){
			tinArea=Double.parseDouble(residto.getTinArea());
			}
			if(residto.getKacchaArea()!=null && !residto.getKacchaArea().equalsIgnoreCase("")){
			kacchaArea=Double.parseDouble(residto.getKacchaArea());
			}
			
			
			floorValue=floorValue+multiplyingFactor*((rccRate*rccArea+rbcArea*rbcRate+tinArea*tinRate+kacchaArea*kacchaRate)-(rccArea+rbcArea+tinArea+kacchaArea)*residentialRate);
			
		}
		if(commdto!=null)
		{
			double shopArea=0.0;
			double officeArea=0.0;
			double godownArea=0.0;
			floorId=floorDTO.getFloorId();
			String rebate=propDAO.getFloorRebate(floorId,"8");
			if(rebate!=null && !rebate.equalsIgnoreCase(""))
			{	
			floorRebate=Double.parseDouble(rebate);
			}
			else
			{
			floorRebate=0.0;
			}
			double multiplyingFactor=(100-floorRebate)/100;
			double commercialRate=Double.parseDouble(propDAO.getL1Rate(propdto,"2","1"));
			double shopRate=Double.parseDouble(propDAO.getL2Rate(propdto,"8","5"));
			double officeRate=Double.parseDouble(propDAO.getL2Rate(propdto,"8","6"));
			double godownRate=Double.parseDouble(propDAO.getL2Rate(propdto,"8","7"));
			
			if(commdto.getShopArea()!=null && !commdto.getShopArea().equalsIgnoreCase("")){
			shopArea=Double.parseDouble(commdto.getShopArea());
			}
			if(commdto.getOfficeArea()!=null && !commdto.getOfficeArea().equalsIgnoreCase("")){
			officeArea=Double.parseDouble(commdto.getOfficeArea());
			}
			if(commdto.getGodownArea()!=null && !commdto.getGodownArea().equalsIgnoreCase("")){
			godownArea=Double.parseDouble(commdto.getGodownArea());
			}
			
			floorValue=floorValue+multiplyingFactor*((shopArea*shopRate+officeArea*officeRate+godownArea*godownRate)-(shopArea+officeArea+godownArea)*commercialRate);
		}
		if(inddto!=null)
		{
			if("7".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			else if("6".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			double rccArea=0.0;
			double rbcArea=0.0;
			double tinArea=0.0;
			double kacchaArea=0.0;

			double godownRateL2 = Double.parseDouble(propDAO.getL2Rate(propdto,"8","7"));
			String rebate=propDAO.getFloorRebate(floorId,"7");
			if(rebate!=null && !rebate.equalsIgnoreCase(""))
			{	
			floorRebate=Double.parseDouble(rebate);
			}
			else
			{
			floorRebate=0.0;
			}
			double multiplyingFactor=(100-floorRebate)/100;
			if(inddto.getRccArea()!=null && !inddto.getRccArea().equalsIgnoreCase("")){
			rccArea=Double.parseDouble(inddto.getRccArea());
			}
			if(inddto.getRbcArea()!=null && !inddto.getRbcArea().equalsIgnoreCase("")){
			rbcArea=Double.parseDouble(inddto.getRbcArea());
			}
			if(inddto.getTinArea()!=null && !inddto.getTinArea().equalsIgnoreCase("")){
			tinArea=Double.parseDouble(inddto.getTinArea());
			}
			if(inddto.getKacchaArea()!=null && !inddto.getKacchaArea().equalsIgnoreCase("")){
			kacchaArea=Double.parseDouble(inddto.getKacchaArea());
			}
			
			/*double rccArea=Double.parseDouble(inddto.getRccArea());
			double rbcArea=Double.parseDouble(inddto.getRbcArea());
			double tinArea=Double.parseDouble(inddto.getTinArea());
			double kacchaArea=Double.parseDouble(inddto.getKacchaArea());*/
			//floorValue=floorValue+multiplyingFactor*0.7*((rccRate*rccArea+rbcArea*rbcRate+tinArea*tinRate+kacchaArea*kacchaRate)-(rccArea+rbcArea+tinArea+kacchaArea)*residentialRate);
			//above logic commented by saurav on 19/07/21 for changing the logic of industrial build prop valuation
			floorValue=floorValue+multiplyingFactor*0.7*(((rccArea+rbcArea+tinArea+kacchaArea)*godownRateL2)-(rccArea+rbcArea+tinArea+kacchaArea)*residentialRate);
			
		}
		if(schooldto!=null)
		{
			double rccArea=0.0;
			double rbcArea=0.0;
			double tinArea=0.0;
			double kacchaArea=0.0;
			if("7".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			else if("6".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			String rebate=propDAO.getFloorRebate(floorId,"7");
			if(rebate!=null && !rebate.equalsIgnoreCase(""))
			{	
			floorRebate=Double.parseDouble(rebate);
			}
			else
			{
			floorRebate=0.0;
			}
			double multiplyingFactor=(100-floorRebate)/100;
			if(schooldto.getRccArea()!=null && !schooldto.getRccArea().equalsIgnoreCase("")){
			rccArea=Double.parseDouble(schooldto.getRccArea());
			}
			if(schooldto.getRbcArea()!=null && !schooldto.getRbcArea().equalsIgnoreCase("")){
			rbcArea=Double.parseDouble(schooldto.getRbcArea());
			}
			if(schooldto.getTinArea()!=null && !schooldto.getTinArea().equalsIgnoreCase("")){
			tinArea=Double.parseDouble(schooldto.getTinArea());
			}
			if(schooldto.getKacchaArea()!=null && !schooldto.getKacchaArea().equalsIgnoreCase("")){
			kacchaArea=Double.parseDouble(schooldto.getKacchaArea());
			}
			
			/*double rccArea=Double.parseDouble(schooldto.getRccArea());
			double rbcArea=Double.parseDouble(schooldto.getRbcArea());
			double tinArea=Double.parseDouble(schooldto.getTinArea());
			double kacchaArea=Double.parseDouble(schooldto.getKacchaArea());*/
			floorValue=floorValue+multiplyingFactor*((rccRate*rccArea+rbcArea*rbcRate+tinArea*tinRate+kacchaArea*kacchaRate)-(rccArea+rbcArea+tinArea+kacchaArea)*residentialRate);
	
		}
		if(healthdto!=null)
		{
			
			double rccArea=0.0;
			double rbcArea=0.0;
			double tinArea=0.0;
			double kacchaArea=0.0;
			if("7".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			else if("6".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			String rebate=propDAO.getFloorRebate(floorId,"7");
			if(rebate!=null && !rebate.equalsIgnoreCase(""))
			{	
			floorRebate=Double.parseDouble(rebate);
			}
			else
			{
			floorRebate=0.0;
			}
			double multiplyingFactor=(100-floorRebate)/100;
			if(healthdto.getRccArea()!=null && !healthdto.getRccArea().equalsIgnoreCase("")){
			rccArea=Double.parseDouble(healthdto.getRccArea());
			}
			if(healthdto.getRbcArea()!=null && !healthdto.getRbcArea().equalsIgnoreCase("")){
			rbcArea=Double.parseDouble(healthdto.getRbcArea());
			}
			if(healthdto.getTinArea()!=null && !healthdto.getTinArea().equalsIgnoreCase("")){
			tinArea=Double.parseDouble(healthdto.getTinArea());
			}
			if(healthdto.getKacchaArea()!=null && !healthdto.getKacchaArea().equalsIgnoreCase("")){
			kacchaArea=Double.parseDouble(healthdto.getKacchaArea());
			}
			
			/*double rccArea=Double.parseDouble(healthdto.getRccArea());
			double rbcArea=Double.parseDouble(healthdto.getRbcArea());
			double tinArea=Double.parseDouble(healthdto.getTinArea());
			double kacchaArea=Double.parseDouble(healthdto.getKacchaArea());*/
			floorValue=floorValue+multiplyingFactor*((rccRate*rccArea+rbcArea*rbcRate+tinArea*tinRate+kacchaArea*kacchaRate)-(rccArea+rbcArea+tinArea+kacchaArea)*residentialRate);
	
		}
		if(otherdto!=null)
		{
			double rccArea=0.0;
			double rbcArea=0.0;
			double tinArea=0.0;
			double kacchaArea=0.0;
			if("7".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			else if("6".equalsIgnoreCase(floorId))
			{
				floorId="8";
			}
			String rebate=propDAO.getFloorRebate(floorId,"7");
			if(rebate!=null && !rebate.equalsIgnoreCase(""))
			{	
			floorRebate=Double.parseDouble(rebate);
			}
			else
			{
			floorRebate=0.0;
			}
			double multiplyingFactor=(100-floorRebate)/100;
			if(otherdto.getRccArea()!=null && !otherdto.getRccArea().equalsIgnoreCase("")){
			rccArea=Double.parseDouble(otherdto.getRccArea());
			}
			if(otherdto.getRbcArea()!=null && !otherdto.getRbcArea().equalsIgnoreCase("")){
			rbcArea=Double.parseDouble(otherdto.getRbcArea());
			}
			if(otherdto.getTinArea()!=null && !otherdto.getTinArea().equalsIgnoreCase("")){
			tinArea=Double.parseDouble(otherdto.getTinArea());
			}
			if(otherdto.getKacchaArea()!=null && !otherdto.getKacchaArea().equalsIgnoreCase("")){
			kacchaArea=Double.parseDouble(otherdto.getKacchaArea());
			}
			
			/*double rccArea=Double.parseDouble(otherdto.getRccArea());
			double rbcArea=Double.parseDouble(otherdto.getRbcArea());
			double tinArea=Double.parseDouble(otherdto.getTinArea());
			double kacchaArea=Double.parseDouble(otherdto.getKacchaArea());*/
			floorValue=floorValue+multiplyingFactor*((rccRate*rccArea+rbcArea*rbcRate+tinArea*tinRate+kacchaArea*kacchaRate)-(rccArea+rbcArea+tinArea+kacchaArea)*residentialRate);
	
		}
		if(resiCommDto!=null)
		{
			double rccArea=0.0;
			double rbcArea=0.0;
			double tinArea=0.0;
			double kacchaArea=0.0;
			double shopArea=0.0;
			double officeArea=0.0;
			double godownArea=0.0;
			floorId=floorDTO.getFloorId();
			String commrebate=propDAO.getFloorRebate(floorId,"8");
			if(commrebate!=null && !commrebate.equalsIgnoreCase(""))
			{	
			floorRebate=Double.parseDouble(commrebate);
			}
			else
			{
			floorRebate=0.0;
			}
			double multiplyingFactorComm=(100-floorRebate)/100;
			double commercialRate=Double.parseDouble(propDAO.getL1Rate(propdto,"2","1"));
			double shopRate=Double.parseDouble(propDAO.getL2Rate(propdto,"8","5"));
			double officeRate=Double.parseDouble(propDAO.getL2Rate(propdto,"8","6"));
			double godownRate=Double.parseDouble(propDAO.getL2Rate(propdto,"8","7"));
			if(resiCommDto.getShopArea()!=null && !resiCommDto.getShopArea().equalsIgnoreCase("")){
				shopArea=Double.parseDouble(resiCommDto.getShopArea());
				}
				if(resiCommDto.getOfficeArea()!=null && !resiCommDto.getOfficeArea().equalsIgnoreCase("")){
				officeArea=Double.parseDouble(resiCommDto.getOfficeArea());
				}
				if(resiCommDto.getGodownArea()!=null && !resiCommDto.getGodownArea().equalsIgnoreCase("")){
				godownArea=Double.parseDouble(resiCommDto.getGodownArea());
				}
				
				floorValue=floorValue+multiplyingFactorComm*((shopArea*shopRate+officeArea*officeRate+godownArea*godownRate)-(shopArea+officeArea+godownArea)*commercialRate);
				if("7".equalsIgnoreCase(floorId))
				{
					floorId="8";
				}
				else if("6".equalsIgnoreCase(floorId))
				{
					floorId="8";
				}
				String rebate=propDAO.getFloorRebate(floorId,"7");
				if(rebate!=null && !rebate.equalsIgnoreCase(""))
				{	
				floorRebate=Double.parseDouble(rebate);
				}
				else
				{
				floorRebate=0.0;
				}
				double multiplyingFactor=(100-floorRebate)/100;
				if(resiCommDto.getRccArea()!=null && !resiCommDto.getRccArea().equalsIgnoreCase("")){
				rccArea=Double.parseDouble(resiCommDto.getRccArea());
				}
				if(resiCommDto.getRbcArea()!=null && !resiCommDto.getRbcArea().equalsIgnoreCase("")){
				rbcArea=Double.parseDouble(resiCommDto.getRbcArea());
				}
				if(resiCommDto.getTinArea()!=null && !resiCommDto.getTinArea().equalsIgnoreCase("")){
				tinArea=Double.parseDouble(resiCommDto.getTinArea());
				}
				if(resiCommDto.getKacchaArea()!=null && !resiCommDto.getKacchaArea().equalsIgnoreCase("")){
				kacchaArea=Double.parseDouble(resiCommDto.getKacchaArea());
				}
				floorValue=floorValue+multiplyingFactor*((rccRate*rccArea+rbcArea*rbcRate+tinArea*tinRate+kacchaArea*kacchaRate)-(rccArea+rbcArea+tinArea+kacchaArea)*residentialRate);
			
		}
		return Math.ceil(floorValue);
		
	
	}
	
	//ADDED BY SIMRAN
	
	public Double calcWithSubclauses(String baseValue,PropertyValuationDTO propDTO){
		Double finalValue = 0.0;
		Double baseVal = Double.parseDouble(baseValue);
		ArrayList completeSubClauseList = propDAO.calcWithSubclausesBuilding();
		String subIds[] = propDTO.getBuildingSubId().split("~");
		
		for(int j = 0 ;j < subIds.length ; j++)
		{
			for(int i=0;i<completeSubClauseList.size();i++){
				ArrayList subList=(ArrayList)completeSubClauseList.get(i);
				
				
				if(subIds[j].equalsIgnoreCase(subList.get(0).toString())){
					String operator=subList.get(1).toString();
					String operand=subList.get(2).toString();
					logger.debug("MATCH");
					
					if(operator.equalsIgnoreCase("+%")){
						finalValue=baseVal+(baseVal*(Double.valueOf(operand)/100));
						
					}
					if(operator.equalsIgnoreCase("-%")){
						finalValue=baseVal-(baseVal*(Double.valueOf(operand)/100));
						
					}
					
					break;
				}
				
			}
		}
		if(finalValue==0)
		{
			finalValue=baseVal;
		}
		logger.debug("calculate Building: after subcluase"+finalValue);
		return finalValue;
	}
}