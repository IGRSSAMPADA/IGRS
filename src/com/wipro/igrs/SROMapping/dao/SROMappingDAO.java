package com.wipro.igrs.SROMapping.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.SROMapping.dto.SROMappingDTO;
import com.wipro.igrs.SROMapping.form.SROMappingForm;
import com.wipro.igrs.SROMapping.sql.SROMappingSQL;
import com.wipro.igrs.areaManagement.sql.areaManagementSQL;
import com.wipro.igrs.common.dto.CommonDTO;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.guideline.sql.CommonSQL;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;
import com.wipro.igrs.useracctmgmt.sql.UserCommonSQL;


public class SROMappingDAO {
	
	DBUtility dbUtil=null ;
	private static Logger logger = org.apache.log4j.Logger.getLogger(SROMappingDAO.class);

	public ArrayList getCountry(String language) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_COUNTRY_LIST;
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
			String sql =SROMappingSQL.SELECT_STATE_LIST;
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


	public ArrayList getDistrict(String StateId, String officeId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_DISTRICT_LIST;
			String temp[] = new String[2];
			temp[0]=officeId;
			temp[1]=StateId;
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
	
	public ArrayList getDistrictEdit(String stateId,String officeId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_DISTRICT_LIST_EDIT;
			String temp[] = new String[2];
			temp[0]=officeId;
			temp[1]=stateId;
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
			String sql =SROMappingSQL.SELECT_TEHSIL_LIST;
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
	
	public ArrayList getTehsilEdit(String distId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_TEHSIL_LIST_EDIT;
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

	public ArrayList getArea(String distId, String tehId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_AREA_LIST;
			String temp[] = new String[2];
			temp[0]=distId;
			temp[1]=tehId;
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

	public ArrayList getAreaEdit(String distId , String tehId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_AREA_LIST_EDIT;
			String temp[] = new String[2];
			temp[0]=distId;
			temp[1]=tehId;
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			/*String sql ="";
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			if("SubArea".equalsIgnoreCase(logicId))
			{
				sql =SROMappingSQL.SELECT_URBANAREA_LIST;
			}
			else
			{
				 sql =SROMappingSQL.SELECT_AREA_LIST;
			}
			String temp[] = new String[1];
			temp[0]="1";
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);*/
			
			
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

	
	public ArrayList getSubArea(String tehId, String areaId,String distId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_SAREA_LIST;
			String temp[] = new String[3];
			temp[0]=distId;
			temp[1]=tehId;
			temp[2]=areaId;
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

	public ArrayList getSubAreaEdit(String tehId, String areaId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			String sql="";
			
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			if("1".equalsIgnoreCase(areaId))
			{
				 sql =SROMappingSQL.SELECT_RURAL_SUBAREA_LIST_EDIT;
				 String temp[] = new String[2];
					temp[0]=areaId;
					temp[1]=tehId;
					dbUtility.createPreparedStatement(sql);
					details=dbUtility.executeQuery(temp);
			}
			else
			{
				 sql =SROMappingSQL.SELECT_URBAN_SUBAREA_LIST_EDIT;
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
			String sql =SROMappingSQL.SELECT_WARD_LIST;
			String temp[] = new String[6];
			temp[0]=tehId;
			temp[1]=areaId;
			temp[2]=subareaId;
			temp[3]=tehId;
			temp[4]=areaId;
			temp[5]=subareaId;
			
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

	public ArrayList getwardEdit(String tehId, String areaId, String subareaId, String sroId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_WARD_LIST_EDIT;
			String temp[] = new String[4];
			temp[0]=tehId;
			temp[1]=areaId;
			temp[2]=subareaId;
			temp[3]=sroId;
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

	
	public boolean saveAllWard(String[] ward_data,String[] ofc_data) {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag1=false;
		boolean flag2=false;
		boolean flag3=false;
		try{
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int size = ward_data.length;
        for (int i=0; i<size; i++)
        {
            System.out.println(ward_data[i]);
        }
		}
		/*Iterator<String> crunchifyIterator = ward_data.iterator();
		while (crunchifyIterator.hasNext()) {
			System.out.println(crunchifyIterator.next());
		}*/
			
			
		/*String qstnMarks = "(";
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
			*/
	/*	try {
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
		}*/
	 catch (Exception e) {
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

	public boolean saveWardData(SROMappingForm SROform,String userId, String[] ward_data) throws Exception {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag2=false;
		int seqVal = 0;
		String seq="";
	
		try{
		
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String colonyArr[] = ward_data.split(",");
		System.out.println("length of ward :: "+ward_data.length);
		for(int i = 0; i<ward_data.length; i++){
			String sql = SROMappingSQL.GET_OFFICE_SEQ;
			logger.debug("query is:"+SROMappingSQL.GET_OFFICE_SEQ);
			dbUtility.createStatement();
			 seq = dbUtility.executeQry(sql);
			//pst = connTest.prepareStatement(CommonSQL.SELECT_SEQ);
			seqVal = Integer.parseInt(seq);
                logger.debug("seqVal"+seqVal);
			String sql1=SROMappingSQL.INSERT_SRO_OFC_DATA;
			System.out.println("INSERT_SRO_OFC_DATA");
		try {
			dbUtility.createPreparedStatement(sql1);
			String temp[] = new String[8];
			
			temp[0]=seq;
			temp[1]=SROform.getAppdto().getDistrictId();
			temp[2]=SROform.getAppdto().getTehsilListId();
			temp[3]=SROform.getAppdto().getAreaId();
			temp[4]=SROform.getAppdto().getSubAreaListId();
			temp[5]=userId;
			//temp[5]=SROform.getAppdto().getWardListId();
			temp[6]=ward_data[i];
			temp[7]=SROform.getAppdto().getSroListId();
			  flag = dbUtility.executeUpdate(temp);
		}catch(Exception e){
			e.printStackTrace();
			}
		}
		if(flag)
		{
			dbUtility.commit();
		}
		
		else
		{
			dbUtility.rollback();
		}
	//	}
		
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
	
	
	public ArrayList getsroOfc(String distId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_SRO_OFC_LIST;
			String temp[] = new String[1];
			temp[0]=distId;
			//temp[1]=distId;
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
	
	public ArrayList getsroOfcEdit(String distId ,String tehId,String areaId,String subAreaId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SROMappingSQL.SELECT_SRO_OFC_LIST_EDIT;
			String temp[] = new String[4];
			
			temp[0]=distId;
			temp[1]=tehId;
			temp[2]=areaId;
			temp[3]=subAreaId;
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
	public boolean updateWard(String[] abc ) {
		DBUtility dbUtility = null;
		boolean flag=false;
		
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
		} catch (Exception e) {
			
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
	
		String sql=SROMappingSQL.DELETE_ENTRY+qstnMarks;
	
		try {
			dbUtility.createPreparedStatement(sql);
			
			flag = dbUtility.executeUpdate(abc);
		
				if(flag)
			{
			dbUtility.commit();
			}
			else
			{
				dbUtility.rollback();
				flag=false;
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
		
				e.printStackTrace();
			}
		}	
		return flag;
	}

	
}
