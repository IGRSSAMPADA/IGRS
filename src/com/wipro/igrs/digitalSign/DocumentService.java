package com.wipro.igrs.digitalSign;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author psrivastava
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
//import com.itextpdf.text.pdf.PdfPKCS7;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignature;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.safenetinc.luna.LunaSlotManager;
import com.safenetinc.luna.provider.key.LunaKey;
import com.safenetinc.luna.provider.LunaProvider;


public class DocumentService {
    
	
        //boolean transactional = false;
	private static Logger logger= (Logger) Logger.getLogger(DocumentService.class);
    public static LunaSlotManager hsmConnection = LunaSlotManager.getInstance();
    public static String PartitionName = "IGRS-DEPARTMENT-USERS"; //= "esignaturePartitionName";
    public static String PartitionPassword = "bTY4-SCps-LJd6-d3tT"; //= "esignaturePartitionPassword";
  //  public static String CertLabel = "test test test"; //= "esignatureCertLabel";

/*public static void sign(String documentPath,String signedDocumentPath,String certLabel,int x1,int y1,int x2,int y2 ) throws Exception {/*
        logger.debug("Entered Sign Method wirh Subject Name : "+certLabel);
        
        PrivateKey priv = getPrivateKeyLabel(certLabel);
        Certificate[] chain = getcertificatechain(certLabel);
        
System.out.println("chain--" +chain.length);
         // documentPath = "C:\\P2P2013_041.pdf";
       //   signedDocumentPath = "C:\\signed_P2P2013_041.pdf";
        
     //  createPdf("C:\\P2P2013_041.pdf");
        //  createPdf(documentPath);
logger.debug("Inside Sign Method");

        PdfReader reader = new PdfReader(documentPath);
        File signed = new File(signedDocumentPath);
        logger.debug("Got the PDF");
        FileOutputStream fout = new FileOutputStream(signed);
        PdfStamper stamper = PdfStamper.createSignature(reader, fout, '\0', null, true);
        PdfSignatureAppearance sap = stamper.getSignatureAppearance();
       //sap.setReason("I'm the author");
       //sap.setLocation("India");
       Calendar cal  = Calendar.getInstance();
       Date date = new Date();
       cal.setTime(date);
       sap.setSignDate(cal);
       int m = stamper.getReader().getNumberOfPages();
       //sap.setVisibleSignature(new Rectangle(100, 100, 200, 200), m, "signature");    
       sap.setVisibleSignature(new Rectangle(x1, y1, x2, y2), 1, "signature");
       sap.setCrypto(priv, chain ,null , PdfSignatureAppearance.SELF_SIGNED);

       sap.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
       //sap.setRenderingMode(PdfSignatureAppearance.RenderingMode.NAME_AND_DESCRIPTION);
       sap.setAcro6Layers(false);
       PdfTemplate n1 = sap.getLayer(1);
       n1.reset();
       PdfTemplate n3 = sap.getLayer(3);
       n3.reset();
     PdfTemplate n4 = sap.getLayer(4);
       n4.reset(true);
       n4.setBoundingBox(new Rectangle(10, 10, 20, 20));
       logger.debug("Here 1");
   //  BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, false);
   //  n4.setFontAndSize(bf, (float) 100);
   //   n4.setColorFill(BaseColor.WHITE);
       
        PdfSignature dic = new PdfSignature(PdfName.ADOBE_PPKLITE, new PdfName("adbe.pkcs7.detached"));
           //dic.setReason(sap.getReason()); 
           //dic.setLocation(sap.getLocation());
            dic.setContact(sap.getContact());
            dic.setDate(new PdfDate(sap.getSignDate())); 
            sap.setCryptoDictionary(dic);
       
               int contentEstimated = 15000;
		HashMap<PdfName, Integer> exc = new HashMap<PdfName,Integer>();
		exc.put(PdfName.CONTENTS, new Integer(contentEstimated * 2 + 2));
		sap.preClose(exc);
                
                PdfPKCS7 sgn = new PdfPKCS7(priv, chain, null, "SHA1", null, false);
		InputStream data = sap.getRangeStream();
		MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
		 logger.debug("Here 2");   
		byte buf[] = new byte[8192];
		int n;
		while ((n = data.read(buf)) > 0) {
			messageDigest.update(buf, 0, n);
		}
		byte hash[] = messageDigest.digest();
		cal = Calendar.getInstance();
			
		byte sh[] = sgn.getAuthenticatedAttributeBytes(hash, cal, null);
		sgn.update(sh, 0, sh.length);
                
                byte[] encodedSig = sgn.getEncodedPKCS7(hash, cal, null, null);
                logger.debug("Here 3");
		if (contentEstimated + 2 < encodedSig.length)
			throw new Exception();

		byte[] paddedSig = new byte[contentEstimated];
		System.arraycopy(encodedSig, 0, paddedSig, 0, encodedSig.length);

		PdfDictionary dic2 = new PdfDictionary();
		dic2.put(PdfName.CONTENTS, new PdfString(paddedSig).setHexWriting(true));
                sap.close(dic2);
                logger.debug("Done Signing");
       
    }*/
    
    
    public boolean sign(String documentPath, String signedDocumentPath,
			String certLabel, int x1, int y1, int x2, int y2) throws Exception {
		
    	
    	
    	
		hsmConnectionLogin();
		
	//	System.out.println(" inside sign " + certLabel);
		logger.debug(" inside sign " + certLabel);
		PrivateKey priv = getPrivateKeyLabel(certLabel);
		
	//	System.out.println(" getting certificate chain ");	
		logger.debug(" getting certificate chain ");
		Certificate[] chain = getcertificatechain(certLabel);

	//	System.out.println(" --CHAIN-- " + chain.length);
		logger.debug(" --CHAIN-- " + chain.length);
		PdfReader reader = new PdfReader(documentPath);
		
	//	System.out.println(" Reading document from path: " + documentPath);
		logger.debug(" Reading document from path: " + documentPath);
		File signed = new File(signedDocumentPath);
		
		//System.out.println(" Document to sign - "+documentPath);
		logger.debug(" Document to sign - "+documentPath);
		FileOutputStream fout = new FileOutputStream(signed);
		PdfStamper stamper = PdfStamper.createSignature(reader, fout, '\000', null, true);		
		//System.out.println("create signature");
		logger.debug("create signature");
		PdfSignatureAppearance sap = stamper.getSignatureAppearance();
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		sap.setSignDate(cal);
		int m = stamper.getReader().getNumberOfPages();
	//	System.out.println(" Document to sign contains ["+m+"] pages");
		logger.debug(" Document to sign contains ["+m+"] pages");
		sap.setVisibleSignature(new Rectangle(x1, y1, x2, y2), 1, "signature");
		sap.setCertificationLevel(0);			
		sap.setCertificate(chain[0]);
		sap.setAcro6Layers(false);
		PdfTemplate n1 = sap.getLayer(1);
		n1.reset();
		PdfTemplate n3 = sap.getLayer(3);
		n3.reset();
		PdfTemplate n4 = sap.getLayer(4);
		n4.reset(true);
		n4.setBoundingBox(new Rectangle(10.0F, 10.0F, 20.0F, 20.0F));
	//	System.out.println("(1) Digest ");
		PdfSignature dic = new PdfSignature(PdfName.ADOBE_PPKLITE, new PdfName("adbe.pkcs7.detached"));
		////-added below
		dic.setCert(chain[0].getEncoded());
		dic.setContact(sap.getContact());
		dic.setDate(new PdfDate(sap.getSignDate()));
		sap.setCryptoDictionary(dic);
		int contentEstimated = 15000;
		HashMap exc = new HashMap();
		exc.put(PdfName.CONTENTS, new Integer(contentEstimated * 2 + 2));		
		sap.preClose(exc);
	//	System.out.println(" Signature appearance preclose. ");
		logger.debug(" Signature appearance preclose. ");
		PdfPKCS7 sgn = new PdfPKCS7(priv, chain, "SHA1", null,null, false);
		InputStream data = sap.getRangeStream();
		//System.out.println(" Getting range stream. ");
		logger.debug(" Getting range stream. ");
		MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
	//	System.out.println("(2) Digest ");
		byte[] buf = new byte[8192];
		int n;
		while ((n = data.read(buf)) > 0) {
			messageDigest.update(buf, 0, n);
		}
		byte[] hash = messageDigest.digest();
	
		cal = Calendar.getInstance();
		//System.out.println(" Setting Authenticated Attributes ");
		logger.debug(" Setting Authenticated Attributes ");
		byte[] sh = sgn.getAuthenticatedAttributeBytes(hash, cal, null, null,null);
		logger.debug(" Setting Authenticated Attributes " + sh);
		sgn.update(sh, 0, sh.length);
		//System.out.println(" PdfPKCS update ");
		byte[] encodedSig = sgn.getEncodedPKCS7(hash, cal);
	//	System.out.println("(3) get encoded PKCS7 ");
		logger.debug("(3) get encoded PKCS7 ");
		if (contentEstimated + 2 < encodedSig.length) {
			throw new Exception();
		}			
		byte[] paddedSig = new byte[contentEstimated];
		System.arraycopy(encodedSig, 0, paddedSig, 0, encodedSig.length);
		PdfDictionary dic2 = new PdfDictionary();
		dic2.put(PdfName.CONTENTS, new PdfString(paddedSig).setHexWriting(true));
		sap.close(dic2);
	//	System.out.println("(4) signing done on "+signedDocumentPath);
		logger.debug("(4) signing done on "+signedDocumentPath);

		return true;
	}

	private PrivateKey getPrivateKeyLabel(String certLabel)
			throws Exception {
		LunaKey privkey =null;
		if (Security.getProvider("LunaProvider") == null) {
			Security.insertProviderAt(new LunaProvider(), 2);
		}
		if (!hsmConnection.isLoggedIn()) {
			hsmConnectionLogin();
		}
		try{
			
		
		KeyStore ks = KeyStore.getInstance("Luna");
		ks.load(null, null);
		RSAPublicKey pub = (RSAPublicKey) ks.getCertificate(certLabel)
				.getPublicKey();
		BigInteger modulus = pub.getModulus();
		System.out.println("Modulus is  : "+modulus.toString(16));
		int flag = 1;
		String keyStoreObj = "";
		 privkey = (LunaKey) ks.getKey(certLabel, null);
		System.out.println("Got");
	}
	catch (Exception e) {
		e.printStackTrace();
	}
logger.debug("Succesfull Private Key" +privkey);
System.out.println("Succesfull Private Key" +privkey);
		return (PrivateKey) privkey;
	}

	private void refreshHsmConnection() throws Exception {
		try {
			resolveLunaSlotManagerInstance();
			hsmConnectionLogin();
		} catch (Throwable t) {
			throw new Exception(
					"Unable to login to the Hardware Storage Module (HSM). E-signing can't be completed without access to a certificate",
					t);
		}
	}

	private void hsmConnectionLogin() {
		System.out.println("Login to HSM Server ...");
		if (!hsmConnection.isLoggedIn()) {
			hsmConnection.login(PartitionName, PartitionPassword);
			System.out.println("Logged in successful...");
			return;
		}
		System.out.println("Login failed in HSM");
	}

	private void resolveLunaSlotManagerInstance() throws Exception {
		if (hsmConnection == null) {
			if (hsmConnection.isTokenPresent(hsmConnection
					.findSlotFromLabel(PartitionName))) {
				hsmConnection = LunaSlotManager.getInstance();
				hsmConnection.reinitialize();
			}
		} else {
			throw new Exception("LunaSlotManager did not return an instance.");
		}
	}

	private Certificate[] getcertificatechain(String certLabel)
			throws Exception {
		try {
			if (Security.getProvider("LunaProvider") == null) {
				Security.insertProviderAt(new LunaProvider(), 2);
			}
			if (!hsmConnection.isLoggedIn()) {
				refreshHsmConnection();
			} 
			KeyStore ks = KeyStore.getInstance("Luna");
			ks.load(null, null);

			Certificate[] chain = new Certificate[1];
			chain[0] = ks.getCertificate(certLabel);

			return chain;
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(
					"Unable to retrieve resources from the Hardware Storage Module (HSM). E-signing can't be completed without a private key and certificate chain",
					t);
		}
	}

	private static void createPdf(String filename) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();
		document.add(new Paragraph("Hello World!"));
		document.close();
	}
    
   
/*private static PrivateKey getPrivateKeyLabel(String certLabel ) throws Exception {
    
    logger.debug("Inside getting private key ");
    if (Security.getProvider("LunaProvider")==null) {
        Security.insertProviderAt(new com.safenetinc.luna.provider.LunaProvider(),2);
    }
  
    if(!hsmConnection.isLoggedIn()){
    	refreshHsmConnection();
    	logger.debug("Initating connection with HSM");
   // hsmConnectionLogin();
    }
    KeyStore ks = KeyStore.getInstance("Luna");
    ks.load(null, null);
    logger.debug("Got the KeyStore");
    logger.debug("Getting the public key using subject name");
    RSAPublicKey pub = (RSAPublicKey) ks.getCertificate(certLabel).getPublicKey();
    BigInteger modulus = pub.getModulus();
System.out.println(modulus.toString(16));
    int flag = 1;
    String keyStoreObj=  "";
LunaKey privkey = (LunaKey) ks.getKey(certLabel, null);
logger.debug("Got the Key");
    return (PrivateKey) privkey ;
    
}

private static void refreshHsmConnection() throws Exception {
try {
    resolveLunaSlotManagerInstance();
    hsmConnectionLogin();
} catch (Throwable t) {
     throw new Exception("Unable to login to the Hardware Storage Module (HSM). E-signing can't be completed without access to a certificate", t);
}
}

private static void hsmConnectionLogin() {
 if (!hsmConnection.isLoggedIn()) {
	 logger.debug("Loggin in with partion name" +PartitionName +"and Password "+PartitionPassword);
       boolean check =  hsmConnection.login(PartitionName, PartitionPassword);
       
       if(check)
       {
    	logger.debug("Logged in Succesfully to HSM")   ;
       }
       else
       {
    	   logger.debug("Not Logged  Succesfully to HSM")   ;
       }
    }

}

private static void resolveLunaSlotManagerInstance() throws Exception {
if(hsmConnection==null){
	
	logger.debug("Connection was null");
if (hsmConnection.isTokenPresent(hsmConnection.findSlotFromLabel(PartitionName))) {
	logger.debug("Inside getting connection");
    hsmConnection = LunaSlotManager.getInstance();
    hsmConnection.reinitialize();
logger.debug("Reinitialized Properly");
}
}else{
	logger.debug("Within else loop");
	  hsmConnection = LunaSlotManager.getInstance();
	    hsmConnection.reinitialize();
	logger.debug("LunaSlotManager did not return an instance.");
}
}

private static Certificate[] getcertificatechain(String certLabel) throws Exception {
try {
	logger.debug("Insert get Cetificate Chain");
    if (Security.getProvider("LunaProvider")==null) {
    	logger.debug("Insert setting provider");
        Security.insertProviderAt(new com.safenetinc.luna.provider.LunaProvider(),2);
        logger.debug("Done setting provider");
    }
      if(!hsmConnection.isLoggedIn()){
    refreshHsmConnection();
      }
    KeyStore ks = KeyStore.getInstance("Luna");
    ks.load(null, null);
 
    Certificate[] chain = new Certificate[1];
    chain[0] = ks.getCertificate(certLabel);
//chain[1] = ks.getCertificate("e-Mudhra Sub CA for Class 2 Organisation 2014");
//          chain[2] = ks.getCertificate("e-Mudhra CA 2014");
//        chain[3] = ks.getCertificate("CCA India 2014");            



//hsmConnection.logout();
    return chain;
} catch (Throwable t) {
    t.printStackTrace();
   throw new Exception("Unable to retrieve resources from the Hardware Storage Module (HSM). E-signing can't be completed without a private key and certificate chain", t);
}
}

private static void createPdf(String filename)
throws Exception {
// step 1
Document document = new Document();
// step 2
PdfWriter.getInstance(document, new FileOutputStream(filename));
// step 3
document.open();
// step 4
document.add(new Paragraph("Hello World!"));
// step 5
document.close();
}*/

	public static void main(String[] args) {
		
	}
	
}
