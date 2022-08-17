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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Vector;

import com.itextpdf.text.log.SysoLogger;



public class ThreadListener implements Runnable {

	ETokenDTO dto;
	  ServerSocket welcomeSocket;
	  ListQueue objQu;
	public ThreadListener(ListQueue obj)
	{
		this.objQu = obj;
		
	}
	
	@Override
	public void run() {
		System.out.println("Thread Started of thread listner");
	try {
		Thread.sleep(15000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
			System.out.println("Working111");
			
			try {
				welcomeSocket  = new ServerSocket(6789);
				InetAddress	ip = InetAddress.getLocalHost();
				System.out.println(ip.getHostAddress());
				
			
						//welcomeSocket.bind(new InetSocketAddress(ip.getHostAddress(), 6789));
			
						while(true)
						{
				   Socket connectionSocket = welcomeSocket.accept();
				   
				   ObjectInputStream stream = new ObjectInputStream(connectionSocket.getInputStream());
			Object obj = 	stream.readObject();
			if(obj!=null)
			System.out.println("Got Something");
			if(obj instanceof ETokenDTO)
			{
				
				System.out.println("Got the Object ");
				ETokenDTO dtos = (ETokenDTO) obj;
				
	if (dtos.getCounterNo() != null && dtos.getCounterName() != null) {
			System.out.println("In Counter Wala Part");
			if (!"".equalsIgnoreCase(dtos.getCounterName())&& !"".equalsIgnoreCase(dtos.getCounterNo())) {
				System.out.println("Got Counter Object");
				if (dtos.getCounterType().equalsIgnoreCase("Maker")) {
					synchronized (objQu) {
					System.out.println("In Maker Counter Object Logic");
					Vector<ETokenDTO> makerCounter = objQu.getMakerCounter();
					if (dtos.getActiveDeactive().equalsIgnoreCase("Active")) {
							makerCounter.add(dtos);
						} else if (dtos.getActiveDeactive().equalsIgnoreCase("Deactivate")) {
							for (int f = 0; f < makerCounter.size(); f++) {
								ETokenDTO dtoC = makerCounter.get(f);
								if (dtoC.getCounterNo().equalsIgnoreCase(dtos.getCounterNo())) {
									makerCounter.remove(f);
										break;
									}
							}
									}
									objQu.setFlag(true);
								}
				//	checkExist(dtos.getRegistrationId(),  dtos.getCounterNo(), dtos.getUrls(),dtos.getAppStatus(),dtos.getEtokenNo(),dtos.getOfficeId());
							} else if (dtos.getCounterType().equalsIgnoreCase(
									"Checker")) {
							synchronized(objQu)
							{
								System.out.println("In Checker  Counter Object Logic");	
								Vector<ETokenDTO> checkerCounter = objQu.getCheckerCounter();
								if(dtos.getActiveDeactive().equalsIgnoreCase("Active"))
								{
									checkerCounter.add(dtos);
									
								}
								else if (dtos.getActiveDeactive().equalsIgnoreCase("Deactivate"))
								{
								for(int f =0;f<checkerCounter.size();f++)
								{
									ETokenDTO dtoC = checkerCounter.get(f);
									if(dtoC.getCounterNo().equalsIgnoreCase(dtos.getCounterNo()))
									{
										checkerCounter.remove(f);
										break;
									}
								}
								}
								objQu.setFlag(true);
							}
						}
					}
				}
				else
				{
				System.out.println("In Maker or Checker Than in Movement Quque");
				System.out.println("Status is " + dtos.getStatus()+"!");
				System.out.println("Registration Id is " + dtos.getRegistrationId()+"!");
				System.out.println("Type of Person " + dtos.getTypeOfPerson()+"!");
				if(dtos.getStatus().trim().equalsIgnoreCase("11")) //In case of Maker Hold moving the think out of quque
				{
					System.out.println("In Hold Maker");
					String regId = dtos.getRegistrationId();
					if(dtos.getTypeOfPerson().trim().equalsIgnoreCase("Special"))
					{
						synchronized (objQu) {
							Vector<ETokenDTO> msdto = objQu.getMakerCheckerS();
							Vector<ETokenDTO> makerCounter = objQu.getMakerCounter();
							System.out.println("MSDTO Size is "+msdto.size());
							for(int i=0;i<msdto.size();i++)
							{
								System.out.println("Before Removing Size MS : "+msdto.size());
								ETokenDTO d = msdto.get(i);
								if(d.getRegistrationId().equalsIgnoreCase(dtos.getRegistrationId()))
								{
									String counter = d.getCounterNo();
									for(int f =0;f<makerCounter.size();f++)
									{
										ETokenDTO dtoC = makerCounter.get(f);
										if(dtoC.getCounterNo().equalsIgnoreCase(counter))
										{
											dtoC.setAssigned("N");
											dtoC.setRegistrationId(d.getRegistrationId());
										}
													
										
									}
									msdto.remove(i);
									System.out.println("After Removing Size MS: "+msdto.size());
								
									break;
								}
								
							}
							objQu.setFlag(true);
						//	saveObject(objQu);
						}
						
					}
					else
					{
						synchronized (objQu) {
						Vector<ETokenDTO> mndto = objQu.getMakerCheckerN();	
						Vector<ETokenDTO> makerCounter = objQu.getMakerCounter();
						System.out.println("MNDTO Size is "+mndto.size());
						for(int i=0;i<mndto.size();i++)
						{
							System.out.println("Before Removing Size MN: "+mndto.size());
							ETokenDTO d = mndto.get(i);
							if(d.getRegistrationId().equalsIgnoreCase(dtos.getRegistrationId()))
							{
								System.out.println(d.getRegistrationId());
								
								String counter = d.getCounterNo();
								for(int f =0;f<makerCounter.size();f++)
								{
									System.out.println(makerCounter.size());
									System.out.println("counter "+counter);
									ETokenDTO dtoC = makerCounter.get(f);
									if(dtoC.getCounterNo().equalsIgnoreCase(counter))
									{
										dtoC.setAssigned("N");
										dtoC.setRegistrationId(d.getRegistrationId());
									}
												
									
								}
								
								mndto.remove(i);
								System.out.println("After Removing Size MN: "+mndto.size());
								
								break;
							}
							
						}
						objQu.setFlag(true);
					//	saveObject(objQu);
						}
					}
			
				//	for(int i=0;)
					
					
				} //In case of Maker Completed 
				else if(dtos.getStatus().trim().equalsIgnoreCase("13"))
				{
					String regId = dtos.getRegistrationId();
					System.out.println("Reg Id "+regId);
					System.out.println("In Move to Checker");
					if(dtos.getTypeOfPerson().trim().equalsIgnoreCase("Special"))
					{
						synchronized (objQu) {
							Vector<ETokenDTO> msdto = objQu.getMakerCheckerFinal();
						//	Vector<ETokenDTO> csdto = objQu.getCheckerS();
							Vector<ETokenDTO> makerCounter = objQu.getMakerCounter();
							System.out.println("MSDTO Size is "+msdto.size());
							for(int i=0;i<msdto.size();i++)
							{
								System.out.println("Before Removing Size MS 13 : "+msdto.size());
								ETokenDTO d = msdto.get(i);
								if(d.getRegistrationId().equalsIgnoreCase(dtos.getRegistrationId()))
								{
									String counter = d.getCounterNo();
									System.out.println(d.getCounterNo());
									for(int f =0;f<makerCounter.size();f++)
									{
										ETokenDTO dtoC = makerCounter.get(f);
										System.out.println(dtoC.getCounterNo());
										if(dtoC.getCounterNo().equalsIgnoreCase(counter))
										{
											dtoC.setAssigned("N");
											dtoC.setRegistrationId(d.getRegistrationId());
										}
													
									}
									
									
									d.setAppStatus("13");
									//checkExist(regId, d.getCounterNoChecker(), d.getUrls(),d.getAppStatus(),d.getEtokenNo(),d.getOfficeId());
									d.setCounterNo(""); 
									//csdto.add(d);
								//	msdto.remove(i);
									System.out.println("After Removing Size MS: "+msdto.size());
								//	System.out.println("After Adding Size CS: "+csdto.size());
								
									break;
								}
								
							}
							objQu.setFlag(true);
					//		saveObject(objQu);
						}
						
					}
					else
					{
						synchronized (objQu) {
						Vector<ETokenDTO> mndto = objQu.getMakerCheckerN();
						//	Vector<ETokenDTO> cndto = objQu.getCheckerN();	
						Vector<ETokenDTO> makerCounter = objQu.getMakerCounter();
						System.out.println("MNDTO Size is "+mndto.size());
						for(int i=0;i<mndto.size();i++)
						{
							System.out.println("Before Removing Size MN : "+mndto.size());
							ETokenDTO d = mndto.get(i);
							if(d.getRegistrationId().equalsIgnoreCase(dtos.getRegistrationId()))
							{
								String counter = d.getCounterNo();
								for(int f =0;f<makerCounter.size();f++)
								{
									ETokenDTO dtoC = makerCounter.get(f);
									if(dtoC.getCounterNo().equalsIgnoreCase(counter))
									{
										dtoC.setAssigned("N");
										dtoC.setRegistrationId(d.getRegistrationId());
									}
												
								}
								System.out.println("EToken No : "+d.getEtokenNo());
								d.setAppStatus("13");
							//	checkExist(regId, d.getCounterNoChecker(), d.getUrls(),d.getAppStatus(),d.getEtokenNo(),d.getOfficeId());
								d.setCounterNo(""); 
							//	cndto.add(d);
								
								//mndto.remove(i);
								System.out.println("After Removing Size MN : "+mndto.size());
							//	System.out.println("After Addng Size CN : "+cndto.size());
								break;
							}
							
						}
						objQu.setFlag(true);
					//	saveObject(objQu);
						}
					}
					
					
					
					
				}
				else if(dtos.getStatus().trim().equalsIgnoreCase("14")||dtos.getStatus().trim().equalsIgnoreCase("17"))
				{
					String regId = dtos.getRegistrationId();
					System.out.println("In Checker Complete or Emty Checker.");
					if(dtos.getTypeOfPerson().trim().equalsIgnoreCase("Special"))
					{
						synchronized (objQu) {
							
							Vector<ETokenDTO> csdto = objQu.getMakerCheckerS();
							Vector<ETokenDTO> checkerCounter = objQu.getCheckerCounter();
							System.out.println("Before Removing Size CS : "+csdto.size());
							for(int i=0;i<csdto.size();i++)
							{
								ETokenDTO d = csdto.get(i);
								if(d.getRegistrationId().equalsIgnoreCase(dtos.getRegistrationId()))
								{
									System.out.println("new:"+dtos.getStatus());
									if(dtos.getStatus().trim().equalsIgnoreCase("17"))
									{
										System.out.println("inside setting the status");
										d.setCollectCounter(d.getWaitCounterMaker());
										d.setCounterNoChecker("");
										d.setAppStatus("17");
									}
									
									String counter = d.getCounterNoChecker();
									for(int f =0;f<checkerCounter.size();f++)
									{
										System.out.println("inside checker assigned");
										ETokenDTO dtoC = checkerCounter.get(f);
										if(dtoC.getCounterNo().equalsIgnoreCase(counter))
										{
											System.out.println(dtoC.getCounterNo());
											dtoC.setAssigned("N");
											dtoC.setRegistrationId(d.getRegistrationId());
										}
													
									}
									if(dtos.getStatus().trim().equalsIgnoreCase("14"))
									{
										System.out.println("here not");
									csdto.remove(i);
									}
									
									System.out.println("After Removing Size CS : "+csdto.size());
									break;
								}
								
							}
							objQu.setFlag(true);
							//saveObject(objQu);
						}
						
					}
					else
					{
						synchronized (objQu) {
					
						Vector<ETokenDTO> cndto = objQu.getMakerCheckerN();
						Vector<ETokenDTO> checkerCounter = objQu.getCheckerCounter();
						System.out.println("CNDTO Size is "+cndto.size());
						for(int i=0;i<cndto.size();i++)
						{
							System.out.println("i: "+i);
							System.out.println("Before Removing Size CN : "+cndto.size());
							ETokenDTO d = cndto.get(i);
							System.out.println(d.getRegistrationId());
							if(d.getRegistrationId().equalsIgnoreCase(dtos.getRegistrationId()))
							{
								if(dtos.getStatus().trim().equalsIgnoreCase("17"))
								{
								String counter = d.getCounterNoChecker();
								System.out.println(counter);
								for(int f =0;f<checkerCounter.size();f++)
								{
									System.out.println(checkerCounter.size());
									ETokenDTO dtoC = checkerCounter.get(f);
									if(dtoC.getCounterNo().equalsIgnoreCase(counter))
									{
										System.out.println("if");
										dtoC.setAssigned("N");
										dtoC.setRegistrationId(d.getRegistrationId());
									}
								}
								d.setCollectCounter(d.getWaitCounterMaker());
								d.setCounterNoChecker("");
								d.setAppStatus("17");
								}
								System.out.println("After Removing Size CN: "+cndto.size());
								if(dtos.getStatus().trim().equalsIgnoreCase("14"))
								{
									String counter = d.getCounterNoChecker();
									for(int f =0;f<checkerCounter.size();f++)
									{
										System.out.println(checkerCounter.size());
										ETokenDTO dtoC = checkerCounter.get(f);
										if(dtoC.getCounterNo().equalsIgnoreCase(counter))
										{
											System.out.println("if");
											dtoC.setAssigned("N");
											dtoC.setRegistrationId(d.getRegistrationId());
										}
									}
									cndto.remove(i);
									
								}
								
								break;
							}
							
						}
						objQu.setFlag(true);
					//	saveObject(objQu);
						
						}
					}
					
					
					
					
				}
				else if(dtos.getStatus().trim().equalsIgnoreCase("7"))
				{
					String regId = dtos.getRegistrationId();
					System.out.println("In Collection Complete or Emty Collection.");
					dtos.setTypeOfPerson("Normal");
					if(dtos.getTypeOfPerson().trim().equalsIgnoreCase("Special"))
					{
						synchronized (objQu) {
							
							Vector<ETokenDTO> csdto = objQu.getMakerCheckerS();
							Vector<ETokenDTO> checkerCounter = objQu.getCheckerCounter();
							//System.out.println("Before Removing Size CS : "+csdto.size());
							for(int i=0;i<csdto.size();i++)
							{
								ETokenDTO d = csdto.get(i);
								if(d.getRegistrationId().equalsIgnoreCase(dtos.getRegistrationId()))
								{
									
									csdto.remove(i);
									
									
								//	System.out.println("After Removing Size CS : "+csdto.size());
									break;
								}
								
							}
							objQu.setFlag(true);
							//saveObject(objQu);
						}
						
					}
					else
					{
						synchronized (objQu) {
					
						Vector<ETokenDTO> cndto = objQu.getMakerCheckerN();
						Vector<ETokenDTO> checkerCounter = objQu.getCheckerCounter();
						//System.out.println("CNDTO Size is "+cndto.size());
						for(int i=0;i<cndto.size();i++)
						{
						//	System.out.println("Before Removing Size CN : "+cndto.size());
							ETokenDTO d = cndto.get(i);
							
							if(d.getRegistrationId().equalsIgnoreCase(dtos.getRegistrationId()))
							{
								
								
								
								
									cndto.remove(i);
								
								
								break;
							}
							
						}
						objQu.setFlag(true);
					//	saveObject(objQu);
						
						}
					}
					
					
					
					
				}
				
			
				//System.out.println("sdasd");
				//System.out.println(dtos.getStatus());
				//System.out.println(dtos.getRegistrationId());
			//	System.out.println(dtos.getOfficeId());
				/*String a = (String) obj;
				String values[]  = a.split(";");
				String redId = values[0];
				String status = values[1];*/
				
				/*synchronized (objQu) {
					
					Vector<ETokenDTO> maker = objQu.getMakerFinal();
					Vector<ETokenDTO> checkerN = objQu.getCheckerN();
					Vector<ETokenDTO> checkerS = objQu.getCheckerS();
					for(int i = 0;i<maker.size();i++)
					{
						ETokenDTO dto = maker.get(i);
						if(dto.getRegistrationId().equalsIgnoreCase(redId))
						{
							dto.setAppStatus(values[1]);
							
							if(dto.getTypeOfPerson().equalsIgnoreCase("Special"))
							{
								checkerS.add(dto);
								removeMaker(redId,"S");
							}
							else
							{
								checkerN.add(dto);
								removeMaker(redId,"N");
							}
							
						}
						//maker
						
						
					}
					
					
					
				}*/
				
			}
			
			
			}
						}
						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
	
		
		
	}
	public void removeMaker(String redId, String SorN)
	{
		Vector<ETokenDTO> dto = null;
		if(SorN.equalsIgnoreCase("N"))
		{
			dto = objQu.getMakerN();
		}
		else
		{
			dto = objQu.getCheckerN();
		}
		
		for(int i=0;i<dto.size();i++)
		{
			ETokenDTO dtos = dto.get(i);
			if(dtos.getRegistrationId().equalsIgnoreCase(redId))
			{
				dto.remove(i);
			}
			
			
		}
		
	}
	
	public synchronized boolean saveObject(ListQueue obj)
	{
		FileInputStream in =null;
		ObjectInputStream readers = null;
		boolean check = false;
		try {
			File yourFile = new File("D:\\Data\\obj.sav");
			/*if(!yourFile.exists()) {
				System.out.println("File Doesnot");
			    yourFile.createNewFile();
			    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\obj.sav"));
			    obj = new ListQueue();
			    out.writeObject(obj);
			    out.close();
			    check = true;

			} */
			
			if(yourFile.exists())
			{
				if(yourFile.delete())
				{
					yourFile.createNewFile();
					 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\Data\\obj.sav"));
					 out.writeObject(obj);
					 
					    out.close();
					 System.out.println("Object Existed saved Thread Listener Thread");
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
				 System.out.println("Object Created saved  Thread Listener");
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
	private boolean checkExist(String regId, String counterNo,URL urls,String status,String etokenNo,String officeId) {
		try{
			System.out.println("inside checkExist");
	
		URL url = urls;
		
		
		System.out.println("counterNo checker is>>>" + counterNo);
		System.out.println("etokenNo is>>>" + etokenNo);
		System.out.println("url is>>>" + url);
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
