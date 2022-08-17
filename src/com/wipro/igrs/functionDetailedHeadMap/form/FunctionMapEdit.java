package com.wipro.igrs.functionDetailedHeadMap.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class FunctionMapEdit extends org.apache.struts.action.ActionForm {

	private ArrayList functions;
	private ArrayList heads;
	private String selectedFunc;
	private String selectedHead;
    public ArrayList getFunctions() {
		return functions;
	}

	public void setFunctions(ArrayList functions) {
		this.functions = functions;
	}

	public ArrayList getHeads() {
		return heads;
	}

	public void setHeads(ArrayList heads) {
		this.heads = heads;
	}

	public String getSelectedFunc() {
		return selectedFunc;
	}

	public void setSelectedFunc(String selectedFunc) {
		this.selectedFunc = selectedFunc;
	}

	public String getSelectedHead() {
		return selectedHead;
	}

	public void setSelectedHead(String selectedHead) {
		this.selectedHead = selectedHead;
	}

	public FunctionMapEdit () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
    
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
    	return null;
    }


}