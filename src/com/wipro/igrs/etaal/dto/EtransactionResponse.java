package com.wipro.igrs.etaal.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "eTaal")
public class EtransactionResponse {

	List<ResponseDTO>	response	= new ArrayList<ResponseDTO>();

	@XmlElement(name = "Response")
	public List<ResponseDTO> getResponse() {
		return response;
	}

	public void setResponse(List<ResponseDTO> response) {
		this.response = response;
	}

}
