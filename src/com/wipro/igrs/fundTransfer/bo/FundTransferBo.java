package com.wipro.igrs.fundTransfer.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.wipro.igrs.fundTransfer.dao.FundTransferDAO;
import com.wipro.igrs.fundTransfer.form.FundTransferForm;

public class FundTransferBo
{
	public ArrayList getDistrict(HttpServletRequest request) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getDistrict(request);
	}
	public ArrayList getFinYear(HttpServletRequest request) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getFinYear(request);
	}
	public boolean fundTransfer2(String param2[]) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.fundTransfer2(param2);
	}
	public ArrayList getDro(HttpServletRequest request) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getDro(request);
	}
	public ArrayList getAllHeads() throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getAllHeads();
	}
	public ArrayList headDetails(String droId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.headDetails(droId);
	}
	public boolean fundApprove2(String param1[]) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.fundApprove2(param1);
	}
	public boolean fundApprove1(String param2[]) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.fundApprove1(param2);
	}
	public ArrayList fundType() throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.fundType();
	}
	public ArrayList viewDroDH() throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.viewDroDH();
	}
	public ArrayList getHeadList(String droId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getHeadList(droId);
	}
	public ArrayList droStatusDetails(String droId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.droStatusDetails(droId);
	}
	public boolean fundReqReject2(String param1[]) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.fundReqReject2(param1);
	}
	public boolean fundReqReject1(String param2[]) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.fundReqReject1(param2);
	}
	public ArrayList getDroIds(HttpServletRequest requset) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getDroIds(requset);
	}
	public ArrayList getMajorHeadId(String userId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getMajorHeadId(userId);
	}
	public ArrayList getSubMajorHeadId(String userId,String majorId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getSubMajorHeadId(userId,majorId);
	}
	public ArrayList getMinorHeadId(String userId,String subMjorId,String majorId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getMinorHeadId(userId,subMjorId,majorId);
	}
	public ArrayList getSchemeNames(String userId,String minorId,String subMjorId,String majorId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getSchemeNames(userId,minorId,subMjorId,majorId);
	}
	public ArrayList getSegmentNames(String userId,String schemeId,String minorId,String subMjorId,String majorId)throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getSegmentNames(userId,schemeId,minorId,subMjorId,majorId);
	}
	public ArrayList getObjectNames(String userId,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getObjectNames(userId,segmentId,schemeId,minorId,subMjorId,majorId);
	}
	public ArrayList getDHead(String userId,String ojectId,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getDHead(userId,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
	}
	public ArrayList getDHead2(String userId,String dhId) throws Exception
	{
		FundTransferDAO dao = new FundTransferDAO();
		return dao.getDHead2(userId,dhId);
	}
}
