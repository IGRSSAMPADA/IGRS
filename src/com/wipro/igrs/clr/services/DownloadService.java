package com.wipro.igrs.clr.services;

import java.io.File;

import javax.servlet.http.HttpServletResponse;


import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.payment.util.AESEncrypt;
import com.wipro.igrs.payments.util.PropertiesFileReader;

public class DownloadService {
	public  void downloadFiles(String finalPath, HttpServletResponse response) throws Exception{

		byte[] content = DMSUtility.getDocumentBytes(finalPath);
		DMSUtility.downloadDocument(response, new File(finalPath).getName(), content);
		//DownloadCopyFromDMS.downloadCopyFromDMS("310122000001", response);
	}
	
	public static  void downloadFilesRCMS(String finalPath, HttpServletResponse response,String type) throws Exception{

		byte[] content = DMSUtility.getDocumentBytes(finalPath);
		DMSUtility.downloadDocument(response, finalPath, content);
		//DownloadCopyFromDMS.downloadCopyFromDMS("310122000001", response);
	}
	public static void downloadFilesForRCMS(String path, HttpServletResponse response, String type) throws Exception{
		
		System.out.println("file path -- "+path);
		
		if("F".equals(type)){
			downloadDoc(path, response,type);
		}else{
		byte[] content = DMSUtility.getDocumentBytes(path);
		String fileName = (new File(path)).getName();
		DMSUtility.downloadDocument(response, fileName, content);
		}
		
	}
public static void downloadDoc(String regTxnId, HttpServletResponse response, String type) throws Exception{
		
		PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
		
		DownloadCopyFromDMS.downloadCopyFromDMS(regTxnId, response);
	}
	
}
