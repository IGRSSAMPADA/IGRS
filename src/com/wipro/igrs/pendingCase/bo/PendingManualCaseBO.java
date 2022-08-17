package com.wipro.igrs.pendingCase.bo;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.caseMonitoring.bd.CaseMonHistoryBD;
import com.wipro.igrs.caseMonitoring.bo.CaseMonBO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.pendingCase.bd.PendingManualCaseBD;
import com.wipro.igrs.pendingCase.dao.PendingManualCaseDAO;
import com.wipro.igrs.pendingCase.dto.PendingManualCaseDTO;
import com.wipro.igrs.pendingCase.form.PendingManualCaseForm;
import com.wipro.igrs.report.bo.ReportBO;
import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.report.dto.MISReportDTO;

public class PendingManualCaseBO {
	public PendingManualCaseBO() {
	}

	PendingManualCaseBD caseBD=new PendingManualCaseBD();
	PendingManualCaseDAO caseDao = new PendingManualCaseDAO();

	private Logger logger = (Logger) Logger.getLogger(PendingManualCaseBO.class);
	
	public ArrayList getRevenueHeadList(String language)
	{
		ArrayList list=new ArrayList();
		list=caseBD.getRevenueHeadList(language);
		return list;
		
	}

	public ArrayList getCaseSectionList(String language , String revHeadId)
	{
		ArrayList list=new ArrayList();
		list=caseBD.getCaseSectionList(language, revHeadId);
		return list;
		
	}
	
	public boolean saveCaseData(PendingManualCaseForm form)
	throws Exception {
    return caseBD.saveCaseData(form);
   
    }
	public boolean saveEditCaseData(PendingManualCaseForm form)
	throws Exception {
    return caseBD.saveEditCaseData(form);
   
    }
	
	public String getRevenueHeadName(String revHeadId)throws Exception{
		   return caseBD.getRevenueHeadName(revHeadId);
	}

	
	public String getSectionHeadName(String sectionHeadId)throws Exception{
		   return caseBD.getSectionHeadName(sectionHeadId);
	}
	
	public ArrayList getPendingCases(String userId ,PendingManualCaseForm form, String language, String userOffice)
	{
		ArrayList list=new ArrayList();
		list=caseBD.getPendingCases(userId, form,language, userOffice);
		return list;
	}

	public void getEditDetails( String hiddenCaseNumber, PendingManualCaseForm caseForm, String language) throws Exception {
	
		caseBD.getEditDetails( hiddenCaseNumber, caseForm, language);
		
		//return caseBD.getEditDetails( hiddenCaseNumber);
		// TODO Auto-generated method stub
		
	}

	public void getEditDetailsDisplay( String hiddenCaseNumber, PendingManualCaseForm caseForm, String language) throws Exception {
		
		caseBD.getEditDetailsDisplay( hiddenCaseNumber, caseForm, language);
		
		//return caseBD.getEditDetails( hiddenCaseNumber);
		// TODO Auto-generated method stub
		
	}
	public ArrayList getDistrictDetails(String language,String userOffice, String offcID) {
		//ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			// IGRSCommon common = new IGRSCommon();
			ArrayList resultList = caseDao.getDistrict("1",userOffice, offcID);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					PendingManualCaseDTO dto = new PendingManualCaseDTO();
					dto.setDistrictId((String) list.get(0));
					if ("en".equalsIgnoreCase(language))
						dto.setDistrictName((String) list.get(1));
					else
						dto.setDistrictName((String) list.get(2));
					returnList.add(dto);
					logger.debug(dto.getDistrictId() + ":"
							+ dto.getDistrictName());
				}
			}
		} catch (Exception err) {

			logger.debug("In getDistrictDetails Exception " + err);
		}
		return returnList;
	}

	public String getDistrictName(String distID, String language) throws Exception {
		
		return caseDao.getDistrictName(distID,language);
	}

	public String getOfficeType(String officeId) {

		String officeType = null;
		officeType = caseBD.getOfficeType(officeId);

		return officeType;
	}


	public String getDistrictId(String officeId) {
		// TODO Auto-generated method stub
		return caseBD.getDistrictId(officeId);
	}

	public String getType(String officeType) {
		// TODO Auto-generated method stub

		return caseBD.getType(officeType);
	}

		public String getDIGZone(String officeId) {
	
		return caseBD.getDIGZoneBo(officeId);

	}

			public ArrayList getDistDIGList(String zoneId,String language) {

		//ReportBO reportBO = new ReportBO();

		ArrayList ret = caseBD.getDistDIGListBO(zoneId,language);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				PendingManualCaseDTO dto = new PendingManualCaseDTO();
				dto.setDistrictName((String) lst.get(0));
				dto.setDistrictId((String) lst.get(1));
				list.add(dto);
			}
			logger.info("hetDistric in manual cases  " + list);
		}
		return list;

	}

		public ArrayList getDistList(String officeId) {

		//ReportBO reportBO = new ReportBO();
		ArrayList ret = new ArrayList();

		ret = caseBD.getDistListBO(officeId);

		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				//MISReportDTO dto = new MISReportDTO();
				PendingManualCaseDTO dto = new PendingManualCaseDTO();
				dto.setDistrictId((String) lst.get(0));
				dto.setDistrictName((String) lst.get(1));
				dto.setZoneId((String) lst.get(2));
				list.add(dto);
			}
			logger.info("SpotInspBD----getDistrcit  " + list);
		}
		return list;

	}

		public boolean getSearchReportResult(String selectedDistrict,
				String selectedRevId, String selectedSectionId,
				String selectedCaseType, String durationFrom,String durationTo,
				String selectedCaseStatus, String selectedPaymentType, String selectedDRofficeId) {
			// TODO Auto-generated method stub
			
			return	caseBD.getSearchReportResult(selectedDistrict,selectedRevId,selectedSectionId,selectedCaseType,durationFrom,durationTo,selectedCaseStatus,selectedPaymentType,selectedDRofficeId);
			
		}
		
		
		public ArrayList getDRofficeName(String language, String districtID) {
			//ReportDAO dao = new ReportDAO();
			ArrayList returnList = new ArrayList();
			try {
				// IGRSCommon common = new IGRSCommon();
				ArrayList resultList = caseDao.getDrOfficeName(language,districtID);
				if (resultList != null) {
					for (int i = 0; i < resultList.size(); i++) {
						logger.debug("in bd for loop start:-" + resultList.get(i));
						ArrayList list = (ArrayList) resultList.get(i);
						PendingManualCaseDTO dto = new PendingManualCaseDTO();
						dto.setDrOfficeId((String) list.get(0));
						if ("en".equalsIgnoreCase(language))
							dto.setDrOfficeName((String) list.get(1));
						else
							dto.setDrOfficeName((String) list.get(2));
						returnList.add(dto);
						
					}
				}
			} catch (Exception err) {

				logger.debug("In getDistrictDetails Exception " + err);
			}
			return returnList;
		}
}
