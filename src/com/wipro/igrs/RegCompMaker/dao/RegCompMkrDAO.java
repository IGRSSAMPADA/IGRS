package com.wipro.igrs.RegCompMaker.dao;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.RegCompMaker.constants.RegCompConstant;
import com.wipro.igrs.RegCompMaker.dto.CommonMkrDTO;
import com.wipro.igrs.RegCompMaker.dto.OthrDeedDtlsDTO;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteDutiesDTO;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;
import com.wipro.igrs.RegCompMaker.form.RegCompleteMakerForm;
import com.wipro.igrs.RegCompMaker.sql.RegCommonMkrSQL;
import com.wipro.igrs.RegCompMaker.util.PropertiesFileReader;
import com.wipro.igrs.Seals.sql.SealsSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.device.applet.EtokenChange;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.sql.RegCompCheckerSQL;
import com.wipro.igrs.reginit.sql.RegCommonSQL;

/**
 * ===========================================================================
 * File : RegCompMkrDAO.java Description : Represents the RegCompMkr DAO Class
 * 
 * @author : Ankita Created Date : January 18, 2013 Updated Date Version
 *         UpdatedBy
 * 
 * 
 *         
 *         ======================================================================
 *         =====
 */

public class RegCompMkrDAO {

	
	String sql = null;
	String sqlOne = null;
	ArrayList mainList = null;
	CommonMkrDTO dto = null;
	//IGRSCommon common = null;
	private Logger logger = Logger.getLogger(RegCompMkrDAO.class);
	private PropertiesFileReader pr;
	//IGRSCommon igrsCommon = null;

	public RegCompMkrDAO() {
		try {
			//igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			logger.error("RegCompMkrDAO in dao start" + e.getStackTrace());
		}
	}

	// * Start :==== Common Functions

	/**
	 * $1. Common Function : Getting Country
	 * 
	 * @return ArrayList
	 * */
	public ArrayList getCountry() {
		mainList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon();
			ArrayList list = igrsCommon.getCountry();

			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonMkrDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonMkrDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCommonmkrDAO in dao start"
								+ e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * $2. for getting all State details
	 * 
	 * @param param
	 * @return ArrayList
	 */
	public ArrayList getState(String param) {
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon();
			ArrayList list = igrsCommon.getState(param);
			mainList = new ArrayList();
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonMkrDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonMkrDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCommonMkrDAO in dao start"
								+ e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * $3.for getting all District details
	 * 
	 * @param stateId
	 * @return ArrayList
	 */
	public ArrayList getDistrict(String stateId) {
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon();
			ArrayList list = igrsCommon.getDistrict(stateId);
			mainList = new ArrayList();
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonMkrDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCommonMkrDAO in dao start" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCommonMkrDAO in dao start"
								+ e.getStackTrace());
			}
		}
		return mainList;
	}

	// * End :==== Common Functions

	/**
	 * $1 returns the given application details
	 * 
	 * @param regNo
	 * @return ApplicantForm
	 */
	/*public RegCompleteMakerDTO getRegApplicationDetails(String regNumber) {

		RegCompleteMakerDTO dto = new RegCompleteMakerDTO();

		try {
			dbUtility = new DBUtility();
			sql = RegCommonMkrSQL.SELECT_APP_INFO + "'" + regNumber + "'";
			dbUtility.createStatement();
			mainList = new ArrayList();
			mainList = dbUtility.executeQuery(sql);

			if (mainList != null) {
				ArrayList list = (ArrayList) mainList.get(0);

				if (list != null) {
					dto.setRegNumber(list.get(0).toString());
					// party name is 1
					logger.debug("list.get(2).toString():-"
							+ list.get(2).toString());

					dto.setFname(list.get(2).toString());
					dto.setMname(list.get(3).toString());
					dto.setLname(list.get(4).toString());
					dto.setGender(list.get(5).toString());
					dto.setAge(list.get(6).toString());
					dto.setNationality(list.get(7).toString());
					dto.setIndcountry(list.get(8).toString());
					dto.setIndstatename(list.get(9).toString());
					dto.setInddistrict(list.get(10).toString());
					dto.setIndaddress(list.get(11).toString());
					dto.setPostalCode(list.get(12).toString());
					dto.setIndphno(list.get(13).toString());
					dto.setIndmobno(list.get(14).toString());
					dto.setEmailID(list.get(15).toString());
					dto.setListID(list.get(16).toString());
					dto.setIdno(list.get(17).toString());
					dto.setBname(list.get(18).toString());
					dto.setBaddress(list.get(19).toString());

					// organization name is 20
					dto.setFatherName(list.get(21).toString());
					dto.setMotherName(list.get(22).toString());

				}

			}

		} catch (Exception e) {
			logger.error("RegCompMkrDAo in dao getRegApplicationDetails"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCompMkrDAO in dao getRegApplicationDetails close: "
								+ e.getStackTrace());
			}
		}

		return dto;
	}*/

	public String checkValidRegistration(String regID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList getComplList() {
		ArrayList compList = new ArrayList();
		String SQL;
		logger
				.info("Wipro in RegCompMkrDAO - getComplList() before EXCUTIN QUERY");
		SQL = RegCommonMkrSQL.COMPLIANCE_LIST_QUERY;
		DBUtility dbUtility = null;
		try {
			 dbUtility = new DBUtility();
			dbUtility.createStatement();
			compList = dbUtility.executeQuery(SQL);
			dbUtility.closeConnection();
			logger
					.info("Wipro in RegCompMkrDAO - getComplList() AFTER EXCUTIN QUERY"
							+ SQL);
			logger
					.info("Wipro in RegCompMkrDAO - getComplList() AFTER EXCUTIN QUERY list values"
							+ compList);
		} catch (Exception e) {
			logger
					.error("Wipro in RegCompMkrDAO -Exception in calling at DAO Class at getComplList ()  "
							+ e);
		}
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return compList;
	}

	public ArrayList getCompDeedList(String regNumber) throws Exception {
		ArrayList deedList = new ArrayList();
		String[] regParam = new String[1];
		regParam[0] = regNumber;
		DBUtility dbUtil = new DBUtility();
		ArrayList deedTxnList;
		try {
			dbUtil
					.createPreparedStatement(RegCommonMkrSQL.SELECT_REG_DEED_DETAIL);
			deedTxnList = dbUtil.executeQuery(regParam);

			if (deedTxnList != null) {
				for (int i = 0; i < deedTxnList.size(); i++) {
					ArrayList list = (ArrayList) deedTxnList.get(i);
					RegCompleteMakerDTO regdto = new RegCompleteMakerDTO();
					regdto.setDeedTxnId(list.get(0).toString());
					deedList.add(regdto);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
			dbUtil.commit();
		} finally {
			dbUtil.closeConnection();
		}
		return deedList;
	}

	public ArrayList getDeedType() {
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonMkrSQL.SELECT_DEED_TYPE;
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			mainList = new ArrayList();
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonMkrDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCompmkrDAO in dao getDeedType:"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompmkrDAO in dao getDeedType close:"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}

	public ArrayList getPropertyListByRegId(String regNumber) {
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			// String arr[] = { regId };
			dbUtility
					.createPreparedStatement(RegCommonMkrSQL.SELECT_PROP_BY_REGID);
			ArrayList list = dbUtility.executeQuery(regNumber);
			mainList = new ArrayList();
			ArrayList subList = new ArrayList();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					subList = (ArrayList) list.get(i);
					// Added By Aruna
					if (!isPropertyMapped((String) subList.get(0), dbUtility)) {
						dto = new CommonMkrDTO();
						dto.setPropertyTxnId(subList.get(0).toString());
						mainList.add(dto);
					}
				}
			}
		} catch (Exception e) {
			logger.error("RegCompMkrDAO in dao getPropertyListByRegId:"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getPropertyListByRegId close:"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}

	public boolean isPropertyMapped(String propertyTxnID, DBUtility dbUtil)
			throws Exception {
		ArrayList dList = new ArrayList();
		 dbUtil = new DBUtility();
		boolean propertyMapped = false;
		try {
			String arr[] = new String[1];
			arr[0] = propertyTxnID;
			String sql =RegCommonMkrSQL.PROPERTY_MAPPED;
			dbUtil.createPreparedStatement(sql);
			ArrayList countList = dbUtil.executeQuery(arr);

			if (countList != null) {
				for (int i = 0; i < countList.size(); i++) {
					ArrayList list = (ArrayList) countList.get(i);
					String counter = (String) list.get(0);
					int count = Integer.parseInt(counter);
					if (count > 0) {
						propertyMapped = true;
					}
				}
			}

		} catch (Exception x) {
			logger.debug(x);
		} finally {
			 dbUtil.closeConnection();
		}

		return propertyMapped;
	}

	/**
	 * $1 checks if the registration initiation is complete created Date
	 * 8/02/2013
	 * 
	 * @author ankita
	 * @param regNo
	 * @return Boolean
	 */

	// Start
	public Boolean checkID(String regNumber) throws Exception {

		Boolean Flag = true;
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			dbUtility.createStatement();
			mainList = new ArrayList();
			String arr[] = {regNumber};
			sql =  RegCommonMkrSQL.CHECK_ID;
			dbUtility.createPreparedStatement(sql);
			mainList = dbUtility.executeQuery(arr);
			if (mainList != null && mainList.size()>0) {
				for (int i = 0; i < mainList.size(); i++) {
					System.out.println(i);
					ArrayList list = (ArrayList) mainList.get(i);

					if (list != null) {
						System.out.println(list.get(1).toString());
						if (!list.get(1).toString().equalsIgnoreCase("10") || (list.get(1).toString().equalsIgnoreCase("10") && "A".equalsIgnoreCase(((String)list.get(2))))) {
							Flag = false;
						}
					}
				}

			}else{
				Flag = false;
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
		System.out.println(Flag);
		return Flag;
	}
	
	@SuppressWarnings("deprecation")
	public String checkEtokenFlag(String officeId) throws Exception {
		String status = null;
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			String sql =  RegCommonMkrSQL.CHECK_ETOKEN_FLAG;
			dbUtility.createPreparedStatement(sql);
			status = dbUtility.executeQry(new String[]{officeId});
		} catch (Exception e) {
			logger.debug("Exception in checkEtokenFlag Checker" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		return status;
	}
	
	
	@SuppressWarnings("deprecation")
	public String checkEtokenUserId(String regId,String userId) throws Exception {
		String mappingId = "";
		String counterUserId="";
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			String sql =  RegCommonMkrSQL.GET_COUNTER_MAPPING_ID;
			dbUtility.createPreparedStatement(sql);
			mappingId = dbUtility.executeQry(new String[]{regId});
			if(mappingId!=null || mappingId!=""){
			String query =  RegCommonMkrSQL.GET_COUNTER_USER_ID;
			dbUtility.createPreparedStatement(query);
			counterUserId = dbUtility.executeQry(new String[]{mappingId});}
		} catch (Exception e) {
			logger.debug("Exception in checkEtokenFlag Checker" + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		return counterUserId;
	}

	// End

	/**
	 * $2 returns the property id given a regnumber created Date 8/02/2013
	 * 
	 * @author ankita
	 * @param regNo
	 * @return PropertyId Arraylist
	 */

	public ArrayList getPropertyIdList(String regNumber) throws Exception {
		ArrayList propIdList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
		//	dbUtility.createStatement();
			sql = RegCommonMkrSQL.GET_PROPERTY_ID;
			dbUtility.createPreparedStatement(sql);
			// Commented by Simran
			/*sql = "Select p.PROPERTY_ID from IGRS_REG_INIT_PROPRTY_DTLS  p"
					+ " where REG_TXN_ID='" + regNumber + "'"
					+ "ORDER BY p.PROPERTY_ID ASC";*/
			

			//COMMENTED BY VINAY
			/*	sql = "Select p.PROPERTY_ID from IGRS_REG_PROPRTY_DTLS p " +
					"where REG_TXN_ID= '" + regNumber + "'" +
					"ORDER BY p.PROPERTY_ID ASC";
			*/
			String arr[]={regNumber};
			mainList = new ArrayList();
			mainList = dbUtility.executeQuery(arr);

			if (mainList.size() != 0) {
				System.out.println(mainList.size());
				for (int i = 0; i < mainList.size(); i++) {
					System.out.println(i);
					ArrayList list = (ArrayList) mainList.get(i);
					String str = (String) list.get(0);
					System.out.println(str);
					RegCompleteMakerDTO regdto = new RegCompleteMakerDTO();

					regdto.setPropId(str);
					System.out.println(regdto.getPropId());
					propIdList.add(regdto);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
			logger.error("error in getProperty method of RegCompMkrDAO"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("error in getProperty method close dbUtility of RegCompMkrDAO :"
								+ e.getStackTrace());
			}

		}
		logger.debug(propIdList.size());

		return propIdList;
	}

	/**
	 * $3 returns the linked amount on the basis of regnum or ecode or phy stmp
	 * num created Date 12/02/2013
	 * 
	 * @author ankita
	 * @param tempduty
	 * @param tempfee
	 * @param num
	 *            ,searchBy
	 * @return linkedAmtList Arraylist
	 */

	public ArrayList getLinkedAmt(String num, String searchBy) throws Exception {
		ArrayList linkedAmtList = new ArrayList();
		DBUtility dbUtility = null;
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String flag = "";

		int tempFee = 0;

		try {
			String arr [] = {num};
			if (RegCompConstant.SEARCH_BY_REGNUM.equalsIgnoreCase(searchBy)) {
				sql = RegCommonMkrSQL.GET_LINKED_AMOUNT_BY_REG_NUMBER ;
						
				flag = "1";// flag=1 search by reg number
			} else if (RegCompConstant.SEARCH_BY_ESTAMP
					.equalsIgnoreCase(searchBy)) {
				sql = RegCommonMkrSQL.GET_LINKED_AMOUNT_BY_ESTAMP ;
				flag = "2"; // flag=2 search by ecode
			} else if (RegCompConstant.SEARCH_BY_PHYSTAMP
					.equalsIgnoreCase(searchBy)) {
				sql = RegCommonMkrSQL.GET_LINKED_AMOUNT_BY_PHYSICAL_STAMP;
						
				flag = "3"; // flag=3 search by physical code
			}
			else if(RegCompConstant.SEARCH_BY_OLD_REG
					.equalsIgnoreCase(searchBy))
			{
				sql = RegCommonMkrSQL.GET_LINKED_AMOUNT_BY_PHYSICAL_STAMP;
				
				flag = "4";
			}
			//logger.debug(sql);
			mainList = new ArrayList();
			if(!flag.equalsIgnoreCase("3")&&!flag.equalsIgnoreCase("4"))
			{
				dbUtility.createPreparedStatement(sql);
			
			mainList = dbUtility.executeQuery(arr);
			}
			CommonMkrDTO cdto = new CommonMkrDTO();
		//	logger.debug("mainList---size"+mainList.size());
			
			if(RegCompConstant.SEARCH_BY_REGNUM.equalsIgnoreCase(searchBy))
			{
				if (mainList.size() != 0) {
					String str = mainList.toString();
					str = str.substring(2, (str.length() - 2));
					System.out.println(str);
					String str1[] = str.split(",");
					System.out.println(str1[0] + str1[1] + str1[2]);
					cdto.setLinkedNum(str1[0]);
					cdto.setLinkedFlag(flag);
					if(str1[2].trim().equals("")||str1[2].trim().equals(null))
					{
						str1[2] = "0";
					}
					
					if(str1[1].trim().equals("")||str1[1].trim().equals(null))
					{
						str1[1] = "0";
					}
					double lnkdTtlRegFee = Integer.parseInt(str1[2].trim());
					double lnkdTtlDuty = Integer.parseInt(str1[1].trim());
					cdto.setLinkedTotalRegFee(lnkdTtlRegFee);
					cdto.setLinkedTotalDuty(lnkdTtlDuty);

					linkedAmtList.add(cdto);
					System.out.println("<-------"+lnkdTtlRegFee);
					System.out.println("<-------"+lnkdTtlDuty);
					System.out.println("<------size of arraylist"+linkedAmtList);

				}
				else if(mainList.size() == 0)
				{	
					cdto.setLinkedNum(num);
					cdto.setLinkedFlag(flag);
					cdto.setLinkedTotalRegFee(0.0);
					cdto.setLinkedTotalDuty(0.0);
					linkedAmtList.add(cdto);
				}
			}
			else if(RegCompConstant.SEARCH_BY_ESTAMP.equalsIgnoreCase(searchBy))
			{
				if (mainList.size() != 0) {
					String str = mainList.toString();
					str = str.substring(2, (str.length() - 2));
					System.out.println(str);
					String str1[] = str.split(",");
					System.out.println(str1[0] + str1[1]);
					cdto.setLinkedNum(str1[0]);
					cdto.setLinkedFlag(flag);
					/*if(str1[2].trim().equals("")||str1[2].trim().equals(null))
					{
						str1[2] = "0";
					}*/
					
					if(str1[1].trim().equals("")||str1[1].trim().equals(null))
					{
						str1[1] = "0";
					}
					double lnkdTtlRegFee = 0.0;
					double lnkdTtlDuty = Integer.parseInt(str1[1].trim());
					cdto.setLinkedTotalRegFee(lnkdTtlRegFee);
					cdto.setLinkedTotalDuty(lnkdTtlDuty);

					linkedAmtList.add(cdto);
					System.out.println("<-------"+lnkdTtlRegFee);
					System.out.println("<-------"+lnkdTtlDuty);
					System.out.println("<------size of arraylist"+linkedAmtList);

				}
				else if(mainList.size() == 0)
				{	
					cdto.setLinkedNum(num);
					cdto.setLinkedFlag(flag);
					cdto.setLinkedTotalRegFee(0.0);
					cdto.setLinkedTotalDuty(0.0);
					linkedAmtList.add(cdto);
				}
			}
			else
			{
				cdto.setLinkedNum(num);
				cdto.setLinkedFlag(flag);
				cdto.setLinkedTotalRegFee(0.0);
				cdto.setLinkedTotalDuty(0.0);
				linkedAmtList.add(cdto);
			}
			
			
		} catch (Exception e) {
			logger.debug(e);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("error in close connection getLinkedAmt method of RegCompMkrDao"
								+ e.getStackTrace());
			}

		}
		return linkedAmtList;
	}

	public RegCompleteDutiesDTO getTotalDuties(String regNumber)
			throws Exception {
		// TODO Auto-generated method stub

		RegCompleteDutiesDTO dutydto = new RegCompleteDutiesDTO();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String arr[]  = {regNumber};
			sql = RegCommonMkrSQL.GET_TOTAL_DUTY ;
			mainList = new ArrayList();
			dbUtility.createPreparedStatement(sql);
			mainList = dbUtility.executeQuery(arr);

			if (mainList != null) {
				ArrayList list = (ArrayList) mainList.get(0);

				if (list != null) {
					System.out.println(list.get(1).toString());
					System.out.println(list.get(2).toString());
					System.out.println(list.get(3).toString());
					System.out.println(list.get(4).toString());
					dutydto.setStampduty(list.get(1).toString());
					dutydto.setMunicipalDuty(list.get(2).toString());
					dutydto.setJanpadDuty(list.get(3).toString());
					dutydto.setUpkarDuty(list.get(4).toString());
					dutydto.setTotalduty(list.get(5).toString());
					dutydto.setRegistrationFee(list.get(6).toString());
					dutydto.setEstampCode(list.get(7).toString());
					dutydto.setPaymentId(list.get(8).toString());
					dutydto.setPaymentAmt(list.get(10).toString());
				}

			}

		} catch (Exception e) {
			logger.error("error in RegCompleteDutiesDTO() RegCompMkrDAO"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("error in close conection In  getTotal Duties of RegCompMkrDAO"
								+ e.getStackTrace());
			}
		}

		return dutydto;
	}

	public boolean saveLinkedAmt(String regNum, Map myMap, double balDuty,
			double balfee, String compRegNumber, String userId, String cdate,RegCompleteMakerDTO dto) throws Exception {
		boolean flag = false;
		String cdate1 = getDate(cdate);
		Map newMap = myMap;
		ArrayList subList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			Iterator<Map.Entry<String, ArrayList>> entries = newMap.entrySet()
					.iterator();
			while (entries.hasNext()) {
				Map.Entry<String, ArrayList> entry = entries.next();
				System.out.println("Key = " + entry.getKey() + ", Value = "
						+ entry.getValue());
				String key = entry.getKey();
				ArrayList list = entry.getValue();
				System.out.println("size of list" + list.size());
				System.out.println(list);
				String str = list.toString();
				str = str.substring(1, (str.length() - 1));
				System.out.println(str);
				String str1[] = str.split(",");
				/*
				 * this loop has to be started from i=1 because first element of
				 * the list is value already paid which is not to be stored. *
				 */
				//for (int i = 1; i < str1.length; i++) {   // commented by Simran
				for (int i = 0; i < str1.length; i++) {
					System.out.println(str1[i]);
					String str2[] = str1[i].split("~");
					System.out.println("regnum" + i + str2[0]);
					System.out.println("flag" + str2[1]);
					System.out.println("reg fee" + str2[2]);
					System.out.println("duty" + str2[3]);
					
					String SQL = RegCommonMkrSQL.PARAM_ID_GENERATION;
					dbUtility.createStatement();
					String number = dbUtility.executeQry(SQL);
					dbUtility.closeConnection();
					dbUtility = new DBUtility();
					sql = RegCommonMkrSQL.INSERT_LINKED_AMOUNTS;
					dbUtility.createPreparedStatement(sql);
					String temp[] = new String[14];
					temp[0] = number;
					temp[1] = key; // prop id
					temp[2] = regNum; // Registration initiation number
					/*
					 * this str2[1] contains flag which tells if the linked
					 * number is a regnum or estamp or physical stamp
					 */
					if (str2[1].equals("1")) {
						//temp[2] = str2[0];
						temp[3]=compRegNumber;
						temp[4] = "";
						temp[5] = "";
						temp[10] = "";
						temp[13] = "";
					} else if (str2[1].equals("2")) {
						temp[3] = "";
						temp[4] = str2[0];
						temp[5] = "";
						temp[10] = "";
						temp[13] = "";
					} else if (str2[1].equals("3")) {
						temp[3] = "";
						temp[4] = "";
						temp[5] = str2[0];
						temp[10] = "";
						temp[13] = "";
					}
					else if (str2[1].equals("4")) {
						temp[3] = "";
						temp[4] = "";
						temp[5] = "";
						temp[10]=str2[0];
						temp[13] = dto.getOldRegistrationDate();
					}

					temp[6] = str2[2];// reg fee
					temp[7] = str2[3];// duty
					temp[8] = userId;
					temp[9] = cdate1;
					temp[11]=dto.getDenotingCheck();
					temp[12]=dto.getLinkingCheck();	
					// Commented By simran
					//temp[7] = String.valueOf(balDuty);
					//temp[8] = String.valueOf(balfee);
					//temp[9] = number;
					dbUtility.executeUpdate(temp);

				}
				// for(int i =0;i<list.size();i++)
				// {
				// subList = new ArrayList();
				// subList= (ArrayList) list.get(i);
				//		    	
				// }

				/*
				 * String str= list.toString(); str = str.substring(2,
				 * (str.length()-2)); String str1[]= str.split(",");
				 * System.out.println(str1.length); for(int i
				 * =0;i<=str1.length;i++) { subList.add(o) } Iterator<ArrayList>
				 * itr = list.iterator(); while (itr.hasNext()) { ArrayList
				 * element1 = itr.next();
				 * 
				 * /* for(int i=0;i<list.size();i++) {
				 * System.out.println(list.get(i)); subList =(ArrayList)
				 * list.get(i);
				 * 
				 * }
				 */
				// }
				flag = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getStackTrace());
		}

		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in closing connection "
						+ "of saveLinkedAmt method of regcompmkrDAO class"
						+ e.getStackTrace());
			}
		}

		return flag;
	}

	// this method tell if the documenthas already been linked.

	public boolean getConsumedDoc(String num, String searchBy) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			String arr[] = {num};
			if (RegCompConstant.SEARCH_BY_REGNUM.equalsIgnoreCase(searchBy)) {
				/*sql = "SELECT count(1) FROM IGRS_REG_MKR_PROP_PYMNT_LNKNG "
					+ "WHERE DELETE_FLAG IS NULL AND REGISTRATION_NUMBER='" + num + "'";
				*/
				sql=RegCommonMkrSQL.SEARCH_BY_REGNUMBER;
				System.out.println(sql);
			} else if (RegCompConstant.SEARCH_BY_ESTAMP
					.equalsIgnoreCase(searchBy)) {
				
				/*sql = "SELECT DISTINCT LINKED_ESTAMP_CODE FROM IGRS_REG_INIT_PROP_PYMNT_LNKNG "
						+ "WHERE LINKED_ESTAMP_CODE='" + num + "'";*/
				sql=RegCommonMkrSQL.SEARCH_BY_ESTAMP;

			} else if (RegCompConstant.SEARCH_BY_PHYSTAMP
					.equalsIgnoreCase(searchBy)) {
				/*sql = "SELECT DISTINCT LINKED_ESTAMP_CODE FROM IGRS_REG_INIT_PROP_PYMNT_LNKNG "
						+ "WHERE LINKED_ESTAMP_CODE='" + num + "'";*/
				sql=RegCommonMkrSQL.SEARCH_BY_PHYSTAMP;
				
				//logger.debug(sql);
			}
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			

			// mainList=dbUtility.executeQuery(sql);

			String str = dbUtility.executeQry(arr);
			if (!str.equalsIgnoreCase("0")) {
				flag = true;
			}

		} catch (Exception e) {
			logger.error(e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("in RegCompMkrDAO:getConsumedDoc "
						+ e.getStackTrace());

			}
		}

		return flag;
	}

	public String saveHoldData(String regNum, String hldFlag, String fwdPage,
			String date, String createdBy, String holdId, String holdRemarks) throws Exception {
		// TODO Auto-generated method stub

		String str = "";
		boolean uniqueflg = true; 
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			//dbUtility.createStatement();
			String SQL=RegCommonMkrSQL.SAVE_HOLD_DATA;
			dbUtility.createPreparedStatement(SQL);
			/*check if unique constraint is being voilated
			 * unique cnstarint is that thecombination of registration number
			 * and forward page be unique
			 * this is to ensure that when hold resumes only one forward mapping is
			 * found from the hold table
			 * 
			 * */
			
			//Start:===check unique
			//COMMENTED BY SIMRAN
			/*String SQL ="SELECT COUNT(*) FROM  IGRS_REG_CMPLTN_MKR_HOLD " +
						"WHERE REG_TXN_ID='"+regNum+"' AND FORWARD_PAGE='"+fwdPage+"'";*/
			
			
			/*String SQL = "SELECT COUNT(*) FROM  IGRS_REG_TXN_HOLD WHERE REG_TXN_ID='"+regNum+"'" +
					" AND HOLD_ID='"+holdId+"'";*/
			String arr[]={regNum,holdId};
			String str1 =dbUtility.executeQry(arr);
			
			int i = Integer.parseInt(str1);//get count from hold table
			
			if(i!=0)
			{
				if(i>0)
				{
					uniqueflg = false;
				}
			}
			
			//End:===check unique
			
			//Start :=== if unique flag=true than insert data
			
			
			if(uniqueflg)
			{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = RegCommonMkrSQL.INSERT_HOLD_TABLE_DATA;
			dbUtility.createPreparedStatement(sql);
			String temp[] = new String[6];
			temp[0]= holdId;
			temp[1] = regNum;
			temp[2] = getDate(date);
			temp[3] = holdRemarks;
			
			temp[4] = getDate(date);
			temp[5] = createdBy;
		
			
			boolean tmpFlag = dbUtility.executeUpdate(temp);
			if (tmpFlag) {
				str = "1";
			}
		}
			//update data
			else
			{
				dbUtility = new DBUtility();
				//dbUtility.createStatement();
				String date1 = getDate(date);
				/*sql = "UPDATE IGRS_REG_TXN_HOLD SET HOLD_STATUS='" + hldFlag +"',UPDATE_BY ='" + createdBy + "'" + "," +
						"UPDATE_DATE='"+ date1 + "',HOLD_REMARKS='"+holdRemarks+ "' , HOLD_DATE = '"+date1+"'" +
								"WHERE  REG_TXN_ID='" + regNum + "' AND HOLD_ID='"+holdId+"'";*/
				sql = RegCommonMkrSQL.UPDATE_HOLD_STATUS;
				String arr1[]={hldFlag,createdBy,date1,holdRemarks,date1,regNum,holdId};
				dbUtility.createPreparedStatement(sql);
				boolean tmpFlag=dbUtility.executeUpdate(arr1);
				if (tmpFlag) {
					str = "1";
				}
				
			}
			
			if(str.equals("1"))
			{
				updateHoldStatus(regNum, date, createdBy);
			}

		} catch (Exception e) {
			System.out.println("error in regCompMkrDAO : saveHoldData"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out
						.println("error in closing connection RegCompMkrDAO : saveHoldData "
								+ e.getStackTrace());
			}
		}
		return str;
	}
	
	
	/////////////////////ADDED BY SIMRAN/////////////////////////////
	public boolean updateHoldStatus(String regNum, String date, String userId) throws Exception
	{
		boolean tmpFlag = false;
		DBUtility dbUtility = null;
		try
		{
			String date1 = getDate(date);
			dbUtility = new DBUtility();
			sql =RegCommonMkrSQL.UPDATE_HOLD_DATA; 
			dbUtility.createPreparedStatement(sql);
			String arr[]={userId,date1,regNum};
		//	sql = "UPDATE IGRS_REG_TXN_DETLS SET REGISTRATION_TXN_STATUS = '11', UPDATE_BY = '"+userId+"', UPDATE_DATE = '"+date1+"' WHERE  REG_TXN_ID='" + regNum + "'";
			tmpFlag=dbUtility.executeUpdate(arr);
			UpdateTImeEtoken(regNum, "11");
			EtokenChange eChange = new EtokenChange(regNum,"11",null);
			Thread t = new Thread(eChange);
			t.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch(Exception e){
				e.getStackTrace();
			}
		}
		
		return tmpFlag;
	}

	public String getMonthName(String _month) {
		HashMap hm = new HashMap();
		hm.put("01", "JAN");
		hm.put("02", "FEB");
		hm.put("03", "MAR");
		hm.put("04", "APR");
		hm.put("05", "MAY");
		hm.put("06", "JUN");
		hm.put("07", "JUL");
		hm.put("08", "AUG");
		hm.put("09", "SEP");
		hm.put("10", "OCT");
		hm.put("11", "NOV");
		hm.put("12", "DEC");
		return (String) hm.get(_month);
	}

	// This code for Date in required format
	public String getDate(String _fdate) {
		System.out.println("_fdate" + _fdate);
		StringTokenizer stoken = new StringTokenizer(_fdate, "/");
		String dd = stoken.nextToken();
		String mm = stoken.nextToken();
		String yy = stoken.nextToken();
		if (dd.length() == 2) {
			_fdate = dd + "-" + getMonthName(mm) + "-" + yy;
		}

		return _fdate;

	}

	/**
	 * this function return hold page in case application is on hold
	 * @param regNumber
	 * @return String
	 * @throws Exception
	 */
	public String checkHold(String regNumber) throws Exception {

		String fwdPage = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			//sql = "SELECT HLD_FLAG,FORWARD_PAGE FROM IGRS_REG_CMPLTN_MKR_HOLD "
				//	+ "WHERE HLD_FLAG='Y' AND REG_TXN_ID='" + regNumber + "'";
			
			// ADDED BY SIMRAN 22May
			String arr[] = {regNumber};
			
			sql = RegCommonMkrSQL.GET_REG_STATUS;
			dbUtility.createPreparedStatement(sql);
			String str1 = dbUtility.executeQry(arr);
			if(str1.equals("11"))   // hold at maker = 11
			{
				sql = RegCommonMkrSQL.GET_HOLD_PAGE_NAME;
				dbUtility.createPreparedStatement(sql);
				String str = dbUtility.executeQry(arr);
				fwdPage = str;
			}
			
			
			//mainList = dbUtility.executeQuery(sql);///COMMENTED BY SIMRAN 16 MAY
			//if (mainList.size() != 0) {
				/*
				 * ArrayList list = (ArrayList) mainList.get(0); String str=
				 * list.get(0).toString(); fwdPage=list.get(1).toString();
				 */
				// fwdPage=str.substring(2, (fwdPage.length()-2));
				//String str = mainList.toString();
				//str = str.substring(2, (str.length() - 2));
				//System.out.println(str);
				//String str1[] = str.split(",");
				//if (str1[0].equalsIgnoreCase("Y")) {   ///COMMENTED BY SIMRAN 16 MAY
					//fwdPage = str1[1].trim();
				//}
				
				//logger.debug("Hold Page------>"+fwdPage);
			//}
		} catch (Exception e) {
			logger.debug("Exception in CheckHold method : regCompMkrDAO"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug("Exception in dbUtility close : RegCompMkrDAO"
						+ e.getStackTrace());
			}

		}
		return fwdPage;
	}

	public boolean updtHoldTbl(String regNum, String userId, String updtDate,
			String hldFlg) throws Exception {
		boolean flg = false;
		DBUtility dbUtility = null;
		try {
			String updateDate = getDate(updtDate);
			dbUtility = new DBUtility();
			//dbUtility.createStatement();

			sql = RegCommonMkrSQL.UPDATE_HOLD_STATUS1;	
					/*sql = "UPDATE IGRS_REG_TXN_HOLD SET HOLD_STATUS='" + hldFlg
					+ "',UPDATE_BY ='" + userId + "'" + ",UPDATE_DATE='"
					+ updateDate + "'" + "WHERE REG_TXN_ID='" + regNum + "'";*/
			String arr[]={hldFlg,userId,regNum};
			dbUtility.createPreparedStatement(sql);
			flg = dbUtility.executeUpdate(arr);
			
			
			sql = RegCommonMkrSQL.UPDATE_REG_STATUS;
			String arr1[]={userId,regNum};
			dbUtility.createPreparedStatement(sql);

			/*sql = "UPDATE IGRS_REG_TXN_DETLS SET REGISTRATION_TXN_STATUS = 12, UPDATE_BY = '"+userId+"'," +
					"UPDATE_DATE = '"+updtDate+"' WHERE REG_TXN_ID = '"+regNum+"'";  ///12 --hold resume at makers part
		*/	
			//logger.debug(sql);

			flg = dbUtility.executeUpdate(arr1);

		} catch (Exception e) {
			logger.debug("Error in updtHoldtble method of RegCompMkrDAO : "
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.debug("error in closing connection of updtHoldTbl method RegCompMkrDAO :"
								+ e.getStackTrace());
			}
		}
		return flg;
	}

	public CommonMkrDTO getLnkdPmnt(String regNum, CommonMkrDTO dto) throws Exception {
		//CommonMkrDTO cdto = new CommonMkrDTO();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonMkrSQL.GET_REG_FEE;
			String arr[]={regNum};
			//dbUtility.createPreparedStatement(sql);
		//	sql = "SELECT REG_FEE,STAMP_DUTY FROM IGRS_REG_MKR_PROP_PYMNT_LNKNG WHERE REG_TXN_ID = '" + regNum + "' AND DELETE_FLAG = '0'";
			dbUtility.createPreparedStatement(sql);
			mainList = new ArrayList();
			mainList = dbUtility.executeQuery(arr);
			double regFee = 0.0;
			double stampDuty = 0.0;
			double linkdRegFee = 0.0;
			double linkdStampDuty = 0.0;
			
			double balRegFee = 0.0;
			double balStampDuty = 0.0;
			if(mainList.size() == 0)
			{
				regFee = 0.0;
				stampDuty = 0.0;
			}
			else
			{
				for(int i= 0;i<mainList.size();i++)
				{
					ArrayList list = (ArrayList)mainList.get(i);
					regFee = regFee+(Double.parseDouble((String)list.get(0)));
					stampDuty = stampDuty+(Double.parseDouble((String)list.get(1)));
					
				}
			}
			
			ArrayList alreadyPaid = getAlrdyPaidDuty(regNum);
			if(alreadyPaid.size() == 0)
			{
				linkdRegFee = 0.0;
				linkdStampDuty = 0.0;
				dto.setAlreadyPaid(false);
			}
			else
			{
				for(int i= 0;i<alreadyPaid.size();i++)
				{
					RegCompleteMakerDTO cdto1 = (RegCompleteMakerDTO)alreadyPaid.get(i);
					linkdRegFee = cdto1.getLinkedTotalRegFee();
					linkdStampDuty = cdto1.getLinkedTotalDuty();
					dto.setAlreadyPaid(true);
				}
			}
			
			balRegFee = linkdRegFee-regFee;
			balStampDuty = linkdStampDuty-stampDuty;
			//logger.debug("<-----balance"+balRegFee);
			//logger.debug("<----bal stamp Duty"+balStampDuty);
			dto.setTempFee(balRegFee);
			dto.setTempDuty(balStampDuty);
			
		} catch (Exception e) {
			logger.debug("");
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug("");
			}
		}
		return dto;
	}

	
	/**
	 * This method insert witness details 
	 * @param regNum
	 * @param dtlsMap
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertDtlsMap(String regNum, Map dtlsMap, String userId)
			throws Exception {
		boolean flg = false;
		DBUtility dbUtility = null;
		OthrDeedDtlsDTO othrDTO = new OthrDeedDtlsDTO();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			Iterator<Map.Entry<String, OthrDeedDtlsDTO>> entries = dtlsMap
					.entrySet().iterator();
			while (entries.hasNext()) {
				flg = false;
				Map.Entry<String, OthrDeedDtlsDTO> entry = entries.next();
				System.out.println("Key = " + entry.getKey() + ", Value = "
						+ entry.getValue());
				String key = entry.getKey();
				othrDTO = entry.getValue();
				String arr[] = new String[17];
				String SQL = RegCommonMkrSQL.PARAM_ID_WITNESS_DTLS; // FOR SNO
																	// GENERATION
																	// OF
																	// WITNESS
																	// DETAILS
				dbUtility.createStatement();
				String number = dbUtility.executeQry(SQL);
				//dbUtility = new DBUtility();
				sql = RegCommonMkrSQL.INSERT_OTHER_DEED_DTLS;
				dbUtility.createPreparedStatement(sql);
				System.out.println(sql);
				arr[0] = number;
				arr[1] = othrDTO.getFirstName();
				arr[2] = othrDTO.getMiddleName();
				arr[3] = othrDTO.getLastName();
				String gender = othrDTO.getGender();
				if(gender.equalsIgnoreCase("male"))
				{
					arr[4]="M";
				}
				else if(gender.equalsIgnoreCase("female")){
				arr[4] ="F";
				}
				else if(gender.equalsIgnoreCase("other")){
					arr[4] ="O";
				}
				arr[5] = othrDTO.getAge();
				arr[6] = othrDTO.getFatherName();
				arr[7] = othrDTO.getMotherName();
				arr[8] = othrDTO.getSpouseName();
				arr[9] = othrDTO.getNationality();
				arr[10] = othrDTO.getAddress();
				//arr[11] = othrDTO.getCountry();
				//arr[12] = othrDTO.getState();
				//arr[13] = othrDTO.getDistrict();
				arr[11] = othrDTO.getPostalCode();
				arr[12] = othrDTO.getPhoneNumber();
				//arr[16] = othrDTO.getLoanInfo();
				//arr[17] = othrDTO.getTaxDuties();
				
				arr[13] = userId;
				Date date = new Date();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String Date = sdf.format(date);
				arr[14] = getDate(Date);
				arr[15] = regNum;
				arr[16] = othrDTO.getRelationshipWit();
				flg = dbUtility.executeUpdate(arr);
				if(!flg)
				break;

			}

		} catch (Exception e) {
			logger.error("Error in insertDtlsMap of RegCompMkrDAO :"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("Error in closing connection insertDtlsMap Of RegCompMkrDAO"
								+ e.getStackTrace());
			}
		}

		return flg;
	}

	public boolean linkhistory(String propId, String regNum, String propIdInit,
			String oldregNum, String status, String userId, String cdate,String transactionType)
			throws Exception {
		boolean flg = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String SQL = RegCommonMkrSQL.PARAM_ID_PROP_LINKNG_HISTORY;
			dbUtility.createStatement();
			String number = dbUtility.executeQry(SQL);
			dbUtility.closeConnection();
			dbUtility = new DBUtility();
			sql = RegCommonMkrSQL.INSERT_PROP_LINKING_HISTORY;
			dbUtility.createPreparedStatement(sql);
			String[] arr = new String[10];
			arr[0] = number;
			arr[1] = regNum;// current reg number for which maker process is
							// going on
			arr[2] = propIdInit;// prop id of reg number for which completion is
								// being done.
			arr[3] = "";// registration number with which linking is
								// being done
			arr[4] = propId;// prop id of the old reg number
			arr[5] = transactionType;
			arr[6] = status;
			arr[7] = oldregNum;
			arr[8] = userId;
			arr[9] = getDate(cdate);
			
			flg = dbUtility.executeUpdate(arr);
			if(flg)
			{
				String query = RegCommonMkrSQL.UPDATE_PIN_STATUS;
				String propArr[] = {propId};
				dbUtility.createPreparedStatement(query);
				flg = dbUtility.executeUpdate(propArr);
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			logger.error("Error in insertDtlsMap of RegCompMkrDAO :"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in closing connection  Of RegCompMkrDAO"
						+ e.getStackTrace());
			}
		}

		return flg;
	}

	/**
	 * 
	 * @param propId
	 * @param regNum
	 * @param propIdInit
	 * @param oldregNum
	 * @param status
	 * @return boolean
	 * @throws Exception
	 */
	public boolean chkAlrdyLinked(String propId, String regNum,
			String propIdInit, String oldregNum, String status)
			throws Exception {
		boolean flg = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonMkrSQL.CHK_ALRDY_LINKED;
			dbUtility.createPreparedStatement(sql);
			String arr[]={regNum,propIdInit};
			/*sql = "SELECT  COUNT(*) FROM  IGRS_REG_LNKNG_PROP_HISTORY WHERE   REG_TXN_ID='"
					+ regNum
					+ "'AND INITIATED_PROP_ID ='"
					+ propIdInit
					+ "'AND LINKING_STATUS='I'";*/

			String str = dbUtility.executeQry(arr);
			if (!str.equalsIgnoreCase("0")) {
				flg = true;
			}

		} catch (Exception e) {
			logger.error("Error in  of RegCompMkrDAO :" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in closing connection  Of RegCompMkrDAO"
						+ e.getStackTrace());
			}

		}

		return flg;
	}

	public boolean updateLinkngTble(String propId, String regNum,
			String propIdInit, String oldregNum, String status, String userId,
			String cdate, String transactionType) throws Exception {
		boolean flg = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
		//	dbUtility.createStatement();
			String date = getDate(cdate);
			sql =RegCommonMkrSQL.UPDATE_LINKNG_HISTORY;
					
					/*sql = " UPDATE IGRS_REG_LNKNG_PROP_HISTORY   SET REG_TXN_ID='"
					+ regNum
					+ "',INITIATED_PROP_ID ='"
					+ propIdInit
					+ "', REGISTRATION_NUMBER='"
					+ oldregNum
					+ "',   OLD_PROP_ID='"
					+ propId
					+ "' , "
					+ "  LINKING_STATUS='I' ,   UPDATE_DATE='"
					+ date
					+ "',UPDATE_BY='"
					+ userId
					+ "' WHERE  REG_TXN_ID= '"
					+ regNum
					+ "'"
					+ " AND INITIATED_PROP_ID ='"
					+ propIdInit
					+ "' AND LINKING_STATUS='I'";*/
			String arr[]={regNum,propIdInit,oldregNum,propId,transactionType,date,userId,regNum,propIdInit};
			System.out.println(sql);
			dbUtility.createPreparedStatement(sql);
			flg = dbUtility.executeUpdate(arr);
			
			//TODO :- update pin status-
			if(flg)
			{
				String query = RegCommonMkrSQL.UPDATE_PIN_STATUS;
				String propArr[] = {propId};
				dbUtility.createPreparedStatement(query);
				flg = dbUtility.executeUpdate(propArr);
			}
						
			
		} catch (Exception e) {
			logger.debug("Error in updtHoldtble method of RegCompMkrDAO : "
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.debug("error in closing connection of updtHoldTbl method RegCompMkrDAO :"
								+ e.getStackTrace());
			}
		}
		return flg;
	}

	public ArrayList getTransactingParties(String regNum,String language) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList subList = new ArrayList();
		RegCommonBO commonBO = new RegCommonBO();
		RegCommonDTO mapDto =new RegCommonDTO();
		RegCommonBD commonBd = new RegCommonBD();
		RegCommonForm form = new RegCommonForm();
		//ArrayList poAOwnerList = commonBd.getOwnerPoaDescDisplay(language, regNum);
		// for organisation POA - Rahul
		ArrayList poAOwnerListForOrg = new ArrayList();
		String OwnerRolePoA="";
		int i1=0;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			//String arr[] = {regNum};
			String arr[] = new String[2];
			 arr[0]=regNum;
				 arr[1]= regNum;
			sql = RegCommonMkrSQL.GET_TRANSACTING_PARTY_DETAILS;
			System.out.println(sql);
			dbUtility.createPreparedStatement(sql);
			mainList = new ArrayList();
			mainList = dbUtility.executeQuery(arr);
			
			// to check common flow  for will Deed - Rahul
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			String arrCommon[] = {regNum};
			sqlOne = RegCommonMkrSQL.GET_COMMON_FLOW_FLAG;
			System.out.println(sqlOne);
			dbUtility.createPreparedStatement(sqlOne);
			String CommonDeedFlag = "";
			CommonDeedFlag =dbUtility.executeQry(arrCommon);
			System.out.println("CommonDeedFlag"+ CommonDeedFlag);
			
			
			
			
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
			}
			for (int i = 0; i < mainList.size(); i++) {
				subList = (ArrayList) mainList.get(i);
				RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
				rdto.setPartyTypeID((String) subList.get(0));
				int age=0;
				if(subList.get(10)!=null && !subList.get(10).toString().equalsIgnoreCase("")){
					age=Integer.parseInt(subList.get(10).toString());
				}
				
				
				// for POA , Authenticated - Rahul
				int roleId=Integer.parseInt((String)subList.get(3));
			   	  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
			   	int ExecutantClaimantfromDB=0;
			   	  if (subList.get(15)!=null && !subList.get(15).toString().equalsIgnoreCase(""))
			   	  {
			        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
			   	  }	
			     mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
			     // For Will Deed - Authenticated individual - Rahul
			     if(subList.get(8)!=null && subList.get(8).toString().equalsIgnoreCase("2"))
			     {
			     if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)&& ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
					    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6 
					    	){
			    	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					// int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
					 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
					form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
            		 String RoleName = form.getExecutantClaimantName();
                	 System.out.println("Role Name " +RoleName); 
                	 String PoaName = null;
                	
                	// to check hindi and english role 
                	 String HindiEnglishRole[] = RoleName.split(",");
                	 String hindesc = HindiEnglishRole[0];
                	 String EngDesc =HindiEnglishRole[1];
                	 
                	 if (EngDesc.contains("Authenticated")){ 
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    		   PoaName =" By(Authenticated PoA Holder) ";
                 		  }
                 		  else {
                 			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
                 		  }}    
                	 else {
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                			  PoaName =" By(PoA Holder) ";
               		  }
               		  else {
               			  PoaName =" द्वारा (मुख्‍त्‍यार आम)";
               		  }
                	 }
                		 System.out.println("POA NAMe is "+PoaName);
                    	 System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
                    	 String OwnerDetail =null;
                    	/* if(i1<poAOwnerList.size())
                    	 {
                    	 ArrayList m = (ArrayList) poAOwnerList.get(i1);
                	        mapDto.setOwnerPartyDesc(m.get(0).toString());
                	     
                	     OwnerDetail =mapDto.getOwnerPartyDesc();
                	     System.out.println("Owner result from DB is "+OwnerDetail);
                	       
                	       String parts[] = OwnerDetail.split(",");
                	       String Owner = parts[0];
                	       String PoADesc =parts[1];
                	     
                	      OwnerRolePoA=Owner+PoaName+PoADesc;
                	        System.out.println("owner Role to display "+ OwnerRolePoA);
                    	 }*/
                	         /*rdto.setPartyFirstName(OwnerRolePoA);
                    	      rdto.setPartyLastName("");
                    	      i1++;*/
                    	 
                    	 String   poAOwnerListNew = commonBd.getOwnerDetails(regNum,rdto.getPartyTypeID(),language);
                    	 String poaNameDetails = commonBd.getOwnerPOAnameDetails(rdto.getPartyTypeID());
                    	 OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;

                    	 rdto.setPartyFirstName(OwnerRolePoA);
                 	      rdto.setPartyLastName("");
					
			     }
			     
			       // FOr POA - Covenynce ,Mortagage 
			     else if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ){
			    	 
			    	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
			    	 String  RoleName= null;
			    	// rdto.setPartyRole(getPartyRole((String) subList.get(3),language));
			    	 // for both hindi english role POA - RAhul
			    	 rdto.setPartyRoleFullNamePOA(commonBO.getRoleNameForPOA((String) subList.get(3)));
			    	 RoleName = rdto.getPartyRoleFullNamePOA();
			    	 
           	         System.out.println("Role Name " +RoleName); 
           	      // to check hindi and english role 
                	 String HindiEnglishRole[] = RoleName.split(",");
                	 String hindesc = HindiEnglishRole[0];
                	 String EngDesc =HindiEnglishRole[1];
                	 
                	 
                	 String PoaName = null;
                	 if (EngDesc.contains("Authenticated")){ 
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  		   PoaName =" By(Authenticated PoA Holder) ";
               		  }
               		  else {
               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
               		  }}    
                	 else {
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                			  PoaName =" By(PoA Holder) ";
               		  }
               		  else {
               			  PoaName =" द्वारा (मुख्‍त्‍यार आम)";
               		  }
			}

                	 String   poAOwnerListNew = commonBd.getOwnerDetails(regNum,rdto.getPartyTypeID(),language);
                	 String poaNameDetails = commonBd.getOwnerPOAnameDetails(rdto.getPartyTypeID());
                	 OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;

                	 rdto.setPartyFirstName(OwnerRolePoA);
             	      rdto.setPartyLastName("");
                //	 dto.setName(OwnerRolePoA);
                 	
                 	// System.out.println("POA NAMe is "+PoaName);
           	        // System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
           	       // System.out.println("PoAOwnerlistdisplay"+poAOwnerList.get(i));
        	         
         	      //  String OwnerDetail =null;
         	        
         	       /*if(i1<poAOwnerList.size())
         	       {
         	       ArrayList m = (ArrayList) poAOwnerList.get(i1);
          	        mapDto.setOwnerPartyDesc(m.get(0).toString());
          	     
          	     OwnerDetail =mapDto.getOwnerPartyDesc();
          	     System.out.println("Owner result from DB is "+OwnerDetail);
          	       
          	       String parts[] = OwnerDetail.split(",");
          	       String Owner = parts[0];
          	       String PoADesc =parts[1];
          	     
          	    OwnerRolePoA=Owner+PoaName+PoADesc;
          	        System.out.println("owner Role to display "+ OwnerRolePoA);
         	       }*/
          	        
          	     
          	   
			    	 
			     }// end of if POA cond 
			     
			     
// code end for POA  - Rahul 
			     else {
			    	 if ((String) subList.get(1) != null
								&& !((String) subList.get(1)).equalsIgnoreCase("")) {
							// changed for middle name -
							if ((String)subList.get(12)!=null && !((String) subList.get(12)).equalsIgnoreCase("")){
							rdto.setPartyFirstName((String) subList.get(1)+ " "+(String)subList.get(12));
							}
							else {
								// previous code 
								rdto.setPartyFirstName((String) subList.get(1));
							}
						} else {
							rdto.setPartyFirstName((String) subList.get(5));
						}

						if ((String) subList.get(2) != null
								&& !((String) subList.get(2)).equalsIgnoreCase("")) {
							rdto.setPartyLastName((String) subList.get(2));
						} else {
							rdto.setPartyLastName("");
						}
			    	 	
						// Changed logic to show middle and Guaridan Name Rahul
						if(age>0 && age<RegCompCheckerConstant.MINOR_AGE_LIMIT){
					String name;
					 if((String)subList.get(12)!=null && !((String) subList.get(12)).equalsIgnoreCase(""))
					 {
						 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
						 {
						  rdto.setPartyFirstName(" (Minor) "+(String) subList.get(1)+" " +(String)subList.get(12)+" "+ (String) subList.get(2)+" "+ (String) subList.get(14)+" "+ (String) subList.get(11)+" By "+(String) subList.get(13));
						 }
						 else
						 {
							 rdto.setPartyFirstName(" (अवयस्‍क) "+(String) subList.get(1)+" " +(String)subList.get(12)+" "+ (String) subList.get(2)+" "+ (String) subList.get(16)+" "+ (String) subList.get(11)+" द्वारा "+(String) subList.get(13));
						 }
						  /*name=rdto.getPartyFirstName();
						  System.out.println(".....with Middle.Name "+ name);*/
							rdto.setPartyLastName("");
							rdto.setGovOfcCheck("N");
					 }
					
					 else{
						 //old code
					//rdto.setPartyFirstName((String) subList.get(1) +" C/O "+ (String) subList.get(11));
						 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
						 {	 
					      rdto.setPartyFirstName(" (Minor) "+(String) subList.get(1)+" "+ (String) subList.get(2)+" "+ (String) subList.get(14)+" "+ (String) subList.get(11)+" By "+(String) subList.get(13));
						 }
						 else
						 {
							 rdto.setPartyFirstName(" (अवयस्‍क) "+(String) subList.get(1)+" "+ (String) subList.get(2)+" "+ (String) subList.get(16)+" "+ (String) subList.get(11)+" द्वारा "+(String) subList.get(13));
						 }
					rdto.setPartyLastName("");
					rdto.setGovOfcCheck("N");
					/*name=rdto.getPartyFirstName();
					System.out.println("...Without Middle ..."+ name);*/
					 }
				}	
			     }
			     } // individual check end
			   //
				
				{
					
				/*if ((String) subList.get(1) != null
						&& !((String) subList.get(1)).equalsIgnoreCase("")) {
					// changed for middle name -
					if ((String)subList.get(12)!=null && !((String) subList.get(12)).equalsIgnoreCase("")){
					rdto.setPartyFirstName((String) subList.get(1)+ " "+(String)subList.get(12));
					}
					else {
						// previous code 
						rdto.setPartyFirstName((String) subList.get(1));
					}
				} else {
					rdto.setPartyFirstName((String) subList.get(5));
				}

				if ((String) subList.get(2) != null
						&& !((String) subList.get(2)).equalsIgnoreCase("")) {
					rdto.setPartyLastName((String) subList.get(2));
				} else {
					rdto.setPartyLastName("");
				}
				*/
				if(subList.get(8)!=null && subList.get(8).toString().equalsIgnoreCase("3"))
				{
					rdto.setGovOfcCheck("Y");
					if(subList.get(9)!=null&&!subList.get(9).toString().equalsIgnoreCase(""))
					{
						rdto.setPartyFirstName((String) subList.get(9));
					}
					else
					{
						rdto.setPartyFirstName("--");
					}
				}
				else
				{
					rdto.setGovOfcCheck("N");
				}
				// for organisaation POA holder start -- Rahul
				if(subList.get(8)!=null && subList.get(8).toString().equalsIgnoreCase("1"))
				{
					// will deed 
					 if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)&& ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
						    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
						    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
						    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
						    	)
					 {
						// poAOwnerListForOrg = commonBd.getOwnerPoaDescDisplayForOrg(language, regNum);
				    	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						// int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
						 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
						form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
	            		 String RoleName = form.getExecutantClaimantName();
	                	 System.out.println("Role Name " +RoleName); 
	                	 String PoaName = null;
	                	
	                	// to check hindi and english role 
	                	 String HindiEnglishRole[] = RoleName.split(",");
	                	 String hindesc = HindiEnglishRole[0];
	                	 String EngDesc =HindiEnglishRole[1];
	                	 
	                	 if (EngDesc.contains("Authenticated")){ 
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                    		   PoaName =" By(Authenticated PoA Holder) ";
	                 		  }
	                 		  else {
	                 			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
	                 		  }}    
	                	 else {
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                			  PoaName =" By(PoA Holder) ";
                   		  }
                   		  else {
                   			  PoaName =" द्वारा (मुख्‍त्‍यार आम)";
                   		  }
	                	 }
	                		 System.out.println("POA NAMe is "+PoaName);
	                    	 System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
	                    	 String OwnerDetail =null;
	                    /*	 if(i1<poAOwnerListForOrg.size())
	                    	 {
	                    	 ArrayList m = (ArrayList) poAOwnerListForOrg.get(i1);
	                	        mapDto.setOwnerPartyDescForOrg(m.get(0).toString());
	                	     
	                	     OwnerDetail =mapDto.getOwnerPartyDescForOrg();
	                	     System.out.println("Owner result from DB is "+OwnerDetail);
	                	       
	                	       String parts[] = OwnerDetail.split(",");
	                	       String Owner = parts[0];
	                	       String PoADesc =parts[1];
	                	     
	                	       OwnerRolePoA=Owner+PoaName+PoADesc;
	                	        System.out.println("owner Role to display "+ OwnerRolePoA);
	                    	 }
	                	         rdto.setPartyFirstName(OwnerRolePoA);
	                    	      rdto.setPartyLastName("");
	                    	      i1++;*/
	                    	 
	                    	 String   poAOwnerListNew = commonBd.getOwnerDetails(regNum,rdto.getPartyTypeID(),language);
	                    	 String poaNameDetails = commonBd.getOwnerPOAnameDetails(rdto.getPartyTypeID());
	                    	 OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;

	                    	 rdto.setPartyFirstName(OwnerRolePoA);
	                 	      rdto.setPartyLastName("");
						
				     }
					 
					 else if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
					 {
						 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
				    	 String  RoleName= null;
						// poAOwnerListForOrg = commonBd.getOwnerPoaDescDisplayForOrg(language, regNum);
						 rdto.setPartyRoleFullNamePOA(commonBO.getRoleNameForPOA((String) subList.get(3)));
				    	 RoleName = rdto.getPartyRoleFullNamePOA();
				    	 
	           	         System.out.println("Role Name " +RoleName); 
	           	      // to check hindi and english role 
	                	 String HindiEnglishRole[] = RoleName.split(",");
	                	 String hindesc = HindiEnglishRole[0];
	                	 String EngDesc =HindiEnglishRole[1];
	                	 
	                	 
	                	 String PoaName = null;
	                	 if (EngDesc.contains("Authenticated")){ 
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                    		   PoaName =" By(Authenticated PoA Holder) ";
	                 		  }
	                 		  else {
	                 			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
	                 		  }}    
	                	 else {
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                			  PoaName =" By(PoA Holder) ";
                   		  }
                   		  else {
                   			  PoaName =" द्वारा (मुख्‍त्‍यार आम)";
                   		  }
				}


	                 	
	                 	 System.out.println("POA NAMe is "+PoaName);
	           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
	           	       // System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
	        	         
	         	        String OwnerDetail =null;
	         	   /*    if(i1<poAOwnerListForOrg.size())
                  	 {
	         	       ArrayList m = (ArrayList) poAOwnerListForOrg.get(i1);
	          	        mapDto.setOwnerPartyDescForOrg(m.get(0).toString());
	          	     
	          	     OwnerDetail =mapDto.getOwnerPartyDescForOrg();
	          	     System.out.println("Owner result from DB is "+OwnerDetail);
	          	       
	          	       String parts[] = OwnerDetail.split(",");
	          	       String Owner = parts[0];
	          	       String PoADesc =parts[1];
	          	     
	          	        OwnerRolePoA=Owner+PoaName+PoADesc;
	          	        System.out.println("owner Role to display "+ OwnerRolePoA);
                  	 }
	          	        
	          	      rdto.setPartyFirstName(OwnerRolePoA);
	          	      rdto.setPartyLastName("");
	          	      i1++;*/
	         	       String   poAOwnerListNew = commonBd.getOwnerDetails(regNum,rdto.getPartyTypeID(),language);
	                	 String poaNameDetails = commonBd.getOwnerPOAnameDetails(rdto.getPartyTypeID());
	                	 OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;

	                	 rdto.setPartyFirstName(OwnerRolePoA);
	             	      rdto.setPartyLastName("");
				    	 
					 }
					 // if not POA - Authroised name - Rahul
					 else {
							rdto.setPartyFirstName((String) subList.get(5));
						}
				}
			}
				// for organistion POA end  - Rahul
				rdto.setPartyRoleTypeId((String) subList.get(3));
				String partyId = (String) subList.get(3);
				if(partyId.equals("0"))
					rdto.setPartyRole((String) subList.get(6));
				else
					rdto.setPartyRole(getPartyRole((String) subList.get(3),language));
				rdto.setPhotoChkParty("N");
				//rdto.setGovOfcCheck((String) subList.get(7));
				
				
				
				
				
				
				
				
				
				
				
				dbUtility.closeConnection();
				dbUtility = new DBUtility();
				sql = RegCompCheckerSQL.GET_PHOTO_PATH_PARTY;
				System.out.println(sql);
				dbUtility.createPreparedStatement(sql);
				ArrayList mainPhotoList = new ArrayList();
				String aar1[]=new String[1];
				aar1[0]= rdto.getPartyTypeID();
				mainPhotoList = dbUtility.executeQuery(aar1);
				if(mainPhotoList!=null&&mainPhotoList.size()>0)
				{
					ArrayList subList1=(ArrayList) mainPhotoList.get(0);
					if(subList1.get(0)!=null && !subList1.get(0).toString().equalsIgnoreCase(""))
					{
					rdto.setPartyPhotoName(subList1.get(0).toString().trim());
					rdto.setPartyPhotoPath(subList1.get(1).toString().trim());
					rdto.setPhotoChkParty("Y");
					}
				}
				/*if(subList.get(10)!=null && !subList.get(10).toString().equalsIgnoreCase(""))
				{
					rdto.setPartyPhotoName(subList.get(10).toString().trim());
					rdto.setPartyPhotoPath(subList.get(11).toString().trim());
					rdto.setPhotoChkParty("Y");
				}*/
				
				list.add(rdto);
			}
	
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}

		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
			}

		}

		return list;
	}

	public String getPartyRole(String roleId,String language) throws Exception {

		String partyrole = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			
			// String param[]={appId,appId};
			String roleArr[] = {roleId};
			if("en".equalsIgnoreCase(language))
			{	
			sql = RegCommonMkrSQL.GET_PARTY_ROLE_NAME;
			}
			else
			{
				sql = RegCommonMkrSQL.GET_PARTY_ROLE_NAME_HI;
			}
			// dbUtility.createStatement(sql);
			dbUtility.createPreparedStatement(sql);
			partyrole = dbUtility.executeQry(roleArr);

			//logger.debug("payment flag from database----->" + partyrole);
		} catch (Exception exception) {

			System.out.println("Exception in getPartyRole-RegCommonMkrDAO"
					+ exception);

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}

		return partyrole;

	}

	public ArrayList getWitnessList(String regNum, String cdate,String language)
			throws Exception {
		ArrayList list = new ArrayList();
		ArrayList subList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			String dt = getDate(cdate);
			dbUtility = new DBUtility();
		
			mainList = new ArrayList();
			String arr[] = {regNum}; 
			sql = RegCommonMkrSQL.GET_WITNESS_DETAILS;
					//+ "' AND CREATED_DATE='" + dt + "'";
			System.out.println(sql);
			dbUtility.createPreparedStatement(sql);
			mainList = new ArrayList();
			mainList = dbUtility.executeQuery(arr);
			for (int i = 0; i < mainList.size(); i++) {
				subList = (ArrayList) mainList.get(i);
				RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
				rdto.setWitnessFirstName((String) subList.get(0));
				rdto.setWitnessLastName((String) subList.get(1));
				rdto.setWitnessSno((String) subList.get(2));
				if("en".equalsIgnoreCase(language))
				{	
				rdto.setPartyRole("Witness");
				}
				else
				{
					rdto.setPartyRole("गवाह");
				}
				rdto.setWitnessPhotoName("");
				rdto.setWitnessPhotoPath("");
				rdto.setPhotoChkWitness("N");
				sql = RegCompCheckerSQL.GET_PHOTO_PATH_WITNESS;
				System.out.println(sql);
				dbUtility.createPreparedStatement(sql);
				ArrayList mainPhotoList = new ArrayList();
				String aar1[]=new String[1];
				aar1[0]= rdto.getWitnessSno();
				mainPhotoList = dbUtility.executeQuery(aar1);
				if(mainPhotoList!=null&&mainPhotoList.size()>0)
				{
					ArrayList subList1=(ArrayList) mainPhotoList.get(0);
					if(subList1.get(0)!=null && !subList1.get(0).toString().equalsIgnoreCase(""))
					{
					rdto.setWitnessPhotoName(subList1.get(0).toString().trim());
					rdto.setWitnessPhotoPath(subList1.get(1).toString().trim());
					rdto.setPhotoChkWitness("Y");
					}
				}
				list.add(rdto);
			}

		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}

		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
			}

		}

		return list;

	}

	public ArrayList getofficialList() throws Exception {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		mainList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			list = dbUtility
					.executeQuery(RegCommonMkrSQL.GET_GOVT_OFFICIAL_lIST);
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
				rdto.setGovtOffId((String) subList.get(0));
				rdto.setGovtOffName((String) subList.get(1));
				mainList.add(rdto);
			}
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return mainList;

	}

	public boolean insrtPrtyPrsntDet(String hdnPresentParty, String cdate,
			String userId, String regNum) throws Exception {
		boolean flg = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String[] temp = new String[4];
			//temp[1] = regNum;// registration number
			
			temp[0] = userId;// created BY
			temp[1] = getDate(cdate);// presentation date
			temp[2] = regNum;
			//temp[6] = getDate(cdate);// created date
			String arr[] = hdnPresentParty.split(",");
			for (int i = 0; i < arr.length; i++) {

				/*if (arr[i].contains("~")) {
					String arr2[] = arr[i].split("~");
					temp[0] = arr2[0];// party txn Id
					temp[3] = arr2[1];// type of government official
					temp[2] = "Y"; // value to tell if the person is a
									// government official

				} else {*/
					temp[3] = arr[i];
					//temp[3] = "";
					//temp[2] = "";
				//}
				
				sql = RegCommonMkrSQL.UPDATE_PARTY_AT_MAKER;
				System.out.println(sql);
				dbUtility.createPreparedStatement(sql);
				flg = dbUtility.executeUpdate(temp);
			}

		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}

		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection insrtPartyPrsntDet:"
						+ e.getStackTrace());
			}
		}
		return flg;
	}

	public boolean updtOthrDeedDtlTbl(String hdnPresentWitness, String cdate,
			String userId, String regNum) throws Exception {
		boolean tempFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String[] temp = new String[6];

			temp[1] = getDate(cdate);// presentation date
			temp[2] = userId;// updated BY
			temp[3] = getDate(cdate);// update date
			temp[4] = regNum;
			String arr[] = hdnPresentWitness.split(",");
			for (int i = 0; i < arr.length; i++) {

				{
					temp[0] = "Y";
					temp[5] = arr[i];

				}
				sql = RegCommonMkrSQL.UPDT_OTHR_DEED_TBL_WITNESS_PRESENT;
				System.out.println(sql);
				dbUtility.createPreparedStatement(sql);
				tempFlag = dbUtility.executeUpdate(temp);
			}

		} catch (Exception e) {
			logger.error("error in updtOthrDeedDtlTbl " + e.getStackTrace());
		} finally {

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection in updtOthrDeedDtlTbl"
						+ e.getStackTrace());
			}
		}

		return tempFlag;
	}

	public String getTypeOfArea(String regNumber) throws Exception {

		String typeofarea = "";
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.GET_TYPE_OF_AREA;
			dbUtility.createPreparedStatement(sql);
			System.out.println(sql);
			//mainList = new ArrayList();
			typeofarea = dbUtility.executeQry(regArr);
			//mainList = dbUtility.executeQuery(sql);
			//if (mainList.size() != 0) {
				//String str = mainList.toString();
				//str = str.substring(2, (str.length() - 2));   // Commented by Simran
				//System.out.println(str);
				//String[] temp = str.split(",");
				//typeofarea = temp[2].trim();
			//}
			//logger.debug("Type of area--->"+typeofarea);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}

		return typeofarea;
	}

	public boolean getIsPoa(String regNumber) throws Exception {
		// TODO Auto-generated method stub
		boolean flg = false;
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.CHECK_IS_POA;
			dbUtility.createPreparedStatement(sql);
			System.out.println(sql);
			mainList = new ArrayList();
			mainList = dbUtility.executeQuery(regArr);
			if (mainList.size() != 0) {
				String str = mainList.toString();
				str = str.substring(1, (str.length() - 1));
				String[] str1 = str.split(",");
				System.out.println(str);
				for (int j = 0; j < str1.length; j++) {
					String temp = str1[j].trim();
					if (temp.equalsIgnoreCase("[50003]")
							|| temp.equalsIgnoreCase("[50007]")
							|| temp.equalsIgnoreCase("[50008]")
							|| temp.equalsIgnoreCase("[50011]")
							|| temp.equalsIgnoreCase("[50012]")) {
						flg = true;
					}
				}

			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}

		return flg;
	}

	String getPhotoIdProofName(String proofId) throws Exception {
		String proofname = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String photoArr[] = {proofId};
			sql = RegCommonMkrSQL.GET_PHOTO_PROOF_NAME;
			dbUtility.createPreparedStatement(sql);
			proofname = dbUtility.executeQry(photoArr);
		} catch (Exception exception) {
			System.out
					.println("Exception in getPhotoIdProofName-RegCommonMkrDAO"
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

	public ArrayList getPartyDet(String regNumber) throws Exception {

		mainList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList sublist = new ArrayList();
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.GET_PARTY_DETAILS;
			dbUtility.createPreparedStatement(sql);
			System.out.println(sql);
			list = dbUtility.executeQuery(regArr);
			System.out.println(list.size());
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					sublist = (ArrayList) list.get(i);
					RegCompleteMakerDTO dto = new RegCompleteMakerDTO();
					dto.setPartyTxnId(sublist.get(2).toString().trim());
					/*if (sublist.get(4).toString().trim().equalsIgnoreCase("")) {
						dto.setPhotoTypeId("not available");
					} else {
						dto.setPhotoTypeId(sublist.get(4).toString().trim());
					}
					if (sublist.get(3).toString().trim().equalsIgnoreCase("")) {
						dto.setPhotoProofTypeName("not available");
					} else {
						String proofname = getPhotoIdProofName(sublist.get(3)
								.toString().trim());
						dto.setPhotoProofTypeName(proofname);
					}*/
					if(sublist.get(4)!=null&&!sublist.get(4).toString().equalsIgnoreCase(""))
					{
						dto.setPhotoTypeId(sublist.get(4).toString().trim());
					}
					else
					{
						dto.setPhotoTypeId("not available");
					}
					if(sublist.get(3)!=null&&!sublist.get(3).toString().equalsIgnoreCase(""))
					{
						String proofname = getPhotoIdProofName(sublist.get(3)
								.toString().trim());
						dto.setPhotoProofTypeName(proofname);
					}
					else 
					{
						dto.setPhotoProofTypeName("not available");
					}
					
					// modified for govt offcial power of attorney - akansha
					if(sublist.get(7)!=null&&sublist.get(7).toString().equalsIgnoreCase("3")){
						dto.setPartyName(sublist.get(8).toString().trim());
					}
					else{	
					if (sublist.get(1).toString().trim().equalsIgnoreCase("")) {
						dto.setPartyName(sublist.get(6).toString().trim());
					} else {
						dto.setPartyName(sublist.get(1).toString().trim());
					  }
				   }
					mainList.add(dto);

				}
			}

		} catch (Exception e)

		{
			logger.error("" + e.getStackTrace());

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection" + e.getStackTrace());
			}
		}

		// TODO Auto-generated method stub
		return mainList;
	}

	public boolean insertComplianceID(String complId, String regNum,
			String cdate, String userId) throws Exception {
		boolean tempFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			
			String[] temp = new String[5];

			temp[0] = regNum;
			temp[2] = "A";
			temp[3] = userId;// updated BY
			temp[4] = getDate(cdate);// update date
			String arr[] = complId.split(",");
			System.out.println(arr.length);
			dbUtility.closeConnection();
			for (int i = 0; i < arr.length; i++) {
				{
					
					dbUtility = new DBUtility();
					temp[1] = arr[i];
					sql = RegCommonMkrSQL.INSERT_COMPLIANCE_ID;
					System.out.println(sql);
					dbUtility.createPreparedStatement(sql);
					tempFlag = dbUtility.executeUpdate(temp);
					dbUtility.closeConnection();
				}

			}

		} catch (Exception e) {
			logger.error("error in updtOthrDeedDtlTbl " + e.getStackTrace());
		} finally {

			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection in updtOthrDeedDtlTbl"
						+ e.getStackTrace());
			}
		}

		return tempFlag;

	}

	public ArrayList getcheckedCompliance(String regNumber) throws Exception {
		mainList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] ={regNumber};
			sql = RegCommonMkrSQL.GET_CHECKED_COMPLIANCE_ID;
			dbUtility.createPreparedStatement(sql);
			mainList = dbUtility.executeQuery(regArr);
		}

		catch (Exception e) {
			logger.error("in getcheckedCompliance" + e.getStackTrace());
		}
		finally{
			dbUtility.closeConnection();
		}

		return mainList;
	}

	public ArrayList getDeedInstId(String regNumber) {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			String query = RegCompCheckerSQL.GET_REG_INIT_DEED_INST_ID;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(regArr);

		} catch (Exception exception) {
			logger.debug("Exception in getDeedInstId" + exception);
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
		return list;
	}

	public ArrayList getPropertyIdMarketVal(String regNumber) {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			String query = RegCommonMkrSQL.GET_PROPERTY_ID_MARKET_VALUE;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(regArr);

		} catch (Exception exception) {
			logger.debug("Exception in getPropertyIdMarketVal" + exception);
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
		return list;
	}

	
	/**
	 * 
	 * @param regNumber
	 * @return ArrayList that holds duty already paid and reg fee already paid
	 * @throws Exception
	 */
	public ArrayList getAlrdyPaidDuty(String regNumber) throws Exception {
		ArrayList mainList = new ArrayList();
		ArrayList sublist = new ArrayList();
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.GET_LINKED_DUTIES;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(regArr);
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					sublist = (ArrayList) list.get(i);
					/*
					 * CommonMkrDTO cdto1=null; cdto1 = new CommonMkrDTO();
					 */

					RegCompleteMakerDTO cdto1 = new RegCompleteMakerDTO();
					String duty = (String) sublist.get(0);
					String fee = (String) sublist.get(1);
					if (duty != null && !duty.equalsIgnoreCase("")) {
						cdto1.setLinkedTotalDuty(Double.parseDouble(duty));
					} else if (duty.equalsIgnoreCase("")
							|| duty.equalsIgnoreCase(null)) {
						cdto1.setLinkedTotalDuty(0.0);
					} else {
						cdto1.setLinkedTotalDuty(0.0);

					}
					if (fee != null && !fee.equalsIgnoreCase("")) {
						cdto1.setLinkedTotalRegFee(Double.parseDouble(fee));
					} else if (fee.equalsIgnoreCase("")
							|| fee.equalsIgnoreCase(null)) {
						cdto1.setLinkedTotalRegFee(0.0);
					} else {
						cdto1.setLinkedTotalRegFee(0.0);
					}

					mainList.add(cdto1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getAlrdyPaidDuty"
						+ e.getStackTrace());
			}
		}

		return mainList;
	}

	public String saveChecklist(RegCompleteMakerDTO dto2, String cdate,
			String userId) throws Exception {
		boolean flg = false;
		String number = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String date1 = dto2.getExeDate();
			String date2 = dto2.getExeOutIndDate();
			String date3 = dto2.getOrdrDate();
			String date4 = dto2.getLstAppealDate();

			String[] temp = new String[13];
			String SQL = RegCommonMkrSQL.GET_CHECKLIST_TXN_NUM;
			dbUtility.createStatement();
			 number = dbUtility.executeQry(SQL);
			temp[0] = number;
			if (date1 != null && !date1.equalsIgnoreCase("")) {
				temp[2] = getDate(dto2.getExeDate());
			} else {
				temp[2] = "";
			}
			temp[1] = dto2.getRegNumber();
			// temp[2]=getDate(dto2.getExeDate());//execution date
			temp[3] = dto2.getExeOutIndFlg(); // executed outside india flag
			temp[4] = dto2.getCourtDecreeFlg();//court decree Flag
			if (date3 != null && !date3.equalsIgnoreCase("")) {
				temp[5] = getDate(dto2.getOrdrDate());// court order date
			} else {
				temp[5] = "";
			}
			temp[6] = dto2.getCrtDcrWithAplFlg();// court decree with appeal
													// flag
			temp[7] = dto2.getDocAfterDeathFlg();// doc after death flag
			//temp[8] = "";// dto2.getUpldDeathCert();//name of death certificate
							// file
			//temp[9] = "";// dto2.getUplaReltnProof(); // name of relationship
							// proof file
			//temp[10] = dto2.getComments(); // comments
			temp[8] = dto2.getPoaFlg();// poa flag
			//temp[12] = "";// poa from mp flag
			//temp[13] = "";// dto2.getPoaAuthNum();//poa authentication number
			if (date4 != null && !date4.equalsIgnoreCase("")) {
				temp[9] = getDate(dto2.getLstAppealDate());// last appeal date
			} else {
				temp[9] = "";
			}
			temp[10] = userId;
			temp[11] = getDate(cdate);
			if (date2 != null && !date2.equalsIgnoreCase("")) {
				temp[12] = getDate(date2);// court order date
			} else {
				temp[12] = "";
			}
			
			dbUtility.closeConnection();
			dbUtility = new DBUtility();
			sql = RegCommonMkrSQL.INSERT_INTO_CHECKLIST;
			dbUtility.createPreparedStatement(sql);
			flg = dbUtility.executeUpdate(temp);
			if(!flg)
			{
				number = "";
			}

		} catch (Exception e) {
			logger.error("error in saveCheckList" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection saveChecklist"
						+ e.getStackTrace());
			}
		}
		logger.debug("checklist number in DAO"+number);
		return number;
	}

	public Date getsysDate() {
		// TODO Auto-generated method stub
		Date date = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql = "SELECT SYSDATE FROM DUAL";
			SimpleDateFormat sdfrmt = new SimpleDateFormat(
					"dd/MM/yyyy hh:mm:ss a");
			date = sdfrmt.parse(dbUtility.executeQry(sql));
		} catch (Exception e) {
			logger.error("error in getting sysdate" + e.getStackTrace());
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
		return date;
	}

	public ArrayList getlnkdamt(String regNumber) {
		// TODO Auto-generated method stub
		mainList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.GET_FINAL_PAYMENT_AMOUNT_MAKER;
			dbUtility.createPreparedStatement(sql);
			mainList = dbUtility.executeQuery(regArr);
		} catch (Exception e) {
			logger.error("" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close Connection getlnkdamt"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}

	public boolean checkAlrdyInsertdRegID(String regNum, String userId, String cDate) throws Exception {
		boolean flg = false;
		boolean flg1 = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = {regNum};
			sql = RegCommonMkrSQL.GET_PROP_PYMENT_LNKING ;
			//logger.debug(sql);
			dbUtility.createPreparedStatement(sql);
			String str = dbUtility.executeQry(regArr);
			if (!str.equalsIgnoreCase("0")) {
				flg = true;
			}

			if (flg) {
				//dbUtility = new DBUtility();
				String SQL =RegCommonMkrSQL.UPDATE_PROP_PYMENT_LNKING;
				dbUtility.createPreparedStatement(SQL);
				/*String SQL = "UPDATE IGRS_REG_MKR_PROP_PYMNT_LNKNG SET DELETE_FLAG='1', UPDATE_BY = '"+userId+"', UPDATE_DATE = to_date('"+cDate+"','dd/mm/yyyy') " +
						"WHERE REG_TXN_ID ='" + regNum + "'";*/
				String arr[]={userId,cDate,regNum};
				flg1 = dbUtility.executeUpdate(arr);
			}
			//added by simran
			if(!flg)
			{
				flg1= true;
			}

		} catch (Exception e) {
			logger.error("error in checkAlreadyInserted" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();

			} catch (Exception e) {
				logger.error("error in close connection checkAlreadyInserted"
						+ e.getStackTrace());

			}

		}

		return flg1;
	}

	// ADDED BY SIMRAN
	//ADDED BY SIMRAN
	public boolean saveLinkedAmtChecker(String regNum, Map myMap, double balDuty,
			double balfee, boolean tmpflg, String comRegNum,String userId, String cdate,RegCompleteMakerDTO dto) throws Exception {
		boolean flag = false;
		String cdate1 = getDate(cdate);
		Map newMap = myMap;
		ArrayList subList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
		
			Iterator<Map.Entry<String, ArrayList>> entries = newMap.entrySet()
					.iterator();
			while (entries.hasNext()) {
				Map.Entry<String, ArrayList> entry = entries.next();
				System.out.println("Key = " + entry.getKey() + ", Value = "
						+ entry.getValue());
				String key = entry.getKey();
				ArrayList list = entry.getValue();
				System.out.println("size of list" + list.size());
				System.out.println(list);
				String str = list.toString();
				str = str.substring(1, (str.length() - 1));
				System.out.println(str);
				String str1[] = str.split(",");
				/*
				 * this loop has to be started from i=1 because first element of
				 * the list is value already paid which is not to be stored. *
				 */
				for (int i = 0; i < str1.length; i++) {
					System.out.println(str1[i]);
					String str2[] = str1[i].split("~");
					System.out.println("regnum" + i + str2[0]);
					System.out.println("flag" + str2[1]);
					System.out.println("reg fee" + str2[2]);
					System.out.println("duty" + str2[3]);
					//String SQL = RegCommonMkrSQL.PARAM_ID_GENERATION;
					//dbUtility.createStatement();
					//String number = dbUtility.executeQry(SQL);
					//dbUtility = new DBUtility();
					
					if(tmpflg)
					{
						sql = RegCommonMkrSQL.UPDATE_LINKED_CHCEKER;
						dbUtility.createPreparedStatement(sql);
						String arr [] = {userId,
								cdate,
								regNum
							}; 
						dbUtility.executeUpdate(arr);
					}
					
					sql = RegCommonMkrSQL.INSERT_LINKED_AMOUNTS_CHECKER;
					dbUtility.createPreparedStatement(sql);
					String temp[] = new String[13];
					
					temp[0] = key; // prop id
					temp[1] = regNum; // Registration initiation number
					/*
					 * this str2[1] contains flag which tells if the linked
					 * number is a regnum or estamp or physical stamp
					 */
					if (str2[1].equals("1")) {
						//temp[2] = str2[0];
						temp[2]=comRegNum;
						temp[3] = "";
						temp[4] = "";
						temp[9] = "";
						temp[12] = "";
					} else if (str2[1].equals("2")) {
						
						temp[2] = "";
						temp[3] = str2[0];
						temp[4] = "";
						temp[9] = "";
						temp[12] = "";
					} else if (str2[1].equals("3")) {
						temp[2] = "";
						temp[3] = "";
						temp[4] = str2[0];
						temp[9] = "";
						temp[12] = "";
					}
					else if (str2[1].equals("4")) {
						temp[3] = "";
						temp[4] = "";
						temp[5] = "";
						temp[9]=str2[0];
						temp[12] = dto.getOldRegistrationDate();
					}

					temp[5] = str2[2];// reg fee
					temp[6] = str2[3];// duty
					temp[7] = userId;
					temp[8] = cdate1;
					temp[10]=dto.getDenotingCheck();
					temp[11]=dto.getLinkingCheck();
					//logger.debug("size of array in checker"+temp.length);
				boolean insertFlag=	dbUtility.executeUpdate(temp);
					if(insertFlag){
					if (str2[1].equals("2")) {
						//To change the estamp status consumed and deactive done by akansha 
						
						String deactive[] = new String[1];
						deactive[0] =str2[0].trim();
						
						sql = RegCommonMkrSQL.Change_estamp_status;
						dbUtility.createPreparedStatement(sql);
						boolean deactiveFlag =dbUtility.executeUpdate(deactive);
						
						if(!deactiveFlag){
							flag = false;
							dbUtility.rollback();
						}
						else{
							flag = true;
							dbUtility.commit();
						}
					}
					else{
						
						flag = true;	
					}
					
					}
					
					else{
						
						flag = true;
					}

				}
				
				//flag = true;
			}

		} catch (Exception e) {
			dbUtility.rollback();
			e.printStackTrace();
			logger.error(e.getStackTrace());
			
		}

		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in closing connection "
						+ "of saveLinkedAmtChecker method of regcompmkrDAO class"
						+ e.getStackTrace());
			}
		}

		return flag;
	}
	
	/*public boolean saveHoldDataChecker(String regNum, String hldFlag, String fwdPage,
			String date, String createdBy, boolean flag) throws Exception {
		// TODO Auto-generated method stub
		boolean tmpFlag = false;
		String str = "";
		//String regNum = rDTO.getRegInitNumber();
		//String holdId = rDTO.getHoldId();
		//String holdRemarks = rDTO.getRemarks();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			
			
			if(flag)
			{
				sql = RegCompCheckerSQL.UPDATE_HOLD_DATA+"'"+regNum+"'";
			}
			else
			{
				sql = RegCommonMkrSQL.INSERT_HOLD_TABLE_DATA_CHECKER;
			}
			dbUtility.createPreparedStatement(sql);
			String temp[] = new String[5];
			temp[0] = regNum;
			temp[1] = hldFlag.toUpperCase();
			temp[2] = fwdPage.toLowerCase();
			temp[3] = getDate(date);
			temp[4] = createdBy;
			temp[5] = "1";
			temp[6] = "ghkxdfghjkdfgh";
			tmpFlag = dbUtility.executeUpdate(temp);
			

		} catch (Exception e) {
			System.out.println("error in regCompMkrDAO : saveHoldData"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out
						.println("error in closing connection RegCompMkrDAO : saveHoldData "
								+ e.getStackTrace());
			}
		}
		return tmpFlag;
	}*/
	
	
	
	/*public boolean saveHoldDataChecker(RegCompleteMakerDTO rDTO, String hldFlag, String fwdPage,
			String date, String createdBy, boolean flag) throws Exception {
		// TODO Auto-generated method stub
		boolean tmpFlag = false;
		String str = "";
		String regNum = rDTO.getRegNumber();
		String holdId = rDTO.getHoldId();
		String holdRemarks = rDTO.getTxtArea();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			
			
			if(flag)
			{
				
				sql = RegCompCheckerSQL.UPDATE_HOLD_DATA+"'"+regNum+"'";
				dbUtility.createPreparedStatement(sql);
				String temp[] = new String[5];
				temp[0] = regNum;
				temp[1] = hldFlag.toUpperCase();
				temp[2] = fwdPage.toLowerCase();
				temp[3] = getDate(date);
				temp[4] = createdBy;
				temp[5] = holdId;
				temp[6] = holdRemarks;
				temp[7] = regNum;
				tmpFlag = dbUtility.executeUpdate(temp);
			}
			else
			{
				sql = RegCommonMkrSQL.INSERT_HOLD_TABLE_DATA_CHECKER;
				dbUtility.createPreparedStatement(sql);
				String temp[] = new String[5];
				temp[0] = regNum;
				temp[1] = hldFlag.toUpperCase();
				temp[2] = fwdPage.toLowerCase();
				temp[3] = getDate(date);
				temp[4] = createdBy;
				temp[5] = holdId;
				temp[6] = holdRemarks;
				tmpFlag = dbUtility.executeUpdate(temp);
			}
			
			

		} catch (Exception e) {
			System.out.println("error in regCompMkrDAO : saveHoldData"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out
						.println("error in closing connection RegCompMkrDAO : saveHoldData "
								+ e.getStackTrace());
			}
		}
		return tmpFlag;
	}*/

	
	public boolean getConsumedDocChecker(String num, String searchBy) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			String numArr[] = {num};
			
			if (RegCompConstant.SEARCH_BY_REGNUM.equalsIgnoreCase(searchBy)) {
				sql = RegCommonMkrSQL.GET_CONSUMED_BY_REGNUM;

			} else if (RegCompConstant.SEARCH_BY_ESTAMP
					.equalsIgnoreCase(searchBy)) {
				sql = RegCommonMkrSQL.GET_CONSUMED_BY_ESTAMP;

			} else if (RegCompConstant.SEARCH_BY_PHYSTAMP
					.equalsIgnoreCase(searchBy)) {
				sql = RegCommonMkrSQL.GET_CONSUMED_BY_PHYSTAMP;

			}
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);

			// mainList=dbUtility.executeQuery(sql);

			String str = dbUtility.executeQry(numArr);
			if(str.isEmpty()){
				str = "0";	
			}
			if (!str.equalsIgnoreCase("0")) {
				flag = true;
			}

		} catch (Exception e) {
			logger.error(e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("in RegCompMkrDAO:getConsumedDoc "
						+ e.getStackTrace());

			}
		}

		return flag;
	}

	  // methode by akansha to check the consumption status of estamp
	public boolean getConsumedEstamp(String num) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			String numArr[] = {num};
			sql = RegCommonMkrSQL.GET_CONSUMED_ESTAMP;
		
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);

			// mainList=dbUtility.executeQuery(sql);

			String str = dbUtility.executeQry(numArr);
			if(str.isEmpty()){
				str = "0";	
			}
			if (!str.equalsIgnoreCase("0")) {
				flag = true;
			}

		} catch (Exception e) {
			logger.error(e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("in RegCompMkrDAO:getConsumedDoc "
						+ e.getStackTrace());

			}
		}

		return flag;
	}


	public boolean checkalrdyPrsntWtness(String regNum) {
		boolean flg=false;
		boolean flg1=false;
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String regArr[] = {regNum};
			sql=RegCommonMkrSQL.GET_WITNESS_DTLS;
			dbUtility.createPreparedStatement(sql);
			String str = dbUtility.executeQry(regArr);
			if (!str.equalsIgnoreCase("0")) {
				flg = true;
			}
			logger.debug("*****flag"+flg);
			if(flg)
			{
				//dbUtility= new DBUtility();
				String regArr1[] = {regNum};
				String	SQL=RegCommonMkrSQL.UPDATE_WITNESS_DTLS;
				dbUtility.createPreparedStatement(SQL);
				flg1 = dbUtility.executeUpdate(regArr1);
				
			}
			
			
			
		}
		catch(Exception e)
		{
			logger.error("error in checkAlreadyInserted"+e.getStackTrace());
		}
		finally
		{
			try{
				dbUtility.closeConnection();
				
			}catch(Exception e)
			{
				logger.error("error in close connection checkAlreadyInserted"+e.getStackTrace());
				
			}
			
		}
		
		return flg1;
	}

	// END ADDED BY SIMRAN
	public String getPaymentFlag(String appId) throws Exception {

		String paymentFlag = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String param[]={appId};
			sql = RegCommonMkrSQL.GET_REG_COMP_PAYMENT_FLAG;
			dbUtility.createPreparedStatement(sql);
			paymentFlag = dbUtility.executeQry(param);

			//logger.debug("payment flag from database----->" + paymentFlag);
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

		return paymentFlag;

	}

	public boolean insertPaymentDetails(String regNumber, String valueOf,
			String userId, String cdate) throws Exception {

		boolean boo = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			sql = RegCommonMkrSQL.INSERT_REG_COMP_PAYMENT_DETLS;
			dbUtility.createPreparedStatement(sql);
			String[] detls = new String[5];

			detls[0] = regNumber;
			detls[1] = valueOf;
			detls[2] = "I";// payment flag initiated
			detls[3] = userId;
			detls[4] = getDate(cdate);
			boo = dbUtility.executeUpdate(detls);
			if (boo) {

				dbUtility.commit();
			} else
				dbUtility.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCommonMkrDAO in dao start"
								+ e.getStackTrace());
			}
		}
		return boo;
	}

	public boolean updtStatus(String pkey, String userId, String cdate)
			throws Exception {
		// TODO Auto-generated method stub
		boolean flg1 = false;
		boolean flg2 = false;
		boolean flg3 = false;
		String date = getDate(cdate);
		DBUtility dbUtility = null;
		try {
			
			UpdateTImeEtoken(pkey, "13");
			EtokenChange eChange = new EtokenChange(pkey,"13",null);
			Thread t = new Thread(eChange);
			t.start();
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		//	dbUtility.createStatement();

			/*sql = "UPDATE IGRS_REG_INIT_TXN_PARTY_DTLS SET IS_TXN_COMPLETE ='M',"
					+ "UPDATE_BY ='"
					+ userId
					+ "',UPDATE_DATE ='"
					+ date
					+ "' " + "WHERE REG_TXN_ID ='" + pkey + "'";*/
			/*sql = "UPDATE IGRS_REG_TXN_DETLS SET REGISTRATION_TXN_STATUS = 13, UPDATE_BY = '"+userId+"'," +
					"UPDATE_DATE = '"+date+"' WHERE REG_TXN_ID = '"+pkey+"'";*/  ///13 --registration complete at makers part
			//logger.debug(sql);
			sql = RegCommonMkrSQL.UPDATE_REG_DTLS;
					
			dbUtility.createPreparedStatement(sql);
			String arr[]={userId,date,pkey};
			flg1 = dbUtility.executeUpdate(arr);
			
			/*if (flg1) {
				String sql1 = "UPDATE IGRS_REG_COMPLETE_TXN_DETLS SET PAYMENT_FLAG='C',"
						+ "UPDATE_BY ='"
						+ userId
						+ "',UPDATE_DATE ='"
						+ date
						+ "' " + "WHERE REG_TXN_ID ='" + pkey + "'";

				flg2 = dbUtility.executeUpdate(sql1);

			}*/

			if (flg1) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}

		} catch (Exception e) {
			logger.error("error in updtStatus" + e.getStackTrace());

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection updtStatus"
						+ e.getStackTrace());
			}

		}

		return flg1;
	}

	/**
	 * 
	 * @param regNumber
	 * @return boolean
	 */
	public boolean checkstatusM(String regNumber) {
		Boolean Flag = false;
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			
			mainList = new ArrayList();
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.CHECK_STATUS_M;
			dbUtility.createPreparedStatement(sql);
			mainList = dbUtility.executeQuery(regArr);
			if (mainList != null) {
				for (int i = 0; i < mainList.size(); i++) {
					System.out.println(i);
					ArrayList list = (ArrayList) mainList.get(i);
					if (list != null) {
						System.out.println(list.get(1).toString());
						if (list.get(1).toString().equalsIgnoreCase("13")) {
							Flag = true;
						}
					}
				}

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
		System.out.println(Flag);
		return Flag;
	}

	/**
	 * 
	 * @param caveattxnnum
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPropIdLockedByCaveat(String caveattxnnum)
			throws Exception {
		mainList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String caveatArr[] = {caveattxnnum};
			sql = RegCommonMkrSQL.GET_LOCKED_PROPERTY_ID;
			dbUtility.createPreparedStatement(sql);
			mainList = dbUtility.executeQuery(caveatArr);
		} catch (Exception e) {
			logger
					.error("error in getPropIdLockedByCaveat"
							+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.debug("error in close connection getPropIdLockedByCaveat"
								+ e.getStackTrace());
			}
		}
		return mainList;
	}

	// Start this code added for getting linked reg number
	// and to check if there is any caveat on that linked registration number

	public ArrayList getLinkedRegNumber(String regNumber) throws Exception {
		mainList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.GET_LINKED_REG_NUMBER;
			dbUtility.createPreparedStatement(sql);
			mainList = dbUtility.executeQuery(regArr);

		} catch (Exception e) {
			logger.error("Error in  getLinkedRegNumber " + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in closing connection getLinkedRegNumber"
						+ e.getStackTrace());
			}
		}

		return mainList;

	}

	// End

	public ArrayList getCaveatDet(String regNumber) throws Exception {
		// TODO Auto-generated method stub
		mainList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList sublist = new ArrayList();
		ArrayList regList = new ArrayList();
		ArrayList subregList = new ArrayList();
		ArrayList propList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			
			
			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.GET_LINKED_REG_NUMBER ;
			dbUtility.createPreparedStatement(sql);
			regList = dbUtility.executeQuery(regArr);

			System.out.println(regList.size());
		//	dbUtility = new DBUtility();
			//dbUtility.createStatement();
			if (regList.size() != 0) {
				for (int k = 0; k < regList.size(); k++) {
					subregList = (ArrayList) regList.get(k);

					String num = (String) subregList.get(0);
					String propId = (String) subregList.get(1);
					if(propId.length() == 15)
						propId = "0"+propId;
					String arr[] = {num,
							propId};
					
					sql = RegCommonMkrSQL.GET_CAVEAT_DETAILS;
					dbUtility.createPreparedStatement(sql);
					list = dbUtility.executeQuery(arr);

					if (list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							sublist = (ArrayList) list.get(i);
							RegCompleteMakerDTO dto = new RegCompleteMakerDTO();
							dto.setTransactionId((String) sublist.get(0));
							String caveatid = (String) sublist.get(1);
							String caveatArr[] = {caveatid};
							String SQL = RegCommonMkrSQL.GET_CAVEAT_NAME;
							dbUtility.createPreparedStatement(SQL);
							String caveatname = dbUtility.executeQry(caveatArr);
							dto.setCaveatName(caveatname);

							dto.setStayOrdrNum((String) sublist.get(2));
							dto.setStayOrdrDet((String) sublist.get(3));
							dto.setCaveatDet((String) sublist.get(4));
							dto.setRegNumLocked((String) sublist.get(5));
							dto.setCaveatStatus((String) sublist.get(6));
							dto.setStayOrdrStrtDate((String) sublist.get(7));
							dto.setStayOrdrUptoDate((String) sublist.get(8));
							dto.setPropTxnLock((String) sublist.get(9));
							dto.setPropId((String)sublist.get(10));
							//creating error this codeto be uncommented later
							/*if (((String) sublist.get(9))
									.equalsIgnoreCase("yes")) {
								propList = getPropIdLockedByCaveat((String) sublist
										.get(0));
								dto.setPropIDLocked(propList);
							}*/
							mainList.add(dto);
						}
						//logger.debug("<------size of arraylist with caveat details"+mainList.size());
					}
				}
			}

		} catch (Exception e) {
			logger.error("error in getCaveatDet" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getCaveatDet"
						+ e.getStackTrace());
			}
		}

		return mainList;
	}

	public boolean checkalrdyPaidDuty(String regNumber) throws Exception {
		boolean flg = false;

		ArrayList sublist = new ArrayList();
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.GET_LINKED_DUTIES;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(regArr);
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					sublist = (ArrayList) list.get(i);
					String duty = (String) sublist.get(0);
					String fee = (String) sublist.get(1);
					duty = duty== null?"0":duty;
					fee = fee== null?"0":fee;
					double regduty = Double.parseDouble(duty);
					double regfee = Double.parseDouble(fee);
					/*
					 * if(!(duty.equalsIgnoreCase("")) &&
					 * !(duty.equalsIgnoreCase(null))) {
					 * if(!(duty.equalsIgnoreCase("0"))) { flg = true; } }
					 */
					if (regduty != 0 || regfee != 0) {
						flg = true;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection getAlrdyPaidDuty"
						+ e.getStackTrace());
			}
		}

		return flg;
	}

	public ArrayList getBnkCaveatDet(String regNumber) throws Exception {
		// TODO Auto-generated method stub
		mainList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList sublist = new ArrayList();
		ArrayList regList = new ArrayList();
		ArrayList subregList = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = {regNumber};
			sql = RegCommonMkrSQL.GET_LINKED_REG_NUMBER;
			dbUtility.createPreparedStatement(sql);
			regList = dbUtility.executeQuery(regArr);

			System.out.println(regList.size());
			//dbUtility = new DBUtility();
		
			if (regList.size() != 0) {
				for (int k = 0; k < regList.size(); k++) {
					subregList = (ArrayList) regList.get(k);
					String num = (String) subregList.get(0);
					String propId = (String) subregList.get(1);
					if(propId.length() == 15)
						propId = "0"+propId;
					String arr[] = {num,
							propId};
					sql = RegCommonMkrSQL.GET_BANK_CAVEAT_DETAILS;
					dbUtility.createPreparedStatement(sql);
					list = dbUtility.executeQuery(arr);

					if (list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							sublist = (ArrayList) list.get(i);
							RegCompleteMakerDTO dto = new RegCompleteMakerDTO();
							dto.setCvtBnkTxnId((String) sublist.get(0));
							dto.setOttId((String) sublist.get(1));
							dto.setLoanAcntNum((String) sublist.get(2));
							dto.setBnkName((String) sublist.get(3));
							dto.setRegId((String) sublist.get(4));
							dto.setPropId((String) sublist.get(5));
							mainList.add(dto);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("error in getBnkCaveatDet" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("error in close connection" + e.getStackTrace());
			}
		}

		return mainList;
	}
//This code for checking already inserted data in presentation table
	public boolean dltAlrdyPresentData(String regNumber, String userId, String cdate) throws Exception {
		// TODO Auto-generated method stub
	boolean flg = false;
	DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			sql = RegCommonMkrSQL.UPDATE_REG_PARTY_DTLS;
			dbUtility.createPreparedStatement(sql);
			/*sql = "UPDATE IGRS_REG_TXN_PARTY_DETLS SET PARTY_PRESENT_AT_MAKER  = NULL, " +
					"UPDATE_BY = '"+userId+"', UPDATE_DATE = to_date('"+cdate+"','dd/mm/yyyy') WHERE REG_TXN_ID ='"+regNumber+"'";*/
			//logger.debug(sql);
			//sql =RegCommonMkrSQL.UPDATE_PRESENTATION_AT_MAKER+"'"+regNumber+"'";
			String arr[]={userId,cdate,regNumber};
			flg = dbUtility.executeUpdate(arr);
		}
		catch(Exception e)
		{
			logger.error("error in checkAlrdyPresentData "+e.getStackTrace());
		}
		finally
		{
			try
				{
				dbUtility.closeConnection();
				}
			catch(Exception e)
			
			{
				logger.error("error in checkAlrdyPresentData in close connection"+e.getStackTrace());
			}
		}

		return flg;
	}

	public ArrayList holdDetails() throws Exception
	{
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query=RegCommonMkrSQL.HOLD_DATA;
			list = dbUtility.executeQuery(query);
			
		} catch (Exception exception) {
			logger.debug("Exception in holdDetails" + exception);
		}
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				System.out
						.println("error in closing connection RegCompMkrDAO : saveHoldData "
								+ e.getStackTrace());
			}
		}
		
		//logger.debug("Size of holdDetails in DAO"+list.size());
		
		
		return list;
	}

	public String getHoldName(String holdId) throws Exception {
		// TODO Auto-generated method stub
		String HoldName="";
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String holdArr[] = {holdId};
			sql =RegCommonMkrSQL.GET_HOLD_NAME; 
			dbUtility.createPreparedStatement(sql);
			HoldName =	dbUtility.executeQry(holdArr);
			
		}
		catch(Exception e)
		{
			logger.error("Error in getHoldNAme of RegCompMkrDAO"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("Error in RegcompMkrDAO in getHoldName" +
						" in close connection"+e.getStackTrace());
			}
		}
		
		return HoldName;
	}

//added by simran
/*public boolean checkAlrdyInsertdLinkChkr(String regNum) throws Exception {
		boolean flg=false;
		//boolean flg1=false;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			sql="SELECT COUNT(*) FROM IGRS_REG_COMP_PROP_PYMNT_LNKNG WHERE REG_TXN_ID =" +"'" +regNum+"'";
			String str = dbUtility.executeQry(sql);
			if(str.length()>0)
			{
				flg = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return flg;
	}*/
	
	
	public boolean checkAlrdyInsertdLinkChkr(String regNum) throws Exception {
		boolean flg=true;
		//boolean flg1=false;
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String arr[] = {regNum};
			sql=RegCommonMkrSQL.CHECK_ALREADY_INSERTED;
			dbUtility.createPreparedStatement(sql);
			String str = dbUtility.executeQry(arr);
			if(str.isEmpty()){
				str = "0";	
			}
			if(str.equals("0"))
			{
				flg = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return flg;
	}

public boolean insertCompliancedata(String partyTxnId, String partyName,
		String photoDocName, byte[] content, String cdate, String userId) {
	// TODO Auto-generated method stub
	boolean tempFlg = false;
	int rowCount;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String date = getDate(cdate);
		String insertQuery = RegCommonMkrSQL.INSERT_COMPLIANCE_DATA2;
		System.out.println(insertQuery);
		PreparedStatement prepStmt = dbUtility.returnPreparedStatement(insertQuery);
		prepStmt.setString(1, partyTxnId);
		prepStmt.setString(2, photoDocName);
		prepStmt.setBinaryStream(3, new ByteArrayInputStream(content), content.length);
		prepStmt.setString(4,userId );
		prepStmt.setString(5, date);
		rowCount=prepStmt.executeUpdate();
		//logger.debug(rowCount);
		//TODO set parameters for this prepared statement similar to what is there in dbutilty class
//		dbUtility.createStatement();
//		String date = getDate(cdate);
//		Blob myblob = 
//				sql = "INSERT INTO IGRS_REGCOMP_COMPLIANCE_DATA(PARTY_TXN_ID,PHOTO_DOC_NAME,PHOTO_DOC_CONTENT," +
//				"CREATED_BY,CREATED_DATE)" +
//				"VALUES ('"+partyTxnId+"','"+photoDocName+"','"+content+"','"+userId+"','"+date+"')";
//		System.out.println(sql);
//		String str = dbUtility.executeQry(sql);
//		if (str.equalsIgnoreCase("1")) {
//			tempFlg = true;
//		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.error("error in insertComplianceDate in regCompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
			}
		catch(Exception e)
		{
			logger.error("error in close connection regCompMkrDAO"+e.getStackTrace());
		}
	}
	return tempFlg;
}

public String getreginitNumber(String compNumber) throws Exception{
	// TODO Auto-generated method stub
	String RegInitNumber ="";
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String arr[] = {compNumber};
		sql = RegCommonMkrSQL.GET_REG_INIT_NUMBER;
		dbUtility.createPreparedStatement(sql);
		RegInitNumber= dbUtility.executeQry(arr);
		
	}catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getreginitNumber of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getreginitNumber of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return RegInitNumber;
}


/**
 * 
 * @param transactionId
 * @return ArrayList
 */
public ArrayList getCompleteCaveatDetails(String transactionId)
{
	ArrayList caveatDetails = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String trnsArr[] = {transactionId};
		sql = RegCommonMkrSQL.GET_CAVEAT_COMPLETE_DETAILS;
		dbUtility.createPreparedStatement(sql);
		//logger.debug(sql);
		caveatDetails = dbUtility.executeQuery(trnsArr);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getCompleteCaveatDetails of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getreginitNumber of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return caveatDetails;
}

/**
 * 
 * @param transactionId
 * @return ArrayList
 */
public ArrayList getBankCaveatDetails(String transactionId)
{
	ArrayList caveatDetails = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String trnsArr[] = {transactionId};
		sql = RegCommonMkrSQL.GET_BANK_CAVEAT_DETLS;
		dbUtility.createPreparedStatement(sql);
		caveatDetails = dbUtility.executeQuery(trnsArr);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getBankCaveatDetails of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getreginitNumber of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return caveatDetails;
}
	
	/**
	 * 
	 * @param regInit
	 * @return
	 * @throws Exception
	 */
	public String getDeedId(String regInit) throws Exception
	{
		String deedId = "" ;
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String regArr[] = {regInit};
			sql = RegCommonMkrSQL.GET_DEED_ID;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			deedId = dbUtility.executeQry(regArr);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in getDeedId of ragVompMkrDAO"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in close connection getDeedId of ragVompMkrDAO"+e.getStackTrace());
				
			}
		}
		return deedId;
	}
	
	/**
	 * 
	 * @param regInit
	 * @return
	 * @throws Exception
	 */
	public String getInstd(String regInit) throws Exception
	{
		String deedId = "" ;
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String regArr[] = {regInit};
			sql = RegCommonMkrSQL.GET_INST_ID;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			deedId = dbUtility.executeQry(regArr);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in getInstd of ragVompMkrDAO"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
				
			}
		}
		return deedId;
	}
	
	/**
	 * 
	 * @param regInitId
	 * @return
	 */
	public boolean checkOtherDeedDetails(String regInitId)
	{
		String count = "";
		boolean flag = false;
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String regArr[] = {regInitId};
			sql =RegCommonMkrSQL.CHECK_DATA;
			dbUtility.createPreparedStatement(sql);
			logger.debug(sql);
			count = dbUtility.executeQry(regArr);
			if(!count.equals("0"))
			{
				flag = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in getDeedId of ragVompMkrDAO"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in close connection getDeedId of ragVompMkrDAO"+e.getStackTrace());
				
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @param rDTO
	 * @param userId
	 * @param cdate
	 * @return
	 */
	public boolean insertOtherDeedDetls(RegCompleteMakerDTO rDTO, String userId, String cdate)
	{
		boolean flag = checkOtherDeedDetails(rDTO.getRegNumber());
		boolean insertData = false;
		String cDate = getDate(cdate);
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
		if(flag)
		{
			String arr[] = {
					rDTO.getPendingTaxDuties(),
					rDTO.getLoanInfo(),
					rDTO.getChargeOrCase(),
					rDTO.getRightToSale(),
					userId,
					cDate,
					rDTO.getRegNumber()
				};
			dbUtility.createPreparedStatement(RegCommonMkrSQL.UPDATE_OTHER_DEED_DETLS);
			insertData = dbUtility.executeUpdate(arr);
		}
		else{
			String arr[] = {rDTO.getRegNumber(),
					rDTO.getPendingTaxDuties(),
					rDTO.getLoanInfo(),
					rDTO.getChargeOrCase(),
					rDTO.getRightToSale(),
					userId,
					cDate
				};
			dbUtility.createPreparedStatement(RegCommonMkrSQL.INSERT_OTHER_DEED_DETLS);
			insertData = dbUtility.executeUpdate(arr);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in getDeedId of ragVompMkrDAO"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in close connection getDeedId of ragVompMkrDAO"+e.getStackTrace());
				
			}
		}
		return insertData;
	}
	
	/**
	 * 
	 * @param regInit
	 * @return
	 * @throws Exception
	 */
	public String checkPOA(String poaAuthNo) throws Exception
	{
		String regInit = "";
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String arr[] = {poaAuthNo};
			
			sql =RegCommonMkrSQL.CHECK_POA;
			dbUtility.createPreparedStatement(sql);
			logger.debug(sql);
			regInit = dbUtility.executeQry(arr);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in getDeedId of checkPOA"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in close connection checkPOA of ragVompMkrDAO"+e.getStackTrace());
				
			}
		}
		return regInit;
	}
	
	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getTransPartyDets(String regInitId) throws Exception {
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			
			dbUtility = new DBUtility();
			String regArr[] = {regInitId};
			String query= RegCommonMkrSQL.GET_PARTY_NAME;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(regArr);
			
		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyDets" + exception);
		}
		finally{
			dbUtility.closeConnection();
		}
		return list;

	}
	public boolean isLinkingPage(String regInitId)  {
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			
			dbUtility = new DBUtility();
			String regArr[] = {regInitId};
			String query= RegCommonMkrSQL.GET_PROPIDS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(regArr);
			
		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyDets" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug("Exception in getTransPartyDets" + e);
			}
		}
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	/**
	 * 
	 * @param regInitId
	 * @param partyName
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getTransPartyIds(String regInitId, String partyName) throws Exception {
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			String arr[] = {regInitId,
					partyName,
					regInitId,
					partyName};
			dbUtility = new DBUtility();
			String query= RegCommonMkrSQL.GET_PARTY_TXN_ID;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);
			
		} catch (Exception exception) {
			logger.debug("Exception in getTransPartyIds" + exception);
		}
		finally{
			dbUtility.closeConnection();
		}
		return list;

	}
	
	/**
	 * 
	 * @param dto2
	 * @param cdate
	 * @param userId
	 * @param num
	 * @param deathCertList
	 * @return
	 */
	public boolean saveDthCertDetails(RegCompleteMakerDTO dto2, String cdate,
			String userId, String num, Map deathCertList)
	{
		boolean insertData = false;
		String cDate = getDate(cdate);
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			Set mapSet = (Set)deathCertList.entrySet();
			Iterator mapIterator = mapSet.iterator();
			while(mapIterator.hasNext())
			{
				Map.Entry mapEntry = (Map.Entry)mapIterator.next();
				ArrayList valueList = (ArrayList)mapEntry.getValue();
				RegCompleteMakerDTO rDTO = (RegCompleteMakerDTO)valueList.get(0);
				String partyId =  (String)mapEntry.getKey();
				
				String arr[] = {num,
						RegCompConstant.FILE_NAME_DTH_CERTIFICATE,
						rDTO.getFilePath(),
						RegCompConstant.FILE_NAME_RELTN_PROOF,
						rDTO.getFilePathRltn(),
						rDTO.getTxtArea(),
						userId,
						cDate,
						partyId
					};
					dbUtility.createPreparedStatement(RegCommonMkrSQL.INSERT_DEATH_CERT_DETAILS);
					insertData = dbUtility.executeUpdate(arr);
				
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in saveDathCertDetails of ragcompMkrDAO"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in close connection saveDathCertDetails of ragcompMkrDAO"+e.getStackTrace());
				
			}
		}
		return insertData;
}
	
	/**
	 * 
	 * @param dto2
	 * @param cdate
	 * @param userId
	 * @param num
	 * @param poaList
	 * @return
	 */
	public boolean savePOADetails(RegCompleteMakerDTO dto2, String cdate,
			String userId, String num, Map poaList)
	{
		boolean insertData = false;
		String cDate = getDate(cdate);
		String poaMpFlg = "N";
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			Set mapSet = (Set)poaList.entrySet();
			Iterator mapIterator = mapSet.iterator();
			while(mapIterator.hasNext())
			{
				Map.Entry mapEntry = (Map.Entry)mapIterator.next();
				ArrayList valueList = (ArrayList)mapEntry.getValue();
				RegCompleteMakerDTO rDTO = (RegCompleteMakerDTO)valueList.get(0);
				//String partyId =  (String)mapEntry.getKey();
				if(rDTO.getPoaFlg().equalsIgnoreCase(RegCompConstant.POA_FROM_MP))
				{
					poaMpFlg = "Y";
				}
				
				String arr[] = {num,
						poaMpFlg,
						rDTO.getPoaAuthNum(),
						RegCompConstant.FILE_NAME_POA_DOC,
						rDTO.getFilePathPOA(),
						userId,
						cDate
					};
					dbUtility.createPreparedStatement(RegCommonMkrSQL.INSERT_POA_DETAILS);
					insertData = dbUtility.executeUpdate(arr);
				
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in savePOADetails of ragcompMkrDAO"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in close connection savePOADetails of ragcompMkrDAO"+e.getStackTrace());
				
			}
		}
		return insertData;
}
	
	public boolean checkListData(String regInitId, String userId, String cdate) throws Exception{
		String checkList = "";
		boolean flag = false;
		boolean flag1 = false;
		boolean flag2 = false;
		String cDate = getDate(cdate);
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String regArr[] = {regInitId};
			sql =RegCommonMkrSQL.CHECK_CHECKLIST;
			dbUtility.createPreparedStatement(sql);
			logger.debug(sql);
			checkList = dbUtility.executeQry(regArr);
			
			if(!checkList.equals(""))
			{
				String arr[] = new String[3];
				arr[0] = userId;
				arr[1] = cDate;
				arr[2] = checkList;
				dbUtility.createPreparedStatement(RegCommonMkrSQL.DEL_CHECKLIST_POA);
				flag = dbUtility.executeUpdate(arr);
				if(flag)
				{
					dbUtility.createPreparedStatement(RegCommonMkrSQL.DEL_CHECKLIST_DTH);
					flag1 = dbUtility.executeUpdate(arr);
					if(flag1)
					{
						arr[2] = regInitId;
						dbUtility.createPreparedStatement(RegCommonMkrSQL.DEL_CHECKLIST_MAIN);
						flag2 = dbUtility.executeUpdate(arr);
					}
				}
			}
			else
			{
				flag = true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in getDeedId of checkPOA"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in close connection checkPOA of ragVompMkrDAO"+e.getStackTrace());
				
			}
		}
		return flag;
	}

	
	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPropertyRelatedComplianeList(String regInitId) throws Exception
	{
		ArrayList propertyRelatedComplaince = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String regArr[] = {regInitId};
			
			String query=RegCompCheckerSQL.GET_PROPERTY_RELATED_COMPLIANCE;
			dbUtility.createPreparedStatement(query);
			propertyRelatedComplaince = dbUtility.executeQuery(regArr);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropertyRelatedComplianeList" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getPropertyRelatedComplianeList"+e.getStackTrace());
			}
			
		}
		return propertyRelatedComplaince;
		
	}

	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPartyRelatedComplianeList(String regInitId) throws Exception
	{
		ArrayList propertyRelatedComplaince = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			//String query=RegCompCheckerSQL.GET_PARTY_RELATED_COMPLAINCE;
			
			
			String query=RegCompCheckerSQL.GET_PARTY_RELATED_COMPLAINCE_updated;
			dbUtility.createPreparedStatement(query);
			String arr[] = {regInitId};
			
			propertyRelatedComplaince = dbUtility.executeQuery(arr);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPartyRelatedComplianeList" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getPartyRelatedComplianeList"+e.getStackTrace());
			}
			
		}
		return propertyRelatedComplaince;
		
	}

	/**
	 * 
	 * @param propertyRelatedCompliance
	 * @param regInitId
	 * @param userId
	 * @param date
	 * @return boolean
	 */
	public boolean modifyPropertyRelatedCompliance(HashMap propertyRelatedCompliance, String regInitId, String userId, String date)
	{
		boolean flag = false;
		String query = "";
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			if(propertyRelatedCompliance.size()>0)
			{
				Set mapSet = (Set)propertyRelatedCompliance.entrySet();
				Iterator mapIterator = mapSet.iterator();
				while(mapIterator.hasNext())
				{
					Map.Entry mapEntry = (Map.Entry)mapIterator.next();
					String propertyId = (String)mapEntry.getKey();
					ArrayList valueList = (ArrayList)mapEntry.getValue();
					RegCompleteMakerDTO rrdto = (RegCompleteMakerDTO)valueList.get(0);
					if(rrdto.getIsUpload().equals("Y"))
					{
						query = RegCompCheckerSQL.INSERT_UPLOAD_PROPERTY_HISTORY;
						dbUtility.createPreparedStatement(query);
						String arrTemp[] = {
								"M",
								userId,
								date,
								regInitId,
								propertyId
							};
						flag = dbUtility.executeUpdate(arrTemp);
						if(flag)
						{
							query = RegCompCheckerSQL.MODIFY_PROPERTY_RELATED_COMPLIANCE;
							dbUtility.createPreparedStatement(query);
							String arr[] = {
									rrdto.getPropImage1FilePath(),
									rrdto.getPropImage2FilePath(),
									rrdto.getPropImage3FilePath(),
									rrdto.getPropMapFilePath(),
									rrdto.getPropRinFilePath(),
									rrdto.getPropKhasraFilePath(),
									userId,
									date,
									regInitId,
									propertyId,
								};
							flag = dbUtility.executeUpdate(arr);
						}
					}
					
				}
			}
			
				
		} catch(Exception e)
		{
			logger.debug("Exception in modifyPropertyRelatedCompliance in RegCompCheckerDAO"+e);
	    }finally{
			try {
				dbUtility.commit();
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		
		return flag;
	}

	/**
	 * 
	 * @param partyRelatedCompliance
	 * @param regInitId
	 * @param userId
	 * @param date
	 * @return boolean
	 */
	public boolean modifyPartyRelatedCompliance(HashMap partyRelatedCompliance, String regInitId, String userId, String date)
	{
		boolean flag = false;
		String query = "";
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			if(partyRelatedCompliance.size()>0)
			{
				Set mapSet = (Set)partyRelatedCompliance.entrySet();
				Iterator mapIterator = mapSet.iterator();
				while(mapIterator.hasNext())
				{
					Map.Entry mapEntry = (Map.Entry)mapIterator.next();
					String partyId = (String)mapEntry.getKey();
					ArrayList valueList = (ArrayList)mapEntry.getValue();
					RegCompleteMakerDTO rrdto = (RegCompleteMakerDTO)valueList.get(0);
					if(rrdto.getIsUpload().equals("Y"))
					{
						query = RegCompCheckerSQL.INSERT_UPLOAD_PARTY_HISTORY;
						dbUtility.createPreparedStatement(query);
						String arrTemp[] = {
								"M",
								userId,
								date,
								regInitId,
								partyId
							};
						flag = dbUtility.executeUpdate(arrTemp);
						if(flag)
						{
							query = RegCompCheckerSQL.MODIFY_PARTY_RELATED_COMPLIANCE;
							dbUtility.createPreparedStatement(query);
							String arr[] = {
									rrdto.getCollectorCertDocPath(),
									rrdto.getPhotoProofDocPath(),
									rrdto.getNotAffdExecDocPath(),
									rrdto.getExecPhotoPath(),
									rrdto.getNotAffdAttrPath(),
									rrdto.getAttrPhotoPath(),
									rrdto.getClaimntPhotoPath(),
									rrdto.getPanForm60Path(),
									userId,
									date,
									regInitId,
									partyId,
								};
							flag = dbUtility.executeUpdate(arr);
						}
					}
					
				}
			}
			
				
		} catch(Exception e)
		{
			logger.debug("Exception in modifyPropertyRelatedCompliance in RegCompCheckerDAO"+e);
	    }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
			}
		}
		
		return flag;
	}

	/**
	 * 
	 * @param witnessUploadMap
	 * @param regInitId
	 * @param userId
	 * @param cdate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addWitnessPhotographDetails(Map witnessUploadMap,String regInitId, String userId, String cdate) throws Exception{
		
		String date = getDate(cdate);
		boolean flag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			if(witnessUploadMap.size()>0)
			{
				Set mapSet = (Set)witnessUploadMap.entrySet();
				Iterator mapIterator = mapSet.iterator();
				while(mapIterator.hasNext())
				{
					String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
					dbUtility.createStatement();
					String seqNum = dbUtility.executeQry(query);
					//logger.debug("<------SEQ NUMBER IN DAO"+seqNum);
					Map.Entry mapEntry = (Map.Entry)mapIterator.next();
					ArrayList valueList = (ArrayList)mapEntry.getValue();
					RegCompleteMakerDTO rDTO = (RegCompleteMakerDTO)valueList.get(0);
					String wittId =  (String)mapEntry.getKey();
					query = RegCompCheckerSQL.INSERT_PARTY_OTHER_WITNESS_DETAILS;
					dbUtility.createPreparedStatement(query);
					String arr[] = {seqNum,
							rDTO.getWitnessDocPath(),
							userId,
							date 
							};
					flag = dbUtility.executeUpdate(arr);
					if(flag)
					{
						String sql = RegCompCheckerSQL.UPDATE_WITNESS_TXN_DETAILS;
						dbUtility.createPreparedStatement(sql);
						String arrTemp[] = {userId,
								date,
								seqNum,
								regInitId,
								wittId
								};
						flag = dbUtility.executeUpdate(arrTemp);
					}
				}
			}
		}
		 catch(Exception e)
			{
				logger.debug("Exception in addWitnessPhotographDetails in RegCompCheckerDAO"+e);
		    }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("RegCompCheckerDAO---->" + e.getStackTrace());
				}
		    }
			return flag;
	}
	/**
	 * 
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */

	public ArrayList getWitnessDet(String regNumber) throws Exception {
		
		
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			
			dbUtility = new DBUtility();
			String arr[] = {regNumber};
			sql = RegCompCheckerSQL.GET_WITNESS_DETAILS;
			dbUtility.createPreparedStatement(sql);
			System.out.println(sql);
			list = dbUtility.executeQuery(arr);
			System.out.println(list.size());
			

		}catch(Exception e){
		logger.error(""+e.getStackTrace());
		}finally{
			try{
				dbUtility.closeConnection();
				}catch(Exception e)
				{
					logger.error("error in close connection"+e.getStackTrace());
				}
			}
		return list;
		}
	
	/**
	 * 
	 * @param regInitId
	 * @return String
	 */
	public String getCancelledDetails(String regInitId)
	{
		String status = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String arr[] = {regInitId};
			sql =  RegCompCheckerSQL.GET_REGISTRATION_STATUS_MAKER;
			dbUtility.createPreparedStatement(sql);
			
			status = dbUtility.executeQry(arr);
			
			
		} catch (Exception exception) {
			logger.debug("Exception in getCancelledDetails" + exception);
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
		
		return status;
	}
	
	/**
	 * 
	 * @param regCompNumber
	 * @param propId
	 * @return String
	 */
	public String getPropLockDetails(String regCompNumber, String propId)
	{
		String status = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = RegCommonMkrSQL.GET_LOCKING_DETLS;
			dbUtility.createPreparedStatement(sql);
			String arr[] = {
					regCompNumber,
					propId
			};
			status = dbUtility.executeQry(arr);
			
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropLockDetails" + exception);
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
		
		return status;
	}
	
	/**
	 * 
	 * @param regCompNumber
	 * @param propId
	 * @return ArrayList
	 */
	public ArrayList getPartyRoleIdDetails(String regInitNumber, String propId)
	{
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = RegCommonMkrSQL.GET_PARTY_ROLE_IDS;
			dbUtility.createPreparedStatement(sql);
			String arr[] = {
					propId,
					regInitNumber
					
			};
		list = dbUtility.executeQuery(arr);
			
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropLockDetails" + exception);
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
		return list;
	}

	public ArrayList getDeedInstId1(String appId) throws Exception {
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String arr[] = {appId};
			String query=RegCommonMkrSQL.GET_REG_INIT_DEED_INST_ID;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);
			
		} catch (Exception exception) {
			logger.debug("Exception in getDeedInstId" + exception);
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
		return list;

	}
	public ArrayList getAdjudicatedDutyDetls(String appId) throws Exception {
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String arr[] = {appId};
			String query=RegCommonMkrSQL.GET_ADJUDICATED_DUTY_DETS;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);
			
			
		} catch (Exception exception) {
			logger.debug("Exception in getDutyDetls" + exception);
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
		return list;

	}
	
	/**
	 * 
	 * @param regInitId
	 * @param emailSub
	 * @param emailContent
	 * @return
	 * @throws Exception
	 */
	public String emailAlert(String regInitId, String emailSub,String emailContent) throws Exception
	{
		String status = new String();
		ArrayList list =  new ArrayList();
		ArrayList subList =  new ArrayList();
		DBUtility dbUtility = null;
		try {
			
			dbUtility = new DBUtility();
			String sql = RegCompCheckerSQL.GET_PARTIES_EMAIL_ID;
			dbUtility.createPreparedStatement(sql);
			String arr[] = {regInitId};
			list = dbUtility.executeQuery(arr);
			for(int i = 0 ; i < list.size(); i ++ )
			{
				subList = (ArrayList)list.get(i);
				String emailId = (String)subList.get(1);
				if(emailId != "" && emailId != null)
				{
					dbUtility.createCallableStatement(RegCompCheckerSQL.CALL_IGRS_EMAIL_PROC);
					status = dbUtility.insertEmailData(emailId,emailSub, emailContent);
				}
			}
			
			
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
	
	/**
	 * 
	 * @param regInitId
	 * @param emailSub
	 * @param emailContent
	 * @param disttCode
	 * @return
	 * @throws Exception
	 */
	public String emailAlertDr(String regInitId, String emailSub,String emailContent,String disttCode) throws Exception
	{
		String status = new String();
		ArrayList list =  new ArrayList();
		ArrayList subList =  new ArrayList();
		ArrayList list2 =  new ArrayList();
		ArrayList subList2 =  new ArrayList();
		String userId = "";
		DBUtility dbUtility = null;
		try {
			
			dbUtility = new DBUtility();
			String sql = RegCompCheckerSQL.GET_DRO;
			dbUtility.createPreparedStatement(sql);
			String arr[] = {disttCode};
			list = dbUtility.executeQuery(arr);
			for(int i = 0 ; i < list.size(); i ++ )
			{
				subList = (ArrayList)list.get(i);
				String droId = (String)subList.get(0);
				String droName = (String)subList.get(1);
				
				String sql2 = RegCompCheckerSQL.GET_DR_OFFICERS_USER_ID;
				dbUtility.createPreparedStatement(sql2);
				String arrDR[] = {droId};
				list2 = dbUtility.executeQuery(arrDR);
				for(int j = 0 ; j < list2.size(); j ++ )
				{
					subList2 = (ArrayList)list2.get(j);
					userId = (String)subList2.get(0);
					if(userId != "" && userId != null)
					{
						dbUtility.createCallableStatement(RegCompCheckerSQL.CALL_IGRS_EMAIL_PROC_DR);
						status = dbUtility.insertEmailData(userId,emailSub, emailContent);
					}
				}
				//if(emailId != "" && emailId != null)
				//{
					//dbUtility.createCallableStatement(RegCompCheckerSQL.CALL_IGRS_EMAIL_PROC_DR);
				//	status = dbUtility.insertEmailData(emailId,emailSub, emailContent);
				//}
			}
			
			
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
	
	/**
	 * 
	 * @param regNum
	 * @param dtlsMap
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
public boolean insertConsenterDtlsMap(String regNum, Map dtlsMap, String userId)
throws Exception {
boolean flg = false;
OthrDeedDtlsDTO othrDTO = new OthrDeedDtlsDTO();
DBUtility dbUtility = null;
try {
	dbUtility = new DBUtility();
	dbUtility.createStatement();
	Iterator<Map.Entry<String, OthrDeedDtlsDTO>> entries = dtlsMap
			.entrySet().iterator();
	while (entries.hasNext()) {
		Map.Entry<String, OthrDeedDtlsDTO> entry = entries.next();
		System.out.println("Key = " + entry.getKey() + ", Value = "
				+ entry.getValue());
		String key = entry.getKey();
		othrDTO = entry.getValue();
		String arr[] = new String[17];
		String SQL = RegCommonMkrSQL.PARAM_ID_CONSENTER_DTLS; // FOR SNO
															// GENERATION
															// OF
															// WITNESS
															// DETAILS
		dbUtility.createStatement();
		String number = dbUtility.executeQry(SQL);
		//dbUtility = new DBUtility();
		sql = RegCommonMkrSQL.INSERT_CONSENTER_DTLS;
		dbUtility.createPreparedStatement(sql);
		System.out.println(sql);
		arr[0] = number;
		arr[1] = othrDTO.getFirstName();
		arr[2] = othrDTO.getMiddleName();
		arr[3] = othrDTO.getLastName();
		String gender = othrDTO.getGender();
		if(gender.equalsIgnoreCase("male"))
		{
			arr[4]="M";
		}
		else if(gender.equalsIgnoreCase("female")){
		arr[4] ="F";
		}
		arr[5] = othrDTO.getAge();
		arr[6] = othrDTO.getFatherName();
		arr[7] = othrDTO.getMotherName();
		arr[8] = othrDTO.getSpouseName();
		arr[9] = othrDTO.getNationality();
		arr[10] = othrDTO.getAddress();
		//arr[11] = othrDTO.getCountry();
		//arr[12] = othrDTO.getState();
		//arr[13] = othrDTO.getDistrict();
		arr[11] = othrDTO.getPostalCode();
		arr[12] = othrDTO.getPhoneNumber();
		//arr[16] = othrDTO.getLoanInfo();
		//arr[17] = othrDTO.getTaxDuties();
		
		arr[13] = userId;
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String Date = sdf.format(date);
		arr[14] = getDate(Date);
		arr[15] = regNum;
		arr[16] = othrDTO.getRelationshipWit();
		flg = dbUtility.executeUpdate(arr);

	}

} catch (Exception e) {
	logger.error("Error in insertDtlsMap of RegCompMkrDAO :"
			+ e.getStackTrace());
} finally {
	try {
		dbUtility.closeConnection();
	} catch (Exception e) {
		logger
				.error("Error in closing connection insertDtlsMap Of RegCompMkrDAO"
						+ e.getStackTrace());
	}
}

return flg;
}

/**
 * 
 * @param regNum
 * @return
 */
public boolean checkalrdyPrsntConsenter(String regNum) {
	boolean flg=false;
	boolean flg1=false;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String arr[] = {regNum};
		sql=RegCommonMkrSQL.GET_CONSENTER_DTLS;
		dbUtility.createPreparedStatement(sql);
		String str = dbUtility.executeQry(arr);
		logger.debug("^^^^^^^^^^^^^^^str"+str);
		if (!str.equalsIgnoreCase("0")) {
			logger.debug("^^^^^^^^^^^^^^^str"+str);
			flg = true;
		}
		
		if(flg)
		{
			//dbUtility= new DBUtility();
			String regarr[] = {regNum};
			String	SQL=RegCommonMkrSQL.UPDATE_CONSENTER_DTLS;
			dbUtility.createPreparedStatement(SQL);
			flg1=dbUtility.executeUpdate(regarr);
		}
		
		
		
	}
	catch(Exception e)
	{
		logger.error("error in checkalrdyPrsntConsenter"+e.getStackTrace());
	}
	finally
	{
		try{
			dbUtility.closeConnection();
			
		}catch(Exception e)
		{
			logger.error("error in close connection checkalrdyPrsntConsenter"+e.getStackTrace());
			
		}
		
	}
	
	return flg1;
}

/**
 * 
 * @param rdto
 * @return boolean
 * @throws Exception
 */
public boolean insertDeedDocDetails(RegCompleteMakerDTO rdto,String userId) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String arr[] = {RegCompConstant.FILE_NAME_DEED_DOC,
				RegCompConstant.FILE_UPLOAD_PATH+rdto.getRegNumber()+RegCompConstant.UPLOAD_PATH_DEED_DOC+RegCompConstant.FILE_NAME_DEED_DOC,
				userId,
				rdto.getRegNumber()
				};
		
		sql=RegCommonMkrSQL.INSERT_DEED_DOC_DETLS;
		dbUtility.createPreparedStatement(sql);
		flag = dbUtility.executeUpdate(arr);
	}
	catch(Exception e)
	{
		logger.error("error in insertDeedDocDetails"+e.getStackTrace());
	}
	finally
	{
		try{
			dbUtility.closeConnection();
			
		}catch(Exception e)
		{
			logger.error("error in close connection insertDeedDocDetails"+e.getStackTrace());
			
		}
		
	}
	return flag;
}

/**
 * 
 * @param gender
 * @return
 */
public ArrayList getRelationshipList(String gender,String langauge) {
	ArrayList mainList = new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		if("en".equalsIgnoreCase(langauge))
		{		
	sql = RegCommonSQL.GET_RELATIONSHIP_LIST;
		}
		else
		{
			sql = RegCommonSQL.GET_RELATIONSHIP_LIST_HI;
		}
	dbUtility.createPreparedStatement(sql);
    String param[]={gender};
    ArrayList list = dbUtility.executeQuery(param);
   // mainList = new ArrayList();
    ArrayList subList = null;
    RegCompleteMakerDTO dto;
    for (int i = 0; i < list.size(); i++) {
        subList = (ArrayList)list.get(i);
        dto = new RegCompleteMakerDTO();
        dto.setId(subList.get(0).toString());
        dto.setName(subList.get(1).toString());
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
 * 
 * @param propertyId
 * @return
 * @throws Exception
 */
public ArrayList getPinDetails(String propertyId) throws Exception{
	ArrayList list = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		
		dbUtility = new DBUtility();
		String arr[] = {propertyId};
		sql = RegCompCheckerSQL.GET_PIN_DETAILS;
		dbUtility.createPreparedStatement(sql);
		System.out.println(sql);
		list = dbUtility.executeQuery(arr);
		System.out.println(list.size());
		

	}catch(Exception e){
	logger.error(""+e.getStackTrace());
	}finally{
		try{
			dbUtility.closeConnection();
			}catch(Exception e)
			{
				logger.error("error in close connection"+e.getStackTrace());
			}
		}
	return list;
	}

public boolean UpdateRegistrationCompletionStatus(String regInitId, String status, String date, String userId) throws Exception
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		sql = RegCompCheckerSQL.UPDATE_REGISTRATION_STATUS;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {status,
				userId,
				
				regInitId
				};
		flag = dbUtility.executeUpdate(arr);
		if("11".equalsIgnoreCase(status)||"13".equalsIgnoreCase(status))
		{
			EtokenChange eChange = new EtokenChange(regInitId,status,null);
			Thread t = new Thread(eChange);
			t.start();
		}
		UpdateTImeEtoken(regInitId, status);
		
	}catch (Exception e) {
		logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection UpdateRegistrationCompletionStatus"+e.getStackTrace());
		}
		
	}
	return flag;
}
public boolean UpdateTImeEtoken(String regInitId,String status) throws Exception
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		sql = RegCompCheckerSQL.UPDATE_ETOKEN_MAKER_CHECK;
		dbUtility.createPreparedStatement(sql);
		String status1="";
		if("11".equalsIgnoreCase(status))
		{
			status1="H";
		}
		String arr[] = {status1,status,regInitId
				};
		flag = dbUtility.executeUpdate(arr);
		
	}catch (Exception e) {
		logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection UpdateRegistrationCompletionStatus"+e.getStackTrace());
		}
		
	}
	return flag;
}
/**
 * 
 * @param regNumber
 * @param holdId
 * @return String
 * @throws Exception
 */
public String getHoldRemarks(String regNumber, String holdId) throws Exception {

	String remarks = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		
		String arr[] = {holdId,regNumber};
		
		sql = RegCommonMkrSQL.GET_HOLD_REMARKS;
		dbUtility.createPreparedStatement(sql);
		remarks = dbUtility.executeQry(arr);
		
		
		
		
	} catch (Exception e) {
		logger.debug("Exception in getHoldRemarks : regCompMkrDAO"
				+ e.getStackTrace());
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.debug("Exception in dbUtility close : RegCompMkrDAO"
					+ e.getStackTrace());
		}

	}
	return remarks;
}

/**
 * 
 * @param remarks
 * @param suppDocFileName
 * @param suppDocFilePath
 * @param regNumber
 * @param holdId
 * @return
 * @throws Exception
 */
public boolean saveHoldResumeDetails(String remarks, String suppDocFileName, String suppDocFilePath, String regNumber, String holdId) throws Exception {

	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String filePath = suppDocFilePath+suppDocFileName;
		String arr[] = {remarks,suppDocFileName,filePath,holdId,regNumber};
		sql = RegCommonMkrSQL.UPDATE_HOLD_RESUME_DETAILS;
		dbUtility.createPreparedStatement(sql);
		flag = dbUtility.executeUpdate(arr);
		
		} catch (Exception e) {
		logger.debug("Exception in getHoldRemarks : regCompMkrDAO"
				+ e.getStackTrace());
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.debug("Exception in dbUtility close : RegCompMkrDAO"
					+ e.getStackTrace());
		}

	}
	return flag;
}


public ArrayList getConsenterList(String regNum,String langauge )
throws Exception {
ArrayList list = new ArrayList();
ArrayList subList = new ArrayList();
DBUtility dbUtility = null;
try {

dbUtility = new DBUtility();

mainList = new ArrayList();
String arr[] = {regNum}; 
sql = RegCommonMkrSQL.GET_CONSENTER_DETAILS;
		//+ "' AND CREATED_DATE='" + dt + "'";
System.out.println(sql);
dbUtility.createPreparedStatement(sql);
mainList = new ArrayList();
mainList = dbUtility.executeQuery(arr);
for (int i = 0; i < mainList.size(); i++) {
	subList = (ArrayList) mainList.get(i);
	RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
	rdto.setConsenterFirstName((String) subList.get(0));
	rdto.setConsenterLastName((String) subList.get(1));
	rdto.setConsenterSno((String) subList.get(2));
	rdto.setConsenterPhotoName("");
	rdto.setConsenterPhotoPath("");
	rdto.setPhotoChkConsenter("N");
	if("en".equalsIgnoreCase(langauge))
	{	
	rdto.setPartyRole("Consenter");
	}
	else
	{
		rdto.setPartyRole("सहमतिकर्ता");
	}
	sql = RegCompCheckerSQL.GET_PHOTO_PATH_CONSENTER;
	System.out.println(sql);
	dbUtility.createPreparedStatement(sql);
	ArrayList mainPhotoList = new ArrayList();
	String aar1[]=new String[1];
	aar1[0]= rdto.getConsenterSno();
	mainPhotoList = dbUtility.executeQuery(aar1);
	if(mainPhotoList!=null&&mainPhotoList.size()>0)
	{
		ArrayList subList1=(ArrayList) mainPhotoList.get(0);
		if(subList1.get(0)!=null && !subList1.get(0).toString().equalsIgnoreCase(""))
		{
		rdto.setConsenterPhotoName(subList1.get(0).toString().trim());
		rdto.setConsenterPhotoPath(subList1.get(1).toString().trim());
		rdto.setPhotoChkConsenter("Y");
		}
	}
	list.add(rdto);
}

} catch (Exception e) {
logger.error(e.getStackTrace());
}

finally {
try {
	dbUtility.closeConnection();
} catch (Exception e) {
	e.getStackTrace();
}

}

return list;

}

/**
 * 
 * @param eForm
 * @param userId
 * @param date
 * @param regNum
 * @return
 * @throws Exception
 */
public boolean updatePhotographDetails(RegCompleteMakerForm eForm, String userId, String date,String regNum) throws Exception{
	ArrayList partyList = eForm.getTransactPartyList();
	ArrayList witnessList = eForm.getWitnessList();
	ArrayList consenterList = eForm.getConsenterList();
	boolean tmpFlag = false;
	DBUtility dbUtility = null;
	try
	{
		Iterator itr = partyList.iterator();
		dbUtility = new DBUtility();
		while(itr.hasNext())
		{
			RegCompleteMakerDTO rrdto = (RegCompleteMakerDTO)itr.next();
			String partyId = rrdto.getPartyTypeID();
			logger.debug("^^^^party id"+partyId);
			
			String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
			dbUtility.createStatement();
			String seqNum = dbUtility.executeQry(query);
//			logger.debug("<------SEQ NUMBER IN DAO"+seqNum);
			
				String arr[] = {seqNum,
						userId,
						rrdto.getPartyPhotoName(),
						rrdto.getPartyPhotoPath()
				};
				query = RegCommonMkrSQL.INSERT_PARTY_OTHER_DETLS_2;
				dbUtility.createPreparedStatement(query);
				boolean flag = dbUtility.executeUpdate(arr);
			
				sql = RegCommonMkrSQL.UPDATE_PARTY_OTHER_DETLS;
				dbUtility.createPreparedStatement(sql);
				String[] partArr = {
						seqNum,
						userId,
						regNum,
						partyId
								
				};
				tmpFlag = dbUtility.executeUpdate(partArr);
			}
		
		Iterator itr2 = witnessList.iterator();
		while(itr2.hasNext())
		{
			RegCompleteMakerDTO rrdto = (RegCompleteMakerDTO)itr2.next();
			String partyId = rrdto.getWitnessSno();
		//	logger.debug("^^^^witness id"+partyId);
			String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
			dbUtility.createStatement();
			String seqNum = dbUtility.executeQry(query);
			//logger.debug("<------SEQ NUMBER IN DAO"+seqNum);
			
				String arr[] = {seqNum,
						userId,
						rrdto.getWitnessPhotoName(),
						rrdto.getWitnessPhotoPath()
				};
				query = RegCommonMkrSQL.INSERT_PARTY_OTHER_DETLS_2;
				dbUtility.createPreparedStatement(query);
				boolean flag = dbUtility.executeUpdate(arr);
			
				sql = RegCommonMkrSQL.UPDATE_WITNESS_OTHER_DETLS;
				dbUtility.createPreparedStatement(sql);
				String[] partArr = {
						seqNum,
						userId,
						regNum,
						partyId
								
				};
				tmpFlag = dbUtility.executeUpdate(partArr);
			}
		
		Iterator itr3 = consenterList.iterator();
		while(itr3.hasNext())
		{
			RegCompleteMakerDTO rrdto = (RegCompleteMakerDTO)itr3.next();
			String partyId = rrdto.getConsenterSno();
		//	logger.debug("^^^^witness id"+partyId);
			String query = RegCompCheckerSQL.GET_PARTY_OTHER_DETLS_SEQ;
			dbUtility.createStatement();
			String seqNum = dbUtility.executeQry(query);
			//logger.debug("<------SEQ NUMBER IN DAO"+seqNum);
			
				String arr[] = {seqNum,
						userId,
						rrdto.getConsenterPhotoName(),
						rrdto.getConsenterPhotoPath()
				};
				query = RegCommonMkrSQL.INSERT_PARTY_OTHER_DETLS_2;
				dbUtility.createPreparedStatement(query);
				boolean flag = dbUtility.executeUpdate(arr);
			
				sql = RegCommonMkrSQL.UPDATE_CONSENTER_OTHER_DETLS;
				dbUtility.createPreparedStatement(sql);
				String[] partArr = {
						seqNum,
						userId,
						regNum,
						partyId
								
				};
				tmpFlag = dbUtility.executeUpdate(partArr);
			}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		dbUtility.closeConnection();
	}
	
	
	return tmpFlag;
}

	public ArrayList getPropertyLinking(String regNo)
	{

		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			
			dbUtility = new DBUtility();
			String arr[] = {regNo};
			sql = RegCompCheckerSQL.GET_PROPERT_LINKING;
			dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			list = dbUtility.executeQuery(arr);
			//System.out.println(list.size());
			

		}catch(Exception e){
		logger.error(""+e.getStackTrace());
		}finally{
			try{
				dbUtility.closeConnection();
				}catch(Exception e)
				{
					logger.error("error in close connection"+e.getStackTrace());
				}
			}
		return list;
		
	}

	public String getPropertyStatus(String propId)
	{

		String status = "";
		DBUtility dbUtility = null;
		try
		{
			
			dbUtility = new DBUtility();
			String arr[] = {propId};
			sql = RegCompCheckerSQL.GET_PIN_STATUS;
			dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			status = dbUtility.executeQry(arr);
			//System.out.println(list.size());
			

		}catch(Exception e){
		logger.error(""+e.getStackTrace());
		}finally{
			try{
				dbUtility.closeConnection();
				}catch(Exception e)
				{
					logger.error("error in close connection"+e.getStackTrace());
				}
			}
		return status;
		
	}
	public String getPinFlag(String instId)
	{


		String status = "";
		DBUtility dbUtility = null;
		try
		{
			
			dbUtility = new DBUtility();
			String arr[] = {instId};
			sql = RegCompCheckerSQL.GET_PIN_FLAG;
			dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			status = dbUtility.executeQry(arr);
			//System.out.println(list.size());
			

		}catch(Exception e){
		logger.error("GET_PIN_FLAG:"+e.getStackTrace());
		}finally{
			try{
				dbUtility.closeConnection();
				}catch(Exception e)
				{
					logger.error("error in close connection"+e.getStackTrace());
				}
			}
		return status;
		
	
	}
	public boolean checkPinRequired(String regNumber)
	{
		boolean flag =false;
		String deedId="";
		String instId="";
		try {
			deedId =getDeedId(getreginitNumber(regNumber));
			instId=	getInstd(getreginitNumber(regNumber));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pinFlag=getPinFlag(instId);
		if(pinFlag!=null&&pinFlag.equalsIgnoreCase("Y"))
		{
			flag=true;
		}
		
	
		/*if((deedId.equals(RegCompCheckerConstant.CERTIFICATION_OF_SALE)&& instId.equals(RegCompCheckerConstant.SALE_DEED_INST_1))||
				(deedId.equals(RegCompCheckerConstant.CONVEYANCE_DEED) && (instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_1) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_2) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_3) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_4) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_5))) ||
				(deedId.equals(RegCompCheckerConstant.EXCHANGE_DEED) && instId.equals(RegCompCheckerConstant.EXCAHNGE_DEED_INST_1)) ||
				(deedId.equals(RegCompCheckerConstant.GIFT_DEED) && instId.equals(RegCompCheckerConstant.GIFT_DEED_INST_1)) ||
				(deedId.equals(RegCompCheckerConstant.LEASE_DEED) && (instId.equals(RegCompCheckerConstant.LEASE_DEED_INST_1))) ||
				(deedId.equals(RegCompCheckerConstant.RELEASE_DEED) && instId.equals(RegCompCheckerConstant.RELAESE_DEED_INST_1)) ||
				(deedId.equals(RegCompCheckerConstant.PARTITION_DEED) && (instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_1) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_2) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_3))))
		{
			flag=true;
		}*/
		else
		{
			flag=false;
		}
		
		return flag;
	}
	public String isBuilding(String propID)
	{


		String status = "";
		DBUtility dbUtility = null;
		try
		{
			
			dbUtility = new DBUtility();
			String arr[] = {propID};
			sql = RegCompCheckerSQL.IS_BUILDING;
			dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			status = dbUtility.executeQry(arr);
			//System.out.println(list.size());
			

		}catch(Exception e){
		logger.error("GET_PIN_FLAG:"+e.getStackTrace());
		}finally{
			try{
				dbUtility.closeConnection();
				}catch(Exception e)
				{
					logger.error("error in close connection"+e.getStackTrace());
				}
			}
		return status;
		
	
	}
	
	public boolean checkPinRequiredBuilding(String propId)
	{
		boolean flag =false;
		
		ArrayList list=null;
		DBUtility dbUtility = null;
		try
		{
			
			dbUtility = new DBUtility();
			String arr[] = {propId};
			dbUtility.createPreparedStatement(sql);
			sql=RegCompCheckerSQL.IS_BUILDING;
			String id=dbUtility.executeQry(arr);
			if("2".equalsIgnoreCase(id))
			{
				flag=true;
			}
			else
			{
			sql = RegCompCheckerSQL.BUILDING_LINKED;
			dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			list = dbUtility.executeQuery(arr);
			//System.out.println(list.size());
			}

		}catch(Exception e){
		logger.error("GET_PIN_FLAG:"+e.getStackTrace());
		}finally{
			try{
				dbUtility.closeConnection();
				}catch(Exception e)
				{
					logger.error("error in close connection"+e.getStackTrace());
				}
			}
	if(flag==false)
	{	
		if(list!=null&&list.size()>0)
		{
			flag= true;
		}
		else
		{
		flag=false;
		}
	}	
		return flag;
	}
	/**
	 * 
	 * @param estampCode
	 * @return
	 * @throws Exception
	 */
	public String getEstampTxnId(String estampCode) throws Exception{
		
		String estampId = "";
		DBUtility dbUtility = null;
		try
		{
			
			dbUtility = new DBUtility();
			String arr[] = {estampCode};
			sql = RegCommonMkrSQL.GET_ESTAMP_TXN_ID;
			dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			estampId = dbUtility.executeQry(arr);
			//System.out.println(list.size());
			

		}catch(Exception e){
		logger.error(""+e.getStackTrace());
		}finally{
			try{
				dbUtility.closeConnection();
				}catch(Exception e)
				{
					logger.error("error in close connection"+e.getStackTrace());
				}
			}
		return estampId;
	}
	
public String getRegInitNumber(String estampCode) throws Exception{
		
		String regInitId= "";
		DBUtility dbUtility = null;
		try
		{
			
			dbUtility = new DBUtility();
			String arr[] = {estampCode};
			sql = RegCommonMkrSQL.GET_REG_NUMBER_ESTAMP;
			dbUtility.createPreparedStatement(sql);
			//System.out.println(sql);
			regInitId = dbUtility.executeQry(arr);
			//System.out.println(list.size());
			

		}catch(Exception e){
		logger.error(""+e.getStackTrace());
		}finally{
			try{
				dbUtility.closeConnection();
				}catch(Exception e)
				{
					logger.error("error in close connection"+e.getStackTrace());
				}
			}
		return regInitId;
	}

public ArrayList getDutyDetlsForConfirmation(String appId) throws Exception {
	ArrayList list=new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String[] param={appId};
		String query=RegCommonMkrSQL.GET_REG_INIT_DUTY_DETS_FOR_CONFIRMATION;
		dbUtility.createPreparedStatement(query);
		list = dbUtility.executeQuery(param);
		
	} catch (Exception exception) {
		logger.debug("Exception in getDutyDetlsForConfirmation" + exception);
	}finally {
		try {
			
			dbUtility.closeConnection();
		} catch (Exception ex) {
			logger.debug(ex);
			//ex.printStackTrace();
		}
	}
	return list;

}

public ArrayList getDutyDetlsAdjuForConfirmation(String appId)
throws Exception {
ArrayList list = new ArrayList();
DBUtility dbUtility = null;
try {
dbUtility = new DBUtility();
String[] param = { appId };
String query = RegCommonMkrSQL.GET_REG_ADJU_DUTY_DETS_FOR_CONFIRMATION;
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

public String getInstdFromProp(String propId) throws Exception
{
	String deedId = "" ;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String regArr[] = {propId};
		sql = RegCommonMkrSQL.GET_INST_ID_FROM_PROP;
		dbUtility.createPreparedStatement(sql);
		//logger.debug(sql);
		deedId = dbUtility.executeQry(regArr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getInstd of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return deedId;
}

public String getInst(String regTxn) throws Exception
{
	String deedId = "" ;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String regArr[] = {regTxn};
		sql = RegCommonMkrSQL.GET_INST_ID;
		dbUtility.createPreparedStatement(sql);
		//logger.debug(sql);
		deedId = dbUtility.executeQry(regArr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getInstd of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return deedId;
}

public String getDeed(String regTxn) throws Exception
{
	String deedId = "" ;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String regArr[] = {regTxn};
		sql = RegCommonMkrSQL.GET_DEED_ID;
		dbUtility.createPreparedStatement(sql);
		//logger.debug(sql);
		deedId = dbUtility.executeQry(regArr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getInstd of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return deedId;
}

public ArrayList getDeedDocDetails(String regNum) throws Exception
{
	ArrayList list = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String regArr[] = {regNum};
		sql = RegCommonMkrSQL.GET_DEED_DOC_DETAILS;
		dbUtility.createPreparedStatement(sql);
		list = dbUtility.executeQuery(regArr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getInstd of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return list;
}

public String validateDeedDoc(String deedId) throws Exception   // 0- no deed 1 - master deed 2- ok
{
	String count = "2";
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement("select deed_draft_status from igrs_deed_draft_txn_dtls where deed_draft_id = ?");
		String ab=dbUtility.executeQry(new String[]{deedId});
		
		if(!"A".equalsIgnoreCase(ab)){
			count="3";
		}
		else{
		String regArr[] = {deedId};
		sql = RegCommonMkrSQL.VALIDATE_DEED_DOC;
		dbUtility.createPreparedStatement(sql);
		//logger.debug(sql);
		count = dbUtility.executeQry(regArr);
	
		if(!count.equals("0"))
		{
			sql = RegCommonMkrSQL.VALIDATE_MASTER_DEED;
			dbUtility.createPreparedStatement(sql);
			//logger.debug(sql);
			count = dbUtility.executeQry(regArr);
			if(!count.equals("1"))
				count = "2";
		}
			
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in validateDeedDoc of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection validateDeedDoc of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return count;
}

public boolean checkConsumptionOfDeedDoc(String deedId, String path, String name, String regTxnId) throws Exception   // 0- no deed 1 - master deed 2- ok
{
	String count = "";
	boolean flag = false;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement("update igrs_deed_draft_txn_dtls set deed_draft_status='A' where deed_draft_id = (select deed_draft_id from igrs_reg_txn_detls where reg_txn_id=?)");
		boolean b=dbUtility.executeUpdate(new String[] {regTxnId});
		
		
		//if(b){
		dbUtility.createPreparedStatement("update igrs_reg_txn_detls set deed_doc_filename=?, deed_doc_filepath=?, deed_draft_id=? where reg_txn_id=?");
		flag=dbUtility.executeUpdate(new String[] {name, path, deedId, regTxnId});
		if(flag){
			dbUtility.createPreparedStatement("update igrs_deed_draft_txn_dtls set deed_draft_status='D' where deed_draft_id = ?");
			dbUtility.executeUpdate(new String[] {deedId});
			
		}
		
		//}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in validateDeedDoc of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection validateDeedDoc of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return flag;
}
public boolean updatePhotoDetails(String partyId, String photoName, String photoPath) throws Exception
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		sql = RegCompCheckerSQL.UPDATE_PHOTO_PATH_PARTY;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {photoName,
				photoPath,
				
				partyId
				};
		flag = dbUtility.executeUpdate(arr);
		
	}catch (Exception e) {
		logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection UpdateRegistrationCompletionStatus"+e.getStackTrace());
		}
		
	}
	return flag;
}

public boolean updatePhotoDetailsWitness(String partyId, String photoName, String photoPath) throws Exception
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		sql = RegCompCheckerSQL.UPDATE_PHOTO_PATH_WITNESS;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {photoName,
				photoPath,
				
				partyId
				};
		flag = dbUtility.executeUpdate(arr);
		
	}catch (Exception e) {
		logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection UpdateRegistrationCompletionStatus"+e.getStackTrace());
		}
		
	}
	return flag;
}
public boolean updatePhotoDetailsConsenter(String partyId, String photoName, String photoPath) throws Exception
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		sql = RegCompCheckerSQL.UPDATE_PHOTO_PATH_CONSENTER;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {photoName,
				photoPath,
				
				partyId
				};
		flag = dbUtility.executeUpdate(arr);
		
	}catch (Exception e) {
		logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection UpdateRegistrationCompletionStatus"+e.getStackTrace());
		}
		
	}
	return flag;
}
public boolean insertTimeMakerStart(RegCompleteMakerForm eForm,String userId,String sroid) throws Exception
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		ArrayList list;
		dbUtility = new DBUtility();
		sql = RegCompCheckerSQL.GET_ETOKEN_MAKER_CHECK;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {eForm.getRegDTO().getRegNumber()};
		list = dbUtility.executeQuery(arr);
		if(list!=null&&list.size()>0)
		{
			flag=true;
		}
		else
		{
			sql = RegCompCheckerSQL.INSERT_ETOKEN_MAKER_CHECK;
			dbUtility.createPreparedStatement(sql);
			String arr1[] = {eForm.getRegDTO().getRegNumber(),userId,sroid};
			flag=dbUtility.executeUpdate(arr1);
		}
		
	}catch (Exception e) {
		logger.error("RegCompCheckerDAO in UpdateRegistrationCompletionStatus start" + e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection UpdateRegistrationCompletionStatus"+e.getStackTrace());
		}
		
	}
	return flag;
}

public boolean sendMail(String regInitNumber,String content,String userId)
{
	boolean flag=true;
	DBUtility dbUtility = null;
	try {
		ArrayList list;
		dbUtility = new DBUtility();
		sql = RegCompCheckerSQL.GET_EMAIL_ID;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {regInitNumber};
		list = dbUtility.executeQuery(arr);
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{	
			ArrayList subList=(ArrayList) list.get(i);
			String emailId=(String)subList.get(0);
			insertEmailData(userId, emailId, content,regInitNumber);
			
			}
		}
		
	}catch(Exception e)
	{
		logger.debug(e.getStackTrace());
		logger.debug("error");
	}
	return flag; 
}
public boolean sendSMS(String regInitNumber,String content,String userId)
{
	boolean flag=true;
	DBUtility dbUtility = null;
	try {
		ArrayList list;
		dbUtility = new DBUtility();
		sql = RegCompCheckerSQL.GET_SMS_NUMBER;
		dbUtility.createPreparedStatement(sql);
		String arr[] = {regInitNumber};
		list = dbUtility.executeQuery(arr);
		dbUtility.closeConnection();
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
			ArrayList subList=(ArrayList) list.get(i);
			String number=(String)subList.get(0);
			insertSMSData(userId,number,content);
			}
		}
		
	}catch(Exception e)
	{
		logger.debug(e.getStackTrace());
	}
	return flag; 
}

public String insertSMSData(String userId, String mobileno, String content)
throws Exception {
	
String status = new String();
String[] param={mobileno,userId,content};
DBUtility dbUtility = null; 
try {
	 dbUtility = new DBUtility();
	 dbUtility.createPreparedStatement(RegCompCheckerSQL.INSERT_SMS_CONTENT);
	 dbUtility.executeUpdate(param);
	 //dbUtility.executeQuery(param);
   
 } catch (Exception x) {
logger.debug(x);
x.printStackTrace();
 } finally {
	 dbUtility.closeConnection();
 }
return status;
}

public String insertEmailData(String userId, String emailId, String content,String regInitID )
throws Exception {
	
String status = new String();
DBUtility dbUtility = null; 
try {
	 dbUtility = new DBUtility();
	 dbUtility.createPreparedStatement(RegCompCheckerSQL.EMAIL_DATA);
	 String param1[]={emailId,"Regarding Regisration apllication Number"+regInitID,content};
	 dbUtility.executeUpdate(param1);
 } catch (Exception x) {
logger.debug(x);
x.printStackTrace();
 } finally {
	 dbUtility.closeConnection();
 }
return status;
}

public String getDateTIME()
{
	DBUtility dbUtility = null;
	String status = null;
	try {
		dbUtility = new DBUtility();
		/*dbUtility.createStatement();
		String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
		status = dbUtility.executeQry(query);*/
		String query="";
		
			query=RegCompCheckerSQL.GET_SYSDATETIME;
		
			
		
		dbUtility.createStatement();
		status = dbUtility.executeQry(query);
		
	} catch (Exception exception) {
		logger.debug("Exception in checkFinalDocGenFlag" + exception);
		logger.debug(exception.getStackTrace());

	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection checkFinalDocGenFlag"+e.getStackTrace());
		}
		
	}
	
	return status;
	

}
public String getofficeName(String officeId, String lang) {
	String offcName = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String SQL ="";
		if(lang.equalsIgnoreCase("en"))
			SQL=SealsSQL.GET_OFFICE_NAME;
		else
			SQL=SealsSQL.GET_OFFICE_NAME_HI;
		dbUtility.createPreparedStatement(SQL);
		String arr[] = {officeId};
		offcName = dbUtility.executeQry(arr);
	} catch (Exception exception) {
		logger.debug("Exception in getofficeName" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return offcName;
	
}
public double getPlotArea(String valTxnId)
{

	String deedId = "" ;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String regArr[] = {valTxnId};
		sql = RegCompCheckerSQL.GET_AREA_PLOT;
		dbUtility.createPreparedStatement(sql);
		//logger.debug(sql);
		deedId = dbUtility.executeQry(regArr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getInstd of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return Double.parseDouble(deedId);

}

public double getAgriArea(String valTxnId)
{

	String deedId = "" ;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String regArr[] = new String[]{valTxnId};
		sql = RegCompCheckerSQL.GET_AREA_AGRI;
		dbUtility.createPreparedStatement(sql);
		//logger.debug(sql);
		deedId = dbUtility.executeQry(regArr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		e.printStackTrace();
		logger.debug("error in getInstd of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return (Double.parseDouble(deedId)*10000);

}
public ArrayList areaList(String propId)
{

	ArrayList compList = new ArrayList();
	String SQL;
	logger
			.info("Wipro in RegCompMkrDAO - getComplList() before EXCUTIN QUERY");
	SQL = RegCompCheckerSQL.GET_PROP_IDS;
	DBUtility dbUtility = null;
	try {
		 dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(SQL);
		compList = dbUtility.executeQuery(new String[]{propId});
		logger
				.info("Wipro in RegCompMkrDAO - getComplList() AFTER EXCUTIN QUERY"
						+ SQL);
		logger
				.info("Wipro in RegCompMkrDAO - getComplList() AFTER EXCUTIN QUERY list values"
						+ compList);
	} catch (Exception e) {
		logger
				.error("Wipro in RegCompMkrDAO -Exception in calling at DAO Class at getComplList ()  "
						+ e);
	}
	finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return compList;

}
public String getPropertyType(String propId)
{

	String deedId = "" ;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String regArr[] = {propId};
		sql = RegCommonMkrSQL.PROP_ID;
		dbUtility.createPreparedStatement(sql);
		//logger.debug(sql);
		deedId = dbUtility.executeQry(regArr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getInstd of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return deedId;

}
public ArrayList linkingDashBoardList(String regInitId)
{

	ArrayList compList = new ArrayList();
	String SQL;
	logger
			.info("Wipro in RegCompMkrDAO - getComplList() before EXCUTIN QUERY");
	SQL = RegCompCheckerSQL.GET_LINK_IDS;
	DBUtility dbUtility = null;
	try {
		 dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(SQL);
		compList = dbUtility.executeQuery(new String[]{regInitId});
		logger
				.info("Wipro in RegCompMkrDAO - getComplList() AFTER EXCUTIN QUERY"
						+ SQL);
		logger
				.info("Wipro in RegCompMkrDAO - getComplList() AFTER EXCUTIN QUERY list values"
						+ compList);
	} catch (Exception e) {
		logger
				.error("Wipro in RegCompMkrDAO -Exception in calling at DAO Class at getComplList ()  "
						+ e);
	}
	finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return compList;

}
//added by saurav
public ArrayList<String> getSlotActiveCheck(String regNumber, String officeId){
	ArrayList<String> slotStatus=new ArrayList<String>();
	ArrayList list = new ArrayList();
	DBUtility dbUtility = null;
	try{
		dbUtility = new DBUtility();
		String sql = RegCommonMkrSQL.GET_SLOT_STATUS;
		dbUtility.createPreparedStatement(sql);
		String arr [] = {regNumber,officeId};
		list = dbUtility.executeQueryCustomArrayList(arr);
		if(null!=list & list.size()>0){
		slotStatus=(ArrayList<String>) list.get(0);
		}
	}catch (Exception e) {
		e.printStackTrace();
		logger.debug("error in getMarketValueExchangeDeed of regCompCkrDAO" + e.getStackTrace());
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error in close connection getMarketValueExchangeDeed of regCompCkrDAO" + e.getStackTrace());
		}
	}
	return slotStatus;
}
//Added by Rustam
public ArrayList getCyberTehsilFormDetails(String regNum) throws Exception
{
	ArrayList list = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		String regArr[] = {regNum};
		sql = RegCommonMkrSQL.GET_CYBER_TEHSIL_FORM_DETAILS;
		dbUtility.createPreparedStatement(sql);
		list = dbUtility.executeQuery(regArr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.debug("error in getInstd of ragVompMkrDAO"+e.getStackTrace());
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
			
		}
	}
	return list;
}
}

