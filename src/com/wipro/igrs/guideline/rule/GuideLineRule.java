
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: GuideLineRule.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING:	   16th May 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE RULES FOR GUIDELINE PREPARATION
 */


package com.wipro.igrs.guideline.rule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;


import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.util.PropertiesFileReader;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;

/**
 * @author NIHAR M.
 *
 */
public class GuideLineRule {

	private boolean error;
	private PropertiesFileReader pr;
	private Logger logger = (Logger) Logger.getLogger(GuideLineRule.class);
	/**
	 * @param error
	 */
	public void setError(boolean error) {
		this.error = error;
	}
	/**
	 * @return boolean
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param eForm
	 * @return
	 */
	public ArrayList checkApprovedValidation(GuidelinePreparationForm eForm) {
		ArrayList errorList = new ArrayList();
		boolean flag = false;
		
		

		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");

			GuidelineDTO wDTO = eForm.getGuideDTO();

			errorList.add(pr.getValue("error.header"));
			if( wDTO.getBasePeriodFrom().equals("") || wDTO.getBasePeriodTo().equals(""))
			{
				logger.debug("Base Period Error:  If Part");
				errorList.add(pr.getValue("error.basePeriods")); 
				flag = true;
			}if( wDTO.getDurationTo().equals("") || wDTO.getDurationFrom().equals(""))
			{
				logger.debug("Base Period Error:  If Part");
				errorList.add(pr.getValue("error.durationPeriod")); 
				flag = true;
			}

		} catch (Exception x) {
			logger.debug(""+x);
		}
		setError(flag);
		return errorList;
	}

	/**
	 * @param dFrom
	 * @param dTo
	 * @param fYear
	 * @param eForm
	 * @return ArrayList
	 */
	//commented by simran
	/*public ArrayList checkDurationDates(String dFrom, String dTo, String fYear, GuidelinePreparationForm eForm)
	{
		logger.debug("-----------------------Called:   checkDurationDates()-----------------------------");
		ArrayList errorList = new ArrayList();
		boolean flag = false;

		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");

			GuidelineDTO wDTO = eForm.getGuideDTO();
			errorList.add(pr.getValue("error.header"));

			if( wDTO.getBasePeriodFrom().equals("") || wDTO.getBasePeriodTo().equals("")){
				logger.debug("Base Period Error:  If Part");
				errorList.add(pr.getValue("error.basePeriods")); 
				flag = true;
			}
			if( "".equals(wDTO.getFinancialYear().trim())){
				logger.debug("Base Period Error:  If Part");
				errorList.add(pr.getValue("error.financialYear")); 
				flag = true;
			}

			SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.DATE_FORMAT);

			String bFrom = eForm.getGuideDTO().getBasePeriodFrom();
			String bTo = eForm.getGuideDTO().getBasePeriodTo();
			Date BF = sdf.parse(bFrom);
			Date BT = sdf.parse(bTo);

			long baseDateDiff = BT.getTime() - BF.getTime();
			logger.debug("baseDateDiff:-   "+baseDateDiff);
			logger.debug("Difference between the Base Periods:-      "+baseDateDiff/86400000 + " day(s)");

			String[] finYear = fYear.split("-");
			int finYearLast = Integer.parseInt(finYear[1]);

			String Date_fin_Last = "31/03/"+finYearLast;
			Date dfn = sdf.parse(Date_fin_Last);

			long baseDateDiff1 = dfn.getTime() - BT.getTime();
			long baseDateDiff11 = baseDateDiff1/86400000;
			long baseDateDiff2 = dfn.getTime() - BF.getTime();
			long baseDateDiff22 = baseDateDiff2/86400000;

			logger.debug("baseDateDiff11:-   "+baseDateDiff11);
			logger.debug("baseDateDiff22:-   "+baseDateDiff22);

			if(baseDateDiff11 < 0 || baseDateDiff22 < 0){
				logger.debug("(baseDateDiff1 < 0 || baseDateDiff2 < 0)");
				errorList.add(pr.getValue("error.basePeriodNotMoreThanYear")); 
				flag = true;
			}
			else{
				if(baseDateDiff <= 0){

					logger.debug("(baseDateDiff <= 0)");
					errorList.add(pr.getValue("error.validateBasePeriods")); 
					flag = true;
				}
			}

			int finYear1 = Integer.parseInt(finYear[0]);
			int finYear2 = Integer.parseInt(finYear[1]);
			logger.debug(""+finYear1);
			logger.debug(""+finYear2);
			String Date1 = "01/04/"+finYear1;
			String dFromDate = dFrom;
			String Date2 = "31/03/"+finYear2;
			String dToDate = dTo;
			Date d1 = sdf.parse(Date1);
			Date df = sdf.parse(dFromDate);
			logger.debug("From Date:- "+d1);
			logger.debug("Fixed One:- "+df);
			Date d2 = sdf.parse(Date2);
			Date dT = sdf.parse(dToDate);
			logger.debug("Fixed Two:-  "+d2);
			logger.debug("To Date:-  "+dT);
			long diff = df.getTime() - d1.getTime();
			System.out.println(diff/86400000 + " day(s)");
			long diff2 = d2.getTime() - dT.getTime();
			System.out.println(diff2/86400000 + " day(s)");
			long dateD = dT.getTime()-df.getTime();
			long dateDiff = dateD/86400000;
			logger.debug("dateDiff:-   "+dateDiff);

			long BF2 = (BF.getTime())/86400000;
			long DT2 = (dT.getTime())/86400000;

			if(BF2 >= DT2){
				logger.debug("Base Period From > Duration From. ");
				errorList.add(pr.getValue("error.baseFromdurationFromError")); 
				flag = true;
			}

			if(dateDiff >0 ){
				logger.debug(""+dateDiff);
				if(diff < 0 || diff2 <0){
					logger.debug("If Part");
					errorList.add(pr.getValue("error.durationDates")); 
					flag = true;
				}
			}
			else{
				logger.debug("dFrom > dTo");
				errorList.add(pr.getValue("error.fromLessToDurationDate"));
				flag = true;
			}

		} catch (Exception x) {
			logger.debug(""+x);
		}
		setError(flag);
		logger.debug(""+flag);
		return errorList;
	}*/
	
	// edited by simran
	
	public Date removeTime(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		return cal.getTime();
	}
	public GuidelinePreparationForm checkDurationDates(String dF, String dT, String fYear, String start, String end, GuidelinePreparationForm eForm)
	{
		logger.debug("-----------------------Called:   checkDurationDates()-----------------------------");
		ArrayList errorList = new ArrayList();
		boolean flag = false;
		eForm.setErrorMsg("");
		try {
			setError(false);
			pr = PropertiesFileReader.getInstance("resources.igrs");

			GuidelineDTO wDTO = eForm.getGuideDTO();
			

			/*if( wDTO.getBasePeriodFrom().equals("") || wDTO.getBasePeriodTo().equals("")){
				logger.debug("Base Period Error:  If Part");
				errorList.add(pr.getValue("error.basePeriods")); 
				flag = true;
			}
			if( "".equals(wDTO.getFinancialYear().trim())){
				logger.debug("Base Period Error:  If Part");
				errorList.add(pr.getValue("error.financialYear")); 
				flag = true;
			}*/

			SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
			SimpleDateFormat sdf2 = new SimpleDateFormat(CommonConstant.DATE_FORMAT3);  //to convert the format of date fetched from database(start and end)
			

			/*String baseFrom = eForm.getGuideDTO().getBasePeriodFrom();
			String baseTo = eForm.getGuideDTO().getBasePeriodTo();*/
			
			/*
			 * Converting from String to Date
			 * 
			 */
			/*Date bFrom = sdf.parse(baseFrom);
			Date bTo = sdf.parse(baseTo);*/
			
			Date dFrom=(Date)sdf.parse(dF);
			Date dTo=(Date)sdf.parse(dT);
			
			
			
			/*
			 * Converting the format of start and end dates
			 */
			Date finStart = sdf2.parse(start);
			String finStart2 = sdf.format(finStart);
			Date finStart3 = sdf.parse(finStart2);
			
			
			Date finEnd = sdf2.parse(end);
			String finEnd2 = sdf.format(finEnd);
			Date finEnd3 = sdf.parse(finEnd2);
			

			/*
			 * GETTING SYSTEM DATE
			 */
			Date sysDate=new Date();
			
			
			
			
			/*
			 * 	CONVERTING ALL DATES TO LONG
			 */
			
			/*long dFromFinal=(dFrom.getTime()/86400000);
			long dToFinal=(dTo.getTime()/86400000);
			long sysDateFinal=(sysDate.getTime()/86400000);
			long bFromFinal = (bFrom.getTime()/86400000);
			long bToFinal = (bTo.getTime()/86400000);
			long finStartFinal = (finStart.getTime()/86400000);
			long finEndFinal = (finEnd.getTime()/86400000);
			*/
			          
			
			
			
			/*
			 *  START OF VALIDATIONS ON DURATION FROM
			 */
			if(removeTime(sysDate).compareTo(removeTime(dFrom)) > 0)
			{
				 flag = true;
				//errorList.add(pr.getValue("error.fromDateLessThanSysDate"));
				eForm.setErrorMsg("From Date should not be less than Today's Date \n\n / दिनांक से फ़ील्ड् आज के दिनांक से कम नहीं होना चाहिए");
				logger.debug("From and system date");
				setError(flag);
				
			}
			else if(removeTime(dFrom).compareTo (removeTime(finStart3)) < 0)
			{
				flag = true;
				////errorList.add(pr.getValue("error.durationFromLessThanFinancial"));
				eForm.setErrorMsg("From Date Cannot be Less than 1 April of that financial Year \n\n / दिनांक से फ़ील्ड् उस् वित्तीय वर्ष के 1 अप्रैल से कम नहीं हो सकता");
				logger.debug("From and first April Of Financial Year");
				setError(flag);
			}
			
			else if(removeTime(dFrom).compareTo(removeTime(dTo)) > 0) 
				{
					logger.debug("dFrom > dTo");
					flag = true;
					//errorList.add(pr.getValue("error.fromLessToDurationDate"));
					eForm.setErrorMsg("From Date cannot be greater than To Date \n\n / दिनांक से फ़ील्ड् दिनांक तक फ़ील्ड् से बड़ा नहीं हो सकता");
					setError(flag);
				}
			 
			 /*
			  * END OF VALIDATION ON DURATION FROM
			  */
			 
			 
			 /*
			  * VALIDATIONS ON DURATION TO
			  */
			else if(removeTime(dTo).compareTo(removeTime(finEnd3)) > 0)
				{
					flag = true;
					//errorList.add(pr.getValue("error.durationToGreaterThanFinancial"));
					eForm.setErrorMsg("To Date cannot be greater than 31 March of that financial year\n\n / दिनांक तक फ़ील्ड् उस् वित्तीय वर्ष के 31 मार्च से बड़ा नहीं हो सकता");
					logger.debug("To and 31 March");
					setError(flag);
				}
			 
			 /*
			  * END OF VALIDATIONS ON DURATION TO
			  */
			 
			 
			 /*
			  * VALIDATION ON BASE FROM
			  */
			 /* if(removeTime(bFrom).compareTo(removeTime(bTo)) > 0){

				logger.debug("base date comparison");
				errorList.add(pr.getValue("error.baseFromGreaterThanBaseTo")); 
				flag = true;
				setError(flag);
			}*/
			/*
			 * END OF VALIDATION ON BASE FROM
			 */
			  
			  
			  
			  /*
			   * VALIDATION ON BASE TO
			   */
			/*if(removeTime(bTo).compareTo(removeTime(sysDate)) > 0){
				
				logger.debug("Base Period To Greater Than SysDate ");
				flag = true;
				errorList.add(pr.getValue("error.baseToGreaterThanSysDate")); 
				setError(flag);
				
			}*/
			/*
			 * END OF VALIDATIONS ON BASE TO
			 */
			
			
			logger.info("isError():" + isError());
				//if (isError())
					//errorList.add(0, pr.getValue("error.header"));
				//errorList.trimToSize();

		} catch (Exception x) {
			System.out.println("EXCEPTION"+x);
		}
		
		return eForm;
	}


	/**
	 * @param guideDTO
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList checkBasePeriods(GuidelineDTO guideDTO) throws Exception{

		ArrayList errorList = new ArrayList();
		boolean flag = false;

		if( guideDTO.getBasePeriodFrom().equals("") || guideDTO.getBasePeriodTo().equals(""))
		{
			logger.debug("Base Period Error:  If Part");
			errorList.add(pr.getValue("error.basePeriods")); 
			flag = true;
		}

		setError(flag);
		logger.debug(""+flag);
		return errorList;
	}



	
}
