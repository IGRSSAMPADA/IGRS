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



package com.wipro.igrs.newdutycalculation.bd;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.newdutycalculation.dao.DutyCalculationDAO;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;
import com.wipro.igrs.reginit.bd.RegCommonBD;





public class DutyCalculationBD {
	
	DutyCalculationDAO dao = null;
	private Logger logger = (Logger) Logger.getLogger(RegCommonBD.class);

	public DutyCalculationBD() {
		dao = new DutyCalculationDAO();
		
	}
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
	public ArrayList getInstruments(int[] deedType,String cancellationFlag,String module) throws Exception {
		return dao.getInstruments(deedType,cancellationFlag,module);
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
	
	/**
	 * for getting property valuation flag corresponding to instrument id from db
	 * @param int id
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropValRequiredFlag(int id){
		
		String instId="";
		
		try{
			instId=Integer.toString(id);
			
		}catch(ClassCastException ex){
			instId="0";
		}
		
		return dao.getPropValRequiredFlag(instId);
	}
	public ArrayList getMarketValue(String valId,String language)
	{
		return dao.getMarketValue(valId,language);
	}
	public String getValuationIdLsit(String valId,String propertyID)
	{
		String list= dao.getValuationIdLsit(valId,propertyID);
		
		return list;
	}
	public ArrayList getUserEnterableField(String deedId,String instId,String language)
	{
		return dao.getUserEnterableField(deedId, instId, language);
		
	}
	
	public ArrayList getUserEnterableFieldEstamp(String deedId,String instId,String language)
	{
		return dao.getUserEnterableFieldEstamp(deedId, instId, language);
		
	}
	public String getPropertyName(String propId, String language) {
		return dao.getPropertyNAme(propId,language);
	}
	public String getUpkarFlag(int instId) {
		return dao.getUpkarFlag(instId);
	}
	public String getUpkarValue() {
		return dao.getUpkarValue();
	}
	public String submitDutyDetails(DutyCalculationDTO dto1,InstrumentsDTO insDTO) {
		return dao.submitDutyDetails(dto1,insDTO);
	}
	public String getValuationIdAGri(String valId) {
		return dao.getValuationIdAGri(valId);

	}
	public String getmuniciplaFlag(int id) {
	return dao.getmuniciplaFlag(id);
	}
	public String getFamilyFlag(int id) {
		return dao.getFamilyFlag(id);
		}
	public ArrayList getInstLevelExemptions(int[] deedId,String language) {
		return dao.getInstLevelExemptionsNew(deedId,language);
	}
	public ArrayList getRegFeeExemptions(int[] deedId,String language) {
		return dao.getRegFeeExemptionsNew(deedId,language);
	}
	
	public ArrayList getSelectedMineralList(String language,String mineralSelected ) throws Exception {
		return dao.getSelectedMineralList(language,mineralSelected);
	}
	
	public String getDeedDiscription(int deedId, String language) {
		return dao.getDeedDiscription(deedId,language);
	}
	public String getInstDiscription(int deedId, String language) {
		return dao.getInstDiscription(deedId,language);
	}
	
	public ArrayList cancellationCategoryList(String langauge)
	{
		return dao.cancellationCategoryList(langauge);
	}
	public ArrayList cancellationDeedList(String langauge)
	{
		return dao.cancellationDeedList(langauge);
	}
	public ArrayList getDeedType(String option,String flag,String language,String module) {
		try {
			return dao.getDeedType(option,flag,language,module);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getAgriArea(String valuationId) {
		return dao.getAreaAgri(valuationId);
	}
	public String minStampDuty(String deedId, String instId) {
		return dao.minStampDuty(deedId,instId);
	}
	public String minRegFee(String deedId, String instId) {
		return dao.minRegFee(deedId,instId);
	}
	public ArrayList getDeedCategoryList(String langauge)
	{
		try {
			return dao.getDeedCategoryList(langauge);
		} catch (Exception e) {
			return null;
		}
	}
	public String getcurrYear() {
		return dao.getcurrYear();
	
	}
	public String getcurrDate() {
		return dao.getcurrDate();
	
	}
	
	public String getAgriApplicableSubClause(String colonyId) {
		return dao.getAgriApplicableSubClause(colonyId);
	
	}
	
	public String getPropType(String valId) {
		return dao.getPropType(valId);
	
	}
	public String getRinPustikaNo(String valTxnId)
	{
		return dao.getRinPustikaNo(valTxnId);
	}
	public String getColonyId(String valTxnId)
	{
		return dao.getColonyId(valTxnId);
	}
	public ArrayList getKhasraListClr(String valTxnId)
	{
		return dao.getKhasraListClr(valTxnId);
	}
}
