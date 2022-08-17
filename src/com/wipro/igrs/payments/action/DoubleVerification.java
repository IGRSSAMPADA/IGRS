package com.wipro.igrs.payments.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tcs.sgv.encdec.MPCTPAESEncDec;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.payment.dto.OnlinePaymentDto;
import com.wipro.igrs.payment.util.MD5Check;
import com.wipro.igrs.payments.util.PropertiesFileReader;

public class DoubleVerification extends BaseAction{
	
	Logger logger = (Logger) Logger.getLogger(PaymentAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
		String secretKey=proper.getValue("mp_treasury_key_ifmis");
		String DV_URL=proper.getValue("mp_treasury_ifmis_dv_url");
		String forwardTo="";
		//logger.debug(
		String urn =request.getParameter("urn");//"MPTIGR070320205386424"
		boolean validateURN=URNStatus(urn);
		if(validateURN){
		String Ru=request.getRequestURL().toString();
		String furl=Ru.substring(0,Ru.indexOf("IGRS/"));
		furl=furl+"IGRS/";
		furl=furl+"ifmispymtrecpt.do";
		logger.debug("furl from code for double verification-----"+furl);
		//Added by Ajit for production https issue
		 if(furl.contains("mpigr")){
       	  furl="https://www.mpigr.gov.in:8080/IGRS/ifmispymtrecpt.do";
       	  logger.debug("Hardcoded url for production on load balancer---"+furl);
       	  }

		logger.debug("urn from user---"+urn);
		String []a = new String[]{"urn","ru"};
		String []b = new String[]{urn,furl};//MPTIGR070320205386424   MPTIGR290220205386417  "http://localhost:8080/IGRS/ifmispymtrecpt.do"
		String urls = 	createUrl(a, b, "");
		logger.debug("Created url--"+urls);
		String encoded =  encryptFile(urls); 
		logger.debug("encoded---"+encoded);
        session.setAttribute("DV_URL",DV_URL);
		session.setAttribute("encdata",encoded);  
		logger.debug("Within POST method");
		forwardTo="ifmis";
		}else{
			logger.debug("Invalid URN entered by user!!"+urn);
			forwardTo="error";
		}
		return mapping.findForward(forwardTo);

	}
	
	public String createUrl(String params[], String values[], String key) {
		String url = "";
		logger.debug("Parameter val:---"+params.length);
		logger.debug("Value of aparamepert:-----"+values.length);
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

	public String encryptFile(String s)
	{
		String encData = null;
		try
		{
			PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
			String secretKey=proper.getValue("mp_treasury_key_ifmis");
			//String AESKeyFilePath="d://shared1//treasury//KEY_IGRS.key";
			logger.debug("Original Data :: " + s +"--secretKey--"+secretKey);
			encData = MPCTPAESEncDec.EncryptData(s,secretKey);
			logger.debug("Encrypted Data :: " + encData);

			String decData = MPCTPAESEncDec.DecryptData(encData, secretKey);
			logger.debug("Decrypted Data :: " + decData);

		}
		catch (Exception ex)
		{
			logger.debug("Exception occured :" + ex);
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
			logger.debug("Decrypted Data in :: " + decData);

		}
		catch (Exception ex)
		{
			logger.debug("Exception occured :" + ex);
		}
		return decData;
	}
	
	
	public  boolean URNStatus( String urn) throws Exception{
  		Connection conn=null;
  		DBUtility dbUtil = null;
  		String result="";
  		boolean out=false;
		   dbUtil = new DBUtility();
		String   sql="select STATUS_FLAG from IGRS_PAYMENT_ECHALLAN_DETAILS where  URN=?";;  
		try{
			 conn=dbUtil.getDBConnection();
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, urn);
		ResultSet rset=pst.executeQuery();
		while(rset.next())
		{     result=rset.getString(1);
		      logger.debug("Given URN exists in our system---"+urn);
		      out=true;
			
		}
		}finally{
			if(conn!=null)conn.close();
		}
		return out;
  		
  	}

/*	public static void main(String args[]){
		String s="mYS2EA6eKBMWNNZnT5AXVlOwX9xrqo0NGVxJEUbT6VbnEz7EpTu8QM/8CxTuYXQeClKPBPF0xb3S"+
		"6YgTRqgKUsKN3KuKOABQJ/446p7WHLjvtiXU39J+GjmIF6nvg+wTAmtq+6t9gID7PHdSq/om94J6"+
		"9SQnZrlpT2Is+icnRf8UC7Me58vs9N9NmkBepDSD2W4qVf+E1TWz+se7um8rayLs5w46YnF01WN/"+
		"CC7qElqVTDgn/WCffiCK/yiIxabXli66iS4q7ilvBJwPSdECfFzAvc8rS1pcuESG0OKMajJPgVva"+
		"X6OlUuov+KhdiBzCa8iATfAAAwxWs3+6iofPTMXRolvT365ymn7fW7nNa4uz52YWQ3MuSKRBLFC3"+
		"rANQ";
		DoubleVerificationServlet obj=new DoubleVerificationServlet();
		String out=obj.decryptFile(s);
		logger.debug("out---"+out);
		
	}*/

}
