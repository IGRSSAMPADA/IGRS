package com.wipro.igrs.propertytypeuom.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class PropTypeMapForm extends ActionForm{
	

	private String propertyTypeName;
	private String propertyTypeL1Name;
	private String propertyTypeL2Name;
	private String uomName;
	private String id;	
	
	private ArrayList property;
	private ArrayList l1;
	private ArrayList l2;
	private ArrayList uom;
	
	
	private String[] selected;


	public String getPropertyTypeName() {
		return propertyTypeName;
	}


	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}


	public String getPropertyTypeL1Name() {
		return propertyTypeL1Name;
	}


	public void setPropertyTypeL1Name(String propertyTypeL1Name) {
		this.propertyTypeL1Name = propertyTypeL1Name;
	}


	public String getPropertyTypeL2Name() {
		return propertyTypeL2Name;
	}


	public void setPropertyTypeL2Name(String propertyTypeL2Name) {
		this.propertyTypeL2Name = propertyTypeL2Name;
	}



	public String getUomName() {
		return uomName;
	}


	public void setUomName(String uomName) {
		this.uomName = uomName;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String[] getSelected() {
		return selected;
	}


	public void setSelected(String[] selected) {
		this.selected = selected;
	}





	public ArrayList getProperty() {
		return property;
	}


	public void setProperty(ArrayList property) {
		this.property = property;
	}


	public ArrayList getL1() {
		return l1;
	}


	public void setL1(ArrayList l1) {
		this.l1 = l1;
	}


	public ArrayList getL2() {
		return l2;
	}


	public void setL2(ArrayList l2) {
		this.l2 = l2;
	}


	public ArrayList getUom() {
		return uom;
	}


	public void setUom(ArrayList uom) {
		this.uom = uom;
	}



	


}
