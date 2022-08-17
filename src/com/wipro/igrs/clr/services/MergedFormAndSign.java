package com.wipro.igrs.clr.services;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.apache.pdfbox.util.PDFMergerUtility;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.newgen.niplj.Margin;
import com.newgen.niplj.NIPLJ;
import com.newgen.niplj.codec.DecodeParam;
import com.newgen.niplj.codec.bmp.BmpEncodeParam;
import com.newgen.niplj.codec.gif.GifEncodeParam;
import com.newgen.niplj.codec.jpeg.JpegEncodeParam;
import com.newgen.niplj.codec.tif6.Tif6EncodeParam;
import com.newgen.niplj.generic.NG_BufferedImageOperations;
import com.newgen.niplj.io.RandomInputStreamSource;
import com.newgen.niplj.operations.NG_SimpleImageProducer;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.newreginit.dao.RegCommonDAO;
import com.wipro.igrs.rcms.bd.RevenueCaseBD;

public class MergedFormAndSign {

	public static String mergeFormA1(String regTxnID) throws Exception {
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		RevenueCaseBD revenueBD = new RevenueCaseBD();
		RegCommonDAO regDAO = new RegCommonDAO();
		String resultCode = "";
		String signatureLocation = "";
		String thumbLocation = "";
		String folderId=regTxnID;
		if(regTxnID.length()==11){
			regTxnID="0"+regTxnID;
			folderId = regTxnID;
		}
		ArrayList signPath = new ArrayList();
		System.out.println("*************Merging Form A1*************");
		String formType = "A1";
		String formA1Path = revenueBD.getFormPath(regTxnID, formType);
		if (null == formA1Path) {
			resultCode = "1";
			return resultCode;
		} else if ("".equalsIgnoreCase(formA1Path)) {
			resultCode = "1";
			return resultCode;
		} else {
			signPath = revenueBD.getPartySignPath(regTxnID);
		}
		ArrayList seller = new ArrayList();
		ArrayList buyer = new ArrayList();
		for (int i = 0; i < signPath.size(); i++) {
			ArrayList tmp = (ArrayList) signPath.get(i);
			if (((String) tmp.get(0)).equalsIgnoreCase("seller")) {
				seller.add(tmp);
			} else {
				buyer.add(tmp);
			}
		}
		if (null != formA1Path) {

			File formViA1Pdf = new File(formA1Path);
			PdfReader reader = new PdfReader(formViA1Pdf.getAbsolutePath());
			int count = reader.getNumberOfPages();
			String mpPdfFileName = formViA1Pdf.getName();
			ArrayList singPath = new ArrayList();
			//010622000001
			//10622000001
			float pageWidth = PageSize.A4.getWidth();
			String fontFilePath = pr.getValue("dms_hindi_font_path");
			java.awt.Font englishFont = java.awt.Font.createFont(0, new FileInputStream(fontFilePath));
			float pageHeight = PageSize.A4.getHeight();
			int w = (int) pageWidth - 20;
			int h = (int) pageHeight;
			
			String singedPdfPath = pr.getValue("FORMVIA_PATH") + File.separator + folderId + File.separator + "temp_" + pr.getValue("SIGNED_FORMA1_NAME");
			File pdffile = new File(singedPdfPath);
			FileOutputStream fileout = new FileOutputStream(pdffile);
			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, fileout);
			document.open();
			PdfContentByte cb = writer.getDirectContent();

			Graphics2D g2 = cb.createGraphicsShapes(w, h);
			englishFont = englishFont.deriveFont(17.0F);
			g2.setFont(englishFont);
			double gapHeight = 1.9D;
			double singlePresenterPerc = 13.0D + gapHeight;
			int rowCount = 1;
			int cCount = 0;
			float width = 40;
			float header = 80;
			float imageGap = 0;
			for (int i = 0; i < seller.size(); i++) {

				ArrayList signPathList = (ArrayList) seller.get(i);
				String partyName = (String) signPathList.get(1);
				partyName = partyName.trim();
				String signaturePath = (String) signPathList.get(2);
				String thumbPath = (String) signPathList.get(3);

				signatureLocation = pr.getValue("FORMVIA_PATH") + File.separator + folderId;
				thumbLocation = pr.getValue("FORMVIA_PATH") + File.separator + folderId;

				String cropFileType = cropImage((String) signaturePath, signatureLocation, "cropsignature", "gif");
				com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(signatureLocation+File.separator +"cropsignature.gif");
				

				cropFileType = cropImage((String) thumbPath, signatureLocation, "cropthumb", "gif");
				com.itextpdf.text.Image img1 = com.itextpdf.text.Image.getInstance(thumbLocation+File.separator +"cropthumb.gif");
				 
				img.scaleToFit(12 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
				img1.scaleToFit(12 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);

				float height = h - (img.getScaledHeight() + header + imageGap);
				System.out.println("Left page heigh .... " + (h - height));
				if ((h - height) > 642) {
					System.out.println("New Page added....");
					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(w, h);
					cCount = 0;
					imageGap = 0;
					height = h - (img.getScaledHeight() + header + imageGap);
				}
				System.out.println("Image scaled Height --> " + img.getScaledHeight());
				System.out.println("Header --> " + header + " Image Gap --> " + imageGap + " and height is --> " + height);

				int startPos = 40;
				drawString(g2, partyName, startPos, (int) (h - height - (img.getScaledHeight() / 2)), 150);
				img.setAbsolutePosition(width + 220, height);
				img1.setAbsolutePosition(width + 220+img.getWidth(), height);
				width = 40;
				imageGap = imageGap + 80;
				document.add(img);
				document.add(img1);

			}
			g2.dispose();
			document.close();
			PDFMergerUtility obj = new PDFMergerUtility();
			String signedFormA1Path = pr.getValue("FORMVIA_PATH") + File.separator + folderId + File.separator + pr.getValue("SIGNED_FORMA1_NAME");
			obj.setDestinationFileName(signedFormA1Path);
			obj.addSource(formA1Path);
			obj.addSource(singedPdfPath);

			// Merging documents

			obj.mergeDocuments();
			
			regDAO.updateSignedFormPath(regTxnID, "A1", signedFormA1Path);
			if (!signatureLocation.equalsIgnoreCase("")) {
				System.out.println("In File deletion part..... --> " + signatureLocation);
				String signLoc = signatureLocation+File.separator+"cropsignature.gif";
				String thumbLoc = thumbLocation+File.separator+"cropthumb.gif";
				File delCropFile = new File(signLoc);
				if (delCropFile.isFile()) {
					System.out.println("Yes cropped sign exist, deleting....");
					delCropFile.delete();
				}
				delCropFile = new File(thumbLoc);
				if (delCropFile.isFile()) {
					System.out.println("Yes cropped thumb exist, deleting....");
					delCropFile.delete();
				}
			}

		}
		formType = "A2";
		String formA2Path = revenueBD.getFormPath(regTxnID, formType);
		System.out.println("*************Merging Form A2*************");
		if (null != formA2Path) {

			File formViA1Pdf = new File(formA1Path);
			PdfReader reader = new PdfReader(formViA1Pdf.getAbsolutePath());
			int count = reader.getNumberOfPages();
			String mpPdfFileName = formViA1Pdf.getName();
			ArrayList singPath = new ArrayList();

			float pageWidth = PageSize.A4.getWidth();
			String fontFilePath = pr.getValue("dms_hindi_font_path");
			java.awt.Font englishFont = java.awt.Font.createFont(0, new FileInputStream(fontFilePath));
			float pageHeight = PageSize.A4.getHeight();
			int w = (int) pageWidth - 20;
			int h = (int) pageHeight;
			
			String singedPdfPath = pr.getValue("FORMVIB_PATH") + File.separator + folderId + File.separator + "temp_" + pr.getValue("SIGNED_FORMA2_NAME");
			File pdffile = new File(singedPdfPath);
			FileOutputStream fileout = new FileOutputStream(pdffile);
			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, fileout);
			document.open();
			PdfContentByte cb = writer.getDirectContent();

			Graphics2D g2 = cb.createGraphicsShapes(w, h);
			englishFont = englishFont.deriveFont(17.0F);
			g2.setFont(englishFont);
			double gapHeight = 1.9D;
			double singlePresenterPerc = 13.0D + gapHeight;
			int rowCount = 1;
			int cCount = 0;
			float width = 40;
			float header = 80;
			float imageGap = 0;
			for (int i = 0; i < buyer.size(); i++) {

				ArrayList signPathList = (ArrayList) buyer.get(i);
				String partyName = (String) signPathList.get(1);
				partyName = partyName.trim();
				String signaturePath = (String) signPathList.get(2);

				String thumbPath = (String) signPathList.get(3);

				signatureLocation = pr.getValue("FORMVIB_PATH") + File.separator + folderId;
				thumbLocation = pr.getValue("FORMVIB_PATH") + File.separator + folderId;

				String cropFileType = cropImage((String) signaturePath, signatureLocation, "cropsignature", "gif");
				com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(signatureLocation+File.separator +"cropsignature.gif");
				

				cropFileType = cropImage((String) thumbPath, signatureLocation, "cropthumb", "gif");
				com.itextpdf.text.Image img1 = com.itextpdf.text.Image.getInstance(thumbLocation+File.separator +"cropthumb.gif");
				
				img.scaleToFit(12 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
				img1.scaleToFit(12 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);

				float height = h - (img.getScaledHeight() + header + imageGap);
				System.out.println("Left page heigh .... " + (h - height));
				if ((h - height) > 642) {
					System.out.println("New Page added....");
					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(w, h);
					cCount = 0;
					imageGap = 0;
					height = h - (img.getScaledHeight() + header + imageGap);
				}
				System.out.println("Image scaled Height --> " + img.getScaledHeight());
				System.out.println("Header --> " + header + " Image Gap --> " + imageGap + " and height is --> " + height);

				int startPos = 40;
				drawString(g2, partyName, startPos, (int) (h - height - (img.getScaledHeight() / 2)), 300);
				img.setAbsolutePosition(width + 220, height);
				img1.setAbsolutePosition(width + 220+img.getWidth(), height);
				width = 40;
				imageGap = imageGap + 80;
				document.add(img);
				document.add(img1);

			}
			g2.dispose();
			document.close();
			PDFMergerUtility obj = new PDFMergerUtility();
			String signedFormA2Path = pr.getValue("FORMVIB_PATH") + File.separator + folderId + File.separator + pr.getValue("SIGNED_FORMA2_NAME");
			obj.setDestinationFileName(signedFormA2Path);
			obj.addSource(formA2Path);
			obj.addSource(singedPdfPath);

			// Merging documents
			
			obj.mergeDocuments();
			

			regDAO.updateSignedFormPath(regTxnID, "A2", signedFormA2Path);
			
			if (!signatureLocation.equalsIgnoreCase("")) {
				System.out.println("In File deletion part..... --> " + signatureLocation);
				String signLoc = signatureLocation+File.separator+"cropsignature.gif";
				String thumbLoc = thumbLocation+File.separator+"cropthumb.gif";
				File delCropFile = new File(signLoc);
				if (delCropFile.isFile()) {
					System.out.println("Yes cropped sign exist, deleting....");
					delCropFile.delete();
				}
				delCropFile = new File(thumbLoc);
				if (delCropFile.isFile()) {
					System.out.println("Yes cropped thumb exist, deleting....");
					delCropFile.delete();
				}
			}
		}

		return resultCode;
	}
	public static String cropImage(String inputFilePath, String cropFilePath, String cropFileName, String fileType) {
		NG_BufferedImageOperations ngbuff = new NG_BufferedImageOperations();
		DecodeParam decode1 = new DecodeParam(1, 0);
		java.awt.Image imageObj = null;
		RandomInputStreamSource riss = null;
		int inputFileType = 0;
		String retunrType = "";
		Tif6EncodeParam enc1 = new Tif6EncodeParam();
		BmpEncodeParam enc2 = new BmpEncodeParam();
		JpegEncodeParam enc3 = new JpegEncodeParam();
		GifEncodeParam enc4 = new GifEncodeParam();
		try {
			riss = new RandomInputStreamSource(inputFilePath);
			inputFileType = NIPLJ.getFileFormat(riss);

			// logger.info("BurningImageAndText cropImage method, incoming file type: "
			// + inputFileType);
			imageObj = NIPLJ.decodeToImage(inputFilePath, decode1);

		}

		catch (Exception e) {
			// logger.error("BurningImageAndText cropImage method, Exception caught.",
			// e);
		}
		while (ngbuff.NoOfRowsSet != ngbuff.ImageDimension.height) {
			try {
				Thread.sleep(100L);
			} catch (Exception localException1) {
			}
		}
		ngbuff.FillInImage(imageObj, true);
		try {
			MediaTracker mt = new MediaTracker(new JPanel());
			mt.addImage(imageObj, 0);
			mt.waitForID(0);
			mt.removeImage(imageObj);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Margin mg = new Margin();
		mg.iBottom = 10;
		mg.iTop = 10;
		mg.iLeft = 10;
		mg.iRight = 10;
		Margin lmg = new Margin();
		lmg.iBottom = 50;
		lmg.iTop = 50;
		lmg.iLeft = 50;
		lmg.iRight = 30;
		NG_BufferedImageOperations ngbuffout = new NG_BufferedImageOperations();
		ngbuffout = NIPLJ.cropWhiteArea(ngbuff, 15, mg, lmg, 0, 0);

		NG_SimpleImageProducer ng = new NG_SimpleImageProducer(ngbuffout);
		java.awt.Image image = Toolkit.getDefaultToolkit().createImage(ng);
		try {
			if (inputFileType == 1) {
				retunrType = "tif";
				NIPLJ.encodeFromImage(image, cropFilePath + File.separator + cropFileName + "." + "tif", enc1);
			} else if (inputFileType == 2) {
				retunrType = "bmp";
				NIPLJ.encodeFromImage(image, cropFilePath + File.separator + cropFileName + "." + "bmp", enc2);
			} else if (inputFileType == 3) {
				retunrType = "jpg";
				NIPLJ.encodeFromImage(image, cropFilePath + File.separator + cropFileName + "." + "jpg", enc3);
			} else if (inputFileType == 5) {
				retunrType = "gif";
				NIPLJ.encodeFromImage(image, cropFilePath + File.separator + cropFileName + "." + "gif", enc4);
			}
			return retunrType;
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("BurningImageAndText cropImage method, Exception caught ",
			// e);
		}
		return "error";
	}

	static int drawString(Graphics g, String s, int x, int y, int width) {
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
					if (curX + wordWidth > x + width) {
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
							while (tmpWordWidth > width - 1) {
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
