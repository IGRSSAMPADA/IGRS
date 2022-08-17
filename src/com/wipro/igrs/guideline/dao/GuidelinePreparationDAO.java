
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
 * FILE NAME: GuidelinePreparationDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    6th MAY 2008 
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DATA ACCESS OBJECTS CLASS FOR GUIDELINE PREPARATION ACTION.
 */


package com.wipro.igrs.guideline.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleCallableStatement;

import org.apache.log4j.Logger;

import com.wipro.igrs.CitizenFeedback.sql.CitizenFeedbackSQL;
import com.wipro.igrs.areaManagement.sql.areaManagementSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DBUtilityTest;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.sql.CommonSQL;
import com.wipro.igrs.guideline.sql.GuidelineNewSQL;
/**
 * @author NIHAR M.
 *
 */
import com.wipro.igrs.newPropvaluation.sql.PropertyValuationSQL;
import com.wipro.igrs.regCompChecker.sql.RegCompCheckerSQL;

/**
 * Modified BY SIMRAN
 */
public class GuidelinePreparationDAO {
	Connection con;
	DBUtility dbUtil;

	
	 	Statement st = null;
	   PreparedStatement pst = null;
	    ResultSet rst = null;
	    CallableStatement clstmt = null;
	    CallableStatement oclstmt = null;
	    DBUtility dbUtility = null;
		private CallableStatement callstmt  ;
		GuidelineDTO formDTO = new GuidelineDTO(); 
	
		private Logger logger = (Logger) Logger
		.getLogger(GuidelinePreparationDAO.class);
	


	/**
	 * @return ArrayList that holds District List that are active in master table
	 */
	public ArrayList getDistrictList() {
		GuidelineDTO  district ;
		ArrayList districtList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			
			
				
			 try {
				 dbUtility = new DBUtility();
					
					String sql =CommonSQL.DISTRICT;
					dbUtility.createStatement();
					list2=dbUtility.executeQuery(sql);
		            
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		           logger.debug("DAO- executeQuery(String): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO- executeQuery(String): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				district = new GuidelineDTO();

				district.setDistrictID((String)list1.get(0));
				district.setDistrict((String)list1.get(1));
				district.setHdnDistrict((String)list1.get(0)+"~"+(String)list1.get(1));
				districtList.add(district);
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
		return districtList;
	}
	
	/*
	 * 
	 * BELOW CODE COMMENTED BY SIMRAN AS THESE METHODS ARE NO LONGER USED IN GUIDELINE MODULE
	 */

	/*
	public ArrayList getMohViewList(String wardId){
		GuidelineDTO  mohalla ;
		ArrayList mohList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		//String sql= CommonSQL.GET_MOHALLAS_DRAFT_VIEW;

		try {
			
			connTest = DBUtilityTest.openConnection();
			pst = connTest.prepareStatement(CommonSQL.GET_MOHALLAS_DRAFT_VIEW);
			//dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(sql);
			
			//list2 = dbUtil.executeQuery(fyear);
			
			try
			{	
			
	                pst.setString(1, wardId);
	                rst = pst.executeQuery();
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		            ResultSetMetaData rsmd = rst.getMetaData();
		            int col_count = rsmd.getColumnCount();
		            while (rst.next()) {
		                ArrayList row_list = new ArrayList();
		                for (int i = 1; i <= col_count; i++) {
		                    row_list.add(rst.getString(i));
		                } //for
		                list2.add(row_list);
		            } //while
		            pst.clearParameters();
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();

	

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohalla = new GuidelineDTO();

				mohalla.setMohallaID((String)list1.get(0));
				mohalla.setMohalla((String)list1.get(1));
				mohalla.setFinancialYear((String)list1.get(2));

				mohalla.setHdnMohalla((String)list1.get(0)+"="+(String)list1.get(1));
				mohList.add(mohalla);
			}

		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
					rst.close();
					pst.close();
					DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return mohList;
	}

	public ArrayList getMohList2(String wardid){
		GuidelineDTO  mohalla ;
		ArrayList mohList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

			
			try {
				
				connTest = DBUtilityTest.openConnection();
				pst = connTest.prepareStatement(CommonSQL.MOHALLA_NOVALUE);
				
				try
				{	
				
		                pst.setString(1, wardid);
		                rst = pst.executeQuery();
			            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
			            
			            ResultSetMetaData rsmd = rst.getMetaData();
			            int col_count = rsmd.getColumnCount();
			            while (rst.next()) {
			                ArrayList row_list = new ArrayList();
			                for (int i = 1; i <= col_count; i++) {
			                    row_list.add(rst.getString(i));
			                } //for
			                list2.add(row_list);
			            } //while
			            pst.clearParameters();
			        } catch (SQLException sqle) {
			            logger.debug("DAO - executeQuery(String[]): " +
			                               sqle.getMessage());
			            throw sqle;
			        } catch (NumberFormatException nfe) {
			            logger.debug("DAO- executeQuery(String[]): " +
			                               nfe.getMessage());
			            throw nfe;
			        } catch (IllegalArgumentException iae) {
			            logger.debug("DAO - executeQuery(String[]): " +
			                               iae.getMessage());
			            throw iae;
			        }
			        list2.trimToSize();


			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohalla = new GuidelineDTO();
				mohalla.setMohallaID((String)list1.get(0));
				mohalla.setMohalla((String)list1.get(1));
				mohalla.setHdnMohalla((String)list1.get(0)+"="+(String)list1.get(1));
				mohList.add(mohalla);
			}
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
					rst.close();
					pst.close();
					DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return mohList;
	}
	 
	public ArrayList getVillage(String patwariId){

		logger.debug("GuidelinePreparationDAO:: Called:  getVillage()");
		GuidelineDTO  village ;
		ArrayList villageList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			
			
			connTest = DBUtilityTest.openConnection();
			
			pst = connTest.prepareStatement(CommonSQL.GET_VILLAGE_VIEW);
			
			try
			{	
			
	                pst.setString(1, patwariId);
	                rst = pst.executeQuery();
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		            ResultSetMetaData rsmd = rst.getMetaData();
		            int col_count = rsmd.getColumnCount();
		            while (rst.next()) {
		                ArrayList row_list = new ArrayList();
		                for (int i = 1; i <= col_count; i++) {
		                    row_list.add(rst.getString(i));
		                } //for
		                list2.add(row_list);
		            } //while
		            pst.clearParameters();
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				village = new GuidelineDTO();
				village.setBasePeriodFrom((String)list1.get(0));
				village.setBasePeriodTo((String)list1.get(1));
				village.setVillageID((String)list1.get(2));
				village.setVillage((String)list1.get(3));
				villageList.add(village);
			}
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
					rst.close();
					pst.close();
					DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return villageList;
	}

	public ArrayList getWardList(String tehsilid){
		GuidelineDTO  ward ;
		ArrayList wardList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			
			//dbUtil.createPreparedStatement(CommonSQL.WARD);
			//list2 = dbUtil.executeQuery(tehsilid);
				connTest = DBUtilityTest.openConnection();
				
				pst = connTest.prepareStatement(CommonSQL.WARD);
				
				try
				{	
				
		                pst.setString(1, tehsilid);
		                rst = pst.executeQuery();
			            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
			            
			            ResultSetMetaData rsmd = rst.getMetaData();
			            int col_count = rsmd.getColumnCount();
			            while (rst.next()) {
			                ArrayList row_list = new ArrayList();
			                for (int i = 1; i <= col_count; i++) {
			                    row_list.add(rst.getString(i));
			                } //for
			                list2.add(row_list);
			            } //while
			            pst.clearParameters();
			        } catch (SQLException sqle) {
			            logger.debug("DAO - executeQuery(String[]): " +
			                               sqle.getMessage());
			            throw sqle;
			        } catch (NumberFormatException nfe) {
			            logger.debug("DAO- executeQuery(String[]): " +
			                               nfe.getMessage());
			            throw nfe;
			        } catch (IllegalArgumentException iae) {
			            logger.debug("DAO - executeQuery(String[]): " +
			                               iae.getMessage());
			            throw iae;
			        }
			        list2.trimToSize();

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				ward = new GuidelineDTO();
				ward.setWardID((String)list1.get(0));
				ward.setWard((String)list1.get(1));
				ward.setHdnWard((String)list1.get(0)+"~"+(String)list1.get(1));

				logger.debug(""+ward);
				wardList.add(ward);
			}
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();

		} finally {
			try {
					rst.close();
					pst.close();
					DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return wardList;
	}

	public ArrayList getTehsilList(String distid) {
		GuidelineDTO  tehsil ;
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			
			connTest = DBUtilityTest.openConnection();
			
			pst = connTest.prepareStatement(CommonSQL.TEHSIL);
			
			try
			{	
			
	                pst.setString(1, distid);
	                rst = pst.executeQuery();
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		            ResultSetMetaData rsmd = rst.getMetaData();
		            int col_count = rsmd.getColumnCount();
		            while (rst.next()) {
		                ArrayList row_list = new ArrayList();
		                for (int i = 1; i <= col_count; i++) {
		                    row_list.add(rst.getString(i));
		                } //for
		                list2.add(row_list);
		            } //while
		            pst.clearParameters();
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		
		        
			
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				tehsil = new GuidelineDTO();
				tehsil.setTehsilID((String)list1.get(0));
				tehsil.setTehsil((String)list1.get(1));
				tehsil.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(1));

				tehsilList.add(tehsil);
			}
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {

				rst.close();
				pst.close();
				DBUtilityTest.closeConnection();
				
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return tehsilList;
	}
*/

	/**
	 * @return ArrayList that holds list of Area Types(Urban/rural etc)
	 */
	public ArrayList getAreasTypeList(GuidelineDTO ggdto)throws Exception{

		GuidelineDTO  areaType ;
		ArrayList areaList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage(); 
		String flag = ggdto.getAreaFlag(); 
		String sql="";
		int rurcnt=0;
		int urbcnt=0;
		int guidecnt=0;
		try {
			dbUtility = new DBUtility();
			
			//st =
				// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			//list2 = dbUtil.executeQuery(CommonSQL.FINANCIAL);
			try {
				
				
				if(flag.equalsIgnoreCase("2"))
				{
					
				
					String ruralcount=null;
					String urbancount=null;
					String guidecount=null;
					dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_EN_RURAL);
					ruralcount=dbUtility.executeQry(new String[]{ggdto.getTehsilID(),ggdto.getGuideLineID()});
					dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_EN_URBAN);
					urbancount=dbUtility.executeQry(new String[]{ggdto.getTehsilID(),ggdto.getGuideLineID()});
					dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_EN_GIDCNT);
					guidecount=dbUtility.executeQry(new String[]{ggdto.getGuideLineID()});
					rurcnt=Integer.valueOf(ruralcount);
					urbcnt=Integer.valueOf(urbancount);
					guidecnt=Integer.valueOf(guidecount);
					
					if(guidecnt==0)
					{
						ggdto.setGuidFlag("Y");
						
						return areaList;
						
						
					} 
					
					
					if (rurcnt>0)
						
					{
						 sql=CommonSQL.AREA_TYPE_RU;
						dbUtility.createStatement();
					}
					
					if (urbcnt>0)
					{
						
						 sql=CommonSQL.AREA_TYPE_UR;
						dbUtility.createStatement();
					}
					
					
					if(rurcnt==0 && urbcnt==0)
					{
						return areaList;
						
					}
					
					if(rurcnt!=0 && urbcnt!=0)
					{
						 sql=CommonSQL.AREA_TYPE;
						dbUtility.createStatement();
					}
					
				}
				
				//logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^^changed^^^^^^^^^^^^^");
				
				if(flag.equalsIgnoreCase("1"))
				{
					 sql=CommonSQL.AREA_TYPE;
					dbUtility.createStatement();
				}
				
				list2=dbUtility.executeQuery(sql);
	           
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeQuery(String): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	           logger.debug("DAO- executeQuery(String): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO - executeQuery(String): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        list2.trimToSize();
	        
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				areaType = new GuidelineDTO();

				areaType.setAreaTypeID((String)list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					areaType.setAreaName((String)list1.get(1));

					areaType.setHdnArea((String)list1.get(0)+"~"+(String)list1.get(1));
				}
				else
				{
					areaType.setAreaName((String)list1.get(2));

					areaType.setHdnArea((String)list1.get(0)+"~"+(String)list1.get(2));
				}
				
				areaList.add(areaType);
			}
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
			throw x;
		} finally {
			try {
					
				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
				throw ex;
			}
		}
		return areaList;
	}


	/*
	 * 
	 * BELOW CODE COMMENTED BY SIMRAN AS THESE METHODS ARE NO LONGER USED IN GUIDELINE MODULE
	 */
	
	/*
	public ArrayList getPatwariList(String tehsilid){

		GuidelineDTO  patwari;
		ArrayList patwariList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			
			
			
			//list2 = dbUtil.executeQuery(tehsilid);
			
			connTest = DBUtilityTest.openConnection();
			
			pst = connTest.prepareStatement(CommonSQL.PATWARI);
			
			try
			{	
			
	                pst.setString(1, tehsilid);
	                rst = pst.executeQuery();
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		            ResultSetMetaData rsmd = rst.getMetaData();
		            int col_count = rsmd.getColumnCount();
		            while (rst.next()) {
		                ArrayList row_list = new ArrayList();
		                for (int i = 1; i <= col_count; i++) {
		                    row_list.add(rst.getString(i));
		                } //for
		                list2.add(row_list);
		            } //while
		            pst.clearParameters();
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				patwari = new GuidelineDTO();
				patwari.setPatwariID((String)list1.get(0));
				patwari.setPatwari((String)list1.get(1));
				patwari.setHdnPatwari((String)list1.get(0)+"~"+(String)list1.get(1));

				patwariList.add(patwari);
			}

		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
					rst.close();
					pst.close();
					DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return patwariList;
	}

	public ArrayList getIndividualMohallaDetailsReadOnly(String[] param){

		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.INDIVIDUAL_MOHALLA_VIEW_DETAILS);
			logger.debug(CommonSQL.INDIVIDUAL_MOHALLA_VIEW_DETAILS);
			connTest = DBUtilityTest.openConnection();
			
			pst = connTest.prepareStatement(CommonSQL.INDIVIDUAL_MOHALLA_VIEW_DETAILS);
			 try {
		            for (int i = 0; i < param.length; i++) {
		            	logger.debug("Wipro : arr[" + i + "] = " + param[i]);
		                pst.setString(i + 1, param[i]);
		            }
		            rst = pst.executeQuery();
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            ResultSetMetaData rsmd = rst.getMetaData();
		            int col_count = rsmd.getColumnCount();
		            while (rst.next()) {
		                ArrayList row_list = new ArrayList();
		                for (int i = 1; i <= col_count; i++) {
		                    row_list.add(rst.getString(i));
		                } //for
		                list2.add(row_list);
		            } //while
		            pst.clearParameters();
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		       
			for (int i = 0; i < list2.size(); i++) {

				list1 = (ArrayList)list2.get(i);
				mohallaDetails = new GuidelineDTO();

				mohallaDetails.setPropertyID((String)list1.get(1));
				mohallaDetails.setPropertyTypeName((String)list1.get(2));
				mohallaDetails.setL1Name((String)list1.get(3));
				mohallaDetails.setL2Name((String)list1.get(4));
				mohallaDetails.setGuideLineValue((String)list1.get(5));

				mohallaDetailsList.add(mohallaDetails);
			}
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
					rst.close();
					pst.close();
					DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return mohallaDetailsList;
	}

	public ArrayList getIndividualMohallaDetails(String[] param) {

		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS_BASE);
			//logger.debug(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS_BASE);
			
			connTest = DBUtilityTest.openConnection();
			
			pst = connTest.prepareStatement(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS );
			
			try {
	            for (int i = 0; i < param.length; i++) {
	            	logger.debug("Wipro : param[" + i + "] = " + param[i]);
	                pst.setString(i + 1, param[i]);
	            }
	            rst = pst.executeQuery();
	            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
	            ResultSetMetaData rsmd = rst.getMetaData();
	            int col_count = rsmd.getColumnCount();
	            while (rst.next()) {
	                ArrayList row_list = new ArrayList();
	                for (int i = 1; i <= col_count; i++) {
	                    row_list.add(rst.getString(i));
	                } //for
	                list2.add(row_list);
	            } //while
	            pst.clearParameters();
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeQuery(String[]): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	            logger.debug("DAO - executeQuery(String[]): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO - executeQuery(String[]): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        list2.trimToSize();

			//list2 = dbUtil.executeQuery(param);
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohallaDetails = new GuidelineDTO();

				mohallaDetails.setGuideLineID((String)list1.get(0));
				mohallaDetails.setDistrictID((String)list1.get(1));
				mohallaDetails.setTehsilID((String)list1.get(2));
				mohallaDetails.setWardID((String)list1.get(3));
				mohallaDetails.setMohallaID((String)list1.get(4));
				mohallaDetails.setAreaTypeID((String)list1.get(5));
				mohallaDetails.setPropertyID((String)list1.get(6));
				mohallaDetails.setAverageValue((String)list1.get(7));
				mohallaDetails.setPropertyTypeName((String)list1.get(8));
				mohallaDetails.setL1_ID((String)list1.get(9));
				mohallaDetails.setL1Name((String)list1.get(10));
				mohallaDetails.setL2_ID((String)list1.get(11));
				mohallaDetails.setL2Name((String)list1.get(12));

				mohallaDetails.setUnitMeasureID((String)list1.get(13));	
				mohallaDetails.setGuideLineValue((String)list1.get(14));
				mohallaDetails.setDurationFrom((String)list1.get(15));
				mohallaDetails.setDurationTo((String)list1.get(16));
				mohallaDetails.setFinancialYear((String)list1.get(17));
				mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(18)));
				mohallaDetails.setStatus((String)list1.get(19));
				mohallaDetails.setCreatedBy((String)list1.get(20));
				mohallaDetails.setCreatedDate((String)list1.get(21));
				mohallaDetails.setUpdatedBy((String)list1.get(22));
				mohallaDetails.setUpdatedDate((String)list1.get(23));
				mohallaDetails.setBasePeriodFrom((String)list1.get(24));
				mohallaDetails.setBasePeriodTo((String)list1.get(25));
				
				mohallaDetails.setPropertyTypeName((String)list1.get(0));
				mohallaDetails.setL1Name((String)list1.get(1));
				mohallaDetails.setL2Name((String)list1.get(2));
				mohallaDetails.setGuideLineValue((String)list1.get(3));
				mohallaDetails.setAverageValue((String)list1.get(4));
				mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
				mohallaDetails.setMohallaID((String)list1.get(6));
				logger.debug("mohalla id:-   "+(String)list1.get(6));
				logger.debug("mohalla:-   "+(String)list1.get(7));
				mohallaDetails.setMohalla((String)list1.get(7));
				mohallaDetails.setGuideLineID((String)list1.get(8));
				mohallaDetails.setAreaTypeID((String)list1.get(9));
				mohallaDetails.setPropertyID((String)list1.get(10));
				mohallaDetails.setL1_ID((String)list1.get(11));
				mohallaDetails.setL2_ID((String)list1.get(12));

				mohallaDetailsList.add(mohallaDetails);
			}
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				rst.close();
				pst.close();
				DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return mohallaDetailsList;
	}

*/

/**
 *  This function is used to fetch all the available property types for mohalla/village
 *   @return ArrayList
 */
	
	public ArrayList getIndividualMohallaDetails2(GuidelineDTO ggdto) {

		logger.debug("DAO: Called: getIndividualMohallaDetails2()");

		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		try {
			dbUtility = new DBUtility();
			//st =
				// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			 try {
				 String sql =CommonSQL.INDIVIDUAL_MOHALLA_DETAILS_NOVALUE;
				 dbUtility.createStatement();
				 list2=dbUtility.executeQuery(sql);
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		           logger.debug("DAO- executeQuery(String): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO- executeQuery(String): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        logger.debug(list2.size());
		        
		      for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohallaDetails = new GuidelineDTO();
				mohallaDetails.setPropertyID((String)list1.get(0));
				mohallaDetails.setL1_ID((String)list1.get(3));
				mohallaDetails.setL2_ID((String)list1.get(6));
				mohallaDetails.setUomID((String)list1.get(9));
				if(lang.equalsIgnoreCase("en"))
				{
					mohallaDetails.setPropertyTypeName((String)list1.get(1));
					mohallaDetails.setL1Name((String)list1.get(4));
					mohallaDetails.setL2Name((String)list1.get(7));
					mohallaDetails.setUomName((String)list1.get(10));
				}
				else
				{
					mohallaDetails.setPropertyTypeName((String)list1.get(2));
					mohallaDetails.setL1Name((String)list1.get(5));
					mohallaDetails.setL2Name((String)list1.get(8));
					mohallaDetails.setUomName((String)list1.get(11));
				}
				
				
				

				mohallaDetailsList.add(mohallaDetails);
				logger.debug(mohallaDetailsList.size());
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
		return mohallaDetailsList;
	}

	/*
	 * 
	 * BELOW CODE COMMENTED BY SIMRAN AS THESE METHODS ARE NO LONGER USED IN GUIDELINE MODULE
	 */
	
	/*
	public String getMohallaName(String uname)
	{
		String list = null;
		try {
			//dbUtil.createPreparedStatement(CommonSQL.GET_MOHALLA_NAME);
			//logger.debug("SQL:" + CommonSQL.GET_MOHALLA_NAME);
			
			connTest = DBUtilityTest.openConnection();
			
			pst = connTest.prepareStatement(CommonSQL.GET_MOHALLA_NAME);
			//list = dbUtil.executeQry(uname);
			
			try {
	            rst = st.executeQuery(uname);
	            while (rst.next()) {
	                String data = rst.getString(1);
	                if (rst.wasNull()) {
	                    data = "";
	                }
	                list = data;
	            } //while
	        } catch (SQLException sqle) {
	        	logger.debug("DAO - executeQry(String): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	        	logger.debug("DAO  - executeQry(String): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	        	logger.debug("DAO  - executeQry(String): " +
	                               iae.getMessage());
	            throw iae;
	        }
	      
			logger.debug("DAO :-  "+list);
		} catch (Exception x) {
			logger.debug("DAO:Catch:  Exception in getMohallaName():- "	 	+ x.getStackTrace()    +x.getCause()+      
					x.getClass());
			x.printStackTrace();
		}finally {
			try {
				rst.close();
				pst.close();
				DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug("DAO:Finally: Exception in getMohallaName():-" 	+ ex.getMessage());
				ex.printStackTrace();
			}
		}
		return list;

	}


	public boolean insertMohallaMasterDetails(){

		logger.debug("DAO:-  insertMohallaMasterDetails");
		boolean mohallaInserted = false;
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.MOHALLA_MASTER_DETAILS_INSERT);
			connTest = DBUtilityTest.openConnection();
			DBUtilityTest db= new DBUtilityTest();
			connTest.setAutoCommit(false);
			int j = 0;
			
			pst = connTest.prepareStatement(CommonSQL.MOHALLA_MASTER_DETAILS_INSERT);
			
			logger.debug("DAO:-  insertMohallaMasterDetails:-  Query:-      "+CommonSQL.MOHALLA_MASTER_DETAILS_INSERT);

			String param = "A";
			

			mohallaInserted = dbUtil.executeUpdate(param);
			
			 try {
		            
		                pst.setString(1, param);
		            
		            logger.debug("Wipro : before execute update");
		            try {
		                j = pst.executeUpdate();
		            } catch (StringIndexOutOfBoundsException e) {
		                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
		            }
		            logger.debug("Wipro : after execute update");
		            pst.clearParameters();
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sqle.getMessage());
		           
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        } catch (StringIndexOutOfBoundsException sioobe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sioobe.getMessage());
		            throw sioobe;
		        }
		        if (j == 0)
		        	mohallaInserted = false;
		        mohallaInserted = true;
		        
			if(mohallaInserted) {
				connTest.commit();
			}
		} 
		catch (Exception x) {
			try
			{
				connTest.rollback();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	     		logger.debug(""+x.getMessage());
	     		x.printStackTrace();
			
		}finally {
			try {
					pst.close();
					DBUtilityTest.closeConnection();
			}catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		return mohallaInserted;
	}

	public boolean insertIndividualMohallaDetails(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, 
			String dTo, String roleId, String funId, String userId) throws Exception{  		//create

		logger.debug("DAO:-  insertIndividualMohallaDetails");
		boolean mohallaInserted = false;
		int j = 0;
		IGRSCommon igrsCommon = null;
		try {
			
			
				connTest = DBUtilityTest.openConnection();
		}
		catch (SQLException sqle) {
	           logger.debug(sqle);
	            throw sqle;
		}
		try
		{
			igrsCommon = new IGRSCommon();
			DBUtilityTest db= new DBUtilityTest();
			connTest.setAutoCommit(false);
			//createPreparedStatement(CommonSQL.INDIVIDUAL_MOHALLA_INSERT_CREATE);
			pst = connTest.prepareStatement(CommonSQL.INDIVIDUAL_MOHALLA_INSERT_CREATE);
			int guideStatus = CommonConstant.GUIDELINESTATUS_PENDING;
			for(int i = 0;i<mohallaDetails.size();i++ ){
				GuidelineDTO dto = (GuidelineDTO)mohallaDetails.get(i);

				
				try
				{
					//pst.setString( 1, gDTO.getDistrictID());
	                pst.setString( 2, gDTO.getTehsilID());
	                
	                String patID = gDTO.getPatwariID();
					String warID = gDTO.getWardID();
					
					if(patID != null && patID.length() > 0)
					{
						 pst.setString( 3, gDTO.getPatwariID());
					}
					else
						 pst.setString( 3, gDTO.getWardID());
					
					pst.setString( 4,gDTO.getMohallaID());
	                pst.setString( 5,gDTO.getAreaTypeID());
	                pst.setString( 6,dto.getPropertyID());
	                pst.setString( 7,dto.getL1_ID());
	                pst.setString( 8,dto.getL2_ID());
	                pst.setString( 9, dto.getUomID());
	                pst.setString( 10,""+dto.getProposedValue());
	                pst.setString( 11, dFrom);
	                pst.setString( 12,  dTo);
	                pst.setInt(13, guideStatus);
	                pst.setString( 14,  userId);
	                pst.setString( 15,  gDTO.getBasePeriodFrom());
	                pst.setString( 16,gDTO.getBasePeriodTo());
	                pst.setString( 17,gDTO.getFinancialYear());
	                pst.setString( 18, dto.getAverageValue());
	                pst.setString( 19,""+dto.getIncrementValue());
	                pst.setString( 20, "G");
	                
	                
	                try {
		                j = pst.executeUpdate();
		            } catch (StringIndexOutOfBoundsException e) {
		                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
		            }
		            logger.debug("Wipro : after execute update");
		            pst.clearParameters();
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sqle.getMessage());
		           
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        } catch (StringIndexOutOfBoundsException sioobe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sioobe.getMessage());
		            throw sioobe;
		        }
		       
			}
			
			 if (j == 0)
		           mohallaInserted = false;
		        mohallaInserted = true;
				if(mohallaInserted) {
					connTest.commit();
					igrsCommon.saveLogDet("igrs_guideline_data_temp","INSERT","T",funId,userId,roleId);
				}else {
					
					igrsCommon.saveLogDet("igrs_guideline_data_temp","INSERT","F",funId,userId,roleId);
				}

		} catch (Exception x) {
			
			connTest.rollback();
			logger.debug("ERROR:-   "+x.getMessage());
			x.printStackTrace();
		}finally {
			try {
					pst.close();
			}
			catch(Exception xe)
			{
				logger.debug("Close PreparedStatement Error:-"+xe);
				xe.printStackTrace();
					DBUtilityTest.closeConnection();
			}
			try
			{
				DBUtilityTest.closeConnection();
			}
			catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
		return mohallaInserted;
	}

	public ArrayList getMohList(String wardid){
		GuidelineDTO  mohalla ;
		ArrayList mohList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		//String sql= CommonSQL.GET_MOHALLAS_DRAFT_VIEW;
		try {
			
			//list2 = dbUtil.executeQuery(wardid);
				
				
					connTest = DBUtilityTest.openConnection();
					
					pst = connTest.prepareStatement(CommonSQL.GET_MOHALLAS_DRAFT_VIEW);
					
					try
					{	
					
			                pst.setString(1, wardid);
			                rst = pst.executeQuery();
				            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
				            
				            ResultSetMetaData rsmd = rst.getMetaData();
				            int col_count = rsmd.getColumnCount();
				            while (rst.next()) {
				                ArrayList row_list = new ArrayList();
				                for (int i = 1; i <= col_count; i++) {
				                    row_list.add(rst.getString(i));
				                } //for
				                list2.add(row_list);
				            } //while
				            pst.clearParameters();
				        } catch (SQLException sqle) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               sqle.getMessage());
				            throw sqle;
				        } catch (NumberFormatException nfe) {
				            logger.debug("DAO- executeQuery(String[]): " +
				                               nfe.getMessage());
				            throw nfe;
				        } catch (IllegalArgumentException iae) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               iae.getMessage());
				            throw iae;
				        }
				        list2.trimToSize();

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohalla = new GuidelineDTO();
			//	mohalla.setBasePeriodFrom((String)list1.get(0));
			//	mohalla.setBasePeriodTo((String)list1.get(1));	
			//	mohalla.setMohallaID((String)list1.get(0));
				mohalla.setMohalla((String)list1.get(1));
			//	mohalla.setStatus((String)list1.get(4));
				mohalla.setHdnMohalla((String)list1.get(0)+"="+(String)list1.get(1));
				mohList.add(mohalla);
				logger.debug(""+mohList.size());
			}
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				rst.close();
				pst.close();
				DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return mohList;
	}

	public boolean insertMohallaDetails(ArrayList mohallaDetails){//approve

		logger.debug("DAO:-  insertMohallaDetails");
		boolean mohallaInserted = false;
		int j = 0;
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
			
			connTest = DBUtilityTest.openConnection();
			
			pst = connTest.prepareStatement(CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
			connTest.setAutoCommit(false);
			
			
			int guideStatus = CommonConstant.GUIDELINESTATUS_FINAL;
			for(int i = 0;i<mohallaDetails.size();i++ ){
				GuidelineDTO dto = (GuidelineDTO)mohallaDetails.get(i);
				
			try
			{

				//pst.setString( 1 ,dto.getDistrictID());
				pst.setString( 2, dto.getTehsilID());
				pst.setString( 3, dto.getWardID());
				pst.setString( 4, dto.getMohallaID());
				pst.setString( 5, dto.getAreaTypeID());
				pst.setString( 6, dto.getPropertyID());
				pst.setString( 7, dto.getL1_ID());
				pst.setString( 8, dto.getL2_ID());
				pst.setString( 9, dto.getUnitMeasureID());
				pst.setString( 10, ""+dto.getGuideLineValue());
				pst.setString( 11, dto.getDurationFrom());
				pst.setString( 12, dto.getDurationTo());
				pst.setInt( 13, guideStatus );
				pst.setString( 14, dto.getCreatedBy());
				pst.setString( 15, dto.getCreatedDate());
				pst.setString( 16,"nihar");
				pst.setString( 17, dto.getBasePeriodFrom());
				pst.setString( 18, dto.getBasePeriodTo());
				pst.setString( 19, dto.getFinancialYear());
				pst.setString( 20, dto.getAverageValue());
				pst.setString( 21, ""+dto.getIncrementValue());
				pst.setString( 22, dto.getIncrementon());
				pst.setString( 23, dto.getGuideLineID());
				//logger.debug("moharray:     "+moharray[0]+" "+moharray[1]+" "+moharray[2]+" "+moharray[3]+" "+moharray[4]+" "+moharray[5]+" "+moharray[6]+" "+moharray[7]+" "+moharray[8]+" "+moharray[9]+" "+moharray[10]+" "+moharray[11]+" "+moharray[12]+" "+moharray[13]+" "+moharray[14]+" "+moharray[15]+" "+moharray[16]+" "+moharray[17]+" "+moharray[18]+" "+moharray[19]+" "+moharray[20]+" "+moharray[21]+" "+moharray[22]);
				//logger.debug("Query:-     "+CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
			
				try {
                j = pst.executeUpdate();
            } catch (StringIndexOutOfBoundsException e) {
                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
            }
            logger.debug("Wipro : after execute update");
            pst.clearParameters();
        } catch (SQLException sqle) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               sqle.getMessage());
           
            throw sqle;
        } catch (NumberFormatException nfe) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               iae.getMessage());
            throw iae;
        } catch (StringIndexOutOfBoundsException sioobe) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               sioobe.getMessage());
            throw sioobe;
        }
       
	}
	
	 if (j == 0)
           mohallaInserted = false;
        mohallaInserted = true;
			
        if(mohallaInserted){
				logger.debug("Data sucessfully set for the temp table.");
					mohallaInserted = insertMohallaMasterDetails();

				if(mohallaInserted){
					connTest.commit();
				}
				
			}
		} catch (Exception x) {
			try
			{
			connTest.rollback();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			logger.debug("Error-->  "+x.getMessage());
			x.printStackTrace();
		}finally {
			try {
					pst.close();
					DBUtilityTest.closeConnection();
			}catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		return mohallaInserted;
	}

	public boolean insertTempMohallaDetails(ArrayList mohallaDetails){                   //approve  ||   draft part ||    enter temp only

		logger.debug("DAO:-  insertMohallaDetails");
		boolean mohallaInserted = false;
		int j= 0;
		try {
			

			for(int i = 0;i<mohallaDetails.size();i++ ){
				GuidelineDTO dto = (GuidelineDTO)mohallaDetails.get(i);

				String[] moharray = new String[23];
				moharray[0]  = dto.getDistrictID();
				moharray[1]  = dto.getTehsilID();
				moharray[2]  = dto.getWardID();
				moharray[3]  = dto.getMohallaID();
				moharray[4]  = dto.getAreaTypeID();
				moharray[5]  = dto.getPropertyID();
				moharray[6]  = dto.getL1_ID();
				moharray[7]  = dto.getL2_ID();
				moharray[8]  = dto.getUnitMeasureID();
				moharray[9] = ""+dto.getGuideLineValue();
				moharray[10] = dto.getDurationFrom();
				moharray[11] = dto.getDurationTo();
				moharray[12] = "D";
				moharray[13] = dto.getCreatedBy();
				moharray[14] = dto.getCreatedDate();
				moharray[15] = "nihar";
				moharray[16] = dto.getBasePeriodFrom();
				moharray[17] = dto.getBasePeriodTo();
				moharray[18] = dto.getFinancialYear();
				moharray[19] = dto.getAverageValue();
				moharray[20] = ""+dto.getIncrementValue();
				moharray[21] = dto.getIncrementon();
				moharray[22] = dto.getGuideLineID();
				
				connTest = DBUtilityTest.openConnection();
				
				pst = connTest.prepareStatement(CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
				connTest.setAutoCommit(false);
				
				int guideStatus = CommonConstant.GUIDELINESTATUS_DRAFT;
				
				for(int i = 0;i<mohallaDetails.size();i++ ){
					GuidelineDTO dto = (GuidelineDTO)mohallaDetails.get(i);
					
				try
				{

					//pst.setString( 1 ,dto.getDistrictID());
					pst.setString( 2, dto.getTehsilID());
					pst.setString( 3, dto.getWardID());
					pst.setString( 4, dto.getMohallaID());
					pst.setString( 5, dto.getAreaTypeID());
					pst.setString( 6, dto.getPropertyID());
					pst.setString( 7, dto.getL1_ID());
					pst.setString( 8, dto.getL2_ID());
					pst.setString( 9, dto.getUnitMeasureID());
					pst.setString( 10, ""+dto.getGuideLineValue());
					pst.setString( 11, dto.getDurationFrom());
					pst.setString( 12, dto.getDurationTo());
					pst.setInt( 13, guideStatus);
					pst.setString( 14, dto.getCreatedBy());
					pst.setString( 15, dto.getCreatedDate());
					pst.setString( 16,"nihar");
					pst.setString( 17, dto.getBasePeriodFrom());
					pst.setString( 18, dto.getBasePeriodTo());
					pst.setString( 19, dto.getFinancialYear());
					pst.setString( 20, dto.getAverageValue());
					pst.setString( 21, ""+dto.getIncrementValue());
					pst.setString( 22, dto.getIncrementon());
					pst.setString( 23, dto.getGuideLineID());
					
				//logger.debug("moharray:     "+moharray[0]+" "+moharray[1]+" "+moharray[2]+" "+moharray[3]+" "+moharray[4]+" "+moharray[5]+" "+moharray[6]+" "+moharray[7]+" "+moharray[8]+" "+moharray[9]+" "+moharray[10]+" "+moharray[11]+" "+moharray[12]+" "+moharray[13]+" "+moharray[14]+" "+moharray[15]+" "+moharray[16]+" "+moharray[17]+" "+moharray[18]+" "+moharray[19]+" "+moharray[20]+" "+moharray[21]+" "+moharray[22]);
				//logger.debug("Query:-     "+CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
				//mohallaInserted = dbUtil.executeUpdate(moharray);
				
				try {
	                j = pst.executeUpdate();
	            } catch (StringIndexOutOfBoundsException e) {
	                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
	            }
	            logger.debug("Wipro : after execute update");
	            pst.clearParameters();
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeUpdate(String[]): " +
	                               sqle.getMessage());
	           
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	            logger.debug("DAO - executeUpdate(String[]): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO - executeUpdate(String[]): " +
	                               iae.getMessage());
	            throw iae;
	        } catch (StringIndexOutOfBoundsException sioobe) {
	            logger.debug("DAO - executeUpdate(String[]): " +
	                               sioobe.getMessage());
	            throw sioobe;
	        }
	       
		}
		
		 if (j == 0)
	           mohallaInserted = false;
	        mohallaInserted = true;
			if(mohallaInserted){
				
				logger.debug("Data sucessfully set for the temp table.");

				if(mohallaInserted){
					connTest.commit();
				}
				
			}
		} catch (Exception x) {
			try
			{
			connTest.rollback();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			logger.debug("Error-->  "+x.getMessage());
			x.printStackTrace();
		}finally {
			try {
					pst.close();
					DBUtilityTest.closeConnection();
				
			}catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		return mohallaInserted;
	}

	public boolean updateMohallaDetails(ArrayList mohallaDetails){

		logger.debug("DAO:-  updateMohallaDetails");

		boolean mohallaUpdated= false;
		int j = 0;
		try {
				//dbUtil = new DBUtility();
				//dbUtil.createPreparedStatement(CommonSQL.MOHALLA_DETAILS_UPDATE);
			
			connTest = DBUtilityTest.openConnection();
			
			pst = connTest.prepareStatement(CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
			connTest.setAutoCommit(false);
			
			for(int i = 0;i<mohallaDetails.size();i++ ){
				GuidelineDTO dto = (GuidelineDTO)mohallaDetails.get(i);

			try
			{
				//pst.setString( 1, dto.getDistrictID());
				pst.setString( 2, dto.getTehsilID());
				pst.setString( 3, dto.getWardID());
				pst.setString( 4, dto.getMohallaID());
				pst.setString( 5, dto.getAreaTypeID());
				pst.setString( 6, dto.getUnitMeasureID());
				pst.setString( 7, ""+dto.getProposedValue());
				pst.setString( 8, dto.getDurationFrom());
				pst.setString( 9, dto.getDurationTo());
				pst.setString( 10, dto.getStatus());
				pst.setString( 11, dto.getCreatedBy());
				pst.setString( 12, dto.getCreatedDate());
				pst.setString( 13, dto.getUpdatedBy());
				pst.setString( 14, dto.getUpdatedDate());
				pst.setString( 15, dto.getBasePeriodFrom());
				pst.setString( 16, dto.getBasePeriodTo());
				pst.setString( 17, dto.getFinancialYear());
				pst.setString( 18, dto.getAverageValue());
				pst.setString( 19, ""+dto.getIncrementValue());
				pst.setString( 20, "G");

				pst.setString( 21, dto.getPropertyID());
				pst.setString( 22, dto.getL1_ID());
				pst.setString( 23, dto.getL2_ID());
				pst.setString( 24,dto.getGuideLineID());

				//logger.debug(moharray[i]);
				//logger.debug("moharray: -      "+moharray[0]+" : "+moharray[1]+" : "+moharray[2]+" : "+moharray[3]+" : "+moharray[4]+" :	"+moharray[5]+" : "+moharray[6]+" : "+moharray[7]+" : "+moharray[8]+" : "+moharray[9]+" : "+moharray[10]+" : "+moharray[11]+" : "+moharray[12]+" : "+moharray[13]+" : "+moharray[14]+" : "+moharray[15]+" : "+moharray[16]+" : "+moharray[17]+" : "+moharray[18]+" : "+moharray[19]+" : "+moharray[20]+" : "+moharray[21]+" : "+moharray[22]+" : "+moharray[23]);
				//logger.debug("Query:-     "+CommonSQL.MOHALLA_DETAILS_UPDATE);
				
				
				//mohallaUpdated = dbUtil.executeUpdate(moharray);
				try {
	                j = pst.executeUpdate();
	            } catch (StringIndexOutOfBoundsException e) {
	                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
	            }
	            logger.debug("Wipro : after execute update");
	            pst.clearParameters();
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeUpdate(String[]): " +
	                               sqle.getMessage());
	           
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	            logger.debug("DAO - executeUpdate(String[]): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO - executeUpdate(String[]): " +
	                               iae.getMessage());
	            throw iae;
	        } catch (StringIndexOutOfBoundsException sioobe) {
	            logger.debug("DAO - executeUpdate(String[]): " +
	                               sioobe.getMessage());
	            throw sioobe;
	        }
	       
		}
		
		 if (j == 0)
			 mohallaUpdated  = false;
		 	mohallaUpdated  = true;
				
				if(mohallaUpdated){
					connTest.commit();
				}
			}
		 catch (Exception x) {
			 try
			 {
				 connTest.rollback();
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			logger.debug(""+x.getMessage());
		}finally {
			try {
					pst.close();
					DBUtilityTest.closeConnection();
					
			}catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		return mohallaUpdated;
	}

	
	public ArrayList checkTempTableData(){

		ArrayList dbValue = null;
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createStatement();
			connTest = DBUtilityTest.openConnection();
			st = connTest.createStatement();
			//dbValue = dbUtil.executeQuery("select * from igrs_guideline_data_temp");
			
			try {
	            rst = st.executeQuery("select * from igrs_guideline_data_temp");
	            ResultSetMetaData rsmd = rst.getMetaData();
	            int col_count = rsmd.getColumnCount();
	            while (rst.next()) {
	                ArrayList row_list = new ArrayList();
	                for (int i = 1; i <= col_count; i++) {
	                    String temp = rst.getString(i);
	                    if (rst.wasNull()) {
	                        temp = "";
	                    }
	                    row_list.add(temp);
	                }
	                dbValue.add(row_list);
	            }
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeQuery(String): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	           logger.debug("DAO - executeQuery(String): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO - executeQuery(String): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        dbValue.trimToSize();
	        

			if(dbValue != null){
				logger.debug("dbValue:-	"+dbValue);
			}
			else{
				logger.debug("dbValue:-	"+dbValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rst.close();
				st.close();
				DBUtilityTest.closeConnection();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return dbValue;
	}
	*/
	
	/*
	 * ADDED BY SIMRAN TO GET FINANCIAL YEAR DROP DOWN IN GUIDELINE PREPARATION AND START/END DATES
	 */
	/**
	 * return ArrayList that holds List of avialable financial Years in master table
	 */
	public ArrayList getFinancialYearList(){
		
		GuidelineDTO financialYear ;
		ArrayList financialYearList = new ArrayList();
		
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		try {
			
			dbUtility = new DBUtility();
			//st =
				// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			try {
				dbUtility.createStatement();
				String sql =CommonSQL.FINANCIAL;
				list2=dbUtility.executeQuery(sql);
				
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeQuery(String): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	           logger.debug("DAO - executeQuery(String): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO - executeQuery(String): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        list2.trimToSize();
	       
			
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				
				financialYear = new GuidelineDTO();
				
				//financialYear.setFinancialYearID((String)list1.get(0));
				financialYear.setFinancialYear((String)list1.get(0));
				//financialYear.setHdnFinancialYear((String)list1.get(0)+"~"+(String)list1.get(1));
				financialYearList.add(financialYear);
			}
		}
		catch(Exception e)
		{
			logger.debug(e);
			e.printStackTrace();
		}
		finally {
			try {
				
				dbUtility.closeConnection();
			} catch (Exception ex) {

				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
		
		return financialYearList;
	}
	
	
	/**
	 * 
	 * @param fyear
	 * @return ArrayList with starting and ending date for current financial Year
	 */
	public ArrayList getStartEndDate(String fyear)
	{
		ArrayList start_end= new ArrayList();
		ArrayList list2 = new ArrayList();
		logger.debug("<-----In DAO----->");
		
		
		try {
			dbUtility = new DBUtility();
			
			
			try
			{	String sql= CommonSQL.START_END;
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[1];
				sd[0] = fyear;
				list2= dbUtility.executeQuery(sd);
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
			
			
			for(int i=0; i<list2.size();i++)
			{
				start_end=(ArrayList)list2.get(i);
				
			}
			
		}
		catch(Exception e)
		{
			logger.debug(e);
			e.printStackTrace();
		}
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception ex) {

				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return start_end;
	}
	
	public ArrayList getVersionList(String finan, String distt)
	{
		ArrayList versionList = new ArrayList();
		return versionList;
	}

	
	/**
	 * 
	 * @param finanDistt
	 * @param distId
	 * @return long guidelineId
	 */
	public long getGuidelineID( String finanDistt, String distId )
	{
		long guideID = 0;
		try {
			
			dbUtility = new DBUtility();
			
			try
			{	
				String sql = CommonSQL.GUIDELINE_ID;
					logger.debug("finanDistt");
					dbUtility.createPreparedStatement(sql);
					String sd[] = new String[2];
					sd[0] = distId;
					sd[1] = finanDistt.concat("%");
					String id = dbUtility.executeQry(sd);
					if(id.equalsIgnoreCase("") || id == null)
		            	{
		            		guideID = 0;
		            	}
		            	else
		            	{
		            		guideID=Long.parseLong(id);
		            	}
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
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
		logger.debug("<----GUIDE"+guideID);
		return guideID;
	}
	
	/**
	 * This function is used to insert all the combinations of tehsils/wards/mohallas in status table to keep
	 * track of data entry by maker
	 * @param guidelineID
	 * @param distId
	 * @return boolean
	 */
	public boolean insertAllCombinations(String guidelineID, String distId)
	{int j = 0;
		boolean inserted = false;
		boolean flag = false;
		try {
			dbUtility = new DBUtility();
			
			String sql = CommonSQL.COMBINATION_INSERTION;
		
			try
			{
				dbUtility.setAutoCommit(false);
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[2];
				sd[0] = guidelineID;
				sd[1] = distId;
				flag = dbUtility.executeUpdate(sd);

				logger.debug("guideline + district"+guidelineID+"**"+distId);
			}
		         catch (SQLException sqle) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sqle.getMessage());
		           
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        } catch (StringIndexOutOfBoundsException sioobe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sioobe.getMessage());
		            throw sioobe;
		        }
		        if (flag=false)
		        {
		           inserted = false;
		        }
		        inserted = true;
		        if(inserted) {
		        	dbUtility.commit();
				}
			} catch (Exception x) {
				try
				{
			
					dbUtility.rollback();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					logger.debug("ERROR:-   "+x.getMessage());
					x.printStackTrace();
			}finally {
			try {
					
			}
			catch(Exception xe)
			{
				logger.debug("Close PreparedStatement Error:-"+xe);
				xe.printStackTrace();
					
			}
			try
			{
				dbUtility.closeConnection();
			}
			catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		logger.debug("GuideLineDAO:: guideline_status_table INSERTED:-  "+inserted);
		return inserted;
	}
	
	/**
	 * 
	 * @param distId
	 * @param guideID
	 * @return ArrayList that holds list of tehsils for which maker has not entered data in that guideline
	 */
	
	public ArrayList getTehsilListMaker(String distId, long guideID, GuidelineDTO ggdto)
	{
		GuidelineDTO  tehsil ;
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		try {
			
			dbUtility = new DBUtility();
			
			String sql = CommonSQL.TEHSIL_MAKER;
			
			try
			{	dbUtility.createPreparedStatement(sql);
			String sd[] = new String[2];
			sd[0] = distId;
			sd[1] = String.valueOf(guideID);
			list2 = dbUtility.executeQuery(sd);
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				 tehsil = new GuidelineDTO();
				tehsil.setTehsilID((String)list1.get(0));
				logger.debug("********"+list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					tehsil.setTehsil((String)list1.get(1));
					tehsil.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(1));
				}
				else
				{
					tehsil.setTehsil((String)list1.get(2));
					tehsil.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(2));
				}
				
				tehsilList.add(tehsil);
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
		return tehsilList;
	}
	
	/**
	 * 
	 * @param tehID
	 * @param guideID
	 * @return  ArrayList that holds list of wards for which maker has not entered data in that guideline
	 */
	public ArrayList getWardListMaker(String tehID, long guideID, GuidelineDTO ggdto)
	{
		GuidelineDTO  ward ;
		ArrayList wardList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		try {
			
			dbUtility = new DBUtility();
			
			String sql = CommonSQL.WARD_MAKER;
			
			try
			{	
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[2];
				sd[0] = tehID;
				sd[1] = String.valueOf(guideID);
				list2 = dbUtility.executeQuery(sd);
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				ward = new GuidelineDTO();
				ward.setWardID((String)list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					ward.setWard((String)list1.get(1));
					ward.setHdnWard((String)list1.get(0)+"~"+(String)list1.get(1));
				}
				else
				{
					ward.setWard((String)list1.get(2));
					ward.setHdnWard((String)list1.get(0)+"~"+(String)list1.get(2));
				}
				
				wardList.add(ward);
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
		return wardList;
	}
	
	
	/**
	 * 
	 * @param tehsilid
	 * @param guideID
	 * @return  ArrayList that holds list of Patwari halkas for which maker has not entered data in that guideline
	 */
	public ArrayList getPatwariListMaker(String tehsilid, long guideID, GuidelineDTO ggdto){

		GuidelineDTO  patwari;
		ArrayList patwariList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang  = ggdto.getLanguage();
		try {
			dbUtility = new DBUtility();
			
			String sql = CommonSQL.PATWARI_MAKER;
			
			try
			{	
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[2];
				sd[0] = tehsilid;
				sd[1] = String.valueOf(guideID);
				list2 = dbUtility.executeQuery(sd);
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				patwari = new GuidelineDTO();
				patwari.setPatwariID((String)list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					patwari.setPatwari((String)list1.get(1));
					patwari.setHdnPatwari((String)list1.get(0)+"~"+(String)list1.get(1));

				}
				else
				{
					patwari.setPatwari((String)list1.get(2));
					patwari.setHdnPatwari((String)list1.get(0)+"~"+(String)list1.get(2));

				}
				
				patwariList.add(patwari);
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
		return patwariList;
	}
	
	/**
	 * 
	 * @param wardID
	 * @param guideID
	 * @return  ArrayList that holds list of mohallas/villages for which maker has not entered data in that guideline
	 */
	
	public ArrayList getMohListMaker(String wardID, long guideID, GuidelineDTO ggdto)
	{
		GuidelineDTO  mohalla ;
		ArrayList mohList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		try {
			dbUtility = new DBUtility();
				String sql = CommonSQL.MOHALLA_MAKER;
					
					try
					{	
						dbUtility.createPreparedStatement(sql);
						String sd[] = new String[2];
						sd[0] = wardID;
						sd[1] = String.valueOf(guideID);
						list2 = dbUtility.executeQuery(sd);
							logger.debug(wardID);
				        } catch (SQLException sqle) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               sqle.getMessage());
				            throw sqle;
				        } catch (NumberFormatException nfe) {
				            logger.debug("DAO- executeQuery(String[]): " +
				                               nfe.getMessage());
				            throw nfe;
				        } catch (IllegalArgumentException iae) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               iae.getMessage());
				            throw iae;
				        }
				        list2.trimToSize();

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohalla = new GuidelineDTO();
				if(lang.equalsIgnoreCase("en"))
				{
					mohalla.setMohalla((String)list1.get(1));
					mohalla.setHdnMohalla((String)list1.get(0)+"="+(String)list1.get(1));
				}
				else
				{
					mohalla.setMohalla((String)list1.get(2));
					mohalla.setHdnMohalla((String)list1.get(0)+"="+(String)list1.get(2));
				}
				
				mohList.add(mohalla);
				logger.debug(""+mohList.size());
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
		return mohList;
	}
	
	/**
	 * This function is used to insert data in guideline temp tables
	 * @param mohallaDetails
	 * @param gDTO
	 * @param dFrom
	 * @param dTo
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertPendingGuidelineValues(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, 
			String dTo, String roleId, String funId, String userId,String flagChk) throws Exception
	{
		logger.debug("DAO:-  insertPendingGuidelineValues");
		boolean mohallaInserted = false;
		boolean child1Inserted = false;
		int j = 0;
		boolean updateStatus = false;
		boolean flag1 = false;
		IGRSCommon igrsCommon = null;
		ArrayList list2= new ArrayList();
		dbUtility = new DBUtility();
		String sql1 ="";
		try
		{
			igrsCommon = new IGRSCommon();
			
				String sql =CommonSQL.CHECK;
				
				try
				{	dbUtility.createPreparedStatement(sql);
				String sd[] = new String[1];
				sd[0] = gDTO.getGuideLineID();
				list2 = dbUtility.executeQuery(sd);
				
				
		               
			        } catch (SQLException sqle) {
			            logger.debug("DAO - executeQuery(String[]): " +
			                               sqle.getMessage());
			            throw sqle;
			        } catch (NumberFormatException nfe) {
			            logger.debug("DAO- executeQuery(String[]): " +
			                               nfe.getMessage());
			            throw nfe;
			        } catch (IllegalArgumentException iae) {
			            logger.debug("DAO - executeQuery(String[]): " +
			                               iae.getMessage());
			            throw iae;
			        }
			        list2.trimToSize();
			        
			        logger.debug("SIZE OF LIST2"+list2.size());
			        
			        if(list2.size()>0)
			        {
			        	mohallaInserted = true;
			        }
			        
			        else
			        {
			        	boolean flag = false;
			        	if(flagChk.equals("D"))
			        	{
			        		flag = true;
			        	}
			        	else
			        	{
			        		flag = insertAllCombinations(gDTO.getGuideLineID(),gDTO.getDistrictID());
			        	}
			        	
			        	if(flag)
			        	{
			        	if(formDTO.getGotTypeChk()==null || formDTO.getGotTypeChk().equalsIgnoreCase(""))
			        	{
			        		 sql1 = CommonSQL.INSERT_TEMP_PARENT;
			        	}
			        	else
			        	{
			        		 sql1 = CommonSQL.INSERT_TEMP_PARENT_DERIVED_FINAL;
			        	}
							int guideStatus = CommonConstant.GUIDELINESTATUS_PENDING;
							try
								{
								dbUtility = new DBUtility();
								dbUtility.createPreparedStatement(sql1);
								String sd[] = new String[9];
								sd[0] =gDTO.getGuideLineID();
								sd[1] = gDTO.getFinancialYear();
								sd[2] =gDTO.getDistrictID();
								sd[3] =dFrom ;
								sd[4] =dTo;
								sd[5] =gDTO.getGuideLineID();
								sd[6] = userId;
								sd[7] ="1";
								sd[8] ="0";
								flag1 = dbUtility.executeUpdate(sd);   
					       
						        } catch (SQLException sqle) {
						            logger.debug("DAO - executeUpdate(String[]): " +
						                               sqle.getMessage());
						           
						            throw sqle;
						        } catch (NumberFormatException nfe) {
						            logger.debug("DAO - executeUpdate(String[]): " +
						                               nfe.getMessage());
						            throw nfe;
						        } catch (IllegalArgumentException iae) {
						            logger.debug("DAO - executeUpdate(String[]): " +
						                               iae.getMessage());
						            throw iae;
						        } catch (StringIndexOutOfBoundsException sioobe) {
						            logger.debug("DAO - executeUpdate(String[]): " +
						                               sioobe.getMessage());
						            throw sioobe;
						        }
						       if (flag1=false)
						           mohallaInserted = false;
						        mohallaInserted = true;
				        }
			        	else
			        	{
			        		mohallaInserted = false;
			        	}
				       
						
						
					}
			        if(mohallaInserted) {
						
						child1Inserted = insertPendingChild1(mohallaDetails, gDTO, userId);
						int makerStatus = 2; //added-2, 1-blank, 3-derived, 4-modified
						updateStatus = updateStatus(gDTO, makerStatus);
			        	}
			        	
					

		} catch (Exception x) {
			
			
			logger.debug("ERROR:-   "+x.getMessage());
			x.printStackTrace();
		}finally {
			
			try
			{
				dbUtility.closeConnection();
			}
			catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
		return mohallaInserted;
	}
	
	/**
	 * This function is used to store data in IGRS_GUIDELINE_CHILD1_TEMP table (tehsil -- mohalla combination)
	 * @param mohallaDetails
	 * @param gDTO
	 * @param userID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertPendingChild1(ArrayList mohallaDetails,GuidelineDTO gDTO, String userID) throws Exception
	{
		logger.debug("DAO:-  insertPendingChild1Values");
		
		boolean child2Inserted = false;
		boolean child1Inserted = false;
		int j = 0;
		int seqVal = 0;
		String seq="";
		boolean flag=false;
		IGRSCommon igrsCommon = null;
		try
		{
			igrsCommon = new IGRSCommon();
			dbUtility = new DBUtility();
			//st =
				// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			//list2 = dbUtil.executeQuery(CommonSQL.FINANCIAL);
			try {
				String sql = CommonSQL.SELECT_SEQ;
				logger.debug("query is:"+CommonSQL.SELECT_SEQ);
				dbUtility.createStatement();
				 seq = dbUtility.executeQry(sql);
				//pst = connTest.prepareStatement(CommonSQL.SELECT_SEQ);
				seqVal = Integer.parseInt(seq);
	                logger.debug("seqVal"+seqVal);
	               
	           
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeQuery(String): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	           logger.debug("DAO- executeQuery(String): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO - executeQuery(String): " +
	                               iae.getMessage());
	            throw iae;
	        }catch(Exception e)
	        {
	        	logger.debug("DAO - executeQuery(String): " +
                        e.getMessage());
	        }
	        
	        finally{
	        }
	     
	        String sql2 =CommonSQL.INSERT_TEMP_CHILD1;
	       logger.debug("query is:"+CommonSQL.INSERT_TEMP_CHILD1);
			int guideStatus = CommonConstant.GUIDELINESTATUS_PENDING;
			try
				{
				dbUtility.createPreparedStatement(sql2);
				String sd[] = new String[11];
				
				sd[0] =  seq;
				sd[1] = gDTO.getGuideLineID();
					logger.debug("...."+gDTO.getTehsilID());
					sd[2] = gDTO.getTehsilID();
					logger.debug("...."+gDTO.getAreaTypeID());
					sd[3] =  gDTO.getAreaTypeID();
					 String wardArr[] = gDTO.getWardID().split("~");
		                String colonyArr[] = gDTO.getMohallaID().split("~");
		                sd[4] =  wardArr[0];
		                sd[5] = colonyArr[0];
		                sd[6] =  String.valueOf(guideStatus);
		                sd[7] =  userID;
		                sd[8] =  gDTO.getSubAreaId();
		                sd[9] = wardArr[1];
		                sd[10] =  colonyArr[1];
	                /*String patID = gDTO.getPatwariID();
					String warID = gDTO.getWardID();
					logger.debug(".."+gDTO.getWardID());
					logger.debug(".."+gDTO.getPatwariID());
					
					if(patID != null && patID.length() > 0)
					{
						 pst.setString( 5, gDTO.getPatwariID());
					}
					else*/
	               
	                
	               
		               logger.debug("before execute  Query");
		           	logger.debug("query is:"+CommonSQL.INSERT_TEMP_CHILD1);
		           	flag= dbUtility.executeUpdate(sd);
	                	 logger.debug("After execute  Query:j");
	                	
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sqle.getMessage());
		           
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        } catch (StringIndexOutOfBoundsException sioobe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sioobe.getMessage());
		            throw sioobe;
		        }
		       if (flag = false)
		           child1Inserted = false;
		       child1Inserted = true;
				if(child1Inserted) {
					
					child2Inserted = insertPendingChild2(seqVal,mohallaDetails,gDTO, userID);
					if(child2Inserted)
					{
						child1Inserted = true;
					}
					else
					{
						child1Inserted = false;
					}
				}			

		} catch (Exception x) {
			
			logger.debug("ERROR:-   "+x.getMessage());
			x.printStackTrace();
		}finally {
			
			try
			{
				dbUtility.closeConnection();
			}
			catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		
		return child1Inserted;
		
	}
	
	
	/**
	 * This funcation is used to store guideline data in IGRS_GUIDELINE_CHILD2_TEMP
	 * @param seqVal
	 * @param mohallaDetails
	 * @param gDTO
	 * @param userID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertPendingChild2(int seqVal,ArrayList mohallaDetails,GuidelineDTO gDTO, String userID) throws Exception
	{
		//mohallaDetails = checkIndustrialRate(mohallaDetails);
	
		ArrayList finalGuidelineValuesList = deriveGuidelineDetails(mohallaDetails);
		logger.debug("inside dao child2 insertion");
		boolean child2Inserted = false;
		int j = 0;
		IGRSCommon igrsCommon = null;
		boolean subClauseUpdation = false;
		boolean flag=false;
		
		try
		{
			igrsCommon = new IGRSCommon();
			dbUtility = new DBUtility();
			//createPreparedStatement(CommonSQL.INDIVIDUAL_MOHALLA_INSERT_CREATE);
			String sql =CommonSQL.INSERT_TEMP_CHILD2;
			int guideStatus = CommonConstant.GUIDELINESTATUS_PENDING;
			
			for(int i = 0;i<finalGuidelineValuesList.size();i++ ){
				GuidelineDTO dto = (GuidelineDTO)finalGuidelineValuesList.get(i);
			try
				{
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[10];
				
				sd[0] = String.valueOf(seqVal);
				sd[1] =dto.getPropertyID();
				sd[2] =dto.getL1_ID();
				sd[3] =dto.getL2_ID();
				sd[4] = dto.getUomID();
				sd[5] =""+dto.getProposedValue();
				sd[6] =dto.getAverageValue();
				sd[7] =""+dto.getIncrementValue();
				sd[8] ="G";
				sd[9] =userID;
	                
            	flag= dbUtility.executeUpdate(sd);
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sqle.getMessage());
		           
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        } catch (StringIndexOutOfBoundsException sioobe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sioobe.getMessage());
		            throw sioobe;
		        }
		       if (flag =false)
		           child2Inserted = false;
		       child2Inserted = true;
				
			}		

		} catch (Exception x) {
			
				logger.debug("ERROR:-   "+x.getMessage());
			x.printStackTrace();
		}finally {
			try {
			
			}
			catch(Exception xe)
			{
				logger.debug("Close PreparedStatement Error:-"+xe);
				xe.printStackTrace();
					
			}
			try
			{
				dbUtility.closeConnection();
			}
			catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		return child2Inserted;
	}
	
	/**
	 * This function is used to update status in status table to keep track of data entry by maker
	 * @param gDTO
	 * @param status
	 * @return boolean
	 */
	public boolean updateStatus(GuidelineDTO gDTO, int status)
	{
		
		int j=0;
		boolean mohallaUpdated = false;
		int derivedFrom = 0;
		boolean flag=false;
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.MOHALLA_DETAILS_UPDATE);
			dbUtility = new DBUtility();
			
			
			

			
			/*String query = "select derived_from from igrs_guideline_parent_temp where guideline_id = '"+gDTO.getGuideLineID()+"'";
			st = connTest.createStatement();
			//ArrayList list = new ArrayList();
	        try {
	            rst = st.executeQuery(query);
	            ResultSetMetaData rsmd = rst.getMetaData();
	            int col_count = rsmd.getColumnCount();
	            while (rst.next()) {
	                derivedFrom = rst.getInt(1);
	            	}
	        } catch (SQLException sqle) {
	            logger.debug("DBUtility - executeQuery(String): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	           logger.debug("DBUtility - executeQuery(String): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DBUtility - executeQuery(String): " +
	                               iae.getMessage());
	            throw iae;
	        }
		connTest = DBUtilityTest.openConnection();
		//logger.debug(gDTO.getDerivedFrom());*/
		String sql = CommonSQL.UPDATE_GUIDELINE_STATUS;
		
		
		
		try
		{
			dbUtility.createPreparedStatement(sql);
			
			String colArr[] = gDTO.getMohallaID().split("~"); 
			String sd[] = new String[4];
			sd[0] =String.valueOf(status);
			sd[1] =colArr[0];
			sd[2] = gDTO.getGuideLineID();
			sd[3] =colArr[1];
			flag= dbUtility.executeUpdate(sd);
			//pst.setString( 1, dto.getDistrictID());
			//if(derivedFrom == 1)
			//{
				
			//}
			//if(derivedFrom == 2 || derivedFrom == 3)
			//{
				//pst.setInt(1,4);
			//}
			
			
			
        } catch (SQLException sqle) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               sqle.getMessage());
           
            throw sqle;
        } catch (NumberFormatException nfe) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               iae.getMessage());
            throw iae;
        } catch (StringIndexOutOfBoundsException sioobe) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               sioobe.getMessage());
            throw sioobe;
        }
       if (flag = false)
		 mohallaUpdated  = false;
	 	mohallaUpdated  = true;
		}
	 catch (Exception x) {
		 try
		 {
			 dbUtility.rollback();
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		logger.debug(""+x.getMessage());
	}finally {
		try {
			dbUtility.closeConnection();
				
		}catch (Exception xe ) {
			logger.debug("Close Connection Error:-"+xe);
			xe.printStackTrace();
		}
	}
	return mohallaUpdated;
	}
	
	
	/**
	 * 
	 * @param finan
	 * @param disttID
	 * @return ArrayList that holds all the available versions of guideline for that district
	 * along with status
	 */
	public ArrayList showStatusMaker(String finan, String disttID, String lang )
	{
		GuidelineDTO  version ;
		ArrayList versionList = new ArrayList();
		ArrayList listVer1 = new ArrayList();
		ArrayList listVer2 = new ArrayList();
		String dFrom =null; 
		String dTo = null;
		try {
			logger.debug("<....."+disttID);
			logger.debug("<...."+finan);
			dbUtility = new DBUtility();
					String sql = CommonSQL.SHOW_VERSION_MAKER;
					try
					{	
						dbUtility.createPreparedStatement(sql);
						String sd[] = new String[2];
						sd[0] = finan;
						sd[1] = disttID;
						listVer2 = dbUtility.executeQuery(sd);
					 logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
				            
				        } catch (SQLException sqle) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               sqle.getMessage());
				            throw sqle;
				        } catch (NumberFormatException nfe) {
				            logger.debug("DAO- executeQuery(String[]): " +
				                               nfe.getMessage());
				            throw nfe;
				        } catch (IllegalArgumentException iae) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               iae.getMessage());
				            throw iae;
				        }
				        listVer2.trimToSize();

			for (int i = 0; i < listVer2.size(); i++) {
				listVer1 = (ArrayList)listVer2.get(i);
				version = new GuidelineDTO();
				version.setGuideLineID((String)listVer1.get(0));
				String fromDate = listVer1.get(1).toString().substring(0,10);
				String toDate = listVer1.get(2).toString().substring(0,10);
				
				String []dFromArr = fromDate.toString().split("-");
				if(dFromArr.length ==3)
				{
					 dFrom = dFromArr[2]+"/"+dFromArr[1]+"/"+dFromArr[0];
				}
				
				String [] dToArr = toDate.toString().split("-");
				if(dToArr.length ==3)
				{
					 dTo = dToArr[2]+"/"+dToArr[1]+"/"+dToArr[0];
				}
				version.setFromDepositeDate(dFrom);
			
				version.setToDepositeDate(dTo);
				if(lang.equalsIgnoreCase("en"))
				{
					version.setStatus((String)listVer1.get(3));
					version.setStatusChecker((String)listVer1.get(4));
				}
				else
				{
					String makerStatus = (String)listVer1.get(3);
					String checkerStatus = (String)listVer1.get(4);
					
					if(makerStatus.equalsIgnoreCase("Pending"))
					{
						version.setStatus("लंबित");
					}
					else if(makerStatus.equalsIgnoreCase("Complete"))
					{
						version.setStatus("संपूर्ण");
					}
					
					
					if(checkerStatus.equalsIgnoreCase("Pending"))
					{
						version.setStatusChecker("लंबित");
					}
					else if(checkerStatus.equalsIgnoreCase("Draft"))
					{
						version.setStatusChecker("प्रारूप्");
					}
					else if(checkerStatus.equalsIgnoreCase("final"))
					{
						version.setStatusChecker("फाइनल");
					}
					
					//version.setStatus((String)listVer1.get(3));
					//version.setStatusChecker((String)listVer1.get(4));
				}
				
				//version.setDerivedFrom((Integer.parseInt((String)listVer1.get(5))));
				version.setHdnGuideDuration((String)listVer1.get(0)+"~"+ dFrom+"~"+dTo);
				
				versionList.add(version);
				logger.debug("!!!!!!"+listVer1.get(0));	
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
		return versionList;
	}
	
	
	/**
	 * This function is used to copy data from final tables when user derives 
	 * guideline from Previous Year's guideline
	 * @param gDTO
	 * @param userID
	 * @return boolean
	 */
	public boolean insertParentData(GuidelineDTO gDTO, String userID)
	{
		logger.debug("DAO:-  insertPendingGuidelineValues");
		boolean mohallaInserted = false;
		boolean dataCopied= false;
		int j = 0;
		boolean updateStatus = false;
		IGRSCommon igrsCommon = null;
		ArrayList list2= new ArrayList();
		long guideID_old = 0;
		boolean flag=false;
		try{	
			
			dbUtility = new DBUtility();
				igrsCommon = new IGRSCommon();
		
				
				String query = CommonSQL.GET_GUIDELINE_ID;
				
				
				//ArrayList list = new ArrayList();
		        try {
		        	dbUtility.createPreparedStatement(query);
		        	String sd[] = new String[1];
					sd[0] = gDTO.getDistrictID();
		        	String id = dbUtility.executeQry(sd);
		        	guideID_old = Long.parseLong(id);
		        } catch (SQLException sqle) {
		            logger.debug("DBUtility - executeQuery(String): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		           logger.debug("DBUtility - executeQuery(String): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DBUtility - executeQuery(String): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        
				logger.debug("OLD GUIDELINE ID in DAO"+guideID_old);
				if(guideID_old == 0)
				{
					mohallaInserted= false;
				}
				else
				{
					String sql= CommonSQL.INSERT_TEMP_PARENT_DRIVE;
					int guideStatus = CommonConstant.GUIDELINESTATUS_PENDING;
							try
								{
								dbUtility.createPreparedStatement(sql);
								String sd[] = new String[9];
								
								
								
								sd[0] =String.valueOf(gDTO.getGuideID());
								sd[1] =gDTO.getFinancialYear();
								sd[2] =gDTO.getDistrictID();
								sd[3] =gDTO.getFromDepositeDate();
								sd[4] =gDTO.getToDepositeDate();
								sd[5] =String.valueOf(gDTO.getGuideID());
								sd[6] = userID;
								sd[7] ="3";
								sd[8] =String.valueOf(guideID_old);
					                flag = dbUtility.executeUpdate(sd);
					                
					                
						        } catch (SQLException sqle) {
						            logger.debug("DAO - executeUpdate(String[]): " +
						                               sqle.getMessage());
						           
						            throw sqle;
						        } catch (NumberFormatException nfe) {
						            logger.debug("DAO - executeUpdate(String[]): " +
						                               nfe.getMessage());
						            throw nfe;
						        } catch (IllegalArgumentException iae) {
						            logger.debug("DAO - executeUpdate(String[]): " +
						                               iae.getMessage());
						            throw iae;
						        } catch (StringIndexOutOfBoundsException sioobe) {
						            logger.debug("DAO - executeUpdate(String[]): " +
						                               sioobe.getMessage());
						            throw sioobe;
						        }
						       if (flag == false)
						           mohallaInserted = false;
						        mohallaInserted = true;
				      if(mohallaInserted)
				      {
				    	  dataCopied = childDetailsProcedure(gDTO, guideID_old,userID);
				      }
				}
				
			       
			} catch (Exception x) {
			
			
			logger.debug("ERROR:-   "+x.getMessage());
			x.printStackTrace();
		}finally {
			
			try
			{	
				dbUtility.closeConnection();
			}
			catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
		return dataCopied;	
	}
	
	
	/**
	 * This function is used to copy data from final tables when user derives 
	 * guideline from Previous Year's guideline
	 * @param gDTO
	 * @param guideID_old
	 * @param userID
	 * @return boolean
	 */
	public boolean childDetailsProcedure(GuidelineDTO gDTO,  long guideID_old, String userID)
	{
		boolean dataCopied = false;
		try{	
			
			dbUtility = new DBUtility();
			
			
			int guideStatus = CommonConstant.GUIDELINESTATUS_PENDING;
					try
						{
						clstmt = dbUtility.createCallableStatement(CommonSQL.COPY_MASTER_DATA);
						
							clstmt.setLong(1, gDTO.getGuideID());
							clstmt.setLong(2, guideID_old);
							clstmt.setString(3, userID);
							clstmt.setString(4, gDTO.getDistrictID());
							clstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			                
			                try {
				                	if(!clstmt.execute())
				                	{
				                		dataCopied = true;
				                	}
				            } catch (StringIndexOutOfBoundsException e) {
				                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
				            }
				            logger.debug("Wipro : after execute update");
				           
				        } catch (SQLException sqle) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               sqle.getMessage());
				           
				            throw sqle;
				        } catch (NumberFormatException nfe) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               nfe.getMessage());
				            throw nfe;
				        } catch (IllegalArgumentException iae) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               iae.getMessage());
				            throw iae;
				        } catch (StringIndexOutOfBoundsException sioobe) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               sioobe.getMessage());
				            throw sioobe;
				        }
		       
				} catch (Exception x) {
					logger.debug("ERROR:-   "+x.getMessage());
					x.printStackTrace();
				}finally {
					try
					{
						dbUtility.closeConnection();
					}
					catch (Exception xe ) {
					logger.debug("Close Connection Error:-"+xe);
					xe.printStackTrace();
					}
				}
				return dataCopied;
	
	}
	
	/*public boolean draftData(String distId)
	{
		logger.debug("DAO:-  copy draft data");
		boolean draftData = false;
		boolean dataCopied= false;
		int j = 0;
		boolean updateStatus = false;
		IGRSCommon igrsCommon = null;
		ArrayList list2= new ArrayList();
		long guideID_old = 0;
		try{	
			
				connTest = DBUtilityTest.openConnection();
		
		
				igrsCommon = new IGRSCommon();
				DBUtilityTest db= new DBUtilityTest();
				String query = "select a.guideline_id from igrs_guideline_parent_temp a, igrs_guideline_child1_temp b where a.guideline_id = b.guideline_id and b.status_flag = 2 and a.district_id = '"+distId+"' and duration_from =( select max(duration_from) from IGRS_GUIDELINE_PARENT_TEMP a , IGRS_GUIDELINE_CHILD1_TEMP b where  a.guideline_id = b.guideline_id and a.district_id ='"+distId+"' and b.STATUS_FLAG = 2)";
				//String query = "select guideline_id from IGRS_GUIDELINE_PARENT_TEMP where duration_from = (select max(duration_from) from IGRS_GUIDELINE_PARENT_TEMP a , IGRS_GUIDELINE_CHILD1_TEMP b where a.district_id = '"+gDTO.getDistrictID()+"' and b.STATUS_FLAG = 2)";
				st = connTest.createStatement();
				//ArrayList list = new ArrayList();
		        try {
		            rst = st.executeQuery(query);
		            ResultSetMetaData rsmd = rst.getMetaData();
		            int col_count = rsmd.getColumnCount();
		            while (rst.next()) {
		                guideID_old = rst.getLong(1);
		            	}
		        } catch (SQLException sqle) {
		            logger.debug("DBUtility - executeQuery(String): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		           logger.debug("DBUtility - executeQuery(String): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DBUtility - executeQuery(String): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        if(guideID_old !=0)
		        {
		        	draftData = true;
		        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rst.close();
				st.close();
				DBUtilityTest.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return draftData;
	}
	*/
	
	/**
	 *  This function is used to copy data from final tables when user derives 
	 * guideline from Draft guideline
	 * @param gDTO
	 * @param userID
	 * @return boolean
	 */
	public boolean copyDraftData(GuidelineDTO gDTO, String userID)
	{
		logger.debug("DAO:-  copy draft data");
		boolean mohallaInserted = false;
		boolean dataCopied= false;
		int j = 0;
		boolean updateStatus = false;
		IGRSCommon igrsCommon = null;
		ArrayList list2= new ArrayList();
		//long guideID_old = 0;
		boolean flag1=false;
		try{	
			
			dbUtility = new DBUtility();
				
		
				igrsCommon = new IGRSCommon();
				
		        logger.debug("OLD DRAFT GUIDELINE ID in DAO"+gDTO.getGuideLineID());
				
				//if(guideID_old != 0)
				//{
				String sql = CommonSQL.INSERT_TEMP_PARENT;
				int guideStatus = CommonConstant.GUIDELINESTATUS_PENDING;
						try
							{
							dbUtility.createPreparedStatement(sql);
							String sd[] = new String[9];
				
						
							sd[0] =String.valueOf(gDTO.getGuideID());
							sd[1] =gDTO.getFinancialYear();
							sd[2] =gDTO.getDistrictID();
							sd[3] =gDTO.getFromDepositeDate();
							sd[4] =gDTO.getToDepositeDate();
							sd[5] =String.valueOf(gDTO.getGuideID());
							sd[6] =userID;
							sd[7] ="2";		//Dervied_from 1-new, 2-Draft, 3- Final
							sd[8] =gDTO.getGuideLineID();  // added on 26 july for storing old guideline detials
				            	flag1 = dbUtility.executeUpdate(sd);
				                
				                
					        }catch (SQLException sqle) {
					            logger.debug("DAO - executeUpdate(String[]): " +
					                               sqle.getMessage());
					           
					            throw sqle;
					        } catch (NumberFormatException nfe) {
					            logger.debug("DAO - executeUpdate(String[]): " +
					                               nfe.getMessage());
					            throw nfe;
					        } catch (IllegalArgumentException iae) {
					            logger.debug("DAO - executeUpdate(String[]): " +
					                               iae.getMessage());
					            throw iae;
					        } catch (StringIndexOutOfBoundsException sioobe) {
					            logger.debug("DAO - executeUpdate(String[]): " +
					                               sioobe.getMessage());
					            throw sioobe;
					        }
				//}
					       if (flag1 = false)
					       {
					    	   
					           mohallaInserted = false;
					          
					       }
					       else 
					       {
					    	   
					        mohallaInserted = true;
					        boolean flag=false;
					        dbUtility = new DBUtility();
					        
					        String type="";
							
							String arr[]=new String[1];
					        //System.out.println("1");
					        arr[0]= gDTO.getLoggedOfficeId();
					        
					        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
							*/
					        dbUtility.createPreparedStatement(CommonSQL.CHECK_OFFICE_TYPE);
							 //System.out.println("heyyyyyy");
					        type = dbUtility.executeQry(arr);
							
					        if("SRO".equalsIgnoreCase(type))
					        {
					        	dbUtility.createPreparedStatement(CommonSQL.UPDATE_OLD_GUIDELINE_SR_STATUS);
								 //System.out.println("heyyyyyy");
						        flag = dbUtility.executeUpdate(new String[]{gDTO.getGuideLineID()});
						        
						        dbUtility.createPreparedStatement(CommonSQL.UPDATE_DERIVE_SR_STATUS);
								 //System.out.println("heyyyyyy");
						        flag = dbUtility.executeUpdate(new String[]{String.valueOf(gDTO.getGuideID())});
					        }
					        else if("DRO".equalsIgnoreCase(type))
					        {
					        	dbUtility.createPreparedStatement(CommonSQL.UPDATE_OLD_GUIDELINE_SR_STATUS);
								 //System.out.println("heyyyyyy");
						        flag = dbUtility.executeUpdate(new String[]{gDTO.getGuideLineID()});
						        
						        dbUtility.createPreparedStatement(CommonSQL.UPDATE_DERIVE_DR_STATUS);
								 //System.out.println("heyyyyyy");
						        flag = dbUtility.executeUpdate(new String[]{String.valueOf(gDTO.getGuideID())});
					        }
					        else
					        {
					        	dbUtility.createPreparedStatement(CommonSQL.UPDATE_OLD_GUIDELINE_SR_STATUS);
								 //System.out.println("heyyyyyy");
						        flag = dbUtility.executeUpdate(new String[]{gDTO.getGuideLineID()});
						        
						        dbUtility.createPreparedStatement(CommonSQL.UPDATE_DERIVE_SR_STATUS);
								 //System.out.println("heyyyyyy");
						        flag = dbUtility.executeUpdate(new String[]{gDTO.getGuideLineID()});
					        }
					        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
							*/
					        
					        if(flag)
					        {
					        	
					        	logger.debug("Old Guideline SR PUBLISH STATUS UPDATED");
					        }
					        else
					        {
					        	throw new Exception("update failed");
					        }
					       }
			      if(mohallaInserted)
			      {
			    	  dataCopied = draftChildDetailsProcedure(gDTO,userID);
			      }
			       
			} catch (Exception x) {
			
			
			logger.debug("ERROR:-   "+x.getMessage());
			x.printStackTrace();
		}finally {
			
			try
			{	
				dbUtility.closeConnection();
			}
			catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
		logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
		return dataCopied;
		
		
		
	}
	
	
	/**
	 *  This function is used to copy data from final tables when user derives 
	 * guideline from Draft guideline
	 * @param gDTO
	 * @param userID
	 * @return
	 */
	public boolean draftChildDetailsProcedure(GuidelineDTO gDTO, String userID)
	{
		boolean dataCopied = false;
		try{	
			
			dbUtility = new DBUtility();
			clstmt = dbUtility.createCallableStatement(CommonSQL.COPY_DRAFT_DATA);
			int guideStatus = CommonConstant.GUIDELINESTATUS_PENDING;
					try
						{
							clstmt.setLong(1, gDTO.getGuideID());
							clstmt.setString(2, gDTO.getGuideLineID());
							clstmt.setString(3, userID);
							clstmt.setString(4, gDTO.getDistrictID());
							clstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			                
			                try {
				                	if(!clstmt.execute())
				                	{
				                		dataCopied = true;
				                	}                                                                                      
				            } catch (StringIndexOutOfBoundsException e) {
				                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
				            }
				            logger.debug("Wipro : after execute update");
				           
				        } catch (SQLException sqle) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               sqle.getMessage());
				           
				            throw sqle;
				        } catch (NumberFormatException nfe) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               nfe.getMessage());
				            throw nfe;
				        } catch (IllegalArgumentException iae) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               iae.getMessage());
				            throw iae;
				        } catch (StringIndexOutOfBoundsException sioobe) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               sioobe.getMessage());
				            throw sioobe;
				        }
		       
				} catch (Exception x) {
					logger.debug("ERROR:-   "+x.getMessage());
					x.printStackTrace();
				}finally {
					try
					{
						dbUtility.closeConnection();
					}
					catch (Exception xe ) {
					logger.debug("Close Connection Error:-"+xe);
					xe.printStackTrace();
					}
				}
				return dataCopied;
	
	}
	
	/**
	 * 
	 * @param finan
	 * @param disttID
	 * @param derivedFrom
	 * @param formDTO2 
	 * @return ArrayList @return ArrayList that holds list of all versions of guideline derived from previous year 
	 * 
	 */
	public ArrayList showVersionFinalMaker(String finan, String disttID, int derivedFrom, String lang, GuidelineDTO formDTO2)
	{
		GuidelineDTO  version ;
		ArrayList versionList = new ArrayList();
		ArrayList listVer1 = new ArrayList();
		ArrayList listVer2 = new ArrayList();
		String dFrom = null;
		String dTo = null;
		try {
			String sql ="";
			String type="";
			dbUtility = new DBUtility();
			String arr[]=new String[1];
	        //System.out.println("1");
	        arr[0]= formDTO2.getLoggedOfficeId();
	        
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtility.createPreparedStatement(CommonSQL.CHECK_OFFICE_TYPE);
			 //System.out.println("heyyyyyy");
	        type = dbUtility.executeQry(arr);
	        String h = formDTO2.getGuideTypeChk();
			logger.debug("<....."+disttID);
			logger.debug("<...."+finan);
			dbUtility = new DBUtility();
					if("2".equalsIgnoreCase(h))
					{
					if("SRO".equalsIgnoreCase(type))
					{
						sql =CommonSQL.SHOW_VERSION_FINAL_MAKER;
						
					}
					else if("DRO".equalsIgnoreCase(type))
					{
						sql =CommonSQL.SHOW_VERSION_FINAL_MAKER_DR;
						
					}
					else
					{
						sql =CommonSQL.SHOW_VERSION_FINAL_MAKER;
						
					}
					}
					else
					{
						derivedFrom=3;
						sql =CommonSQL.SHOW_VERSION_MAKER_FINAL;
					}
			
					try
					{	
						dbUtility.createPreparedStatement(sql);
						String sd[] = new String[2];
						sd[0] = finan;
						sd[1] = disttID;
						
						listVer2=dbUtility.executeQuery(sd);
				            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
				            
				           
				        } catch (SQLException sqle) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               sqle.getMessage());
				            throw sqle;
				        } catch (NumberFormatException nfe) {
				            logger.debug("DAO- executeQuery(String[]): " +
				                               nfe.getMessage());
				            throw nfe;
				        } catch (IllegalArgumentException iae) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               iae.getMessage());
				            throw iae;
				        }
				        listVer2.trimToSize();
				        
				        logger.debug(listVer2.size());

			for (int i = 0; i < listVer2.size(); i++) {
				listVer1 = (ArrayList)listVer2.get(i);
				version = new GuidelineDTO();
			//	mohalla.setBasePeriodFrom((String)list1.get(0));
			//	mohalla.setBasePeriodTo((String)list1.get(1));	
			//	mohalla.setMohallaID((String)list1.get(0));
				version.setGuideLineID((String)listVer1.get(0));
			//	mohalla.setStatus((String)list1.get(4));
				String fromDate = listVer1.get(1).toString().substring(0,10);
				String toDate = listVer1.get(2).toString().substring(0,10);
			
				
				String []dFromArr = fromDate.toString().split("-");
				if(dFromArr.length ==3)
				{
					 dFrom = dFromArr[2]+"/"+dFromArr[1]+"/"+dFromArr[0];
				}
				
				String [] dToArr = toDate.toString().split("-");
				if(dToArr.length ==3)
				{
					 dTo = dToArr[2]+"/"+dToArr[1]+"/"+dToArr[0];
				}
				version.setFromDepositeDate(dFrom);
				version.setToDepositeDate(dTo);
				if(lang.equalsIgnoreCase("en"))
				{
					version.setStatus((String)listVer1.get(3));
					version.setStatusChecker((String)listVer1.get(4));
				}
				else
				{
					String makerStatus =  (String)listVer1.get(3);
					String checkerStatus = (String)listVer1.get(4);
					if(makerStatus.equalsIgnoreCase("Pending"))
					{
						version.setStatus("लंबित");
					}
					else if(makerStatus.equalsIgnoreCase("Complete"))
					{
						version.setStatus("संपूर्ण");
					}
					
					if(checkerStatus.equalsIgnoreCase("Pending"))
					{
						version.setStatusChecker("लंबित");
					}
					else if(checkerStatus.equalsIgnoreCase("Draft"))
					{
						version.setStatusChecker("प्रारूप्");
					}
					else if(checkerStatus.equalsIgnoreCase("Final"))
					{
						version.setStatusChecker("फाइनल");
					}
				}
				
				//version.setDerivedFrom(Integer.parseInt((String)(listVer1.get(3))));
				version.setHdnGuideDuration((String)listVer1.get(0)+"~"+ dFrom+"~"+dTo);
				
				versionList.add(version);
				logger.debug("!!!!!!"+listVer1.get(0));
				
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
		return versionList;
	}
	
	
	/**
	 * 
	 * @param gDTO
	 *  @return ArrayList that holds guideline data in case guideline is derived
	 */
	public ArrayList getIndividualMohallaDetailsNew(GuidelineDTO gDTO)
	{
		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = gDTO.getLanguage(); 
		ArrayList displayList = new ArrayList();
		
		try {
			
			
			dbUtility = new DBUtility();
		
			logger.debug(gDTO.getGuideLineID());
			
			
			String sql = CommonSQL.MOHALLA_DETAILS_MODIFY;
			
			try {
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[3];
				String colArr[] = gDTO.getMohallaID().split("~");
				sd[0] =gDTO.getGuideLineID();
				sd[1] =colArr[1];
				sd[2] =colArr[0];
				list2 = dbUtility.executeQuery(sd);
	           
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeQuery(String[]): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	        	logger.debug("DAO - executeQuery(String[]): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	        	logger.debug("DAO - executeQuery(String[]): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        list2.trimToSize();
	        
	        	for (int i = 0; i < list2.size(); i++) {
				displayList = (ArrayList)list2.get(i);
				
				
				
				mohallaDetails = new GuidelineDTO();
				
			
				mohallaDetails.setGuideLineValue((String)displayList.get(3));
				
				mohallaDetails.setMohallaID((String)displayList.get(4));
				logger.debug("mohalla id:-   "+(String)displayList.get(4));
				logger.debug("mohalla:-   "+(String)displayList.get(5));
				
				
				mohallaDetails.setAreaTypeID((String)displayList.get(6));
				mohallaDetails.setPropertyID((String)displayList.get(7));
				mohallaDetails.setL1_ID((String)displayList.get(8));
				mohallaDetails.setL2_ID((String)displayList.get(9));
				
				mohallaDetails.setUomID((String)displayList.get(11));
				if(lang.equalsIgnoreCase("en"))
				{
					mohallaDetails.setPropertyTypeName((String)displayList.get(0));
					mohallaDetails.setL1Name((String)displayList.get(1));
					mohallaDetails.setL2Name((String)displayList.get(2));
					mohallaDetails.setMohalla((String)displayList.get(5));
					mohallaDetails.setUomName((String)displayList.get(10));
				}
				else
				{
					mohallaDetails.setPropertyTypeName((String)displayList.get(12));
					mohallaDetails.setL1Name((String)displayList.get(13));
					mohallaDetails.setL2Name((String)displayList.get(14));
					mohallaDetails.setMohalla((String)displayList.get(15));
					mohallaDetails.setUomName((String)displayList.get(16));
				}
				mohallaDetailsList.add(mohallaDetails);
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
		return mohallaDetailsList;
	}
	
	/**
	 * This function is used to update guideline values in case user
	 * has modify any data in derived guideline
	 * @param mohallaDetails
	 * @param gDTO
	 * @param dFrom
	 * @param dTo
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @param subDetails
	 * @return boolean
	 */
	public boolean updatePendingGuidelineValues(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, 
			String dTo,String roleId, String funId, String userId){  		
		//mohallaDetails = checkIndustrialRate(mohallaDetails);
		ArrayList mohallaDetailsNew = deriveGuidelineDetails(mohallaDetails);
		logger.debug("DAO:-  updateTempMohallaDetails");
		boolean mohallaInserted = false;
		boolean updateStatus = false;
		boolean updateValidate = false;
		String wardId = null;
		boolean  j = false;
		IGRSCommon igrsCommon = null;
		boolean subClauseUpdation = false;
		try {
				
				igrsCommon = new IGRSCommon();
				dbUtility = new DBUtility();
			//clstmt = connTest.prepareCall("Call {igrs_update_temp_guideline(?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?,?,?,?,?,?,?,?,to_date(?,'dd/mm/yy'),?,?,?,?)}");
			clstmt = dbUtility.createCallableStatement(CommonSQL.UPDATE_DRAFT_PROCEDURE);
			
			dbUtility.setAutoCommit(true);
			
			int guideStatus = CommonConstant.GUIDELINESTATUS_PENDING;
			
			String guidelineValue= null;
			
			for(int i = 0;i<mohallaDetailsNew.size();i++ ){
				GuidelineDTO dto = (GuidelineDTO)mohallaDetailsNew.get(i);
			
			try
			{
				guidelineValue =String.valueOf(dto.getProposedValue());
				
				
				/*if(gDTO.getPatwariID() == null)
				{
					wardId = gDTO.getWardID();
				}
				else 
				{
					wardId = gDTO.getPatwariID();
				}*/
				String wardArr[]  = gDTO.getWardID().split("~");
				String mohallaArr[] = gDTO.getMohallaID().split("~");
				
				clstmt.setString( 1, gDTO.getFinancialYear());
				clstmt.setString(2, gDTO.getDistrictID());
				clstmt.setString( 3, dFrom);
				clstmt.setString( 4, dTo);
				clstmt.setString( 5, gDTO.getTehsilID());
				clstmt.setString( 6, wardArr[0]);
				clstmt.setString( 7, mohallaArr[0]);
				clstmt.setString( 8, mohallaArr[1]);
				clstmt.setString( 9, wardArr[1]);
				clstmt.setString( 10, gDTO.getAreaTypeID());
				clstmt.setString( 11, gDTO.getSubAreaId());
				clstmt.setInt( 12, guideStatus);
				clstmt.setString( 13, dto.getPropertyID());
				clstmt.setString( 14, dto.getL1_ID());
				clstmt.setString( 15, dto.getL2_ID());
				clstmt.setString( 16, dto.getUomID());
				clstmt.setString( 17, ""+guidelineValue);
				logger.debug("GUIDELINE_VALUE"+guidelineValue);
				clstmt.setString( 18, dto.getAverageValue());
				clstmt.setString( 19, ""+dto.getIncrementValue());
				clstmt.setString( 20, "G");
				
				clstmt.setString( 21, dto.getCreatedBy());
				clstmt.setString( 22, dto.getCreatedDate());
				clstmt.setString( 23, userId);
				clstmt.setString( 24, gDTO.getGuideLineID());
				clstmt.registerOutParameter(25, OracleTypes.VARCHAR);
				clstmt.registerOutParameter(26, OracleTypes.VARCHAR);
				try {
	                 if(!clstmt.execute())
	                 {
	                	 mohallaInserted = true;
	                 }
	                
	            } catch (StringIndexOutOfBoundsException e) {
	            	logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
	            }
	            logger.debug("Wipro : after execute update");
	            clstmt.clearParameters();
	        } catch (SQLException sqle) {
	           logger.debug("DAO- executeUpdate(String[]): " +
	                               sqle.getMessage());
	           
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	        	logger.debug("DAO - executeUpdate(String[]): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	        	logger.debug("DAO - executeUpdate(String[]): " +
	                               iae.getMessage());
	            throw iae;
	        } catch (StringIndexOutOfBoundsException sioobe) {
	        	logger.debug("DAO - executeUpdate(String[]): " +
	                               sioobe.getMessage());
	            throw sioobe;
	        }	
		}
		} catch (Exception x) {
			
			logger.debug("ERROR:-   "+x.getMessage());
		}finally {
			try {
					
				dbUtility.closeConnection();
				
			}catch (Exception xe ) {
				logger.debug("Error In Closing"+xe);
			}
		}
		logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
		if(mohallaInserted)
		{
			// subClauseUpdation = subClauseInsertion(subDetails,gDTO);
			 //if(subClauseUpdation)
			// {
				 int makerStatus = 4;  //modified
					updateValidate = updateValidateStatus(gDTO);
					if(updateValidate)
					{
						updateStatus = updateStatus( gDTO, makerStatus);
					}
			// }
			
		}
		return updateStatus;
	}
	
	/**
	 * This method is used to update validate status after maker has changed something in derived guideline
	 * so that same can be shown to checker that it needs to be validated
	 * @param gDTO
	 * @return boolean
	 */
	public boolean updateValidateStatus(GuidelineDTO gDTO)
	{
		int j = 0;
		boolean updateStatus = false;
		boolean flag=false;
		try
		{
			
			dbUtility = new DBUtility();
		try
		{
			String sql=CommonSQL.UPDATE_CHILD_TEMP2;
			dbUtility.createPreparedStatement(sql);
			//String sql = "update igrs_guideline_child1_temp set validate_Status = 1 where guideline_id = '"+gDTO.getGuideLineID()+"' and tehsil_id = '"+gDTO.getTehsilID()+"' and mohalla_village_id = '"+gDTO.getMohallaID()+"'";
			try {
				String sd[] = new String[3];
				sd[0] =gDTO.getGuideLineID();
				sd[1] =gDTO.getTehsilID();
				sd[2] =gDTO.getMohallaID().split("~")[0];
				flag= dbUtility.executeUpdate(sd);
				} catch (StringIndexOutOfBoundsException e) {
                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
            }
            logger.debug("Wipro : after execute update");
            
        } catch (SQLException sqle) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               sqle.getMessage());
           
            throw sqle;
        }
         catch (NumberFormatException nfe) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               iae.getMessage());
            throw iae;
        } catch (StringIndexOutOfBoundsException sioobe) {
            logger.debug("DAO - executeUpdate(String[]): " +
                               sioobe.getMessage());
            throw sioobe;
        }
        if (flag= false)
        	updateStatus = false;
        updateStatus = true;
		
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	return updateStatus;
}
	
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that holds list of subclauses for selected mohalla 
	 * and property types in case of derived guideline 
	 */
	
	public ArrayList subclauses(GuidelineDTO gDTO)
	{
		String wardId = null;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO map_sub ;
		ArrayList mapSubClauseList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			logger.debug(gDTO.getL2_ID());
			//logger.debug(gDTO.getWardID().toString());
			String patID = gDTO.getPatwariID();
			String warID = gDTO.getWardID();
			
			if(patID != null && patID.length() > 0)
			{
				 wardId = gDTO.getPatwariID();
			}
			else
				 wardId = gDTO.getWardID();
			
			
				String sql =CommonSQL.VIEW_DERIVE;
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[7];

				sd[0] = gDTO.getGuideLineID();
				sd[1] = gDTO.getTehsilID();
				sd[2] = wardId;
				sd[3] = gDTO.getMohallaID();
				sd[4] = gDTO.getPropertyID();
				sd[5] = gDTO.getL1_ID();
				sd[6] = gDTO.getL2_ID();
			
			try
			{
				 list2 = dbUtility.executeQuery(sd);
				
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		            
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		
		        
			
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				map_sub = new GuidelineDTO();
				logger.debug("#######"+list1.get(0));
				map_sub.setSubClauseId((String)list1.get(0));
				map_sub.setSubName((String)list1.get(1));
				map_sub.setSubClauseName((String)list1.get(2));
				map_sub.setStatus((String)list1.get(3));
				
				
				mapSubClauseList.add(map_sub);
			}
			logger.debug("SIZE IN MAKER"+mapSubClauseList.size());
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
		
		return mapSubClauseList;
	}
	
	 /**
	  * 
	  * @param gDTO
	  * @return ArrayList that is newly added this year and has not been derived from previous years guideline
	  */
	public ArrayList subclausesNotDerived(GuidelineDTO gDTO) throws SQLException
	{
		String wardId = null;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO map_sub ;
		ArrayList mapSubClauseList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			logger.debug(gDTO.getL2_ID());
			//logger.debug(gDTO.getWardID().toString());
			String patID = gDTO.getPatwariID();
			String warID = gDTO.getWardID();
			
			if(patID != null && patID.length() > 0)
			{
				 wardId = gDTO.getPatwariID();
			}
			else
				 wardId = gDTO.getWardID();
			
			
				
				
				if(gDTO.getL2_ID().toString().equals("0"))
				{
					String sql =CommonSQL.SUBCLAUSE_NULL;
					dbUtility.createPreparedStatement(sql);
					String sd[] = new String[2];
					sd[0] = gDTO.getPropertyID();
					sd[1] = gDTO.getL1_ID();
					list2=dbUtility.executeQuery(sd);
				}
				else
				{
					String sql = CommonSQL.SUBCLAUSE;
					dbUtility.createPreparedStatement(sql);
					String sd[] = new String[3];
					sd[0] = gDTO.getPropertyID();
					sd[1] = gDTO.getL1_ID();
					sd[2] = gDTO.getL2_ID();
					list2=dbUtility.executeQuery(sd);
				}
			
			try
			{
				//pst.setString(4,gDTO.getDistrictID());
				//pst.setInt(5, gDTO.getTehID());
				//pst.setInt(6,gDTO.getWardId());
				//pst.setInt(7,gDTO.getMohallaId());
				
				
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		            
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();    
			
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				map_sub = new GuidelineDTO();
				logger.debug("#######"+list1.get(0));
				map_sub.setSubClauseId((String)list1.get(0));
				map_sub.setSubName((String)list1.get(1));
				map_sub.setSubClauseName((String)list1.get(2));
				map_sub.setStatus((String)list1.get(3));
				
				
				mapSubClauseList.add(map_sub);
			}
			logger.debug("DAO"+mapSubClauseList.size());
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
		
		return mapSubClauseList;
	}
	
	/**
	 * 
	 * @param subKeys
	 * @param gDTO
	 * @return HashMap that holds subclauses which are mapped but not clicked by user
	 */
	public HashMap subClauseNotClicked(ArrayList subKeys, GuidelineDTO gDTO)
	{
		String propId = null;
		String propL1Id = null;
		String propL2Id = null;
		
		//String list1 = null;
		//ArrayList list1 = new ArrayList();
		GuidelineDTO sub;
		ArrayList subClauseList = new ArrayList();
		HashMap hMap = new HashMap();
		String wardId = null;
		
		try
		{
			dbUtility = new DBUtility();
			
			//pst = connTest.prepareStatement(CommonSQL.SUBCLAUSE_NOT_CLICKED);
			for(int i = 0;i<subKeys.size();i++)
			{
				String list1 = null;
				try
				{
				
					String keys[] = ((String)subKeys.get(i)).split("_");
					int size = keys.length;
					logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^"+keys[1]+"#"+keys[2]+"#"+keys[3]);
					
						propId = keys[1];
						propL1Id = keys[2];
						
						propL2Id = keys[3];	
						logger.debug(propL2Id);
						
						String patID = gDTO.getPatwariID();
						String warID = gDTO.getWardID();
						
						if(patID != null && patID.length() > 0)
						{
							 wardId = gDTO.getPatwariID();
						}
						else
							 wardId = gDTO.getWardID();
						
						
						//if(propL2Id == null)
						//{
							//pst = connTest.prepareStatement(CommonSQL.SUBCLAUSE_NOT_CLICKED_NULL);
							//pst.setString(1,propId);
							//pst.setString(2,propL1Id);
							
						//}
						//else
						//{
							String sql =CommonSQL.DERIVE_SUB_NOTCLICKED;
							dbUtility.createPreparedStatement(sql);
							String sd[] = new String[7];
							sd[0] =gDTO.getGuideLineID();
							sd[1] =gDTO.getTehsilID();
							sd[2] =wardId;
							sd[3] =gDTO.getMohallaID();
							sd[4] =propId;
							sd[5] =propL1Id;
							sd[6] =propL2Id;
						//}
							String val = dbUtility.executeQry(sd);
				
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		                	list1 = (list1 == null?val:(list1+"_"+val));
		           
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        	hMap.put(subKeys.get(i),list1);
					
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
		
		return hMap;
	}
	
	
	/**
	 * 
	 * @param subId
	 *@return ArrayList that holds name of subclause whose Id is passed in this method
	 */
	public ArrayList subClause(String subId[])
	{
		int wardId = 0;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		GuidelineDTO map_sub ;
		ArrayList mapSubClauseList = new ArrayList();
		try
		{
			
			dbUtility = new DBUtility();
			//st =
				 //connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			for(int j = 0;j< subId.length;j++)
			{
				//String sql = "SELECT SUB_CLAUSE_ID,SUB_CLAUSE_NAME, SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER WHERE SUB_CLAUSE_ID = '"+subId[j]+"'";
				String sql = CommonSQL.GET_SUBCLAUSE_DETLS;
				dbUtility.createPreparedStatement(sql);
				
		
				String sd[] = new String[1];
				sd[0] = subId[j];
				logger.debug(sql);
			 try {
				 list2 = dbUtility.executeQuery(sd);
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
			}
		        list2.trimToSize();
		
		        logger.debug("SIZE OF ARRAYLIST"+list2.size());
			
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				map_sub = new GuidelineDTO();
				logger.debug("#######"+list1.get(0));
				
				map_sub.setSubClauseId((String)list1.get(0));
				map_sub.setSubName((String)list1.get(1));
				map_sub.setSubClauseName((String)list1.get(2));
				map_sub.setStatus("Y");
				
				
				mapSubClauseList.add(map_sub);
			}
			logger.debug("SIZE IN MAKER"+mapSubClauseList.size());
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
		
		return mapSubClauseList;
	}
	
	/**
	 * This function is used to insert subclause data
	 * @param subDetails
	 * @param gDTO
	 * @return boolean
	 */
	public boolean subClauseInsertion(ArrayList subDetails, GuidelineDTO gDTO)
	{
		logger.debug("SIZE of SUB CLAUSE LIST"+subDetails.size());
		String wardId = null;
		int j = 0;	
		boolean flag=false;
		boolean SubClauseUpdation = false;
		String patID = gDTO.getPatwariID();
		String warID = gDTO.getWardID();
		
		if(patID != null && patID.length() > 0)
		{
			 wardId = gDTO.getPatwariID();
		}
		else
			 wardId = gDTO.getWardID();
		
		try {
				
			dbUtility = new DBUtility();
			for(int i = 0;i<subDetails.size();i++ ){
				
				ArrayList list1 = new ArrayList();
				GuidelineDTO dto = new GuidelineDTO();
				dto = (GuidelineDTO)subDetails.get(i);
				int k = 0;
				//String sql = "SELECT DISTINCT SUBCLAUSE_ID FROM IGRS_SUBCLAUSE_DERIVED_MASTER WHERE GUIDELINE_ID = '"+gDTO.getGuideLineID()+"' AND TEHSIL_ID = '"+gDTO.getTehsilID()+"' AND WARD_PATWARI_ID = '"+wardId+"' AND MOHALLA_VILLAGE_ID = '"+gDTO.getMohallaID()+"' AND PROPERTY_TYPE_ID = '"+dto.getPropId()+"' AND PROPERTY_TYPE_L1_ID = '"+dto.getPropId1()+"' AND PROPERTY_TYPE_L2_ID = '"+dto.getPropId2()+"' AND STATUS = 'A'";
				String sql = CommonSQL.GET_SUBCLUASE_ID;
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[7];

				sd[0] =gDTO.getGuideLineID();
				sd[1] =gDTO.getTehsilID();
				sd[2] =wardId;
				sd[3] = gDTO.getMohallaID();
				sd[4] =dto.getPropId();
				sd[5] =dto.getPropId1();
				sd[6] =dto.getPropId2();
			//ArrayList list = new ArrayList();
				
				try {
					list1 = dbUtility.executeQuery(sd);
					logger.debug("size of rowlist"+list1.size());
				} catch (SQLException sqle) {
	            logger.debug("DBUtility - executeQuery(String): " +
	                               sqle.getMessage());
	            throw sqle;
				} catch (NumberFormatException nfe) {
	           logger.debug("DBUtility - executeQuery(String): " +
	                               nfe.getMessage());
	           throw nfe;
				} catch (IllegalArgumentException iae) {
	            logger.debug("DBUtility - executeQuery(String): " +
	                               iae.getMessage());
	            throw iae;
				}
				
			
				String sql1=CommonSQL.DERIVE_UPDATE;
				dbUtility.createPreparedStatement(sql1);
				String subId[] = dto.getSubClauseIds();
				
				logger.debug("PROP"+dto.getPropId());
				logger.debug("PROP1"+dto.getPropId1());
				logger.debug("PROP2"+dto.getPropId2());
				
				//logger.debug("SUB"+subId[0]);
				//logger.debug("SUB"+subId[1]);
				list1.trimToSize();
				logger.debug("**********SUBIDS IN DERIVE GUIDELINE"+list1.size());
				Iterator itr = list1.iterator();
				int sizeNew = (subId == null? 0:subId.length);
				while(itr.hasNext())
				{	String mapped= "N";
					String id = itr.next().toString();
					
					for(int m = 0;m< sizeNew;m++)
					{	
						logger.debug("LIST1"+id);
						logger.debug("selected 1"+subId[m]);
						if(id.equals(subId[m]))
						{
							mapped = "Y";
							break;
						}
						
					}
					logger.debug(mapped);
					try
					{
						String sd1[] = new String[10];
						
						
				
						sd1[0] =mapped;
						sd1[1] =gDTO.getGuideLineID();
						sd1[2] =gDTO.getDistrictID();
						sd1[3] =gDTO.getTehsilID();
						sd1[4] =wardId;
						sd1[5] =gDTO.getMohallaID();
					
						sd1[6] =dto.getPropId();
						sd1[7] =dto.getPropId1();
						sd1[8] =dto.getPropId2();
						sd1[9] =id;
					try {
						flag = dbUtility.executeUpdate(sd1);
		            } catch (StringIndexOutOfBoundsException e) {
		                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
		            }
		            logger.debug("Wipro : after execute update");
		            pst.clearParameters();
		        } catch (SQLException sqle) {
		            logger.debug("DAO- executeUpdate(String[]): " +
		                               sqle.getMessage());
		           
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        } catch (StringIndexOutOfBoundsException sioobe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sioobe.getMessage());
		            throw sioobe;
		        }
		        
		        if (flag=false)
		        	SubClauseUpdation = false;
		        SubClauseUpdation = true;
					
					
				}
				}
			} catch (Exception x) {
				try
				{
					dbUtility.rollback();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					x.printStackTrace();
			}finally {
				try {
						
					dbUtility.closeConnection();
					
				}catch (Exception xe ) {
					logger.debug("Error In Closing"+xe);
				}
			}
			logger.debug("GuideLineDAO::SUBCLAUSES UPDATED-  "+SubClauseUpdation);
			return SubClauseUpdation;
	}
	
	
	/*public ArrayList subclausesNew(GuidelineDTO gDTO)
	{
		String wardId = null;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO map_sub ;
		ArrayList mapSubClauseList = new ArrayList();
		try
		{
			connTest = DBUtilityTest.openConnection();
			logger.debug(gDTO.getL2_ID());
			
			String patID = gDTO.getPatwariID();
			String warID = gDTO.getWardID();
			
			if(patID != null && patID.length() > 0)
			{
				 wardId = gDTO.getPatwariID();
			}
			else
				 wardId = gDTO.getWardID();
			
			if(gDTO.getL2_ID().toString().equals("0"))
			{
				pst = connTest.prepareStatement(CommonSQL.SUBCLAUSE_NULL);
				pst.setString(1,gDTO.getPropertyID());
				pst.setString(2,gDTO.getL1_ID());
			}
			else
			{
				pst = connTest.prepareStatement(CommonSQL.SUBCLAUSE);
				
				pst.setString(1,gDTO.getPropertyID());
				pst.setString(2,gDTO.getL1_ID());
				pst.setString(3,gDTO.getL2_ID());
			}
			try
			{
				//pst.setString(4,gDTO.getDistrictID());
				//pst.setInt(5, gDTO.getTehID());
				//pst.setInt(6,gDTO.getWardId());
				//pst.setInt(7,gDTO.getMohallaId());
			
				 rst = pst.executeQuery();
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		            ResultSetMetaData rsmd = rst.getMetaData();
		            int col_count = rsmd.getColumnCount();
		            while (rst.next()) {
		                ArrayList row_list = new ArrayList();
		                for (int i = 1; i <= col_count; i++) {
		                    row_list.add(rst.getString(i));
		                } //for
		                list2.add(row_list);
		            } //while
		            pst.clearParameters();
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		
		        
			
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				map_sub = new GuidelineDTO();
				logger.debug("#######"+list1.get(0));
				map_sub.setSubClauseId((String)list1.get(0));
				map_sub.setSubClauseName((String)list1.get(1));
				map_sub.setStatus((String)list1.get(2));
				
				
				mapSubClauseList.add(map_sub);
			}
			logger.debug("SIZE IN MAKER"+mapSubClauseList.size());
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {

				rst.close();
				pst.close();
				DBUtilityTest.closeConnection();
				
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
		return mapSubClauseList;
	}*/
	
	/**
	 * This method is used to insert sub clauses in case of derived guideline
	 * @param subDetails
	 * @param gDTO
	 * @return boolean
	 */
	public boolean subClauseInsertionNewMohalla(ArrayList subDetails, GuidelineDTO gDTO)
	{
		logger.debug("SIZE of SUB CLAUSE LIST"+subDetails.size());
		String wardId = null;
		int j = 0;
		boolean flag=false;
		boolean SubClauseInsertion = false;
		
		try {
				
			dbUtility = new DBUtility();
			
			String sql=CommonSQL.SUB_NEW_MOHALLA;
			if(subDetails.size() >0)
			{
				for(int i = 0;i<subDetails.size();i++ ){
					GuidelineDTO dto = new GuidelineDTO();
					dto = (GuidelineDTO)subDetails.get(i);
					
					String subId[] = dto.getSubClauseIds();
					
					logger.debug("PROP"+dto.getPropId());
					logger.debug("PROP1"+dto.getPropId1());
					logger.debug("PROP2"+dto.getPropId2());
					
					//logger.debug("SUB"+subId[0]);
					//logger.debug("SUB"+subId[1]);
					int size = (subId == null? 0:subId.length);
					String patID = gDTO.getPatwariID();
					String warID = gDTO.getWardID();
					
					if(patID != null && patID.length() > 0)
					{
						 wardId = gDTO.getPatwariID();
					}
					else
						 wardId = gDTO.getWardID();
					
					for(int k = 0 ; k < size;k++)
					{
				try
				{
					dbUtility.createPreparedStatement(sql);
					String sd[] = new String[10];
			
					sd[0] =gDTO.getDistrictID();
					sd[1] =String.valueOf(gDTO.getTehID());
					sd[2] =wardId;
					sd[3] =String.valueOf(gDTO.getMohallaId());
					
					sd[4] =dto.getPropId();
					sd[5] =dto.getPropId1();
					sd[6] =dto.getPropId2();
					sd[7] =subId[k];
					sd[8] ="A";
					sd[9] =gDTO.getGuideLineID();
					flag = dbUtility.executeUpdate(sd);
										
					
					//mohallaInserted = dbUtil.executeUpdate(moharray);
					
		        } catch (SQLException sqle) {
		            logger.debug("DAO- executeUpdate(String[]): " +
		                               sqle.getMessage());
		           
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        } catch (StringIndexOutOfBoundsException sioobe) {
		            logger.debug("DAO - executeUpdate(String[]): " +
		                               sioobe.getMessage());
		            throw sioobe;
		        }
		        
		        if (flag =false)
		        	SubClauseInsertion = false;
		        SubClauseInsertion = true;
					
					
				}
				}
			}
			else
			{
				 SubClauseInsertion = true;
			}
		} catch (Exception x) {
			try
			{
				dbUtility.rollback();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				x.printStackTrace();
		}finally {
			try {
					
				dbUtility.closeConnection();
				
			}catch (Exception xe ) {
				logger.debug("Error In Closing"+xe);
			}
		}
		logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+SubClauseInsertion);
		return SubClauseInsertion;
	}
	
	/**
	 * 
	 * @param gDTO
	 *  @return ArrayList that holds available Draft versions of guideline
	 */
	public ArrayList availableDraftVersions(GuidelineDTO gDTO)
	{
		GuidelineDTO  version ;
		ArrayList versionList = new ArrayList();
		ArrayList listVer1 = new ArrayList();
		ArrayList listVer2 = new ArrayList();
		String dFrom = null;
		String dTo = null;
		String sql="";
		try {
			
			String type="";
			dbUtility = new DBUtility();
			String arr[]=new String[1];
	        //System.out.println("1");
	        arr[0]= gDTO.getLoggedOfficeId();
	        
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtility.createPreparedStatement(CommonSQL.CHECK_OFFICE_TYPE);
			 //System.out.println("heyyyyyy");
	        type = dbUtility.executeQry(arr);
			
	        if("SRO".equalsIgnoreCase(type))
	        {
	        	
	        	sql=CommonSQL.SR_VIEW_DRAFT_AVAILABLE;
	        }
	        else if("DRO".equalsIgnoreCase(type))
	        {
	        	
	        	sql=CommonSQL.DR_VIEW_DRAFT_AVAILABLE;
	        }
	        else
	        {
			
	        	sql=CommonSQL.VIEW_DRAFT_AVAILABLE;
	        }
					
			try
				{	
					String finan[] = gDTO.getFinancialYear().split("-");
					Integer f2 = Integer.parseInt(finan[0]);
					Integer f3 = Integer.parseInt(finan[1]);
					Integer f1 = f2-1;
					Integer f4 = f3+1;
					String finan1 = f1.toString()+"-"+f2.toString();
					String finan3 = f3.toString()+"-"+f4.toString();
					
					logger.debug("Financial Year1 in DAO"+finan1);
					logger.debug("Financial Year2 in DAO"+gDTO.getFinancialYear());
					logger.debug("Financial Year3 in DAO"+finan3);
					dbUtility.createPreparedStatement(sql);
					String sd[] = new String[2];
					sd[0] = gDTO.getDistrictID();
					
					sd[1] = gDTO.getFinancialYear();
					
					listVer2 = dbUtility.executeQuery(sd);
				      logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
				            
				     
				        } catch (SQLException sqle) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               sqle.getMessage());
				            throw sqle;
				        } catch (NumberFormatException nfe) {
				            logger.debug("DAO- executeQuery(String[]): " +
				                               nfe.getMessage());
				            throw nfe;
				        } catch (IllegalArgumentException iae) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               iae.getMessage());
				            throw iae;
				        }
				        listVer2.trimToSize();

				        for (int i = 0; i < listVer2.size(); i++) {
							listVer1 = (ArrayList)listVer2.get(i);
							version = new GuidelineDTO();
							version.setGuideLineID((String)listVer1.get(0));
							String fromDate = listVer1.get(1).toString().substring(0,10);
							String toDate = listVer1.get(2).toString().substring(0,10);
							
							String []dFromArr = fromDate.toString().split("-");
							if(dFromArr.length ==3)
							{
								 dFrom = dFromArr[2]+"/"+dFromArr[1]+"/"+dFromArr[0];
							}
							
							String [] dToArr = toDate.toString().split("-");
							if(dToArr.length ==3)
							{
								 dTo = dToArr[2]+"/"+dToArr[1]+"/"+dToArr[0];
							}
							
							version.setFromDepositeDate(dFrom);
							version.setToDepositeDate(dTo);
							version.setFinancialYear((String)listVer1.get(3));
							versionList.add(version);
				
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
		return versionList;
	}
	/*public String[] getVersionStatus(String ver)
	{
			try {
			
			ArrayList list2 = new ArrayList();
			ArrayList status = new ArrayList();
			int j= 0;
			boolean flag = false;
				
			
					connTest = DBUtilityTest.openConnection();
					
					pst = connTest.prepareStatement(CommonSQL.VER_STATUS);
					
					
					try
					{	
							
			                pst.setString(1, ver);
			              
							clstmt.setString(1, finan);
							clstmt.setString(2,disttID);
							clstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
							clstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
							clstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
							
							
			                rst = pst.executeQuery();
				            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
				            
				            ResultSetMetaData rsmd = rst.getMetaData();
				            int col_count = rsmd.getColumnCount();
				            while (rst.next()) {
				                
				            	 ArrayList row_list = new ArrayList();
					                for (int i = 1; i <= col_count; i++) {
					                    row_list.add(rst.getString(i));
					                } //for
					                list2.add(row_list);
				           
				            } //while
				            pst.clearParameters();
				        } catch (SQLException sqle) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               sqle.getMessage());
				            throw sqle;
				        } catch (NumberFormatException nfe) {
				            logger.debug("DAO- executeQuery(String[]): " +
				                               nfe.getMessage());
				            throw nfe;
				        } catch (IllegalArgumentException iae) {
				            logger.debug("DAO - executeQuery(String[]): " +
				                               iae.getMessage());
				            throw iae;
				        }
				        for (int i = 0; i < list2.size(); i++) {
				        	if(list2.get(i)==null)
				        	{
				        		flag = true;
				        		break;
				        	}
				        }
				        	if(flag)
				        {
				        	status.add("Pending");
				        	j++;
				        }
				        else
				        {
				        	status.add("Complete");
				        	j++;
				        }
			}
			catch(Exception e)
			{
				
			}
							
return status[];
	}*/
	public ArrayList getDistrictIdList(ArrayList officeList) {
		GuidelineDTO  district ;
		ArrayList districtList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		 String disttId = "";
		try {
			
			dbUtility = new DBUtility();
				//st =
				 //connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			 
				 for(int j = 0; j<officeList.size();j++)
				 {
					 ArrayList list = (ArrayList)officeList.get(j);
					 String officeId = (String)list.get(0);
					 logger.debug(""+officeId);
					 
					 try {
						 String sql = CommonSQL.GET_DISTRICT;
						 dbUtility.createPreparedStatement(sql);
							String sd[] = new String[1];
							sd[0] = officeId;
							String id =dbUtility.executeQry(sd);
						 logger.debug("<-------"+sql);
						 disttId = id;
			            if(!list2.contains(disttId))
			            {
			            	list2.add(disttId);
			            }
			        } catch (SQLException sqle) {
			            logger.debug("DAO - executeQuery(String): " +
			                               sqle.getMessage());
			            throw sqle;
			        } catch (NumberFormatException nfe) {
			           logger.debug("DAO- executeQuery(String): " +
			                               nfe.getMessage());
			            throw nfe;
			        } catch (IllegalArgumentException iae) {
			            logger.debug("DAO- executeQuery(String): " +
			                               iae.getMessage());
			            throw iae;
			        }
			        list2.trimToSize();
				 }
		           
		
			/*for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				district = new GuidelineDTO();

				district.setDistrictID((String)list1.get(0));
				district.setDistrict((String)list1.get(1));
				district.setHdnDistrict((String)list1.get(0)+"~"+(String)list1.get(1));
				districtList.add(district);
			}*/
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
		return getDistrictList(list2);
	}
	
	public ArrayList getDistrictList(ArrayList disttIdList) {
		GuidelineDTO  district ;
		ArrayList districtList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		 String disttId = "";
		try {
			
			dbUtility = new DBUtility();
				
			
			 
				 for(int j = 0; j<disttIdList.size();j++)
				 {
					// ArrayList list = (ArrayList)disttIdList.get(j);
					  disttId = (String)disttIdList.get(j);
					 logger.debug("distt"+disttId);
					 
					 try {
						 String sql = CommonSQL.GET_DISRICT_NAME;
						 dbUtility.createPreparedStatement(sql);
							String sd[] = new String[1];
							sd[0] = disttId;
							list2 = dbUtility.executeQuery(sd);

			            
			        } catch (SQLException sqle) {
			            logger.debug("DAO - executeQuery(String): " +
			                               sqle.getMessage());
			            throw sqle;
			        } catch (NumberFormatException nfe) {
			           logger.debug("DAO- executeQuery(String): " +
			                               nfe.getMessage());
			            throw nfe;
			        } catch (IllegalArgumentException iae) {
			            logger.debug("DAO- executeQuery(String): " +
			                               iae.getMessage());
			            throw iae;
			        }
			        list2.trimToSize();
				 }
		           
		
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				district = new GuidelineDTO();

				district.setDistrictID((String)list1.get(0));
				district.setDistrict((String)list1.get(1));
				district.setHdnDistrict((String)list1.get(0)+"~"+(String)list1.get(1));
				districtList.add(district);
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
		return districtList;
	}
	
	
	public HashMap subClauseNotClickedNewMohalla(ArrayList subKeys, GuidelineDTO gDTO)
	{
		String propId = null;
		String propL1Id = null;
		String propL2Id = null;
		
		//String list1 = null;
		//ArrayList list1 = new ArrayList();
		GuidelineDTO sub;
		ArrayList subClauseList = new ArrayList();
		HashMap hMap = new HashMap();
		String wardId = null;
		String sql ="";
		String val="";
		try
		{
			dbUtility = new DBUtility();
			//pst = connTest.prepareStatement(CommonSQL.SUBCLAUSE_NOT_CLICKED);
			for(int i = 0;i<subKeys.size();i++)
			{
				String list1 = null;
				try
				{
				
					String keys[] = ((String)subKeys.get(i)).split("_");
					int size = keys.length;
					logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^"+keys[1]+"#"+keys[2]+"#"+keys[3]);
					
						propId = keys[1];
						propL1Id = keys[2];
						
						propL2Id = keys[3];	
						logger.debug(propL2Id);
						
						String patID = gDTO.getPatwariID();
						String warID = gDTO.getWardID();
						
						if(patID != null && patID.length() > 0)
						{
							 wardId = gDTO.getPatwariID();
						}
						else
							 wardId = gDTO.getWardID();
						
						
						//if(propL2Id == null)
						//{
							//pst = connTest.prepareStatement(CommonSQL.SUBCLAUSE_NOT_CLICKED_NULL);
							//pst.setString(1,propId);
							//pst.setString(2,propL1Id);
							
						//}
						//else
						//{
						if(gDTO.getL2_ID().toString().equals("0"))
						{
							sql = CommonSQL.DERIVE_SUBCLAUSE_NOT_CLICKED_NEW_MOHALLA_NULL;
							dbUtility.createPreparedStatement(sql);
							String sd[] = new String[2];
							sd[0] = gDTO.getPropertyID();
							sd[1] = gDTO.getL1_ID();
							 val= dbUtility.executeQry(sd);
						}
						else
						{
							sql =CommonSQL.DERIVE_SUBCLAUSE_NOT_CLICKED_NEW_MOHALLA;
							dbUtility.createPreparedStatement(sql);
							String sd[] = new String[3];
							sd[0] = gDTO.getPropertyID();
							sd[1] = gDTO.getL1_ID();
							sd[2] = gDTO.getL2_ID();
							 val= dbUtility.executeQry(sd);
						}
							
		                	list1 = (list1 == null?val:(list1+"_"+val));
		               
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        	hMap.put(subKeys.get(i),list1);
					
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
		
		return hMap;
	}
	
	/**
	 * 
	 * @param officeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrict(String officeId, GuidelineDTO ggdto) throws Exception
	{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();
		ArrayList list2 = new ArrayList();
		logger.debug("<-----In DAO----->");
		String language = ggdto.getLanguage();
		String sql= "";
		try {
			dbUtility = new DBUtility();
			if(language.equalsIgnoreCase("hi"))
			{
				sql=CommonSQL.GET_DISTRICT_LIST_HI;
			}
			else
			{
				sql=CommonSQL.GET_DISTRICT_LIST;
			}
			
			
			try
			{	
			
	              
	                dbUtility.createPreparedStatement(sql);
					String sd[] = new String[1];
					sd[0] = officeId;
					
					list2 = dbUtility.executeQuery(sd);
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		           
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
			
			
			for(int i=0; i<list2.size();i++)
			{
				subList =(ArrayList)list2.get(i);
				GuidelineDTO gdto = new GuidelineDTO();
				gdto.setDistrictID((String)subList.get(0));
				if(language.equalsIgnoreCase("en"))
				{
					gdto.setDistrict((String)subList.get(1));
					gdto.setHdnDistrict((String)subList.get(0)+"~"+(String)subList.get(1));
				}
				else
				{
					gdto.setDistrict((String)subList.get(2));
					gdto.setHdnDistrict((String)subList.get(0)+"~"+(String)subList.get(2));
				}
				
				mainList.add(gdto);
			}
			
		}
		catch(Exception e)
		{
			logger.debug(e);
			e.printStackTrace();
		}
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception ex) {

				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return mainList;
	}
	
	// added by simran for changes 
	
	public ArrayList getSubArea(String language, String areaId, String tehsilId, String urbanFlag, String guidelineID) {
		ArrayList subAreaList=null;
	
	try {

		dbUtility = new DBUtility();
		if(language.equalsIgnoreCase("en"))
		{	
			if("Y".equalsIgnoreCase(urbanFlag))
			{	
				dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_EN_UR);
			subAreaList=dbUtility.executeQuery(new String[]{areaId,tehsilId,guidelineID});

			}
			else
			{
				dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_EN);	
				subAreaList=dbUtility.executeQuery(new String[]{areaId});
			}
		}
		else
		{
			if("Y".equalsIgnoreCase(urbanFlag))
			{	
				dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_HI_UR);
			subAreaList=dbUtility.executeQuery(new String[]{areaId,tehsilId,guidelineID});
			}
			else
			{
				dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_HI);	
				subAreaList=dbUtility.executeQuery(new String[]{areaId});

			}
		}
		
		
		
	} catch (Exception e) {
		logger.error(e);
		return null;
	}
	finally {
		try {

			dbUtility.closeConnection();
			
		} catch (Exception ex) {
			logger.debug(ex);
			ex.printStackTrace();
		}
	}
	return subAreaList;
}
	
	public ArrayList getWardPatwari(String language, String subAreaId,
			String tehsilId,String guidelineID) {
			ArrayList wardPatwariList=null;
		
		try {
			dbUtility = new DBUtility();
			if(language.equalsIgnoreCase("en"))
			{	
				dbUtility.createPreparedStatement(GuidelineNewSQL.WARD_PATWARI_NAME_EN);
			}
			else
			{
				dbUtility.createPreparedStatement(GuidelineNewSQL.WARD_PATWARI_NAME_HI);	
			}
			wardPatwariList=dbUtility.executeQuery(new String[]{subAreaId,tehsilId,guidelineID});
		
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		finally {
			try {

				dbUtility.closeConnection();
				
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return wardPatwariList;
	}
	
	public ArrayList getColonyName(String language, String wardPatwariId, String guidelineID) {
		ArrayList l2NameList=null;
		String wardid="";
		try {
			dbUtility = new DBUtility();
			if(language.equalsIgnoreCase("en"))
			{	
				dbUtility.createPreparedStatement(GuidelineNewSQL.COLONY_NAME_EN);
			}
			else
			{
				dbUtility.createPreparedStatement(GuidelineNewSQL.COLONY_NAME_HI);	
			}
			
			if(wardPatwariId==null || wardPatwariId.equalsIgnoreCase(""))
			{
				wardid="";
			}
			else
			{
				String ward [] =  wardPatwariId.split("~");
				wardid= ward[1];
			
				
			}
			l2NameList=dbUtility.executeQuery(new String[]{guidelineID,wardid});
		
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		finally {
			try {

				dbUtility.closeConnection();
				
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return l2NameList;
	}
	
	public ArrayList deriveGuidelineDetails(ArrayList guidelineValues)
	{
		logger.debug("size of orignal List-->"+guidelineValues.size());
		ArrayList propDetailsForPV = new ArrayList();
		ArrayList finalList = new ArrayList();
		ArrayList propList = new ArrayList();
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			propList=dbUtility.executeQuery(GuidelineNewSQL.GET_PROPERTY_DETAILS_FOR_PV);
			for(int i = 0 ;i < propList.size(); i++ )
			{
				mainList = (ArrayList)propList.get(i);
				GuidelineDTO ggdto = new GuidelineDTO();
				ggdto.setPropertyID((String)mainList.get(0));
				ggdto.setL1_ID((String)mainList.get(1));
				ggdto.setL2_ID((String)mainList.get(2));
				String l1Logic = (String)mainList.get(3);
				String l2Logic = (String)mainList.get(4);
				l2Logic = l2Logic == null ? "":l2Logic;
				if(l2Logic == "")
					ggdto.setComputationLogic(l1Logic);
				else
					ggdto.setComputationLogic(l2Logic);
				propDetailsForPV.add(ggdto);
				
			}
			//deriving values and creating final ArrayList
			logger.debug("size of PV List-->"+propDetailsForPV.size());
			for(int j = 0 ; j < propDetailsForPV.size(); j ++)
			{
				GuidelineDTO ggdto = (GuidelineDTO)propDetailsForPV.get(j);
				for(int k = 0 ; k < guidelineValues.size() ; k++)
				{
					GuidelineDTO fdto = (GuidelineDTO)guidelineValues.get(k);
					finalList.add(fdto);
			
				
					String compLogic[] = ggdto.getComputationLogic().split("~");
					String l1Id = "";
					String operator = "";
					String val = "";
					//logger.debug("comp length"+compLogic.length);
					//logger.debug("comp  "+compLogic);
						if(compLogic[0].equalsIgnoreCase("L1"))
						{
							logger.debug("computation"+compLogic.length);
							if(compLogic.length == 4)
							{
								 l1Id = compLogic[1];
								 operator = compLogic[2];
								 val = compLogic[3];
							}
							else
							{
								l1Id = compLogic[1];
								operator = "*";
								val = "1";
							}
							/*System.out.println("final propId"+fdto.getPropertyID());
							System.out.println("propId"+ggdto.getPropertyID());
							System.out.println("final L1"+fdto.getL1_ID());
							System.out.println("L1"+ggdto.getL1_ID());*/
							if(l1Id.equals(fdto.getL1_ID()))
							{
								//logger.debug("Match------------------->");
								BigDecimal guideval = fdto.getProposedValue();
								Double guideVal = guideval.doubleValue();
								Double multiplyingFactor = Double.parseDouble(val);
								System.out.println(fdto.getPropertyID()+"~~~"+fdto.getL1_ID()+"~"+fdto.getL2_ID()+"~~~"+guideVal+"~~~"+multiplyingFactor+"---"+operator+"~~~ to be updated val --"+ggdto.getPropertyID()+"~~~"+ggdto.getL1_ID()+"~"+ggdto.getL2_ID());
								if(operator.equals("*"))
								{
									Double finalVal = guideVal * multiplyingFactor;
									ggdto.setProposedValue(BigDecimal.valueOf(finalVal));
								}
								else if(operator.equals("/"))
								{
									Double finalVal = guideVal / multiplyingFactor;
									ggdto.setProposedValue(BigDecimal.valueOf(finalVal));
								}
								guidelineValues.add(ggdto);
								break;
							}
						}
						else
						{
							if(compLogic.length == 4)
							{
								 l1Id = compLogic[1];
								 operator = compLogic[2];
								 val = compLogic[3];
							}
							else
							{
								l1Id = compLogic[1];
								operator = "*";
								val = "1";
							}
							/*System.out.println("final propId"+fdto.getPropertyID());
							System.out.println("propId"+ggdto.getPropertyID());
							System.out.println("final L1"+fdto.getL1_ID());
							System.out.println("L1"+ggdto.getL1_ID());
							*/
							if(l1Id.equals(fdto.getL2_ID()))
							{
								//logger.debug("Match------------------->");
								BigDecimal guideval = fdto.getProposedValue();
								Double guideVal = guideval.doubleValue();
								Double multiplyingFactor = Double.parseDouble(val);
								System.out.println(fdto.getPropertyID()+"~~~"+fdto.getL1_ID()+"~"+fdto.getL2_ID()+"~~~"+guideVal+"~~~"+multiplyingFactor+"---"+operator+"~~~ to be updated val --"+ggdto.getPropertyID()+"~~~"+ggdto.getL1_ID()+"~"+ggdto.getL2_ID());
								if(operator.equals("*"))
								{
									Double finalVal = guideVal * multiplyingFactor;
									ggdto.setProposedValue(BigDecimal.valueOf(finalVal));
								}
								else if(operator.equals("/"))
								{
									Double finalVal = guideVal / multiplyingFactor;
									ggdto.setProposedValue(BigDecimal.valueOf(finalVal));
								}
								guidelineValues.add(ggdto);
								break;
							}
						}
					
					
					
				}
			}
			logger.debug("size of finalList"+guidelineValues.size());
			for(int m = 0 ; m < guidelineValues.size() ;m++)
			{
				GuidelineDTO fdto = (GuidelineDTO)guidelineValues.get(m);
				System.out.println(fdto.getPropertyID()+"~"+"~"+fdto.getL1_ID()+"~"+fdto.getL2_ID()+"~"+fdto.getProposedValue());
			}
		} catch (Exception e) {
			logger.error(e);
			return null;
		}	
		finally {
			try {

				dbUtility.closeConnection();
				
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return guidelineValues;
	}
	
	public ArrayList getTehsilListMakerCreation(String distId, long guideID, GuidelineDTO ggdto)
	{
		GuidelineDTO  tehsil ;
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		try {
			
			dbUtility = new DBUtility();
			
			String sql =CommonSQL.TEHSIL_MAKER_FIRST_TIME;
			
			try
			{	
			
	           
	                dbUtility.createPreparedStatement(sql);
					String sd[] = new String[1];
					sd[0] = distId;
					
				 list2 = dbUtility.executeQuery(sd);
	               
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				 tehsil = new GuidelineDTO();
				tehsil.setTehsilID((String)list1.get(0));
				logger.debug("********"+list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					tehsil.setTehsil((String)list1.get(1));
					tehsil.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(1));
				}
				else
				{
					tehsil.setTehsil((String)list1.get(2));
					tehsil.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(2));
				}
				
				tehsilList.add(tehsil);
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
		return tehsilList;
	}
	
	public ArrayList getSubAreaFirst(String language, String areaId, String tehsilId, String urbanFlag) {
		ArrayList subAreaList=null;
	
	try {
		dbUtility = new DBUtility();
		if(language.equalsIgnoreCase("en"))
		{	
			if("Y".equalsIgnoreCase(urbanFlag))
			{	
				dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_EN_UR_FIRST);
			subAreaList=dbUtility.executeQuery(new String[]{areaId,tehsilId});

			}
			else
			{
				dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_EN);	
				subAreaList=dbUtility.executeQuery(new String[]{areaId});
			}
		}
		else
		{
			if("Y".equalsIgnoreCase(urbanFlag))
			{	
				dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_HI_UR_FIRST);
			subAreaList=dbUtility.executeQuery(new String[]{areaId,tehsilId});
			}
			else
			{
				dbUtility.createPreparedStatement(GuidelineNewSQL.SUB_AREA_NAME_HI);	
				subAreaList=dbUtility.executeQuery(new String[]{areaId});

			}
		}
		
	
		
	} catch (Exception e) {
		logger.error(e);
		return null;
	}
	finally {
		try {

			dbUtility.closeConnection();
			
		} catch (Exception ex) {
			logger.debug(ex);
			ex.printStackTrace();
		}
	}
	return subAreaList;
	
}
	
	public ArrayList getWardPatwariFirst(String language, String subAreaId,
			String tehsilId) {
			ArrayList wardPatwariList=null;
		
		try {
			dbUtility = new DBUtility();
			if(language.equalsIgnoreCase("en"))
			{	
				dbUtility.createPreparedStatement(GuidelineNewSQL.WARD_PATWARI_NAME_EN_FIRST);
			}
			else
			{
				dbUtility.createPreparedStatement(GuidelineNewSQL.WARD_PATWARI_NAME_HI_FIRST);	
			}
			wardPatwariList=dbUtility.executeQuery(new String[]{subAreaId,tehsilId});
			
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		finally {
			try {

				dbUtility.closeConnection();
				
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return wardPatwariList;
	}
	
	public ArrayList getColonyNameFirst(String language, String wardPatwariId) {
		ArrayList l2NameList=null;
		
		try {
			dbUtility = new DBUtility();
			if(language.equalsIgnoreCase("en"))
			{	
				dbUtility.createPreparedStatement(GuidelineNewSQL.COLONY_NAME_EN_FIRST);
			}
			else
			{
				dbUtility.createPreparedStatement(GuidelineNewSQL.COLONY_NAME_HI_FIRST);	
			}
			l2NameList=dbUtility.executeQuery(new String[]{wardPatwariId.split("~")[1]});
			
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		finally {
			try {

				dbUtility.closeConnection();
				
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return l2NameList;
	}
	
	public ArrayList checkIndustrialRate(ArrayList guidelineValuesList)
	{
		
		logger.debug("checking industrial value");
		ArrayList resPlotArrayList = guidelineValuesList;
		for(int i = 0;i<guidelineValuesList.size();i++ ){
			GuidelineDTO dto = (GuidelineDTO)guidelineValuesList.get(i);
			String propertyType = dto.getPropertyID();
			String propTypeL1 = dto.getL1_ID();
			if(propertyType.equals(CommonConstant.PROPERTY_TYPE_FOR_PLOT_INDUSTRIAL) && propTypeL1.equals(CommonConstant.PROPERTY_TYPE_L1_FOR_PLOT_INDUSTRIAL))
			{
				logger.debug("Industrial"+dto.getProposedValue());
				Double indVal = dto.getProposedValue().doubleValue();
				if(indVal.equals(Double.parseDouble("0.0")))
				{
					logger.debug("zero for industrial");
					for(int j = 0;j<resPlotArrayList.size();j++ ){
						GuidelineDTO rdto = (GuidelineDTO)resPlotArrayList.get(j);
						if(rdto.getPropertyID().equals(CommonConstant.PROPERTY_TYPE_FOR_PLOT_INDUSTRIAL) && rdto.getL1_ID().equals(CommonConstant.PROPERTY_TYPE_L1_FOR_PLOT_RESIDENTIAL))
						{
							Double resVal = rdto.getProposedValue().doubleValue();
							logger.debug("residential rate"+resVal);
							dto.setProposedValue(BigDecimal.valueOf((resVal*.7)));
							break;
						}
						
					
				}
			}
			break;
		}
		
	}
		return guidelineValuesList;
}

	public ArrayList getTehsilListViewall(String distId, long guideID,
			GuidelineDTO ggdto) {
		GuidelineDTO  tehsil ;
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		String sql ="";
		try {
			
			dbUtility = new DBUtility();
			
			String status= String.valueOf(ggdto.getGuideStatus());
			String type= ggdto.getGuideType();
			
			if("2".equalsIgnoreCase(status) || "P".equalsIgnoreCase(type))
			{
			sql =CommonSQL.TEHSIL_VIEW_TEMP_ALL;
			}
			
			else
			{
				sql =CommonSQL.TEHSIL_VIEW_ALL;
			}
			try
			{	
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[1];
				sd[0] = String.valueOf(guideID);
	               
	                list2 = dbUtility.executeQuery(sd);
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		           
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				 tehsil = new GuidelineDTO();
				tehsil.setTehsilID((String)list1.get(0));
				logger.debug("********"+list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					tehsil.setTehsil((String)list1.get(1));
					tehsil.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(1));
				}
				else
				{
					tehsil.setTehsil((String)list1.get(2));
					tehsil.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(2));
				}
				
				tehsilList.add(tehsil);
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
		return tehsilList;
	}

	public ArrayList getSubAreaListViewall(GuidelineDTO ggdto, String guideID) {
		GuidelineDTO  subarea ;
		ArrayList subareaList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		String sql ="";
		try {
			
			dbUtility = new DBUtility();
			
			String status= String.valueOf(ggdto.getGuideStatus());
			
			String type= ggdto.getGuideType();
			
			if("2".equalsIgnoreCase(status) || "P".equalsIgnoreCase(type))
			{
			sql= CommonSQL.SUBAREA_VIEW_TEMP_ALL;
			}
			else
			{
				sql= CommonSQL.SUBAREA_VIEW_ALL;
			}
			try
			{	
			
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[3];

				sd[0] =guideID;
				sd[1] =ggdto.getTehsilID();
				sd[2] =ggdto.getAreaTypeID();
	                
	              list2 = dbUtility.executeQuery(sd);
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				subarea = new GuidelineDTO();
				subarea.setSideSubId((String)list1.get(0));
				logger.debug("********"+list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					subarea.setSideSubName((String)list1.get(1));
					//subarea.sethdn((String)list1.get(0)+"~"+(String)list1.get(1));
				}
				else
				{
					subarea.setSideSubName((String)list1.get(2));
					//subarea.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(2));
				}
				
				subareaList.add(subarea);
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
		return subareaList;
	}

	public ArrayList getWardListViewall(GuidelineDTO ggdto, String guideID) {
		GuidelineDTO  wardpatwari ;
		ArrayList wardList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		String sql ="";
		try {
			
			dbUtility = new DBUtility();
			String status= String.valueOf(ggdto.getGuideStatus());
			
			String type= ggdto.getGuideType();
			
			if("2".equalsIgnoreCase(status) || "P".equalsIgnoreCase(type))
			{
				sql =CommonSQL.WARD_VIEW_TEMP_ALL;
			}
			else
			{
				sql =CommonSQL.WARD_VIEW_ALL;
			}
			try
			{	
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[4];
	              
				sd[0] =guideID;
				sd[1] =ggdto.getTehsilID();
				sd[2] =ggdto.getAreaTypeID();
				sd[3] =ggdto.getSideSubId();
	                list2 = dbUtility.executeQuery(sd);
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		           
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				wardpatwari = new GuidelineDTO();
				wardpatwari.setWardID((String)list1.get(0));
				logger.debug("********"+list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					wardpatwari.setWard((String)list1.get(1));
					//subarea.sethdn((String)list1.get(0)+"~"+(String)list1.get(1));
				}
				else
				{
					wardpatwari.setWard((String)list1.get(2));
					//subarea.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(2));
				}
				
				wardList.add(wardpatwari);
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
		return wardList;
	}

	public ArrayList getMohallaListViewall(GuidelineDTO ggdto, String guideID) {
		GuidelineDTO  mohallavillage;
		ArrayList mohallavillageList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		String sql ="";
		try {
			
			dbUtility = new DBUtility();
			String status= String.valueOf(ggdto.getGuideStatus());
			
			String type= ggdto.getGuideType();
			
			if("2".equalsIgnoreCase(status) || "P".equalsIgnoreCase(type))
			{
				sql =CommonSQL.COLONY_VIEW_TEMP_ALL;
			}
			else
			{
				sql =CommonSQL.COLONY_VIEW_ALL;
			}
			try
			{	
					ggdto.setSubAreaID("6");
					dbUtility.createPreparedStatement(sql);
					String sd[] = new String[5];
					
					sd[0] =guideID;
					sd[1] =ggdto.getTehsilID();
					sd[2] =ggdto.getAreaTypeID();
					sd[3] =ggdto.getSideSubId();
					sd[4] =ggdto.getWardID();
					list2 = dbUtility.executeQuery(sd);

		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		           
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohallavillage = new GuidelineDTO();
				mohallavillage.setMohallaID((String)list1.get(0));
				logger.debug("********"+list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					mohallavillage.setMohalla((String)list1.get(1));
					//subarea.sethdn((String)list1.get(0)+"~"+(String)list1.get(1));
				}
				else
				{
					mohallavillage.setMohalla((String)list1.get(2));
					//subarea.setHdnTehsil((String)list1.get(0)+"~"+(String)list1.get(2));
				}
				
				mohallavillageList.add(mohallavillage);
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
		return mohallavillageList;
	}

	public ArrayList getguidelineratesList(GuidelineDTO ggdto, String guideID) {
		
		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		ArrayList list3 = new ArrayList(); 
		String lang = ggdto.getLanguage();
		String sql ="";
		try {
			//dbUtil = new DBUtility();

			//dbUtil.createPreparedStatement(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS);
			//logger.debug(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS);
			dbUtility = new DBUtility();
			
			String status= String.valueOf(ggdto.getGuideStatus());
			
			String type= ggdto.getGuideType();
			
			if("2".equalsIgnoreCase(status) || "P".equalsIgnoreCase(type))
			{
			sql = CommonSQL.GET_TEMP_VIEW_RATES_ALL;
			}
			else
			{
			sql = CommonSQL.GET_FINAL_VIEW_RATES_ALL;
			}
			//list2 = dbUtil.executeQuery(param);
			logger.debug("GUIDE"+guideID);
			
			
			try
			{
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[2];
				sd[0] = guideID;
				sd[1] = ggdto.getMohallaID();
				list2 = dbUtility.executeQuery(sd);
				
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeQuery(String[]): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	        	logger.debug("DAO- executeQuery(String[]): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	        	logger.debug("DAO- executeQuery(String[]): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        list2.trimToSize();
	        logger.debug("SIZE IN DAO"+list2.size());
	        
	       
		        	for (int i = 0; i < list2.size(); i++) {
		        	
					list1 = (ArrayList)list2.get(i);
					mohallaDetails = new GuidelineDTO();

					
					
					mohallaDetails.setGuideLineValue((String)list1.get(3));
					//mohallaDetails.setAverageValue((String)list1.get(4));
					//mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
					//mohallaDetails.setMohallaID((String)list1.get(6));
					
					mohallaDetails.setPropertyID((String)list1.get(7));
					mohallaDetails.setL1_ID((String)list1.get(8));
					mohallaDetails.setL2_ID((String)list1.get(9));
					
					mohallaDetails.setUomID((String)list1.get(11));
					if(lang.equalsIgnoreCase("en"))
					{
						mohallaDetails.setPropertyTypeName((String)list1.get(0));
						mohallaDetails.setL1Name((String)list1.get(1));
						mohallaDetails.setL2Name((String)list1.get(2));
						mohallaDetails.setUomName((String)list1.get(10));
					}
					else
					{
						mohallaDetails.setPropertyTypeName((String)list1.get(12));
						mohallaDetails.setL1Name((String)list1.get(13));
						mohallaDetails.setL2Name((String)list1.get(14));
						mohallaDetails.setUomName((String)list1.get(16));
					}
					mohallaDetailsList.add(mohallaDetails);
		        }
			//}
		}
		 catch (Exception x) {
			 x.printStackTrace();
			logger.debug(x);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
		return mohallaDetailsList;
		
		
		
		
		
	}

	public GuidelineDTO getFinancialDuration(GuidelineDTO ggdto, String guideID) {
		GuidelineDTO  FiYear = null;
		//ArrayList mohallavillageList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = ggdto.getLanguage();
		try {
			
			dbUtility = new DBUtility();
			
			String sql = CommonSQL.GET_FIYEAR_DETLS;
			
			try
			{	
					
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[1];
				sd[0] = guideID;
				list2 = dbUtility.executeQuery(sd);
					
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		            logger.debug("DAO- executeQuery(String[]): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO - executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		         list1 = (ArrayList) list2.get(0);
		         FiYear = new GuidelineDTO();
		         FiYear.setFinancialYear(list1.get(0).toString());
		         FiYear.setFromDepositeDate(list1.get(1).toString());
		         FiYear.setToDepositeDate(list1.get(2).toString());
		      
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
		return FiYear;
	}

	public String getMohallaAppliclause(GuidelineDTO formDTO2) {
		
		String appliclause=null;
		 
		try {
			dbUtility = new DBUtility();
			String arr[]=new String[1];
	        //System.out.println("1");
	        arr[0]= formDTO2.getMohallaChkId();
	        
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtility.createPreparedStatement(CommonSQL.COL_SUBCLAUSE_CHK);
			 //System.out.println("heyyyyyy");
	        appliclause = dbUtility.executeQry(arr);
			 //System.out.println(list);
			 //System.out.println("just printed query");
		} catch (Exception e) {
			//System.out.println("Exception in getFServices():" + e);
		}finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				
				}
			}catch(Exception x) {
				//System.out.println(x);
			}
		}
		//System.out.println("returning value");
		return appliclause;
	
	}

	public boolean getUpdatePrintStatus(GuidelineDTO formDTO2) throws Exception {
		
		boolean flag=false;
		 
		try {
			dbUtility = new DBUtility();
	
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtility.createPreparedStatement(CommonSQL.UPDATE_PRINT_STATUS);
			 //System.out.println("heyyyyyy");
	        flag = dbUtility.executeUpdate(new String[]{formDTO2.getGuideLineID()});
	        
	        dbUtility.createPreparedStatement(CommonSQL.UPDATE_COMPLETION_STATUS);
			 //System.out.println("heyyyyyy");
	        flag = dbUtility.executeUpdate(new String[]{formDTO2.getGuideLineID()});
	        
	        if(flag)
	        {
	        	formDTO2.setDownlaodChkId("Y");
	        	 dbUtility.createPreparedStatement(CommonSQL.UPDATE_SR_PUBLISH_STATUS);
				 //System.out.println("heyyyyyy");
		        flag = dbUtility.executeUpdate(new String[]{formDTO2.getGuideLineID()});
		        if(flag)
		        {
		        	logger.debug("SR Publish status updated");
		        }
		        
		        else
		        {
		        	throw new Exception("update failed");
		        }
		        
		        
	        	logger.debug("Print status updated");
	        }
	        else
	        {
	        	throw new Exception("update failed");
	        }
			 //System.out.println(list);
			 //System.out.println("just printed query");
		} catch (Exception e) {
			//System.out.println("Exception in getFServices():" + e);
		}finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				
				}
			}catch(Exception x) {
				
				logger.debug(x);
				x.printStackTrace();
				throw x;
				//System.out.println(x);
			}
		}
		//System.out.println("returning value");
	return flag;
	}

	public String getSRPublishStatus(GuidelineDTO formDTO2) {
		String srstatus=null;
		 
		try {
			dbUtility = new DBUtility();
			String arr[]=new String[1];
	        //System.out.println("1");
	        arr[0]= formDTO2.getGuideLineID();
	        
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtility.createPreparedStatement(CommonSQL.CHECK_SR_PUBLISH_STATUS);
			 //System.out.println("heyyyyyy");
	        srstatus = dbUtility.executeQry(arr);
			 //System.out.println(list);
			 //System.out.println("just printed query");
		} catch (Exception e) {
			//System.out.println("Exception in getFServices():" + e);
		}finally {
			try { 
				if (dbUtility != null) {
					dbUtility.closeConnection();
				
				}
			}catch(Exception x) {
				//System.out.println(x);
			}
		}
		//System.out.println("returning value");
		return srstatus;
	}

	public String getPrintStatus(GuidelineDTO formDTO2) {
		String PrintStatus=null;
		 
		try {
			dbUtility = new DBUtility();
			String arr[]=new String[1];
	        //System.out.println("1");
	        arr[0]= formDTO2.getGuideLineID();
	        
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtility.createPreparedStatement(CommonSQL.CHECK_PRINT_STATUS);
			 //System.out.println("heyyyyyy");
	        PrintStatus = dbUtility.executeQry(arr);
	        
	        
	        String type="";
			dbUtility = new DBUtility();
			String arr1[]=new String[1];
	        //System.out.println("1");
	        arr1[0]= formDTO2.getLoggedOfficeId();
	        
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtility.createPreparedStatement(CommonSQL.CHECK_OFFICE_TYPE);
			 //System.out.println("heyyyyyy");
	        type = dbUtility.executeQry(arr1);
	        
	        formDTO2.setUserType(type);
			 //System.out.println(list);
			 //System.out.println("just printed query");
		} catch (Exception e) {
			//System.out.println("Exception in getFServices():" + e);
		}finally {
			try { 
				if (dbUtility != null) {
					dbUtility.closeConnection();
				
				}
			}catch(Exception x) {
				//System.out.println(x);
			}
		}
		//System.out.println("returning value");
		return PrintStatus;
	}

	public boolean getUpdateSubmitStatus(GuidelineDTO formDTO2) throws Exception {
		boolean flag=false;
		 
		try {
			dbUtility = new DBUtility();
	
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtility.createPreparedStatement(CommonSQL.UPDATE_SUBMIT_TO_DR);
			 //System.out.println("heyyyyyy");
	        flag = dbUtility.executeUpdate(new String[]{formDTO2.getGuideLineID()});
	        
	        if(flag)
	        {
	        	boolean autoapprove=false;
	        	formDTO2.setDownlaodChkId("Y");
	        	logger.debug("submit to DR status updated");
	        	dbUtility.createPreparedStatement(CommonSQL.UPDATE_AUTO_APPROVE_TO_DR);
				 //System.out.println("heyyyyyy");
	        	autoapprove = dbUtility.executeUpdate(new String[]{formDTO2.getGuideLineID()});
	        	
	        	dbUtility.createPreparedStatement(CommonSQL.UPDATE_AUTO_VALIDATE_TO_DR);
				 //System.out.println("heyyyyyy");
	        	autoapprove = dbUtility.executeUpdate(new String[]{formDTO2.getGuideLineID()});
	        	
	        	if(autoapprove)
	        	{
	        		formDTO2.setDownlaodChkId("Y");
	        		logger.debug("COLONIES AUTO APPROVED FOR DR");
	        	}
	        	
	        	else
	        	{
	        		throw new Exception("update failed");
	        	}
	        }
	        else
	        {
	        	throw new Exception("update failed");
	        }
			 //System.out.println(list);
			 //System.out.println("just printed query");
		} catch (Exception e) {
			//System.out.println("Exception in getFServices():" + e);
		}finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				
				}
			}catch(Exception x) {
				
				logger.debug(x);
				x.printStackTrace();
				throw x;
				//System.out.println(x);
			}
		}
		//System.out.println("returning value");
	return flag;
	}

	public boolean getUpdateSubmitSRAll(GuidelineDTO formDTO2)throws Exception {
		boolean flag=false;
		 
		try {
			dbUtility = new DBUtility();
	
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtility.createPreparedStatement(CommonSQL.UPDATE_AUTO_APPROVE_MODIFY_TO_SR);
			 //System.out.println("heyyyyyy");
	        flag = dbUtility.executeUpdate(new String[]{formDTO2.getGuideLineID()});
	        
	        if(flag)
	        {
	        	
	        	logger.debug("All colonies modification approved");
	        }
	        else
	        {
	        	throw new Exception("All colonies modification failed");
	        }
			 //System.out.println(list);
			 //System.out.println("just printed query");
		} catch (Exception e) {
			//System.out.println("Exception in getFServices():" + e);
		}finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				
				}
			}catch(Exception x) {
				
				logger.debug(x);
				x.printStackTrace();
				throw x;
				//System.out.println(x);
			}
		}
		//System.out.println("returning value");
	return flag;
	}

	public String getUserType(GuidelineDTO formDTO2) throws Exception {
		String type="";
		try {
			dbUtility = new DBUtility();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String arr1[]=new String[1];
        //System.out.println("1");
        arr1[0]= formDTO2.getLoggedOfficeId();
        
        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
		*/
        try {
			dbUtility.createPreparedStatement(CommonSQL.CHECK_OFFICE_TYPE);
			type = dbUtility.executeQry(arr1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				
				}
			}catch(Exception x) {
				
				logger.debug(x);
				x.printStackTrace();
				throw x;
				//System.out.println(x);
			}
		}
		return type;
	}

	public String getGuidelineparentList(GuidelineDTO formDTO2, String finanDistt, String fromDate, String toDate) throws Exception {
		String count="";
		String distid="";
		try {
			dbUtility = new DBUtility();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String arr1[]=new String[1];
        //System.out.println("1");
        arr1[0]= formDTO2.getLoggedOfficeId();
        
        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
		*/
        try {
        	dbUtility.createPreparedStatement(CommonSQL.GET_DISTRICT_DETLS);
        	distid = dbUtility.executeQry(arr1);
        	
        	
        	if(distid.equalsIgnoreCase("") || distid==null)
        	{
        		count="0";
        	}
        	
        	else
        	{
        		dbUtility.createPreparedStatement(CommonSQL.GET_ID_PARENT_DETLS);
    			count = dbUtility.executeQry(new String[]{distid,finanDistt,fromDate,toDate});
        	}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				
				}
			}catch(Exception x) {
				
				logger.debug(x);
				x.printStackTrace();
				throw x;
				//System.out.println(x);
			}
		}
		return count;
	}

	public boolean deleteDeacCombinations(String guidelineID, String distId) {
		boolean flag1=false;
		boolean flag2=false;

		try {
			
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/String arr1[]=new String[1];
	        //System.out.println("1");
			arr1[0]=guidelineID;
	        
	        dbUtility.createPreparedStatement(CommonSQL.CHILD2_DELETE_DEAC_COMBINATIONS);
			 //System.out.println("heyyyyyy");
	        flag2 = dbUtility.executeUpdate(arr1);
	        
	        dbUtility.createPreparedStatement(CommonSQL.CHILD1_DELETE_DEAC_COMBINATIONS);
			 //System.out.println("heyyyyyy");
	        flag1 = dbUtility.executeUpdate(arr1);
	        
	        
	        
	       
	        
	     
	        
	        if(flag1&&flag2)
	        {
	        	dbUtility.commit();
	        	logger.debug("All colonies deleted successfully");
	        }
	        else
	        {
	        	dbUtility.rollback();
	        	logger.debug("All colonies deleted successfully");
	        }
			 //System.out.println(list);
			 //System.out.println("just printed query");
		} catch (Exception e) {
			//System.out.println("Exception in getFServices():" + e);
		}finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				
				}
			}catch(Exception x) {
				
				logger.debug(x);
				x.printStackTrace();
				
				//System.out.println(x);
			}
		}
		//System.out.println("returning value");
	return flag1;
	}

	public boolean insertNewCombinations(String guidelineID, String distId) {
		boolean dataInserted = false;
		try{	
			
			dbUtility = new DBUtility();
			clstmt = dbUtility.createCallableStatement(CommonSQL.INSERT_NEW_COMBINATIONS);
					try
						{
							
							clstmt.setString(1, guidelineID);
							clstmt.setString(2, distId);
			                
			                try {
				                	if(!clstmt.execute())
				                	{
				                		dataInserted = true;
				                	}                                                                                      
				            } catch (StringIndexOutOfBoundsException e) {
				                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
				            }
				            logger.debug("Wipro : after execute update");
				           
				        } catch (SQLException sqle) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               sqle.getMessage());
				           
				            throw sqle;
				        } catch (NumberFormatException nfe) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               nfe.getMessage());
				            throw nfe;
				        } catch (IllegalArgumentException iae) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               iae.getMessage());
				            throw iae;
				        } catch (StringIndexOutOfBoundsException sioobe) {
				            logger.debug("DAO - executeUpdate(String[]): " +
				                               sioobe.getMessage());
				            throw sioobe;
				        }
		       
				} catch (Exception x) {
					logger.debug("ERROR:-   "+x.getMessage());
					x.printStackTrace();
				}finally {
					try
					{
						dbUtility.closeConnection();
					}
					catch (Exception xe ) {
					logger.debug("Close Connection Error:-"+xe);
					xe.printStackTrace();
					}
				}
				return dataInserted;
	}

	public ArrayList getMohallaListViewall(String language,
			GuidelineDTO formDTO2) {
		logger.debug("DAO: Called: getIndividualMohallaDetails2()");

		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String lang = language;
		try {

			//st =
				// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			 try {
				
				 dbUtility = new DBUtility();
					dbUtility.createStatement();
					String sql =CommonSQL.GET_NEW_COMBINATIONS;
					String temp[] = new String[1];
					
					
					if(formDTO2.getGuideLineID().equalsIgnoreCase("") || formDTO2.getGuideLineID()==null)
					{
						temp[0]="";
					}
					else
					{
						temp[0]=formDTO2.getGuideLineID();
					}
					
					dbUtility.createPreparedStatement(sql);
					list2=dbUtility.executeQuery(temp);
				 
				 	
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		           logger.debug("DAO- executeQuery(String): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO- executeQuery(String): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        logger.debug(list2.size());
		        
		      for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohallaDetails = new GuidelineDTO();
				
				if(lang.equalsIgnoreCase("en"))
				{
					mohallaDetails.setNewTehsilName((String)list1.get(0));
				}
				else
				{
					mohallaDetails.setNewTehsilName((String)list1.get(1));
				}
				
				
				if(lang.equalsIgnoreCase("en"))
				{
					mohallaDetails.setNewAreaName((String)list1.get(2));
				}
				else
				{
					mohallaDetails.setNewAreaName((String)list1.get(3));
				}
				
				
				if(lang.equalsIgnoreCase("en"))
				{
					mohallaDetails.setNewSubAreaName((String)list1.get(4));
				}
				else
				{
					mohallaDetails.setNewSubAreaName((String)list1.get(5));
				}
				
				if(lang.equalsIgnoreCase("en"))
				{
					mohallaDetails.setNewWardName((String)list1.get(6));
				}
				else
				{
					mohallaDetails.setNewWardName((String)list1.get(7));
				}

				if(lang.equalsIgnoreCase("en"))
				{
					mohallaDetails.setNewMohallaName((String)list1.get(8));
				}
				else
				{
					mohallaDetails.setNewMohallaName((String)list1.get(9));
				}
				
				mohallaDetails.setNewMohallaID((String)list1.get(10));
				

				mohallaDetailsList.add(mohallaDetails);
				logger.debug(mohallaDetailsList.size());
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
		return mohallaDetailsList;
}

	public boolean insertTemplateData(String distId, GuidelineDTO formDTO2) {
		logger.debug("DAO: Called: insertTemplateData()");

			Boolean flag=false;
			Boolean flag1=false;
			Boolean flag3=false;
			String cnt="";
		try {
			
			
			//st =
				// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			 try {
				
				 dbUtility = new DBUtility();
				 dbUtility.setAutoCommit(false);
					dbUtility.createStatement();
					String sql1 =CommonSQL.CHECK_OLD_COMBINATION;
					String temp2[] = new String[1];
					temp2[0]=distId;
					dbUtility.createPreparedStatement(sql1);
					cnt=dbUtility.executeQry(temp2);
				 
					if(Integer.parseInt(cnt)>0)
					{
						dbUtility = new DBUtility();
							dbUtility.createStatement();
							String sql =CommonSQL.DELETE_OLD_COMBINATION;
							String temp[] = new String[1];
							temp[0]=distId;
							
							
							
							dbUtility.createPreparedStatement(sql);
							flag=dbUtility.executeUpdate(temp);
							if(flag)
							{
								dbUtility.commit();
							}
					}
					
					else
					{
						flag=true;
					}
				 
				 
					
					dbUtility = new DBUtility();
					dbUtility.createStatement();
					String sql2 =CommonSQL.INSERT_NEW_TEMPLATE_COMBINATIONS;
					String temp1[] = new String[1];
					temp1[0]=distId;
					
					
					
					dbUtility.createPreparedStatement(sql2);
					flag1=dbUtility.executeUpdate(temp1);
				 
					if(flag & flag1)
					{
						flag3=true;
						dbUtility.commit();
					}
					else
					{
						flag3=false;
						dbUtility.rollback();
					}
				 	
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		           logger.debug("DAO- executeQuery(String): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO- executeQuery(String): " +
		                               iae.getMessage());
		            throw iae;
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
		return flag3;
	}

	public boolean getinsertDataForPrint(String guideId, GuidelineDTO formDTO2) {
		logger.debug("DAO: Called: getinsertDataForPrint()");
		Boolean flag=false;
		Boolean flag1=false;
		Boolean flag3=false;
		Boolean flag4=false;
		String cnt="";
	try {
		
		
		//st =
			// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		 try {
			
			 dbUtility = new DBUtility();
			 dbUtility.setAutoCommit(false);
				dbUtility.createStatement();
				String sql1 =CommonSQL.CHECK_OLD_PRINT_COMBINATION;
				String temp2[] = new String[1];
				temp2[0]=guideId;
				dbUtility.createPreparedStatement(sql1);
				cnt=dbUtility.executeQry(temp2);
			 
				if(Integer.parseInt(cnt)>0)
				{
					dbUtility = new DBUtility();
					 dbUtility.setAutoCommit(false);
						dbUtility.createStatement();
						String sql =CommonSQL.DELETE_OLD_PRINT_COMBINATION;
						String temp[] = new String[1];
						temp[0]=guideId;
						
						
						
						dbUtility.createPreparedStatement(sql);
						flag=dbUtility.executeUpdate(temp);
				}
				
				else
				{
					flag=true;
				}
			 
				clstmt = dbUtility.createCallableStatement(CommonSQL.INSERT_NEW_PRINT_COMBINATIONS);
				clstmt.setString(1, guideId);
				clstmt.setString(2, "40");
				clstmt.setString(3, formDTO2.getDistrictID());
                
               
	                	if(!clstmt.execute())
	                	{
	                		flag1 = true;
	                		
	                		dbUtility = new DBUtility();
	   						dbUtility.createStatement();
	   						String sql4 =CommonSQL.UPDATE_GUIDELINE_PRINT_STATUS;
	   						String temp[] = new String[1];
	   						temp[0]=guideId;
	   						
	   						
	   						
	   						dbUtility.createPreparedStatement(sql4);
	   						flag4=dbUtility.executeUpdate(temp);
	   						if(flag4)
	   						{
	   							logger.debug("Print status updated successfully");
	   						}
	   						
	   						else
	   						{
	   							logger.debug("Print status updation failed");
	   						}
	                		
	                		
	                	}                                                
				
				
			 
				if(flag & flag1)
				{
					flag3=true;
					dbUtility.commit();
				}
				else
				{
					flag3=false;
					dbUtility.rollback();
				}
			 	
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeQuery(String): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	           logger.debug("DAO- executeQuery(String): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO- executeQuery(String): " +
	                               iae.getMessage());
	            throw iae;
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
	return flag3;
		
	}

	public boolean checkForAlreadySubmitted(String guideLineID,
			GuidelineDTO gdto) throws Exception {
		boolean flag=false;
		 try {
				
			
			 dbUtility = new DBUtility();
				
				String sql =CommonSQL.CHECK_ALREADY_SUBMMITTED_COLONY;
				 String colonyArr[] = gdto.getMohallaID().split("~");
				String temp[] = new String[2];
				temp[0]=guideLineID;
				temp[1]=colonyArr[0];
				dbUtility.createPreparedStatement(sql);
				String cnt=dbUtility.executeQry(temp);
			 if(Integer.parseInt(cnt)>0)
			 {
				 flag=true;
			 }
			 else
			 {
				 flag=false;
			 }
			 	
	        } catch (SQLException sqle) {
	            logger.debug("DAO - executeQuery(String): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	           logger.debug("DAO- executeQuery(String): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO- executeQuery(String): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        catch (Exception x) {
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
	        return flag;
	}

	public ArrayList getconstructRateList(String appliclause) {
		logger.debug("DAO: Called: getIndividualMohallaDetails2()");

		ArrayList constructRateList = new ArrayList();
		try {

			
			 try {
				
				 dbUtility = new DBUtility();
					dbUtility.createStatement();
					String sql =CommonSQL.GET_CONSTRUCTION_RATES;
					String temp[] = new String[1];
					temp[0]=appliclause;
					
					dbUtility.createPreparedStatement(sql);
					constructRateList=dbUtility.executeQuery(temp);
				 
				 	
		        } catch (SQLException sqle) {
		            logger.debug("DAO - executeQuery(String): " +
		                               sqle.getMessage());
		            throw sqle;
		        } catch (NumberFormatException nfe) {
		           logger.debug("DAO- executeQuery(String): " +
		                               nfe.getMessage());
		            throw nfe;
		        } catch (IllegalArgumentException iae) {
		            logger.debug("DAO- executeQuery(String): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        constructRateList.trimToSize();
		        logger.debug(constructRateList.size());
		  
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
		return constructRateList;
	}
}
