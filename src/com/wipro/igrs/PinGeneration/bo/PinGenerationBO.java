package com.wipro.igrs.PinGeneration.bo;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lingala.zip4j.core.ZipFile;
import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import org.apache.log4j.Logger;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.PinGeneration.dao.PinGenerationDAO;
import com.wipro.igrs.PinGeneration.dto.PinGenerationDTO;
import com.wipro.igrs.PinGeneration.form.PinGenerationForm;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;
 
public class PinGenerationBO{
	
	private Logger logger = (Logger) Logger.getLogger(PinGenerationBO.class);
	PinGenerationDAO dao=new PinGenerationDAO();
	
	public PinGenerationBO(){
		
	}
	
	
	/**
     * ===========================================================================
     * Method : getStateList()
     * Description : Selecting all State from IGRS_STATE_MASTER . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     */

    public ArrayList getStateList() {
        return dao.getStateList();
    }
    /**
     * ===========================================================================
     * Method : getCountryList()
     * Description : Selecting all Country from IGRS_COUNTRY_MASTER . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     */
    public ArrayList getCountryList(String lang) {
        return dao.getCountryList(lang);
    }
    /**
     * ===========================================================================
     * Method : getDistrictList()
     * Description : Selecting all District from IGRS_DISTRICT_MASTER  . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     */

    public ArrayList getDistrictList(String state, String lang) {
        return dao.getDistrictList(state,lang);
    }
    public ArrayList getStateList(String state, String lang) {
        return dao.getStateList(state, lang);
    }
	
    /**
     * ===========================================================================
     * Method : getphotoIdList()
     * Description : Selecting all ID TYPE from IGRS_PHOTOID_PROOF_TYPE_MASTER . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    public ArrayList getphotoIdList(String lang) {
        return dao.getphotoIdList(lang);
    }
    
    
    
    /**
     * ===========================================================================
     * Method :  pinIDGenerator()
     * Description : This method generate PIN.
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
    public String pinIDGenerator(String seqTableName, String seqName) {
    	try{
        String id = dao.getSequenceNumber(seqTableName,seqName);
        
        return id;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    
    /**
     * ===========================================================================
     * Method : getphotoIdList()
     * Description : Selecting all ID TYPE from IGRS_PHOTOID_PROOF_TYPE_MASTER . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
	
	public ArrayList getPropertyList(String property){
		return dao.getPropertyList(property);
	}
	

	
	/**
	 * This method Generate pin for property
	 * @param request
	 * @param pingen
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * @return boolean
	 */
	
	public String generatePin(PinGenerationDTO pingen, String roleId, String funId, String userId){
		
		try{
		String pinreq=pinIDGenerator("IGRS_PINREQ","PINREQ");
	
		logger.debug("pinreq bo method"+pinreq);
		String verify =pingen.getDeedDocVerify();
		
		if(verify !=null && verify.equalsIgnoreCase("yes"))
			verify="y";
		
		else
			verify="n";	
		if(pingen.getRadioApplicent().equalsIgnoreCase("2"))
			{
				String pinReqPartyDetail[] = new String[28];
				pinReqPartyDetail[0]=pinreq;
				pinReqPartyDetail[1]="APPLICANT";
				pinReqPartyDetail[2]=pingen.getFirstName();
				pinReqPartyDetail[3]=pingen.getMiddleName();
				pinReqPartyDetail[4]=pingen.getLastName();
				
				String gen = pingen.getGender();
		        if (gen.equals("Male")){
		            gen = "M";
		        }
		        else{
		            gen = "F";
		        }
				pinReqPartyDetail[5]=gen;
				pinReqPartyDetail[6]=pingen.getAge();
				pinReqPartyDetail[7]=pingen.getCountry();
				pinReqPartyDetail[8]=pingen.getState();
				pinReqPartyDetail[9]=pingen.getDistrict();
				pinReqPartyDetail[10]=pingen.getAddress();
				pinReqPartyDetail[11]=pingen.getPostelCode();
				pinReqPartyDetail[12]=pingen.getPhoneNumber();
				pinReqPartyDetail[13]=pingen.getMobileNumber();
				pinReqPartyDetail[14]=pingen.getEmailID();
				pinReqPartyDetail[15]=pingen.getIdName();
				pinReqPartyDetail[16]=pingen.getIdProofNumber();
				pinReqPartyDetail[17]=pingen.getFatherName();
				pinReqPartyDetail[18]=pingen.getMotherName();
				pinReqPartyDetail[19]=pingen.getSpouseName();
				pinReqPartyDetail[20]=pingen.getAgentProofPath();
				pinReqPartyDetail[21]=pingen.getAgentProofNameDeath();
				pinReqPartyDetail[22]=pingen.getAgentProofNameMutation();
				pinReqPartyDetail[23]=pingen.getAgentProofNameRelation();
				pinReqPartyDetail[24]=pingen.getRemarks();
				pinReqPartyDetail[25]=pingen.getRelation();
				pinReqPartyDetail[26]=pingen.getRegNoSearch();
				pinReqPartyDetail[27]=verify;
				 dao.generatePin(pinReqPartyDetail);
			}
				else if(pingen.getRadioApplicent().equalsIgnoreCase("1"))
				{
					String pinReqPartyDetail[] = new String[7];
					pinReqPartyDetail[0]=pinreq;
					pinReqPartyDetail[1]="OWNER";
					pinReqPartyDetail[2]=pingen.getRegNoSearch();
					pinReqPartyDetail[3]=pingen.getAgentProofPath();
					pinReqPartyDetail[4]=pingen.getAgentProofNameDoc();
					pinReqPartyDetail[5]=verify;
					pinReqPartyDetail[6]=pingen.getRemarks();
					 dao.generatePin(pinReqPartyDetail);
				}
		if(pingen.getCloneSelectedItems()!=null&&!pingen.getCloneSelectedItems().isEmpty()){
			ArrayList selected = new ArrayList();

			for(int i=0;i<pingen.getCloneSelectedItems().size();i++)
			{
				PinGenerationDTO dto1=(PinGenerationDTO) pingen.getCloneSelectedItems().get(i);
				
					String propId= dto1.getPropertyTxnId();
					
				selected.add(propId);	
	
				pingen.setSelectedItems(selected);	
				
				
			}
			
		}
				
		
		
			if(dao.pinGeneration(pingen.getSelectedItems(), pingen,userId))
			{
				return pinreq;
			}
			else
			{
				return "";
			}
		}
		catch(Exception ex){
			logger.debug("Inside pin req not found");
		ex.printStackTrace();
		return "";
		}
	}


public String generatePinNew(PinGenerationDTO pingen, String roleId, String funId, String userId){
		
		try{
		String pinreq=pinIDGenerator("IGRS_PINREQ","PINREQ");
	
		logger.debug("pinreq bo method"+pinreq);
		String verify =pingen.getDeedDocVerify();
		
		if(verify !=null && verify.equalsIgnoreCase("yes"))
			verify="y";
		
		else
			verify="n";	
		if(pingen.getRadioApplicent().equalsIgnoreCase("2"))
			{
				String pinReqPartyDetail[] = new String[28];
				pinReqPartyDetail[0]=pinreq;
				pinReqPartyDetail[1]="APPLICANT";
				pinReqPartyDetail[2]=pingen.getFirstName();
				pinReqPartyDetail[3]=pingen.getMiddleName();
				pinReqPartyDetail[4]=pingen.getLastName();
				
				String gen = pingen.getGender();
		        if (gen.equals("Male")){
		            gen = "M";
		        }
		        else{
		            gen = "F";
		        }
				pinReqPartyDetail[5]=gen;
				pinReqPartyDetail[6]=pingen.getAge();
				pinReqPartyDetail[7]=pingen.getCountry();
				pinReqPartyDetail[8]=pingen.getState();
				pinReqPartyDetail[9]=pingen.getDistrict();
				pinReqPartyDetail[10]=pingen.getAddress();
				pinReqPartyDetail[11]=pingen.getPostelCode();
				pinReqPartyDetail[12]=pingen.getPhoneNumber();
				pinReqPartyDetail[13]=pingen.getMobileNumber();
				pinReqPartyDetail[14]=pingen.getEmailID();
				pinReqPartyDetail[15]=pingen.getIdName();
				pinReqPartyDetail[16]=pingen.getIdProofNumber();
				pinReqPartyDetail[17]=pingen.getFatherName();
				pinReqPartyDetail[18]=pingen.getMotherName();
				pinReqPartyDetail[19]=pingen.getSpouseName();
				pinReqPartyDetail[20]=pingen.getAgentProofPath();
				pinReqPartyDetail[21]=pingen.getAgentProofNameDeath();
				pinReqPartyDetail[22]=pingen.getAgentProofNameMutation();
				pinReqPartyDetail[23]=pingen.getAgentProofNameRelation();
				pinReqPartyDetail[24]=pingen.getRemarks();
				pinReqPartyDetail[25]=pingen.getRelation();
				pinReqPartyDetail[26]=pingen.getRegNoSearch();
				pinReqPartyDetail[27]=verify;
				 dao.generatePin(pinReqPartyDetail);
			}
				else if(pingen.getRadioApplicent().equalsIgnoreCase("1"))
				{
					String pinReqPartyDetail[] = new String[7];
					pinReqPartyDetail[0]=pinreq;
					pinReqPartyDetail[1]="OWNER";
					pinReqPartyDetail[2]=pingen.getRegNoSearch();
					pinReqPartyDetail[3]=pingen.getAgentProofPath();
					pinReqPartyDetail[4]=pingen.getAgentProofNameDoc();
					pinReqPartyDetail[5]=verify;
					pinReqPartyDetail[6]=pingen.getRemarks();
					 dao.generatePin(pinReqPartyDetail);
				}
		/*if(pingen.getCloneSelectedItems()!=null&&!pingen.getCloneSelectedItems().isEmpty()){
			ArrayList selected = new ArrayList();

			for(int i=0;i<pingen.getCloneSelectedItems().size();i++)
			{
				PinGenerationDTO dto1=(PinGenerationDTO) pingen.getCloneSelectedItems().get(i);
				
					String propId= dto1.getPropertyTxnId();
					
				selected.add(propId);	
	
				pingen.setSelectedItems(selected);	
				
				
			}
			
		}*/
				
		
		
			if(dao.pinGeneration(pingen.getSelectedItems(), pingen,userId))
			{
				return pinreq;
			}
			else
			{
				return "";
			}
		}
		catch(Exception ex){
			logger.debug("Inside pin req not found");
		ex.printStackTrace();
		return "";
		}
	}



/**
	 * This method is used to display pin details
	 * @param reqId
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 */
	
public ArrayList viewDetails(String reqId,String fromDate,String toDate){
		ArrayList list =new ArrayList();
		
		ArrayList list2 =dao.viewDetails(reqId,fromDate,toDate);
		for(int i=0;i<list2.size();i++){
			ArrayList list1=(ArrayList)list2.get(i);
			PinGenerationForm form =new PinGenerationForm();
			form.getPinGenerationDTO().setUserID((String)list1.get(0));
			form.getPinGenerationDTO().setPinReqTxnNo((String)list1.get(1));
			form.getPinGenerationDTO().setReqDate((String)list1.get(2));
			form.getPinGenerationDTO().setFirstName((String)list1.get(3));
			form.getPinGenerationDTO().setMiddleName((String)list1.get(4));
			form.getPinGenerationDTO().setLastName((String)list1.get(5));
			form.getPinGenerationDTO().setGender((String)list1.get(6));
			form.getPinGenerationDTO().setAge((String)list1.get(7));
			form.getPinGenerationDTO().setFatherName((String)list1.get(8));
			form.getPinGenerationDTO().setMotherName((String)list1.get(9));
			form.getPinGenerationDTO().setSpouseName((String)list1.get(10));
			form.getPinGenerationDTO().setAddress((String)list1.get(11));
			form.getPinGenerationDTO().setCountryId((String)list1.get(12));
			form.getPinGenerationDTO().setStateId((String)list1.get(13));
			form.getPinGenerationDTO().setDistrictId((String)list1.get(14));
			form.getPinGenerationDTO().setPostelCode((String)list1.get(15));
			form.getPinGenerationDTO().setPhoneNumber((String)list1.get(16));
			form.getPinGenerationDTO().setMobileNumber((String)list1.get(17));
			form.getPinGenerationDTO().setEmailID((String)list1.get(18));
			form.getPinGenerationDTO().setIdProofNumber((String)list1.get(19));
			form.getPinGenerationDTO().setRegistrationNumber((String)list1.get(20));
			form.getPinGenerationDTO().setDeedDocVerify((String)list1.get(21));
			form.getPinGenerationDTO().setRemarks((String)list1.get(22));
			form.getPinGenerationDTO().setPropertyTransId((String)list1.get(23));
			form.getPinGenerationDTO().setDeathCertificate((String)list1.get(24));
			form.getPinGenerationDTO().setMutationDocument((String)list1.get(25));
			//form.getPinGenerationDTO().setMutationfile((File)list1.get(26));
			list.add(form);
					
		}
			
		return list;
	}

	
	/**
    * 
    * @param regNo
    * @param propertyNo
    * @return ArrayList
    */     
          
	public ArrayList check(String regNo,String propertyNo){
		return dao.check(regNo,propertyNo);
	}
	


	/**
     * ===========================================================================
     * Method :  getUpdateDate()
     * Description : Get the  updatedate of the pin. 
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 6 June,2008
     * ===========================================================================
     * 
     */	
	public ArrayList getUpdateDate(String pinReqNo){
		return dao.getUpdateDate(pinReqNo);
	}
	


	/**
     * ===========================================================================
     * Method :  getOfficeId()
     * Description : Get the  office id. 
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 6 June,2008
     * ===========================================================================
     * 
     */	
     
	public ArrayList getOfficeId(String pinReqNo){
		return dao.getOfficeId(pinReqNo);
	}

	/**
     * ===========================================================================
     * Method :  readBLOBToFileGet()
     * Description : Get the  BLOB File(death file). 
     * return type : ResultSet
     * Author : Samuel Prabhakaran
     * Created Date : 6 June,2008
     * ===========================================================================
     * 
     */	
     
	public void readBLOBToFileGet(HttpServletResponse res,String pinReqNo){
		
		String              sqlText                     = null;
		BLOB                image                       = null;
		String contentName=null;
		OutputStream os = null;
		String ext = null;
		String docObject= "DOCUMENT_OBJ";
		String docExtn  = "DOCUMENT_EXTN";
		try{
			ResultSet rst = dao.readBLOBToFileGet(pinReqNo);
			 while(rst.next()){
				   
				   
			        image = ((OracleResultSet) rst).getBLOB(docObject);
			        
			 	   
			        contentName = ((OracleResultSet) rst).getString(docExtn);
			      
			 	   
			    }
			   System.out.println("after excuting while loop:"+sqlText);
				System.out.println("Content Name :"+contentName);
			    os = res.getOutputStream();
				long index = 1;
				if(contentName!=null)
						{
					//System.out.println(" Before exception ");
							ext = contentName.substring(contentName.length()-3, contentName.length());
							System.out.println(" \n\next "+ext);
							if(ext!=null){
									if (ext.equalsIgnoreCase("doc")){
										res.setContentType("application/download");
									}
									else if (ext.equalsIgnoreCase("ppt")){
										res.setContentType("application/download");
									}
									else if (ext.equalsIgnoreCase("xls")){
										res.setContentType("application/download");
									}
									else if (ext.equalsIgnoreCase("htm")){
										res.setContentType("text/html");
									}
									else if (ext.equalsIgnoreCase("tml")){
										res.setContentType("text/html");
									}
									else if (ext.equalsIgnoreCase("txt")){
										res.setContentType("text/plain");
									}
									else if (ext.equalsIgnoreCase("pdf")){
										res.setContentType("application/pdf");
									}
									else if (ext.equalsIgnoreCase("bmp")){
										res.setContentType("image/x-bmp");
									}
									else if (ext.equalsIgnoreCase("gif")){
										res.setContentType("image/gif");
									}
									else if (ext.equalsIgnoreCase("jpg")){
										res.setContentType("image/jpeg");
									}
									else if (ext.equalsIgnoreCase("peg")){
										res.setContentType("image/jpeg");
									}
									else
									{
										res.setContentType("application/download");
									}
							} 
							
						}

			    //res.setHeader ("Content-Disposition", "attachment; filename="+contentName);

			    
			    // Loop through while reading a data from the BLOB
			    // column using the getBytes() method. This data will be stored
			    // in a BLOB column and writting in OutPutStream.
				while (index < image.length()) {
					os.write(image.getBytes(index, 10000));
				
					os.flush();
					index += 10000;
				}

			} catch (IOException e) {
			    System.out.println("Caught I/O Exception: (Write BLOB value to file - Get Method).");
			    e.printStackTrace();
			    
			} catch (SQLException e) {
			    System.out.println("Caught SQL Exception: (Write BLOB value to file - Get Method).");
			    System.out.println("SQL:\n" + sqlText);
			    e.printStackTrace();
			    System.out.println(
						" DBUtility:downloadContent:finally :General Exception Clause"
							+ e.toString());
			}
			
		
	}

	/**
     * ===========================================================================
     * Method :  readBLOBToFileGet1()
     * Description : Get the  BLOB File(mutation file). 
     * return type : ResultSet
     * Author : Samuel Prabhakaran
     * Created Date : 6 June,2008
     * ===========================================================================
     * 
     */	
	public void readBLOBToFileGet1(HttpServletResponse res,String pinReqNo){
		String              sqlText                     = null;
		BLOB                image                       = null;
		String contentName=null;
		OutputStream os = null;
		String ext = null;
		String docObject= "DOCUMENT_OBJ";
		String docExtn  = "DOCUMENT_EXTN";
		
		try{
			ResultSet rst=dao.readBLOBToFileGet1(pinReqNo);
			while(rst.next()){
				   
				   
			     image = ((OracleResultSet) rst).getBLOB(docObject);
			     
				   
			     contentName = ((OracleResultSet) rst).getString(docExtn);
			     ;
				   
			 }
			System.out.println("after excuting while loop:"+sqlText);
				System.out.println("Content Name :"+contentName);
			 os = res.getOutputStream();
				long index = 1;
				if(contentName!=null)
						{
					//System.out.println(" Before exception ");
							ext = contentName.substring(contentName.length()-3, contentName.length());
							System.out.println(" \n\next "+ext);
							if(ext!=null){
									if (ext.equalsIgnoreCase("doc")){
										res.setContentType("application/download");
									}
									else if (ext.equalsIgnoreCase("ppt")){
										res.setContentType("application/download");
									}
									else if (ext.equalsIgnoreCase("xls")){
										res.setContentType("application/download");
									}
									else if (ext.equalsIgnoreCase("htm")){
										res.setContentType("text/html");
									}
									else if (ext.equalsIgnoreCase("tml")){
										res.setContentType("text/html");
									}
									else if (ext.equalsIgnoreCase("txt")){
										res.setContentType("text/plain");
									}
									else if (ext.equalsIgnoreCase("pdf")){
										res.setContentType("application/pdf");
									}
									else if (ext.equalsIgnoreCase("bmp")){
										res.setContentType("image/x-bmp");
									}
									else if (ext.equalsIgnoreCase("gif")){
										res.setContentType("image/gif");
									}
									else if (ext.equalsIgnoreCase("jpg")){
										res.setContentType("image/jpeg");
									}
									else if (ext.equalsIgnoreCase("peg")){
										res.setContentType("image/jpeg");
									}
									else
									{
										res.setContentType("application/download");
									}
							} 
							
						}

			 //res.setHeader ("Content-Disposition", "attachment; filename="+contentName);

			 
			 // Loop through while reading a data from the BLOB
			 // column using the getBytes() method. This data will be stored
			 // in a BLOB column and writting in OutPutStream.
				while (index < image.length()) {
					os.write(image.getBytes(index, 10000));
					
					os.flush();
					index += 10000;
				}

			} catch (IOException e) {
			 System.out.println("Caught I/O Exception: (Write BLOB value to file - Get Method).");
			 e.printStackTrace();
			 
			} catch (SQLException e) {
			 System.out.println("Caught SQL Exception: (Write BLOB value to file - Get Method).");
			 System.out.println("SQL:\n" + sqlText);
			 e.printStackTrace();
			 System.out.println(
						" DBUtility:downloadContent:finally :General Exception Clause"
							+ e.toString());
			}
		
	}

	public ArrayList regIdCheckInfo(String regisId) throws Exception{
		return dao.regIdCheckInfo(regisId);
	}
	 public ArrayList getClaimaintDetails(String propId)throws ServletException,IOException,SQLException,Exception{
		 return dao.getClaimaintDetails(propId);
	 
	 }
	 public String getDeedID(String getDeedID) throws Exception {
			return dao.getDeedID(getDeedID);
		}

	 public void printPin(ArrayList propIds, PinGenerationDTO pingen,HttpServletRequest request,  HttpServletResponse response) throws Exception{
		    
		    logger.debug("Inside printPin method");
		   	OutputStream os = response.getOutputStream();
			ZipOutputStream zos = new ZipOutputStream(os);
			String regInitNumber=pingen.getRegistrationNumber();
			ArrayList pinList = dao.pinDetails(propIds);
			
			try{
			for(int i = 0 ; i < pinList.size() ; i++)
			{
				RegCompCheckerDTO rrdto = (RegCompCheckerDTO)pinList.get(i);
				logger.debug("Inside printPin List >>>"+rrdto);
				String fileFolderPath=RegCompCheckerConstant.FILE_UPLOAD_PATH+regInitNumber+"/";
				 File folder = new File(fileFolderPath);
				 logger.debug("Inside printPin folder path >>>"+folder);
				    if (!folder.exists()) {
				          folder.mkdirs();
				    }
				String fileName="PinDoc"+(i+1)+"_"+rrdto.getPropertyId()+".pdf";
				File fileToDownload = new File(fileFolderPath+fileName);
				 logger.debug("Inside printPin fileToDownload path >>>"+fileToDownload);
				FileOutputStream baos=new FileOutputStream(fileToDownload);
				Document document = new Document(PageSize.A4, 20, 20, 20, 20);
		
				PdfWriter writer = PdfWriter.getInstance(document, baos);
				document.open(); 
				Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				row.setHeader(true);
				row.setColspan(25);
				Table datatable = new Table(25);
				datatable.setWidth(100);
				datatable.setPadding(3);
				
				Cell title = new Cell(new Phrase("PIN DOCUMENT", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
				title.setHorizontalAlignment(Element.ALIGN_CENTER);
				title.setLeading(20);
				title.setColspan(25);
				title.setBorder(Rectangle.NO_BORDER);
				title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(title);
				datatable.setAlignment(1);
				datatable.addCell(row); 
				
				Cell sectionheader1=new Cell(new Phrase("E- REGISTRATION NUMBER - " + regInitNumber , FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
				sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
				sectionheader1.setColspan(25);
				sectionheader1.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(sectionheader1);
				datatable.setAlignment(1);
				
				Cell line01=new Cell(new Phrase("PROPERTY ID", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line01.setColspan(5);
				line01.setBorder(Rectangle.NO_BORDER);
				line01.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line01);
				datatable.setAlignment(1);
				
				Cell line02=new Cell(new Phrase("PROPERTY TYPE", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line02.setColspan(5);
				line02.setBorder(Rectangle.NO_BORDER);
				line02.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line02);
				datatable.setAlignment(1);
				
				Cell line03=new Cell(new Phrase("DISTRICT", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line03.setColspan(5);
				line03.setBorder(Rectangle.NO_BORDER);
				line03.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line03);
				datatable.setAlignment(1);
				
				Cell line04=new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line04.setColspan(5);
				line04.setBorder(Rectangle.NO_BORDER);
				line04.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line04);
				datatable.setAlignment(1);
				
				Cell line05=new Cell(new Phrase("PIN NUMBER", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line05.setColspan(5);
				line05.setBorder(Rectangle.NO_BORDER);
				line05.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line05);
				datatable.setAlignment(1);
				
				Cell line1=new Cell(new Phrase(rrdto.getPropertyId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line1.setColspan(5);
				line1.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line1);
				datatable.setAlignment(1);
				
				Cell line2=new Cell(new Phrase(rrdto.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line2.setColspan(5);
				line2.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line2);
				datatable.setAlignment(1);
				
				Cell line3=new Cell(new Phrase(rrdto.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line3.setColspan(5);
				line3.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line3);
				datatable.setAlignment(1);
				
				Cell line4=new Cell(new Phrase(rrdto.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line4.setColspan(5);
				line4.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line4);
				datatable.setAlignment(1);
				
				Cell line5=new Cell(new Phrase(rrdto.getPinNumber(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line5.setColspan(5);
				line5.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line5);
				datatable.setAlignment(1);
				
				datatable.setCellsFitPage(true);
				document.add(datatable); 
				document.close();
				baos.close();
				
				ZipEntry entry = new ZipEntry("PinDoc"+(i+1)+"_"+rrdto.getPropertyId()+".pdf");	
				if(fileToDownload.getParentFile().exists()==false)
				{
					fileToDownload.getParentFile().mkdirs();
				}
				FileInputStream fileInputStream = new FileInputStream(fileToDownload);
			    byte[] buf = new byte[(int) fileToDownload.length()];
			    fileInputStream.read(buf);
			    fileInputStream.close();
			    zos.putNextEntry(entry);
			    zos.write(buf);
			    zos.closeEntry(); 
			    }
			    zos.close();
				
	 }catch(IOException ie){
		 System.out.println("File not found exception"+ie);;
	 }
			catch(Exception e ){
				e.printStackTrace();
	 }
		finally{
				if(null!=zos)
					zos.close();
				if(null!=os)os.close();
			}
			}
	 public String printPin1(ArrayList propIds, PinGenerationDTO pingen,HttpServletRequest request,  HttpServletResponse response) throws Exception{
		    String retPath="D:/Upload/Zip.zip";
		    logger.debug("Inside printPin method");
		   	OutputStream os = new FileOutputStream(new File(retPath));
			ZipOutputStream zos = new ZipOutputStream(os);
			String regInitNumber=pingen.getRegistrationNumber();
			ArrayList pinList = dao.pinDetails(propIds);
			
			try{
			for(int i = 0 ; i < pinList.size() ; i++)
			{
				RegCompCheckerDTO rrdto = (RegCompCheckerDTO)pinList.get(i);
				logger.debug("Inside printPin List >>>"+rrdto);
				String fileFolderPath=RegCompCheckerConstant.FILE_UPLOAD_PATH+regInitNumber+"/";
				 File folder = new File(fileFolderPath);
				 logger.debug("Inside printPin folder path >>>"+folder);
				    if (!folder.exists()) {
				          folder.mkdirs();
				    }
				String fileName="PinDoc"+(i+1)+"_"+rrdto.getPropertyId()+".pdf";
				File fileToDownload = new File(fileFolderPath+fileName);
				 logger.debug("Inside printPin fileToDownload path >>>"+fileToDownload);
				FileOutputStream baos=new FileOutputStream(fileToDownload);
				Document document = new Document(PageSize.A4, 20, 20, 20, 20);
		
				PdfWriter writer = PdfWriter.getInstance(document, baos);
				document.open(); 
				Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				row.setHeader(true);
				row.setColspan(25);
				Table datatable = new Table(25);
				datatable.setWidth(100);
				datatable.setPadding(3);
				
				Cell title = new Cell(new Phrase("PIN DOCUMENT", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
				title.setHorizontalAlignment(Element.ALIGN_CENTER);
				title.setLeading(20);
				title.setColspan(25);
				title.setBorder(Rectangle.NO_BORDER);
				title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(title);
				datatable.setAlignment(1);
				datatable.addCell(row); 
				
				Cell sectionheader1=new Cell(new Phrase("E- REGISTRATION NUMBER - " + regInitNumber , FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
				sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
				sectionheader1.setColspan(25);
				sectionheader1.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(sectionheader1);
				datatable.setAlignment(1);
				
				Cell line01=new Cell(new Phrase("PROPERTY ID", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line01.setColspan(5);
				line01.setBorder(Rectangle.NO_BORDER);
				line01.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line01);
				datatable.setAlignment(1);
				
				Cell line02=new Cell(new Phrase("PROPERTY TYPE", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line02.setColspan(5);
				line02.setBorder(Rectangle.NO_BORDER);
				line02.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line02);
				datatable.setAlignment(1);
				
				Cell line03=new Cell(new Phrase("DISTRICT", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line03.setColspan(5);
				line03.setBorder(Rectangle.NO_BORDER);
				line03.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line03);
				datatable.setAlignment(1);
				
				Cell line04=new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line04.setColspan(5);
				line04.setBorder(Rectangle.NO_BORDER);
				line04.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line04);
				datatable.setAlignment(1);
				
				Cell line05=new Cell(new Phrase("PIN NUMBER", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line05.setColspan(5);
				line05.setBorder(Rectangle.NO_BORDER);
				line05.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line05);
				datatable.setAlignment(1);
				
				Cell line1=new Cell(new Phrase(rrdto.getPropertyId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line1.setColspan(5);
				line1.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line1);
				datatable.setAlignment(1);
				
				Cell line2=new Cell(new Phrase(rrdto.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line2.setColspan(5);
				line2.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line2);
				datatable.setAlignment(1);
				
				Cell line3=new Cell(new Phrase(rrdto.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line3.setColspan(5);
				line3.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line3);
				datatable.setAlignment(1);
				
				Cell line4=new Cell(new Phrase(rrdto.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line4.setColspan(5);
				line4.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line4);
				datatable.setAlignment(1);
				
				Cell line5=new Cell(new Phrase(rrdto.getPinNumber(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line5.setColspan(5);
				line5.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line5);
				datatable.setAlignment(1);
				
				datatable.setCellsFitPage(true);
				document.add(datatable); 
				document.close();
				baos.close();
				
				ZipEntry entry = new ZipEntry("PinDoc"+(i+1)+"_"+rrdto.getPropertyId()+".pdf");	
				if(fileToDownload.getParentFile().exists()==false)
				{
					fileToDownload.getParentFile().mkdirs();
				}
				FileInputStream fileInputStream = new FileInputStream(fileToDownload);
			    byte[] buf = new byte[(int) fileToDownload.length()];
			    fileInputStream.read(buf);
			    fileInputStream.close();
			    zos.putNextEntry(entry);
			    zos.write(buf);
			    //zos.closeEntry(); 
			    }
			    zos.close();
				
	 }catch(IOException ie){
		 System.out.println("File not found exception"+ie);;
	 }
			catch(Exception e ){
				e.printStackTrace();
	 }
		finally{
				if(null!=zos)
					zos.close();
				if(null!=os)os.close();
			}
		return retPath;
			}

	 public String printPin2(ArrayList propIds, PinGenerationDTO pingen,HttpServletRequest request,  HttpServletResponse response) throws Exception{
		    String retPath="D:/Upload/Zip.zip";
		    logger.debug("Inside printPin method");
		    ZipFile zip = new ZipFile(retPath);
		   	//OutputStream os = new FileOutputStream(new File(retPath));
			//ZipOutputStream zos = new ZipOutputStream(os);
			String regInitNumber=pingen.getRegistrationNumber();
			ArrayList pinList = dao.pinDetails(propIds);
			ArrayList al = new ArrayList();
			try{
			for(int i = 0 ; i < pinList.size() ; i++)
			{
				RegCompCheckerDTO rrdto = (RegCompCheckerDTO)pinList.get(i);
				logger.debug("Inside printPin List >>>"+rrdto);
				String fileFolderPath=RegCompCheckerConstant.FILE_UPLOAD_PATH+regInitNumber+"/";
				 File folder = new File(fileFolderPath);
				 logger.debug("Inside printPin folder path >>>"+folder);
				    if (!folder.exists()) {
				          folder.mkdirs();
				    }
				String fileName="PinDoc"+(i+1)+"_"+rrdto.getPropertyId()+".pdf";
				File fileToDownload = new File(fileFolderPath+fileName);
				 logger.debug("Inside printPin fileToDownload path >>>"+fileToDownload);
				FileOutputStream baos=new FileOutputStream(fileToDownload);
				Document document = new Document(PageSize.A4, 20, 20, 20, 20);
		
				PdfWriter writer = PdfWriter.getInstance(document, baos);
				document.open(); 
				Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				row.setHeader(true);
				row.setColspan(25);
				Table datatable = new Table(25);
				datatable.setWidth(100);
				datatable.setPadding(3);
				
				Cell title = new Cell(new Phrase("PIN DOCUMENT", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
				title.setHorizontalAlignment(Element.ALIGN_CENTER);
				title.setLeading(20);
				title.setColspan(25);
				title.setBorder(Rectangle.NO_BORDER);
				title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(title);
				datatable.setAlignment(1);
				datatable.addCell(row); 
				
				Cell sectionheader1=new Cell(new Phrase("E- REGISTRATION NUMBER - " + regInitNumber , FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
				sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
				sectionheader1.setColspan(25);
				sectionheader1.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(sectionheader1);
				datatable.setAlignment(1);
				
				Cell line01=new Cell(new Phrase("PROPERTY ID", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line01.setColspan(5);
				line01.setBorder(Rectangle.NO_BORDER);
				line01.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line01);
				datatable.setAlignment(1);
				
				Cell line02=new Cell(new Phrase("PROPERTY TYPE", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line02.setColspan(5);
				line02.setBorder(Rectangle.NO_BORDER);
				line02.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line02);
				datatable.setAlignment(1);
				
				Cell line03=new Cell(new Phrase("DISTRICT", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line03.setColspan(5);
				line03.setBorder(Rectangle.NO_BORDER);
				line03.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line03);
				datatable.setAlignment(1);
				
				Cell line04=new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line04.setColspan(5);
				line04.setBorder(Rectangle.NO_BORDER);
				line04.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line04);
				datatable.setAlignment(1);
				
				Cell line05=new Cell(new Phrase("PIN NUMBER", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
				line05.setColspan(5);
				line05.setBorder(Rectangle.NO_BORDER);
				line05.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				datatable.addCell(line05);
				datatable.setAlignment(1);
				
				Cell line1=new Cell(new Phrase(rrdto.getPropertyId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line1.setColspan(5);
				line1.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line1);
				datatable.setAlignment(1);
				
				Cell line2=new Cell(new Phrase(rrdto.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line2.setColspan(5);
				line2.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line2);
				datatable.setAlignment(1);
				
				Cell line3=new Cell(new Phrase(rrdto.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line3.setColspan(5);
				line3.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line3);
				datatable.setAlignment(1);
				
				Cell line4=new Cell(new Phrase(rrdto.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line4.setColspan(5);
				line4.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line4);
				datatable.setAlignment(1);
				
				Cell line5=new Cell(new Phrase(rrdto.getPinNumber(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				line5.setColspan(5);
				line5.setBorder(Rectangle.NO_BORDER);
				datatable.addCell(line5);
				datatable.setAlignment(1);
				
				datatable.setCellsFitPage(true);
				document.add(datatable); 
				document.close();
				baos.close();
				
				ZipEntry entry = new ZipEntry("PinDoc"+(i+1)+"_"+rrdto.getPropertyId()+".pdf");	
				if(fileToDownload.getParentFile().exists()==false)
				{
					fileToDownload.getParentFile().mkdirs();
				}
				FileInputStream fileInputStream = new FileInputStream(fileToDownload);
			    byte[] buf = new byte[(int) fileToDownload.length()];
			    fileInputStream.read(buf);
			    fileInputStream.close();
			   al.add(fileToDownload) ;
			    }
			   // zos.close();
				
	 }catch(IOException ie){
		 System.out.println("File not found exception"+ie);;
	 }
			catch(Exception e ){
				e.printStackTrace();
	 }
		finally{
				
			}
		return retPath;
			}

	 public String getRegTxnID(String pingen)
	 {
	 	return dao.getRegTxnID(pingen);
	 }
	 
	 public String checkPinStatus(PinGenerationDTO pingen)
	 {
	 	return dao.checkPinStatus(pingen);
	 }
	 
	 public boolean changePin(PinGenerationDTO pingen)
	 {
	 	return dao.changePin(pingen);
	 }
}
