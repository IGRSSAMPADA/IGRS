package com.wipro.igrs.rcms.pdf;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Arrays;


import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.rcms.bd.RevenueCaseBD;
import com.wipro.igrs.rcms.entity.RCMSResponse;

public class GenerateTeritorialCourtReceipt {

	
	static PropertiesFileReader pr = null;

	//public static final String FONT = "C:/Users/AN350799/shared2/font/ARIALUNI.ttf";
	public static String FONT =null; 

	//static Font f = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	 //private static String FILE = "c:/temp/RCMS_RECEIPT_NEW.pdf";
	    private static String lang = "hn";
	    
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
				//Graphics2D g2 = cb.createGraphicsShapes(w, h);
				 g2 = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				cb.addTemplate(pdt,5,-7);
				
				 drawPageBorder(); //border on first page , other page border handle in drawstring method
				 
				int centerOfPage = w / 2;
				
				String content = (lang=="en")?TerritorialCourtConstant.HN_HEADING_1:TerritorialCourtConstant.HN_HEADING_1;
				hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,10.0F);
				g2.setFont(hindiFontBold);
				 currPos = drawString(g2, content, 230, (int) (5D * h) / 100, w);
							
				String imageUrl = pr.getValue("RCMS_LOGG_PATH")+"/"+pr.getValue("RCMS_LOGO_FILENAME");//"C:/Users/AN350799/Downloads/" +"rcmsLogo.jpg";

			     Image image = Image.getInstance(imageUrl);
			     image.setAbsolutePosition(55, h-currPos- image.getHeight()-5);
			     document.add(image);

			     	hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,15.0F);
					g2.setFont(hindiFontBold);
					 content = (lang=="en")?TerritorialCourtConstant.HN_HEADING_2:TerritorialCourtConstant.HN_HEADING_2;
					 currPos = drawString(g2, content, 200, currPos+(int) (4D * h) / 100, w);
					
					 g2.drawLine( 200, currPos+(int) (.5D * h) / 100, 400,currPos+(int) (.5D * h) / 100); //X
					 
					hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,15.0F);
					g2.setFont(hindiFontBold);
					 content = (lang=="en")?TerritorialCourtConstant.HN_HEADING_3:TerritorialCourtConstant.HN_HEADING_3;
					 currPos = drawString(g2, content, 150, currPos+(int) (2.5D * h) / 100, w-150);
					
					hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,12.0F);
					g2.setFont(hindiFontBold);
					 content = (lang=="en")?TerritorialCourtConstant.HN_HEADING_DATE:TerritorialCourtConstant.HN_HEADING_DATE;
					 content= content+ " : "+ rcmsResp.getRegistrationDate();
					 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w);
			     
					 
					 g2.drawLine(50, (currPos+(int) (1.5D * h) / 100), 540, (currPos+(int) (1.5D * h) / 100));
					 g2.drawLine(50, (currPos+(int) (2D * h) / 100), 540, (currPos+(int) (2D * h) / 100));
			
			

			//for(int p=0;p<regTxnId.length;p++) {
				//rcmsReceipt( regTxnId ,  propId[p]);
			//}
						 //iterate in loop
				rcmsReceipt( regTxnId, rcmsResp );
			
			 //close the box
			 content = (lang=="en")?TerritorialCourtConstant.HN_NOTE:TerritorialCourtConstant.HN_NOTE;
			 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w-25);
			 
			 
	     
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
	  		
	  		
				 hindiFont = hindiFont.deriveFont(12.0F);
					g2.setFont(hindiFont);
				 
				 String content = (lang=="en")?TerritorialCourtConstant.HN_CONTENT_1:TerritorialCourtConstant.HN_CONTENT_1;
				 content =  content + " "+ mpNo+" " ;
				 content = content + ((lang=="en")?TerritorialCourtConstant.HN_CONTENT_1_1:TerritorialCourtConstant.HN_CONTENT_1_1) + " "+doctype;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_1_2:TerritorialCourtConstant.HN_CONTENT_1_2)+ " "+sellerFirstName;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_1_3:TerritorialCourtConstant.HN_CONTENT_1_3) + " "+buyerFirstName;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_1_4:TerritorialCourtConstant.HN_CONTENT_1_4) + " "+buyerFirstfatherName;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_1_5:TerritorialCourtConstant.HN_CONTENT_1_5) + " "+gramPanOrMukhyalay;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_1_6:TerritorialCourtConstant.HN_CONTENT_1_6);
				 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w-25);
			
				 
				 hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,12.0F);
					g2.setFont(hindiFontBold);
				 content = (lang=="en")?TerritorialCourtConstant.HN_CONTENT_HEADING_2:TerritorialCourtConstant.HN_CONTENT_HEADING_2;
				 
				 System.out.println("currPos before heading anusuchi - "+currPos);
				 
				 isNewReq = checkForNewPage(currPos,100);
				 
				 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w-25);
				 
				 System.out.println("currPos after anusuchi - "+currPos);
				 //table to be created-----------------
				 
				 ArrayList list = rcBD.getKhasraDetail(regTxnId, lang);
				 
				 if(list!=null)
				 {
					 int[]  tempPosition = new int[4];
					 int tempPos;
					 int upperLen = 0;
					 int headerPad = (int) (4D * h) / 100;
					 int rowPad = (int) (5D * h) / 100;
					 
					 hindiFontBold = hindiFontBold.deriveFont(Font.BOLD,12.0F);
					 g2.setFont(hindiFontBold);

					 	isNewReq = checkForNewPage(currPos,150);
					 	
					 	
					 if(!isNewReq) {
						 g2.drawLine(50, (currPos+(int)(0.5D*h)/100), 540, (currPos+(int)(0.5D*h)/100));
				  			 upperLen = currPos+(int)(0.5D*h)/100;
					 }
					 	
					 
					 	tempPosition[0]=drawString(g2, TerritorialCourtConstant.HN_TABLE1_HD1, 50, (currPos + headerPad), 80);
					 	if(isNewReq) {
					 						g2.drawLine(50, (currPos-(int)(2D*h)/100) , 540, (currPos-(int)(2D*h)/100));
					 						upperLen = (currPos-(int)(2D*h)/100) ;
					 						headerPad = 0;
					 	}
						tempPosition[1]=drawString(g2, TerritorialCourtConstant.HN_TABLE1_HD2, 130, (currPos + headerPad), 70);
						tempPosition[2]=drawString(g2, TerritorialCourtConstant.HN_TABLE1_HD3, 200, (currPos + headerPad), 170);
						tempPosition[3]=drawString(g2, TerritorialCourtConstant.HN_TABLE1_HD4, 370, (currPos + headerPad), 170);
					 
						int finalPos = sortingStringArray(tempPosition);
			  			currPos=finalPos;
			  			hindiFont = hindiFont.deriveFont(10.0F);
			  			g2.setFont(hindiFont);
			  			
			  			g2.drawLine(50, (finalPos), 540, (finalPos));
					 
			  			 isNewReq = checkForNewPage(currPos,150);
			  			 
			  			 if(isNewReq) {
			  				 				g2.drawLine(50, (currPos+(int)(5D*h)/100), 540, (currPos+(int)(5D*h)/100));
			  				 				upperLen = (currPos+(int)(5D*h)/100);
			  			 }
			  			
					 if(list.size()>0) {
						 for(int i =0;i<list.size();i++) {
							 ArrayList subList = (ArrayList) list.get(i);
							 
							 currPos = currPos + (int) (1.5D * h) / 100;
							 tempPosition = new int[4];
							 
							 for(int j =0;j<subList.size();j++) {
								 //subList.get(0);//khasra no
								// subList.get(1);//rakba no
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

								 isNewReq = checkForNewPage(currPos,150);
								 
								 	tempPosition[0]=drawString(g2,subList.get(0).toString(), 50, (currPos + rowPad), 80);
								 	if(isNewReq) {
				 						g2.drawLine(50, (currPos-(int)(2D*h)/100) , 540, (currPos-(int)(2D*h)/100));
				 						upperLen = (currPos-(int)(2D*h)/100) ;
				 						rowPad = 0;
								 	}
									tempPosition[1]=drawString(g2, subList.get(1).toString(), 130, (currPos + rowPad), 70);
									tempPosition[2]=drawString(g2, sellerNames.toString(), 200, (currPos + rowPad), 170);
									tempPosition[3]=drawString(g2, buyerNames.toString(), 370, (currPos + rowPad), 170);

							 }
							 
							 	currPos=sortingStringArray(tempPosition);
							 	tempPos = currPos + (int) (.5D * h) / 100;
								
								g2.drawLine(50, tempPos, 540, tempPos);
								g2.drawLine(50, tempPos, 50, upperLen);
								g2.drawLine(130, tempPos, 130, upperLen);
								g2.drawLine(200, tempPos, 200, upperLen);
								g2.drawLine(370, tempPos, 370, upperLen);
								g2.drawLine(540, tempPos, 540, upperLen);
									
								currPos = currPos + (int) (1.5D * h) / 100;
						 }
						 
						 	
						
					 }
					 
				 }
				 
				 
				 hindiFont = hindiFont.deriveFont(12.0F);
					g2.setFont(hindiFont);
				 
				 content = (lang=="en")?TerritorialCourtConstant.HN_CONTENT_2:TerritorialCourtConstant.HN_CONTENT_2;
				 content =  content + " "+ courtdate+" " ;
				 content = content + ((lang=="en")?TerritorialCourtConstant.HN_CONTENT_2_1:TerritorialCourtConstant.HN_CONTENT_2_1) + " "+rcmsAppId;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_2_2:TerritorialCourtConstant.HN_CONTENT_2_2)+ " "+coutcaseNo;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_2_3:TerritorialCourtConstant.HN_CONTENT_2_3) + " "+courtName;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_2_4:TerritorialCourtConstant.HN_CONTENT_2_4) + " "+courtTehsil;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_2_5:TerritorialCourtConstant.HN_CONTENT_2_5) + " "+courtDist;
				 content = content + " "+((lang=="en")?TerritorialCourtConstant.HN_CONTENT_2_6:TerritorialCourtConstant.HN_CONTENT_2_6);
				 
				 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w-25);
				 
				 content = (lang=="en")?TerritorialCourtConstant.HN_CONTENT_3:TerritorialCourtConstant.HN_CONTENT_3;
				 currPos = drawString(g2, content, 50, currPos+(int) (5D * h) / 100, w-25);
				 
				 g2.drawLine( 55, currPos+(int) (2D * h) / 100, 535,currPos+(int) (2D * h) / 100); //X
				 
				 

				 
				
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
		
		public  int sortingStringArray(int [] arr){
			int len = arr.length;
			 Arrays.sort(arr);
			return (arr[len-1]);
		}
		private  int drawString(Graphics g, String s, int x, int y, int width) {
			//check for new page
			if(isBottom) {
		 			if (y >= (h - 75)) {
						g2.dispose();
						document.newPage();
						cb = writer.getDirectContent();
						g2 = cb.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
						hindiFont = hindiFont.deriveFont(12.0F);
						y = (int) (5D * h) / 100;
						g2.setFont(hindiFont);
						drawPageBorder();
						isNewAdded = true;
						g=g2;
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
