package com.wipro.igrs.applicationlog.bd;

import java.util.List;

import com.wipro.igrs.applicationlog.bao.ApplicationLogBAO;
import com.wipro.igrs.applicationlog.dao.ApplicationLogDAO;


public class ApplicationLogBD {
	
    ApplicationLogBAO appBAO = new ApplicationLogBAO();
	
	public  List searchByDate(String durationfrom,String durationto, String userId, String language)
	{
		return appBAO.searchByDate(durationfrom,durationto,userId,language);
	}
	public  List transSearchByDate(String durationfrom,String durationto, String userId, String language)
	{
		return appBAO.transSearchByDate(durationfrom,durationto,userId,language);
	}
	public boolean deleteactivitylog(String durationfrom, String durationto, String userId) {
	
		
		 boolean flag = appBAO.deleteactivitylog(durationfrom,durationto,userId);
		 
		 return flag;
	}
	public boolean deletetxnlog(String durationfrom, String durationto,
			String userId) {
		
		boolean flag = appBAO.deletetxnlog(durationfrom,durationto,userId);
		 
		 return flag;
	}
	public String getrolegroup(String userId) throws Exception {
		
		String rolegroup = appBAO.getrolegroup(userId);
		
		return rolegroup;
	}
	
}
