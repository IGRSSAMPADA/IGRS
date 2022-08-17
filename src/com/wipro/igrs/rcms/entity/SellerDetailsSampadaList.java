package com.wipro.igrs.rcms.entity;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class SellerDetailsSampadaList {

private ArrayList<SellerDetails> SellerDetails=new ArrayList<SellerDetails>();
	
	public SellerDetailsSampadaList() {
		super();
	}
	
	@XmlElement(name="SellerDetails")
	public ArrayList<SellerDetails> getSellerDetails() {
		return SellerDetails;
	}

	public void setSellerDetails(ArrayList<SellerDetails> sellerDetails) {
		SellerDetails = sellerDetails;
	}
	
}
