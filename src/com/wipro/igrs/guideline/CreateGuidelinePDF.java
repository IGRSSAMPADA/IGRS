/**
 * 
 */
package com.wipro.igrs.guideline;

import in.cdac.ilcg.jasperreports.pdfexporter.JRPdfExporter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author MA264124
 *
 */
public class CreateGuidelinePDF {

	private Logger logger = Logger.getLogger(CreateGuidelinePDF.class);
	
	public CreateGuidelinePDF() {
	}
	
	 public void generatePDF(String guideID, String lang, String financialYear) {
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		try {
			String uploadDir = "";
			PropertiesFileReader prObj;
			try {
				prObj = PropertiesFileReader.getInstance("resources.igrs");
				uploadDir = prObj.getValue("GuidelineSavePath");
				System.out.println(uploadDir);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			String filePath = uploadDir.replace("//", File.separator);
			filePath = filePath + File.separator + guideID;
			File folder = new File(filePath);
			if (!folder.exists()) {
				System.out.println(folder.toString());
				folder.mkdirs();
			}

			if (lang.equalsIgnoreCase("en")) {
				File folderH = new File(filePath.concat(File.separator
						+ "ENGLISH"));
				if (!folderH.exists()) {
					System.out.println(folderH.toString());

					folderH.mkdirs();

				}
				filePath = filePath.concat(File.separator + "ENGLISH");
			} else {
				File folderH = new File(filePath.concat(File.separator
						+ "HINDI"));
				if (!folderH.exists()) {
					System.out.println(folderH.toString());
					folderH.mkdirs();
				}
				filePath = filePath.concat(File.separator + "HINDI");
			}
			logger.debug("FOLDER STRUCTURE CREATED ");

			PropertiesFileReader prop = PropertiesFileReader
					.getInstance("resources.igrs");
			String path = prop.getValue("pdf.location");
			HashMap jasperMap = new HashMap();
			jasperMap.put("path", path);
			jasperMap.put("lang", lang);
			jasperMap.put("plotFinancialYear", financialYear);
			jasperMap.put("guideID", guideID);
			jasperReport = JasperCompileManager.compileReport(prop.getValue(
					"pdf.location").concat("guidelineNeww.jrxml"));
			DBUtility dbUtil = null;
			Connection conn = null;
			try{
				dbUtil = new DBUtility();
				conn = dbUtil.getDBConnection();
			}catch (Exception e){
				e.printStackTrace();
			}
			
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperMap,
					conn);
			
			// JasperViewer.viewReport(jasperPrint);
			jasperPrint.setPageHeight(612);
			jasperPrint.setPageWidth(1008);
			ByteArrayOutputStream pdfReportPlot = new ByteArrayOutputStream();
			JRPdfExporter exporterPlot = new JRPdfExporter();

			exporterPlot.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporterPlot.setParameter(
					JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporterPlot.setParameter(JRExporterParameter.CHARACTER_ENCODING,
					"UTF-8");
			exporterPlot.setParameter(JRExporterParameter.OUTPUT_STREAM,
					pdfReportPlot);
			exporterPlot.exportReport();

			byte bytePlot[] = pdfReportPlot.toByteArray();

			File newFilePlot = new File(filePath.concat(File.separator + "GuidelineFull.pdf"));
			FileOutputStream fosp = new FileOutputStream(newFilePlot);
			fosp.write(bytePlot);
			fosp.close();
			logger.debug("GuidelineFull.pdf saved.");

			dbUtil.closeConnection();
			
			System.out.println("Guideline ID :- " + guideID +" Successfully created..");
			logger.debug("Guideline ID :- " + guideID +" Successfully created..");
		} catch (Exception e) {
			logger.debug("EXCEPTION" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length ==3){
			System.out.println("Guideline ID :- "+args[0]);
			System.out.println("Locale :- "+args[1]);
			System.out.println("Financial Year :- "+args[2]);
			CreateGuidelinePDF obj = new CreateGuidelinePDF();
			obj.generatePDF(args[0], args[1], args[2]);
		}else{
			System.out.println("main should have three arguments.. 1)Guideline-ID, " +
					"2)Language, 3)Financial Year.");
		}
	}

}
