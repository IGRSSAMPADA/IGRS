package com.wipro.igrs.newPropvaluation.action;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.newPropvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.newPropvaluation.constant.PropertyValuationConstant;
import com.wipro.igrs.newPropvaluation.dao.PropertyValuationDAO;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;

public class CalculatePlot {
	
	
	/*private PropertyValuationDAO propDAO;
	public CalculatePlot() throws Exception
	{
		propDAO= new PropertyValuationDAO();
	}*/
	private static Logger logger = org.apache.log4j.Logger.getLogger(PropertyValuationBO.class);

	public String calcWithoutSubclauses(PropertyValuationDTO propDTO,String eduTcp,String healthTcp) throws Exception{
		String marketValue="";
		//ArrayList list=new ArrayList();
		//list=propDAO.getGuidelineId(propDTO);
		double resiArea=0.0;
		double commArea=0.0;
		double	indArea=0.0;
		double healthArea=0.0;
		double schoolArea=0.0;
		double otherArea=0.0;
		double resiCommArea=0.0;
		double baseValue=0.0;
		double resiValue=0.0;
		double marketVal=0.0;
		
		if(propDTO.getPlotResiArea()!=null && !propDTO.getPlotResiArea().equalsIgnoreCase(""))
		{	
		resiArea=Double.valueOf(propDTO.getPlotResiArea());
		if(resiArea>0)
		{	
		propDTO.setPlotOnlyResi("Y");
		}
		}
		if(propDTO.getPlotCommArea()!=null && !propDTO.getPlotCommArea().equalsIgnoreCase(""))
		{
		commArea=Double.valueOf(propDTO.getPlotCommArea());
		if(commArea>0)
		{	
		propDTO.setPlotOnlyResi("N");
		}
		}
		if(propDTO.getPlotIndusArea()!=null && !propDTO.getPlotIndusArea().equalsIgnoreCase(""))
		{
		indArea=Double.valueOf(propDTO.getPlotIndusArea());
		if(indArea>0)
		{	
		propDTO.setPlotOnlyResi("N");
		}
		
		}
		if(propDTO.getPlotHealthArea()!=null && !propDTO.getPlotHealthArea().equalsIgnoreCase(""))
		{
		
			healthArea=Double.valueOf(propDTO.getPlotHealthArea());
		if(healthArea>0)
		{	
			propDTO.setPlotOnlyResi("N");
		}
		}
		if(propDTO.getPlotSchoolArea()!=null && !propDTO.getPlotSchoolArea().equalsIgnoreCase(""))
		{
		schoolArea=Double.valueOf(propDTO.getPlotSchoolArea());
		if(schoolArea>0)
		{	
		
		propDTO.setPlotOnlyResi("N");
		}
		}
		if(propDTO.getPlotOtherArea()!=null && !propDTO.getPlotOtherArea().equalsIgnoreCase(""))
		{
		otherArea=Double.valueOf(propDTO.getPlotOtherArea());
		if(otherArea>0)
		{	
		
		propDTO.setPlotOnlyResi("N");
		}
		
		}
		if(propDTO.getPlotResicumComm()!=null && !propDTO.getPlotResicumComm().equalsIgnoreCase(""))
		{
			
			resiCommArea=Double.valueOf(propDTO.getPlotResicumComm());
			if(resiCommArea>0)
			{	
			propDTO.setPlotOnlyResi("N");
			}
		}
		PropertyValuationDAO propDAO=new PropertyValuationDAO();
		ArrayList guideLineValues=propDAO.getPlotGuidelineValues(propDTO);
		if(guideLineValues!=null){
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
						baseValue=baseValue+(resiArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_COMMERCIAL_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						baseValue=baseValue+(commArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_INDUSTRIAL_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						baseValue=baseValue+(indArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_HEALTH_L1)
				{
					if(healthTcp!=null && healthTcp.equalsIgnoreCase("Y"))
					{	
						if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
						{
						baseValue=baseValue+(healthArea*Double.valueOf(subList.get(0).toString()));
				
						}
					}
					else
					{
						baseValue=baseValue+(healthArea*resiValue);
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_EDUCATIONAL_L1)
				{
					if(eduTcp!=null && eduTcp.equalsIgnoreCase("Y"))
					{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
						{
						
						baseValue=baseValue+(schoolArea*Double.valueOf(subList.get(0).toString()));
				
						}
					}
					else
					{
						baseValue=baseValue+(schoolArea*resiValue);
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_OTHER_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						baseValue=baseValue+(otherArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
				else 
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						baseValue=baseValue+(resiCommArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
			}		
		}
		
		
		}
		marketVal=baseValue;
		
		try{
		
		}catch (Exception e) {
			logger.error(e);
			
		}
		marketValue=BigDecimal.valueOf(marketVal).toPlainString();
		return marketValue;
		}
	
	//added for developer agreement
	
	public String calcWithoutSubclausesDevAgree(PropertyValuationDTO propDTO,String eduTcp,String healthTcp) throws Exception{
		String marketValue="";
		//ArrayList list=new ArrayList();
		//list=propDAO.getGuidelineId(propDTO);
		double resiArea=0.0;
		double commArea=0.0;
		double	indArea=0.0;
		double healthArea=0.0;
		double schoolArea=0.0;
		double otherArea=0.0;
		double resiCommArea=0.0;
		double baseValue=0.0;
		double resiValue=0.0;
		double marketVal=0.0;
		double totalArea=0;//added for developer agreement
		
		if(propDTO.getPlotResiArea()!=null && !propDTO.getPlotResiArea().equalsIgnoreCase(""))
		{	
		resiArea=Double.valueOf(propDTO.getPlotResiArea());
		if(resiArea>0)
		{	
		propDTO.setPlotOnlyResi("Y");
		}
		}
		if(propDTO.getPlotCommArea()!=null && !propDTO.getPlotCommArea().equalsIgnoreCase(""))
		{
		commArea=Double.valueOf(propDTO.getPlotCommArea());
		if(commArea>0)
		{	
		propDTO.setPlotOnlyResi("N");
		}
		}
		if(propDTO.getPlotIndusArea()!=null && !propDTO.getPlotIndusArea().equalsIgnoreCase(""))
		{
		indArea=Double.valueOf(propDTO.getPlotIndusArea());
		if(indArea>0)
		{	
		propDTO.setPlotOnlyResi("N");
		}
		
		}
		if(propDTO.getPlotHealthArea()!=null && !propDTO.getPlotHealthArea().equalsIgnoreCase(""))
		{
		
			healthArea=Double.valueOf(propDTO.getPlotHealthArea());
		if(healthArea>0)
		{	
			propDTO.setPlotOnlyResi("N");
		}
		}
		if(propDTO.getPlotSchoolArea()!=null && !propDTO.getPlotSchoolArea().equalsIgnoreCase(""))
		{
		schoolArea=Double.valueOf(propDTO.getPlotSchoolArea());
		if(schoolArea>0)
		{	
		
		propDTO.setPlotOnlyResi("N");
		}
		}
		if(propDTO.getPlotOtherArea()!=null && !propDTO.getPlotOtherArea().equalsIgnoreCase(""))
		{
		otherArea=Double.valueOf(propDTO.getPlotOtherArea());
		if(otherArea>0)
		{	
		
		propDTO.setPlotOnlyResi("N");
		}
		
		}
		if(propDTO.getPlotResicumComm()!=null && !propDTO.getPlotResicumComm().equalsIgnoreCase(""))
		{
			
			resiCommArea=Double.valueOf(propDTO.getPlotResicumComm());
			if(resiCommArea>0)
			{	
			propDTO.setPlotOnlyResi("N");
			}
		}
		//added for developer agreement
		totalArea = resiArea+commArea+indArea+schoolArea+healthArea+otherArea+resiCommArea;
		double share = propDTO.getBuilderShare();
		totalArea = totalArea*(share/100);
		double remainingArea=0.0;
		if(resiCommArea>0){ //if resi-cum commercial
			 resiArea=0.0;
			 commArea=0.0;
			 indArea=0.0;
			 healthArea=0.0;
			 schoolArea=0.0;
			 otherArea=0.0;
			 resiCommArea=Double.valueOf(propDTO.getPlotResicumComm());
			 if(resiCommArea>=totalArea){
				 resiCommArea = totalArea;
			 }
		}else if("Y".equalsIgnoreCase(propDTO.getPlotOnlyResi())){ //if only residential
			 commArea=0.0;
			 indArea=0.0;
			 healthArea=0.0;
			 schoolArea=0.0;
			 otherArea=0.0;
			 resiCommArea=0.0;
			 resiArea=Double.valueOf(propDTO.getPlotResiArea());
			 if(resiArea>=totalArea){
				 resiArea = totalArea;
			 }
		}else {
			 if(commArea>=totalArea){
				 resiArea=0.0;
				 indArea=0.0;
				 healthArea=0.0;
				 schoolArea=0.0;
				 otherArea=0.0;
				 resiCommArea=0.0;
				 commArea=totalArea;
			 }else{
				 remainingArea=totalArea-commArea;
				 resiArea = remainingArea;
				 indArea=0.0;
				 healthArea=0.0;
				 schoolArea=0.0;
				 otherArea=0.0;
				 resiCommArea=0.0;
			 }
		}
		logger.debug("resiArea :---> "+resiArea);
		logger.debug("commArea :---> "+commArea);
		logger.debug("indArea :---> "+indArea);
		logger.debug("schoolArea :---> "+schoolArea);
		logger.debug("healthArea :---> "+healthArea);
		logger.debug("otherArea :---> "+otherArea);
		logger.debug("resiCommArea :---> "+resiCommArea);
		
		PropertyValuationDAO propDAO=new PropertyValuationDAO();
		ArrayList guideLineValues=propDAO.getPlotGuidelineValues(propDTO);
		if(guideLineValues!=null){
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
						baseValue=baseValue+(resiArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_COMMERCIAL_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						baseValue=baseValue+(commArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_INDUSTRIAL_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						baseValue=baseValue+(indArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_HEALTH_L1)
				{
					if(healthTcp!=null && healthTcp.equalsIgnoreCase("Y"))
					{	
						if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
						{
						baseValue=baseValue+(healthArea*Double.valueOf(subList.get(0).toString()));
				
						}
					}
					else
					{
						baseValue=baseValue+(healthArea*resiValue);
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_EDUCATIONAL_L1)
				{
					if(eduTcp!=null && eduTcp.equalsIgnoreCase("Y"))
					{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
						{
						
						baseValue=baseValue+(schoolArea*Double.valueOf(subList.get(0).toString()));
				
						}
					}
					else
					{
						baseValue=baseValue+(schoolArea*resiValue);
					}
				}
				else if((i+1)==PropertyValuationConstant.PLOT_OTHER_L1)
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						baseValue=baseValue+(otherArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
				else 
				{
					if(subList.get(0)!=null&&!subList.get(0).toString().equalsIgnoreCase(""))
					{
						baseValue=baseValue+(resiCommArea*Double.valueOf(subList.get(0).toString()));
				
					}
				}
			}		
		}
		
		
		}
		marketVal=baseValue;
		
		try{
		
		}catch (Exception e) {
			logger.error(e);
			
		}
		marketValue=BigDecimal.valueOf(marketVal).toPlainString();
		return marketValue;
		}
	
	public String calcWithSubclauses(String baseValues,String ids[],String ewsFlag) throws Exception{
		//calculation logic with subclauses
		String marketValue="";
		double finalValue=Double.parseDouble(baseValues);
		double natHighway=0.0;
		double operandHighway=0.0;
		double operandGandiBasti=0.0;
		double distHighway=0.0;
		double operandDouble=0.0;
		PropertyValuationDAO propDAO=new PropertyValuationDAO();
		ArrayList guideLineValues=propDAO.calcWithSubclauses("1");
		double baseValue=Double.parseDouble(baseValues);
		for(int j=1;j<ids.length;j++){
			for(int i=0;i<guideLineValues.size();i++){
				ArrayList subList=(ArrayList)guideLineValues.get(i);
				if(ids[j].equalsIgnoreCase(subList.get(0).toString())){
					String operator=subList.get(1).toString();
					String operand=subList.get(2).toString();
					
					if(ids[j].equalsIgnoreCase("1")||ids[j].equalsIgnoreCase("2")||ids[j].equalsIgnoreCase("3"))
					{
						if(Double.parseDouble(operand) > operandHighway)
						{
							operandHighway=Double.parseDouble(operand);
						}
					}
					else if(ids[j].equalsIgnoreCase("6")||ids[j].equalsIgnoreCase("7"))
					{
						if(ids[j].equalsIgnoreCase("7")&&"Y".equalsIgnoreCase(ewsFlag))
						{	
						if(Double.parseDouble(operand) >= operandGandiBasti)
						{
							operandGandiBasti=Double.parseDouble(operand);
						}
						
						}
						else if(ids[j].equalsIgnoreCase("6"))
						{
							if(Double.parseDouble(operand) >= operandGandiBasti)
							{
								operandGandiBasti=Double.parseDouble(operand);
							}
						}
					}
					else
					{
						if(operator.equalsIgnoreCase("+%")){
							finalValue=finalValue+(baseValue*(Double.valueOf(operand)/100));
							
							
						}
						if(operator.equalsIgnoreCase("-%")){
							finalValue=finalValue-(baseValue*(Double.valueOf(operand)/100));
							
						}
					}
					
				}
				
				
				/*
			}
				ArrayList subList=(ArrayList)guideLineValues.get(i);
				if(ids[j].equalsIgnoreCase(subList.get(0).toString())){
					String operator=subList.get(1).toString();
					String operand=subList.get(2).toString();
				
					//String highId[]={"1","2","3"};
					//if(ids[i].contains(highId[2])){
					if(ids[j].equalsIgnoreCase("1")||ids[j].equalsIgnoreCase("2")||ids[j].equalsIgnoreCase("3")){
						operandDouble=Double.parseDouble(operand);
						if(ids[j].equalsIgnoreCase("1")){
							natHighway=operandDouble;
						}
						if(ids[j].equalsIgnoreCase("2")){
							stateHighway=operandDouble;
						}
						if(ids[j].equalsIgnoreCase("3")){
							distHighway=operandDouble;
						}
						if(natHighway!=0.0 || stateHighway!=0.0 || distHighway!=0.0){
						if(natHighway>stateHighway && natHighway>distHighway ){
							//j=j+2;
							operandDouble=natHighway;
						}else if(stateHighway>natHighway && stateHighway>distHighway){
							//j=j+1;
							operandDouble=stateHighway;
						}else if(distHighway>natHighway && distHighway>stateHighway){
							operandDouble=distHighway;
						}
						}
						operand=String.valueOf(operandDouble);
						if(ids[j].equalsIgnoreCase("1")||ids[j].equalsIgnoreCase("2") && 
								ids[j].equalsIgnoreCase("2")||ids[j].equalsIgnoreCase("3") &&
								ids[j].equalsIgnoreCase("1") || ids[j].equalsIgnoreCase("3")){
							if(j==1){
								continue;
								}
						}else {
						if(j==1||j==2){
						continue;
						}
				}
		
				
					if(operator.equalsIgnoreCase("+%")){
						finalValue=baseValue+(baseValue*(Double.valueOf(operand)/100));
					}
					if(operator.equalsIgnoreCase("-%")){
						finalValue=baseValue-(baseValue*(Double.valueOf(operand)/100));
					}
				}
			}
			
		*/}
		
		}
		finalValue=finalValue;
		finalValue=finalValue+(baseValue*operandHighway/100);
		finalValue=finalValue-(baseValue*operandGandiBasti/100);
		/*for(int k=0;k<ids.length;k++){
		if(ids[k].equalsIgnoreCase("1")||ids[k].equalsIgnoreCase("2")||ids[k].equalsIgnoreCase("3"))
		{*/
		//}
		
	

		//}
		marketValue=BigDecimal.valueOf(finalValue).toPlainString();
		return marketValue;
		
}
}