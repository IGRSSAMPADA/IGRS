package com.wipro.igrs.report.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.report.dto.ReportDTO;
import com.wipro.igrs.report.sql.PendingCourtCasesSQL;

public class PendingReportDAO {
	
	
	
	
	DBUtility dbutility;
	
	private static final Logger logger = Logger.getLogger(ReportDAO.class);

	public PendingReportDAO() {
		
	}

	//added by Rachita
	public ArrayList getSearchPendingCourtReport(String froDate,String toDate){
		logger.debug("in getSearchReqReport dao block start");
		ArrayList ret=new ArrayList();
		ArrayList ret1=new ArrayList();
		ArrayList ret2=new ArrayList();
		ArrayList retList=new ArrayList();
		String sql1="";
		String sql2="";
		
		try{
			String param[]={froDate,toDate};
			dbutility = new DBUtility();
			String sql = PendingCourtCasesSQL.PENDING_COURT_CASES_DETAILS;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			ret=dbutility.executeQuery(param);
			sql1=PendingCourtCasesSQL.PENDING_COURT_CASES_DETAILS1;
			sql2=PendingCourtCasesSQL.PENDING_COURT_CASES_DETAILS2;
			logger.debug("sql query====" + sql1);
			System.out.println("list size"+ret.size());
			for(int i=0;i<ret.size();i++){
				ArrayList retloop=new ArrayList();
				ArrayList retloop1=new ArrayList();
				ArrayList retsub=(ArrayList)ret.get(i);
				String param1[]={(String)retsub.get(6)};
				dbutility.createPreparedStatement(sql1);
				retloop=dbutility.executeQuery(param1);
				String param2[]={(String)retsub.get(1)};
				dbutility.createPreparedStatement(sql2);
				retloop1=dbutility.executeQuery(param2);
				ret1.add(retloop);
				ret2.add(retloop1);
			System.out.println("remarks=============================="+retloop1.get(0));
			//System.out.println("stay order=============================="+ret.get(9));
			}
		retList.add(ret);
		retList.add(ret1);
		retList.add(ret2);
		}catch (Exception err) {

			logger.error("this is Exception in " + "getSearchReqReport DAO: "
					+ err);
		}
		return retList;
	}
	
	//added by Rachita for cess municipal
	public ArrayList getSearchCessMunicipalReport(String fromMonth,String fromYear,String toMonth,String toYear){
		logger.debug("in getSearchCessMunicipalReport dao block start");
		// for monthly data datalist
		ArrayList ret=new ArrayList();
		ArrayList ret1=new ArrayList();
		// for data list 1 Duration data
		ArrayList ret2=new ArrayList();
		ArrayList ret3=new ArrayList();
		ArrayList retList=new ArrayList();
		
		String sql2="";
		System.out.println("year============================="+fromYear);
		try{
			//String param[]={month,year};
			String p=fromMonth+"/"+fromYear;
			// for data list2
			String p1=toMonth+"/"+toYear;
			System.out.println("p============================="+p);
			System.out.println("p1============================="+p1);
			String param[]={p};
	// for datalist2
			String param1[]={p,p1};
			dbutility = new DBUtility();
			String sql = PendingCourtCasesSQL.CESS_MUNICIPAL_CORPORATION_DUTY_MONTH1;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			ret=dbutility.executeQuery(param);
			//ret=dbutility.executeQuery(param);
			// for datalist2
			String sql1 = PendingCourtCasesSQL.CESS_MUNICIPAL_CORPORATION_DUTY_DURATION1;
			logger.debug("sql query====" + sql1);
			dbutility.createPreparedStatement(sql1);
			ret2=dbutility.executeQuery(param1);
			
			
			System.out.println("list size datalist"+ret.size());
			System.out.println("list size datalist 2"+ret2.size());
			//rachita comment
			for(int i=0;i<ret.size();i++){
				ArrayList reportsub=(ArrayList)ret.get(i);
				//ArrayList ownList1=(ArrayList)reportsub.get(i);
				String a=(String)reportsub.get(0);
				String b=(String)reportsub.get(1);
				String c=(String)reportsub.get(2);
				String d=(String)reportsub.get(3);
				String e=(String)reportsub.get(4);
				String f=(String)reportsub.get(5);
				String g=(String)reportsub.get(6);
				System.out.println("value 1================"+a);
				System.out.println("value 1================"+b);
				System.out.println("value 1================"+c);
				System.out.println("value 1================"+d);
				System.out.println("value 1================"+e);
				System.out.println("value 1================"+f);
				System.out.println("value 1================"+g);
				//System.out.println("value 2================"+ownList1.get(1));
				//System.out.println("value 3================"+ownList1.get(2));
				//System.out.println("value 4================"+ownList1.get(4));
			}
			
			// for datalist2
			for(int i=0;i<ret2.size();i++){
				ArrayList reportsub1=(ArrayList)ret2.get(i);
				//ArrayList ownList1=(ArrayList)reportsub.get(i);
				String a=(String)reportsub1.get(0);
				String b=(String)reportsub1.get(1);
				String c=(String)reportsub1.get(2);
				String d=(String)reportsub1.get(3);
				String e=(String)reportsub1.get(4);
				String f=(String)reportsub1.get(5);
				String g=(String)reportsub1.get(6);
				System.out.println("list2 value 1================"+a);
				System.out.println("list2 value 1================"+b);
				System.out.println("list2 value 1================"+c);
				System.out.println("list2 value 1================"+d);
				System.out.println("list2 value 1================"+e);
				System.out.println("list2 value 1================"+f);
				System.out.println("list2 value 1================"+g);
				//System.out.println("value 2================"+ownList1.get(1));
				//System.out.println("value 3================"+ownList1.get(2));
				//System.out.println("value 4================"+ownList1.get(4));
			}
			
				//rachita comment
			/*for(int i=0;i<ret.size();i++){
				//ArrayList retloop=new ArrayList();
				//ArrayList retloop1=new ArrayList();
				ArrayList retsub=(ArrayList)ret.get(i);
				String param1[]={(String)retsub.get(6)};
				dbutility.createPreparedStatement(sql1);
				retloop=dbutility.executeQuery(param1);
				String param2[]={(String)retsub.get(1)};
				dbutility.createPreparedStatement(sql2);
				retloop1=dbutility.executeQuery(param2);
				ret1.add(retloop);
				ret2.add(retloop1);
			System.out.println("remarks=============================="+retloop1.get(0));
			//System.out.println("stay order=============================="+ret.get(9));
			}*/
			
		retList.addAll(ret);
		retList.addAll(ret2);
		System.out.println("final list================="+retList.size());
	//	retList.add(ret1);
	//	retList.add(ret2);
		}catch (Exception err) {

			logger.error("this is Exception in " + "getSearchCessMunicipalReport DAO: "
					+ err);
		}
	//	return ret;
		return retList;
	}
	
/*public ArrayList getSearchCessMunicipalReport1(String fromMonth,String fromYear,String toMonth,String toYear){
		logger.debug("in getSearchCessMunicipalReport dao block start");
		ArrayList ret=new ArrayList();
		ArrayList ret1=new ArrayList();
	//	ArrayList ret2=new ArrayList();
		ArrayList retList=new ArrayList();
		String sql1="";
		String sql2="";
		System.out.println("toYear============================="+toYear);
		System.out.println("fromYear============================="+fromYear);
		System.out.println("toMonth============================="+toMonth);
		System.out.println("FromMonth============================="+fromMonth);
		try{
			//String param[]={month,year};
			String p=fromMonth+"/"+fromYear;
			//check toYear and toMonth values interchange hoke aa rahi h
			String p1=toYear+"/"+toMonth;
			System.out.println("p============================="+p);
			System.out.println("p1============================="+p1);
			String param[]={p,p1};
			dbutility = new DBUtility();
			String sql = PendingCourtCasesSQL.CESS_MUNICIPAL_CORPORATION_DUTY_DURATION1;
			logger.debug("sql query====" + sql);
			dbutility.createPreparedStatement(sql);
			ret=dbutility.executeQuery(param);
			//sql1=PendingCourtCasesSQL.PENDING_COURT_CASES_DETAILS1;
			//sql2=PendingCourtCasesSQL.PENDING_COURT_CASES_DETAILS2;
			//logger.debug("sql query====" + sql1);
			System.out.println("list size"+ret.size());
			//rachita comment
			for(int i=0;i<ret.size();i++){
				ArrayList reportsub=(ArrayList)ret.get(i);
				//ArrayList ownList1=(ArrayList)reportsub.get(i);
				String a=(String)reportsub.get(0);
				String b=(String)reportsub.get(1);
				String c=(String)reportsub.get(2);
				String d=(String)reportsub.get(3);
				String e=(String)reportsub.get(4);
				String f=(String)reportsub.get(5);
				String g=(String)reportsub.get(6);
				System.out.println("value 1================"+a);
				System.out.println("value 1================"+b);
				System.out.println("value 1================"+c);
				System.out.println("value 1================"+d);
				System.out.println("value 1================"+e);
				System.out.println("value 1================"+f);
				System.out.println("value 1================"+g);
				//System.out.println("value 2================"+ownList1.get(1));
				//System.out.println("value 3================"+ownList1.get(2));
				//System.out.println("value 4================"+ownList1.get(4));
			}
				//rachita comment
			for(int i=0;i<ret.size();i++){
				//ArrayList retloop=new ArrayList();
				//ArrayList retloop1=new ArrayList();
				ArrayList retsub=(ArrayList)ret.get(i);
				String param1[]={(String)retsub.get(6)};
				dbutility.createPreparedStatement(sql1);
				retloop=dbutility.executeQuery(param1);
				String param2[]={(String)retsub.get(1)};
				dbutility.createPreparedStatement(sql2);
				retloop1=dbutility.executeQuery(param2);
				ret1.add(retloop);
				ret2.add(retloop1);
			System.out.println("remarks=============================="+retloop1.get(0));
			//System.out.println("stay order=============================="+ret.get(9));
			}
		retList.add(ret);
	//	retList.add(ret1);
	//	retList.add(ret2);
		}catch (Exception err) {

			logger.error("this is Exception in " + "getSearchCessMunicipalReport DAO: "
					+ err);
		}
		return ret;
	}
	*/
	
	//For Comparative report
	
	public synchronized ArrayList getDistrictList() throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbutility = new DBUtility();
			dbutility.createStatement();
			list = dbutility.executeQuery(PendingCourtCasesSQL.DISTRICT_QRY);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbutility != null) {
					 dbutility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}

	//Added by rachita
	//Added by rachita
	public void insertComparitive(String FINANCIAL_YEAR,String JANUARY,String FEBRUARY,String MARCH,
			String APRIL,String MAY,String JUNE,String JULY,String AUGUST,String SEPTEMBER,String OCTOBER,
			String NOVEMBER,String DECEMBER,String CREATEDBY,String CREATEDDATE,String UPDATEDBY
	        ,String UPDATEDDATE,String DISTRICT,String JANUARYC,String FEBRUARYC,String MARCHC,
            String APRILC,String MAYC,String JUNEC,String JULYC,String AUGUSTC,String SEPTEMBERC,String OCTOBERC,
            String NOVEMBERC,String DECEMBERC)
	{
		
		try {
			dbutility = new DBUtility();
			String	sql = PendingCourtCasesSQL.INSERT_COMPARATIVE;
			logger.debug("SQL Query :-"+sql);
			
			String arr[] = {FINANCIAL_YEAR,JANUARY,FEBRUARY,MARCH,
	                APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,
	                NOVEMBER,DECEMBER,CREATEDBY,UPDATEDBY,DISTRICT,JANUARYC,FEBRUARYC,MARCHC,
	                APRILC,MAYC,JUNEC,JULYC,AUGUSTC,SEPTEMBERC,OCTOBERC,
	                NOVEMBERC,DECEMBERC};
			dbutility.createPreparedStatement(sql);
			dbutility.executeUpdate(arr);
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {
				logger.error(err);
			}
		}
		logger.debug("in insertComparitive dao block end");
		
	}
	
	
	
	//Added by rachita
	public void updateComparitive(String FINANCIAL_YEAR,String JANUARY,String FEBRUARY,String MARCH,
			String APRIL,String MAY,String JUNE,String JULY,String AUGUST,String SEPTEMBER,String OCTOBER,
			String NOVEMBER,String DECEMBER,String UPDATEDBY
	        ,String UPDATEDDATE,String DISTRICT,String JANUARYC,String FEBRUARYC,String MARCHC,
            String APRILC,String MAYC,String JUNEC,String JULYC,String AUGUSTC,String SEPTEMBERC,String OCTOBERC,
            String NOVEMBERC,String DECEMBERC)
	{
		boolean b = false;
		try {
			dbutility = new DBUtility();
			String	sql = PendingCourtCasesSQL.UPDATE_COMPARATIVE;
			logger.debug("SQL Query :-"+sql);
			
			String arr[] = {JANUARY,FEBRUARY,MARCH,
	                APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,
	                NOVEMBER,DECEMBER,UPDATEDBY,JANUARYC,FEBRUARYC,MARCHC,APRILC,MAYC,JUNEC,
	                JULYC,AUGUSTC,SEPTEMBERC,OCTOBERC,NOVEMBERC,DECEMBERC,DISTRICT,FINANCIAL_YEAR};
			dbutility.createPreparedStatement(sql);
			b=	dbutility.executeUpdate(arr);
			System.out.println("boolean================"+b);
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {
				logger.error(err);
			}
		}
		logger.debug("in UPDATEComparitive dao block end");
		
	}
	
	
	//Added by rachita
	//Added by rachita
	public ArrayList viewComparitive(String DISTRICT,String FINANCIAL_YEAR)
	{
		ArrayList view = new ArrayList();
		try {
			dbutility = new DBUtility();
			String	sql = PendingCourtCasesSQL.VIEW_COMPARATIVE;
			logger.debug("SQL Query :-"+sql);
			String arr[] = {DISTRICT,FINANCIAL_YEAR};
			dbutility.createPreparedStatement(sql);
			view=dbutility.executeQuery(arr);
			/*if(view.equals(null))
			{
				
			}*/
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {
				logger.error(err);
			}
		}
		logger.debug("in insertComparitive dao block end");
		return view;
		
		
	}
	
	//Added by rachita for create target
	public ArrayList viewCreateTarget(String DISTRICT,String FINANCIAL_YEAR)
	{
		ArrayList view = new ArrayList();
		try {
			dbutility = new DBUtility();
			String	sql = PendingCourtCasesSQL.VIEW_CREATE_TARGET;
			logger.debug("SQL Query :-"+sql);
			String arr[] = {DISTRICT,FINANCIAL_YEAR};
			dbutility.createPreparedStatement(sql);
			view=dbutility.executeQuery(arr);
			/*if(view.equals(null))
			{
				
			}*/
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {
				logger.error(err);
			}
		}
		logger.debug("in viewCreateTarget dao block end");
		return view;
		
		
	}
	
	
	public ArrayList viewComparitiveFigures(String fYearAdd,String tYearAdd,String DISTRICT)
	{ 
		ArrayList view = new ArrayList();
		try {
			String FINANCIAL_YEAR="2013-2014";
			dbutility = new DBUtility();
			String	sql = PendingCourtCasesSQL.VIEW_COMPARATIVE_FIGURES;
			logger.debug("SQL Query :-"+sql);
			String arr[] = {fYearAdd,tYearAdd,DISTRICT};
			dbutility.createPreparedStatement(sql);
			view=dbutility.executeQuery(arr);
			/*if(view.equals(null))
			{
				
			}*/
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {
				logger.error(err);
			}
		}
		logger.debug("in viewComparitiveFigures dao block end");
		return view;
		
		
	}
	
	
	public ArrayList viewProgAchivement(String fYearAdd,String tYearAdd,String DISTRICT)
	{ 
		ArrayList view = new ArrayList();
		try {
			
			dbutility = new DBUtility();
			String	sql = PendingCourtCasesSQL.VIEW_PROG_ACHIVEMENT;
			logger.debug("SQL Query :-"+sql);
			String arr[] = {DISTRICT,"APRIL/"+fYearAdd,"MARCH/"+tYearAdd,DISTRICT,"APRIL/"+fYearAdd,"MARCH/"+tYearAdd};
			dbutility.createPreparedStatement(sql);
			view=dbutility.executeQuery(arr);
			
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {
				logger.error(err);
			}
		}
		logger.debug("in viewComparitiveFigures dao block end");
		return view;
		
		
	}
	
	
	//added by SHreeraJ

	public ArrayList getRevRcptMP(ArrayList yearList,String dist,String lang)
	{ 
		ReportDTO dto = new ReportDTO();
		ArrayList views = new ArrayList();
		ArrayList list=new ArrayList();
		ArrayList totTargetList=new ArrayList();
		double totalTarget=0.0;
		
		try {
			
			dbutility = new DBUtility();
			
				
				for(int j=0;j<yearList.size();j++){
				//	String month=(String)view.get(i);
					
					ReportDTO rDTO=(ReportDTO)yearList.get(j);
					String year=(String)rDTO.getYears();
					
					String	sql = PendingCourtCasesSQL.VIEW_CUMULATIVE_COMPARATIVE_FIGURES;
					logger.debug("SQL Query :-"+sql);
					String arr[] = {year,dist};
					dbutility.createPreparedStatement(sql);
					ArrayList view=dbutility.executeQuery(arr);
				/*	if(view!=null && view.size()>0)
					views=(ArrayList)view.get(0);*/
					String fromYear=year.split("-")[0];
					String toYear=year.split("-")[1];
					String	sqlProg = PendingCourtCasesSQL.VIEW_PROGRESSIVE_ACHIEVEMENT;
					String arrProg[] = {dist,"APRIL/"+fromYear,"MARCH/"+toYear,dist,"APRIL/"+fromYear,"MARCH/"+toYear};
					dbutility.createPreparedStatement(sqlProg);
					ArrayList viewsProgressive=dbutility.executeQuery(arrProg);
					System.out.println(viewsProgressive.size());
				
					if(view!=null && view.size()>0){
						//for(int i=0;i<viewsProgressive.size();i++){
							ArrayList subList=(ArrayList)view.get(0);						
							
							dto.setAprTarget(subList.get(0).toString());
							dto.setMayTarget(subList.get(1).toString());
							dto.setJunTarget(subList.get(2).toString());
							dto.setJulTarget(subList.get(3).toString());
							dto.setAugTarget(subList.get(4).toString());
							dto.setSepTarget(subList.get(5).toString());
							dto.setOctTarget(subList.get(6).toString());
							dto.setNovTarget(subList.get(7).toString());
							dto.setDecTarget(subList.get(8).toString());
							dto.setJanTarget(subList.get(9).toString());
							dto.setFebTarget(subList.get(10).toString());
							dto.setMarTarget(subList.get(11).toString());
							
						
						//}
					}else{
						dto.setJanTarget("0.0");
						dto.setFebTarget("0.0");
						dto.setMarTarget("0.0");
						dto.setAprTarget("0.0");
						dto.setMayTarget("0.0");
						dto.setJunTarget("0.0");
						dto.setJulTarget("0.0");
						dto.setAugTarget("0.0");
						dto.setSepTarget("0.0");
						dto.setOctTarget("0.0");
						dto.setNovTarget("0.0");
						dto.setDecTarget("0.0");
						
					}
					dto.setYear(year);
					if("en".equalsIgnoreCase(lang))
					dto.setExemName("Progressive Target (in rupees)");
					else
					dto.setExemName("प्रगतिशील लक्ष्य (रूपये में)");	
					list.add(dto);
					dto=new ReportDTO();
					/*String[] array=new String[viewsProgressive.size()];
					array=(String[])viewsProgressive.toArray(array);*/
					if(viewsProgressive!=null && viewsProgressive.size()>0){
						
					StringBuilder listString=new StringBuilder();
					
					for(Object obj : viewsProgressive){
						String s=obj.toString();
						listString.append(s.substring(1, s.length()-1));
						listString.append(",");
					}
					if(listString.length()!=0){
						String subString=listString.toString();
						String subListString=subString.substring(0, subString.length()-1);
						String[] abc=subListString.split(",");
						
						//for month
						String [] mn=new String[abc.length/2];
						for(int m = 0,p=0; m<abc.length-1;p++){
							mn[p]=abc[m];
							m+=2;
						}
						
						//for data
						String dt[]=new String[abc.length/2];
						for(int m = 1,p=0; m<abc.length;p++){
							dt[p]=abc[m];
							m+=2;
						}
						
						for(int sh=0;sh<mn.length;sh++){
							
							String num = mn[sh];
							if("01".equals(num)){
								dto.setJanTarget(dt[sh]);
							}
							if("02".equals(num)){
								dto.setFebTarget(dt[sh]);
							}
							if("03".equals(num)){
								dto.setMarTarget(dt[sh]);
							}
							if("04".equals(num)){
								dto.setAprTarget(dt[sh]);
							}
							if("05".equals(num)){
								dto.setMayTarget(dt[sh]);
							}
							if("06".equals(num)){
								dto.setJunTarget(dt[sh]);
							}
							if("07".equals(num)){
								dto.setJulTarget(dt[sh]);
							}
							if("08".equals(num)){
								dto.setAugTarget(dt[sh]);
							}
							if("09".equals(num)){
								dto.setSepTarget(dt[sh]);
							}
							if("10".equals(num)){
								dto.setOctTarget(dt[sh]);
							}
							if("11".equals(num)){
								dto.setNovTarget(dt[sh]);
							}
							if("12".equals(num)){
								dto.setDecTarget(dt[sh]);
							}
						}
					
					}
						
					
					}else{
						dto.setJanTarget("0.0");
						dto.setFebTarget("0.0");
						dto.setMarTarget("0.0");
						dto.setAprTarget("0.0");
						dto.setMayTarget("0.0");
						dto.setJunTarget("0.0");
						dto.setJulTarget("0.0");
						dto.setAugTarget("0.0");
						dto.setSepTarget("0.0");
						dto.setOctTarget("0.0");
						dto.setNovTarget("0.0");
						dto.setDecTarget("0.0");
					}
					dto.setYear(year);		
					if("en".equalsIgnoreCase(lang))
					dto.setExemName("Progressive Achievement (in rupees)");
					else 
					dto.setExemName(" प्रगतिशील उपलब्धि(रूपये में)");
					list.add(dto);
				    dto=new ReportDTO();
				    
				    
					if(list!=null && list.size()>=2){
					ReportDTO r=(ReportDTO)list.get(list.size()-2);
					ReportDTO r1=(ReportDTO)list.get(list.size()-1);
					double janTarget=Double.parseDouble(r.getJanTarget());
					double febTarget=Double.parseDouble(r.getFebTarget());
					double marTarget=Double.parseDouble(r.getMarTarget());
					double aprTarget=Double.parseDouble(r.getAprTarget());
					double mayTarget=Double.parseDouble(r.getMayTarget());
					double junTarget=Double.parseDouble(r.getJunTarget());
					double julTarget=Double.parseDouble(r.getJulTarget());
					double augTarget=Double.parseDouble(r.getAugTarget());
					double sepTarget=Double.parseDouble(r.getSepTarget());
					double octTarget=Double.parseDouble(r.getOctTarget());
					double novTarget=Double.parseDouble(r.getNovTarget());
					double decTarget=Double.parseDouble(r.getDecTarget());
					
					
					double janTargetAch=Double.parseDouble(r1.getJanTarget());
					double febTargetAch=Double.parseDouble(r1.getFebTarget());
					double marTargetAch=Double.parseDouble(r1.getMarTarget());
					double aprTargetAch=Double.parseDouble(r1.getAprTarget());
					double mayTargetAch=Double.parseDouble(r1.getMayTarget());
					double junTargetAch=Double.parseDouble(r1.getJunTarget());
					double julTargetAch=Double.parseDouble(r1.getJulTarget());
					double augTargetAch=Double.parseDouble(r1.getAugTarget());
					double sepTargetAch=Double.parseDouble(r1.getSepTarget());
					double octTargetAch=Double.parseDouble(r1.getOctTarget());
					double novTargetAch=Double.parseDouble(r1.getNovTarget());
					double decTargetAch=Double.parseDouble(r1.getDecTarget());
					
					
					
					if (janTarget!=0.0){
						janTarget=((janTarget-janTargetAch)/janTarget)*100;
						dto.setJanTarget((String.valueOf(BigDecimal.valueOf(janTarget).toPlainString()).concat("%")));
					}
					
					if (febTarget!=0.0){
						febTarget=Math.round((((febTarget-febTargetAch)/febTarget)*100));
						dto.setFebTarget(String.valueOf(BigDecimal.valueOf(febTarget).toPlainString()).concat("%"));
					}
					
					if (marTarget!=0.0){
						marTarget=Math.round((((marTarget-marTargetAch)/marTarget)*100));
						dto.setMarTarget(String.valueOf(BigDecimal.valueOf(marTarget).toPlainString()).concat("%"));
					}
					
					if (aprTarget!=0.0){
						aprTarget=Math.round((((aprTarget-aprTargetAch)/aprTarget)*100));
						dto.setAprTarget(String.valueOf(BigDecimal.valueOf(aprTarget).toPlainString()).concat("%"));
					}
					
					if (mayTarget!=0.0){
						mayTarget=Math.round((((mayTarget-mayTargetAch)/mayTarget)*100));
						dto.setMayTarget(String.valueOf(BigDecimal.valueOf(mayTarget).toPlainString()).concat("%"));
					}
					
					if (junTarget!=0.0){
						junTarget=Math.round((((junTarget-junTargetAch)/junTarget)*100));
						dto.setJunTarget(String.valueOf(BigDecimal.valueOf(junTarget).toPlainString()).concat("%"));
					}
					
					if (julTarget!=0.0){
						julTarget=Math.round((((julTarget-julTargetAch)/julTarget)*100));
						dto.setJulTarget(String.valueOf(BigDecimal.valueOf(julTarget).toPlainString()).concat("%"));
					}
					
					if (augTarget!=0.0){
						augTarget=Math.round((((augTarget-augTargetAch)/augTarget)*100));
						dto.setAugTarget(String.valueOf(BigDecimal.valueOf(augTarget).toPlainString()).concat("%"));
					}
					
					if (sepTarget!=0.0){
						sepTarget=Math.round((((sepTarget-sepTargetAch)/sepTarget)*100));
						dto.setSepTarget(String.valueOf(BigDecimal.valueOf(sepTarget).toPlainString()).concat("%"));
					}
					
					if (octTarget!=0.0){
						octTarget=Math.round((((octTarget-octTargetAch)/octTarget)*100));
						dto.setOctTarget(String.valueOf(BigDecimal.valueOf(octTarget).toPlainString()).concat("%"));
					}
					
					if (novTarget!=0.0){
						novTarget=Math.round((((novTarget-novTargetAch)/novTarget)*100));
						dto.setNovTarget(String.valueOf(BigDecimal.valueOf(novTarget).toPlainString()).concat("%"));
					}
				
					if (decTarget!=0.0){
						decTarget=Math.round((((decTarget-decTargetAch)/decTarget)*100));
						dto.setDecTarget(String.valueOf(BigDecimal.valueOf(decTarget).toPlainString()).concat("%"));
					}
				
							
					
					
					}else{
						dto.setJanTarget("--%");
						dto.setFebTarget("--%");
						dto.setMarTarget("--%");
						dto.setAprTarget("--%");
						dto.setMayTarget("--%");
						dto.setJunTarget("--%");
						dto.setJulTarget("--%");
						dto.setAugTarget("--%");
						dto.setSepTarget("--%");
						dto.setOctTarget("--%");
						dto.setNovTarget("--%");
						dto.setDecTarget("--%");
						
					}
					dto.setYear(year);
					if("en".equalsIgnoreCase(lang))
					dto.setExemName("Comparative Growth in %");			
					else
					dto.setExemName(" % में तुलनात्मक वृद्धि");				
					list.add(dto);
					dto=new ReportDTO();
				}
			
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {
				logger.error(err);
			}
		}
		logger.debug("in viewComparitiveFigures dao block end");
		return list;
		
		
	}
	
	public ArrayList getRevRcptMPTargets(ArrayList yearList,String dist)
	{ 
		ArrayList list=new ArrayList();
		ArrayList view = new ArrayList();
		ReportDTO dto = new ReportDTO();
		try {
			
			dbutility = new DBUtility();
			for(int j=0;j<yearList.size();j++){
				
				ReportDTO rDTO=(ReportDTO)yearList.get(j);
				String year=(String)rDTO.getYears();
				String splitYear[]=year.split("-");
				int fromYear=Integer.parseInt(splitYear[0])-1;
				int toYear=Integer.parseInt(splitYear[1])-1;
				String prvYear=String.valueOf(fromYear).concat("-").concat(String.valueOf(toYear));
			String	sql = PendingCourtCasesSQL.VIEW_TARGETS_COMP;
			logger.debug("SQL Query :-"+sql);
			String arr[] = {year,dist,prvYear,dist};
			dbutility.createPreparedStatement(sql);
			view=dbutility.executeQuery(arr);
			
			if(view!=null && view.size()>0){
					ArrayList subList=(ArrayList)view.get(0);
					dto.setTotalTarget(subList.get(0).toString());
					dto.setTotalTargetComp(subList.get(1).toString().concat("%"));			
					}
			dto.setYear(year);
			list.add(dto);
			dto=new ReportDTO();
			}
			
			
		} catch (Exception err) {
			logger.error(err);
		} finally {
			try {
				if (dbutility != null)
					dbutility.closeConnection();
			} catch (Exception err) {
				logger.error(err);
			}
		}
		logger.debug("in viewComparitiveFigures dao block end");
		return list;
		
		
	}
	
		
	

}
