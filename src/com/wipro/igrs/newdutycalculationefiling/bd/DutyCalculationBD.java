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



package com.wipro.igrs.newdutycalculationefiling.bd;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.wipro.igrs.commonEfiling.IGRSCommon;
import com.wipro.igrs.newdutycalculationefiling.dao.DutyCalculationDAO;
import com.wipro.igrs.newdutycalculationefiling.form.DutyCalculationForm;
import com.wipro.igrs.propertyvaluationefiling.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.InstrumentsDTO;
import com.wipro.igrs.reginit.bd.RegCommonBD;





/**
 * @author PR836429
 * Preeti Kuralkar 20 May 2016
 *
 */
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
	public String getPropertyName(String propId, String language) {
		return dao.getPropertyNAme(propId,language);
	}
	public String getUpkarFlag(int instId) {
		return dao.getUpkarFlag(instId);
	}
	public String getUpkarValue() {
		return dao.getUpkarValue();
	}
	public String submitDutyDetails(DutyCalculationDTO dto1,InstrumentsDTO insDTO,String exedate,String exemption) {
		return dao.submitDutyDetails(dto1,insDTO,exedate,exemption);
	}
	
	//bank details 
	public boolean submitDutyDetails(DutyCalculationDTO dto1) {
		return dao.submitDutyDetails(dto1);
	}
	
	
	//final insert
	//bank details 
	public boolean submitDetails(DutyCalculationForm eForm,String tempEfileId,String userId) {
		return dao.submitDetails(eForm,tempEfileId,userId);
	}
	
	public boolean submitEfileNoDetails(String tempEfileId,String regTxnId1,String officeName,String officeId,String officeSRName,String officeSRId) {
		return dao.submitEfileNoDetails(tempEfileId,regTxnId1,officeName,officeId,officeSRName,officeSRId);
	}
	
	public boolean updateStausLast(String tempEfileId,String Finalpage03,String roleID,String officeSRId) {
		return dao.updateStausLast(tempEfileId,Finalpage03,roleID,officeSRId);
	}
	public boolean updateCount(String roleID,String officeSRId) {
		return dao.updateCount(roleID,officeSRId);
	}
	
	public boolean getpropValId(String tempEfileId) {
		return dao.getpropValId(tempEfileId);
	}
	
	
	public boolean getuploadpath(String maindutyId,String filePath) {
		return dao.getuploadpath(maindutyId,filePath);
	}
	
	public boolean getuploadpath1(String maindutyId,String filePath) {
		return dao.getuploadpath1(maindutyId,filePath);
	}
	
	
	public boolean getuploadformpath(String efileTxnId,String filePath) {
		return dao.getuploadformpath(efileTxnId,filePath);
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
	
	
	//changes to insert bank details
	
	//changes by preeti for estamp code 
	public boolean insertBankDtls(DutyCalculationDTO dutyDTO,String dutyId,String tempEfileId){
		return dao.insertBankDtls(dutyDTO,dutyId,tempEfileId);
		
	}
	//end
	
	//changes by preeti for estamp code 
	public boolean getestamp(String estampCode){
		return dao.getestamp(estampCode);
		
	}
	
	
	public boolean getestampDevactivate(String estampCode){
		return dao.getestampDevactivate(estampCode);
		
	}
	
	
	public boolean getstatusEstamp(String estampCode){
		return dao.getstatusEstamp(estampCode);
		
	}
	
	
	public boolean getstatusEstampType(String estampCode){
		return dao.getstatusEstampType(estampCode);
		
	}
	
	
	public boolean getstatusSourceType(String estampCode){
		return dao.getstatusSourceType(estampCode);
		
	}
	
	//changes by preeti for physical estamp code 
	public boolean getphysicalstamp(String seriesNumber,String stampvendor, String codeOfStamps, String stampdate,String deedId,String instId,String maindutyId,String regTxnId){
		return dao.getphysicalstamp(seriesNumber,stampvendor, codeOfStamps, stampdate,deedId,instId,maindutyId,regTxnId);
		
	}
	
	//changes for add more physical stamp code 
	
	public boolean addMorephysicalstamp(DutyCalculationForm eForm,String deedId,String instId,String regTxnId,String datephyestamp){
		return dao.addMorephysicalstamp(eForm,deedId,instId,regTxnId,datephyestamp);
		
	}
	
	public boolean setestamp(String estampCode,String maindutyId,String regTxnId){
		return dao.setestamp(estampCode,maindutyId,regTxnId);
		
	}
	
	
	public boolean setpurposeLoan(String purposeLoan,String maindutyId,String regTxnId){
		return dao.setpurposeLoan(purposeLoan,maindutyId,regTxnId);
		
	}
	
	
	public boolean setpurposeLoanNonPayment(String purposeLoan,String maindutyId){
		return dao.setpurposeLoanNonPayment(purposeLoan,maindutyId);
		
	}
	
	public boolean setpurposeLoanPhysical(String purposeLoan,String maindutyId,String regTxnId){
		return dao.setpurposeLoanPhysical(purposeLoan,maindutyId,regTxnId);
		
	}
	
	
	public boolean setpurposeLoanDuty(String purposeLoan,String maindutyId,String regTxnId){
		return dao.setpurposeLoanDuty(purposeLoan,maindutyId,regTxnId);
		
	}
	
	public boolean getPhysicalFlag(String tempefileId,String maindutyId){
		return dao.getPhysicalFlag(tempefileId,maindutyId);
		
	}
	
	
	public boolean updateStatus(String regTxnId,String statuspage01,String maindutyId,String deedId,String instId,String userid){
		return dao.updateStatus(regTxnId,statuspage01,maindutyId,deedId,instId,userid);
		
	}
	
	public String getdeedId(String maindutyId) throws Exception{
		return dao.getdeedId(maindutyId);
	}
	
	
	public String getValId(String tempEfileId) throws Exception{
		return dao.getValId(tempEfileId);
	}
	
	
	public String getValtehsilId(String valId) throws Exception{
		return dao.getValtehsilId(valId);
	}
	
	public String getInstId(String maindutyId) throws Exception{
		return dao.getInstId(maindutyId);
	}
	
	public boolean updateStatusBank(String regTxnId,String statuspage02){
		return dao.updateStatusBank(regTxnId,statuspage02);
		
	}
	
	
	public int getinstdashboardid(String tempEfileId){
		return dao.getinstdashboardid(tempEfileId);
	}
	
	
	public int getdeeddashboardid(String tempEfileId){
		return dao.getdeeddashboardid(tempEfileId);
	}
	
	public ArrayList getbankDistrict(String stateDisId) {
		return dao.getbankDistrict(stateDisId);
	}
	
	
	public ArrayList getbankDistrictHindi(String stateDisId) {
		return dao.getbankDistrictHindi(stateDisId);
	}
	
	
	public ArrayList getDistrictEfile(String tempEfileId) {
		return dao.getDistrictEfile(tempEfileId);
	}
	
	// Added by Gulrej on 5th Sept, 2017 // fetches property district id
	public ArrayList getDistrictOfProperty(String tempEfileId) {
		return dao.getDistrictOfProperty(tempEfileId);
	}
	
	
	public ArrayList getOfficeMapEfile(String officeId) {
		return dao.getOfficeMapEfile(officeId);
	}
	
	public String getRegTxnIdSeq() throws Exception {
		return dao.getRegTxnIdSeq();
	}
	
	
	//efile district
	
	public String getDistrict(String abc){
		return dao.getDistrict(abc);
	}
	
	//efile 
	
	public String getRegInitTxnIdSeq() throws Exception{
		String seq = null;

		Calendar cal = new GregorianCalendar();
		String mon = "";
		String day = "";
		String yr = "";
		int curr_month = cal.get(Calendar.MONTH) + 1;
		int curr_day = cal.get(Calendar.DATE);
		int curr_year = cal.get(Calendar.YEAR);
		
		mon = String.valueOf(curr_month);
		day = String.valueOf(curr_day);
		yr = String.valueOf(curr_year);
		
		
		seq = getRegTxnIdSeq();

		
		
		
		if (seq.length() == 1) {
			seq = "00000" + seq;
		} else if (seq.length() == 2) {
			seq = "0000" + seq;
		} else if (seq.length() == 3) {
			seq = "000" + seq;
		} else if (seq.length() == 4) {
			seq = "00" + seq;
		} else if (seq.length() == 5) {
			seq = "0" + seq;
		}
		
		
		
		return seq;
		
		
		
		
	}
	
	//end of changes 
	
	
	public String getRegInitTxnIdSeqEfile() throws Exception {
		String seq = null;

		Calendar cal = new GregorianCalendar();
		String mon = "";
		String day = "";
		String yr = "";
		int curr_month = cal.get(Calendar.MONTH) + 1;
		int curr_day = cal.get(Calendar.DATE);
		int curr_year = cal.get(Calendar.YEAR);
		// System.out.println("current month :- "+curr_month);

		mon = String.valueOf(curr_month);
		day = String.valueOf(curr_day);
		yr = String.valueOf(curr_year);
		yr = yr.substring(2);
		// System.out.println("current mon :- "+mon);
		// System.out.println("current day :- "+day);
		// System.out.println("current yr :- "+yr);

		// String serialnumber = getSPSerialNumber();

		// String serialnumber;

		// getting the month of recently saved licence number;
		// String[] previousDate =getPreviousRegIdDate();
		// System.out.println("previous dd :- "+previousDate[0]);
		// System.out.println("previous mm :- "+previousDate[1]);
		// System.out.println("previous yy :- "+previousDate[2]);

		// if(day.equals(previousDate[0]) &&
		// mon.equals(previousDate[1]) &&
		// yr.equals(previousDate[2])){

		seq = getRegTxnIdSeqEfile();

		// }
		// else
		// seq=getNewRegTxnIdSeq();

		if (seq.length() == 1) {
			seq = "00000" + seq;
		} else if (seq.length() == 2) {
			seq = "0000" + seq;
		} else if (seq.length() == 3) {
			seq = "000" + seq;
		} else if (seq.length() == 4) {
			seq = "00" + seq;
		} else if (seq.length() == 5) {
			seq = "0" + seq;
		}

		return seq;
	}
	
	

	//for first 3 digit seq
	public String getfSeq1() throws Exception {
		return dao.getfSeq();
	}
	
	//end
	
	//final efile number for last page 
	public String getFinalSeqEfile() throws Exception {
		String seq = null;

		Calendar cal = new GregorianCalendar();
		String mon = "";
		String day = "";
		String yr = "";
		int curr_month = cal.get(Calendar.MONTH) + 1;
		int curr_day = cal.get(Calendar.DATE);
		int curr_year = cal.get(Calendar.YEAR);
		// System.out.println("current month :- "+curr_month);

		mon = String.valueOf(curr_month);
		day = String.valueOf(curr_day);
		yr = String.valueOf(curr_year);
		yr = yr.substring(2);
		// System.out.println("current mon :- "+mon);
		// System.out.println("current day :- "+day);
		// System.out.println("current yr :- "+yr);

		// String serialnumber = getSPSerialNumber();

		// String serialnumber;

		// getting the month of recently saved licence number;
		// String[] previousDate =getPreviousRegIdDate();
		// System.out.println("previous dd :- "+previousDate[0]);
		// System.out.println("previous mm :- "+previousDate[1]);
		// System.out.println("previous yy :- "+previousDate[2]);

		// if(day.equals(previousDate[0]) &&
		// mon.equals(previousDate[1]) &&
		// yr.equals(previousDate[2])){

		seq = getRegTxnIdSeqEfileFinal();

		// }
		// else
		// seq=getNewRegTxnIdSeq();

		if (seq.length() == 1) {
			seq = "000000" + seq;
		} else if (seq.length() == 2) {
			seq = "00000" + seq;
		} else if (seq.length() == 3) {
			seq = "0000" + seq;
		} else if (seq.length() == 4) {
			seq = "000" + seq;
		} else if (seq.length() == 5) {
			seq = "00" + seq;
		}
		else if (seq.length() == 6) {
			seq = "0" + seq;
		}

		return seq;
	}
	
	
	public ArrayList getoffNameEfile(String district,String language) throws Exception {
		return dao.getoffNameEfile(district,language);
	}
	
	public String getoffNameEfileBank(String district,String language) throws Exception {
		return dao.getoffNameEfileBank(district,language);
	}
	
	
	public String getOffIdEfile(String officeName,String language) throws Exception {
		return dao.getOffIdEfile(officeName,language);
	}
	
	
	public String getpropertyFlag(String tempEfileId) throws Exception {
		return dao.getpropertyFlag(tempEfileId);
	}
	
	
	public String getpropValRequried(String propertyFlag) throws Exception {
		return dao.getpropValRequried(propertyFlag);
	}
	
	
	public String getestampAmt(String maindutyId) throws Exception {
		return dao.getestampAmt(maindutyId);
	}
	
	
	public String getAmt(String estampCode) throws Exception {
		return dao.getAmt(estampCode);
	}
	
	
	public String physicalAmt(String tempefileId) throws Exception {
		return dao.physicalAmt(tempefileId);
	}
	
	public String getRegTxnIdSeqEfile() throws Exception {
		return dao.getRegTxnIdSeqEfile();
	}
	
	public String getRegTxnIdSeqEfileFinal() throws Exception {
		return dao.getFinalSeqEfile();
	}
	
	
	//for first 3 digit seq
	public String getfSeqEfile() throws Exception {
		return dao.getfSeqEfile();
	}
	public boolean deleteStamp(String efiledeleteId, String deleteseriesNo) {
		// TODO Auto-generated method stub
		return dao.deleteStamp(efiledeleteId,deleteseriesNo);
	}
	public ArrayList getremainDetails(String efiledeleteId) {
		// TODO Auto-generated method stub
		return dao.getremainDetails(efiledeleteId);
	}
	public String getdutyCheck(String tempefileId, String maindutyId) {
		// TODO Auto-generated method stub
		return dao.getdutyCheck( tempefileId,  maindutyId);
	}
	public String getPropValRequiredFlag(String propertyoutMP, String dutyId1) {
		return dao.getPropValRequiredFlag( propertyoutMP,  dutyId1);
	}
	public boolean getcheckduty(String maindutyId) {
		return dao.getcheckduty( maindutyId);
	}
	public String getdeedType(int deed) {
		return dao.getdeedType( deed);
	}
	public String getInstType(int inst) {
		return dao.getInstType( inst);
	}
	/*public boolean updateCount(String roleID,String officeSRId,int count) {
		return dao.updateCount(roleID,officeSRId,count);
	}*/
	//end
	public String getPayableDuty(String mainDutyId){
		return dao.getPayableDuty(mainDutyId);
	}
	public String getRegTxnId(String maindutyid){
		return dao.getRegTxnId(maindutyid);
	}
}
