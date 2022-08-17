package com.wipro.igrs.newPropvaluation.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.newPropvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.newPropvaluation.constant.PropertyValuationConstant;
import com.wipro.igrs.newPropvaluation.dao.PropertyValuationDAO;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.newPropvaluation.dto.TreeDTO;
import com.wipro.igrs.newPropvaluation.form.PropertyValuationForm;

public class CalculationAgriLand {
	private static Logger logger = org.apache.log4j.Logger.getLogger(PropertyValuationBO.class);
	private PropertyValuationDAO propDAO;
	
	public CalculationAgriLand() throws Exception
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
			double constructedArea=Double.parseDouble(propDTO.getConstructedAreaBuilding());
			constructedArea=constructedArea/10000; // in hectares
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
						DoubleCropRate=Double.parseDouble((String) subList.get(1));//double crop guideline rate will be same as single crop rate
					}
					//commented by saurav for double crop Guideline Value changes
					/*
					if(i==3)
					{
						DoubleCropRate=Double.parseDouble((String) subList.get(1));
					}*/
					
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
							//value = value + TotalArea*10000*ResiRate;   // commented by ankit for new slab changes 
							value = value +calValSlab4_1Undiverted(TotalArea*10000,ResiRate);
							
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
							//commneted by ankit for new slab changes given by dept.
							//value= value+(x*ResiRate)+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];
							
							value= value+(calValSlab4_1Undiverted(x,ResiRate))+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];
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
							//value = value + TotalArea*10000*ResiRate;
							 // commented by ankit for new slab changes 
							
							value = value +calValSlab4_2Undiverted(TotalArea*10000,ResiRate);
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
							//value= value+(x*ResiRate)+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];
							// commented by ankit for new slab changes 
							value= value+( calValSlab4_2Undiverted(x,ResiRate))+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];
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
							//value = value + TotalArea*10000*ResiRate;
							// commented by ankit for new slab changes 
							value = value + calValSlab4_4Undiverted(TotalArea*10000,ResiRate);
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
							
							
							//value= value+(x*ResiRate)+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];
							// commented by ankit for new slab changes 
							value= value+( calValSlab4_4Undiverted(x,ResiRate) )+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];
							returnString[1]=String.valueOf(value);
						}
					}
					
					//value=Double.parseDouble(undivpropDTO.getAgriUndivSingleCropArea())*UnIrrigatedRate + 1.25*Double.parseDouble(undivpropDTO.getAgriUndivDoubleCropArea())*UnIrrigatedRate + Double.parseDouble(undivpropDTO.getAgriUndivDoubleCropArea())*IrrigatedRate;
					}
					else
					{
						
					if(TotalArea<=.03)
					{
						double ResiRate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "26"));
						//value=TotalArea*ResiRate*10000;
						//changes by ankit for new slab work
						value = calValSlab4_4Undiverted(TotalArea*10000,ResiRate);
					}
					else
					{	
					value= value+arr[0][0]*arr[0][1]+arr[1][0]*arr[1][1]+arr[2][0]*arr[2][1];					
					}
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
            double arrCopy[][]=new double[6][2]; //added by ankit for diverted land work/changes
            
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
						DoubleCropRate=Double.parseDouble((String) subList.get(1));//double crop guideline value will be same single crop guideline value
					}
					//commented by saurav for double crop guideline value changes
					/*if(i==3)
					{
						DoubleCropRate=Double.parseDouble((String) subList.get(1));
					}*/
					
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
			double resiRate =Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "26"));  //Resi rate 
			arr[0][0]=resiRate;                                    
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
        	   arr[3][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "30"));                          // edu rate             
               arr[3][1]=schoolArea;                                  //area      
        	   }
        	   else
        	   {
        		   arr[3][0]=arr[0][0];                                    // resi rate 
                   arr[3][1]=schoolArea; 
        	   }
        	   if("YESHEALTH".equalsIgnoreCase(propDTO.getIsAgriTncpHealth()))
        	   { 
               arr[4][0]=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "29"));                               //health rate       
               arr[4][1]=healthArea;
        	   }
        	   else
        	   {
        		   arr[4][0]=arr[0][0];                              //resi rate       
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
            
            for(int i=0;i<arr.length;i++)
            {
            	for(int j=0;j<=1;j++){
            		logger.info("sorted array diverted flow : "+arr[i][j]);
            	}
            	
            }
            
            
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
            
            //added by ankit for diverted land work/changes
            for(int i=0; i<arr.length; i++)
            	  for(int j=0; j<arr[i].length; j++)
            		  arrCopy[i][j]=arr[i][j];
            
           // arrCopy = arr;  it will not fulfil our purpose as it will modify the arr then it will modify copy also 
            
            if(propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("4"))
            {
                  double x=0;
                  int tempId=0;  //added by ankit for new slab changes
                  if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1"))
                  {
                        x=1000;
                        tempId =1;
                  }
                  else if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3"))
                  {
                        x=500;
                        tempId =2;
                  }
                  else
                  {
                        x=300;
                        tempId=3;
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
                	  double remainngArea =0.0;
                	  double higherRate = 0;
                	  double otherRate=0;
                  	  int flagged=0;
                  	  double tempTotalArea=0.0;
                  
                	  	for(int i=arr.length-1;i>=0;i--)
                              {
                                   // Value = Value+arr[i][0]*arr[i][1]*10000;
                                    //added by ankit for new slab changes
                                 /*   if(tempId==1){                                    	
                                    	Value = Value +calValSlab4_1(arr[i][1]*10000,arr[i][0]);
                                    }
                                    else if(tempId==2){
                                    	Value = Value +calValSlab4_2(arr[i][1]*10000,arr[i][0]);
                                    }else{
                                    	Value = Value +calValSlab4_4(arr[i][1]*10000,arr[i][0]);
                                    }
                                    */
                	  		
	                	  		if(i==(arr.length-1)){
	                	  				higherRate =	arr[i][0] ; //Higher Rate
		        	  					commArea=       arr[i][1]*10000; // Area
		        	  					tempTotalArea=commArea;
	                	  		}else{
	                	  				tempTotalArea= tempTotalArea+arr[i][1]*10000;
	                	  				
	                	  				}
                              }
                	  			
                	  			//double resiRate = arr[0][0];
                	  			if(tempId==1){                                    	
                                	Value = Value +calDivertedSlabVal4_1( commArea, higherRate, tempTotalArea, resiRate);
                                }
                                else if(tempId==2){
                                	Value = Value +calDivertedSlabVal4_2( commArea, higherRate, tempTotalArea, resiRate);
                                }else{
                                	Value = Value +calDivertedSlabVal4_4( commArea, higherRate, tempTotalArea, resiRate);
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
                        
                        
                        java.util.Arrays.sort(arr, new java.util.Comparator<double[]>() {
                            public int compare(double[] a, double[] b) {
                                return Double.compare(a[0], b[0]);
                            }
                        });
                        
                    /*    for(int i=arr.length-1;i>=0;i--) 
                        {
                             // Value = Value+arr[i][0]*arr[i][1]*10000;
                              
                              //added by ankit for new slab changes
                              if(tempId==1){
                              	Value = Value +calValSlab4_1(arr[i][1]*10000,arr[i][0]);
                              }
                              else if(tempId==2){
                              	Value = Value +calValSlab4_2(arr[i][1]*10000,arr[i][0]);
                              }else{
                              	Value = Value +calValSlab4_4(arr[i][1]*10000,arr[i][0]);
                              }

                        }*/
                        
                        double remainngArea =0.0;
                        double higherRate = 0;
                        double otherRate=0;
                    	int flagged=0;
                    	double tempTotalArea=0.0;
                    
                  	  	for(int i=arr.length-1;i>=0;i--)
                                {
  		
		  	                	  		if(i==(arr.length-1)){
		  	                	  				higherRate =	arr[i][0] ; //Higher Rate
		  		        	  					commArea=       arr[i][1]*10000; // Area
		  		        	  					tempTotalArea=commArea;
		  	                	  		}else{
		  	                	  				tempTotalArea= tempTotalArea+arr[i][1]*10000;
		  	                	  				
		  	                	  			}
                                }
                  	  			
                  	  			//double resiRate = arr[0][0];
                  	  			
	                  	  			if(tempId==1){                                    	
	                                  	Value = Value +calDivertedSlabVal4_1( commArea, higherRate, tempTotalArea, resiRate);
	                                  }
	                                  else if(tempId==2){
	                                  	Value = Value +calDivertedSlabVal4_2( commArea, higherRate, tempTotalArea, resiRate);
	                                  }else{
	                                  	Value = Value +calDivertedSlabVal4_4( commArea, higherRate, tempTotalArea, resiRate);
	                                  }
                        
	                  	  			logger.info(" calcDiverted value after 1000sqm : "+(1.5*(totalArea-(x/10000))*IrrigatedRate));
                      
	                  	  			Value = Value+1.5*(totalArea-(x/10000))*IrrigatedRate;
	                  	  			
	                  	  		//added by ankit for diverted land changes 	
                        // totalarea>1000 sqm   not required 
                        // subclause 4.1   tempId==1    not required 
                        // 10,000 multiplier check  resiRate*10000 == IrrigatedRate
	                  	  			//Value>totalArea*resiRate  then Value = totalArea*resiRate
	                  	  		//logger.info(" IrrigatedRate "+IrrigatedRate + " -- " +"resiRate*10000 : "+resiRate*10000);
	                  	  		if(resiRate*10000 == IrrigatedRate){	
	                  	  			//logger.info("---------- Viksit Area ------------");
	                  	  			double tempArea=0.0;
	                  	  			double tempRate=0.0;
	                  	  			double tempPlotValue=0.0;
	                  	  		for(int i=arrCopy.length-1;i>=0;i--)
	                              {
	                                  	                	  		
	                  	  				tempRate=arrCopy[i][0];
	                  	  				tempArea=	arrCopy[i][1]*10000; // Area*rate
	                  	  				//logger.info(" tempRate "+tempRate + " -- " +"tempArea : "+tempArea);
			        	  					
	                  	  				tempPlotValue =tempPlotValue+tempRate*tempArea;
		                	  		}
	                  	  			//double plotValue =  (arrCopy[0][0]*arrCopy[0][1] + arrCopy[1][0]*arrCopy[1][1] + arrCopy[2][0]*arrCopy[2][1] + arrCopy[3][0]*arrCopy[3][1] +  arrCopy[4][0]*arrCopy[4][1] + arrCopy[5][0]*arrCopy[5][1])*10000;
	                  	  			logger.info("---------- Viksit Area ------------ tempPlotValue : "+tempPlotValue+" diverted Value as per 1.5 factor: "+Value);
	                  	  			Value = Value>tempPlotValue?tempPlotValue:Value;
	                  	  		}
	                  	  			
                        logger.info("calcDiverted Total Value : " + Value);
                  }
            }
            else
            {
                  if(totalArea>(300.00/10000.0))
                  {
                        
                        Value=totalArea*1.5*IrrigatedRate;
                        
                        //added by ankit for diverted land changes 
                        if(arr[0][0]*10000 == IrrigatedRate){	
                      	  	double plotValue=0.0;
              	  			plotValue =  arrCopy[0][0]*arrCopy[0][1] + arrCopy[1][0]*arrCopy[1][1] + arrCopy[2][0]*arrCopy[2][1] + arrCopy[3][0]*arrCopy[3][1] +  arrCopy[4][0]*arrCopy[4][1] + arrCopy[5][0]*arrCopy[5][1];
              	  			Value = Value>plotValue?plotValue:Value;
              	  		}
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
                              /*for(int i=0;i<arr.length;i++)
                              {
                                   // Value = Value+arr[i][0]*arr[i][1]*10000;
                                    //changes by ankit for new slab work
                                    Value = Value+calValSlab4_4(arr[i][1]*10000,arr[i][0]);

                              }    
                              */
                      //changes by ankit for new slab work
                              double remainngArea =0.0;
                              double higherRate = 0;
                              double otherRate=0;
                          	  int flagged=0;
                          	  double tempTotalArea=0.0;
                          
                        	  	for(int i=arr.length-1;i>=0;i--)
                                      {
      		  	                	  		if(i==(arr.length-1)){
      		  	                	  				higherRate =	arr[i][0] ; //Higher Rate
      		  		        	  					commArea=       arr[i][1]*10000; // Area
      		  		        	  					tempTotalArea=commArea;
      		  	                	  		}else{
      		  	                	  				tempTotalArea= tempTotalArea+arr[i][1]*10000;
      		  	                	  				
      		  	                	  			}
                                      }
                        	  			
                        	  	//double resiRate = arr[0][0];

      	                        Value = Value +calDivertedSlabVal4_4( commArea, higherRate, tempTotalArea, resiRate);
      	                                                             
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
					// TODO Auto-generated method stub
					
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
							DoubleCropRate=Double.parseDouble((String) subList.get(1));//double crop guideline value will be same single crop guideline value
						}
						//commented by saurav for double crop guideline value changes
						/*if(i==3)
						{
							DoubleCropRate=Double.parseDouble((String) subList.get(1));
						}*/
						
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
	            
	            
	            //added by ankit for diverted land work/changes
	            double  divArrayCopy[][] = new double[6][2];
	            for(int i=0; i<divArray.length; i++)
	            	  for(int j=0; j<divArray[i].length; j++)
	            		  divArrayCopy[i][j]=divArray[i][j];
	          
	            
	            if(propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("4"))
	        {
	                  double x=0;
	                  int tempId=0; // added by ankit for new slab changes
	            if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("1"))
	            {
	                  x=1000;
	                  tempId=1;
	            }
	            else if (propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("2")||propDTO.getAgriApplicableSubclauseId().equalsIgnoreCase("3"))
	            {
	                  x=500;
	                  tempId=2;
	            }
	            else
	            {
	                  x=300;
	                  tempId=4;
	            }
	            if((totalDivArea+totalUnDivArea)<=((x/10000)-constructedArea))
	            {
	            	/*  for(int i=0;i<unDivArray.length;i++)
		                {
		                     // unDivValue = unDivValue+unDivArray[i][0]*unDivArray[i][1];
		                      //changes by ankit for new slab changes
		                      if(tempId==1){
		                    	  unDivValue = unDivValue+calValSlab4_1(unDivArray[i][1],unDivArray[i][0]);
		                      }else if(tempId==2){
		                    	  unDivValue = unDivValue+calValSlab4_2(unDivArray[i][1],unDivArray[i][0]);
		                      }else{
		                    	  unDivValue = unDivValue+calValSlab4_4(unDivArray[i][1],unDivArray[i][0]);
		                      }
		                      
		                }
	            	  */
	            	                             	  
	            	   /*for(int i=0;i<divArray.length;i++) 
	                    {
	                          //divValue = divValue+divArray[i][0]*divArray[i][1]*10000;
	                        //changes by ankit for new slab changes
	                          
	                          if(tempId==1){
	                        	  divValue = divValue+calValSlab4_1(divArray[i][1]*10000,divArray[i][0]);
		                      }else if(tempId==2){
		                    	  divValue = divValue+calValSlab4_2(divArray[i][1]*10000,divArray[i][0]);
		                      }else{
		                    	  divValue = divValue+calValSlab4_4(divArray[i][1]*10000,divArray[i][0]);
		                      }
	                    }*/
	            	   
                       double higherRate = 0;
                   	   double tempTotalArea=0.0;
                   	  // double[][] divAndUnArray= new double[9][2];
                   	   
                 	  	for(int i=divArray.length-1;i>=0;i--)
                               {
		  	                	  		if(i==(divArray.length-1)){
		  	                	  				higherRate =	divArray[i][0] ; //Higher Rate
		  		        	  					commArea=       divArray[i][1]*10000; // Area
		  		        	  					tempTotalArea=commArea;
		  	                	  		}else{
		  	                	  				tempTotalArea= tempTotalArea+divArray[i][1]*10000;
		  	                	  				
		  	                	  			}
                               }
                 	  	for(int i=unDivArray.length-1;i>=0;i--)
                        {	
                 	  		tempTotalArea= tempTotalArea+unDivArray[i][1]*10000;
	  	                	  				
                        }
                 	  		
                 	  			double resiRate = resirate;
                 	  			
	                  	  			if(tempId==1){                                    	
	                  	  				  divValue = divValue +calDivertedSlabVal4_1( commArea, higherRate, tempTotalArea, resiRate);
	                                  }
	                                  else if(tempId==2){
	                                	  divValue = divValue +calDivertedSlabVal4_2( commArea, higherRate, tempTotalArea, resiRate);
	                                  }else{
	                                	  divValue = divValue +calDivertedSlabVal4_4( commArea, higherRate, tempTotalArea, resiRate);
	                                  }  
	            }
	            else
	            {
	            if(constructedArea>=(x/10000))
	            {
	                  for(int i=0;i<unDivArray.length;i++)
	                {
	                     unDivValue = unDivValue+unDivArray[i][0]*unDivArray[i][1];
	                	/*//changes by ankit for new slab changes
	                      if(tempId==1){
	                    	  unDivValue = unDivValue+calValSlab4_1(unDivArray[i][1],unDivArray[i][0]);
	                      }else if(tempId==2){
	                    	  unDivValue = unDivValue+calValSlab4_2(unDivArray[i][1],unDivArray[i][0]);
	                      }else{
	                    	  unDivValue = unDivValue+calValSlab4_4(unDivArray[i][1],unDivArray[i][0]);
	                      }*/
	                      
	                }
	                  
	                  divValue=1.5*totalDivArea*IrrigatedRate;
	                  
	                //added by ankit for diverted land changes
                      if(resirate*10000 == IrrigatedRate){	
            	  			double plotValue =  divArrayCopy[0][0]*divArrayCopy[0][1] + divArrayCopy[1][0]*divArrayCopy[1][1] + divArrayCopy[2][0]*divArrayCopy[2][1] + divArrayCopy[3][0]*divArrayCopy[3][1] +  divArrayCopy[4][0]*divArrayCopy[4][1] + divArrayCopy[5][0]*divArrayCopy[5][1];
            	  			divValue = divValue>plotValue?plotValue:divValue;
            	  		}
	                  
	            }
	            else
	            {
	            	 double newX=(((x/10000)-constructedArea));
	            	 
	            	 if(newX<=totalDivArea)
	            	 {
	            		 double minusx=newX;
	            		 
	            		// unDivValue=totalUnDivArea*resirate*10000;
	                        double xForDiv=newX;
	                       
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
	                    /*    for(int i=0;i<divArray.length;i++) 
	                    {
	                          //divValue = divValue+divArray[i][0]*divArray[i][1]*10000;
	                        //changes by ankit for new slab changes
	                          if(tempId==1){
	                        	  divValue = divValue+calValSlab4_1(divArray[i][1]*10000,divArray[i][0]);
		                      }else if(tempId==2){
		                    	  divValue = divValue+calValSlab4_2(divArray[i][1]*10000,divArray[i][0]);
		                      }else{
		                    	  divValue = divValue+calValSlab4_4(divArray[i][1]*10000,divArray[i][0]);
		                      }
	                    }
	                   */
	                        
	                        java.util.Arrays.sort(divArray, new java.util.Comparator<double[]>() {
	            	            public int compare(double[] a, double[] b) {
	            	                return Double.compare(a[0], b[0]);
	            	            }
	            	        });

	                         double higherRate = 0;
	                    	 double tempTotalArea=0.0;
	                    
	                  	  	for(int i=divArray.length-1;i>=0;i--)
	                                {
	 		  	                	  		if(i==(divArray.length-1)){
	 		  	                	  				higherRate =	divArray[i][0] ; //Higher Rate
	 		  		        	  					commArea=       divArray[i][1]*10000; // Area
	 		  		        	  					tempTotalArea=commArea;
	 		  	                	  		}else{
	 		  	                	  				tempTotalArea= tempTotalArea+divArray[i][1]*10000;
	 		  	                	  				
	 		  	                	  			}
	                                }
	                  	  			
	                  	  			double resiRate = resirate;
	                  	  			
	 	                  	  			if(tempId==1){                                    	
	 	                  	  				  divValue = divValue +calDivertedSlabVal4_1( commArea, higherRate, tempTotalArea, resiRate);
	 	                                  }
	 	                                  else if(tempId==2){
	 	                                	  divValue = divValue +calDivertedSlabVal4_2( commArea, higherRate, tempTotalArea, resiRate);
	 	                                  }else{
	 	                                	  	divValue = divValue +calDivertedSlabVal4_4( commArea, higherRate, tempTotalArea, resiRate);
	 	                                  }
	                        
	                        
	                        divValue = divValue+1.5*totalDivArea*IrrigatedRate;
	                        
	                        //added by ankit for diverted land changes
	                        if(resirate*10000 == IrrigatedRate){	
                  	  			double plotValue =  divArrayCopy[0][0]*divArrayCopy[0][1] + divArrayCopy[1][0]*divArrayCopy[1][1] + divArrayCopy[2][0]*divArrayCopy[2][1] + divArrayCopy[3][0]*divArrayCopy[3][1] +  divArrayCopy[4][0]*divArrayCopy[4][1] + divArrayCopy[5][0]*divArrayCopy[5][1];
                  	  		divValue = divValue>plotValue?plotValue:divValue;
                  	  		}
	                        
	                        
	                        for(int i=0;i<unDivArray.length;i++)
		                    {
		                          unDivValue = unDivValue+unDivArray[i][0]*unDivArray[i][1];		                          
		                        //changes by ankit for new slab changes
		                        /*  if(tempId==1){
			                    	  unDivValue = unDivValue+calValSlab4_1(unDivArray[i][1],unDivArray[i][0]);
			                      }else if(tempId==2){
			                    	  unDivValue = unDivValue+calValSlab4_2(unDivArray[i][1],unDivArray[i][0]);
			                      }else{
			                    	  unDivValue = unDivValue+calValSlab4_4(unDivArray[i][1],unDivArray[i][0]);
			                      }*/
		                    }	 
	            	 }
	            	 
	            	 else
	            	 {
	            		 double newX1=((x/10000)-totalDivArea-constructedArea);

	                        double minusx=newX1;
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
	                        
	                      //  unDivValue=unDivValue+ newX1*10000*resirate;                        
	                      //changes by ankit for new slab changes
	                        /*  if(tempId==1){
		                    	  unDivValue = unDivValue+calValSlab4_1(newX1*10000,resirate);
		                      }else if(tempId==2){
		                    	  unDivValue = unDivValue+calValSlab4_2(newX1*10000,resirate);
		                      }else{
		                    	  unDivValue = unDivValue+calValSlab4_4(newX1*10000,resirate);
		                      }
	                          */
	                        double higherRate = 0;
	                    	double tempTotalArea=0.0;
	                    
	                  	  	for(int i=divArray.length-1;i>=0;i--)
	                                {
	 		  	                	  		if(i==(divArray.length-1)){
	 		  	                	  				higherRate =	divArray[i][0] ; //Higher Rate
	 		  		        	  					commArea=       divArray[i][1]*10000; // Area
	 		  		        	  					tempTotalArea=commArea;
	 		  	                	  		}else{
	 		  	                	  				tempTotalArea= tempTotalArea+divArray[i][1]*10000;
	 		  	                	  				
	 		  	                	  			}
	                                }
	                  	  			tempTotalArea = tempTotalArea+newX1*10000;
	                  	  			double resiRate = resirate;
	                  	  			
	 	                  	  			if(tempId==1){                                    	
	 	                  	  				  divValue = divValue +calDivertedSlabVal4_1( commArea, higherRate, tempTotalArea, resiRate);
	 	                                  }
	 	                                  else if(tempId==2){
	 	                                	  divValue = divValue +calDivertedSlabVal4_2( commArea, higherRate, tempTotalArea, resiRate);
	 	                                  }else{
	 	                                	  divValue = divValue +calDivertedSlabVal4_4( commArea, higherRate, tempTotalArea, resiRate);
	 	                                  }
	                      /*  for(int i=0;i<divArray.length;i++) 
		                    {
		                         // divValue = divValue+divArray[i][0]*divArray[i][1]*10000;	                          
		                        //changes by ankit for new slab changes
		                          if(tempId==1){
		                        	  divValue = divValue+calValSlab4_1(divArray[i][1]*10000,divArray[i][0]);
			                      }else if(tempId==2){
			                    	  divValue = divValue+calValSlab4_2(divArray[i][1]*10000,divArray[i][0]);
			                      }else{
			                    	  divValue = divValue+calValSlab4_4(divArray[i][1]*10000,divArray[i][0]);
			                      }
		                    }*/
	                        

	                         
	            		 
	            	 }
	            	
	            	//
	                  /*double newX=((x/10000)-constructedArea);
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
	        */    }//
	            
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
	                        
	                        
	                        double higherRate = 0;
	                    	double tempTotalArea=0.0;
	                    
	                  	  	for(int i=divArray.length-1;i>=0;i--)
	                                {
	 		  	                	  		if(i==(divArray.length-1)){
	 		  	                	  				higherRate =	divArray[i][0] ; //Higher Rate
	 		  		        	  					commArea=       divArray[i][1]*10000; // Area
	 		  		        	  					tempTotalArea=commArea;
	 		  	                	  		}else{
	 		  	                	  				tempTotalArea= tempTotalArea+divArray[i][1]*10000;
	 		  	                	  				
	 		  	                	  			}
	                                }
	                  	  			
	                  	  			double resiRate = resirate;
	 	                            divValue = divValue +calDivertedSlabVal4_4( commArea, higherRate, tempTotalArea, resiRate);
	 	                                  
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

		
		public String calcAgriWithSubclauses(String unDivValue, String divValue, double irrigatedRate,String[] ids, PropertyValuationDTO propDTO, String totalArea, PropertyValuationForm propForm) {
		// TODO Auto-generated method stub
			String marketValue="";
			
			double finalValue=Double.parseDouble(unDivValue) + Double.parseDouble(divValue);
			
			double natHighway=0.0;
			double operandHighway=0.0;
			double distHighway=0.0;
			double operandDouble=0.0;
			boolean flag=true;
			
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
							
							baseValue=majorMineral(propForm);
							finalValue=baseValue;
							flag=false;
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
		if(flag)
		{
	for(int i=0;i<propForm.getAgriAddBuyerList().size();i++){
 		PropertyValuationDTO buyerDTO= propForm.getAgriAddBuyerList().get(i);
 		double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
	if(constructionCost>0)
	{
		if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
		{
		double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
		
		finalValue=finalValue+(plotValue*operandHighway/100);
		}
	}
	}
}
			marketValue=String.valueOf(finalValue);
			return marketValue;
			
			
		
	}

		
		
		

		//----major mineral excavation
		
		private double majorMineral(PropertyValuationForm propForm) {
			
			
			//TODO Auto-generated method stub
			PropertyValuationDTO propDTO = propForm.getPropDTO();
			
			double noBuyer=1;
			double value=0;
			double IrrigatedRate=Double.parseDouble((propDAO.getL1Rate(propDTO, "20", "3")));
			double resirate=Double.parseDouble(propDAO.getL2Rate(propDTO, "22", "26"));
			if(propDTO.getAreMoreBuyers().equalsIgnoreCase("morebuyers"))
			{
				if(propDTO.getFamily().equalsIgnoreCase("difffamily") && propDTO.getAgriDiscloseShare().equalsIgnoreCase("unopenshare"))
				{
					noBuyer=Double.parseDouble(propDTO.getNoOfBuyers());
					
				}
			}
			
			for(int i=0;i<propForm.getAgriAddBuyerList().size();i++){
		 		PropertyValuationDTO buyerDTO= propForm.getAgriAddBuyerList().get(i);
			
			
		 		
			if(buyerDTO.getAgriLandTypeId().equalsIgnoreCase("5"))
	 		{
				
				if(buyerDTO.getAgriBuildingConstructArea()==null || buyerDTO.getAgriBuildingConstructArea().equalsIgnoreCase(""))
				{
					buyerDTO.setAgriBuildingConstructArea("0.0");
				}
				double constructedArea=Double.parseDouble(buyerDTO.getAgriBuildingConstructArea());// in hectares
				constructedArea=constructedArea/10000;
		
				double TotalArea=0.0;
				
				TotalArea=Double.parseDouble(buyerDTO.getAgriTotalUnDivertedArea());
				propDTO.setAgriTotalUnDivertedArea(buyerDTO.getAgriTotalUnDivertedArea());
				
				//String guidelineId="";
				//String subIds="";
				String finalValue="";
				TotalArea=TotalArea-constructedArea;
				//double IrrigatedRate=0.0;
				//double UnIrrigatedRate=0.0;
				//double OneCropRate=0.0;
				//double DoubleCropRate=0.0;
				
				//String returnString[]=new String[2];
				
					if(buyerDTO.getAgriUndivSingleCropArea()==null || buyerDTO.getAgriUndivSingleCropArea().equalsIgnoreCase(""))
					{
						buyerDTO.setAgriUndivSingleCropArea("0");
					}
					
					if(buyerDTO.getAgriUndivDoubleCropArea()==null || buyerDTO.getAgriUndivDoubleCropArea().equalsIgnoreCase(""))
					{
						buyerDTO.setAgriUndivDoubleCropArea("0");
					}
					
					if(buyerDTO.getAgriUndivIrrigatedArea()==null || buyerDTO.getAgriUndivIrrigatedArea().equalsIgnoreCase(""))
					{
						buyerDTO.setAgriUndivIrrigatedArea("0");
					}
					
				
				
			//------end of addition----
			
				if("unopenshare".equalsIgnoreCase(propDTO.getAgriDiscloseShare()))
				{
					double noBuyers=Double.parseDouble(propDTO.getNoOfBuyers());
					//IrrigatedArea=IrrigatedArea/noBuyers;
					//OneCropArea=OneCropArea/noBuyers;
					//DoubleCropArea=DoubleCropArea/noBuyers;
					TotalArea=TotalArea/noBuyers;
					constructedArea=constructedArea/noBuyers;
					
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
			        
	 		
	 		
				 if(constructedArea>=(x/10000))
				 {
					 
					 
					 value=value+(TotalArea*1.5*IrrigatedRate);
					 double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
					 
					 if(constructionCost>0)
						{
						 
							if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
							{
							double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
							plotValue=plotValue/noBuyer;
							value=value+((plotValue*1.5));
							}
						}
					 
					 
				 }
				 
				 else
				 {
					 
					 x=x-constructedArea*10000;
					 
					 value=value+(((x*resirate)+(((TotalArea-x/10000)*IrrigatedRate)))*1.5);
					 
					 double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
					 
					 if(constructionCost>0)
						{
						 
							if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
							{
							double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
							plotValue=plotValue/noBuyer;
							value=value+((plotValue*1.5));
							}
						}
					 
				 }
				 
			        }
				 
				 else
				 {
					 
					 value=value+(TotalArea*IrrigatedRate*1.5);
					 
					 double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
					 
					 if(constructionCost>0)
						{
						 
							if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
							{
							double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
									plotValue=plotValue/noBuyer;
							value=(value+((plotValue*1.5)));
							}
						}
					 
					 
				 }
	 		}
			
			if(buyerDTO.getAgriLandTypeId().equalsIgnoreCase("6"))
	 		{
				//String[] returnString=new String[2];
	            
				double totalArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedArea());
				if(buyerDTO.getAgriBuildingConstructArea()==null || buyerDTO.getAgriBuildingConstructArea().equalsIgnoreCase(""))
				{
					buyerDTO.setAgriBuildingConstructArea("0.0");
				}
				double constructedArea=Double.parseDouble(buyerDTO.getAgriBuildingConstructArea());// in hectares
				constructedArea=constructedArea/10000;
	            //String guidelineId="";
	            //double IrrigatedRate=0.0;
	            //double UnIrrigatedRate=0.0;
				//double OneCropRate=0.0;
				//double Value=0.0;
				//double DoubleCropRate=0.0;
	            double minusConstructedArea=constructedArea;
	            totalArea=totalArea-constructedArea;
	            double arr[][]=new double[6][2];
	            
	            //-----null handling of diverted agriland areas---
	            
	            if(buyerDTO.getAgriTotalDivertedResidentialArea()==null || buyerDTO.getAgriTotalDivertedResidentialArea().equalsIgnoreCase(""))
				{
	            	buyerDTO.setAgriTotalDivertedResidentialArea("0");
				}
				
				if(buyerDTO.getAgriTotalDivertedCommercialArea()==null || buyerDTO.getAgriTotalDivertedCommercialArea().equalsIgnoreCase(""))
				{
					buyerDTO.setAgriTotalDivertedCommercialArea("0");
				}
				
				if(buyerDTO.getAgriTotalDivertedIndustrialArea()==null || buyerDTO.getAgriTotalDivertedIndustrialArea().equalsIgnoreCase(""))
				{
					buyerDTO.setAgriTotalDivertedIndustrialArea("0");
				}
				if(buyerDTO.getAgriTotalDivertedEducationalArea()==null || buyerDTO.getAgriTotalDivertedEducationalArea().equalsIgnoreCase(""))
				{
					buyerDTO.setAgriTotalDivertedEducationalArea("0");
				}
				if(buyerDTO.getAgriTotalDivertedHealthArea()==null || buyerDTO.getAgriTotalDivertedHealthArea().equalsIgnoreCase(""))
				{
					buyerDTO.setAgriTotalDivertedHealthArea("0");
				}
				if(buyerDTO.getAgriTotalDivertedOthersArea()==null || buyerDTO.getAgriTotalDivertedOthersArea().equalsIgnoreCase(""))
				{
					buyerDTO.setAgriTotalDivertedOthersArea("0");
				}
				
				 //-----end of null handling---
				
				double resiArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedResidentialArea());
				double commArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedCommercialArea());
				double indArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedIndustrialArea()); 
				double schoolArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedEducationalArea());
				double healthArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedHealthArea());
				double otherArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedOthersArea());
				
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
	            for(int j=0;j<arr.length;j++)
	            {
	                  if(minusConstructedArea<=arr[j][1] && minusConstructedArea!=0.0 )
	                  {
	                        arr[j][1]=arr[j][1]-minusConstructedArea;
	                        minusConstructedArea=0.0;
	                  }
	                  
	                  else if(minusConstructedArea==0.0)
	                  {
	                	  
	                  }
	                	
	                  else
	                  {
	                        minusConstructedArea=minusConstructedArea-arr[j][1];
	                        arr[j][1]=0.0;
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
	                  
	                  
	                  if(constructedArea>=(x/10000))
						{
	                	  
	                	  value=value+(totalArea*1.5*IrrigatedRate);
		 					 double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
		 					 
		 					 if(constructionCost>0)
		 						{
		 						 
		 							if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
		 							{
		 							double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
		 							plotValue=plotValue/noBuyer;
									value=value+((plotValue*1.5));
		 							}
		 						}
						}
	                  
						else
						{
							
							
							double minusArea=0.0;
	                	  	minusArea=x/10000-constructedArea;
	                        for(int z=arr.length-1;z>=0;z--)
	                        {
	                              if(minusArea>=arr[z][1])
	                              {
	                                    minusArea=minusArea-arr[z][1];
	                                    continue;
	                              }
	                              else if(minusArea<=arr[z][1]&&minusArea>=0)
	                              {
	                                    arr[z][1]=minusArea;
	                                    minusArea=0.0;
	                                    continue;
	                              }
	                              else if(minusArea<=0)
	                              {
	                                    arr[z][1]=0.0;
	                              }
	                        }
	                        for(int k=0;k<arr.length;k++) 
	                        {
	                              value = value+arr[k][0]*arr[k][1]*10000*1.5;
	                        }
	                        value=value+((totalArea-(x/10000-constructedArea)))*1.5*IrrigatedRate;
	                        double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
		 					 
		 					 if(constructionCost>0)
		 						{
		 						 
		 							if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
		 							{
		 							double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
		 							plotValue=plotValue/noBuyer;
									value=value+((plotValue*1.5));
		 							}
		 						}
						}
	                  
	                  
	                  
	                  
	                  
	            }
	            
	            else
	            {
	            	
	            	
	            	value=value+(totalArea*IrrigatedRate*1.5);
					 
					 double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
					 
					 if(constructionCost>0)
						{
						 
							if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
							{
							double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
							
							plotValue=plotValue/noBuyer;
							value=value+(((plotValue*1.5)));
							}
						}
					 
	            }
	 		}
			
			if(buyerDTO.getAgriLandTypeId().equalsIgnoreCase("7"))
	 		{
				 //String returnString[]= new String[3];
		            
		            
		          //---null handling of undiverted areas
		            
		            if(buyerDTO.getAgriUndivSingleCropArea()==null || buyerDTO.getAgriUndivSingleCropArea().equalsIgnoreCase(""))
					{
		            	buyerDTO.setAgriUndivSingleCropArea("0");
					}
					
					if(buyerDTO.getAgriUndivDoubleCropArea()==null || buyerDTO.getAgriUndivDoubleCropArea().equalsIgnoreCase(""))
					{
						buyerDTO.setAgriUndivDoubleCropArea("0");
					}
					
					if(buyerDTO.getAgriUndivIrrigatedArea()==null || buyerDTO.getAgriUndivIrrigatedArea().equalsIgnoreCase(""))
					{
						buyerDTO.setAgriUndivIrrigatedArea("0");
					}
					//---end of null handling 
					
					
					//---null handling of diverted areas
					
					 if(buyerDTO.getAgriTotalDivertedResidentialArea()==null || buyerDTO.getAgriTotalDivertedResidentialArea().equalsIgnoreCase(""))
						{
						 buyerDTO.setAgriTotalDivertedResidentialArea("0");
						}
						
						if(buyerDTO.getAgriTotalDivertedCommercialArea()==null || buyerDTO.getAgriTotalDivertedCommercialArea().equalsIgnoreCase(""))
						{
							buyerDTO.setAgriTotalDivertedCommercialArea("0");
						}
						
						if(buyerDTO.getAgriTotalDivertedIndustrialArea()==null || buyerDTO.getAgriTotalDivertedIndustrialArea().equalsIgnoreCase(""))
						{
							buyerDTO.setAgriTotalDivertedIndustrialArea("0");
						}
						if(buyerDTO.getAgriTotalDivertedEducationalArea()==null || buyerDTO.getAgriTotalDivertedEducationalArea().equalsIgnoreCase(""))
						{
							buyerDTO.setAgriTotalDivertedEducationalArea("0");
						}
						if(buyerDTO.getAgriTotalDivertedHealthArea()==null || buyerDTO.getAgriTotalDivertedHealthArea().equalsIgnoreCase(""))
						{
							buyerDTO.setAgriTotalDivertedHealthArea("0");
						}
						if(buyerDTO.getAgriTotalDivertedOthersArea()==null || buyerDTO.getAgriTotalDivertedOthersArea().equalsIgnoreCase(""))
						{
							buyerDTO.setAgriTotalDivertedOthersArea("0");
						}
					//---end of null handling 
						// TODO Auto-generated method stub
						
		            double divValue=0;
		            double unDivValue=0;
		            //double IrrigatedRate=0.0;
		            
					//double UnIrrigatedRate=0.0;
					//double OneCropRate=0.0;
					//double DoubleCropRate=0.0;
					//String guidelineId="";
		            
		            
		            double totalUnDivArea=Double.parseDouble(buyerDTO.getAgriTotalUnDivertedArea());
		            double oneCropArea=Double.parseDouble(buyerDTO.getAgriUndivSingleCropArea());
		            double twoCropArea=Double.parseDouble(buyerDTO.getAgriUndivDoubleCropArea());
		            double irrigatedArea=Double.parseDouble(buyerDTO.getAgriUndivIrrigatedArea());
		            double totalDivArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedArea());
		            double resiArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedResidentialArea());
		            double commArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedCommercialArea());
		            double indArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedIndustrialArea());
		            double healthArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedHealthArea());
		            double schoolArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedEducationalArea());
		            double otherArea=Double.parseDouble(buyerDTO.getAgriTotalDivertedOthersArea());
		            double constructedArea=Double.parseDouble(buyerDTO.getAgriBuildingConstructArea())/10000;     //in hectares
		            
		            
		            
		        
		            
		            double healthrate=0.0;
		            double schoolrate=0.0;
		            
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
		                  //double noBuyer =Double.parseDouble(propDTO.getNoOfBuyers());
		                  totalDivArea=totalDivArea/noBuyer;
		                  oneCropArea=oneCropArea/noBuyer;
		                  twoCropArea=twoCropArea/noBuyer;
		                  irrigatedArea=irrigatedArea/noBuyer;
		                  totalUnDivArea=(totalUnDivArea/noBuyer);
		                  resiArea=resiArea/noBuyer;
		                  commArea=commArea/noBuyer;
		                  indArea=indArea/noBuyer;
		                  schoolArea=schoolArea/noBuyer;
		                  healthArea=healthArea/noBuyer;
		                  otherArea=otherArea/noBuyer;
		                  constructedArea=constructedArea/noBuyer;
		            }
		            //double[][] unDivArray= new double[3][2];
		            double[][] divArray= new double[6][2];
		            
		            //unDivArray[0][0]=IrrigatedRate;
		           //unDivArray[0][1]=irrigatedArea;
		           // unDivArray[1][0]=OneCropRate;
		           // unDivArray[1][1]=oneCropArea;
		           // unDivArray[2][0]=DoubleCropRate;
		           // unDivArray[2][1]=twoCropArea;
		            	
		            
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
					
		            
		            
		           /* java.util.Arrays.sort(unDivArray, new java.util.Comparator<double[]>() {
		            public int compare(double[] a, double[] b) {
		                return Double.compare(a[0], b[0]);
		            }
		        });*/
					
		            java.util.Arrays.sort(divArray, new java.util.Comparator<double[]>() {
		            public int compare(double[] a, double[] b) {
		                return Double.compare(a[0], b[0]);
		            }
		        });
		            if(constructedArea>0&&constructedArea<=totalDivArea)
		            {
		                  totalDivArea=totalDivArea-constructedArea;
		                  double minusConstructedArea=constructedArea;
		                    for(int m=0;m<divArray.length;m++)
		                  {
		                      
		                          if(minusConstructedArea<=divArray[m][1] && minusConstructedArea!=0.0 )
		                        {
		                          divArray[m][1]=divArray[m][1]-minusConstructedArea;
		                              minusConstructedArea=0.0;
		                        }
		                        
		                        else if(minusConstructedArea==0.0)
		                        {
		                          
		                        }
		                        
		                        else
		                        {
		                              minusConstructedArea=minusConstructedArea-divArray[m][1];
		                              divArray[m][1]=0.0;
		                        }
		                  }

		                  
		            }
		            else if(constructedArea>0&&constructedArea>totalDivArea)
		            {
		            	
		            	
		            	
		            	totalUnDivArea=totalUnDivArea-constructedArea+totalDivArea;
		            	totalDivArea=0;
		            	/*
		                  double minusConstructedArea=constructedArea-totalDivArea;
		                  totalDivArea=0.0;
		                  totalUnDivArea=totalUnDivArea-minusConstructedArea;
		                  for(int l=0;l<unDivArray.length;l++)
		            {
		                
		                    if(minusConstructedArea<=unDivArray[l][1] && minusConstructedArea!=0.0 )
		                  {
		                    divArray[l][1]=unDivArray[l][1]-minusConstructedArea;
		                        minusConstructedArea=0.0;
		                  }
		                  
		                  else if(minusConstructedArea==0.0)
		                  {
		                    
		                  }
		                  
		                  else
		                  {
		                        minusConstructedArea=minusConstructedArea-unDivArray[l][1];
		                        unDivArray[l][1]=0.0;
		                  }
		            }

		            */}
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
		            
		            if(constructedArea>=(x/10000))
		            {
		            	value=value+((totalDivArea+totalUnDivArea)*IrrigatedRate*1.5);
						 
						 double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
						 
						 if(constructionCost>0)
							{
							 
								if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
								{
								double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
								
								plotValue=plotValue/noBuyer;
								value=value+((plotValue*1.5));
								}
							}
		            	
		            	
		            }
		            else
		            {
		            	
		            	x=x-constructedArea*10000;
		            	
		            	if((x/10000)<=totalDivArea)
		            	{
		            		double minusArea=0.0;
		            		double summinusArea=x/10000;
		            		minusArea=x/10000;
	                        for(int z=divArray.length-1;z>=0;z--)
	                        {
	                              if(minusArea>=divArray[z][1])
	                              {
	                                    minusArea=minusArea-divArray[z][1];
	                                    continue;
	                              }
	                              else if(minusArea<=divArray[z][1]&&minusArea>=0)
	                              {
	                            	  divArray[z][1]=minusArea;
	                                    minusArea=0.0;
	                                    continue;
	                              }
	                              else if(minusArea<=0)
	                              {
	                            	  divArray[z][1]=0.0;
	                              }
	                        }
	                        for(int k=0;k<divArray.length;k++) 
	                        {
	                              value = value+divArray[k][0]*divArray[k][1]*10000*1.5;
	                        }
	                        value=value+(totalDivArea+totalUnDivArea-summinusArea)*1.5*IrrigatedRate;
	                        double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
			            	if(constructionCost>0)
							{
							 
								if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
								{
								double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
								
								plotValue=plotValue/noBuyer;
								value=value+((plotValue*1.5));
								}
							}
						}
		           	 
	                  
	                  
		            	
		            	
		            	else
		            	{
		            		
		            		double minusArea=0.0;
	                	  	minusArea=x;
	                        for(int z=divArray.length-1;z>=0;z--)
	                        {
	                              if(minusArea>=divArray[z][1])
	                              {
	                                    minusArea=minusArea-divArray[z][1];
	                                    continue;
	                              }
	                              else if(minusArea<=divArray[z][1]&&minusArea>=0)
	                              {
	                            	  divArray[z][1]=minusArea;
	                                    minusArea=0.0;
	                                    continue;
	                              }
	                              else if(minusArea<=0)
	                              {
	                            	  divArray[z][1]=0.0;
	                              }
	                        }
	                        for(int k=0;k<divArray.length;k++) 
	                        {
	                              value = value+divArray[k][0]*divArray[k][1]*10000*1.5;
	                        }
		            		
	                        value= value+((totalUnDivArea-x+totalDivArea)*IrrigatedRate+(x-totalDivArea)*resirate)*1.5;
	                        double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
	                        if(constructionCost>0)
							{
							 
								if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
								{
								double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
								
								plotValue=plotValue/noBuyer;
								value=value+((plotValue*1.5));
								}
							}
		            		
		            	}
		            }
		            
		        }
		            else
		            {

		            	
		            	
		            	value=value+((totalDivArea+totalUnDivArea)*IrrigatedRate*1.5);
						 
						 double constructionCost=Double.parseDouble(buyerDTO.getAgriBuildingIndivConstValue());
						 
						 if(constructionCost>0)
							{
							 
								if(buyerDTO.getAgriBuildingPlotValue()!=null && !buyerDTO.getAgriBuildingPlotValue().equalsIgnoreCase(""))
								{
								double plotValue=Double.parseDouble(buyerDTO.getAgriBuildingPlotValue());
								
								plotValue=plotValue/noBuyer;
								value=value+((plotValue*1.5));
								}
							}
						 
		            
		            	
		            	
		            }
		            

	 		}
			
			}
			return value*noBuyer;
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
		
		//Added by ankit for new slab changes - start
		public double calValSlab4_1Undiverted(double x, double Rate){
			logger.info("-------- method calValSlab4_1 -------------");
			double Val =0;
			double val1 =0;
			double val2 =0;
			double val3 =0;
			double x3 =0;
			double x2 =0;
			
			if(x>=400 && x<=1000){
				
				x2=x-400;
				
				val1 = 400*Rate*(PropertyValuationConstant.SLAB_4_1_FACT1);
				
				if(x2>=300){
					 x3=x2-300;
					 val2 = 300*Rate*(PropertyValuationConstant.SLAB_4_1_FACT2);
					
					 if(x3>=0){
						 	val3 = x3*Rate*(PropertyValuationConstant.SLAB_4_1_FACT3); 
					 }
	
				}else{
					 val2 = x2*Rate*(PropertyValuationConstant.SLAB_4_1_FACT2);
				}
				
				Val = val1+val2+val3;
				
			}else if(x<400){
						Val = x*Rate*(PropertyValuationConstant.SLAB_4_1_FACT1);
			}else{
					logger.info(" value of x is greater than max value : "+x);
					
					
			}
	
			logger.info(" calValSlab4_1 Rate : "+ Rate);
			logger.info("value 1 : "+val1 + " and x : "+x);
			logger.info("value 2 : "+val2 + " and x2 : "+x2);
			logger.info("value 3 : "+val3 + " and x3 : "+x3);
			logger.info("Total Value : "+Val);
			return Val;
		}
		
		public double calValSlab4_2Undiverted(double x, double Rate){
			logger.info("-------- method calValSlab4_2 -------------");
			double Val =0;
			double val1 =0;
			double val2 =0;
			double val3 =0;
			double x3 =0;
			double x2 =0;
			
			if(x>=PropertyValuationConstant.SLAB_4_2_AREA1 && x<=PropertyValuationConstant.SLAB_4_2_MAXAREA){
				
				x2=x-PropertyValuationConstant.SLAB_4_2_AREA1;
				
				val1 = PropertyValuationConstant.SLAB_4_2_AREA1*Rate*(PropertyValuationConstant.SLAB_4_2_FACT1);
				
				if(x2>= PropertyValuationConstant.SLAB_4_2_AREA2){
					 x3=x2-PropertyValuationConstant.SLAB_4_2_AREA2;
					 val2 = PropertyValuationConstant.SLAB_4_2_AREA2*Rate*(PropertyValuationConstant.SLAB_4_2_FACT2);
					
					 if(x3>=0){
						 	val3 = x3*Rate*(PropertyValuationConstant.SLAB_4_2_FACT3); 
					 }
	
				}else{
					 val2 = x2*Rate*(PropertyValuationConstant.SLAB_4_2_FACT2);
				}
				
				Val = val1+val2+val3;
				
			}else if(x<PropertyValuationConstant.SLAB_4_2_AREA1){
						Val = x*Rate*(PropertyValuationConstant.SLAB_4_2_FACT1);
			}else{
					logger.info(" value of x is greater than max value : "+x);
					
					
			}
			
			logger.info("calValSlab4_2 Rate: "+Rate);
			logger.info("value 1 : "+val1 + " and x : "+x);
			logger.info("value 2 : "+val2 + " and x2 : "+x2);
			logger.info("value 3 : "+val3 + " and x3 : "+x3);
			logger.info("Total Value : "+Val);
			return Val;
		}
		
		public double calValSlab4_4Undiverted(double x, double Rate){
			logger.info("-------- method calValSlab4_4 -------------");
			double Val =0;
			double val1 =0;
			double val2 =0;
			double val3 =0;
			double x3 =0;
			double x2 =0;
			
			if(x>=PropertyValuationConstant.SLAB_4_4_AREA1 && x<=PropertyValuationConstant.SLAB_4_4_MAXAREA){
				
				x2 = x-PropertyValuationConstant.SLAB_4_4_AREA1;
				
				val1 = PropertyValuationConstant.SLAB_4_4_AREA1*Rate*(PropertyValuationConstant.SLAB_4_4_FACT1);
				
				if(x2>= PropertyValuationConstant.SLAB_4_4_AREA2){
					 x3=x2-PropertyValuationConstant.SLAB_4_4_AREA2;
					 val2 = PropertyValuationConstant.SLAB_4_4_AREA2*Rate*(PropertyValuationConstant.SLAB_4_4_FACT2);
					
					 if(x3>=0){
						 	val3 = x3*Rate*(PropertyValuationConstant.SLAB_4_4_FACT3); 
					 }
	
				}else{
					 val2 = x2*Rate*(PropertyValuationConstant.SLAB_4_4_FACT2);
				}
				
				Val = val1+val2+val3;
				
			}else if(x<PropertyValuationConstant.SLAB_4_4_AREA1){
						Val = x*Rate*(PropertyValuationConstant.SLAB_4_4_FACT1);
			}else{
					logger.info(" value of x is greater than max value : "+x);
					
					
			}
			logger.info("calValSlab4_4 : "+Rate);
			logger.info("value 1 : "+val1 + " and x : "+x);
			logger.info("value 2 : "+val2 + " and x2 : "+x2);
			logger.info("value 3 : "+val3 + " and x3 : "+x3);
			logger.info("Total Value : "+Val);
			return Val;
		}
		

		//Added by ankit for new slab changes - end
		
		
		public double calDivertedSlabVal4_1(double commArea,double higherRate,double totalArea,double resiRate){
			logger.info("commArea : "+commArea);
			logger.info("higherRate : "+higherRate);
			logger.info("totalArea : "+totalArea);
			logger.info("resiRate : "+resiRate);
			
			 double x1=400;                                    
             double x2=300;                                                      
             double x3=300;
             double Value=0;  
             double remainngArea =0.0;
             int flagged=0;
             
               if(x1>commArea ){
                                 
               x1= x1-commArea;                                                      
               Value = Value+commArea*higherRate*1;                                                     
               flagged=1;
                                 
               }
                                 
               else if(x1<=commArea && commArea<=x1+x2){
                                 
               Value = Value+400*higherRate*1+(commArea-x1)*higherRate*0.8;                                                      
                                                                   
               x2=(x1+x2)-commArea;      
               x1=0; 
               flagged=2;
                                 
               }else if( commArea>=x1+x2 && commArea<=x1+x2+x3){
                                 
               Value = Value+400*higherRate+300*higherRate*0.8+(commArea-x1-x2)*higherRate*0.6;
                                 
                                                                   
               x3=(x1+x2+x3)-commArea;   
               x1=0;                                                      
               x2=0;  
               flagged=3;
                                 
               }else{     
               			flagged =0; //commercial 0;               
               }

                 logger.info(" value for higher area : "+Value);
                 
               switch (flagged){
                                 
               case 0 :
                                        
                                 
                       break;
                                 
               case 1 :
                                 
                       remainngArea=totalArea-commArea;                 
                       x1=(remainngArea>x1)?(x1):(remainngArea);                 
                       if(x1==remainngArea){                
			                                    x2=0;                
			                                    x3=0;                
                       }else{     
                       		remainngArea = remainngArea-x1;               
                       		x2=(remainngArea>x2)?(x2):(remainngArea);
                                         
                               if(x2==remainngArea){             
                               x3=0;                 
                               }else{  
                               		x3=remainngArea-x2;
                                         
                               	}
                       }
                                         
                       Value = Value+x1*resiRate*1+x2*resiRate*0.8+x3*resiRate*0.6;
                                         
                       break;
                                 
               case 2 :
                                 
		               		remainngArea=totalArea-commArea;          
		               		x2 = (remainngArea>x2)? x2:remainngArea;
		                             
		               		if(x2==remainngArea){                                                     
		                        x3=0;                
		               		}else{
		               				x3 = remainngArea-x2;       
		               		}
		
		                                 
		                 Value = Value+x2*resiRate*0.8+x3*resiRate*0.6;                                                      
		                 break;
                                                                                    
               case 3 :
                                 
                           remainngArea=totalArea-commArea;                                                    
                           Value = Value+remainngArea*resiRate*0.6;                                                     
                           break;                 
               }
		logger.info(" calDivertedSlabVal4_1 Value : "+Value);
               return Value;
		}
		
		public double calDivertedSlabVal4_2(double commArea,double higherRate,double totalArea,double resiRate){

			logger.info("commArea : "+commArea);
			logger.info("higherRate : "+higherRate);
			logger.info("totalArea : "+totalArea);
			logger.info("resiRate : "+resiRate);
			
			 double x1=200;                                    
             double x2=150;                                                      
             double x3=150;
             double Value=0;  
             double remainngArea =0.0;
             int flagged=0;
             
               if(x1>commArea ){
                                 
               x1= x1-commArea;                                                      
               Value = Value+commArea*higherRate*1;                                                     
               flagged=1;
                                 
               }
                                 
               else if(x1<=commArea && commArea<=x1+x2){
                                 
               Value = Value+200*higherRate*1+(commArea-x1)*higherRate*0.8;                                                      
                                                                   
               x2=(x1+x2)-commArea;      
               x1=0; 
               flagged=2;
                                 
               }else if( commArea>=x1+x2 && commArea<=x1+x2+x3){
                                 
               Value = Value+200*higherRate+150*higherRate*0.8+(commArea-x1-x2)*higherRate*0.6;
                                 
                                                                   
               x3=(x1+x2+x3)-commArea;   
               x1=0;                                                      
               x2=0;  
               flagged=3;
                                 
               }else{     
               			flagged =0; //commercial 0;               
               }

                 logger.info(" value for higher area : "+Value);
                 
               switch (flagged){
                                 
               case 0 :
                                        
                                 
                       break;
                                 
               case 1 :
                                 
                       remainngArea=totalArea-commArea;                 
                       x1=(remainngArea>x1)?(x1):(remainngArea);                 
                       if(x1==remainngArea){                
			                                    x2=0;                
			                                    x3=0;                
                       }else{     
                       		remainngArea = remainngArea-x1;               
                       		x2=(remainngArea>x2)?(x2):(remainngArea);
                                         
                               if(x2==remainngArea){             
                               x3=0;                 
                               }else{  
                               		x3=remainngArea-x2;
                                         
                               	}
                       }
                                         
                       Value = Value+x1*resiRate*1+x2*resiRate*0.8+x3*resiRate*0.6;
                                         
                       break;
                                 
               case 2 :
                                 
		               		remainngArea=totalArea-commArea;          
		               		x2 = (remainngArea>x2)? x2:remainngArea;
		                             
		               		if(x2==remainngArea){                                                     
		                        x3=0;                
		               		}else{
		               				x3 = remainngArea-x2;       
		               		}
		
		                                 
		                 Value = Value+x2*resiRate*0.8+x3*resiRate*0.6;                                                      
		                 break;
                                                                                    
               case 3 :
                                 
                           remainngArea=totalArea-commArea;                                                    
                           Value = Value+remainngArea*resiRate*0.6;                                                     
                           break;                 
               }
		logger.info(" calDivertedSlabVal4_2 Value : "+Value);
               return Value;
		
		}
		
		public double calDivertedSlabVal4_4(double commArea,double higherRate,double totalArea,double resiRate){

			logger.info("commArea : "+commArea);
			logger.info("higherRate : "+higherRate);
			logger.info("totalArea : "+totalArea);
			logger.info("resiRate : "+resiRate);
			
			 double x1=120;                                    
             double x2=90;                                                      
             double x3=90;
             double Value=0;  
             double remainngArea =0.0;
             int flagged=0;
             
               if(x1>commArea ){
                                 
               x1= x1-commArea;                                                      
               Value = Value+commArea*higherRate*1;                                                     
               flagged=1;
                                 
               }
                                 
               else if(x1<=commArea && commArea<=x1+x2){
                                 
               Value = Value+120*higherRate*1+(commArea-x1)*higherRate*0.8;                                                      
                                                                   
               x2=(x1+x2)-commArea;      
               x1=0; 
               flagged=2;
                                 
               }else if( commArea>=x1+x2 && commArea<=x1+x2+x3){
                                 
               Value = Value+120*higherRate+90*higherRate*0.8+(commArea-x1-x2)*higherRate*0.6;
                                 
                                                                   
               x3=(x1+x2+x3)-commArea;   
               x1=0;                                                      
               x2=0;  
               flagged=3;
                                 
               }else{     
               			flagged =0; //commercial 0;               
               }

                 logger.info(" value for higher area : "+Value);
                 
               switch (flagged){
                                 
               case 0 :
                                        
                                 
                       break;
                                 
               case 1 :
                                 
                       remainngArea=totalArea-commArea;                 
                       x1=(remainngArea>x1)?(x1):(remainngArea);                 
                       if(x1==remainngArea){                
			                                    x2=0;                
			                                    x3=0;                
                       }else{     
                       		remainngArea = remainngArea-x1;               
                       		x2=(remainngArea>x2)?(x2):(remainngArea);
                                         
                               if(x2==remainngArea){             
                               x3=0;                 
                               }else{  
                               		x3=remainngArea-x2;
                                         
                               	}
                       }
                                         
                       Value = Value+x1*resiRate*1+x2*resiRate*0.8+x3*resiRate*0.6;
                                         
                       break;
                                 
               case 2 :
                                 
		               		remainngArea=totalArea-commArea;          
		               		x2 = (remainngArea>x2)? x2:remainngArea;
		                             
		               		if(x2==remainngArea){                                                     
		                        x3=0;                
		               		}else{
		               				x3 = remainngArea-x2;       
		               		}
		
		                                 
		                 Value = Value+x2*resiRate*0.8+x3*resiRate*0.6;                                                      
		                 break;
                                                                                    
               case 3 :
                                 
                           remainngArea=totalArea-commArea;                                                    
                           Value = Value+remainngArea*resiRate*0.6;                                                     
                           break;                 
               }
		logger.info(" calDivertedSlabVal4_4 Value : "+Value);
               return Value;
		
		}
		
}

