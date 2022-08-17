package com.wipro.igrs.eToken.dao;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.eToken.dto.ETokenDTO;
import com.wipro.igrs.eToken.form.ETokenForm;
import com.wipro.igrs.eToken.sql.ETokenSQL;

public class ETokenDAO {

	private final Logger logger = Logger.getLogger(ETokenDAO.class);
	DBUtility dbUtility = null;

	/**
	 * 
	 * @param language
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList<String> getDistrictList(String language) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		try {

			dbUtility = new DBUtility();
			dbUtility.createStatement();

			String query ="";
			if(language.equalsIgnoreCase("en"))
				query = ETokenSQL.GET_DISTRICT_LIST;
			else
				query = ETokenSQL.GET_DISTRICT_LIST_HINDI;
			list = dbUtility.executeQuery(query);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getDistrictList" + exception);
			exception.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	public ArrayList<String> getLock(String officeId) throws Exception
	{
		ArrayList<String> list=null;
		try {
			dbUtility = new DBUtility();


			String query ="";

			query = ETokenSQL.GET_LOCK_DETAILS;
			dbUtility.createPreparedStatement(query);
			String ar[] = {officeId};
			list = dbUtility.executeQuery(ar);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getDistrictList" + exception);
			exception.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	public boolean  updateTokenDetails(String regDetails,String counterNo,String tokenNo,String appstatus) throws Exception
	{
		ArrayList<String> list=null;
		boolean boo = false;
		String checkCounterNo = "";
		String checkQuery = "";
		try {
			dbUtility = new DBUtility();


			String query ="";
// code changed by akansha and rustam
			if(appstatus.equalsIgnoreCase("10") || appstatus.equalsIgnoreCase("11")){
			 checkQuery = ETokenSQL.check_COUNTER_Number_maker;}
			else{
				
				checkQuery = ETokenSQL.check_COUNTER_Number_checker;
			}
			String arsCheck[] = {regDetails,tokenNo};
			dbUtility.createPreparedStatement(checkQuery);
			checkCounterNo = dbUtility.executeQry(arsCheck);
			if(checkCounterNo.isEmpty()){
			
			if(appstatus.equalsIgnoreCase("10") || appstatus.equalsIgnoreCase("11"))
				query = ETokenSQL.UPDATE_MAKER_COUNTER;
			else
				query = ETokenSQL.UPDATE_CHECKER_COUNTER;
			String ars[] = {counterNo,regDetails,tokenNo};

			dbUtility.createPreparedStatement(query);
			boo = dbUtility.executeUpdate(ars);
			}
		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in updateTokenDetails" + exception);
			exception.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return boo;
	}
	public String getTokenMappingId(String tokenName,String officeId) throws Exception
	{
		//boolean flag = false;
		ArrayList list=new ArrayList();
		String mappingId = "";
		try {

			dbUtility = new DBUtility();
			String query = "";
			String arr[] = {tokenName,officeId};

			query = ETokenSQL.GET_COUNTER_MAPPING_ID;

			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);
			ArrayList li = (ArrayList) list.get(0);
			mappingId = (String) li.get(0);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in checkRegInitId " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return mappingId;
	}

	/**
	 * 
	 * @param disttId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList<String> getSROList(String disttId,String language) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = "";
			if(language.equalsIgnoreCase("en"))
				query = ETokenSQL.GET_SRO_LIST+"'"+disttId+"'";
			else
				query =ETokenSQL.GET_SRO_LIST_HINDI+"'"+disttId+"'";


			list = dbUtility.executeQuery(query);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getSROList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * 
	 * @param counterArr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertCounterDetls(String counterArr[],String userType) throws Exception
	{
		boolean flag = false;
		try {

			dbUtility = new DBUtility();
			String query = "";

			String counterCheck[] = new String[1];
			counterCheck[0] = counterArr[1];
			String firstSelect ="";

			firstSelect =  ETokenSQL.GET_COUNTER;

			String counterID= "0";
			dbUtility.createPreparedStatement(firstSelect);
			String data = dbUtility.executeQry(counterCheck);
			int counter =0;
			if(data!=null && !"".equalsIgnoreCase(data))
			{
				counter = 	Integer.parseInt(data)+1;

			}
			else
			{

				counter = 1;
			}


			String finalArr[] = {
					String.valueOf(counter),
					counterArr[0],counterArr[1],
					counterArr[2],counterArr[3],
					counterArr[4],counterArr[5],
					counterArr[6]


			};

			query = ETokenSQL.SAVE_COUNTER_DETLS;


			dbUtility.createPreparedStatement(query);
			flag = dbUtility.executeUpdate(finalArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param counterName
	 * @return
	 * @throws Exception
	 */
	public String checkUniqueName(String counterName) throws Exception{

		String check = "";
		String checkFinal = "N";
		try {
			dbUtility = new DBUtility();
			String query = ETokenSQL.UNIQUE_COUNTER_NAME_CHECK_CHECKER;
			dbUtility.createPreparedStatement(query);
			String arr[]  = {counterName};
			check = dbUtility.executeQry(arr);
			if(check.equals("0"))
			{
				query = ETokenSQL.UNIQUE_COUNTER_NAME_CHECK_MAKER;
				dbUtility.createPreparedStatement(query);
				String arr2[]  = {counterName};
				check = dbUtility.executeQry(arr2);
				if(!check.equals("0"))
				{
					checkFinal = "Y";
				}
			}
			else
			{
				checkFinal = "Y";
			}

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getSROList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return checkFinal;

	}

	/**
	 * 
	 * @param locArr
	 * @param userType
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCounterDetails(String[] locArr,String userType, String lang ) throws Exception{
		ArrayList list = new ArrayList();
		try {


			dbUtility = new DBUtility();
			String query = "";
			if(lang.equalsIgnoreCase("en"))
				query = ETokenSQL.GET_COUNTER_DETAILS;
			else
				query = ETokenSQL.GET_COUNTER_DETAILS_HINDI;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(locArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getCounterDetails" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;

	}

	/**
	 * 
	 * @param counterId
	 * @param userType
	 * @return ArrayList
	 * @throws Exception
	 */
	public String getMakerWaitTime()  {

		String getSpotMaxDay = "";
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			getSpotMaxDay=	dbUtility.executeQry( "SELECT  ATTRIBUTE_VALUE  FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID = 'ATT_170'");
		}catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getSpotMaxDay;

	}



	public String getCheckerWaitTime()  {

		String getSpotMaxDay = "";
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			getSpotMaxDay=	dbUtility.executeQry( "SELECT  ATTRIBUTE_VALUE  FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID = 'ATT_171'");
		}catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getSpotMaxDay;
	}




	public ArrayList<String> getCounterDetailsComplete(String counterId,String userType,String lang , ETokenDTO edto) throws Exception{
		ArrayList<String> list = new ArrayList<String>();
		try {

			dbUtility = new DBUtility();
			String query = "";
			
			String locArr[] = {edto.getDistrictId(),
					edto.getSroId(),counterId

			};
			
			/*if(lang.equalsIgnoreCase("en"))
				query = ETokenSQL.GET_COMPLETE_COUNTER_DETLS+"'"+counterId+"'";
			else
				query = ETokenSQL.GET_COMPLETE_COUNTER_DETLS_HINDI+"'"+counterId+"'";
*/
			
			if(lang.equalsIgnoreCase("en"))
				query = ETokenSQL.GET_COMPLETE_COUNTER_DETLS;
			else
				query = ETokenSQL.GET_COMPLETE_COUNTER_DETLS_HINDI;

			
		
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(locArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getCounterDetailsComplete" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;

	}

	public boolean updateCounterDetls(String counterArr[]) throws Exception
	{
		boolean flag = false;
		try {

			dbUtility = new DBUtility();
			String query = "";
			query = ETokenSQL.UPDATE_COUNTER_DETLS;
			dbUtility.createPreparedStatement(query);
			flag = dbUtility.executeUpdate(counterArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in updateCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;
	}


	/**
	 * 
	 * @param counterName
	 * @param counterId
	 * @return
	 * @throws Exception
	 */
	public String checkUniqueNameEdit(String counterName, String counterId) throws Exception{

		String check = "";
		String checkFinal = "N";
		try {
			dbUtility = new DBUtility();
			String query = ETokenSQL.UNIQUE_CONSTRAINT_FOR_EDIT_MAKER;
			dbUtility.createPreparedStatement(query);
			String arr[]  = {counterName,
					counterId};
			check = dbUtility.executeQry(arr);
			if(check.equals("0"))
			{
				query = ETokenSQL.UNIQUE_CONSTRAINT_FOR_EDIT_CHECKER;
				dbUtility.createPreparedStatement(query);
				String arr2[]  = {counterName,
						counterId};
				check = dbUtility.executeQry(arr2);
				if(!check.equals("0"))
				{
					checkFinal = "Y";
				}
			}
			else
			{
				checkFinal = "Y";
			}

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getSROList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return checkFinal;

	}

	/**
	 * 
	 * @param officeId
	 * @param language
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLoggedInDistrictDetls(String officeId, String language) throws Exception{
		ArrayList list=new ArrayList();
		try {
			String query="";
			dbUtility = new DBUtility();
			if(language.equalsIgnoreCase("en"))
				query = ETokenSQL.GET_OFFICE_DISTRICT_DETLS;
			else
				query = ETokenSQL.GET_OFFICE_DISTRICT_DETLS_HINDI;
			dbUtility.createPreparedStatement(query);
			String arr[] = {officeId};
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getLoggedInDistrictDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * 
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getMakerCounterDetls(String officeId) throws Exception{
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String query = ETokenSQL.GET_SRO_COUNTERS_MAKER;
			dbUtility.createPreparedStatement(query);
			String arr[] = {officeId};
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getSroCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * 
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getMappedMakerDetls(String officeId) throws Exception{
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String query = ETokenSQL.GET_MAPPED_MAKERS_LIST;
			dbUtility.createPreparedStatement(query);
			String arr[] = {officeId};
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getMappedMakerDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * 
	 * @param counterArr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertMakerCounterDetls(String counterArr[]) throws Exception
	{
		boolean flag = false;
		try {

			dbUtility = new DBUtility();
			String query = ETokenSQL.INSERT_MAPPED_MAKERS_LIST;
			dbUtility.createPreparedStatement(query);
			flag = dbUtility.executeUpdate(counterArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertMakerCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param mappingId
	 * @param userId
	 * @param tDate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateMakerCounterDetls(String mappingId,String userId, String tDate) throws Exception
	{
		boolean flag = false;
		try {

			dbUtility = new DBUtility();
			String query = ETokenSQL.UPDATE_MAPPED_MAKER_DETLS;
			dbUtility.createPreparedStatement(query);
			String arr[] ={userId,
					tDate,
					mappingId};
			flag = dbUtility.executeUpdate(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in updateMakerCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getMakerSRODetls(String officeId) throws Exception{
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String query = ETokenSQL.GET_MAKERS_MAPPED_TO_SRO;
			dbUtility.createPreparedStatement(query);
			String arr[] = {officeId};
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getMakerSRODetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	public ArrayList getCheckerCounterDetls(String officeId) throws Exception{
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String query = ETokenSQL.GET_SRO_COUNTERS_CHECKER;
			dbUtility.createPreparedStatement(query);
			String arr[] = {officeId};
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getCheckerCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * 
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getMappedCheckerDetls(String officeId) throws Exception{
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String query = ETokenSQL.GET_MAPPED_CHECKERS_LIST;
			dbUtility.createPreparedStatement(query);
			String arr[] = {officeId};
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getMappedCheckerDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * 
	 * @param counterArr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertCheckerCounterDetls(String counterArr[]) throws Exception
	{
		boolean flag = false;
		try {

			dbUtility = new DBUtility();
			String query = ETokenSQL.INSERT_MAPPED_CHECKERS_LIST;
			dbUtility.createPreparedStatement(query);
			flag = dbUtility.executeUpdate(counterArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCheckerCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param mappingId
	 * @param userId
	 * @param tDate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateCheckerCounterDetls(String mappingId,String userId, String tDate) throws Exception
	{
		boolean flag = false;
		try {

			dbUtility = new DBUtility();
			String query = ETokenSQL.UPDATE_MAPPED_CHECKERS_DETLS;
			dbUtility.createPreparedStatement(query);
			String arr[] ={userId,
					tDate,
					mappingId};
			flag = dbUtility.executeUpdate(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in updateCheckerCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCheckerSRODetls(String officeId) throws Exception{
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			String query = ETokenSQL.GET_CHECKERS_MAPPED_TO_SRO;
			dbUtility.createPreparedStatement(query);
			String arr[] = {officeId};
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getCheckerSRODetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	/**
	 * 
	 * @param officeId
	 * @param userType
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getAvailableUsersList(String officeId,String userType) throws Exception
	{
		ArrayList list = new ArrayList();
		try {

			dbUtility = new DBUtility();
			String query = "";
			String arr[] = {officeId};
			if(userType.equalsIgnoreCase("MAKER"))
			{
				query = ETokenSQL.GET_AVAILABLE_MAKERS_LIST;

			}
			else
			{
				query = ETokenSQL.GET_AVAILABLE_CHECKERS_LIST;
			}
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getAvailableMakersList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}


	/**
	 * 
	 * @param mappingId
	 * @param userType
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateAvailableUsersList(String mappingId,String status,String userType) throws Exception
	{
		boolean flag = false;
		try {

			dbUtility = new DBUtility();
			String query = "";
			String arr[] = {
					status,
					mappingId};
			if(userType.equalsIgnoreCase("MAKER"))
			{
				query = ETokenSQL.UPDATE_MAKER_AVAILABLITY_STATUS;

			}
			else
			{
				query = ETokenSQL.UPDATE_CHECKER_AVAILABLITY_STATUS;
			}
			dbUtility.createPreparedStatement(query);
			flag = dbUtility.executeUpdate(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in updateAvailableUsersList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author ROOPAM
	 */

	public String getDate()
	{
		String dates = "";
		try {

			dbUtility = new DBUtility();
			dbUtility.createStatement();
			dates = dbUtility.executeQry(ETokenSQL.get_details_date);

		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dates;

	}

	public ArrayList checkRegInitId(String officeId,String regInitId) throws Exception
	{
		//boolean flag = false;
		ArrayList list=new ArrayList();
		String deedId="";
		try {

			dbUtility = new DBUtility();
			String query = "";
			String arr1[] = {regInitId};
			String arr[] = {officeId,regInitId};
			query = ETokenSQL.CHECK_DEED_ID_forRegtxnID;
			dbUtility.createPreparedStatement(query);
			deedId = dbUtility.executeQry(arr1);
			
			
			if(deedId.equalsIgnoreCase("1094")){
				query = ETokenSQL.CHECK_REG_INIT_ID_WILL;
			}
			else
			{
				query = ETokenSQL.CHECK_REG_INIT_ID;

				
			}
			
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);	


		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in checkRegInitId " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}


	public boolean eTokenUpdate(String regId,String status)
	{
		// Run this when maker is completed for an initiation ID and has to go to checker//
		boolean check = false;

		try {

			dbUtility = new DBUtility();
			String param[] = {status,regId};
			if(status.equalsIgnoreCase("11") || status.equalsIgnoreCase("13"))
				dbUtility.createPreparedStatement("Update IGRS_ETOKEN_MAKER_DETAILS set FLAG='D',reg_status=? where reg_status=10 and FLAG='A' and reg_id = ? ");
			else if(status.equalsIgnoreCase("14") || status.equalsIgnoreCase("17"))
				dbUtility.createPreparedStatement("Update IGRS_ETOKEN_CHECKER_DETAILS set FLAG='D',reg_status=? where reg_status=13 and FLAG='A' and reg_id = ? ");
			check = dbUtility.executeUpdate(param);

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);

		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check ;


	}




	public String checkRegInitIdM(String regInitId,String officeId) throws Exception
	{
		//boolean flag = false;
		ArrayList list=new ArrayList();
		String status = "";
		
		String deedId= "";
		try {

			dbUtility = new DBUtility();
			String query = "";
			String arr[] = {officeId,regInitId};


			String arr1[] = {regInitId};
		
			query = ETokenSQL.CHECK_DEED_ID_forRegtxnID;
			dbUtility.createPreparedStatement(query);
			deedId = dbUtility.executeQry(arr1);
			
			
			if(deedId.equalsIgnoreCase("1094")){
				query = ETokenSQL.CHECK_REG_INIT_ID_WILL;
			}
			else
			{
				query = ETokenSQL.CHECK_REG_INIT_ID;

				
			}
			

			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);
			ArrayList li = (ArrayList) list.get(0);
			status = (String) li.get(1);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in checkRegInitId " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return status;
	}

	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */
	public void eTokenInfo(String etokenNo,String status,int flag,String createdBy) throws Exception
	{


		try {

			dbUtility = new DBUtility();
			String query = "";
			String flags = String.valueOf(flag);
			String arr[] = {etokenNo,status,flags,createdBy};

			query = ETokenSQL.INSERT_ETOKEN_INFO;

			dbUtility.createPreparedStatement(query);
			dbUtility.executeQuery(arr);



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in checkRegInitId " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

	}



	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public ArrayList<String> getMakerNormalQueue() throws Exception
	{
		DBUtility dbUtil = new DBUtility();
		ArrayList<String> arrlist = new ArrayList<String>();
		try {
			String sql = "select etokenno from igrs_etoken_info where flag = '0' and status='A' order by etokenno";

			dbUtil.createStatement();
			arrlist = dbUtil.executeQuery(sql);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getMakerNormalQueue " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return arrlist;


	}

	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public ArrayList<String> getMakerSpecialQueue() throws Exception
	{
		DBUtility dbUtil = new DBUtility();
		ArrayList<String> arrlist = new ArrayList<String>();
		try {
			String sql = "select etokenno from igrs_etoken_info where flag = '1' and status='A' order by etokenno";

			dbUtil.createStatement();
			arrlist = dbUtil.executeQuery(sql);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getMakerNormalQueue " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return arrlist;


	}



	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public String insertCounterCheck(String counterOfCheker) throws Exception
	{
		DBUtility dbUtil = new DBUtility();
		String i="";
		try {
			String sql = "select count(*) from igrs_etoken_details where counter=?";
			String arr[]={counterOfCheker};
			dbUtil.createPreparedStatement(sql);
			i = dbUtil.executeQry(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getMakerNormalQueue " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return i;


	}


	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */
	public void insertCounterEtoken(String etokenNo,String counter,String counterType,int flag,String status,String createdBy,String regID) throws Exception
	{


		try {

			dbUtility = new DBUtility();
			String query = "";
			String flags = String.valueOf(flag);
			String arr[] = {etokenNo,counter,counterType,flags,status,createdBy,regID};

			if(counterType.equals("Maker")){

				query = "INSERT INTO IGRS_ETOKEN_DETAILS (ETOKENNO,COUNTER,COUNTER_TYPE,FLAG,STATUS,CREATEDDATE,CREATEDBY,REG_ID)" +
				" VALUES (?,?,?,?,?,sysdate,?,?)";


				dbUtility.createPreparedStatement(query);
				dbUtility.executeUpdate(arr);
			}
			if(counterType.equals("Checker")){

				query = "INSERT INTO IGRS_ETOKEN_CHECKER_DETAILS (ETOKENNO,COUNTER,COUNTER_TYPE,FLAG,STATUS,CREATEDDATE,CREATEDBY,REG_ID)" +
				" VALUES (?,?,?,?,?,sysdate,?,?)";


				dbUtility.createPreparedStatement(query);
				dbUtility.executeUpdate(arr);
			}



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterEtoken " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

	}


	/********
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 ********/
	public ArrayList checkRegID(String regID) throws Exception
	{

		ArrayList returnArr = new ArrayList();
		try {

			dbUtility = new DBUtility();
			String query = "";
			String arr[] = {regID};

			query = "select status from igrs_etoken_details where reg_id=? order by createddate desc ";


			dbUtility.createPreparedStatement(query);
			returnArr=dbUtility.executeQuery(arr);



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterEtoken " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return returnArr;

	}



	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public List insertCounterCheckArray(String counterType) throws Exception
	{
		List counterArr = new ArrayList();
		DBUtility dbUtil = new DBUtility();
		String i="";
		try {
			//maker
			if(counterType.equals("Maker")){
				String sql = "select count(*),counter from igrs_etoken_details Group by counter";
				dbUtil.createStatement();
				counterArr = dbUtil.executeQuery(sql);
			}
			//checker
			if(counterType.equals("Checker")){
				String sql = "select count(*),counter from IGRS_ETOKEN_CHECKER_DETAILS Group by counter";
				dbUtil.createStatement();
				counterArr = dbUtil.executeQuery(sql);
			}

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getMakerNormalQueue " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return counterArr;


	}


	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public ArrayList getCounterData(String shortestCounter,String flag) throws Exception
	{
		ArrayList counterData = new ArrayList();
		DBUtility dbUtil = new DBUtility();
		String arr[] = {shortestCounter,flag};
		try {
			String sql = "SELECT * FROM(select * from igrs_etoken_details  where  counter =?  and   flag=?  order by createddate) WHERE ROWNUM <= 1";
			dbUtil.createPreparedStatement(sql);
			counterData = dbUtil.executeQuery(arr);



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getMakerNormalQueue " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return counterData;


	}

	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public ArrayList getCounterCheckerData(String shortestCounter,String flag) throws Exception
	{
		ArrayList counterData = new ArrayList();
		DBUtility dbUtil = new DBUtility();
		String arr[] = {shortestCounter,flag};
		try {
			String sql = "SELECT * FROM(select * from igrs_etoken_checker_details  where  counter =?  and   flag=?  order by createddate) WHERE ROWNUM <= 1";
			dbUtil.createPreparedStatement(sql);
			counterData = dbUtil.executeQuery(arr);




		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getMakerNormalQueue " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return counterData;


	}


	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */
	public void deleteCounterData(String etokenNo,String counter,int flag,String status,String createdBy) throws Exception
	{


		try {

			dbUtility = new DBUtility();
			String query = "";
			String flags = String.valueOf(flag);
			String arr[] = {etokenNo,counter,flags,status,createdBy};

			query = "INSERT INTO IGRS_ETOKEN_DETAILS (ETOKENNO,COUNTER,FLAG,STATUS,CREATEDDATE,CREATEDBY )" +
			" VALUES (?,?,?,?,sysdate,?)";//added by tanu


			dbUtility.createPreparedStatement(query);
			dbUtility.executeUpdate(arr);



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterEtoken " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

	}


	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */
	public void insertCounterEtokenSecond(String etokenNo,String counter,String flag,String status,String createdBy) throws Exception
	{


		try {

			dbUtility = new DBUtility();
			String query = "";
			String arr[] = {etokenNo,counter,flag,status,createdBy};

			query = "INSERT INTO IGRS_ETOKEN_DETAILS (ETOKENNO,COUNTER,FLAG,STATUS,CREATEDDATE,CREATEDBY )" +
			" VALUES (?,?,?,?,sysdate,?)";//added by tanu


			dbUtility.createPreparedStatement(query);
			dbUtility.executeUpdate(arr);



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterEtoken " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

	}



	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */
	public void insertCounterEtokenThird(String etokenNo,String counter,String flag,String status,String createdDate,String createdBy) throws Exception
	{


		try {

			dbUtility = new DBUtility();
			String query = "";
			String arr[] = {etokenNo,counter,flag,status,createdDate,createdBy};

			query = "INSERT INTO IGRS_ETOKEN_DETAILS (ETOKENNO,COUNTER,FLAG,STATUS,CREATEDDATE,CREATEDBY )" +
			" VALUES (?,?,?,?,?,?)";//added by tanu


			dbUtility.createPreparedStatement(query);
			dbUtility.executeUpdate(arr);



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterEtoken " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

	}







	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */
	public void deleteCounterEtokenThird(String shortestCounter) throws Exception
	{


		try {

			dbUtility = new DBUtility();
			String query = "";
			String arr[] = {shortestCounter};

			query = "delete from igrs_etoken_details where counter= ?";


			dbUtility.createPreparedStatement(query);
			dbUtility.executeUpdate(arr);



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in deleteCounterEtokenThird " + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

	}


	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public ArrayList distinctCounter() throws Exception
	{
		DBUtility dbUtil = new DBUtility();
		ArrayList counterArr = new ArrayList();
		try {
			String sql = "select distinct counter from igrs_etoken_details";
			dbUtil.createStatement();
			counterArr = dbUtil.executeQuery(sql);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in distinctCounter " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return counterArr;


	}

	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public ArrayList distinctCheckerCounter() throws Exception
	{
		DBUtility dbUtil = new DBUtility();
		ArrayList counterArr = new ArrayList();
		try {
			String sql = "select distinct counter from igrs_etoken_checker_details";
			dbUtil.createStatement();
			counterArr = dbUtil.executeQuery(sql);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in distinctCheckerCounter " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return counterArr;


	}


	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public ArrayList searchEtoken(String etokenNo,String officeId) throws Exception
	{
		DBUtility dbUtil = new DBUtility();
		ArrayList counterArr = new ArrayList();
		String arr[] = {etokenNo,officeId,etokenNo,officeId};
		try {
			String sql = "SELECT DISTINCT a.reg_id,a.created_date ,b.REGISTRATION_TXN_STATUS,a.counter_type"+
			" FROM IGRS_ETOKEN_MAKER_DETAILS a, igrs_reg_txn_detls b  WHERE a.reg_id= lpad(b.REG_TXN_ID,'12','0')"+
			" AND a.ETOKENNO= ? AND a.flag='A' AND a.sro_id=? UNION "+
			" SELECT a.etokenno, a.created_date , b.REGISTRATION_TXN_STATUS, a.counter_type FROM IGRS_ETOKEN_CHECKER_DETAILS a, "+
			" igrs_reg_txn_detls b WHERE a.reg_id   =lpad( b.REG_TXN_ID,'12','0') AND a.ETOKENNO = ? AND a.flag='A' AND a.sro_id=?";
			dbUtil.createPreparedStatement(sql);
			counterArr=dbUtil.executeQuery(arr);


		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in searchEToken " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return counterArr;


	}




	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 * @param language
	 * @param officeId
	 */

	public boolean cancelEToken(String regId,String remarks,String typeId, String language, String officeId) throws Exception
	{
		boolean check = false;
		String sql = "";
		DBUtility dbUtil = new DBUtility();
		String arr[] = {remarks,regId,officeId};
		try {

			sql = "update IGRS_ETOKEN_MAKER_DETAILS SET FLAG='D' , REMARKS=? , updated_date=sysdate  where REG_ID = ? and FLAG ='A' and sro_id=?";
			dbUtil.createPreparedStatement(sql);
			check  = 	dbUtil.executeUpdate(arr);
			if(!check)
			{
				sql = "update IGRS_ETOKEN_CHECKER_DETAILS SET FLAG='D' , REMARKS=? , updated_date=sysdate  where REG_ID = ? and FLAG ='A' and sro_id=?";
				dbUtil.createPreparedStatement(sql);
				check  = 	dbUtil.executeUpdate(arr);
			}
		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in cancelEToken " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return check;




	}


	public ArrayList counterPerDay(String counterType, String officeID) throws Exception
	{
		ArrayList countArr = new ArrayList();
		DBUtility dbUtil = new DBUtility();		try {

			//maker
			if(counterType.equals("Maker")){
				String sql = "SELECT COUNT(*) FROM igrs_MAKER_counter_mapping where MAKER_AVAILABLE_STATUS='Y' And sro_id='"+officeID+"'";
				dbUtil.createStatement();
				countArr = dbUtil.executeQuery(sql);
			}

			//checker
			if(counterType.equals("Checker")){
				String sql = "SELECT COUNT(*) FROM igrs_CHECKER_counter_mapping WHERE CHECKER_AVAILABLE_STATUS='Y' AND sro_id='"+officeID+"'";
				dbUtil.createStatement();
				countArr = dbUtil.executeQuery(sql);
			}



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in cancelEToken " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return countArr;




	}

	/**
	 * 
	 * @param
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 * @param mo
	 */

	public ArrayList todayReportEtoken(String toDate,String fromDate,String off, String[] mo) throws Exception
	{
		DBUtility dbUtil = new DBUtility();
		ArrayList makerDetails = new ArrayList();
		ArrayList checkerDetails = new ArrayList();
		ArrayList makerCheckerDetails = new ArrayList();
		String arr[] = {off,fromDate,toDate};
		String sql ="";
		try {
			if(mo.length>1)
			{
				sql = ETokenSQL.GET_ETOKEN_REPORT_DETAILS;
				dbUtil.createPreparedStatement(sql);
				String arr1[] = {off,fromDate,toDate,off,fromDate,toDate};
				makerDetails=dbUtil.executeQuery(arr1);
			}
			else
			{
				if(mo[0].equalsIgnoreCase("M")){

					sql = ETokenSQL.GET_ETOKEN_REPORT_MAKER;
					String arr1[] = {off,fromDate,toDate};
					dbUtil.createPreparedStatement(sql);
					makerDetails=dbUtil.executeQuery(arr1);

				}
				else
				{
					sql = ETokenSQL.GET_ETOKEN_REPORT_CHECKER;
					String arr1[] = {off,fromDate,toDate};
					dbUtil.createPreparedStatement(sql);
					makerDetails=dbUtil.executeQuery(arr1);
				}
			}
			//String sql = "select ETOKENNO ,COUNTER ,REG_ID from igrs_etoken_details where trunc(CREATEDDATE) = trunc(sysdate)";





			/*
			makerDetails.addAll(checkerDetails);

			makerCheckerDetails = makerDetails;*/



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in todayReportEtoken " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return makerDetails;


	}


	public String ReportEtoken(String toDate,String fromDate,String off,String id, String language) throws Exception
	{
		DBUtility dbUtil = new DBUtility();
		String noOftokens=null;

		String sql ="";
		try {

			if("1".equalsIgnoreCase(id))
			{
				String arr[] = {fromDate,toDate,off,fromDate,toDate,off,fromDate,toDate,off};
				sql = ETokenSQL.GET_NO_OF_TOKENS;
				dbUtil.createPreparedStatement(sql);
				String ans =  dbUtil.executeQry(arr);
				if(ans!=null && !ans.equalsIgnoreCase("0"))
				{
					if(language.equalsIgnoreCase("en"))
						noOftokens = "Number  of tokens issued : "+ans;
					else
						noOftokens = "जारी किए गए टोकनों की संख्या : "+ans;
				}
			}
			else if("2".equalsIgnoreCase(id))
			{
				String arr[] = {fromDate,toDate,off};
				sql = ETokenSQL.GET_NO_OF_TOKENS_COMPLETED;
				dbUtil.createPreparedStatement(sql);
				String ans =  dbUtil.executeQry(arr);
				if(ans!=null && !ans.equalsIgnoreCase("0"))
				{
					if(language.equalsIgnoreCase("en"))
						noOftokens = "Number of tokens Completed in the given  period : "+ans;
					else
						noOftokens = "पूरी किए गए टोकनों की संख्या : "+ans;

				}

			}
			else if("3".equalsIgnoreCase(id))
			{
				String arr[] = {fromDate,toDate,off};
				sql = ETokenSQL.GET_NO_OF_TOKENS_HOLD_M;
				dbUtil.createPreparedStatement(sql);
				String ans =  dbUtil.executeQry(arr);
				if(ans!=null && !ans.equalsIgnoreCase("0")){
					if(language.equalsIgnoreCase("en"))
						noOftokens = "Number of tokens Hold at Maker : "+ans;
					else
						noOftokens = "निर्माता पर रुको गए टोकनों की संख्या : "+ans;
				}
			}
			else if("4".equalsIgnoreCase(id))
			{
				String arr[] = {fromDate,toDate,off};
				sql = ETokenSQL.GET_NO_OF_TOKENS_HOLD_C;
				dbUtil.createPreparedStatement(sql);
				String ans =  dbUtil.executeQry(arr);
				if(ans!=null && !ans.equalsIgnoreCase("0"))
				{
					if(language.equalsIgnoreCase("en"))
						noOftokens = "No of tokens Hold at Checker : "+ans;
					else
						noOftokens = "चेकर पर रुको गए टोकनों की संख्या  : "+ans;

				}
			}
			//String sql = "select ETOKENNO ,COUNTER ,REG_ID from igrs_etoken_details where trunc(CREATEDDATE) = trunc(sysdate)";





			/*
			makerDetails.addAll(checkerDetails);

			makerCheckerDetails = makerDetails;*/



		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in todayReportEtoken " + exception);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}

		return noOftokens;


	}

	public ArrayList getCounterID(int i, String officeID) {

		String param[] = new String[2];
		param[0] = String.valueOf(i);
		param[1] = officeID;
		ArrayList countermappings = null;
		try {
			dbUtility = new DBUtility();
			String sql = ETokenSQL.GET_MAKER_COUNTER_ACTIVE_OFFICE;
			dbUtility.createPreparedStatement(sql);
			countermappings = dbUtility.executeQuery(param);


		} catch (Exception e) {

			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		return null;
	}

	public ArrayList getCounterIDS(String officeID) {

		String param[] = new String[1];
		param[0] = officeID;
		ArrayList countermappings = null;
		try {
			dbUtility = new DBUtility();
			String sql = ETokenSQL.GET_ACTIVE_MAKER_COUNTERS;
			dbUtility.createPreparedStatement(sql);
			countermappings = dbUtility.executeQuery(param);
			System.out.println();

		} catch (Exception e) {

			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return countermappings;
	}

	public ArrayList getActiveCountersMaker(String officeID) {

		String param[] = new String[1];
		param[0] = officeID;
		ArrayList countermappings = null;
		try {
			dbUtility = new DBUtility();
			String sql = ETokenSQL.GET_MAKER_COUNTERS;
			dbUtility.createPreparedStatement(sql);
			countermappings = dbUtility.executeQuery(param);
			System.out.println();

		} catch (Exception e) {

			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return countermappings;
	}




	public ArrayList getCounterIDSChecker(String officeID) {

		String param[] = new String[1];
		param[0] = officeID;
		ArrayList countermappings = null;
		try {
			dbUtility = new DBUtility();
			String sql = ETokenSQL.GET_ACTIVE_CHECKER_COUNTERS;
			dbUtility.createPreparedStatement(sql);
			countermappings = dbUtility.executeQuery(param);


		} catch (Exception e) {

			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		return countermappings;
	}


	public String getEtokenNum() {

		String etokenNo = "0";

		try {
			DBUtility dbUtility = new DBUtility();
			String sql =ETokenSQL.GET_ETOKEN_NUMBER;
			dbUtility.createStatement();


			String number = dbUtility.executeQry(sql);


			Date date = new Date();
			Format yearformat = new SimpleDateFormat("yyyy");
			Format monthformat = new SimpleDateFormat("MM");
			Format dateformat = new SimpleDateFormat("dd");
			String dfmt = dateformat.format(date);
			String yfmt = yearformat.format(date);
			String mfmt = monthformat.format(date);

			if(number.length()==1){
				number="00"+number;
			}else
				if(number.length()==2){
					number="0"+number;
				}

			etokenNo =   number;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(), e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return etokenNo;
	}

	public String getEtokenNum(String officeId) {

		String etokenNo = "0";

		try {
			DBUtility dbUtility = new DBUtility();
			String arr[] = new String[2];
			arr[0] = officeId;
			arr[1] = officeId;
			String sql =ETokenSQL.GET_ETOKEN_NUMBER_OFFICE;
			dbUtility.createPreparedStatement(sql);


			String number = dbUtility.executeQry(arr);

			if(number==null || number.equalsIgnoreCase(""))
			{
				number="1";


			}
			else
			{

				int i = Integer.parseInt(number);
				i = i+1;
				number = String.valueOf(i);
			}
			Date date = new Date();
			Format yearformat = new SimpleDateFormat("yyyy");
			Format monthformat = new SimpleDateFormat("MM");
			Format dateformat = new SimpleDateFormat("dd");
			String dfmt = dateformat.format(date);
			String yfmt = yearformat.format(date);
			String mfmt = monthformat.format(date);

			if(number.length()==1){
				number="00"+number;
			}else
				if(number.length()==2){
					number="0"+number;
				}

			etokenNo =   number;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(), e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return etokenNo;
	}



	public boolean insertETokenDetails(ETokenForm eForm, String counterType, String catagory, String userId, String MakerOrChecker, String checkRegIDM, String officID) {

		try {

			String arr[] = {eForm.getEtokenDTO().getEtokenNumberC(),counterType,String.valueOf(eForm.getEtokenDTO().getNoPersons()),catagory,userId,eForm.getEtokenDTO().getRegInitId(),checkRegIDM,officID};

			dbUtility = new DBUtility();
			if(MakerOrChecker.equalsIgnoreCase("M"))
				dbUtility.createPreparedStatement(ETokenSQL.INSERT_MAKER_ETOKEN_DETAILS);
			else
				dbUtility.createPreparedStatement(ETokenSQL.INSERT_CHECKER_ETOKEN_DETAILS);
			dbUtility.executeUpdate(arr);

			return true;

		} catch (Exception e) {

			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;


	}

	public boolean insertETokenDetailsS(String eTokenNo,String noOfPersons,String regId, String counterType, String catagory, String userId, String MakerOrChecker, String checkRegIDM, String officID) {

		try {

			String arr[] = {eTokenNo,counterType,noOfPersons,catagory,userId,regId,checkRegIDM,officID};

			dbUtility = new DBUtility();
			if(MakerOrChecker.equalsIgnoreCase("M"))
				dbUtility.createPreparedStatement(ETokenSQL.INSERT_MAKER_ETOKEN_DETAILS);
			else
				dbUtility.createPreparedStatement(ETokenSQL.INSERT_CHECKER_ETOKEN_DETAILS);
			dbUtility.executeUpdate(arr);

			return true;

		} catch (Exception e) {

			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;


	}

	public boolean checkAlreadyToken(ETokenDTO etokenDTO, String counterType) {
		ArrayList check = null;
		boolean checks = false;
		try {


			String sql="";
			if(counterType.trim().equalsIgnoreCase("Maker") || counterType.trim().equalsIgnoreCase("Maker-Hold") )
				sql = "select * from IGRS_ETOKEN_MAKER_DETAILS where reg_id=? and flag = 'A'";
			else if(counterType.trim().equalsIgnoreCase("Checker") || counterType.trim().equalsIgnoreCase("Checker-Hold") )

				sql = "select * from IGRS_ETOKEN_CHECKER_DETAILS where reg_id=? and flag = 'A'";

			dbUtility = new DBUtility();
			String param[] = {etokenDTO.getRegInitId()};

			dbUtility.createPreparedStatement(sql);
			check  = dbUtility.executeQuery(param);
			if(check == null || check.size()<=0)
			{
				checks = true;
			}
			else
			{
				checks = false;
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return checks;
	}

	public boolean checkAlreadyToken(String regId, String counterType) {
		ArrayList check = null;
		boolean checks = false;
		try {


			String sql="";
			if(counterType.trim().equalsIgnoreCase("For Maker") || counterType.trim().equalsIgnoreCase("On Maker-Hold") )
				sql = "select reg_id from IGRS_ETOKEN_MAKER_DETAILS where reg_id=? and flag = 'A' union all select reg_id from IGRS_ETOKEN_CHECKER_DETAILS where reg_id=? and flag = 'A'";
			else if(counterType.trim().equalsIgnoreCase("For Checker") || counterType.trim().equalsIgnoreCase("On Checker-Hold") )

				sql = "select reg_id from IGRS_ETOKEN_MAKER_DETAILS where reg_id=? and flag = 'A' union all select reg_id from IGRS_ETOKEN_CHECKER_DETAILS where reg_id=? and flag = 'A'";

			dbUtility = new DBUtility();
			String param[] = {regId,regId};

			dbUtility.createPreparedStatement(sql);
			check  = dbUtility.executeQuery(param);
			if(check == null || check.size()<=0)
			{
				checks = true;
			}
			else
			{
				checks = false;
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return checks;
	}


	public ArrayList checkAlreadyTokenExists(String regId, String counterType) {
		ArrayList check = null;

		try {


			String sql="";




			sql = ETokenSQL.IGRS_RESISSUE_ETOKEN;

			dbUtility = new DBUtility();
			String param[] = {regId,regId};

			dbUtility.createPreparedStatement(sql);
			check  = dbUtility.executeQuery(param);


		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return check;
	}

	public String getOfficeName(String officID) {

		String officeName = "";
		try {

			String param[] = new String[1];
			param[0] = officID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement("select H_office_name from igrs_office_master where office_id=?");
			officeName	=  dbUtility.executeQry(param);


		} catch (Exception e) {

			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return officeName;
	}
	
	
	public ArrayList getOfficeId(String reg_id) {

		ArrayList counterArr = new ArrayList();
		String arr[] = {reg_id};
		try {

			
			//param[0] = reg_id;
			dbUtility = new DBUtility();
			String sql="select SRO_ID,ETokenNo from IGRS_ETOKEN_MAKER_DETAILS where reg_id=? and rownum < 2";
			dbUtility.createPreparedStatement(sql);
			counterArr	=  dbUtility.executeQuery(arr);


		} catch (Exception e) {

			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return counterArr;
	}

	public com.wipro.igrs.device.applet.ETokenDTO getInfo(String regId, String status, HttpServletRequest request) {
		String param[] = new String[1];
		param[0] = regId;
		com.wipro.igrs.device.applet.ETokenDTO dto = null;
		try {

			dbUtility = new DBUtility();

			if(status.equalsIgnoreCase("11")|| status.equalsIgnoreCase("13"))
				dbUtility.createPreparedStatement(ETokenSQL.GET_REG_STATUS_ETOKEN_CHECK_M);
			else
				dbUtility.createPreparedStatement(ETokenSQL.GET_REG_STATUS_ETOKEN_CHECK_C);



			ArrayList list = dbUtility.executeQuery(param);

			if(status.equalsIgnoreCase("7"))
			{
				dto  = new com.wipro.igrs.device.applet.ETokenDTO();
				if(!list.isEmpty())
				{

					ArrayList yu = (ArrayList) list.get(0);

					dto.setTypeOfPerson((String)(yu.get(3)));

				}

				dto.setRegistrationId(regId);
				dto.setStatus(status);

				return dto;

			}

			for(int j=0;j<list.size();j++)
			{
				System.out.println("in");
				dto  = new com.wipro.igrs.device.applet.ETokenDTO();
				ArrayList yu = (ArrayList) list.get(j) ;
				String reg_id = (String)yu.get(0);
				char c[] = 	reg_id.toCharArray();
				if(c.length<12)
					reg_id = "0"+reg_id;
				dto.setRegistrationId(reg_id);
				dto.setStatus(status);
				System.out.println((String)(yu.get(2)));
				dto.setOfficeId((String)(yu.get(2)));
				dto.setTypeOfPerson((String)(yu.get(3)));
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;



	}

	public boolean setOfficeIp(String officeIds, String ip) {
		boolean check = false;
		String param[] = new String[2];
		param[1] = officeIds;
		param[0] = ip;
		try {

			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement("UPDATE IGRS_OFFICE_MASTER SET OFFICE_IP_ADDRESS = ? WHERE office_id =?");
			check = dbUtility.executeUpdate(param);


		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return check;
	}

	public static void main(String[] args) {
		ETokenDAO dao = new ETokenDAO();
		System.out.println(dao.getEtokenNum("OFC01"));
	}

	public boolean moveToChecker(String regId, String status) {
		boolean flag = false;
		try {
			String counterArr[] = new String[2];
			counterArr[0] = status ;
			counterArr[1] = regId        ;

			dbUtility = new DBUtility();
			String query =ETokenSQL.MOVE_TO_CHECKER;



			dbUtility.createPreparedStatement(query);
			flag = dbUtility.executeUpdate(counterArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;

	}


	public boolean getOffice(String office_id, String ip) {
		boolean flag = false;
		try {
			String counterArr[] = new String[2];
			counterArr[0] = ip;
			counterArr[1] = office_id ;


			dbUtility = new DBUtility();
			String query =ETokenSQL.UPDATE_OFFICE_MASTER;



			dbUtility.createPreparedStatement(query);
			flag = dbUtility.executeUpdate(counterArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;

	}

	public String getOfficeIp(String office_id)
	{
		String ip = null;
		try {
			String counterArr[] = new String[1];
			counterArr[0] = office_id;



			dbUtility = new DBUtility();
			String query =ETokenSQL.GET_OFFICE_IP;



			dbUtility.createPreparedStatement(query);
			ip =  dbUtility.executeQry(counterArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}


		return ip;

	}

	public boolean setMakerHoldDeactive(String regId, String status) {
		boolean flag = false;
		try {
			String counterArr[] = new String[2];
			counterArr[1] =regId;
			counterArr[0] = status ;


			dbUtility = new DBUtility();
			String query =ETokenSQL.UPDATE_MAKER_TABLE;



			dbUtility.createPreparedStatement(query);
			flag = dbUtility.executeUpdate(counterArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;

	}

	public boolean setCheckerHoldDeactivate(String regId, String status) {
		boolean flag = false;
		try {
			String counterArr[] = new String[2];
			counterArr[1] =regId;
			counterArr[0] = status ;


			dbUtility = new DBUtility();
			String query =ETokenSQL.UPDATE_CHECKER_TABLE;



			dbUtility.createPreparedStatement(query);
			flag = dbUtility.executeUpdate(counterArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in insertCounterDetls" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;

	}

	public boolean getRegCheck(String regID) {

		boolean flag = false;
		try {
			String counterArr[] = new String[2];
			counterArr[0] =regID;
			counterArr[1] =regID;


			dbUtility = new DBUtility();
			String query =ETokenSQL.UPDATE_CHECKER_TABLE_get;



			dbUtility.createPreparedStatement(query);
			ArrayList list  = dbUtility.executeQuery(counterArr);
			if(list.size()>0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getCheckdetails" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return flag;
	}

	public ArrayList getEtokenUpdateDto(String regID, String officeId) {
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();

			String ar[] = {regID,officeId,regID,officeId};
			String sql = ETokenSQL.GET_UPDATE_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(ar);

		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean  deleteExixstingToken(String regID,String MakerOrChecker) {
		boolean check = false;
		try {
			String[] arr= new String[]{regID};
			dbUtility = new DBUtility();
			if(MakerOrChecker.equalsIgnoreCase("M"))
				dbUtility.createPreparedStatement(ETokenSQL.DELETE_OLD_MAKER);
			else
				dbUtility.createPreparedStatement(ETokenSQL.DELETE_OLD_CHECKER);
			check = dbUtility.executeUpdate(arr);


		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;

	}

	public ArrayList timeTakenReprtEtoken(String toDate, String fromDate,
			String off, String id) {
		ArrayList finalList = null;
		DBUtility dbutil = null ;

		try {

			dbutil = new DBUtility();
			String arr[];
			arr = new String[1];
			arr[0] = off;
			String sql = ETokenSQL.GET_REG_NUMBERS_MAKER;
			dbutil.createPreparedStatement(sql);
			ArrayList list = dbutil.executeQuery(arr);
			if(list!=null && list.size()>0)
			{
				String regNumbers[] = new String[list.size()] ;
				for(int i =0;i<list.size();i++)
				{
					ArrayList l =(ArrayList) list.get(i);
					regNumbers[i] = (String)l.get(0);

				}

				ArrayList<String> numbers = new ArrayList<String>();
				for(int i=0;i<regNumbers.length;i++)
				{
					String arr1[]= {regNumbers[i],regNumbers[i],fromDate,toDate};

					dbutil.createPreparedStatement(ETokenSQL.GET_DATE_CHECK_MAKER);
					String number = dbutil.executeQry(arr1);
					if(!number.equalsIgnoreCase("0"))
					{
						numbers.add(regNumbers[i]);
					}
				}

				if(numbers.size()>0)
				{
					finalList = new ArrayList<ETokenDTO>();
					for(int i=0;i<numbers.size();i++)
					{
						ETokenDTO dto = new ETokenDTO();
						String arr2[]={numbers.get(i)};
						dbutil.createPreparedStatement(ETokenSQL.GET_TIME_MAKER);
						ArrayList de = dbutil.executeQuery(arr2);
						ArrayList li = (ArrayList) de.get(0);
						dto.setRegID((String)li.get(0));
						dto.setTimeTaken((String)li.get(1));
						finalList.add(dto);
					}


				}
				else
				{
					return null;
				}


			}
			else
			{
				return null;
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbutil.closeConnection();
			} catch (Exception e) {

				logger.debug(e.getMessage(),e);
			}
		}

		return finalList;
	}

	public ArrayList timeTakenReprtEtokenC(String toDate, String fromDate,
			String off, String id) {
		ArrayList finalList = null;
		DBUtility dbutil = null ;

		try {

			dbutil = new DBUtility();
			String arr[];
			arr = new String[1];
			arr[0] = off;
			String sql = ETokenSQL.GET_REG_NUMBERS_CHECKER;
			dbutil.createPreparedStatement(sql);
			ArrayList list = dbutil.executeQuery(arr);
			if(list!=null && list.size()>0)
			{
				String regNumbers[] = new String[list.size()] ;
				for(int i =0;i<list.size();i++)
				{
					ArrayList l =(ArrayList) list.get(i);
					regNumbers[i] = (String)l.get(0);

				}

				ArrayList<String> numbers = new ArrayList<String>();
				for(int i=0;i<regNumbers.length;i++)
				{
					String arr1[]= {regNumbers[i],regNumbers[i],fromDate,toDate};

					dbutil.createPreparedStatement(ETokenSQL.GET_DATE_CHECK_CHECKER);
					String number = dbutil.executeQry(arr1);
					if(!number.equalsIgnoreCase("0"))
					{
						numbers.add(regNumbers[i]);
					}
				}

				if(numbers.size()>0)
				{
					finalList = new ArrayList<ETokenDTO>();
					for(int i=0;i<numbers.size();i++)
					{
						ETokenDTO dto = new ETokenDTO();
						String arr2[]={numbers.get(i)};
						dbutil.createPreparedStatement(ETokenSQL.GET_TIME_CHECKER);
						ArrayList de = dbutil.executeQuery(arr2);
						ArrayList li = (ArrayList) de.get(0);
						dto.setRegID((String)li.get(0));
						dto.setTimeTaken((String)li.get(1));
						finalList.add(dto);
					}


				}
				else
				{
					return null;
				}


			}
			else
			{
				return null;
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbutil.closeConnection();
			} catch (Exception e) {

				logger.debug(e.getMessage(),e);
			}
		}

		return finalList;
	}

	public String getMakerCollectionCounterDetailsDao(String regId) {
		DBUtility dbUtility = null ;

		String counterNo="";
		String mappingId="";
		try{
			dbUtility = new DBUtility();
			//	String arr[] = {regId,regId};
			String query=ETokenSQL.GET_REG_COUNTER_NO;
			dbUtility.createPreparedStatement(query);
			mappingId = dbUtility.executeQry(new String[]{regId,regId});
			String sql = ETokenSQL.GET_REG_COUNTER_NAME;
			dbUtility.createPreparedStatement(sql);
			counterNo = dbUtility.executeQry(new String[]{mappingId});

		}
		catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {

				logger.debug(e.getMessage(),e);
			}
		}
		return counterNo;
	}
	public String getCounterName(String counterNo) {
		DBUtility dbUtility = null ;

		String counterName="";
		String mappingId="";
		try{
			dbUtility = new DBUtility();
			//	String arr[] = {regId,regId};
			String sql = ETokenSQL.GET_REG_COUNTER_NAME;
			dbUtility.createPreparedStatement(sql);
			counterName = dbUtility.executeQry(new String[]{counterNo});

		}
		catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {

				logger.debug(e.getMessage(),e);
			}
		}
		return counterName;
	}
	
	public ArrayList<String> getEtokenActiveCounterList(String language,String officeId) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> list1=new ArrayList<String>();
		try {

			dbUtility = new DBUtility();
			dbUtility.createStatement();


			String temp[] = new String[1];
			temp[0]=officeId;
			//temp[1]=SROId;
			String query="",query1 ="";
			
				query = ETokenSQL.GET_ETOKEN_ACTIVE_MAKER_COUNTER_LIST;
				
				
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(temp);
				query1=ETokenSQL.GET_ETOKEN_ACTIVE_CHECKER_COUNTER_LIST;
				dbUtility.createPreparedStatement(query1);
				list1 = dbUtility.executeQuery(temp);
				if(list1.size()>=1 && list.size()>=1){
					
				}
				else
					list.clear();

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getDistrictList" + exception);
			exception.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}
	public ArrayList<String> getEtokenDistrictList(String language,String officeId) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		try {

			dbUtility = new DBUtility();
			dbUtility.createStatement();


			String temp[] = new String[1];
			temp[0]=officeId;
			//temp[1]=SROId;
			String query ="";
			if(language.equalsIgnoreCase("en"))
				query = ETokenSQL.GET_ETOKEN_DISTRICT_LIST;
			else
				query = ETokenSQL.GET_ETOKEN_DISTRICT_LIST_Hindi;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(temp);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getDistrictList" + exception);
			exception.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in ETokenDAO" + e.getStackTrace());
			}
		}
		return list;
	}

	//counterData

	public ArrayList getCounterDataList(String language,String districtId,String SROId) throws Exception
	{
		ArrayList details = new ArrayList();

		DBUtility dbUtility = null;
		try
		{

			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql ="";
			if(language.equalsIgnoreCase("en"))
				sql =ETokenSQL.GET_MAPPED_COUNTER_LIST;
			else
				sql =ETokenSQL.GET_MAPPED_COUNTER_LIST_HINDI;

			String temp[] = new String[2];
			temp[0]=districtId;
			temp[1]=SROId;
			dbUtility.createPreparedStatement(sql);

			details=dbUtility.executeQuery(temp);


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}
	//Attendence Data
	public ArrayList getAttendenceDataList(String language,String districtId,String SROId) throws Exception{
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{

			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =ETokenSQL.GET_ATTENDENCE_MASTER_LIST;
			String temp[] = new String[2];
			temp[0]=districtId;
			temp[1]=SROId;

			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);



		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList<String> getEtokenSROList(String disttId,String language) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String query = "";
			if(language.equalsIgnoreCase("en"))
				query = ETokenSQL.GET_ETOKEN_SRO_LIST+"'"+disttId+"'";
			else
				query =ETokenSQL.GET_ETOKEN_SRO_LIST_HINDI+"'"+disttId+"'";;


				list = dbUtility.executeQuery(query);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getEtokenSROList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in getEtokenSROList" + e.getStackTrace());
			}
		}
		return list;
	}
	public ArrayList<String> getUserCounterMappingList(String[] locArr,String UserType,String lang) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		try {
			dbUtility = new DBUtility();
			//	arr = new String[2];
			//	arr[0] = districtId;
			//	arr[1] = office;
			//dbUtility.createStatement();
			String query = "";
			System.out.println("UserType "+UserType);
			if(lang.equalsIgnoreCase("en"))
			{
				if(UserType.equalsIgnoreCase("MAKER"))
					query = ETokenSQL.GET_USERID_COUNTER__MAKER_LIST_NEW;
				else
					query = ETokenSQL.GET_USERID_COUNTER__CHECKER_LIST;

			}
			else{
				if(UserType.equalsIgnoreCase("MAKER"))
					query =ETokenSQL.GET_USERID_COUNTER__MAKER_LIST_NEW;
				else
					query =ETokenSQL.GET_USERID_COUNTER__CHECKER_LIST;
			}

			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(locArr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getEtokenSROList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in getEtokenSROList" + e.getStackTrace());
			}
		}
		return list;
	}
	/*	public ArrayList<String> getUserCounterMappingList(String counterUserId)throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		try {
			dbUtility = new DBUtility();
			String arr[] = {counterUserId};
			String query = "";
			query = ETokenSQL.GET_COUNTER_TYPE;
		dbUtility.createPreparedStatement(query);
		 list = dbUtility.executeQuery(arr);

	} catch (Exception exception) {
		logger.debug("Exception ETokenDAO: in getEtokenSROList" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("Error in getEtokenSROList" + e.getStackTrace());
		}
	}
	return list;
	}*/

	public ArrayList<String> getcounterOfficeMappingList(String office,String userId) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		try {
			dbUtility = new DBUtility();
			String arr[] = {office,userId.toUpperCase()};
			//	arr = new String[2];
			//	arr[0] = districtId;
			//	arr[1] = office;
			//dbUtility.createStatement();
			String query = "";
			query = ETokenSQL.GET_COUNTER_OFFICE_MAPPING_LIST;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getEtokenSROList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in getEtokenSROList" + e.getStackTrace());
			}
		}
		return list;
	}

	public ArrayList<String> getcounterOfficeMappingListInsert(String office) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		try {
			dbUtility = new DBUtility();
			String arr[] = {office};
			//	arr = new String[2];
			//	arr[0] = districtId;
			//	arr[1] = office;
			//dbUtility.createStatement();
			String query = "";
			query = ETokenSQL.GET_COUNTER_OFFICE_MAPPING_LIST;
			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getEtokenSROList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in getEtokenSROList" + e.getStackTrace());
			}
		}
		return list;
	}

	public boolean insertAttendenceData(ETokenForm form, String[] checkBoxSize,
			String districtId, String SROId, String userId, String lang)
	throws Exception {
		DBUtility dbUtility = null;
		boolean flag = false;
		boolean flag2 = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String awr[] = form.getCheckBoxFlag().split(",");
			for (int i = 0; i < checkBoxSize.length; i++) {
				String sql1 = ETokenSQL.INSERT_ATTENDENCE_MASTER_DATA;
				dbUtility.createPreparedStatement(sql1);
				String temp[] = new String[4];
				String checkData[] = awr[i].split("#");
				temp[0] = checkData[3];
				temp[1] = districtId;
				temp[2] = SROId;
				temp[3] = userId;
				flag = dbUtility.executeUpdate(temp);
			}
			if (flag) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public boolean checkAttendenceData(ETokenForm form, String[] checkBoxSize,
			String districtId, String SROId, String userId, String lang)
	throws Exception {
		DBUtility dbUtility = null;
		boolean flag = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String awr[] = form.getCheckBoxFlag().split(",");
			for (int i = 0; i < checkBoxSize.length; i++) {
				String sql1 = ETokenSQL.SELECT_ATTENDENCE_MASTER_DATA;
				dbUtility.createPreparedStatement(sql1);
				String temp[] = new String[2];
				String checkData[] = awr[i].split("#");
				//temp[0] = checkData[3];
				temp[0] = districtId;
				temp[1] = SROId;
				String count = dbUtility.executeQry(temp);
				if(Integer.parseInt(count)>0)
					flag=true;
				else
					flag=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return flag;
	}


	public boolean updateAttendenceData(ETokenForm form,
			String[] unCheckBoxSize, String districtId, String SROId,
			String userId, String lang) throws Exception {
		DBUtility dbUtility = null;
		boolean flag = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String awr[] = form.getUncheckBoxFlag().split(",");
			for (int i = 0; i < unCheckBoxSize.length; i++) {
				String sql1 = ETokenSQL.UPDATE_ATTENDENCE_MASTER_DATA;
				dbUtility.createPreparedStatement(sql1);
				String temp[] = new String[4];
				String uncheckData[] = awr[i].split("#");
				temp[0] = userId;
				temp[1] = uncheckData[3];
				temp[2] = districtId;
				temp[3] = SROId;
				flag = dbUtility.executeUpdate(temp);
			}
			if (flag) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public ArrayList<String> getcounterType(String counterUserId,String DistrictId,String SroId,String lang) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		try {
			dbUtility = new DBUtility();
			String arr[] = {DistrictId,SroId,counterUserId};
			//	arr = new String[2];
			//	arr[0] = districtId;
			//	arr[1] = office;
			//dbUtility.createStatement();
			String query = "";
			if(lang.equalsIgnoreCase("en"))
				query = ETokenSQL.GET_USERID_COUNTER_TYPE;
			else
				query =ETokenSQL.GET_USERID_COUNTER_TYPE_HINDI;

			dbUtility.createPreparedStatement(query);
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getEtokenSROList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in getEtokenSROList" + e.getStackTrace());
			}
		}
		return list;
	}

	public String getDeedIDforRegtxn(String regInitId) {
		// TODO Auto-generated method stub
		String deedId="";
		try {
		dbUtility = new DBUtility();
		String query = "";
		String arr[] = {regInitId};
		query = ETokenSQL.CHECK_DEED_ID_forRegtxnID;
		dbUtility.createPreparedStatement(query);
		deedId = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception ETokenDAO: in getEtokenSROList" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("Error in getEtokenSROList" + e.getStackTrace());
			}
		}
		return deedId;
		
	}


}
