package com.wipro.igrs.deedDraft.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.deedDraft.dto.DeedDraftDTO;
import com.wipro.igrs.deedDraft.sql.DeedDraftSQL;

public class DeedDraftDeriveDAO {

	private Logger logger = Logger.getLogger(DeedDraftDeriveDAO.class);
	
	
	public ArrayList getDraftDeedDetailsForDashboard(String userId) throws Exception
	{
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_DRAFT_DERIVE_DETAILS;
			dbUtility.createPreparedStatement(sql);
			String userArr[] ={userId};
			list = dbUtility.executeQuery(userArr);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			dbUtility.closeConnection();
		}
		logger.debug("size of list in dao-->"+list.size());
		return list;
	}
	
	public boolean copyDeedDraftData(String deedIdOld, String userID,DeedDraftDTO ddto) throws Exception
	{
		boolean flag = false;
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_DRAFT_ID;
			dbUtility.createStatement();
			String deedDraftId = dbUtility.executeQry(sql);
			ddto.setDeedId(deedDraftId);
			dbUtility.setAutoCommit(false);
			
			sql = DeedDraftSQL.COPY_DEED_DRAFT_TXN_DTLS;
			String deedDtls[] = {
					deedDraftId,
					userID,
					deedIdOld
			};
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeUpdate(deedDtls);
			if(flag)
			{
				sql = DeedDraftSQL.COPY_DEED_DRAFT_CONTENT_DTLS;
				String deedContentDtls[] = {
						deedDraftId,
						userID,
						deedIdOld
				};
				dbUtility.createPreparedStatement(sql);
				flag = dbUtility.executeUpdate(deedContentDtls);
			}
			if(flag)
			{
				dbUtility.commit();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			dbUtility.closeConnection();
		}
		return flag;
	}
	public boolean saveDeedDraftPath(DeedDraftDTO ddto){
		boolean ret = false;
		DBUtility dbUtility = null;
		try{
			dbUtility = new DBUtility();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			String path=pr.getValue("deed_draft_upload_location");
			dbUtility.createPreparedStatement("UPDATE IGRS_DEED_DRAFT_TXN_DTLS SET UPLOAD_PATH=?, DEED_DRAFT_STATUS='A' WHERE DEED_DRAFT_ID = ?");
			ret=dbUtility.executeUpdate(new String[]{path+ddto.getDeedId()+"/"+DeedDraftConstant.UPLOAD_NAME_SIGN_DOC, ddto.getDeedId()});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	public String getPageId(String deedId){
		String page = "";
		DBUtility dbUtility = null;
		try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement("select count(*) from IGRS_DEED_DRAFT_CONTENT_DTLS where DEED_DRAFT_ID=?");
			page=dbUtility.executeQry(new String[]{deedId});
			page = page.trim();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return page;
	}
}
