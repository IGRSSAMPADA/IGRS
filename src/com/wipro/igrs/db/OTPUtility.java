package com.wipro.igrs.db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import org.apache.log4j.Logger;

import com.wipro.igrs.util.PropertiesFileReader;

public class OTPUtility {

	static String username = "DITMP-CTDDRS";
	static String password = "qazxswedc123#";
	static String senderid = "CTDDRS";
		String timeout = "10000";
		String proxy="";
		String proxyPort = "";
	static HttpURLConnection connection = null;
	private static final Logger Log = Logger.getLogger(OTPUtility.class);
	
	public String getOTP(){
	    List<Integer> numbers = new ArrayList<Integer>();
	    for(int i = 0; i < 10; i++){
	        numbers.add(i);
	    }

	    Collections.shuffle(numbers);

	    String result = "";
	    for(int i = 0; i < 4; i++){
	        result += numbers.get(i).toString();
	    }
	    return result;
	}
	
	
	public boolean generateOTP(String userId,String referenceId,String moduleId)
	{
	
		boolean coo = false;
		boolean boo=false;
		OTPDao dao = new OTPDao();
		
		//Update existing OTP for module Id and referenceId
		dao.updateOTPModuleId(referenceId, moduleId);
		
		String OTP = getOTP(); 
		boolean boos= true;
		//Check for already existing OTP for that date
		while(boos)
		{
		boos = 	dao.checkOTP(OTP);
		if(boos == true)
		{
			OTP = getOTP();
		}
		
		}
		
	String email = 	dao.getEmail(userId);
	String mobileNo = dao.getMobile(userId);
	boo = dao.insertIntoOtpMaster(userId,referenceId,moduleId,OTP,email,mobileNo);
	
	if(boo)
	{
	OTPDTO dto = new OTPDTO();
	dto.setMessageOTP(OTP);
	dto.setNumber(mobileNo);
	dto.setEmailId(email);
	dto.setReferenceId(referenceId);
	JMSCommunicator qs = new JMSCommunicator();
	//cloneObject(dto);
	try {
		
		PropertiesFileReader reads = PropertiesFileReader.getInstance("resources.igrs");
		
		String ctx = reads.getValue("jms_initial_context");
		String queueName = reads.getValue("jms_q_jndi_name");
		String queueNameEmail = reads.getValue("jms_qe_jndi_name");
		String connectionFactoryName = reads.getValue("jms_connection_factory_jndi_name");
		
		InitialContext ic = qs.getInitialContext(ctx);
		try
		{
		qs.init(ic, queueName, connectionFactoryName);
		JMSCommunicator.readAndSend(qs,dto);
		qs.close();
		}
		catch (Exception e) {
			Log.debug("Failed to Connext to SMS Queue",e);
		}
		
		try
		{
		qs.init(ic, queueNameEmail, connectionFactoryName);
		JMSCommunicator.readAndSend(qs,dto);
		qs.close();
		}
		catch (Exception e) {
			Log.debug("Failed to Connext to Email Queue",e);
		}
		
		coo = true;
	} catch (NamingException e) {
		Log.debug(e.getMessage(),e);
	} catch (Exception e) {
		
		Log.debug(e.getMessage(),e);
	}
	
	}
	
	
		//insert OTP into OTP Master
	
		boo = false;
		if(boo)
		{
			
			  try {
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				username = pr.getValue("igrs_sms_username");
				password = pr.getValue("igrs_sms_password");
				senderid = pr.getValue("igrs_sms_senderid");
				timeout =  pr.getValue("igrs_sms_timeout");
				proxy = pr.getValue("igrs_sms_proxy_url");
				proxyPort="8080";
			} catch (Exception e) {
				Log.debug(e.getMessage(),e);
				e.printStackTrace();
			}
			
			Log.debug("In SMS sending");
			String output = sendSingleSMS(username,password,senderid,mobileNo, "Your OTP for transaction with reference ID : "+referenceId+" is "+OTP);
			Log.debug("Response Code for SMS is :- "+output);
			boo = true;
			if(boo)
			{
				boo = dao.updateMobileStatus(referenceId,moduleId,userId);
			}
		//	Log.debug("Before Sending Mail");
		//	boo = SendMail("admn2@mpigr.gov.in",email,OTP);
		//	if(boo)
		//	{
		//		boo = dao.updateEmailStatus(referenceId,moduleId,userId);
		//	}
			
		}
		return coo;
		
		
		
		
	}


	public Object cloneObject(Object originalObject) {
        Object clonedObject = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(originalObject);
            //retrieve back
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            clonedObject = objectInputStream.readObject();
        } catch (IOException ioe) {
  
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
  
        return clonedObject;
    }
	
	public  String sendSingleSMS(String username,
			String password, String senderId, 
String mobileNo, String message) {
		String outputString ="";
try {
	URL url = new URL("http://164.100.129.141/esms/sendsmsrequest");
	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.125.243.67", 8080));
	connection = (HttpURLConnection) url.openConnection(proxy);
	connection.setDoInput(true);
	int timeo =10000;
	try{
	 timeo = Integer.parseInt(timeout);
	}
	catch (Exception e) {
		Log.debug(e.getMessage(),e);
	}
	connection.setConnectTimeout(timeo);
	connection.setDoOutput(true);
	connection.setRequestMethod("POST");
	connection.setFollowRedirects(true);
	
	
	Log.debug("Username is :-" +username);
	Log.debug("Password is :-" +password);
	Log.debug("Sender ID is :-"+senderId);
	Log.debug("Timeout is :-"+timeout);
	
		String smsservicetype = "singlemsg"; // For single message.
			String query = "username=" + URLEncoder.encode(username)
				+ "&password=" + URLEncoder.encode(password)
				+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
				+ "&content=" + URLEncoder.encode(message) + "&mobileno="
				+ URLEncoder.encode(mobileNo) + "&senderid="
				+ URLEncoder.encode(senderId);
		connection.setRequestProperty("Content-length", String
			.valueOf(query.length()));
		connection.setRequestProperty("Content-Type",
			"application/x-www-form-urlencoded");
		connection.setRequestProperty("User-Agent",
			"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

		// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(connection
					.getOutputStream());

		// write out the data
		int queryLength = query.length();
		output.writeBytes(query);
		// output.close();

		// get ready to read the response from the cgi script
		DataInputStream input = new DataInputStream(connection
					.getInputStream());

		// read in each character until end-of-stream is detected
		for (int c = input.read(); c != -1; c = input.read())
		{
			System.out.print((char) c);
			outputString = outputString+(char)c;
		}
			input.close();
	} catch (Exception e) {
		System.out.println("Something bad just happened.");
		Log.debug(e.getMessage(),e);
	}

	return outputString;
}

public boolean validateOTP(String referenceId,String moduleId,String userId,String otp)
{
	OTPDao dao = new OTPDao();
	boolean boo = dao.validateOTP(referenceId,moduleId,userId,otp);
	
	return boo;
}
	
	public boolean SendMail(String senderEmail,String recieptEmail,String OTP)
	{
		Log.debug("In Mail Server");
		boolean check = false;
		try {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "164.100.14.5");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.debug", "false");
	        props.put("mail.smtp.ssl.enable", "true");
	        props.put("mail.smtp.port","465");
	        props.put("http.proxyPort","8080");
	        props.put("http.proxyHost","10.125.243.67");
	        props.put("mail.smtp.connectiontimeout",10000);
	        Session session = Session.getInstance(props, new EmailAuth());
	        Message msg = new MimeMessage(session);
	        Log.debug("Mail Properties Set");
	        InternetAddress from = new InternetAddress(senderEmail, "IGRS");
	        msg.setFrom(from);

	        InternetAddress toAddress = new InternetAddress(recieptEmail);

	        msg.setRecipient(Message.RecipientType.TO, toAddress);

	        msg.setSubject("OTP Number for current Transaction");
	        Multipart multipart = new MimeMultipart();

			BodyPart part1 = new MimeBodyPart();
			part1.setContent("<html>\n" +
	                "<body>\n" +
	                "\n" +
	                "" +
	                "Your OTP number for the current tranaction is : "+OTP +
	                "\n" +
	                "</body>\n" +
	                "</html>", "text/html");
			multipart.addBodyPart(part1);
	      msg.setContent(multipart);
	        Transport.send(msg);
	    } catch (UnsupportedEncodingException ex) {
	    	
	        ex.printStackTrace();
	        return false;
	    } catch (MessagingException ex) {
	        ex.printStackTrace();
	        return false;
	    }
	    return true;
	}
	}

class EmailAuth extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication("admn2@mpigr.gov.in", "Ntl@1216");

    }
}

	
	

