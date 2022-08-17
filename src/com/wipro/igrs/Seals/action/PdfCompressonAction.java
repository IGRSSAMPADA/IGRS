package com.wipro.igrs.Seals.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.emudhra.itextpdf.text.List;
import com.wipro.igrs.db.DBUtility;

public class PdfCompressonAction {
	public static void main(String args[]) throws Exception {
		DBUtility dbUtil = null;
		try{
			String sql="select REG_TXN_ID, DEED_DOC_FILEPATH, DEED_DOC_FILEPATH_INITIAL from IGRS_REG_TXN_DETLS where REGISTRATION_TXN_STATUS=17 and DEED_DOC_FILEPATH like '%shared10/%'";
			dbUtil = new DBUtility();
			dbUtil.createStatement();  
			ArrayList result = dbUtil.executeQuery(sql);
			String filePath="C://Users//HP//Desktop//dmsapi//result.xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(filePath);
			for(int i = 0;i<result.size();i++){
				ArrayList<String> res = (ArrayList<String>) (result.get(i));
	            ArrayList<String> data =  new ArrayList<String>(Arrays.asList(res.get(0),res.get(1),res.get(2)));
	            writeExcel(data, filePath);
			}
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				System.out.println("Exception in closing connection" + ex);
				
			}
		}
	}
	
	public static void writeExcel(java.util.List<String> listBook, String excelFilePath) throws IOException {
	   
	 
	    int rowCount = 0;
	 
	    for (String aBook : listBook) {
	      //  Row row = sheet.createRow(++rowCount);
	        
	    }
	 /*
	    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
	       // workbook.write(outputStream);
	    }*/
	}
	private  static void writeBook(String aBook, Row row) {
	 /*   Cell cell = row.createCell(1);
	    cell.setCellValue(aBook.getTitle());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(aBook.getAuthor());
	 
	    cell = row.createCell(3);
	    cell.setCellValue(aBook.getPrice());*/
	}
}
