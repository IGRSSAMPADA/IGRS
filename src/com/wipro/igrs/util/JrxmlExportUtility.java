package com.wipro.igrs.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.ResolutionSyntax;
import javax.print.attribute.standard.PrinterResolution;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.fill.JRBaseFiller;
import net.sf.jasperreports.engine.fill.JRFiller;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.baseaction.constant.Constants;
import com.wipro.igrs.db.ConnectionPool;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.report.action.MISReportAction;

public class JrxmlExportUtility {
	private static final Logger logger = Logger.getLogger(MISReportAction.class);

	public void generatePDF(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response) throws Exception {
		logger.debug("generatePDF Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Connection conn = null;
		byte[] pdf = null;
		DBUtility dbUtil = null;
		try {
			logger.debug("jasper compiling start");
			//Generate Jasper Report after compilation
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end");
			// Generate jasper print
			dbUtil = new DBUtility();
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
			//JasperViewer.viewReport(jasperPrint);
			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			logger.debug("OPENOFFICE_PATH = " + System.getenv("OPENOFFICE_PATH"));
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
			exporter.exportReport();
			logger.debug("Done--->EXPORTED:)");
			ByteArrayOutputStream os = (ByteArrayOutputStream) exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\\" + reportName + ".pdf");
			response.setContentLength(pdfReport.size());
			ServletOutputStream out = response.getOutputStream();
			os.writeTo(out);
			out.flush();
			out.close();
		} catch (JRException ex) {
			logger.debug(ex.getMessage(), ex);
		} catch (Exception exe) {
			logger.debug(exe.getMessage(), exe);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void generatePDFDiffSize(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response, int height, int width) {
		logger.debug("generatePDF Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Connection conn = null;
		byte[] pdf = null;
		DBUtility dbUtil = null;
		try {
			logger.debug("jasper compiling start");
			//Generate Jasper Report after compilation
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end");
			// Generate jasper print
			dbUtil = new DBUtility();
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
			//JasperViewer.viewReport(jasperPrint);
			jasperPrint.setPageHeight(height);
			jasperPrint.setPageWidth(width);
			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			logger.debug("OPENOFFICE_PATH = " + System.getenv("OPENOFFICE_PATH"));
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
			exporter.exportReport();
			logger.debug("Done--->EXPORTED:)");
			ByteArrayOutputStream os = (ByteArrayOutputStream) exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\\" + reportName + ".pdf");
			response.setContentLength(pdfReport.size());
			ServletOutputStream out = response.getOutputStream();
			os.writeTo(out);
			out.flush();
			out.close();
		} catch (JRException ex) {
			logger.debug(ex.getMessage(), ex);
		} catch (Exception exe) {
			logger.debug(exe.getMessage(), exe);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void generateXLS(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response) throws Exception {
		logger.debug("generateXLS Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Connection conn = ConnectionPool.getInstance().getDs().getConnection();
		byte[] pdf = null;
		//DBUtility dbUtil = null;
		try {
			logger.debug("jasper compiling start");
			//Generate Jasper Report after compilation
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end");
			// Generate jasper print
			//dbUtil = new DBUtility();
			logger.debug("jasper filling start");
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			logger.debug("jasper filling end");
			//JasperViewer.viewReport(jasperPrint);
			//  jasperMap.put("IS_IGNORE_PAGINATION", true);
			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			JRXlsExporter exporter = new JRXlsExporter();
			logger.debug("OPENOFFICE_PATH = " + System.getenv("OPENOFFICE_PATH"));
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			//we set the one page per sheet parameter here
			//exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
			exporter.exportReport();
			logger.debug("Done--->EXPORTED:)");
			ByteArrayOutputStream os = (ByteArrayOutputStream) exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
			response.setContentType("application/xls");
			response.setHeader("Content-Disposition", "attachment; filename=\\" + reportName + ".xls");
			response.setContentLength(pdfReport.size());
			ServletOutputStream out = response.getOutputStream();
			os.writeTo(out);
			out.flush();
			out.close();
		} catch (JRException ex) {
			logger.debug(ex.getMessage(), ex);
		} catch (Exception exe) {
			logger.debug(exe.getMessage(), exe);
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void generateHTML(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response) throws Exception {
		logger.debug("generateXLS Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Connection conn = ConnectionPool.getInstance().getDs().getConnection();
		byte[] pdf = null;
		//DBUtility dbUtil = null;
		try {
			logger.debug("jasper compiling start");
			//Generate Jasper Report after compilation
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end");
			// Generate jasper print
			//  dbUtil = new DBUtility();
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			//JasperViewer.viewReport(jasperPrint);
			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			JRHtmlExporter exporter = new JRHtmlExporter();
			logger.debug("OPENOFFICE_PATH = " + System.getenv("OPENOFFICE_PATH"));
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
			exporter.exportReport();
			System.out.println("Done--->");
			logger.debug("Done--->EXPORTED:)");
			ByteArrayOutputStream os = (ByteArrayOutputStream) exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
			response.setContentType("application/html");
			response.setHeader("Content-Disposition", "attachment; filename=\\" + reportName + ".html");
			response.setContentLength(pdfReport.size());
			ServletOutputStream out = response.getOutputStream();
			os.writeTo(out);
			out.flush();
			out.close();
		} catch (JRException ex) {
			logger.debug(ex.getMessage(), ex);
		} catch (Exception exe) {
			logger.debug(exe.getMessage(), exe);
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void generatePDFImage(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response) throws Exception {
		logger.debug("generatePDF Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Connection conn = ConnectionPool.getInstance().getDs().getConnection();
		byte[] pdf = null;
		//DBUtility dbUtil = null;
		//ServletOutputStream out ;
		try {
			logger.debug("jasper compiling start");
			//Generate Jasper Report after compilation
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end");
			// Generate jasper print
			// dbUtil = new DBUtility();
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			//JasperViewer.viewReport(jasperPrint);
			int pageIndex = 0;
			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			BufferedImage pageImage = new BufferedImage(jasperPrint.getPageWidth() + 1, jasperPrint.getPageHeight() + 1, BufferedImage.TYPE_INT_RGB);
			JRGraphics2DExporter exporter = new JRGraphics2DExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, pageImage.getGraphics());
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(pageIndex));
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
			exporter.exportReport();
			ByteArrayOutputStream os = (ByteArrayOutputStream) exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\\" + reportName + ".pdf");
			response.setContentLength(pdfReport.size());
			ServletOutputStream out = response.getOutputStream();
			os.writeTo(out);
			out.flush();
			out.close();
		} catch (JRException ex) {
			logger.debug(ex.getMessage(), ex);
		} catch (Exception exe) {
			logger.debug(exe.getMessage(), exe);
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void getJasperPdfviewerFromImg(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response, String path, String orientation) throws Exception {
		ActionForward forward = null;
		//HttpSession session = request.getSession(false);
		logger.debug("generatePDF Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Connection conn = ConnectionPool.getInstance().getDs().getConnection();
		byte[] pdf = null;
		//DBUtility dbUtil = null;
		//orientation=P,L i.e. Portrait,Landscape
		//ServletOutputStream out ;
		try {
			Document document = null;
			logger.debug("jasper compiling start");
			//Generate Jasper Report after compilation
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end");
			// Generate jasper print
			//dbUtil = new DBUtility();
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			Rectangle ngle = new Rectangle(1000, 1000);
			//  if("all".equalsIgnoreCase(iifName)||"plot".equalsIgnoreCase(iifName)){
			//  document = new Document(PageSize.A4,50,50,50,50);//PageSize.A4);
			//  size=75;
			// }
			// else{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if ("P".equalsIgnoreCase(orientation))
				document = new Document(PageSize.A4);//PageSize.A4);
			else
				document = new Document(PageSize.A4.rotate());
			int size = 85;
			//  }
			BufferedImage img = null;
			Graphics2D g = null;
			File output = null;
			String tempDirectory = path + "temp//";
			File folder = new File(tempDirectory);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String iifFolderName = jasperPrint.getName();
			String iifReporytName = jasperPrint.getName();
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				System.out.println(tempDirectory + iifReporytName + i + ".jpeg");
				output = new File(tempDirectory + iifReporytName + i + ".jpeg");
				img = new BufferedImage(jasperPrint.getPageWidth(), jasperPrint.getPageHeight(), BufferedImage.TYPE_INT_RGB);
				g = (Graphics2D) img.getGraphics();
				JRGraphics2DExporter exporter = new JRGraphics2DExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);
				exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(i));
				exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(0)); //lblPage border
				exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(0));
				exporter.exportReport();
				img.flush();
				g.dispose();
				ImageIO.write(img, "jpeg", output);
			}
			PdfWriter.getInstance(document, baos);
			document.open();
			Image image1 = null;
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				image1 = Image.getInstance(tempDirectory + iifReporytName + i + ".jpeg");
				image1.scalePercent(size);
				//     image1.scaleToFit( image1.getAbsoluteX(),  image1.getAbsoluteY());
				document.add(image1);
			}
			document.addTitle(iifReporytName);
			document.close();
			/* FileInputStream inputStream = null;
			 inputStream = new FileInputStream(tempDirectory + "//" + iifReporytName+ ".pdf");
			 File file1 = new File(tempDirectory + "//" + iifReporytName + ".pdf");
			// long filesize = file1.length();
			 OutputStream outputStream = new FileOutputStream(tempDirectory + "//" + iifReporytName + ".pdf");
			 byte[] buffer = new byte[10024];
			 int bytesRead = 0;
			 do {
			     bytesRead = inputStream.read(buffer);
			     outputStream.write(buffer, 0, bytesRead);
			     
			 }while (bytesRead == buffer.length);
			 outputStream.flush();
			 outputStream.close();
			 inputStream.close();*/
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\\" + reportName + ".pdf");
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			//out.write(abc);
			baos.writeTo(out);
			out.flush();
			out.close();
			////////////////////////////To Cleand Temp File After Export ////////////////////////////////////
			File imagefiles = null;
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				imagefiles = new File(tempDirectory + iifReporytName + i + ".jpeg");
				boolean success = imagefiles.delete();
			}
			/*     File pdffile = new File(tempDirectory + iifReporytName  + ".pdf");
			     boolean success = pdffile.delete();*/
			logger.debug("cleaning done , now opening file");
			////////////////////////////To Cleand Temp File After Export ////////////////////////////////////
			/* 
			 * Opening the file
			 */
			/*File fileToOpen = new File(path +uniqeid+ ".pdf");
			
			 if (Desktop.isDesktopSupported()) {
			    try {

			        Desktop.getDesktop().open(fileToOpen);
			    } catch (IOException ex) {
			        ex.printStackTrace();
			    }
			}*/
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			logger.debug("method name getJasperPdfviewerFromImg");
			logger.debug(e);
			e.printStackTrace();
			throw new Exception(Constants.PDFEXCEPTION);
			//session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
			//forward =  new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
			//	logger.debug(e);
			//e.printStackTrace();
			//  form.getAppdto().setCheck("exists");
			//Session session = request.getSession(true);
			//session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
		} finally {
			if (conn != null) {
				if (!conn.isClosed())
					conn.close();
			}
		}
	}

	public void generateDOCX(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response) throws Exception {
		logger.debug("generateDOCX Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		//Connection conn=null;
		Connection conn = ConnectionPool.getInstance().getDs().getConnection();
		byte[] pdf = null;
		//DBUtility dbUtil = null;
		try {
			logger.debug("jasper compiling start");
			//Generate Jasper Report after compilation
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end");
			// Generate jasper print
			//  dbUtil = new DBUtility();
			logger.debug("jasper filling start");
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			logger.debug("jasper filling end");
			//JasperViewer.viewReport(jasperPrint);
			//  jasperMap.put("IS_IGNORE_PAGINATION", true);
			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			JRDocxExporter exporter = new JRDocxExporter();
			logger.debug("OPENOFFICE_PATH = " + System.getenv("OPENOFFICE_PATH"));
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			//exporter.setParameter(JRDocxExporterParameter.FONT_MAP, Boolean.TRUE);
			//we set the one page per sheet parameter here
			//exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
			exporter.exportReport();
			logger.debug("Done--->EXPORTED:)");
			ByteArrayOutputStream os = (ByteArrayOutputStream) exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
			response.setContentType("application/docx");
			response.setHeader("Content-Disposition", "attachment; filename=\\" + reportName + ".docx");
			response.setContentLength(pdfReport.size());
			ServletOutputStream out = response.getOutputStream();
			os.writeTo(out);
			out.flush();
			out.close();
		} catch (JRException ex) {
			logger.debug(ex.getMessage(), ex);
		} catch (Exception exe) {
			logger.debug(exe.getMessage(), exe);
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void getJasperPdfviewerFromImgDiffSize(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response, String path, String orientation, int height, int width) throws Exception {
		logger.debug("generatePDF Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Connection conn = ConnectionPool.getInstance().getDs().getConnection();
		byte[] pdf = null;
		//DBUtility dbUtil = null;
		//orientation=P,L i.e. Portrait,Landscape
		//ServletOutputStream out ;
		try {
			Document document = null;
			logger.debug("jasper compiling start");
			//Generate Jasper Report after compilation
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end");
			// Generate jasper print
			//dbUtil = new DBUtility();
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			jasperPrint.setPageHeight(height);
			jasperPrint.setPageWidth(width);
			Rectangle ngle = new Rectangle(595, 1000);
			//  if("all".equalsIgnoreCase(iifName)||"plot".equalsIgnoreCase(iifName)){
			//  document = new Document(PageSize.A4,50,50,50,50);//PageSize.A4);
			//  size=75;
			// }
			// else{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if ("P".equalsIgnoreCase(orientation))
				document = new Document(PageSize.A4);//PageSize.A4);
			else
				document = new Document(PageSize.A4.rotate());
			int size = 80;
			//  }
			BufferedImage img = null;
			Graphics2D g = null;
			File output = null;
			String tempDirectory = path + "//temp//";
			File folder = new File(tempDirectory);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String iifFolderName = jasperPrint.getName();
			String iifReporytName = jasperPrint.getName();
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				output = new File(tempDirectory + iifReporytName + i + ".jpeg");
				img = new BufferedImage(jasperPrint.getPageWidth(), jasperPrint.getPageHeight(), BufferedImage.TYPE_INT_RGB);
				g = (Graphics2D) img.getGraphics();
				JRGraphics2DExporter exporter = new JRGraphics2DExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);
				exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(i));
				exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(0)); //lblPage border
				exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(0));
				exporter.exportReport();
				img.flush();
				g.dispose();
				ImageIO.write(img, "jpeg", output);
			}
			PdfWriter.getInstance(document, baos);
			document.open();
			Image image1 = null;
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				image1 = Image.getInstance(tempDirectory + iifReporytName + i + ".jpeg");
				image1.scalePercent(size);
				//     image1.scaleToFit( image1.getAbsoluteX(),  image1.getAbsoluteY());
				document.add(image1);
			}
			document.addTitle(iifReporytName);
			document.close();
			/* FileInputStream inputStream = null;
			 inputStream = new FileInputStream(tempDirectory + "//" + iifReporytName+ ".pdf");
			 File file1 = new File(tempDirectory + "//" + iifReporytName + ".pdf");
			// long filesize = file1.length();
			 OutputStream outputStream = new FileOutputStream(tempDirectory + "//" + iifReporytName + ".pdf");
			 byte[] buffer = new byte[10024];
			 int bytesRead = 0;
			 do {
			     bytesRead = inputStream.read(buffer);
			     outputStream.write(buffer, 0, bytesRead);
			     
			 }while (bytesRead == buffer.length);
			 outputStream.flush();
			 outputStream.close();
			 inputStream.close();*/
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\\" + reportName + ".pdf");
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			//out.write(abc);
			baos.writeTo(out);
			out.flush();
			out.close();
			////////////////////////////To Cleand Temp File After Export ////////////////////////////////////
			File imagefiles = null;
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				imagefiles = new File(tempDirectory + iifReporytName + i + ".jpeg");
				boolean success = imagefiles.delete();
			}
			/*     File pdffile = new File(tempDirectory + iifReporytName  + ".pdf");
			     boolean success = pdffile.delete();*/
			logger.debug("cleaning done , now opening file");
			////////////////////////////To Cleand Temp File After Export ////////////////////////////////////
			/* 
			 * Opening the file
			 */
			/*File fileToOpen = new File(path +uniqeid+ ".pdf");
			
			 if (Desktop.isDesktopSupported()) {
			    try {

			        Desktop.getDesktop().open(fileToOpen);
			    } catch (IOException ex) {
			        ex.printStackTrace();
			    }
			}*/
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				if (!conn.isClosed())
					conn.close();
			}
		}
	}

	//added for deed PDF : prop val changes
	public byte[] getJasperDeedPdfviewerFromImg(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response, String path, String orientation, int statusFlg, String deedId, String userId) throws Exception {
		ActionForward forward = null;
		//HttpSession session = request.getSession(false);
		logger.debug("generatePDF Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Connection conn = ConnectionPool.getInstance().getDs().getConnection();
		byte[] pdf = null;
		String imgFormat = ".jpeg";
		String imgFormat2 = ".bmp";
		String imgFormat3 = ".png";
		//DBUtility dbUtil = null;
		//orientation=P,L i.e. Portrait,Landscape
		//ServletOutputStream out ;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			Document document = null;
			logger.debug("jasper compiling start ....... getJasperDeedPdfviewerFromImg");
			//Generate Jasper Report after compilation
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end ........ getJasperDeedPdfviewerFromImg");
			// Generate jasper print
			//dbUtil = new DBUtility();
			//  jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			JRBaseFiller filler = JRFiller.createFiller(DefaultJasperReportsContext.getInstance(), jasperReport);
			jasperPrint = filler.fill(jasperMap, conn);
			//Rectangle ngle = new Rectangle(1000, 1000);
			//  if("all".equalsIgnoreCase(iifName)||"plot".equalsIgnoreCase(iifName)){
			//  document = new Document(PageSize.A4,50,50,50,50);//PageSize.A4);
			//  size=75;
			// }
			// else{
			if ("P".equalsIgnoreCase(orientation)) {
				document = new Document(PageSize.A4, 20, 00, 100, 20);//PageSize.A4);
			} else {
				document = new Document(PageSize.A4.rotate());
			}
			int size = 85;
			float zoom = 1f;
			//  }
			BufferedImage img = null;
			Graphics2D g = null;
			File output = null;
			String tempDirectory = path + "temp//" + reportName + "//";
			File folder = new File(tempDirectory);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
			attrs.add(new PrinterResolution(144, 144, ResolutionSyntax.DPI));
			String iifFolderName = jasperPrint.getName();
			String iifReporytName = jasperPrint.getName();
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				output = new File(tempDirectory + iifReporytName + i + ".jpeg");
				//img = new BufferedImage( (int)(jasperPrint.getPageWidth()*zoom)+1, (int)(jasperPrint.getPageHeight()*zoom)+1, BufferedImage.TYPE_INT_RGB);
				img = new BufferedImage(jasperPrint.getPageWidth(), jasperPrint.getPageHeight(), BufferedImage.TYPE_INT_RGB);
				g = (Graphics2D) img.getGraphics();
				JRGraphics2DExporter exporter = new JRGraphics2DExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);
				exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(i));
				exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(0)); //lblPage border
				exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(0));
				//  exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, new Float(zoom));
				// exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, attrs);
				exporter.exportReport();
				img.flush();
				g.dispose();
				Object createdby = filler.getVariableValue("CREATED_BY_REPORT");
				int count = 0;
				if (createdby instanceof String) {
					if (!((String) createdby).equalsIgnoreCase(userId)) {
						File imagefiles = null;
						for (int k = i; k >= 0; k--) {
							imagefiles = new File(tempDirectory + iifReporytName + k + ".jpeg");
							boolean success = imagefiles.delete();
						}
						System.out.println(" user id not matched !!!!!");
						throw new Exception("------------------ user not matched --------------------");
					}
				}
				/*Object propId = filler.getVariableValue("PROP_ID_REPORT");
				if (propId instanceof String){
				  propId
				}*/
				ImageIO.write(img, "jpeg", output);
			}
			PdfWriter.getInstance(document, baos);
			document.open();
			Image image1 = null;
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				image1 = Image.getInstance(tempDirectory + iifReporytName + i + ".jpeg");
				//    image1.scalePercent(size);
				//image1.scaleAbsolute(200f, 50f);
				//image1.setAbsolutePosition(10f, 10f); // Adding Image
				//image1.scaleToFit( 45f, 45f);
				//image1.scaleToFit( 545,  750);
				document.add(image1);
			}
			document.addTitle(iifReporytName);
			document.close();
			/* FileInputStream inputStream = null;
			 inputStream = new FileInputStream(tempDirectory + "//" + iifReporytName+ ".pdf");
			 File file1 = new File(tempDirectory + "//" + iifReporytName + ".pdf");
			// long filesize = file1.length();
			 OutputStream outputStream = new FileOutputStream(tempDirectory + "//" + iifReporytName + ".pdf");
			 byte[] buffer = new byte[10024];
			 int bytesRead = 0;
			 do {
			     bytesRead = inputStream.read(buffer);
			     outputStream.write(buffer, 0, bytesRead);
			     
			 }while (bytesRead == buffer.length);
			 outputStream.flush();
			 outputStream.close();
			 inputStream.close();*/
			if (statusFlg != 1) {
				/* response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "attachment; filename=\\"+reportName+".pdf");
					response.setContentLength(baos.size());
					ServletOutputStream out = response.getOutputStream();
					
					
					//out.write(abc);
					
					baos.writeTo(out);
					out.flush();
					out.close();*/
			}
			////////////////////////////To Cleand Temp File After Export ////////////////////////////////////
			File imagefiles = null;
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				imagefiles = new File(tempDirectory + iifReporytName + i + ".jpeg");
				boolean success = imagefiles.delete();
			}
			/*     File pdffile = new File(tempDirectory + iifReporytName  + ".pdf");
			     boolean success = pdffile.delete();*/
			logger.debug("cleaning done , now opening file");
			////////////////////////////To Cleand Temp File After Export ////////////////////////////////////
			/* 
			 * Opening the file
			 */
			/*File fileToOpen = new File(path +uniqeid+ ".pdf");
			
			 if (Desktop.isDesktopSupported()) {
			    try {

			        Desktop.getDesktop().open(fileToOpen);
			    } catch (IOException ex) {
			        ex.printStackTrace();
			    }
			}*/
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			logger.debug("method name getJasperPdfviewerFromImg");
			logger.debug(e);
			e.printStackTrace();
			throw new Exception(Constants.PDFEXCEPTION);
			//session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
			//forward =  new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
			//	logger.debug(e);
			//e.printStackTrace();
			//  form.getAppdto().setCheck("exists");
			//Session session = request.getSession(true);
			//session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
		} finally {
			if (conn != null) {
				if (!conn.isClosed())
					conn.close();
			}
		}
		return baos.toByteArray();
	}
	/*	
		public void generateDeedDOCX(HashMap jasperMap,String reportPath,String reportName,HttpServletResponse response)throws Exception
		{
			logger.debug("generateDOCX Name :::::::::::Report Path"+reportPath+"Report Name:::::::"+reportName);

			JasperReport jasperReport;
			JasperPrint jasperPrint;
			//Connection conn=null;
			Connection conn = ConnectionPool.getInstance().getDs().getConnection();
			byte[] pdf=null;
			//DBUtility dbUtil = null;
	               try{
	            	logger.debug("jasper compiling start");
	            	//Generate Jasper Report after compilation
	                jasperReport = JasperCompileManager.compileReport(reportPath);
	                logger.debug("jasper compiling end");
	                // Generate jasper print
	              //  dbUtil = new DBUtility();
	            	logger.debug("jasper filling start");
	                jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, conn);
	                logger.debug("jasper filling end");
	        	 	//JasperViewer.viewReport(jasperPrint);
	              //  jasperMap.put("IS_IGNORE_PAGINATION", true);
	                
	        	 	 	ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
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
	        			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
	        			exporter.exportReport();
	        			logger.debug("Done--->EXPORTED:)");
	        			
	        			ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
	        		//	String deed = new String(os.toByteArray(), "UTF-8");
	        			StringBuffer deedBuffer = new StringBuffer().append(new String(os.toByteArray(), "UTF-8"));
	        			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        			com.itextpdf.text.Document document = new com.itextpdf.text.Document();
	        			com.itextpdf.text.pdf.PdfWriter writer = com.itextpdf.text.pdf.PdfWriter.getInstance(document, baos); 
	        		   
	        		    document.open();
	        		    document.newPage();
	        		    PdfContentByte cb = writer.getDirectContent();
	        		    System.out.println(document.getPageSize());
	        		  
	        		    PdfTemplate pdt=cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
	        		    
	        		      Graphics2D g2 = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
	        			if(deedBuffer.length()>0){
	        		    	  cb = writer.getDirectContent();
	        		    	  pdt=cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
	        				  
	        		    
	        		      logger.debug("PdfContentByte INIT");
	        		      g2 = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
	        		      logger.debug("SECOND ONWARDS GRAPHIC INIT CREATED");
	        		      
	        		      document.newPage();
	        			  logger.debug("NEW PAGE CREATED");
	        			  drawStringMultiLine(g2, deedBuffer.toString(), (int)PageSize.A4.getWidth()-(94+43), 94, 144,document,cb,pdt,writer);//x->left margin(94),y->top margin
	        			  logger.debug("sECOND ONWARds PAGE DATA CREATED");
	        			 // g2.dispose();
	        			 
	        			  document.add(new Paragraph(" "));
	        			  logger.debug("Paragraph added");
	        		      }
	        			    document.close();
	        			
	        			
	        	  		response.setContentType("application/pdf");
	        	  		response.setHeader("Content-Disposition", "attachment; filename=\\"+reportName+".pdf");
	        	  		response.setContentLength(pdfReport.size());
	        	  		ServletOutputStream out = response.getOutputStream();
	        	  		baos.writeTo(out);
	        	  		out.flush();
	        	  		out.close();
	               }
	               catch (JRException ex){
	            	   logger.debug(ex.getMessage(),ex);
	               }
	               catch ( Exception exe) 
	       		{
	            	   logger.debug(exe.getMessage(),exe);
	       		}
	               
	               finally{
	       			try {
	       				if ( conn != null){
	    					if(!conn.isClosed())
	    						conn.close();}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	       		}
		}
		
		
		public void generateDeedHTML(HashMap jasperMap,String reportPath,String reportName,HttpServletResponse response)throws Exception
		{
			logger.debug("generateXLS Name :::::::::::Report Path"+reportPath+"Report Name:::::::"+reportName);

			JasperReport jasperReport;
			JasperPrint jasperPrint;
			Connection conn = ConnectionPool.getInstance().getDs().getConnection();
			byte[] pdf=null;
			//DBUtility dbUtil = null;
	               try{
	            	   logger.debug("jasper compiling start");
	            	//Generate Jasper Report after compilation
	                jasperReport = JasperCompileManager.compileReport(reportPath);
	                logger.debug("jasper compiling end");
	                // Generate jasper print
	              //  dbUtil = new DBUtility();
	                jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, conn);
	        	 	//JasperViewer.viewReport(jasperPrint);
	               
	                
	        	 	 	ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
	        	 	 	JRHtmlExporter exporter = new JRHtmlExporter();
	        	 	 	logger.debug("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
	        			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
	        			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
	        			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
	        			exporter.exportReport();
	        			System.out.println("Done--->");
	        			logger.debug("Done--->EXPORTED:)");
	        			
	        			ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);        			      
	        			Document document = new Document();
	        			OutputStream file = new FileOutputStream(new File("F://HTMLtoPDF.pdf"));
						PdfWriter writer = PdfWriter.getInstance(document, file);
						document.open();
						InputStream is = new ByteArrayInputStream(os.toString().getBytes());
						XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
						document.close();
						
	        	  		response.setContentType("application/html");
	        	  		response.setHeader("Content-Disposition", "attachment; filename=\\"+reportName+".html");
	        	  		response.setContentLength(pdfReport.size());
	        	  		ServletOutputStream out = response.getOutputStream();
	        	  		os.writeTo(out);
	        	  		out.flush();
	        	  		out.close();
	               }
	               catch (JRException ex){
	            	   logger.debug(ex.getMessage(),ex);
	               }
	               catch ( Exception exe) 
	       		{
	            	   logger.debug(exe.getMessage(),exe);
	       		}finally{
	       			try {
	       				if ( conn != null){
	    					if(!conn.isClosed())
	    						conn.close();
	    				}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	       		}
		}
		
		
		public  void drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y,com.itextpdf.text.Document document,com.itextpdf.text.pdf.PdfContentByte cb,com.itextpdf.text.pdf.PdfTemplate pdt,com.itextpdf.text.pdf.PdfWriter writer) {
		    FontMetrics m = g.getFontMetrics();
		  System.out.println(m.getFont());
		  try{
		    if(m.stringWidth(text) < lineWidth) {
		        g.drawString(text, x, y);
		    } else {
		    	 String splitf[]=text.split("\n");
	        	for(int k=0;k<splitf.length;k++){
	        		
	        		
	               
	        	
		        String[] words = splitf[k].split(" ");
		       if(words[0].trim().equals("")){
		    	   y += m.getHeight();
		       }
		        if(words.length>0){
		        String currentLine = words[0];
		        for(int i = 1; i < words.length; i++) {
		            if(m.stringWidth(currentLine+words[i]) < lineWidth) {
		                currentLine += " "+words[i];
		                
		               
		            } else {
		            	String split[]=currentLine.split("\n");
		            	for(int j=0;j<split.length;j++){
		            		g.drawString(split[j], x, y);
			                y += m.getHeight();
		            	}
		               // g.drawString(currentLine, x, y);
		               // y += m.getHeight();
		                currentLine = words[i];
		            }
		            System.out.println("Y IS----->"+y);
		            System.out.println("X IS----->"+x);
		            if(y>(int)PageSize.A4.getHeight()-(148)){//148 is the bottom margin
		            	logger.debug("X IS "+x+"Y IS "+y);
		            	  g.dispose();
		        		  cb.addTemplate(pdt,5,-7);
		            	
		   			  
		       	      cb = writer.getDirectContent();
		       	      pdt=cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
		       	      logger.debug("PdfContentByte INIT");
		       	      g = pdt.createGraphicsShapes(PageSize.A4.getWidth(), PageSize.A4.getHeight());
		       	      logger.debug("SECOND ONWARDS GRAPHIC INIT CREATED");
		            	document.newPage();
		            	y=144;//TOP MARGIN
		            }
		        }
		        
		        if(currentLine.trim().length() > 0) {
		            g.drawString(currentLine, x, y);
		            y += m.getHeight();//added
		        }
		        if(currentLine.trim().length() > lineWidth) {
		        	y += m.getHeight();//next line
		        }
	        	}
		    }
		  }catch(Exception e){
			  logger.debug(e.getMessage(),e);
			  
		  }finally{
			  g.dispose();
			  logger.debug("sECOND ONWARds DISPOSE finally");
			  cb.addTemplate(pdt,5,-7);
			  logger.debug("sECOND ONWARds temp finally");
		  }
		   // }
		}
		*/
	
	public String getJasperPdf(HashMap jasperMap, String reportPath, String reportName, HttpServletResponse response, String path, String orientation) throws Exception {

		//ActionForward forward = null;
		logger.debug("generatePDF Name :::::::::::Report Path" + reportPath + "Report Name:::::::" + reportName);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		
		//String regTxnId="211110022";
		String formAPath = path+File.separator;
		Connection conn = ConnectionPool.getInstance().getDs().getConnection();
		byte[] pdf = null;
		try {
			Document document = null;
			logger.debug("jasper compiling start");
			jasperReport = JasperCompileManager.compileReport(reportPath);
			logger.debug("jasper compiling end");
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap, conn);
			Rectangle ngle = new Rectangle(1000, 1000);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if ("P".equalsIgnoreCase(orientation))
				document = new Document(PageSize.A4);//PageSize.A4);
			else
				document = new Document(PageSize.A4.rotate());
			int size = 85;
			//  }
			BufferedImage img = null;
			Graphics2D g = null;
			File output = null;
			String tempDirectory = path+File.separator + "temp"+File.separator;
			File folder = new File(tempDirectory);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			
			String iifReporytName = jasperPrint.getName();
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				System.out.println(tempDirectory + iifReporytName + i + ".jpeg");
				output = new File(tempDirectory + iifReporytName + i + ".jpeg");
				img = new BufferedImage(jasperPrint.getPageWidth(), jasperPrint.getPageHeight(), BufferedImage.TYPE_INT_RGB);
				g = (Graphics2D) img.getGraphics();
				JRGraphics2DExporter exporter = new JRGraphics2DExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);
				exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(i));
				exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(0)); //lblPage border
				exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(0));
				exporter.exportReport();
				img.flush();
				g.dispose();
				ImageIO.write(img, "jpeg", output);
			}
			PdfWriter.getInstance(document, baos);
			document.open();
			Image image1 = null;
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				image1 = Image.getInstance(tempDirectory + iifReporytName + i + ".jpeg");
				image1.scalePercent(size);
				//     image1.scaleToFit( image1.getAbsoluteX(),  image1.getAbsoluteY());
				document.add(image1);
			}
			document.addTitle(iifReporytName);
			document.close();
			 FileInputStream inputStream = null;
			// File file1 = new File(formAPath);
			 File formAFolder = new File(formAPath);
				if (!formAFolder.exists()) {
					formAFolder.mkdirs();
				}
				formAPath=formAPath + iifReporytName + ".pdf";
			//	File file1 = new File(formAPath);
			 inputStream = new FileInputStream(tempDirectory + iifReporytName+"0"+ ".jpeg");
			// long filesize = file1.length();
			 response.setContentLength(baos.size());
			 OutputStream outputStream = new FileOutputStream(formAPath);
			 byte[] buffer = new byte[10024];
			 int bytesRead = 0;

			 baos.writeTo(outputStream);
			 outputStream.flush();
			 outputStream.close();
			 inputStream.close();
			
			File imagefiles = null;
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				imagefiles = new File(tempDirectory + iifReporytName + i + ".jpeg");
				boolean success = imagefiles.delete();
			}
			
			logger.debug("cleaning done , now opening file");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			logger.debug("method name getJasperPdfviewerFromImg");
			logger.debug(e);
			e.printStackTrace();
			throw new Exception(Constants.PDFEXCEPTION);
			
		} finally {
			if (conn != null) {
				if (!conn.isClosed())
					conn.close();
			}
		}
	return formAPath;
	}
}
