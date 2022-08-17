package com.wipro.igrs.newreginit.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="ownerDetails")
public class OwnerDetails {
	private ArrayList<Owner> ownerDetail=new ArrayList<Owner>();

	 @XmlElement(name="ownerDetail")
	public ArrayList<Owner> getOwner() {
		return ownerDetail;
	}

	public void setOwner(ArrayList<Owner> owner) {
		ownerDetail = ownerDetail;
	}
   
	
	

	

	
	

}
