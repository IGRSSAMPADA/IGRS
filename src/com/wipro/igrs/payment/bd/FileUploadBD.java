package com.wipro.igrs.payment.bd;
import org.apache.log4j.Logger;
import com.wipro.igrs.payment.form.FileUploadForm;


import com.wipro.igrs.payment.dao.FileUploadDAO;

//~--- non-JDK imports --------------------------------------------------------
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * ===========================================================================
 * File : FileUploadBD.java 
 * Description : Represents the file upload BD Class
 * Author : vengamamba P 
 * Created Date : Mar 28, 2008
 * 
 * ===========================================================================
 */
public class FileUploadBD {
	private Logger logger = (Logger) Logger.getLogger(FileUploadBD.class);
	public FileUploadBD() {
	}

	/**
	 * Method : Transidgenerator
	 *  Description : creating trasactionid for transaction 
	 * return Type :string of Transid
	 */

	public String transIDgenerator() {
		String id = "Ch" + new Date().getTime();
		logger.debug("this is transidgenerator () & value of ID " + id);
		return id;
	}

	/**
	 * ===========================================================================
	 * Method : excelReader()
	 *  Description : to read the data from a Excel file and display them 
	 * Arguments : file name
	 *  return type : Author : vengamamba
	 * ===========================================================================
	 */
	public String excelReader(InputStream is,String roleId, String funId, String userId) {
		InputStream inputStream = null;
		String rs = "fail";

		try {
			inputStream = is;
		} catch (Exception e) {
			logger.error("File not found in the specified path.");
			e.printStackTrace();
		}

		POIFSFileSystem fileSystem = null;

		try {
			FileUploadDAO dao = new FileUploadDAO();
			fileSystem = new POIFSFileSystem(inputStream);

			HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);

			int sheets = workBook.getNumberOfSheets();
			logger.debug("no of sheets.: " + sheets);
			boolean b = false;
			int i;
			for (i = 0; i < sheets; i++) {
				HSSFSheet sheet = workBook.getSheetAt(i);
				logger.debug("sheet No.: " + i);
				Iterator rows = sheet.rowIterator();
				// taking 0th row of excel sheet contain headings
				if (rows.hasNext()) {
					HSSFRow row0 = (HSSFRow)rows.next();
				}
				while (rows.hasNext()) {
					HSSFRow row = (HSSFRow)rows.next();

					// display row number in the console.
					logger.debug("Row No.: " + row.getRowNum());
					String rowList[] = new String[16];
					// once get a row its time to iterate through cells.
					Iterator cells = row.cellIterator();
					int count = 0;
					rowList[count] = transIDgenerator();
					count = count + 1;
					while (cells.hasNext()) {
						HSSFCell cell = (HSSFCell)cells.next();

						//logger.debug("Cell No.: " + cell.getCellNum());

						/*
						 * Now we will get the cell type and display the values
						 * accordingly.
						 */
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC: {

							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								HSSFCellStyle cs = cell.getCellStyle();
								short df = cs.getDataFormat();
								Date dvalue = cell.getDateCellValue();
								SimpleDateFormat sim = new SimpleDateFormat(
										"dd/MMM/yyyy");
								String cdvalue = sim.format(dvalue);
								rowList[count] = cdvalue;
								logger.debug("Date value: " + cdvalue);
							} else {
								double cellValue = cell.getNumericCellValue();
								int cellValueint = (int) cellValue;
								rowList[count] = String.valueOf(cellValueint);
								// cell type numeric.
								logger.debug("Numeric value: "
										+ cellValueint);
							}
							break;
						}

						case HSSFCell.CELL_TYPE_STRING: {

							// cell type string.
							HSSFRichTextString richTextString = cell
									.getRichStringCellValue();
							String cellValue = richTextString.getString();
							logger.debug("String value: " + cellValue);
							rowList[count] = cellValue;
							break;
						}

						default: {

							// types other than String and Numeric.
							logger.debug("Type not supported.");

							break;
						}
						}
						count++;
					}// end of while row reading
				   // session.getParameter(userid);
				   // if (userid==null){
				  //  	userid="USER001";
				  //  }
					rowList[count]="USER001";
					b = dao.insertExcelData(rowList,roleId,funId,userId);
					if (!b) {
						dao.cancelTxn();
						dao.closeConnection();
						rs = "fail";
						break;
					}

				}
				// checking if any trans fail
				if (!b) {
					break;
				}
			}//end of total sheets loop
			//saving trans if all sheets has read
			if (i == sheets) {
				dao.saveTxn();
				dao.closeConnection();
				rs = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ===========================================================================
	 * Method : displayData()
	 *  Description : to read the data from db and put into arraylist 
	 * Arguments : 
	 *  return type :ArrayList
	 *  Author : vengamamba
	 * ===========================================================================
	 */

	public ArrayList displayData()throws Exception{
		FileUploadDAO dao = new FileUploadDAO();
		 
		 
	    // String param2[]=new String[2];
	     ArrayList tlist=null;
		 //Date d=new Date();
		// SimpleDateFormat sim = new SimpleDateFormat("dd/MMM/yyyy");
		// String cdvalue = sim.format(d);
	    // param2[0]="USER001";
		// param2[1]=cdvalue;
		 tlist=new ArrayList();
		 tlist=dao.displayData();//getting  challn data based on userid, date
		 logger.debug("getting chalan data"+tlist);
		  return tlist;
	 }
		
}