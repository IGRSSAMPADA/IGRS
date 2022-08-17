package com.wipro.igrs.newreginit.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class FormUserEnterableDTO implements Serializable{
	private String userEnterableId;
	private String userEnterableFieldNameEnglish;
	private String userEnterableFieldNameHindi;
	private String valueEntered;
	public String getUserEnterableId() {
		return userEnterableId;
	}
	public void setUserEnterableId(String userEnterableId) {
		this.userEnterableId = userEnterableId;
	}
	public String getUserEnterableFieldNameEnglish() {
		return userEnterableFieldNameEnglish;
	}
	public void setUserEnterableFieldNameEnglish(String userEnterableFieldNameEnglish) {
		this.userEnterableFieldNameEnglish = userEnterableFieldNameEnglish;
	}
	public String getUserEnterableFieldNameHindi() {
		return userEnterableFieldNameHindi;
	}
	public void setUserEnterableFieldNameHindi(String userEnterableFieldNameHindi) {
		this.userEnterableFieldNameHindi = userEnterableFieldNameHindi;
	}
	public String getValueEntered() {
		return valueEntered;
	}
	public void setValueEntered(String valueEntered) {
		this.valueEntered = valueEntered;
	}
	
}
