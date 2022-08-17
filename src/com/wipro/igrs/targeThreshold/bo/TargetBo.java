package com.wipro.igrs.targeThreshold.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import com.wipro.igrs.targeThreshold.dao.TargetDao;
import com.wipro.igrs.targeThreshold.dto.TargetDto;

public class TargetBo
{
	
	
	
	 public ArrayList getOfficeId(HttpServletRequest request) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.getOfficeId(request);
	 }
	 
	 public ArrayList getMonthId(HttpServletRequest request,String yearId) throws Exception
	 {
		 System.out.println("I am in bo of getMonthId=================");
		 TargetDao dao = new TargetDao();
		 	return dao.getMonthId(request,yearId);
	 }
	 
	 public ArrayList getYear(HttpServletRequest request) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.getYear(request);
	 }
	 
	 public boolean targetAnnual(String param1[]) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.targetAnnual(param1);
	 }
	 
	 public ArrayList monthValues(String droId,String yearId) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.monthValues(droId,yearId);
	 }
	 
	 public boolean monthData(String param1[]) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.monthData(param1);
	 }
	 
	 public ArrayList stampCase(String droId,String yearId) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.stampCase(droId,yearId);
	 }
	 
	 public ArrayList rrcTarget(String droId,String yearId) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.rrcTarget(droId,yearId);
	 }
	 
	 public ArrayList sroTarget(String droId,String yearId) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.sroTarget(droId,yearId);
	 }
	 
	 public ArrayList droTarget(String droId,String yearId) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.droTarget(droId,yearId);
	 }
	 public ArrayList poTarget(String droId,String yearId) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.poTarget(droId,yearId);
	 }
	 
	 public ArrayList annualView() throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.annualView();
	 }
	 
	 public ArrayList AnnualDetails(String targetId) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.AnnualDetails(targetId);
	 }
	 
	 public boolean fundApprove2(String param1[]) throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.fundApprove2(param1);
	 }
	 
	 
	 public ArrayList monthViewList() throws Exception
	 {
		 TargetDao dao = new TargetDao();
		 	return dao.monthViewList();
	 }
	 
}
