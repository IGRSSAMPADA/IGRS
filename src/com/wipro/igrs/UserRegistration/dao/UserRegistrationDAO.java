/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRegistrationDAO.java
 * Author      :   Nihar Ranjan Mishra 
 * Description :   Represents the DAO Class for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  26th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.UserRegistration.dao;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;
import com.wipro.igrs.UserRegistration.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.reginit.dto.CommonDTO;

/**
 * @author nihraa
 *
 */
public class UserRegistrationDAO {

	DBUtility dbUtil;
	private static Logger logger = 
		(Logger) Logger.getLogger(UserRegistrationDAO.class);
	/**
	 * @throws Exception
	 */
	public UserRegistrationDAO()   {

		
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getCountry() {
		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getCountry();
		} catch (Exception e) {
			logger.info("Exception in getCountry():" + e);
		}
		return list;
	}

	public ArrayList getProofId() {
		ArrayList list = null;
		String sql;
		try {
			 dbUtil = new DBUtility();
			// sql = CommonSQL.SELECT_PHOTO_PROOF_TYPE;
			 sql = CommonSQL.SELECT_PHOTO_PROOF_TYPE_HINDI;
			 dbUtil.createStatement();
			 list = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			logger.info("Exception in getCountry():" + e);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}
	
	
	/**
	 * @param countryId
	 * @return ArrayList
	 */
	public ArrayList getStateList(String countryId) {
		ArrayList list = null;
		IGRSCommon igrsCommon;

		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getState(countryId);
		} catch (Exception e) {
			logger.info("Exception in getState():" + e);
		}
		return list;
	}
	
	
	/**
	 * @param countryId
	 * @return ArrayList
	 */
	public ArrayList getStoredPswd(String loginID) {
		ArrayList list=null;
		try {
			String arry[] = new String[1];
			if (loginID != null) {
				arry[0] = loginID;
			}
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.USER_PSWD_QUERY);
			logger.debug("Wipro in IGRSCommon - getStoredPswd() after creating preparedstatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - getStoredPswd() after excuteQuery");
		}catch(Exception e){
			logger.debug(e);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}
	
	
	/**
	 * @param state
	 * @return ArrayList
	 */
	public ArrayList getCityDist(String state) {
		ArrayList list = null;
		IGRSCommon igrsCommon;

		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getDistrict(state);
		} catch (Exception e) {
			logger.info("Exception in getState():" + e);
		}
		return list;
	}
	
	
	/**
	 * @return ArrayList
	 */
	public ArrayList getHintQuestions() {
		logger.info("DAO:   We are in getHintQuestions()");
		ArrayList list = null;
		String sql;
		try {
			 dbUtil = new DBUtility();
			// sql = CommonSQL.SELECT_QUESTION;
			 sql = CommonSQL.SELECT_QUESTION_HINDI;
			 dbUtil.createStatement();
			 list = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			logger.info("Exception in getCountry():" + e);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}
	/**
	 * @param type
	 * @return ArrayList
	 */
	public ArrayList getType(String type) {
		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getCommon(type);
		} catch (Exception e) {
			logger.info("Exception in getCommon():" + e);
		}
		return list;
	}
	/**
	 * @param sParam
	 * @return boolean
	 */
	public boolean insertSPRegistrationDetails(String[] sParam, String[]
		 uParam, String[] LiRoleParam){
		boolean spLicenseDetailsSubmit = false;

		try {
			logger.info("Before inserting user details ");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL
				.USER_SP_LICENSING_DETAILS_INSERT);
			spLicenseDetailsSubmit = dbUtil.executeUpdate(sParam);
			logger.info("After inserting user details ");

			if (spLicenseDetailsSubmit) {
				logger.debug(""+spLicenseDetailsSubmit);
				if (uParam != null) {
					if (uParam.length > 0) {
						dbUtil.createPreparedStatement(CommonSQL
							.USER_DETAILS_UPDATE);
						spLicenseDetailsSubmit = dbUtil.executeUpdate(uParam);
						logger.debug(""+spLicenseDetailsSubmit);
					}
				}

			}
			
			if (spLicenseDetailsSubmit) {
				logger.debug(""+spLicenseDetailsSubmit);
				if (LiRoleParam != null) {
					if (LiRoleParam.length > 0) {
						dbUtil.createPreparedStatement(CommonSQL
							.USER_LICENSED_ROLE_UPDATE);
						spLicenseDetailsSubmit = dbUtil.executeUpdate
						(LiRoleParam);
						logger.debug(""+spLicenseDetailsSubmit);
					}
				}

			}
			if (spLicenseDetailsSubmit) {
				dbUtil.commit();
			}else {
				dbUtil.rollback();
			}

		}catch (Exception x) {
				logger.info("Exception in insertUserRegDetails() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return spLicenseDetailsSubmit;
	}
	
		/**
		 * @param ref
		 * @return ArrayList
		 */
	public ArrayList getUserDetails(String ref) {
		ArrayList list = null;
		try {
				logger.info("Before getting User Details... ");
				dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.USER_DETAILS_FETCH_QUERY);
				logger.info("SQL:" + CommonSQL.USER_REF_ID_DETAILS_FETCH_QUERY);

			String param[] = new String[1];
			param[0] = ref;
			list = dbUtil.executeQuery(param);
				logger.info("After getting User Details... ");
		} catch (Exception x) {
				logger.info("Exception in getUserDetails() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

		
	/**
		 * @param spRefID
		 * @return ArrayList
		 */
	public ArrayList getUserSPLicenseDetails(String spRefID){
		ArrayList list = null;
		try {
				logger.info("DAO:  Before getting Reference ID Details... ");
				dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.USER_REF_ID_DETAILS_FETCH_QUERY);
			logger.debug("DAO:    SQL:  " + CommonSQL.USER_REF_ID_DETAILS_FETCH_QUERY);

			String param[] = new String[1];
			param[0] = spRefID;
			list = dbUtil.executeQuery(param);
				logger.info("DAO:   After getting User Details... ");
		} catch (Exception x) {
				logger.info("DAO:    Exception in getUserSPLicenseDetails() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
		
	}
	
	/**
	 * @param spRefID
	 * @return ArrayList
	 */
	public ArrayList userSPLicenseDetails(String spRefID){
		ArrayList list = null;
		try {
			logger.info("DAO:  Before getting Reference ID Details...");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL
				.USER_LICENSE_DETAILS_FETCH_QUERY);
			logger.debug("DAO:    SQL:  " + CommonSQL.USER_LICENSE_DETAILS_FETCH_QUERY);

			String param[] = new String[1];
			param[0] = spRefID;
			list = dbUtil.executeQuery(param);
			} catch (Exception x) {
			logger.info("DAO:    Exception in getUserSPLicenseDetails() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}
	
	public ArrayList checkUserName(String[] uname)
	{
		ArrayList list = null;
		try {
				logger.debug("before getting user validation ");
				dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.USER_VALIDATION_QUERY);
				logger.debug("SQL:" + CommonSQL.USER_VALIDATION_QUERY);
			list = dbUtil.executeQuery(uname);
		} catch (Exception x) {
				logger.debug("Exception in checkUserName() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;

	}
		/**
		 * @param fDate
		 * @param tDate
		 * @return ArrayList
		 */
	public ArrayList getSPLicenseView(String fDate, String tDate){
		String sql = CommonSQL.USER_SP_LICENSE_VIEW;

			//logger.info(fDate +  ":" +  tDate);
		if (fDate != null || tDate != null) {
			if ((fDate != null && tDate != null) && (!"".equals(fDate.trim()) 
				 && !"".equals(tDate.trim())))
			{
				sql += " TRUNC(ISSUANCE_DATE) BETWEEN TO_CHAR(TO_DATE('" + fDate
					+ "','mm/dd/yy')) AND TO_CHAR(TO_DATE('" + tDate 
					+ "','mm/dd/yy'))";
			}
		}
		ArrayList list = null;
		try {
				//logger.info("before getting view details " + sql);
			dbUtil = new DBUtility();
			
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
				//logger.info("after getting view details ");
			 
				//logger.info("View Details retrieved successfully ");
		} catch (Exception x) {
				//logger.info("Exception in getWillDeposit() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;

	}


	public boolean insertUserRegDetails(String[] param, String[] userParam,String[] regUser) {
		boolean userDetailsSubmit = false;

		try {
			logger.debug("Before inserting user details ");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.USER_REG_DETAILS_INSERT);
			userDetailsSubmit = dbUtil.executeUpdate(param);
		
			if (userDetailsSubmit) {
				if (userParam != null) {
					if (userParam.length > 0) {
							logger.info("BEFORE :  User Credentials");
						dbUtil.createPreparedStatement(CommonSQL.USER_CREDENTIALS_INSERT);
						userDetailsSubmit = dbUtil.executeUpdate(userParam);
							logger.info("AFTER  :  User Credentials");							
						
						
						if (userDetailsSubmit) 
						{
							dbUtil.commit();
						}
					}
				}
				if(regUser.length > 0)
				    {
				    System.out
					    .println("length is "+regUser.length);
				     dbUtil.createPreparedStatement(CommonSQL.USER_ROLE_INSERT);
					userDetailsSubmit = dbUtil.executeUpdate(regUser);
						logger.info("AFTER  :  User Credentials");
						dbUtil.commit();
				    }
				
			
			}
		 
			logger.info("User Credential Details Inserted ");
		} catch (Exception x) {
			logger.debug("Exception in insertUserRegDetails() :- " + x,x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return userDetailsSubmit;
	}
	
	/**
	 * @param genPinparam
	 * @return
	 */
	public boolean updatePswdDetails(String[] param) {
		boolean pinDetailsUpdated = true;

		try {
			logger.info("Before updating password details ");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.UPDATE_PSWD_CREDENTIALS);
			logger.info("SQL:" + CommonSQL.UPDATE_PSWD_CREDENTIALS);
			pinDetailsUpdated = dbUtil.executeUpdate(param);
			logger.info("After updating password details ");
			
			if(pinDetailsUpdated) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
			
			logger.info("Password Details updated ");

		} catch (Exception x) {
			logger.info("Exception in updatePswdDetails() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return pinDetailsUpdated;
	}
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
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			currDateTime = dbUtil.executeQry(CommonSQL.CURRENT_DATE_TIME);
			
		} catch (Exception exception) {
			logger.debug("Exception in getCurrDateTime" + exception);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return currDateTime;

	}
	/**
	 * for getting sequence id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getUserTxnIdSeq() throws Exception {

       // int regTxnIdSeq = 0;
        String seq="";
        
        try {
        	dbUtil = new DBUtility();
        	dbUtil.createStatement();
        	seq = dbUtil.executeQry(CommonSQL.GET_USER_TXN_ID_SEQ);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getUserTxnIdSeq-UserRegistrationDAO" + exception);

        }finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("UserRegistrationDAO in dao start" + e.getStackTrace());
			}
		}

        return seq;

}
	/**
	 * for getting all Occupation List
	 * @return ArrayList
	 */
	public ArrayList getOccupationList(String locale) {
		ArrayList mainList=new ArrayList();
		String sql;
		UserRegistrationDTO dto;
		try {
			dbUtil = new DBUtility();
		//sql = CommonSQL.GET_OCCUPATION_LIST;
			sql = CommonSQL.GET_OCCUPATION_LIST_HINDI;
		dbUtil.createStatement();
        ArrayList list = dbUtil.executeQuery(sql);
        mainList = new ArrayList();
        ArrayList subList = null;
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new UserRegistrationDTO();
            dto.setId(subList.get(0).toString());
                  
            if(locale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());
            }
            
            
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("UserRegistrationDAO in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("UserRegistrationDAO in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	
	
	/**
	 * for getting all Categories from DB.
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getCategoryList(String locale) {
		ArrayList mainList=new ArrayList();
		String sql;
		CommonDTO dto;
		 try {
			 dbUtil = new DBUtility();
		//sql = CommonSQL.SELECT_CATEGORY;
			 sql = CommonSQL.SELECT_CATEGORY_HINDI;
		dbUtil.createStatement();
        ArrayList list = dbUtil.executeQuery(sql);
        mainList = new ArrayList();
        ArrayList subList = null;
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new CommonDTO();
            dto.setId(subList.get(0).toString());
            if(locale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());
            }
            mainList.add(dto);
        	}
		 }catch (Exception e) {
			 logger.error("UserRegDAO in dao start" + e.getStackTrace());
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("UserRegDAO in dao start" + e.getStackTrace());
			}
		}
        return mainList;
	}
	
	

}