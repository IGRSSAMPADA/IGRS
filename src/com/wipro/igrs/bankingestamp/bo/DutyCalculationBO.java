/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  DutyCalculationAction.java 
 * Author      :  Madan Mohan 
 * Description :   14 jan 2008
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             Lavi Tripathi        05 jun 2013       changes as per new SRS requirements       
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.bankingestamp.bo;


import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.EStamp;
import com.newgen.burning.EStampPartyDetails;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.estamping.bd.DutyCalculationBD;
import com.wipro.igrs.estamping.bd.EstampingBD;
import com.wipro.igrs.estamping.dao.DutyCalculationDAO;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.estamping.dto.EstampDTO;
import com.wipro.igrs.estamping.form.DutyCalculationForm;
import com.wipro.igrs.payments.dao.PaymentDAO;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;


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
					//.setHdnAmount(lst.get(0).toString()
							//+"~"+lst.get(1).toString()+"~"+lst.get(2).toString()+"~"+lst.get(3).toString());
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
	public ArrayList getExemptions(int deedType, int instId
		) {
		logger.debug("new method");
		//SIMRAN
		/*ArrayList list = new ArrayList();
		try {
			DutyCalculationBD bd = new DutyCalculationBD();
			int[] deedId = new int[2];
			deedId[0] = deedType;
			deedId[1] = instId;
		
			
			ArrayList retList = bd.getExemptions(deedId);
			if(retList!=null) {
				for(int i = 0; i < retList.size(); i++){
					ArrayList lst = (ArrayList) retList.get(i);
					ExemptionDTO dto = new ExemptionDTO();
					dto.setExemId(lst.get(0).toString());
					dto.setExemType(lst.get(1).toString());
					dto.setExemDeedId(lst.get(0).toString()
							+"~"+lst.get(1).toString());
					dto.setHdnExempAmount(lst.get(0).toString());
					logger.debug("###"+lst.get(1).toString());
					list.add(dto);
				}
			}
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;*/
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
			DutyCalculationBD bd = new DutyCalculationBD();
			
			System.out.println("Before deed selection");
		//	ArrayList retList = common.getDeedType(option);
			ArrayList retList = bd.getDeedType();
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
			
			// modified by SIMRAN
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
	
	// modified by SIMRAN -- copied from duty calc action
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
		//double marketValue =dutyDTO.getBaseValue(); // commented by SIMRAN
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
	///commented by simran
	
	/*public void captureNonPropStampDetails(DutyCalculationDTO dutyDTO,
			InstrumentsDTO instDTO, ExemptionDTO exeDTO) {
		
		 	String firstName=dutyDTO.getFirstName();
		 	String middleName=dutyDTO.getMidName();
		 	String lastName=dutyDTO.getLastName();
		 	String exempId=exeDTO.getHdnExAmount();
			int age=dutyDTO.getAge();
			int deedId=dutyDTO.getDeedId();
			int instId=instDTO.getInstId();
			double stampDuty = dutyDTO.getStampDuty();
			double gramDuty = dutyDTO.getPanchayatDuty();
			double nagarDuty = dutyDTO.getNagarPalikaDuty();
			double upkar = dutyDTO.getUpkarDuty();
			double regFee = dutyDTO.getRegFee();
			double marketValue =dutyDTO.getBaseValue();
			double annualRent =dutyDTO.getAnnualRent();
			double total =dutyDTO.getTotal();
			double regPaid=dutyDTO.getRegPaid();
			double dutyPaid=dutyDTO.getDutyAlreadyPaid();
			
			try {
				//updated on 06-10-2012 
				System.out.println("@@@@45454545@@@@");
				System.out.println("Checking market value");
		//String dutyId=	new IGRSCommon().captureNonPropStampDetails(firstName,middleName,lastName,age,stampDuty,gramDuty,nagarDuty,upkar,regFee,marketValue,annualRent,total,deedId,instId,
				//exempId,regPaid,dutyPaid);
		//dutyDTO.setStampId(Integer.parseInt((dutyId)));
				System.out.println(marketValue);
				//System.out.println(" valuation id"+dutyId);

			} catch (Exception x) {
				System.out.println(x);
			}
			 
		
			
		}*/
	
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
	/**
	 *
	 * @return ArrayList
	 */
	public ArrayList getCountry(String language) {
		DutyCalculationBD bd = new DutyCalculationBD();
		ArrayList country =new ArrayList();
		try{
		country = bd.getCountry(language);
		
		}catch(Exception e){
		}
		return country;
	}
	/**
	 * @param String[]
	 * @return ArrayList
	 */
	public ArrayList getState(String cntryId,String language) {
		DutyCalculationBD bd = new DutyCalculationBD();
		ArrayList state =new ArrayList();
		try{
		state = bd.getState(cntryId,language);
		
		}catch(Exception e){
		}
		return state;
	}
	/**
	 * @param String[]
	 * @return ArrayList
	 */
	public ArrayList getDistrict(String stateId,String language) {
		DutyCalculationBD bd = new DutyCalculationBD();
		ArrayList district =new ArrayList();
		try{
			district = bd.getDistrict(stateId,language);
		
		}catch(Exception e){
		}
		return district;
	}
	/**
	 *
	 * @return ArrayList
	 */
	public ArrayList getPhotoId(String language) {
		DutyCalculationBD bd = new DutyCalculationBD();
		ArrayList photo =new ArrayList();
		try{
		photo = bd.getPhoto(language);
		}catch(Exception e){
		}
		return photo;
	}
	/**
	 
	 * @return ArrayList
	 */
	public ArrayList getAppellate(String language) {
		DutyCalculationBD bd = new DutyCalculationBD();
		ArrayList appellate =new ArrayList();
		try{
			appellate = bd.getAppellate(language);
		}catch(Exception e){
		}
		return appellate;
	}
	 /******************************************************************  
	  *   Method Name  :   insertTxn()
	  *   Arguments    :    Form 
	  *   Return       :   Boolean
	  *   Throws 	  :   NullPointer  or SQLException or Exception
	 *******************************************************************/  
	 public  boolean insertTxn (DutyCalculationForm form,String lang)throws NullPointerException,
	 SQLException,Exception{
		 DutyCalculationBD bd = new DutyCalculationBD();
	 	 boolean flg = false;
	 	 flg = bd.insertTxn(form,lang);
	 	 return flg;
	 }
	
	 /******************************************************************  
	  *   Method Name  :   inserteCode()
	  *   Arguments    :    Form 
	  *   Return       :   Boolean
	  *   Throws 	  :   NullPointer  or SQLException or Exception
	 *******************************************************************/  
	 public  boolean inserteCode (DutyCalculationForm form, DashBoardDTO dto)throws NullPointerException,
	 SQLException,Exception{
		 DutyCalculationBD bd = new DutyCalculationBD();
	 	 boolean flg = false;
	 	 flg = bd.inserteCode(form, dto);
	 	 return flg;
	 }
	
	 
	 /******************************************************************  
	  *   Method Name  :   updateTxn()
	  *   Arguments    :    Form 
	  *   Return       :   Boolean
	  *   Throws 	  :   NullPointer  or SQLException or Exception
	 *******************************************************************/  
	 public  boolean updateTxn (DutyCalculationForm form,String lang)throws NullPointerException,
	 SQLException,Exception{
		 DutyCalculationBD bd = new DutyCalculationBD();
	 	 boolean flg = false;
	 	 flg = bd.updateTxn(form,lang);
	 	 return flg;
	 }
	 public  boolean updateInitationLanguage(String regNo,String langauge )throws NullPointerException,
	 SQLException,Exception{
		 DutyCalculationBD bd = new DutyCalculationBD();
	 	 boolean flg = false;
	 	 flg = bd.updateInitationLanguage(regNo,langauge);
	 	 return flg;
	 }
	 
	 /**
		 * getCurrentYear
		 * for inserting registration initiation other property details in db.
		 * @param String
		 * @return boolean
		 * @author Aakriti
		 */
		public String getCurrentYear()throws
		Exception  
		{
			
			EstampingBD bd = new EstampingBD();
			String currDate=bd.getCurrDateTime();
			DateFormat formatter= new SimpleDateFormat("mm/dd/yyyy hh:mm:ss"); 
			Date date = (Date)formatter.parse(currDate);
			SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
			String currentYear = formatYear.format(date);
			System.out.println("year----------**********************>"+currentYear);
			
			return currentYear;
		}
		
		/**
		 * getCurrentYear
		 * for inserting registration initiation other property details in db.
		 * @param String
		 * @return boolean
		 * @author Aakriti
		 */
		public String getCurrentDate()throws
		Exception  
		{
			
			EstampingBD bd = new EstampingBD();
			String currDate=bd.getCurrDateTime();
			
			
			
			/*DateFormat formatter= new SimpleDateFormat("mm/dd/yyyy hh:mm:ss"); 
			Date date = (Date)formatter.parse(currDate);
			SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
			String currentYear = formatYear.format(date);
			System.out.println("year----------**********************>"+currentYear);
			
			*/return currDate;
		}
		
		/**
		 *
		 * @return ArrayList
		 */
		public ArrayList getPayDtls(String txnId) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls =new ArrayList();
			try{
				paydtls = bd.getPayDtls(txnId);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		
		
		public ArrayList getspDtls(String txnId,String language) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls =new ArrayList();
			try{
				paydtls = bd.getspDtls(txnId,language);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		
		/**
		 *
		 * @return ArrayList
		 */
		public ArrayList getAppDtls(String txnId,String language) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls =new ArrayList();
			try{
				paydtls = bd.getAppDtls(txnId,language);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		/**
		 *
		 * @return ArrayList
		 */
		public ArrayList getPartyDtls(String txnId,String language) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls =new ArrayList();
			try{
				paydtls = bd.getPartyDtls(txnId,language);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		public ArrayList getPartyAdoptDtls(String txnId,String language) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls =new ArrayList();
			try{
				paydtls = bd.getPartyAdoptDtls(txnId,language);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		/**
		 *
		 * @return ArrayList
		 */
		public ArrayList getEcodeDtls(String txnId,String language) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls =new ArrayList();
			try{
				paydtls = bd.getEcodeDtls(txnId,language);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		
		/**
		 *
		 * @return ArrayList
		 */
		public ArrayList getspbnkDtls(String txnId,String language) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls =new ArrayList();
			try{
				paydtls = bd.getspbnkDtls(txnId,language);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		
		/**
		 *
		 * @return ArrayList
		 */
		public ArrayList getDeedDtls(String txnId,String language) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList deeddtls =new ArrayList();
			try{
				deeddtls = bd.getDeedDtls(txnId,language);
			
			}catch(Exception e){
			}
			return deeddtls;
		}
		
		
		/**
		 *
		 * @return String
		 */
		public String getDuty(String txnId) {
			DutyCalculationBD bd = new DutyCalculationBD();
			String paydtls=null;
			try{
				paydtls = bd.getDuty(txnId);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		public String getLangauge(String txnId) {
			DutyCalculationBD bd = new DutyCalculationBD();
			String paydtls=null;
			try{
				paydtls = bd.getLangauge(txnId);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		public void getDutyDetails(String txnId, DutyCalculationForm form) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls=null;
			try{
				paydtls = bd.getDutyDetails(txnId);
				if(paydtls!=null)
				{
					for(int i=0;i<paydtls.size();i++)
					{
						ArrayList subList=(ArrayList) paydtls.get(i);
						form.setStamp((String) subList.get(1));
						form.setMuncipality((String) subList.get(2));
						form.setJanpad((String) subList.get(3));
						form.setUpkar((String) subList.get(4));
						double pay=Double.parseDouble((String) subList.get(0));
						double total=Double.parseDouble((String) subList.get(5));
						double exe=total-pay;
							form.setExemptedAmount(String.valueOf(exe));
						DecimalFormat myformatter = new DecimalFormat(
						"############.####");
						form.setStampDisplay(myformatter.format(Double.parseDouble(form.getStamp())));
						form.setMuncipalityDIspaly(myformatter.format(Double.parseDouble(form.getMuncipality())));
						form.setJanpadDisplay(myformatter.format(Double.parseDouble(form.getJanpad())));
						form.setUpkarDisplay(myformatter.format(Double.parseDouble(form.getUpkar())));
						form.setExemptedAmountDisplay(myformatter.format(exe));
						
					}
				}
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		public void getDutyDetailsInitiation(String txnId, EstampDTO estampDTO,String module) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls=null;
			try{
				paydtls = bd.getDutyDetailsInitation(txnId, module);
				if(paydtls!=null)
				{
					for(int i=0;i<paydtls.size();i++)
					{
						ArrayList subList=(ArrayList) paydtls.get(i);
						estampDTO.setStamp((String) subList.get(1));
						estampDTO.setMuncipality((String) subList.get(2));
						estampDTO.setJanpad((String) subList.get(3));
						estampDTO.setUpkar((String) subList.get(4));
						double pay=Double.parseDouble((String) subList.get(0));
						double total=Double.parseDouble((String) subList.get(5));
						double exe=total-pay;
						if("C".equalsIgnoreCase(module))
						{
							exe=0;
						}
						estampDTO.setExemptedAmount(String.valueOf(exe));
						DecimalFormat myformatter = new DecimalFormat(
						"############.####");
						if(exe<0)
						{
							exe=0;
						}
						if(Double.parseDouble(estampDTO.getStamp())<0)
						{
							estampDTO.setStamp("0");
						}
						if(Double.parseDouble(estampDTO.getMuncipality())<0)
						{
							estampDTO.setMuncipality("0");
						}
						if(Double.parseDouble(estampDTO.getJanpad())<0)
						{
							estampDTO.setJanpad("0");
						}
						if(Double.parseDouble(estampDTO.getUpkar())<0)
						{
							estampDTO.setUpkar("0");
						}
						
						estampDTO.setStampDisplay(myformatter.format(Double.parseDouble(estampDTO.getStamp())));
						estampDTO.setMuncipalityDIspaly(myformatter.format(Double.parseDouble(estampDTO.getMuncipality())));
						estampDTO.setJanpadDisplay(myformatter.format(Double.parseDouble(estampDTO.getJanpad())));
						estampDTO.setUpkarDisplay(myformatter.format(Double.parseDouble(estampDTO.getUpkar())));
						estampDTO.setExemptedAmountDisplay(myformatter.format(exe));
						
					}
				}
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		public void getDutyDetailsInitiation(String txnId, DutyCalculationForm estampDTO,String module) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls=null;
			try{
				paydtls = bd.getDutyDetailsInitation(txnId, module);
				if(paydtls!=null)
				{
					for(int i=0;i<paydtls.size();i++)
					{
						ArrayList subList=(ArrayList) paydtls.get(i);
						estampDTO.setStamp((String) subList.get(1));
						estampDTO.setMuncipality((String) subList.get(2));
						estampDTO.setJanpad((String) subList.get(3));
						estampDTO.setUpkar((String) subList.get(4));
						double pay=Double.parseDouble((String) subList.get(0));
						double total=Double.parseDouble((String) subList.get(5));
						double exe=total-pay;
						if("C".equalsIgnoreCase(module))
						{
							exe=0;
						}
						estampDTO.setExemptedAmount(String.valueOf(exe));
						DecimalFormat myformatter = new DecimalFormat(
						"############.####");
						if(exe<0)
						{
							exe=0;
						}
						if(Double.parseDouble(estampDTO.getStamp())<0)
						{
							estampDTO.setStamp("0");
						}
						if(Double.parseDouble(estampDTO.getMuncipality())<0)
						{
							estampDTO.setMuncipality("0");
						}
						if(Double.parseDouble(estampDTO.getJanpad())<0)
						{
							estampDTO.setJanpad("0");
						}
						if(Double.parseDouble(estampDTO.getUpkar())<0)
						{
							estampDTO.setUpkar("0");
						}
						
						estampDTO.setStampDisplay(myformatter.format(Double.parseDouble(estampDTO.getStamp())));
						estampDTO.setMuncipalityDIspaly(myformatter.format(Double.parseDouble(estampDTO.getMuncipality())));
						estampDTO.setJanpadDisplay(myformatter.format(Double.parseDouble(estampDTO.getJanpad())));
						estampDTO.setUpkarDisplay(myformatter.format(Double.parseDouble(estampDTO.getUpkar())));
						estampDTO.setExemptedAmountDisplay(myformatter.format(exe));
						
					}
				}
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		public boolean updateEstampFlag(String regInitId)
		{
			DutyCalculationBD bd = new DutyCalculationBD();
			return bd.updateEstampFlag(regInitId);
		}
		/**
		 *
		 * @return String
		 */
		public String getMainId(String txnId) {
			DutyCalculationBD bd = new DutyCalculationBD();
			String paydtls=null;
			try{
				paydtls = bd.getMainId(txnId);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		
		/**
		 *
		 * @return String
		 */
		public String getspName(String txnId) {
			DutyCalculationBD bd = new DutyCalculationBD();
			String paydtls=null;
			try{
				paydtls = bd.getspName(txnId);
			
			}catch(Exception e){
			}
			return paydtls;
		}
		public String getspLicenseNo(String txnId) {
			DutyCalculationBD bd = new DutyCalculationBD();
			String paydtls=null;
			try{
				paydtls = bd.getspLicenseNo(txnId);
			
			}catch(Exception e){
				logger.debug(e);
			}
			return paydtls;
		}
		
		
		public String moduleFlag(String estampCode)
		{
			DutyCalculationBD bd = new DutyCalculationBD();
			String flag=null;
			try{
				flag = bd.moduleFlag(estampCode);
			
			}catch(Exception e){
				logger.debug(e);
			}
			
			return flag;
			
		}
		public String getRegId(String estampCode)
		{
			DutyCalculationBD bd = new DutyCalculationBD();
			String flag=null;
			try{
				flag = bd.getRegId(estampCode);
			
			}catch(Exception e){
				logger.debug(e);
			}
			
			return flag;
			
		}
		
		
		/**
		 *
		 * @return ArrayList
		 */
		public ArrayList getruName(String txnId,String language) {
			DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList rudtls =new ArrayList();
			try{
				rudtls = bd.getruName(txnId,language);
			
			}catch(Exception e){
			}
			return rudtls;
		}
		/******************************************************************  
		  *   Method Name  :   insertPay()
		  *   Arguments    :    Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		 *******************************************************************/  
		 public  boolean insertPay(DutyCalculationForm form, DashBoardDTO dto)throws NullPointerException,
		 SQLException,Exception{
			 DutyCalculationBD bd = new DutyCalculationBD();
		 	 boolean flg = false;
		 	 flg = bd.insertPay(form, dto);
		 	 return flg;
		 }
		 
		 
		 /**
		    * Method 		: getRole ()
		    * Description	: getting role id based on the user id
		    * return Type  :  String
		    * 
		    */	 
		 public String getRole(String uid)throws Exception{	
			 String role=null;
			 PaymentDAO dao = new  PaymentDAO ();
			 role=dao.getRole(uid);
			 return role;
		 }
		 
		 public  boolean updateStatus (DutyCalculationForm form)throws Exception {
			 DutyCalculationBD bd = new DutyCalculationBD();
		 	 boolean flg = false;
		 	 flg = bd.updateStatus(form);
		 	 return flg;
		 }
		 
		 public String getEcode (String disttId, String amnt, String func_id, String purpose, EstampDTO estampDTO, String regInitId,String modFlag, String language) throws NullPointerException, SQLException, Exception
		 {
			 DutyCalculationBD bd = new DutyCalculationBD();
			 String ecodeTxnId = bd.getEcode(disttId, amnt, func_id, purpose, estampDTO, regInitId, modFlag,language);
			 return ecodeTxnId;
		 }
		 public String getEcode2 (String disttId, String amnt, String func_id, String purpose, EstampDTO estampDTO, String regInitId,String modFlag) throws NullPointerException, SQLException, Exception
		 {
			 DutyCalculationBD bd = new DutyCalculationBD();
			 String ecodeTxnId = bd.getEcode2(disttId, amnt, func_id, purpose, estampDTO, regInitId, modFlag);
			 return ecodeTxnId;
		 }
		 public String getBreifDocument (String id) throws NullPointerException, SQLException, Exception
		 {
			 DutyCalculationBD bd = new DutyCalculationBD();
			 String ecodeTxnId = bd.getBreifDocument(id);
			 return ecodeTxnId;
		 }
		 /*public String getEcodeRegComp (String disttId, String amnt, String func_id, String purpose, EstampDTO estampDTO, String regInitId) throws NullPointerException, SQLException, Exception
		 {
			 DutyCalculationBD bd = new DutyCalculationBD();
			 String ecodeTxnId = bd.getEcodeRegComp(disttId, amnt, func_id, purpose, estampDTO, regInitId);
			 return ecodeTxnId;
		 }*/
		
		 /******************************************************************  
		  *   Method Name  :   insertConsume()
		  *   Arguments    :    Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		 *******************************************************************/  
		 public  boolean insertConsume (DutyCalculationForm form, DashBoardDTO dto)throws NullPointerException,
		 SQLException,Exception{
			 DutyCalculationBD bd = new DutyCalculationBD();
		 	 boolean flg = false;
		 	 flg = bd.insertConsume(form, dto);
		 	 return flg;
		 }
		 public  boolean insertConsumeExp (DutyCalculationForm form, DashBoardDTO dto)throws NullPointerException,
		 SQLException,Exception{
			 DutyCalculationBD bd = new DutyCalculationBD();
		 	 boolean flg = false;
		 	 flg = bd.insertConsumeExp(form, dto);
		 	 return flg;
		 }
		public ArrayList getDetails(String userId, DashBoardDTO objDashBoardDTO1) {
			 DutyCalculationBD bd = new DutyCalculationBD();
			 ArrayList details = new ArrayList();
			 details = bd.getdetails(userId,objDashBoardDTO1);
			
			 ArrayList list = new ArrayList();

				if (details != null) {
					for (int i = 0; i <details.size(); i++) {
						ArrayList lst = (ArrayList) details.get(i);
						objDashBoardDTO1.setDistrictid((String) lst.get(0));
						objDashBoardDTO1.setDistrictname((String) lst.get(1));
						list.add(objDashBoardDTO1);
					}
				}
				return list;
		}
		public ArrayList getruDetails(String userId, DashBoardDTO objDashBoardDTO1,
				String txnId,String language) {
			 DutyCalculationBD bd = new DutyCalculationBD();
			 ArrayList details = new ArrayList();
			 details = bd.getrudetails(userId,objDashBoardDTO1,txnId,language);
				
			 ArrayList list = new ArrayList();

				if (details != null) {
					for (int i = 0; i <details.size(); i++) {
						ArrayList lst = (ArrayList) details.get(i);
						objDashBoardDTO1.setDistrictid((String) lst.get(0));
						objDashBoardDTO1.setDistrictname((String) lst.get(1));
						list.add(objDashBoardDTO1);
					}
				}
				return list;
		}
		public ArrayList getiuDetails(DashBoardDTO objDashBoardDTO1, String offcId,String language) {
			 DutyCalculationBD bd = new DutyCalculationBD();
			 ArrayList details = new ArrayList();
			 details = bd.getiudetails(offcId,objDashBoardDTO1,language);
				
			 ArrayList list = new ArrayList();

				if (details != null) {
					for (int i = 0; i <details.size(); i++) {
						ArrayList lst = (ArrayList) details.get(i);
						objDashBoardDTO1.setIuofficeid((String) lst.get(0));
						objDashBoardDTO1.setIuofficename((String) lst.get(1));
						objDashBoardDTO1.setDistrictid((String) lst.get(2));
						objDashBoardDTO1.setDistrictname((String) lst.get(3));
						list.add(objDashBoardDTO1);
					}
				}
				return list;
		}
		
		
		/******************************************************************  
		  *   Method Name  :   insertDocDetls()
		  *   Arguments    :    Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		 *******************************************************************/  
		 public  boolean insertDocDetls (DutyCalculationForm form)throws NullPointerException,
		 SQLException,Exception{
			 DutyCalculationBD bd = new DutyCalculationBD();
		 	 boolean flg = false;
		 	 flg = bd.insertDocDetls(form);
		 	 return flg;
		 }
		public ArrayList getiuDtls(String userId, String officeId,String language) {
			 DutyCalculationBD bd = new DutyCalculationBD();
			ArrayList paydtls =new ArrayList();
			try{
				paydtls = bd.getiuDtls(userId,officeId,language);
			
			}catch(Exception e){
			}
			return paydtls;
		}
	
		public String getEstampTxnId(String estampCode) throws Exception
		{
			DutyCalculationBD bd = new DutyCalculationBD();
			//EstampBO eBO = new EstampBO();
			String estampTxnId = bd.getEstampTxnId(estampCode);
			return estampTxnId;
			//
		}
		public String getEstampCodeType(String estampCode) throws Exception
		{
			DutyCalculationBD bd = new DutyCalculationBD();
			//EstampBO eBO = new EstampBO();
			String estampTxnId = bd.getEstampCode(estampCode);
			if(estampTxnId.substring(0,4).equalsIgnoreCase("0101"))
			{
				return "nonjud";
			}
			else
			{
				return "jud";
			}
		}
		public String generateEstampPDF(EstampDTO estampDTO,String language,String var,String regInitId) throws Exception
		{
			
			//String estampGenCert = new EstampBO().getCertChkDetails(estampDTO.getMainTxnId().toString());
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
	        ODServerDetails connDetails = new ODServerDetails();
	        Dataclass metaDataInfo = new Dataclass();
	        connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	        connDetails.setCabinetName(pr.getValue("CabinetName"));
	        connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	        connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	        connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	        connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	        connDetails.setIniPath("D:\\simran\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	        metaDataInfo.setUniqueNo(estampDTO.getEcode().toString());
	        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	        PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
	        String downloadPath=proper.getValue("igrs_upload_path");
			downloadPath=downloadPath+File.separator+"IGRS";
	        
	        String EstampPath = downloadPath+File.separator+estampDTO.getEcode().toString()+File.separator+"EStamp.PDF";
			File output;
			output = new File(EstampPath.toString());
			if (output.exists()) {
				logger.info("file already exists deleting....");
				output.delete();
			}
			if (output.getParentFile().exists() == false) {
				logger.info("Parent not found creating directory....");
				output.getParentFile().mkdirs();
			}
			EStamp estampObj = new EStamp();
	        ArrayList<EStampPartyDetails> eStampPartyList = new ArrayList();
	        estampObj.seteStampCode(estampDTO.getEcode());
	        DecimalFormat myformatter = new DecimalFormat(
			"############");
	        double stamp=Double.parseDouble(estampDTO.getStamp());
	        double municipal=Double.parseDouble(estampDTO.getMuncipality());
	        double janpad=Double.parseDouble(estampDTO.getJanpad());
	        double upkar=Double.parseDouble(estampDTO.getUpkar());
	        double exemp=Double.parseDouble(estampDTO.getExemptedAmount());
	        double total=Double.parseDouble(estampDTO.getDutyCalculationDTO().getTotalDisplay());
	        double sum=municipal+stamp+janpad+upkar+exemp;
	        if(sum<total)
	        {
	        	double balance=total-sum;
	        	double stamp1=stamp+balance;
	        	String stampDis=String.valueOf(stamp1);
	        	estampDTO.setStamp(stampDis);
	        }
	        estampDTO.setStampDisplay(myformatter.format(Double.parseDouble(estampDTO.getStamp())));
			estampDTO.setMuncipalityDIspaly(myformatter.format(Double.parseDouble(estampDTO.getMuncipality())));
			estampDTO.setJanpadDisplay(myformatter.format(Double.parseDouble(estampDTO.getJanpad())));
			estampDTO.setUpkarDisplay(myformatter.format(Double.parseDouble(estampDTO.getUpkar())));
			estampDTO.setExemptedAmountDisplay(myformatter.format(Double.parseDouble(estampDTO.getExemptedAmount())));
//	        estampObj.seteStampAmount("216250");
	        estampObj.setGovtStampDuty(estampDTO.getStampDisplay());
	        estampObj.setMunicipalityDuty(estampDTO.getMuncipalityDIspaly());
	        estampObj.setJanpadDuty(estampDTO.getJanpadDisplay());
	        estampObj.setExemptedAmount(estampDTO.getExemptedAmountDisplay());
	        estampObj.setUpkarAmount(estampDTO.getUpkarDisplay());
	        estampObj.seteStampType(estampDTO.getEstampType());
	        estampObj.setIssuedDate(estampDTO.getCurrentDate());
	        estampObj.setUserId(estampDTO.getUserName());
	        estampObj.setOffice(estampDTO.getOfficeName());
	        estampObj.setPlace(estampDTO.getIssuedPlace());
	        estampObj.setValidity(estampDTO.getEstampValidity());
	        estampObj.setDeedType((estampDTO.getDutyCalculationDTO().getDeedType()));
	        estampObj.setDeedInstrument(estampDTO.getDutyCalculationDTO().getInstDescription());
	        estampObj.setDeedPurpose(estampDTO.getEstampPurpose());
	        estampObj.setDeedDuration(estampDTO.getDeedDuration());
	       // estampObj.setDeedDuration("1234567");
	        estampObj.seteStampAmount(estampDTO.getDutyCalculationDTO().getTotalDisplay());
	        String deedContent=getDeedContent(regInitId,"R");
	        System.out.println(deedContent);
	        estampObj.setDeedContent(deedContent);
	        EStampPartyDetails party1 = new EStampPartyDetails();
	        party1.setName(estampDTO.getAppFirsName());
	        party1.setCountry(estampDTO.getAppCountryName());
	        party1.setState(estampDTO.getAppStateName());
	        party1.setDistrict(estampDTO.getAppDistrictName());   
	        party1.setAddress(estampDTO.getAppAddress());
	        party1.setPartyType(estampDTO.getApplicantTypeFlag());
	        party1.setNoOfPerson(estampDTO.getAppPersons());
	        eStampPartyList.add(party1);
	       
	        EStampPartyDetails party2 = new EStampPartyDetails();
	       if(estampDTO.getPartyTypeFlag()==null||estampDTO.getPartyTypeFlag().equalsIgnoreCase(""))
	       {
	    	   party2.setName("--");
		        party2.setCountry("--");
		        party2.setState(estampDTO.getPartyStateName());
		        party2.setDistrict(estampDTO.getPartyDistrictName());   
		        party2.setAddress("--");
		        party2.setPartyType("2");
		        party2.setNoOfPerson("--");
	       }
	       else
	       {
	        party2.setName(estampDTO.getPartyFirsName());
	        party2.setCountry(estampDTO.getPartyCountryName());
	        party2.setState(estampDTO.getPartyStateName());
	        party2.setDistrict(estampDTO.getPartyDistrictName());   
	        party2.setAddress(estampDTO.getPartyAddress());
	        party2.setPartyType(estampDTO.getPartyTypeFlag());
	        party2.setNoOfPerson(estampDTO.getPartyPersons());
	       }
	        
	      
	        eStampPartyList.add(party2);
	        if("Y".equalsIgnoreCase(estampDTO.getIsAdoption()))
	        {
	        	EStampPartyDetails party3 = new EStampPartyDetails();
		        party3.setName(estampDTO.getAdoptFirstName());
		        party3.setCountry(estampDTO.getAdoptCountryName());
		        party3.setState(estampDTO.getAdoptStateName());
		        party3.setDistrict(estampDTO.getAdoptDistrictName());   
		        party3.setAddress(estampDTO.getAdoptAddress());
		        party3.setAuthorizerName(estampDTO.getAdoptFatherName());
			    party3.setPartyType(estampDTO.getAdoptionTypeFlag());

		        
		        party3.setNoOfPerson(estampDTO.getAdoptNoPerson());
		        eStampPartyList.add(party3);
	        }
	        estampObj.setPartyList(eStampPartyList);
	        int resultStamp = -1;
	        PropertiesFileReader pr1 = PropertiesFileReader.getInstance("resources.igrs");
			
			String hindiFont=pr1.getValue("dms_hindi_font_path");
			String englishFont=pr1.getValue("dms_english_font_path");
			SealsBD sealBD=new SealsBD();
			boolean val=sealBD.validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
	        BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);
	        
	        String     outputPath = downloadPath+File.separator+estampDTO.getEcode().toString();
	      	
      		String dmsFolderName = "IGRS";
      		
      		String docPath = new EstampBO().getEstampDocRegDetails(regInitId);
      		estampObj.setMainDeedDocPath(docPath);
			/*if(!docPath.equalsIgnoreCase(""))
		      	{
				if("en".equalsIgnoreCase(language))
			       {  
			        resultStamp = burnObj.createEStamps(outputPath, var, "English", estampObj, "EStampPdf.pdf");
			       }
			       else
			       {
					     estampObj.seteStampType("गैर न्यायिक");

			    	   resultStamp = burnObj.createEStamps( outputPath,var, "Hindi", estampObj, "EStampPdf.pdf");
			       }
				int result = -1;
		      	outputPath = CommonConstant.DRIVE_PATH+File.separator+"upload"+File.separator+"IGRS"+File.separator+eForm.getEcode().toString();
		      	String	fileName = "EStamp.pdf";
		      	burnObj.mergeDocs(CommonConstant.DRIVE_PATH+File.separator+"upload"+File.separator+"IGRS"+File.separator+eForm.getEcode().toString(), outputPath+File.separator+"EStampPdf.pdf", docPath, "EStamp.PDF");
		      		
		      	}
			else*/
			{
				if("en".equalsIgnoreCase(language))
			       {  
					 estampObj.seteStampType("NON-JUDICIAL");
					resultStamp = burnObj.createEStamps(outputPath, var, "English", estampObj, "EStamp.PDF");
			       }
			       else
			       {
					     estampObj.seteStampType("गैर न्यायिक");

				     resultStamp = burnObj.createEStamps(outputPath,var, "Hindi", estampObj, "EStamp.PDF");
   
			       }
			}
			if(resultStamp!=0)
			{
				logger.debug("ERROR CODE:"+resultStamp);
				throw new Exception();
			}
				
			/*try
		      {
				ReadPropertiesFile prop = new ReadPropertiesFile();
					metaDataInfo.setUniqueNo(eForm.getEcode().toString());
						String	fileName = "EStamp.pdf";
							String path = outputPath+"/"+fileName;
							logger.debug("path^^^^^^^^^^^^^^^^^^^^^^^^^^^"+path);
							File file = new File(path);
							metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
							String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
							logger.debug("doc Id "+docId);
							if(docId != "-1")
								{
									// update generate cert status in table
									boolean flag = objEstampBO.updateCertificateGenerationChk(eForm.getMainTxnId().toString());
									
									
								}
								 // below code to view the doc
						        // set the http content type to "APPLICATION/OCTET-STREAM
		          			   response.setContentType("application/download");

		          			  
		          			   response.setHeader("Content-Disposition", "attachment; filename="
		          						+ URLEncoder.encode(path,"UTF-8"));
		          			   
		          			   File fileToDownload = new File(path);
		          			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
		          			   OutputStream out = response.getOutputStream();
		          			   byte[] buf = new byte[2048];

		          			   int readNum;
		          			   while ((readNum=fileInputStream.read(buf))!=-1)
		          			   {
		          			      out.write(buf,0,readNum);
		          			   }
		          			   fileInputStream.close();
		          			   out.close();
							
			}
			catch(Exception e)
			{
				
			}
*/			EstampPath=EstampPath;
			return EstampPath;
			}
			
	public String getDeedContent(String id,String moduleId)
	{
		DutyCalculationBD bd = new DutyCalculationBD();
		return bd.getDeedContent(id,moduleId);
	}
	public  boolean checkEstamp (String regTxnID,String mod_flag)throws Exception {
		 DutyCalculationBD bd = new DutyCalculationBD();
	 	 boolean flg = false;
	 	 flg = bd.checkEstamp(regTxnID,mod_flag);
	 	 return flg;
	 }
	public ArrayList getReprintStatus(String estampCodeId) {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list3=new ArrayList();
		ArrayList list= new ArrayList();
		return dao.getReprintStatus(estampCodeId);
		
	}
	public Boolean insertdata(String estampCodeId, String userId,
			String remarks) {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		return dao.insertdata(estampCodeId,userId,remarks);
	}
	public Boolean updatedata(String estampCodeId) {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		return dao.updatedata(estampCodeId);
	}
	public  boolean checkEstampValidity (String TxnID,String deedDraft)throws Exception {
		 DutyCalculationBD bd = new DutyCalculationBD();
	 	 boolean flg = false;
	 	 flg = bd.checkEstampValidity(TxnID,deedDraft);
	 	 return flg;
	 }
			/*
			String EstampPath = "D:/upload/IGRS"+eForm.getEcode().toString()+"/Estamp.PDF";
			File output;
			output = new File(EstampPath.toString());
			if (output.exists()) {
				logger.info("file already exists deleting....");
				output.delete();
			}
			if (output.getParentFile().exists() == false) {
				logger.info("Parent not found creating directory....");
				output.getParentFile().mkdirs();
			}
			OutputStream fos = new FileOutputStream(EstampPath, false);
			Document document = new Document(PageSize.A4, 20, 20, 20, 20);
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			document.open();

		Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
	      	row.setHeader(true);
	      	row.setColspan(22);
	      	
	      	Table estampCerti = new Table(22);
	      	estampCerti.setWidth(100);
	      	estampCerti.setPadding(3);
		
		PdfPTable table = new PdfPTable(18);
		table.setWidthPercentage(100);
		
		String path = request.getRealPath("/")+"images\\HeaderComp.jpg";
		logger.info("======>"+path);
		Image image1 = Image.getInstance(path);
		//image1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		
		
		PdfPCell imageHeader = new PdfPCell(image1,false);
		imageHeader.setColspan(18);
		imageHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		//imageHeader.setHorizontalAlignment(0);
		
		imageHeader.setBorder(Rectangle.NO_BORDER);
		//imageHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		table.addCell(imageHeader);
		
		estampCerti.addCell(row);
		
		Cell title = new Cell(new Phrase("Certificate of Stamp Duty", FontFactory.getFont(FontFactory.TIMES_ITALIC, 14, Font.BOLD)));
		title.setHorizontalAlignment(Element.ALIGN_CENTER);
		title.setLeading(20);
		title.setColspan(22);
		title.setBorder(Rectangle.NO_BORDER);
		estampCerti.addCell(title);
		estampCerti.setBorderWidth(2);
		estampCerti.setAlignment(1);
		
		estampCerti.addCell(row);
		
		Cell sectionheader=new Cell(new Phrase("E-Stamp Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		sectionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
	      	sectionheader.setColspan(22);
	      	sectionheader.setBorder(Rectangle.NO_BORDER);
	      	sectionheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      	estampCerti.addCell(sectionheader);
	      	estampCerti.setAlignment(2);
	      	
	      	Cell estamp_Code=new Cell(new Phrase("E-Stamp Code", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
	      	estamp_Code.setHeader(true);
	      	estamp_Code.setColspan(11);
	      	estampCerti.addCell(estamp_Code);
	      	
	      	Cell ecodeValue=new Cell(new Phrase(eForm.getEcode(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
	      	ecodeValue.setHeader(true);
	      	ecodeValue.setColspan(11);
	      	estampCerti.addCell(ecodeValue);
	      	
	      	Cell estampAmnt=new Cell(new Phrase("E-Stamp Amount", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
	      	estampAmnt.setHeader(true);
	    	estampAmnt.setColspan(11);
      	estampCerti.addCell(estampAmnt);
      	
      	Cell estampAmntValue=new Cell(new Phrase(eForm.getDutyCalculationDTO().getTotalDisplay(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	estampAmntValue.setHeader(true);
      	estampAmntValue.setColspan(11);
      	estampCerti.addCell(estampAmntValue);
      	
      	Cell estampType=new Cell(new Phrase("E-Stamp Type", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	estampType.setHeader(true);
      	estampType.setColspan(11);
	      	estampCerti.addCell(estampType);
	      	
	      	Cell estampTypeValue=new Cell(new Phrase(eForm.getEstampType(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
	      	estampTypeValue.setHeader(true);
	      	estampTypeValue.setColspan(11);
	      	estampCerti.addCell(estampTypeValue);
	      	
	      	Cell issueDate=new Cell(new Phrase("Issue Date & Time", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
	      	issueDate.setHeader(true);
	      	issueDate.setColspan(11);
      	estampCerti.addCell(issueDate);
      	
      	Cell issueDateValue=new Cell(new Phrase(eForm.getCurrentDate(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	issueDateValue.setHeader(true);
      	issueDateValue.setColspan(11);
      	estampCerti.addCell(issueDateValue);
      	
      	Cell issuer=new Cell(new Phrase("User ID/Issuer", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	issuer.setHeader(true);
      	issuer.setColspan(11);
	      	estampCerti.addCell(issuer);
	      	
	      	Cell issuerValue=new Cell(new Phrase(eForm.getUserName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
	      	issuerValue.setHeader(true);
	      	issuerValue.setColspan(11);
	      	estampCerti.addCell(issuerValue);
	      	
	      	Cell issueOffice=new Cell(new Phrase("SP/SRO/DRO/HO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
	      	issueOffice.setHeader(true);
	      	issueOffice.setColspan(11);
      	estampCerti.addCell(issueOffice);
      	
      	Cell issueOfficeValue=new Cell(new Phrase(eForm.getOfficeName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	issueOfficeValue.setHeader(true);
      	issueOfficeValue.setColspan(11);
      	estampCerti.addCell(issueOfficeValue);
      	
      	Cell place=new Cell(new Phrase("Place", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	place.setHeader(true);
      	place.setColspan(11);
	      	estampCerti.addCell(place);
	      	
	      	Cell placeValue=new Cell(new Phrase(eForm.getIssuedPlace(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
	      	placeValue.setHeader(true);
	      	placeValue.setColspan(11);
	      	estampCerti.addCell(placeValue);
	      	
	      	Cell validity=new Cell(new Phrase("Validity", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
	      	validity.setHeader(true);
	      	validity.setColspan(11);
      	estampCerti.addCell(validity);
      	
      	Cell validityValue=new Cell(new Phrase(eForm.getEstampValidity(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	validityValue.setHeader(true);
      	validityValue.setColspan(11);
      	estampCerti.addCell(validityValue);
      	
      	estampCerti.addCell(row);
      	
      	Cell sectionheader1=new Cell(new Phrase("Deed Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
      	sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
      	sectionheader1.setColspan(22);
      	sectionheader1.setBorder(Rectangle.NO_BORDER);
      	sectionheader1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      	estampCerti.addCell(sectionheader1);
	      	estampCerti.setAlignment(2);
      	
      	Cell deedType=new Cell(new Phrase("Deed Type", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	deedType.setHeader(true);
      	deedType.setColspan(11);
      	estampCerti.addCell(deedType);
      	
      	Cell deedTypeValue=new Cell(new Phrase(eForm.getDutyCalculationDTO().getDeedType(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	deedTypeValue.setHeader(true);
      	deedTypeValue.setColspan(11);
      	estampCerti.addCell(deedTypeValue);
      	
      	Cell deedInst=new Cell(new Phrase("Deed Instrument", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	deedInst.setHeader(true);
      	deedInst.setColspan(11);
      	estampCerti.addCell(deedInst);
      	
      	Cell deedInstValue=new Cell(new Phrase(eForm.getInstDTO().getInstType(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	deedInstValue.setHeader(true);
      	deedInstValue.setColspan(11);
      	estampCerti.addCell(deedInstValue);
      	
      	Cell exemption=new Cell(new Phrase("Exemption", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
      	exemption.setHeader(true);
      	exemption.setColspan(11);
      	estampCerti.addCell(exemption);
      	
      	Cell exemptionValue=new Cell(new Phrase(eForm.getEcode(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
      	exemptionValue.setHeader(true);
      	exemptionValue.setColspan(11);
      	estampCerti.addCell(exemptionValue);
      	
      	Cell purpose=new Cell(new Phrase("Purpose", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	purpose.setHeader(true);
      	purpose.setColspan(11);
      	estampCerti.addCell(purpose);
      	
      	Cell purposeValue=new Cell(new Phrase(eForm.getEstampPurpose(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	purposeValue.setHeader(true);
      	purposeValue.setColspan(11);
      	estampCerti.addCell(purposeValue);
      	
      	// Ecode duration field has to be given in the creation of E-stamp ------ yet to be done
      	Cell deedDuration=new Cell(new Phrase("Deed Duration", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	deedDuration.setHeader(true);
      	deedDuration.setColspan(11);
      	estampCerti.addCell(deedDuration);
      	
      	Cell deedDurationValue=new Cell(new Phrase(eForm.getDeedDuration(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	deedDurationValue.setHeader(true);
      	deedDurationValue.setColspan(11);
      	estampCerti.addCell(deedDurationValue);
      	//
      	estampCerti.addCell(row);
      	
      	Cell sectionheader2=new Cell(new Phrase("First Party Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
      	sectionheader2.setHorizontalAlignment(Element.ALIGN_CENTER);
      	sectionheader2.setColspan(22);
      	sectionheader2.setBorder(Rectangle.NO_BORDER);
      	sectionheader2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      	estampCerti.addCell(sectionheader2);
	      	estampCerti.setAlignment(2);
      	
      	Cell aplicantName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	aplicantName.setHeader(true);
      	aplicantName.setColspan(11);
      	estampCerti.addCell(aplicantName);
      	
      	Cell aplicantNameValue=new Cell(new Phrase(eForm.getAppNamedsply(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	aplicantNameValue.setHeader(true);
      	aplicantNameValue.setColspan(11);
      	estampCerti.addCell(aplicantNameValue);
      	
      	Cell applicantCountry=new Cell(new Phrase("Country", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	applicantCountry.setHeader(true);
      	applicantCountry.setColspan(11);
      	estampCerti.addCell(applicantCountry);
      	
      	Cell applicantCountryValue=new Cell(new Phrase(eForm.getAppCountryName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	applicantCountryValue.setHeader(true);
      	applicantCountryValue.setColspan(11);
      	estampCerti.addCell(applicantCountryValue);
      	
      	Cell applicantState=new Cell(new Phrase("State", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	applicantState.setHeader(true);
      	applicantState.setColspan(11);
      	estampCerti.addCell(applicantState);
      	
      	Cell applicantStateValue=new Cell(new Phrase(eForm.getAppStateName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	applicantStateValue.setHeader(true);
      	applicantStateValue.setColspan(11);
      	estampCerti.addCell(applicantStateValue);
      	
      	Cell applicantDistt=new Cell(new Phrase("District", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	applicantDistt.setHeader(true);
      	applicantDistt.setColspan(11);
      	estampCerti.addCell(applicantDistt);
      	
      	Cell applicantDisttValue=new Cell(new Phrase(eForm.getAppDistrictName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	applicantDisttValue.setHeader(true);
      	applicantDisttValue.setColspan(11);
      	estampCerti.addCell(applicantDisttValue);
      	
      	Cell applicantAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	applicantAddress.setHeader(true);
      	applicantAddress.setColspan(11);
      	estampCerti.addCell(applicantAddress);
      	
      	Cell applicantAddressValue=new Cell(new Phrase(eForm.getAppAddress(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	applicantAddressValue.setHeader(true);
      	applicantAddressValue.setColspan(11);
      	estampCerti.addCell(applicantAddressValue);
      	if(eForm.getAppFatherName()==null || eForm.getAppFatherName().toString().equalsIgnoreCase(""))
      	{
      	Cell appFatherName=new Cell(new Phrase("Authorized Person's Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
	    appFatherName.setHeader(true);
	    appFatherName.setColspan(11);
	    estampCerti.addCell(appFatherName);
      	
      	Cell appFatherNameValue=new Cell(new Phrase(eForm.getAppAuthPersonName().toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	appFatherNameValue.setHeader(true);
      	appFatherNameValue.setColspan(11);
      	estampCerti.addCell(appFatherNameValue);
      	}
      	else
      	{
      		Cell appFatherName=new Cell(new Phrase("Father's Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
	      	appFatherName.setHeader(true);
	      	appFatherName.setColspan(11);
	      	estampCerti.addCell(appFatherName);
	      	
	      	Cell appFatherNameValue=new Cell(new Phrase(eForm.getAppFatherName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
	      	appFatherNameValue.setHeader(true);
	      	appFatherNameValue.setColspan(11);
	      	estampCerti.addCell(appFatherNameValue);
      	}
      	
      	
      	Cell numberOfPersons=new Cell(new Phrase("Number of Persons", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	numberOfPersons.setHeader(true);
      	numberOfPersons.setColspan(11);
      	estampCerti.addCell(numberOfPersons);
      	
      	Cell numberOfPersonsValue=new Cell(new Phrase(eForm.getAppPersons(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	numberOfPersonsValue.setHeader(true);
      	numberOfPersonsValue.setColspan(11);
      	estampCerti.addCell(numberOfPersonsValue);
      	
      	estampCerti.addCell(row);
      	
      	Cell sectionheader3=new Cell(new Phrase("Second Party Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
      	sectionheader3.setHorizontalAlignment(Element.ALIGN_CENTER);
      	sectionheader3.setColspan(22);
      	sectionheader3.setBorder(Rectangle.NO_BORDER);
      	sectionheader3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      	estampCerti.addCell(sectionheader3);
	      	estampCerti.setAlignment(2);
      	
      	Cell name=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	name.setHeader(true);
      	name.setColspan(11);
      	estampCerti.addCell(name);
      	
      	Cell nameValue=new Cell(new Phrase(eForm.getPartyNamedsply(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	nameValue.setHeader(true);
      	nameValue.setColspan(11);
      	estampCerti.addCell(nameValue);
      	
      	Cell country=new Cell(new Phrase("Country", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	country.setHeader(true);
      	country.setColspan(11);
      	estampCerti.addCell(country);
      	
      	Cell countryValue=new Cell(new Phrase(eForm.getPartyCountryName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	countryValue.setHeader(true);
      	countryValue.setColspan(11);
      	estampCerti.addCell(countryValue);
      	
      	Cell state=new Cell(new Phrase("State", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	state.setHeader(true);
      	state.setColspan(11);
      	estampCerti.addCell(state);
      	
      	Cell stateValue=new Cell(new Phrase(eForm.getPartyStateName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	stateValue.setHeader(true);
      	stateValue.setColspan(11);
      	estampCerti.addCell(stateValue);
      	
      	Cell distt=new Cell(new Phrase("District", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	distt.setHeader(true);
      	distt.setColspan(11);
      	estampCerti.addCell(distt);
      	
      	Cell disttValue=new Cell(new Phrase(eForm.getPartyDistrictName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	disttValue.setHeader(true);
      	disttValue.setColspan(11);
      	estampCerti.addCell(disttValue);
      	
      	Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	address.setHeader(true);
      	address.setColspan(11);
      	estampCerti.addCell(address);
      	
      	Cell addressValue=new Cell(new Phrase(eForm.getPartyAddress(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	addressValue.setHeader(true);
      	addressValue.setColspan(11);
      	estampCerti.addCell(addressValue);
      	
      	if(eForm.getPartyFatherName()==null || eForm.getPartyFatherName().toString().equalsIgnoreCase(""))
      	{
      		Cell fatherName=new Cell(new Phrase("Authorized Person's Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
	      	fatherName.setHeader(true);
	      	fatherName.setColspan(11);
	      	estampCerti.addCell(fatherName);
	      	
      		Cell fatherNameValue=new Cell(new Phrase(eForm.getPartyAuthPersonName().toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
		    fatherNameValue.setHeader(true);
		    fatherNameValue.setColspan(11);
		    estampCerti.addCell(fatherNameValue);
      	
      	
      	}
      	else
      	{
      		Cell fatherName=new Cell(new Phrase("Father's Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
	      	fatherName.setHeader(true);
	      	fatherName.setColspan(11);
	      	estampCerti.addCell(fatherName);
	      	
	      	Cell fatherNameValue=new Cell(new Phrase(eForm.getPartyFatherName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
	      	fatherNameValue.setHeader(true);
	      	fatherNameValue.setColspan(11);
	      	estampCerti.addCell(fatherNameValue);
      	}
      	
      	
      	
      	Cell numberOfPerson=new Cell(new Phrase("Number of persons", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));	    			      
      	numberOfPerson.setHeader(true);
      	numberOfPerson.setColspan(11);
      	estampCerti.addCell(numberOfPerson);
      	
      	Cell numberOfPersonValue=new Cell(new Phrase(eForm.getPartyPersons(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));	    			      
      	numberOfPersonValue.setHeader(true);
      	numberOfPersonValue.setColspan(11);
      	estampCerti.addCell(numberOfPersonValue);
      	
      	estampCerti.addCell(row);
      	
      	Cell sectionheader4=new Cell(new Phrase("2D Bar Code", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
      	sectionheader4.setHorizontalAlignment(Element.ALIGN_CENTER);
      	sectionheader4.setColspan(22);
      	sectionheader4.setBorder(Rectangle.NO_BORDER);
      	sectionheader4.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      	estampCerti.addCell(sectionheader4);
	      	estampCerti.setAlignment(2);
	      	
	      	estampCerti.setCellsFitPage(true);
	      	//document.add(table);
	      	document.add(estampCerti);	
	      	document.close();
	      	//commented by simran
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"E-Stamp Certificate.pdf");
		response.setContentLength(baos.size());
		ServletOutputStream out = response.getOutputStream();
		baos.writeTo(out);
		out.flush();
	      	
	      	return EstampPath;
		*/
	
	
}
		
		

