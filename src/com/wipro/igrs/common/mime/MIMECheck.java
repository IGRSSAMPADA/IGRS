package com.wipro.igrs.common.mime;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

public class MIMECheck {

	
  private boolean error;
	
public boolean isValidMIME(FormFile uploadedFile) {

	ArrayList errors = new ArrayList();
	
		String fileName = uploadedFile.getFileName();
		String temp = fileName.replaceFirst("\\.", "");
		if (temp.contains(".") || uploadedFile.getFileSize() <= 0) {
			return false;
		}

		String mimeType = uploadedFile.getContentType();
		mimeType = mimeType.toLowerCase();
		String fileextension = getFileExtension(fileName);
		fileextension = fileextension.toLowerCase();

	//	String[] arrFileExt = { "tif", "jpg", "tiff", "gif", "jpeg" };

		if (mimeType != null && mimeType.contains("image")) {
			if (mimeType.contains("jpeg")) {
				if (fileextension.equalsIgnoreCase("jpg")
						|| fileextension.equalsIgnoreCase("jpeg")) {
					return true;
				}
			} else if (mimeType.contains("gif")) {
				if (fileextension.equalsIgnoreCase("gif")) {
					return true;
				}
			} else if (mimeType.contains("tiff")) {
				if (fileextension.equalsIgnoreCase("tiff")
						|| fileextension.equalsIgnoreCase("tif")) {
					return true;
				}
			}

		} else if (mimeType != null && mimeType.contains("application")) {
			if (mimeType.contains("pdf")) {
				if (fileextension.equalsIgnoreCase("pdf")) {
					return true;
				}
			} else if (mimeType.contains("msword")) {
				if (fileextension.equalsIgnoreCase("doc")
						|| fileextension.equalsIgnoreCase("docx")) {
					return true;
				}
			} else if (mimeType.contains("zip")) {
				if (fileextension.equalsIgnoreCase("zip")) {
					return true;
				}
			} else if (mimeType.contains("rtf")) {
				if (fileextension.equalsIgnoreCase("rtf")) {
					return true;
				}
			} else if (mimeType.contains("ms-excel")) {
				if (fileextension.equalsIgnoreCase(".xls")
						|| fileextension.equalsIgnoreCase(".xlsx")) {
					return true;
				}
			}
		}

		return false;

	}
	
	public boolean validateMIMEAndFileType(FormFile uploadedFile){
		
		if (isValidMIME(uploadedFile) && validateFileType(getFileExtension(uploadedFile.getFileName()))){
			return true;
		}
		
		
		return false;
	}
	  // To validate only jpg file format.
public boolean validateMIMEAndJPGFileType(FormFile uploadedFile){
		
		if (isValidMIME(uploadedFile) && validateFileTypeJPG(getFileExtension(uploadedFile.getFileName()))){
			return true;
		}
		
		return false;
	}

public boolean validateFileTypeJPG(String fileExt) {
	String[] arrFileExt = {"jpg","jpeg"};
	try {
		for (int i = 0; i < arrFileExt.length; i++) {
			if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
				return true;
			}
		}
		
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return false;
}

	public boolean validateFileType(String fileExt) {
		String[] arrFileExt = { "tif","pdf", "jpg","tiff","gif","jpeg","doc","docx","xls", "xlsx","rtf", "zip"};
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					return true;
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			if (strExt != null && !" ".equalsIgnoreCase(strExt)) {
				strExt = strExt.toLowerCase();
			}
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
	
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return
	 */
	public boolean isError() {
		return error;
	}
	
	
	//added by ankit for plant and machinery - start
	  // To validate only jpg file format.
	public boolean validateMIMEAndJPGAndPDFFileType(FormFile uploadedFile){
			
			if (isValidMIME(uploadedFile) && validateFileTypeJPGAndPDF(getFileExtension(uploadedFile.getFileName()))){
				return true;
			}
			
			return false;
		}

	//added by ankit for plant and machinery - start
	public boolean validateFileTypeJPGAndPDF(String fileExt) {
		String[] arrFileExt = {"jpg","jpeg","pdf"};
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					return true;
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
  //added by ankit for plant and machinery - end
}
