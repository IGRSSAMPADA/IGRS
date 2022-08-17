package com.wipro.igrs.clr.services;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.log.Logger;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.documentsearch.bd.DocumentSearchBD;
import com.wipro.igrs.payments.util.PropertiesFileReader;

public class DownloadCopyFromDMS {


	//private static Logger logger = (Logger) Logger.getLogger(DownloadCopyFromDMS.class);

	public static void downloadCopyFromDMS(String docNumber, HttpServletResponse response) throws Exception {
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		
		String[] docList =  new String[1];
		docList[0]=docNumber;
		//String[] docList = new String[]{"50821000001", "20821000001", "60721000001", "180621000007", "20621000007", "310521000001", "190521000002", "60521000001", "60521000002", "60521000003"};
		long startTime = System.currentTimeMillis();
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
		metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		metaDataInfo.setType("FI");
		metaDataInfo.setLatestFlag("L");
		String tempPath = "";
		//String filePath = "";
		System.out.println("value start:here");
		System.out.println(pr.getValue("AppServerIp"));
		System.out.println(pr.getValue("CabinetName"));
		System.out.println(pr.getValue("AppServerUserId"));
		System.out.println(pr.getValue("AppServerUserPass"));
		System.out.println(pr.getValue("ImageServerIP"));
		System.out.println(pr.getValue("ImageServerPort"));
		String downloadPath = pr.getValue("igrs_upload_path");
		for (int i = 0; i < docList.length; i++) {
			metaDataInfo.setUniqueNo(docList[i]);
			String guid = UUID.randomUUID().toString();
			String EstampPath = downloadPath + File.separator + guid+File.separator+docList[i];
			File folder = new File(EstampPath.toString());
			if (!folder.exists()) {
				System.out.println(folder.toString());

				folder.mkdirs();

			}
			
			int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath.toString(), "Estamp");
			File[] listOfFiles = folder.listFiles();
			for (int z = 0; z < listOfFiles.length; z++) {
				tempPath = listOfFiles[z].getPath();
			}
			System.out.println("GET DOCUMENT BYTES");
			byte bytes[] = DMSUtility.getDocumentBytes(tempPath);
			System.out.println(bytes);
			System.out.println(tempPath);
			startTime = System.currentTimeMillis();
			System.out.println("Download result ---- " + result);
			System.out.println("Download result ---- " + bytes.toString());
			System.out.println("Download at path ---- " + tempPath);
			String fileName = (new File(tempPath)).getName();
			DMSUtility.downloadDocument(response, fileName, bytes);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Total Time taken --" + (endTime - startTime));
		// filePath = burnRequestIdForPDF("", tempPath, EstampPath);
	}


}
