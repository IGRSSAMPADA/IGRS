package com.wipro.igrs.digitalSign;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;



public class DigtalSignThread implements Callable, Runnable {

	private static Logger logger= (Logger) Logger.getLogger(DigtalSignThread.class);
	String sourcePath="";
	String destinationPath="";
	String certLabel="";
	String x1="100";
	String x2="100";
	String x3="200";
	String x4="200";
	
	public DigtalSignThread(String sourcePath,String destinationPath,String certLabel,String x1,String x2,String x3,String x4)
	{
		this.sourcePath=sourcePath;
		this.destinationPath=destinationPath;
		this.certLabel=certLabel;
		this.x1=x1;
		this.x2=x2;
		this.x3=x3;
		this.x4=x4;
		
	}
	
	

	@Override
	public Object call() throws Exception {
		Process pro2;
		
			
		//String ex =  "java -cp . igrs_sms.DocumentService "+sourcePath+" "+destinationPath+" "+certLabel+" "+x1+" "+x2+" "+x3+" "+x4+" ";
		String ex="";
		try{
			
		
		 ex = "sh /home/oracle/Downloads/SMS_IGRS/bin/sms.sh "+sourcePath+" "+destinationPath+" "+certLabel+" "+x1+" "+x2+" "+x3+" "+x4+" >> Logs.txt";
		//	File f = new File("C:\\Users\\MO836801\\Desktop\\WinSCPPortable");
		logger.debug("Our Code First "+ex);	
		pro2 = Runtime.getRuntime().exec(ex);
			pro2.waitFor(); 
		}
		catch (Exception e) {
			write(ex,e.getMessage(),"//shared//LogDigSign.txt");
			logger.debug("Our Code :"+e.getMessage(),e);
			System.out.println("Standalone Error is "+e.getMessage());
		}
		
			return true;
		
		
	}



	@Override
	public void run() {
		Process pro2;
		
		String ex="";
		try{
			
		
		 ex = "sh /home/oracle/Downloads/SMS_IGRS/bin/sms.sh "+sourcePath+" "+destinationPath+" "+certLabel+" "+x1+" "+x2+" "+x3+" "+x4+"";
		//	File f = new File("C:\\Users\\MO836801\\Desktop\\WinSCPPortable");
		logger.debug("Our Code First "+ex);	
		pro2 = Runtime.getRuntime().exec(ex);
			pro2.waitFor(); 
		}
		catch (Exception e) {
			write(ex,e.getMessage(),"//shared//LogDigSign.txt");
			logger.debug("Our Code :"+e.getMessage(),e);
			System.out.println("Standalone Error is "+e.getMessage());
		}
		
			
	}
	public  void write(String ex,String exception,String path)
	{
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    out.println("Our String Path "+ ex+"  Exception Occured"+exception );
	    out.close();
		
	}
	
	public static void main(String[] args) {
		
		DigtalSignThread a = new DigtalSignThread("","","","","","","");
		try {
			a.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
