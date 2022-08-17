package com.wipro.igrs.device.applet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.Timer;

public class TimeManagerClass extends JFrame implements  ActionListener {
	ListQueue objs;
	Timer timer;
	int m=0;
	public TimeManagerClass(ListQueue obj) {
		
		this.objs = obj;
		System.out.println("Timer Class Running");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 10, 10);
		setVisible(false);
		timer = new Timer(3000,this);
		timer.start();
		timer.setRepeats(false);
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
			/*if(!yourFile.exists()) {
				System.out.println("File Doesnot in porcessor Thread");
			    yourFile.createNewFile();
			    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\obj.sav"));
			    objs = new ListQueue();
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
					 System.out.println("Object Existed saved Timer Thread");
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
				 System.out.println("Object Created saved Timer Thread ");
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
	
	public static void main(String[] args) {
		
		System.out.println(2%3);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		m=m+3;
		if(m==60)
		{
			System.out.println("inside action performed");
		synchronized(objs)
		{
			System.out.println("objs");
			m=0;
			 Vector<ETokenDTO> makerN = objs.getMakerN();
			 Vector<ETokenDTO> makseS = objs.getMakseS();
			 Vector<ETokenDTO> checkerS = objs.getCheckerS();
			 Vector<ETokenDTO> checkerN = objs.getCheckerN();
			 Vector<ETokenDTO> checkerCounter = objs.getCheckerCounter();
			 Vector<ETokenDTO> makerCounter = objs.getMakerCounter();
			 Vector<ETokenDTO> makerFinal = objs.getMakerFinal();
			 Vector<ETokenDTO> checkerFinal	= objs.getCheckerFinal();
			 Vector<ETokenDTO> makerEmpty = new Vector<ETokenDTO>();
			 Vector<ETokenDTO> makerAssigned = new Vector<ETokenDTO>();
		   	 Vector<ETokenDTO> checkerEmpty = new Vector<ETokenDTO>();
			 Vector<ETokenDTO> checkerAssigned = new Vector<ETokenDTO>();
				
				
				
				System.out.println("mak N"+makerN.size());
				System.out.println("mak S"+makseS.size());
				System.out.println("che N"+checkerS.size());
				System.out.println("che S"+checkerN.size());
				System.out.println("checkerCounter"+checkerCounter.size());
				System.out.println("makerCounter"+makerCounter.size());
				System.out.println("maker final"+makerFinal.size());
				System.out.println("checkerFinal"+checkerFinal.size());
				System.out.println("makerEmpty"+makerEmpty.size());
				System.out.println("checkerEmpty"+checkerEmpty.size());
				System.out.println("checkerAssigned"+checkerAssigned.size());

			 //Clearing Both Final Lists 
			makerFinal.removeAllElements();
			checkerFinal.removeAllElements();
			
			// Making Maker final List with updated values.
			makerFinal.addAll(makseS);
			makerFinal.addAll(makerN);
			
			// Making Checker final List with updated values.
			checkerFinal.addAll(checkerS);
			checkerFinal.addAll(checkerN);
			System.out.println("Checker Final List size : "+checkerFinal.size());
			
			
			Vector<ETokenDTO> tempCheckerEmpty = new Vector<ETokenDTO>();
			Vector<ETokenDTO> tempMakerEmpty = new Vector<ETokenDTO>();
			Vector<ETokenDTO> tempcheckerCounter = new Vector<ETokenDTO>();
			Vector<ETokenDTO> tempMakerCounter = new Vector<ETokenDTO>();
			
			
			for(int i=0;i<makerCounter.size();i++)
			{
				ETokenDTO dto = makerCounter.get(i);
				if("Y".equalsIgnoreCase(dto.getAssigned()))
				{
					int l = dto.getTimeRemaining();
					l = l-1;
					if(l>0)
					dto.setTimeRemaining(l);
					
					else
					{
						dto.setTimeRemaining(2);
					}	
				//	System.out.println(i+" Counter "+dto.getTimeRemaining() );
					makerAssigned.add(dto);
				}
				else
				{
					
					makerEmpty.add(dto);
					
				}
				
				
			}
			
			for(int i=0;i<checkerCounter.size();i++)
			{
				ETokenDTO dto = checkerCounter.get(i);
				if("Y".equalsIgnoreCase(dto.getAssigned()))
				{
					int l = dto.getTimeRemaining();
				
					l = l-1;
					if(l>0){
					dto.setTimeRemaining(l);
					System.out.println(dto.getTimeRemaining()+"After Removing from  Checker Counter : "+dto.getCounterNo());
					}
					else
					{
						dto.setTimeRemaining(2);
					}	
					checkerAssigned.add(dto);
				}
				else
				{
					
					checkerEmpty.add(dto);
					
				}
				
				
			}
			
			for(int j=0;j<makerFinal.size();j++)
			{
				ETokenDTO dto = makerFinal.get(j);
				if(dto.getCounterNo()==null || dto.getCounterNo().equalsIgnoreCase(""))
				{
					tempMakerEmpty.add(dto);
					int l = dto.getTimeRemaining();
					l = l-1;
					if(l>0)
					dto.setTimeRemaining(l);
					else
					{
						dto.setTimeRemaining(2);
					}	
					
				}
			//		System.out.println("Empty size : "+ tempMakerEmpty.size());
				
				
			}
			
			for(int k=0;k<checkerFinal.size();k++)
			{
				ETokenDTO dto = checkerFinal.get(k);
			//	System.out.println("Counter No: "+dto.getCounterNo());
				if(dto.getCounterNo()==null || dto.getCounterNo().equalsIgnoreCase(""))
				{
					tempCheckerEmpty.add(dto);
					int l = dto.getTimeRemaining();
					l = l-1;
					if(l>0)
					dto.setTimeRemaining(l);
					else
					{
						dto.setTimeRemaining(2);
					}
				}
				
			}
			saveObject(objs);
			
			
			
		}
		
		
		
		}
		
		
	}

		
	
}
