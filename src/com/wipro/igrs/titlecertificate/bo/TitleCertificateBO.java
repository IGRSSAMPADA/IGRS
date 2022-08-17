package com.wipro.igrs.titlecertificate.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.titlecertificate.bd.TitleCertificateBD;
import com.wipro.igrs.titlecertificate.dto.TitleCertificateDTO;

public class TitleCertificateBO {
	private  Logger logger = 
		(Logger) Logger.getLogger(TitleCertificateBO.class);
	
	public ArrayList getTitleCertificateOwnerDetails(String regID) {
		ArrayList list = new ArrayList();
		try {
			TitleCertificateBD bd = new TitleCertificateBD();
			ArrayList retlist = 
				bd.getTitleCertificateOwnerDetails(regID);
			
			if(retlist!=null) {
				for(int i=0;i<retlist.size();i++) {
					logger.debug("inside for");
					ArrayList returnList = (ArrayList) retlist.get(i);
					TitleCertificateDTO dto = new TitleCertificateDTO();
					logger.debug("Name:-"+(String)returnList.get(0));
					dto.setProprietorName((String)returnList.get(0));
					list.add(dto);
				}
			}
			
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public TitleCertificateDTO getTitleCertificatePropertyDetails(String regID) {
		ArrayList list = new ArrayList();
		TitleCertificateDTO dto = new TitleCertificateDTO();
		try {
			TitleCertificateBD bd = new TitleCertificateBD();
			list = 
				bd.getTitleCertificatePropertyDetails(regID);
			if(list!=null) {
				ArrayList listReturn = (ArrayList) list.get(0);
				if(listReturn!=null) {
						dto.setTitleNumber((String)listReturn.get(0));
						dto.setTitleSubject((String)listReturn.get(1));
						dto.setTitleType((String)listReturn.get(2));
						dto.setLocationPlan((String)listReturn.get(3));
						dto.setChangeEntryNumber((String)listReturn.get(4));
						dto.setChangeSpecification((String)listReturn.get(5));
						dto.setRegistrationDate((String)listReturn.get(6));
						dto.setBurdenEntryNumber((String)listReturn.get(7));
						
						dto.setRegistrationNumber((String)listReturn.get(8));
						dto.setRegistrationID((String)listReturn.get(9));
						dto.setBurdenSpecification((String)listReturn.get(10));
						dto.setPropertyType((String)listReturn.get(11));
						dto.setDistrict((String)listReturn.get(12));
						dto.setTehsil((String)listReturn.get(13));
						dto.setWardNumber((String)listReturn.get(14));
						dto.setMohalla((String)listReturn.get(15));
						dto.setArea((String)listReturn.get(16));
						dto.setMunicipalBody((String)listReturn.get(17));
						dto.setVikasKhand((String)listReturn.get(18));
						dto.setRiCircle((String)listReturn.get(19));
						dto.setLayoutDetail((String)listReturn.get(20));
						dto.setKhasraNumber((String)listReturn.get(21));
						dto.setNazoolSheetNumber((String)listReturn.get(22));
						dto.setAddress((String)listReturn.get(23));
						dto.setEastBoundry((String)listReturn.get(24));
						dto.setWestBoundry((String)listReturn.get(25));
						dto.setNorthBoundry((String)listReturn.get(26));
						dto.setSouthBoundry((String)listReturn.get(27));
						dto.setTotalSQM((String)listReturn.get(28));
						dto.setMarketValue((String)listReturn.get(29));
						dto.setConsiderationAmount((String)listReturn.get(30));
						dto.setWard("W".equals((String)listReturn.get(31)) ? 
								"Ward" : "Patwari");
						dto.setUsageType((String)listReturn.get(32));
						dto.setCeilingType((String)listReturn.get(33));
				}
				
			}
			
		}catch(Exception x) {
			logger.debug(x);
		}
		return dto;
	}
}
