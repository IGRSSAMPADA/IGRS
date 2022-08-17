package com.wipro.igrs.eToken.bo;




import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.device.applet.printnow;
import com.wipro.igrs.eToken.bd.ETokenBD;
import com.wipro.igrs.eToken.dao.ETokenDAO;
import com.wipro.igrs.eToken.dto.ETokenDTO;
import com.wipro.igrs.eToken.form.ETokenForm;


public class ETokenBO implements Comparator<ETokenDTO>{

	ETokenBD bd = null;
	public ETokenBO() {
		bd = new ETokenBD();
	}
	
	
	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author ROOPAM
	 */

	//Added By Mohit Soni
	public  String getEtokenNum()
	{
		ETokenBD bd = new ETokenBD();
		return bd.getEtokenNum();
		
		
	}
	
	
	public static String getEtoken(String str) throws Exception
	{
		
		    int treeSizeOne = 0;
		    int treeSizeTwo = 0;
			String sql = "select etokenno from igrs_etoken_details order by etokenno";
			DBUtility dbUtil = new DBUtility();
			dbUtil.createStatement();
			ArrayList etoken = dbUtil.executeQuery(sql);
			ArrayList subEtoken = new ArrayList();
		    int eTokenNumberToCheck = new Random().nextInt(999);
				String eTokenNumberStr = "" + eTokenNumberToCheck;
				 ArrayList value= new ArrayList();
				 TreeSet t = new TreeSet();
				
				
				 for(int i =0; i < etoken.size();i++ )
			    {
				    
					 value =(ArrayList)etoken.get(i);
					 t.add((String) value.get(0));
					 
			    }
				 treeSizeOne = t.size();
				 t.add(eTokenNumberStr);
				 treeSizeTwo = t.size();
				 if(treeSizeTwo>treeSizeOne)		 {
					 if(eTokenNumberStr.length()==1)	 {
						 return "00" + eTokenNumberStr;
					 }
					 if(eTokenNumberStr.length()==2)	 {
						 return "0" + eTokenNumberStr;
					 }
					 if(eTokenNumberStr.length()==3){
					 return eTokenNumberStr;
					 }
				 }
				 else	 {
					 getEtoken("ETOK");
				 }
				 
				 return eTokenNumberStr;
				
			}
	
		
	private String getDateTimeEtoken(String dateFormat) { 
		// format: mm/dd/yyyy hh:mm AM  //  
        System.out.println("Formatting date:"+dateFormat+":");   
        String[] retVal = new String[2];   
        StringTokenizer stk = new StringTokenizer(dateFormat," ");   
        retVal[0] = stk.nextToken();   
        retVal[1] = stk.nextToken()+" "+stk.nextToken();   
        return retVal[1];   
}
		
	
	
	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author ROOPAM
	 */
	public void checkRegInitId(String officeId,ETokenForm eForm) throws Exception
	{
		ArrayList list=bd.checkRegInitId(officeId, eForm);
		ArrayList rowList;
		eForm.setErrorMsg("");
		String[] strArr=null;
		if(list!=null && list.size()==1){
			
			rowList=(ArrayList)list.get(0);
			
			String str=rowList.toString();
			str=str.substring(2, str.length()-2);
			
			strArr=str.split(",");
			
		}
		
		if(strArr!=null){
			
			if(strArr[1].trim().equalsIgnoreCase("10") ||       // 10-initiation complete
					strArr[1].trim().equalsIgnoreCase("12") ||  // 12-maker hold
					strArr[1].trim().equalsIgnoreCase("13") ||  // 13-maker complete
					strArr[1].trim().equalsIgnoreCase("14")){   // 14-checker hold
				
				//add to maker queue for 10 status
				eForm.getEtokenDTO().setSlotDate(strArr[2].trim());
				
				//String from = getDateTimeEtoken(strArr[3].trim());
			//	String to = getDateTimeEtoken(strArr[4].trim());
				//eForm.getEtokenDTO().setSlotFromTime(from);
				//eForm.getEtokenDTO().setSlotToTime(to);
				eForm.getEtokenDTO().setTokenNumber("token1");
				if(strArr[1].trim().equalsIgnoreCase("10"))
				eForm.getEtokenDTO().setApplicationType("For Maker");
				else if(strArr[1].trim().equalsIgnoreCase("12"))
					eForm.getEtokenDTO().setApplicationType("On Maker-Hold");
					else if(strArr[1].trim().equalsIgnoreCase("13"))
						eForm.getEtokenDTO().setApplicationType("For Checker");
						else if(strArr[1].trim().equalsIgnoreCase("14"))
							eForm.getEtokenDTO().setApplicationType("On Checker-Hold");
				eForm.getEtokenDTO().setAppStatus(Integer.parseInt(strArr[1].trim()));
				
			}else{
				eForm.getEtokenDTO().setSlotDate("");
				eForm.getEtokenDTO().setSlotFromTime("");
				eForm.getEtokenDTO().setSlotToTime("");
				eForm.getEtokenDTO().setTokenNumber("");
				eForm.getEtokenDTO().setAppStatus(0);
			}
			
			//check status and add to respective queue.
			
		}else{
			eForm.getEtokenDTO().setSlotDate("");
			eForm.getEtokenDTO().setSlotFromTime("");
			eForm.getEtokenDTO().setSlotToTime("");
			eForm.getEtokenDTO().setTokenNumber("");
			eForm.getEtokenDTO().setAppStatus(0);
			eForm.getEtokenDTO().setTokenNumber("token1");
		}
		
		
	
	}
	
	public com.wipro.igrs.device.applet.ETokenDTO checkRegInitId(String officeId,String  regId) throws Exception
	{
		com.wipro.igrs.device.applet.ETokenDTO dto = new com.wipro.igrs.device.applet.ETokenDTO();
		ArrayList list=bd.checkRegInitId(officeId, regId);
		ArrayList rowList;
		//eForm.setErrorMsg("");
		String[] strArr=null;
		
		String deedId = "";
		
		deedId  = bd.getDeedIDforRegtxn(regId);
		
	/*	if(deedId.equalsIgnoreCase("1094")){
			if(list!=null && list.size()==1){
				
				rowList=(ArrayList)list.get(0);
				
				String str=rowList.toString();
				str=str.substring(2, str.length()-1);
				
				strArr=str.split(",");
				
			}
			
		}
		else{
		*/
		if(list!=null && list.size()==1){
			
			rowList=(ArrayList)list.get(0);
			
			String str=rowList.toString();
			str=str.substring(2, str.length()-1);
			
			strArr=str.split(",");
			
		}
		
	/*	}*/
		
		if(strArr!=null){
			
			if(strArr[1].trim().equalsIgnoreCase("10") ||       // 10-initiation complete
					strArr[1].trim().equalsIgnoreCase("11") ||  // 12-maker hold
					strArr[1].trim().equalsIgnoreCase("13") ||  // 13-maker complete
					strArr[1].trim().equalsIgnoreCase("14")){   // 14-checker hold
				
				//add to maker queue for 10 status
				dto.setSlotDate(strArr[2].trim());
	    	if(deedId.equalsIgnoreCase("1094")){
	    		
	    		dto.setTimeFrom("00");
				dto.setTimeTo("00");
	    	}
	    	else{
				String from = strArr[3].trim();
				String to = strArr[4].trim();
				dto.setTimeFrom(from);
				dto.setTimeTo(to);}
			//	dto.setTokenNumber("token1");
				if(strArr[1].trim().equalsIgnoreCase("10"))
			dto.setCounterType("For Maker");
				else if(strArr[1].trim().equalsIgnoreCase("11"))
					dto.setCounterType("On Maker-Hold");
					else if(strArr[1].trim().equalsIgnoreCase("13"))
						dto.setCounterType("For Checker");
						else if(strArr[1].trim().equalsIgnoreCase("14"))
							dto.setCounterType("On Checker-Hold");
				dto.setAppStatus(strArr[1].trim());
				
			}else{
				dto.setSlotDate("");
				dto.setTimeFrom("");
				dto.setTimeTo("");
				
				dto.setAppStatus("0");
			}
			
			//check status and add to respective queue.
			
		}else{
	
		
			dto.setSlotDate("");
			dto.setTimeFrom("");
			dto.setTimeTo("");
			
			dto.setAppStatus("0");
		}
		
		
	return dto;
	}
	public boolean outputtingBarcodeAsPNG(String RegId) throws BarcodeException {
        // get a Barcode from the BarcodeFactory
		boolean check = false;
		Barcode bar = BarcodeFactory.createCode128(RegId);
bar.setBarWidth(1);

        try {
            File f = new File("D:\\mybarcode.jpg");
            
            // Let the barcode image handler do the hard work
            BarcodeImageHandler.saveJPEG(bar, f);
            check = true;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return check;
        
    }
	
	
	
	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author ROOPAM
	 * @param userId 
	 */
	public boolean checkAlreadyToken(String regId,String counterType)
	{
		
		ETokenDAO dao = new ETokenDAO();
		boolean boo = dao.checkAlreadyToken(regId, counterType);
		return boo;
		
		
	}
	
	
	public com.wipro.igrs.device.applet.ETokenDTO checkExistsAlreadyToken(String regId,String counterType)
	{
		
		ETokenDAO dao = new ETokenDAO();
		ArrayList  boo = dao.checkAlreadyTokenExists(regId, counterType);
		com.wipro.igrs.device.applet.ETokenDTO dto = null;
		for(int i=0;i<boo.size();i++)
		{
			dto = new com.wipro.igrs.device.applet.ETokenDTO();
			ArrayList l = (ArrayList) boo.get(i);			
			dto.setRegistrationId((String)l.get(0));
			dto.setEtokenNo((String)l.get(1));
			dto.setCounterType((String)l.get(2));
			dto.setNoOfPersons((String)l.get(3));
			dto.setTypeOfPerson((String)l.get(4));
			dto.setSlotDate((String)l.get(5));
			dto.setOfficeId((String)l.get(6));
		}
		if(boo.size()==0)
		{
			dto = new com.wipro.igrs.device.applet.ETokenDTO();
			dto.setAssigned("BBB");
		}
		return dto;
		
		
	}
	
	public String generateToken(ETokenForm eForm, HttpServletResponse response,String officID, String userId) throws Exception
	{
		
		
		officID="OFC66";
		boolean boo = false;
		String checkRegIDM= "";
		String counterType = "";
		ETokenDAO ojb = new ETokenDAO();
		 checkRegIDM = ojb.checkRegInitIdM(eForm.getEtokenDTO().getRegInitId(),officID);
		 String office_Name = ojb.getOfficeName(officID);
		 
		 if(checkRegIDM.trim().equalsIgnoreCase("10")) {counterType = "Maker";}      // 10-initiation complete
			if(checkRegIDM.trim().equalsIgnoreCase("11")) {counterType = "Maker-Hold";}      // 12-maker hold
			if(checkRegIDM.trim().equalsIgnoreCase("13")) {counterType = "Checker";}      // 13-maker complete
			if(checkRegIDM.trim().equalsIgnoreCase("14")) {counterType = "Checker-Hold";}  
			
			
			
		boo = ojb.checkAlreadyToken(eForm.getEtokenDTO(),counterType);
		if(boo==false)
		{
			
			return "E";
			
		}
		ArrayList checkRegID = new ArrayList();
		ArrayList subcheckRegID = new ArrayList();
		String catagory = "";
		if(eForm.getEtokenDTO().getCategory()==0)
		{
			catagory = "Special";
		}
		else
		{
			catagory = "Normal";
		}
		
		try
		{
			boo = true;
		//	boo = outputtingBarcodeAsPNG(eForm.getEtokenDTO().getRegInitId());
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		if(boo)
		{
		String a = ojb.getEtokenNum();
		eForm.getEtokenDTO().setEtokenNumberC(a);
		
		
		try
		{
		//	boo = printnow.printCard(eForm,catagory,office_Name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		}
		if(boo)
		{
			 checkRegIDM = ojb.checkRegInitIdM(eForm.getEtokenDTO().getRegInitId(),officID);
				if(checkRegIDM.trim().equalsIgnoreCase("10")) {counterType = "Maker";}      // 10-initiation complete
				if(checkRegIDM.trim().equalsIgnoreCase("11")) {counterType = "Maker-Hold";}      // 12-maker hold
				if(checkRegIDM.trim().equalsIgnoreCase("13")) {counterType = "Checker";}      // 13-maker complete
				if(checkRegIDM.trim().equalsIgnoreCase("14")) {counterType = "Checker-Hold";}      // 14-checker hold
			
				if(counterType.equalsIgnoreCase("Maker") || counterType.equalsIgnoreCase("Maker-Hold"))
				{
				boo = 	ojb.insertETokenDetails(eForm,counterType,catagory,userId,"M",checkRegIDM,officID);
					
				}
				else if(counterType.equalsIgnoreCase("Checker") || counterType.equalsIgnoreCase("Checker-Hold"))
				{
					
				boo = 	ojb.insertETokenDetails(eForm,counterType,catagory,userId,"C",checkRegIDM,officID);
					
				}
				
			
		}
		/*String qrCodeText = "Registraion No : "+eForm.getEtokenDTO().getRegInitId()
							+ "\r\n Number of persons : "+eForm.getEtokenDTO().getNoPersons()
							+ "\r\n Category : "+catagory
							+ "\r\n Transaction Date : "+eForm.getEtokenDTO().getSlotDate();
		String filePath = "D:\\Barcode\\"+eForm.getEtokenDTO().getRegInitId()+"\\JD.jpg";
		int size = 100;
		String fileType = "jpg";
		File qrFile = new File(filePath);
		try {
		
			checkCounter(eForm , officID);
			
		//Registration Id check
			checkRegID = ojb.checkRegID(eForm.getEtokenDTO().getRegInitId());
			if(checkRegID.size()==0){
				checkCounter(eForm , officID);
			    createQRImage(qrFile, qrCodeText, size, fileType, filePath,eForm,response);
			}
			else{
			 subcheckRegID = (ArrayList)checkRegID.get(0);
		     String checkStatus = (String)subcheckRegID.get(0);
		     if(checkStatus.equals("C")){
                checkCounter(eForm , officID);
		        createQRImage(qrFile, qrCodeText, size, fileType, filePath,eForm,response);
		    }
		    }
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	System.out.println("DONE");
		*/
		System.out.println(boo);
		return "Succesfully";
	}
	
	public com.wipro.igrs.device.applet.ETokenDTO generateTokenS(String regID, String etokenNo,String officID,String noOfPersons, String userId,String slot_time, String category, com.wipro.igrs.device.applet.ETokenDTO dto,String status) throws Exception
	{
		
		
		
		boolean boo = false;
		String checkRegIDM= "";
		String counterType = "";
		ETokenDAO ojb = new ETokenDAO();
		String tokenNo = "";
		 checkRegIDM = ojb.checkRegInitIdM(regID,officID);
		 
		 
		boolean boos =  ojb.getRegCheck(regID);
		if(checkRegIDM.trim().equalsIgnoreCase("10")){
		 
		if(boos==false)
		{
			String dates = dto.getSlotDate();
			String curr = ojb.getDate();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateInString = dates;
			
			try {
		 
				Date date = formatter.parse(dateInString);
				Date dataCurrent = formatter.parse(curr);
				
				if(date.compareTo(dataCurrent)>0)
				{
					return null;
				}
				else if(date.compareTo(dataCurrent)<0)
				{
					return null;
				}
				
				
				
			
		 
			} catch (ParseException e) {
				e.printStackTrace();
			
		}
		}
			else if(boos==true)
			{
				
				String dates = dto.getSlotDate();
				String curr = ojb.getDate();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String dateInString = dates;
				try {
					 
					Date date = formatter.parse(dateInString);
					Date dataCurrent = formatter.parse(curr);
					
					 if(date.compareTo(dataCurrent)<0)
					{
						return null;
					}
					
					
				
			 
				} catch (ParseException e) {
					e.printStackTrace();
				
			}
				
			}
		}
		ArrayList checkRegID = new ArrayList();
		ArrayList subcheckRegID = new ArrayList();
		String catagory = category;
		try
		{
			
		boo = outputtingBarcodeAsPNG(regID);
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		if(boo)
		{
		String a = ojb.getEtokenNum(officID);
		
		tokenNo = a;
		
		dto.setEtokenNo(tokenNo);
		
		}
		if(boo)
		{
		//	 checkRegIDM = ojb.checkRegInitIdM(regID,officID);
				if(checkRegIDM.trim().equalsIgnoreCase("10")) {counterType = "Maker";}      // 10-initiation complete
				if(checkRegIDM.trim().equalsIgnoreCase("11")) {counterType = "Maker-Hold";}      // 12-maker hold
				if(checkRegIDM.trim().equalsIgnoreCase("13")) {counterType = "Checker";}      // 13-maker complete
				if(checkRegIDM.trim().equalsIgnoreCase("14")) {counterType = "Checker-Hold";}      // 14-checker hold
			
				if(counterType.equalsIgnoreCase("Maker") || counterType.equalsIgnoreCase("Maker-Hold"))
				{
					if(counterType.equalsIgnoreCase("Maker-Hold"))
					{
					boo = 	ojb.deleteExixstingToken(regID,"M");
					}
				boo = 	ojb.insertETokenDetailsS(tokenNo,noOfPersons,regID,"Maker",catagory,userId,"M","10",officID);
					
				}
				else if(counterType.equalsIgnoreCase("Checker") || counterType.equalsIgnoreCase("Checker-Hold"))
				{
					if(counterType.equalsIgnoreCase("Checker-Hold"))
					{
						boo =	ojb.deleteExixstingToken(regID,"C");
					}
					
				boo = 	ojb.insertETokenDetailsS(tokenNo,noOfPersons,regID,"Checker",catagory,userId,"C","13",officID);
					
				}
				
			
		}
		
		System.out.println(boo);
		if(boo)
		{
			return dto;
		}
		else
			return null;
		}
	/**
	 * 
	 * @param qrFile
	 * @param qrCodeText
	 * @param size
	 * @param fileType
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void createQRImage(File qrFile, String qrCodeText, int size,
			String fileType,String filePath,ETokenForm eForm,HttpServletResponse response) throws WriterException, IOException {
		
		if(qrFile.getParentFile().exists()!=true){
			qrFile.getParentFile().mkdirs();
		}
		// Create the ByteMatrix for the QR-Code that encodes the input String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
				BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
				BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
		imageToPDF(filePath,eForm,response);
	}
	
	
	

	public static void imageToPDF(String filePath,ETokenForm eForm,HttpServletResponse response) {
		try {
			
			
			String path = filePath;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A8, 20, 20, 20, 20);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.open();

			Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
		      	row.setHeader(true);
		      	row.setColspan(22);
		      	
		    Table eToken = new Table(22);
		    eToken.setWidth(100);
		    eToken.setBorder(Rectangle.NO_BORDER);
		    //eToken.setPadding(3);
			
			PdfPTable table = new PdfPTable(22);
			table.setWidthPercentage(100);
			
			
			Image image1 = Image.getInstance(path);
			
			PdfPCell imageHeader = new PdfPCell(image1,false);
			imageHeader.setColspan(22);
			imageHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			imageHeader.setBorder(Rectangle.NO_BORDER);
			table.addCell(imageHeader);
			
			eToken.addCell(row);
			
			Cell licenceno=new Cell(new Phrase("Date/Time : ", FontFactory.getFont(FontFactory.COURIER, 6, Font.NORMAL)));	    			      
		      licenceno.setHeader(true);
		      licenceno.setColspan(11);
		      licenceno.setBorder(Rectangle.NO_BORDER);
		      eToken.addCell(licenceno);
		      
		      Cell licencenovalue=new Cell(new Phrase(eForm.getEtokenDTO().getSlotDate(), FontFactory.getFont(FontFactory.COURIER, 6, Font.NORMAL)));	    			      
		      licencenovalue.setHeader(true);
		      licencenovalue.setColspan(11);
		     licencenovalue.setBorder(Rectangle.NO_BORDER);
		     eToken.addCell(licencenovalue);
		      
		      Cell place=new Cell(new Phrase("E-Token Number : ", FontFactory.getFont(FontFactory.COURIER, 6, Font.NORMAL)));	    			      
		      place.setHeader(true);
		      place.setColspan(11);
		     place.setBorder(Rectangle.NO_BORDER);
		      eToken.addCell(place);
		     
		      Cell placevalue=new Cell(new Phrase(eForm.getEtokenDTO().getTokenNumber(), FontFactory.getFont(FontFactory.COURIER, 6, Font.NORMAL)));	    			      
		      placevalue.setHeader(true);
		      placevalue.setColspan(11);
		      placevalue.setBorder(Rectangle.NO_BORDER);
		     eToken.addCell(placevalue);
		      
		      Cell name=new Cell(new Phrase("Registration Number : ", FontFactory.getFont(FontFactory.COURIER, 6, Font.NORMAL)));	    			      
		      name.setHeader(true);
		      name.setColspan(11);
		      name.setBorder(Rectangle.NO_BORDER);
		      eToken.addCell(name);
		      Cell namevalue=new Cell(new Phrase(eForm.getEtokenDTO().getRegInitId(), FontFactory.getFont(FontFactory.COURIER, 6, Font.NORMAL)));	    			      
		      namevalue.setHeader(true);
		      namevalue.setColspan(11);
		      namevalue.setBorder(Rectangle.NO_BORDER);
		      eToken.addCell(namevalue);
		      
		      
		      eToken.setCellsFitPage(true);
		      document.add(table);
		      document.add(eToken);	
		      document.close();
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"E-Token.pdf");
				response.setContentLength(baos.size());
				ServletOutputStream out = response.getOutputStream();
				baos.writeTo(out);
				out.flush();
				
				
				if(eForm.getEtokenDTO().getAppStatus()==10 || eForm.getEtokenDTO().getAppStatus()==12){  // 10 initiaion complt, 12 maker hold
					
					if(eForm.getEtokenDTO().getCategory()==0){
					makerNormalQueue(eForm.getEtokenDTO().getTokenNumber(),eForm);
					}else{
						makerSpecialQueue(eForm.getEtokenDTO().getTokenNumber(),eForm);	
					}
					
				}
				if(eForm.getEtokenDTO().getAppStatus()==13 || eForm.getEtokenDTO().getAppStatus()==14){  // 13 maker complt, 14 checker hold
					
					if(eForm.getEtokenDTO().getCategory()==0){
					checkerNormalQueue(eForm.getEtokenDTO().getTokenNumber(),eForm);
					}else{
						checkerSpecialQueue(eForm.getEtokenDTO().getTokenNumber(),eForm);	
					}
					
				}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * 
	 */
	public static void makerNormalQueue(String eTokenNumber,ETokenForm eForm) throws Exception{
		
		
		try{
		Queue<String> makerNormalQueue=eForm.getMakerNormalQueue();
		makerNormalQueue.add(eTokenNumber);
		eForm.setMakerNormalQueue(makerNormalQueue);
		
		System.out.println("maker normal : "+eForm.getMakerNormalQueue().peek());
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
	/**
	 * 
	 * 
	 */
	public static void makerSpecialQueue(String eTokenNumber,ETokenForm eForm) throws Exception{
		
		
		try{
		Queue<String> makerSpecialQueue=eForm.getMakerSpecialQueue();
		makerSpecialQueue.add(eTokenNumber);
		eForm.setMakerSpecialQueue(makerSpecialQueue);
		
		System.out.println("maker special : "+eForm.getMakerSpecialQueue().peek());
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
	/**
	 * 
	 * 
	 */
	public static void checkerNormalQueue(String eTokenNumber,ETokenForm eForm) throws Exception{
		
		
		try{
		Queue<String> checkerNormalQueue=eForm.getCheckerNormalQueue();
		checkerNormalQueue.add(eTokenNumber);
		eForm.setCheckerNormalQueue(checkerNormalQueue);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
	/**
	 * 
	 * 
	 */
	public static void checkerSpecialQueue(String eTokenNumber,ETokenForm eForm) throws Exception{
		
		
		try{
		Queue<String> checkerSpecialQueue=eForm.getCheckerSpecialQueue();
		checkerSpecialQueue.add(eTokenNumber);
		eForm.setCheckerSpecialQueue(checkerSpecialQueue);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
	
	//added by tanushree for queue management
	

	@SuppressWarnings("unchecked")
	static	public  String makerQueue()
	{
		
		ETokenDAO ojb = new ETokenDAO();
		Queue<String> q1 = new PriorityQueue<String>();
		Queue<String> q2 = new PriorityQueue<String>();
		
		ArrayList<String> arrlist1 = new ArrayList();
		List<String> arrlist2 = new ArrayList<String>();
		
		
		try {
			    arrlist1 = ojb.getMakerNormalQueue();
			
				Iterator i = arrlist1.iterator();
				while(i.hasNext())
				{
					Object element = i.next();
					q1.add(element.toString());
				}
				System.out.println(q1);
			
			    arrlist2 = ojb.getMakerSpecialQueue();
			
				Iterator j = arrlist2.iterator();
				while(j.hasNext())
				{
					Object element = j.next();
					q2.add(element.toString());
				}
				System.out.println(q1);
			

		
			
		}
		        catch (Exception e) {
					
					e.printStackTrace();
				}
		

				if(q2.isEmpty())
				{
					return q2.poll();
				}
				else
				{
					return q1.poll();
				}
				
				
	}
	
	public Vector<com.wipro.igrs.device.applet.ETokenDTO> getCounterIDS(String officeID)
	{
		ETokenDAO dao = new ETokenDAO();
		ArrayList e = dao.getCounterIDS(officeID);
		String makerTime = dao.getCheckerWaitTime();
		Vector<com.wipro.igrs.device.applet.ETokenDTO> dt = new Vector<com.wipro.igrs.device.applet.ETokenDTO>();
		for(int i =0;i<e.size();i++)
		{
			ArrayList f = (ArrayList) e.get(i);
			com.wipro.igrs.device.applet.ETokenDTO dto = new com.wipro.igrs.device.applet.ETokenDTO();
			dto.setCounterName((String)f.get(0));
			dto.setCounterNo((String)f.get(1));
			dto.setCounterType("Maker");
			dto.setWaitTime(makerTime);
			dt.add(dto);
		}
		return dt;
		
		
	}
	
	public Vector<com.wipro.igrs.device.applet.ETokenDTO> getCounterIDSChecker(String officeID)
	{
		ETokenDAO dao = new ETokenDAO();
		ArrayList e = dao.getCounterIDSChecker(officeID);
		String checkerTime = dao.getCheckerWaitTime();
		Vector<com.wipro.igrs.device.applet.ETokenDTO> dt = new Vector<com.wipro.igrs.device.applet.ETokenDTO>();
		for(int i =0;i<e.size();i++)
		{
			ArrayList f = (ArrayList) e.get(i);
			com.wipro.igrs.device.applet.ETokenDTO dto = new com.wipro.igrs.device.applet.ETokenDTO();
			dto.setCounterName((String)f.get(0));
			dto.setCounterNo((String)f.get(1));
			dto.setCounterType("Checker");
			dto.setWaitTime(checkerTime);
			dt.add(dto);
		}
		return dt;
		
		
	}
	
	@SuppressWarnings("unchecked")
	static	public  void checkCounter(ETokenForm eForm , String officeID)
	{
		int counter = 0;
		String checker = "C";
		String maker = "M";
		String counterOfCheker = "";
		String check ;
		ETokenDAO ojb = new ETokenDAO();
		ETokenDTO eDTO = new ETokenDTO();
		String InsertionFlag = "Y";
		String counterType = null;
		
		String eTokenNumber = null;
		ArrayList makerCounterArr = new ArrayList();
		ArrayList submakerCounterArr = new ArrayList();
		String makerCounter=null;
		ETokenBD bd = new ETokenBD();
		try {
						
		//for counter type
			
			ArrayList list=bd.checkRegInitId(officeID, eForm);
			 			
			ArrayList rowList;
			String[] strArr=null;
			if(list!=null && list.size()==1){
				
				rowList=(ArrayList)list.get(0);
				
				String str=rowList.toString();
				str=str.substring(2, str.length()-2);
				
				strArr=str.split(",");
				
			}
			
			if(strArr[1].trim().equalsIgnoreCase("10")) {counterType = "Maker";}      // 10-initiation complete
			if(strArr[1].trim().equalsIgnoreCase("12")) {counterType = "Maker-Hold";}      // 12-maker hold
			if(strArr[1].trim().equalsIgnoreCase("13")) {counterType = "Checker";}      // 13-maker complete
			if(strArr[1].trim().equalsIgnoreCase("14")) {counterType = "Checker-Hold";}      // 14-checker hold
			
					
			//counter per day
			makerCounterArr = ojb.counterPerDay(counterType,officeID);
			if(makerCounterArr.size() != 0){
				submakerCounterArr = (ArrayList)makerCounterArr.get(0);
				makerCounter=(String)submakerCounterArr.get(0);
				counter=Integer.parseInt(makerCounter);
				
				
			}
	
			
		//creation of counter ID and assigning first token for each counter
			
		//maker
		if(counterType.equals("Maker")){
		for(int i=1;i<=counter;i++)
		{
			ArrayList ActiveCounterIds = bd.getCounterId(counter+1,officeID);
			
			
			
		//	 counterOfCheker = maker + i;

				//check = ojb.insertCounterCheck(counterOfCheker);
				
					eTokenNumber = bd.getEtokenNum();
					eForm.getEtokenDTO().setTokenNumber(eTokenNumber);
					ojb.insertCounterEtoken(eTokenNumber,counterOfCheker,counterType,eForm.getEtokenDTO().getCategory() ,
							"A",eForm.getEtokenDTO().getUserId(),eForm.getEtokenDTO().getRegInitId());
					InsertionFlag="N";
					break;
				
				
				
		}
		}
		
		//checker
		if(counterType.equals("Checker")){
			for(int i=1;i<=counter;i++)
			{
				 counterOfCheker = checker + i;
				 
					check = ojb.insertCounterCheck(counterOfCheker);
					if(check.equalsIgnoreCase("0")){
						
						ojb.insertCounterEtoken(eForm.getEtokenDTO().getTokenNumber(),counterOfCheker,counterType,eForm.getEtokenDTO().getCategory() ,
								"A",eForm.getEtokenDTO().getUserId(),eForm.getEtokenDTO().getRegInitId());
						InsertionFlag="N";
						break;
					}
					
					
			}
			}

		
		//Assigning token to counter having least no. etoken
		
		
		if(InsertionFlag.equalsIgnoreCase("Y")){
		
		List counters = new ArrayList();
		
		List subList = new ArrayList();
		List<ETokenDTO> mainList = new ArrayList<ETokenDTO>();
		ETokenDTO objETokenDTO = new ETokenDTO();
	
		counters = ojb.insertCounterCheckArray(counterType);
	
			for(int j= 0; j <counters.size(); j++)
			{
				ETokenDTO m = new ETokenDTO();
				subList = (ArrayList)counters.get(j);
				m.setCounterNumber((String)subList.get(0));
				System.out.println((String)subList.get(0));
				m.setCounter((String)subList.get(1));
				System.out.println((String)subList.get(1));
				mainList.add(m);
			}
			
			
			
			System.out.println("before sorting");
			for(int j= 0; j <mainList.size(); j++)
			{
				ETokenDTO  a = new ETokenDTO();
				a= mainList.get(j);
				
				System.out.println(a.getCounterNumber());
				System.out.println(a.getCounter());
				
			}

			
		
			Collections.sort(mainList,new ETokenBO());
			System.out.println("after sorting");
			
			  String shortestCounter = null;
			
			for(int j= 0; j <mainList.size(); j++)
			{
				ETokenDTO  a = new ETokenDTO();
				a= mainList.get(j);
				shortestCounter=a.getCounter();
				System.out.println(a.getCounterNumber());
				System.out.println(a.getCounter());
				
			}
			 
			 System.out.println("shortestCountershortestCountershortestCountershortestCountershortestCounter"+shortestCounter);
		 
	   
             //maker
			 if(counterType.equals("Maker")){
			 eTokenNumber = getEtoken("ETOK");
			 eForm.getEtokenDTO().setTokenNumber(eTokenNumber);
			 ojb.insertCounterEtoken(eTokenNumber,shortestCounter,counterType,eForm.getEtokenDTO().getCategory() ,
					"A",eForm.getEtokenDTO().getUserId(),eForm.getEtokenDTO().getRegInitId());
			 }
			 
			
			 
			//checker
			 if(counterType.equals("Checker")){
			 ojb.insertCounterEtoken( eForm.getEtokenDTO().getTokenNumber(),shortestCounter,counterType,eForm.getEtokenDTO().getCategory() ,
					"A",eForm.getEtokenDTO().getUserId(),eForm.getEtokenDTO().getRegInitId());
			 }

			

	
			
		}//end of flag
		
		

	//display of maker counter	
		ArrayList countersdata = new ArrayList();
		ArrayList subcountersdata = new ArrayList();
		ArrayList<ETokenDTO> maincountersdata = new ArrayList<ETokenDTO>();
		
		ArrayList distinctCounters = new ArrayList();
		ArrayList subDistinctCounters = new ArrayList();
		distinctCounters = ojb.distinctCounter();
		
		for(int z=0;z < distinctCounters.size();z++){
			subDistinctCounters = (ArrayList)distinctCounters.get(z);
			String C = (String)subDistinctCounters.get(0);
	        countersdata = ojb.getCounterData(C,"0");
	        if(countersdata.size()==0)
			{
				countersdata = ojb.getCounterData(C,"1");
			}
	        
	        for(int j= 0; j <countersdata.size(); j++)
			{
				ETokenDTO n = new ETokenDTO();
				subcountersdata = (ArrayList)countersdata.get(0);
				n.setEtokenNumberC((String)subcountersdata.get(0));
				n.setCounterNumberC((String)subcountersdata.get(1));
				n.setFlagC((String)subcountersdata.get(2));
				n.setStatusC((String)subcountersdata.get(3));
				n.setCreatedDateC((String)subcountersdata.get(4));
				n.setCreatedByC((String)subcountersdata.get(5));
				maincountersdata.add(n);
			}
	        
	    
		
		
		}
		
//		System.out.println("FFFFFFFFFFFFFF IIIIIIIIIIIIIIIIII FFFFFFFFFFFFFFFF OOOOOOOOOOOOOOo");
//		for(int j= 0; j <maincountersdata.size(); j++)
//		{
//			ETokenDTO  b = new ETokenDTO();
//			b= maincountersdata.get(j);
//			
//			System.out.println("EToken Number   "+b.getEtokenNumberC());
//			System.out.println("Counter Number "+b.getCounterNumberC());
//			System.out.println("Flag   "+b.getFlagC());
//			System.out.println("Status   "+b.getStatusC());
//			System.out.println("Created date   "+b.getCreatedDateC());
//			
//		}
//		
		
		//display of checker counter
		
		
		distinctCounters = ojb.distinctCheckerCounter();
		
		for(int z=0;z < distinctCounters.size();z++){
			subDistinctCounters = (ArrayList)distinctCounters.get(z);
			String C = (String)subDistinctCounters.get(0);
	        countersdata = ojb.getCounterCheckerData(C,"0");
	        if(countersdata.size()==0)
			{
				countersdata = ojb.getCounterCheckerData(C,"1");
			}
	        
	        for(int j= 0; j <countersdata.size(); j++)
			{
				ETokenDTO n = new ETokenDTO();
				subcountersdata = (ArrayList)countersdata.get(0);
				n.setEtokenNumberC((String)subcountersdata.get(0));
				n.setCounterNumberC((String)subcountersdata.get(1));
				n.setFlagC((String)subcountersdata.get(2));
				n.setStatusC((String)subcountersdata.get(3));
				n.setCreatedDateC((String)subcountersdata.get(4));
				n.setCreatedByC((String)subcountersdata.get(5));
				maincountersdata.add(n);
			}
	        
	    
		
		
		}
		
		System.out.println("FFFFFFFFFFFFFF IIIIIIIIIIIIIIIIII FFFFFFFFFFFFFFFF OOOOOOOOOOOOOOo");
		for(int j= 0; j <maincountersdata.size(); j++)
		{
			ETokenDTO  b = new ETokenDTO();
			b= maincountersdata.get(j);
			
			System.out.println("EToken Number   "+b.getEtokenNumberC());
			System.out.println("Counter Number "+b.getCounterNumberC());
			System.out.println("Flag   "+b.getFlagC());
			System.out.println("Status   "+b.getStatusC());
			System.out.println("Created date   "+b.getCreatedDateC());
			
		}
		
			

		
			
	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
	


    @Override
	public int compare(ETokenDTO e1, ETokenDTO e2) {
		
		int value1 = Integer.parseInt(e1.getCounterNumber());
		int value2 = Integer.parseInt(e2.getCounterNumber());
		
		if(value1 <  value2)
			return 1;
			else
				return -1;
		    
	}
    
    
    public void searchEToken(String regId, ETokenForm form,String officeId, String language) throws Exception
    {
    	ETokenBD bd = new ETokenBD();
		ArrayList list= new ArrayList();
		list =	bd.searchEToken(regId,officeId);
		
		if(list.size()!=0){
		ArrayList to = (ArrayList) list.get(0);
    	form.getEtokenDTO().setEtokenNumberC((String)to.get(0));
    	form.getEtokenDTO().setCreatedDateC((String)to.get(1));
    	form.getEtokenDTO().setCategory(Integer.parseInt((String)to.get(2)));
    	form.getEtokenDTO().setCancelMsg("");
		}
		else
		{
			if(language.equalsIgnoreCase("en"))
			form.getEtokenDTO().setCancelMsg("System Cannot find this E-Token");
			else
				form.getEtokenDTO().setCancelMsg("सिस्टम को ये ई टोकन नहीं मिला है");	
			form.getEtokenDTO().setEtokenNumberC("");
		}
//    	String rm = "Remarks";
//        form.getEtokenDTO().setETokenUpdateRemarks(rm);
    	

    }
    
    public boolean updateEToken(String regId, ETokenForm form, String language, String officeId) throws Exception
    {
    	ETokenBD bd = new ETokenBD();
		ArrayList list= new ArrayList();
		boolean check  = bd.cancelEToken(regId,form.getEtokenDTO().getUpdateRemarks(),String.valueOf(form.getEtokenDTO().getCategory()),language,officeId);
		
		if(check)
		{
			if(language.equalsIgnoreCase("en"))
			{
				form.getEtokenDTO().setCancelMsg("The E-Token Number has been canceled");
			}
			else
			{
				form.getEtokenDTO().setCancelMsg("ई-टोकन नंबर को रद्द कर दिया गया है");
			}
			form.getEtokenDTO().setEtokenNumberC("");
		}
		else
		{
			if(language.equalsIgnoreCase("en"))
			{
			form.getEtokenDTO().setCancelMsg("System Could not find this E-Token");
			}
			else
			{
				form.getEtokenDTO().setCancelMsg("सिस्टम को ये ई टोकन नहीं मिला है");
			}
			}
		return check;
		
		
    }


	public void setOfficeIp(String officeIds, String ip) {
		ETokenDAO dao = new ETokenDAO();
		dao.getOffice(officeIds,ip);
		
	}


	public com.wipro.igrs.device.applet.ETokenDTO getDtoUpdate(String regID, String officeId) {
		ETokenDAO dao = new ETokenDAO();
		ArrayList list = dao.getEtokenUpdateDto(regID,officeId);
		com.wipro.igrs.device.applet.ETokenDTO dto = new com.wipro.igrs.device.applet.ETokenDTO();
		if(list.size()>0)
		{
		ArrayList li = (ArrayList) list.get(0);
		
		dto.setEtokenNo(li.get(0).toString());
		if(li.get(1).toString().equalsIgnoreCase("Maker"))
		{
			dto.setStatus("11");
		}
		else
		{
			dto.setStatus("14");
		}
		dto.setTypeOfPerson(li.get(3).toString());
		dto.setRegistrationId(regID);
		return dto;
		}
		else
			return null;
		}


	public String getMakerCollectionCounterDetails(String regId) {
		ETokenDAO dao = new ETokenDAO();
		String counterNo = dao.getMakerCollectionCounterDetailsDao(regId);
		return counterNo;
	}
	
	@SuppressWarnings("unchecked")
	public String insertChecker(String regID, com.wipro.igrs.device.applet.ETokenDTO dto,String status) throws Exception
	{
		
		
		boolean boo = false;
		String checkRegIDM= "";
		String counterType = "";
		ETokenDAO ojb = new ETokenDAO();
		 checkRegIDM = status;
		 String officID="";
		 String tokenNo = "";
			ArrayList rowList;
		 

		
		 ArrayList list=ojb.getOfficeId(regID);
		 if(list!=null && list.size()==1){
				
				rowList=(ArrayList)list.get(0);
			 officID=(String) rowList.get(0);
			 tokenNo=(String) rowList.get(1);
		 }
		 String office_Name = ojb.getOfficeName(officID);
		 String catagory="Normal";
		 	String  noOfPersons ="1";
		 			     // 12-maker hold
			if(checkRegIDM.trim().equalsIgnoreCase("13")) {counterType = "Checker";}      // 13-maker complete
			boo = 	ojb.insertETokenDetailsS(tokenNo,noOfPersons,regID,"Checker",catagory,"","C","13",officID);
			
			
			
if(boo)
		
		return "Succesfully";
else
	return "false";
	}



}