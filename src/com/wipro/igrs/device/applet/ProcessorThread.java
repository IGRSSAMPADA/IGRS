package com.wipro.igrs.device.applet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.Timer;

public class ProcessorThread implements Runnable{

	ListQueue objs;
	Timer time ;
	public ProcessorThread(ListQueue obj)
	{
		this.objs  = obj;
		
	}
	
	@Override
	public void run() {
	
		kam();
		
	}
	
	public Vector clonedData(Vector list) throws CloneNotSupportedException
    {
    	Vector temp = new Vector();
    	for(int i=0;i<list.size();i++){
    	
    		
    		ETokenDTO dto=(ETokenDTO)list.get(i);
    		ETokenDTO dto1=(ETokenDTO) dto.clone();
    		temp.add(dto1);
    	
    	}
    	return temp;
    }
	public synchronized boolean saveObject(ListQueue obj)
	{
		
		System.out.println("In saveobj");
		FileInputStream in =null;
		ObjectInputStream readers = null;
		boolean check = false;
		try {
			File yourFile = new File("D:\\Data\\obj.sav");
			if(yourFile.exists())
			{
				if(yourFile.delete())
				{
					yourFile.createNewFile();
					 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\Data\\obj.sav"));
					 out.writeObject(obj);
					 
					    out.close();
					 System.out.println("Object Existed saved Processor Thread");
					    check = true;
				}
				else
				{
					System.out.println("Coudnt Find File");
				}
			}
			else
			{
				yourFile.createNewFile();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\Data\\obj.sav"));
				out.writeObject(obj);
				out.close();
				 System.out.println("Object Created saved Processor Thread ");
			    check = true;
			}
				

			
			
		} catch (FileNotFoundException e) {
			check = false;
			e.printStackTrace();
		} catch (IOException e) {
			check = false;
			e.printStackTrace();
		}
		/*} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return check;
		
		
	}
	
	private synchronized void kam() {
		while(true)
		{
			
			while(objs.isFlag())
			{
				
				
				System.out.println("Working...process thread");
	//		
			 Vector<ETokenDTO> checkerCounter = objs.getCheckerCounter();
			 Vector<ETokenDTO> makerCounter = objs.getMakerCounter();
			 Vector<ETokenDTO> checkerFinal	= objs.getCheckerFinal();
	
		
			 //New Changes for Etoken August 2015 
			 Vector<ETokenDTO> makerCheckerN = objs.getMakerCheckerN();
			 Vector<ETokenDTO> makerCheckerS = objs.getMakerCheckerS();
			 Vector<ETokenDTO> makerCheckerFinal = objs.getMakerCheckerFinal();
			 
			 
			 
			 //Clearing Both Final Lists 
			 makerCheckerFinal.removeAllElements();
			
			// Making Maker final List with updated values. //2015 changes August for E-Token Revised. 
			makerCheckerFinal.addAll(makerCheckerS);
			makerCheckerFinal.addAll(makerCheckerN);
			
			
						
			// Making Checker final List with updated values.
	//	checkerFinal.addAll(checkerS);
		//	checkerFinal.addAll(checkerN);
			System.out.println("Checker Final List size : "+checkerFinal.size());
			
			
			Vector<ETokenDTO> tempCheckerEmpty = new Vector<ETokenDTO>();
			Vector<ETokenDTO> tempMakerEmpty = new Vector<ETokenDTO>();
			Vector<ETokenDTO> tempcheckerCounter = new Vector<ETokenDTO>();
			Vector<ETokenDTO> tempMakerCounter = new Vector<ETokenDTO>();
			
			System.out.println("Maker Checker Final List Size : "+makerCheckerFinal.size());
			for(int j=0;j<makerCheckerFinal.size();j++)
			{
				ETokenDTO dto = makerCheckerFinal.get(j);
				System.out.println("App Status :: " +dto.getAppStatus());
				if(dto.getAppStatus().equalsIgnoreCase("For Maker")){
					dto.setAppStatus("10");
				}
				else if(dto.getAppStatus().equalsIgnoreCase("On Maker-Hold")){
					dto.setAppStatus("11");
				}
				else if(dto.getAppStatus().equalsIgnoreCase("For Checker")){
					dto.setAppStatus("13");
				}
				else if(dto.getAppStatus().equalsIgnoreCase("On Checker-Hold")){
					dto.setAppStatus("14");
				}
				
				if(("14").equalsIgnoreCase(dto.getAppStatus())||("13").equalsIgnoreCase(dto.getAppStatus()))
				{
					if(dto.getCounterNoChecker()==null || dto.getCounterNoChecker().equalsIgnoreCase(""))
					{
						tempCheckerEmpty.add(dto);
						//System.out.println("Added in Checker " + tempCheckerEmpty.size());
						
					}
				}
				else if(("10").equalsIgnoreCase(dto.getAppStatus())||("11").equalsIgnoreCase(dto.getAppStatus()))
				{
				if(dto.getCounterNo()==null || dto.getCounterNo().equalsIgnoreCase(""))
				{
					
					tempMakerEmpty.add(dto);
			
				}
				}	
				
			}
			
			// For checker Counter 
		//	System.out.println("size of checker : "+checkerCounter.size());
		//	System.out.println("size of checker counter : "+tempcheckerCounter);
		//	System.out.println("tempCheckerEmpty : "+tempCheckerEmpty);
			for(int i =0;i<checkerCounter.size();i++)
			{
			//	System.out.println("size of checker counter "+checkerCounter.size());
				ETokenDTO cCDto = checkerCounter.get(i);
				if(cCDto.getAssigned()==null || cCDto.getAssigned().equalsIgnoreCase("N"))
				{
					if(!tempCheckerEmpty.isEmpty())
					{
						//System.out.println("regId : "+);
			//			System.out.println(" size of empty checker : "+tempCheckerEmpty.size());
					tempCheckerEmpty.get(0).setCounterNoChecker(cCDto.getCounterNo());
					tempCheckerEmpty.get(0).setCounterNo("");
					tempCheckerEmpty.get(0).setCollectCounter("");
					tempCheckerEmpty.get(0).setTimeRemaining(0);
					cCDto.setAssigned("Y");
					for(int j=0;j<makerCheckerFinal.size();j++)
					{
						ETokenDTO dto = makerCheckerFinal.get(j);
						if(tempCheckerEmpty.get(0).getRegistrationId()!=null)
						if(tempCheckerEmpty.get(0).getRegistrationId().equals(dto.getRegistrationId())){
							System.out.println("success : "+tempCheckerEmpty.get(0).getRegistrationId());
					checkExist(dto.getRegistrationId(), cCDto.getCounterNo(), dto.getUrls(),dto.getAppStatus(),dto.getEtokenNo(),dto.getOfficeId());
						}
					
					}
					tempCheckerEmpty.remove(0);
					}
					else
					{
						break;
					}
				}
				
			}
			System.out.println("Maker Counter Size "+makerCounter.size());
			// For Maker Counter 
			for(int i =0;i<makerCounter.size();i++)
			{
				
				ETokenDTO cCDto = makerCounter.get(i);
				if(cCDto.getAssigned()==null || cCDto.getAssigned().equalsIgnoreCase("N"))
				{
					System.out.println("in");
					if(!tempMakerEmpty.isEmpty())
					{
						tempMakerEmpty.get(0).setCounterNo(cCDto.getCounterNo());
						tempMakerEmpty.get(0).setCounterNoChecker("");
						tempMakerEmpty.get(0).setCollectCounter("");
						tempMakerEmpty.get(0).setTimeRemaining(0);
						tempMakerEmpty.get(0).setWaitCounterMaker(cCDto.getCounterNo());
					
					cCDto.setAssigned("Y");
					System.out.println("regId of "+tempMakerEmpty.get(0).getRegistrationId());
					for(int j=0;j<makerCheckerFinal.size();j++)
					{
						ETokenDTO dto = makerCheckerFinal.get(j);
						if(tempMakerEmpty.get(0).getRegistrationId()!=null)
						if(tempMakerEmpty.get(0).getRegistrationId().equals(dto.getRegistrationId())){
							System.out.println("success : "+tempMakerEmpty.get(0).getRegistrationId());
					checkExist(dto.getRegistrationId(), cCDto.getCounterNo(), dto.getUrls(),dto.getAppStatus(),dto.getEtokenNo(),dto.getOfficeId());
						}
					
					}
					
					System.out.println("counter no 1 : "+cCDto.getCounterName());
				//	checkExist(cCDto.getRegistrationId(), cCDto.getCounterNo(), cCDto.getUrls(),cCDto.getAppStatus(),cCDto.getEtokenNo(),cCDto.getOfficeId());
					tempMakerEmpty.remove(0);
					}
					
					else
					{
						break;
					}
					
				}
				
			}
			
			objs.setFlag(false);
			saveObject(objs);
			
		}
		}
	}
	private boolean checkExist(String regId, String counterNo,URL urls,String status,String etokenNo,String officeId) {
	try{
		System.out.println("inside checkExist");

	URL url = urls;
	
	
	
	System.out.println("regId is>>>" + regId);
	System.out.println("counterNo is>>>" + counterNo);
	System.out.println("urls is>>>" + urls);
	System.out.println("etokenNo is>>>" + etokenNo);
	System.out.println("status is>>>" + status);
	System.out.println("etokenNo is>>>" + etokenNo);
	System.out.println("officeId is>>>" + officeId);
	URLConnection connection = url.openConnection();
	connection.setDoInput(true);
	connection.setDoOutput(true);
	connection.setUseCaches(false);
	connection.setRequestProperty("Content-Type", "application/binary");
	HttpURLConnection hc = (HttpURLConnection) (connection);
	//System.out.println(hc);
	hc.setRequestMethod("POST");
	OutputStream os = hc.getOutputStream();
	InetAddress	ip = InetAddress.getLocalHost();
	HashMap<String, Object> payload = new HashMap<String, Object>();
//	System.out.println(ip.getHostAddress());
	System.out.println("Reg Id inside is "+regId);
	payload.put("work", "insertMaker");
	payload.put("ofc", officeId);
	payload.put("regId", regId);
	payload.put("counterNo", counterNo);
	payload.put("status", status);
	payload.put("etokenNo", etokenNo);
	
	
	ObjectOutputStream oos = new ObjectOutputStream(os);
	oos.writeObject(payload);
	oos.close();
	
	ObjectInputStream in = new ObjectInputStream(hc.getInputStream());
	boolean boo = (Boolean) in.readObject();
	//System.out.println(boo);
	if(boo==true)
	{
		return true;
	}
	else
	{
		return false;
	}
	
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return true;
	}


}
