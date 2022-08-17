package com.wipro.igrs.deedDraft.dao;

import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.sql.CLOB;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.common.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.deedDraft.dto.DeedDraftDTO;
import com.wipro.igrs.deedDraft.sql.DeedDraftSQL;
import com.wipro.igrs.newreginit.constant.RegInitConstant;

public class DeedDraftDAO {
	private Logger logger = Logger.getLogger(DeedDraftDAO.class);

	/*OracleConnection conn;  // initialize this first

	
	public Clob getClob(String data){
		Clob clob=conn.createClob();
	  return clob.setString(1, data);
	 }*/
	/*OracleConnection conn1;  // initialize this first

	//	Clob myClob = conn1.createClob();



	 OracleConnection orclConn ;
	public void setConnection( OracleConnection conn )
	{
	    this.conn = conn;
	}

	public Clob setClob( String content ) throws SQLException
	{
	    Clob myClob = ( CLOB ) orclConn.createClob();
	    myClob.setString( 1, content);
	    return myClob;
	}
	*/
	public Clob getCLOB(String innStr, Connection conn) throws Exception {
		logger.debug("BEGINNING OF GETCLOB METHOD PARAM STRING::::::" + innStr);
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		String pathClob = pr.getValue("reg.deed_draft_clob");
		Clob tempClob = null;
		// If the temporary CLOB has not yet been created, create new
		logger.debug("CONNECTION BEFORE CASTING" + conn.getClass().getName());
		//Connection orConn = (oracle.jdbc.OracleConnection)conn;
		//	logger.debug("CONNECTION AFTER CASTING"+orConn.getClass().getName());
		//tempClob = CLOB.createTemporary(orConn, true, CLOB.DURATION_SESSION);
		if ("SIT".equalsIgnoreCase(pathClob)) {
			Connection orConn = (oracle.jdbc.OracleConnection) conn;
			tempClob = CLOB.createTemporary(orConn, true, CLOB.DURATION_SESSION);
		} else {
			tempClob = conn.createClob();
		}
		logger.debug("CONNECTION ENVIRONEMT TYPE:::::::::::::" + pathClob);
		logger.debug("CLOB CREATED");
		// Open the temporary CLOB in readwrite mode to enable writing
		//tempClob.open(CLOB.MODE_READWRITE);
		// Get the output stream to write
		Writer tempClobWriter = tempClob.setCharacterStream(1);
		// Write the data into the temporary CLOB
		tempClobWriter.write(innStr);
		// Flush and close the stream
		tempClobWriter.flush();
		tempClobWriter.close();
		logger.debug("ENDING OF GETCLOB METHOD RETURN CLOB:::::::");
		return tempClob;
	}

	//saurav
	public ArrayList checkValidRegID(String appID) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_APPLICATION_DETAILS;
			//String [] params = {appID};
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(appID);
			if (!(list == null || list.isEmpty())) {
				list = (ArrayList) list.get(0);
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getPropdetails(String appId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			list = new ArrayList();
			String sql = DeedDraftSQL.GET_PROPERTY_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(appId);
			//list = (ArrayList) list.get(0);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getDutyDetails(String appId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			list = new ArrayList();
			String sql = DeedDraftSQL.GET_DUTY_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(appId);
			//list = (ArrayList) list.get(0);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getAppDetails(String appId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_APP_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(appId);
			list = (ArrayList) list.get(0);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public String getPropType(String id) throws Exception {
		String type = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_PROP_TYPE;
			dbUtility.createPreparedStatement(sql);
			String[] arr = { id };
			type = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return type;
	}

	public ArrayList getbuildingView(String id, String language) {
		DBUtility dbUtility = null;
		ArrayList details = new ArrayList();
		try {
			dbUtility = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = id; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_BUILDING_VIEW_DETAILS);
				} else {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_BUILDING_VIEW_DETAILS_HINDI);
				}
				details = dbUtility.executeQuery(arr);
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
		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}
	
	//construction slab changes by saurav
	public String getConstructionSlabDesc(String constSlabId, String language) throws Exception {
		String constSlabDesc = "";
		DBUtility db = null;
		try{
			db = new DBUtility();
			String sql = "";
			if(language.equalsIgnoreCase("en")){
			sql = DeedDraftSQL.GET_CONST_SLAB_DESC_E;
			}else {
			sql = DeedDraftSQL.GET_CONST_SLAB_DESC_H;
			}
			String arr[] = {constSlabId};
			db.createPreparedStatement(sql);
			constSlabDesc = db.executeQry(arr);
		}catch(Exception e){
			logger.debug(e);
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		return constSlabDesc;
	}
	
	public ArrayList getconstSlabDetail(String id, String language) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility db = null;
		try{
			db = new DBUtility();
			String sql = DeedDraftSQL.GET_AGRI_CONSTRUCTION_CONST_SLAB;
			String[] arr = {id};
			db.createPreparedStatement(sql);
			list = db.executeQuery(arr);
		}catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return list;
	}
	//construction slab changes end
	public ArrayList getConsenterDetails(String regTxnId, String userId) throws Exception {
		ArrayList consenterDetails = new ArrayList();
		DBUtility db = null;
		try {
			db = new DBUtility();
			String sql = DeedDraftSQL.GET_CONSENTER_DETLS;
			String[] arr = { userId, regTxnId };
			db.createPreparedStatement(sql);
			consenterDetails = db.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return consenterDetails;
	}

	public ArrayList getBuildingDetailsAgri(String id, String language) {
		ArrayList details = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = id; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_VIEW_DETAILS_AGRI);
				} else {
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_VIEW_DETAILS_AGRI_HINDI);
				}
				details = dbUtil.executeQuery(arr);
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}

	public ArrayList getKhasraDetails(String propertyId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_KHASHRA_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(propertyId);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getPropDutyDetails(String dutyTxnId, String regTxnId, String propertyId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			list = new ArrayList();
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_PROP_DUTY_DETAILS;
			String[] arr = { dutyTxnId, regTxnId, propertyId };
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getUserEnterableValue(String dutyTxnId, String propDutyId, String language) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = "";
			if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				query = DeedDraftSQL.GET_PROP_WISE_USER_ENTERABLE;
			} else {
				query = DeedDraftSQL.GET_PROP_WISE_USER_ENTERABLE_HI;
			}
			if (("".equals(propDutyId))) {
				query = query + DeedDraftSQL.PROP_DUTY_NOT_AVAILABLE;
				String param[] = { dutyTxnId };
				dbUtility.createPreparedStatement(query);
				list = dbUtility.executeQuery(param);
			} else {
				String param1[] = { dutyTxnId, propDutyId };
				query = query + DeedDraftSQL.PROP_DUTY_AVAILABLE;
				dbUtility.createPreparedStatement(query);
				list = dbUtility.executeQuery(param1);
			}
		} catch (Exception exception) {
			logger.debug("Exception in getPropWiseUserEnterables" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	public ArrayList getSubClauseList(String id, String lang) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			String sql = "";
			dbUtility = new DBUtility();
			sql = ("en".equals(lang)) ? DeedDraftSQL.GET_EN_SUB_CLAUSES_LIST : DeedDraftSQL.GET_HI_SUB_CLAUSES_LIST;
			dbUtility.createPreparedStatement(sql);
			String[] arr = { id };
			list = dbUtility.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getValuationDetails(String params, String language) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = null;
			list = new ArrayList();
			ArrayList lst = null;
			sql = ("en").equalsIgnoreCase(language) ? DeedDraftSQL.GET_VAL_DETAILS_E : DeedDraftSQL.GET_VAL_DETAILS_H;
			String[] arr = { params };
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getFloorList(String buildingTxnId, String language) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = buildingTxnId; //TODO
				if ("en".endsWith(language)) {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_BUILDING_FLOOR_DETAILS);
				} else {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_BUILDING_FLOOR_DETAILS_HINDI);
				}
				details = dbUtility.executeQuery(arr);
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
		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}

	public ArrayList getAgriDetails(String valId, String language) {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = valId; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_AGRI_EN_VIEW_AREA_DETAILS);
				} else {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_AGRI_HI_VIEW_AREA_DETAILS);
				}
				details = dbUtility.executeQuery(arr);
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
		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}

	public ArrayList getagriPropDetailsList(String valId, String language) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = valId; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(DeedDraftSQL.GET_AGRI_EN_VIEW_PROP_DETAILS);
				} else {
					dbUtil.createPreparedStatement(DeedDraftSQL.GET_AGRI_HI_VIEW_PROP_DETAILS);
				}
				details = dbUtil.executeQuery(arr);
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			}
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return details;
	}

	public ArrayList getAgriTreeDetailsList(String valId, String language) throws Exception {
		ArrayList treedetails = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = valId; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(DeedDraftSQL.GET_EN_AGRI_TREE_DETAILS);
				} else {
					dbUtil.createPreparedStatement(DeedDraftSQL.GET_HI_AGRI_TREE_DETAILS);
				}
				treedetails = dbUtil.executeQuery(arr);
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return treedetails;
	}

	public ArrayList getConstructionDetails(String agriTxnId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			list = new ArrayList();
			String sql = DeedDraftSQL.GET_AGRI_CONSTRUCTION_DETAILS;
			String[] arr = { agriTxnId };
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	//For exemption in deed draft page
	public ArrayList getExemption(String appId, String type) throws Exception {
		ArrayList<String> list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = null;
			list = new ArrayList<String>();
			sql = DeedDraftSQL.GET_EXEMPTION_DETAILS;
			dbUtility.createPreparedStatement(sql);
			String[] arr = { type, appId };
			list = dbUtility.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public String getPropRelated(String deedId, String instId) throws Exception {
		String propCheck = "N";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = "select prop_req_flag from igrs_deed_instrument_master where deed_type_id=? and instrument_id=?";
			dbUtility.createPreparedStatement(sql);
			String[] arr = { deedId, instId };
			propCheck = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return propCheck;
	}

	//for property optional flag
	public String getPropOptionalCheck(String deedId, String instId) throws Exception {
		String propOptionalCheck = "N";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = "select prop_optional_flag from igrs_deed_instrument_master where deed_type_id=? and instrument_id=?";
			dbUtility.createPreparedStatement(sql);
			String[] arr = { deedId, instId };
			propOptionalCheck = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return propOptionalCheck;
	}

	public String getPropValCheck(String deedId, String instId) throws Exception {
		String isVal = "N";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = "select PROP_VAL_REQ_FLAG from igrs_deed_instrument_master where deed_type_id=? and instrument_id=?";
			dbUtility.createPreparedStatement(sql);
			String[] arr = { deedId, instId };
			isVal = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return isVal;
	}

	public ArrayList getClauseList(String language, String valId) throws Exception {
		ArrayList list = null;
		DBUtility db = null;
		try {
			db = new DBUtility();
			String[] arr = { valId };
			if (language.equalsIgnoreCase("en")) {
				db.createPreparedStatement(CommonSQL.GET_EN_SUB_CLAUSES_LIST);
			} else {
				db.createPreparedStatement(CommonSQL.GET_HI_SUB_CLAUSES_LIST);
			}
			list = db.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			db.closeConnection();
		}
		return list;
	}

	public ArrayList getClauseListAgriConst(String language, String agriTxnId) throws Exception {
		ArrayList list = null;
		DBUtility db = null;
		try {
			db = new DBUtility();
			String[] arr = { agriTxnId };
			if (language.equalsIgnoreCase("en")) {
				db.createPreparedStatement(DeedDraftSQL.GET_EN_SUB_AGRI_CLAUSES_LIST);
			} else {
				db.createPreparedStatement(DeedDraftSQL.GET_HI_SUB_AGRI_CLAUSES_LIST);
			}
			list = db.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			db.closeConnection();
		}
		return list;
	}

	public boolean getDeedVerified(String applicationId, String appType, String deedId) throws Exception {
		boolean check = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if ("2".equals(appType)) {
				String sql = DeedDraftSQL.GET_DEED_DETAILS;
				String[] arr = { deedId, appType, applicationId };
				String count = dbUtility.executeQry(arr);
				if (!("0".equals(count))) {
					check = true;
				}
			} else {
				check = true;
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return check;
	}

	public String getAdjudicationFlag(String applicationId) throws Exception {
		String adjuFlag = "";
		DBUtility db = null;
		try {
			db = new DBUtility();
			String sql = DeedDraftSQL.GET_ADJU_FLAG;
			String[] arr = { applicationId };
			db.createPreparedStatement(sql);
			adjuFlag = db.executeQry(arr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return adjuFlag;
	}

	/**
	 * @param ddto
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public boolean saveDeedDraftDetails(DeedDraftDTO ddto, String userID, String appType, String applicationId) throws Exception {
		logger.debug("STARTING saveDeedDraftDetails METHOD PARAM:::::::USER" + userID);
		boolean flag = false;
		int i = 0;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if (ddto.getFirstPageChk().equalsIgnoreCase("Y")) {
				if (checkDeedRecord(ddto.getDeedId(), ddto.getPageId())) {
					dbUtility.setAutoCommit(false);
					String sql = DeedDraftSQL.UPDATE_DEED_TXN_DTLS;
					String deedDtls[] = { ddto.getDeedName(), userID, appType, applicationId, ddto.getDeedId() };
					dbUtility.createPreparedStatement(sql);
					flag = dbUtility.executeUpdate(deedDtls);
					if (flag) {
						dbUtility.commit();
						sql = DeedDraftSQL.UPADTE_DEED_CONTENT_DTLS;
						/*String deedContentDtls[] = {
								
								ddto.getDeedContent(),
								userID,
								ddto.getDeedId(),
								ddto.getPageId()
						};
						dbUtility.createPreparedStatement(sql);*/
						//Clob c=new StringClob(ddto.getDeedContent());
						//c.setString(1, ddto.getDeedContent());
						Connection conn = dbUtility.getDBConnection();
						//CLOB objClob = conn.createClob();
						//objClob.setString(1, ddto.getDeedContent());
						// CLOB objClob = stringtoClob(ddto.getDeedContent(),conn );
						Clob objClob = getCLOB(ddto.getDeedContent(), conn);
						PreparedStatement pst = conn.prepareStatement(sql);
						//  Clob cl=orclConn.createClob();
						//cl=setClob(ddto.getDeedContent() );
						pst.setClob(1, objClob);
						pst.setString(2, userID);
						pst.setString(3, ddto.getDeedId());
						pst.setString(4, ddto.getPageId());
						//flag = pst.execute();
						i = pst.executeUpdate();
						if (i > 0) {
							logger.debug("CLOB UPDATED SUCCESSFULLY");
							flag = true;
							conn.commit();
							conn.close();
						} else {
							logger.debug("CLOB UPDATE FAILED");
							flag = false;
							conn.rollback();
							conn.close();
						}
					}
				} else {
					String sql = DeedDraftSQL.GET_DEED_DRAFT_ID;
					dbUtility.createStatement();
					String deedDraftId = dbUtility.executeQry(sql);
					ddto.setDeedId(deedDraftId);
					dbUtility.setAutoCommit(false);
					sql = DeedDraftSQL.INSERT_DEED_TXN_DTLS;
					String deedDtls[] = { deedDraftId, ddto.getDeedName(), userID, appType, applicationId };
					dbUtility.createPreparedStatement(sql);
					flag = dbUtility.executeUpdate(deedDtls);
					if (flag) {
						dbUtility.commit();
						sql = DeedDraftSQL.INSERT_DEED_CONTENT_DTLS;
						String deedContentDtls[] = { ddto.getPageId(), deedDraftId, userID };
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(deedContentDtls);
						logger.debug("will be updating clob:::::::: flag is" + flag);
						/*	//Clob objClob = conn.createClob();
							 //objClob.setString(1, ddto.getDeedContent());
							 PreparedStatement pst= conn.prepareStatement(sql);
						   // Clob cl=orclConn.createClob();
						   //  cl=setClob(ddto.getDeedContent() );
						    pst.setString(1, ddto.getPageId() );
						    pst.setString(2,ddto.getDeedId() );
						   // pst.setClob(3,objClob );
						    pst.setString(3, userID );
							i = pst.executeUpdate();	*/
						if (flag) {
							dbUtility.commit();
							Connection conn = dbUtility.getDBConnection();
							sql = DeedDraftSQL.UPDATE_DEED_CONTENT_CLOB;
							logger.debug(sql);
							// CLOB objClob = stringtoClob(ddto.getDeedContent(),conn );
							Clob objClob = getCLOB(ddto.getDeedContent(), conn);
							PreparedStatement pstClob = conn.prepareStatement(sql);
							pstClob.setClob(1, objClob);
							pstClob.setString(2, ddto.getPageId());
							pstClob.setString(3, ddto.getDeedId());
							i = pstClob.executeUpdate();
							logger.debug("CLOB STATUS STRING IS::::::");
							if (i > 0) {
								logger.debug("CLOB UPDATED SUCCESSFULLY");
								flag = true;
								conn.commit();
								conn.rollback();
								conn.close();
							} else {
								logger.debug("CLOB UPDATE FAILED");
								flag = false;
								conn.rollback();
								conn.close();
							}
						}
					}
				}
			} else {
				if (checkDeedRecord(ddto.getDeedId(), ddto.getPageId())) {
					String sql = DeedDraftSQL.UPADTE_DEED_CONTENT_DTLS;
					/*	String deedContentDtls[] = {
								ddto.getDeedContent(),
								userID,
								ddto.getDeedId(),
								ddto.getPageId()
						};*/
					/*dbUtility.createPreparedStatement(sql);
					flag = dbUtility.executeUpdate(deedContentDtls);*/
					Connection conn = dbUtility.getDBConnection();
					//CLOB objClob = conn.createClob();
					//objClob.setString(1, ddto.getDeedContent());
					// CLOB objClob = stringtoClob(ddto.getDeedContent(),conn );
					Clob objClob = getCLOB(ddto.getDeedContent(), conn);
					PreparedStatement pst = conn.prepareStatement(sql);
					//  Clob cl=orclConn.createClob();
					//cl=setClob(ddto.getDeedContent() );
					pst.setClob(1, objClob);
					pst.setString(2, userID);
					pst.setString(3, ddto.getDeedId());
					pst.setString(4, ddto.getPageId());
					//flag = pst.execute();
					i = pst.executeUpdate();
					if (i > 0) {
						logger.debug("CLOB UPDATED SUCCESSFULLY");
						flag = true;
						conn.commit();
						conn.close();
					} else {
						logger.debug("CLOB UPDATE FAILED");
						flag = false;
						conn.rollback();
						conn.close();
					}
				} else//for inserting
				{
					String sql = DeedDraftSQL.INSERT_DEED_CONTENT_DTLS;
					String deedContentDtls[] = { ddto.getPageId(), ddto.getDeedId(), userID };
					dbUtility.createPreparedStatement(sql);
					flag = dbUtility.executeUpdate(deedContentDtls);
					logger.debug("will be updating clob:::::::: flag is" + flag);
					if (flag) {
						dbUtility.commit();
						Connection conn = dbUtility.getDBConnection();
						sql = DeedDraftSQL.UPDATE_DEED_CONTENT_CLOB;
						logger.debug(sql);
						// CLOB objClob = stringtoClob(ddto.getDeedContent(),conn );
						Clob objClob = getCLOB(ddto.getDeedContent(), conn);
						PreparedStatement pstClob = conn.prepareStatement(sql);
						pstClob.setClob(1, objClob);
						pstClob.setString(2, ddto.getPageId());
						pstClob.setString(3, ddto.getDeedId());
						i = pstClob.executeUpdate();
						logger.debug("CLOB STATUS STRING IS::::::");
						if (i > 0) {
							logger.debug("CLOB UPDATED SUCCESSFULLY");
							flag = true;
							conn.commit();
							conn.close();
						} else {
							logger.debug("CLOB UPDATE FAILED");
							flag = false;
							conn.rollback();
							conn.close();
						}
					}
				}
			}
			if (flag) {
				if(dbUtility!=null & !dbUtility.isClosed())
				{
				logger.debug("INSERTION SUCCESSFULL");
				dbUtility.commit();
				}
			} else {
				logger.debug("INSERTION FAILURE");
				dbUtility.rollback();
			}
		} catch (Exception e) {
			flag = false;
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		
		return flag;
	}

	/**
	 * @param deedDraftId
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	public String getDeedContent(String deedDraftId, String pageId) throws Exception {
		Clob clob = null;
		String deedContent = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility("");
			String sql = DeedDraftSQL.GET_DEED_CONTENT;
			//	dbUtility.createPreparedStatement(sql);
			//	String deedArr[] = {deedDraftId,
			//			pageId};
			//deedContent = dbUtility.executeQry(deedArr);
			Connection conn = dbUtility.getDBConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, deedDraftId);
			pst.setString(2, pageId);
			ResultSet rset = pst.executeQuery();
			while (rset.next()) {
				clob = (rset.getClob("DEED_CONTENT"));
			}
			//    dbUtility.createPreparedStatement(query);
			//estampTxnId = dbUtility.executeQry(new String[]{id});
			if (clob != null) {
				long length = clob.length();
				deedContent = clob.getSubString(1, (int) length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return deedContent;
	}

	/**
	 * @param deedDraftId
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	public boolean checkDeedRecord(String deedDraftId, String pageId) throws Exception {
		String deedContent = "";
		boolean flag = true;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_RECORD;
			dbUtility.createPreparedStatement(sql);
			String deedArr[] = { deedDraftId, pageId };
			deedContent = dbUtility.executeQry(deedArr);
			if (deedContent.equals("0"))
				flag = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	/**
	 * @param deedDraftId
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	public boolean checkDeedRecordOnNext(String deedDraftId, String pageId) throws Exception {
		String deedContent = "";
		boolean flag = true;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_RECORD;
			dbUtility.createPreparedStatement(sql);
			String deedArr[] = { deedDraftId, pageId };
			deedContent = dbUtility.executeQry(deedArr);
			if (deedContent.equals("0"))
				flag = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	/**
	 * @param deedDraftId
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	public ArrayList checkID(String userID) {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		//String param[]=new String[32];
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_ID;
			dbUtility.createPreparedStatement(sql);
			String deedArr[] = { userID };
			list = dbUtility.executeQuery(deedArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean updateDeedDraftStatus(String deedDraftId) {
		boolean ret = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeedDraftSQL.UPDATE_DEED_TXN_DTLS_STATUS);
			ret = dbUtility.executeUpdate(new String[] { deedDraftId });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public boolean saveDeedDraftPath(DeedDraftDTO ddto) {
		boolean ret = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			String path = pr.getValue("deed_draft_upload_location");
			dbUtility.createPreparedStatement("UPDATE IGRS_DEED_DRAFT_TXN_DTLS SET UPLOAD_PATH=?, DEED_DRAFT_STATUS='A' WHERE DEED_DRAFT_ID = ?");
			ret = dbUtility.executeUpdate(new String[] { path + ddto.getDeedId() + "/" + DeedDraftConstant.UPLOAD_NAME_SIGN_DOC, ddto.getDeedId() });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * @param deedDraftId
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getDeedContentPDF(String deedDraftId) throws Exception {
		Clob clob = null;
		ArrayList deedContentList = new ArrayList();
		//String deedContent = "";
		StringBuffer deedBuffer = new StringBuffer();
		DBUtility dbUtility = null;
		Connection conn = null;
		try {
			dbUtility = new DBUtility("");
			String sql = DeedDraftSQL.GET_DEED_CONTENT_PDF;
			logger.debug("QUERY IS--------->" + sql);
			//	dbUtility.createPreparedStatement(sql);
			//	String deedArr[] = {deedDraftId,
			//			pageId};
			//deedContent = dbUtility.executeQry(deedArr);
			conn = dbUtility.getDBConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, deedDraftId);
			ResultSet rset = pst.executeQuery();
			while (rset.next()) {
				clob = (rset.getClob("DEED_CONTENT"));
				deedContentList.add(clob);
			}
			//    dbUtility.createPreparedStatement(query);
			//estampTxnId = dbUtility.executeQry(new String[]{id});
			/*  if(clob!=null)
			  {
			        
			         long length=clob.length();
			         deedContent=clob.getSubString(1, (int)length);
			     
			  }*/
			for (int i = 0; i < deedContentList.size(); i++) {
				clob = (Clob) deedContentList.get(i);
				if (clob != null) {
					long length = clob.length();
					String deedContent = clob.getSubString(1, (int) length);
					deedBuffer.append(deedContent + " ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.releaseConnection(conn);
		}
		if (deedBuffer.length() > 0)
			deedBuffer = deedBuffer.append("\n---------------Please turn over/क्रप्या पृष्ठ पलटिये।---------------");
		return deedBuffer;
	}

	/**
	 * @param deedDraftId
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getDeedContentPDFFirst(String deedDraftId) throws Exception {
		Clob clob = null;
		ArrayList deedContentList = new ArrayList();
		//String deedContent = "";
		StringBuffer deedBuffer = new StringBuffer();
		DBUtility dbUtility = null;
		Connection conn = null;
		try {
			dbUtility = new DBUtility("");
			String sql = DeedDraftSQL.GET_DEED_CONTENT_PDF_FIRST;
			logger.debug("QUERY IS--------->" + sql);
			//	dbUtility.createPreparedStatement(sql);
			//	String deedArr[] = {deedDraftId,
			//			pageId};
			//deedContent = dbUtility.executeQry(deedArr);
			conn = dbUtility.getDBConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, deedDraftId);
			ResultSet rset = pst.executeQuery();
			while (rset.next()) {
				clob = (rset.getClob("DEED_CONTENT"));
				deedContentList.add(clob);
			}
			//    dbUtility.createPreparedStatement(query);
			//estampTxnId = dbUtility.executeQry(new String[]{id});
			/*  if(clob!=null)
			  {
			        
			         long length=clob.length();
			         deedContent=clob.getSubString(1, (int)length);
			     
			  }*/
			for (int i = 0; i < deedContentList.size(); i++) {
				clob = (Clob) deedContentList.get(i);
				if (clob != null) {
					long length = clob.length();
					String deedContent = clob.getSubString(1, (int) length);
					deedBuffer.append(deedContent + " ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.releaseConnection(conn);
		}
		return deedBuffer;
	}

	//added by ankit for prop val changes 
	public boolean getDeedIsConsumed(String applicationId, String appType) throws Exception {
		boolean check = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_CONSUMED;
			dbUtility.createPreparedStatement(sql);
			String[] arr = { appType, applicationId };
			String status = dbUtility.executeQry(arr);
			if ("D".equalsIgnoreCase(status)) {
				check = true;
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return check;
	}
}
