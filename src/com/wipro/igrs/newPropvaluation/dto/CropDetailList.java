package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cropDetails")
public class CropDetailList implements Serializable{
	private ArrayList<CropDetailDTO> cropDetail;
	@XmlElement(name="cropDetail")
	public ArrayList<CropDetailDTO> getCropDetail() {
		return cropDetail;
	}

	public void setCropDetail(ArrayList<CropDetailDTO> cropDetail) {
		this.cropDetail = cropDetail;
	}
	
}
