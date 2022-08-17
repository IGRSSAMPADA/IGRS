package com.wipro.igrs.device.applet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.device.servlet.ETokenServlet;
import com.wipro.igrs.eToken.dao.ETokenDAO;
import com.wipro.igrs.eToken.bo.ETokenBO;
import com.wipro.igrs.device.applet.ETokenDTO;
import com.wipro.igrs.util.PropertiesFileReader;

public class EtokenChange implements Runnable {
	private String regId;
	private String status;
	HttpServletRequest request;
	HttpSession se;
	PropertiesFileReader pr;
	String path ="D:\\myfile_";
	private Logger logger = 
		(Logger) Logger.getLogger(EtokenChange.class);
	public EtokenChange(String regId, String status, HttpServletRequest request) {
		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
			this.path = pr.getValue("etoken_log_file");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.regId = regId;
		this.status = status;
		
	//	this.request = request;
	//	this.se = request.getSession();
	}

	
	public synchronized void write(String regId,String path,String status,String message)
	{
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    out.println(new Date().toString()+"       :   "+regId+"   Status  :     "+status+" Error    :   "+   message );
	    out.close();
		
	}
	
	public void run() {

		ETokenDAO dao = new ETokenDAO();
		ETokenBO bo=new ETokenBO();
		ETokenDTO dto1=new ETokenDTO();
		String regid = regId;
		char ref[] = regid.toCharArray();
		String boo1="false";
		if(ref.length<12)
		{
			regid = "0"+regid;
		}
		
		ETokenDTO dto = dao.getInfo(regid, status, request);

		if (dto != null) {
			String ipAdress = dao.getOfficeIp(dto.getOfficeId());
			if (status.equalsIgnoreCase("13")) {
				
				
				dto.setAppStatus("For Checker");	
			boolean boo = 	dao.moveToChecker(regid, status);
			try {
				 boo1 = bo.insertChecker(dto.getRegistrationId(), dto1,dto.getAppStatus());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(boo &&(boo1=="Succesfully")) 
			write(regid, path+ipAdress+"_"+dto.getOfficeId() , status, "Completed Database Insertion for Maker Completion in Maker tables");
			else
				write(regid, path+ipAdress+"_"+dto.getOfficeId(), status, "Failed Database Insertion for Maker Completion i nMaker tables");	
			
			} else if (status.equalsIgnoreCase("11")) {
				
				dto.setAppStatus("On Maker-Hold");
				boolean boo =	dao.setMakerHoldDeactive(regid, status);

				if(boo)
					write(regid, path+ipAdress+"_"+dto.getOfficeId() , status, "Completed Database Insertion for Maker Hold in Maker tables");
					else
						write(regid, path+ipAdress+"_"+dto.getOfficeId(), status, "Failed Database Insertion for Maker Hold in Maker tables");	
				
			} else if (status.equalsIgnoreCase("14")
					|| status.equalsIgnoreCase("17")) {

				boolean boo =	dao.setCheckerHoldDeactivate(regid, status);

				if(boo)
					write(regid, path+ipAdress+"_"+dto.getOfficeId() , status, "Completed Database Insertion for Checker  Completion | Hold in Checker tables");
					else
						write(regid, path+ipAdress+"_"+dto.getOfficeId(), status, "Failed Database Insertion for Checekr Completion | Hold in Checker tables");	
				
			}
			else if(status.equalsIgnoreCase("7")){
				write(regid, path+ipAdress+"_"+dto.getOfficeId() , status, "Completed Database Insertion for Checker  delivery of document");
			}


			//String ipAdress = dao.getOfficeIp(dto.getOfficeId());
			try {
				Socket clientSocket = new Socket(ipAdress, 6789);
				ObjectOutputStream stream = new ObjectOutputStream(clientSocket
						.getOutputStream());
				System.out.println(ipAdress);
				System.out.println(stream.toString());

				stream.writeObject(dto);

				stream.close();
				clientSocket.close();
				Thread.sleep(5000);
			} catch (UnknownHostException e) {
				
				write(regid, path+ipAdress+"_"+dto.getOfficeId() , status, e.getMessage());
				logger.debug("EToken Exception "+e.getMessage(),e);
				
			} catch (IOException e) {
				write(regid, path+ipAdress+"_"+dto.getOfficeId() , status, e.getMessage());
				logger.debug("EToken Exception "+e.getMessage(),e);
			} catch (InterruptedException e) {
				write(regid, path+ipAdress+"_"+dto.getOfficeId() , status, e.getMessage());
				logger.debug("EToken Exception "+e.getMessage(),e);
			}
		}
	}

}
