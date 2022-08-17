package com.wipro.igrs.slotbooking.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages; //import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.slotbooking.bd.SlotBookBD;
import com.wipro.igrs.slotbooking.dto.SlotBookDTO;
import com.wipro.igrs.slotbooking.dto.SroSlotSheduleDTO;
import com.wipro.igrs.slotbooking.form.SlotBookActionForm;
import com.wipro.igrs.slotbooking.form.SroSlotSheduleForm;

/**
 * =========================================================================== File : SlotBookAction.java Description : Represents the Business Class
 * 
 * Author : Hari Krishna GV Created Date : Nov 28, 2007
 * 
 * Modified : September,2012 Last Modified : 25.6.2013
 * 
 * ===========================================================================
 */

public class SlotBookAction extends BaseAction {
	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	private final Object lock = new Object();
	@SuppressWarnings("null")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		String lang = (String) session.getAttribute("languageLocale");

		Logger logger = (Logger) Logger.getLogger(SlotBookAction.class);
		String forwardPage = "userSlotbook";
		ActionMessages messages = new ActionMessages();

		String roleId = (String) session.getAttribute("role");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");

		SlotBookActionForm sbform = (SlotBookActionForm) form;
		SlotBookBD bd = new SlotBookBD();
		SlotBookDTO bookdto = sbform.getBookdto();
		if (bookdto.getRegId() != null && !bookdto.getRegId().isEmpty()) {
			String input = bookdto.getRegId();
			String output = input.trim();
			for (; output.length() > 1 && output.charAt(0) == '0'; output = output.substring(1));
			bookdto.setRegId(output);
			String slotFlag = bd.instrumentId(bookdto.getRegId());
			bookdto.setSlotFlag(slotFlag);
		}
		Date date = new Date();
		SimpleDateFormat yearformat = new SimpleDateFormat("MM/dd/yyyy");
		String yfmt = yearformat.format(date);
		bookdto.setSlotDateSys(yfmt);
		String label = "";
		String fwdName = (String) bookdto.getReSchedule();

		ArrayList sronamelist = new ArrayList();
		ArrayList slottimelist = new ArrayList();
		ArrayList userList = new ArrayList();
		ArrayList slotBkViewList = new ArrayList();
		ArrayList sroList = new ArrayList();
		ArrayList getDistrictList = new ArrayList();
		bookdto.setLanguage(lang);
		bookdto.setErrorMsg("");

		bookdto.setUserId(userId);

		if (request.getParameter("flag") != null) {
			if (request.getParameter("flag").toString().equalsIgnoreCase("fromInitiation")) {
				label = (String) request.getAttribute("label");
			}
		}

		if (request.getParameter("label") != null) {
			if (!(request.getParameter("label")).equalsIgnoreCase("")) {
				label = (String) request.getParameter("label");
				if (label.equalsIgnoreCase("userSlotbook")) {
					bookdto.setRegId("");
					bookdto.setBookList(new ArrayList());
					bookdto.setTehsilList(new ArrayList());
					bookdto.setOfficeList(new ArrayList());
					session.removeAttribute("sronamelist");
					session.removeAttribute("slottimelist");
					bookdto.setAvailSro("");
					bookdto.setDistId("");
					bookdto.setCheck("");
					sbform.setBookdto(new SlotBookDTO());

					forwardPage = "userSlotbook";
				}
			}
		}

		try {

			/**
			 * added for direct slot booking from registration initiation module
			 */
			if (request.getAttribute("from") != null) {
				if (request.getAttribute("from").toString().equalsIgnoreCase("regInit")) {
					sbform.getBookdto().setRegId(request.getAttribute("regInitId").toString());
				}
			}
			/** End */

			if (request.getParameter("param") != null && request.getParameter("param").equalsIgnoreCase("userSlotbook")) {
				// check user who initiated the
				if (bd.checkUser(bookdto.getRegId(), userId)) {

					int j = bd.chkRegTable(bookdto);
					slotBkViewList = bd.check(bookdto);
					if (j > 0) {
						messages.add("MSG", new ActionMessage("no.records.found"));
						saveMessages(request, messages);

						bookdto.setRegId("");
						forwardPage = "userSlotbook";

					}

					else if (slotBkViewList.size() != 0 && !bookdto.getReSchedule().equalsIgnoreCase("reSchedule")) {
						SlotBookDTO sbdto = (SlotBookDTO) slotBkViewList.get(0);
						sbform.getBookdto().setSlotBkViewList(slotBkViewList);
						request.setAttribute("SltBkViewLst", slotBkViewList);
						forwardPage = "failureSltAlrdyBkd";
						return mapping.findForward(forwardPage);
					} else {
						if (bookdto.getReSchedule().equalsIgnoreCase("reSchedule")) {
							getDistrictList = bd.getDistrictFromEstamp(bookdto, lang);
							bookdto.setBookList(getDistrictList);
							sbform.setBookdto(bookdto);
							forwardPage = "success";
						} else {
							if (bd.checkEstamp(bookdto.getRegId())) {
								getDistrictList = bd.getDistrictFromEstamp(bookdto, lang);
								bookdto.setBookList(getDistrictList);
								sbform.setBookdto(bookdto);
								forwardPage = "success";
							}
						}
					}

				} else {
					forwardPage = "failure1";
				}
			}

			if (request.getParameter("parameter") != null && request.getParameter("parameter").equalsIgnoreCase("tehsil"))

			{
				String distId = bookdto.getDistId();
				ArrayList getTehsilList = bd.getTehsilList(bookdto, distId, lang);
				bookdto.setTehsilList(getTehsilList);
				bookdto.setCheck("N");
				sbform.setBookdto(bookdto);
				forwardPage = "success";
			}

			if (request.getParameter("parameter") != null && request.getParameter("parameter").equalsIgnoreCase("officeDetails"))

			{
				bookdto.setCheck("N");
				forwardPage = "success";
			}

			if (request.getParameter("parameter") != null && request.getParameter("parameter").equalsIgnoreCase("office"))

			{
				String tehsilId = bookdto.getTehsilId();
				String regTxnId = bookdto.getRegId();
				ArrayList getOfficeList = bd.getofficeMappedToTehsil(regTxnId, tehsilId, lang);
				bookdto.setOfficeList(getOfficeList);
				bookdto.setCheck("N");
				sbform.setBookdto(bookdto);
				forwardPage = "success";
			}

			if (((String) sbform.getSlotdate()) != null && !((String) sbform.getSlotdate()).equals("")) {
				SroSlotSheduleDTO srodto = new SroSlotSheduleDTO();
				srodto.setSelectDate(sbform.getSlotdate());
				int i = bd.checkweekend(srodto);
				if (i > 0) {
					logger.debug("inside if");
					forwardPage = "failureHoliday";
					return mapping.findForward(forwardPage);
				}
			}

			if (request.getParameter("param") != null && request.getParameter("param").equalsIgnoreCase("showslot"))

			{
				if (((String) sbform.getSlotdate()) != null && !((String) sbform.getSlotdate()).equals("")) {
					if (!((bookdto.getDistId()).equalsIgnoreCase("")) && (bookdto.getDistId()) != null) {
						slottimelist = bd.getSroAvailableSlot1(bookdto.getOfficeID(), sbform.getSlotdate());

						if ((slottimelist != null && ((String) sbform.getSlotdate()) != null) && (slottimelist != null && ((String) sbform.getSlotdate()) != "")) {
							bookdto.setSlotdate(sbform.getSlotdate());
							sronamelist = bd.getAvailableSro(slottimelist, bookdto);

						}
						// START | To exclude the slot time list if sro is not available in that slot | Santosh

						System.out.println("Actual Value of slottimelist count :::::" + slottimelist.size());
						System.out.println("Actual Value of sronamelist count :::::" + sronamelist.size());

						ArrayList<SlotBookDTO> slottimelist1 = new ArrayList<SlotBookDTO>();
						ArrayList<SlotBookDTO> sronamelist1 = new ArrayList<SlotBookDTO>();
						try {
							if (slottimelist != null && slottimelist.size() > 0) {
								for (long i = 0; i < slottimelist.size(); i++) {
									boolean emptySlotFlag = false;
									SlotBookDTO book = (SlotBookDTO) slottimelist.get((int) i);
									String slotTimeId = book.getTimeSlotId();
									String slotTime = book.getTimesSlot();
									if (sronamelist1 != null && sronamelist.size() > 0) {
										for (long j = 0; j < sronamelist.size(); j++) {
											SlotBookDTO book1 = (SlotBookDTO) sronamelist.get((int) j);
											String SroSlotTimeId = book1.getTimeSlotId();
											String SroSlotTime = book1.getTimesSlot();
											String SroName = book1.getAvailSro();
											if (SroSlotTimeId.equalsIgnoreCase(slotTimeId) && SroSlotTime.equalsIgnoreCase(slotTime) && (SroName != null && SroName != "")) {
												emptySlotFlag = true;
												sronamelist1.add(book1);
											}
										}
										if (emptySlotFlag == true) {
											slottimelist1.add(book);
										}
									}
								}
							}
							logger.debug("Actual Value of slottimelist1 count :::::" + slottimelist1.size());
							logger.debug("Actual Value of sronamelist1 count :::::" + sronamelist1.size());

						} catch (Exception e) {
							logger.debug("SlotBookAction :: exception occurred in the block while excluding the slot time list if sro is not available in that slot " + e.getMessage());
						}

						// END | To exclude the slot time list if sro is not available in that slot | Santosh

						// START | to display only 25 percent of total slot to user if minimum duty amount is less than 500 | santosh

						String slotBookParamsVal = bd.fetchSlotParams(bookdto.getRegId());
						String slotBookParamsVal1[] = slotBookParamsVal.split(";");
						String minDutyAmount = null;
						String slotPercentage = null;
						String minDutyAfterExemp = null;
						long minDutyVal = 0;
						long slotPerVal = 0;
						long dutyVal1 = 0;
						double minSroVal = 0;
						double minSroVal1 = 0;
						// boolean slotFalg = false;
						if (slotBookParamsVal1.length > 0) {
							if (slotBookParamsVal1[0] != null && slotBookParamsVal1[0] != "") {
								minDutyAmount = slotBookParamsVal1[0];
								minDutyVal = Long.parseLong(minDutyAmount);
							}

							if (slotBookParamsVal1[1] != null && slotBookParamsVal1[1] != "") {
								slotPercentage = slotBookParamsVal1[1];
								slotPerVal = Long.parseLong(slotPercentage);
							}

							if (slotBookParamsVal1[2] != null && slotBookParamsVal1[2] != "") {
								minDutyAfterExemp = slotBookParamsVal1[2];
								dutyVal1 = Long.parseLong(minDutyAfterExemp);

							}
						}
						// long dutyVal = 400;
						// long totalSlotCount = 0 ;
						long slottimelistSize = slottimelist1.size();
						long sronamelistSize = sronamelist1.size();
						try {
							minSroVal = ((double) slotPerVal / 100) * sronamelistSize;
							minSroVal1 = Math.round(minSroVal);
						} catch (ArithmeticException e) {
							logger.debug("Exception occurred while calculating 25 percen slot" + e.getMessage());
						}
						ArrayList<SlotBookDTO> slotTimeLst = new ArrayList<SlotBookDTO>();
						ArrayList<SlotBookDTO> SroNameLst = new ArrayList<SlotBookDTO>();
						// Object lock = new Object();

						if (dutyVal1 < minDutyVal) {
							try {
								// synchronized (lock){
								Collections.reverse(slottimelist1);
								Collections.reverse(sronamelist1);
								if (slottimelist1 != null && slottimelistSize > 0) {
									for (long i = 0; i < slottimelistSize; i++) {

										SlotBookDTO book = (SlotBookDTO) slottimelist1.get((int) i);
										String slotTimeId = book.getTimeSlotId();
										String slotTime = book.getTimesSlot();
										if (minSroVal1 == SroNameLst.size()) {
											break;
										}
										slotTimeLst.add(book);

										if (sronamelist1 != null && sronamelistSize > 0) {
											for (long j = 0; j < sronamelistSize; j++) {
												SlotBookDTO book1 = (SlotBookDTO) sronamelist1.get((int) j);
												String SroSlotTimeId = book1.getTimeSlotId();
												String SroSlotTime = book1.getTimesSlot();
												String SroName = book1.getAvailSro();
												if (SroSlotTimeId.equalsIgnoreCase(slotTimeId) && SroSlotTime.equalsIgnoreCase(slotTime) && (SroName != null && SroName != "")) {
													SroNameLst.add(book1);
													if (minSroVal1 == SroNameLst.size()) {
														break;
													}

												}
											}
										}
									}
								}
								logger.debug("regTxnID Value :::::" + bookdto.getRegId());
								logger.debug("Minimum duty amount Value fetch from table :::::" + dutyVal1);
								logger.debug("Actual Value of total sro list count :::::" + sronamelist1.size());
								logger.debug("Actual Value of total slot list count :::::" + slottimelist1.size());
								logger.debug("Value of total sro list count :::::" + SroNameLst.size());
								logger.debug("Value of total slot list count :::::" + slotTimeLst.size());
								logger.debug("Value of total min Sro Val1  :::" + minSroVal1);

								Collections.reverse(slotTimeLst);
								Collections.reverse(SroNameLst);

								if (SroNameLst != null) {
									session.setAttribute("sronamelist", SroNameLst);
								}
								if (slotTimeLst != null) {
									session.setAttribute("slottimelist", slotTimeLst);
								}

								// }
							} catch (Exception e) {
								logger.debug("SlotBookAction :: exception occurred in the block while displaying only 25 percent of total slot " + e.getMessage());
							}
						} else {
							// START | to display only 25 percent of total slot to user if minimum duty amount is less than 500 | santosh
							if (sronamelist != null) {
								session.setAttribute("sronamelist", sronamelist1);
								// bookdto.setSroNameList(sronamelist);
							}
							if (slottimelist != null) {
								session.setAttribute("slottimelist", slottimelist1);
								// bookdto.setSroSlotList(slottimelist);
							}
						}

						if ((sronamelist1 != null && sronamelist1.size() > 0) && (slottimelist1 != null && slottimelist1.size() > 0)) {
							bookdto.setCheck("Y");
						}

						forwardPage = "success";
					}
				}
			}

			if (request.getParameter("param") != null && request.getParameter("param").equalsIgnoreCase("bookslot"))

			{
				// String paramVal = request.getParameter("param");
				synchronized (lock) {
					String values[] = bookdto.getSroTime().split("-");
					bookdto.setAvailSro(values[0]);
					bookdto.setTimeSlotId(values[1]);
					bookdto.setSlotdate(sbform.getSlotdate());
					ArrayList list = bd.userStoreData(bookdto);
					if (list != null && list.size() > 0) {
						SlotBookDTO ssdto = (SlotBookDTO) list.get(0);
						bookdto.setRefId(ssdto.getSlotRefId());
						bookdto.setTimesSlot(bd.getslottime(bookdto.getTimeSlotId()));
						sbform.setBookdto(bookdto);
					}
					String errorMsg = bookdto.getErrorMsg();
					if (errorMsg != null && errorMsg != "") {
						forwardPage = "success1";
					} else {
						forwardPage = "success";
					}
				}
			}

			if (request.getParameter("param") != null && request.getParameter("param").equalsIgnoreCase("bookslotWithoutSR"))

			{
				// String values[] = bookdto.getSroTime().split("-");
				bookdto.setAvailSro("SRO");
				bookdto.setTimeSlotId("No Slot Id");
				bookdto.setSlotdate(sbform.getSlotdate());
				ArrayList list = bd.userStoreData(bookdto);
				if (list != null && list.size() > 0) {
					SlotBookDTO ssdto = (SlotBookDTO) list.get(0);
					bookdto.setRefId(ssdto.getSlotRefId());
					bookdto.setTimesSlot("After 4:30 pm");
					sbform.setBookdto(bookdto);
				}

				forwardPage = "success";
			}

			if (request.getParameter("param") != null && request.getParameter("param").equalsIgnoreCase("reset"))

			{
				getDistrictList = bd.getDistrictFromEstamp(bookdto, lang);
				bookdto.setBookList(getDistrictList);
				bookdto.setAvailSro("");
				bookdto.setDistId("");
				bookdto.setTehsilList(new ArrayList());
				bookdto.setOfficeList(new ArrayList());
				session.removeAttribute("sronamelist");
				session.removeAttribute("slottimelist");
				sbform.setSlotdate("");
				bookdto.setCheck("");
				sbform.setBookdto(bookdto);
				forwardPage = "success";
			}

			/*
			 * 
			 * String actionID = sbform.getBookdto().getActionID();
			 * 
			 * if(actionID==null || actionID=="") { actionID="submit"; } if(actionID.equalsIgnoreCase("reset")){ sbform.getBookdto().setRegId(""); sbform.getBookdto().setActionID(""); forwardPage="userSlotbook"; return mapping.findForward(forwardPage); }
			 * 
			 * 
			 * //App_No session ref id of reg init String sessionAppNo=(String)session.getAttribute("App_No");
			 * 
			 * 
			 * if(sessionAppNo!=null&& !"".equals(sessionAppNo) && !(label.equalsIgnoreCase("userSlotbook"))) {
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * //Start:====code for checking validity of application number if(((String)request.getParameter("pageName"))!=null){ if(((String)request.getParameter("pageName")).equals("regRefID")){ session.setAttribute("regId",sessionAppNo); } if(((String)request.getParameter("pageName")).equals("userReport")) { userList=bd.getUserReport(userId); sbform.getSlotdto().setUserReportList(userList);
			 * 
			 * session.setAttribute("slotbookactionform",sbform); } forwardPage="" + ""; }else{
			 * 
			 * 
			 * String distid=sbform.getBookdto().getSelectdistid(); //added on 12.10.12 @author ankita
			 * 
			 * String sroid=sbform.getBookdto().getSelectsroid();
			 * 
			 * 
			 * slotdto.setBookList(distList); if(((String)sbform.getSlotdate())!=null && !((String)sbform.getSlotdate()).equals("") ){ slotdto.setSlotdate(sbform.getSlotdate()); //Start:=====code for checking weekend setting slotdate in select date of srodto since checkweekend(SroSlotSheduleDTO srodto)has this signature * SroSlotSheduleDTO srodto=new SroSlotSheduleDTO(); srodto.setSelectDate(sbform.getSlotdate()); int i=bd.checkweekend(srodto); if(i>0) { logger.debug("inside if"); forwardPage="failureHoliday"; return mapping.findForward(forwardPage); } //End:=====code for checking weekend System.out.println( "slot date is"+sbform.getSlotdate()); } ArrayList sroList=null; String regnum=sessionAppNo; //added by ankita 15-10-2012 logger.debug("registraTion number is"+regnum);
			 * 
			 * 
			 * if(distid!=null){ if(sroid!=null && sroid.trim().length()>0 ){ logger.debug("first"+distid); logger.debug("sroid"+sroid); slotdto.setSroId(sroid); logger.debug("inside sroid check if"+sroid); slotdto.setDistId(distid); sroList=bd.getSroName(distid); slottimelist = bd.getSroAvailableSlot(sroid); if((slottimelist!=null && ((String)sbform.getSlotdate())!=null) && (slottimelist!=null && ((String)sbform.getSlotdate())!="")){ sronamelist=bd.getAvailableSro(slottimelist,slotdto); slotdto.setAvailableSro(sronamelist); slotdto.setSroSlotList(slottimelist); slotdto.setDistId(sbform.getBookdto().getDistId()); slotdto.setDistName(sbform.getBookdto().getDistName()); slotdto.setSroId(sbform.getBookdto().getSroId()); slotdto.setSroName(sbform.getBookdto().getSroName()); logger.debug("sbform.getBookdto().getSroName()"+sbform.getBookdto().getSroName()); if(sbform.getBookdto().getSelctsroname()!=null){ slotdto.setAvailSro(sbform.getBookdto().getSelctsroname()); logger.debug("select sro name"+sbform.getBookdto().getSelctsroname()); } if(sbform.getBookdto().getSelctslottime()!=null){
			 * 
			 * //added by ankita 15-10-2012
			 * 
			 * logger.debug("slot time id is"+sbform.getBookdto().getSelctslottime());
			 * 
			 * 
			 * slotdto.setTimesSlot(sbform.getBookdto().getSelctslottime()); } } }else{ slotdto.setDistId(distid); sroList=bd.getSroName(distid); logger.debug("from else"+distid); } slotdto.setSroNameList(sroList); }else{ SlotBookDTO sro=new SlotBookDTO(); sroList=new ArrayList(); sro.setSroId("Select"); sro.setSroName("Select"); sroList.add(sro); slotdto.setSroNameList(sroList); }
			 * 
			 * sbform.setBookdto(slotdto);
			 * 
			 * session.setAttribute("sbform",sbform);
			 * 
			 * if(sronamelist!=null){ session.setAttribute("sronamelist",sronamelist); } if(slottimelist!=null){ session.setAttribute("slottimelist",slottimelist); } sbform.getBookdto().setRegId(regnum); forwardPage="success"; }
			 * 
			 * 
			 * if(funId!=null){ String slotAmount = bd.getSlotBookFee(funId,userId);
			 * 
			 * 
			 * sbform.getBookdto().setSlotAmount(slotAmount); }else{ sbform.getBookdto().setSlotAmount(""); }
			 * 
			 * } sbform=null;
			 */

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward("failure");
		}
		return mapping.findForward(forwardPage);
	}

}
