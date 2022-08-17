package com.wipro.igrs.newreginitefiling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wipro.igrs.commonEfiling.IGRSCommon;
import com.wipro.igrs.commonEfiling.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.deedDraft.bd.DeedDraftBD;
import com.wipro.igrs.newreginitefiling.action.CommonAction;
import com.wipro.igrs.newreginitefiling.action.CommonActionUtilities;
import com.wipro.igrs.newreginitefiling.constant.RegInitConstant;
import com.wipro.igrs.newreginitefiling.dto.CommonDTO;
import com.wipro.igrs.newreginitefiling.dto.RegCommonDTO;
import com.wipro.igrs.newreginitefiling.dto.RegCompletDTO;
import com.wipro.igrs.newreginitefiling.form.RegCommonForm;
import com.wipro.igrs.newreginitefiling.form.RegCompletionForm;
import com.wipro.igrs.newreginitefiling.sql.RegCommonSQL;
import com.wipro.igrs.propertyvaluationefiling.dto.DutyCalculationDTO;
import com.wipro.igrs.slotbooking.dto.SlotBookDTO;
import com.wipro.igrs.slotbooking.sql.CommonSQL;

public class RegCommonDAO {

	DBUtility dbUtility = null;
	Connection connTest;
	String sql = null;
	// CommonDTO dto = null;
	// ArrayList mainList = null;
	IGRSCommon igrsCommon = null;
	PreparedStatement pst = null;
	private Logger logger = (Logger) Logger.getLogger(DBUtility.class);

	public RegCommonDAO() {
		try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
	}

	/**
	 * for getting all Applicant Types
	 * 
	 * @return ArrayList
	 */
	public ArrayList getAppType(String languageLocale,int poaHolderFlag,int role) {

		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			// sql = RegCommonSQL.SELECT_APPL_TYPE;
			if(role==RegInitConstant.ROLE_CHILD_ADOPTED){
				sql = RegCommonSQL.SELECT_APPL_TYPE_CHILD_HINDI;
			}else
			if(poaHolderFlag==1){
				sql = RegCommonSQL.SELECT_APPL_TYPE_GOVT_OFFCL_HINDI;
			}else{
			sql = RegCommonSQL.SELECT_APPL_TYPE_HINDI;
			}
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			// ArrayList mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;

			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				if (languageLocale
						.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					dto.setName(subList.get(1).toString());
				} else {
					dto.setName(subList.get(2).toString());
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				logger.debug(ex.getMessage(),ex);
				// ex.printStackTrace();
			}
		}
		return mainList;
	}

	/**
	 * for getting all Country details
	 * 
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
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				if (languageLocale
						.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					dto.setName(subList.get(1).toString());
				} else {
					dto.setName(subList.get(2).toString());
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
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
	 * 
	 * @param param
	 * @return ArrayList
	 */
	public ArrayList getState(String param, String languageLocale) {

		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			ArrayList list = igrsCommon.getState(param);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				if (languageLocale
						.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					dto.setName(subList.get(1).toString());
				} else {
					dto.setName(subList.get(2).toString());
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
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
	 * 
	 * @param stateId
	 * @return ArrayList
	 */
	public ArrayList getDistrict(String stateId, String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			ArrayList list = igrsCommon.getDistrict(stateId);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;

			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				if (languageLocale
						.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					dto.setName(subList.get(1).toString());
				} else {
					dto.setName(subList.get(2).toString());
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return mainList;
	}

	
	public ArrayList getTehsildrop(String stateId, String languageLocale) {
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		DBUtility dbUtil=null;
		try {

		dbUtil = new DBUtility();
		dbUtil.createStatement();

		String temp[] = new String[1];
		temp[0] = stateId;// district ID selected By User
		String sql = RegCommonSQL.TEHSIL;
		dbUtil.createPreparedStatement(sql);
		list1 = dbUtil.executeQuery(temp);

		for (int i = 0; i < list1.size(); i++) {
			list2 = (ArrayList) list1.get(i);
			//SlotBookDTO sdto = new SlotBookDTO();
			CommonDTO dto;
			dto = new CommonDTO();
			dto.setTehsildropid(list2.get(0).toString());
			if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				dto.setTehsildropname(list2.get(1).toString());
			}else{
				dto.setTehsildropname(list2.get(2).toString());
			}
			
			
			
			tehsilList.add(dto);
		}

		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.debug("closing connection error on slot bookdao");

		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		}

		return tehsilList;
	}
	
	public ArrayList getTehsildrop1(String stateId) {
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		DBUtility dbUtil=null;
		try {

		dbUtil = new DBUtility();
		dbUtil.createStatement();

		String temp[] = new String[1];
		temp[0] = stateId;// district ID selected By User
		String sql = RegCommonSQL.TEHSIL;
		dbUtil.createPreparedStatement(sql);
		list1 = dbUtil.executeQuery(temp);

		for (int i = 0; i < list1.size(); i++) {
			list2 = (ArrayList) list1.get(i);
			//SlotBookDTO sdto = new SlotBookDTO();
			CommonDTO dto;
			dto = new CommonDTO();
			dto.setTehsildropid1(list2.get(0).toString());
			
			dto.setTehsildropname1(list2.get(1).toString());
			
			tehsilList.add(dto);
		}

		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.debug("closing connection error on slot bookdao");

		}
		}

		return tehsilList;
	}
	
	/**
	 * for getting all ID Proof details
	 * 
	 * @return ArrayList
	 */
	public ArrayList getIdProf(String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			// sql = RegCommonSQL.SELECT_PHOTO_PROOF;
			sql = RegCommonSQL.SELECT_PHOTO_PROOF_HINDI;
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				if (languageLocale
						.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					dto.setName(subList.get(1).toString());
				} else {
					dto.setName(subList.get(2).toString());
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
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
	 * 
	 * @return ArrayList
	 */
	public ArrayList getDeedType(String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_DEED_TYPE;
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return mainList;
	}

	// following method created by roopam
	/**
	 * for getting Deed Details based on deed type i.e. optional or mandatory
	 * 
	 * @return ArrayList
	 */
	public ArrayList getDeedTypeNew(String flag) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			if (flag.equalsIgnoreCase("Registration Process "))
				sql = RegCommonSQL.SELECT_DEED_TYPE;
			else
				sql = RegCommonSQL.SELECT_DEED_TYPE_OPTIONAL;
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return mainList;
	}

	// end of creation by roopam

	/**
	 * for getting Instruments list based on deed
	 * 
	 * @param deed
	 * @return ArrayList
	 */
	public ArrayList getInstrument(String deed) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { deed };
			sql = RegCommonSQL.SELECT_DEED_INSTRUMENT;
			dbUtility.createPreparedStatement(sql);
			ArrayList list = dbUtility.executeQuery(param);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
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
	 * 
	 * @param deed
	 * @param deed1
	 * @return ArrayList
	 */
	public ArrayList getExemption(String deed, String deed1) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { deed };
			if (deed1.equalsIgnoreCase("deed")) {
				sql = RegCommonSQL.SELECT_DEED_EXEMPTION_ONDEED;
			} else {
				sql = RegCommonSQL.SELECT_DEED_EXEMPTION;
			}
			dbUtility.createPreparedStatement(sql);
			ArrayList list = dbUtility.executeQuery(param);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
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
	 * 
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @param marketValue
	 * @return Stamp Duty
	 */
	/*
	 * public String getStampDuty(String deed,String instrument,String
	 * exemption, String marketValue){ String exemptions[] =
	 * exemption.split(","); String exemptionIDs[] = new
	 * String[exemptions.length/2]; int j=0; for(int
	 * i=0;i<exemptions.length;i=i+2){ exemptionIDs[j] = exemptions[i]; j++; }
	 * String tempExm=""; for(int i=0;i<exemptionIDs.length;i++){
	 * if(tempExm.equalsIgnoreCase("")) tempExm=exemptionIDs[i]; else
	 * tempExm=tempExm+","+exemptionIDs[i]; } int
	 * value=Integer.parseInt(marketValue); double market = value; String
	 * duty="0"; try { duty=igrsCommon.getStampDuty(deed, instrument, tempExm,
	 * market); } catch (Exception e) { logger.error("RegCommonDAO in dao start"
	 * + e.getStackTrace()); } return duty; }
	 */
	/*
	 * public String getStampDuty(String deed,String instrument,String
	 * exemption, String marketValue){
	 * 
	 * String tempExm = null; String duty = "0"; double market = 0.0; if
	 * (exemption != null) if (!exemption.equalsIgnoreCase("null")) { String
	 * exemptions[] = exemption.split(","); String exemptionIDs[] = new
	 * String[exemptions.length/2]; int j=0; if(!("".equals(exemption))){
	 * for(int i=0;i<exemptions.length;i=i+2){ exemptionIDs[j] = exemptions[i];
	 * j++; } for(int i=0;i<exemptionIDs.length;i++){ if("".equals(tempExm))
	 * tempExm=exemptionIDs[i]; else tempExm=tempExm+","+exemptionIDs[i]; } } }
	 * if (marketValue != null) { market = Double.parseDouble(marketValue); }
	 * try { logger.debug("deed value in DAO is === " + deed +
	 * "instrument value is == " + instrument + "exemption value is == "+
	 * tempExm + "base value is === "+ market);
	 * duty=igrsCommon.getStampDuty(deed, instrument, tempExm, market); } catch
	 * (Exception e) { logger.debug("RegCommonDAO in dao start" +
	 * e.getStackTrace()); } return duty; }
	 */
	// above code commented by roopam
	/**
	 * for calculating other fee for stamp duty
	 * 
	 * @param function_id
	 * @param user_id
	 * @param service_id
	 * @return otherFee
	 */

	/*
	 * public ArrayList getOthersDuty(String function_id, String service_id,
	 * String user_id){ ArrayList otherFee = null; try { String args[] = new
	 * String[3]; args[0] = function_id; args[1] = service_id; args[2] =
	 * user_id; otherFee =
	 * igrsCommon.getOthersFeeDuty(function_id,service_id,user_id); }catch
	 * (Exception e) { logger.error("RegCommonDAO in dao start" +
	 * e.getStackTrace()); } return otherFee; }
	 */

	/**
	 * this method is useful for Initiate the Registration
	 * 
	 * @param stampDuty
	 * @param reg_txn
	 * @param deed_details
	 * @return boolean
	 */
	/*
	 * public boolean InitRegistration(String stampDuty[],String
	 * reg_txn[],String deed_details[]){ boolean boo=false; try { dbUtility =
	 * new DBUtility(); sql = RegCommonSQL.INSERT_STAMP_DUTY;
	 * dbUtility.createPreparedStatement(sql);
	 * boo=dbUtility.executeUpdate(stampDuty); if(boo){ sql =
	 * RegCommonSQL.INSERT_REG_TXN; dbUtility.createPreparedStatement(sql);
	 * boo=dbUtility.executeUpdate(reg_txn); } if(boo){ String temp[] =
	 * deed_details[3].split(","); sql = RegCommonSQL.INSERT_REG_DEED;
	 * dbUtility.createPreparedStatement(sql); for(int i=0;i<temp.length;i=i+2){
	 * deed_details[3] = temp[i]; boo=dbUtility.executeUpdate(deed_details); } }
	 * if(boo) dbUtility.commit(); else dbUtility.rollback(); } catch (Exception
	 * e) { logger.error("RegCommonDAO in dao start" + e.getStackTrace()); }
	 * 
	 * return boo; }
	 */

	/*
	 * public boolean InitRegPartyDetails(String[] party) { boolean boo=false;
	 * try { dbUtility = new DBUtility(); sql =
	 * RegCommonSQL.INSERT_REG_TXN_PARTY;
	 * dbUtility.createPreparedStatement(sql);
	 * boo=dbUtility.executeUpdate(party); if(boo) dbUtility.commit(); else
	 * dbUtility.rollback(); }catch (Exception e) {
	 * logger.error("RegCommonDAO in InitRegPartyDetails start" +
	 * e.getStackTrace()); } return boo; }
	 */
	/**
	 * @auther : ROOPAM MEHTA
	 * @Created : 16 Nov.2012
	 * @Description : checks weather the given deed requires property valuation
	 *              or duty calculation
	 * @Paratmeter : String
	 */
	/*
	 * public boolean propertyValReqCheck(String deed) { boolean boo=false; try
	 * { dbUtility = new DBUtility(); ArrayList result=new ArrayList(); String
	 * param[]=new String[1]; param[0]=deed; sql =
	 * RegCommonSQL.PROPERT_VAL_REQ_CHECK;
	 * dbUtility.createPreparedStatement(sql);
	 * result=dbUtility.executeQuery(param);
	 * System.out.println("result -> "+result.toString()); if(result!=null) {
	 * //System.out.println("result -> "+result.get(0)); String
	 * res=result.get(0).toString(); System.out.println("res -> "+res);
	 * //res.substring(1, res.length()); //res.
	 * //System.out.println("res -> "+res);
	 * 
	 * if(res.equals(new String("[Y]"))||res.equals(new String("[y]"))){
	 * boo=true; } }
	 * 
	 * 
	 * }catch (Exception e) {
	 * //logger.error("RegCommonDAO in InitRegPartyDetails start" +
	 * e.getStackTrace()); e.printStackTrace(); } return boo; }
	 */
	/**
	 * for getting Instruments list based on deed
	 * 
	 * @param deed
	 * @return ArrayList
	 */
	public ArrayList getPartyType(int deed, int inst, String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			// String[] param={Integer.toString(deed)};
			ArrayList list = new ArrayList();

			if (inst == RegInitConstant.INST_DISSOLUTION_NPV
					|| inst == RegInitConstant.INST_DISSOLUTION_2 
					|| inst == RegInitConstant.INST_BUILDER_1
					|| inst == RegInitConstant.INST_BUILDER_2
					|| inst == RegInitConstant.INST_AUTHENTICATE_POA
					|| inst == RegInitConstant.INST_DECREE
					|| inst == RegInitConstant.INST_Attachment_orders
					|| inst == RegInitConstant.INST_Written_Demands
					|| inst == RegInitConstant.INST_Order_of_Release_of_Attachment
					|| inst == RegInitConstant.INST_Memo) {
				String[] param = { Integer.toString(inst) };
				// sql = RegCommonSQL.SELECT_PARTY_TYPE_INST_WISE;
				sql = RegCommonSQL.SELECT_PARTY_TYPE_INST_WISE_HINDI;
				dbUtility.createPreparedStatement(sql);
				list = dbUtility.executeQuery(param);
			} else {
				String[] param = { Integer.toString(deed) };
				// sql = RegCommonSQL.SELECT_PARTY_TYPE;
				sql = RegCommonSQL.SELECT_PARTY_TYPE_HINDI;
				dbUtility.createPreparedStatement(sql);
				list = dbUtility.executeQuery(param);
			}
			// dbUtility.createPreparedStatement(sql);
			// ArrayList list = dbUtility.executeQuery(param);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;

			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				int partyId = Integer.parseInt(dto.getId());

				if (deed == RegInitConstant.DEED_COMPOSITION_NPV
						|| deed == RegInitConstant.DEED_LETTER_OF_LICENCE_NPV
						|| inst == RegInitConstant.INST_TRANSFER_NPV_1
						|| inst == RegInitConstant.INST_TRANSFER_NPV_2)// for
																		// composition
																		// deed,
																		// letter
																		// of
																		// license
																		// deed
																		// & 2
																		// instruments
																		// of
																		// transfer
																		// deed
				{

					if (languageLocale
							.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						if (partyId == RegInitConstant.ROLE_EXECUTANT_SELF
								|| partyId == RegInitConstant.ROLE_EXECUTANT_POA_HOLDER) {
							dto.setName(subList.get(1).toString()
									+ RegInitConstant.CREDITOR_ENGLISH);
						} else {
							dto.setName(subList.get(1).toString()
									+ RegInitConstant.DEBTOR_ENGLISH);
						}
					} else {

						if (partyId == RegInitConstant.ROLE_EXECUTANT_SELF
								|| partyId == RegInitConstant.ROLE_EXECUTANT_POA_HOLDER) {
							dto.setName(subList.get(2).toString()
									+ RegInitConstant.CREDITOR_HINDI);
						} else {
							dto.setName(subList.get(2).toString()
									+ RegInitConstant.DEBTOR_HINDI);
						}

					}

				} else if (inst == RegInitConstant.INST_TRANSFER_NPV_4)// for 1
																		// instrument
																		// of
																		// transfer
																		// deed
				{
					if (languageLocale
							.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						if (partyId == RegInitConstant.ROLE_EXECUTANT_SELF
								|| partyId == RegInitConstant.ROLE_EXECUTANT_POA_HOLDER) {
							dto.setName(subList.get(1).toString());
						} else {
							dto.setName(subList.get(1).toString()
									+ RegInitConstant.BENEFICIARY_ENGLISH);
						}
					} else {

						if (partyId == RegInitConstant.ROLE_EXECUTANT_SELF
								|| partyId == RegInitConstant.ROLE_EXECUTANT_POA_HOLDER) {
							dto.setName(subList.get(2).toString());
						} else {
							dto.setName(subList.get(2).toString()
									+ RegInitConstant.BENEFICIARY_HINDI);
						}

					}

				} else {

					if (languageLocale
							.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						dto.setName(subList.get(1).toString());
					} else {
						dto.setName(subList.get(2).toString());
					}
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
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
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	/*public boolean insrtApplcntTransPartyPropDetls(String[] party,
			String[] deed, String[] propDetls, String[] propDetls2,
			String[] ownerDetails, String exemp, String[] statusParam,
			String[] extraDetls) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			if (party[32].equals("") || party[32].equalsIgnoreCase("")) {
				sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW; // relationship
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(party);
			} else {
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(ownerDetails);
				if (boo) {
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(party);
				} else
					dbUtility.rollback();
			}
			if (boo) {

				if (deed.length == 8) {
					sql = RegCommonSQL.INSERT_REG_DEED_DETLS;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(deed);
					if (boo) {

						// BELOW CODE FOR INSERTION OF PROPERTY DETAILS

						sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
						dbUtility.createPreparedStatement(sql);
						boo = dbUtility.executeUpdate(propDetls);

						// BELOW CODE FOR INSERTION OF EXEMPTIONS
						if (boo) {

							String[] exempParam = new String[3];
							if (exemp != null && !exemp.equalsIgnoreCase("")) {

								String[] exempArr = exemp.split(",");
								if (exempArr != null && exempArr.length > 0) {

									for (int i = 0; i < exempArr.length; i++) {

										exempParam[0] = party[0];
										exempParam[1] = exempArr[i].trim();
										exempParam[2] = party[30];

										sql = RegCommonSQL.INSERT_REG_EXEMPTION_DETLS;
										dbUtility.createPreparedStatement(sql);
										boo = dbUtility
												.executeUpdate(exempParam);

										if (!boo) {
											break;
										}

									}
								}
							}
							// for inserting extra details if present
							if (extraDetls != null) { // for title deed and
														// trust deed only.
								if (boo) {
									sql = RegCommonSQL.INSERT_REG_DEED_EXTRA_DTLS;
									dbUtility.createPreparedStatement(sql);
									boo = dbUtility.executeUpdate(extraDetls);
									if (!boo) {
										dbUtility.rollback();
									}

								} else {
									dbUtility.rollback();
								}
							}
						} else {
							dbUtility.rollback();
						}

					} else {
						dbUtility.rollback();
					}
				}
				if (boo) {

					// BELOW CODE FOR INSERTION OF PROPERTY AND TRANSACTING
					// PARTY MAP DETAILS

					sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(propDetls2);
					if (boo) {

						if (statusParam != null) {
							sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
							dbUtility.createPreparedStatement(sql);
							boo = dbUtility.executeUpdate(statusParam);
							if (boo) {
								dbUtility.commit();
							} else {
								dbUtility.rollback();
							}
						} else {
							dbUtility.commit();
						}
					} else {
						dbUtility.rollback();
					}

				} else {
					dbUtility.rollback();
				}
			} else {
				dbUtility.rollback();
			}
		} catch (Exception e) {
			boo = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception in RegCommonDAO:insrtApplcntTransPartyPropDetls rollback.");
			}
			logger.debug(e.getMessage(),e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}*/

	/*public boolean insrtPVPropertyDetls(String[] deed, String[] propDetls,
			String exemp, String[] statusParam) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			if (deed.length == 8) {
				sql = RegCommonSQL.INSERT_REG_DEED_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(deed);
				if (boo) {

					// BELOW CODE FOR INSERTION OF PROPERTY DETAILS

					sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(propDetls);

					// BELOW CODE FOR INSERTION OF EXEMPTIONS
					if (boo) {

						String[] exempParam = new String[3];
						if (exemp != null && !exemp.equalsIgnoreCase("")) {

							String[] exempArr = exemp.split(",");
							if (exempArr != null && exempArr.length > 0) {

								for (int i = 0; i < exempArr.length; i++) {

									exempParam[0] = propDetls[1];
									exempParam[1] = exempArr[i].trim();
									exempParam[2] = propDetls[14];

									sql = RegCommonSQL.INSERT_REG_EXEMPTION_DETLS;
									dbUtility.createPreparedStatement(sql);
									boo = dbUtility.executeUpdate(exempParam);

									if (!boo) {
										break;
									}

								}
							}
						}

					} else {
						dbUtility.rollback();
					}

				} else {
					dbUtility.rollback();
				}
			}
			if (boo) {

				if (statusParam != null) {
					sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(statusParam);
					if (boo) {
						dbUtility.commit();
					} else {
						dbUtility.rollback();
					}
				} else {
					dbUtility.commit();
				}

			} else {
				dbUtility.rollback();
			}

		} catch (Exception e) {
			boo = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception in RegCommonDAO:insrtApplcntTransPartyPropDetls rollback.");
			}
			logger.debug(e.getMessage(),e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}*/

	/**
	 * for getting sequence id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getRegTxnIdSeq() throws Exception {
	
		String regTxnIdSeq = "0";
		try {
			dbUtility = new DBUtility();
			String SQL1 = RegCommonSQL.GET_TODAY_APP_COUNT;
			logger.debug(SQL1);
			dbUtility.createStatement();
			String number1 = dbUtility.executeQry(SQL1);
			if (number1.equalsIgnoreCase("0")) {
				String drpqry = RegCommonSQL.DROP_REG_TXN_ID_SEQ_1;
				dbUtility.executeUpdate(drpqry);
				String crtqry = RegCommonSQL.CREATE_REG_TXN_ID_SEQ_1;
     			dbUtility.executeUpdate(crtqry);
			}
		regTxnIdSeq = dbUtility.executeQry(RegCommonSQL.GET_REG_TXN_ID_SEQ);
		} catch (Exception exception) {
			logger.debug("Exception in getRegTxnIdSeq-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return regTxnIdSeq;

	}
	
	
	
	//for first 3 digit seq
	
	
	public String getfSeq() throws Exception {
		
		String regTxnIdSeq = "0";
		try {
			dbUtility = new DBUtility();
			//String SQL1 = RegCommonSQL.GET_TODAY_APP_COUNT;
			//logger.debug(SQL1);
			dbUtility.createStatement();
			//String number1 = dbUtility.executeQry(SQL1);
			//if (number1.equalsIgnoreCase("0")) {
			//	String drpqry = RegCommonSQL.DROP_REG_TXN_ID_SEQ_1;
			//	dbUtility.executeUpdate(drpqry);
				//String crtqry = RegCommonSQL.CREATE_REG_TXN_ID_SEQ_1;
     		//	dbUtility.executeUpdate(crtqry);
			//}
		regTxnIdSeq = dbUtility.executeQry(RegCommonSQL.GET_FIRST_REG_TXN_ID_SEQ);
		} catch (Exception exception) {
			logger.debug("Exception in getRegTxnIdSeq-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return regTxnIdSeq;

	}
	
	//end

	/**
	 * for getting new sequence id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getNewRegTxnIdSeq() throws Exception {

		// int regTxnIdSeq = 0;
		String regTxnIdSeq = "0";

		try {
			dbUtility = new DBUtility();
			// dbUtility.createStatement();
			dbUtility.createStatement();
			dbUtility.executeUpdate(RegCommonSQL.DROP_REG_TXN_ID_SEQ);
			//dbUtility.createStatement();
			dbUtility.executeUpdate(RegCommonSQL.RESTART_REG_TXN_ID_SEQ);
			regTxnIdSeq = dbUtility.executeQry(RegCommonSQL.GET_REG_TXN_ID_SEQ);

		} catch (Exception exception) {

			System.out.println("Exception in getNewRegTxnIdSeq-RegCommonDAO"
					+ exception);

		} finally {
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
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	/*
	 * public boolean insertTxnDetails(String[] party) { boolean boo=false; try
	 * { dbUtility = new DBUtility(); sql =
	 * RegCommonSQL.INSERT_REG_INIT_TXN_DETLS;
	 * dbUtility.createPreparedStatement(sql);
	 * boo=dbUtility.executeUpdate(party); if(boo){ boo=false;
	 * dbUtility.commit(); String param1[]=new String[1]; param1[0]=party[0];
	 * sql = RegCommonSQL.UPDATE_REG_INIT_TXN_PARTY_DETLS;
	 * dbUtility.createPreparedStatement(sql);
	 * boo=dbUtility.executeUpdate(param1); if(boo) dbUtility.commit(); else
	 * dbUtility.rollback(); } else dbUtility.rollback(); }catch (Exception e) {
	 * e.printStackTrace(); }finally{ try { dbUtility.closeConnection(); } catch
	 * (Exception e) { logger.error("RegCommonDAO in dao start" +
	 * e.getStackTrace()); } } return boo; }
	 */
	public boolean insertTxnDetails(String[] param, String regFeeCheck) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			//sql = RegCommonSQL.UPDATE_PAYMENT_FLAG;
			//dbUtility.createPreparedStatement(sql);
			//boo = dbUtility.executeUpdate(param);
			if (true) {

				/*if (param[0].trim().equalsIgnoreCase(
						RegInitConstant.PAYMENT_FLAG_COMPLETED)) {*/

					String[] statusParam = {
							RegInitConstant.STATUS_FINAL_SCREEN,regFeeCheck,
							param[1].trim() };

					//sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
					sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS_FEE;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(statusParam);
					if (boo) {
						dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				/*} else {
					dbUtility.commit();
				}*/
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	
	public boolean updatePaymentStatus(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_PAYMENT_FLAG;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {

				
					dbUtility.commit();
				
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	//updating challan by po
	
	public boolean updatePaymentStatusEfile(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_PAYMENT_FLAG_EFILE;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {

				
					dbUtility.commit();
				
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	//end
	
	
//updating online payment  by po
	
	public boolean updateTableEfile(String parentKeyEfile) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_PAYMENT_FLAG_EFILE_ONLINE;
			dbUtility.createPreparedStatement(sql);
			String arr1[]=new String[2];
			arr1[0]="C";
			arr1[1]=parentKeyEfile;
			
			boo = dbUtility.executeUpdate(arr1);
			if (boo) {

				
					dbUtility.commit();
					//below code to update efile_dtls table
					dbUtility = new DBUtility();
					String efileTxnId=null;
					String temp[] = new String[1];
					temp[0] = parentKeyEfile;
						sql = RegCommonSQL.GET_EFILE_ID_PAYMENT;
						dbUtility.createPreparedStatement(sql);
						efileTxnId = dbUtility.executeQry(temp);
						
						String arr2[]=new String[3];
						arr2[0]="COMPLETE";
						arr2[1]="N";
						arr2[2]=efileTxnId;
						sql = RegCommonSQL.UPDATE_EFILE_TABLE;
						dbUtility.createPreparedStatement(sql);
						boo = dbUtility.executeUpdate(arr2);
				
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	//end
	
	
	
	public boolean updateEfileTable(String TempIdPo1) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String arr1[]=new String[3];
			arr1[0]="COMPLETE";
			arr1[1]="N";
			arr1[2]=TempIdPo1;
			sql = RegCommonSQL.UPDATE_EFILE_TABLE;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(arr1);
			if (boo) {

				
					dbUtility.commit();
				
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	public boolean insertTxnDetailsFee(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_PAYMENT_FLAG_FEE;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				
				dbUtility.commit();
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
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
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertMappingDetails(String[] ownerId, String[] other) {
		boolean boo = false;

		int ownerCount = ownerId.length;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			for (int i = 0; i < ownerCount; i++) {
				String[] param = new String[6];
				param[0] = other[0];
				param[1] = other[1];
				param[2] = ownerId[i];
				param[3] = other[2];
				// param[4]=other[3];
				param[4] = other[3];
				param[5] = other[4];

				

					sql = RegCommonSQL.INSERT_POA_OWNER_MAPPING;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(param);
					if (boo)
						dbUtility.commit();
					else
					{
						dbUtility.rollback();
						throw new Exception();
					}
				
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return boo;
	}

	/**
	 * getPreviousRegIdDate for getting latest reg id date from db
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public String[] getPreviousRegIdDate() throws Exception {
		String[] previousDate = new String[3];
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			previousDate[0] = dbUtility
					.executeQry(RegCommonSQL.PREVIOUS_REG_ID_DD);
			//dbUtility.createStatement();
			previousDate[1] = dbUtility
					.executeQry(RegCommonSQL.PREVIOUS_REG_ID_MM);
			//dbUtility.createStatement();
			previousDate[2] = dbUtility
					.executeQry(RegCommonSQL.PREVIOUS_REG_ID_YY);

		} catch (Exception exception) {
			System.out.println("Exception in getPreviousRegIdDate()"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return previousDate;

	}

	/**
	 * getPreviousRegIdDate for getting latest reg id date from db
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	/*
	 * public String getPreviousRegIdDate() throws Exception { String
	 * previousDate = new String(); try { dbUtility = new DBUtility();
	 * dbUtility.createStatement(); previousDate =
	 * dbUtility.executeQry(RegCommonSQL.PREVIOUS_CREATED_DATE);
	 * 
	 * 
	 * } catch (Exception exception) {
	 * System.out.println("Exception in getPreviousRegIdDate()" + exception); }
	 * return previousDate;
	 * 
	 * }
	 */
	/**
	 * getCurrDateTime for getting current system date/time from db.
	 * 
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
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return currDateTime;

	}

	/**
	 * getApplicantRegistrationDetls for getting applicant details registration
	 * initiation from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantRegistrationDetls(String UserId)
			throws Exception {
		ArrayList appRegDetls = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { UserId };
			String query = RegCommonSQL.GET_APPLICANT_REG_DETLS;
			dbUtility.createPreparedStatement(query);
			appRegDetls = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getApplicantRegistrationDetls"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return appRegDetls;

	}

	/*public double calcBalanceReg(int fromAdju,String regTxnId, String adjuFlag)throws Exception{
		
		double balance=0;
		//ArrayList finalList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { regTxnId };
			String query = RegCommonSQL.TOTAL_PAYABLE_AMOUNT;
			String total="0";
			if(fromAdju==1){
				query = RegCommonSQL.GET_ADJU_FEE;//get adjudication total payable fee
				String[] newParam = { "FUN_029" };
				dbUtility.createPreparedStatement(query);
				total = dbUtility.executeQry(newParam);
				
			}else{
			if(adjuFlag!=null && ("R").equalsIgnoreCase(adjuFlag)){
			query = RegCommonSQL.TOTAL_PAYABLE_AMOUNT_ADJU;
			}
			
			dbUtility.createPreparedStatement(query);
			total = dbUtility.executeQry(param);
			
			
			}
			
			
			String query2 = RegCommonSQL.TOTAL_PAID;
			dbUtility.createPreparedStatement(query);
			String paid = dbUtility.executeQry(param);
			
			balance=Double.parseDouble(total)-Double.parseDouble(paid);

		} catch (Exception exception) {
			logger.debug("Exception in calcBalanceReg"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return balance;

	
	}*/
	
	
	
	public boolean changeStatus(String TempRejId){
		boolean unconsumeflag=false;
		
		boolean finalStatus=false;
		
boolean statusreferDR=false;
		
		/*try{
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(RegCommonSQL.INSERT_SR_REJECT);
		String arr1[]=new String[2];
		arr1[0]=TempRejId;
		arr1[1]="R";
		
		
		statusreferDR=dbUtility.executeUpdate(arr1);
		
		if(statusreferDR){*/

			try{
				
				dbUtility = new DBUtility();
				String arr2[]=new String[1];
				arr2[0]=TempRejId;
	            sql = RegCommonSQL.UPDATE_EFILE_TXN_STATUS_REJECT;
	            dbUtility.createPreparedStatement(sql);
	            finalStatus=dbUtility. executeUpdate(arr2);
	            dbUtility.commit();
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		
		
		
		/*catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}*/
		
		
		finally {
            try {
                  dbUtility.closeConnection();
            } catch (Exception e) {
                  logger.error("RegCommonDAO in dao start" + e.getStackTrace());
            }
      }
		
		return finalStatus;
		
	}
	
	
	//to get property details
	
	public ArrayList getPropertyDetails(String TempPropId,String propId){
		
ArrayList efilePropertyList = new ArrayList();
		
		String param[] = new String[2];
		param[0] = TempPropId;
		param[1]=propId;
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PROPERTY_DETAILS;
			
			dbUtility.createPreparedStatement(sql);
			efilePropertyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efilePropertyList;
		
	}
	

	public ArrayList getPropertyDetails1(String propertyId){
		
		ArrayList efilePropertyList = new ArrayList();
				
				String param[] = new String[1];
				param[0] = propertyId;
				
				try {
					dbUtility = new DBUtility();
					
					sql = RegCommonSQL.GET_PROPERTY_DETAILS1;
					
					dbUtility.createPreparedStatement(sql);
					efilePropertyList = dbUtility.executeQuery(param);
					 
					// String abc=flag;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.debug(e.getMessage(),e);
				}finally
				{
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						
						logger.debug(e.getMessage(),e);
					}
				}

				
				return efilePropertyList;
				
			}
			
	
	public String getuploadPathSR(String TempPropId) throws Exception {
		
String efileDutyId=null;
		
		String param[] = new String[1];
		param[0] = TempPropId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_UPLOAD_PATH;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileDutyId;
		


	}
////to get duty details at sr end
	
	
	public String getPartyType(String partyId1) throws Exception {
		
		String efileDutyId=null;
				
				String param[] = new String[1];
				param[0] = partyId1;
				
				
				try {
					dbUtility = new DBUtility();
					
					sql = RegCommonSQL.GET_PARTY_TYPE;
					
					dbUtility.createPreparedStatement(sql);
					efileDutyId = dbUtility.executeQry(param);
					 
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.debug(e.getMessage(),e);
				}finally
				{
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						
						logger.debug(e.getMessage(),e);
					}
				}

				
				return efileDutyId;
				


			}
	
	
	
	
	public String getPartyTypeefileid(String partyId1) throws Exception {
		
		String efileDutyId=null;
				
				String param[] = new String[1];
				param[0] = partyId1;
				
				
				try {
					dbUtility = new DBUtility();
					
					sql = RegCommonSQL.GET_PARTY_TYPE_efileid;
					
					dbUtility.createPreparedStatement(sql);
					efileDutyId = dbUtility.executeQry(param);
					 
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.debug(e.getMessage(),e);
				}finally
				{
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						
						logger.debug(e.getMessage(),e);
					}
				}

				
				return efileDutyId;
				


			}
	
	
	public String getFlagPoA(String partyTypeid1) throws Exception {
		
		String efileDutyId=null;
				
				String param[] = new String[1];
				param[0] = partyTypeid1;
				
				
				try {
					dbUtility = new DBUtility();
					
					sql = RegCommonSQL.GET_POA_FLAG;
					
					dbUtility.createPreparedStatement(sql);
					efileDutyId = dbUtility.executeQry(param);
					 
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.debug(e.getMessage(),e);
				}finally
				{
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						
						logger.debug(e.getMessage(),e);
					}
				}

				
				return efileDutyId;
				


			}
	
	public String getpartyname(String partyTypeid) throws Exception {
		
		String efileDutyId=null;
				
				String param[] = new String[1];
				param[0] = partyTypeid;
				
				
				try {
					dbUtility = new DBUtility();
					
					sql = RegCommonSQL.GET_PARTY_TYPE_NAME;
					
					dbUtility.createPreparedStatement(sql);
					efileDutyId = dbUtility.executeQry(param);
					 
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.debug(e.getMessage(),e);
				}finally
				{
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						
						logger.debug(e.getMessage(),e);
					}
				}

				
				return efileDutyId;
				


			}
	
	public ArrayList getDutyEfile(String TempDutyId,String EfileTempId){
		
		ArrayList efileDutyList = new ArrayList();
		
		String param[] = new String[2];
		param[0] = EfileTempId;
		param[1] = TempDutyId;
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_DUTY_DETAILS;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileDutyList;
		
	}
	
	

	public ArrayList getDutyEfileNonPayment(String TempDutyId,String EfileTempId){
		
		ArrayList efileDutyList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = TempDutyId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_DUTY_DETAILS_1;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		return efileDutyList;
		
	}
	
	
public ArrayList getInstExtraList(String EfileTempId){
		
		ArrayList efileDutyList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = EfileTempId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_INST_EXTRA_DTLS;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileDutyList;
		
	}
	
public ArrayList getInstExtraList1(String EfileTempId){
	
	ArrayList efileDutyList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = EfileTempId;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_INST_EXTRA_DTLS1;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efileDutyList;
	
}
	

	public ArrayList getExemptionListEfile(String TempDutyId){
		
		ArrayList efileDutyList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = TempDutyId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_EXEMPTION_DETAILS_1;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		return efileDutyList;
		
	}
	
	
public ArrayList getownerListEfile(String efiletempid,String partyId1){
		
		ArrayList efileDutyList = new ArrayList();
		
		String param[] = new String[2];
		param[0] = efiletempid;
		param[1]=partyId1;
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_OWNER_DETLS;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileDutyList;
		
	}


public ArrayList getphysicalList(String EfileTempId){
	
	ArrayList efileDutyList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = EfileTempId;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_PHYSICAL_DETLS;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efileDutyList;
	
}

	
	
public ArrayList getExemptionListEfile1(String TempDutyIdPo){
		
		ArrayList efileDutyList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = TempDutyIdPo;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_EXEMPTION_DETAILS_1;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileDutyList;
		
	}

public ArrayList getExemptionListEfile4(String TempDutyIdPo){
	
	ArrayList efileDutyList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = TempDutyIdPo;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_EXEMPTION_DETAILS_4;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efileDutyList;
	
}
	
	
public ArrayList getDutyEfileNonPayment1(String TempDutyIdPo,String EfileTempId){
		
		ArrayList efileDutyList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = TempDutyIdPo;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_DUTY_DETAILS_2;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileDutyList;
		
	}
	
	
	
public ArrayList getDutyPhyEfile(String TempDutyId,String EfileTempId){
		
		ArrayList efileDutyList = new ArrayList();
		
		String param[] = new String[2];
		param[0] = EfileTempId;
		param[1] = TempDutyId;
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_DUTY_PHY_DETAILS;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		return efileDutyList;
		
	}


public ArrayList getDutyPhyEfilePo(String TempDutyIdPo,String EfileTempId){
	
	ArrayList efileDutyList = new ArrayList();
	
	String param[] = new String[2];
	param[0] = EfileTempId;
	param[1] = TempDutyIdPo;
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_DUTY_PHY_DETAILS;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efileDutyList;
	
}
	
public ArrayList getDutyEfilePo(String TempDutyId,String EfileTempId){
		
		ArrayList efileDutyList = new ArrayList();
		
		String param[] = new String[2];
		param[0] = EfileTempId;
		param[1] = TempDutyId;
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_DUTY_DETAILS;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileDutyList;
		
	}
	
	
////to get print details at sr end
	
	public ArrayList getprintDetails(String TempDutyId){
		
		ArrayList efilePrintList = new ArrayList();
		
		String param[] = new String[2];
		param[0] = TempDutyId;
		param[1] = TempDutyId;
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PRINT_DETAILS;
			
			dbUtility.createPreparedStatement(sql);
			efilePrintList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efilePrintList;
		
	}
	
	
	
public ArrayList getprintDetailsNonPayment(String TempIdPrint){
		
		ArrayList efilePrintList = new ArrayList();
		
		String param[] = new String[2];
		param[0] = TempIdPrint;
		param[1] = TempIdPrint;
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PRINT_DETAILS_NONPAYMENT_MODE;
			
			dbUtility.createPreparedStatement(sql);
			efilePrintList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efilePrintList;
		
	}
	
	
public ArrayList getprintPhysicalDetails(String TempDutyId){
		
		ArrayList efilePrintList = new ArrayList();
		
		String param[] = new String[2];
		param[0] = TempDutyId;
		param[1] = TempDutyId;
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PRINT_DETAILS_PHYSICAL;
			
			dbUtility.createPreparedStatement(sql);
			efilePrintList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		return efilePrintList;
		
	}
	
	
	//to get duty details from dashboard resume
public ArrayList getdutyDetails(String TempDutyId){
		
		ArrayList efilePrintList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = TempDutyId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_DUTY_DASHHBOARD_DETAILS;
			
			dbUtility.createPreparedStatement(sql);
			efilePrintList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efilePrintList;
		
	}

public ArrayList getdutyDetails1(String TempDutyId){
	
	ArrayList efilePrintList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = TempDutyId;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_DUTY_PHYSICAL_DASHHBOARD_DETAILS1;
		
		dbUtility.createPreparedStatement(sql);
		efilePrintList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efilePrintList;
	
}
// Added by Gulrej on 9/5/2017

public ArrayList getPhysicalStampDetails(String txnId){
	
	ArrayList physicalStampList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = txnId;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_PHYSICAL_STAMP_DASHHBOARD_DETAILS;
		
		dbUtility.createPreparedStatement(sql);
		physicalStampList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return physicalStampList;
	
}

	
	
	
////to get print party details at sr end
	
	public ArrayList getpartyPrintDetails(String TempDutyId){
		
		ArrayList efilePrintList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = TempDutyId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PRINT_PARTY_DETAILS;
			
			dbUtility.createPreparedStatement(sql);
			efilePrintList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efilePrintList;
		
	}
	
	
	
	
////to get bank details at sr end
	
	public ArrayList getBankDtls(String TempId){
		
		ArrayList efileBankList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = TempId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_BANK_DTLS;
			
			dbUtility.createPreparedStatement(sql);
			efileBankList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileBankList;
		
	}
	
	
	
	//refer to dr insert in table
	public boolean finalReferDR(String efileId,String remarks,String srID){
		
		boolean statusreferDR=false;
		
		boolean	finalStatus=false;
		boolean	finalCount=false;
		
		try{
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(RegCommonSQL.INSERT_SR_REFER_TO_DR);
		String arr1[]=new String[3];
		arr1[0]=efileId;
		arr1[1]="Refer To DR";
		arr1[2]=remarks;
		
		
		statusreferDR=dbUtility.executeUpdate(arr1);
		
		if(statusreferDR){

			try{
				
				dbUtility = new DBUtility();
				String arr2[]=new String[1];
				arr2[0]=efileId;
	            sql = RegCommonSQL.UPDATE_EFILE_TXN_STATUS_REFER_TO_DR;
	            dbUtility.createPreparedStatement(sql);
	            finalStatus=dbUtility. executeUpdate(arr2);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		if(finalStatus){
    		dbUtility = new DBUtility();
			String arr3[]=new String[1];
			arr3[0]=srID;
           // sql = RegCommonSQL.UPDATE_EFILE_SR_COUNT;
            dbUtility.createPreparedStatement(RegCommonSQL.UPDATE_EFILE_SR_COUNT);
            finalCount=dbUtility. executeUpdate(arr3);
        	dbUtility.commit();
    	}
		
		}
		
		
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return finalStatus;
		
	}
	
	
	//end
	
	
	
	//final submit by SR
	public boolean finalSubmit(String efileId,String saveFilePath,String remarks,String srID){
		
		boolean status=false;
		boolean finalStatus=false;
		boolean finalCount=false;
		
		try{
			dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(RegCommonSQL.INSERT_SR_FINAL_DETAILS);
		String arr1[]=new String[4];
		arr1[0]=efileId;
		arr1[1]=remarks;
		arr1[2]=saveFilePath;
		arr1[3]="Approved";
		
		status=dbUtility.executeUpdate(arr1);
		dbUtility.commit();
		if(status){
		try{
			
			dbUtility = new DBUtility();
			String arr2[]=new String[1];
			arr2[0]=efileId;
            sql = RegCommonSQL.UPDATE_EFILE_TXN_STATUS;
            dbUtility.createPreparedStatement(sql);
            finalStatus=dbUtility. executeUpdate(arr2);
        	dbUtility.commit();
        	if(finalStatus){
        		dbUtility = new DBUtility();
    			String arr3[]=new String[1];
    			arr3[0]=srID;
               // sql = RegCommonSQL.UPDATE_EFILE_SR_COUNT;
                dbUtility.createPreparedStatement(RegCommonSQL.UPDATE_EFILE_SR_COUNT);
                finalCount=dbUtility. executeUpdate(arr3);
            	dbUtility.commit();
        	}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		return finalStatus;
		
	}
	
	
	//end
	
	
	//final reject  by SR
	public boolean finalSubmitReject(String TempId,String TempRejId,String saveFilePath,String remarks,String srID){
		
		boolean status=false;
		boolean finalStatus=false;
		boolean checkestampCode=false;
		boolean finalCount=false;
		
		try{
			dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(RegCommonSQL.INSERT_SR_FINAL_DETAILS);
		String arr1[]=new String[4];
		arr1[0]=TempRejId;
		arr1[1]=remarks;
		arr1[2]=saveFilePath;
		arr1[3]="Reject";
		
		status=dbUtility.executeUpdate(arr1);
		dbUtility.commit();
		if(status){
		try{
			//check whether estamp code is mapped to efile id or not
			dbUtility = new DBUtility();
			String estampCode=null;
			boolean estampCodeCheck=false;
			String arr3[]=new String[1];
			arr3[0]=TempId;
			   sql = RegCommonSQL.GET_CODE;
			dbUtility.createPreparedStatement(sql);
			estampCode = dbUtility.executeQry(arr3);
			if(estampCode!=null && !estampCode.isEmpty()){
				estampCodeCheck=true;
			}
			
			
			//end
			if(estampCodeCheck){
			dbUtility = new DBUtility();
			String arr2[]=new String[1];
			arr2[0]=TempId;
            sql = RegCommonSQL.SET_ESTAMP_STATUS;
            dbUtility.createPreparedStatement(sql);
            finalStatus=dbUtility. executeUpdate(arr2);
        	dbUtility.commit();
		}
			if(finalStatus){
        		dbUtility = new DBUtility();
    			String arr4[]=new String[1];
    			arr4[0]=srID;
               // sql = RegCommonSQL.UPDATE_EFILE_SR_COUNT;
                dbUtility.createPreparedStatement(RegCommonSQL.UPDATE_EFILE_SR_COUNT);
                finalCount=dbUtility. executeUpdate(arr4);
            	dbUtility.commit();
        	}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		return finalStatus;
		
	}
	
	
	//end
	
public boolean checkExempFlag(String DutyIdCheck){
		
		String  status;
		boolean finalStatus=false;
		
		try{
			dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(RegCommonSQL.CHECK_EXEMP_FLAG);
		String arr1[]=new String[1];
		arr1[0]=DutyIdCheck;
		
		
      //  estampflag=dbUtility.executeQry(param);
		status=dbUtility.executeQry(arr1);
		
		if(status.equalsIgnoreCase("N")){
			finalStatus=false;
		}
		
		else{
			finalStatus=true;
		}
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		return finalStatus;
		
	}


public boolean updatePaymentStatus(String EfileId){
	
	boolean status=false;
	boolean finalStatus=false;
	
	try{
		dbUtility = new DBUtility();
	dbUtility.createPreparedStatement(RegCommonSQL.UPDATE_EFILE_EXEMP_FLAG);
	String arr1[]=new String[1];
	arr1[0]=EfileId;
	
	
	status=dbUtility.executeUpdate(arr1);
	
	
	
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	return status;
	
}
	
	
	//to get appid and applicant details
	public ArrayList getappId(String TempId){
		ArrayList pendingAppList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = TempId;
		

		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_TEMP_APP_TXN_ID;
			
			dbUtility.createPreparedStatement(sql);
			pendingAppList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return pendingAppList;
		
	}
	public ArrayList getappId1(String TempId){
		ArrayList pendingAppList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = TempId;
		

		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_TEMP_APP_TXN_ID1;
			
			dbUtility.createPreparedStatement(sql);
			pendingAppList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return pendingAppList;
		
	}
	
	public ArrayList getappId3(String EfileTempId){
		ArrayList pendingAppList = new ArrayList();
		
		String param[] = new String[1];
		param[0] = EfileTempId;
		

		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_TEMP_APP_TXN_ID1;
			
			dbUtility.createPreparedStatement(sql);
			pendingAppList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return pendingAppList;
		
	}
	
	//end 
	
	
	//to get duty details on sr dashboard
	public String getTempDutyId(String EfileTempId){
		String efileDutyId=null;
		
		String param[] = new String[1];
		param[0] = EfileTempId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_TEMP_DUTY_TXN_ID;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			 
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileDutyId;
		
	}
	
	public String getTempDutyId1(String TempIdPrint){
		String efileDutyId=null;
		
		String param[] = new String[1];
		param[0] = TempIdPrint;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_TEMP_DUTY_TXN_ID1;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			 
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efileDutyId;
		
	}
	
	//for first 3 digit seq


	public String firstSeqPO() throws Exception {
		
		String regTxnIdSeq = "0";
		try {
			dbUtility = new DBUtility();
			
			dbUtility.createStatement();
			
		regTxnIdSeq = dbUtility.executeQry(RegCommonSQL.GET_FIRST_REG_TXN_ID_SEQ_PO);
		} catch (Exception exception) {
			logger.debug("Exception in getRegTxnIdSeq-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return regTxnIdSeq;

	}

	
public String getsrOffId(String TempDutyIdPrint) throws Exception {
		
	String efileDutyId=null;
	
	String param[] = new String[1];
	param[0] = TempDutyIdPrint;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_SR_OFFICE_ID;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyId = dbUtility.executeQry(param);
		 
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efileDutyId;
	}


public String getPropertyId1(String TempPropId) throws Exception {
	
	String efileDutyId=null;
	
	String param[] = new String[1];
	param[0] = TempPropId;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_PROPERTY1_ID;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyId = dbUtility.executeQry(param);
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	
}
	
	return efileDutyId;
	}


public String getstautsSR(String TempId) throws Exception {
	
	String efileDutyId=null;
	
	String param[] = new String[1];
	param[0] = TempId;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_STATUS_SR;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyId = dbUtility.executeQry(param);
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	
}
	
	return efileDutyId;
	}


public String getstautsPO(String TempPropId) throws Exception {
	
	String efileDutyId=null;
	
	String param[] = new String[1];
	param[0] = TempPropId;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_STATUS_SR;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyId = dbUtility.executeQry(param);
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	
}
	
	return efileDutyId;
	}
	
	public ArrayList getDistrictEfile(String tempEfileId){
		ArrayList mainList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		DBUtility dbUtil=null;
		try {
			
			
			dbUtility = new DBUtility();
	        String[] param = new String[1];
	        param[0] = tempEfileId;
	        sql = RegCommonSQL.DISTRICT_LIST;
	        dbUtility.createPreparedStatement(sql);
	        list1=dbUtility. executeQuery (param);
			
			
			
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return list1;
	}

	public String efileTxnIdSeqPo() throws Exception {
		
		String regTxnIdSeq = "0";
		try {
			dbUtility = new DBUtility();
			
			dbUtility.createStatement();
			
		regTxnIdSeq = dbUtility.executeQry(RegCommonSQL.GET_LAST_EFILE_TXN_ID_SEQ_PO);
		} catch (Exception exception) {
			logger.debug("Exception in getRegTxnIdSeq-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return regTxnIdSeq;

	}

	
	public boolean getCheckMode(String TempDutyIdPrint){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = TempDutyIdPrint;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_PAYMENT_MODE;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return checkModeFlag;
		
	}
	
	
	public boolean insertDocEfileNo(String docEfileNo,String TempDutyIdPrint){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[2];
		param[0] = docEfileNo;
		param[1] = TempDutyIdPrint;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.UPDATE_DOC_EFILE_NO;
			
			dbUtility.createPreparedStatement(sql);
			checkModeFlag = dbUtility.executeUpdate(param);
			
			
			 
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return checkModeFlag;
		
	}
	
	public boolean getcheckModePhysical(String TempDutyIdPrint){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = TempDutyIdPrint;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_PAYMENT_MODE1;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return checkModeFlag;
		
	}
	
	public boolean getCheckModePO(String EfileTempId){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = EfileTempId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_PAYMENT_MODE_PO;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return checkModeFlag;
		
	}
	
	public boolean getCheckModePO1(String EfileTempId){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = EfileTempId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_PAYMENT_MODE_PO1;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return checkModeFlag;
		
	}
	
	public boolean getCheckModeDetails(String EfileTempId){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = EfileTempId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_PAYMENT_MODE_DETLS;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return checkModeFlag;
		
	}
	
	public boolean getCheckModeDetails2(String TempDutyId){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = TempDutyId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_PAYMENT_MODE_DETLS2;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return checkModeFlag;
		
	}
	
	
	public boolean getCheckModeDetails1(String EfileTempId){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = EfileTempId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_PAYMENT_MODE_DETLS1;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return checkModeFlag;
		
	}
	
	
	
	public boolean getCheckExemptionDetails(String TempDutyId){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = TempDutyId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_EXEMPTION;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return checkModeFlag;
		
	}
	
	
	public boolean getCheckExemptionDetails1(String TempDutyIdPo){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = TempDutyIdPo;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_EXEMPTION;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return checkModeFlag;
		
	}
	
	
	public boolean getCheckModeDetails3(String TempDutyId){
		String efileDutyId=null;
		boolean checkModeFlag=false;
		String param[] = new String[1];
		param[0] = TempDutyId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_PAYMENT_MODE_DETLS3;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			
			if(efileDutyId!=null && !efileDutyId.isEmpty() ){
				checkModeFlag=true;
			}
			else{
				checkModeFlag=false;
			}
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return checkModeFlag;
		
	}
	
	//to get tempefile id 
	public String getEfileTempId(String TempId){
		String efileDutyId=null;
		
		String param[] = new String[1];
		param[0] = TempId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_TEMP_Efile_TXN_ID;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return efileDutyId;
		
	}
	
	
	public String getEfileTempId1(String TempId){
		String efileDutyId=null;
		
		String param[] = new String[1];
		param[0] = TempId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_TEMP_Efile_TXN_ID;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		
		return efileDutyId;
		
	}
	
	//to get tempefile no while printing pdf
	
	//to get duty details on sr dashboard
	public String getTempEfileNo(String efileNo){
		String efileDutyId=null;
		
		String param[] = new String[1];
		param[0] = efileNo;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_TEMP_EFILE_NO;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return efileDutyId;
		
	}
	
	
	public String getefileGenCert(String TempEfileNo){
		String efileDutyId=null;
		
		String param[] = new String[1];
		param[0] = TempEfileNo;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_EFILE_CERT_GEN;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return efileDutyId;
		
	}
	
	public String checkefileGenCert(String TempEfileNo){
		String efileDutyId=null;
		
		String param[] = new String[1];
		param[0] = TempEfileNo;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_CHECK_EFILE_GEN_CERTIFICATE;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return efileDutyId;
		
	}
	
	
	public String getefileId(String TempRejId){
		String efileDutyId=null;
		
		String param[] = new String[1];
		param[0] = TempRejId;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_EFILE_ID;
			
			dbUtility.createPreparedStatement(sql);
			efileDutyId = dbUtility.executeQry(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return efileDutyId;
		
	}
	
	public boolean getStatus(String efileNumber,String userId){
		boolean  status;
		boolean finalStatus=false;
		
		try{
			dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(RegCommonSQL.GET_STATUS_EFILE_NO);
		String arr1[]=new String[2];
		arr1[0]=efileNumber;
		arr1[1]=userId;
		
		
      //  estampflag=dbUtility.executeQry(param);
		status=dbUtility.executeUpdate(arr1);
		
		if(status){
			finalStatus=true;
		}
		
		else{
			finalStatus=false;
		}
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return finalStatus;
		
	}
	
	public boolean getStatusSR(String efileNumber,String userId,String officeId){
		boolean  status;
		boolean finalStatus=false;
		
		try{
			dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(RegCommonSQL.GET_STATUSSR_EFILE_NO);
		String arr1[]=new String[3];
		arr1[0]=efileNumber;
		arr1[1]=userId;
		arr1[2]=officeId;
		
		
      //  estampflag=dbUtility.executeQry(param);
		status=dbUtility.executeUpdate(arr1);
		
		if(status){
			finalStatus=true;
		}
		
		else{
			finalStatus=false;
		}
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return finalStatus;
		
	}
	
	//end 
	
	public boolean updateCertificateGenerationChk(String TempEfileNo) throws Exception
	{
		String tid[] = new String[1];
		tid[0]=TempEfileNo;
		logger.info("inside dao of updateCertificateGenerationChk efiling");
		boolean flag = false;
		DBUtility transmgtUtil = null;
		try{
			transmgtUtil=new DBUtility();
		
		transmgtUtil.createPreparedStatement(RegCommonSQL.UPADTE_CERTIFICATE_CHK_DETLS);
		flag=transmgtUtil.executeUpdate(tid);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			transmgtUtil.closeConnection();
		}
		
		return flag;
	}
	
	
	//To fetchpending list of new search 
	
	public ArrayList	getPendingEfile(String userId,String officeId){
		ArrayList pendingList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PENDING_APPLICATIONS;
			String param[] = new String[3];
			param[0] = "Pending";
			param[1]=userId;
			param[2]=officeId;
			dbUtility.createPreparedStatement(sql);
			pendingList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return pendingList;
	}
	
	
	public ArrayList	getPendingEfilePO(String UserId){
		ArrayList pendingList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PENDING_APPLICATIONS_PO;
			String param[] = new String[2];
			param[0] = "Pending";
			param[1]=UserId;
			
			dbUtility.createPreparedStatement(sql);
			pendingList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return pendingList;
	}
	
//To fetch submit list of new search 
	
	public ArrayList	getSubmitEfile(String userId,String officeId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_SUBMIT_APPLICATIONS;
			String param[] = new String[3];
			param[0] = "Approved";
			param[1]=userId;
			param[2]=officeId;
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
//To fetch submit list of new search  PO
	
	public ArrayList	getSubmitEfilePo(){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_SUBMIT_APPLICATIONS_PO;
			String param[] = new String[1];
			param[0] = "Approved";
			
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
	
//To fetch reject list of new search 
	
	public ArrayList	getRejectEfile(String userId,String officeId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_REJECT_APPLICATIONS;
			String param[] = new String[3];
			param[0] = "Reject";
			param[1]=userId;
			param[2]=officeId;
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
	public ArrayList	getRejectEfilePO(String UserId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_REJECT_APPLICATIONS_PO;
			String param[] = new String[2];
			param[0] = "Reject";
			param[1]=UserId;
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
//To fetch party type gov 
	
	public ArrayList	getpartTypeGov(String partyId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PARTY_TYPE_GOV_APPLICATIONS;
			String param[] = new String[1];
			param[0] = partyId;
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
//To fetch party type Ind 
	
	public ArrayList	getpartTypeInd(String partyId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PARTY_TYPE_IND_APPLICATIONS;
			String param[] = new String[1];
			param[0] = partyId;
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
//To fetch party type Org
	
	public ArrayList	getpartTypeOrg(String partyId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PARTY_TYPE_ORG_APPLICATIONS;
			String param[] = new String[1];
			param[0] = partyId;
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
//To fetch duration list of new search 
	
	public ArrayList	getDurationEfile(String durationFrom,String durationTo,String userId,String officeId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_DURATION_APPLICATIONS;
		//	String param[] = new String[2];
		//	param[0] = durationFrom;
		//	param[1]=durationTo;
			dbUtility.createPreparedStatement(sql);
		//	submitList = dbUtility.executeQuery(param);
			
			
			submitList=	dbUtility.executeQuery(new String[]{durationFrom,durationTo,userId,officeId});
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
	
//To fetch duration list of new search  PO
	
	public ArrayList	getDurationEfilePo(String durationFrom,String durationTo,String userId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_DURATION_APPLICATIONS_PO;
		//	String param[] = new String[2];
		//	param[0] = durationFrom;
		//	param[1]=durationTo;
			dbUtility.createPreparedStatement(sql);
		//	submitList = dbUtility.executeQuery(param);
			
			
			submitList=	dbUtility.executeQuery(new String[]{userId,durationFrom,durationTo});
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
	
	
//To fetch efile list of new search 
	
	public ArrayList	getEfileNumber(String efileNumber,String userId,String officeId){
		ArrayList submitList = new ArrayList();
		String param[] = new String[3];
		param[0] = efileNumber;
		param[1]=userId;
		param[2]=officeId;
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_EFILE_NUMBER_LIST;
			
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
	
//To fetch efile list of new search PO
	
	public ArrayList	getEfileNumberPo(String efileNumber){
		ArrayList submitList = new ArrayList();
		String param[] = new String[1];
		param[0] = efileNumber;
		
		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_EFILE_NUMBER_LIST_PO;
			
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
	
//To fetch refer list of new search 
	
	public ArrayList	getReferEfile(String userId,String officeId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_REFER_APPLICATIONS;
			String param[] = new String[3];
			param[0] = "Refer To DR";
			param[1]=userId;
			param[2]=officeId;
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
	
	public ArrayList	getReferEfilePO(String UserId){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_REFER_APPLICATIONS_PO;
			String param[] = new String[2];
			param[0] = "Refer To DR";
			param[1]=UserId;
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
	
	
//To fetch payment pending at po end 
	
	public ArrayList	getPendingPaymentPO(){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PAYMENT_PENDING_APPLICATIONS;
			String param[] = new String[2];
			param[0] = "Approved";
			param[1] = "Pending";
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
//To fetch payment complete at po end 
	
	public ArrayList	getCompletePaymentPO(){
		ArrayList submitList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PAYMENT_COMPLETE_APPLICATIONS;
			String param[] = new String[2];
			param[0] = "Approved";
			param[1] = "COMPLETE";
			dbUtility.createPreparedStatement(sql);
			submitList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return submitList;
	}
	
	
//To fetch  list of print
	
	public ArrayList	getPrintEfile(String UserId){
		ArrayList printList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PRINT_APPLICATIONS;
			String param[] = new String[3];
			param[0] = "COMPLETE";
			param[1] = "N";
			param[2]=UserId;
			dbUtility.createPreparedStatement(sql);
			printList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		return printList;
	}
	
	
	//to fetch pending application at SR end
	public ArrayList getPendingEfileApps(String userId) {
		ArrayList pendingAppList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PENDING_APPLICATIONS_DETLS_EFILE;
			String param[] = new String[2];
			param[0] = "Pending";
			param[1]=userId;
			
			dbUtility.createPreparedStatement(sql);
			pendingAppList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return pendingAppList;
	}
	
	
	public ArrayList getPendingEfileApps1(String userId,String officeId) {
		ArrayList pendingAppList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PENDING_APPLICATIONS_DETLS_EFILE1;
			String param[] = new String[3];
			param[0] = "Pending";
			param[1]=userId;
			param[2]=officeId;
			dbUtility.createPreparedStatement(sql);
			pendingAppList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		
	}
		return pendingAppList;
	}
	
	
	
	//to fetch pending application at PO end
	public ArrayList getPendingEfileAppsPo(String userId) {
		ArrayList pendingAppList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PENDING_APPLICATIONS_DETLS_EFILE_PO;
			String param[] = new String[3];
			param[0] = "Approved";
			param[1]="Pending";
			param[2]=userId;
			dbUtility.createPreparedStatement(sql);
			pendingAppList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		return pendingAppList;
	}
	
	
	//to fetch pending Initate  application at PO end
	public ArrayList getPendingEfileInitate(String userId) {
		ArrayList pendingAppList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_PENDING_INITATE_APPLICATIONS_PO;
			String param[] = new String[2];
			param[0] = "53";
			param[1]=userId;
			
			dbUtility.createPreparedStatement(sql);
			pendingAppList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		return pendingAppList;
	}
	
	/**
	 * getPendingRegApps for getting pending applications of registration
	 * initiation from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPendingRegApps(String userId, int fromAdju) {
		ArrayList pendingAppList = new ArrayList();
		ArrayList pendingAppListFinal = new ArrayList();

		ArrayList list1 = new ArrayList();

		String[] param = { userId, userId };

		try {
			dbUtility = new DBUtility();

			if (fromAdju == 1) {
				sql = RegCommonSQL.GET_PENDING_APPLICATIONS_DETLS_ADJU;
			} else {
				sql = RegCommonSQL.GET_PENDING_APPLICATIONS_DETLS;
			}
			dbUtility.createPreparedStatement(sql);

			try {

				pendingAppList = dbUtility.executeQuery(param);
				logger
						.debug("Wipro in RegCommonDAO - getPendingRegApps() after dbUtility.executeQuery(sql)");

				pendingAppList.trimToSize();

			} catch (Exception x) {
				logger.debug(x);
				// x.printStackTrace();
			} /*
			 * finally { try {
			 * 
			 * dbUtility.closeConnection(); } catch (Exception ex) {
			 * logger.debug(ex); //ex.printStackTrace(); } }
			 */

		} catch (Exception e) {
			logger.debug(e);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return pendingAppList;
	}

	/**
	 * getSavedRegInitApplication for getting pending applications of
	 * registration initiation from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSavedRegInitApplication(String appId) {
		ArrayList savedAppDets = new ArrayList();
		/*
		 * ArrayList savedTxnPartyDets = new ArrayList(); ArrayList
		 * savedPoaOwnerMapDets = new ArrayList(); ArrayList savedRegInitTxnDets
		 * = new ArrayList();
		 */
		ArrayList finalList = new ArrayList();

		// ArrayList list1 = new ArrayList();

		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			sql = RegCommonSQL.GET_SAVED_AAPLICANT_DETLS;   // get 4 columns
			dbUtility.createPreparedStatement(sql);
			try {
				savedAppDets = dbUtility.executeQuery(param);
				logger
						.debug("Wipro in RegCommonDAO - getPendingRegApps() after dbUtility.executeQuery(sql)");
				if (savedAppDets != null && savedAppDets.size() > 0) {
					finalList.add(savedAppDets);
					/*
					 * sql =
					 * RegCommonSQL.GET_SAVED_TXN_PARTY_DETLS+"'"+appId+"'";
					 * dbUtility.createStatement(); try {
					 * savedTxnPartyDets=dbUtility.executeQuery(sql);
					 * if(savedTxnPartyDets!=null &&
					 * savedTxnPartyDets.size()>0){
					 * finalList.add(savedTxnPartyDets) ; sql =
					 * RegCommonSQL.GET_SAVED_POA_OWNER_MAP_DETLS+"'"+appId+"'";
					 * dbUtility.createStatement(); try {
					 * savedPoaOwnerMapDets=dbUtility.executeQuery(sql);
					 * if(savedPoaOwnerMapDets!=null &&
					 * savedPoaOwnerMapDets.size()>0)
					 * finalList.add(savedPoaOwnerMapDets) ; } catch (Exception
					 * x) { logger.debug(x); x.printStackTrace(); } sql =
					 * RegCommonSQL.GET_SAVED_REGINIT_TXN_DETLS+"'"+appId+"'";
					 * dbUtility.createStatement(); try {
					 * savedRegInitTxnDets=dbUtility.executeQuery(sql);
					 * if(savedRegInitTxnDets!=null &&
					 * savedRegInitTxnDets.size()>0)
					 * finalList.add(savedRegInitTxnDets) ; } catch (Exception
					 * x) { logger.debug(x); x.printStackTrace(); } }
					 * 
					 * 
					 * } catch (Exception x) { logger.debug(x);
					 * x.printStackTrace(); }
					 */
				}

			} catch (Exception x) {
				logger.debug(x);
				logger.debug(x.getMessage(),x);
			} /*
			 * finally { try {
			 * 
			 * dbUtility.closeConnection(); } catch (Exception ex) {
			 * logger.debug(ex); ex.printStackTrace(); } }
			 */

		} catch (Exception e) {
			logger.debug(e);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return finalList;
	}

	/**
	 * for inserting registration initiation other property details in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean updatePropertyDetails(String[] param, ArrayList khasra,
			String action, String[] regStatus) {
		boolean boo = false;
		String khasraNo;
		String rinPustika;
		String khasraArea;
		String lagaan;
		String north;
		String south;
		String east;
		String west;
		
		String[] khasraNoArr;
		String[] rinPustikaArr;
		String[] khasraAreaArr;
		String[] lagaanArr;
		
		String[] northArr;
		String[] southArr;
		String[] eastArr;
		String[] westArr;
		
		
		
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_REG_INIT_PROP_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			dbUtility.commit();
			if (boo) {

				if (khasra != null && khasra.size()>0) // khasra insertion will only take place if
									// khasra details have been added
				{
					if (action
							.equalsIgnoreCase(RegInitConstant.SAVE_PROP_ACTION)) {
						String[] khasraParam = { param[19] };
						sql = RegCommonSQL.DELETE_REG_INIT_PROP_KHASRA_DETLS;
						dbUtility.createPreparedStatement(sql);
						// boo=dbUtility.executeUpdate(khasraParam);
						dbUtility.executeUpdate(khasraParam);
						dbUtility.commit();
					}

					if (boo)
					// BELOW CODE FOR INSERTING KHASRA DETAILS INTO DATABASE
					{
						/*khasraNo = khasra[0];
						khasraNoArr = khasraNo
								.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);

						rinPustika = khasra[1];
						rinPustikaArr = rinPustika
								.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);

						khasraArea = khasra[2];
						khasraAreaArr = khasraArea
								.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);

						lagaan = khasra[3];
						lagaanArr = lagaan
								.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
						
						north = khasra[4];
						northArr = north
								.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
						
						south = khasra[5];
						southArr = south
								.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
						
						east = khasra[6];
						eastArr = east
								.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
						
						west = khasra[7];
						westArr = west
								.split(RegInitConstant.SPLIT_CONSTANT_KHASRA);*/

						String[] khasraParam = new String[10];
//ArrayList rowList;
						for (int i = 0; i < khasra.size(); i++) {
							//rowList=(ArrayList)khasra.get(i);
							CommonDTO member = (CommonDTO)khasra.get(i);
							
							if(member.getKhasraNum()!=null && !("").equalsIgnoreCase(member.getKhasraNum()) 
									&& member.getNorth()!=null && !("").equalsIgnoreCase(member.getNorth()))
							{
							khasraParam[0] = param[19]; // PROPERTY ID
							khasraParam[1] = member.getKhasraNum();
							khasraParam[2] = member.getRinPustika();
							khasraParam[3] = member.getKhasraArea();
							khasraParam[4] = member.getLagaan();
							
							khasraParam[5] = member.getNorth();
							khasraParam[6] = member.getSouth();
							khasraParam[7] = member.getEast();
							khasraParam[8] = member.getWest();
							
							khasraParam[9] = param[10]; // CREATED BY

							sql = RegCommonSQL.INSERT_REG_INIT_PROP_KHASRA_DETLS;
							dbUtility.createPreparedStatement(sql);
							boo = dbUtility.executeUpdate(khasraParam);
							dbUtility.commit();
							if (!boo) {
								break;
							}
							
							
						}

						}
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				}

				if (boo) {

					if (action
							.equalsIgnoreCase(RegInitConstant.NEXT_TO_MAPPING_ACTION)
							|| action
									.equalsIgnoreCase(RegInitConstant.ADD_MORE_PROP_ACTION)
							|| action
									.equalsIgnoreCase(RegInitConstant.ADD_NEXT_PROP_DETLS_ACTION)) {

						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						boo = dbUtility.executeUpdate(regStatus);
						dbUtility.commit();
						if (boo) {
							dbUtility.commit();
						} else {
							dbUtility.rollback();
							throw new Exception();
						}

					} else {
						dbUtility.commit();
					}
				} else {
					dbUtility.rollback();
					throw new Exception();
				}

			} else {
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			boo = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception while rollback in RegCommonDAO:updatePropertyDetails");
			}
			// e.printStackTrace();
		} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropIdSeq() throws Exception {

		// int regTxnIdSeq = 0;
		String regPropIdSeq = "0";

		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			regPropIdSeq = dbUtility
					.executeQry(RegCommonSQL.GET_REG_PROPERTY_ID_SEQ);

		} catch (Exception exception) {

			logger.debug("Exception in getPropIdSeq-RegCommonDAO"
					+ exception);
			throw new Exception("could not generate property sequence");

		} finally {
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
	 * 
	 * @param String
	 * @return boolean
	 * @author ROOPAM
	 */
	/*
	 * public boolean deleteSelectedAppDetails(String appId) { boolean
	 * boo=false; try{ dbUtility = new DBUtility(); try {
	 * 
	 * sql = RegCommonSQL.DEL_REG_INIT_TRANS_DETLS+"'"+appId+"'";
	 * dbUtility.createStatement(); boo=dbUtility.executeUpdate(sql); if(boo){
	 * logger.debug("tansacting party details deleted"); //dbUtility.commit();
	 * sql = RegCommonSQL.DEL_REG_INIT_PROP_DETLS+"'"+appId+"'";
	 * dbUtility.createStatement(); boo=dbUtility.executeUpdate(sql); if(boo){
	 * logger.debug("property details deleted"); sql =
	 * RegCommonSQL.DEL_REG_INIT_MAP_DETLS+"'"+appId+"'";
	 * dbUtility.createStatement(); boo=dbUtility.executeUpdate(sql); if(boo){
	 * logger.debug("mapping details deleted"); sql =
	 * RegCommonSQL.DEL_REG_INIT_DEED_DETLS+"'"+appId+"'";
	 * dbUtility.createStatement(); boo=dbUtility.executeUpdate(sql); if(boo){
	 * logger.debug("deed details deleted"); dbUtility.commit(); } else
	 * dbUtility.rollback(); } } } else dbUtility.rollback(); }catch (Exception
	 * e) { logger.debug(e); }
	 * 
	 * } catch(Exception e) { logger.debug(e); } finally{ try {
	 * dbUtility.closeConnection(); } catch (Exception e) {
	 * logger.error("RegCommonDAO in dao start" + e.getStackTrace()); } }
	 * 
	 * return boo; }
	 */
	public boolean deleteSelectedAppDetails(String appId,int fromAdju) {
		boolean boo = false;

		// try{
		// dbUtility = new DBUtility();

		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String[] param = { appId };
			if(fromAdju==1){
				sql = RegCommonSQL.DEL_REG_INIT_TXN_DETLS_ADJU;
			}else{
			sql = RegCommonSQL.DEL_REG_INIT_TXN_DETLS;
			}
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
				logger.debug("-------------" + appId
						+ "----------application deleted");

			}
			/*
			 * //dbUtility.commit(); sql =
			 * RegCommonSQL.DEL_REG_INIT_PROP_DETLS+"'"+appId+"'";
			 * //dbUtility.createStatement(); boo=dbUtility.executeUpdate(sql);
			 * if(boo){ logger.debug("property details deleted"); } sql =
			 * RegCommonSQL.DEL_REG_INIT_MAP_DETLS+"'"+appId+"'";
			 * //dbUtility.createStatement(); boo=dbUtility.executeUpdate(sql);
			 * if(boo){ logger.debug("mapping details deleted"); } sql =
			 * RegCommonSQL.DEL_REG_INIT_DEED_DETLS+"'"+appId+"'";
			 * //dbUtility.createStatement(); boo=dbUtility.executeUpdate(sql);
			 * if(boo){ logger.debug("deed details deleted");
			 * dbUtility.commit(); }
			 */
			else {
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e);
			return false;
		}

		// }
		// catch(Exception e) {
		// logger.debug(e);
		// }
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return boo;
	}

	/**
	 * getRegInitPendingAppStatus for getting pending reg init application
	 * details from db
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	/*
	 * public String[] getRegInitPendingAppStatus(String appId) throws Exception
	 * { String[] appStatus = new String[5]; try { dbUtility = new DBUtility();
	 * 
	 * sql=RegCommonSQL.GET_REG_INIT_DEED_STATUS+"'"+appId+"'";
	 * dbUtility.createStatement(); if(dbUtility.executeQry(sql)!=null)
	 * appStatus[4] = dbUtility.executeQry(sql); else appStatus[4] = "";
	 * 
	 * sql=RegCommonSQL.GET_REG_INIT_TXN_STATUS+"'"+appId+"'";
	 * dbUtility.createStatement(); if(dbUtility.executeQry(sql)!=null)
	 * appStatus[3] = dbUtility.executeQry(sql); else appStatus[3] = "";
	 * 
	 * 
	 * sql=RegCommonSQL.GET_REG_INIT_MAP_STATUS+"'"+appId+"'";
	 * dbUtility.createStatement(); if(dbUtility.executeQry(sql)!=null)
	 * appStatus[2] = dbUtility.executeQry(sql); else appStatus[2] = "";
	 * 
	 * 
	 * sql=RegCommonSQL.GET_REG_INIT_PROP_STATUS+"'"+appId+"'";
	 * dbUtility.createStatement(); if(dbUtility.executeQry(sql)!=null)
	 * appStatus[1] = dbUtility.executeQry(sql); else appStatus[1] = "";
	 * 
	 * 
	 * sql=RegCommonSQL.GET_REG_INIT_TRANS_STATUS;
	 * dbUtility.createPreparedStatement(sql); String param[]=new String[2];
	 * param[0]=appId; param[1]=appId; if(dbUtility.executeQry(param)!=null)
	 * appStatus[0] = dbUtility.executeQry(param); else appStatus[0] = "";
	 * 
	 * } catch (Exception exception) {
	 * System.out.println("Exception in getRegInitPendingAppStatus()" +
	 * exception); } return appStatus;
	 * 
	 * }
	 */
	/**
	 * for getting country name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getCountryName(String countryId, String languageLocale)
			throws Exception {

		// int regTxnIdSeq = 0;
		String Countryname = "";

		try {
			dbUtility = new DBUtility();
			String[] param = { countryId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_COUNTRY_NAME;
			} else {
				sql = RegCommonSQL.GET_COUNTRY_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			Countryname = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getCountryName-RegCommonDAO"
					+ exception);

		} finally {
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
	 * 
	 * @param String
	 * @return String
	 * @author SHREERAJ
	 */
	public String getAdjFlag(String regID) throws Exception {

		// int regTxnIdSeq = 0;
		String Countryname = "";

		try {
			dbUtility = new DBUtility();
			String[] param = { regID };
			sql = RegCommonSQL.GET_ADJU_FLAG;
			dbUtility.createPreparedStatement(sql);
			Countryname = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getAdjFlag-RegCommonDAO"
					+ exception);

		} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getStateName(String stateId, String languageLocale)
			throws Exception {

		// int regTxnIdSeq = 0;
		String statename = "";

		try {
			dbUtility = new DBUtility();
			String[] param = { stateId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_STATE_NAME;
			} else {
				sql = RegCommonSQL.GET_STATE_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			statename = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getStateName-RegCommonDAO"
					+ exception);

		} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getDistrictName(String distId, String languageLocale)
			throws Exception {

		// int regTxnIdSeq = 0;
		String distname = "";

		try {
			dbUtility = new DBUtility();
			/*
			 * dbUtility.createStatement();
			 * sql=RegCommonSQL.GET_DISTRICT_NAME+"'"+distId+"'"; distname =
			 * dbUtility.executeQry(sql);
			 */

			String[] param = { distId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_DISTRICT_NAME;
			} else {
				sql = RegCommonSQL.GET_DISTRICT_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			distname = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getDistrictName-RegCommonDAO"
					+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return distname;

	}
	
	public String getTehsilName1(String tehsilId, String languageLocale)
	throws Exception {

// int regTxnIdSeq = 0;
String distname = "";

try {
	dbUtility = new DBUtility();
	/*
	 * dbUtility.createStatement();
	 * sql=RegCommonSQL.GET_DISTRICT_NAME+"'"+distId+"'"; distname =
	 * dbUtility.executeQry(sql);
	 */

	String[] param = { tehsilId };
	if (languageLocale
			.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
		sql = RegCommonSQL.GET_TEHSIL1_NAME;
	} else {
		sql = RegCommonSQL.GET_TEHSIL1_NAME_HINDI;
	}
	dbUtility.createPreparedStatement(sql);
	distname = dbUtility.executeQry(param);

} catch (Exception exception) {

	System.out.println("Exception in getDistrictName-RegCommonDAO"
			+ exception);

} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getValuationId(String appId) throws Exception {

		String valuationId = "";

		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			sql = RegCommonSQL.GET_REG_INIT_PROP_VAL_ID;
			dbUtility.createPreparedStatement(sql);
			valuationId = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getValuationId-RegCommonDAO"
					+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return valuationId;

	}

	/**
	 * getPropDetlsForDashboard for getting PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropDetlsForDashboard(String appId, String propId)
			throws Exception {
		ArrayList propDetls = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId, propId };
			String query = RegCommonSQL.GET_PROP_DETLS_DASHBOARD;
			dbUtility.createPreparedStatement(query);
			// propDetls = dbUtility.executeQueryCustomArrayList(param);
			propDetls = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDashboard" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return propDetls;

	}

	/**
	 * getPoaForDashboard for getting PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public ArrayList getPoaForDashboard(String appId) throws Exception {
	 * ArrayList poaDetls=new ArrayList(); try { dbUtility = new DBUtility();
	 * String[] param={appId}; String
	 * query=RegCommonSQL.GET_PARTY_ROLE_ID_DASHBOARD;
	 * dbUtility.createPreparedStatement(query); poaDetls =
	 * dbUtility.executeQuery(param);
	 * 
	 * } catch (Exception exception) {
	 * logger.debug("Exception in getPropDetlsForDashboard" + exception); }
	 * return poaDetls;
	 * 
	 * }
	 */
	/**
	 * getPoaListFromDb for getting poa list from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public ArrayList getPoaListFromDb(String appId) throws Exception {
	 * ArrayList poaList=new ArrayList(); try { dbUtility = new DBUtility();
	 * dbUtility.createStatement(); String
	 * query=RegCommonSQL.GET_POA_LIST_DASHBOARD+"'"+appId+"'"; poaList =
	 * dbUtility.executeQuery(query);
	 * 
	 * } catch (Exception exception) {
	 * logger.debug("Exception in getPoaListFromDb" + exception); } return
	 * poaList;
	 * 
	 * }
	 */
	/**
	 * getOwnerListFromDb for getting owner list from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public ArrayList getOwnerListFromDb(String appId) throws Exception {
	 * ArrayList ownerList=new ArrayList(); try { dbUtility = new DBUtility();
	 * dbUtility.createStatement(); String
	 * query=RegCommonSQL.GET_OWNER_LIST_DASHBOARD+"'"+appId+"'"; ownerList =
	 * dbUtility.executeQuery(query);
	 * 
	 * } catch (Exception exception) {
	 * logger.debug("Exception in getOwnerListFromDb" + exception); } return
	 * ownerList;
	 * 
	 * }
	 */
	/**
	 * for getting property id corresponding to registration app id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropertyId(String appId) throws Exception {

		String propertyId = "";

		try {
			dbUtility = new DBUtility();
			// dbUtility.createStatement();
			String param[] = { appId, appId };
			sql = RegCommonSQL.GET_REG_INIT_PROPERTY_ID;
			dbUtility.createPreparedStatement(sql);
			propertyId = dbUtility.executeQry(param);

			logger.debug("Property Id from database----->" + propertyId);
		} catch (Exception exception) {

			System.out.println("Exception in getValuationId-RegCommonDAO"
					+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return propertyId;

	}

	/**
	 * getPropertyIdMarketVal for getting PROPERTY ID AND MARKET VALUE details
	 * from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdMarketVal(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_PROPERTY_ID_MARKET_VALUE;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropertyIdMarketVal" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getTransPartyDets for getting transacting party details corresponding to
	 * a PROPERTY ID from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDets(String propId, String appId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			String param[] = new String[2];
			param[0] = appId;
			param[1] = propId;
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_TRANSACTING_PARTY_DETS;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyDets" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	public ArrayList getTransPartyDetsForPaticular(String particularId,
			String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			String param[] = new String[2];
			param[0] = appId;
			param[1] = particularId;
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_TRANSACTING_PARTY_DETS_WITH_PARTICULAR;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyDets" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getApplicantPartyDets for getting APPLICANT PARTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantPartyDets(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_APPLICANT_PARTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getApplicantPartyDets" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	// FOLLOWING METHODS FOR INTEGRATING NEW PAYMENT MODALITY
	/**
	 * for getting payment flag corresponding to registration app id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public ArrayList getPaymentFlag(String appId,String funId) throws Exception {

		ArrayList paymentList = new ArrayList();
		String sourceModFlag="I";
		
		if(funId.equalsIgnoreCase("3")){
			sourceModFlag="A";
		}

		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String param[] = { appId, appId, funId, sourceModFlag };
			sql = RegCommonSQL.GET_REG_INIT_PAYMENT_FLAG;
			dbUtility.createPreparedStatement(sql);
			paymentList = dbUtility.executeQuery(param);

			logger.debug("payment flag from database----->" + paymentList);
		} catch (Exception exception) {

			System.out.println("Exception in getPaymentFlag-RegCommonDAO"
					+ exception);

		} finally {
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
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertPaymentDetails(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_REG_INIT_PAYMENT_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {

				dbUtility.commit();
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	
	
	public boolean insertPaymentDetailsEfile(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_EFILE_PAYMENT_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {

				dbUtility.commit();
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * getPartyDetsForViewConfirm for getting APPLICANT PARTY details from db.
	 * 
	 * @param String
	 *            , String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyDetsForViewConfirm(String appId, String partyId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {

			String param[] = { appId.trim(), partyId.trim() };
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_PARTY_DETS;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getApplicantPartyDets" + exception);
			logger.debug(exception);
			logger.debug(exception.getMessage(),exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for getting photo id proof name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPhotoIdProofName(String proofId, String languageLocale)
			throws Exception {
		String proofname = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { proofId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_PHOTO_PROOF_NAME;
			} else {
				sql = RegCommonSQL.GET_PHOTO_PROOF_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			proofname = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getPhotoIdProofName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return proofname;
	}

	/**
	 * getPropertyDetsForViewConfirm for getting APPLICANT PARTY details from
	 * db.
	 * 
	 * @param String
	 *            , String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public ArrayList getPropertyDetsForViewConfirm(String appId, String
	 * partyId) throws Exception { ArrayList list=new ArrayList(); try {
	 * 
	 * String param[]={appId.trim(),partyId.trim()}; dbUtility = new
	 * DBUtility(); String query=RegCommonSQL.GET_PARTY_DETS;
	 * dbUtility.createPreparedStatement(query);
	 * 
	 * list = dbUtility.executeQuery(param);
	 * 
	 * } catch (Exception exception) {
	 * logger.debug("Exception in getApplicantPartyDets" + exception); } return
	 * list;
	 * 
	 * }
	 */
	/**
	 * for getting valuation id corresponding to registration app id and
	 * property Id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropValuationId(String appId, String propId)
			throws Exception {

		String valuationId = "";

		try {
			String param[] = { appId, propId };
			dbUtility = new DBUtility();
			sql = RegCommonSQL.GET_REG_INIT_PROP_VALUATION_ID;
			dbUtility.createPreparedStatement(sql);

			valuationId = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getPropValuationId-RegCommonDAO"
					+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return valuationId;

	}

	/**
	 * getOtherPropDetsForViewConfirm for getting other property details from
	 * db.
	 * 
	 * @param String
	 *            , String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getOtherPropDetsForViewConfirm(String appId, String propId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {

			String param[] = { appId.trim(), propId.trim() };
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_REG_INIT_OTHER_PROP_DETS;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getOtherPropDetsForViewConfirm"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDutyDetls(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_REG_INIT_DUTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for getting reg init app id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	/*
	 * public String getRegInitAppId(String userId) throws Exception { String
	 * appId=""; try { dbUtility = new DBUtility(); dbUtility.createStatement();
	 * sql=RegCommonSQL.GET_PHOTO_PROOF_NAME+"'"+userId+"'"; appId =
	 * dbUtility.executeQry(sql); } catch (Exception exception) {
	 * System.out.println("Exception in getPhotoIdProofName-RegCommonDAO" +
	 * exception); }finally{ try { dbUtility.closeConnection(); } catch
	 * (Exception e) { logger.error("RegCommonDAO in dao start" +
	 * e.getStackTrace()); } } return appId; }
	 */
	/**
	 * for inserting multiple property details in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	/*
	 * public boolean insrtMultiplePropDetls(String[] propDetls) { boolean
	 * boo=false; try { dbUtility = new DBUtility(); sql =
	 * RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
	 * dbUtility.createPreparedStatement(sql);
	 * boo=dbUtility.executeUpdate(propDetls); if(boo){ dbUtility.commit();
	 * 
	 * } else dbUtility.rollback(); }catch (Exception e) { e.printStackTrace();
	 * }finally{ try { dbUtility.closeConnection(); } catch (Exception e) {
	 * logger.error("RegCommonDAO in dao start" + e.getStackTrace()); } } return
	 * boo; }
	 */
	public boolean insrtMultiplePropDetls(String[] propDetls,
			String[][][] mapTransPartyPropDetls, String[] regStatus,
			ArrayList paramList1, int agriFlag) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			CommonDTO row_list;
			String[] param1 = new String[19];
			String[] param2 = new String[4];

			if (agriFlag == 0) {
				sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(propDetls);
				dbUtility.commit();
				logger.debug("Insertion commited in DAO line no 4334 " );
			} else {
				sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
				dbUtility.createPreparedStatement(sql);

				for (int i = 0; i < paramList1.size(); i++) {

					row_list = (CommonDTO) paramList1.get(i);
					param1[0] = row_list.getPropertyId();
					param1[1] = row_list.getRegTxnId();
					param1[2] = row_list.getMarketValue();
					// param1[2]=""; // "" is passed because final market value
					// is not being stored in db from pv
					param1[3] = row_list.getAreaTypeId();
					param1[4] = row_list.getGovBodyId();
					param1[5] = row_list.getPropTypeId();
					param1[6] = row_list.getL1TypeId();
					param1[7] = row_list.getL2TypeId();
					param1[8] = row_list.getAreaUnitId();
					param1[9] = row_list.getArea();
					param1[10] = row_list.getDistId();
					param1[11] = row_list.getTehsilId();
					param1[12] = row_list.getWardId();
					param1[13] = row_list.getMohalaId();
					param1[14] = row_list.getUserId();
					param1[15] = row_list.getValuationId();
					param1[16] = "N"; // PROP_TXN_COMPL_FLAG
					param1[17] = row_list.getMarketValue(); // INITIAL_MARKET_VALUE
					// param1[17]=""; //INITIAL_MARKET_VALUE
					param1[18] = row_list.getSysMv(); // SYSTEM MARKET_VALUE

					boo = dbUtility.executeUpdate(param1); // loop
					dbUtility.commit();
					logger.debug("Insertion commited in DAO line no 4367" );
					if (!boo)
						break;

				}

			}

			if (boo) {

				if (mapTransPartyPropDetls != null) {

					for (int i = 0; i < mapTransPartyPropDetls.length; i++) {

						for (int j = 0; j < mapTransPartyPropDetls[i].length; j++) {
							sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
							dbUtility.createPreparedStatement(sql);
							boo = dbUtility
									.executeUpdate(mapTransPartyPropDetls[i][j]);
							dbUtility.commit();
							if (!boo) {
								break;
							}
						}

					}
				}/*
				 * else{
				 * 
				 * //BELOW CODE FOR INSERTION OF PROPERTY AND TRANSACTING PARTY
				 * MAP DETAILS sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
				 * dbUtility.createPreparedStatement(sql);
				 * 
				 * for(int i=0;i<paramList2.size();i++){
				 * 
				 * row_list=(CommonDTO)paramList2.get(i);
				 * 
				 * param2[0]=row_list.getRegTxnId();
				 * param2[1]=row_list.getPropertyId();
				 * param2[2]=row_list.getPartyTxnId();
				 * param2[3]=row_list.getUserId();
				 * 
				 * 
				 * boo=dbUtility.executeUpdate(param2); //loop
				 * 
				 * if(!boo) break;
				 * 
				 * }
				 * 
				 * 
				 * 
				 * }
				 */

				if (boo) {

					sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(regStatus);
					if (boo) {
						dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else {
					dbUtility.rollback();
					throw new Exception();
				}

			} else {
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			boo = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception while rollback in RegCommonDAO:insrtMultiplePropDetls");
			}
			logger.debug(e.getMessage(),e);
		} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantRoleId(String appId) throws Exception {
		String roleId = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };

			sql = RegCommonSQL.GET_APPLICANT_ROLE_ID;
			dbUtility.createPreparedStatement(sql);
			roleId = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getApplicantRoleId-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return roleId;
	}

	/**
	 * getShareOfPropList for getting other property details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public ArrayList getShareOfPropList(String appId) throws Exception {
	 * ArrayList list=new ArrayList(); try {
	 * 
	 * String param[]={appId.trim(),appId.trim()}; dbUtility = new DBUtility();
	 * String query=RegCommonSQL.GET_SHARE_OF_PROPERTY_LIST;
	 * dbUtility.createPreparedStatement(query);
	 * 
	 * list = dbUtility.executeQuery(param);
	 * 
	 * } catch (Exception exception) {
	 * logger.debug("Exception in getShareOfPropList" + exception); } return
	 * list;
	 * 
	 * }
	 */
	public ArrayList getShareOfPropList(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {

			// String param[]={appId.trim(),appId.trim()};
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_SHARE_OF_PROPERTY_LIST;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getShareOfPropList" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getApplicantShareHolders for getting other property details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public ArrayList getApplicantShareHolders(String appId) throws Exception
	 * { ArrayList list=new ArrayList(); try {
	 * 
	 * String param[]={appId.trim(),appId.trim()}; dbUtility = new DBUtility();
	 * String query=RegCommonSQL.GET_APPLICANT_SHARE_HOLDERS;
	 * dbUtility.createPreparedStatement(query);
	 * 
	 * list = dbUtility.executeQuery(param);
	 * 
	 * } catch (Exception exception) {
	 * logger.debug("Exception in getApplicantShareHolders" + exception); }
	 * return list;
	 * 
	 * }
	 */
	public ArrayList getApplicantShareHolders(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {

			// String param[]={appId.trim(),appId.trim()};
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_APPLICANT_SHARE_HOLDERS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getApplicantShareHolders" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for getting role name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getRoleName(String roleId, String languageLocale)
			throws Exception {
		String roleName = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { roleId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_PARTY_ROLE_NAME;
			} else {
				sql = RegCommonSQL.GET_PARTY_ROLE_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			roleName = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getRoleName-RegCommonDAO"
					+ exception);
		} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getTransactingPartyIdSeq() throws Exception {

		String seqId = "0";
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			seqId = dbUtility
					.executeQry(RegCommonSQL.GET_REG_TRANS_PARTY_ID_SEQ);
		} catch (Exception exception) {
			System.out
					.println("Exception in getTransactingPartyIdSeq-RegCommonDAO"
							+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return seqId;
	}
	
	public String getConsenterIdSeq() throws Exception {

		String seqId = "0";
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			seqId = dbUtility
					.executeQry(RegCommonSQL.GET_REG_CONSENTER_ID_SEQ);
		} catch (Exception exception) {
			System.out
					.println("Exception in getConsenterIdSeq-RegCommonDAO"
							+ exception);
		} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getTransPartyPropertyMapIdSeq() throws Exception {

		String seqId = "0";
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			seqId = dbUtility
					.executeQry(RegCommonSQL.GET_REG_TRANS_PARTY_PROP_MAP_ID_SEQ);
		} catch (Exception exception) {
			System.out
					.println("Exception in getTransPartyPropertyMapIdSeq-RegCommonDAO"
							+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return seqId;
	}

	/**
	 * getOwnerDetls for getting owner details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getOwnerDetls(String ownerId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { ownerId };
			String query = RegCommonSQL.GET_OWNER_DETAILS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getOwnerDetls" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getPropDetlsForDutyCalc for getting property details from db for duty
	 * calculation.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropDetlsForDutyCalc(String valuationId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { valuationId };
			String query = RegCommonSQL.GET_PROPERTY_DETAILS_FOR_DUTY_CALC;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDutyCalc" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getPaymentTxnId for getting payment transaction id from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPaymentTxnId(String appId,String paymentType) throws Exception {
		ArrayList list = new ArrayList();
		
		String sourceModFlag="I";
		
		if(paymentType.equalsIgnoreCase("3")){
			sourceModFlag="A";
		}
		
		
		String[] param = { appId, appId,paymentType,sourceModFlag };
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.GET_REG_INIT_PAYMENT_TXN_ID;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPaymentTxnId" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for inserting registration initiation E STAMP CODE details in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertEStampCode(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_REG_INIT_ESTAMP_CODE;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {

				dbUtility.commit();
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
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
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean updateTransactionStatus(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS_WITH_ADDRESS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {

				dbUtility.commit();
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * getDeedInstId for getting deed and instrument ids from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDeedInstId(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			// String query=RegCommonSQL.GET_REG_INIT_DEED_INST_ID;
			String query = RegCommonSQL.GET_REG_INIT_DEED_INST_ID_NEW;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getDeedInstId" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for inserting registration initiation stamp duties details in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertStampDuties(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_DUTIES_DETAILS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {

				dbUtility.commit();
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	// for building floor details
	/**
	 * getDeedInstId for getting deed and instrument ids from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getGuildingFloorDetails(String valId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { valId };
			String query = RegCommonSQL.GET_BUILDING_FLOOR_DETAILS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getGuildingFloorDetails" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}
	
	
	

	/**
	 * getDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDutyDetlsForConfirmation(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_REG_INIT_DUTY_DETS_FOR_CONFIRMATION;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger
					.debug("Exception in getDutyDetlsForConfirmation"
							+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for getting deed name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getDeedName(String deedId, String languageLocale)
			throws Exception {
		String deedName = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { deedId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_DEED_NAME;
			} else {
				sql = RegCommonSQL.GET_DEED_NAME_HI;
			}
			dbUtility.createPreparedStatement(sql);
			deedName = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getDeedName-RegCommonDAO"
					+ exception);
		} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getInstrumentName(String instrumentId, String languageLocale)
			throws Exception {
		String instName = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { instrumentId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_INST_NAME;
			} else {
				sql = RegCommonSQL.GET_INST_NAME_HI;
			}
			dbUtility.createPreparedStatement(sql);
			instName = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getInstrumentName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return instName;
	}
	
	public String getFamilyFlag(String dutyId)
	throws Exception {
		String flag = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { dutyId };
			
				sql = RegCommonSQL.GET_FAMILY_FLAG;
			
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getFamilyFlag-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return flag;
}

	/**
	 * for getting exemption name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getExemptionName(String exempId, String languageLocale)
			throws Exception {
		String exmpName = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { exempId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_EXMP_NAME;
			} else {
				sql = RegCommonSQL.GET_EXMP_NAME_HI;
			}
			dbUtility.createPreparedStatement(sql);
			exmpName = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getExemptionName-RegCommonDAO"
					+ exception);
		} finally {
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
		// ArrayList subList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { tempId };

			sql = RegCommonSQL.GET_ESTAMP_DETAILS;
			dbUtility.createPreparedStatement(sql);
			mainList = dbUtility.executeQuery(param);
			/*
			 * subList = dbUtility.executeQuery(sql); if(subList.size()!=0) {
			 * 
			 * for(int i =0;i<subList.size();i++) { ArrayList list = new
			 * ArrayList(); list = (ArrayList) subList.get(i); RegCommonForm
			 * form = new RegCommonForm();
			 * form.setEstampCode((String)list.get(0));
			 * form.setEstampAmt((String)list.get(1));
			 * form.setEstampDateTime((String)list.get(2)); mainList.add(form);
			 * } }
			 */
		} catch (Exception e) {
			logger.error("error in getEstampDet" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection in getEstampDet"
						+ e.getStackTrace());
			}
		}

		return mainList;
	}

	/**
	 * getAdjudicationStatus for getting adjudication id and status from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAdjudicationStatus(String adjuId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { adjuId };

			String query = RegCommonSQL.GET_REG_INIT_ADJUDICATION_STATUS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAdjudicationStatus" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for updating reg init id corresponding to adjudication id in database
	 * 
	 * @param String
	 *            regInitId,String adjuId
	 * @param boolean
	 * 
	 */
	public boolean updateAdjudicationRecords(String regInitId, String adjuId,
			String userId) {
		boolean boo = false;
		String[] param = { regInitId, userId, adjuId };
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_TRANS_PARTY_TABLE;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				sql = RegCommonSQL.UPDATE_STAMP_DUTY_TABLE;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(param);
				if (boo) {
					sql = RegCommonSQL.UPDATE_PROP_DETLS_TABLE;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(param);
					if (boo) {
						sql = RegCommonSQL.UPDATE_DEED_DETLS_TABLE;
						dbUtility.createPreparedStatement(sql);
						boo = dbUtility.executeUpdate(param);
						if (boo) {
							sql = RegCommonSQL.UPDATE_PROP_TRANS_MAP_TABLE;
							dbUtility.createPreparedStatement(sql);
							boo = dbUtility.executeUpdate(param);
							dbUtility.commit();
							if (boo) {

								dbUtility.commit();
							} else
								dbUtility.rollback();

						} else
							dbUtility.rollback();

					} else
						dbUtility.rollback();

				} else
					dbUtility.rollback();

			} else
				dbUtility.rollback();

		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);
			}
		}

		return boo;
	}

	/**
	 * getPropertyIdMarketValAdju for getting PROPERTY ID AND MARKET VALUE
	 * details for adjudicated applications from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdMarketValAdju(String adjuId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { adjuId };
			String query = RegCommonSQL.GET_PROPERTY_ID_MARKET_VALUE_ADJU;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropertyIdMarketValAdju" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getAdjudicatedDutyDetls for getting adjudicated duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAdjudicatedDutyDetls(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_ADJUDICATED_DUTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAdjudicatedDutyDetls" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for getting applicant district id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantDistrict(String appId) throws Exception {
		String distId = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			sql = RegCommonSQL.GET_APPLICANT_DIST_ID;
			dbUtility.createPreparedStatement(sql);
			distId = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getApplicantDistrict-RegCommonDAO"
					+ exception);
		} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getEStampPurpose(String appId) throws Exception {
		String purpose = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			sql = RegCommonSQL.GET_ESTAMP_PURPOSE;
			dbUtility.createPreparedStatement(sql);
			purpose = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getEStampPurpose-RegCommonDAO"
					+ exception);
		} finally {
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
	 * 
	 * @param String
	 *            regInitId,String purpose
	 * @param boolean
	 * 
	 */
	public boolean updateEStampPurpose(String regInitId, String purpose) {
		boolean boo = false;
		String[] param = { purpose, regInitId };
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_ESTAMP_PURPOSE;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();

			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);
			}
		}

		return boo;
	}

	/**
	 * getAllValuationIdsExchangeDeed for getting ALL VALUATION IDS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllValuationIdsExchangeDeed(String finalValId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { finalValId };
			// String query=RegCommonSQL.GET_ALL_VALUATION_IDS_EXCHANGE;

			String query = RegCommonSQL.GET_ALL_VALUATION_IDS; // as per new PV-
																// common for
																// agri land and
																// exchange deed

			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getAllValuationIds for getting ALL VALUATION IDS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllValuationIds(String dutyTxnId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { dutyTxnId };
			// String query=RegCommonSQL.GET_ALL_VALUATION_IDS_EXCHANGE;

			String query = RegCommonSQL.GET_ALL_VALUATION_IDS_NEW; // as per new
																	// PV-
																	// common
																	// for agri
																	// land and
																	// exchange
																	// deed

			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAllValuationIds" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getAllPropDetlsExchangeDeed for getting ALL PROPERTY DETAILS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPropDetlsExchangeDeed(String valId, String dutyTxnId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { valId, dutyTxnId };
			// String query=RegCommonSQL.GET_ALL_PROPERTY_DETLS_EXCHANGE;

			// String query=RegCommonSQL.GET_ALL_PROPERTY_DETLS; // as per new
			// PV- common for agri land and exchange deed
			String query = RegCommonSQL.GET_ALL_PROPERTY_DETLS_NEW;

			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger
					.debug("Exception in getAllPropDetlsExchangeDeed"
							+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}
	
	public ArrayList getAllPropDetlsNPVDeed(String valId)
	throws Exception {
ArrayList list = new ArrayList();
try {
	dbUtility = new DBUtility();
	String[] param = { valId};
	// String query=RegCommonSQL.GET_ALL_PROPERTY_DETLS_EXCHANGE;

	// String query=RegCommonSQL.GET_ALL_PROPERTY_DETLS; // as per new
	// PV- common for agri land and exchange deed
	String query = RegCommonSQL.GET_ALL_PROPERTY_DETLS_NEW_NPV_DEED;

	dbUtility.createPreparedStatement(query);
	list = dbUtility.executeQuery(param);
	
	if(list!=null){
		
		ArrayList rowList=(ArrayList)list.get(0);
		
		if(rowList.get(3).toString().equalsIgnoreCase("3")){
			
			
				
			dbUtility.createPreparedStatement(RegCommonSQL.GET_VALUATION_ID_AGRI);
        	String arr2[]=new String[1];
        	arr2[0]=valId;
        	valId=dbUtility.executeQry(arr2);
        	String[] param2 = { valId};
        	
        	
        	dbUtility.createPreparedStatement(query);
        	list = dbUtility.executeQuery(param2);
			
			
		}
		
		
	}

} catch (Exception exception) {
	logger
			.debug("Exception in getAllPropDetlsNPVDeed"
					+ exception);
} finally {
	try {

		dbUtility.closeConnection();
	} catch (Exception ex) {
		logger.debug(ex);
		// ex.printStackTrace();
	}
}
return list;

}

	/**
	 * insrtApplcntTransPartyPropDetlsExchange for inserting applicant details
	 * in db.(EXCHANGE DEED)
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 * @throws Exception
	 */
	public boolean insrtApplcntTransPartyPropDetlsExchange(String[] party,
			String[] deed, ArrayList propDetls, ArrayList propTransDets,
			HashMap ownerDetailsMap, String exemp, String[] statusParam,
			String[] extraDetls) throws Exception {
		boolean boo = true;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			CommonDTO row_list;
			String[] param1 = new String[19];
			String[] param2 = new String[4];
		
			
			if (deed!=null && deed.length == 10) {//main table insertion brought at top on 6feb2015 for db constraints correction 
				sql = RegCommonSQL.INSERT_REG_DEED_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(deed);
			}
			
			
			if(boo)
			{
			if(ownerDetailsMap!=null && ownerDetailsMap.size()>0)
			{
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				
				String ownerDetails[] = new String[21];
				Collection mapCol=ownerDetailsMap.values();
                Object[] l2=mapCol.toArray();
                RegCommonDTO ownerMap =new RegCommonDTO();
				for(int k=0;k<l2.length;k++)
				{
					ownerMap=(RegCommonDTO)l2[k];
				
					ownerDetails[0] = party[0]; 							// reg txn id
					if (ownerMap.getOwnerOgrNameTrns().equals("-") 
							|| ownerMap.getOwnerOgrNameTrns().equals("")
							|| ownerMap.getOwnerOgrNameTrns().equals(null)) {
						ownerDetails[1] = "2"; 								// individual party type
						ownerDetails[2] = ownerMap.getOwnerNameTrns(); 		// first name
						ownerDetails[12] = ""; 								// authorized person name
					} else {
						ownerDetails[1] = "1"; 								// organization party type
						ownerDetails[2] = ""; 								// first name
						ownerDetails[12] = ownerMap.getOwnerNameTrns(); 	// authorized person name
					}
					ownerDetails[3] = ownerMap.getOwnerGendarValTrns(); 	// gender
					ownerDetails[4] = ownerMap.getOwnerAgeTrns(); 			// DOB to age
					ownerDetails[5] = ownerMap.getOwnerNationalityTrns(); 	// nationality
					ownerDetails[6] = ownerMap.getOwnerAddressTrns(); 		// address
					ownerDetails[7] = ownerMap.getOwnerPhnoTrns(); 			// phone number
					if (ownerMap.getOwnerEmailIDTrns() != null
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("-")) {
						ownerDetails[8] = ownerMap.getOwnerEmailIDTrns(); 	// email id
					} else {
						ownerDetails[8] = "";
					}
					ownerDetails[9] = ownerMap.getOwnerListIDTrns(); 		// photo proof type id
					
					
					if (ownerMap.getOwnerIdnoTrns() != null
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("-")) {
						ownerDetails[10] = ownerMap.getOwnerIdnoTrns(); 	// photo proof number
					} else {
						ownerDetails[10] = "";
					}
					
					
					if (ownerMap.getOwnerOgrNameTrns() != null
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("-")) {
						ownerDetails[11] = ownerMap.getOwnerOgrNameTrns(); 	// organization name
					} else {
						ownerDetails[11] = "";
					}
					ownerDetails[13] = ownerMap.getOwnerTxnId(); 			// party transaction
					ownerDetails[14] = "O"; 								// is applicant O-Owner
					ownerDetails[15] = ""; 									// share of property
					ownerDetails[16] = Integer
							.toString(RegInitConstant.ROLE_OWNER_SELF); 	// party role
					ownerDetails[17] = party[30]; 							// CREATED BY
					
					
					if (ownerMap.getAddressOwnerOutMpTrns() != null
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("")
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("null")
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("-")) {
						ownerDetails[18] = ownerMap.getAddressOwnerOutMpTrns(); 	// address of owner outside mp
					} else {
						ownerDetails[18] = "";
					}
					
					ownerDetails[19] = party[23];							// parent id
					boo = dbUtility.executeUpdate(ownerDetails);
				
					if(!boo)
						break;
					
				
				}
				
				
				if (boo) {
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(party);
				} else
				{
					dbUtility.rollback();
					throw new Exception();
				}
			}else{
				sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW; //  anuj change query
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(party);
				dbUtility.commit();
			}
		}else{
			dbUtility.rollback();
			throw new Exception();
			
		}
			//below code for certificate of sale value not inserting into reg property table 
			/*if(propTransDets!=null){
				sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
				dbUtility.createPreparedStatement(sql);

				for (int i = 0; i < propTransDets.size(); i++) {

					row_list = (CommonDTO) propTransDets.get(i);
					param1[0] = row_list.getPropertyId();
					param1[1] = row_list.getRegTxnId();
					param1[2] = row_list.getMarketValue();
					// param1[2]=""; // "" is passed because final
					// market value is not being stored in db from pv
					param1[3] = row_list.getAreaTypeId();
					param1[4] = row_list.getGovBodyId();
					param1[5] = row_list.getPropTypeId();
					param1[6] = row_list.getL1TypeId();
					param1[7] = row_list.getL2TypeId();
					param1[8] = row_list.getAreaUnitId();
					param1[9] = row_list.getArea();
					param1[10] = row_list.getDistId();
					param1[11] = row_list.getTehsilId();
					param1[12] = row_list.getWardId();
					param1[13] = row_list.getMohalaId();
					param1[14] = row_list.getUserId();
					param1[15] = row_list.getValuationId();
					param1[16] = "N"; // PROP_TXN_COMPL_FLAG
					param1[17] = row_list.getMarketValue(); // INITIAL_MARKET_VALUE
					// param1[17]=""; //INITIAL_MARKET_VALUE
					param1[18] = row_list.getSysMv(); // SYSTEM
														// MARKET_VALUE

					boo = dbUtility.executeUpdate(param1); // loop

			}
}*/
			
			if (boo) {

				if (deed!=null && deed.length == 10) {
					//sql = RegCommonSQL.INSERT_REG_DEED_DETLS;
					//dbUtility.createPreparedStatement(sql);
					//boo = dbUtility.executeUpdate(deed);
					if (boo) {

						// BELOW CODE FOR INSERTION OF PROPERTY DETAILS

						sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
						dbUtility.createPreparedStatement(sql);
if(propDetls.size()>0){
						for (int i = 0; i < propDetls.size(); i++) {

							row_list = (CommonDTO) propDetls.get(i);
							param1[0] = row_list.getPropertyId();
							param1[1] = row_list.getRegTxnId();
							param1[2] = row_list.getMarketValue();
							// param1[2]=""; // "" is passed because final
							// market value is not being stored in db from pv
							param1[3] = row_list.getAreaTypeId();
							param1[4] = row_list.getGovBodyId();
							param1[5] = row_list.getPropTypeId();
							param1[6] = row_list.getL1TypeId();
							param1[7] = row_list.getL2TypeId();
							param1[8] = row_list.getAreaUnitId();
							param1[9] = row_list.getArea();
							param1[10] = row_list.getDistId();
							param1[11] = row_list.getTehsilId();
							param1[12] = row_list.getWardId();
							param1[13] = row_list.getMohalaId();
							param1[14] = row_list.getUserId();
							param1[15] = row_list.getValuationId();
							param1[16] = "N"; // PROP_TXN_COMPL_FLAG
							param1[17] = row_list.getMarketValue(); // INITIAL_MARKET_VALUE
							// param1[17]=""; //INITIAL_MARKET_VALUE
							param1[18] = row_list.getSysMv(); // SYSTEM
																// MARKET_VALUE

							boo = dbUtility.executeUpdate(param1); // loop
							dbUtility.commit();
							logger.debug("Insertion commited in DAO line no 5823 " );
							if (!boo)
								break;

						}
}else{
	boo=false;
}

						// BELOW CODE FOR INSERTION OF EXEMPTIONS
						if (boo) {

							String[] exempParam = new String[3];
							if (exemp != null && !exemp.equalsIgnoreCase("")) {

								String[] exempArr = exemp.split("-");
								if (exempArr != null && exempArr.length > 0) {

									for (int i = 0; i < exempArr.length; i++) {

										exempParam[0] = party[0];
										exempParam[1] = exempArr[i].trim();
										exempParam[2] = party[30];

										sql = RegCommonSQL.INSERT_REG_EXEMPTION_DETLS;
										dbUtility.createPreparedStatement(sql);
										boo = dbUtility
												.executeUpdate(exempParam);

										if (!boo) {
											break;
										}

									}
								}
							}
						} else {
							dbUtility.rollback();
							throw new Exception();
						}

					} else
					{
						dbUtility.rollback();
						throw new Exception();
					}
				}
				if (boo) {
					// BELOW CODE FOR INSERTION OF PROPERTY AND TRANSACTING
					// PARTY MAP DETAILS
					if (propTransDets != null && propTransDets.size()>0) // insert mapping at this point
												// only for exchange, partition,
												// single property
					{
						sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
						dbUtility.createPreparedStatement(sql);
						if(propTransDets.size()>0){
						for (int i = 0; i < propTransDets.size(); i++) {

							row_list = (CommonDTO) propTransDets.get(i);

							param2[0] = row_list.getRegTxnId();
							param2[1] = row_list.getPropertyId();
							param2[2] = row_list.getPartyTxnId();
							param2[3] = row_list.getUserId();

							boo = dbUtility.executeUpdate(param2); // loop
							dbUtility.commit();
							if (!boo)
								break;

						}
						}else{
							boo=false;
						}

					}

					if (boo) {

						if (statusParam != null) { // insertion at NEXT_ACTION
							sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
							dbUtility.createPreparedStatement(sql);
							boo = dbUtility.executeUpdate(statusParam);
							if (boo) {

								/*
								 * String[] dutyParam={deed[6]}; //duty txn id
								 * sql = RegCommonSQL.GET_DUTIES_FROM_DUTY;
								 * dbUtility.createPreparedStatement(sql);
								 * ArrayList
								 * dutyList=dbUtility.executeQuery(dutyParam);
								 * ArrayList rowList; if(dutyList!=null &&
								 * dutyList.size()==1){
								 * 
								 * rowList=(ArrayList)dutyList.get(0); String[]
								 * dutiesParam=new String[9];
								 * 
								 * dutiesParam[0]=party[0]; // reg txn id
								 * dutiesParam[1]=rowList.get(0).toString();
								 * dutiesParam[2]=rowList.get(1).toString();
								 * dutiesParam[3]=rowList.get(2).toString();
								 * dutiesParam[4]=rowList.get(3).toString();
								 * dutiesParam[5]=rowList.get(4).toString();
								 * dutiesParam[6]=rowList.get(5).toString();
								 * dutiesParam[8]=party[30]; // user id
								 * 
								 * 
								 * 
								 * sql = RegCommonSQL.INSERT_DUTIES_DETAILS_NEW;
								 * dbUtility.createPreparedStatement(sql);
								 * boo=dbUtility.executeUpdate(dutiesParam);
								 * 
								 * if(boo){
								 */
								dbUtility.commit();
								/*
								 * }else{ dbUtility.rollback(); }
								 * 
								 * 
								 * }
								 */

							} else {
								dbUtility.rollback();
								throw new Exception();
							}
						} else {

							String[] dutyParam = { deed[6] }; // duty txn id
							sql = RegCommonSQL.GET_DUTIES_FROM_DUTY;
							dbUtility.createPreparedStatement(sql);
							ArrayList dutyList = dbUtility
									.executeQuery(dutyParam);
							ArrayList rowList;
							if (dutyList != null && dutyList.size() == 1) {

								rowList = (ArrayList) dutyList.get(0);
								String[] dutiesParam = new String[15];//15 for ews applied

								dutiesParam[0] = party[0]; // reg txn id
								dutiesParam[1] = rowList.get(0).toString();
								dutiesParam[2] = rowList.get(1).toString();
								dutiesParam[3] = rowList.get(2).toString();
								dutiesParam[4] = rowList.get(3).toString();
								dutiesParam[5] = rowList.get(4).toString();
								dutiesParam[6] = rowList.get(5).toString();
								dutiesParam[7] = party[30]; // user id

								dutiesParam[8] = rowList.get(8).toString();// duty
																			// already
																			// paid
								dutiesParam[9] = rowList.get(9).toString();// fee
																			// already
																			// paid

								if (rowList.get(6) != null
										&& !rowList.get(6).toString()
												.equalsIgnoreCase("")
										&& !rowList.get(6).toString()
												.equalsIgnoreCase("null")) {
									dutiesParam[10] = rowList.get(6).toString();
								} else {
									dutiesParam[10] = "0";
								}

								if (rowList.get(7) != null
										&& !rowList.get(7).toString()
												.equalsIgnoreCase("")
										&& !rowList.get(7).toString()
												.equalsIgnoreCase("null")) {
									dutiesParam[11] = rowList.get(7).toString();
								} else {
									dutiesParam[11] = "0";
								}

								dutiesParam[12] = rowList.get(10).toString();// duty
																				// exemp
								dutiesParam[13] = rowList.get(11).toString();// fee
																				// exemp
								dutiesParam[14] = rowList.get(12).toString();//ews flag

								sql = RegCommonSQL.INSERT_DUTIES_DETAILS_NEW;
								dbUtility.createPreparedStatement(sql);
								boo = dbUtility.executeUpdate(dutiesParam);

								if (boo) {

									// for inserting extra details if present
									if (extraDetls != null) { // for title deed
																// and trust
																// deed only.
										if (boo) {
											sql = RegCommonSQL.INSERT_REG_DEED_EXTRA_DTLS;
											dbUtility
													.createPreparedStatement(sql);
											boo = dbUtility
													.executeUpdate(extraDetls);
											if (!boo) {
												dbUtility.rollback();
											}

										} else {
											dbUtility.rollback();
											throw new Exception();
										}
									}

									if (boo) {
										dbUtility.commit();
									} else {
										dbUtility.rollback();
										throw new Exception();
									}

								} else {
									dbUtility.rollback();
									throw new Exception();
								}

							}

						}
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else
				{
					dbUtility.rollback();
					throw new Exception();
				}
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			try {
				dbUtility.rollback();
			} finally {
				logger.debug(e.getMessage(),e);
			}
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	
	public boolean insrtApplcntTransPartyPropDetlsMineral(String[] party,
			String[] deed, ArrayList propDetls, ArrayList propTransDets,
			HashMap ownerDetailsMap, String exemp, String[] statusParam,
			String[] extraDetls, RegCommonForm form) throws Exception {
		boolean boo = true;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			CommonDTO row_list;
			String[] param1 = new String[19];
			String[] param2 = new String[4];
		
			
			if (deed!=null && deed.length == 10) {//main table insertion brought at top on 6feb2015 for db constraints correction 
				sql = RegCommonSQL.INSERT_REG_DEED_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(deed);
			}
			
               if(boo){
				
				String[] mineral = new String[5];
				double miningMonths =form.getMiningDuration()*12;
				ArrayList mineralList =form.getMineralListSelected();
				
				if(!mineralList.isEmpty()){	
					for(int i = 0; i < mineralList.size(); i++){
						String lst = (String) mineralList.get(i);
						mineral[0] = form.getHidnRegTxnId();
						mineral[1] = Integer.toString(form.getDeedID()); // DEED_ID
						mineral[2] = Integer.toString(form.getInstID());
						mineral[3]  = Double.toString(miningMonths);
						mineral[4] =lst;
						sql = RegCommonSQL.MineralInsertion;  //mohit
						dbUtility.createPreparedStatement(sql);
						boo = dbUtility.executeUpdate(mineral);	
					
				}
				}
			}
			
			if(boo)
			{
			if(ownerDetailsMap!=null && ownerDetailsMap.size()>0)
			{
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				
				String ownerDetails[] = new String[21];
				Collection mapCol=ownerDetailsMap.values();
                Object[] l2=mapCol.toArray();
                RegCommonDTO ownerMap =new RegCommonDTO();
				for(int k=0;k<l2.length;k++)
				{
					ownerMap=(RegCommonDTO)l2[k];
				
					ownerDetails[0] = party[0]; 							// reg txn id
					if (ownerMap.getOwnerOgrNameTrns().equals("-") 
							|| ownerMap.getOwnerOgrNameTrns().equals("")
							|| ownerMap.getOwnerOgrNameTrns().equals(null)) {
						ownerDetails[1] = "2"; 								// individual party type
						ownerDetails[2] = ownerMap.getOwnerNameTrns(); 		// first name
						ownerDetails[12] = ""; 								// authorized person name
					} else {
						ownerDetails[1] = "1"; 								// organization party type
						ownerDetails[2] = ""; 								// first name
						ownerDetails[12] = ownerMap.getOwnerNameTrns(); 	// authorized person name
					}
					ownerDetails[3] = ownerMap.getOwnerGendarValTrns(); 	// gender
					ownerDetails[4] = ownerMap.getOwnerAgeTrns(); 			// DOB to age
					ownerDetails[5] = ownerMap.getOwnerNationalityTrns(); 	// nationality
					ownerDetails[6] = ownerMap.getOwnerAddressTrns(); 		// address
					ownerDetails[7] = ownerMap.getOwnerPhnoTrns(); 			// phone number
					if (ownerMap.getOwnerEmailIDTrns() != null
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("-")) {
						ownerDetails[8] = ownerMap.getOwnerEmailIDTrns(); 	// email id
					} else {
						ownerDetails[8] = "";
					}
					ownerDetails[9] = ownerMap.getOwnerListIDTrns(); 		// photo proof type id
					
					
					if (ownerMap.getOwnerIdnoTrns() != null
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("-")) {
						ownerDetails[10] = ownerMap.getOwnerIdnoTrns(); 	// photo proof number
					} else {
						ownerDetails[10] = "";
					}
					
					
					if (ownerMap.getOwnerOgrNameTrns() != null
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("-")) {
						ownerDetails[11] = ownerMap.getOwnerOgrNameTrns(); 	// organization name
					} else {
						ownerDetails[11] = "";
					}
					ownerDetails[13] = ownerMap.getOwnerTxnId(); 			// party transaction
					ownerDetails[14] = "O"; 								// is applicant O-Owner
					ownerDetails[15] = ""; 									// share of property
					ownerDetails[16] = Integer
							.toString(RegInitConstant.ROLE_OWNER_SELF); 	// party role
					ownerDetails[17] = party[30]; 							// CREATED BY
					
					
					if (ownerMap.getAddressOwnerOutMpTrns() != null
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("")
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("null")
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("-")) {
						ownerDetails[18] = ownerMap.getAddressOwnerOutMpTrns(); 	// address of owner outside mp
					} else {
						ownerDetails[18] = "";
					}
					
					ownerDetails[19] = party[23];							// parent id
					boo = dbUtility.executeUpdate(ownerDetails);
				
					if(!boo)
						break;
					
				
				}
				
				
				if (boo) {
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(party);
				} else
				{
					dbUtility.rollback();
					throw new Exception();
				}
			}else{
				sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW; //  anuj change query
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(party);
				dbUtility.commit();
			}
		}else{
			dbUtility.rollback();
			throw new Exception();
			
		}
			
			if (boo) {

				if (deed!=null && deed.length == 10) {
					//sql = RegCommonSQL.INSERT_REG_DEED_DETLS;
					//dbUtility.createPreparedStatement(sql);
					//boo = dbUtility.executeUpdate(deed);
					if (boo) {

						// BELOW CODE FOR INSERTION OF PROPERTY DETAILS

						sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
						dbUtility.createPreparedStatement(sql);
if(propDetls.size()>0){
						for (int i = 0; i < propDetls.size(); i++) {

							row_list = (CommonDTO) propDetls.get(i);
							param1[0] = row_list.getPropertyId();
							param1[1] = row_list.getRegTxnId();
							param1[2] = row_list.getMarketValue();
							// param1[2]=""; // "" is passed because final
							// market value is not being stored in db from pv
							param1[3] = row_list.getAreaTypeId();
							param1[4] = row_list.getGovBodyId();
							param1[5] = row_list.getPropTypeId();
							param1[6] = row_list.getL1TypeId();
							param1[7] = row_list.getL2TypeId();
							param1[8] = row_list.getAreaUnitId();
							param1[9] = row_list.getArea();
							param1[10] = row_list.getDistId();
							param1[11] = row_list.getTehsilId();
							param1[12] = row_list.getWardId();
							param1[13] = row_list.getMohalaId();
							param1[14] = row_list.getUserId();
							param1[15] = row_list.getValuationId();
							param1[16] = "N"; // PROP_TXN_COMPL_FLAG
							param1[17] = row_list.getMarketValue(); // INITIAL_MARKET_VALUE
							// param1[17]=""; //INITIAL_MARKET_VALUE
							param1[18] = row_list.getSysMv(); // SYSTEM
																// MARKET_VALUE

							boo = dbUtility.executeUpdate(param1); // loop
							dbUtility.commit();
							logger.debug("Insertion commited in DAO line no 6270 " );
							if (!boo)
								break;

						}
}else{
	boo=false;
}

						// BELOW CODE FOR INSERTION OF EXEMPTIONS
						if (boo) {

							String[] exempParam = new String[3];
							if (exemp != null && !exemp.equalsIgnoreCase("")) {

								String[] exempArr = exemp.split("-");
								if (exempArr != null && exempArr.length > 0) {

									for (int i = 0; i < exempArr.length; i++) {

										exempParam[0] = party[0];
										exempParam[1] = exempArr[i].trim();
										exempParam[2] = party[30];

										sql = RegCommonSQL.INSERT_REG_EXEMPTION_DETLS;
										dbUtility.createPreparedStatement(sql);
										boo = dbUtility
												.executeUpdate(exempParam);

										if (!boo) {
											break;
										}

									}
								}
							}
						} else {
							dbUtility.rollback();
							throw new Exception();
						}

					} else
					{
						dbUtility.rollback();
						throw new Exception();
					}
				}
				if (boo) {
					// BELOW CODE FOR INSERTION OF PROPERTY AND TRANSACTING
					// PARTY MAP DETAILS
					if (propTransDets != null && propTransDets.size()>0) // insert mapping at this point
												// only for exchange, partition,
												// single property
					{
						sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
						dbUtility.createPreparedStatement(sql);
						if(propTransDets.size()>0){
						for (int i = 0; i < propTransDets.size(); i++) {

							row_list = (CommonDTO) propTransDets.get(i);

							param2[0] = row_list.getRegTxnId();
							param2[1] = row_list.getPropertyId();
							param2[2] = row_list.getPartyTxnId();
							param2[3] = row_list.getUserId();

							boo = dbUtility.executeUpdate(param2); // loop
							dbUtility.commit();
							if (!boo)
								break;

						}
						}else{
							boo=false;
						}

					}

					if (boo) {

						if (statusParam != null) { // insertion at NEXT_ACTION
							sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
							dbUtility.createPreparedStatement(sql);
							boo = dbUtility.executeUpdate(statusParam);
							if (boo) {

								/*
								 * String[] dutyParam={deed[6]}; //duty txn id
								 * sql = RegCommonSQL.GET_DUTIES_FROM_DUTY;
								 * dbUtility.createPreparedStatement(sql);
								 * ArrayList
								 * dutyList=dbUtility.executeQuery(dutyParam);
								 * ArrayList rowList; if(dutyList!=null &&
								 * dutyList.size()==1){
								 * 
								 * rowList=(ArrayList)dutyList.get(0); String[]
								 * dutiesParam=new String[9];
								 * 
								 * dutiesParam[0]=party[0]; // reg txn id
								 * dutiesParam[1]=rowList.get(0).toString();
								 * dutiesParam[2]=rowList.get(1).toString();
								 * dutiesParam[3]=rowList.get(2).toString();
								 * dutiesParam[4]=rowList.get(3).toString();
								 * dutiesParam[5]=rowList.get(4).toString();
								 * dutiesParam[6]=rowList.get(5).toString();
								 * dutiesParam[8]=party[30]; // user id
								 * 
								 * 
								 * 
								 * sql = RegCommonSQL.INSERT_DUTIES_DETAILS_NEW;
								 * dbUtility.createPreparedStatement(sql);
								 * boo=dbUtility.executeUpdate(dutiesParam);
								 * 
								 * if(boo){
								 */
								dbUtility.commit();
								/*
								 * }else{ dbUtility.rollback(); }
								 * 
								 * 
								 * }
								 */

							} else {
								dbUtility.rollback();
								throw new Exception();
							}
						} else {

							String[] dutyParam = { deed[6] }; // duty txn id
							sql = RegCommonSQL.GET_DUTIES_FROM_DUTY;
							dbUtility.createPreparedStatement(sql);
							ArrayList dutyList = dbUtility
									.executeQuery(dutyParam);
							ArrayList rowList;
							if (dutyList != null && dutyList.size() == 1) {

								rowList = (ArrayList) dutyList.get(0);
								String[] dutiesParam = new String[15];//15 for ews applied

								dutiesParam[0] = party[0]; // reg txn id
								dutiesParam[1] = rowList.get(0).toString();
								dutiesParam[2] = rowList.get(1).toString();
								dutiesParam[3] = rowList.get(2).toString();
								dutiesParam[4] = rowList.get(3).toString();
								dutiesParam[5] = rowList.get(4).toString();
								dutiesParam[6] = rowList.get(5).toString();
								dutiesParam[7] = party[30]; // user id

								dutiesParam[8] = rowList.get(8).toString();// duty
																			// already
																			// paid
								dutiesParam[9] = rowList.get(9).toString();// fee
																			// already
																			// paid

								if (rowList.get(6) != null
										&& !rowList.get(6).toString()
												.equalsIgnoreCase("")
										&& !rowList.get(6).toString()
												.equalsIgnoreCase("null")) {
									dutiesParam[10] = rowList.get(6).toString();
								} else {
									dutiesParam[10] = "0";
								}

								if (rowList.get(7) != null
										&& !rowList.get(7).toString()
												.equalsIgnoreCase("")
										&& !rowList.get(7).toString()
												.equalsIgnoreCase("null")) {
									dutiesParam[11] = rowList.get(7).toString();
								} else {
									dutiesParam[11] = "0";
								}

								dutiesParam[12] = rowList.get(10).toString();// duty
																				// exemp
								dutiesParam[13] = rowList.get(11).toString();// fee
																				// exemp
								dutiesParam[14] = rowList.get(12).toString();//ews flag

								sql = RegCommonSQL.INSERT_DUTIES_DETAILS_NEW;
								dbUtility.createPreparedStatement(sql);
								boo = dbUtility.executeUpdate(dutiesParam);

								if (boo) {

									// for inserting extra details if present
									if (extraDetls != null) { // for title deed
																// and trust
																// deed only.
										if (boo) {
											sql = RegCommonSQL.INSERT_REG_DEED_EXTRA_DTLS;
											dbUtility
													.createPreparedStatement(sql);
											boo = dbUtility
													.executeUpdate(extraDetls);
											if (!boo) {
												dbUtility.rollback();
											}

										} else {
											dbUtility.rollback();
											throw new Exception();
										}
									}

									if (boo) {
										dbUtility.commit();
									} else {
										dbUtility.rollback();
										throw new Exception();
									}

								} else {
									dbUtility.rollback();
									throw new Exception();
								}

							}

						}
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else
				{
					dbUtility.rollback();
					throw new Exception();
				}
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			try {
				dbUtility.rollback();
			} finally {
				logger.debug(e.getMessage(),e);
			}
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}


	
	public boolean insrtPVPropDetlsExchange(String[] deed, ArrayList propDetls,
			String exemp, String[] statusParam) throws Exception {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			CommonDTO row_list;
			String[] param1 = new String[19];
			String[] param2 = new String[4];

			if (deed.length == 8) {
				sql = RegCommonSQL.INSERT_REG_DEED_DETLS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(deed);
				if (boo) {

					// BELOW CODE FOR INSERTION OF PROPERTY DETAILS

					sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
					dbUtility.createPreparedStatement(sql);

					for (int i = 0; i < propDetls.size(); i++) {

						row_list = (CommonDTO) propDetls.get(i);
						param1[0] = row_list.getPropertyId();
						param1[1] = row_list.getRegTxnId();
						param1[2] = row_list.getMarketValue();
						// param1[2]=""; // "" is passed because final market
						// value is not being stored in db from pv
						param1[3] = row_list.getAreaTypeId();
						param1[4] = row_list.getGovBodyId();
						param1[5] = row_list.getPropTypeId();
						param1[6] = row_list.getL1TypeId();
						param1[7] = row_list.getL2TypeId();
						param1[8] = row_list.getAreaUnitId();
						param1[9] = row_list.getArea();
						param1[10] = row_list.getDistId();
						param1[11] = row_list.getTehsilId();
						param1[12] = row_list.getWardId();
						param1[13] = row_list.getMohalaId();
						param1[14] = row_list.getUserId();
						param1[15] = row_list.getValuationId();
						param1[16] = "N"; // PROP_TXN_COMPL_FLAG
						param1[17] = row_list.getMarketValue(); // INITIAL_MARKET_VALUE
						// param1[17]=""; //INITIAL_MARKET_VALUE
						param1[18] = row_list.getSysMv(); // SYSTEM MARKET_VALUE

						boo = dbUtility.executeUpdate(param1); // loop
						dbUtility.commit();
						logger.debug("Insertion commited in DAO line no 6576 " );
						if (!boo)
							break;

					}

					// BELOW CODE FOR INSERTION OF EXEMPTIONS
					if (boo) {

						String[] exempParam = new String[3];
						if (exemp != null && !exemp.equalsIgnoreCase("")) {

							String[] exempArr = exemp.split(",");
							if (exempArr != null && exempArr.length > 0) {

								for (int i = 0; i < exempArr.length; i++) {

									exempParam[0] = param1[1];
									exempParam[1] = exempArr[i].trim();
									exempParam[2] = param1[14];

									sql = RegCommonSQL.INSERT_REG_EXEMPTION_DETLS;
									dbUtility.createPreparedStatement(sql);
									boo = dbUtility.executeUpdate(exempParam);

									if (!boo) {
										break;
									}

								}
							}
						}
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else
				{
					dbUtility.rollback();
					throw new Exception();
				}
			}
			if (boo) {

				if (statusParam != null) {
					sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(statusParam);
					if (boo) {
						dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}
				} else {
					dbUtility.commit();
				}

			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			try {
				dbUtility.rollback();
			} finally {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * getExchangeDeedDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getExchangeDeedDutyDetls(String refValId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { refValId };
			String query = RegCommonSQL.GET_REG_INIT_DUTY_DETS_EXCHANGE_DEED;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getExchangeDeedDutyDetls" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getRefValIdExchngDeed for getting reference valuation id of Exchange Deed
	 * from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String getRefValIdExchngDeed(String appId, int flag)
			throws Exception {
		String refValId = "";
		try {

			dbUtility = new DBUtility();
			if (flag == 0) { // 0 for exchange
				String[] param = { appId };
				sql = RegCommonSQL.GET_REF_VAL_ID_EXCHNG_DEED;
				dbUtility.createPreparedStatement(sql);
				refValId = dbUtility.executeQry(param);
			} else { // 1 for agri
				String[] param = { appId, appId };
				sql = RegCommonSQL.GET_REF_VAL_ID_AGRI;
				dbUtility.createPreparedStatement(sql);
				refValId = dbUtility.executeQry(param);
			}

		} catch (Exception exception) {
			System.out
					.println("Exception in getRefValIdExchngDeed-RegCommonDAO"
							+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return refValId;
	}

	/**
	 * getExchangeDeedFinalMV for getting FINAL MARKET VALUE of Exchange Deed
	 * from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExchangeDeedFinalMV(String appId) throws Exception {
		String finalMV = "";
		try {

			dbUtility = new DBUtility();
			String[] param = { appId };
			// sql=RegCommonSQL.GET_EXCHNG_DEED_FINAL_MV;
			sql = RegCommonSQL.GET_EXCHNG_DEED_FINAL_MV_NEW;
			dbUtility.createPreparedStatement(sql);
			finalMV = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out
					.println("Exception in getExchangeDeedFinalMV-RegCommonDAO"
							+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return finalMV;
	}

	/**
	 * getSubClauseListNonBuilding for getting sub clause list non building from
	 * db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSubClauseListNonBuilding(String valId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { valId };
			String query = RegCommonSQL.GET_SUB_CLAUSE_LIST_NON_BUILDING;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger
					.debug("Exception in getSubClauseListNonBuilding"
							+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for getting sub clause name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getSubClauseName(String Id) throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { Id };
			sql = RegCommonSQL.GET_SUBCLASE_NAME;
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getSubClauseName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	/**
	 * getSubClauseListBuilding for getting sub clause list building from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSubClauseListBuilding(String[] param) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_SUB_CLAUSE_LIST_BUILDING;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getSubClauseListBuilding" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	// ADDED BY SHRUTI
	public String getDeedId(int duty_txn_id) {
		String deed = "";
		String[] param = { Integer.toString(duty_txn_id) };
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_DEED_ID;
			dbUtility.createPreparedStatement(sql);
			deed = dbUtility.executeQry(param);
			/*
			 * ArrayList list = dbUtility.executeQuery(param); mainList = new
			 * ArrayList(); ArrayList subList = null; for (int i = 0; i <
			 * list.size(); i++) { subList = (ArrayList)list.get(i); dto = new
			 * CommonDTO(); dto.setId(subList.get(0).toString());
			 * mainList.add(dto); }
			 */
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return deed;
	}

	public ArrayList getExempId(int duty_txn_id) {
		ArrayList exempid = new ArrayList();
		String[] param = { Integer.toString(duty_txn_id) };
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_EXEMPTION_ID;
			dbUtility.createPreparedStatement(sql);
			exempid = dbUtility.executeQuery(param);
			/*
			 * ArrayList list = dbUtility.executeQuery(param); mainList = new
			 * ArrayList(); ArrayList subList = null; for (int i = 0; i <
			 * list.size(); i++) { subList = (ArrayList)list.get(i); dto = new
			 * CommonDTO(); dto.setId(subList.get(0).toString());
			 * mainList.add(dto); }
			 */
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return exempid;
	}

	public String getInstrumentId(int duty_txn_id) {
		String instid = "";
		String[] param = { Integer.toString(duty_txn_id) };
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_INSTRUMENT_ID;
			dbUtility.createPreparedStatement(sql);
			instid = dbUtility.executeQry(param);
			/*
			 * ArrayList list = dbUtility.executeQuery(param); mainList = new
			 * ArrayList(); ArrayList subList = null; for (int i = 0; i <
			 * list.size(); i++) { subList = (ArrayList)list.get(i); dto = new
			 * CommonDTO(); dto.setId(subList.get(0).toString());
			 * mainList.add(dto); }
			 */
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
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
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author Shruti
	 */
	public boolean insrtDepositDeedApplcntTransPartyBnkDetls(String[] party,
			String[] deed, String[] bankDetls, HashMap ownerDetailsMap,
			String exemp, RegCommonForm form ) {
		boolean boo = true;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			
			
			if (deed.length == 11) {
				
				sql = RegCommonSQL.INSERT_REG_TXN_DETLS;  //mohit
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(deed);
			}
			
			
			
			if (boo) {
			if(ownerDetailsMap!=null && ownerDetailsMap.size()>0)
			{
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				
				
				String ownerDetails[] = new String[21];
				Collection mapCol=ownerDetailsMap.values();
                Object[] l2=mapCol.toArray();
                RegCommonDTO ownerMap =new RegCommonDTO();
				for(int k=0;k<l2.length;k++)
				{
					ownerMap=(RegCommonDTO)l2[k];
				
					ownerDetails[0] = party[0]; 							// reg txn id
					if (ownerMap.getOwnerOgrNameTrns().equals("-") 
							|| ownerMap.getOwnerOgrNameTrns().equals("")
							|| ownerMap.getOwnerOgrNameTrns().equals(null)) {
						ownerDetails[1] = "2"; 								// individual party type
						ownerDetails[2] = ownerMap.getOwnerNameTrns(); 		// first name
						ownerDetails[12] = ""; 								// authorized person name
					} else {
						ownerDetails[1] = "1"; 								// organization party type
						ownerDetails[2] = ""; 								// first name
						ownerDetails[12] = ownerMap.getOwnerNameTrns(); 	// authorized person name
					}
					ownerDetails[3] = ownerMap.getOwnerGendarValTrns(); 	// gender
					ownerDetails[4] = ownerMap.getOwnerAgeTrns(); 			// DOB to age
					ownerDetails[5] = ownerMap.getOwnerNationalityTrns(); 	// nationality
					ownerDetails[6] = ownerMap.getOwnerAddressTrns(); 		// address
					ownerDetails[7] = ownerMap.getOwnerPhnoTrns(); 			// phone number
					if (ownerMap.getOwnerEmailIDTrns() != null
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("-")) {
						ownerDetails[8] = ownerMap.getOwnerEmailIDTrns(); 	// email id
					} else {
						ownerDetails[8] = "";
					}
					ownerDetails[9] = ownerMap.getOwnerListIDTrns(); 		// photo proof type id
					
					
					if (ownerMap.getOwnerIdnoTrns() != null
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("-")) {
						ownerDetails[10] = ownerMap.getOwnerIdnoTrns(); 	// photo proof number
					} else {
						ownerDetails[10] = "";
					}
					
					
					if (ownerMap.getOwnerOgrNameTrns() != null
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("-")) {
						ownerDetails[11] = ownerMap.getOwnerOgrNameTrns(); 	// organization name
					} else {
						ownerDetails[11] = "";
					}
					ownerDetails[13] = ownerMap.getOwnerTxnId(); 			// party transaction
					ownerDetails[14] = "O"; 								// is applicant O-Owner
					ownerDetails[15] = ""; 									// share of property
					ownerDetails[16] = Integer
							.toString(RegInitConstant.ROLE_OWNER_SELF); 	// party role
					ownerDetails[17] = party[30]; 							// CREATED BY
					
					
					if (ownerMap.getAddressOwnerOutMpTrns() != null
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("")
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("null")
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("-")) {
						ownerDetails[18] = ownerMap.getAddressOwnerOutMpTrns(); 	// address of owner outside mp
					} else {
						ownerDetails[18] = "";
					}
					
					ownerDetails[19] = party[23];
					ownerDetails[20]="";
					boo = dbUtility.executeUpdate(ownerDetails);
					dbUtility.commit();
					if(!boo)
						break;
					
				
				}
				
				
				
				
				
				
				if (boo) {
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(party);
					dbUtility.commit();
				} else{
					dbUtility.rollback();
					throw new Exception();
				}
			}else{

				sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(party);
				dbUtility.commit();
			
			}
		}else{
			dbUtility.rollback();
			throw new Exception();
		}
			if (boo) {

				if (deed.length == 11) {
					//sql = RegCommonSQL.INSERT_REG_TXN_DETLS;  //mohit
					//dbUtility.createPreparedStatement(sql);
					//boo = dbUtility.executeUpdate(deed);
					// BELOW CODE FOR INSERTION OF EXEMPTIONS
					if (boo) {

						String[] exempParam = new String[3];
						if (exemp != null && !exemp.equalsIgnoreCase("")) {

							String[] exempArr = exemp.split("-");
							if (exempArr != null && exempArr.length > 0) {

								for (int i = 0; i < exempArr.length; i++) {

									exempParam[0] = party[0];
									exempParam[1] = exempArr[i].trim();
									exempParam[2] = party[30];

									sql = RegCommonSQL.INSERT_REG_EXEMPTION_DETLS;
									dbUtility.createPreparedStatement(sql);
									boo = dbUtility.executeUpdate(exempParam);

									if (!boo) {
										break;
									}

								}
							}
						}
					}

					if (boo) {

						String[] dutyParam = { deed[6] }; // duty txn id
						sql = RegCommonSQL.GET_DUTIES_FROM_DUTY;
						dbUtility.createPreparedStatement(sql);
						ArrayList dutyList = dbUtility.executeQuery(dutyParam);
						ArrayList rowList;
						if (dutyList != null && dutyList.size() == 1) {

							rowList = (ArrayList) dutyList.get(0);
							String[] dutiesParam = new String[15];

							dutiesParam[0] = party[0]; // reg txn id
							dutiesParam[1] = rowList.get(0).toString();
							dutiesParam[2] = rowList.get(1).toString();
							dutiesParam[3] = rowList.get(2).toString();
							dutiesParam[4] = rowList.get(3).toString();
							dutiesParam[5] = rowList.get(4).toString();
							dutiesParam[6] = rowList.get(5).toString();
							dutiesParam[7] = party[30]; // user id

							dutiesParam[8] = rowList.get(8).toString();// duty
																		// already
																		// paid
							dutiesParam[9] = rowList.get(9).toString();// fee
																		// already
																		// paid

							if (rowList.get(6) != null
									&& !rowList.get(6).toString()
											.equalsIgnoreCase("")
									&& !rowList.get(6).toString()
											.equalsIgnoreCase("null")) {
								dutiesParam[10] = rowList.get(6).toString();
							} else {
								dutiesParam[10] = "0";
							}

							if (rowList.get(7) != null
									&& !rowList.get(7).toString()
											.equalsIgnoreCase("")
									&& !rowList.get(7).toString()
											.equalsIgnoreCase("null")) {
								dutiesParam[11] = rowList.get(7).toString();
							} else {
								dutiesParam[11] = "0";
							}

							dutiesParam[12] = rowList.get(10).toString();// duty
																			// exemp
							dutiesParam[13] = rowList.get(11).toString();// fee
																			// exemp
							dutiesParam[14] = rowList.get(12).toString();//ews flag

							/*
							 * if(rowList.get(6)!=null &&
							 * !rowList.get(6).toString().equalsIgnoreCase("")
							 * &&
							 * !rowList.get(6).toString().equalsIgnoreCase("null"
							 * )) { dutiesParam[8]=rowList.get(6).toString(); }
							 * else{ dutiesParam[8]="0"; }
							 * 
							 * if(rowList.get(7)!=null &&
							 * !rowList.get(7).toString().equalsIgnoreCase("")
							 * &&
							 * !rowList.get(7).toString().equalsIgnoreCase("null"
							 * )) { dutiesParam[9]=rowList.get(7).toString(); }
							 * else{ dutiesParam[9]="0"; }
							 */
							// dutiesParam[8]=rowList.get(6).toString();
							// dutiesParam[9]=rowList.get(7).toString();

							sql = RegCommonSQL.INSERT_DUTIES_DETAILS_NEW;
							dbUtility.createPreparedStatement(sql);
							boo = dbUtility.executeUpdate(dutiesParam);

						}

					}

				}

				if (bankDetls != null) { // for title deed and trust deed only.
					if (boo) {
						sql = RegCommonSQL.INSERT_REG_DEED_EXTRA_DTLS;
						dbUtility.createPreparedStatement(sql);
						boo = dbUtility.executeUpdate(bankDetls);
						if (boo) {
							dbUtility.commit();
						} else {
							dbUtility.rollback();
							throw new Exception();
						}
					} else {
						dbUtility.rollback();
						throw new Exception();
					}
				} else {
					dbUtility.commit();
				}

			} else{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	public ArrayList getPropTypeL1List(String propId, String languageLocale)
			throws Exception {

		ArrayList list = new ArrayList();

		try {

			String[] param = { propId };
			dbUtility = new DBUtility();
			// dbUtility.createPreparedStatement(RegCommonSQL.SELECTPROPERTYTYPEL1DETAILS);
			dbUtility
					.createPreparedStatement(RegCommonSQL.SELECTPROPERTYTYPEL1DETAILS_HINDI);
			ArrayList ret = dbUtility.executeQuery(param);

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList lst = (ArrayList) ret.get(i);
					RegCompletDTO dto = new RegCompletDTO();
					logger.debug("Dist:-" + lst.get(0) + ":" + lst.get(1));
					dto.setPropTypeL1Id((String) lst.get(0));
					if (languageLocale
							.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						dto.setPropTypeL1((String) lst.get(1));
						dto.setHdnPropTypeL1((String) lst.get(0) + "~"
								+ (String) lst.get(1));
					} else {
						dto.setPropTypeL1((String) lst.get(2));
						dto.setHdnPropTypeL1((String) lst.get(0) + "~"
								+ (String) lst.get(2));
					}
					list.add(dto);
				}

			}
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * getNonPropDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author Shruti
	 */
	public ArrayList getNonPropDutyDetls(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_REG_NON_PROP_DUTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getNonPropDutyDetls" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	// END OF CODE BY SHRUTI

	/**
	 * for getting all Categories from DB.
	 * 
	 * @return ArrayList
	 */
	public ArrayList getCategoryList(String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			// sql = RegCommonSQL.SELECT_CATEGORY;
			sql = RegCommonSQL.SELECT_CATEGORY_HINDI;
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				if (languageLocale
						.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					dto.setName(subList.get(1).toString());
				} else {
					dto.setName(subList.get(2).toString());
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
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
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getCategoryName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_CATEGORY_NAME;
			} else {
				sql = RegCommonSQL.GET_CATEGORY_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getCategoryName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	/**
	 * getPropertyDetailsFromValuation for getting property details from
	 * valuation tables.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyDetailsFromValuation(String valId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { valId };
			String query = RegCommonSQL.GET_PROP_DETLS_FROM_VAL;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropertyDetailsFromValuation"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getExemptionList for getting exemption ids from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getExemptionList(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_REG_EXEMPTION_DETLS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getExemptionList" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getPropDetlsForDisplay for getting PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropDetlsForDisplay(String propId) throws Exception {
		ArrayList propDetls = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { propId };
			String query = RegCommonSQL.GET_PROP_DETLS_DISPLAY;
			dbUtility.createPreparedStatement(query);
			propDetls = dbUtility.executeQuery(param);
logger.debug(propDetls);
		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDisplay" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return propDetls;

	}

	/**
	 * getPropKhasraDetlsForDisplay for getting PROPERTY KHASRA details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropKhasraDetlsForDisplay(String propId)
			throws Exception {
		ArrayList propDetls = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { propId };
			String query = RegCommonSQL.GET_PROP_KHASRA_DETLS_DISPLAY;
			dbUtility.createPreparedStatement(query);
			propDetls = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropKhasraDetlsForDisplay"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return propDetls;

	}

	/**
	 * for getting all Occupation List
	 * 
	 * @return ArrayList
	 */
	public ArrayList getOccupationList(String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			// sql = RegCommonSQL.GET_OCCUPATION_LIST;
			sql = RegCommonSQL.GET_OCCUPATION_LIST_HINDI;
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				if (languageLocale
						.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					dto.setName(subList.get(1).toString());
				} else {
					dto.setName(subList.get(2).toString());
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
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
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getOccupationName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_OCCUPATION_NAME;
			} else {
				sql = RegCommonSQL.GET_OCCUPATION_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getOccupationName-RegCommonDAO"
					+ exception);
		} finally {
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
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantShare(String id) throws Exception {
		String share = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			sql = RegCommonSQL.GET_APPLICANT_SHARE;
			dbUtility.createPreparedStatement(sql);
			share = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getApplicantShare-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return share;
	}

	/**
	 * getPartyTxnIdList for getting party txn id list corresponding to property
	 * id given
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyTxnIdList(String propId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { propId };
			String query = RegCommonSQL.GET_PARTY_TXN_ID_LIST;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPartyTxnIdList" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	public ArrayList getPartyDetailsPdf(String partyId) throws Exception {
		ArrayList list = new ArrayList();
		try {

			// String param[]={appId.trim(),appId.trim()};
			dbUtility = new DBUtility();
			String[] param = { partyId };
			String query = RegCommonSQL.GET_PARTY_DETAILS_PDF;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPartyDetailsPdf" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getGuidelineRate for getting current guideline rate from db.
	 * 
	 * @param String
	 *            []
	 * @return String
	 * @author ROOPAM
	 */
	public String getGuidelineRate(String[] param) throws Exception {
		String rate = "";
		String[] finalParam;
		try {
			dbUtility = new DBUtility();
			String query = "";
			if (param[6] == null || param[6].equalsIgnoreCase("")
					|| param[6].equalsIgnoreCase("null")) {
				finalParam = new String[6];
				finalParam[0] = param[0];
				finalParam[1] = param[1];
				finalParam[2] = param[2];
				finalParam[3] = param[3];
				finalParam[4] = param[4];
				finalParam[5] = param[5];
				query = RegCommonSQL.GET_GUIDELINE_RATE_WITHOUT_L2;
			} else {
				finalParam = param;
				query = RegCommonSQL.GET_GUIDELINE_RATE_WITH_L2;
			}
			dbUtility.createPreparedStatement(query);

			rate = dbUtility.executeQry(finalParam);

		} catch (Exception exception) {
			logger.debug("Exception in getGuidelineRate" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return rate;

	}

	/**
	 * for inserting transacting party details title deed in db.
	 * 
	 * @param String
	 *            [],String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insrtTransPartyDetlsTitleDeed(String[] party,
			HashMap ownerDetailsMap, String[] statusParam) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			
			
			if(ownerDetailsMap!=null && ownerDetailsMap.size()>0) {
				
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				
				
				
				String ownerDetails[] = new String[21];
				Collection mapCol=ownerDetailsMap.values();
                Object[] l2=mapCol.toArray();
                RegCommonDTO ownerMap =new RegCommonDTO();
				for(int k=0;k<l2.length;k++)
				{
					ownerMap=(RegCommonDTO)l2[k];
				
					ownerDetails[0] = party[0]; 							// reg txn id
					if (ownerMap.getOwnerOgrNameTrns().equals("-") 
							|| ownerMap.getOwnerOgrNameTrns().equals("")
							|| ownerMap.getOwnerOgrNameTrns().equals(null)) {
						ownerDetails[1] = "2"; 								// individual party type
						ownerDetails[2] = ownerMap.getOwnerNameTrns(); 		// first name
						ownerDetails[12] = ""; 								// authorized person name
					} else {
						ownerDetails[1] = "1"; 								// organization party type
						ownerDetails[2] = ""; 								// first name
						ownerDetails[12] = ownerMap.getOwnerNameTrns(); 	// authorized person name
					}
					ownerDetails[3] = ownerMap.getOwnerGendarValTrns(); 	// gender
					ownerDetails[4] = ownerMap.getOwnerAgeTrns(); 			// DOB to age
					ownerDetails[5] = ownerMap.getOwnerNationalityTrns(); 	// nationality
					ownerDetails[6] = ownerMap.getOwnerAddressTrns(); 		// address
					ownerDetails[7] = ownerMap.getOwnerPhnoTrns(); 			// phone number
					if (ownerMap.getOwnerEmailIDTrns() != null
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerEmailIDTrns().equalsIgnoreCase("-")) {
						ownerDetails[8] = ownerMap.getOwnerEmailIDTrns(); 	// email id
					} else {
						ownerDetails[8] = "";
					}
					ownerDetails[9] = ownerMap.getOwnerListIDTrns(); 		// photo proof type id
					
					
					if (ownerMap.getOwnerIdnoTrns() != null
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerIdnoTrns().equalsIgnoreCase("-")) {
						ownerDetails[10] = ownerMap.getOwnerIdnoTrns(); 	// photo proof number
					} else {
						ownerDetails[10] = "";
					}
					
					
					if (ownerMap.getOwnerOgrNameTrns() != null
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("")
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("null")
							&& !ownerMap.getOwnerOgrNameTrns().equalsIgnoreCase("-")) {
						ownerDetails[11] = ownerMap.getOwnerOgrNameTrns(); 	// organization name
					} else {
						ownerDetails[11] = "";
					}
					ownerDetails[13] = ownerMap.getOwnerTxnId(); 			// party transaction
					ownerDetails[14] = "O"; 								// is applicant O-Owner
					ownerDetails[15] = ""; 									// share of property
					ownerDetails[16] = Integer
							.toString(RegInitConstant.ROLE_OWNER_SELF); 	// party role
					ownerDetails[17] = party[30]; 							// CREATED BY
					
					
					if (ownerMap.getAddressOwnerOutMpTrns() != null
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("")
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("null")
							&& !ownerMap.getAddressOwnerOutMpTrns().equalsIgnoreCase("-")) {
						ownerDetails[18] = ownerMap.getAddressOwnerOutMpTrns(); 	// address of owner outside mp
					} else {
						ownerDetails[18] = "";
					}
					
					ownerDetails[19] = party[23];
					ownerDetails[20] = "";// parent id
					boo = dbUtility.executeUpdate(ownerDetails);
					dbUtility.commit();
					if(!boo)
						break;
					
				
				}
				
				
				if (boo) {
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(party);
					dbUtility.commit();
				} else{
					dbUtility.rollback();
					throw new Exception();
				}
			}else {
				sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW; // relationship
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(party);
				dbUtility.commit();
			} 
			if (boo) {

				if (statusParam != null) {
					sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(statusParam);
					if (boo) {
						dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}
				} else {
					dbUtility.commit();
				}

			} else {
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			boo = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception in RegCommonDAO:insrtTransPartyDetlsTitleDeed rollback.");
			}
			logger.debug(e.getMessage(),e);
		} finally {
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
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropTypeL2List(String propL1Id) throws Exception {
		ArrayList ret = new ArrayList();
		try {
			String[] param = { propL1Id };
			dbUtility = new DBUtility();
			// dbUtility.createPreparedStatement(RegCommonSQL.SELECT_PROPERTY_TYPE_L2_DETAILS);
			dbUtility
					.createPreparedStatement(RegCommonSQL.SELECT_PROPERTY_TYPE_L2_DETAILS_HINDI);
			ret = dbUtility.executeQuery(param);

		} catch (Exception e) {
			logger.debug(e);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * for getting unit list
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getUnitList(String propL1Id) throws Exception {
		ArrayList ret = new ArrayList();
		try {
			String[] param = { propL1Id };
			dbUtility = new DBUtility();
			// dbUtility.createPreparedStatement(RegCommonSQL.SELECT_UNIT_LIST_DETAILS);
			dbUtility
					.createPreparedStatement(RegCommonSQL.SELECT_UNIT_LIST_DETAILS_HINDI);
			ret = dbUtility.executeQuery(param);

		} catch (Exception e) {
			logger.debug(e);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * for getting property type name from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropertyTypeName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_PROPERTY_TYPE_NAME;
			} else {
				sql = RegCommonSQL.GET_PROPERTY_TYPE_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getPropertyTypeName-RegCommonDAO"
					+ exception);
		} finally {
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
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertPropertyDetailsNonPropDeeds(String[] param,
			ArrayList khasra, HashMap floorMap, String[] regStatus,
			String[] mapParam,String[] newDuties) {
		boolean boo = false;
		String khasraNo;
		String rinPustika;
		String khasraArea;
		String lagaan;
		String[] khasraNoArr;
		String[] rinPustikaArr;
		String[] khasraAreaArr;
		String[] lagaanArr;
		String[] northArr;
		String[] southArr;
		String[] eastArr;
		String[] westArr;
		
		String[] floorArr = new String[8];
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_REG_PROP_DETLS_NON_PROP_DEEDS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {

				// BELOW CODE FOR INSERTING KHASRA DETAILS INTO DATABASE
				if (khasra != null && khasra.size()>0) {
				
					String[] khasraParam = new String[10];

					for (int i = 0; i < khasra.size(); i++) {
						CommonDTO member = (CommonDTO)khasra.get(i);
						
						if(member.getKhasraNum()!=null && !("").equalsIgnoreCase(member.getKhasraNum()) 
								&& member.getNorth()!=null && !("").equalsIgnoreCase(member.getNorth()))
						{
						khasraParam[0] = param[19]; // PROPERTY ID
						khasraParam[1] = member.getKhasraNum();
						khasraParam[2] = member.getRinPustika();
						khasraParam[3] = member.getKhasraArea();
						khasraParam[4] = member.getLagaan();
						
						khasraParam[5] = member.getNorth();
						khasraParam[6] = member.getSouth();
						khasraParam[7] = member.getEast();
						khasraParam[8] = member.getWest();
						
						khasraParam[9] = param[12]; // CREATED BY

						sql = RegCommonSQL.INSERT_REG_INIT_PROP_KHASRA_DETLS;
						dbUtility.createPreparedStatement(sql);
						boo = dbUtility.executeUpdate(khasraParam);

						if (!boo) {
							break;
						}
						}

					}

				}
				
				if(boo && newDuties!=null){
					
					
					sql = RegCommonSQL.UPDATE_EWS_DUTIES;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(newDuties);
					if (boo) {
						sql = RegCommonSQL.UPDATE_EWS_DUTIES_DUTY;
						dbUtility.createPreparedStatement(sql);
						boo = dbUtility.executeUpdate(newDuties);
						if(boo){
							
						}else{
							dbUtility.rollback();
							throw new Exception();
						}
					} else {
						dbUtility.rollback();
						throw new Exception();
					}
					
					
					
				}

				if (boo) {

					

						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						boo = dbUtility.executeUpdate(regStatus);
						if (boo) {
							// dbUtility.commit();
						} else {
							dbUtility.rollback();
							throw new Exception();
						}
					
					if (boo) {

						if (mapParam != null) {

							String propDets[] = new String[4];

							for (int i = 0; i < mapParam.length; i++) {
								propDets[0] = param[1];
								propDets[1] = param[0];
								propDets[2] = mapParam[i];
								propDets[3] = param[25];

								sql = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
								dbUtility.createPreparedStatement(sql);
								boo = dbUtility.executeUpdate(propDets);
								dbUtility.commit();
								if (!boo) {
									break;
								}

							}

							if (boo) {
								dbUtility.commit();
							} else {
								dbUtility.rollback();
								throw new Exception();

							}

						} else {
							dbUtility.commit();
						}

					} else {
						dbUtility.rollback();
						throw new Exception();

					}

				} else {
					dbUtility.rollback();
					throw new Exception();

				}

			} else {
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			boo = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception while rollback in RegCommonDAO:updatePropertyDetails");
			}
			logger.debug(e.getMessage(),e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * getPropertyListNonPropDeed for getting PROPERTY ID list from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyListNonPropDeed(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_PROPERTY_ID_LIST_NON_PROP_DEED;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropertyListNonPropDeed" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getTransPartyDetsNonPropDeed for getting transacting party details
	 * corresponding to a registration id non property deed from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDetsNonPropDeed(String appId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			String param[] = new String[1];
			param[0] = appId;
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_TRANS_PARTY_DETS_NON_PROP_DEED;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyDetsNonPropDeed"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getBuildingFloorDetailsNonProp for getting floor details from db in case
	 * of non property deed
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getBuildingFloorDetailsNonProp(String propId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { propId };
			String query = RegCommonSQL.GET_BUILDING_FLOOR_DETAILS_NON_PROP;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getBuildingFloorDetailsNonProp"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for getting payment table primary key sequence id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPaymentTxnIdSeq() throws Exception {

		String seqId = "0";
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			seqId = dbUtility
					.executeQry(RegCommonSQL.GET_REG_PAYMENT_TXN_ID_SEQ);
		} catch (Exception exception) {
			System.out.println("Exception in getPaymentTxnIdSeq-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return seqId;
	}

	/**
	 * getPaidAmounts for getting all paid amounts details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPaidAmounts(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_ALL_PAID_AMOUNTS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAllPaidAmounts" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for getting district id from db that is required for generating e stamp
	 * code.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getDistIdEstamp(String appId) throws Exception {

		String distId = "";

		try {

			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String param1[] = { appId };
			sql = RegCommonSQL.COUNT_PROP_ID;
			dbUtility.createPreparedStatement(sql);
			String var = dbUtility.executeQry(param1);
			int propCount = Integer.parseInt(var);

			if (propCount == 0) {

				String param2[] = { appId };
				sql = RegCommonSQL.GET_REG_APPLICANT_DIST_ID;
				dbUtility.createPreparedStatement(sql);
				distId = dbUtility.executeQry(param2);

			} else {

				String param[] = { appId, appId };
				sql = RegCommonSQL.GET_REG_DIST_ID;
				dbUtility.createPreparedStatement(sql);
				distId = dbUtility.executeQry(param);
			}

		} catch (Exception exception) {

			logger.debug("Exception in getDistIdEstamp-RegCommonDAO"
					+ exception);
			throw new Exception("Exception in getDistIdEstamp-RegCommonDAO");

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return distId;

	}
	
	
	//changes by preeti for payment po
	public String getDistIdEfile(String tempefile) throws Exception {

		String distId = "";

		try {

			
			
			
			dbUtility = new DBUtility();
			
			String temp[] = new String[1];
			temp[0] = tempefile;
				sql = RegCommonSQL.GET_EFILE_DIST_ID;
				dbUtility.createPreparedStatement(sql);
				distId = dbUtility.executeQry(temp);
			

		} catch (Exception exception) {

			logger.debug("Exception in getDistIdEstamp-RegCommonDAO"
					+ exception);
			throw new Exception("Exception in getDistIdEstamp-RegCommonDAO");

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return distId;

	}
	
	
	
	public String getTempEfileNoPayment(String TempIdPo1) throws Exception {

		String distId = "";

		try {

			
			
			
			dbUtility = new DBUtility();
			
			String temp[] = new String[1];
			temp[0] = TempIdPo1;
				sql = RegCommonSQL.GET_EFILE_ID_PAY;
				dbUtility.createPreparedStatement(sql);
				distId = dbUtility.executeQry(temp);
			

		} catch (Exception exception) {

			logger.debug("Exception in getDistIdEstamp-RegCommonDAO"
					+ exception);
			throw new Exception("Exception in getDistIdEstamp-RegCommonDAO");

		} finally {
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
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertEstampMappingDetls(String[] param) throws Exception {
		boolean boo = false;
		dbUtility = new DBUtility();
		try {
			
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_REG_ESTAMP_MAP_DETLS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo)
				dbUtility.commit();
			else{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in insertEstampMappingDetls start"
					+ e.getStackTrace());
			dbUtility.rollback();
			return false;
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return boo;
	}

	/**
	 * getBankDetails for getting bank details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getBankDetails(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_BANK_DETLS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getBankDetails" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getPartyIdsExchngPdf for getting party ids of exchange deed for pdf from
	 * db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyIdsExchngPdf(String[] param) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();

			String query = RegCommonSQL.GET_PARTY_IDS_EXCHNG_PDF;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPartyIdsExchngPdf" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getPropIdsExchngPdf for getting party ids of exchange deed for pdf from
	 * db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropIdsExchngPdf(String[] param) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();

			String query = RegCommonSQL.GET_PROP_IDS_EXCHNG_PDF;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropIdsExchngPdf" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getPartyTxnIdListTitleDeed for getting party txn id list corresponding to
	 * reg id given
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyTxnIdListTitleDeed(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_PARTY_TXN_ID_LIST_TITLE_DEED;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPartyTxnIdListTitleDeed" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getConsidAmtSysMV for getting consideration amount and system calculated
	 * MV from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getConsidAmtSysMV(String valId) throws Exception {
		ArrayList propDetls = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { valId };
			String query = RegCommonSQL.GET_CONSID_AMNT_SYS_MV;
			dbUtility.createPreparedStatement(query);
			propDetls = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getConsidAmtSysMV" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return propDetls;

	}

	/**
	 * getConsidAmtTitle for getting consideration amount from db for Title
	 * deed.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getConsidAmtTitle(String regId) throws Exception {
		String str = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { regId };
			String query = RegCommonSQL.GET_CONSID_AMNT_TITLE_DEED;
			dbUtility.createPreparedStatement(query);
			str = dbUtility.executeQry(param);

		} catch (Exception exception) {
			logger.debug("Exception in getConsidAmtSysMV" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return str;

	}

	/**
	 * getBuildingFloorDetailsTitleDeed for getting building floor details for
	 * title deed from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getBuildingFloorDetailsTitleDeed(String propId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { propId };
			String query = RegCommonSQL.GET_BUILDING_FLOOR_DETAILS_TITLE_DEED;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getBuildingFloorDetailsTitleDeed"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getPaymentDetlsForDisplay for getting payment matrix details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPaymentDetlsForDisplay(String regId) throws Exception {
		ArrayList paymentDetls = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { regId };
			String query = RegCommonSQL.GET_PAYMENT_DETLS_DISPLAY;
			dbUtility.createPreparedStatement(query);
			paymentDetls = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPaymentDetlsForDisplay" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return paymentDetls;

	}

	/**
	 * for UPDATING transacting parties details in db.
	 * 
	 * @param String
	 *            [],String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean updateTransPartyDetls(String[] party) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			//if (ownerDetails == null) {
				sql = RegCommonSQL.UPDATE_REG_TXN_PARTY_NEW;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(party);
				
				if(boo){
					dbUtility.commit();	
				}
			/*} else {
				sql = RegCommonSQL.UPDATE_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(ownerDetails);
				if (boo) {
					sql = RegCommonSQL.UPDATE_REG_TXN_PARTY_NEW;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(party);
					
					if(boo){
						dbUtility.commit();	
					}else{
						dbUtility.rollback();
					}
				} else
					dbUtility.rollback();
			}*/

		} catch (Exception e) {
			boo = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception in RegCommonDAO:updateTransPartyDetls rollback.");
			}
			logger.debug(e.getMessage(),e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	public boolean updateOwnerDetls(String[] ownerDetails) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		 {
				sql = RegCommonSQL.UPDATE_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(ownerDetails);
				
					
					if(boo){
						dbUtility.commit();	
					}else{
						dbUtility.rollback();
						throw new Exception();
					}
				
			}

		} catch (Exception e) {
			boo = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception in RegCommonDAO:updateOwnerDetls rollback.");
			}
			logger.debug(e.getMessage(),e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * getEstampCode for getting eStamp code from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getEstampCode(String regId) throws Exception {
		String str = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { regId };
			String query = RegCommonSQL.GET_ESTAMP_CODE;
			dbUtility.createPreparedStatement(query);
			str = dbUtility.executeQry(param);

		} catch (Exception exception) {
			logger.debug("Exception in getEstampCode" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return str;

	}
	
	
	
	public String getdutyId(String efileId) throws Exception {
		String str = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { efileId };
			String query = RegCommonSQL.GET_DUTY_ID_DASHBOARD;
			dbUtility.createPreparedStatement(query);
			str = dbUtility.executeQry(param);

		} catch (Exception exception) {
			logger.debug("Exception in getEstampCode" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return str;

	}
	
	
	public String getdashboarddutyid(String tempEfileId) throws Exception {
		String str = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { tempEfileId };
			String query = RegCommonSQL.GET_DUTY_ID_DASHBOARD1;
			dbUtility.createPreparedStatement(query);
			str = dbUtility.executeQry(param);

		} catch (Exception exception) {
			logger.debug("Exception in getEstampCode" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return str;

	}
	
	public String getexeDate(String getdutyId) throws Exception {
		String str = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { getdutyId };
			String query = RegCommonSQL.GET_EXECUTION_DATE_DASHBOARD;
			dbUtility.createPreparedStatement(query);
			str = dbUtility.executeQry(param);

		} catch (Exception exception) {
			logger.debug("Exception in getEstampCode" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return str;

	}
	
	
	
	public ArrayList getEstampTxnDetls(String regId) throws Exception {
		ArrayList str = new ArrayList() ;
		try {
			dbUtility = new DBUtility();
			String[] param = { regId };
			String query = RegCommonSQL.GET_ESTAMP_TXN_DETLS;
			dbUtility.createPreparedStatement(query);
			str = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getEstampCode" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return str;

	}

	/**
	 * getPendingAppStatus for getting pending reg init application details from
	 * db
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public ArrayList getPendingAppStatus(String appId) throws Exception {
		// String appStatus="";
		ArrayList adjuStatus = new ArrayList();

		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			sql = RegCommonSQL.GET_PENDING_APP_STATUS;
			dbUtility.createPreparedStatement(sql);
			adjuStatus = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			System.out
					.println("Exception in getPendingAppStatus()" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return adjuStatus;

	}

	/**
	 * getPropertyIdApplicant for getting property id of applicant from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdApplicant(String appId) throws Exception {
		ArrayList propId = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			sql = RegCommonSQL.GET_PROPERTY_ID_OF_APPLICANT;
			dbUtility.createPreparedStatement(sql);
			propId = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			System.out.println("Exception in getPropertyIdApplicant()"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return propId;

	}

	/**
	 * getLatestPropertyId for getting latest property id from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getLatestPropertyId(String appId) throws Exception {
		ArrayList propId = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId, appId };
			sql = RegCommonSQL.GET_LATEST_PROPERTY_ID;
			dbUtility.createPreparedStatement(sql);

			propId = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getLatestPropertyId()" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return propId;

	}
	
	public ArrayList getAllPropertyId(String appId) throws Exception {
		ArrayList propId = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			sql = RegCommonSQL.GET_ALL_PROPERTY_ID;
			dbUtility.createPreparedStatement(sql);

			propId = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAllPropertyId()" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return propId;

	}

	/**
	 * getAllPropDetlsExchangeDeedDash for getting ALL PROPERTY DETAILS from db
	 * for dashboard.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPropDetlsExchangeDeedDash(String valId,
			String dutyTxnId, String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { valId, dutyTxnId, appId };
			// String query=RegCommonSQL.GET_ALL_PROPERTY_DETLS_DASH;
			String query = RegCommonSQL.GET_ALL_PROPERTY_DETLS_DASH_NEW;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAllPropDetlsExchangeDeedDash"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getRemValuationIdsExchangeDeed for getting remaining valuation ids from
	 * db for dashboard.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getRemValuationIds(String dutyTxnId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { dutyTxnId };
			// String
			// query=RegCommonSQL.GET_REMAINING_VALUATION_IDS_EXCHANGE_DASH;
			String query = RegCommonSQL.GET_REMAINING_VALUATION_IDS_DASH;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getRemValuationIds" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getDutyTxnId for getting duty txn id from db
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getDutyTxnId(String appId) throws Exception {
		String appStatus = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			sql = RegCommonSQL.GET_DUTY_TXN_ID;
			dbUtility.createPreparedStatement(sql);
			appStatus = dbUtility.executeQry(param);

		} catch (Exception exception) {
			System.out.println("Exception in getDutyTxnId()" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return appStatus;

	}
	
	public String getdutyTxnIdDashboard(String tempefileid) throws Exception {
		String appStatus = "";
		String flag="";
		try {
			dbUtility = new DBUtility();
			String[] param = { tempefileid };
			sql = RegCommonSQL.GET_DUTY_TXN_ID;
			dbUtility.createPreparedStatement(sql);
			appStatus = dbUtility.executeQry(param);
			
			

		} catch (Exception exception) {
			System.out.println("Exception in getDutyTxnId()" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return appStatus;

	}
	
	
	public String getpayFlag(String tempefileid) throws Exception {
		String appStatus = "";
		String payFlag="";
		try {
			dbUtility = new DBUtility();
			String[] param = { tempefileid };
			sql = RegCommonSQL.GET_PAY_FLAG;
			dbUtility.createPreparedStatement(sql);
			appStatus = dbUtility.executeQry(param);
			
			if(appStatus!=null && !appStatus.isEmpty()){
				payFlag="Y";
			}

		} catch (Exception exception) {
			System.out.println("Exception in getDutyTxnId()" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return payFlag;

	}
	
	
	public String getpayflagphysical(String tempefileid) throws Exception {
		
		String payFlag="";
		String temp=tempefileid.trim();
		boolean flag=false;
		String appStatus = "";
		
		try {
			dbUtility = new DBUtility();
			String[] param = { tempefileid };
			sql = RegCommonSQL.GET_PAY_FLAG_PHYSICAL;
			dbUtility.createPreparedStatement(sql);
			appStatus = dbUtility.executeQry(param);
			
			if(appStatus!=null && !appStatus.isEmpty()){
				payFlag="Y";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Exception in getDutyTxnId()" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return payFlag;

	}

	/**
	 * getApplicantAddress for getting applicant district and address from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantAddress(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_APPLICANT_ADDRESS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getApplicantAddress" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * insertTxnDetailsFinalAction for inserting reg txn status from final
	 * action
	 * 
	 * @param String
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertTxnDetailsFinalAction(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	public boolean insertTxnDetailsFinalActionWithRegFee(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS_FEE;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	public boolean updateConsenterFlag(String[] param) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_CONSENTER_FLAG;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
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
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author Shruti
	 */
	public boolean updateBankDetails(String[] bankDetls) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_BANK_DTLS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(bankDetls);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * getTransPartyDetsNonPropDeed for getting party txn ids from db for prop
	 * trns map non pv deeds
	 * 
	 * @param String
	 *            []
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyTxnIdsForPropMap(String[] param, int deedId,
			int instId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			// String param[]=new String[1];
			// param[0]=appId;
			dbUtility = new DBUtility();
			String query = "";
			if (deedId == RegInitConstant.DEED_TRUST
					&& instId == RegInitConstant.INST_TRUST_NPV_P
					|| (deedId == RegInitConstant.DEED_PARTNERSHIP_NPV && (instId == RegInitConstant.INST_PARTNERSHIP_NPV_1 || instId == RegInitConstant.INST_PARTNERSHIP_NPV_2))) {
				query = RegCommonSQL.GET_PARTY_TXN_IDS_FOR_PROP_MAP_TRUST_NPV_NP;
			} else {
				query = RegCommonSQL.GET_PARTY_TXN_IDS_FOR_PROP_MAP;
			}
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPartyTxnIdsForPropMap" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * for getting extra field label from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExtraFieldLabel(String instId, String languageLocale)
			throws Exception {

		// int regTxnIdSeq = 0;
		String extraFieldLabel = "";

		try {
			dbUtility = new DBUtility();
			String[] param = { instId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_EXTRA_FIELD_LABEL;
			} else {
				sql = RegCommonSQL.GET_EXTRA_FIELD_LABEL_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			extraFieldLabel = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getExtraFieldLabel-RegCommonDAO"
					+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return extraFieldLabel;

	}

	/**
	 * for getting claimant flag from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getClaimantFlag(String roleId) throws Exception {

		// int regTxnIdSeq = 0;
		ArrayList claimantFlag = new ArrayList();

		try {
			dbUtility = new DBUtility();
			String[] param = { roleId };
			sql = RegCommonSQL.GET_CLAIMANT_FLAG;
			dbUtility.createPreparedStatement(sql);
			claimantFlag = dbUtility.executeQuery(param);

		} catch (Exception exception) {

			System.out.println("Exception in getClaimantFlag-RegCommonDAO"
					+ exception);

		} finally {
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
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getUserTypeId(String userId) throws Exception {

		String typeId = "";
		String[] param = { userId };
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.GET_USER_TYPE_ID;
			dbUtility.createPreparedStatement(sql);
			typeId = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getUserTypeId-RegCommonDAO"
					+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return typeId;

	}

	/**
	 * getDistIdNameRU for getting dist id name of registered user
	 * 
	 * @param String
	 *            []
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDistIdNameRU(String userId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			String[] param = { userId };
			dbUtility = new DBUtility();
			String query = "";
			query = RegCommonSQL.GET_DIST_ID_NAME_RU;

			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getDistIdNameRU" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getDistIdNameOfficeNameDRS for getting dist id name, office name of DRS
	 * 
	 * @param String
	 *            []
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDistIdNameOfficeNameDRS(String officeId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			String[] param = { officeId };
			dbUtility = new DBUtility();
			String query = "";
			query = RegCommonSQL.GET_DIST_ID_NAME_OFFC_NAME_DRS;

			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getDistIdNameOfficeNameDRS" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getDistIdNameSP for getting dist id name of SERVICE PROVIDER
	 * 
	 * @param String
	 *            []
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDistIdNameSP(String userId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			String[] param = { userId };
			dbUtility = new DBUtility();
			String query = "";
			query = RegCommonSQL.GET_DIST_ID_NAME_SP;

			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getDistIdNameSP" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * insertAdjuDuties for getting dist id name of SERVICE PROVIDER
	 * 
	 * @param String
	 *            []
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public boolean insertAdjuDuties(String[] param) {
		boolean boo = false;
		// String bool="";
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.INSERT_ADJU_DUTY;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
				/*String[] param1 = { param[0] };
				sql = RegCommonSQL.UPDATE_REG_ADJU_STATUS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(param1);
				if (boo) {
					dbUtility.commit();
				} else {
					dbUtility.rollback();
				}*/
			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * insertAdjuDuties for getting dist id name of SERVICE PROVIDER
	 * 
	 * @param String
	 *            []
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public boolean insertAdjuDutiesSys(String[] param) { boolean boo=false;
	 * //String bool=""; try { dbUtility = new DBUtility();
	 * dbUtility.setAutoCommit(false); sql = RegCommonSQL.INSERT_ADJU_DUTY;
	 * dbUtility.createPreparedStatement(sql);
	 * boo=dbUtility.executeUpdate(param); if(boo){ dbUtility.commit(); String[]
	 * param1={param[0]}; sql = RegCommonSQL.UPDATE_REG_ADJU_STATUS;
	 * dbUtility.createPreparedStatement(sql);
	 * boo=dbUtility.executeUpdate(param1); if(boo){ dbUtility.commit(); }else{
	 * dbUtility.rollback(); } } else dbUtility.rollback(); }catch (Exception e)
	 * { e.printStackTrace(); }finally{ try { dbUtility.closeConnection(); }
	 * catch (Exception e) { logger.error("RegCommonDAO in dao start" +
	 * e.getStackTrace()); } } return boo; }
	 */
	/**
	 * getDutyDetlsAdjuForConfirmation for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getDutyDetlsAdjuForConfirmation(String appId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_REG_ADJU_DUTY_DETS_FOR_CONFIRMATION;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetlsAdjuForConfirmation"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getCommonDeedFlag for getting common deed flag from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getCommonDeedFlag(String deedId) throws Exception {

		String flag = "";
		String[] param = { deedId };
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.GET_COMMON_DEED_FLAG;
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getCommonDeedFlag-RegCommonDAO"
					+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return flag;

	}

	/**
	 * insrtTransPartyDetlsCommonDeeds for inserting applicant details in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	/*public boolean insrtTransPartyDetlsCommonDeeds(String[] party,
			String[] ownerDetails, String[] statusParam) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			if (party[32].equals("") || party[32].equalsIgnoreCase("")) {
				sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW; // relationship
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(party);
			} else {
				sql = RegCommonSQL.INSERT_OWNER_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(ownerDetails);
				if (boo) {
					sql = RegCommonSQL.INSERT_REG_TXN_PARTY_NEW;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(party);
				} else
					dbUtility.rollback();
			}
			if (boo) {
				sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(statusParam);
				if (boo) {
					dbUtility.commit();
				} else {
					dbUtility.rollback();
				}
			} else {
				dbUtility.rollback();
			}
		} catch (Exception e) {
			boo = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception in RegCommonDAO:insrtTransPartyDetlsCommonDeeds rollback.");
			}
			logger.debug(e.getMessage(),e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}*/

	/**
	 * getTransPartyDetsCommonDeed for getting transacting party details
	 * corresponding to a registration id common deed from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDetsCommonDeed(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			String param[] = new String[1];
			param[0] = appId;
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_TRANS_PARTY_DETS_COMMON_DEED;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger
					.debug("Exception in getTransPartyDetsCommonDeed"
							+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * insertParticularsDetails for inserting particular details db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public boolean insertParticularsDetails(HashMap map, String regId,
			String userId, String[] status) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();

			dbUtility.setAutoCommit(false);
			Collection mapCollection = map.values();
			Object[] l1 = mapCollection.toArray();
			RegCommonDTO mapDto1 = new RegCommonDTO();
			String[] param = new String[4];

			for (int i = 0; i < l1.length; i++) {
				mapDto1 = (RegCommonDTO) l1[i];

				param[0] = regId;
				param[1] = mapDto1.getParticularName();
				param[2] = mapDto1.getParticularDesc();
				param[3] = userId;

				sql = RegCommonSQL.INSERT_PARTICULAR_DETAILS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(param);

				if (!boo) {
					dbUtility.rollback();
					break;
				}
			}

			if (boo) {

				sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(status);
				if (boo) {
					dbUtility.commit();
				} else {
					dbUtility.rollback();
					throw new Exception();
				}

			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCommonDAO:insertParticularsDetails in dao start"
								+ e.getStackTrace());
			}
		}
		return boo;
	}

	/**
	 * getParticularList for getting particular list from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getParticularList(String appId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_PARTICULAR_LIST;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getParticularList" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getParticularDetails for getting particular details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getParticularDetails(String partId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			String param[] = new String[1];
			param[0] = partId;
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_PARTICULAR_DETAILS;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getParticularDetails" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * updateParticularDetails for updating particular details in db
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author Shruti
	 */
	public boolean updateParticularDetails(String[] particularDetls) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPADTE_PARTICULAR_DETAILS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(particularDetls);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		} finally {
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
	public ArrayList getPartyDetailsCommonDeedsPdf(String partyId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {

			String param[] = { partyId };
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_PARTY_DETAILS_PDF_COMMON_DEED;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPartyDetailsCommonDeedsPdf"
					+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * getRemarks for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public String getRemarks(String appId) throws Exception {
		String remarks = null;
		try {
			dbUtility = new DBUtility();
			String[] param = { appId };
			String query = RegCommonSQL.GET_ADJU_REMARKS;
			dbUtility.createPreparedStatement(query);
			// list = dbUtility.executeQuery(query);
			remarks = dbUtility.executeQry(param);

		} catch (Exception exception) {
			logger.debug("Exception in getRemarks" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return remarks;

	}

	/**
	 * updateAdjudicationFlag for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public boolean updateAdjudicationFlag(String appId, String userId)
			throws Exception {
		boolean boo = false;
		String[] param = { userId, appId };
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			sql = RegCommonSQL.UPDATE_ADJU_REG_STATUS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();

			} else
			{
				dbUtility.rollback();
				throw new Exception();
			}

		} catch (Exception exception) {
			logger.debug("Exception in updateAdjudicationFlag" + exception);
			return false;
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return boo;

	}

	/**
	 * for getting all relationships
	 * 
	 * @return ArrayList
	 */
	public ArrayList getRelationshipList(String gender, String languageLocale) {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			// sql = RegCommonSQL.GET_RELATIONSHIP_LIST;
			ArrayList list;
			if(gender!=null){
			sql = RegCommonSQL.GET_RELATIONSHIP_LIST_HINDI;
			dbUtility.createPreparedStatement(sql);
			String param[] = { gender };
			 list = dbUtility.executeQuery(param);
			}else{
				sql = RegCommonSQL.GET_RELATIONSHIP_LIST_HINDI_ALL;
				dbUtility.createStatement();
				 list = dbUtility.executeQuery(sql);
			}
			
			
			
			
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				if (languageLocale
						.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					dto.setName(subList.get(1).toString());
				} else {
					dto.setName(subList.get(2).toString());
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO:getRelationshipList in dao start"
					+ e.getStackTrace());
		} finally {
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
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getRelationshipName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_RELATIONSHIP_NAME;
			} else {
				sql = RegCommonSQL.GET_RELATIONSHIP_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getRelationshipName-RegCommonDAO"
					+ exception);
		} finally {
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
	 * 
	 * @author ROOPAM
	 * @date 22 October 2013
	 * @return ArrayList
	 */
	public ArrayList getExecutantClaimant(String languageLocale,int instId, String govtOffcl) {

		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			// sql = RegCommonSQL.GET_EXECUTANT_CLAIMANT_LIST;
			if(govtOffcl.equalsIgnoreCase("3")){
				sql = RegCommonSQL.GET_EXECUTANT_CLAIMANT_LIST_GOVT_OFFCL_HINDI;
			 }else{
			 sql = RegCommonSQL.GET_EXECUTANT_CLAIMANT_LIST_HINDI;
			 }
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			// ArrayList mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;

			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				if (languageLocale
						.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					dto.setName(subList.get(1).toString());
				} else {
					dto.setName(subList.get(2).toString());
				}
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO : getExecutantClaimant in dao start"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * for getting ExecutantClaimant name from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExecutantClaimantName(String id) throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			sql = RegCommonSQL.GET_EXECUTANT_CLAIMANT_NAME;
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out
					.println("Exception in getExecutantClaimantName-RegCommonDAO"
							+ exception);
		} finally {
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
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getUnitName(String unitId, String languageLocale)
			throws Exception {
		String proofname = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { unitId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_UNIT_NAME;
			} else {
				sql = RegCommonSQL.GET_UNIT_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			proofname = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getUnitName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return proofname;
	}

	public String getTehsilName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_TEHSIL_NAME;
			} else {
				sql = RegCommonSQL.GET_TEHSIL_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getTehsilName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	public String getAreaTypeName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_AREA_TYPE_NAME;
			} else {
				sql = RegCommonSQL.GET_AREA_TYPE_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getTehsilName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	public String getWardName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_WARD_NAME;
			} else {
				sql = RegCommonSQL.GET_WARD_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getTehsilName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	public String getMohallaName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_WARD_NAME;
			} else {
				sql = RegCommonSQL.GET_WARD_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getTehsilName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	public String getPropertyL1Name(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_L1_NAME;
			} else {
				sql = RegCommonSQL.GET_L1_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getPropertyL1Name-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	public String getPropertyL2Name(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_L2_NAME;
			} else {
				sql = RegCommonSQL.GET_L2_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getPropertyL2Name-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	public String getFloorName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_FLOOR_NAME;
			} else {
				sql = RegCommonSQL.GET_FLOOR_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getFloorName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	public String getAppleteName(String id, String languageLocale)
			throws Exception {
		String name = "";
		try {
			dbUtility = new DBUtility();
			String[] param = { id };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.GET_APPLETE_NAME;
			} else {
				sql = RegCommonSQL.GET_APPLETE_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);
		} catch (Exception exception) {
			System.out.println("Exception in getAppleteName-RegCommonDAO"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return name;
	}

	/*
	 * public String getAdjuRemarks(String id) throws Exception { String
	 * remarks=""; try { dbUtility = new DBUtility(); String[] param={id};
	 * 
	 * sql=RegCommonSQL.GET_ADJU_REMARKS;
	 * 
	 * dbUtility.createPreparedStatement(sql); remarks =
	 * dbUtility.executeQry(param); } catch (Exception exception) {
	 * System.out.println("Exception in getAdjuRemarks-RegCommonDAO" +
	 * exception); }finally{ try { dbUtility.closeConnection(); } catch
	 * (Exception e) { logger.error("RegCommonDAO in dao start" +
	 * e.getStackTrace()); } } return remarks; }
	 */
	/**
	 * for getting office name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getOfficeName(String ofcId, String languageLocale)
			throws Exception {

		// int regTxnIdSeq = 0;
		String name = "";

		try {
			dbUtility = new DBUtility();
			/*
			 * dbUtility.createStatement();
			 * sql=RegCommonSQL.GET_DISTRICT_NAME+"'"+distId+"'"; distname =
			 * dbUtility.executeQry(sql);
			 */

			String[] param = { ofcId };
			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				sql = RegCommonSQL.OFFICE_NAME;
			} else {
				sql = RegCommonSQL.OFFICE_NAME_HINDI;
			}
			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out.println("Exception in getOfficeName-RegCommonDAO"
					+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return name;

	}

	/**
	 * for getLatestPropertyType from db.
	 * 
	 * @param String
	 *            regTxnId
	 * @return String
	 * @author ROOPAM
	 */
	public String getLatestPropertyType(String regTxnId) throws Exception {

		String name = "";

		try {
			dbUtility = new DBUtility();

			String[] param = { regTxnId, regTxnId };

			sql = RegCommonSQL.GET_LATEST_PROPERTY_TYPE;

			dbUtility.createPreparedStatement(sql);
			name = dbUtility.executeQry(param);

		} catch (Exception exception) {

			System.out
					.println("Exception in getLatestPropertyType-RegCommonDAO"
							+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return name;

	}

	/**
	 * getPropIdsExchangeAgriDisp for creating display grid
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropIdsExchangeAgriDisp(String regTxnId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { regTxnId };
			String query = RegCommonSQL.GET_PROPERTY_IDS_EXCHANGE_AGRI_DISP;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropIdsExchangeAgriDisp" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return list;

	}

	/**
	 * getInstrumentFlag for creating display grid
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getInstrumentFlag(String instID) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { instID };
			String query = RegCommonSQL.GET_INSTRUMENT_FLAGS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getInstrumentFlag" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return list;

	}

	public ArrayList getAllPartiesProperties(String regTxnID, int flag)
			throws Exception {
		ArrayList listParty = new ArrayList();
		ArrayList listProperty = new ArrayList();
		ArrayList listCombine = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { regTxnID };
			String query;
			if (flag == 0) {
				query = RegCommonSQL.GET_ALL_PARTIES;
			} else {
				query = RegCommonSQL.GET_ALL_PARTIES_FOR_MAP;
			}

			dbUtility.createPreparedStatement(query);
			listParty = dbUtility.executeQuery(param);
			dbUtility.commit();
			logger.debug("first party details from db line no 11092");

			query = RegCommonSQL.GET_ALL_PROPERTIES;
			dbUtility.createPreparedStatement(query);
			listProperty = dbUtility.executeQuery(param);
			dbUtility.commit();
			
			logger.debug("porperty details from db line no 11092");
			
			listCombine.add(listParty);
			listCombine.add(listProperty);

		} catch (Exception exception) {
			logger.debug("Exception in getAllPartiesProperties" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listCombine;

	}

	public boolean insertPartyPropMapAll(HashMap map, RegCommonForm form)
			throws Exception {
		ArrayList listParty = new ArrayList();
		ArrayList listPartyRow = new ArrayList();
		// ArrayList listProperty=new ArrayList();
		// ArrayList listCombine=new ArrayList();

		String[] param = new String[4];

		boolean insertSuccess = false;
		try {
			dbUtility = new DBUtility();
			if (map != null && map.isEmpty() == false) {

				Set keys = map.keySet();

				if (keys != null && keys.isEmpty() == false) {

					Iterator keyIterate = keys.iterator();

					while (keyIterate.hasNext()) {

						String currentKey = keyIterate.next().toString();
						listParty = (ArrayList) map.get(currentKey);

						if (listParty != null && listParty.size() > 0) {

							for (int i = 0; i < listParty.size(); i++) {

								listPartyRow = (ArrayList) listParty.get(i);

								if (listPartyRow != null
										&& listPartyRow.size() > 0) {

									// for(int j=0;j<listPartyRow.size();j++){

									param[0] = form.getHidnRegTxnId();
									param[1] = currentKey;
									param[2] = listPartyRow.get(0).toString();
									param[3] = form.getHidnUserId();

									String query = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
									dbUtility.createPreparedStatement(query);
									insertSuccess = dbUtility
											.executeUpdate(param);
									dbUtility.commit();
									if (!insertSuccess) {
										dbUtility.rollback();
										break;
									}

									// }

								} else {
									insertSuccess = false;
								}

							}

						} else {
							insertSuccess = false;
						}

					}

					if (insertSuccess) {

						// insert new status in db
						String[] statusParam = {
								RegInitConstant.STATUS_BACKED_MAPPING_SAVED,
								form.getHidnRegTxnId() };

						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						insertSuccess = dbUtility.executeUpdate(statusParam);
						if (insertSuccess) {
							dbUtility.commit();
						} else {
							dbUtility.rollback();
							throw new Exception();
						}

						// dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else {
					insertSuccess = false;
				}

			}

		} catch (Exception exception) {
			insertSuccess = false;
			logger.debug("Exception in insertPartyPropMapAll", exception);
		} finally {

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}

			return insertSuccess;
		}

	}

	public ArrayList getAllProperties(String regTxnID) throws Exception {
		// ArrayList listParty=new ArrayList();
		ArrayList listProperty = new ArrayList();
		// ArrayList listCombine=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { regTxnID };

			String query = RegCommonSQL.GET_ALL_PROPERTIES;
			dbUtility.createPreparedStatement(query);
			listProperty = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAllProperties" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listProperty;

	}

	public boolean insertPartyParticularMap(HashMap map, RegCommonForm form)
			throws Exception {
		ArrayList listParty = new ArrayList();
		ArrayList listPartyRow = new ArrayList();
		CommonDTO dto;
		String query = "";
		// ArrayList listProperty=new ArrayList();
		// ArrayList listCombine=new ArrayList();

		String[] param = new String[4];
		String[] keyArr = new String[2];
		String particularId;

		boolean insertSuccess = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			if (map != null && map.isEmpty() == false) {

				Set keys = map.keySet();

				if (keys != null && keys.isEmpty() == false) {

					Iterator keyIterate = keys.iterator();

					while (keyIterate.hasNext()) {

						String currentKey = keyIterate.next().toString();
						listParty = (ArrayList) map.get(currentKey);

						keyArr = currentKey.split(",");
						particularId = keyArr[0].trim();

						if (listParty != null && listParty.size() > 0) {

							for (int i = 0; i < listParty.size(); i++) {

								dto = (CommonDTO) listParty.get(i);

								if (dto != null) {

									// for(int j=0;j<listPartyRow.size();j++){

									// if(saveMappingFlag==1){

									if (dto.getPartyCheck().equalsIgnoreCase(
											"Y")) {

										param[0] = form.getHidnRegTxnId();
										param[1] = particularId;
										param[2] = dto.getId();
										param[3] = form.getHidnUserId();
										// param[4]=dto.getShareOfProp();

										query = RegCommonSQL.INSERT_REG_PATICULAR_TRANS_MAP;
										dbUtility
												.createPreparedStatement(query);
										insertSuccess = dbUtility
												.executeUpdate(param);

										if (!insertSuccess) {
											dbUtility.rollback();
											break;
										}

									}

									// }
									/*
									 * else{
									 * 
									 * param[0]=dto.getShareOfProp();
									 * param[1]=form.getHidnUserId();
									 * param[2]=form.getHidnRegTxnId();
									 * param[3]=currentKey;
									 * param[4]=dto.getId();
									 * 
									 * query=RegCommonSQL.
									 * UPDATE_REG_PROP_TRANS_MAP_WITH_SHARES;
									 * dbUtility.createPreparedStatement(query);
									 * insertSuccess =
									 * dbUtility.executeUpdate(param);
									 * 
									 * if(!insertSuccess){ dbUtility.rollback();
									 * break; } }
									 */

									// }

								} else {
									insertSuccess = false;
								}

							}

						} else {
							insertSuccess = false;
						}

					}

					if (insertSuccess) {

						// insert new status in db
						String[] statusParam = {
								RegInitConstant.STATUS_SHARES_SAVED,
								form.getHidnRegTxnId() };

						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						insertSuccess = dbUtility.executeUpdate(statusParam);
						if (insertSuccess) {
							dbUtility.commit();
						} else {
							dbUtility.rollback();
							throw new Exception();
						}

						// dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else {
					insertSuccess = false;
				}

			}

		} catch (Exception exception) {
			insertSuccess = false;
			logger.debug("Exception in updatePartyPropShares", exception);
		} finally {

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}

			return insertSuccess;
		}

	}

	public boolean updatePartyPropShares(HashMap map, RegCommonForm form,
			int saveMappingFlag) throws Exception {
		ArrayList listParty = new ArrayList();
		ArrayList listPartyRow = new ArrayList();
		CommonDTO dto;
		String query = "";
		// ArrayList listProperty=new ArrayList();
		// ArrayList listCombine=new ArrayList();

		String[] param = new String[5];

		boolean insertSuccess = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			if (map != null && map.isEmpty() == false) {

				Set keys = map.keySet();

				if (keys != null && keys.isEmpty() == false) {

					Iterator keyIterate = keys.iterator();

					while (keyIterate.hasNext()) {

						String currentKey = keyIterate.next().toString();
						listParty = (ArrayList) map.get(currentKey);

						if (listParty != null && listParty.size() > 0) {

							for (int i = 0; i < listParty.size(); i++) {

								dto = (CommonDTO) listParty.get(i);

								if (dto != null) {

									// for(int j=0;j<listPartyRow.size();j++){

									if (saveMappingFlag == 1) {

										if (dto.getPartyCheck()
												.equalsIgnoreCase("Y")) {

											param[0] = form.getHidnRegTxnId();
											param[1] = currentKey;
											param[2] = dto.getId();
											param[3] = form.getHidnUserId();
											param[4] = dto.getShareOfProp();

											query = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP_WITH_SHARES;
											dbUtility
													.createPreparedStatement(query);
											insertSuccess = dbUtility
													.executeUpdate(param);

											if (!insertSuccess) {
												dbUtility.rollback();
												break;
											}

										}

									} else {

										param[0] = dto.getShareOfProp();
										param[1] = form.getHidnUserId();
										param[2] = form.getHidnRegTxnId();
										param[3] = currentKey;
										param[4] = dto.getId();

										query = RegCommonSQL.UPDATE_REG_PROP_TRANS_MAP_WITH_SHARES;
										dbUtility
												.createPreparedStatement(query);
										insertSuccess = dbUtility
												.executeUpdate(param);

										if (!insertSuccess) {
											dbUtility.rollback();
											break;
										}
									}

									// }

								} else {
									insertSuccess = false;
								}

							}

						} else {
							insertSuccess = false;
						}

					}

					if (insertSuccess) {

						// insert new status in db
						String[] statusParam = {
								RegInitConstant.STATUS_SHARES_SAVED,
								form.getHidnRegTxnId() };

						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						insertSuccess = dbUtility.executeUpdate(statusParam);
						if (insertSuccess) {
							dbUtility.commit();
						} else {
							dbUtility.rollback();
							throw new Exception();
						}

						// dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else {
					insertSuccess = false;
				}

			}

		} catch (Exception exception) {
			insertSuccess = false;
			logger.debug("Exception in updatePartyPropShares", exception);
		} finally {

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}

			return insertSuccess;
		}

	}
	
	public String getDutyId(String tempEfileId){
		
		String dutyId="";
        try{
              dbUtility = new DBUtility();
              String[] param = new String[1];
              param[0] = tempEfileId;
              sql = RegCommonSQL.GET_DUTY_ID;
              dbUtility.createPreparedStatement(sql);
              dutyId=dbUtility.executeQry(param);
              
              
             
        }
        catch (Exception exception){
              System.out.println("Exception in getdutyid-RegCommonDAO"
                          + exception);
        }
        finally {
              try {
                    dbUtility.closeConnection();
              } catch (Exception e) {
                    logger.error("RegCommonDAO in dao start" + e.getStackTrace());
              }
        }
        return dutyId;
		
	}
	
public String getDutyId1(String TempId){
		
		String dutyId="";
        try{
              dbUtility = new DBUtility();
              String[] param = new String[1];
              param[0] = TempId;
              sql = RegCommonSQL.GET_DUTY_ID;
              dbUtility.createPreparedStatement(sql);
              dutyId=dbUtility.executeQry(param);
              
              
             
        }
        catch (Exception exception){
              System.out.println("Exception in getdutyid-RegCommonDAO"
                          + exception);
        }
        finally {
              try {
                    dbUtility.closeConnection();
              } catch (Exception e) {
                    logger.error("RegCommonDAO in dao start" + e.getStackTrace());
              }
        }
        return dutyId;
		
	}
	
	
	public String getDeedId(String dutyId){
		
		String dutyId3="";
        try{
              dbUtility = new DBUtility();
              String[] param = new String[1];
              param[0] = dutyId;
              sql = RegCommonSQL.GET_DUTY_ID1;
              dbUtility.createPreparedStatement(sql);
              dutyId3=dbUtility.executeQry(param);
              
              
             
        }
        catch (Exception exception){
              System.out.println("Exception in getdutyid-RegCommonDAO"
                          + exception);
        }
        finally {
              try {
                    dbUtility.closeConnection();
              } catch (Exception e) {
                    logger.error("RegCommonDAO in dao start" + e.getStackTrace());
              }
        }
        return dutyId3;
		
	}
	
	
	public int getInstId(String dutyId){
		
		int dutyId1=0;
        try{
              
              
              dbUtility = new DBUtility();
      		String param[]={dutyId};
      		String SQL = RegCommonSQL.GET_INST_ID1;
      		dbUtility.createPreparedStatement(SQL);
      		String page=dbUtility.executeQry(param);
      		dutyId1=Integer.parseInt(page);
              
             
        }
        catch (Exception exception){
              System.out.println("Exception in getdutyid-RegCommonDAO"
                          + exception);
        }
        finally {
              try {
                    dbUtility.closeConnection();
              } catch (Exception e) {
                    logger.error("RegCommonDAO in dao start" + e.getStackTrace());
              }
        }
        return dutyId1;
		
	}
	
	public String getExecDate(String dutyId){
		
		String execDate="";
        try{
              dbUtility = new DBUtility();
              String[] param = new String[1];
              param[0] = dutyId;
              sql = RegCommonSQL.GET_EXECUTION_DATE;
              dbUtility.createPreparedStatement(sql);
              execDate=dbUtility.executeQry(param);
              
              
             
        }
        catch (Exception exception){
              System.out.println("Exception in getexecutionDate-RegCommonDAO"
                          + exception);
        }
        finally {
              try {
                    dbUtility.closeConnection();
              } catch (Exception e) {
                    logger.error("RegCommonDAO in dao start" + e.getStackTrace());
              }
        }
        return execDate;
		
	}
	

	public boolean updatePartyPropShares(HashMap map, RegCompletionForm form)
			throws Exception {
		ArrayList listParty = new ArrayList();
		ArrayList listPartyRow = new ArrayList();
		CommonDTO dto;
		// ArrayList listProperty=new ArrayList();
		// ArrayList listCombine=new ArrayList();

		String[] param = new String[5];

		boolean insertSuccess = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			if (map != null && map.isEmpty() == false) {

				Set keys = map.keySet();

				if (keys != null && keys.isEmpty() == false) {

					Iterator keyIterate = keys.iterator();

					while (keyIterate.hasNext()) {

						String currentKey = keyIterate.next().toString();
						listParty = (ArrayList) map.get(currentKey);

						if (listParty != null && listParty.size() > 0) {

							for (int i = 0; i < listParty.size(); i++) {

								dto = (CommonDTO) listParty.get(i);

								if (dto != null) {

									// for(int j=0;j<listPartyRow.size();j++){

									param[0] = dto.getShareOfProp();
									param[1] = form.getUserID();
									param[2] = form.getHidnRegTxnId();
									param[3] = currentKey;
									param[4] = dto.getId();

									String query = RegCommonSQL.UPDATE_REG_PROP_TRANS_MAP_WITH_SHARES;
									dbUtility.createPreparedStatement(query);
									insertSuccess = dbUtility
											.executeUpdate(param);

									if (!insertSuccess) {
										dbUtility.rollback();
										break;
									}

									// }

								} else {
									insertSuccess = false;
								}

							}

						} else {
							insertSuccess = false;
						}

					}

					if (insertSuccess) {

						// insert new status in db
						String[] statusParam = {
								RegInitConstant.STATUS_SHARES_SAVED,
								form.getHidnRegTxnId() };

						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						insertSuccess = dbUtility.executeUpdate(statusParam);
						if (insertSuccess) {
							dbUtility.commit();
						} else {
							dbUtility.rollback();
							throw new Exception();
						}

						// dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else {
					insertSuccess = false;
				}

			}

		} catch (Exception exception) {
			insertSuccess = false;
			logger.debug("Exception in updatePartyPropShares", exception);
		} finally {

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}

			return insertSuccess;
		}

	}

	/**
	 * getAllParticular for fetching particular count
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getAllParticular(String regTxnID) throws Exception {

		ArrayList listProperty = new ArrayList();

		try {
			dbUtility = new DBUtility();
			String[] param = { regTxnID };

			String query = RegCommonSQL.GET_ALL_PARTICULAR;
			dbUtility.createPreparedStatement(query);
			listProperty = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAllParticular" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listProperty;

	}

	/**
	 * getAllPartiesParticulars for fetching particular details
	 * 
	 * @param String
	 *            ,int
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getAllPartiesParticulars(String regTxnID, int flag)
			throws Exception {
		ArrayList listParty = new ArrayList();
		ArrayList listProperty = new ArrayList();
		ArrayList listCombine = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { regTxnID };
			String query;
			if (flag == 0) {
				query = RegCommonSQL.GET_ALL_PARTIES;
			} else {
				query = RegCommonSQL.GET_ALL_PARTIES_FOR_MAP;
			}

			dbUtility.createPreparedStatement(query);
			listParty = dbUtility.executeQuery(param);

			query = RegCommonSQL.GET_ALL_PARTICULAR;
			dbUtility.createPreparedStatement(query);
			listProperty = dbUtility.executeQuery(param);

			listCombine.add(listParty);
			listCombine.add(listProperty);

		} catch (Exception exception) {
			logger.debug("Exception in getAllPartiesParticulars" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listCombine;

	}

	/**
	 * insertPartyPropMapAll for inserting particular mapping details
	 * 
	 * @param map
	 *            ,form
	 * @return boolean
	 * @author SHREERAJ
	 */
	public boolean insertPartyParticularMapAll(HashMap map, RegCommonForm form)
			throws Exception {
		ArrayList listParty = new ArrayList();
		ArrayList listPartyRow = new ArrayList();
		// ArrayList listProperty=new ArrayList();
		// ArrayList listCombine=new ArrayList();

		String[] param = new String[4];

		boolean insertSuccess = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			if (map != null && map.isEmpty() == false) {

				Set keys = map.keySet();

				if (keys != null && keys.isEmpty() == false) {

					Iterator keyIterate = keys.iterator();

					while (keyIterate.hasNext()) {

						String currentKey = keyIterate.next().toString();
						listParty = (ArrayList) map.get(currentKey);

						if (listParty != null && listParty.size() > 0) {

							for (int i = 0; i < listParty.size(); i++) {

								listPartyRow = (ArrayList) listParty.get(i);

								if (listPartyRow != null
										&& listPartyRow.size() > 0) {

									// for(int j=0;j<listPartyRow.size();j++){

									param[0] = form.getHidnRegTxnId();
									param[1] = currentKey;
									param[2] = listPartyRow.get(0).toString();
									param[3] = form.getHidnUserId();

									String query = RegCommonSQL.INSERT_REG_PATICULAR_TRANS_MAP;
									dbUtility.createPreparedStatement(query);
									insertSuccess = dbUtility
											.executeUpdate(param);

									if (!insertSuccess) {
										dbUtility.rollback();
										break;
									}

									// }

								} else {
									insertSuccess = false;
								}

							}

						} else {
							insertSuccess = false;
						}

					}

					if (insertSuccess) {

						// insert new status in db
						String[] statusParam = {
								RegInitConstant.STATUS_SHARES_SAVED,
								form.getHidnRegTxnId() };

						sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
						dbUtility.createPreparedStatement(sql);
						insertSuccess = dbUtility.executeUpdate(statusParam);
						if (insertSuccess) {
							dbUtility.commit();
						} else {
							dbUtility.rollback();
							throw new Exception();
						}

						// dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else {
					insertSuccess = false;
				}

			}

		} catch (Exception exception) {
			insertSuccess = false;
			logger.debug("Exception in insertPartyPropMapAll", exception);
		} finally {

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}

			return insertSuccess;
		}

	}

	/**
	 * getTransPartyDetsParticular for getting transacting party details
	 * corresponding to a PARTICULAR ID from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getTransPartyDetsParticular(String propId, String appId)
			throws Exception {
		ArrayList list = new ArrayList();
		try {
			String param[] = new String[2];
			param[0] = appId;
			param[1] = propId;
			dbUtility = new DBUtility();
			String query = RegCommonSQL.GET_TRANSACTING_PARTY_DETS_PARTICULAR;
			dbUtility.createPreparedStatement(query);

			list = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger
					.debug("Exception in getTransPartyDetsParticular"
							+ exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return list;

	}

	public boolean insertPartyPropMapAll(HashMap map, RegCompletionForm form)
			throws Exception {
		ArrayList listParty = new ArrayList();
		ArrayList listPartyRow = new ArrayList();
		// ArrayList listProperty=new ArrayList();
		// ArrayList listCombine=new ArrayList();

		String[] param = new String[4];

		boolean insertSuccess = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			if (map != null && map.isEmpty() == false) {

				Set keys = map.keySet();

				if (keys != null && keys.isEmpty() == false) {

					Iterator keyIterate = keys.iterator();

					while (keyIterate.hasNext()) {

						String currentKey = keyIterate.next().toString();
						listParty = (ArrayList) map.get(currentKey);

						if (listParty != null && listParty.size() > 0) {

							for (int i = 0; i < listParty.size(); i++) {

								listPartyRow = (ArrayList) listParty.get(i);

								if (listPartyRow != null
										&& listPartyRow.size() > 0) {

									// for(int j=0;j<listPartyRow.size();j++){

									param[0] = form.getHidnRegTxnId();
									param[1] = currentKey;
									param[2] = listPartyRow.get(0).toString();
									param[3] = form.getUserID();

									String query = RegCommonSQL.INSERT_REG_PROP_TRANS_MAP;
									dbUtility.createPreparedStatement(query);
									insertSuccess = dbUtility
											.executeUpdate(param);
									dbUtility.commit();
									if (!insertSuccess) {
										dbUtility.rollback();
										break;
									}

									// }

								} else {
									insertSuccess = false;
								}

							}

						} else {
							insertSuccess = false;
						}

					}

					if (insertSuccess) {
						dbUtility.commit();
					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else {
					insertSuccess = false;
				}

			}

		} catch (Exception exception) {
			insertSuccess = false;
			logger.debug("Exception in insertPartyPropMapAll", exception);
		} finally {

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}

			return insertSuccess;
		}

	}

	public String getPartyType1Or2(int deedId, int instId, int roleId)
			throws Exception {
		ArrayList list = new ArrayList();
		String partyType1or2 = "";
		try {
			String param[] = new String[2];

			param[0] = Integer.toString(deedId);
			param[1] = Integer.toString(roleId);

			String query = RegCommonSQL.GET_PARTY_TYPE_1_OR_2_DEED_BASED;

			if (instId == RegInitConstant.INST_DISSOLUTION_NPV
					|| instId == RegInitConstant.INST_DISSOLUTION_2 
					|| instId == RegInitConstant.INST_BUILDER_1
					|| instId == RegInitConstant.INST_BUILDER_2
					|| instId == RegInitConstant.INST_AUTHENTICATE_POA) {
				param[0] = Integer.toString(instId);
				query = RegCommonSQL.GET_PARTY_TYPE_1_OR_2_INST_BASED;
			}

			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(query);

			partyType1or2 = dbUtility.executeQry(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPartyType1Or2" + exception);
		} finally {
			try {

				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				// ex.printStackTrace();
			}
		}
		return partyType1or2;

	}

	public ArrayList getAllParties(String regTxnID) throws Exception {
		// ArrayList listParty=new ArrayList();
		ArrayList listProperty = new ArrayList();
		// ArrayList listCombine=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { regTxnID };

			String query = RegCommonSQL.GET_ALL_PARTIES;
			dbUtility.createPreparedStatement(query);
			listProperty = dbUtility.executeQuery(param);
			dbUtility.commit();
			logger.debug("Reg Common DAO line 12089 where party count is coming from DB ");

		} catch (Exception exception) {
			logger.debug("Exception in getAllProperties" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listProperty;

	}

	public ArrayList getAllExemptions(String dutyTxnId) throws Exception {
		// ArrayList listParty=new ArrayList();
		ArrayList listExem = new ArrayList();
		// ArrayList listCombine=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { dutyTxnId };

			String query = RegCommonSQL.GET_ALL_EXEMPTIONS;
			dbUtility.createPreparedStatement(query);
			listExem = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAllExemptions" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listExem;

	}

	public ArrayList getAllUserEnterables(String dutyTxnId,
			String languageLocale) throws Exception {

		ArrayList listExem = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { dutyTxnId };
			String query = "";

			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				query = RegCommonSQL.GET_USER_ENTERABLE;
			} else {
				query = RegCommonSQL.GET_USER_ENTERABLE_HI;
			}
			dbUtility.createPreparedStatement(query);
			listExem = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAllUserEnterables" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listExem;

	}

	public ArrayList getPropWiseUserEnterables(String dutyTxnId,
			String propDutyId, String languageLocale) throws Exception {

		ArrayList listExem = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { dutyTxnId, propDutyId };
			String query = "";

			if (languageLocale
					.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				query = RegCommonSQL.GET_PROP_WISE_USER_ENTERABLE;
			} else {
				query = RegCommonSQL.GET_PROP_WISE_USER_ENTERABLE_HI;
			}
			dbUtility.createPreparedStatement(query);
			listExem = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropWiseUserEnterables" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listExem;

	}

	public ArrayList getPropertiesWithDuties(String regTxnID, String dutyTxnId)
			throws Exception {

		ArrayList listProperty = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { dutyTxnId, regTxnID };

			String query = RegCommonSQL.GET_PROP_WISE_DUTY;
			dbUtility.createPreparedStatement(query);
			listProperty = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getPropertiesWithDuties" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listProperty;

	}
	
	public ArrayList getConsenterWithDuties(String regTxnID)
	throws Exception {

ArrayList listProperty = new ArrayList();
try {
	dbUtility = new DBUtility();
	String[] param = { regTxnID };

	String query = RegCommonSQL.GET_CONSENTER_WISE_DUTY;
	dbUtility.createPreparedStatement(query);
	listProperty = dbUtility.executeQuery(param);

} catch (Exception exception) {
	logger.debug("Exception in getConsenterWithDuties" + exception);
} finally {
	try {
		dbUtility.closeConnection();
	} catch (Exception e) {
		logger.error("RegCommonDAO in dao start" + e.getStackTrace());
	}
}
return listProperty;

}

	public ArrayList getAllPropertiesWithType(String regTxnID) throws Exception {

		ArrayList listProperty = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { regTxnID };

			String query = RegCommonSQL.GET_ALL_PROPERTIES_WITH_TYPE;
			dbUtility.createPreparedStatement(query);
			listProperty = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getAllPropertiesWithType" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listProperty;

	}

	public ArrayList getPropertyType(String language) {
		ArrayList propertyList = null;

		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			if (language.equalsIgnoreCase("en")) {
				propertyList = dbUtility
						.executeQuery(RegCommonSQL.PROPERTY_TYPE_NAME_EN);
			} else {
				propertyList = dbUtility
						.executeQuery(RegCommonSQL.PROPERTY_TYPE_NAME_HI);
			}

			return propertyList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public ArrayList getArea(String language) {
		ArrayList areaList = null;

		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			if (language.equalsIgnoreCase("en")) {
				areaList = dbUtility.executeQuery(RegCommonSQL.AREA_NAME_EN);
			} else {
				areaList = dbUtility.executeQuery(RegCommonSQL.AREA_NAME_HI);
			}

			return areaList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}

	public ArrayList getTehsil(String language, String districtId) {

		ArrayList tehsilList = null;

		try {
			dbUtility = new DBUtility();
			if (language.equalsIgnoreCase("en")) {
				dbUtility.createPreparedStatement(RegCommonSQL.TEHSIL_NAME_EN);
			} else {
				dbUtility.createPreparedStatement(RegCommonSQL.TEHSIL_NAME_HI);
			}
			tehsilList = dbUtility.executeQuery(new String[] { districtId });

		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return tehsilList;

	}

	public ArrayList getCancellationLabel(String dutyTxnID) throws Exception {

		ArrayList listProperty = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String[] param = { dutyTxnID };

			String query = RegCommonSQL.GET_CANCELLATION_LABEL;
			dbUtility.createPreparedStatement(query);
			listProperty = dbUtility.executeQuery(param);

		} catch (Exception exception) {
			logger.debug("Exception in getCancellationLabel" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return listProperty;

	}

	public boolean insrtPropDetlsNonPVDeeds( ArrayList propDetls) throws Exception {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			CommonDTO row_list;
			String[] param1 = new String[19];
					

						// BELOW CODE FOR INSERTION OF PROPERTY DETAILS

						sql = RegCommonSQL.INSERT_REG_PROPERTY_DETLS;
						dbUtility.createPreparedStatement(sql);

						

							row_list = (CommonDTO) propDetls.get(0);
							param1[0] = row_list.getPropertyId();
							param1[1] = row_list.getRegTxnId();
							param1[2] = row_list.getMarketValue();
							// param1[2]=""; // "" is passed because final
							// market value is not being stored in db from pv
							param1[3] = row_list.getAreaTypeId();
							param1[4] = row_list.getGovBodyId();
							param1[5] = row_list.getPropTypeId();
							param1[6] = row_list.getL1TypeId();
							param1[7] = row_list.getL2TypeId();
							param1[8] = row_list.getAreaUnitId();
							if(row_list.getAreaEfile()==null)
								param1[9] = null;
							else
							param1[9] = row_list.getAreaEfile().toString();
							param1[10] = row_list.getDistId();
							param1[11] = row_list.getTehsilId();
							param1[12] = row_list.getWardId();
							param1[13] = row_list.getMohalaId();
							param1[14] = row_list.getUserId();
							param1[15] = row_list.getValuationId();
							param1[16] = "N"; // PROP_TXN_COMPL_FLAG
							param1[17] = row_list.getMarketValue(); // INITIAL_MARKET_VALUE
							// param1[17]=""; //INITIAL_MARKET_VALUE
							param1[18] = row_list.getSysMv(); // SYSTEM
																// MARKET_VALUE
                          
							boo = dbUtility.executeUpdate(param1); // loop
							dbUtility.commit();
							logger.debug("Insertion commited in DAO line no 11968 " );
							if (boo){
								
								
								
								
								String[] statusParam = {
										RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS,
										param1[1] };

								sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
								dbUtility.createPreparedStatement(sql);
								boo = dbUtility.executeUpdate(statusParam);
								
								
								
								
								
								
							if(boo)	{
								dbUtility.commit();
							}else{
								dbUtility.rollback();
								throw new Exception();
							}
							
							
							
							}else{
								dbUtility.rollback();
								throw new Exception();
							}
								

						

						

					
				
				
			
		} catch (Exception e) {
			try {
				dbUtility.rollback();
			} finally {
				logger.debug(e.getMessage(),e);
			}
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
	}
	
	public String getRegFeeCheck(String reg_txn_id){
		String ret = "N";
		try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(RegCommonSQL.CHECK_REG_FEE_PAY_FLAG);
			ret=dbUtility.executeQry(new String[]{reg_txn_id});
		}
		catch(Exception e){
			
		}
		finally{

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		
		}
		return ret;
	}
	
	public ArrayList checkFeeCompletFlag(String reg_txn_id){
		ArrayList ret=new ArrayList();
		try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(RegCommonSQL.CHECK_REG_FEE_COMPLT_PAY_FLAG);
			ret=dbUtility.executeQuery(new String[]{reg_txn_id});
		}
		catch(Exception e){
			
		}
		finally{

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return ret;
	}

	public boolean insertAdditionalUploads(RegCommonForm regForm) {
		boolean check=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String SQL = RegCommonSQL.IGRS_INSERT_ADDITIONAL_UPLOADS;
			dbUtility.createPreparedStatement(SQL);
			
			ArrayList<CommonDTO> dtoList = regForm.getListDto();
			for(int i =0; i < dtoList.size() ; i++)
			{
				CommonDTO dto = dtoList.get(i);
				String arr[] = new String[8];
				arr[0] = regForm.getHidnRegTxnId();
				arr[1] = regForm.getPartyRoleTypeId();
				arr[2] = "";
				arr[3] = dto.getDocumentPath();
				arr[4] = dto.getDocumentName();
				arr[5] = "A";
				arr[6] = regForm.getHidnUserId();
				arr[7] = "";
				check = dbUtility.executeUpdate(arr);
				if(!check)
				{
					break;
				}
			}
			if(check)
			{
				dbUtility.commit();
			}else{
				dbUtility.rollback();
				throw new Exception();
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return check;
	}
	
	
	//check payflag
	
	public boolean checkpayflag(String TempIdPay) {
		
		boolean estampflag=false;
        try{
              dbUtility = new DBUtility();
              String[] param = new String[1];
              param[0] = TempIdPay;
              sql = RegCommonSQL.CHECK_PAYMENT_FLAG_CHALLAN;
              dbUtility.createPreparedStatement(sql);
              estampflag=dbUtility. executeUpdate(param);
             if(estampflag){
            	 estampflag=true ;
             }
        }
        catch (Exception exception){
              System.out.println("Exception in getRelationshipName-RegCommonDAO"
                          + exception);
        }
        finally {
              try {
                    dbUtility.closeConnection();
              } catch (Exception e) {
                    logger.error("RegCommonDAO in dao start" + e.getStackTrace());
              }
        }
        return estampflag;
		
	}
	
	
	//end
	
	
public String  getparentKey(String TempIdPay) {
		
		String parentKey="";
        try{
              dbUtility = new DBUtility();
              String[] param = new String[1];
              param[0] = TempIdPay;
              sql = RegCommonSQL.GET_PARENT_KEY;
              dbUtility.createPreparedStatement(sql);
              parentKey=dbUtility.executeQry(param);
              
              
             
        }
        catch (Exception exception){
              System.out.println("Exception in getRelationshipName-RegCommonDAO"
                          + exception);
        }
        finally {
              try {
                    dbUtility.closeConnection();
              } catch (Exception e) {
                    logger.error("RegCommonDAO in dao start" + e.getStackTrace());
              }
        }
        return parentKey;
		
	}
	
	public boolean insertAdditionalUploadsConsenter(RegCommonForm regForm) {
		boolean check=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String SQL = RegCommonSQL.IGRS_INSERT_ADDITIONAL_UPLOADS;
			dbUtility.createPreparedStatement(SQL);
			
			ArrayList<CommonDTO> dtoList = regForm.getListDto();
			for(int i =0; i < dtoList.size() ; i++)
			{
				CommonDTO dto = dtoList.get(i);
				String arr[] = new String[8];
				arr[0] = regForm.getHidnRegTxnId();
				arr[1] = "";
				arr[2] = "";
				arr[3] = dto.getDocumentPath();
				arr[4] = dto.getDocumentName();
				arr[5] = "A";
				arr[6] = regForm.getHidnUserId();
				arr[7] = regForm.getConsenterId();
				check = dbUtility.executeUpdate(arr);
				if(!check)
				{
					break;
				}
			}
			if(check)
			{
				dbUtility.commit();
			}else{
				dbUtility.rollback();
				throw new Exception();
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return check;
	}
	
	public boolean insertAdditionalUploadsAdju(RegCommonForm regForm) {
		boolean check=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String SQL = RegCommonSQL.IGRS_INSERT_ADDITIONAL_UPLOADS;
			dbUtility.createPreparedStatement(SQL);
			
			ArrayList<CommonDTO> dtoList = regForm.getListDtoAdju();
			for(int i =0; i < dtoList.size() ; i++)
			{
				CommonDTO dto = dtoList.get(i);
				String arr[] = new String[8];
				arr[0] = regForm.getHidnRegTxnId();
				arr[1] = "";
				arr[2] = "";
				arr[3] = dto.getDocumentPath();
				arr[4] = dto.getDocumentName();
				arr[5] = "A";
				arr[6] = regForm.getHidnUserId();
				arr[7] = "";
				check = dbUtility.executeUpdate(arr);
				if(!check)
				{
					break;
				}
			}
			if(check)
			{
				dbUtility.commit();
			}else{
				dbUtility.rollback();
				throw new Exception();
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return check;
	}
	
	
	public boolean insertAdditionalUploads(RegCompletionForm eForm) {
		boolean check=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String SQL = RegCommonSQL.IGRS_INSERT_ADDITIONAL_UPLOADS_PROPERTY;
			dbUtility.createPreparedStatement(SQL);
			
			ArrayList<CommonDTO> dtoList = eForm.getListDtoP();
			for(int i =0; i < dtoList.size() ; i++)
			{
				CommonDTO dto = dtoList.get(i);
				String arr[] = new String[8];
				arr[0] = eForm.getHidnRegTxnId();
				//String propid=eForm.getPropertyId();
			//	int length=propid.length();
				
			//	String propId1 = propid.substring(0, 1);
				
		//	if(propId1.equalsIgnoreCase("0")){
				//arr[1]=propid.substring(1, length);
			//}
			
		//	else{
				arr[1] = eForm.getPropertyId();
		//	}
				
				
				arr[2] = "";
				arr[3] = dto.getDocumentPath();
				arr[4] = dto.getDocumentName();
				arr[5] = "A";
				arr[6] = eForm.getUserID();
				arr[7] = "";
				check = dbUtility.executeUpdate(arr);
				if(!check)
				{
					break;
				}
			}
			if(check)
			{
				dbUtility.commit();
			}else{
				dbUtility.rollback();
				throw new Exception();
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return check;
	}
	
	public boolean insertAdditionalUploads(RegCommonDTO regDto, RegCommonForm regForm) {
		boolean check=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String SQL = RegCommonSQL.IGRS_INSERT_ADDITIONAL_UPLOADS;
			dbUtility.createPreparedStatement(SQL);
			
			ArrayList<CommonDTO> dtoList = regDto.getListDto();
			for(int i =0; i < dtoList.size() ; i++)
			{
				CommonDTO dto = dtoList.get(i);
				String arr[] = new String[8];
				arr[0] = regForm.getHidnRegTxnId();
				arr[1] = regDto.getPartyRoleTypeId();
				arr[2] = "";
				arr[3] = dto.getDocumentPath();
				arr[4] = dto.getDocumentName();
				arr[5] = "A";
				arr[6] = regForm.getHidnUserId();
				arr[7] = "";
				check = dbUtility.executeUpdate(arr);
				if(!check)
				{
					break;
				}
			}
			if(check)
			{
				dbUtility.commit();
			}else{
				dbUtility.rollback();
				throw new Exception();
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return check;
	}
	
	public ArrayList getAdditonalUploadsAdju(String appId) {
		boolean check=false;
		ArrayList listDto = null;
		try {
			dbUtility = new DBUtility();
			
			String SQL = RegCommonSQL.IGRS_GET_ADDITIONAL_UPLOADS_ADJU;
			dbUtility.createPreparedStatement(SQL);
			String param[] = new String[1];
			param[0] = appId;
			
		listDto = 	dbUtility.executeQuery(param);
			
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		return listDto;
		
	}
	
	public ArrayList getDeedDraftId(String appId) {
		boolean check=false;
		ArrayList listDto = null;
		try {
			dbUtility = new DBUtility();
			
			String SQL = RegCommonSQL.GET_DEED_DRAFT_ID;
			dbUtility.createPreparedStatement(SQL);
			String param[] = new String[1];
			param[0] = appId;
			
		listDto = 	dbUtility.executeQuery(param);
			
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		return listDto;
		
	}
	
	
	public ArrayList getAdditonalUploads(String appId, String partyId) {
		boolean check=false;
		ArrayList listDto = null;
		try {
			dbUtility = new DBUtility();
			
			String SQL = RegCommonSQL.IGRS_GET_ADDITIONAL_UPLOADS;
			dbUtility.createPreparedStatement(SQL);
			String param[] = new String[2];
			param[0] = appId;
			param[1] = partyId;
		listDto = 	dbUtility.executeQuery(param);
			
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		return listDto;
		
	}//By Mohit
	
	public ArrayList getAdditonalUploadsConsenter(String appId, String consenterId) {
		boolean check=false;
		ArrayList listDto = null;
		try {
			dbUtility = new DBUtility();
			
			String SQL = RegCommonSQL.IGRS_GET_ADDITIONAL_UPLOADS_CONSENTER;
			dbUtility.createPreparedStatement(SQL);
			String param[] = new String[2];
			param[0] = appId;
			param[1] = consenterId;
		listDto = 	dbUtility.executeQuery(param);
			
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		return listDto;
		
	}
	
	
	public ArrayList getAdditonalUploadsP(String appId, String propId) {
		boolean check=false;
		ArrayList listDto = null;
		ArrayList listDto1 = null;
		try {
			dbUtility = new DBUtility();
			
			String SQL = RegCommonSQL.IGRS_GET_ADDITIONAL_UPLOADS_PROPERTY;
			dbUtility.createPreparedStatement(SQL);
			String param[] = new String[2];
			param[0] = appId;
			
			
			//String propid=propId;
			//int length=propid.length();
			
			//String propId1 = propid.substring(0, 1);
			
		//if(propId1.equalsIgnoreCase("0")){
			//param[1] =propid.substring(1, length);
		//}
		
		//else{
			param[1] = propId;
		//}
		listDto = 	dbUtility.executeQuery(param);
		//changes by preeti 24sept2016 when po/sr end additonal uplaods are not coming 
			/*if(listDto.isEmpty()){
				dbUtility = new DBUtility();
				
				String SQL1 = RegCommonSQL.IGRS_GET_ADDITIONAL_UPLOADS_PROPERTY1;
				dbUtility.createPreparedStatement(SQL1);
				String param1[] = new String[1];
				param1[0] = appId;
				
			listDto = 	dbUtility.executeQuery(param1);
			
			}*/
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		return listDto;
		
	}

	/*public boolean deleteALLRemovedUploads(String hidnRegTxnId,
			String partyTxnId) {
		boolean check = false;
		try {
			dbUtility = new DBUtility();
			String param[] = new String[2];
			String SQL = RegCommonSQL.IGRS_DELETE_REMOVED_UPLOADS;
			param[0] = hidnRegTxnId;
			param[1] = partyTxnId;
			dbUtility.createPreparedStatement(SQL);
		check = dbUtility.executeUpdate(param);
			
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return check;
	}*/

	public void validateDeedDraftId(RegCommonForm reg, String lang){
		try{
			dbUtility = new DBUtility();
			String abc="";
			if(reg.getFromAdjudication()==0){
			dbUtility.createPreparedStatement(RegCommonSQL.CHECK_DEED_DRAFT_ID_EXIST);
			abc=dbUtility.executeQry(new String[]{reg.getDeedDraftId(),reg.getHidnUserId()});
			}else{
				dbUtility.createPreparedStatement(RegCommonSQL.CHECK_DEED_DRAFT_ID_EXIST_ADJU);
				abc=dbUtility.executeQry(new String[]{reg.getDeedDraftId()});
			}
			
			//NEED TO BE SLIGHTLY CHANGED
			
			if(abc!="" && !abc.equalsIgnoreCase("0")){
				dbUtility.createPreparedStatement("SELECT DEED_DRAFT_STATUS FROM IGRS_DEED_DRAFT_TXN_DTLS WHERE DEED_DRAFT_ID=? AND CREATED_BY IS NOT NULL");
				String stat=dbUtility.executeQry(new String[]{reg.getDeedDraftId()});
				if("D".equalsIgnoreCase(stat)){
					reg.setDeedStat("3");
					if("en".equalsIgnoreCase(lang)){
					reg.setErMsg("Already consumed");
					}
					else{
						reg.setErMsg("पहले से ही उपयोग किया गया है");
					}
				}
				else if("I".equalsIgnoreCase(stat)){
					reg.setDeedStat("3");
					if("en".equalsIgnoreCase(lang)){
					reg.setErMsg("Incomplete Deed");
					}
					else{
						reg.setErMsg("डीड अपूर्ण है");
					}
				}
				else{
					
				reg.setDeedStat("2");
				}
			}
			else if(abc.equalsIgnoreCase("0")){
			

				
				reg.setDeedStat("3");
				if("en".equalsIgnoreCase(lang)){
					reg.setErMsg("Master Template cannot be consumed");
					}
					else{
						reg.setErMsg("मास्टर टेम्पलेट का सेवन नहीं किया जा सकता");
					}
			
				
			}else
				{
			
				reg.setDeedStat("3");
				if(reg.getFromAdjudication()==0){
				if("en".equalsIgnoreCase(lang)){
					reg.setErMsg("Invalid Id or deed created by other user cannot be consumed");
					}
					else{
						reg.setErMsg("अवैध आईडी या अन्य उपयोगकर्ता द्वारा बनाई गई डीड का सेवन नहीं किया जा सकता");
					}
				}else{
					if("en".equalsIgnoreCase(lang)){
						reg.setErMsg("Invalid Id.");
						}
						else{
							reg.setErMsg("अवैध आईडी.");
						}
				}
			}
			
		}
		catch(Exception e){
			reg.setDeedStat("3");
			logger.debug(e.getMessage(),e);
		}
		finally{

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		
		}
	}
	public void consumeDeedDraftId(RegCommonForm reg, String lang, HttpServletResponse response) throws Exception{
		try{
			
			boolean boo=true;
			dbUtility = new DBUtility();
			String abc="";
			
			if(reg.getFromAdjudication()==0){
			dbUtility.createPreparedStatement(RegCommonSQL.CHECK_DEED_DRAFT_ID_EXIST_CONSUME);
			abc=dbUtility.executeQry(new String[]{reg.getDeedDraftId(),reg.getHidnUserId()});
			}else{
				dbUtility.createPreparedStatement(RegCommonSQL.CHECK_DEED_DRAFT_ID_EXIST_CONSUME_ADJU);
				abc=dbUtility.executeQry(new String[]{reg.getDeedDraftId()});
			}
			
			
			
			
			
			if(abc==""){
				reg.setDeedStat("3");
			}
			else if("D".equalsIgnoreCase(abc)){
				reg.setDeedStat("1");

				if("en".equalsIgnoreCase(lang)){
					reg.setErMsg("Already consumed");
					}
					else{
						reg.setErMsg("पहले से ही उपयोग किया गया है");
					}
			}
			else{
				//boolean ab=consumeDeedDraftIdInsert(reg.getDeedDraftId(), reg.getHidnRegTxnId());
				if(true){/*
					reg.setDeedStat("5");
					
					//create folder structure now.
					
					
					DeedDraftBD bd = new DeedDraftBD();
					CommonAction action=new CommonAction();
					CommonActionUtilities uti = new CommonActionUtilities();
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					
						String path=pr.getValue("pdf.location");
						byte[] byteArr=bd.generateDeedPDF(path,reg.getDeedDraftId(), response,1);
						
						String filePath=action.uploadFile(reg.getHidnRegTxnId()+"/"+RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT,byteArr,
								null,null,RegInitConstant.UPLOAD_DEED_CONSUMED);
						
						if(filePath!=null && !filePath.equalsIgnoreCase("")){
							
							boolean ab=consumeDeedDraftIdInsert(reg.getDeedDraftId(), reg.getHidnRegTxnId());
							if(ab){
								reg.setDeedStat("5");
							String[] param={filePath,reg.getHidnRegTxnId()};
							
							reg.setDeedPath(filePath);
							reg.setDeedContents(byteArr);
							
							dbUtility.createPreparedStatement(RegCommonSQL.UPDATE_DEED_DRAFT_PATH_NAME);
							boo=dbUtility.executeUpdate(param);
							
							if(boo){
								dbUtility.commit();
								
							}else{
								
							    dbUtility.rollback();
								
							}
							}else{
								dbUtility.rollback();
							}
							
						}else{
							
							dbUtility.createPreparedStatement(RegCommonSQL.UPDATE_DEED_DRAFT_ID_ROLLBACK);
							boo=dbUtility.executeUpdate(new String[]{reg.getHidnRegTxnId()});
							
							dbUtility.createPreparedStatement(RegCommonSQL.UPDATE_DEED_DRAFT_TABLE_ROLLBACK);
							boo=dbUtility.executeUpdate(new String[]{reg.getDeedDraftId()});
							
							if(boo){
								dbUtility.commit();
								
							}
							
						}
					
				
					//}else{
						//formDTO.setValidDeed("Invalid");
					//}
				  		//regForm.setActionName("");
				  		//forward = "reginitConfirm";
				
					
				
					
					
					
				*/}
				else{
					reg.setDeedStat("1");
				}
			}
			
		}
		catch(Exception e){
			dbUtility.rollback();
			logger.debug(e.getMessage(),e);
			logger.debug(e);
			throw e;
		}
		finally{

			try {
				dbUtility.closeConnection();
				logger.debug("connection closed");
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		
		}
	}
	public boolean consumeDeedDraftIdInsert(String id, String regId){
		boolean ret=false;
		try{
			ret=consumeDeedDraftId(id);
			if(ret){
			//dbUtility= new DBUtility();
			dbUtility.createPreparedStatement(RegCommonSQL.UPDATE_DEED_DRAFT_ID);
			ret=dbUtility.executeUpdate(new String []{id, regId});
			}
		}
		catch(Exception e){
			logger.debug(e.getMessage(),e);
			return false;
		}
		finally{

			try {
				//dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		
		}
	return ret;
	}
	public boolean consumeDeedDraftId(String id){
		boolean ret=false;
		try{
			//dbUtility= new DBUtility();
			dbUtility.createPreparedStatement(RegCommonSQL.UPDATE_DEED_DRAFT_TABLE);
			ret=dbUtility.executeUpdate(new String []{id});
		}
		catch(Exception e){
			logger.debug(e.getMessage(),e);
			return false;
		}
		finally{

			try {
				//dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		
		}
	return ret;
	}

public String getPlotTotArea(String valId){
		



		String area = "";
		try {
			dbUtility = new DBUtility();
			String param[] = new String[1];
			String SQL = RegCommonSQL.IGRS_PLOT_TOT_AREA;
			param[0] = valId;
			
			dbUtility.createPreparedStatement(SQL);
			area = dbUtility.executeQry(param);
			
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return area;
	
		
	}
	
public String getAgriTotArea(String valId){
		

		String area = "";
		try {
			dbUtility = new DBUtility();
			String param[] = new String[1];
			String SQL = RegCommonSQL.IGRS_AGRI_TOT_AREA;
			param[0] = valId;
			
			dbUtility.createPreparedStatement(SQL);
			area = dbUtility.executeQry(param);
			
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return area;
	
		
	}


public ArrayList getMPCSTParams(String valId){
	

	ArrayList list=new ArrayList();
	try {
		dbUtility = new DBUtility();
		String param[] = new String[1];
		String SQL = RegCommonSQL.GET_MPCST_PARAMS;
		param[0] = valId;
		
		dbUtility.createPreparedStatement(SQL);
		list = dbUtility.executeQuery(param);
		
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	
	return list;

	
}
	
	
	public boolean insertAdditionalUploads(RegCompletDTO regDto, RegCompletionForm regForm) {
		boolean check=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String SQL = RegCommonSQL.IGRS_INSERT_ADDITIONAL_UPLOADS;
			dbUtility.createPreparedStatement(SQL);
			
			ArrayList<CommonDTO> dtoList = regDto.getListDtoP();
			for(int i =0; i < dtoList.size() ; i++)
			{
				CommonDTO dto = dtoList.get(i);
				String arr[] = new String[8];
				arr[0] = regForm.getHidnRegTxnId();
				arr[1] = "";
				arr[2] = regDto.getPropertyId();
				arr[3] = dto.getDocumentPath();
				arr[4] = dto.getDocumentName();
				arr[5] = "A";
				arr[6] = regForm.getUserID();
				arr[7] = "";
				check = dbUtility.executeUpdate(arr);
				if(!check)
				{
					break;
				}
			}
			if(check)
			{
				dbUtility.commit();
			}else {
				dbUtility.rollback();
				throw new Exception();
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return check;
	}
	

public boolean deleteALLRemovedUploads(String hidnRegTxnId,
			String partyTxnId) {
		boolean check = false;
		try {
			dbUtility = new DBUtility();
			String param[] = new String[2];
			String SQL = RegCommonSQL.IGRS_DELETE_REMOVED_UPLOADS;
			param[0] = hidnRegTxnId;
			param[1] = partyTxnId;
			dbUtility.createPreparedStatement(SQL);
		check = dbUtility.executeUpdate(param);
		//check=true;
			
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return false;
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}
		
		return check;
	}

public boolean deleteAllRemovedUploadsConsenter(String hidnRegTxnId,
		String ConsenterId) {
	boolean check = false;
	try {
		dbUtility = new DBUtility();
		String param[] = new String[2];
		String SQL = RegCommonSQL.IGRS_DELETE_REMOVED_UPLOADS_CONSENTER;
		param[0] = hidnRegTxnId;
		param[1] = ConsenterId;
		dbUtility.createPreparedStatement(SQL);
	check = dbUtility.executeUpdate(param);
	//check=true;
		
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
		return false;
	}
	finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	
	return check;
}




public boolean deleteALLRemovedUploadsP(String hidnRegTxnId,
		String propId) {
	boolean check = false;
	try {
		dbUtility = new DBUtility();
		String param[] = new String[2];
		String SQL = RegCommonSQL.IGRS_DELETE_REMOVED_UPLOADS_PROPERTY;
		param[0] = hidnRegTxnId;
		param[1] = propId;
		dbUtility.createPreparedStatement(SQL);
	check = dbUtility.executeUpdate(param);
		//check=true;
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
		return false;
	}
	finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	
	return check;
}


public String getAdjuFee(String funId){
	

	String fee="0";
	try {
		dbUtility = new DBUtility();
		String param[] = new String[1];
		String SQL = RegCommonSQL.GET_ADJU_FEE;
		param[0] = funId;
		
		dbUtility.createPreparedStatement(SQL);
		fee = dbUtility.executeQry(param);
		
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	
	return fee;

	
}

public ArrayList getConsenterFlag(String appId){
	

	ArrayList list=new ArrayList();
	try {
		dbUtility = new DBUtility();
		String param[] = new String[1];
		String SQL = RegCommonSQL.GET_CONSENTER_FLAG;
		param[0] = appId;
		
		dbUtility.createPreparedStatement(SQL);
		list = dbUtility.executeQuery(param);
		
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	
	return list;

	
}



public int getMaxDeedPage(String deedDraftID){
	int deedPage=0;
	try {
		
		dbUtility = new DBUtility();
		String param[]={deedDraftID};
		String SQL = RegCommonSQL.GET_DEED_DRAFT_MAX_PAGE_NUMBER;
		dbUtility.createPreparedStatement(SQL);
		String page=dbUtility.executeQry(param);
		deedPage=Integer.parseInt(page);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return deedPage;
}

public String getTotalConsenterConsidAmount(String regTxnId){
	String amount1="0";
	try {
		
		dbUtility = new DBUtility();
		String param[]={regTxnId};
		String SQL = RegCommonSQL.GET_TOTAL_CONSENTER_CONSID_AMOUNT;
		dbUtility.createPreparedStatement(SQL);
		 amount1=dbUtility.executeQry(param);
		//amount=Double.parseDouble(amount1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return amount1;
}

public String getTotalConsenterConsidAmountFinal(String regTxnId){
	String amount1="0";
	try {
		
		dbUtility = new DBUtility();
		String param[]={regTxnId};
		String SQL = RegCommonSQL.GET_TOTAL_CONSENTER_CONSID_AMOUNT_FINAL;
		dbUtility.createPreparedStatement(SQL);
		 amount1=dbUtility.executeQry(param);
		//amount=Double.parseDouble(amount1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return amount1;
}

public boolean updateDeedDetails(String deedName,String deedPath,String regTxnID,int noofPages){
	boolean boo=false;
try {
		
		String page=String.valueOf(noofPages);
		dbUtility = new DBUtility();
		String param[]={deedName,deedPath+deedName,page,regTxnID};
		String SQL = RegCommonSQL.UPDATE_MERGED_DEED_STATUS;
		dbUtility.createPreparedStatement(SQL);
		boo = dbUtility.executeUpdate(param);
		if(boo){
			dbUtility.commit();
		}else{
			dbUtility.rollback();
			throw new Exception();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
		return false;
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return boo;
}

public boolean insertConsenterDetails(String[] param,String action){
	boolean boo=false;
try {
		
		dbUtility = new DBUtility();
		
		String SQL = RegCommonSQL.INSERT_CONSENTER_DETLS;
		dbUtility.createPreparedStatement(SQL);
		boo = dbUtility.executeUpdate(param);
		if(boo){
			
			if(action!=null && "save".equalsIgnoreCase(action)){
				
				
				String[] statusParam = {
						RegInitConstant.STATUS_SHARES_SAVED,
						param[10] };

				sql = RegCommonSQL.UPDATE_REG_INIT_TXN_STATUS;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(statusParam);
				if (boo) {
					dbUtility.commit();
				} else {
					dbUtility.rollback();
					throw new Exception();
				}
				
				
				
			}
			else{
			
			dbUtility.commit();
			}
		}else{
			dbUtility.rollback();
			throw new Exception();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
		return false;
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return boo;
}

public boolean updateConsenterDetails(String[] param){
	boolean boo=false;
try {
		
		dbUtility = new DBUtility();
		
		String SQL = RegCommonSQL.UPDATE_CONSENTER_DETLS;
		dbUtility.createPreparedStatement(SQL);
		boo = dbUtility.executeUpdate(param);
		if(boo){
			
			
			
			dbUtility.commit();
			
		}else{
			dbUtility.rollback();
			throw new Exception();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
		return false;
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return boo;
}

public boolean deleteConsenterDetails(String[] consenterIds, String regTxnId){
	boolean boo=false;
try {
		
		dbUtility = new DBUtility();
		String param[]=new String[2];
		String SQL = RegCommonSQL.DELETE_CONSENTER_DETLS;
		for(int i=0;i<consenterIds.length;i++){
			
			param[0]=consenterIds[i];
			param[1]=regTxnId;
			
			dbUtility.createPreparedStatement(SQL);
			boo = dbUtility.executeUpdate(param);
			
			if(!boo){
				break;
			}else{
				logger.debug("consenter deleted :"+i);
			}
			
		}
		if(boo){
			dbUtility.commit();
		}else{
			dbUtility.rollback();
			throw new Exception();
		}
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
		return false;
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return boo;
}

public ArrayList getConsenters(String regTxnID){
	ArrayList list=new ArrayList();
	try {
		
		dbUtility = new DBUtility();
		String param[]={regTxnID};
		String SQL = RegCommonSQL.GET_CONSENTERS;
		dbUtility.createPreparedStatement(SQL);
		list=dbUtility.executeQuery(param);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return list;
}

public ArrayList getConsenterDetailsView(String regTxnID,String consenterId){
	ArrayList list=new ArrayList();
	try {
		
		dbUtility = new DBUtility();
		String param[]={regTxnID,consenterId};
		String SQL = RegCommonSQL.GET_CONSENTER_DEATILS_VIEW;
		dbUtility.createPreparedStatement(SQL);
		list=dbUtility.executeQuery(param);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return list;
}


public boolean copyFinalConsenterDuties(String regTxnId, String userId, String dutyTxnId
		) throws Exception {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			sql = RegCommonSQL.DELETE_DUTIES_DETAILS;
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(new String[] { regTxnId });

			if (boo) {

				String[] dutyParam = { dutyTxnId }; // duty txn id
				sql = RegCommonSQL.GET_DUTIES_FROM_DUTY;
				dbUtility.createPreparedStatement(sql);
				ArrayList dutyList = dbUtility.executeQuery(dutyParam);
				ArrayList rowList;
				if (dutyList != null && dutyList.size() == 1) {

					rowList = (ArrayList) dutyList.get(0);
					String[] dutiesParam = new String[15];

					dutiesParam[0] = regTxnId; // reg txn id
					dutiesParam[1] = rowList.get(0).toString();
					dutiesParam[2] = rowList.get(1).toString();
					dutiesParam[3] = rowList.get(2).toString();
					dutiesParam[4] = rowList.get(3).toString();
					dutiesParam[5] = rowList.get(4).toString();
					dutiesParam[6] = rowList.get(5).toString();
					dutiesParam[7] = userId; // user id

					dutiesParam[8] = rowList.get(8).toString();// duty
					// already
					// paid
					dutiesParam[9] = rowList.get(9).toString();// fee
					// already
					// paid

					if (rowList.get(6) != null
							&& !rowList.get(6).toString().equalsIgnoreCase("")
							&& !rowList.get(6).toString().equalsIgnoreCase(
									"null")) {
						dutiesParam[10] = rowList.get(6).toString();
					} else {
						dutiesParam[10] = "0";
					}

					if (rowList.get(7) != null
							&& !rowList.get(7).toString().equalsIgnoreCase("")
							&& !rowList.get(7).toString().equalsIgnoreCase(
									"null")) {
						dutiesParam[11] = rowList.get(7).toString();
					} else {
						dutiesParam[11] = "0";
					}

					dutiesParam[12] = rowList.get(10).toString();// duty
					// exemp
					dutiesParam[13] = rowList.get(11).toString();// fee
					// exemp
					dutiesParam[14] = rowList.get(12).toString();

					sql = RegCommonSQL.INSERT_DUTIES_DETAILS_NEW;
					dbUtility.createPreparedStatement(sql);
					boo = dbUtility.executeUpdate(dutiesParam);

					if (boo) {

						dbUtility.commit();

					} else {
						dbUtility.rollback();
						throw new Exception();
					}

				} else{
					dbUtility.rollback();
					throw new Exception();
				}

			}

		} catch (Exception e) {
			try {
				dbUtility.rollback();
			} finally {
				logger.debug(e.getMessage(),e);
			}
			return false;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return boo;
}
public boolean updateMPCSTdata(String[] param){
	boolean boo=false;
try {
		
	dbUtility = new DBUtility();
	dbUtility.setAutoCommit(false);
	sql = RegCommonSQL.UPDATE_REG_PROP_MPCST_DETLS;
	dbUtility.createPreparedStatement(sql);
	boo = dbUtility.executeUpdate(param);
		if(boo){
			dbUtility.commit();	
		}else{
			dbUtility.rollback();
			throw new Exception();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
		return false;
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return boo;
}
public ArrayList getMPCSTdata(String propID){
	ArrayList list=new ArrayList();
	try {
		
		dbUtility = new DBUtility();
		String param[]={propID};
		String SQL = RegCommonSQL.GET_REG_PROP_MPCST_DETLS;
		dbUtility.createPreparedStatement(SQL);
		list=dbUtility.executeQuery(param);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return list;
}

public ArrayList getOwnersHashMap(String partyTxnId, String regTxnId){
	ArrayList list=new ArrayList();
	try {
		
		dbUtility = new DBUtility();
		String param[]={regTxnId,partyTxnId};
		String SQL = RegCommonSQL.GET_OWNER_HASHMAP;
		dbUtility.createPreparedStatement(SQL);
		list=dbUtility.executeQuery(param);
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return list;
}

public ArrayList getOwnersHashMapAllDetails(String partyTxnId){
	ArrayList list=new ArrayList();
	try {
		
		dbUtility = new DBUtility();
		String param[]={partyTxnId};
		String SQL = RegCommonSQL.GET_ALL_OWNERS_DETAILS;
		dbUtility.createPreparedStatement(SQL);
		list=dbUtility.executeQuery(param);
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return list;
}

public ArrayList getBuilderOwnerShares(String propID,int dutyTxnID){
	ArrayList list=new ArrayList();
	try {
		
		dbUtility = new DBUtility();
		String param[]={propID,Integer.toString(dutyTxnID)};
		String SQL = RegCommonSQL.GET_PROP_DUTY_ID;
		dbUtility.createPreparedStatement(SQL);
		String propDutyId=dbUtility.executeQry(param);
		
		if(propDutyId!=null && !("").equalsIgnoreCase(propDutyId))
		{
		String param2[]={propDutyId};
		SQL = RegCommonSQL.GET_DEVELOPER_SHARE;
		dbUtility.createPreparedStatement(SQL);
		String developerShare=dbUtility.executeQry(param2);
		
		SQL = RegCommonSQL.GET_OWNER_SHARE;
		dbUtility.createPreparedStatement(SQL);
		String ownerShare=dbUtility.executeQry(param2);
		
		if(developerShare!=null && !("").equalsIgnoreCase(developerShare) &&
				ownerShare!=null && !("").equalsIgnoreCase(ownerShare)){
		list.add(developerShare);
		list.add(ownerShare);
		}else{
			list.add("100");
			list.add("100");
		}	
	}else{
		list.add("100");
		list.add("100");
	}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return list;
}
public ArrayList getAllUploadsCountAndSize(String appId){
	
	ArrayList finalList=new ArrayList();
	
	try{
	dbUtility = new DBUtility();
	String param[]={appId};
	String SQL = RegCommonSQL.GET_PARTY_UPLOADS;
	dbUtility.createPreparedStatement(SQL);
	ArrayList list1=dbUtility.executeQuery(param);
	
	SQL = RegCommonSQL.GET_PROPERTY_UPLOADS;
	dbUtility.createPreparedStatement(SQL);
	ArrayList list2=dbUtility.executeQuery(param);
	
	SQL = RegCommonSQL.GET_ADD_UPLOADS;
	dbUtility.createPreparedStatement(SQL);
	ArrayList list3=dbUtility.executeQuery(param);
	
	finalList.add(list1);
	finalList.add(list2);
	finalList.add(list3);
	}
	catch (Exception e) {
		
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	
	return finalList;
	
}

public ArrayList getPrintForm(String regID) throws Exception {

	
	ArrayList list=new ArrayList();

	try {
		dbUtility = new DBUtility();
		String[] param = { regID };
		sql = RegCommonSQL.GET_PRINT_FORM;
		dbUtility.createPreparedStatement(sql);
		list = dbUtility.executeQuery(param);

	} catch (Exception exception) {

		logger.debug("Exception in getPrintForm-RegCommonDAO"
				+ exception);

	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
	}

	return list;

}

public ArrayList getConsenterList(String regTxnID){
	ArrayList list=new ArrayList();
	try {
		
		dbUtility = new DBUtility();
		String param[]={regTxnID};
		String SQL = RegCommonSQL.GET_CONSENTERS_PDF;
		dbUtility.createPreparedStatement(SQL);
		list=dbUtility.executeQuery(param);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return list;
}

public ArrayList  getTehsilList(String districtId,String languageLocale1)
throws Exception {
ArrayList tehsilList = new ArrayList();
ArrayList list1 = new ArrayList();
ArrayList list2 = new ArrayList();
DBUtility dbUtil=null;
try {

dbUtil = new DBUtility();
dbUtil.createStatement();

String temp[] = new String[1];
String sql =null;
temp[0] = districtId;// district ID selected By User
if(languageLocale1.equalsIgnoreCase("en")){
sql = RegCommonSQL.TEHSIL;
}
else{
	sql = RegCommonSQL.TEHSIL_HINDI;
}
dbUtil.createPreparedStatement(sql);
list1 = dbUtil.executeQuery(temp);

for (int i = 0; i < list1.size(); i++) {
	list2 = (ArrayList) list1.get(i);
	//SlotBookDTO sdto = new SlotBookDTO();
	RegCommonDTO sdto= new RegCommonDTO();
	sdto.setTehsilId(list2.get(0).toString());
	
		sdto.setTehsilName(list2.get(1).toString());
	
	tehsilList.add(sdto);
}

} catch (Exception e) {
e.printStackTrace();
} finally {
try {
	dbUtil.closeConnection();
} catch (Exception e) {
	logger.debug("closing connection error on slot bookdao");

}
}

return tehsilList;
}

public ArrayList  getTehsilList6(String districtId,String languageLocale1)
throws Exception {
ArrayList tehsilList = new ArrayList();
ArrayList list1 = new ArrayList();
ArrayList list2 = new ArrayList();
DBUtility dbUtil=null;
try {

dbUtil = new DBUtility();
dbUtil.createStatement();
String sql =null;
String temp[] = new String[1];
temp[0] = districtId;// district ID selected By User
if(languageLocale1.equalsIgnoreCase("en")){
sql = RegCommonSQL.TEHSIL;
}
else{
	sql = RegCommonSQL.TEHSIL_HINDI;
}
dbUtil.createPreparedStatement(sql);
list1 = dbUtil.executeQuery(temp);

for (int i = 0; i < list1.size(); i++) {
	list2 = (ArrayList) list1.get(i);
	//SlotBookDTO sdto = new SlotBookDTO();
	RegCommonDTO sdto= new RegCommonDTO();
	sdto.setTehsilId6(list2.get(0).toString());
	
		sdto.setTehsilName6(list2.get(1).toString());
	
	tehsilList.add(sdto);
}

} catch (Exception e) {
e.printStackTrace();
} finally {
try {
	dbUtil.closeConnection();
} catch (Exception e) {
	logger.debug("closing connection error on slot bookdao");

}
}

return tehsilList;
}

public ArrayList  getTehsilList5(String districtId,String languageLocale1)
throws Exception {
ArrayList tehsilList = new ArrayList();
ArrayList list1 = new ArrayList();
ArrayList list2 = new ArrayList();
DBUtility dbUtil=null;
try {

dbUtil = new DBUtility();
dbUtil.createStatement();
String sql =null;
String temp[] = new String[1];
temp[0] = districtId;// district ID selected By User
if(languageLocale1.equalsIgnoreCase("en")){
 sql = RegCommonSQL.TEHSIL;
}
else{
	sql = RegCommonSQL.TEHSIL_HINDI;
}
dbUtil.createPreparedStatement(sql);
list1 = dbUtil.executeQuery(temp);

for (int i = 0; i < list1.size(); i++) {
	list2 = (ArrayList) list1.get(i);
	//SlotBookDTO sdto = new SlotBookDTO();
	RegCommonDTO sdto= new RegCommonDTO();
	sdto.setTehsilId5(list2.get(0).toString());
	
		sdto.setTehsilName5(list2.get(1).toString());
	
	tehsilList.add(sdto);
}

} catch (Exception e) {
e.printStackTrace();
} finally {
try {
	dbUtil.closeConnection();
} catch (Exception e) {
	logger.debug("closing connection error on slot bookdao");

}
}

return tehsilList;
}





//below to clear perviously selected tehsil
public ArrayList  getTehsilList1(String districtId,String languageLocale1)
throws Exception {
ArrayList tehsilList = new ArrayList();
ArrayList list1 = new ArrayList();
ArrayList list2 = new ArrayList();
DBUtility dbUtil=null;
try {

dbUtil = new DBUtility();
dbUtil.createStatement();
String sql =null;
String temp[] = new String[1];
temp[0] = districtId;// district ID selected By User
if(languageLocale1.equalsIgnoreCase("en")){
sql = RegCommonSQL.TEHSIL;
}
else{
	 sql = RegCommonSQL.TEHSIL_HINDI;
}
dbUtil.createPreparedStatement(sql);
list1 = dbUtil.executeQuery(temp);

for (int i = 0; i < list1.size(); i++) {
	list2 = (ArrayList) list1.get(i);
	//SlotBookDTO sdto = new SlotBookDTO();
	RegCommonDTO sdto= new RegCommonDTO();
	sdto.setTehsilId1(list2.get(0).toString());
	
		sdto.setTehsilName1(list2.get(1).toString());
	
	tehsilList.add(sdto);
}

} catch (Exception e) {
e.printStackTrace();
} finally {
try {
	dbUtil.closeConnection();
} catch (Exception e) {
	logger.debug("closing connection error on slot bookdao");

}
}

return tehsilList;
}
public ArrayList  getTehsilList3(String districtId,String languageLocale1)
throws Exception {
ArrayList tehsilList = new ArrayList();
ArrayList list1 = new ArrayList();
ArrayList list2 = new ArrayList();
DBUtility dbUtil=null;
try {

dbUtil = new DBUtility();
dbUtil.createStatement();
String sql =null;
String temp[] = new String[1];
temp[0] = districtId;// district ID selected By User
if(languageLocale1.equalsIgnoreCase("en")){
sql = RegCommonSQL.TEHSIL;
}
else{
	sql = RegCommonSQL.TEHSIL_HINDI;
}
dbUtil.createPreparedStatement(sql);
list1 = dbUtil.executeQuery(temp);

for (int i = 0; i < list1.size(); i++) {
	list2 = (ArrayList) list1.get(i);
	//SlotBookDTO sdto = new SlotBookDTO();
	RegCommonDTO sdto= new RegCommonDTO();
	sdto.setTehsilId3(list2.get(0).toString());
	
		sdto.setTehsilName3(list2.get(1).toString());
	
	tehsilList.add(sdto);
}

} catch (Exception e) {
e.printStackTrace();
} finally {
try {
	dbUtil.closeConnection();
} catch (Exception e) {
	logger.debug("closing connection error on slot bookdao");

}
}

return tehsilList;
}

public ArrayList  getTehsilList2(String districtId,String languageLocale1)
throws Exception {
ArrayList tehsilList = new ArrayList();
ArrayList list1 = new ArrayList();
ArrayList list2 = new ArrayList();
DBUtility dbUtil=null;
try {

dbUtil = new DBUtility();
dbUtil.createStatement();
String sql =null;
String temp[] = new String[1];
temp[0] = districtId;// district ID selected By User
if(languageLocale1.equalsIgnoreCase("en")){
 sql = RegCommonSQL.TEHSIL;
}
else{
sql = RegCommonSQL.TEHSIL_HINDI;
}
dbUtil.createPreparedStatement(sql);
list1 = dbUtil.executeQuery(temp);

for (int i = 0; i < list1.size(); i++) {
	list2 = (ArrayList) list1.get(i);
	//SlotBookDTO sdto = new SlotBookDTO();
	RegCommonDTO sdto= new RegCommonDTO();
	sdto.setTehsilId2(list2.get(0).toString());
	
		sdto.setTehsilName2(list2.get(1).toString());
	
	tehsilList.add(sdto);
}

} catch (Exception e) {
e.printStackTrace();
} finally {
try {
	dbUtil.closeConnection();
} catch (Exception e) {
	logger.debug("closing connection error on slot bookdao");

}
}

return tehsilList;
}


public ArrayList  getTehsilList7(String districtId,String languageLocale1)
throws Exception {
ArrayList tehsilList = new ArrayList();
ArrayList list1 = new ArrayList();
ArrayList list2 = new ArrayList();
DBUtility dbUtil=null;
try {

dbUtil = new DBUtility();
dbUtil.createStatement();
String sql =null;
String temp[] = new String[1];
temp[0] = districtId;// district ID selected By User
if(languageLocale1.equalsIgnoreCase("en")){
sql = RegCommonSQL.TEHSIL;
}
else{
	sql = RegCommonSQL.TEHSIL_HINDI;
}
dbUtil.createPreparedStatement(sql);
list1 = dbUtil.executeQuery(temp);

for (int i = 0; i < list1.size(); i++) {
	list2 = (ArrayList) list1.get(i);
	//SlotBookDTO sdto = new SlotBookDTO();
	RegCommonDTO sdto= new RegCommonDTO();
	sdto.setTehsilId7(list2.get(0).toString());
	
		sdto.setTehsilName7(list2.get(1).toString());
	
	tehsilList.add(sdto);
}

} catch (Exception e) {
e.printStackTrace();
} finally {
try {
	dbUtil.closeConnection();
} catch (Exception e) {
	logger.debug("closing connection error on slot bookdao");

}
}

return tehsilList;
}
public String checkStampdutyDetails(String getdutyTxnId) {
	ArrayList list1=new ArrayList();
	ArrayList exemptionIds= new ArrayList();
	String dutyId=getdutyTxnId;
	DBUtility dbUtil=null;
    String exmType="";	

	 try {
		 
		 String sql="";
		 String sql1="";
		 String param1[]= { dutyId };
		 dbUtil = new DBUtility();
		sql= RegCommonSQL.GET_EXEMPTION_ID_E;
		dbUtil.createPreparedStatement(sql);
		exemptionIds=dbUtil.executeQuery(param1);
		if(exemptionIds.size()>0){
			 exmType="E";
		}
						 
	 }catch ( Exception e) {
		 e.printStackTrace();
	 }
	 finally {
		 try {
		 	dbUtil.closeConnection();
		 } catch (Exception e) {
		 	logger.debug("closing connection error on slot bookdao");
		 }
	 }
	
	return exmType;
}
public String checkEfiledutyDetails(String getdutyTxnId) {
	// TODO Auto-generated method stub
	
	ArrayList list1=new ArrayList();
	ArrayList exemptionIds= new ArrayList();
	String dutyId=getdutyTxnId;
	DBUtility dbUtil=null;
    String exmType="";	
    
    try {
		 
		 String sql="";
		 String sql1="";
		 String param1[]= { dutyId };
		 dbUtil = new DBUtility();
		sql= RegCommonSQL.GET_EXEMPTION_ID_F;
		dbUtil.createPreparedStatement(sql);
		exemptionIds=dbUtil.executeQuery(param1);
		
		if(exemptionIds.size()>0){
			exmType="F";
		}
						 
	 }catch ( Exception e) {
		 e.printStackTrace();
	 }
	 finally {
		 try {
		 	dbUtil.closeConnection();
		 } catch (Exception e) {
		 	logger.debug("closing connection error on slot bookdao");
		 }
	 }
	
	return exmType;
}
public boolean chekExemption(String getdutyTxnId) {
	// TODO Auto-generated method stub
	
	boolean exemFlag=false;
	DBUtility dbUtil=null;
	
	try {
	    String sql="";
	    String temp="";
		String param1[]= { getdutyTxnId };
		dbUtil = new DBUtility();
		sql=RegCommonSQL.GET_EXEMP_FLAG;
		dbUtil.createPreparedStatement(sql);
		temp=dbUtil.executeQry(param1);
		if(temp.equalsIgnoreCase("Y"))
			exemFlag=true;
		
	}catch ( Exception e){
		e.printStackTrace();
	}
	return exemFlag;
}
public String getuploadpath(String dutyid) {
	String amount1="";
	try {
		
		dbUtility = new DBUtility();
		String param[]={dutyid};
		String SQL = RegCommonSQL.GET_PATH;
		dbUtility.createPreparedStatement(SQL);
		 amount1=dbUtility.executeQry(param);
		//amount=Double.parseDouble(amount1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return amount1;
}
public String getuploadpath1(String dutyid) {
	String amount1="";
	try {
		
		dbUtility = new DBUtility();
		String param[]={dutyid};
		String SQL = RegCommonSQL.GET_PATH1;
		dbUtility.createPreparedStatement(SQL);
		 amount1=dbUtility.executeQry(param);
		//amount=Double.parseDouble(amount1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return amount1;
}

public String gettehId(String tehsilGov) {
	String amount1="";
	try {
		
		dbUtility = new DBUtility();
		String param[]={tehsilGov};
		String SQL = RegCommonSQL.GET_TEH_ID;
		dbUtility.createPreparedStatement(SQL);
		 amount1=dbUtility.executeQry(param);
		//amount=Double.parseDouble(amount1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return amount1;
}

public String gettehId1(String tehsilGov,String district, String languageLocale) {
	String amount1="";
	try {
		
		dbUtility = new DBUtility();
		String param[]=new String[2];
		param[0] = tehsilGov;
		param[1] = district;
		
		String SQL_EN = RegCommonSQL.GET_TEH_ID_EN;
		String SQL_HI = RegCommonSQL.GET_TEH_ID_HI;
		if(languageLocale.trim().equalsIgnoreCase("en")){
			dbUtility.createPreparedStatement(SQL_EN);
		}else{
			dbUtility.createPreparedStatement(SQL_HI);
		}
		
		 amount1=dbUtility.executeQry(param);
		//amount=Double.parseDouble(amount1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	return amount1;
}

public ArrayList getTehsilListModify(String districtId,String languageLocale1) {
	ArrayList tehsilList = new ArrayList();
	ArrayList list1 = new ArrayList();
	ArrayList list2 = new ArrayList();
	DBUtility dbUtil=null;
	String sql_tehsil = null;
	try {

	dbUtil = new DBUtility();
	dbUtil.createStatement();

	String temp[] = new String[1];
	temp[0] = districtId;// district ID selected By User
	if(languageLocale1.equalsIgnoreCase("en")){
		sql_tehsil = RegCommonSQL.TEHSIL;
	}
	else{
		sql_tehsil = RegCommonSQL.TEHSIL_HINDI;
	}
	dbUtil.createPreparedStatement(sql_tehsil);
	list1 = dbUtil.executeQuery(temp);

	for (int i = 0; i < list1.size(); i++) {
		list2 = (ArrayList) list1.get(i);
		CommonDTO dto;
		dto = new CommonDTO();
		dto.setTehsildropid(list2.get(0).toString());
		
		dto.setTehsildropname(list2.get(1).toString());
		
		tehsilList.add(dto);
		
		
	}

	} catch (Exception e) {
	e.printStackTrace();
	} finally {
	try {
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.debug("closing connection error on slot bookdao");

	}
	}

	return tehsilList;
}

public ArrayList getTehsilListModify1(String districtId,String languageLocale1) {
	ArrayList tehsilList = new ArrayList();
	ArrayList list1 = new ArrayList();
	ArrayList list2 = new ArrayList();
	DBUtility dbUtil=null;
	try {

	dbUtil = new DBUtility();
	dbUtil.createStatement();

	String temp[] = new String[1];
	temp[0] = districtId;// district ID selected By User
	if(languageLocale1.equalsIgnoreCase("en")){
	String sql = RegCommonSQL.TEHSIL;
	}
	else{
		String sql = RegCommonSQL.TEHSIL_HINDI;
	}
	dbUtil.createPreparedStatement(sql);
	list1 = dbUtil.executeQuery(temp);

	for (int i = 0; i < list1.size(); i++) {
		list2 = (ArrayList) list1.get(i);
		CommonDTO dto;
		dto = new CommonDTO();
		dto.setTehsildropid1(list2.get(0).toString());
		
		dto.setTehsildropname1(list2.get(1).toString());
		
		tehsilList.add(dto);
		
		
	}

	} catch (Exception e) {
	e.printStackTrace();
	} finally {
	try {
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.debug("closing connection error on slot bookdao");

	}
	}

	return tehsilList;
}

public ArrayList getpropertyPrintDetails(String tempIdPrint) {
	ArrayList efilePrintList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = tempIdPrint;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_PRINT_PROP_DETAILS;
		
		dbUtility.createPreparedStatement(sql);
		efilePrintList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efilePrintList;
}

public String getinstId(String tempDutyId) {
	String efileDutyId=null;
	
	String param[] = new String[1];
	param[0] = tempDutyId;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_TEMP_INST_TXN_ID;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyId = dbUtility.executeQry(param);
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	
}
	
	return efileDutyId;
}

public ArrayList getgovDetails(String partytxnid) {

	ArrayList efilePrintList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = partytxnid;

	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_GOV_DETLS;
		
		dbUtility.createPreparedStatement(sql);
		efilePrintList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efilePrintList;
}

public ArrayList getindDetails(String partytxnid) {
ArrayList efilePrintList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = partytxnid;

	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_IND_DETLS;
		
		dbUtility.createPreparedStatement(sql);
		efilePrintList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	
}
	
	return efilePrintList;
}

public ArrayList getorgDetails(String partytxnid) {
ArrayList efilePrintList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = partytxnid;

	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_ORG_DETLS;
		
		dbUtility.createPreparedStatement(sql);
		efilePrintList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	
}
	
	return efilePrintList;
}

public boolean getispoa(String partytxnid) {
	boolean statuspoa=false;
	String str=null;
	
	
	
	
	try{
	dbUtility = new DBUtility();
	
	dbUtility.createPreparedStatement(RegCommonSQL.GET_POA_DETLS1);
	String arr1[]=new String[1];
	arr1[0]=partytxnid;
	
	
	str=dbUtility.executeQry(arr1);
	
	if(!str.isEmpty() && str!=null){
		statuspoa=true;
	}
	
	
	}
	
	
	
	
	
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	
}finally
{
	try {
		dbUtility.closeConnection();
	} catch (Exception e) {
		
		logger.debug(e.getMessage(),e);
	}
}
	
	return statuspoa;
}

public boolean getisPoACount(String partytxnid) {
	int poaCount =0;
	String str=null;
	boolean count=false;
	try{
		dbUtility = new DBUtility();
		
		dbUtility.createPreparedStatement(RegCommonSQL.GET_POA_DETLS);
		String arr1[]=new String[1];
		arr1[0]=partytxnid;
		
		
		str=dbUtility.executeQry(arr1);
		poaCount=Integer.parseInt(str);
		if(poaCount<2){
			count=true;
		}
		
		}
		
		
		
		
		
		catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return count;
}

public ArrayList getindpoaDetails(String partytxnid) {
ArrayList efilePrintList = new ArrayList();
	
	String param[] = new String[1];
	param[0] = partytxnid;
	//param[1] = partytxnid; // Commented By Gulrej on 7th Sept, 2017

	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_POA_1_DETLS;
		
		dbUtility.createPreparedStatement(sql);
		efilePrintList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efilePrintList;
}

public ArrayList getindpoaDetails1(String partytxnid) {
ArrayList efilePrintList = new ArrayList();
	
	String param[] = new String[2];
	param[0] = partytxnid;
	param[1] = partytxnid;

	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_POA_2_DETLS;
		
		dbUtility.createPreparedStatement(sql);
		efilePrintList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efilePrintList;
}

public ArrayList getorgpoaDetails(String partytxnid) {
ArrayList efilePrintList = new ArrayList();
	
	String param[] = new String[2];
	param[0] = partytxnid;
	param[1] = partytxnid;

	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_ORG_POA_1_DETLS;
		
		dbUtility.createPreparedStatement(sql);
		efilePrintList = dbUtility.executeQuery(param);
		 
		// String abc=flag;
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	return efilePrintList;
}

public ArrayList getorgpoaDetails1(String partytxnid) {
	ArrayList efilePrintList = new ArrayList();
		
		String param[] = new String[2];
		param[0] = partytxnid;
		param[1] = partytxnid;

		
		try {
			dbUtility = new DBUtility();
			
			sql = RegCommonSQL.GET_ORG_POA_2_DETLS;
			
			dbUtility.createPreparedStatement(sql);
			efilePrintList = dbUtility.executeQuery(param);
			 
			// String abc=flag;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				
				logger.debug(e.getMessage(),e);
			}
		}

		
		return efilePrintList;
	}

public String getphysicalSeries(String tempIdPrint) {
	String efileDutyId=null;
	
	String param[] = new String[1];
	param[0] = tempIdPrint;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_PHYSICAL_SERIES;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyId = dbUtility.executeQry(param);
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efileDutyId;
	
}

public boolean getcheckfileName(String hidnRegTxnId, String fileName) {
	
	boolean boo = false;
	boolean checkfileflag=false;
	try {
		dbUtility = new DBUtility();
		dbUtility.setAutoCommit(false);
		String arr1[]=new String[2];
		arr1[0]=hidnRegTxnId;
		arr1[1]=fileName;
		
		sql = RegCommonSQL.CHECK_TWICE_FILE_NAME;
		dbUtility.createPreparedStatement(sql);
		boo = dbUtility.executeUpdate(arr1);
		if (boo) {
			checkfileflag=true;
			
				
			
		} else
		{
			checkfileflag=false;
		}

	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
		return false;
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
	}
	return checkfileflag;
}

public String insertFileName(String hidnRegTxnId, String fileName) {
	
	
	boolean flag=false;
	String flag1=null;
	String []arr=new String[3];
	String UPLOAD_TXN_ID=getUploadSequenceID(RegCommonSQL.GET_MAIN_UPLOAD_ID);
	
	arr[0]=UPLOAD_TXN_ID;
	arr[1]=hidnRegTxnId;
		arr[2]=fileName;
		
		
	try {
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(RegCommonSQL.INSERT_FILE_NAME_PARTY);//ews applied flag to be inserted
		 flag=dbUtility.executeUpdate(arr);
		 if(flag){
			 flag1="true"; 
		 }
		 else{
			 flag1="false"; 
		 }
		// String abc=flag;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

////	arr[4]=dto.getUserId();
	//arr[5]=dto.getDistrictName();
	
	return flag1;
}
public String getUploadSequenceID(String sql)
{
	String id=null;
	 try {
        	
        	dbUtility = new DBUtility();
        		
        	dbUtility.createStatement();
        	
        	id = dbUtility.executeQry(sql);
        	

        } catch (Exception exception) {

                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
        
	return id;
}

public String getefileFee(String dutyIdCheck) {
	String efileDutyId=null;
	
	String param[] = new String[1];
	param[0] = dutyIdCheck;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_EFILE_FEE_PAYEMNT;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyId = dbUtility.executeQry(param);
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efileDutyId;
	
}

public boolean checkdocnoStatus(String tempIdPrint) {
	String  boo = null;
	boolean checkfileflag=false;
	try {
		dbUtility = new DBUtility();
		dbUtility.setAutoCommit(false);
		String arr1[]=new String[1];
		arr1[0]=tempIdPrint;
		
		
		sql = RegCommonSQL.CHECK_DOC_NO;
		dbUtility.createPreparedStatement(sql);
		boo = dbUtility.executeQry(arr1);
		/*dbUtility.createPreparedStatement(sql);
		boo = dbUtility.executeUpdate(arr1);*/
		if (!boo.isEmpty() && boo!=null) {
			checkfileflag=true;
			
				
			
		} else
		{
			checkfileflag=false;
		}

	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
		return false;
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
	}
	return checkfileflag;
}

public String getdocEfileNo(String tempIdPrint) {
String efileDutyId=null;
	
	String param[] = new String[1];
	param[0] = tempIdPrint;
	
	
	try {
		dbUtility = new DBUtility();
		
		sql = RegCommonSQL.GET_EFILE_DOC_NO;
		
		dbUtility.createPreparedStatement(sql);
		efileDutyId = dbUtility.executeQry(param);
		 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.debug(e.getMessage(),e);
	}finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}

	
	return efileDutyId;
}

public String getpropFlagParty(String partypo) {
String flag="";

    
    try {
    	String param[]={partypo};
    	dbUtility = new DBUtility();
    	
    	dbUtility.createPreparedStatement(RegCommonSQL.GET_PROPERTY_FLAG_DEED);
    	
    	flag = dbUtility.executeQry(param);
    	

    } catch (Exception exception) {

            System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

    }
    return flag;

}

public boolean updateDetailsOfOrganisation(String ogrNameTrns,
		String authPerNameTrns, String orgaddressTrns, String countryTrns,
		String statenameTrns, String districtTrns, String indtehsilmodify,
		String mobnoTrns, String phnoTrns, String authPerGendarTrns,
		String authPerFatherNameTrns, int authPerRelationshipTrns,
		String authPerCountryTrns, String authPerStatenameTrns,
		String authPerDistrictTrns, String indtehsilmodify1,
		String addressOrgOutMpTrns, String addressAuthPerOutMpTrns,
		String addressOrgOutMpTrns2, String addressAuthPerOutMpTrns2, String partyRoleTypeId) {
	
	return true;
}

public boolean updateDetailsOfGovtOfficial(String nameOfOfficialTrns,
		String desgOfOfficialTrns, String addressOfOfficialTrns,
		String inddistrictTrns, String indtehsilmodify,
		String addressGovtOffclOutMpTrns, String partyRoleTypeId) {
	
	return true;
}

public boolean updateDetailsOfIndividual(String ageTrns, String guardianTrans,
		String addressIndOutMpTrns, int relationshipTrns, String indphnoTrns,
		String indmobnoTrns, String indtehsilmodify, String inddistrictTrns,
		String indstatenameTrns, String indcountryTrns, String indaddressTrns,
		String indPovertyLineTrns, String indMinorityTrns,
		String occupationIdTrns, String indDisabilityDescTrns,
		String indDisabilityTrns, String indCategoryTrns, String ogrNameTrns,
		String authPerNameTrns, String idnoTrns, String emailIDTrns,
		String postalCodeTrns, String nationalityTrns, String spouseNameTrns,
		String motherNameTrns, String ageTrns2, String gendarTrns,
		String lnameTrns, String mnameTrns, String roleName, String partyTypeTrns, String partyRoleTypeId) {
	
	boolean flag = false;
	String[] param = null;
	param[0] = ageTrns;
	param[1] = guardianTrans;
	param[2] = addressIndOutMpTrns;
	param[3] = String.valueOf(relationshipTrns);
	param[4] = indphnoTrns;
	param[5] = indmobnoTrns;
	param[6] = indtehsilmodify;
	param[7] = inddistrictTrns;
	param[8] = indstatenameTrns;
	param[9] = indcountryTrns;
	param[10] = indaddressTrns;
	param[11] = indPovertyLineTrns;
	param[12] = indMinorityTrns;
	param[13] = occupationIdTrns;
	param[14] = indDisabilityDescTrns;
	param[15] = indDisabilityTrns;
	param[16] = indCategoryTrns;
	param[17] = ogrNameTrns;
	param[18] = authPerNameTrns;
	param[19] = idnoTrns;
	param[20] = emailIDTrns;
	param[21] = postalCodeTrns;
	param[22] = nationalityTrns;
	param[23] = spouseNameTrns;
	param[24] = motherNameTrns;
	param[25] = ageTrns2;
	param[26] = gendarTrns;
	param[27] = lnameTrns;
	param[28] = mnameTrns;
	param[29] = roleName;
	param[30] = partyTypeTrns;
	param[31] = partyRoleTypeId;
	
	try {
		dbUtility = new DBUtility();
		dbUtility.setAutoCommit(false);
		if (param != null) {
			sql = RegCommonSQL.UPDATE_PARTY_ORG_DETLS;
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeUpdate(param);
			if (flag) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
		}
	}catch (Exception e) {
		flag = false;
			try {
				dbUtility.rollback();
			} catch (Exception ex) {
				logger
						.debug("Exception in RegCommonDAO : updateDetailsOfIndividual() rollback.");
			}
			logger.debug(e.getMessage(),e);
	} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO updateDetailsOfIndividual() " + e.getStackTrace());
			}
		}
		return flag;
	
}

}
