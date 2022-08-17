package com.wipro.igrs.rcms.bd;

import java.util.ArrayList;

import com.wipro.igrs.rcms.bo.RevenueCaseBO;
import com.wipro.igrs.rcms.entity.RCMSResponse;

public class RevenueCaseBD {

	RevenueCaseBO revBO = new RevenueCaseBO();

	public ArrayList getPropIdsForRCMS(String regTxnID) throws Exception {
		return revBO.getPropIdsForRCMS(regTxnID);
	}

	public ArrayList getRegistrationDetails(String regTxnID) throws Exception {
		return revBO.getRegistrationDetails(regTxnID);
	}

	public String getDocDate(String registrationNo) throws Exception {
		return revBO.getDocDate(registrationNo);
	}

	public ArrayList getAllKhasraIds(String propId, String clrFlag) throws Exception {
		return revBO.getAllKhasraIds(propId, clrFlag);
	}

	public ArrayList getBuyerSellerPartyList(String regTxnID, String propId, String clrFlag) throws Exception {
		return revBO.getBuyerSellerPartyList(regTxnID, propId, clrFlag);
	}

	public ArrayList getSellerPartyList(String partyTxnId) throws Exception {
		return revBO.getSellerPartyList(partyTxnId);
	}

	public ArrayList getBuyerPartyList(String partyTxnId) throws Exception {
		return revBO.getBuyerPartyList(partyTxnId);
	}

	public String getRelationName(String relationID) throws Exception {
		return revBO.getRelationName(relationID);
	}

	public String getBuyerIdTypeName(String photoID) throws Exception {
		return revBO.getBuyerIdTypeName(photoID);
	}

	public String getBuyerCasteName(String categoryID) throws Exception {
		return revBO.getBuyerCasteName(categoryID);
	}

	public ArrayList getAllAreaDetails(String propId) throws Exception {
		return revBO.getAllAreaDetails(propId);
	}

	public String getPartyShare(String partyTxnId, String propId, String clrFlag, String khasraNo) throws Exception {
		return revBO.getPartyShare(partyTxnId, propId, clrFlag, khasraNo);
	}

	public boolean updateRCMSStatus(String regTxnID, ArrayList propIdList, RCMSResponse rcmsResponse, String status) throws Exception {
		return revBO.updateRCMSStatus(regTxnID, propIdList, rcmsResponse, status);
	}

	public boolean updateRegTrnStatus(String regTxnID, String status) throws Exception {
		return revBO.updateRegTrnStatus(regTxnID, status);
	}

	public String getLandAreaForProp(String propId) throws Exception {
		return revBO.getLandAreaForProp(propId);
	}

	public String getTotalSellableArea(String propId) throws Exception {
		return revBO.getTotalSellableArea(propId);
	}

	public String getClrFlag(String propId) throws Exception {
		return revBO.getClrFlag(propId);
	}
	// Table for RCMS Receipt
	public ArrayList getKhasraDetail(String regTxnId, String language) throws Exception {
		return revBO.getKhasraDetail(regTxnId,language);
	}
	// other receipt data received from RCMS
	public RCMSResponse getReceiptDetail(String regTxnId) throws Exception {
		return revBO.getReceiptDetail(regTxnId);
	}
	public ArrayList getDocDetail(String regTxnId) throws Exception {
		return revBO.getDocDetail(regTxnId);
	}
	public ArrayList<String> getPaymentDetail(String regTxnId) throws Exception {
		ArrayList list = revBO.getPaymentDetail(regTxnId);
		ArrayList<String> returnList = new ArrayList<String>();
		String transactionId = "";
		String amountPaid = "";
		String date = "";
		for (int i = 0; i < list.size(); i++) {
			ArrayList tempList = (ArrayList) list.get(i);
			transactionId = transactionId + "," + (String) tempList.get(0);
			amountPaid = amountPaid + "," + (String) tempList.get(1);
			date = (String) tempList.get(2);
		}
		if (!transactionId.equals("")) {
			transactionId = transactionId.substring(1, transactionId.length());
		}
		if (!amountPaid.equals("")) {
			amountPaid = amountPaid.substring(1, amountPaid.length());
		}

		returnList.add(transactionId);
		returnList.add(amountPaid);
		returnList.add(date);
		return returnList;
	}
	public ArrayList getFormAData(String regTxnId) throws Exception {
		return revBO.getFormAData(regTxnId);
	}

	public ArrayList getPartySignPath(String regTxnId) throws Exception {
		return revBO.getPartySignPath(regTxnId);
	}
	public String getFormPath(String regTxnId, String formType) throws Exception {
		return revBO.getFormPath(regTxnId,formType);
	}
}
