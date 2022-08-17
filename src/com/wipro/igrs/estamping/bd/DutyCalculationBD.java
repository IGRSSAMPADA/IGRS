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



package com.wipro.igrs.estamping.bd;


import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.estamping.dao.DutyCalculationDAO;
import com.wipro.igrs.estamping.dao.EstampDAO;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.estamping.dto.DutyCalculationDTO;
import com.wipro.igrs.estamping.dto.EstampDTO;
import com.wipro.igrs.estamping.form.DutyCalculationForm;



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
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getCountry(String language) throws Exception{
		//ArrayList list =  new IGRSCommon().getCountry();
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCountryJud(language);
        ArrayList mainList = new ArrayList();
        ArrayList subList = null;
        DutyCalculationDTO dto = new DutyCalculationDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new DutyCalculationDTO();
            dto.setValue(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		return mainList;
	}
	
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getState(String cntryID,String language) throws Exception{
		//ArrayList list =  new IGRSCommon().getState(cntryID,language);
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getStateJud(cntryID,language);
        ArrayList mainList = new ArrayList();
        ArrayList subList = null;
        DutyCalculationDTO dto = new DutyCalculationDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new DutyCalculationDTO();
            dto.setValue(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		return mainList;
	}
	
	
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getDistrict(String stateID,String language) throws Exception{
		ArrayList list =  new IGRSCommon().getDistrict(stateID);
        ArrayList mainList = new ArrayList();
        ArrayList subList = null;
        DutyCalculationDTO dto = new DutyCalculationDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new DutyCalculationDTO();
            if(language.equalsIgnoreCase("en"))
            {
            	 dto.setName(subList.get(1).toString());
            }
            else
            {
            	 dto.setName(subList.get(2).toString());
            }
            dto.setValue(subList.get(0).toString());
           
            mainList.add(dto);
        	}
		return mainList;
	}
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getPhoto(String language) throws Exception{
		ArrayList list =  new IGRSCommon().getPhotoIdProof(language);
        ArrayList mainList = new ArrayList();
        ArrayList subList = null;
        DutyCalculationDTO dto = new DutyCalculationDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new DutyCalculationDTO();
            dto.setValue(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		return mainList;
	}
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getAppellate(String language) throws Exception{
		ArrayList list =  new EstampDAO().getAppellate(language);
        ArrayList mainList = new ArrayList();
        ArrayList subList = null;
        DutyCalculationDTO dto = new DutyCalculationDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new DutyCalculationDTO();
            dto.setValue(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		return mainList;
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
	
	/******************************************************************  
	 *   Method Name  :   insertTxn()
	 *   Arguments    :   Cash Form 
	 *   Return       :   Boolean
	 *   Throws 	  :   NullPointer  or SQLException or Exception
	*******************************************************************/  
	public  boolean insertTxn (DutyCalculationForm _form, String lang)throws NullPointerException,
	SQLException,Exception{
		boolean flg = false;
		DutyCalculationDAO dao = new DutyCalculationDAO();
		
			flg = dao.insertTxn(_form,lang);
		
		   return flg;
	}
	
	/******************************************************************  
	 *   Method Name  :   inserteCode()
	 *   Arguments    :   Cash Form 
	 *   Return       :   Boolean
	 *   Throws 	  :   NullPointer  or SQLException or Exception
	*******************************************************************/  
	public  boolean inserteCode (DutyCalculationForm _form, DashBoardDTO dto)throws NullPointerException,
	SQLException,Exception{
		boolean flg = false;
		DutyCalculationDAO dao = new DutyCalculationDAO();
		
			flg = dao.inserteCode(_form, dto);
		
		   return flg;
	}
	
	/******************************************************************  
	 *   Method Name  :   insertPay()
	 *   Arguments    :   Cash Form 
	 *   Return       :   Boolean
	 *   Throws 	  :   NullPointer  or SQLException or Exception
	*******************************************************************/  
	public  boolean insertPay (DutyCalculationForm _form, DashBoardDTO dto)throws NullPointerException,
	SQLException,Exception{
		boolean flg = false;
		DutyCalculationDAO dao = new DutyCalculationDAO();
		
			flg = dao.insertPay(_form,dto);
		
		   return flg;
	}

	
	/******************************************************************  
	 *   Method Name  :   updateTxn()
	 *   Arguments    :   Cash Form 
	 *   Return       :   Boolean
	 *   Throws 	  :   NullPointer  or SQLException or Exception
	*******************************************************************/  
	public  boolean updateTxn (DutyCalculationForm _form,String lang)throws NullPointerException,
	SQLException,Exception{
		boolean flg = false;
		DutyCalculationDAO dao = new DutyCalculationDAO();
		
			flg = dao.updateTxn(_form,lang);
		
		   return flg;
	}
	public  boolean updateInitationLanguage (String regNo,String lang)throws NullPointerException,
	SQLException,Exception{
		boolean flg = false;
		DutyCalculationDAO dao = new DutyCalculationDAO();
		
			flg = dao.updateInitationLanguage(regNo,lang);
		
		   return flg;
	}
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getPayDtls(String txnId) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getPayDtls(txnId);
       
		return list;
	}
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	
	public ArrayList getspDtls(String txnId,String language) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getspDtls(txnId,language);
       
		return list;
	}
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getAppDtls(String txnId,String language) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getAppDtls(txnId,language);
       
		return list;
	}
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getPartyDtls(String txnId,String language) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getPartyDtls(txnId,language);
       
		return list;
	}
	public ArrayList getPartyAdoptDtls(String txnId,String language) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getPartyAdoptDtls(txnId,language);
       
		return list;
	}
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getEcodeDtls(String txnId,String language) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getEcodeDtls(txnId,language);
       
		return list;
	}
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getspbnkDtls(String txnId,String language) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getspbnkDtls(txnId,language);
       
		return list;
	}
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getDeedDtls(String txnId,String language) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getDeedDtls(txnId,language);
       
		return list;
	}
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return String
	 */
	public String getDuty(String txnId) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String list = dao.getDuty(txnId);
       
		return list;
	}
	public String getLangauge(String txnId) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String list = dao.getLangauge(txnId);
       
		return list;
	}
	public ArrayList getDutyDetails(String txnId) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getDutyDetails(txnId);
       
		return list;
	}
	public ArrayList getDutyDetailsInitation(String txnId,String module) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getDutyDetailsInitiation(txnId,module);
       
		return list;
	}
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return String
	 */
	public String getMainId(String txnId) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String list = dao.getMainId(txnId);
       
		return list;
	}
	 /*  public ArrayList getDutyDetailsAdjudication(String txnId, String module)
       throws Exception
   {
       DutyCalculationDAO dao = new DutyCalculationDAO();
       ArrayList list = dao.getDutyDetailsAdjudication(txnId, module);
       return list;
   }*/
	/*   public String getAdjuFlag(String txnId)
       throws Exception
   {
       DutyCalculationDAO dao = new DutyCalculationDAO();
       String list = dao.getAdjuFlag(txnId);
       return list;
   }*/
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return String
	 */
	public String getspName(String txnId) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String list = dao.getspName(txnId);
       
		return list;
	}
	public String getspLicenseNo(String txnId) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String list = dao.getspLicenseNo(txnId);
       
		return list;
	}
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getruName(String txnId,String language) throws Exception{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getruName(txnId,language);
       
		return list;
	}
	public  boolean updateStatus (DutyCalculationForm form)throws Exception,
	SQLException,Exception{
		boolean flg = false;
		DutyCalculationDAO dao = new DutyCalculationDAO();
		
			flg = dao.updateStatus(form);
		
		   return flg;
	}
	
	public String getEcode(String disttId, String amnt, String func_id, String purpose, EstampDTO estampDTO, String regInitId,String modFlag,String language) throws NullPointerException, SQLException, Exception
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String ecodeTxnId = dao.getEcode(disttId, amnt, func_id, purpose, estampDTO, regInitId,modFlag,language);
		return ecodeTxnId;
	}
	public String getEcode2(String disttId, String amnt, String func_id, String purpose, EstampDTO estampDTO, String regInitId,String modFlag) throws NullPointerException, SQLException, Exception
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String ecodeTxnId = dao.getEcode2(disttId, amnt, func_id, purpose, estampDTO, regInitId,modFlag);
		return ecodeTxnId;
	}
	public String getBreifDocument(String id) throws NullPointerException, SQLException, Exception
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String ecodeTxnId = dao.getBreifDocument(id);
		return ecodeTxnId;
	}
	/*public String getEcodeRegComp(String disttId, String amnt, String func_id, String purpose, EstampDTO estampDTO, String regInitId) throws NullPointerException, SQLException, Exception
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String ecodeTxnId = dao.getEcodeRegComp(disttId, amnt, func_id, purpose, estampDTO, regInitId);
		return ecodeTxnId;
	}*/
	/******************************************************************  
	 *   Method Name  :   insertTxn()
	 *   Arguments    :   Cash Form 
	 *   Return       :   Boolean
	 *   Throws 	  :   NullPointer  or SQLException or Exception
	*******************************************************************/  
	public  boolean insertConsume (DutyCalculationForm _form, DashBoardDTO dto)throws NullPointerException,
	SQLException,Exception{
		boolean flg = false;
		DutyCalculationDAO dao = new DutyCalculationDAO();
		
			flg = dao.insertConsume(_form, dto);
		
		   return flg;
	}
	public  boolean insertConsumeExp (DutyCalculationForm _form, DashBoardDTO dto)throws NullPointerException,
	SQLException,Exception{
		boolean flg = false;
		DutyCalculationDAO dao = new DutyCalculationDAO();
		
			flg = dao.insertConsumeExp(_form, dto);
		
		   return flg;
	}


	public ArrayList getdetails(String userId,
			DashBoardDTO objDashBoardDTO1) {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList details = new ArrayList();
		 details = dao.getdetails(userId,objDashBoardDTO1);
		
		 return details;
	}


	public ArrayList getrudetails(String userId,
			DashBoardDTO objDashBoardDTO1,
			String txnId,String language) {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList details = new ArrayList();
		 details = dao.getrudetails(userId,objDashBoardDTO1,txnId,language);
		
		 return details;
	}


	public ArrayList getiudetails(String offcId,
			DashBoardDTO objDashBoardDTO1,String language) {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList details = new ArrayList();
		 details = dao.getiudetails(objDashBoardDTO1,offcId,language);
		
		 return details;
	}


	/******************************************************************  
	 *   Method Name  :   insertDocDetls()
	 *   Arguments    :   Cash Form 
	 *   Return       :   Boolean
	 *   Throws 	  :   NullPointer  or SQLException or Exception
	*******************************************************************/  
	public  boolean insertDocDetls(DutyCalculationForm _form)throws NullPointerException,
	SQLException,Exception{
		boolean flg = false;
		DutyCalculationDAO dao = new DutyCalculationDAO();
		
			flg = dao.insertDocDetls(_form);
		
		   return flg;
	}


	public ArrayList getiuDtls(String userId, String officeId,String language) throws NullPointerException,
	SQLException,Exception {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list = dao.getiuDtls(userId,officeId,language);
	   
		return list;
	}
	
	public String getEstampTxnId(String estampCode)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		return dao.getEstampTxnId(estampCode);
	}
	public String getEstampCode(String estampCode)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		return dao.getEstampCode(estampCode);
	}
	public String getDeedContent(String id,String moduleId)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		return dao.getDeedContent(id,moduleId);
	}
	public  ArrayList getDeedType() throws Exception {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		return dao.getDeedType();
	}
	
	public String ivrsEsatmpStatus(int eStampNo)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list =dao.ivrsEsatmpStatus(eStampNo);
		String estampStatus="";
		String consumptionStatus="";
		if(list!=null&&list.size()>0)
		{
			ArrayList rowList=(ArrayList) list.get(0);
			estampStatus=(String)rowList.get(0);
			consumptionStatus=(String)rowList.get(1);
		}
		if("A".equalsIgnoreCase(estampStatus))
		{
			return "A~Activated.Not Consumed ";
		}
		else if("D".equalsIgnoreCase(estampStatus)&&!"Consumed".equalsIgnoreCase(consumptionStatus))
		{
			return "D~Deactivated";
		}
		else if("D".equalsIgnoreCase(estampStatus)&&"Consumed".equalsIgnoreCase(consumptionStatus))
		{
			return "Consumed~Consumed";
		}
		return null;
	}
	
	public String ivrsEstampPurchaser(int eStampNo)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		ArrayList list =dao.ivrsEstampPurchaser(eStampNo);
		String partyType="";
		String name="";
		String orgnisationName="";
		if(list!=null&&list.size()>0)
		{
			ArrayList rowList=(ArrayList) list.get(0);
			partyType=(String)rowList.get(0);
			name=(String)rowList.get(1)+" "+(String)rowList.get(2)+" "+(String)rowList.get(3);
			orgnisationName=(String)rowList.get(4);
			if(name==null || name.equalsIgnoreCase(""))
			{
				return orgnisationName;
			}
			else
			{
				return name;
			}
			
		}
		else
		{
			return null;
		}
		
	}
	public String ivrsEstampPurchaseDate(int eStampNo)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String list =dao.ivrsEstampPurchaseDate(eStampNo);
		if(list==null||list.equalsIgnoreCase(""))
		{
			return null;
		}
		else
		{
			return list;
		}
	}
	public String ivrsEstampPupose(int eStampNo)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String list =dao.ivrsEstampPupose(eStampNo);
		if(list==null||list.equalsIgnoreCase(""))
		{
			return null;
		}
		else
		{
			return list;
		}
	}


	public String moduleFlag(String estampCode) {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String flag=dao.moduleFlag(estampCode); 
		return flag;
	}
	public String getRegId(String estampCode) {
		DutyCalculationDAO dao = new DutyCalculationDAO();
		String flag=dao.getRegId(estampCode); 
		return flag;
	}
	public boolean updateEstampFlag(String regInitId)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		return 	dao.updateEstampFlag(regInitId);
	}
	public boolean checkEstamp(String regTxnID,String mod_flag)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		return 	dao.checkEstamp(regTxnID,mod_flag);
	}
	public boolean checkEstampValidity(String regTxnID,String deedDraft)
	{
		DutyCalculationDAO dao = new DutyCalculationDAO();
		return 	dao.checkEstampValidity(regTxnID,deedDraft);
	}
}
