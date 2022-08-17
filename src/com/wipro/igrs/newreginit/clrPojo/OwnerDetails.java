package com.wipro.igrs.newreginit.clrPojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="ownerDetails")
public class OwnerDetails {
	private ArrayList<Owner> Owner=new ArrayList<Owner>();

	 @XmlElement(name="Owner")
	public ArrayList<Owner> getOwner() {
		return Owner;
	}

	public void setOwner(ArrayList<Owner> owner) {
		Owner = owner;
	}
   
	
	

	

	
	

}
