package com.wipro.igrs.SMSAlerts.action;

import com.wipro.igrs.SMSAlerts.dao.UserDetailsDAO;
import com.wipro.igrs.SMSAlerts.dto.SmsDetails;
import com.wipro.igrs.SMSAlerts.form.SMSAlertsForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SendSMSAlertsAction extends BaseAction implements Runnable{
	
  private static final Logger log = Logger.getLogger(SendSMSAlertsAction.class);
  
  private static final SimpleDateFormat dateFormatter = 
	  new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss.S a zzz");
  
  private static final String senderid = "CTDDRS";
  
  private static final String username = "DITMP-CTDDRS";
  
  private static final String password = "qazxswedc123#";
  private static String smsservicetype = "";
  
  private String threadNum;
  //private SmsDetails smsDetail;
  SmsDetails smsDetail = new SmsDetails();
  private HttpURLConnection httpConnection;
  private static int num;
  
  public SendSMSAlertsAction(SmsDetails smsDetail) {
    	this.smsDetail = smsDetail;
		this.threadNum = "Thread-"+(++num)+" SmsTxnId : "+smsDetail.getSmsTxnId();
  }
  
  public SendSMSAlertsAction() {
		// TODO Auto-generated constructor stub
	}
	
  public void sendSms()
  {
    
    try
    {
     SMSAlertsForm form=smsDetail.getSmsAlertsForm();
     String userId = smsDetail.getUserId();
	 String output = "";
     String mobileNos = "";
     String smsContent = "";
      URL url = new URL("http://164.100.129.141/esms/sendsmsrequest");
      
      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.125.243.67", 8080));

      httpConnection = ((HttpURLConnection)url.openConnection(proxy));
      httpConnection.setDoInput(true);
      httpConnection.setDoOutput(true);
      httpConnection.setRequestMethod("POST");
      httpConnection.setConnectTimeout(20000);
      HttpURLConnection.setFollowRedirects(true);
      
      if(form.getActionName().equalsIgnoreCase("sendSMStoMobile") || form.getActionName().equalsIgnoreCase("sendSMStoGroup"))
      {
		  if(form.getUserMobileNumber().contains(","))
			  output = sendBulkSMS(username, password, senderid, form.getUserMobileNumber(), form.getSmsHindiContent());
		  else
			  output = sendSingleSMS(username, password, senderid, form.getUserMobileNumber(), form.getSmsHindiContent());
		  
		  //System.out.println("Mobile Numbers" + form.getUserMobileNumber());
	      UserDetailsDAO.updateSmsDetail(form.getUserMobileNumber(), form.getSmsHindiContent());
      }
      else{
	      if (form.getUserTypes().equalsIgnoreCase("S")) {
	        List<SmsDetails> allSMSStateData = UserDetailsDAO.getStateUsersList(form, userId);
	        for (int i = 0; i < allSMSStateData.size(); i++) {
	          SmsDetails sms = (SmsDetails)allSMSStateData.get(i);
	          String mob = new StringBuilder(String.valueOf(sms.getSmsMobileNumber())).append(",").toString();
	          mobileNos = mobileNos + mob;
	          smsContent = sms.getSmsMessasgeContent();
	        }
	        mobileNos = mobileNos.substring(0, mobileNos.length() - 1);
	      }
	      else if (form.getUserTypes().equalsIgnoreCase("Z")) {
	        List<SmsDetails> allSMSZoneData = UserDetailsDAO.getZoneUsersList(form, userId);
	        for (int i = 0; i < allSMSZoneData.size(); i++) {
	          SmsDetails sms = (SmsDetails)allSMSZoneData.get(i);
	          String mob = new StringBuilder(String.valueOf(sms.getSmsMobileNumber())).append(",").toString();
	          mobileNos = mobileNos + mob;
	          smsContent = sms.getSmsMessasgeContent();
	        }
	        mobileNos = mobileNos.substring(0, mobileNos.length() - 1);
	      }
	      else if (form.getUserTypes().equalsIgnoreCase("D")) {
	        List<SmsDetails> allSMSDistData = UserDetailsDAO.getDistUsersList(form, userId);
	        for (int i = 0; i < allSMSDistData.size(); i++) {
	          SmsDetails sms = (SmsDetails)allSMSDistData.get(i);
	          String mob = new StringBuilder(String.valueOf(sms.getSmsMobileNumber())).append(",").toString();
	          mobileNos = mobileNos + mob;
	          smsContent = sms.getSmsMessasgeContent();
	        }
	        mobileNos = mobileNos.substring(0, mobileNos.length() - 1);
	      }
	      else if (form.getUserTypes().equalsIgnoreCase("SP")) {
	        List<SmsDetails> allSMSSPData = UserDetailsDAO.getSPUsersList(form, userId);
	        for (int i = 0; i < allSMSSPData.size(); i++) {
	          SmsDetails sms = (SmsDetails)allSMSSPData.get(i);
	          String mob = new StringBuilder(String.valueOf(sms.getSmsMobileNumber())).append(",").toString();
	          mobileNos = mobileNos +mob;
	          smsContent = sms.getSmsMessasgeContent();
	        }
	        mobileNos = mobileNos.substring(0, mobileNos.length() - 1);
	      }
      
      output = sendBulkUnicodeSMS("DITMP-CTDDRS", "qazxswedc123#", "CTDDRS", mobileNos, smsContent);
      System.out.println("Mobile Numbers" + mobileNos);
      UserDetailsDAO.updateSmsDetail(mobileNos, smsContent);
      }
      System.out.println("output" + output);
      if ((output.contains("402,1,0")) && (UserDetailsDAO.updateUserPinDetail(smsDetail.getSmsTxnId()))) {
        String outData = "\n@@ " + threadNum + "\nCurrent Date: " + dateFormatter.format(new Date()) + 
          "\nMessage : " + smsDetail.getSmsMessasgeContent() + "\nResp from service : " + output + 
          "Resp Message: " + httpConnection.getResponseMessage() + "\n@@ " + threadNum;
        log.debug(outData);
      } else {
        log.debug("\nSms not send for - " + threadNum + " Resp from service : " + output);
      }
    }
    catch (Exception e) {
      log.debug(e.getMessage(), e);
    }
  }
  
  public String sendSingleSMS(String username, String password, String senderId, String mobileNo, String message) {
		StringBuilder outputString = new StringBuilder();
		try {
			System.out.println(message);
		      String finalmessage = "";
		      String sss = "";
		      char ch = '\000';
		      for (int i = 0; i < message.length(); i++) {
		        ch = message.charAt(i);
		        int j = ch;
		        sss = "&#" + j + ";";
		        finalmessage = finalmessage + sss;
		      }
		      System.out.println("ddd" + finalmessage);
		      message = finalmessage;
		      System.out.println("unicoded message==" + message);
			
			smsservicetype="unicodemsg";
			String query = (new StringBuilder("username=")).append(URLEncoder.encode(username)).append("&password=").append(
					URLEncoder.encode(password)).append("&smsservicetype=").append(URLEncoder.encode(smsservicetype)).append(
							"&content=").append(URLEncoder.encode(message)).append("&mobileno=").append(URLEncoder.encode(mobileNo, "UTF-8"))
					.append("&senderid=").append(URLEncoder.encode(senderId)).toString();
			
			httpConnection.setRequestProperty("Content-length", String.valueOf(query.length()));
			httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");
			
			DataOutputStream output = new DataOutputStream(httpConnection.getOutputStream());
			output.writeBytes(query);
			output.close();
			DataInputStream input = new DataInputStream(httpConnection.getInputStream());
			for (int c = input.read(); c != -1; c = input.read()) 
				outputString.append((char) c);
			
			input.close();
		} catch (Exception e) {
			outputString.append("Error occurred - "+e.getMessage());
			log.debug(e.getMessage(), e);
		}
		return outputString.toString();
	}
	
	public String sendBulkSMS(String username, String password, String senderId, String mobileNo, String message) {
		StringBuilder outputString = new StringBuilder();
		try {
			System.out.println(message);
		      String finalmessage = "";
		      String sss = "";
		      char ch = '\000';
		      for (int i = 0; i < message.length(); i++) {
		        ch = message.charAt(i);
		        int j = ch;
		        sss = "&#" + j + ";";
		        finalmessage = finalmessage + sss;
		      }
		      System.out.println("ddd" + finalmessage);
		      message = finalmessage;
		      System.out.println("unicoded message==" + message);
			
			
			log.debug("Started CDAC service.... ");
			long aa = System.currentTimeMillis();
			smsservicetype="unicodemsg";
			String query = (new StringBuilder("username=")).append(URLEncoder.encode(username)).append("&password=").append(
					URLEncoder.encode(password)).append("&smsservicetype=").append(URLEncoder.encode(smsservicetype)).append(
							"&content=").append(URLEncoder.encode(message)).append("&bulkmobno=").append(URLEncoder.encode(mobileNo, "UTF-8"))
					.append("&senderid=").append(URLEncoder.encode(senderId)).toString();
			
			httpConnection.setRequestProperty("Content-length", String.valueOf(query.length()));
			httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");
			
			DataOutputStream output = new DataOutputStream(httpConnection.getOutputStream());
			output.writeBytes(query);
			output.close();
			DataInputStream input = new DataInputStream(httpConnection.getInputStream());
			for (int c = input.read(); c != -1; c = input.read()) 
				outputString.append((char) c);
			
			input.close();
			log.debug("Completed CDAC webservice in " + (System.currentTimeMillis() - aa) + " Milli Second.");
			
		} catch (Exception e) {
			outputString.append("Error occurred - "+e.getMessage());
			log.debug(e.getMessage(), e);
		}
		return outputString.toString();
	}
  
  
  
  public String sendBulkUnicodeSMS(String username, String password, String senderId, String mobileNos, String message)
  {
    StringBuilder outputString = new StringBuilder();
    try
    {
      System.out.println(message);
      String finalmessage = "";
      String sss = "";
      char ch = '\000';
      for (int i = 0; i < message.length(); i++) {
        ch = message.charAt(i);
        int j = ch;
        sss = "&#" + j + ";";
        finalmessage = finalmessage + sss;
      }
      System.out.println("ddd" + finalmessage);
      message = finalmessage;
      System.out.println("unicoded message==" + message);
      String smsservicetype = "unicodemsg";
      
      String query = "username=" + URLEncoder.encode(username) + "&password=" + 
        URLEncoder.encode(password) + "&smsservicetype=" + URLEncoder.encode(smsservicetype) + 
        "&content=" + URLEncoder.encode(message) + "&bulkmobno=" + URLEncoder.encode(mobileNos, "UTF-8") + 
        "&senderid=" + URLEncoder.encode(senderId);
      
      httpConnection.setRequestProperty("Content-length", String.valueOf(query.length()));
      httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      httpConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");
      
      DataOutputStream output = new DataOutputStream(httpConnection.getOutputStream());
      output.writeBytes(query);
      output.close();
      DataInputStream input = new DataInputStream(httpConnection.getInputStream());
      for (int c = input.read(); c != -1; c = input.read()) {
        outputString.append((char)c);
      }
      input.close();
    }
    catch (Exception e)
    {
      outputString.append("Error occurred - " + e.getMessage());
      log.debug(e.getMessage(), e);
    }
    return outputString.toString();
  }
  
  @Override
	public void run() {
		log.debug("Started " + threadNum);
		long aa = System.currentTimeMillis();
		sendSms();
		log.debug("Completed " + threadNum + " in "
				+ (System.currentTimeMillis() - aa) + " Milli Second.");
	}
  
  public void sendConfigSms(SMSAlertsForm form, String userId) {
		 
	 }

  
  public String call(SMSAlertsForm form, String userId) throws Exception {
		if(form!=null){
			String mob = form.getUserMobileNumber().replaceAll(" ","");
			String content = form.getSmsHindiContent();
			//System.out.println("mob no:-  " + mob);
			System.out.println("content :-  " + form.getSmsHindiContent());
			smsDetail.setUserId(userId);
			smsDetail.setSmsAlertsForm(form);
			smsDetail.setSmsMobileNumber(mob);
			smsDetail.setSmsMessasgeContent(content);
		}
		run();
		return "Completed";

	}
  
  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session)
    throws Exception
  {
    return null;
  }
}
