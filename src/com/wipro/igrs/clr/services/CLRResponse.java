package com.wipro.igrs.clr.services;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class CLRResponse {

	private static Logger logger = (Logger) Logger.getLogger(CLRResponse.class);
	 public static void insertCLRData(String regTxnId) throws Exception{
	//public static void main(String args[]) throws Exception {
		//String regTxnId = "280122000002";
		String registrationNumber = "";
		ArrayList<String> list = CLRService.ifCLR(regTxnId);
		System.out.println(list.size());
		for (String s : list) {
			System.out.println(s);
			ArrayList temp = CLRService.propDetails(regTxnId, s);
			ArrayList propData = new ArrayList();
			System.out.println(temp.size());
			for (int k = 0; k < temp.size(); k++) {
				ArrayList<String> tempList = (ArrayList<String>) temp.get(k);
				ArrayList<String> storeInDb = new ArrayList<String>();

				// deed type
				if (tempList.get(0) == null) {
					storeInDb.add(0, "NA");
				} else {
					storeInDb.add(0, tempList.get(0));
				}
				// instrument type
				if (tempList.get(1) == null) {
					storeInDb.add(1, "NA");
				} else {
					storeInDb.add(1, tempList.get(1));
				}
				// registration number
				if (tempList.get(2) == null) {
					storeInDb.add(2, "NA");
				} else {
					storeInDb.add(2, tempList.get(2));
					registrationNumber = tempList.get(2);
				}
				// registration date
				if (tempList.get(3) == null) {
					storeInDb.add(3, null);
				} else {
					storeInDb.add(3, tempList.get(3));
				}
				// registration office id
				if (tempList.get(4) == null) {
					storeInDb.add(4, "NA");
				} else {
					storeInDb.add(4, tempList.get(4));
				}
				// registration property id
				if (tempList.get(5) == null) {
					storeInDb.add(5, "NA");
				} else {
					storeInDb.add(5, tempList.get(5));
				}
				// proprty district name
				if (tempList.get(6) == null) {
					storeInDb.add(6, "NA");
				} else {
					storeInDb.add(6, tempList.get(6));
				}
				// proprty tehsil name
				if (tempList.get(7) == null) {
					storeInDb.add(7, "NA");
				} else {
					storeInDb.add(7, tempList.get(7));
				}
				// proprty area name
				if (tempList.get(8) == null) {
					storeInDb.add(8, "NA");
				} else {
					storeInDb.add(8, tempList.get(8));
				}
				// proprty sub area name
				if (tempList.get(9) == null) {
					storeInDb.add(9, "NA");
				} else {
					storeInDb.add(9, tempList.get(9));
				}
				// proprty ward name
				if (tempList.get(10) == null) {
					storeInDb.add(10, "NA");
				} else {
					storeInDb.add(10, tempList.get(10));
				}
				// proprty colony name
				if (tempList.get(11) == null) {
					storeInDb.add(11, "NA");
				} else {
					storeInDb.add(11, tempList.get(11));
				}
				// proprty colony id
				if (tempList.get(12) == null) {
					storeInDb.add(12, "NA");
				} else {
					storeInDb.add(12, tempList.get(12));
				}
				// proprty ri circle
				if (tempList.get(13) == null) {
					storeInDb.add(13, "NA");
				} else {
					storeInDb.add(13, tempList.get(13));
				}
				// proprty lgd code
				if (tempList.get(14) == null) {
					storeInDb.add(14, "NA");
				} else {
					storeInDb.add(14, tempList.get(14));
				}
				// proprty khasra id
				if (tempList.get(15) == null) {
					storeInDb.add(15, "NA");
				} else {
					storeInDb.add(15, tempList.get(15));
				}
				// proprty khasra number
				if (tempList.get(16) == null) {
					storeInDb.add(16, "NA");
				} else {
					storeInDb.add(16, tempList.get(16));
				}
				// clr district id
				if (tempList.get(17) == null) {
					storeInDb.add(17, "NA");
				} else {
					storeInDb.add(17, tempList.get(17));
				}
				// clr tehsil id
				if (tempList.get(18) == null) {
					storeInDb.add(18, "NA");
				} else {
					storeInDb.add(18, tempList.get(18));
				}
				// clr khasra area
				if (tempList.get(19) == null) {
					storeInDb.add(19, "NA");
				} else {
					storeInDb.add(19, tempList.get(19));
				}
				// saleable area
				if (tempList.get(20) == null) {
					storeInDb.add(20, "NA");
				} else {
					storeInDb.add(20, tempList.get(20));
				}
				// owner id
				if (tempList.get(21) == null) {
					storeInDb.add(21, "NA");
				} else {
					storeInDb.add(21, tempList.get(21));
				}
				// registration office name
				if (tempList.get(22) == null) {
					storeInDb.add(22, "NA");
				} else {
					storeInDb.add(22, tempList.get(22));
				}
				// transaction type
				if (tempList.get(23) == null) {
					storeInDb.add(23, "NA");
				} else {
					storeInDb.add(23, tempList.get(23));
				}
				propData.add(storeInDb);
			}

			ArrayList tempParty = CLRService.partyDetails(regTxnId, s);
			ArrayList allPartyData = new ArrayList();
			for (int i = 0; i < tempParty.size(); i++) {
				ArrayList<String> partyData = (ArrayList<String>) tempParty.get(i);
				ArrayList<String> partyStoreInDB = new ArrayList<String>();
				// applicant type
				partyStoreInDB.add(0, partyData.get(0));
				// applicant type name
				partyStoreInDB.add(1, partyData.get(1));
				if (("1").equals(partyData.get(0))) { // organization
					partyStoreInDB.add(2,"");
					partyStoreInDB.add(3,"");
					partyStoreInDB.add(4,"");
					partyStoreInDB.add(5, partyData.get(24));// org address
					partyStoreInDB.add(6, partyData.get(5));
					partyStoreInDB.add(7, partyData.get(6));
					

					partyStoreInDB.add(8,"");
					partyStoreInDB.add(9,"");
					partyStoreInDB.add(10,"");
					
				} else if (("2").equals(partyData.get(0))) {// individual
					partyStoreInDB.add(2,partyData.get(2));
					partyStoreInDB.add(3,partyData.get(3));
					partyStoreInDB.add(4,partyData.get(4));
					
					partyStoreInDB.add(5,partyData.get(24));// party_Address
					
					partyStoreInDB.add(6,"");
					partyStoreInDB.add(7,"");
					partyStoreInDB.add(8,"");
					partyStoreInDB.add(9,"");
					partyStoreInDB.add(10,"");

				} else if (("3").equals(partyData.get(0))) {// government

					partyStoreInDB.add(2,"");
					partyStoreInDB.add(3,"");
					partyStoreInDB.add(4,"");
					partyStoreInDB.add(5,"");
					partyStoreInDB.add(6,"");
					partyStoreInDB.add(7,"");
					partyStoreInDB.add(8, partyData.get(7));
					partyStoreInDB.add(9,partyData.get(8));
					partyStoreInDB.add(10, partyData.get(10));
					
				}
						//index 10 completed
				
				if (null != partyData.get(27)) {
					if (("2").equals(partyData.get(28))) {
						partyStoreInDB.add(11, partyData.get(21));// poa name if individual
						partyStoreInDB.add(12, partyData.get(24));// poa type if individual
					} else if (("1").equals(partyData.get(28))) {
						partyStoreInDB.add(11, partyData.get(22));// poa name if
						partyStoreInDB.add(12, partyData.get(24));// poa type if
					} else {
						partyStoreInDB.add(11, "");
						partyStoreInDB.add(12, "");
					}
				} else {
					partyStoreInDB.add(11, "");
					partyStoreInDB.add(12, "");
				}
				
				//index 12 completed
				
				partyStoreInDB.add(13, partyData.get(31));// party mobile number
				partyStoreInDB.add(14, partyData.get(34));// party district name
				partyStoreInDB.add(15, partyData.get(33));// party email id
				partyStoreInDB.add(16, partyData.get(32));// party category
				partyStoreInDB.add(17, partyData.get(12));// party type
				partyStoreInDB.add(18, partyData.get(15));// guardian_name
				partyStoreInDB.add(19, partyData.get(29));// relation type
				
				partyStoreInDB.add(20, partyData.get(17));// property_id from sampada
				partyStoreInDB.add(21, partyData.get(18));// khasra number
				partyStoreInDB.add(22, partyData.get(19));// khasra id from CLR
				partyStoreInDB.add(23, partyData.get(20));// share for saleable  area
				
				partyStoreInDB.add(24, partyData.get(14));// photo proof type
				partyStoreInDB.add(25, partyData.get(13));// photo proof id
				partyStoreInDB.add(26, partyData.get(28));// party_txn_id
				partyStoreInDB.add(27,regTxnId);
				partyStoreInDB.add(28,partyData.get(30)); //gender
				//partyStoreInDB.add(27,partyData.get(34));
				
				allPartyData.add(partyStoreInDB);
				// System.out.println();
			}
			String caseType = CLRService.getCyberCase(regTxnId);
			boolean status = CLRService.insertAllData(propData, allPartyData, regTxnId, s);

			ArrayList<String[]> clrData = new ArrayList<String[]>();
			if (status) {
				ArrayList dataForClr = CLRService.propDetailsCLR(regTxnId, s);

				for (int i = 0; i < dataForClr.size(); i++) {
					ArrayList tmpPropData = (ArrayList) dataForClr.get(i);
					String distId = (String) tmpPropData.get(17);
					String tehsilId = (String) tmpPropData.get(18);
					String lgdCode = (String) tmpPropData.get(14);
					String regNumb = (String) tmpPropData.get(2);
					String regDate = (String) tmpPropData.get(3);
					String typeOfReg = (String) tmpPropData.get(1);
					String transactingArea = (String) tmpPropData.get(20);
					String khasraId = (String) tmpPropData.get(15);
					String khasraNumb = (String) tmpPropData.get(16);
					String transactionType = (String)tmpPropData.get(22);
					String readByGis = "N";
					String partyName = "";
					for (int j = 0; j < allPartyData.size(); j++) {
						ArrayList tmpPartyData = (ArrayList) allPartyData.get(j);
						String pKhasraId = (String) tmpPartyData.get(22);
						if (khasraId.equalsIgnoreCase(pKhasraId)) {
							String partyTxnId = (String) tmpPartyData.get(26);
							String buyerType = CLRService.ifBuyer(partyTxnId);
							String shares = CLRService.findShare(s, khasraNumb, partyTxnId);
							double shr = Double.parseDouble(shares);
							double area = Double.parseDouble(transactingArea);
							double totalArea = (area * shr) / 100;
							String transactingArea1 = String.valueOf(totalArea);
							String applicantType = (String) tmpPartyData.get(0);
							partyName = (String) tmpPartyData.get(2);
							String finalDataToInsert[] = new String[30];
							finalDataToInsert[0] = distId;
							finalDataToInsert[1] = tehsilId;
							finalDataToInsert[2] = lgdCode;
							finalDataToInsert[3] = khasraId;
							finalDataToInsert[4] = regNumb;
							finalDataToInsert[5] = regDate;
							finalDataToInsert[6] = typeOfReg;
							
							finalDataToInsert[7] = "";
							finalDataToInsert[8] = transactingArea1;
							finalDataToInsert[9] = readByGis;
							finalDataToInsert[10] = s;
							finalDataToInsert[11] = buyerType;
							finalDataToInsert[12] = transactionType;
							
							finalDataToInsert[13] = (String) tmpPartyData.get(2);
							finalDataToInsert[14] = (String) tmpPartyData.get(3);
							finalDataToInsert[15] = (String) tmpPartyData.get(4);
							
							finalDataToInsert[16] = (String) tmpPartyData.get(6);
							finalDataToInsert[17] = (String) tmpPartyData.get(7);
							
							finalDataToInsert[18] = (String) tmpPartyData.get(8);
							finalDataToInsert[19] = (String) tmpPartyData.get(9);
							
							finalDataToInsert[20] = (String) tmpPartyData.get(19);
							
							finalDataToInsert[21] = (String) tmpPartyData.get(5);

							finalDataToInsert[22] = (String) tmpPartyData.get(10);
							
							finalDataToInsert[23] = (String) tmpPartyData.get(14);
							//tmpPartyData.get(15);
							finalDataToInsert[24]= (String) tmpPartyData.get(13);

							finalDataToInsert[25]= (String) tmpPartyData.get(28);
							finalDataToInsert[26]= (String) tmpPartyData.get(16);
							finalDataToInsert[27]=shares;
							finalDataToInsert[28]=caseType;
							finalDataToInsert[29] = (String) tmpPartyData.get(18);
							clrData.add(finalDataToInsert);
							// status=CLRService.insertDataToCLR(finalDataToInsert);

						}
					}
					// status=CLRService.insertDataToCLR(propData, allPartyData,
					// regTxnId, s);
				}

				String count = String.valueOf(clrData.size());
				String totalCountInClr = CLRService.checkCLRCount(regTxnId);
				//System.out.println(count + "!!!!!!!!!" + totalCountInClr);
				boolean flag =count.equalsIgnoreCase(totalCountInClr) & status; 
				/*if (flag) {
					status = false;
					logger.debug("Data already present at CLR for propertyId "+s+" at CLR...");
				} else {
					status = true;
				}*/
				if (!flag) {
					for (int k = 0; k < clrData.size(); k++) {
						String param[] = clrData.get(k);
						System.out.println("Parameter size is -- "+param.length);
						status = CLRService.insertDataToCLR(param);
					}
				}else{
					status=true;
					logger.debug("Data already present at CLR for propertyId "+s+" at CLR...");
				}
			}
			if (status) {
				status = CLRService.updateMain(regTxnId, s);
			}
			if (status) {
				System.out.println("All inserted for regtxnid -- " + regTxnId + " and property id " + s);
			}
		}

	}
}
