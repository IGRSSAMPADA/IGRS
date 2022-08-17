package com.wipro.igrs.supplementaryBudget.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.wipro.igrs.supplementaryBudget.dao.BudgetDAO;
import com.wipro.igrs.supplementaryBudget.form.SupplementaryBudgetForm;

public class SupplementaryBudgetBo
{
	public ArrayList getFiscalYear(HttpServletRequest request) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getFiscalYear(request);
	}
	
	public boolean supCreate(String param1[]) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.supCreate(param1);
	}
	public ArrayList getMonthId(String fiscalYearId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getMonthId(fiscalYearId);
	}
	public boolean budgetMonthReq(String param2[]) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.budgetMonthReq(param2);
	}
	public ArrayList getOfficeNames(HttpServletRequest request) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getOfficeNames(request);
	}
	
	
	public ArrayList getMajorHeadId(String userId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getMajorHeadId(userId);
	}
	public ArrayList getSubMajorHeadId(String userId,String majorId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getSubMajorHeadId(userId,majorId);
	}
	public ArrayList getMinorHeadId(String userId,String subMjorId,String majorId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getMinorHeadId(userId,subMjorId,majorId);
	}
	public ArrayList getSchemeNames(String userId,String minorId,String subMjorId,String majorId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getSchemeNames(userId,minorId,subMjorId,majorId);
	}
	public ArrayList getSegmentNames(String userId,String schemeId,String minorId,String subMjorId,String majorId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getSegmentNames(userId,schemeId,minorId,subMjorId,majorId);
	}
	public ArrayList getObjectNames(String userId,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getObjectNames(userId,segmentId,schemeId,minorId,subMjorId,majorId);
		
	}
	public ArrayList getDHead(String userId,String ojectId,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getDHead(userId,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
	}
	public ArrayList supplementaryView1(String param[]) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.supplementaryView1(param);
	}
	public ArrayList supplementaryView2(String durationFrom,String durationTo) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.supplementaryView2(durationFrom,durationTo);
	}
	public ArrayList supplementaryViewDetails(String budgetReqId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.supplementaryViewDetails(budgetReqId);
	}
	public ArrayList supplementaryViewBetMonths(String budgetReqId) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.supplementaryViewBetMonths(budgetReqId);
	}
	public boolean budgetApporve(String param5[]) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.budgetApporve(param5);
	}
	public ArrayList getMonthIdApprove(String year) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.getMonthIdApprove(year);
	}
	public boolean budgetMonthReqUpdate(String param2[]) throws Exception
	{
		BudgetDAO dao = new BudgetDAO();
		return dao.budgetMonthReqUpdate(param2);
	}
	public ArrayList getDetailedHead(String DHead) throws Exception
	{
	    BudgetDAO dao = new BudgetDAO();
		return dao.getDetailedHead(DHead);
		
	}
	

}
