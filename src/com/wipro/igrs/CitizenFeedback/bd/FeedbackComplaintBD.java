package com.wipro.igrs.CitizenFeedback.bd;

import com.wipro.igrs.CitizenFeedback.action.CryptoLibrary;
import com.wipro.igrs.CitizenFeedback.action.FeedbackRegistrationAction;
import com.wipro.igrs.CitizenFeedback.constant.FeedbackConstant;
import com.wipro.igrs.CitizenFeedback.dao.FeedbackComplaintDAO;
import com.wipro.igrs.CitizenFeedback.dto.FeedbackComplaintDTO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

public class FeedbackComplaintBD {

	
	/**
	 * @author Sourav Khatri
	 *
	 */

		public static String UserID = null;
		private static Logger logger = 
			(Logger) Logger.getLogger(FeedbackComplaintBD.class);
		public String getUserID()
	    {
	        return UserID;
	    }
	    
	    private String userIDgenerator() 
		 {
	        String id = "USR" + new Date().getTime();
	        logger.debug("this is userIDgenerator() & value of ID " + id);
	        return id;
	    }
	    
		/**
		 * getCountry  
		 * Returns ArrayList of Countries.
		 *
		 * @return ArrayList
		 * @throws Exception 
		 *
		 * @Exception
		 */
	    
		public ArrayList getFServices() throws Exception {
			FeedbackComplaintDAO dao = new FeedbackComplaintDAO();
			ArrayList ret = dao.getFServices();
			ArrayList list = new ArrayList();

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList lst = (ArrayList) ret.get(i);
					FeedbackComplaintDTO dto = new FeedbackComplaintDTO();
					dto.setServiceName((String) lst.get(0));
					dto.setServiceID(lst.get(1).toString());
					list.add(dto);
				}
			}
			return list;
		}
		
	public ArrayList getFDistrict() throws Exception {
			FeedbackComplaintDAO dao = new FeedbackComplaintDAO();
			ArrayList ret = dao.getFdistrict();
			ArrayList list = new ArrayList();

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList lst = (ArrayList) ret.get(i);
					FeedbackComplaintDTO dto = new FeedbackComplaintDTO();
					dto.setDistrictID(lst.get(0).toString());
					dto.setDistrictName((String) lst.get(1));
					list.add(dto);
				}
			}
			return list;
		}
		
			/**
		 * getState
		 * Returns ArrayList of States.
		 * @param stateId
		 *
		 * @return ArrayList
		 * @throws Exception 
		 *
		 */
				/**
		 * insertUserRegDetails
		 * Returns boolean value in order to check the insertion.
		 * @param dto
		 *
		 * @return boolean
		 * @throws Exception 
		 *
		 */
		
		/* public boolean insertUserRegDetails(UserRegistrationDTO dto) throws
		Exception   //main
		{
			boolean regsuccess = false;
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
			Date currentDate= new Date();
			UserRegistrationDAO dao = new UserRegistrationDAO();
			//UserID = userIDgenerator();
			String userTxnId=dao.getUserTxnIdSeq();
			String day = dto.getUserDay();
			String month = dto.getUserMonth();
			String year = dto.getUserYear();
			String[] param = new String[30];
			param[0] = userTxnId;
			param[1] = dto.getUserFirstName();
			param[2] = dto.getUserMiddleName();
			param[3] = dto.getUserLastName();
			param[4] = dto.getUserGender();
			param[5] = dto.getOccupationId();                      //occupation
			param[6] = getOracleDate(day, month, year);
			param[7] = dto.getUserFatherName();
			param[8] = dto.getUserMotherName();
			param[9] = dto.getUserSpouseName();
			String address=dto.getUserAddress();
			address=address.replace(",", " ");
			param[10] = address;
			param[11] = dto.getUserCountryID();
			param[12] = dto.getUserStateID();
			param[13] = dto.getUserCityID();
			param[14] = "" + dto.getUserPostalCode();
			param[15] = "" + dto.getUserPhoneNumber();
			param[16] = dto.getUserMobileNumber();
			param[17] = dto.getUserPrimaryEmail();
			param[18] = dto.getUserSecondaryEmail();
			param[19] = dto.getIdProofCode();
			String proof=dto.getIdProofNumber();
			proof=proof.replace(",","");
			param[20] = proof;
			param[21] = dto.getBankName();
			param[22] = dto.getBankAddress();
			param[23] = dto.getRegistrationNumber();
			param[24] = dto.getUserId();
			
			CryptoLibrary crypt = new CryptoLibrary();                  //uncommented by roopam
			//String encryptpswd = crypt.encrypt(dto.getUserPass());    //uncommented by roopam
			String encryptpswd = crypt.SHAencryption(dto.getUserPass());//added by roopam for implementing one way encryprion
			//String encryptpswd = dto.getUserPass();                   //commented by roopam
			
			param[25] = encryptpswd;
			logger.debug(param[25]);
			logger.debug("upper case"+param[25]);
			
		
			param[26] = dto.getHintQuestID();
			param[27] = dto.getUserHintAnswer();
			param[28] = "A";//User status being Active
			param[29] = "Admin";	//Created by field as per the Role Id from the session;
			//param[30] = dto.getOccupationId();
			
			String[] userParam = new String[3];
			userParam[0] = dto.getUserId();
			userParam[1] = CommonConstant.REGISTERED_USER;
			userParam[2] =  "Admin";	//Created by field as per the Role Id from the session;
			//userParam[3] =  formatter.format(currentDate);
			
			String[] rUser = new String[3];
			rUser[0] = dto.getUserId();
			rUser[1] = CommonConstant.DEFAULT_ROLE_GROUP;	
			rUser[2] = CommonConstant.REGISTERED_USER;
					
			regsuccess = dao.insertUserRegDetails(param,userParam,rUser);
			
			
			return regsuccess;
		}
		/**
		 * insertSPRegistrationDetails
		 * Returns boolean value in order to check the insertion.
		 * @param dto
		 *
		 * @return boolean
		 * @throws Exception 
		 *
		 */
	
	/* public boolean insertSPRegistrationDetails(UserRegistrationDTO dto) throws
		Exception  //main update
		{
			logger.info("BD:  insertSPRegistrationDetails starts here.");
			UserRegistrationDAO dao = new UserRegistrationDAO();
			boolean licenseGeneratedSuccess = false;
			String dFromDate = dto.getDurationFrom();
			String dToDate = dto.getDurationTo();
			String iDate = dto.getIssuanceDate();

			String[] licenseparam = new String[9];
			licenseparam[0] = refIDgenerator();
			licenseparam[1] = dto.getUserId();
			licenseparam[2] = getConvertedDate(dFromDate);
			licenseparam[3] = getConvertedDate(dToDate);
			licenseparam[4] = getConvertedDate(iDate);
			licenseparam[5] = dto.getLicenseNumber();
			licenseparam[6] = dto.getOfficialAddress();
			licenseparam[7] = dto.getOfficialCity();
			licenseparam[8] = dto.getOtherInformation();

			String[] userParam = new String[2];
			userParam[0] = dto.getUserFirstName();
			userParam[1] = dto.getUserId();

			String[] userRoleParam = new String[2];
			userRoleParam[0] = CommonConstant.LICENSED_USER_ROLE_ID;
			userRoleParam[1] = dto.getUserId();



			dao.insertSPRegistrationDetails(licenseparam, userParam, userRoleParam);
			//logger.info("BD:  Called:  insertSPRegistrationDetails method in
			// DAO Class");
			return licenseGeneratedSuccess;
		}
		/**
		 * getConvertedDate
		 * Returns String which returns a parsed date format.
		 * @param dFromDate
		 *
		 * @return String
		 *
		 * @Exception
		 */
		/*public static String getConvertedDate(String dFromDate) {
			String inputDate = dFromDate;
			logger.info(inputDate);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			logger.debug("  " + inputDate + " parses as ");
			String finalDate = "";
			Date newDate = new Date();
			try {
				newDate = formatter.parse(inputDate);
				SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
				finalDate = format.format(newDate);
				logger.info(finalDate);
			} catch (ParseException e) {
				System.out.print(e);
			}
			return  finalDate;
		}

		/**
		 * getUserDetails
		 * Returns UserRegistrationDTO which contains all the values which are
		 *  retrieved from DAO..
		 * @param  userName
		 *
		 * @return UserRegistrationDTO
		 * @throws Exception 
		 *
		 */
	/*	public UserRegistrationDTO getUserDetails(String userName) throws Exception
		{
			UserRegistrationDTO dto = new UserRegistrationDTO();
			UserRegistrationDAO dao = new UserRegistrationDAO();
			ArrayList ret = dao.getUserDetails(userName);
			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList list = (ArrayList) ret.get(i);

					dto.setUserId((String) list.get(0));
					dto.setUserFirstName((String) list.get(1));
					dto.setUserMiddleName((String) list.get(2));
					dto.setUserLastName((String) list.get(3));
					dto.setUserGender((String) list.get(4));
					dto.setUserFatherName((String) list.get(5));
					dto.setUserMotherName((String) list.get(6));
					dto.setUserSpouseName((String) list.get(7));
					dto.setUserAddress((String) list.get(8));
					dto.setUserCountry((String) list.get(9));
					dto.setUserState((String) list.get(10));
					dto.setUserPostalCode((String) list.get(11));
					dto.setUserPhoneNumber((String) list.get(12));
					dto.setUserPrimaryEmail((String) list.get(13));
					dto.setUserMobileNumber((String) list.get(14));
				}
			}
			return dto;
		}
		/**
		 * getSPLicenseView
		 * Returns a List which contains all the values which are retrieved from
		 *  the database..
		 * @param  fDate
		 * @param  tDate
		 *
		 * @return ArrayList
		 * @throws Exception 
		 *
		 */

	/*	public ArrayList getUserDetailsView(String userName) throws Exception
		{
			UserRegistrationDAO dao = new UserRegistrationDAO();
			ArrayList ret = dao.getUserDetails(userName);
			ArrayList userList = new ArrayList();

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList lst = (ArrayList) ret.get(i);
					UserRegistrationDTO dto = new UserRegistrationDTO();
					dto.setUserFirstName((String) lst.get(1));
					dto.setUserMiddleName((String) lst.get(2));
					dto.setUserLastName((String) lst.get(3));

					userList.add(dto);
				}
				
			}

			return userList;
		}
		/**
		 * getSPLicenseView
		 * Returns a List which contains all the values which are retrieved from
		 *  the database..
		 * @param  fDate
		 * @param  tDate
		 *
		 * @return ArrayList
		 * @throws Exception 
		 *
		 */

	/*	public ArrayList getSPLicenseView(String fDate, String tDate) throws
		Exception
		{
			UserRegistrationDAO dao = new UserRegistrationDAO();
			ArrayList list = dao.getSPLicenseView(fDate, tDate);
			ArrayList returnList = new ArrayList();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList lst = (ArrayList) list.get(i);
					UserRegistrationDTO dto = new UserRegistrationDTO();
					dto.setUserLicenseRefID((String) lst.get(0));
					dto.setUserId((String) lst.get(1));
					dto.setUserFirstName((String) lst.get(2));
					dto.setLicenseNumber((String) lst.get(3));

					logger.debug("BD: Ref ID:  "+(String) lst.get(0));
					returnList.add(dto);
				}
			}
			return returnList;
		}

		/**
		 * getUserSPLicenseDetails
		 * Returns the parsed date.
		 * @param  refName
		 * @return UserRegistrationDTO
		 * @throws Exception 
		 */
	/*	public UserRegistrationDTO getUserSPLicenseDetails(String refName) throws
		Exception
		{
			UserRegistrationDTO dto = new UserRegistrationDTO();
			UserRegistrationDAO dao = new UserRegistrationDAO();
			ArrayList ret = dao.getUserSPLicenseDetails(refName);
			ArrayList ret2 = dao.userSPLicenseDetails(refName);

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList list = (ArrayList) ret.get(i);
					logger.debug("List is :     "+list);
					dto.setUserId((String) list.get(0));
					dto.setUserFirstName((String) list.get(1));
					dto.setUserMiddleName((String) list.get(2));
					dto.setUserLastName((String) list.get(3));
					dto.setUserGender((String) list.get(4));
					dto.setUserFatherName((String) list.get(5));
					dto.setUserMotherName((String) list.get(6));
					dto.setUserSpouseName((String) list.get(7));
					dto.setUserAddress((String) list.get(8));
					dto.setUserCountry((String) list.get(9));
					dto.setUserState((String) list.get(10));
					dto.setUserPostalCode((String) list.get(11));
					dto.setUserPhoneNumber((String) list.get(12));
					dto.setUserPrimaryEmail((String) list.get(13));
					dto.setUserMobileNumber((String) list.get(14));
					dto.setUserDOB((String) list.get(15));
					dto.setUserSecondaryEmail((String) list.get(16));
				}
			}

			if(ret2 != null){
				for(int i = 0; i<ret2.size(); i++){
					ArrayList list = (ArrayList) ret2.get(i);
					logger.debug("List is:-    "+list);

					dto.setDurationFrom((String) list.get(0));
					dto.setDurationTo((String) list.get(1));
					dto.setIssuanceDate((String) list.get(2));
					dto.setLicenseNumber((String) list.get(3));
					dto.setOfficialAddress((String) list.get(4));
					dto.setOfficialCity((String) list.get(5));
					dto.setOtherInformation((String) list.get(6));

				}
			}
			return dto;
		}
		/**
		 * getOracleDate
		 * Returns the parsed date.
		 * @param  day
		 * @param  month
		 * @param  year
		 * @return String
		 * @ParseException
		 */
	/*	public static String getOracleDate(String day, String month, String year) {
			String inputDate = day + "-" + month + "-" + year;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			logger.debug("  " + inputDate + " parses as ");
			String finalDate = "";
			Date newDate = new Date();
			try {
				newDate = formatter.parse(inputDate);
				SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
				finalDate = format.format(newDate);

				logger.debug(finalDate);
			} catch (ParseException e) {
				System.out.print(e);
			}
			return  finalDate;
		}
		private String refIDgenerator() {
			String id = "SPL" + new Date().getTime();
			return id;
		}
		
		
		public boolean updatePswd(String updatedPswd, String loginName, String oldPswd) throws Exception 
		{
			UserRegistrationDAO uregDAO = new UserRegistrationDAO();
			//CryptoLibrary cryptPswd = new CryptoLibrary();
			String[] reg_pin = new String[3];
			//reg_pin[0] = cryptPswd.encrypt(updatedPswd);
			reg_pin[0] = updatedPswd;
			reg_pin[1] = loginName;
			//reg_pin[2] = cryptPswd.encrypt(oldPswd);
			reg_pin[2] =oldPswd;
			boolean success = uregDAO.updatePswdDetails(reg_pin);
			
			return success;
		}
		
		public String matchPswd(String loginName) throws Exception 
		{
			UserRegistrationDAO uregDAO = new UserRegistrationDAO();
			//CryptoLibrary cryptPswd = new CryptoLibrary();
			String retrievedPass = new String();
			//reg_pin[0] = loginName;
			ArrayList pswdList = uregDAO.getStoredPswd(loginName);
			if (pswdList != null) {
				for (int i = 0; i < pswdList.size(); i++) {
					ArrayList lst = (ArrayList) pswdList.get(i);
					//retrievedPass = cryptPswd.decrypt((String)lst.get(1));
					retrievedPass = (String)lst.get(1);
					
				}
			}
			
			return retrievedPass;
		}
		
		/**
		 * getCurrentYear
		 * for inserting registration initiation other property details in db.
		 * @param String
		 * @return boolean
		 * @author ROOPAM
		 */
	/*	public String getCurrentYear()throws
		Exception  
		{
			//String propId=null;
			UserRegistrationDAO uregDAO = new UserRegistrationDAO();
			String currDate=uregDAO.getCurrDateTime();
			DateFormat formatter= new SimpleDateFormat("mm/dd/yyyy hh:mm:ss"); 
			//formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			Date date = (Date)formatter.parse(currDate);
			SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
			String currentYear = formatYear.format(date);
			System.out.println("year----------**********************>"+currentYear);
			
			//String seq=commonBd.getPropIdSeq();
			
			//propId=currentYear+seq;
			
			return currentYear;
		}
		
		
		//	Added for encrypting the user password before storing it into the database
	/*	public synchronized String encrypt(String plaintext) throws ClassCastException //SystemUnavailableException 
		  {
		    MessageDigest md = null;
		    try
		    {
		      md = MessageDigest.getInstance("SHA"); //step 2
		    }
		    catch(NoSuchAlgorithmException e)
		    {
		      throw new ClassCastException(e.getMessage());
		    }
		    try
		    {
		      md.update(plaintext.getBytes("UTF-8")); //step 3
		    }
		    catch(UnsupportedEncodingException e)
		    {
		      throw new ClassCastException(e.getMessage());
		    }
		    byte raw[] = md.digest(); //step 4
	    	String hash = (new BASE64Encoder()).encode(raw); //step 5
	    	return hash; //step 6
		  }*/
		/**
		 * for getting all OCCUPATION list
		 * @return ArrayList
		 */
	/*	public ArrayList getOccupationList() {
			UserRegistrationDAO uregDAO = new UserRegistrationDAO();
			return uregDAO.getOccupationList();
		}

	} */
}
