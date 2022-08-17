package com.wipro.igrs.device.applet;

import java.io.Serializable;
import java.util.Vector;

public class ListQueue implements Serializable,Cloneable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8913681506023590569L;
	private Vector<ETokenDTO> makerN = new Vector<ETokenDTO>();
	private Vector<ETokenDTO> makseS = new Vector<ETokenDTO>();
	private Vector<ETokenDTO> checkerS = new Vector<ETokenDTO>();
	private Vector<ETokenDTO> checkerN = new Vector<ETokenDTO>();
	private Vector<ETokenDTO> checkerCounter = new Vector<ETokenDTO>();
	private Vector<ETokenDTO> checkerOldFinal = new Vector<ETokenDTO>();
	private Vector<ETokenDTO> makerCounter = new Vector<ETokenDTO>();
	private Vector<ETokenDTO> makerFinal = new Vector<ETokenDTO>();
	private Vector<ETokenDTO> makerOldFinal = new Vector<ETokenDTO>();
	private Vector<ETokenDTO> counterChecker = new Vector<ETokenDTO>();
	private Vector <ETokenDTO> counterMaker  = new Vector<ETokenDTO>();
	private Vector <ETokenDTO> makerCheckerN = new Vector<ETokenDTO>();
	private Vector <ETokenDTO> makerCheckerS = new Vector<ETokenDTO>();
	private Vector <ETokenDTO> makerCheckerFinal = new Vector<ETokenDTO>();
	
	public Vector<ETokenDTO> getMakerCheckerFinal() {
		return makerCheckerFinal;
	}
	public void setMakerCheckerFinal(Vector<ETokenDTO> makerCheckerFinal) {
		this.makerCheckerFinal = makerCheckerFinal;
	}
	public Vector<ETokenDTO> getMakerCheckerN() {
		return makerCheckerN;
	}
	public void setMakerCheckerN(Vector<ETokenDTO> makerCheckerN) {
		this.makerCheckerN = makerCheckerN;
	}
	public Vector<ETokenDTO> getMakerCheckerS() {
		return makerCheckerS;
	}
	public void setMakerCheckerS(Vector<ETokenDTO> makerCheckerS) {
		this.makerCheckerS = makerCheckerS;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	private boolean flag=false;;
	private String language;
	private String checkerWaitTime;
	private String makerWaitTime;
	
	@Override
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	public Vector<ETokenDTO> getCounterMaker() {
		return counterMaker;
	}
	public void setCounterMaker(Vector<ETokenDTO> counterMaker) {
		this.counterMaker = counterMaker;
	}
	public Vector<ETokenDTO> getMakerFinal() {
		return makerFinal;
	}
	public void setMakerFinal(Vector<ETokenDTO> makerFinal) {
		this.makerFinal = makerFinal;
	}
	public Vector<ETokenDTO> getCheckerFinal() {
		return checkerFinal;
	}
	public void setCheckerFinal(Vector<ETokenDTO> checkerFinal) {
		this.checkerFinal = checkerFinal;
	}
	private Vector<ETokenDTO> checkerFinal = new Vector<ETokenDTO>();
	public Vector<ETokenDTO> getMakerN() {
		return makerN;
	}
	public void setMakerN(Vector<ETokenDTO> makerN) {
		this.makerN = makerN;
	}
	public Vector<ETokenDTO> getCheckerCounter() {
		return checkerCounter;
	}
	public void setCheckerCounter(Vector<ETokenDTO> checkerCounter) {
		this.checkerCounter = checkerCounter;
	}
	public Vector<ETokenDTO> getMakerCounter() {
		return makerCounter;
	}
	public void setMakerCounter(Vector<ETokenDTO> makerCounter) {
		this.makerCounter = makerCounter;
	}
	public Vector<ETokenDTO> getMakseS() {
		return makseS;
	}
	public void setMakseS(Vector<ETokenDTO> makseS) {
		this.makseS = makseS;
	}
	public Vector<ETokenDTO> getCheckerS() {
		return checkerS;
	}
	public void setCheckerS(Vector<ETokenDTO> checkerS) {
		this.checkerS = checkerS;
	}
	public Vector<ETokenDTO> getCheckerN() {
		return checkerN;
	}
	public void setCheckerN(Vector<ETokenDTO> checkerN) {
		this.checkerN = checkerN;
	}
	public void setCounterChecker(Vector<ETokenDTO> counterChecker) {
		this.counterChecker = counterChecker;
	}
	public Vector<ETokenDTO> getCounterChecker() {
		return counterChecker;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setMakerOldFinal(Vector<ETokenDTO> makerOldFinal) {
		this.makerOldFinal = makerOldFinal;
	}
	public Vector<ETokenDTO> getMakerOldFinal() {
		return makerOldFinal;
	}
	public void setCheckerOldFinal(Vector<ETokenDTO> checkerOldFinal) {
		this.checkerOldFinal = checkerOldFinal;
	}
	public Vector<ETokenDTO> getCheckerOldFinal() {
		return checkerOldFinal;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLanguage() {
		return language;
	}
	public void setCheckerWaitTime(String checkerWaitTime) {
		this.checkerWaitTime = checkerWaitTime;
	}
	public String getCheckerWaitTime() {
		return checkerWaitTime;
	}
	public void setMakerWaitTime(String makerWaitTime) {
		this.makerWaitTime = makerWaitTime;
	}
	public String getMakerWaitTime() {
		return makerWaitTime;
	}
}
