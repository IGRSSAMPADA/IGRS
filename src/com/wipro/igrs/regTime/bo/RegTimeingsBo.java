package com.wipro.igrs.regTime.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;




import com.wipro.igrs.regTime.dao.RegTimeingsDao;



public class RegTimeingsBo
{
	
	public ArrayList getMoudleName() throws Exception
	{
		RegTimeingsDao dao = new RegTimeingsDao();
		return dao.getMoudleName();
	}
	
	public ArrayList getSubMoudle() throws Exception
	{
		RegTimeingsDao dao = new RegTimeingsDao();
		return dao.getSubMoudle();
	}
	public ArrayList getFunction() throws Exception
	{
		RegTimeingsDao dao = new RegTimeingsDao();
		return dao.getFunction();
	}
	
	public boolean regCreate(String param1[]) throws Exception
	 {
		RegTimeingsDao dao = new RegTimeingsDao();
		 	return dao.regCreate(param1);
	 }
	
	public ArrayList getModules() throws Exception
	 {
		RegTimeingsDao dao = new RegTimeingsDao();
		 	return dao.getModules();
	 }
	public boolean moudleUpdate(String param1[]) throws Exception
	{
		RegTimeingsDao dao = new RegTimeingsDao();
		return dao.moudleUpdate(param1);
	}
	
	public ArrayList getFuctionId(String function) throws Exception
	 {
		RegTimeingsDao dao = new RegTimeingsDao();
		 	return dao.getFuctionId(function);
	 }
	
}
