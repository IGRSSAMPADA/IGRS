package com.wipro.igrs.applicationlog.bao;

import java.util.List;

import com.wipro.igrs.applicationlog.dao.ApplicationLogDAO;


public class ApplicationLogBAO {
	
	ApplicationLogDAO appBAO = new ApplicationLogDAO();
	
	public  List searchByDate(String durationfrom,String durationto, String userId, String language)
	{
		return appBAO.searchByDate(durationfrom,durationto,userId,language);
	}
	public  List transSearchByDate(String durationfrom,String durationto, String userId, String language)
	{
		return appBAO.transSearchByDate(durationfrom,durationto,userId,language);
	}
/*	public boolean deletelog(String durationfrom1, String durationfrom12) {
		
		boolean flag = false;
		
		flag = appBAO.deletelog(durationfrom1,durationfrom12);
		
		return flag;
	
	}*/
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
