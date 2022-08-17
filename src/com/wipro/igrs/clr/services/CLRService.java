package com.wipro.igrs.clr.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.wipro.igrs.db.DBUtility;

public class CLRService {

	private static Logger logger = (Logger) Logger.getLogger(CLRService.class);
	public static ArrayList<String> ifCLR(String regTxnId) throws Exception {
		ArrayList<String> list = null;
		String sql = CLRServicesSQL.CLR_PROPERTIES_ID;
		String arr[] = {regTxnId};
		list = CLRServiceDAO.getValAsArrayListAsString(sql, arr, "CLRService-ifCLR");
		return list;
	}
	public static ArrayList propDetails(String regTxnId, String propertyId) throws Exception {
		ArrayList list = new ArrayList();
		String sql = CLRServicesSQL.CLR_PROP_DETAILS;
		String arr[] = {regTxnId, propertyId};
		list = CLRServiceDAO.getValAsArrayList(sql, arr, "CLRService-propDetails");
		return list;
	}
	public static ArrayList partyDetails(String regTxnId, String propertyId) throws Exception {
		ArrayList list = new ArrayList();
		String sql = CLRServicesSQL.CLR_PARTY_DETAILS;
		String arr[] = {regTxnId, propertyId};
		list = CLRServiceDAO.getValAsArrayList(sql, arr, "CLRService-partyDetails");
		return list;
	}
	public static boolean insertAllData(ArrayList propData, ArrayList partyData, String regTxnId, String propertyId) throws Exception {
		boolean response = false;
		String sql = CLRServicesSQL.CHECK_CLR_DATA;

		String param[] = {regTxnId, propertyId}; // variable will not change for checking
		// data in tables
		boolean result = false;
		String cnt = CLRServiceDAO.checkifAvailable(sql, param, "CLRServiceDAO-CHECK_CLR_DATA");
		if ("Y".equalsIgnoreCase(cnt)) {
			sql = CLRServicesSQL.INSERT_CLR_STATUS;
			String arr[] = {regTxnId, regTxnId, propertyId, "0"};
			result = CLRServiceDAO.insertData(sql, arr, "insertAllData-INSERT_CLR_STATUS");
		} else {
			result = true;
			System.out.println("Data available not inserting...");
			logger.debug("Data available not inserting...");
		}
		if (result) {
			int totalCountOfPropInsertion = 0;
			result = false;
			sql = CLRServicesSQL.CHECK_CLR_PROP_DATA;
			String dataCount = CLRServiceDAO.checkCount(sql, param, "CLRServiceDAO-CHECK_CLR_DATA");
			int dataCountN = Integer.parseInt(dataCount);
			System.out.println(dataCount + " ---- " + propData.size());
			if (dataCountN == propData.size()) {
				logger.debug("Data avaialable in the table prop table");
				System.out.println("Data avaialable in the table prop table");
				result = true;
			} else {
				logger.debug("deleting existing record if any from prop table");
				System.out.println("deleting existing record if any from prop table");
				sql = CLRServicesSQL.DELETE_CLR_PROP_DATA;
				boolean deleteCheck = CLRServiceDAO.deleteData(sql, param, "CLRServiceDAO-DELETE_CLR_PROP_DATA");
				deleteCheck = true;
				if (deleteCheck) {
					logger.debug("Inserting property data in table...");
					System.out.println("Inserting property data in table...");
					sql = CLRServicesSQL.INSERT_PROP_DATA;
					for (int i = 0; i < propData.size(); i++) {
						String newparam[] = new String[25];
						ArrayList<String> tempPropData = (ArrayList<String>) propData.get(i);
						for (int j = 0; j < tempPropData.size(); j++) {
							newparam[j] = (String) tempPropData.get(j);
						}
						newparam[24] = regTxnId;
						result = CLRServiceDAO.insertData(sql, newparam, "insertAllData-INSERT_PROP_DATA");
						if (result) {
							totalCountOfPropInsertion++;
						}
					}

					if (propData.size() == totalCountOfPropInsertion) {
						result = true;
						System.out.println("all inserted for INSERT_PROP_DATA..");
						logger.debug("all inserted for INSERT_PROP_DATA ....");
					} else {
						result = false;
					}
				} else {
					result = false;
				}
			}
		}
		if (result) {
			int totalCountOfPartyInsertion = 0;
			result = false;
			sql = CLRServicesSQL.CHECK_CLR_PARTY_DATA;
			String dataCount = CLRServiceDAO.checkCount(sql, param, "CLRServiceDAO-CHECK_CLR_PARTY_DATA");
			int dataCountN = Integer.parseInt(dataCount);
			if (dataCountN == partyData.size()) {
				logger.debug("Data avaialable in the party table");
				System.out.println("Data avaialable in the party table");
				result = true;
			} else {
				logger.debug("deleting existing record if any from party table");
				System.out.println("deleting existing record if any from party table");
				sql = CLRServicesSQL.DELETE_CLR_PARTY_DATA;
				boolean deleteCheck = CLRServiceDAO.deleteData(sql, param, "CLRServiceDAO-DELETE_CLR_PARTY_DATA");
				deleteCheck = true;
				if (deleteCheck) {
					logger.debug("Inserting party data in table...");
					System.out.println("Inserting party data in table...");
					sql = CLRServicesSQL.INSERT_PARTY_DATA;
					for (int i = 0; i < partyData.size(); i++) {
						String newparam[] = new String[29];
						ArrayList<String> tempPartyData = (ArrayList<String>) partyData.get(i);
						for (int j = 0; j < tempPartyData.size(); j++) {
							newparam[j] = (String) tempPartyData.get(j);
						}
						//newparam[28] = regTxnId;
						result = CLRServiceDAO.insertData(sql, newparam, "insertAllData-INSERT_PARTY_DATA");
						if (result) {
							totalCountOfPartyInsertion++;
						}
					}
					if (partyData.size() == totalCountOfPartyInsertion) {
						result = true;
						System.out.println("all inserted for INSERT_PROP_DATA..");
						logger.debug("all inserted for INSERT_PROP_DATA ....");
					} else {
						result = false;
					}
				} else {
					result = false;
				}
			}
		}
		response = result;
		return response;
	}

	public static boolean updateMain(String regTxnId, String propertyId) throws Exception {
		boolean resp = false;
		String sql = CLRServicesSQL.UPDATE_CLR_SHARE_STATUS;
		String arr[] = {regTxnId, propertyId};
		resp = CLRServiceDAO.updateData(sql, arr, "updateMain-UPDATE_CLR_SHARE_STATUS");
		return resp;
	}

	// below methods are for checking data and pushing it to CLR tables
	public static String ifBuyer(String partyTxnId) throws Exception {
		String response = "";
		String sql = CLRServicesSQL.CHECK_BUYER_STATUS;
		String param[] = {partyTxnId};
		ArrayList listTemp = CLRServiceDAO.getValAsArrayList(sql, param, "ifBuyer-CHECK_BUYER_STATUS");
		for (int k = 0; k < listTemp.size(); k++) {
			ArrayList<String> list = (ArrayList<String>) listTemp.get(k);
			if (null == list.get(2)) {
				response=list.get(4);
				/*String claimantFlag = list.get(3);
				if (claimantFlag.equalsIgnoreCase("3") || claimantFlag.equalsIgnoreCase("4") || claimantFlag.equalsIgnoreCase("6")) {
					response = "Y";
				}*/
			} else {
				String parentId = list.get(2);
				partyTxnId=parentId;
				String parentParam[] = {parentId};
				ArrayList tempList = CLRServiceDAO.getValAsArrayList(sql, parentParam, "ifBuyer-CHECK_BUYER_STATUS");
				for (int k1 = 0; k1 < tempList.size(); k1++) {
					ArrayList<String> tList = (ArrayList<String>) tempList.get(k1);
					response="OWNER-"+tList.get(4);
					/*String claimantFlagParent = tList.get(3);
					if (claimantFlagParent.equalsIgnoreCase("3") || claimantFlagParent.equalsIgnoreCase("4") || claimantFlagParent.equalsIgnoreCase("6")) {
						response = "Y";
					}*/
				}

			}
		}
		//response="Y";
		return response;
	}
	public static String checkCLRCount(String registrationNumber) throws Exception {
		String response="";
		String sql = CLRServicesSQL.CHK_DATA_AT_CLR;
		String param[] = {registrationNumber};
		response = CLRServiceDAO.checkCount(sql, param, "checkCLRCount-CHK_DATA_AT_CLR");
		return response;
	}
	public static boolean insertDataToCLR(String[] clrData) throws Exception {
		boolean status = false;
		String sql = CLRServicesSQL.INSERT_DATA_TO_CLR;
		status = CLRServiceDAO.insertData(sql, clrData, "insertAllData-INSERT_DATA_TO_CLR");
		return status;
	}
	
	public static String findShare(String propId, String khasraNumb, String partyTxnId) throws Exception {
		String share="";
		String sql = CLRServicesSQL.SHARE_CHK;
		String[] param = {propId,khasraNumb,partyTxnId};
		share = CLRServiceDAO.checkCount(sql, param, "findShare-SHARE_CHK");
		return share;
	}
	public static ArrayList propDetailsCLR(String regTxnId, String propertyId) throws Exception {
		ArrayList list = new ArrayList();
		String sql = CLRServicesSQL.FETCH_DATA_FOR_CLR;
		String arr[] = {regTxnId, propertyId};
		list = CLRServiceDAO.getValAsArrayList(sql, arr, "CLRService-propDetails");
		return list;
	}
	public static String getCyberCase(String regTxnId) throws Exception {
		String cyberCase="";
		String sql = CLRServicesSQL.CHECK_IF_CYBER;
		String[] param = {regTxnId};
		cyberCase = CLRServiceDAO.checkCount(sql, param, "getCyberCase-CHECK_IF_CYBER");
		return cyberCase;
	}
}
