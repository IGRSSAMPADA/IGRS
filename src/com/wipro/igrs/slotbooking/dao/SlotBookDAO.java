package com.wipro.igrs.slotbooking.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import oracle.jdbc.OracleTypes;

import com.wipro.igrs.ACL.dto.OfficeDTO;
import com.wipro.igrs.areaManagement.sql.areaManagementSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.guideline.dto.GuidelineDTO;

import com.wipro.igrs.newreginit.sql.RegCommonSQL;
import com.wipro.igrs.slotbooking.constant.CommonConstant;
import com.wipro.igrs.slotbooking.dto.SlotBookDTO;
import com.wipro.igrs.slotbooking.dto.SroSlotSheduleDTO;
import com.wipro.igrs.slotbooking.form.SlotBookingUrgentForm;
import com.wipro.igrs.slotbooking.sql.CommonSQL;
import org.apache.log4j.Logger;

/**
 * =========================================================================== File : SlotBookDAO.java Description : Represents the Data Access Object Class
 * 
 * Author : Hari Krishna G V Created Date : Nov 28, 2007
 * 
 * ===========================================================================
 */
public class SlotBookDAO {

	private Logger logger = (Logger) Logger.getLogger(SlotBookDAO.class);
	// DBUtility dbUtil;
	PreparedStatement pstmt = null;

	/**
	 * 
	 */

	public SlotBookDAO() {
		System.out.println("in dao");
		logger.info("We are in SlotBookDAO..");

	}

	/**
	 * @return
	 */

	public ArrayList getDistrictName() throws Exception {
		ArrayList arl = new ArrayList();
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		try {
			ArrayList dd = new ArrayList();
			ArrayList ddrl = new ArrayList();

			String str = CommonSQL.SLOTBOOK_DISTRICT_DROPDOWN_SELECT;
			dbUtil.createStatement();
			dd = dbUtil.executeQuery(str);
			if (dd.size() != 0) {
				for (int i = 0; i < dd.size(); i++) {
					ddrl = new ArrayList();
					ddrl = (ArrayList) dd.get(i);
					if (ddrl.size() > 0) {
						SlotBookDTO book = new SlotBookDTO();
						book.setDistId((String) ddrl.get(0));
						book.setDistName((String) ddrl.get(1));
						arl.add(book);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return arl;

	}

	/**
	 * @return
	 */
	public ArrayList getSroName(String _distid, String lang) throws Exception {
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		ArrayList arl = new ArrayList();

		try {
			ArrayList dd = new ArrayList();
			ArrayList ddrl = new ArrayList();
			String str = CommonSQL.SLOTBOOK_SRONAME_DROPDOWN_SELECT + "'" + _distid + "'";
			System.out.println(str);
			dbUtil.createStatement();
			dd = dbUtil.executeQuery(str);
			if (dd.size() != 0) {
				for (int i = 0; i < dd.size(); i++) {
					ddrl = new ArrayList();
					ddrl = (ArrayList) dd.get(i);
					if (ddrl.size() > 0) {
						SlotBookDTO book = new SlotBookDTO();
						book.setSroId((String) ddrl.get(0));
						if ("en".equalsIgnoreCase(lang)) {
							book.setSroName((String) ddrl.get(1));
						} else {
							book.setSroName((String) ddrl.get(2));
						}
						arl.add(book);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return arl;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String checkRegistrationId(String _refRegId) throws Exception {
		String refRegTransId = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String str = "SELECT TRANSACTION_ID  FROM IGRS_REG_COMPLETION_DETAILS WHERE REGISTRATION_ID='" + _refRegId + "'";
			if (dbUtil == null)
				dbUtil = new DBUtility();
			dbUtil.createStatement();
			refRegTransId = dbUtil.executeQry(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return refRegTransId;
	}

	/**
	 * @return
	 */
	public ArrayList getSroAvailableSlots(String _sroid) throws Exception {
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		ArrayList arl = new ArrayList();

		try {
			ArrayList dd = new ArrayList();
			ArrayList ddrl = new ArrayList();
			String str = CommonSQL.SLOTBOOK_USER_TIMESLOT_SELECT + _sroid + "'" + " ORDER BY to_date(to_char(time_from,'hh:mi am'),'hh:mi am')";
			System.out.println(str);
			dbUtil.createStatement();
			dd = dbUtil.executeQuery(str);

			if (dd.size() != 0) {
				for (int i = 0; i < dd.size(); i++) {
					ddrl = new ArrayList();
					ddrl = (ArrayList) dd.get(i);
					if (ddrl.size() > 0) {
						SlotBookDTO book = new SlotBookDTO();
						book.setTimeSlotId((String) ddrl.get(0));
						book.setTimesSlot((String) ddrl.get(1));
						arl.add(book);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return arl;

	}

	public ArrayList getSroAvailableSlots1(String _sroid, String selectDate) throws Exception {
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		ArrayList arl = new ArrayList();

		try {
			ArrayList dd = new ArrayList();
			ArrayList ddrl = new ArrayList();
			Date date = new Date();
			SimpleDateFormat yearformat = new SimpleDateFormat("dd/MM/yyyy");
			String yfmt = yearformat.format(date);
			String abc = selectDate;
			System.out.println(yfmt);

			Date date1 = yearformat.parse(yfmt);
			Date date2 = yearformat.parse(abc);
			String str = "";
			if (date1.compareTo(date2) == -1) {
				str = CommonSQL.SLOTBOOK_USER_TIMESLOT_SELECT + _sroid + "' ORDER BY to_date(to_char(time_from,'hh:mi am'),'hh:mi am')";
			} else {
				str = CommonSQL.SLOTBOOK_USER_TIMESLOT_SELECT + _sroid + "' AND   to_timestamp(TO_CHAR(TIME_FROM,'HH12:MI am'), 'HH:MI AM')>to_timestamp(TO_CHAR(sysdate,'HH12:MI am'), 'HH:MI AM') ORDER BY to_date(to_char(time_from,'hh:mi am'),'hh:mi am')";

			}
			System.out.println(str);
			dbUtil.createStatement();
			dd = dbUtil.executeQuery(str);

			if (dd.size() != 0) {
				for (int i = 0; i < dd.size(); i++) {
					ddrl = new ArrayList();
					ddrl = (ArrayList) dd.get(i);
					if (ddrl.size() > 0) {
						SlotBookDTO book = new SlotBookDTO();
						book.setTimeSlotId((String) ddrl.get(0));
						book.setTimesSlot((String) ddrl.get(1));
						arl.add(book);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return arl;

	}

	/**
	 * @return
	 */
	/*
	 * public ArrayList getAvailableSrs( ArrayList _slottimelist, SlotBookDTO _slotdto) throws Exception { dbUtil = new DBUtility(); String usrid="100"; ArrayList arl = new ArrayList(); try { ArrayList dd = new ArrayList(); ArrayList ddsrll = new ArrayList(); ArrayList ddrl = new ArrayList(); String str = CommonSQL.SLOTBOOK_SRNAME_DROPDOWN_SELECT +_slotdto.getSroId()+ "'"+")"; dbUtil.createStatement(); dd = dbUtil.executeQuery(str); if (dd.size() != 0) { for (int i = 0; i < dd.size(); i++) { logger.debug(dd.get(i)); ddrl = new ArrayList(); ddrl = (ArrayList) dd.get(i); if (ddrl.size() > 0) { SlotBookDTO book;// = new SlotBookDTO(); ArrayList ddtime = new ArrayList(); ArrayList ddrltime = new ArrayList(); //commented by ankita 25-10-2012 for user slot booking //String strtime = CommonSQL.SLOTBOOK_USER_TIMESLOT_SELECT+_slotdto.getSroId()+"'"; String strtime1 = CommonSQL.SLOTBOOK_USR_TIMESLOT_SELECT; //Start:===== added by ankita String strtime = strtime1 +"'" +_slotdto.getSlotdate()+"'"+")"+ "AND OFFICE_ID='"+ _slotdto.getSroId()+"'"; //End:===== added by ankita
	 * 
	 * ddtime = dbUtil.executeQuery(strtime); if (ddtime.size() != 0) { for (int k = 0; k < ddtime.size(); k++) { ddrltime = new ArrayList(); ddrltime = (ArrayList) ddtime.get(k); if (ddrltime.size() > 0) { book = new SlotBookDTO(); book .setTimeSlotId((String) ddrltime .get(0)); logger.debug("timeslot id being set--->"+(String) ddrltime .get(0)); book.setTimesSlot((String) ddrltime.get(1)); logger.debug("timeslot being set---->"+(String) ddrltime.get(1)); String timesql = CommonSQL.SLOTBOOK_SRO_BOOKED_SEARCH; timesql = timesql + "'" + _slotdto.getDistId() + "' AND USER_ID='"+ddrl.get(1)+"' AND OFFICE_ID='" + _slotdto.getSroId() + "' AND SLOT_ID='" +(String) ddrltime.get(0)+ "'AND SLOT_DATE='"+_slotdto.getSlotdate()+"'";// and
	 * 
	 * /*start:========added by ankita 25-10-2012 for user slot book view String timesql1 = CommonSQL.SLOTBOOK_SRO_BOOKED_SEARCH; timesql1 = timesql1 + "'" + _slotdto.getDistId() + "' AND USER_ID='"+ddrl.get(1)+"' AND OFFICE_ID='" + _slotdto.getSroId() + "' AND SLOT_ID='" +(String) ddrltime.get(0)+ "'AND SLOT_DATE='"+_slotdto.getSlotdate()+"'";// and
	 * 
	 * String timesql = CommonSQL.SLOTBOOK_USR_TIMESLOT_SELECT; timesql = timesql +"'" +_slotdto.getSlotdate()+"'"+")"+ "AND OFFICE_ID='"+ _slotdto.getSroId()+"'"; //end:========added by ankita 25-10-2012 for user slot book view
	 */

	/*
	 * ArrayList ddsr = new ArrayList(); ddsr = dbUtil.executeQuery(timesql); ArrayList timedisp = new ArrayList(); if (ddsr.size() > 0) { for (int m = 0; m < ddsr.size(); m++) { timedisp = new ArrayList(); timedisp = (ArrayList) ddsr.get(m); if (timedisp.size() > 0) {
	 * 
	 * if ((((String) timedisp.get(0)) .trim()) .equals(((String) ddrl .get(1)).trim()) && (((String) timedisp .get(1)).trim()) .equals(((String) ddrltime .get(0)) .trim())) { logger.debug("timedisp.get(0)"+(String) timedisp.get(0)); logger.debug("(String) ddrl.get(1)).trim()"+(String) ddrl.get(1)); logger.debug("timedisp.get(1)"+(String) timedisp.get(1)); logger.debug("(String) ddrl.get(0)).trim()"+(String) ddrl.get(0)); book.setAvailSroId(""); book.setAvailSro("");
	 * 
	 * }
	 * 
	 * 
	 * else { book .setAvailSroId((String) ddrl .get(1)); book.setAvailSro((String) ddrl .get(0));
	 * 
	 * } } }
	 * 
	 * } else {
	 * 
	 * book .setAvailSroId((String) ddrl .get(1)); logger.debug("book.setAvailSroId"+((String) ddrl.get(1))); book.setAvailSro((String) ddrl.get(0)); logger.debug("book.setAvailSro"+((String) ddrl.get(0))); }
	 * 
	 * arl.add(book); } }
	 * 
	 * }
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * } finally { if(dbUtil!=null){ dbUtil.closeConnection(); } dbUtil=null; } return arl;
	 * 
	 * }*
	 */

	public ArrayList getAvailableSrs(ArrayList _slottimelist, SlotBookDTO _slotdto) throws Exception {
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		String usrid = "100";
		ArrayList arl = new ArrayList();
		try {
			ArrayList dd = new ArrayList();
			ArrayList ddsrll = new ArrayList();
			ArrayList ddrl = new ArrayList();
			String str = CommonSQL.SLOTBOOK_SRNAME_DROPDOWN_SELECT + _slotdto.getOfficeID() + "'" + ")";

			str = "SELECT FIRST_NAME  ||'  '  ||LAST_NAME NAME,  EMP_ID FROM IGRS_EMP_MASTER WHERE EMP_ID IN " + "  (SELECT user_id  FROM IGRS_USER_ROLE_GROUP_MAPPING  WHERE role_group_id IN " + "  (SELECT role_group_id    FROM IGRS_RGROUP_ROLE_MAPPING    WHERE STATUS='A' and role_office_map_id IN " + "      (SELECT role_office_map_id      FROM IGRS_ROLE_OFFICE_MAPPING      WHERE office_id='" + _slotdto.getOfficeID() + "' " + "      AND role_id   IN        (SELECT role_id        FROM IGRS_ROLE_FN_ACTIVITY_MAPPING " + "        WHERE activity_id='ACT_233'        )      )    ) " + "    ) and emp_id not in(select  EMP_ID FROM IGRS_EMP_MASTER " + " WHERE EMP_ID IN  (SELECT user_id  FROM IGRS_USER_ROLE_GROUP_MAPPING   WHERE role_group_id IN " + "    (SELECT role_group_id    FROM IGRS_RGROUP_ROLE_MAPPING    WHERE STATUS='A' and role_office_map_id IN      (SELECT role_office_map_id " + "      FROM IGRS_ROLE_OFFICE_MAPPING      WHERE office_id='" + _slotdto.getOfficeID() + "'      AND role_id   IN " + "        (SELECT role_id        FROM IGRS_ROLE_FN_ACTIVITY_MAPPING        WHERE activity_id='ACT_232' " + "        )      )    )  ))";

			System.out.println(str);
			dbUtil.createStatement();
			dd = dbUtil.executeQuery(str);
			// Start:===Added on 4/12/2012
			// if there's only one Maker cum Checker
			if (dd.size() == 0) {
				str = "select FIRST_NAME||'  '||LAST_NAME NAME,EMP_ID from IGRS_EMP_MASTER where EMP_ID in (select user_id from IGRS_USER_ROLE_GROUP_MAPPING where role_group_id in (select role_group_id from IGRS_RGROUP_ROLE_MAPPING where  STATUS            ='A' AND role_office_map_id in " + "(select role_office_map_id from IGRS_ROLE_OFFICE_MAPPING where office_id='" + _slotdto.getOfficeID() + "' and role_id in (select role_id from IGRS_ROLE_FN_ACTIVITY_MAPPING where activity_id='ACT_233'))))";// query
				// modified
				// for
				// slot
				// booking
				// by
				// saurav

				System.out.println(str);
				dbUtil.createStatement();
				dd = dbUtil.executeQuery(str);
			}
			if (dd.size() != 0) {
				for (int k = 0; k < dd.size(); k++)
					System.out.println("name--->" + dd.get(k));
			}
			// End:===Added on 4/12/2012
			// End:===Added on 4/12/2012
			// Added by Anuj on 3rd July 2014
			Date date = new Date();
			SimpleDateFormat yearformat = new SimpleDateFormat("dd/MM/yyyy");
			String yfmt = yearformat.format(date);
			String abc = _slotdto.getSelectedDate();
			System.out.println(yfmt);

			Date date1 = yearformat.parse(yfmt);
			Date date2 = yearformat.parse(abc);

			// end
			if (dd.size() != 0) {
				for (int i = 0; i < dd.size(); i++) {
					ddrl = new ArrayList();
					ddrl = (ArrayList) dd.get(i);
					System.out.println("--->" + dd.get(i));
					if (ddrl.size() > 0) {
						SlotBookDTO book;// = new SlotBookDTO();
						ArrayList ddtime = new ArrayList();
						ArrayList ddrltime = new ArrayList();
						String strtime = "";
						if (date1.compareTo(date2) == -1) {
							strtime = CommonSQL.SLOTBOOK_USER_TIMESLOT_SELECT + _slotdto.getOfficeID() + "' ORDER BY to_date(to_char(time_from,'hh:mi am'),'hh:mi am')";
						} else if (date1.compareTo(date2) == 0) {
							strtime = "SELECT" + "  SLOT_ID," + "  TO_CHAR(TIME_FROM,'HH12:MI')" + "  ||' - '" + "  ||TO_CHAR(TIME_TO,'HH12:MI') " + "FROM " + "IGRS_REG_SLOT_MASTER " + " WHERE " + "  SLOT_STATUS='A' " + "AND OFFICE_ID='" + _slotdto.getOfficeID() + "' " + "AND to_timestamp(TO_CHAR(TIME_FROM,'HH12:MI am'), 'HH:MI AM')>" + "  to_timestamp(TO_CHAR(sysdate,'HH12:MI am'), 'HH:MI AM')" + "ORDER BY to_date(to_char(time_from,'hh:mi am'),'hh:mi am')";

						}
						/*
						 * strtime = CommonSQL.SLOTBOOK_USER_TIMESLOT_SELECT + _slotdto.getOfficeID() + "'";
						 */

						ddtime = dbUtil.executeQuery(strtime);
						if (ddtime.size() != 0) {
							for (int k = 0; k < ddtime.size(); k++) {
								// boolean emptySroFlag = false;
								ddrltime = new ArrayList();
								ddrltime = (ArrayList) ddtime.get(k);
								if (ddrltime.size() > 0) {
									book = new SlotBookDTO();
									book.setTimeSlotId((String) ddrltime.get(0));
									logger.debug("(String) ddrltime.get(0)) slotid " + (String) ddrltime.get(0));
									book.setTimesSlot((String) ddrltime.get(1));
									logger.debug("(String) ddrltime.get(1))slottime " + (String) ddrltime.get(1));
									String timesql = CommonSQL.SLOTBOOK_USER_BOOKED_SEARCH;
									String srtimesql = CommonSQL.SLOTBOOK_SRO_BOOKED_SEARCH;
									timesql = timesql + "'" + _slotdto.getDistId() + "' AND USER_ID='" + ddrl.get(1) + "' AND OFFICE_ID='" + _slotdto.getOfficeID() + "' AND SLOT_ID='" + (String) ddrltime.get(0) + "' AND SLOT_STATUS IS NULL" + " AND SLOT_DATE='" + _slotdto.getSlotdate() + "'";// and
									ArrayList ddsr = new ArrayList();
									System.out.println("query" + timesql);
									ddsr = dbUtil.executeQuery(timesql);
									// Start:======= Added by Ankita
									srtimesql = srtimesql + ddrl.get(1) + "' AND OFFICE_ID='" + _slotdto.getOfficeID() + "' AND SLOT_ID='" + (String) ddrltime.get(0) + "' AND BLOCK_STATUS IN ('A','D')" + " AND BLOCKED_DATE='" + _slotdto.getSlotdate() + "'";// and
									ArrayList ddsro = new ArrayList();
									System.out.println("query" + srtimesql);
									ddsro = dbUtil.executeQuery(srtimesql);
									// End:=====Added by Ankita
									ArrayList timedisp = new ArrayList();
									// START | 18th Nov 16 | Santosh | changes
									// the look up sequence in
									// slot_block_details and slot_Txn_details
									// to make the rescheduled slot unavailable
									if (ddsro.size() > 0) {
										ArrayList srtimedisp = new ArrayList();
										for (int n = 0; n < ddsro.size(); n++) {
											srtimedisp = new ArrayList();
											srtimedisp = (ArrayList) ddsro.get(n);
											if (srtimedisp.size() > 0) {
												System.out.println("" + srtimedisp.get(0));
												System.out.println("" + srtimedisp.get(1));
												if ((((String) srtimedisp.get(0)).trim()).equals(((String) ddrl.get(1)).trim()) && (((String) srtimedisp.get(1)).trim()).equals(((String) ddrltime.get(0)).trim())) {

													// emptySroFlag = true;
													book.setAvailSroId("");
													book.setAvailSro("");

												}

											}
										}

									} else if (ddsr.size() > 0) {
										for (int m = 0; m < ddsr.size(); m++) {
											timedisp = new ArrayList();
											timedisp = (ArrayList) ddsr.get(m);
											if (timedisp.size() > 0) {
												logger.debug("inside if timedisp.size()>0");
												logger.debug("(String) timedisp.get(0)" + (String) timedisp.get(0));
												logger.debug("(String) ddrltime.get(1))userid " + (String) ddrltime.get(1));
												logger.debug("slot status " + (String) timedisp.get(2));

												if ((((String) timedisp.get(0)).trim()).equals(((String) ddrl.get(1)).trim()) && (((String) timedisp.get(1)).trim()).equals(((String) ddrltime.get(0)).trim())) {
													// emptySroFlag = true;
													book.setAvailSroId("");
													book.setAvailSro("");

												} else {
													book.setAvailSroId((String) ddrl.get(1));
													book.setAvailSro((String) ddrl.get(0));

												}
											}
										}

									}
									// END | 18th Nov 16 | Santosh | changes the
									// look up sequence in slot_block_details
									// and slot_Txn_details
									// to make the rescheduled slot unavailable
									else {
										book.setAvailSroId((String) ddrl.get(1));
										book.setAvailSro("SLOT" + (i + 1));
									}
									// if(emptySroFlag==false){
									arl.add(book);
									// }
								}
							}

						}

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return arl;

	}

	/**
	 * @return public static ArrayList getBookSlotDetails() { logger.debug("in db conn get available sro's "); ArrayList arl = new ArrayList(); SlotBookDTO bookdto = new SlotBookDTO(); bookdto.setAvailSro("SR 1"); arl.add(bookdto); SlotBookDTO bookdto1 = new SlotBookDTO(); bookdto1.setAvailSro("SR 2"); arl.add(bookdto1); SlotBookDTO bookdto2 = new SlotBookDTO(); bookdto2.setAvailSro("SR 3"); arl.add(bookdto2); SlotBookDTO bookdto3 = new SlotBookDTO(); bookdto3.setAvailSro("SR 4"); arl.add(bookdto3); logger.debug("in db conn get available sro's " + arl); return arl; }
	 * 
	 *         /**
	 * @return
	 */
	public ArrayList getSroSlotSheduleDetails(SroSlotSheduleDTO _srdto, String lang) throws Exception {

		ArrayList arl = new ArrayList();
		DBUtility dbUtil = null;
		try {
			ArrayList dd = new ArrayList();
			ArrayList ddrl = null;

			ArrayList srtimeslot = getSroAvailableSlots(_srdto.getSno());
			dbUtil = new DBUtility();
			if (srtimeslot.size() > 0) {
				for (int j = 0; j < srtimeslot.size(); j++) {
					SroSlotSheduleDTO srdto = new SroSlotSheduleDTO();
					srdto.setSno("" + (j + 1));
					SlotBookDTO dto = new SlotBookDTO();
					if (!dto.equals(null)) {
						dto = (SlotBookDTO) srtimeslot.get(j);
						String strtime = "SELECT SLOT_ID, SLOT_STATUS, REG_TXN_ID FROM IGRS_REG_SLOT_BOOK_TXN_DTLS " + "WHERE SLOT_ID='" + dto.getTimeSlotId() + "'  AND  OFFICE_ID='" + _srdto.getSno() + "' AND USER_ID='" + _srdto.getCreatedBy() + "' AND SLOT_STATUS is null AND SLOT_DATE='" + _srdto.getSelectDate() + "'";
						// START | slot book changes for view page by santosh
						// santosh
						String slotBlkDetls = "SELECT SLOT_ID,REG_TXN_ID FROM IGRS_REG_SLOT_BLOCK_DETAILS " + " WHERE SLOT_ID='" + dto.getTimeSlotId() + "'  AND  OFFICE_ID='" + _srdto.getSno() + "' AND USER_ID='" + _srdto.getCreatedBy() + "' AND BLOCK_STATUS = 'D' AND BLOCKED_DATE='" + _srdto.getSelectDate() + "'";
						String slotBlkDetls1 = "SELECT SLOT_ID,REG_TXN_ID FROM IGRS_REG_SLOT_BLOCK_DETAILS " + " WHERE SLOT_ID='" + dto.getTimeSlotId() + "'  AND  OFFICE_ID='" + _srdto.getSno() + "' AND USER_ID='" + _srdto.getCreatedBy() + "' AND BLOCK_STATUS = 'A' AND BLOCKED_DATE='" + _srdto.getSelectDate() + "'";

						logger.debug(" sql -->" + strtime);
						dbUtil.createStatement();
						ArrayList srbooked = dbUtil.executeQuery(strtime);
						ArrayList sltBlockDetlsList = dbUtil.executeQuery(slotBlkDetls);
						ArrayList sltBlockDetlsList1 = dbUtil.executeQuery(slotBlkDetls1);

						if (sltBlockDetlsList1 != null && sltBlockDetlsList1.size() > 0) {
							for (int n = 0; n < sltBlockDetlsList1.size(); n++) {
								ArrayList timedisp = new ArrayList();
								timedisp = (ArrayList) sltBlockDetlsList1.get(n);
								if (timedisp.get(0).equals(dto.getTimeSlotId())) {

									srdto.setSlottime(dto.getTimesSlot());
									srdto.setSlottimeId(dto.getTimeSlotId());
									srdto.setRemarks((String) timedisp.get(1));
									if ("en".equalsIgnoreCase(lang)) {
										srdto.setStatus(CommonConstant.SLOT_STATUS_BOOKED);
									} else {
										srdto.setStatus(CommonConstant.SLOT_STATUS_BOOKED_H);
									}

									arl.add(srdto);
								}

							}
						} else if (srbooked != null && srbooked.size() > 0) {
							for (int m = 0; m < srbooked.size(); m++) {
								ArrayList timedisp = new ArrayList();
								timedisp = (ArrayList) srbooked.get(m);
								if (timedisp.get(0).equals(dto.getTimeSlotId())) {

									srdto.setSlottime(dto.getTimesSlot());
									srdto.setSlottimeId(dto.getTimeSlotId());
									srdto.setRemarks((String) timedisp.get(2));
									if ("en".equalsIgnoreCase(lang)) {
										srdto.setStatus(CommonConstant.SLOT_STATUS_BOOKED);
									} else {
										srdto.setStatus(CommonConstant.SLOT_STATUS_BOOKED_H);
									}

									arl.add(srdto);
								}

							}
						} else {
							srdto.setSlottime(dto.getTimesSlot());
							srdto.setSlottimeId(dto.getTimeSlotId());
							if ("en".equalsIgnoreCase(lang)) {
								srdto.setStatus(CommonConstant.SLOT_STATUS);
							} else {
								srdto.setStatus(CommonConstant.SLOT_STATUS_H);
							}
							srdto.setRemarks("");
							arl.add(srdto);

						}
						if (sltBlockDetlsList != null && sltBlockDetlsList.size() > 0) {
							for (int n = 0; n < sltBlockDetlsList.size(); n++) {
								ArrayList timedisp = new ArrayList();
								timedisp = (ArrayList) sltBlockDetlsList.get(n);
								if (timedisp.get(0).equals(dto.getTimeSlotId())) {

									srdto.setSlottime(dto.getTimesSlot());
									srdto.setSlottimeId(dto.getTimeSlotId());
									srdto.setRemarks((String) timedisp.get(1));
									if ("en".equalsIgnoreCase(lang)) {
										srdto.setStatus(CommonConstant.SLOT_STATUS_RESCHEDULED);
									} else {
										srdto.setStatus(CommonConstant.SLOT_STATUS_RESCHEDULED_H);
									}
									arl.add(srdto);
								}

							}
						}
					}
				}

			}
			// END | slot book changes for view page by santosh
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return arl;

	}

	public ArrayList getSroSlotSheduleDetails1(SroSlotSheduleDTO _srdto) throws Exception {
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		ArrayList slotBkViewList1 = new ArrayList();
		ArrayList list1 = new ArrayList();
		SroSlotSheduleDTO srdto = null;
		try {

			String sqlQry = "SELECT b.SLOT_ID,b.BLOCK_STATUS,b.REMARKS,b.USER_ID,to_char(b.BLOCKED_DATE,'DD-MON-YYYY')" + ",(SELECT distinct TO_CHAR(a.TIME_FROM,'HH:MI AM') FROM IGRS_REG_SLOT_MASTER a WHERE a.SLOT_ID=b.SLOT_ID) SLOT_TIME" + " FROM IGRS_REG_SLOT_BLOCK_DETAILS b " + "WHERE b.OFFICE_ID='" + _srdto.getSno() + "' AND b.USER_ID='" + _srdto.getUserId() + "'AND b.BLOCKED_DATE BETWEEN TO_DATE('" + _srdto.getSelectDate() + "'" + ",'DD/MM/YYYY')  AND TO_DATE('" + _srdto.getDurationTo() + "','DD/MM/YYYY') order by BLOCKED_DATE,to_date(to_char(time_from,'hh:mi am'),'hh:mi am')";
			dbUtil.createStatement();
			System.out.println("qry" + sqlQry);
			ArrayList srbooked = dbUtil.executeQuery(sqlQry);

			if (srbooked.size() != 0) {
				for (int i = 0; i <= srbooked.size(); i++) {
					list1 = (ArrayList) srbooked.get(i);
					srdto = new SroSlotSheduleDTO();
					srdto.setUserId((String) list1.get(3));
					srdto.setSlottimeId((String) list1.get(5));
					srdto.setStatus((String) list1.get(1));

					if (((String) list1.get(1)).equals("A")) {

						srdto.setStatus("BOOKED");
						srdto.setRemarks((String) list1.get(2));
					}
					if (((String) list1.get(1)).equals("D")) {
						// srdto.setStatus("OPEN");
						// Start:=== added for report viewing by SR on 5/12/2012
						srdto.setStatus("BOOKING CANCELLED");
						// End:=====added for report viewing by SR on 5/12/2012
						srdto.setRemarks("");

					}

					// srdto.setSelectDate((String)list1.get(4));
					srdto.setSlotDate((String) list1.get(4));
					slotBkViewList1.add(srdto);
					logger.debug("" + slotBkViewList1.size());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("thisis Exception in getSlotBkViewDetails() DAO: " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
				logger.debug("this is Fianlly Try catch in getSlotBkViewDetails DAO " + e);
			}
		}
		return slotBkViewList1;
	}

	/**
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSrobookedSlotDetails(SroSlotSheduleDTO _dto, String param[]) throws Exception {

		ArrayList arl = new ArrayList();
		DBUtility dbUtil = null;
		try {
			ArrayList dd = new ArrayList();
			ArrayList srtimeslot = getSroAvailableSlots(_dto.getSno());
			dbUtil = new DBUtility();
			if (srtimeslot.size() > 0) {
				for (int j = 0; j < srtimeslot.size(); j++) {
					int k = 0;
					SroSlotSheduleDTO srdto = new SroSlotSheduleDTO();

					SlotBookDTO dto = new SlotBookDTO();

					if (!dto.equals(null)) {
						dto = (SlotBookDTO) srtimeslot.get(j);

						String strtime = "SELECT SLOT_ID,BLOCK_STATUS,REMARKS FROM IGRS_REG_SLOT_BLOCK_DETAILS WHERE SLOT_ID='" + dto.getTimeSlotId() + "'  AND  OFFICE_ID='" + param[1] + "' AND USER_ID='" + param[0] + "' AND BLOCK_STATUS='A' AND BLOCKED_DATE=TO_DATE('" + _dto.getSelectScheduleDate() + "','DD/MM/YYYY')";

						logger.debug("in dao try for sro" + strtime);
						dbUtil.createStatement();
						ArrayList srbooked = dbUtil.executeQuery(strtime);
						if (srbooked != null) {
							if (srbooked.size() > 0) {
								for (int m = 0; m < srbooked.size(); m++) {
									ArrayList timedisp = new ArrayList();
									timedisp = (ArrayList) srbooked.get(m);
									if (timedisp.get(0).equals(dto.getTimeSlotId())) {
										srdto.setSno("" + (j + 1));
										srdto.setSlottime(dto.getTimesSlot());
										srdto.setSlottimeId(dto.getTimeSlotId());
										if (((String) timedisp.get(1)).equals("A")) {
											srdto.setRemarks((String) timedisp.get(2));
											srdto.setStatus("BOOKED");

										}
										if (((String) timedisp.get(1)).equals("D")) {
											srdto.setStatus("OPEN");
											srdto.setRemarks("");

										}

										arl.add(srdto);
									} else {
										srdto.setSlottime(dto.getTimesSlot());
										srdto.setSlottimeId(dto.getTimeSlotId());
										srdto.setStatus(CommonConstant.SLOT_STATUS);
										srdto.setRemarks("");
										arl.add(srdto);
									}

								}
							}
						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return arl;

	}

	/**
	 * @param _userslotparam
	 * @param _dto
	 * @param slotparam
	 * @return
	 * @throws Exception
	 */
	public ArrayList userStoreData(String[] _userslotparam, SlotBookDTO _dto, String[] slotparam, String[] historyparam) throws Exception {
		boolean flag = false;
		boolean flagNew = false;
		String strhistory = null;
		int num = 0;
		ArrayList result = new ArrayList();
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		ArrayList<String> list = new ArrayList<String>();

		try {
			// Start:======added by ankita
			SlotBookDAO dao = new SlotBookDAO();

			// End:===== added by ankita
			// Start:Added by SreeLatha for slot booking confirmation Id
			// Generation using Sequence
			Date date = new Date();
			Format yearformat = new SimpleDateFormat("yyyy");
			Format monthformat = new SimpleDateFormat("MM");
			Format dateformat = new SimpleDateFormat("dd");
			String dfmt = dateformat.format(date);
			String yfmt = yearformat.format(date);
			String mfmt = monthformat.format(date);
			String SQL = CommonSQL.PARAM_ID_GENERATION;
			dbUtil.createStatement();
			String number = dbUtil.executeQry(SQL);
			String slottxnnum = "SLOT" + dfmt + mfmt + yfmt + number;
			logger.debug("slottxnnum=====" + slottxnnum);
			// End: Added by SreeLatha for slot booking confirmation Id
			// Generation using Sequence
			/*
			 * String strref = "select max(SLOT_TXN_ID)+1 SLOT_TXN_ID from IGRS_REG_SLOT_BOOK_TXN_DTLS" ; dbUtil.createStatement(); String slottxnnum = dbUtil.executeQry(strref);
			 */
			String str1 = null;

			try {
				if (_userslotparam[4] != null && !(_userslotparam[4].equalsIgnoreCase("No Slot Id"))) {
					str1 = "SELECT * FROM IGRS_REG_SLOT_BOOK_TXN_DTLS WHERE USER_ID = '" + _userslotparam[1] + "' AND OFFICE_ID='" + _userslotparam[2] + "' AND DISTRICT_ID = '" + _userslotparam[3] + "' AND SLOT_ID= '" + _userslotparam[4] + "' AND SLOT_DATE= '" + _userslotparam[7] + "'";
				} else if (_userslotparam[4] != null && _userslotparam[4].equalsIgnoreCase("No Slot Id")) {
					str1 = "SELECT * FROM IGRS_REG_SLOT_BOOK_TXN_DTLS WHERE USER_ID = '" + _userslotparam[1] + "' AND OFFICE_ID='" + _userslotparam[2] + "' AND DISTRICT_ID = '" + _userslotparam[3] + "' AND SLOT_ID= '" + _userslotparam[4] + "' AND SLOT_DATE= '" + _userslotparam[7] + "' AND REG_TXN_ID= '" + _userslotparam[5] + "'";
				}
				dbUtil.createStatement();

				list = dbUtil.executeQuery(str1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int size = list.size();
			// size = 2;
			if (list != null && size > 0) {
				String lan = _dto.getLanguage();
				if (lan != null && lan.equalsIgnoreCase("en")) {
					_dto.setErrorMsg("Selected Slot is not available.Please book others available slot.");
				} else if (lan != null && lan.equalsIgnoreCase("hi")) {
					_dto.setErrorMsg("चयनित  स्लॉट  उपलब्ध  नहीं  है। अन्य  उपलब्ध  स्लॉट  बुक  करें।");
				}
			} else {
				// START | changes to avoid duplicate insert in
				// IGRS_REG_SLOT_BOOK_TXN_DTLS tables by santosh
				String str2 = null;
				ArrayList<String> list1 = new ArrayList<String>();
				if (_userslotparam[4] != null && !(_userslotparam[4].equalsIgnoreCase("No Slot Id"))) {

					str2 = "SELECT * FROM IGRS_REG_SLOT_BOOK_TXN_DTLS WHERE REG_TXN_ID= '" + _userslotparam[5] + "'";
				}
				if (_userslotparam[4] != null && _userslotparam[4].equalsIgnoreCase("No Slot Id")) {

					str2 = "SELECT * FROM IGRS_REG_SLOT_BOOK_TXN_DTLS WHERE REG_TXN_ID= '" + _userslotparam[5] + "'";
				}
				dbUtil.createStatement();
				list1 = dbUtil.executeQuery(str2);

				if (list1 != null && list1.size() > 0) {

					result = userStoreDataReschedule(_userslotparam, _dto, slotparam, historyparam);
				} else {
					// END | changes to avoid duplicate insert in
					// IGRS_REG_SLOT_BOOK_TXN_DTLS tables by santosh
					String str = CommonSQL.SLOTBOOK_USER_INSERT;
					_userslotparam[0] = slottxnnum;
					historyparam[0] = slottxnnum;

					logger.debug("slot txn id is" + historyparam[0]);
					SroSlotSheduleDTO dto_ = new SroSlotSheduleDTO();

					// added by ankita 19-10-2012 for adding booking status to
					// table
					// _userslotparam[9] ="A"; //shows that slot is booked
					System.out.println("in dao try for sro" + str);
					dbUtil.createPreparedStatement(str);

					flag = dbUtil.executeUpdate(_userslotparam);
				}
			}
			if (flag) {

				String blockInsertion = CommonSQL.SLOTBOOK_USER_INSERT_BLOCK;

				String strref = "SELECT max(REG_SLOT_BLOCK_ID) from IGRS_REG_SLOT_BLOCK_DETAILS ";
				dbUtil.createStatement();
				String number1 = dbUtil.executeQry(strref);// getting ref number
				if (!number1.equalsIgnoreCase("") && !number1.isEmpty()) {
					num = Integer.parseInt(number1) + 1;
				}

				else {

					num = 1;
				}
				String[] blockInsertionData = new String[9];
				blockInsertionData[0] = _userslotparam[0];

				blockInsertionData[1] = _userslotparam[1];
				blockInsertionData[2] = _userslotparam[2];
				blockInsertionData[3] = _userslotparam[4];
				blockInsertionData[4] = _userslotparam[5];
				blockInsertionData[5] = _userslotparam[6];

				blockInsertionData[6] = _userslotparam[7];
				blockInsertionData[7] = "A";
				blockInsertionData[8] = String.valueOf(num);

				dbUtil.createPreparedStatement(blockInsertion);

				flagNew = dbUtil.executeUpdate(blockInsertionData);
				if (flagNew) {

					strhistory = CommonSQL.SLOTBOOK_SR_SLOTHISTORY_INSERT;

					String[] historyInsertionData = new String[7];
					historyInsertionData[0] = slottxnnum;// txn id

					historyInsertionData[1] = _userslotparam[1];// user id (sr)
					historyInsertionData[2] = _userslotparam[2];// office id
					historyInsertionData[3] = _userslotparam[4];// slot id
					historyInsertionData[4] = _userslotparam[6];// created by

					historyInsertionData[5] = _userslotparam[7];// blocked date
					historyInsertionData[6] = _userslotparam[5];// reg init id

					if (historyInsertionData[0] == null || historyInsertionData[0] == "") {
						String slottxnID = getslotblockrefid();
						historyInsertionData[0] = slottxnID;
					}
					if (dbUtil == null) {
						dbUtil = new DBUtility();
					}
					dbUtil.createPreparedStatement(strhistory);
					boolean rflag = dbUtil.executeUpdate(historyInsertionData);
				}

			}

			// boolean abc = storeSroData(slotparam, historyparam, dto_);
			// if (abc) {
			// logger.debug("successfully inserted into sr table");
			// }
			if (flag) {
				_dto.setSlotRefId(slottxnnum);
				result.add(_dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return result;

	}

	public ArrayList userStoreDataReschedule(String[] _userslotparam, SlotBookDTO _dto, String[] slotparam, String[] historyparam) throws Exception {
		boolean flag = false;
		boolean flagNew = false;
		int num = 0;
		ArrayList result = new ArrayList();
		String slotrefID = "";
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		ArrayList<String> list = new ArrayList<String>();
		boolean ac = false;
		String records = null;
		boolean slotExceptionFlag = false;
		try {
			// Start:======added by ankita
			SlotBookDAO dao = new SlotBookDAO();

			// End:===== added by ankita
			// Start:Added by SreeLatha for slot booking confirmation Id
			// Generation using Sequence
			Date date = new Date();
			Format yearformat = new SimpleDateFormat("yyyy");
			Format monthformat = new SimpleDateFormat("MM");
			Format dateformat = new SimpleDateFormat("dd");
			String dfmt = dateformat.format(date);
			String yfmt = yearformat.format(date);
			String mfmt = monthformat.format(date);

			// End: Added by SreeLatha for slot booking confirmation Id
			// Generation using Sequence
			/*
			 * String strref = "select max(SLOT_TXN_ID)+1 SLOT_TXN_ID from IGRS_REG_SLOT_BOOK_TXN_DTLS" ; dbUtil.createStatement(); String slottxnnum = dbUtil.executeQry(strref);
			 */
			String str1 = null;
			try {
				if (_userslotparam[4] != null && !(_userslotparam[4].equalsIgnoreCase("No Slot Id"))) {
					str1 = "SELECT * FROM IGRS_REG_SLOT_BOOK_TXN_DTLS WHERE USER_ID = '" + _userslotparam[1] + "' AND OFFICE_ID='" + _userslotparam[2] + "' AND DISTRICT_ID = '" + _userslotparam[3] + "' AND SLOT_ID= '" + _userslotparam[4] + "' AND SLOT_DATE= '" + _userslotparam[7] + "'";
				} else if (_userslotparam[4] != null && _userslotparam[4].equalsIgnoreCase("No Slot Id")) {
					str1 = "SELECT * FROM IGRS_REG_SLOT_BOOK_TXN_DTLS WHERE USER_ID = '" + _userslotparam[1] + "' AND OFFICE_ID='" + _userslotparam[2] + "' AND DISTRICT_ID = '" + _userslotparam[3] + "' AND SLOT_ID= '" + _userslotparam[4] + "' AND SLOT_DATE= '" + _userslotparam[7] + "' AND REG_TXN_ID= '" + _userslotparam[5] + "'";
				}
				dbUtil.createStatement();

				list = dbUtil.executeQuery(str1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (list != null && list.size() > 0) {

				String lan = _dto.getLanguage();
				if (lan != null && lan.equalsIgnoreCase("en")) {
					_dto.setErrorMsg("Selected Slot is not available.Please book others available slot.");
				} else if (lan != null && lan.equalsIgnoreCase("hi")) {
					_dto.setErrorMsg("चयनित  स्लॉट  उपलब्ध  नहीं  है। अन्य  उपलब्ध  स्लॉट  बुक  करें।");
				}

			} else {

				String str = "UPDATE IGRS_REG_SLOT_BOOK_TXN_DTLS SET USER_ID=?,OFFICE_ID=?,DISTRICT_ID=?,SLOT_ID=?,UPDATE_BY=?,UPDATE_DATE=SYSDATE,SLOT_DATE=?,count=nvl(count,0)+1 WHERE REG_TXN_ID=?";

				String prm[] = new String[7];
				prm[0] = _userslotparam[1];
				prm[1] = _userslotparam[2];
				prm[2] = _userslotparam[3];
				prm[3] = _userslotparam[4];
				prm[4] = _userslotparam[6];
				prm[5] = _userslotparam[7];
				prm[6] = _userslotparam[5];
				logger.debug("slot txn id is" + historyparam[0]);
				SroSlotSheduleDTO dto_ = new SroSlotSheduleDTO();

				// added by ankita 19-10-2012 for adding booking status to table
				// _userslotparam[9] ="A"; //shows that slot is booked
				System.out.println("in dao try for sro" + str);
				dbUtil.createPreparedStatement(str);
				ac = dbUtil.executeUpdate(prm);
				// result.add(_dto);

			}

			if (ac) {

				String strBlock = "UPDATE IGRS_REG_SLOT_BLOCK_DETAILS SET BLOCK_STATUS = 'D' WHERE REG_TXN_ID=?";

				String block[] = new String[1];
				block[0] = _userslotparam[5];

				dbUtil.createPreparedStatement(strBlock);
				boolean blockInsert = dbUtil.executeUpdate(block);
				if (blockInsert) {

					String blockInsertion = CommonSQL.SLOTBOOK_USER_INSERT_BLOCK;

					String strref = "SELECT max(REG_SLOT_BLOCK_ID) from IGRS_REG_SLOT_BLOCK_DETAILS ";
					dbUtil.createStatement();
					String number1 = dbUtil.executeQry(strref);// getting ref
					// number
					if (!number1.equalsIgnoreCase("") && !number1.isEmpty()) {
						num = Integer.parseInt(number1) + 1;
					} else {
						num = 1;
					}
					String[] blockInsertionData = new String[9];

					if (_dto.getSlotBkViewList().size() > 0) {
						SlotBookDTO refId = (SlotBookDTO) _dto.getSlotBkViewList().get(0);
						slotrefID = (String) refId.getRefId();
						blockInsertionData[0] = slotrefID;
					} else {
						blockInsertionData[0] = _userslotparam[0];
					}

					blockInsertionData[1] = _userslotparam[1];
					blockInsertionData[2] = _userslotparam[2];
					blockInsertionData[3] = _userslotparam[4];
					blockInsertionData[4] = _userslotparam[5];
					blockInsertionData[5] = _userslotparam[6];

					blockInsertionData[6] = _userslotparam[7];
					blockInsertionData[7] = "A";
					blockInsertionData[8] = String.valueOf(num);

					dbUtil.createPreparedStatement(blockInsertion);

					flagNew = dbUtil.executeUpdate(blockInsertionData);

					String strhistory = CommonSQL.SLOTBOOK_SR_SLOTHISTORY_INSERT;

					String[] historyInsertionData = new String[7];
					historyInsertionData[0] = _userslotparam[0];// txn id

					historyInsertionData[1] = _userslotparam[1];// user id (sr)
					historyInsertionData[2] = _userslotparam[2];// office id
					historyInsertionData[3] = _userslotparam[4];// slot id
					historyInsertionData[4] = _userslotparam[6];// created by

					historyInsertionData[5] = _userslotparam[7];// blocked date
					historyInsertionData[6] = _userslotparam[5];// reg init id

					if (historyInsertionData[0] == null || historyInsertionData[0] == "") {
						String slottxnID = getslotblockrefid();
						historyInsertionData[0] = slottxnID;
					}
					if (dbUtil == null) {
						dbUtil = new DBUtility();
					}
					dbUtil.createPreparedStatement(strhistory);
					boolean rflag = dbUtil.executeUpdate(historyInsertionData);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		try {
			dbUtil = new DBUtility();
			String pm[] = new String[1];
			pm[0] = _userslotparam[5];
			String sql1 = "Select slot_txn_id from igrs_reg_slot_book_txn_dtls where reg_txn_id=?";
			dbUtil.createPreparedStatement(sql1);
			String slottxnnum = dbUtil.executeQry(pm);
			_dto.setSlotRefId(slottxnnum);
			result.add(_dto);

		} catch (Exception e) {

		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return result;

	}

	/**
	 * @param _slotparam
	 * @param _historyparam
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean storeSroData(String[] _slotparam, String[] _historyparam, SroSlotSheduleDTO _dto) throws Exception {
		boolean flag = false;
		boolean hflag = false;
		boolean rflag = false;
		logger.debug("in dao storeSroData");
		logger.debug("historyparam[0]----->" + _historyparam[0]);
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		try {
			logger.debug("the value of history parameter inside sro" + _historyparam[0]);
			String strhistory = null;

			String blkstatus = getsroblockedstatus(_historyparam);
			logger.debug("blkstatus-->" + blkstatus);
			if (blkstatus.equals("D")) {
				logger.debug("inside 'if' if status is D-->D");
				_dto.setSlottimeId(_historyparam[3]);
				_dto.setSelectDate(_historyparam[8]);
				_dto.setRemarks(_historyparam[9]);
				// Start:=====Added by Ankita 5/12/2012
				_dto.setSno(_historyparam[2]); // office id
				_dto.setUserId(_historyparam[1]); // user id
				// End:=====Added by Ankita 5/12/2012
				int blkresult = updatesroslotblock("A", _dto);// _slotid,_datett,_remarksCheck
				if (blkresult == 1) {
					_historyparam[4] = "D";
					_historyparam[5] = "A";
					if (dbUtil == null) {
						dbUtil = new DBUtility();
					}
					strhistory = CommonSQL.SLOTBOOK_SR_BLOCKHISTORY_INSERT;

					if (_historyparam[0] == null || _historyparam[0] == "") {
						String slottxnnum = getslotblockrefid();
						_historyparam[0] = slottxnnum;
					}
					if (dbUtil == null) {
						dbUtil = new DBUtility();
					}
					dbUtil.createPreparedStatement(strhistory);
					rflag = dbUtil.executeUpdate(_historyparam);

				}

			}
			if (blkstatus.equals("") || blkstatus == null) {
				logger.debug("status--> Space or null");
				String str = CommonSQL.SLOTBOOK_SR_BLOCK_INSERT;
				String strref = "SELECT max(REG_SLOT_BLOCK_ID) from IGRS_REG_SLOT_BLOCK_DETAILS ";
				System.out.println(strref);
				if (dbUtil == null) {
					dbUtil = new DBUtility();
				}
				dbUtil.createStatement();
				String number = dbUtil.executeQry(strref);// getting ref number
				int num = Integer.parseInt(number) + 1;
				System.out.println(num);
				_slotparam[6] = Integer.toString(num);
				System.out.println(_slotparam[6]);
				strhistory = CommonSQL.SLOTBOOK_SR_BLOCKHISTORY_INSERT;
				// for transid
				if (dbUtil == null) {
					dbUtil = new DBUtility();
				}

				dbUtil.createPreparedStatement(str);
				flag = dbUtil.executeUpdate(_slotparam);

				if (flag) {
					if (dbUtil == null) {
						dbUtil = new DBUtility();
					}
					dbUtil.createPreparedStatement(strhistory);

					if (_historyparam[0] == null || _historyparam[0] == "") {
						String slottxnnum = getslotblockrefid();
						if (dbUtil == null) {
							dbUtil = new DBUtility();
						}
						_historyparam[0] = slottxnnum;
					}
					dbUtil.createPreparedStatement(strhistory);
					hflag = dbUtil.executeUpdate(_historyparam);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return flag;

	}

	public boolean storeSroData1(String[] _slotparam, String[] _historyparam, SroSlotSheduleDTO _dto) throws Exception {
		boolean flag = false;
		boolean hflag = false;
		boolean rflag = false;
		logger.debug("in dao storeSroData");
		logger.debug("historyparam[0]----->" + _historyparam[0]);
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		try {
			logger.debug("the value of history parameter inside sro" + _historyparam[0]);
			String strhistory = null;

			String blkstatus = getsroblockedstatus(_historyparam);
			logger.debug("blkstatus-->" + blkstatus);
			if (blkstatus.equals("D")) {
				logger.debug("inside 'if' if status is D-->D");
				_dto.setSlottimeId(_historyparam[3]);
				_dto.setSelectDate(_historyparam[8]);
				_dto.setRemarks(_historyparam[9]);
				// Start:=====Added by Ankita 5/12/2012
				_dto.setSno(_historyparam[2]); // office id
				_dto.setUserId(_historyparam[1]); // user id
				// End:=====Added by Ankita 5/12/2012
				int blkresult = updatesroslotblock("A", _dto);// _slotid,_datett,_remarksCheck
				if (blkresult == 1) {
					_historyparam[4] = "D";
					_historyparam[5] = "A";
					if (dbUtil == null) {
						dbUtil = new DBUtility();
					}
					strhistory = CommonSQL.SLOTBOOK_SR_BLOCKHISTORY_INSERT;

					if (_historyparam[0] == null || _historyparam[0] == "") {
						String slottxnnum = getslotblockrefid();
						_historyparam[0] = slottxnnum;
					}
					if (dbUtil == null) {
						dbUtil = new DBUtility();
					}
					dbUtil.createPreparedStatement(strhistory);
					rflag = dbUtil.executeUpdate(_historyparam);

				}

			}
			if (blkstatus.equals("") || blkstatus == null) {
				logger.debug("status--> Space or null");
				String str = CommonSQL.SLOTBOOK_SR_BLOCK_INSERT;
				String strref = "SELECT max(REG_SLOT_BLOCK_ID) from IGRS_REG_SLOT_BLOCK_DETAILS ";
				System.out.println(strref);
				if (dbUtil == null) {
					dbUtil = new DBUtility();
				}
				dbUtil.createStatement();
				String number = dbUtil.executeQry(strref);// getting ref number
				int num = Integer.parseInt(number) + 1;
				System.out.println(num);
				_slotparam[6] = Integer.toString(num);
				System.out.println(_slotparam[6]);
				strhistory = CommonSQL.SLOTBOOK_SR_BLOCKHISTORY_INSERT;
				// for transid
				if (dbUtil == null) {
					dbUtil = new DBUtility();
				}

				dbUtil.createPreparedStatement(str);
				flag = dbUtil.executeUpdate(_slotparam);

				if (flag) {
					if (dbUtil == null) {
						dbUtil = new DBUtility();
					}
					dbUtil.createPreparedStatement(strhistory);

					if (_historyparam[0] == null || _historyparam[0] == "") {
						String slottxnnum = getslotblockrefid();
						if (dbUtil == null) {
							dbUtil = new DBUtility();
						}
						_historyparam[0] = slottxnnum;
					}
					dbUtil.createPreparedStatement(strhistory);
					hflag = dbUtil.executeUpdate(_historyparam);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return flag;

	}

	/**
	 * @param _dto
	 * @param _historyparam
	 * @return
	 * @throws Exception
	 * 
	 * 
	 * 
	 */
	public int updatebookedbysr(SroSlotSheduleDTO _dto, String[] _historyparam) throws Exception {
		int result = 0;
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		try {
			String blkstatus = getsroblockedstatus(_historyparam);
			if (blkstatus.equals("D")) {
			}
			if (dbUtil == null) {
				dbUtil = new DBUtility();
			}
			// Start:===== added by ankita 31.10.2012
			String str1 = gettxnId(_dto, _historyparam); // gets txn id from
			// IGRS_REG_SLOT_BOOK_TXN_DTLS
			logger.debug("str1 value is---->" + str1);
			String str2 = "SLOTBLK";
			boolean flag = false;
			if (str1.toLowerCase().contains(str2.toLowerCase()))// if slot id
			// contains
			// "slotblk"
			{
				flag = true;
			}

			// if flag is true---->slot blocked by sr
			if (flag) {
				String strhistory = CommonSQL.SLOTBOOK_SR_BLOCKHISTORY_INSERT;
				String slottxnnum = getslotblockrefid();
				_historyparam[0] = slottxnnum;

				if (dbUtil == null) {
					dbUtil = new DBUtility();

					dbUtil.createPreparedStatement(strhistory);
					dbUtil.executeUpdate(_historyparam);

					blkstatus = "D";

					result = updatesroslotblock(blkstatus, _dto);
				}

			} else { // code for calling user table and sr table update.
				_historyparam[0] = str1;// set slottxn id equal to the user txn
				// id

				String test = str1;
				/*
				 * updation of IGRS_REG_SLOT_BLOCK_HISTORY
				 * 
				 * *
				 */

				String updatehistory = CommonSQL.UPDATE_HISTORY + "REMARKS='" + _dto.getRemarks() + "' WHERE TXN_ID='" + test + "' " + "AND USER_ID='" + _dto.getUserId() + "' AND SLOT_ID='" + _dto.getSlottimeId() + "' AND BLOCKED_DATE='" + _dto.getSelectDate() + "'";
				System.out.println("query---->" + updatehistory);
				if (dbUtil == null) {
					dbUtil = new DBUtility();

					pstmt = dbUtil.returnPreparedStatement(updatehistory);

					pstmt.executeUpdate();

					// boolean b=dbUtil.executeUpdate(_historyparam);

					blkstatus = "D";
					// update IGRS_REG_SLOT_BLOCK_DETAILS

					result = updatesroslotblock(blkstatus, _dto);
					// update IGRS_REG_SLOT_BOOK_TXN_DTLS
					int result1 = updateusrslotblock(blkstatus, _dto, _historyparam);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			} else {
				dbUtil = null;
			}
		}

		return result;

	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	private int updatesroslotblock(String _blkstatus, SroSlotSheduleDTO _dto) throws Exception {
		int blkstatus = 0;
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		try {

			String updatestr = "UPDATE IGRS_REG_SLOT_BLOCK_DETAILS SET BLOCK_STATUS='" + _blkstatus + "',REMARKS='" + _dto.getRemarks() + "' WHERE OFFICE_ID='" + _dto.getSno() + "' AND USER_ID='" + _dto.getUserId() + "' AND SLOT_ID='" + _dto.getSlottimeId() + "' AND BLOCKED_DATE='" + _dto.getSelectDate() + "'";
			pstmt = dbUtil.returnPreparedStatement(updatestr);

			blkstatus = pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return blkstatus;
	}

	// START:======== ADDED BY ANKITA
	/**
	 * @param _dto
	 * @param _historyparam
	 * @return
	 * @throws Exception
	 */
	public String gettxnId(SroSlotSheduleDTO _dto, String[] _historyparam) throws Exception {

		String refRegTransId = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.GET_TXN_ID + _dto.getSno() + "' AND " + "USER_ID='" + _dto.getUserId() + "' AND" + " SLOT_ID='" + _dto.getSlottimeId() + "'" + "AND BLOCKED_DATE='" + _dto.getSelectDate() + "'";
			if (dbUtil == null)
				dbUtil = new DBUtility();
			dbUtil.createStatement();
			refRegTransId = dbUtil.executeQry(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return refRegTransId;
	}

	// FOR USER TABLE UPDATION
	/**
	 * @param _blkstatus
	 * @param _dto
	 * @param _historyparam
	 * @return
	 * @throws Exception
	 */
	private int updateusrslotblock(String _blkstatus, SroSlotSheduleDTO _dto, String[] _historyparam) throws Exception {
		int blkstatus = 0;

		String str = _historyparam[0];// for getting slot txn id from
		// igrs_reg_slot_blk_history
		System.out.println("slotid" + _dto.getSlottimeId());
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		try {
			String updateusr = CommonSQL.USER_UPDATE_SRCANCEL + _dto.getRemarks() + "'," + "UPDATE_BY='" + _dto.getUserId() + "',UPDATE_DATE='" + _dto.getSelectDate() + "'," + "SLOT_STATUS='" + _blkstatus + "'" + "WHERE SLOT_TXN_ID='" + str + "'";
			pstmt = dbUtil.returnPreparedStatement(updateusr);

			blkstatus = pstmt.executeUpdate();
			System.out.println("blkstatuts" + blkstatus);

			if (blkstatus == 1) {
				email(_dto, _historyparam);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return blkstatus;
	}

	// Start:=====Added by ankita on 11.12.2012 for email sending
	private ArrayList email(SroSlotSheduleDTO _dto, String[] _historyparam) throws Exception {
		// TODO Auto-generated method stub
		ArrayList ar1 = new ArrayList();
		String str = _historyparam[0];// get the txn id
		String subject = "Regarding Slot Cancellation";// enter subject of the
		// Email.
		String slottime, slotdate, appId, regId, updtdBy = "";// content
		// variables
		String userId = "";// user id to which mail has to be sent
		String status = new String();
		DBUtility dbUtil = null;
		try {
			ArrayList dd = new ArrayList();
			ArrayList ddrl = new ArrayList();
			// change b.USER_ID TO b.CREATED_BY on a later stage
			// userid should contain the id of the user to whom email has to be
			// sent
			String sql = "SELECT b.SLOT_TXN_ID,b.USER_ID,b.SLOT_ID,b.REG_TXN_ID,b.SLOT_DATE," + "( SELECT distinct TO_CHAR(TIME_FROM,'HH12:MI')||' - '||TO_CHAR(TIME_TO,'HH12:MI') FROM IGRS_REG_SLOT_MASTER a WHERE a.SLOT_ID=b.SLOT_ID)" + " SLOT_TIME,b.UPDATE_BY  FROM IGRS_REG_SLOT_BOOK_TXN_DTLS b " + "WHERE SLOT_TXN_ID='" + str + "'";
			System.out.println("query" + sql);
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			dd = dbUtil.executeQuery(sql);
			if (dd.size() != 0) {
				for (int i = 0; i < dd.size(); i++) {
					ddrl = new ArrayList();
					ddrl = (ArrayList) dd.get(i);
					if (ddrl.size() > 0) {
						appId = (String) ddrl.get(0);
						userId = (String) ddrl.get(1);
						regId = (String) ddrl.get(3);
						slotdate = (String) ddrl.get(4);
						slottime = (String) ddrl.get(5);
						updtdBy = (String) ddrl.get(6);
						ar1.add(0, appId);
						ar1.add(1, userId);
						ar1.add(2, regId);
						ar1.add(3, slotdate);
						ar1.add(4, slottime);
						ar1.add(5, updtdBy);
					}
				}
			}
			String Content = "Your slot for Application Id " + ar1.get(0) + " and Reference Id " + ar1.get(2) + " for " + ar1.get(4) + " on " + ar1.get(3) + " has been cancelled by " + ar1.get(5);

			System.out.println("content" + Content);
			System.out.println("userid" + userId + ar1.get(1));
			System.out.println("subject" + subject);
			if (ar1.size() != 0) {
				if (dbUtil != null) {
					dbUtil = new DBUtility();
					dbUtil.createCallableStatement(CommonSQL.CALL_IGRS_INSERT_EMAIL_DATA);
					status = dbUtil.insertEmailData(userId, subject, Content);
					System.out.println("STATUS --->");
					System.out.println("STATUS --->" + status);
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return ar1;

	}

	// END:======== ADDED BY ANKITA FOR USER TABLE UPDATION

	/**
	 * @param _historyparam
	 * @return
	 * @throws Exception
	 */
	private String getsroblockedstatus(String[] _historyparam) throws Exception {
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		String blkstatus = "";
		try {

			String statusstr = "SELECT BLOCK_STATUS FROM IGRS_REG_SLOT_BLOCK_DETAILS  WHERE OFFICE_ID='" + _historyparam[2] + "' AND USER_ID='" + _historyparam[1] + "' AND SLOT_ID='" + _historyparam[3] + "' AND BLOCKED_DATE='" + _historyparam[8] + "'";
			dbUtil.createStatement();
			blkstatus = dbUtil.executeQry(statusstr);
			logger.debug("" + blkstatus);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;

		}

		return blkstatus;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private String getslotblockrefid() throws Exception {
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		String slottxnnum = "";
		try {
			// Start Added by SreeLatha for slot book create txn_id
			Date date = new Date();
			Format yearformat = new SimpleDateFormat("yyyy");
			Format monthformat = new SimpleDateFormat("MM");
			Format dateformat = new SimpleDateFormat("dd");
			String dfmt = dateformat.format(date);
			String yfmt = yearformat.format(date);
			String mfmt = monthformat.format(date);
			String strref = "select IGRS_REG_SLOT_BLOCK_SEQ.NEXTVAL from DUAL";
			dbUtil.createStatement();
			String number = dbUtil.executeQry(strref);// getting ref number
			slottxnnum = "SLOTBLK" + dfmt + mfmt + yfmt + number;
			logger.debug("slottxnnum=====" + slottxnnum);
			// End Added by SreeLatha for slot book create txn_id
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;

		}

		return slottxnnum;

	}

	/**
	 * @param _createdBy
	 * @return
	 * @throws Exception
	 */
	public ArrayList getUserReport(String _createdBy) throws Exception {
		logger.debug("in dao getslotblockrefid");
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		ArrayList userList = new ArrayList();
		try {
			String strref = "select b.SLOT_TXN_ID,b.REG_TXN_ID,to_char(b.SLOT_DATE,'DD-MON-YYYY') SLOT_DATE,(SELECT distinct TO_CHAR(a.TIME_FROM,'HH:MI AM') FROM IGRS_REG_SLOT_MASTER a WHERE a.SLOT_ID=b.SLOT_ID) SLOT_TIME,(select c.FIRST_NAME||' '||c.MIDDLE_NAME||' '||c.LAST_NAME from IGRS_EMP_MASTER c where c.EMP_ID=b.USER_ID) SR_NAME,(select d.OFFICE_NAME   from IGRS_OFFICE_MASTER d where d.OFFICE_ID=b.OFFICE_ID) SRO_OFFICE,b.CREATED_BY from IGRS_REG_SLOT_BOOK_TXN_DTLS b  where b.CREATED_BY='" + _createdBy + "'";
			dbUtil.createStatement();
			userList = dbUtil.executeQuery(strref);// getting ref number
			logger.debug("userList-->" + userList);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;

		}
		return userList;

	}

	// Start Added by SreeLatha added for sro name list
	/**
	 * @param _sroId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSrNameList(String _sroId) throws Exception {
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		ArrayList sroNameList = new ArrayList();
		ArrayList srNameList = new ArrayList();
		try {
			String str = "SELECT FIRST_NAME  ||'  '  ||LAST_NAME NAME,  EMP_ID FROM IGRS_EMP_MASTER WHERE EMP_ID IN " + "  (SELECT user_id  FROM IGRS_USER_ROLE_GROUP_MAPPING  WHERE role_group_id IN " + "  (SELECT role_group_id    FROM IGRS_RGROUP_ROLE_MAPPING    WHERE STATUS='A' and role_office_map_id IN " + "      (SELECT role_office_map_id      FROM IGRS_ROLE_OFFICE_MAPPING      WHERE office_id='" + _sroId + "' " + "      AND role_id   IN        (SELECT role_id        FROM IGRS_ROLE_FN_ACTIVITY_MAPPING " + "        WHERE activity_id='ACT_233'        )      )    ) " + "    ) and emp_id not in(select  EMP_ID FROM IGRS_EMP_MASTER " + " WHERE EMP_ID IN  (SELECT user_id  FROM IGRS_USER_ROLE_GROUP_MAPPING   WHERE role_group_id IN " + "    (SELECT role_group_id    FROM IGRS_RGROUP_ROLE_MAPPING    WHERE STATUS='A' and role_office_map_id IN      (SELECT role_office_map_id " + "      FROM IGRS_ROLE_OFFICE_MAPPING      WHERE office_id='" + _sroId + "'      AND role_id   IN " + "        (SELECT role_id        FROM IGRS_ROLE_FN_ACTIVITY_MAPPING        WHERE activity_id='ACT_232' " + "        )      )    )  ))";
			/*
			 * String str = CommonSQL.SLOTBOOK_SRNAME_DROPDOWN_SELECT + _sroId + "')"; str = "select FIRST_NAME||'  '||LAST_NAME NAME,EMP_ID from IGRS_EMP_MASTER where EMP_ID in (select user_id from IGRS_USER_ROLE_GROUP_MAPPING where role_group_id in (select role_group_id from IGRS_RGROUP_ROLE_MAPPING where role_office_map_id in " + "(select role_office_map_id from IGRS_ROLE_OFFICE_MAPPING where office_id='" + _sroId + "' and role_id in (select role_id from IGRS_ROLE_FN_ACTIVITY_MAPPING where activity_id='ACT_233'))))" ;
			 */logger.debug(str);
			dbUtil.createStatement();
			sroNameList = dbUtil.executeQuery(str);
			if (sroNameList.size() == 0) {
				str = "select FIRST_NAME||'  '||LAST_NAME NAME,EMP_ID from IGRS_EMP_MASTER where EMP_ID in (select user_id from IGRS_USER_ROLE_GROUP_MAPPING where role_group_id in (select role_group_id from IGRS_RGROUP_ROLE_MAPPING where STATUS            ='A' AND role_office_map_id in " + "(select role_office_map_id from IGRS_ROLE_OFFICE_MAPPING where office_id='" + _sroId + "' and role_id in (select role_id from IGRS_ROLE_FN_ACTIVITY_MAPPING where activity_id='ACT_233'))))";// query
				// modified
				// for
				// slot
				// view
				// by
				// saurav

				System.out.println(str);
				dbUtil.createStatement();
				sroNameList = dbUtil.executeQuery(str);
			}
			for (int i = 0; i < sroNameList.size(); i++) {
				ArrayList list = (ArrayList) sroNameList.get(i);
				SlotBookDTO dto = new SlotBookDTO();
				dto.setSrName("SLOT" + (i + 1));
				dto.setSrId((String) list.get(1));
				srNameList.add(dto);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;

		}
		return srNameList;
	}

	// End Added by SreeLatha added for sro name list

	// Start Added by SreeLatha for slotAmnt
	/**************************************************************
	 * Method Name : getSlotBookFee() Arguments : Function Id, Sub Function Id Return : slotBookFee Exception : NullPointer or SQLException or Exception
	 ***************************************************************/
	public String getSlotBookFee(String _funId, String _userId) throws NullPointerException, SQLException, Exception {
		DBUtility dbUtility = null;
		CallableStatement clstmt = null;
		String slotBookFee = null;
		logger.debug("Inside getSlotBookFee()");
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String ser_Id = dbUtility.executeQry(CommonSQL.USER_SLOT_BOOK_SELECT + "'" + _funId + "'");
			System.out.println("--ser_Id--<>" + ser_Id);
			clstmt = dbUtility.returnCallableStatement(CommonSQL.SERVICE_FEE_PROCEDURE);
			clstmt.setString(1, _funId);
			clstmt.setString(2, ser_Id);
			clstmt.setString(3, _userId);
			clstmt.registerOutParameter(4, OracleTypes.NUMBER);
			clstmt.registerOutParameter(5, OracleTypes.CLOB);
			clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			if (!clstmt.execute()) {
				int temp = clstmt.getInt(4);
				slotBookFee = String.valueOf(temp);
				String SerArray = clstmt.getString(5);
				logger.debug("SerArray ==" + SerArray);
			}
		} catch (NullPointerException ne) {
			logger.error("Null Pointer Exception at getSlotBookFee in DAO " + ne);
		} catch (SQLException se) {
			logger.error("SQL Exception at getSlotBookFee in DAO " + se);
		} catch (Exception e) {
			logger.error("Exception at getSlotBookFee in DAO " + e);
		} finally {
			dbUtility.closeConnection();
		}
		logger.debug("THE SlotBookFee IS " + slotBookFee);
		return slotBookFee;
	}

	// End Added by SreeLatha for slotAmnt
	// Start:==Added By SreeLatha for inserting paymentTxn_in and Slot_Id into
	// IGRS_REG_SLOT_PAYMENT_DTLS
	public boolean insertPayTxnSLotTxnId(String _payTxnId, String _slotTxnId) throws Exception {
		boolean returnflag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = CommonSQL.PAY_TXN_SLOT_TXN_DETAILS;
			String paySlot[] = new String[2];
			paySlot[0] = _slotTxnId;
			paySlot[1] = _payTxnId;
			dbUtility.createPreparedStatement(sql);
			returnflag = dbUtility.executeUpdate(paySlot);
			logger.debug("returnflag===========" + returnflag);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("this is Exception in insertPayTxnSLotTxnId(): " + e);
			dbUtility.rollback();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
				logger.debug("this is Fianlly Try catch in insertPayTxnSLotTxnId DAO " + e);
			}
		}
		return returnflag;
	}

	// End:==Added By SreeLatha for inserting paymentTxn_in and Slot_Id into
	// IGRS_REG_SLOT_PAYMENT_DTLS

	// Start:====Added by SreeLatha for SlotBooking View
	public ArrayList getSlotBkViewDetails(SlotBookDTO _dto) {
		DBUtility dbUtility = null;
		ArrayList slotBkViewList = new ArrayList();
		String radio = _dto.getRadiodate();
		String durationFrom = _dto.getDurationFrom();
		String durationTo = _dto.getDurationTo();
		String refId = _dto.getRefId();
		String status = _dto.getStatus();

		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			// START | slot book view changes to display the reschedule slots as
			// well by santosh

			// String sqlQry = CommonSQL.SLOT_BOOKING_VIEW_DETAILS;
			String sqlQry = CommonSQL.SLOT_BOOKING_VIEW_DETLS;
			if (radio.equalsIgnoreCase("refID")) {

				/*
				 * slotBkViewList = dbUtility .executeQuery(sqlQry + " where b.REG_TXN_ID = '" + refId + "' order by TO_DATE(SLOT_DATE,'DD/MM/YYYY'),TO_DATE(SLOT_TIME, 'HH:MI AM')" );
				 */

				slotBkViewList = dbUtility.executeQuery(sqlQry + " where b.REG_TXN_ID = '" + refId + "' order by TO_DATE(BLOCKED_DATE,'DD/MM/YYYY'),TO_DATE(SLOT_TIME, 'HH:MI AM')");

			} else if (radio.equalsIgnoreCase("week")) {
				System.out.println("query" + sqlQry);
				/*
				 * slotBkViewList = dbUtility .executeQuery(sqlQry + "" + " where b.SLOT_DATE  BETWEEN TO_DATE('" + durationFrom + "','DD/MM/YYYY') " + " AND TO_DATE('" + durationTo + "','DD/MM/YYYY') " + " order by TO_DATE(SLOT_DATE,'DD/MM/YYYY'),TO_DATE(SLOT_TIME, 'HH:MI AM') " );
				 */

				slotBkViewList = dbUtility.executeQuery(sqlQry + "" + " where b.BLOCKED_DATE  BETWEEN TO_DATE('" + durationFrom + "','DD/MM/YYYY') " + " AND TO_DATE('" + durationTo + "','DD/MM/YYYY') " + " order by TO_DATE(BLOCKED_DATE,'DD/MM/YYYY'),TO_DATE(SLOT_TIME, 'HH:MI AM') ");

			} else {
				/*
				 * slotBkViewList = dbUtility .executeQuery(sqlQry + " where b.SLOT_STATUS = '" + status + "' order by TO_DATE(SLOT_DATE,'DD/MM/YYYY'),TO_DATE(SLOT_TIME, 'HH:MI AM') " );
				 */

				slotBkViewList = dbUtility.executeQuery(sqlQry + " where b.BLOCK_STATUS = '" + status + "' order by TO_DATE(BLOCKED_DATE,'DD/MM/YYYY'),TO_DATE(SLOT_TIME, 'HH:MI AM') ");

				// END | slot book view changes to display the reschedule slots
				// as well by santosh
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("thisis Exception in getSlotBkViewDetails() DAO: " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
				logger.debug("this is Fianlly Try catch in getSlotBkViewDetails DAO " + e);
			}
		}
		return slotBkViewList;
	}

	// End:====Added by SreeLatha for SlotBooking View

	// Start:====Added by SreeLatha for SlotBooking View Get Data for
	// particularId
	public ArrayList getRefIdDetails(String refId) {
		DBUtility dbUtility = null;
		ArrayList slotBkViewList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sqlQry = CommonSQL.SLOT_BOOKING_VIEW_DETAILS;
			slotBkViewList = dbUtility.executeQuery(sqlQry + " where b.SLOT_TXN_ID = '" + refId + "' order by SR_NAME");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("thisis Exception in getRefIdDetails() DAO: " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
				logger.debug("this is Fianlly Try catch in getRefIdDetails DAO " + e);
			}
		}
		return slotBkViewList;
	}

	// End:====Added by SreeLatha for SlotBooking View Get Data for particularId

	public ArrayList getOffId(String userId) {
		DBUtility dbUtility = null;
		ArrayList offIdList = new ArrayList();
		ArrayList subList = new ArrayList();

		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sqlQry = CommonSQL.SLOT_BOOKING_OFF_ID + "'" + userId + "'";
			offIdList = dbUtility.executeQuery(sqlQry);
			subList = (ArrayList) offIdList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("thisis Exception in getOffId() DAO: " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
				logger.debug("this is Fianlly Try catch in getOffId DAO " + e);
			}
		}
		return subList;
	}

	public static void main(String[] args) throws Exception {
		SlotBookDAO dao = new SlotBookDAO();
		System.out.println(dao.getSlotBookFee("FUN_036", "3"));

	}

	// Start:===== added by Ankita 7-11-2012 for checking if a date falls on
	// weekend/holiday

	public int checkweekend(SroSlotSheduleDTO srodto) throws Exception {
		// TODO Auto-generated method stub
		int weekend = 0;
		ArrayList holiday = new ArrayList();
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		try {
			String dt = srodto.getSelectDate();
			/*
			 * DateFormat formatter ; Date date ; formatter = new SimpleDateFormat("dd-MMM-yy"); String date1=formatter.format(str_date);
			 * 
			 * SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mmm/yyyy"); Date convertedDate = dateFormat.parse(dt); logger.debug("Converted string to date : " +convertedDate); *
			 */
			logger.debug("date1---->" + dt);

			logger.debug("test comment");

			logger.debug("date is " + dt);
			String str = "SELECT HOLIDAY_DATE FROM IGRS_HOLIDAY_LIST WHERE HOLIDAY_DATE =to_date('" + dt + "','dd-mm-yyyy')" + " AND APPROVAL_STATUS='P' AND HOLIDAY_STATUS='P' ";
			dbUtil.createStatement();
			holiday = dbUtil.executeQuery(str);
			if (holiday.size() != 0) {
				weekend = 1;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return weekend;
	}

	// End:===== added by Ankita 7-11-2012

	// Start:======added by ankita 8-11-2012
	// updated on 12/10/2012
	public ArrayList check(SlotBookDTO slotdto) throws Exception {
		ArrayList arl = new ArrayList();
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();
		try {

			logger.debug("session application number is" + slotdto.getRegId());
			ArrayList dd = new ArrayList();
			ArrayList ddrl = new ArrayList();
			String str = "SELECT b.REG_TXN_ID,b.SLOT_TXN_ID,to_char(b.SLOT_DATE, 'dd/mm/yyyy'),b.SLOT_ID,b.REMARKS,b.SLOT_STATUS," + "(SELECT distinct TO_CHAR(TIME_FROM,'HH12:MI')||' - '||TO_CHAR(TIME_TO,'HH12:MI') " + " FROM IGRS_REG_SLOT_MASTER a WHERE a.SLOT_ID=b.SLOT_ID) SLOT_TIME, nvl(count,0) FROM IGRS_REG_SLOT_BOOK_TXN_DTLS b" + " WHERE REG_TXN_ID='" + slotdto.getRegId() + "'";
			dbUtil.createStatement();
			dd = dbUtil.executeQuery(str);
			if (dd.size() != 0) {
				for (int i = 0; i < dd.size(); i++) {
					ddrl = new ArrayList();
					ddrl = (ArrayList) dd.get(i);
					if (ddrl.size() > 0) {
						SlotBookDTO book = new SlotBookDTO();
						book.setAppNumber((String) ddrl.get(0));
						book.setRefId((String) ddrl.get(1));
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date Date = sdf.parse((String) ddrl.get(2));
						String dt = sdf.format(Date);

						book.setVisitDate(dt);
						book.setVisitTime((String) ddrl.get(6));
						String status = (String) ddrl.get(5);
						if (status.equalsIgnoreCase("D")) {
							book.setStatus("cancelled");
						} else {
							book.setStatus("open");
						}
						book.setRemarks((String) ddrl.get(4));
						Integer count = Integer.parseInt((String) ddrl.get(7));
						String sql = "select ATTRIBUTE_VALUE from igrs_config_param_list WHERE attribute_id='ATT_SLOT_RE' AND ATTRIBUTE_STATUS='A'";
						dbUtil.createStatement();
						String slotCountAtt = dbUtil.executeQry(sql);
						book.setBookedCount("Y");
						if(!"".equalsIgnoreCase(slotCountAtt)){

							int slotCount = Integer.parseInt(slotCountAtt);
							if (count >= slotCount) {
								book.setBookedCount("N");
							} else {
								book.setBookedCount("Y");
							}
						}
						System.out.println("details" + (String) ddrl.get(0) + (String) ddrl.get(1) + (String) ddrl.get(2) + (String) ddrl.get(5) + (String) ddrl.get(4));
						arl.add(book);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return arl;
	}

	// End:======added by ankita 8-11-2012

	// Start:=======Added by Ankita on 5/12/2012
	public String getslotTime(String slotId) throws Exception {
		// TODO Auto-generated method stub
		String slottime = "";
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();

		try {
			String str = "SELECT TO_CHAR(TIME_FROM,'HH12:MI')||' - '||TO_CHAR(TIME_TO,'HH12:MI')" + "  FROM IGRS_REG_SLOT_MASTER WHERE SLOT_ID='" + slotId + "'";
			dbUtil.createStatement();
			slottime = dbUtil.executeQry(str);
			System.out.println("slottime" + slottime);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return slottime;

	}

	public String getInstrumentId(String initiationID) throws Exception {
		// TODO Auto-generated method stub
		String instrumentID = "";
		String slotFlag = "";
		DBUtility dbUtil = null;
		dbUtil = new DBUtility();

		try {
			String str = "SELECT INSTRUMENTS_ID" + "  FROM IGRS_REG_TXN_DETLS where REG_TXN_ID='" + initiationID + "'";
			dbUtil.createStatement();
			instrumentID = dbUtil.executeQry(str);
			System.out.println("instID" + instrumentID);

			if (instrumentID != null && !instrumentID.isEmpty()) {

				String string1 = "SELECT SLOT_FLAG" + "  FROM IGRS_DEED_INSTRUMENT_MASTER where INSTRUMENT_ID ='" + instrumentID + "'";
				dbUtil.createStatement();
				slotFlag = dbUtil.executeQry(string1);

				if (slotFlag == null || slotFlag.equalsIgnoreCase("")) {

					slotFlag = "Y";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return slotFlag;

	}

	// End:========added by ankita 5/12/2012

	public int chkRegTable(SlotBookDTO slotdto) throws Exception {
		ArrayList list = new ArrayList();
		int num = 1;
		DBUtility dbUtil = null;
		try {
			logger.debug("session application number is" + slotdto.getRegId());
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String temp[] = new String[2];
			temp[0] = (dbUtil.executeQry(CommonSQL.GET_COMPLETION_STATUS_FROM_MASTER)); // reg
			// init
			// status
			temp[1] = slotdto.getRegId(); // reg init number
			String sql = CommonSQL.Check_Reg_Number_Status;
			dbUtil.createPreparedStatement(sql);
			list = dbUtil.executeQuery(temp);

			if (list.size() > 0) {
				num = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("couldnot cose connection in SlotBookDAO in chkRegTable( ) method");
			}
		}

		return num;
	}

	public ArrayList getDistrictIdList(ArrayList officeList, String lang) {
		SlotBookDTO district;
		ArrayList districtList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String disttId = "";
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createStatement();
			for (int j = 0; j < officeList.size(); j++) {
				ArrayList list = (ArrayList) officeList.get(j);
				String officeId = (String) list.get(0);
				logger.debug("" + officeId);
				String sql = CommonSQL.GET_DISTRICT + "'" + officeId + "'";
				logger.debug("<-------" + sql);
				disttId = dbUtil.executeQry(sql);

				if (!(list2.contains(disttId))) {
					list2.add(disttId);
				}
				list2.trimToSize();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("closing connection error on slot bookdao");

			}

		}
		return getDistrictList(list2, lang);
	}

	public ArrayList getDistrictList(ArrayList disttIdList, String lang) {
		SlotBookDTO bookdto;
		ArrayList districtList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		ArrayList sublist = new ArrayList();
		String disttId = "";
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createStatement();

			for (int j = 0; j < disttIdList.size(); j++) {
				// ArrayList list = (ArrayList)disttIdList.get(j);
				disttId = (String) disttIdList.get(j);
				logger.debug("distt" + disttId);

				String sql = CommonSQL.GET_DISRICT_NAME + "'" + disttId + "'";
				list1 = dbUtil.executeQuery(sql);

				for (int i = 0; i < list1.size(); i++) {
					list2 = (ArrayList) list1.get(i);
					bookdto = new SlotBookDTO();
					bookdto.setDistId(list2.get(0).toString());
					if ("en".equalsIgnoreCase(lang)) {
						bookdto.setDistName(list2.get(1).toString());
					} else {
						bookdto.setDistName(list2.get(2).toString());
					}
					districtList.add(bookdto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return districtList;
	}

	public ArrayList getOfficeName(ArrayList officeList2, String lang) {
		// TODO Auto-generated method stub

		ArrayList officeNameList = new ArrayList();
		DBUtility dbUtil = null;
		try {
			ArrayList subList = new ArrayList();

			for (int i = 0; i < officeList2.size(); i++) {
				ArrayList list = (ArrayList) officeList2.get(i);
				String officeId = (String) list.get(0);
				dbUtil = new DBUtility();
				dbUtil.createStatement();
				String sql = CommonSQL.GET_SR_MAPPED_OFFICES + "'" + officeId + "'";
				list = dbUtil.executeQuery(sql);

				for (int j = 0; j < list.size(); j++) {
					subList = (ArrayList) list.get(j);
					SlotBookDTO bookdto = new SlotBookDTO();
					bookdto.setSroId((String) subList.get(0));
					if ("en".equalsIgnoreCase(lang)) {
						bookdto.setSroName((String) subList.get(1));
					} else {
						bookdto.setSroName((String) subList.get(2));
					}
					officeNameList.add(bookdto);

				}

			}
		} catch (Exception e) {

		} finally {

			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("closing connection error on slot bookdao");

			}

		}
		return officeNameList;
	}

	public String getEstamp(SlotBookDTO bookdto) {
		// TODO Auto-generated method stub

		return null;
	}

	public ArrayList getDistrictFromEstamp(SlotBookDTO bookdto, String lang) throws Exception {
		// TODO Auto-generated method stub
		ArrayList DistrictList = new ArrayList();
		ArrayList list = new ArrayList();
		ArrayList sublist = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String getEstamp = CommonSQL.GET_ESTAMP_CODE;
			dbUtil.createPreparedStatement(getEstamp);
			String temp[] = new String[1];
			temp[0] = bookdto.getRegId();

			String Estamp = dbUtil.executeQry(temp);

			String getDistId = Estamp.substring(4, 6);
			int distId = Integer.parseInt(getDistId);
			dbUtil.createStatement();
			String getDistrict = CommonSQL.GET_DISRICT_NAME + "'" + distId + "'";

			list = dbUtil.executeQuery(getDistrict);

			for (int i = 0; i < list.size(); i++) {
				sublist = (ArrayList) list.get(i);
				SlotBookDTO dto = new SlotBookDTO();
				dto.setDistId(sublist.get(0).toString());
				if ("en".equalsIgnoreCase(lang)) {
					dto.setDistName(sublist.get(1).toString());
				} else {
					dto.setDistName(sublist.get(2).toString());
				}
				DistrictList.add(dto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("closing connection error on slot bookdao");

			}
		}

		return DistrictList;
	}

	public ArrayList getTehsil(SlotBookDTO bookdto, String usrSlctDist, String lang) throws Exception {
		ArrayList tehsilList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String check1 = CommonSQL.CHECK_DISTRICT_MAPPED;
			String check2 = CommonSQL.CHECK_WARD_MAPPED;

			dbUtil.createPreparedStatement(check1);
			String temp1[] = new String[1];
			temp1[0] = usrSlctDist;
			String districtMap;
			districtMap = dbUtil.executeQry(temp1);

			dbUtil.createPreparedStatement(check2);
			String temp2[] = new String[1];
			temp2[0] = bookdto.getRegId();
			String wardMap;
			wardMap = dbUtil.executeQry(temp2);

			if (Integer.parseInt(districtMap) == 0 || Integer.parseInt(wardMap) == 0) {

				dbUtil = new DBUtility();
				dbUtil.createStatement();

				String temp[] = new String[1];
				temp[0] = usrSlctDist;// district ID selected By User
				String sql = CommonSQL.TEHSIL;
				dbUtil.createPreparedStatement(sql);
				list1 = dbUtil.executeQuery(temp);

			} else {

				dbUtil = new DBUtility();
				String check = CommonSQL.CHECK_PROPERTY_OF_REG_TXN;

				dbUtil.createPreparedStatement(check);
				String checkarr[] = new String[1];
				checkarr[0] = bookdto.getRegId();
				String count;
				count = dbUtil.executeQry(checkarr);

				if (Integer.parseInt(count) > 0) {
					if (Integer.parseInt(count) == 1) {
						dbUtil = new DBUtility();
						dbUtil.createStatement();
						String temp[] = new String[2];
						temp[0] = usrSlctDist;// district ID selected By User
						temp[1] = bookdto.getRegId();
						String sql = CommonSQL.TEHSIL_PROPERTY;
						dbUtil.createPreparedStatement(sql);
						list1 = dbUtil.executeQuery(temp);
					} else {
						dbUtil = new DBUtility();
						dbUtil.createStatement();
						String temp[] = new String[2];
						temp[0] = usrSlctDist;// district ID selected By User
						temp[1] = bookdto.getRegId();
						String sql = CommonSQL.TEHSIL_PROPERTY_MULTIPLE;
						dbUtil.createPreparedStatement(sql);
						list1 = dbUtil.executeQuery(temp);
					}
				} else {
					dbUtil = new DBUtility();
					dbUtil.createStatement();

					String temp[] = new String[1];
					temp[0] = usrSlctDist;// district ID selected By User
					String sql = CommonSQL.TEHSIL;
					dbUtil.createPreparedStatement(sql);
					list1 = dbUtil.executeQuery(temp);

				}
			}

			for (int i = 0; i < list1.size(); i++) {
				list2 = (ArrayList) list1.get(i);
				SlotBookDTO sdto = new SlotBookDTO();
				sdto.setTehsilId(list2.get(0).toString());
				if ("en".equalsIgnoreCase(lang)) {
					sdto.setTehsilName(list2.get(1).toString());
				} else {
					sdto.setTehsilName(list2.get(2).toString());
				}
				tehsilList.add(sdto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("closing connection error on slot bookdao");

			}
		}

		return tehsilList;
	}

	public ArrayList getOfficesMappedToTehsil(String regTxnId, String tehsilID, String lang) throws Exception {
		ArrayList officeList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		DBUtility dbUtil = null;
		try {

			dbUtil = new DBUtility();
			String check1 = CommonSQL.CHECK_WARD_MAPPED;

			dbUtil.createPreparedStatement(check1);
			String temp2[] = new String[1];
			temp2[0] = regTxnId;
			String wardMap;
			wardMap = dbUtil.executeQry(temp2);

			if (Integer.parseInt(wardMap) == 1)// ward map h
			{

				dbUtil = new DBUtility();
				dbUtil.createStatement();

				String temp3[] = new String[2];
				temp3[0] = tehsilID;
				temp3[1] = regTxnId;

				String sql = CommonSQL.SRO_WARD;
				dbUtil.createPreparedStatement(sql);
				list1 = dbUtil.executeQuery(temp3);

			} else// ward map ni h
			{

				dbUtil = new DBUtility();
				dbUtil.createStatement();

				String temp[] = new String[2];
				temp[0] = tehsilID;
				temp[1] = dbUtil.executeQry(CommonSQL.GET_OFFICE_TYPE_ID);
				String sql = CommonSQL.OFFICE_MAPPED_TO_TEHSIL;
				dbUtil.createPreparedStatement(sql);
				list1 = dbUtil.executeQuery(temp);

			}

			for (int i = 0; i < list1.size(); i++) {
				list2 = (ArrayList) list1.get(i);
				SlotBookDTO sdto = new SlotBookDTO();
				sdto.setOfficeID(list2.get(0).toString());
				if ("en".equalsIgnoreCase(lang)) {
					sdto.setOfficeName(list2.get(1).toString());
				} else {
					sdto.setOfficeName(list2.get(2).toString());
				}
				officeList.add(sdto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("closing connection error on slot bookdao");

			}
		}

		return officeList;
	}

	public boolean checkBookie(String reg_txn_num, String user_id) {
		boolean flag = false;
		DBUtility dbUtil = null;
		try {
			String a = "";
			dbUtil = new DBUtility();
			String pm[] = new String[2];
			pm[0] = reg_txn_num;
			pm[1] = user_id;
			String query = "select reg_txn_id from igrs_reg_slot_book_txn_dtls where reg_txn_id = ? and created_by = ?";
			dbUtil.createPreparedStatement(query);
			a = dbUtil.executeQry(pm);
			if (!a.equalsIgnoreCase("")) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("closing connection error on slot bookdao");

			}
		}

		return flag;
	}

	public boolean checkUser(String regId, String userId) {
		boolean ret = false;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement("SELECT CASE a.one    WHEN 'TRUE'    THEN A.ONE    ELSE 'FALSE'  END,  CASE B.one    WHEN 'TRUE'    THEN B.ONE    ELSE 'FALSE'  END FROM  (SELECT MAX(ABC) AS ONE,    MAX(DEF)  FROM    (SELECT 'TRUE' AS ABC,      ''           AS DEF    FROM IGRS_REG_TXN_DETLS    WHERE (REG_TXN_ID=?    AND CREATED_BY   =?)    UNION    SELECT '' AS ABC,      'FALSE' AS DEF    FROM IGRS_REG_TXN_DETLS    WHERE REG_TXN_ID <> ?    AND CREATED_BY  <> ?    )  )A ,  (SELECT MAX(ABC) AS ONE,    MAX(DEF)  FROM    (SELECT 'TRUE' AS ABC,      ''           AS DEF    FROM IGRS_USERS_LIST    WHERE USER_ID   =?    AND USER_TYPE_ID='I'    UNION    SELECT '' AS ABC,      'FALSE' AS DEF    FROM IGRS_USERS_LIST    WHERE USER_ID   <>?    AND USER_TYPE_ID<>'I'    )  ) B");
			ArrayList alist = dbUtil.executeQuery(new String[]{regId, userId, regId, userId, userId, userId});
			if (alist != null) {
				for (int i = 0; i < alist.size(); i++) {
					ArrayList lst = (ArrayList) alist.get(i);
					if ("TRUE".equalsIgnoreCase(lst.get(0).toString())) {
						ret = true;
					} else {
						if ("TRUE".equalsIgnoreCase(lst.get(1).toString())) {
							ret = true;
						} else {
							// ADJUDICATION CODE HERE
							dbUtil.createPreparedStatement("select UPDATE_BY from IGRS_REG_TXN_DETLS where ADJUDICATION_FLAG='R' and REG_TXN_ID=? and UPDATE_BY=?");
							String REG_TXN_ID = dbUtil.executeQry(new String[]{regId, userId});
							if (REG_TXN_ID != null && !REG_TXN_ID.equalsIgnoreCase("")) {
								ret = true;
							} else {
								ret = false;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			ret = false;
			e.printStackTrace();
		} finally {

			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("closing connection error on slot bookdao");

			}
		}

		return ret;
	}

	public boolean checkEstamp(String reg_txn_num) {
		boolean ret = true;

		boolean flag = true;

		boolean insertFlag = true;
		DBUtility dbUtil = null;
		String sql = null;
		try {

			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createStatement();
			String getEstamp = CommonSQL.ESTAMP_Check;
			dbUtil.createPreparedStatement(getEstamp);
			String temp[] = new String[1];
			temp[0] = reg_txn_num;

			String Estamp = dbUtil.executeQry(temp);
			int countEstamp = Integer.parseInt(Estamp);
			if (countEstamp > 1) {

				dbUtil.createStatement();
				String getTransaction = CommonSQL.GetDuplicateEstamp;
				dbUtil.createPreparedStatement(getTransaction);

				String tempNew[] = new String[1];
				tempNew[0] = reg_txn_num;

				ArrayList EstampDetails = dbUtil.executeQuery(tempNew);
				// System.out.println(EstampDetails.get(0));
				// System.out.println(EstampDetails.get(1));

				if (EstampDetails != null) {
					for (int i = 1; i < EstampDetails.size(); i++) {
						ArrayList lst = (ArrayList) EstampDetails.get(i);

						String transactionNo = lst.get(0).toString();

						String transactnDtls = CommonSQL.getTransactionDetails;
						dbUtil.createPreparedStatement(transactnDtls);
						String detls[] = new String[2];
						detls[0] = transactionNo;
						detls[1] = reg_txn_num;
						ArrayList insertDtls = dbUtil.executeQuery(detls);

						if (insertDtls != null) {

							for (int j = 0; j < insertDtls.size(); j++) {

								ArrayList lstNew = (ArrayList) insertDtls.get(j);
								String estampTxn = lstNew.get(0).toString();

								// String estampTxnId =
								// lstNew.get(0).toString();
								String insertDetails = CommonSQL.INSERT_ESTAMP_LOG_MAP;
								String logDetls[] = new String[3];
								logDetls[0] = transactionNo;
								logDetls[1] = estampTxn;
								logDetls[2] = reg_txn_num;
								dbUtil.createPreparedStatement(insertDetails);
								insertFlag = dbUtil.executeUpdate(logDetls);

								if (insertFlag) {
									dbUtil.commit();
									sql = CommonSQL.DELETE_DETAILS;
									dbUtil.createPreparedStatement(sql);
									ret = dbUtil.executeUpdate(new String[]{transactionNo, reg_txn_num});

								} else {
									dbUtil.rollback();
									throw new Exception();
								}
								if (ret) {
									dbUtil.commit();
								} else {
									dbUtil.rollback();
									throw new Exception();
								}
							}
						}
					}
				}

				dbUtil = new DBUtility();
				dbUtil.setAutoCommit(false);

				dbUtil.setAutoCommit(false);
				dbUtil.createStatement();
				String diffTransaction = CommonSQL.ESTAMP_Check_again;
				dbUtil.createPreparedStatement(diffTransaction);

				String tempNewData[] = new String[1];
				tempNewData[0] = reg_txn_num;
				String EstampQuery = dbUtil.executeQry(tempNewData);
				int count = Integer.parseInt(EstampQuery);
				if (count > 1) {

					dbUtil.createStatement();
					String newTransaction = CommonSQL.Transaction;
					dbUtil.createPreparedStatement(newTransaction);

					ArrayList EstampDetailsNew = dbUtil.executeQuery(tempNewData);

					if (EstampDetailsNew != null) {
						for (int i = 1; i < EstampDetailsNew.size(); i++) {
							ArrayList lst = (ArrayList) EstampDetailsNew.get(i);

							String transactionNo = lst.get(0).toString();

							String transactnDtls = CommonSQL.getTransactionDetails;
							dbUtil.createPreparedStatement(transactnDtls);
							String detls[] = new String[2];
							detls[0] = transactionNo;
							detls[1] = reg_txn_num;
							ArrayList insertDtls = dbUtil.executeQuery(detls);

							if (insertDtls != null) {

								for (int j = 0; j < insertDtls.size(); j++) {

									ArrayList lstNew = (ArrayList) insertDtls.get(j);
									String estampTxn = lstNew.get(0).toString();
									String estampPath = lstNew.get(1).toString();

									String transactnDtlsNew = CommonSQL.getTransactionEstampDetls;
									dbUtil.createPreparedStatement(transactnDtlsNew);
									String detlsNew[] = new String[2];
									detlsNew[0] = estampTxn;
									detlsNew[1] = reg_txn_num;
									ArrayList insertDtlsDetlsLog = dbUtil.executeQuery(detlsNew);
									// String estampTxnId =
									// lstNew.get(0).toString();
									insertFlag = false;
									if (insertDtlsDetlsLog != null && !insertDtlsDetlsLog.isEmpty()) {
										for (int k = 0; k < insertDtlsDetlsLog.size(); k++) {
											ArrayList lstNew2 = (ArrayList) insertDtlsDetlsLog.get(k);
											String indexEstampId = lstNew2.get(0).toString();
											String indexEstampCode = lstNew2.get(1).toString();
											String indexEstampAmount = lstNew2.get(2).toString();

											String insertDetails = CommonSQL.INSERT_ESTAMP_DETLS_LOG;
											String logDetls[] = new String[6];
											logDetls[0] = transactionNo;
											logDetls[1] = indexEstampId;
											logDetls[2] = indexEstampCode;
											logDetls[3] = reg_txn_num;
											logDetls[4] = indexEstampAmount;
											logDetls[5] = estampPath;
											dbUtil.createPreparedStatement(insertDetails);
											insertFlag = dbUtil.executeUpdate(logDetls);

											if (insertFlag) {

												dbUtil.commit();
												sql = CommonSQL.DELETE_DETAILS;
												dbUtil.createPreparedStatement(sql);

												ret = dbUtil.executeUpdate(new String[]{transactionNo, reg_txn_num});

												if (ret) {
													dbUtil.commit();
													sql = CommonSQL.DELETE_DETAILS_estamp;
													dbUtil.createPreparedStatement(sql);

													ret = dbUtil.executeUpdate(new String[]{indexEstampId, reg_txn_num, indexEstampCode});

												}

												if (ret) {
													dbUtil.commit();
												} else {
													dbUtil.rollback();
													throw new Exception();
												}

											} else {
												dbUtil.rollback();
												throw new Exception();
											}
										}

									} else {

										String insertDetails = CommonSQL.INSERT_ESTAMP_LOG_MAP;
										String logDetls[] = new String[3];
										logDetls[0] = transactionNo;
										logDetls[1] = estampTxn;
										logDetls[2] = reg_txn_num;
										dbUtil.createPreparedStatement(insertDetails);
										insertFlag = dbUtil.executeUpdate(logDetls);

										if (insertFlag) {
											dbUtil.commit();
											sql = CommonSQL.DELETE_DETAILS;
											dbUtil.createPreparedStatement(sql);

											ret = dbUtil.executeUpdate(new String[]{transactionNo, reg_txn_num});

										}

										else {
											dbUtil.rollback();
											throw new Exception();
										}

										if (ret) {
											dbUtil.commit();
										} else {
											dbUtil.rollback();
											throw new Exception();
										}

									}

								}

								if (ret) {
									dbUtil.commit();
								} else {
									dbUtil.rollback();
									throw new Exception();
								}

							}

						}

					}

				}

			}

		} catch (Exception e) {
			ret = false;
			e.printStackTrace();
		} finally {

			try {

				dbUtil.closeConnection();

			} catch (Exception e) {
				logger.debug("closing connection error on slot bookdao");

			}
		}

		return ret;
	}

	public String fetchSlotParams(String regTxnIDVal) {
		String slotParamValues = null;
		String slotParamValues1 = null;
		String slotParamFinalVal = null;
		String minDutyValues = null;
		String minDutyValues1 = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			// fetch minimum duty amount value
			String pm[] = new String[2];
			pm[0] = "min_duty_amount";
			pm[1] = "A";
			String query = "select attribute_value from igrs_config_param_list where attribute_name = ? and attribute_status = ? ";
			dbUtil.createPreparedStatement(query);
			slotParamValues = dbUtil.executeQry(pm);

			// fetch slot percentage value
			String pm1[] = new String[2];
			pm1[0] = "slot_percentage";
			pm1[1] = "A";
			String query1 = "select attribute_value from igrs_config_param_list where attribute_name = ? and attribute_status = ? ";
			dbUtil.createPreparedStatement(query1);
			slotParamValues1 = dbUtil.executeQry(pm1);

			// fetch duty amount value after exemption from table
			String regTxnID[] = new String[1];
			regTxnID[0] = regTxnIDVal;
			String query2 = "SELECT TOTAL_AFTER_EXEMP FROM IGRS_REG_INIT_STAMP_DUTY_DETLS WHERE REG_TXN_ID = ? ";
			dbUtil.createPreparedStatement(query2);
			minDutyValues = dbUtil.executeQry(regTxnID);

			slotParamFinalVal = slotParamValues + ";" + slotParamValues1 + ";" + minDutyValues;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("closing connection fetchSlotParams fun");

			}
		}

		return slotParamFinalVal;

	}
	public ArrayList<String> getSlotDetail(String regTxnId) throws Exception {
		ArrayList<String> response = new ArrayList<String>();
		DBUtility dbUtil = null;
		try {
			if(regTxnId.startsWith("0")){
				regTxnId=regTxnId.substring(1, (regTxnId.length()));
			}
			String SQL = CommonSQL.SLOT_BOOKED_DETAIL;
			String[] param = {regTxnId};
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			response = dbUtil.executeQuerySearch(regTxnId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return response;
	}

	public String getOfficeType(String officeId) throws Exception {
		String officeType = "";
		// officeType=dao.getOfficeType(officeId);
		String sql = CommonSQL.CHECK_OFFICE_TYPE_ID;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String[] param = {officeId};
			dbUtil.createPreparedStatement(sql);
			officeType = dbUtil.executeQry(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return officeType;
	}
	public ArrayList getSlotReschedulingReasonList(String officeType) throws Exception {
		ArrayList list = null;
		String sql = CommonSQL.GET_SLOT_RESCHEDULE_REASON_LIST;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String[] param = {officeType};
			list = new ArrayList();
			list = dbUtil.executeQuery(param);
			// list=(ArrayList) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return list;
	}
	public String updateSlotTime(SlotBookingUrgentForm sForm, String userId, String officeType, String loggedInOffice) throws Exception {
		String responseCode = "-1";

		String sql1 = CommonSQL.GET_APPLICATION_STATUS;
		String[] param1 = {sForm.getRegTxnId()};
		responseCode = getDetails(sql1, param1, "updateSlotTime-GET_APPLICATION_STATUS");
		DBUtility dbUtil = null;

		if (!"1".equals(officeType)) {
			sql1 = CommonSQL.CHECK_OFFICE_STATUS;
			// dbUtil = new DBUtility();
			String arr[] = {loggedInOffice, sForm.getBookedDistrictId()};
			String count = getDetails(sql1, arr, "updateSlotTime-CHECK_OFFICE_STATUS");
			if (null == count) {
				count = "0";
				return "-5";
			} else if ("".equals(count)) {
				count = "0";
				return "-5";
			} else if ("0".equals(count)) {
				count = "0";
				return "-5";
			} else {
				count = "1";
			}

		}
		if ("10".equals(responseCode)) {
			sql1 = CommonSQL.STATUS_SLOT_RESCHEDULE;
			String param[] = {sForm.getRegTxnId()};
			String count = getDetails(sql1, param, "updateSlotTime-STATUS_SLOT_RESCHEDULE");

			if (null == count) {
				count = "0";
			} else if ("".equals(count)) {
				count = "0";
			} else if ("1".equals(count)) {
				count = "1";
				return "-2";
			}

			if ("0".equals(count)) {
				responseCode = processSlotReschedule(sForm, userId);
			}
		} else {
			return "-3";
		}
		return responseCode;
	}
	public String processSlotReschedule(SlotBookingUrgentForm sForm, String userId) throws Exception {
		DBUtility dbUtil = null;
		String responseCode = "-1";
		String sql1 = CommonSQL.UPDATE_SLOT_DETAIL;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sql1);
			String[] param = {sForm.getToBeBookedDate(), sForm.getRegTxnId()};
			boolean updateFlag = dbUtil.executeUpdate(param);
			if (updateFlag) {
				sql1 = CommonSQL.UPDATE_SLOT_BLOCK_DETAIL;
				String[] param1 = {sForm.getRegTxnId()};
				dbUtil.createPreparedStatement(sql1);
				updateFlag = dbUtil.executeUpdate(param1);
			}
			if (updateFlag) {
				// dbUtil.commit();
				sql1 = CommonSQL.INSERT_SLOT_RESCHEDULE_DETAIL;
				String[] param1 = new String[7];
				param1[0] = sForm.getRegTxnId();
				param1[1] = sForm.getToBeBookedDate();
				param1[2] = userId;
				param1[3] = sForm.getReasonId();
				param1[4] = sForm.getRegTxnId();
				param1[5] = sForm.getRegTxnId();
				param1[6] = sForm.getSlotBookTxnId();
				dbUtil.createPreparedStatement(sql1);
				updateFlag = dbUtil.executeUpdate(param1);
				if (updateFlag) {
					// dbUtil.commit();
					String sql2 = CommonSQL.GET_PAYMENT_REQUIRED;
					String[] arr = {sForm.getReasonId()};
					dbUtil.createPreparedStatement(sql2);
					String pReq = dbUtil.executeQry(arr);
					if (updateFlag) {
						// dbUtil.commit();
						if ("Y".equals(pReq)) {
							sql2 = CommonSQL.UPDATE_PAYMENT_RECORD;
							String[] arr1 = {sForm.getRegTxnId()};
							dbUtil.createPreparedStatement(sql2);
							updateFlag = dbUtil.executeUpdate(arr1);
						}
						if (updateFlag) {
							dbUtil.commit();
							sql2 = CommonSQL.UPDATE_PAYMENT_STATUS;
							dbUtil.createPreparedStatement(sql2);
							String[] arr2 = {sForm.getReasonId(), userId, sForm.getRegTxnId()};
							updateFlag = dbUtil.executeUpdate(arr2);

						} else {
							dbUtil.rollback();
							return "-4";
						}
					}
				} else {
					dbUtil.rollback();
					return "-4";
				}
			} else {
				dbUtil.rollback();
				return "0";
			}
			if (updateFlag) {
				dbUtil.commit();
				responseCode = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return responseCode;
	}
	public ArrayList getDistrictDetail(String officeId) throws Exception {
		ArrayList result = new ArrayList();
		String sql = CommonSQL.GET_DISTRICT_LIST;
		if ("OFC214".equals(officeId)) {
			sql = sql +"  order by district_name";
			String[] arr = {};
			result = getDetailsArray(sql, arr, "getDistrictDetail");
		} else {
			sql = sql + CommonSQL.ADD_OFFICE_CHECK;
			String[] arr = {officeId};
			result = getDetailsArray(sql, arr, "getDistrictDetail--WITH OFFICE ID");
		}
		return result;
	}
	public ArrayList getOfficeDetail(String officeType, String districtId, String loggedInOffice) throws Exception {
		ArrayList result = new ArrayList();
		String sql = CommonSQL.GET_OFFICE_DETAIL;
		if("OFC214".equals(loggedInOffice)){
			sql=CommonSQL.GET_OFFICE_DETAIL_ADMIN;
			String[] arr = {officeType};
			result = getDetailsArray(sql, arr, "getOfficeDetail");
		}else{
		String[] arr = {officeType, districtId};
		result = getDetailsArray(sql, arr, "getOfficeDetail");
		}
		return result;
	}

	public String updateOfficeSlotData(ArrayList<String> dataUpdate) throws Exception {
		String responseCode = "1";
		int n = dataUpdate.size();
		String districtId = dataUpdate.get(0);
		String officeId = dataUpdate.get(1);
		String time = (null == dataUpdate.get(2)) ? "NA" : ("".equals(dataUpdate.get(2)) ? "NA" : dataUpdate.get(2));
		String officeStatus = (null == dataUpdate.get(3)) ? "NA" : ("".equals(dataUpdate.get(3)) ? "NA" : dataUpdate.get(3));
		int sizeOfParamArray = 0;
		ArrayList<String> param = new ArrayList<String>();
		String sql = CommonSQL.UPDATE_OFFICE_SLOT_DATA_1;
		if ("NA".equals(time)) {
			if (!("NA".equals(officeStatus))) {
				sql = sql + CommonSQL.UPDATE_OFFICE_SLOT_DATA_4;
				sql = sql + CommonSQL.UPDATE_OFFICE_SLOT_DATA_3;
				param.add(officeStatus);
				param.add(officeStatus);
			}

		} else {
			sql = sql + CommonSQL.UPDATE_OFFICE_SLOT_DATA_2;
			param.add(time);
			if (!("NA".equals(officeStatus))) {
				sql = sql + CommonSQL.UPDATE_OFFICE_SLOT_DATA_4;
				sql = sql + CommonSQL.UPDATE_OFFICE_SLOT_DATA_3;
				param.add(officeStatus);
				param.add(officeStatus);
			}
		}
		if ("ALL".equals(districtId)) {
			sql = sql + CommonSQL.UPDATE_OFFICE_SLOT_DATA_6;
		} else {
			if ("ALL".equals(officeId)) {
				sql = sql + CommonSQL.UPDATE_OFFICE_SLOT_DATA_7;
				param.add(districtId);
			} else {
				sql = sql + CommonSQL.UPDATE_OFFICE_SLOT_DATA_5;
				param.add(officeId);
			}
		}

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String arr[] = param.toArray(new String[param.size()]);
			boolean chk = dbUtil.executeUpdate(arr);
			if (!chk) {
				responseCode = "-100";
			}
		} catch (Exception e) {
			responseCode = "-100";
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return responseCode;
	}
	public String updateOfficeSlotDataAllDist(ArrayList<String> dataUpdate) throws Exception {
		String responseCode = "";
		String sql = "";
		return responseCode;
	}
	public String getPaymentStatus(String regTxnId) throws Exception {
		String returnVal = "";
		String sql1 = CommonSQL.GET_PAYMENT_DETAIL;
		DBUtility dbUtil = null;
		ArrayList tmp = new ArrayList();
		String[] arr = {regTxnId};
		returnVal = getDetails(sql1, arr, "getPaymentStatus");
		if ("".equals(returnVal)) {
			returnVal = "0";
		}
		return returnVal;
	}

	public String getPaymentDetail(String regTxnId, String payableAmount) throws Exception {
		String retVal = "";
		String refId = "";
		String sql1 = CommonSQL.GET_PAYMENT_STATUS;
		String[] arr = {regTxnId};
		refId = getDetails(sql1, arr, "getPaymentDetail-1st sql");
		if (!("".equals(refId))) {
			String sql = CommonSQL.GET_PAYMENT_DETAIL;
			String[] arr1 = {regTxnId};
			retVal = getDetails(sql, arr1, "getPaymentDetail-2nd sql");

		} else {
			retVal = refId;
		}
		return retVal;
	}

	public boolean insertSltRefDtl(String refId, String amount, String regTxnId, String filePath, String reasonId, String userId, String slotDate) throws Exception {
		boolean bool = false;
		String query = CommonSQL.GET_INITIATED_PAYMENT_COUNT;
		String param[] = {regTxnId};
		String count = getDetails(query, param, "insertSltRefDtl--GET_INITIATED_PAYMENT_COUNT");
		if (!("0".equals(count))) {
			bool = true;
		} else {
			String sql = CommonSQL.INSERT_SLOT_RESCHEDULE_DETAIL_PAYMENT;

			String arr[] = new String[8];
			arr[0] = regTxnId;
			arr[1] = refId;
			arr[2] = amount;
			arr[3] = reasonId;
			arr[4] = filePath;
			arr[5] = reasonId;
			arr[6] = userId;
			arr[7] = slotDate;
			bool = insertRecord(sql, arr, "insertSltRefDtl--GET_INITIATED_PAYMENT_COUNT");
		}
		return bool;
	}

	public boolean checkholiday(String date) throws Exception {
		boolean result = true;
		String sql = CommonSQL.CHK_HOLIDAY;
		String[] param = {date};
		String count = getDetails(sql, param, "checkholiday -- Checking date for holiday " + date);
		if ("".equals(count) || "0".equals(count)) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	public ArrayList getReport(String districtId, String toDate, String frmDate) throws Exception {
		ArrayList report = new ArrayList();
		String sql = CommonSQL.SLOT_RESCHEDULE_REPORT;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String[] arr = {districtId, frmDate+" 00:00:00", toDate+" 23:59:59"};
			report = getDetailsArray(sql, arr, "getReport--For Slot booking tatkal");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return report;
	}

	public String getIfPresent(String regTxnId) throws Exception {
		String response = "1";
		String sql = CommonSQL.CHECK_VALID_APP;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String[] arr = {regTxnId};
			response = dbUtil.executeQry(arr);
		} catch (SQLException e) {
			response = "0";
			e.printStackTrace();
		} catch (Exception e) {
			response = "0";
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return response;
	}
	public String getWillDeedCheck(String regTxnId) throws Exception {
		String response = "1";
		String sql = CommonSQL.CHECK_IF_WILL;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String[] arr = {regTxnId};
			response = dbUtil.executeQry(arr);
		} catch (SQLException e) {
			response = "0";
			e.printStackTrace();
		} catch (Exception e) {
			response = "0";
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return response;
	}
	public String getIfRescheduled(String regTxnId) throws Exception {
		String response = "1";
		String sql = CommonSQL.CHECK_IF_ALREADY_RESCHEDULED;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String[] arr = {regTxnId};
			response = dbUtil.executeQry(arr);
		} catch (SQLException e) {
			response = "0";
			e.printStackTrace();
		} catch (Exception e) {
			response = "0";
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return response;
	}
	
	public ArrayList getPaymentRecord(String refId, String funcID) throws Exception {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String query = CommonSQL.PAYMENT_RECORD;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			list = dbUtil.executeQuery(new String[]{refId.trim(), funcID.trim()});
			if (list.size() <= 0) {
				list = dbUtil.executeQuery(new String[]{"0" + refId.trim(), funcID.trim()});
			}
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getPaymentRecord(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}
	
	public ArrayList getEarlierStatus(String regTxnId) throws Exception {
		ArrayList tmp = new ArrayList();
		String sql = CommonSQL.GET_UPLOAD_FILE_PATH;
		String[] arr = {regTxnId};
		tmp = getDetailsArray(sql, arr, "getEarlierStatus");
		return tmp;
	}

	public String getDetails(String sql, String[] arr, String methodName) throws Exception {
		String tmp = "";
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			tmp = dbUtil.executeQry(arr);
		} catch (Exception e) {
			logger.debug("Some error occured in method " + methodName);
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return tmp;
	}
	public boolean insertRecord(String sql, String[] arr, String methodName) throws Exception {
		boolean bool = false;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			bool = dbUtil.executeUpdate(arr);
		} catch (Exception e) {
			logger.debug("Some error occured in insertion for method " + methodName);
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return bool;
	}
	public ArrayList getDetailsArray(String sql, String[] arr, String methodName) throws Exception {
		ArrayList result = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if (arr.length == 0) {
				dbUtil.createStatement();
				result = dbUtil.executeQuery(sql);
			} else {
				dbUtil.createPreparedStatement(sql);
				result = dbUtil.executeQuery(arr);
			}
		} catch (Exception e) {
			logger.debug("Some error occured in getDetailsArray for method " + methodName);
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return result;
	}
}
