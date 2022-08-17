package com.wipro.igrs.municipalbodymaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.municipalbodymaster.bao.MunicipalBodyBAO;
import com.wipro.igrs.municipalbodymaster.dto.MunicipalBodyDTO;
import com.wipro.igrs.municipalbodymaster.exception.MunicipalNameAlreadyExistException;

public class MunicipalBodyBD {
	
	private MunicipalBodyBAO municipalBodyBAO;
	
	public MunicipalBodyBD() {
		municipalBodyBAO = new MunicipalBodyBAO();
	}
	
	public ArrayList getAllMunicipalBodies() {
		return municipalBodyBAO.getAllMunicipalBodies();
	}
	
	public void addMunicipalBody(MunicipalBodyDTO bean) throws MunicipalNameAlreadyExistException {
		municipalBodyBAO.addMunicipalBody(bean);
	}
	
	public void deleteMunicipalBody(MunicipalBodyDTO bean) {
		municipalBodyBAO.deleteMunicipalBody(bean);
	}
	
	public void updateMunicipalBody(MunicipalBodyDTO bean) throws MunicipalNameAlreadyExistException {
		municipalBodyBAO.updateMunicipalBody(bean);
	}
	
	public MunicipalBodyDTO getMunicipalBodyById(MunicipalBodyDTO bean) {
		return municipalBodyBAO.getMunicipalBodyById(bean);
	}
	
	public void deleteMunicipalBodies(String[] municipalsIds) {
		municipalBodyBAO.deleteMunicipalBodies(municipalsIds);
	}

}
