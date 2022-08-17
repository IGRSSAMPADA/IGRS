package com.wipro.igrs.newreginit.dto;

import java.sql.Timestamp;

public class AadharFailTxn {
	
	private int txn_id;
	private String adhar_no;
	private int party_txn_id;
	private int reg_txn_id;
	private int consenter_txn_num;
	private String error_code;
	private String error_desc;
	private String transaction_code;
	//private Timestamp created_by;
	private String ekyc;
	
	public int getTxn_id() {
		return txn_id;
	}
	public void setTxn_id(int txn_id) {
		this.txn_id = txn_id;
	}
	public String getAdhar_no() {
		return adhar_no;
	}
	public void setAdhar_no(String adhar_no) {
		this.adhar_no = adhar_no;
	}
	public int getParty_txn_id() {
		return party_txn_id;
	}
	public void setParty_txn_id(int party_txn_id) {
		this.party_txn_id = party_txn_id;
	}
	public int getReg_txn_id() {
		return reg_txn_id;
	}
	public void setReg_txn_id(int reg_txn_id) {
		this.reg_txn_id = reg_txn_id;
	}
	public int getConsenter_txn_num() {
		return consenter_txn_num;
	}
	public void setConsenter_txn_num(int consenter_txn_num) {
		this.consenter_txn_num = consenter_txn_num;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_desc() {
		return error_desc;
	}
	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}
	public String getTransaction_code() {
		return transaction_code;
	}
	public void setTransaction_code(String transaction_code) {
		this.transaction_code = transaction_code;
	}
	public String getEkyc() {
		return ekyc;
	}
	public void setEkyc(String ekyc) {
		this.ekyc = ekyc;
	}
	
	
}
