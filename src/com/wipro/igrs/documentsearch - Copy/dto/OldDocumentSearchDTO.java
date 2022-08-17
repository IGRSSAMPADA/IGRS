/**
 * 
 */
package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */
public class OldDocumentSearchDTO implements Serializable {

	private static final long serialVersionUID = -5406192988568897153L;
	private String districtName;
	private String sroName;
	private String srName;
	private String bookNumber;
	private String volumeNumber;
	private String serialNumber;
	private List<DistrictDetailsDTO> districtList;
	private List<SRONameDetailsDTO> sroNameList;
	private List<String> result;
	private String errorName;
	private String serviceType;

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getSroName() {
		return sroName;
	}

	public void setSroName(String sroName) {
		this.sroName = sroName;
	}

	public String getSrName() {
		return srName;
	}

	public void setSrName(String srName) {
		this.srName = srName;
	}

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getVolumeNumber() {
		return volumeNumber;
	}

	public void setVolumeNumber(String volumeNumber) {
		this.volumeNumber = volumeNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public List<DistrictDetailsDTO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<DistrictDetailsDTO> districtList) {
		this.districtList = districtList;
	}

	public List<SRONameDetailsDTO> getSroNameList() {
		return sroNameList;
	}

	public void setSroNameList(List<SRONameDetailsDTO> sroNameList) {
		this.sroNameList = sroNameList;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<String> result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public List<String> getResult() {
		return result;
	}

	/**
	 * @param errorName
	 *            the errorName to set
	 */
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	/**
	 * @return the errorName
	 */
	public String getErrorName() {
		return errorName;
	}

	/**
	 * @param serviceType
	 *            the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}

	@Override
	public String toString() {
		return "OldDocumentSearchDTO [districtName=" + districtName + ", sroName=" + sroName + ", srName=" + srName
				+ ", bookNumber=" + bookNumber + ", volumeNumber=" + volumeNumber + ", serialNumber=" + serialNumber
				+ "]";
	}

}
