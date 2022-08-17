package com.wipro.igrs.newPropvaluation.dto;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="list")
public class VillageKhasraListDto {
	
	private ArrayList<VillageKhasraDTO> VillageKhasra=new ArrayList<VillageKhasraDTO>();

	public void setVillageKhasra(ArrayList<VillageKhasraDTO> villageKhasra) {
		VillageKhasra = villageKhasra;
	}
	 @XmlElement(name="Khasra")
	public ArrayList<VillageKhasraDTO> getVillageKhasra() {
		return VillageKhasra;
	}


}
