
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
 * FILE NAME: GuideLineViewDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    6th MAY 2008 
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE APPROVAL ACTION.
 */

package com.wipro.igrs.guideline.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DBUtilityTest;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;
import com.wipro.igrs.guideline.sql.CommonSQL;
import com.wipro.igrs.util.GUIDGenerator;

/**
 * @author NIHAR M.
 *
 */

/**
 * Modified by SIMRAN                                               
 */

public class GuideLineViewDAO {


	Connection con;
	DBUtility dbUtil;
	Connection connTest;
	
	 	Statement st = null;
	    PreparedStatement pst = null;
	    ResultSet rst = null;
	    CallableStatement clstmt = null;
	    CallableStatement oclstmt = null;
	     
		private CallableStatement callstmt  ;
		private Logger logger = (Logger) Logger
		.getLogger(GuideLineViewDAO.class);

	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/mm/yyyy");

	/**
	 * @return ArrayList that holds District List that are active in master table
	 */
	public ArrayList getDistrictList(String lang) {
		GuidelineDTO  district ;
		ArrayList districtList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		try {
			
			connTest = new DBUtility().getDBConnection();
		//	st =
				 //connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			 try {
				 if(lang.equalsIgnoreCase("hi"))
				 {
					 pst = connTest.prepareStatement(CommonSQL.DISTRICT_HI);
				 }
				 else
				 {
					 pst = connTest.prepareStatement(CommonSQL.DISTRICT);
				 }
				  
		            rst = pst.executeQuery();
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
		                list2.add(row_list);
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
		
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				district = new GuidelineDTO();

				district.setDistrictID((String)list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					district.setDistrict((String)list1.get(1));
					district.setHdnDistrict((String)list1.get(0)+"~"+(String)list1.get(1));
				}
				else
				{
					district.setDistrict((String)list1.get(2));
					district.setHdnDistrict((String)list1.get(0)+"~"+(String)list1.get(2));
				}
				
				districtList.add(district);
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
		return districtList;
	}


	/**
	 * 
	 * @param tehsilid
	 * 
	 * @return  ArrayList that holds list of wards 
	 */
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


	/**
	 * 
	 * @param tehsilid
	 * 
	 * @return  ArrayList that holds list of Patwari halkas 
	 */
	public ArrayList getPatwariList(String tehsilid){

		GuidelineDTO  patwari;
		ArrayList patwariList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
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


	/**
	 * @return ArrayList that holds list of Area Types(Urban/rural etc)
	 */
	public ArrayList getAreasTypeList(){

		GuidelineDTO  areaType ;
		ArrayList areaList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			
			//list2 = dbUtil.executeQuery(CommonSQL.AREA_TYPE);
			connTest = DBUtilityTest.openConnection();
			//st =
				 //connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			
			try {
				pst = connTest.prepareStatement(CommonSQL.AREA_TYPE);
	            rst = pst.executeQuery();
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
	                list2.add(row_list);
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
	            logger.debug("DAO - executeQuery(String): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        list2.trimToSize();
	        
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				areaType = new GuidelineDTO();

				areaType.setAreaTypeID((String)list1.get(0));
				areaType.setAreaName((String)list1.get(1));

				areaType.setHdnArea((String)list1.get(0)+"~"+(String)list1.get(1));
				areaList.add(areaType);
			}
		} catch (Exception x) {
			logger.debug(x);

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
		return areaList;
	}

	
	
	/**
	 * 
	 * @param distId
	 * 
	 * @return ArrayList that holds list of tehsils 
	 */
	public ArrayList getTehsilList(String distid, String lang) {
		GuidelineDTO  tehsil ;
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			
			//list2 = dbUtil.executeQuery(distid);
			
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
				if(lang.equalsIgnoreCase("en")){
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


	/**
	 * 
	 * @param wardId
	 * 
	 * @return  ArrayList that holds list of mohallas/villages 
	 */
	public ArrayList getMohViewList(String wardId, String lang){
		GuidelineDTO  mohalla ;
		ArrayList mohList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		String sql= CommonSQL.GET_MOHALLAS_DRAFT_VIEW;

		try {
			connTest = DBUtilityTest.openConnection();
			pst = connTest.prepareStatement(CommonSQL.GET_MOHALLAS_DRAFT_VIEW);
			
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
			//list2 = dbUtil.executeQuery(fyear);
		        for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohalla = new GuidelineDTO();

				mohalla.setMohallaID((String)list1.get(0));
				logger.debug("mohalla id:-  "+(String)list1.get(0));
				if(lang.equalsIgnoreCase("en"))
				{
					mohalla.setMohalla((String)list1.get(1));
					logger.debug("mohalla name:-  "+(String)list1.get(1));
					mohalla.setHdnMohalla((String)list1.get(0)+"="+(String)list1.get(1));
				}
				else
				{
					mohalla.setMohalla((String)list1.get(2));
					logger.debug("mohalla name:-  "+(String)list1.get(2));
					mohalla.setHdnMohalla((String)list1.get(0)+"="+(String)list1.get(2));
				}
				
			//	mohalla.setFinancialYear((String)list1.get(2));

				
				mohList.add(mohalla);
			}

		} catch (Exception x) {
			logger.debug(x);
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


/////////BELOW CODE COMMENTED BY SIMRAN///////////////////////////////////////////////////////

	/**
	 * This function is used for to fetch the individual mohalla list details by taking the parameters as
	 * mohalla id, base period from and base period to.
	 * 
	 * @param param
	 * @return ArrayList mohallaDetailsList
	 */
	/*public ArrayList getIndividualMohallaDraftView(String[] param, int guideStatus) {

		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
				//dbUtil = new DBUtility();
			//logger.debug("<-----------Inside DAO-------------->");
			//dbUtil.createPreparedStatement(CommonSQL.MOHALLA_DRAFT_VIEW);
			connTest = DBUtilityTest.openConnection();
			pst = connTest.prepareStatement(CommonSQL.MOHALLA_DRAFT_VIEW);
			
			

			//list2 = dbUtil.executeQuery(param);
			
			try {
	           
	                pst.setString( 1, param[2]);
	                pst.setString( 2, param[1]);
	                pst.setInt( 3, guideStatus);
	              
	                pst.setString( 4, param[2]);
	                pst.setString( 5, param[1]);
	                pst.setInt( 6, guideStatus);
	                pst.setString(7, param[0]);
	                
	            
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
	            logger.debug("DAO  - executeQuery(String[]): " +
	                               sqle.getMessage());
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	            logger.debug("DAO  - executeQuery(String[]): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO  - executeQuery(String[]): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        list2.trimToSize();
	        
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohallaDetails = new GuidelineDTO();
				

				mohallaDetails.setPropertyTypeName((String)list1.get(0));
				mohallaDetails.setL1Name((String)list1.get(1));
				mohallaDetails.setL2Name((String)list1.get(2));
				mohallaDetails.setGuideLineValue((String)list1.get(3));
				//mohallaDetails.setAverageValue((String)list1.get(4));
				//mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
				mohallaDetails.setMohallaID((String)list1.get(4));
				logger.debug("mohalla id:-   "+(String)list1.get(4));
				logger.debug("mohalla:-   "+(String)list1.get(5));
				mohallaDetails.setMohalla((String)list1.get(5));
				mohallaDetails.setUomName((String)list1.get(10));

				mohallaDetailsList.add(mohallaDetails);
			}
		} catch (Exception x) {
			logger.debug("error---->  "+x);

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
	}/*



	/**
	 * This function is used for to fetch the individual mohalla list details by taking the parameters as
	 * mohalla id, base period from and base period to.
	 * This is used for the Approval Process.
	 * 
	 * @param param
	 * @return ArrayList mohallaDetailsList
	 */
/*	public ArrayList getIndividualVillageDraftView(String[] param) {

		GuidelineDTO  villageDetails ;
		ArrayList villageDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			connTest = DBUtilityTest.openConnection();
			pst = connTest.prepareStatement(CommonSQL.VILLAGE_DRAFT_VIEW);
			logger.debug(CommonSQL.VILLAGE_DRAFT_VIEW);

			//list2 = dbUtil.executeQuery(param);
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
	            logger.debug("DAO- executeQuery(String[]): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO- executeQuery(String[]): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        list2.trimToSize();
	        
	        
			if(list2.size()!=0){
				logger.debug("List2 is here:-   "+list2);
			}
			else{
				logger.debug("List2 size is 0");
			}
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				villageDetails = new GuidelineDTO();

				villageDetails.setPropertyTypeName((String)list1.get(0));
				villageDetails.setL1Name((String)list1.get(1));
				villageDetails.setL2Name((String)list1.get(2));
				villageDetails.setGuideLineValue((String)list1.get(3));
				villageDetails.setAverageValue((String)list1.get(4));
				villageDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
				villageDetails.setMohallaID((String)list1.get(6));
				logger.debug("mohalla id:-   "+(String)list1.get(6));
				logger.debug("mohalla:-   "+(String)list1.get(7));
				villageDetails.setMohalla((String)list1.get(7));

				villageDetailsList.add(villageDetails);
			}
		} catch (Exception x) {
			logger.debug(""+x);

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
		return villageDetailsList;
	}*/




	/**
	 * This function is used for to fetch the individual mohalla list details by taking the parameters as
	 * mohalla id, base period from and base period to.
	 * 
	 * @param param
	 * @return ArrayList mohallaDetailsList
	 */
	/*public ArrayList getIndividualMohallaFinalView(String[] param) {

		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			
			logger.debug(CommonSQL.MOHALLA_FINAL_VIEW);
			
			connTest = DBUtilityTest.openConnection();
			pst = connTest.prepareStatement(CommonSQL.MOHALLA_FINAL_VIEW);

			//list2 = dbUtil.executeQuery(param);
			
			try {
		           
                pst.setString( 1, param[0]);
                pst.setString( 2, param[1]);
               // pst.setInt( 3, guideStatus);
            
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
            logger.debug("DAO  - executeQuery(String[]): " +
                               sqle.getMessage());
            throw sqle;
        } catch (NumberFormatException nfe) {
            logger.debug("DAO  - executeQuery(String[]): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DAO  - executeQuery(String[]): " +
                               iae.getMessage());
            throw iae;
        }
        list2.trimToSize();
        
			if(list2.size()!=0){
				System.out.println("List2 is here:-   "+list2);
			}
			else{
				logger.debug("List2 size is 0");
			}
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohallaDetails = new GuidelineDTO();

				mohallaDetails.setPropertyTypeName((String)list1.get(0));
				mohallaDetails.setL1Name((String)list1.get(1));
				mohallaDetails.setL2Name((String)list1.get(2));
				mohallaDetails.setGuideLineValue((String)list1.get(3));
				//mohallaDetails.setAverageValue((String)list1.get(4));
				//mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
				mohallaDetails.setMohallaID((String)list1.get(4));
				logger.debug("mohalla id:-   "+(String)list1.get(4));
				logger.debug("mohalla:-   "+(String)list1.get(5));
				mohallaDetails.setMohalla((String)list1.get(5));
				mohallaDetails.setUomName((String)list1.get(10));


				mohallaDetailsList.add(mohallaDetails);
			}
		} catch (Exception x) {
			logger.debug(""+x);

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
	 * This function is used for to fetch the individual mohalla list details by taking the parameters as
	 * mohalla id, base period from and base period to.
	 * This is used for the Approval Process.
	 * 
	 * @param param
	 * @return ArrayList mohallaDetailsList
	 */
	/*public ArrayList getIndividualVillageFinalView(String[] param) {

		GuidelineDTO  villageDetails ;
		ArrayList villageDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			
			logger.debug(CommonSQL.VILLAGE_FINAL_VIEW);
			
			connTest = DBUtilityTest.openConnection();
			pst = connTest.prepareStatement(CommonSQL.VILLAGE_FINAL_VIEW);

			//list2 = dbUtil.executeQuery(param);
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
			
			
			if(list2.size()!=0){
				logger.debug("List2 is here:-   "+list2);
			}
			else{
				logger.debug("List2 size is 0");
			}
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				villageDetails = new GuidelineDTO();

				villageDetails.setPropertyTypeName((String)list1.get(0));
				villageDetails.setL1Name((String)list1.get(1));
				villageDetails.setL2Name((String)list1.get(2));
				villageDetails.setGuideLineValue((String)list1.get(3));
				villageDetails.setAverageValue((String)list1.get(4));
				villageDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
				villageDetails.setMohallaID((String)list1.get(6));
				logger.debug("mohalla id:-   "+(String)list1.get(6));
				logger.debug("mohalla:-   "+(String)list1.get(7));
				villageDetails.setMohalla((String)list1.get(7));

				villageDetailsList.add(villageDetails);
			}
		} catch (Exception x) {
			logger.debug(""+x);

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
		return villageDetailsList;
	}
	*/
	
	/*
	 * ADDED BY SIMRAN
	 */

	
	/*public ArrayList getFinancialYearList(){
		
		GuidelineDTO financialYear ;
		ArrayList financialYearList = new ArrayList();
		
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createStatement();
			
			connTest = DBUtilityTest.openConnection();
			st =
				 connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			try {
	            rst = st.executeQuery(CommonSQL.FINANCIAL);
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
	                list2.add(row_list);
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
	        list2.trimToSize();
			
			//list2 = dbUtil.executeQuery(CommonSQL.FINANCIAL_VIEW);
			
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
				
				rst.close();
				st.close();
				DBUtilityTest.closeConnection();
			} catch (Exception ex) {

				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
		return financialYearList;
	}
	
	public ArrayList getIndividualMohallaPendingView(String[] param,int guideStatus) {

		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			dbUtil = new DBUtility();
			logger.debug("<-----------Inside DAO Pending-------------->");
			dbUtil.createPreparedStatement(CommonSQL.MOHALLA_PENDING_VIEW);
			
			connTest = DBUtilityTest.openConnection();
			pst = connTest.prepareStatement(CommonSQL.MOHALLA_PENDING_VIEW);

			//list2 = dbUtil.executeQuery(param);
			
			try {
		           
                pst.setString( 1, param[0]);
                pst.setString( 2, param[1]);
                pst.setInt( 3, guideStatus);
            
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
            logger.debug("DAO  - executeQuery(String[]): " +
                               sqle.getMessage());
            throw sqle;
        } catch (NumberFormatException nfe) {
            logger.debug("DAO  - executeQuery(String[]): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DAO  - executeQuery(String[]): " +
                               iae.getMessage());
            throw iae;
        }
        list2.trimToSize();
        
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				mohallaDetails = new GuidelineDTO();
				

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
				mohallaDetails.setUomName((String)list1.get(8));

				mohallaDetailsList.add(mohallaDetails);
			}
		} catch (Exception x) {
			logger.debug("error---->  "+x);

		}finally {
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
	}*/
	
/**
 * 
 * @return ArrayList that holds list of financial Years
 */
public ArrayList getActiveFinancialYearList(){
		
		GuidelineDTO financialYear ;
		ArrayList financialYearList = new ArrayList();
		
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		try {
			connTest = DBUtilityTest.openConnection();
			//st =
				// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			//list2 = dbUtil.executeQuery(CommonSQL.ACTIVE_FINANCIAL_VIEW);
			
			try {
				pst = connTest.prepareStatement(CommonSQL.ACTIVE_FINANCIAL_VIEW);
	            rst = pst.executeQuery();
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
	                list2.add(row_list);
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
				
				rst.close();
				pst.close();
				DBUtilityTest.closeConnection();
			} catch (Exception ex) {

				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
		return financialYearList;
	}


public ArrayList viewVersionsTemp(GuidelineDTO gDTO)
{
	GuidelineDTO versions ;
	ArrayList versionList = new ArrayList();
	ArrayList list1 = new ArrayList();
	ArrayList list2 = new ArrayList();

	try {
	
			
			//dbUtil.createPreparedStatement(CommonSQL.WARD);
			//list2 = dbUtil.executeQuery(tehsilid);
				connTest = DBUtilityTest.openConnection();
				
				pst = connTest.prepareStatement(CommonSQL.VIEW_VERSION_TEMP_DRAFT);
				
				try
				{	
				
		                pst.setString(1, gDTO.getFinancialYear());
		                pst.setString(2, gDTO.getDistrictID());
		                pst.setInt(3, gDTO.getGuideStatus());
		                pst.setInt(4, gDTO.getGuideStatus());
		                
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
			versions = new GuidelineDTO();
	
			versions.setGuideLineID((String)list1.get(0));
			versions.setFromDepositeDate((String)list1.get(1));
			versions.setToDepositeDate((String)list1.get(2));
			
			versionList.add(versions);
			//logger.debug(""+ward);
			//wardList.add(ward);
		}
	} catch (Exception x) {
		logger.debug(x);

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
	return versionList;
}


/**
 * 
 * @param gDTO
 * @param type
 * @return ArrayList that holds list of available versions on the basis of selected district,
 * Financial Year and approval Type(Final/Draft)
 */
public ArrayList viewVersionsTemp(GuidelineDTO gDTO, String type)
{
	GuidelineDTO versions ;
	ArrayList versionList = new ArrayList();
	ArrayList list1 = new ArrayList();
	ArrayList list2 = new ArrayList();

	try {
	
			
			//dbUtil.createPreparedStatement(CommonSQL.WARD);
			//list2 = dbUtil.executeQuery(tehsilid);
				connTest = DBUtilityTest.openConnection();
				
				if(type.equalsIgnoreCase("temp"))
				{
					pst = connTest.prepareStatement(CommonSQL.VIEW_VERSION_TEMP_DRAFT);
				}
				else
				{
					pst = connTest.prepareStatement(CommonSQL.VIEW_VERSION_FINAL);
				}
				try
				{	
					if(type.equalsIgnoreCase("temp"))
					{
		                pst.setString(1, gDTO.getFinancialYear());
		                pst.setString(2, gDTO.getDistrictID());
		                pst.setInt(3, gDTO.getGuideStatus());
		                pst.setInt(4, gDTO.getGuideStatus());
					}
					else
					{
						 pst.setString(1, gDTO.getFinancialYear());
			             pst.setString(2, gDTO.getDistrictID());
			             pst.setInt(3, gDTO.getGuideStatus());
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
			versions = new GuidelineDTO();
	
			versions.setGuideLineID((String)list1.get(0));
			String fDate = list1.get(1).toString().substring(0,10);
			String tDate = list1.get(2).toString().substring(0,10);
			logger.debug("$$$$$$$$$$$$$$$$$"+fDate+"$$$$"+tDate);
			Date dFrom = sdf1.parse(fDate);
			logger.debug("DATE"+dFrom);
			
			String dF = sdf2.format(dFrom);
			logger.debug("DATE"+dF);
			
			Date dTo = sdf1.parse(tDate);
			logger.debug("DATE"+dTo);
			
			String dT = sdf2.format(dTo);
			logger.debug("DATE"+dT);;
			
			versions.setFromDepositeDate(dF);
			versions.setToDepositeDate(dT);
			versions.setHdnGuideDuration((String)list1.get(0)+"~"+dF+"~"+dT);
			
			versionList.add(versions);
			//logger.debug(""+ward);
			//wardList.add(ward);
		}
	} catch (Exception x) {
		logger.debug(x);

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
	return versionList;
}


/**
 * 
 * @param gDTO
 * @return ArrayList that returns Guideline Data for selected mohalla in selected guideline
 */
public ArrayList getIndividualMohallaViewNew(GuidelineDTO gDTO)
{
	GuidelineDTO  mohallaDetails ;
	ArrayList mohallaDetailsList = new ArrayList();
	ArrayList list1 = new ArrayList();
	ArrayList list2 = new ArrayList();
	String ward = null;
	try {
		
		logger.debug("<-----------Inside DAO Pending-------------->");
		
		
		connTest = DBUtilityTest.openConnection();
		
		if(gDTO.getGuideType().equalsIgnoreCase("F"))
		{
			pst = connTest.prepareStatement(CommonSQL.GUIDELINE_VIEW_FINAL);
		}
		else
		{
			pst = connTest.prepareStatement(CommonSQL.GUIDELINE_VIEW_TEMP);
		}
		

		//list2 = dbUtil.executeQuery(param);
		
		String patID = gDTO.getPatwariID();
		String warID = gDTO.getWardID();
		
		if(patID != null && patID.length() > 0)
		{
			 ward = gDTO.getPatwariID();
		}
		else
			 ward = gDTO.getWardID();
		
		try {
	           
            pst.setString( 1, gDTO.getGuideLineID());
            pst.setString( 2, gDTO.getTehsilID());
            pst.setString( 3, ward);
            pst.setString(4, gDTO.getMohallaID());
            
        
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
        logger.debug("DAO  - executeQuery(String[]): " +
                           sqle.getMessage());
        throw sqle;
    } catch (NumberFormatException nfe) {
        logger.debug("DAO  - executeQuery(String[]): " +
                           nfe.getMessage());
        throw nfe;
    } catch (IllegalArgumentException iae) {
        logger.debug("DAO  - executeQuery(String[]): " +
                           iae.getMessage());
        throw iae;
    }
    list2.trimToSize();
    
		for (int i = 0; i < list2.size(); i++) {
			list1 = (ArrayList)list2.get(i);
			mohallaDetails = new GuidelineDTO();
			
			mohallaDetails.setPropertyID((String)list1.get(0));
			mohallaDetails.setPropertyTypeName((String)list1.get(1));
			mohallaDetails.setL1_ID((String)list1.get(2));
			mohallaDetails.setL1Name((String)list1.get(3));
			mohallaDetails.setL2_ID((String)list1.get(4));
			mohallaDetails.setL2Name((String)list1.get(5));
			mohallaDetails.setGuideLineValue((String)list1.get(6));
			//mohallaDetails.setAverageValue((String)list1.get(4));
			//mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
			mohallaDetails.setMohallaID((String)list1.get(7));
			logger.debug("mohalla id:-   "+(String)list1.get(7));
			logger.debug("mohalla:-   "+(String)list1.get(8));
			mohallaDetails.setMohalla((String)list1.get(8));
			mohallaDetails.setUomName((String)list1.get(9));
			mohallaDetails.setUomID((String)list1.get(10));
			mohallaDetailsList.add(mohallaDetails);
		}
	} catch (Exception x) {
		logger.debug("error---->  "+x);

	}finally {
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

/**
 * 
 * @param gDTO
 *  @return ArrayList that holds list of subClauses on the basis of property types and mohalla
 */
public ArrayList subClauseReadOnly (GuidelineDTO gDTO)
{
	String propId = null;
	String propL1Id = null;
	String propL2Id = null;
	
	//String list1 = null;
	//ArrayList list1 = new ArrayList();
	GuidelineDTO sub;
	ArrayList subClauseList = new ArrayList();
	ArrayList list2 = new ArrayList();
	ArrayList list1 = new ArrayList();
	String ward =null;
	
	try
	{
		connTest = DBUtilityTest.openConnection();
		
		String patID = gDTO.getPatwariID();
		String warID = gDTO.getWardID();
		
		if(patID != null && patID.length() > 0)
		{
			 ward = gDTO.getPatwariID();
		}
		else
			 ward = gDTO.getWardID();
		
		if(gDTO.getGuideType().equalsIgnoreCase("F"))
		{
			pst = connTest.prepareStatement(CommonSQL.SUB_VIEW_FINAL);
			pst.setString(1, gDTO.getDistrictID());
			pst.setString(2, gDTO.getTehsilID());
			pst.setString(3, ward);
			pst.setString(4, gDTO.getMohallaID());
			pst.setString(5,gDTO.getPropertyID());
			pst.setString(6,gDTO.getL1_ID());
			pst.setString(7,gDTO.getL2_ID());
			
		}
		else
		{
			pst = connTest.prepareStatement(CommonSQL.SUB_VIEW_APP);
			pst.setString(1, gDTO.getGuideLineID());
			pst.setString(2, gDTO.getTehsilID());
			pst.setString(3, ward);
			pst.setString(4, gDTO.getMohallaID());
			pst.setString(5,gDTO.getPropertyID());
			pst.setString(6,gDTO.getL1_ID());
			pst.setString(7,gDTO.getL2_ID());
		}
			try
					{
						
						
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
				sub = new GuidelineDTO();
				sub.setSubClauseId((String)list1.get(0));
				sub.setSubName((String)list1.get(1));
				sub.setSubClauseName((String)list1.get(2));
				sub.setStatus("Y");


				logger.debug(""+sub);
				subClauseList.add(sub);
			}

		} catch (Exception x) {
			logger.debug(x);

		} finally {
			try {
					rst.close();
					pst.close();
					DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);

			}
		}
		return subClauseList;
}

///////////////////////////BELOW METHODS FOR DISTRICT WISE VIEW OF GUIDELINE//////////////////////////
DBUtility dbUtility = null;

/**
 * 
 * @param glDTO
 * @return HashMap that holds data for the whole District
 * @throws Exception
 */
public HashMap getGuidelineData (GuidelineDTO glDTO, String lang)throws Exception  {
	HashMap  guidelineData = new HashMap();
	try {
			String disttName = glDTO.getDistrict();
			ArrayList tehsilList = getTehsilList(glDTO.getDistrictID(),lang);
			
			for(int i = 0;i<tehsilList.size();i++)
			{
				ArrayList mohallaGuidelineData  = new ArrayList();
				GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
				String tehsilId = tDTO.getTehsilID();
				String tehsilName = tDTO.getTehsil();
				ArrayList wardPatwariList = getWardPatwari(tehsilId,lang);
				for(int j = 0;j<wardPatwariList.size();j++)
				{
					GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
					String wardId = wDTO.getWardID();
					String wardName = wDTO.getWard();
					ArrayList mohallaList = getMohViewList(wardId,lang);
					for(int k = 0; k<mohallaList.size();k++)
					{
						GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
						String mohallaId = mDTO.getMohallaID();
						String mohallaName = mDTO.getMohalla();
						glDTO.setTehsilID(tehsilId);
						glDTO.setWardID(wardId);
						glDTO.setMohallaID(mohallaId);
					
						mohallaGuidelineData = getIndividualMohallaViewNew(glDTO);
						logger.debug("size of arrayList: "+mohallaGuidelineData.size());
						logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName);
						guidelineData.put(disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName, mohallaGuidelineData);
						
					}
			}
		
		}
			logger.debug("size of HashMap"+guidelineData.size());

	} catch (Exception e) {
		e.printStackTrace();
		logger.debug(e);
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}
	
	return guidelineData;
}


/**
 * 
 * @param disttId
 * @return ArrayList that holds list of wards and patwari halkas
 * @throws Exception
 */
public ArrayList getWardPatwari (String tehId, String lang)throws Exception  {
	ArrayList wardList = new ArrayList();
	ArrayList wardPatwariList = new ArrayList();
	try {

		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String arr[] = {tehId};
		
		String sql =CommonSQL.GET_WARD_PATWARI;
		dbUtility.createPreparedStatement(sql);
		wardList = dbUtility.executeQuery(arr);
		for(int i = 0;i < wardList.size();i++)
		{
			ArrayList list = (ArrayList)wardList.get(i);
			GuidelineDTO gdto = new GuidelineDTO();
			if(lang.equalsIgnoreCase("en"))
			{
				gdto.setWard((String)list.get(1));
			}
			else
			{
				gdto.setWard((String)list.get(2));
			}
			gdto.setWardID((String)list.get(0));
			
			wardPatwariList.add(gdto);
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.debug(e);
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}
	return wardPatwariList;
	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////

/** CODE TO SEND EMAIL TO CONCERNED PERSON WHEN FEEDBACK IS GIVEN ON DRAFT
 */

public String getEmailContent(GuidelineDTO gDTO) throws Exception
{
	String status = new String();
	try {
		dbUtility = new DBUtility();
		//dbUtility.createStatement();
		logger.debug("<-----------Inside DAO_GUIDELINE Pending-------------->");
		
		
		dbUtility = new DBUtility();
		dbUtility.createCallableStatement(CommonSQL.CALL_IGRS_EMAIL_PROC);
		status = dbUtility.insertEmailData(CommonConstant.user_id, CommonConstant.EMAIL_SUBJECT, gDTO.getMailContent());
		logger.debug("<-----------status obtained-------------->"+status);

		//list2 = dbUtil.executeQuery(param);
		}
		catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		     } finally {
		    	 dbUtility.closeConnection();
		     }
	return status;
}


//***********************code to get view according to praroop*********************************************//
//*********************code as per the new Property Valuation*******************ADDED by>>>>>>>SHREERAJ<<<<<<<<<<
public ArrayList getArea(String language) {
	ArrayList areaList=null;
	
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		if(language.equalsIgnoreCase("en"))
		{	
			areaList=dbUtility.executeQuery(CommonSQL.AREA_NAME_EN);
		}
		else
		{
			areaList=dbUtility.executeQuery(CommonSQL.AREA_NAME_HI);
		}
		
		return areaList;
		
	} catch (Exception e) {
		logger.error(e);
		return null;
	}
}
public ArrayList getSubArea(String language, String areaId, String tehsilId) {
	ArrayList subAreaList=null;
	
try {
	dbUtility = new DBUtility();
	if(language.equalsIgnoreCase("en"))
	{	
		if("2".equalsIgnoreCase(areaId))//for urban
		{	
			dbUtility.createPreparedStatement(CommonSQL.SUB_AREA_NAME_EN_UR);
		subAreaList=dbUtility.executeQuery(new String[]{areaId,tehsilId});

		}
		else
		{
			dbUtility.createPreparedStatement(CommonSQL.SUB_AREA_NAME_EN);	
			subAreaList=dbUtility.executeQuery(new String[]{areaId});
		}
	}
	else
	{
		if("2".equalsIgnoreCase(areaId))//for rural
		{	
			dbUtility.createPreparedStatement(CommonSQL.SUB_AREA_NAME_HI_UR);
		subAreaList=dbUtility.executeQuery(new String[]{areaId,tehsilId});
		}
		else
		{
			dbUtility.createPreparedStatement(CommonSQL.SUB_AREA_NAME_HI);	
			subAreaList=dbUtility.executeQuery(new String[]{areaId});

		}
	}
	
	return subAreaList;
	
} catch (Exception e) {
	logger.error(e);
	return null;
}

}
public ArrayList getWardPatwari(String language, String subAreaId,
		String tehsilId) {

		ArrayList wardPatwariList=null;
	
	try {
		dbUtility = new DBUtility();
		if(language.equalsIgnoreCase("en"))
		{	
			dbUtility.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_EN);
		}
		else
		{
			dbUtility.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_HI);	
		}
		wardPatwariList=dbUtility.executeQuery(new String[]{subAreaId,tehsilId});
		return wardPatwariList;
		
	} catch (Exception e) {
		logger.error(e);
		return null;
	}
	finally{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
public ArrayList getColonyName(String language, String wardPatwariId) {
	ArrayList l2NameList=null;
	
	try {
		dbUtility = new DBUtility();
		if(language.equalsIgnoreCase("en"))
		{	
			dbUtility.createPreparedStatement(CommonSQL.COLONY_NAME_EN);
		}
		else
		{
			dbUtility.createPreparedStatement(CommonSQL.COLONY_NAME_HI);	
		}
		l2NameList=dbUtility.executeQuery(new String[]{wardPatwariId});
		return l2NameList;
		
	} catch (Exception e) {
		logger.error(e);
		return null;
	}	
	finally{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
//Modified by SHreeraJ
/**
 * 
 * @param glDTO
 * @return HashMap
 * @throws Exception
 */
public LinkedHashMap getGuidelineDataPraroop1 (GuidelineDTO glDTO, GuidelinePreparationForm eForm, String lang)throws Exception  {
	HashMap  guidelineData = new HashMap();
	HashMap tehsilMap = new HashMap();
	HashMap wardMap = new HashMap();
	HashMap mohallaMap = new HashMap();
	LinkedHashMap testMap = new LinkedHashMap();
	logger.debug("************new Method*************");
	glDTO.setPraroopType("1");
	try {
			String disttName = glDTO.getDistrict();
			ArrayList tehsilList = getTehsilList(glDTO.getDistrictID(),lang);
			tehsilMap = new HashMap();
			for(int i = 0;i<tehsilList.size();i++)
			{
				wardMap = new HashMap();
				ArrayList mohallaGuidelineData  = new ArrayList();
				GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
				String tehsilId = tDTO.getTehsilID();
				String tehsilName = tDTO.getTehsil();
				ArrayList areaList=getArea(lang);
				for(int a=0;a<areaList.size();a++){
					ArrayList al =(ArrayList)areaList.get(a);
					//GuidelineDTO alDTO =(GuidelineDTO)areaList.get(a);
					String areaId = al.get(0).toString();
					String areaName = al.get(1).toString();
					ArrayList subAreaList = getSubArea(lang,areaId,tehsilId);
					for(int b=0;b<subAreaList.size();b++){
						ArrayList sal =(ArrayList)subAreaList.get(b);
						String subAreaId = sal.get(0).toString();
						String subAreaName = sal.get(1).toString();
						ArrayList wardPatwariList = getWardPatwari(lang,subAreaId,tehsilId);
						for(int j = 0;j<wardPatwariList.size();j++)
						{
							mohallaMap = new HashMap();
							//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
							//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
							ArrayList wpl =(ArrayList)wardPatwariList.get(j);
							String wardId = wpl.get(0).toString();
							String wardName = wpl.get(1).toString();
							String wardMappingID = wpl.get(2).toString();
							ArrayList mohallaList = getColonyName(lang,wardMappingID);
							for(int k = 0; k<mohallaList.size();k++)
							{
								
								//GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
								ArrayList col =(ArrayList)mohallaList.get(k);
								String colonyID = col.get(0).toString();
								String colonyName = col.get(1).toString();
								String colonyMappingID = col.get(2).toString();
								glDTO.setTehsilID(tehsilId);
								glDTO.setAreaTypeID(areaId);
								glDTO.setSubAreaID(subAreaId);
								glDTO.setWardMappingID(wardMappingID);
								glDTO.setWardID(wardId);
								glDTO.setMohallaID(colonyID);
								glDTO.setColonyMappingID(colonyMappingID);
								
								mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
								logger.debug("size of arrayList: "+mohallaGuidelineData.size());
								logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName);
								testMap.put(disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName, mohallaGuidelineData);
								
							}
						}
					}
				}
				
				//eForm.setPropTypePraroop1(getPropertyTypes(glDTO));
				
		}
			
			logger.debug("size of HashMap"+testMap.size());
			
	} catch (Exception e) {
		e.printStackTrace();
		logger.debug(e);
	} finally {
		try {
			logger.debug("closing connection dbUtility.closeConnection();");
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}
	
	return testMap;
}

/**
 * 
 * @param glDTO
 * @return HashMap
 * @throws Exception
 */
public LinkedHashMap getGuidelineDataPraroop2 (GuidelineDTO glDTO,GuidelinePreparationForm eForm, String lang)throws Exception  {
	LinkedHashMap  guidelineData = new LinkedHashMap();
	HashMap tehsilMap = new HashMap();
	HashMap wardMap = new HashMap();
	HashMap mohallaMap = new HashMap();
	glDTO.setPraroopType("2");
	try {
			String disttName = glDTO.getDistrict();
			ArrayList tehsilList = getTehsilList(glDTO.getDistrictID(),lang);
			tehsilMap = new HashMap();
			for(int i = 0;i<tehsilList.size();i++)
			{
				wardMap = new HashMap();
				ArrayList mohallaGuidelineData  = new ArrayList();
				GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
				String tehsilId = tDTO.getTehsilID();
				String tehsilName = tDTO.getTehsil();
				
				//ArrayList wardPatwariList = getWardPatwari(tehsilId,lang);
				/*for(int j = 0;j<wardPatwariList.size();j++)
				{
					mohallaMap = new HashMap();
					GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
					String wardId = wDTO.getWardID();
					String wardName = wDTO.getWard();
					ArrayList mohallaList = getMohViewList(wardId,lang);
					for(int k = 0; k<mohallaList.size();k++)
					{
						
						GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
						String mohallaId = mDTO.getMohallaID();
						String mohallaName = mDTO.getMohalla();
						glDTO.setTehsilID(tehsilId);
						glDTO.setWardID(wardId);
						glDTO.setMohallaID(mohallaId);
					
						mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
						logger.debug("size of arrayList: "+mohallaGuidelineData.size());
						logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName);
						guidelineData.put(disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName, mohallaGuidelineData);
						
					}
				}*/
				ArrayList areaList=getArea(lang);
				for(int a=0;a<areaList.size();a++){
					ArrayList al =(ArrayList)areaList.get(a);
					//GuidelineDTO alDTO =(GuidelineDTO)areaList.get(a);
					String areaId = al.get(0).toString();
					String areaName = al.get(1).toString();
					ArrayList subAreaList = getSubArea(lang,areaId,tehsilId);
					for(int b=0;b<subAreaList.size();b++){
						ArrayList sal =(ArrayList)subAreaList.get(b);
						String subAreaId = sal.get(0).toString();
						String subAreaName = sal.get(1).toString();
						ArrayList wardPatwariList = getWardPatwari(lang,subAreaId,tehsilId);
						for(int j = 0;j<wardPatwariList.size();j++)
						{
							mohallaMap = new HashMap();
							//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
							//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
							ArrayList wpl =(ArrayList)wardPatwariList.get(j);
							String wardId = wpl.get(0).toString();
							String wardName = wpl.get(1).toString();
							String wardMappingID = wpl.get(2).toString();
							ArrayList mohallaList = getColonyName(lang,wardMappingID);
							for(int k = 0; k<mohallaList.size();k++)
							{
								
								//GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
								ArrayList col =(ArrayList)mohallaList.get(k);
								String colonyID = col.get(0).toString();
								String colonyName = col.get(1).toString();
								String colonyMappingID = col.get(2).toString();
								glDTO.setTehsilID(tehsilId);
								glDTO.setAreaTypeID(areaId);
								glDTO.setSubAreaID(subAreaId);
								glDTO.setWardMappingID(wardMappingID);
								glDTO.setWardID(wardId);
								glDTO.setMohallaID(colonyID);
								glDTO.setColonyMappingID(colonyMappingID);
								
								mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
								logger.debug("size of arrayList: "+mohallaGuidelineData.size());
								logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName);
								guidelineData.put(disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName, mohallaGuidelineData);
								
							}
						}
					}
				}
				
				//eForm.setPropTypePraroop2(getPropertyTypes(glDTO));
				
		}
			
			logger.debug("size of HashMap"+guidelineData.size());

	} catch (Exception e) {
		e.printStackTrace();
		logger.debug(e);
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}
	
	return guidelineData;
}

/**
 * 
 * @param glDTO
 * @return HashMap
 * @throws Exception
 */
public LinkedHashMap getGuidelineDataPraroop3 (GuidelineDTO glDTO,GuidelinePreparationForm eForm,String lang)throws Exception  {
	LinkedHashMap  guidelineData = new LinkedHashMap();
	HashMap tehsilMap = new HashMap();
	HashMap wardMap = new HashMap();
	HashMap mohallaMap = new HashMap();
	glDTO.setPraroopType("3");
	try {
			String disttName = glDTO.getDistrict();
			ArrayList tehsilList = getTehsilList(glDTO.getDistrictID(),lang);
			tehsilMap = new HashMap();
			for(int i = 0;i<tehsilList.size();i++)
			{
				wardMap = new HashMap();
				ArrayList mohallaGuidelineData  = new ArrayList();
				GuidelineDTO tDTO =(GuidelineDTO)tehsilList.get(i);
				String tehsilId = tDTO.getTehsilID();
				String tehsilName = tDTO.getTehsil();
				/*ArrayList wardPatwariList = getWardPatwari(tehsilId,lang);
				for(int j = 0;j<wardPatwariList.size();j++)
				{
					mohallaMap = new HashMap();
					GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
					String wardId = wDTO.getWardID();
					String wardName = wDTO.getWard();
					ArrayList mohallaList = getMohViewList(wardId,lang);
					for(int k = 0; k<mohallaList.size();k++)
					{
						
						GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
						String mohallaId = mDTO.getMohallaID();
						String mohallaName = mDTO.getMohalla();
						glDTO.setTehsilID(tehsilId);
						glDTO.setWardID(wardId);
						glDTO.setMohallaID(mohallaId);
					
						mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
						logger.debug("size of arrayList: "+mohallaGuidelineData.size());
						logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName);
						guidelineData.put(disttName+"~"+tehsilName+"~"+wardName+"~"+mohallaName, mohallaGuidelineData);
						
					}
				}*/
				ArrayList areaList=getArea(lang);
				for(int a=0;a<areaList.size();a++){
					ArrayList al =(ArrayList)areaList.get(a);
					//GuidelineDTO alDTO =(GuidelineDTO)areaList.get(a);
					String areaId = al.get(0).toString();
					String areaName = al.get(1).toString();
					ArrayList subAreaList = getSubArea(lang,areaId,tehsilId);
					for(int b=0;b<subAreaList.size();b++){
						ArrayList sal =(ArrayList)subAreaList.get(b);
						String subAreaId = sal.get(0).toString();
						String subAreaName = sal.get(1).toString();
						ArrayList wardPatwariList = getWardPatwari(lang,subAreaId,tehsilId);
						for(int j = 0;j<wardPatwariList.size();j++)
						{
							mohallaMap = new HashMap();
							//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
							//GuidelineDTO wDTO =(GuidelineDTO)wardPatwariList.get(j);
							ArrayList wpl =(ArrayList)wardPatwariList.get(j);
							String wardId = wpl.get(0).toString();
							String wardName = wpl.get(1).toString();
							String wardMappingID = wpl.get(2).toString();
							ArrayList mohallaList = getColonyName(lang,wardMappingID);
							for(int k = 0; k<mohallaList.size();k++)
							{
								
								//GuidelineDTO mDTO = (GuidelineDTO)mohallaList.get(k);
								ArrayList col =(ArrayList)mohallaList.get(k);
								String colonyID = col.get(0).toString();
								String colonyName = col.get(1).toString();
								String colonyMappingID = col.get(2).toString();
								glDTO.setTehsilID(tehsilId);
								glDTO.setAreaTypeID(areaId);
								glDTO.setSubAreaID(subAreaId);
								glDTO.setWardMappingID(wardMappingID);
								glDTO.setWardID(wardId);
								glDTO.setMohallaID(colonyID);
								glDTO.setColonyMappingID(colonyMappingID);
								
								mohallaGuidelineData = getIndividualMohallaViewPraroop(glDTO);
								logger.debug("size of arrayList: "+mohallaGuidelineData.size());
								logger.debug("key of map---->"+disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName);
								guidelineData.put(disttName+"~"+tehsilName+"~"+areaName+"~"+subAreaName+"~"+wardName+"~"+colonyName, mohallaGuidelineData);
								
							}
						}
					}
				}
				
				//eForm.setPropTypePraroop3(getPropertyTypes(glDTO));
				
		}
			
			logger.debug("size of HashMap"+guidelineData.size());

	} catch (Exception e) {
		e.printStackTrace();
		logger.debug(e);
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}
	
	return guidelineData;
}


/**
 * 
 * @param gDTO
 * @return ArrayList
 */
public ArrayList getIndividualMohallaViewPraroop(GuidelineDTO gDTO)
{
	GuidelineDTO  mohallaDetails ;
	ArrayList mohallaDetailsList = new ArrayList();
	ArrayList list1 = new ArrayList();
	ArrayList list2 = new ArrayList();
	String ward = null;
	try {
		
		connTest = DBUtilityTest.openConnection();
		
		if(gDTO.getGuideType().equalsIgnoreCase("F"))
		{
			pst = connTest.prepareStatement(CommonSQL.GUIDELINE_VIEW_FINAL_PRAROOP);
		}
		else
		{
			pst = connTest.prepareStatement(CommonSQL.GUIDELINE_VIEW_TEMP_PRAROOP);
		}
		String patID = gDTO.getPatwariID();
		String warID = gDTO.getWardID();
		
		if(patID != null && patID.length() > 0)
		{
			 ward = gDTO.getPatwariID();
		}
		else
			 ward = gDTO.getWardID();
		
		try {
	           
            pst.setString( 1, gDTO.getGuideLineID());
            pst.setString( 2, gDTO.getAreaTypeID());
            //pst.setString( 3, gDTO.getSubAreaID());
            
            pst.setString(3, gDTO.getTehsilID());
            pst.setString(4, gDTO.getWardID());
           // pst.setString(6, gDTO.getWardMappingID());
            pst.setString(5, gDTO.getColonyMappingID());
            pst.setString(6, gDTO.getMohallaID());
           // pst.setString(7, gDTO.getPraroopType());
          
            logger.debug("guideid"+gDTO.getGuideLineID());
            logger.debug("area"+gDTO.getAreaTypeID());
            logger.debug("subarea"+gDTO.getSubAreaID());
            logger.debug("wardmapping"+gDTO.getWardMappingID());
            logger.debug("colonymapping"+gDTO.getColonyMappingID());
            logger.debug("teh"+gDTO.getTehsilID());
            logger.debug("ward"+gDTO.getWardID());
            logger.debug("praroop"+gDTO.getPraroopType());
            logger.debug("moh"+gDTO.getMohallaID());
            
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
        logger.debug("DAO  - executeQuery(String[]): " +
                           sqle.getMessage());
        throw sqle;
    } catch (NumberFormatException nfe) {
        logger.debug("DAO  - executeQuery(String[]): " +
                           nfe.getMessage());
        throw nfe;
    } catch (IllegalArgumentException iae) {
        logger.debug("DAO  - executeQuery(String[]): " +
                           iae.getMessage());
        throw iae;
    }
    list2.trimToSize();
    
    if(list2.size() == 0)
    {
    	mohallaDetails = new GuidelineDTO();
    	if(gDTO.getPraroopType() == "1")
    	{
    		
    		for(int i = 0 ;i < 3; i++)
    		{
    			mohallaDetails.setGuideLineValue("0");
    			mohallaDetailsList.add(mohallaDetails);
    		}
    	}
    	else if(gDTO.getPraroopType() == "2")
    	{
    		for(int i = 0 ;i < 9; i++)
    		{
    			mohallaDetails.setGuideLineValue("0");
    			mohallaDetailsList.add(mohallaDetails);
    		}
    	}
    	else
    	{
    		for(int i = 0 ;i < 4; i++)
    		{
    			mohallaDetails.setGuideLineValue("0");
    			mohallaDetailsList.add(mohallaDetails);
    		}
    	}
    }
    else
    {
    	for (int i = 0; i < list2.size(); i++) {
			list1 = (ArrayList)list2.get(i);
			mohallaDetails = new GuidelineDTO();
			
			mohallaDetails.setPropertyID((String)list1.get(0));
			mohallaDetails.setPropertyTypeName((String)list1.get(1));
			mohallaDetails.setL1_ID((String)list1.get(2));
			mohallaDetails.setL1Name((String)list1.get(3));
			mohallaDetails.setL2_ID((String)list1.get(4));
			mohallaDetails.setL2Name((String)list1.get(5));
			if(list1.get(6).equals(""))
			{
				mohallaDetails.setGuideLineValue("0");
			}
			else
			{
				mohallaDetails.setGuideLineValue((String)list1.get(6));
			}
			mohallaDetails.setMohallaID((String)list1.get(7));
			mohallaDetails.setMohalla((String)list1.get(8));
			mohallaDetails.setUomName((String)list1.get(9));
			mohallaDetails.setUomID((String)list1.get(10));
			mohallaDetailsList.add(mohallaDetails);
		}
    }
    
		
		logger.debug("size of mohallaDetailsList"+mohallaDetailsList.size());
	} catch (Exception x) {
		logger.debug("error---->  "+x);

	}finally {
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

/**
 * 
 * @param gDTO
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getPropertyTypes (GuidelineDTO gDTO)throws Exception  {
	ArrayList propertyTypeList = new ArrayList();
	ArrayList propertyTypeListFinal = new ArrayList();
	
	try {

		dbUtility = new DBUtility();
		String sql =CommonSQL.GET_PROPERTY_TYPES;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {
				gDTO.getGuideLineID(),
				gDTO.getPraroopType()
		};
		propertyTypeList = dbUtility.executeQuery(arr);
		for(int i = 0;i < propertyTypeList.size();i++)
		{
			ArrayList list = (ArrayList)propertyTypeList.get(i);
			GuidelineDTO gdto = new GuidelineDTO();
			gDTO.setPropId1((String)list.get(0));
			gDTO.setPropId2((String)list.get(1));
			propertyTypeListFinal.add(gdto);
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.debug(e);
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}
	return propertyTypeListFinal;
	
}


public String getFinalGuidelineId(GuidelineDTO formDTO) {
	String guideId=null;
	
	try {

		
		
		dbUtility = new DBUtility();
		String sql =CommonSQL.GET_FINAL_GUIDELINE_ID;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {
				formDTO.getDistrictID()
				
		};
		guideId = dbUtility.executeQry(arr);
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.debug(e);
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}
	return guideId;
	
}


public ArrayList getGuidelineIdList(GuidelinePreparationForm form) {
	
	
	
	GuidelineDTO guidelineIdYear ;
	ArrayList guidelineIdYearList = new ArrayList();
	
	ArrayList list1 = new ArrayList();
	ArrayList list2 = new ArrayList();
	
	try {
		connTest = DBUtilityTest.openConnection();
		
		
		try {
			pst = connTest.prepareStatement(CommonSQL.GET_GUIDELINE_IDS);
			pst.setString(1, form.getGuideDTO().getDistrictID());
			pst.setString(2, form.getGuideDTO().getFinancialYear());
            rst = pst.executeQuery();
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
                list2.add(row_list);
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
        list2.trimToSize();
		
		for (int i = 0; i < list2.size(); i++) {
			list1 = (ArrayList)list2.get(i);
			
			guidelineIdYear = new GuidelineDTO();
			
			//financialYear.setFinancialYearID((String)list1.get(0));
			guidelineIdYear.setGuidelineIdTop((String)list1.get(0));
			guidelineIdYear.setGuidelineIdYearTop((String)list1.get(1));
			//financialYear.setHdnFinancialYear((String)list1.get(0)+"~"+(String)list1.get(1));
			guidelineIdYearList.add(guidelineIdYear);
		}
	}
	catch(Exception e)
	{
		logger.debug(e);
		e.printStackTrace();
	}
	finally {
		try {
			
			rst.close();
			pst.close();
			DBUtilityTest.closeConnection();
		} catch (Exception ex) {

			logger.debug(ex);
			ex.printStackTrace();
		}
	}
	
	return guidelineIdYearList;
	
	
	
	
	
	
	
	/*ArrayList guidelineIDList = new ArrayList();
	ArrayList guidelineIDListFinal = new ArrayList();
	
	try {
		form.getGuideDTO().setDistrictID("5");
		dbUtility = new DBUtility();
		String sql =CommonSQL.GET_GUIDELINE_IDS;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {
				form.getGuideDTO().getDistrictID(),
				
		};
		guidelineIDList = dbUtility.executeQuery(arr);
		for(int i = 0;i < guidelineIDList.size();i++)
		{
			ArrayList list = (ArrayList)guidelineIDList.get(i);
			GuidelineDTO gdto = new GuidelineDTO();
			form.getGuideDTO().setGuidelineIdTop((String)list.get(0));
			form.getGuideDTO().setGuidelineIdYearTop((String)list.get(1));
			guidelineIDListFinal.add(gdto);
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.debug(e);
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}
	return guidelineIDListFinal;
*/}
public boolean emptyTempTable(String guideID){
	     boolean transactionflag = false;
	     String detl[]    = new String[1];
	     detl[0]=guideID;
	     
	     DBUtility dbUtility = null;
	   try{
		   dbUtility = new DBUtility();
		
	  
		   dbUtility.createPreparedStatement(CommonSQL.DEL_TEMP_TABLE);
		   transactionflag= dbUtility.executeUpdate(detl);
		   if(transactionflag){
	       dbUtility.commit();				     
		   }
	 }
	   catch(Exception e){
		   e.printStackTrace();
		   
		logger.error(" Exception at emptyTempTable in DAO " + e);
		
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at emptyTempTable in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 
}
public String updateTempGuideline(String guideID,String distID){
    boolean transactionflag = false;
    String detl[]    = new String[3];
  
    String ranNo=GUIDGenerator.getGUID();
    detl[0]=guideID;
    detl[1]=ranNo;
    detl[2]=distID;
    DBUtility dbUtility = null;
  try{
	   dbUtility = new DBUtility();
	
 
	   dbUtility.createPreparedStatement(CommonSQL.CALL_PRC_TEMP_GUID);
	   transactionflag= dbUtility.executeUpdate(detl);
	   if(transactionflag){
      dbUtility.commit();				     
	   }
}
  catch(Exception e){
	   e.printStackTrace();
	   
	logger.error(" Exception at emptyTempTable in DAO " + e);
	
	}
  
  finally {
		 try{	    
			 if (dbUtility != null) {
					dbUtility.closeConnection();
		 }
			 }
		 catch (Exception e) {
		 logger.error("Exception at emptyTempTable in DAO  " + e);
		 }		
      }

  return ranNo;   

}
}
