package com.wipro.igrs.rcms.entity;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class KhasraRecordList {
	
	private ArrayList<KhasraRecord> khasraRecord=new ArrayList<KhasraRecord>();
	
	public KhasraRecordList() {
		super();
	}

	public void setKhasraRecord(ArrayList<KhasraRecord> khasraRecord) {
		this.khasraRecord = khasraRecord;
	}
	
	@XmlElement
	public ArrayList<KhasraRecord> getKhasraRecord() {
		return khasraRecord;
	}

	
}
