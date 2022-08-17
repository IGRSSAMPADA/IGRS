package com.wipro.igrs.deedDraft.bd;

import in.cdac.ilcg.jasperreports.pdfexporter.JRPdfExporter;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.log4j.Logger;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.poi.util.IOUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.deedDraft.dao.DeedDraftDAO;
import com.wipro.igrs.deedDraft.dto.DeedDraftAppDTO;
import com.wipro.igrs.deedDraft.dto.DeedDraftDTO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.util.JrxmlExportUtility;

public class DeedDraftBD {
	DeedDraftDAO deedDAO = new DeedDraftDAO();
	private Logger logger = Logger.getLogger(DeedDraftBD.class);

	//saurav
	public ArrayList checkValidRegID(String appID) throws Exception {
		return deedDAO.checkValidRegID(appID);
	}

	public ArrayList getPropdetails(String appId) throws Exception {
		return deedDAO.getPropdetails(appId);
	}

	public ArrayList getDutyDetails(String appId) throws Exception {
		return deedDAO.getDutyDetails(appId);
	}

	public ArrayList getConsenterDetails(String regTxnId, String userId) throws Exception {
		return deedDAO.getConsenterDetails(regTxnId, userId);
	}

	public ArrayList getAppDetails(String appId) throws Exception {
		return deedDAO.getAppDetails(appId);
	}

	public ArrayList getbuildingView(String valId, String language) throws Exception {
		ArrayList build = deedDAO.getbuildingView(valId, language);
		return build;
	}
	//added for construction slab changes by saurav
	public String getConstructionSlabDesc(String id, String language) throws Exception {
		return deedDAO.getConstructionSlabDesc(id, language);
	}
	public ArrayList getconstSlabDetail(String id, String language) throws Exception{
		return deedDAO.getconstSlabDetail(id,language);
	}
	//construction slab changes end
	public ArrayList getBuildingDetailsAgri(String agriTxnId, String language) throws Exception {
		return deedDAO.getBuildingDetailsAgri(agriTxnId, language);
	}

	public ArrayList getKhasraDetails(String propertyId) throws Exception {
		return deedDAO.getKhasraDetails(propertyId);
	}

	public ArrayList getPropDutyDetails(String dutyTxnId, String regTxnId, String propertId) throws Exception {
		return deedDAO.getPropDutyDetails(dutyTxnId, regTxnId, propertId);
	}

	public ArrayList getUserEnterableValue(String dutyTxnId, String propDutyId, String language) throws Exception {
		return deedDAO.getUserEnterableValue(dutyTxnId, propDutyId, language);
	}

	public ArrayList getSubClauseList(String id, String language) throws Exception {
		return deedDAO.getSubClauseList(id, language);
	}

	public String getPropRelated(String deedId, String instId) throws Exception {
		return deedDAO.getPropRelated(deedId, instId);
	}

	public String getPropOptionalCheck(String deedId, String instId) throws Exception {
		return deedDAO.getPropOptionalCheck(deedId, instId);
	}

	public String getPropValCheck(String deedId, String instId) throws Exception {
		return deedDAO.getPropValCheck(deedId, instId);
	}

	public ArrayList getExemption(String appId, String type) throws Exception {
		return deedDAO.getExemption(appId, type);
	}

	public ArrayList getValuationDetails(String params, String language) throws Exception {
		return deedDAO.getValuationDetails(params, language);
	}

	public ArrayList getFloorList(String buildingTxnId, String language) throws Exception {
		return deedDAO.getFloorList(buildingTxnId, language);
	}

	public ArrayList getAgriDetails(String valId, String language) throws Exception {
		ArrayList retList = new ArrayList();
		ArrayList subClauseList = new ArrayList();
		DeedDraftAppDTO ddto = null;
		subClauseList = deedDAO.getAgriDetails(valId, language);
		for (int i = 0; i < subClauseList.size(); i++) {
			ArrayList subList = (ArrayList) subClauseList.get(i);
			ddto = new DeedDraftAppDTO();
			ddto.setDistrictName((String) subList.get(0));
			ddto.setTehsilName((String) subList.get(1));
			ddto.setAreaTypeName((String) subList.get(2));
			ddto.setSubAreaTypeName((String) subList.get(3));
			ddto.setWardName((String) subList.get(4));
			ddto.setColonyName((String) subList.get(5));
			ddto.setCommonAgriSingleBuyer((String) subList.get(6));
			ddto.setCommonAgriSameFamily(subList.get(7) == null ? "-" : subList.get(7).toString());
			ddto.setCommonAgriBuyerCount(subList.get(8) == null ? "-" : subList.get(8).toString());
			ddto.setCommonAgriTreePresent(subList.get(9) == null ? "-" : subList.get(9).toString());
			ddto.setCommonAgriDiscloseShare(subList.get(10) == null ? "-" : subList.get(10).toString());
			ddto.setAreaId((String) subList.get(11));
			retList.add(ddto);
		}
		return subClauseList;
	}

	public ArrayList getAgriPropDetails(String valId, String language) throws Exception {
		ArrayList retList = new ArrayList();
		DeedDraftAppDTO ddto = null;
		ArrayList agriPropDetailsList = new ArrayList();
		try {
			agriPropDetailsList = deedDAO.getagriPropDetailsList(valId, language);
			for (int i = 0; i < agriPropDetailsList.size(); i++) {
				ArrayList subList = (ArrayList) agriPropDetailsList.get(i);
				ddto = new DeedDraftAppDTO();
				ddto.setCommonAgriTxnid(subList.get(0) == null ? "-" : subList.get(0).toString());
				ddto.setCommonAgriSubTypeId(subList.get(1) == null ? "-" : subList.get(1).toString());
				ddto.setCommonAgriPropSubTypeName(subList.get(2) == null ? "-" : subList.get(2).toString());
				ddto.setCommonTotalUndivArea(subList.get(3) == null ? "-" : subList.get(3).toString());
				ddto.setCommonTotalUnirriOneCrop(subList.get(4) == null ? "-" : subList.get(4).toString());
				ddto.setCommonTotalUnirriTwoCrop(subList.get(5) == null ? "-" : subList.get(5).toString());
				ddto.setCommonTotalIrrigatedArea(subList.get(6) == null ? "-" : subList.get(6).toString());
				ddto.setCommonAgriConstruction(subList.get(7) == null ? "-" : subList.get(7).toString());
				ddto.setCommonTotalDivArea(subList.get(8) == null ? "-" : subList.get(8).toString());
				ddto.setCommonTotalResiArea(subList.get(9) == null ? "-" : subList.get(9).toString());
				ddto.setCommonTotalCommArea(subList.get(10) == null ? "-" : subList.get(10).toString());
				ddto.setCommonTotalIndArea(subList.get(11) == null ? "-" : subList.get(11).toString());
				ddto.setCommonTotalEduArea(subList.get(12) == null ? "-" : subList.get(12).toString());
				ddto.setCommonTotalHealthArea(subList.get(13) == null ? "-" : subList.get(13).toString());
				ddto.setCommonTotalOtherArea(subList.get(14) == null ? "-" : subList.get(14).toString());
				ddto.setCommonAgriEducationTcp(subList.get(15) == null ? "-" : subList.get(15).toString());
				ddto.setCommonAgriHealthTcp(subList.get(16) == null ? "-" : subList.get(16).toString());
				retList.add(ddto);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return agriPropDetailsList;
	}

	public ArrayList getConstructionDetails(String agriTxnId) throws Exception {
		return deedDAO.getConstructionDetails(agriTxnId);
	}

	public ArrayList getAgriTreeDetails(String valId, String language) throws Exception {
		ArrayList agriTreeDetailsList = new ArrayList();
		ArrayList retList = new ArrayList();
		DeedDraftAppDTO ddto = null;
		try {
			agriTreeDetailsList = deedDAO.getAgriTreeDetailsList(valId, language);
			for (int i = 0; i < agriTreeDetailsList.size(); i++) {
				ArrayList subList = (ArrayList) agriTreeDetailsList.get(i);
				ddto = new DeedDraftAppDTO();
				ddto.setCommonAgriTreeCount((String) subList.get(1));
				if (language.equalsIgnoreCase("en")) {
					ddto.setCommonAgriTreeName((String) subList.get(0));
				} else {
					ddto.setCommonAgriTreeName((String) subList.get(0));
				}
				retList.add(ddto);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return agriTreeDetailsList;
	}

	public ArrayList getClauseList(String language, String valId) throws Exception {
		return deedDAO.getClauseList(language, valId);
	}

	public ArrayList getClauseListAgriConst(String language, String agriTxnId) throws Exception {
		return deedDAO.getClauseListAgriConst(language, agriTxnId);
	}

	public boolean getDeedVerified(String appId, String appType, String deedId) throws Exception {
		return deedDAO.getDeedVerified(appId, appType, deedId);
	}

	public String getAdjudicationFlag(String applicationId) throws Exception {
		return deedDAO.getAdjudicationFlag(applicationId);
	}

	public boolean saveDeedDraftDetails(DeedDraftDTO ddto, String userID, String appType, String applicationId) throws Exception {
		return deedDAO.saveDeedDraftDetails(ddto, userID, appType, applicationId);
	}

	public String getPropType(String id) throws Exception {
		return deedDAO.getPropType(id);
	}

	public String getDeedContent(String deedDraftId, String pageId) throws Exception {
		return deedDAO.getDeedContent(deedDraftId, pageId);
	}

	public boolean checkDeedRecordOnNext(String deedDraftId, String pageId) throws Exception {
		return deedDAO.checkDeedRecordOnNext(deedDraftId, pageId);
	}

	public String checkID(String deedDraftId, String userID) throws Exception {
		String arr = new String();
		boolean flag = false;
		ArrayList deedIDs = deedDAO.checkID(userID);
		try {
			for (int i = 0; i < deedIDs.size(); i++) {
				ArrayList subList = (ArrayList) deedIDs.get(i);
				String did = subList.get(0).toString();
				String deedStatus = subList.get(1).toString();
				if (deedStatus.equalsIgnoreCase("D") && did.equalsIgnoreCase(deedDraftId.trim())) {
					arr = "consumed";
					return arr;
				} else if (deedStatus.equalsIgnoreCase("I") && did.equalsIgnoreCase(deedDraftId.trim())) {
					arr = "valid";
					return arr;
				} else if (deedStatus.equalsIgnoreCase("A") && did.equalsIgnoreCase(deedDraftId.trim())) {
					arr = "valid";
					return arr;
				} else {
					arr = "invalid";
				}
			}
		} catch (Exception e) {
			logger.debug("exceptionnn");
			System.out.println("exceptionnn");
			e.printStackTrace();
		}
		return arr;
	}

	public boolean updateDeedDraftStatus(String deedDraftId) {
		return deedDAO.updateDeedDraftStatus(deedDraftId);
	}

	public byte[] generateDeedPDF(String path, String deedDraftId, HttpServletResponse response, int consumeFlag, byte[] propDtlsbytes) throws Exception {
		HashMap jasperMap = new HashMap();
		//jasperMap.put("deedID", deedDraftId);
		try {
			InputStream myInputStream2 = null; //added by ankit for prop val changes
			String pdfName = "DeedDraft";
			StringBuffer deedBufferFirst = deedDAO.getDeedContentPDFFirst(deedDraftId);
			StringBuffer deedBuffer = deedDAO.getDeedContentPDF(deedDraftId);
			/*jasperMap.put("deedContent", deedBuffer);
			jasperMap.put("deedID", deedDraftId);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DBUtility dbUtil = new DBUtility("");
			logger.debug("connecting--->");
			System.out.println("connecting--->");
			JasperReport jasperReport=JasperCompileManager.compileReport(path.concat("deedDraftPDF.jrxml"));
			System.out.println("before conn");
			Connection conn = dbUtil.getDBConnection();
			System.out.println("after getting conn");
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			//JasperViewer.viewReport(jasperPrint);
			String pdfName="DeedDraft";
			
			dbUtil.closeConnection();
			logger.debug("connection closed--->");
			System.out.println("connection closed--->");
			 ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
				logger.debug("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
				exporter.exportReport();
				
				logger.debug("starting--->");
				System.out.println("starting--->");
				
				ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
				*/
			/*String path1=pr.getValue("deed_draft_upload_location");
			File f = new File(path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
			
			logger.debug("starting writing----->"+path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
			System.out.println("starting writing----->"+path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
			
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(os.toByteArray());
			fo.close();
			
			logger.debug("file writing Done--->");
			System.out.println("file writing Done--->");
			
			PDFMergerUtility ut = new PDFMergerUtility();
			ut.addSource(f);
			ut.addSource(new File(path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_NAME_SIGN_DOC));
			
			ut.setDestinationFileName(path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_PATH_MERGED_DOC);
			ut.mergeDocuments();
			logger.debug("merging Done--->");
			System.out.println("merging done---->");
			
			FileInputStream fis = new FileInputStream(new File(path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_PATH_MERGED_DOC));
			byte[] abc = IOUtils.toByteArray(fis);
			
			fis.close();*/
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document();
			//PdfWriter.getInstance(document, baos); 			   
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.open();
			document.newPage();
			PdfContentByte cb = writer.getDirectContent();
			System.out.println(document.getPageSize());
			PdfTemplate pdt = cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
			Graphics2D g2 = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
			// g2.drawString(,10,45);
			drawStringMultiLine(g2, deedBufferFirst.toString(), (int) PageSize.A4.getWidth() - (94 + 43), 94, 144, document, cb, pdt, writer);//x->left margin(94),y->top margin
			logger.debug("FIIRT PAGE DATA CREATED");
			g2.dispose();
			logger.debug("FIIRT PAGE DISPOSE");
			cb.addTemplate(pdt, 5, -7);
			logger.debug("FIIRT PAGE ADDED");
			if (deedBuffer.length() > 0) {
				cb = writer.getDirectContent();
				pdt = cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				logger.debug("PdfContentByte INIT");
				g2 = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				logger.debug("SECOND ONWARDS GRAPHIC INIT CREATED");
				document.newPage();
				logger.debug("NEW PAGE CREATED");
				drawStringMultiLine(g2, deedBuffer.toString(), (int) PageSize.A4.getWidth() - (94 + 43), 94, 144, document, cb, pdt, writer);//x->left margin(94),y->top margin
				logger.debug("sECOND ONWARds PAGE DATA CREATED");
				// g2.dispose();
				document.add(new Paragraph(" "));
				logger.debug("Paragraph added");
			}
			document.close();
			/*response.setContentType("Report/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"Report.pdf");
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();*/
			//added by ankit for prop valuation changes - start
			// byte[] one = baos.toByteArray();
			//byte[] two = propDtlsbytes;
			//byte[] combined = new byte[one.length + two.length];
			InputStream myInputStream = new ByteArrayInputStream(baos.toByteArray());
			if (propDtlsbytes != null) {
				myInputStream2 = new ByteArrayInputStream(propDtlsbytes);
			}
			//added by ankit for prop valuation changes - end
			if (consumeFlag != 1) {
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\\" + pdfName + ".pdf");
				//response.setContentLength(baos.size()*4);
				ServletOutputStream out = response.getOutputStream();
				//out.write(abc);
				//added by ankit for prop detls pdf
				/*String propPdfPath  = uploadPath +RegInitConstant.FILE_UPLOAD_PATH
				+ regInitNo + RegInitConstant.FILE_UPLOAD_PATH_PROP_DETLS + "/"+RegInitConstant.PROP_DETLS_DOC;
				
				File fileObject = new File(propPdfPath);

				if (fileObject.isFile()) {
				
				 FileInputStream fis = new FileInputStream(fileObject);
				 ByteArrayOutputStream bos = new ByteArrayOutputStream();
				 
				 ByteArrayOutputStream output = new ByteArrayOutputStream();
				 PdfContentByte cb1 = writer.getDirectContent();	
				 
				// baos.writeTo(bos);
				 byte[] buffer = new byte[1024*8];
				 int bytesRead;
				      while ((bytesRead = fis.read(buffer)) != -1) {
				      	bos.write(buffer, 0, bytesRead);
				      }
				    response.setContentLength(baos.size() + bos.size());
				    output.writeTo(cb1.getInternalBuffer());
				    output.writeTo(bos);
				    output.writeTo(out);
				    uyguii7f
				    fis.close();
				    bos.close();
				    output.close();
				}
				
				
				savePropDetlsToPdf(regForm, request, response,
					languageLocale);*/
				//ByteArrayOutputStream bos = new ByteArrayOutputStream(propDtlsbytes.length);
				//bos.write(propDtlsbytes,0, propDtlsbytes.length	  		 
				//ByteArrayOutputStream output2 = new ByteArrayOutputStream();
				//baos.writeTo(output1);
				//output1.write(baos.toByteArray());
				//output1.write(propDtlsbytes);
				PDFMergerUtility mergePdf = new PDFMergerUtility();
				mergePdf.addSource(myInputStream);
				if (propDtlsbytes != null) {
					mergePdf.addSource(myInputStream2);
				}
				mergePdf.setDestinationStream(out);
				mergePdf.mergeDocuments();
				//System.arraycopy(one,0,combined,0         ,one.length);
				//	System.arraycopy(two,0,combined,one.length,two.length);
				//response.setContentLength(combined.length);
				//out.write(combined);
				//baos.writeTo(out);
				myInputStream.close();
				if (myInputStream2 != null) {
					myInputStream2.close();
				}
				out.flush();
				out.close();
			}
			/*			//added by ankit for prop detls pdf
				  		String propPdfPath  = uploadPath +RegInitConstant.FILE_UPLOAD_PATH
						+ regInitNo + RegInitConstant.FILE_UPLOAD_PATH_PROP_DETLS + "/"+RegInitConstant.PROP_DETLS_DOC;
				  		
				  		File fileObject = new File(propPdfPath);

						if (fileObject.isFile()) {
							
							logger.info("propPdfPath 123 : "+propPdfPath);
							logger.info(" lenght of file : "+fileObject.length());
							
							byte[] temp = baos.toByteArray();
							FileInputStream fis1 = new FileInputStream(temp);
							//baos.write(temp);
							
							 FileInputStream fis = new FileInputStream(fileObject);
							byte[] abc = IOUtils.toByteArray(fis);
							
							byte[] buffer = new byte[8192];
							ByteArrayOutputStream baosPdf = new ByteArrayOutputStream();

						   int bytesRead;
						    while ((bytesRead = fis.read(buffer)) != -1)
						    {
						    	baos.write(buffer, 0, bytesRead);
						    }
							
							ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
							outputStream.write( temp );
							outputStream.write( abc );

							byte c[] = outputStream.toByteArray( );
							
							//baos.write(abc);
							return c;
							
							
							PDFMergerUtility mergePdf = new PDFMergerUtility();
							
							ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
							mergePdf.addSource(new ByteArrayInputStream(temp));
							mergePdf.addSource(fis);
							mergePdf.setDestinationStream(byteArrayOutputStream);
							mergePdf.mergeDocuments();
							c = byteArrayOutputStream.toByteArray();
							
							fis.close();

						}	
			  		*/
			//added by ankit for prop valuation changes - start
			PDFMergerUtility mergePdf = new PDFMergerUtility();
			mergePdf.addSource(myInputStream);
			if (propDtlsbytes != null) {
				mergePdf.addSource(myInputStream2);
			}
			mergePdf.setDestinationStream(baos);
			mergePdf.mergeDocuments();
			myInputStream.close();
			if (myInputStream2 != null) {
				myInputStream2.close();
			}
			baos.close();
			//added by ankit for prop valuation changes - end
			return baos.toByteArray();
		} catch (Exception e) {
			System.out.println("exception");
			logger.debug("exception");
			logger.debug(e.getMessage(), e);
			System.out.println(e);
			//return null;
			throw e;
		}
	}

	public byte[] mergeDeedPDF(String path, String deedDraftId, HttpServletResponse response, int consumeFlag) throws Exception {
		HashMap jasperMap = new HashMap();
		jasperMap.put("deedID", deedDraftId);
		try {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DBUtility dbUtil = new DBUtility("");
			logger.debug("connecting--->");
			System.out.println("connecting--->");
			JasperReport jasperReport = JasperCompileManager.compileReport(path.concat("deedDraftPDF.jrxml"));
			System.out.println("before conn");
			Connection conn = dbUtil.getDBConnection();
			System.out.println("after getting conn");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			//JasperViewer.viewReport(jasperPrint);
			String pdfName = "DeedDraft";
			dbUtil.closeConnection();
			logger.debug("connection closed--->");
			System.out.println("connection closed--->");
			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			logger.debug("OPENOFFICE_PATH = " + System.getenv("OPENOFFICE_PATH"));
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
			exporter.exportReport();
			logger.debug("starting--->");
			System.out.println("starting--->");
			ByteArrayOutputStream os = (ByteArrayOutputStream) exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
			String path1 = pr.getValue("deed_draft_upload_location");
			File f = new File(path1 + deedDraftId + "/" + DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
			logger.debug("starting writing----->" + path1 + deedDraftId + "/" + DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
			System.out.println("starting writing----->" + path1 + deedDraftId + "/" + DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(os.toByteArray());
			fo.close();
			logger.debug("file writing Done--->");
			System.out.println("file writing Done--->");
			PDFMergerUtility ut = new PDFMergerUtility();
			ut.addSource(f);
			ut.addSource(new File(path1 + deedDraftId + "/" + DeedDraftConstant.UPLOAD_NAME_SIGN_DOC));
			ut.setDestinationFileName(path1 + deedDraftId + "/" + DeedDraftConstant.UPLOAD_PATH_MERGED_DOC);
			ut.mergeDocuments();
			logger.debug("merging Done--->");
			System.out.println("merging done---->");
			FileInputStream fis = new FileInputStream(new File(path1 + deedDraftId + "/" + DeedDraftConstant.UPLOAD_PATH_MERGED_DOC));
			byte[] abc = IOUtils.toByteArray(fis);
			fis.close();
			if (consumeFlag != 1) {
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\\" + pdfName + ".pdf");
				//response.setContentLength(pdfReport.size());
				ServletOutputStream out = response.getOutputStream();
				out.write(abc);
				//os.writeTo(out);
				out.flush();
				out.close();
			}
			return os.toByteArray();
		} catch (Exception e) {
			System.out.println("exception");
			logger.debug("exception");
			e.printStackTrace();
			System.out.println(e);
			//return null;
			throw e;
		}
	}

	public boolean saveDeedDraftPath(DeedDraftDTO ddto, String userID) throws Exception {
		return deedDAO.saveDeedDraftPath(ddto);
	}

	public void drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y, Document document, PdfContentByte cb, PdfTemplate pdt, PdfWriter writer) {
		FontMetrics m = g.getFontMetrics();
		System.out.println(m.getFont());
		try {
			/* if(m.stringWidth(text) < lineWidth) {
			     g.drawString(text, x, y);
			 } else {*/
			String splitf[] = text.split("\n");
			for (int k = 0; k < splitf.length; k++) {
				String[] words = splitf[k].split(" ");
				if (words[0].trim().equals("")) {
					y += m.getHeight();
				}
				if (words.length > 0) {
					String currentLine = words[0];
					for (int i = 1; i < words.length; i++) {
						if (m.stringWidth(currentLine + words[i]) < lineWidth) {
							currentLine += " " + words[i];
						} else {
							String split[] = currentLine.split("\n");
							for (int j = 0; j < split.length; j++) {
								g.drawString(split[j], x, y);
								y += m.getHeight();
							}
							// g.drawString(currentLine, x, y);
							// y += m.getHeight();
							currentLine = words[i];
						}
						System.out.println("Y IS----->" + y);
						System.out.println("X IS----->" + x);
						if (y > (int) PageSize.A4.getHeight() - (148)) {//148 is the bottom margin
							logger.debug("X IS " + x + "Y IS " + y);
							g.dispose();
							cb.addTemplate(pdt, 5, -7);
							cb = writer.getDirectContent();
							pdt = cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
							logger.debug("PdfContentByte INIT");
							g = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
							logger.debug("SECOND ONWARDS GRAPHIC INIT CREATED");
							document.newPage();
							y = 144;//TOP MARGIN
						}
					}
					if (currentLine.trim().length() > 0) {
						g.drawString(currentLine, x, y);
						y += m.getHeight();//added
					}
					if (currentLine.trim().length() > lineWidth) {
						y += m.getHeight();//next line
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			g.dispose();
			logger.debug("sECOND ONWARds DISPOSE finally");
			cb.addTemplate(pdt, 5, -7);
			logger.debug("sECOND ONWARds temp finally");
		}
		// }
	}

	//added by ankit for prop val changes
	public byte[] propDetlsPdf(String regTxnId) throws Exception {
		byte[] outputBytes = null;
		try {
			PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
			String uploadPath = prop.getValue("upload.location");
			String propPdfPath = uploadPath + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_PROP_DETLS + "/" + RegInitConstant.PROP_DETLS_DOC;
			File fileObjectTemp = new File(propPdfPath);
			if (fileObjectTemp.isFile()) {
				FileInputStream fis = new FileInputStream(fileObjectTemp);
				outputBytes = IOUtils.toByteArray(fis);
				fis.close();
				return outputBytes;
			}
		} catch (Exception e) {
			logger.debug("propDetlsPdf method : " + e);
			logger.debug(e.getMessage(), e);
		}
		return outputBytes;
	}

	//added by ankit for prop val changes
	public byte[] generateDeedPDFOLD(String path, String deedDraftId, HttpServletResponse response, int consumeFlag) throws Exception {
		HashMap jasperMap = new HashMap();
		//jasperMap.put("deedID", deedDraftId);
		try {
			String pdfName = "DeedDraft";
			StringBuffer deedBufferFirst = deedDAO.getDeedContentPDFFirst(deedDraftId);
			StringBuffer deedBuffer = deedDAO.getDeedContentPDF(deedDraftId);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document();
			//PdfWriter.getInstance(document, baos); 		
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.open();
			document.newPage();
			PdfContentByte cb = writer.getDirectContent();
			System.out.println(document.getPageSize());
			PdfTemplate pdt = cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
			Graphics2D g2 = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
			// g2.drawString(,10,45);
			drawStringMultiLine(g2, deedBufferFirst.toString(), (int) PageSize.A4.getWidth() - (94 + 43), 94, 144, document, cb, pdt, writer);//x->left margin(94),y->top margin
			logger.debug("FIIRT PAGE DATA CREATED");
			g2.dispose();
			logger.debug("FIIRT PAGE DISPOSE");
			cb.addTemplate(pdt, 5, -7);
			logger.debug("FIIRT PAGE ADDED");
			if (deedBuffer.length() > 0) {
				cb = writer.getDirectContent();
				pdt = cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				logger.debug("PdfContentByte INIT");
				g2 = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				logger.debug("SECOND ONWARDS GRAPHIC INIT CREATED");
				document.newPage();
				logger.debug("NEW PAGE CREATED");
				drawStringMultiLine(g2, deedBuffer.toString(), (int) PageSize.A4.getWidth() - (94 + 43), 94, 144, document, cb, pdt, writer);//x->left margin(94),y->top margin
				logger.debug("sECOND ONWARds PAGE DATA CREATED");
				// g2.dispose();
				document.add(new Paragraph(" "));
				logger.debug("Paragraph added");
			}
			document.close();
			if (consumeFlag != 1) {
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\\" + pdfName + ".pdf");
				response.setContentLength(baos.size());
				ServletOutputStream out = response.getOutputStream();
				//out.write(abc);
				baos.writeTo(out);
				out.flush();
				out.close();
			}
			return baos.toByteArray();
		} catch (Exception e) {
			System.out.println("exception");
			logger.debug("exception");
			logger.debug(e.getMessage(), e);
			System.out.println(e);
			//return null;
			throw e;
		}
	}

	/*	
		
		//added by ankit for prop val : deed create new logic and method
		public byte[] newDeedPDF(RegCommonForm regForm, String path, String deedDraftId, HttpServletResponse response, int consumeFlag, String language) throws Exception {
			String regTxnId = regForm.getHidnRegTxnId();
			String deed = regForm.getDeedtype1();
			String instrument = regForm.getInstType();
			String exempStamp = regForm.getExempStamp();
			String exempReg = regForm.getExempReg();
			String totalDuty = regForm.getTotalduty();
			String totalReg = regForm.getRegistrationFee();
			String dutyTxnId = Integer.toString(regForm.getDuty_txn_id());
			logger.info("ExempStamp : " + regForm.getExempStamp());
			logger.info("ExempReg : " + regForm.getExempReg());
			logger.info("Totalduty : " + regForm.getTotalduty());
			logger.info("RegistrationFee : " + regForm.getRegistrationFee());
			PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
			String reportPath = prop.getValue("pdf.location");
			String uploadPath = prop.getValue("upload.location");
			HashMap jasperMap = new HashMap();
			jasperMap.put("RegAdjuNo", regTxnId);
			jasperMap.put("reg_txn_id", regTxnId);
			jasperMap.put("exempStamp", exempStamp);
			jasperMap.put("exempReg", exempReg);
			jasperMap.put("totalDuty", totalDuty);
			jasperMap.put("totalReg", totalReg);
			jasperMap.put("duty_txn_id", dutyTxnId);
			jasperMap.put("path", reportPath);
			logger.debug("reportPath PATH IS----------------->" + reportPath);
			logger.debug("DEED PDF jasperMap----------------->" + jasperMap.toString());
			DBUtility dbUtil = new DBUtility("");
			StringBuffer deedBufferFirst = deedDAO.getDeedContentPDFFirst(deedDraftId);
			StringBuffer deedBuffer = deedDAO.getDeedContentPDF(deedDraftId);
			jasperMap.put("deedID", deedDraftId);
			jasperMap.put("deedContent", deedBuffer);
			JasperReport jasperReport;
			try {
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				logger.debug("connecting--->");
				System.out.println("connecting--->");
				if (RegInitConstant.LANGUAGE_ENGLISH.equalsIgnoreCase(language)) {
					reportPath+="deedDraftPDF.jrxml";
					jasperReport = JasperCompileManager.compileReport(path.concat("deedDraftPDF.jrxml"));
				} else {
					reportPath+="deedDraftPDFH.jrxml";
					jasperReport = JasperCompileManager.compileReport(path.concat("deedDraftPDFH.jrxml"));
					JasperCompileManager.compileReport(path.concat("RegInitPlotPropValH.jrxml"));
					JasperCompileManager.compileReport(path.concat("RegInitfloorDetailsPropValH.jrxml"));
					JasperCompileManager.compileReport(path.concat("RegInitBuildingPropValH.jrxml"));
					JasperCompileManager.compileReport(path.concat("RegInitAgriPropValH.jrxml"));
					JasperCompileManager.compileReport(path.concat("DutyCalculationH.jrxml"));
					JasperCompileManager.compileReport(path.concat("PropertyValDetailSubReportPropValH.jrxml"));
				}
				System.out.println("before conn");
				Connection conn = dbUtil.getDBConnection();
				System.out.println("after getting conn");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
				//JasperViewer.viewReport(jasperPrint);
				String pdfName = "DeedDraft";
				logger.debug("connection closed--->");
				System.out.println("connection closed--->");
				//ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
				logger.debug("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
				//	List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
				//	jasperPrints.add(jasperPrint);
				JRPdfExporter exporter = new JRPdfExporter();
				// ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
				logger.debug("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
					
					//exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);	
				exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
				exporter.exportReport();
				byte[] outputBytes =pdfReport.toByteArray();
				logger.debug("starting--->");
				System.out.println("starting--->");
				//ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
				//byte[] outputBytes =os.toByteArray();
				
				//JRPdfExporter exporter = new JRPdfExporter();
				//File pdf = File.createTempFile("output.", ".pdf");
				//JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));
				

				
				ByteArrayOutputStream  pdfDocReport = new ByteArrayOutputStream();
		 	 	JRDocxExporter exporter = new JRDocxExporter();
				logger.debug("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				//exporter.setParameter(JRDocxExporterParameter.FONT_MAP, Boolean.TRUE);
				//we set the one page per sheet parameter here
				//exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfDocReport);
				exporter.exportReport();
				logger.debug("Done--->EXPORTED:)");
				
				//ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
				//byte[] bytes = os.toByteArray();
				
				//InputStream inputStream = new ByteArrayInputStream(bytes);
				//XWPFDocument document = new XWPFDocument(inputStream);
				
	          //  PdfOptions options = PdfOptions.create();
	           
	            //ByteArrayOutputStream pdfout = new ByteArrayOutputStream();
	           // PdfConverter.getInstance().convert(document, pdfout, options);
	           
	           // OutputStream pdfout = new FileOutputStream(new File("F:/PROP_VAL_PDF_CHANGES/UAT_PDF_DEPLOY/PDFUpdated16Nov/PropertyValDetailSubReportPropVal.pdf"));
	           // PdfConverter.getInstance().convert(document, pdfout, options);
	            
	           
				//byte[] outputBytes = JasperExportManager.exportReportToPdf(jasperPrint);
				
				//CommonAction action = new CommonAction();
				//String path1 = pr.getValue("deed_draft_upload_location");
				//String filePath = action.uploadFile(deedDraftId + "/", outputBytes, null, null, DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
								String path1=pr.getValue("deed_draft_upload_location");
				File f = new File(path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
				
				logger.debug("starting writing----->"+path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
				System.out.println("starting writing----->"+path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_NAME_TEMP_DOC);
				
				FileOutputStream fo = new FileOutputStream(f);
				//	fo.write(pdfReport.toByteArray());
				fo.write(outputBytes);
				
				fo.close();
				
				logger.debug("file writing Done--->");
				System.out.println("file writing Done--->");
				
				PDFMergerUtility ut = new PDFMergerUtility();
				ut.addSource(f);
				ut.addSource(new File(path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_NAME_SIGN_DOC));
				
				ut.setDestinationFileName(path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_PATH_MERGED_DOC);
				ut.mergeDocuments();
				logger.debug("merging Done--->");
				System.out.println("merging done---->");
				
				FileInputStream fis = new FileInputStream(new File(path1+deedDraftId+"/"+DeedDraftConstant.UPLOAD_PATH_MERGED_DOC));
				byte[] abc = IOUtils.toByteArray(fis);
				
				fis.close();
				if (consumeFlag != 1) {
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "attachment; filename=\\" + pdfName + ".pdf");
					//response.setContentLength(pdfDocReport.size());
					ServletOutputStream out = response.getOutputStream();
					//os.writeTo(out);
					out.write(outputBytes);
					out.flush();
					out.close();
					
					
					//JrxmlExportUtility export = new JrxmlExportUtility();
					//export.generateDeedDOCX(jasperMap, reportPath, regTxnId,response);
					//export.generatePDF(jasperMap, reportPath, regTxnId,response);
					//export.generatePDFImage(jasperMap, reportPath, "sample",response);
					//export.generateHTML(jasperMap, reportPath, "sampledeed",response);
				}
				
				JrxmlExportUtility export = new JrxmlExportUtility();
				byte[] outputBytes = export.getJasperDeedPdfviewerFromImg(jasperMap, reportPath, regTxnId,response, path, "P",consumeFlag);
				return outputBytes;
				//return os.toByteArray();
			} catch (Exception e) {
				System.out.println("exception");
				logger.debug("exception");
				e.printStackTrace();
				System.out.println(e);
				//return null;
				throw e;
			} finally {
				if (!dbUtil.isClosed()) {
					dbUtil.closeConnection();
				}
			}
		}*/
	//added by ankit for prop val : deed create new logic and method
	public byte[] newDeedPDF(RegCommonForm regForm, String path, String deedDraftId, HttpServletResponse response, int consumeFlag, String language) throws Exception {
		String regTxnId = regForm.getHidnRegTxnId();
		String deed = regForm.getDeedtype1();
		String instrument = regForm.getInstType();
		String exempStamp = regForm.getExempStamp();
		String exempReg = regForm.getExempReg();
		String totalDuty = regForm.getTotalduty();
		String totalReg = regForm.getRegistrationFee();
		String dutyTxnId = Integer.toString(regForm.getDuty_txn_id());
		logger.info("ExempStamp : " + regForm.getExempStamp());
		logger.info("ExempReg : " + regForm.getExempReg());
		logger.info("Totalduty : " + regForm.getTotalduty());
		logger.info("RegistrationFee : " + regForm.getRegistrationFee());
		PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
		String reportPath = prop.getValue("pdf.location");
		String uploadPath = prop.getValue("upload.location");
		HashMap jasperMap = new HashMap();
		jasperMap.put("RegAdjuNo", regForm.getHidnRegTxnId());
		jasperMap.put("reg_txn_id", regForm.getHidnRegTxnId());
		jasperMap.put("exempStamp", exempStamp);
		jasperMap.put("exempReg", exempReg);
		jasperMap.put("totalDuty", totalDuty);
		jasperMap.put("totalReg", totalReg);
		jasperMap.put("duty_txn_id", dutyTxnId);
		jasperMap.put("path", reportPath);
		logger.debug("reportPath PATH IS----------------->" + reportPath);
		logger.debug("DEED PDF jasperMap----------------->" + jasperMap.toString());
		DBUtility dbUtil = new DBUtility("");
		StringBuffer deedBufferFirst = deedDAO.getDeedContentPDFFirst(deedDraftId);
		StringBuffer deedBuffer = deedDAO.getDeedContentPDF(deedDraftId);
		jasperMap.put("deedID", deedDraftId);
		jasperMap.put("deedContent", deedBuffer);
		JasperReport jasperReport;
		try {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			logger.debug("connecting--->");
			System.out.println("connecting--->");
			if (RegInitConstant.LANGUAGE_ENGLISH.equalsIgnoreCase(language)) {
				reportPath += "PropertyValDetailSubReportPropVal.jrxml";
				jasperReport = JasperCompileManager.compileReport(path.concat("PropertyValDetailSubReportPropVal.jrxml"));
				JasperCompileManager.compileReport(path.concat("RegInitPlotPropVal.jrxml"));
				JasperCompileManager.compileReport(path.concat("RegInitfloorDetailsPropVal.jrxml"));
				JasperCompileManager.compileReport(path.concat("RegInitBuildingPropVal.jrxml"));
				JasperCompileManager.compileReport(path.concat("RegInitAgriPropVal.jrxml"));
				JasperCompileManager.compileReport(path.concat("DutyCalculation.jrxml"));
				//reportPath+="deedDraftPDF.jrxml";
				//jasperReport = JasperCompileManager.compileReport(path.concat("deedDraftPDF.jrxml"));
			} else {
				//reportPath+="deedDraftPDFH.jrxml";
				//jasperReport = JasperCompileManager.compileReport(path.concat("deedDraftPDFH.jrxml"));
				reportPath += "PropertyValDetailSubReportPropValH.jrxml";
				jasperReport = JasperCompileManager.compileReport(path.concat("PropertyValDetailSubReportPropValH.jrxml"));
				JasperCompileManager.compileReport(path.concat("RegInitPlotPropValH.jrxml"));
				JasperCompileManager.compileReport(path.concat("RegInitfloorDetailsPropValH.jrxml"));
				JasperCompileManager.compileReport(path.concat("RegInitBuildingPropValH.jrxml"));
				JasperCompileManager.compileReport(path.concat("RegInitAgriPropValH.jrxml"));
				JasperCompileManager.compileReport(path.concat("DutyCalculationH.jrxml"));
			}
			System.out.println("before conn");
			Connection conn = dbUtil.getDBConnection();
			System.out.println("after getting conn");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			//JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, jasperMap, conn);
			//JasperViewer.viewReport(jasperPrint);
			String pdfName = "DeedDraft";
			logger.debug("connection closed--->");
			System.out.println("connection closed--->");
			//ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			//logger.debug("OPENOFFICE_PATH = " + System.getenv("OPENOFFICE_PATH"));
			JrxmlExportUtility export = new JrxmlExportUtility();
			byte[] propBytes = export.getJasperDeedPdfviewerFromImg(jasperMap, reportPath, regForm.getHidnRegTxnId(), response, path, "P", consumeFlag, regForm.getDeedDraftId(), regForm.getHidnUserId());
			byte[] outputBytes = generateDeedPDF(path, regForm.getDeedDraftId(), response, consumeFlag, propBytes);
			if (consumeFlag != 1) {
				/*response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\\" + pdfName + ".pdf");
				//response.setContentLength(pdfDocReport.size());
				ServletOutputStream out = response.getOutputStream();
				//os.writeTo(out);
				out.write(propBytes);
				out.flush();
				out.close();*/
				//JrxmlExportUtility export = new JrxmlExportUtility();
				//export.generateDeedDOCX(jasperMap, reportPath, regTxnId,response);
				//export.generatePDF(jasperMap, reportPath, regTxnId,response);
				//export.generatePDFImage(jasperMap, reportPath, "sample",response);
				//export.generateHTML(jasperMap, reportPath, "sampledeed",response);
			}
			//return propBytes;
			return outputBytes;
			//return os.toByteArray();
		} catch (Exception e) {
			System.out.println("exception");
			logger.debug("exception");
			e.printStackTrace();
			System.out.println(e);
			//return null;
			throw e;
		} finally {
			if (!dbUtil.isClosed()) {
				dbUtil.closeConnection();
			}
		}
	}

	public boolean getDeedIsConsumed(String appId, String appType) throws Exception {
		return deedDAO.getDeedIsConsumed(appId, appType);
	}
}
