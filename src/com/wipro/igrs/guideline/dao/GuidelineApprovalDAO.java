
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
 * FILE NAME: GuidelineApprovalAction.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    6th MAY 2008 
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DATA ACCESS OBJECTS FOR GUIDELINE APPROVAL ACTION.
 */

package com.wipro.igrs.guideline.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Date;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

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
public class GuidelineApprovalDAO {

	Connection con;
	//DBUtility dbUtil;

	 DBUtility dbUtility = null;
	 	Statement st = null;
	    PreparedStatement pst = null;
	    ResultSet rst = null;
	    CallableStatement clstmt = null;
	    CallableStatement oclstmt = null;
	    //DBUtility dbUtility = null;
		private CallableStatement callstmt  ;
	private Logger logger = (Logger) Logger.getLogger(GuidelineApprovalDAO.class);
	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");


	/**
	 * @param tehsilid
	 * @return ArrayList
	 */
	 /*
	public ArrayList getWardList(String tehsilid){
		GuidelineDTO  ward ;
		ArrayList wardList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			//dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.WARD);
			//list2 = dbUtil.executeQuery(tehsilid);
	
			dbUtility = new DBUtility();
					
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

			}
		}
		return wardList;
	}



	public ArrayList getPatwariList(String tehsilid){

		GuidelineDTO  patwari;
		ArrayList patwariList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			
			dbUtility = new DBUtility();
			
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
			}
		}
		return patwariList;
	}

*/

	/**
	 * @return ArrayList that holds District List
	 */
	public ArrayList getDistrictList() {
		GuidelineDTO  district ;
		ArrayList districtList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			dbUtility = new DBUtility();
			
				
				 try {
					 dbUtility.createStatement();
						list2 = dbUtility.executeQuery(CommonSQL.DISTRICT);
						
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


	/**
	 * @return ArrayList that holds list of areas (urban/rural etc)
	 */
	public ArrayList getAreasTypeList(){

		GuidelineDTO  areaType ;
		ArrayList areaList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			
			 dbUtility = new DBUtility();
			//st =
				// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
		
			try {
				String sql =CommonSQL.AREA_TYPE;
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
				dbUtility.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
		return areaList;
	}

	///////////////////////////BELOW CODE COMMENTED BY SIMRAN/////////////////////////////////////////////

	/**
	 * @param distid
	 * @return ArryaList
	 */
	/*public ArrayList getTehsilList(String distid) {
		GuidelineDTO  tehsil ;
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		
			try {
				
				dbUtility = new DBUtility();
				
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

		} finally {
			try {
					rst.close();
					pst.close();
					DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
		return tehsilList;
	}

	public boolean updateTempMohallaDetails(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, 
			String dTo,String roleId, String funId, String userId){  		

		logger.debug("DAO:-  updateTempMohallaDetails");
		boolean mohallaInserted = false;
		int j = 0;
		IGRSCommon igrsCommon = null;
		try {
				//dbUtil = new DBUtility();
				igrsCommon = new IGRSCommon();
				//dbUtil.createPreparedStatement(CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
			
			dbUtility = new DBUtility();
			pst = connTest.prepareStatement(CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
			
			connTest.setAutoCommit(false);
			
			int guideStatus = CommonConstant.GUIDELINESTATUS_DRAFT;

			for(int i = 0;i<mohallaDetails.size();i++ ){
				GuidelineDTO dto = (GuidelineDTO)mohallaDetails.get(i);

			try
			{
				pst.setString( 1, gDTO.getDistrictID());
				pst.setString( 2, gDTO.getTehsilID());
				pst.setString( 3, gDTO.getWardID());
				pst.setString( 4, dto.getMohallaID());
				pst.setString( 5, dto.getAreaTypeID());
				pst.setString( 6, dto.getPropertyID());
				pst.setString( 7, dto.getL1_ID());
				pst.setString( 8, dto.getL2_ID());
				pst.setString( 9, "SQM");
				pst.setString( 10, ""+dto.getProposedValue());
				pst.setString( 11, dFrom);
				pst.setString( 12, dTo);
				pst.setInt( 13, guideStatus);
				pst.setString( 14, dto.getCreatedBy());
				pst.setString( 15, dto.getCreatedDate());
				pst.setString( 16, userId);

				pst.setString( 17, gDTO.getBasePeriodFrom());
				pst.setString( 18, gDTO.getBasePeriodTo());
				pst.setString( 19, gDTO.getFinancialYear());
				pst.setString( 20, dto.getAverageValue());
				pst.setString( 21, ""+dto.getIncrementValue());
				pst.setString( 22, "G");

				pst.setString( 23, dto.getGuideLineID());

				//mohallaInserted = dbUtil.executeUpdate(moharray);
				
				try {
	                j = pst.executeUpdate();
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
	        
	        if (j == 0)
	        	mohallaInserted = false;
	        mohallaInserted = true;
				
				if(mohallaInserted) {
					connTest.commit();
					igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","T",funId,userId,roleId);
				}else {
					
					igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","F",funId,userId,roleId);
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
			logger.debug("ERROR:-   "+x.getMessage());
		}finally {
			try {
					
					pst.close();
					DBUtilityTest.closeConnection();
				
			}catch (Exception xe ) {
				logger.debug("Error In Closing"+xe);
			}
		}
		logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
		return mohallaInserted;
	}

	public boolean insertFinalMohallaMasterDetails(ArrayList mohallaDetails, GuidelineDTO gDTO, 
			String dFrom, String dTo, String roleId, String funId, String userId){  		

		logger.debug("DAO:-  insertFinalMohallaMasterDetails "+CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
		boolean mohallaInserted = false;
		String guideLineIds="";
		int j = 0;
		IGRSCommon igrsCommon = null;
		try {
			//dbUtil = new DBUtility();
			igrsCommon = new IGRSCommon();
			//dbUtil.createPreparedStatement(CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
			
			dbUtility = new DBUtility();
			pst = connTest.prepareStatement(CommonSQL.MOHALLA_DETAILS_UPDATE_APPROVAL);
			
			connTest.setAutoCommit(false);
			
			int guideStatus = CommonConstant.GUIDELINESTATUS_FINAL;

			for(int i = 0;i<mohallaDetails.size();i++ ){
				GuidelineDTO dto = (GuidelineDTO)mohallaDetails.get(i);

			try
			{
				pst.setString( 1, gDTO.getDistrictID());
				pst.setString( 2, gDTO.getTehsilID());
				pst.setString( 3, gDTO.getWardID());
				pst.setString( 4, dto.getMohallaID());
				pst.setString( 5, dto.getAreaTypeID());
				pst.setString( 6, checkNull(dto.getPropertyID()));
				pst.setString( 7, checkNull(dto.getL1_ID()));
				pst.setString( 8, checkNull(dto.getL2_ID()));
				pst.setString( 9, "SQM");
				pst.setString( 10, ""+dto.getGuideLineValue());
				pst.setString( 11, dFrom);
				pst.setString( 12, dTo);
				pst.setInt( 13, guideStatus);
				pst.setString( 14, dto.getCreatedBy());
				pst.setString( 15, dto.getCreatedDate());
				pst.setString( 16, userId);

				pst.setString( 17, gDTO.getBasePeriodFrom());
				pst.setString( 18, gDTO.getBasePeriodTo());
				pst.setString( 19, gDTO.getFinancialYear());
				pst.setString( 20, dto.getAverageValue());
				pst.setString( 21, ""+dto.getIncrementValue());
				pst.setString( 22, "G");

				pst.setString( 23, dto.getGuideLineID());
				
				if(guideLineIds.equals("")){
					guideLineIds = dto.getGuideLineID();
				}
				else{
					guideLineIds = guideLineIds+","+dto.getGuideLineID();
				}

				//mohallaInserted = dbUtil.executeUpdate(moharray);
				

				try {
	                j = pst.executeUpdate();
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
	        
	        if (j == 0)
	        	mohallaInserted = false;
	        mohallaInserted = true;
				
				if(mohallaInserted){
					connTest.commit();
					igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","T",funId,userId,roleId);
				}
				else{
					
					igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","F",funId,userId,roleId);
				}
			}
			if(mohallaInserted){
				logger.debug("Data sucessfully set for the temp table for the guileline ids ->"+guideLineIds);
				mohallaInserted = insertMohallaMasterDetails(roleId,funId,userId,gDTO.getMohallaID(),guideLineIds);

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
			logger.debug("ERROR:-   "+x.getMessage());
		}finally {
			try {
					pst.close();
					DBUtilityTest.closeConnection();
				
			}catch (Exception xe ) {
				logger.debug("Closing Error:-"+xe);
			}
		}
		logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
		return mohallaInserted;
	}
	public boolean insertMohallaMasterDetails(String roleId, String funId, String userId, String mohId, String guideLineIds){

		logger.debug("DAO:-  insertMohallaMasterDetails");
		boolean mohallaInserted = false;
		IGRSCommon  igrsCommon = null;
		int j = 0;
		
		String sql=CommonSQL.MOHALLA_MASTER_DETAILS_INSERT;
		sql = sql +" AND mohalla_village_id='"+mohId+"' AND guideline_id in ("+guideLineIds+")";
		try {
			
			igrsCommon = new IGRSCommon();
			
			logger.debug("DAO:-  insertMohallaMasterDetails:-  Query:-      "+sql);
			
			dbUtility = new DBUtility();
			pst = connTest.prepareStatement(sql);
			
		try
		{
			pst.setInt( 1, CommonConstant.GUIDELINESTATUS_FINAL);
			
			//mohallaInserted = dbUtil.executeUpdate(status);
			
			try {
                j = pst.executeUpdate();
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
        
        if (j == 0)
        	mohallaInserted = false;
        mohallaInserted = true;
			
			if(mohallaInserted){
				connTest.commit();
				igrsCommon.saveLogDet("IGRS_MASTER_GUIDELINE_DATA","INSERT","T",funId,userId,roleId);
			}
			else{
				
				igrsCommon.saveLogDet("IGRS_MASTER_GUIDELINE_DATA","INSERT","F",funId,userId,roleId);
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
				logger.debug("Close Error:-"+xe);
			}
		}

		return mohallaInserted;
	}
public ArrayList getMohList(String wardid){
		GuidelineDTO  mohalla ;
		ArrayList mohList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		try {
			dbUtility = new DBUtility();
			
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
				mohalla.setMohallaID((String)list1.get(0)+"="+(String)list1.get(1));
				mohalla.setMohalla((String)list1.get(1));
			//	mohalla.setStatus((String)list1.get(4));  
			//	mohalla.setHdnMohalla((String)list1.get(2)+"="+(String)list1.get(3));
				mohList.add(mohalla);
				logger.debug(""+mohList.size());
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
		return mohList;
	}
	public ArrayList getVillage(String patwariId[]){

		logger.debug("GuidelinePreparationDAO:: Called:  getVillage()");
		GuidelineDTO  village ;
		ArrayList villageList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			//dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.VILLAGE);
			//logger.debug(CommonSQL.VILLAGE);
			dbUtility = new DBUtility();
			
			pst = connTest.prepareStatement(CommonSQL.VILLAGE);
			//list2 = dbUtil.executeQuery(patwariId);
			
			 try {
		            for (int i = 0; i < patwariId.length; i++) {
		            	logger.debug("Wipro : patwari[" + i + "] = " + patwariId[i]);
		                pst.setString(i + 1, patwariId[i]);
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
				village = new GuidelineDTO();
				village.setBasePeriodFrom((String)list1.get(0));
				village.setBasePeriodTo((String)list1.get(1));
				village.setVillageID((String)list1.get(2));
				village.setVillage((String)list1.get(3));
				villageList.add(village);
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
		return villageList;
	}

public ArrayList checkTempTableData(){

		ArrayList dbValue = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			
			dbUtility = new DBUtility();
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

public ArrayList getIndividualMohallaDetails(String[] param) {

		GuidelineDTO  mohallaDetails ;
		ArrayList mohallaDetailsList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			//dbUtil = new DBUtility();

			//dbUtil.createPreparedStatement(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS);
			//logger.debug(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS);
			dbUtility = new DBUtility();
			
			pst = connTest.prepareStatement(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS);

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
	            logger.debug("DAO- executeQuery(String[]): " +
	                               nfe.getMessage());
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	            logger.debug("DAO- executeQuery(String[]): " +
	                               iae.getMessage());
	            throw iae;
	        }
	        list2.trimToSize();
	        
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
				logger.debug("(String)list1.get(19):-        "+(String)list1.get(19));

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

		} finally {
			try {
					rst.close();
					pst.close();
					DBUtilityTest.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
		return mohallaDetailsList;
	}
	
	private static String checkNull(String checkStr){
		String returnStr="";
			if(checkStr == null || checkStr == "null"){
				checkStr=returnStr;
			}
			
		return checkStr;
	}
	
	
	
	public ArrayList getStartEndDate(String fyear)
	{
		ArrayList start_end= new ArrayList();
		ArrayList list2 = new ArrayList();
		logger.debug("<-----In DAO----->");
		
		
		try {
					//dbUtil = new DBUtility();
				//dbUtil.createPreparedStatement(sql);
			dbUtility = new DBUtility();
			
			pst = connTest.prepareStatement(CommonSQL.START_END);
			logger.debug(CommonSQL.START_END);
			//list2 = dbUtil.executeQuery(fyear);
			
			try
			{	
			
	                pst.setString(1, fyear);
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
						rst.close();
						pst.close();
						DBUtilityTest.closeConnection();
				} catch (Exception ex) {

					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		
		
		
		return start_end;
	}*/
	///////////////////////////////end commented code////////////////////////////
	/**
	 * 
	 * @return ArrayList that holds list of FinancialYear
	 */
public ArrayList getFinancialYearList(){
		
		GuidelineDTO financialYear ;
		ArrayList financialYearList = new ArrayList();
		
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createStatement();
			
			dbUtility = new DBUtility();
			//st =
				 //connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			//list2 = dbUtil.executeQuery(CommonSQL.FINANCIAL);
			try {
				String sql =CommonSQL.FINANCIAL;
				dbUtility.createStatement();
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
 * @param finan
 * @param disttID
 *@return ArrayList that Holds available versions of Guideline for a particular District and 
 * Financial Year
 */
public ArrayList showStatusChecker(String finan, String disttID, String lang)
	{
		GuidelineDTO  version ;
		ArrayList versionList = new ArrayList();
		ArrayList listVer1 = new ArrayList();
		ArrayList listVer2 = new ArrayList();
		String dFrom = null;
		String dTo = null;
		
		try {
			
			logger.debug("<....."+disttID);
			logger.debug("<...."+finan);
			 dbUtility = new DBUtility();
					
					
			try
				{	
				String sql = CommonSQL.SHOW_VERSION_CHECKER;
				 dbUtility.createPreparedStatement(sql);
					String sd[] = new String[2];
					sd[0] = finan;
					sd[1] = disttID;
					listVer2 =dbUtility.executeQuery(sd);
							
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
				String status=((String)listVer1.get(6));
				
				if("3".equalsIgnoreCase(status))
				{
					version.setColorCode("orange");
				}
				else
				{
					version.setColorCode("green");
				}
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
				version.setChkStatus((String)listVer1.get(4));
				if(lang.equalsIgnoreCase("en"))
				{
					version.setStatus((String)listVer1.get(3));
					version.setStatusChecker((String)listVer1.get(4));
					version.setGuideDerivedFrom((String)listVer1.get(5));
				}
				else
				{
					String makerStatus =  (String)listVer1.get(3);
					String checkerStatus = (String)listVer1.get(4);
					String derivedFrom = (String)listVer1.get(5);
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
					if(derivedFrom.equalsIgnoreCase("New"))
					{
						version.setGuideDerivedFrom("नया");
					}
					else if(derivedFrom.equalsIgnoreCase("draft"))
					{
						version.setGuideDerivedFrom("प्रारूप्");
					}
					else
					{
						version.setGuideDerivedFrom("फाइनल");
					}
				}
				
				version.setHdnGuideDuration((String)listVer1.get(0)+"~"+dFrom+"~"+dTo+"~"+(String)listVer1.get(4)+"~"+(String)listVer1.get(5));
				
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
 * @param disttID
 * @param guideID
 *@return ArrayList that holds list of Tehsils for particular guideline for which Checker has not 
 * yet approved the guideline Data
 */
	public ArrayList getPendingTehsilListChecker(String disttID, long guideID, String lang)
	{
		GuidelineDTO  tehsil ;
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			
			dbUtility = new DBUtility();
			if(lang.equalsIgnoreCase("hi"))
			{
				 dbUtility.createPreparedStatement(CommonSQL.PENDING_TEHSIL_CHECKER_HI);
				
			}
			else
			{
				 dbUtility.createPreparedStatement(CommonSQL.PENDING_TEHSIL_CHECKER);
				
			}
			
			
			try
			{	
				String sd[] = new String[2];
				sd[0] = disttID;
				sd[1] = String.valueOf(guideID);
				list2 =dbUtility.executeQuery(sd);
	               
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
	 * @param disttID
	 * @param guideID
	 * @return ArrayList that holds list of Tehsils for particular guideline for which Checker has  
	 * already approved the guideline Data
	 */
		public ArrayList getCompleteTehsilListChecker(String disttID, long guideID, String lang)
		{
			GuidelineDTO  tehsil ;
			ArrayList tehsilList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();

			try {
				
				dbUtility = new DBUtility();
				if(lang.equalsIgnoreCase("hi"))
				{
					dbUtility.createPreparedStatement(CommonSQL.COMPLETE_TEHSIL_CHECKER_HI);
					
				}
				else
				{
					dbUtility.createPreparedStatement(CommonSQL.COMPLETE_TEHSIL_CHECKER);
					
				}
				
				
				try
				{	String sd[] = new String[4];
				sd[0] = disttID;
				sd[1] = String.valueOf(guideID);
				sd[2] = disttID;
				sd[3] = String.valueOf(guideID);
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
	* @return ArrayList that holds list of wards for particular guideline for which Checker has not
	* yet approved the guideline Data
 */
		public ArrayList getPendingWardListChecker(int tehID, long guideID, String lang)
		{
			GuidelineDTO  ward ;
			ArrayList wardList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();
			
			try {
				dbUtility = new DBUtility();
				if(lang.equalsIgnoreCase("hi"))
				{
					dbUtility.createPreparedStatement(CommonSQL.WARD_CHECKER_HI);
					
				}
				else
				{
					dbUtility.createPreparedStatement(CommonSQL.WARD_CHECKER);
					
				}
				
				try
				{	
					String sd[] = new String[2];
					sd[0] = String.valueOf(tehID);
					sd[1] = String.valueOf(guideID);
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
					ward = new GuidelineDTO();
					logger.debug("#######"+list1.get(0));
					ward.setWardID((String)list1.get(0)+"~"+(String)list1.get(3));
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
		 * @param tehID
		 * @param guideID
		 * @return ArrayList that holds list of wards for particular guideline for which Checker has  
		 * already approved the guideline Data
		 */
		public ArrayList getCompleteWardListChecker(int  tehID, long guideID, String lang)
		{
			GuidelineDTO  ward ;
			ArrayList wardList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();
			try {
				dbUtility = new DBUtility();
				if(lang.equalsIgnoreCase("hi"))
				{
					dbUtility.createPreparedStatement(CommonSQL.COMPLETE_WARD_CHECKER_HI);
					
				}
				else
				{
					dbUtility.createPreparedStatement(CommonSQL.COMPLETE_WARD_CHECKER);
					
				}
			
				
				try
				{	
					String sd[] = new String[4];
					sd[0] = String.valueOf(tehID);
					sd[1] = String.valueOf(guideID);
					sd[2] = String.valueOf(tehID);
					sd[3] = String.valueOf(guideID);
					list2 = dbUtility.executeQuery(sd);
					
		                logger.debug("IN DAO"+tehID);
		                logger.debug("IN DAO"+guideID);
		                
		              
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
			
			        logger.debug("SIZE OF LIST"+list2.size());
				
				for (int i = 0; i < list2.size(); i++) {
					list1 = (ArrayList)list2.get(i);
					ward = new GuidelineDTO();
					logger.debug("#######"+list1.get(0));
					ward.setWardID((String)list1.get(0)+"~"+(String)list1.get(3));
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
		 * @param tehID
		 * @param guideID
		 * @return ArrayList that holds list of Patwari Halkas for particular guideline for which Checker has not
		 * yet approved the guideline Data
		 */
		public ArrayList getPendingPatwariListChecker(int  tehID, long guideID, String lang)
		{
			GuidelineDTO  patwari;
			ArrayList patwariList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();

			try {
				dbUtility = new DBUtility();
				if(lang.equalsIgnoreCase("hi"))
				{
					 dbUtility.createPreparedStatement(CommonSQL.PATWARI_CHECKER_HI);
					
				}
				else
				{
					 dbUtility.createPreparedStatement(CommonSQL.PATWARI_CHECKER);
					
				}
				
				System.out.println(CommonSQL.PATWARI_CHECKER);
				try
				{	
					String sd[] = new String[2];
					sd[0] = String.valueOf(tehID);
					sd[1] = String.valueOf(guideID);
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
					patwari = new GuidelineDTO();
					
					logger.debug("#######"+list1.get(0));
					patwari.setPatwariID((String)list1.get(0)+"~"+(String)list1.get(3));
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
		 * @param tehID
		 * @param guideID
		 * @return ArrayList that holds list of Patwari Halkas for particular guideline for which Checker has  
		 * already approved the guideline Data
		 */
		public ArrayList getCompletePatwariListChecker(int tehID, long guideID, String lang)
		{
			GuidelineDTO  patwari;
			ArrayList patwariList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();

			try {
				dbUtility = new DBUtility();
				if(lang.equalsIgnoreCase("hi"))
				{
					dbUtility.createPreparedStatement(CommonSQL.COMPLETE_PATWARI_CHECKER_HI);
					
				}
				else
				{
					dbUtility.createPreparedStatement(CommonSQL.COMPLETE_PATWARI_CHECKER);
				
				}
				
				
				try
				{	
					String sd[] = new String[4];
					sd[0] = String.valueOf(tehID);
					sd[1] = String.valueOf(guideID);
					sd[2] = String.valueOf(tehID);
					sd[3] = String.valueOf(guideID);
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
					patwari = new GuidelineDTO();
					
					logger.debug("#######"+list1.get(0));
					patwari.setPatwariID((String)list1.get(0)+"~"+(String)list1.get(3));
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
 * @param wardId
 * @param guideId
 * @return  ArrayList that holds list of mohallas for particular guideline for which Checker has not
 * yet approved the guideline Data
*/
		public ArrayList getPendingMohallaListChecker(int wardId, long guideId, String lang)
		{
			GuidelineDTO  mohalla ;
			ArrayList mohList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();

			try {
					dbUtility = new DBUtility();
					if(lang.equalsIgnoreCase("hi"))
					{
						dbUtility.createPreparedStatement(CommonSQL.MOHALLA_CHECKER_HI);
						
					}
					else
					{
						dbUtility.createPreparedStatement(CommonSQL.MOHALLA_CHECKER);
						
					}
					
					try
						{
						String sd[] = new String[2];
						sd[0] = String.valueOf(wardId);
						sd[1] = String.valueOf(guideId);
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
					mohalla = new GuidelineDTO();
				//	mohalla.setBasePeriodFrom((String)list1.get(0));
				//	mohalla.setBasePeriodTo((String)list1.get(1));	
					mohalla.setMohallaID((String)list1.get(0)+"~"+(String)list1.get(3));
					if(lang.equalsIgnoreCase("en"))
					{
						mohalla.setMohalla((String)list1.get(1));
						//	mohalla.setStatus((String)list1.get(4));
							mohalla.setHdnMohalla((String)list1.get(0)+"="+(String)list1.get(1));
					}
					else
					{
						mohalla.setMohalla((String)list1.get(2));
						//	mohalla.setStatus((String)list1.get(4));
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
 * 		
 * @param wardId
 * @param guideId
 * @return ArrayList that holds list of mohallas for particular guideline for which Checker has  
 *  already approved the guideline Data
 */
		public ArrayList getCompleteMohallaListChecker(int wardId, long guideId, String lang)
		{
			GuidelineDTO  mohalla ;
			ArrayList mohList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();
			try {
				dbUtility = new DBUtility();
				if(lang.equalsIgnoreCase("hi"))
				{
					dbUtility.createPreparedStatement(CommonSQL.COMPLETE_MOHALLA_CHECKER_HI);
					
				}
				else
				{
					dbUtility.createPreparedStatement(CommonSQL.COMPLETE_MOHALLA_CHECKER);
					
				}
				
				try
				{	
					String sd[] = new String[4];
					sd[0] = String.valueOf(wardId);
					sd[1] = String.valueOf(guideId);
					sd[2] = String.valueOf(wardId);
					sd[3] = String.valueOf(guideId);
					list2 =dbUtility.executeQuery(sd);
							
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
					mohalla = new GuidelineDTO();
				//	mohalla.setBasePeriodFrom((String)list1.get(0));
				//	mohalla.setBasePeriodTo((String)list1.get(1));	
					mohalla.setMohallaID((String)list1.get(0)+"~"+(String)list1.get(3));
					if(lang.equalsIgnoreCase("en"))
					{
						mohalla.setMohalla((String)list1.get(1));
						//	mohalla.setStatus((String)list1.get(4));
							mohalla.setHdnMohalla((String)list1.get(0)+"="+(String)list1.get(1));
					}
					else
					{
						mohalla.setMohalla((String)list1.get(2));
						//	mohalla.setStatus((String)list1.get(4));
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
		 * 
		 * @param guideID
		 * @param mohID
		  @return ArrayList that holds Guideline Data for a particular mohalla which is pending for approval
		 */
		public ArrayList guideLineViewChecker(long guideID, String mohID, String lang)
		{
			GuidelineDTO  mohallaDetails ;
			ArrayList mohallaDetailsList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();
			ArrayList listDerived = new ArrayList();
			ArrayList listTemp = new ArrayList();
			ArrayList list4 = new ArrayList();
			ArrayList list3 = new ArrayList();
			String derivedFromGuideline ="";
			String derivedFrom = "";
			String mohArr[] = mohID.split("~");
			try {
				//dbUtil = new DBUtility();

				//dbUtil.createPreparedStatement(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS);
				//logger.debug(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS);
				dbUtility = new DBUtility();
				
				
				 dbUtility.createPreparedStatement(CommonSQL.GUIDELINE_VIEW_APPROVAL);
				//list2 = dbUtil.executeQuery(param);
				logger.debug("GUIDE"+guideID);
				logger.debug("GUIDE"+mohID);
				
				try
				{
					String sd[] = new String[3];
					sd[0] = String.valueOf(guideID);
					sd[1] = mohArr[1];
					sd[2] = mohArr[0];
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
		        	logger.debug("DAO- executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        
		        
//////////////////added on 26 july////////////////////
			      
		        //st =
					 //connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		    	 
				try {
					dbUtility.createPreparedStatement(CommonSQL.GET_DERIVED_GUIDELINE_DETLS);
					String sd1[] = new String[1];
					sd1[0] = String.valueOf(guideID);
					list3 = dbUtility.executeQuery(sd1);
					
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
		        list3.trimToSize();
		        logger.debug("SIZE OF DERIVED DETAILS LIST"+list3.size());
		        for(int j = 0 ; j < list3.size(); j++)
		        {
		        	listDerived = (ArrayList)list3.get(j);
		        	derivedFrom = (String)listDerived.get(0);
		        	derivedFromGuideline = (String)listDerived.get(1);
		        	logger.debug("**************derived from***"+derivedFrom);
		        	logger.debug("**************derived from***"+derivedFromGuideline);
		        }
	            
		        if(derivedFrom.equals("2"))
		        {
		        	 dbUtility.createPreparedStatement(CommonSQL.GUIDELINE_VIEW_APPROVAL);
		        	 
		        }
		        else if(derivedFrom.equals("3"))
		        {
		        	 dbUtility.createPreparedStatement(CommonSQL.GET_GUIDELINE_VIEW_FINAL_DETAILS);
		        	
		        }
		       if(derivedFrom.equals("2")||derivedFrom.equals("3"))
		       {
		    	   try {
		    		   String sd2[] = new String[3];
						sd2[0] = derivedFromGuideline;
						sd2[1] = mohArr[1];
						sd2[2] = mohArr[0];
						list4 = dbUtility.executeQuery(sd2);
		               
		                
		           
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
		        
		        list4.trimToSize();
		        if(list4.size() > 0)
		        {
		        	logger.debug("SIZE IN DAO"+list2.size());
			        logger.debug("SIZE IN DAO"+list4.size());
					for (int i = 0; i < list2.size(); i++) {
						list1 = (ArrayList)list2.get(i);
					    logger.debug("******************");
						listTemp = (ArrayList)list4.get(i);
					    logger.debug("********************");
						mohallaDetails = new GuidelineDTO();
						logger.debug("P%%"+(String)list1.get(0));
						logger.debug("P%%"+(String)listTemp.get(0));
						logger.debug("P1%%"+(String)list1.get(1));
						logger.debug("P1%%"+(String)listTemp.get(1));
						logger.debug("P2%%"+(String)list1.get(2));
						logger.debug("P2%%"+(String)listTemp.get(2));
						logger.debug("V1%%"+(String)list1.get(3));
						logger.debug("V1%%"+(String)listTemp.get(3));
		
						
						mohallaDetails.setGuideLineValue((String)list1.get(3));
						//mohallaDetails.setAverageValue((String)list1.get(4));
						//mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
						//mohallaDetails.setMohallaID((String)list1.get(6));
						//logger.debug("mohalla id:-   "+(String)list1.get(6));
						//logger.debug("mohalla:-   "+(String)list1.get(7));
						//mohallaDetails.setMohalla((String)list1.get(7));
						//mohallaDetails.setGuideLineID((String)list1.get(8));
						//mohallaDetails.setAreaTypeID((String)list1.get(9));
						mohallaDetails.setPropertyID((String)list1.get(7));
						mohallaDetails.setL1_ID((String)list1.get(8));
						mohallaDetails.setL2_ID((String)list1.get(9));
						
						mohallaDetails.setUomID((String)list1.get(11));
						mohallaDetails.setHdnPropTypes((String)list1.get(7)+"~"+(String)list1.get(8)+"~"+(String)list1.get(9));
						
						if(((String)list1.get(3)).equals(((String)listTemp.get(3))))
						{
							mohallaDetails.setCheckModify("N");
						}
						else
						{
							mohallaDetails.setCheckModify("Y");
						}
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
		        }
		        else
		        {
		        	logger.debug("SIZE IN DAO"+list2.size());
					for (int i = 0; i < list2.size(); i++) {
						list1 = (ArrayList)list2.get(i);
						mohallaDetails = new GuidelineDTO();

						//mohallaDetails.setPropertyTypeName((String)list1.get(0));
						//mohallaDetails.setL1Name((String)list1.get(1));
						//mohallaDetails.setL2Name((String)list1.get(2));
						mohallaDetails.setGuideLineValue((String)list1.get(3));
						//mohallaDetails.setAverageValue((String)list1.get(4));
						//mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
						//mohallaDetails.setMohallaID((String)list1.get(6));
						//logger.debug("mohalla id:-   "+(String)list1.get(6));
						//logger.debug("mohalla:-   "+(String)list1.get(7));
						//mohallaDetails.setMohalla((String)list1.get(7));
						//mohallaDetails.setGuideLineID((String)list1.get(8));
						//mohallaDetails.setAreaTypeID((String)list1.get(9));
						mohallaDetails.setPropertyID((String)list1.get(7));
						mohallaDetails.setL1_ID((String)list1.get(8));
						mohallaDetails.setL2_ID((String)list1.get(9));
						//mohallaDetails.setUomName((String)list1.get(10));
						mohallaDetails.setUomID((String)list1.get(11));
						mohallaDetails.setHdnPropTypes((String)list1.get(7)+"~"+(String)list1.get(8)+"~"+(String)list1.get(9));
						mohallaDetails.setCheckModify("Y");
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
				
		        }
		        
		      }
		       else
		       {
		    	   logger.debug("SIZE IN DAO"+list2.size());
					for (int i = 0; i < list2.size(); i++) {
						list1 = (ArrayList)list2.get(i);
						mohallaDetails = new GuidelineDTO();

		/*				mohallaDetails.setGuideLineID((String)list1.get(0));
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
						logger.debug("(String)list1.get(19):-        "+(String)list1.get(19));
		*/
						//mohallaDetails.setPropertyTypeName((String)list1.get(0));
						//mohallaDetails.setL1Name((String)list1.get(1));
						//mohallaDetails.setL2Name((String)list1.get(2));
						mohallaDetails.setGuideLineValue((String)list1.get(3));
						//mohallaDetails.setAverageValue((String)list1.get(4));
						//mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
						//mohallaDetails.setMohallaID((String)list1.get(6));
						//logger.debug("mohalla id:-   "+(String)list1.get(6));
						//logger.debug("mohalla:-   "+(String)list1.get(7));
						//mohallaDetails.setMohalla((String)list1.get(7));
						//mohallaDetails.setGuideLineID((String)list1.get(8));
						//mohallaDetails.setAreaTypeID((String)list1.get(9));
						mohallaDetails.setPropertyID((String)list1.get(7));
						mohallaDetails.setL1_ID((String)list1.get(8));
						mohallaDetails.setL2_ID((String)list1.get(9));
						//mohallaDetails.setUomName((String)list1.get(10));
						mohallaDetails.setUomID((String)list1.get(11));
						mohallaDetails.setHdnPropTypes((String)list1.get(7)+"~"+(String)list1.get(8)+"~"+(String)list1.get(9));
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
				
		        
		        
		       
				}
			}
			 catch (Exception x) {
				logger.debug(x);

			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
				}
			}
			logger.debug("SIZE BEFORE RETURNING"+mohallaDetailsList.size());
			return mohallaDetailsList;
		}
		
		//////////////////////BELOW CODE COMMENTED BY SIMRAN////////////////////////////////////////////
		
	/*public boolean validateTempDraft(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, 
				String dTo,String roleId, String funId, String userId)
		{
			
			logger.debug("DAO:-  VALIDATETempMohallaDetails");
			
			boolean  j = false;
			boolean mohallaUpdated = false;
			String dataFound = null;
			IGRSCommon igrsCommon = null;
			try {
					
					igrsCommon = new IGRSCommon();
					
				
				dbUtility = new DBUtility();
				
				clstmt = connTest.prepareCall(CommonSQL.VALIDATE_DRAFT_PROCEDURE);
				//clstmt = connTest.prepareCall("Call {igrs_validate_draft_guideline(?,?,?,?,?,?)");
				
				try
				{
					
					
					clstmt.setString(1, gDTO.getFinancialYear());
					clstmt.setInt(2, Integer.parseInt(gDTO.getDistrictID()));
					clstmt.setInt(3,gDTO.getTehID());
					clstmt.setInt(4,gDTO.getWardId());
					clstmt.setInt(5,gDTO.getMohallaId());
					clstmt.setString(6, dFrom);
					clstmt.setString(7, dTo);
					clstmt.registerOutParameter(8, OracleTypes.VARCHAR);
					
					try {
		                j=  clstmt.execute();
		                logger.debug("JJJJJJJJJ"+j);
		                dataFound = clstmt.getString(8);
		                 logger.debug("OUTPUT PARAMETER FROM VALIDATION"+clstmt.getString(8));	
		                 
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
		        
		         catch (Exception x) {
					
					logger.debug("ERROR:-   "+x.getMessage());
				}finally {
					try {
							
							clstmt.close();
							DBUtilityTest.closeConnection();
						
					}catch (Exception xe ) {
						logger.debug("Error In Closing"+xe);
					}
				}
			
			if(dataFound.equalsIgnoreCase("true"))
			{
				mohallaUpdated = false;
			}
			
			else if(dataFound.equalsIgnoreCase("false"))
			{
				//mohallaUpdated = updateTempMohallaDetailsNew(mohallaDetails,  gDTO, dFrom, 
						// dTo, roleId, funId,  userId);
			}
			
			return mohallaUpdated;
		}*/
/**
 * 	This method is used to update guideline Data details in guideline tables
 * after approval	
 * @param mohallaDetails
 * @param gDTO
 * @param dFrom
 * @param dTo
 * @param roleId
 * @param funId
 * @param userId
 * @param subClause
 * @return boolean
 */
public boolean updateTempMohallaDetailsNew(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, 
				String dTo,String roleId, String funId, String userId){  		

			// method to derive values
			mohallaDetails = checkIndustrialRate(mohallaDetails);
			ArrayList mohallaDetailsNew = deriveGuidelineDetails(mohallaDetails);
			logger.debug("DAO:-  updateTempMohallaDetails");
			boolean mohallaInserted = false;
			boolean updateStatus = false;
			boolean updateValidate = false;
			int wardId = 0;
			boolean  j = false;
			IGRSCommon igrsCommon = null;
			try {
					
					igrsCommon = new IGRSCommon();
					
				
				dbUtility = new DBUtility();
				
				//clstmt = connTest.prepareCall("Call {igrs_update_temp_guideline(?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?,?,?,?,?,?,?,?,to_date(?,'dd/mm/yy'),?,?,?,?)}");
				clstmt = dbUtility.createCallableStatement(CommonSQL.UPDATE_DRAFT_PROCEDURE);
				
				
				dbUtility.setAutoCommit(false);
				
				//int guideStatus = CommonConstant.GUIDELINESTATUS_DRAFT;
				
				String guidelineValue= null;

				for(int i = 0;i<mohallaDetailsNew.size();i++ ){
					GuidelineDTO dto = (GuidelineDTO)mohallaDetailsNew.get(i);
					logger.debug("^^^^^^PROPOSED VALUE^^^^^"+dto.getProposedValue());
					//clstmt = connTest.prepareCall("Call {igrs_update_temp_guideline(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				try
				{
					//if(dto.getProposedValue() == 0)
					//{
					//	 guidelineValue = dto.getGuideLineValue();
					//}
					//else
					//{
						guidelineValue =String.valueOf(dto.getProposedValue());
					//}
					
					if(gDTO.getPatwariId() == 0)
					{
						wardId = gDTO.getWardId();
					}
					else 
					{
						wardId = gDTO.getPatwariId();
					}
					//String wardArr[]  = gDTO.getWardID().split("~");
					String mohallaArr[] = gDTO.getMoh().split("~");
					
					clstmt.setString( 1, gDTO.getFinancialYear());
					clstmt.setString(2, gDTO.getDistrictID());
					clstmt.setString( 3, dFrom);
					clstmt.setString( 4, dTo);
					clstmt.setInt( 5, gDTO.getTehID());
					clstmt.setInt( 6, wardId);
					clstmt.setInt( 7, gDTO.getMohallaId());
					clstmt.setString( 8, mohallaArr[1]);
					clstmt.setString( 9, gDTO.getWardID());
					clstmt.setString( 10, "");
					clstmt.setString( 11, "");
					clstmt.setInt( 12, gDTO.getApprovalType());
					
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
					
					/*logger.debug("$$$$"+gDTO.getFinancialYear());
					logger.debug("$$$$"+gDTO.getDistrictID());
					logger.debug("$$$$"+dFrom);
					logger.debug("$$$$"+ dTo);
					logger.debug("$$$$"+gDTO.getTehID());
					logger.debug("$$$$"+ wardId);
					logger.debug("$$$$"+gDTO.getMohallaId());
					logger.debug("$$$$ STATUS"+gDTO.getApprovalType());
					logger.debug("$$$$"+dto.getPropertyID());
					logger.debug("$$$$"+ dto.getL1_ID());
					logger.debug("$$$$"+ dto.getL2_ID());
					logger.debug("$$$$"+dto.getUomID());
					logger.debug("$$$$"+dto.getProposedValue());
					logger.debug("$$$$+GuidelineValue"+guidelineValue);
					
					logger.debug("$$$$"+dto.getAverageValue());
					logger.debug("$$$$"+ dto.getCreatedBy());
					logger.debug("$$$$"+ dto.getCreatedDate());
					logger.debug("$$$$"+ userId);
					logger.debug("$$$$"+ gDTO.getGuideLineID());*/
					
					
					

					
					
					
					//mohallaInserted = dbUtil.executeUpdate(moharray);
				
					
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
		        
		       
		        	
					
					/*if(mohallaInserted) {
						
						
						igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","T",funId,userId,roleId);
					}else {
						
						igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","F",funId,userId,roleId);
					}*/
				}
			} catch (Exception x) {
				
				logger.debug("ERROR:-   "+x.getMessage());
			}finally {
				try {
						
						clstmt.close();
						dbUtility.closeConnection();
					
				}catch (Exception xe ) {
					logger.debug("Error In Closing"+xe);
				}
			}
			logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
			if(mohallaInserted)
			{
				/*boolean subClauseInsertion = subClauseInsertion(subClause,gDTO);
				if(subClauseInsertion)
				{*/
				
				updateValidate  = updateValidateStatus(gDTO);
				if(updateValidate)
				{
					updateStatus = updateStatus( gDTO);
				}
			//}
		}
			return updateStatus;
	}

/**
 * This function is used to change the status(2-DRAFT Approval 3-Final Approval)
 * @param gDTO
 * @return
 */
public boolean updateStatus(GuidelineDTO gDTO){
			int j=0;
			boolean flag = false;
			boolean mohallaUpdated = false;
			ArrayList list2 = new ArrayList();
			boolean flag2  =false;
			try {
				//dbUtil = new DBUtility();
				//dbUtil.createPreparedStatement(CommonSQL.MOHALLA_DETAILS_UPDATE);
			
			dbUtility = new DBUtility();
			
			String sql = CommonSQL.GET_CHECKER_STATUS;
			 dbUtility.createPreparedStatement(sql);
			 String sd[] = new String[1];
				sd[0] = gDTO.getGuideLineID();
				list2 = dbUtility.executeQuery(sd);
		
				ArrayList m = (ArrayList) list2.get(0);
                for (int i = 1; i <= m.size(); i++) {
                   String id = m.get(i).toString();
                   int status = Integer.parseInt(id);
                   if(status == 4)
                   {
                	   flag = true;
                	   break;
                   }
                } 
           
			if(flag == true)
			{
				dbUtility.createPreparedStatement(CommonSQL.UPDATE_GUIDELINE_STATUS_CHECKER_ALL);
				
				try
				{
			
					String sd2[] = new String[1];
					sd2[0] = gDTO.getGuideLineID();
					
					try {
						flag2 = dbUtility.executeUpdate(sd2);
		              
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
			dbUtility.createPreparedStatement(CommonSQL.UPDATE_GUIDELINE_STATUS_CHECKER);
			
			try
			{
				String sd3[] = new String[3];
				sd3[0] = String.valueOf(gDTO.getApprovalType());
				sd3[1] = String.valueOf(gDTO.getMohallaId());
				sd3[2] = gDTO.getGuideLineID();
			
				try {
					flag2 = dbUtility.executeUpdate(sd3);
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
	       if (flag2 == false)
			 mohallaUpdated  = false;
		 	mohallaUpdated  = true;
			}
		 catch (Exception x) {
			 
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
		 * This function is used to update validate status(i.e this data has been approved by checker)
		 * @param gDTO
		 * @return
		 */
public boolean updateValidateStatus(GuidelineDTO gDTO)
		{
			int j = 0;
			boolean flag=false;
			boolean updateStatus = false;
			try
			{
				
				dbUtility = new DBUtility();
			try
			{
				
				//st = connTest.createStatement();
				//String sql = "update igrs_guideline_child1_temp set validate_Status = 2 where guideline_id = '"+gDTO.getGuideLineID()+"' and tehsil_id = '"+gDTO.getTehID()+"' and mohalla_village_id = '"+gDTO.getMohallaId()+"'" ;
				String sql = CommonSQL.UPDATE_CHILD_TEMP;
				logger.debug(sql);
				 dbUtility.createPreparedStatement(sql);
				try {
					String sd[] = new String[3];
					sd[0] = gDTO.getGuideLineID();
					sd[1] = String.valueOf(gDTO.getTehID());
					sd[2] = String.valueOf(gDTO.getMohallaId());
					flag = dbUtility.executeUpdate(sd);
						
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
	        if (flag == false)
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
 * This function is used to check whether there is some other Approved guideline existed
 * in the system for same duration period(overlapping of durations)	
 * @param mohallaDetails
 * @param gDTO
 * @param dFrom
 * @param dTo
 * @param roleId
 * @param funId
 * @param userId
 * @return boolean
 */
public boolean validateGuidelineData(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, 
	String dTo,String roleId, String funId, String userId)
	{
			logger.debug("DAO:-  VALIDATETempMohallaDetails");
			
			boolean  j = false;
			boolean mohallaInserted = false;
			String dataFound = null;
			long guideIdOld =0;
			IGRSCommon igrsCommon = null;
			try {
					igrsCommon = new IGRSCommon();
					dbUtility = new DBUtility();
					int appType = gDTO.getApprovalType();
					logger.debug("APPROVAL TYPE IN VALIDATE---->"+appType);
					if(appType == 2)
					{
						clstmt = dbUtility.createCallableStatement(CommonSQL.VALIDATE_DRAFT_PROCEDURE);
					}
				else
				{
					clstmt = dbUtility.createCallableStatement(CommonSQL.VALIDATE_FINAL_PROCEDURE);
				}
				//clstmt = connTest.prepareCall("Call {igrs_validate_draft_guideline(?,?,?,?,?,?)");
				
				try
				{
					clstmt.setString(1, gDTO.getFinancialYear());
					clstmt.setInt(2, Integer.parseInt(gDTO.getDistrictID()));
					clstmt.setInt(3,gDTO.getTehID());
					clstmt.setInt(4,gDTO.getWardId());
					clstmt.setInt(5,gDTO.getMohallaId());
					clstmt.setString(6, dFrom);
					clstmt.setString(7, dTo);
					clstmt.registerOutParameter(8, OracleTypes.VARCHAR);
					clstmt.registerOutParameter(9, OracleTypes.VARCHAR);
					logger.debug(dFrom);
					logger.debug(dTo);
					logger.debug(gDTO.getFinancialYear());
					logger.debug(gDTO.getDistrictID());
					logger.debug(gDTO.getTehID());
					logger.debug(gDTO.getWardId());
					logger.debug(gDTO.getMohallaId());
					try {
		                if(!clstmt.execute())
		                {
		                	dataFound = clstmt.getString(8);
			                guideIdOld = clstmt.getLong(9);
		                }
		                //logger.debug("JJJJJJJJJ"+j);
		               
		                logger.debug(dataFound);
		                logger.debug(guideIdOld);
		                 logger.debug("OUTPUT PARAMETER FROM VALIDATION"+clstmt.getString(8));	
		                 
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
		        if(dataFound.equalsIgnoreCase("true"))
				{
					gDTO.setGuideIdOld(guideIdOld);
					mohallaInserted = false;
				}
			
				else if(dataFound.equalsIgnoreCase("false"))
				{
					//mohallaInserted = updateTempMohallaDetailsNew(mohallaDetails,  gDTO, dFrom, 
						// dTo, roleId, funId,  userId);
					mohallaInserted = true;
				}
			logger.debug("MOHALLA IN VALIDATE"+mohallaInserted);
			}
		        
		         catch (Exception x) {
					
					logger.debug("ERROR:-   "+x.getMessage());
				}finally {
					try {
							
							clstmt.close();
							dbUtility.closeConnection();
						
					}catch (Exception xe ) {
						logger.debug("Error In Closing"+xe);
					}
				}
			return mohallaInserted;
		}
		
		
	//////////////////////////////BELOW CODE COMMENTED BY SIMRAN////////////////////////////////////////	

/*public String checkChecker(GuidelineDTO gDTO)
		{
			
		ArrayList list2 = new ArrayList();
		String dataFound = null;
		
			try
			{
				dbUtility = new DBUtility();
				pst = connTest.prepareStatement(CommonSQL.CHECK_CHECKER);
					
					try
					{	
							pst.setString(1,gDTO.getGuideLineID());
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
				        
				        logger.debug("SIZE OF LIST2"+list2.size());
				        
				        if(list2.size() == 0)
				        {
				        	dataFound = "false";
				        }
				        
				        else
				        {
				        	dataFound = "true";
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
					pst.close();
					rst.close();
					DBUtilityTest.closeConnection();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			return dataFound;
		}
		
		public String checkChecker2(GuidelineDTO gDTO)
		{
			
		ArrayList list2 = new ArrayList();
		String dataFound = null;
		int wardId;
		
			try
			{
				dbUtility = new DBUtility();
				pst = connTest.prepareStatement(CommonSQL.CHECK_CHECKER2);
					
					try
					{	
							
						if(gDTO.getPatwariId() == 0)
						{
							wardId = gDTO.getWardId();
						}
						else 
						{
							wardId = gDTO.getPatwariId();
						}
						pst.setString(1,gDTO.getGuideLineID());
						pst.setInt(2, gDTO.getTehID());
						pst.setInt(3, wardId);
						pst.setInt(4, gDTO.getMohallaId());
							
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
				        
				        logger.debug("SIZE OF LIST2"+list2.size());
				        
				        if(list2.size() == 0)
				        {
				        	dataFound = "false";
				        }
				        
				        else
				        {
				        	dataFound = "true";
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
					pst.close();
					rst.close();
					DBUtilityTest.closeConnection();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			return dataFound;
		}
		
public boolean insertFinalMohallaMasterDetailsNew(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, String dTo,String roleId, String funId, String userId){  
		
	ArrayList list2 = new ArrayList();
	String dataFound = null;
	String dataFound2 = null;
	
	logger.debug("DAO:-  FINALMohallaDetails");
	boolean mohallaInserted = false;
	boolean  j = false;
	String guidelineValue = null;
	int wardId;
	IGRSCommon igrsCommon = null;
	try
		{
					dbUtility = new DBUtility();
					clstmt = connTest.prepareCall(CommonSQL.INSERT_FINAL_PROCEDURE);
					
				
					int guideStatus = CommonConstant.GUIDELINESTATUS_FINAL;
					logger.debug("MOHALLA SIZE"+mohallaDetails.size());
					for(int i = 0;i<mohallaDetails.size();i++ ){
					GuidelineDTO dto = (GuidelineDTO)mohallaDetails.get(i);
					//clstmt = connTest.prepareCall("Call {igrs_update_temp_guideline(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
					try
					{
						dataFound = checkChecker( gDTO);
						dataFound2 = checkChecker2(gDTO);
						logger.debug("DATA FOUND"+dataFound);
						logger.debug("DATA FOUND 2"+dataFound2);
						
						if(dto.getProposedValue() == 0)
						{
							 guidelineValue = dto.getGuideLineValue();
						}
						else
						{
							guidelineValue =String.valueOf(dto.getProposedValue());
						}
						
						if(gDTO.getPatwariId() == 0)
						{
							wardId = gDTO.getWardId();
						}
						else 
						{
							wardId = gDTO.getPatwariId();
						}
					clstmt.setString(1, dataFound);
					clstmt.setString(2, dataFound2);
					clstmt.setString( 3, gDTO.getFinancialYear());
					clstmt.setString(4, gDTO.getDistrictID());
					clstmt.setString( 5, dFrom);
					clstmt.setString( 6, dTo);
					clstmt.setInt( 7, gDTO.getTehID());
					clstmt.setInt( 8, wardId);
					clstmt.setInt( 9, gDTO.getMohallaId());
					clstmt.setInt( 10, guideStatus);
					clstmt.setString( 11, gDTO.getBasePeriodFrom());
					clstmt.setString( 12, gDTO.getBasePeriodTo());
					clstmt.setString( 13, dto.getPropertyID());
					clstmt.setString( 14, dto.getL1_ID());
					clstmt.setString( 15, dto.getL2_ID());
					clstmt.setString( 16, dto.getUomID());
					clstmt.setString( 17, ""+guidelineValue);
					clstmt.setString( 18, dto.getAverageValue());
					clstmt.setString( 19, ""+dto.getIncrementValue());
					clstmt.setString( 20, "G");
					
					clstmt.setString( 21, dto.getCreatedBy());
					clstmt.setString( 22, dto.getCreatedDate());
					clstmt.setString( 23, userId);
					clstmt.setString( 24, gDTO.getGuideLineID());
					clstmt.registerOutParameter(25, OracleTypes.VARCHAR);
					
					logger.debug("$$$$"+gDTO.getFinancialYear());
					logger.debug("$$$$"+gDTO.getDistrictID());
					logger.debug("$$$$"+dFrom);
					logger.debug("$$$$"+ dTo);
					logger.debug("$$$$"+gDTO.getTehID());
					logger.debug("$$$$"+ gDTO.getWardId());
					logger.debug("$$$$"+gDTO.getMohallaId());
					logger.debug("$$$$"+guideStatus);
					logger.debug("$$$$"+gDTO.getBasePeriodFrom());
					logger.debug("$$$$"+gDTO.getBasePeriodTo());
					logger.debug("$$$$"+ dto.getL1_ID());
					logger.debug("$$$$"+ dto.getL2_ID());
					logger.debug("$$$$"+dto.getUomID());
					logger.debug("$$$$"+dto.getProposedValue());
					
					logger.debug("$$$$"+dto.getAverageValue());
					logger.debug("$$$$"+ dto.getCreatedBy());
					logger.debug("$$$$"+ dto.getCreatedDate());
					logger.debug("$$$$"+ userId);
					logger.debug("$$$$"+ gDTO.getGuideLineID());
				
					
					try {
		                 if( !clstmt.execute())
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
				if(mohallaInserted) {
						
						igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","T",funId,userId,roleId);
					}else {
						
						igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","F",funId,userId,roleId);
					}
				}
			} catch (Exception x) {
				
				logger.debug("ERROR:-   "+x.getMessage());
			}finally {
				try {
						
						clstmt.close();
						pst.close();
						DBUtilityTest.closeConnection();
					
				}catch (Exception xe ) {
					logger.debug("Error In Closing"+xe);
				}
			}
			logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
			
			updateStatusFinal(gDTO);
			return mohallaInserted;
		}
		


public boolean updateStatusFinal(GuidelineDTO gDTO)
{
	
	int j=0;
	boolean mohallaUpdated = false;
	try {
		//dbUtil = new DBUtility();
		//dbUtil.createPreparedStatement(CommonSQL.MOHALLA_DETAILS_UPDATE);
	
	dbUtility = new DBUtility();
	
	pst = connTest.prepareStatement(CommonSQL.UPDATE_GUIDELINE_STATUS_CHECKER_FINAL);
	
	try
	{
		//pst.setString( 1, dto.getDistrictID());
		pst.setInt( 1, gDTO.getMohallaId());
		pst.setString( 2, gDTO.getGuideLineID());
		
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
	 mohallaUpdated  = false;
 	mohallaUpdated  = true;
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
*/

/////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * 
 * @param guideID
 * @param mohID
 * @return ArrayList that holds guideline data for a particular mohalla that has been approved
 */
public ArrayList guideLineViewCheckerComplete( long guideID, String mohID, String lang)
		{
			GuidelineDTO  mohallaDetails ;
			ArrayList mohallaDetailsList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();
			ArrayList list3 = new ArrayList(); 
			String mohArr[] = mohID.split("~"); 
			try {
				//dbUtil = new DBUtility();

				//dbUtil.createPreparedStatement(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS);
				//logger.debug(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS);
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.GUIDELINE_VIEW_APPROVAL_DRAFT);
				

				//list2 = dbUtil.executeQuery(param);
				logger.debug("GUIDE"+guideID);
				logger.debug("GUIDE"+mohID);
				
				try
				{
					String sd[] = new String[3];
					sd[0] = String.valueOf(guideID);
					sd[1] = mohArr[1];
					sd[2] = mohArr[0];
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
		        	logger.debug("DAO- executeQuery(String[]): " +
		                               iae.getMessage());
		            throw iae;
		        }
		        list2.trimToSize();
		        logger.debug("SIZE IN DAO"+list2.size());
		        
		       /* if(list2.size() == 0)
		        {
		        	
						pst = connTest.prepareStatement(CommonSQL.GUIDELINE_VIEW_APPROVAL_FINAL);

						logger.debug("GUIDE"+guideID);
						logger.debug("GUIDE"+mohID);
						
						try
						{
							pst.setLong(1, guideID);
						
							pst.setInt(2, mohID);
				            rst = pst.executeQuery();
				            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
				            ResultSetMetaData rsmd = rst.getMetaData();
				            int col_count = rsmd.getColumnCount();
				            while (rst.next()) {
				                ArrayList row_list = new ArrayList();
				                for (int i = 1; i <= col_count; i++) {
				                    row_list.add(rst.getString(i));
				                } //for
				                list3.add(row_list);
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
				        list3.trimToSize();
				        logger.debug("SIZE IN DAO"+list3.size());
				        for (int i = 0; i < list3.size(); i++) {
				        	
							list1 = (ArrayList)list3.get(i);
							mohallaDetails = new GuidelineDTO();
		
			
							mohallaDetails.setPropertyTypeName((String)list1.get(0));
							mohallaDetails.setL1Name((String)list1.get(1));
							mohallaDetails.setL2Name((String)list1.get(2));
							mohallaDetails.setGuideLineValue((String)list1.get(3));
							mohallaDetails.setAverageValue((String)list1.get(4));
							mohallaDetails.setIncrementValue(Integer.parseInt((String)list1.get(5)));
							mohallaDetails.setMohallaID((String)list1.get(6));
							
							mohallaDetails.setPropertyID((String)list1.get(7));
							mohallaDetails.setL1_ID((String)list1.get(8));
							mohallaDetails.setL2_ID((String)list1.get(9));
							mohallaDetails.setUomName((String)list1.get(10));
							mohallaDetails.setUomID((String)list1.get(11));
							
							mohallaDetailsList.add(mohallaDetails);
				        
		        }
		        }
		        
		        else
		        	{*/
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
		
		/////////////////////////BELOW CODE COMMENTED BY SIMRAN/////////////////////////////////////////////
		/*public boolean updateDraftToFinal(ArrayList mohallaDetails, GuidelineDTO gDTO, String dFrom, String dTo,String roleId, String funId, String userId)
		{
			ArrayList list2 = new ArrayList();
			String dataFound = null;
			String dataFound2 = null;
			
			logger.debug("DAO:-  FINALMohallaDetails");
			boolean mohallaInserted = false;
			boolean  j = false;
			String guidelineValue = null;
			int wardId;
			IGRSCommon igrsCommon = null;
			try
				{
							dbUtility = new DBUtility();
							clstmt = connTest.prepareCall(CommonSQL.UPDATE_FINAL_PROCEDURE);
							
						
							int guideStatus = CommonConstant.GUIDELINESTATUS_FINAL;
							logger.debug("MOHALLA SIZE"+mohallaDetails.size());
							for(int i = 0;i<mohallaDetails.size();i++ ){
							GuidelineDTO dto = (GuidelineDTO)mohallaDetails.get(i);
							//clstmt = connTest.prepareCall("Call {igrs_update_temp_guideline(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
							try
							{
								//dataFound = checkChecker( gDTO);
								//dataFound2 = checkChecker2(gDTO);
								//logger.debug("DATA FOUND"+dataFound);
								//logger.debug("DATA FOUND 2"+dataFound2);
								
								if(dto.getProposedValue() == 0)
								{
									 guidelineValue = dto.getGuideLineValue();
								}
								else
								{
									guidelineValue =String.valueOf(dto.getProposedValue());
								}
								
								if(gDTO.getPatwariId() == 0)
								{
									wardId = gDTO.getWardId();
								}
								else 
								{
									wardId = gDTO.getPatwariId();
								}
							
							clstmt.setString( 1, gDTO.getFinancialYear());
							clstmt.setString(2, gDTO.getDistrictID());
							clstmt.setString( 3, dFrom);
							clstmt.setString( 4, dTo);
							clstmt.setInt( 5, gDTO.getTehID());
							clstmt.setInt( 6, wardId);
							clstmt.setInt( 7, gDTO.getMohallaId());
							clstmt.setInt( 8, guideStatus);
							clstmt.setString(9, gDTO.getBasePeriodFrom());
							clstmt.setString( 10, gDTO.getBasePeriodTo());
							clstmt.setString( 11, dto.getPropertyID());
							clstmt.setString( 12, dto.getL1_ID());
							clstmt.setString( 13, dto.getL2_ID());
							clstmt.setString( 14, dto.getUomID());
							clstmt.setString( 15, ""+guidelineValue);
							clstmt.setString( 16, dto.getAverageValue());
							clstmt.setString( 17, ""+dto.getIncrementValue());
							clstmt.setString( 18, "G");
							
							clstmt.setString( 19, dto.getCreatedBy());
							clstmt.setString( 20, dto.getCreatedDate());
							clstmt.setString( 21, userId);
							clstmt.setString( 22, gDTO.getGuideLineID());
							clstmt.registerOutParameter(23, OracleTypes.VARCHAR);
							clstmt.registerOutParameter(24, OracleTypes.VARCHAR);
							
							logger.debug("$$$$"+gDTO.getFinancialYear());
							logger.debug("$$$$"+gDTO.getDistrictID());
							logger.debug("$$$$"+dFrom);
							logger.debug("$$$$"+ dTo);
							logger.debug("$$$$"+gDTO.getTehID());
							logger.debug("$$$$"+ gDTO.getWardId());
							logger.debug("$$$$"+gDTO.getMohallaId());
							logger.debug("$$$$"+guideStatus);
							logger.debug("$$$$"+gDTO.getBasePeriodFrom());
							logger.debug("$$$$"+gDTO.getBasePeriodTo());
							logger.debug("$$$$"+ dto.getL1_ID());
							logger.debug("$$$$"+ dto.getL2_ID());
							logger.debug("$$$$"+dto.getUomID());
							logger.debug("$$$$"+dto.getProposedValue());
							
							logger.debug("$$$$"+dto.getAverageValue());
							logger.debug("$$$$"+ dto.getCreatedBy());
							logger.debug("$$$$"+ dto.getCreatedDate());
							logger.debug("$$$$"+ userId);
							logger.debug("$$$$"+ gDTO.getGuideLineID());
						
							
							try {
				                 if( !clstmt.execute())
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
						if(mohallaInserted) {
								
								igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","T",funId,userId,roleId);
							}else {
								
								igrsCommon.saveLogDet("igrs_guideline_data_temp","UPDATE","F",funId,userId,roleId);
							}
						}
					} catch (Exception x) {
						
						logger.debug("ERROR:-   "+x.getMessage());
					}finally {
						try {
								
								clstmt.close();
								pst.close();
								DBUtilityTest.closeConnection();
							
						}catch (Exception xe ) {
							logger.debug("Error In Closing"+xe);
						}
					}
					logger.debug("GuideLineDAO:: MOHALLA INSERTED:-  "+mohallaInserted);
					
					updateStatusFinal(gDTO);
					return mohallaInserted;
		}
		public ArrayList getIndividualMohallaDetails2() {

			logger.debug("DAO: Called: getIndividualMohallaDetails2()");

			GuidelineDTO  mohallaDetails ;
			ArrayList mohallaDetailsList = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();
			try {
				//dbUtil = new DBUtility();
				//dbUtil.createStatement();
				//logger.debug(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS_NOVALUE);
				//list2 = dbUtil.executeQuery(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS_NOVALUE);
				
				dbUtility = new DBUtility();
				st =
					 connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				 try {
			            rst = st.executeQuery(CommonSQL.INDIVIDUAL_MOHALLA_DETAILS_NOVALUE);
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
			        logger.debug(list2.size());
			        
			      for (int i = 0; i < list2.size(); i++) {
					list1 = (ArrayList)list2.get(i);
					mohallaDetails = new GuidelineDTO();
					mohallaDetails.setPropertyID((String)list1.get(0));
					mohallaDetails.setPropertyTypeName((String)list1.get(1));
					mohallaDetails.setL1_ID((String)list1.get(2));
					mohallaDetails.setL1Name((String)list1.get(3));
					mohallaDetails.setL2_ID((String)list1.get(4));
					mohallaDetails.setL2Name((String)list1.get(5));
					mohallaDetails.setUomID((String)list1.get(6));
					mohallaDetails.setUomName((String)list1.get(7));

					mohallaDetailsList.add(mohallaDetails);
					logger.debug(mohallaDetailsList.size());
				}
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
						rst.close();
						st.close();
						DBUtilityTest.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
			return mohallaDetailsList;
		}*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
/**
 * This function is used to update duration period in case of overlapping guidelines
 * @param gDTO
 * @param dFrom
 * @return boolean
 */
public boolean updateDurationPeriod(GuidelineDTO gDTO, String dFrom)
	{
			int j = 0;
			boolean dateUpdated = false;
			String prev = null;
			boolean flag=false;
			int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
			try
			{
				logger.debug(dFrom);
				Date dFrom1 = dateFormat.parse(dFrom);
				logger.debug(dFrom1);
				prev = dateFormat.format(dFrom1.getTime() -MILLIS_IN_DAY);
				logger.debug("Previous Date"+prev);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				dbUtility = new DBUtility();
				if(gDTO.getApprovalType() == 2)
				{
					dbUtility.createPreparedStatement(CommonSQL.UPDATE_END_DATE);
					
				}
				else
				{
					dbUtility.createPreparedStatement(CommonSQL.UPDATE_END_DATE_MASTER);
					
				}
				
				//dbUtility = new DBUtility();
			try
			{
				String sd[] = new String[2];
				sd[0] = prev;
				sd[1] = String.valueOf(gDTO.getGuideIdOld());
				
				//pst.setString( 1, dto.getDistrictID());
			
				
				
				try {
					flag = dbUtility.executeUpdate(sd);
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
	       if (flag == false)
	    	   	dateUpdated  = false;
		 		dateUpdated  = true;
			}
		 catch (Exception x) {
			 
			logger.debug(""+x.getMessage());
		}finally {
			try {
				dbUtility.closeConnection();
					
			}catch (Exception xe ) {
				logger.debug("Close Connection Error:-"+xe);
				xe.printStackTrace();
			}
		}
	return dateUpdated;
			
}
/**
 * 
 * @param guideID
 * @return ArrayList that holds list of tehsils/wards/mohallas for which maker has not entered data yet
 */
	public ArrayList checkPublishMakerStatus(long guideID)
	{
		ArrayList combination = new ArrayList();
		GuidelineDTO tehsil_mohalla; 
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.NOT_ENTERED_DATA);
		
			
			try
			{	
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
				tehsil_mohalla = new GuidelineDTO();
				logger.debug("#######"+list1.get(0));
				tehsil_mohalla.setTehsil((String)list1.get(0));
				tehsil_mohalla.setWard((String)list1.get(1));
				tehsil_mohalla.setMohalla((String)list1.get(2));
				
				combination.add(tehsil_mohalla);
				
			}
			logger.debug("SIZE IN MAKER"+combination);
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
		
		return combination;
	}
	
	/**
	 * 
	 * @param guideID
	 * @return ArrayList that holds tehsils/wards/mohallas for which checker has not validated data yet
	 */
	public ArrayList checkPublishValidateStatus(long guideID)
	{
		ArrayList combination = new ArrayList();
		GuidelineDTO tehsil_mohalla; 
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			
			dbUtility = new DBUtility();
			
			
			dbUtility.createPreparedStatement(CommonSQL.NOT_VALIDATED_DATA);
			try
			{	
				
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
				tehsil_mohalla = new GuidelineDTO();
				logger.debug("#######"+list1.get(0));
				tehsil_mohalla.setTehsil((String)list1.get(0));
				tehsil_mohalla.setWard((String)list1.get(1));
				tehsil_mohalla.setMohalla((String)list1.get(2));
				
				combination.add(tehsil_mohalla);
			}
			logger.debug("SIZE IN MAKER"+combination);
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
		
		return combination;
	}
	
	/**
	 * This table is used to update publish status for guideline(2-DRAFT, 3-FINAl)
	 * @param guideID
	 * @param appType
	 * @return boolean
	 */
	public boolean updatePublishStatus(long guideID, int appType)
	{
		int j = 0;
		boolean updateStatus = false;
		boolean flag=false;
		try
		{
			
			dbUtility = new DBUtility();
		try
		{
			
			//st = connTest.createStatement();
			//String sql = "update igrs_guideline_parent_temp set publish_status = '"+appType+"' where guideline_id = '"+guideID+"'" ;
			String sql = CommonSQL.UPDATE_PARENT_TEMP;
			dbUtility.createPreparedStatement(sql);
			
			try {
				String sd[] = new String[2];
				sd[0] = String.valueOf(appType);
				sd[1] = String.valueOf(guideID);
				flag = dbUtility.executeUpdate(sd);
                	
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
        if (flag == false)
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
	 * This table is used to update publish status for guideline(2-DRAFT, 3-FINAl)
	 * @param guideID
	 * @param appType
	 * @return boolean
	 */
	public boolean updateStatus(String guideID)
	{
		int j = 0;
		boolean flag=false;
		boolean updateStatus = false;
		try
		{
			
			dbUtility = new DBUtility();
		try
		{
			
			//st = connTest.createStatement();
			//String sql = "update igrs_guideline_parent_temp set publish_status = '"+appType+"' where guideline_id = '"+guideID+"'" ;
			String sql = CommonSQL.UPDATE_PARENT_TEMP_NEW;
			 dbUtility.createPreparedStatement(sql);
			

			try {
				String sd[] = new String[1];
				sd[0] = guideID;
				flag = dbUtility.executeUpdate(sd);
             
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
        if (flag == false)
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
	 * This table is used to update publish status for guideline(2-DRAFT, 3-FINAl)
	 * @param guideID
	 * @param appType
	 * @return boolean
	 */
	public boolean updateChild1Status(long guideID, int appType)
	{
		int j = 0;
		boolean flag=false;
		boolean updateStatus = false;
		try
		{
				dbUtility = new DBUtility();
		try
		{
			String sql = CommonSQL.UPDATE_CHILD_TEMP1;
			dbUtility.createPreparedStatement(sql);
		
			//String sql = "update igrs_guideline_child1_temp set status_flag = '"+appType+"' where guideline_id = '"+guideID+"'" ;
			try {
				String sd[] = new String[2];
				sd[0] = String.valueOf(appType);
				sd[1] = String.valueOf(guideID);
				flag = dbUtility.executeUpdate(sd);
                	
                	
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
        if (flag == false)
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

	////////////////////////////////////BELOW CODE COMMENTED BY SIMRAN//////////////////////////////////////////
	/*public ArrayList mappedSubclauses(GuidelineDTO gDTO)
	{
		int wardId = 0;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO map_sub ;
		ArrayList mapSubClauseList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			
			pst = connTest.prepareStatement(CommonSQL.MAP_SUBCLAUSES);
			
			try
			{
				if(gDTO.getWardId() == 0)
				{
					wardId = gDTO.getPatwariId();
				}
				else
				{
					wardId = gDTO.getWardId();
				}
				logger.debug("$$$$$$$$$$$$$$$$$");
				logger.debug(gDTO.getPropertyID());
				logger.debug(gDTO.getL1_ID());
				logger.debug(gDTO.getL2_ID());
				
				pst.setString(1,gDTO.getPropertyID());
				pst.setString(2,gDTO.getL1_ID());
				pst.setString(3,gDTO.getL2_ID());
				pst.setString(4,gDTO.getDistrictID());
				pst.setInt(5, gDTO.getTehID());
				pst.setInt(6,gDTO.getWardId());
				pst.setInt(7,gDTO.getMohallaId());
			
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
				map_sub.setStatus("Y");
				
				
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
	
	/*public ArrayList unMappedSubclauses(GuidelineDTO gDTO)
	{
		int wardId = 0;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO unMap_sub ;
		ArrayList unMapSubClauseList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			
			pst = connTest.prepareStatement(CommonSQL.UNMAP_SUBCLAUSES);
			
			try
			{
				if(gDTO.getWardId() == 0)
				{
					wardId = gDTO.getPatwariId();
				}
				else
				{
					wardId = gDTO.getWardId();
				}
				
				pst.setString(1,gDTO.getPropertyID());
				pst.setString(2,gDTO.getL1_ID());
				pst.setString(3,gDTO.getL2_ID());
				pst.setString(4,gDTO.getDistrictID());
				//pst.setInt(5, gDTO.getTehID());
				//pst.setInt(6,gDTO.getWardId());
				//pst.setInt(7,gDTO.getMohallaId());
				//pst.setString(8,gDTO.getDistrictID());
				
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
				unMap_sub = new GuidelineDTO();
				logger.debug("#######"+list1.get(0));
				unMap_sub.setSubClauseId((String)list1.get(0));
				unMap_sub.setSubClauseName((String)list1.get(1));
				unMap_sub.setStatus("N");
				
				
				unMapSubClauseList.add(unMap_sub);
			}
			logger.debug("SIZE IN MAKER"+unMapSubClauseList.size());
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {

				//rst.close();
				//pst.close();
				DBUtilityTest.closeConnection();
				
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
		return unMapSubClauseList;
	}*/
	
	/*public ArrayList mappedSubclausesTest(GuidelineDTO gDTO)
	{
		int wardId = 0;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO map_sub ;
		ArrayList mapSubClauseList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			
			pst = connTest.prepareStatement(CommonSQL.MAP_TEST);
			
			try
			{
				if(gDTO.getWardId() == 0)
				{
					wardId = gDTO.getPatwariId();
				}
				else
				{
					wardId = gDTO.getWardId();
				}
				//logger.debug("$$$$$$$$$$$$$$$$$");
				//logger.debug(gDTO.getPropertyID());
				//logger.debug(gDTO.getL1_ID());
				//logger.debug(gDTO.getL2_ID());
				
				//pst.setString(1,gDTO.getPropertyID());
				//pst.setString(2,gDTO.getL1_ID());
				//pst.setString(3,gDTO.getL2_ID());
				pst.setString(1,gDTO.getDistrictID());
				pst.setInt(2, gDTO.getTehID());
				pst.setInt(3,gDTO.getWardId());
				pst.setInt(4,gDTO.getMohallaId());
			
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
				map_sub.setPropertyID((String)list1.get(0));
				map_sub.setL1_ID((String)list1.get(1));
				map_sub.setL2_ID((String)list1.get(2));
				map_sub.setSubClauseId((String)list1.get(3));
				map_sub.setSubClauseName((String)list1.get(4));
				map_sub.setStatus("Y");
				
				
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
	
	/*public ArrayList unMappedSubclausesTest(GuidelineDTO gDTO)
	{
		int wardId = 0;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO unMap_sub ;
		ArrayList unMapSubClauseList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			
			pst = connTest.prepareStatement(CommonSQL.UNMAP_SUBCLAUSES);
			
			try
			{
				if(gDTO.getWardId() == 0)
				{
					wardId = gDTO.getPatwariId();
				}
				else
				{
					wardId = gDTO.getWardId();
				}
				
				pst.setString(1,gDTO.getPropertyID());
				pst.setString(2,gDTO.getL1_ID());
				pst.setString(3,gDTO.getL2_ID());
				pst.setString(4,gDTO.getDistrictID());
				//pst.setInt(5, gDTO.getTehID());
				//pst.setInt(6,gDTO.getWardId());
				//pst.setInt(7,gDTO.getMohallaId());
				//pst.setString(8,gDTO.getDistrictID());
				
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
				unMap_sub = new GuidelineDTO();
				logger.debug("#######"+list1.get(0));
				unMap_sub.setSubClauseId((String)list1.get(0));
				unMap_sub.setSubClauseName((String)list1.get(1));
				unMap_sub.setStatus("N");
				
				
				unMapSubClauseList.add(unMap_sub);
			}
			logger.debug("SIZE IN MAKER"+unMapSubClauseList.size());
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {

				//rst.close();
				//pst.close();
				DBUtilityTest.closeConnection();
				
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
		return unMapSubClauseList;
	}*/
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @param gDTO
	 * @return ArrayList that holds list of subclauses 
	 */
	public ArrayList subclausesNew(GuidelineDTO gDTO)
	{
		int wardId = 0;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO map_sub ;
		ArrayList mapSubClauseList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			logger.debug(gDTO.getL2_ID());
			
			if(gDTO.getWardId() == 0)
			{
				wardId = gDTO.getPatwariId();
			}
			else
			{
				wardId = gDTO.getWardId();
			}
			
			if(gDTO.getL2_ID().toString().equals("0"))
			{
				dbUtility.createPreparedStatement(CommonSQL.SUBCLAUSE_NULL);

				String sd[] = new String[2];
				sd[0] = gDTO.getPropertyID();
				sd[1] = gDTO.getL1_ID();
				list2 = dbUtility.executeQuery(sd);
			}
			else
			{
				dbUtility.createPreparedStatement(CommonSQL.SUBCLAUSE);

				String sd[] = new String[3];
				sd[0] = gDTO.getPropertyID();
				sd[1] = gDTO.getL1_ID();
				sd[2] = gDTO.getL2_ID();
				list2 = dbUtility.executeQuery(sd);
			}
			try
			{
				//pst.setString(4,gDTO.getDistrictID());
				//pst.setInt(5, gDTO.getTehID());
				//pst.setInt(6,gDTO.getWardId());
				//pst.setInt(7,gDTO.getMohallaId());
			
				 
		        }  catch (NumberFormatException nfe) {
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
				if(((String)list1.get(3)).equalsIgnoreCase("Y"))
				{
					map_sub.setMapped("Y");
				}
				else
				{
					map_sub.setMapped("N");
				}
				
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
	 * @return ArrayList that holds subClauses for derived Guideline
	 */
	public ArrayList subclauses(GuidelineDTO gDTO)
	{
		int wardId = 0;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO map_sub ;
		ArrayList mapSubClauseList = new ArrayList();
		try
		{
			dbUtility = new DBUtility();
			logger.debug(gDTO.getL2_ID());
			//logger.debug(gDTO.getWardID().toString());
			if(gDTO.getWardId() == 0)
			{
				wardId = gDTO.getPatwariId();
			}
			else
			{
				wardId = gDTO.getWardId();
			}
			
			
			/*if(gDTO.getL2_ID().toString().equals("0"))
			{
				pst = connTest.prepareStatement(CommonSQL.VIEW_DERIVE_NULL);
				pst.setString(1, gDTO.getGuideLineID());
				pst.setString(2, gDTO.getTehsilID());
				pst.setString(3, wardId);
				pst.setString(4, gDTO.getMohallaID());
				pst.setString(5,gDTO.getPropertyID());
				pst.setString(6,gDTO.getL1_ID());
			}
			else
			{*/
				
				 dbUtility.createPreparedStatement(CommonSQL.VIEW_DERIVE);
				 String sd[] = new String[7];
					sd[0] = gDTO.getGuideLineID();
					sd[1] =  String.valueOf(gDTO.getTehID());
					sd[2] = String.valueOf(wardId);
					sd[3] = String.valueOf(gDTO.getMohallaId());
					sd[4] = gDTO.getPropertyID();
					sd[5] = gDTO.getL1_ID();
					sd[6] = gDTO.getL2_ID();
					
			
			//}
			try
			{
				//pst.setString(4,gDTO.getDistrictID());
				//pst.setInt(5, gDTO.getTehID());
				//pst.setInt(6,gDTO.getWardId());
				//pst.setInt(7,gDTO.getMohallaId());
			
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
	 * @param subKeys
	 * @param gDTO
	 * @return HashMap that holds list of subClauses that are derived and has not been modified or changed
	 */
	public HashMap subClauseNotClickedDerived (ArrayList subKeys, GuidelineDTO gDTO)
	{
		String propId = null;
		String propL1Id = null;
		String propL2Id = null;
		
		//String list1 = null;
		//ArrayList list1 = new ArrayList();
		GuidelineDTO sub;
		ArrayList subClauseList = new ArrayList();
		HashMap hMap = new HashMap();
		int wardId =0;
		
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
						
						if(gDTO.getWardId() == 0)
						{
							wardId = gDTO.getPatwariId();
						}
						else
						{
							wardId = gDTO.getWardId();
						}
						
						
						//if(propL2Id == null)
						//{
							//pst = connTest.prepareStatement(CommonSQL.SUBCLAUSE_NOT_CLICKED_NULL);
							//pst.setString(1,propId);
							//pst.setString(2,propL1Id);
							
						//}
						//else
						//{
							
							dbUtility.createPreparedStatement(CommonSQL.DERIVE_SUB_NOTCLICKED);
							String sd[] = new String[7];
							sd[0] = gDTO.getGuideLineID();
							sd[1] = String.valueOf(gDTO.getTehID());
							sd[2] = String.valueOf(wardId);
							sd[3] = String.valueOf(gDTO.getMohallaId());
							sd[4] = propId;
							sd[5] = propL1Id;
							sd[6] = propL2Id;
							
							String id = dbUtility.executeQry(sd);
							
						//}
					
					
					
					
		                	String val = id;
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
	
	/*public ArrayList subclausesTest(GuidelineDTO gDTO)
	{
		int wardId = 0;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		GuidelineDTO map_sub ;
		ArrayList mapSubClauseList = new ArrayList();
		try
		{
			
			dbUtility = new DBUtility();
			st =
				 connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			 try {
		            rst = st.executeQuery(CommonSQL.SUB_PROP);
		            logger.debug("Wipro in DAO - executeQuery() after pst.executeQuery()");
		            
		            ResultSetMetaData rsmd = rst.getMetaData();
		            int col_count = rsmd.getColumnCount();
		            
		            logger.debug(col_count);
		            while (rst.next()) {
		                ArrayList row_list = new ArrayList();
		                //for (int i = 1; i <= col_count; i++) {
		                    String prop_id = rst.getString(1);
		                    String l1_id = rst.getString(2);
		                    String l2_id = rst.getString(3);
		                    logger.debug(prop_id);
		                    logger.debug(l1_id);
		                    logger.debug(l2_id);
		               // } //for
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
				map_sub.setPropertyID((String)list1.get(0));
				map_sub.setL1_ID((String)list1.get(1));
				map_sub.setL2_ID((String)list1.get(2));
				map_sub.setSubClauseId((String)list1.get(3));
				map_sub.setSubClauseName((String)list1.get(4));
				map_sub.setStatus((String)list1.get(5));
				
				
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
	}
	*/
	
	
	/**
	 * This function is used to insert subClause details into SubClause_Approved table after approval
	 * @param subDetails
	 * @param gDTO
	 * @return boolean 
	 */
	public boolean subClauseInsertion(ArrayList subDetails, GuidelineDTO gDTO)
	{
		logger.debug("SIZE of SUB CLAUSE LIST"+subDetails.size());
		int wardId = 0;
		int j = 0;
		boolean flag=false;
		boolean SubClauseInsertion = false;
		
		try {
				
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.SUBCLAUSE_APPROVED);
			if(subDetails.size() > 0)
			{
				for(int i = 0;i<subDetails.size();i++ ){
					GuidelineDTO dto = new GuidelineDTO();
					dto = (GuidelineDTO)subDetails.get(i);
					
					String subId[] = dto.getSubClauseIds();
					
					logger.debug("PROP"+dto.getPropId());
					logger.debug("PROP1"+dto.getPropId1());
					logger.debug("PROP2"+dto.getPropId2());
					//logger.debug("subclause size"+ subId.length);
					
					//logger.debug("SUB"+subId[0]);
					//logger.debug("SUB"+subId[1]);
					int size = (subId == null? 0:subId.length);
					if(gDTO.getPatwariId() == 0)
					{
						wardId = gDTO.getWardId();
					}
					else 
					{
						wardId = gDTO.getPatwariId();
					}
					for(int k = 0 ; k < size;k++)
					{
						logger.debug("subClauseId"+subId[k]);
						subId[k] = subId[k] == null?"":subId[k];
						if("".equalsIgnoreCase(subId[k]) != true)
						{
							logger.debug("subId not null");
							try
							{
								String sd[] = new String[10];
								sd[0] = gDTO.getDistrictID();
								sd[1] = String.valueOf(gDTO.getTehID());
								sd[2] = String.valueOf(wardId);
								sd[3] = String.valueOf(gDTO.getMohallaId());
								sd[4] = dto.getPropId();
								sd[5] = dto.getPropId1();
								sd[6] = dto.getPropId2();
								sd[7] = subId[k];
								sd[8] = "A";
								sd[9] = gDTO.getGuideLineID();
								
								
								
								//mohallaInserted = dbUtil.executeUpdate(moharray);
								
								try {
									flag = dbUtility.executeUpdate(sd);
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
					        
					        if (flag == false)
					        	SubClauseInsertion = false;
					        SubClauseInsertion = true;
						}
						else
						{
							SubClauseInsertion = true;
						}
			
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
	 * @param subId
	 * @return ArrayList that holds subclause name and subclause desc for subclause ids passed as parameter
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
				// connTest.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			for(int j = 0;j< subId.length;j++)
			{
				//String sql = "SELECT SUB_CLAUSE_ID,SUB_CLAUSE_NAME, SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER WHERE SUB_CLAUSE_ID = '"+subId[j]+"'";
				String sql = CommonSQL.GET_SUBCLAUSE_DETLS;
				 dbUtility.createPreparedStatement(sql);
				
			 try {
				 String sd[] = new String[1];
					sd[0] = subId[j];
					list2 = dbUtility.executeQuery(sd);
				logger.debug(sql);
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
	 * 
	 * @param subKeys
	 * @return HashMap that holds list of subClauses that have not been modified or changed
	 */
	public HashMap subClauseNotClicked(ArrayList subKeys)
	{
		String propId = null;
		String propL1Id = null;
		String propL2Id = null;
		
		//String list1 = null;
		//ArrayList list1 = new ArrayList();
		GuidelineDTO sub;
		ArrayList subClauseList = new ArrayList();
		HashMap hMap = new HashMap();
		
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
						
						propL2Id = (keys[3].toString().equals("0")?null:keys[3]);	
						logger.debug(propL2Id);
						
						if(propL2Id == null)
						{
							dbUtility.createPreparedStatement(CommonSQL.SUBCLAUSE_NOT_CLICKED_NULL);
							
					
							String sd[] = new String[2];
							sd[0] = propId;
							sd[1] = propL1Id;
							String id  = dbUtility.executeQry(sd);
							String val = id;
		                	list1 = (list1 == null?val:(list1+"_"+val));
						}
						else
						{
							dbUtility.createPreparedStatement(CommonSQL.SUBCLAUSE_NOT_CLICKED);
							
							
							String sd[] = new String[3];
							sd[0] = propId;
							sd[1] = propL1Id;
							sd[2] = propL2Id;
							String id = dbUtility.executeQry(sd);
							String val = id;
		                	list1 = (list1 == null?val:(list1+"_"+val));
						}
					
					
					
				
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
	 * This function is used to copy subClauses in case of derived guideline
	 * @param guideId
	 * @return boolean
	 */
	public boolean subClauseDeriveToApprove(String guideId)
	{
		
		int wardId = 0;
		int j = 0;
		ArrayList list1 = new ArrayList();
		boolean flag = false;
		boolean SubClauseInsertion = false;
		
		try {
				
			dbUtility = new DBUtility();
			
			//String sql = "SELECT DISTINCT MOHALLA_VILLAGE_ID FROM IGRS_SUBCLAUSE_DERIVED_MASTER WHERE GUIDELINE_ID = '"+guideId+"' minus SELECT DISTINCT MOHALLA_VILLAGE_ID FROM IGRS_SUBCLAUSE_MASTER_APPROVED WHERE GUIDELINE_ID = '"+guideId+"'";
			String sql = CommonSQL.GET_MOHALLA_MINUS;
			dbUtility.createPreparedStatement(sql);
	
			//ArrayList list = new ArrayList();
				try {
					String sd[] = new String[2];
					sd[0] = guideId;
					sd[1] = guideId;
					list1 = dbUtility.executeQuery(sd);

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
				
				list1.trimToSize();
				
				Iterator itr = list1.iterator();
				while(itr.hasNext())
				{
					String mohalla = itr.next().toString();
					logger.debug("IN PUBLISH DERIVED DAO "+ mohalla);
					
				
					 dbUtility.createPreparedStatement(CommonSQL.DERIVE_TO_APPROVED);
					try
					{
						String sd1[] = new String[2];
						sd1[0] = guideId;
						sd1[1] = mohalla;
				
						
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
			        
			        if (flag == false)
			        	SubClauseInsertion = false;
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
		logger.debug("GuideLineDAO:: DERIVE TO APPROVED:-  "+SubClauseInsertion);
		return SubClauseInsertion;
	}
	
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that holds list of subclauses to show on read only page for checker
	 */
	public ArrayList subClauseReadOnlyComplete (GuidelineDTO gDTO)
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
		int wardId =0;
		
		try
		{
			dbUtility = new DBUtility();
			
		
			 dbUtility.createPreparedStatement(CommonSQL.APP_COMPLETE);		
						if(gDTO.getWardId() == 0)
						{
							wardId = gDTO.getPatwariId();
						}
						else
						{
							wardId = gDTO.getWardId();
						}
						
						
						try
						{
							String sd[] = new String[7];
							sd[0] = gDTO.getGuideLineID();
							sd[1] = String.valueOf(gDTO.getTehID());
							sd[2] = String.valueOf(wardId);
							sd[3] = String.valueOf(gDTO.getMohallaId());
							sd[4] = gDTO.getPropertyID();
							sd[5] = gDTO.getL1_ID();
							sd[6] = gDTO.getL2_ID();
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
					dbUtility.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);

				}
			}
			return subClauseList;
	}
	
	
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that holds list of subclauses to show on read only page for checker(In case of derived Guideline)
	 */
	public ArrayList subClauseReadOnlyCompleteDerived (GuidelineDTO gDTO)
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
		int wardId =0;
		
		try
		{
			dbUtility = new DBUtility();
	
			dbUtility.createPreparedStatement(CommonSQL.APP_COMPLETE_DERIVED);
						
						if(gDTO.getWardId() == 0)
						{
							wardId = gDTO.getPatwariId();
						}
						else
						{
							wardId = gDTO.getWardId();
						}
						
						
						try
						{
							String sd[] = new String[7];
							sd[0] = gDTO.getGuideLineID();
							sd[1] = String.valueOf(gDTO.getTehID());
							sd[2] = String.valueOf(wardId);
							sd[3] = String.valueOf(gDTO.getMohallaId());
							sd[4] = gDTO.getPropertyID();
							sd[5] = gDTO.getL1_ID();
							sd[6] = gDTO.getL2_ID();
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
					sub = new GuidelineDTO();
					sub.setSubClauseId((String)list1.get(0));
					sub.setSubName((String)list1.get(1));
					sub.setSubClauseName((String)list1.get(2));
					sub.setStatus((String)list1.get(3));


					logger.debug(""+sub);
					subClauseList.add(sub);
				}

			} catch (Exception x) {
				logger.debug(x);

			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);

				}
			}
			return subClauseList;
	}
	
	public ArrayList getDistrictIdList(ArrayList officeList) {
		GuidelineDTO  district ;
		ArrayList districtList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		 String disttId = "";
		try {
			
				dbUtility = new DBUtility();
				
			
			 
				 for(int j = 0; j<officeList.size();j++)
				 {
					 ArrayList list = (ArrayList)officeList.get(j);
					 String officeId = (String)list.get(0);
					 logger.debug(""+officeId);
					 
					 try {
						 dbUtility.createPreparedStatement(CommonSQL.GET_DISTRICT);
							String sd[] = new String[1];
							sd[0] = officeId;
							String id = dbUtility.executeQry(sd);

			              disttId = id;
			           
			            logger.debug("**************"+disttId);
			            if(!list2.contains(disttId))
			            {
			            	logger.debug("added to List");
			            	list2.add(disttId);
			            	logger.debug("size"+list2.size());
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
		           logger.debug("size of ArrayList"+list2.size());
		
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
						 dbUtility.createPreparedStatement(CommonSQL.GET_DISRICT_NAME);
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
	
	
	/**
	 * 
	 * @param officeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrict(String officeId, String lang) throws Exception
	{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();
		ArrayList list2 = new ArrayList();
		logger.debug("<-----In DAO----->");
		
		String sql= CommonSQL.GET_DISTRICT_LIST;
		try {
			dbUtility = new DBUtility();
			
			dbUtility.createPreparedStatement(CommonSQL.GET_DISTRICT_LIST);
			
			try
			{	
				String sd[] = new String[1];
				sd[0] = officeId;
				list2 =dbUtility.executeQuery(sd);
	                
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
				if(lang.equalsIgnoreCase("en"))
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
	
	// added by simran
	
	public ArrayList deriveGuidelineDetails(ArrayList guidelineValues)
	{
		logger.debug("size of orignal List-->"+guidelineValues.size());
		ArrayList propDetailsForPV = new ArrayList();
		ArrayList finalList = new ArrayList();
		ArrayList propList = new ArrayList();
		ArrayList mainList = new ArrayList();DBUtility dbUtility = null;
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
							System.out.println("final propId"+fdto.getPropertyID());
							System.out.println("propId"+ggdto.getPropertyID());
							System.out.println("final L1"+fdto.getL1_ID());
							System.out.println("L1"+ggdto.getL1_ID());
							if(l1Id.equals(fdto.getL1_ID()))
							{
								logger.debug("Match------------------->");
								BigDecimal guideval = fdto.getProposedValue();
								Double guideVal = guideval.doubleValue();
								Double multiplyingFactor = Double.parseDouble(val);
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
							System.out.println("final propId"+fdto.getPropertyID());
							System.out.println("propId"+ggdto.getPropertyID());
							System.out.println("final L1"+fdto.getL1_ID());
							System.out.println("L1"+ggdto.getL1_ID());
							System.out.println("final L2"+fdto.getL2_ID());
							if(l1Id.equals(fdto.getL2_ID()))
							{
								logger.debug("Match------------------->");
								BigDecimal guideVal = fdto.getProposedValue();
								Double guideval =  guideVal.doubleValue();
								Double multiplyingFactor = Double.parseDouble(val);
								if(operator.equals("*"))
								{
									double finalVal = guideval * multiplyingFactor;
									System.out.println("final Val"+finalVal);
									
									ggdto.setProposedValue(BigDecimal.valueOf(finalVal));
								}
								else if(operator.equals("/"))
								{
									Double finalVal = guideval / multiplyingFactor;
									System.out.println("final Val"+finalVal);
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
		}	finally
		{
			try{
				if(dbUtility!=null){
				dbUtility.closeConnection();
				}
			}catch(Exception e){
				logger.debug("exception while closing connection.");
			}
		}
		return guidelineValues;
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


	public boolean updatestatus(GuidelineDTO formDTO) throws Exception {
		
		boolean flag=false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
	
			
			   String distid=formDTO.getDistrictID();
				String financalyr=formDTO.getFinancialYear();
				dbUtility.createPreparedStatement(CommonSQL.UPDATE_CURRENT_PUBLISH_STATUS);
				flag = dbUtility.executeUpdate(new String[]{formDTO.getGuideLineID(),financalyr,distid});
				String type = formDTO.getGotTypeChk();
				if("final".equalsIgnoreCase(type))
				{
					dbUtility.createPreparedStatement(CommonSQL.UPDATE_PUBLISH_STATUS_FINAL);
				}
				else
				{
					dbUtility.createPreparedStatement(CommonSQL.UPDATE_PUBLISH_STATUS);
				}
			 //System.out.println("heyyyyyy");
	        flag = dbUtility.executeUpdate(new String[]{formDTO.getGuideLineID()});
	        
	        if("final".equalsIgnoreCase(type))
			{
	        	dbUtility.createPreparedStatement(CommonSQL.UPDATE_VIEW_STATUS_FINAL);
			}
	        else
	        {
	        	dbUtility.createPreparedStatement(CommonSQL.UPDATE_VIEW_STATUS);
	        }
	        
			 //System.out.println("heyyyyyy");
	        flag = dbUtility.executeUpdate(new String[]{formDTO.getGuideLineID()});
	        
	     
			
			 
	        if(flag)
	        {
	        	
	        	 
		      	logger.debug("Publish status updated");
		    
		      
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
	
	
}
