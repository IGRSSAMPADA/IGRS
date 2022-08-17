package com.wipro.igrs.documentsearch.bd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.documentsearch.bo.OldDocumentSearchBO;
import com.wipro.igrs.documentsearch.constant.CommonConstant;
import com.wipro.igrs.documentsearch.dto.DistrictDetailsDTO;
import com.wipro.igrs.documentsearch.dto.OldDocSearchDashboardDTO;
import com.wipro.igrs.documentsearch.dto.OldRegistrationMap;
import com.wipro.igrs.documentsearch.dto.SRONameDetailsDTO;
import com.wipro.igrs.documentsearch.form.OldDocumentSearchForm;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */
public class OldDocumentSearchBD {

	OldDocumentSearchBO oldDocSrchBo = new OldDocumentSearchBO();

	Logger logger = (Logger) Logger.getLogger(OldDocumentSearchBD.class);

	/**
	 * @return
	 * @throws Exception
	 */
	public List<DistrictDetailsDTO> getDistrictList() throws Exception {
		List<DistrictDetailsDTO> districList = new ArrayList<DistrictDetailsDTO>();
		List<String> temp = null;
		try {
			temp = oldDocSrchBo.getDistrictList();
			for (String district : temp) {
				DistrictDetailsDTO districtDetailsDTO = new DistrictDetailsDTO();
				districtDetailsDTO.setName(district);
				districList.add(districtDetailsDTO);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return districList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<SRONameDetailsDTO> getSroNameList() throws Exception {
		List<SRONameDetailsDTO> sroNameList = new ArrayList<SRONameDetailsDTO>();
		List<String> temp = null;
		try {
			temp = oldDocSrchBo.getSroNameList();
			for (String sro : temp) {
				SRONameDetailsDTO sroNameDetailsDTO = new SRONameDetailsDTO();
				sroNameDetailsDTO.setName(sro);
				sroNameList.add(sroNameDetailsDTO);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return sroNameList;
	}

	/**
	 * @param form
	 * @param deedRegistrationParamMap
	 * @throws Exception
	 */
	public void getDeedDetails(OldDocumentSearchForm form, Map<String, String> deedRegistrationParamMap)
			throws Exception {
		String deedType = "";
		try {
			Set<Map.Entry<String, String>> entrySet = deedRegistrationParamMap.entrySet();
			Iterator<Entry<String, String>> entrySetIterator = entrySet.iterator();
			while (entrySetIterator.hasNext()) {
				Entry<String, String> entry = entrySetIterator.next();
				String key = entry.getKey().trim();
				if (key.equalsIgnoreCase("type")) {
					deedType = entry.getValue();
				}
				if (key.equalsIgnoreCase("registration_number")) {
					form.setRegistration_number(entry.getValue());
				}
				if (key.equalsIgnoreCase("district")) {
					form.setDistrict(entry.getValue());
				}
				if (key.equalsIgnoreCase("sro_name")) {
					form.setSro_name(entry.getValue());
				}
				if (key.equalsIgnoreCase("volume_number")) {
					form.setVolume_number(entry.getValue());
				}
				if (key.equalsIgnoreCase("document_number")) {
					form.setDocument_number(entry.getValue());
				}

				if (key.equalsIgnoreCase("book_number")) {
					form.setBook_number(entry.getValue());
				}
				if (key.equalsIgnoreCase("name_of_sr")) {
					form.setName_of_sr(entry.getValue());
				}
				if (key.equalsIgnoreCase("date_of_registration")) {
					form.setDate_of_registration(entry.getValue());
				}
				if (key.equalsIgnoreCase("registration_number")) {
					form.setRegistration_number(entry.getValue());
				}
			}
			deedRegistrationParamMap.remove("type");
			oldDocSrchBo.getBuyerSellerDetails(form, deedRegistrationParamMap, deedType);
			oldDocSrchBo.getPropertyDetails(form, deedRegistrationParamMap, deedType);
			oldDocSrchBo.getFloorDetails(form, deedRegistrationParamMap, deedType);
			oldDocSrchBo.getKhasraDetails(form, deedRegistrationParamMap, deedType);
			oldDocSrchBo.getStampDetails(form, deedRegistrationParamMap, deedType);

		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @param form
	 * @return
	 */
	public Map<String, String> getParams(OldDocumentSearchForm form) {
		Map<String, String> params = new HashMap<String, String>();
		if (!(form.getDistrict() == "" || form.getDistrict() == null))
			params.put("DISTRICT", form.getDistrict());
		if (!(form.getSro_name() == "" || form.getSro_name() == null))
			params.put("SRO_NAME", form.getSro_name());
		if (!(form.getBook_number() == "" || form.getBook_number() == null))
			params.put("BOOK_NUMBER", form.getBook_number());
		if (!(form.getVolume_number() == "" || form.getVolume_number() == null))
			params.put("VOLUME_NUMBER", form.getVolume_number());
		if (!(form.getDocument_number() == "" || form.getDocument_number() == null))
			params.put("DOCUMENT_NUMBER", form.getDocument_number());
		if (!(form.getDate_of_registration() == "" || form.getDate_of_registration() == null))
			params.put("DATE_OF_REGISTRATION", dateConverter(form.getDate_of_registration()));
		if (!(form.getName_of_sr() == "" || form.getName_of_sr() == null)) {
			if (!(form.getName_of_sr().equalsIgnoreCase(""))) {
				params.put("NAME_OF_SR", form.getName_of_sr());
			}
		}
		if (!(form.getToDate() == "" || form.getToDate() == null))
			params.put("TO_DATE", form.getToDate());
		if (!(form.getFromDate() == "" || form.getFromDate() == null))
			params.put("FROM_DATE", form.getFromDate());
		if (!(form.getOrganisationName() == "" || form.getOrganisationName() == null)) {
			if (!(form.getOrganisationName().equalsIgnoreCase(""))) {
				params.put("ORGANIGATION", form.getOrganisationName());
			}
		}
		if (!(form.getPartyName() == "" || form.getPartyName() == null)) {
			if (!(form.getPartyName().equalsIgnoreCase(""))) {
				params.put("PARTY_NAME", form.getPartyName());
			}
		}
		if (!(form.getWardNumber() == "" || form.getWardNumber() == null)) {
			if (!(form.getWardNumber().equalsIgnoreCase(""))) {
				params.put("PROPERTY_WARD_NUMBER", form.getWardNumber());
			}
		}
		if (!(form.getKhasraNumber() == "" || form.getKhasraNumber() == null)) {
			if (!(form.getKhasraNumber().equalsIgnoreCase(""))) {
				params.put("KHASRA_NUMBER", form.getKhasraNumber());
			}
		}
		return params;
	}

	/**
	 * @param form
	 * @return
	 */
	public Map<String, String> getDeedRegistrationParamMap(OldDocumentSearchForm form) {
		String[] registrationMapObject = form.getRadioButton().split(",");
		Map<String, String> registrationMap = new HashMap<String, String>();
		for (String element : registrationMapObject) {
			int index = element.indexOf("=");
			registrationMap.put(element.substring(0, index).toString(), element.substring(index + 1).toString());
		}
		return registrationMap;
	}

	/**
	 * @param funId
	 * @param serviceId
	 * @param userTypeId
	 * @return
	 * @throws Exception
	 */
	public String getOthersFeeDuty(String funId, String serviceId, String userTypeId) throws Exception {
		String totalFee = "";
		try {
			IGRSCommon common = new IGRSCommon();
			totalFee = common.getOthersFeeDutyNew(funId, serviceId, userTypeId);
		} catch (Exception e) {
			logger.error(e);
		}
		return totalFee;
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
		String docTxnId = null;
		try {
			docTxnId = oldDocSrchBo.storePaymentTxnDetails(paymentflag, form, userId, functionId, refID);
		} catch (Exception e) {
			logger.error(e);
		}
		return docTxnId;
	}

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<OldDocSearchDashboardDTO> getOldSrchAResult(String userId) throws Exception {
		List<String> params = new ArrayList<String>();
		List<OldDocSearchDashboardDTO> result = new ArrayList<OldDocSearchDashboardDTO>();
		List<List<String>> records = null;
		try {
			params.add(userId);
			records = oldDocSrchBo.getOldSrchAResult(params);
			if (records != null && !records.isEmpty()) {
				for (List<String> rec : records) {
					OldDocSearchDashboardDTO oldDocSearchDashboardDTO = new OldDocSearchDashboardDTO();
					oldDocSearchDashboardDTO.setReferenceNumber(rec.get(0));
					oldDocSearchDashboardDTO.setOldRegistrationNumber(rec.get(1));
					oldDocSearchDashboardDTO.setDateOfCreation(rec.get(2));
					result.add(oldDocSearchDashboardDTO);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<OldDocSearchDashboardDTO> getOldSrchBResult(String userId) throws Exception {
		List<String> params = new ArrayList<String>();
		List<OldDocSearchDashboardDTO> result = new ArrayList<OldDocSearchDashboardDTO>();
		List<List<String>> records = null;
		try {
			params.add(userId);
			records = oldDocSrchBo.getOldSrchBResult(params);
			if (records != null && !records.isEmpty()) {
				for (List<String> rec : records) {
					OldDocSearchDashboardDTO oldDocSearchDashboardDTO = new OldDocSearchDashboardDTO();
					oldDocSearchDashboardDTO.setReferenceNumber(rec.get(0));
					oldDocSearchDashboardDTO.setOldRegistrationNumber(rec.get(1));
					oldDocSearchDashboardDTO.setDateOfCreation(rec.get(2));
					result.add(oldDocSearchDashboardDTO);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	/**
	 * @param form
	 * @param referenceId
	 * @throws Exception
	 */
	public void getOldSearchATxnDetails(OldDocumentSearchForm form, String referenceId, String userId)
			throws Exception {
		List<List<String>> records = null;
		List<String> params = new ArrayList<String>();
		params.add(referenceId);
		params.add(userId);
		params.add(form.getFunctionId());
		try {
			records = oldDocSrchBo.getOldSearchATxnDetails(params);
			if (records != null && !records.isEmpty()) {
				for (List<String> rec : records) {
					form.setDistrict(rec.get(0));
					form.setSro_name(rec.get(1));
					// form.setName_of_sr((rec.get(2) == null ||
					// rec.get(2).equals("")) ? "" : rec.get(2));
					form.setBook_number(rec.get(3));
					form.setVolume_number(rec.get(4));
					form.setDocument_number(rec.get(5));
					form.setDate_of_registration(rec.get(6));
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @param dateInString
	 * @return
	 */
	public String dateConverter(String dateInString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MMM-yy");
		String newFormat = "";
		try {
			Date date = dateFormat.parse(dateInString);
			newFormat = newDateFormat.format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newFormat;
	}

	/**
	 * @param form
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<OldRegistrationMap> getDeedRegistrationMap(OldDocumentSearchForm form, Map<String, String> params,
			String searchType) throws Exception {
		List<OldRegistrationMap> oldRegistrationList = null;
		List<List<String>> saleRegistrationNumber = null;
		List<List<String>> exchangeRegistrationNumber = null;
		List<List<String>> masterRegistrationNumber = null;
		oldRegistrationList = new ArrayList<OldRegistrationMap>();
		try {
			if (searchType.equals("A")) {
				saleRegistrationNumber = oldDocSrchBo.getSaleDeedDetailsA(form, params);
			}
			if (searchType.equals("B")) {
				saleRegistrationNumber = oldDocSrchBo.getSaleDeedDetailsB(form, params);
			}
			for (List<String> record : saleRegistrationNumber) {
				if (record != null) {
					OldRegistrationMap oldRegistrationMap = new OldRegistrationMap();
					oldRegistrationMap.setRegistration_number(record.get(0));
					oldRegistrationMap.setSro_name(record.get(1));
					oldRegistrationMap.setDistrict(record.get(2));
					oldRegistrationMap.setBook_number(record.get(3));
					oldRegistrationMap.setVolume_number(record.get(4));
					oldRegistrationMap.setDocument_number(record.get(5));
					oldRegistrationMap.setDate_of_registration(record.get(6));
					// oldRegistrationMap.setName_of_sr((record.get(7) == null
					// || record.get(7).equals("")) ? "-NA-" : record.get(7));
					oldRegistrationMap.setType(CommonConstant.SALE);
					oldRegistrationMap.setOldRegistrationMapObject(oldRegistrationMap.toString());
					oldRegistrationList.add(oldRegistrationMap);
				}
			}
			if (searchType.equals("A")) {
				exchangeRegistrationNumber = oldDocSrchBo.getExchangeDeedDetailsA(form, params);
			}
			if (searchType.equals("B")) {
				exchangeRegistrationNumber = oldDocSrchBo.getExchangeDeedDetailsB(form, params);
			}
			for (List<String> record : exchangeRegistrationNumber) {
				if (record != null) {
					OldRegistrationMap oldRegistrationMap = new OldRegistrationMap();
					oldRegistrationMap.setRegistration_number(record.get(0));
					oldRegistrationMap.setSro_name(record.get(1));
					oldRegistrationMap.setDistrict(record.get(2));
					oldRegistrationMap.setBook_number(record.get(3));
					oldRegistrationMap.setVolume_number(record.get(4));
					oldRegistrationMap.setDocument_number(record.get(5));
					oldRegistrationMap.setDate_of_registration(record.get(6));
					oldRegistrationMap.setType(CommonConstant.EXCHANGE);
					oldRegistrationMap.setOldRegistrationMapObject(oldRegistrationMap.toString());
					oldRegistrationList.add(oldRegistrationMap);
				}
			}
			if (searchType.equals("A")) {
				masterRegistrationNumber = oldDocSrchBo.getMasterDeedDetailsA(form, params);
			}
			if (searchType.equals("B")) {
				masterRegistrationNumber = oldDocSrchBo.getMasterDeedDetailsB(form, params);
			}
			for (List<String> record : masterRegistrationNumber) {
				if (record != null) {
					OldRegistrationMap oldRegistrationMap = new OldRegistrationMap();
					oldRegistrationMap.setRegistration_number(record.get(0));
					oldRegistrationMap.setSro_name(record.get(1));
					oldRegistrationMap.setDistrict(record.get(2));
					oldRegistrationMap.setBook_number(record.get(3));
					oldRegistrationMap.setVolume_number(record.get(4));
					oldRegistrationMap.setDocument_number(record.get(5));
					oldRegistrationMap.setDate_of_registration(record.get(6));
					oldRegistrationMap.setType(CommonConstant.MASTER);
					oldRegistrationMap.setOldRegistrationMapObject(oldRegistrationMap.toString());
					oldRegistrationList.add(oldRegistrationMap);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return oldRegistrationList;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean updateOldSearchATxnDetails(OldDocumentSearchForm form) throws Exception {
		String filePath = form.getFilePath();
		String fileName = form.getUploadDocumentStack();
		String reason = form.getReason();
		String transactionId = form.getReferenceId();
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!(filePath == "" || filePath == null)) {
			{
				if (!(filePath.equalsIgnoreCase(""))) {
					paramMap.put("DOC_PATH", filePath);
				}
			}
		}
		if (!(fileName == "" || fileName == null)) {
			{
				if (!(fileName.equalsIgnoreCase(""))) {
					paramMap.put("DOC_NAME", fileName);
				}
			}
		}
		if (!(reason == "" || reason == null)) {
			{
				if (!(reason.equalsIgnoreCase(""))) {
					paramMap.put("OFFICIAL_SEARCH_PURPOSE", reason);
				}
			}
		}
		return oldDocSrchBo.updateOldSearchATxnDetails(form, paramMap, transactionId);
	}
}
