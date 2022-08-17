package com.wipro.igrs.noEncumbrance.form;

import java.util.ArrayList;

import com.wipro.igrs.noEncumbrance.dto.DashBoardDTO;
import com.wipro.igrs.noEncumbrance.dto.FloorTypeDTO;
import com.wipro.igrs.noEncumbrance.dto.KhasraDTO;
import com.wipro.igrs.noEncumbrance.dto.NoEncumDTO;
import org.apache.struts.action.ActionForm;
public class NoEncumForm extends ActionForm {
    private NoEncumDTO NoEncumDTO=new NoEncumDTO();
    private ArrayList<FloorTypeDTO> floorMasterList = new ArrayList<FloorTypeDTO>();
    private DashBoardDTO objDashBoardDTO = new DashBoardDTO();
    private KhasraDTO khasra=new KhasraDTO();
	private ArrayList searchResultList=new ArrayList();
    public ArrayList getSearchResultList() {
		return searchResultList;
	}
	public void setSearchResultList(ArrayList searchResultList) {
		this.searchResultList = searchResultList;
	}
	public void setRinPustika(String rinPustika) {
		this.rinPustika = rinPustika;
	}
	public KhasraDTO getKhasra() {
		return khasra;
	}
	private String  khasraNumber;
	private String rinPustika;
	
	public String getKhasraNumber() {
		return khasraNumber;
	}
	public void setKhasraNumber(String khasraNumber) {
		this.khasraNumber = khasraNumber;
	}
	public String getRinPustika() {
		return rinPustika;
	}
	public void setKhasra(KhasraDTO khasra) {
		this.khasra = khasra;
	}

	public ArrayList<FloorTypeDTO> getFloorMasterList() {
		return floorMasterList;
	}

	public void setFloorMasterList(ArrayList<FloorTypeDTO> floorMasterList) {
		this.floorMasterList = floorMasterList;
	}

	public NoEncumDTO getNoEncumDTO() {
		return NoEncumDTO;
	}

	public void setNoEncumDTO(NoEncumDTO NoEncumDTO) {
		this.NoEncumDTO = NoEncumDTO;
	}
    
	public DashBoardDTO getObjDashBoardDTO() {
		return objDashBoardDTO;
	}

	public void setObjDashBoardDTO(DashBoardDTO objDashBoardDTO) {
		this.objDashBoardDTO = objDashBoardDTO;
	}
}
