package com.wipro.igrs.targeThreshold.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



import com.wipro.igrs.fundTransfer.dao.FundTransferDAO;
import com.wipro.igrs.fundTransfer.form.FundTransferForm;
import com.wipro.igrs.targeThreshold.action.TargetAnnual;
import com.wipro.igrs.targeThreshold.bo.TargetBo;
import com.wipro.igrs.targeThreshold.dao.TargetDao;
import com.wipro.igrs.targeThreshold.dto.TargetDto;
import com.wipro.igrs.targeThreshold.form.TargetTLform;

public class TargetBd
{
	private  Logger logger = 
		(Logger) Logger.getLogger(TargetBd.class);
	public static String RID = null;
	
	 public void getOfficeId(HttpServletRequest request) throws IOException
		{
		 TargetBo bo = null;
			ArrayList setList = new ArrayList();
			HttpSession session = null;
			String officeId = null;
			try
			{
				bo = new TargetBo();
				session = request.getSession();
				
				ArrayList fullList = bo.getOfficeId(request);
				if(fullList!=null)
				{
					for(int i=0;i<fullList.size();i++)
					{
						TargetDto setDto = new TargetDto();
						ArrayList subList = (ArrayList)fullList.get(i);
						setDto.setDroId((String)subList.get(0));
						 officeId = subList.get(0).toString();
						setDto.setDroName((String)subList.get(1));
						setList.add(setDto);
											
					}
				}
				TargetDto dto = new TargetDto();
					dto.setList1(setList);
					session.setAttribute("droIds",dto);
					
					HttpSession sessionDro = request.getSession();
					sessionDro.setAttribute("officeId", officeId);
					
			}
			catch(Exception e)
			{
		        logger.error("this is Exception in getOfficeId in BD" + e);
		        e.printStackTrace();
		    }
			
		}
	 
	 
	 public void getMonthId(HttpServletRequest request,String yearId) throws IOException
		{
		 TargetBo bo = null;
			ArrayList setList = new ArrayList();
			HttpSession session = null;
			try
			{
				bo = new TargetBo();
				session = request.getSession();
				ArrayList fullList = bo.getMonthId(request,yearId);
				if(fullList!=null)
				{
					for(int i=0;i<fullList.size();i++)
					{
						TargetDto setDto = new TargetDto();
						ArrayList subList = (ArrayList)fullList.get(i);
						setDto.setMonthId((String)subList.get(0));
						setDto.setMonthName((String)subList.get(1));
						setList.add(setDto);
											
					}
				}
				TargetDto dto = new TargetDto();
					dto.setList2(setList);
					session.setAttribute("months",dto);
			}
			catch(Exception e)
			{
				logger.error("this is Exception in getMonthId in BD" + e);
				e.printStackTrace();
		    }
					
		}
	 
	 
	 
	 public void getYear(HttpServletRequest request) throws IOException
		{
		 TargetBo bo = null;
			ArrayList setList = new ArrayList();
			HttpSession session = null;
			try
			{
				bo = new TargetBo();
				session = request.getSession();
				
				ArrayList fullList = bo.getYear(request);
				if(fullList!=null)
				{
					for(int i=0;i<fullList.size();i++)
					{
						TargetDto setDto = new TargetDto();
						ArrayList subList = (ArrayList)fullList.get(i);
						setDto.setYearId((String)subList.get(0));
						setDto.setYearName((String)subList.get(1));
						setList.add(setDto);
											
					}
				}
				TargetDto dto = new TargetDto();
					dto.setList3(setList);
					session.setAttribute("years",dto);
			}
			catch(Exception e)
			{
				logger.error("this is Exception in getYear in BD" + e);
				e.printStackTrace();
		    }
					
		}
	 
	 
	 public String getRID()
     {
         return RID;
     }
     
     private String potDgenerator() 
	 {
         String id = "PO" + new Date().getTime();
       //  System.out.println("this is potDgenerator () & value of ID " + id);
         return id;
     }   
	 
	 
	 public boolean targetAnnual(TargetTLform formData) throws IOException
	    {
	    	ArrayList FullList = new ArrayList();
			boolean flag = false;
			TargetBo bo = null;
			try
			{
				//RID = txnDgenerator();
				 bo = new TargetBo();
				 RID = potDgenerator();
								String param1[] = new String[10];
								
								String targetId = RID;
								String officeId = formData.getOfficeName();
								String Fyear = formData.getFinancialYear();
								String rrc = formData.getRrc();
								String revenue = formData.getRevenueTg();
								String stamp = formData.getStampCt();
								String po = formData.getPoInspTarg();
								String sro = formData.getSoInspTarg();
								String dro = formData.getDroInspTarg();
								
								
								param1[0] = targetId;
								param1[1] = officeId;
								param1[2] = Fyear;
								param1[3] = rrc;
								param1[4] = revenue;
								param1[5] = stamp;
								param1[6] = po;
								param1[7] = sro;
								param1[8] = dro;
								param1[9] = "P";
											
								flag = bo.targetAnnual(param1);
							
				 
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return flag;
		}
	 
	 
	 
	 
	 public TargetTLform monthValues(String droId,String yearId) throws Exception
		{
			ArrayList getFullList = new ArrayList();
			TargetTLform fund = null;
			TargetTLform stamp = null;
			try
			{
				fund = new TargetTLform();
				TargetBo bo = new TargetBo();
				ArrayList list1 = bo.monthValues(droId,yearId);
			         
				ArrayList list2 = bo.stampCase(droId,yearId);
				ArrayList rrcMainList = bo.rrcTarget(droId,yearId);
				ArrayList sroMainList = bo.sroTarget(droId,yearId);
				ArrayList droMainList = bo.droTarget(droId,yearId);
				ArrayList poMainList = bo.poTarget(droId,yearId);
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				
							{
							//	fund = new TargetTLform();
								
								ArrayList list =(ArrayList)list1.get(i);
								
								//fund.setOfficeName((String)list.get(0));
								fund.setRt1(getAmount(list));
								fund.setRt2(getAmount(list));
								fund.setRt3(getAmount(list));
								fund.setRt4(getAmount(list));
								fund.setRt5(getAmount(list));
								fund.setRt6(getAmount(list));
								fund.setRt7(getAmount(list));
								fund.setRt8(getAmount(list));
								fund.setRt9(getAmount(list));
								fund.setRt10(getAmount(list));
								fund.setRt11(getAmount(list));
								fund.setRt12(getAmount(list));
								fund.setRtTotal((String)list.get(0));
							
								getFullList.add(fund);
								
							}
				}
				if(list2!=null)
				{
					for(int i=0;i<list2.size();i++)
					{
					//	fund = new TargetTLform();
						
						ArrayList list3 = (ArrayList)list2.get(i);
						fund.setSt1(getStamp(list3));
						fund.setSt2(getStamp(list3));
						fund.setSt3(getStamp(list3));
						fund.setSt4(getStamp(list3));
						fund.setSt5(getStamp(list3));
						fund.setSt6(getStamp(list3));
						fund.setSt7(getStamp(list3));
						fund.setSt8(getStamp(list3));
						fund.setSt9(getStamp(list3));
						fund.setSt10(getStamp(list3));
						fund.setSt11(getStamp(list3));
						fund.setSt12(getStamp(list3));
						fund.setStTotal((String)list3.get(0));
						
						getFullList.add(fund);
					}
				}
				
				
				if(rrcMainList!=null)
				{
					for(int i=0;i<rrcMainList.size();i++)
					{
					//	fund = new TargetTLform();
						
						ArrayList subList = (ArrayList)rrcMainList.get(i);
						fund.setRr1(getRrc(subList));
						fund.setRr2(getRrc(subList));
						fund.setRr3(getRrc(subList));
						fund.setRr4(getRrc(subList));
						fund.setRr5(getRrc(subList));
						fund.setRr6(getRrc(subList));
						fund.setRr7(getRrc(subList));
						fund.setRr8(getRrc(subList));
						fund.setRr9(getRrc(subList));
						fund.setRr10(getRrc(subList));
						fund.setRr11(getRrc(subList));
						fund.setRr12(getRrc(subList));
						fund.setRrTotal((String)subList.get(0));
						
						getFullList.add(fund);
					}
				}
				if(sroMainList!=null)
				{
					for(int i=0;i<sroMainList.size();i++)
					{
						ArrayList subList = (ArrayList)sroMainList.get(i);
						fund.setSn1(getSro(subList));
						fund.setSn2(getSro(subList));
						fund.setSn3(getSro(subList));
						fund.setSn4(getSro(subList));
						fund.setSn5(getSro(subList));
						fund.setSn6(getSro(subList));
						fund.setSn7(getSro(subList));
						fund.setSn8(getSro(subList));
						fund.setSn9(getSro(subList));
						fund.setSn10(getSro(subList));
						fund.setSn11(getSro(subList));
						fund.setSn12(getSro(subList));
						fund.setSnTotal((String)subList.get(0));
						
						getFullList.add(fund);
					}
				}
				if(droMainList!=null)
				{
					for(int i=0;i<droMainList.size();i++)
					{
						ArrayList subList = (ArrayList)droMainList.get(i);
						fund.setDt1(getDro(subList));
						fund.setDt2(getDro(subList));
						fund.setDt3(getDro(subList));
						fund.setDt4(getDro(subList));
						fund.setDt5(getDro(subList));
						fund.setDt6(getDro(subList));
						fund.setDt7(getDro(subList));
						fund.setDt8(getDro(subList));
						fund.setDt9(getDro(subList));
						fund.setDt10(getDro(subList));
						fund.setDt11(getDro(subList));
						fund.setDt12(getDro(subList));
						fund.setDtTotal((String)subList.get(0));
						getFullList.add(fund);
					}
				}
				if(poMainList!=null)
				{
					for(int i=0;i<poMainList.size();i++)
					{
						ArrayList subList = (ArrayList)poMainList.get(i);
						fund.setPo1(getPo(subList));
						fund.setPo2(getPo(subList));
						fund.setPo3(getPo(subList));
						fund.setPo4(getPo(subList));
						fund.setPo5(getPo(subList));
						fund.setPo6(getPo(subList));
						fund.setPo7(getPo(subList));
						fund.setPo8(getPo(subList));
						fund.setPo9(getPo(subList));
						fund.setPo10(getPo(subList));
						fund.setPo11(getPo(subList));
						fund.setPo12(getPo(subList));
						fund.setPoTotal((String)subList.get(0));
						getFullList.add(fund);
					}
				}
				
				
				
				
			}
			catch(Exception e)
			{
				logger.error("this is Exception in monthValues in BD" + e);
				e.printStackTrace();
		    }
			
			return fund;
			
		}
	 
	 
	  
	 public String getAmount(ArrayList a1)
	 {
		 String revenue = a1.get(0).toString();
		 float i = Float.parseFloat(revenue);
		 float j = i/12;
		 
		 DecimalFormat df = new DecimalFormat("0.##");
         double k=Double.parseDouble(df.format(j));
         String amount = Double.toString(k);
         
                
	/*  //   String k = Float.toString(j);
	     System.out.println("String length is ----------------"+k.length());
	     int index = k.indexOf(".");
	     System.out.println("In String . post is =============="+index);
		 
	   //  index = index+2;
	  //   String sub = k.substring(0,index);
	     System.out.println("In String . post is =============="+index);
		 
		 float amount2 = Math.round(j);
		 double amount22 = Math.ceil(j);
		 double amount33 = Math.floor(j);
		 System.out.println("i/12 value is ========================="+j);
		 System.out.println("math.round is =========================="+amount2);
		 System.out.println("Math.floor is =========================="+amount33);
		 System.out.println("Math.ceil is =========================="+amount22);
		 String amount = Float.toString(amount2);
		 											*/
		 return amount;
	 }
	 
	 public String getStamp(ArrayList a2)
	 {
		 String stamp1 = a2.get(0).toString();
		 float i = Float.parseFloat(stamp1);
		 float j = i/12;
		 
		 DecimalFormat df = new DecimalFormat("0.##");
         double k=Double.parseDouble(df.format(j));
          String amount = Double.toString(k);
         
		 //float stamp2 = Math.round(j);
		 //String stamp3 = Float.toString(stamp2);
		 return amount;
	 }
	 public String getRrc(ArrayList a3)
	 {
		 String rrc1 = a3.get(0).toString();
		 float i = Float.parseFloat(rrc1);
		 float j = i/12;
		 
		 DecimalFormat df = new DecimalFormat("0.##");
         double k=Double.parseDouble(df.format(j));
         String amount = Double.toString(k);
		// float rrc2 = Math.round(j);
		// String rrc3 = Float.toString(rrc2);
		 return amount;
		 
	 }
	 public String getSro(ArrayList a4)
	 {
		 String sro1 = a4.get(0).toString();
		 float i = Float.parseFloat(sro1);
		 float j = i/12;
		 
		 DecimalFormat df = new DecimalFormat("0.##");
         double k=Double.parseDouble(df.format(j));
         String amount = Double.toString(k);
		// String sro2 = Float.toString(j);
		 return amount;
	 }
	 public String getDro(ArrayList a5)
	 {
		 String dro1 = a5.get(0).toString();
		 float i = Float.parseFloat(dro1);
		 float j = i/12;
		 
		 DecimalFormat df = new DecimalFormat("0.##");
         double k=Double.parseDouble(df.format(j));
        String amount = Double.toString(k);
		// String dro2 = Float.toString(j);
		 return amount;
	 }
	 public String getPo(ArrayList a6)
	 {
		 String po1 = a6.get(0).toString();
		 float i = Float.parseFloat(po1);
		 float j = i/12;
		 
		 DecimalFormat df = new DecimalFormat("0.##");
         double k=Double.parseDouble(df.format(j));
         String amount = Double.toString(k);
		 return amount;
	 }
	 
	 
	 
	 public boolean monthData(HttpServletRequest request,TargetTLform data,String yearId,String droId) throws SQLException
		{
			boolean flag = false;
			TargetBo bo = null;
			String approve = null;
			try
			{
				RID = potDgenerator();
				bo = new TargetBo();
				String[] param1 = new String[10];
					
				String targetId = RID;
				//String officeId = data.getOfficeName();
				//String finYear = data.getFinancialYear();
				//String month = data.getMonth();
				
				String revenue = data.getRtTotal();
				String stamp = data.getStTotal();
				String rrc = data.getRrTotal();
				String sro = data.getSnTotal();
				String dro = data.getDtTotal();
				String po = data.getPoTotal();
				String app = data.getApproveOrReject();
				if(app.equals("Approve"))
				{
					approve = "A";
				}
				else
				{
					approve ="R";
				}
				
				HttpSession sessionDro = request.getSession();
				//String officeIdNew = (String)sessionDro.getAttribute("officeId");
				
				
				param1[0] = targetId;
				param1[1] = droId;
				param1[2] = yearId;
				param1[3] = revenue;
				param1[4] = stamp;
				param1[5] = rrc;
				param1[6] = sro;
				param1[7] = dro;
				param1[8] = po;
				param1[9] = approve;
				
				flag = bo.monthData(param1); 
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
		
			}
			return flag;
			
		}
	    
	 
	 
	 public  ArrayList annualView() throws SQLException
		{
			ArrayList mainList = new ArrayList();
			
			try
			{
				TargetBo bo = new TargetBo();
				ArrayList list1 = bo.annualView();
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				{
					TargetTLform fund = new TargetTLform();
					ArrayList list =(ArrayList)list1.get(i);
					
							fund.setFinancialYear((String)list.get(0));
							fund.setOfficeName((String)list.get(1));
							fund.setRevenueTg((String)list.get(2));
							fund.setStampCt((String)list.get(3));
							fund.setRrc((String)list.get(4));
							fund.setSoInspTarg((String)list.get(5));
							fund.setDroInspTarg((String)list.get(6));
							fund.setPoInspTarg((String)list.get(7));
							fund.setTargetId((String)list.get(8));
							//fund.setStatus((String)list.get(9));
							fund.setStatus(getStatus(list));
							
					mainList.add(fund);
				}
			  }
			}
			catch(Exception e)
			{
				logger.error("this is Exception in annualView in BD" + e);
				e.printStackTrace();
		    }
			return mainList;
		}
	 
	 public String getStatus(ArrayList list)
	 {
	     String str = (String) list.get(9);
	     String status = null;
	     if(str.equals("A"))
	     {
		status = "Approved";
	     }
	     if(str.equals("R"))
	     {
		 status = "Rejected";
	     }
	     if(str.equals("P"))
	     {
		 status = "Pending";
	     }
	     return status;
	 }
	 
	 		
	 public TargetTLform AnnualDetails(HttpServletRequest request,String targetId) throws Exception
		{
			ArrayList getFullList = new ArrayList();
			TargetTLform fund = null;
			try
			{
				TargetBo bo = new TargetBo();
				ArrayList list1 = bo.AnnualDetails(targetId);
				
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				{
				 fund = new TargetTLform();
					ArrayList list =(ArrayList)list1.get(i);
					
							fund.setFinancialYear((String)list.get(0));
							fund.setOfficeName((String)list.get(1));
							fund.setRevenueTg((String)list.get(2));
							fund.setStampCt((String)list.get(3));
							fund.setRrc((String)list.get(4));
							fund.setSoInspTarg((String)list.get(5));
							fund.setDroInspTarg((String)list.get(6));
							fund.setPoInspTarg((String)list.get(7));
							fund.setTargetId((String)list.get(8));
							getFullList.add(fund);
				}
			  }
				
				
			}
			catch(Exception e)
			{
				logger.error("this is Exception in AnnualDetails in BD" + e);
				e.printStackTrace();
		    }
			
			return fund;
			
		}
	 
	 
	 
	 public boolean fundApprove(TargetTLform data) throws SQLException
		{
			boolean flag = false;
			String approve = null;
			try
			{
				TargetBo bo = new TargetBo();
				String[] param1 = new String[8];
			//	String[] param2 = new String[5];
				String rrc = data.getRrc();
				String revenue = data.getRevenueTg();
				String stamp = data.getStampCt();
				String po = data.getPoInspTarg();
				String sro = data.getSoInspTarg();
				String dro = data.getDroInspTarg();
				String approveOrReject = data.getApproveOrReject();
				if(approveOrReject.endsWith("Approve"))
				{
					 approve = "A";
				}
				else
				{
					 approve = "R";
				}
				String targetId = data.getTargetId();
						param1[0] = rrc;
						param1[1] = revenue;
						param1[2] = stamp;
						param1[3] = po;
						param1[4] = sro;
						param1[5] = dro;
						param1[6] = approve;
						param1[7] = targetId;
						
						flag = bo.fundApprove2(param1); 
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return flag;
			
		}
	 
	 
	 
	 public  ArrayList monthViewList() throws SQLException
		{
				ArrayList mainList = new ArrayList();
			
			try
			{
				TargetBo bo = new TargetBo();
				ArrayList list1 = bo.monthViewList();
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				{
					TargetTLform fund = new TargetTLform();
					ArrayList list =(ArrayList)list1.get(i);
					
							fund.setFinancialYear((String)list.get(0));
							fund.setOfficeName((String)list.get(1));
							fund.setRevenueTg((String)list.get(2));
							fund.setStampCt((String)list.get(3));
							fund.setRrc((String)list.get(4));
							fund.setSoInspTarg((String)list.get(5));
							fund.setDroInspTarg((String)list.get(6));
							fund.setPoInspTarg((String)list.get(7));
							fund.setTargetId((String)list.get(8));
							//fund.setStatus((String)list.get(9));
							fund.setStatus(getStatus(list));
							
					mainList.add(fund);
				}
			  }
			}
			catch(Exception e)
			{
				logger.error("this is Exception in monthViewList in BD" + e);
				e.printStackTrace();
		    }
			return mainList;
		}
	 
	 

}
