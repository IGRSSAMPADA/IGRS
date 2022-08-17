package com.wipro.igrs.caveats.bd;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.RegistrationCertificate;
import com.newgen.burning.RegistrationSeal;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.Seals.constant.SealsConstant;
import com.wipro.igrs.Seals.dao.SealsDAO;
import com.wipro.igrs.Seals.form.SealsForm;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dao.DocumentFlagEvaluationDAO;
import com.wipro.igrs.caveats.form.DocumentFlagEvaluationForm;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;

public class DocumentFlagEvaluationBD {
	
	private  Logger logger = (Logger) Logger.getLogger(DocumentFlagEvaluationBD.class);
	DocumentFlagEvaluationDAO dao =  new DocumentFlagEvaluationDAO();
	
	public List getRegDetails(String regNo) throws Exception{
		
		List list = dao.getRegDetails(regNo);
		return list;	
		
	}
	
	public String  getCompletionNumber(String regInitId) throws Exception{
		//RegCompCheckerDAO regCompDAO =  new RegCompCheckerDAO();
		return dao.getCompletionNumber(regInitId);
	}
	
	public boolean checkFinalDocGen(String regInitId) throws Exception{
		boolean flag = false;
		//RegCompCheckerDAO regCompDAO =  new RegCompCheckerDAO();
		String status = dao.checkFinalDocGenFlag(regInitId);
		if(status.equalsIgnoreCase("Y"))
		{
			flag = true;
		}
		
		return flag;	
		
	}
	
	public boolean checkRegNumber(String regNumber)
	{
		return dao.checkRegNumber(regNumber);
	}
	
	public String checkNullAndVoid(String regId)
	{
		return dao.checkNullAndVoid(regId);
	}
	
	public String getUsrId(String regNo)
	{
		return dao.getUsrId(regNo);
	}
	
	public String getOfcId(String regNo)
	{
		return dao.getOfcId(regNo);
	}
	
	
	public boolean generateFinalCertificatePrint(DocumentFlagEvaluationForm docForm, String regId,String regNo, String userId,String offcId,String lang,HttpServletResponse response)throws Exception{
		boolean flag = false;
		
		long start = 0, end = 0;
		 start = System.currentTimeMillis();
		 String langauge=dao.getLangauge(regId);
		 if("English".equalsIgnoreCase(langauge))
		 {
			 lang="en";
		 }
		 else
		 {
			 lang="hi";
		 }
		if(generateRegCertificatePrint(docForm, regId,userId,offcId,lang))//registration certificate generation
		{
			if(generateEstampPdf(regId))  // generate estamp pdf and uploading it in DMS
			{
				
						if(true)//mergeSealWithDeed(regForm))// merge seal doc with deed doc
						{
							if(true)//putPropertyImages(regForm.getRegDTO().getRegInitNumber(),regForm))   // property images on seal doc
							{
							if(mergeRegCertWithEstamp(regId)) // merge regCert and Estamp
							{
								if(mergeDocsToGenerateFinalCertPrint(docForm, regId,regNo, lang,offcId))//sealDeed doc and regEstamp doc
								{
									
									
									//String filePath="";
									
									PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
									String path=pr.getValue("igrs_upload_path")+File.separator+"REGSEAL"+File.separator+"IGRS"; 
									String filePath=path+File.separator+regId+File.separator+RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG;
									response.setHeader("Content-Disposition", "attachment; filename="
			          						+ URLEncoder.encode(filePath,"UTF-8"));
									File fileToDownload = new File(filePath);
			          			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
			          			   OutputStream out = response.getOutputStream();
			          			   byte[] buf = new byte[(int) fileToDownload.length()];

			          			   int readNum;
			          			   while ((readNum=fileInputStream.read(buf))!=-1)
			          			   {
			          			      out.write(buf,0,readNum);
			          			   }
			          			   fileInputStream.close();
			          			   out.flush();
			          			   out.close();
								
								}
							}
						}
					}
				//}
			}
			 
		}
		 end = System.currentTimeMillis();
		 docForm.setTimeTaken((end-start)/1000);
		
		return flag;
	}
	
	public boolean generateRegCertificatePrint(DocumentFlagEvaluationForm docForm, String regId, String userId,String offcId,String lang)
	{
		boolean flag = false;
		String language = "";
		 int result = -1;
		try
		{
			
			String sysDate = dao.getSystemDate();
			String srName = dao.getSrDetails(userId);
			String offcName = dao.getofficeName(offcId,lang);
			String stampDuty = "0";
			String regFee = "0";
			ArrayList dutyDetls = dao.getRegStampDuty(regId);
			for(int i = 0 ; i < dutyDetls.size() ; i++)
			{
				ArrayList subList = (ArrayList)dutyDetls.get(i);
				if(subList.get(2).toString().contains("-")){
					stampDuty = "0";
				}
				else{
				stampDuty = (String)subList.get(2);
				}
				
				if(subList.get(1).toString().contains("-")){
					regFee = "0";
				}
				
				else{
					regFee = (String)subList.get(1);
				}
			}
		
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(regId);
		    metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT);

		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD=new SealsBD();
			boolean val=sealBD.validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
			
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
			 
			  RegistrationCertificate  regCertObject = new RegistrationCertificate();
		      regCertObject.setRegistrationNo(regId);
		      SealsDAO sealDAO = new SealsDAO();	
		      String regDate=sealDAO.getSealDAte(regId, "12");
		      regCertObject.setRegistrationDate(regDate);
		      regCertObject.setDateOfDelivery("NA");
		      String marketValue=dao.getMarketValue(regId); 
		      regCertObject.setMarketvalue(marketValue);
		      regCertObject.setRegistrationFees(regFee);
		   // Set Value for consideration amount - Registration Certficate Print Preview - 19 SEp
		      
		      regCertObject.setConsiderationAmount(dao.getConsiderAmount(regId));
		      regCertObject.setTotalStampDuty(stampDuty);
		      regCertObject.setSubRegistrarName(srName);
		      regCertObject.setSubRegistrarOfficeName(offcName);
		      regCertObject.setPartydetails(dao.getPartyDetailsRegCertificate(regId,lang));
		      if(lang.equalsIgnoreCase("en"))
		    	  language = "English";
		    	  else
		    		  language = "Hindi";
		      String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+regId;
				File output2;
				output2 = new File(downloadPath.toString());
				
				if (output2.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output2.mkdirs();
				}
		    String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regId;
		    File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			result = burnObj.generateRegistrationCertificate(connDetails,metaDataInfo,outputPath, docForm.getHeaderImg(), regCertObject,RegCompCheckerConstant.NAME_OF_REG_CERT ,RegCompCheckerConstant.DMS_FOLDER,language); 

				
		}
		catch(Exception e)
		{
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getStackTrace());
			logger.debug(e.getMessage(),e);
		}
		if(result == 0)
			flag = true;
		return flag;
	}
	
	public boolean mergeDocsToGenerateFinalCertPrint(DocumentFlagEvaluationForm docForm, String regId,String regNo, String lang,String userId)
	{
		boolean flag = false;
		try
		{
			String deedDocPath = dao.getDeedDocPath(regId);
			ArrayList list = dao.getEstampCodeDetails(regId);
			String stampCode = "";
			for(int i = 0 ; i < list.size() ;i++)
			{
				ArrayList subList = (ArrayList)list.get(i);
				if("".equals(stampCode))
					stampCode = (String)subList.get(0);
				else
					stampCode = stampCode+" | "+(String)subList.get(0);
			}
			stampCode = regNo+" | "+stampCode;
			
			logger.debug("final String ------->"+stampCode);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(regId);
		
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD=new SealsBD();
			boolean val=sealBD.validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
			 int result = -1;
			 String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+regId;
				File output2;
				output2 = new File(downloadPath.toString());
				
				if (output2.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output2.mkdirs();
				}
			 String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regId;
			 File output;
				output = new File(outputPath.toString());
				
				if (output.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output.mkdirs();
				}
			docForm.setFinalDocPath(outputPath+File.separator+RegCompCheckerConstant.NAME_OF_FINAL_DOC);
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			result = burnObj.mergeStampWithFinalDeed(connDetails, metaDataInfo, downloadPath,RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_ESTAMP, RegCompCheckerConstant.NAME_OF_REG_ESTAMP, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC,RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL, RegCompCheckerConstant.DMS_FOLDER, stampCode);
			logger.debug("result--->"+result);
			if(result!=0)
			{
				throw new Exception(); 
			}
			
		
			result=applyRegistartionSeal(regId,regNo,lang,docForm.getHeaderImg(),stampCode);
			if(result!=0)
			{
				throw new Exception(); 
			}
			
			logger.debug("result--->"+result);
			if(result == 0)
				flag = true;
		}
		catch(Exception e)
		{
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getMessage(),e);
		}
		
		return flag;
	}
	
	public int	applyRegistartionSeal(String regNo,String CompNumber,String langauge,String headerimg,String stampCode)
	{
		int result=-1;
		try{	
	SealsDAO sealDAO = new SealsDAO();	
	//String sysDate = sealDAO.getSystemDate();
	//ArrayList list = new RegCompCheckerDAO().getEstampCodeDetails(sForm.getSealDTO().getRegNumber());
	PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
	DocumentOperations docOperations = new DocumentOperations();
	ODServerDetails connDetails = new ODServerDetails();
	Dataclass metaDataInfo = new Dataclass();
	connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	connDetails.setCabinetName(pr.getValue("CabinetName"));
	connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	metaDataInfo.setUniqueNo(regNo);
	metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL);
	metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));

	String hindiFont = pr.getValue("dms_hindi_font_path");
	String englishFont = pr.getValue("dms_english_font_path");
	SealsBD sealBD=new SealsBD();
	boolean val=sealBD.validateFont(hindiFont,englishFont);
	if(!val){
		throw new Exception();
	}
	BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  


	SealsForm sForm= new SealsForm();
	SealsBD bd=new SealsBD();
	bd.getRegFeeDetails(regNo, sForm);
	sealDAO.signatureDetails(sForm, regNo, langauge);
	RegistrationSeal registrationSeal = new RegistrationSeal();
	//by vinay
	registrationSeal.setAuthName(sForm.getSealDTO().getUserName());
	registrationSeal.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
	registrationSeal.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
	registrationSeal.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
			// end
	String serialNumber=sealDAO.getSerailNumber(regNo, "21");
	if(!"".equalsIgnoreCase(serialNumber))
	{
	registrationSeal.setIsCorrectionSeal(Boolean.TRUE);
	registrationSeal.setCorrectionSealAuthName(sForm.getSealDTO().getUserName());
	registrationSeal.setCorrectionSealAuthDesignation(sForm.getSealDTO().getUserDesignation());
	registrationSeal.setCorrectionSealAuthPlace(sForm.getSealDTO().getUserOfficeName());
	registrationSeal.setCorrectionSealAuthSignatureLocation(sForm.getSealDTO().getUserSignature());

	String corrdDate=sealDAO.getSealDAte(regNo, "21");
	registrationSeal.setCorrectionSeal("यह दस्तावेज पूर्व में पंजीकृत दस्तावेज क्रमांक "+serialNumber+" दिनांक "+corrdDate+" को संशोधित / निरसित करता है।");
	}

	String regDate=dao.getRegistartionDateInit(regNo);
	registrationSeal.setType(Boolean.TRUE);  // true- registration seal , false- refusal seal
	sForm.getSealDTO().setCopyFee("0");
	sForm.getSealDTO().setOthers("0");
	registrationSeal.setRegistrationSealContentPartOne("इस दस्तावेज का इलेक्ट्रानिक पंजीयन दिनांक "+regDate+" को क्रमांक "+CompNumber+" दे कर किया गया है। जिसमें");
	registrationSeal.setRegistrationSealContentPartTwo("पृष्ठ समाविष्ट हैं ");
	registrationSeal.setFeesSealContent("स्टाम्प शुल्क        "+sForm.getSealDTO().getTotalDuty()+" \n \n \n पंजीयन शुल्क       "+sForm.getSealDTO().getRegFee()+" \n प्रतिलिपि " +
			"शुल्क      "+sForm.getSealDTO().getCopyFee()+" \n अधिक             "+sForm.getSealDTO().getOthers()+" \n योग               "+sForm.getSealDTO().getRegFee());
	//registrationSeal.setRefusalSealContent("This is a refusal Seal");
	//RegCompCheckerDAO regDao = new RegCompCheckerDAO();
	//String deedDocPath = regDao.getDeedDocPath(sForm.getSealDTO().getRegNumber());
	//logger.debug("deed doc path--->"+deedDocPath);
	String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+regNo;
	String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+regNo;
	String path=pr.getValue("igrs_upload_path")+File.separator+"REGSEAL"+File.separator+"IGRS"; 
	String documentPath=path+File.separator+regNo;


	File output2;
	//String sealConetent="इस दस्तावेज का इलेक्ट्रानिक पंजीयन दिनाक "+sysDate+"को क्रमांक "+sForm.getSealDTO().getRegCompNumber()+" को किया गया है। जिसमें "+list.size()+" पृष्ठ समाविष्ट है "+"\nस्टाम्प शुल्क      "+sForm.getSealDTO().getTotalDuty()+" \n पंजीयन शुल्क       "+sForm.getSealDTO().getRegFee()+" \n प्रतिलिपि " +
	//"शुल्क     "+sForm.getSealDTO().getCopyFee()+" \n अधिक     "+sForm.getSealDTO().getOthers()+" \n योग     "+sForm.getSealDTO().getTotal();

	output2 = new File(downloadPath.toString());

	if (output2.exists() == false) {
		logger.debug("Parent not found creating directory....");
		output2.mkdirs();
	}
	File output;
	output = new File(documentPath.toString());

	if (output.exists() == false) {
		logger.debug("Parent not found creating directory....");
		output.mkdirs();
	}

	if (null==metaDataInfo.getUniqueNo()) {
		throw new Exception();
	}
	if (metaDataInfo.getUniqueNo().equals("")) {
		throw new Exception();
	}
	if("en".equalsIgnoreCase(langauge))
	{	
	result= burnObj.putRegistrationFinalSeal(connDetails,metaDataInfo,downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL,documentPath,headerimg,registrationSeal, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL_REG, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG,SealsConstant.DMS_FOLDER,stampCode,"English");
	}
	else
	{
	result= burnObj.putRegistrationFinalSeal(connDetails,metaDataInfo,downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL,documentPath,headerimg,registrationSeal, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL_REG, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG,SealsConstant.DMS_FOLDER,stampCode,"Hindi");
		
	}
	//result =burnObj.putExtraSeals(connDetails, metaDataInfo, downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(), false, 1, 1, sealConetent, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC, SealsConstant.NAME_OF_SEALS_DOC, SealsConstant.DMS_FOLDER);
	logger.debug("result of reg seal------->"+result);
	}
	catch(Exception e)
	{
		logger.debug(e.getMessage(),e);
	}
	return result;
	}
	
	public boolean mergeRegCertWithEstamp(String regId)
	{
		boolean flag = false;
		try
		{
			//RegCompCheckerDTO rdto = regForm.getRegDTO();
			//RegCompCheckerDAO regCompDAO =  new RegCompCheckerDAO();
			String deedDocPath = dao.getDeedDocPath(regId);
		
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(regId);
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD=new SealsBD();
			boolean val=sealBD.validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
			 int result = -1;
			 String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+regId;
				File output2;
				output2 = new File(downloadPath.toString());
				
				if (output2.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output2.mkdirs();
				}
			 String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regId;
			 File output;
				output = new File(outputPath.toString());
				
				if (output.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output.mkdirs();
				}
				if (null==metaDataInfo.getUniqueNo()) {
					throw new Exception();
				}
				if (metaDataInfo.getUniqueNo().equals("")) {
					throw new Exception();
				}
				
			result = burnObj.mergeCertificateWithEStamp(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT, RegCompCheckerConstant.NAME_OF_REG_CERT, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_ESTAMP, RegCompCheckerConstant.NAME_OF_ESTAMP,outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_ESTAMP, RegCompCheckerConstant.NAME_OF_REG_ESTAMP, RegCompCheckerConstant.DMS_FOLDER);
			logger.debug("merging estamp and reg cert-->"+result);
			if(result == 0)
				flag = true;
		}
		catch(Exception e)
		{
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getStackTrace());
		}
		
		return flag;
	}
	
	public boolean generateEstampPdf(String regInitId) throws Exception
	{
		boolean flag = false;
		String estampPath1  = "";
		String estampPath2 = "";
		String estampPath3 = "";
		String estamp1  = "";
		String estamp2 = "";
		String estamp3 = "";
		int result = -1;
		ArrayList estampList = dao.getEstampForRegistration(regInitId);
		if(estampList.size() == 2)
		{
				ArrayList subList = (ArrayList)estampList.get(0);
				estampPath1 = (String)subList.get(0);
				estamp1 = (String)subList.get(1);
				ArrayList subList2 = (ArrayList)estampList.get(1);
				estampPath2 = (String)subList2.get(0);
				estamp2 = (String)subList2.get(1);
			
		}
		else if(estampList.size() == 3)
		{
			ArrayList subList = (ArrayList)estampList.get(0);
			estampPath1 = (String)subList.get(0);
			estamp1 = (String)subList.get(1);
			
			ArrayList subList2 = (ArrayList)estampList.get(1);
			estampPath2 = (String)subList2.get(0);
			estamp2 = (String)subList2.get(1);
			
			ArrayList subList3 = (ArrayList)estampList.get(1);
			estampPath3 = (String)subList3.get(0);
			estamp3 = (String)subList3.get(1);
		}
		else
		{
			ArrayList subList = (ArrayList)estampList.get(0);
			estampPath1 = (String)subList.get(0);
			estamp1 = (String)subList.get(1);
		}
		try
		{
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(regInitId);
		    metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_ESTAMP);
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD=new SealsBD();
			boolean val=sealBD.validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
			
			 String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId;
			    File outPath;
			    outPath = new File(outputPath.toString());
				
				if (outPath.exists() == false) {
					logger.debug("Parent not found creating directory....");
					outPath.mkdirs();
				}
				if (null==metaDataInfo.getUniqueNo()) {
					throw new Exception();
				}
				if (metaDataInfo.getUniqueNo().equals("")) {
					throw new Exception();
				}
			if(estampList.size() == 1)
			{
				File file = new File(estampPath1);
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId, regForm.getHeaderImg(),estampPath1 , "Estamp.PDF");
				String docId = docOperations.uploadDocument(connDetails, file, RegCompCheckerConstant.DMS_FOLDER, true,metaDataInfo );
				if(docId == "0")
					flag = true;
			}
			else if(estampList.size() == 2){
				String outputPath1 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp1;
				String outputPath2 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp2;	
				File output = new File(outputPath1.toString());
			    if(output.exists() == false)
			    		{
			    			logger.debug("Parent not found creating directory....");
			    			output.mkdirs();
			    		}
			    File output2 = new File(outputPath2.toString());
			    if(output2.exists() == false)
			    		{
			    			logger.debug("Parent not found creating directory....");
			    			output2.mkdirs();
			    		}
					
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp1, regForm.getHeaderImg(),estampPath1 , "Estamp1.PDF");
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp2, regForm.getHeaderImg(),estampPath2 , "Estamp2.PDF");
			   
				result = burnObj.mergeDocs(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId,estampPath1 , estampPath2, "EStamp.PDF");
				
				File file = new File(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId+File.separator+"EStamp.PDF");
				String docId = docOperations.uploadDocument(connDetails, file, RegCompCheckerConstant.DMS_FOLDER, true,metaDataInfo );
				if(docId == "0")
					flag = true;
			}
			else if(estampList.size() == 3)
			{
				String outputPath1 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp1;
				String outputPath2 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp2;
				String outputPath3 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp3;
				File output = new File(outputPath1.toString());
			    if(output.exists() == false)
			    		{
			    			logger.debug("Parent not found creating directory....");
			    			output.mkdirs();
			    		}
			    File output2 = new File(outputPath2.toString());
			    if(output2.exists() == false)
			    		{
			    			logger.debug("Parent not found creating directory....");
			    			output2.mkdirs();
			    		}
			    File output3 = new File(outputPath3.toString());
			    if(output3.exists() == false)
			    		{
			    			logger.debug("Parent not found creating directory....");
			    			output3.mkdirs();
			    		}
					
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp1, regForm.getHeaderImg(),estampPath1 , "Estamp1.PDF");
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp2, regForm.getHeaderImg(),estampPath2 , "Estamp2.PDF");
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp3, regForm.getHeaderImg(),estampPath3 , "Estamp3.PDF");
				result = burnObj.mergeDocs(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId, estampPath1,estampPath2, "EstampTemp.PDF");
				result = burnObj.mergeDocs(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId,RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId+File.separator+"EstampTemp.PDF" , estampPath3, "EStamp.PDF");
				
				File file = new File(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId+File.separator+"EStamp.PDF");
				String docId = docOperations.uploadDocument(connDetails, file, RegCompCheckerConstant.DMS_FOLDER, true,metaDataInfo );
				if(docId == "0")
					flag = true;
			}
			
			
		}
		catch(Exception e)
		{
			logger.debug(e.getStackTrace());
		}
		
		return flag;
	}

	public boolean declareNullAndVoid(DocumentFlagEvaluationForm docForm, String userId) {
		return dao.declareNullAndVoid(docForm, userId);
		
	}
	
	public boolean revertNullAndVoid(DocumentFlagEvaluationForm docForm, String userId,boolean revertFlag) {
		return dao.revertNullAndVoid(docForm, userId,revertFlag);
		
	}

	public List getLastRemarksAndUploads(String regNumber) {
		return dao.getLastRemarksAndUploads(regNumber);
	}
	
	public String fetchRegTxnId(String regNo) {
		String retVal = null;
		try {
			DocumentFlagEvaluationDAO refDAO = new DocumentFlagEvaluationDAO();
			retVal = refDAO.getReginitId(regNo);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return retVal;
	}
	
	public boolean validateFileTypeEfile(String fileExt){
		String efileExt="pdf";
		if (efileExt.equalsIgnoreCase(fileExt)){
			return true;
		}
		return false;
	}
	

}
