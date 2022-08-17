package com.wipro.igrs.visitBooking.bd;

///**
// * ===========================================================================
// * File           :   VisitBookingBD.java
// * Description    :   Represents the Business Delegate Class
//
// * Author         :   pavani Param
// * Created Date   :   Apr 08, 2008.
//
// * ===========================================================================
// */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import com.wipro.igrs.visitBooking.form.VisitBookingForm;
import com.wipro.igrs.visitBooking.bo.VisitBO;
import com.wipro.igrs.visitBooking.dto.VisitBookingDTO;
import com.wipro.igrs.common.IGRSCommon;

///**
// * 
// * @author pavpapa
// *
// */

public class VisitBookingBD {

	boolean blnInsval;
	boolean blnInsRem;
	ArrayList list = null;
	
	VisitBO bo;
	IGRSCommon objCommon;
	private Logger logger = (Logger) Logger.getLogger(VisitBookingBD.class);
	

	public ArrayList getCountry() throws Exception {
		bo = new VisitBO();
		ArrayList ret = bo.getCountry();
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				VisitBookingDTO dto = new VisitBookingDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	public ArrayList getState(String countryId) throws Exception {
		bo = new VisitBO();
		ArrayList ret = bo.getState(countryId);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				VisitBookingDTO dto = new VisitBookingDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));
				list.add(dto);
			}
			logger.info("VisitBookingBD----getState  " + list);
		}
		return list;
	}

	public ArrayList getDistrict(String stateId) throws Exception {
		bo = new VisitBO();
		ArrayList ret = bo.getDistrict(stateId);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				VisitBookingDTO dto = new VisitBookingDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));
				list.add(dto);
			}
		}
		logger.info(" VisitBookingBD --   getDistrict()............... "+ list);
		return list;
	}

	public ArrayList getphotoIdList() throws Exception {
		bo = new VisitBO();
		ArrayList ret = bo.getphotoIdList();
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				VisitBookingDTO dto = new VisitBookingDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));
				list.add(dto);
			}

		}
		return list;
	}

	/**
	 * for eStamp Id generation
	 * @return eStampID
	 */
	public String visitIDgenerator() {
		return "visit" + new Date().getTime();

	}

	public boolean submitVisitInfo(VisitBookingForm form, String roleId, String funId, String userId) throws Exception 
	{
		bo = new VisitBO();
		logger.info(" VisitBookingBD  -- Eenter in to   submitVisitInfo()");
		String visitDetIns[] = new String[36];
		String visitRemIns[] = new String[4];
		String visitTxnId = null;
		
		objCommon = new IGRSCommon();
		visitTxnId = objCommon.getSequenceNumber("IGRS_ESTAMP_SEQ", "VisTxnId");
		logger.info("VisitBookingBD  visitTxnId ==  "+ visitTxnId);
		form.setVisitTxnId(visitTxnId);
		
		
		// --start--values are inserted in to String array  visitDetIns.
		visitDetIns[0] = form.getVisitTxnId();
		visitDetIns[1] = form.getFirstName();
		visitDetIns[2] = form.getMiddleName();
		visitDetIns[3] = form.getLastName();
		visitDetIns[4] = form.getGender();
		visitDetIns[5] = form.getAge();
		visitDetIns[6] = form.getFatherName();
		visitDetIns[7] = form.getMotherName();
		visitDetIns[8] = form.getCountry();
		visitDetIns[9] = form.getState();
		visitDetIns[10] = form.getDistrict();
		visitDetIns[11] = form.getAddress();
		visitDetIns[12] = form.getPostalCode();
		visitDetIns[13] = form.getPhoneNumber();
		visitDetIns[14] = form.getMobileNumber();
		visitDetIns[15] = form.getEmailID();
		visitDetIns[16] = form.getPhotoID();
		visitDetIns[17] = form.getIdNo();
		visitDetIns[18] = form.getBankName();
		visitDetIns[19] = form.getBankAddr();
		visitDetIns[20] = form.getReqFirstName();
		visitDetIns[21] = form.getReqMiddleName();
		visitDetIns[22] = form.getReqLastName();
		visitDetIns[23] = form.getReqReason();
		visitDetIns[24] = form.getReqApplNo();
		visitDetIns[25] = getOracleDate(form.getReqDate());
		visitDetIns[26] = form.getReqTime();
		visitDetIns[27] = form.getReqPhoneNo();
		visitDetIns[28] = form.getReqMobileNo();
		visitDetIns[29] = form.getVisitDistrict();
		visitDetIns[30] = form.getVisitAddr();
		visitDetIns[31] = form.getVisitPCode();
		visitDetIns[32] = form.getTotal();
		visitDetIns[33] = form.getFee();
		visitDetIns[34] = form.getOthers();
		visitDetIns[35] = form.getPmtTxnId();
		

		// --end--values are inserted in to String array visitDetIns

		blnInsval = bo.visitDetIns(visitDetIns,roleId,funId,userId);

		visitRemIns[0] = form.getVisitTxnId();//VisitTXnid  is inserted 
		visitRemIns[1] = "DRO_ID";
		visitRemIns[2] = "USER_ID";
		visitRemIns[3] = form.getVisitRemarks();

		// --start--Remarks  are inserted in to String array  
		if (blnInsval) {
			blnInsRem = bo.visitRemIns(visitRemIns,roleId,funId,userId);
		}
		logger.info("Visist Booking BD -- AFTER insert Visit Booking Remarks Details"+ blnInsval);
		return blnInsRem;
	}

	public boolean submitVisitUpdRem(VisitBookingForm form,String roleId, String funId, String userId) throws Exception 
	{
		
		bo = new VisitBO();
		String visitRemIns[] = new String[4];
		visitRemIns[0] = form.getReferenceID();//reference Id  is inserted 
		visitRemIns[1] = "DRO_ID";
		visitRemIns[2] = "USER_ID";
		visitRemIns[3] = form.getVisitRemarks();

		// --start--Updated Remarks values  are inserted.

		blnInsRem = bo.visitRemIns(visitRemIns,roleId,funId,userId);
		logger.info("Visist Booking BD -- submitVisitUpdRem()  AFTER insert Visit Booking updated Remarks Details"+ blnInsRem);

		// --End--Updated Remarks values  are inserted.

		return blnInsRem;

	}

	public static String getOracleDate(String DateFormat) throws Exception
	{
		String finalDate = "";
		if (DateFormat != null && !DateFormat.equalsIgnoreCase("")) {
			StringTokenizer st = new StringTokenizer(DateFormat, "/");
			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();
			String inputDate = day + "-" + month + "-" + year;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			Date newDate;
			try {
				newDate = formatter.parse(inputDate);
				SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
				finalDate = format.format(newDate);

			} catch (Exception e) {
				
			}

		}
		return finalDate;
	}

	public VisitBookingForm getRefId(VisitBookingForm form) throws Exception
	{
		bo = new VisitBO();
		list = new ArrayList();
		ArrayList retList1 = new ArrayList();

		try {
			list = bo.getRefId();
			retList1 = (ArrayList) list.get(0);
			logger.info("Wipro in VisitBooking BD - getRefId() after EXCUTIN QUERY retList1="+ retList1);
			form.setReqApplNo((String) retList1.get(0));
			form.setVisitTxnId((String) retList1.get(1));
		} catch (Exception e) {
			
			logger.debug("VisitBooking BD -- Exception in getRefId()" + e);
		}
		return form;
	}

	public VisitBookingForm printVisitBookDet(VisitBookingForm form)
			throws Exception {
		bo = new VisitBO();
		list = new ArrayList();
		ArrayList retList1 = new ArrayList();
		String visitRefId = form.getVisitTxnId();

		try {
			list = bo.printVisitBookDet(visitRefId);
			retList1 = (ArrayList) list.get(0);
			form.setReqApplNo((String) retList1.get(0));
			form.setVisitTxnId((String) retList1.get(1));
			form.setDoReq((String) retList1.get(2));
			form.setFirstName((String) retList1.get(3));
			form.setMiddleName((String) retList1.get(4));
			form.setLastName((String) retList1.get(5));
			form.setReqFirstName((String) retList1.get(6));
			form.setReqMiddleName((String) retList1.get(7));
			form.setReqLastName((String) retList1.get(8));
			form.setReqReason((String) retList1.get(9));
			form.setReqDate((String) retList1.get(10));
			form.setReqTime((String) retList1.get(11));
			form.setVisitAddr((String) retList1.get(12));
			form.setVisitDistrict((String) retList1.get(13));
			form.setVisitPCode((String) retList1.get(14));
			form.setTotal((String) retList1.get(15));
			logger.info("Wipro in Visist Booking BD  - printVisitBookDet() AFTER EXCUTIN QUERY"+ list);
			
		} catch (Exception e) {
			
			logger.debug("Visist Booking BD ---  Exception in printVisitBookDet(). "+ e);
		}

		return form;
	}

	public VisitBookingForm getVisitViewResult(VisitBookingForm form)
			throws Exception {

		bo = new VisitBO();
		ArrayList visitVRList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList retList1 = null;
		list = bo.getVisitViewResult(form);
		try {
			if (list != null) {
				for (int i = 0; i < list.size(); i++)
				{
					form = new VisitBookingForm();
					retList1 = (ArrayList) list.get(i);
					form.setReferenceID(retList1.get(0).toString());
					form.setDateOfRequest(retList1.get(1).toString());
					form.setApplicationNo(retList1.get(2).toString());
					form.setDateOfVisit(retList1.get(3).toString());
					if(((String)retList1.get(4)).equalsIgnoreCase("A"))
					form.setStatus("Activate");
					else 
					form.setStatus("De activate");
					visitVRList.add(form);
					logger.info("Wipro in Visist Booking BD --  getVisitViewResult()  visitVRList= "+ visitVRList);

				}
				form.setVisitViewList(visitVRList);
			}

		} catch (Exception e) 
		{
			
			logger.debug(" Visist Booking BD --  Exception in getVisitViewResult() " + e);
		}

		return form;
	}

	public VisitBookingForm getVisitUpdateResult(VisitBookingForm form)
			throws Exception {
		bo = new VisitBO();
		ArrayList visitVRList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList retList1 = null;
		
		try {
			list = bo.getVisitUpdateResult(form);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					form = new VisitBookingForm();
					retList1 = (ArrayList) list.get(i);
					form.setReferenceID(retList1.get(0).toString());
					form.setDateOfRequest(retList1.get(1).toString());
					form.setApplicationNo(retList1.get(2).toString());
					form.setDateOfVisit(retList1.get(3).toString());
					form.setStatus(retList1.get(4).toString());
					visitVRList.add(form);
					logger.info("Wipro in Visist Booking BD  getVisitUpdateResult()  visitVRList= "+ visitVRList);

				}
				form.setVisitViewList(visitVRList);
			}

		} catch (Exception e)
		{
			
			logger.debug("Visist Booking BD --  Exception in getVisitUpdateResult() "+ e);
		}
		return form;
	}

	public VisitBookingForm getVisitBookViewDet(VisitBookingForm form) throws Exception
	{

		bo = new VisitBO();
		list = new ArrayList();
		ArrayList retRemList = new ArrayList();
		ArrayList retList1 = new ArrayList();
		
		String visitRefId = form.getReferenceID();

		try {
			list = bo.getVisitBookViewDet(visitRefId);
			retList1 = (ArrayList) list.get(0);
			//--START--Applicant Details.
			form.setReferenceID((String) retList1.get(0));
			form.setFirstName((String) retList1.get(1));
			form.setMiddleName((String) retList1.get(2));
			form.setLastName((String) retList1.get(3));
			form.setGender((String) retList1.get(4));
			form.setAge((String) retList1.get(5));
			form.setFatherName((String) retList1.get(6));
			form.setMotherName((String) retList1.get(7));
			form.setAddress((String) retList1.get(8));
			form.setCountry((String) retList1.get(9));
			form.setState((String) retList1.get(10));
			form.setDistrict((String) retList1.get(11));
			form.setPostalCode((String) retList1.get(12));
			form.setPhoneNumber((String) retList1.get(13));
			form.setMobileNumber((String) retList1.get(14));
			form.setEmailID((String) retList1.get(15));
			form.setPhotoID((String) retList1.get(16));
			form.setIdNo((String) retList1.get(17));
			form.setBankName((String) retList1.get(18));
			form.setBankAddr((String) retList1.get(19));
			//--END--Applicant Details.

			//--START--Request Details.
			form.setReqApplNo((String) retList1.get(20));
			form.setReqFirstName((String) retList1.get(21));
			form.setReqMiddleName((String) retList1.get(22));
			form.setReqLastName((String) retList1.get(23));
			form.setReqPhoneNo((String) retList1.get(24));
			form.setReqMobileNo((String) retList1.get(25));
			form.setReqReason((String) retList1.get(26));
			form.setReqDate((String) retList1.get(27));
			form.setReqTime((String) retList1.get(28));
			form.setVisitAddr((String) retList1.get(29));
			form.setVisitDistrict((String) retList1.get(30));
			form.setVisitPCode((String) retList1.get(31));
			form.setTotal((String) retList1.get(32));
			form.setFee((String) retList1.get(33));
			form.setOthers((String) retList1.get(34));
			//--END--Request Details.

			
			list = bo.getVisitBookRemDet(visitRefId);
			VisitBookingDTO dto;
			for (int i = 0; i < list.size(); i++) {
				dto = new VisitBookingDTO();
				retList1 = new ArrayList();
				retList1 = (ArrayList) list.get(i);
				dto.setVisitRem((String) retList1.get(1));
				retRemList.add(dto);
			}
			form.setRemarksList(retRemList);
		} catch (Exception e)
		{
			logger.info("this is Exception in Visist Booking BD - getVisitBookViewDet()"+ e);
		}

		return form;
	}

	/**
	 * getting  Visit Booking Fee Details.
	 * @param functionId 
	 * @return list
	 * @throws Exception
	 */
	public String getVisitBookFeeVal(String functionId) throws Exception
	{
		bo = new VisitBO();
		ArrayList list = new ArrayList();
		ArrayList retList1 = new ArrayList();
		String feeVal = "";

		try {
			
			list = bo.getVisitBookFeeVal(functionId);
			retList1 = (ArrayList) list.get(0);
			feeVal = ((String) retList1.get(1));
			logger.info("VisitBookingBD in getVisitBookFeeVal(): feeVal="+ feeVal);
			logger.debug("VisitBookingBD in getVisitBookFeeVal(): feeVal="+ feeVal);
		} catch (Exception e)
		{
			logger.info("VisitBookingBD -- Exception in VisitBookingBD - getVisitBookFeeVal():"+ e);
		}
		return feeVal;
	}

	/**
	 * for calculating  othersFeeDuty
	 * @param _refFunId
	 * @return othersFeeDuty
	 */
	public ArrayList getOthersFeeDuty(String _retFunId,String _serId,String _userType) throws Exception 
	{
		logger.info("VisitBooking BD --  getVisitOthersDuty() _retFunId= "+ _retFunId);
		bo = new VisitBO();
		ArrayList OthersDuty = null;
		try {
			OthersDuty = bo.getOthersFeeDuty( _retFunId, _serId, _userType);
		} catch (Exception e) {
			logger.info(" VisitBookingBD --- Exception in  getVisitOthersDuty()  " + e);
			e.printStackTrace();
		}

		return OthersDuty;
	}
	/**
	 * check application no
	 * @param reqApplNo
	 * @return status
	 */
	public String checkAppNo(String reqApplNo) {
		bo = new VisitBO();
		return bo.checkAppNo(reqApplNo);
	}

}
