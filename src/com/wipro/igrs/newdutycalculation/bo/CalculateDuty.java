package com.wipro.igrs.newdutycalculation.bo;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.newdutycalculation.dao.DutyCalculationDAO;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.form.RegCompletionForm;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;

public class CalculateDuty {
	private static Logger logger = org.apache.log4j.Logger.getLogger(CalculateDuty.class);
	DutyCalculationDAO dao = null;

	public CalculateDuty() {
		dao = new DutyCalculationDAO();
	}

	@SuppressWarnings("null")
	public String calculateStampDuty(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, DutyCalculationDTO propDTO) {
		String govMarketValue = dutyDTO.getGovValue();
		ArrayList stampDetails = null;
		if (instDTO.getInstId() == 2087 || instDTO.getInstId() == 2228 || instDTO.getInstId() == 2229 || instDTO.getInstId() == 2230) {
			stampDetails = dao.getStampDutyDetails(String.valueOf(1053), String.valueOf(2090)); // same
			// as
			// conveyance
		} else if (instDTO.getInstId() == 2219 || instDTO.getInstId() == 2220) {
			stampDetails = dao.getStampDutyDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getLeaseId()));
		} else {
			stampDetails = dao.getStampDutyDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getInstId()));
		}
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
		double stampduty = 0;
		double finalStampDuty = 0;
		int operationId;
		double operationValue;
		double unitFractionValue;
		double maxValue;
		double minValue;
		double multiply = 0;
		double marketValue = 0;
		double marketValueBuilderShare = 0;
		double additionalDuty = 0;
		// added for developer agreement work : start
		// double securedAmount = 0;
		// double workContractAmount = 0;
		// double finalAmountOfWorkContract = 0;
		// added by akansha for work contract: end
		if (dutyDTO.getDeedId() == 1054) {
			if (dutyDTO.getExchangeMV1() > dutyDTO.getExchangeMV2()) {
				marketValue = dutyDTO.getExchangeMV1();
			} else {
				marketValue = dutyDTO.getExchangeMV2();
			}
		} else if (instDTO.getInstId() == 2105 || instDTO.getInstId() == 2106 || instDTO.getInstId() == 2108 || instDTO.getInstId() == 2184) {
			marketValue = dutyDTO.getPartitionMV() - dutyDTO.getMaxPartitionMv();
		} else {
			if (propDTO.getMarketValue() != null && !propDTO.getMarketValue().equalsIgnoreCase("")) {
				marketValue = Double.parseDouble(propDTO.getMarketValue());
			}
			// added for developer agreement
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
			if (dutyDTO.getDeedId() == 1092) {
			} else {
				considerationAmount = 0;
			}
		}
		if (stampDetails != null && stampDetails.size() > 0) {
			ArrayList subList = (ArrayList) stampDetails.get(0);
			if (subList.get(1) == null || subList.get(1).toString().equalsIgnoreCase("")) {
				operationId = 0;
			} else {
				operationId = Integer.parseInt(subList.get(1).toString());
				if ("Y".equalsIgnoreCase(dutyDTO.getCancellationFlag())) {
					if (operationId == 1) {
						return dao.getCancelaationStamp(dutyDTO.getDeedId(), instDTO.getInstId());
					}
				}
			}
			if (subList.get(2) == null || subList.get(2).toString().equalsIgnoreCase("")) {
				operationValue = 0;
			} else {
				operationValue = Double.parseDouble(subList.get(2).toString());
			}
			if (subList.get(3) == null || subList.get(3).toString().equalsIgnoreCase("")) {
				unitFractionValue = 0;
			} else {
				unitFractionValue = Double.parseDouble(subList.get(3).toString());
			}
			if (subList.get(4) == null || subList.get(4).toString().equalsIgnoreCase("")) {
				maxValue = 0;
			} else {
				maxValue = Double.parseDouble(subList.get(4).toString());
			}
			if (subList.get(5) == null || subList.get(5).toString().equalsIgnoreCase("")) {
				minValue = 0;
			} else {
				minValue = Double.parseDouble(subList.get(5).toString());
			}
			switch (operationId) {
				case 1 :
					stampduty = operationValue;
					break;
				case 2 :
					// changes done by akansha and rahul
					if (govMarketValue != null && !govMarketValue.isEmpty() && !govMarketValue.equals("")) {
						stampduty = (marketValue * operationValue) / 100;
					} else {
						if (marketValue > considerationAmount) {
							stampduty = (marketValue * operationValue) / 100;
						} else {
							stampduty = (considerationAmount * operationValue) / 100;
						}
					}
					break;
				case 3 :
					if (instDTO.getInstId() == 2254 || instDTO.getInstId() == 2261 || instDTO.getInstId() == 2262) {
						if (govMarketValue != null && !govMarketValue.isEmpty() && !govMarketValue.equals("")) {
							if ("major".equalsIgnoreCase(dutyDTO.getMineralSelected())) {
								stampduty = (marketValue * 2) / 100;
							}
							if ("minor".equalsIgnoreCase(dutyDTO.getMineralSelected())) {
								stampduty = (marketValue * 1.25) / 100;
							}
							// } else {
							// stampduty = (marketValue * operationValue) / 100;
							// }
						} else if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("") && instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
							multiply = Double.parseDouble(instDTO.getInStampDutyAmount()) + Double.parseDouble(instDTO.getAsConsiderationAmount());
							// change by ankit for major/minor mineral - Mining
							// Lease
							// if (instDTO.getInstId() == 2254) {
							if ("major".equalsIgnoreCase(dutyDTO.getMineralSelected())) {
								stampduty = (multiply * 2) / 100;
							}
							if ("minor".equalsIgnoreCase(dutyDTO.getMineralSelected())) {
								stampduty = (multiply * 1.25) / 100;
							}
							// } else {
							// stampduty = (multiply * operationValue) / 100;
							// }
						}
					} /*
					 * else if (instDTO.getInstId() == 2256) {// changed by
					 * akansha for work contract if
					 * (instDTO.getInStampDutyAmount() != null &&
					 * !instDTO.getInStampDutyAmount().equalsIgnoreCase("") &&
					 * instDTO.getAsConsiderationAmount() != null &&
					 * !instDTO.getAsConsiderationAmount().equalsIgnoreCase(""))
					 * { securedAmount =
					 * Double.valueOf(instDTO.getAsConsiderationAmount());
					 * workContractAmount =
					 * Double.valueOf(instDTO.getInStampDutyAmount()); if
					 * (securedAmount > workContractAmount) { multiply =
					 * Double.parseDouble(instDTO.getAsConsiderationAmount()); }
					 * else if (workContractAmount > securedAmount) { multiply =
					 * Double.parseDouble(instDTO.getInStampDutyAmount()); }
					 * else { multiply =
					 * Double.parseDouble(instDTO.getAsConsiderationAmount()); }
					 * } finalAmountOfWorkContract = multiply; stampduty =
					 * (multiply operationValue) / 100; }
					 */else {
						if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
							multiply = Double.parseDouble(instDTO.getCommonUserField());
						} else if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
							multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
						} else if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
							multiply = Double.parseDouble(instDTO.getAsConsiderationAmount());
						}
						if (instDTO.getInstId() == 2256) {// added by ankit for
							// work contract
							if (multiply <= 5000000) {
								stampduty = minValue;
							} else {
								stampduty = (multiply * operationValue) / 100;
							}
						} else {
							stampduty = (multiply * operationValue) / 100;
						}
						if ("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag()) && (dutyDTO.getDeedId() == 1053 || dutyDTO.getDeedId() == 1092)) {
							stampduty = (Double.parseDouble(dutyDTO.getGovValue()) * operationValue) / 100;
						}
					}
					break;
				case 4 :
					if (marketValue > considerationAmount) {
						stampduty = (Math.ceil((marketValue / unitFractionValue)) * operationValue);
					} else {
						stampduty = (Math.ceil((considerationAmount / unitFractionValue)) * operationValue);
					}
					break;
				case 5 :
					if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getCommonUserField());
					} else if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
					} else {
						multiply = Double.parseDouble(instDTO.getAsConsiderationAmount());
					}
					if (instDTO.getInstId() == 2007 || instDTO.getInstId() == 2030 || instDTO.getInstId() == 2060) {
						// changes done by akansha and rahul;
						if ("D".equalsIgnoreCase(dutyDTO.getTypeOfTransaction())) {
							operationValue = 1;
							unitFractionValue = 10000;
						} else {
							operationValue = 2;
							unitFractionValue = 100000;
						}
					}
					stampduty = (Math.ceil((multiply / unitFractionValue)) * operationValue);
					break;
				case 6 :
					if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
					} else if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getCommonUserField());
					}
					stampduty = multiply * operationValue;
					break;
				case 7 :
					break;
				case 8 :
					break;
				case 9 :
					break;
				case 10 :
					break;
				case 11 :
					break;
				case 12 :
					break;
				case 13 :
					break;
				case 14 :
					// added by ankit - additional duty 1%
					if (govMarketValue != null && !govMarketValue.isEmpty() && !govMarketValue.equals("")) {
						stampduty = (marketValue * operationValue) / 100;
					} else {
						if (marketValue < considerationAmount) {
							stampduty = (marketValue * operationValue) / 100;
							additionalDuty = ((considerationAmount - marketValue) * 1) / 100;
							stampduty = stampduty + additionalDuty;
						} else {
							stampduty = (marketValue * operationValue) / 100;
						}
					}
					break;
				case 16 :
					// added by ankit for transfer of mining lease
					if (govMarketValue != null && !govMarketValue.isEmpty() && !govMarketValue.equals("")) {
						stampduty = (marketValue * operationValue) / 100;
					} else if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("") && instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getInStampDutyAmount()) + Double.parseDouble(instDTO.getAsConsiderationAmount());
						stampduty = (multiply * operationValue) / 100;
					}
					break;
			}
			/*
			 * if (instDTO.getInstId() == 2256) {// changed by akansha for work
			 * contract if (finalAmountOfWorkContract <= 5000000) { stampduty =
			 * minValue; } else { if (maxValue > 0 && stampduty > maxValue) {
			 * stampduty = maxValue; } } }
			 */// else {
			if (minValue > 0 && stampduty < minValue) {
				stampduty = minValue;
			}
			if (maxValue > 0 && stampduty > maxValue) {
				stampduty = maxValue;
			}
			// }
		} else {
			if (instDTO.getInstId() == 2179) {
				if ("Y".equalsIgnoreCase(dutyDTO.getHvShare())) {
					logger.info("marketValueBuilderShare : " + marketValueBuilderShare);
					if (dutyDTO.getPropDTO() != null) {
						logger.info("building check----->" + dutyDTO.getPropDTO().getPropertyName());
						if (dutyDTO.getPropDTO().getPropertyName().equals("2")) {
							marketValueBuilderShare = marketValue * (Double.parseDouble(instDTO.getInStampDutyAmount())) / 100;
						}
					}
					// double stampAmount=(Double.parseDouble(instDTO.
					// getInStampDutyAmount()))/100;
					stampduty = (5 * marketValueBuilderShare) / 100;
					double stampduty1 = (2.5 * marketValue) / 100;
					if (stampduty1 > stampduty) {
						stampduty = stampduty1;
					}
				} else {
					stampduty = (5 * marketValue) / 100;
				}
			}
			if (instDTO.getInstId() == 2107) {
				if ("Y".equalsIgnoreCase(dutyDTO.getFamilyCheckFlag())) {// added
					// by
					// saurav
					stampduty = (((dutyDTO.getTotalLandRevenue() - dutyDTO.getMaxLandRevenue()) * 100) * 1) / 100; // 0.5
					// %
					// will
					// be
					// done
					// in
					// another
					// chunk
					// where
					// all
					// the
					// within
					// the
					// family
					// type
					// has
					// been
					// done
				} else {
					stampduty = (((dutyDTO.getTotalLandRevenue() - dutyDTO.getMaxLandRevenue()) * 100) * 5) / 100;
				}
			} else if (instDTO.getInstId() == 2091)// Amalgamation Instrument
			{
				double stampduty1;
				double stampDuty2;
				stampduty1 = (marketValue * 5) / 100;
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				stampDuty2 = ((considerationAmount + stampAmount) * 0.5) / 100;
				if (stampduty1 > stampDuty2) {
					stampduty = stampduty1;
				} else {
					stampduty = stampDuty2;
				}
				if (stampduty > 250000000)// Added by Roopam-20 May 2015
				{
					stampduty = 250000000;
				}
			} else if (instDTO.getInstId() == 2210) {
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				stampduty = ((considerationAmount) + (5 * stampAmount)) * 4 / 100;
			} else if (instDTO.getInstId() == 2211) {
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				stampduty = ((considerationAmount) + (8 * stampAmount)) * 5 / 100;
			} else if (instDTO.getInstId() == 2221) {
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				stampduty = ((considerationAmount) + (5 * stampAmount)) * 4 / 100;
			} else if (instDTO.getInstId() == 2222) {
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				stampduty = ((considerationAmount) + (8 * stampAmount)) * 5 / 100;
			}
		}
		if ("Y".equalsIgnoreCase(dutyDTO.getFamilyCheckFlag())) {
			// return String.valueOf(Math.ceil(stampduty/2));
			// return String.valueOf(Math.ceil(stampduty/2));
			// New duty changed to 0.5% of MV -Rahul
			if (instDTO.getInstId() == 2112)// release deed
			{
				return String.valueOf(Math.ceil(stampduty / 10));
			} else if (instDTO.getInstId() == 2107) { // added by saurav
				return String.valueOf(Math.ceil(stampduty / 2)); // 0.5% for
				// partition
				// of
				// agricultural
				// land
				// within
				// family
				// member
			}
			// for other as prev
			else {
				return String.valueOf(Math.ceil(stampduty / 2));
			}
		} else {
			return String.valueOf(Math.ceil(stampduty));
		}
	}

	public String calculateStampDutyCoveyance1Percent(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, DutyCalculationDTO propDTO) {
		ArrayList stampDetails = null;
		stampDetails = dao.getStampDutyDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getInstId()));
		double stampduty = 0;
		double finalStampDuty = 0;
		int operationId;
		double operationValue;
		double unitFractionValue;
		double maxValueInst;
		long maxValueExemption = 250000000;
		double minValueInst;
		double multiply = 0;
		double marketValue = 0;
		if (propDTO.getMarketValue() != null && !propDTO.getMarketValue().equalsIgnoreCase("")) {
			marketValue = Double.parseDouble(propDTO.getMarketValue());
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
			if (dutyDTO.getDeedId() == 1092) {
			} else {
				considerationAmount = 0;
			}
		}
		if (stampDetails != null && stampDetails.size() > 0) {
			ArrayList subList = (ArrayList) stampDetails.get(0);
			/*
			 * if(subList.get(1)==null||subList.get(1).toString().equalsIgnoreCase
			 * ("")) { operationId=0; } else {
			 * operationId=Integer.parseInt(subList.get(1).toString());
			 * if("Y".equalsIgnoreCase(dutyDTO.getCancellationFlag())) {
			 * if(operationId==1) { return
			 * dao.getCancelaationStamp(dutyDTO.getDeedId
			 * (),instDTO.getInstId()); } } }
			 */
			operationId = 2;
			/*
			 * if(subList.get(2)==null||subList.get(2).toString().equalsIgnoreCase
			 * ("")) { operationValue=0; } else {
			 * operationValue=Double.parseDouble(subList.get(2).toString()); }
			 */
			operationValue = 1;
			if (subList.get(3) == null || subList.get(3).toString().equalsIgnoreCase("")) {
				unitFractionValue = 0;
			} else {
				unitFractionValue = Double.parseDouble(subList.get(3).toString());
			}
			if (subList.get(4) == null || subList.get(4).toString().equalsIgnoreCase("")) {
				maxValueInst = 0;
			} else {
				maxValueInst = Double.parseDouble(subList.get(4).toString());
			}
			if (subList.get(5) == null || subList.get(5).toString().equalsIgnoreCase("")) {
				minValueInst = 0;
			} else {
				minValueInst = Double.parseDouble(subList.get(5).toString());
			}
			switch (operationId) {
				case 1 :
					stampduty = operationValue;
					break;
				case 2 :
					if (marketValue > considerationAmount) {
						stampduty = (marketValue * operationValue) / 100;
					} else {
						stampduty = (considerationAmount * operationValue) / 100;
					}
					break;
				case 3 :
					if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getCommonUserField());
					} else if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
					} else if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getAsConsiderationAmount());
					}
					stampduty = (multiply * operationValue) / 100;
					if ("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag()) && (dutyDTO.getDeedId() == 1053 || dutyDTO.getDeedId() == 1092)) {
						stampduty = (Double.parseDouble(dutyDTO.getGovValue()) * operationValue) / 100;
					}
					break;
				case 4 :
					if (marketValue > considerationAmount) {
						stampduty = (Math.ceil((marketValue / unitFractionValue)) * operationValue);
					} else {
						stampduty = (Math.ceil((considerationAmount / unitFractionValue)) * operationValue);
					}
					break;
				case 5 :
					if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getCommonUserField());
					} else if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
					} else {
						multiply = Double.parseDouble(instDTO.getAsConsiderationAmount());
					}
					if (instDTO.getInstId() == 2007 || instDTO.getInstId() == 2030 || instDTO.getInstId() == 2060) {
						if ("D".equalsIgnoreCase(dutyDTO.getTypeOfTransaction())) {
							operationValue = 2;
							unitFractionValue = 100000;
						} else {
							operationValue = 1;
							unitFractionValue = 10000;
						}
					}
					stampduty = (Math.ceil((multiply / unitFractionValue)) * operationValue);
					break;
				case 6 :
					if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
					} else if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getCommonUserField());
					}
					stampduty = multiply * operationValue;
					break;
				case 7 :
					break;
				case 8 :
					break;
				case 9 :
					break;
				case 10 :
			}
			if (minValueInst > 0 && stampduty < minValueInst) {
				stampduty = minValueInst;
			}
			if (maxValueExemption > maxValueInst) {
				if (maxValueInst > 0 && stampduty > maxValueInst) {
					stampduty = maxValueInst;
				}
			} else if (stampduty > maxValueExemption) {
				stampduty = maxValueExemption;
			}
		}
		if ("Y".equalsIgnoreCase(dutyDTO.getFamilyCheckFlag())) {
			return String.valueOf(Math.ceil(stampduty / 2));
		} else {
			return String.valueOf(Math.ceil(stampduty));
		}
	}

	public double calculateUpkar(int instId, double stampDuty) {
		String UpkarFlag = new DutyCalculationBO().getUpkarFlag(instId);
		double upkar = 0;
		double upkarValue = Double.parseDouble(new DutyCalculationBO().getUpkarValue());
		if ("Y".equalsIgnoreCase(UpkarFlag)) {
			upkar = ((upkarValue * stampDuty) / 100);
		}
		return Math.ceil(upkar);
	}

	public double calculateMuncipalDuty(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, DutyCalculationDTO propDTO) {
		double municipalDuty = 0;
		{
			boolean applicableFlag = false;
			String flag = "";
			String subAreaId = "";
			String municipalId = "";
			if (propDTO.getValuationId() == null || propDTO.getValuationId().equalsIgnoreCase("")) {
				if ("Y".equalsIgnoreCase(dutyDTO.getMunicipalCheck())) {
					applicableFlag = true;
				}
			} else {
				ArrayList municpal = dao.getMunicipalId(propDTO.getValuationId());
				if (municpal != null && municpal.size() > 0) {
					ArrayList subList = (ArrayList) municpal.get(0);
					if (subList.get(0) == null || subList.get(0).toString().equalsIgnoreCase("")) {
						subAreaId = "";
					} else {
						subAreaId = subList.get(0).toString();
					}
					if (subList.get(1) == null || subList.get(1).toString().equalsIgnoreCase("")) {
						flag = "";
					} else {
						flag = subList.get(1).toString();
					}
					if (subList.get(2) == null || subList.get(2).toString().equalsIgnoreCase("")) {
						municipalId = "";
					} else {
						municipalId = subList.get(2).toString();
					}
					if ("5".equalsIgnoreCase(subAreaId) && "Y".equalsIgnoreCase(flag)) {
						applicableFlag = true;
					} else if (("1".equalsIgnoreCase(municipalId) || "5".equalsIgnoreCase(municipalId) || "2".equalsIgnoreCase(municipalId) || ("3".equalsIgnoreCase(municipalId)) || "4".equalsIgnoreCase(municipalId)) && "Y".equalsIgnoreCase(flag)) {
						applicableFlag = true;
					} else {
						applicableFlag = false;
					}
				}
			}
			if (applicableFlag) {
				ArrayList subList = dao.janpadMunicipalDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getInstId()));
				if (subList != null && subList.size() > 0) {
					ArrayList janpadDetails = (ArrayList) subList.get(0);
					int operationId = 0;
					double municipalValue = 0;
					if (janpadDetails.get(0) == null || janpadDetails.get(0).toString().equalsIgnoreCase("")) {
						operationId = 0;
					} else {
						operationId = Integer.parseInt(janpadDetails.get(0).toString());
					}
					if ("5".equalsIgnoreCase(subAreaId)) {
						if (janpadDetails.get(4) == null || janpadDetails.get(4).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(4).toString());
						}
					} else if ("1".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(1) == null || janpadDetails.get(1).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(1).toString());
						}
					} else if ("2".equalsIgnoreCase(municipalId) || "5".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(2) == null || janpadDetails.get(2).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(2).toString());
						}
					} else if ("4".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(3) == null || janpadDetails.get(3).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(3).toString());
						}

					} else if ("3".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(3) == null || janpadDetails.get(3).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(3).toString());
						}
					} else {
						// changes for municipal value where valuation id not
						// required and nagar parishad .
						// added by ankit as per requirement
						if (instDTO.getInstId() == 2095 || instDTO.getInstId() == 2275 || instDTO.getInstId() == 2277) {
							if ("3".equalsIgnoreCase(municipalId)) {
								municipalValue = 0;
							}
						} else {
							// changes made by saurav for municipal duty
							// to be db configurable if municipal is checked
							// or only property is required not valuation if
							// applicable

							if (janpadDetails.get(1) == null || janpadDetails.get(1).toString().equalsIgnoreCase("")) {
								municipalValue = 0;
							} else {
								municipalValue = Double.parseDouble(janpadDetails.get(1).toString());
							}

						}
					}
					double marketValue = 0;
					if (propDTO.getMarketValue() != null && !propDTO.getMarketValue().equalsIgnoreCase("")) {
						marketValue = Double.parseDouble(propDTO.getMarketValue());
					}
					double considerationAmount = 0;
					if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
						considerationAmount = Double.parseDouble(instDTO.getAsConsiderationAmount());
					}
					if (instDTO.getInstId() == 2091) {
						double stampAmount = 0;
						if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
							stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
						}
						double value1 = considerationAmount + stampAmount;
						if (value1 > marketValue) {
							marketValue = value1;
						}
					}
					double multiply = 0;
					if ("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag()) && dutyDTO.getDeedId() == 1053) {
						marketValue = Double.parseDouble(dutyDTO.getGovValue());
						considerationAmount = 0;
					}
					switch (operationId) {
						case 2 :
							if (marketValue > considerationAmount) {
								municipalDuty = (marketValue * municipalValue) / 100;
							} else {
								municipalDuty = (considerationAmount * municipalValue) / 100;
							}
							break;
						case 3 :
							if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
								multiply = Double.parseDouble(instDTO.getCommonUserField());
							} else if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
								multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
							} else if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
								multiply = Double.parseDouble(instDTO.getAsConsiderationAmount());
							}
							municipalDuty = (multiply * municipalValue) / 100;
							if ("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag()) && dutyDTO.getDeedId() == 1053) {
								municipalDuty = (Double.parseDouble(dutyDTO.getGovValue()) * municipalValue) / 100;
							}
							break;
						case 14 :// added by ankit - additional duty 1%
							if (marketValue > considerationAmount) {
								municipalDuty = (marketValue * municipalValue) / 100;
							} else {
								municipalDuty = (considerationAmount * municipalValue) / 100;
							}
							break;
						case 16 :// added by ankit - transfer of mining lease
							if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("") && instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
								multiply = Double.parseDouble(instDTO.getInStampDutyAmount()) + Double.parseDouble(instDTO.getAsConsiderationAmount());
								municipalDuty = (multiply * municipalValue) / 100;
							}
							break;
					}
				} else {
					if (instDTO.getInstId() == 2210) {
						double stampAmount = 0;
						double considerationAmount = 0;
						if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
							stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
						}
						if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
							considerationAmount = Double.parseDouble(instDTO.getAsConsiderationAmount());
						}
						municipalDuty = ((considerationAmount) + (5 * stampAmount)) * 1 / 100;
					} else if (instDTO.getInstId() == 2211) {
						double stampAmount = 0;
						double considerationAmount = 0;
						if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
							stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
						}
						if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
							considerationAmount = Double.parseDouble(instDTO.getAsConsiderationAmount());
						}
						municipalDuty = ((considerationAmount) + (8 * stampAmount)) * 1 / 100;
					}
				}
			}
		}
		return Math.ceil(municipalDuty);
	}

	public double calculateMuncipalDutyConsnter(int instId, int deedId, String consenterAmount) {
		double municipalDuty = 0;
		{
			boolean applicableFlag = true;
			String flag = "";
			String subAreaId = "";
			String municipalId = "1";
			/*
			 * if(propDTO.getValuationId()==null||propDTO.getValuationId().equalsIgnoreCase
			 * ("")) { if("Y".equalsIgnoreCase(dutyDTO.getMunicipalCheck())) {
			 * applicableFlag=true; } } else
			 */
			{/*
			 * ArrayList municpal=dao.getMunicipalId(propDTO.getValuationId());
			 * if(municpal!=null && municpal.size()>0) { ArrayList
			 * subList=(ArrayList) municpal.get(0);
			 * if(subList.get(0)==null||subList
			 * .get(0).toString().equalsIgnoreCase("")) { subAreaId=""; } else {
			 * subAreaId=subList.get(0).toString(); }
			 * if(subList.get(1)==null||subList
			 * .get(1).toString().equalsIgnoreCase("")) { flag=""; } else {
			 * flag=subList.get(1).toString(); }
			 * if(subList.get(2)==null||subList
			 * .get(2).toString().equalsIgnoreCase("")) { municipalId=""; } else
			 * { municipalId=subList.get(2).toString(); }
			 * if("5".equalsIgnoreCase(subAreaId)&&"Y".equalsIgnoreCase(flag)) {
			 * applicableFlag=true; } else
			 * if(("1".equalsIgnoreCase(municipalId)||
			 * "5".equalsIgnoreCase(municipalId
			 * )||"2".equalsIgnoreCase(municipalId
			 * )||"4".equalsIgnoreCase(municipalId
			 * ))&&"Y".equalsIgnoreCase(flag)) { applicableFlag=true; } else {
			 * applicableFlag=false; } }
			 */
			}
			if (applicableFlag) {
				ArrayList subList = dao.janpadMunicipalDetails(String.valueOf(deedId), String.valueOf(instId));
				if (subList != null && subList.size() > 0) {
					ArrayList janpadDetails = (ArrayList) subList.get(0);
					int operationId = 0;
					double municipalValue = 0;
					if (janpadDetails.get(0) == null || janpadDetails.get(0).toString().equalsIgnoreCase("")) {
						operationId = 0;
					} else {
						operationId = Integer.parseInt(janpadDetails.get(0).toString());
					}
					if ("5".equalsIgnoreCase(subAreaId)) {
						if (janpadDetails.get(4) == null || janpadDetails.get(4).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(4).toString());
						}
					} else if ("1".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(1) == null || janpadDetails.get(1).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(1).toString());
						}
					} else if ("2".equalsIgnoreCase(municipalId) || "5".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(2) == null || janpadDetails.get(2).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(2).toString());
						}
					} else if ("4".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(3) == null || janpadDetails.get(3).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(3).toString());
						}
					} else {
						municipalValue = 1;
					}
					double marketValue = 0;
					if (consenterAmount != null && !consenterAmount.equalsIgnoreCase("")) {
						marketValue = Double.parseDouble(consenterAmount);
					}
					/*
					 * double considerationAmount=0;
					 * if(instDTO.getAsConsiderationAmount
					 * ()!=null&&!instDTO.getAsConsiderationAmount
					 * ().equalsIgnoreCase("")) {
					 * considerationAmount=Double.parseDouble
					 * (instDTO.getAsConsiderationAmount()); }
					 */
					/*
					 * if(instDTO.getInstId()==2091) { double stampAmount=0;
					 * if(instDTO
					 * .getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount
					 * ().equalsIgnoreCase("")) {
					 * stampAmount=Double.parseDouble(
					 * instDTO.getInStampDutyAmount()); }
					 * 
					 * double value1=considerationAmount+stampAmount;
					 * if(value1>marketValue) { marketValue=value1; } }
					 */
					double multiply = Double.parseDouble(consenterAmount);
					/*
					 * if("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag())&&dutyDTO
					 * .getDeedId()==1053) {
					 * marketValue=Double.parseDouble(dutyDTO.getGovValue());
					 * considerationAmount=0; }
					 */
					switch (operationId) {
						case 2 : {
							municipalDuty = (marketValue * municipalValue) / 100;
						}
							break;
						case 3 :
							municipalDuty = (multiply * municipalValue) / 100;
							break;
						case 14 :// added by ankit - additional duty 1%
							municipalDuty = (marketValue * municipalValue) / 100;
							break;
					}
				} else {/*
						 * if(instDTO.getInstId()==2210) { double stampAmount=0;
						 * double considerationAmount=0;
						 * if(instDTO.getInStampDutyAmount
						 * ()!=null&&!instDTO.getInStampDutyAmount
						 * ().equalsIgnoreCase("")) {
						 * stampAmount=Double.parseDouble
						 * (instDTO.getInStampDutyAmount()); }
						 * if(instDTO.getAsConsiderationAmount
						 * ()!=null&&!instDTO.
						 * getAsConsiderationAmount().equalsIgnoreCase("")) {
						 * considerationAmount
						 * =Double.parseDouble(instDTO.getAsConsiderationAmount
						 * ()); }
						 * municipalDuty=((considerationAmount)+(5stampAmount
						 * ))1/100; } else if(instDTO.getInstId()==2211) {
						 * double stampAmount=0; double considerationAmount=0;
						 * if(instDTO.getInStampDutyAmount()!=null&&!instDTO.
						 * getInStampDutyAmount().equalsIgnoreCase("")) {
						 * stampAmount
						 * =Double.parseDouble(instDTO.getInStampDutyAmount());
						 * }
						 * if(instDTO.getAsConsiderationAmount()!=null&&!instDTO
						 * .getAsConsiderationAmount().equalsIgnoreCase("")) {
						 * considerationAmount
						 * =Double.parseDouble(instDTO.getAsConsiderationAmount
						 * ()); }
						 * municipalDuty=((considerationAmount)+(8stampAmount
						 * ))1/100; }
						 */
				}
			}
		}
		return Math.ceil(municipalDuty);
	}

	public double calculateJanPadDutyConsenter(int instId, int deedId, String consenterAmount) {
		double janPadDuty = 0;
		ArrayList subList = dao.janpadMunicipalDetails(String.valueOf(deedId), String.valueOf(instId));
		if (subList != null && subList.size() > 0) {
			ArrayList janpadDetails = (ArrayList) subList.get(0);
			int operationId = 0;
			double janpadValue = 0;
			if (janpadDetails.get(0) == null || janpadDetails.get(0).toString().equalsIgnoreCase("")) {
				operationId = 0;
			} else {
				operationId = Integer.parseInt(janpadDetails.get(0).toString());
			}
			if (janpadDetails.get(5) == null || janpadDetails.get(5).toString().equalsIgnoreCase("")) {
				janpadValue = 0;
			} else {
				janpadValue = Double.parseDouble(janpadDetails.get(5).toString());
			}
			double marketValue = 0;
			if (consenterAmount != null && !consenterAmount.equalsIgnoreCase("")) {
				marketValue = Double.parseDouble(consenterAmount);
			}
			double considerationAmount = 0;
			/*
			 * if(instDTO.getAsConsiderationAmount()!=null&&!instDTO.getAsConsiderationAmount
			 * ().equalsIgnoreCase("")) {
			 * considerationAmount=Double.parseDouble(
			 * instDTO.getAsConsiderationAmount()); }
			 */
			/*
			 * if(instDTO.getInstId()==2091) {
			 * 
			 * double stampAmount=0;
			 * if(instDTO.getInStampDutyAmount()!=null&&!instDTO
			 * .getInStampDutyAmount().equalsIgnoreCase("")) {
			 * stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount()); }
			 * 
			 * double value1=considerationAmount+stampAmount;
			 * if(value1>marketValue) { marketValue=value1; } }
			 */
			/*
			 * if("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag())&&dutyDTO.getDeedId
			 * ()==1053) {
			 * marketValue=Double.parseDouble(dutyDTO.getGovValue());
			 * considerationAmount=0; }
			 */
			double multiply = 0;
			multiply = Double.parseDouble(consenterAmount);
			switch (operationId) {
				case 1 :
					if (instId == 2055) {
						janPadDuty = 500;
					}
					break;
				case 2 :
					janPadDuty = (marketValue * janpadValue) / 100;
					break;
				case 3 :
					janPadDuty = (multiply * janpadValue) / 100;
					break;
				case 14 :// added by ankit - additional duty 1%
					janPadDuty = (marketValue * janpadValue) / 100;
					break;
			}
		} else {/*
				 * if(instDTO.getInstId()==2210) { double stampAmount=0; double
				 * considerationAmount=0;
				 * if(instDTO.getInStampDutyAmount()!=null
				 * &&!instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
				 * stampAmount
				 * =Double.parseDouble(instDTO.getInStampDutyAmount()); }
				 * if(instDTO.getAsConsiderationAmount()!=null&&!instDTO.
				 * getAsConsiderationAmount().equalsIgnoreCase("")) {
				 * considerationAmount
				 * =Double.parseDouble(instDTO.getAsConsiderationAmount()); }
				 * janPadDuty=((considerationAmount)+(5stampAmount))1/100; }
				 * else if(instDTO.getInstId()==2211) { double stampAmount=0;
				 * double considerationAmount=0;
				 * if(instDTO.getInStampDutyAmount(
				 * )!=null&&!instDTO.getInStampDutyAmount
				 * ().equalsIgnoreCase("")) {
				 * stampAmount=Double.parseDouble(instDTO
				 * .getInStampDutyAmount()); }
				 * if(instDTO.getAsConsiderationAmount
				 * ()!=null&&!instDTO.getAsConsiderationAmount
				 * ().equalsIgnoreCase("")) {
				 * considerationAmount=Double.parseDouble
				 * (instDTO.getAsConsiderationAmount()); }
				 * janPadDuty=((considerationAmount)+(8stampAmount))1/100; }
				 */
		}
		return Math.ceil(janPadDuty);
	}

	public double calculateJanPadDuty(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, DutyCalculationDTO propDTO) {
		double janPadDuty = 0;
		ArrayList subList = dao.janpadMunicipalDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getInstId()));
		if (subList != null && subList.size() > 0) {
			ArrayList janpadDetails = (ArrayList) subList.get(0);
			int operationId = 0;
			double janpadValue = 0;
			if (janpadDetails.get(0) == null || janpadDetails.get(0).toString().equalsIgnoreCase("")) {
				operationId = 0;
			} else {
				operationId = Integer.parseInt(janpadDetails.get(0).toString());
			}
			if (janpadDetails.get(5) == null || janpadDetails.get(5).toString().equalsIgnoreCase("")) {
				janpadValue = 0;
			} else {
				janpadValue = Double.parseDouble(janpadDetails.get(5).toString());
			}
			double marketValue = 0;
			if (propDTO.getMarketValue() != null && !propDTO.getMarketValue().equalsIgnoreCase("")) {
				marketValue = Double.parseDouble(propDTO.getMarketValue());
			}
			double considerationAmount = 0;
			if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
				considerationAmount = Double.parseDouble(instDTO.getAsConsiderationAmount());
			}
			if (instDTO.getInstId() == 2091) {
				double stampAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				double value1 = considerationAmount + stampAmount;
				if (value1 > marketValue) {
					marketValue = value1;
				}
			}
			if ("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag()) && dutyDTO.getDeedId() == 1053) {
				marketValue = Double.parseDouble(dutyDTO.getGovValue());
				considerationAmount = 0;
			}
			double multiply = 0;
			switch (operationId) {
				case 1 :
					if (instDTO.getInstId() == 2055) {
						janPadDuty = 500;
					}
					break;
				case 2 :
					if (marketValue > considerationAmount) {
						janPadDuty = (marketValue * janpadValue) / 100;
					} else {
						janPadDuty = (considerationAmount * janpadValue) / 100;
					}
					break;
				case 3 :
					if (instDTO.getCommonUserField() != null && !instDTO.getCommonUserField().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getCommonUserField());
					} else if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getInStampDutyAmount());
					} else if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getAsConsiderationAmount());
					}
					janPadDuty = (multiply * janpadValue) / 100;
					if ("Y".equalsIgnoreCase(dutyDTO.getGovCheckFlag()) && dutyDTO.getDeedId() == 1053) {
						janPadDuty = (Double.parseDouble(dutyDTO.getGovValue()) * janpadValue) / 100;
					}
					break;
				case 14 :// added by ankit - additional duty 1%
					if (marketValue > considerationAmount) {
						janPadDuty = (marketValue * janpadValue) / 100;
					} else {
						janPadDuty = (considerationAmount * janpadValue) / 100;
					}
					break;
				case 16 :// added by ankit - transfer of mining lease
					if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("") && instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
						multiply = Double.parseDouble(instDTO.getInStampDutyAmount()) + Double.parseDouble(instDTO.getAsConsiderationAmount());
						janPadDuty = (multiply * janpadValue) / 100;
					}
					break;
			}
		} else {
			if (instDTO.getInstId() == 2210) {
				double stampAmount = 0;
				double considerationAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
					considerationAmount = Double.parseDouble(instDTO.getAsConsiderationAmount());
				}
				janPadDuty = ((considerationAmount) + (5 * stampAmount)) * 1 / 100;
			} else if (instDTO.getInstId() == 2211) {
				double stampAmount = 0;
				double considerationAmount = 0;
				if (instDTO.getInStampDutyAmount() != null && !instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
					stampAmount = Double.parseDouble(instDTO.getInStampDutyAmount());
				}
				if (instDTO.getAsConsiderationAmount() != null && !instDTO.getAsConsiderationAmount().equalsIgnoreCase("")) {
					considerationAmount = Double.parseDouble(instDTO.getAsConsiderationAmount());
				}
				janPadDuty = ((considerationAmount) + (8 * stampAmount)) * 1 / 100;
			}
		}
		return Math.ceil(janPadDuty);
	}

	public String calculateStampDutyConsnter(int instid, int deedid, String considerationAmount, String cancellationFlag, String familyFlag) {
		ArrayList stampDetails = null;
		if (instid == 2087 || instid == 2228 || instid == 2229 || instid == 2230) {
			stampDetails = dao.getStampDutyDetails(String.valueOf(1053), String.valueOf(2090)); // same
			// as
			// conveyance
		}
		/*
		 * else if(instid==2219||instid==2220) {
		 * stampDetails=dao.getStampDutyDetails
		 * (String.valueOf(dutyDTO.getDeedId(
		 * )),String.valueOf(instDTO.getLeaseId())); }
		 */
		else {
			stampDetails = dao.getStampDutyDetails(String.valueOf(deedid), String.valueOf(instid));
		}
		/*
		 * if(instDTO.getInstId()==2105||instDTO.getInstId()==2106||instDTO.getInstId
		 * ()==2108||instDTO.getInstId()==2184) { double partitionMV=0; double
		 * maxMv=0; ArrayList partitionList=dutyDTO.getPropertyDetailsList();
		 * for(int i=0;i<partitionList.size();i++) { DutyCalculationDTO
		 * pdto=(DutyCalculationDTO) partitionList.get(i);
		 * partitionMV=partitionMV+Double.parseDouble(pdto.getMarketValue());
		 * if(Double.parseDouble(pdto.getMarketValue())>=maxMv) {
		 * maxMv=Double.parseDouble(pdto.getMarketValue()); }
		 * 
		 * } dutyDTO.setPartitionMV(partitionMV);
		 * dutyDTO.setMaxPartitionMv(maxMv); }
		 */
		double stampduty = 0;
		double finalStampDuty = 0;
		int operationId;
		double operationValue;
		double unitFractionValue;
		double maxValue;
		double minValue;
		double multiply = 0;
		double marketValue = 0;
		/*
		 * if(dutyDTO.getDeedId()==1054) {
		 * if(dutyDTO.getExchangeMV1()>dutyDTO.getExchangeMV2()) {
		 * marketValue=dutyDTO.getExchangeMV1(); } else {
		 * marketValue=dutyDTO.getExchangeMV2(); } }
		 */
		/*
		 * else
		 * if(instDTO.getInstId()==2105||instDTO.getInstId()==2106||instDTO.
		 * getInstId()==2108||instDTO.getInstId()==2184) {
		 * marketValue=dutyDTO.getPartitionMV()-dutyDTO.getMaxPartitionMv(); }
		 * else
		 */
		{
			if (considerationAmount != null && !considerationAmount.equalsIgnoreCase("")) {
				marketValue = Double.parseDouble(considerationAmount);
			}
		}
		// double considerationAmount=0;
		/*
		 * if(instDTO.getAsConsiderationAmount()!=null&&!instDTO.getAsConsiderationAmount
		 * ().equalsIgnoreCase("")) {
		 * considerationAmount=Double.parseDouble(instDTO
		 * .getAsConsiderationAmount()); }
		 * if(instDTO.getInstId()==2215||instDTO.
		 * getInstId()==2218||instDTO.getInstId
		 * ()==2217||instDTO.getInstId()==2216
		 * ||instDTO.getInstId()==2220||instDTO
		 * .getInstId()==2219||instDTO.getInstId()==2223) { double
		 * stampAmount=0;
		 * if(instDTO.getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount
		 * ().equalsIgnoreCase("")) {
		 * stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount()); }
		 * considerationAmount=considerationAmount+stampAmount; }
		 * if("Y".equalsIgnoreCase
		 * (dutyDTO.getGovCheckFlag())&&(dutyDTO.getDeedId
		 * ()==1053||dutyDTO.getDeedId()==1092)) {
		 * marketValue=Double.parseDouble(dutyDTO.getGovValue());
		 * considerationAmount=0; }
		 */
		if (stampDetails != null && stampDetails.size() > 0) {
			ArrayList subList = (ArrayList) stampDetails.get(0);
			if (subList.get(1) == null || subList.get(1).toString().equalsIgnoreCase("")) {
				operationId = 0;
			} else {
				operationId = Integer.parseInt(subList.get(1).toString());
				if ("Y".equalsIgnoreCase(cancellationFlag)) {
					if (operationId == 1) {
						return dao.getCancelaationStamp(deedid, instid);
					}
				}
			}
			if (subList.get(2) == null || subList.get(2).toString().equalsIgnoreCase("")) {
				operationValue = 0;
			} else {
				operationValue = Double.parseDouble(subList.get(2).toString());
			}
			if (subList.get(3) == null || subList.get(3).toString().equalsIgnoreCase("")) {
				unitFractionValue = 0;
			} else {
				unitFractionValue = Double.parseDouble(subList.get(3).toString());
			}
			if (subList.get(4) == null || subList.get(4).toString().equalsIgnoreCase("")) {
				maxValue = 0;
			} else {
				maxValue = Double.parseDouble(subList.get(4).toString());
			}
			if (subList.get(5) == null || subList.get(5).toString().equalsIgnoreCase("")) {
				minValue = 0;
			} else {
				minValue = Double.parseDouble(subList.get(5).toString());
			}
			switch (operationId) {
				case 1 :
					stampduty = operationValue;
					break;
				case 2 :
					stampduty = (marketValue * operationValue) / 100;
					break;
				case 3 :
					stampduty = (marketValue * operationValue) / 100;
					break;
				case 4 :
					stampduty = (Math.ceil((marketValue / unitFractionValue)) * operationValue);
					break;
				case 5 :
					stampduty = (Math.ceil((marketValue / unitFractionValue)) * operationValue);
					break;
				case 6 :
					stampduty = marketValue * operationValue;
					break;
				case 7 :
					break;
				case 8 :
					break;
				case 9 :
					break;
				case 10 :
					break;
				case 11 :
					break;
				case 12 :
					break;
				case 13 :
					break;
				case 14 :// added by ankit - additional duty 1%
					stampduty = (marketValue * operationValue) / 100;
					break;
			}
			if (minValue > 0 && stampduty < minValue) {
				stampduty = minValue;
			}
			if (maxValue > 0 && stampduty > maxValue) {
				stampduty = maxValue;
			}
		} else {
			if (instid == 2107) {
				stampduty = ((marketValue * 100) * 5) / 100;
			}
			/*
			 * else if(instDTO.getInstId()==2091) { double stampduty1; double
			 * stampDuty2; stampduty1=(marketValue5)/100; double stampAmount=0;
			 * if
			 * (instDTO.getInStampDutyAmount()!=null&&!instDTO.getInStampDutyAmount
			 * ().equalsIgnoreCase("")) {
			 * stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount()); }
			 * stampDuty2=((considerationAmount+stampAmount)0.5)/100;
			 * if(stampduty1>stampDuty2) { stampduty=stampduty1; } else {
			 * stampduty=stampDuty2; } } else if(instDTO.getInstId()==2210) {
			 * double stampAmount=0;
			 * if(instDTO.getInStampDutyAmount()!=null&&!instDTO
			 * .getInStampDutyAmount().equalsIgnoreCase("")) {
			 * stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount()); }
			 * stampduty=((considerationAmount)+(5stampAmount))4/100; } else
			 * if(instDTO.getInstId()==2211) { double stampAmount=0;
			 * if(instDTO.getInStampDutyAmount
			 * ()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
			 * stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount()); }
			 * stampduty=((considerationAmount)+(8stampAmount))5/100; } else
			 * if(instDTO.getInstId()==2221) { double stampAmount=0;
			 * if(instDTO.getInStampDutyAmount
			 * ()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
			 * stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount()); }
			 * stampduty=((considerationAmount)+(5stampAmount))4/100; } else
			 * if(instDTO.getInstId()==2222) { double stampAmount=0;
			 * if(instDTO.getInStampDutyAmount
			 * ()!=null&&!instDTO.getInStampDutyAmount().equalsIgnoreCase("")) {
			 * stampAmount=Double.parseDouble(instDTO.getInStampDutyAmount()); }
			 * stampduty=((considerationAmount)+(8stampAmount))5/100; }
			 */}
		if ("Y".equalsIgnoreCase(familyFlag)) {
			return String.valueOf(Math.ceil(stampduty / 2));
		} else {
			return String.valueOf(Math.ceil(stampduty));
		}
	}

	public boolean insertAllDutiesConsenter(String regInitId) {
		boolean flag = true;
		String instid = "";
		String deedId = "";
		String cancellationFlag = "";
		String familyFlag = "";
		String dutyTxnId = "";
		String munDuty = "";
		double munduty = 0;
		ArrayList deedInstList = dao.deedInstDetails(regInitId);
		if (deedInstList != null && deedInstList.size() > 0) {
			ArrayList sublist = (ArrayList) deedInstList.get(0);
			instid = (String) sublist.get(1);
			deedId = (String) sublist.get(0);
			cancellationFlag = (String) sublist.get(2);
			familyFlag = (String) sublist.get(3);
			dutyTxnId = (String) sublist.get(4);
			munDuty = (String) sublist.get(5);
			if (munDuty != null && !munDuty.equalsIgnoreCase("")) {
				munduty = Double.parseDouble(munDuty);
			}
		}
		ArrayList consenterList = dao.consenterDetails(regInitId);
		if (consenterList != null && consenterList.size() > 0) {
			// for(int i=0;i<consenterList.size();i++)
			for (int i = 0; i < 1; i++)// hard coded 1 by roopam on 17 april 15
			// as per departments requirement
			{
				ArrayList sublist = (ArrayList) consenterList.get(i);
				String consenterId = (String) sublist.get(0);
				String consenterFlag = (String) sublist.get(1);
				String consneterAmount = (String) sublist.get(2);
				String stampDuty = "0";
				double janpad = 0;
				double municipal = 0;
				double upakr = 0;
				double total;
				String regFee = "0";
				if ("N".equalsIgnoreCase(consenterFlag)) {
					ArrayList dutyList = dao.constFixDutyDetails();
					if (dutyList != null && dutyList.size() > 0) {
						ArrayList dutyFixSub = (ArrayList) dutyList.get(0);
						stampDuty = (String) dutyFixSub.get(0);
						regFee = (String) dutyFixSub.get(4);
						if (dutyFixSub.get(1) != null && dutyFixSub.get(1).toString().equalsIgnoreCase("")) {
							janpad = Double.parseDouble(dutyFixSub.get(1).toString());
						}
						if (dutyFixSub.get(2) != null && dutyFixSub.get(2).toString().equalsIgnoreCase("")) {
							if (munduty > 0) {
								municipal = Double.parseDouble(dutyFixSub.get(2).toString());
							} else {
								municipal = 0;
							}
						}
						if (dutyFixSub.get(3) != null && dutyFixSub.get(3).toString().equalsIgnoreCase("")) {
							upakr = Double.parseDouble(dutyFixSub.get(3).toString());
						}
					}
				} else {
					stampDuty = calculateStampDutyConsnter(Integer.parseInt(instid), Integer.parseInt(deedId), consneterAmount, cancellationFlag, familyFlag);
					upakr = calculateUpkar(Integer.parseInt(instid), Double.parseDouble(stampDuty));
					if (munduty > 0) {
						municipal = calculateMuncipalDutyConsnter(Integer.parseInt(instid), Integer.parseInt(deedId), consneterAmount);
					} else {
						municipal = 0;
					}
					janpad = calculateJanPadDutyConsenter(Integer.parseInt(instid), Integer.parseInt(deedId), consneterAmount);
					regFee = new CalculateRegFee().calculateRegFeeConsenter(Integer.parseInt(instid), Integer.parseInt(deedId), consneterAmount, stampDuty, cancellationFlag);
				}
				total = upakr + janpad + municipal + Double.parseDouble(stampDuty);
				double regFe = Double.parseDouble(regFee);
				double dutyexemption = dao.getStampExemption(dutyTxnId, "S");
				double regexemption = dao.getStampExemption(dutyTxnId, "R");
				double exemptedduty = 0;
				double exemptedregfee = 0;
				double finalduty = 0;
				double finalregfee = 0;
				exemptedduty = (total * dutyexemption) / 100;
				exemptedregfee = (regFe * regexemption) / 100;
				finalduty = total - exemptedduty;
				finalregfee = regFe - exemptedregfee;
				boolean flag1 = dao.insertConsneterDuties(dutyTxnId, regInitId, consenterId, stampDuty, String.valueOf(janpad), String.valueOf(municipal), String.valueOf(upakr), String.valueOf(total), String.valueOf(regFee), String.valueOf(exemptedduty), String.valueOf(exemptedregfee), String.valueOf(finalduty), String.valueOf(finalregfee));
				if (flag1 == false) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	public int calculatePropWiseExemptedDutiesEWS(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO) {
		dutyDTO.setEwsAppliedFlag("N");
		double mainPayableDuty = dutyDTO.getTotalPayableStamp();
		ArrayList propertyList = dutyDTO.getPropertyDetailsList();
		ArrayList exempList = dutyDTO.getExemptionDescpList();
		boolean EWSExemp = false;
		boolean ewsSelected = false;
		boolean ligSelected = false;
		boolean migSelected = false;
		for (int i = 0; i < exempList.size(); i++) {
			DutyCalculationDTO dto1 = (DutyCalculationDTO) exempList.get(i);
			if (dto1.getExempId().equalsIgnoreCase("131") || dto1.getExempId().equalsIgnoreCase("132") || dto1.getExempId().equalsIgnoreCase("133")) {
				EWSExemp = true;
				if (dto1.getExempId().equalsIgnoreCase("131")) {// ews
					ewsSelected = true;
				} else if (dto1.getExempId().equalsIgnoreCase("132")) {// lig
					ligSelected = true;
				} else if (dto1.getExempId().equalsIgnoreCase("133")) {// mig
					migSelected = true;
				}
			}
		}
		if (ewsSelected && ligSelected) {
			return 2;// 1 out of 3 exemptions allowed
		} else if (ewsSelected && migSelected) {
			return 2;// 1 out of 3 exemptions allowed
		} else if (ligSelected && ewsSelected) {
			return 2;// 1 out of 3 exemptions allowed
		} else if (ligSelected && migSelected) {
			return 2;// 1 out of 3 exemptions allowed
		} else if (migSelected && ewsSelected) {
			return 2;// 1 out of 3 exemptions allowed
		} else if (migSelected && ligSelected) {
			return 2;// 1 out of 3 exemptions allowed
		}
		if (EWSExemp) {
			if (propertyList != null && propertyList.size() > 0) {
				dutyDTO.setTotalPayableStamp(0);
				/*
				 * dutyDTO.setTotalStampDuty(0); dutyDTO.setTotalUpkar(0);
				 * dutyDTO.setTotalNagarPalika(0); dutyDTO.setTotalPanchyat(0);
				 */
				for (int i = 0; i < propertyList.size(); i++) {
					DutyCalculationDTO ddto = (DutyCalculationDTO) propertyList.get(i);
					String propId = dao.getPropType(ddto.getValuationId());
					String propSubId = "NA";
					double areaToBeCompared = 0;// conditional
					if ("1".equalsIgnoreCase(propId)) {// plot
						RegCommonBO commonBo = new RegCommonBO();
						areaToBeCompared = commonBo.getPlotTotArea(ddto.getValuationId());
						if (ewsSelected) {
							if (areaToBeCompared > 0 && areaToBeCompared <= 60) {
								recalculateEWSDuties("EWS", ddto, instDTO.getInstId());
								dutyDTO.setEwsAppliedFlag("Y");
								dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getTotalEWS());
							} else {
								dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
							}
						} else if (ligSelected) {
							if (areaToBeCompared > 0 && areaToBeCompared <= 96) {
								recalculateEWSDuties("LIG", ddto, instDTO.getInstId());
								dutyDTO.setEwsAppliedFlag("Y");
								dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getTotalEWS());
							} else {
								dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
							}
						} else if (migSelected) {
							if (areaToBeCompared > 0 && areaToBeCompared <= 190) {
								recalculateEWSDuties("MIG", ddto, instDTO.getInstId());
								dutyDTO.setEwsAppliedFlag("Y");
								dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getTotalEWS());
							} else {
								dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
							}
						} else {
							dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
						}
					} else if ("2".equalsIgnoreCase(propId)) {// building
						ArrayList mainList = dao.getPropFurtherDetls(ddto.getValuationId());
						if (mainList != null && mainList.size() == 1) {
							ArrayList list = (ArrayList) mainList.get(0);
							propSubId = list.get(0).toString();
							if (propSubId.equalsIgnoreCase("2")) { // Independent
								// floor
								areaToBeCompared = Double.parseDouble(list.get(1).toString());// total
								// floor
								// area
							} else if (propSubId.equalsIgnoreCase("3") && list.get(2).toString().equalsIgnoreCase("18")) {// Multistorey
								// building
								// -
								// residential
								areaToBeCompared = Double.parseDouble(list.get(3).toString());// build
								// up
								// area
							} else if (propSubId.equalsIgnoreCase("1")) {// Independent
								// building
								int count = dao.getResiFloorCount(ddto.getValuationId());// resi
								if (count > 1) {
									areaToBeCompared = 0;
								} else {
									String floorArea = dao.getFloorArea(ddto.getValuationId());
									logger.debug("aabbcc");
									if (floorArea != null && !floorArea.equalsIgnoreCase("")) {
										areaToBeCompared = Double.parseDouble(floorArea);// sum
										// of
										// rcc
										// rbc
										// tin
										// and
										// kacha
									} else {
										areaToBeCompared = 0;
									}
									if (areaToBeCompared > 0 && ((ewsSelected && areaToBeCompared <= 30) || (ligSelected && areaToBeCompared <= 48) || (migSelected && areaToBeCompared <= 85))) {
										// housing board checkbox check here
										if (list.get(4) != null && list.get(4).toString().equalsIgnoreCase("Y")) {
											// if checkbox checked, do nothing
										} else {
											// return 1;//prompt user for
											// checkbox selection
											areaToBeCompared = 0;// if checkbox
											// not
											// checked,
											// no
											// exemption
											// to be
											// applied
											// on this
											// property
											// -done on
											// 24May2015
										}
									}
								}
							}
							if (ewsSelected) {
								if (areaToBeCompared > 0 && areaToBeCompared <= 30) {
									recalculateEWSDuties("EWS", ddto, instDTO.getInstId());
									dutyDTO.setEwsAppliedFlag("Y");
									dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getTotalEWS());
								} else {
									dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
								}
							} else if (ligSelected) {
								if (areaToBeCompared > 0 && areaToBeCompared <= 48) {
									recalculateEWSDuties("LIG", ddto, instDTO.getInstId());
									dutyDTO.setEwsAppliedFlag("Y");
									dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getTotalEWS());
								} else {
									dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
								}
							} else if (migSelected) {
								if (areaToBeCompared > 0 && areaToBeCompared <= 85) {
									recalculateEWSDuties("MIG", ddto, instDTO.getInstId());
									dutyDTO.setEwsAppliedFlag("Y");
									dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getTotalEWS());
								} else {
									dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
								}
							} else {
								dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
							}
						}
					} else {
						dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
					}
					/*
					 * dutyDTO.setTotalStampDuty(dutyDTO.getTotalStampDuty()+ddto
					 * .getStampDuty());
					 * dutyDTO.setTotalUpkar(dutyDTO.getTotalUpkar
					 * ()+ddto.getUpkarDuty());
					 * dutyDTO.setTotalNagarPalika(dutyDTO
					 * .getTotalNagarPalika()+ddto.getNagarPalikaDuty());
					 * dutyDTO.setTotalPanchyat(dutyDTO.getTotalPanchyat()+ddto.
					 * getPanchayatDuty());
					 */
				}
			}
		}
		double newPayableStampDuty = dutyDTO.getTotalPayableStamp();
		dutyDTO.setMinusExempStampTotal(mainPayableDuty - newPayableStampDuty);
		return 0;
	}

	public String[] calculateDutiesEWS_NPV(RegCompletionForm eForm, String appId) throws Exception {
		RegCommonBD commonBd = new RegCommonBD();
		RegCommonBO commonBo = new RegCommonBO();
		ArrayList exempList = commonBd.getExemptionList(appId);
		ArrayList propValList = commonBd.getAllPropertyId(appId);
		String dutyListArr[] = commonBo.getDutyDetlsForConfirmation(appId);
		boolean EWSExemp = false;
		boolean ewsSelected = false;
		boolean ligSelected = false;
		boolean migSelected = false;
		ArrayList rowList;
		String currExemp;
		String currValId;
		for (int i = 0; i < exempList.size(); i++) {
			rowList = (ArrayList) exempList.get(i);
			currExemp = rowList.get(0) != null ? rowList.get(0).toString() : "";
			if (currExemp.equalsIgnoreCase("131") || currExemp.equalsIgnoreCase("132") || currExemp.equalsIgnoreCase("133")) {
				EWSExemp = true;
				if (currExemp.equalsIgnoreCase("131")) {// ews
					ewsSelected = true;
				} else if (currExemp.equalsIgnoreCase("132")) {// lig
					ligSelected = true;
				} else if (currExemp.equalsIgnoreCase("133")) {// mig
					migSelected = true;
				}
			} else {
				EWSExemp = false;
				break;
			}
		}
		if (EWSExemp) {
			if (propValList != null && propValList.size() > 0) {
				for (int i = 0; i < propValList.size(); i++) {
					rowList = (ArrayList) propValList.get(i);
					currValId = rowList.get(1) != null ? rowList.get(1).toString() : "";
					String propId = dao.getPropType(currValId);
					String propSubId = "NA";
					double areaToBeCompared = 0;// conditional
					if ("1".equalsIgnoreCase(propId)) {// plot
						areaToBeCompared = commonBo.getPlotTotArea(currValId);
						if (ewsSelected) {
							if (areaToBeCompared > 0 && areaToBeCompared <= 60) {
								// recalculateEWSDuties("EWS",ddto,instDTO.
								// getInstId());
								// dutyDTO.setTotalPayableStamp(dutyDTO.
								// getTotalPayableStamp()+ddto.getTotalEWS());
							} else {
								EWSExemp = false;
								break;
								// dutyDTO.setTotalPayableStamp(dutyDTO.
								// getTotalPayableStamp
								// ()+ddto.getPayableStampDuty());
							}
						} else if (ligSelected) {
							if (areaToBeCompared > 0 && areaToBeCompared <= 96) {
								// recalculateEWSDuties("LIG",ddto,instDTO.
								// getInstId());
								// dutyDTO.setTotalPayableStamp(dutyDTO.
								// getTotalPayableStamp()+ddto.getTotalEWS());
							} else {
								EWSExemp = false;
								break;
								// dutyDTO.setTotalPayableStamp(dutyDTO.
								// getTotalPayableStamp
								// ()+ddto.getPayableStampDuty());
							}
						} else if (migSelected) {
							if (areaToBeCompared > 0 && areaToBeCompared <= 190) {
								// recalculateEWSDuties("MIG",ddto,instDTO.
								// getInstId());
								// dutyDTO.setTotalPayableStamp(dutyDTO.
								// getTotalPayableStamp()+ddto.getTotalEWS());
							} else {
								EWSExemp = false;
								break;
								// dutyDTO.setTotalPayableStamp(dutyDTO.
								// getTotalPayableStamp
								// ()+ddto.getPayableStampDuty());
							}
						} else {
							EWSExemp = false;
							break;
							// dutyDTO.setTotalPayableStamp(dutyDTO.
							// getTotalPayableStamp
							// ()+ddto.getPayableStampDuty());
						}
					} else if ("2".equalsIgnoreCase(propId)) {// building
						ArrayList mainList = dao.getPropFurtherDetls(currValId);
						if (mainList != null && mainList.size() == 1) {
							ArrayList list = (ArrayList) mainList.get(0);
							propSubId = list.get(0).toString();
							if (propSubId.equalsIgnoreCase("2")) { // Independent
								// floor
								areaToBeCompared = Double.parseDouble(list.get(1).toString());// total
								// floor
								// area
							} else if (propSubId.equalsIgnoreCase("3") && list.get(2).toString().equalsIgnoreCase("18")) {// Multistorey
								// building
								// -
								// residential
								areaToBeCompared = Double.parseDouble(list.get(3).toString());// build
								// up
								// area
							} else if (propSubId.equalsIgnoreCase("1")) {// Independent
								// building
								int count = dao.getResiFloorCount(currValId);// resi
								if (count > 1) {
									areaToBeCompared = 0;
								} else {
									String floorArea = dao.getFloorArea(currValId);
									logger.debug("aabbcc");
									if (floorArea != null && !floorArea.equalsIgnoreCase("")) {
										areaToBeCompared = Double.parseDouble(floorArea);// sum
										// of
										// rcc
										// rbc
										// tin
										// and
										// kacha
									} else {
										areaToBeCompared = 0;
									}
									if (areaToBeCompared > 0 && ((ewsSelected && areaToBeCompared <= 30) || (ligSelected && areaToBeCompared <= 48) || (migSelected && areaToBeCompared <= 85))) {
										// housing board checkbox check here
										if (list.get(4) != null && list.get(4).toString().equalsIgnoreCase("Y")) {
											// if checkbox checked, do nothing
										} else {
											// return 1;//prompt user for
											// checkbox selection
											EWSExemp = false;
											break;
										}
									}
								}
							}
							if (ewsSelected) {
								if (areaToBeCompared > 0 && areaToBeCompared <= 30) {
									// recalculateEWSDuties("EWS",ddto,instDTO.
									// getInstId());
									// dutyDTO.setTotalPayableStamp(dutyDTO.
									// getTotalPayableStamp
									// ()+ddto.getTotalEWS());
								} else {
									EWSExemp = false;
									break;
									// dutyDTO.setTotalPayableStamp(dutyDTO.
									// getTotalPayableStamp
									// ()+ddto.getPayableStampDuty());
								}
							} else if (ligSelected) {
								if (areaToBeCompared > 0 && areaToBeCompared <= 48) {
									// recalculateEWSDuties("LIG",ddto,instDTO.
									// getInstId());
									// dutyDTO.setTotalPayableStamp(dutyDTO.
									// getTotalPayableStamp
									// ()+ddto.getTotalEWS());
								} else {
									EWSExemp = false;
									break;
									// dutyDTO.setTotalPayableStamp(dutyDTO.
									// getTotalPayableStamp
									// ()+ddto.getPayableStampDuty());
								}
							} else if (migSelected) {
								if (areaToBeCompared > 0 && areaToBeCompared <= 85) {
									// recalculateEWSDuties("MIG",ddto,instDTO.
									// getInstId());
									// dutyDTO.setTotalPayableStamp(dutyDTO.
									// getTotalPayableStamp
									// ()+ddto.getTotalEWS());
								} else {
									EWSExemp = false;
									break;
									// dutyDTO.setTotalPayableStamp(dutyDTO.
									// getTotalPayableStamp
									// ()+ddto.getPayableStampDuty());
								}
							} else {
								EWSExemp = false;
								break;
								// dutyDTO.setTotalPayableStamp(dutyDTO.
								// getTotalPayableStamp
								// ()+ddto.getPayableStampDuty());
							}
						}
					} else {
						EWSExemp = false;
						break;
						// dutyDTO.setTotalPayableStamp(dutyDTO.
						// getTotalPayableStamp()+ddto.getPayableStampDuty());
					}
				}
				// change duties here
				if (EWSExemp) {
					String[] dutiesParam = new String[4];
					if (ewsSelected || ligSelected) {
						DecimalFormat myformatter = new DecimalFormat("###.##");
						double exempAmount = Double.parseDouble(dutyListArr[12].trim()) + Double.parseDouble(dutyListArr[5].trim());
						dutiesParam[0] = "0";// TOTAL_AFTER_EXEMP
						dutiesParam[1] = myformatter.format(exempAmount);// duty
						// exemp
						dutiesParam[2] = "SYSTEM";// UPDATE_BY
						dutiesParam[3] = appId;
					} else {
						DecimalFormat myformatter = new DecimalFormat("###.##");
						double stamp = (.75) * Double.parseDouble((dutyListArr[0].trim()));
						double nagar = Double.parseDouble(dutyListArr[1].trim());
						double gram = Double.parseDouble(dutyListArr[2].trim());
						double upkar = calculateUpkar(eForm.getInstID(), stamp);
						double newtotal = stamp + nagar + gram + upkar;
						double exempAmount = Double.parseDouble(dutyListArr[12].trim()) + (Double.parseDouble(dutyListArr[5].trim()) - newtotal);
						dutiesParam[0] = myformatter.format(newtotal);// TOTAL_AFTER_EXEMP
						dutiesParam[1] = myformatter.format(exempAmount);// duty
						// exemp
						dutiesParam[2] = "SYSTEM";// UPDATE_BY
						dutiesParam[3] = appId;
					}
					return dutiesParam;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public void recalculateEWSDuties(String exemptionFlag, DutyCalculationDTO ddto, int instId) {
		if (exemptionFlag.equalsIgnoreCase("EWS") || exemptionFlag.equalsIgnoreCase("LIG")) {
			ddto.setStampDutyEWS(0);
			ddto.setNagarPalikaDutyEWS(0);
			ddto.setPanchayatDutyEWS(0);
			ddto.setUpkarDutyEWS(0);
			ddto.setTotalEWS(0.0);
			// ddto.setDashTotal("0");
			ddto.setDashPayTotal("0");
		} else {
			DecimalFormat myformatter = new DecimalFormat("###.##");
			ddto.setStampDutyEWS((.75) * (ddto.getStampDuty()));
			ddto.setUpkarDutyEWS(calculateUpkar(instId, ddto.getStampDutyEWS()));
			ddto.setTotalEWS(ddto.getStampDutyEWS() + ddto.getNagarPalikaDuty() + ddto.getPanchayatDuty() + ddto.getUpkarDutyEWS());
			// ddto.setDashTotal(myformatter.format(ddto.getTotal()));
			ddto.setDashPayTotal(myformatter.format(ddto.getTotalEWS()));
		}
	}

	// added by ankit for palnt and machinery -- start
	public String calculateDutyOnMovableProp(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, String dutyType, double stampDutyOnMovProp) {
		double plantAndMachineryAmount = 0;
		double duty = 0;
		int operationId;
		double operationValue;
		double unitFractionValue;
		double maxValue;
		double minValue;
		double multiply = 0;
		if (instDTO.getValueOfMovableProp() != null && !instDTO.getValueOfMovableProp().equalsIgnoreCase("")) {
			plantAndMachineryAmount = Double.parseDouble(instDTO.getValueOfMovableProp());
		}
		ArrayList dutyDetails = null;
		if (dutyDTO != null && instDTO != null) {
			dutyDetails = dao.getDutyDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getInstId()), dutyType);
		}
		if (dutyDetails != null && dutyDetails.size() > 0) {
			ArrayList subList = (ArrayList) dutyDetails.get(0);
			if (subList.get(1) == null || subList.get(1).toString().equalsIgnoreCase("")) {
				operationId = 0;
			} else {
				operationId = Integer.parseInt(subList.get(1).toString());
				if ("Y".equalsIgnoreCase(dutyDTO.getCancellationFlag())) {
					if (operationId == 1) {
						return dao.getCancelaationStamp(dutyDTO.getDeedId(), instDTO.getInstId());
					}
				}
			}
			if (subList.get(2) == null || subList.get(2).toString().equalsIgnoreCase("")) {
				operationValue = 0;
			} else {
				operationValue = Double.parseDouble(subList.get(2).toString());
			}
			if (subList.get(3) == null || subList.get(3).toString().equalsIgnoreCase("")) {
				unitFractionValue = 0;
			} else {
				unitFractionValue = Double.parseDouble(subList.get(3).toString());
			}
			if (subList.get(4) == null || subList.get(4).toString().equalsIgnoreCase("")) {
				maxValue = 0;
			} else {
				maxValue = Double.parseDouble(subList.get(4).toString());
			}
			if (subList.get(5) == null || subList.get(5).toString().equalsIgnoreCase("")) {
				minValue = 0;
			} else {
				minValue = Double.parseDouble(subList.get(5).toString());
			}
			if (dutyType.equalsIgnoreCase(com.wipro.igrs.newdutycalculation.constant.CommonConstant.MUNICIPAL_MOV_PROP)) {
			}
			switch (operationId) {
				case 1 :
					duty = operationValue;
					break;
				case 3 :
					if (plantAndMachineryAmount > 0.0) {
						duty = (plantAndMachineryAmount * operationValue) / 100;
					}
					break;
				case 7 :
					if (stampDutyOnMovProp > 0) {
						duty = (operationValue * stampDutyOnMovProp) / 100;
					}
					break;
				case 8 :
					break;
				case 9 :
					break;
				case 10 :
			}
			if (minValue > 0 && duty < minValue) {
				duty = minValue;
			}
			if (maxValue > 0 && duty > maxValue) {
				duty = maxValue;
			}
		} else {
			duty = 0.0;
		}
		return String.valueOf(Math.ceil(duty));
	}

	public String calculateStampDutyOnMovableProp(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, String dutyType) {
		return calculateDutyOnMovableProp(instDTO, dutyDTO, dutyType, 0);
	}

	public String calculateJanpadDutyOnMovableProp(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, String dutyType) {
		return calculateDutyOnMovableProp(instDTO, dutyDTO, dutyType, 0);
	}

	/*
	 * public String calculateMunicipalDutyOnMovableProp(InstrumentsDTO instDTO,
	 * DutyCalculationDTO dutyDTO, String dutyType) { return
	 * calculateDutyOnMovableProp(instDTO, dutyDTO, dutyType, 0); }
	 */
	public String calculateMunicipalDutyOnMovableProp(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, DutyCalculationDTO propDTO, String dutyType) {
		return getMunicipalDutyOnMovableProp(instDTO, dutyDTO, propDTO, dutyType);
	}

	public String calculateRegFeeOnMovableProp(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, String dutyType) {
		return calculateDutyOnMovableProp(instDTO, dutyDTO, dutyType, 0);
	}

	public double calculateUpkarDutyOnMovableProp(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, String dutyType, double stampDuty) {
		double upkar = 0;
		upkar = Double.parseDouble(calculateDutyOnMovableProp(instDTO, dutyDTO, dutyType, stampDuty));
		return Math.ceil(upkar);
	}

	public double getMuncipalValfromProp(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, DutyCalculationDTO propDTO) {
		double municipalValue = 0;
		{
			boolean applicableFlag = false;
			String flag = "";
			String subAreaId = "";
			String municipalId = "";
			if (propDTO.getValuationId() == null || propDTO.getValuationId().equalsIgnoreCase("")) {
				if ("Y".equalsIgnoreCase(dutyDTO.getMunicipalCheck())) {
					applicableFlag = true;
				}
			} else {
				ArrayList municpal = dao.getMunicipalId(propDTO.getValuationId());
				if (municpal != null && municpal.size() > 0) {
					ArrayList subList = (ArrayList) municpal.get(0);
					if (subList.get(0) == null || subList.get(0).toString().equalsIgnoreCase("")) {
						subAreaId = "";
					} else {
						subAreaId = subList.get(0).toString();
					}
					if (subList.get(1) == null || subList.get(1).toString().equalsIgnoreCase("")) {
						flag = "";
					} else {
						flag = subList.get(1).toString();
					}
					if (subList.get(2) == null || subList.get(2).toString().equalsIgnoreCase("")) {
						municipalId = "";
					} else {
						municipalId = subList.get(2).toString();
					}
					if ("5".equalsIgnoreCase(subAreaId) && "Y".equalsIgnoreCase(flag)) {
						applicableFlag = true;
					} else if (("1".equalsIgnoreCase(municipalId) || "5".equalsIgnoreCase(municipalId) || "2".equalsIgnoreCase(municipalId) || ("3".equalsIgnoreCase(municipalId)) || "4".equalsIgnoreCase(municipalId)) && "Y".equalsIgnoreCase(flag)) {
						applicableFlag = true;
					} else {
						applicableFlag = false;
					}
				}
			}
			if (applicableFlag) {
				ArrayList subList = dao.janpadMunicipalDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getInstId()));
				if (subList != null && subList.size() > 0) {
					ArrayList janpadDetails = (ArrayList) subList.get(0);
					int operationId = 0;
					if (janpadDetails.get(0) == null || janpadDetails.get(0).toString().equalsIgnoreCase("")) {
						operationId = 0;
					} else {
						operationId = Integer.parseInt(janpadDetails.get(0).toString());
					}
					if ("5".equalsIgnoreCase(subAreaId)) {
						if (janpadDetails.get(4) == null || janpadDetails.get(4).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(4).toString());
						}
					} else if ("1".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(1) == null || janpadDetails.get(1).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(1).toString());
						}
					} else if ("2".equalsIgnoreCase(municipalId) || "5".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(2) == null || janpadDetails.get(2).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(2).toString());
						}
					} else if ("4".equalsIgnoreCase(municipalId)) {
						if (janpadDetails.get(3) == null || janpadDetails.get(3).toString().equalsIgnoreCase("")) {
							municipalValue = 0;
						} else {
							municipalValue = Double.parseDouble(janpadDetails.get(3).toString());
						}
					} else {
						// changes for municipal value where valuation id not
						// required and nagar parishad .
						municipalValue = 3;
					}
				}
			}
		}
		return Math.ceil(municipalValue);
	}

	public String getMunicipalDutyOnMovableProp(InstrumentsDTO instDTO, DutyCalculationDTO dutyDTO, DutyCalculationDTO propDTO, String dutyType) {
		double plantAndMachineryAmount = 0;
		double duty = 0;
		int operationId;
		double operationValue;
		double unitFractionValue;
		double maxValue;
		double minValue;
		double multiply = 0;
		if (instDTO.getValueOfMovableProp() != null && !instDTO.getValueOfMovableProp().equalsIgnoreCase("")) {
			plantAndMachineryAmount = Double.parseDouble(instDTO.getValueOfMovableProp());
		}
		ArrayList dutyDetails = null;
		if (dutyDTO != null && instDTO != null) {
			dutyDetails = dao.getDutyDetails(String.valueOf(dutyDTO.getDeedId()), String.valueOf(instDTO.getInstId()), dutyType);
		}
		if (dutyDetails != null && dutyDetails.size() > 0) {
			ArrayList subList = (ArrayList) dutyDetails.get(0);
			if (subList.get(1) == null || subList.get(1).toString().equalsIgnoreCase("")) {
				operationId = 0;
			} else {
				operationId = Integer.parseInt(subList.get(1).toString());
				if ("Y".equalsIgnoreCase(dutyDTO.getCancellationFlag())) {
					if (operationId == 1) {
						return dao.getCancelaationStamp(dutyDTO.getDeedId(), instDTO.getInstId());
					}
				}
			}
			if (subList.get(2) == null || subList.get(2).toString().equalsIgnoreCase("")) {
				operationValue = 0;
			} else {
				operationValue = Double.parseDouble(subList.get(2).toString());
			}
			if (subList.get(3) == null || subList.get(3).toString().equalsIgnoreCase("")) {
				unitFractionValue = 0;
			} else {
				unitFractionValue = Double.parseDouble(subList.get(3).toString());
			}
			if (subList.get(4) == null || subList.get(4).toString().equalsIgnoreCase("")) {
				maxValue = 0;
			} else {
				maxValue = Double.parseDouble(subList.get(4).toString());
			}
			if (subList.get(5) == null || subList.get(5).toString().equalsIgnoreCase("")) {
				minValue = 0;
			} else {
				minValue = Double.parseDouble(subList.get(5).toString());
			}
			if (dutyType.equalsIgnoreCase(com.wipro.igrs.newdutycalculation.constant.CommonConstant.MUNICIPAL_MOV_PROP) && instDTO.getInstId() == 2271) {
				double optValue = getMuncipalValfromProp(instDTO, dutyDTO, propDTO);
				if (optValue == 0) {
					operationValue = 0;
				}
			}
			switch (operationId) {
				case 1 :
					duty = operationValue;
					break;
				case 3 :
					if (plantAndMachineryAmount > 0.0) {
						duty = (plantAndMachineryAmount * operationValue) / 100;
					}
					break;
				case 8 :
					break;
				case 9 :
					break;
				case 10 :
			}
			if (minValue > 0 && duty < minValue) {
				duty = minValue;
			}
			if (maxValue > 0 && duty > maxValue) {
				duty = maxValue;
			}
		} else {
			duty = 0.0;
		}
		return String.valueOf(Math.ceil(duty));
	}
	// added by ankit for palnt and machinery -- end
}
