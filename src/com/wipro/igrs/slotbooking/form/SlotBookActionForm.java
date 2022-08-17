package com.wipro.igrs.slotbooking.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.slotbooking.dto.SlotBookDTO;
/**
 * ===========================================================================
 * File           :   SlotBookActionForm.java
 * Description    :   Represents the Business Class

 * Author         :   Hari Krishna GV
 * Created Date   :   Nov 28, 2007

 * ===========================================================================
 */

public class SlotBookActionForm extends ActionForm  {

   
    private  SlotBookDTO bookdto=new SlotBookDTO() ;//like vo
    private  SlotBookDTO dto=new SlotBookDTO();//like dto
    private  ArrayList dist= new ArrayList();//loke dto
    private  SlotBookDTO  slotdto =new SlotBookDTO();
    private  String slotdate;
    private  String tempdate;
    
    
    
    
    
    
    
    
    
    public String getTempdate() {
		return tempdate;
	}

	public void setTempdate(String tempdate) {
		this.tempdate = tempdate;
	}

	private  String availslot;
    //Start:===Added by SreeLatha
    private String durationFrom;
    private String durationTo;
    private ArrayList slotBkViewList = new ArrayList();
    //End:=====Added by SreeLatha
    
    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	this.getBookdto().setDistId("");  
    	this.getBookdto().setSroId("");
    	this.slotdate="";
    	this.getSlotdto().setDistId("");    	
    	this.getBookdto().setSlotdate("");   	
      }

    /**
     * @param dto
     */
    public void setDto(SlotBookDTO dto) {
        this.dto = dto;
    }

    /**
     * @return
     */
    public SlotBookDTO getDto() {
        return dto;
    }

    /**
     * @param dist
     */
    public void setDist(ArrayList dist) {
        this.dist = dist;
    }

    /**
     * @return
     */
    public ArrayList getDist() {
        return dist;
    }

    /**
     * @param bookdto
     */
    public void setBookdto(SlotBookDTO bookdto) {
    	bookdto.getSelctslottime();
        this.bookdto = bookdto;
    }

    /**
     * @return
     */
    public SlotBookDTO getBookdto() {
        return bookdto;
    }

   
    /**
     * @param slotdate
     */
    public void setSlotdate(String slotdate) {
        this.slotdate = slotdate;
    }

    /**
     * @return
     */
    public String getSlotdate() {
        return slotdate;
    }

    /**
     * @param slotdto
     */
    public void setSlotdto(SlotBookDTO slotdto) {
     //System.out.println("in form "+slotdto);
        this.slotdto = slotdto;
    }

    /**
     * @return
     */
    public SlotBookDTO getSlotdto() {
        return slotdto;
    }

	/**
	 * @return
	 */
	public String getAvailslot() {
		return availslot;
	}

	/**
	 * @param availslot
	 */
	public void setAvailslot(String availslot) {
		this.availslot = availslot;
	}

	/**
	 * @return the durationFrom
	 */
	public String getDurationFrom() {
		return durationFrom;
	}

	/**
	 * @param durationFrom the durationFrom to set
	 */
	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
		
		//@author ankita
		System.out.println("we are in form");
		System.out.println(this.durationFrom);
	}

	/**
	 * @return the durationTo
	 */
	public String getDurationTo() {
		return durationTo;
	}

	/**
	 * @param durationTo the durationTo to set
	 */
	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
		
		//@author ankita
		System.out.println("we are in form");
		System.out.println(this.durationFrom);
	}

	/**
	 * @return the slotBkViewList
	 */
	public ArrayList getSlotBkViewList() {
		return slotBkViewList;
	}

	/**
	 * @param slotBkViewList the slotBkViewList to set
	 */
	public void setSlotBkViewList(ArrayList slotBkViewList) {
		this.slotBkViewList = slotBkViewList;
	}

  
}
