package com.wipro.igrs.reconciliation.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.wipro.igrs.reconciliation.dao.ReconciliationDao;
import com.wipro.igrs.targeThreshold.dao.TargetDao;


public class ReconciliationBo
{
	
	public ArrayList getDistrict(HttpServletRequest request) throws Exception
	 {
		ReconciliationDao dao = new ReconciliationDao();
		 	return dao.getDistrict(request);
	 }
	
	public ArrayList getDro(HttpServletRequest request) throws Exception
	 {
		ReconciliationDao dao = new ReconciliationDao();
		 	return dao.getDro(request);
	 }
	
	public ArrayList getFinYear(HttpServletRequest request) throws Exception
	 {
		ReconciliationDao dao = new ReconciliationDao();
		 	return dao.getFinYear(request);
	 }
	
	public ArrayList getDetailedTo(HttpServletRequest request) throws Exception
	 {
		ReconciliationDao dao = new ReconciliationDao();
		 	return dao.getDetailedTo(request);
	 }
	
	
	public boolean reconCreate(String param1[]) throws Exception
	 {
		ReconciliationDao dao = new ReconciliationDao();
		 	return dao.reconCreate(param1);
	 }
	
	public boolean isPrimaryKeyExists(String param1[]) throws Exception
	 {
		ReconciliationDao dao = new ReconciliationDao();
		 	return dao.isPrimaryKeyExists(param1);
	 }
	
	public ArrayList getAllHeads(String dstrctNm, String financYr, String duratnFrm, String duratnTo ) throws Exception
	 {
		ReconciliationDao dao = new ReconciliationDao();
		 	return dao.getAllHeads(dstrctNm,financYr,duratnFrm,duratnTo);
	 }
	
	public ArrayList headDetails(String detailedHeadId) throws Exception
	 {
		ReconciliationDao dao = new ReconciliationDao();
		 	return dao.headDetails(detailedHeadId);
	 }
	

}
