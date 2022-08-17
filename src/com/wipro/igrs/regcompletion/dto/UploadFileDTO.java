/*
 * UploadFileDTO.java
 */

package com.wipro.igrs.regcompletion.dto;


import java.io.File;
import java.io.Serializable;


public class UploadFileDTO implements Serializable {
	private String	fileName;

	private int	   fileId;

	private int	   fileSize;

	private File file;
	
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
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
