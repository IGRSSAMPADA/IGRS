package com.wipro.igrs.transactionHistory.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.transactionHistory.DTO.TransactionHistoryDTO;

public class TranHistoryDAO {
	
	
	public ArrayList getData(TransactionHistoryDTO dto){
		
		try {
			DBUtility db = new DBUtility();
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	public ArrayList getDistrict(String language){
	ArrayList alist = new ArrayList();
	try {
		//modified by shruti----districts only in MP 
		DBUtility db = new DBUtility();
		String sql="";
		if("en".equalsIgnoreCase(language))
		{
			sql = "Select DISTRICT_ID, "
				+"DISTRICT_NAME from IGRS_DISTRICT_MASTER "
				+" Where DISTRICT_STATUS='A' and STATE_ID=1"
				+"ORDER BY DISTRICT_NAME ASC";
		}
		else
		{
			sql = "Select DISTRICT_ID, "
				+"H_DISTRICT_NAME from IGRS_DISTRICT_MASTER "
				+" Where DISTRICT_STATUS='A' and STATE_ID=1"
				+"ORDER BY DISTRICT_NAME ASC";	
		}
		
		db.createStatement();
		alist=db.executeQuery(sql);
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return alist;
	}
	
	public String setStampData(TransactionHistoryDTO dto){
		String abc="";
		String sql ="";
		String param[]=null;
		try {
			DBUtility db = new DBUtility();
			
			if("2".equalsIgnoreCase(dto.getRadio())){
			param=new String[11];
			
			
			param[0]=dto.getType();
			param[1]=dto.getFirstName();
			param[2]=dto.getDistrict();
			param[3]=getOracleDate(dto.getDate());
			param[4]=dto.getDro();
			param[5]=dto.getSro();
			param[6]=dto.getDenoStamp();
			param[7]=dto.getCodeStamp();
			param[8]=dto.getSeriesNo();
			param[9]=dto.getAmount();
			param[10]=dto.getUserId();
			
			
			sql = "INSERT INTO IGRS_MANUAL_STAMPS (TYPE, NAME, DISTRICT, DATE_OF_ISSUE, DRO, SRO, DENOMINATION_OF_STAMP, CODE_OF_STAMP, SERIES_NO, AMOUNT, CREATED_DATE, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?, TO_CHAR(SYSDATE, 'DD-MON-YYYY'), ?)";

		}
		
		else if("1".equalsIgnoreCase(dto.getRadio())){
		param=new String[12];
		param[0]=dto.getType();
		param[1]=dto.getFirstName();
		param[2]=dto.getDistrict();
		param[3]=getOracleDate(dto.getDate());
		param[4]=dto.getDro();
		param[5]=dto.getSro();
		param[6]=dto.getDenoStamp();
		param[7]=dto.getCodeStamp();
		param[8]=dto.getSeriesNoF();
		param[9]=dto.getSeriesNoT();
		param[10]=dto.getAmount();
		param[11]=dto.getUserId();
		
		sql = "INSERT INTO IGRS_MANUAL_STAMPS (TYPE, NAME, DISTRICT, DATE_OF_ISSUE, DRO, SRO, DENOMINATION_OF_STAMP, CODE_OF_STAMP, SERIES_NO_FROM, SERIES_NO_TO, AMOUNT, CREATED_DATE, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?,?, ?, SYSDATE, ?)";
		}
		db.createPreparedStatement(sql);
			
		db.executeUpdate(param);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return abc;
	}
	
	public ArrayList getStampData(TransactionHistoryDTO dto){
		ArrayList alist= new ArrayList();
		
		String param[]= new String[1];
		param[0]=dto.getCodeStamp();
		try {
			DBUtility db = new DBUtility();
			
			String sql = "SELECT TYPE, NAME, DISTRICT, DATE_OF_ISSUE, DRO, SRO, DENOMINATION_OF_STAMP, CODE_OF_STAMP, SERIES_NO, AMOUNT, CREATED_DATE, CREATED_BY, SERIES_NO_FROM, SERIES_NO_TO FROM IGRS_MANUAL_STAMPS WHERE CODE_OF_STAMP = ?";
			
			db.createPreparedStatement(sql);
			alist=db.executeQuery(param);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return alist;
	}
	public static String getOracleDate(String DateFormat) {
		String finalDate = "";
		if (DateFormat != null || !DateFormat.equalsIgnoreCase("")) {
			StringTokenizer st = new StringTokenizer(DateFormat, "/");
			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();
			String inputDate = day + "-" + month + "-" + year;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			Date newDate;
			try {
				newDate = formatter.parse(inputDate);
				SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
				finalDate = format.format(newDate);

			} catch (Exception e) {
				System.out.print(e);
			}

		}
		return finalDate;
	}
	public ArrayList getList(TransactionHistoryDTO dto){
		ArrayList alist = new ArrayList();
		
		String param[]= new String[2];
		param[0]=getOracleDate(dto.getFromDate());
		param[1]=getOracleDate(dto.getToDate());
		try {
			DBUtility db = new DBUtility();
			
			String sql = "SELECT CREATED_DATE, CODE_OF_STAMP, TYPE, AMOUNT FROM IGRS_MANUAL_STAMPS WHERE CREATED_DATE BETWEEN ? AND ?";
			
			db.createPreparedStatement(sql);
			alist=db.executeQuery(param);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return alist;
	}
	
	
}
