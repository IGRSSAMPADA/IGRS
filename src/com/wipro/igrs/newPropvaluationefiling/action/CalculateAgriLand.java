package com.wipro.igrs.newPropvaluationefiling.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.wipro.igrs.newPropvaluationefiling.bo.PropertyValuationBO;
import com.wipro.igrs.newPropvaluationefiling.dao.PropertyValuationDAO;
import com.wipro.igrs.newPropvaluationefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.newPropvaluationefiling.dto.TreeDTO;

public class CalculateAgriLand {
	private static Logger logger = org.apache.log4j.Logger.getLogger(PropertyValuationBO.class);
	private PropertyValuationDAO propDAO;
	
	public CalculateAgriLand() throws Exception
	{
		propDAO= new PropertyValuationDAO();
	}
	
	/*public double calcDiverted(dto){
	//calculation logic for Diverted
	calcFloorValue(dto);
	return value;
	}
	
	public double calcUndiverted (dto){
	
	return value;
	}
	
	public double calcBoth(dto){
	//calculation logic for both
	calcDiverted(dto);
	calcUndiverted(dto);
	return value;
	}
	
	public double calcWithoutSubclauses(dto){
		//calculation logic without subclauses.
		//based on sub type id, above methods will be called here
		return baseValue;
		}
		public double calcInternalSubclauses(baseValue,dto){
		//calculation logic with screen level subclauses
		return newBaseValue;
		}
		public double calcWithSubclauses(newBaseValue,Subclause ids){
		//calculation logic with subclauses
		return finalValue;
		}*/
	


		public String[] calcUndiverted(PropertyValuationDTO undivpropDTO, PropertyValuationDTO propDTO) {
			
			
			double value=0;
			if(propDTO.getConstructedAreaBuilding()==null || propDTO.getConstructedAreaBuilding().equalsIgnoreCase(""))
			{
				propDTO.setConstructedAreaBuilding("0.0");
			}
			double constructedArea=Double.parseDouble(propDTO.getConstructedAreaBuilding());// in hectares
			constructedArea=constructedArea/10000;
			double x=0.0;
			double TotalArea=0.0;
			TotalArea=Double.parseDouble(undivpropDTO.getAgriTotalUnDivertedArea());
			propDTO.setAgriTotalUnDivertedArea(undivpropDTO.getAgriTotalUnDivertedArea());
			String guidelineId="";
			String subIds="";
			String finalValue="";
			TotalArea=TotalArea-constructedArea;
			double IrrigatedRate=0.0;
			double UnIrrigatedRate=0.0;
			double OneCropRate=0.0;
			double DoubleCropRate=0.0;
			
			String returnString[]=new String[2];
			
			ArrayList guideLineValuesList=propDAO.getAllGuideLineValuesOfUndiverted(propDTO);
			
			if(guideLineValuesList!=null && guideLineValuesList.size()>0)
			{
				for (int i = 0; i < guideLineValuesList.size(); i++)
				{
					ArrayList subList= (ArrayList) guideLineValuesList.get(i);
					
					guidelineId=String.valueOf(subList.get(0));
					returnString[0]=guidelineId;
					
					if(i==0)
					{
						
						IrrigatedRate=Double.parseDouble((String) subList.get(1));   //-----null handling of rates need to be done
						propDTO.setAgriIrrigatedRate(IrrigatedRate);
					}
					
					if(i==1)
					{
						UnIrrigatedRate=Double.parseDouble((String) subList.get(1));
					}
					
					if(i==2)
					{
						OneCropRate=Double.parseDouble((String) subList.get(1));
					}
					
					if(i==3)
					{
						DoubleCropRate=Double.parseDouble((String) subList.get(1));
					}
					
				}
				if(undivpropDTO.getAgriUndivSingleCropArea()==null || undivpropDTO.getAgriUndivSingleCropArea().equalsIgnoreCase(""))
				{
					undivpropDTO.setAgriUndivSingleCropArea("0");
				}
				
				if(undivpropDTO.getAgriUndivDoubleCropArea()==null || undivpropDTO.getAgriUndivDoubleCropArea().equalsIgnoreCase(""))
				{
					undivpropDTO.setAgriUndivDoubleCropArea("0");
				}
				
				if(undivpropDTO.getAgriUndivIrrigatedArea()==null || undivpropDTO.getAgriUndivIrrigatedArea().equalsIgnoreCase(""))
				{
					undivpropDTO.setAgriUndivIrrigatedArea("0");
				}
				
			//------geting the subcaluses id's checked in a String array
			if(propDTO.getAgriSelectedSubclauseId()!=""){
				subIds=propDTO.getAgriSelectedSubclauseId();
				
			}
			String sId[]=subIds.split("~");
			String[] subClauseId=new String[sId.length];

		//------end of addition----
			
			double IrrigatedArea=Double.parseDouble(undivpropDTO.getAgriUndivIrrigatedArea());
			double OneCropArea=Double.parseDouble(undivpropDTO.getAgriUndivSingleCropArea());
			double DoubleCropArea=Double.parseDouble(undivpropDTO.getAgriUndivDoubleCropArea());
			
			if("unopenshare".equalsIgnoreCase(propDTO.getAgriDiscloseShare()))
			{
				double noBuyers=Double.parseDouble(propDTO.getNoOfBuyers());
				IrrigatedArea=IrrigatedArea/noBuyers;
				OneCropArea=OneCropArea/noBuyers;
				DoubleCropArea=DoubleCropArea/noBuyers;
				TotalArea=TotalArea/noBuyers;
				constructedArea=constructedArea/noBuyers;
				
			}
			double [][] arr= new double[3][2];  // 2D Double array. [3] for areas & [2] for corresponding rates
			
			//below code for sorting areas in ascending order of their rates
			if(IrrigatedRate<=OneCropRate && IrrigatedRate<=DoubleCropRate){
				
				arr[0][0]=IrrigatedArea; arr[0][1]=IrrigatedRate;
								
				if(OneCropRate<=DoubleCropRate){
					arr[1][0]=OneCropArea;     arr[1][1]=OneCropRate;
					arr[2][0]=DoubleCropArea;  arr[2][1]=DoubleCropRate;
				}else{
					arr[1][0]=DoubleCropArea;  arr[1][1]=DoubleCropRate;
					arr[2][0]=OneCropArea;     arr[2][1]=OneCropRate;
				}
			}else if(OneCropRate<=IrrigatedRate && OneCropRate<=DoubleCropRate){
				
				arr[0][0]=OneCropArea;         arr[0][1]=OneCropRate;
				
				if(IrrigatedRate<=DoubleCropRate){
					arr[1][0]=IrrigatedArea;   arr[1][1]=IrrigatedRate;
					arr[2][0]=DoubleCropArea;  arr[2][1]=DoubleCropRate;
				}else{
					arr[1][0]=DoubleCropArea;  arr[1][1]=DoubleCropRate;
					arr[2][0]=IrrigatedArea;   arr[2][1]=IrrigatedRate;
				}
			}else if(DoubleCropRate<=IrrigatedRate && DoubleCropRate<=OneCropRate){
				arr[0][0]=DoubleCropArea;       arr[0][1]=DoubleCropRate;
				
				if(IrrigatedRate<=OneCropRate){
					arr[1][0]=IrrigatedArea;   arr[1][1]=IrrigatedRate;
					arr[2][0]=OneCropArea;     arr[2][1]=OneCropRate;
				}else{
					arr[1][0]=OneCropArea;     arr[1][1]=OneCropRate;
					arr[2][0]=IrrigatedArea;   arr[2][1]=IrrigatedRate;
				}
			}
			
			if(constructedArea>0)
			{
				if(constructedArea<=arr[0][0])
				{
					arr[0][0]=arr[0][0]-constructedArea;
				}
				else if(constructedArea<=(arr[0][0]+arr[1][0]))
				{
					arr[1][0]=(arr[0][0]+arr[1][0])-constructedArea;
					arr[0][0]=0.0;
				}
				else
				{
					arr[2][0]=(arr[0][0]+arr[1][0]+arr[2][0])-constructedArea;
					arr[0][0]=0.0;
					arr[1][0]=0.0;
				}
			}
			
				
					if(propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("4"))
					{
						
					if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1"))
					{
						x=1000;
						if(constructedArea<=(x/10000))
						{
							x=(x-(constructedArea*10000));
						}
						else
						{
							x=0;
						}
						double ResiRate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "26"));
						
						if(TotalArea<(x/10000))
						{
							value = value + TotalArea*10000*ResiRate;
							returnString[1]=String.valueOf(value);
						}
						
						else
						{
							if((x/10000)<=arr[0][0])
							{
								arr[0][0]=arr[0][0]-x/10000;
							}
							else if((x/10000)<=(arr[0][0]+arr[1][0]))
							{
								arr[1][0]=(arr[0][0]+arr[1][0])-x/10000;
								arr[0][0]=0.0;
							}
							else
							{
								arr[2][0]=(arr[0][0]+arr[1][0]+arr[2][0])-x/10000;
								arr[0][0]=0.0;
								arr[1][0]=0.0;
							}
							value= value+(x*ResiRate)+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];
							returnString[1]=String.valueOf(value);
						}
					}
					
					else if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")|| propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3"))
					{
						double ResiRate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "26"));

						x=500;
						if(constructedArea<=(x/10000))
						{
							x=(x-(constructedArea*10000));
						}
						else
						{
							x=0;
						}
						if(TotalArea<(x/10000))
						{
							value = value + TotalArea*10000*ResiRate;
							returnString[1]=String.valueOf(value);
						
						}
						
						else
						{
							if((x/10000)<=arr[0][0])
							{
								arr[0][0]=arr[0][0]-x/10000;
							}
							else if((x/10000)<=(arr[0][0]+arr[1][0]))
							{
								arr[1][0]=(arr[0][0]+arr[1][0])-x/10000;
								arr[0][0]=0.0;
							}
							else
							{
								arr[2][0]=(arr[0][0]+arr[1][0]+arr[2][0])-x/10000;
								arr[0][0]=0.0;
								arr[1][0]=0.0;
							}
							value= value+(x*ResiRate)+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];
							returnString[1]=String.valueOf(value);
						}
					}
					
					else if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("4"))
					{
						double ResiRate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "26"));

						x=300;
						if(constructedArea<=(x/10000))
						{
							x=(x-(constructedArea*10000));
						}
						else
						{
							x=0;
						}
						if(TotalArea<(x/10000))
						{
							value = value + TotalArea*10000*ResiRate;
						
						}
						
						else
						{
							if((x/10000)<=arr[0][0])
							{
								arr[0][0]=arr[0][0]-x/10000;
							}
							else if((x/10000)<=(arr[0][0]+arr[1][0]))
							{
								arr[1][0]=(arr[0][0]+arr[1][0])-x/10000;
								arr[0][0]=0.0;
							}
							else
							{
								arr[2][0]=(arr[0][0]+arr[1][0]+arr[2][0])-x/10000;
								arr[0][0]=0.0;
								arr[1][0]=0.0;
							}
							value= value+(x*ResiRate)+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];
							returnString[1]=String.valueOf(value);
						}
					}
					
					//value=Double.parseDouble(undivpropDTO.getAgriUndivSingleCropArea())*UnIrrigatedRate + 1.25*Double.parseDouble(undivpropDTO.getAgriUndivDoubleCropArea())*UnIrrigatedRate + Double.parseDouble(undivpropDTO.getAgriUndivDoubleCropArea())*IrrigatedRate;
					}
					else
					{
						
						
						value= value+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];					
					returnString[1]=String.valueOf(value);
					}
					
					
					
					
				
				
			}
			if("unopenshare".equalsIgnoreCase(propDTO.getAgriDiscloseShare()))
			{
				double noBuyers=Double.parseDouble(propDTO.getNoOfBuyers());
				returnString[1]=String.valueOf(Double.parseDouble(returnString[1])*noBuyers);
			}	
			return returnString;
			
		}
		
		//----for diverted land calculation--
		public String[] calcDiverted(PropertyValuationDTO divpropDTO, PropertyValuationDTO propDTO){
           
			String[] returnString=new String[2];
            
			double totalArea=Double.parseDouble(divpropDTO.getAgriTotalDivertedArea());
			if(propDTO.getConstructedAreaBuilding()==null || propDTO.getConstructedAreaBuilding().equalsIgnoreCase(""))
			{
				propDTO.setConstructedAreaBuilding("0.0");
			}
			double constructedArea=Double.parseDouble(propDTO.getConstructedAreaBuilding());// in hectares
			constructedArea=constructedArea/10000;
            String guidelineId="";
            double IrrigatedRate=0.0;
            double UnIrrigatedRate=0.0;
			double OneCropRate=0.0;
			double Value=0.0;
			double DoubleCropRate=0.0;
            double minusConstructedArea=constructedArea;
            totalArea=totalArea-constructedArea;
            double arr[][]=new double[6][2];
            
            //-----getting rates of irrigated land----
            
            ArrayList guideLineValuesList=propDAO.getAllGuideLineValuesOfUndiverted(propDTO);
            if(guideLineValuesList!=null && guideLineValuesList.size()>0)
			{
				for (int i = 0; i < guideLineValuesList.size(); i++)
				{
					ArrayList subList= (ArrayList) guideLineValuesList.get(i);
					
					guidelineId=String.valueOf(subList.get(0));
					returnString[0]=guidelineId;
					
					if(i==0)
					{
						
						IrrigatedRate=Double.parseDouble((String) subList.get(1));   //-----null handling of rates need to be done
						propDTO.setAgriIrrigatedRate(IrrigatedRate);
					}
					
					if(i==1)
					{
						UnIrrigatedRate=Double.parseDouble((String) subList.get(1));
					}
					
					if(i==2)
					{
						OneCropRate=Double.parseDouble((String) subList.get(1));
					}
					
					if(i==3)
					{
						DoubleCropRate=Double.parseDouble((String) subList.get(1));
					}
					
				}
			}
            
            //-----null handling of diverted agriland areas---
            
            if(divpropDTO.getAgriTotalDivertedResidentialArea()==null || divpropDTO.getAgriTotalDivertedResidentialArea().equalsIgnoreCase(""))
			{
            	divpropDTO.setAgriTotalDivertedResidentialArea("0");
			}
			
			if(divpropDTO.getAgriTotalDivertedCommercialArea()==null || divpropDTO.getAgriTotalDivertedCommercialArea().equalsIgnoreCase(""))
			{
				divpropDTO.setAgriTotalDivertedCommercialArea("0");
			}
			
			if(divpropDTO.getAgriTotalDivertedIndustrialArea()==null || divpropDTO.getAgriTotalDivertedIndustrialArea().equalsIgnoreCase(""))
			{
				divpropDTO.setAgriTotalDivertedIndustrialArea("0");
			}
			if(divpropDTO.getAgriTotalDivertedEducationalArea()==null || divpropDTO.getAgriTotalDivertedEducationalArea().equalsIgnoreCase(""))
			{
				divpropDTO.setAgriTotalDivertedEducationalArea("0");
			}
			if(divpropDTO.getAgriTotalDivertedHealthArea()==null || divpropDTO.getAgriTotalDivertedHealthArea().equalsIgnoreCase(""))
			{
				divpropDTO.setAgriTotalDivertedHealthArea("0");
			}
			if(divpropDTO.getAgriTotalDivertedOthersArea()==null || divpropDTO.getAgriTotalDivertedOthersArea().equalsIgnoreCase(""))
			{
				divpropDTO.setAgriTotalDivertedOthersArea("0");
			}
			
			 //-----end of null handling---
			double resiArea=Double.parseDouble(divpropDTO.getAgriTotalDivertedResidentialArea());
			double commArea=Double.parseDouble(divpropDTO.getAgriTotalDivertedCommercialArea());
			double indArea=Double.parseDouble(divpropDTO.getAgriTotalDivertedIndustrialArea()); 
			double schoolArea=Double.parseDouble(divpropDTO.getAgriTotalDivertedEducationalArea());
			double healthArea=Double.parseDouble(divpropDTO.getAgriTotalDivertedHealthArea());
			double otherArea=Double.parseDouble(divpropDTO.getAgriTotalDivertedOthersArea());
			if("unopenshare".equalsIgnoreCase(propDTO.getAgriDiscloseShare()))
			{
				double noBuyers=Double.parseDouble(propDTO.getNoOfBuyers());
				resiArea=resiArea/noBuyers;
				commArea=commArea/noBuyers;
				indArea=indArea/noBuyers;
				schoolArea=schoolArea/noBuyers;
				healthArea=healthArea/noBuyers;
				otherArea=otherArea/noBuyers;
				totalArea=totalArea/noBuyers;
				constructedArea=constructedArea/noBuyers;
				minusConstructedArea=minusConstructedArea/noBuyers;
		
			
			}
			arr[0][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "26"));                                    //Resi rate 
            arr[0][1]=resiArea;                                  //area      
            
            arr[1][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "27"));                                     //commercial  rate 
            arr[1][1]=commArea;                                 //area      
                                          
            arr[2][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "28"));                                // industrail rate 
            arr[2][1]=indArea;                                 //area      
           
            if(propDTO.getSubAreaId().equalsIgnoreCase("6"))
           { 
            arr[3][0]=arr[0][0];                                     // edu rate 
            arr[3][1]=schoolArea;                                  //area      
            
            arr[4][0]=arr[0][0];                                    //health rate 
            arr[4][1]=healthArea;                                  //area      
           }
           else
           {
        	   if("YESEDUC".equalsIgnoreCase(propDTO.getIsAgriTncpEducational()))
        	   {
        	   arr[3][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "30"));                                     // resi rate 
               arr[3][1]=schoolArea;                                  //area      
        	   }
        	   else
        	   {
        		   arr[3][0]=arr[0][0];                                     // edu rate 
                   arr[3][1]=schoolArea; 
        	   }
        	   if("YESHEALTH".equalsIgnoreCase(propDTO.getIsAgriTncpHealth()))
        	   { 
               arr[4][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "29"));                                     //resi rate 
               arr[4][1]=healthArea;
        	   }
        	   else
        	   {
        		   arr[4][0]=arr[0][0];                                    //health rate 
                   arr[4][1]=healthArea;
        	   }
           }
            arr[5][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "31"));                                  //rate 
            arr[5][1]=otherArea;                                 //area      
            
            java.util.Arrays.sort(arr, new java.util.Comparator<double[]>() {
                public int compare(double[] a, double[] b) {
                    return Double.compare(a[0], b[0]);
                }
            });
            
            if(constructedArea>0)
            {
            for(int i=0;i<arr.length;i++)
            {
                  if(minusConstructedArea<=arr[i][1] && minusConstructedArea!=0.0 )
                  {
                        arr[i][1]=arr[i][1]-minusConstructedArea;
                        minusConstructedArea=0.0;
                  }
                  
                  else if(minusConstructedArea==0.0)
                  {
                	  
                  }
                	
                  else
                  {
                        minusConstructedArea=minusConstructedArea-arr[i][1];
                        arr[i][1]=0.0;
                  }
            }
            }
            
            
            if(propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("4"))
            {
                  double x=0;
                  if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1"))
                  {
                        x=1000;
                  }
                  else if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3"))
                  {
                        x=500;
                  }
                  else
                  {
                        x=300;
                  }
                  if(constructedArea<=(x/10000))
					{
						x=(x-(constructedArea*10000));
					}
					else
					{
						x=0;
					}
                  if(totalArea<=(x/10000))
                  {
                	  	for(int i=0;i<arr.length;i++)
                              {
                                    Value = Value+arr[i][0]*arr[i][1]*10000;
                              }
                        
                  }
                  else if(totalArea>(x/10000))
                  {
                       /* arr[0][0]=Double.parseDouble(propDAO.getL1Rate(propDTO, "1", "1"));
                        arr[1][0]=Double.parseDouble(propDAO.getL1Rate(propDTO, "2", "1"));
                        arr[2][0]=Double.parseDouble(propDAO.getL1Rate(propDTO, "3", "1"));
                        if("5".equalsIgnoreCase(propDTO.getSubAreaId()))
                        {	
                        arr[3][0]=Double.parseDouble(propDAO.getL1Rate(propDTO, "4", "1"));
                        arr[4][0]=Double.parseDouble(propDAO.getL1Rate(propDTO, "5", "1"));
                        }
                        else
                        {
                        	arr[3][0]=arr[0][0];
                        	arr[4][0]=arr[0][0];
                        }
                        arr[5][0]=Double.parseDouble(propDAO.getL1Rate(propDTO, "6", "1"));
                        java.util.Arrays.sort(arr, new java.util.Comparator<double[]>() {
                            public int compare(double[] a, double[] b) {
                                return Double.compare(a[0], b[0]);
                            }
                        });*/
                	  	double minusArea=0.0;
                	  	minusArea=(x/10000);
                        for(int i=arr.length-1;i>=0;i--)
                        {
                              if(minusArea>=arr[i][1])
                              {
                                    minusArea=minusArea-arr[i][1];
                                    continue;
                              }
                              else if(minusArea<=arr[i][1]&&minusArea>=0)
                              {
                                    arr[i][1]=minusArea;
                                    minusArea=0.0;
                                    continue;
                              }
                              else if(minusArea<=0)
                              {
                                    arr[i][1]=0.0;
                              }
                        }
                        for(int i=0;i<arr.length;i++) 
                        {
                              Value = Value+arr[i][0]*arr[i][1]*10000;
                        }
                        
                        Value = Value+1.5*(totalArea-(x/10000))*IrrigatedRate;
                        
                  }
            }
            else
            {
                  if(totalArea>(300.00/10000.0))
                  {
                        
                        Value=totalArea*1.5*IrrigatedRate;
                  }
                  else
                  {
                	  arr[0][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "26"));                                  //Resi rate 
                      arr[0][1]=resiArea;                                 //area      
                      
                      arr[1][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "27"));                                   //commercial  rate 
                      arr[1][1]=commArea;                                  //area      
                                                    
                      arr[2][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "28"));                                 // industrail rate 
                      arr[2][1]=indArea;                                  //area      
                     
                      if(propDTO.getSubAreaId().equalsIgnoreCase("6"))
                     { 
                      arr[3][0]=arr[0][0];                                   // edu rate 
                      arr[3][1]=schoolArea;                                 //area      
                      
                      arr[4][0]=arr[0][0];                                   //health rate 
                      arr[4][1]=healthArea;                             //area      
                     }
                     else
                     {
                    	 if("YESEDUC".equalsIgnoreCase(propDTO.getIsAgriTncpEducational()))
                  	   {
                  	   arr[3][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "30"));                                     // resi rate 
                         arr[3][1]=schoolArea;                                  //area      
                  	   }
                  	   else
                  	   {
                  		   arr[3][0]=arr[0][0];                                     // edu rate 
                             arr[3][1]=schoolArea; 
                  	   }
                  	   if("YESHEALTH".equalsIgnoreCase(propDTO.getIsAgriTncpHealth()))
                  	   { 
                         arr[4][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "29"));                                     //resi rate 
                         arr[4][1]=healthArea;
                  	   }
                  	   else
                  	   {
                  		   arr[4][0]=arr[0][0];                                    //health rate 
                             arr[4][1]=healthArea;
                  	   } 
                     }
                      arr[5][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "31"));                                //rate 
                      arr[5][1]=otherArea;                                 //area      
                      
                      java.util.Arrays.sort(arr, new java.util.Comparator<double[]>() {
                          public int compare(double[] a, double[] b) {
                              return Double.compare(a[0], b[0]);
                          }
                      });
                      minusConstructedArea=constructedArea;
                      if(constructedArea>0)
                      {
                      for(int i=0;i<arr.length;i++)
                      {
                    	  if(minusConstructedArea<=arr[i][1] && minusConstructedArea!=0.0)
                          {
                                arr[i][1]=arr[i][1]-minusConstructedArea;
                                minusConstructedArea=0.0;
                          }
                          
                          else if(minusConstructedArea==0.0)
                          {
                        	  
                          }
                        	
                          else
                          {
                                minusConstructedArea=minusConstructedArea-arr[i][1];
                                arr[i][1]=0.0;
                          }
                      }
                      }
                              for(int i=0;i<arr.length;i++)
                              {
                                    Value = Value+arr[i][0]*arr[i][1]*10000;
                              }
                       
                  }
            }
            returnString[1]=String.valueOf(Value);
            if("unopenshare".equalsIgnoreCase(propDTO.getAgriDiscloseShare()))
			{
				double noBuyers=Double.parseDouble(propDTO.getNoOfBuyers());
				returnString[1]=String.valueOf(Double.parseDouble(returnString[1])*noBuyers);
			}
            return returnString;
            }

		
		//---for the calculation of both agriland
		
		public  String[] calculateBoth(PropertyValuationDTO bothpropDTO, PropertyValuationDTO propDTO)
	      
		{
	            String returnString[]= new String[3];
	            
	            
	          //---null handling of undiverted areas
	            
	            if(bothpropDTO.getAgriUndivSingleCropArea()==null || bothpropDTO.getAgriUndivSingleCropArea().equalsIgnoreCase(""))
				{
	            	bothpropDTO.setAgriUndivSingleCropArea("0");
				}
				
				if(bothpropDTO.getAgriUndivDoubleCropArea()==null || bothpropDTO.getAgriUndivDoubleCropArea().equalsIgnoreCase(""))
				{
					bothpropDTO.setAgriUndivDoubleCropArea("0");
				}
				
				if(bothpropDTO.getAgriUndivIrrigatedArea()==null || bothpropDTO.getAgriUndivIrrigatedArea().equalsIgnoreCase(""))
				{
					bothpropDTO.setAgriUndivIrrigatedArea("0");
				}
				//---end of null handling 
				
				
				//---null handling of diverted areas
				
				 if(bothpropDTO.getAgriTotalDivertedResidentialArea()==null || bothpropDTO.getAgriTotalDivertedResidentialArea().equalsIgnoreCase(""))
					{
					 bothpropDTO.setAgriTotalDivertedResidentialArea("0");
					}
					
					if(bothpropDTO.getAgriTotalDivertedCommercialArea()==null || bothpropDTO.getAgriTotalDivertedCommercialArea().equalsIgnoreCase(""))
					{
						bothpropDTO.setAgriTotalDivertedCommercialArea("0");
					}
					
					if(bothpropDTO.getAgriTotalDivertedIndustrialArea()==null || bothpropDTO.getAgriTotalDivertedIndustrialArea().equalsIgnoreCase(""))
					{
						bothpropDTO.setAgriTotalDivertedIndustrialArea("0");
					}
					if(bothpropDTO.getAgriTotalDivertedEducationalArea()==null || bothpropDTO.getAgriTotalDivertedEducationalArea().equalsIgnoreCase(""))
					{
						bothpropDTO.setAgriTotalDivertedEducationalArea("0");
					}
					if(bothpropDTO.getAgriTotalDivertedHealthArea()==null || bothpropDTO.getAgriTotalDivertedHealthArea().equalsIgnoreCase(""))
					{
						bothpropDTO.setAgriTotalDivertedHealthArea("0");
					}
					if(bothpropDTO.getAgriTotalDivertedOthersArea()==null || bothpropDTO.getAgriTotalDivertedOthersArea().equalsIgnoreCase(""))
					{
						bothpropDTO.setAgriTotalDivertedOthersArea("0");
					}
				//---end of null handling 
					
					
	            double divValue=0;
	            double unDivValue=0;
	            double IrrigatedRate=0.0;
				double UnIrrigatedRate=0.0;
				double OneCropRate=0.0;
				double DoubleCropRate=0.0;
				String guidelineId="";
	            double totalUnDivArea=Double.parseDouble(bothpropDTO.getAgriTotalUnDivertedArea());
	            double oneCropArea=Double.parseDouble(bothpropDTO.getAgriUndivSingleCropArea());
	            double twoCropArea=Double.parseDouble(bothpropDTO.getAgriUndivDoubleCropArea());
	            double irrigatedArea=Double.parseDouble(bothpropDTO.getAgriUndivIrrigatedArea());
	            double totalDivArea=Double.parseDouble(bothpropDTO.getAgriTotalDivertedArea());
	            double resiArea=Double.parseDouble(bothpropDTO.getAgriTotalDivertedResidentialArea());
	            double commArea=Double.parseDouble(bothpropDTO.getAgriTotalDivertedCommercialArea());
	            double indArea=Double.parseDouble(bothpropDTO.getAgriTotalDivertedIndustrialArea());
	            double healthArea=Double.parseDouble(bothpropDTO.getAgriTotalDivertedHealthArea());
	            double schoolArea=Double.parseDouble(bothpropDTO.getAgriTotalDivertedEducationalArea());
	            double otherArea=Double.parseDouble(bothpropDTO.getAgriTotalDivertedOthersArea());
	            double constructedArea=Double.parseDouble(bothpropDTO.getAgriBuildingConstructArea())/10000;     //in hectares
	            
	            //getting the rates for undiverted land
	            
	            
	            ArrayList guideLineValuesList=propDAO.getAllGuideLineValuesOfUndiverted(propDTO);
	            if(guideLineValuesList!=null && guideLineValuesList.size()>0)
				{
					for (int i = 0; i < guideLineValuesList.size(); i++)
					{
						ArrayList subList= (ArrayList) guideLineValuesList.get(i);
						
						guidelineId=String.valueOf(subList.get(0));
						returnString[0]=guidelineId;
						
						if(i==0)
						{
							
							IrrigatedRate=Double.parseDouble((String) subList.get(1));   //-----null handling of rates need to be done
							propDTO.setAgriIrrigatedRate(IrrigatedRate);
						}
						
						if(i==1)
						{
							UnIrrigatedRate=Double.parseDouble((String) subList.get(1));
						}
						
						if(i==2)
						{
							OneCropRate=Double.parseDouble((String) subList.get(1));
						}
						
						if(i==3)
						{
							DoubleCropRate=Double.parseDouble((String) subList.get(1));
						}
						
					}
				}
	            
	            //----getting rates for diverted land
	            
	            double healthrate=0.0;
	            double schoolrate=0.0;
	            double resirate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "26"));
	            double commrate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "27"));
	            double indrate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "28"));
	            
	            if(propDTO.getSubAreaId().equalsIgnoreCase("6"))
	            { 
	                 healthrate=resirate;
	                 schoolrate=resirate;//area      
	            }
	            else
	            {
	         	   if("YESEDUC".equalsIgnoreCase(propDTO.getIsAgriTncpEducational()))
	         	   {
	         	       schoolrate= Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "30"));                           //area      
	         	   }
	         	   else
	         	   {
	         		  schoolrate=resirate;
	         	   }
	         	   if("YESHEALTH".equalsIgnoreCase(propDTO.getIsAgriTncpHealth()))
	         	   { 
	                healthrate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "29"));                                     //resi rate 
	                
	         	   }
	         	   else
	         	   {
	         		  healthrate=resirate;
	         	   }
	            }
	            
	            double otherrate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "31"));
	            
	            String shareFlag=propDTO.getAgriDiscloseShare();
	            if("unopenshare".equalsIgnoreCase(shareFlag))
	            {
	                  double noBuyer =Double.parseDouble(propDTO.getNoOfBuyers());
	                  totalDivArea=totalDivArea/noBuyer;
	                  oneCropArea=oneCropArea/noBuyer;
	                  twoCropArea=twoCropArea/noBuyer;
	                  irrigatedArea=irrigatedArea/noBuyer;
	                  totalUnDivArea=totalUnDivArea/noBuyer;
	                  resiArea=resiArea/noBuyer;
	                  commArea=commArea/noBuyer;
	                  indArea=indArea/noBuyer;
	                  schoolArea=schoolArea/noBuyer;
	                  healthArea=healthArea/noBuyer;
	                  otherArea=otherArea/noBuyer;
	                  constructedArea=constructedArea/noBuyer;
	            }
	            double[][] unDivArray= new double[3][2];
	            double[][] divArray= new double[6][2];
	            
	            unDivArray[0][0]=IrrigatedRate;
	            unDivArray[0][1]=irrigatedArea;
	            unDivArray[1][0]=OneCropRate;
	            unDivArray[1][1]=oneCropArea;
	            unDivArray[2][0]=DoubleCropRate;
	            unDivArray[2][1]=twoCropArea;
	            	
	            
	            divArray[0][0]=resirate;
		        divArray[0][1]=resiArea;
			    divArray[1][0]=commrate;
			    divArray[1][1]=commArea;
			    divArray[2][0]=indrate;
				divArray[2][1]=indArea;
				divArray[3][0]=schoolrate;
				divArray[3][1]=schoolArea;
				divArray[4][0]=healthrate;
				divArray[4][1]=healthArea;
				divArray[5][0]=otherrate;
				divArray[5][1]=otherArea;
				
	            
	            
	            java.util.Arrays.sort(unDivArray, new java.util.Comparator<double[]>() {
	            public int compare(double[] a, double[] b) {
	                return Double.compare(a[0], b[0]);
	            }
	        });
	            java.util.Arrays.sort(divArray, new java.util.Comparator<double[]>() {
	            public int compare(double[] a, double[] b) {
	                return Double.compare(a[0], b[0]);
	            }
	        });
	            if(constructedArea>0&&constructedArea<=totalDivArea)
	            {
	                  totalDivArea=totalDivArea-constructedArea;
	                  double minusConstructedArea=constructedArea;
	                    for(int i=0;i<divArray.length;i++)
	                  {
	                      
	                          if(minusConstructedArea<=divArray[i][1] && minusConstructedArea!=0.0 )
	                        {
	                          divArray[i][1]=divArray[i][1]-minusConstructedArea;
	                              minusConstructedArea=0.0;
	                        }
	                        
	                        else if(minusConstructedArea==0.0)
	                        {
	                          
	                        }
	                        
	                        else
	                        {
	                              minusConstructedArea=minusConstructedArea-divArray[i][1];
	                              divArray[i][1]=0.0;
	                        }
	                  }

	                  
	            }
	            else if(constructedArea>0&&constructedArea>totalDivArea)
	            {
	                  double minusConstructedArea=constructedArea-totalDivArea;
	                  totalDivArea=0.0;
	                  totalUnDivArea=totalUnDivArea-minusConstructedArea;
	                  for(int i=0;i<unDivArray.length;i++)
	            {
	                
	                    if(minusConstructedArea<=unDivArray[i][1] && minusConstructedArea!=0.0 )
	                  {
	                    divArray[i][1]=unDivArray[i][1]-minusConstructedArea;
	                        minusConstructedArea=0.0;
	                  }
	                  
	                  else if(minusConstructedArea==0.0)
	                  {
	                    
	                  }
	                  
	                  else
	                  {
	                        minusConstructedArea=minusConstructedArea-unDivArray[i][1];
	                        unDivArray[i][1]=0.0;
	                  }
	            }

	            }
	            if(propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("4"))
	        {
	                  double x=0;
	            if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1"))
	            {
	                  x=1000;
	            }
	            else if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3"))
	            {
	                  x=500;
	            }
	            else
	            {
	                  x=300;
	            }
	            if((totalDivArea+totalUnDivArea)<=((x/10000)-constructedArea))
	            {
	            	  for(int i=0;i<unDivArray.length;i++)
		                {
		                      unDivValue = unDivValue+unDivArray[i][0]*unDivArray[i][1];
		                }
	            	   for(int i=0;i<divArray.length;i++) 
	                    {
	                          divValue = divValue+divArray[i][0]*divArray[i][1]*10000;
	                    }
	            	  
	            }
	            else
	            {
	            if(constructedArea>=(x/10000))
	            {
	                  for(int i=0;i<unDivArray.length;i++)
	                {
	                      unDivValue = unDivValue+unDivArray[i][0]*unDivArray[i][1];
	                }
	                  
	                  divValue=1.5*totalDivArea*IrrigatedRate;
	                  
	            }
	            else
	            {
	                  double newX=((x/10000)-constructedArea);
	                  if(newX<=totalUnDivArea)
	                  {
	                        double minusx=newX;
	                        for(int i=0;i<unDivArray.length;i++)
	                    {
	                        
	                          if(minusx<=unDivArray[i][1] && minusx!=0.0 )
	                          {
	                        	  unDivArray[i][1]=unDivArray[i][1]-minusx;
	                                minusx=0.0;
	                          }
	                          
	                          else if(minusx==0.0)
	                          {
	                                
	                          }
	                              
	                          else
	                          {
	                                minusx=minusx-unDivArray[i][1];
	                                unDivArray[i][1]=0.0;
	                          }
	                    }
	                        for(int i=0;i<unDivArray.length;i++)
	                    {
	                          unDivValue = unDivValue+unDivArray[i][0]*unDivArray[i][1];
	                    }
	                        unDivValue=unDivValue+ newX*10000*resirate;
	                  divValue=1.5*totalDivArea*IrrigatedRate;
	                  }
	                  else if(newX>totalUnDivArea)
	                  {
	                        
	                        
	                        unDivValue=totalUnDivArea*resirate*10000;
	                        double xForDiv=newX-totalUnDivArea;
	                        double minusx=xForDiv;
	                        totalUnDivArea=0;
	                        totalDivArea=totalDivArea-xForDiv;
	                        for(int i=divArray.length-1;i>=0;i--)              
	                        {                        
	                              if(minusx>=divArray[i][1])
	                    {
	                              minusx=minusx-divArray[i][1];
	                          continue;
	                    }
	                    else if(minusx<=divArray[i][1]&&minusx>=0)
	                    {
	                        divArray[i][1]=minusx;
	                          minusx=0.0;
	                          continue;
	                    }
	                    else if(minusx<=0)
	                    {
	                        divArray[i][1]=0.0;
	                    }
	                  }
	                        for(int i=0;i<divArray.length;i++) 
	                    {
	                          divValue = divValue+divArray[i][0]*divArray[i][1]*10000;
	                    }
	                   
	                        divValue = divValue+1.5*totalDivArea*IrrigatedRate;
	                    

	                  }
	            }
	            
	        }
	        }
	            else
	            {
	                  for(int i=0;i<unDivArray.length;i++)
	            {
	                  unDivValue = unDivValue+unDivArray[i][0]*unDivArray[i][1];
	            }
	                  if(totalDivArea+totalUnDivArea>.03)
	                  {
	                       
	                  divValue = divValue+1.5*totalDivArea*IrrigatedRate;
	                  }
	                  else
	                  {
	                        for(int i=0;i<divArray.length;i++)
	                  {
	                      divValue = divValue+divArray[i][0]*divArray[i][1]*10000;
	                  }     
	                  }
	            }
	            
	      returnString[0]=guidelineId;
	      returnString[1]=String.valueOf(divValue);
	      returnString[2]=String.valueOf(unDivValue);
	      if("unopenshare".equalsIgnoreCase(propDTO.getAgriDiscloseShare()))
			{
				double noBuyers=Double.parseDouble(propDTO.getNoOfBuyers());
				returnString[1]=String.valueOf(Double.parseDouble(returnString[1])*noBuyers);
				returnString[2]=String.valueOf(Double.parseDouble(returnString[2])*noBuyers);
			}
	      
	            return returnString;
	      }

		
		
		
		
		
		
		
		public String calcAgriWithSubclauses(String unDivValue, String divValue, double irrigatedRate,String[] ids, PropertyValuationDTO propDTO, String totalArea) {
		// TODO Auto-generated method stub
			String marketValue="";
			
			double finalValue=Double.parseDouble(unDivValue) + Double.parseDouble(divValue);
			
			double natHighway=0.0;
			double operandHighway=0.0;
			double distHighway=0.0;
			double operandDouble=0.0;
			double constructionCost=Double.parseDouble(propDTO.getConstructionCostFinal());
			ArrayList guideLineValues=propDAO.calcAgriWithSubclauses();
			double baseValue=Double.parseDouble(unDivValue) + Double.parseDouble(divValue);
			for(int j=1;j<ids.length;j++){
				for(int i=0;i<guideLineValues.size();i++){
					ArrayList subList=(ArrayList)guideLineValues.get(i);
					if(ids[j].equalsIgnoreCase(subList.get(0).toString())){
						String operator=subList.get(1).toString();
						String operand=subList.get(2).toString();
						if(ids[j].equalsIgnoreCase("11"))
						{
							baseValue=1.5*Double.parseDouble(totalArea)*irrigatedRate;
							finalValue=baseValue;
						}
						
						if(ids[j].equalsIgnoreCase("12")||ids[j].equalsIgnoreCase("13")||ids[j].equalsIgnoreCase("14"))
						{
							if(Double.parseDouble(operand) > operandHighway)
							{
								operandHighway=Double.parseDouble(operand);
							}
						}
						/*else
						{
							if(operator.equalsIgnoreCase("+%")){
								finalValue=baseValue+(baseValue*(Double.valueOf(operand)/100));
							}
							if(operator.equalsIgnoreCase("-%")){
								finalValue=baseValue-(baseValue*(Double.valueOf(operand)/100));
							}
							if(operator.equalsIgnoreCase("*")){
								finalValue=baseValue*Double.valueOf(operand);
							}
						}*/
						
					}
					
					}
				
			}
	finalValue=finalValue+(baseValue*operandHighway/100);
			
	if(constructionCost>0)
	{
		if(propDTO.getPlotValueFinal()!=null && !propDTO.getPlotValueFinal().equalsIgnoreCase(""))
		{
		double plotValue=Double.parseDouble(propDTO.getPlotValueFinal());
		
		finalValue=finalValue+(plotValue*operandHighway/100);
		}
	}
			marketValue=String.valueOf(finalValue);
			return marketValue;
			
			
		
	}

		//--------for calculation of trees-----
		
		public String[] calcTotalTreeValue(TreeDTO dto,
				PropertyValuationDTO propDTO) {
			double value=0;
			
			double SagunTreeRate=0.0;
			double SalTreeRate=0.0;
			double FruitTreeRate=0.0;
			double Les45TreeRate=0.0;
			
			if(dto.getSagunTree()==null || dto.getSagunTree().equalsIgnoreCase(""))
			{
				dto.setSagunTree("0");
			}
			
			if(dto.getSaalTree()==null ||  dto.getSaalTree().equalsIgnoreCase(""))
			{
				dto.setSaalTree("0");
			}
			
			if(dto.getFruitTree()==null ||  dto.getFruitTree().equalsIgnoreCase(""))
			{
				dto.setFruitTree("0");
			}
			
			if(dto.getLess45Tree()==null ||  dto.getLess45Tree().equalsIgnoreCase(""))
			{
				dto.setLess45Tree("0");
			}
			
			String returnString[]=new String[1];
			
			ArrayList TreeValuesList=propDAO.getAllGuideLineTreeRates();
			
			if(TreeValuesList!=null && TreeValuesList.size()>0)
			{
				for (int i = 0; i < TreeValuesList.size(); i++)
				{
					ArrayList subList= (ArrayList) TreeValuesList.get(i);
					if(i==0)
					SagunTreeRate=Double.parseDouble((String) subList.get(0));
					if(i==1)
					SalTreeRate=Double.parseDouble((String) subList.get(0));
					if(i==2)
					FruitTreeRate=Double.parseDouble((String) subList.get(0));
					if(i==3)
					Les45TreeRate=Double.parseDouble((String) subList.get(0));
					
					
				}
			}
			
			value = Double.parseDouble(dto.getSagunTree())*SagunTreeRate + Double.parseDouble(dto.getSaalTree())*SalTreeRate + Double.parseDouble(dto.getFruitTree())*FruitTreeRate + Double.parseDouble(dto.getLess45Tree())*Les45TreeRate;
				
			returnString[0]=String.valueOf(value);;
			return returnString;
		}
		
}

