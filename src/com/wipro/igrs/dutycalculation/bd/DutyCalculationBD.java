/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  DutyCalculationBD.java 
 * Author      :  Madan Mohan 
 * Description :   
*/



package com.wipro.igrs.dutycalculation.bd;


import java.util.ArrayList;

import com.wipro.igrs.common.IGRSCommon;


/**
 *	@author Madan Mohan
 *	@see this class is used for Duty Calculation BD
 */



public class DutyCalculationBD {
	
	/**
	 *  @param  deedType[]
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getExemptions(int[] deedType) throws Exception {
		return new IGRSCommon().getExemptions(deedType);
	}
	/**
	 *  @param  deedType[]
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getInstruments(int[] deedType) throws Exception {
		return new IGRSCommon().getInstruments(deedType);
	}
	/**
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @param baseValue
	 * @return String
	 * @throws Exception
	 * @author Madan Mohan
	 * @see return Stamp Duty
	 */
	public double getStampDuty(int deed,
			 				   int instrument,
			 				   String exemption,
			 				   double baseValue,double annualRent,double dutyPaid) throws Exception{
		return new IGRSCommon().getStampDuty(deed, 
								instrument, 
								exemption, baseValue,annualRent,dutyPaid);
	}
	
}
