package com.wipro.igrs.areaManagement.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.areaManagement.dto.areaManagementDTO;
import com.wipro.igrs.areaManagement.form.areaManagementForm;
import com.wipro.igrs.areaManagement.sql.areaManagementSQL;
import com.wipro.igrs.common.dto.CommonDTO;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;
import com.wipro.igrs.useracctmgmt.sql.UserCommonSQL;


public class areaManagementDAO {
	
	DBUtility dbUtil=null ;
	private static Logger logger = org.apache.log4j.Logger.getLogger(areaManagementDAO.class);

	public ArrayList getCountry(String language) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_COUNTRY_LIST;
			details=dbUtility.executeQuery(sql);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;


	}

	public ArrayList getState() throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_STATE_LIST;
			String temp[] = new String[1];
			temp[0]="1";
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public  areaManagementDTO getdetails(String lang, String id, String type, areaManagementDTO dto2) throws Exception {
		
		
		String sql="";
		DBUtility dbUtility = null;
		try 
		{
			dbUtility = new DBUtility();
			
			if("Tehsil".equalsIgnoreCase(type))
			{
				sql = areaManagementSQL.SELECT_TEHSIL_DETLS;
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[1];
				sd[0] = id;
				
				ArrayList list = dbUtility.executeQuery(sd);
				ArrayList list1 = (ArrayList) list.get(0);
				
				dto2.setTehsilId(list1.get(0)==null ?" ":list1.get(0).toString());
				dto2.setTehsilName(list1.get(1)==null ?" ":list1.get(1).toString());
				dto2.setTehsilHindiName(list1.get(2)==null ?" ":list1.get(2).toString());
				dto2.setTehsilDesc(list1.get(3)==null ?" ":list1.get(3).toString());
				logger.debug("###"+dto2.getTehsilName());
			}
			if("SubArea".equalsIgnoreCase(type))
			{
				sql = areaManagementSQL.SELECT_SUBAREA_DETLS;
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[1];
				sd[0] = id;
				
				ArrayList list = dbUtility.executeQuery(sd);
				ArrayList list1 = (ArrayList) list.get(0);
				
				dto2.setSubAreaId(list1.get(0)==null ?" ":list1.get(0).toString());
				dto2.setSubAreaName(list1.get(1)==null ?" ":list1.get(1).toString());
				dto2.setSubAreaHindiName(list1.get(2)==null ?" ":list1.get(2).toString());
				dto2.setSubAreaDesc(list1.get(3)==null ?" ":list1.get(3).toString());
				dto2.setMunciBodyListId(list1.get(4)==null ?" ":list1.get(4).toString());
				logger.debug("###"+dto2.getSubAreaName());
			}
			if("Ward".equalsIgnoreCase(type))
			{
				sql = areaManagementSQL.SELECT_WARD_DETLS;
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[1];
				sd[0] = id;
				
				ArrayList list = dbUtility.executeQuery(sd);
				ArrayList list1 = (ArrayList) list.get(0);
				
				dto2.setWardId(list1.get(0)==null ?" ":list1.get(0).toString());
				dto2.setWardName(list1.get(1)==null ?" ":list1.get(1).toString());
				dto2.setWardHindiName(list1.get(2)==null ?" ":list1.get(2).toString());
				dto2.setWardDesc(list1.get(3)==null ?" ":list1.get(3).toString());
				dto2.setWardorPatwariListId(list1.get(4)==null ?" ":list1.get(4).toString());
				logger.debug("###"+dto2.getWardName());
			}
			if("Mohalla".equalsIgnoreCase(type))
			{
				sql = areaManagementSQL.SELECT_COLONY_DETLS;
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[1];
				sd[0] = id;
				
				ArrayList list = dbUtility.executeQuery(sd);
				ArrayList list1 = (ArrayList) list.get(0);
				
				dto2.setMohallaId(list1.get(0)==null ?" ":list1.get(0).toString());
				dto2.setMohallaName(list1.get(1)==null ?" ":list1.get(1).toString());
				dto2.setMohallaHindiName(list1.get(2)==null ?" ":list1.get(2).toString());
				dto2.setMohallaDesc(list1.get(3)==null ?" ":list1.get(3).toString());
				dto2.setSubClauseListId(list1.get(4)==null ?" ":list1.get(4).toString());
				logger.debug("###"+dto2.getMohallaName());
			}
			
			
		} catch (Exception e) {
			//System.out.println("Caugut in Exception: " + e);
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return dto2;
	}

	

	public ArrayList getDistrict(String stateId,String officeId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_DISTRICT_LIST;
			String temp[] = new String[2];
			temp[0]=stateId;
			temp[1]=officeId;
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getTehsil(String distId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_TEHSIL_LIST;
			String temp[] = new String[1];
			temp[0]=distId;
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getArea(String lang, String logicId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			String sql ="";
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			if("SubArea".equalsIgnoreCase(logicId))
			{
				sql =areaManagementSQL.SELECT_URBANAREA_LIST;
			}
			else
			{
				 sql =areaManagementSQL.SELECT_AREA_LIST;
			}
			String temp[] = new String[1];
			temp[0]="1";
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getSubArea(String tehId, String areaId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			String sql="";
			
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			if("1".equalsIgnoreCase(areaId))
			{
				 sql =areaManagementSQL.SELECT_RURALSUBAREA_LIST;
				 String temp[] = new String[1];
					temp[0]=areaId;
					dbUtility.createPreparedStatement(sql);
					details=dbUtility.executeQuery(temp);
			}
			else
			{
				 sql =areaManagementSQL.SELECT_SUBAREA_LIST;
				 String temp[] = new String[2];
					temp[0]=areaId;
					temp[1]=tehId;
					dbUtility.createPreparedStatement(sql);
					details=dbUtility.executeQuery(temp);
			}
			
	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getWard(String subareaId, String areaId, String tehId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_WARD_LIST;
			String temp[] = new String[3];
			temp[0]=tehId;
			temp[1]=areaId;
			temp[2]=subareaId;
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getMohalla(String tehId, String areaId, String subareaId,
			String wardId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_COLONY_LIST;
			String temp[] = new String[4];
			temp[0]=tehId;
			temp[1]=areaId;
			temp[2]=subareaId;
			temp[3]=wardId;
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getsubClause() throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_SUBCLAUSE_LIST;
			String temp[] = new String[1];
			temp[0]="1";
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public boolean saveTehsilData(areaManagementForm form, String userId) throws Exception {
		DBUtility dbUtility = null;
		boolean flag=false;
		try{
		
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String check=areaManagementSQL.CHECK_EXIST_TEHSIL;
		
		dbUtility.createPreparedStatement(check);
		String checkarr[] = new String[3];
		checkarr[0]=form.getAppdto().getTehsilName().toUpperCase();
		checkarr[1]=form.getAppdto().getTehsilHindiName().toUpperCase();
		checkarr[2]=form.getAppdto().getDistrictId();
		    String count = dbUtility.executeQry(checkarr);
		
		if(Integer.parseInt(count)>0)
		{
			form.getAppdto().setCheck("exists");
		}
		
		else
		{
		String sql=areaManagementSQL.INSERT_TEHSIL_DATA;
			
		try {
			dbUtility.createPreparedStatement(sql);
			String temp[] = new String[5];
			temp[0]=form.getAppdto().getTehsilName().toUpperCase();
			temp[1]=form.getAppdto().getTehsilHindiName().toUpperCase();
			temp[2]=form.getAppdto().getTehsilDesc();
			temp[3]=userId;
			temp[4]=form.getAppdto().getDistrictId();
			flag = dbUtility.executeUpdate(temp);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			if(flag)
			{
				dbUtility.commit();
			}
			
			else
			{
				dbUtility.rollback();
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public boolean saveSubareaData(areaManagementForm form, String userId) throws Exception {
		DBUtility dbUtility = null;
		boolean flag=false;
		try{
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String check=areaManagementSQL.CHECK_EXIST_SUBAREA;
		
		dbUtility.createPreparedStatement(check);
		String checkarr[] = new String[4];
		checkarr[0]=form.getAppdto().getSubAreaName().toUpperCase();
		checkarr[1]=form.getAppdto().getSubAreaHindiName().toUpperCase();
		checkarr[2]=form.getAppdto().getTehsilListId();
		checkarr[3]=form.getAppdto().getAreaId();
		//checkarr[4]=form.getAppdto().getMunciBodyListId();
		    String count = dbUtility.executeQry(checkarr);
		
		if(Integer.parseInt(count)>0)
		{
			form.getAppdto().setCheck("exists");
		}
		
		else
		{
		String sql=areaManagementSQL.INSERT_SUBAREA_DATA;
			
		try {
			dbUtility.createPreparedStatement(sql);
			String temp[] = new String[7];
			temp[0]=form.getAppdto().getSubAreaName().toUpperCase();
			temp[1]=form.getAppdto().getSubAreaDesc();
			temp[2]=userId;
			temp[3]=form.getAppdto().getAreaId();
			temp[4]=form.getAppdto().getSubAreaHindiName().toUpperCase();
			temp[5]=form.getAppdto().getTehsilListId();
			temp[6]=form.getAppdto().getMunciBodyListId();
			
			
			
			
			flag = dbUtility.executeUpdate(temp);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if(flag)
			{
				dbUtility.commit();
			}
			else
			{
				dbUtility.rollback();
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public boolean saveWardData(areaManagementForm form, String userId) throws Exception {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag2=false;
		try{
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String check=areaManagementSQL.CHECK_EXIST_WARD;
		
		dbUtility.createPreparedStatement(check);
		String checkarr[] = new String[5];
		checkarr[0]=form.getAppdto().getWardName().toUpperCase();
		checkarr[1]=form.getAppdto().getWardHindiName().toUpperCase();
		// checkarr[2]=form.getAppdto().getWardorPatwariListId();
		checkarr[2]=form.getAppdto().getTehsilListId();
		checkarr[3]=form.getAppdto().getAreaId();
		checkarr[4]=form.getAppdto().getSubAreaListId();
		
		    String count = dbUtility.executeQry(checkarr);
		
		if(Integer.parseInt(count)>0)
		{
			form.getAppdto().setCheck("exists");
		}
		
		else
		{
		String sql=areaManagementSQL.GET_WARD_SEQ;
		
		  dbUtility.createStatement();
	      
			    
		    String wardId = dbUtility.executeQry(sql);
		
		
		
		
		String sql1=areaManagementSQL.INSERT_WARD_DATA;
			
		try {
			dbUtility.createPreparedStatement(sql1);
			String temp[] = new String[9];
			
			temp[0]=wardId;
			temp[1]=form.getAppdto().getWardName().toUpperCase();
			temp[2]=form.getAppdto().getWardDesc();
			temp[3]=form.getAppdto().getTehsilListId();
			temp[4]=userId;
			temp[5]=form.getAppdto().getAreaId();
			temp[6]=form.getAppdto().getWardorPatwariListId();
			temp[7]=form.getAppdto().getWardHindiName().toUpperCase();
			temp[8]=form.getAppdto().getSubAreaListId();
			
			flag = dbUtility.executeUpdate(temp);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(flag)
		{
			String sql2=areaManagementSQL.INSERT_SUBWARD_DATA;
			String temp1[] = new String[3];
			temp1[0]=wardId;
			temp1[1]=form.getAppdto().getSubAreaListId();
			temp1[2]=userId;
			
			dbUtility.createPreparedStatement(sql2);			
			flag2 = dbUtility.executeUpdate(temp1);
			
			if(flag && flag2)
			{
				dbUtility.commit();
			}
			else
			{
				dbUtility.rollback();
				return false;
			}
		}
		else
		{
			dbUtility.rollback();
			return false;
		}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public boolean saveMohallaData(areaManagementForm form, String userId) throws Exception {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag2=false;
		try {
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String wardNamesql=areaManagementSQL.GET_WARD_PATWARI_NAME;
		 dbUtility.createPreparedStatement(wardNamesql);
		  String subtemp[] = new String[1];
		  subtemp[0]=form.getAppdto().getWardListId();
		  String wardName = dbUtility.executeQry(subtemp);
		  
		String check=areaManagementSQL.CHECK_EXIST_COLONY;
		
		dbUtility.createPreparedStatement(check);
		String checkarr[] = new String[5];
		checkarr[0]=form.getAppdto().getTehsilListId();
		checkarr[1]=form.getAppdto().getSubAreaListId();
		checkarr[2]=wardName;
		checkarr[3]=form.getAppdto().getMohallaName().toUpperCase();
		checkarr[4]=form.getAppdto().getMohallaHindiName().toUpperCase();
		//checkarr[5]=form.getAppdto().getSubClauseListId();
		String count = dbUtility.executeQry(checkarr);
		
		if(Integer.parseInt(count)>0)
		{
			form.getAppdto().setCheck("exists");
		}
		
		else
		{

		  String sqlsubareaId=areaManagementSQL.GET_SUB_AREA_ID;
			dbUtility.createPreparedStatement(sqlsubareaId);
		  String subwardId = dbUtility.executeQry(subtemp);
		  
		  
		  String sql=areaManagementSQL.GET_COLONY_SEQ;
		 dbUtility.createStatement();
		  String colonyId = dbUtility.executeQry(sql);
		
		String sql1=areaManagementSQL.INSERT_MOHALLA_DATA;
			
		try {
			dbUtility.createPreparedStatement(sql1);
			String temp[] = new String[9];
			temp[0]=colonyId;
			temp[1]=form.getAppdto().getMohallaName().toUpperCase();
			temp[2]=form.getAppdto().getMohallaDesc();
			temp[3]=form.getAppdto().getMohallaHindiName().toUpperCase();
			temp[4]=userId;
			temp[5]=form.getAppdto().getSubClauseListId();
			temp[6]=form.getAppdto().getSubAreaListId();
			temp[7]=form.getAppdto().getTehsilListId();
			temp[8]=wardName;
			flag = dbUtility.executeUpdate(temp);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag)
		{
			String sql2=areaManagementSQL.INSERT_COLWARD_DATA;
			String temp1[] = new String[3];
			temp1[0]=colonyId;
			temp1[1]=subwardId;
			temp1[2]=userId;
			dbUtility.createPreparedStatement(sql2);
			flag2 = dbUtility.executeUpdate(temp1);
			if(flag && flag2)
			{
				dbUtility.commit();
			}
			else
			{
				dbUtility.rollback();
				return false;
			}
		}
		else
		{
			dbUtility.rollback();
			return false;
		}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public ArrayList getmunicipalBody() throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_MUNICIPAL_BODY_LIST;
			String temp[] = new String[1];
			temp[0]="1";
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getwardorPatwari() throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_WARD_OR_PATWARI_LIST;
			String temp[] = new String[1];
			temp[0]="1";
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public boolean deleteTehsil(String[] abc) {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag1=false;
		boolean flag2=false;
		boolean flag3=false;
		boolean flag4=false;
		boolean flag5=false;
		try{
		
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qstnMarks = "(";
		for(int i = 0; i<abc.length; i++){
			qstnMarks+="?";
			
			if(i!=abc.length-1){
				qstnMarks+=",";	
			}
		}
		qstnMarks+=")";
		
		String sql=areaManagementSQL.DELETE_TEHSIL+qstnMarks;
		String sql1=areaManagementSQL.DELETE_TEHSIL_SUBAREA_LINKAGE+qstnMarks;
		String sql2=areaManagementSQL.DELETE_TEHSIL_WARD_LINKAGE+qstnMarks;
		String sql3=areaManagementSQL.DELETE_TEHSIL_SUBAREA_WARD_LINKAGE+qstnMarks+")";
		String sql4=areaManagementSQL.DELETE_TEHSIL_COLONY_LINKAGE+qstnMarks;
		String sql5=areaManagementSQL.DELETE_TEHSIL_COLONY_WARD_LINKAGE+qstnMarks+")"+")";
			
		try {
			dbUtility.createPreparedStatement(sql);
			
			flag = dbUtility.executeUpdate(abc);
			
dbUtility.createPreparedStatement(sql1);
			
			flag1 = dbUtility.executeUpdate(abc);
			
dbUtility.createPreparedStatement(sql2);
			
			flag2 = dbUtility.executeUpdate(abc);
			
dbUtility.createPreparedStatement(sql3);
			
			flag3 = dbUtility.executeUpdate(abc);
			
dbUtility.createPreparedStatement(sql4);
			
			flag4 = dbUtility.executeUpdate(abc);
			
dbUtility.createPreparedStatement(sql5);
			
			flag5 = dbUtility.executeUpdate(abc);
			
			if (flag)
			{
			dbUtility.commit();
			}
			else
			{
				dbUtility.rollback();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	public boolean deleteSubArea(String[] abc) {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag1=false;
		boolean flag2=false;
		boolean flag3=false;
		boolean flag4=false;
		try{
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qstnMarks = "(";
		for(int i = 0; i<abc.length; i++){
			qstnMarks+="?";
			
			if(i!=abc.length-1){
				qstnMarks+=",";	
			}
		}
		qstnMarks+=")";
		
		String sql=areaManagementSQL.DELETE_SUBAREA+qstnMarks;
		String sql1=areaManagementSQL.DELETE_SUBAREA_WARD_LINKAGE+qstnMarks;
		String sql2=areaManagementSQL.DELETE_SUBAREA_WARD_MAPPING_LINKAGE+qstnMarks;
		String sql3=areaManagementSQL.DELETE_SUBAREA_COLONY_LINKAGE+qstnMarks+")"+")";
		String sql4=areaManagementSQL.DELETE_SUBAREA_COLONY_MAPPING_LINKAGE+qstnMarks+")";
			
		try {
			dbUtility.createPreparedStatement(sql);
			
			flag = dbUtility.executeUpdate(abc);
dbUtility.createPreparedStatement(sql1);
			
			flag1 = dbUtility.executeUpdate(abc);
dbUtility.createPreparedStatement(sql2);
			
			flag2 = dbUtility.executeUpdate(abc);
dbUtility.createPreparedStatement(sql3);
			
			flag3 = dbUtility.executeUpdate(abc);
dbUtility.createPreparedStatement(sql4);
			
			flag4 = dbUtility.executeUpdate(abc);
			if (flag)
			{
			dbUtility.commit();
			}
			else
			{
				dbUtility.rollback();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean deleteWard(String[] abc) {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag1=false;
		boolean flag2=false;
		boolean flag3=false;
		
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qstnMarks = "(";
		for(int i = 0; i<abc.length; i++){
			qstnMarks+="?";
			
			if(i!=abc.length-1){
				qstnMarks+=",";	
			}
		}
		qstnMarks+=")";
		
		String sql=areaManagementSQL.DELETE_WARD+qstnMarks;
		
		String sql1=areaManagementSQL.DELETE_SUBWARD+qstnMarks;
		String sql2=areaManagementSQL.DELETE_WARD_COLONY_LINKAGE+qstnMarks+")"+")";
		String sql3=areaManagementSQL.DELETE_WARD_COLONY_MAPPING_LINKAGE+qstnMarks+")";
			
		try {
			dbUtility.createPreparedStatement(sql);
			
			flag = dbUtility.executeUpdate(abc);
			
			dbUtility.createPreparedStatement(sql1);
			
			flag1 = dbUtility.executeUpdate(abc);
dbUtility.createPreparedStatement(sql2);
			
			flag2 = dbUtility.executeUpdate(abc);
dbUtility.createPreparedStatement(sql3);
			
			flag3 = dbUtility.executeUpdate(abc);
			if (flag&&flag1)
			{
			dbUtility.commit();
			}
			else
			{
				dbUtility.rollback();
				flag=false;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return flag;
	}

	public boolean deleteMohalla(String[] abc) {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag1=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qstnMarks = "(";
		for(int i = 0; i<abc.length; i++){
			qstnMarks+="?";
			
			if(i!=abc.length-1){
				qstnMarks+=",";	
			}
		}
		qstnMarks+=")";
		
		String sql=areaManagementSQL.DELETE_COLONY+qstnMarks;
		String sql1=areaManagementSQL.DELETE_COLONYWARD+qstnMarks;
			
		try {
			dbUtility.createPreparedStatement(sql);
			
			flag = dbUtility.executeUpdate(abc);
			
			dbUtility.createPreparedStatement(sql1);
			
			flag1 = dbUtility.executeUpdate(abc);
			if (flag&&flag1)
			{
			dbUtility.commit();
			}
			else
			{
				dbUtility.rollback();
				flag=false;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return flag;
	}

	public boolean updatedetails(String lang, String id, String type,
			areaManagementDTO dto) throws Exception {
		DBUtility dbUtility = null;
		boolean flag=false;
		
	try{
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("Tehsil".equalsIgnoreCase(type))
		{
			String check=areaManagementSQL.CHECK_EXIST_TEHSIL;
			
			dbUtility.createPreparedStatement(check);
			String checkarr[] = new String[3];
			checkarr[0]=dto.getTehsilName().toUpperCase();
			checkarr[1]=dto.getTehsilHindiName();
			checkarr[2]=dto.getDistrictId();
			    String count = dbUtility.executeQry(checkarr);
			
			if(Integer.parseInt(count)==1 || Integer.parseInt(count)==0)
			{
			String sql=areaManagementSQL.UPDATE_TEHSIL;
				
			
				String abc[] = new String[4];
				abc[0]=dto.getTehsilName().toUpperCase();
				abc[1]=dto.getTehsilHindiName();
				abc[2]=dto.getTehsilDesc();
				abc[3]=id;
				dbUtility.createPreparedStatement(sql);
				
				flag = dbUtility.executeUpdate(abc);
				
				if (flag)
				{
				dbUtility.commit();
				}
				else
				{
					dbUtility.rollback();
				}
				
			}
			else{
				dto.setCheck("exists");
			}
			
		}
		if("SubArea".equalsIgnoreCase(type))
		{
			String check=areaManagementSQL.CHECK_EXIST_SUBAREA;
			
			dbUtility.createPreparedStatement(check);
			String checkarr[] = new String[5];
			checkarr[0]=dto.getSubAreaName();
			checkarr[1]=dto.getSubAreaHindiName();
			checkarr[2]=dto.getTehsilListId();
			checkarr[3]=dto.getAreaId();
			checkarr[4]=dto.getMunciBodyListId();
			    String count = dbUtility.executeQry(checkarr);
			
			
			
			if(Integer.parseInt(count)==1 || Integer.parseInt(count)==0)
			{
			String sql=areaManagementSQL.UPDATE_SUBAREA;
			
			String abc[] = new String[5];
			abc[0]=dto.getSubAreaName().toUpperCase();
			abc[1]=dto.getSubAreaHindiName();
			abc[2]=dto.getSubAreaDesc();
			abc[3]=dto.getMunciBodyListId();
			abc[4]=id;
				dbUtility.createPreparedStatement(sql);
				
				flag = dbUtility.executeUpdate(abc);
				
			
				if (flag)
				{
				dbUtility.commit();
				}
				else
				{
					dbUtility.rollback();
				}
			}
			else{
				dto.setCheck("exists");
			}
		}
		
		if("Ward".equalsIgnoreCase(type))
		{
			System.out.println("ward "+dto.getWardorPatwariListId());
			//System.out.println("ward new"+form.getAppdto().getWardListId());
			
			String chkMohalla=areaManagementSQL.CHK_EXIST_wardPatwari_CHILD1;
			dbUtility.createPreparedStatement(chkMohalla);
			String mohallaarr[] = new String[1];
			mohallaarr[0]=id;
		//	mohallaarr[1]=dto.getAreaId();
		//	mohallaarr[2]=dto.getSubAreaListId();
			
			String countmoh = dbUtility.executeQry(mohallaarr);
			
			if(Integer.parseInt(countmoh)>=1 )
			{
				dto.setCheck("child1Exists");
			}
			else
				
			{
			boolean flag1=false;
			String check=areaManagementSQL.CHECK_EXIST_WARD;
			
			dbUtility.createPreparedStatement(check);
			String checkarr[] = new String[5];
			checkarr[0]=dto.getWardName().toUpperCase();
			checkarr[1]=dto.getWardHindiName();
			//checkarr[2]=dto.getWardorPatwariListId();
			checkarr[2]=dto.getTehsilListId();
			checkarr[3]=dto.getAreaId();
			checkarr[4]=dto.getSubAreaListId();
			
			    String count = dbUtility.executeQry(checkarr);
			
			if(Integer.parseInt(count)==1 || Integer.parseInt(count)==0)
			{
			String sql=areaManagementSQL.UPDATE_WARD;
			String abc[] = new String[5];
			abc[0]=dto.getWardName().toUpperCase();
			abc[1]=dto.getWardHindiName();
			abc[2]=dto.getWardDesc();
			abc[3]=dto.getWardorPatwariListId();
			abc[4]=id;
				dbUtility.createPreparedStatement(sql);
				
				flag = dbUtility.executeUpdate(abc);
				
				
				String check1=areaManagementSQL.CHECK_EXIST_WARD_IN_COLONY;
				
				dbUtility.createPreparedStatement(check1);
				String checkarr1[] = new String[1];
				//checkarr1[0]=dto.getSubAreaName();
				checkarr1[0]=id;
				    String count2 = dbUtility.executeQry(checkarr1);
				    
				    if(Integer.parseInt(count2)>0)
				    {
				    	String wardNamesql=areaManagementSQL.UPDATE_COLONY_WARD;
						String abc1[] = new String[2];
						abc1[0]=dto.getWardName().toUpperCase();
						abc1[1]=id;
							dbUtility.createPreparedStatement(wardNamesql);
							flag1 = dbUtility.executeUpdate(abc1);
							
							if (flag&&flag1)
							{
								
							dbUtility.commit();
							}
							else
							{
								dbUtility.rollback();
							}
				    }
				
				
				    else
				    {
				    	if (flag)
						{
							
						dbUtility.commit();
						}
						else
						{
							dbUtility.rollback();
						}
				    }
			}
			else{
				dto.setCheck("exists");
			}
		}
		}
		
		if("Mohalla".equalsIgnoreCase(type))
		{
			String radioid = dto.getClickRadio();
			/*System.out.println(":::"+dto.getWardId());
			System.out.println("1"+dto.getWardListId());
			System.out.println("2"+dto.getWardorPatwariListId());
			if("Y".equalsIgnoreCase(radioid)){}
			String chkMohalla=areaManagementSQL.CHK_EXIST_MOHALLA_CHILD1;
			dbUtility.createPreparedStatement(chkMohalla);
			String mohallaarr[] = new String[1];
			mohallaarr[0]=id;
			//mohallaarr[1]=dto.get
			
		//	checkarr[5]=dto.getSubClauseListId();
			String countmoh = dbUtility.executeQry(mohallaarr);
			
			if(Integer.parseInt(countmoh)>=1 )
			{
				dto.setCheck("child1Exists");
			}
			else*/
			if("Y".equalsIgnoreCase(radioid))
			{
				String chkMohalla=areaManagementSQL.CHK_EXIST_wardPatwari_CHILD1;
				dbUtility.createPreparedStatement(chkMohalla);
				String mohallaarr[] = new String[1];
				mohallaarr[0]=dto.getWardListId();
				//mohallaarr[1]=dto.get
				
			//	checkarr[5]=dto.getSubClauseListId();
				String countmoh = dbUtility.executeQry(mohallaarr);
				
				if(Integer.parseInt(countmoh)>=1 )
				{
					dto.setCheck("child1Exists");
				}
				else
				{
				String sql=areaManagementSQL.UPDATE_MOHALLA_ALL;
				
				String abc[] = new String[2];
				abc[0]=dto.getSubClauseListId();
				abc[1]=dto.getWardListId();
					dbUtility.createPreparedStatement(sql);
					
					flag = dbUtility.executeUpdate(abc);
					
					
					if (flag)
					{
					dbUtility.commit();
					}
					else
					{
						dbUtility.rollback();
					}
				
			}
			}
			else
			{
				String chkMohalla=areaManagementSQL.CHK_EXIST_MOHALLA_CHILD1;
				dbUtility.createPreparedStatement(chkMohalla);
				String mohallaarr[] = new String[1];
				mohallaarr[0]=id;
				//mohallaarr[1]=dto.get
				
			//	checkarr[5]=dto.getSubClauseListId();
				String countmoh = dbUtility.executeQry(mohallaarr);
				
				if(Integer.parseInt(countmoh)>=1 )
				{
					dto.setCheck("child1Exists");
				}
				else
				{
			String wardNamesql=areaManagementSQL.GET_WARD_PATWARI_NAME;
			 dbUtility.createPreparedStatement(wardNamesql);
			  String subtemp[] = new String[1];
			  subtemp[0]=dto.getWardListId();
			  String wardName = dbUtility.executeQry(subtemp);
			  
			String check=areaManagementSQL.CHECK_EXIST_COLONY;
			
			dbUtility.createPreparedStatement(check);
			String checkarr[] = new String[5];
			checkarr[0]=dto.getTehsilListId();
			checkarr[1]=dto.getSubAreaListId();
			checkarr[2]=wardName;
			checkarr[3]=dto.getMohallaName().toUpperCase();
			checkarr[4]=dto.getMohallaHindiName();
		//	checkarr[5]=dto.getSubClauseListId();
			String count = dbUtility.executeQry(checkarr);
			
			if(Integer.parseInt(count)==1 || Integer.parseInt(count)==0)
			{
			String sql=areaManagementSQL.UPDATE_MOHALLA;
		
			String abc[] = new String[5];
			abc[0]=dto.getMohallaName().toUpperCase();
			abc[1]=dto.getMohallaHindiName();
			abc[2]=dto.getMohallaDesc();
			abc[3]=dto.getSubClauseListId();
			abc[4]=id;
				dbUtility.createPreparedStatement(sql);
				
				flag = dbUtility.executeUpdate(abc);
				
				
				if (flag)
				{
				dbUtility.commit();
				}
				else
				{
					dbUtility.rollback();
				}
		}
			else{
				dto.setCheck("exists");
			}
		}
		}
		}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public areaManagementDTO getConstRate(areaManagementDTO dto) {
		
		DBUtility dbUtility = null;
		ArrayList details = new ArrayList();
		ArrayList <areaManagementDTO>returnList=null;
		try 
		{
			dbUtility = new DBUtility();
			
			
			dbUtility.createStatement();
			String sql =areaManagementSQL.SELECT_CONSTRUCT_RATES;
			details=dbUtility.executeQuery(sql);
			if (details != null) {
				
				for (int i = 0; i < details.size(); i++) {
					ArrayList lst = (ArrayList) details.get(i);
				
				if(i==0)
				{
					dto.setRcc41((String)lst.get(0));
					dto.setRbc41((String)lst.get(1));
					 dto.setTin41((String)lst.get(2));
					 dto.setKabelu41((String)lst.get(3));
 
					 
				}
				
				else if(i==1)
				{
					dto.setRcc42((String)lst.get(0));
					 dto.setRbc42((String)lst.get(1));
					 dto.setTin42((String)lst.get(2));
					 dto.setKabelu42((String)lst.get(3));

				}
				
				else if(i==2)
				{
					
					dto.setRcc43((String)lst.get(0));
					dto.setRbc43((String)lst.get(1));
					 dto.setTin43((String)lst.get(2));
					 dto.setKabelu43((String)lst.get(3));
					 
					 
					 
				}
				
				else if(i==3)
				{
					dto.setRcc44((String)lst.get(0));
					dto.setRbc44((String)lst.get(1));
					dto.setTin44((String)lst.get(2));
					 dto.setKabelu44((String)lst.get(3));
					 
					
				}
				else if(i==4)
				{
					dto.setRccOthers((String)lst.get(0));
					 dto.setRbcOthers((String)lst.get(1));
					dto.setTinOthers((String)lst.get(2));
					 dto.setKabeluOthers((String)lst.get(3));
				}
					
				}
			}
			
			
		}
		catch (Exception e) {
			//System.out.println("Caugut in Exception: " + e);
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}

	public boolean updateConstData(areaManagementForm form, String userId) {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag1=false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String sql=areaManagementSQL.DEACT_CONST_RATES;
		String sql1=areaManagementSQL.INSERT_CONST_RATES;
		String abc[] = new String[6];
		try {
			dbUtility.createStatement();
			
			flag = dbUtility.executeUpdate(sql);
			
		
			if (flag)
			{
				for(int i=0;i<5;i++)
				{
					if(i==0)
					{
					dbUtility.createPreparedStatement(sql1);
					abc[0]="1";
					abc[1]=form.getAppdto().getRcc41();
					abc[2]=form.getAppdto().getRbc41();
					abc[3]=form.getAppdto().getTin41();
					abc[4]=form.getAppdto().getKabelu41();
					abc[5]=userId;
					flag1 = dbUtility.executeUpdate(abc);
					}
					if(i==1)
					{
						dbUtility.createPreparedStatement(sql1);
						abc[0]="2";
						abc[1]=form.getAppdto().getRcc42();
						abc[2]=form.getAppdto().getRbc42();
						abc[3]=form.getAppdto().getTin42();
						abc[4]=form.getAppdto().getKabelu42();
						abc[5]=userId;
					flag1 = dbUtility.executeUpdate(abc);
					}
					if(i==2)
					{
						dbUtility.createPreparedStatement(sql1);
						abc[0]="3";
						abc[1]=form.getAppdto().getRcc43();
						abc[2]=form.getAppdto().getRbc43();
						abc[3]=form.getAppdto().getTin43();
						abc[4]=form.getAppdto().getKabelu43();
						abc[5]=userId;
					flag1 = dbUtility.executeUpdate(abc);
					}
					if(i==3)
					{
						dbUtility.createPreparedStatement(sql1);
						abc[0]="4";
						abc[1]=form.getAppdto().getRcc44();
						abc[2]=form.getAppdto().getRbc44();
						abc[3]=form.getAppdto().getTin44();
						abc[4]=form.getAppdto().getKabelu44();
						abc[5]=userId;
					flag1 = dbUtility.executeUpdate(abc);
					}
					if(i==4)
					{
						dbUtility.createPreparedStatement(sql1);
						abc[0]="5";
						abc[1]=form.getAppdto().getRccOthers();
						abc[2]=form.getAppdto().getRbcOthers();
						abc[3]=form.getAppdto().getTinOthers();
						abc[4]=form.getAppdto().getKabeluOthers();
						abc[5]=userId;
					flag1 = dbUtility.executeUpdate(abc);
				}
				}
				
				if(flag1)
				{
					dbUtility.commit();
				}
				else
				{
					dbUtility.rollback();
					flag=false;
				}
				
			}
			else
			{
				dbUtility.rollback();
				flag=false;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return flag;
	}


		
}
