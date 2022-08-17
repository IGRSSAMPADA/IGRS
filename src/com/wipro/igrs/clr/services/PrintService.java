package com.wipro.igrs.clr.services;

import java.io.File;
import java.math.BigInteger;

import com.wipro.igrs.AadharEsign.util.GenerateFormVIAENGHINDI;
import com.wipro.igrs.login.form.LoginForm;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.rcms.pdf.GenerateRCMSReceipt;
import com.wipro.igrs.rcms.pdf.GenerateTeritorialCourtReceipt;

public class PrintService {
	public static void main(String[] args) throws Exception {
		//PDFGeneration pdf = new PDFGeneration("220422000001", "en");
		RegCommonBO bo = new RegCommonBO();
		//CLRResponse.insertCLRData("300522000001");
		//CLRResponse.insertCLRData("300522000002");
		//CLRResponse.insertCLRData("300522000010");
		//CLRResponse.insertCLRData("300522000005");
		//CLRResponse.insertCLRData("300522000008");
		/*CLRResponse.insertCLRData("300522000015");
		CLRResponse.insertCLRData("300522000002");
		CLRResponse.insertCLRData("300522000004");
		CLRResponse.insertCLRData("300522000006");
		CLRResponse.insertCLRData("300522000010");
		CLRResponse.insertCLRData("300522000005");
		CLRResponse.insertCLRData("300522000016");
		CLRResponse.insertCLRData("300522000014");
		CLRResponse.insertCLRData("300522000011");
		CLRResponse.insertCLRData("300522000001");
		CLRResponse.insertCLRData("300522000008");
		CLRResponse.insertCLRData("300522000012");*/
		//System.out.println(bo.getFormOneInsertStatus("280422000015","FORM_VIB"));
		GenerateRCMSReceipt gr = new GenerateRCMSReceipt();
		//gr.generateRCMSReceipt("300522000008", 4);
		GenerateFormVIAENGHINDI gn = new GenerateFormVIAENGHINDI();
		gn.generateFormVIAPDF("010622000002", "hi", "C:/Users/SA334510/Documents/shared1/esignpdf/formviA/020622000001/FormA1.pdf");
		//CLRResponse.insertCLRData("200522000002");
		FormVIBPdfGeneration fb = new FormVIBPdfGeneration();
		//fb.generationFormVIB("030622000002", "hi", "C:/Users/SA334510/Documents/shared1/esignpdf/formviB/020622000001/FormA2.pdf");
		MergedFormAndSign mg = new MergedFormAndSign();
		//mg.mergeFormA1("20622000001");
		GenerateTeritorialCourtReceipt gt = new GenerateTeritorialCourtReceipt();
		//gt.generateRCMSReceipt("020622000001", 4);
	}
}
