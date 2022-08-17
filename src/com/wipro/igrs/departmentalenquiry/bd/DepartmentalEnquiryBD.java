/*
 * DepartmentalEnquiryBD.java
 */


package com.wipro.igrs.departmentalenquiry.bd;


import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wipro.igrs.departmentalenquiry.bao.DepartmentalEnquiryBAO;
import com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquiryDAO;
import com.wipro.igrs.departmentalenquiry.dto.ChargeSheetReleaseDTO;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeAcceptChanrgesDTO;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeReleaseChanrgesDTO;
import com.wipro.igrs.departmentalenquiry.dto.PreliminaryEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.SuspensionOrderDTO;
import com.wipro.igrs.departmentalenquiry.dto.WitnessDTO;
import com.wipro.igrs.departmentalenquiry.form.SuspensionForm;
import com.wipro.igrs.util.CommonUtil;


/**
 * @author oneapps
 * 
 */
public class DepartmentalEnquiryBD {
	private DepartmentalEnquiryBAO	deBAO	= new DepartmentalEnquiryBAO();
	private Logger logger = (Logger) Logger.getLogger(DepartmentalEnquiryBD.class);
	
	public ArrayList getUserDetails(String userId) {
		ArrayList list = new ArrayList();
		ArrayList userDetails = null;

		try {

			ArrayList userDetailsTemp = new ArrayList();
			userDetailsTemp = deBAO.getUserDetails(userId);
			for (int i = 0; i < userDetailsTemp.size(); i++) {
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();
				userDetails = (ArrayList) userDetailsTemp.get(i);
				
				
				cceDTO.setUserId((String) userDetails.get(0));

				cceDTO.setName((String) userDetails.get(1));
				cceDTO.setAddress((String) userDetails.get(2));

				cceDTO.setContactNumber((String) userDetails.get(3));

				cceDTO.setEmail((String) userDetails.get(4));

				cceDTO.setPlaceOfPosting("");
				cceDTO.setDesignation("");

				list.add(cceDTO);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * @param userId
	 * @return
	 */
	public ArrayList getEmpDetails(String userId) {
		ArrayList list = new ArrayList();
		ArrayList userDetails = null;

		try {

			ArrayList userDetailsTemp = new ArrayList();

			userDetailsTemp = deBAO.getEmpDetails(userId);

			for (int i = 0; i < userDetailsTemp.size(); i++) {
				userDetails = (ArrayList) userDetailsTemp.get(i);
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();

				cceDTO.setUserId((String) userDetails.get(0));

				cceDTO.setName((String) userDetails.get(1));

				cceDTO.setAddress((String) userDetails.get(2));

				cceDTO.setContactNumber((String) userDetails.get(3));

				cceDTO.setEmail((String) userDetails.get(4));

				cceDTO.setPlaceOfPosting("");

				cceDTO.setDesignation((String) userDetails.get(6));

				list.add(cceDTO);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * @param userId
	 * @return
	 */
	public ArrayList getComplainerUserDetails(String userId) {
		ArrayList list = new ArrayList();
		ArrayList userDetails = null;

		try {

			ArrayList userDetailsTemp = new ArrayList();
			userDetailsTemp = deBAO.getComplainerUserDetails(userId);
			for (int i = 0; i < userDetailsTemp.size(); i++) {
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();
				userDetails = (ArrayList) userDetailsTemp.get(i);

				cceDTO.setComplainerUserId((String) userDetails.get(0));
				// System.out.println("1 :" + (String) userDetails.get(0));

				cceDTO.setComplainerName((String) userDetails.get(1));
				// System.out.println("2 :" + (String) userDetails.get(1));

				cceDTO.setComplainerAddress((String) userDetails.get(2));
				// System.out.println("3:" + (String) userDetails.get(2));

				cceDTO.setComplainerContactNumber((String) userDetails.get(3));
				// System.out.println("4 :" + (String) userDetails.get(3));

				cceDTO.setComplainerEmail((String) userDetails.get(4));
				// System.out.println("5 :" + (String) userDetails.get(4));

				cceDTO.setComplainerDesignation("");
				// System.out.println("6 :"+(String)userDetails.get(5));

				cceDTO.setComplainerDepartment("");
				// System.out.println("7 :"+(String)userDetails.get(6));

				list.add(cceDTO);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * @param userId
	 * @return
	 */
	public ArrayList getComplainerEmpDetails(String userId) {
		ArrayList list = new ArrayList();
		ArrayList userDetails = null;

		try {

			ArrayList userDetailsTemp = new ArrayList();
			userDetailsTemp = deBAO.getComplainerEmpDetails(userId);
			for (int i = 0; i < userDetailsTemp.size(); i++) {
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();
				userDetails = (ArrayList) userDetailsTemp.get(i);

				cceDTO.setComplainerUserId((String) userDetails.get(0));
				// System.out.println("1 :" + (String) userDetails.get(0));

				cceDTO.setComplainerName((String) userDetails.get(1));
				// System.out.println("2 :" + (String) userDetails.get(1));

				cceDTO.setComplainerAddress((String) userDetails.get(2));
				// System.out.println("3:" + (String) userDetails.get(2));

				cceDTO.setComplainerContactNumber((String) userDetails.get(3));
				// System.out.println("4 :" + (String) userDetails.get(3));

				cceDTO.setComplainerEmail((String) userDetails.get(4));
				// System.out.println("5 :" + (String) userDetails.get(4));
				
				cceDTO.setComplainerDepartment("");
				
				cceDTO.setComplainerDesignation((String) userDetails.get(5));
				// System.out.println("6 :" + (String) userDetails.get(5));

				

				list.add(cceDTO);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * @param criminalCaseDTO
	 * @param userId
	 * @return
	 */
	public String submitCriminalCase(CriminalCaseEnquiryDTO criminalCaseDTO,
	        String userId) {

		// System.out.println("I am in BD");
		String criminalCaseTxnId = null;
		String[] arr = new String[12];
		arr[0] = criminalCaseDTO.getComplainerUserId();
		arr[1] = criminalCaseDTO.getUserId();
		if (criminalCaseDTO.getCaseType().equalsIgnoreCase("Criminal Case")) {
			arr[2] = "Y";
		} else {
			arr[2] = "Y";
		}
		arr[3] = criminalCaseDTO.getCriminalCaseAbout();
		arr[4] = CommonUtil.getConvertedDate(criminalCaseDTO.getCriminalCaseDate());
		arr[5] = "Registered";
		arr[6] = "";
		arr[7] = "";
		arr[8] = userId;
		arr[9] = userId;
		arr[10] = "Y";

		try {
			criminalCaseTxnId = deBAO.submitCriminalCase(arr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return criminalCaseTxnId;
	}

	/**
	 * @param complaintId
	 * @return
	 */
	public ArrayList getComplaintList(String complaintId) {
		ArrayList list = new ArrayList();
		try {
			ArrayList listComplaint = deBAO.getComplaintList(complaintId);
			for (int i = 0; i < listComplaint.size(); i++) {
				ArrayList listcomplaints = (ArrayList) listComplaint.get(i);
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();

				cceDTO.setComplaintNo((String) listcomplaints.get(0));
				// System.out.println("1 :" + (String) listcomplaints.get(0));

				cceDTO.setCreatedBy((String) listcomplaints.get(1));
				// System.out.println("2 :" + (String) listcomplaints.get(1));

				cceDTO.setComplaintStatus((String) listcomplaints.get(2));
				// System.out.println("3 :" + (String) listcomplaints.get(2));

				cceDTO.setCreatedDate((String) listcomplaints.get(3));
				// System.out.println("4 :" + (String) listcomplaints.get(3));
				list.add(cceDTO);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * @param complaintId
	 * @return
	 */
	public CriminalCaseEnquiryDTO getComplaintDetails(String complaintId) {
		// /ArrayList list=new ArrayList();
		CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();
		try {
			ArrayList listComplaint = deBAO.getComplaintDetails(complaintId);
			ArrayList listComplainee = (ArrayList) listComplaint.get(0);
			ArrayList listComplainer = (ArrayList) listComplaint.get(1);
			ArrayList listComplainee1 = (ArrayList) listComplainee.get(0);
			ArrayList listComplainer1 = (ArrayList) listComplainer.get(0);

			// CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();

			cceDTO.setName((String) listComplainee1.get(0));
			// System.out.println("1 :" + (String) listComplainee1.get(0));

			cceDTO.setComplainerName((String) listComplainer1.get(0));
			// System.out.println("11 :" + (String) listComplainer1.get(0));

			cceDTO.setAddress((String) listComplainee1.get(1));
			// System.out.println("2 :" + (String) listComplainee1.get(1));

			cceDTO.setComplainerAddress((String) listComplainer1.get(1));
			// System.out.println("22 :" + (String) listComplainer1.get(1));

			cceDTO.setContactNumber((String) listComplainee1.get(2));
			// System.out.println("3 :" + (String) listComplainee1.get(2));

			cceDTO.setComplainerContactNumber((String) listComplainer1.get(2));
			// System.out.println("33 :" + (String) listComplainer1.get(2));

			cceDTO.setEmail((String) listComplainee1.get(3));
			// System.out.println("3 :" + (String) listComplainee1.get(3));

			cceDTO.setComplainerEmail((String) listComplainer1.get(3));
			// System.out.println("33 :" + (String) listComplainer1.get(3));

			cceDTO.setCreatedDate((String) listComplainee1.get(4));
			cceDTO.setCriminalCaseAbout((String) listComplainee1.get(5));

			cceDTO.setComplaintNo(complaintId);
			// System.out.println("CR CAs Ab :" + (String)
			// listComplainee1.get(5));

			// list.add(cceDTO);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cceDTO;
	}

	/**
	 * @param dto
	 * @param userId
	 * @param complaintId
	 * @return
	 */
	public boolean submitDeInitiateDetails(CriminalCaseEnquiryDTO dto,
	        String userId, String complaintId) {
		boolean flag = false;
		String[] arr = new String[4];
		arr[0] = dto.getUserId();
		arr[1] = userId;
		arr[2] = "Initiated";
		arr[3] = complaintId;

		try {
			flag = deBAO.submitDeInitiateDetails(arr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * @param complaintNo
	 * @return
	 */
	public CriminalCaseEnquiryDTO getPreEnquiryDetails(String complaintNo) {
		String[] arr = new String[1];
		arr[0] = complaintNo;
		CriminalCaseEnquiryDTO myDTO = new CriminalCaseEnquiryDTO();

		ArrayList list1 = deBAO.getPreEnquiryDetails(arr);
		System.out.println("Pre Enquiry Details"+list1.size());
		ArrayList listComplainee = (ArrayList) list1.get(0);
		System.out.println("listComplainee Details"+listComplainee.size());
		ArrayList listComplainer = (ArrayList) list1.get(1);
		System.out.println("listComplainer Details"+listComplainer.size());
		ArrayList listDe=(ArrayList) list1.get(2);
		System.out.println("listComplainer Details"+listDe.size());
		ArrayList listComplainee1 = (ArrayList) listComplainee.get(0);
		System.out.println("listComplainee1 Details"+listComplainee1.size());
		ArrayList listComplainer1 = (ArrayList) listComplainer.get(0);
		System.out.println("listComplainer1 Details"+listComplainer1.size());
		ArrayList listdeper = null;
		if(listDe != null && listDe.size() > 0){
			listdeper = (ArrayList) listDe.get(0);	
		}
		System.out.println("listdeper Details"+listdeper.size());
		
		// System.out.println("Size of 2nd List :" + list.size());

		myDTO.setComplainerName((String) listComplainee1.get(0));
		// System.out.println("1 :" + (String) list.get(0));

		myDTO.setComplainerAddress((String) listComplainee1.get(1));
		// System.out.println("2 :" + (String) list.get(1));

		myDTO.setComplainerContactNumber((String) listComplainee1.get(2));
		// System.out.println("3 :" + (String) list.get(2));

		myDTO.setComplainerEmail((String) listComplainee1.get(3));
		
		
		myDTO.setName((String) listComplainer1.get(0));
		myDTO.setAddress((String) listComplainer1.get(1));
		myDTO.setContactNumber((String) listComplainer1.get(2));
		myDTO.setEmail((String) listComplainer1.get(3));
		if(listdeper != null && listdeper.size() > 0){
		myDTO.setDpName((String) listdeper.get(0));
		myDTO.setDpAddress((String) listdeper.get(1));
		myDTO.setDpContactNumber((String) listdeper.get(2));
		myDTO.setDpEmail((String) listdeper.get(3));
		}
		myDTO.setCriminalCaseAbout((String) listComplainee1.get(4));
		myDTO.setCreatedDate((String) listComplainee1.get(5));
		myDTO.setComplaintNo(complaintNo);
		System.out.println("End of BD");
		return myDTO;
	}

	/**
	 * @param complaintId
	 * @return
	 */
	public ArrayList getPreEnquiryComplaintList(String complaintId) {
		ArrayList list = new ArrayList();
		try {
			ArrayList listComplaint = deBAO
			        .getPreEnquiryComplaintList(complaintId);
			for (int i = 0; i < listComplaint.size(); i++) {
				ArrayList listcomplaints = (ArrayList) listComplaint.get(i);
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();

				cceDTO.setComplaintNo((String) listcomplaints.get(0));
				// System.out.println("1 :" + (String) listcomplaints.get(0));

				cceDTO.setCreatedBy((String) listcomplaints.get(1));
				// System.out.println("2 :" + (String) listcomplaints.get(1));

				cceDTO.setComplaintStatus((String) listcomplaints.get(2));
				// System.out.println("3 :" + (String) listcomplaints.get(2));

				cceDTO.setCreatedDate((String) listcomplaints.get(3));
				// System.out.println("4 :" + (String) listcomplaints.get(3));
				list.add(cceDTO);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * @param preEnquiryDTO
	 * @param empDTO
	 * @param filePath
	 * @param userId
	 * @return
	 */
	public boolean submitEmpAcceptCharges(PreliminaryEnquiryDTO preEnquiryDTO,
	        EmployeeAcceptChanrgesDTO empDTO, String filePath, String userId) {

		// System.out.println("I am in submitEmpAcceptCharges() BD");
		boolean result = false;
		String[] compDetails = new String[4];
		String[] compAction = new String[3];
		String[] compComment = new String[6];
		String[] compStatus = new String[3];
		String[] compDocMap = new String[4];

		// Details Of IGRS_EMP_COMPLAINT_DETAILS
		compDetails[0] = "Accepted";
		compDetails[1] = userId;
		if (empDTO.getStrNatureOfCharge().equalsIgnoreCase("Major")) {
			compDetails[2] = "Y";
		} else {
			compDetails[2] = "N";
		}
		compDetails[3] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_ACTION_DTLS
		compAction[0] = empDTO.getStrFinalCharge();
		compAction[1] = empDTO.getStrComments();
		compAction[2] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_COMMENTS
		compComment[0] = preEnquiryDTO.getStrComplaintId();
		compComment[1] = userId;
		compComment[2] = preEnquiryDTO.getStrPriliminaryDetails();
		compComment[3] = CommonUtil.getConvertedDate(preEnquiryDTO.getStrPreliminaryDate());
		compComment[4] = "Accepted";
		compComment[5] = preEnquiryDTO.getStrPriliminaryComments();

		// Details of IGRS_EMP_COMPLAINT_STATUS_DTLS

		compStatus[0] = "Accepted";
		compStatus[1] = CommonUtil.getConvertedDate(preEnquiryDTO.getStrPreliminaryDate());
		compStatus[2] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_DOC_MAP
		compDocMap[0] = preEnquiryDTO.getStrComplaintId();
		compDocMap[1] = preEnquiryDTO.getStrSuuportingDoc();
		compDocMap[2] = "Suspension";
		compDocMap[3] = CommonUtil.getConvertedDate(preEnquiryDTO.getStrPreliminaryDate());

		ArrayList list = new ArrayList();
		list.add(compDetails);
		list.add(compAction);
		list.add(compComment);
		list.add(compStatus);
		list.add(compDocMap);

		result = deBAO.submitEmpAcceptCharges(list, filePath);
		// System.out.println("Result Status in BD :"+result);

		return result;

	}

	/**
	 * @param preEnquiryDTO
	 * @param empDTO
	 * @param filePath
	 * @param userId
	 * @return
	 */
	public boolean submitEmpReleaseCharges(PreliminaryEnquiryDTO preEnquiryDTO,
	        EmployeeReleaseChanrgesDTO empDTO, String filePath, String userId) {

		// System.out.println("I am in submitEmpAcceptCharges() BD");
		boolean result = false;
		String[] compDetails = new String[4];
		String[] compAction = new String[3];
		String[] compComment = new String[6];
		String[] compStatus = new String[3];
		String[] compDocMap = new String[4];

		// Details Of IGRS_EMP_COMPLAINT_DETAILS
		compDetails[0] = "Closed";
		compDetails[1] = userId;
		compDetails[2] = "N";
		compDetails[3] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_ACTION_DTLS
		compAction[0] = "Closed";
		compAction[1] = empDTO.getStrComments();
		compAction[2] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_COMMENTS
		compComment[0] = preEnquiryDTO.getStrComplaintId();
		compComment[1] = userId;
		compComment[2] = preEnquiryDTO.getStrPriliminaryDetails();
		compComment[3] = CommonUtil.getConvertedDate(preEnquiryDTO.getStrPreliminaryDate());
		compComment[4] = "Closed";
		compComment[5] = preEnquiryDTO.getStrPriliminaryComments();

		// Details of IGRS_EMP_COMPLAINT_STATUS_DTLS

		compStatus[0] = "Closed";
		compStatus[1] = CommonUtil.getConvertedDate(preEnquiryDTO.getStrPreliminaryDate());
		compStatus[2] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_DOC_MAP
		compDocMap[0] = preEnquiryDTO.getStrComplaintId();
		compDocMap[1] = preEnquiryDTO.getStrSuuportingDoc();
		compDocMap[2] = "Closed";
		compDocMap[3] = CommonUtil.getConvertedDate(preEnquiryDTO.getStrPreliminaryDate());

		ArrayList list = new ArrayList();
		list.add(compDetails);
		list.add(compAction);
		list.add(compComment);
		list.add(compStatus);
		list.add(compDocMap);

		result = deBAO.submitEmpReleaseCharges(list, filePath);
		// System.out.println("Result Status in BD :"+result);
		return result;
	}

	// / SAI
	/**
	 * @param complaintNo
	 * @return
	 */
	public CriminalCaseEnquiryDTO getDeputedPEDetails(String complaintNo) {
		// System.out.println(">>>>>BD : in getDeputedPEDetails<<<<<");
		String[] arr = new String[1];
		arr[0] = complaintNo;
		CriminalCaseEnquiryDTO myDTO = new CriminalCaseEnquiryDTO();

		ArrayList list1 = deBAO.getDeputedPEDetails(arr);// complaint No
		
		ArrayList complaineelist=(ArrayList)list1.get(0);
		ArrayList complainerlist=(ArrayList)list1.get(1);
		ArrayList duptedlist=(ArrayList)list1.get(2);
		
		 System.out.println("Size of List :" + list1.size());

		ArrayList complainerlistdto = (ArrayList) complainerlist.get(0);
		 System.out.println("Size of 2nd List :" + complainerlist.size());

		myDTO.setComplainerName((String) complainerlistdto.get(0));
		 System.out.println("1 :" + (String) complainerlistdto.get(0));

		myDTO.setComplainerAddress((String) complainerlistdto.get(1));
		System.out.println("2 :" + (String) complainerlistdto.get(1));

		myDTO.setComplainerContactNumber((String) complainerlistdto.get(2));
		 System.out.println("3 :" + (String) complainerlistdto.get(2));

		myDTO.setComplainerEmail((String) complainerlistdto.get(3));
		ArrayList complaineelistdto=(ArrayList) complaineelist.get(0);
		myDTO.setName((String) complaineelistdto.get(0));		
		myDTO.setAddress((String) complaineelistdto.get(1));
		myDTO.setContactNumber((String) complaineelistdto.get(2));
		myDTO.setEmail((String) complaineelistdto.get(3));
		ArrayList duptedlistdto=(ArrayList)duptedlist.get(0);
		myDTO.setDpName((String) duptedlistdto.get(0));
		myDTO.setDpAddress((String) duptedlistdto.get(1));
		myDTO.setDpContactNumber((String) duptedlistdto.get(2));
		myDTO.setDpEmail((String) duptedlistdto.get(3));
		myDTO.setCriminalCaseAbout((String) duptedlistdto.get(4));
		myDTO.setCreatedDate((String) duptedlistdto.get(5));
		myDTO.setComplaintNo(complaintNo);

		return myDTO;
	}

	public PreliminaryEnquiryDTO getSuspenionPEDetails(String complaintNo) {
		// System.out.println(">>>>in getSuspenionPEDetails<<<<");
		String[] arr = new String[1];
		arr[0] = complaintNo;
		ArrayList list1 = deBAO.getSuspenionPEDetails(complaintNo);
		// System.out.println("List Size :"+list1.size());
		if (list1.size() > 0) {
			ArrayList list = (ArrayList) list1.get(0);
			PreliminaryEnquiryDTO preDTO = new PreliminaryEnquiryDTO();
			preDTO.setStrPriliminaryDetails((String) list.get(0));
			// System.out.println("1:"+(String)list.get(0));
			preDTO.setStrPriliminaryComments((String) list.get(1));
			// System.out.println("2:"+(String)list.get(1));
			preDTO.setStrPreliminaryDate((String) list.get(2));
			// System.out.println("3:"+(String)list.get(2));
			preDTO.setStrSuuportingDoc((String) list.get(3));
			// System.out.println("4:"+(String)list.get(3));
			preDTO.setStrDocumentType((String) list.get(4));
			preDTO.setStrDocumentId((String) list.get(5));
			return preDTO;
		} else
			return null;
	}

	public EmployeeAcceptChanrgesDTO getSuspensionAcceptedDetails(
	        String complaintNo) {
		// System.out.println(">>>>in getSuspensionAcceptedDetails<<<");
		String[] arr = new String[1];
		arr[0] = complaintNo;
		ArrayList list1 = deBAO.getSuspenionAcceptedDetails(complaintNo);
		// System.out.println("List Size :"+list1.size());
		ArrayList list = (ArrayList) list1.get(0);
		EmployeeAcceptChanrgesDTO acceptDTO = new EmployeeAcceptChanrgesDTO();
		// if(list.size()>0)
		// {
		acceptDTO.setStrFinalCharge((String) list.get(0));
		// System.out.println("1:"+(String)list.get(0));
		String s = (String) list.get(1);
		if (s.equalsIgnoreCase("Y"))
			s = "Major";
		else
			s = "Minor";
		acceptDTO.setStrNatureOfCharge(s);
		System.out.println("2:" + (String) list.get(1) + "-->" + s);
		// }
		return acceptDTO;
	}

	public ArrayList getComplaintListForSuspensionOrder(String complaintId) {
		ArrayList list = new ArrayList();
		try {
			ArrayList listComplaint = deBAO
			        .getComplaintListForSuspensionOrder(complaintId);
			for (int i = 0; i < listComplaint.size(); i++) {
				ArrayList listcomplaints = (ArrayList) listComplaint.get(i);
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();

				cceDTO.setComplaintNo((String) listcomplaints.get(0));
				// System.out.println("1 :" + (String) listcomplaints.get(0));

				cceDTO.setCreatedBy((String) listcomplaints.get(1));
				// System.out.println("2 :" + (String) listcomplaints.get(1));

				cceDTO.setComplaintStatus((String) listcomplaints.get(2));
				// System.out.println("3 :" + (String) listcomplaints.get(2));

				cceDTO.setCreatedDate((String) listcomplaints.get(3));
				// System.out.println("4 :" + (String) listcomplaints.get(3));
				list.add(cceDTO);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public boolean submitPreSuspension(PreliminaryEnquiryDTO preEnquiryDTO,
	        SuspensionOrderDTO suspensionDTO, String filePath, String userId) {
		// System.out.println("I am in submitEmpAcceptCharges() BD");
		boolean result = false;

		String[] compDetails = new String[4];
		String[] compAction = new String[10];
		String[] compComment = new String[6];
		String[] compStatus = new String[3];
		String[] compDocMap = new String[4];
		String[] compSigDocMap = new String[4];

		// Details Of IGRS_EMP_COMPLAINT_DETAILS
		compDetails[0] = "Suspension";
		compDetails[1] = userId;
		compDetails[2] = suspensionDTO.getNatureOfCharge();
		compDetails[3] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_ACTION_DTLS
		compAction[0] = "Suspension";
		compAction[1] = suspensionDTO.getStrDetails();
		compAction[2] = suspensionDTO.getStrIssuingAuthority();
		compAction[3] = CommonUtil.getConvertedDate(suspensionDTO.getStrDateOfIssue());
		compAction[4] = userId;
		compAction[5] = suspensionDTO.getStrComments();
		compAction[6] = suspensionDTO.getStrFinalCharge();
		compAction[7] = suspensionDTO.getStrPlaceOfIssue();
		compAction[8] = suspensionDTO.getStrAddress();
		compAction[9] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_COMMENTS
		compComment[0] = userId;
		compComment[1] = preEnquiryDTO.getStrPriliminaryDetails();
		compComment[2] = CommonUtil.getConvertedDate(preEnquiryDTO.getStrPreliminaryDate());
		compComment[3] = "Suspension";
		compComment[4] = preEnquiryDTO.getStrPriliminaryComments();
		compComment[5] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_STATUS_DTLS

		compStatus[0] = "Suspension";
		compStatus[1] = CommonUtil.getConvertedDate(preEnquiryDTO.getStrPreliminaryDate());
		compStatus[2] = preEnquiryDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_DOC_MAP
		compDocMap[0] = preEnquiryDTO.getStrComplaintId();
		compDocMap[1] = preEnquiryDTO.getStrSuuportingDoc();
		compDocMap[2] = "Suspension";
		compDocMap[3] = CommonUtil.getConvertedDate(preEnquiryDTO.getStrPreliminaryDate());

		compSigDocMap[0] = preEnquiryDTO.getStrComplaintId();
		compSigDocMap[1] = suspensionDTO.getStrSignatureFileName();
		compSigDocMap[2] = "Signature";
		compSigDocMap[3] = CommonUtil.getConvertedDate(suspensionDTO.getStrDateOfIssue());

		ArrayList list = new ArrayList();
		list.add(compDetails);
		list.add(compAction);
		list.add(compComment);
		list.add(compStatus);
		list.add(compDocMap);
		list.add(compSigDocMap);

		result = deBAO.submitPreSuspension(list, filePath);
		// System.out.println("Result Status in BD :"+result);
		return result;
	}

	public String checkComplaintStatus(String complaintNo) {
		String status = deBAO.checkComplaintStatus(complaintNo);
		return status;
	}

	public String displayObjectDetails(HttpServletResponse res, String contId,String strFunctionName) {
		String status = deBAO.displayObjectDetails(res, contId,strFunctionName);
		return status;
	}

	public boolean submitSuspenseOrderInitiated(SuspensionForm sForm,
	        String filePath, String userId) {
		boolean flag = false;
		try {
			// System.out.println("in BD - submitSuspenseOrderInitiated");
			boolean b = false;
			PreliminaryEnquiryDTO pDTO = sForm.getPrelimDTO();
			EmployeeAcceptChanrgesDTO aDTO = sForm.getPeAcceptsDTO();
			SuspensionOrderDTO sDTO = sForm.getSuspenseDTO();

			// IGRS_EMP_COMPLAINT_COMMENTS ------------------------------->
			// insert these details with complaint_id
			String preArr[] = new String[6];
			preArr[0] = userId;// pDTO.getStrComplaintId();
			preArr[1] = pDTO.getStrPriliminaryComments();
			preArr[2] = CommonUtil.getConvertedDate(pDTO.getStrPreliminaryDate());
			preArr[3] = "Suspension";
			preArr[4] = pDTO.getStrPriliminaryDetails();
			preArr[5] = pDTO.getStrComplaintId();
			// System.out.println("preArr[0] :"+preArr[0]);
			// System.out.println("preArr[1] :"+preArr[1]);
			// System.out.println("preArr[2] :"+preArr[2]);
			// System.out.println("preArr[3] :"+preArr[3]);
			// System.out.println("preArr[4] :"+preArr[4]);
			// System.out.println("preArr[5] :"+preArr[5]);

			// IGRS_EMP_COMPLAINT_DETAILS ------------------------------->
			// update these details with complaint_id
			String acArr[] = new String[5];
			acArr[0] = "Suspension";
			acArr[1] = aDTO.getStrNatureOfCharge();// IGRS_EMP_COMPLAINT_ACTION_DTLS
			acArr[2] = userId;
			acArr[3] = CommonUtil.getConvertedDate(pDTO.getStrPreliminaryDate());
			acArr[4] = aDTO.getStrComplaintId();
			// System.out.println("acArr[0] :"+acArr[0]);
			// System.out.println("acArr[1] :"+acArr[1]);
			// System.out.println("acArr[2] :"+acArr[2]);
			// System.out.println("acArr[3] :"+acArr[3]);
			// System.out.println("acArr[4] :"+acArr[4]);

			// IGRS_EMP_COMPLAINT_ACTION_DTLS ------------------------------->
			// insert these details with complaint_id
			String susArr[] = new String[10];
			susArr[0] = sDTO.getStrIssuingAuthority();
			susArr[1] = CommonUtil.getConvertedDate(sDTO.getStrDateOfIssue());
			susArr[2] = sDTO.getStrDetails();
			susArr[3] = sDTO.getStrComments();
			susArr[4] = "Suspension";
			susArr[5] = aDTO.getStrFinalCharge();
			susArr[6] = sDTO.getStrPlaceOfIssue();
			susArr[7] = sDTO.getStrAddress();
			susArr[8] = sDTO.getStrComplaintId();
			susArr[9] = userId;
			// System.out.println("susArr[0] :"+susArr[0]);
			// /System.out.println("susArr[1] :"+susArr[1]);
			// System.out.println("susArr[2] :"+susArr[2]);
			// System.out.println("susArr[3] :"+susArr[3]);
			// System.out.println("susArr[4] :"+susArr[4]);
			// System.out.println("susArr[5] :"+susArr[5]);
			// System.out.println("susArr[6] :"+susArr[6]);
			// System.out.println("susArr[7] :"+susArr[7]);
			// System.out.println("susArr[8] :"+susArr[8]);
			// System.out.println("susArr[9] :"+susArr[9]);

			// IGRS_EMP_COMPLAINT_STATUS_DTLS ------------------------------->
			// update these details with complaint_id
			String statusDetails[] = new String[3];
			statusDetails[0] = "Suspension";
			statusDetails[1] = CommonUtil.getConvertedDate(sDTO.getStrDateOfIssue());
			statusDetails[2] = sDTO.getStrComplaintId();
			// System.out.println("statusDetails[0] :"+statusDetails[0]);
			// System.out.println("statusDetails[1] :"+statusDetails[1]);
			// System.out.println("statusDetails[2] :"+statusDetails[2]);

			// Uploading into IGRS_EMP_COMPLAINT_DOC_MAP - Pre Enquiry Document
			// -----------> Insert these details with complaint_id
			String docDetails[] = new String[4];
			docDetails[0] = pDTO.getStrComplaintId();
			docDetails[1] = sForm.getTheFile1().getFileName();
			docDetails[2] = "Suspension";
			docDetails[3] = CommonUtil.getConvertedDate(pDTO.getStrPreliminaryDate());
			/*
			 * System.out.println("docDetails[0] :"+docDetails[0]);
			 * System.out.println("docDetails[1] :"+docDetails[1]);
			 * System.out.println("docDetails[2] :"+docDetails[2]);
			 * System.out.println("docDetails[3] :"+docDetails[3]);
			 */

			// Uploading into IGRS_EMP_COMPLAINT_DOC_MAP - Suspension Signature
			// -----------> Insert these details with complaint_id
			// System.out.println("SIGNATURE");
			String signDetails[] = new String[4];
			signDetails[0] = sDTO.getStrComplaintId();
			signDetails[1] = sForm.getTheFile2().getFileName();
			signDetails[2] = "Signature";
			signDetails[3] = CommonUtil.getConvertedDate(sDTO.getStrDateOfIssue());
			/*
			 * System.out.println("signDetails[0] :"+signDetails[0]);
			 * System.out.println("signDetails[1] :"+signDetails[1]);
			 * System.out.println("signDetails[2] :"+signDetails[2]);
			 * System.out.println("signDetails[3] :"+signDetails[3]);
			 */

			ArrayList list = new ArrayList();
			list.add(preArr);
			list.add(acArr);
			list.add(susArr);
			list.add(statusDetails);
			list.add(docDetails);
			list.add(signDetails);
			// System.out.println(" going to DAO now ...");
			flag = deBAO.submitSuspenseOrderInitiated(list, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public ArrayList getChargesheetComplaintList(String complaintNo) {
		ArrayList list = new ArrayList();
		try {
			ArrayList listComplaint = deBAO
			        .getChargesheetComplaintList(complaintNo);
			for (int i = 0; i < listComplaint.size(); i++) {
				ArrayList listcomplaints = (ArrayList) listComplaint.get(i);
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();

				cceDTO.setComplaintNo((String) listcomplaints.get(0));
				// System.out.println("1 :" + (String) listcomplaints.get(0));

				cceDTO.setCreatedBy((String) listcomplaints.get(1));
				// System.out.println("2 :" + (String) listcomplaints.get(1));

				cceDTO.setComplaintStatus((String) listcomplaints.get(2));
				// System.out.println("3 :" + (String) listcomplaints.get(2));

				cceDTO.setCreatedDate((String) listcomplaints.get(3));
				// System.out.println("4 :" + (String) listcomplaints.get(3));
				list.add(cceDTO);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public CriminalCaseEnquiryDTO getChargesheetDetails(String complaintNo) {
		String[] arr = new String[1];
		arr[0] = complaintNo;
		CriminalCaseEnquiryDTO myDTO = new CriminalCaseEnquiryDTO();

		ArrayList list1 = deBAO.getChargesheetDetails(arr);
		// System.out.println("Size of List :" + list1.size());

		ArrayList list = (ArrayList) list1.get(0);
		myDTO.setName((String) list.get(0));
		myDTO.setAddress((String) list.get(1));
		myDTO.setContactNumber((String) list.get(2));
		myDTO.setEmail((String) list.get(3));
		myDTO.setComplaintNo(complaintNo);

		return myDTO;
	}

	public boolean submitSuspenseOrderAccepted(SuspensionForm sForm,
	        String filePath, String userId) {
		boolean flag = false;
		try {
			// System.out.println("in BD - submitSuspenseOrderAccepted");
			boolean b = false;
			SuspensionOrderDTO sDTO = sForm.getSuspenseDTO();

			// IGRS_EMP_COMPLAINT_COMMENTS ------------------------------->
			// update these details with complaint_id
			String preArr[] = new String[4];
			preArr[0] = userId;// pDTO.getStrComplaintId();
			preArr[1] = CommonUtil.getConvertedDate(sDTO.getStrDateOfIssue());
			preArr[2] = "Suspension";
			preArr[3] = sDTO.getStrComplaintId();
			/*
			 * System.out.println("preArr[0] :"+preArr[0]);
			 * System.out.println("preArr[1] :"+preArr[1]);
			 * System.out.println("preArr[2] :"+preArr[2]);
			 * System.out.println("preArr[3] :"+preArr[3]);
			 */

			// IGRS_EMP_COMPLAINT_DETAILS ------------------------------->
			// UPDATE these details with complaint_id
			String acArr[] = new String[5];
			acArr[0] = "Suspension";
			String maj_min = sDTO.getNatureOfCharge();// IGRS_EMP_COMPLAINT_ACTION_DTLS
			if (maj_min != null && maj_min.equalsIgnoreCase("Major"))
				maj_min = "Y";
			else
				maj_min = "N";
			acArr[1] = maj_min;
			acArr[2] = userId;
			acArr[3] = CommonUtil.getConvertedDate(sDTO.getStrDateOfIssue());
			acArr[4] = sDTO.getStrComplaintId();
			/*
			 * System.out.println("acArr[0] :"+acArr[0]);
			 * System.out.println("acArr[1] :"+acArr[1]);
			 * System.out.println("acArr[2] :"+acArr[2]);
			 * System.out.println("acArr[3] :"+acArr[3]);
			 * System.out.println("acArr[4] :"+acArr[4]);
			 */

			// IGRS_EMP_COMPLAINT_ACTION_DTLS ------------------------------->
			// UPDATE these details with complaint_id
			String susArr[] = new String[10];
			susArr[0] = sDTO.getStrIssuingAuthority();
			susArr[1] = CommonUtil.getConvertedDate(sDTO.getStrDateOfIssue());
			susArr[2] = sDTO.getStrDetails();
			susArr[3] = sDTO.getStrComments();
			susArr[4] = "Suspension";
			susArr[5] = sDTO.getStrFinalCharge();
			susArr[6] = sDTO.getStrPlaceOfIssue();
			susArr[7] = sDTO.getStrAddress();
			susArr[8] = userId;
			susArr[9] = sDTO.getStrComplaintId();
			/*
			 * System.out.println("susArr[0] :"+susArr[0]);
			 * System.out.println("susArr[1] :"+susArr[1]);
			 * System.out.println("susArr[2] :"+susArr[2]);
			 * System.out.println("susArr[3] :"+susArr[3]);
			 * System.out.println("susArr[4] :"+susArr[4]);
			 * System.out.println("susArr[5] :"+susArr[5]);
			 * System.out.println("susArr[6] :"+susArr[6]);
			 * System.out.println("susArr[7] :"+susArr[7]);
			 * System.out.println("susArr[8] :"+susArr[8]);
			 * System.out.println("susArr[9] :"+susArr[9]);
			 */

			// IGRS_EMP_COMPLAINT_STATUS_DTLS ------------------------------->
			// UPDATE these details with complaint_id
			String statusDetails[] = new String[3];
			statusDetails[0] = "Suspension";
			statusDetails[1] = CommonUtil.getConvertedDate(sDTO.getStrDateOfIssue());
			statusDetails[2] = sDTO.getStrComplaintId();
			/*
			 * System.out.println("statusDetails[0] :"+statusDetails[0]);
			 * System.out.println("statusDetails[1] :"+statusDetails[1]);
			 * System.out.println("statusDetails[2] :"+statusDetails[2]);
			 */

			// Uploading into IGRS_EMP_COMPLAINT_DOC_MAP - Suspension Signature
			// -----------> INSERT these details with complaint_id
			// System.out.println("SIGNATURE");
			String signDetails[] = new String[4];
			signDetails[0] = sDTO.getStrComplaintId();
			signDetails[1] = sForm.getTheFile2().getFileName();
			signDetails[2] = "Signature";
			signDetails[3] = CommonUtil.getConvertedDate(sDTO.getStrDateOfIssue());
			/*
			 * System.out.println("signDetails[0] :"+signDetails[0]);
			 * System.out.println("signDetails[1] :"+signDetails[1]);
			 * System.out.println("signDetails[2] :"+signDetails[2]);
			 * System.out.println("signDetails[3] :"+signDetails[3]);
			 */

			ArrayList list = new ArrayList();
			list.add(preArr);
			list.add(acArr);
			list.add(susArr);
			list.add(statusDetails);
			list.add(signDetails);

			// System.out.println(" going to DAO now ...");
			flag = deBAO.submitSuspenseOrderAccepted(list, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public ArrayList getSuspensionComplaintsList(String complaintId) {
		ArrayList list = new ArrayList();
		try {
			ArrayList listComplaint = deBAO
			        .getSuspensionComplaintsList(complaintId);
			for (int i = 0; i < listComplaint.size(); i++) {
				ArrayList listcomplaints = (ArrayList) listComplaint.get(i);
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();

				cceDTO.setComplaintNo((String) listcomplaints.get(0));
				// System.out.println("1 :" + (String) listcomplaints.get(0));

				cceDTO.setCreatedBy((String) listcomplaints.get(1));
				// System.out.println("2 :" + (String) listcomplaints.get(1));

				cceDTO.setComplaintStatus((String) listcomplaints.get(2));
				// System.out.println("3 :" + (String) listcomplaints.get(2));

				cceDTO.setCreatedDate((String) listcomplaints.get(3));
				// System.out.println("4 :" + (String) listcomplaints.get(3));
				list.add(cceDTO);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public ArrayList getSuspensionOrderRecord(String complaintId) {
		ArrayList finalList = new ArrayList();
		try {
			ArrayList finaldocumentlist=new ArrayList();
			ArrayList sorList = deBAO.getSuspensionOrderRecord(complaintId);
			ArrayList complaineelist=(ArrayList)sorList.get(0);
			ArrayList complainerlist=(ArrayList)sorList.get(1);
			ArrayList duputedlist=(ArrayList)sorList.get(2);
			ArrayList suspensiondetailslist=(ArrayList)sorList.get(3);
			ArrayList documentlist=(ArrayList)sorList.get(4);
			
			ArrayList complaineelistdto = (ArrayList) complaineelist.get(0);
			ArrayList complainerlistdto = (ArrayList) complainerlist.get(0);
			ArrayList duputedlistdto = (ArrayList) duputedlist.get(0);
			ArrayList suspensiondetailslistdto=(ArrayList) suspensiondetailslist.get(0);
			
			ArrayList listRecord2 = (ArrayList) sorList.get(1);

			// System.out.println("List1 :"+listRecord1);
			// System.out.println("List2 :"+listRecord2);
			// System.out.println("List1.size :"+listRecord1.size());

			CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();
			// Complainee Details
			cceDTO.setName((String) complaineelistdto.get(0));
			cceDTO.setAddress((String) complaineelistdto.get(1));
			cceDTO.setContactNumber((String) complaineelistdto.get(2));
			cceDTO.setEmail((String) complaineelistdto.get(3));
			// Complainer Details
			cceDTO.setComplainerName((String) complainerlistdto.get(0));
			cceDTO.setComplainerAddress((String) complainerlistdto.get(1));
			cceDTO.setComplainerContactNumber((String) complainerlistdto.get(2));
			cceDTO.setComplainerEmail((String) complainerlistdto.get(3));
			// Case Details
			
			// Deputed Details
			cceDTO.setDpName((String) duputedlistdto.get(0));
			cceDTO.setDpAddress((String) duputedlistdto.get(1));
			cceDTO.setDpContactNumber((String) duputedlistdto.get(2));
			cceDTO.setDpEmail((String) duputedlistdto.get(3));
			cceDTO.setCriminalCaseAbout((String) duputedlistdto.get(4));
			cceDTO.setCriminalCaseDate((String) duputedlistdto.get(5));
			
		
			PreliminaryEnquiryDTO preDTO = new PreliminaryEnquiryDTO();
			SuspensionOrderDTO sDTO = new SuspensionOrderDTO();
			// Pre Enq. Details
			preDTO.setStrPriliminaryDetails((String) suspensiondetailslistdto.get(0));
			preDTO.setStrPriliminaryComments((String) suspensiondetailslistdto.get(1));
			sDTO.setStrFinalCharge((String) suspensiondetailslistdto.get(2));
			preDTO.setStrPreliminaryDate((String) suspensiondetailslistdto.get(3));//

			String maj_min = (String) suspensiondetailslistdto.get(4);
			if (maj_min != null && maj_min.equalsIgnoreCase("Y"))
				maj_min = "Major";
			else
				maj_min = "Minor";
			sDTO.setNatureOfCharge(maj_min);
			
			//preDTO.setStrSuuportingDoc((String) listRecord1.get(19));
			//preDTO.setStrDocumentId((String) listRecord1.get(20));
			// Suspension Order Details
			sDTO.setStrIssuingAuthority((String) suspensiondetailslistdto.get(5));
			sDTO.setStrDateOfIssue((String) suspensiondetailslistdto.get(6));
			sDTO.setStrPlaceOfIssue((String) suspensiondetailslistdto.get(7));
			sDTO.setStrAddress((String) suspensiondetailslistdto.get(8));
			sDTO.setStrDetails((String) suspensiondetailslistdto.get(9));
			sDTO.setStrComments((String) suspensiondetailslistdto.get(10));
			if(documentlist!=null && documentlist.size()>0){
				for(int i=0;i<documentlist.size();i++){
					SuspensionOrderDTO orderDTO = new SuspensionOrderDTO();
					ArrayList documentlistdto=(ArrayList)documentlist.get(i);
					orderDTO.setStrSignatureFileName((String) documentlistdto.get(0));
					orderDTO.setStrDocumentId((String) documentlistdto.get(1));
					finaldocumentlist.add(orderDTO);
				}
			}
			
			

			finalList.add(cceDTO);
			finalList.add(preDTO);
			finalList.add(sDTO);
			finalList.add(finaldocumentlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalList;
	}

	public boolean saveChargeSheetDetails(ChargeSheetReleaseDTO chargeSheetDTO,
	        ArrayList listWitnessDetails, String userId, String filePath) {

		// System.out.println("I am in saveChargeSheetDetails() BD");
		boolean result = false;

		String[] compDetails = new String[3];
		String[] compAction = new String[8];
		String[] compComment = new String[3];
		String[] compStatus = new String[2];
		String[] compBasicProof = new String[4];
		String[] compSigDocMap = new String[4];

		// Details Of IGRS_EMP_COMPLAINT_DETAILS
		compDetails[0] = "Chargesheet";
		compDetails[1] = userId;
		compDetails[2] = chargeSheetDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_ACTION_DTLS
		compAction[0] = "Chargesheet";
		compAction[1] = chargeSheetDTO.getStrCharge();
		compAction[2] = chargeSheetDTO.getStrIssuingAuthority();
		compAction[3] = CommonUtil.getConvertedDate(chargeSheetDTO.getStrDateOfIssue());
		compAction[4] = userId;
		compAction[5] = chargeSheetDTO.getStrDetails();
		compAction[6] = chargeSheetDTO.getStrPlaceOfIssue();
		compAction[7] = chargeSheetDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_COMMENTS
		compComment[0] = userId;
		compComment[1] = "Chargesheet";
		compComment[2] = chargeSheetDTO.getStrComplaintId();

		// Details of IGRS_EMP_COMPLAINT_STATUS_DTLS

		compStatus[0] = "Chargesheet";
		compStatus[1] = chargeSheetDTO.getStrComplaintId();

		String date = null;
		try {
			java.util.Date myDate = new Date();
			date = new java.text.SimpleDateFormat("dd-MMM-yyyy").format(myDate);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		// Details of IGRS_EMP_COMPLAINT_DOC_MAP
		compBasicProof[0] = chargeSheetDTO.getStrComplaintId();
		compBasicProof[1] = chargeSheetDTO.getStrBasisOfCharges();
		compBasicProof[2] = "ChargesheetProof";
		compBasicProof[3] = date;

		ArrayList witnessDoc = new ArrayList();
		for (int i = 0; i < listWitnessDetails.size(); i++) {
			WitnessDTO dto = (WitnessDTO) listWitnessDetails.get(i);
			String[] strArr = new String[4];
			strArr[0] = chargeSheetDTO.getStrComplaintId();
			strArr[1] = dto.getStrSupportingDocName();
			strArr[2] = "Witness";
			strArr[3] = date;
			witnessDoc.add(strArr);
		}
		ArrayList witnessDetails = new ArrayList();
		for (int i = 0; i < listWitnessDetails.size(); i++) {
			WitnessDTO dto = (WitnessDTO) listWitnessDetails.get(i);
			String[] strArrWitness = new String[4];
			strArrWitness[0] = chargeSheetDTO.getStrComplaintId();
			strArrWitness[1] = dto.getStrEnqOfficerName().trim();
			strArrWitness[2] = "Chargesheet";
			strArrWitness[3] = "";
			witnessDetails.add(strArrWitness);
		}

		ArrayList list = new ArrayList();
		list.add(compDetails);
		list.add(compAction);
		list.add(compComment);
		list.add(compStatus);
		list.add(compBasicProof);
		list.add(witnessDoc);
		list.add(witnessDetails);

		result = deBAO.submitChargesheet(list, filePath);
		// System.out.println("Result Status in BD :"+result);
		return result;
	}

	public boolean updateSuspensionToRevoke(String complaintId, String userId) {
		boolean flag = false;
		String actionDtls[] = new String[4]; // IGRS_EMP_COMPLAINT_ACTION_DTLS
		actionDtls[0] = "Revoke";
		actionDtls[1] = userId;
		actionDtls[2] = complaintId;
		actionDtls[3] = "Suspension";

		String compCom[] = new String[3]; // IGRS_EMP_COMPLAINT_COMMENTS
		compCom[0] = "Revoke";
		compCom[1] = complaintId;
		compCom[2] = "Suspension";

		String compDtls[] = new String[4]; // IGRS_EMP_COMPLAINT_DETAILS
		compDtls[0] = "Revoke";
		compDtls[1] = userId;
		compDtls[2] = complaintId;
		compDtls[3] = "Suspension";

		String statusDtls[] = new String[3]; // IGRS_EMP_COMPLAINT_STATUS_DTLS
		statusDtls[0] = "Revoke";
		statusDtls[1] = complaintId;
		statusDtls[2] = "Suspension";

		ArrayList list = new ArrayList();
		list.add(actionDtls);
		list.add(compCom);
		list.add(compDtls);
		list.add(statusDtls);

		flag = deBAO.updateSuspensionToRevoke(list);
		return flag;
	}

	public ArrayList getChargesheetCloseComplaintList(String complaintNo) {
		ArrayList list = new ArrayList();
		try {
			ArrayList listComplaint = deBAO
			        .getChargesheetCloseComplaintList(complaintNo);
			for (int i = 0; i < listComplaint.size(); i++) {
				ArrayList listcomplaints = (ArrayList) listComplaint.get(i);
				CriminalCaseEnquiryDTO cceDTO = new CriminalCaseEnquiryDTO();

				cceDTO.setComplaintNo((String) listcomplaints.get(0));
				// System.out.println("1 :" + (String) listcomplaints.get(0));

				cceDTO.setCreatedBy((String) listcomplaints.get(1));
				// System.out.println("2 :" + (String) listcomplaints.get(1));

				cceDTO.setComplaintStatus((String) listcomplaints.get(2));
				// System.out.println("3 :" + (String) listcomplaints.get(2));

				cceDTO.setCreatedDate((String) listcomplaints.get(3));
				// System.out.println("4 :" + (String) listcomplaints.get(3));
				list.add(cceDTO);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public ArrayList getChargesheetCloseDetails(String complaintNo) {
		String[] arr = new String[1];
		arr[0] = complaintNo;
		CriminalCaseEnquiryDTO myDTO = new CriminalCaseEnquiryDTO();
		ChargeSheetReleaseDTO chargesheetDTO = new ChargeSheetReleaseDTO();

		ArrayList list1 = deBAO.getChargesheetCloseDetails(arr);
		ArrayList myList1 = (ArrayList) list1.get(0);
		ArrayList myList2 = (ArrayList) list1.get(1);

		 System.out.println("Size of List :" + list1);
		 System.out.println("Size of myList1 :" + myList1);
		 System.out.println("Size of myList2 :" + myList2);
		 if(myList1 != null && myList1.size() > 0){
		ArrayList myList3 = (ArrayList) myList1.get(0);
		myDTO.setName((String) myList3.get(0));
		myDTO.setAddress((String) myList3.get(1));
		myDTO.setContactNumber((String) myList3.get(2));
		myDTO.setEmail((String) myList3.get(3));
		myDTO.setComplaintNo(complaintNo);
		chargesheetDTO.setStrCharge((String) myList3.get(4));
		chargesheetDTO.setStrDateOfIssue((String) myList3.get(5));
		chargesheetDTO.setStrIssuingAuthority((String) myList3.get(6));
		chargesheetDTO.setStrPlaceOfIssue((String) myList3.get(7));
		chargesheetDTO.setStrDetails((String) myList3.get(8));
		chargesheetDTO.setStrBasisOfCharges((String) myList3.get(9));
		chargesheetDTO.setStrDocumentId((String) myList3.get(10));
		 }
		System.out.println("After setting ****************************:");
		ArrayList newList = new ArrayList();
		for (int i = 0; i < myList2.size(); i++) {
			ArrayList myList4 = (ArrayList) myList2.get(i);
			WitnessDTO witnessDTO = new WitnessDTO();
			witnessDTO.setStrEnqOfficerName((String) myList4.get(0));
			System.out.println("INSIDE ARRAYLIST :"+(String)myList4.get(0)+"           "+(String)myList4.get(1)+"           "+(String)myList4.get(2));
			witnessDTO.setStrSupportingDocName((String) myList4.get(1));
			witnessDTO.setStrDocumentId((String) myList4.get(2));
			System.out.println("2 :"+(String)myList4.get(1));
			newList.add(witnessDTO);
		}

		ArrayList finalList = new ArrayList();
		finalList.add(myDTO);
		finalList.add(chargesheetDTO);
		finalList.add(newList);
		System.out.println("finalList-------------->>>>>   "+finalList);
		return finalList;
	}

	public boolean saveChargeSheetCloseDetails(String comments,
	        String complaintNo, String userId) {

		// System.out.println("I am in saveChargeSheetCloseDetails() BD");
		boolean result = false;

		String date = null;
		try {
			java.util.Date myDate = new Date();
			date = new java.text.SimpleDateFormat("dd-MMM-yyyy").format(myDate);
		} catch (Exception ex) {
			// System.out.println(ex.toString());
		}
		String[] compDetails = new String[3];
		String[] compAction = new String[4];
		String[] compComment = new String[4];
		String[] compStatus = new String[2];
		String[] compWitness = new String[2];

		// Details Of IGRS_EMP_COMPLAINT_DETAILS
		compDetails[0] = "Closed";
		compDetails[1] = userId;
		compDetails[2] = complaintNo;

		// Details of IGRS_EMP_COMPLAINT_ACTION_DTLS
		compAction[0] = "Closed";
		compAction[1] = userId;
		compAction[2] = comments;
		compAction[3] = complaintNo;

		// Details of IGRS_EMP_COMPLAINT_COMMENTS
		compComment[0] = userId;
		compComment[1] = comments;
		compComment[2] = "Closed";
		compComment[3] = complaintNo;

		// Details of IGRS_EMP_COMPLAINT_STATUS_DTLS

		compStatus[0] = "Closed";
		compStatus[1] = complaintNo;

		// Details of IGRS_EMP_COMPLAINT_WITNES_DTLS
		compWitness[0] = "Closed";
		compWitness[1] = complaintNo;

		ArrayList list = new ArrayList();
		list.add(compDetails);
		list.add(compAction);
		list.add(compComment);
		list.add(compStatus);
		list.add(compWitness);

		result = deBAO.submitChargesheetClose(list);
		//System.out.println("Result Status in BD :"+result);
		return result;
	}
	
	
}
