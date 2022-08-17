package com.wipro.igrs.empmgmt.dto;
import java.io.Serializable;
import java.util.ArrayList;
public class PropertyAssetDTO implements Serializable{

	private String assetTypeId;
	private String assetTypeName;
	private ArrayList assetList;
	
	
	private String assetDescId;
	private String assetDescName;
	private ArrayList assetDescList;
	
	public String getAssetDescId() {
		return assetDescId;
	}
	public void setAssetDescId(String assetDescId) {
		this.assetDescId = assetDescId;
	}
	
	public String getAssetDescName() {
		return assetDescName;
	}
	public void setAssetDescName(String assetDescName) {
		this.assetDescName = assetDescName;
	}
	
	
	
	public String getAssetTypeName() {
		return assetTypeName;
	}
	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}
	public String getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
	public ArrayList getAssetList() {
		return assetList;
	}
	public void setAssetList(ArrayList assetList) {
		this.assetList = assetList;
	}
	public ArrayList getAssetDescList() {
		return assetDescList;
	}
	public void setAssetDescList(ArrayList assetDescList) {
		this.assetDescList = assetDescList;
	}
}
