package com.wipro.igrs.caveatsMaster.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.caveatsMaster.dao.CaveatsDAO;
import com.wipro.igrs.caveatsMaster.dto.CaveatsDTO;
import com.wipro.igrs.caveatsMaster.exception.CaveatsException;

public class CaveatsBO {

	public CaveatsBO() {
	}

	private Logger logger = (Logger) Logger.getLogger(CaveatsBO.class);

	public boolean logCaveatsBO(String param1[], String param2[])
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (logCaveatsBO) Method");
			return refDAO.logCaveatsDAO(param1, param2);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
// /////////////mona
	 public boolean updateCaveatsMasterBO(String[] param,String originalName)throws Exception,CaveatsException
	 {
			logger.debug("WE ARE IN BD Debug");
			boolean result=false;
			CaveatsDAO refDAO = new CaveatsDAO();
			
			if(!originalName.equals(param[0]))
			{
			if(refDAO.isCaveatsExist(param[0]))
			{
				
				throw new CaveatsException();
			}
			}
			
			try
			{
				result=refDAO.updateCaveatsMasterDAO(param);
				return result;
			}
		 catch (Exception e) {
			logger.error(e);
			return result;
		}
	 }
	 public boolean deleteCaveatsMasterBO(String id)throws Exception
	 {
			logger.debug("WE ARE IN BD Debug");
			boolean result=false;
			CaveatsDAO refDAO = new CaveatsDAO();
			
			
			
			try
			{
				result=refDAO.deleteCaveatsMasterDAO(id);
				return result;
			}
		 catch (Exception e) {
			logger.error(e);
			return result;
		}
	 }
	public CaveatsDTO retrieveCavietsByNameBO(String name)
	{
		
	logger.debug("WE ARE IN BD Debug");
		
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (retrieveCavietsByName) Method");
			return refDAO.retrieveCavietsByName(name);
			
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList listCaveatsBO() throws Exception {
		logger.debug("WE ARE IN BD Debug");
		
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (listCaveatsBO) Method");
			return refDAO.retrieveAllCaveats();
			
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public boolean insertCaveatsMasterBO(String [] param)throws Exception,CaveatsException
	{
		logger.debug("WE ARE IN BD Debug");
		boolean result=false;
		CaveatsDAO refDAO = new CaveatsDAO();
		
		if(refDAO.isCaveatsExist(param[0]))
		{
			throw new CaveatsException();
		}
		
		try
		{
			result=refDAO.insertCaveatsMasterDAO(param);
			return result;
		}
	 catch (Exception e) {
		logger.error(e);
		return result;
	}
	}
	
	// /////////////////////////////////////////////
	public ArrayList findCaveatBO(String _refId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (findCaveatBO) Method");
			return refDAO.findCaveatDAO(_refId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList releaseCvtBankByRefIdBO(String _refId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (releaseCvtBankByRefIdBO) Method");
			return refDAO.releaseCvtBankByRefIdDAO(_refId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList releaseSearchByAllBO(String param[])
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (releaseSearchByAllBO) Method");
			return refDAO.releaseSearchByAllDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList relsCavtBankByAllBO(String param[])
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (relsCavtBankByAllBO) Method");
			return refDAO.relsCavtBankByAllDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList releaseByRegnoBO(String _regNo) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (releaseByRegnoBO) Method");
			return refDAO.releaseByRegnoDAO(_regNo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList searchRegIdBO(String _regNo) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchRegIdBO) Method");
			return refDAO.searchRegIdDao(_regNo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean modifyFlagBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (modifyFlagBO) Method");
			return refDAO.modifyFlagDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean relBankUpdationBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (relBankUpdationBO) Method");
			return refDAO.relBankUpdationDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public ArrayList searchByRefidBO(String _refid) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchByRefidBO) Method");
			return refDAO.searchByRefidDAO(_refid);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList searchByAllBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchByAllBO) Method");
			return refDAO.searchByAllDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList searchForPinBO(String _regNo, String _pin)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchForPinBO) Method");
			return refDAO.searchForPinDAO(_regNo, _pin);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public CaveatsDTO insertOttBO(String _regNo, String _pinOtt, String _ottauto)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (insertOttBO) Method");
			return refDAO.insertOttDAO(_regNo, _pinOtt, _ottauto);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList searchForOttBO(String _regNo, String _ott)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchForOttBO) Method");
			return refDAO.searchForOttDAO(_regNo, _ott);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean insertCaveatBankBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (insertCaveatBankBO) Method");
			return refDAO.insertCaveatBankDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean updateOttStatusBO(String _regNo, String _ott)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (updateOttStatusBO) Method");
			return refDAO.updateOttStatusDAO(_regNo, _ott);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
}