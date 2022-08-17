package com.wipro.igrs.report.bd;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.report.bo.ReportBO;

public class PDFGeneration {

	private Logger logger = (Logger) Logger.getLogger(ReportBD.class);
	public static void createPDF(HashMap<Integer, ArrayList<String>> finalPdfData) {

		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E://shared1//AddTableExample.pdf"));
			FileOutputStream reportFile = new FileOutputStream("E://shared1//AddTableExampleCSV.csv", true);
			FileOutputStream reportFiletxt = new FileOutputStream("E://shared1//AddTableExampleTXT.txt", true);
			Writer out = new OutputStreamWriter(reportFile, "UTF-8");
			Writer out1 = new OutputStreamWriter(reportFiletxt, "UTF-8");
			document.open();
			int columnLen = finalPdfData.size();
			PdfPTable table = new PdfPTable(columnLen);
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table
			float[] columnWidths = new float[columnLen];
			for (int i = 0; i < columnLen; i++) {
				columnWidths[i] = 2f;
			}
			table.setWidths(columnWidths);
			Set<Integer> columnName = finalPdfData.keySet();
			List<Integer> listsort = new ArrayList<Integer>(columnName);
			Collections.sort(listsort);
			ArrayList<PdfPCell> cellList = new ArrayList<PdfPCell>();
			Iterator<Integer> it = listsort.iterator();
			ArrayList<Integer> list = new ArrayList<Integer>();
			String columnHeader = "";
			int countOfColumn = 0;
			while (it.hasNext()) {
				countOfColumn++;
				Integer key = it.next();
				ArrayList<String> col = finalPdfData.get(key);
				String colName = col.get(0).split("~")[0];
				PdfPCell cell = new PdfPCell(new Paragraph(colName));
				cell.setBorderColor(BaseColor.BLUE);
				cell.setPaddingLeft(10);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cellList.add(cell);
				list.add(key);
				if (columnHeader.equals("")) {
					columnHeader = colName;
				} else {
					columnHeader = columnHeader + "," + colName;
				}
			}
			ArrayList<String> restValues = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				Integer key = list.get(i);
				ArrayList list1 = finalPdfData.get(key);
				System.out.println(key + "-----" + list1.size());
			}
			byte[] b = columnHeader.getBytes();
			out.write(columnHeader);
			out1.write(columnHeader);
			while (finalPdfData.get(list.get(0)).size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Integer key = list.get(i);
					ArrayList list1 = finalPdfData.get(key);
					String value = ((String) list1.get(0)).split("~")[1];
					list1.remove(0);
					finalPdfData.put(key, list1);
					restValues.add(value);
				}
			}
			Iterator<String> it1 = restValues.iterator();
			int colCount = 0;
			String rowValues = "";
			while (it1.hasNext()) {
				colCount++;
				String key = it1.next();
				PdfPCell cell = new PdfPCell(new Paragraph(key));
				cell.setBorderColor(BaseColor.BLUE);
				cell.setPaddingLeft(10);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cellList.add(cell);
				if (colCount == countOfColumn) {
					colCount = 0;
					rowValues = rowValues + ", "+ key;
					byte[] c = rowValues.getBytes();
					out.write(rowValues);
					out1.write(rowValues);
					rowValues = "";
				} else {
					if (rowValues.equals("")) {
						rowValues =  key ;
					} else {
						rowValues = rowValues + ", " + key ;
					}
				}
				// list.add(key);
			}
			Iterator<PdfPCell> itr = cellList.iterator();
			while (itr.hasNext()) {
				table.addCell(itr.next());
			}
			reportFile.write('\n');
			
			File img = new File("E://shared1//IndianSymbolJPg.jpg");
			BufferedImage bImage = ImageIO.read(img);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "jpg", bos );
			byte [] data = bos.toByteArray();
			//reportFile.write(data);
			document.add(table);
			document.close();
			reportFile.close();
			out.close();
			out1.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
