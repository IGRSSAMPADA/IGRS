package com.wipro.igrs.payment.bd;
/**
 * ===========================================================================
 * File           :   PaymentServiceBD.java
 * Description    :   Represents the  Payment BD Class
 * Author         :   Shreeraj Khare
 * Created Date   :   Jan 12, 2017

 * ===========================================================================
 */
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.wipro.igrs.estamping.bd.DutyCalculationBD;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.payment.constant.CommonConstant;
import com.wipro.igrs.payment.dao.PaymentServiceDAO;
import com.wipro.igrs.payment.dto.PaymentServiceDTO;
import com.wipro.igrs.payment.dto.PaymentYearDto;
import com.wipro.igrs.payment.form.PaymentServiceForm;

public class PaymentServiceBD {
	private Logger logger = (Logger) Logger.getLogger(PaymentServiceBD.class);
	/**
	 * Method : getServiceList Description : returns list of function ids whose
	 * payment flag=Y
	 * 
	 * @param query
	 *            : return Type :ArrayList
	 */
	public ArrayList getServiceList(String languageLocale) throws Exception {
		PaymentServiceDAO dao2 = new PaymentServiceDAO();
		ArrayList flist = dao2.getServiceList();
		ArrayList list = new ArrayList();

		if (flist != null) {
			for (int i = 0; i < flist.size(); i++) {
				ArrayList lst = (ArrayList) flist.get(i);
				PaymentServiceDTO dto = new PaymentServiceDTO();
				dto.setValue((String) lst.get(0));
				if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)) {
					dto.setName((String) lst.get(1));
				} else {
					dto.setName((String) lst.get(2));
				}
				dto.setSpAmount((String) lst.get(3));
				dto.setRuAmount((String) lst.get(4));
				dto.setDrAmount((String) lst.get(5));
				dto.setServiceID((String) lst.get(6));
				list.add(dto);
			}
		}
		return list;
	}

	// added by Sanjeev Kumar
	public ArrayList getYearRange(String languageLocale) throws Exception {
		PaymentServiceDAO paymentServiceDAO = new PaymentServiceDAO();
		ArrayList arrayYearrangelist = paymentServiceDAO.getYearRange();
		ArrayList list = new ArrayList();

		if (arrayYearrangelist != null) {
			for (int i = 0; i < arrayYearrangelist.size(); i++) {
				ArrayList lst = (ArrayList) arrayYearrangelist.get(i);
				PaymentYearDto dto = new PaymentYearDto();
				dto.setToYear((String) lst.get(0));
				dto.setFromYear((String) lst.get(0));
				list.add(dto);

			}
		}

		return list;
	}

	/**
	 * Method : getServiceList Description : returns list of function ids whose
	 * payment flag=Y
	 * 
	 * @param query
	 *            : return Type :ArrayList
	 */
	public ArrayList getOfflineServiceList(String languageLocale, String userID) throws Exception {
		PaymentServiceDAO dao2 = new PaymentServiceDAO();
		ArrayList flist = dao2.getOfflineServiceList();
		ArrayList list = new ArrayList();
		String type = dao2.gettype(userID);
		if (flist != null) {
			for (int i = 0; i < flist.size(); i++) {
				ArrayList lst = (ArrayList) flist.get(i);
				PaymentServiceDTO dto = new PaymentServiceDTO();
				dto.setOffServiceID((String) lst.get(0));
				dto.setFunID((String) lst.get(1));
				if ("3".equalsIgnoreCase(type) || "4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {// for
																																			// SP
					dto.setFees((String) lst.get(3));
				} else if ("2".equalsIgnoreCase(type)) { // for RU
					dto.setFees((String) lst.get(2));
				} else { // for Internal
					dto.setFees((String) lst.get(4));
				}
				if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)) {
					dto.setName((String) lst.get(5));
				} else {
					dto.setName((String) lst.get(6));
				}
				if (lst.get(7) != null) {
					if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)) {
						dto.setUserEnterableParam((String) lst.get(7));
					} else {
						dto.setUserEnterableParam((String) lst.get(8));
					}
					dto.setUserEnterableValue(dto.getFees());
				} else {
					dto.setUserEnterableParam(null);
				}
				dto.setServiceDesc((String) lst.get(9));

				if (lst.get(10) != null) {
					if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH))
						dto.setReferenceName((String) lst.get(10));
					else
						dto.setReferenceName((String) lst.get(11));
				}

				list.add(dto);
			}
		}
		return list;
	}
	/**
	 * Method : validateRefID Description : returns list of function ids whose
	 * payment flag=Y
	 * 
	 * @param query
	 *            : return Type :boolean
	 */
	public String[] validateRefID(String refID, String serviceID) throws Exception {
		PaymentServiceDAO dao2 = new PaymentServiceDAO();
		String[] arr = new String[4];
		double paidAmt = 0.0, payableAmt = 0.0;
		ArrayList list = dao2.validateReff(refID, serviceID);
		if (list == null || list.isEmpty())
			throw new Exception("10003");
		if (list != null) {
			ArrayList subList = (ArrayList) list.get(0);
			arr[0] = subList.get(0).toString();// PTID
			arr[1] = subList.get(1).toString();// PAID
			arr[2] = subList.get(2).toString();// PAYABLE
			arr[3] = subList.get(3).toString();// UNIQUE ID
			paidAmt = arr[1] == null ? 0.0 : Double.parseDouble(arr[1]);
			payableAmt = arr[2] == null ? 0.0 : Double.parseDouble(arr[2]);
			if (arr[0] != null && paidAmt >= payableAmt)
				throw new Exception("10002");
			else {
				payableAmt = payableAmt - paidAmt;
				arr[2] = String.valueOf(payableAmt);
			}
		}
		return arr;

	}
	public String gettype(String userId) throws Exception {
		PaymentServiceDAO dao2 = new PaymentServiceDAO();

		String type = dao2.gettype(userId);

		return type;

	}
	public ArrayList getDetails(String userId, PaymentServiceDTO objDashBoardDTO1) {
		PaymentServiceDAO dao2 = new PaymentServiceDAO();
		ArrayList details = new ArrayList();
		details = dao2.getdetails(userId);

		ArrayList list = new ArrayList();

		if (details != null) {
			for (int i = 0; i < details.size(); i++) {
				ArrayList lst = (ArrayList) details.get(i);
				objDashBoardDTO1.setDistrictid((String) lst.get(0));
				objDashBoardDTO1.setDistrictname((String) lst.get(1));
				list.add(objDashBoardDTO1);
			}
		}
		return list;
	}
	public ArrayList getruDetails(String userId, PaymentServiceDTO objDashBoardDTO1, String txnId, String language) {
		PaymentServiceDAO dao2 = new PaymentServiceDAO();
		ArrayList details = new ArrayList();
		details = dao2.getrudetails(userId, objDashBoardDTO1, txnId, language);

		ArrayList list = new ArrayList();

		if (details != null) {
			for (int i = 0; i < details.size(); i++) {
				ArrayList lst = (ArrayList) details.get(i);
				objDashBoardDTO1.setDistrictid((String) lst.get(0));
				objDashBoardDTO1.setDistrictname((String) lst.get(1));
				list.add(objDashBoardDTO1);
			}
		}
		return list;
	}
	public ArrayList getPaymentDetails(String serviceID) {
		return new PaymentServiceDAO().getPaymentDetails(serviceID);
	}

	public String insertPaymentDetls(PaymentServiceForm pForm) {
		return new PaymentServiceDAO().insertPaymentDetls(pForm);
	}
	public ArrayList getPaymentRecord(String referenceID, String _funcId) {
		return new PaymentServiceDAO().getPaymentRecord(referenceID, _funcId);
	}
	public boolean insertPayable(String referenceID, String _funcId, String payableAmt, String _userID) throws Exception {
		return new PaymentServiceDAO().insertPayable(referenceID, _funcId, payableAmt, _userID);
	}
	public String insertOfflinePaymentDetls(PaymentServiceForm pForm, String _funcId) {
		return new PaymentServiceDAO().insertOfflinePaymentDetls(pForm, _funcId);
	}
	/**
	 * Method : validateRefID Description : returns list of function ids whose
	 * payment flag=Y
	 * 
	 * @param query
	 *            : return Type :boolean
	 */
	public String[] validateRefIDView(PaymentServiceForm pForm, String languageLocale, String userID) throws Exception {
		PaymentServiceDAO dao2 = new PaymentServiceDAO();
		String[] arr = new String[7];
		double paidAmt = 0.0, payableAmt = 0.0;
		ArrayList list = dao2.validateReffView(pForm.getRefID(), pForm.getServiceID(), userID);
		if (list == null || list.isEmpty())
			throw new Exception("10003");
		if (list != null) {
			ArrayList subList = (ArrayList) list.get(0);

			pForm.setPaymentID(subList.get(0).toString());// PTID
			pForm.setPaidAmt(subList.get(1).toString());// PAID
			arr[2] = subList.get(2).toString();// PAYABLE
			arr[3] = subList.get(3).toString();// UNIQUE ID
			pForm.setUserName(subList.get(4).toString());// USER NAME
			if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)) {
				pForm.setServiceName(subList.get(5).toString());
				pForm.setPaymentMode(subList.get(9).toString());
			} else {
				pForm.setServiceName(subList.get(6).toString());
				pForm.setPaymentMode(subList.get(8).toString());
			}
			pForm.setPaymentDate(subList.get(7).toString());

			paidAmt = arr[1] == null ? 0.0 : Double.parseDouble(arr[1]);
			payableAmt = arr[2] == null ? 0.0 : Double.parseDouble(arr[2]);
			if (pForm.getPaymentID() == null)
				throw new Exception("10002");

		}
		if ("SER_009".equalsIgnoreCase(pForm.getServiceID())) {
			
			String parentKey=dao2.validateEfilePayment(pForm.getRefID(), pForm.getServiceID(), userID);
			boolean boo = dao2.updateEfilePayment(pForm.getRefID(), pForm.getServiceID(), userID, parentKey);
		}
		return arr;

	}

	public ArrayList getOfflinePaymentList(String languageLocale, String serviceID, String fromDate, String toDate, String userID, String officeId) throws Exception {
		ArrayList<PaymentServiceDTO> list = new ArrayList<PaymentServiceDTO>();
		ArrayList subList = new PaymentServiceDAO().getOfflinePaymentList(serviceID, fromDate, toDate, userID, officeId);
		if (subList != null && !subList.isEmpty()) {
			for (int i = 0; i < subList.size(); i++) {
				PaymentServiceDTO psDto = new PaymentServiceDTO();
				ArrayList subSetList = (ArrayList) subList.get(i);
				psDto.setPaymentTxnID(subSetList.get(0).toString());
				psDto.setPaidAmt(subSetList.get(1).toString());
				psDto.setRefernceID(subSetList.get(2).toString());
				psDto.setName(subSetList.get(3).toString());
				if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH))
					psDto.setServiceDesc(subSetList.get(4).toString());
				else
					psDto.setServiceDesc(subSetList.get(5).toString());
				psDto.setPayDate(subSetList.get(6).toString());
				if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH))
					psDto.setPayMode(subSetList.get(8).toString());
				else
					psDto.setPayMode(subSetList.get(7).toString());
				if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH))
					psDto.setUserEnterableParam(subSetList.get(9).toString());
				else
					psDto.setUserEnterableParam(subSetList.get(10).toString());
				psDto.setUserEnterableValue(subSetList.get(11).toString());
				psDto.setConsumptionStatus(subSetList.get(13).toString());
				list.add(psDto);
			}
		} else {
			throw new Exception("10003");
		}
		return list;
	}

	/**
	 * Method : validateRefID Description : returns list of function ids whose
	 * payment flag=Y
	 * 
	 * @param query
	 *            : return Type :boolean
	 */
	public boolean validateConsumedReff(PaymentServiceForm pForm, String languageLocale) throws Exception {
		ArrayList list = new PaymentServiceDAO().validateConsumedReff(pForm.getRefID(), pForm.getOffServiceID());
		boolean boo = false;
		if (list == null || list.isEmpty())
			throw new Exception("10003");
		if (list != null) {
			ArrayList subList = (ArrayList) list.get(0);
			if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH))
				pForm.setServiceName(subList.get(0).toString());
			else
				pForm.setServiceName(subList.get(1).toString());
			pForm.setPaymentID(subList.get(2).toString());// PTID
			pForm.setPaidAmt(subList.get(3).toString());// PAID
			if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH))
				pForm.setEnterableName(subList.get(4).toString());
			else
				pForm.setEnterableName(subList.get(5).toString());
			pForm.setEnterableValue(subList.get(6).toString());
			pForm.setPaymentDate(subList.get(7).toString());
			pForm.setUserName(subList.get(8).toString());
			pForm.setConsumption(subList.get(9).toString());
			// pForm.setConsumerOffice(subList.get(10).toString());
			// pForm.setConsumerID(subList.get(11).toString());

			if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH))
				pForm.setOfficeName(subList.get(10).toString());
			else
				pForm.setOfficeName(subList.get(11).toString());
			pForm.setEmpName(subList.get(12).toString());
			pForm.setConsumeDate(subList.get(13).toString());
			if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH))
				pForm.setPaymentMode(subList.get(14).toString());
			else
				pForm.setPaymentMode(subList.get(15).toString());
			pForm.setOfflineRefValue(subList.get(16).toString());
			if (languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH))
				pForm.setOfflineRefID(subList.get(17).toString());
			else
				pForm.setOfflineRefID(subList.get(18).toString());
			if (pForm.getConsumption().equalsIgnoreCase("CONSUMED"))
				boo = true;
			if (pForm.getPaymentID().equalsIgnoreCase("--"))
				throw new Exception("10004");
			// START | to add case number and party name for service id 105 &
			// 106 by santosh
			// START | to add case number and party name for service id 114 ,
			// 101 , 102,103,104 and 107 by sanjeev
			if ("105".equalsIgnoreCase(pForm.getOffServiceID()) || "106".equalsIgnoreCase(pForm.getOffServiceID()) || "114".equalsIgnoreCase(pForm.getOffServiceID()) || "101".equalsIgnoreCase(pForm.getOffServiceID()) || "102".equalsIgnoreCase(pForm.getOffServiceID()) || "103".equalsIgnoreCase(pForm.getOffServiceID()) || "104".equalsIgnoreCase(pForm.getOffServiceID()) || "107".equalsIgnoreCase(pForm.getOffServiceID())) {
				// pForm.setCaseNo(subList.get(19).toString());
				pForm.setOfflineRefValue(subList.get(19).toString());
				pForm.setPartyName(subList.get(20).toString());

				if ("101".equalsIgnoreCase(pForm.getOffServiceID()) || "102".equalsIgnoreCase(pForm.getOffServiceID())) {
					pForm.setfYear(subList.get(21).toString());
					pForm.settYear(subList.get(22).toString());
				}
			}// END | to add case number and party name for service id 105 & 106
				// by santosh
			// End | to add case number and party name for service id 114 , 101
			// , 102,103,104 and 107 by sanjeev

		}
		return boo;

	}
	public boolean updateConsume(String payID, String referenceID, String empID, String offcID) throws Exception {
		return new PaymentServiceDAO().updateConsume(payID, referenceID, empID, offcID);
	}

	/**
	 * Method : Number of Days Findout Description : return of days
	 * 
	 * @param query
	 *            : auther :Sanjeev
	 */

	// added by sanjeev i---28 Aug 2018
	public String getNumberOfDays() {
		return new PaymentServiceDAO().getNumberOfDays();
	}

	public ArrayList getDistrictList(String language){
		PaymentServiceDAO dao2 = new PaymentServiceDAO();
		ArrayList flist = dao2.getDistrictList();
		ArrayList list = new ArrayList();
		if(flist!=null){
			for(int i = 0; i<flist.size(); i++){
				ArrayList lst = (ArrayList) flist.get(i);
				PaymentServiceDTO dto = new PaymentServiceDTO();
				dto.setDistrictid((String)lst.get(0));
				if(language.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					dto.setServiceDistrictName((String)lst.get(1));
				}else{
					dto.setServiceDistrictName((String)lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
}
