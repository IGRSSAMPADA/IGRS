/**
 * 
 */
package com.wipro.igrs.aadhar.domain.authentication.subservice;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author ARNAV.NEGI
 *
 */
@XmlRootElement
public class Pid implements Serializable {

	private static final long serialVersionUID = -2390463111928825816L;
	
	private Demo demo;
	private Bios bios;
	private Pv pv;
	private XMLGregorianCalendar ts;
	private String ver;
	/**
	 * @return the demo
	 */
	public Demo getDemo() {
		return demo;
	}
	/**
	 * @param demo the demo to set
	 */
	public void setDemo(Demo demo) {
		this.demo = demo;
	}
	/**
	 * @return the bios
	 */
	public Bios getBios() {
		return bios;
	}
	/**
	 * @param bios the bios to set
	 */
	public void setBios(Bios bios) {
		this.bios = bios;
	}
	/**
	 * @return the pv
	 */
	public Pv getPv() {
		return pv;
	}
	/**
	 * @param pv the pv to set
	 */
	public void setPv(Pv pv) {
		this.pv = pv;
	}
	/**
	 * @return the ts
	 */
	public XMLGregorianCalendar getTs() {
		return ts;
	}
	/**
	 * @param ts the ts to set
	 */
	public void setTs(XMLGregorianCalendar ts) {
		this.ts = ts;
	}
	/**
	 * @return the ver
	 */
	public String getVer() {
		return ver;
	}
	/**
	 * @param ver the ver to set
	 */
	public void setVer(String ver) {
		this.ver = ver;
	}
}
