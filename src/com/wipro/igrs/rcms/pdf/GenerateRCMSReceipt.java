package com.wipro.igrs.rcms.pdf;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

///import com.igrs.wipro.cybertehsil.forms.constant.FormVIAConstant;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.rcms.bd.RevenueCaseBD;
import com.wipro.igrs.rcms.entity.RCMSResponse;

public class GenerateRCMSReceipt {
	
	static PropertiesFileReader pr = null;

	//public static final String FONT = "C:/Users/AN350799/shared2/font/ARIALUNI.ttf";
	public static String FONT =null; 

	//static Font f = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	 //private static String FILE = "c:/temp/RCMS_RECEIPT_NEW.pdf";
	    private static String lang = "hi";
	    
		static java.awt.Font hindiFont ;
		static java.awt.Font hindiFontBold ;
		
	     Document document = new Document(); 
	     PdfTemplate pdt=null;
	     PdfContentByte cb =null;
	     PdfWriter writer =null;
	     Graphics2D g2 =null;
	     float pageWidth = PageSize.A4.getWidth();

	     float pageHeight = PageSize.A4.getHeight();
		 int w = (int) pageWidth;
		 int h = (int) pageHeight;
		
		 boolean isBottom = true;
		 boolean isNewAdded = false;
		 boolean isNewReq = false;
		 int currPos;
		 
		public  void generateRCMSReceipt(String regTxnId, int pageCount) throws Exception {
			 
			pr = PropertiesFileReader.getInstance("resources.igrs");
			FONT =  pr.getValue("dms_hindi_font_path");
			
			 hindiFont =  java.awt.Font.createFont(0, new FileInputStream(FONT));
			 hindiFontBold =  java.awt.Font.createFont(0, new FileInputStream(FONT));
			 
			 
			//30918000001 //540201802715386 , 540201802715387
			//getKhasraDetail
			//getReceiptDetail
			
			// String regdate =  "10-2-2022";
			 
				hindiFontBold = hindiFont.deriveFont(17.0F);
				
				//String regTxnId = "220222000004";//"250518000599";//"220222000004";//"60815000169";////"30918000001";
				//String[] propId = {"540202202719193"};//{"4708201500067675"};//{"531201802517798"};//{"540201802715386","540201802715387","540201802715388","540201802715389"};
				
				RevenueCaseBD rcBD = new RevenueCaseBD();
				 RCMSResponse rcmsResp = rcBD.getReceiptDetail(regTxnId);
				
				
				String pdfDir = pr.getValue("RCMS_RECEIPT_PATH");
				pdfDir = pdfDir+regTxnId+"//";
						
				File pdfDirPath = new File(pdfDir);
				
				if (!pdfDirPath.exists()) {
					pdfDirPath.mkdir();
				}
				String pdfFilePath = pdfDirPath +"//"+ pr.getValue("RCMS_RECEIPT_FILENAME");
					
				/* File pdffile = new File(pdfFilePath);
				 * 
				 * if (!pdffile.exists()) { pdffile.createNewFile(); } */
				//Document document = new Document(PageSize.A4);
				document.addAuthor("WIPRO");
				document.addTitle("RCMS RECEIPT");
				
				FileOutputStream fileout = new FileOutputStream(pdfFilePath);
				 writer = PdfWriter.getInstance(document, fileout);
				document.open();
				
				if(!(pageCount%2==0)) {
					document.newPage();
			        writer.setPageEmpty(false);
			        document.newPage();
				}
				  
			        
				 cb = writer.getDirectContent();
				 pdt = cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				// cb.saveState();
				float pageWidth = PageSize.A4.getWidth();

				float pageHeight = PageSize.A4.getHeight();
				 w = (int) pageWidth-50;
				 h = (int) pageHeight;
				
				System.out.println(w +"!!!!"+h);
				 g2 = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				cb.addTemplate(pdt,5,-7);
				
				drawPageBorder(); //border on first page , other page border handle in drawstring method
				 
				int centerOfPage = w / 2;
				
				String content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_HEADING_1:RCMSPDFCONSTANT.HN_HEADING_1;
				hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,10.0F);
				g2.setFont(hindiFontBold);
				 currPos = drawString(g2, content, 230, (int) (5D * h) / 100, w);
				
			     	hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,12.0F);
					g2.setFont(hindiFontBold);
					 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_HEADING_2:RCMSPDFCONSTANT.HN_HEADING_2;
					 currPos = drawString(g2, content, 200, currPos+(int) (4D * h) / 100, w);
					
					 //g2.drawLine( 200, currPos+(int) (.5D * h) / 100, 400,currPos+(int) (.5D * h) / 100); //X
					 
					hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,12.0F);
					g2.setFont(hindiFontBold);
					 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_HEADING_3:RCMSPDFCONSTANT.HN_HEADING_3;
					 
					 currPos = drawString(g2, content, 140, currPos+(int) (2.5D * h) / 100, w);
					
				/*	 String heading=(lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.EN_HEADING_4_1:RCMSPDFCONSTANT.HN_HEADING_4_1;
					// heading=heading+;
					hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,12.0F);
					g2.setFont(hindiFontBold);
					 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.EN_HEADING_4_1:RCMSPDFCONSTANT.HN_HEADING_4_1;
					 content= content+ " : "+ rcmsResp.getRegistrationDate();
					 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w);
			     

						hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,12.0F);
						g2.setFont(hindiFontBold);
						 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.EN_HEADING_4_2:RCMSPDFCONSTANT.HN_HEADING_4_2;
						 content= content+ " : "+ rcmsResp.getRegistrationDate();
						 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w);
						 

							hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,12.0F);
							g2.setFont(hindiFontBold);
							 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.EN_HEADING_4_3:RCMSPDFCONSTANT.HN_HEADING_4_3;
							 content= content+ " : "+ rcmsResp.getRegistrationDate();
							 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w);*/
					 
					// g2.drawLine(50, (currPos+(int) (1.5D * h) / 100), 540, (currPos+(int) (1.5D * h) / 100));
					// g2.drawLine(50, (currPos+(int) (2D * h) / 100), 540, (currPos+(int) (2D * h) / 100));
			
			

			//for(int p=0;p<regTxnId.length;p++) {
				//rcmsReceipt( regTxnId ,  propId[p]);
			//}
						 //iterate in loop
				rcmsReceipt( regTxnId, rcmsResp );
			
			 //close the box
			/* content = (lang=="en")?RCMSPDFCONSTANT.HN_NOTE:RCMSPDFCONSTANT.HN_NOTE;
			 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w-25);*/
			 
			 
	     
			 g2.dispose();
			document.close();
		}
		
		public  void rcmsReceipt(String regTxnId,RCMSResponse rcmsResp ) throws Exception {
			RevenueCaseBD rcBD = new RevenueCaseBD();
			//RCMSResponse rcmsResp = rcBD.getReceiptDetail(regTxnId); //iterate in loop
			
			String mpNo  = rcmsResp.getRegistrationNumber();//"MP280222000001";
	  		String doctype = rcmsResp.getDocumentType();//"rcms receipt";//rcmsResp.getd
	  		
	  		String sellerFirstName =  rcmsResp.getSellerName();//"Seller first name";
	  		String sellerFirstfatherName = rcmsResp.getSellerFather();// "Seller first father name";
	  		String buyerFirstName =  rcmsResp.getBuyerName();//"Buyer first name";
	  		String buyerFirstfatherName =  rcmsResp.getBuyerFather();//"Buyer first father name";
	  		String gramPanOrMukhyalay =  rcmsResp.getGramPanchayat();//"gramPanOrMukhyalay name";
	  		
	  		String courtdate =  rcmsResp.getDateOfPresentation();//"12-2-2022";
	  		String rcmsAppId = rcmsResp.getApplicationNumber();// "0229C15-APP-10656582";
	  		String coutcaseNo =  rcmsResp.getCaseNumber();//"0255/अ-6/2021-22";
	  		String courtName = rcmsResp.getCourtName();// "न्या\r\n" + "या\r\n" + 	"लय तहसी लदा र, तहसी ल श्या मपु पुर, ज़ि ला सी हो र म.प्र .\r\n" + "तहसी";
	  		String courtTehsil = rcmsResp.getTehsil();// "श्या\r\n" + "मपु पुर";
	  		String courtDist =rcmsResp.getDistrict();//  "सीहो\"र";
	  		
	  		
	  		 String heading=(lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_HEADING_4_1:RCMSPDFCONSTANT.HN_HEADING_4_1;
				 heading=heading+courtTehsil+" ,";
				 heading = heading + ((lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_HEADING_4_2:RCMSPDFCONSTANT.HN_HEADING_4_2);
				 heading = heading + courtDist+ ((lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_HEADING_4_3:RCMSPDFCONSTANT.HN_HEADING_4_3);
				 
				hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,10.0F);
				g2.setFont(hindiFontBold);
				String content  = heading;
			//	 content= content+ " : "+ rcmsResp.getRegistrationDate();
				 currPos = drawString(g2, content, 50, currPos+(int) (3D * h) / 100, w);
				 hindiFont = hindiFont.deriveFont(12.0F);
					g2.setFont(hindiFont);
					content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.EN_APP_NO:RCMSPDFCONSTANT.HN_APP_NO;
			  		content = content + " "+rcmsAppId;
			  		currPos = drawString(g2, content, 300, currPos+(int) (2D * h) / 100,500);
			  		
			  		content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.EN_CASE_NO:RCMSPDFCONSTANT.HN_CASE_NO;
			  		content = content + " "+coutcaseNo;
			  		currPos = drawString(g2, content, 300, currPos+(int) (2D * h) / 100,500);
			  		
				 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_CONTENT_1:RCMSPDFCONSTANT.HN_CONTENT_1;
				 currPos = drawString(g2, content, 50, currPos+(int) (3D * h) / 100, w);
				 
				 content = ((lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_EXECUTANT_1:RCMSPDFCONSTANT.HN_EXECUTANT_1)+" "+sellerFirstName+", "+sellerFirstfatherName+" "+((lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.EN_EXECUTANT_2:RCMSPDFCONSTANT.HN_EXECUTANT_2);
				 currPos = drawString(g2, content, 50, currPos+(int) (3D * h) / 100, w);
				 
				 content = ((lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_CLAIMANT_1:RCMSPDFCONSTANT.HN_CLAIMANT_1)+" "+buyerFirstName+", "+buyerFirstfatherName+" "+((lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.EN_CLAIMANT_2:RCMSPDFCONSTANT.HN_CLAIMANT_2);
				 currPos = drawString(g2, content, 50, currPos+(int) (3D * h) / 100, w);
				 
				 
				 content="";
				 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_BODY_1:RCMSPDFCONSTANT.HN_BODY_1;
				 content = content + courtdate;
				 content = content + " " + ((lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_BODY_2:RCMSPDFCONSTANT.HN_BODY_2);
				 content = content + " " + RCMSPDFCONSTANT.RCMS_WEBGIS_LINK;
				 content = content + " " +((lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_BODY_3:RCMSPDFCONSTANT.HN_BODY_3);
				 
				 hindiFontBold = hindiFontBold.deriveFont(Font.PLAIN,12.0F);
				g2.setFont(hindiFontBold);
				 //content = (lang=="en")?RCMSPDFCONSTANT.HN_CONTENT_HEADING_2:RCMSPDFCONSTANT.HN_CONTENT_HEADING_2;
				 
				 System.out.println("currPos before heading anusuchi - "+currPos);
				 
				 isNewReq = checkForNewPage(currPos,100);
				 
				 currPos = drawString(g2, content, 50, currPos+(int) (3D * h) / 100, w-25);
				 
				 //content ="";
				 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_BODY_4:RCMSPDFCONSTANT.HN_BODY_4;
				 content = content+ courtdate;
				 content = content+ " "+((lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_BODY_5:RCMSPDFCONSTANT.HN_BODY_5);
				 isNewReq = checkForNewPage(currPos,100);
				 
				 currPos = drawString(g2, content, 50, currPos+(int) (3D * h) / 100, w-25);
				 
				 
				 
				 System.out.println("currPos after anusuchi - "+currPos);
				 
				 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.EN_TABLE_HEADER:RCMSPDFCONSTANT.HN_TABLE_HEADER;
				 currPos = drawString(g2, content, w/2, currPos+(int) (3D * h) / 100, w-25);
				 //table to be created-----------------
				 
				 ArrayList list = rcBD.getKhasraDetail(regTxnId,lang);
				 
				 if(list!=null)
				 {
					 int[]  tempPosition = new int[8];
					 int tempPos;
					 int upperLen = 0;
					 int headerPad = (int) (4D * h) / 100;
					 int rowPad = (int) (5D * h) / 100;
					 
					 hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,12.0F);
					 g2.setFont(hindiFontBold);

					 
					 	upperLen = currPos+(int)(0.5D*h)/100;
					 	//currPos = currPos+currPos+(int)(0.5D*h)/100;
					 	tempPosition[0]=drawString(g2, RCMSPDFCONSTANT.HN_TABLE1_HD1, 40, (currPos + headerPad), 40);
					 	
						tempPosition[1]=drawString(g2, RCMSPDFCONSTANT.HN_TABLE1_HD2, 80, (currPos + headerPad), 40);
						tempPosition[2]=drawString(g2, RCMSPDFCONSTANT.HN_TABLE1_HD3, 120, (currPos + headerPad), 80);
						tempPosition[3]=drawString(g2, RCMSPDFCONSTANT.HN_TABLE1_HD4, 200, (currPos + headerPad), 60);
						tempPosition[3]=drawString(g2, RCMSPDFCONSTANT.HN_TABLE1_HD5, 260, (currPos + headerPad), 80);
						tempPosition[3]=drawString(g2, RCMSPDFCONSTANT.HN_TABLE1_HD6, 340, (currPos + headerPad), 30);
						tempPosition[3]=drawString(g2, RCMSPDFCONSTANT.HN_TABLE1_HD7, 370, (currPos + headerPad), 80);
						tempPosition[3]=drawString(g2, RCMSPDFCONSTANT.HN_TABLE1_HD8, 450, (currPos + headerPad), 80);
					 
						int finalPos = sortingStringArray(tempPosition);
			  			currPos=finalPos;
			  			hindiFont = hindiFont.deriveFont(10.0F);
			  			g2.setFont(hindiFont);
			  			
			  			//g2.drawLine(40, (finalPos), 540, (finalPos));
					 
			  			
			  			
					 if(list.size()>0) {
						 for(int i =0;i<list.size();i++) {
							 ArrayList subList = (ArrayList) list.get(i);
							 
							 currPos = currPos + (int) (1.5D * h) / 100;
							 tempPosition = new int[4];
							 
							 for(int j =0;j<subList.size();j++) {
								 //subList.get(0);//khasra no
								// subList.get(1);//rakba no
								 if (checkForNewPage1(currPos, h, 150)) {
									 g2.drawLine(40, currPos, 540, currPos);
										g2.drawLine(40, currPos, 40, upperLen);
										g2.drawLine(80, currPos, 80, upperLen);
										g2.drawLine(120, currPos, 120, upperLen);
										g2.drawLine(200, currPos, 200, upperLen);
										g2.drawLine(260, currPos, 260, upperLen);
										g2.drawLine(340, currPos, 340, upperLen);
										g2.drawLine(370, currPos, 370, upperLen);
										g2.drawLine(450, currPos, 450, upperLen);
										g2.drawLine(540, currPos, 540, upperLen);
										g2.drawLine(40, upperLen, 540, upperLen);
										g2.dispose();
										document.newPage();
										cb = writer.getDirectContent();
										g2 = cb.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
										hindiFont = hindiFont.deriveFont(8.0F);
										g2.setFont(hindiFont);
										currPos = (int) (5D * h) / 100;
										drawPageBorder();
									}else{
										g2.drawLine(40, currPos, 40, upperLen);
										g2.drawLine(80, currPos, 80, upperLen);
										g2.drawLine(120, currPos, 120, upperLen);
										g2.drawLine(200, currPos, 200, upperLen);
										g2.drawLine(260, currPos, 260, upperLen);
										g2.drawLine(340, currPos, 340, upperLen);
										g2.drawLine(370, currPos, 370, upperLen);
										g2.drawLine(450, currPos, 450, upperLen);
										g2.drawLine(540, currPos, 540, upperLen);
										g2.drawLine(40, upperLen, 540, upperLen);
										hindiFont = hindiFont.deriveFont(8.0F);
										g2.setFont(hindiFont);
									}
								 ArrayList buyer =  (ArrayList) subList.get(2);//seller list
								ArrayList seller =  (ArrayList) subList.get(3);//buyer list
								StringBuffer buyerNames = new StringBuffer();
								StringBuffer sellerNames = new StringBuffer();
								for(int b=0;b<buyer.size();b++) {
									buyerNames.append(buyer.get(b)+"\n");
								}
								for(int b=0;b<seller.size();b++) {
									sellerNames.append(seller.get(b)+"\n");
								}

								 //isNewReq = checkForNewPage(currPos,150);
								 	upperLen=currPos+(int)(0.5D*h)/100;
								 	tempPosition[0]=drawString(g2,subList.get(4).toString(), 44, (currPos + rowPad), 35);
								 	
									tempPosition[1]=drawString(g2, subList.get(5).toString(), 81, (currPos + rowPad), 38);
									tempPosition[2]=drawString(g2, subList.get(6).toString(), 121, (currPos + rowPad), 77);
									tempPosition[3]=drawString(g2, subList.get(7).toString(), 201, (currPos + rowPad), 58);

									tempPosition[3]=drawString(g2, subList.get(0).toString(), 261, (currPos + rowPad), 78);
									tempPosition[3]=drawString(g2, subList.get(1).toString(), 341, (currPos + rowPad), 28);
									
									tempPosition[2]=drawString(g2, sellerNames.toString(), 371, (currPos + rowPad), 77);
									tempPosition[3]=drawString(g2, buyerNames.toString(), 451, (currPos + rowPad), 77);

							 }
							 
							 	currPos=sortingStringArray(tempPosition);
							 	tempPos = currPos + (int) (.5D * h) / 100;
								
								g2.drawLine(40, tempPos, 540, tempPos);
								g2.drawLine(40, tempPos, 40, upperLen);
								g2.drawLine(80, tempPos, 80, upperLen);
								g2.drawLine(120, tempPos, 120, upperLen);
								g2.drawLine(200, tempPos, 200, upperLen);
								g2.drawLine(260, tempPos, 260, upperLen);
								g2.drawLine(340, tempPos, 340, upperLen);
								g2.drawLine(370, tempPos, 370, upperLen);
								g2.drawLine(450, tempPos, 450, upperLen);
								g2.drawLine(540, tempPos, 540, upperLen);
								g2.drawLine(40, upperLen, 540, upperLen);
									
								currPos = currPos + (int) (1.5D * h) / 100;
						 }
						 
						 	
						
					 }
					 
				 }
				 Date date = new Date();
			      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			       String str = formatter.format(date);
				 
				 hindiFont = hindiFont.deriveFont(12.0F);
					g2.setFont(hindiFont);
				 content = (lang.equalsIgnoreCase("en"))?(RCMSPDFCONSTANT.HN_BOTTOM_1 +" "+str+" "+RCMSPDFCONSTANT.HN_BOTTOM_2):(RCMSPDFCONSTANT.HN_BOTTOM_1+" "+str+" "+RCMSPDFCONSTANT.HN_BOTTOM_2);
				 currPos = drawString(g2, content, 50, currPos+(int) (3D * h) / 100, w-25);
				 
				 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_BOTTOM_4+" "+str:RCMSPDFCONSTANT.HN_BOTTOM_4+" "+str;
				 currPos = drawString(g2, content, 50, currPos+(int) (3D * h) / 100, w-25);
				 
				 content = (lang.equalsIgnoreCase("en"))?RCMSPDFCONSTANT.HN_BOTTOM_6:RCMSPDFCONSTANT.HN_BOTTOM_6;
				 currPos = drawString(g2, content, 100, currPos+(int) (3D * h) / 100, w-25);
				 
				 /* content = (lang=="en")?RCMSPDFCONSTANT.HN_CONTENT_2:RCMSPDFCONSTANT.HN_CONTENT_2;
				 content =  content + " "+ courtdate+" " ;
				 content = content + ((lang=="en")?RCMSPDFCONSTANT.HN_CONTENT_2_1:RCMSPDFCONSTANT.HN_CONTENT_2_1) + " "+rcmsAppId;
				 content = content + " "+((lang=="en")?RCMSPDFCONSTANT.HN_CONTENT_2_2:RCMSPDFCONSTANT.HN_CONTENT_2_2)+ " "+coutcaseNo;
				 content = content + " "+((lang=="en")?RCMSPDFCONSTANT.HN_CONTENT_2_3:RCMSPDFCONSTANT.HN_CONTENT_2_3) + " "+courtName;
				 content = content + " "+((lang=="en")?RCMSPDFCONSTANT.HN_CONTENT_2_4:RCMSPDFCONSTANT.HN_CONTENT_2_4) + " "+courtTehsil;
				 content = content + " "+((lang=="en")?RCMSPDFCONSTANT.HN_CONTENT_2_5:RCMSPDFCONSTANT.HN_CONTENT_2_5) + " "+courtDist;
				 content = content + " "+((lang=="en")?RCMSPDFCONSTANT.HN_CONTENT_2_6:RCMSPDFCONSTANT.HN_CONTENT_2_6);
				 
				 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w-25);
				 
				 content = (lang=="en")?RCMSPDFCONSTANT.HN_CONTENT_3:RCMSPDFCONSTANT.HN_CONTENT_3;
				 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w-25);*/
				 
				// g2.drawLine( 55, currPos+(int) (2D * h) / 100, 535,currPos+(int) (2D * h) / 100); //X
				 
				 

				 
				
		}
		
		public  void drawPageBorder() {
			 g2.drawLine( 25, 815, 565,815); //X
			 g2.drawLine( 25, 25, 565,25);   //X
			 g2.drawLine( 25, 815, 25,25);   //Y
			 g2.drawLine( 565, 815, 565,25);  //Y
		}
		
		public  boolean checkForNewPage(int curPos, int size){
			isNewReq = false;
			boolean result = false;
			if((h-size)<curPos){
				currPos = 815;
				result=true;
			}
			return result;
		}
		public boolean checkForNewPage1(int curPos, int h, int size) {
			boolean result = false;
			if ((h - size) < curPos) {
				result = true;
			}
			return result;
		}
		public  int sortingStringArray(int [] arr){
			int len = arr.length;
			 Arrays.sort(arr);
			return (arr[len-1]);
		}
		private  int drawString(Graphics g, String s, int x, int y, int width) {
			//check for new page
			if (checkForNewPage1(currPos, h, 100)) {
				if (y > (h - 150)) {
					g2.dispose();
					document.newPage();
					cb = writer.getDirectContent();
					g2 = cb.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
					hindiFont = hindiFont.deriveFont(12.0F);
					y = (int) (5D * h) / 100;
					currPos = y;
					g2.setFont(hindiFont);
					drawPageBorder();
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
			
			if(isNewAdded) {
				currPos=curY; //for new page currPos to be updated
				isNewAdded =false;
			}
			
			return curY;
		}
	

}
