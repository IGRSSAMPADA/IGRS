package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="sourceofIrrigations")
public class SourceOfIrrigationList implements Serializable{
	private ArrayList<SourceOfIrrigateDTO> sourceofIrrigation;

	public ArrayList<SourceOfIrrigateDTO> getSourceofIrrigation() {
		return sourceofIrrigation;
	}
	@XmlElement(name="sourceofIrrigation")
	public void setSourceofIrrigation(ArrayList<SourceOfIrrigateDTO> sourceofIrrigation) {
		this.sourceofIrrigation = sourceofIrrigation;
	}
	
}
