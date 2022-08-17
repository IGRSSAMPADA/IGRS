package com.wipro.igrs.documentsearch.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wipro.igrs.documentsearch.constant.CommonConstant;
import com.wipro.igrs.documentsearch.dao.OldDocumentSearchDAO;
import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSaleBuyerSellerDetails;
import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSaleFloorDetails;
import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSaleKhasraDetails;
import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSalePropertyDetails;
import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSaleStampDetails;
import com.wipro.igrs.documentsearch.form.OldDocumentSearchForm;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */
public class OldDocumentSearchBO {
	OldDocumentSearchDAO oldDocSrchDao = new OldDocumentSearchDAO();
	Logger logger = (Logger) Logger.getLogger(OldDocumentSearchBO.class);

	/**
	 * @return
	 * @throws Exception
	 */
	public List<String> getDistrictList() throws Exception {
		List<String> districList = null;
		try {
			districList = oldDocSrchDao.getDistrictList();
		} catch (Exception e) {
			logger.error(e);
		}
		return districList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<String> getSroNameList() throws Exception {
		List<String> sroNameList = null;
		try {
			sroNameList = oldDocSrchDao.getSroNameList();
		} catch (Exception e) {
			logger.error(e);
		}
		return sroNameList;
	}

	/**
	 * @param form
	 * @param params
	 * @param deedType
	 * @throws Exception
	 */
	public void getBuyerSellerDetails(OldDocumentSearchForm form, Map<String, String> deedRegistrationParamMap,
			String deedType) throws Exception {
		List<List<String>> seller_buyer_records = null;
		List<DeedCertificateOfSaleBuyerSellerDetails> deedCertificateOfSaleBuyerSellerDetails = null;
		try {
			if (deedType.equalsIgnoreCase(CommonConstant.SALE)) {
				seller_buyer_records = oldDocSrchDao.getSaleBuyerSellerDetails(form, deedRegistrationParamMap);
			} else if (deedType.equalsIgnoreCase(CommonConstant.MASTER)) {
				seller_buyer_records = oldDocSrchDao.getMasterPartyDetails(form, deedRegistrationParamMap);
			} else if (deedType.equalsIgnoreCase(CommonConstant.EXCHANGE)) {
				seller_buyer_records = oldDocSrchDao.getExchangePartyDetails(form, deedRegistrationParamMap);
			}
			deedCertificateOfSaleBuyerSellerDetails = new ArrayList<DeedCertificateOfSaleBuyerSellerDetails>();
			for (List<String> record : seller_buyer_records) {
				DeedCertificateOfSaleBuyerSellerDetails buyerSellerDetails = new DeedCertificateOfSaleBuyerSellerDetails();
				buyerSellerDetails.setId(Integer.parseInt(record.get(0)));
				buyerSellerDetails
						.setName((record.get(1) == null || record.get(1).equals("")) ? "-NA-" : record.get(1));
				buyerSellerDetails
						.setOrganisation((record.get(2) == null || record.get(2).equals("")) ? "-NA-" : record.get(2));
				buyerSellerDetails
						.setAge_in_years((record.get(3) == null || record.get(3).equals("")) ? "-NA-" : record.get(3));
				buyerSellerDetails
						.setFather_name((record.get(4) == null || record.get(4).equals("")) ? "-NA-" : record.get(4));
				buyerSellerDetails
						.setAddress((record.get(5) == null || record.get(5).equals("")) ? "-NA-" : record.get(5));
				buyerSellerDetails
						.setTehsil((record.get(6) == null || record.get(6).equals("")) ? "-NA-" : record.get(6));
				buyerSellerDetails
						.setMoholla((record.get(7) == null || record.get(7).equals("")) ? "-NA-" : record.get(7));
				buyerSellerDetails
						.setWard_number((record.get(8) == null || record.get(8).equals("")) ? "-NA-" : record.get(8));
				buyerSellerDetails
						.setArea_type((record.get(9) == null || record.get(9).equals("")) ? "-NA-" : record.get(9));
				buyerSellerDetails
						.setDistrict((record.get(10) == null || record.get(10).equals("")) ? "-NA-" : record.get(10));
				buyerSellerDetails
						.setState((record.get(11) == null || record.get(11).equals("")) ? "-NA-" : record.get(11));
				buyerSellerDetails
						.setCountry((record.get(12) == null || record.get(12).equals("")) ? "-NA-" : record.get(12));
				buyerSellerDetails
						.setEmail((record.get(13) == null || record.get(13).equals("")) ? "-NA-" : record.get(13));
				buyerSellerDetails
						.setCaste((record.get(14) == null || record.get(14).equals("")) ? "-NA-" : record.get(14));
				buyerSellerDetails.setNationality(
						(record.get(15) == null || record.get(15).equals("")) ? "-NA-" : record.get(15));
				buyerSellerDetails
						.setId_Name((record.get(16) == null || record.get(16).equals("")) ? "-NA-" : record.get(16));
				buyerSellerDetails.setMajor_minor(
						(record.get(17) == null || record.get(17).equals("")) ? "-NA-" : record.get(17));
				buyerSellerDetails.setOwnership_share(
						(record.get(18) == null || record.get(18).equals("")) ? "-NA-" : record.get(18));
				buyerSellerDetails
						.setRole((record.get(19) == null || record.get(19).equals("")) ? "-NA-" : record.get(19));
				buyerSellerDetails.setIndividual_organization(
						(record.get(20) == null || record.get(20).equals("")) ? "-NA-" : record.get(20));
				buyerSellerDetails
						.setSelf_poa((record.get(21) == null || record.get(21).equals("")) ? "-NA-" : record.get(21));
				buyerSellerDetails
						.setPoaRegDate((record.get(22) == null || record.get(22).equals("")) ? "-NA-" : record.get(22));
				buyerSellerDetails
						.setPoaSROName((record.get(23) == null || record.get(23).equals("")) ? "-NA-" : record.get(23));
				buyerSellerDetails
						.setPoaRegNo((record.get(24) == null || record.get(24).equals("")) ? "-NA-" : record.get(24));
				buyerSellerDetails
						.setParty_type((record.get(25) == null || record.get(25).equals("")) ? "-NA-" : record.get(25));
				deedCertificateOfSaleBuyerSellerDetails.add(buyerSellerDetails);
			}

		} catch (Exception e) {
			logger.error(e);
		}
		form.setDeedCertificateOfSaleBuyerSellerDetails(deedCertificateOfSaleBuyerSellerDetails);
	}

	/**
	 * @param form
	 * @param params
	 * @param deedType
	 * @throws Exception
	 */
	public void getPropertyDetails(OldDocumentSearchForm form, Map<String, String> deedRegistrationParamMap,
			String deedType) throws Exception {
		List<List<String>> property_records = null;
		List<DeedCertificateOfSalePropertyDetails> deedCertificateOfSalePropertyDetails = null;
		try {
			if (deedType.equalsIgnoreCase(CommonConstant.SALE)) {
				property_records = oldDocSrchDao.getSalePropertyDetails(form, deedRegistrationParamMap);
			} else if (deedType.equalsIgnoreCase(CommonConstant.MASTER)) {
				property_records = oldDocSrchDao.getMasterPropertyDetails(form, deedRegistrationParamMap);
			} else if (deedType.equalsIgnoreCase(CommonConstant.EXCHANGE)) {
				property_records = oldDocSrchDao.getExchangePropertyDetails(form, deedRegistrationParamMap);
			}
			deedCertificateOfSalePropertyDetails = new ArrayList<DeedCertificateOfSalePropertyDetails>();
			for (List<String> record : property_records) {
				DeedCertificateOfSalePropertyDetails propertyDetails = new DeedCertificateOfSalePropertyDetails();
				propertyDetails.setId(Integer.parseInt(record.get(0)));
				propertyDetails.setNazool((record.get(1) == null || record.get(1).equals("")) ? "-NA-" : record.get(1));
				propertyDetails.setNo_of_contructed_floors(
						(record.get(2) == null || record.get(2).equals("")) ? "-NA-" : record.get(2));
				propertyDetails
						.setNorth_detail((record.get(3) == null || record.get(3).equals("")) ? "-NA-" : record.get(3));
				propertyDetails.setOriginal_plot_number(
						(record.get(4) == null || record.get(4).equals("")) ? "-NA-" : record.get(4));
				propertyDetails
						.setPlot_number((record.get(5) == null || record.get(5).equals("")) ? "-NA-" : record.get(5));
				propertyDetails.setProperty_address(
						(record.get(6) == null || record.get(6).equals("")) ? "-NA-" : record.get(6));
				propertyDetails.setProperty_area_type(
						(record.get(7) == null || record.get(7).equals("")) ? "-NA-" : record.get(7));
				propertyDetails.setProperty_district(
						(record.get(8) == null || record.get(8).equals("")) ? "-NA-" : record.get(8));
				propertyDetails.setProperty_governing_municipal(
						(record.get(9) == null || record.get(9).equals("")) ? "-NA-" : record.get(9));
				propertyDetails.setProperty_mohalla(
						(record.get(10) == null || record.get(10).equals("")) ? "-NA-" : record.get(10));
				propertyDetails.setProperty_number(
						(record.get(11) == null || record.get(11).equals("")) ? "-NA-" : record.get(11));
				propertyDetails.setProperty_subtypeI(
						(record.get(12) == null || record.get(12).equals("")) ? "-NA-" : record.get(12));
				propertyDetails.setProperty_subtypeII(
						(record.get(13) == null || record.get(13).equals("")) ? "-NA-" : record.get(13));
				propertyDetails.setProperty_tehsil(
						(record.get(14) == null || record.get(14).equals("")) ? "-NA-" : record.get(14));
				propertyDetails.setProperty_type(
						(record.get(15) == null || record.get(15).equals("")) ? "-NA-" : record.get(15));
				propertyDetails.setProperty_ward_number(
						(record.get(16) == null || record.get(16).equals("")) ? "-NA-" : record.get(16));
				propertyDetails
						.setRIC_circle((record.get(17) == null || record.get(17).equals("")) ? "-NA-" : record.get(17));
				propertyDetails.setSouth_detail(
						(record.get(18) == null || record.get(18).equals("")) ? "-NA-" : record.get(18));
				propertyDetails.setTotal_land_area(
						(record.get(19) == null || record.get(19).equals("")) ? "-NA-" : record.get(19));
				propertyDetails.setUnit_of_measurement(
						(record.get(20) == null || record.get(20).equals("")) ? "-NA-" : record.get(20));
				propertyDetails.setVikas_khand(
						(record.get(21) == null || record.get(21).equals("")) ? "-NA-" : record.get(21));
				propertyDetails.setWest_detail(
						(record.get(22) == null || record.get(22).equals("")) ? "-NA-" : record.get(22));
				propertyDetails.setEast_detail(
						(record.get(23) == null || record.get(23).equals("")) ? "-NA-" : record.get(23));
				propertyDetails.setProperty_village(
						(record.get(24) == null || record.get(24).equals("")) ? "-NA-" : record.get(24));
				propertyDetails
						.setParty_id((record.get(25) == null || record.get(25).equals("")) ? "-NA-" : record.get(25));
				deedCertificateOfSalePropertyDetails.add(propertyDetails);
			}

		} catch (Exception e) {
			logger.error(e);
		}
		form.setDeedCertificateOfSalePropertyDetails(deedCertificateOfSalePropertyDetails);
	}

	/**
	 * @param form
	 * @param params
	 * @param deedType
	 * @throws Exception
	 */
	public void getFloorDetails(OldDocumentSearchForm form, Map<String, String> deedRegistrationParamMap,
			String deedType) throws Exception {
		List<List<String>> floor_records = null;
		List<DeedCertificateOfSaleFloorDetails> deedCertificateOfSaleFloorDetails = null;
		try {
			if (deedType.equalsIgnoreCase(CommonConstant.SALE)) {
				floor_records = oldDocSrchDao.getSaleFloorDetails(form, deedRegistrationParamMap);
			} else if (deedType.equalsIgnoreCase(CommonConstant.MASTER)) {
				floor_records = oldDocSrchDao.getMasterFloorDetails(form, deedRegistrationParamMap);
			}
			deedCertificateOfSaleFloorDetails = new ArrayList<DeedCertificateOfSaleFloorDetails>();
			for (List<String> record : floor_records) {
				DeedCertificateOfSaleFloorDetails floorDetails = new DeedCertificateOfSaleFloorDetails();
				floorDetails.setId(Integer.parseInt(record.get(0)));
				floorDetails.setProperty_number(
						(record.get(1) == null || record.get(1).equals("")) ? "-NA-" : record.get(1));
				floorDetails
						.setFloor_type((record.get(2) == null || record.get(2).equals("")) ? "-NA-" : record.get(2));
				floorDetails.setConstructed_area(
						(record.get(3) == null || record.get(3).equals("")) ? "-NA-" : record.get(3));
				floorDetails.setMeasurement_unit(
						(record.get(4) == null || record.get(4).equals("")) ? "-NA-" : record.get(4));
				floorDetails
						.setMarket_value((record.get(5) == null || record.get(5).equals("")) ? "-NA-" : record.get(5));
				deedCertificateOfSaleFloorDetails.add(floorDetails);
			}

		} catch (Exception e) {
			logger.error(e);
		}
		form.setDeedCertificateOfSaleFloorDetails(deedCertificateOfSaleFloorDetails);
	}

	/**
	 * @param form
	 * @param params
	 * @param deedType
	 * @throws Exception
	 */
	public void getKhasraDetails(OldDocumentSearchForm form, Map<String, String> deedRegistrationParamMap,
			String deedType) throws Exception {
		List<List<String>> khasra_records = null;
		List<DeedCertificateOfSaleKhasraDetails> deedCertificateOfSaleKhasraDetails = null;
		try {
			if (deedType.equalsIgnoreCase(CommonConstant.SALE)) {
				khasra_records = oldDocSrchDao.getSaleKhasraDetails(form, deedRegistrationParamMap);
			} else if (deedType.equalsIgnoreCase(CommonConstant.MASTER)) {
				khasra_records = oldDocSrchDao.getMasterKhasraDetails(form, deedRegistrationParamMap);
			} else if (deedType.equalsIgnoreCase(CommonConstant.EXCHANGE)) {
				khasra_records = oldDocSrchDao.getExchangeKhasraDetails(form, deedRegistrationParamMap);
			}
			deedCertificateOfSaleKhasraDetails = new ArrayList<DeedCertificateOfSaleKhasraDetails>();
			for (List<String> record : khasra_records) {
				DeedCertificateOfSaleKhasraDetails khasraDetails = new DeedCertificateOfSaleKhasraDetails();
				khasraDetails.setId(Integer.parseInt(record.get(0)));
				khasraDetails.setArea((record.get(1) == null || record.get(1).equals("")) ? "-NA-" : record.get(1));
				khasraDetails
						.setKhasra_number((record.get(2) == null || record.get(2).equals("")) ? "-NA-" : record.get(2));
				khasraDetails
						.setLand_Record((record.get(3) == null || record.get(3).equals("")) ? "-NA-" : record.get(3));
				khasraDetails.setMeasurement_unit(
						(record.get(4) == null || record.get(4).equals("")) ? "-NA-" : record.get(4));
				khasraDetails.setProperty_number(
						(record.get(5) == null || record.get(5).equals("")) ? "-NA-" : record.get(5));
				khasraDetails.setParty_id((record.get(6) == null || record.get(6).equals("")) ? "-NA-" : record.get(6));
				deedCertificateOfSaleKhasraDetails.add(khasraDetails);
			}

		} catch (Exception e) {
			logger.error(e);
		}
		form.setDeedCertificateOfSaleKhasraDetails(deedCertificateOfSaleKhasraDetails);
	}

	/**
	 * @param form
	 * @param params
	 * @param deedType
	 * @throws Exception
	 */
	public void getStampDetails(OldDocumentSearchForm form, Map<String, String> deedRegistrationParamMap,
			String deedType) throws Exception {
		List<List<String>> stamp_records = null;
		List<DeedCertificateOfSaleStampDetails> deedCertificateOfSaleStampDetails = null;
		try {
			if (deedType.equalsIgnoreCase(CommonConstant.SALE)) {
				stamp_records = oldDocSrchDao.getSaleStampDetails(form, deedRegistrationParamMap);
			} else if (deedType.equalsIgnoreCase(CommonConstant.MASTER)) {

				stamp_records = oldDocSrchDao.getMasterStampDetails(form, deedRegistrationParamMap);
			} else if (deedType.equalsIgnoreCase(CommonConstant.EXCHANGE)) {
				stamp_records = oldDocSrchDao.getExchangeStampDetails(form, deedRegistrationParamMap);
			}
			deedCertificateOfSaleStampDetails = new ArrayList<DeedCertificateOfSaleStampDetails>();
			for (List<String> record : stamp_records) {
				DeedCertificateOfSaleStampDetails stampDetails = new DeedCertificateOfSaleStampDetails();
				stampDetails.setId(Integer.parseInt(record.get(0)));
				stampDetails
						.setStamp_number((record.get(1) == null || record.get(1).equals("")) ? "-NA-" : record.get(1));
				deedCertificateOfSaleStampDetails.add(stampDetails);
			}

		} catch (Exception e) {
			logger.error(e);
		}
		form.setDeedCertificateOfSaleStampDetails(deedCertificateOfSaleStampDetails);
	}

	/**
	 * @param paymentflag
	 * @param form
	 * @param userId
	 * @param functionId
	 * @param refID
	 * @return
	 * @throws Exception
	 */
	public String storePaymentTxnDetails(String paymentflag, OldDocumentSearchForm form, String userId,
			String functionId, String refID) throws Exception {
		String txnId = null;
		try {
			txnId = oldDocSrchDao.storePaymentTxnDetails(paymentflag, form, userId, functionId, refID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return txnId;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getSaleDeedDetailsA(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> sale_details = null;
		try {
			sale_details = oldDocSrchDao.getSaleDetailsA(form, params);
		} catch (Exception e) {
			logger.error(e);
		}
		return sale_details;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getExchangeDeedDetailsA(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> sale_details = null;
		try {
			sale_details = oldDocSrchDao.getExchangeDetailsA(form, params);
		} catch (Exception e) {
			logger.error(e);
		}
		return sale_details;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getMasterDeedDetailsA(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> sale_details = null;
		try {
			sale_details = oldDocSrchDao.getMasterDetailsA(form, params);
		} catch (Exception e) {
			logger.error(e);
		}
		return sale_details;
	}

	/**
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getOldSrchAResult(List<String> params) throws Exception {
		List<List<String>> records = null;
		try {
			records = oldDocSrchDao.getOldSrchAResult(params);
		} catch (Exception e) {
			logger.error(e);
		}
		return records;
	}

	/**
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getOldSrchBResult(List<String> params) throws Exception {
		List<List<String>> records = null;
		try {
			records = oldDocSrchDao.getOldSrchBResult(params);
		} catch (Exception e) {
			logger.error(e);
		}
		return records;
	}

	/**
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getOldSearchATxnDetails(List<String> params) throws Exception {
		List<List<String>> records = null;
		try {
			records = oldDocSrchDao.getOldSearchATxnDetails(params);
		} catch (Exception e) {
			logger.error(e);
		}
		return records;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getSaleDeedDetailsB(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> sale_details = null;
		try {
			sale_details = oldDocSrchDao.getSaleDetailsB(form, params);
		} catch (Exception e) {
			logger.error(e);
		}
		return sale_details;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getExchangeDeedDetailsB(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> sale_details = null;
		try {
			sale_details = oldDocSrchDao.getExchangeDetailsB(form, params);
		} catch (Exception e) {
			logger.error(e);
		}
		return sale_details;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getMasterDeedDetailsB(OldDocumentSearchForm form, Map<String, String> params)
			throws Exception {
		List<List<String>> sale_details = null;
		try {
			sale_details = oldDocSrchDao.getMasterDetailsB(form, params);
		} catch (Exception e) {
			logger.error(e);
		}
		return sale_details;
	}

	/**
	 * @param form
	 * @param paramMap
	 * @param transactionId
	 * @return
	 * @throws Exception
	 */
	public boolean updateOldSearchATxnDetails(OldDocumentSearchForm form, Map<String, String> paramMap,
			String transactionId) throws Exception {
		return oldDocSrchDao.updateOldSearchATxnDetails(form, paramMap, transactionId);
	}
}
