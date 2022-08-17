package com.wipro.igrs.payment.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.tcs.sgv.encdec.MPCTPAESEncDec;

import weblogic.utils.encoders.BASE64Decoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Provider;
import java.security.Security;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bridj.cpp.com.CY;


public class AESEncrypt
{
  private String secretKey;
  
  
  public AESEncrypt()
  {
    this.secretKey = null;
  }
  
  
  
  public String decryptFile(String s)
  {
    String decdata = null;
   
    
    try
    {
    	 decdata = MPCTPAESEncDec.DecryptData(s, this.secretKey);
		System.out.println("Decrypted Data :: " + decdata);
    	
    }
    catch (Exception ex)
    {
ex.printStackTrace();
    }
    return decdata;
  }
  
  public String encryptFile(String s)
  {
	 String encData = null;
	  try
	  {
	
	
	  System.out.println("Original Data :: " + s);
		 encData = MPCTPAESEncDec.EncryptData(s,this.secretKey);
		System.out.println("Encrypted Data :: " + encData);

   
	  }
    catch (Exception ex)
    {
      System.out.println("Exception occured :" + ex);
    }
    return encData;
  }
  
  byte[] returnbyte(String path)
    throws IOException
  {
    FileInputStream fileinputstream = new FileInputStream(path);
    byte[] abyte = new byte[fileinputstream.available()];
    fileinputstream.read(abyte);
    fileinputstream.close();
    return abyte;
  }
  
  public void setSecretKey(String s)
  {
    this.secretKey = s;
  }
  
  String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
  

  public static void main(String[] args) {
	  
	  File fs = new File("D://Data1//lol");
	  fs.mkdirs();
	String s = "";
	  AESEncrypt crypt = new AESEncrypt();
	try {
	s = 	crypt.readFile("D://RCT_DATA_050320140926epran.txt");
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	crypt.setSecretKey("C://Users//SA334510//Documents//shared1//treasury//IGRS_KEY.key");
	//crypt.setSecretKey("D://MPT_CYBER.key");
	System.out.println(crypt.decryptFile(s));
	String l = crypt.decryptFile("F/0ecqalOlcsI/H3WTp4t07j5BIPTjZ1i4jDbbUs3qS7r7tcKwdn+tNayltFDgxc6B2drTESz71mQIccIaJBlqH/if+5QjGW644h9Pj+OHlY0QcjCUe8KslYWsfkVVvVmLuRulDFeUjwHdfvcDWFDFY+jEuW+Im3PrUaoqa7VOJhdrZBfQTteZ5wgkdegS/gAp+xgDSwSjdwKyaabjHrGMskEGbXscYuKMZW8Z1LHJ8aZ470WdiU/ZjyUTPE0pjyd4YAjfrr9SM1wYj70w+JLSiduEo4FVQbDxLJjFC8VfngNsE/6cD+5BY5IVF98UBX3V4axUrmAv3L3jI2sjWlzQ==");
	String po="yjSXBSYb1BCw1EtcLBUZGznb4QJTzbMFmqkM%2FFcBPlksCyeYr2JOnJ1Vp66wf9Y8J0jkmgZ70uTl%0D%0An9QgDT0VQ%2FEAhryzKaqao%2Bzdag3uy21JPESpYT8VrpLobM%2FlwCN3hguZalN3iF8aj3MtNTRgHa1q%0D%0Al%2B9w2PQEjyfVNjJjELEWtjdDS6hUpjWzveZXM8K1C8xv6Zw9Q7yy46zJY3NqB1DzAJg58HChMPGs%0D%0AJklsmn%2Fc6QPwtejlfMEI3IGQzrlpeMdYB71o5BL2H5FnrnJHKv4L4dRI24VPfnQi9It8H%2FcdG7fe%0D%0AODcAhSNMYANbowNJDWDJkA4c7ygyI25A3jz2NQ%3D%3D";
	po = URLDecoder.decode(po);
	//System.out.println(po);
	String g = crypt.decryptFile(po);
	//System.out.println(g);
	String ls= "F/0ecqalOlcsI/H3WTp4t07j5BIPTjZ1i4jDbbUs3qS7r7tcKwdn+tNayltFDgxc6B2drTESz71mQIccIaJBlqH/if+5QjGW644h9Pj+OHlY0QcjCUe8KslYWsfkVVvVmLuRulDFeUjwHdfvcDWFDFY+jEuW+Im3PrUaoqa7VOJhdrZBfQTteZ5wgkdegS/gAp+xgDSwSjdwKyaabjHrGMskEGbXscYuKMZW8Z1LHJ8aZ470WdiU/ZjyUTPE0pjyd4YAjfrr9SM1wYj70w+JLSiduEo4FVQbDxLJjFC8VfngNsE/6cD+5BY5IVF98UBX3V4axUrmAv3L3jI2sjWlzQ==";
	//System.out.println(crypt.encryptFile("crn=IGR004006271651201|amount=50|challan_number=00302|challan_date=24092014|cin=BARB0BANEAS00302409201400002|brn=106301|scroll_number=2409201401|scroll_date=24092014|transaction_date_time=24/09/2014 12:46:16|checkSum=922d489c951d8a41b752764c20677978"));
	String ency = crypt.encryptFile("crn=IGR011014012540005|amount=50|challan_number=00302|challan_date=24092014|cin=BARB0BANEAS00302409201400002|brn=106301|scroll_number=2409201401|scroll_date=24092014|transaction_date_time=24/09/2014 12:46:16|checkSum=922d489c951d8a41b752764c20677978");
	String e = crypt.decryptFile(ency);
	String f = URLEncoder.encode(e);
	
	System.out.println(crypt.decryptFile(URLDecoder.decode("yjSXBSYb1BCw1EtcLBUZGznb4QJTzbMFmqkM%2FFcBPlksCyeYr2JOnJ1Vp66wf9Y8J0jkmgZ70uTl%0D%0An9QgDT0VQ%2FEAhryzKaqao%2Bzdag3uy21JPESpYT8VrpLobM%2FlwCN3hguZalN3iF8aj3MtNTRgHa1q%0D%0Al%2B9w2PQEjyfVNjJjELEWtjdDS6hUpjWzveZXM8K1C8xv6Zw9Q7yy46zJY3NqB1DzAJg58HChMPGs%0D%0AJklsmn%2Fc6QPwtejlfMEI3IGQzrlpeMdYB71o5BL2H5FnrnJHKv4L4dRI24VPfnQi9It8H%2FcdG7fe%0D%0AODcAhSNMYANbowNJDWDJkA4c7ygyI25A3jz2NQ%3D%3D")));
	MD5Check check = new MD5Check();
	//System.out.println(check.md5Java("crn=IGR004006171451201|amount=50|challan_number=00302|challan_date=24092014|cin=BARB0BANEAS00302409201400002|brn=106301|scroll_number=2409201401|scroll_date=24092014|transaction_date_time=24/09/2014 12:46:16|"));
	//System.out.println(ency);
  }
  
}
