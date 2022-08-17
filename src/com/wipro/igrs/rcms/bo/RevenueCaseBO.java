package com.wipro.igrs.rcms.bo;

import java.util.ArrayList;

import com.wipro.igrs.rcms.dao.RevenueCaseDAO;
import com.wipro.igrs.rcms.entity.RCMSResponse;

public class RevenueCaseBO {

	RevenueCaseDAO revDAO = new RevenueCaseDAO();

	public ArrayList getPropIdsForRCMS(String regTxnID) throws Exception {
		return revDAO.getPropIdsForRCMS(regTxnID);
	}

	public ArrayList getRegistrationDetails(String regTxnID) throws Exception {
		return revDAO.getRegistrationDetails(regTxnID);
	}

	public String getDocDate(String registrationNo) throws Exception {
		return revDAO.getDocDate(registrationNo);
	}

	public ArrayList getAllKhasraIds(String propId, String clrFlag) throws Exception {
		return revDAO.getAllKhasraIds(propId, clrFlag);
	}

	public ArrayList getBuyerSellerPartyList(String regTxnID, String propId, String clrFlag) throws Exception {
		return revDAO.getBuyerSellerPartyList(regTxnID, propId, clrFlag);
	}

	public ArrayList getSellerPartyList(String partyTxnId) throws Exception {
		return revDAO.getSellerPartyList(partyTxnId);
	}

	public ArrayList getBuyerPartyList(String partyTxnId) throws Exception {
		return revDAO.getBuyerPartyList(partyTxnId);
	}

	public String getRelationName(String relationID) throws Exception {
		return revDAO.getRelationName(relationID);
	}

	public String getBuyerIdTypeName(String photoID) throws Exception {
		return revDAO.getBuyerIdTypeName(photoID);
	}

	public String getBuyerCasteName(String categoryID) throws Exception {
		return revDAO.getBuyerCasteName(categoryID);
	}

	public ArrayList getAllAreaDetails(String propId) throws Exception {
		return revDAO.getAllAreaDetails(propId);
	}

	public String getPartyShare(String partyTxnId, String propId, String clrFlag, String khasraNo) throws Exception {
		return revDAO.getPartyShare(partyTxnId, propId, clrFlag, khasraNo);
	}

	public boolean updateRCMSStatus(String regTxnID, ArrayList propIdList, RCMSResponse rcmsResponse, String status) throws Exception {
		return revDAO.updateRCMSStatus(regTxnID, propIdList, rcmsResponse, status);
	}

	public boolean updateRegTrnStatus(String regTxnID, String status) throws Exception {
		return revDAO.updateRegTrnStatus(regTxnID, status);
	}

	public String getLandAreaForProp(String propId) throws Exception {
		return revDAO.getLandAreaForProp(propId);
	}

	public String getTotalSellableArea(String propId) throws Exception {
		return revDAO.getTotalSellableArea(propId);
	}
	public String getClrFlag(String propId) throws Exception {
		return revDAO.getClrFlag(propId);
	}
	public ArrayList getKhasraDetail(String regTxnId, String language) throws Exception {
		return revDAO.getKhasraDetail(regTxnId,language);
	}
	public RCMSResponse getReceiptDetail(String regTxnId) throws Exception {
		return revDAO.getReceiptDetail(regTxnId);
	}

	public ArrayList getDocDetail(String regTxnId) throws Exception {
		return revDAO.getDocDetail(regTxnId);
	}

	public ArrayList getPaymentDetail(String regTxnId) throws Exception {
		return revDAO.getPaymentDetail(regTxnId);
	}

	public ArrayList getFormAData(String regTxnId) throws Exception {
		return revDAO.getFormAData(regTxnId);
	}
	public boolean getDistrictCyberLiveStatus(String districtId) throws Exception {
		return revDAO.getDistrictCyberLiveStatus(districtId);
	}
	public boolean getKhasraTransactionType(String regTxnId) throws Exception {
		return revDAO.getKhasraTransactionType(regTxnId);
	}
	public ArrayList getPartySignPath(String regTxnId) throws Exception {
		return revDAO.getPartySignPath(regTxnId);
	}

	public String getFormPath(String regTxnId, String formType) throws Exception {
		return revDAO.getFormPath(regTxnId,formType);
	}
}
