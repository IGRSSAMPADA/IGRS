package com.wipro.igrs.etaal.bd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.etaal.dao.EtaalDAO;
import com.wipro.igrs.etaal.dto.EtransactionResponse;
import com.wipro.igrs.etaal.dto.ResponseDTO;

public class EtaalBD {
	private Logger				logger			= (Logger) Logger.getLogger(EtaalBD.class);
	private List<ResponseDTO>	responseList	= null;

	public List<ResponseDTO> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<ResponseDTO> responseList) {
		this.responseList = responseList;
	}

	public boolean isValidUserCredentials(String username, String password) throws Exception{
		PropertiesFileReader reader = PropertiesFileReader.getInstance("resources.etaal");
		 if(StringUtils.equals(reader.getValue("username"), username )&& StringUtils.equals(reader.getValue("password"), password )){
			 return true;
		 }
		return false;
	}

	public EtransactionResponse getDataCount(String date) throws Exception {
		EtransactionResponse etranxResponse = new EtransactionResponse();
		try {
			responseList = new ArrayList<ResponseDTO>();
			EtaalDAO etaalDAO = new EtaalDAO();

			ArrayList<ResponseDTO> eTaalDetails = etaalDAO.checkEtaalDataForSameDate(date);
			if (eTaalDetails != null && !eTaalDetails.isEmpty()) {
				etranxResponse.setResponse(eTaalDetails);
				return etranxResponse;
			}

			populateResponse("A019371108634", etaalDAO.getEstampCount(date));

			populateResponse("A019371208635", etaalDAO.getRegistrationCount(date));

			populateResponse("C019371308636", etaalDAO.getOnlinePaymentEstampCount(date));

			populateResponse("A019371408637", etaalDAO.getOnlinePaymentRegistartionFeeCount(date));

			populateResponse("A019371508638", etaalDAO.getDeedPreparationCount(date));

			populateResponse("A019371608639", etaalDAO.getEregistrationBySubRegistrarsCount(date));

			populateResponse("A019371708640", etaalDAO.getOnlineDocSearchCount(date));

			populateResponse("A019371808641", etaalDAO.getRevenueFromStampDuty(date));

			populateResponse("A019371908642", etaalDAO.getRevenueFromRegistration(date));

			populateResponse("D019368508643", etaalDAO.getPropertyValuationCount(date));

			populateResponse("D019372008644", etaalDAO.getDutyCalculationCount(date));

			populateResponse("A019372108645", etaalDAO.getDownloadedEregisteredDocCount(date));

			populateResponse("A019372208646", etaalDAO.getPINGenerationCount(date));

			populateResponse("D019372308647", etaalDAO.getActiveServiceProviderCount(date));

			populateResponse("A019372408648", etaalDAO.getSlotBookCount(date));

			etranxResponse.setResponse(responseList);

			if (responseList != null && responseList.size() != 0) {
				etaalDAO.insertEtransactionData(date, responseList);
			}
		} catch (Exception ex) {
			logger.error("Exception in EtaalBD:" + ex);
		}
		return etranxResponse;
	}

	private void populateResponse(String serviceCode, String count) {
		if (StringUtils.isBlank(count)) {
			count = "0";
		}
		ResponseDTO resp = new ResponseDTO();
		resp.setServiceCode(serviceCode);
		resp.setServiceCount(count);
		responseList.add(resp);
	}

}
