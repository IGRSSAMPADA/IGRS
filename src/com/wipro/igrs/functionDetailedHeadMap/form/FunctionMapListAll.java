package com.wipro.igrs.functionDetailedHeadMap.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class FunctionMapListAll extends org.apache.struts.action.ActionForm {

private ArrayList functions;
private String  functionName;
private String functionId;
private String detailedHeadName;
private String detailedHeadId;


	public String getFunctionName() {
	return functionName;
}

public void setFunctionName(String functionName) {
	this.functionName = functionName;
}

public String getFunctionId() {
	return functionId;
}

public void setFunctionId(String functionId) {
	this.functionId = functionId;
}

public String getDetailedHeadName() {
	return detailedHeadName;
}

public void setDetailedHeadName(String detailedHeadName) {
	this.detailedHeadName = detailedHeadName;
}

public String getDetailedHeadId() {
	return detailedHeadId;
}

public void setDetailedHeadId(String detailedHeadId) {
	this.detailedHeadId = detailedHeadId;
}

	public ArrayList getFunctions() {
	return functions;
}

public void setFunctions(ArrayList functions) {
	this.functions = functions;
}

	public FunctionMapListAll () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
       
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
    return null;
    }


}