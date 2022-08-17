package com.wipro.igrs.AadharEsign.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.wipro.igrs.cybertehsil.constant.FormVIAConstant;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.cybertehsil.forms.dto.DeclarationDTO;
import com.wipro.igrs.cybertehsil.forms.dto.FormUserEnterableDTO;
import com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.constant.RegInitConstant;

public class GenerateFormVIAENGHINDI {
	static private Logger logger = (Logger) Logger.getLogger(GenerateFormVIAENGHINDI.class);
	static PropertiesFileReader pr = null;

	// public static final String FONT =
	// "C:/Users/AN350799/shared2/font/ARIALUNI.ttf";
	public static String FONT = null;
	// static Font f = FontFactory.getFont(FONT, BaseFont.IDENTITY_H,
	// BaseFont.EMBEDDED);
	// private static String FILE = "c:/temp/FirstForm6PdfGraphics.pdf";
	// static private String lang = "en";

	static java.awt.Font hindiFont;
	static java.awt.Font hindiFontBold;

	Document document = new Document();
	PdfTemplate pdt = null;
	PdfContentByte cb = null;
	PdfWriter writer = null;
	Graphics2D g2 = null;
	float pageWidth = PageSize.A4.getWidth();
	int currPos;
	float pageHeight = PageSize.A4.getHeight();
	int w = (int) pageWidth;
	int h = (int) pageHeight;

	boolean isBottom = true;

	// public static void main(String[] args) throws Exception {
	public boolean generateFormVIAPDF(String regTxnId, String lang, String FILE) {
		boolean ret = true;

		RegCommonBD bd = new RegCommonBD();
		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
			FONT = pr.getValue("dms_hindi_font_path");

			// String regTxnId = "280222000001";//"250222000001";
			String[] fieldId = {"1", "2", "5"};
			String formName = "FORM_VIA";

			hindiFont = java.awt.Font.createFont(0, new FileInputStream(FONT));
			hindiFontBold = java.awt.Font.createFont(0, new FileInputStream(FONT));

			hindiFontBold = hindiFont.deriveFont(17.0F);

			File pdffile = new File(FILE);
			// Document document = new Document(PageSize.A4);
			document.addAuthor("WIPRO");
			document.addTitle("FORM_A1");

			FileOutputStream fileout = new FileOutputStream(pdffile);
			writer = PdfWriter.getInstance(document, fileout);
			document.open();
			cb = writer.getDirectContent();
			pdt = cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
			// cb.saveState();
			float pageWidth = PageSize.A4.getWidth();

			float pageHeight = PageSize.A4.getHeight();
			w = (int) pageWidth - 50;
			h = (int) pageHeight;

			System.out.println(w + "!!!!" + h);
			// Graphics2D g2 = cb.createGraphicsShapes(w, h);
			g2 = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
			cb.addTemplate(pdt, 5, -7);


			
			
			int centerOfPage = w / 2;

			String content = (lang.equals("en")) ? FormVIAConstant.HN_HEADING_1 : FormVIAConstant.HN_HEADING_1;
			int width = (content).length();
			int centerPosition = centerOfPage - width / 2;
			hindiFontBold = hindiFontBold.deriveFont(Font.BOLD, 17.0F);
			g2.setFont(hindiFontBold);
			currPos = drawString(g2, content, centerPosition, (currPos+(int) (5D * h) / 100), w);

			content = (lang.equals("en")) ? FormVIAConstant.HN_HEADING_2 : FormVIAConstant.HN_HEADING_2;
			width = (content).length();
			centerPosition = centerOfPage + width / 2;
			if (checkForNewPage(currPos, h, 150)) {
				
					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(w, h);
					hindiFont = hindiFont.deriveFont(12.0F);
					currPos = (int) (5D * h) / 100;
					//currPos = y;
					g2.setFont(hindiFont);
				
			}
			hindiFont = hindiFont.deriveFont(8.0F);
			g2.setFont(hindiFont);
			// g2.getFontMetrics().
			currPos = drawString(g2, content, centerPosition, (currPos + (int) (1.5D * h) / 100), w);

			if (checkForNewPage(currPos, h, 150)) {
				
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(12.0F);
				currPos = (int) (5D * h) / 100;
				//currPos = y;
				g2.setFont(hindiFont);
			
		}
			content = (lang.equals("en")) ? FormVIAConstant.HN_HEADING_3 : FormVIAConstant.HN_HEADING_3;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			// System.out.println(centerPosition+" ------ "+width);
			hindiFontBold = hindiFontBold.deriveFont(Font.BOLD, 10.0F);
			g2.setFont(hindiFontBold);
			currPos = drawString(g2, content, 160, (currPos + (int) (2D * h) / 100), w);
			if (checkForNewPage(currPos, h, 150)) {
				
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(12.0F);
				currPos = (int) (5D * h) / 100;
				//currPos = y;
				g2.setFont(hindiFont);
			
		}
			content = (lang.equals("en")) ? FormVIAConstant.HN_HEADING_4 : FormVIAConstant.HN_HEADING_4;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			hindiFontBold = hindiFontBold.deriveFont(Font.BOLD, 10.0F);
			// hindiFont =hindiFont.deriveFont(Font.BOLD);
			g2.setFont(hindiFontBold);
			System.out.println(centerPosition + " ------ " + width);
			currPos = drawString(g2, content, centerPosition - 20, (currPos + (int) (2D * h) / 100), w);
			if (checkForNewPage(currPos, h, 150)) {
				
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(12.0F);
				currPos = (int) (5D * h) / 100;
				//currPos = y;
				g2.setFont(hindiFont);
			
		}
			content = (lang.equals("en")) ? FormVIAConstant.HN_HEADING_1_1 : FormVIAConstant.HN_HEADING_1_1;
			width = (content).length();
			//int centerPosition = centerOfPage - width / 2;
			hindiFontBold = hindiFontBold.deriveFont(Font.BOLD, 10.0F);
			g2.setFont(hindiFontBold);
			currPos = drawString(g2, content, 40, (currPos+(int) (5D * h) / 100), w);
			if (checkForNewPage(currPos, h, 150)) {
				
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(12.0F);
				currPos = (int) (5D * h) / 100;
				//currPos = y;
				g2.setFont(hindiFont);
			
		}
			 content = (lang.equals("en")) ? FormVIAConstant.HN_HEADING_1_2 : FormVIAConstant.HN_HEADING_1_2;
			 width = (content).length();
			//int centerPosition = centerOfPage - width / 2;
			hindiFontBold = hindiFontBold.deriveFont(Font.BOLD, 10.0F);
			g2.setFont(hindiFontBold);
			currPos = drawString(g2, content, 70, (currPos+(int) (5D * h) / 100), w);
			if (checkForNewPage(currPos, h, 150)) {
				
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(12.0F);
				currPos = (int) (5D * h) / 100;
				//currPos = y;
				g2.setFont(hindiFont);
			
		}
			content = (lang.equals("en")) ? FormVIAConstant.HN_HEADING_5 : FormVIAConstant.HN_HEADING_5;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			hindiFont = hindiFont.deriveFont(10.0F);
			// hindiFont =hindiFont.deriveFont(Font.BOLD);
			g2.setFont(hindiFont);
			System.out.println(centerPosition + " ------ " + width);
			currPos = drawString(g2, content, 50, (currPos + (int) (2D * h) / 100), w);

			ArrayList<PartyDetailsDTO> partyList = bd.getFormVIPartyDetails(regTxnId, fieldId);

			ArrayList<DeclarationDTO> decDtoList = bd.getFormVIDetails(regTxnId, formName);

			if (decDtoList != null) {
				int padTab = 4;
				centerPosition = 50;
				for (int i = 0; i < decDtoList.size(); i++) {

					DeclarationDTO dto = (DeclarationDTO) decDtoList.get(i);
					String decId = dto.getDeclarationId();
					String subDecId = dto.getDeclarationSubId();
					String userEnterables = "";
					if ("1".equals(decId) && "1.1".equals(subDecId)) {
						content = "मैं/हम   ";

						currPos = drawString(g2, content, centerPosition, (currPos + (int) (2D * h) / 100), w - 25);
						currPos = (currPos + (int) (0.5D * h) / 100);
						String columnNameEN[] = {"नाम ", "माता/पिता/पति का नाम / संरक्षक का नाम ", "पूर्ण पता", "ई-मेल पता ", "मोबाईल फोन नम्बर", "*पहचान पत्र का नाम और उसका नम्बर"};
						String columnNameHN[] = {"नाम ", "माता/पिता/पति का नाम /संरक्षक का नाम", "पूर्ण पता", "ई-मेल पता ", "मोबाईल फोन नम्बर", "*पहचान पत्र का नाम और उसका नम्बर"};
						String columnName[] = (lang.equals("en")) ? columnNameEN : columnNameHN;

						int[] tempPosition = new int[6];

						hindiFontBold = hindiFontBold.deriveFont(Font.BOLD, 10.0F);
						g2.setFont(hindiFontBold);

						g2.drawLine(50, (currPos + (int) (.5D * h) / 100), 540, (currPos + (int) (.5D * h) / 100));
						int upperLen = currPos + (int) (.5D * h) / 100;

						tempPosition[0] = drawString(g2, columnName[0], 50 + padTab, (currPos + (int) (2D * h) / 100), 100 - padTab);

						tempPosition[1] = drawString(g2, columnName[1], 150 + padTab, (currPos + (int) (2D * h) / 100), 75 - padTab);

						tempPosition[2] = drawString(g2, columnName[2], 225 + padTab, (currPos + (int) (2D * h) / 100), 75 - padTab);

						tempPosition[3] = drawString(g2, columnName[3], 300 + padTab, (currPos + (int) (2D * h) / 100), 80 - padTab);

						tempPosition[4] = drawString(g2, columnName[4], 380 + padTab, (currPos + (int) (2D * h) / 100), 70 - padTab);

						tempPosition[5] = drawString(g2, columnName[5], 450 + padTab, (currPos + (int) (2D * h) / 100), 90 - padTab);

						// currPos=tempPosition[5];
						int finalPos = sortingStringArray(tempPosition);
						currPos = finalPos;
						hindiFont = hindiFont.deriveFont(10.0F);
						g2.setFont(hindiFont);

						g2.drawLine(50, (finalPos), 540, (finalPos));
						if (checkForNewPage(currPos, h, 150)) {
							
							g2.dispose();
							document.newPage();
							cb = writer.getDirectContent();
							g2 = cb.createGraphicsShapes(w, h);
							hindiFont = hindiFont.deriveFont(12.0F);
							currPos = (int) (5D * h) / 100;
							//currPos = y;
							g2.setFont(hindiFont);
						
					}
						for (int p = 0; p < partyList.size(); p++) {
							PartyDetailsDTO partyDto = (PartyDetailsDTO) partyList.get(p);
							currPos = currPos + (int) (1.5D * h) / 100;

							tempPosition = new int[6];

							tempPosition[0] = drawString(g2, partyDto.getPartyName(), 50 + padTab, (currPos + (int) (2D * h) / 100), 100 - padTab);

							tempPosition[1] = drawString(g2, ((lang.equals("en")) ? partyDto.getRelationType() : partyDto.getRelationTypeHindi()) + " " + partyDto.getGuardianName(), 150 + padTab, (currPos + (int) (2D * h) / 100), 75 - padTab);

							tempPosition[2] = drawString(g2, partyDto.getPartyAdress(), 225 + padTab, (currPos + (int) (2D * h) / 100), 75 - padTab);

							tempPosition[3] = drawString(g2, partyDto.getEmailId(), 300 + padTab, (currPos + (int) (2D * h) / 100), 80 - padTab);

							tempPosition[4] = drawString(g2, partyDto.getMobileNo(), 380 + padTab, (currPos + (int) (2D * h) / 100), 70 - padTab);

							tempPosition[5] = drawString(g2, ((lang.equals("en")) ? partyDto.getIdProofType() : partyDto.getIdProofTypeHindi()) + ":" + partyDto.getIdProof(), 450 + padTab, (currPos + (int) (2D * h) / 100), 90 - padTab);

							currPos = sortingStringArray(tempPosition);

							int tempPos = currPos + (int) (.5D * h) / 100;
							g2.drawLine(50, tempPos, 540, tempPos);
							g2.drawLine(50, tempPos, 50, upperLen);
							g2.drawLine(150, tempPos, 150, upperLen);
							g2.drawLine(225, tempPos, 225, upperLen);
							g2.drawLine(300, tempPos, 300, upperLen);
							g2.drawLine(380, tempPos, 380, upperLen);
							g2.drawLine(450, tempPos, 450, upperLen);
							g2.drawLine(540, tempPos, 540, upperLen);

							currPos = currPos + (int) (1.5D * h) / 100;

							/*
							 * if (currPos >= (h - 50)) {
							 * System.out.println("Stopped printing after -- " +
							 * (i + 1) + " pages"); g2.dispose();
							 * document.newPage(); cb =
							 * writer.getDirectContent(); g2 =
							 * cb.createGraphicsShapes(w, h); hindiFont =
							 * hindiFont.deriveFont(10.0F); currPos = (int) (5D
							 * * h) / 100; g2.setFont(hindiFont); }
							 */
							if (checkForNewPage(currPos, h, 150)) {
								
								g2.dispose();
								document.newPage();
								cb = writer.getDirectContent();
								g2 = cb.createGraphicsShapes(w, h);
								hindiFont = hindiFont.deriveFont(12.0F);
								currPos = (int) (5D * h) / 100;
								//currPos = y;
								g2.setFont(hindiFont);
							
						}
						}
						if (checkForNewPage(currPos, h, 150)) {
							
							g2.dispose();
							document.newPage();
							cb = writer.getDirectContent();
							g2 = cb.createGraphicsShapes(w, h);
							hindiFont = hindiFont.deriveFont(12.0F);
							currPos = (int) (5D * h) / 100;
							//currPos = y;
							g2.setFont(hindiFont);
						
					}
						content = (lang.equals("en")) ? FormVIAConstant.TABLE_1_FOOTER : FormVIAConstant.TABLE_1_FOOTER;
						 width = (content).length();
						//int centerPosition = centerOfPage - width / 2;
						hindiFontBold = hindiFontBold.deriveFont(Font.BOLD, 12.0F);
						g2.setFont(hindiFontBold);
						currPos = drawString(g2, content, 70, (currPos+(int) (1D * h) / 100), w);
					}

					decId = decId + ". " + ((lang.equals("en")) ? dto.getDeclarationNameHindi() : dto.getDeclarationNameHindi());
					// currPos = drawString(g2, decId, 50, (currPos + (int) (2D
					// * h) / 100), w-25);

					ArrayList<FormUserEnterableDTO> userDtoList = dto.getUserEnterableData();

					System.out.println("userDtoList: " + (userDtoList != null ? userDtoList.size() : "user enterable null"));
					if ("Y".equalsIgnoreCase(dto.getUserEntrableFlag())) {
						String[] header = null;
						ArrayList<String> col1 = null;
						ArrayList<String> col2 = null;
						ArrayList<String> col3 = null;
						if ("6.2".equals(dto.getDeclarationSubId())) {
							header = new String[3];

							col1 = new ArrayList<String>();
							col2 = new ArrayList<String>();
							col3 = new ArrayList<String>();

						}

						if (userDtoList != null && userDtoList.size() > 0) {
							// int tempId = 0;

							for (int j = 0; j < userDtoList.size(); j++) {
								FormUserEnterableDTO userDto = userDtoList.get(j);
								int userTxnId = Integer.parseInt(userDto.getUserEnterableTxnId());
								if ("6.2".equals(subDecId)) {
									// if(tempId == userTxnId) {
									if ("2004".equalsIgnoreCase(userDto.getUserEnterableId())) {
										header[0] = (lang.equals("en")) ? userDto.getUserEnterableFieldNameHindi() : "  " + userDto.getUserEnterableFieldNameHindi();
										col1.add(userDto.getValueEntered());

									}
									if ("2005".equalsIgnoreCase(userDto.getUserEnterableId())) {
										header[1] = (lang.equals("en")) ? userDto.getUserEnterableFieldNameHindi() : "  " + userDto.getUserEnterableFieldNameHindi();
										col2.add(userDto.getValueEntered());

									}
									if ("2006".equalsIgnoreCase(userDto.getUserEnterableId())) {
										header[2] = (lang.equals("en")) ? userDto.getUserEnterableFieldNameHindi() : "  " + userDto.getUserEnterableFieldNameHindi();
										col3.add(userDto.getValueEntered());

									}

								} else {

									// decId = decId
									// +(((lang=="en")?"  "+userDto.getUserEnterableFieldNameEnglish():"  "+userDto.getUserEnterableFieldNameHindi())+"  "+userDto.getValueEntered());
									userEnterables = userEnterables + (((lang.equals("en")) ? "  " + userDto.getUserEnterableFieldNameHindi() : "  " + userDto.getUserEnterableFieldNameHindi()) + "  " + userDto.getValueEntered());
								}

							}

						}
						if (checkForNewPage(currPos, h, 150)) {
							
							g2.dispose();
							document.newPage();
							cb = writer.getDirectContent();
							g2 = cb.createGraphicsShapes(w, h);
							hindiFont = hindiFont.deriveFont(12.0F);
							currPos = (int) (5D * h) / 100;
							//currPos = y;
							g2.setFont(hindiFont);
						
					}
						hindiFont = hindiFont.deriveFont(12.0F);
						g2.setFont(hindiFont);
						currPos = drawString(g2, decId, 50, (currPos + (int) (2D * h) / 100), w - 25);
						if (checkForNewPage(currPos, h, 150)) {
							
							g2.dispose();
							document.newPage();
							cb = writer.getDirectContent();
							g2 = cb.createGraphicsShapes(w, h);
							hindiFont = hindiFont.deriveFont(12.0F);
							currPos = (int) (5D * h) / 100;
							//currPos = y;
							g2.setFont(hindiFont);
						
					}
						hindiFontBold = hindiFontBold.deriveFont(Font.BOLD, 12.0F);
						g2.setFont(hindiFontBold);
						currPos = drawString(g2, userEnterables, 50, (currPos + (int) (2D * h) / 100), w - 25);

						/*
						 * if (currPos >= (h - 50)) {
						 * System.out.println("Stopped printing after -- " + (i
						 * + 1) + " pages"); g2.dispose(); document.newPage();
						 * cb = writer.getDirectContent(); g2 =
						 * cb.createGraphicsShapes(w, h); hindiFont =
						 * hindiFont.deriveFont(10.0F); currPos = (int) (5D * h)
						 * / 100; g2.setFont(hindiFont); }
						 */

						if ("6.2".equals(dto.getDeclarationSubId())) {
							int tempPos = currPos + (int) (.5D * h) / 100;
						/*	if (checkForNewPage(currPos, h, 150)) {
								//	currPos = sortingStringArray(tempPosition);
									

									g2.drawLine(50, tempPos, 550, tempPos);
									g2.drawLine(50, tempPos, 50, currPos);
									g2.drawLine(150, tempPos, 150, currPos);
									g2.drawLine(250, tempPos, 250, currPos);
									g2.drawLine(550, tempPos, 550, currPos);
									g2.drawLine(50, currPos, 550, currPos);
									
								}*/
							hindiFontBold = hindiFontBold.deriveFont(Font.BOLD, 10.0F);
							g2.setFont(hindiFontBold);
							if (checkForNewPage(currPos, h, 150)) {
								
								g2.dispose();
								document.newPage();
								cb = writer.getDirectContent();
								g2 = cb.createGraphicsShapes(w, h);
								hindiFont = hindiFont.deriveFont(12.0F);
								currPos = (int) (5D * h) / 100;
								//currPos = y;
								g2.setFont(hindiFont);
							
						}
							//g2.drawLine(50, (currPos + (int) (.5D * h) / 100), 550, (currPos + (int) (.5D * h) / 100));
							int upperLen = currPos + (int) (.5D * h) / 100;
							//tempPos = currPos + (int) (.5D * h) / 100;
							int[] tempPosition = new int[3];
							g2.drawLine(50, (currPos + (int) (.5D * h) / 100), 550, (currPos + (int) (.5D * h) / 100));
							tempPosition[0] = drawString(g2, header[0], 50 + padTab, (currPos + (int) (2D * h) / 100), 100 - padTab);

							tempPosition[1] = drawString(g2, header[1], 150 + padTab, (currPos + (int) (2D * h) / 100), 100 - padTab);

							tempPosition[2] = drawString(g2, header[2], 250 + padTab, (currPos + (int) (2D * h) / 100), 300 - padTab);
							
							
							int finalPos = sortingStringArray(tempPosition);
							currPos = finalPos;
							tempPos=currPos;
							hindiFont = hindiFont.deriveFont(10.0F);
							g2.setFont(hindiFont);
							g2.drawLine(50, (currPos + (int) (.5D * h) / 100), 550, (currPos + (int) (.5D * h) / 100));
							if (checkForNewPage(currPos, h, 150)) {
								//	currPos = sortingStringArray(tempPosition);
									System.out.println("creating lines... 1"+currPos);

									//g2.drawLine(50, upperLen, 550, upperLen);
									g2.drawLine(50, upperLen, 50, currPos);
									g2.drawLine(150, upperLen, 150, currPos);
									g2.drawLine(250, upperLen, 250, currPos);
									g2.drawLine(550, upperLen, 550, currPos);
									//g2.drawLine(50, currPos, 550, currPos);
									g2.dispose();
									document.newPage();
									cb = writer.getDirectContent();
									g2 = cb.createGraphicsShapes(w, h);
									hindiFont = hindiFont.deriveFont(12.0F);
									currPos = (int) (1D * h) / 100;
									upperLen=currPos;
									System.out.println("creating lines... 2"+currPos);
									g2.setFont(hindiFont);
								}
							/*
							 * if (currPos >= (h - 50)) {
							 * System.out.println("Stopped printing after -- " +
							 * (i + 1) + " pages"); g2.dispose();
							 * document.newPage(); cb =
							 * writer.getDirectContent(); g2 =
							 * cb.createGraphicsShapes(w, h); hindiFont =
							 * hindiFont.deriveFont(10.0F); currPos = (int) (5D
							 * * h) / 100; g2.setFont(hindiFont); }
							 */
							//g2.drawLine(50, (finalPos), 550, (finalPos));

							for (int j = 0; j < col1.size(); j++) {
								if (checkForNewPage(currPos, h, 150)) {
									//	currPos = sortingStringArray(tempPosition);
										
										System.out.println("creating lines... 3"+currPos);
										//g2.drawLine(50, tempPos, 550, tempPos);
										g2.drawLine(50, tempPos, 50, currPos);
										g2.drawLine(150, tempPos, 150, currPos);
										g2.drawLine(250, tempPos, 250, currPos);
										g2.drawLine(550, tempPos, 550, currPos);
										//g2.drawLine(50, currPos, 550, currPos);
										g2.dispose();
										document.newPage();
										cb = writer.getDirectContent();
										g2 = cb.createGraphicsShapes(w, h);
										hindiFont = hindiFont.deriveFont(12.0F);
										currPos = (int) (5D * h) / 100;
										upperLen=currPos;
										System.out.println("creating lines... 4"+currPos);
										g2.setFont(hindiFont);
									}
								currPos = currPos + (int) (1.5D * h) / 100;
								tempPosition = new int[3];

								tempPosition[0] = drawString(g2, col1.get(j), 50 + padTab, (currPos + (int) (2D * h) / 100), 100 - padTab);

								tempPosition[1] = drawString(g2, col2.get(j), 150 + padTab, (currPos + (int) (2D * h) / 100), 100 - padTab);

								tempPosition[2] = drawString(g2, col3.get(j), 250 + padTab, (currPos + (int) (2D * h) / 100), 300 - padTab);

								currPos = sortingStringArray(tempPosition);
								 tempPos = currPos + (int) (.5D * h) / 100;
								//currPos = currPos + (int) (1.5D * h) / 100;

								/*
								 * if (currPos >= (h - 50)) {
								 * System.out.println(
								 * "Stopped printing after -- " + (j + 1) +
								 * " pages"); g2.dispose(); document.newPage();
								 * cb = writer.getDirectContent(); g2 =
								 * cb.createGraphicsShapes(w, h); hindiFont =
								 * hindiFont.deriveFont(10.0F); currPos = (int)
								 * (5D * h) / 100; g2.setFont(hindiFont); }
								 */

							}


							g2.drawLine(50, tempPos, 550, tempPos);
							g2.drawLine(50, tempPos, 50, upperLen);
							g2.drawLine(150, tempPos, 150, upperLen);
							g2.drawLine(250, tempPos, 250, upperLen);
							g2.drawLine(550, tempPos, 550, upperLen);


						}
					} else {
						if (checkForNewPage(currPos, h, 150)) {
							
							g2.dispose();
							document.newPage();
							cb = writer.getDirectContent();
							g2 = cb.createGraphicsShapes(w, h);
							hindiFont = hindiFont.deriveFont(12.0F);
							currPos = (int) (5D * h) / 100;
							//currPos = y;
							g2.setFont(hindiFont);
						
					}
						hindiFont = hindiFont.deriveFont(12.0F);
						g2.setFont(hindiFont);
						// decId = decId
						// +(((lang=="en")?"  "+userDto.getUserEnterableFieldNameEnglish():"  "+userDto.getUserEnterableFieldNameHindi())+"  "+userDto.getValueEntered());
						currPos = drawString(g2, decId, 50, (currPos + (int) (2D * h) / 100), w - 25);

						/*
						 * if (currPos >= (h - 50)) {
						 * System.out.println("Stopped printing after -- " + (i
						 * + 1) + " pages"); g2.dispose(); document.newPage();
						 * cb = writer.getDirectContent(); g2 =
						 * cb.createGraphicsShapes(w, h); hindiFont =
						 * hindiFont.deriveFont(10.0F); currPos = (int) (5D * h)
						 * / 100; g2.setFont(hindiFont); }
						 */

					}
					//currPos = drawString(g2, " ", 50, (currPos + (int) (2D * h) / 100), w - 25);
					if(i==9){
						System.out.println("Last curr pos --> "+currPos);
					}
					// document.add(new Paragraph(" "));
				}
			}

			hindiFont = hindiFont.deriveFont(12.0F);
			g2.setFont(hindiFont);
			ArrayList bottomData = bd.getPropDistrict(regTxnId, lang);
			ArrayList declaration = bd.getDeclaration(regTxnId, "A1");
			if (checkForNewPage(currPos, h, 50)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(12.0F);
				currPos = (int) (5D * h) / 100;
				g2.setFont(hindiFont);
			}

			String con1 = ((String) declaration.get(0)) == null ? "" : (String) declaration.get(0);
			String con2 = ((String) declaration.get(1)) == null ? "" : (String) declaration.get(1);
			//String userName = bd.getUserDetailWithRegid(regTxnId);

			/*if (con1.equalsIgnoreCase("Y")) {
				
				content = (lang.equals("en")) ? FormVIAConstant.HN_PARTY_DECLARATION : FormVIAConstant.HN_PARTY_DECLARATION;
				currPos = drawString(g2, content, 50, (currPos + (int) (2D * h) / 100), w - 25);
			}
			if (con2.equalsIgnoreCase("Y")) {
				content = (lang.equals("en")) ? FormVIAConstant.HN_SP_RU_DECLARATION_1 + " " + userName + FormVIAConstant.HN_SP_RU_DECLARATION_2 : FormVIAConstant.HN_SP_RU_DECLARATION_1 + " " + userName + FormVIAConstant.HN_SP_RU_DECLARATION_2;
				currPos = drawString(g2, content, 50, (currPos + (int) (2D * h) / 100), w - 25);
			}*/
			if (checkForNewPage(currPos, h, 150)) {
				
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(12.0F);
				currPos = (int) (5D * h) / 100;
				//currPos = y;
				g2.setFont(hindiFont);
			
		}
			content = (lang.equals("en")) ? FormVIAConstant.HN_DATE + " - " + bottomData.get(0) : FormVIAConstant.HN_DATE + " - " + bottomData.get(0);
			currPos = drawString(g2, content, 50, (currPos + (int) (2D * h) / 100), w - 25);
			if (checkForNewPage(currPos, h, 150)) {
				
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(12.0F);
				currPos = (int) (5D * h) / 100;
				//currPos = y;
				g2.setFont(hindiFont);
			
		}
			content = (lang.equals("en")) ? FormVIAConstant.HN_PLACE + " - " + bottomData.get(1) : FormVIAConstant.HN_PLACE + " - " + bottomData.get(2);
			currPos = drawString(g2, content, 50, (currPos + (int) (2D * h) / 100), w - 25);

			int XLenght = 74;
			int XPadSig = 25;

			// g2.drawLine(25, (800), 550, (800));

			/*
			 * int [] tempPosition=new int[6]; int
			 * upperLen=800+(int)(.5D*h)/100;
			 * 
			 * for(int p=0;p<partyList.size();p++) { PartyDetailsDTO partyDto =
			 * (PartyDetailsDTO) partyList.get(p); isBottom =false; hindiFont
			 * =hindiFont.deriveFont(8.0F); g2.setFont(hindiFont);
			 * 
			 * tempPosition[p] = drawString(g2, partyDto.getPartyName(),
			 * XPadSig+(XLenght*p), 800+ (int) (2D * h) / 100, XLenght);
			 * 
			 * }
			 * 
			 * currPos=sortingStringArray(tempPosition); for(int i=0;i<6;i++) {
			 * g2.drawLine(XPadSig+(XLenght*i), currPos, XPadSig+(XLenght*i),
			 * upperLen); }
			 * 
			 * 
			 * g2.drawLine(25, currPos, 550, currPos);
			 */

			/*
			 * if (currPos >= (h - 50)) { g2.dispose(); document.newPage(); cb =
			 * writer.getDirectContent(); g2 = cb.createGraphicsShapes(w, h);
			 * hindiFont = hindiFont.deriveFont(12.0F); currPos = (int) (5D * h)
			 * / 100; g2.setFont(hindiFont); }
			 */
			isBottom = true;

			g2.dispose();
			document.close();

		} catch (Exception e) {
			logger.debug(e.getMessage());
			ret = false;
		}
		if (ret) {
			try {
				ret = bd.updateFormPath(regTxnId, "A1", FILE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ret = false;
				e.printStackTrace();
			}
		}
		return ret;
	}

	public boolean checkForNewPage(int curPos, int h, int size) {
		//System.out.println(curPos+"--"+h+"---"+size);
		boolean result = false;
		if ((h - size) < curPos) {
			result = true;
		}
		return result;
	}
	public int sortingStringArray(int[] arr) {
		int len = arr.length;
		Arrays.sort(arr);
		return (arr[len - 1]);
	}
	private int drawString(Graphics g, String s, int x, int y, int width) {
		// check for new page
		if (checkForNewPage(currPos, h, 150)) {
			if (y > (h - 150)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(12.0F);
				y = (int) (5D * h) / 100;
				currPos = y;
				g2.setFont(hindiFont);
			}
		}

		FontMetrics fm = g.getFontMetrics();
		int lineHeight = fm.getHeight();
		boolean underlined = false;
		int curX = x;
		int curY = y;
		int chck = 680;
		int count = 1;
		boolean bigWord = false;
		if (s != null) {
			String[] words = s.split(" ");
			int firstword = 1;
			for (String word : words) {

				bigWord = false;

				int wordWidth = fm.stringWidth(word + " ");

				int subIndex = width / 4 - 3;
				if (word.equalsIgnoreCase("<ULine>")) {
					underlined = true;
				} else if (word.equalsIgnoreCase("</ULine>")) {
					underlined = false;
				} else {
					if (curX + wordWidth >= x + width) {
						if (wordWidth > width) {
							curX = x;
							if (firstword == 1) {
								curY += 0;
								firstword++;
							} else {
								curY += lineHeight;
							}
							bigWord = true;

							int indexofcomma = 0;
							int tempLength = word.length();
							StringBuffer sbrtmp = new StringBuffer(word);
							int tmpWordWidth = fm.stringWidth(sbrtmp.toString());
							while (tmpWordWidth > width) {

								try {
									String temp = sbrtmp.substring(0, subIndex);

									tmpWordWidth = fm.stringWidth(temp + "-");
									if (underlined) {

										g.drawString(temp + "-", curX, curY);

										g.drawLine(curX, curY + 2, curX + tmpWordWidth, curY + 2);
									} else {
										g.drawString(temp + "-", curX, curY);
									}
								} catch (StringIndexOutOfBoundsException e) {
									String temp = sbrtmp.substring(0, subIndex - 2);

									tmpWordWidth = fm.stringWidth(temp);
									if (underlined) {
										g.drawString(temp + "-", curX, curY);

										g.drawLine(curX, curY + 2, curX + tmpWordWidth, curY + 2);
									} else {
										g.drawString(temp + "-", curX, curY);
									}
								}
								curY += lineHeight;
								curX = x;

								sbrtmp.delete(0, subIndex);

								tmpWordWidth = fm.stringWidth(sbrtmp.toString());

							}
							if (sbrtmp.length() > 0) {
								tmpWordWidth = fm.stringWidth(sbrtmp.toString() + " ");
								if (underlined) {
									g.drawString(sbrtmp.toString() + " ", curX, curY);
									g.drawLine(curX, curY + 2, curX + tmpWordWidth, curY + 2);
								} else {
									g.drawString(sbrtmp.toString() + " ", curX, curY);
								}
								curX += tmpWordWidth;
							}
						} else {
							curY += lineHeight;
							curX = x;
							firstword++;
						}
					} else if (word.equalsIgnoreCase("\n")) {
						curY += lineHeight;
						curX = x - 4;
					}
					if ((underlined) && (!bigWord)) {
						g.drawString(word, curX, curY);
						g.drawLine(curX, curY + 2, curX + wordWidth, curY + 2);
						firstword++;
					} else if (!bigWord) {
						g.drawString(word, curX, curY);
						firstword++;
					}
					if (!bigWord) {
						curX += wordWidth;
						firstword++;
					}
				}

			}
		}
		return curY;
	}
}
