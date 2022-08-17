package com.wipro.igrs.report.action;


import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class JasperApplet extends Applet implements ActionListener
{
	public JasperApplet()
	{
		
	}
	public void init()
	{
		Button b = new Button("submit");
		b.addActionListener(this);
		add(b);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		Button button=(Button)ae.getSource();
		if(button.getLabel()=="submit")
		{
			try
			{
				String s1="3ab05b286bcb4fd0b8418e47d706754c";
		
				Class.forName ("oracle.jdbc.driver.OracleDriver");
				Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@10.125.243.27:1521:igrsuat", "igrssit", "igrssit");
				System.out.println("Connection  Established");
				
				Map  jasperParameter = new  HashMap();
				jasperParameter.put("Result_Id",s1);
				 
				String path = "D://PDF//SATBLE INIT JRXMLS//sample.jrxml";
				net.sf.jasperreports.engine.design.JasperDesign jDesign = net.sf.jasperreports.engine.xml.JRXmlLoader.load(path);
				System.out.println(path);
				JasperReport jReport = net.sf.jasperreports.engine.JasperCompileManager.compileReport(jDesign);
	
				JasperPrint jasperPrint =  JasperFillManager.fillReport(jReport, jasperParameter,  conn);
				System.out.println(jasperPrint);
				
				JasperViewer.viewReport(jasperPrint);
				
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	
	}
		
}
