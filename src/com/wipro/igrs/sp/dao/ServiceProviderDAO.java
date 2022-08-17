/**
 * ServiceProviderDAO.java
 */

package com.wipro.igrs.sp.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.sp.bd.ServiceProviderBD;
import com.wipro.igrs.sp.dto.ServiceProviderDTO;
import com.wipro.igrs.sp.sql.CommonSQL;
import com.wipro.igrs.util.CommonUtil;

public class ServiceProviderDAO {
	
	private static Logger logger = 
		(Logger) Logger.getLogger(ServiceProviderDAO.class);
	
	DBUtility dbUtil = null;
	ArrayList list = null;
	boolean result = false;

	/*Date date=new Date();
	SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
	String dateString = formatter.format(date);*/

	public ServiceProviderDAO() throws Exception {
		// TODO Auto-generated constructor stub
		logger.info("SERVICE PROVIDER DAO()");
		dbUtil = new DBUtility();
	}

	/**
	 * Method name :getSPUsers
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getSPUsers() throws Exception {
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.SELECTSPUSERS);
			logger.info("DAO" + list);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return list;
	}

	/**
	 * Method name :getSPUsersInfo
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getSPUsersInfo(String userid) throws Exception {
		try {
			String arruserid[] = { userid, userid };
			dbUtil.createPreparedStatement(CommonSQL.SELECTSPUSERINFO);
			list = dbUtil.executeQuery(arruserid);
			logger.info("DAO" + list);
		} catch (Exception exception) {
			logger.info("Exception in getSPusersInfo-SPDAo" + exception);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}
//added by Shruti
	/*for retrieving district only for logged in user*/
	/**
	 * Method name :getUserDistrict
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getUserDistrict(String userid) throws Exception
	{
		try{
			dbUtil = new DBUtility();
			String requserid[]={userid};
			dbUtil.createPreparedStatement(CommonSQL.SELECTUSERDISTRICT);
			list = dbUtil.executeQuery(requserid);
		}
		catch(Exception exception)
		{
			logger.info("Exception in SPDAO-user district "+exception);
		}
		finally
		{
			dbUtil.closeConnection();
		}
		return list;
	}
	/**
	 * Method name :getDROOfficecode
	 * 
	 * @param distid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getDROOfficecode(String distid) throws Exception {
		try {
			dbUtil = new DBUtility();
			String arrdistid[] = { distid };
			dbUtil.createPreparedStatement(CommonSQL.SELECTDROOFFICECODE);
			list = dbUtil.executeQuery(arrdistid);
			
			
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		finally
		{
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * Method name :submitSPLlicence
	 * 
	 * @param providerDTO
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public boolean submitSPLlicence(ServiceProviderDTO providerDTO,
			String userid) throws Exception {

		try {
			String splicence[] = { getSpltxnid(), // LICENSE_TXN_ID
					providerDTO.getSplicencenumber(), // LICENSE_NUMBER
					providerDTO.getSpuserid(), // SP_USER_ID
					CommonUtil.getConvertedDate(providerDTO.getSplfromdate()), // VALID_FROM
					providerDTO.getSpdistrctid(), // DISTRICT_ID
					providerDTO.getSptehsilid(), // TEHSIL_ID
					providerDTO.getSpaddress(), // SP_OFFICE_ADDRESS
					providerDTO.getSppostalcode(), // PINCODE
					providerDTO.getDrocomments(), // DRO_COMMENTS
					CommonUtil.getConvertedDate(providerDTO.getSpltodate()), // VALID_TO
					userid, // CREATED_BY
					userid, // UPDATE_BY
			};

			dbUtil.createPreparedStatement(CommonSQL.INSERTSPLICENCEDETAILS);
			result = dbUtil.executeUpdate(splicence);
			
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			dbUtil.closeConnection();
		}

		return result;
	}

	/**
	 * Method name :getSpltxnid
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	private String getSpltxnid() throws Exception {
		String splicencetxnidseq = "";
		String splicencetxnconstant = "";
		try {
			dbUtil.createStatement();
			splicencetxnidseq = dbUtil
					.executeQry(CommonSQL.SELECT_LICENSE_TXN_ID_SEQ);
			splicencetxnconstant = "SPL";
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return splicencetxnconstant + splicencetxnidseq;

	}

	/**
	 * Method name :getSPBanks
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	//added by shruti
	public ArrayList getSPBanks() throws Exception {
		try {
			dbUtil = new DBUtility();
			
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.SELECTSPBANKS);
			logger.info("DAO" + list);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * Method name :getSPBankInfo
	 * 
	 * @param bankid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getSPBankInfo(String bankid) throws Exception {
		try {
			String arrbankid[] = { bankid };
			dbUtil.createPreparedStatement(CommonSQL.SELECTSPBANKINFO);
			list = dbUtil.executeQuery(arrbankid);
			
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * Method name :submitSPLbanklicence
	 * 
	 * @param providerDTO
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	
	/* ** original code
	public boolean submitSPLbanklicence(ServiceProviderDTO providerDTO,
			String userid) throws Exception {

		try {
			String splicence[] = { getSpltxnid(), // LICENSE_TXN_ID
					providerDTO.getSplicencenumber(), // LICENSE_NUMBER
					providerDTO.getSpuserid(), // SP_USER_ID
					providerDTO.getSplfromdate(), // VALID_FROM
					providerDTO.getDrocomments(), // DRO_COMMENTS
					providerDTO.getSpltodate(), // VALID_TO
					"A",
					userid, // CREATED_BY
					userid, // UPDATE_BY
					providerDTO.getSpbankid(), // BANK_ID
					providerDTO.getSpdistrctid(), // DISTRICT_ID
			};

			dbUtil
					.createPreparedStatement(CommonSQL.INSERTSPBANKLICENCEDETAILS);
			result = dbUtil.executeUpdate(splicence);
			logger.info("DAO" + list);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			dbUtil.closeConnection();
		}

		return result;
	}
	*/
	public boolean submitSPLbanklicence(ServiceProviderDTO providerDTO,
			String userid) throws Exception {

		try {
			String splicence[] = { getSpltxnid(), // LICENSE_TXN_ID
					providerDTO.getSplicencenumber(), // LICENSE_NUMBER
					providerDTO.getSpuserid(), // SP_USER_ID
					providerDTO.getSplfromdate(), // VALID_FROM
					providerDTO.getDrocomments(), // DRO_COMMENTS
					providerDTO.getSpltodate(), // VALID_TO
					"A",
					userid, // CREATED_BY
					userid, // UPDATE_BY
					providerDTO.getSpbankid(), // BANK_ID
					providerDTO.getSpdistrctid() // DISTRICT_ID
			};

			dbUtil
					.createPreparedStatement(CommonSQL.INSERTSPBANKLICENCEDETAILS);
			result = dbUtil.executeUpdate(splicence);
			
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			dbUtil.closeConnection();
		}

		return result;
	}
	
	/**
	 * Method name :getSPSerialNumber
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public String getSPSerialNumber() throws Exception {
		String splicenceseq = "";
		try {
			dbUtil.createStatement();
			//splicenceseq = dbUtil.executeQry(CommonSQL.SELECT_LICENSE_ID_SEQ);
			
			//MODIFIED BY SHRUTI
			splicenceseq = dbUtil.executeQry(CommonSQL.SELECT_LICENSE_ID_SEQ_1);
			
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return splicenceseq;

	}
	//added by shruti
	public String getLicenceTxnNumber() throws Exception
	{
		String licenceseq = "";
		try {
			dbUtil.createStatement();
			licenceseq = dbUtil.executeQry(CommonSQL.SELECT_LICENSE_ID_SEQ);
			
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return licenceseq;
	}
	//edited by shruti
	public String getNewSPSerialNumber() throws Exception {
		String splicenceseq = "";
		try {
			//dbUtil.createStatement();
			//splicenceseq = dbUtil.executeQry(CommonSQL.SELECT_LICENSE_ID_SEQ);
			//MODIFIED BY SHRUTI
			dbUtil.createStatement();
			dbUtil.executeUpdate(CommonSQL.RESET_LICENSE_ID_SEQ_1);
			dbUtil.createStatement();
			dbUtil.executeUpdate(CommonSQL.RESTART_LICENSE_ID_SEQ_1);
			splicenceseq = dbUtil.executeQry(CommonSQL.SELECT_LICENSE_ID_SEQ_1);
		} catch (Exception exception) {
			logger.info("Exception in getNewSPSerialNumber" + exception);
		}
		return splicenceseq;

	}
	//end 

	public ArrayList getUserDetails(String[] empid) throws Exception {
		ArrayList list = null;
		try {
			
			dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.RETRIVE_SP_USER_DETAILS);
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_SP_USER_DETAILS_NEW);
			//dbUtil.createPreparedStatement(CommonSQL.RETRIVE_SP_USER_DETAILS_1);
			list = dbUtil.executeQuery(empid);
			return list;
		} catch (Exception e) {
			logger.info("Exception occured " + e);

			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	
	public boolean submitspUserDetails(ServiceProviderDTO providerDTO,
			ArrayList listFileNames, String strFilePath, HttpSession session)
			throws Exception {
		
		dbUtil=new DBUtility();
		String licenceTxnId = this.getLicenceTxnNumber();
		String strUserId = (String) session.getAttribute("UserId");
		logger.info("licenceTxnId>>>>>>>>>>>>>>" + licenceTxnId);
		boolean flag = false;
		boolean flag1 = false;
		String filename = null;
		if (listFileNames != null) {
			ServiceProviderDTO serviceProviderDTO = (ServiceProviderDTO) listFileNames
					.get(0);
			filename = serviceProviderDTO.getFileName();
			logger.info("File Name" + filename);
		}
		try {
			
			dbUtil.setAutoCommit(false);
			
			String sqlValues[] = {
					licenceTxnId, // licenceTxnId,
					providerDTO.getSplicencenumber(), // LICENSE_NO
					providerDTO.getSpusername(), // SP_USER_ID
					CommonUtil
							.getConvertedDate(providerDTO.getSpDurationFrom()), // VALID_FROM
					providerDTO.getSpdistrctid(), // DISTRICT_ID
					providerDTO.getSptehsilid(), // TEHSIL_ID
					providerDTO.getSpOfficeAddress(), // SP_OFFICE_ADDR
					providerDTO.getSppostalcode(), // PINCODE
					providerDTO.getSpOtherInformation(), // DR_COMMENTS
					CommonUtil.getConvertedDate(providerDTO.getSpDurationTo()), // VALID_TO
					"R", // LICENSE_STATUS
					strUserId, // CREATED_BY
					strUserId, // UPDATE_BY
					filename, // DOCUMENT_NAME
					getlicenseCertificateTxnid()
			};
			String sqlQuery = CommonSQL.INSERT_SP_USER_DETAILS;
			dbUtil.createPreparedStatement(sqlQuery);
			flag1 = dbUtil.executeUpdate(sqlValues);
			if(flag1 && strFilePath!=null && filename!=null){
			try {
				flag = dbUtil.attachSpLicence(strFilePath, filename,
						licenceTxnId);
			} catch (Exception e) {
				dbUtil.rollback();
				dbUtil.closeConnection();
				logger.info(e);
			}
			}else{
				flag=true;
			}

			if (flag1 && flag) {
				dbUtil.commit();
				dbUtil.closeConnection();
				flag = true;
			} else {
				dbUtil.rollback();
				dbUtil.closeConnection();
				flag = false;
			}

		} catch (Exception e) {
		logger.info(e);
		}
		session.setAttribute("licenceTxnId", licenceTxnId);
		return flag;

	}

	public ArrayList getrefrenceId(String[] spDetails) throws Exception {
		ArrayList list = null;
		ArrayList list1=null;
		try {

			dbUtil
					.createPreparedStatement(CommonSQL.RETRIVE_REFRENCE_ID_SP_VIEW);
			list = dbUtil.executeQuery(spDetails);
			
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_REFRENCE_ID_SP_BANK_VIEW);
			list1=dbUtil.executeQuery(spDetails);
			list.addAll(list1);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public ArrayList getlicenceuserdetails(String[] spuserid) throws Exception {
		ArrayList list = null;
		try {
			
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_SP_VIEW_LICENCE_DETAILS_NEW);
			
			list = dbUtil.executeQuery(spuserid);
			
			//added by shruti
			if(list.size()==0)
			{
				dbUtil.createPreparedStatement(CommonSQL.RETRIVE_SP_BANK_VIEW_LICENCE_DETAILS);
				list = dbUtil.executeQuery(spuserid);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	
	//added by shruti
	public ArrayList getdeaclicenceuserdetails(String[] userid) throws Exception {
		ArrayList list = null;
		
		try {
			
			dbUtil
					.createPreparedStatement(CommonSQL.RETRIVE_DEAC_SP_LICENCE_DETAILS1);
			list = dbUtil.executeQuery(userid);
			if(list.size()==0)
			{
				dbUtil.createPreparedStatement(CommonSQL.RETRIVE_DEAC_SP_BANK_LICENCE_DETAILS1);
				list=dbUtil.executeQuery(userid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	//end of code
	
	
	//modified by shruti
	public ArrayList getSpUserTypeList(){
		ArrayList list1 = null;
		ArrayList list2 =null;
		ArrayList spUserTypeList = new ArrayList();
		 ServiceProviderDTO spusertypename;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			
			list2 = dbUtil.executeQuery(CommonSQL.SELECTUSERTYPE);
			for(int i=0;i<list2.size();i++)
			{
				list1= (ArrayList)list2.get(i);
				spusertypename= new ServiceProviderDTO();
				spusertypename.setSpusertypeid((String)list1.get(0));
				spusertypename.setSpusertypename((String)list1.get(1));
				spusertypename.setHdnspusertypename((String)list1.get(0)+"~"+(String)list1.get(1));
				logger.info((String)list1.get(0)+"~"+(String)list1.get(1));
				spUserTypeList.add(spusertypename);	
			}
		} catch (Exception exception) {
			
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		finally {
			try
			{
			dbUtil.closeConnection();
			}
			catch(Exception e)
			{
				logger.info("in DAO"+e);
			}
		}
		return spUserTypeList ;
	}
	
	public boolean storelicence(ServiceProviderDTO providerDTO) throws Exception {

		try {
			
			dbUtil.setAutoCommit(false);
			String licence[]={
					providerDTO.getSplicencenumber()
			};
			dbUtil.createPreparedStatement(CommonSQL.LICENCE_DETAILS);
			result=dbUtil.executeUpdate(licence);
			
		} catch (Exception exception) {
			logger.info("Exception in getstoredLicence-SPDAo" + exception);
		} finally {
			dbUtil.closeConnection();
		}
		return result;
	}
	
	public String getRecentLicenceNumber() throws Exception {
		String licencenumbr = "";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			licencenumbr = dbUtil.executeQry(CommonSQL.MM_LICENCE_NUMBER);
			
		} catch (Exception exception) {
			logger.info("Exception in getrecentlicencenumber-SPDAo" + exception);
		}
		return licencenumbr;

	}
	
	

	public boolean submitspBankDetails1(ServiceProviderDTO providerDTO,
			ArrayList listFileNames, String strFilePath, HttpSession session)
			throws Exception {
		
		dbUtil=new DBUtility();
		
		String licenceTxnId = this.getLicenceTxnNumber();
		String strUserId = (String) session.getAttribute("UserId");
		logger.info("licenceTxnId>>>>>>>>>>>>>>" + licenceTxnId);
		boolean flag = false;
		boolean flag1 = false;
		String filename = null;
		if (listFileNames != null) {
			ServiceProviderDTO serviceProviderDTO = (ServiceProviderDTO) listFileNames
					.get(0);
			filename = serviceProviderDTO.getFileName();
			logger.info("File Name" + filename);
		}
		try {
			dbUtil.setAutoCommit(false);
			String sqlValuesBank[] = {
					
					licenceTxnId, // LICENSE_TXN_ID
					providerDTO.getSplicencenumber(), // LICENSE_NUMBER
					//providerDTO.getSpusername(),//SP_BANK_USER_ID
					providerDTO.getSpusername(),
					//providerDTO.getSpuserid(),
					CommonUtil.getConvertedDate(providerDTO.getSpDurationFrom()),//VALID_FROM
					providerDTO.getSpOtherInformation(), // DR_COMMENTS
					CommonUtil.getConvertedDate(providerDTO.getSpDurationTo()), // VALID_TO
					//"D",//LICENSE_STATUS
					strUserId, // CREATED_BY
					strUserId, // UPDATE_BY
					providerDTO.getSpbankid(), // BANK_ID
					providerDTO.getSpdistrctid(),// DISTRICT_ID
					filename,//DOCUMENT_NAME
					providerDTO.getSpOfficeAddress(),//SP_BANK_OFFICE_ADDR
					getlicenseCertificateTxnid(),
					providerDTO.getBankAuthPerson(),
					providerDTO.getBankPersonDesignation(),
					providerDTO.getBankContactInfo()
					
			};
		
			//String sqlQuery = CommonSQL.INSERTSPBANKLICENCEDETAILS;
			String sqlQuery = CommonSQL.INSERTSPBANKLICENCEDETAILS_NEW1;
			dbUtil.createPreparedStatement(sqlQuery);
			flag1 = dbUtil.executeUpdate(sqlValuesBank);
			if(flag1 && strFilePath!=null && filename!=null){
			try {
				flag = dbUtil.attachSpBankLicence(strFilePath, filename,
						licenceTxnId);
			} catch (Exception e) {
				dbUtil.rollback();
				dbUtil.closeConnection();
				logger.info(e);
			}
			}else{
				flag=true;
			}

			if (flag1 && flag) {
				dbUtil.commit();
				dbUtil.closeConnection();
				flag = true;
			} else {
				dbUtil.rollback();
				dbUtil.closeConnection();
				flag = false;
			}

		} catch (Exception e) {
			logger.info(e);
		}
		session.setAttribute("licenceTxnId", licenceTxnId);
		return flag;

	}
	
	
	public boolean submitspBankDetails(ServiceProviderDTO providerDTO,
			HttpSession session) throws Exception {

		String licenceTxnId = this.getSPSerialNumber();
		String strUserId = (String) session.getAttribute("UserId");
		logger.info("licenceTxnId>>>>>>>>>>>>>>" + licenceTxnId);
		try {
			
			String splicence[] = {

					licenceTxnId, // LICENSE_TXN_ID
					providerDTO.getSplicencenumber(), // LICENSE_NUMBER
					providerDTO.getSpusername(),//SP_BANK_USER_ID
					CommonUtil.getConvertedDate(providerDTO.getSpDurationFrom()),//VALID_FROM
					providerDTO.getDrocomments(), // DRO_COMMENTS
					CommonUtil.getConvertedDate(providerDTO.getSpDurationTo()), // VALID_TO
					//"A",//LICENSE_STATUS
					strUserId, // CREATED_BY
					strUserId, // UPDATE_BY
					providerDTO.getSpbankid(), // BANK_ID
					providerDTO.getSpdistrctid() // DISTRICT_ID
	
			};

			dbUtil
					.createPreparedStatement(CommonSQL.INSERTSPBANKLICENCEDETAILS);
			result = dbUtil.executeUpdate(splicence);
			logger.info("DAO" + list);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		} finally {
			dbUtil.closeConnection();
		}

		return result;
	}
	/*
	 * *public ArrayList getUserDistrict(String userid) throws Exception
	{
		try{
			dbUtil = new DBUtility();
			String requserid[]={userid};
			dbUtil.createPreparedStatement(CommonSQL.SELECTUSERDISTRICT);
			list = dbUtil.executeQuery(requserid);
			logger.info("DAO" + list);
		}
		catch(Exception exception)
		{
			logger.info("Exception in SPDAO-user district "+exception);
		}
		finally
		{
			dbUtil.closeConnection();
		}
		return list;
	}
	 */
	//added by shruti
	public  ArrayList getThesil(String distId) throws Exception {
		
		ArrayList list = null;
		String arry[] = new String[1];
		if (distId != null) {
			arry[0] = distId;
		}
		try {
			dbUtil = new DBUtility();
			String reqdistId[]={distId};
			dbUtil.createPreparedStatement(CommonSQL.SELECTUSERTEHSILS);
			logger.debug("Wipro in ServiceProvider - "
					+ "getThesil() after PreparedStatement");
			list = dbUtil.executeQuery(reqdistId);
			logger.debug("Wipro in ServiceProvider - "
					+ "getTehsil() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}
	
	
	//added by shruti
	
	public boolean checkUserLicenceIssuance(String[] spuname)
	{
		ArrayList list = null;
		ArrayList list2 = null;
		boolean flag=true;
		try {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(CommonSQL.SPUSER_LICENCE_VALIDATION_QUERY);
				list = dbUtil.executeQuery(spuname);
				if(list.size()==0)
				{
					dbUtil.createPreparedStatement(CommonSQL.SPBANK_LICENCE_VALIDATION_QUERY);
					logger.info("before execute query in checkuserlicenceissuance");
					list2=dbUtil.executeQuery(spuname);
					logger.info("after execute query in checkuserlicenceissuance");
					if(list2.size()==0)
					{
					flag= false;
				}
				}
		} catch (Exception x) {
			
				logger.debug("Exception in checkUserLicenceIssuance() :- " + x);
				
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return flag;

	}
	
	
	
	
	public String getSpUserTypeId() throws Exception {
		String spusertypeid = "";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			spusertypeid = dbUtil.executeQry(CommonSQL.SPUSERTYPEID);
			
		} catch (Exception exception) {
			logger.info("Exception in getrecentlicencenumber-SPDAo" + exception);
		}
		return spusertypeid;

	}
	
	public String getSpBankUserTypeId() throws Exception {
		String spbankusertypeid = "";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			spbankusertypeid = dbUtil.executeQry(CommonSQL.SPBUSERTYPEID);
			
		} catch (Exception exception) {
			logger.info("Exception in getrecentlicencenumber-SPDAo" + exception);
		}
		return spbankusertypeid;

	}
	
	
	
	public boolean submitspUserDetailsNew(ServiceProviderDTO providerDTO,
			ArrayList listFileNames, String strFilePath, HttpSession session,String[] spUser)
			throws Exception {
		String licenceTxnId = this.getLicenceTxnNumber();
		String strUserId = (String) session.getAttribute("UserId");
		logger.info("licenceTxnId>>>>>>>>>>>>>>" + licenceTxnId);
		boolean flag = false;
		boolean flag1 = false;
		String filename = null;
		if (listFileNames != null) {
			ServiceProviderDTO serviceProviderDTO = (ServiceProviderDTO) listFileNames.get(0);
			filename = serviceProviderDTO.getFileName();
			logger.info("File Name" + filename);
		}
		try {
			dbUtil.setAutoCommit(false); 
			
			ServiceProviderDAO providerDAO=new ServiceProviderDAO();
			String sysDate=providerDAO.getCurrDateTime();
		
			DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate=(Date)formatter.parse(providerDTO.getSpDurationFrom());
			logger.info("FROM DATE------------*************"+fromDate);
			Date toDate=(Date)formatter.parse(providerDTO.getSpDurationTo());
			logger.info("TO DATE------------*************"+toDate);
			Date currentDate=(Date)formatter.parse(sysDate);
			logger.info("CURRENT DATE------------*************"+currentDate);
			
			if((fromDate.compareTo(currentDate)<0 )||(fromDate.compareTo(currentDate)==0))
				{
				String sqlValues[] = {
										licenceTxnId, // licenceTxnId,
										providerDTO.getSplicencenumber(), // LICENSE_NO
										providerDTO.getSpusername(), // SP_USER_ID
										CommonUtil.getConvertedDate(providerDTO.getSpDurationFrom()), // VALID_FROM
										providerDTO.getSpdistrctid(), // DISTRICT_ID
										providerDTO.getSptehsilid(), // TEHSIL_ID
										providerDTO.getSpOfficeAddress(), // SP_OFFICE_ADDR
										providerDTO.getSppostalcode(), // PINCODE
										providerDTO.getSpOtherInformation(), // DR_COMMENTS
										CommonUtil.getConvertedDate(providerDTO.getSpDurationTo()), // VALID_TO
										"A", // LICENSE_STATUS
										strUserId, // CREATED_BY
										strUserId, // UPDATE_BY
										filename, // DOCUMENT_NAME
										getlicenseCertificateTxnid()//LICENSE_CER_TXN_ID
								    };
			String sqlQuery = CommonSQL.INSERT_SP_USER_DETAILS;
			dbUtil.createPreparedStatement(sqlQuery);
			flag1 = dbUtil.executeUpdate(sqlValues);
				if (flag1) {
					String s[]={spUser[2]};
					dbUtil.createPreparedStatement(CommonSQL.UPDATE_USERS_LIST_SP_ROLE);
					flag1=dbUtil.executeUpdate(s);
					
				if(spUser.length > 0)
				    {
				     dbUtil.createPreparedStatement(CommonSQL.USER_ROLE_INSERT);
					flag1= dbUtil.executeUpdate(spUser);
					dbUtil.commit();
				    }
			}
				}
			
			else if((fromDate.compareTo(currentDate)>0 ))
					{
				String sqlValues[] = {
						licenceTxnId, // licenceTxnId,
						providerDTO.getSplicencenumber(), // LICENSE_NO
						providerDTO.getSpusername(), // SP_USER_ID
						CommonUtil.getConvertedDate(providerDTO.getSpDurationFrom()), // VALID_FROM
						providerDTO.getSpdistrctid(), // DISTRICT_ID
						providerDTO.getSptehsilid(), // TEHSIL_ID
						providerDTO.getSpOfficeAddress(), // SP_OFFICE_ADDR
						providerDTO.getSppostalcode(), // PINCODE
						providerDTO.getSpOtherInformation(), // DR_COMMENTS
						CommonUtil.getConvertedDate(providerDTO.getSpDurationTo()), // VALID_TO
						"C", // LICENSE_STATUS
						strUserId, // CREATED_BY
						strUserId, // UPDATE_BY
						filename, // DOCUMENT_NAME
						getlicenseCertificateTxnid()//LICENSE_CER_TXN_ID
				    };
				String sqlQuery = CommonSQL.INSERT_SP_USER_DETAILS;
				dbUtil.createPreparedStatement(sqlQuery);
				flag1 = dbUtil.executeUpdate(sqlValues);
					}
			
			if(flag1 && strFilePath!=null && filename!=null){
			try {
				flag = dbUtil.attachSpLicence(strFilePath, filename,
						licenceTxnId);
			} catch (Exception e) {
				dbUtil.rollback();
				dbUtil.closeConnection();
				logger.info(e);
			}
			}else{
				flag=true;
			}

			if (flag1 && flag) {
				dbUtil.commit();
				dbUtil.closeConnection();
				flag = true;
			} else {
				dbUtil.rollback();
				dbUtil.closeConnection();
				flag = false;
			}

		}
		
		catch (Exception e) {
			logger.info(e);
		}
		session.setAttribute("licenceTxnId", licenceTxnId);
		return flag;

	}
	
	public boolean submitspBankDetailsNew(ServiceProviderDTO providerDTO,
			ArrayList listFileNames, String strFilePath, HttpSession session,String[] spBankUser)
			throws Exception {
		String licenceTxnId = this.getLicenceTxnNumber();
		String strUserId = (String) session.getAttribute("UserId");
		logger.info("licenceTxnId>>>>>>>>>>>>>>" + licenceTxnId);
		boolean flag = false;
		boolean flag1 = false;
		String filename = null;
		if (listFileNames != null) {
			ServiceProviderDTO serviceProviderDTO = (ServiceProviderDTO) listFileNames
					.get(0);
			filename = serviceProviderDTO.getFileName();
			logger.info("File Name" + filename);
		}
		try {
			dbUtil.setAutoCommit(false);
			
			ServiceProviderDAO providerDAO=new ServiceProviderDAO();
			String sysDate=providerDAO.getCurrDateTime();
		
			DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate=(Date)formatter.parse(providerDTO.getSpDurationFrom());
			Date toDate=(Date)formatter.parse(providerDTO.getSpDurationTo());
			Date currentDate=(Date)formatter.parse(sysDate);
				
			if((fromDate.compareTo(currentDate)<0)||(fromDate.compareTo(currentDate)==0))
			{
			String sqlValuesBank[] = {
					licenceTxnId, // LICENSE_TXN_ID
					providerDTO.getSplicencenumber(), // LICENSE_NUMBER
					//providerDTO.getSpusername(),//SP_BANK_USER_ID
					providerDTO.getSpusername(),
					//providerDTO.getSpuserid(),
					CommonUtil.getConvertedDate(providerDTO.getSpDurationFrom()),//VALID_FROM
					providerDTO.getSpOtherInformation(), // DR_COMMENTS
					CommonUtil.getConvertedDate(providerDTO.getSpDurationTo()), // VALID_TO
					"A",//LICENSE_STATUS
					strUserId, // CREATED_BY
					strUserId, // UPDATE_BY
					providerDTO.getSpbankid(), // BANK_ID
					providerDTO.getSpdistrctid(),// DISTRICT_ID
					filename,//DOCUMENT_NAME
					providerDTO.getSpOfficeAddress(),//SP_BANK_OFFICE_ADDR
					getlicenseCertificateTxnid(),//LICENSE_CER_TXN_ID
					providerDTO.getBankAuthPerson(),
					providerDTO.getBankPersonDesignation(),
					providerDTO.getBankContactInfo()
			};
			//String sqlQuery = CommonSQL.INSERTSPBANKLICENCEDETAILS;
			String sqlQuery = CommonSQL.INSERTSPBANKLICENCEDETAILS_NEW;
			dbUtil.createPreparedStatement(sqlQuery);
			flag1 = dbUtil.executeUpdate(sqlValuesBank);
			if (flag1) {
				String s[]={spBankUser[2]};
				dbUtil.createPreparedStatement(CommonSQL.UPDATE_USERS_LIST_SPBANK_ROLE);
				flag1=dbUtil.executeUpdate(s);
				if(spBankUser.length > 0)
				    {
				    logger.info("length is "+spBankUser.length);
				     dbUtil.createPreparedStatement(CommonSQL.USER_ROLE_INSERT);
					flag1= dbUtil.executeUpdate(spBankUser);
						logger.info("AFTER  :  User Credentials");
						dbUtil.commit();
				    }
			}
			}
			
			else if(fromDate.compareTo(currentDate)>0)
			//else if(!(CommonUtil.getConvertedDate(providerDTO.getSpDurationFrom())==CommonUtil.getConvertedDate(dateString)))
			{
		String sqlValues[] = {
				licenceTxnId, // LICENSE_TXN_ID
				providerDTO.getSplicencenumber(), // LICENSE_NUMBER
				//providerDTO.getSpusername(),//SP_BANK_USER_ID
				providerDTO.getSpusername(),
				//providerDTO.getSpuserid(),
				CommonUtil.getConvertedDate(providerDTO.getSpDurationFrom()),//VALID_FROM
				providerDTO.getSpOtherInformation(), // DR_COMMENTS
				CommonUtil.getConvertedDate(providerDTO.getSpDurationTo()), // VALID_TO
				"C",//LICENSE_STATUS
				strUserId, // CREATED_BY
				strUserId, // UPDATE_BY
				providerDTO.getSpbankid(), // BANK_ID
				providerDTO.getSpdistrctid(),// DISTRICT_ID
				filename,//DOCUMENT_NAME
				providerDTO.getSpOfficeAddress(),//SP_BANK_OFFICE_ADDR
				getlicenseCertificateTxnid(),//LICENSE_CER_TXN_ID
				providerDTO.getBankAuthPerson(),
				providerDTO.getBankPersonDesignation(),
				providerDTO.getBankContactInfo()
		    };
		String sqlQuery = CommonSQL.INSERTSPBANKLICENCEDETAILS_NEW;
		dbUtil.createPreparedStatement(sqlQuery);
		flag1 = dbUtil.executeUpdate(sqlValues);
			}
			if(flag1 && strFilePath!=null && filename!=null){
			try {
				flag = dbUtil.attachSpBankLicence(strFilePath, filename,
						licenceTxnId);
			} catch (Exception e) {
				dbUtil.rollback();
				dbUtil.closeConnection();
				logger.info(e);
			}
			}else{
				flag=true;
			}

			if (flag1 && flag) {
				dbUtil.commit();
				dbUtil.closeConnection();
				flag = true;
			} else {
				dbUtil.rollback();
				dbUtil.closeConnection();
				flag = false;
			}

		} catch (Exception e) {
			logger.info(e);
		}
		session.setAttribute("licenceTxnId", licenceTxnId);
		return flag;

	}
	
	//added for deactivating account for service provider
	public boolean deac(ServiceProviderDTO providerDTO,String[] deacUser)
	{
		boolean flag = false;
		try {
			String userdetails[] = {
					
					providerDTO.getSpOtherInformation(),
					providerDTO.getSpusername()
			};
			dbUtil.createPreparedStatement(CommonSQL.DEAC_SP_ACNT);
			flag=dbUtil.executeUpdate(userdetails);
			
			if(flag==false)
			{
				dbUtil.createPreparedStatement(CommonSQL.DEAC_SPBANK_ACNT);
				flag=dbUtil.executeUpdate(userdetails);
			}
			
			if (flag) {
				if(deacUser.length > 0)
				    {
					String uid[]=new String[1];
					uid[0]=deacUser[3].toString();
					logger.info("uidDAO"+uid[0].toString());
					dbUtil.createPreparedStatement(CommonSQL.DEAC_USER_LIST_ROLE);
					flag=dbUtil.executeUpdate(uid);
					
					if(flag==true)
					{

					     String deacUser1[]=new String[3];
					     deacUser1[0]=deacUser[0].toString();
					    deacUser1[1]=deacUser[1].toString();
					    deacUser1[2]=deacUser[2].toString();
				     dbUtil.createPreparedStatement(CommonSQL.UPDATE_USER_ACNT);
				     flag= dbUtil.executeUpdate(deacUser1);
					}
					logger.info("ACNT DEACTIVATED AND ROLE GROUP ALSO CHANGED");
						dbUtil.commit();
				    }
			}
			
		}
		catch(Exception e)
		{
			logger.info(e);
			}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return flag;
		}
			
			//added for license certificate id 
	private String getlicenseCertificateTxnid() throws Exception {
		String licencecertificatetxnidseq = "";
		try {
			dbUtil.createStatement();
			licencecertificatetxnidseq = dbUtil
					.executeQry(CommonSQL.CERTIFICATE_TXN_ID);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return licencecertificatetxnidseq;

	}
	
	
	//added
	public ArrayList getrenewlicenceuserdetails(String[] userid) throws Exception {
		ArrayList list = null;
		
		try {
			dbUtil = new DBUtility();
			
			dbUtil
					.createPreparedStatement(CommonSQL.RETRIVE_RENEW_SP_LICENCE_DETAILS);
			list = dbUtil.executeQuery(userid);
			if(list.size()==0)
			{
				dbUtil.createPreparedStatement(CommonSQL.RETRIVE_RENEW_SP_BANK_LICENCE_DETAILS1);
				list=dbUtil.executeQuery(userid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	
	
	
	public boolean StatusAtLicenseRenewal(ServiceProviderDTO providerDTO) throws Exception {
	
		boolean flag=false;
		
		try {
			
			dbUtil = new DBUtility();
			
			String username[]={
					providerDTO.getSpusername()
			};
			
			dbUtil.createPreparedStatement(CommonSQL.LICENSE_STATUS_CHANGE_SP);
			flag=dbUtil.executeUpdate(username);
			if(flag==false)
			{
				dbUtil.createPreparedStatement(CommonSQL.LICENSE_STATUS_CHANGE_SPBANK);
				flag=dbUtil.executeUpdate(username);
			}
			
		} catch (Exception exception) {
			logger.info("Exception in getNewSPSerialNumber" + exception);
		}
		return flag;

	}
	public String getCurrDateTime() throws Exception {

        String currDateTime = new String();
        try {
                dbUtil = new DBUtility();
                dbUtil.createStatement();
                currDateTime = dbUtil.executeQry(CommonSQL.CURRENT_DATE_TIME);
                dbUtil.commit();
                
        } catch (Exception exception) {

                logger.debug("Exception in getCurrDateTime" + exception);
        }
        return currDateTime;
}

	public String getCurrentDate() throws Exception {

        String currentDate = new String();
        try {
                dbUtil = new DBUtility();
                dbUtil.createStatement();
                currentDate = dbUtil.executeQry(CommonSQL.GETCURRENTDATE);
                dbUtil.commit();
                
        } catch (Exception exception) {

                logger.debug("Exception in getCurrDateTime" + exception);
        }
        return currentDate;
}

	public boolean checkUserLicenceRenewal(String[] spuname)
	{
		ArrayList list = null;
		ArrayList list2 = null;
		boolean flag=true;
		try {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(CommonSQL.SPUSER_LICENCE_RENEWED_VALIDATION_QUERY);
				list = dbUtil.executeQuery(spuname);
				if(list.size()==0)
				{
					dbUtil.createPreparedStatement(CommonSQL.SPBANK_LICENCE_RENEWED_VALIDATION_QUERY);
					logger.info("before execute query in checkuserlicencerenewal");
					list2=dbUtil.executeQuery(spuname);
					logger.info("after execute query in checkuserlicencerenewal");
					if(list2.size()==0)
					{
					flag= false;
				}
				}
		} catch (Exception x) {
			
				logger.debug("Exception in checkUserLicenceRenewal() :- " + x);
				
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return flag;

	}
	
	
	
}
	