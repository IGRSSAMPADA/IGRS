package com.wipro.igrs.report.bo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import org.apache.log4j.Logger;

import com.wipro.igrs.report.dao.PendingReportDAO;
import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.report.dto.PendingCourtCasesDTO;
import com.wipro.igrs.report.dto.ReportDTO;
import com.wipro.igrs.revenueManagement.dao.RevMgtDAO;
import com.wipro.igrs.revenueManagement.dto.RevMgtDTO;
public class PendingCourtCaseReportBO {
	private static final Logger logger = Logger.getLogger(PendingCourtCaseReportBO.class);
	
	//Added by rachita for pending court cases report
	public ArrayList getSearchReqReport(String froDate,String toDate) throws Exception{
		ArrayList reportFinalList=new ArrayList();
		ArrayList ret=new ArrayList();
		PendingReportDAO dao = new PendingReportDAO();
		int sum=0;
		ArrayList reportList=dao.getSearchPendingCourtReport(froDate,toDate);
		try {
		//ArrayList report=new ArrayList();	
			ArrayList report=(ArrayList)reportList.get(0);
			ArrayList report1=(ArrayList)reportList.get(1);
			ArrayList report2=(ArrayList)reportList.get(2);
			for(int i=0;i<report.size();i++){
				ArrayList reportsub=(ArrayList)report.get(i);
				ArrayList ownList1=new ArrayList();
				String fullName="";
				PendingCourtCasesDTO penDTO = new PendingCourtCasesDTO();
				penDTO.setPetitionNumber((String)reportsub.get(1));
				//penDTO.setPetitionerName((String)reportsub.get(0));
				penDTO.setPetitionDate((String)reportsub.get(0));
				penDTO.setCourtName((String)reportsub.get(10));
				penDTO.setOfficerIncharge((String)reportsub.get(5));
				penDTO.setSubjectMatter((String)reportsub.get(8));
				//penDTO.setStayOrder((String)reportsub.get(5));
				//penDTO.setRemarks((String)reportsub.get(5));
				String status=((String)reportsub.get(0));
				ArrayList reportsub1=(ArrayList)report1.get(i);
				//int nodoc=Integer.parseInt((String)reportsub.get(5));
			//	sum+=nodoc;
				if(status.equalsIgnoreCase("S"))
				{
					penDTO.setStayOrder("Yes");
				}
				else
				{
					penDTO.setStayOrder("No");
				}
				for(int j=0;j<reportsub1.size();j++){
					PendingCourtCasesDTO penDTO1 = new PendingCourtCasesDTO();
				ArrayList ownerList=(ArrayList)reportsub1.get(j);
				penDTO1.setPetitionerName((String)ownerList.get(0));
					ownList1.add(penDTO1);
					}
				//misDTO.setOwnerNameList((ArrayList)ownList1);
				String name[]=new String[ownList1.size()];
				for(int k=0;k<ownList1.size();k++){
					
					PendingCourtCasesDTO mrt=(PendingCourtCasesDTO)ownList1.get(k);
					//System.out.println();
					name[k]=mrt.getPetitionerName();
				//	fullName=(k+1)+". "+name[k]+"\n";
				}
				for(int l=0;l<name.length;l++){
					String s=name[l];
					
					fullName=fullName.concat("\n"+(l+1)+". "+s+"\n\n");
					}
				/*PendingCourtCasesDTO penDTO2 = new PendingCourtCasesDTO();*/
				ArrayList ownerList=(ArrayList)report2.get(0);
				ArrayList owner90 =(ArrayList) ownerList.get(0);
				penDTO.setRemarks((String)owner90.get(0));
				//ownList1.add(penDTO2);
				penDTO.setPetitionerName(fullName);
				reportFinalList.add(penDTO);
				
				/*ArrayList reportsub2=(ArrayList)report2.get(i);
				for(int j=0;j<reportsub2.size();j++){
					PendingCourtCasesDTO penDTO2 = new PendingCourtCasesDTO();
				ArrayList ownerList=(ArrayList)reportsub2.get(j);
				penDTO2.setRemarks((String)ownerList.get(0));
					ownList1.add(penDTO2);
					}*/
				
				//reportFinalList.add(ownList1);
			}
		//	ret.add(String.valueOf(sum));
			ret.add(reportFinalList);
		} catch (Exception e) {
			logger.error(e);

		}
		return reportFinalList;
		
	}

	//Added by rachita for cess municipal report 
	
	public ArrayList getSearchCessMunicipalReport(String fromMonth,String fromYear,String toMonth,String toYear ) throws Exception{
		ArrayList reportFinalList=new ArrayList();
		ArrayList reportFinalList1=new ArrayList();
		ArrayList reportFinalList2=new ArrayList();
		ArrayList ret=new ArrayList();
		PendingReportDAO dao = new PendingReportDAO();
		int sum=0;

	ArrayList reportList=dao.getSearchCessMunicipalReport(fromMonth,fromYear,toMonth,toYear);
	System.out.println("========================reportlist in bo"+reportList.size());
	try {
		
		PendingCourtCasesDTO penDTO = new PendingCourtCasesDTO();
		if(reportList!=null && reportList.size()>0)
		{
		
		ArrayList report=(ArrayList)reportList.get(0);
		ArrayList report1=(ArrayList)reportList.get(1);
		
		
		
			penDTO.setNoOfDocRegMonth((String)report.get(1));
			penDTO.setStampDutyMonth((String)report.get(2));
			penDTO.setGramDutyMonth((String)report.get(3));
			penDTO.setNagarDutyMonth((String)report.get(4));
			penDTO.setUpkarMonth((String)report.get(5));
			penDTO.setRegFeeMonth((String)report.get(6));
		
	
		
		
			penDTO.setNoOfDocRegDuration((String)report1.get(1));
			penDTO.setStampDutyYear((String)report1.get(2));
			penDTO.setGramDutyYear((String)report1.get(3));	
			penDTO.setNagarDutyYear((String)report1.get(4));
			penDTO.setUpkarYear((String)report1.get(5));
			penDTO.setRegFeeYear((String)report1.get(6));
			reportFinalList2.add(penDTO);
			
			
		
		//reportFinalList.addAll(reportFinalList1);
		reportFinalList.addAll(reportFinalList2);
	}} catch (Exception e) {
		logger.error(e);

	}
	return reportFinalList;
}
/*	public ArrayList getSearchCessMunicipalReport1(String fromMonth,String fromYear,String toYear,String toMonth) throws Exception{
		ArrayList reportFinalList=new ArrayList();
		ArrayList ret=new ArrayList();
		PendingReportDAO dao = new PendingReportDAO();
		int sum=0;

	ArrayList reportList=dao.getSearchCessMunicipalReport1(fromMonth,fromYear,toMonth,toYear);
	try {
	//ArrayList report=new ArrayList();	
		//ArrayList report=(ArrayList)reportList.get(0);
		//ArrayList report1=(ArrayList)reportList.get(1);
		//ArrayList report2=(ArrayList)reportList.get(2);
		
		
			
			//ArrayList ownList1=(ArrayList)reportsub.get(i);
			
	if(reportList!=null && reportList.size()>0)
	{
		for(int i=0;i<reportList.size();i++){
			ArrayList reportsub=(ArrayList)reportList.get(i);
			//ArrayList ownList1=new ArrayList();
			//String fullName="";
			String a=(String)reportsub.get(0);
			String b=(String)reportsub.get(1);
		String c=(String)reportsub.get(2);
			String d=(String)reportsub.get(3);
			//String e=(String)reportsub.get(4);
			//String f=(String)reportsub.get(5);
			//String g=(String)reportsub.get(6);
			PendingCourtCasesDTO penDTO = new PendingCourtCasesDTO();
			penDTO.setNoOfDocRegDuration((String)reportsub.get(1));
			//penDTO.setPetitionerName((String)reportsub.get(0));
			penDTO.setStampDutyYear((String)reportsub.get(2));
			penDTO.setGramDutyYear((String)reportsub.get(3));
			
			penDTO.setNagarDutyYear((String)reportsub.get(4));
			penDTO.setUpkarYear((String)reportsub.get(5));
			penDTO.setRegFeeYear((String)reportsub.get(6));
			
			//int nodoc=Integer.parseInt((String)reportsub.get(5));
		//	sum+=nodoc;
			if(status.equalsIgnoreCase("S"))
			{
				penDTO.setStayOrder("Yes");
			}
			else
			{
				penDTO.setStayOrder("No");
			}
			
			
			
			for(int j=0;j<reportsub1.size();j++){
				PendingCourtCasesDTO penDTO1 = new PendingCourtCasesDTO();
			ArrayList ownerList=(ArrayList)reportsub1.get(j);
			penDTO1.setPetitionerName((String)ownerList.get(0));
				ownList1.add(penDTO1);
				}
			//misDTO.setOwnerNameList((ArrayList)ownList1);
			String name[]=new String[ownList1.size()];
			for(int k=0;k<ownList1.size();k++){
				
				PendingCourtCasesDTO mrt=(PendingCourtCasesDTO)ownList1.get(k);
				//System.out.println();
				name[k]=mrt.getPetitionerName();
			//	fullName=(k+1)+". "+name[k]+"\n";
			}
			for(int l=0;l<name.length;l++){
				String s=name[l];
				
				fullName=fullName.concat("\n"+(l+1)+". "+s+"\n\n");
				
				
			
			}
			PendingCourtCasesDTO penDTO2 = new PendingCourtCasesDTO();
			ArrayList ownerList=(ArrayList)report2.get(0);
			ArrayList owner90 =(ArrayList) ownerList.get(0);
			penDTO.setRemarks((String)owner90.get(0));
			//ownList1.add(penDTO2);
			penDTO.setPetitionerName(fullName);
			reportFinalList.add(penDTO);
			
			ArrayList reportsub2=(ArrayList)report2.get(i);
			for(int j=0;j<reportsub2.size();j++){
				PendingCourtCasesDTO penDTO2 = new PendingCourtCasesDTO();
			ArrayList ownerList=(ArrayList)reportsub2.get(j);
			penDTO2.setRemarks((String)ownerList.get(0));
				ownList1.add(penDTO2);
				}
			
			//reportFinalList.add(ownList1);
		}
	//	ret.add(String.valueOf(sum));
		//ret.add(reportFinalList);
	}} catch (Exception e) {
		logger.error(e);

	}
	return reportFinalList;
}
*/
	//For Comparative Report
	
	public ArrayList getDistrictList(String lang) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
    ArrayList subList = null;
   PendingReportDAO dao = new PendingReportDAO();
		try{
		list = dao.getDistrictList();
    
	ReportDTO dto = new ReportDTO();
    for (int i = 0; i < list.size(); i++) {
        subList = (ArrayList)list.get(i);
        dto=new ReportDTO();
   
       // dto = new RevMgtDTO();
       dto.setDistrictId(subList.get(0).toString());
       if("en".equalsIgnoreCase(lang)){
    	     dto.setDistrictName(subList.get(1).toString());

       }else{
    	   	dto.setDistrictName(subList.get(2).toString());

       }
        mainList.add(dto);
    	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	//added by rachita
	//added by rachita
	public void insertComparitive(String FINANCIAL_YEAR,String JANUARY,String FEBRUARY,String MARCH,
			String APRIL,String MAY,String JUNE,String JULY,String AUGUST,String SEPTEMBER,String OCTOBER,
			String NOVEMBER,String DECEMBER,String CREATEDBY,String CREATEDDATE,String UPDATEDBY
            ,String UPDATEDDATE,String DISTRICT,String JANUARYC,String FEBRUARYC,String MARCHC,
            String APRILC,String MAYC,String JUNEC,String JULYC,String AUGUSTC,String SEPTEMBERC,String OCTOBERC,
            String NOVEMBERC,String DECEMBERC)
	{
		PendingReportDAO  dao = new PendingReportDAO();
		dao.insertComparitive(FINANCIAL_YEAR,JANUARY,FEBRUARY,MARCH,
                APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,
                NOVEMBER,DECEMBER,CREATEDBY,CREATEDDATE,UPDATEDBY
                ,UPDATEDDATE,DISTRICT,JANUARYC,FEBRUARYC,MARCHC,
                APRILC,MAYC,JUNEC,JULYC,AUGUSTC,SEPTEMBERC,OCTOBERC,
                NOVEMBERC,DECEMBERC);
	}
	
	//added by rachita
	public void updateComparitive(String FINANCIAL_YEAR,String JANUARY,String FEBRUARY,String MARCH,
			String APRIL,String MAY,String JUNE,String JULY,String AUGUST,String SEPTEMBER,String OCTOBER,
			String NOVEMBER,String DECEMBER,String UPDATEDBY
            ,String UPDATEDDATE,String DISTRICT,String JANUARYC,String FEBRUARYC,String MARCHC,
            String APRILC,String MAYC,String JUNEC,String JULYC,String AUGUSTC,String SEPTEMBERC,String OCTOBERC,
            String NOVEMBERC,String DECEMBERC)
	{
		PendingReportDAO  dao = new PendingReportDAO();
		dao.updateComparitive(FINANCIAL_YEAR,JANUARY,FEBRUARY,MARCH,
                APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,
                NOVEMBER,DECEMBER,UPDATEDBY
                ,UPDATEDDATE,DISTRICT,JANUARYC,FEBRUARYC,MARCHC,
                APRILC,MAYC,JUNEC,JULYC,AUGUSTC,SEPTEMBERC,OCTOBERC,
                NOVEMBERC,DECEMBERC);
	}
	
	//added by rachita
	public ArrayList viewComparitive(String DISTRICT,String FINANCIAL_YEAR )
	{
		PendingReportDAO  dao = new PendingReportDAO();
		ArrayList arr = new ArrayList();
		arr=dao.viewComparitive(DISTRICT,FINANCIAL_YEAR);
		return arr;
	}
	
	public ArrayList viewComparitiveFigures(String fYearAdd,String tYearAdd ,String DISTRICT)
	{
		PendingReportDAO  dao = new PendingReportDAO();
		ArrayList arr = new ArrayList();
		arr=dao.viewComparitiveFigures(fYearAdd,tYearAdd,DISTRICT);
		return arr;
  	}
	//added by rachita for create target
	public ArrayList viewCreateTarget(String DISTRICT,String FINANCIAL_YEAR )
	{
		PendingReportDAO  dao = new PendingReportDAO();
		ArrayList arr = new ArrayList();
		arr=dao.viewCreateTarget(DISTRICT,FINANCIAL_YEAR);
		return arr;
	}
	public ArrayList viewProgAchivement(String fYearAdd,String tYearAdd ,String DISTRICT)
	{
		PendingReportDAO  dao = new PendingReportDAO();
		ArrayList arr = new ArrayList();
		arr=dao.viewProgAchivement(fYearAdd,tYearAdd,DISTRICT);
		return arr;
	}
	//added by SHreeraJ
	public ArrayList getRevRcptMP(ArrayList yearList,String dist,String lang){
		ArrayList list=new ArrayList();
		PendingReportDAO  dao = new PendingReportDAO();
		list=dao.getRevRcptMP(yearList,dist,lang);
		return list;
	}
	
	
	public ArrayList getRevRcptMPTargets(ArrayList yearList,String dist){
		ArrayList list=new ArrayList();
		PendingReportDAO  dao = new PendingReportDAO();
		list=dao.getRevRcptMPTargets(yearList,dist);
		return list;
	}
}
