package com.wipro.igrs.payments.action;

import com.tcs.sgv.encdec.MPCTPAESEncDec;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;

public class ManualDoubleVerification {
	public static void main(String args[]) throws Exception{

		PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
		String secretKey=proper.getValue("mp_treasury_key_ifmis");
		//String DV_URL="";
		String forwardTo="";
		//logger.debug(
		String urn ="MPTIGR121120210001802";//"MPTIGR070320205386424"
		boolean validateURN=URNStatus(urn);
		if(validateURN){
		//String Ru=request.getRequestURL().toString();
		/*String furl=Ru.substring(0,Ru.indexOf("IGRS/"));
		furl=furl+"IGRS/";
		furl=furl+"ifmispymtrecpt.do";*/
		//logger.debug("urn from user---"+urn);
		String []a = new String[]{"urn","ru"};
		String []b = new String[]{urn,"aaaa"};//MPTIGR070320205386424   MPTIGR290220205386417  "http://localhost:8080/IGRS/ifmispymtrecpt.do"
		String urls = 	createUrl(a, b, "");
		//logger.debug("Created url--"+urls);
		String encoded =  encryptFile(urls); 
		System.out.println(encoded);
		//logger.debug("encoded---"+encoded);
     // session.setAttribute("DV_URL",DV_URL);
		//session.setAttribute("encdata",encoded);  
		//logger.debug("Within POST method");
		forwardTo="ifmis";
		}else{
			System.out.println("Invalid URN entered by user!!");
			forwardTo="error";
		}
		

	
	}
	public static String createUrl(String params[], String values[], String key) {
		String url = "";
		System.out.println("Parameter val:---"+params.length);
		System.out.println("Value of aparamepert:-----"+values.length);
		if (params.length != values.length) {
			return null;
		} else {

			for (int i = 0; i < params.length; i++) {
				if (i != params.length - 1) {
					url = url + params[i] + "=" + values[i] + "|";

				} else {
					url = url + params[i] + "=" + values[i];
				}

			}

		}
/*		MD5Check check = new MD5Check();
//		url = url + "|checkSum=" + check.md5Java(url);

		try {


		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return url;
	}

	public static String encryptFile(String s)
	{
		String encData = null;
		try
		{
			PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
			String secretKey=proper.getValue("mp_treasury_key_ifmis");
			//String AESKeyFilePath="d://shared1//treasury//KEY_IGRS.key";
		//	logger.debug("Original Data :: " + s +"--secretKey--"+secretKey);
			encData = MPCTPAESEncDec.EncryptData(s,secretKey);
			System.out.println("Encrypted data --> "+encData );
			//logger.debug("Encrypted Data :: " + encData);

			String decData = MPCTPAESEncDec.DecryptData(encData, secretKey);
			System.out.println("Decrypted data --> "+decData );
		//	logger.debug("Decrypted Data :: " + decData);

		}
		catch (Exception ex)
		{
			//logger.debug("Exception occured :" + ex);
			ex.printStackTrace();
		}
		return encData;
	}

	public String decryptFile(String s)
	{
		String decData = null;
		String sanjeevDec=null;
		try
		{
			PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
			String secretKey=proper.getValue("mp_treasury_key_ifmis");
			decData = MPCTPAESEncDec.DecryptData(s, secretKey);
			//logger.debug("Decrypted Data in :: " + decData);

		}
		catch (Exception ex)
		{
			//logger.debug("Exception occured :" + ex);
		}
		return decData;
	}
	
	
	public static  boolean URNStatus( String urn) throws Exception{
  		//Connection conn=null;
  		DBUtility dbUtil = null;
  		String result="";
  		boolean out=false;
		   dbUtil = new DBUtility();
		String   sql="select STATUS_FLAG from IGRSPILOT.IGRS_PAYMENT_ECHALLAN_DETAILS where  URN=?";;  
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String arr[] = {urn};
			String ss = dbUtil.executeQry(arr);
			if(ss.equals("A")){
				out=true;
			}
		}finally{
			dbUtil.closeConnection();
		}
		return out;
  		
  	}
}
