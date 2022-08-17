package com.wipro.igrs.propertyvaluationefiling.dto;

import java.io.Serializable;

public class UsePlotDTO implements Serializable{
	
private int usePlotId;
private String usePlot;
private String usePlotLabel;
private String check;
public String getCheck() {
	return check;
}
public void setCheck(String check) {
	this.check = check;
}
public String getUsePlotLabel() {
	return usePlotLabel;
}
public void setUsePlotLabel(String usePlotLabel) {
	this.usePlotLabel = usePlotLabel;
}
public int getUsePlotId() {
	return usePlotId;
}
public void setUsePlotId(int usePlotId) {
	this.usePlotId = usePlotId;
}
public String getUsePlot() {
	return usePlot;
}
public void setUsePlot(String usePlot) {
	this.usePlot = usePlot;
}


}
