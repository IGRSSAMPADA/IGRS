package com.wipro.igrs.municipalbodymaster.bao;

import java.util.ArrayList;
import java.util.Iterator;

import com.wipro.igrs.municipalbodymaster.dao.MunicipalBodyDAO;
import com.wipro.igrs.municipalbodymaster.dto.MunicipalBodyDTO;
import com.wipro.igrs.municipalbodymaster.exception.MunicipalNameAlreadyExistException;

public class MunicipalBodyBAO {
	
	private MunicipalBodyDAO municipalBodyDAO;
	
	public MunicipalBodyBAO() {
		municipalBodyDAO = new MunicipalBodyDAO();
	}
	
	public ArrayList getAllMunicipalBodies() {
		return municipalBodyDAO.getAllMunicipalBodies();
	}
	
	public void addMunicipalBody(MunicipalBodyDTO bean) throws MunicipalNameAlreadyExistException {
		
		if (municipalBodyDAO.isMunicipalBodyExist(bean)) {
			throw new MunicipalNameAlreadyExistException();
		} else {
			municipalBodyDAO.addMunicipalBody(bean);
		}
	}
	
	public void deleteMunicipalBody(MunicipalBodyDTO bean) {
		municipalBodyDAO.deleteMunicipalBody(bean);
	}
	
	public void updateMunicipalBody(MunicipalBodyDTO bean) throws MunicipalNameAlreadyExistException {
		if (municipalBodyDAO.isMunicipalBodyExist(bean)) {
			MunicipalBodyDTO municipalBodyDTO = municipalBodyDAO.getMunicipalBodyByName(bean);
    		if ((municipalBodyDTO != null) && (municipalBodyDTO.getMunicipalBodyId() != null)) {
				if (municipalBodyDTO.getMunicipalBodyId().equals(bean.getMunicipalBodyId())) {
					municipalBodyDAO.updateMunicipalBody(bean);
				} else {
					throw new MunicipalNameAlreadyExistException();
				}
    		} else {
    			municipalBodyDAO.updateMunicipalBody(bean);
    		}
    	} else {
    		municipalBodyDAO.updateMunicipalBody(bean);
    	}
	}
	
	public MunicipalBodyDTO getMunicipalBodyById(MunicipalBodyDTO bean) {
		return municipalBodyDAO.getMunicipalBodyById(bean);
	}
	
	public void deleteMunicipalBodies(String[] municipalsIds) {
		
		String currentMunicipalId = null;
		MunicipalBodyDTO currentMunicipalDTO = null;
		if (municipalsIds != null) {
			for (int i = 0; i < municipalsIds.length; i++) {
				currentMunicipalId = municipalsIds[i];
				currentMunicipalDTO = new MunicipalBodyDTO();
				currentMunicipalDTO.setMunicipalBodyId(currentMunicipalId);
				municipalBodyDAO.deleteMunicipalBody(currentMunicipalDTO);
			}
		}
	}

}
