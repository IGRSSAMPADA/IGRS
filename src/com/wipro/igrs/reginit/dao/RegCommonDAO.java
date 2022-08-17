package com.wipro.igrs.reginit.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.CustomArrayList;
import com.wipro.igrs.common.IGRSCommon;
//import com.wipro.igrs.common.customArrayList;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.reginit.dto.CommonDTO;
import com.wipro.igrs.reginit.dto.RegCommonDTO;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.reginit.form.RegCommonForm;
import com.wipro.igrs.reginit.sql.RegCommonSQL;


public class RegCommonDAO{
	
	DBUtility dbUtility = null;
	Connection connTest;
	String sql = null;
	//CommonDTO dto = null;
	//ArrayList mainList = null;
	IGRSCommon igrsCommon = null;
	PreparedStatement pst = null;
	private  Logger logger = 
		(Logger) Logger.getLogger(DBUtility.class);
	public RegCommonDAO(){
		 try {
	            igrsCommon = new IGRSCommon();
	        } catch (Exception e) {
	        	logger.error("RegCommonDAO in dao start" + e.getStackTrace());
	        }
	}
	
	/**
	 * for getting all Applicant Types
	 * @return ArrayList
	 */
	public ArrayList getAppType(String languageLocale) {
		
		ArrayList mainList = new ArrayList();
		 try {
			 dbUtility = new DBUtility();
		//sql = RegCommonSQL.SELECT_APPL_TYPE;
			 sql = RegCommonSQL.SELECT_APPL_TYPE_HINDI;
        dbUtility.createStatement();
        ArrayList list = dbUtility.executeQuery(sql);
        //ArrayList mainList = new ArrayList();
        ArrayList subList = null;
        CommonDTO dto;
        
        
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new CommonDTO();
            dto.setId(subList.get(0).toString());
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());
            }
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	
	/**
	 * for getting all Country details
	 * @return ArrayList
	 */
	public ArrayList getCountry(String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
	        ArrayList list = igrsCommon.getCountry();
	       // mainList = new ArrayList();
	        ArrayList subList = null;
	        CommonDTO dto;
	        for (int i = 0; i < list.size(); i++) {
	            subList = (ArrayList)list.get(i);
	            dto = new CommonDTO();
	            dto.setId(subList.get(0).toString());
	            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	            dto.setName(subList.get(1).toString());
	            }else{
	            	dto.setName(subList.get(2).toString());
	            }
	            mainList.add(dto);
	        	}
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			 }finally{
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						logger.error("RegCommonDAO in dao start" + e.getStackTrace());
					}
			}
	        return mainList;
	}
	
	/**
	 * for getting all State details
	 * @param param 
	 * @return ArrayList
	 */
	public ArrayList getState(String param,String languageLocale) {
		
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
	        ArrayList list = igrsCommon.getState(param);
	        //mainList = new ArrayList();
	        ArrayList subList = null;
	        CommonDTO dto;
	        for (int i = 0; i < list.size(); i++) {
	            subList = (ArrayList)list.get(i);
	            dto = new CommonDTO();
	            dto.setId(subList.get(0).toString());
	            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	            dto.setName(subList.get(1).toString());
	            }else{
	            	dto.setName(subList.get(2).toString());	
	            }
	            mainList.add(dto);
	        	}
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCommonDAO in dao start" + e.getStackTrace());
				}
			}
	        return mainList;
	}
	
	/**
	 * for getting all District details
	 * @param stateId 
	 * @return ArrayList
	 */
	public ArrayList getDistrict(String stateId,String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
        ArrayList list = igrsCommon.getDistrict(stateId);
        //mainList = new ArrayList();
        ArrayList subList = null;
        CommonDTO dto;
        
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new CommonDTO();
            dto.setId(subList.get(0).toString());
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());
            }
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	
	/**
	 * for getting all ID Proof details
	 * @return ArrayList
	 */
	public ArrayList getIdProf(String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
		//sql = RegCommonSQL.SELECT_PHOTO_PROOF;
			sql = RegCommonSQL.SELECT_PHOTO_PROOF_HINDI;
        dbUtility.createStatement();
        ArrayList list = dbUtility.executeQuery(sql);
        //mainList = new ArrayList();
        ArrayList subList = null;
        CommonDTO dto;
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new CommonDTO();
            dto.setId(subList.get(0).toString());
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());	
            }
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	
	/**
	 * for getting All Deed Details
	 * @return ArrayList
	 */
	public ArrayList getDeedType(String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_DEED_TYPE;
	        dbUtility.createStatement();
	        ArrayList list = dbUtility.executeQuery(sql);
	        //mainList = new ArrayList();
	        ArrayList subList = null;
	        CommonDTO dto;
	        for (int i = 0; i < list.size(); i++) {
	            subList = (ArrayList)list.get(i);
	            dto = new CommonDTO();
	            dto.setId(subList.get(0).toString());
	            dto.setName(subList.get(1).toString());
	            mainList.add(dto);
	        	}
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCommonDAO in dao start" + e.getStackTrace());
				}
			}
	        return mainList;
	}
	
	//following method created by roopam
	/**
	 * for getting Deed Details based on deed type i.e. optional or mandatory
	 * @return ArrayList
	 */
	public ArrayList getDeedTypeNew(String flag) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			if(flag.equalsIgnoreCase("Registration Process "))
			sql = RegCommonSQL.SELECT_DEED_TYPE;
			else
				sql = RegCommonSQL.SELECT_DEED_TYPE_OPTIONAL;
	        dbUtility.createStatement();
	        ArrayList list = dbUtility.executeQuery(sql);
	       // mainList = new ArrayList();
	        ArrayList subList = null;
	        CommonDTO dto;
	        for (int i = 0; i < list.size(); i++) {
	            subList = (ArrayList)list.get(i);
	            dto = new CommonDTO();
	            dto.setId(subList.get(0).toString());
	            dto.setName(subList.get(1).toString());
	            mainList.add(dto);
	        	}
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCommonDAO in dao start" + e.getStackTrace());
				}
			}
	        return mainList;
	}
	//end of creation by roopam
	
	/**
	 * for getting Instruments list based on deed
	 * @param deed
	 * @return ArrayList
	 */
	public ArrayList getInstrument(String deed) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={deed};
			sql = RegCommonSQL.SELECT_DEED_INSTRUMENT;
	        dbUtility.createPreparedStatement(sql);
	        ArrayList list = dbUtility.executeQuery(param);
	       // mainList = new ArrayList();
	        ArrayList subList = null;
	        CommonDTO dto;
	        for (int i = 0; i < list.size(); i++) {
	            subList = (ArrayList)list.get(i);
	            dto = new CommonDTO();
	            dto.setId(subList.get(0).toString());
	            dto.setName(subList.get(1).toString());
	            mainList.add(dto);
	        	}
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCommonDAO in dao start" + e.getStackTrace());
				}
			}
	        return mainList;
	}
	
	/**
	 * form setting Instrument/Exemptions Details based on deed/instrument
	 * @param deed
	 * @param deed1
	 * @return ArrayList
	 */
	public ArrayList getExemption(String deed,String deed1) {
		ArrayList mainList = new ArrayList();
		try{
			dbUtility = new DBUtility();
			String[] param={deed};
			if(deed1.equalsIgnoreCase("deed")){
				sql = RegCommonSQL.SELECT_DEED_EXEMPTION_ONDEED;
			}
			else{
				sql = RegCommonSQL.SELECT_DEED_EXEMPTION;
			}
        dbUtility.createPreparedStatement(sql);
        ArrayList list = dbUtility.executeQuery(param);
      //  mainList = new ArrayList();
        ArrayList subList = null;
        CommonDTO dto;
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new CommonDTO();
            dto.setId(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	/**
	 * for calculating stamp duty
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @param marketValue 
	 * @return Stamp Duty
	 */
	/*public String getStampDuty(String deed,String instrument,String exemption, String marketValue){
		String exemptions[] = exemption.split(",");
		String exemptionIDs[] = new String[exemptions.length/2];
		int j=0;
		for(int i=0;i<exemptions.length;i=i+2){
			exemptionIDs[j] = exemptions[i]; 
			j++;
		}
		String tempExm="";
		for(int i=0;i<exemptionIDs.length;i++){
			if(tempExm.equalsIgnoreCase(""))
				tempExm=exemptionIDs[i];
			else
				tempExm=tempExm+","+exemptionIDs[i];
		}
		int value=Integer.parseInt(marketValue);
		double market = value;
		String duty="0";
		try {
			duty=igrsCommon.getStampDuty(deed, instrument, tempExm, market);
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
		return duty;
	}*/
	/*public String getStampDuty(String deed,String instrument,String exemption, String marketValue){
		
		String tempExm = null;
		String duty = "0";
		double market = 0.0;
		if (exemption != null)
		    if (!exemption.equalsIgnoreCase("null"))
		{
			String exemptions[] = exemption.split(",");
			String exemptionIDs[] = new String[exemptions.length/2];
			int j=0;
			if(!("".equals(exemption))){
			for(int i=0;i<exemptions.length;i=i+2){
				exemptionIDs[j] = exemptions[i]; 
				j++;
			}		
			for(int i=0;i<exemptionIDs.length;i++){
				if("".equals(tempExm))
					tempExm=exemptionIDs[i];
				else
					tempExm=tempExm+","+exemptionIDs[i];
			}
			}
		}
		if (marketValue != null) {
			market	=	Double.parseDouble(marketValue);
		}
		try {
			logger.debug("deed value in DAO is === " + deed + "instrument value is == " + instrument + "exemption value is == "+ tempExm + "base value is === "+ market);
			duty=igrsCommon.getStampDuty(deed, instrument, tempExm, market);
		} catch (Exception e) {
			logger.debug("RegCommonDAO in dao start" + e.getStackTrace());
		}
		return duty;
	}*/
	//above code commented by roopam
	
	/**
	 * for calculating other fee for stamp duty
	 * @param function_id
	 * @param user_id 
	 * @param service_id 
	 * @return otherFee
	 */
	
	public ArrayList getOthersDuty(String function_id, String service_id, String user_id){
		ArrayList otherFee = null;
	try {
			String args[] = new String[3];
			args[0] = function_id;
			args[1] = service_id;
			args[2] = user_id;
			otherFee = igrsCommon.getOthersFeeDuty(function_id,service_id,user_id);
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		return otherFee; 
	}
	
	/**
	 * this method is useful for Initiate the Registration 
	 * @param stampDuty
	 * @param reg_txn
	 * @param deed_details
	 * @return boolean
	 */
	public boolean InitRegistration(String stampDuty[],String reg_txn[],String deed_details[]){
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.INSERT_STAMP_DUTY;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(stampDuty);
			if(boo){
				sql = RegCommonSQL.INSERT_REG_TXN;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(reg_txn);
			}
			if(boo){
				String temp[] = deed_details[3].split(",");
				sql = RegCommonSQL.INSERT_REG_DEED;
				dbUtility.createPreparedStatement(sql);
				for(int i=0;i<temp.length;i=i+2){
					deed_details[3] = temp[i];
				boo=dbUtility.executeUpdate(deed_details);
				}
			}
			if(boo)
				dbUtility.commit();
			else
				dbUtility.rollback();
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
		
		return boo;
	}

	public boolean InitRegPartyDetails(String[] party) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.INSERT_REG_TXN_PARTY;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(party);
			if(boo)
				dbUtility.commit();
			else
				dbUtility.rollback();
		}catch (Exception e) {
			logger.error("RegCommonDAO in InitRegPartyDetails start" + e.getStackTrace());
		}
		return boo;
	}
	/**@auther : ROOPAM MEHTA
	   @Created : 16 Nov.2012
	   @Description : checks weather the given deed requires property valuation or duty calculation
	   @Paratmeter : String
	*/
	public boolean propertyValReqCheck(String deed) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			ArrayList result=new ArrayList();
			String param[]=new String[1];
			param[0]=deed;
			sql = RegCommonSQL.PROPERT_VAL_REQ_CHECK;
			dbUtility.createPreparedStatement(sql);
			result=dbUtility.executeQuery(param);
			System.out.println("result -> "+result.toString());
			if(result!=null)
			{
				//System.out.println("result -> "+result.get(0));
				String res=result.get(0).toString();
				System.out.println("res -> "+res);
				//res.substring(1, res.length());
				//res.
				//System.out.println("res -> "+res);
				
				if(res.equals(new String("[Y]"))||res.equals(new String("[y]"))){
					boo=true;
				}
			}
			
				
		}catch (Exception e) {
			//logger.error("RegCommonDAO in InitRegPartyDetails start" + e.getStackTrace());
			e.printStackTrace();
		}
		return boo;
	}
	
	/**
	 * for getting Instruments list based on deed
	 * @param deed
	 * @return ArrayList
	 */
	public ArrayList getPartyType(int deed,int inst, String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			//String[] param={Integer.toString(deed)};
			ArrayList list=new ArrayList();
			
			if(inst==RegInitConstant.INST_DISSOLUTION_NPV){
				String[] param={Integer.toString(inst)};
				//sql = RegCommonSQL.SELECT_PARTY_TYPE_INST_WISE;
				sql = RegCommonSQL.SELECT_PARTY_TYPE_INST_WISE_HINDI;
				dbUtility.createPreparedStatement(sql);
		        list = dbUtility.executeQuery(param);
			}
			else{
				String[] param={Integer.toString(deed)};
			//sql = RegCommonSQL.SELECT_PARTY_TYPE;
				sql = RegCommonSQL.SELECT_PARTY_TYPE_HINDI;
			dbUtility.createPreparedStatement(sql);
	        list = dbUtility.executeQuery(param);
			}
	        //dbUtility.createPreparedStatement(sql);
	        //ArrayList list = dbUtility.executeQuery(param);
	       // mainList = new ArrayList();
	        ArrayList subList = null;
	        CommonDTO dto;
	        
	        for (int i = 0; i < list.size(); i++) {
	            subList = (ArrayList)list.get(i);
	            dto = new CommonDTO();
	            dto.setId(subList.get(0).toString());
	            int partyId=Integer.parseInt(dto.getId());
	            
	            if(deed==RegInitConstant.DEED_COMPOSITION_NPV || deed==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
	            		inst==RegInitConstant.INST_TRANSFER_NPV_1 || inst==RegInitConstant.INST_TRANSFER_NPV_2)// for composition deed, letter of license deed & 2 instruments of transfer deed
	            {
	             	
	            	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	            	if(partyId==RegInitConstant.ROLE_EXECUTANT_SELF || partyId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER)
	            	{
	            		dto.setName(subList.get(1).toString()+RegInitConstant.CREDITOR_ENGLISH);
	            	}else{
	            		dto.setName(subList.get(1).toString()+RegInitConstant.DEBTOR_ENGLISH);
	            	}
	            	}else{
	            		

		            	if(partyId==RegInitConstant.ROLE_EXECUTANT_SELF || partyId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER)
		            	{
		            		dto.setName(subList.get(2).toString()+RegInitConstant.CREDITOR_HINDI);
		            	}else{
		            		dto.setName(subList.get(2).toString()+RegInitConstant.DEBTOR_HINDI);
		            	}
		            	
	            		
	            	}
	            
	            }else if(inst==RegInitConstant.INST_TRANSFER_NPV_4)// for 1 instrument of transfer deed
	            {
	             	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	            	if(partyId==RegInitConstant.ROLE_EXECUTANT_SELF || partyId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER)
	            	{
	            		dto.setName(subList.get(1).toString());
	            	}else{
	            		dto.setName(subList.get(1).toString()+RegInitConstant.BENEFICIARY_ENGLISH);
	            	}
	             	}else{
	             		

		            	if(partyId==RegInitConstant.ROLE_EXECUTANT_SELF || partyId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER)
		            	{
		            		dto.setName(subList.get(2).toString());
		            	}else{
		            		dto.setName(subList.get(2).toString()+RegInitConstant.BENEFICIARY_HINDI);
		            	}
		             	
	             		
	             	}
	            
	            }else{
	            	
	            	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	            	dto.setName(subList.get(1).toString());
	            	}else{
	            		dto.setName(subList.get(2).toString());
	            	}
	            }
	            mainList.add(dto);
	        	}
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCommonDAO in dao start" + e.getStackTrace());
				}
			}
	        return mainList;
	}
	/**
	 * for inserting applicant details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insrtApplcntTransPartyPropDetls(String[] party, String[] deed, String[] propDetls,
			                                       String[] propDetls2,String[] ownerDetails, String exemp, String[] statusParam, String[] extraDetls) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			if(party[32].equals("") || party[32].equalsIgnoreCase(""))
			{
			sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;   //relationship
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(party);
			}
			else{
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(ownerDetails);
				if(boo){
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;    
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(party);
				}
				else
					dbUtility.rollback();
			}
			if(boo){
				
				if(deed.length==8)
				{
				sql = RegCommonSQL.INSERT_REG_DEED_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(deed);
				if(boo){
					
					//BELOW CODE FOR INSERTION OF PROPERTY DETAILS
					
					sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(propDetls);
					
					//BELOW CODE FOR INSERTION OF EXEMPTIONS
					if(boo){
						
						String[] exempParam = new String[3];
						if(exemp!=null && !exemp.equalsIgnoreCase("")){
							
						String[] exempArr=exemp.split(",");	
						if(exempArr!=null && exempArr.length>0){
											
							for(int i=0;i<exempArr.length;i++){
								
								
								exempParam[0]=party[0];
								exempParam[1]=exempArr[i].trim();
								exempParam[2]=party[30];
								
								
								sql = RegCommonSQL.INSERT_REG_EXEMPTION_DETLS;
								dbUtility.createPreparedStatement(sql);
								boo=dbUtility.executeUpdate(exempParam);
								
								if(!boo){
									break;
								}
								
							}
						}
					}
						//for inserting extra details if present
						if(extraDetls!=null){                                         //for title deed and trust deed only.
							if(boo)
							{
								sql=RegCommonSQL.INSERT_REG_DEED_EXTRA_DTLS;
								dbUtility.createPreparedStatement(sql);
								boo=dbUtility.executeUpdate(extraDetls);
								if(!boo)
								{
									dbUtility.rollback();
								}
								
							}
							else{ 
								dbUtility.rollback();
							}
							}
		    	}else{
					dbUtility.rollback();
		    	}
					
					
					
					
				}	
				else{
					dbUtility.rollback();
				}
				}
				if(boo){
					
					   //BELOW CODE FOR INSERTION OF PROPERTY AND TRANSACTING PARTY MAP DETAILS
						
						sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
						dbUtility.createPreparedStatement(sql);
						boo=dbUtility.executeUpdate(propDetls2);
						if(boo){
							
							
							
							if(statusParam!=null){
							sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
							dbUtility.createPreparedStatement(sql);
							boo=dbUtility.executeUpdate(statusParam);
							if(boo){
							dbUtility.commit();
							}else{
								dbUtility.rollback();
							}
							}
							else{
								dbUtility.commit();
							}
						}
						else{
							dbUtility.rollback();
						}
					
				}
				else{				
					dbUtility.rollback();
				}
			}
			else{
				dbUtility.rollback();
			}
		}catch (Exception e) {
			boo=false;
			try{
				dbUtility.rollback();
			}catch(Exception ex){
				logger.debug("Exception in RegCommonDAO:insrtApplcntTransPartyPropDetls rollback.");
			}
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * for getting sequence id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getRegTxnIdSeq() throws Exception {

       // int regTxnIdSeq = 0;
        String regTxnIdSeq="0";
        
        try {
        	dbUtility = new DBUtility();
        	
        	String SQL1 = RegCommonSQL.GET_TODAY_APP_COUNT;
        	  logger.debug(SQL1);
        	  dbUtility.createStatement();
        	  String number1 = dbUtility.executeQry(SQL1);
        	  if (number1.equalsIgnoreCase("0")){
        			String drpqry = RegCommonSQL.DROP_REG_TXN_ID_SEQ_1;				
        			dbUtility.createStatement();
        			dbUtility.executeUpdate(drpqry);
        			String crtqry = RegCommonSQL.CREATE_REG_TXN_ID_SEQ_1;
        			
        			dbUtility.createStatement();
        			dbUtility.executeUpdate(crtqry);
        		}
        	
      	dbUtility.createStatement();
        	regTxnIdSeq = dbUtility.executeQry(RegCommonSQL.GET_REG_TXN_ID_SEQ);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getRegTxnIdSeq-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return regTxnIdSeq;

}
	/**
	 * for getting new sequence id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getNewRegTxnIdSeq() throws Exception {

       // int regTxnIdSeq = 0;
        String regTxnIdSeq="0";
        
        try {
        	dbUtility = new DBUtility();
        	//dbUtility.createStatement();
        	dbUtility.createStatement();
        	dbUtility.executeUpdate(RegCommonSQL.DROP_REG_TXN_ID_SEQ);
        	dbUtility.createStatement();
        	dbUtility.executeUpdate(RegCommonSQL.RESTART_REG_TXN_ID_SEQ);
        	regTxnIdSeq = dbUtility.executeQry(RegCommonSQL.GET_REG_TXN_ID_SEQ);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getRegTxnIdSeq-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return regTxnIdSeq;

}
	/**
	 * for inserting registration initiation txn details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
/*	public boolean insertTxnDetails(String[] party) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.INSERT_REG_INIT_TXN_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(party);
			if(boo){
				boo=false;
				dbUtility.commit();
				String param1[]=new String[1];
				param1[0]=party[0];
				sql = RegCommonSQL.UPDATE_REG_INIT_TXN_PARTY_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(param1);
				if(boo)
					dbUtility.commit();
				else
					dbUtility.rollback();
			}
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}*/
	public boolean insertTxnDetails(String[] param) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			
				sql = RegCommonSQL.UPDATE_PAYMENT_FLAG;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(param);
				if(boo)
				{
					
					if(param[0].trim().equalsIgnoreCase(RegInitConstant.PAYMENT_FLAG_COMPLETED)){
						
						String[] statusParam={RegInitConstant.STATUS_FINAL_SCREEN,param[1].trim()};
						
						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						boo=dbUtility.executeUpdate(statusParam);
						if(boo){
						dbUtility.commit();
						}else{
							dbUtility.rollback();
						}
						
					}else{
					dbUtility.commit();
					}
				}
				else
					dbUtility.rollback();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * for inserting mapping details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertMappingDetails(String[] ownerId, String[] other) {
		boolean boo=false;
		
		int ownerCount=ownerId.length;
		try{
			dbUtility = new DBUtility();
			
		for(int i=0; i<ownerCount; i++){
			String[] param=new String[6];
			param[0]=other[0];
			param[1]=other[1];	
			param[2]=ownerId[i];
			param[3]=other[2];
			//param[4]=other[3];
			param[4]=other[3];
			param[5]=other[4];
			
			try {
				
				sql = RegCommonSQL.INSERT_POA_OWNER_MAPPING;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(param);
				if(boo)
					dbUtility.commit();
				else
					dbUtility.rollback();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		
		return boo;
	}
	/**
	 * getPreviousRegIdDate
	 * for getting latest reg id date from db
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public String[] getPreviousRegIdDate() throws Exception {
		String[] previousDate = new String[3];
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			previousDate[0] = dbUtility.executeQry(RegCommonSQL.PREVIOUS_REG_ID_DD);
			dbUtility.createStatement();
			previousDate[1] = dbUtility.executeQry(RegCommonSQL.PREVIOUS_REG_ID_MM);
			dbUtility.createStatement();
			previousDate[2] = dbUtility.executeQry(RegCommonSQL.PREVIOUS_REG_ID_YY);
			
		} catch (Exception exception) {
			System.out.println("Exception in getPreviousRegIdDate()" + exception);
		}
		return previousDate;

	}
	/**
	 * getPreviousRegIdDate
	 * for getting latest reg id date from db
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 *//*
	public String getPreviousRegIdDate() throws Exception {
		String previousDate = new String();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			previousDate = dbUtility.executeQry(RegCommonSQL.PREVIOUS_CREATED_DATE);
			
			
		} catch (Exception exception) {
			System.out.println("Exception in getPreviousRegIdDate()" + exception);
		}
		return previousDate;

	}*/
	/**
	 * getCurrDateTime
	 * for getting current system date/time from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getCurrDateTime() throws Exception {
		String currDateTime = new String();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			currDateTime = dbUtility.executeQry(RegCommonSQL.CURRENT_DATE_TIME);
			
		} catch (Exception exception) {
			logger.debug("Exception in getCurrDateTime" + exception);
		}
		return currDateTime;

	}
	/**
	 * getApplicantRegistrationDetls
	 * for getting applicant details registration initiation from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantRegistrationDetls(String UserId) throws Exception {
		ArrayList appRegDetls=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={UserId};
			String query=RegCommonSQL.GET_APPLICANT_REG_DETLS;
			dbUtility.createPreparedStatement(query);
			appRegDetls = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getApplicantRegistrationDetls" + exception);
		}
		return appRegDetls;

	}
	/**
	 * getPendingRegApps
	 * for getting pending applications of registration initiation from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPendingRegApps(String userId,int fromAdju)
	{
		ArrayList pendingAppList = new ArrayList();
		ArrayList pendingAppListFinal = new ArrayList();
		
		
		
		ArrayList list1 = new ArrayList();
		
		String[] param={userId,userId};
		
		try {
			dbUtility = new DBUtility();
			
			if(fromAdju==1){
				sql = RegCommonSQL.GET_PENDING_APPLICATIONS_DETLS_ADJU;
			}else{
				sql = RegCommonSQL.GET_PENDING_APPLICATIONS_DETLS;
			}
					dbUtility.createPreparedStatement(sql);
				
					
					try
					{	
						
						pendingAppList=dbUtility.executeQuery(param);
				            logger.debug("Wipro in RegCommonDAO - getPendingRegApps() after dbUtility.executeQuery(sql)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
	
	/**
	 * getSavedRegInitApplication
	 * for getting pending applications of registration initiation from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSavedRegInitApplication(String appId)
	{
		ArrayList savedAppDets = new ArrayList();
		/*ArrayList savedTxnPartyDets = new ArrayList();
		ArrayList savedPoaOwnerMapDets = new ArrayList();
		ArrayList savedRegInitTxnDets = new ArrayList();*/
		ArrayList finalList = new ArrayList();
		
		
		
		//ArrayList list1 = new ArrayList();
		

		
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
					sql = RegCommonSQL.GET_SAVED_AAPLICANT_DETLS;
					dbUtility.createPreparedStatement(sql);
					try
					{	
						savedAppDets=dbUtility.executeQuery(param);
				            logger.debug("Wipro in RegCommonDAO - getPendingRegApps() after dbUtility.executeQuery(sql)");
				         if(savedAppDets!=null && savedAppDets.size()>0){
				        	 finalList.add(savedAppDets) ;
				        	/* sql = RegCommonSQL.GET_SAVED_TXN_PARTY_DETLS+"'"+appId+"'";
								dbUtility.createStatement();
								try
								{	
									savedTxnPartyDets=dbUtility.executeQuery(sql);
									if(savedTxnPartyDets!=null && savedTxnPartyDets.size()>0){
										finalList.add(savedTxnPartyDets) ;
										sql = RegCommonSQL.GET_SAVED_POA_OWNER_MAP_DETLS+"'"+appId+"'";
										dbUtility.createStatement();
										try
										{	
											savedPoaOwnerMapDets=dbUtility.executeQuery(sql);
											if(savedPoaOwnerMapDets!=null && savedPoaOwnerMapDets.size()>0)
											finalList.add(savedPoaOwnerMapDets) ;
										}
										catch (Exception x) {
											logger.debug(x);
											x.printStackTrace();
										}
										sql = RegCommonSQL.GET_SAVED_REGINIT_TXN_DETLS+"'"+appId+"'";
										dbUtility.createStatement();
										try
										{	
											savedRegInitTxnDets=dbUtility.executeQuery(sql);
											if(savedRegInitTxnDets!=null && savedRegInitTxnDets.size()>0)
											finalList.add(savedRegInitTxnDets) ;
										}
										catch (Exception x) {
											logger.debug(x);
											x.printStackTrace();
										}
									}
									
									
								}
								catch (Exception x) {
									logger.debug(x);
									x.printStackTrace();
								} 
				        	 */
				         }
				            
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return finalList;
		}
	/**
	 * for inserting registration initiation other property details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean updatePropertyDetails(String[] param,String[] khasra,String action,String[] regStatus) {
		boolean boo=false;
		String khasraNo;
		String rinPustika;
		String khasraArea;
		String lagaan;
		String[] khasraNoArr;
		String[] rinPustikaArr;
		String[] khasraAreaArr;
		String[] lagaanArr;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.UPDATE_REG_INIT_PROP_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				
				if(khasra!=null)                        //khasra insertion will only take place if khasra details have been added
				{
				if(action.equalsIgnoreCase(RegInitConstant.SAVE_PROP_ACTION)){
					String[] khasraParam={param[19]};
					sql = RegCommonSQL.DELETE_REG_INIT_PROP_KHASRA_DETLS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(khasraParam);
					
				}
				
				if(boo)
				//BELOW CODE FOR INSERTING KHASRA DETAILS INTO DATABASE
				{
				khasraNo=khasra[0];   
				khasraNoArr=khasraNo.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				
				rinPustika=khasra[1];   
				rinPustikaArr=rinPustika.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				
				khasraArea=khasra[2];   
				khasraAreaArr=khasraArea.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				
				lagaan=khasra[3];   
				lagaanArr=lagaan.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				
				String[] khasraParam=new String[6];
				
				for(int i=0;i<khasraNoArr.length;i++){
					
					khasraParam[0]=param[19];               //PROPERTY ID
					khasraParam[1]=khasraNoArr[i].trim();
					khasraParam[2]=rinPustikaArr[i].trim();
					khasraParam[3]=khasraAreaArr[i].trim();
					khasraParam[4]=lagaanArr[i].trim();
					khasraParam[5]=param[10];               //CREATED BY
					
					sql = RegCommonSQL.INSERT_REG_INIT_PROP_KHASRA_DETLS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(khasraParam);
					
					if(!boo){
						break;
					}
					
				}
			}else{
				dbUtility.rollback();
				
			}
				
			}
				
				if(boo){
					
					if(action.equalsIgnoreCase(RegInitConstant.NEXT_TO_CONFIRM_ACTION) || 
							action.equalsIgnoreCase(RegInitConstant.ADD_MORE_PROP_ACTION) ||
							action.equalsIgnoreCase(RegInitConstant.ADD_NEXT_PROP_DETLS_ACTION)){
						
						
						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						boo=dbUtility.executeUpdate(regStatus);
						if(boo){
						dbUtility.commit();
						}else{
							dbUtility.rollback();
						}
					
					}
				else{
					dbUtility.commit();
				}
				}else{
					dbUtility.rollback();
					
				}
				
			
			}
			else{
				dbUtility.rollback();
			}
		}catch (Exception e) {
			boo=false;
			try{
			dbUtility.rollback();
			}catch(Exception ex){
				logger.debug("Exception while rollback in RegCommonDAO:updatePropertyDetails");
			}
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * for getting sequence id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropIdSeq() throws Exception {

       // int regTxnIdSeq = 0;
        String regPropIdSeq="0";
        
        try {
        	dbUtility = new DBUtility();
        	dbUtility.createStatement();
        	regPropIdSeq = dbUtility.executeQry(RegCommonSQL.GET_REG_PROPERTY_ID_SEQ);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getPropIdSeq-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return regPropIdSeq;

}
	/**
	 * for deleting partial saved applications from db.
	 * @param String
	 * @return boolean
	 * @author ROOPAM
	 */
	/*public boolean deleteSelectedAppDetails(String appId) {
		boolean boo=false;
	    try{
			dbUtility = new DBUtility();
			try {
				
				sql = RegCommonSQL.DEL_REG_INIT_TRANS_DETLS+"'"+appId+"'";
				dbUtility.createStatement();
				boo=dbUtility.executeUpdate(sql);
				if(boo){
					logger.debug("tansacting party details deleted");
					//dbUtility.commit();
					sql = RegCommonSQL.DEL_REG_INIT_PROP_DETLS+"'"+appId+"'";
					dbUtility.createStatement();
					boo=dbUtility.executeUpdate(sql);
					if(boo){
						logger.debug("property details deleted");
						sql = RegCommonSQL.DEL_REG_INIT_MAP_DETLS+"'"+appId+"'";
						dbUtility.createStatement();
						boo=dbUtility.executeUpdate(sql);
						if(boo){
							logger.debug("mapping details deleted");
							sql = RegCommonSQL.DEL_REG_INIT_DEED_DETLS+"'"+appId+"'";
							dbUtility.createStatement();
							boo=dbUtility.executeUpdate(sql);
							if(boo){
								logger.debug("deed details deleted");
								dbUtility.commit();
							}
							else
								dbUtility.rollback();
					}
					}
				}
				else
					dbUtility.rollback();
			}catch (Exception e) {
				logger.debug(e);
			}
		
		}
		catch(Exception e) {
			logger.debug(e);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		
		return boo;
	}*/
	public boolean deleteSelectedAppDetails(String appId) {
		boolean boo=false;
		
		
		//try{
		//	dbUtility = new DBUtility();
			
		
			
			try {
				dbUtility = new DBUtility();
				String[] param={appId};
				sql = RegCommonSQL.DEL_REG_INIT_TXN_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(param);
				if(boo){
					dbUtility.commit();
					logger.debug("-------------"+appId+"----------application deleted");
					
				}
					/*//dbUtility.commit();
					sql = RegCommonSQL.DEL_REG_INIT_PROP_DETLS+"'"+appId+"'";
					//dbUtility.createStatement();
					boo=dbUtility.executeUpdate(sql);
					if(boo){
						logger.debug("property details deleted");
					}
					sql = RegCommonSQL.DEL_REG_INIT_MAP_DETLS+"'"+appId+"'";
					//dbUtility.createStatement();
					boo=dbUtility.executeUpdate(sql);
					if(boo){
						logger.debug("mapping details deleted");
					}
						sql = RegCommonSQL.DEL_REG_INIT_DEED_DETLS+"'"+appId+"'";
						//dbUtility.createStatement();
						boo=dbUtility.executeUpdate(sql);
					if(boo){
						logger.debug("deed details deleted");
						dbUtility.commit();
					}*/
					else
					{
						dbUtility.rollback();
					}
			
			}catch (Exception e) {
				logger.debug(e);
			}
		
		//}
		//catch(Exception e) {
		//	logger.debug(e);
		//}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		
		return boo;
	}
	/**
	 * getRegInitPendingAppStatus
	 * for getting pending reg init application details from db
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	/*public String[] getRegInitPendingAppStatus(String appId) throws Exception {
		String[] appStatus = new String[5];
		try {
			dbUtility = new DBUtility();
			
			sql=RegCommonSQL.GET_REG_INIT_DEED_STATUS+"'"+appId+"'";
			dbUtility.createStatement();
			if(dbUtility.executeQry(sql)!=null)
			appStatus[4] = dbUtility.executeQry(sql);
			else
				appStatus[4] = "";
			
			sql=RegCommonSQL.GET_REG_INIT_TXN_STATUS+"'"+appId+"'";
			dbUtility.createStatement();
			if(dbUtility.executeQry(sql)!=null)
			appStatus[3] = dbUtility.executeQry(sql);
			else
				appStatus[3] = "";
			
			
			sql=RegCommonSQL.GET_REG_INIT_MAP_STATUS+"'"+appId+"'";
			dbUtility.createStatement();
			if(dbUtility.executeQry(sql)!=null)
			appStatus[2] = dbUtility.executeQry(sql);
			else
				appStatus[2] = "";
			
			
			sql=RegCommonSQL.GET_REG_INIT_PROP_STATUS+"'"+appId+"'";
			dbUtility.createStatement();
			if(dbUtility.executeQry(sql)!=null)
			appStatus[1] = dbUtility.executeQry(sql);
			else
				appStatus[1] = "";
			
			
			sql=RegCommonSQL.GET_REG_INIT_TRANS_STATUS;
			dbUtility.createPreparedStatement(sql);
			String param[]=new String[2];
			param[0]=appId;
			param[1]=appId;
			if(dbUtility.executeQry(param)!=null)
			appStatus[0] = dbUtility.executeQry(param);
			else
				appStatus[0] = "";
			
		} catch (Exception exception) {
			System.out.println("Exception in getRegInitPendingAppStatus()" + exception);
		}
		return appStatus;

	}*/
	/**
	 * for getting country name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getCountryName(String countryId,String languageLocale) throws Exception {

       // int regTxnIdSeq = 0;
        String Countryname="";
        
        try {
        	dbUtility = new DBUtility();
        	String[] param={countryId};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_COUNTRY_NAME;
        	}else{
        		sql=RegCommonSQL.GET_COUNTRY_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	Countryname = dbUtility.executeQry(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getCountryName-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return Countryname;

}
	/**
	 * for fetching the adjudication ID from db
	 * @param String
	 * @return String
	 * @author SHREERAJ
	 */
	public String getAdjFlag(String regID) throws Exception {

	       // int regTxnIdSeq = 0;
	        String Countryname="";
	        
	        try {
	        	dbUtility = new DBUtility();
	        	String[] param={regID};
	        	sql=RegCommonSQL.GET_ADJU_FLAG;
	        	dbUtility.createPreparedStatement(sql);
	        	Countryname = dbUtility.executeQry(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in getAdjFlag-RegCommonDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCommonDAO in dao start" + e.getStackTrace());
				}
			}

	        return Countryname;

	}
	/**
	 * for getting state name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getStateName(String stateId,String languageLocale) throws Exception {

       // int regTxnIdSeq = 0;
        String statename="";
        
        try {
        	dbUtility = new DBUtility();
        	String[] param={stateId};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_STATE_NAME;
        	}else{
        		sql=RegCommonSQL.GET_STATE_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	statename = dbUtility.executeQry(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getStateName-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return statename;

}
	/**
	 * for getting district name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getDistrictName(String distId,String languageLocale) throws Exception {

       // int regTxnIdSeq = 0;
        String distname="";
        
        try {
        	dbUtility = new DBUtility();
        	/*dbUtility.createStatement();
        	sql=RegCommonSQL.GET_DISTRICT_NAME+"'"+distId+"'";
        	distname = dbUtility.executeQry(sql);*/
        	
        	String[] param={distId};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_DISTRICT_NAME;
        	}else{
        		sql=RegCommonSQL.GET_DISTRICT_NAME_HINDI;	
        	}
        	dbUtility.createPreparedStatement(sql);
        	distname = dbUtility.executeQry(param);

        } catch (Exception exception) {

                System.out.println("Exception in getDistrictName-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return distname;

}
	
	/**
	 * for getting valuation id corresponding to registration app id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getValuationId(String appId) throws Exception {

      
        String valuationId="";
        
        try {
        	dbUtility = new DBUtility();
        	String[] param={appId};
        	sql=RegCommonSQL.GET_REG_INIT_PROP_VAL_ID;
        	dbUtility.createPreparedStatement(sql);
        	valuationId = dbUtility.executeQry(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getValuationId-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return valuationId;

}
	/**
	 * getPropDetlsForDashboard
	 * for getting PROPERTY details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public CustomArrayList getPropDetlsForDashboard(String appId) throws Exception {
		CustomArrayList propDetls=new CustomArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_PROP_DETLS_DASHBOARD;
			dbUtility.createPreparedStatement(query);
			propDetls = dbUtility.executeQueryCustomArrayList(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDashboard" + exception);
		}
		return propDetls;

	}
	/**
	 * getPoaForDashboard
	 * for getting PROPERTY details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*public ArrayList getPoaForDashboard(String appId) throws Exception {
		ArrayList poaDetls=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_PARTY_ROLE_ID_DASHBOARD;
			dbUtility.createPreparedStatement(query);
			poaDetls = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDashboard" + exception);
		}
		return poaDetls;

	}*/
	/**
	 * getPoaListFromDb
	 * for getting poa list from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*public ArrayList getPoaListFromDb(String appId) throws Exception {
		ArrayList poaList=new ArrayList();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query=RegCommonSQL.GET_POA_LIST_DASHBOARD+"'"+appId+"'";
			poaList = dbUtility.executeQuery(query);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPoaListFromDb" + exception);
		}
		return poaList;

	}*/
	/**
	 * getOwnerListFromDb
	 * for getting owner list from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*public ArrayList getOwnerListFromDb(String appId) throws Exception {
		ArrayList ownerList=new ArrayList();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query=RegCommonSQL.GET_OWNER_LIST_DASHBOARD+"'"+appId+"'";
			ownerList = dbUtility.executeQuery(query);
			
		} catch (Exception exception) {
			logger.debug("Exception in getOwnerListFromDb" + exception);
		}
		return ownerList;

	}*/
	/**
	 * for getting property id corresponding to registration app id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropertyId(String appId) throws Exception {

      
        String propertyId="";
        
        try {
        	dbUtility = new DBUtility();
        	//dbUtility.createStatement();
        	String param[]={appId,appId};
        	sql=RegCommonSQL.GET_REG_INIT_PROPERTY_ID;
        	dbUtility.createPreparedStatement(sql);
        	propertyId = dbUtility.executeQry(param);
        	
        	logger.debug("Property Id from database----->"+propertyId);
        } catch (Exception exception) {

                System.out.println("Exception in getValuationId-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return propertyId;

}
	/**
	 * getPropertyIdMarketVal
	 * for getting PROPERTY ID AND MARKET VALUE details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdMarketVal(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_PROPERTY_ID_MARKET_VALUE;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropertyIdMarketVal" + exception);
		}
		return list;

	}
	/**
	 * getTransPartyDets
	 * for getting transacting party details corresponding to a PROPERTY ID from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDets(String propId, String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			String param[]=new String[2];
			param[0]=appId;
			param[1]=propId;
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_TRANSACTING_PARTY_DETS;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyDets" + exception);
		}
		return list;

	}
	/**
	 * getApplicantPartyDets
	 * for getting APPLICANT PARTY details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantPartyDets(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_APPLICANT_PARTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getApplicantPartyDets" + exception);
		}
		return list;

	}
	//FOLLOWING METHODS FOR INTEGRATING NEW PAYMENT MODALITY
	/**
	 * for getting payment flag corresponding to registration app id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public ArrayList getPaymentFlag(String appId) throws Exception {

      
        ArrayList paymentList=new ArrayList();
        
        try {
        	dbUtility = new DBUtility();
        	dbUtility.createStatement();
        	String param[]={appId,appId};
        	sql=RegCommonSQL.GET_REG_INIT_PAYMENT_FLAG;
        	dbUtility.createPreparedStatement(sql);
        	paymentList = dbUtility.executeQuery(param);
        	
        	logger.debug("payment flag from database----->"+paymentList);
        } catch (Exception exception) {

                System.out.println("Exception in getPaymentFlag-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return paymentList;

}
	/**
	 * for inserting registration initiation payment txn details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertPaymentDetails(String[] param) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.INSERT_REG_INIT_PAYMENT_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				
					dbUtility.commit();
			}
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * getPartyDetsForViewConfirm
	 * for getting APPLICANT PARTY details from db.
	 * @param String, String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyDetsForViewConfirm(String appId, String partyId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			String param[]={appId.trim(),partyId.trim()};
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_PARTY_DETS;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getApplicantPartyDets" + exception);
		}
		return list;

	}
	/**
	 * for getting photo id proof name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getPhotoIdProofName(String proofId,String languageLocale) throws Exception {
        String proofname="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={proofId};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_PHOTO_PROOF_NAME;
        	}else{
        		sql=RegCommonSQL.GET_PHOTO_PROOF_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	proofname = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getPhotoIdProofName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return proofname;
       }
	/**
	 * getPropertyDetsForViewConfirm
	 * for getting APPLICANT PARTY details from db.
	 * @param String, String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*public ArrayList getPropertyDetsForViewConfirm(String appId, String partyId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			String param[]={appId.trim(),partyId.trim()};
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_PARTY_DETS;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getApplicantPartyDets" + exception);
		}
		return list;

	}*/
	/**
	 * for getting valuation id corresponding to registration app id and property Id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropValuationId(String appId, String propId) throws Exception {

      
        String valuationId="";
        
        try {
        	String param[]={appId,propId};
        	dbUtility = new DBUtility();
        	sql=RegCommonSQL.GET_REG_INIT_PROP_VALUATION_ID;
        	dbUtility.createPreparedStatement(sql);
        	
        	valuationId = dbUtility.executeQry(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getPropValuationId-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return valuationId;

}
	/**
	 * getOtherPropDetsForViewConfirm
	 * for getting other property details from db.
	 * @param String, String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getOtherPropDetsForViewConfirm(String appId, String propId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			String param[]={appId.trim(),propId.trim()};
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_REG_INIT_OTHER_PROP_DETS;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getOtherPropDetsForViewConfirm" + exception);
		}
		return list;

	}
	/**
	 * getDutyDetls
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDutyDetls(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_REG_INIT_DUTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		}
		return list;

	}
	/**
	 * for getting reg init app id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	/*public String getRegInitAppId(String userId) throws Exception {
        String appId="";
        try {
        	dbUtility = new DBUtility();
        	dbUtility.createStatement();
        	sql=RegCommonSQL.GET_PHOTO_PROOF_NAME+"'"+userId+"'";
        	appId = dbUtility.executeQry(sql);
       } catch (Exception exception) {
         System.out.println("Exception in getPhotoIdProofName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return appId;
       }*/
	/**
	 * for inserting multiple property details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	/*public boolean insrtMultiplePropDetls(String[] propDetls) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(propDetls);
			if(boo){
				dbUtility.commit();
				
			}
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}*/
	public boolean insrtMultiplePropDetls(String[] propDetls, String[][] mapTransPartyPropDetls, String[] regStatus) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			
			//if(boo){
			
			sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(propDetls);
			if(boo){
				
				if(mapTransPartyPropDetls!=null){
				for(int i=0;i<mapTransPartyPropDetls.length;i++){
					
					
					sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(mapTransPartyPropDetls[i]);	
					if(!boo){
						break;
					}
				}
				}
				if(boo){
					
					sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(regStatus);
					if(boo){
					dbUtility.commit();
					}else{
						dbUtility.rollback();
					}
					
				}else{
					dbUtility.rollback();
				}
				
				
			}
			else{
				dbUtility.rollback();
			}
			/*}
			else
				dbUtility.rollback();*/
		}catch (Exception e) {
			boo=false;
			try{
				dbUtility.rollback();
			}catch(Exception ex){
				logger.debug("Exception while rollback in RegCommonDAO:insrtMultiplePropDetls");
			}
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * for getting role id of applicant from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantRoleId(String appId) throws Exception {
        String roleId="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={appId}; 
        	
        	sql=RegCommonSQL.GET_APPLICANT_ROLE_ID;
        	dbUtility.createPreparedStatement(sql);
        	roleId = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getApplicantRoleId-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return roleId;
       }
	/**
	 * getShareOfPropList
	 * for getting other property details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*public ArrayList getShareOfPropList(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			String param[]={appId.trim(),appId.trim()};
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_SHARE_OF_PROPERTY_LIST;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getShareOfPropList" + exception);
		}
		return list;

	}*/
	public ArrayList getShareOfPropList(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			//String param[]={appId.trim(),appId.trim()};
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_SHARE_OF_PROPERTY_LIST;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getShareOfPropList" + exception);
		}
		return list;

	}
	/**
	 * getApplicantShareHolders
	 * for getting other property details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*public ArrayList getApplicantShareHolders(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			String param[]={appId.trim(),appId.trim()};
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_APPLICANT_SHARE_HOLDERS;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getApplicantShareHolders" + exception);
		}
		return list;

	}*/
	public ArrayList getApplicantShareHolders(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			//String param[]={appId.trim(),appId.trim()};
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_APPLICANT_SHARE_HOLDERS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getApplicantShareHolders" + exception);
		}
		return list;

	}
	/**
	 * for getting role name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getRoleName(String roleId,String languageLocale) throws Exception {
        String roleName="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={roleId};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_PARTY_ROLE_NAME;
        	}else{
        		sql=RegCommonSQL.GET_PARTY_ROLE_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	roleName = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getRoleName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return roleName;
       }
	/**
	 * for getting transacting party sequence id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getTransactingPartyIdSeq() throws Exception {

       String seqId="0";
       try {
        	dbUtility = new DBUtility();
        	dbUtility.createStatement();
        	seqId = dbUtility.executeQry(RegCommonSQL.GET_REG_TRANS_PARTY_ID_SEQ);
       } catch (Exception exception) {
                System.out.println("Exception in getTransactingPartyIdSeq-RegCommonDAO" + exception);
        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return seqId;
}
	/**
	 * for getting transacting party property map sequence id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getTransPartyPropertyMapIdSeq() throws Exception {

       String seqId="0";
       try {
        	dbUtility = new DBUtility();
        	dbUtility.createStatement();
        	seqId = dbUtility.executeQry(RegCommonSQL.GET_REG_TRANS_PARTY_PROP_MAP_ID_SEQ);
       } catch (Exception exception) {
                System.out.println("Exception in getTransPartyPropertyMapIdSeq-RegCommonDAO" + exception);
        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return seqId;
}
	/**
	 * getOwnerDetls
	 * for getting owner details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getOwnerDetls(String ownerId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={ownerId};
			String query=RegCommonSQL.GET_OWNER_DETAILS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getOwnerDetls" + exception);
		}
		return list;

	}
	/**
	 * getPropDetlsForDutyCalc
	 * for getting property details from db for duty calculation.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropDetlsForDutyCalc(String valuationId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={valuationId};
			String query=RegCommonSQL.GET_PROPERTY_DETAILS_FOR_DUTY_CALC;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDutyCalc" + exception);
		}
		return list;

	}
	/**
	 * getPaymentTxnId
	 * for getting payment transaction id from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPaymentTxnId(String appId) throws Exception {
		ArrayList list=new ArrayList();
		String[] param={appId,appId};
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.GET_REG_INIT_PAYMENT_TXN_ID;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(param);
			
			
			
		} catch (Exception exception) {
			logger.debug("Exception in getPaymentTxnId" + exception);
		}
		return list;

	}
	/**
	 * for inserting registration initiation E STAMP CODE details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertEStampCode(String[] param) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_REG_INIT_ESTAMP_CODE;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				
					dbUtility.commit();
			}
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * for updating Registration txn status in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean updateTransactionStatus(String[] param) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS_WITH_ADDRESS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				
					dbUtility.commit();
			}
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * getDeedInstId
	 * for getting deed and instrument ids from db
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDeedInstId(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_REG_INIT_DEED_INST_ID;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDeedInstId" + exception);
		}
		return list;

	}
	/**
	 * for inserting registration initiation stamp duties details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertStampDuties(String[] param) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_DUTIES_DETAILS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				
					dbUtility.commit();
			}
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	//for building floor details
	/**
	 * getDeedInstId
	 * for getting deed and instrument ids from db
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getGuildingFloorDetails(String valId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={valId};
			String query=RegCommonSQL.GET_BUILDING_FLOOR_DETAILS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getGuildingFloorDetails" + exception);
		}
		return list;

	}
	/**
	 * getDutyDetls
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDutyDetlsForConfirmation(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_REG_INIT_DUTY_DETS_FOR_CONFIRMATION;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetlsForConfirmation" + exception);
		}
		return list;

	}
	/**
	 * for getting deed name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getDeedName(String deedId,String languageLocale) throws Exception {
        String deedName="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={deedId};
        	sql=RegCommonSQL.GET_DEED_NAME;
        	dbUtility.createPreparedStatement(sql);
        	deedName = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getDeedName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return deedName;
       }
	/**
	 * for getting instrument name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getInstrumentName(String instrumentId,String languageLocale) throws Exception {
        String instName="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={instrumentId};
        	sql=RegCommonSQL.GET_INST_NAME;
        	dbUtility.createPreparedStatement(sql);
        	instName = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getInstrumentName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return instName;
       }
	/**
	 * for getting exemption name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getExemptionName(String exempId) throws Exception {
        String exmpName="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={exempId};
        	sql=RegCommonSQL.GET_EXMP_NAME;
        	dbUtility.createPreparedStatement(sql);
        	exmpName = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getExemptionName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return exmpName;
       }

	
	public ArrayList getEstampDet(String tempId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList mainList = new ArrayList();
		//ArrayList subList = new ArrayList();
		try
		{
			dbUtility= new DBUtility();
			String[] param={tempId};
			
			sql=RegCommonSQL.GET_ESTAMP_DETAILS;
			dbUtility.createPreparedStatement(sql);
			mainList=dbUtility.executeQuery(param);
			/*subList = dbUtility.executeQuery(sql);
			if(subList.size()!=0)
			{
				
				for(int i =0;i<subList.size();i++)
				{
					ArrayList list = new ArrayList();
					list = (ArrayList) subList.get(i);
					RegCommonForm form	= new RegCommonForm();
					form.setEstampCode((String)list.get(0));
					form.setEstampAmt((String)list.get(1));
					form.setEstampDateTime((String)list.get(2));
					mainList.add(form);
				}
			}*/
		}
		catch(Exception e)
		{
			logger.error("error in getEstampDet"+e.getStackTrace());
		}
		finally
		{
			try{dbUtility.closeConnection();}
			catch(Exception e)
			{
				logger.error("error in close connection in getEstampDet"+e.getStackTrace());
			}
		}
				
		return mainList ;
	}
/**
	 * getAdjudicationStatus
	 * for getting adjudication id and status from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAdjudicationStatus(String adjuId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={adjuId};
			
			String query=RegCommonSQL.GET_REG_INIT_ADJUDICATION_STATUS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getAdjudicationStatus" + exception);
		}
		return list;

	}
	/**
	 * for updating reg init id corresponding to adjudication id in database
	 * @param String regInitId,String adjuId
	 * @param boolean
	 *
	 */
	public boolean updateAdjudicationRecords(String regInitId,String adjuId, String userId){
		boolean boo=false;
		String[] param={regInitId,userId,adjuId};
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_TRANS_PARTY_TABLE;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				sql = RegCommonSQL.UPDATE_STAMP_DUTY_TABLE;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(param);
				if(boo){
					sql = RegCommonSQL.UPDATE_PROP_DETLS_TABLE;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(param);
					if(boo){
						sql = RegCommonSQL.UPDATE_DEED_DETLS_TABLE;
						dbUtility.createPreparedStatement(sql);
						boo=dbUtility.executeUpdate(param);
						if(boo){
							sql = RegCommonSQL.UPDATE_PROP_TRANS_MAP_TABLE;
							dbUtility.createPreparedStatement(sql);
							boo=dbUtility.executeUpdate(param);
							if(boo){
								
								dbUtility.commit();
							}
							else
							dbUtility.rollback();
							
						}
						else
							dbUtility.rollback();
						
					}
					else
						dbUtility.rollback();
					
				}
				else
					dbUtility.rollback();
				
			}
			else
				dbUtility.rollback();
			
			
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
		finally{
			try{
			dbUtility.closeConnection();
			}
			catch(Exception e){
				logger.debug(e);
			}
		}
		
		return boo;
	}
	/**
	 * getPropertyIdMarketValAdju
	 * for getting PROPERTY ID AND MARKET VALUE details for adjudicated applications from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdMarketValAdju(String adjuId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={adjuId};
			String query=RegCommonSQL.GET_PROPERTY_ID_MARKET_VALUE_ADJU;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropertyIdMarketValAdju" + exception);
		}
		return list;

	}
	/**
	 * getAdjudicatedDutyDetls
	 * for getting adjudicated duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAdjudicatedDutyDetls(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_ADJUDICATED_DUTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getAdjudicatedDutyDetls" + exception);
		}
		return list;

	}
	/**
	 * for getting applicant district id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantDistrict(String appId) throws Exception {
        String distId="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={appId};
        	sql=RegCommonSQL.GET_APPLICANT_DIST_ID;
        	dbUtility.createPreparedStatement(sql);
        	distId = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getApplicantDistrict-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return distId;
       }
	/**
	 * for getting e stamp purpose from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getEStampPurpose(String appId) throws Exception {
        String purpose="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={appId};
        	sql=RegCommonSQL.GET_ESTAMP_PURPOSE;
        	dbUtility.createPreparedStatement(sql);
        	purpose = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getEStampPurpose-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return purpose;
       }
	/**
	 * for updating updating e stamp purpose of registration in database
	 * @param String regInitId,String purpose
	 * @param boolean
	 *
	 */
	public boolean updateEStampPurpose(String regInitId, String purpose){
		boolean boo=false;
		String[] param={purpose,regInitId};
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_ESTAMP_PURPOSE;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				dbUtility.commit();
				
			}
			else
				dbUtility.rollback();
			
			
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
		finally{
			try{
			dbUtility.closeConnection();
			}
			catch(Exception e){
				logger.debug(e);
			}
		}
		
		return boo;
	}
	/**
	 * getAllValuationIdsExchangeDeed
	 * for getting ALL VALUATION IDS from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllValuationIdsExchangeDeed(String finalValId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={finalValId};
		    String query=RegCommonSQL.GET_ALL_VALUATION_IDS_EXCHANGE;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		}
		return list;

	}
	/**
	 * getAllPropDetlsExchangeDeed
	 * for getting ALL PROPERTY DETAILS from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPropDetlsExchangeDeed(String valId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={valId};
			String query=RegCommonSQL.GET_ALL_PROPERTY_DETLS_EXCHANGE;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		}
		return list;

	}
	/**insrtApplcntTransPartyPropDetlsExchange
	 * for inserting applicant details in db.(EXCHANGE DEED)
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insrtApplcntTransPartyPropDetlsExchange(String[] party, String[] deed, ArrayList propDetls, 
			                                               ArrayList propTransDets, String[] ownerDetails, String exemp, String[] statusParam) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			CommonDTO row_list;
			String[] param1=new String[19];
			String[] param2=new String[4];
			if(party[32].equals("") || party[32].equalsIgnoreCase(""))
			{
			sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;     //relationship
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(party);
			}
			else{
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(ownerDetails);
				if(boo){
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;    
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(party);
				}
				else
					dbUtility.rollback();
			}
			if(boo){
				
				if(deed.length==8)
				{
				sql = RegCommonSQL.INSERT_REG_DEED_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(deed);
				if(boo){
					
					//BELOW CODE FOR INSERTION OF PROPERTY DETAILS
					
					sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
					dbUtility.createPreparedStatement(sql);
					
					for(int i=0;i<propDetls.size();i++){
						
						row_list=(CommonDTO)propDetls.get(i);
						param1[0]=row_list.getPropertyId();
						param1[1]=row_list.getRegTxnId();
						param1[2]=row_list.getMarketValue();
						param1[3]=row_list.getAreaTypeId();
						param1[4]=row_list.getGovBodyId();
						param1[5]=row_list.getPropTypeId();
						param1[6]=row_list.getL1TypeId();
						param1[7]=row_list.getL2TypeId();
						param1[8]=row_list.getAreaUnitId();
						param1[9]=row_list.getArea();
						param1[10]=row_list.getDistId();
						param1[11]=row_list.getTehsilId();
						param1[12]=row_list.getWardId();
						param1[13]=row_list.getMohalaId();
						param1[14]=row_list.getUserId();
						param1[15]=row_list.getValuationId();
						param1[16]="N";                                        //PROP_TXN_COMPL_FLAG
						param1[17]=row_list.getMarketValue();                  //INITIAL_MARKET_VALUE
						param1[18]=row_list.getSysMv();                        //SYSTEM MARKET_VALUE
						
						
						
						
					boo=dbUtility.executeUpdate(param1);               //loop
					
					if(!boo)
						break;
					
					}
					
					//BELOW CODE FOR INSERTION OF EXEMPTIONS
					if(boo){
						
						String[] exempParam = new String[3];
						if(exemp!=null && !exemp.equalsIgnoreCase("")){
							
						String[] exempArr=exemp.split(",");	
						if(exempArr!=null && exempArr.length>0){
											
							for(int i=0;i<exempArr.length;i++){
								
								
								exempParam[0]=party[0];
								exempParam[1]=exempArr[i].trim();
								exempParam[2]=party[30];
								
								
								sql = RegCommonSQL.INSERT_REG_EXEMPTION_DETLS;
								dbUtility.createPreparedStatement(sql);
								boo=dbUtility.executeUpdate(exempParam);
								
								if(!boo){
									break;
								}
								
							}
						}
					}
		    	}else{
		    		dbUtility.rollback();
		    	}
					
					
				}	
				else
					dbUtility.rollback();
				}
				if(boo){
					
					
						//BELOW CODE FOR INSERTION OF PROPERTY AND TRANSACTING PARTY MAP DETAILS
						
						sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
						dbUtility.createPreparedStatement(sql);
						for(int i=0;i<propTransDets.size();i++){
							
							row_list=(CommonDTO)propTransDets.get(i);
							
							param2[0]=row_list.getRegTxnId();
							param2[1]=row_list.getPropertyId();
							param2[2]=row_list.getPartyTxnId();
							param2[3]=row_list.getUserId();
							
						boo=dbUtility.executeUpdate(param2);               //loop
						
						if(!boo)
							break;
						
						}
						if(boo){

							
							if(statusParam!=null){
							sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
							dbUtility.createPreparedStatement(sql);
							boo=dbUtility.executeUpdate(statusParam);
							if(boo){
							dbUtility.commit();
							}else{
								dbUtility.rollback();
							}
							}
							else{
								dbUtility.commit();
							}
						}
						else{
							dbUtility.rollback();
						}
					
				}
				else				
					dbUtility.rollback();
			}
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * getExchangeDeedDutyDetls
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getExchangeDeedDutyDetls(String refValId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={refValId};
			String query=RegCommonSQL.GET_REG_INIT_DUTY_DETS_EXCHANGE_DEED;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getExchangeDeedDutyDetls" + exception);
		}
		return list;

	}
	/**
	 * getRefValIdExchngDeed
	 * for getting reference valuation id of Exchange Deed from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String getRefValIdExchngDeed(String appId) throws Exception {
        String refValId="";
        try {
        	String[] param={appId};
        	dbUtility = new DBUtility();
        	sql=RegCommonSQL.GET_REF_VAL_ID_EXCHNG_DEED;
        	dbUtility.createPreparedStatement(sql);
        	refValId = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getRefValIdExchngDeed-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return refValId;
       }
	/**
	 * getExchangeDeedFinalMV
	 * for getting FINAL MARKET VALUE of Exchange Deed from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExchangeDeedFinalMV(String refValId) throws Exception {
        String finalMV="";
        try {
        	
        	dbUtility = new DBUtility();
        	String[] param={refValId};
        	sql=RegCommonSQL.GET_EXCHNG_DEED_FINAL_MV;
        	dbUtility.createPreparedStatement(sql);
        	finalMV = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getExchangeDeedFinalMV-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return finalMV;
       }
	/**
	 * getSubClauseListNonBuilding
	 * for getting sub clause list non building from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSubClauseListNonBuilding(String valId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={valId};
			String query=RegCommonSQL.GET_SUB_CLAUSE_LIST_NON_BUILDING;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getSubClauseListNonBuilding" + exception);
		}
		return list;

	}
	/**
	 * for getting sub clause name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getSubClauseName(String Id) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={Id};
        	sql=RegCommonSQL.GET_SUBCLASE_NAME;
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getSubClauseName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	/**
	 * getSubClauseListBuilding
	 * for getting sub clause list building from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSubClauseListBuilding(String[] param) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_SUB_CLAUSE_LIST_BUILDING;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getSubClauseListBuilding" + exception);
		}
		return list;

	}
	//ADDED BY SHRUTI
	public String getDeedId(int duty_txn_id) {
		String deed="";
		String[] param={Integer.toString(duty_txn_id)};
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_DEED_ID;
	        dbUtility.createPreparedStatement(sql);
	        deed= dbUtility.executeQry(param);
	        /*ArrayList list = dbUtility.executeQuery(param);
	        mainList = new ArrayList();
	        ArrayList subList = null;
	        for (int i = 0; i < list.size(); i++) {
	            subList = (ArrayList)list.get(i);
	            dto = new CommonDTO();
	            dto.setId(subList.get(0).toString());
	            mainList.add(dto);
	        	}*/
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCommonDAO in dao start" + e.getStackTrace());
				}
			}
	        return deed;
	}
	public ArrayList getExempId(int duty_txn_id) {
		ArrayList exempid=new ArrayList();
		String[] param={Integer.toString(duty_txn_id)};
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_EXEMPTION_ID;
	        dbUtility.createPreparedStatement(sql);
	        exempid= dbUtility.executeQuery(param);
	        /*ArrayList list = dbUtility.executeQuery(param);
	        mainList = new ArrayList();
	        ArrayList subList = null;
	        for (int i = 0; i < list.size(); i++) {
	            subList = (ArrayList)list.get(i);
	            dto = new CommonDTO();
	            dto.setId(subList.get(0).toString());
	            mainList.add(dto);
	        	}*/
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCommonDAO in dao start" + e.getStackTrace());
				}
			}
	        return exempid;
	}
	public String getInstrumentId(int duty_txn_id) {
		String instid="";
		String[] param={Integer.toString(duty_txn_id)};
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_INSTRUMENT_ID;
	        dbUtility.createPreparedStatement(sql);
	        instid= dbUtility.executeQry(param);
	        /*ArrayList list = dbUtility.executeQuery(param);
	        mainList = new ArrayList();
	        ArrayList subList = null;
	        for (int i = 0; i < list.size(); i++) {
	            subList = (ArrayList)list.get(i);
	            dto = new CommonDTO();
	            dto.setId(subList.get(0).toString());
	            mainList.add(dto);
	        	}*/
			 }catch (Exception e) {
				 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCommonDAO in dao start" + e.getStackTrace());
				}
			}
	        return instid;
	}
	/**
	 * for inserting applicant details in db in case of deposit deed.
	 * @param String[]
	 * @return boolean
	 * @author Shruti
	 */
	public boolean insrtDepositDeedApplcntTransPartyBnkDetls(String[] party, String[] deed, String[] bankDetls,String[] ownerDetails, String exemp) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			if(party[32].equals("") || party[32].equalsIgnoreCase(""))
			{
			sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;         
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(party);
			}
			else{
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(ownerDetails);
				if(boo){
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;        
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(party);
				}
				else
					dbUtility.rollback();
			}
			if(boo){
				
				if(deed.length==9)
				{
				sql = RegCommonSQL.INSERT_REG_TXN_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(deed);
				//BELOW CODE FOR INSERTION OF EXEMPTIONS
				if(boo){
					
					String[] exempParam = new String[3];
					if(exemp!=null && !exemp.equalsIgnoreCase("")){
						
					String[] exempArr=exemp.split("-");	
					if(exempArr!=null && exempArr.length>0){
										
						for(int i=0;i<exempArr.length;i++){
							
							
							exempParam[0]=party[0];
							exempParam[1]=exempArr[i].trim();
							exempParam[2]=party[30];
							
							
							sql = RegCommonSQL.INSERT_REG_EXEMPTION_DETLS;
							dbUtility.createPreparedStatement(sql);
							boo=dbUtility.executeUpdate(exempParam);
							
							if(!boo){
								break;
							}
							
						}
					}
					}
				}
				
				}
				
				if(bankDetls!=null){                                         //for title deed and trust deed only.
				if(boo)
				{
					sql=RegCommonSQL.INSERT_REG_DEED_EXTRA_DTLS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(bankDetls);
					if(boo)
					{
						dbUtility.commit();
					}
					else
					{
						dbUtility.rollback();
					}
				}
				else{ 
					dbUtility.rollback();
				}
				}else{
					dbUtility.commit();
				}
				
				
				
			}
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	public ArrayList getPropTypeL1List(String propId,String languageLocale) throws Exception {
		String[] param={propId};
		dbUtility = new DBUtility();
		//dbUtility.createPreparedStatement(RegCommonSQL.SELECTPROPERTYTYPEL1DETAILS);
		dbUtility.createPreparedStatement(RegCommonSQL.SELECTPROPERTYTYPEL1DETAILS_HINDI);
		ArrayList ret=dbUtility.executeQuery(param);
		
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				RegCompletDTO dto = new RegCompletDTO();
				logger.debug("Dist:-" + lst.get(0) + ":" + lst.get(1));
				dto.setPropTypeL1Id((String) lst.get(0));
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setPropTypeL1((String) lst.get(1));
				dto.setHdnPropTypeL1((String) lst.get(0) + "~"
						+ (String) lst.get(1));
				}else{
					dto.setPropTypeL1((String) lst.get(2));
					dto.setHdnPropTypeL1((String) lst.get(0) + "~"
							+ (String) lst.get(2));
				}
				list.add(dto);
			}

		}
		return list;
	}
	/**
	 * getNonPropDutyDetls
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author Shruti
	 */
	public ArrayList getNonPropDutyDetls(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_REG_NON_PROP_DUTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getNonPropDutyDetls" + exception);
		}
		return list;

	}
	//END OF CODE BY SHRUTI
	
	
	/**
	 * for getting all Categories from DB.
	 * @return ArrayList
	 */
	public ArrayList getCategoryList(String languageLocale) {
		ArrayList mainList = new ArrayList();
		 try {
			 dbUtility = new DBUtility();
		//sql = RegCommonSQL.SELECT_CATEGORY;
			 sql = RegCommonSQL.SELECT_CATEGORY_HINDI;
        dbUtility.createStatement();
        ArrayList list = dbUtility.executeQuery(sql);
       // mainList = new ArrayList();
        ArrayList subList = null;
        CommonDTO dto;
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new CommonDTO();
            dto.setId(subList.get(0).toString());
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());
            }
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	/**
	 * for getting category name from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getCategoryName(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_CATEGORY_NAME;
        	}else{
        		sql=RegCommonSQL.GET_CATEGORY_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getCategoryName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	/**
	 * getPropertyDetailsFromValuation
	 * for getting property details from valuation tables.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyDetailsFromValuation(String valId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={valId};
			String query=RegCommonSQL.GET_PROP_DETLS_FROM_VAL;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropertyDetailsFromValuation" + exception);
		}
		return list;

	}
	/**
	 * getExemptionList
	 * for getting exemption ids from db
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getExemptionList(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_REG_EXEMPTION_DETLS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getExemptionList" + exception);
		}
		return list;

	}
	/**
	 * getPropDetlsForDisplay
	 * for getting PROPERTY details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public CustomArrayList getPropDetlsForDisplay(String propId) throws Exception {
		CustomArrayList propDetls=new CustomArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={propId};
			String query=RegCommonSQL.GET_PROP_DETLS_DISPLAY;
			dbUtility.createPreparedStatement(query);
			propDetls = dbUtility.executeQueryCustomArrayList(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDisplay" + exception);
		}
		return propDetls;

	}
	/**
	 * getPropKhasraDetlsForDisplay
	 * for getting PROPERTY KHASRA details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropKhasraDetlsForDisplay(String propId) throws Exception {
		ArrayList propDetls=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={propId};
			String query=RegCommonSQL.GET_PROP_KHASRA_DETLS_DISPLAY;
			dbUtility.createPreparedStatement(query);
			propDetls = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropKhasraDetlsForDisplay" + exception);
		}
		return propDetls;

	}
	/**
	 * for getting all Occupation List
	 * @return ArrayList
	 */
	public ArrayList getOccupationList(String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
		//sql = RegCommonSQL.GET_OCCUPATION_LIST;
			sql = RegCommonSQL.GET_OCCUPATION_LIST_HINDI;
        dbUtility.createStatement();
        ArrayList list = dbUtility.executeQuery(sql);
       // mainList = new ArrayList();
        ArrayList subList = null;
        CommonDTO dto;
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new CommonDTO();
            dto.setId(subList.get(0).toString());
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());
            }
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	/**
	 * for getting occupation name from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getOccupationName(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_OCCUPATION_NAME;
        	}else{
        		sql=RegCommonSQL.GET_OCCUPATION_NAME_HINDI;	
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getOccupationName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	/**
	 * for getting applicant share from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantShare(String id) throws Exception {
        String share="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	sql=RegCommonSQL.GET_APPLICANT_SHARE;
        	dbUtility.createPreparedStatement(sql);
        	share = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getApplicantShare-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return share;
       }
	/**
	 * getPartyTxnIdList
	 * for getting party txn id list corresponding to property id given
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyTxnIdList(String propId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={propId};
			String query=RegCommonSQL.GET_PARTY_TXN_ID_LIST;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPartyTxnIdList" + exception);
		}
		return list;

	}
	public ArrayList getPartyDetailsPdf(String partyId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			//String param[]={appId.trim(),appId.trim()};
			dbUtility = new DBUtility();
			String[] param={partyId};
			String query=RegCommonSQL.GET_PARTY_DETAILS_PDF;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPartyDetailsPdf" + exception);
		}
		return list;

	}
	/**
	 * getGuidelineRate
	 * for getting current guideline rate from db.
	 * @param String[]
	 * @return String
	 * @author ROOPAM
	 */
	public String getGuidelineRate(String[] param) throws Exception {
		String rate="";
		String[] finalParam;
		try {
			dbUtility = new DBUtility();
			String query="";
			if(param[6]==null || param[6].equalsIgnoreCase("") || param[6].equalsIgnoreCase("null")){
				finalParam=new String[6];
				finalParam[0]=param[0];
				finalParam[1]=param[1];
				finalParam[2]=param[2];
				finalParam[3]=param[3];
				finalParam[4]=param[4];
				finalParam[5]=param[5];
			query=RegCommonSQL.GET_GUIDELINE_RATE_WITHOUT_L2;
			}else{
				finalParam=param;
				query=RegCommonSQL.GET_GUIDELINE_RATE_WITH_L2;
			}
			dbUtility.createPreparedStatement(query);
			
			rate = dbUtility.executeQry(finalParam);
			
		} catch (Exception exception) {
			logger.debug("Exception in getGuidelineRate" + exception);
		}
		return rate;

	}
	/**
	 * for inserting transacting party details title deed in db.
	 * @param String[],String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insrtTransPartyDetlsTitleDeed(String[] party, String[] ownerDetails, String[] statusParam) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			if(party[32].equals("") || party[32].equalsIgnoreCase(""))
			{
			sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;            //relationship
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(party);
			}
			else{
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(ownerDetails);
				if(boo){
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;        
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(party);
				}
				else
					dbUtility.rollback();
			}
			if(boo){
				
				
				if(statusParam!=null){
				sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(statusParam);
				if(boo){
				dbUtility.commit();
				}else{
					dbUtility.rollback();
				}
				}
				else{
					dbUtility.commit();
				}
				
				
				
			}
			else{
				dbUtility.rollback();
			}
		}catch (Exception e) {
			boo=false;
			try{
				dbUtility.rollback();
			}catch(Exception ex){
				logger.debug("Exception in RegCommonDAO:insrtTransPartyDetlsTitleDeed rollback.");
			}
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * for getting property type l2 list
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropTypeL2List(String propL1Id) throws Exception {
		String[] param={propL1Id};
		dbUtility = new DBUtility();
		//dbUtility.createPreparedStatement(RegCommonSQL.SELECT_PROPERTY_TYPE_L2_DETAILS);
		dbUtility.createPreparedStatement(RegCommonSQL.SELECT_PROPERTY_TYPE_L2_DETAILS_HINDI);
		ArrayList ret=dbUtility.executeQuery(param);
		
		if(ret!=null){
		return ret;
		}
		else{
			return new ArrayList();
		}
	}
	/**
	 * for getting unit list
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getUnitList(String propL1Id) throws Exception {
		String[] param={propL1Id};
		dbUtility = new DBUtility();
		//dbUtility.createPreparedStatement(RegCommonSQL.SELECT_UNIT_LIST_DETAILS);
		dbUtility.createPreparedStatement(RegCommonSQL.SELECT_UNIT_LIST_DETAILS_HINDI);
		ArrayList ret=dbUtility.executeQuery(param);
		
		if(ret!=null){
		return ret;
		}
		else{
			return new ArrayList();
		}
	}
	/**
	 * for getting property type name from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropertyTypeName(String id, String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_PROPERTY_TYPE_NAME;
        	}else{
        		sql=RegCommonSQL.GET_PROPERTY_TYPE_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getPropertyTypeName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	/**
	 * for inserting registration initiation other property details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertPropertyDetailsNonPropDeeds(String[] param,String[] khasra,HashMap floorMap,String[] regStatus,String[] mapParam) {
		boolean boo=false;
		String khasraNo;
		String rinPustika;
		String khasraArea;
		String lagaan;
		String[] khasraNoArr;
		String[] rinPustikaArr;
		String[] khasraAreaArr;
		String[] lagaanArr;
		String[] floorArr=new String[8];
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_REG_PROP_DETLS_NON_PROP_DEEDS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				
				//BELOW CODE FOR INSERTING KHASRA DETAILS INTO DATABASE
				if(khasra!=null)
				{	
				/*if(!khasra[0].equalsIgnoreCase(""))
				{*/				  
				khasraNoArr  =khasra[0].split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				rinPustikaArr=khasra[1].split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				khasraAreaArr=khasra[2].split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				lagaanArr    =khasra[3].split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				/*}else{
					khasraNoArr=new String[0];
					rinPustikaArr=new String[0];
					khasraAreaArr=new String[0];
					lagaanArr=new String[0];
				}*/
				String[] khasraParam=new String[6];
				
				for(int i=0;i<khasraNoArr.length;i++){
					
					khasraParam[0]=param[0];               //PROPERTY ID
					khasraParam[1]=khasraNoArr[i].trim();
					khasraParam[2]=rinPustikaArr[i].trim();
					khasraParam[3]=khasraAreaArr[i].trim();
					khasraParam[4]=lagaanArr[i].trim();
					khasraParam[5]=param[25];               //CREATED BY
					
					sql = RegCommonSQL.INSERT_REG_INIT_PROP_KHASRA_DETLS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(khasraParam);
					
					if(!boo){
						break;
					}
					
				}
				
			}
				
				if(boo){
					
					if(param[15].equalsIgnoreCase("2") && !floorMap.isEmpty())//check for building
					{
						//code for floor insertion
						
						Collection mapCollection=floorMap.values();
	                    Object[] l1=mapCollection.toArray();
	                    RegCompletDTO mapDto1 =new RegCompletDTO();
	                    String[] splitArr;
	                  
	                   // boolean floorDetailsInserted=false;
	                   
	                    for(int i=0;i<l1.length;i++)
	                    {
	                    	mapDto1=(RegCompletDTO)l1[i];
	                    	
	                    	floorArr[0]=param[0];
	                    	splitArr=mapDto1.getPropTypeL1Id().split("~");
	                    	floorArr[1]=splitArr[0];
	                    	
	                    	if(mapDto1.getPropTypeL2Id()!=null && !mapDto1.getPropTypeL2Id().equalsIgnoreCase("Select")){
	                    		splitArr=mapDto1.getPropTypeL2Id().split("~");
	                    		floorArr[2] = splitArr[0];
	                    		}else{
	                    			floorArr[2] = "";
	                    		}
	                    	
	                    	splitArr=mapDto1.getTypeFloorID().split("~");
	                    	floorArr[3]=splitArr[0];
	                    	floorArr[4]=Float.toString(mapDto1.getAreaConstructed());
	                    	floorArr[5]=Float.toString(mapDto1.getArea());
	                    	splitArr=mapDto1.getUnitTypeId().split("~");
	                    	floorArr[6]=splitArr[0];
	                    	floorArr[7]=param[25];
	                    	
	                    	
	                    	sql = RegCommonSQL.INSERT_REG_PROP_FLOOR_DETLS;
	    					dbUtility.createPreparedStatement(sql);
	    					boo=dbUtility.executeUpdate(floorArr);
	    					
	    					if(!boo){
	    						break;
	    					}
	  						  
	                    }
						
					if(boo){
						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						boo=dbUtility.executeUpdate(regStatus);
						if(boo){
						//dbUtility.commit();
						}else{
							dbUtility.rollback();
						}
					}else{
						dbUtility.rollback();
					}
					
				}else{
					
					sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(regStatus);
					if(boo){
					//dbUtility.commit();
					}else{
						dbUtility.rollback();
					}
				}
					
					
					//insert prop trans map code here
					
					if(boo){
						
						if(mapParam!=null){
							
							String propDets[]=new String[4];

		                    for(int i=0;i<mapParam.length;i++)
		                    {
		                       	propDets[0]=param[1];
		                		propDets[1]=param[0];
		                		propDets[2]=mapParam[i];
		                		propDets[3]=param[25];
		                    	
		                    	
		                    	sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
		    					dbUtility.createPreparedStatement(sql);
		    					boo=dbUtility.executeUpdate(propDets);
		    					
		    					if(!boo){
		    						break;
		    					}
		  						  
		                    }
							
							if(boo){
								dbUtility.commit();
							}else{
								dbUtility.rollback();
								
							}
							
							
							
							
							
						}else{
							dbUtility.commit();
						}
						
						
						
					}else{
						dbUtility.rollback();
						
					}
					
					
							
					
					
				}else{
					dbUtility.rollback();
					
				}
				
			
			}
			else{
				dbUtility.rollback();
			}
		}catch (Exception e) {
			boo=false;
			try{
			dbUtility.rollback();
			}catch(Exception ex){
				logger.debug("Exception while rollback in RegCommonDAO:updatePropertyDetails");
			}
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * getPropertyListNonPropDeed
	 * for getting PROPERTY ID list from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyListNonPropDeed(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_PROPERTY_ID_LIST_NON_PROP_DEED;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropertyListNonPropDeed" + exception);
		}
		return list;

	}
	/**
	 * getTransPartyDetsNonPropDeed
	 * for getting transacting party details corresponding to a registration id non property deed from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDetsNonPropDeed(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			String param[]=new String[1];
			param[0]=appId;
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_TRANS_PARTY_DETS_NON_PROP_DEED;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyDetsNonPropDeed" + exception);
		}
		return list;

	}
	/**
	 * getBuildingFloorDetailsNonProp
	 * for getting floor details from db in case of non property deed
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getBuildingFloorDetailsNonProp(String propId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={propId};
			String query=RegCommonSQL.GET_BUILDING_FLOOR_DETAILS_NON_PROP;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getBuildingFloorDetailsNonProp" + exception);
		}
		return list;

	}
	/**
	 * for getting payment table primary key sequence id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getPaymentTxnIdSeq() throws Exception {

       String seqId="0";
       try {
        	dbUtility = new DBUtility();
        	dbUtility.createStatement();
        	seqId = dbUtility.executeQry(RegCommonSQL.GET_REG_PAYMENT_TXN_ID_SEQ);
       } catch (Exception exception) {
                System.out.println("Exception in getPaymentTxnIdSeq-RegCommonDAO" + exception);
        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return seqId;
}
	/**
	 * getPaidAmounts
	 * for getting all paid amounts details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPaidAmounts(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_ALL_PAID_AMOUNTS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getAllPaidAmounts" + exception);
		}
		return list;

	}
	/**
	 * for getting district id from db that is required for generating e stamp code.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getDistIdEstamp(String appId) throws Exception {

      
        String distId="";
        
        try {
        	
        	dbUtility = new DBUtility();
        	dbUtility.setAutoCommit(false);
        	String param1[]={appId};
        	sql=RegCommonSQL.COUNT_PROP_ID;
        	dbUtility.createPreparedStatement(sql);
        String var = dbUtility.executeQry(param1);
        int propCount=Integer.parseInt(var);
        	
        	if(propCount==0){
        		
        		String param2[]={appId};
            	sql=RegCommonSQL.GET_REG_APPLICANT_DIST_ID;
            	dbUtility.createPreparedStatement(sql);
               	distId = dbUtility.executeQry(param2);
               	
               	
        	}else{

        		String param[]={appId,appId};
        	sql=RegCommonSQL.GET_REG_DIST_ID;
        	dbUtility.createPreparedStatement(sql);
           	distId = dbUtility.executeQry(param);
            }
        	

        } catch (Exception exception) {

                System.out.println("Exception in getDistIdEstamp-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return distId;

}
	/**
	 * for inserting estamp txn id mapped with reg txn id into database.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertEstampMappingDetls(String[] param) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_REG_ESTAMP_MAP_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo)
				dbUtility.commit();
			else
				dbUtility.rollback();
		}catch (Exception e) {
			logger.error("RegCommonDAO in insertEstampMappingDetls start" + e.getStackTrace());
		}
		return boo;
	}
	/**
	 * getBankDetails
	 * for getting bank details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getBankDetails(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_BANK_DETLS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getBankDetails" + exception);
		}
		return list;

	}
	/**
	 * getPartyIdsExchngPdf
	 * for getting party ids of exchange deed for pdf from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyIdsExchngPdf(String[] param) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			String query=RegCommonSQL.GET_PARTY_IDS_EXCHNG_PDF;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPartyIdsExchngPdf" + exception);
		}
		return list;

	}
	/**
	 * getPropIdsExchngPdf
	 * for getting party ids of exchange deed for pdf from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropIdsExchngPdf(String[] param) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			String query=RegCommonSQL.GET_PROP_IDS_EXCHNG_PDF;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropIdsExchngPdf" + exception);
		}
		return list;

	}
	/**
	 * getPartyTxnIdListTitleDeed
	 * for getting party txn id list corresponding to reg id given
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyTxnIdListTitleDeed(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_PARTY_TXN_ID_LIST_TITLE_DEED;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPartyTxnIdListTitleDeed" + exception);
		}
		return list;

	}
	/**
	 * getConsidAmtSysMV
	 * for getting consideration amount and system calculated MV from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getConsidAmtSysMV(String valId) throws Exception {
		ArrayList propDetls=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={valId};
			String query=RegCommonSQL.GET_CONSID_AMNT_SYS_MV;
			dbUtility.createPreparedStatement(query);
			propDetls = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getConsidAmtSysMV" + exception);
		}
		return propDetls;

	}
	/**
	 * getConsidAmtTitle
	 * for getting consideration amount from db for Title deed.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getConsidAmtTitle(String regId) throws Exception {
		String str="";
		try {
			dbUtility = new DBUtility();
			String[] param={regId};
			String query=RegCommonSQL.GET_CONSID_AMNT_TITLE_DEED;
			dbUtility.createPreparedStatement(query);
			str = dbUtility.executeQry(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getConsidAmtSysMV" + exception);
		}
		return str;

	}
	/**
	 * getBuildingFloorDetailsTitleDeed
	 * for getting building floor details for title deed from db
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getBuildingFloorDetailsTitleDeed(String propId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={propId};
			String query=RegCommonSQL.GET_BUILDING_FLOOR_DETAILS_TITLE_DEED;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getBuildingFloorDetailsTitleDeed" + exception);
		}
		return list;

	}
	/**
	 * getPaymentDetlsForDisplay
	 * for getting payment matrix details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPaymentDetlsForDisplay(String regId) throws Exception {
		ArrayList paymentDetls=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={regId};
			String query=RegCommonSQL.GET_PAYMENT_DETLS_DISPLAY;
			dbUtility.createPreparedStatement(query);
			paymentDetls = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPaymentDetlsForDisplay" + exception);
		}
		return paymentDetls;

	}
	/**
	 * for UPDATING transacting parties details in db.
	 * @param String[],String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean updateTransPartyDetls(String[] party,String[] ownerDetails) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			if(ownerDetails==null)
			{
			sql = RegCommonSQL.UPDATE_REG_TXN_PARTY_NEW;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(party);
			}
			else{
				sql = RegCommonSQL.UPDATE_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(ownerDetails);
				if(boo){
					sql = RegCommonSQL.UPDATE_REG_TXN_PARTY_NEW;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(party);
				}
				else
					dbUtility.rollback();
			}
			
		}catch (Exception e) {
			boo=false;
			try{
				dbUtility.rollback();
			}catch(Exception ex){
				logger.debug("Exception in RegCommonDAO:updateTransPartyDetls rollback.");
			}
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * getEstampCode
	 * for getting eStamp code from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getEstampCode(String regId) throws Exception {
		String str="";
		try {
			dbUtility = new DBUtility();
			String [] param={regId};
			String query=RegCommonSQL.GET_ESTAMP_CODE;
			dbUtility.createPreparedStatement(query);
			str = dbUtility.executeQry(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getEstampCode" + exception);
		}
		return str;

	}
	/**
	 * getPendingAppStatus
	 * for getting pending reg init application details from db
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public ArrayList getPendingAppStatus(String appId) throws Exception {
		//String appStatus="";
		ArrayList adjuStatus=new ArrayList();
		
		try {
			dbUtility = new DBUtility();
			String [] param={appId};
			sql=RegCommonSQL.GET_PENDING_APP_STATUS;
			dbUtility.createPreparedStatement(sql);
			adjuStatus =dbUtility.executeQuery(param);
			
			
		} catch (Exception exception) {
			System.out.println("Exception in getPendingAppStatus()" + exception);
		}
		return adjuStatus;

	}
	/**
	 * getPropertyIdApplicant
	 * for getting property id of applicant from db
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdApplicant(String appId) throws Exception {
		ArrayList propId=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String [] param={appId};
			sql=RegCommonSQL.GET_PROPERTY_ID_OF_APPLICANT;
			dbUtility.createPreparedStatement(sql);
			propId = dbUtility.executeQuery(param);
			
			
		} catch (Exception exception) {
			System.out.println("Exception in getPropertyIdApplicant()" + exception);
		}
		return propId;

	}
	/**
	 * getLatestPropertyId
	 * for getting latest property id from db
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getLatestPropertyId(String appId) throws Exception {
		ArrayList propId=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId,appId};
			sql=RegCommonSQL.GET_LATEST_PROPERTY_ID;
			dbUtility.createPreparedStatement(sql);
			
			propId = dbUtility.executeQuery(param);
			
			
		} catch (Exception exception) {
			System.out.println("Exception in getLatestPropertyId()" + exception);
		}
		return propId;

	}
	/**
	 * getAllPropDetlsExchangeDeedDash
	 * for getting ALL PROPERTY DETAILS from db for dashboard.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPropDetlsExchangeDeedDash(String valId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={valId};
			String query=RegCommonSQL.GET_ALL_PROPERTY_DETLS_EXCHANGE_DASH;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getAllPropDetlsExchangeDeedDash" + exception);
		}
		return list;

	}
	/**
	 * getRemValuationIdsExchangeDeed
	 * for getting remaining valuation ids from db for dashboard.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getRemValuationIdsExchangeDeed(String valId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={valId};
			String query=RegCommonSQL.GET_REMAINING_VALUATION_IDS_EXCHANGE_DASH;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getRemValuationIdsExchangeDeed" + exception);
		}
		return list;

	}
	/**
	 * getDutyTxnId
	 * for getting duty txn id from db
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getDutyTxnId(String appId) throws Exception {
		String appStatus="";
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			sql=RegCommonSQL.GET_DUTY_TXN_ID;
			dbUtility.createPreparedStatement(sql);
			appStatus = dbUtility.executeQry(param);
			
			
		} catch (Exception exception) {
			System.out.println("Exception in getDutyTxnId()" + exception);
		}
		return appStatus;

	}
	/**
	 * getApplicantAddress
	 * for getting applicant district and address from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantAddress(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_APPLICANT_ADDRESS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getApplicantAddress" + exception);
		}
		return list;

	}
	/**
	 * insertTxnDetailsFinalAction
	 * for inserting reg txn status from final action
	 * @param String
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertTxnDetailsFinalAction(String[] param) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				dbUtility.commit();
			}else{
				dbUtility.rollback();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * for updating bank details in db in case of deposit deed.
	 * @param String[]
	 * @return boolean
	 * @author Shruti
	 */
	public boolean updateBankDetails(String[] bankDetls) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
					sql=RegCommonSQL.UPDATE_BANK_DTLS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(bankDetls);
					if(boo)
					{
						dbUtility.commit();
					}
					else
					{
						dbUtility.rollback();
					}
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * getTransPartyDetsNonPropDeed
	 * for getting party txn ids from db for prop trns map non pv deeds
	 * @param String[]
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyTxnIdsForPropMap(String[] param,int deedId,int instId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			//String param[]=new String[1];
			//param[0]=appId;
			dbUtility = new DBUtility();
			String query="";
			if(deedId==RegInitConstant.DEED_TRUST && instId==RegInitConstant.INST_TRUST_NPV_P ||
					(deedId==RegInitConstant.DEED_PARTNERSHIP_NPV && 
 							 (instId==RegInitConstant.INST_PARTNERSHIP_NPV_1 || instId==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))){
				query=RegCommonSQL.GET_PARTY_TXN_IDS_FOR_PROP_MAP_TRUST_NPV_NP;
			}else{
			    query=RegCommonSQL.GET_PARTY_TXN_IDS_FOR_PROP_MAP;
			}
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPartyTxnIdsForPropMap" + exception);
		}
		return list;

	}
	/**
	 * for getting extra field label from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExtraFieldLabel(String instId, String languageLocale) throws Exception {

       // int regTxnIdSeq = 0;
        String extraFieldLabel="";
        
        try {
        	dbUtility = new DBUtility();
        	String[] param={instId};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_EXTRA_FIELD_LABEL;
        	}else{
        		sql=RegCommonSQL.GET_EXTRA_FIELD_LABEL_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	extraFieldLabel = dbUtility.executeQry(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getExtraFieldLabel-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return extraFieldLabel;

}
	/**
	 * for getting claimant flag  from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getClaimantFlag(String roleId) throws Exception {

       // int regTxnIdSeq = 0;
        ArrayList claimantFlag=new ArrayList();
        
        try {
        	dbUtility = new DBUtility();
        	String[] param={roleId};
        	sql=RegCommonSQL.GET_CLAIMANT_FLAG;
        	dbUtility.createPreparedStatement(sql);
        	claimantFlag = dbUtility.executeQuery(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getClaimantFlag-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return claimantFlag;

}
	/**
	 * for getting user type id from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getUserTypeId(String userId) throws Exception {

        String typeId="";
        String[] param={userId};
        try {
        	dbUtility = new DBUtility();
           	sql=RegCommonSQL.GET_USER_TYPE_ID;
        	dbUtility.createPreparedStatement(sql);
        	typeId = dbUtility.executeQry(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getUserTypeId-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return typeId;

}
	/**
	 * getDistIdNameRU
	 * for getting dist id name of registered user
	 * @param String[]
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDistIdNameRU(String userId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			String[] param={userId};
			dbUtility = new DBUtility();
			String query="";
		    query=RegCommonSQL.GET_DIST_ID_NAME_RU;
			
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDistIdNameRU" + exception);
		}
		return list;

	}
	/**
	 * getDistIdNameOfficeNameDRS
	 * for getting dist id name, office name of DRS
	 * @param String[]
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDistIdNameOfficeNameDRS(String officeId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			String[] param={officeId};
			dbUtility = new DBUtility();
			String query="";
		    query=RegCommonSQL.GET_DIST_ID_NAME_OFFC_NAME_DRS;
			
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDistIdNameOfficeNameDRS" + exception);
		}
		return list;

	}
	/**
	 * getDistIdNameSP
	 * for getting dist id name of SERVICE PROVIDER
	 * @param String[]
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDistIdNameSP(String userId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			String[] param={userId};
			dbUtility = new DBUtility();
			String query="";
		    query=RegCommonSQL.GET_DIST_ID_NAME_SP;
			
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDistIdNameSP" + exception);
		}
		return list;

	}
	/**
	 * insertAdjuDuties
	 * for getting dist id name of SERVICE PROVIDER
	 * @param String[]
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public boolean insertAdjuDuties(String[] param) {
		boolean boo=false;
		//String bool="";
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_ADJU_DUTY;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
					dbUtility.commit();
					String[] param1={param[0]};
					sql = RegCommonSQL.UPDATE_REG_ADJU_STATUS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(param1);
							if(boo){
										dbUtility.commit();
									}else{
										dbUtility.rollback();
									}
			}	
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	/**
	 * insertAdjuDuties
	 * for getting dist id name of SERVICE PROVIDER
	 * @param String[]
	 * @return ArrayList
	 * @author ROOPAM
	 */
/*	public boolean insertAdjuDutiesSys(String[] param) {
		boolean boo=false;
		//String bool="";
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_ADJU_DUTY;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
					dbUtility.commit();
					String[] param1={param[0]};
					sql = RegCommonSQL.UPDATE_REG_ADJU_STATUS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(param1);
							if(boo){
										dbUtility.commit();
									}else{
										dbUtility.rollback();
									}
			}	
			else
				dbUtility.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}*/
	/**
	 * getDutyDetlsAdjuForConfirmation
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getDutyDetlsAdjuForConfirmation(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_REG_ADJU_DUTY_DETS_FOR_CONFIRMATION;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetlsAdjuForConfirmation" + exception);
		}
		return list;

	}
	/**
	 * getCommonDeedFlag
	 * for getting common deed flag from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getCommonDeedFlag(String deedId) throws Exception {

        String flag="";
        String[] param={deedId};
        try {
        	dbUtility = new DBUtility();
           	sql=RegCommonSQL.GET_COMMON_DEED_FLAG;
        	dbUtility.createPreparedStatement(sql);
        	flag = dbUtility.executeQry(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getCommonDeedFlag-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return flag;

}
	/**
	 * insrtTransPartyDetlsCommonDeeds
	 * for inserting applicant details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insrtTransPartyDetlsCommonDeeds(String[] party, String[] ownerDetails, String[] statusParam) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			if(party[32].equals("") || party[32].equalsIgnoreCase(""))
			{
			sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;               //relationship
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(party);
			}
			else{
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(ownerDetails);
				if(boo){
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;            
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(party);
				}
				else
					dbUtility.rollback();
			}
			if(boo){
							sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
							dbUtility.createPreparedStatement(sql);
							boo=dbUtility.executeUpdate(statusParam);
							if(boo){
								dbUtility.commit();
							}else{
								dbUtility.rollback();
							}
			}
			else{
				dbUtility.rollback();
			}
		}catch (Exception e) {
			boo=false;
			try{
				dbUtility.rollback();
			}catch(Exception ex){
				logger.debug("Exception in RegCommonDAO:insrtTransPartyDetlsCommonDeeds rollback.");
			}
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * getTransPartyDetsCommonDeed
	 * for getting transacting party details corresponding to a registration id common deed from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDetsCommonDeed(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			String param[]=new String[1];
			param[0]=appId;
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_TRANS_PARTY_DETS_COMMON_DEED;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyDetsCommonDeed" + exception);
		}
		return list;

	}
	/**
	 * insertParticularsDetails
	 * for inserting particular details db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public boolean insertParticularsDetails(HashMap map, String regId ,String userId,String[] status) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			
			dbUtility.setAutoCommit(false);
			Collection mapCollection=map.values();
            Object[] l1=mapCollection.toArray();
            RegCommonDTO mapDto1 =new RegCommonDTO();
            String[] param=new String[4];
            
            for(int i=0;i<l1.length;i++)
            {
            	mapDto1=(RegCommonDTO)l1[i];
            	
            	param[0]=regId;
            	param[1]=mapDto1.getParticularName();
            	param[2]=mapDto1.getParticularDesc();
            	param[3]=userId;
               
            	sql = RegCommonSQL.INSERT_PARTICULAR_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo=dbUtility.executeUpdate(param);
				
				if(!boo){
					dbUtility.rollback();
					break;
				}
            }
			
			if(boo)
				{
									
						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						boo=dbUtility.executeUpdate(status);
						if(boo){
						dbUtility.commit();
						}else{
							dbUtility.rollback();
						}
						
					
				}
				else
					dbUtility.rollback();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO:insertParticularsDetails in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * getParticularList
	 * for getting particular list from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getParticularList(String appId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param={appId};
			String query=RegCommonSQL.GET_PARTICULAR_LIST;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getParticularList" + exception);
		}
		return list;

	}
	
	/**
	 * getParticularDetails
	 * for getting particular details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getParticularDetails(String partId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			String param[]=new String[1];
			param[0]=partId;
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_PARTICULAR_DETAILS;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getParticularDetails" + exception);
		}
		return list;

	}
	/**
	 * updateParticularDetails
	 * for updating particular details in db
	 * @param String[]
	 * @return boolean
	 * @author Shruti
	 */
	public boolean updateParticularDetails(String[] particularDetls) {
		boolean boo=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
					sql=RegCommonSQL.UPADTE_PARTICULAR_DETAILS;
					dbUtility.createPreparedStatement(sql);
					boo=dbUtility.executeUpdate(particularDetls);
					if(boo)
					{
						dbUtility.commit();
					}
					else
					{
						dbUtility.rollback();
					}
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	/**
	 * getPartyDetailsCommonDeedsPdf
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyDetailsCommonDeedsPdf(String partyId) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			String param[]={partyId};
			dbUtility = new DBUtility();
			String query=RegCommonSQL.GET_PARTY_DETAILS_PDF_COMMON_DEED;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPartyDetailsCommonDeedsPdf" + exception);
		}
		return list;

	}
	/**
	 * getRemarks
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public String getRemarks(String appId) throws Exception {
		String remarks=null;
		try {
			dbUtility = new DBUtility();
			String[] param={appId}; 
			String query=RegCommonSQL.GET_ADJU_REMARKS;
			dbUtility.createPreparedStatement(query);
			//list = dbUtility.executeQuery(query);
			remarks=dbUtility.executeQry(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getRemarks" + exception);
		}
		return remarks;

	}
	
	/**
	 * updateAdjudicationFlag
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public boolean updateAdjudicationFlag(String appId,String userId) throws Exception {
		boolean boo=false;
		String[] param={userId,appId};
		try {
			dbUtility = new DBUtility();	
			dbUtility.setAutoCommit(false);
			sql=RegCommonSQL.UPDATE_ADJU_REG_STATUS;
			dbUtility.createPreparedStatement(sql);
			boo=dbUtility.executeUpdate(param);
			if(boo){
				dbUtility.commit();
				
			}
			else
				dbUtility.rollback();
			
		} catch (Exception exception) {
			logger.debug("Exception in updateAdjudicationFlag" + exception);
		}
		return boo;

	}
	/**
	 * for getting all relationships
	 * @return ArrayList
	 */
	public ArrayList getRelationshipList(String gender, String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
		//sql = RegCommonSQL.GET_RELATIONSHIP_LIST;
			sql = RegCommonSQL.GET_RELATIONSHIP_LIST_HINDI;
        dbUtility.createPreparedStatement(sql);
        String param[]={gender};
        ArrayList list = dbUtility.executeQuery(param);
       // mainList = new ArrayList();
        ArrayList subList = null;
        CommonDTO dto;
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new CommonDTO();
            dto.setId(subList.get(0).toString());
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());
            }
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("RegCommonDAO:getRelationshipList in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	/**
	 * for getting relationship name from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getRelationshipName(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_RELATIONSHIP_NAME;
        	}else{
        		sql=RegCommonSQL.GET_RELATIONSHIP_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getRelationshipName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	/**
	 * for getting ExecutantClaimant drop down for universal deeds
	 * @author ROOPAM
	 * @date 22 October 2013
	 * @return ArrayList
	 */
	public ArrayList getExecutantClaimant(String languageLocale, int instId) {
		
		ArrayList mainList = new ArrayList();
		 try {
			 dbUtility = new DBUtility();
		//sql = RegCommonSQL.GET_EXECUTANT_CLAIMANT_LIST;
			 if(instId==2225){
				 sql = RegCommonSQL.GET_EXECUTANT_CLAIMANT_LIST_WILL_HINDI;
			 }else{
			 sql = RegCommonSQL.GET_EXECUTANT_CLAIMANT_LIST_HINDI;
			 }
        dbUtility.createStatement();
        ArrayList list = dbUtility.executeQuery(sql);
        //ArrayList mainList = new ArrayList();
        ArrayList subList = null;
        CommonDTO dto;
        
        //2225-will instrument
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new CommonDTO();
            dto.setId(subList.get(0).toString());
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());
            }
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO : getExecutantClaimant in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	/**
	 * for getting ExecutantClaimant name from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExecutantClaimantName(String id) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	sql=RegCommonSQL.GET_EXECUTANT_CLAIMANT_NAME;
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getExecutantClaimantName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	/**
	 * for getting unit name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getUnitName(String unitId,String languageLocale) throws Exception {
        String proofname="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={unitId};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_UNIT_NAME;
        	}else{
        		sql=RegCommonSQL.GET_UNIT_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	proofname = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getUnitName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return proofname;
       }
	public String getTehsilName(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_TEHSIL_NAME;
        	}else{
        		sql=RegCommonSQL.GET_TEHSIL_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getTehsilName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	public String getAreaTypeName(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_AREA_TYPE_NAME;
        	}else{
        		sql=RegCommonSQL.GET_AREA_TYPE_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getTehsilName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	public String getWardName(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_WARD_NAME;
        	}else{
        		sql=RegCommonSQL.GET_WARD_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getTehsilName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	public String getMohallaName(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_WARD_NAME;
        	}else{
        		sql=RegCommonSQL.GET_WARD_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getTehsilName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	public String getPropertyL1Name(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_L1_NAME;
        	}else{
        		sql=RegCommonSQL.GET_L1_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getPropertyL1Name-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	public String getPropertyL2Name(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_L2_NAME;
        	}else{
        		sql=RegCommonSQL.GET_L2_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getPropertyL2Name-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	public String getFloorName(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_FLOOR_NAME;
        	}else{
        		sql=RegCommonSQL.GET_FLOOR_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getFloorName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	public String getAppleteName(String id,String languageLocale) throws Exception {
        String name="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.GET_APPLETE_NAME;
        	}else{
        		sql=RegCommonSQL.GET_APPLETE_NAME_HINDI;
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getAppleteName-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return name;
       }
	/*public String getAdjuRemarks(String id) throws Exception {
        String remarks="";
        try {
        	dbUtility = new DBUtility();
        	String[] param={id};
        	
        	sql=RegCommonSQL.GET_ADJU_REMARKS;
        	
        	dbUtility.createPreparedStatement(sql);
        	remarks = dbUtility.executeQry(param);
       } catch (Exception exception) {
         System.out.println("Exception in getAdjuRemarks-RegCommonDAO" + exception);
         }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
       return remarks;
       }*/
	/**
	 * for getting office name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getOfficeName(String ofcId,String languageLocale) throws Exception {

       // int regTxnIdSeq = 0;
        String name="";
        
        try {
        	dbUtility = new DBUtility();
        	/*dbUtility.createStatement();
        	sql=RegCommonSQL.GET_DISTRICT_NAME+"'"+distId+"'";
        	distname = dbUtility.executeQry(sql);*/
        	
        	String[] param={ofcId};
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	sql=RegCommonSQL.OFFICE_NAME;
        	}else{
        		sql=RegCommonSQL.OFFICE_NAME_HINDI;	
        	}
        	dbUtility.createPreparedStatement(sql);
        	name = dbUtility.executeQry(param);

        } catch (Exception exception) {

                System.out.println("Exception in getOfficeName-RegCommonDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

        return name;

}
}