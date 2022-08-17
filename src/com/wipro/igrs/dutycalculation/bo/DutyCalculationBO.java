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

package com.wipro.igrs.dutycalculation.bo;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.dutycalculation.bd.DutyCalculationBD;
import com.wipro.igrs.dutycalculation.constant.CommonConstant;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;
import com.wipro.igrs.propertyvaluation.dto.PropertyValuationDTO;


/**
 * @since 14 jan 2008
 * File Name: DutyCalculationBO.java
 * @author Madan Mohan
 * @version 1.0
 * Created on 14 jan 2008
 *
 */
public class DutyCalculationBO {

	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger.getLogger(DutyCalculationBO.class);
	
	/**
	 * @author Madan Mohan
	 */
	public DutyCalculationBO() {}
	/**
	 * @param deedType
	 * @return ArrayList
	 */
	public ArrayList getInstrument(int deedType, String languageLocale) {
		ArrayList list = new ArrayList();
		try {
			DutyCalculationBD bd = new DutyCalculationBD();
			int[] deedId = new int[1];
			deedId[0] = deedType;
			
			ArrayList retList = bd.getInstruments(deedId);
			if(retList!=null) {
				for(int i = 0; i < retList.size(); i++){
					ArrayList lst = (ArrayList) retList.get(i);
					InstrumentsDTO dto = new InstrumentsDTO();
					dto.setInstId(Integer.parseInt(lst.get(0).toString()));
					dto.setInstType(lst.get(1).toString());
					//dto.setLabelDisplay(lst.get(2).toString());
					//dto.setLabelAmountFlag(lst.get(3).toString());
					if(languageLocale.equalsIgnoreCase("en_US")){
					dto.setHdnAmount(lst.get(0).toString()
							+"~"+lst.get(1).toString()+"~"+lst.get(2).toString()+"~"+lst.get(3).toString());// LABEL VALUE
					}else{
						dto.setHdnAmount(lst.get(0).toString()
								+"~"+lst.get(1).toString()+"~"+lst.get(4).toString()+"~"+lst.get(3).toString());// LABEL VALUE
					}
					
					logger.debug(dto.getHdnAmount()); 
					list.add(dto);
				}
			}
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	/**
	 * 
	 * @param deedType
	 * @param instId
	 * @return ArrayList
	 */
	public ArrayList getExemptions(int deedType, int instId) {
		ArrayList list = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			 DBUtility	 dbUtil = new DBUtility();
			 ArrayList retList=new ArrayList();
			 String exemp=null ;
			 String exempLevel = common.getExemptionsLevel(deedType);
			 
			// commented by Lavi on 04th October 2013.
			/* String exempLevel = "SELECT exemption_level FROM IGRS_DEED_TYPE_MASTER "
				+"WHERE DEED_TYPE_ID="+deedType+"";*/
			  
			 logger.debug("Inside exempLevel " + deedType);
			  
				try {
					// commented by Lavi on 04th October 2013.
					
					/*dbUtil = new DBUtility();
					logger.debug("Inside exempLevel " + exempLevel);
					dbUtil.createStatement();
					exemp = dbUtil.executeQry(exempLevel);*/
					
					// end of commented code by Lavi on 04th October 2013.
					
					exemp = exempLevel;
					logger.debug("Inside exempLevel AND exemp IS " + exemp);
				} catch (Exception x) {
					logger.debug(x);
				} 
			 
				
				if(exemp.equalsIgnoreCase("D"))
			 	{
					System.out.println("INSIDE IF");
			 		int[] deedId = new int[1];
					deedId[0] = deedType;
					 retList = common.getInstLevelExemptions(deedId);
					if (retList != null) {
						for (int i = 0; i < retList.size(); i++) {
							ArrayList lst = (ArrayList) retList.get(i);
							ExemptionDTO dto = new ExemptionDTO();
							dto.setExemId(lst.get(0).toString());
							dto.setExemType(lst.get(1).toString());
							dto.setExemDeedId(lst.get(0).toString() + "~"
									+ lst.get(1).toString());
							System.out.println("###" + lst.get(1).toString());
							list.add(dto);
						}
					
			 	}
			 	}else
			 	{
			 		System.out.println("INSIDE ELSEIF");
			 		int[] deedId1 = new int[2];
					deedId1[0] = deedType;
					deedId1[1] = instId;	
					 retList = common.getExemptions(deedId1);
					if (retList != null) {
						for (int i = 0; i < retList.size(); i++) {
							ArrayList lst = (ArrayList) retList.get(i);
							ExemptionDTO dto = new ExemptionDTO();
							dto.setExemId(lst.get(0).toString());
							dto.setExemType(lst.get(1).toString());
							dto.setExemDeedId(lst.get(0).toString() + "~"
									+ lst.get(1).toString());
							System.out.println("###" + lst.get(1).toString());
							list.add(dto);
						}
			 	}
			 
			 	
			 	}
		} catch (Exception x) {
			System.out.println(x);
		}
		return list;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getDeed(String option) {
		ArrayList list = 
				new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			
			System.out.println("Before deed selection");
			ArrayList retList = common.getDeedType(option);
			if(retList!=null) {
				for(int i = 0; i < retList.size(); i++){
					ArrayList lst = (ArrayList) retList.get(i);
					DutyCalculationDTO dto = new DutyCalculationDTO();
					dto.setDeedId(Integer.parseInt(lst.get(0).toString()));
					dto.setDeedType(lst.get(1).toString());
					list.add(dto);
				}
			}
		}catch(Exception x) {
			System.out.println(x);
		}
		return list;
	}
	/**
	 * 
	 * @param dutyDTO
	 * @param instDTO
	 * @param exeDTO
	 * @return String
	 */
	public double getDutyCalculation(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO) {
		double calculatedDuty=0.0;	
		try {
		
			DutyCalculationBD bd = new DutyCalculationBD();
			logger.debug("@@@@22@@@@"
					+instDTO.getHdnAmount()
					+":"+dutyDTO.getDeedId());
			//changed  on 10-05-2012 for resolving null point error
	
		     int instId = instDTO.getInstId();
        	 double annualRent=dutyDTO.getAnnualRent();
			 double dutyPaid=dutyDTO.getDutyPaid();
		     int deedId =  dutyDTO.getDeedId();
			
			String exemption=exeDTO.getHdnExAmount();
			
		//	if(inst != null && inst.length == 2)  {
			//	logger.debug("Instrument type:-"+instId+":"+inst[1]);
				//instId = inst[0];
			//}
			logger.debug("The exemption value is for :"+exeDTO.getHdnExAmount());
			
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
			
			double baseVal=0.0;
			
			if(dutyDTO.getBaseValue()!=null && !dutyDTO.getBaseValue().equalsIgnoreCase("")){
				baseVal=Double.parseDouble(dutyDTO.getBaseValue());
			}
				calculatedDuty = 
					bd.getStampDuty(deedId, 
								instId, 
								exemption,
								baseVal,annualRent,dutyPaid);
			logger.debug("Duty :-"+calculatedDuty);
		}catch (Exception x) {
			logger.debug(x);
		}
		 	
		return calculatedDuty;
	}
	public double getRegistrationFee(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO) {
		double regFee=0.0 ;
		try {
			IGRSCommon common = new IGRSCommon();
			logger.debug("@@@@@@@@" + instDTO.getHdnAmount() + ":"
					+ dutyDTO.getDeedId());
			int instId = instDTO.getInstId();
			int deedId = dutyDTO.getDeedId();
			
		

			String exemption = exeDTO.getHdnExAmount();
			logger.debug("Exemption Type:-" + exemption);
			//double baseValue = dutyDTO.getBaseValue();
			
			
			double baseValue=0.0;
			
			if(dutyDTO.getBaseValue()!=null && !dutyDTO.getBaseValue().equalsIgnoreCase("")){
				baseValue=Double.parseDouble(dutyDTO.getBaseValue());
			}
			
			
			
			
			
			
			logger.debug("baseValue:-" + baseValue);
			
			// commented by Lavi for resolving bug of Lease deed
			//double duty =dutyDTO.getDuty();
			
			// Added by Lavi for resolving bug of Lease deed
			double duty =dutyDTO.getStampDuty();
			// end of addition by Lavi for resolving bug of Lease deed
			
			logger.debug("STAM DUTY:-" + duty);
				regFee = common.getRegistrstionFee(deedId, instId, null,duty,
						baseValue);

			
			logger.debug("Registration Fee :-" + regFee);
		} catch (Exception x) {
			logger.debug(x);
		}
		 
		return regFee;
	}
	
	public double[] getMunicipalDutyCalculation(DutyCalculationDTO dutyDTO,InstrumentsDTO instDTO, ExemptionDTO exeDTO
			)
	{	
		double calculatedDuty[]=new double[3];
		try {
	
	
		
		
		IGRSCommon common = new IGRSCommon();
		
			logger.debug("@@@@1545@@@@" + instDTO.getInstId() + ":"
					+ dutyDTO.getDeedId());
			//if (instDTO.getHdnAmount()!=null){
		//	String[] inst = instDTO.getHdnAmount().split("~");
				int instId =instDTO.getInstId();
	
		
	
		int deedId =dutyDTO.getDeedId();
		String marketVal="";
		double stampDuty=dutyDTO.getStampDuty();
		logger.debug("@@@@@@@STAMP DUTY @@@@@@@"+stampDuty);
	//	if (inst != null && inst.length == 2) {
		//	logger.debug("Instrument type:-" + instId + ":" + inst[1]);
			//instId = inst[0];
		//}


		String exemption = exeDTO.getHdnExAmount();
		logger.debug("Exemption Type:-" + exemption);
		//double marketValue =dutyDTO.getBaseValue();
		//double baseValue = dutyDTO.getBaseValue();
		
		double marketValue =0.0;
		double baseValue=0.0;
		
		if(dutyDTO.getBaseValue()!=null && !dutyDTO.getBaseValue().equalsIgnoreCase("")){
			baseValue=Double.parseDouble(dutyDTO.getBaseValue());
			marketValue=Double.parseDouble(dutyDTO.getBaseValue());
		}
		
		
		logger.debug("marketValue:-" + marketValue);
		logger.debug("baseValue:-" + baseValue);
		
		logger.debug(" CALLING MUNICIPAL BODY FUNCTIO0N FOR CALCULATING THE DATA");
		

			calculatedDuty = common.getMuncipalCalculatedDuty(deedId,instId,marketValue,stampDuty);

	

		logger.debug("Duty :-" + calculatedDuty);
	} catch (Exception x) {
		logger.debug(x);
	}
	return calculatedDuty;
	}
	/**
	 * @author Rishab 
	  */
	
	
	public void captureNonPropStampDetails(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO) {
		
		 	String firstName=dutyDTO.getFirstName();
		 	String middleName=dutyDTO.getMidName();
		 	String lastName=dutyDTO.getLastName();
		 	String exempId=exeDTO.getHdnExAmount();
		 	String dateofbirth=null;
			int deedId=dutyDTO.getDeedId();
			int instId=instDTO.getInstId();
			double stampDuty = dutyDTO.getStampDuty();
			double gramDuty = dutyDTO.getPanchayatDuty();
			double nagarDuty = dutyDTO.getNagarPalikaDuty();
			double upkar = dutyDTO.getUpkarDuty();
			double regFee = dutyDTO.getRegFee();
			//double marketValue =dutyDTO.getBaseValue();
			double annualRent =dutyDTO.getAnnualRent();
			double total =dutyDTO.getTotal();
			double regPaid=dutyDTO.getRegPaid();
			double dutyPaid=dutyDTO.getDutyAlreadyPaid();
			String userId=dutyDTO.getUserId();
			
			
			double marketValue =0.0;
			//double baseValue=0.0;
			
			if(dutyDTO.getBaseValue()!=null && !dutyDTO.getBaseValue().equalsIgnoreCase("")){
				//baseValue=Double.parseDouble(dutyDTO.getBaseValue());
				marketValue=Double.parseDouble(dutyDTO.getBaseValue());
			}
			
			if(dutyDTO.getDobDay()!=null)
			{
				System.out.println("inside first == dateofbirth "+dateofbirth);
				dateofbirth=dutyDTO.getDobDay()+"-"+dutyDTO.getDobMonth()+"-"+dutyDTO.getDobYear();
			}
			
			try {
				//updated on 06-10-2012 
				System.out.println("@@@@45454545@@@@");
				System.out.println("Checking market value");
		String dutyId=	new IGRSCommon().captureNonPropStampDetails(firstName,middleName,lastName,dateofbirth,stampDuty,gramDuty,nagarDuty,upkar,regFee,marketValue,annualRent,total,deedId,instId,
				exempId,regPaid,dutyPaid,userId);
		dutyDTO.setStampId(Integer.parseInt((dutyId)));
				System.out.println(marketValue);
				System.out.println(" valuation id"+dutyId);

			} catch (Exception x) {
				System.out.println(x);
			}
			 
		
			
		}
	
	public void updateUserDetails(String id,String finalValuationId,String partyLabel) {

		
		try {
			//updated on 06-10-2012 
			System.out.println("@@@@New Id is@@@@"+id);
			System.out.println("final ID IS"+finalValuationId);
			System.out.println("Party ID IS"+partyLabel);
			new IGRSCommon().updateUserDetails(id,finalValuationId,partyLabel);
			

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
		if(exemptions != null && exemptions.length >0 ) {
			for(int i = 0; i < exemptions.length; i++) {
				ExemptionDTO dto = new ExemptionDTO();
				dto.setExemType(exemptions[i]);
				exemp.add(dto);
				
			}
		}
		return exemp;
	}
	
}
