package com.wipro.igrs.regcompletion.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import com.wipro.igrs.regcompletion.dao.RegCompMailDAO;

public class RegCompMailBO
{
	
	public ArrayList getPartyTxnId() throws Exception
	 {
		RegCompMailDAO dao = new RegCompMailDAO();
		 	return dao.getPartyTxnId();
	 }
	
	public boolean mailSendType(String param1[]) throws Exception
	 {
		RegCompMailDAO dao = new RegCompMailDAO();
		 	return dao.mailSendType(param1);
	 }
}
