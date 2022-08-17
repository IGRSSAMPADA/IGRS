package com.wipro.igrs.visitBooking.dao;

/**
 * ===========================================================================
 * File           :   VisitBookingDAO.java
 * Description    :   Represents the DAO Class

 * Author         :   Pavani param
 * Created Date   :   Apr 08, 2008.

 * ===========================================================================
 */
import com.wipro.igrs.visitBooking.form.VisitBookingForm;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.visitBooking.sql.CommonSQL;
import com.wipro.igrs.visitBooking.bd.VisitBookingBD;
import java.util.ArrayList;
import org.apache.log4j.Logger;
public class VisitBookingDAO {

	DBUtility dbUtil;
	ArrayList list = null;
	boolean insert;
	String SQL;
	IGRSCommon common;
	
	private Logger logger = (Logger) Logger.getLogger(VisitBookingDAO.class);

	/**
	 * Getting Country list
	 * 
	 * @return list
	 */
	public ArrayList getCountry() {

		try {
			common = new IGRSCommon();
			list = common.getCountry();
			logger.info("VisitBookingDAO ---- getCountry()" + list);
		} catch (Exception e) {
			logger.debug("VisitBookingDAO --- Exception in getCountry():"+ e);
		}
		return list;
	}

	/**
	 * Getting State list.
	 * 
	 * @param countryId
	 * @return list
	 */

	public ArrayList getState(String countryId) {

		try {
			common = new IGRSCommon();
			if (countryId == null) {
				countryId = "INDIA";
			}
			list = common.getState(countryId);
			logger.info("VisitBookingDAO ---- getState()" + list);
		} catch (Exception e) {
			logger.debug("VisitBookingDAO --- Exception in getState():"+ e);
		}
		return list;
	}

	/**
	 * Getting District list.
	 * 
	 * @return list
	 */

	public ArrayList getDistrict(String stateId) {
		try {
			// if (stateId == null) {
			// stateId = "MP";
			// }
			common = new IGRSCommon();
			list = common.getDistrict(stateId);
			logger.info("VisitBookingDAO----getDistrict()" + list);
		} catch (Exception e) {
			logger.debug("VisitBookingDAO -- Exception in getDistrict():"+ e);
		}
		return list;
	}

	/**
	 * Getting Photo List
	 * 
	 * @return list
	 */

	public ArrayList getphotoIdList()throws Exception
	{
		String SQL = CommonSQL.PHOTO_ID_DET;
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("VisitBookingDAO --- in getphotoIdList(): list.size()"+ list);

		} catch (Exception e) {
			logger.debug("VisitBookingDAO --  Exception in getphotoIdList():"+ e);
		}
		 finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}

	/**
	 * Getting deed details
	 * 
	 * @return list
	 */
	public ArrayList getOptionalDeedType() {

		try {
			common = new IGRSCommon();
			list = common.getOptionalDeedType();
			
			logger.info("Wipro in VisitBookingDAO - getOptionalDeedType() after execute query  "+ list);
		} catch (Exception e) {
			logger.debug("VisitBookingDAO -- Exception in getOptionalDeedType():"+ e);
		}
		
		return list;

	}

	/**
	 * Inserting Visit Booking Details.
	 * 
	 * @param param
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * @return
	 * @throws Exception
	 */
	// --START--VISIT DETAILS INSERT
	public boolean visitDetIns(String param[], String roleId, String funId, String userId) throws Exception {
		boolean blnVisitIns = false;
		try {
			dbUtil = new DBUtility();
			common = new IGRSCommon();
			String SQL = CommonSQL.VISIT_DET_INSERT;
			dbUtil.createPreparedStatement(SQL);
			blnVisitIns = dbUtil.executeUpdate(param);
			if (blnVisitIns) {
				dbUtil.commit();
				common.saveLogDet("IGRS_VISIT_BOOKING_TXN","INSERT","T",funId,userId,roleId);
			}
			logger.info("VisitBookingDAO--After inserting Visit Book details"+blnVisitIns);

		} catch (Exception x) {
			logger.debug("VisitBookingDAO -- Exception in visitDetIns() :- "+ x);
			logger.debug("VisitBookingDAO -- Exception in visitDetIns() :- "+ x);
			dbUtil.rollback();
			common.saveLogDet("IGRS_VISIT_BOOKING_TXN","INSERT","F",funId,userId,roleId);
		} finally {
			try {
				if (!blnVisitIns) {
					dbUtil.rollback();
					common.saveLogDet("IGRS_VISIT_BOOKING_TXN","INSERT","F",funId,userId,roleId);
				}
				 dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("VisitBookingDAO --  Exception in visitDetIns() :"+ ex);
			}
		}
		return blnVisitIns;
	}

	/**
	 * getting the visit Remarks values.
	 * 
	 * @param param
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * @return
	 * @throws Exception
	 */

	public boolean visitRemIns(String param[], String roleId, String funId, String userId) throws Exception {
		boolean blnVisitIns = false;
		try {
			dbUtil = new DBUtility();
			common = new IGRSCommon();
			String SQL = CommonSQL.VISIT_REM_INSERT;
			dbUtil.createPreparedStatement(SQL);
			blnVisitIns = dbUtil.executeUpdate(param);
			if (blnVisitIns) {
				dbUtil.commit();
				common.saveLogDet("IGRS_VISIT_BOOKING_COMMENTS","INSERT","T",funId,userId,roleId);
			}
			logger.info("VisitBookingDAO--After  inserting   Visit Booking Remarks Details "+blnVisitIns);

		} catch (Exception x) {
			logger.info("VisitBookingDAO --  Exception in visitRemIns() :- "	+ x);
			dbUtil.rollback();
			common.saveLogDet("IGRS_VISIT_BOOKING_COMMENTS","INSERT","F",funId,userId,roleId);
		} finally {
			try {
				if (!blnVisitIns) {
					dbUtil.rollback();
					common.saveLogDet("IGRS_VISIT_BOOKING_COMMENTS","INSERT","F",funId,userId,roleId);
				}
				 dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("VisitBookingDAO --  Exception in visitRemIns() :-"+ ex);
			}
		}
		return blnVisitIns;
	}

	/**
	 * getting Visit Booking Reference Id.
	 * 
	 * @throws Exception
	 */
	public ArrayList getRefId() throws Exception {
		ArrayList list = new ArrayList();
		logger.info("Wipro in VisitBookingDAO - getRefId() ");
		SQL = CommonSQL.VISIT_REF_ID_SELECT;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in VisitBookingDAO - getRefId() AFTER EXCUTIN QUERY list values"+ list);
		} catch (Exception e) {
			logger.debug("VisitBookingDAO --  Exception in calling at VisitBookingDAO Class at getRefId ()  "+ e);
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}
	
	/**
	 * getting visit booking print Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList printVisitBookDet(String visitRefId) throws Exception {
		ArrayList estampList = new ArrayList();
		logger.info("Wipro in EstampDAo - printEstampTxnDet() before EXCUTIN QUERY");
		SQL = CommonSQL.VISIT_PRINT_DET;
		try {
			dbUtil = new DBUtility();
			String arry[] = new String[1];
			if (visitRefId != null) {
				arry[0] = visitRefId;
			}
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(arry);
			logger.info("Wipro in VisitBookingDAO - printVisitBookDet() AFTER EXCUTIN QUERY list values"+ list);
		} catch (Exception e) {
			logger.debug("VisitBookingDAO -- Exception in calling at DAO Class at printVisitBookDet ()  "+ e);
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}

	/**
	 * getting visit booking View Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getVisitBookViewDet(String visitRefId) throws Exception
	{
		
		SQL = CommonSQL.VISIT_VIEW_DET + "'" + visitRefId + "'";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("Wipro in VisitBookingDAO - getVisitBookViewDet() AFTER EXCUTIN QUERY list values"+ list);
		} catch (Exception e) 
		{
			logger.debug("VisitBookingDAO -- Exception in calling at DAO Class at getVisitBookViewDet ()  "+ e);
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}

	/**
	 * getting visit booking Remarks Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getVisitBookRemDet(String visitRefId) throws Exception 
	{
		SQL = CommonSQL.VISIT_REM_DET + "'" + visitRefId + "'";
		try {
			    dbUtil = new DBUtility();
				dbUtil.createStatement();
				list = dbUtil.executeQuery(SQL);
				logger.info("Wipro in VisitBookingDAO - getVisitBookRemDet() AFTER EXCUTIN QUERY list values"+ list);
		} 
		catch (Exception e) 
		{
			logger.debug("VisitBookingDAO -- Exception in calling at DAO Class at getVisitBookRemDet ()  "+ e);
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}

	/**
	 * getting visit booking View Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getVisitViewResult(VisitBookingForm _refForm)
			throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		try {
			dbUtil = new DBUtility();
			if (_refForm.getViewType().equalsIgnoreCase("refId"))
			{
					SQL = CommonSQL.VISIT_VIEW_REFID_QUERY;
					if (_refForm.getReferenceID() != null)
					{
						arry[0] = _refForm.getReferenceID();
					}
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(arry);
					logger.info("Wipro in VisitBookingDAO  - getVisitViewResult() AFTER EXCUTIN QUERY "+ list);
			}
			if (_refForm.getViewType().equalsIgnoreCase("Status"))
			{
					SQL = CommonSQL.VISIT_VIEW_STATUS_QUERY;
					if (_refForm.getStatus() != null)
					{
						arry[0] = _refForm.getStatus();
					}
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(arry);
					logger.info("Wipro in VisitBookingDAO - getVisitViewResult() AFTER EXCUTIN QUERY"+ list);
			}

			if (_refForm.getViewType().equalsIgnoreCase("Date")) {

	
					SQL = CommonSQL.VISIT_VIEW_DATE_QUERY;
					String arry1[] = new String[2];
					if (_refForm.getDurFrom() != null
							&& _refForm.getDurTo() != null) 
					{
						
						arry1[0] = VisitBookingBD.getOracleDate(_refForm.getDurFrom());
						arry1[1] = VisitBookingBD.getOracleDate(_refForm.getDurTo());
					}
					dbUtil.createPreparedStatement(SQL);
					list = dbUtil.executeQuery(arry1);
			}
		} catch (Exception e)
		{
			logger.debug("VisitBookingDAO -- Exception in calling at Visit Booking -- DAO Class at getVisitViewResult ()  "+ e);
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}

	/**
	 * getting Visit Booking Update Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getVisitUpdateResult(VisitBookingForm _refForm)
			throws Exception {
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		try {
			dbUtil = new DBUtility();
			if (_refForm.getViewType().equalsIgnoreCase("refId")) {

				SQL = CommonSQL.VISIT_VIEW_REFID_QUERY;
				if (_refForm.getReferenceID() != null) {
					arry[0] = _refForm.getReferenceID();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);
				logger.info("Wipro in VisitBookingDAO  - getVisitUpdateResult() AFTER EXCUTIN QUERY "+ list);
			}
			if (_refForm.getViewType().equalsIgnoreCase("Status")) {



				SQL = CommonSQL.VISIT_VIEW_STATUS_QUERY;
				if (_refForm.getStatus() != null) {
					arry[0] = _refForm.getStatus();
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);
				
				logger.info("Wipro in VisitBookingDAO - getVisitUpdateResult() AFTER EXCUTIN QUERY"+ list);
			}

			if (_refForm.getViewType().equalsIgnoreCase("Date")) {


				SQL = CommonSQL.VISIT_VIEW_DATE_QUERY;
				String arry1[] = new String[2];
				
				if (_refForm.getDurFrom() != null&& _refForm.getDurTo() != null) {
					
					arry1[0] = VisitBookingBD.getOracleDate(_refForm
							.getDurFrom());
					arry1[1] = VisitBookingBD.getOracleDate(_refForm.getDurTo());
				}
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry1);
			}
		} catch (Exception e) {
			logger.debug("VisitBookingDAO -- Exception in calling at Visit Booking -- DAO Class at getVisitUpdateResult ()  "+ e);
		}

		return list;
	}

	/**
	 * getting Visit Booking Fee Details.
	 * @param functionId 
	 * 
	 * @return list
	 * @throws Exception
	 */
	public ArrayList getVisitBookFeeVal(String functionId) throws Exception 
	{
		ArrayList list = new ArrayList();


		String SQL = CommonSQL.VISIT_BOOK_FEE_QUERY+"'"+functionId+"'";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			logger.info("VisitBookingDAO in getVisitBookFeeVal(): list.size()"+ list.size());
			logger.debug("VisitBookingDAO in getVisitBookFeeVal(): list.size()"+ list.size());
			logger.debug("VisitBookingDAO SQL"+ SQL);

		} catch (Exception e) {
			logger.debug("Exception in VisitBookingDAO - getVisitBookFeeVal():"+ e);
			e.printStackTrace();
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}

	/**
	 * for calculating othersFeeDuty
	 * 
	 * @param _refFunId
	 * @throws Exception
	 * @return othersFeeDuty
	 */
	public ArrayList getOthersFeeDuty(String _retFunId, String _serId,
			String _userType) throws Exception
			{
		ArrayList othersFeeDuty = null;
		try {
			common = new IGRSCommon();
			 othersFeeDuty =
				 common.getOthersFeeDuty(_retFunId,_serId,_userType);
			 logger.info("in VisitBookingDAO  getVisitOthersDuty()- othersFeeDuty="+ othersFeeDuty);
		} catch (Exception e) 
		{
			logger.debug("VisitBookingDAO -- Exceptin in getOthersDuty() "+ e);
		}
		return othersFeeDuty;

	}

	  /**
	 * check application no
	 * @param reqApplNo
	 * @return status
	 */
	public String checkAppNo(String reqApplNo) {
		String regid = null;
		try {
			dbUtil = new DBUtility();
			String sql = "SELECT APPLICATION_STATUS FROM IGRS_REG_INITN_TXN_DETAILS WHERE TRANSACTION_ID='"+reqApplNo+"'";
			dbUtil.createStatement();
			regid = dbUtil.executeQry(sql);
		}catch (Exception e) {
			logger.debug("Exception in VisitBookingDAO - checkAppNo():"+ e);
			e.printStackTrace();
		}
		finally {
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		return regid;
	}
}
