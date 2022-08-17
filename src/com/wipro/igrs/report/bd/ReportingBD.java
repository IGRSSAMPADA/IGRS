/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: ReportBD.java
 *
 * Description	   		: This class interacts with DbService for data 
 * 							persistance and data fetch
 *
 * Version Number  		: 0.0 
 *
 * Created Date	   		: 30 04 2008 
 *
 * Modification History	: Created
 */

package com.wipro.igrs.report.bd;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.File;

import org.apache.log4j.Logger;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.report.dao.ReportingDAO;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.report.dto.ReportingDTO;
import com.wipro.igrs.report.form.ReportingForm;
import com.wipro.igrs.report.sql.CommonSQL;
import com.wipro.igrs.report.util.PropertiesFileReader;

import com.wipro.igrs.common.IGRSCommon;

public class ReportingBD {

	private IGRSCommon common;
	private Logger logger = 
		(Logger) Logger.getLogger(ReportingBD.class);

	/**
	 * ===========================================================================
	 * Method : getSroNameDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * vengamamba p Created Date : jun 4 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ===========================================================================
	 */
	public ArrayList getSroNameDetails(ReportingForm reportForm) {
		ReportingDAO dao = new ReportingDAO();
		// ArrayList resultList=new ArrayList();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getDroNameDetails BD start");
			ArrayList resultList = dao.getSroNameDetails(reportForm);
			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
					
					ArrayList list = (ArrayList) resultList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					reportDTO.setName((String) list.get(0));
					reportDTO.setValue((String) list.get(1));
					returnList.add(reportDTO);
					//logger.debug("in bd for loop end,reportDTO.GetDroName:-"
					//		+ reportDTO.getName());
					//logger.debug("in bd for loop end reportDTO.setDroId  "
					//		+ reportDTO.getValue());
				}
			}
			logger.debug("In getDroNameDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getDroNameDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ===========================================================================
	 * Method : getdistictDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * vengamamba p Created Date : jun 4 2008
	 * 
	 * @param reportForm
	 *            description
	 *            ===========================================================================
	 */
	public ArrayList getdistictDetails(ReportingForm reportForm) {
		ReportingDAO dao = new ReportingDAO();
		// ArrayList resultList=new ArrayList();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getDroNameDetails BD start");
			ArrayList resultList = dao.getDistrictDetails(reportForm);
			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
//					logger.debug("in bd for loop start:-"
//							+ resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					reportDTO.setName((String) list.get(1));
					reportDTO.setValue((String) list.get(0));
					returnList.add(reportDTO);
					//logger.debug("in bd for loop end,reportDTO.GetDroName:-"
					//		+ reportDTO.getName());
				//	logger.debug("in bd for loop end reportDTO.setDroId  "
					//		+ reportDTO.getValue());
				}
			}
			logger.debug("In getDroNameDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getDroNameDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ===========================================================================
	 * Method : getDroNameDetails() Description : Retrieving values from
	 * database Arguments : ReportForm reportForm return type : List Author :
	 * Sreelatha M Created Date : May 27,2008
	 * 
	 * @param reportForm
	 *            description
	 *            ===========================================================================
	 */
	public ArrayList getDroNameDetails(ReportingForm reportForm) {
		ReportingDAO dao = new ReportingDAO();
		// ArrayList resultList=new ArrayList();
		ArrayList returnList = new ArrayList();
		try {
			logger.debug("In getDroNameDetails BD start");
			ArrayList resultList = dao.getDroNameDetails(reportForm);
			if (resultList != null) {
				logger.debug("in bd if block start");
				for (int i = 0; i < resultList.size(); i++) {
//					logger.debug("in bd for loop start:-"
//							+ resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					reportDTO.setName((String) list.get(0));
					reportDTO.setValue((String) list.get(1));
					returnList.add(reportDTO);
					//logger.debug("in bd for loop end,reportDTO.GetDroName:-"
					//		+ reportDTO.getName());
					//logger.debug("in bd for loop end reportDTO.setDroId  "
					//		+ reportDTO.getValue());
				}
			}
			logger.debug("In getDroNameDetails BD TryBlock end ");
		} catch (Exception err) {
			err.getStackTrace();
			logger.debug("In getDroNameDetails Exception " + err);
		}
		return returnList;
	}

	/**
	 * ===========================================================================
	 * Method : prepareRRCYearPdfFile Description : Returns pdf format file
	 * Arguments : form return type : Returns file Author : vengamamba P Created
	 * Date : jun 11, 2008
	 * ===========================================================================
	 */
	public synchronized File prepareRRCYearPdfFile(ReportingForm reportForm) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);

		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");
			fileName = pr.getValue("REPORT_LOCATION") + "RRCYear"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));

			document.open();

			Table datatable = new Table(11);

			int headerwidths[] = { 35, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);

			datatable.setPadding(3);

			Cell title = new Cell(new Phrase("RRS Cases Report", FontFactory
					.getFont(FontFactory.COURIER, 18, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(11);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 9, Font.BOLD)));
			slno.setHeader(true);
			slno.setRowspan(3);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell yearWisePending = new Cell(new Phrase(
					"Year wise Status of Cases Pending", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			yearWisePending.setHeader(true);
			yearWisePending.setColspan(10);
			yearWisePending.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(yearWisePending);

			Cell moreThanOneyr = new Cell(new Phrase("More than One Year  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			moreThanOneyr.setHeader(true);
			moreThanOneyr.setColspan(2);
			moreThanOneyr.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(moreThanOneyr);

			Cell moreThanTwoyr = new Cell(new Phrase("More than Two Years  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			moreThanTwoyr.setHeader(true);
			moreThanTwoyr.setColspan(2);
			moreThanTwoyr.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(moreThanTwoyr);

			Cell moreThanThreeyr = new Cell(new Phrase(
					"More than Three Years  ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			moreThanThreeyr.setHeader(true);
			moreThanThreeyr.setColspan(2);
			moreThanThreeyr.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(moreThanThreeyr);

			Cell moreThanFouryr = new Cell(new Phrase("More than Four Years  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			moreThanFouryr.setHeader(true);
			moreThanFouryr.setColspan(2);
			moreThanFouryr.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(moreThanFouryr);

			Cell moreThanFiveyr = new Cell(new Phrase("More than Five Years  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			moreThanFiveyr.setHeader(true);
			moreThanFiveyr.setColspan(2);
			moreThanFiveyr.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(moreThanFiveyr);

			Cell noOfCaseOne = new Cell(new Phrase("No. of Cases   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfCaseOne.setHeader(true);
			noOfCaseOne.setColspan(1);
			noOfCaseOne.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noOfCaseOne);

			Cell amtInvolvedOne = new Cell(new Phrase("Amount Involved   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			amtInvolvedOne.setHeader(true);
			amtInvolvedOne.setColspan(1);
			amtInvolvedOne.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolvedOne);

			Cell noOfCaseTwo = new Cell(new Phrase("No. of Cases   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfCaseTwo.setHeader(true);
			noOfCaseTwo.setColspan(1);
			noOfCaseTwo.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noOfCaseTwo);

			Cell amtInvolvedTwo = new Cell(new Phrase("Amount Involved   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			amtInvolvedTwo.setHeader(true);
			amtInvolvedTwo.setColspan(1);
			amtInvolvedTwo.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolvedTwo);

			Cell noOfCaseThree = new Cell(new Phrase("No. of Cases   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfCaseThree.setHeader(true);
			noOfCaseThree.setColspan(1);
			noOfCaseThree.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noOfCaseThree);

			Cell amtInvolvedThree = new Cell(new Phrase("Amount Involved   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			amtInvolvedThree.setHeader(true);
			amtInvolvedThree.setColspan(1);
			amtInvolvedThree.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolvedThree);

			Cell noOfCaseFour = new Cell(new Phrase("No. of Cases   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfCaseFour.setHeader(true);
			noOfCaseFour.setColspan(1);
			noOfCaseFour.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noOfCaseFour);

			Cell amtInvolvedFour = new Cell(new Phrase("Amount Involved   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			amtInvolvedFour.setHeader(true);
			amtInvolvedFour.setColspan(1);
			amtInvolvedFour.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolvedFour);

			Cell noOfCaseFive = new Cell(new Phrase("No. of Cases   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfCaseFive.setHeader(true);
			noOfCaseFive.setColspan(1);
			noOfCaseFive.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noOfCaseFive);

			Cell amtInvolvedFive = new Cell(new Phrase("Amount Involved   ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			amtInvolvedFive.setHeader(true);
			amtInvolvedFive.setColspan(1);
			amtInvolvedFive.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolvedFive);

			datatable.endHeaders();

			for (int i = 0; i < reportForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportingDTO reportDTO = new ReportingDTO();
				reportDTO = (ReportingDTO) reportForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values====="
						+ reportForm.getReportList());
				logger.debug("list size========"
						+ reportForm.getReportList().size());
				// datatable.setRight(Element.ALIGN_RIGHT);
				datatable.addCell(String.valueOf(reportDTO.getSno()));
				datatable.addCell(reportDTO.getNoCasesone());
				datatable.addCell(reportDTO.getAmtone());
				datatable.addCell(reportDTO.getNoCasestwo());
				datatable.addCell(reportDTO.getAmttwo());
				datatable.addCell(reportDTO.getNoCasesthree());
				datatable.addCell(reportDTO.getAmtthree());
				datatable.addCell(reportDTO.getNoCasesfour());
				datatable.addCell(reportDTO.getAmtfour());
				datatable.addCell(reportDTO.getNoCasesfive());
				datatable.addCell(reportDTO.getAmtfive());

			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	/**
	 * ===========================================================================
	 * Method : prepareRRCperiodPdfFile Description : Returns pdf format file
	 * Arguments : form return type : Returns file Author : vengamamba P Created
	 * Date : jun 10, 2008
	 * ===========================================================================
	 */
	public synchronized File prepareRRCperiodPdfFile(ReportingForm reportForm) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);

		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");
			fileName = pr.getValue("REPORT_LOCATION") + "RRCPeriod"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));

			document.open();

			Table datatable = new Table(8);

			int headerwidths[] = { 35, 50, 50, 50, 50, 50, 50, 50 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);

			datatable.setPadding(3);

			Cell title = new Cell(new Phrase("RRS Cases Report", FontFactory
					.getFont(FontFactory.COURIER, 18, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(8);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			
			
			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 9, Font.BOLD)));
			slno.setHeader(true);
			slno.setRowspan(3);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell statusDurMonth = new Cell(new Phrase(
					"Status During the Period ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			statusDurMonth.setHeader(true);
			statusDurMonth.setColspan(7);
			statusDurMonth.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(statusDurMonth);

			Cell tarDurPeriod = new Cell(new Phrase(
					"Target During the Period ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			tarDurPeriod.setHeader(true);
			tarDurPeriod.setRowspan(2);
			tarDurPeriod.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(tarDurPeriod);

			// datatable.addCell("Header 8");
			Cell noOfCaseReg = new Cell(new Phrase("No of Cases Registered ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfCaseReg.setHeader(true);
			noOfCaseReg.setRowspan(2);
			noOfCaseReg.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noOfCaseReg);

			// datatable.addCell("Header 9");
			Cell amtInvolCase = new Cell(new Phrase(
					"Amount involved in the Cases", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			amtInvolCase.setHeader(true);

			amtInvolCase.setRowspan(2);

			amtInvolCase.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolCase);

			Cell numCaseDisOff = new Cell(new Phrase(
					"No. of Cases Disposed off", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			numCaseDisOff.setHeader(true);
			numCaseDisOff.setRowspan(2);
			numCaseDisOff.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(numCaseDisOff);

			Cell amtRecovDepo = new Cell(new Phrase(
					"Amount Recover From the Deposited ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			amtRecovDepo.setHeader(true);
			amtRecovDepo.setRowspan(2);
			amtRecovDepo.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtRecovDepo);

			Cell amtInvolNoCase = new Cell(new Phrase("Pending No. of Cases ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			amtInvolNoCase.setHeader(true);
			amtInvolNoCase.setRowspan(2);
			amtInvolNoCase.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolNoCase);

			Cell amtInvolCases = new Cell(new Phrase(
					"Amount Involved in the Pending Cases ", FontFactory
							.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			amtInvolCases.setHeader(true);
			amtInvolCases.setRowspan(2);
			amtInvolCases.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolCases);

			datatable.endHeaders();

			for (int i = 0; i < reportForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportingDTO reportDTO = new ReportingDTO();
				reportDTO = (ReportingDTO) reportForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values====="
						+ reportForm.getReportList());
				logger.debug("list size========"
						+ reportForm.getReportList().size());
				// datatable.setRight(Element.ALIGN_RIGHT);
				datatable.addCell(String.valueOf(reportDTO.getSno()));
				datatable.addCell(reportDTO.getAnualTarget());
				datatable.addCell(reportDTO.getCaseRegisd());
				datatable.addCell(reportDTO.getAmt());
				datatable.addCell(reportDTO.getCasesDisp());
				datatable.addCell(reportDTO.getAmtRecover());
				datatable.addCell(reportDTO.getPendingCases());
				datatable.addCell(reportDTO.getAmtPend());

			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	/**
	 * ===========================================================================
	 * Method : prepareRRCmthPdfFile Description : Returns pdf format file
	 * Arguments : form return type : Returns file Author : vengamamba P Created
	 * Date : jun 10, 2008
	 * ===========================================================================
	 */
	public synchronized File prepareRRCmthPdfFile(ReportingForm reportForm) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);

		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");
			fileName = pr.getValue("REPORT_LOCATION") + "RRCMonmth"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));

			document.open();

			Table datatable = new Table(10);

			int headerwidths[] = { 35, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);

			datatable.setPadding(3);

			Cell title = new Cell(new Phrase("RRS Cases Report", FontFactory
					.getFont(FontFactory.COURIER, 18, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(10);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			
			
			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 9, Font.BOLD)));
			slno.setHeader(true);
			slno.setRowspan(3);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell statusDurMonth = new Cell(new Phrase(
					"Status During the month ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			statusDurMonth.setHeader(true);
			statusDurMonth.setColspan(9);
			statusDurMonth.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(statusDurMonth);

			// datatable.addCell("Header 4");
			Cell annualTarget = new Cell(new Phrase("Annual Target ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			annualTarget.setHeader(true);
			annualTarget.setRowspan(2);
			annualTarget.setBackgroundColor(new Color(176, 255, 255));
			datatable.addCell(annualTarget);

			// datatable.addCell("Header 5");
			Cell monthTagRecov = new Cell(new Phrase(
					"Monthly Target of Recovery ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			monthTagRecov.setHeader(true);
			monthTagRecov.setRowspan(2);
			monthTagRecov.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(monthTagRecov);

			// datatable.addCell("Header 6");

			Cell noOfCasePendonApr = new Cell(new Phrase(
					"No. of Cases Pending as on 1st Apr", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			noOfCasePendonApr.setHeader(true);
			noOfCasePendonApr.setRowspan(2);
			noOfCasePendonApr.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noOfCasePendonApr);
			// datatable.addCell("Header 7");

			Cell caseRegDurMonth = new Cell(new Phrase(
					"Cases Registered During the Month ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			caseRegDurMonth.setHeader(true);
			caseRegDurMonth.setRowspan(2);
			caseRegDurMonth.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(caseRegDurMonth);

			// datatable.addCell("Header 8");
			Cell amtInvolCaseReg = new Cell(new Phrase(
					"Amount Involved in the Cases Registered ", FontFactory
							.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			amtInvolCaseReg.setHeader(true);
			amtInvolCaseReg.setRowspan(2);
			amtInvolCaseReg.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolCaseReg);

			// datatable.addCell("Header 9");
			Cell noOfCaseDispOff = new Cell(new Phrase(
					"No. of Cases Disposed off ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			noOfCaseDispOff.setHeader(true);
			noOfCaseDispOff.setRowspan(2);
			noOfCaseDispOff.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noOfCaseDispOff);

			Cell amtRecfromDepoCase = new Cell(new Phrase(
					"Amount Recover From the Deposited of Cases During the",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			amtRecfromDepoCase.setHeader(true);
			amtRecfromDepoCase.setRowspan(2);
			amtRecfromDepoCase.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtRecfromDepoCase);

			// datatable.addCell("Header 5");
			Cell noCasePendEndMonth = new Cell(new Phrase(
					"No. of Cases Pending at the End of the Month", FontFactory
							.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noCasePendEndMonth.setHeader(true);
			noCasePendEndMonth.setRowspan(2);
			noCasePendEndMonth.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noCasePendEndMonth);

			// datatable.addCell("Header 6");

			Cell amtInvolPendEndMonth = new Cell(
					new Phrase(
							"Amount Involved in the Cases Pending at the End of the Month",
							FontFactory.getFont(FontFactory.COURIER, 9,
									Font.BOLD)));
			amtInvolPendEndMonth.setHeader(true);
			amtInvolPendEndMonth.setRowspan(2);
			amtInvolPendEndMonth.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtInvolPendEndMonth);
			// datatable.addCell("Header 7");

			datatable.endHeaders();
			for (int i = 0; i < reportForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportingDTO reportDTO = new ReportingDTO();
				reportDTO = (ReportingDTO) reportForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values====="
						+ reportForm.getReportList());
				logger.debug("list size========"
						+ reportForm.getReportList().size());
				// datatable.setRight(Element.ALIGN_RIGHT);
				datatable.addCell(String.valueOf(reportDTO.getSno()));
				datatable.addCell(reportDTO.getAnualTarget());
				datatable.addCell(reportDTO.getMthTarget());
				datatable.addCell(reportDTO.getPendingCases());
				datatable.addCell(reportDTO.getCaseRegisd());
				datatable.addCell(reportDTO.getAmt());
				datatable.addCell(reportDTO.getCasesDisp());
				datatable.addCell(reportDTO.getAmtRecover());
				datatable.addCell(reportDTO.getCasesPend());
				datatable.addCell(reportDTO.getAmtPend());

			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	/**
	 * ===========================================================================
	 * Method : getRRCDetails Description : Returns list of rrc report fields
	 * Arguments : form return type : Arraylist Author : vengamamba P Created
	 * Date : jun 5, 2008
	 * ===========================================================================
	 */
	public ArrayList getRRCDetails(ReportingForm reportForm) {

		ReportingDAO dao = new ReportingDAO();
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String param[] = null;
		String userValue = null;
		String query = "";

		String distid = reportForm.getDistrictID();
		String typeofuser = reportForm.getUserType();

		if (typeofuser.equalsIgnoreCase("sro")) {
			userValue = reportForm.getSroID();
		} else if (typeofuser.equalsIgnoreCase("dro")) {
			userValue = reportForm.getDroID();
		}

		String type = reportForm.getRadioSearch();
		if (type.equalsIgnoreCase("Month")) {
			String mth = reportForm.getMonth();
			if (userValue != null) {
				param = new String[20];
				param[0] = mth;
				int j;
				for (j = 1; j < 5; j = j + 2) {
					param[j] = distid;
					param[j + 1] = userValue;
				}
				param[5] = mth;
				param[6] = mth;
				param[7] = mth;
				for (j = 8; j < 17; j = j + 3) {
					param[j] = distid;
					param[j + 1] = userValue;
					param[j + 2] = mth;
				}
				param[j] = mth;
				param[j + 1] = distid;
				param[j + 2] = userValue;

				query = query + CommonSQL.RRC_CASES_MONTH;
				returnList = dao.getRevenueReceiptDetails(query, param);

			}

		} else if (type.equalsIgnoreCase("Period")) {
			String frmdt = reportForm.getFromDate();
			String todt = reportForm.getToDate();
			if (userValue != null) {
				param = new String[28];
				int j;
				for (j = 0; j < 24; j = j + 4) {
					param[j] = distid;
					param[j + 1] = userValue;
					param[j + 2] = frmdt;
					param[j + 3] = todt;
				}
				param[j] = frmdt;
				param[j + 1] = todt;
				param[j + 2] = distid;
				param[j + 3] = userValue;
				query = query + CommonSQL.RRC_CASES_PERIOD;
				returnList = dao.getRevenueReceiptDetails(query, param);

			}

		} else if (type.equalsIgnoreCase("Year")) {
			if (userValue != null) {
				param = new String[22];
				int j;
				for (j = 0; j < 22; j = j + 2) {
					param[j] = distid;
					param[j + 1] = userValue;
				}

				query = query + CommonSQL.RRC_CASES_YEAR;
				returnList = dao.getRevenueReceiptDetails(query, param);

			}

		}
		logger.debug("query=" + query);
		logger.debug("param array=" + param);

		logger.debug("return list=" + returnList);
		if (returnList != null) {
			// it is in case of month
			if (type.equalsIgnoreCase("Month")) {

				for (int i = 0; i < returnList.size(); i++) {

					double amt = 0, pendingamt = 0, recamt = 0;
					// double
					// stampdutyPrev=0f,regfeePrev=0f,ruralPrev=0f,urbanPrev=0f;
					// double
					// sdtotal=0f,sdprevtotal=0f,ruraltotal=0f,ruralprevtotal=0f;
					// double
					// sdcomptotal=0f,ruralcomptotal=0f,sdcomp=0f,ruralcomp=0f;
					// double regfeecomp=0.0,urbancomp=0.0;

					ArrayList listRet = (ArrayList) returnList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					String sno = i + 1 + "";
					reportDTO.setSno(sno);
					reportDTO.setAnualTarget((String) listRet.get(1));
					reportDTO.setMthTarget((String) listRet.get(2));
					reportDTO.setPendingCases((String) listRet.get(3));
					reportDTO.setCaseRegisd((String) listRet.get(4));
					amt = listRet.get(5) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(5));
					reportDTO.setCasesDisp((String) listRet.get(6));
					reportDTO.setCasesPend((String) listRet.get(8));
					recamt = listRet.get(7) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(7));

					pendingamt = listRet.get(9) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(9));

					reportDTO.setAmt("" + amt);
					reportDTO.setAmtRecover("" + recamt);
					reportDTO.setAmtPend("" + pendingamt);

					list.add(reportDTO);

				}
			} else if (type.equalsIgnoreCase("Period")) {
				for (int i = 0; i < returnList.size(); i++) {
					double amt = 0, pendingamt = 0, recamt = 0;
					ArrayList listRet = (ArrayList) returnList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					String sno = i + 1 + "";
					reportDTO.setSno(sno);
					reportDTO.setAnualTarget((String) listRet.get(1));
					reportDTO.setCaseRegisd((String) listRet.get(2));
					amt = listRet.get(3) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(3));
					reportDTO.setCasesDisp((String) listRet.get(4));
					reportDTO.setPendingCases((String) listRet.get(6));
					recamt = listRet.get(5) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(5));

					pendingamt = listRet.get(7) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(7));

					reportDTO.setAmt("" + amt);
					reportDTO.setAmtRecover("" + recamt);
					reportDTO.setAmtPend("" + pendingamt);

					list.add(reportDTO);
				}
			} else if (type.equalsIgnoreCase("Year")) {
				for (int i = 0; i < returnList.size(); i++) {

					double amt1 = 0, amt2 = 0, amt3 = 0, amt4 = 0, amt5 = 0;
					ArrayList listRet = (ArrayList) returnList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					String sno = i + 1 + "";
					reportDTO.setSno(sno);
					reportDTO.setNoCasesone((String) listRet.get(0));

					amt1 = listRet.get(1) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(1));

					reportDTO.setNoCasestwo((String) listRet.get(2));
					amt2 = listRet.get(3) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(3));
					amt3 = listRet.get(5) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(5));
					amt4 = listRet.get(7) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(7));
					amt5 = listRet.get(9) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(9));
					reportDTO.setNoCasesthree((String) listRet.get(4));
					reportDTO.setNoCasesfour((String) listRet.get(6));
					reportDTO.setNoCasesfive((String) listRet.get(8));
					reportDTO.setAmtone("" + amt1);
					reportDTO.setAmttwo("" + amt2);
					reportDTO.setAmtthree("" + amt3);
					reportDTO.setAmtfour("" + amt4);
					reportDTO.setAmtfive("" + amt5);
					list.add(reportDTO);
				}
			}
		}
		return list;

	}

	/**
	 * ===========================================================================
	 * Method :getCessDetails Description : Returns list of cess report fields
	 * Arguments : form return type : Arraylist Author : vengamamba P Created
	 * Date : jun 11, 2008
	 * ===========================================================================
	 */
	public ArrayList getCessDetails(ReportingForm reportForm) {

		ReportingDAO dao = new ReportingDAO();
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String param[] = null;
		String userValue = null;
		String query = "";

		String type = reportForm.getRadioSearch();
		if (type.equalsIgnoreCase("Month")) {
			String mth = reportForm.getMonth();

			param = new String[1];
			param[0] = mth;

			query = query + CommonSQL.CESS_INF_MONTH;
			returnList = dao.getRevenueReceiptDetails(query, param);

		} else if (type.equalsIgnoreCase("Period")) {

			param = new String[2];
			String frmdt = reportForm.getFromDate();
			String todt = reportForm.getToDate();
			param[0] = frmdt;
			param[1] = todt;
			query = query + CommonSQL.CESS_INF_PERIOD;
			System.out.println(query);
			returnList = dao.getRevenueReceiptDetails(query, param);

		}

		logger.debug("query=" + query);
		logger.debug("param array=" + param);

		logger.debug("return list=" + returnList);
		if (returnList != null) {
			// it is in case of month
			for (int i = 0; i < returnList.size(); i++) {

				double amtsc = 0, amthc = 0, amtrb = 0, amtrc = 0;

				ArrayList listRet = (ArrayList) returnList.get(i);
				ReportingDTO reportDTO = new ReportingDTO();
				String sno = i + 1 + "";
				reportDTO.setSno(sno);
				/*reportDTO.setCasenoSc((String) listRet.get(0));
				amtsc = listRet.get(1) == null ? 0.0 : Double
						.parseDouble((String) listRet.get(1));
				reportDTO.setAmtRecoverSc("" + amtsc);
				amthc = listRet.get(5) == null ? 0.0 : Double
						.parseDouble((String) listRet.get(5));
				amtrb = listRet.get(9) == null ? 0.0 : Double
						.parseDouble((String) listRet.get(9));
				amtrc = listRet.get(13) == null ? 0.0 : Double
						.parseDouble((String) listRet.get(13));

				reportDTO.setLatestCaseSc((String) listRet.get(2));
				reportDTO.setCurrentcaseSc((String) listRet.get(3));
				reportDTO.setCasenoHc((String) listRet.get(4));
				reportDTO.setAmtRecoverHc("" + amthc);
				reportDTO.setLatestCaseHc((String) listRet.get(6));
				reportDTO.setCurrentcaseHc((String) listRet.get(7));
				reportDTO.setCasenoBr((String) listRet.get(8));
				reportDTO.setAmtRecoverBr("" + amtrb);
				reportDTO.setLatestCaseBr((String) listRet.get(10));
				reportDTO.setCurrentcaseBr((String) listRet.get(11));
				reportDTO.setCasenoC((String) listRet.get(12));

				reportDTO.setAmtRecoverC("" + amtrc);
				reportDTO.setLatestCaseC((String) listRet.get(14));
				reportDTO.setCurrentcaseC((String) listRet.get(15));
				reportDTO.setRemarks(null);*/
				if((listRet.get(0)!=null))
				{
				reportDTO.setCasenoSc((String) listRet.get(0));
				}
				else
				{
					reportDTO.setCasenoSc("-");
				}
				amtsc = listRet.get(1) == null ? 0.0 : Double
						.parseDouble((String) listRet.get(1));
				
				reportDTO.setAmtRecoverSc("" + amtsc);
				
				amthc = listRet.get(7) == null ? 0.0 : Double
						.parseDouble((String) listRet.get(7));
				amtrb = listRet.get(13) == null ? 0.0 : Double
						.parseDouble((String) listRet.get(13));
				amtrc = listRet.get(19) == null ? 0.0 : Double
						.parseDouble((String) listRet.get(19));
				
				
				
				if((listRet.get(5)!=null))
				{
				reportDTO.setRemarks((String) listRet.get(5));
				}
				else
				{
					
				}
				if((listRet.get(11)!=null))
				{
				reportDTO.setRemarksHC((String) listRet.get(11));
				}
				else
				{
					
				}
				if((listRet.get(17)!=null))
				{
				reportDTO.setRemarksRB((String) listRet.get(17));
				}
				else
				{
					
				}
				if((listRet.get(23)!=null))
				{
				reportDTO.setRemarksRC((String) listRet.get(23));
				}
				else
				{
					
				}
				
				if((listRet.get(8)!=null))
				{
				reportDTO.setActionHc((String) listRet.get(8));
				}
				else
				{
					reportDTO.setActionHc("-");
				}
				if((listRet.get(14)!=null))
				{
				reportDTO.setActionRb((String) listRet.get(14));
				}
				else
				{
					reportDTO.setActionRb("-");	
				}
				if((listRet.get(20)!=null))
				{
				reportDTO.setActionRc((String) listRet.get(20));
				}
				else
				{
					reportDTO.setActionRc("-");
				}
				if((listRet.get(2)!=null))
			       {
					reportDTO.setActionSc((String) listRet.get(2));
			       }
			       else
			       {
			    	   reportDTO.setActionSc("-");
			       }
				if((listRet.get(3)!=null))
				{
				reportDTO.setLatestCaseSc((String) listRet.get(3));
				}
				else
				{
					reportDTO.setLatestCaseSc("-");
				}
				if((listRet.get(4)!=null))
				{
				reportDTO.setCurrentcaseSc((String) listRet.get(4));
				}
				else
					{
					reportDTO.setCurrentcaseSc("-");
					}
				if((listRet.get(6)!=null))
				{
					reportDTO.setCasenoHc((String) listRet.get(6));
				}
				else
				{
					reportDTO.setCasenoHc("-");
				}
			
				reportDTO.setAmtRecoverHc("" + amthc);
				
				if((listRet.get(9)!=null))
				{
				reportDTO.setLatestCaseHc((String) listRet.get(9));
				}
				else
				{
					reportDTO.setLatestCaseHc("-");
				}
				if((listRet.get(10)!=null))
				{
				reportDTO.setCurrentcaseHc((String) listRet.get(10));
				}
				else
				{
					reportDTO.setCurrentcaseHc("-");
				}
				if((listRet.get(12)!=null))
				{
				reportDTO.setCasenoBr((String) listRet.get(12));
				}
				else
				{
					reportDTO.setCasenoBr("-");
				}
				
				reportDTO.setAmtRecoverBr("" + amtrb);
				
				if((listRet.get(15)!=null))
				{
				reportDTO.setLatestCaseBr((String) listRet.get(15));
				}
				else
				{
					reportDTO.setLatestCaseBr("-");
				}
				if((listRet.get(16)!=null))
				{
				reportDTO.setCurrentcaseBr((String) listRet.get(16));
				}
				else
				{
					reportDTO.setCurrentcaseBr("-");
				}
				if((listRet.get(18)!=null))
				{
				reportDTO.setCasenoC((String) listRet.get(18));
				}
				else
				{
					reportDTO.setCasenoC("-");
				}
				
				reportDTO.setAmtRecoverC("" + amtrc);
				
				if((listRet.get(21)!=null))
				{
				reportDTO.setLatestCaseC((String) listRet.get(21));
				}else
				{
					reportDTO.setLatestCaseC("-");
				}
				if((listRet.get(22)!=null))
				{
				reportDTO.setCurrentcaseC((String) listRet.get(22));
				}
				else
				{
					reportDTO.setCurrentcaseC("-");
				}
				list.add(reportDTO);

			}
		}
		return list;

	}

	/**
	 * ===========================================================================
	 * Method : getCompRevenueReceiptDetails Description : Returns list of
	 * stampduty,Reg fee and rural urban and totalno of docs Arguments : form
	 * return type : Arraylist Author : vengamamba P Created Date : jun 5, 2008
	 * ===========================================================================
	 */
	public ArrayList getCompRevenueReceiptDetails(ReportingForm reportForm) {

		ReportingDAO dao = new ReportingDAO();

		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String param[] = null;
		String userValue = null;
		String query = "";

		try {
			common = new IGRSCommon();
		} catch (Exception x) {
			logger.debug(" in Exception" + x);
		}

		String typeofuser = reportForm.getUserType();

		if (typeofuser.equalsIgnoreCase("sro")) {
			userValue = reportForm.getSroID();
		} else if (typeofuser.equalsIgnoreCase("dro")) {
			userValue = reportForm.getDroID();
		}

		String type = reportForm.getRadioSearch();
		if (type.equalsIgnoreCase("Month")) {
			String mth = reportForm.getMonth();
			if (userValue != null) {
				param = new String[17];
				param[0] = mth;
				for (int j = 1; j < 17; j = j + 2) {
					param[j] = userValue;
					param[j + 1] = mth;
				}
				query = query + CommonSQL.COMP_REVENUE_REGDOC_MONTH;
				returnList = dao.getRevenueReceiptDetails(query, param);

			}

		} else if (type.equalsIgnoreCase("Period")) {
			String frmdt = reportForm.getFromDate();
			String todt = reportForm.getToDate();
			if (userValue != null) {
				param = new String[31];
				param[0] = frmdt;
				for (int j = 1; j < 31; j = j + 3) {
					param[j] = userValue;
					param[j + 1] = frmdt;
					param[j + 2] = todt;
				}

				query = query + CommonSQL.COMP_REVENUE_REGDOC_PERIOD;
				returnList = dao.getRevenueReceiptDetails(query, param);

			}

		}
		logger.debug("query=" + query);
		logger.debug("param array=" + param);

		logger.debug("return list=" + returnList);
		if (returnList != null) {
			// it is in case of month
			if (type.equalsIgnoreCase("Month")) {

				for (int i = 0; i < returnList.size(); i++) {

					double stampDuty = 0, regfee = 0, rural = 0, urban = 0;
					double stampdutyPrev = 0f, regfeePrev = 0f, ruralPrev = 0f, urbanPrev = 0f;
					double sdtotal = 0f, sdprevtotal = 0f, ruraltotal = 0f, ruralprevtotal = 0f;
					double sdcomptotal = 0f, ruralcomptotal = 0f, sdcomp = 0f, ruralcomp = 0f;
					double regfeecomp = 0.0, urbancomp = 0.0;

					ArrayList listRet = (ArrayList) returnList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					String sno = i + 1 + "";
					reportDTO.setSno(sno);
					reportDTO.setDistid((String) listRet.get(1));
					reportDTO.setAnnualTarget(((String) listRet.get(2)));
					reportDTO.setMonthTarget(((String) listRet.get(3)));
					stampDuty = listRet.get(4) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(4));

					regfee = listRet.get(5) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(5));
					sdtotal = stampDuty + regfee;
					rural = listRet.get(6) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(6));
					urban = listRet.get(7) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(7));
					ruraltotal = rural + rural;
					stampdutyPrev = listRet.get(8) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(8));
					regfeePrev = listRet.get(9) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(9));
					sdprevtotal = stampdutyPrev + regfeePrev;
					ruralPrev = listRet.get(10) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(10));
					urbanPrev = listRet.get(11) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(11));
					ruralprevtotal = ruralPrev + urbanPrev;

					sdcomp = stampdutyPrev == 0.0 ? 0.00
							: ((stampDuty - stampdutyPrev) / stampdutyPrev) * 100;
					ruralcomp = ruralPrev == 0.0 ? 0.00
							: ((rural - ruralPrev) / ruralPrev) * 100;

					regfeecomp = regfeePrev == 0.0 ? 0.00
							: ((regfee - regfeePrev) / regfeePrev) * 100;
					urbancomp = urbanPrev == 0.0 ? 0.00
							: ((urban - urbanPrev) / urbanPrev) * 100;

					sdcomptotal = sdcomp + regfeecomp;
					ruralcomptotal = ruralcomp + urbancomp;

					reportDTO.setStampduty("" + stampDuty);
					reportDTO.setRegfee("" + regfee);
					reportDTO.setTotal("" + sdtotal);

					reportDTO.setRural("" + rural);
					reportDTO.setUrban("" + urban);
					reportDTO.setAreaTotal("" + ruraltotal);

					reportDTO.setStampdutyPrev("" + stampdutyPrev);
					reportDTO.setRegfeePrev("" + regfeePrev);
					reportDTO.setTotalPrev("" + sdprevtotal);

					reportDTO.setRuralPrev("" + ruralPrev);
					reportDTO.setUrbanPrev("" + urbanPrev);
					reportDTO.setAreaTotalPrev("" + ruralprevtotal);

					reportDTO.setStampdutyComp(""
							+ common.getCurrencyFormat(sdcomp) + " %");
					reportDTO.setRegfeeComp(""
							+ common.getCurrencyFormat(regfeecomp) + " %");
					reportDTO.setTotalComp(""
							+ common.getCurrencyFormat(sdcomptotal) + " %");

					reportDTO.setRuralComp(""
							+ common.getCurrencyFormat(ruralcomp) + " %");
					reportDTO.setAreaTotalComp(""
							+ common.getCurrencyFormat(ruralcomptotal) + " %");
					reportDTO.setUrbanComp(""
							+ common.getCurrencyFormat(urbancomp) + " %");

					list.add(reportDTO);

				}
			} else {
				for (int i = 0; i < returnList.size(); i++) {

					double stampDuty = 0, regfee = 0, rural = 0, urban = 0;
					double stampdutyPrev = 0f, regfeePrev = 0f, ruralPrev = 0f, urbanPrev = 0f;
					double sdtotal = 0f, sdprevtotal = 0f, ruraltotal = 0f, ruralprevtotal = 0f;
					double sdcomptotal = 0f, ruralcomptotal = 0f, sdcomp = 0f, ruralcomp = 0f;
					double regfeecomp = 0.0, urbancomp = 0.0, totaldoc = 0.0, totaldocprev = 0.0, totalcomp = 0.0;

					ArrayList listRet = (ArrayList) returnList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					String sno = i + 1 + "";
					reportDTO.setSno(sno);
					reportDTO.setDistid((String) listRet.get(1));
					reportDTO.setAnnualTarget(((String) listRet.get(2)));

					stampDuty = listRet.get(3) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(3));

					regfee = listRet.get(4) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(4));
					sdtotal = stampDuty + regfee;
					rural = listRet.get(5) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(5));
					urban = listRet.get(6) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(6));
					ruraltotal = rural + rural;
					stampdutyPrev = listRet.get(7) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(7));
					regfeePrev = listRet.get(8) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(8));
					sdprevtotal = stampdutyPrev + regfeePrev;
					ruralPrev = listRet.get(9) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(9));
					urbanPrev = listRet.get(10) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(10));
					ruralprevtotal = ruralPrev + urbanPrev;

					totaldoc = listRet.get(11) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(11));
					totaldocprev = listRet.get(12) == null ? 0.0 : Double
							.parseDouble((String) listRet.get(12));

					totalcomp = totaldocprev == 0.0 ? 0.00
							: ((totaldoc - totaldocprev) / totaldocprev) * 100;
					sdcomp = stampdutyPrev == 0.0 ? 0.00
							: ((stampDuty - stampdutyPrev) / stampdutyPrev) * 100;
					ruralcomp = ruralPrev == 0.0 ? 0.00
							: ((rural - ruralPrev) / ruralPrev) * 100;

					regfeecomp = regfeePrev == 0.0 ? 0.00
							: ((regfee - regfeePrev) / regfeePrev) * 100;
					urbancomp = urbanPrev == 0.0 ? 0.00
							: ((urban - urbanPrev) / urbanPrev) * 100;

					sdcomptotal = sdcomp + regfeecomp;
					ruralcomptotal = ruralcomp + urbancomp;

					reportDTO.setStampduty("" + stampDuty);
					reportDTO.setRegfee("" + regfee);
					reportDTO.setTotal("" + sdtotal);

					reportDTO.setRural("" + rural);
					reportDTO.setUrban("" + urban);
					reportDTO.setAreaTotal("" + ruraltotal);

					reportDTO.setStampdutyPrev("" + stampdutyPrev);
					reportDTO.setRegfeePrev("" + regfeePrev);
					reportDTO.setTotalPrev("" + sdprevtotal);

					reportDTO.setRuralPrev("" + ruralPrev);
					reportDTO.setUrbanPrev("" + urbanPrev);
					reportDTO.setAreaTotalPrev("" + ruralprevtotal);

					reportDTO.setStampdutyComp(""
							+ common.getCurrencyFormat(sdcomp) + " %");
					reportDTO.setRegfeeComp(""
							+ common.getCurrencyFormat(regfeecomp) + " %");
					reportDTO.setTotalComp(""
							+ common.getCurrencyFormat(sdcomptotal) + " %");

					reportDTO.setRuralComp(""
							+ common.getCurrencyFormat(ruralcomp) + " %");
					reportDTO.setAreaTotalComp(""
							+ common.getCurrencyFormat(ruralcomptotal) + " %");
					reportDTO.setUrbanComp(""
							+ common.getCurrencyFormat(urbancomp) + " %");

					reportDTO.setNoofdoc("" + totaldoc);
					reportDTO.setNoofdocPrev("" + totaldocprev);
					reportDTO.setNoofdocComp(""
							+ common.getCurrencyFormat(totalcomp) + " %");

					list.add(reportDTO);

				}

			}
		}
		return list;

	}

	/**
	 * ===========================================================================
	 * Method : getRevenueReceiptDetails Description : Returns list of no of
	 * institutions ,no of docs and RevenueReceipts Arguments : form return type :
	 * Arraylist Author : vengamamba P Created Date : jun 4, 2008
	 * ===========================================================================
	 */
	public ArrayList getRevenueReceiptDetails(ReportingForm reportForm) {
		ReportingDAO dao = new ReportingDAO();
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String param[] = null;
		String userValue = null;
		String query = "";

		String typeofuser = reportForm.getUserType();

		if (typeofuser.equalsIgnoreCase("sro")) {
			userValue = reportForm.getSroID();
		} else if (typeofuser.equalsIgnoreCase("dro")) {
			userValue = reportForm.getDroID();
		}

		String type = reportForm.getRadioSearch();
		if (type.equalsIgnoreCase("Month")) {
			String mth = reportForm.getMonth();
			if (userValue != null) {
				param = new String[2];
				param[0] = userValue;
				param[1] = mth;
				query = query + CommonSQL.REVENUE_REGDOC_MONTH;
			} else {
				param = new String[1];
				param[0] = mth;
				query = query + CommonSQL.REVENUE_REGDOC_MONTH_STATE;
			}

		} else if (type.equalsIgnoreCase("Period")) {
			String frmdt = reportForm.getFromDate();
			String todt = reportForm.getToDate();
			if (userValue != null) {
				param = new String[3];
				param[0] = userValue;
				param[1] = frmdt;
				param[2] = todt;
				query = query + CommonSQL.REVENUE_REGDOC_PERIOD;
			} else {
				param = new String[2];
				param[0] = frmdt;
				param[1] = todt;
				query = query + CommonSQL.REVENUE_REGDOC_PERIOD_STATE;
			}
		}
		logger.debug("query=" + query);
		logger.debug("param array=" + param);
		returnList = dao.getRevenueReceiptDetails(query, param);
		logger.debug("return list=" + returnList);
		if (returnList != null) {
			for (int i = 0; i < returnList.size(); i++) {
				ArrayList listRet = (ArrayList) returnList.get(i);
				ReportingDTO reportDTO = new ReportingDTO();
				String sno = i + 1 + "";
				reportDTO.setSno(sno);
				reportDTO.setNameofint((String) listRet.get(0));
				reportDTO.setNoofdocs((String) listRet.get(1));
				reportDTO.setRevrep((String) listRet.get(2));

				list.add(reportDTO);
			}
		}

		return list;

	}

	/**
	 * ===========================================================================
	 * Method : preparecompRevrepPdfmth Description : Returns pdf format file
	 * Arguments : form return type : Returns file Author : vengamamba P Created
	 * Date : jun 10, 2008
	 * ===========================================================================
	 */
	public synchronized File preparecompRevrepPdfmth(ReportingForm reportForm) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);

		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");
			fileName = pr.getValue("REPORT_LOCATION") + "CompareRRRDMonth"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));

			document.open();

			Table datatable = new Table(22);

			int headerwidths[] = { 35, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
					50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);

			datatable.setPadding(3);

			Cell title = new Cell(new Phrase("Comparative Information",
					FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(22);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			
			
			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 9, Font.BOLD)));
			slno.setHeader(true);
			slno.setRowspan(2);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell district = new Cell(new Phrase("District ", FontFactory
					.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			district.setHeader(true);
			district.setRowspan(2);
			district.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(district);

			// datatable.addCell("Header 2");
			Cell annualTarget = new Cell(new Phrase("Annual Target ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			annualTarget.setHeader(true);
			annualTarget.setRowspan(2);
			annualTarget.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(annualTarget);

			// datatable.addCell("Header 3");
			Cell monthlyTarget = new Cell(new Phrase("Monthly Target",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			monthlyTarget.setHeader(true);
			monthlyTarget.setRowspan(2);
			monthlyTarget.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(monthlyTarget);

			// datatable.addCell("Header 4");
			Cell revenueRecetTreasFig = new Cell(
					new Phrase(
							"Revenue Receipt During the Month as Per Treasury Figures ",
							FontFactory.getFont(FontFactory.COURIER, 9,
									Font.BOLD)));
			revenueRecetTreasFig.setHeader(true);
			revenueRecetTreasFig.setColspan(3);
			revenueRecetTreasFig.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(revenueRecetTreasFig);

			// datatable.addCell("Header 5");
			Cell revenueReceSameMonth = new Cell(new Phrase(
					"Revenue Receipt in the Same Month of Previous Year ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			revenueReceSameMonth.setHeader(true);
			revenueReceSameMonth.setColspan(3);
			revenueReceSameMonth.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(revenueReceSameMonth);

			// datatable.addCell("Header 6");

			Cell coparativePercentage = new Cell(new Phrase(
					"Comparative (In Percent)", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			coparativePercentage.setHeader(true);
			coparativePercentage.setColspan(3);
			coparativePercentage.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(coparativePercentage);
			// datatable.addCell("Header 7");

			Cell noOfDocRegDurMonth = new Cell(new Phrase(
					"No. of Documents Registered During the Month ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfDocRegDurMonth.setHeader(true);
			noOfDocRegDurMonth.setColspan(3);
			noOfDocRegDurMonth.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(noOfDocRegDurMonth);

			// datatable.addCell("Header 8");
			Cell noOfDocRegDurSameMonthPy = new Cell(
					new Phrase(
							"No. of Documents Registered During the Same month of Previous Year ",
							FontFactory.getFont(FontFactory.COURIER, 9,
									Font.BOLD)));
			noOfDocRegDurSameMonthPy.setHeader(true);
			noOfDocRegDurSameMonthPy.setColspan(3);
			noOfDocRegDurSameMonthPy
					.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(noOfDocRegDurSameMonthPy);

			// datatable.addCell("Header 5");
			Cell comparIncrDecrDurPer = new Cell(
					new Phrase(
							"Comparative Increase/Decrease in Progressive No. of Documents During Month",
							FontFactory.getFont(FontFactory.COURIER, 9,
									Font.BOLD)));
			comparIncrDecrDurPer.setHeader(true);
			comparIncrDecrDurPer.setColspan(3);
			comparIncrDecrDurPer.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(comparIncrDecrDurPer);

			// datatable.addCell("Header 5");

			Cell stampDutyDurMonthTresFig = new Cell(new Phrase("Stamp Duty ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			stampDutyDurMonthTresFig.setHeader(true);
			stampDutyDurMonthTresFig.setColspan(1);
			stampDutyDurMonthTresFig
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(stampDutyDurMonthTresFig);

			Cell regFeeDurMonthTresFig = new Cell(new Phrase(
					"Registration Fee ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			regFeeDurMonthTresFig.setHeader(true);
			regFeeDurMonthTresFig.setColspan(1);
			regFeeDurMonthTresFig.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(regFeeDurMonthTresFig);

			Cell totalDurMonthTresFig = new Cell(new Phrase("Total ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalDurMonthTresFig.setHeader(true);
			totalDurMonthTresFig.setColspan(1);
			totalDurMonthTresFig.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalDurMonthTresFig);

			Cell stampDutySameMonPreYr = new Cell(new Phrase("Stamp Duty ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			stampDutySameMonPreYr.setHeader(true);
			stampDutySameMonPreYr.setColspan(1);
			stampDutySameMonPreYr.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(stampDutySameMonPreYr);

			Cell regFeeSameMonPreYr = new Cell(new Phrase("Registration Fee ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			regFeeSameMonPreYr.setHeader(true);
			regFeeSameMonPreYr.setColspan(1);
			regFeeSameMonPreYr.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(regFeeSameMonPreYr);

			Cell totalSameMonPreYr = new Cell(new Phrase("Total ", FontFactory
					.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalSameMonPreYr.setHeader(true);
			totalSameMonPreYr.setColspan(1);
			totalSameMonPreYr.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalSameMonPreYr);

			Cell stampDutyComparative = new Cell(new Phrase("Stamp Duty ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			stampDutyComparative.setHeader(true);
			stampDutyComparative.setColspan(1);
			stampDutyComparative.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(stampDutyComparative);

			Cell regFeeComparative = new Cell(new Phrase("Registration Fee ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			regFeeComparative.setHeader(true);
			regFeeComparative.setColspan(1);
			regFeeComparative.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(regFeeComparative);

			Cell totalComparative = new Cell(new Phrase("Total ", FontFactory
					.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalComparative.setHeader(true);
			totalComparative.setColspan(1);
			totalComparative.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalComparative);

			Cell ruralAreaDocRegDurMonth = new Cell(new Phrase("Rural Area  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			ruralAreaDocRegDurMonth.setHeader(true);
			ruralAreaDocRegDurMonth.setColspan(1);
			ruralAreaDocRegDurMonth
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(ruralAreaDocRegDurMonth);

			Cell urbanAreaDocRegDurMonth = new Cell(new Phrase("Urban Area  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			urbanAreaDocRegDurMonth.setHeader(true);
			urbanAreaDocRegDurMonth.setColspan(1);
			urbanAreaDocRegDurMonth
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(urbanAreaDocRegDurMonth);

			Cell totalDocRegDurMonth = new Cell(new Phrase("Total  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalDocRegDurMonth.setHeader(true);
			totalDocRegDurMonth.setColspan(1);
			totalDocRegDurMonth.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalDocRegDurMonth);

			Cell ruralAreaDocRegDurSameMonthPYr = new Cell(new Phrase(
					"Rural Area  ", FontFactory.getFont(FontFactory.COURIER, 9,
							Font.BOLD)));
			ruralAreaDocRegDurSameMonthPYr.setHeader(true);
			ruralAreaDocRegDurSameMonthPYr.setColspan(1);
			ruralAreaDocRegDurSameMonthPYr.setBackgroundColor(new Color(196,
					255, 255));
			datatable.addCell(ruralAreaDocRegDurSameMonthPYr);

			Cell urbanAreaDocRegDurSameMonthPYr = new Cell(new Phrase(
					"Urban Area  ", FontFactory.getFont(FontFactory.COURIER, 9,
							Font.BOLD)));
			urbanAreaDocRegDurSameMonthPYr.setHeader(true);
			urbanAreaDocRegDurSameMonthPYr.setColspan(1);
			urbanAreaDocRegDurSameMonthPYr.setBackgroundColor(new Color(196,
					255, 255));
			datatable.addCell(urbanAreaDocRegDurSameMonthPYr);

			Cell totalDocRegDurSameMonthPYr = new Cell(new Phrase("Total  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalDocRegDurSameMonthPYr.setHeader(true);
			totalDocRegDurSameMonthPYr.setColspan(1);
			totalDocRegDurSameMonthPYr.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(totalDocRegDurSameMonthPYr);

			Cell ruralAreaDocRegDurPerFromApr = new Cell(new Phrase(
					"Rural Area  ", FontFactory.getFont(FontFactory.COURIER, 9,
							Font.BOLD)));
			ruralAreaDocRegDurPerFromApr.setHeader(true);
			ruralAreaDocRegDurPerFromApr.setColspan(1);
			ruralAreaDocRegDurPerFromApr.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(ruralAreaDocRegDurPerFromApr);

			Cell urbanAreaDocRegDurPerFromApr = new Cell(new Phrase(
					"Urban Area  ", FontFactory.getFont(FontFactory.COURIER, 9,
							Font.BOLD)));
			urbanAreaDocRegDurPerFromApr.setHeader(true);
			urbanAreaDocRegDurPerFromApr.setColspan(1);
			urbanAreaDocRegDurPerFromApr.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(urbanAreaDocRegDurPerFromApr);

			Cell totalDocRegDurPerFromApr = new Cell(new Phrase("Total  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalDocRegDurPerFromApr.setHeader(true);
			totalDocRegDurPerFromApr.setColspan(1);
			totalDocRegDurPerFromApr
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalDocRegDurPerFromApr);

			datatable.endHeaders();
			for (int i = 0; i < reportForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportingDTO reportDTO = new ReportingDTO();
				reportDTO = (ReportingDTO) reportForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values====="
						+ reportForm.getReportList());
				logger.debug("list size========"
						+ reportForm.getReportList().size());
				// datatable.setRight(Element.ALIGN_RIGHT);
				datatable.addCell(String.valueOf(reportDTO.getSno()));
				datatable.addCell(reportDTO.getDistid());
				datatable.addCell(reportDTO.getAnnualTarget());
				datatable.addCell(reportDTO.getMonthTarget());
				datatable.addCell(reportDTO.getStampduty());
				datatable.addCell(reportDTO.getRegfee());
				datatable.addCell(reportDTO.getTotal());
				datatable.addCell(reportDTO.getStampdutyPrev());
				datatable.addCell(reportDTO.getRegfeePrev());
				datatable.addCell(reportDTO.getTotalPrev());
				datatable.addCell(reportDTO.getStampdutyComp());
				datatable.addCell(reportDTO.getRegfeeComp());
				datatable.addCell(reportDTO.getTotalComp());
				datatable.addCell(reportDTO.getRural());
				datatable.addCell(reportDTO.getUrban());
				datatable.addCell(reportDTO.getAreaTotal());
				datatable.addCell(reportDTO.getRuralPrev());
				datatable.addCell(reportDTO.getUrbanPrev());
				datatable.addCell(reportDTO.getAreaTotalPrev());
				datatable.addCell(reportDTO.getRuralComp());
				datatable.addCell(reportDTO.getUrbanComp());
				datatable.addCell(reportDTO.getAreaTotalComp());

			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	/**
	 * ===========================================================================
	 * Method : preparecompRevrepPdfperiod Description : Returns pdf format file
	 * Arguments : form return type : Returns file Author : vengamamba P Created
	 * Date : jun 10, 2008
	 * ===========================================================================
	 */
	public synchronized File preparecompRevrepPdfperiod(ReportingForm reportForm) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);

		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");
			fileName = pr.getValue("REPORT_LOCATION") + "CompareRRRDPeriod"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));

			document.open();

			Table datatable = new Table(24);

			int headerwidths[] = { 35, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
					50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);

			datatable.setPadding(3);

			Cell title = new Cell(new Phrase("Corporate Information",
					FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(24);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			
			
			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 9, Font.BOLD)));
			slno.setHeader(true);
			slno.setRowspan(2);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell district = new Cell(new Phrase("District ", FontFactory
					.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			district.setHeader(true);
			district.setRowspan(2);
			district.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(district);

			// datatable.addCell("Header 2");
			Cell annualTarget = new Cell(new Phrase("Annual Target ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			annualTarget.setHeader(true);
			annualTarget.setRowspan(2);
			annualTarget.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(annualTarget);

			// datatable.addCell("Header 4");
			Cell revenueRecetTreasFig = new Cell(
					new Phrase(
							"Revenue Receipt During the Period as Per Treasury Figures ",
							FontFactory.getFont(FontFactory.COURIER, 9,
									Font.BOLD)));
			revenueRecetTreasFig.setHeader(true);
			revenueRecetTreasFig.setColspan(3);
			revenueRecetTreasFig.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(revenueRecetTreasFig);

			// datatable.addCell("Header 5");
			Cell revenueReceSameMonth = new Cell(new Phrase(
					"Revenue Receipt in the Same Period of Previous Year ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			revenueReceSameMonth.setHeader(true);
			revenueReceSameMonth.setColspan(3);
			revenueReceSameMonth.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(revenueReceSameMonth);

			// datatable.addCell("Header 6");

			Cell coparativePercentage = new Cell(new Phrase(
					"Comparative (In Percent)", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			coparativePercentage.setHeader(true);
			coparativePercentage.setColspan(3);
			coparativePercentage.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(coparativePercentage);
			// datatable.addCell("Header 7");

			Cell noOfDocRegDurMonth = new Cell(new Phrase(
					"No. of Documents Registered During the Period ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfDocRegDurMonth.setHeader(true);
			noOfDocRegDurMonth.setColspan(3);
			noOfDocRegDurMonth.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(noOfDocRegDurMonth);

			// datatable.addCell("Header 8");
			Cell noOfDocRegDurSameMonthPy = new Cell(
					new Phrase(
							"No. of Documents Registered During the Same Period of Previous Year ",
							FontFactory.getFont(FontFactory.COURIER, 9,
									Font.BOLD)));
			noOfDocRegDurSameMonthPy.setHeader(true);
			noOfDocRegDurSameMonthPy.setColspan(3);
			noOfDocRegDurSameMonthPy
					.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(noOfDocRegDurSameMonthPy);

			// datatable.addCell("Header 5");
			Cell comparIncrDecrDurPer = new Cell(
					new Phrase(
							"Comparative Increase/Decrease in Progressive No. of Documents During Period",
							FontFactory.getFont(FontFactory.COURIER, 9,
									Font.BOLD)));
			comparIncrDecrDurPer.setHeader(true);
			comparIncrDecrDurPer.setColspan(3);
			comparIncrDecrDurPer.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(comparIncrDecrDurPer);

			Cell noOfDocRegDurPeriod = new Cell(new Phrase(
					"No. of Documents Registered During the Period",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfDocRegDurPeriod.setHeader(true);
			noOfDocRegDurPeriod.setRowspan(2);
			noOfDocRegDurPeriod.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(noOfDocRegDurPeriod);

			Cell noOfDocRegDurSamePeriod = new Cell(new Phrase(
					"No. of Documents Registered During the Same Period of",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			noOfDocRegDurSamePeriod.setHeader(true);
			noOfDocRegDurSamePeriod.setRowspan(2);
			noOfDocRegDurSamePeriod.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(noOfDocRegDurSamePeriod);

			Cell compIncDecNumber = new Cell(new Phrase(
					"Comparative Increase/Decrease Number ", FontFactory
							.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			compIncDecNumber.setHeader(true);
			compIncDecNumber.setRowspan(2);
			compIncDecNumber.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(compIncDecNumber);

			Cell stampDutyDurMonthTresFig = new Cell(new Phrase("Stamp Duty ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			stampDutyDurMonthTresFig.setHeader(true);
			stampDutyDurMonthTresFig.setColspan(1);
			stampDutyDurMonthTresFig
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(stampDutyDurMonthTresFig);

			Cell regFeeDurMonthTresFig = new Cell(new Phrase(
					"Registration Fee ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			regFeeDurMonthTresFig.setHeader(true);
			regFeeDurMonthTresFig.setColspan(1);
			regFeeDurMonthTresFig.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(regFeeDurMonthTresFig);

			Cell totalDurMonthTresFig = new Cell(new Phrase("Total ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalDurMonthTresFig.setHeader(true);
			totalDurMonthTresFig.setColspan(1);
			totalDurMonthTresFig.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalDurMonthTresFig);

			Cell stampDutySameMonPreYr = new Cell(new Phrase("Stamp Duty ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			stampDutySameMonPreYr.setHeader(true);
			stampDutySameMonPreYr.setColspan(1);
			stampDutySameMonPreYr.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(stampDutySameMonPreYr);

			Cell regFeeSameMonPreYr = new Cell(new Phrase("Registration Fee ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			regFeeSameMonPreYr.setHeader(true);
			regFeeSameMonPreYr.setColspan(1);
			regFeeSameMonPreYr.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(regFeeSameMonPreYr);

			Cell totalSameMonPreYr = new Cell(new Phrase("Total ", FontFactory
					.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalSameMonPreYr.setHeader(true);
			totalSameMonPreYr.setColspan(1);
			totalSameMonPreYr.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalSameMonPreYr);

			Cell stampDutyComparative = new Cell(new Phrase("Stamp Duty ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			stampDutyComparative.setHeader(true);
			stampDutyComparative.setColspan(1);
			stampDutyComparative.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(stampDutyComparative);

			Cell regFeeComparative = new Cell(new Phrase("Registration Fee ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			regFeeComparative.setHeader(true);
			regFeeComparative.setColspan(1);
			regFeeComparative.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(regFeeComparative);

			Cell totalComparative = new Cell(new Phrase("Total ", FontFactory
					.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalComparative.setHeader(true);
			totalComparative.setColspan(1);
			totalComparative.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalComparative);

			Cell ruralAreaDocRegDurMonth = new Cell(new Phrase("Rural Area  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			ruralAreaDocRegDurMonth.setHeader(true);
			ruralAreaDocRegDurMonth.setColspan(1);
			ruralAreaDocRegDurMonth
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(ruralAreaDocRegDurMonth);

			Cell urbanAreaDocRegDurMonth = new Cell(new Phrase("Urban Area  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			urbanAreaDocRegDurMonth.setHeader(true);
			urbanAreaDocRegDurMonth.setColspan(1);
			urbanAreaDocRegDurMonth
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(urbanAreaDocRegDurMonth);

			Cell totalDocRegDurMonth = new Cell(new Phrase("Total  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalDocRegDurMonth.setHeader(true);
			totalDocRegDurMonth.setColspan(1);
			totalDocRegDurMonth.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalDocRegDurMonth);

			Cell ruralAreaDocRegDurSameMonthPYr = new Cell(new Phrase(
					"Rural Area  ", FontFactory.getFont(FontFactory.COURIER, 9,
							Font.BOLD)));
			ruralAreaDocRegDurSameMonthPYr.setHeader(true);
			ruralAreaDocRegDurSameMonthPYr.setColspan(1);
			ruralAreaDocRegDurSameMonthPYr.setBackgroundColor(new Color(196,
					255, 255));
			datatable.addCell(ruralAreaDocRegDurSameMonthPYr);

			Cell urbanAreaDocRegDurSameMonthPYr = new Cell(new Phrase(
					"Urban Area  ", FontFactory.getFont(FontFactory.COURIER, 9,
							Font.BOLD)));
			urbanAreaDocRegDurSameMonthPYr.setHeader(true);
			urbanAreaDocRegDurSameMonthPYr.setColspan(1);
			urbanAreaDocRegDurSameMonthPYr.setBackgroundColor(new Color(196,
					255, 255));
			datatable.addCell(urbanAreaDocRegDurSameMonthPYr);

			Cell totalDocRegDurSameMonthPYr = new Cell(new Phrase("Total  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalDocRegDurSameMonthPYr.setHeader(true);
			totalDocRegDurSameMonthPYr.setColspan(1);
			totalDocRegDurSameMonthPYr.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(totalDocRegDurSameMonthPYr);

			Cell ruralAreaDocRegDurPerFromApr = new Cell(new Phrase(
					"Rural Area  ", FontFactory.getFont(FontFactory.COURIER, 9,
							Font.BOLD)));
			ruralAreaDocRegDurPerFromApr.setHeader(true);
			ruralAreaDocRegDurPerFromApr.setColspan(1);
			ruralAreaDocRegDurPerFromApr.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(ruralAreaDocRegDurPerFromApr);

			Cell urbanAreaDocRegDurPerFromApr = new Cell(new Phrase(
					"Urban Area  ", FontFactory.getFont(FontFactory.COURIER, 9,
							Font.BOLD)));
			urbanAreaDocRegDurPerFromApr.setHeader(true);
			urbanAreaDocRegDurPerFromApr.setColspan(1);
			urbanAreaDocRegDurPerFromApr.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(urbanAreaDocRegDurPerFromApr);

			Cell totalDocRegDurPerFromApr = new Cell(new Phrase("Total  ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			totalDocRegDurPerFromApr.setHeader(true);
			totalDocRegDurPerFromApr.setColspan(1);
			totalDocRegDurPerFromApr
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(totalDocRegDurPerFromApr);

			datatable.endHeaders();
			for (int i = 0; i < reportForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportingDTO reportDTO = new ReportingDTO();
				reportDTO = (ReportingDTO) reportForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values====="
						+ reportForm.getReportList());
				logger.debug("list size========"
						+ reportForm.getReportList().size());
				// datatable.setRight(Element.ALIGN_RIGHT);
				datatable.addCell(String.valueOf(reportDTO.getSno()));
				datatable.addCell(reportDTO.getDistid());
				datatable.addCell(reportDTO.getAnnualTarget());
				// datatable.addCell(reportDTO.getMonthTarget());
				datatable.addCell(reportDTO.getStampduty());
				datatable.addCell(reportDTO.getRegfee());
				datatable.addCell(reportDTO.getTotal());
				datatable.addCell(reportDTO.getStampdutyPrev());
				datatable.addCell(reportDTO.getRegfeePrev());
				datatable.addCell(reportDTO.getTotalPrev());
				datatable.addCell(reportDTO.getStampdutyComp());
				datatable.addCell(reportDTO.getRegfeeComp());
				datatable.addCell(reportDTO.getTotalComp());
				datatable.addCell(reportDTO.getRural());
				datatable.addCell(reportDTO.getUrban());
				datatable.addCell(reportDTO.getAreaTotal());
				datatable.addCell(reportDTO.getRuralPrev());
				datatable.addCell(reportDTO.getUrbanPrev());
				datatable.addCell(reportDTO.getAreaTotalPrev());
				datatable.addCell(reportDTO.getRuralComp());
				datatable.addCell(reportDTO.getUrbanComp());
				datatable.addCell(reportDTO.getAreaTotalComp());
				datatable.addCell(reportDTO.getNoofdoc());
				datatable.addCell(reportDTO.getNoofdocPrev());
				datatable.addCell(reportDTO.getNoofdocComp());

			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	/**
	 * ===========================================================================
	 * Method : prepareCessPdfFile Description : Returns pdf format file
	 * Arguments : form return type : Returns file Author : vengamamba P Created
	 * Date : jun 12, 2008
	 * ===========================================================================
	 */
	public synchronized File prepareCessPdfFile(ReportingForm reportForm) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);

		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");
			fileName = pr.getValue("REPORT_LOCATION") + "Cess Wise"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));

			document.open();

			Table datatable = new Table(18);

			int headerwidths[] = { 35, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
					50, 50, 50, 50, 50, 50, 50, };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);

			datatable.setPadding(3);

			Cell title = new Cell(
					new Phrase(
							"Cess Wise Information of Pending Stamp Cases, RRC Cases, Appeal Cases, Cases in Stay",
							FontFactory.getFont(FontFactory.COURIER, 18,
									Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(18);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			// datatable.setDefaultCellBorderWidth(2);
			// 
			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 9, Font.BOLD)));
			slno.setHeader(true);
			slno.setRowspan(2);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell supremeCourt = new Cell(new Phrase("Supreme Court ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			supremeCourt.setHeader(true);
			supremeCourt.setColspan(4);
			supremeCourt.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(supremeCourt);

			// datatable.addCell("Header 2");
			Cell highCourt = new Cell(new Phrase("High Court ", FontFactory
					.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			highCourt.setHeader(true);
			highCourt.setColspan(4);
			highCourt.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(highCourt);

			// datatable.addCell("Header 3");
			Cell boardofRevenue = new Cell(new Phrase("Board of Revenue",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			boardofRevenue.setHeader(true);
			boardofRevenue.setColspan(4);
			boardofRevenue.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(boardofRevenue);

			// datatable.addCell("Header 4");
			Cell commissioner = new Cell(new Phrase("Commissioner ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			commissioner.setHeader(true);
			commissioner.setColspan(4);
			commissioner.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(commissioner);

			// datatable.addCell("Header 5");
			Cell remarks = new Cell(new Phrase("Remarks ", FontFactory.getFont(
					FontFactory.COURIER, 9, Font.BOLD)));
			remarks.setHeader(true);
			remarks.setRowspan(2);
			remarks.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(remarks);

			// datatable.addCell("Header 7");

			Cell caseNoforSupCourt = new Cell(new Phrase("Case No. ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			caseNoforSupCourt.setHeader(true);
			caseNoforSupCourt.setColspan(1);
			caseNoforSupCourt.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(caseNoforSupCourt);

			// datatable.addCell("Header 8");
			Cell amtRecoverForSupCourt = new Cell(new Phrase(
					"Amount to be Recovered ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			amtRecoverForSupCourt.setHeader(true);
			amtRecoverForSupCourt.setColspan(1);
			amtRecoverForSupCourt.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtRecoverForSupCourt);

			Cell latestCaseProcesSupCourt = new Cell(new Phrase(
					"Latest Case Proceeding", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			latestCaseProcesSupCourt.setHeader(true);
			latestCaseProcesSupCourt.setColspan(1);
			latestCaseProcesSupCourt
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(latestCaseProcesSupCourt);

			// datatable.addCell("Header 5");
			Cell currentCaseStatusSupCourt = new Cell(new Phrase(
					"Current Case Status", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			currentCaseStatusSupCourt.setHeader(true);
			currentCaseStatusSupCourt.setColspan(1);
			currentCaseStatusSupCourt.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(currentCaseStatusSupCourt);

			Cell caseNoforHighCourt = new Cell(new Phrase("Case No. ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			caseNoforHighCourt.setHeader(true);
			caseNoforHighCourt.setColspan(1);
			caseNoforHighCourt.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(caseNoforHighCourt);

			// datatable.addCell("Header 8");
			Cell amtRecoverForHighCourt = new Cell(new Phrase(
					"Amount to be Recovered ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			amtRecoverForHighCourt.setHeader(true);
			amtRecoverForHighCourt.setColspan(1);
			amtRecoverForHighCourt.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(amtRecoverForHighCourt);

			// datatable.addCell("Header 9");

			Cell latestCaseProcesHighCourt = new Cell(new Phrase(
					"Latest Case Proceeding", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			latestCaseProcesHighCourt.setHeader(true);
			latestCaseProcesHighCourt.setColspan(1);
			latestCaseProcesHighCourt.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(latestCaseProcesHighCourt);

			// datatable.addCell("Header 5");
			Cell currentCaseStatusHighCourt = new Cell(new Phrase(
					"Current Case Status", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			currentCaseStatusHighCourt.setHeader(true);
			currentCaseStatusHighCourt.setColspan(1);
			currentCaseStatusHighCourt.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(currentCaseStatusHighCourt);

			Cell caseNoforBoardOfRevenue = new Cell(new Phrase("Case No. ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			caseNoforBoardOfRevenue.setHeader(true);
			caseNoforBoardOfRevenue.setColspan(1);
			caseNoforBoardOfRevenue
					.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(caseNoforBoardOfRevenue);

			// datatable.addCell("Header 8");
			Cell amtRecoverForBoardOfRevenue = new Cell(new Phrase(
					"Amount to be Recovered ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			amtRecoverForBoardOfRevenue.setHeader(true);
			amtRecoverForBoardOfRevenue.setColspan(1);
			amtRecoverForBoardOfRevenue.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(amtRecoverForBoardOfRevenue);

			Cell latestCaseProcesBoardOfRevenue = new Cell(new Phrase(
					"Latest Case Proceeding", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			latestCaseProcesBoardOfRevenue.setHeader(true);
			latestCaseProcesBoardOfRevenue.setColspan(1);
			latestCaseProcesBoardOfRevenue.setBackgroundColor(new Color(196,
					255, 255));
			datatable.addCell(latestCaseProcesBoardOfRevenue);

			// datatable.addCell("Header 5");
			Cell currentCaseStatusBoardOfRevenue = new Cell(new Phrase(
					"Current Case Status", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			currentCaseStatusBoardOfRevenue.setHeader(true);
			currentCaseStatusBoardOfRevenue.setColspan(1);
			currentCaseStatusBoardOfRevenue.setBackgroundColor(new Color(196,
					255, 255));
			datatable.addCell(currentCaseStatusBoardOfRevenue);

			Cell caseNoforCommissioner = new Cell(new Phrase("Case No. ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			caseNoforCommissioner.setHeader(true);
			caseNoforCommissioner.setColspan(1);
			caseNoforCommissioner.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(caseNoforCommissioner);

			// datatable.addCell("Header 8");
			Cell amtRecoverForCommissioner = new Cell(new Phrase(
					"Amount to be Recovered ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			amtRecoverForCommissioner.setHeader(true);
			amtRecoverForCommissioner.setColspan(1);
			amtRecoverForCommissioner.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(amtRecoverForCommissioner);

			Cell latestCaseProcesCommissioner = new Cell(new Phrase(
					"Latest Case Proceeding", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			latestCaseProcesCommissioner.setHeader(true);
			latestCaseProcesCommissioner.setColspan(1);
			latestCaseProcesCommissioner.setBackgroundColor(new Color(196, 255,
					255));
			datatable.addCell(latestCaseProcesCommissioner);

			// datatable.addCell("Header 5");
			Cell currentCaseStatusCommissioner = new Cell(new Phrase(
					"Current Case Status", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			currentCaseStatusCommissioner.setHeader(true);
			currentCaseStatusCommissioner.setColspan(1);
			currentCaseStatusCommissioner.setBackgroundColor(new Color(196,
					255, 255));
			datatable.addCell(currentCaseStatusCommissioner);

			datatable.endHeaders();
			// datatable.setDefaultCellBorderWidth(1);

			for (int i = 0; i < reportForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportingDTO reportDTO = new ReportingDTO();
				reportDTO = (ReportingDTO) reportForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values====="
						+ reportForm.getReportList());
				logger.debug("list size========"
						+ reportForm.getReportList().size());
				// datatable.setRight(Element.ALIGN_RIGHT);
				datatable.addCell(String.valueOf(reportDTO.getSno()));
				datatable.addCell(reportDTO.getCasenoSc());
				datatable.addCell(reportDTO.getAmtRecoverSc());
				datatable.addCell(reportDTO.getLatestCaseSc());
				datatable.addCell(reportDTO.getCurrentcaseSc());

				datatable.addCell(reportDTO.getCasenoHc());
				datatable.addCell(reportDTO.getAmtRecoverHc());
				datatable.addCell(reportDTO.getLatestCaseHc());
				datatable.addCell(reportDTO.getCurrentcaseHc());

				datatable.addCell(reportDTO.getCasenoBr());
				datatable.addCell(reportDTO.getAmtRecoverBr());
				datatable.addCell(reportDTO.getLatestCaseBr());
				datatable.addCell(reportDTO.getCurrentcaseBr());

				datatable.addCell(reportDTO.getCasenoC());
				datatable.addCell(reportDTO.getAmtRecoverC());
				datatable.addCell(reportDTO.getLatestCaseC());
				datatable.addCell(reportDTO.getCurrentcaseC());

				datatable.addCell(reportDTO.getRemarks());

			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	/**
	 * ===========================================================================
	 * Method : prepareRevenrecepPdfFile Description : Returns pdf format file
	 * Arguments : form return type : Returns file Author : vengamamba P Created
	 * Date : jun 4, 2008
	 * ===========================================================================
	 */
	public synchronized File prepareRevenrecepPdfFile(ReportingForm reportForm) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);

		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");
			fileName = pr.getValue("REPORT_LOCATION") + "RevRecep"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));

			document.open();

			Table datatable = new Table(4);

			int headerwidths[] = { 20, 70, 50, 50 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);
			datatable.setPadding(3);

			Cell title = new Cell(
					new Phrase(
							"Revenue Receipt And Registered Documents Relating to other local bodies(Govt and Non Govt)",
							FontFactory.getFont(FontFactory.COURIER, 18,
									Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(4);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			// datatable.setDefaultCellBorderWidth(2);
			// 

			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 9, Font.BOLD)));
			slno.setHeader(true);
			slno.setRowspan(2);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell statusDurMonth = new Cell(new Phrase(
					"   Name Of Institutions     ", FontFactory.getFont(
							FontFactory.COURIER, 9, Font.BOLD)));
			statusDurMonth.setHeader(true);
			statusDurMonth.setRowspan(2);
			statusDurMonth.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(statusDurMonth);

			// datatable.addCell("Header 2");
			Cell statusDuringPeriod = new Cell(
					new Phrase(
							"No of Documents Registered & Revenue Receipt in the Month ",
							FontFactory.getFont(FontFactory.COURIER, 9,
									Font.BOLD)));
			statusDuringPeriod.setHeader(true);
			statusDuringPeriod.setColspan(2);
			statusDuringPeriod.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(statusDuringPeriod);

			// datatable.addCell("Header 4");
			Cell annualTarget = new Cell(new Phrase("No of Documents ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			annualTarget.setHeader(true);
			annualTarget.setColspan(1);
			annualTarget.setBackgroundColor(new Color(176, 255, 255));
			datatable.addCell(annualTarget);

			// datatable.addCell("Header 5");
			Cell monthTagRecov = new Cell(new Phrase("Revenue Receipt ",
					FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD)));
			monthTagRecov.setHeader(true);
			monthTagRecov.setColspan(1);
			monthTagRecov.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(monthTagRecov);

			datatable.endHeaders();
			// datatable.setDefaultCellBorderWidth(1);

			for (int i = 0; i < reportForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportingDTO reportDTO = new ReportingDTO();
				reportDTO = (ReportingDTO) reportForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values====="
						+ reportForm.getReportList());
				logger.debug("list size========"
						+ reportForm.getReportList().size());
				// datatable.setRight(Element.ALIGN_RIGHT);
				datatable.addCell(String.valueOf(reportDTO.getSno()));
				datatable.addCell(reportDTO.getNameofint());
				datatable.addCell(reportDTO.getNoofdocs());
				datatable.addCell(reportDTO.getRevrep());
				// datatable.addCell(reportDTO.getRevrep());
				// datatable.addCell(reportDTO.getRevrep());

			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}
	
	//added by SHreeraJ
	public ArrayList getYearForJudicalStamp(int iYear) {

		String str = "";
		ArrayList listRet = new ArrayList();

		ReportDAO dao = new ReportDAO();
		ArrayList list = dao.getCurrentYear();
		if (list != null) {
			if (list.size() == 1) {
				ArrayList ret = (ArrayList) list.get(0);
				str = (String) ret.get(0);

			}
		}
		for (int i = 0; i < iYear; i++) {
			int Year = Integer.parseInt(str) - i;
			ReportingDTO dto = new ReportingDTO();
			int YearPlus = Year + 1;
			dto.setYearValue(""+Year);
			dto.setYear("" + Year+"-"+YearPlus);
			listRet.add(dto);
		}
		return listRet;
	}
	
	
	public int getYearCheck()
	{
		ReportDAO dao = new ReportDAO();
		return Integer.parseInt(dao.getYearCheck());
	}
	
	
	public ArrayList getDistrictDetails(String lang) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			IGRSCommon common = new IGRSCommon();
			ArrayList resultList = common.getDistrict("1",lang);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					reportDTO.setDistrictID((String) list.get(0));
					reportDTO.setDistrictName((String) list.get(1));
					returnList.add(reportDTO);
					logger.debug(reportDTO.getDistrictID() + ":"
							+ reportDTO.getDistrictName());
				}
			}
		} catch (Exception err) {

			logger.debug("In getDistrictDetails Exception " + err);
		}
		return returnList;
	}
	
	
	public ArrayList getRevenueReceiptReport(ReportingDTO dto) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			ArrayList resultList = dao.getRevenueReceiptReport(dto);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					reportDTO.setInstitutionName((String) list.get(0));
					reportDTO.setNoDocMonth((String) list.get(1));
					reportDTO.setRevRcptMonth((String)list.get(2));
					reportDTO.setNoDocPeriod((String)list.get(3));
					reportDTO.setRevRcptPeriod((String)list.get(4));
					returnList.add(reportDTO);
					
				}
			}
		} catch (Exception err) {

			logger.debug("In getRevenueReceiptReport Exception " + err);
		}
		return returnList;
	}
	
	public ArrayList getTotalRevenueReceiptReport(ReportingDTO dto) {
		ReportDAO dao = new ReportDAO();
		return dao.getTotalRevenueReceiptReport(dto);
	}
	
	public ArrayList getSubjectWiseReport(ReportingDTO dto) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			ArrayList resultList = dao.getSubjectWiseReport(dto);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					reportDTO.setExemptionName((String) list.get(0));
					reportDTO.setNoDocMonth((String) list.get(1));
					reportDTO.setExeStampMonth((String)list.get(2));
					reportDTO.setExeRegFeeMonth((String)list.get(3));
					reportDTO.setTotMonth((String)list.get(4));
					reportDTO.setNoDocPeriod((String) list.get(5));
					reportDTO.setExeStampPeriod((String)list.get(6));
					reportDTO.setExeRegFeePeriod((String)list.get(7));
					reportDTO.setTotPeriod((String)list.get(8));
					returnList.add(reportDTO);
					
				}
			}
		} catch (Exception err) {

			logger.debug("In getRevenueReceiptReport Exception " + err);
		}
		return returnList;
	}
	
	public ArrayList getTotalSubjectWiseReport(ReportingDTO dto) {
		ReportDAO dao = new ReportDAO();
		return dao.getTotalSubjectWiseReport(dto);
	}
	
	public ArrayList getCompInfoReport(ReportingDTO dto) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			ArrayList resultList = dao.getCompInfoReport(dto);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					reportDTO.setDistrictName((String) list.get(0));
					reportDTO.setAnnualTarget((String) list.get(1));
					reportDTO.setMonthTarget((String) list.get(2));
					reportDTO.setExeStampMonth((String) list.get(3));
					reportDTO.setExeRegFeeMonth((String) list.get(4));
					reportDTO.setExeTotalMonth((String) list.get(5));
					reportDTO.setExeStampPeriod((String) list.get(6));
					reportDTO.setExeRegFeePeriod((String) list.get(7));
					reportDTO.setExeTotalPeriod((String) list.get(8));
					reportDTO.setExeStampComp(list.get(9).toString().concat("%"));
					reportDTO.setExeRegFeeComp(list.get(10).toString().concat("%"));
					reportDTO.setExeTotalComp( list.get(11).toString().concat("%"));
					reportDTO.setExeStampApr((String) list.get(12));
					reportDTO.setExeRegFeeApr((String) list.get(13));
					reportDTO.setExeTotalApr((String) list.get(14));
					reportDTO.setExeStampPrvApr((String) list.get(15));
					reportDTO.setExeRegFeePrvApr((String) list.get(16));
					reportDTO.setExeTotalPrvApr((String) list.get(17));
					reportDTO.setExeStampComApr(list.get(18).toString().concat("%"));
					reportDTO.setExeRegFeeCompApr( list.get(19).toString().concat("%"));
					reportDTO.setExeTotalCompApr( list.get(20).toString().concat("%"));
					returnList.add(reportDTO);
					
				}
			}
		} catch (Exception err) {

			logger.debug("In getCompInfoReport Exception " + err);
		}
		return returnList;
	}
	
	public ArrayList getNoDocCompInfoReport(ReportingDTO dto) {
		ReportDAO dao = new ReportDAO();
		ArrayList returnList = new ArrayList();
		try {
			ArrayList resultList = dao.getNoDocCompInfoReport(dto);
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					logger.debug("in bd for loop start:-" + resultList.get(i));
					ArrayList list = (ArrayList) resultList.get(i);
					ReportingDTO reportDTO = new ReportingDTO();
					reportDTO.setNoDocRuralMonth((String) list.get(0));
					reportDTO.setNoDocUrbanMonth((String) list.get(1));
					reportDTO.setNoDocTotalMonth((String) list.get(2));
					reportDTO.setNoDocRuralPeriod((String) list.get(3));
					reportDTO.setNoDocUrbanPeriod((String) list.get(4));
					reportDTO.setNoDocTotalPeriod((String) list.get(5));
					reportDTO.setNoDocRuralPeriodApr((String) list.get(6));
					reportDTO.setNoDocUrbanPeriodApr((String) list.get(7));
					reportDTO.setNoDocTotalPeriodApr((String) list.get(8));
					reportDTO.setNoDocRuralPeriodPrv((String) list.get(9));
					reportDTO.setNoDocUrbanPeriodPrv((String) list.get(10));
					reportDTO.setNoDocTotalPeriodPrv((String) list.get(11));
					reportDTO.setNoDocRuralPeriodComp(list.get(12).toString().concat("%"));
					reportDTO.setNoDocUrbanPeriodComp(list.get(13).toString().concat("%"));
					reportDTO.setNoDocTotalPeriodComp(list.get(14).toString().concat("%"));
				
					returnList.add(reportDTO);
					
				}
			}
		} catch (Exception err) {

			logger.debug("In getCompInfoReport Exception " + err);
		}
		return returnList;
	}
}


