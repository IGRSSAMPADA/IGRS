package com.wipro.igrs.clr.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.jms.Session;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Request;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.digitalSign.DocumentService;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.rcms.bo.RevenueCaseBO;
import com.wipro.igrs.util.JrxmlExportUtility;

public class PDFFormVI {
	public static void generateFormAPDF(String regTxnId, HttpServletResponse response, String language) throws Exception {
		// HttpServletResponse response;
		System.out.println("in test method");
		RevenueCaseBO bo = new RevenueCaseBO();
		ArrayList formDetail = bo.getFormAData(regTxnId);
		//formDetail = (ArrayList) formDetail.get(0);
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		String fileName = "formvi.jrxml";
		String reportpath = pr.getValue("pdf.location")+File.separator+fileName;
		String body_first_en = "The copy of registered document no ";
		String body_first_hi = "पंजीकृत दस्तावेज क्रमांक ";
		String documentNumber = "";
		String body_second_en = " along with declarations by executant " + "and claimant in Form A-1 and Form A-2 made before the Sub-Registrar and " + "reciept of case fee are enclosed herewith for necessary action. The nature of " + "document and class of transaction recorded in the document is ";
		String body_second_hi = " के साथ निष्पादक और दावेदार द्वारा क्रमषः प्ररूप क-1 तथा प्ररूप क-2 में उप पंजीयक के समक्ष किए गए घोषणा पत्रों और मामले की फीस की रसीद आवष्यक कार्रवाई हेतु संलग्न हैं। दस्तावेज में अभिलिखित दस्तावेज की प्रकृति और संव्यवहार की श्रेणी ";
		String body_hi=" है।";
		String docType = "";
		for (int i = 0; i < formDetail.size(); i++) {
			ArrayList tempList = (ArrayList) formDetail.get(i);
			HashMap jasperMap = new HashMap();
			documentNumber=(String) tempList.get(0);
			docType=(language.equalsIgnoreCase("en")?(String) tempList.get(8):(String) tempList.get(8));
			String districtName = (language.equalsIgnoreCase("en")?(String) tempList.get(2):(String) tempList.get(6));
			String tehsilName = (language.equalsIgnoreCase("en")?(String) tempList.get(3):(String) tempList.get(7));
			String sroName = (language.equalsIgnoreCase("en")?(String) tempList.get(1):(String) tempList.get(5));
			String finalContent = body_first_hi + documentNumber + body_second_hi + docType + body_hi;
			Date date = Calendar.getInstance().getTime();
			String dateofsign = String.valueOf(date);
			jasperMap.put("documentContent", finalContent);
			jasperMap.put("sroname", sroName);
			jasperMap.put("district", "जिला  "+districtName);
			jasperMap.put("tehsil", "तहसील  "+tehsilName);
			jasperMap.put("dateofpresent",dateofsign);
			jasperMap.put("regTxnId",regTxnId);
			
			
			jasperMap.put("pagefooter", "*  यह कम्प्यूटर जनरेट दस्तावेज है और इस पर हस्ताक्षर अपेक्षित नहीं हैं।");
			// jasperMap.put("path", reportpath);
			JrxmlExportUtility export = new JrxmlExportUtility();
			//regTxnId="211110022";upload.location
			String formAPath = pr.getValue("upload.location")+regTxnId;
			String signedFormAPath = pr.getValue("upload.location")+regTxnId+"//signedFormA.pdf";
			
			formAPath=export.getJasperPdf(jasperMap, reportpath, "F", response, formAPath, "P");
			//new DocumentService().sign(formAPath, signedFormAPath, "IGRS000382",200,100,300,200);
			RegCommonBO commonBo = new RegCommonBO();
			boolean updateStatus = commonBo.updateFormPath(regTxnId,"A",formAPath);
			
		}
	}
}