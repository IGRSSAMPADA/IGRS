package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement(name="sourceofIrrigation")
public class SourceOfIrrigateDTO implements Serializable{
	private String source;
	@XmlElement
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
