/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.dto;


import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;


public class UploadFileDTO implements Serializable {
	private String	fileName;
	
	private int	   fileId;
	private String filePath;
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private int	   fileSize;
	private byte[] fileData;
	private String docId;
	ArrayList filelist=new ArrayList();
	ArrayList filedatalist=new ArrayList();
	private FormFile theFile = null;

	
	
	/**
	 * @return the theFile
	 */
	public FormFile getTheFile() {
		return theFile;
	}

	/**
	 * @param theFile the theFile to set
	 */
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}

	/**
	 * @return the filelist
	 */
	public ArrayList getFilelist() {
		return filelist;
	}

	/**
	 * @param filelist the filelist to set
	 */
	public void setFilelist(ArrayList filelist) {
		this.filelist = filelist;
	}

	/**
	 * @return the filedatalist
	 */
	public ArrayList getFiledatalist() {
		return filedatalist;
	}

	/**
	 * @param filedatalist the filedatalist to set
	 */
	public void setFiledatalist(ArrayList filedatalist) {
		this.filedatalist = filedatalist;
	}

	/**
	 * @return the fileData
	 */
	public byte[] getFileData() {
		return fileData;
	}

	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}	

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileId
	 */
	public int getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileSize
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the docId
	 */
	public String getDocId() {
		return docId;
	}

	/**
	 * @param docId the docId to set
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}

}
