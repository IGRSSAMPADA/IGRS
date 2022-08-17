/**
 * WitnessDTO.java
 */
package com.wipro.igrs.departmentalenquiry.dto;

import java.io.Serializable;

/**
 * @author admin
 *
 */
public class WitnessDTO implements Serializable {
	private int iSerialNo;
	
	private String strEnqOfficerName;
	
	private String strSupportingDocName;
	
	private String strDocumentId;
	
	private int fileSize;
	
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
     * @return the iSerialNo
     */
    public int getISerialNo() {
    	return iSerialNo;
    }
	/**
     * @param serialNo the iSerialNo to set
     */
    public void setISerialNo(int serialNo) {
    	iSerialNo = serialNo;
    }
	/**
     * @return the strEnqOfficerName
     */
    public String getStrEnqOfficerName() {
    	return strEnqOfficerName;
    }
	/**
     * @param strEnqOfficerName the strEnqOfficerName to set
     */
    public void setStrEnqOfficerName(String strEnqOfficerName) {
    	this.strEnqOfficerName = strEnqOfficerName;
    }
	/**
     * @return the strSupportingDocName
     */
    public String getStrSupportingDocName() {
    	return strSupportingDocName;
    }
	/**
     * @param strSupportingDocName the strSupportingDocName to set
     */
    public void setStrSupportingDocName(String strSupportingDocName) {
    	this.strSupportingDocName = strSupportingDocName;
    }
	/**
     * @return the strDocumentId
     */
    public String getStrDocumentId() {
    	return strDocumentId;
    }
	/**
     * @param strDocumentId the strDocumentId to set
     */
    public void setStrDocumentId(String strDocumentId) {
    	this.strDocumentId = strDocumentId;
    }
	
}
