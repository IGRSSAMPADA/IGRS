package com.wipro.igrs.cybertehsil.forms.dto;
import java.io.Serializable;
import java.util.ArrayList;

public class FormOneDTO implements Serializable{
	private String declarationId;
	private ArrayList<DeclarationDTO> declaration;

	public String getDeclarationId() {
		return declarationId;
	}

	public void setDeclarationId(String declarationId) {
		this.declarationId = declarationId;
	}

	public ArrayList<DeclarationDTO> getDeclaration() {
		return declaration;
	}

	public void setDeclaration(ArrayList<DeclarationDTO> declaration) {
		this.declaration = declaration;
	}

	
	
	
}