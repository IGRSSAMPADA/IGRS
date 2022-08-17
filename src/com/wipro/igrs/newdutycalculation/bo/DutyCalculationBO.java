/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  DutyCalculationBO.java
 * Author      :  Madan Mohan 
 * Description :   
*/
package com.wipro.igrs.newdutycalculation.bo;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.newdutycalculation.bd.DutyCalculationBD;
import com.wipro.igrs.newdutycalculation.constant.CommonConstant;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;
import com.wipro.igrs.reginit.bd.RegCommonBD;

/**
 * @since 14 jan 2008 File Name: DutyCalculationBO.java
 * @author Madan Mohan
 * @version 1.0 Created on 14 jan 2008
 */
public class DutyCalculationBO {
	DutyCalculationBD bd = null;
	private Logger logger = (Logger) Logger.getLogger(RegCommonBD.class);

	/**
	 * @author Madan Mohan
	 */
	public DutyCalculationBO() {
		bd = new DutyCalculationBD();
	}

	/**
	 * @param deedType
	 * @return ArrayList
	 */
	public ArrayList getInstrument(int deedType, String languageLocale, String cancellationFlag, String module) {
		ArrayList list = new ArrayList();
		try {
			DutyCalculationBD bd = new DutyCalculationBD();
			int[] deedId = new int[1];
			deedId[0] = deedType;
			ArrayList retList = bd.getInstruments(deedId, cancellationFlag, module);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					InstrumentsDTO dto = new InstrumentsDTO();
					dto.setInstId(Integer.parseInt(lst.get(0).toString()));
					//dto.setLabelDisplay(lst.get(2).toString());
					//dto.setLabelAmountFlag(lst.get(3).toString());
					if (languageLocale.equalsIgnoreCase("en_US")) {
						dto.setInstType(lst.get(1).toString());
						dto.setHdnAmount(lst.get(0).toString() + "~" + lst.get(1) + "~" + lst.get(2) + "~" + lst.get(3));// LABEL VALUE
					} else {
						dto.setInstType(lst.get(5).toString());
						dto.setHdnAmount(lst.get(0).toString() + "~" + lst.get(5) + "~" + lst.get(4) + "~" + lst.get(3));// LABEL VALUE
					}
					logger.debug(dto.getHdnAmount());
					list.add(dto);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	/**
	 * @param deedType
	 * @param instId
	 * @return ArrayList
	 */
	public ArrayList getExemptions(int deedType, int instId, String langauge, boolean fromEstamp) {
		ArrayList list = new ArrayList();
		try {
			//IGRSCommon common = new IGRSCommon();
			// DBUtility	 dbUtil = new DBUtility();
			ArrayList retList = new ArrayList();
			System.out.println("INSIDE IF");
			int[] deedId = new int[2];
			deedId[0] = deedType;
			deedId[1] = instId;
			retList = bd.getInstLevelExemptions(deedId, langauge);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					DutyCalculationDTO dto = new DutyCalculationDTO();
					/*	dto.setExempId(lst.get(0).toString());
						dto.setExempName(lst.get(1).toString());
						dto.setExempNameId(lst.get(0).toString() + "~"
								+ lst.get(1).toString());
						System.out.println("###" + lst.get(1).toString());
						dto.setExempDescription(lst.get(2).toString());
						dto.setExmepApplicable(lst.get(3).toString());
						dto.setExemppercent(lst.get(4).toString());
						list.add(dto);*/
					String exemEstamp = lst.get(5).toString();
					if ((fromEstamp && !StringUtils.equalsIgnoreCase(exemEstamp, "N")) || !fromEstamp) {
						dto.setExempId(lst.get(0).toString());
						dto.setExempName(lst.get(1).toString());
						dto.setExempNameId(lst.get(0).toString() + "~" + lst.get(1).toString());
						System.out.println("###" + lst.get(1).toString());
						dto.setExempDescription(lst.get(2).toString());
						dto.setExmepApplicable(lst.get(3).toString());
						dto.setExemppercent(lst.get(4).toString());
						list.add(dto);
					}
				}
			}
		} catch (Exception x) {
			System.out.println(x);
			x.printStackTrace();
		}
		return list;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getRegFeeExemptions(int deedType, int instId, String langauge, boolean fromEstamp) {
		ArrayList list = new ArrayList();
		try {
			//	IGRSCommon common = new IGRSCommon();
			ArrayList retList = new ArrayList();
			System.out.println("INSIDE IF");
			int[] deedId = new int[2];
			deedId[0] = deedType;
			deedId[1] = instId;
			retList = bd.getRegFeeExemptions(deedId, langauge);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					String exemEstamp = lst.get(5).toString();
					if ((fromEstamp && !StringUtils.equalsIgnoreCase(exemEstamp, "N")) || !fromEstamp) {
						DutyCalculationDTO dto = new DutyCalculationDTO();
						dto.setExempId(lst.get(0).toString());
						dto.setExempName(lst.get(1).toString());
						dto.setExempNameId(lst.get(0).toString() + "~" + lst.get(1).toString());
						System.out.println("###" + lst.get(1).toString());
						dto.setExempDescription(lst.get(2).toString());
						dto.setExmepApplicable(lst.get(3).toString());
						dto.setExemppercent(lst.get(4).toString());
						list.add(dto);
					}
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	public ArrayList getSelectedMineralList(String langauge, String mineralSelected) {
		ArrayList subList = new ArrayList();
		try {
			//	IGRSCommon common = new IGRSCommon();
			ArrayList retList = bd.getSelectedMineralList(langauge, mineralSelected);
			//retList=(ArrayList)mainRetList.get(0);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					DutyCalculationDTO dto = new DutyCalculationDTO();
					dto.setMineralId(lst.get(0).toString());
					dto.setMineralName(lst.get(1).toString());
					subList.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return subList;
	}

	public ArrayList getDeed(String option, String cancellationFlag, String language, String module) {
		ArrayList list = new ArrayList();
		try {
			//IGRSCommon common = new IGRSCommon();
			System.out.println("Before deed selection");
			ArrayList retList = bd.getDeedType(option, cancellationFlag, language, module);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					DutyCalculationDTO dto = new DutyCalculationDTO();
					dto.setDeedId(Integer.parseInt(lst.get(0).toString()));
					dto.setDeedType(lst.get(1).toString());
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	public ArrayList getDeedCategory(String language) {
		ArrayList list = new ArrayList();
		try {
			//	IGRSCommon common = new IGRSCommon();
			System.out.println("Before deed selection");
			ArrayList retList = bd.getDeedCategoryList(language);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					DutyCalculationDTO dto = new DutyCalculationDTO();
					dto.setDeedCategoryId((String) lst.get(0));
					dto.setDeedCategoryName((String) lst.get(1));
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	/**
	 * @param dutyDTO
	 * @param instDTO
	 * @param exeDTO
	 * @return String
	 */
	public double getDutyCalculation(DutyCalculationDTO dutyDTO, InstrumentsDTO instDTO, ExemptionDTO exeDTO) {
		double calculatedDuty = 0.0;
		try {
			DutyCalculationBD bd = new DutyCalculationBD();
			logger.debug("@@@@22@@@@" + instDTO.getHdnAmount() + ":" + dutyDTO.getDeedId());
			//changed  on 10-05-2012 for resolving null point error
			int instId = instDTO.getInstId();
			double annualRent = dutyDTO.getAnnualRent();
			double dutyPaid = dutyDTO.getDutyPaid();
			int deedId = dutyDTO.getDeedId();
			String exemption = exeDTO.getHdnExAmount();
			//	if(inst != null && inst.length == 2)  {
			//	logger.debug("Instrument type:-"+instId+":"+inst[1]);
			//instId = inst[0];
			//}
			logger.debug("The exemption value is for :" + exeDTO.getHdnExAmount());
			/*
			if(exeDTO.getHdnExempAmount()!=null){
				String[] exemptions =exeDTO.getHdnExempAmount().split("~");
			
				for(int i=0;i<exemptions.length;i++)
				{	
				logger.debug("Exemption Type "+i+" : -"+exemptions[i]);
				}
			
			
			
				for(int i=0;i<exemptions.length;i++)
				{	
					exeDTO.setHdnExAmount(exemptions[i]+","+exemptions[i]);
					i=i+2;
				}
				exemption=exeDTO.getHdnExAmount();
			
				logger.debug("Exemption Type new :-"+exeDTO.getHdnExAmount());
			}
			*/
			double baseVal = 0.0;
			if (dutyDTO.getBaseValue() != null && !dutyDTO.getBaseValue().equalsIgnoreCase("")) {
				baseVal = Double.parseDouble(dutyDTO.getBaseValue());
			}
			calculatedDuty = bd.getStampDuty(deedId, instId, exemption, baseVal, annualRent, dutyPaid);
			logger.debug("Duty :-" + calculatedDuty);
		} catch (Exception x) {
			logger.debug(x);
		}
		return calculatedDuty;
	}

	public double getRegistrationFee(DutyCalculationDTO dutyDTO, InstrumentsDTO instDTO, ExemptionDTO exeDTO) {
		double regFee = 0.0;
		try {
			IGRSCommon common = new IGRSCommon();
			logger.debug("@@@@@@@@" + instDTO.getHdnAmount() + ":" + dutyDTO.getDeedId());
			int instId = instDTO.getInstId();
			int deedId = dutyDTO.getDeedId();
			String exemption = exeDTO.getHdnExAmount();
			logger.debug("Exemption Type:-" + exemption);
			//double baseValue = dutyDTO.getBaseValue();
			double baseValue = 0.0;
			if (dutyDTO.getBaseValue() != null && !dutyDTO.getBaseValue().equalsIgnoreCase("")) {
				baseValue = Double.parseDouble(dutyDTO.getBaseValue());
			}
			logger.debug("baseValue:-" + baseValue);
			// commented by Lavi for resolving bug of Lease deed
			//double duty =dutyDTO.getDuty();
			// Added by Lavi for resolving bug of Lease deed
			double duty = dutyDTO.getStampDuty();
			// end of addition by Lavi for resolving bug of Lease deed
			logger.debug("STAM DUTY:-" + duty);
			regFee = common.getRegistrstionFee(deedId, instId, null, duty, baseValue);
			logger.debug("Registration Fee :-" + regFee);
		} catch (Exception x) {
			logger.debug(x);
		}
		return regFee;
	}

	public double[] getMunicipalDutyCalculation(DutyCalculationDTO dutyDTO, InstrumentsDTO instDTO, ExemptionDTO exeDTO) {
		double calculatedDuty[] = new double[3];
		try {
			IGRSCommon common = new IGRSCommon();
			logger.debug("@@@@1545@@@@" + instDTO.getInstId() + ":" + dutyDTO.getDeedId());
			//if (instDTO.getHdnAmount()!=null){
			//	String[] inst = instDTO.getHdnAmount().split("~");
			int instId = instDTO.getInstId();
			int deedId = dutyDTO.getDeedId();
			String marketVal = "";
			double stampDuty = dutyDTO.getStampDuty();
			logger.debug("@@@@@@@STAMP DUTY @@@@@@@" + stampDuty);
			//	if (inst != null && inst.length == 2) {
			//	logger.debug("Instrument type:-" + instId + ":" + inst[1]);
			//instId = inst[0];
			//}
			String exemption = exeDTO.getHdnExAmount();
			logger.debug("Exemption Type:-" + exemption);
			//double marketValue =dutyDTO.getBaseValue();
			//double baseValue = dutyDTO.getBaseValue();
			double marketValue = 0.0;
			double baseValue = 0.0;
			if (dutyDTO.getBaseValue() != null && !dutyDTO.getBaseValue().equalsIgnoreCase("")) {
				baseValue = Double.parseDouble(dutyDTO.getBaseValue());
				marketValue = Double.parseDouble(dutyDTO.getBaseValue());
			}
			logger.debug("marketValue:-" + marketValue);
			logger.debug("baseValue:-" + baseValue);
			logger.debug(" CALLING MUNICIPAL BODY FUNCTIO0N FOR CALCULATING THE DATA");
			calculatedDuty = common.getMuncipalCalculatedDuty(deedId, instId, marketValue, stampDuty);
			logger.debug("Duty :-" + calculatedDuty);
		} catch (Exception x) {
			logger.debug(x);
		}
		return calculatedDuty;
	}

	/**
	 * @author Rishab
	 */
	public void captureNonPropStampDetails(DutyCalculationDTO dutyDTO, InstrumentsDTO instDTO, ExemptionDTO exeDTO) {
		String firstName = dutyDTO.getFirstName();
		String middleName = dutyDTO.getMidName();
		String lastName = dutyDTO.getLastName();
		String exempId = exeDTO.getHdnExAmount();
		String dateofbirth = null;
		int deedId = dutyDTO.getDeedId();
		int instId = instDTO.getInstId();
		double stampDuty = dutyDTO.getStampDuty();
		double gramDuty = dutyDTO.getPanchayatDuty();
		double nagarDuty = dutyDTO.getNagarPalikaDuty();
		double upkar = dutyDTO.getUpkarDuty();
		double regFee = dutyDTO.getRegFee();
		//double marketValue =dutyDTO.getBaseValue();
		double annualRent = dutyDTO.getAnnualRent();
		double total = dutyDTO.getTotal();
		double regPaid = dutyDTO.getRegPaid();
		double dutyPaid = dutyDTO.getDutyAlreadyPaid();
		String userId = dutyDTO.getUserId();
		double marketValue = 0.0;
		//double baseValue=0.0;
		if (dutyDTO.getBaseValue() != null && !dutyDTO.getBaseValue().equalsIgnoreCase("")) {
			//baseValue=Double.parseDouble(dutyDTO.getBaseValue());
			marketValue = Double.parseDouble(dutyDTO.getBaseValue());
		}
		if (dutyDTO.getDobDay() != null) {
			System.out.println("inside first == dateofbirth " + dateofbirth);
			dateofbirth = dutyDTO.getDobDay() + "-" + dutyDTO.getDobMonth() + "-" + dutyDTO.getDobYear();
		}
		try {
			//updated on 06-10-2012 
			System.out.println("@@@@45454545@@@@");
			System.out.println("Checking market value");
			String dutyId = new IGRSCommon().captureNonPropStampDetails(firstName, middleName, lastName, dateofbirth, stampDuty, gramDuty, nagarDuty, upkar, regFee, marketValue, annualRent, total, deedId, instId, exempId, regPaid, dutyPaid, userId);
			dutyDTO.setStampId(Integer.parseInt((dutyId)));
			System.out.println(marketValue);
			System.out.println(" valuation id" + dutyId);
		} catch (Exception x) {
			System.out.println(x);
		}
	}

	public void updateUserDetails(String id, String finalValuationId, String partyLabel) {
		try {
			//updated on 06-10-2012 
			System.out.println("@@@@New Id is@@@@" + id);
			System.out.println("final ID IS" + finalValuationId);
			System.out.println("Party ID IS" + partyLabel);
			new IGRSCommon().updateUserDetails(id, finalValuationId, partyLabel);
		} catch (Exception x) {
			System.out.println(x);
		}
	}

	/**
	 * @param String[]
	 * @return ArrayList
	 */
	public ArrayList getExemptionList(String[] exemptions) {
		ArrayList exemp = new ArrayList();
		if (exemptions != null && exemptions.length > 0) {
			for (int i = 0; i < exemptions.length; i++) {
				ExemptionDTO dto = new ExemptionDTO();
				dto.setExemType(exemptions[i]);
				exemp.add(dto);
			}
		}
		return exemp;
	}

	/**
	 * for getting property valuation flag corresponding to instrument id from
	 * db
	 * 
	 * @param int id
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropValRequiredFlag(int id) {
		return bd.getPropValRequiredFlag(id);
	}

	public String validateValuationId(DutyCalculationDTO dto1, InstrumentsDTO insdto, String language) throws Exception {
		if (dto1.getDeedId() == 1054) {
			ArrayList alreadyList = dto1.getExchangeList1();
			boolean flag = false;
			for (int i = 0; i < alreadyList.size(); i++) {
				DutyCalculationDTO ddto = (DutyCalculationDTO) alreadyList.get(i);
				if (dto1.getValuationId().equalsIgnoreCase(ddto.getValuationId())) {
					flag = true;
				} else {}
			}
			if (flag == false) {
				ArrayList alreadyList2 = dto1.getExchangeList2();
				for (int i = 0; i < alreadyList2.size(); i++) {
					DutyCalculationDTO ddto = (DutyCalculationDTO) alreadyList2.get(i);
					if (dto1.getValuationId().equalsIgnoreCase(ddto.getValuationId())) {
						flag = true;
					} else {}
				}
			}
			if (flag) {
				return "DUP";
			}
			ArrayList marketValueList = bd.getMarketValue(dto1.getValuationId(), language);
			if (marketValueList == null || marketValueList.size() == 0) {
				return "NA";
			} else {
				ArrayList subList = (ArrayList) marketValueList.get(0);
				DutyCalculationDTO dto = new DutyCalculationDTO();
				dto.setMarketValue((String) subList.get(0));
				DecimalFormat myformatter1 = new DecimalFormat("###.##");
				dto.setMarketValueDisplay(myformatter1.format(Double.parseDouble(dto.getMarketValue())));
				dto.setPropertyName((String) subList.get(1));
				String properyId = (String) subList.get(2);
				dto.setPropertyId(properyId);
				dto.setLandId((String) subList.get(5));
				String distId = (String) subList.get(3);
				if (distId == null || distId.equalsIgnoreCase("")) {
					return "SUB";
				} else if (!properyId.equalsIgnoreCase("3") && "Y".equalsIgnoreCase(dto1.getRinPustikaCheck())) {
					return "Agri";
				} else if (properyId.equalsIgnoreCase("3")) {
					if ("firstParty".equalsIgnoreCase(dto1.getExchangeParty())) {
						dto1.setRinPustikaVisible1("Y");
					} else {
						dto1.setRinPustikaVisible2("Y");
					}
				}
				String valuationId = bd.getValuationIdLsit(dto1.getValuationId(), properyId);
				String area = bd.getAgriArea(valuationId);
				if (area != null && !area.equalsIgnoreCase("")) {
					dto.setPropArea(Double.parseDouble(area));
				}
				dto.setValuationId(valuationId);
				if ("firstParty".equalsIgnoreCase(dto1.getExchangeParty())) {
					dto1.getExchangeList1().add(dto);
					dto1.setExchangeMV1(dto1.getExchangeMV1() + Double.parseDouble(dto.getMarketValue()));
				} else {
					dto1.getExchangeList2().add(dto);
					dto1.setExchangeMV2(dto1.getExchangeMV2() + Double.parseDouble(dto.getMarketValue()));
				}
				dto1.setExchangeParty("");
				return "A";
			}
		} else if (insdto.getInstId() == 2105 || insdto.getInstId() == 2106 || insdto.getInstId() == 2108 || insdto.getInstId() == 2184) {
			ArrayList alreadyList = dto1.getPropertyDetailsList();
			boolean flag = false;
			for (int i = 0; i < alreadyList.size(); i++) {
				DutyCalculationDTO ddto = (DutyCalculationDTO) alreadyList.get(i);
				if (dto1.getValuationId().equalsIgnoreCase(ddto.getValuationId())) {
					flag = true;
				} else {}
			}
			if (flag) {
				return "DUP";
			}
			ArrayList marketValueList = bd.getMarketValue(dto1.getValuationId(), language);
			if (marketValueList == null || marketValueList.size() == 0) {
				return "NA";
			} else {
				ArrayList subList = (ArrayList) marketValueList.get(0);
				DutyCalculationDTO dto = new DutyCalculationDTO();
				dto.setMarketValue((String) subList.get(0));
				DecimalFormat myformatter1 = new DecimalFormat("###.##");
				dto.setMarketValueDisplay(myformatter1.format(Double.parseDouble(dto.getMarketValue())));
				dto.setPropertyName((String) subList.get(1));
				String properyId = (String) subList.get(2);
				dto.setPropertyId(properyId);
				dto.setLandId((String) subList.get(5));
				dto.setAgriApplicableSubclauseId(getAgriApplicableSubClause((String) subList.get(6)));//added by roopam-14april15
				String distId = (String) subList.get(3);
				String buyer = (String) subList.get(4);
				if (distId == null || distId.equalsIgnoreCase("")) {
					return "SUB";
				} else if (!properyId.equalsIgnoreCase("3") && "Y".equalsIgnoreCase(dto1.getRinPustikaCheck())) {
					return "Agri";
				} else if ("N".equalsIgnoreCase(buyer)) {
					return "buyer";
				} else if (properyId.equalsIgnoreCase("3")) {
					dto1.setRinPustikaVisible("Y");
				}
				String valuationId = bd.getValuationIdLsit(dto1.getValuationId(), properyId);
				dto.setValuationId(valuationId);
				dto.setUserValueList(dto1.getUserValueList());
				dto1.getPropertyDetailsList().add(dto);
				dto1.setPartitionMV(dto1.getPartitionMV() + Double.parseDouble(dto.getMarketValue()));
				if (dto1.getMaxPartitionMv() <= Double.parseDouble(dto.getMarketValue())) {
					dto1.setMaxPartitionMv(Double.parseDouble(dto.getMarketValue()));
				}
				return "A";
			}
		} else {
			ArrayList alreadyList = dto1.getPropertyDetailsList();
			boolean flag = false;
			for (int i = 0; i < alreadyList.size(); i++) {
				DutyCalculationDTO ddto = (DutyCalculationDTO) alreadyList.get(i);
				if (dto1.getValuationId().equalsIgnoreCase(ddto.getValuationId())) {
					flag = true;
				} else {}
			}
			if (flag) {
				return "DUP";
			}
			ArrayList marketValueList = bd.getMarketValue(dto1.getValuationId(), language);
			if (marketValueList == null || marketValueList.size() == 0) {
				return "NA";
			} else {
				ArrayList subList = (ArrayList) marketValueList.get(0);
				DutyCalculationDTO dto = new DutyCalculationDTO();
				dto.setMarketValue((String) subList.get(0));
				DecimalFormat myformatter1 = new DecimalFormat("###.##");
				dto.setMarketValueDisplay(myformatter1.format(Double.parseDouble(dto.getMarketValue())));
				dto.setPropertyName((String) subList.get(1));
				String properyId = (String) subList.get(2);
				dto.setPropertyId(properyId);
				String distId = (String) subList.get(3);
				String landId = (String) subList.get(5);
				if (distId == null || distId.equalsIgnoreCase("")) {
					return "SUB";
				} else if (!properyId.equalsIgnoreCase("3") && "Y".equalsIgnoreCase(dto1.getRinPustikaCheck())) {
					return "Agri";
				} else if ("5".equalsIgnoreCase(landId)) {
					if (dto1.getDeedId() == 1092) // lease deed 
					{
						return "leaseun";
					}
				} else if (properyId.equalsIgnoreCase("3")) {
					dto1.setRinPustikaVisible("Y");
				}
				String valuationId = bd.getValuationIdLsit(dto1.getValuationId(), properyId);
				dto.setValuationId(valuationId);
				dto.setUserValueList(dto1.getUserValueList());
				//Added by ankit for plant and machinery - start
				double stampDutyOnMovProp = 0;
				double janpadDutyOnMovProp = 0;
				double municipalDutyOnMovProp = 0;
				double upkarDutyOnMovProp = 0;
				double regFeeOnMovProp = 0;
				if (insdto.getValueOfMovableProp() != null && !insdto.getValueOfMovableProp().isEmpty()) {
					if (Long.parseLong(insdto.getValueOfMovableProp()) > 0) {
						stampDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateStampDutyOnMovableProp(insdto, dto1, CommonConstant.STAMP_MOV_PROP));
						janpadDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateJanpadDutyOnMovableProp(insdto, dto1, CommonConstant.JANPAD_MOV_PROP));
						upkarDutyOnMovProp = new CalculateDuty().calculateUpkarDutyOnMovableProp(insdto, dto1, CommonConstant.UPKAR_MOV_PROP, stampDutyOnMovProp);
						//municipalDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateMunicipalDutyOnMovableProp(insdto, dto1, CommonConstant.MUNICIPAL_MOV_PROP));
						municipalDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateMunicipalDutyOnMovableProp(insdto, dto1, dto, CommonConstant.MUNICIPAL_MOV_PROP));
						regFeeOnMovProp = Double.parseDouble(new CalculateDuty().calculateRegFeeOnMovableProp(insdto, dto1, CommonConstant.REG_FEE_MOV_PROP));
						dto.setStampDutyOnMovProp(stampDutyOnMovProp);
						dto.setJanpadDutyOnMovProp(janpadDutyOnMovProp);
						dto.setUpkarDutyOnMovProp(upkarDutyOnMovProp);
						dto.setMunicipalDutyOnMovProp(municipalDutyOnMovProp);
						dto.setRegFeeOnMovProp(regFeeOnMovProp);
						dto.setValueOfMovableProp(insdto.getValueOfMovableProp());
						dto1.setTotalStampDutyOnMovProp(dto1.getTotalStampDutyOnMovProp() + stampDutyOnMovProp);
						dto1.setTotalJanpadDutyOnMovProp(dto1.getTotalJanpadDutyOnMovProp() + janpadDutyOnMovProp);
						dto1.setTotalUpkarDutyOnMovProp(dto1.getTotalUpkarDutyOnMovProp() + upkarDutyOnMovProp);
						dto1.setTotalMunicipalDutyOnMovProp(dto1.getTotalMunicipalDutyOnMovProp() + municipalDutyOnMovProp);
						dto1.setTotalRegFeeOnMovProp(dto1.getTotalRegFeeOnMovProp() + regFeeOnMovProp);
					}
				}
				//Added by ankit for plant and machinery - end
				String stampDuty = new CalculateDuty().calculateStampDuty(insdto, dto1, dto);
				dto.setStampDuty(Double.parseDouble(stampDuty));
				double upkarDuty = new CalculateDuty().calculateUpkar(insdto.getInstId(), Double.parseDouble(stampDuty));
				double paidRegFee = 0;
				double paidStampduty = 0;
				if (dto1.getAlreadyPaidRegFee() != null && !dto1.getAlreadyPaidRegFee().equalsIgnoreCase("")) {
					paidRegFee = Double.parseDouble(dto1.getAlreadyPaidRegFee());
				}
				if (dto1.getAlreadyPaidStampDuty() != null && !dto1.getAlreadyPaidStampDuty().equalsIgnoreCase("")) {
					paidStampduty = Double.parseDouble(dto1.getAlreadyPaidStampDuty());
				}
				double nagarpalika = new CalculateDuty().calculateMuncipalDuty(insdto, dto1, dto);
				//if(nagarpalika<=stamp)
				{
					dto.setNagarPalikaDuty(nagarpalika);
				}
				/*else
				{
					dto.setNagarPalikaDuty(stamp);	
				}*/
				double panchayat = new CalculateDuty().calculateJanPadDuty(insdto, dto1, dto);
				//if(panchayat<=stamp)
				{
					dto.setPanchayatDuty(panchayat);
				}
				/*else
				{
				dto.setPanchayatDuty(stamp);
				}*/
				double total = Double.parseDouble(stampDuty) + upkarDuty + panchayat + nagarpalika;
				dto.setTotal(total);
				String regFee = new CalculateRegFee().calculateRegFee(insdto, dto1, dto);
				if (((Double.parseDouble(regFee)) - paidRegFee) < 0) {
					dto.setPayableRegFee(0);
				} else {
					dto.setPayableRegFee((Double.parseDouble(regFee)) - paidRegFee);
				}
				if ((total - paidStampduty) < 0) {
					dto.setPayableStampDuty(0);
				} else {
					dto.setPayableStampDuty(total - paidStampduty);
				}
				DecimalFormat myformatter = new DecimalFormat("###.##");
				dto.setPremium(dto1.getPremium());
				dto.setAdditionalPremium(dto1.getAdditionalPremium());
				dto.setOtherCharges(dto1.getOtherCharges());
				dto.setDevelopmentCharges(dto1.getDevelopmentCharges());
				dto.setRentArrayList(dto1.getRentArrayList());
				dto.setUpkarDuty(upkarDuty);
				dto.setRegFee(Double.parseDouble(regFee));
				dto.setAlreadyPaidRegFee(dto1.getAlreadyPaidRegFee());
				dto.setAlreadyPaidStampDuty(dto1.getAlreadyPaidStampDuty());
				dto.setDashReg((myformatter.format(dto.getRegFee())));
				dto.setDashSatmp(myformatter.format(dto.getStampDuty()));
				dto.setDashMunicipal(myformatter.format(dto.getNagarPalikaDuty()));
				dto.setDashJanpad(myformatter.format(dto.getPanchayatDuty()));
				dto.setDashUpkar(myformatter.format(dto.getUpkarDuty()));
				dto.setDashTotal(myformatter.format(dto.getTotal()));
				dto.setDashPayTotal(myformatter.format(dto.getPayableStampDuty()));
				dto.setDashPayReg(myformatter.format(dto.getPayableRegFee()));
				dto.setPaidStamp(paidStampduty);
				dto.setPaidReg(paidRegFee);
				double govtValue = 0;
				if (dto1.getGovValue() != null && !dto1.getGovValue().equalsIgnoreCase("")) {
					govtValue = Double.parseDouble(dto1.getGovValue());
				}
				dto1.setTotalGovValue(dto1.getTotalGovValue() + govtValue);
				dto.setGovValue(dto1.getGovValue());
				//dto1.setTotalStampDuty(dto1.getTotalStampDuty() + Double.parseDouble(stampDuty));
				dto1.setTotalregFee(dto1.getTotalregFee() + Double.parseDouble(regFee));
				//dto1.setTotalUpkar(dto1.getTotalUpkar() + upkarDuty);
				dto1.setTotalNagarPalika(dto1.getTotalNagarPalika() + nagarpalika);
				dto1.setTotalPanchyat(dto1.getTotalPanchyat() + panchayat);
				dto1.setTotalPaidStamp(dto1.getTotalPaidStamp() + paidStampduty);
				dto1.setTotalPaidReg(dto1.getTotalPaidReg() + paidRegFee);
				dto1.setEntireTotal(dto1.getEntireTotal() + total);
				//dto1.setTotalPayableReg(dto1.getTotalPayableReg() + dto.getPayableRegFee());
				//change in reg fee by ankit 2275
				System.out.println("BO 2275 change inst id " + insdto.getInstId());
				if (insdto.getInstId() == 2275) {
					dto1.setTotalPayableReg(dto1.getTotalPayableReg() + dto.getPayableRegFee());
					if (dto1.getTotalPayableReg() > 100) {
						dto1.setTotalPayableReg(100.0);
					}
				} else {
					dto1.setTotalPayableReg(dto1.getTotalPayableReg() + dto.getPayableRegFee());
				}
				//change in stamp duty by ankit 2275
				if (insdto.getInstId() == 2275) {
					dto1.setTotalStampDuty(dto1.getTotalStampDuty() + Double.parseDouble(stampDuty));
					dto1.setTotalUpkar(dto1.getTotalUpkar() + upkarDuty);
					dto1.setTotalPayableStamp(dto1.getTotalPayableStamp() + dto.getPayableStampDuty());
					if (dto1.getTotalStampDuty() > 1000) {
						dto1.setTotalStampDuty(1000);
						dto1.setTotalUpkar(100);
						dto1.setTotalPayableStamp(dto1.getTotalStampDuty() + dto1.getTotalUpkar() + dto1.getTotalNagarPalika() + dto1.getTotalPanchyat());
					}
				} else {
					dto1.setTotalStampDuty(dto1.getTotalStampDuty() + Double.parseDouble(stampDuty));
					dto1.setTotalUpkar(dto1.getTotalUpkar() + upkarDuty);
					dto1.setTotalPayableStamp(dto1.getTotalPayableStamp() + dto.getPayableStampDuty());
				}
				//dto1.setTotalPayableStamp(dto1.getTotalPayableStamp() + dto.getPayableStampDuty());
				dto1.getPropertyDetailsList().add(dto);
				return "A";
			}
		}
	}

	public ArrayList getUserEnterableField(String deedId, String instId, String language) {
		ArrayList list = bd.getUserEnterableField(deedId, instId, language);
		ArrayList retList = null;
		if (list != null && list.size() > 0) {
			retList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				InstrumentsDTO instDTO = new InstrumentsDTO();
				ArrayList subList = (ArrayList) list.get(i);
				instDTO.setUserEnterableId((String) subList.get(0));
				instDTO.setUserEnterableName((String) subList.get(1));
				instDTO.setAsConsiderationAmountFlag((String) subList.get(2));
				instDTO.setInStampDutyAmountFlag((String) subList.get(3));
				instDTO.setUserEnterableStatus((String) subList.get(4));
				instDTO.setAlreadyPaidRegFeeFlag((String) subList.get(5));
				instDTO.setAlreadyPaidStampDutyFlag((String) subList.get(6));
				instDTO.setMovablePropFlag((String) subList.get(7)); //added by ankit for plant and machinery
				retList.add(instDTO);
			}
		}
		return retList;
	}

	public ArrayList getUserEnterableFieldEstamp(String deedId, String instId, String language) {
		ArrayList list = bd.getUserEnterableFieldEstamp(deedId, instId, language);
		ArrayList retList = null;
		if (list != null && list.size() > 0) {
			retList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				InstrumentsDTO instDTO = new InstrumentsDTO();
				ArrayList subList = (ArrayList) list.get(i);
				instDTO.setUserEnterableId((String) subList.get(0));
				instDTO.setUserEnterableName((String) subList.get(1));
				instDTO.setAsConsiderationAmountFlag((String) subList.get(2));
				instDTO.setInStampDutyAmountFlag((String) subList.get(3));
				instDTO.setUserEnterableStatus((String) subList.get(4));
				instDTO.setAlreadyPaidRegFeeFlag((String) subList.get(5));
				instDTO.setAlreadyPaidStampDutyFlag((String) subList.get(6));
				instDTO.setMovablePropFlag((String) subList.get(7)); //added by ankit for plant and machinery
				retList.add(instDTO);
			}
		}
		return retList;
	}

	public String getPropertyName(String propId, String language) {
		return bd.getPropertyName(propId, language);
	}

	public String getUpkarFlag(int instId) {
		return bd.getUpkarFlag(instId);
	}

	public String getUpkarValue() {
		return bd.getUpkarValue();
	}

	public String submitDutyDetails(DutyCalculationDTO dto1, InstrumentsDTO insDTO) {
		return bd.submitDutyDetails(dto1, insDTO);
	}

	public String getValuationIdAGri(String valId) {
		return bd.getValuationIdAGri(valId);
	}

	public String getmuniciplaFlag(int id) {
		return bd.getmuniciplaFlag(id);
	}

	public String getFamilyFlag(int id) {
		return bd.getFamilyFlag(id);
	}

	public String getDeedDiscription(int deedId, String language) {
		return bd.getDeedDiscription(deedId, language);
	}

	public String getInstDiscription(int deedId, String language) {
		return bd.getInstDiscription(deedId, language);
	}

	public double getExemptionPecentage(DutyCalculationDTO dto1, InstrumentsDTO instdto) {
		double stampexe = 0;
		double regexe = 0;
		ArrayList list = dto1.getExemptionDescpList();
		for (int i = 0; i < list.size(); i++) {
			DutyCalculationDTO dto = (DutyCalculationDTO) list.get(i);
			if (dto.getExmepApplicable().equalsIgnoreCase("S")) {
				double percnt = Double.parseDouble(dto.getExemppercent());
				if (dto.getExempId().equalsIgnoreCase("118")) {
					if (!exchangeExemption(dto1)) {
						percnt = 0;
					}
				}
				if (dto.getExempId().equalsIgnoreCase("131")//ews
						|| dto.getExempId().equalsIgnoreCase("132")//lig
						|| dto.getExempId().equalsIgnoreCase("133"))//mig
				{
					percnt = 0;
				}
				if (dto.getExempId().equalsIgnoreCase("112") && instdto.getInstId() == 2054) {
					percnt = 0;
				}
				//added by ankit as per dept. requirement
				if (dto.getExempId().equalsIgnoreCase("102") && instdto.getInstId() == 2091) {
					percnt = 0;
				}
				if (percnt >= stampexe) {
					stampexe = percnt;
				}
			}
		}
		return stampexe;
	}

	public double getExemptionPecentageRegFee(DutyCalculationDTO dto1, InstrumentsDTO instdto) {
		double regexe = 0;
		ArrayList list = dto1.getRegFeeExemptionDiscriptionList();
		for (int i = 0; i < list.size(); i++) {
			DutyCalculationDTO dto = (DutyCalculationDTO) list.get(i);
			if (dto.getExmepApplicable().equalsIgnoreCase("R")) {
				double percnt = Double.parseDouble(dto.getExemppercent());
				if (dto.getExempId().equalsIgnoreCase("81") && instdto.getInstId() == 2054) {
					percnt = 0;
				}
				if (percnt >= regexe) {
					regexe = percnt;
				}
			}
		}
		return regexe;
	}

	public boolean exchangeExemption(DutyCalculationDTO dto) {
		boolean flag = false;
		boolean agri = true;
		double area1 = 0;
		double area2 = 0;
		double mv1 = dto.getExchangeMV1();
		double mv2 = dto.getExchangeMV2();
		ArrayList list1 = dto.getExchangeList1();
		ArrayList list2 = dto.getExchangeList2();
		for (int i = 0; i < list1.size(); i++) {
			DutyCalculationDTO dto1 = (DutyCalculationDTO) list1.get(i);
			if (!"3".equalsIgnoreCase(dto1.getPropertyId())) {
				agri = false;
			} else if (!"5".equalsIgnoreCase(dto1.getLandId())) {
				agri = false;
			}
			area1 = area1 + dto1.getPropArea();
		}
		for (int i = 0; i < list2.size(); i++) {
			DutyCalculationDTO dto1 = (DutyCalculationDTO) list2.get(i);
			if (!"3".equalsIgnoreCase(dto1.getPropertyId())) {
				agri = false;
			} else if (!"5".equalsIgnoreCase(dto1.getLandId())) {
				agri = false;
			}
			area2 = area2 + dto1.getPropArea();
		}
		if (agri == false) {
			flag = false;
		} else {
			if ((area1 <= 2 && area2 <= 2) && (mv1 == mv2)) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public ArrayList cancellationCategoryList(String langauge) {
		ArrayList list = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			System.out.println("Before deed selection");
			ArrayList retList = bd.cancellationCategoryList(langauge);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					DutyCalculationDTO dto = new DutyCalculationDTO();
					dto.setCancellationCategoryId(lst.get(0).toString());
					dto.setCancellationCategoryName(lst.get(1).toString());
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	public ArrayList cancellationDeedList(String langauge) {
		ArrayList list = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			System.out.println("Before deed selection");
			ArrayList retList = bd.cancellationDeedList(langauge);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					DutyCalculationDTO dto = new DutyCalculationDTO();
					dto.setCancellationDeedId(lst.get(0).toString());
					dto.setCancellationDeedName(lst.get(1).toString());
					list.add(dto);
				}
			}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}

	public double minStampDuty(String deedId, String instId) {
		String fee = bd.minStampDuty(deedId, instId);
		if (fee == null || fee.equalsIgnoreCase("")) {
			return 0;
		} else {
			return Double.parseDouble(fee);
		}
	}

	public double minRegFee(String deedId, String instId) {
		String fee = bd.minRegFee(deedId, instId);
		if (fee == null || fee.equalsIgnoreCase("")) {
			return 0;
		} else {
			return Double.parseDouble(fee);
		}
	}

	public boolean partitionvalidation(ArrayList list) {
		boolean boo = false;
		for (int i = 0; i < list.size(); i++) {
			DutyCalculationDTO dto = (DutyCalculationDTO) list.get(i);
			if (!dto.getPropertyId().equalsIgnoreCase("3")) {
				boo = true;
			} else if (!dto.getLandId().equalsIgnoreCase("5")) {
				boo = true;
			} else if (!dto.getAgriApplicableSubclauseId().equalsIgnoreCase("5")) {//Subclause other than 4.1, 4.2, 4.3 and 4.4//added by roopam-14 april 15
				boo = true;
			}
		}
		return boo;
	}

	public String getCurrentYear() throws Exception {
		return bd.getcurrYear();
	}

	public String getCurrentDate() throws Exception {
		return bd.getcurrDate();
	}

	public boolean consenterInsertions(String regInitId) {
		boolean flag = false;
		boolean dutyFlag = new CalculateDuty().insertAllDutiesConsenter(regInitId);
		///boolean regFeeFlag=new CalculateRegFee().insertAllRegFeeConsenter(regInitId);
		if (dutyFlag) {
			flag = true;
		}
		return flag;
	}

	public String getAgriApplicableSubClause(String colonyId) throws Exception {
		return bd.getAgriApplicableSubClause(colonyId);
	}

	public String getPropType(String valId) throws Exception {
		return bd.getPropType(valId);
	}

	public String getRinPustikaNo(String valTxnId) {
		return bd.getRinPustikaNo(valTxnId);
	}
	/*public String getColonyId(String valTxnId)
	{
		return bd.getColonyId(valTxnId);
	}
	public ArrayList getKhasraListClr(String valTxnId)
	{
		return bd.getKhasraListClr(valTxnId);
	}*/
}
