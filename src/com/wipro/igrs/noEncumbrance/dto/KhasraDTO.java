package com.wipro.igrs.noEncumbrance.dto;

import java.io.Serializable;

public class KhasraDTO implements Serializable{

private String khasraNumber;
private String rinPustika;
private String khasraArea;
private String lagaan;
public String getKhasraArea() {
	return khasraArea;
}
public void setKhasraArea(String khasraArea) {
	this.khasraArea = khasraArea;
}
public String getLagaan() {
	return lagaan;
}
public void setLagaan(String lagaan) {
	this.lagaan = lagaan;
}
public String getKhasraNumber() {
	return khasraNumber;
}
public void setKhasraNumber(String khasraNumber) {
	this.khasraNumber = khasraNumber;
}
public String getRinPustika() {
	return rinPustika;
}
public void setRinPustika(String rinPustika) {
	this.rinPustika = rinPustika;
}

}
