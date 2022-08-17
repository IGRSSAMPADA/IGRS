package com.wipro.igrs.ACL.action;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import oracle.net.aso.s;

import org.apache.log4j.Logger;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.struts.action.ActionForward;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.PresentationSeal;
import com.newgen.burning.Presenter;
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
import com.wipro.igrs.DeliveryOfDocuments.action.DeliveryOfDocumentsAction;
import com.wipro.igrs.DeliveryOfDocuments.bd.DeliveryOfDocumentsBD;
import com.wipro.igrs.DeliveryOfDocuments.dto.DeliveryOfDocumentsDTO;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.rcms.bd.RevenueCaseBD;
import com.wipro.igrs.rcms.constant.RevenueCaseConstant;
import com.wipro.igrs.rcms.dao.RevenueCaseDAO;
import com.wipro.igrs.rcms.entity.BuyerDetails;
import com.wipro.igrs.rcms.entity.BuyerDetailsSampadaList;
import com.wipro.igrs.rcms.entity.KhasraRecord;
import com.wipro.igrs.rcms.entity.KhasraRecordList;
import com.wipro.igrs.rcms.entity.PropertyDetails;
import com.wipro.igrs.rcms.entity.RCMSDetailsSampada;
import com.wipro.igrs.rcms.entity.RCMSResponse;
import com.wipro.igrs.rcms.entity.SellerDetails;
import com.wipro.igrs.rcms.entity.SellerDetailsSampadaList;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.form.RegCompCheckerForm;
import com.wipro.igrs.util.GUIDGenerator;

public class rcmssenddata {

	private static Logger logger = (Logger) Logger.getLogger(rcmssenddata.class);

	public static void merge() throws Exception {

		DeliveryOfDocumentsBD dodBD = new DeliveryOfDocumentsBD();
		RegCompCheckerBD bd = new RegCompCheckerBD();
		DeliveryOfDocumentsDTO dto = new DeliveryOfDocumentsDTO();
		// flag = true;
		// String stCumDt = request.getParameter("regDtCumStatus");
		// String stCumDtList[] = stCumDt.split("~");
		String eRegNo = "MP059692022A1000040";
		String regTxnID = dodBD.getRegTxnID(eRegNo);
		String printFlag = bd.getPrintFlag(regTxnID);
		if ("".equalsIgnoreCase(printFlag)) {

		}

		else {
			RegCompCheckerForm regForm = new RegCompCheckerForm();
			String headerimg = "C:/Users/SA334510/Documents/shared1/header img.jpg";
			regForm.setHeaderImg(headerimg);
			String uid = bd.getUsrId(regTxnID);
			String oid = bd.getOfcId(regTxnID);
			regForm.getRegDTO().setRegInitNumber(regTxnID);
			regForm.getRegDTO().setRegCompleteId(eRegNo);
			regForm.setUserId(uid);
			SealsBD sealbd = new SealsBD();
			String lang = sealbd.getLangauge(regTxnID);
			boolean flag = false;

			try {
				flag = bd.generateFinalCertificate(regForm, uid, oid, lang);
				if (flag) {

					bd.updatePrintFlag(regTxnID);
				}
			} catch (Exception e) {
				logger.debug("error in generating document");
				logger.debug(e.getStackTrace());
				throw new Exception();
			}
		}

		String guideId = GUIDGenerator.getGUID();
		// String filePath = regForm.getFinalDocPath();
		dto.setFileNameOwm(RegCompCheckerConstant.FILE_PATH_FOR_OWM + guideId + File.separator + eRegNo.trim() + RegCompCheckerConstant.NAME_OF_FINAL_DOC_EXETENSION);
		logger.debug("guideid--------------->" + guideId);
		String outputPath = RegCompCheckerConstant.FILE_PATH_FOR_OWM + guideId + File.separator;
		File output;
		output = new File(outputPath.toString());

		if (output.exists() == false) {
			logger.debug("Parent not found creating directory....");
			output.mkdirs();
			if (output.exists()) {
				logger.debug("path created------------------>");
			}
		}
		try {
			// BurningImageAndText burnObj = new
			// BurningImageAndText();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			/*
			 * String stCumDt = request.getParameter("regDtCumStatus"); String
			 * stCumDtList[]= stCumDt.split("~"); String eRegNo=stCumDtList[0];
			 * String regTxnID=dodBD.getRegTxnID(eRegNo);
			 */
			dto.setRegInitNumber(regTxnID);

			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			PresentationSeal sealContent = new PresentationSeal();
			ArrayList<Presenter> presenterList = new ArrayList();
			// ArrayList<Claimant> claimantList = new
			// ArrayList();
			// PropertiesFileReader pr =
			// PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
			ODServerDetails connDetails = new ODServerDetails();
			Dataclass metaDataInfo = new Dataclass();
			connDetails.setAppServerIp(pr.getValue("AppServerIp"));
			connDetails.setCabinetName(pr.getValue("CabinetName"));
			connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
			connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
			connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
			connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
			connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			metaDataInfo.setUniqueNo(dto.getRegInitNumber());
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
			if (null == metaDataInfo.getUniqueNo()) {
				// session.setAttribute("checkStatus", "DMSError");
				// return new
				// ActionForward("/jsp/login/UserRoleValidationPage.jsp");
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				// session.setAttribute("checkStatus", "DMSError");
				// return new
				// ActionForward("/jsp/login/UserRoleValidationPage.jsp");
			}
			docOperations.downloadDocument(connDetails, metaDataInfo, outputPath, "F");
			/*
			 * File folder=new File(outputPath); File[] listOfFiles =
			 * folder.listFiles(); for (int z = 0; z < listOfFiles.length; z++)
			 * { String tempPath=listOfFiles[z].getPath();
			 * dto.setFileNameOwm(tempPath); }
			 */

			dto.setOwmChk("Y");
			dto.setRegCompAppletNo(eRegNo);
			// dodBD.statusPrintedUpdate(eRegNo, userId,
			// officeId);

		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			throw new Exception();
		}

		/*
		 * eForm.setActionName(""); eForm.setFormName(""); formName = "";
		 * actionName = ""; forwardPage = "dodsrsearch";
		 */
		/*
		 * response.setContentType("application/download");
		 * 
		 * 
		 * response.setHeader("Content-Disposition", "attachment; filename=" +
		 * URLEncoder.encode(filePath,"UTF-8"));
		 * 
		 * File fileToDownload = new File(filePath); FileInputStream
		 * fileInputStream = new FileInputStream(fileToDownload); OutputStream
		 * out = response.getOutputStream(); byte[] buf = new byte[2048];
		 * 
		 * int readNum; while ((readNum=fileInputStream.read(buf))!=-1) {
		 * out.write(buf,0,readNum); } fileInputStream.close(); out.close();
		 */

	}
	public static void main(String[] args) throws Exception {
		// merge();
		String responseString = "<RCMSResponse> <status>Success</status>  <errorType>Success</errorType>  <ApplicationNumb>0642C11-CYAPP-16</ApplicationNumb>" + "<CaseNumb>0016/अ-6/2021-22</CaseNumb>" + "<CourtName>न्यायालय तहसीलदार,  जोबट, तहसील जोबट, जिला आलीराजपुर,  (म.प्र)</CourtName>" + "<DateofPresentation/>" + "<Tehsil>जोबट</Tehsil>" + "<District>Alirajpur (आलीराजपुर)</District>" + "<buyerName>वरूण  श्रीमाल</buyerName>    <buyerFather>श्री ललित</buyerFather>    <sellerName>अंकित  सोलंकी</sellerName>" + "<sellerFather>श्री सोलंकी अंकित</sellerFather>    <GramPanchayat>उमरी</GramPanchayat> </RCMSResponse>";
		// System.out.println(responseString);
		RCMSResponse response = null;
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RCMSResponse.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(responseString);
			response = (RCMSResponse) jaxbUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String xmlString = "<RCMSRequestSampada>                                                                                               " + "    <docTypeId>Conveyance</docTypeId>                                                                              " + "    <documentDate>2022-02-14</documentDate>                                                                        " + "    <documentNo>e1122131</documentNo>                                                                            " + "    <Registry_URL>http://localhost:8080/IGRS/login.do?downloadFilePath=106420702222000002&amp;type=F</Registry_URL> " + "    <mutationTypeId>Conveyance</mutationTypeId>                                                                     " + "    <officeId>OFC969</officeId>                                                                                     " + "    <Property>                                                                                                      " + "        <khasraRecordList>                                                                                          " + "            <khasraRecord>                                                                                          " + "                <AreaType>1</AreaType>                                                                              " + "                <buyerDetailsSampadaList>                                                                           " + "                    <BuyerDetails>                                                                                  " + "                        <aadharNumber></aadharNumber>                                                               " + "                        <address>test</address>                                                                     " + "                        <caste></caste>                                                                             " + "                        <city></city>                                                                               " + "                        <country>1</country>                                                                        " + "                        <deptCode>22</deptCode>                                                                     " + "                        <districtId>5</districtId>                                                                  " + "                        <emailId></emailId>                                                                         " + "                        <fatherName></fatherName>                                                                   " + "                        <firstName>test</firstName>                                                                 " + "                        <gender></gender>                                                                           " + "                        <idNumber></idNumber>                                                                       " + "                        <idType></idType>                                                                           " + "                        <lastName></lastName>                                                                       " + "                        <middleName></middleName>                                                                   " + "                        <mobileNumber></mobileNumber>                                                               " + "                        <nameAadharCard></nameAadharCard>                                                           " + "                        <ownerId></ownerId>                                                                         " + "                        <ownerShare>100</ownerShare>                                                                " + "                        <ownershipTypeIdBuyer>3</ownershipTypeIdBuyer>                                              " + "                        <phoneNumber></phoneNumber>                                                                 " + "                        <relationType></relationType>                                                               " + "                        <stateId>1</stateId>                                                                        " + "                    </BuyerDetails>                                                                                 " + "                </buyerDetailsSampadaList>                                                                          " + "               <District>42</District>                                                                              " + "			   <KhasraNumber>104/2/1</KhasraNumber>                                                                  " + "			   <KhasraSellableArea>2</KhasraSellableArea>                                                            " + "			   <PatwariHalka>102377</PatwariHalka>                                                                   " + "			   <RICircle/>                                                                                           " + "                <sellerDetailsSampadaList>                                                                          " + "                    <SellerDetails>                                                                                 " + "                        <deptCode>22</deptCode>                                                                     " + "                        <fatherName>fdsf</fatherName>                                                               " + "                        <firstName>test</firstName>                                                                 " + "                        <lastName></lastName>                                                                       " + "                        <middleName></middleName>                                                                   " + "                        <ownershipTypeIdSeller>3</ownershipTypeIdSeller>                                            " + "                    </SellerDetails>                                                                                " + "                </sellerDetailsSampadaList>                                                                         " + "                <Tehsil>06420702</Tehsil>                                                                           " + "				<Village>372048</Village>                                                                            " + "				<khasraID>0001</khasraID>                                                                            " + "				<transactionType>0002</transactionType>                                                              " + "				<ULPIN>0003</ULPIN>                                                                                  " + "				<khasraUniqueId>0004</khasraUniqueId>                                                                " + "				<lgdcode>0005</lgdcode>                                                                              " + "            </khasraRecord>                                                                                         " + "                                                                                                                    " + "        </khasraRecordList>                                                                                         " + "        <propertyId>506420702202202719164</propertyId>                                                              " + "        <TotalKhasraArea>2</TotalKhasraArea>                                                                        " + "        <totalSellableArea>2</totalSellableArea>                                                                    " + "    </Property>                                                                                                     " + "    <rcmsPassword>rcms@123</rcmsPassword>                                                                           " + "	<ApplicationFee>100</ApplicationFee>                                                                             " + "	<TransactionID>13146464971646646</TransactionID>                                                                 " + "	<TransactionDate>2022-03-25</TransactionDate>                                                                    " + "    <URL_Form_A>C://Users//SA334510//Documents//IGRSCode//testRcmsPdf.pdf</URL_Form_A>                              " + "    <URL_Form_A1>C://Users//SA334510//Documents//IGRSCode//testRcmsPdf.pdf</URL_Form_A1>                            " + "    <URL_Form_A2>C://Users//SA334510//Documents//IGRSCode//testRcmsPdf.pdf</URL_Form_A2>                            " + "</RCMSRequestSampada>";

		/*
		 * RCMSResponse rcms = sendRCMSRequest(xmlString, "wertyhrr");
		 * 
		 * System.out.println(rcms.getApplicationNumber());
		 * System.out.println(rcms.getBuyerFather());
		 * System.out.println(rcms.getBuyerName());
		 * System.out.println(rcms.getCaseNumber());
		 * System.out.println(rcms.getCourtName());
		 */
		RevenueCaseDAO rr = new RevenueCaseDAO();
		ArrayList tmp = new ArrayList();
		// tmp = rr.getKhasraDetail("250518000599","531201802517798");
		for (int i = 0; i < tmp.size(); i++) {
			ArrayList tmp1 = (ArrayList) tmp.get(i);

			System.out.println((String) tmp1.get(0));
			System.out.println((String) tmp1.get(1));
			ArrayList buyer = (ArrayList) tmp1.get(2);
			for (int b = 0; b < buyer.size(); b++) {
				System.out.println("buyer -- " + (String) buyer.get(b));
			}
			ArrayList seller = (ArrayList) tmp1.get(3);
			for (int b = 0; b < seller.size(); b++) {
				System.out.println("seller -- " + (String) seller.get(b));
			}

		}
		// RCMSResponse resp = rr.getReceiptDetail("30918000001",
		// "540201802715387");
		// System.out.println(resp.getApplicationNumber() + "   " +
		// resp.getBuyerFather());
		check(xmlString);
	}
	public static void check(String xmlString1) {

		ArrayList propIdList = new ArrayList();
		RevenueCaseBD revenueBD = new RevenueCaseBD();
		boolean propFlag = false;
		try {

			// Check for property with beresia tehsil level in SQL query
			// ****Removed
			String regTxnID = "30622000004";
			propIdList = new ArrayList();
			ArrayList propIDs = revenueBD.getPropIdsForRCMS(regTxnID);
			PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
			String url1 = property.getValue("URL_FINAL_DOCUMENT_1");
			String url2 = property.getValue("URL_FINAL_DOCUMENT_2");
			String finalCopyUrl = url1 + regTxnID + url2;
			if (propIDs != null && !propIDs.isEmpty()) {

				// Property Null check to be added
				for (int j = 0; j < propIDs.size(); j++) {
					ArrayList list2 = new ArrayList();
					list2 = (ArrayList) propIDs.get(j);
					propIdList.add(list2.get(0));
				}

				// Tehsil Check for Pilot

				// Update reg txn table with New Request status

				boolean transFlag = revenueBD.updateRegTrnStatus(regTxnID, "1");

				logger.debug("Property ID List :- " + propIdList);

				logger.debug("Get XML data to be sent to RCMS");

				int counter = 1;
				RCMSDetailsSampada rcmsds;
				ArrayList<PropertyDetails> propds;
				PropertyDetails prds;
				KhasraRecordList krList;
				ArrayList<KhasraRecord> kr;
				BuyerDetailsSampadaList bdsl;
				SellerDetailsSampadaList sdsl;
				ArrayList<SellerDetails> sdList;
				ArrayList<BuyerDetails> bdList;
				KhasraRecord krr;

				// Get common reg details
				rcmsds = new RCMSDetailsSampada();
				propds = new ArrayList<PropertyDetails>();

				ArrayList commonfields = revenueBD.getRegistrationDetails(regTxnID);
				logger.info("commonfields: " + commonfields);
				ArrayList commonfieldsNew = new ArrayList();
				for (int j = 0; j < commonfields.size(); j++) {
					ArrayList list1 = new ArrayList();
					list1 = (ArrayList) commonfields.get(j);
					commonfieldsNew.add(list1.get(0));
					commonfieldsNew.add(list1.get(1));
					commonfieldsNew.add(list1.get(2));
					commonfieldsNew.add(list1.get(3));
					commonfieldsNew.add(list1.get(4) == null ? "NA" : list1.get(4));
					commonfieldsNew.add(list1.get(5) == null ? "NA" : list1.get(5));
					commonfieldsNew.add(list1.get(6) == null ? "NA" : list1.get(6));
				}

				// Fetch RegNo,doctypeID
				String registrationNo = (String) commonfieldsNew.get(0);
				String MutationTypeId = (String) commonfieldsNew.get(2);
				String docTypeId = (String) commonfieldsNew.get(2);

				String officeId = (String) commonfieldsNew.get(3);
				logger.info("Registration No:" + commonfieldsNew.get(0));

				// Set RCMS password
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String password = pr.getValue("RCMS_PASSWORD");
				rcmsds.setURL_Form_6((String) commonfieldsNew.get(4));
				rcmsds.setURL_Form_6A((String) commonfieldsNew.get(5));
				rcmsds.setURL_Form_6B((String) commonfieldsNew.get(6));

				String formViA1URL = rcmsds.getURL_Form_6A();
				formViA1URL = "C://Users//SA334510//Documents//shared1//font//test.pdf";
				if (!"NA".equalsIgnoreCase(formViA1URL)) {
					File formViA1Pdf = new File(formViA1URL);
					PdfReader reader = new PdfReader(formViA1Pdf.getAbsolutePath());
					int count = reader.getNumberOfPages();
					String mpPdfFileName = formViA1Pdf.getName();
					ArrayList singPath = new ArrayList();
					singPath.add("C://Users//SA334510//Documents//IGRSCode//signpath//signature.gif");

					float pageWidth = PageSize.A4.getWidth();
					String fontFilePath = pr.getValue("dms_hindi_font_path");
					java.awt.Font englishFont = java.awt.Font.createFont(0, new FileInputStream(fontFilePath));
					float pageHeight = PageSize.A4.getHeight();
					int w = (int) pageWidth - 20;
					int h = (int) pageHeight;
					System.out.println("Page Height --> "+h);
					//System.out.println("width --> " + w);
					//System.out.println("height -->" + h);
					String singedPdfPath = "C://Users//SA334510//Documents//shared1//font//" + File.separator + "testSigned.pdf";
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
					//float width = 50f;
					//float height = 30f;
					int rowCount=1;
					int cCount=0;
					float width =  40;
					float header = 80;
					float imageGap = 0;
					ArrayList signPath = revenueBD.getPartySignPath(regTxnID);
					//for seller
					for (int i = 0; i < 2; i++) {
						
						boolean chk = false;
						if(cCount<2){
							chk=true;
						}
						cCount = cCount+1;

						ArrayList signPathList =new ArrayList();
						signPathList.add("C://Users//SA334510//Documents//IGRSCode//signpath//signature.gif");//(ArrayList) signPath.get(i);
						String partyName = (String) signPathList.get(0);
						partyName = "raman";
						String signaturePath = (String) signPathList.get(0);
						
						String signatureLocation = signaturePath.substring(0,signaturePath.lastIndexOf("/"));
						if(i==0){
							signaturePath="C://Users//SA334510//Documents//shared1//one//";
						}else{
							signaturePath="C://Users//SA334510//Documents//shared1//two//";
						}
						String cropFileType = cropImage((String) signaturePath+"signature.gif", signatureLocation, "cropsignature", "gif");
						com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(signaturePath+"signature.gif");
						img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
						
						
						float height = h - (img.getScaledHeight() + header+imageGap);
						System.out.println("Left page heigh .... "+(h-height));
						if((h-height)>642){
							System.out.println("New Page added....");
							g2.dispose();
							document.newPage();
							cb = writer.getDirectContent();
							g2 = cb.createGraphicsShapes(w, h);
							cCount=0;
							imageGap=0;
							height = h - (img.getScaledHeight() + header+imageGap);
						}
						System.out.println("Image scaled Height --> "+img.getScaledHeight());
						System.out.println("Header --> "+header+" Image Gap --> "+imageGap+" and height is --> "+height);
						
						//img.setAbsolutePosition(0, 0);
						/*if(i%2==1 ){
							img.setAbsolutePosition(width, height);
							width =40;
							imageGap=imageGap+80;
						}else{
							img.setAbsolutePosition(width, height);
							width = img.getWidth()+50+30;
						}*/
						
						int startPos=40;
						drawString(g2, partyName, startPos, (int)(h-height-(img.getScaledHeight()/2)), 300);
						img.setAbsolutePosition(width+300, height);
						width =40;
						imageGap=imageGap+80;
						/*if(rowCount==1 & chk){
							img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
							img.setAbsolutePosition(width, h - (img.getScaledHeight() + height));
							width = img.getWidth()+50+40;
							rowCount=rowCount+1;
							System.out.println(i+" th image height is "+(h - (img.getScaledHeight() + height))+ " and width is "+width);
						}else if(rowCount==2 & chk){
							img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
							img.setAbsolutePosition(width, h - (img.getScaledHeight() + height));
							System.out.println(i+" th image height is "+(h - (img.getScaledHeight() + height))+ " and width is "+width);
							width = 50;
							height = h-(2*img.getScaledHeight() + height+100);
							rowCount=1;
						}else if(rowCount==1 & !chk){
							img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
							img.setAbsolutePosition(width, height);
							System.out.println(i+" th image height is "+height+ " and width is "+width);
							width = img.getWidth()+50+40;
							rowCount=rowCount+1;
						}else if(rowCount==2 & !chk){
							img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
							img.setAbsolutePosition(width, height);
							System.out.println(i+" th image height is "+ height+ " and width is "+width);
							width = 50;
							height = h-(2*img.getScaledHeight() + height+100);
							rowCount=1;
						}*/
						document.add(img);
						
					}
					g2.dispose();
					document.close();
					PDFMergerUtility obj = new PDFMergerUtility();
					obj.setDestinationFileName("C://Users//SA334510//Documents//shared1//font//test11.pdf");
					obj.addSource(formViA1URL);
					obj.addSource(singedPdfPath);

					// Merging documents

					obj.mergeDocuments();

				}
				
				String formViA2URL = rcmsds.getURL_Form_6B();
				formViA2URL="NA";
				formViA1URL="NA";
				//formViA1URL = "C://Users//SA334510//Documents//shared1//font//test.pdf";
				if (!"NA".equalsIgnoreCase(formViA2URL)) {
					File formViA2PDF = new File(formViA2URL);
					PdfReader reader = new PdfReader(formViA2PDF.getAbsolutePath());
					int count = reader.getNumberOfPages();
					String mpPdfFileName = formViA2PDF.getName();
					/*ArrayList singPath = new ArrayList();
					singPath.add("C://Users//SA334510//Documents//IGRSCode//signpath//signature.gif");*/

					float pageWidth = PageSize.A4.getWidth();
					String fontFilePath = pr.getValue("dms_hindi_font_path");
					java.awt.Font englishFont = java.awt.Font.createFont(0, new FileInputStream(fontFilePath));
					float pageHeight = PageSize.A4.getHeight();
					int w = (int) pageWidth - 20;
					int h = (int) pageHeight;
					System.out.println("Page Height --> "+h);
					//System.out.println("width --> " + w);
					//System.out.println("height -->" + h);
					String singedPdfPath = "C://Users//SA334510//Documents//shared1//font//" + File.separator + "testSigned.pdf";
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
					//float width = 50f;
					//float height = 30f;
					int rowCount=1;
					int cCount=0;
					float width =  40;
					float header = 80;
					float imageGap = 0;
					ArrayList signPath = revenueBD.getPartySignPath(regTxnID);
					//for seller
					for (int i = 0; i < signPath.size(); i++) {
						
						boolean chk = false;
						if(cCount<2){
							chk=true;
						}
						cCount = cCount+1;
						ArrayList signPathList = (ArrayList) signPath.get(i);
						String partyName = (String) signPathList.get(0);
						partyName = partyName.trim();
						String signaturePath = (String) signPathList.get(1);
						if(i==0){
							signaturePath="C://Users//SA334510//Documents//shared1//one//";
						}else{
							signaturePath="C://Users//SA334510//Documents//shared1//two//";
						}
						String signatureLocation = signaturePath.substring(0,signaturePath.lastIndexOf("/"));
						String cropFileType = cropImage((String) signaturePath+"signature.gif", signatureLocation, "cropsignature", "gif");
						com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(signaturePath+"signature.gif");
						img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
						
						
						float height = h - (img.getScaledHeight() + header+imageGap);
						System.out.println("Left page heigh .... "+(h-height));
						if((h-height)>642){
							System.out.println("New Page added....");
							g2.dispose();
							document.newPage();
							cb = writer.getDirectContent();
							g2 = cb.createGraphicsShapes(w, h);
							cCount=0;
							imageGap=0;
							height = h - (img.getScaledHeight() + header+imageGap);
						}
						System.out.println("Image scaled Height --> "+img.getScaledHeight());
						System.out.println("Header --> "+header+" Image Gap --> "+imageGap+" and height is --> "+height);
						
						//img.setAbsolutePosition(0, 0);
						/*if(i%2==1 ){
							img.setAbsolutePosition(width, height);
							width =40;
							imageGap=imageGap+80;
						}else{
							img.setAbsolutePosition(width, height);
							width = img.getWidth()+50+30;
						}*/
						
						int startPos=40;
						drawString(g2, partyName, startPos, (int)(h-height-(img.getScaledHeight()/2)), 300);
						img.setAbsolutePosition(width+300, height);
						width =40;
						imageGap=imageGap+80;
						/*if(rowCount==1 & chk){
							img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
							img.setAbsolutePosition(width, h - (img.getScaledHeight() + height));
							width = img.getWidth()+50+40;
							rowCount=rowCount+1;
							System.out.println(i+" th image height is "+(h - (img.getScaledHeight() + height))+ " and width is "+width);
						}else if(rowCount==2 & chk){
							img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
							img.setAbsolutePosition(width, h - (img.getScaledHeight() + height));
							System.out.println(i+" th image height is "+(h - (img.getScaledHeight() + height))+ " and width is "+width);
							width = 50;
							height = h-(2*img.getScaledHeight() + height+100);
							rowCount=1;
						}else if(rowCount==1 & !chk){
							img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
							img.setAbsolutePosition(width, height);
							System.out.println(i+" th image height is "+height+ " and width is "+width);
							width = img.getWidth()+50+40;
							rowCount=rowCount+1;
						}else if(rowCount==2 & !chk){
							img.scaleToFit(9 * w / 100.0F, (float) ((singlePresenterPerc - 5.9D) * h) / 100.0F);
							img.setAbsolutePosition(width, height);
							System.out.println(i+" th image height is "+ height+ " and width is "+width);
							width = 50;
							height = h-(2*img.getScaledHeight() + height+100);
							rowCount=1;
						}*/
						document.add(img);
						
					}
					g2.dispose();
					document.close();
					PDFMergerUtility obj = new PDFMergerUtility();
					obj.setDestinationFileName("C://Users//SA334510//Documents//shared1//font//test11.pdf");
					obj.addSource(formViA1URL);
					obj.addSource(singedPdfPath);

					// Merging documents

					obj.mergeDocuments();

				}

				ArrayList<String> paymentData = revenueBD.getPaymentDetail(regTxnID);
				rcmsds.setApplicationFee((paymentData.get(1) == "" ? "0" : paymentData.get(1)));
				rcmsds.setTransactionId((paymentData.get(0) == "" ? "NA" : paymentData.get(0)));
				rcmsds.setTransactionDate((paymentData.get(2) == "" ? "2022-02-22" : paymentData.get(2)));
				rcmsds.setFinalDocumentCopy(finalCopyUrl);
				rcmsds.setRcmsPassword(password);
				rcmsds.setDocumentNo((registrationNo != null && !registrationNo.isEmpty()) ? registrationNo : "");
				rcmsds.setMutationTypeId((MutationTypeId != null && !MutationTypeId.isEmpty()) ? MutationTypeId : "");
				rcmsds.setDocTypeId((docTypeId != null && !docTypeId.isEmpty()) ? docTypeId : "");
				rcmsds.setOfficeId((officeId != null && !officeId.isEmpty()) ? officeId : "");
				// Fetch Document Date
				String docDate = revenueBD.getDocDate(registrationNo);
				if (docDate != null && !docDate.isEmpty()) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedDate = dateFormat.parse(docDate);
					String newDate = dateFormat.format(parsedDate);
					rcmsds.setDocumentDate(newDate);
				} else {
					rcmsds.setDocumentDate("");
				}
				logger.info("Document Date: " + docDate);

				for (int i = 0; i < propIdList.size(); i++) {
					double totalKhasraArea = 0.0;
					prds = new PropertyDetails();
					krList = new KhasraRecordList();

					String propId = (String) propIdList.get(i);
					prds.setPropertyId(propId);
					String clrFlag = revenueBD.getClrFlag(propId); // for
					// fetching
					// party and
					// khasra
					// detail
					// from
					// separate
					// table in
					// case of
					// CLR and
					// Non-Clr
					// Get total sellable area
					String totalArea = revenueBD.getTotalSellableArea(propId);
					prds.setTotalSellableArea(totalArea);

					kr = new ArrayList<KhasraRecord>();

					// Get khasra IDs wrt Property ID
					ArrayList khasraIdLists = revenueBD.getAllKhasraIds(propId, clrFlag);
					ArrayList khasraIdList = new ArrayList();

					// Fetch Buyer and Seller Details
					// Party Details will be same for all khasras
					//System.out.println("CLR Flag is " + clrFlag);
					ArrayList buyerSellerPartyList = revenueBD.getBuyerSellerPartyList(regTxnID, propId, clrFlag);
					ArrayList sellerPartyList = (ArrayList) buyerSellerPartyList.get(1);
					logger.info("sellerPartyList:" + sellerPartyList);

					ArrayList buyerPartyList = (ArrayList) buyerSellerPartyList.get(0);
					logger.info("buyerList:" + buyerPartyList);

					// Get district, tehsil and other area related details

					ArrayList areaList = revenueBD.getAllAreaDetails(propId);
					logger.info("areaDetails: " + areaList);

					String landArea = revenueBD.getLandAreaForProp(propId);

					for (int m = 0; m < khasraIdLists.size(); m++) {
						khasraIdList = (ArrayList) khasraIdLists.get(m);

						krr = new KhasraRecord();
						bdsl = new BuyerDetailsSampadaList();
						sdsl = new SellerDetailsSampadaList();
						sdList = new ArrayList<SellerDetails>();
						bdList = new ArrayList<BuyerDetails>();

						String khasraNo = (String) khasraIdList.get(1);
						String khasraArea = (String) khasraIdList.get(2);

						// Store area details to xml object
						for (int j = 0; j < areaList.size(); j++) {
							ArrayList list1 = new ArrayList();
							list1 = (ArrayList) areaList.get(j);
							if (((String) list1.get(1)) != null && !((String) list1.get(1)).isEmpty()) {
								krr.setDistrict((String) list1.get(1));
							} else {
								krr.setDistrict("");
							}

							if (((String) list1.get(2)) != null && !((String) list1.get(2)).isEmpty()) {
								krr.setTehsil((String) list1.get(2));
							} else {
								krr.setTehsil("");
							}

							if (((String) list1.get(3)) != null && !((String) list1.get(3)).isEmpty()) {
								krr.setRICircle((String) list1.get(3));
							} else {
								krr.setRICircle("");
							}

							if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
								krr.setPatwariHalka((String) list1.get(4));
							} else {
								krr.setPatwariHalka("");
							}

							if (((String) list1.get(5)) != null && !((String) list1.get(5)).isEmpty()) {
								krr.setVillage((String) list1.get(5));
							} else {
								krr.setVillage("");
							}

							if (((String) list1.get(6)) != null && !((String) list1.get(6)).isEmpty()) {
								krr.setAreaType((String) list1.get(6));
							} else {
								krr.setAreaType("");
							}

							// Khasra Sellable Area
							if (khasraArea != null && !khasraArea.isEmpty()) {
								krr.setKhasraSellableArea(khasraArea);
								totalKhasraArea = totalKhasraArea + Double.parseDouble(khasraArea);
							} else {
								krr.setKhasraSellableArea("");
							}
							if (clrFlag.equalsIgnoreCase("Y")) {
								krr.setKhasraUniqueId((String) khasraIdList.get(6));
								krr.setTransactionType((String) khasraIdList.get(8));
								krr.setUlpin((String) khasraIdList.get(7));
								krr.setLgdcode((String) khasraIdList.get(9));
								krr.setKhasraID((String) khasraIdList.get(3));
							} else {
								krr.setKhasraUniqueId("NA");
								krr.setTransactionType("NA");
								krr.setUlpin("NA");
								krr.setLgdcode("AN");
								krr.setKhasraID("NA");
							}
							krr.setKhasraNumber(khasraNo);

						}

						// Seller Details iterating loop
						if (sellerPartyList.size() != 0) {
							for (int j = 0; j < sellerPartyList.size(); j++) {
								ArrayList sellerList = revenueBD.getSellerPartyList((String) sellerPartyList.get(j));
								for (int k = 0; k < sellerList.size(); k++) {
									SellerDetails sd = new SellerDetails();
									ArrayList list1 = new ArrayList();
									list1 = (ArrayList) sellerList.get(k);

									logger.info("***************Seller Details************************");
									if (list1.get(0).equals("1")) {
										if (((String) list1.get(5)) != null && !((String) list1.get(5)).isEmpty()) {
											sd.setFirstName((String) list1.get(5));
										} else {
											sd.setFirstName("");
										}

										sd.setMiddleName("");
										sd.setLastName("");
										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											sd.setOwnershipTypeIdSeller((String) list1.get(0));
										} else {
											sd.setOwnershipTypeIdSeller("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											sd.setDeptCode((String) list1.get(4));
										} else {
											sd.setDeptCode("");
										}
										if (((String) list1.get(8)) != null && !((String) list1.get(8)).isEmpty()) {
											sd.setFatherName((String) list1.get(8));
										} else {
											sd.setFatherName("");
										}
										sdList.add(sd);

									}
									if (list1.get(0).equals("2")) {
										if (((String) list1.get(1)) != null && !((String) list1.get(1)).isEmpty()) {
											sd.setFirstName((String) list1.get(1));
										} else {
											sd.setFirstName("");
										}

										if (((String) list1.get(2)) != null && !((String) list1.get(2)).isEmpty()) {
											sd.setMiddleName((String) list1.get(2));
										} else {
											sd.setMiddleName("");
										}
										if (((String) list1.get(3)) != null && !((String) list1.get(3)).isEmpty()) {
											sd.setLastName((String) list1.get(3));
										} else {
											sd.setLastName("");
										}

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											sd.setOwnershipTypeIdSeller((String) list1.get(0));
										} else {
											sd.setOwnershipTypeIdSeller("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											sd.setDeptCode((String) list1.get(4));
										} else {
											sd.setDeptCode("");
										}

										if (((String) list1.get(8)) != null && !((String) list1.get(8)).isEmpty()) {
											sd.setFatherName((String) list1.get(8));
										} else {
											sd.setFatherName("");
										}
										sdList.add(sd);
									}
									if (list1.get(0).equals("3")) {

										if (((String) list1.get(6)) != null && !((String) list1.get(6)).isEmpty()) {
											sd.setFirstName((String) list1.get(6));
										} else {
											sd.setFirstName("");
										}
										sd.setMiddleName("");
										sd.setLastName("");

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											sd.setOwnershipTypeIdSeller((String) list1.get(0));
										} else {
											sd.setOwnershipTypeIdSeller("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											sd.setDeptCode((String) list1.get(4));
										} else {
											sd.setDeptCode("");
										}

										sd.setFatherName("fdsf");
										sdList.add(sd);
										if (((String) list1.get(8)) != null && !((String) list1.get(8)).isEmpty()) {
											sd.setFatherName((String) list1.get(8));
										} else {
											sd.setFatherName("");
										}
									}

								}

							}
						} else {
							SellerDetails sd = new SellerDetails();
							sd.setFirstName("");
							sd.setMiddleName("");
							sd.setLastName("");
							sd.setOwnershipTypeIdSeller("");
							sd.setDeptCode("");

							sd.setFatherName("fdsf");
							sdList.add(sd);
						}

						// Buyer Details iterating loop
						if (buyerPartyList.size() != 0) {
							for (int j = 0; j < buyerPartyList.size(); j++) {
								ArrayList buyerList = revenueBD.getBuyerPartyList((String) buyerPartyList.get(j));
								logger.info("***************Buyer Details************************");
								for (int k = 0; k < buyerList.size(); k++) {

									String partyTxnId = (String) buyerPartyList.get(j);
									logger.info("partyTxnId" + partyTxnId);
									String share = revenueBD.getPartyShare(partyTxnId, propId, clrFlag, khasraNo);

									ArrayList list1 = new ArrayList();
									list1 = (ArrayList) buyerList.get(k);

									// Buyer Details
									BuyerDetails bd = new BuyerDetails();
									if (share != null && !share.isEmpty()) {
										bd.setOwnerShare(share.trim());
									} else {
										bd.setOwnerShare("");
									}
									bd.setOwnerId("");
									bd.setCity("");
									if (((String) list1.get(7)) != null && !((String) list1.get(7)).isEmpty()) {
										bd.setCountry((String) list1.get(7));
									} else {
										bd.setCountry("");
									}
									if (((String) list1.get(8)) != null && !((String) list1.get(8)).isEmpty()) {
										bd.setStateId((String) list1.get(8));
									} else {
										bd.setStateId("");
									}
									if (((String) list1.get(9)) != null && !((String) list1.get(9)).isEmpty()) {
										bd.setDistrictId((String) list1.get(9));
									} else {
										bd.setDistrictId("");
									}
									if (((String) list1.get(11)) != null && !((String) list1.get(11)).isEmpty()) {
										bd.setPhoneNumber((String) list1.get(11));
									} else {
										bd.setPhoneNumber("");
									}
									if (((String) list1.get(12)) != null && !((String) list1.get(12)).isEmpty()) {
										bd.setMobileNumber((String) list1.get(12));
									} else {
										bd.setMobileNumber("");
									}
									if (((String) list1.get(13)) != null && !((String) list1.get(13)).isEmpty()) {
										bd.setEmailId((String) list1.get(13));
									} else {
										bd.setEmailId("");
									}

									if (((String) list1.get(17)) != null && !((String) list1.get(17)).isEmpty()) {
										bd.setIdNumber((String) list1.get(17));
										bd.setAadharNumber((String) list1.get(17));
									} else {
										bd.setIdNumber("");
										bd.setAadharNumber("");
									}
									if (((String) list1.get(18)) != null && !((String) list1.get(18)).isEmpty()) {
										bd.setNameAadharCard((String) list1.get(18));
									} else {
										bd.setNameAadharCard("");
									}

									if (((String) list1.get(20)) != null && !((String) list1.get(20)).isEmpty()) {
										String CasteName = revenueBD.getBuyerCasteName((String) list1.get(20));
										bd.setCaste(CasteName);
									} else {
										bd.setCaste("");
									}

									String IdTypeName = "";
									if (((String) list1.get(22)) != null && !((String) list1.get(22)).isEmpty()) {
										IdTypeName = revenueBD.getBuyerIdTypeName((String) list1.get(22));
										bd.setIdType(IdTypeName);
									}

									else {
										bd.setIdType("");
									}

									// Buyer Details
									if (list1.get(0).equals("1")) {

										// For Organization
										if (((String) list1.get(5)) != null && !((String) list1.get(5)).isEmpty()) {
											bd.setFirstName((String) list1.get(5));
										} else {
											bd.setFirstName("");
										}

										bd.setFatherName("");
										bd.setMiddleName("");
										bd.setLastName("");
										bd.setGender("");
										bd.setRelationType("");

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											bd.setOwnershipTypeIdBuyer((String) list1.get(0));
										} else {
											bd.setOwnershipTypeIdBuyer("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											bd.setDeptCode((String) list1.get(4));
										} else {
											bd.setDeptCode("");
										}

										if (((String) list1.get(10)) != null && !((String) list1.get(10)).isEmpty()) {
											bd.setAddress((String) list1.get(10));
										} else {
											bd.setAddress("");
										}

									}
									if (list1.get(0).equals("2")) {

										if (((String) list1.get(1)) != null && !((String) list1.get(1)).isEmpty()) {
											bd.setFirstName((String) list1.get(1));
										} else {
											bd.setFirstName("");
										}

										if (((String) list1.get(2)) != null && !((String) list1.get(2)).isEmpty()) {
											bd.setMiddleName((String) list1.get(2));
										} else {
											bd.setMiddleName("");
										}
										if (((String) list1.get(3)) != null && !((String) list1.get(3)).isEmpty()) {
											bd.setLastName((String) list1.get(3));
										} else {
											bd.setLastName("");
										}
										if (((String) list1.get(21)) != null && !((String) list1.get(21)).isEmpty()) {
											bd.setFatherName((String) list1.get(21));
										} else {
											bd.setFatherName("");
										}
										if (((String) list1.get(14)) != null && !((String) list1.get(14)).isEmpty()) {
											bd.setGender((String) list1.get(14));
										} else {
											bd.setGender("");
										}
										if (((String) list1.get(15)) != null && !((String) list1.get(15)).isEmpty()) {
											String reletionName = revenueBD.getRelationName((String) list1.get(15));
											bd.setRelationType(reletionName);
										} else {
											bd.setRelationType("");
										}

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											bd.setOwnershipTypeIdBuyer((String) list1.get(0));
										} else {
											bd.setOwnershipTypeIdBuyer("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											bd.setDeptCode((String) list1.get(4));
										} else {
											bd.setDeptCode("");
										}

										if (((String) list1.get(10)) != null && !((String) list1.get(10)).isEmpty()) {
											bd.setAddress((String) list1.get(10));
										} else {
											bd.setAddress("");
										}

									}
									if (list1.get(0).equals("3")) {
										if (((String) list1.get(6)) != null && !((String) list1.get(6)).isEmpty()) {
											bd.setFirstName((String) list1.get(6));
										} else {
											bd.setFirstName("");
										}

										bd.setMiddleName("");
										bd.setLastName("");
										bd.setFatherName("");
										bd.setRelationType("");
										if (((String) list1.get(14)) != null && !((String) list1.get(14)).isEmpty()) {
											bd.setGender((String) list1.get(14));
										} else {
											bd.setGender("");
										}

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											bd.setOwnershipTypeIdBuyer((String) list1.get(0));
										} else {
											bd.setOwnershipTypeIdBuyer("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											bd.setDeptCode((String) list1.get(4));
										} else {
											bd.setDeptCode("");
										}
										if (((String) list1.get(23)) != null && !((String) list1.get(23)).isEmpty()) {
											bd.setAddress((String) list1.get(23));
										} else {
											bd.setAddress("");
										}

									}

									bdList.add(bd);
									logger.info("bdListSize:" + bdList.size());

								}

							}
							bdsl.setBuyerDetails(bdList);
							sdsl.setSellerDetails(sdList);
							krr.setBuyerDetailsSampadaList(bdsl);
							krr.setSellerDetailsSampadaList(sdsl);
							kr.add(krr);
						} else {
							// Empty buyer details

							BuyerDetails buyerDetails = new BuyerDetails();
							buyerDetails.setOwnerId("");
							buyerDetails.setOwnershipTypeIdBuyer("");
							buyerDetails.setFirstName("");
							buyerDetails.setMiddleName("");
							buyerDetails.setLastName("");
							buyerDetails.setGender("");
							buyerDetails.setFatherName("");
							buyerDetails.setRelationType("");
							buyerDetails.setCaste("");
							buyerDetails.setDistrictId("");
							buyerDetails.setOwnerShare("");
							buyerDetails.setAadharNumber("");
							buyerDetails.setNameAadharCard("");
							buyerDetails.setIdType("");
							buyerDetails.setIdNumber("");
							buyerDetails.setAddress("");
							buyerDetails.setCity("");
							buyerDetails.setStateId("");
							buyerDetails.setCountry("");
							buyerDetails.setPhoneNumber("");
							buyerDetails.setEmailId("");
							buyerDetails.setMobileNumber("");
							buyerDetails.setDeptCode("");

							bdList.add(buyerDetails);

							bdsl.setBuyerDetails(bdList);
							sdsl.setSellerDetails(sdList);
							krr.setBuyerDetailsSampadaList(bdsl);
							krr.setSellerDetailsSampadaList(sdsl);
							kr.add(krr);
						}

					}
					prds.setTotalKhasraArea(String.valueOf(totalKhasraArea));
					// prds.setTotalSellableArea("9");
					krList.setKhasraRecord(kr);
					prds.setKhasraRecordList(krList);
					propds.add(prds);

				}

				rcmsds.setProperty(propds);

				// Marshalling of object to XML
				JAXBContext jc = JAXBContext.newInstance(RCMSDetailsSampada.class);
				Marshaller ms = jc.createMarshaller();

				ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				String rootFile = pr.getValue("rcms_upload_path");
				String filePath = rootFile + RevenueCaseConstant.IGRS_RCMS_XML + "/";;
				logger.debug("Path :- " + filePath);

				File folder = new File(filePath);
				if (!folder.exists()) {
					folder.mkdirs();
				}

				filePath = filePath + RevenueCaseConstant.XML_REG_NO + registrationNo + "_" + counter + ".xml";
				// String s = "D:/RCMSRequest/SampleReQ_" + regTxnID + "_" +
				// counter + ".xml";
				ms.marshal(rcmsds, new FileOutputStream(filePath));

				StringWriter xmlStringWriter = new StringWriter();
				ms.marshal(rcmsds, xmlStringWriter);

				String xmlString = xmlStringWriter.toString().trim();
				logger.info("xmlString:" + xmlString);
				logger.debug("Before WebService Call");
				System.out.println(xmlString);
				RCMSResponse rcmsResponse = null;
				
				try {
					// Send request to RCMS
					//System.out.println(xmlString);
					rcmsResponse = sendRCMSRequest(xmlString, registrationNo);
				} catch (Exception e) {
					logger.debug("Exception in sendRCMSRequest Method Call" + e);
				}

				if (rcmsResponse != null) {
					logger.debug("Webservice response from RCMS...");
					if (rcmsResponse.getStatus() != null && !(rcmsResponse.getStatus().isEmpty())) {
						logger.debug("Response Status : " + rcmsResponse.getStatus());
						String status = rcmsResponse.getStatus();
						String errorDesc = rcmsResponse.getErrorType();
						//System.out.println(status);
						//System.out.println(errorDesc);
						//System.out.println(rcmsResponse.getApplicationNumber());
						//System.out.println(rcmsResponse.getBuyerFather());
						//System.out.println(rcmsResponse.getCaseNumber());
						// Update reg txn table with status 2 for successful
						// completion
						if (rcmsResponse.getStatus().equalsIgnoreCase("Success")) {
							//boolean transFlag1 = revenueBD.updateRegTrnStatus(regTxnID, "2");
						}

						// Update reg txn table with status 2 for failed
						// transaction
						if (rcmsResponse.getStatus().equalsIgnoreCase("Failed")) {
							//boolean transFlag1 = revenueBD.updateRegTrnStatus(regTxnID, "3");
						}
						// check if record exist

//						boolean flag = revenueBD.updateRCMSStatus(regTxnID, propIdList, rcmsResponse, status);
					}

				}
				// return true;
			} else {
				// return false;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		// return propFlag;

	}
	private static RCMSResponse sendRCMSRequest(String xmlString, String registrationNo) {

		RCMSResponse response = null;

		try {
			
			long startTime = System.currentTimeMillis();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			// System.out.println(pr.getValue("IGRS_RCMS_URL"));
			URL url = new URL("http://rcms.mp.gov.in/wcrcms/Service1.svc/PUSH");
			int timeout = Integer.valueOf(pr.getValue("RCMS_REQ_TIMEOUT"));
			// HttpURLConnection rc ;
			String proxyIp = (String) pr.getValue("IGRS_RCMS_PROXY_IP");
			String proxyPort = (String) pr.getValue("IGRS_RCMS_PROXY_PORT");
			HttpURLConnection rc = (HttpURLConnection) url.openConnection();

			Proxy proxy;
			/*
			 * Properties systemSettings = System.getProperties();
			 * 
			 * systemSettings.put("proxySet", "true");
			 * systemSettings.put("http.proxyHost", proxyIp);
			 * systemSettings.put("http.proxyPort", proxyPort);
			 * System.out.println("Proxy IP----" +
			 * systemSettings.getProperty("http.proxyHost") +
			 * "  Proxy Port -----" +
			 * systemSettings.getProperty("http.proxyPort"));
			 * 
			 * // System.out.println("Connection opened " + rc ); proxy = new
			 * Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(proxyIp,
			 * Integer.parseInt(proxyPort))); rc = (HttpURLConnection)
			 * url.openConnection(); System.out.println("If going proxy--"+
			 * rc.usingProxy()); // if(rc.get) rc.usingProxy();
			 */
			// rc.usingProxy();
			rc.setRequestMethod("POST");
			rc.setDoOutput(true);
			rc.setDoInput(true);
			rc.setConnectTimeout(timeout * 1000);
			rc.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			String reqStr = xmlString; // the entire payload in a single
			// String
			int len = reqStr.length();
			// rc.setRequestProperty("Content-Length", Integer.toString(len));
			rc.connect();
			OutputStreamWriter out = new OutputStreamWriter(rc.getOutputStream());
			out.write(reqStr, 0, len);
			out.flush();
			// System.out.println("Request sent, reading response ");
			InputStreamReader read = new InputStreamReader(rc.getInputStream());
			StringBuilder sb = new StringBuilder();
			int ch = read.read();
			while (ch != -1) {
				sb.append((char) ch);
				ch = read.read();
			}
			String response1 = sb.toString(); // entire response ends up in
			// String
			read.close();
			rc.disconnect();
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			long elapsedTimeInSec = 0;
			if (elapsedTime < 1000) {
				elapsedTimeInSec = 0;
			} else {
				elapsedTimeInSec = elapsedTime / 1000;
			}
			logger.info("**********Total Time taken in process of " + registrationNo + " is " + elapsedTime + " MilliSeconds ************ " + elapsedTimeInSec + " Seconds");
			// System.out.println(response1);

			JAXBContext jaxbContext = JAXBContext.newInstance(RCMSResponse.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(response1);
			response = (RCMSResponse) jaxbUnmarshaller.unmarshal(reader);

			logger.info(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
		}
		return response;

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

			logger.info("BurningImageAndText cropImage method, incoming file type: " + inputFileType);
			imageObj = NIPLJ.decodeToImage(inputFilePath, decode1);

		}

		catch (Exception e) {
			logger.error("BurningImageAndText cropImage method, Exception caught.", e);
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
			logger.error("BurningImageAndText cropImage method, Exception caught ", e);
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
