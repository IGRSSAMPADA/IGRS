package com.wipro.igrs.willdeposit.form;

import com.wipro.igrs.willdeposit.dto.PaymentDTO;

/**
 * @author NIHAR M.
 *
 */
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import com.wipro.igrs.willdeposit.dto.ChallanDTO;

public class PaymentForm extends ActionForm {

	/**
	 * NIHAR M.
	 */
	private static final long serialVersionUID = 1L;

	private PaymentDTO paymentDTO = new PaymentDTO();

	private String radioSelect;
	private String durationFrom;
	private String durationTo;
	private String cashDate;
	private String challanDate;
	private String challancheck;
	private double totAmt;

	private ArrayList paymentList = new ArrayList();
	private ChallanDTO payDTO = new ChallanDTO();

	private String hidNextMode;

	private String challanNO;


	/**
	 * @return the challanNO
	 */
	public String getChallanNO() {
		return challanNO;
	}

	/**
	 * @param challanNO the challanNO to set
	 */
	public void setChallanNO(String challanNO) {
		this.challanNO = challanNO;
	}

	/**
	 * @return the challancheck
	 */
	public String getChallancheck() {
		return challancheck;
	}

	/**
	 * @param challancheck the challancheck to set
	 */
	public void setChallancheck(String challancheck) {
		this.challancheck = challancheck;
	}

	/**
	 * @return the challanDate
	 */
	public String getChallanDate() {
		return challanDate;
	}

	/**
	 * @param challanDate the challanDate to set
	 */
	public void setChallanDate(String challanDate) {
		this.challanDate = challanDate;
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
	}



	/**
	 * @return the radioSelect
	 */
	public String getRadioSelect() {
		return radioSelect;
	}

	/**
	 * @param radioSelect the radioSelect to set
	 */
	public void setRadioSelect(String radioSelect) {
		this.radioSelect = radioSelect;
	}

	/**
	 * @return the paymentDTO
	 */
	public PaymentDTO getPaymentDTO() {
		return paymentDTO;
	}

	/**
	 * @param paymentDTO the paymentDTO to set
	 */
	public void setPaymentDTO(PaymentDTO paymentDTO) {
		this.paymentDTO = paymentDTO;
	}

	/**
	 * @return the cashDate
	 */
	public String getCashDate() {
		return cashDate;
	}

	/**
	 * @param cashDate the cashDate to set
	 */
	public void setCashDate(String cashDate) {
		this.cashDate = cashDate;
	}

	/**
	 * @param index
	 * @param vo
	 */
	public void setPaymentItems(int index, ChallanDTO vo){
		for (; index >= paymentList.size(); paymentList.add(new ChallanDTO()));
		System.out.println("in setter -->"+vo.getChallanDate());
		System.out.println(vo.getAmount());
		System.out.println(vo.getChallanNo());
		paymentList.set(index, vo);
	}

	/**
	 * @param index 
	 * @return PaymentDTO
	 */

	public ChallanDTO getPaymentItems(int index){
		for(; index >= paymentList.size(); paymentList.add(new ChallanDTO()));
		return (ChallanDTO) paymentList.get(index);
	}

	/**
	 * @return the paymentList
	 */
	public ArrayList getPaymentList() {
		return paymentList;
	}

	/**
	 * @param paymentList the paymentList to set
	 */
	public void setPaymentList(ArrayList paymentList) {
		this.paymentList = paymentList;
	}

	/**
	 * @return the payDTO
	 */
	public ChallanDTO getPayDTO() {
		return payDTO;
	}

	/**
	 * @param payDTO the payDTO to set
	 */
	public void setPayDTO(ChallanDTO payDTO) {
		this.payDTO = payDTO;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.radioSelect = null;
		this.durationFrom = null;
		this.durationTo = null;
		this.cashDate = null;
		this.challanDate = null;
		this.challancheck = null;
		this.getPaymentDTO().setReceiptID("");
		this.getPaymentDTO().setAmount(0.0);
		this.getPayDTO().setChallanNo("");
		this.getPayDTO().setAmount("");
		this.getPayDTO().setChallanDate("");
	}

	/**
	 * @return the totAmt
	 */
	public double getTotAmt() {
		return totAmt;
	}

	/**
	 * @param totAmt the totAmt to set
	 */
	public void setTotAmt(double totAmt) {
		this.totAmt = totAmt;
	}

	/**
	 * @return the hidNextMode
	 */
	public String getHidNextMode() {
		return hidNextMode;
	}

	/**
	 * @param hidNextMode the hidNextMode to set
	 */
	public void setHidNextMode(String hidNextMode) {
		this.hidNextMode = hidNextMode;
	}
}
