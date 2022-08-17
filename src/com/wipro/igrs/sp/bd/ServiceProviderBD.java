/**
 * ServiceProviderBD.java
 */

package com.wipro.igrs.sp.bd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.dao.UserRegistrationDAO;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.ServiceProviderConstants;
import com.wipro.igrs.sp.action.ServiceProviderAction;
import com.wipro.igrs.sp.dao.ServiceProviderDAO;
import com.wipro.igrs.sp.dto.ServiceProviderDTO;
import com.wipro.igrs.util.CommonUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceProviderBD {
	ServiceProviderDAO serviceProviderDAO = null;

	IGRSCommon common = null;
	private Logger logger = 
		(Logger) Logger.getLogger(ServiceProviderBD.class);
	/**
	 * Constructor name : ServiceProviderBD
	 * 
	 * @throws Exception
	 */
	public ServiceProviderBD() throws Exception {

		serviceProviderDAO = new ServiceProviderDAO();
	}

	/**
	 * Method name :getSPUsers
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getSPUsers() throws Exception {
		ArrayList userlist = null;
		try {
			
			ArrayList arrayList = serviceProviderDAO.getSPUsers();
			logger.info("arraylist" + arrayList);
			userlist = new ArrayList();
			ServiceProviderDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setSpuserid(templist.get(0).toString());
				providerDTO.setSpusername(templist.get(0).toString());
				userlist.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return userlist;
	}

	/**
	 * Method name :getSPUsersInfo
	 * 
	 * @param spuserid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getSPUsersInfo(String spuserid) throws Exception {
		ArrayList usersinfo = null;
		try {
			ArrayList arrayList = serviceProviderDAO.getSPUsersInfo(spuserid);
			logger.info("arraylist" + arrayList);
			usersinfo = new ArrayList();
			ServiceProviderDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setSpaddress(templist.get(0).toString());
				providerDTO.setSppostalcode(templist.get(1).toString());
				providerDTO.setSpdistrctid(templist.get(2).toString());
				providerDTO.setSpdistrictname(templist.get(3).toString());
				usersinfo.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return usersinfo;
	}

	/**
	 * Method name :getSPTehsils
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getSPTehsils() throws Exception {
		ArrayList tehsillist = null;
		try {
			common = new IGRSCommon();
			ArrayList arrayList = common.getTehsil();
			logger.info("arraylist" + arrayList);
			tehsillist = new ArrayList();
			ServiceProviderDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setSptehsilid(templist.get(0).toString());
				providerDTO.setSptehsilname(templist.get(1).toString());
				tehsillist.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return tehsillist;
	}
	
	
	//edited for tehsils selected on base of district
	
	 public ArrayList getThesil(String distId) throws Exception {
		ArrayList tehsilist = null;
		try {
			
			ArrayList arrayList = serviceProviderDAO.getThesil(distId);
			logger.info("arraylist" + arrayList);
			tehsilist = new ArrayList();
			ServiceProviderDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setSptehsilid(templist.get(0).toString());
				providerDTO.setSptehsilname(templist.get(1).toString());
				tehsilist.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return tehsilist;
	}
	 
	/*public ArrayList getThesil(String distId) throws Exception {
		ArrayList tehsillist = null;
		try {
			common = new IGRSCommon();
			ArrayList arrayList = common.getThesil(distId);
			//ArrayList arrayList = common.getTehsil();
			logger.info("arraylist" + arrayList);
			tehsillist = new ArrayList();
			ServiceProviderDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setSptehsilid(templist.get(0).toString());
				providerDTO.setSptehsilname(templist.get(1).toString());
				tehsillist.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return tehsillist;
	}
	*/
	/**
	 * Method name :getDistricts
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	
	
	 public ArrayList getUserDistrict(String userid) throws Exception {
		ArrayList userdistrict = null;
		try {
			ArrayList arrayList = serviceProviderDAO.getUserDistrict(userid);
			logger.info("arraylist" + arrayList);
			userdistrict = new ArrayList();
			ServiceProviderDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setSpdistrctid(templist.get(0).toString());
				providerDTO.setSpdistrictname(templist.get(1).toString());
				userdistrict.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return userdistrict;
	}
	 
	public ArrayList getDistricts() throws Exception {

		ArrayList districtlist = null;
		try {
			common = new IGRSCommon();
			ArrayList arrayList = common
					.getDistrict();
			//logger.info("arraylist" + arrayList);
			districtlist = new ArrayList();
			ServiceProviderDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setSpdistrctid(templist.get(0).toString());
				providerDTO.setSpdistrictname(templist.get(1).toString());
				districtlist.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return districtlist;
	}

	/**
	 * Method name :genereteSPLicence
	 * 
	 * @param distid
	 * @return
	 * @throws Exception
	 * 
	 */
	public String genereteSPLicence(String distid,String licencett) throws Exception {
		String splicence = "";
		
		
		String spstring = "SP";
		String typid="";
		String districtid="";
		//added by shruti
		if(licencett!=null && licencett.length()==1)
		{
			typid="0"+licencett;
		}
		else
		{
			typid=licencett;
		}
		
		if(distid!=null && distid.length()==1){
			districtid ="0"+distid;
		}
		//end of code by shruti
		else if(distid!=null && distid.length()>=3){
			districtid = distid.substring(0, 2);
		
		}else if(distid ==null){
			districtid="";
		}else{
			
			districtid=distid;
			
		}
		
		
		String drocode = getDROOfficecode(distid);
		String drocodevalue="";
		
		//added by shruti
		
		if(drocode!=null && drocode.length()==1){
			drocodevalue ="0"+ drocode;
		
		}
		//end of code by shruti
		else if(drocode!=null && drocode.length()>=3){
			drocodevalue = drocode.substring(0, 2);
		
		}else if(drocode==null){
			drocodevalue="";
		}else{
			drocodevalue=drocode;
		}
				
		Calendar cal = new GregorianCalendar();
		String mon = "";
		int month = cal.get(Calendar.MONTH) + 1;
		
		//getting the month of licence number being generated.
		
	
		if (month < 10) {
			mon = "0" + month;
		} else {
			mon = String.valueOf(month);
		}
		
		int year = cal.get(Calendar.YEAR);
		
		//String serialnumber = getSPSerialNumber();
		
		String serialnumber;
		
		//getting the month of recently saved licence number;
		String mmlicencenumber=getMMLicenceNumber();
		
	if(mon.equals(mmlicencenumber))
	{
		serialnumber = getSPSerialNumber();
	}
	else
	{
		serialnumber=getNewSPSerialNumber();
	}
		
	
		//added by shruti
		if(serialnumber.length()==1)
		{
			splicence = spstring + typid + districtid + drocodevalue + mon + year +"0000"+ serialnumber;
		}
		else if(serialnumber.length()==2)
		{
			splicence = spstring + typid +districtid + drocodevalue + mon + year +"000"+ serialnumber;
		}
		else if(serialnumber.length()==3)
		{
			splicence = spstring + typid + districtid + drocodevalue + mon + year +"00"+ serialnumber;
		}
		else if(serialnumber.length()==4)
		{
			splicence = spstring + typid + districtid + drocodevalue + mon + year +"0"+ serialnumber;
		}
		else
		{
			splicence = spstring + typid + districtid + drocodevalue + mon + year + serialnumber;
		}
		return splicence;
	}
	//end of code by shruti
	
	/*
	 * 
	 * //original code

			splicence = spstring + districtid + drocodevalue + mon + year + serialnumber;
			return splicence;
				
		*/
		
	/**
	 * Method name :getDROOfficecode
	 * 
	 * @param distid
	 * @return
	 * @throws Exception
	 * 
	 */
	public String getDROOfficecode(String distid) throws Exception {
		String drocode = "";
		ArrayList arrayList = serviceProviderDAO.getDROOfficecode(distid);
		for (int i = 0; i < arrayList.size(); i++) {
			ArrayList templist = (ArrayList) arrayList.get(i);
			drocode = templist.get(0).toString();
		}
		return drocode;
	}

	/**
	 * Method name :getSPSerialNumber
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	private String getSPSerialNumber() throws Exception {
		return serviceProviderDAO.getSPSerialNumber();
	}
	
	private String getNewSPSerialNumber() throws Exception{
		return serviceProviderDAO.getNewSPSerialNumber();
	}
	

	/**
	 * Method name :submitSPLlicence *
	 * 
	 * @param providerDTO
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public boolean submitSPLlicence(ServiceProviderDTO providerDTO,
			String userid) throws Exception {
		return serviceProviderDAO.submitSPLlicence(providerDTO, userid);
	}

	/**
	 * Method name :getSPBanks
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getSPBanks() throws Exception {
		ArrayList banklist = null;
		try {
			ArrayList arrayList = serviceProviderDAO.getSPBanks();
			logger.info("arraylist" + arrayList);
			banklist = new ArrayList();
			ServiceProviderDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setSpbankid(templist.get(0).toString());
				//logger.info("~~~~~"+templist.get(0).toString());
				providerDTO.setSpbankname(templist.get(1).toString());
				//logger.info("~~~~~"+templist.get(1).toString());
				banklist.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return banklist;
	}

	/**
	 * Method name :getSPBankInfo
	 * 
	 * @param spbankid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getSPBankInfo(String spbankid) throws Exception {
		ArrayList usersinfo = null;
		try {
			ArrayList arrayList = serviceProviderDAO.getSPBankInfo(spbankid);
			logger.info("arraylist" + arrayList);
			usersinfo = new ArrayList();
			ServiceProviderDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setSpbankaddress(templist.get(0).toString());
				providerDTO.setSpbankphonenumber(templist.get(1).toString());
				usersinfo.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return usersinfo;
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
	public boolean submitSPLbanklicence(ServiceProviderDTO providerDTO,
			String userid) throws Exception {
		return serviceProviderDAO.submitSPLbanklicence(providerDTO, userid);
	}

	public ArrayList getUserDetails(ServiceProviderDTO providerDTO,
			HttpServletRequest request) throws Exception {

		String[] spUserId = new String[1];
		spUserId[0] = providerDTO.getSpusername();
		logger.info("spUserId" + spUserId[0]);
		ServiceProviderDTO spDTO = null;
		ArrayList userDetails = serviceProviderDAO.getUserDetails(spUserId);
		ArrayList userDetailsList = new ArrayList();
		logger.info("SIZE IN BD"+userDetails.size());
		if (userDetails.isEmpty()) {
			request.setAttribute("noData", "noData");
			spDTO = new ServiceProviderDTO();
			spDTO.setSpFirstName("");
			spDTO.setSpMiddleName("");
			spDTO.setSpLastName("");
			spDTO.setSpGender("");
			spDTO.setSpOccupation("");
			spDTO.setSpFatherName("");
			spDTO.setSpMotherName("");
			spDTO.setSpSpouseName("");
			spDTO.setSpaddress("");
			spDTO.setSpCountry("");
			spDTO.setSpState("");
			spDTO.setSpDistrict("");
			//spDTO.setSpDOB("");
			spDTO.setSpPhoneNumber("");
			spDTO.setSpMobileNumber("");
			spDTO.setSpPrimaryEmail("");
			spDTO.setSpAlternateEmail("");
			spDTO.setSpPhotoIdProof("");
			spDTO.setSpIdNo("");
			spDTO.setSpbankname("");
			spDTO.setSpbankaddress("");
			spDTO.setSpRegistrationNumber("");
			spDTO.setSpVolumeNumber("");
			spDTO.setSpbookNumber("");
			spDTO.setSpFatherName("");
			

		}
		else {
			logger.info("userdetails in BD" + userDetails);
			// ArrayList userDetailsList=new ArrayList();
			for (int i = 0; i < userDetails.size(); i++) {
				ArrayList user = (ArrayList) userDetails.get(i);
				if (userDetails != null) {
					spDTO = new ServiceProviderDTO();
					spDTO.setSpFirstName((String) user.get(0)); // iurd.FIRST_NAME,
					spDTO.setSpMiddleName((String) user.get(1));// iurd.MIDDLE_NAME,iurd.
					spDTO.setSpLastName((String) user.get(2));// LAST_NAME,
					spDTO.setSpGender((String) user.get(3)); // iurd.GENDER,
					spDTO.setSpOccupation((String) user.get(4)); // iurd.OCCUPATION,
					spDTO.setSpFatherName((String) user.get(5)); // iurd.GUARDIAN_NAME,
					spDTO.setSpDOB((String) user.get(6)); // iurd.DOB,
					
					spDTO.setSpMotherName((String) user.get(7)); // iurd.MOTHER_NAME,
					spDTO.setSpSpouseName((String) user.get(8)); // iurd.SPOUSE_NAME,
					spDTO.setSpaddress((String) user.get(9)); // iurd.ADDRESS,
					spDTO.setSpCountry((String) user.get(10)); // icm.COUNTRY_NAME,
					spDTO.setSpDistrict((String) user.get(11)); // idm.DISTRICT_NAME,
					spDTO.setSpState((String) user.get(12)); // ism.STATE_NAME,
					spDTO.setSppostalcode((String) user.get(13)); // iurd.POSTAL_CODE,
					spDTO.setSpPhoneNumber((String) user.get(14)); // iurd.PHONE_NUMBER,
					spDTO.setSpMobileNumber((String) user.get(15)); // iurd.MOBILE_NUMBER,
					spDTO.setSpPrimaryEmail((String) user.get(16)); // iurd.EMAIL_ID,
					spDTO.setSpAlternateEmail((String) user.get(17)); // iurd.ALTERNATE_EMAIL_ID
																		// ,
					spDTO.setSpPhotoIdProof((String) user.get(18)); // iurd.PHOTO_PROOF_TYPE_ID,
					spDTO.setSpPhotoIdNumber((String) user.get(19)); // iurd.PHOTO_PROOF_ID
																		// ,
					spDTO.setSpbankname((String) user.get(20)); // iurd.BANK_NAME,
					spDTO.setSpbankaddress((String) user.get(21)); // iurd.BANK_ADDRESS,
					spDTO.setSpVolumeNumber((String) user.get(22)); // iurd.OLD_REG_VOLUME_NO,
					spDTO.setSpbookNumber((String) user.get(23)); // iurd.OLD_REG_BOOK_NO,
					spDTO.setSpRegistrationNumber((String) user.get(24)); // iurd.OLD_REG_NUMBER
					//
					spDTO.setSpPhotoIdName((String) user.get(25));
					
					logger.info("dddddddddddddddddddddddddd-->"+spDTO.getSpPhotoIdName());
					
					//spDTO.setSpusername((String) user.get(25));//iurd.USER_ID
					
					/*String uname=spUserId[0].toString();
					request.setAttribute("username",uname);*/
					userDetailsList.add(spDTO);
					
					
					

				}

			}
		}
		return userDetailsList;
	}

	public ArrayList getlicenceuserdetails(String refid) throws Exception {

		String[] spUserId = new String[2];
		spUserId[0] = refid;
		spUserId[1] = refid;
		ServiceProviderDTO spDTO = null;
		ArrayList userDetails = serviceProviderDAO
				.getlicenceuserdetails(spUserId);
		ArrayList userDetailsList = new ArrayList();

		for (int i = 0; i < userDetails.size(); i++) {
			ArrayList user = (ArrayList) userDetails.get(i);
			if (user != null) {
				spDTO = new ServiceProviderDTO();
				
				common = new IGRSCommon();
				
				//String d=user.get(5).toString();
				//String d1=d.substring(0, 10);
				//logger.info("DDDDDD DOB~~~~~"+d1);
				//SimpleDateFormat sdf=new SimpleDateFormat(common.DATE_FORMATJSP);
                //SimpleDateFormat sdf2 = new SimpleDateFormat(common.DATE_FORMATDB);  
                	//Date dob1=sdf2.parse(d1);
                	//String dob2=sdf.format(dob1);
                	//logger.info("DDDDDD DOB~~~"+dob2);
				spDTO.setSpuserid((String) user.get(0));
				spDTO.setSpFirstName((String) user.get(1));
				spDTO.setSpMiddleName((String) user.get(2));
				spDTO.setSpLastName((String) user.get(3));
				spDTO.setSpGender((String) user.get(4));
				spDTO.setSpDOB((String) user.get(5));
				//spDTO.setSpDOB(dob2);
				spDTO.setSpFatherName((String) user.get(6));
				spDTO.setSpMotherName((String) user.get(7));
				spDTO.setSpSpouseName((String) user.get(8));
				spDTO.setSpPermAddress((String) user.get(9));
				spDTO.setSpCountry((String) user.get(10));
				spDTO.setSpState((String) user.get(11));
				spDTO.setSpDistrict((String) user.get(12));
				spDTO.setSppostalcode((String) user.get(13));
				spDTO.setSpPrimaryEmail((String) user.get(14));
				spDTO.setSpAlternateEmail((String) user.get(15));
				spDTO.setSpPhoneNumber((String) user.get(16));
				spDTO.setSpMobileNumber((String) user.get(17));
				spDTO.setSpPhotoIdProof((String) user.get(18));
				spDTO.setSpDurationFrom((String) user.get(19));
				spDTO.setSpDurationTo((String) user.get(20));
				spDTO.setSpDateOfInsurance((String) user.get(21));
				spDTO.setSplicencenumber((String) user.get(22));
				spDTO.setDocumentname((String) user.get(23));
				spDTO.setSpOfficeAddress((String) user.get(24));
				spDTO.setSpOfficeDistrict((String) user.get(25));
				spDTO.setSpOtherInformation((String) user.get(26));
				spDTO.setSpRefrenceId((String) user.get(27));
			}
			userDetailsList.add(spDTO);
logger.info("SP OFFICE ADDRESS FROM BD---->"+spDTO.getSpOfficeAddress());
		}

		return userDetailsList;
	}

	public ArrayList getrefrenceId(String[] userid,ServiceProviderDTO providerDTO,
			HttpServletRequest request) throws Exception {

		
		logger.info("UUUUUUSEr"+userid[0].toString());
		String[] spDetails = new String[5];
		logger.info(" getSpDurationFrom " + providerDTO.getSpDurationFrom()
				+ "getSpDurationTo() " + providerDTO.getSpDurationTo());
		logger.info("getrefrenceId) " + providerDTO.getSpRefrenceId()
				+ "providerDTO.getSplicencenumber() "
				+ providerDTO.getSplicencenumber());
		
		spDetails[0]=userid[0].toString();
		spDetails[1] = CommonUtil.getConvertedDate(providerDTO
				.getSpDurationFrom());
		spDetails[2] = CommonUtil.getConvertedDate(providerDTO
				.getSpDurationTo());
		spDetails[3] = providerDTO.getSpRefrenceId();
		spDetails[4] = providerDTO.getSplicencenumber();
		ArrayList userDetailsList = new ArrayList();


		
		ServiceProviderDTO spDTO = null;
		ArrayList userDetails = serviceProviderDAO.getrefrenceId(spDetails);
		logger.info("userDetails>>>>>>>>>>...in BD" + userDetails);
		if (userDetails.isEmpty()) {
			request.setAttribute("noData", "noData");
			spDTO = new ServiceProviderDTO();
			spDTO.setSpuserid("");
			spDTO.setSpLicenceTxnId("");
			spDTO.setSpuserid("");
			spDTO.setSpUserlicenceNo("");

		} else {
			logger.info("userDetails>>>>>>in bd" + userDetails);

			logger.info("userdetails in BD" + userDetails);

			for (int i = 0; i < userDetails.size(); i++) {
				ArrayList user = (ArrayList) userDetails.get(i);
				if (userDetails != null) {
					spDTO = new ServiceProviderDTO();
					spDTO.setSpLicenceTxnId((String) user.get(0));
					spDTO.setSpuserid((String) user.get(1));
					spDTO.setSpUserlicenceNo((String) user.get(2));
					userDetailsList.add(spDTO);

				}

			}
		}
		return userDetailsList;
	}

	public boolean submitspUserDetails(ServiceProviderDTO providerDTO,
			ArrayList listFileNames, String strFilePath, HttpSession session)
			throws Exception {
		
		
		return serviceProviderDAO.submitspUserDetails(providerDTO,
				listFileNames, strFilePath, session);

	}
	
	public boolean submitspBankDetails1(ServiceProviderDTO providerDTO,
			ArrayList listFileNames, String strFilePath, HttpSession session)
	throws Exception {

			return serviceProviderDAO.submitspBankDetails1(providerDTO, listFileNames, strFilePath, session);
}
//modified by shruti .

	public ArrayList getSPUserTypeList()
	{
		return serviceProviderDAO.getSpUserTypeList();
	}
	
	public boolean storelicence(ServiceProviderDTO providerDTO) throws Exception {
		return serviceProviderDAO.storelicence(providerDTO);
	}	
	
	public String getMMLicenceNumber() throws Exception {
		return serviceProviderDAO.getRecentLicenceNumber();
	}
	
	public boolean submitspBankDetails(ServiceProviderDTO providerDTO,HttpSession session)
			throws Exception {
		
		
		return serviceProviderDAO.submitspBankDetails(providerDTO, session);
		//return serviceProviderDAO.submitspBankDetails(providerDTO,
				//listFileNames, strFilePath, session);
	}
	
		
	
	/*
	 * public boolean submitspUserDetails(ServiceProviderDTO providerDTO,
			ArrayList listFileNames, String strFilePath, HttpSession session)
			throws Exception {
		return serviceProviderDAO.submitspUserDetails(providerDTO,
				listFileNames, strFilePath, session);

	}
	 */
	
	public boolean checkUserLicenceIssuance(String[] spuname)throws Exception
	{
		return serviceProviderDAO.checkUserLicenceIssuance(spuname);
	}
	public boolean checkUserLicenceRenewal(String[] spuname)throws Exception
	{
		return serviceProviderDAO.checkUserLicenceRenewal(spuname);
	}
	
	public String getSpUserTypeId() throws Exception {
		return serviceProviderDAO.getSpUserTypeId();
	}
	
	public String getSpBankUserTypeId() throws Exception{
		return serviceProviderDAO.getSpBankUserTypeId();
	}
	
	
	public boolean submitspUserDetailsNew(ServiceProviderDTO providerDTO,
			ArrayList listFileNames, String strFilePath, HttpSession session,String[] spUser)
			throws Exception {
		
		return serviceProviderDAO.submitspUserDetailsNew(providerDTO,
				listFileNames, strFilePath, session,spUser);

	}
	
	public boolean submitspBankDetailsNew(ServiceProviderDTO providerDTO,
			ArrayList listFileNames, String strFilePath, HttpSession session,String[] spBankUser)
	throws Exception {
		
			return serviceProviderDAO.submitspBankDetailsNew(providerDTO, listFileNames, strFilePath, session,spBankUser);
}
	
	public ArrayList getdeacLicencedUserDetailsList(ServiceProviderDTO providerDTO,HttpServletRequest request) throws Exception {

		String[] spUserId = new String[1];
		spUserId[0] = providerDTO.getSpusername();
		logger.info("spUserId" + spUserId[0]);
		ServiceProviderDTO spDTO = null;
		ArrayList userDetails = serviceProviderDAO
				.getdeaclicenceuserdetails(spUserId);
		ArrayList userDetailsList = new ArrayList();

		for (int i = 0; i < userDetails.size(); i++) {
			ArrayList user = (ArrayList) userDetails.get(i);
			if (user != null) {
				spDTO = new ServiceProviderDTO();
				
				common = new IGRSCommon();
				spDTO.setSpuserid((String) user.get(0));
				spDTO.setSpFirstName((String) user.get(1));
				spDTO.setSpMiddleName((String) user.get(2));
				spDTO.setSpLastName((String) user.get(3));
				spDTO.setSpGender((String) user.get(4));
				spDTO.setSpDOB((String) user.get(5));
				//spDTO.setSpDOB(dob2);
				spDTO.setSpFatherName((String) user.get(6));
				spDTO.setSpMotherName((String) user.get(7));
				spDTO.setSpSpouseName((String) user.get(8));
				spDTO.setSpPermAddress((String) user.get(9));
				spDTO.setSpCountry((String) user.get(10));
				spDTO.setSpState((String) user.get(11));
				spDTO.setSpDistrict((String) user.get(12));
				spDTO.setSppostalcode((String) user.get(13));
				spDTO.setSpPrimaryEmail((String) user.get(14));
				spDTO.setSpAlternateEmail((String) user.get(15));
				spDTO.setSpPhoneNumber((String) user.get(16));
				spDTO.setSpMobileNumber((String) user.get(17));
				spDTO.setSpPhotoIdProof((String) user.get(18));
				spDTO.setSpDurationFrom((String) user.get(19));
				spDTO.setSpDurationTo((String) user.get(20));
				spDTO.setSpDateOfInsurance((String) user.get(21));
				spDTO.setSplicencenumber((String) user.get(22));
				spDTO.setDocumentname((String) user.get(23));
				spDTO.setSpOfficeAddress((String) user.get(24));
				//spDTO.setSpOfficeDistrict((String) user.get(25));
				spDTO.setSpOtherInformation((String) user.get(25));
				//spDTO.setSpRefrenceId((String) user.get(27));
				spDTO.setSpPhotoIdName((String)user.get(26));
				spDTO.setSpOfficeDistrict((String)user.get(27));
				logger.info("DDDDDSPSP~~~"+(String)user.get(27));
			}
			userDetailsList.add(spDTO);

		}
		return userDetailsList;
	}
	
//added 22
	/*
	public boolean deacUser(ServiceProviderDTO providerDTO)
			throws Exception {
		
		return serviceProviderDAO.deacUser(providerDTO);
	}
	*/
	public boolean deacUser(ServiceProviderDTO providerDTO,String[] deacUser) throws Exception
	{
		return serviceProviderDAO.deac(providerDTO,deacUser);
	}
	
	
	public ServiceProviderDTO renewLicence(ServiceProviderDTO providerDTO) throws Exception {

		String[] spUserId = new String[1];
		spUserId[0] = providerDTO.getSpusername();
		logger.info("spUserId" + spUserId[0]);
		ServiceProviderDTO spDTO = null;
		ArrayList userDetails = serviceProviderDAO.getrenewlicenceuserdetails(spUserId);
		ArrayList userDetailsList = new ArrayList();
logger.info("size"+userDetails.size());
		for (int i = 0; i < userDetails.size(); i++) {
			ArrayList user = (ArrayList) userDetails.get(i);
			if (user != null) {
				spDTO = new ServiceProviderDTO();
				
				common = new IGRSCommon();
				spDTO.setSpusername((String) user.get(0));
				spDTO.setSpFirstName((String) user.get(1));
				spDTO.setSpMiddleName((String) user.get(2));
				spDTO.setSpLastName((String) user.get(3));
				spDTO.setSpGender((String) user.get(4));
				spDTO.setSpDOB((String) user.get(5));
				//spDTO.setSpDOB(dob2);
				spDTO.setSpFatherName((String) user.get(6));
				spDTO.setSpMotherName((String) user.get(7));
				spDTO.setSpSpouseName((String) user.get(8));
				spDTO.setSpPermAddress((String) user.get(9));
				spDTO.setSpCountry((String) user.get(10));
				spDTO.setSpState((String) user.get(11));
				spDTO.setSpDistrict((String) user.get(12));
				spDTO.setSppostalcode((String) user.get(13));
				spDTO.setSpPrimaryEmail((String) user.get(14));
				spDTO.setSpAlternateEmail((String) user.get(15));
				spDTO.setSpPhoneNumber((String) user.get(16));
				spDTO.setSpMobileNumber((String) user.get(17));
				spDTO.setSpPhotoIdProof((String) user.get(18));
				spDTO.setSpDurationFrom((String) user.get(19));
				
				spDTO.setSpDurationTo((String) user.get(20));
				spDTO.setSpDateOfInsurance((String) user.get(21));
				spDTO.setSplicencenumber((String) user.get(22));
				spDTO.setDocumentname((String) user.get(23));
				spDTO.setSpOfficeAddress((String) user.get(24));
				//spDTO.setSpOfficeDistrict((String) user.get(25));
				spDTO.setSpOtherInformation((String) user.get(25));
				//spDTO.setSpRefrenceId((String) user.get(27));
				spDTO.setSpPhotoIdName((String)user.get(26));
				spDTO.setSpOfficeDistrict((String)user.get(27));
				spDTO.setSpdistrctid((String)user.get(28));
				spDTO.setRenewFromDate((String) user.get(29));
				
				if(user.get(30).toString().length()<4)
				{
				spDTO.setSptehsilid((String)user.get(30));
				spDTO.setSpbankid((String)user.get(30));
				spDTO.setBankAuthPerson("");
				spDTO.setBankPersonDesignation("");
				spDTO.setBankContactInfo("");
				}
				else
				{
					spDTO.setSpbankid((String)user.get(30));
					spDTO.setBankAuthPerson((String)user.get(31));
					spDTO.setBankPersonDesignation((String)user.get(32));
					spDTO.setBankContactInfo((String)user.get(33));
				}
			}
			userDetailsList.add(spDTO);

		}
		return spDTO;
	}
	
	public boolean StatusAtLicenseRenewal(ServiceProviderDTO providerDTO) throws Exception
	{
		return serviceProviderDAO.StatusAtLicenseRenewal(providerDTO);
	}
	
	
	public ServiceProviderDTO getdeacLicencedUserDetailsList(ServiceProviderDTO providerDTO) throws Exception {

		String[] spUserId = new String[1];
		spUserId[0] = providerDTO.getSpusername();
		logger.info("spUserId" + spUserId[0]);
		ServiceProviderDTO spDTO = null;
		ArrayList userDetails = serviceProviderDAO
				.getdeaclicenceuserdetails(spUserId);
		ArrayList userDetailsList = new ArrayList();
logger.info("size"+userDetails.size());
		for (int i = 0; i < userDetails.size(); i++) {
			ArrayList user = (ArrayList) userDetails.get(i);
			if (user != null) {
				spDTO = new ServiceProviderDTO();
				
				common = new IGRSCommon();
				spDTO.setSpusername((String) user.get(0));
				spDTO.setSpFirstName((String) user.get(1));
				spDTO.setSpMiddleName((String) user.get(2));
				spDTO.setSpLastName((String) user.get(3));
				spDTO.setSpGender((String) user.get(4));
				spDTO.setSpDOB((String) user.get(5));
				//spDTO.setSpDOB(dob2);
				spDTO.setSpFatherName((String) user.get(6));
				spDTO.setSpMotherName((String) user.get(7));
				spDTO.setSpSpouseName((String) user.get(8));
				spDTO.setSpPermAddress((String) user.get(9));
				spDTO.setSpCountry((String) user.get(10));
				spDTO.setSpState((String) user.get(11));
				spDTO.setSpDistrict((String) user.get(12));
				spDTO.setSppostalcode((String) user.get(13));
				spDTO.setSpPrimaryEmail((String) user.get(14));
				spDTO.setSpAlternateEmail((String) user.get(15));
				spDTO.setSpPhoneNumber((String) user.get(16));
				spDTO.setSpMobileNumber((String) user.get(17));
				spDTO.setSpPhotoIdProof((String) user.get(18));
				spDTO.setSpDurationFrom((String) user.get(19));
				spDTO.setRenewFromDate((String) user.get(20));
				spDTO.setSpDurationTo((String) user.get(20));
				spDTO.setSpDateOfInsurance((String) user.get(21));
				spDTO.setSplicencenumber((String) user.get(22));
				spDTO.setDocumentname((String) user.get(23));
				spDTO.setSpOfficeAddress((String) user.get(24));
				//spDTO.setSpOfficeDistrict((String) user.get(25));
				spDTO.setSpOtherInformation((String) user.get(25));
				//spDTO.setSpRefrenceId((String) user.get(27));
				spDTO.setSpPhotoIdName((String)user.get(26));
				spDTO.setSpOfficeDistrict((String)user.get(27));
				logger.info("DDDDDSPSP~~~"+(String)user.get(27));
				spDTO.setSpdistrctid((String)user.get(28));
				if(user.get(29).toString().length()<4)
				{
				spDTO.setSptehsilid((String)user.get(29));
				spDTO.setSpbankid((String)user.get(29));
				spDTO.setBankAuthPerson("");
				spDTO.setBankPersonDesignation("");
				spDTO.setBankContactInfo("");
				}
				else
				{
					spDTO.setSpbankid((String)user.get(29));
					spDTO.setBankAuthPerson((String)user.get(30));
					spDTO.setBankPersonDesignation((String)user.get(31));
					spDTO.setBankContactInfo((String)user.get(32));
				}
			}
			userDetailsList.add(spDTO);

		}
		return spDTO;
	}
	
	public String getCurrentYear()throws
    Exception  
    {
          //String propId=null;
          ServiceProviderDAO providerDAO = new ServiceProviderDAO();
          String currDate=providerDAO.getCurrDateTime();
          DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
          //DateFormat formatter= new SimpleDateFormat("mm/dd/yyyy hh:mm:ss"); 
          //formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
          Date date = (Date)formatter.parse(currDate);
          SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
          SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
          SimpleDateFormat formatDay = new SimpleDateFormat("dd");
          String currentYear = formatYear.format(date);
          System.out.println("year----------**********************>"+currentYear);
          return currentYear;
    }
	
	public String getCurrentMonth()throws
    Exception  
    {
          ServiceProviderDAO providerDAO = new ServiceProviderDAO();
          String currDate=providerDAO.getCurrDateTime();
          DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
          Date date = (Date)formatter.parse(currDate);
          SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
          String currentMonth=formatMonth.format(date);
          logger.info("month----------****************"+currentMonth);
          return currentMonth;
    }
	public String getCurrentDay()throws
    Exception  
    {
          ServiceProviderDAO providerDAO = new ServiceProviderDAO();
          String currDate=providerDAO.getCurrDateTime();
          DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
          Date date = (Date)formatter.parse(currDate);
          SimpleDateFormat formatDay = new SimpleDateFormat("dd");
          String currentDay=formatDay.format(date);
          logger.info("day--------********"+currentDay);
          return currentDay;
    }
	
	public String getCurrentDate()throws
    Exception  
    {
          ServiceProviderDAO providerDAO = new ServiceProviderDAO();
          /*String currDate=providerDAO.getCurrDateTime();
          DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
          Date date = (Date)formatter.parse(currDate);
          SimpleDateFormat formatDay = new SimpleDateFormat("dd");
          String currentDay=formatDay.format(date);
          logger.info("day--------********"+currentDay);
          return currentDay;*/
          return providerDAO.getCurrentDate();
    }
	
	
	
	
	
	
	
}
