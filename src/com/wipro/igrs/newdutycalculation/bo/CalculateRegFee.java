package com.wipro.igrs.newdutycalculation.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.newdutycalculation.dao.DutyCalculationDAO;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;

public class CalculateRegFee {
	private static Logger logger = org.apache.log4j.Logger.getLogger(CalculateRegFee.class);
	DutyCalculationDAO dao = null;

	public CalculateRegFee() {
		dao = new DutyCalculationDAO();
	}

	public String calculateRegFee(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, DutyCalculationDTO propDTO) {
		ArrayList stampDetails = null;
		if (instDTO.getInstId() == 2105 || instDTO.getInstId() == 2106 || instDTO.getInstId() == 2108 || instDTO.getInstId() == 2184) {
			double partitionMV = 0;
			double maxMv = 0;
			ArrayList partitionList = dutyDTO.getPropertyDetailsList();
			for (int i = 0; i < partitionList.size(); i++) {
				DutyCalculationDTO pdto = (DutyCalculationDTO) partitionList.get(i);
				partitionMV = partitionMV + Double.parseDouble(pdto.getMarketValue());
				if (Double.parseDouble(pdto.getMarketValue()) >= maxMv) {
					maxMv = Double.parseDouble(pdto.getMarketValue());
				}
			}
			dutyDTO.setPartitionMV(partitionMV);
			dutyDTO.setMaxPartitionMv(maxMv);
		}
		if (instDTO.getInstId() == 2087) {
			stampDetails = dao.getRegFeeDetails(String.valueOf(1053), String.valueOf(2090)); // FEE ACCORDING TO CONEYANCE AGRREMENT WITH POSSESION
		} else if (instDTO.getInstId() == 2220 || instDTO.getInstId() == 2219) {
			stampDetails = dao.getRegFeeDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getLeaseId()));
		} else {
			stampDetails = dao.getRegFeeDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getInstId()));
		}
		double regFee = 0;
		int operationId;
		double operationValue;
		double maxValue;
		double minValue;
		double multiply = 0;
		double marketValue = 0;
		double marketValueBuilderShare = 0; //added for developer agreement work
		if (dutyDTO.getDeedId() == 1054) {
			if (dutyDTO.getExchangeMV1() > dutyDTO.getExchangeMV2()) {
				marketValue = dutyDTO.getExchangeMV1();
			} else {
				marketValue = dutyDTO.getExchangeMV2();
			}
		} else if (instDTO.getInstId() == 2105 || instDTO.getInstId() == 2106 || instDTO.getInstId() == 2108 || instDTO.getInstId() == 2184) {
			marketValue = dutyDTO.getPartitionMV() - dutyDTO.getMaxPartitionMv();
		} else if (instDTO.getInstId() == 2107) {
			marketValue = (dutyDTO.getTotalLandRevenue() - dutyDTO.getMaxLandRevenue()) * 100;
		} else {
			if (propDTO.getMarketValue() != null && !propDTO.getMarketValue().equalsIgnoreCase("")) {
				marketValue = Double.parseDouble(propDTO.getMarketValue());
			}
			//added for developer agreement
			if (dutyDTO.getPropDTO() != null) {
				if (dutyDTO.getPropDTO().getBuilderSharePropValue() != null && !dutyDTO.getPropDTO().getBuilderSharePropValue().equalsIgnoreCase("")) {
					marketValueBuilderShare = Double.parseDouble(dutyDTO.getPropDTO().getBuilderSharePropValue());
				}
			}
		}
		double considerationAmount = 0;
		if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
			considerationAmount = Double.parseDouble(instDTO.getAsConsiderationAmount());
		}
		if (instDTO.getInstId() == 2215 || instDTO.getInstId() == 2218 || instDTO.getInstId() == 2217 || instDTO.getInstId() == 2216 || instDTO.getInstId() == 2220 || instDTO.getInstId() == 2219 || instDTO.getInstId() == 2223) {
			double stampAmount = 0;
			if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
				stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
			}
			considerationAmount = considerationAmount + stampAmount;
		}
		if ("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag()) && (dutyDTO.getDeedId() == 1053 || dutyDTO.getDeedId() == 1092)) {
			marketValue = Double.parseDouble(dutyDTO.getGovValue());
			considerationAmount = 0;
		}
		if (stampDetails != null && stampDetails.size() > 0) {
			ArrayList subList = (ArrayList) stampDetails.get(0);
			if (subList.get(1) == null || subList.get(1).toString().equalsIgnoreCase("")) {
				operationId = 0;
			} else {
				operationId = Integer.parseInt(subList.get(1).toString());
				if ("Y".equalsIgnoreCase(dutyDTO.getCancellationFlag())) {
					if (operationId == 1) {
						return dao.getCancelaationRegFee(dutyDTO.getDeedId(), instDTO.getInstId());
					}
				}
			}
			if (subList.get(2) == null || subList.get(2).toString().equalsIgnoreCase("")) {
				operationValue = 0;
			} else {
				operationValue = Double.parseDouble(subList.get(2).toString());
			}
			if (subList.get(3) == null || subList.get(3).toString().equalsIgnoreCase("")) {
				maxValue = 0;
			} else {
				maxValue = Double.parseDouble(subList.get(3).toString());
			}
			if (subList.get(4) == null || subList.get(4).toString().equalsIgnoreCase("")) {
				minValue = 0;
			} else {
				minValue = Double.parseDouble(subList.get(4).toString());
			}
			switch (operationId) {
			case 1:
				regFee = operationValue;
				break;
			case 2:
				break;
			case 3:
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
				} else if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
					multiply = Double.parseDouble(instDTO.getCommonUserField());
				} else if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
					multiply = Double.parseDouble(instDTO.getAsConsiderationAmount());
				}
				regFee = (multiply * operationValue / 100);
				if ("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag()) && (dutyDTO.getDeedId() == 1053 || dutyDTO.getDeedId() == 1092)) {
					regFee = (Double.parseDouble(dutyDTO.getGovValue()) * operationValue) / 100;
				}
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				regFee = propDTO.getStampDuty() * operationValue;
				break;
			case 10:
				if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
					considerationAmount = Double.parseDouble(instDTO.getCommonUserField());
				}
				if (marketValue > considerationAmount) {
					regFee = (marketValue * 0.8) / 100;
				} else {
					regFee = (considerationAmount * 0.8) / 100;
				}
				break;
			case 11:
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
				} else if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
					multiply = Double.parseDouble(instDTO.getCommonUserField());
				} else if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
					multiply = Double.parseDouble(instDTO.getAsConsiderationAmount());
				}
				regFee = Math.ceil((multiply * 0.8 / 100));
				if ("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag()) && (dutyDTO.getDeedId() == 1053 || dutyDTO.getDeedId() == 1092)) {
					regFee = Math.ceil((Double.parseDouble(dutyDTO.getGovValue()) * 0.8 / 100));
				}
				break;
			case 12: //added by ankit as per Dept. req
				if (propDTO.getTotal() != null && propDTO.getTotal() >= 0) {
					regFee = (propDTO.getTotal()) * operationValue / 100;
				}
				break;
			case 13:
				//added by ankit as per Dept. req
				regFee = (marketValue * operationValue) / 100;
				break;
			case 15:
				if (instDTO.getInstId() == 2277) {
					if (dutyDTO.getGovValue() != null && !dutyDTO.getGovValue().isEmpty() && !dutyDTO.getGovValue().equals("")) {
						regFee = (marketValue * operationValue) / 100;
					} else if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("") && instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getInStampDutyAmount()) + Double.parseDouble(instDTO.getAsConsiderationAmount());
						regFee = (multiply * operationValue) / 100;
					}
				}
				break;
			}
			if (minValue > 0 && regFee < minValue) {
				regFee = minValue;
			}
			if (maxValue > 0 && regFee > maxValue) {
				regFee = maxValue;
			}
		} else {
			if (instDTO.getInstId() == 2179) {
				logger.info("marketValueBuilderShare : " + marketValueBuilderShare);
				if ("Y".equalsIgnoreCase(dutyDTO.getHvShare())) {
					logger.info("marketValueBuilderShare : " + marketValueBuilderShare);
					if (dutyDTO.getPropDTO() != null) {
						logger.info("building check----->" + dutyDTO.getPropDTO().getPropertyName());
						if (dutyDTO.getPropDTO().getPropertyName().equals("2")) {
							marketValueBuilderShare = marketValue * (Double.parseDouble(instDTO.getInStampDutyAmount())) / 100;
						}
					}
					//double stampAmount=(Double.parseDouble(instDTO.getInStampDutyAmount()))/100;
					double stampduty = (5 * marketValueBuilderShare) / 100;
					double stampduty1 = (2.5 * marketValue) / 100;
					if (stampduty1 >= stampduty) {
						regFee = (0.8 * marketValue) / 100;
					} else {
						regFee = (0.8 * marketValueBuilderShare) / 100;
					}
				} else {
					regFee = (0.8 * marketValue) / 100;
				}
			}
			if (instDTO.getInstId() == 2091) {
				/*	double stampduty1;
					double stampDuty2;
					stampduty1=(marketValue);
					double stampAmount=0;
					if(instDTO.getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase(""))
					{
						stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount());
					}
					
					stampDuty2=(considerationAmount+stampAmount);
					if(stampduty1>stampDuty2)
					{
						regFee=(stampduty1*0.8)/100;
					}
					else
					{
						regFee=(stampDuty2*0.8)/100;
					}
					*/
				//added by ankit as per Dept. req
				if (propDTO.getTotal() != null && propDTO.getTotal() >= 0) {
					regFee = (propDTO.getTotal()) * 0.8 / 100;
				}
			} else if (instDTO.getInstId() == 2210) {
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				regFee = ((considerationAmount) + (5 * stampAmount)) * 0.8 / 100;
			} else if (instDTO.getInstId() == 2211) {
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				regFee = ((considerationAmount) + (8 * stampAmount)) * 0.8 / 100;
			} else if (instDTO.getInstId() == 2221) {
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				regFee = ((considerationAmount) + (5 * stampAmount)) * 0.8 / 100;
			} else if (instDTO.getInstId() == 2222) {
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				regFee = ((considerationAmount) + (8 * stampAmount)) * 0.8 / 100;
			}
		}
		return String.valueOf(Math.ceil(regFee));
	}

	public String calculateRegFeeConsenter(int instId, int deedId, String consenterAmount, String stampDuty, String cancellationFlag) {
		ArrayList stampDetails = null;
		/*if(instDTO.getInstId()==2105||instDTO.getInstId()==2106||instDTO.getInstId()==2108||instDTO.getInstId()==2184)
		{
			double partitionMV=0;
			double maxMv=0;
			ArrayList partitionList=dutyDTO.getPropertyDetailsList();
			for(int i=0;i<partitionList.size();i++)
			{
				DutyCalculationDTO pdto=(DutyCalculationDTO) partitionList.get(i);
				partitionMV=partitionMV+Double.parseDouble(pdto.getMarketValue());
				if(Double.parseDouble(pdto.getMarketValue())>=maxMv)
				{
					maxMv=Double.parseDouble(pdto.getMarketValue());
				}
				
			}
			dutyDTO.setPartitionMV(partitionMV);
			dutyDTO.setMaxPartitionMv(maxMv);
		}*/
		if (instId == 2087) {
			stampDetails = dao.getRegFeeDetails(String.valueOf(1053), String.valueOf(2090)); // FEE ACCORDING TO CONEYANCE AGRREMENT WITH POSSESION
		}
		/*else if(instDTO.getInstId()==2220||instDTO.getInstId()==2219)
		{
		stampDetails=dao.getRegFeeDetails(String.valueOf(dutyDTO.getDeedId()),String.valueOf(instDTO.getLeaseId()));
		}*/
		else {
			stampDetails = dao.getRegFeeDetails(String.valueOf(deedId), String.valueOf(instId));
		}
		double regFee = 0;
		int operationId;
		double operationValue;
		double maxValue;
		double minValue;
		double multiply = 0;
		double marketValue = 0;
		/*	if(dutyDTO.getDeedId()==1054)
			{
				if(dutyDTO.getExchangeMV1()>dutyDTO.getExchangeMV2())
				{
					marketValue=dutyDTO.getExchangeMV1();
				}
				else
				{
					marketValue=dutyDTO.getExchangeMV2();
				}
			}
			else if(instDTO.getInstId()==2105||instDTO.getInstId()==2106||instDTO.getInstId()==2108||instDTO.getInstId()==2184)
			{
				marketValue=dutyDTO.getPartitionMV()-dutyDTO.getMaxPartitionMv();
			}
			else */if (instId == 2107) {
			marketValue = (Double.parseDouble(consenterAmount)) * 100;
		} else {
			if (consenterAmount != null && !consenterAmount.equalsIgnoreCase("")) {
				marketValue = Double.parseDouble(consenterAmount);
				multiply = Double.parseDouble(consenterAmount);
			}
		}
		/*double considerationAmount=0;
		if(instDTO.getAsConsiderationAmount()!=null&&!instDTO.getAsConsiderationAmount().equalsIgnoreCase(""))
		{	
			considerationAmount=Double.parseDouble(instDTO.getAsConsiderationAmount());
		}*/
		/*if(instDTO.getInstId()==2215||instDTO.getInstId()==2218||instDTO.getInstId()==2217||instDTO.getInstId()==2216||instDTO.getInstId()==2220||instDTO.getInstId()==2219||instDTO.getInstId()==2223)
		{
			double stampAmount=0;
			if(instDTO.getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase(""))
			{
				stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount());
			}
			considerationAmount=considerationAmount+stampAmount;
		}
		if("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag())&&(dutyDTO.getDeedId()==1053||dutyDTO.getDeedId()==1092))
		{
			marketValue=Double.parseDouble(dutyDTO.getGovValue());
			considerationAmount=0;
		}*/
		if (stampDetails != null && stampDetails.size() > 0) {
			ArrayList subList = (ArrayList) stampDetails.get(0);
			if (subList.get(1) == null || subList.get(1).toString().equalsIgnoreCase("")) {
				operationId = 0;
			} else {
				operationId = Integer.parseInt(subList.get(1).toString());
				if ("Y".equalsIgnoreCase(cancellationFlag)) {
					if (operationId == 1) {
						return dao.getCancelaationRegFee(deedId, instId);
					}
				}
			}
			if (subList.get(2) == null || subList.get(2).toString().equalsIgnoreCase("")) {
				operationValue = 0;
			} else {
				operationValue = Double.parseDouble(subList.get(2).toString());
			}
			if (subList.get(3) == null || subList.get(3).toString().equalsIgnoreCase("")) {
				maxValue = 0;
			} else {
				maxValue = Double.parseDouble(subList.get(3).toString());
			}
			if (subList.get(4) == null || subList.get(4).toString().equalsIgnoreCase("")) {
				minValue = 0;
			} else {
				minValue = Double.parseDouble(subList.get(4).toString());
			}
			switch (operationId) {
			case 1:
				regFee = operationValue;
				break;
			case 2:
				break;
			case 3:
				/*if(instDTO.getInStampDutyAmount()!=null && !instDTO.getInStampDutyAmount().equalsIgnoreCase(""))
				{
				multiply=Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				else if(instDTO.getCommonUserField()!=null && !instDTO.getCommonUserField().equalsIgnoreCase(""))
				{
				multiply=Double.parseDouble(instDTO.getCommonUserField());
				}
				else if(instDTO.getAsConsiderationAmount()!=null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase(""))
				{
				multiply=Double.parseDouble(instDTO.getAsConsiderationAmount());
				}*/
				regFee = (multiply * operationValue / 100);
				/*if("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag())&&(dutyDTO.getDeedId()==1053||dutyDTO.getDeedId()==1092))
				{
					regFee=(Double.parseDouble(dutyDTO.getGovValue())*operationValue)/100;
				}*/
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				regFee = Double.parseDouble(stampDuty) * operationValue;
				break;
			case 10: /*if(instDTO.getCommonUserField()!=null && !instDTO.getCommonUserField().equalsIgnoreCase(""))
																																{
																																considerationAmount=Double.parseDouble(instDTO.getCommonUserField());
																																}*/
				//	if(marketValue>considerationAmount)
			{
				regFee = (marketValue * 0.8) / 100;
			}
				/*else
				{
					regFee=(considerationAmount*0.8)/100;
				}*/
				break;
			case 11: /*if(instDTO.getInStampDutyAmount()!=null && !instDTO.getInStampDutyAmount().equalsIgnoreCase(""))
																																	{
																																	multiply=Double.parseDouble(instDTO.getInStampDutyAmount());
																																	}
																																	else if(instDTO.getCommonUserField()!=null && !instDTO.getCommonUserField().equalsIgnoreCase(""))
																																	{
																																	multiply=Double.parseDouble(instDTO.getCommonUserField());
																																	}
																																	else if(instDTO.getAsConsiderationAmount()!=null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase(""))
																																	{
																																	multiply=Double.parseDouble(instDTO.getAsConsiderationAmount());
																																	}*/
				regFee = Math.ceil((multiply * 0.8 / 100));
				/*if("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag())&&(dutyDTO.getDeedId()==1053||dutyDTO.getDeedId()==1092))
				{
					regFee=Math.ceil((Double.parseDouble(dutyDTO.getGovValue())*0.8/100));
				}*/
				break;
			case 12: //added by ankit as per Dept. req 
				regFee = Math.ceil((multiply * 0.8 / 100));
				break;
			case 13:
				//added by ankit as per Dept. req // for consenter 0.8 - not DB driven 
				regFee = (marketValue * 0.8) / 100;
				break;
			case 15:
				regFee = Math.ceil((multiply * 0.8 / 100));
				break;
			}
			if (minValue > 0 && regFee < minValue) {
				regFee = minValue;
			}
			if (maxValue > 0 && regFee > maxValue) {
				regFee = maxValue;
			}
		} else {/*
																													 if(instDTO.getInstId()==2091)
																														{
																													double stampduty1;
																													double stampDuty2;
																													stampduty1=(marketValue);
																													double stampAmount=0;
																													if(instDTO.getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase(""))
																													{
																														stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount());
																													}
																													
																													stampDuty2=(considerationAmount+stampAmount);
																													if(stampduty1>stampDuty2)
																													{
																														regFee=(stampduty1*0.8)/100;
																													}
																													else
																													{
																														regFee=(stampDuty2*0.8)/100;
																													}
																												}
																													 else if(instDTO.getInstId()==2210)
																														{
																															double stampAmount=0;
																															if(instDTO.getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase(""))
																															{
																																stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount());
																															}
																															regFee=((considerationAmount)+(5*stampAmount))*0.8/100;
																														}
																													 else if(instDTO.getInstId()==2211)
																														{
																															double stampAmount=0;
																															if(instDTO.getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase(""))
																															{
																																stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount());
																															}
																															regFee=((considerationAmount)+(8*stampAmount))*0.8/100;
																														}
																														else if(instDTO.getInstId()==2221)
																														{
																															double stampAmount=0;
																															if(instDTO.getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase(""))
																															{
																																stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount());
																															}
																															regFee=((considerationAmount)+(5*stampAmount))*0.8/100;
																														}
																														else if(instDTO.getInstId()==2222)
																														{
																															double stampAmount=0;
																															if(instDTO.getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase(""))
																															{
																																stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount());
																															}
																															regFee=((considerationAmount)+(8*stampAmount))*0.8/100;
																														}
																												*/}
		return String.valueOf(Math.ceil(regFee));
	}
}
