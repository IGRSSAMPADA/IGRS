package com.wipro.igrs.partytypemaster.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.partytypemaster.dto.PartyTypeMasterDTO;

public class PartyTypeMasterForm extends org.apache.struts.action.ActionForm {
	
	private String[] selections;
	
	private String id;
	private String name;
	private String desc;
	private String status;

	private String functionId;
	private String dependenceId;
	
	private String functionName;
	private String dependenceName;
	
	private List allFunctions;
	private List allDependences;
	
	private String funcVal;
	private String dependVal;
	
	
	
	
    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the funcVal
	 */
	public String getFuncVal() {
		return funcVal;
	}

	/**
	 * @param funcVal the funcVal to set
	 */
	public void setFuncVal(String funcVal) {
		this.funcVal = funcVal;
	}

	/**
	 * @return the dependVal
	 */
	public String getDependVal() {
		return dependVal;
	}

	/**
	 * @param dependVal the dependVal to set
	 */
	public void setDependVal(String dependVal) {
		this.dependVal = dependVal;
	}

	/**
	 * @return the allFunctions
	 */
	public List getAllFunctions() {
		return allFunctions;
	}

	/**
	 * @param allFunctions the allFunctions to set
	 */
	public void setAllFunctions(List allFunctions) {
		this.allFunctions = allFunctions;
	}

	/**
	 * @return the allDependences
	 */
	public List getAllDependences() {
		return allDependences;
	}

	/**
	 * @param allDependences the allDependences to set
	 */
	public void setAllDependences(List allDependences) {
		this.allDependences = allDependences;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the functionId
	 */
	public String getFunctionId() {
		return functionId;
	}

	/**
	 * @param functionId the functionId to set
	 */
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	/**
	 * @return the dependenceId
	 */
	public String getDependenceId() {
		return dependenceId;
	}

	/**
	 * @param dependenceId the dependenceId to set
	 */
	public void setDependenceId(String dependenceId) {
		this.dependenceId = dependenceId;
	}

	/**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * @return the dependenceName
	 */
	public String getDependenceName() {
		return dependenceName;
	}

	/**
	 * @param dependenceName the dependenceName to set
	 */
	public void setDependenceName(String dependenceName) {
		this.dependenceName = dependenceName;
	}

	/**
	 * @return the selections
	 */
	public String[] getSelections() {
		return selections;
	}

	/**
	 * @param selections the selections to set
	 */
	public void setSelections(String[] selections) {
		this.selections = selections;
	}

	
	public PartyTypeMasterForm () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
       
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
    	return null;
    }


}