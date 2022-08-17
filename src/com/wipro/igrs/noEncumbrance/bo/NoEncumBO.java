package com.wipro.igrs.noEncumbrance.bo;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.copyissuance.dao.CertifiedCopyDetailsDAO;
import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;
import com.wipro.igrs.empmgmt.dao.EmpmgmtViewDAO;
import com.wipro.igrs.empmgmt.dto.FamilyMemberDTO;
import com.wipro.igrs.estamping.bd.EstampingBD;
import com.wipro.igrs.noEncumbrance.dto.DashBoardDTO;
import com.wipro.igrs.noEncumbrance.bd.NoEncumBD;
import com.wipro.igrs.noEncumbrance.dao.NoEncumDAO;
import com.wipro.igrs.noEncumbrance.dto.FloorDetailsDTO;
import com.wipro.igrs.noEncumbrance.dto.NoEncumDTO;
public class NoEncumBO {
    
	private Logger logger = 
		(Logger) Logger.getLogger(NoEncumBO.class);
    public NoEncumBO() {
    }
    
    public ArrayList  countryStackBO(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (countryStackBO) Method");
    		listBO=refDAO.countryStackDAO( languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * @return
     * @throws Exception
     */
    
    public ArrayList  countryPropBO(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (countryStackBO) Method");
    		listBO=refDAO.countrypropDAO( languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  countryBO(String langauge)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (countryStackBO) Method");
    		listBO=refDAO.countryDAO(langauge);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
   /**
    *  
    * @param _countryId
    * @return
    * @throws Exception
    */
    public ArrayList  stateStackBO(String _countryId,String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (stateStackBO) Method");
    		listBO=refDAO.stateStackDAO(_countryId,languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _countryId
     * @return
     * @throws Exception
     */
    public ArrayList  statePropBO(String _countryId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (stateStackBO) Method");
    		listBO=refDAO.statePropDAO(_countryId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _countryId
     * @return
     * @throws Exception
     */
    public ArrayList  stateBO(String _countryId,String language)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (stateStackBO) Method");
    		listBO=refDAO.stateDAO(_countryId,language);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  photoIdStackBO(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (photoIdStackBO) Method");
    		listBO=refDAO.photoIdStackDAO(languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  casteStackBO(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (casteStackBO) Method");
    		listBO=refDAO.casteStackDAO(languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  religionStackBO(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (religionStackBO) Method");
    		listBO=refDAO.religionStackDAO(languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    /**
     * 
     * @param _stateId
     * @return
     * @throws Exception
     */
    public ArrayList  districtStackBO(String _stateId,String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBO) Method");
    		listBO=refDAO.districtStackDAO(_stateId,languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _stateId
     * @return
     * @throws Exception
     */
    public ArrayList  districtPropBO(String _stateId,String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBO) Method");
    		listBO=refDAO.districtPropDAO(_stateId,languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _distId
     * @return
     * @throws Exception
     */
    public ArrayList  tehesilPropBO(String _distId,String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBO) Method");
    		listBO=refDAO.tehesilPropDAO(_distId,languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    /**
     * 
     * @param _tehsilId
     * @return
     * @throws Exception
     */
    public ArrayList  patwariPropBO(String _tehsilId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBO) Method");
    		listBO=refDAO.patwariPropDAO(_tehsilId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _patwariId
     * @return
     * @throws Exception
     */
    public ArrayList gramPropBO(String _patwariId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBO) Method");
    		listBO=refDAO.gramPropDAO(_patwariId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _stateId
     * @return
     * @throws Exception
     */
    public ArrayList  districtBO(String _stateId,String language)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBO) Method");
    		listBO=refDAO.districtDAO(_stateId,language);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _regNo
     * @return
     * @throws Exception
     */
    public ArrayList regIdCheckBO(String _regNo)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (regIdCheckBO) Method");
    		listBO=refDAO.regIdCheckDAO(_regNo);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _txnId
     * @param _frDate
     * @param _toDate
     * @return
     * @throws Exception
     */
    public ArrayList  searchListViewBO(String _txnId,String _frDate,String _toDate)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (searchListViewBO) Method");
    		String param[]=new String [3];
    		param[0]=_txnId;
    		param[1]=_frDate;
    		param[2]=_toDate;
    		listBO=refDAO.searchListViewDAO(param);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _regUserId
     * @return
     * @throws Exception
     */
    public ArrayList  userIdCheckBO(String _regUserId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (userIdCheckBO) Method");
    		String param[]=new String [1];
    		param[0]=_regUserId;
    		listBO=refDAO.userIdCheckDAO(param);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    /**
     * 
     * @param _sdDTO
     * @return
     * @throws Exception
     */
    public boolean insertSuppliDetailsBO(NoEncumDTO _sdDTO)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
    	try{
    		logger.info("Inside (insertSuppliDetailsBO) Method");
    		return refDAO.insertSuppliDetailsDAO(_sdDTO);
    	}catch (Exception e) {
    		logger.error(e);
    		return false;
    	}
    }
    /**
     * 
     * @param _txnId
     * @return
     * @throws Exception
     */
    public ArrayList getTotalDetailsBO(String _txnId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (getTotalDetailsBO) Method");
    		String param[]=new String [1];
    		param[0]=_txnId;
    		listBO=refDAO.getTotalDetailsDAO(param);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
   /**
    *  
    * @param referceId
    * @return
    */
    public ArrayList<FloorDetailsDTO> getFloorDetails(String referceId) {
		ArrayList<FloorDetailsDTO> retVal = new ArrayList<FloorDetailsDTO>();
		NoEncumDAO refDAO=new NoEncumDAO();
		try {			
			retVal = refDAO.getFloorDetails(referceId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal ;

	}
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  propertyBO(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (casteStackBO) Method");
    		listBO=refDAO.propertyDAO(languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
   /**
    *  
    * @return
    * @throws Exception
    */
    public ArrayList  getMunicipalList()throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (casteStackBO) Method");
    		listBO=refDAO.getMunicipalList();
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  getAreaTypeList(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
    	try{
    		logger.info("Inside (casteStackBO) Method");
    		listBO=refDAO.getAreaTypeList(languageLocale);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    public ArrayList getPendingNoEncumApps(String userId) throws Exception
    {
    	
    	NoEncumDAO dao= new NoEncumDAO();
    	
    	ArrayList pendingAppListFinal = new ArrayList();
    	ArrayList list=dao.getPendingNoEncumApps(userId);
    	
    	if(list!=null && list.size()>0){
    		
    		ArrayList rowList;
    		
            try{
    		for (int i = 0; i < list.size(); i++) {
    			
    			NoEncumDTO objdto = new NoEncumDTO();
    			
    			rowList = (ArrayList)list.get(i);
    			//objDashBoardDTO = new DashBoardDTO();
    		
    			//if(rowList.get(0).toString().length()==11){
    			
    			objdto.setTxnId(rowList.get(0).toString());
    				
    				//if (rowList.get(2)== null)
    		//	objdto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(6));
    			//	else
    		//	objdto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(3));
    				 
    			//}else{
    				//objdto.setTypeReq(rowList.get(1).toString());
    			
    				//objDashBoardDTO.setPaidAmount(rowList.get(2));
    				System.out.println("*******"+(String)rowList.get(2));
    			if ((rowList.get(1)).toString().equals(""))
    			{
    				System.out.println("*******");
    				objdto.setPaidAmount("--");
    			}
    				
    			else
    			
    				objdto.setPaidAmount(rowList.get(1).toString());
    			
    			if (rowList.get(2).toString().equalsIgnoreCase(""))
    			{
    				objdto.setBalanceAmount(rowList.get(5).toString());
    			}
    			else
    			{
    				objdto.setBalanceAmount(rowList.get(2).toString());
    			}
    			
    			//objEstampDetailsDTO.setLastTransactionDate(rowList.get(4).toString());
    			
    			if (rowList.get(3).toString().equalsIgnoreCase(""))
    			{
    				objdto.setLastTransactionDate(rowList.get(4).toString());
    			}
    			else
    			{
    				objdto.setLastTransactionDate(rowList.get(3).toString());
    			}
    			System.out.println(rowList.get(4).toString());
    			System.out.println(rowList.get(3).toString());
    			System.out.println(objdto.getLastTransactionDate());
    			pendingAppListFinal.add(objdto);
    		
    		}
            }	 catch(Exception e){
            	e.printStackTrace();
            	
            }
    		
        }
            	
    		ArrayList newlist=dao.getPendingAppsinNoEncum(userId); 
    		ArrayList rowList1;
    		for (int i = 0; i < newlist.size(); i++) {
    			
    			NoEncumDTO objdto = new NoEncumDTO();
    			
    			rowList1 = (ArrayList)newlist.get(i);
    			//objDashBoardDTO = new DashBoardDTO();
    		
    			//if(rowList.get(0).toString().length()==11){
    			
    			objdto.setTxnId(rowList1.get(0).toString());
    				
    				//if (rowList.get(2)== null)
    		//	objdto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(6));
    			//	else
    		//	objdto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(3));
    				 
    			//}else{
    				//objdto.setTypeReq(rowList1.get(0).toString());
    			
    				//objDashBoardDTO.setPaidAmount(rowList.get(2));
    			
    				
    				objdto.setPaidAmount("--");
    				objdto.setBalanceAmount(rowList1.get(1).toString());
    			
    			
    			//objEstampDetailsDTO.setLastTransactionDate(rowList.get(4).toString());
    			
    				objdto.setLastTransactionDate(rowList1.get(2).toString());
    				pendingAppListFinal.add(objdto);
    		
    		
           
    	}
   
    	return pendingAppListFinal;
    }

   public ArrayList  getDetailsOnId(String txnId,String languageLocale)
   {
	  
	   logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
		try{
   		logger.info("Inside (casteStackBO) Method");
   		listBO=refDAO.getDetailsOnId(txnId,languageLocale);
   		return listBO;
		}catch (Exception e) 
		{
   		logger.error(e);
   		
   	}
		return null;
   }
   public ArrayList  getPropertyMappingList(String txnId)
   {
	  
	   logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
		try{
   		logger.info("Inside (casteStackBO) Method");
   		listBO=refDAO.getPropertMappingList(txnId);
   		return listBO;
		}catch (Exception e) 
		{
   		logger.error(e);
   		
   	}
		return null;
   }

   public ArrayList  getPropertyDetails(String txnId)
   {
	  
	   logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
		try{
   		logger.info("Inside (casteStackBO) Method");
   		listBO=refDAO.getPropertyDetails(txnId);
   		return listBO;
		}catch (Exception e) 
		{
   		logger.error(e);
   		
   	}
		return null;
   }

public ArrayList getKhasraDetails(String txnId) {

	   logger.debug("WE ARE IN BO Debug");
		NoEncumDAO refDAO=new NoEncumDAO();
		ArrayList  listBO=new ArrayList ();
		try{
		logger.info("Inside (casteStackBO) Method");
		listBO=refDAO.getKhasraDetails(txnId);
		return listBO;
		}catch (Exception e) 
		{
		logger.error(e);
		
	}
		return null;
}

public ArrayList getFloorDetail(String txnId) {
	  logger.debug("WE ARE IN BO Debug");
			NoEncumDAO refDAO=new NoEncumDAO();
			ArrayList  listBO=new ArrayList ();
			try{
			logger.info("Inside (casteStackBO) Method");
			listBO=refDAO.getFloorDetail(txnId);
			return listBO;
			}catch (Exception e) 
			{
			logger.error(e);
			
		}
			return null;
}

public String getPropCountryName(String id,String languageLocale)
{
	NoEncumDAO dao= new NoEncumDAO();
	return dao.getPropCountryName(id,languageLocale);
}

public String getPropStateName(String id,String languageLocale)
{
	NoEncumDAO dao= new NoEncumDAO();
	return dao.getPropStateName(id,languageLocale);
}
public String getPropDistrictName(String id,String languageLocale)
{
	NoEncumDAO dao= new NoEncumDAO();
	return dao.getPropDistrictName(id,languageLocale);
}
public String getPropTehsilName(String id,String languageLocale)
{
	NoEncumDAO dao= new NoEncumDAO();
	return dao.getPropTehsilName(id,languageLocale);
}
public String getPropPatwariName(String id,String languageLocale)
{
	NoEncumDAO dao= new NoEncumDAO();
	return dao.getPropPatwariName(id,languageLocale);
}
public String getPropGramName(String id,String languageLocale)
{
	NoEncumDAO dao= new NoEncumDAO();
	return dao.getPropGramName(id,languageLocale);
}
public String getPropMCDName(String id,String languageLocale)
{
	NoEncumDAO dao= new NoEncumDAO();
	return dao.getPropMCDName(id,languageLocale);
}
public ArrayList getPaymentParameter(String regno,String languageLocale)
{
	
		try {
			NoEncumDAO dao= new NoEncumDAO();
			return dao.getPaymentParameter(regno,languageLocale);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
}

public ArrayList isDRO(String officeId) {

	try {
		NoEncumDAO dao= new NoEncumDAO();
		return dao.isDRO(officeId);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return null;
}
public String getofficenName(String officeid,String languageLocale)
{
	try {
		NoEncumDAO dao= new NoEncumDAO();
	return dao.getofficeName(officeid,languageLocale);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return null;
}
public boolean UpdateDownloadStatus(NoEncumDTO dto)
{
	
	try {
		NoEncumDAO dao= new NoEncumDAO();
		return dao.UpdateDownloadStatus(dto);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public String getRegTxn(String regno)
{
	try {
		NoEncumDAO dao= new NoEncumDAO();
		return dao.getRegTxn(regno);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

public boolean updateIssuance(String[] param, String[] statusparam,String roleId, String funId, String userId, NoEncumDTO dto) {
	
	try {
		NoEncumDAO dao= new NoEncumDAO();
		return dao. updateIssuance(param, statusparam,roleId, funId, userId, dto);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public String getCollectionOffice(String regNo)
{
	try {
		NoEncumDAO dao= new NoEncumDAO();
		return dao. getCollectionOffice(regNo);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
}

public ArrayList getPdfDetails(String propId,String language) {
	try {
		NoEncumDAO dao= new NoEncumDAO();
		return dao.getPdfDetails(propId,language);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
	
}

public String valArea(String valId,String propId)
{
	NoEncumDAO dao= new NoEncumDAO();
	ArrayList list=dao.valArea(valId, propId);
	if(list!=null&& list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
			ArrayList rowList= (ArrayList) list.get(i);
			if("1".equalsIgnoreCase(propId))
			{
			return (String)rowList.get(0);	
			}
			else if(("3".equalsIgnoreCase(propId)))
			{
				double area1=Double.parseDouble((String)rowList.get(0));
				double area2=Double.parseDouble((String)rowList.get(1));
				return String.valueOf(area1+area2);
			}
			else
			{
				String buildId=(String)rowList.get(0);
				if("1".equalsIgnoreCase(buildId))
				{
					return (String)rowList.get(1);	
				}
				else if("4".equalsIgnoreCase(buildId))
				{
					return (String)rowList.get(3);	

				}
				else
				{
					return (String)rowList.get(2);	
				}
			}
		}
	}
	
		return "0";
	
	
}

public String getSignPath(String userId) {
	NoEncumDAO dao= new NoEncumDAO();
	String path =dao.getPath(userId);
	
	return path;
}

public ArrayList getAreaDetails(String propId, String language) {
	try {
		NoEncumDAO dao= new NoEncumDAO();
		return dao.getAreaDetails(propId,language);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
}


}