package com.wipro.igrs.pot.dto;

import java.util.ArrayList;

public class PotDistrictDTO
{
	private String districtIdDTO;
	private String districtNameDTO;
	private String docTypeId;
	private String bookNo;
	private String physicalStampId;
	private String physicalStampName;
	ArrayList docList; 
	ArrayList bookList; 
	ArrayList physicalList; 
	ArrayList list; 
	public String getDistrictIdDTO()
	{
		return districtIdDTO;
	}
	public void setDistrictIdDTO(String districtIdDTO)
	{
		this.districtIdDTO = districtIdDTO;
	}
	public String getDistrictNameDTO()
	{
		return districtNameDTO;
	}
	public void setDistrictNameDTO(String districtNameDTO)
	{
		this.districtNameDTO = districtNameDTO;
	}
	/**
	 * @return the list
	 */
	public ArrayList getList()
	{
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList list)
	{
		this.list = list;
	}
	public String getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getPhysicalStampId() {
		return physicalStampId;
	}
	public void setPhysicalStampId(String physicalStampId) {
		this.physicalStampId = physicalStampId;
	}
	public String getPhysicalStampName() {
		return physicalStampName;
	}
	public void setPhysicalStampName(String physicalStampName) {
		this.physicalStampName = physicalStampName;
	}
	public ArrayList getDocList() {
		return docList;
	}
	public void setDocList(ArrayList docList) {
		this.docList = docList;
	}
	public ArrayList getBookList() {
		return bookList;
	}
	public void setBookList(ArrayList bookList) {
		this.bookList = bookList;
	}
	public ArrayList getPhysicalList() {
		return physicalList;
	}
	public void setPhysicalList(ArrayList physicalList) {
		this.physicalList = physicalList;
	}
	

}
