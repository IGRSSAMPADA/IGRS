package com.wipro.igrs.newPropvaluation.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="khasraRemarks")
public class KhasraRemarkList {
	private ArrayList<KhasraRemarkDTO> khasraRemarks;

	public ArrayList<KhasraRemarkDTO> getKhasraRemarks() {
		return khasraRemarks;
	}

	public void setKhasraRemarks(ArrayList<KhasraRemarkDTO> khasraRemarks) {
		this.khasraRemarks = khasraRemarks;
	}
	
}
