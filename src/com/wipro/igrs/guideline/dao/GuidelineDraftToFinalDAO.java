package com.wipro.igrs.guideline.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleCallableStatement;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DBUtilityTest;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.sql.CommonSQL;

public class GuidelineDraftToFinalDAO {
	
	Connection con;
	//DBUtility dbUtil;
	//Connection connTest;
	
	 	Statement st = null;
	    PreparedStatement pst = null;
	    ResultSet rst = null;
	    CallableStatement clstmt = null;
	    CallableStatement oclstmt = null;
	    DBUtility dbUtility = null;
		private CallableStatement callstmt  ;
	private Logger logger = (Logger) Logger.getLogger(GuidelineApprovalDAO.class);

	
	public ArrayList getDistrictList() {
		GuidelineDTO  district ;
		ArrayList districtList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createStatement();
			//list2 = dbUtil.executeQuery(CommonSQL.DISTRICT);
			dbUtility = new DBUtility();
				
				 try {		dbUtility.createStatement();
					String query = CommonSQL.DISTRICT;
					list2 = dbUtility.executeQuery(query);
			            
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
	
	
public ArrayList getFinancialYearList(){
		
		GuidelineDTO financialYear ;
		ArrayList financialYearList = new ArrayList();
		
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		try {
			//dbUtil = new DBUtility();
			//dbUtil.createStatement();
			dbUtility = new DBUtility();
			
			//list2 = dbUtil.executeQuery(CommonSQL.FINANCIAL);
			try {
				dbUtility.createStatement();
				String query = CommonSQL.FINANCIAL;
				list2 = dbUtility.executeQuery(query);
	            
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


public ArrayList showDraftStatusChecker(String finan, String disttID)
{
	GuidelineDTO  version ;
	ArrayList versionList = new ArrayList();
	ArrayList listVer1 = new ArrayList();
	ArrayList listVer2 = new ArrayList();
	try {
		
		System.out.println("<....."+disttID);
		System.out.println("<...."+finan);
		dbUtility = new DBUtility();
				
				
				
		try
			{	
			String query = CommonSQL.SHOW_DRAFT_VERSION_CHECKER;
			String arr [] = {finan,disttID};
			
			dbUtility.createPreparedStatement(query);
			listVer2  = dbUtility.executeQuery(arr);
		          
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
			version.setFromDepositeDate(fromDate);
			String toDate = listVer1.get(2).toString().substring(0,10);
			version.setToDepositeDate(toDate);
			version.setStatus((String)listVer1.get(3));
			
			version.setHdnGuideDuration((String)listVer1.get(0)+"~"+fromDate+"~"+toDate);
			
			versionList.add(version);
			System.out.println("!!!!!!"+listVer1.get(0));
			
			
			
			
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
}
