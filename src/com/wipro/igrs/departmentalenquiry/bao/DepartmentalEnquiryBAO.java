/**
 * 
 */
package com.wipro.igrs.departmentalenquiry.bao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO;

/**
 * @author HMOHAM
 *
 */
public class DepartmentalEnquiryBAO {

	private DepartmentalEnquiryDAO departmentalEnquiryDAO = new DepartmentalEnquiryDAO();

	/**
	 * @param complaintNo
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#checkComplaintStatus(java.lang.String)
	 */
	public String checkComplaintStatus(String complaintNo) {
		return departmentalEnquiryDAO.checkComplaintStatus(complaintNo);
	}

	/**
	 * @param res
	 * @param contId
	 * @param strFunctionName
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#displayObjectDetails(javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.String)
	 */
	public String displayObjectDetails(HttpServletResponse res, String contId,
			String strFunctionName) {
		return departmentalEnquiryDAO.displayObjectDetails(res, contId,
				strFunctionName);
	}

	/**
	 * @param complaintId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getChargesheetCloseComplaintList(java.lang.String)
	 */
	public ArrayList getChargesheetCloseComplaintList(String complaintId) {
		return departmentalEnquiryDAO
				.getChargesheetCloseComplaintList(complaintId);
	}

	/**
	 * @param arr
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getChargesheetCloseDetails(java.lang.String[])
	 */
	public ArrayList getChargesheetCloseDetails(String[] arr) {
		return departmentalEnquiryDAO.getChargesheetCloseDetails(arr);
	}

	/**
	 * @param complaintId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getChargesheetComplaintList(java.lang.String)
	 */
	public ArrayList getChargesheetComplaintList(String complaintId) {
		return departmentalEnquiryDAO.getChargesheetComplaintList(complaintId);
	}

	/**
	 * @param arr
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getChargesheetDetails(java.lang.String[])
	 */
	public ArrayList getChargesheetDetails(String[] arr) {
		return departmentalEnquiryDAO.getChargesheetDetails(arr);
	}

	/**
	 * @param empId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getComplainerEmpDetails(java.lang.String)
	 */
	public ArrayList getComplainerEmpDetails(String empId) {
		return departmentalEnquiryDAO.getComplainerEmpDetails(empId);
	}

	/**
	 * @param userId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getComplainerUserDetails(java.lang.String)
	 */
	public ArrayList getComplainerUserDetails(String userId) {
		return departmentalEnquiryDAO.getComplainerUserDetails(userId);
	}

	/**
	 * @param complaintId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getComplaintDetails(java.lang.String)
	 */
	public ArrayList getComplaintDetails(String complaintId) {
		return departmentalEnquiryDAO.getComplaintDetails(complaintId);
	}

	/**
	 * @param complaintId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getComplaintList(java.lang.String)
	 */
	public ArrayList getComplaintList(String complaintId) {
		return departmentalEnquiryDAO.getComplaintList(complaintId);
	}

	/**
	 * @param complaintId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getComplaintListForSuspensionOrder(java.lang.String)
	 */
	public ArrayList getComplaintListForSuspensionOrder(String complaintId) {
		return departmentalEnquiryDAO
				.getComplaintListForSuspensionOrder(complaintId);
	}

	/**
	 * @param arr
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getDeputedPEDetails(java.lang.String[])
	 */
	public ArrayList getDeputedPEDetails(String[] arr) {
		return departmentalEnquiryDAO.getDeputedPEDetails(arr);
	}

	/**
	 * @param empId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getEmpDetails(java.lang.String)
	 */
	public ArrayList getEmpDetails(String empId) {
		return departmentalEnquiryDAO.getEmpDetails(empId);
	}

	/**
	 * @param complaintId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getPreEnquiryComplaintList(java.lang.String)
	 */
	public ArrayList getPreEnquiryComplaintList(String complaintId) {
		return departmentalEnquiryDAO.getPreEnquiryComplaintList(complaintId);
	}

	/**
	 * @param arr
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getPreEnquiryDetails(java.lang.String[])
	 */
	public ArrayList getPreEnquiryDetails(String[] arr) {
		return departmentalEnquiryDAO.getPreEnquiryDetails(arr);
	}

	/**
	 * @param complaintNo
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getSuspenionAcceptedDetails(java.lang.String)
	 */
	public ArrayList getSuspenionAcceptedDetails(String complaintNo) {
		return departmentalEnquiryDAO.getSuspenionAcceptedDetails(complaintNo);
	}

	/**
	 * @param complaintNo
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getSuspenionPEDetails(java.lang.String)
	 */
	public ArrayList getSuspenionPEDetails(String complaintNo) {
		return departmentalEnquiryDAO.getSuspenionPEDetails(complaintNo);
	}

	/**
	 * @param complaintId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getSuspensionComplaintsList(java.lang.String)
	 */
	public ArrayList getSuspensionComplaintsList(String complaintId) {
		return departmentalEnquiryDAO.getSuspensionComplaintsList(complaintId);
	}

	/**
	 * @param complaintId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getSuspensionOrderRecord(java.lang.String)
	 */
	public ArrayList getSuspensionOrderRecord(String complaintId) {
		return departmentalEnquiryDAO.getSuspensionOrderRecord(complaintId);
	}

	/**
	 * @param userId
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#getUserDetails(java.lang.String)
	 */
	public ArrayList getUserDetails(String userId) {
		return departmentalEnquiryDAO.getUserDetails(userId);
	}

	/**
	 * @param list
	 * @param filePath
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#submitChargesheet(java.util.ArrayList, java.lang.String)
	 */
	public boolean submitChargesheet(ArrayList list, String filePath) {
		return departmentalEnquiryDAO.submitChargesheet(list, filePath);
	}

	/**
	 * @param list
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#submitChargesheetClose(java.util.ArrayList)
	 */
	public boolean submitChargesheetClose(ArrayList list) {
		return departmentalEnquiryDAO.submitChargesheetClose(list);
	}

	/**
	 * @param arr
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#submitCriminalCase(java.lang.String[])
	 */
	public String submitCriminalCase(String[] arr) {
		return departmentalEnquiryDAO.submitCriminalCase(arr);
	}

	/**
	 * @param arr
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#submitDeInitiateDetails(java.lang.String[])
	 */
	public boolean submitDeInitiateDetails(String[] arr) {
		return departmentalEnquiryDAO.submitDeInitiateDetails(arr);
	}

	/**
	 * @param list
	 * @param filePath
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#submitEmpAcceptCharges(java.util.ArrayList, java.lang.String)
	 */
	public boolean submitEmpAcceptCharges(ArrayList list, String filePath) {
		return departmentalEnquiryDAO.submitEmpAcceptCharges(list, filePath);
	}

	/**
	 * @param list
	 * @param filePath
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#submitEmpReleaseCharges(java.util.ArrayList, java.lang.String)
	 */
	public boolean submitEmpReleaseCharges(ArrayList list, String filePath) {
		return departmentalEnquiryDAO.submitEmpReleaseCharges(list, filePath);
	}

	/**
	 * @param list
	 * @param filePath
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#submitPreSuspension(java.util.ArrayList, java.lang.String)
	 */
	public boolean submitPreSuspension(ArrayList list, String filePath) {
		return departmentalEnquiryDAO.submitPreSuspension(list, filePath);
	}

	/**
	 * @param list
	 * @param filePath
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#submitSuspenseOrderAccepted(java.util.ArrayList, java.lang.String)
	 */
	public boolean submitSuspenseOrderAccepted(ArrayList list, String filePath) {
		return departmentalEnquiryDAO.submitSuspenseOrderAccepted(list,
				filePath);
	}

	/**
	 * @param list
	 * @param filePath
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#submitSuspenseOrderInitiated(java.util.ArrayList, java.lang.String)
	 */
	public boolean submitSuspenseOrderInitiated(ArrayList list, String filePath) {
		return departmentalEnquiryDAO.submitSuspenseOrderInitiated(list,
				filePath);
	}

	/**
	 * @param list
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO#updateSuspensionToRevoke(java.util.ArrayList)
	 */
	public boolean updateSuspensionToRevoke(ArrayList list) {
		return departmentalEnquiryDAO.updateSuspensionToRevoke(list);
	}
	
	
}
