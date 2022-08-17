package com.wipro.igrs.regcompletion.bo;


import com.wipro.igrs.regcompletion.dao.RegComp1DAO;
import com.wipro.igrs.regcompletion.dto.Common1DTO;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;


public class RegComp1BO {
    
	private  Logger logger = 
		(Logger) Logger.getLogger(RegComp1BO.class);
    public RegComp1BO() {
    }
    public ArrayList countryStackBO()throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (countryStackBO) Method");
    		listBO=refDAO.countryStackDAO();
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList stateStackBO(String _countryId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (stateStackBO) Method");
    		listBO=refDAO.stateStackDAO(_countryId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList photoIdStackBO()throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (photoIdStackBO) Method");
    		listBO=refDAO.photoIdStackDAO();
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList casteStackBO()throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (casteStackBO) Method");
    		listBO=refDAO.casteStackDAO();
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList religionStackBO()throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (religionStackBO) Method");
    		listBO=refDAO.religionStackDAO();
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList districtStackBO(String _stateId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (districtStackBO) Method");
    		listBO=refDAO.districtStackDAO(_stateId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public boolean addPartyBO(String _param[], FormFile filePhoto,
	    FormFile fileSign, FormFile fileThumb)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		boolean flag=false;
    	try{
    		logger.info("Inside (addPartyBO) Method");
    		flag=refDAO.addPartyDAO(_param,filePhoto,fileSign,fileThumb);
    		return flag;
    	}catch (Exception e) {
    		logger.error(e);
    		return flag;
    	}
    }
    public boolean addOrgaBO(String _param[])throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		boolean flag=false;
    	try{
    		logger.info("Inside (addOrgaBO) Method");
    		flag=refDAO.addOrgaDAO(_param);
    		return flag;
    	}catch (Exception e) {
    		logger.error(e);
    		return flag;
    	}
    }
    public ArrayList selectPartiesBO(String[] _appliNo)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (selectPartiesBO) Method");
    		listBO=refDAO.selectPartiesDAO(_appliNo);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList selectPartiesBO(String appliNo)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (selectPartiesBO) Method");
    		listBO=refDAO.selectPartiesDAO(appliNo);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList viewPartyBO(String _partyId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (viewPartyBO) Method");
    		listBO=refDAO.viewPartyDAO(_partyId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public boolean deletePartyBO(String _partyId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		boolean flag=false;
    	try{
    		logger.info("Inside (deletePartyBO) Method");
    		flag=refDAO.deletePartyDAO(_partyId);
    		return flag;
    	}catch (Exception e) {
    		logger.error(e);
    		return flag;
    	}
    }
    public ArrayList retrievePartyBO(String _partyId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (retrievePartyBO) Method");
    		listBO=refDAO.retrievePartyDAO(_partyId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public boolean updatePartyBO(String _partyType, String param[])throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		RegComp1DAO refDAO=new RegComp1DAO();
		boolean flag=false;
    	try{
    		logger.info("Inside (updatePartyBO) Method");
    		flag=refDAO.updatePartyDAO(_partyType, param);
    		return flag;
    	}catch (Exception e) {
    		logger.error(e);
    		return flag;
    	}
    }
}