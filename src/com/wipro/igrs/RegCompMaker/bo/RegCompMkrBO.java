package com.wipro.igrs.RegCompMaker.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.RegCompMaker.bd.RegCompMkrBD;
import com.wipro.igrs.RegCompMaker.dao.RegCompMkrDAO;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;





/**
 * ===========================================================================
 * File           :   RegCompMkrBO.java
 * Description    :   Represents the RegCompMkr BO Class
 * @author        :   Ankita
 * Created Date   :   January 18, 2013
 * Updated Date			Version			UpdatedBy
 * 
 * ===========================================================================
 */




public class RegCompMkrBO{

	private  Logger logger = 
		(Logger) Logger.getLogger(RegCompMkrBO.class);
	RegCompMkrBD regCompBD   = new RegCompMkrBD();
	RegCompMkrDAO refDAO =null;
	RegCommonBO commonBo = new RegCommonBO();
	RegCommonBD commonBd = new RegCommonBD();
	public RegCompMkrBO() {
		
	}
	
	/**
	 * returns the given application details
	 * @param regNo
	 * @return ApplicantForm
	 */
		
	/*public RegCompleteMakerDTO getRegApplicationDetails(String regNumber) {
		RegCompleteMakerDTO dto=regCompBD.getRegApplicationDetails(regNumber);
		return dto ;
	}*/

	public ArrayList getComplList() {
		refDAO=new RegCompMkrDAO();
		return refDAO.getComplList();
	}

	public ArrayList getCompDeedList(String regNumber) throws Exception {
		return regCompBD.getCompDeedList(regNumber);
	}

	public ArrayList getDeedType() {
		return regCompBD.getDeedType();
	}

	public ArrayList getPropertyListByRegId(String regNumber) {
		refDAO=new RegCompMkrDAO();
		return refDAO.getPropertyListByRegId(regNumber);
	}
	/**
	 * 
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public String[] getDeedInstId1(String appId) throws Exception {
		RegCompMkrBD commonBd= new RegCompMkrBD();
		ArrayList list=commonBd.getDeedInstId1(appId);
		String[] newArray=new String[5];
		
		if(list != null && list.size()>0){
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		String[] array=str.split(",");
		int size=array.length;
		
		newArray[0]=array[0];                       //deed id
		newArray[1]=array[1];                       //instrument id
		
		/*String var="";
		for(int i=2;i<array.length-1;i++){
		var=var+array[i]+"-";
		}*/
		RegCommonBO bo =new RegCommonBO();
		newArray[2]=bo.getExemptionIDList(appId);      //exemptions
		
		
		
		newArray[3]=array[2];                       //adjudication id
		newArray[4]=array[3];                       //cancellation deed radio
		
		return newArray;	
		}
		else
		{
			return null;
		}
	}
	/**
	 * 
	 * @param appId
	 * @return
	 * @throws Exception
	 */
public String[] getAdjudicatedDutyDetls(String appId) throws Exception {
		RegCompMkrBD commonBd= new RegCompMkrBD();
		String dutyListArr[]=null;
		ArrayList list=commonBd.getAdjudicatedDutyDetls(appId);
		
		if(list!=null && !(list.size()<1)){
		String dutyList=list.toString();
		dutyList=dutyList.substring(2, dutyList.length()-2);
		dutyListArr=dutyList.split(",");
		}
		return dutyListArr;
	}

/**
 * landConfirmationScreen
 * for landing on confirmation screen from dashboard
 * @param RegCommonForm
 * @return void
 * @author ROOPAM
 */
public void landConfirmationScreen(RegCommonForm regForm,String languageLocale) throws Exception {
	   //add status for confirmation screen
	
	regForm.setMapPropertyTransParty(new HashMap());
	 
	commonBo.getCancellationLabel(Integer.toString(regForm.getDuty_txn_id()),regForm,languageLocale);
	 
	String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
	String purpose=commonBo.getEStampPurpose(regForm.getHidnRegTxnId());    
	
	if(purpose!=null && !purpose.equalsIgnoreCase("")){
		regForm.setPurpose(purpose.replace(RegInitConstant.SPLIT_CONSTANT, ","));
	}else{
		regForm.setPurpose("");
	}
	
		
	if(deedInstArray!=null && deedInstArray.length>0){
		
		
		if(deedInstArray[3].trim()!=null && !deedInstArray[3].trim().equalsIgnoreCase("")){
			
			if(deedInstArray[3].trim().equalsIgnoreCase("A")){
				regForm.setFromAdjudication(1);
			}else{
				regForm.setFromAdjudication(0);
			}
		}else{
			regForm.setFromAdjudication(0);
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
			regForm.setCancelDeedRadio(Integer.parseInt(deedInstArray[4].trim()));
			}
		
		if(Integer.parseInt(deedInstArray[6].trim())==0){
			regForm.setDeedStat("4");
			
			regForm.setDeedPath("");
			regForm.setDeedContents(null);
			
		}else{
			regForm.setDeedStat("5");
			regForm.setDeedDraftId(deedInstArray[6].trim());
			//set deed draft path and contents in form
			regForm.setDeedPath(deedInstArray[8].trim());
			regForm.setDeedContents(DMSUtility.getDocumentBytes(deedInstArray[8].trim()));
			
			
			
			//set draft id here in variable
		}
		
	}
	
	commonBo.getExtraDetls(regForm,languageLocale);
	
	
	
	//NumberFormat obj=new DecimalFormat("#0.00");
	
	String dutyListArr[];
	if(regForm.getInitiateAdjuApp()==0){
		dutyListArr=getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
    	regForm.setAdjuRemarks("");
	}
		else{
				dutyListArr=commonBo.getDutyDetlsAdjuForConfirmation(regForm.getHidnRegTxnId());	
				regForm.setAdjuRemarks(commonBo.getRemarks(regForm.getHidnRegTxnId()));
				//get adjudication remarks here...
		}
	int numberOfProperties;
	
	regForm.setTotalPayableAmount("0");
	double totalPaidAmt=0;
	ArrayList paidAmtList=commonBo.getAllPaidAmounts(regForm.getHidnRegTxnId());
	for(int i=0;i<paidAmtList.size();i++){
		ArrayList row_list=new ArrayList();
		row_list=(ArrayList)paidAmtList.get(i);
		String amnts=row_list.toString();
		amnts=amnts.substring(1, amnts.length()-1);
		//logger.debug("paid amount list-->"+amnts);
		String amntsArr[]=amnts.split(",");
		if(amntsArr[1].trim().equalsIgnoreCase("1")){
		totalPaidAmt=totalPaidAmt+Double.parseDouble(amntsArr[0].trim());
		}else{
			//fee-ANUJ
		}
	}
	regForm.setTotalPaidAmount(BigDecimal.valueOf(totalPaidAmt).toPlainString());
	//ANUJ
	
	if(regForm.getPvReqFlag().equalsIgnoreCase("Y") || regForm.getPropReqFlag().equalsIgnoreCase("Y")){
		
		
		ArrayList propertyIdList=new ArrayList();
		propertyIdList=commonBo.getPropertyIdMarketVal(regForm.getHidnRegTxnId());
		regForm.setShareOfPropSize(0);
		
		
			
		/*if(regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
			ArrayList transPartyDets=getTransPartyDetsNonPropDeed(regForm.getHidnRegTxnId(),regForm.getCommonDeed(),languageLocale, regForm.getDeedID(), regForm.getInstID());
			regForm.setTransPartyListPVDeed(transPartyDets);
			regForm.setPropListPVDeed(getPropertyListPVDeed(propertyIdList));
		}else*/{
			regForm.setTransPartyListPVDeed(null);
			regForm.setPropListPVDeed(null);
			
			HashMap propMap =new HashMap();
			propMap=regForm.getMapPropertyTransParty();
			double totalMarketVal=0;
			numberOfProperties=propertyIdList.size();
			
			if(numberOfProperties>0)
			{
			for(int i=0;i<propertyIdList.size();i++){
				
				ArrayList row_list=new ArrayList();
				row_list=(ArrayList)propertyIdList.get(i);
				String propIds=row_list.toString();
				propIds=propIds.substring(1, propIds.length()-1);
				
				String propId[]=propIds.split(",");
				
				if(regForm.getPvReqFlag().equalsIgnoreCase("Y")){
				if(!propId[1].trim().equalsIgnoreCase("") && !propId[1].trim().equals(null) && !propId[1].trim().equalsIgnoreCase("null")){
				totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
				}else{
					totalMarketVal=0;
				}
				}
				//Updated by Rakesh for PartyPropMappingDisplay: Start
    			ArrayList transPartyDets=null;
    			String clr_flag=commonBo.getClrFlagByPropId(propId[0].trim());
    			if(clr_flag !=null && !clr_flag.isEmpty()){
    				if(clr_flag.equalsIgnoreCase("Y")){
    				transPartyDets=commonBo.getTransPartyDetsCLR(propId[0].trim(),regForm.getHidnRegTxnId(),languageLocale,regForm.getDeedID(),regForm.getInstID(),regForm);
    				}else{            					
    			
    				transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),languageLocale,regForm.getDeedID(),regForm.getInstID(),regForm);
    				}
    				
    			}
                else{
    				transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),languageLocale,regForm.getDeedID(),regForm.getInstID(),regForm);
                }
    			
    			//Updated by Rakesh for PartyPropMappingDisplay: End
				
				
		    	//ArrayList transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),languageLocale, regForm.getDeedID(), regForm.getInstID(),regForm);
				
				if(propId[0].trim().length()==15){
					propId[0]="0"+propId[0].trim();
				}
				
				
				
				if(regForm.getPvReqFlag().equalsIgnoreCase("Y")){
					String pv=BigDecimal.valueOf(Double.parseDouble(propId[1].trim())).toPlainString();
				propMap.put(propId[0].trim()+","+pv, transPartyDets);
				}
				else{
					propMap.put(propId[0].trim(), transPartyDets);   // without market value where valuation is not required
				}
				
				
				
				
			}
			//double finalMVDouble=Double.parseDouble(finalMV);
			//finalMVDouble=Math.ceil(finalMVDouble);
			String totalMarketValString=BigDecimal.valueOf(totalMarketVal).toPlainString();
			regForm.setTotalMarketValue(totalMarketValString);
			regForm.setMapPropertyTransParty(propMap);
			
		}else{//for partition and poa deeds where property details will be captured based on user selection
			
			regForm.setMapPropertyTransParty(null);
			regForm.setParticularCount(0);
			ArrayList transPartyDets=commonBo.getTransPartyDetsNonPropDeed(regForm.getHidnRegTxnId(),0,languageLocale, regForm.getDeedID(), regForm.getInstID());
			regForm.setTransPartyListNonPropDeed(transPartyDets);
		
			
		}
			
		}
		
	}else{
		
		//check whether particular present.
		ArrayList particluarList=commonBo.getParticularList(regForm.getHidnRegTxnId());
		HashMap propMap =new HashMap();
		propMap=regForm.getMapPropertyTransParty();
		//regForm.setParticularCount(particluarList.size());
		
		if(particluarList!=null && particluarList.size()>0){
			regForm.setParticularCount(particluarList.size());
	    		for(int i=0;i<particluarList.size();i++){
					CommonDTO dto=new CommonDTO();
					dto=(CommonDTO)particluarList.get(i);
		    	ArrayList transPartyDets=commonBo.getTransPartyDetsForPaticular(dto.getId(),regForm.getHidnRegTxnId(),languageLocale, regForm.getDeedID(), regForm.getInstID(),regForm);
				propMap.put(dto.getId()+","+dto.getName(), transPartyDets);
			}
	    		regForm.setMapPropertyTransParty(propMap);
		}else{
			regForm.setParticularCount(0);
			ArrayList transPartyDets=commonBo.getTransPartyDetsNonPropDeed(regForm.getHidnRegTxnId(),regForm.getCommonDeed(),languageLocale, regForm.getDeedID(), regForm.getInstID());
			regForm.setTransPartyListNonPropDeed(transPartyDets);
		}
		
	}
	
	/*if(dutyListArr!=null && dutyListArr[5]!=null){
		if(!dutyListArr[5].trim().equalsIgnoreCase("") && !dutyListArr[5].trim().equalsIgnoreCase("null")){
		regForm.setTotalPayableAmount(BigDecimal.valueOf(Double.parseDouble(dutyListArr[5].trim())).toPlainString());
		}else{
			regForm.setTotalPayableAmount("0.0");
		}}else{
			regForm.setTotalPayableAmount("0.0");
		}*/
	
	if(dutyListArr!=null && dutyListArr[11]!=null){
		if(!dutyListArr[11].trim().equalsIgnoreCase("") && !dutyListArr[11].trim().equalsIgnoreCase("null")){
		regForm.setTotalPayableAmount(BigDecimal.valueOf(Double.parseDouble(dutyListArr[11].trim())).toPlainString());
		}else{
			regForm.setTotalPayableAmount("0.0");
		}}else{
			regForm.setTotalPayableAmount("0.0");
		}
	
	//ANUJ -- 11 -> 10
		
		//below code for retrieving estamp code if total payable amount is more than zero
		//if(Double.parseDouble(regForm.getTotalPayableAmount())>0.0)
		//{
			String eCode=commonBo.getEstampCode(regForm.getHidnRegTxnId());
			
			if(eCode!=null && !eCode.equalsIgnoreCase("")){
				regForm.setRegInitEstampCode(eCode);	
			}
			else{
				regForm.setRegInitEstampCode(null);
			}
			
			
		//}
		double balance=Double.parseDouble(regForm.getTotalPayableAmount())-Double.parseDouble(regForm.getTotalPaidAmount());
		regForm.setTotalBalanceAmount(BigDecimal.valueOf(balance).toPlainString());
		
		if(Double.parseDouble(regForm.getTotalBalanceAmount())<=0){
			regForm.setPaymentCompleteFlag(1);
			//regForm.setRegInitEstampCode(null);
		}else{
			//regForm.setRegInitEstampCode(null);
		}
		
		// ANUJ - BALANCE
		
		commonBo.setDutyInForm(regForm,dutyListArr,languageLocale);
	
		if((regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE ||
				regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV) && regForm.getPvReqFlag().equalsIgnoreCase("Y")){
			
			//String finalMV=getExchangeDeedFinalMV(regForm.getHidnRegTxnId());
			String finalMV=commonBo.getExchangeDeedFinalMV(Integer.toString(regForm.getDuty_txn_id()));
			
			//String FinalMarketValue=propBO.updateFinalMarketValue(dto,propForm,language,userId);
			double finalMVDouble=Double.parseDouble(finalMV);
			//finalMVDouble=Math.ceil(finalMVDouble);
			finalMV=BigDecimal.valueOf(finalMVDouble).toPlainString();
			
			
			
		regForm.setExchangeDeedMrktValue(finalMV);
		}
		
		regForm.setRegInitPaymntTxnId(null);
	

	//get all user enterable fields here...
		
		
		commonBo.getAllUserEnterables(regForm,languageLocale);
		
		
		/*String userTypeId=getUserTypeId(regForm.getHidnUserId());
		
		if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) ||
				userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)){
			
			request.setAttribute("loginUserCheck", "Y");
			
		}
		*/
		
	
	
}
/*public void landConfirmationScreen(RegCommonForm regForm,String language) throws Exception {	   //add status for confirmation screen
	
	regForm.setMapPropertyTransParty(new HashMap());
	 
	//String adjuFlag="";
	 
	String[] deedInstArray=getDeedInstId1(regForm.getHidnRegTxnId());
	String purpose=commonBo.getEStampPurpose(regForm.getHidnRegTxnId());    
	//String purpose="";
	if(purpose!=null && !purpose.equalsIgnoreCase("")){
		regForm.setPurpose(purpose.replace(RegInitConstant.SPLIT_CONSTANT, ","));
	}else{
		regForm.setPurpose("");
	}
	
		
	if(deedInstArray!=null && deedInstArray.length>0){
		
		//request.setAttribute("deedId", deedInstArray[0].trim());
		//request.setAttribute("instId", deedInstArray[1].trim());
		regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
		regForm.setCommonDeed(commonBo.getCommonDeedFlag(deedInstArray[0].trim()));   // setting common deed flag
		regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
		if(deedInstArray[2].trim().equals("-") || deedInstArray[2].trim().equalsIgnoreCase("")){
		regForm.setExmpID("");
		regForm.setHdnExAmount("");
		}else{
			regForm.setExmpID(deedInstArray[2].trim());
			//regForm.setHdnExAmount(deedInstArray[2].trim());
		}
		if(deedInstArray[3].trim()!=null && !deedInstArray[3].trim().equalsIgnoreCase("")){
			//adjuFlag=1;
			if(deedInstArray[3].trim().equalsIgnoreCase("R")){
				regForm.setFromAdjudication(1);
			}else{
				regForm.setFromAdjudication(0);
			}
		}else{
			regForm.setFromAdjudication(0);
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
			regForm.setCancelDeedRadio(Integer.parseInt(deedInstArray[4].trim()));
			}
		
	}else {
		//request.setAttribute("deedId", 0);
		//request.setAttribute("instId", 0);
		regForm.setDeedID(0);
		regForm.setInstID(0);
		regForm.setExmpID("");
		regForm.setHdnExAmount("");
	}
	
	regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()),language));
	regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()),language));
	//below code for exemptions
	
	String exemptions = regForm.getExmpID();
	regForm.setSelectedExemptionList(commonBo.getExemptionList(exemptions));
	
	commonBo.getExtraDetls(regForm,language);
	
	NumberFormat obj=new DecimalFormat("#0.00");
	//String dutyListArr[]=new String[9];
	//String dutyListAdjuArr[]=new String[10];
	String dutyListArr[];
	int numberOfProperties;
	
	if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE || 
			(regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_NP) ||
			regForm.getDeedID()==RegInitConstant.DEED_ADOPTION ||
			regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA ||
			regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
			regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV  ||
			(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV && 
					regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
			(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV && 
					regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
			(regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV && 
					regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
			regForm.getCommonDeed()==1
			){
		
			//regForm.setDeedTypeFlag(0);
		
	
	if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
			regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
			regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV){      
	regForm.setDeedTypeFlag(0);          // deedTypeFlag 0 for deeds where property details are required.
	ArrayList propertyIdList=commonBo.getPropertyListNonPropDeed(regForm.getHidnRegTxnId());
	numberOfProperties=propertyIdList.size();
	regForm.setPropListNonPropDeed(propertyIdList);
		}
	else if(regForm.getCommonDeed()==1){                 // for common deeds
		
	      
		//regForm.setDeedTypeFlag("");         
		ArrayList propertyIdList=commonBo.getParticularList(regForm.getHidnRegTxnId());
		regForm.setPropListNonPropDeed(propertyIdList);
		numberOfProperties=1;
		//regForm.setPropListNonPropDeed(new ArrayList());
			
		
		
	}
	else{
			regForm.setDeedTypeFlag(1);  // deedTypeFlag 1 for deeds where property details are not required.
			regForm.setPropListNonPropDeed(null);
			numberOfProperties=1;
		}
	
	

	ArrayList transPartyDets=commonBo.getTransPartyDetsNonPropDeed(regForm.getHidnRegTxnId(),regForm.getCommonDeed(),language,regForm.getDeedID(),regForm.getInstID());
		regForm.setTransPartyListNonPropDeed(transPartyDets);
		//logger.debug("size of party List  "+transPartyDets.size());
	for(int j=0;j<transPartyDets.size();j++){
		
		CommonDTO dto=new CommonDTO();
		dto=(CommonDTO)transPartyDets.get(j);
		logger.debug("transacting party list  role------>"+dto.getRole());
		logger.debug("transacting party list  name------>"+dto.getName());
		logger.debug("transacting party list  id------>"+dto.getId());
	
	}
	
		if(regForm.getFromAdjudication() == 0){
	dutyListArr=commonBo.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
		}
	else{
			dutyListArr=commonBo.getDutyDetlsAdjuForConfirmation(regForm.getHidnRegTxnId());		

		}
	//forward="reginitConfirmNonPV";
	
	}
	
	else{
		
		if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV || 
				regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
				regForm.getDeedID()==RegInitConstant.DEED_POA || 
				regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV || 
				regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV || 
				regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV || 
				regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV || 
				regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV || 
				regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV || 
				regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
				(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))||
				(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
	  						&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
	  			(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
	  		  				&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
	  			(regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
	  		  		  		&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)){
			regForm.setDeedTypeFlag(0);                          //// deedTypeFlag 0 for deeds where property details are required.
		} 
		
		
	HashMap propMap =new HashMap();
	propMap=regForm.getMapPropertyTransParty();
	//logger.debug("in confirmation action----------------------->");
	ArrayList propertyIdList=new ArrayList();
	//if(regForm.getFromAdjudication()==1){
	//	propertyIdList=commonBo.getPropertyIdMarketValAdju(regForm.getAdjudicationNumber());  //to be changed
	//}else{
	    propertyIdList=commonBo.getPropertyIdMarketVal(regForm.getHidnRegTxnId());
	//}
	double totalMarketVal=0;
	
	numberOfProperties=propertyIdList.size();
	
	for(int i=0;i<propertyIdList.size();i++){
		
		ArrayList row_list=new ArrayList();
		row_list=(ArrayList)propertyIdList.get(i);
		String propIds=row_list.toString();
		propIds=propIds.substring(1, propIds.length()-1);
		
		String propId[]=propIds.split(",");
		
		if(regForm.getDeedID()!=RegInitConstant.DEED_LEASE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_MORTGAGE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_POA &&
				regForm.getDeedID()!=RegInitConstant.DEED_SETTLEMENT_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_WILL_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_TRANSFER_LEASE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_SURRENDER_LEASE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_COMPOSITION_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_SECURITY_BOND_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_LETTER_OF_LICENCE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_AGREEMENT_MEMO_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_TRANSFER_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_FURTHER_CHARGE_NPV){
		if(!propId[1].trim().equalsIgnoreCase("") && !propId[1].trim().equals(null)){
		totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
		}else{
			totalMarketVal=0;
		}
		}
		
		if(regForm.getDeedID()!=RegInitConstant.DEED_PARTITION_PV)
		{
			regForm.setPropListPVDeed(null);
    	ArrayList transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID());
		for(int j=0;j<transPartyDets.size();j++){
			CommonDTO dto=new CommonDTO();
			dto=(CommonDTO)transPartyDets.get(j);
		}
		if(propId[0].trim().length()==15){
			propId[0]="0"+propId[0].trim();
		}
		propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
		
		}
		else{
			regForm.setPropListPVDeed(commonBo.getPropertyListPVDeed(propertyIdList));
		}
		
	}
	
	if(regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
		ArrayList transPartyDets=commonBo.getTransPartyDetsNonPropDeed(regForm.getHidnRegTxnId(),regForm.getCommonDeed(),language,regForm.getDeedID(),regForm.getInstID());
		regForm.setTransPartyListPVDeed(transPartyDets);
	}else{
		regForm.setTransPartyListPVDeed(null);
	}
	
	regForm.setTotalMarketValue(obj.format(totalMarketVal));
	regForm.setMapPropertyTransParty(propMap);
	//if(adjuFlag==1){
	//	dutyListArr=getAdjudicatedDutyDetls(regForm.getAdjudicationNumber());   //to be changed
	//}
	//else{
	if(regForm.getFromAdjudication()==0){
		dutyListArr=commonBo.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
			}
			else{
				dutyListArr=commonBo.getDutyDetlsAdjuForConfirmation(regForm.getHidnRegTxnId());		

			}
	//dutyListArr=getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
	//}
	
	//forward="reginitConfirm";
	}
	
	
	
	//String dutyListArr[]=new String[9];
	
	if(adjuFlag==1){
		dutyListArr=commonBo.getAdjudicatedDutyDetls(regForm.getAdjudicationNumber());   //to be changed
	}
	else{
		dutyListArr=commonBo.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
	}
	
	double totalPaidAmt=0;
	ArrayList paidAmtList=commonBo.getAllPaidAmounts(regForm.getHidnRegTxnId());
	for(int i=0;i<paidAmtList.size();i++){
		ArrayList row_list=new ArrayList();
		row_list=(ArrayList)paidAmtList.get(i);
		String amnts=row_list.toString();
		amnts=amnts.substring(1, amnts.length()-1);
		//logger.debug("paid amount list-->"+amnts);
		String amntsArr[]=amnts.split(",");
		
		totalPaidAmt=totalPaidAmt+Double.parseDouble(amntsArr[0].trim());
	}
	regForm.setTotalPaidAmount(obj.format(totalPaidAmt));
	
	
	
		if(numberOfProperties==1)
		{
			if(dutyListArr!=null){
			regForm.setTotalPayableAmount(obj.format(Double.parseDouble(dutyListArr[5].trim())));
			}else{
				regForm.setTotalPayableAmount(obj.format(0));
			}
			
			//below code for retrieving estamp code if total payable amount is more than zero
			if(Double.parseDouble(regForm.getTotalPayableAmount())>0.0)
			{
				String eCode=commonBo.getEstampCode(regForm.getHidnRegTxnId());
				
				if(eCode!=null && !eCode.equalsIgnoreCase("")){
					regForm.setRegInitEstampCode(eCode);	
				}
				else{
					regForm.setRegInitEstampCode(null);
				}
				
				
			}
			double balance=Double.parseDouble(regForm.getTotalPayableAmount())-Double.parseDouble(regForm.getTotalPaidAmount());
			regForm.setTotalBalanceAmount(obj.format(balance));
			
			if(Double.parseDouble(regForm.getTotalBalanceAmount())==0){
				regForm.setPaymentCompleteFlag(1);
				//regForm.setRegInitEstampCode(null);
			}else{
				//regForm.setRegInitEstampCode(null);
			}
			
			commonBo.setDutyInForm(regForm,dutyListArr,language);
			
		regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
		regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
		regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
		regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
		regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
		regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
		            			
		regForm.setIsMultiplePropsFlag(0);
		regForm.setIsDutyCalculated(1);
		
		//if(adjuFlag==0){
			if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
			else
				regForm.setMarketValueShares(Double.toString(0.0));	
			
			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
			else
				regForm.setDutyPaid(Double.toString(0.0));
			
			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
			else
				regForm.setRegPaid(Double.toString(0.0));
			
			if(regForm.getDeedID()!=RegInitConstant.DEED_POA){
			if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
				regForm.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
				else
					regForm.setConsiAmountTrns(Double.toString(0.0));
			}else if(regForm.getInstID()==RegInitConstant.INST_POA_2){

				if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
					regForm.setConsiAmountTrns(dutyListArr[9].trim());
					else
						regForm.setConsiAmountTrns("-");
				
			}
			}
			else{
				regForm.setMarketValueShares(Double.toString(0.0));	
				regForm.setDutyPaid(Double.toString(0.0));
				regForm.setRegPaid(Double.toString(0.0));
			}
		
		}else if(numberOfProperties>1 && (dutyListArr==null)){
			
			regForm.setIsDutyCalculated(0);
			regForm.setIsMultiplePropsFlag(1);
			
		}else if(numberOfProperties>1 && dutyListArr!=null){
			
			regForm.setTotalPayableAmount(obj.format(Double.parseDouble(dutyListArr[5].trim())));
			double balance=Double.parseDouble(regForm.getTotalPayableAmount())-Double.parseDouble(regForm.getTotalPaidAmount());
			regForm.setTotalBalanceAmount(obj.format(balance));
			
			//below code for retrieving estamp code if total payable amount is more than zero
			if(Double.parseDouble(regForm.getTotalPayableAmount())>0.0)
			{
				String eCode=commonBo.getEstampCode(regForm.getHidnRegTxnId());
				
				if(eCode!=null && !eCode.equalsIgnoreCase("")){
					regForm.setRegInitEstampCode(eCode);	
				}
				else{
					regForm.setRegInitEstampCode(null);
				}
				
				
			}
			
			if(Double.parseDouble(regForm.getTotalBalanceAmount())==0){
				regForm.setPaymentCompleteFlag(1);
				//regForm.setRegInitEstampCode(null);
			}else{
				//regForm.setRegInitEstampCode(null);
			}
			
			regForm.setIsDutyCalculated(1);
			regForm.setIsMultiplePropsFlag(1);
			
			commonBo.setDutyInForm(regForm,dutyListArr,language);
			regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
			regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
			regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
			regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
			regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
			regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
			
			//if(adjuFlag==0){
			if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
			else
				regForm.setMarketValueShares(Double.toString(0.0));	
			
			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
			else
				regForm.setDutyPaid(Double.toString(0.0));
			
			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
			else
				regForm.setRegPaid(Double.toString(0.0));
			
			if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
				regForm.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
				else
					regForm.setConsiAmountTrns(Double.toString(0.0));
			if(regForm.getDeedID()!=RegInitConstant.DEED_POA){
				if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
					regForm.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
					else
						regForm.setConsiAmountTrns(Double.toString(0.0));
				}else if(regForm.getInstID()==RegInitConstant.INST_POA_2){

					if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
						regForm.setConsiAmountTrns(dutyListArr[9].trim());
						else
							regForm.setConsiAmountTrns("-");
					
				}
			}
			else{
				regForm.setMarketValueShares(Double.toString(0.0));	
				regForm.setDutyPaid(Double.toString(0.0));
				regForm.setRegPaid(Double.toString(0.0));
			}
			
		}
		if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE ||
				regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
			
			String finalMV=commonBo.getExchangeDeedFinalMV(regForm.getHidnRegTxnId());
			
		regForm.setExchangeDeedMrktValue(obj.format(Double.parseDouble(finalMV)));
		}
		
		regForm.setRegInitPaymntTxnId(null);
		
		
	}*/
public String[] getDutyDetlsForConfirmation(String appId) throws Exception {
	
	String dutyListArr[]=null;
	RegCompMkrBD commonBd= new RegCompMkrBD();
	ArrayList list=commonBd.getDutyDetlsForConfirmation(appId);
	String dutyList;
	
	if(list!=null && list.size()==1){
		//for(int i=0;i<1;i++){
	dutyList=list.toString();
	dutyList=dutyList.substring(2, dutyList.length()-2);
	dutyListArr=dutyList.split(",");
		//}
	}
	return dutyListArr;
}
public String getInstdFromProp(String propId) throws Exception
{
	RegCompMkrBD commonBd= new RegCompMkrBD();
	return commonBd.getInstdFromProp(propId);
}
public String getInst(String regTxn) throws Exception
{
	RegCompMkrBD commonBd= new RegCompMkrBD();
	return commonBd.getInst(regTxn);
}
public String getDeed(String regTxn) throws Exception
{
	RegCompMkrBD commonBd= new RegCompMkrBD();
	return commonBd.getDeed(regTxn);
}
public String validateDeedDoc(String deedId) throws Exception
{
	RegCompMkrBD commonBd= new RegCompMkrBD();
	return commonBd.validateDeedDoc(deedId);
}

public ArrayList getDeedDocDetails(String regNum) throws Exception
{
	RegCompMkrBD commonBd= new RegCompMkrBD();
	return commonBd.getDeedDocDetails(regNum);
}

public boolean checkConsumptionOfDeedDoc(String deedId, String path, String name, String regTxnId) throws Exception   // 0- no deed 1 - master deed 2- ok
{
	RegCompMkrBD commonBd= new RegCompMkrBD();
	return commonBd.checkConsumptionOfDeedDoc(deedId, path,name, regTxnId);
}
public String[] getDutyDetlsAdjuForConfirmation(String appId) throws Exception {
	
	String dutyListArr[]=null;
	RegCompMkrBD bd= new RegCompMkrBD();
	ArrayList list=bd.getDutyDetlsAdjuForConfirmation(appId);
	String dutyList;
	if(list!=null && list.size()==1){
		//for(int i=0;i<1;i++){
	dutyList=list.toString();
	dutyList=dutyList.substring(2, dutyList.length()-2);
	dutyListArr=dutyList.split(",");
		//}
	}
	return dutyListArr;
}
public ArrayList getAdjudicatedDutyDetlsChecker(String appId) throws Exception {
	RegCompMkrBD bd= new RegCompMkrBD();
	String dutyListArr[]=null;
	ArrayList list=bd.getAdjudicatedDutyDetlsChecker(appId);
	ArrayList finalList = new ArrayList();
	
	//if(list!=null && !(list.size()<1)){
	//String dutyList=list.toString();
	//dutyList=dutyList.substring(2, dutyList.length()-2);
	//dutyListArr=dutyList.split(",");
	//}
	for(int i = 0;i<list.size();i++)
	{
		ArrayList subList = (ArrayList)list.get(i);
		RegCompCheckerDTO rdto = new RegCompCheckerDTO();
		rdto.setStampduty1((String)subList.get(0));
		rdto.setPanchayatDuty((String)subList.get(1));
		rdto.setNagarPalikaDuty((String)subList.get(2));
		rdto.setUpkarDuty((String)subList.get(3));
		rdto.setRegistrationFee((String)subList.get(4));
		rdto.setTotalduty((String)subList.get(5));
		
		String stampDuty = (String)subList.get(7);
		stampDuty = stampDuty == null?"":stampDuty;
		
		if((stampDuty).equals(""))
		{
			rdto.setStampdutyAlreadyPaid("0");
		}
		else
		{
			rdto.setStampdutyAlreadyPaid((String)subList.get(7));
		}
		String regFee = (String)subList.get(8);
		regFee = regFee == null?"":regFee;
		
		if((regFee).equals(""))
		{
			rdto.setRegistrationFeeAlreadyPaid("0");
		}
		else
		{
			rdto.setRegistrationFeeAlreadyPaid((String)subList.get(8));
		}
		String exemStampDuty = (String)subList.get(9);
		exemStampDuty = exemStampDuty == null?"":exemStampDuty;
		
		String exemRegFee = (String)subList.get(10);
		exemRegFee = exemRegFee == null?"":exemRegFee;
		rdto.setExempReg(exemRegFee);
		rdto.setExempStamp(exemStampDuty);
		   //in case of adjudication already paid duties 
		finalList.add(rdto);
	}
	return finalList;
}

public ArrayList getDutyDetls(String appId) throws Exception {
	RegCompMkrBD bd= new RegCompMkrBD();
	ArrayList list= bd.getDutyDetlsChecker(appId);
	ArrayList finalList = new ArrayList();
	//String dutyList2 = null;
	//double stmp2 = 0;
	//double reg2 = 0;
	
	//String dutyList=list.toString();
	//dutyList=dutyList.substring(2, dutyList.length()-2);
	//String dutyListArr[]=dutyList.split(",");
	
	for(int i = 0;i<list.size();i++)
	{
		ArrayList subList = (ArrayList)list.get(i);
		RegCompCheckerDTO rdto = new RegCompCheckerDTO();
		rdto.setStampduty1((String)subList.get(0));
		rdto.setPanchayatDuty((String)subList.get(1));
		rdto.setNagarPalikaDuty((String)subList.get(2));
		rdto.setUpkarDuty((String)subList.get(3));
		rdto.setRegistrationFee((String)subList.get(4));
		rdto.setTotalduty((String)subList.get(5));
		String stampDuty = (String)subList.get(6);
		stampDuty = stampDuty == null?"":stampDuty;
		if((stampDuty).equals(""))
		{
			rdto.setStampdutyAlreadyPaid("0");
		}
		else
		{
			rdto.setStampdutyAlreadyPaid((String)subList.get(6));
		}
		
		String regFee = (String)subList.get(7);
		regFee = regFee == null?"":regFee;
		
		if((regFee).equals(""))
		{
			rdto.setRegistrationFeeAlreadyPaid("0");
		}
		else
		{
			rdto.setRegistrationFeeAlreadyPaid((String)subList.get(7));
		}
		String exemStampDuty = (String)subList.get(8);
		exemStampDuty = exemStampDuty == null?"":exemStampDuty;
		
		String exemRegFee = (String)subList.get(9);
		exemRegFee = exemRegFee == null?"":exemRegFee;
		rdto.setExempReg(exemRegFee);
		rdto.setExempStamp(exemStampDuty);
	finalList.add(rdto);
	}
	
	
	/*ArrayList list2 = regCompDAO.getChangedDutyAtMaker(appId);
	if(list2.size()>0)
	{
		dutyList2 = list2.toString();
		dutyList2 = dutyList2.substring(2, dutyList2.length()-2);
		String tempArr[] = dutyList2.split(",");
		 stmp2 =  Double.parseDouble(tempArr[1].trim());
		 reg2 =  Double.parseDouble(tempArr[0].trim());
	}
	
	double stmp1 =  Double.parseDouble(dutyListArr[0].trim());
	double reg1 =  Double.parseDouble(dutyListArr[4].trim());
	
	double total1 = Double.parseDouble(dutyListArr[5].trim());
	Double stamp = stmp1+stmp2;
	Double regFee = reg1+reg2;
	Double total = total1+stmp2;
	dutyListArr[0] = stamp.toString();
	dutyListArr[4] = regFee.toString();
	dutyListArr[5] = total.toString();*/
	
	return finalList;
}
//Added by Rustam
public ArrayList getCyberTehsilFormDetails(String regNum) throws Exception
{
	RegCompMkrBD commonBd= new RegCompMkrBD();
	return commonBd.getCyberTehsilFormDetails(regNum);
}
}
