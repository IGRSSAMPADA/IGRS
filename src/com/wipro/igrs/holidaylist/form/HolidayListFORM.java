
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: HolidayListFORM.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 07th AUG 2008 
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE FORM BEAN FOR HOLIDAY. 
 */


package com.wipro.igrs.holidaylist.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.holidaylist.dto.HolidayListDTO;

/**
 * @author NIHAR M.
 *
 */
public class HolidayListFORM extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private HolidayListDTO holidayDTO = new HolidayListDTO();
	private ArrayList listHolidays = new ArrayList();
	
	private HashMap hashHolidayList = new HashMap();
	
	/*private ArrayList paymentList = new ArrayList();*/
	
	/**
	 * @param index
	 * @param vo
	 *//*
	public void setPaymentItems(int index, HolidayListDTO vo){
		for (; index >= paymentList.size(); paymentList.add(new HolidayListDTO()));
		System.out.println("in setter -->"+vo.getHolidayValue());
		paymentList.set(index, vo);
	}

	*//**
	 * @param index 
	 * @return PaymentDTO
	 *//*

	public HolidayListDTO getPaymentItems(int index){
		for(; index >= paymentList.size(); paymentList.add(new HolidayListDTO()));
		return (HolidayListDTO) paymentList.get(index);
	}
	
	*/
	public ArrayList getListHolidays() {
		return listHolidays;
	}

	public void setListHolidays(ArrayList listHolidays) {
		this.listHolidays = listHolidays;
	}

	public HolidayListDTO getHolidayDTO() {
		return holidayDTO;
	}

	public void setHolidayDTO(HolidayListDTO holidayDTO) {
		this.holidayDTO = holidayDTO;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public HashMap getHashHolidayList() {
		return hashHolidayList;
	}

	public void setHashHolidayList(HashMap hashHolidayList) {
		this.hashHolidayList = hashHolidayList;
	}

	/*public ArrayList getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(ArrayList paymentList) {
		this.paymentList = paymentList;
	}*/
}
