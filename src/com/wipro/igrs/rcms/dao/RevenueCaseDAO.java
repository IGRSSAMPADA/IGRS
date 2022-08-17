package com.wipro.igrs.rcms.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.wipro.igrs.DeliveryOfDocuments.dto.DeliveryOfDocumentsDTO;
import com.wipro.igrs.DeliveryOfDocuments.sql.DeliveryOfDocumentsSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newreginit.sql.RegCommonSQL;
import com.wipro.igrs.rcms.entity.RCMSResponse;
import com.wipro.igrs.rcms.sql.RevenueCaseSQL;

public class RevenueCaseDAO {

	private Logger logger = (Logger) Logger.getLogger(RevenueCaseDAO.class);
	PreparedStatement pst = null;

	public RevenueCaseDAO() {
	}

	public ArrayList getPropIdsForRCMS(String regTxnID) throws Exception {
		logger.debug("RCMS --- getPropIdsForRCMS");
		DBUtility dbUtil = null;
		ArrayList list2 = new ArrayList();
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_REG_PROP_IDs;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = regTxnID;
			list2 = dbUtil.executeQuery(param);
			return list2;
		} catch (Exception ex) {
			logger.debug("Exception occured in getPropIdsForRCMS" + ex);
		} finally {
			logger.debug("Connection closed in getPropIdsForRCMS");
			dbUtil.closeConnection();
		}
		return list2;
	}

	public ArrayList getRegistrationDetails(String regTxnID) throws Exception {

		logger.debug("RCMS --- getRegistrationDetails");
		DBUtility dbUtil = null;
		ArrayList list2 = new ArrayList();
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_REG_COMMON_DETLS;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = regTxnID;
			list2 = dbUtil.executeQuery(param);
			return list2;

		} catch (Exception ex) {
			logger.debug("Exception occured in getRegistrationDetails method" + ex);
		} finally {
			logger.debug("Connection closed for getRegistrationDetails method");
			dbUtil.closeConnection();
		}
		return list2;

	}

	public String getDocDate(String registrationNo) throws Exception {
		logger.debug("RCMS --- getDocDate");
		DBUtility dbUtil = null;
		String regtxnId = "";
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_DOC_DATE;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = registrationNo;
			regtxnId = dbUtil.executeQry(param);

			return regtxnId;
		} catch (Exception ex) {
			logger.debug("Exception occured in getDocDate Method" + ex);
			dbUtil.closeConnection();
		} finally {
			logger.debug("Connection closed for getDocDate ");
			dbUtil.closeConnection();
		}
		return regtxnId;

	}

	public ArrayList getAllKhasraIds(String propId, String clrFlag) throws Exception {
		logger.debug("RCMS --- getAllKhasraIds");
		DBUtility dbUtil = null;
		ArrayList khasraDetails = null;
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = "";
			if (clrFlag.equals("Y")) {
				str = RevenueCaseSQL.GET_KHASRA_ID_CLR;
			} else {
				str = RevenueCaseSQL.GET_KHASRA_ID;
			}
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = propId;
			khasraDetails = dbUtil.executeQuery(param);

			return khasraDetails;
		} catch (Exception ex) {
			logger.debug("Exception occured in RCMS getAllKhasraIds Method" + ex);
		} finally {
			logger.debug("Connection closed in RCMS getAllKhasraIds Method ");
			dbUtil.closeConnection();
		}
		return khasraDetails;
	}

	public ArrayList getBuyerSellerPartyList(String regTxnID, String propId, String clrFlag) throws Exception {
		logger.debug("RCMS --- getBuyerSellerPartyList");
		DBUtility dbUtil = null;
		ArrayList list1 = new ArrayList();
		ArrayList buyerList = new ArrayList();
		ArrayList sellerList = new ArrayList();
		ArrayList list2 = new ArrayList();
		ArrayList temp = new ArrayList();
		ArrayList listCombo = new ArrayList();
		String buyerSellerFlag = "";
		String poaFlag = "";
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = "";
			if ("Y".equalsIgnoreCase(clrFlag)) {
				str = RevenueCaseSQL.GET_ALL_BUYER_SELLER_PARTY_TXN_LIST_CLR;
			} else {
				str = RevenueCaseSQL.GET_ALL_BUYER_SELLER_PARTY_TXN_LIST;
			}

			String param[] = new String[2];
			param[0] = regTxnID;
			param[1] = propId;
			dbUtil.createPreparedStatement(str);

			list1 = dbUtil.executeQuery(param);

			for (int j = 0; j < list1.size(); j++) {
				ArrayList tem = new ArrayList();
				tem = (ArrayList) list1.get(j);
				list2.add(tem.get(0));
				System.out.println(tem.get(0));
			}
		//	list2= list1;
			for (int i = 0; i < list2.size(); i++) {
				String str2 = RevenueCaseSQL.GET_PARTY_TYPE_ID;
				dbUtil.createPreparedStatement(str2);
				String param1[] = new String[1];
				param1[0] = (String) list2.get(i);
				String party_type_id = dbUtil.executeQry(param1);

				// To get the owner parent ID in case of POA

				ArrayList poaDetails;
				ArrayList poaTemp;
				String str3 = RevenueCaseSQL.GET_POA_CHECK;
				dbUtil.createPreparedStatement(str3);
				String param2[] = new String[1];
				param2[0] = party_type_id;
				poaDetails = dbUtil.executeQuery(param2);

				for (int m = 0; m < poaDetails.size(); m++) {
					poaTemp = (ArrayList) poaDetails.get(m);
					poaFlag = (String) poaTemp.get(0);
					buyerSellerFlag = (String) poaTemp.get(1);
				}

				if (poaFlag.equalsIgnoreCase("1")) {
					String str4 = RevenueCaseSQL.GET_POA_OWNER;
					dbUtil.createPreparedStatement(str4);
					String param3[] = new String[1];
					param3[0] = (String) list2.get(i);
					String poa_owner = dbUtil.executeQry(param3);
					if (buyerSellerFlag.equalsIgnoreCase("B")) {
						buyerList.add(poa_owner);
					}
					if (buyerSellerFlag.equalsIgnoreCase("S")) {
						sellerList.add(poa_owner);
					}
				} else {
					if (buyerSellerFlag.equalsIgnoreCase("B")) {
						buyerList.add((String) list2.get(i));
					}
					if (buyerSellerFlag.equalsIgnoreCase("S")) {
						sellerList.add((String) list2.get(i));
					}
				}
			}
			listCombo.add(buyerList);
			listCombo.add(sellerList);
			return listCombo;

		} catch (Exception ex) {
			logger.debug("Exception occured in getBuyerSellerPartyList Method" + ex);
		} finally {
			logger.debug("Connection closed for getBuyerSellerPartyList Method");
			dbUtil.closeConnection();
		}
		return listCombo;

	}

	public ArrayList getSellerPartyList(String partyTxnId) throws Exception {
		logger.debug("RCMS --- getSellerPartyList");
		DBUtility dbUtil = null;
		ArrayList list2 = new ArrayList();
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_SELLER_PARTY_LIST;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = partyTxnId;
			list2 = dbUtil.executeQuery(param);
			return list2;
		} catch (Exception ex) {
			logger.debug("Exception occured in getSellerPartyList Method" + ex);
		} finally {
			logger.debug("Connection closed for getSellerPartyList Method");
			dbUtil.closeConnection();
		}
		return list2;

	}

	public ArrayList getBuyerPartyList(String partyTxnId) throws Exception {
		logger.debug("RCMS --- getBuyerPartyList");
		DBUtility dbUtil = null;
		ArrayList list2 = new ArrayList();
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_BUYER_PARTY_LIST;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = partyTxnId;
			list2 = dbUtil.executeQuery(param);

			return list2;
		} catch (Exception ex) {
			logger.debug("Exception occured in getBuyerPartyList Method" + ex);
		} finally {
			logger.debug("Connection Closed for getBuyerPartyList Method");
			dbUtil.closeConnection();
		}
		return list2;

	}

	public String getRelationName(String relationID) throws Exception {
		logger.debug("RCMS --- getRelationName");
		DBUtility dbUtil = null;
		String idTypeName = "";
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_RELATION_NAME;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = relationID;
			idTypeName = dbUtil.executeQry(param);
			return idTypeName;
		} catch (Exception ex) {
			logger.debug("Exception occured in getRelationName Method" + ex);
			dbUtil.closeConnection();
		} finally {
			logger.debug("Connection Closed for getRelationName Method");
			dbUtil.closeConnection();
		}
		return idTypeName;
	}

	public String getBuyerIdTypeName(String photoId) throws Exception {
		logger.debug("RCMS --- getBuyerIdTypeName");
		DBUtility dbUtil = null;
		String idTypeName = "";
		// String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_BUYER_TYPE_ID_NAME;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = photoId;
			idTypeName = dbUtil.executeQry(param);
			return idTypeName;
		} catch (Exception ex) {
			logger.debug("Exception occured in getBuyerIdTypeName Method" + ex);
		} finally {
			logger.debug("Connection Closed for getBuyerIdTypeName Method");
			dbUtil.closeConnection();
		}

		return idTypeName;

	}

	public String getBuyerCasteName(String categoryId) throws Exception {
		logger.debug("RCMS --- getBuyerCasteName");
		DBUtility dbUtil = null;
		String casteName = "";
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_BUYER_CASTE_NAME;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = categoryId;
			casteName = dbUtil.executeQry(param);
			return casteName;
		} catch (Exception ex) {
			logger.debug("Exception occured in getBuyerCasteName Method" + ex);
			dbUtil.closeConnection();
		} finally {
			logger.debug("Connection Closed for getBuyerCasteName Method");
			dbUtil.closeConnection();
		}

		return casteName;
	}

	public ArrayList getAllAreaDetails(String propId) throws Exception {
		logger.debug("RCMS --- getAllAreaDetails");
		DBUtility dbUtil = null;
		ArrayList list2 = new ArrayList();
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_AREA_DETAILS;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = propId;
			list2 = dbUtil.executeQuery(param);
			return list2;
		} catch (Exception ex) {
			logger.debug("Exception occured in getAllAreaDetails Method" + ex);
		} finally {
			logger.debug("Connection closed for getAllAreaDetails Method");
			dbUtil.closeConnection();
		}
		return list2;

	}

	public String getPartyShare(String partyTxnId, String propId, String clrFlag, String khasrano) throws Exception {
		logger.debug("RCMS --- getPartyShare");
		DBUtility dbUtil = null;
		String share = "";
		// String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = "";

			String param[] = new String[3];

			if ("Y".equals(clrFlag)) {
				str = RevenueCaseSQL.GET_PARTY_SHARE_CLR_KHASRA;
				param[0] = partyTxnId;
				param[1] = propId;
				param[2] = khasrano;
			} else {
				str = RevenueCaseSQL.GET_PARTY_SHARE;
				param = new String[2];
				param[0] = partyTxnId;
				param[1] = propId;
			}
			dbUtil.createPreparedStatement(str);

			share = dbUtil.executeQry(param);
			return share;
		} catch (Exception ex) {
			logger.debug("Exception occured in getShareOfParty Method" + ex);

		} finally {
			logger.debug("Finally block in getPartyShare ");
			dbUtil.closeConnection();
		}

		return share;

	}

	public boolean updateRCMSStatus(String regTxnID, ArrayList propIdList, RCMSResponse rcmsResponse, String status) throws Exception {

		logger.debug("RCMS --- updateRCMSStatus");
		DBUtility dbUtil = null;
		boolean boo = false;
		String seqId = "";
		String response[] = new String[18];
		String SQL = null;
		try {
			if (propIdList != null && !propIdList.isEmpty()) {

				dbUtil = new DBUtility();
				dbUtil.createStatement();

				for (int i = 0; i < propIdList.size(); i++) {
					String propId = (String) propIdList.get(i);
					// check if record exists
					String sql = RevenueCaseSQL.CHECK_DOC_STATUS;
					String param[] = {regTxnID, propId};
					dbUtil.createPreparedStatement(sql);
					String count = dbUtil.executeQry(param);
					int rcmscount = Integer.parseInt(count);
					if (rcmscount <= 0) {
						seqId = dbUtil.executeQry(RevenueCaseSQL.GET_RCMS_SEQ_ID);
						response[0] = seqId;
						response[1] = regTxnID;
						response[2] = propId;
						response[4] = rcmsResponse.getErrorType();
						response[5] = "0";
						response[6] = rcmsResponse.getApplicationNumber();
						response[7] = rcmsResponse.getCaseNumber();
						response[8] = rcmsResponse.getCourtName();
						response[9] = rcmsResponse.getDateOfPresentation();
						response[10] = rcmsResponse.getTehsil();
						response[11] = rcmsResponse.getDistrict();
						response[12] = rcmsResponse.getBuyerName();
						response[13] = rcmsResponse.getBuyerFather();
						response[14] = rcmsResponse.getSellerName();
						response[15] = rcmsResponse.getSellerFather();
						response[16] = rcmsResponse.getGramPanchayat();
						response[17] = rcmsResponse.getCyberCase();
						if (status.equalsIgnoreCase("Success"))
							response[3] = "1";
						if (status.equalsIgnoreCase("Failed"))
							response[3] = "0";

						dbUtil.createPreparedStatement(RevenueCaseSQL.INSERT_RCMS_RESPONSE);
						boo = dbUtil.executeUpdate(response);
						if (boo) {
							dbUtil.commit();

						} else {
							dbUtil.rollback();
							throw new Exception();
						}
					} else {
						response = new String[17];
						if ("Success".equals(rcmsResponse.getErrorType())) {
							response[0] = rcmsResponse.getErrorType();
						} else {
							response[0] = "Success";
						}

						response[1] = "0";
						response[3] = rcmsResponse.getApplicationNumber();
						response[4] = rcmsResponse.getCaseNumber();
						response[5] = rcmsResponse.getCourtName();
						response[6] = rcmsResponse.getDateOfPresentation();
						response[7] = rcmsResponse.getTehsil();
						response[8] = rcmsResponse.getDistrict();
						response[9] = rcmsResponse.getBuyerName();
						response[10] = rcmsResponse.getBuyerFather();
						response[11] = rcmsResponse.getSellerName();
						response[12] = rcmsResponse.getSellerFather();
						response[13] = rcmsResponse.getGramPanchayat();
						response[14] = rcmsResponse.getCyberCase();
						response[15] = regTxnID;
						response[16] = propId;
						if (status.equalsIgnoreCase("Success"))
							response[2] = "1";
						if (status.equalsIgnoreCase("Failed"))
							response[2] = "0";

						String sql1 = RevenueCaseSQL.UPDATE_FAILED_RCMS_STATUS;
						dbUtil.createPreparedStatement(sql1);
						boo = dbUtil.executeUpdate(response);
					}
				}

			}

		} catch (Exception e) {
			logger.debug("Exception occured in updateRCMSStatus Method" + e);
			return false;
		} finally {
			logger.debug("Connection closed for updateRCMSStatus Method");
			dbUtil.closeConnection();

		}
		return boo;

	}

	public boolean updateRegTrnStatus(String regTxnID, String status) throws Exception {
		logger.debug("RCMS ----- updateRegTrnStatus");
		DBUtility dbUtil = null;
		boolean boo = false;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.UPDATE_REG_TXN_TABLE;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[2];
			param[0] = status;
			param[1] = regTxnID;
			boo = dbUtil.executeUpdate(param);
			if (boo) {
				dbUtil.commit();

			} else
				dbUtil.rollback();
			return boo;
		} catch (Exception ex) {
			logger.debug("Exception occured in updateRegTrnStatus Method" + ex);
		} finally {
			logger.debug("Connection Closed for updateRegTrnStatus Method");
			dbUtil.closeConnection();
		}

		return boo;

	}

	public String getLandAreaForProp(String propId) throws Exception {
		logger.debug("RCMS --- getLandAreaForProp");
		DBUtility dbUtil = null;
		String landArea = "";
		String typeList = null;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_LAND_AREA;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = propId;
			landArea = dbUtil.executeQry(param);

			return landArea;
		} catch (Exception ex) {
			logger.debug("Exception occured in getLandAreaForProp Method" + ex);
			dbUtil.closeConnection();
		} finally {
			logger.debug("Connection closed for getLandAreaForProp ");
			dbUtil.closeConnection();
		}
		return landArea;

	}

	public String getTotalSellableArea(String propId) throws Exception {
		logger.debug("RCMS --- getTotalSellableArea");
		DBUtility dbUtil = null;
		ArrayList list2 = new ArrayList();
		String totalArea = null;
		double area = 0;
		try {
			dbUtil = new DBUtility();
			String str = RevenueCaseSQL.GET_LAND_AREA;
			dbUtil.createPreparedStatement(str);
			String param[] = new String[1];
			param[0] = propId;
			totalArea = dbUtil.executeQry(param);

			return totalArea;
		} catch (Exception ex) {
			logger.debug("Exception occured in getTotalSellableArea Method" + ex);
		} finally {
			logger.debug("Connection closed for getTotalSellableArea Method");
			dbUtil.closeConnection();
		}
		return totalArea;

	}

	public String getClrFlag(String propId) throws Exception {
		String clrFlag = "";
		String sql = RevenueCaseSQL.CHECK_CLR_FLAG;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String param[] = {propId};
			clrFlag = dbUtil.executeQry(param);
		} catch (Exception ex) {
			logger.debug("Exception occured in getClrFlag Method" + ex + " for property id " + propId);
		} finally {
			logger.debug("Connection closed for getClrFlag Method");
			dbUtil.closeConnection();
		}
		return clrFlag;
	}
	public ArrayList getAllProperties(String regTxnId) throws Exception {
		ArrayList list = new ArrayList();
		String sql = RevenueCaseSQL.GET_ALL_PROPID;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String param[] = {regTxnId};
			list = dbUtil.executeQuery(param);
		} catch (Exception ex) {
			logger.debug("Exception occured in getAllProperties Method" + ex);
		} finally {
			logger.debug("Connection closed for getAllProperties Method");
			dbUtil.closeConnection();
		}
		return list;
	}
	// Data for Table fetched from db
	public ArrayList getKhasraDetail(String regTxnId, String language) throws Exception {
		ArrayList returnList = new ArrayList();
		ArrayList propList = getAllProperties(regTxnId);
		for (int pList = 0; pList<propList.size() ; pList++) {
		ArrayList propertyIdList = (ArrayList) propList.get(pList);
		String propId=(String) propertyIdList.get(0);
		String clrFlag = getClrFlag(propId);
		ArrayList khasrano = getAllKhasraIds(propId, clrFlag);
		ArrayList listOne = getBuyerSellerPartyList(regTxnId, propId, clrFlag);
		ArrayList buyerPartyList = (ArrayList) listOne.get(0);
		ArrayList sellerPartyList = (ArrayList) listOne.get(1);
		ArrayList districtData = getDistrictData(propId);
		districtData = (ArrayList) districtData.get(0);
		String districtName = (String) ((language.equals("en"))?districtData.get(0):districtData.get(4));
		String tehsilName = (String) ((language.equals("en"))?districtData.get(1):districtData.get(5));
		String subAreaName =  (String) ((language.equals("en"))?districtData.get(2):districtData.get(6));
		String wardName =  (String) ((language.equals("en"))?districtData.get(3):districtData.get(7));
			// ArrayList ss = new ArrayList
			for (int i = 0; i < khasrano.size(); i++) {
				ArrayList list = new ArrayList();
				ArrayList tmpList = (ArrayList) khasrano.get(i);
				String khasranum = (String) tmpList.get(1);
				String rakwaArea = (String) tmpList.get(2);
				ArrayList buyer = new ArrayList();
				ArrayList seller = new ArrayList();
				for (int b = 0; b < buyerPartyList.size(); b++) {
					String buyerName = "";
					String buyerFatherName = "";
					ArrayList tmpBuyer = getBuyerPartyList((String) buyerPartyList.get(b));
					tmpBuyer = (ArrayList) tmpBuyer.get(0);
					if (tmpBuyer.get(0).equals("1")) {
						if (((String) tmpBuyer.get(5)) != null && !((String) tmpBuyer.get(5)).isEmpty()) {
							buyerName = ((String) tmpBuyer.get(5));
						} else {
							buyerName = "";
						}
						buyerFatherName = "";
						buyer.add(buyerName + "," + buyerFatherName);
					} else if (tmpBuyer.get(0).equals("2")) {
						if (((String) tmpBuyer.get(1)) != null && !((String) tmpBuyer.get(1)).isEmpty()) {
							buyerName = ((String) tmpBuyer.get(1));
						} else {
							buyerName = "";
						}

						if (((String) tmpBuyer.get(2)) != null && !((String) tmpBuyer.get(2)).isEmpty()) {
							buyerName = buyerName + (" " + (String) tmpBuyer.get(2));
						} else {
							buyerName = buyerName + "";
						}

						if (((String) tmpBuyer.get(3)) != null && !((String) tmpBuyer.get(3)).isEmpty()) {
							buyerName = buyerName + " " + ((String) tmpBuyer.get(3));
						} else {
							buyerName = buyerName + "";
						}

						if (((String) tmpBuyer.get(21)) != null && !((String) tmpBuyer.get(21)).isEmpty()) {
							buyerFatherName = ((String) tmpBuyer.get(21));
						} else {
							buyerFatherName = "";
						}
						buyer.add(buyerName + ", पिता/पति " + buyerFatherName);
					} else {
						if (((String) tmpBuyer.get(6)) != null && !((String) tmpBuyer.get(6)).isEmpty()) {
							buyerName = ((String) tmpBuyer.get(6));
						} else {
							buyerName = "";
						}
						buyerFatherName = ("");
						buyer.add(buyerName + "," + buyerFatherName);
					}

				}

				for (int s = 0; s < sellerPartyList.size(); s++) {
					String sellerName="";
					String sellerFather="";
					ArrayList tempSeller = getSellerPartyList((String) sellerPartyList.get(s));
					tempSeller = (ArrayList) tempSeller.get(0);
					if (tempSeller.get(0).equals("1")) {
						if (((String) tempSeller.get(5)) != null && !((String) tempSeller.get(5)).isEmpty()) {
							sellerName = (String) tempSeller.get(5);
							sellerFather = ((String) tempSeller.get(8))==null?"":(String) tempSeller.get(8);
							seller.add(sellerName+", "+sellerFather);
						} else {
							seller.add(sellerName+", "+sellerFather);
						}
						//seller.add(seller)
					} else if (tempSeller.get(0).equals("2")) {
						if (((String) tempSeller.get(1)) != null && !((String) tempSeller.get(1)).isEmpty()) {
							sellerName = (String) tempSeller.get(1);
							sellerFather = ((String) tempSeller.get(8))==null?"":(String) tempSeller.get(8);
							seller.add(sellerName+", "+sellerFather);
						} else {
							seller.add(sellerName+", "+sellerFather);
						}
					} else {
						if (((String) tempSeller.get(6)) != null && !((String) tempSeller.get(6)).isEmpty()) {
							sellerName = (String) tempSeller.get(6);
							sellerFather = ((String) tempSeller.get(8))==null?"":(String) tempSeller.get(8);
							seller.add(sellerName+", "+sellerFather);
						} else {
							seller.add(sellerName+", "+sellerFather);
						}
					}
				}
				list.add(khasranum);
				list.add(rakwaArea);
				list.add(buyer);
				list.add(seller);
				list.add(districtName);
				list.add(tehsilName);
				list.add(subAreaName);
				list.add(wardName);
				returnList.add(list);
			}
		}
		return returnList;
		}
	//for receipt detail received from RCMS
	public RCMSResponse getReceiptDetail(String regTxnId) throws Exception {
		RCMSResponse response = null;
		String sql = RevenueCaseSQL.GET_RCMS_RECEIPT_DATA;
		DBUtility dbUtil = null;
		ArrayList list =null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String param[] = {regTxnId};
			list = new ArrayList();
			list = dbUtil.executeQuery(param);
		} catch (Exception ex) {
			logger.debug("Exception occured in getReceiptDetail Method");
		} finally {
			logger.debug("Connection closed for getReceiptDetail Method");
			dbUtil.closeConnection();
		}
		if(list.size()>0){
			list = (ArrayList) list.get(0);
		}else {
					list = new ArrayList();
					list.add("");
					list.add("");
					list.add("");
					list.add("");
					list.add("");
					list.add("");
					list.add("");
					list.add("");
					list.add("");
					list.add("");
					list.add("");
		}
		response = new RCMSResponse();
		response.setApplicationNumber(((String) list.get(0) == null ? "NA" : (String) list.get(0)));
		response.setCaseNumber(((String) list.get(1) == null ? "NA" : (String) list.get(1)));
		response.setCourtName(((String) list.get(2) == null ? "NA" : (String) list.get(2)));
		response.setDateOfPresentation(((String) list.get(3) == null ? "NA" : (String) list.get(3)));
		response.setTehsil(((String) list.get(4) == null ? "NA" : (String) list.get(4)));
		response.setDistrict(((String) list.get(5) == null ? "NA" : (String) list.get(5)));
		response.setBuyerName(((String) list.get(6) == null ? "NA" : (String) list.get(6)));
		response.setBuyerFather(((String) list.get(7) == null ? "NA" : (String) list.get(7)));
		response.setSellerName(((String) list.get(8) == null ? "NA" : (String) list.get(8)));
		response.setSellerFather(((String) list.get(9) == null ? "NA" : (String) list.get(9)));
		response.setGramPanchayat(((String) list.get(10) == null ? "NA" : (String) list.get(10)));
		ArrayList docDataList = getDocDetail(regTxnId);
		for(int dList = 0; dList<docDataList.size(); dList++){
		ArrayList docDetail = (ArrayList) docDataList.get(dList);
		response.setRegistrationNumber(((String) docDetail.get(0) == null ? "NA" : (String) docDetail.get(0)));
		response.setDocumentType(((String) docDetail.get(1) == null ? "NA" : (String) docDetail.get(1)));
		response.setRegistrationDate(((String) docDetail.get(2) == null ? "NA" : (String) docDetail.get(2)));

		}
		return response;
	}
	public ArrayList getDocDetail(String regTxnId) throws Exception {
		ArrayList returnList = null;
		String sql = RevenueCaseSQL.GET_DOC_DETAIL;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			returnList = new ArrayList();
			dbUtil.createPreparedStatement(sql);
			String param[] = {regTxnId};
			returnList = dbUtil.executeQuery(param);
		} catch (Exception ex) {
			logger.debug("Exception occured in getDocDetail Method");
		} finally {
			logger.debug("Connection closed for getDocDetail Method");
			dbUtil.closeConnection();
		}
		return returnList;
	}
	public ArrayList getPaymentDetail(String regTxnId) throws Exception {
		ArrayList list = new ArrayList();
		String sql = RevenueCaseSQL.FETCH_PAYMENT_DETAIL;
		DBUtility dbUtility = null;
		String arr[] = {regTxnId};
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception in getPaymentDetail -RevenueCaseDAO" + exception);
		} finally {
			dbUtility.closeConnection();

		}
		return list;
	}
	public ArrayList getFormAData(String regTxnId) throws Exception {
		String sql = RevenueCaseSQL.FETCH_FORMA_DATA;
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		String arr[] = {regTxnId};
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(arr);

		} catch (Exception exception) {
			logger.debug("Exception in getFormAData -RevenueCaseDAO" + exception);
		} finally {
			dbUtility.closeConnection();

		}
		return list;
	}

	// for district level check in RCMS-Cyber Tehsil Saurav 22/04/22
	public boolean getDistrictCyberLiveStatus(String districtId) throws Exception {
		boolean districtStatus = false;
		String sql = RevenueCaseSQL.GET_CYBER_DISTRICT_CHECK;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String param[] = {districtId};
			dbUtility.createPreparedStatement(sql);
			String status = dbUtility.executeQry(param);
			if ("Y".equalsIgnoreCase(status)) {
				districtStatus = true;
			}
		} catch (Exception exception) {
			logger.debug("Exception in getDistrictCyberLiveStatus -RevenueCaseDAO" + exception);
		} finally {
			dbUtility.closeConnection();

		}
		return districtStatus;
	}

	public boolean getKhasraTransactionType(String regTxnId) throws Exception {
		boolean khasraTransactionStatus = true;
		String sql = RevenueCaseSQL.GET_KHASRA_TRANSACTION_TYPE;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String param[] = {regTxnId};
			dbUtility.createPreparedStatement(sql);
			ArrayList status = dbUtility.executeQuery(param);
			ArrayList<String> statusList = new ArrayList<String>();
			for(int i = 0; i<status.size(); i++){
				ArrayList tempStatus = (ArrayList) status.get(i);
				String kStatus = (String) tempStatus.get(0);
				if("PARTIAL".equalsIgnoreCase(kStatus)){
					khasraTransactionStatus=false;
					break;
				}
			}
			
		} catch (Exception exception) {
			logger.debug("Exception in getKhasraTransactionType -RevenueCaseDAO" + exception);
		} finally {
			dbUtility.closeConnection();

		}
		return khasraTransactionStatus;
	}
	
	public ArrayList getPartySignPath(String regTxnId) throws Exception {
		
		ArrayList data = new ArrayList();
		String sql = RevenueCaseSQL.GET_SELLER_SIGN_PATH;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String param[] = {regTxnId};
			dbUtility.createPreparedStatement(sql);
			data = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPartySignPath -RevenueCaseDAO" + exception);
		} finally {
			dbUtility.closeConnection();

		}
		
		return data;
	}
	public String getFormPath(String regTxnId, String formType) throws Exception {
		String path = "";
		String sql = "";
		if(formType.equalsIgnoreCase("A1")){
			sql = RevenueCaseSQL.GET_FORMVIA_PATH;
		}else if(formType.equalsIgnoreCase("A2")){
			sql = RevenueCaseSQL.GET_FORMVIB_PATH;
		}
		DBUtility dbUtility = null;
		//DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String param[] = {regTxnId};
			dbUtility.createPreparedStatement(sql);
			path = dbUtility.executeQry(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getFormPath -RevenueCaseDAO" + exception);
		} finally {
			dbUtility.closeConnection();

		}
		return path;
	}
	
	public ArrayList getDistrictData(String propId) throws Exception {
		String sql = RevenueCaseSQL.GET_DISTRICT_TEHSIL_INFO;
		DBUtility dbUtility = null;
		ArrayList retList = new ArrayList();
		//DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String param[] = {propId};
			dbUtility.createPreparedStatement(sql);
			retList = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getFormPath -RevenueCaseDAO" + exception);
		} finally {
			dbUtility.closeConnection();

		}
		return retList;
	}
}
