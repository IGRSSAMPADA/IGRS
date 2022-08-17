package com.wipro.igrs.rcms.entity;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class BuyerDetailsSampadaList {

	private ArrayList<BuyerDetails> BuyerDetails=new ArrayList<BuyerDetails>();
	
	public BuyerDetailsSampadaList() {
		super();
	}
	
    @XmlElement(name="BuyerDetails")
	public ArrayList<BuyerDetails> getBuyerDetails() {
		return BuyerDetails;
	}

	public void setBuyerDetails(ArrayList<BuyerDetails> buyerDetails) {
		BuyerDetails = buyerDetails;
	}
	
}
