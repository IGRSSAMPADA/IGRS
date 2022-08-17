package com.wipro.igrs.slotbooking.bd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.wipro.igrs.ACL.dto.OfficeDTO;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.payment.dao.PaymentServiceDAO;
import com.wipro.igrs.slotbooking.constant.CommonConstant;

import com.wipro.igrs.slotbooking.dao.SlotBookDAO;
import com.wipro.igrs.slotbooking.dto.CommonDropDownDTO;
import com.wipro.igrs.slotbooking.dto.SlotBookDTO;
import com.wipro.igrs.slotbooking.dto.SlotBookingUrgentDTO;
import com.wipro.igrs.slotbooking.dto.SroSlotSheduleDTO;
import com.wipro.igrs.slotbooking.form.SlotBookingUrgentForm;

import org.apache.log4j.Logger;

/**
 * @author venshis
 * 
 */
public class SlotBookBD {
	private Logger logger = (Logger) Logger.getLogger(SlotBookBD.class);
	SlotBookDAO dao;

	public SlotBookBD() {
		dao = new SlotBookDAO();
	}

	/**
	 * @return
	 */
	public ArrayList getDistrictName() throws Exception {

		return dao.getDistrictName();

	}

	/**
	 * @return
	 */
	public ArrayList getSroName(String _distid, String lang) throws Exception {

		return dao.getSroName(_distid, lang);

	}

	/**
	 * @return
	 */
	public ArrayList getSroAvailableSlot(String _sroid) throws Exception {

		return dao.getSroAvailableSlots(_sroid);
	}
	public ArrayList getSroAvailableSlot1(String _sroid, String selectDate) throws Exception {

		return dao.getSroAvailableSlots1(_sroid, selectDate);
	}
	/**
	 * @return
	 */
	public ArrayList getAvailableSro(ArrayList _slottimelist, SlotBookDTO _slotdto) throws Exception {
		_slotdto.setSelectedDate(_slotdto.getSlotdate());
		String selectdate = getDate(_slotdto.getSlotdate());
		_slotdto.setSlotdate(selectdate);
		return dao.getAvailableSrs(_slottimelist, _slotdto);
	}

	/**
	 * @return
	 */
	public ArrayList check(SlotBookDTO slotdto) throws Exception {

		return dao.check(slotdto);
	}

	/**
	 * @return
	 */
	public ArrayList getSroSlotSheduleDetails(SroSlotSheduleDTO _srodto, String lang) throws Exception {
		ArrayList retlist = new ArrayList();
		SroSlotSheduleDTO retdto = null;// new SroSlotSheduleDTO();
		String selectdate = getDate(_srodto.getSelectDate());
		_srodto.setSelectDate(selectdate);
		ArrayList srodisp = dao.getSroSlotSheduleDetails(_srodto, lang);
		ArrayList srtimeslot = dao.getSroAvailableSlots(_srodto.getSno());
		if (srtimeslot.size() > 0) {
			for (int i = 0; i < srtimeslot.size(); i++) {
				retdto = new SroSlotSheduleDTO();
				SlotBookDTO timedto = (SlotBookDTO) srtimeslot.get(i);
				retdto.setSlottime(timedto.getTimesSlot());
				retdto.setSno("" + (i + 1));
				retdto.setSlottimeId(timedto.getTimeSlotId());
				if (srodisp.size() > 0) {
					for (int j = 0; j < srodisp.size(); j++) {
						SroSlotSheduleDTO dto = new SroSlotSheduleDTO();
						dto = (SroSlotSheduleDTO) srodisp.get(j);
						if (timedto.getTimeSlotId().equals(dto.getSlottimeId())) {
							retdto.setStatus(dto.getStatus());
							// Start===Added by SreeLatha if remarks value is
							// empty then inserting null
							if (dto.getRemarks() != "") {
								retdto.setRemarks(dto.getRemarks());

							} else {

								retdto.setRemarks(null);

							}
							// End===Added by SreeLatha if remarks value is
							// empty then inserting null

						}

					}

				}
				retlist.add(retdto);
			}

		}
		return retlist;
	}

	public ArrayList getSroSlotSheduleDetails1(SroSlotSheduleDTO _srodto) {
		ArrayList slotBkViewList = new ArrayList();
		try {
			slotBkViewList = dao.getSroSlotSheduleDetails1(_srodto);
		}

		catch (Exception e) {
			logger.debug("Exception at getSlotBkViewDetails in BD " + e);
		}
		return slotBkViewList;
	}

	/**
	 * @return
	 */

	public ArrayList getSrobookedSlotDetails(SroSlotSheduleDTO _srodto, String[] param) throws Exception {
		ArrayList retlist = new ArrayList();
		SroSlotSheduleDTO retdto = null;// new SroSlotSheduleDTO();
		String selectdate = getDate(_srodto.getSelectScheduleDate());

		ArrayList srodisp = dao.getSrobookedSlotDetails(_srodto, param);
		ArrayList srtimeslot = dao.getSroAvailableSlots(_srodto.getSno());
		int k = 1;
		if (srtimeslot.size() > 0) {

			for (int i = 0; i < srtimeslot.size(); i++) {

				SlotBookDTO timedto = (SlotBookDTO) srtimeslot.get(i);

				if (srodisp.size() > 0) {

					for (int j = 0; j < srodisp.size(); j++) {
						SroSlotSheduleDTO dto = new SroSlotSheduleDTO();
						dto = (SroSlotSheduleDTO) srodisp.get(j);

						if (timedto.getTimeSlotId().equals(dto.getSlottimeId())) {
							retdto = new SroSlotSheduleDTO();
							retdto.setSlottime(timedto.getTimesSlot());

							retdto.setSlottimeId(timedto.getTimeSlotId());
							retdto.setStatus(dto.getStatus());
							// retdto.setRemarks(dto.getRemarks());
							retdto.setRemarks("");
							retdto.setSno("" + k);
							k = k + 1;;

							retlist.add(retdto);
						}

					}

				}

			}

		}

		return retlist;
	}

	/**
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public ArrayList userStoreData(SlotBookDTO _dto) throws Exception {
		String date = getDate(_dto.getSlotdate());
		String[] userslotparam = new String[8];
		userslotparam[0] = "";// slot_Ref_ID;//CommonConstant.SLOT_APPLICATION_ID;
		userslotparam[1] = _dto.getAvailSro();// Sr for whom slot is being
		// booked
		logger.debug("user id" + userslotparam[1]);
		userslotparam[2] = _dto.getOfficeID();//
		logger.debug("office id" + userslotparam[2]);
		userslotparam[3] = _dto.getDistId();// CommonConstant.SLOT_CREATED_BY;
		logger.debug("dist id" + userslotparam[3]);
		userslotparam[4] = _dto.getTimeSlotId();
		// added on 16-10-2012
		logger.debug("slot id" + userslotparam[4]);
		userslotparam[5] = _dto.getRegId();// CommonConstant.SLOT_SLOT_SR_REF_ID;
		logger.debug("app id" + userslotparam[5]);
		// userslotparam[6] = _dto.getUserId();
		userslotparam[6] = _dto.getUserId();
		userslotparam[7] = date;

		// start:==== added by ankita
		// when user books a slot same get inserted into sr table
		String[] slotparam = new String[7];
		String[] historyparam = new String[10];

		slotparam[0] = userslotparam[2]; // office_id
		slotparam[1] = _dto.getAvailSro(); // user_id
		slotparam[2] = userslotparam[4]; // slot_id
		slotparam[3] = "A"; // block_status
		slotparam[4] = date; // slot booked date
		slotparam[5] = "";
		slotparam[6] = "";

		historyparam[0] = ""; // txn id
		historyparam[1] = userslotparam[1];// user id
		historyparam[2] = userslotparam[2];// office id
		historyparam[3] = userslotparam[4];
		historyparam[4] = "A";
		historyparam[5] = "";
		// historyparam[6] = userslotparam[1];// user id
		historyparam[6] = userslotparam[6];// created by (session user id)
		historyparam[7] = date;
		historyparam[8] = date;
		historyparam[9] = "";

		// ArrayList arl = dao.userStoreData(userslotparam,_dto);

		// ArrayList arl = dao.userStoreData(userslotparam, _dto, slotparam,
		// historyparam);
		ArrayList arl;

		if (!_dto.getReSchedule().equalsIgnoreCase("reSchedule")) {
			arl = dao.userStoreData(userslotparam, _dto, slotparam, historyparam);
		} else {

			arl = dao.userStoreDataReschedule(userslotparam, _dto, slotparam, historyparam);
			/*
			 * commented by ankita 30-10-2012 boolean ar2 = dao.storeSroData(slotparam,historyparam,dto_,userslotparam);
			 * 
			 * if(ar2) { logger.debug("data inserted in sro table"); }
			 * 
			 * //end :====added by ankita
			 */
		}

		return arl;

	}

	/**
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean storeSroData(SroSlotSheduleDTO _dto, String[] param) throws Exception {

		String timecheck = _dto.getCheckboxvalues();
		logger.debug("timecheck" + timecheck);
		boolean arl = false;

		String datett = getDate(_dto.getSelectDate());

		_dto.setSelectDate(datett);
		StringTokenizer stoken = new StringTokenizer(timecheck, ",");
		int i = 1;
		String[] remarks = new String[20];
		while (stoken.hasMoreTokens()) {
			String[] slotparam = new String[7];
			String[] historyparam = new String[10];
			if ("".equals(timecheck)) {
				continue;
			} else {
				timecheck = stoken.nextToken();

				slotparam[2] = timecheck;
				historyparam[3] = timecheck;

				String remarksCheck = _dto.getCheckRemarks();
				StringTokenizer rstoken = new StringTokenizer(remarksCheck, ",");
				int j = 1;
				while (rstoken.hasMoreTokens()) {
					if ("".equals(timecheck)) {
						slotparam[5] = "";
						continue;
					} else {
						remarksCheck = rstoken.nextToken();

						remarks[j] = remarksCheck;
					}

					j++;

				}
				// TXN_ID,USER_ID,OFFICE_ID,SLOT_ID,STATUS_FROM,STATUS_TO,CREATED_BY,
				// CREATED_DATE,BLOCKED_DATE,REMARKS
				if (slotparam[5] == null)
					slotparam[5] = "";

				historyparam[0] = ""; // txn id
				historyparam[1] = param[0];// user id
				historyparam[2] = param[1];// office id

				historyparam[4] = "A";
				historyparam[5] = "";
				historyparam[6] = param[0];
				historyparam[7] = _dto.getSelectDate();
				historyparam[8] = _dto.getSelectDate();
				historyparam[9] = remarks[i];
				slotparam[0] = param[1];
				slotparam[1] = param[0];

				slotparam[3] = "A";
				slotparam[4] = _dto.getSelectDate();
				slotparam[5] = remarks[i];

				arl = dao.storeSroData(slotparam, historyparam, _dto);

			}

			i++;

		}
		return arl;

	}

	/**
	 * @param _fdate
	 * @return
	 */
	public String getDate(String _fdate) {
		System.out.println("_fdate" + _fdate);
		StringTokenizer stoken = new StringTokenizer(_fdate, "/");
		String dd = stoken.nextToken();
		String mm = stoken.nextToken();
		String yy = stoken.nextToken();
		if (dd.length() == 2) {
			_fdate = dd + "-" + getMonthName(mm) + "-" + yy;
		}

		return _fdate;

	}

	/**
	 * @param _month
	 * @return
	 */
	public String getMonthName(String _month) {
		HashMap hm = new HashMap();
		hm.put("01", "JAN");
		hm.put("02", "FEB");
		hm.put("03", "MAR");
		hm.put("04", "APR");
		hm.put("05", "MAY");
		hm.put("06", "JUN");
		hm.put("07", "JUL");
		hm.put("08", "AUG");
		hm.put("09", "SEP");
		hm.put("10", "OCT");
		hm.put("11", "NOV");
		hm.put("12", "DEC");
		return (String) hm.get(_month);
	}

	/**
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public int updatebookedbysr(SroSlotSheduleDTO _dto, String[] param) throws Exception {
		String[] remarks = new String[20];
		String[] historyparam = new String[10];
		String timecheck = _dto.getCheckboxvalues();
		int rslt = 0;

		String datett = getDate(_dto.getSelectDate());
		_dto.setSelectDate(datett);
		StringTokenizer stoken = new StringTokenizer(timecheck, ",");
		String slotid = "";
		int i = 1;
		while (stoken.hasMoreTokens()) {

			String[] slotparam = new String[10];
			if ("".equals(timecheck)) {
				continue;
			} else {
				timecheck = stoken.nextToken();
				logger.debug("timecheck" + timecheck);
				slotid = timecheck;
				_dto.setSlottimeId(slotid);

				historyparam[3] = timecheck;
				String remarksCheck = _dto.getCheckRemarks();
				StringTokenizer rstoken = new StringTokenizer(remarksCheck, ",");
				int j = 1;
				while (rstoken.hasMoreTokens()) {
					if ("".equals(timecheck)) {
						slotparam[9] = "";
						continue;
					} else {
						remarksCheck = rstoken.nextToken();
						remarks[j] = remarksCheck;
					}
					j++;
				}
				if (slotparam[9] == null)
					slotparam[9] = "";
				slotparam[0] = CommonConstant.SLOT_SRBOOK_DIST_ID;
				slotparam[1] = CommonConstant.SLOT_SRBOOK_SRO_ID;
				slotparam[2] = CommonConstant.SLOT_SRBOOK_SR_ID;
				slotparam[3] = _dto.getSelectDate();
				slotparam[4] = CommonConstant.SLOT_CREATED_BY;
				slotparam[5] = _dto.getSelectDate();
				slotparam[7] = CommonConstant.SLOT_SRBOOK_STATUS;
				slotparam[8] = CommonConstant.SLOT_SLOT_SR_REF_ID;
				slotparam[9] = remarks[i];
				historyparam[0] = "";
				historyparam[1] = param[0];
				historyparam[2] = param[1];

				historyparam[4] = "A";
				historyparam[5] = "D";
				historyparam[6] = param[0];
				historyparam[7] = _dto.getSelectDate();
				historyparam[8] = _dto.getSelectDate();
				historyparam[9] = remarks[i];
				_dto.setRemarks(remarks[i]);
				// DISTRICT_ID,SRO_ID,SR_ID,SLOT_DATE,CREATED_BY,CREATED_DATE,SLOT_ID
				// ,SLOT_STATUS,SLOT_REF_ID,REMARKS
				// slotid,datett,remarksCheck
				rslt = dao.updatebookedbysr(_dto, historyparam);

			}

			// DISTRICT_ID,SRO_ID,SR_ID,SLOT_DATE,CREATED_BY,CREATED_DATE,SLOT_ID
			// ,SLOT_STATUS,SLOT_REF_ID,REMARKS
			i++;
		}

		return rslt;

	}

	/**
	 * @param _createdBy
	 * @return
	 * @throws Exception
	 */
	public ArrayList getUserReport(String _createdBy) throws Exception {
		ArrayList resultList = null;
		ArrayList result = new ArrayList();
		ArrayList tempList = null;
		SlotBookDTO dto = null;
		try {

			resultList = dao.getUserReport(_createdBy);
			int j = 1;
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					tempList = (ArrayList) resultList.get(i);
					if (tempList.size() > 0) {
						dto = new SlotBookDTO();
						dto.setSroId("" + j);
						dto.setSlotRefId((String) tempList.get(0));
						dto.setRegId((String) tempList.get(1));
						dto.setSlotdate((String) tempList.get(2));
						dto.setSelctslottime((String) tempList.get(3));
						dto.setUserId((String) tempList.get(4));
						dto.setSroName((String) tempList.get(5));
						dto.setCreatedBy((String) tempList.get(6));
						dto.setUserStatus("OPEN");
						j++;
					}
					result.add(dto);
				}
			}
		} catch (Exception e) {

		}

		return result;

	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCountry() throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList countlist = common.getCountry();
		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					CommonDropDownDTO cntdto = new CommonDropDownDTO();
					cntdto.setCountryId((String) cnttlist.get(0));
					cntdto.setCountryName((String) cnttlist.get(1));
					cntList.add(cntdto);
				}

			}

		}
		return cntList;

	}

	/**
	 * @param _countryId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getState(String _countryId) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList statelist = common.getState(_countryId);
		ArrayList stList = new ArrayList();
		if (statelist != null) {
			for (int i = 0; i < statelist.size(); i++) {
				ArrayList sttlist = (ArrayList) statelist.get(i);
				if (sttlist != null) {
					CommonDropDownDTO stdto = new CommonDropDownDTO();
					stdto.setStateId((String) sttlist.get(0));
					stdto.setStateName((String) sttlist.get(1));
					stList.add(stdto);
				}

			}

		}
		return stList;

	}

	/**
	 * @param _stateId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrict(String _stateId) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList distlist = common.getDistrict(_stateId);
		ArrayList dstList = new ArrayList();
		if (distlist != null) {
			for (int i = 0; i < distlist.size(); i++) {
				ArrayList disttlist = (ArrayList) distlist.get(i);
				if (disttlist != null) {
					CommonDropDownDTO stdto = new CommonDropDownDTO();
					stdto.setDistId((String) disttlist.get(0));
					stdto.setDistName((String) disttlist.get(1));
					dstList.add(stdto);

				}

			}
		}
		return dstList;

	}

	/**
	 * @param _distId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getMohalla(String _distId) throws Exception {
		IGRSCommon common = new IGRSCommon();
		/*
		 * Start:===Added by ankita on 28-12-2012 for removing String to Int error
		 */
		int dist = Integer.parseInt(_distId);
		// ArrayList mohlist = common.getMahalla(_distId);
		ArrayList mohlist = common.getMahalla(dist);
		// End:====Added by Ankita on 28-12-2012
		ArrayList mhList = new ArrayList();
		if (mohlist != null) {
			for (int i = 0; i < mohlist.size(); i++) {
				ArrayList mhhlist = (ArrayList) mohlist.get(i);
				if (mhhlist != null) {
					CommonDropDownDTO stdto = new CommonDropDownDTO();
					stdto.setMohallaId((String) mhhlist.get(0));
					stdto.setMohallaName((String) mhhlist.get(1));
					mhList.add(stdto);
				}

			}

		}
		return mhList;

	}

	// Start Added by SreeLatha for sroName List
	/**
	 * @param _sroId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSrNameList(String _sroId) throws Exception {
		return dao.getSrNameList(_sroId);
	}

	// End Added by SreeLatha for sroName List

	// Start Added by SreeLatha for slotAmnt
	/***********************************************************
	 * Method Name : getSlotBookFee() Arguments : Function Id, Sub Function Id Return : returnFlag Exception : NullPointer or Exception
	 ***********************************************************/
	public String getSlotBookFee(String _funId, String _userId) throws NullPointerException, Exception {
		String returnFlag = null;
		try {
			returnFlag = dao.getSlotBookFee(_funId, _userId);
			if (returnFlag != null) {
				return returnFlag;
			}
		} catch (NullPointerException ne) {
			logger.debug("Null Pointer Exception at  getSlotBookFee in BD " + ne);
		} catch (Exception e) {
			logger.debug("Exception at  getSlotBookFee in BD " + e);
		}
		return returnFlag;
	}

	// End Added by SreeLatha for slotAmnt

	// Start:==Added By SreeLatha for inserting paymentTxn_in and Slot_Id into
	// IGRS_REG_SLOT_PAYMENT_DTLS
	/**
	 * @param _sroId
	 * @return
	 * @throws Exception
	 */
	public boolean insertPayTxnSLotTxnId(String _payTxnId, String _slotTxnId) throws Exception {
		return dao.insertPayTxnSLotTxnId(_payTxnId, _slotTxnId);
	}

	// End:==Added By SreeLatha for inserting paymentTxn_in and Slot_Id into
	// IGRS_REG_SLOT_PAYMENT_DTLS

	// Start:====Added by SreeLatha for SlotBooking View
	public ArrayList getSlotBkViewDetails(SlotBookDTO _dto, String lang) {
		ArrayList slotBkViewList = new ArrayList();
		ArrayList resultList = new ArrayList();
		SlotBookDTO dto = null;
		String status = "";
		try {
			resultList = dao.getSlotBkViewDetails(_dto);
			int sno = 1;
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					dto = new SlotBookDTO();
					ArrayList list = (ArrayList) resultList.get(i);
					dto.setSno(sno);
					dto.setRefId((String) list.get(0));
					dto.setAppNumber((String) list.get(1));
					dto.setVisitDate((String) list.get(2));
					dto.setVisitTime((String) list.get(3));
					if ("en".equalsIgnoreCase(lang)) {
						dto.setSroName((String) list.get(4));
					} else {
						dto.setSroName((String) list.get(5));
					}
					// dto.setSroName((String) list.get(4));
					dto.setSrName((String) list.get(6));
					if (((String) list.get(7)).equalsIgnoreCase("A")) {
						status = "Open";
					} else if (((String) list.get(7)).equalsIgnoreCase("D")) {
						status = "Closed/Cancelled";
					} else if (((String) list.get(7)).equalsIgnoreCase("")) {
						status = "Open";
					} else {
						status = null;
					}
					dto.setStatus(status);
					sno++;
					slotBkViewList.add(dto);
				}
			}
		} catch (Exception e) {
			logger.debug("Exception at getSlotBkViewDetails in BD " + e);
		}
		return slotBkViewList;
	}

	// End:====Added by SreeLatha for SlotBooking View

	// Start:====Added by SreeLatha for SlotBooking View Get Data for
	// particularId
	public ArrayList getRefIdDetails(String refId) {
		ArrayList slotBkViewList = new ArrayList();
		ArrayList resultList = new ArrayList();
		SlotBookDTO dto = null;
		String status = "";
		try {
			resultList = dao.getRefIdDetails(refId);
			int sno = 1;
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					dto = new SlotBookDTO();
					ArrayList list = (ArrayList) resultList.get(i);
					dto.setSno(sno);
					dto.setRefId((String) list.get(0));
					dto.setAppNumber((String) list.get(1));
					dto.setVisitDate((String) list.get(2));
					dto.setVisitTime((String) list.get(3));
					dto.setSroName((String) list.get(4));
					dto.setSrName((String) list.get(5));
					if (((String) list.get(6)).equalsIgnoreCase("A")) {
						status = "Open";
					} else if (((String) list.get(6)).equalsIgnoreCase("D")) {
						status = "Closed";
					} else {
						status = null;
					}
					dto.setStatus(status);
					sno++;
					slotBkViewList.add(dto);
				}
			}
		} catch (Exception e) {
			logger.debug("Exception at getRefIdDetails in BD " + e);
		}
		return slotBkViewList;

	}

	/**
	 * 
	 * @param userId
	 * @return Office id
	 */
	public String getOffId(String userId) {
		ArrayList offList = new ArrayList();
		String officeId = null;
		offList = dao.getOffId(userId);
		officeId = (String) offList.get(0);

		return officeId;

	}

	public String instrumentId(String initiationId) {

		String slotFlag = null;
		try {
			slotFlag = dao.getInstrumentId(initiationId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return slotFlag;

	}

	public int checkweekend(SroSlotSheduleDTO srodto) throws Exception {
		int i = dao.checkweekend(srodto);
		return i;
	}

	public String getslottime(String slotId) throws Exception {
		String slottime = dao.getslotTime(slotId);
		return slottime;
	}

	public int chkRegTable(SlotBookDTO slotdto) throws Exception {
		// TODO Auto-generated method stub
		return dao.chkRegTable(slotdto);
	}

	public ArrayList getDistrictList(HashMap officeDetails, String actId, String lang) {

		String roleID = "";
		ArrayList officeList = new ArrayList();
		ArrayList activityList = new ArrayList();
		Set mapSet1 = (Set) officeDetails.entrySet();
		Iterator mapIterator1 = mapSet1.iterator();
		logger.debug("IN DISTRICT DETAILS METHOD------>");
		logger.debug("Activity ID" + actId);
		while (mapIterator1.hasNext()) {
			Map.Entry mapEntry1 = (Map.Entry) mapIterator1.next();
			OfficeDTO ofcdto = (OfficeDTO) mapEntry1.getValue();
			roleID = (String) mapEntry1.getKey();
			activityList = (ArrayList) ofcdto.getActivityList();
			activityList.trimToSize();
			for (int i = 0; i < activityList.size(); i++) {
				try {
					ArrayList list1 = (ArrayList) activityList.get(i);
					logger.debug("Activity list---" + (String) list1.get(0));
					if (((String) list1.get(0)).equalsIgnoreCase(actId)) {
						officeList.addAll(ofcdto.getOfficeList());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		}
		logger.debug("size of officeList" + officeList.size());
		officeList.trimToSize();

		ArrayList districtList = dao.getDistrictIdList(officeList, lang);

		return districtList;
	}

	public ArrayList getOfficeName(HashMap officeDetails, String actId, String lang) throws Exception {
		String roleID = "";
		ArrayList officeList = new ArrayList();
		ArrayList activityList = new ArrayList();
		Set mapSet1 = (Set) officeDetails.entrySet();
		Iterator mapIterator1 = mapSet1.iterator();
		logger.debug("IN DISTRICT DETAILS METHOD------>");
		logger.debug("Activity ID" + actId);
		while (mapIterator1.hasNext()) {
			Map.Entry mapEntry1 = (Map.Entry) mapIterator1.next();
			OfficeDTO ofcdto = (OfficeDTO) mapEntry1.getValue();
			roleID = (String) mapEntry1.getKey();
			activityList = (ArrayList) ofcdto.getActivityList();
			activityList.trimToSize();
			for (int i = 0; i < activityList.size(); i++) {
				ArrayList list1 = (ArrayList) activityList.get(i);
				logger.debug("Activity list---" + (String) list1.get(0));
				try {
					if (((String) list1.get(0)).equalsIgnoreCase(actId)) {
						officeList.addAll(ofcdto.getOfficeList());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		logger.debug("size of officeList" + officeList.size());
		officeList.trimToSize();

		return dao.getOfficeName(officeList, lang);
	}

	public ArrayList getDistrictFromEstamp(SlotBookDTO bookdto, String lang) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDistrictFromEstamp(bookdto, lang);
	}

	public ArrayList getTehsilList(SlotBookDTO bookdto, String usrSlctDist, String lang) throws Exception {
		// TODO Auto-generated method stub
		return dao.getTehsil(bookdto, usrSlctDist, lang);
	}

	public ArrayList getofficeMappedToTehsil(String regTxnId, String officeId, String lang) throws Exception {
		// TODO Auto-generated method stub
		return dao.getOfficesMappedToTehsil(regTxnId, officeId, lang);
	}
	public boolean checkBookie(String reg_txn_num, String user_id) {
		return dao.checkBookie(reg_txn_num, user_id);
	}
	public boolean checkUser(String regId, String userId) {
		return dao.checkUser(regId, userId);
	}

	public boolean checkEstamp(String reg_txn_num) {
		return dao.checkEstamp(reg_txn_num);
	}
	// slot booking changes by santosh
	public String fetchSlotParams(String regTxnID) {
		return dao.fetchSlotParams(regTxnID);
	}

	public ArrayList<String> getSlotDetail(String regTxnId) throws Exception {
		return dao.getSlotDetail(regTxnId);
	}

	public String getOfficeType(String officeID) throws Exception {
		return dao.getOfficeType(officeID);
	}

	public boolean chkholidaydat(String currDate) throws Exception {
		boolean chk = dao.checkholiday(currDate);
		return chk;
	}
	public void setSlotDetail(SlotBookingUrgentForm sForm, ArrayList slotDetails, String officeType, String language) throws Exception {
		String pattern = "dd/MM/yyyy";
		Date date = new Date();
		String currentDate = new SimpleDateFormat(pattern).format(date);
		boolean chk = true;
		int j = 1;
		while (chk) {
			chk = dao.checkholiday(currentDate);
			if (chk) {
				currentDate = new SimpleDateFormat(pattern).format(date.getTime() + (j * 24 * 60 * 60 * 1000));
				j++;
			}
		}
		sForm.setToBeBookedDate(currentDate);
		sForm.setBookedSlotDate((String) slotDetails.get(2));
		sForm.setBookedSlotTiming((String) slotDetails.get(3));
		if ("en".equalsIgnoreCase(language)) {
			sForm.setBookedSROName((String) slotDetails.get(6));
		} else {
			sForm.setBookedSROName((String) slotDetails.get(11));
		}
		if ("en".equalsIgnoreCase(language)) {
			sForm.setBookedDistrictName((String) slotDetails.get(7));
		} else {
			sForm.setBookedDistrictName((String) slotDetails.get(10));
		}
		sForm.setBookedSROId((String) slotDetails.get(5));
		sForm.setBookedDistrictId((String) slotDetails.get(8));
		sForm.setSlotBookedBy((String) slotDetails.get(4));
		sForm.setApplicationStatus((String) slotDetails.get(9));
		sForm.setIfError("10".equals((String) slotDetails.get(9)) ? "1" : "-3");
		sForm.setUploadStatus("N");
		ArrayList list = dao.getSlotReschedulingReasonList(officeType);
		for (int i = 0; i < list.size(); i++) {
			SlotBookingUrgentDTO dto = new SlotBookingUrgentDTO();
			ArrayList temp = (ArrayList) list.get(i);
			dto.setReasonId((String) temp.get(0));
			if ("en".equalsIgnoreCase(language)) {
				dto.setReasonNameEng((String) temp.get(1));
			} else {
				dto.setReasonNameEng((String) temp.get(2));
			}
			dto.setPaymentRequired((String) temp.get(3));
			dto.setPayableAmount((String) temp.get(4));
			sForm.getReasonList().add(dto);
		}
		ArrayList list1 = dao.getEarlierStatus(sForm.getRegTxnId());
		sForm.setReasonId("");
		sForm.setPaymentRequired("");
		for (int i = 0; i < list1.size(); i++) {
			ArrayList tmp = (ArrayList) list1.get(i);
			sForm.setReasonId((String) tmp.get(2));
			sForm.setFinalUploadFilePath((String) tmp.get(1));
			sForm.setFileName((String) tmp.get(1));
			sForm.setUploadStatus("Y");
			sForm.setPaymentRequired((String) tmp.get(3));
			sForm.setPayableAmount((String) tmp.get(4));
			if ("en".equals(language)) {
				sForm.setReasonDesc((String) tmp.get(5));
			} else {
				sForm.setReasonDesc((String) tmp.get(6));
			}
			sForm.setDateSelected("Y");
			sForm.setToBeBookedDate((String) tmp.get(7));
			sForm.setPaymentFlag((String) tmp.get(8));
			sForm.setChangeDate("N");
			sForm.setSlotBookTxnId((String) tmp.get(0));
		}

		sForm.setPaymentFlag(sForm.getPaymentRequired());
		// return null;
	}
	public String updateSlotTime(SlotBookingUrgentForm sForm, String userId, String officeType, String loggedInOffice) throws Exception {
		String response = "-1";

		response = dao.updateSlotTime(sForm, userId, officeType, loggedInOffice);
		return response;
	}
	public HashMap<String, String> getDistrictDetail(String language, String officeId) throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		ArrayList response = new ArrayList();
		response = dao.getDistrictDetail(officeId);
		System.out.println("response size -- " + response.size());
		for (int i = 0; i < response.size(); i++) {
			ArrayList tmp = (ArrayList) response.get(i);
			if ("en".equals(language)) {
				result.put((String) tmp.get(0), (String) tmp.get(1));
			} else {
				result.put((String) tmp.get(0), (String) tmp.get(2));
			}
		}
		return result;
	}
	public HashMap<String, String> getOfficeDetail(String officeType, String language, String districtId, String loggedInOffice) throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		ArrayList response = new ArrayList();
		response = dao.getOfficeDetail(officeType, districtId, loggedInOffice);
		// response = (ArrayList) response.get(0);
		System.out.println("response size -- " + response.size());
		for (int i = 0; i < response.size(); i++) {
			ArrayList tmp = (ArrayList) response.get(i);
			if ("en".equals(language)) {
				result.put((String) tmp.get(0), (String) tmp.get(1));
			} else {
				result.put((String) tmp.get(0), (String) tmp.get(2));
			}
		}
		return result;
	}
	public String updateOfficeSlotData(SlotBookingUrgentForm sForm) throws Exception {
		String responseCode = "1";
		if (null == sForm.getSelectedOfficeId()) {
			return "ofcError";
		}
		if ("".equals(sForm.getSelectedOfficeId())) {
			return "ofcError";
		}
		ArrayList<String> dataUpdate = new ArrayList<String>();
		dataUpdate.add(sForm.getSelectedDistrictId());
		dataUpdate.add(sForm.getSelectedOfficeId());

		dataUpdate.add(sForm.getTimeInMinute());
		dataUpdate.add(sForm.getSelectedYesNo());
		System.out.println("size of list is --- " + dataUpdate.size());
		if ("ALL".equals(sForm.getSelectedDistrictId())) {
			responseCode = dao.updateOfficeSlotDataAllDist(dataUpdate);
		}
		responseCode = dao.updateOfficeSlotData(dataUpdate);
		return responseCode;
	}

	public String getPaymentStatus(String regTxnID) throws Exception {
		String returnVal = "";
		returnVal = dao.getPaymentStatus(regTxnID);
		return returnVal;
	}

	public String getPaymentDetail(String regTxnID, String payableAmount) throws Exception {
		String retVal = "";
		retVal = dao.getPaymentDetail(regTxnID, payableAmount);
		return retVal;
	}
	public boolean insertSltRefDtl(String refId, String amount, String regTxnId, String filePath, String reasonId, String userId, String slotDate) throws Exception {
		boolean bool = false;
		bool = dao.insertSltRefDtl(refId, amount, regTxnId, filePath, reasonId, userId, slotDate);
		return bool;
	}

	// for report
	public ArrayList getReport(SlotBookingUrgentForm sForm, String language) throws Exception {
		ArrayList report = new ArrayList();
		report = dao.getReport(sForm.getSelectedDistrictId(),sForm.getToDate(),sForm.getFrmDate());
		ArrayList reportData = new ArrayList();
		for (int i = 0; i < report.size(); i++) {
			ArrayList tmp = (ArrayList) report.get(i);
			SlotBookingUrgentDTO sbdto = new SlotBookingUrgentDTO();
			sbdto.setDistrictName((String) tmp.get(0));
			sbdto.setOfficeName((String) tmp.get(1));
			sbdto.setRegTxnId((String) tmp.get(2));
			sbdto.setPaymentReq((tmp.get(3) == null || (("".equals((String) tmp.get(3))))) ? "NA" : (String) tmp.get(3));
			sbdto.setUpdateById((String) tmp.get(4));
			sbdto.setReason((("en".equalsIgnoreCase(language)) ? (String) tmp.get(6) : (String) tmp.get(7)));
			sbdto.setUpdateByName((String) tmp.get(8));
			sbdto.setFilePath((String) tmp.get(5));
			sbdto.setPrevSlotDate((String) tmp.get(9));
			sbdto.setRescheduleSlotDate((String) tmp.get(10));
			reportData.add(sbdto);
		}
		return reportData;
	}
	public String getIfPresent(String regTxnId) throws Exception {
		String response = "1";
		response = dao.getIfPresent(regTxnId);
		return response;
	}
	public String getWillDeedCheck(String regTxnId) throws Exception {
		String response = "0";
		response = dao.getWillDeedCheck(regTxnId);
		return response;
	}
	public String getIfRescheduled(String regTxnId) throws Exception {
		String response = "1";
		response = dao.getIfRescheduled(regTxnId);
		return response;
	}
	public ArrayList getPaymentRecord(String refId, String funcID) throws Exception{
		ArrayList response = new ArrayList();
		response= dao.getPaymentRecord(refId, funcID);
		return response;
		
	}
}
