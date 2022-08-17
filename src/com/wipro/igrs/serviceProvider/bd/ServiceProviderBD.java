package com.wipro.igrs.serviceProvider.bd;
/**
 * ===========================================================================
 * File           :   ServiceProviderBD.java
 * Description    :   Represents the Business Delegate Class

 * Author         :   Lavi Tripathi
 * Created Date   :   July 22, 2013

 * ===========================================================================
 */
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.serviceProvider.dao.ServiceProviderDAO;
import com.wipro.igrs.serviceProvider.dto.ServiceProviderDTO;
import com.wipro.igrs.serviceProvider.form.ServiceProviderForm;
/**
 * 
 * @author Lavi Tripathi
 *
 */

public class ServiceProviderBD {
	
	private Logger logger = 
		(Logger) Logger.getLogger(ServiceProviderBD.class);
	
	ServiceProviderDAO objServiceProviderDAO = new ServiceProviderDAO();
	
	
	public boolean insertCriteria (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertCriteria(objServiceProviderForm);
	}
	
	public ArrayList getCriterias(String value)
	{
		return objServiceProviderDAO.getCriterias(value);
	}
	
	public boolean deleteCriteria (String id) throws Exception
	{
		return objServiceProviderDAO.deleteCriteria(id);
	}
	
	public boolean editCriteria (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.editCriteria(objServiceProviderForm);
	}
	
	public boolean insertTandC (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertTandC(objServiceProviderForm);
	}
	
	public ArrayList getTC(String value)
	{
		return objServiceProviderDAO.getTC(value);
	}
	
	public boolean deleteTC (String id) throws Exception
	{
		return objServiceProviderDAO.deleteTC(id);
	}
	
	public boolean editTC (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.editTC(objServiceProviderForm);
	}
	
	public ArrayList getSPDetails(String userID,String lang) throws Exception
	{
		return objServiceProviderDAO.getSPDetails(userID,lang);
	}
	
	public ArrayList getSpType(String lang) throws Exception
	{
		return objServiceProviderDAO.getSpType(lang);
	}
	
	public ArrayList getDistrict(String lang) throws Exception
	{
		return objServiceProviderDAO.getDistrict(lang);
	}
	
	public ArrayList getTehsil(String disttId,String lang) throws Exception
	{
		return objServiceProviderDAO.getTehsil(disttId,lang);
	}
	
	public ArrayList getWardPatwari(String tehsilId,String lang) throws Exception
	{
		return objServiceProviderDAO.getWardPatwari(tehsilId,lang);
	}
	
	public ArrayList getMohallaVillage(String wardId,String lang) throws Exception
	{
		return objServiceProviderDAO.getMohallaVillage(wardId,lang);
	}
	
	public boolean insertSpDetls (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertSpDetls(objServiceProviderForm);
	}
	
	public boolean insertSpDocDetls1 (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertSpDocDetls1(objServiceProviderForm);
	}
	
	public ArrayList getDroDetails(String distt,String lang) throws Exception
	{
		return objServiceProviderDAO.getDroDetails(distt,lang);
	}

	public String getdisttId (String ofcId) throws Exception
	{
		return objServiceProviderDAO.getdisttId(ofcId);
	}
	
	public ArrayList getPendingApps(String distt,String lang)
	{
		return objServiceProviderDAO.getPendingApps(distt,lang);
	}
	
	public ArrayList getPendingAppsSearch1(String distt, String fromDate,String toDate,String lang)
	{
		return objServiceProviderDAO.getPendingAppsSearch1(distt,fromDate,toDate,lang);
	}
	
	public ArrayList getPendingAppsSearch2(String distt, String applctnNumber,String language)
	{
		return objServiceProviderDAO.getPendingAppsSearch2(distt, applctnNumber,language);
	}
	
	public ArrayList getPendingAppsSearch3(String distt, String status,String lang)
	{
		return objServiceProviderDAO.getPendingAppsSearch3(distt, status,lang);
	}
	
	public ArrayList getPendingAppsSearch4(String distt, String fromDate, String toDate, String applctnNumber,String lang)
	{
		return objServiceProviderDAO.getPendingAppsSearch4(distt, fromDate, toDate, applctnNumber,lang);
	}
	
	public ArrayList getPendingAppsSearch5(String distt, String fromDate, String toDate, String status,String lang)
	{
		return objServiceProviderDAO.getPendingAppsSearch5(distt, fromDate, toDate, status,lang);
	}
	
	public ArrayList getPendingAppsSearch6(String distt, String applctnNumber, String status,String lang)
	{
		return objServiceProviderDAO.getPendingAppsSearch6(distt, applctnNumber, status,lang);
	}
	public ArrayList getPendingAppsSearch7(String distt, String fromDate, String toDate,String applctnNumber, String status)
	{
		return objServiceProviderDAO.getPendingAppsSearch7(distt,fromDate,toDate, applctnNumber, status);
	}
	
	public ArrayList getPendingAppsSP(String userId,String lang)
	{
		return objServiceProviderDAO.getPendingAppsSP(userId,lang);
	}
	
	public ArrayList getApplictnStatus(String lang) throws Exception
	{
		return objServiceProviderDAO.getApplictnStatus(lang);
	}
	
	public ArrayList getSPDetails(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		return objServiceProviderDAO.getSPDetails(objServiceProviderForm,lang);
	}
	
	public String getspTypeId (String requestNumber) throws Exception
	{
		return objServiceProviderDAO.getspTypeId(requestNumber);
	}
	
	public ArrayList getspDetls(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		return objServiceProviderDAO.getspDetls(objServiceProviderForm,lang);
	}
	
	public ArrayList getspDocDetls(String requestNumber) throws Exception
	{
		return objServiceProviderDAO.getspDocDetls(requestNumber);
	}
	
	public ArrayList getspDocDetlsDR(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getspDocDetlsDR(objServiceProviderForm);
	}
	
	public String getFees () throws Exception
	{
		return objServiceProviderDAO.getFees();
	}
	
	public boolean insertDRApprovalComments (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertDRApprovalComments(objServiceProviderForm);
	}
	
	public boolean updateSpDocDetls (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.updateSpDocDetls(objServiceProviderForm);
	}
	
	public boolean insertDRCallForPVComments (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertDRCallForPVComments(objServiceProviderForm);
	}
	public boolean insertDRCommentsCancel (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertDRCommentsCancel(objServiceProviderForm);
	}
	public boolean insertDRCallForAddInfoComments (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertDRCallForAddInfoComments(objServiceProviderForm);
	}
	
	public boolean insertDrRejectionComments (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertDrRejectionComments(objServiceProviderForm);
	}
	
	public ArrayList getPendingAppsDIG(String ofcId,String lang)
	{
		return objServiceProviderDAO.getPendingAppsDIG(ofcId,lang);
	}
	
	public ArrayList getPendingAppsSearchDIG1(String ofcId, String fromDate,String toDate)
	{
		return objServiceProviderDAO.getPendingAppsSearchDIG1(ofcId,fromDate,toDate);
	}
	
	public ArrayList getPendingAppsSearchDIG2(String ofcId, String applctnNumber)
	{
		return objServiceProviderDAO.getPendingAppsSearchDIG2(ofcId, applctnNumber);
	}
	
	public ArrayList getPendingAppsSearchDIG3(String ofcId, String fromDate, String toDate, String applctnNumber)
	{
		return objServiceProviderDAO.getPendingAppsSearchDIG3(ofcId, fromDate, toDate, applctnNumber);
	}
	
	public boolean updateStatusOfApplicatnDIG (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.updateStatusOfApplicatnDIG(objServiceProviderForm);
	}
	
	public boolean updateStatusOfApplicatnDR (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.updateStatusOfApplicatnDR(objServiceProviderForm);
	}
	
	public boolean updateSpDetls (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.updateSpDetls(objServiceProviderForm);
	}
	
	public String getdisttId (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getdisttId(objServiceProviderForm);
	}
	//added by shruti
	public String getTehsilId (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getTehsilId(objServiceProviderForm);
	}
	public String getWardId (ServiceProviderForm objServiceProviderForm,String subareaId,String tehsilId) throws Exception
	{
		return objServiceProviderDAO.getWardId(objServiceProviderForm,subareaId,tehsilId);
	}
	public String getMohallaId (ServiceProviderForm objServiceProviderForm,String mappingId) throws Exception
	{
		return objServiceProviderDAO.getMohallaId(objServiceProviderForm,mappingId);
	}
	public String getAreaId (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getAreaId(objServiceProviderForm);
	}
	public String getSubAreaId (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getSubAreaId(objServiceProviderForm);
	}
	//end
	
	public ArrayList getDroDetailsIfAlreadyFiled(ServiceProviderForm objServiceProviderForm,String language) throws Exception
	{
		return objServiceProviderDAO.getDroDetailsIfAlreadyFiled(objServiceProviderForm,language);
	}
	
	public ArrayList getPaymentDetls(ServiceProviderForm objServiceProviderForm,String language) throws Exception
	{
		return objServiceProviderDAO.getPaymentDetls(objServiceProviderForm,language);
	}
	
	public boolean insertPaymentDetls (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertPaymentDetls(objServiceProviderForm);
	}
	
	public boolean updateStatus (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.updateStatus(objServiceProviderForm);
	}
	

	public ArrayList cancelLicenseDashboard(ServiceProviderDTO spDTO,String lang)
	{
		return objServiceProviderDAO.cancelLicenseDashboard(spDTO,lang);
	}
	public String getRequestNumber(String licenseId)
	{
		return objServiceProviderDAO.getRequestNumber(licenseId);
	}

	public ArrayList getPreviousLicenseDetailsEstamp(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getPreviousLicenseDetailsEstamp(objServiceProviderForm);
	}
	
	public ArrayList getPreviousLicenseDetailsOthers(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getPreviousLicenseDetailsOthers(objServiceProviderForm);
	}
	
	public ArrayList getFirstTimeLicenseDetails(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getFirstTimeLicenseDetails(objServiceProviderForm);
	}
	
	public boolean insertLicenseNumber (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertLicenseNumber(objServiceProviderForm);
	}
	
	public ArrayList getDRComments(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getDRComments(objServiceProviderForm);
	}
	public ArrayList getLicenseNumber(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getLicenseNumber(objServiceProviderForm);
	}
	
	public boolean updateStatusToLicenseIssued (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.updateStatusToLicenseIssued(objServiceProviderForm);
	}
	
	public boolean updateLicenseDate(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.updateLicenseDate(objServiceProviderForm);
	}
	
	public ArrayList getPdfDetlsForLicense(ServiceProviderForm objServiceProviderForm,String language) throws Exception
	{
		return objServiceProviderDAO.getPdfDetlsForLicense(objServiceProviderForm,language);
	}
	
	public String getTodaysDate() throws Exception
	{
		return objServiceProviderDAO.getTodaysDate();
	}
	
	public ArrayList getDroDetailsIfAlreadyFiledRenewal(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		return objServiceProviderDAO.getDroDetailsIfAlreadyFiledRenewal(objServiceProviderForm,lang);
	}
	
	public ArrayList getSpTypeRenewal(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		return objServiceProviderDAO.getSpTypeRenewal(objServiceProviderForm,lang);
	}
	
	public ArrayList getspDetlsForRenewal(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getspDetlsForRenewal(objServiceProviderForm);
	}
	
	public ArrayList getLicenseDetails(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getLicenseDetails(objServiceProviderForm);
	}
	
	public boolean renewLicenseNumber (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.renewLicenseNumber(objServiceProviderForm);
	}
	
	public boolean updatePreviousStatusOfLicenseIssued (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.updatePreviousStatusOfLicenseIssued(objServiceProviderForm);
	}
	
	public ArrayList getPdfDetlsForLicenseReprint(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		return objServiceProviderDAO.getPdfDetlsForLicenseReprint(objServiceProviderForm,lang);
	}
	
	public boolean insertAmount (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertAmount(objServiceProviderForm);
	}
	
	public ArrayList getDetlsWhetherPreviousLicenseExprd(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getDetlsWhetherPreviousLicenseExprd(objServiceProviderForm);
	}
	
	public boolean updateExprdStatus (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.updateExprdStatus(objServiceProviderForm);
	}
	
	public ArrayList getPlaceDetls (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getPlaceDetls(objServiceProviderForm);
	}
	
	public ArrayList getPreviousLicenseDetailsEstamp1(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getPreviousLicenseDetailsEstamp1(objServiceProviderForm);
	}
	
	public ArrayList getPreviousLicenseDetailsOthers1(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.getPreviousLicenseDetailsOthers1(objServiceProviderForm);
	}
	
	public ArrayList getArea(String language) throws Exception
	{
		return objServiceProviderDAO.getArea(language);
	}
	public ArrayList getSubArea(String language,String areaId,String tehsilId,String urbanFlag) throws Exception
	{
		return objServiceProviderDAO.getSubArea(language,areaId,tehsilId,urbanFlag);
	}
	public ArrayList getWardPatwari(String language,String subAreaId,String tehsilId) throws Exception
	{
		return objServiceProviderDAO.getWardPatwari(language, subAreaId, tehsilId);
	}
	public ArrayList getColonyName(String language,String wardPatwariId) throws Exception
	{
		return objServiceProviderDAO.getColonyName(language, wardPatwariId);
	}

	public ArrayList getPreviousLicenseDetailsEstampJudicial1(
			ServiceProviderForm objServiceProviderForm) throws Exception {
		// TODO Auto-generated method stub
		return objServiceProviderDAO.getPreviousLicenseDetailsEstampJudicial1(objServiceProviderForm);
	}

	public boolean getRequestNumber(ServiceProviderForm objServiceProviderForm) {
		// TODO Auto-generated method stub
		return  objServiceProviderDAO.getRequestNumber(objServiceProviderForm);
	}

	public String getNewLicenseNumber(ServiceProviderForm objServiceProviderForm) {
		// TODO Auto-generated method stub
		return  objServiceProviderDAO.getNewLicenseNumber(objServiceProviderForm);
	}
	public boolean insertSpOldDocDetls (ServiceProviderForm objServiceProviderForm) throws Exception
	{
		return objServiceProviderDAO.insertSpOldDocDetls(objServiceProviderForm);
	}
	
	
}

