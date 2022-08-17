package com.wipro.igrs.clr.services;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.cybertehsil.constant.FormVIAConstant;
import com.wipro.igrs.cybertehsil.forms.dto.PropDetailDto;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.dto.PartyDetailsDTO;

public class FormVIBPdfGeneration {

	String regTxnId;
	PropertiesFileReader pr = null;

	java.awt.Font hindiFont;
	// java.awt.Font hindiFont;

	public boolean generationFormVIB(String regId, String language, String formA2Path) throws Exception {
		boolean bool = true;
		RegCommonBO bo = new RegCommonBO();
		try {
			this.regTxnId = regId;
			pr = PropertiesFileReader.getInstance("resources.igrs");
			String fontFilePath = pr.getValue("dms_hindi_font_path");
			hindiFont = java.awt.Font.createFont(0, new FileInputStream(fontFilePath));
			File pdffile = new File(formA2Path);
			Document document = new Document(PageSize.A4);

			hindiFont = hindiFont.deriveFont(17.0F);

			FileOutputStream fileout = new FileOutputStream(pdffile);
			PdfWriter writer = PdfWriter.getInstance(document, fileout);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			if (null == language) {
				language = "en";
			}
			float pageWidth = PageSize.A4.getWidth();

			float pageHeight = PageSize.A4.getHeight();
			int w = (int) pageWidth - 20;
			int h = (int) pageHeight;

			System.out.println(w + "!!!!" + h);
			Graphics2D g2 = cb.createGraphicsShapes(w, h);
			document.addAuthor("Saurav");
			document.addTitle("FORMVIB");
			hindiFont = hindiFont.deriveFont(Font.BOLD, 17.0F);

			int centerOfPage = w / 2;
			String content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_1_hi : FORMVIBConstant.header_1_hi;
			int width = (content).length();
			int centerPosition = centerOfPage - width / 2;
			g2.setFont(hindiFont);
			int currPos = drawString(g2, content, centerPosition - 50, (int) (5D * h) / 100, w);
			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_2_hi : FORMVIBConstant.header_2_hi;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			hindiFont = hindiFont.deriveFont(Font.BOLD, 12.0F);
			g2.setFont(hindiFont);
			currPos = drawString(g2, content, centerPosition - 45, (currPos + (int) (1.5D * h) / 100), w);

			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_3_hi : FORMVIBConstant.header_3_hi;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			hindiFont = hindiFont.deriveFont(Font.BOLD, 10.0F);
			g2.setFont(hindiFont);
			currPos = drawString(g2, content, centerPosition - 120, (currPos + (int) (2D * h) / 100), w);

			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_4_hi : FORMVIBConstant.header_4_hi;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			hindiFont = hindiFont.deriveFont(Font.BOLD, 10.0F);
			g2.setFont(hindiFont);
			System.out.println(centerPosition + " ------ " + width);
			currPos = drawString(g2, content, centerPosition - 50, (currPos + (int) (2D * h) / 100), w);
			
			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_4_hi_1 : FORMVIBConstant.header_4_hi_1;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			hindiFont = hindiFont.deriveFont(Font.BOLD, 10.0F);
			g2.setFont(hindiFont);
			System.out.println(centerPosition + " ------ " + width);
			currPos = drawString(g2, content, 40, (currPos + (int) (2D * h) / 100), w);
			
			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_4_hi_2 : FORMVIBConstant.header_4_hi_2;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			hindiFont = hindiFont.deriveFont(Font.BOLD, 10.0F);
			g2.setFont(hindiFont);
			System.out.println(centerPosition + " ------ " + width);
			currPos = drawString(g2, content, 70, (currPos + (int) (2D * h) / 100), w);

			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.salutation_hi : FORMVIBConstant.salutation_hi;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			hindiFont = hindiFont.deriveFont(Font.BOLD, 10.0F);
			g2.setFont(hindiFont);
			System.out.println(centerPosition + " ------ " + width);
			currPos = drawString(g2, content, 40, (currPos + (int) (2D * h) / 100), w);
			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_5_hi : FORMVIBConstant.header_5_hi;
			width = (content).length();
			centerPosition = 40;
			hindiFont = hindiFont.deriveFont(12.0F);
			g2.setFont(hindiFont);
			currPos = drawString(g2, content, centerPosition, (currPos + (int) (2D * h) / 100), w - 30);

			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_6_hi : FORMVIBConstant.header_6_hi;
			width = (content).length();
			centerPosition = 40;
			hindiFont = hindiFont.deriveFont(Font.BOLD, 11.0F);
			g2.setFont(hindiFont);
			currPos = drawString(g2, content, centerPosition, (currPos + (int) (3D * h) / 100), w - 30);
			int[] tempPosition = new int[8];

			String columnNmae[] = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.table_1_header_hi : FORMVIBConstant.table_1_header_hi;

			hindiFont = hindiFont.deriveFont(Font.BOLD, 9.0F);
			g2.setFont(hindiFont);
			int leftColumnPadding = 2;
			int rightColumnPadding = 2;
			g2.drawLine(40, (currPos + (int) (.5D * h) / 100), 550, (currPos + (int) (.5D * h) / 100));
			int upperLen = currPos + (int) (.5D * h) / 100;
			tempPosition[0] = drawString(g2, columnNmae[0], 50 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 120 - rightColumnPadding);

			tempPosition[1] = drawString(g2, columnNmae[1], 170 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 100 - rightColumnPadding);

			tempPosition[2] = drawString(g2, columnNmae[2], 270 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

			tempPosition[3] = drawString(g2, columnNmae[6], 310 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

			tempPosition[4] = drawString(g2, columnNmae[3], 350 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 50 - rightColumnPadding);

			tempPosition[5] = drawString(g2, columnNmae[4], 400 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

			tempPosition[6] = drawString(g2, columnNmae[5], 440 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 70 - rightColumnPadding);

			tempPosition[7] = drawString(g2, columnNmae[8], 510 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
			currPos = tempPosition[5];
			int finalPos = sortingStringArray(tempPosition);
			currPos = finalPos + (int) (.75D * h) / 100;
			g2.drawLine(40, (currPos), 550, (currPos));
			ArrayList<PartyDetailsDTO> partyData = bo.getBuyerDetail(this.regTxnId, language); // buyer
			for (int k = 0; k < partyData.size(); k++) {
				hindiFont = hindiFont.deriveFont(8.0F);
				g2.setFont(hindiFont);
				PartyDetailsDTO party = partyData.get(k);
				currPos = currPos + (int) (1.5D * h) / 100;
				String partyNameAddress = (party.getPartyName().concat("/")).concat(party.getPartyAdress());
				String guardianAndRelation = (party.getRelationType().concat("/")).concat(party.getGuardianName());
				String age = party.getPartyAge();
				String mobile = party.getMobileNumber();
				String email = party.getEmailId();
				String caste = party.getCategorName();
				String proof = party.getPhotoProofTypeName() + "/" + party.getPhotoProodId();
				tempPosition = new int[7];
				tempPosition[0] = drawString(g2, partyNameAddress, 50 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 120 - rightColumnPadding);

				tempPosition[1] = drawString(g2, guardianAndRelation, 170 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 100 - rightColumnPadding);

				tempPosition[2] = drawString(g2, age, 270 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

				tempPosition[3] = drawString(g2, email, 310 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

				tempPosition[4] = drawString(g2, mobile, 350 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 50 - rightColumnPadding);

				tempPosition[5] = drawString(g2, caste, 400 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

				tempPosition[6] = drawString(g2, proof, 440 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 70 - rightColumnPadding);
				tempPosition[6] = drawString(g2, " ", 510 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

				currPos = sortingStringArray(tempPosition);
				int tempPos = currPos + (int) (.5D * h) / 100;
				g2.drawLine(40, tempPos, 550, tempPos);
				g2.drawLine(40, tempPos, 40, upperLen);
				g2.drawLine(170, tempPos, 170, upperLen);
				g2.drawLine(270, tempPos, 270, upperLen);
				g2.drawLine(310, tempPos, 310, upperLen);
				g2.drawLine(350, tempPos, 350, upperLen);
				g2.drawLine(400, tempPos, 400, upperLen);
				g2.drawLine(440, tempPos, 440, upperLen);
				g2.drawLine(510, tempPos, 510, upperLen);
				g2.drawLine(550, tempPos, 550, upperLen);
				currPos = currPos + (int) (1.5D * h) / 100;

				if (checkForNewPage(currPos, h, 50)) {

					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(w, h);
					hindiFont = hindiFont.deriveFont(8.0F);
					currPos = (int) (5D * h) / 100;

				}
			}

			if (checkForNewPage(currPos, h, 50)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(10.0F);
				currPos = (int) (5D * h) / 100;
				g2.setFont(hindiFont);
			}

			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_7_hi : FORMVIBConstant.header_7_hi;
			width = (content).length();
			centerPosition = 40;
			hindiFont = hindiFont.deriveFont(Font.BOLD, 11.0F);
			g2.setFont(hindiFont);
			currPos = drawString(g2, content, centerPosition, (currPos + (int) (2D * h) / 100), w - 30);
			if (checkForNewPage(currPos, h, 100)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(10.0F);
				currPos = (int) (5D * h) / 100;
				hindiFont = hindiFont.deriveFont(Font.BOLD, 12.0F);
				g2.setFont(hindiFont);
			}

			hindiFont = hindiFont.deriveFont(Font.BOLD, 9.0F);
			g2.setFont(hindiFont);
			tempPosition = new int[9];

			g2.drawLine(40, (currPos + (int) (.5D * h) / 100), 550, (currPos + (int) (.5D * h) / 100));
			upperLen = currPos + (int) (.5D * h) / 100;
			tempPosition[0] = drawString(g2, columnNmae[0], 50 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 90 - rightColumnPadding);

			tempPosition[1] = drawString(g2, columnNmae[1], 140 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 90 - rightColumnPadding);

			tempPosition[2] = drawString(g2, columnNmae[2], 230 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 20 - rightColumnPadding);

			tempPosition[3] = drawString(g2, columnNmae[6], 250 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

			tempPosition[4] = drawString(g2, columnNmae[3], 290 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

			tempPosition[5] = drawString(g2, columnNmae[4], 330 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

			tempPosition[6] = drawString(g2, columnNmae[5], 370 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 70 - rightColumnPadding);
			tempPosition[7] = drawString(g2, columnNmae[7], 440 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
			tempPosition[8] = drawString(g2, columnNmae[8], 480 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

			finalPos = sortingStringArray(tempPosition);
			currPos = finalPos + (int) (.75D * h) / 100;

			g2.drawLine(40, (currPos), 550, (currPos));
			if (checkForNewPage(currPos, h, 50)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(10.0F);
				currPos = (int) (5D * h) / 100;
				// g2.setFont(hindiFont);
			}
			// hindiFont = hindiFont.deriveFont(10.0F);
			// g2.setFont(hindiFont);
			ArrayList<PartyDetailsDTO> sellerDataList = bo.getSellerDetail(this.regTxnId, language);
			for (int k = 0; k < sellerDataList.size(); k++) {

				hindiFont = hindiFont.deriveFont(8.0F);
				g2.setFont(hindiFont);
				PartyDetailsDTO party = sellerDataList.get(k);
				currPos = currPos + (int) (1.5D * h) / 100;
				String partyNameAddress = (party.getPartyName().concat("/")).concat(party.getPartyAdress());
				String guardianAndRelation = (party.getRelationType().concat("/")).concat(party.getGuardianName());
				String age = party.getPartyAge();
				String mobile = party.getMobileNumber();
				String caste = party.getCategorName();
				String proof = party.getPhotoProofTypeName() + "/" + party.getPhotoProodId();
				String partyEmailId = party.getEmailId();
				tempPosition = new int[9];
				tempPosition[0] = drawString(g2, partyNameAddress, 50 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 90 - rightColumnPadding);

				tempPosition[1] = drawString(g2, guardianAndRelation, 140 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 90 - rightColumnPadding);

				tempPosition[2] = drawString(g2, age, 230 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 20 - rightColumnPadding);

				tempPosition[3] = drawString(g2, partyEmailId, 250 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

				tempPosition[4] = drawString(g2, mobile, 290 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

				tempPosition[5] = drawString(g2, caste, 330 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

				tempPosition[6] = drawString(g2, proof, 370 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 70 - rightColumnPadding);
				tempPosition[7] = drawString(g2, "", 440 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
				tempPosition[8] = drawString(g2, "", 480 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);

				finalPos = sortingStringArray(tempPosition);
				currPos = finalPos + (int) (.75D * h) / 100;
				int tempPos = currPos + (int) (.5D * h) / 100;
				g2.drawLine(40, tempPos, 550, tempPos);
				g2.drawLine(40, tempPos, 40, upperLen);
				g2.drawLine(140, tempPos, 140, upperLen);
				g2.drawLine(230, tempPos, 230, upperLen);
				g2.drawLine(250, tempPos, 250, upperLen);
				g2.drawLine(290, tempPos, 290, upperLen);
				g2.drawLine(330, tempPos, 330, upperLen);
				g2.drawLine(370, tempPos, 370, upperLen);
				g2.drawLine(440, tempPos, 440, upperLen);
				g2.drawLine(480, tempPos, 480, upperLen);
				g2.drawLine(550, tempPos, 550, upperLen);
				currPos = currPos + (int) (1.5D * h) / 100;

				if (checkForNewPage(currPos, h, 50)) {
					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(w, h);
					hindiFont = hindiFont.deriveFont(10.0F);
					currPos = (int) (5D * h) / 100;
					g2.setFont(hindiFont);

				}

			}
			if (checkForNewPage(currPos, h, 200)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(10.0F);
				currPos = (int) (5D * h) / 100;
				g2.setFont(hindiFont);
			}

			ArrayList<PropDetailDto> propList = new ArrayList<PropDetailDto>();
			propList = bo.getPropDetail(this.regTxnId, language);
			String[] propColumnName = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.table_2_header_hi : FORMVIBConstant.table_2_header_hi;

			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.header_3_hi_1 : FORMVIBConstant.header_3_hi_1;
			width = (content).length();
			centerPosition = 40;
			hindiFont = hindiFont.deriveFont(11.0F);
			g2.setFont(hindiFont);
			currPos = drawString(g2, content, centerPosition, (currPos + (int) (2D * h) / 100), w - 30);

			g2.drawLine(30, (currPos + (int) (.5D * h) / 100), 570, (currPos + (int) (.5D * h) / 100));
			upperLen = currPos + (int) (.5D * h) / 100;

			if (checkForNewPage(currPos, h, 200)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(10.0F);
				currPos = (int) (5D * h) / 100;
				g2.setFont(hindiFont);
			}

			hindiFont = hindiFont.deriveFont(Font.BOLD, 9.0F);
			g2.setFont(hindiFont);
			System.out.println("row height " + (int) (2D * h));

			tempPosition = new int[10];

			tempPosition[0] = drawString(g2, propColumnName[0], 30 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 70 - rightColumnPadding);
			tempPosition[1] = drawString(g2, propColumnName[1], 100 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 80 - rightColumnPadding);
			tempPosition[2] = drawString(g2, propColumnName[2], 180 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 80 - rightColumnPadding);
			tempPosition[3] = drawString(g2, propColumnName[3], 260 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
			tempPosition[4] = drawString(g2, propColumnName[4], 300 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 60 - rightColumnPadding);

			tempPosition[5] = drawString(g2, propColumnName[5], 360 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 70 - rightColumnPadding);
			tempPosition[6] = drawString(g2, propColumnName[6], 430 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
			tempPosition[7] = drawString(g2, propColumnName[7], 470 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
			tempPosition[8] = drawString(g2, propColumnName[8], 510 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 30 - rightColumnPadding);
			tempPosition[9] = drawString(g2, propColumnName[9], 540 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 30 - rightColumnPadding);
			finalPos = sortingStringArray(tempPosition);
			currPos = finalPos + (int) (.75D * h) / 100;
			int tempPos = currPos;
			g2.drawLine(30, currPos, 570, currPos);
			currPos = currPos + (int) (1.5D * h) / 100;
			if (checkForNewPage(currPos, h, 200)) {
				g2.drawLine(95, tempPos, 95, upperLen);
				g2.drawLine(180, tempPos, 180, upperLen);
				g2.drawLine(260, tempPos, 260, upperLen);
				g2.drawLine(300, tempPos, 300, upperLen);
				g2.drawLine(360, tempPos, 360, upperLen);
				g2.drawLine(430, tempPos, 430, upperLen);
				g2.drawLine(470, tempPos, 470, upperLen);
				g2.drawLine(510, tempPos, 510, upperLen);
				g2.drawLine(540, tempPos, 540, upperLen);
				g2.drawLine(30, tempPos, 30, upperLen);
				g2.drawLine(570, tempPos, 570, upperLen);
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(10.0F);
				currPos = (int) (5D * h) / 100;
				hindiFont = hindiFont.deriveFont(8.0F);
				g2.setFont(hindiFont);
				tempPos = currPos;
				upperLen = currPos;
				g2.drawLine(30, currPos, 570, currPos);
				System.out.println("Current Position is ------> " + (currPos + (int) (2D * h) / 100) + "      " + (int) (5D + h) / 100);
			}
			hindiFont = hindiFont.deriveFont(9.0F);
			g2.setFont(hindiFont);
			for (int i = 0; i < propList.size(); i++) {
				PropDetailDto property = propList.get(i);
				tempPosition = new int[10];

				tempPosition[0] = drawStringCheck(g2, property.getPatwariHalka(), 30 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 60 - rightColumnPadding);
				tempPosition[1] = drawStringCheck(g2, property.getMohallaName(), 100 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 70 - rightColumnPadding);
				tempPosition[2] = drawStringCheck(g2, property.getSurveyNumber(), 180 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 80 - rightColumnPadding);
				tempPosition[3] = drawStringCheck(g2, property.getTotalKhasraArea(), 260 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
				tempPosition[4] = drawStringCheck(g2, property.getSaleableArea(), 300 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 60 - rightColumnPadding);

				tempPosition[5] = drawStringCheck(g2, property.getPartyName(), 360 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 70 - rightColumnPadding);
				tempPosition[6] = drawStringCheck(g2, property.getAreaAsPerShare(), 430 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
				tempPosition[7] = drawStringCheck(g2, property.getDoubleCrop(), 470 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
				tempPosition[8] = drawStringCheck(g2, property.getSingleCrop(), 510 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 30 - rightColumnPadding);
				tempPosition[9] = drawStringCheck(g2, property.getDryLand(), 540 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 30 - rightColumnPadding);

				finalPos = sortingStringArray(tempPosition);

				if (finalPos > h - 20) {
					System.out.println("New page for property detail");
					g2.drawLine(95, tempPos, 95, upperLen);
					g2.drawLine(180, tempPos, 180, upperLen);
					g2.drawLine(260, tempPos, 260, upperLen);
					g2.drawLine(300, tempPos, 300, upperLen);
					g2.drawLine(360, tempPos, 360, upperLen);
					g2.drawLine(430, tempPos, 430, upperLen);
					g2.drawLine(470, tempPos, 470, upperLen);
					g2.drawLine(510, tempPos, 510, upperLen);
					g2.drawLine(540, tempPos, 540, upperLen);
					g2.drawLine(30, tempPos, 30, upperLen);
					g2.drawLine(570, tempPos, 570, upperLen);
					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(w, h);

					currPos = (int) (5D * h) / 100;
					upperLen = currPos;
					hindiFont = hindiFont.deriveFont(8.0F);
					g2.setFont(hindiFont);
					// g2.drawLine(30, currPos, 570, currPos);
					tempPos = currPos;
					upperLen = currPos;
					g2.drawLine(30, (currPos / 100), 570, (currPos / 100));
				}

				hindiFont = hindiFont.deriveFont(8.0F);
				g2.setFont(hindiFont);
				tempPosition[0] = drawString(g2, property.getPatwariHalka(), 30 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 60 - rightColumnPadding);
				tempPosition[1] = drawString(g2, property.getMohallaName(), 100 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 75 - rightColumnPadding);
				tempPosition[2] = drawString(g2, property.getSurveyNumber(), 180 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 80 - rightColumnPadding);
				tempPosition[3] = drawString(g2, property.getTotalKhasraArea(), 260 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
				tempPosition[4] = drawString(g2, property.getSaleableArea(), 300 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 60 - rightColumnPadding);

				tempPosition[5] = drawString(g2, property.getPartyName(), 360 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 70 - rightColumnPadding);
				tempPosition[6] = drawString(g2, property.getAreaAsPerShare(), 430 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
				tempPosition[7] = drawString(g2, property.getDoubleCrop(), 470 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
				tempPosition[8] = drawString(g2, property.getSingleCrop(), 510 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 30 - rightColumnPadding);
				tempPosition[9] = drawString(g2, property.getDryLand(), 540 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 30 - rightColumnPadding);

				finalPos = sortingStringArray(tempPosition);
				currPos = finalPos + (int) (.75D * h) / 100;
				tempPos = currPos;
				g2.drawLine(30, currPos, 570, currPos);

				currPos = currPos + (int) (2D * h) / 100;
				if (checkForNewPage(currPos, h, 200)) {
					g2.drawLine(95, tempPos, 95, upperLen);
					g2.drawLine(180, tempPos, 180, upperLen);
					g2.drawLine(260, tempPos, 260, upperLen);
					g2.drawLine(300, tempPos, 300, upperLen);
					g2.drawLine(360, tempPos, 360, upperLen);
					g2.drawLine(430, tempPos, 430, upperLen);
					g2.drawLine(470, tempPos, 470, upperLen);
					g2.drawLine(510, tempPos, 510, upperLen);
					g2.drawLine(540, tempPos, 540, upperLen);
					g2.drawLine(30, tempPos, 30, upperLen);
					g2.drawLine(570, tempPos, 570, upperLen);
					if (i == propList.size() - 1) {

					} else {
						g2.dispose();
						document.newPage();
						cb = writer.getDirectContent();
						g2 = cb.createGraphicsShapes(w, h);

						currPos = (int) (5D * h) / 100;
						upperLen = currPos;
						hindiFont = hindiFont.deriveFont(8.0F);
						g2.setFont(hindiFont);
						g2.drawLine(30, currPos, 570, currPos);
					}
				}
			}

			g2.drawLine(95, tempPos, 95, upperLen);
			g2.drawLine(180, tempPos, 180, upperLen);
			g2.drawLine(260, tempPos, 260, upperLen);
			g2.drawLine(300, tempPos, 300, upperLen);
			g2.drawLine(360, tempPos, 360, upperLen);
			g2.drawLine(430, tempPos, 430, upperLen);
			g2.drawLine(470, tempPos, 470, upperLen);
			g2.drawLine(510, tempPos, 510, upperLen);
			g2.drawLine(540, tempPos, 540, upperLen);
			g2.drawLine(30, tempPos, 30, upperLen);
			g2.drawLine(570, tempPos, 570, upperLen);

			if (checkForNewPage(currPos, h, 100)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(8.0F);
				currPos = (int) (2D * h) / 100;
				g2.setFont(hindiFont);
			}

			ArrayList paymentDetail = bo.getPaymentDetails(regTxnId);
			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.payment_header_1_hi : FORMVIBConstant.payment_header_1_hi;
			width = (content).length();
			centerPosition = 40;
			hindiFont = hindiFont.deriveFont(12.0F);
			g2.setFont(hindiFont);
			currPos = currPos + (int) (2D * h) / 100;
			currPos = drawString(g2, content, centerPosition, (currPos + (int) (2D * h) / 100), w - 30);
			currPos = currPos + (int) (2D * h) / 100;
			g2.drawLine(30, (currPos + (int) (.5D * h) / 100), 540, (currPos + (int) (.5D * h) / 100));

			// System.out.println("Current position 1 --> "+currPos);
			upperLen = currPos + (int) (.5D * h) / 100;

			if (checkForNewPage(currPos, h, 100)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(8.0F);
				currPos = (int) (2D * h) / 100;
				g2.setFont(hindiFont);
			}
			hindiFont = hindiFont.deriveFont(Font.BOLD, 9.0F);
			g2.setFont(hindiFont);
			currPos = currPos + (int) (.5D * h) / 100;

			// System.out.println("Current position 2 --> "+currPos);

			String[] paymentTableHeader = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.payament_table_header_hi : FORMVIBConstant.payament_table_header_hi;
			tempPosition = new int[3];
			tempPosition[0] = drawStringCheck(g2, paymentTableHeader[0], 30 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
			tempPosition[1] = drawStringCheck(g2, paymentTableHeader[1], 72 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 230 - rightColumnPadding);
			tempPosition[2] = drawStringCheck(g2, paymentTableHeader[2], 302 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 240 - rightColumnPadding);
			finalPos = sortingStringArray(tempPosition);
			if (finalPos > (h - 50)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				// hindiFont = hindiFont.deriveFont(9.0F);
				currPos = (int) (2D * h) / 100;
				upperLen = currPos;
				hindiFont = hindiFont.deriveFont(Font.BOLD, 9.0F);
				g2.setFont(hindiFont);
			}
			tempPosition = new int[3];
			// upperLen=currPos;
			tempPosition[0] = drawString(g2, paymentTableHeader[0], 30 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
			tempPosition[1] = drawString(g2, paymentTableHeader[1], 72 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 230 - rightColumnPadding);
			tempPosition[2] = drawString(g2, paymentTableHeader[2], 302 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 240 - rightColumnPadding);
			currPos = sortingStringArray(tempPosition);
			g2.drawLine(30, (currPos + (int) (.5D * h) / 100), 540, (currPos + (int) (.5D * h) / 100));
			finalPos = sortingStringArray(tempPosition);
			currPos = finalPos + (int) (.75D * h) / 100;
			System.out.println("Payment detail size is --> " + paymentDetail);
			for (int pSize = 0; pSize < paymentDetail.size(); pSize++) {

				hindiFont = hindiFont.deriveFont(Font.NORMAL, 8.0F);
				g2.setFont(hindiFont);
				currPos = finalPos + (int) (.4D * h) / 100;
				if (checkForNewPage(currPos, h, 50)) {
					g2.drawLine(70, upperLen, 70, currPos);
					g2.drawLine(300, upperLen, 300, currPos);
					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(w, h);
					hindiFont = hindiFont.deriveFont(Font.NORMAL, 8.0F);
					g2.setFont(hindiFont);
					currPos = (int) (2D * h) / 100;
					upperLen = currPos;
					g2.drawLine(30, (currPos + (int) (.5D * h) / 100), 540, (currPos + (int) (.5D * h) / 100));
				}
				ArrayList list = (ArrayList) paymentDetail.get(pSize);
				String amountinfigure = (String) list.get(2);
				String amountinword = (String) list.get(1);
				String challannumber = (String) list.get(0);
				System.out.println(challannumber);
				System.out.println(amountinword);
				tempPosition = new int[3];
				tempPosition[0] = drawStringCheck(g2, amountinfigure, 30 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
				tempPosition[1] = drawStringCheck(g2, amountinword, 72 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 230 - rightColumnPadding);
				tempPosition[2] = drawStringCheck(g2, challannumber, 302 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 240 - rightColumnPadding);
				finalPos = sortingStringArray(tempPosition);
				currPos = finalPos;
				if (finalPos > (h - 50)) {
					g2.drawLine(70, upperLen, 70, currPos);
					g2.drawLine(300, upperLen, 300, currPos);
					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(w, h);
					// hindiFont = hindiFont.deriveFont(9.0F);
					currPos = (int) (2D * h) / 100;
					upperLen = currPos;
					hindiFont = hindiFont.deriveFont(Font.NORMAL, 9.0F);
					g2.setFont(hindiFont);
					g2.drawLine(30, (currPos + (int) (.5D * h) / 100), 540, (currPos + (int) (.5D * h) / 100));
				}
				tempPosition = new int[3];
				tempPosition[0] = drawString(g2, amountinfigure, 30 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 40 - rightColumnPadding);
				tempPosition[1] = drawString(g2, amountinword, 70 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 230 - rightColumnPadding);
				tempPosition[2] = drawString(g2, challannumber, 300 + leftColumnPadding, (currPos + (int) (2D * h) / 100), 240 - rightColumnPadding);
				finalPos = sortingStringArray(tempPosition);
				currPos = finalPos;
				g2.drawLine(30, (currPos + (int) (.5D * h) / 100), 540, (currPos + (int) (.5D * h) / 100));
			}

			g2.drawLine(70, upperLen, 70, currPos + (int) (.5D * h) / 100);
			g2.drawLine(300, upperLen, 300, currPos + (int) (.5D * h) / 100);

			if (currPos > (h - 50)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(8.0F);
				currPos = (int) (2D * h) / 100;
				upperLen = currPos;
				g2.setFont(hindiFont);
			}
			currPos = currPos + (int) (3D * h) / 100;
			content = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.declaration_hi : FORMVIBConstant.declaration_hi;
			width = (content).length();
			centerPosition = centerOfPage - width / 2;
			hindiFont = hindiFont.deriveFont(10.0F);
			g2.setFont(hindiFont);
			currPos = drawString(g2, content, centerPosition, (currPos + (int) (1D * h) / 100), w);

			if (currPos > (h - 50)) {
				g2.dispose();
				document.newPage();
				cb = writer.getDirectContent();
				g2 = cb.createGraphicsShapes(w, h);
				hindiFont = hindiFont.deriveFont(8.0F);
				currPos = (int) (2D * h) / 100;
				upperLen = currPos;
				g2.setFont(hindiFont);
			}
			// partyData

			ArrayList<String> decData = bo.getSelectedDeclarationFormVIB(regTxnId, language);
			for (int j = 0; j < decData.size(); j++) {
				content = (j + 1) + ". " + decData.get(j);
				width = (content).length();
				centerPosition = 40;
				hindiFont = hindiFont.deriveFont(8.0F);
				g2.setFont(hindiFont);
				if (j != 0) {
					if (currPos > (h - 50)) {
						g2.dispose();
						document.newPage();
						cb = writer.getDirectContent();
						g2 = cb.createGraphicsShapes(w, h);
						hindiFont = hindiFont.deriveFont(8.0F);
						currPos = (int) (2D * h) / 100;
						upperLen = currPos;
						g2.setFont(hindiFont);
					}
				}
				currPos = drawString(g2, content, centerPosition, (currPos + (int) (2D * h) / 100), w - 25);
				if (j == 0) {
					g2.drawLine(50, (currPos + (int) (.5D * h) / 100), 580, (currPos + (int) (.5D * h) / 100));
					upperLen = currPos + (int) (.5D * h) / 100;
					String buyerColumnName[] = (language.equalsIgnoreCase("en")) ? FORMVIBConstant.table_3_header_hi : FORMVIBConstant.table_3_header_hi;
					tempPosition = new int[6];
					hindiFont = hindiFont.deriveFont(Font.BOLD, 9.0F);
					g2.setFont(hindiFont);
					tempPosition[0] = drawStringCheck(g2, buyerColumnName[0], 50 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 130 - rightColumnPadding);
					tempPosition[1] = drawStringCheck(g2, buyerColumnName[1], 180 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 90 - rightColumnPadding);
					tempPosition[2] = drawStringCheck(g2, buyerColumnName[2], 270 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 110 - rightColumnPadding);
					tempPosition[3] = drawStringCheck(g2, buyerColumnName[3], 380 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 40 - rightColumnPadding);
					tempPosition[4] = drawStringCheck(g2, buyerColumnName[4], 420 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 80 - rightColumnPadding);
					tempPosition[5] = drawStringCheck(g2, buyerColumnName[5], 500 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 50 - rightColumnPadding);

					finalPos = sortingStringArray(tempPosition);
					if (finalPos > (h - 50)) {
						g2.dispose();
						document.newPage();
						cb = writer.getDirectContent();
						g2 = cb.createGraphicsShapes(w, h);
						// hindiFont = hindiFont.deriveFont(9.0F);
						currPos = (int) (2D * h) / 100;
						upperLen = currPos;
						hindiFont = hindiFont.deriveFont(Font.BOLD, 9.0F);
						g2.setFont(hindiFont);
					}
					tempPosition = new int[6];
					tempPosition[0] = drawString(g2, buyerColumnName[0], 50 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 130 - rightColumnPadding);
					tempPosition[1] = drawString(g2, buyerColumnName[1], 180 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 90 - rightColumnPadding);
					tempPosition[2] = drawString(g2, buyerColumnName[2], 270 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 110 - rightColumnPadding);
					tempPosition[3] = drawString(g2, buyerColumnName[3], 380 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 40 - rightColumnPadding);
					tempPosition[4] = drawString(g2, buyerColumnName[4], 420 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 80 - rightColumnPadding);
					tempPosition[5] = drawString(g2, buyerColumnName[5], 500 + leftColumnPadding, (upperLen + (int) (1D * h) / 100), 50 - rightColumnPadding);

					finalPos = sortingStringArray(tempPosition);
					if (finalPos > (h - 50)) {
						currPos = finalPos + (int) (.4D * h) / 100;
						g2.drawLine(50, currPos, 580, currPos);
						g2.drawLine(180, upperLen, 180, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(270, upperLen, 270, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(380, upperLen, 380, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(420, upperLen, 420, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(500, upperLen, 500, finalPos + (int) (.2D * h) / 100);

						g2.dispose();
						document.newPage();
						cb = writer.getDirectContent();
						g2 = cb.createGraphicsShapes(w, h);
						hindiFont = hindiFont.deriveFont(Font.BOLD, 9.0F);
						currPos = (int) (2D * h) / 100;
						upperLen = currPos;
						finalPos = currPos;
						g2.setFont(hindiFont);
						g2.drawLine(50, currPos, 550, currPos);
					} else {
						currPos = finalPos + (int) (.4D * h) / 100;
						g2.drawLine(50, currPos, 580, currPos);
						g2.drawLine(180, upperLen, 180, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(270, upperLen, 270, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(380, upperLen, 380, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(420, upperLen, 420, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(500, upperLen, 500, finalPos + (int) (.2D * h) / 100);
					}

					ArrayList<PartyDetailsDTO> buyer = new ArrayList<PartyDetailsDTO>();
					buyer = bo.getBuyerDetail(regTxnId, language);
					for (int i = 0; i < buyer.size(); i++) {
						PartyDetailsDTO party = buyer.get(i);

						String partyName = party.getPartyName();
						String relation = party.getRelationType() + "/" + party.getGuardianName();
						String partyAdress = party.getPartyAdress();
						String mobNumber = party.getMobileNumber();
						String emailId = party.getEmailId();
						String proof = party.getPhotoProofTypeName() + " / " + party.getPhotoProodId();

						hindiFont = hindiFont.deriveFont(Font.NORMAL, 9.0F);
						g2.setFont(hindiFont);
						tempPosition = new int[6];
						tempPosition[0] = drawStringCheck(g2, partyName, 50 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 130 - rightColumnPadding);
						tempPosition[1] = drawStringCheck(g2, relation, 180 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 90 - rightColumnPadding);
						tempPosition[2] = drawStringCheck(g2, partyAdress, 270 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 110 - rightColumnPadding);
						tempPosition[3] = drawStringCheck(g2, mobNumber, 380 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 40 - rightColumnPadding);
						tempPosition[4] = drawStringCheck(g2, emailId, 420 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 80 - rightColumnPadding);
						tempPosition[5] = drawStringCheck(g2, proof, 500 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 50 - rightColumnPadding);

						finalPos = sortingStringArray(tempPosition);
						currPos = finalPos;
						if (finalPos > (h - 50)) {
							if(i>0){
							currPos = finalPos + (int) (.4D * h) / 100;
							g2.drawLine(50, currPos, 580, currPos);
							g2.drawLine(180, upperLen, 180, finalPos + (int) (.2D * h) / 100);
							g2.drawLine(270, upperLen, 270, finalPos + (int) (.2D * h) / 100);
							g2.drawLine(380, upperLen, 380, finalPos + (int) (.2D * h) / 100);
							g2.drawLine(420, upperLen, 420, finalPos + (int) (.2D * h) / 100);
							g2.drawLine(500, upperLen, 500, finalPos + (int) (.2D * h) / 100);
							}
							g2.dispose();
							document.newPage();
							cb = writer.getDirectContent();
							g2 = cb.createGraphicsShapes(w, h);
							currPos = (int) (2D * h) / 100;
							upperLen = currPos;
							finalPos = currPos;
							g2.drawLine(50, currPos, 580, currPos);

						}
						tempPosition = new int[6];
						hindiFont = hindiFont.deriveFont(Font.NORMAL, 9.0F);
						g2.setFont(hindiFont);
						tempPosition[0] = drawString(g2, partyName, 50 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 130 - rightColumnPadding);
						tempPosition[1] = drawString(g2, relation, 180 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 90 - rightColumnPadding);
						tempPosition[2] = drawString(g2, partyAdress, 270 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 110 - rightColumnPadding);
						tempPosition[3] = drawString(g2, mobNumber, 380 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 40 - rightColumnPadding);
						tempPosition[4] = drawString(g2, emailId, 420 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 80 - rightColumnPadding);
						tempPosition[5] = drawString(g2, proof, 500 + leftColumnPadding, (currPos + (int) (1D * h) / 100), 60 - rightColumnPadding);

						finalPos = sortingStringArray(tempPosition);
						currPos = finalPos + (int) (.4D * h) / 100;;
						g2.drawLine(50, currPos, 580, currPos);
						g2.drawLine(180, upperLen, 180, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(270, upperLen, 270, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(380, upperLen, 380, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(420, upperLen, 420, finalPos + (int) (.2D * h) / 100);
						g2.drawLine(500, upperLen, 500, finalPos + (int) (.2D * h) / 100);

					}
				}
				if (finalPos > (h - 50)) {

					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(w, h);
					hindiFont = hindiFont.deriveFont(9.0F);
					currPos = (int) (2D * h) / 100;
					upperLen = currPos;
					finalPos = currPos;
					g2.setFont(hindiFont);
					// g2.drawLine(50, currPos, 580, currPos);

				}
			}
			RegCommonBD bd = new RegCommonBD();
			ArrayList bottomData = bd.getPropDistrict(regTxnId, language);
			ArrayList declaration = bd.getDeclaration(regTxnId, "A1");
			String con1 = ((String) declaration.get(0) == null ? "" : (String) declaration.get(0));
			String con2 = ((String) declaration.get(1) == null ? "" : (String) declaration.get(1));
			/*String userName = bd.getUserDetailWithRegid(regTxnId);
			if (con1.equalsIgnoreCase("Y")) {
				content = (language.equals("en")) ? FormVIAConstant.HN_PARTY_DECLARATION : FormVIAConstant.HN_PARTY_DECLARATION;
				currPos = drawString(g2, content, 50, (currPos + (int) (2D * h) / 100), w - 25);
			}
			if (con2.equalsIgnoreCase("Y")) {
				content = (language.equals("en")) ? FormVIAConstant.HN_SP_RU_DECLARATION_1 + " " + userName + FormVIAConstant.HN_SP_RU_DECLARATION_2 : FormVIAConstant.HN_SP_RU_DECLARATION_1 + " " + userName + FormVIAConstant.HN_SP_RU_DECLARATION_2;
				currPos = drawString(g2, content, 50, (currPos + (int) (2D * h) / 100), w - 25);
			}*/
			content = (language.equals("en")) ? FormVIAConstant.HN_DATE + " - " + bottomData.get(0) : FormVIAConstant.HN_DATE + " - " + bottomData.get(0);
			currPos = drawString(g2, content, 50, (currPos + (int) (2D * h) / 100), w - 25);

			content = (language.equals("en")) ? FormVIAConstant.HN_PLACE + " - " + bottomData.get(1) : FormVIAConstant.HN_PLACE + " - " + bottomData.get(2);
			currPos = drawString(g2, content, 50, (currPos + (int) (2D * h) / 100), w - 25);
			g2.dispose();
			document.close();
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		}
		if (bool) {
			try {
				bool = bo.updateFormPath(regTxnId, "A2", formA2Path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				bool = false;
				e.printStackTrace();
			}
		}
		// return bool;
		return bool;
	}
	public FormVIBPdfGeneration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean checkForNewPage(int curPos, int h, int size) {
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
	static int drawString(Graphics g, String s, int x, int y, int width) {
		// check for new page

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

	private static int drawStringCheck(Graphics g, String s, int x, int y, int width) {
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

										// g.drawString(temp + "-", curX, curY);

										// g.drawLine(curX, curY + 2, curX +
										// tmpWordWidth, curY + 2);
									} else {
										// g.drawString(temp + "-", curX, curY);
									}
								} catch (StringIndexOutOfBoundsException e) {
									String temp = sbrtmp.substring(0, subIndex - 2);
									e.printStackTrace();
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
									// g.drawString(sbrtmp.toString() + " ",
									// curX, curY);
									// g.drawLine(curX, curY + 2, curX +
									// tmpWordWidth, curY + 2);
								} else {
									// g.drawString(sbrtmp.toString() + " ",
									// curX, curY);
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
						// g.drawString(word, curX, curY);
						// g.drawLine(curX, curY + 2, curX + wordWidth, curY +
						// 2);
						firstword++;
					} else if (!bigWord) {
						// g.drawString(word, curX, curY);
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
